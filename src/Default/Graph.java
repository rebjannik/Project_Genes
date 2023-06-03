package Default;

import java.util.ArrayList;
import java.util.List;


public class Graph{
	private ArrayList<Integer>[] listOfEdges;
	
    /*
	 * Get the list of all the nodes
	 */
	public int getNumberOfNodes(){
		return listOfEdges.length;
	}
	
	/*
	 * Get the neighbours of a specified node
	 */
	public List<Integer> getNeighbours(int node){
		return listOfEdges[node-1];
	}

	/*
	 * Constructor
	 */
    public Graph() {
        Debug.Log("Initializaing list.");

        listOfEdges = new ArrayList[Const.getMaxCodetableSize()];

        for(int i=0; i<listOfEdges.length; i++){
            listOfEdges[i] = new ArrayList<Integer>();
        }

        Debug.Log("Done initializing list.");
    }

    /*
     * Input: Two nodes of the type T
     * Output: Nothing
     * Creates an edge between the two nodes in the nodes list. Also ensures that
     * the lists gets updated.
     */
    public void CreateEdge(int node1, int node2) {
        //To prevent duplicates - Is there a more efficient way of doing this?
        if (!listOfEdges[node1-1].contains(node2)) {
            listOfEdges[node1-1].add(node2);
        }

        if (!listOfEdges[node2-1].contains(node1)) {
            listOfEdges[node2-1].add(node1);
        }
    }

    /*
     * Helper function to print edges and to see if the function has added the graphs correctly with eachother.
     */
    public void printEdges() {
        for (int i = 0; i<listOfEdges.length; i++) {
            System.out.print((i + 1)  + " : ");
            System.out.println(listOfEdges[i]);
        }
    }
}