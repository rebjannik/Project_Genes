package Default;

import java.util.List;
import java.util.Map;
import java.util.Set;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;

public class GraphCalculator<T> {
	private Graph graph; 
	
	/*
	 * Constructor that uses an already built graph!
	 */
	public GraphCalculator(Graph g) {
		this.graph = g;
	}

	/*
	 * Input: Nothing
	 * Output: Hashmap with the degree as key and frequency as the value 
	 */
	private Map<Integer, Integer> degreeDistribution(){
		Map<Integer, Integer>degreeDistribution = new HashMap<>();
		
		for(int i = 1; i< graph.getNumberOfNodes(); i++) {
			List<Integer> neighbours = graph.getNeighbours(i);
			int degree = neighbours.size();
			degreeDistribution.put(i, degree);
		}
		
		return getNodeDegreeDistribution(degreeDistribution);
		
		
	}
	
	/*
	 * Private helper function for DegreeDistribution
	 */
	private Map<Integer, Integer> getNodeDegreeDistribution(Map<Integer, Integer> degreeDistribution) {
        Map<Integer, Integer> distribution = new HashMap<>();

        for (int degree : degreeDistribution.values()) {
            distribution.put(degree, distribution.getOrDefault(degree, 0) + 1);
        }

        return distribution;
    }

	/*
	 * Input: Nothing
	 * Output: Nothing
	 * Side effect: Creates a file with the degree frequency of the graph given.
	 */
	public void createFrequencyFile() {
		Map<Integer,Integer> map = degreeDistribution();

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

	/*
	 * Input: an integer of smallest continous graph allowed
	 * Output: an Integer with how many smaller continuous graphs are in the large graph
	 */
	public int getNumberOfComponentsLargerThan(int n){
		List<List<Integer>> components = findComponents(n);
		return components.size();
	}

	/*
	 * Input: an Integer size of n
	 * Output: a list with smaller list ocntianing continuous graphs.
	 */
	private List<List<Integer>> findComponents(int n){
		List<List<Integer>> components = new ArrayList<>();
		Set<Integer> visited = new HashSet<>();

		for (int i = 1; i<graph.getNumberOfNodes(); i++){
			if(!visited.contains(i)){
				List<Integer> component = new ArrayList<>();
				
				dfs(i, visited, component);
				
				if(component.size()>= n){
					components.add(component);
				}

			}
		}

		return components;
	}

	/*
	 * Input: A node, a set of visited, and a list nodes in the component
	 * Output: Nothing
	 * The code that executes the whole  
	 */
	private void dfs(int node, Set<Integer> visited, List<Integer> component){
		visited.add(node);
		component.add(node);

		for (Integer neighbor : graph.getNeighbours(node)){
			if(!visited.contains(neighbor)){
				dfs(neighbor, visited, component);
			}
		}
	}

	/*
	 * Input: Nothing
	 * Output: a HashMap with the key for density with frequency
	 * Used for the histogram creation
	 */
	private HashMap<Float,Integer> graphDensity(){
		List<List<Integer>> components = findComponents(1);
		HashMap<Float,Integer> densityMap = new HashMap<Float, Integer>();
		
		for(List<Integer> comp : components){
			Float density = calculateDensity(comp);
			densityMap.put(density, densityMap.getOrDefault(density, 0) +1);
		}

		return densityMap;
	}

	/*
	 * Input: A list of a component of the big graph
	 * Output: The calculated density of this specified component
	 */
	private Float calculateDensity(List<Integer> subGraph){
		Float n = (float) subGraph.size();
		Float edges = countEdges(subGraph);

		Float density = edges/(n*n-n);

		return density;
		
		
	}

	private Float countEdges(List<Integer> subGraph){
		Float edges =0f;
		for(Integer node : subGraph){
			List<Integer> neighbours = graph.getNeighbours(node);
			edges += neighbours.size();
		}

		return edges/2;
	}

	public void WriteDensityFile(){
		HashMap<Float,Integer> densityMap = graphDensity();

		try(FileWriter writer = new FileWriter("DensityDistribution.txt")){
			
			for (Map.Entry<Float, Integer> entry : densityMap.entrySet()) {
				
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