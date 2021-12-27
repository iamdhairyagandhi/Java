package implementation;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.TreeSet;

public class BinarySearchTree<K, V> {
	/*
	 * You may not modify the Node class and may not add any instance nor static
	 * variables to the BinarySearchTree class.
	 */
	private class Node {
		private K key;
		private V value;
		private Node left, right;

		private Node(K key, V value) {
			this.key = key;
			this.value = value;
		}
	}

	private Node root;
	private int treeSize, maxEntries;
	private Comparator<K> comparator;

	// add(), getMinimumKeyValue(), find(), and getMaximumKeyValue()

	public BinarySearchTree(Comparator<K> comparator, int maxEntries) {
		if (maxEntries < 1) {
			throw new IllegalArgumentException("EMPTY TREE");
		} else {
			treeSize = 0;
			root = null;
			this.comparator = comparator;
			this.maxEntries = maxEntries;
		}
	}

	public BinarySearchTree<K, V> add(K key, V value) throws TreeIsFullException {
		if (root == null) {
			root = new Node(key, value);
		} else {
			addAux(key, value, root);
		}
		treeSize++;
		return this;
	}

	private boolean addAux(K key, V value, Node nodeAux) {
		int comparison = comparator.compare(key, nodeAux.key);

		if (comparison == 0) {
			nodeAux.value = value;
			return false;
		} else if (comparison < 0) {
			if (nodeAux.left == null) {
				nodeAux.left = new Node(key, value);

				return true;
			} else {
				return addAux(key, value, nodeAux.left);
			}
		} else {
			if (nodeAux.right == null) {
				nodeAux.right = new Node(key, value);

				return true;
			} else {
				return addAux(key, value, nodeAux.right);
			}
		}
	}

	public String toString() {
		if (root == null) {
			return "EMPTY TREE";
		} else {
			return toStringAux(root);
		}
	}

	private String toStringAux(Node nodeAux) {
		return nodeAux == null ? ""
				: toStringAux(nodeAux.left) + "{" + nodeAux.key + ":" + nodeAux.value + "}"
						+ toStringAux(nodeAux.right);
	}

	public boolean isEmpty() {
		return root == null;
	}

	public int size() {
		return treeSize;
	}

	public boolean isFull() {
		return treeSize == maxEntries;
	}

	public KeyValuePair<K, V> getMinimumKeyValue() throws TreeIsEmptyException {
		if (root == null) {
			throw new TreeIsEmptyException("EMPTY TREE");
		} else {
			Node node = getMinAux(root);
			if (node == null) {
				return null;
			}
			return new KeyValuePair<K, V>(node.key, node.value);
		}
	}

	public Node getMinAux(Node rootAux) {

		if (rootAux.left == null) {
			return rootAux;
		}
		return getMinAux(rootAux.left);

	}

	public KeyValuePair<K, V> getMaximumKeyValue() throws TreeIsEmptyException {
		if (root == null) {
			throw new TreeIsEmptyException("EMPTY TREE");
		} else {
			Node node = getMaxAux(root);
			if (node == null) {
				return null;
			}
			return new KeyValuePair<K, V>(node.key, node.value);
		}
	}

	public Node getMaxAux(Node rootAux) {
		if (rootAux.right == null) {
			return rootAux;
		}
		return getMaxAux(rootAux.right);

	}

	public KeyValuePair<K, V> find(K key) {
		Node node = findAux(key, root);
		if (node == null) {
			return null;
		}
		return new KeyValuePair<K, V>(node.key, node.value);
	}

	private Node findAux(K key, Node rootAux) {
		if (rootAux == null) {
			return null;
		}
		int comparison = comparator.compare(key, rootAux.key);
		if (comparison == 0) {
			return rootAux;
		} else if (comparison < 0) {
			return findAux(key, rootAux.left);
		} else {
			return findAux(key, rootAux.right);
		}
	}

