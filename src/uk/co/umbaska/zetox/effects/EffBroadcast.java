package uk.co.umbaska.zetox.effects;

import java.util.HashMap;

import org.bukkit.Bukkit;

public class EffBroadcast {
	
	private String msg;
	
	public void init(HashMap<Integer, String> args) {
		msg = args.get(0);
		run(msg);
	}

	
	public void run(String message) {
		Bukkit.broadcastMessage(message);
	}

}
