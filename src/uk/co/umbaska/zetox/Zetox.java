package uk.co.umbaska.zetox;

import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

import uk.co.umbaska.zetox.parser.VariableManager;

public class Zetox extends JavaPlugin {

	public static Double ZetoxVersion = 1.0;
	
	public static Integer defaultInteger = 0; // the default log message level
	public static Boolean debugMode = true;
	
	public static ZetoxAPI ZetoxAPI;
	
	public void onEnable() {
		if(defaultInteger > 2) {
			defaultInteger = 0;
			log(defaultInteger, "Reset defaultInteger to 0 as it exceeds allowed");
		}
		
		ZetoxAPI = new ZetoxAPI();
	}
	
	/*
	 * 0 = info
	 * 1 = warn
	 * 2 = severe
	 */
	public static void log(Integer level, String message) {
		if(level == 0) {
			Bukkit.getLogger().info("[Zetox] " + message);
		} else if(level == 1) {
			Bukkit.getLogger().warning("[Zetox] " + message);
		} else if(level == 2) {
			Bukkit.getLogger().severe("[Zetox] " + message);
		} else {
			log(0, "Invalid level given! Defaulting to: " + defaultInteger);
			log(defaultInteger, message);
		}
	}
	
	public static void info(String message) {
		log(0, message);
	}
	
	public static void log(String message) {
		info(message);
	}
	
	public static void warn(String message) {
		log(1, message);
	}
	
	public static void warning(String message) {
		warn(message);
	}
	
	public static void severe(String message) {
		log(2, message);
	}


}
