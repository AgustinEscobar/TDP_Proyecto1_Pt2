package TDP_Proyecto_1;



public class Graph_test {
	
	public static void main (String[]args) {
		Graph grafo = new Graph();
		
		grafo.addNode(1);
		grafo.addNode(2);
		grafo.addNode(3);
		grafo.addNode(4);
		
		grafo.addEdge(1,2);
		grafo.addEdge(1,3);
		grafo.addEdge(2,3);
		grafo.addEdge(2,4);
		
		grafo.addEdge(1,2);
		grafo.removeNode(2);
		grafo.removeNode(2);
		grafo.removeEdge(2, 4);
		grafo.removeEdge(2, 3);
		grafo.addNode(1);
		
	}
}