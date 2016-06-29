package uk.co.umbaska.zetox.types;

import java.io.File;

public class Script {

	private File file;
	private Boolean loaded;
	private String type;
	
	public Script(File f) {
		this.file = f;
	}
	
	public void setLoaded(Boolean l) {
		this.loaded = l;
	}
	
	public void setType(String t) {
		this.type = t;
	}
	
	public Boolean isLoaded() {
		return this.loaded;
	}
	
	public File getFile() {
		return this.file;
	}
	
	public String getType() {
		return this.type;
	}
	
}
