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
	
	/*
	 * Get the list of all the nodes
	 */
	public List<T> getNodes(){
		return listOfNodes;
	}
	
	/*
	 * Get the neighbours of a specified node
	 */
	public List<T> getNeighbours(T node){
		return listOfEdges.get(node);
	}
	
	/*
	 * Constructor
	 */
    public Graph() {
        listOfEdges = new HashMap<>();
        listOfNodes = new ArrayList<>();
    }
    
    /*
     * Input: Two nodes of the type T
     * Output: Nothing
     * Creates an edge between the two nodes in the nodes list. Also ensures that the lists gets updated.
     */
    public void CreateEdge(T node1, T node2) {
        numberOfEdges++;
        
    	if (!listOfEdges.containsKey(node1)) {
            addVertex(node1);
        }

        if (!listOfEdges.containsKey(node2)) {
            addVertex(node2);
        }

        List<T> neighbors1 = listOfEdges.get(node1);
        List<T> neighbors2 = listOfEdges.get(node2);
        
        //To prevent duplicates - Is there a more efficient way of doing this?
        if (!neighbors1.contains(node2)) {
            neighbors1.add(node2);
        }

        if (!neighbors2.contains(node1)) {
            neighbors2.add(node1);
        }
    }

    /*
     * Input: a node of type T
     * Output: nothing
     */
    private void addVertex(T node) {
    	numberOfNodes++;
    	
    	listOfNodes.add(node);
        listOfEdges.put(node, new ArrayList<>());
    }
    
    /*
     * Helper function to print edges and to see if the function has added the graphs correctly with eachother.
     */
    public void printEdges() {
        for (Map.Entry<T, List<T>> entry : listOfEdges.entrySet()) {
            T vertex = entry.getKey();
            List<T> neighbors = entry.getValue();

            System.out.print(vertex + " : ");
            System.out.println(neighbors);
        }
    }
}