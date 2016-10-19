package uk.co.umbaska.zetox.types;

import uk.co.umbaska.zetox.Zetox;
import uk.co.umbaska.zetox.ZetoxAPI;

public class Variable {

	private String name;
	private Object value;
	public Variable(String name) {
		this.name = name;
	}
	
	public Variable(String name, Object value) {
		this.name = name;
		this.value = value;
	}
	
	public void setValue(Object value) {
		this.value = value;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public Object getValue() {
		return value;
	}
	
	public String getName() {
		return name;
	}
	
	public void declare() {
		if(name == null) {
			return;
		}
		Zetox.ZetoxAPI.getVariableManager().registerVariable(this);
	}
}
