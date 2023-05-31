package Default;

import java.util.HashMap;
import java.util.Map.Entry;

public class CodeTable {
	HashMap<Integer, String> intToCode = new HashMap<Integer, String>();
	HashMap<String, Integer> codeToInt = new HashMap<String, Integer>();
		
	int current= 1;
	
	public String getCurrent() {
		return Integer.toString(current);
	}
	
	public Integer getKey(String s) {
		
		return codeToInt.get(s);		
	}
	
	public void addWithKey(String s, int i) {
		intToCode.put(i, s);
		codeToInt.put(s, i);
		
		current++;
	}
	
	public boolean find(String s) {
		return intToCode.containsValue(s);
	}
	
	/**
	    Add a new value to the table, returning the new index.
	**/
	public int add(String s) {
		intToCode.put(current, s);
		codeToInt.put(s, current);
		current++;
		
		return current-1;
	}
	
	public DecodeArray createArray() {
		DecodeArray array= new DecodeArray(intToCode.size());
		
		for (String i : intToCode.values()) {
			array.add(i);
		}
		return array;
	}
	
}
