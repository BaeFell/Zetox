package uk.co.umbaska.zetox.core;

import java.io.File;

import org.bukkit.event.Event;

import uk.co.umbaska.zetox.Zetox;
import uk.co.umbaska.zetox.types.datatypes.ArgType;
import uk.co.umbaska.zetox.types.datatypes.Arguments;
import uk.co.umbaska.zetox.types.datatypes.Effect;
import uk.co.umbaska.zetox.types.datatypes.Expression;
import uk.co.umbaska.zetox.types.datatypes.Script;

public class ScriptParser {

	public void run(Event e) {
		Zetox zt = new Zetox();
		ZetoxAPI zta = zt.getZetoxAPI();
		for(Script script : zta.getScripts()) {
			for(String line : script.getContent()) { // using getContent() so there are no comments or blanks
				Arguments args = new Arguments();
				for(Object obj : zta.getAllRegisteredContent()) {
					String[] lineSplit = line.split(" ");
					String syntax = getSyntaxFromObject(obj);
					if(syntax == null) { 
						continue;
					}
					String[] syntaxSplit = syntax.split(" ");
					Integer arrayIndex = 0;
					for(String syn : syntaxSplit) {
						if(syn.startsWith("%") || syn.endsWith("%")) {
							if(syn.startsWith("%{") || syn.endsWith("}%")) {
								break;
								//TODO: Variables! :D
							}
							String psyn = syn.replace("%", "");
							ArgType at = zta.getArgTypeByName(psyn);
							if(at == null) {
								break;
							}
							Integer toLoop = at.getArgs();
							if(toLoop == 1) {
								if(at.processString(lineSplit[arrayIndex])) {
									args.addArgument(lineSplit[arrayIndex]);
								} else {
									break;
								}
							} else {
								String toBuild = null;
								for(int i = arrayIndex; i < toLoop; i++) {
									toBuild = toBuild + lineSplit[i];
								}
								if(at.processString(toBuild)) {
									args.addArgument(toBuild);
								} else {
									break;
								}
							}
						}
						
						arrayIndex++;
					}
				}
			}
		}
	}
	
	public String getSyntaxFromObject(Object obj) {
		if(obj instanceof Effect) {
			Effect eff = (Effect) obj;
			return eff.getSyntax();
		} else if (obj instanceof Expression) {
			Expression exp = (Expression) obj;
			return exp.getSyntax();
		}
		return null;
	}
	
	public void parseScript(File f) {
		
	}

}
