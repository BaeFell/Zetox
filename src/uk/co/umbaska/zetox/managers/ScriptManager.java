package uk.co.umbaska.zetox.managers;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

import org.bukkit.Bukkit;

import uk.co.umbaska.zetox.Zetox;
import uk.co.umbaska.zetox.types.Script;

public class ScriptManager {

	public static HashMap<File, Script> scriptList = new HashMap<File, Script>();
	
	public void registerScript(File f) {
		if(isSuitableFile(f, true)) {
			if(!scriptList.containsKey(f)) {
				Script script = new Script(f);
				scriptList.put(f, script);
				Bukkit.getLogger().info("[Zetox] Registered the file: " + f);
			}
		} else {
			if(Zetox.errorMessages) {
				Bukkit.getLogger().warning("[Zetox] The file: " + f + " is not a suitable type (ignored)");
			}
		}
		
	}
	
	public void unregisterScript(File f) {
		if(scriptList.containsKey(f)) {
			scriptList.remove(f);
		}
	}
	
	public HashMap<File, Script> getScripts() {
		return scriptList;
	}
	
	public Script getScript(File f) {
		if(scriptList.containsValue(f)) {
			return scriptList.get(f);
		}
		return null;
	}
	
	public Integer numberOfRegisteredScripts() {
		return scriptList.size();
	}
	
	public void loadScripts(File dir) {
		if(!dir.exists()) {
			dir.mkdirs();
			return;
		}
	    Set<File> fileTree = new HashSet<File>();
	    for (File entry : dir.listFiles()) {
	        if (entry.isFile()) {
	        	fileTree.add(entry);
	        } else {
	        	loadScripts(entry);
	        }
	    }
	    if(fileTree.size() > 0) {
	    	for(File f : fileTree) {
	    		if(isSuitableFile(f, true)) {
	    			Script script = new Script(f);
					script.setLoaded(false);
					script.setType(f.getName().replaceAll("^.*\\.(.*)$", "$1"));
					registerScript(f);
	    		}
	    	}
	    }
	}
	
	public Boolean isSuitableFile(File f, Boolean er) {
		try {
			if(!f.getCanonicalPath().startsWith("-")) {
				int delim = f.getName().lastIndexOf(".");
				String ext = f.getName().substring(delim >= 0 ? delim : 0);
				FileTypes ft = new FileTypes();
				if(ft.isType(ext)) {
					return true;
				} else {
					Bukkit.getLogger().info("[Zetox] Invalid file extension for: " + f.getName() + " (" + ext + ")");
				}
			}
		} catch (IOException e) {
			if(Zetox.errorMessages || er) {
				Bukkit.getLogger().warning("[Zetox] Failed to get canonical path for: " + f);
				e.printStackTrace();
			}
			return false;
		}
		return false;
	}
}
