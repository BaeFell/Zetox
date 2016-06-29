package uk.co.umbaska.zetox.managers;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;

import org.apache.commons.lang.StringUtils;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;

import uk.co.umbaska.zetox.types.Script;
import uk.co.umbaska.zetox.types.enums.SyntaxEnum.SyntaxType;

public class ScriptParser {
	
	public static Event currentEvent;
	
	public void run(String s, Event event, Player p) {
		currentEvent = event;
		for(Script script : ScriptManager.scriptList.values()) {
			File f = script.getFile();
			String line = null;
	        try {
	            FileReader fileReader = new FileReader(f);
	            BufferedReader bufferedReader = new BufferedReader(fileReader);
	            Boolean inMode = false;
	            Integer lineNumber = 0;
	            while((line = bufferedReader.readLine()) != null) {
	            	++lineNumber;
	            	if(line.equals("")) {
	            		inMode = false;
	            	} else if(line.contains(s)) {
	                	inMode = true;
	                } else if(inMode == true) { 
	                	String parsedLine = line.replaceAll("\"([^\"]*)\"", "%string%");
	                	parsedLine = parseString(SyntaxType.EXPRESSION, parsedLine);
	                	for(String element : ZetoxAPI.effectMap.keySet()) {
	                		if(parsedLine.contains(element)) {
	                			String[] lineBreakdown = line.split(" ");
		                		Integer inType = 0;
		                		ArrayList<String> args = new ArrayList<>();
		                		ArrayList<String> exp = new ArrayList<>();
		                		HashMap<Integer, String> allArgs = new HashMap<Integer, String>();
		                		for(String lb : lineBreakdown) {
		                			/* inType settings:
		                			 * 0 - unset
		                			 * D1 - string
		                			 * D2 - long comment
		                			 * 3 - variable ({...})
		                			 * D4 - expression (%...%)
		                			 */
		                			
		                			if(lb.contains("\"")) {
		                				if(inType == 1) {
		                					inType = 0;
		                					lb = lb.replaceAll("\"", parseString(SyntaxType.EXPRESSION, lb));
		                					args.add(lb);
		                					String argsJoined = StringUtils.join(args.toArray()," ");
				                			allArgs.put(allArgs.size(), argsJoined);
		                				} else {
			                				Integer numOfMatches = StringUtils.countMatches(lb, "%");
			                				if(numOfMatches == 1) {
			                					inType = 4;
			                					exp.clear();
			                				} else { 
			                					lb = lb.replaceAll("\"", parseString(SyntaxType.EXPRESSION, lb));
			                					args.add(lb);
			                					String argsJoined = StringUtils.join(args.toArray()," ");
					                			allArgs.put(allArgs.size(), argsJoined);
			                				}
		                				}
		                			} else if(inType == 1) {
		                				args.add(lb);
		                			} else if(lb.contains("##")) {
		                				if(inType == 2) {
		                					inType = 0;
		                				} else {
		                					inType = 2;
		                				}
		                			} else if (lb.contains("%")) {
		                				if(inType == 4) {
		                					inType = 0;
		                					lb = lb.replaceAll("%", parseString(SyntaxType.EXPRESSION, lb));
		                					args.add(lb);
		                					String argsJoined = StringUtils.join(args.toArray()," ");
				                			allArgs.put(allArgs.size(), argsJoined);
		                				} else {
			                				Integer numOfMatches = StringUtils.countMatches(lb, "%");
			                				if(numOfMatches == 1) {
			                					inType = 4;
			                					exp.clear();
			                				} else { 
			                					lb = lb.replaceAll("%", parseString(SyntaxType.EXPRESSION, lb));
			                					args.add(lb);
			                					String argsJoined = StringUtils.join(args.toArray()," ");
					                			allArgs.put(allArgs.size(), argsJoined);
			                				}
		                				}
		                			} else if(inType == 4) {
		                				exp.add(lb);
		                			} else {
		                				inType = 0;
		                			}
		                		}		                		
		                		
	                			Class<?> cc = ZetoxAPI.effectMap.get(element);
	                			Object t = null;
								try {
									t = cc.newInstance();
								} catch (InstantiationException e) {
									e.printStackTrace();
								} catch (IllegalAccessException e) {
									e.printStackTrace();
								}
								if(t == null) {
									return;
								}
	                			Method method = null;
								try {
									if(allArgs.size() == 0) {
										method = cc.getMethod("init");
	                				} else {
	                					method = cc.getMethod("init", HashMap.class);
	                				}
									
								} catch (NoSuchMethodException e1) {
									Bukkit.getLogger().warning("[Zetox] Method run doesn't exist for: " + cc);
								} catch (SecurityException e1) {
									e1.printStackTrace();
								} catch (NullPointerException e1) {
									e1.printStackTrace();
								}
	                			try {
	                				method.setAccessible(true);
	                				if(allArgs.size() == 0) {
	                					method.invoke(t, (Object) null);
	                				} else {
	                					method.invoke(t, allArgs);
	                				}
								} catch (IllegalAccessException e1) {
									e1.printStackTrace();
								} catch (IllegalArgumentException e1) {
									e1.printStackTrace();
								} catch (InvocationTargetException e1) {
									e1.printStackTrace();
								}
	                		}
	                	}
	                }
	            }
	            bufferedReader.close();         
	        } catch(FileNotFoundException ex) { 
	        	System.out.println("Unable to open file '" + f + "'");                
	        } catch(IOException ex) {
	            System.out.println("Error reading file '" + f + "'");
	        }
		}
		currentEvent = null;
	}
	
