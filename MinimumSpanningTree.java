package ph.edu.upm.cas.dpsm.rbchua;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.PriorityQueue;

// KRUSKAL
public class MinimumSpanningTree {
	public static ArrayList<Edge> getMST(Graph graph) {
		int size = graph.getNumV();
		ArrayList<Edge> allEdges = new ArrayList<Edge>();
		for (int i = 0; i < size; i++) {
			for (int j = 0; j < size; j++) {
				allEdges.add(graph.getEdge(i, j));
			}
		}
		PriorityQueue<Edge> pq = new PriorityQueue<>(allEdges.size(), Comparator.comparingDouble(o -> o.getWeight()));
		for (int i = 0; i < allEdges.size() ; i++) {
			 pq.add(allEdges.get(i));
		}
		int [] parent = new int[size];
		for (int i = 0; i < size; i++) {
			 parent[i] = i;
		}
		ArrayList<Edge> list = new ArrayList<Edge>();
		int index = 0;
		while (index < size*size) {
			Edge edge = pq.remove();
			int x_set = find(parent, edge.getSource());
			int y_set = find(parent, edge.getDest());
			if (x_set == y_set) {
			}
			else {
				list.add(edge);
			}
			index++;
			union(parent, x_set, y_set);
		}
		return list;
	}
	
	private static int find(int [] parent, int vertex) {
		if (parent[vertex] != vertex)
			return find(parent, parent[vertex]);
		return vertex;
	}
	
	private static void union(int [] parent, int x, int y) {
		int x_setparent = find(parent, x);
		int y_setparent = find(parent, y);
		parent[y_setparent] = x_setparent;
	}
}
