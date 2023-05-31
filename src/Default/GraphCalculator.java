package Default;

import java.util.List;
import java.util.Map;
import java.util.HashMap;

public class GraphCalculator<T> {
	private Graph<T> graph;
	
	public GraphCalculator(Graph<T> g) {
		this.graph = g;
	}

	public Map<Integer, Integer> DegreeDistribution(){
		Map<T, Integer>degreeDistribution = new HashMap<>();
		
		for(T node : graph.getNodes()) {
			List<T> neighbours = graph.getNeighbours(node);
			int degree = neighbours.size();
			degreeDistribution.put(node, degree);
		}
		
		return getNodeDegreeDistribution(degreeDistribution);
		
		
	}
	
	private Map<Integer, Integer> getNodeDegreeDistribution(Map<T, Integer> degreeDistribution) {
        Map<Integer, Integer> distribution = new HashMap<>();

        for (int degree : degreeDistribution.values()) {
            distribution.put(degree, distribution.getOrDefault(degree, 0) + 1);
        }

        return distribution;
    }
}
