package uk.co.umbaska.zetox.types;

@SuppressWarnings({"rawtypes"})
public class Effect {

	private String syntax;
	private Class cl;
	private String description;
	
	public Effect(String s, Class c) {
		this.syntax = s;
		this.cl = c;
	}
	
	public void setDescription(String d) {
		this.description = d;
	}
	
	public String getDescription() {
		if(this.description != null) {
			return this.description;
		} else {
			return "no description set";
		}
	}
		
	public String getSyntax() {
		return this.syntax;
	}
	
	public Class getCl() {
		return this.cl;
	}
	
}
