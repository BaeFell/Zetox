package uk.co.umbaska.zetox.managers;

import java.util.HashMap;
import java.util.List;

import org.bukkit.event.Listener;
import org.bukkit.plugin.Plugin;

import uk.co.umbaska.zetox.Zetox;

@SuppressWarnings("rawtypes")
public class ZetoxAPI {
	
	public static List<Plugin> addonList;
	public static HashMap<String, Class> effectMap = new HashMap<String, Class>();
	public static HashMap<String, Listener> eventMap = new HashMap<String, Listener>();
	public static HashMap<String, Class> expressionMap = new HashMap<String, Class>();

	
	public void registerPlugin(Plugin p) {
		if(!addonList.contains(p)) {
			addonList.add(p);
		}
	}
	
	public void registerEffect(String s, Class c) {
		if(!effectMap.containsKey(s)) {
			effectMap.put(s, c);
		}
	}
	
	public void registerEvent(String s, Listener c) {
		if(!eventMap.containsKey(s)) {
			eventMap.put(s, c);
			Zetox.registerEvent(s);
		}
	}
	
	public void registerExpression(String s, Class c) {
		if(!expressionMap.containsKey(s)) {
			expressionMap.put(s, c);
		}
	}

}
