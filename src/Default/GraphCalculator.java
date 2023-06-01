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

	public int getNumberOfComponentsLargerThan(int n){
		List<List<T>> components = findComponents(n);
		return components.size();
	}

	private List<List<T>> findComponents(int n){
		List<List<T>> components = new ArrayList<>();
		Set<T> visited = new HashSet<>();

		for (T node : graph.getNodes()){
			if(!visited.contains(node)){
				List<T> component = new ArrayList<>();
				
				dfs(node, visited, component, n);
				
				if(component.size()>= n){
					components.add(component);
				}

			}
		}

		return components;
	}

private void dfs(T node, Set<T> visited, List<T> component, int n){
	visited.add(node);
	component.add(node);

	for (T neighbor : graph.getNeighbours(node)){
		if(!visited.contains(neighbor)){
			dfs(neighbor, visited, component, n);
		}
	}
}

private List<Float> graphDensity(){
	List<List<T>> components = findComponents(1);
	List<Float> densityList = new ArrayList<Float>();
	
	for(List<T> comp : components){
		Float density = calculateDensity(comp);
		densityList.add(density);
	}

	return densityList;
}

private Float calculateDensity(List<T> subGraph){
	Float n = (float) subGraph.size();
	Float edges = countEdges(subGraph);

	Float density = edges/(n*n-n);

	return density;
	
	
}

private Float countEdges(List<T> subGraph){
	Float edges =0f;
	for(T node : subGraph){
		List<T> neighbours = graph.getNeighbours(node);
		edges += neighbours.size();
	}

	return edges/2;
}

public void WriteDensityFile(){
	List<Float> densityList=graphDensity();

	try(FileWriter writer = new FileWriter("DensityDistribution.txt")){
		for (Float entry : densityList) {
			writer.append(entry.toString());
			writer.append(",");
		}
	}catch(IOException e){
		e.printStackTrace();
	}
}
}
