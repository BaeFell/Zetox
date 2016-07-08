package uk.co.umbaska.zetox.types.datatypes;

public class Expression {

	private String expressionSyntax;
	private Class<?> expressionClass;
	public Expression(String syntax, Class<?> clazz) {
		expressionSyntax = syntax;
		expressionClass = clazz;
	}
	
	public String getSyntax() {
		return expressionSyntax;
	}
	
	public Class<?> getRawClass() {
		return expressionClass;
	}

}
