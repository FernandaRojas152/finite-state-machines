package model;

import java.util.List;

public interface IGraph<T> {

	/**
	 * Adds a vertex to the graph
	 * @param node The new vertex to be added
	 * @return True if was added and false if it was already in the graph
	 */
	public boolean addVertex(T node);
	
	/**
	 * Adds an edge to the graph
	 * If the graph is directed the connection will be from U to V
	 * <pre> A and B have to exist in the graph
	 * @param A a vertex within the graph
	 * @param B a vertex within the graph
	 */
	public void addEdge(T A, T B);
	
	/**
	 * Adds a weighted edge to the graph
	 * If the graph is directed the connection will be from U to V
	 * <pre> A and B have to exist in the graph
	 * @param A a vertex within the graph
	 * @param B a vertex within the graph
	 * @param l	is the weight of the edge
	 */
	public void addEdge(T A, T B, double l);
	
	/**
	 * Removes a vertex within the graph
	 * @param v A vertex to be removed of the graph
	 * @return True if the vertex was removed or false if the vertex didn't exist
	 */
	public boolean removeVertex(T node);
	
	/**
	 * Removes an edge within the graph
	 * <pre> A and B are within the graph
	 * @param A A vertex connected with V
	 * @param A A vertex connected with U
	 */
	public void removeEdge(T A, T B);
	
	/**
	 * Gives a list of adjacent vertices of V
	 * <pre> node Is within the graph
	 * @param node The vertex to be consulted its adjacent vertices
	 * @return A list with all the adjacent vertices of node
	 */
	public List<T> vertexAdjacent(T node);
	
	/**
	 * Check if A and B are connected
	 * <pre> A and B are within the graph
	 * @param A Is a vertex
	 * @param B Is a vertex
	 * @return True if A and B are connected or false if they're not
	 */
	public boolean areConnected(T A, T B);
	
	/**
	 * <pre> The graph is weighted
	 * @return A matrix with the weight of all the connections
	 */
	public double[][] weightMatrix();
	
	/**
	 * 
	 * @return True if the graph is directed or false if it isn't
	 */
	public boolean isDirected();
	
	public int getVertexSize();
	
	public boolean isWeighted();

	/**
	 * Returns the index given its element
	 * @param vertex the element
	 * @return the index of the element
	 */
	public int getIndex(T vertex);
	
	public boolean search(T A);

	/**
	 * Returns the elements given its index
	 * @param index the index of the element
	 * @return the element of its index
	 */
	public T search(int index);
	
	public List<Edge<T>> getEdges();
}
