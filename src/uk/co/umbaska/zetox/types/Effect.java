package uk.co.umbaska.zetox.types;

public class Effect {

	private String effectSyntax;
	private String lowerCaseSyntax;
	private Class<?> effectClass;
	public Effect(String syntax, Class<?> clazz) {
		effectSyntax = syntax;
		effectClass = clazz;
		lowerCaseSyntax = effectSyntax.toLowerCase();
	}

	public String getSyntax() {
		return effectSyntax;
	}
	
	public Class<?> getRawClass() {
		return effectClass;
	}
	
	public String getLowerCaseSyntax() {
		return lowerCaseSyntax;
	}

}