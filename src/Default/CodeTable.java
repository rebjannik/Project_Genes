package Default;

import java.util.HashMap;

public class CodeTable {

	HashMap<Integer, String> intToCode;
	HashMap<String, Integer> codeToInt;
	
	int current= 0;
	
	public CodeTable(){
		intToCode = new HashMap<Integer,String>(Const.getMaxCodetableSize());
		codeToInt = new HashMap<String, Integer>(Const.getMaxCodetableSize());
	}
	
	/*
	 * Input: A string expected in the hashmap
	 * Output: An Integer with the correct key to the string
	 * Null if no such object exist.
	 */
	public Integer getKey(String s) {
		
		return codeToInt.get(s);		
	}
	
	/*
	 * Input: A string and an int
	 * Output: Nothing
	 * Side Effects: Adds a new value (the string) with the key of the int given
	 */
	public void addWithKey(String s, int i) {

		current++;
		
		intToCode.put(i, s);
		codeToInt.put(s, i);
		
	}
	
	/*
	* Add a new value to the table, returning the new index.
	*/
	public int add(String s) {
		current++;

		intToCode.put(current, s);
		codeToInt.put(s, current);
		
		return current;
	}
}