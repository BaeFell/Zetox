package uk.co.umbaska.zetox.types.datatypes;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

import uk.co.umbaska.zetox.Zetox;
import uk.co.umbaska.zetox.types.enums.OutputLevel.OutputLevels;

public class Script {

	protected File file;
	protected String name;
	protected String path;
	protected Double version;
	protected String author;
	protected String[] rawcontent;
	protected String[] content;
	
	public Script(File f) {
		file = f;
		name = f.getName();
		try {
			path = f.getCanonicalPath();
		} catch (IOException e) {
			Zetox.output(OutputLevels.SEVERE, "Failed to get canonical path for: " + name + " (IOException)");
			path = "unknown path (IOException)";
		}
		fetchFileInformation();
		if(Zetox.debugMode) {
			Zetox.output(OutputLevels.DEBUG, "Generated script object:\n"
					+ "Name: " + name
					+ "\nPath: " + path
					+ "\nVersion: " + version
					+ "\nAuthor: " + author);
		}
	}
	
	public String[] getContent() {
		return content;
	}
	
	public String[] getRawContent() {
		return rawcontent;
	}

	private void fetchFileInformation() {
		if(file == null) {
			version = 0.0;
		} else {
			try {
				String line = null;
				Integer ln = 0;
				Boolean inComments = false;
	            FileReader fileReader;
				fileReader = new FileReader(file);
				BufferedReader bufferedReader = new BufferedReader(fileReader);
	            while((line = bufferedReader.readLine()) != null) {
	            	if(line.startsWith("# version: ")) {
	            		String[] lineSplit = line.split(" ");
	            		Double v = 0.0;
	            		if(lineSplit[2] == null) {
	            			Zetox.output(OutputLevels.SEVERE, "Failed to fetch version for: " + name + " (null at split point 2)");
	            		}
	            		try {
	            			v = Double.valueOf(lineSplit[2]);
	            		} catch(Exception e) {
	            			Zetox.output(OutputLevels.SEVERE, "Failed to get Double in: " + name + ", using String: " + lineSplit[2]);
	            		}
	            		version = v;
	            	} else if(line.startsWith("# author: ")) {
	            		String[] lineSplit = line.split(" ");
	            		String a = "unknown author";
	            		if(lineSplit[2] == null) {
	            			Zetox.output(OutputLevels.SEVERE, "Failed to fetch author for: " + name + " (null at split point 2)");
	            		}
	            		if(lineSplit[2].startsWith("\"")) {
	            			for(int i = 2; i < lineSplit.length; i++) {
	            				if(i == 2) {
	            					a = lineSplit[2].replace("\"", "");
	            				} else {
	            					a = a + ", " + lineSplit[i];
	            				}
	            			}
	            		} else {
	            			a = lineSplit[2];
	            		}
	            		author = a;
	            	}
	            	rawcontent[ln] = line;
	            	if(inComments) {
	            		if(line.endsWith("*/") || line.endsWith("##")) {
	            			inComments = false;
	            		}
	            		continue;
	            	} else if(line.startsWith("#") || line.startsWith("//")) {
	            		continue;
	            	} else if(line.startsWith("/*") || line.startsWith("##")) {
	            		inComments = true;
	            		continue;
	            	}
	            	content[ln] = line;
	            }
	            bufferedReader.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

}
