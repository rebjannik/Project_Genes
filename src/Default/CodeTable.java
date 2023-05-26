package Default;

import java.util.HashMap;
import java.util.Map.Entry;

public class CodeTable {
	HashMap<Integer, String> intToCode = new HashMap<Integer, String>();
	int current= 1;
	
	public String getCurrent() {
		return Integer.toString(current);
	}
	
	public String getKey(String s) {
		for(Entry<Integer, String> entry : intToCode.entrySet()) {
			if (entry.getValue().equals(s)) {
				return Integer.toString(entry.getKey());
			}
		}
		return s;
	}
	
	public void addWithKey(String s, int i) {
		intToCode.put(i, s);
		current++;
	}
	
	public boolean find(String s) {
		return intToCode.containsValue(s);
	}
	
	public void add(String s) {
		intToCode.put(current, s);
		current++;
	}
	
	public DecodeArray createArray() {
		DecodeArray array= new DecodeArray(intToCode.size());
		
		for (String i : intToCode.values()) {
			array.add(i);
		}
		return array;
	}
	
}
