package uk.co.umbaska.zetox.types.datatypes;

import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.PluginDescriptionFile;

public class ZetoxAddon {

	protected Plugin plugin;
	protected String name;
	protected String main;
	protected String description;
	protected String version;
	protected String[] authors;
	protected PluginDescriptionFile dfile;
	
	public ZetoxAddon(Plugin p) {
		plugin = p;
		dfile = p.getDescription();
		name = dfile.getFullName();
		main = dfile.getMain();
		description = dfile.getDescription();
		version = dfile.getVersion();
		authors = (String[]) dfile.getAuthors().toArray();
	}

}
