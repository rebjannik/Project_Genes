package Default;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Graph<T> {
	private Map<T, List<T>> listOfEdges;
	private List<T> listOfNodes;
	private double numberOfNodes=0;
	private double numberOfEdges=0;
	
	public List<T> getNodes(){
		return listOfNodes;
	}
	
	public List<T> getNeighbours(T node){
		return listOfEdges.get(node);
	}
	
    public Graph() {
        listOfEdges = new HashMap<>();
        listOfNodes = new ArrayList<>();
    }

    public void CreateEdge(T node1, T node2) {
        if (!listOfEdges.containsKey(node1)) {
            addVertex(node1);
        }

        if (!listOfEdges.containsKey(node2)) {
            addVertex(node2);
        }

        List<T> neighbors1 = listOfEdges.get(node1);
        List<T> neighbors2 = listOfEdges.get(node2);

        if (!neighbors1.contains(node2)) {
            neighbors1.add(node2);
        }

        if (!neighbors2.contains(node1)) {
            neighbors2.add(node1);
        }
    }

    private void addVertex(T node) {
        if (!listOfNodes.contains(node)) {
            listOfNodes.add(node);
            listOfEdges.put(node, new ArrayList<>());
        }
        listOfEdges.putIfAbsent(node, new ArrayList<>()); // Ensure neighbors list is initialized
    }
    
    public void printEdges() {
        for (Map.Entry<T, List<T>> entry : listOfEdges.entrySet()) {
            T vertex = entry.getKey();
            List<T> neighbors = entry.getValue();

            System.out.print(vertex + " : ");
            System.out.println(neighbors);
        }
    }
}