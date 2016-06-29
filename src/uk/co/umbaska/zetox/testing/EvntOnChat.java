package uk.co.umbaska.zetox.testing;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

import uk.co.umbaska.zetox.managers.ScriptParser;

public class EvntOnChat implements Listener {
	
	public static final EvntOnChat Listener = new EvntOnChat();
	
	@EventHandler()
    public void onPlayerChat(AsyncPlayerChatEvent event) {
		ScriptParser sp = new ScriptParser();
        sp.run("on chat:", (AsyncPlayerChatEvent) event, event.getPlayer());
    }
}
