package Default;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class DNANode implements Comparable<DNANode>{
	private int name;
	
	public int getName() {
		return name; 
	}
	
	DNANode(String config){
		name=DNAToInt(config);
	}
	
	private int DNAToInt(String config) {
		try {
			MessageDigest dig = MessageDigest.getInstance("SHA-256");
			byte[] bytes = config.getBytes(StandardCharsets.UTF_8);
			
			dig.update(bytes);
			byte[] hash = dig.digest();
			
			return byteArrayToInt(hash);
		}
		catch(NoSuchAlgorithmException e) {
			e.printStackTrace();
			return 0;
		}
	}
	
	private int byteArrayToInt(byte[] bytes) {
		int value=0;
		
		for(int i=0; i<4;i++) {
			value |=(bytes[i]&0xFF)<<(24-8*i);
		}
		
		return value;
	}
	
	@Override
	public int compareTo(DNANode o) {
		return Integer.compare(name, o.getName());
	}
	
	
}
