package Default;

public class DecodeArray {
	private int numOfElements=1;
	String[] configs;
	
	public DecodeArray(int size){
		this.configs = new String[size];
	}
	
	public void add(String str) {
		configs[numOfElements]=str;
		numOfElements++;
	}
	
	public String find(int i) {
		return configs[i];
	}
}