	@SuppressWarnings("rawtypes")
	public String parseString(SyntaxType st, String line) {
		if(st == SyntaxType.EXPRESSION) {
			for(String expression : ZetoxAPI.expressionMap.keySet()) {
				Class<?> cc = ZetoxAPI.expressionMap.get(expression);
    			Object t = null;
				try {
					t = cc.newInstance();
				} catch (InstantiationException e) {
					e.printStackTrace();
				} catch (IllegalAccessException e) {
					e.printStackTrace();
				}
				if(t == null) {
					return null;
				}
    			Method method = null;
				try {
					method = cc.getMethod("init");					
				} catch (NoSuchMethodException e1) {
					Bukkit.getLogger().warning("[Zetox] Method run doesn't exist for: " + cc);
				} catch (SecurityException e1) {
					e1.printStackTrace();
				} catch (NullPointerException e1) {
					e1.printStackTrace();
				}
				Class rType = getReturnType(cc);
				String rO = null;
    			try {
    				method.setAccessible(true);
    				rO = rType.cast(method.invoke(t)).toString();
				} catch (IllegalAccessException e1) {
					e1.printStackTrace();
				} catch (IllegalArgumentException e1) {
					e1.printStackTrace();
				} catch (InvocationTargetException e1) {
					e1.printStackTrace();
				}
				line = line.replaceAll("%" + expression + "%", rO);
				
				/* 
				 * player
				 * uuid of player
				 
				List<String> expBreakdown = Arrays.asList(line.split(" "));
				for(String lb : lineBreakdown) {
					if(expBreakdown.contains(lb)) {
						
					}
				}
				*/
				
			}
			return line;
		}
		return null;
	}
	
	@SuppressWarnings("rawtypes")
	public Class getReturnType(Class c) {
		Class<?> cc = c;
		Object t = null;
		try {
			t = cc.newInstance();
		} catch (InstantiationException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		}
		if(t == null) {
			return null;
		}
		Method method = null;
		try {
			method = cc.getMethod("getType");					
		} catch (NoSuchMethodException e1) {
			Bukkit.getLogger().warning("[Zetox] Method run doesn't exist for: " + cc);
		} catch (SecurityException e1) {
			e1.printStackTrace();
		} catch (NullPointerException e1) {
			e1.printStackTrace();
		}
		
		try {
			method.setAccessible(true);
			return (Class) method.invoke(t);
		} catch (IllegalAccessException e1) {
			e1.printStackTrace();
		} catch (IllegalArgumentException e1) {
			e1.printStackTrace();
		} catch (InvocationTargetException e1) {
			e1.printStackTrace();
		}
		return c;
		
	}
	
}
