package uk.co.umbaska.zetox.types.datatypes;

import java.util.ArrayList;

public class Arguments {

	private ArrayList<Object> args = new ArrayList<Object>();
	private Object[] argsArray;
	
	public Arguments() {
		
	}
	
	public Arguments(ArrayList<Object> arg) {
		args = arg;
		argsArray = args.toArray();
	}
	
	public void addArgument(Object o) {
		args.add(o);
		setArgsArray();
	}
	
	private void setArgsArray() {
		if(argsArray == null || argsArray.length == 0) {
			if(args == null || args.size() == 0) {
				return;
			} else {
				argsArray = args.toArray();
			}
		} else {
			return;
		}
	}
	
	public Object getSingle(Integer i) {
		if(argsArray[i] != null) {
			return argsArray[i];
		} else {
			return null;
		}
	}
	
	public Object[] getAll() {
		return argsArray;
	}

}
