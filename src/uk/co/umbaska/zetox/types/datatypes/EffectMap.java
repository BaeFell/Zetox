package uk.co.umbaska.zetox.types.datatypes;

import java.util.List;

public class EffectMap {

	protected List<Effect> effects;
	
	public List<Effect> getEffectsList() {
		return effects;
	}
	
	public Effect registerExpression(String syntax, Class<?> clazz) {
		Effect exp = new Effect(syntax, clazz);
		if(effects.contains(exp)) {
			return null;
		} else {
			effects.add(exp);
			return exp;
		}
	}
	
	protected Boolean unregisterExpression(Expression exp) {
		if(effects.contains(exp)) {
			effects.remove(exp);
			return true;
		} else {
			return false;
		}
	}

}
