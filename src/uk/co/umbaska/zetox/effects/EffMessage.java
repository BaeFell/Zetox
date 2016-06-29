package uk.co.umbaska.zetox.effects;

import java.util.HashMap;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

public class EffMessage {
	
	private String msg;
	private Player p;
	
	public void init(HashMap<Integer, String> args) {
		msg = args.get(0);
		p = Bukkit.getPlayer(args.get(1));
		if(p != null) {
			p.sendMessage(msg);
		}
	}
}
