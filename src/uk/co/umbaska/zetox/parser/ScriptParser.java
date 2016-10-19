package uk.co.umbaska.zetox.parser;

import uk.co.umbaska.zetox.Zetox;
import uk.co.umbaska.zetox.ZetoxAPI;
import uk.co.umbaska.zetox.types.Arguments;
import uk.co.umbaska.zetox.types.Effect;
import uk.co.umbaska.zetox.types.Script;

public class ScriptParser {

	@SuppressWarnings("unused")
	public void parseScript(Script script) {
		for(String line : script.getContent()) {
			ZetoxAPI ZetoxAPI = Zetox.ZetoxAPI;
			Arguments args = new Arguments();
			
			Boolean inVariable = false;
			String currentVariableString = null;
			Object currentVariable = null;
			Boolean inComment = false;
			String[] lineSplit = line.split(" ");
			
			for(String lineS : lineSplit) {
				if(lineS.startsWith("{")) {
					if(lineS.endsWith("}")) {
						currentVariableString = lineS.replace("{", "").replace("}", "");
						currentVariable = ZetoxAPI.getVariableManager().getVariable(currentVariableString);
						args.addArgument(currentVariable);
						line = line.replace(currentVariableString, "%variable%");
					} else {
						inVariable = true;
						currentVariableString = lineS.replace("{", "");
					}
				} else if(inVariable) {
					if(lineS.endsWith("}")) {
						inVariable = false;
						currentVariableString = currentVariable + " " + lineS.replace("}", "");
						currentVariable = ZetoxAPI.getVariableManager().getVariable(currentVariableString);
						args.addArgument(currentVariable);
						line = line.replace(currentVariableString, "%variable%");
					} else {
						currentVariableString = currentVariable + " " + lineS;
					}
				}
			}
			String lowerCaseLine = line.toLowerCase();
			for(Effect e : ZetoxAPI.getEffectMap().getEffectsList()) {
				String effectLowerCase = e.getLowerCaseSyntax();
				
				String[] lcSplit = lowerCaseLine.split(" ");
				String[] efSplit = effectLowerCase.split(" ");
				Integer length = efSplit.length;
				Integer lenMet = 0;
				for(int i = 0; i < length; i++) {
					if(efSplit[i].equalsIgnoreCase(lcSplit[i])) {
						lenMet++;
					} else if(lcSplit[i] == "%variable%") {
						if(efSplit[i].startsWith("%~")){
							break;
						} else if(efSplit[i].startsWith("%") && efSplit[i].endsWith("%")) {
							lenMet++;
						}
					}
				}
				
			}
		}
	}
	
	

}
