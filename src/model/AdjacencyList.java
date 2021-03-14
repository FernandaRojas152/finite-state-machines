package model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class AdjacencyList<T> implements IGraph<T>{
	

	 /**
    * The length of the matrix when using the default Constructor.
    */
   private static final int DEFAULT_CAPACITY = 21;
	
	/**
	 * Map with all the vertices within the graph.
	 * Key of the map is the Vertex and Value is the position of the vertex in the adjacencyList
	 */
	private Map<T, Integer> vertices;	
	
	 /**
     * A Map that uses any vertex as a key to access its corresponding index in the matrix.
     */
    private Map<Integer, T> verticesIndices;
	
	/**
	 * A list for each Vertex within the graph which has a list with all its adjacent Vertices 
	 */
	private List<List<T>> adjacencyLists;
	
	/**
	 * Property that say if a graph is directed or not
	 */
	private boolean isDirected;
	
	/**
     * The associated matrix containing the weight of all edged between nodes in the graph.
     */
    private double[][] adjacencyMatrixWeight;
	
	/**
	 * Basic constructor that is initialized with default values
	 */
	public AdjacencyList() {
		initialize(DEFAULT_CAPACITY);
	}

	/**
	 * Constructor that gets the value for "isDirected" attribute.
	 * True if the graph is Directed or false if it's Indirected
	 * @param id value to set "isDirected"
	 */
	public AdjacencyList(boolean id) {
		initialize(DEFAULT_CAPACITY);
		isDirected = id;
	}
	
	/**
	 * Initializes all the data structures for this graph.
	 * Set "isDirected" attribute in false
	 */
	private final void initialize(int capacity) {
		isDirected = false;
		adjacencyLists = new ArrayList<List<T>>();
		vertices = new HashMap<T, Integer>();
		verticesIndices = new HashMap<>();
		adjacencyMatrixWeight = new double[capacity][capacity];
	}

	@Override
	public boolean addVertex(T node) {
		boolean added = false;
		// Check if the vertex is not on the map already
		if(!search(node)) {
			@SuppressWarnings("unchecked")
			// Create a new empty list for that vertex
			List<T> vList = (List<T>) new ArrayList<Object>();
			// Get the position for this new vertex
			int index = adjacencyLists.size();
			// Add the vertex to the map
			vertices.put(node, index);
			verticesIndices.put(index, node);
			// Add the vertex empty list to the adjacencyLists
			adjacencyLists.add(vList);
			// Change the value to true indicating that it was possible to add the vertex
			added = true;
		}
		return added;
	}

	@Override
	public void addEdge(T A, T B) {
		int ValueA = vertices.get(A);
		int ValueB = vertices.get(B);
		if(!isDirected) {
			adjacencyLists.get(ValueA).add(B);
			adjacencyLists.get(ValueB).add(A);
		}else {
			adjacencyLists.get(ValueA).add(B);
		}
	}

	@Override
	public void addEdge(T A, T B, double l) {
		int x = vertices.get(A);//TODO: check pre-conditions
        int y = vertices.get(B);
        /*if (!isDirected) {
        	adjacencyLists.get(x).add(B);
			adjacencyLists.get(y).add(A);
            adjacencyMatrixWeight[x][y] = l;
            adjacencyMatrixWeight[y][x] = l;
        } else {
            adjacencyMatrixWeight[x][y] = l;
            adjacencyLists.get(x).add(B);
        }*/
		Edge<T> edge = new Edge<T>(A, B, l);
		AdjVertex<T> from = new AdjVertex<T>(A);
		from.getAdjList().add(edge);
		if (!isDirected()) {
			edge = new Edge<T>(A, B, l);
			AdjVertex<T> to = new AdjVertex<T>(B);
			to.getAdjList().add(edge);
			adjacencyLists.get(x).add(B);
			adjacencyLists.get(y).add(A);
            adjacencyMatrixWeight[x][y] = l;
            adjacencyMatrixWeight[y][x] = l;
		}
	}

	@SuppressWarnings("unlikely-arg-type")
	@Override
	public boolean removeVertex(T node) {
		// first looks if the vertex exists
		if (vertices.containsKey(node)) {
			// remove the existing list which represents the adjacent vertices of the vertex
			// to remove
			adjacencyLists.remove(vertices.get(node));
			// remove any existing connection to the vertex
			for (int i = 0; i < adjacencyLists.size(); i++) {
				if (adjacencyLists.get(i).contains(node))
					adjacencyLists.get(i).remove(i);
			}
			// removes the vertex form the map
			vertices.remove(node);
			return true;
		} else {
			return false;
		}
	}

	@Override
	public void removeEdge(T A, T B) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<T> vertexAdjacent(T node) {
		List<T> neigh = new ArrayList<>();
		AdjVertex<T> aV = new AdjVertex<T>(node);
		List<Edge<T>> adj = aV.getAdjList();
		for (int i = 0; i < adj.size(); i++) {
			neigh.add(adj.get(i).getDestination());
		}
		return neigh;
	}

	@Override
	public boolean areConnected(T A, T B) {
		int aValue = vertices.get(A);
		int bValue = vertices.get(B);
		
//		return adjacencyLists.get(uValor).contains(v) || adjacencyLists.get(uValor).contains(v);
//		This return exists in case there is no need of being specific about the direction
		
		if(isDirected) {
			return adjacencyLists.get(aValue).contains(B);
			// this returns if u connected and directed to v
		}else {
			return adjacencyLists.get(aValue).contains(B) && adjacencyLists.get(bValue).contains(A);
			// in case the graph is not connected then both should be connected to each other
		}
	}

	@Override
	public double[][] weightMatrix() {
		for (int i = 0; i < adjacencyLists.size(); i++) {
			for (int j = 0; j < adjacencyLists.size(); j++) {
				if (adjacencyMatrixWeight[i][j] == 0 ) {
					if (i != j) {
						adjacencyMatrixWeight[i][j] = Double.MAX_VALUE;
					}
				}
			}
		}
		return adjacencyMatrixWeight;
	}

	@Override
	public boolean isDirected() {
		return isDirected;
	}

	@Override
	public int getVertexSize() {
		return vertices.size();
	}

	@Override
	public boolean isWeighted() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public int getIndex(T vertex) {
		return vertices.get(vertex);
	}

	@Override
	public boolean search(T A) {
		return verticesIndices.containsValue(A);
	}

	public Map<T, Integer> getVertices() {
		return vertices;
	}

	public List<List<T>> getAdjacencyLists() {
		return adjacencyLists;
	}

	@Override
	public T search(int index) {
		return verticesIndices.get(index);
	}

	@Override
	public List<Edge<T>> getEdges() {
		ArrayList<Edge<T>> edges = new ArrayList<>();
		for (int i = 0; i < vertices.size(); i++) {
			AdjVertex<T> v = new AdjVertex<T>(verticesIndices.get(i));
			for (int j = 0; j < v.getAdjList().size(); j++) {
				edges.add(v.getAdjList().get(j));
			}
		}
		return edges;
	}

}
