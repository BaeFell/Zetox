package uk.co.umbaska.zetox;

import uk.co.umbaska.zetox.parser.VariableManager;
import uk.co.umbaska.zetox.types.EffectMap;
import uk.co.umbaska.zetox.types.ExpressionMap;

public class ZetoxAPI {

	private EffectMap effectMap;
	private ExpressionMap expressionMap;
	private VariableManager variableManager;
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


}
