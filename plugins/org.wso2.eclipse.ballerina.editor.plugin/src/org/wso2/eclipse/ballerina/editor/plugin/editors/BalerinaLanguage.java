package org.wso2.eclipse.ballerina.editor.plugin.editors;

import java.util.HashMap;
import java.util.Properties;
import java.util.Set;

public class BalerinaLanguage {

	static String[] keyWords;
	static HashMap<String, String[]> libraries;
	private static BalerinaLanguage instance = new BalerinaLanguage();

	private BalerinaLanguage() {
		libraries = new HashMap<String, String[]>();
	}

	public static BalerinaLanguage getInstance() {
		return instance;
	}

	public void init(Properties prop) {
		Set<Object> keys = prop.keySet();
		for (Object k : keys) {
			String key = (String) k;
			if ("keywords".equals(key)) {
				String propValue = prop.getProperty(key);
				String[] propArray = propValue.replace("[", "").replace("]", "").split(",");
				keyWords = propArray;
			} else {
				String propValue = prop.getProperty(key);
				String[] propArray = propValue.replace("[", "").replace("]", "").split(",");
				libraries.put(key, propArray);
			}
		}
	}

	public String[] getKeyWords() {
		return keyWords;
	}

	public HashMap<String, String[]> getLibraries() {
		return libraries;
	}

	public String[] getProposals(String key) {
		if (libraries.containsKey(key)) {
			return libraries.get(key);
		} else {
			return null;
		}
	}

}
