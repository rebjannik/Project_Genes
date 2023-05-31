package Default;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Scanner;
import java.io.BufferedWriter;

public class Project {
 //To do:
	/*Should create a graph class involving of nodes,
	 * Reading files
	 * Putting out the numbers required
	 * */
	public static void main(String[] args) throws FileNotFoundException {
		Graph<DNANode> g = new Graph<DNANode>();
		
		
		String map = "xaa.txt"+".map";
		String modified = "xaa.txt"+".processed";
		
		File mapFile = new File(map);
		File modifiedFile = new File(modified);
		
		CodeTable table = new CodeTable();
		if (!mapFile.exists()&&!modifiedFile.exists()) {
			table = createFiles("xaa.txt");
		}else {
			table = readFiles("xaa.txt");
		}
		
		DecodeArray array = table.createArray();
		
		//Create the graph with the nodes with the modified file.
		Scanner sc = new Scanner(new File(modified));
		
		while(sc.hasNext()) {
			String[] pairs = sc.next().split(",");
			
			if(pairs.length==2) {

				DNANode node1= new DNANode(pairs[0]);
				DNANode node2 = new DNANode(pairs[1]);
				g.CreateEdge(node1, node2);
			}
		}
		
		sc.close();
		GraphCalculator<DNANode> calc = new GraphCalculator<DNANode>(g);
		
		Map<Integer, Integer> degreeDistr = calc.DegreeDistribution();
		
		for (Entry<Integer, Integer> entry : degreeDistr.entrySet()) {
            int vertex = entry.getKey();
            int neighbors = entry.getValue();

            System.out.print(vertex + " : ");
            System.out.println(neighbors);
		}
	}
	
	private static CodeTable createFiles(String fname) {
	    CodeTable table = new CodeTable();
	    try (Scanner sc = new Scanner(new File(fname));
	         BufferedWriter forMap = new BufferedWriter(new FileWriter(fname + ".map"));
	         BufferedWriter forModified = new BufferedWriter(new FileWriter(fname + ".processed"))) {

	        while (sc.hasNextLine()) {
			
	            String[] values = sc.nextLine().split("\t");
	            if (values.length <= 10) {
			continue;
		    }
		 
		    int firstOverlap = Integer.parseInt(values[6]) - Integer.parseInt(values[5]);
	            int secondOverlap = Integer.parseInt(values[10]) - Integer.parseInt(values[9]);
 
	            if (firstOverlap < 1000 || secondOverlap < 1000) {
			continue;
		    }
	            
		    boolean foundKey1 = table.find(values[0]);
	            boolean foundKey2 = table.find(values[1]);

	            if (!foundKey1) {
	            	table.add(values[0]);
	                forMap.write(values[0] + "," + table.getKey(values[0]));
	                forMap.newLine();
	            }

	            if (!foundKey2) {
	        	table.add(values[1]);
	                forMap.write(values[1] + "," + table.getKey(values[1]));
	                forMap.newLine();
	            }

	            String key1 = table.getKey(values[0]);
	            String key2 = table.getKey(values[1]);

	            forModified.write(key1);
	            forModified.write(",");
	            forModified.write(key2);
	            forModified.newLine();
	        }
	    } catch (IOException e) {
	        e.printStackTrace();
	    } catch (ArrayIndexOutOfBoundsException e1) {
	        System.out.println("Something went wrong when writing or reading");
	    }
	    return table;
	}
	

	private static CodeTable readFiles(String fname) {
		CodeTable table = new CodeTable();
		try {
			Scanner map = new Scanner(new File(fname +".map"));
			
			while(map.hasNext()) {
				String[] values = map.nextLine().split(",");
				
				if(values.length==2) {
					table.addWithKey(values[0], Integer.parseInt(values[1]));
				}
			}
			
			map.close();
			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return table;
		
	}
}
