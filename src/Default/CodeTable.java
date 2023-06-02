package Default;

import java.util.HashMap;

public class CodeTable {
	HashMap<Integer, String> intToCode;
	HashMap<String, Integer> codeToInt;
		
	int current= 1;
	
	public CodeTable(){
		intToCode = new HashMap<Integer,String>();
		codeToInt = new HashMap<String, Integer>();
	}
	
	/*
	 * Input: Nothing
	 * Output: A string with the number of elements + 1
	 * Or in other words, the number which the next input is going to go to.
	 */
	public String getCurrent() {
		return Integer.toString(current);
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
		intToCode.put(i, s);
		codeToInt.put(s, i);
		
		current++;
	}
	
	/*
	* Add a new value to the table, returning the new index.
	*/
	public int add(String s) {
		intToCode.put(current, s);
		codeToInt.put(s, current);
		current++;
		
		return current-1;
	}
	
	/*
	 * Deletes the codeToInt hasmap that was only used to get a key
	 * Used for memory saving
	 */
	public void doneAdding(){
		codeToInt = new HashMap<>();
	}
}
