package model;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

import org.junit.jupiter.api.Test;

import graph.AdjacencyList;
import graph.Edge;
import graph.GraphAlgorithms;
import graph.IGraph;

class GraphAlgorithmsTest1 {

private IGraph<String> graph;
	
	@Test
	void setUpScenary() {
		graph = new AdjacencyList<String>();
		graph.addVertex("Cali");
		graph.addVertex("Yumbo");
		graph.addVertex("Jamundi");
		graph.addVertex("Buenaventura");
		graph.addVertex("Tulua");
		graph.addEdge("Cali", "Yumbo", 15);
		graph.addEdge("Cali", "Jamundi", 11);
		graph.addEdge("Yumbo", "Jamundi", 12);
		graph.addEdge("Yumbo", "Buenaventura", 14);
		graph.addEdge("Yumbo", "Tulua", 1);
		graph.addEdge("Jamundi", "Buenaventura", 3);
		graph.addEdge("Buenaventura", "Tulua", 2);
	}
	
	@Test
	void setUpScenary1() {
		graph = new AdjacencyList<String>();
		graph.addVertex("A");
		graph.addVertex("B");
		graph.addVertex("C");
		graph.addVertex("D");
		graph.addVertex("E");
		graph.addEdge("A", "C", 4);
		graph.addEdge("A", "E", 3);
		graph.addEdge("B", "D", 3);
		graph.addEdge("B", "E", 4);
		graph.addEdge("C", "D", 7);
		graph.addEdge("C", "E", 2);
		graph.addEdge("D", "E", 8);
	}
	
	@Test 
	void setUpScenary2(){
		graph = new AdjacencyList<String>();
		graph.addVertex("A");
		graph.addVertex("B");
		graph.addVertex("C");
		graph.addVertex("D");
		graph.addVertex("E");
		graph.addVertex("F");
		graph.addVertex("G");
		graph.addVertex("H");
		graph.addVertex("I");
		graph.addVertex("J");
		graph.addEdge("A", "B");
		graph.addEdge("A", "C");
		graph.addEdge("A", "D");
		graph.addEdge("B", "E");
		graph.addEdge("B", "F");
		graph.addEdge("C", "G");
		graph.addEdge("C", "H");
		graph.addEdge("G", "I");
		graph.addEdge("G", "J");
	}
	
	@Test
	void setUpScenary3() {
		graph = new AdjacencyList<String>();
		graph.addVertex("A");
		graph.addVertex("B");
		graph.addVertex("C");
		graph.addVertex("D");
		graph.addVertex("E");
		graph.addVertex("F");
		graph.addVertex("G");

		graph.addEdge("A", "B", 7);
		graph.addEdge("A", "D", 5);
		graph.addEdge("B", "C", 8);
		graph.addEdge("B", "D", 9);
		graph.addEdge("B", "E", 7);
		graph.addEdge("C", "E", 5);
		graph.addEdge("D", "E", 15);
		graph.addEdge("D", "F", 6);
		graph.addEdge("E", "F", 8);
		graph.addEdge("E", "G", 9);
		graph.addEdge("F", "G", 11);
	}
	
	@Test
	void setUpScenary4() {
		graph = new AdjacencyList<String>();
		graph.addVertex("Boston");
		graph.addVertex("Nueva York");
		graph.addVertex("Chicago");
		graph.addVertex("Dallas"); 
		graph.addVertex("Denver");
		graph.addVertex("San Francisco");
		graph.addVertex("Los Angeles");
		//Edges
		graph.addEdge("San Francisco", "Los Angeles", 400);
		graph.addEdge("San Francisco", "Denver", 1000);
		graph.addEdge("San Francisco", "Chicago", 1500);
		graph.addEdge("Los Angeles", "Chicago", 1400);
		graph.addEdge("Los Angeles", "Dallas", 1100);
		graph.addEdge("Denver", "Chicago", 500);
		graph.addEdge("Denver", "Dallas", 600);
		graph.addEdge("Dallas", "Chicago", 800);
		graph.addEdge("Dallas", "Nueva York", 1200);
		graph.addEdge("Chicago", "Nueva York", 700);
		graph.addEdge("Boston", "Nueva York", 300);
		graph.addEdge("Boston", "Chicago", 900);
	}
	
	@Test
	void setUpScenary5() {
		graph = new AdjacencyList<String>();
		graph.addVertex("A");
		graph.addVertex("B");
		graph.addVertex("C");
		
		graph.addEdge("A", "B", 14);
		graph.addEdge("A", "C", 30);
		graph.addEdge("B", "C", 100);
	}
	
