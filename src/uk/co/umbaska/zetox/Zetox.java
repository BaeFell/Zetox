package uk.co.umbaska.zetox;

import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

import uk.co.umbaska.zetox.core.ZetoxAPI;
import uk.co.umbaska.zetox.types.enums.OutputLevel.OutputLevels;

public class Zetox extends JavaPlugin {

	public static Boolean debugMode = true;
	private static ZetoxAPI zta;
	
	public void onEnable() {
		zta = new ZetoxAPI();
	}
	
	public ZetoxAPI getZetoxAPI() {
		return zta;
	}
	
	public static void output(OutputLevels ol, String message) {
		if(ol == OutputLevels.LOG) {
			Bukkit.getLogger().info("[Zetox][Info] " + message);
		} else if(ol == OutputLevels.WARN) {
			Bukkit.getLogger().warning("[Zetox][Warn] " + message);
		} else if(ol == OutputLevels.SEVERE) {
			Bukkit.getLogger().severe("[Zetox][Severe] " + message);
		} else if(ol == OutputLevels.DEBUG) {
			Bukkit.getLogger().info("[Zetox][Debug] " + message);
		}
	}

}
