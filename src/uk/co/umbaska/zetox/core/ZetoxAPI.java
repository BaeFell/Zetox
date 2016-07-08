package uk.co.umbaska.zetox.core;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import uk.co.umbaska.zetox.types.datatypes.ArgType;
import uk.co.umbaska.zetox.types.datatypes.Effect;
import uk.co.umbaska.zetox.types.datatypes.EffectMap;
import uk.co.umbaska.zetox.types.datatypes.Expression;
import uk.co.umbaska.zetox.types.datatypes.ExpressionMap;
import uk.co.umbaska.zetox.types.datatypes.Script;
//import uk.co.umbaska.zetox.types.datatypes.ZetoxAddon;

public class ZetoxAPI {

	private ExpressionMap expressionMap = new ExpressionMap();
	private EffectMap effectMap = new EffectMap();
	//private List<ZetoxAddon> zetoxAddons;
	private List<Script> scripts;
	private List<ArgType> argTypes;
	
	/*public ZetoxAPI(Plugin p) {
		if(!zetoxAddons.contains(new ZetoxAddon(p))) {
			zetoxAddons.add(new ZetoxAddon(p));
		}
	}*/
	
	public ArgType registerArgType(String name, Integer args, Class<?> clazz) {
		ArgType at = new ArgType(name, args, clazz);
		if(!argTypes.contains(at)) {
			argTypes.add(at);
		}
		return at;
	}
	
	public ArgType getArgTypeByName(String name) {
		for(ArgType at : argTypes) {
			if(at.getName() == name) {
				return at;
			}
		}
		return null;
	}
	
	public Effect registerEffect(String syntax, Class<?> clazz) {
		return effectMap.registerExpression(syntax, clazz);
	}
	
	public Expression registerExpression(String syntax, Class<?> clazz) {
		return expressionMap.registerExpression(syntax, clazz);
	}
	
	public List<Object> getAllRegisteredContent() {
		List<Object> out = new ArrayList<Object>();
		for(Expression exp : expressionMap.getExpressionsList()) {
			out.add(exp);
		}
		for(Effect eff : effectMap.getEffectsList()) {
			out.add(eff);
		}
		if(out == null || out.size() == 0) {
			return null;
		}
		return out;
	}
	
	public Script registerScript(File f) {
		Script s = new Script(f);
		if(!scripts.contains(s)) {
			scripts.add(s);
		}
		return s;
	}
	
	public List<Script> getScripts() {
		if(scripts == null || scripts.size() == 0) {
			return null;
		} else {
			return scripts;
		}
	}

}