	public BinarySearchTree<K, V> delete(K key) throws TreeIsEmptyException {
		if (key == null) {
			throw new IllegalArgumentException("Invalid input/key value");
		} else if (root == null) {
			throw new TreeIsEmptyException("EMPTY TREE");
		} else {
			root = deleteAux(root, key);

			return this;
		}
	}

	private Node deleteAux(Node rootAux, K key) {
		if (rootAux == null)
			return rootAux;

		int comparison = comparator.compare(key, rootAux.key);
		if (comparison < 0) {
			rootAux.left = deleteAux(rootAux.left, key);
		} else if (comparison > 0) {
			rootAux.right = deleteAux(rootAux.right, key);
		} else {

			this.treeSize--;
			if (rootAux.left == null) {
				return rootAux.right;
			} else if (rootAux.right == null) {
				return rootAux.left;
			}
			rootAux.key = getMinAux(rootAux.right).key;

			rootAux.right = deleteAux(rootAux.right, rootAux.key);
		}
		return rootAux;
	}

	public void processInorder(Callback<K, V> callback) {
		if (callback == null) {
			throw new IllegalArgumentException("Invalid Argument");
		} else {
			processInorderAux(root, callback);

		}
	}

	private void processInorderAux(Node node, Callback<K, V> callback) {
		if (node == null) {
			return;
		}
		processInorderAux(node.left, callback);
		callback.process(node.key, node.value);
		processInorderAux(node.right, callback);
	}

	public BinarySearchTree<K, V> subTree(K lowerLimit, K upperLimit) {
		int comparison = comparator.compare(lowerLimit, upperLimit);
		BinarySearchTree<K, V> subTree = new BinarySearchTree<>(this.comparator, this.maxEntries);
		if (comparison > 0 || lowerLimit == null || upperLimit == null) {
			throw new IllegalArgumentException(" Invalid limits");
		} else {
			subTree.root = subTreeAux1(lowerLimit, upperLimit, root);

		}
		return subTree;
	}

//	private void subTreeAux(Node node, K lowerLimit, K upperLimit, BinarySearchTree<K, V> sub) {
//		if (node == null)
//			return;
//		int comparison1 = comparator.compare(lowerLimit, node.key);
//		int comparison2 = comparator.compare(upperLimit, node.key);
//
//		subTreeAux(node.left, lowerLimit, upperLimit, sub);
//
//		if (comparison1 < 0 && comparison2 > 0) {
//			//sub.everythingAux(node.key, node.value, sub.root);
//		}
//
//		subTreeAux(node.right, lowerLimit, upperLimit, sub);
//	}

	public Node subTreeAux1(K lowerLimit, K upperLimit, Node rootAux) {

		if (rootAux == null) {
			return null;
		}

		int comp1 = comparator.compare(rootAux.key, lowerLimit);
		int comp2 = comparator.compare(rootAux.key, upperLimit);

		if (comp1 < 0) {
			return subTreeAux1(lowerLimit, upperLimit, rootAux.left);

		} else if (comp2 > 0) {
			return subTreeAux1(lowerLimit, upperLimit, rootAux.right);
		} else {
			Node node = new Node(rootAux.key, rootAux.value);
			node.left = subTreeAux1(lowerLimit, upperLimit, rootAux.left);
			node.right = subTreeAux1(lowerLimit, upperLimit, rootAux.right);
			return node;
		}

	}

	public TreeSet<V> getLeavesValues() {
		ArrayList<V> leaves = new ArrayList<>();
		if (root == null) {
			return new TreeSet<>();
		} else {
			getLeavesValueRec(root, leaves);
			return new TreeSet<>(leaves);
		}
	}

	private void getLeavesValueRec(Node root, ArrayList<V> leaves) {

		if (root == null)
			return;

		if (root.left == null && root.right == null) {
			leaves.add(root.value);
			return;
		}

		if (root.left != null)
			getLeavesValueRec(root.left, leaves);

		if (root.right != null)
			getLeavesValueRec(root.right, leaves);
	}

}
