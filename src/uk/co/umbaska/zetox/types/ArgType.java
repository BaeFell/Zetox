package uk.co.umbaska.zetox.types;

import java.lang.reflect.InvocationTargetException;

public class ArgType {

	
	protected String name;
	protected String syntax;
	protected Integer args;
	protected Class<?> clazz;
	
	public ArgType(String name, String syntax, Integer args, Class<?> clazz) {
		this.name = name;
		this.syntax = syntax;
		this.args = args;
		this.clazz = clazz;
	}
	
	public String getName() {
		return name;
	}
	
	public Integer getArgs() {
		return args;
	}
	
	public String getSyntax() {
		return syntax;
	}
	
	public Boolean processString(String input) {
		Class<?> c = clazz;
		try {
			Object o = c.getDeclaredMethod("parse", String.class).invoke(input);
			if(o instanceof Boolean) {
				return (Boolean) o;
			}
		} catch (IllegalAccessException | IllegalArgumentException
				| InvocationTargetException | NoSuchMethodException
				| SecurityException e) {
			e.printStackTrace();
		}
		return false;
	}

}
