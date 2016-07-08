package uk.co.umbaska.zetox.types.datatypes;

public class Effect {

	private String effectSyntax;
	private Class<?> effectClass;
	public Effect(String syntax, Class<?> clazz) {
		effectSyntax = syntax;
		effectClass = clazz;
	}

	public String getSyntax() {
		return effectSyntax;
	}
	
	public Class<?> getRawClass() {
		return effectClass;
	}

}