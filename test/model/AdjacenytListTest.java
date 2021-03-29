package model;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import graph.AdjacencyList;

class AdjacenytListTest {

private AdjacencyList<String> g;
	
	@Test
	void setUpScenary() {
		g = new AdjacencyList<String>();
	}
	
	@Test
	void setUpScenary1() {
		g = new AdjacencyList<String>();
		g.addVertex("Cali");
		g.addVertex("Yumbo");
	}
	
	@Test
	void setUpScenary2() {
		g = new AdjacencyList<String>();
		g.addVertex("Cali");
		g.addVertex("Yumbo");
		g.addVertex("Jamundi");
		g.addVertex("Buenaventura");
		g.addVertex("Tulua");
		g.addEdge("Cali", "Yumbo", 15);
		g.addEdge("Cali", "Jamundi", 11);
		g.addEdge("Yumbo", "Jamundi", 12);
		g.addEdge("Yumbo", "Buenaventura", 14);
		g.addEdge("Yumbo", "Tulua", 1);
		g.addEdge("Jamundi", "Buenaventura", 3);
		g.addEdge("Buenaventura", "Tulua", 2);
	}

	@Test
	void addVertexTest() {
		setUpScenary();
		assertEquals(true, g.addVertex("Cali"));
		assertEquals(true, g.addVertex("Yumbo"));
		assertEquals(true, g.addVertex("Tumaco"));
	}
	
	@Test
	void addEdgeTest() {
		setUpScenary1();		
		g.addEdge("Yumbo", "Cali", 3.8);
		assertEquals(true, g.areConnected("Yumbo", "Cali"));
		g.addVertex("Jamundi");
		g.addEdge("Jamundi", "Cali", 100);
		assertTrue("No se encontró la arista", g.areConnected("Jamundi", "Cali"));
	}
	
	@Test
	void addEdgeTest1() {
		setUpScenary();
		g.addVertex("Madrid");
		g.addVertex("Barcelona");
		g.addVertex("Fuenlabrada");
		g.addEdge("Madrid", "Barcelona");
		assertEquals(true, g.areConnected("Madrid", "Barcelona"));
		assertFalse("Encuenta una arista que no esta", g.areConnected("Madrid", "Fuenlabrada"));
	}
	
	@Test
	void searchTest() {
		setUpScenary1();
		assertEquals(true, g.search("Yumbo"));
		assertEquals(true, g.search("Cali"));
		assertFalse("Encuentra un vertice que no esta", g.search("Tumaco"));
	}
	
	@Test
	void weightMatrixTest() {
		setUpScenary2();
		assertEquals(14, g.weightMatrix()[1][3]);
		assertEquals(14, g.weightMatrix()[3][1]);
		assertEquals(15, g.weightMatrix()[1][0]);
		assertEquals(15, g.weightMatrix()[0][1]);
		assertEquals(12, g.weightMatrix()[1][2]);
		assertEquals(12, g.weightMatrix()[2][1]);
		assertEquals(11, g.weightMatrix()[0][2]);
		assertEquals(11, g.weightMatrix()[2][0]);
		assertEquals(1, g.weightMatrix()[1][4]);
		assertEquals(1, g.weightMatrix()[4][1]);
		assertEquals(3, g.weightMatrix()[2][3]);
		assertEquals(3, g.weightMatrix()[3][2]);
		assertEquals(2, g.weightMatrix()[3][4]);
		assertEquals(2, g.weightMatrix()[4][3]);
		assertEquals(Double.MAX_VALUE, g.weightMatrix()[4][0]);
		assertEquals(Double.MAX_VALUE, g.weightMatrix()[0][4]);
		assertEquals(Double.MAX_VALUE, g.weightMatrix()[3][0]);
		assertEquals(Double.MAX_VALUE, g.weightMatrix()[0][3]);
		assertEquals(0, g.weightMatrix()[0][0]);
		assertEquals(0, g.weightMatrix()[1][1]);
		assertEquals(0, g.weightMatrix()[2][2]);
		assertEquals(0, g.weightMatrix()[3][3]);
		assertEquals(0, g.weightMatrix()[4][4]);
	}
	
	@Test
	void getIndexTest() {
		setUpScenary2();
		assertEquals(0, g.getIndex("Cali"));
		assertEquals(4, g.getIndex("Tulua"));
		assertNotEquals(2, g.getIndex("Buenaventura"));
	}
	
	@Test
	void getVertexSizeTest() {
		setUpScenary2();
		assertEquals(5, g.getVertexSize());
		assertNotEquals(4, g.getVertexSize());
	}
	
	@Test
	void getEdgesTest() {
		setUpScenary2();
		assertEquals(15, g.getEdges().get(0));
	}


}
