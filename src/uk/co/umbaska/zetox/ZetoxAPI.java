package uk.co.umbaska.zetox;

import java.util.ArrayList;
import java.util.List;

import uk.co.umbaska.zetox.parser.VariableManager;
import uk.co.umbaska.zetox.types.EffectMap;
import uk.co.umbaska.zetox.types.ExpressionMap;
import uk.co.umbaska.zetox.types.ArgType;

public class ZetoxAPI {

	private EffectMap effectMap;
	private ExpressionMap expressionMap;
	private VariableManager variableManager;
	
	private List<ArgType> argTypes = new ArrayList<ArgType>();
	public ZetoxAPI() {
		effectMap = new EffectMap();
		expressionMap = new ExpressionMap();
		variableManager = new VariableManager();
	}
	
	public EffectMap getEffectMap() {
		return effectMap; 
	}
	
	public ExpressionMap getExpressionMap() {
		return expressionMap;
	}
	
	public VariableManager getVariableManager() {
		return variableManager;
	}

	public void registerArgType(ArgType type) {
		if(argTypes.contains(type)) {
			return;
		} else {
			argTypes.add(type);
		}
	}
	
	public void registerArgType(String name, String syntax, Integer args, Class<?> clazz) {
		ArgType arg = new ArgType(name, syntax, args, clazz);
		registerArgType(arg);
	}

}