	@Test
	void floydWarshallTest() {
		setUpScenary();
		GraphAlgorithms.floydWarshall(graph);
		assertEquals(16, graph.weightMatrix()[4][0]); 
		assertNotEquals(Double.MAX_VALUE, graph.weightMatrix()[0][4]);
		assertEquals(14, graph.weightMatrix()[3][0]); 
		assertNotEquals(Double.MAX_VALUE, graph.weightMatrix()[0][3]);
		assertEquals(3, graph.weightMatrix()[1][3]);
		assertNotEquals(14, graph.weightMatrix()[3][1]);
		assertEquals(6, graph.weightMatrix()[1][2]);
		assertNotEquals(12, graph.weightMatrix()[3][0]);
		assertEquals(5, graph.weightMatrix()[2][4]);
		assertNotEquals(Double.MAX_VALUE, graph.weightMatrix()[4][2]);
	}
	
	@Test
	void dijkstraTest() {
		setUpScenary1();
		GraphAlgorithms.dijkstra("D", graph, 0);
		assertEquals(7, GraphAlgorithms.getCost()[4]);
		assertEquals(0, GraphAlgorithms.getCost()[3]);
		assertEquals(7, GraphAlgorithms.getCost()[2]);
		assertEquals(3, GraphAlgorithms.getCost()[1]);
		assertEquals(10, GraphAlgorithms.getCost()[0]);
		
		assertEquals(3, GraphAlgorithms.getPath()[1]);
		
		setUpScenary4();
		GraphAlgorithms.dijkstra("Dallas", graph, 0);
		assertEquals(1500, GraphAlgorithms.getCost()[0]);
		GraphAlgorithms.dijkstra("San Francisco", graph, 0);
		assertEquals(1500, GraphAlgorithms.getCost()[3]);
		GraphAlgorithms.dijkstra("Chicago", graph, 0);
		assertEquals(1400, GraphAlgorithms.getCost()[6]);
		
		setUpScenary5();
		GraphAlgorithms.dijkstra("C", graph, 0);
		assertEquals(30, GraphAlgorithms.getCost()[0]);
		assertEquals(44, GraphAlgorithms.getCost()[1]);
		assertEquals(0, GraphAlgorithms.getCost()[2]);
		assertEquals(0, GraphAlgorithms.getPath()[1]);
	}
	
	@Test
	void bfsTest() {
		setUpScenary2();
		assertEquals("A", GraphAlgorithms.bfs(graph, "A").get(0));
		assertEquals("B", GraphAlgorithms.bfs(graph, "A").get(1));
		assertEquals("C", GraphAlgorithms.bfs(graph, "A").get(2));
		assertEquals("D", GraphAlgorithms.bfs(graph, "A").get(3));
		assertEquals("E", GraphAlgorithms.bfs(graph, "A").get(4));
		assertEquals("F", GraphAlgorithms.bfs(graph, "A").get(5));
		assertEquals("G", GraphAlgorithms.bfs(graph, "A").get(6));
		assertEquals("H", GraphAlgorithms.bfs(graph, "A").get(7));
		assertEquals("I", GraphAlgorithms.bfs(graph, "A").get(8));
		assertEquals("J", GraphAlgorithms.bfs(graph, "A").get(9));
		
		IGraph<Integer> g = new AdjacencyList<Integer>();
		g.addVertex(1);
		g.addVertex(2);
		g.addVertex(3);
		g.addVertex(4);
		g.addVertex(5);
		g.addVertex(6);
		g.addVertex(7);
		g.addVertex(8);
		g.addVertex(9);
		g.addEdge(1, 2);
		g.addEdge(1, 3);
		g.addEdge(1, 5);
		g.addEdge(2, 4);
		g.addEdge(2, 5);
		g.addEdge(3, 5);
		g.addEdge(3, 6);
		g.addEdge(5, 4);
		g.addEdge(5, 6);
		g.addEdge(5, 7);
		g.addEdge(1, 2);
		g.addEdge(4, 7);
		g.addEdge(4, 8);
		g.addEdge(6, 7);
		g.addEdge(6, 9);
		g.addEdge(7, 8);
		g.addEdge(7, 9);
		g.addEdge(8, 9);
		assertEquals(1, GraphAlgorithms.bfs(g, 1).get(0));
		assertEquals(2, GraphAlgorithms.bfs(g, 1).get(1));
		assertEquals(3, GraphAlgorithms.bfs(g, 1).get(2));
		assertEquals(5, GraphAlgorithms.bfs(g, 1).get(3));
		assertEquals(4, GraphAlgorithms.bfs(g, 1).get(4));
		assertEquals(6, GraphAlgorithms.bfs(g, 1).get(5));
		assertEquals(7, GraphAlgorithms.bfs(g, 1).get(6));
		assertEquals(8, GraphAlgorithms.bfs(g, 1).get(7));
		assertEquals(9, GraphAlgorithms.bfs(g, 1).get(8));
	}
	
