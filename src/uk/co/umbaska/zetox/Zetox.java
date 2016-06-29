package uk.co.umbaska.zetox;

import java.io.File;

import org.bukkit.Bukkit;
import org.bukkit.event.Event;
import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;

import uk.co.umbaska.zetox.effects.EffBroadcast;
import uk.co.umbaska.zetox.effects.EffMessage;
import uk.co.umbaska.zetox.expressions.ExprPlayer;
import uk.co.umbaska.zetox.expressions.ExprUUIDofPlayer;
import uk.co.umbaska.zetox.managers.FileTypes;
import uk.co.umbaska.zetox.managers.ScriptManager;
import uk.co.umbaska.zetox.managers.ScriptParser;
import uk.co.umbaska.zetox.managers.ZetoxAPI;
import uk.co.umbaska.zetox.testing.EvntOnChat;
import uk.co.umbaska.zetox.testing.EvntOnJoin;

public class Zetox extends JavaPlugin {
	
	public static Boolean errorMessages = false;
	public static Zetox plugin;

	public void onEnable() {
		plugin = this;
		Bukkit.getLogger().info("[Zetox] Loading up...");
		setConfigValues();
		FileTypes.addFileTypes(getConfig().getStringList("file types"));
		Bukkit.broadcastMessage("[Zetox] Registered file types: " + FileTypes.getFileTypes());
		Bukkit.getLogger().info("[Zetox] Loading scripts");

		ScriptManager scm = new ScriptManager();
		scm.loadScripts(new File(getDataFolder() + "/scripts/"));
		
		ZetoxAPI zta = new ZetoxAPI();
		//FileTypes.addFileTypes(new String[] {".zx", ".zt", ".ze", ".virus", ".bae", ".dark", ".baefell", ".bya", ".illuminati", ".wrong", ".zetox", ".kevin", ".js", ".sk", ".denizen"});
		
		
		zta.registerEvent("PlayerLoginEvent", EvntOnJoin.Listener);
		zta.registerEvent("OnChat", EvntOnChat.Listener);
		zta.registerEffect("broadcast %string%", EffBroadcast.class);
		zta.registerEffect("message %string% to %player%", EffMessage.class);
		zta.registerExpression("player", ExprPlayer.class);
		zta.registerExpression("uuid of player", ExprUUIDofPlayer.class);
	}

	
	private void setConfigValues() {
		saveDefaultConfig();
		if(getConfig().getBoolean("settings.error messages" )) {
			errorMessages = true;
		}		
	}
	
	public static Zetox getInstance() {
		return plugin;
	}
	
	public static Event getCurrentEvent() {
		return ScriptParser.currentEvent;
	}
	
	public static void registerEvent(String s) {
		if(ZetoxAPI.eventMap.containsKey(s)) {
			Listener l = ZetoxAPI.eventMap.get(s);
			Bukkit.getPluginManager().registerEvents(l, plugin);
		}
	}
}


