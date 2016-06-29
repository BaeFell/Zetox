package uk.co.umbaska.zetox.expressions;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import uk.co.umbaska.zetox.managers.ScriptParser;

@SuppressWarnings("rawtypes")
public class ExprUUIDofPlayer {
	
	public Class getType() {
		return UUID.class;
	}
	
	public UUID init() {
		List<Method> gMethods = Arrays.asList(ScriptParser.currentEvent.getClass().getMethods());
		if(gMethods.contains("getPlayer")) {
			Class<?> cc = ScriptParser.currentEvent.getClass();
			Object t = null;
			try {
				t = cc.newInstance();
			} catch (InstantiationException e) {
				e.printStackTrace();
			} catch (IllegalAccessException e) {
				e.printStackTrace();
			}
			if(t == null) {
				return null;
			}
			Method method = null;
			try {
				method = cc.getMethod("getPlayer");					
			} catch (NoSuchMethodException e1) {
				Bukkit.getLogger().warning("[Zetox] Method run doesn't exist for: " + cc);
			} catch (SecurityException e1) {
				e1.printStackTrace();
			} catch (NullPointerException e1) {
				e1.printStackTrace();
			}
			Player p = null;
			try {
				method.setAccessible(true);
				p = (Player) (method.invoke(t));
			} catch (IllegalAccessException e1) {
				e1.printStackTrace();
			} catch (IllegalArgumentException e1) {
				e1.printStackTrace();
			} catch (InvocationTargetException e1) {
				e1.printStackTrace();
			}
			if(p == null) {
				return null;
			} else {
				return p.getUniqueId();
			}
			
		}
		return null;
	}

}