	@Test
	void dfsTest() {
		setUpScenary2();
		assertEquals("A", GraphAlgorithms.dfs(graph, "A").get(0));
		assertEquals("D", GraphAlgorithms.dfs(graph, "A").get(1));
		assertEquals("C", GraphAlgorithms.dfs(graph, "A").get(2));
		assertEquals("H", GraphAlgorithms.dfs(graph, "A").get(3));
		assertEquals("G", GraphAlgorithms.dfs(graph, "A").get(4));
		assertEquals("J", GraphAlgorithms.dfs(graph, "A").get(5));
		assertEquals("I", GraphAlgorithms.dfs(graph, "A").get(6));
		assertEquals("B", GraphAlgorithms.dfs(graph, "A").get(7));
		assertEquals("F", GraphAlgorithms.dfs(graph, "A").get(8));
		assertEquals("E", GraphAlgorithms.dfs(graph, "A").get(9));
		
		IGraph<Integer> g = new AdjacencyList<Integer>();
		g.addVertex(1);
		g.addVertex(2);
		g.addVertex(3);
		g.addVertex(4);
		g.addVertex(5);
		g.addEdge(1, 2);
		g.addEdge(1, 3);
		g.addEdge(1, 5);
		g.addEdge(2, 4);
		assertEquals(1, GraphAlgorithms.dfs(g, 1).get(0));
		assertEquals(5, GraphAlgorithms.dfs(g, 1).get(1));
		assertEquals(3, GraphAlgorithms.dfs(g, 1).get(2));
		assertEquals(2, GraphAlgorithms.dfs(g, 1).get(3));
		assertEquals(4, GraphAlgorithms.dfs(g, 1).get(4));
		
		IGraph<Integer> gr = new AdjacencyList<Integer>();
		gr.addVertex(1);
		gr.addVertex(2);
		gr.addVertex(3);
		gr.addVertex(4);
		gr.addVertex(5);
		gr.addEdge(1, 2);
		gr.addEdge(2, 3);
		gr.addEdge(4, 5);
		assertEquals(1, GraphAlgorithms.dfs(gr, 1).get(0));
		assertEquals(2, GraphAlgorithms.dfs(gr, 1).get(1));
		assertEquals(3, GraphAlgorithms.dfs(gr, 1).get(2));
		/*assertEquals(4, GraphAlgorithms.dfs(gr, 1).get(3));
		assertEquals(5, GraphAlgorithms.dfs(gr, 1).get(4));*/
	}
	
	@Test
	void primTest() {
		setUpScenary3();
		assertEquals(39, GraphAlgorithms.prim("A", graph));
		IGraph<Integer> g = new AdjacencyList<Integer>();
		
		g.addVertex(1);
		g.addVertex(2);
		g.addVertex(3);
		g.addVertex(4);
		g.addVertex(5);
		g.addVertex(6);
		g.addVertex(7);
		g.addVertex(8);
		g.addVertex(9);
		g.addVertex(10);
		g.addEdge(1, 2, 5);
		g.addEdge(1, 3, 10);
		g.addEdge(1, 4, 8);
		g.addEdge(2, 4, 6);
		g.addEdge(2, 6, 5);
		g.addEdge(3, 4, 7);
		g.addEdge(3, 5, 8);
		g.addEdge(3, 8, 15);
		g.addEdge(4, 5, 5);
		g.addEdge(4, 6, 11);
		g.addEdge(5, 7, 4);
		g.addEdge(5, 8, 3);
		g.addEdge(6, 7, 9);
		g.addEdge(6, 9, 7);
		g.addEdge(7, 8, 12);
		g.addEdge(7, 9, 4);
		g.addEdge(7, 10, 6);
		g.addEdge(8, 10, 12);
		g.addEdge(9, 10, 7);
		assertEquals(45, GraphAlgorithms.prim(1, g));
		
		IGraph<Integer> simpleG = new AdjacencyList<Integer>();
		simpleG.addVertex(1);
		simpleG.addVertex(2);
		simpleG.addVertex(3);
		simpleG.addVertex(4);
		simpleG.addVertex(5);
		simpleG.addVertex(6);
		simpleG.addEdge(1, 2, 4);
		simpleG.addEdge(1, 3, 4);
		simpleG.addEdge(2, 3, 2);
		simpleG.addEdge(2, 4, 3);
		simpleG.addEdge(2, 5, 4);
		simpleG.addEdge(2, 6, 2);
		simpleG.addEdge(4, 5, 3);
		simpleG.addEdge(5, 6, 3);
		assertEquals(14, GraphAlgorithms.prim(1, simpleG));
	}
	
