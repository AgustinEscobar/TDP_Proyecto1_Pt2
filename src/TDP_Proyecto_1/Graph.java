package TDP_Proyecto_1;

import java.util.LinkedList;
import java.util.List;
import java.util.logging.ConsoleHandler;
import java.util.logging.Handler;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Graph {
	private List<Edge> lista_arcos;
	private List<Integer> lista_vertices;
	private static Logger logger;

	public Graph() {
		lista_arcos = new LinkedList<Edge>();
		lista_vertices = new LinkedList<Integer>();

		if (logger == null) {

			logger = Logger.getLogger(Graph.class.getName());

			Handler hnd = new ConsoleHandler();
			hnd.setLevel(Level.WARNING);
			logger.addHandler(hnd);

			logger.setLevel(Level.WARNING);

			Logger rootLogger = logger.getParent();
			for (Handler h : rootLogger.getHandlers()) {
				h.setLevel(Level.OFF);
			}
		}
	}

	/**
	 * Agrega el nodo "node" al grafo, si aún no pertenecía a la estructura.
	 * 
	 * @param node
	 */
	public void addNode(int node) {
		Integer nodo = node;
		int existe_nodo;
		existe_nodo = lista_vertices.indexOf(node); // Retorna -1 si el nodo no pertenece a la lista.

		if (existe_nodo == -1) {
			lista_vertices.add(nodo);
			logger.fine("El vertice " + node + " fue añadido al grafo exitosamente.");
		} else {
			logger.warning("El vertice " + node + " ya pertenece al grafo. ");
		}
	}

	/**
	 * Agrega un arco entre el nodo "node1" y el nodo "node2", si aún no existía el
	 * arco y ambos parámetros son nodos pertenecientes a la estructura.
	 * 
	 * @param node1
	 * @param node2
	 */
	public void addEdge(int node1, int node2) {
		Integer nodo1 = node1, nodo2 = node2;
		boolean existen_nodos, existe_arco = false;
		int esta_nodo1, esta_nodo2;
		Edge arco;
		esta_nodo1 = lista_vertices.indexOf(nodo1);
		esta_nodo2 = lista_vertices.indexOf(nodo2);
		existen_nodos = esta_nodo1 != -1 && esta_nodo2 != -1;

		if (existen_nodos) {
			arco = new Edge(node1, node2);
			for (Edge arco2 : lista_arcos) {
				if (arco2.equals(arco)) {
					existe_arco = true;
					logger.warning("El arco con origen " + node1 + " y destino " + node2 + " ya existe. ");
				}
			}
			if (!existe_arco) {
				lista_arcos.add(arco);
				logger.fine("El arco con origen " + node1 + " y destino " + node2 + " fue añadido exitosamente. ");
			}
		} else {
			if (esta_nodo1 == -1) {
				logger.warning("El vertice " + node1 + " no existe en el grafo. ");
			} else {
				logger.warning("El vertice " + node2 + " no existe en el grafo. ");
			}
		}
	}

	/**
	 * Remueve el nodo "node" del grafo, si el parámetro es un nodo de la
	 * estructura.
	 */
	public void removeNode(int node) {
		Integer nodo = node;
		int esta_nodo = lista_vertices.indexOf(nodo);

		if (esta_nodo != -1) {
			eliminar_arcos(node, 0);
			lista_vertices.remove(esta_nodo);
			logger.fine("El nodo " + node + " fue removido exitosamente. ");
		} else
			logger.warning("El nodo " + node + " no existe en el grafo. ");
	}

	/**
	 * Elimina los arcos emergentes e incidentes al vertice.
	 * 
	 * @param node
	 * @param indice
	 */
	private void eliminar_arcos(int node, int indice) {

		if (lista_arcos.size() > indice) {
			if (lista_arcos.get(indice).get_origen() == node || lista_arcos.get(indice).get_destino() == node) {
				eliminar_arcos(node, indice + 1);
				logger.info("Arco eliminado: " + lista_arcos.get(indice).toString());
				lista_arcos.remove(indice);
			} else {
				eliminar_arcos(node, indice + 1);
			}
		}
	}

	/**
	 * Remueve el arco entre el nodo “node1” y el nodo “node2”, si el arco formado
	 * por los parámetros pertenecen a la estructura.
	 * 
	 * @param node1
	 * @param node2
	 */
	public void removeEdge(int node1, int node2) {
		Integer nodo1 = node1, nodo2 = node2;
		int esta_nodo1, esta_nodo2;
		boolean pertenece, encontre_arco = false;
		esta_nodo1 = lista_vertices.indexOf(nodo1);
		esta_nodo2 = lista_vertices.indexOf(nodo2);
		pertenece = esta_nodo1 != -1 && esta_nodo2 != -1;

		if (pertenece) {
			for (Edge arco : lista_arcos) {
				if (arco.get_origen() == node1 && arco.get_destino() == node2) {
					lista_arcos.remove(arco);
					encontre_arco = true;
					logger.fine("El arco " + arco.toString() + " fue removido correctamente. ");
					break;
				}
			}
			if (!encontre_arco) {
				logger.warning("El arco con origen " + node1 + " y destino " + node2 + " no pertenece al grafo. ");
			}
		} else {
			logger.warning("El arco con origen " + node1 + " y destino " + node2 + " no pertenece al grafo. ");
		}
	}
}
