package uk.co.umbaska.zetox.managers;

import java.util.ArrayList;
import java.util.List;

public class FileTypes {
	
	public static List<String> fileTypes = new ArrayList<>();
	
	public Boolean isType(String type) {
		if(fileTypes.contains(type)) {
			return true;
		} else {
			return false;
		}
	}
	
	public static void addFileType(String type) {
		if(fileTypes == null) {
			fileTypes.set(0, ".zx");
		} else {
			fileTypes.add(type);
		}
	}
	
	public static void addFileTypes(String... types) {
		for(String type : types) {
			addFileType(type);
		}
	}
	
	public static void addFileTypes(List<String> types) {
		for(String type : types) {
			addFileType(type);
		}
	}
	
	public static List<String> getFileTypes() {
		return fileTypes;
	}

}
