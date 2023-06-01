package Default;

import java.util.List;
import java.util.Map;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;

public class GraphCalculator<T> {
	private Graph<T> graph;
	
	/*
	 * Constructor that uses an already built graph!
	 */
	public GraphCalculator(Graph<T> g) {
		this.graph = g;
	}

	/*
	 * Input: Nothing
	 * Output: Hashmap with the degree as key and frequency as the value 
	 */
	public Map<Integer, Integer> degreeDistribution(){
		Map<T, Integer>degreeDistribution = new HashMap<>();
		
		for(T node : graph.getNodes()) {
			List<T> neighbours = graph.getNeighbours(node);
			int degree = neighbours.size();
			degreeDistribution.put(node, degree);
		}
		
		return getNodeDegreeDistribution(degreeDistribution);
		
		
	}
	
	/*
	 * Private helper function for DegreeDistribution
	 */
	private Map<Integer, Integer> getNodeDegreeDistribution(Map<T, Integer> degreeDistribution) {
        Map<Integer, Integer> distribution = new HashMap<>();

        for (int degree : degreeDistribution.values()) {
            distribution.put(degree, distribution.getOrDefault(degree, 0) + 1);
        }

        return distribution;
    }

	public void createFrequencyFile(Map<Integer,Integer> map) {
		try(FileWriter writer = new FileWriter("DegreeFrequency.txt")){
			for (Map.Entry<Integer, Integer> entry : map.entrySet()) {
                writer.append(entry.getKey().toString());
                writer.append(",");
                writer.append(entry.getValue().toString());
                writer.append("\n");
			}
		}catch(IOException e){
			e.printStackTrace();
		}
	}
}
