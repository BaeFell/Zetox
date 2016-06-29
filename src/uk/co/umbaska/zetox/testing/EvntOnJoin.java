package uk.co.umbaska.zetox.testing;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerLoginEvent;

import uk.co.umbaska.zetox.managers.ScriptParser;

public class EvntOnJoin implements Listener {

	public static final EvntOnJoin Listener = new EvntOnJoin();
	
	@EventHandler
    public void onPlayerLogin(PlayerLoginEvent event) {
		ScriptParser sp = new ScriptParser();
        sp.run("on player join:", (PlayerLoginEvent) event, event.getPlayer());
    }
	
}
