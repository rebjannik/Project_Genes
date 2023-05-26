package Default;

public class DNANode implements Comparable<DNANode>{
	private int name;
	
	public int getName() {
		return name; 
	}
	
	DNANode(String config){
		
	}
	
	@Override
	public int compareTo(DNANode o) {
		return Integer.compare(name, o.getName());
	}
	
	
}
