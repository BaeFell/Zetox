package uk.co.umbaska.zetox.types;

public class Expression {

	private String expressionSyntax;
	private String lowerCaseSyntax;
	private Class<?> expressionClass;
	public Expression(String syntax, Class<?> clazz) {
		expressionSyntax = syntax;
		expressionClass = clazz;
		lowerCaseSyntax = expressionSyntax.toLowerCase();
	}
	
	public String getSyntax() {
		return expressionSyntax;
	}
	
	public Class<?> getRawClass() {
		return expressionClass;
	}
	
	public String getLowerCaseSyntax() {
		return lowerCaseSyntax;
	}

}