	@Test
	void kruskalTest() {
		setUpScenary3();
		List<Edge<String>> edges = GraphAlgorithms.kruskal(graph);
		double totalW = 0;
		Edge<String> e = edges.get(0);
		assertEquals(e.getSource(), graph.search(graph.getIndex("A")));
		assertEquals(e.getDestination(), graph.search(graph.getIndex("D")));
		assertEquals(e.getWeight(), 5);
		totalW+= e.getWeight();
		
		e = edges.get(1);
		assertEquals(e.getSource(), graph.search(graph.getIndex("C")));
		assertEquals(e.getDestination(), graph.search(graph.getIndex("E")));
		assertEquals(e.getWeight(), 5);
		totalW+= e.getWeight();
		
		e = edges.get(2);
		assertEquals(e.getSource(), graph.search(graph.getIndex("D")));
		assertEquals(e.getDestination(), graph.search(graph.getIndex("F")));
		assertEquals(e.getWeight(), 6);
		totalW+= e.getWeight();
		
		e = edges.get(3);
		assertEquals(e.getSource(), graph.search(graph.getIndex("A")));
		assertEquals(e.getDestination(), graph.search(graph.getIndex("B")));
		assertEquals(e.getWeight(), 7);
		totalW+= e.getWeight();
		
		e = edges.get(4);
		assertEquals(e.getSource(), graph.search(graph.getIndex("B")));
		assertEquals(e.getDestination(), graph.search(graph.getIndex("E")));
		assertEquals(e.getWeight(), 7);
		totalW+= e.getWeight();
		
		e = edges.get(5);
		assertEquals(e.getSource(), graph.search(graph.getIndex("E")));
		assertEquals(e.getDestination(), graph.search(graph.getIndex("G")));
		assertEquals(e.getWeight(), 9);
		totalW+= e.getWeight();
		
		assertEquals(totalW, 39);
		
		
		IGraph<Integer> simpleG = new AdjacencyList<Integer>();
		simpleG.addVertex(1);
		simpleG.addVertex(2);
		simpleG.addVertex(3);
		simpleG.addVertex(4);
		simpleG.addVertex(5);
		simpleG.addVertex(6);
		simpleG.addEdge(1, 2, 4);
		simpleG.addEdge(1, 3, 4);
		simpleG.addEdge(2, 3, 2);
		simpleG.addEdge(2, 4, 3);
		simpleG.addEdge(2, 5, 4);
		simpleG.addEdge(2, 6, 2);
		simpleG.addEdge(4, 5, 3);
		simpleG.addEdge(5, 6, 3);
		assertEquals(14, GraphAlgorithms.prim(1, simpleG));
		List<Edge<Integer>> edges1 = GraphAlgorithms.kruskal(simpleG);
		totalW = 0;
		Edge<Integer> e1 = edges1.get(0);
		assertEquals(e1.getSource(), simpleG.search(simpleG.getIndex(2)));
		assertEquals(e1.getDestination(), simpleG.search(simpleG.getIndex(3)));
		assertEquals(e1.getWeight(), 2);
		totalW+= e1.getWeight();
		
		e1 = edges1.get(1);
		assertEquals(e1.getSource(), simpleG.search(simpleG.getIndex(2)));
		assertEquals(e1.getDestination(), simpleG.search(simpleG.getIndex(6)));
		assertEquals(e1.getWeight(), 2);
		totalW+= e1.getWeight();
		
		e1 = edges1.get(2);
		assertEquals(e1.getSource(), simpleG.search(simpleG.getIndex(2)));
		assertEquals(e1.getDestination(), simpleG.search(simpleG.getIndex(4)));
		assertEquals(e1.getWeight(), 3);
		totalW+= e1.getWeight();
		
		e1 = edges1.get(3);
		assertEquals(e1.getSource(), simpleG.search(simpleG.getIndex(4)));
		assertEquals(e1.getDestination(), simpleG.search(simpleG.getIndex(5)));
		assertEquals(e1.getWeight(), 3);
		totalW+= e1.getWeight();
		
		e1 = edges1.get(4);
		assertEquals(e1.getSource(), simpleG.search(simpleG.getIndex(1)));
		assertEquals(e1.getDestination(), simpleG.search(simpleG.getIndex(2)));
		assertEquals(e1.getWeight(), 4);
		totalW+= e1.getWeight();
		
		assertEquals(totalW, 14);
	}

}
