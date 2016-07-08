package uk.co.umbaska.zetox.types.datatypes;

import java.util.List;

public class ExpressionMap {

	protected List<Expression> expressions;
	
	public List<Expression> getExpressionsList() {
		return expressions;
	}
	
	public Expression registerExpression(String syntax, Class<?> clazz) {
		Expression exp = new Expression(syntax, clazz);
		if(expressions.contains(exp)) {
			return null;
		} else {
			expressions.add(exp);
			return exp;
		}
	}
	
	protected Boolean unregisterExpression(Expression exp) {
		if(expressions.contains(exp)) {
			expressions.remove(exp);
			return true;
		} else {
			return false;
		}
	}

}
