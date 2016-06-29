package uk.co.umbaska.zetox.expressions;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import uk.co.umbaska.zetox.managers.ScriptParser;

@SuppressWarnings("rawtypes")
public class ExprPlayer {
	
	public Class getType() {
		return Player.class;
	}
	
	public Player init() {
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
			try {
				method.setAccessible(true);
				return (Player) (method.invoke(t));
			} catch (IllegalAccessException e1) {
				e1.printStackTrace();
			} catch (IllegalArgumentException e1) {
				e1.printStackTrace();
			} catch (InvocationTargetException e1) {
				e1.printStackTrace();
			}
		}
		return null;
	}

}
