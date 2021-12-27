package implementation;

import java.util.*;

/**
 * Implements a graph. We use two maps: one map for adjacency properties
 * (adjancencyMap) and one map (dataMap) to keep track of the data associated
 * with a vertex.
 * 
 * @author cmsc132
 * 
 * @param <E>
 */
public class Graph<E> {
	/* You must use the following maps in your implementation */
	private HashMap<String, HashMap<String, Integer>> adjacencyMap;
	private HashMap<String, E> dataMap;
	private int count = 0;
	private static final int limit = 10001;

	public Graph() {
		adjacencyMap = new HashMap<>();
		dataMap = new HashMap<>();
	}

	public void addVertex(String vertexName, E data) {
		if (adjacencyMap.get(vertexName) == null) {
			adjacencyMap.put(vertexName, new HashMap<>());
			dataMap.put(vertexName, data);
			count++;
		} else {
			throw new IllegalArgumentException("Vertex already present");
		}
	}

	public void addDirectedEdge(String startVertexName, String endVertexName,
			int cost) {
		HashMap<String, Integer> directedEdge;
		if (adjacencyMap.get(startVertexName) == null) {
			directedEdge = new HashMap<String, Integer>();
		} else {
			directedEdge = adjacencyMap.get(startVertexName);
		}
		directedEdge.put(endVertexName, cost);
		adjacencyMap.put(startVertexName, directedEdge);
	}

	public Map<String, Integer> getAdjacentVertices(String vertexName) {
		HashMap<String, Integer> map = new HashMap<>(
				adjacencyMap.get(vertexName));
		return map;
	}

	public int getCost(String startVertexName, String endVertexName) {
		if (adjacencyMap.get(startVertexName) == null || adjacencyMap
				.get(startVertexName).get(endVertexName) == null) {
			throw new IllegalArgumentException("invalid operation");
		}
		return (int) adjacencyMap.get(startVertexName).get(endVertexName);
	}

	public E getData(String vertex) {
		if (dataMap.get(vertex) == null) {
			throw new IllegalArgumentException("invalid operation");
		}
		return dataMap.get(vertex);
	}

	public Set<String> getVertices() {
		Set<String> set = new HashSet<>(adjacencyMap.keySet());
		return set;
	}

	public void doBreadthFirstSearch(String startVertexName,
			CallBack<E> callback) {
		TreeSet<String> visited = new TreeSet<>();// empty set initially
		Queue<String> enqueue = new LinkedList<>();
		enqueue.add(startVertexName);
		while (!enqueue.isEmpty()) {
			String dequeue = enqueue.remove();
			if (!visited.contains(dequeue)) {
				callback.processVertex(dequeue, dataMap.get(dequeue));
				visited.add(dequeue); // -----------------------
			}
			for (String s : adjacencyMap.get(dequeue).keySet())
				if (!visited.contains(s))
					enqueue.add(s);
		}
	}

	public void doDepthFirstSearch(String startVertexName,
			CallBack<E> callback) {
		TreeSet<String> visited = new TreeSet<>();// empty set initially
		Stack<String> enqueue = new Stack<>();
		enqueue.add(startVertexName);
		while (!enqueue.isEmpty()) {
			String dequeue = enqueue.pop();
			if (!visited.contains(dequeue)) {
				callback.processVertex(dequeue, dataMap.get(dequeue));
				visited.add(dequeue); // -----------------------
			}
			for (String s : (new TreeMap<>(adjacencyMap.get(dequeue))).keySet())
				if (!visited.contains(s))
					enqueue.add(s);
		}
	}

	public String toString() {
		String output = "";
		TreeMap<String, E> map = new TreeMap<String, E>(dataMap);

		output += "Vertices: " + map.keySet();
		output += "\nEdges:";

		for (String thisVertex : map.keySet()) {
			output += "\nVertex(" + thisVertex + ")--->{";
			String[] keys = new String[adjacencyMap.get(thisVertex).keySet()
					.size()];
			adjacencyMap.get(thisVertex).keySet().toArray(keys);

			for (int i = 0; i < keys.length; i++) {

				output += keys[i] + "="
						+ adjacencyMap.get(thisVertex).get(keys[i]);
				if (i < keys.length - 1) {
					output += ", ";
				}
			}
			output += "}";
		}

		return output.toString();
	}

	public int doDijkstras(String startVertexName, String endVertexName,
			ArrayList<String> shortestPath) {
		Set<String> S = new HashSet<>();
		Map<String, String> P = new HashMap<>();
		Map<String, Integer> C = new HashMap<>();
		for (String name : getVertices()) {
			if (name.equals(startVertexName)) {
				C.put(name, 0);
			} else {
				C.put(name, limit);
			}
			P.put(name, null);
		}

		// dijkstra processing
		while (S.size() < count) {
			String K = findingK(C, S);
			S.add(K);
			if (adjacencyMap.get(K) == null)
				;
			else
				for (String J : adjacencyMap.get(K).keySet())
					if (!S.contains(J)) {
						if (C.get(K) + getCost(K, J) < C.get(J)) {
							C.put(J, C.get(K) + getCost(K, J));
							P.put(J, K);
						}
					}
		}

		// preparation for setting up return value and shortestPath
		int ret = -1;
		for (String s : C.keySet()) {
			if (s.equals(endVertexName)) {
				ret = C.get(s) == limit ? -1 : C.get(s);
				break;
			}
		}

		String temp = endVertexName;
		while (!temp.equals(startVertexName)) {
			shortestPath.add(temp);
			temp = P.get(temp);
			if (temp == null)
				break;
		}
		shortestPath.add(temp);
		Collections.reverse(shortestPath);

		if (ret == -1) {
			shortestPath.clear();
			shortestPath.add("None");
		}
		return ret;
	}

	private String findingK(Map<String, Integer> C, Set<String> S) {
		int smallest = limit + 1;
		String K = "";
		for (String wanted : C.keySet()) {
			if (!S.contains(wanted) && C.get(wanted) <= smallest) {
				K = wanted;
				smallest = C.get(wanted);
			}
		}
		return K;
	}
}