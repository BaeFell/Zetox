package uk.co.umbaska.zetox.parser;

import java.util.ArrayList;
import java.util.List;

import uk.co.umbaska.zetox.types.Variable;

public class VariableManager {
	
	private List<Variable> variables = new ArrayList<Variable>();

	public VariableManager() {
		loadVariables();
	}
	
	public void loadVariables() {
		// do something related to adding to variables
	}
	
	public void registerVariable(Variable v) {
		if(v.getName() == null) {
			return;
		}
		
		if(variables.contains(v)) {
			return;
		}
		
		variables.add(v);
	}
	
	public Object getVariable(String name) { 
		Variable v = new Variable(name);
		for(Variable tV : variables) {
			if(tV.getName() == v.getName() || tV.getName().equalsIgnoreCase(v.getName())) {
				return v;
			}
		}
		v = new Variable("<none>", "<none>");
		v.declare();
		return v;
	}
}
