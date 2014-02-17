package net.mustafatasli.db;

import java.util.ArrayList;
import java.util.List;


public class BTree<K extends Comparable<K>, V> {
	private final int ORDER = 6;
	private int height = 0;
	private Node root;
	private NodeManager nm = new NodeManager();
	
	public BTree() {
		this.root = this.nm.createNode();
	}
	
	private Node split(Node node) {
		int median = ORDER / 2;
		Node right = this.nm.createNode();
		right.count = median;
		node.count = median;
		for (int i = 0; i < median; i++) {
			right.keys[i] = node.keys[i + median];
			right.children[i] = node.children[i + median];
		}
		return right;
	}
	
	public void put(K key, V value) {
		Node node = insert(root, key, value, height);
		if(node != null) {
			Node tmp = this.nm.createNode();
			tmp.count = 2;
			tmp.keys[0] = (K)root.keys[0]; 
			tmp.keys[1] = (K)node.keys[0];
			tmp.children[0] = root.index;
			tmp.children[1] = node.index;
			root = tmp;
			this.height++;
		}
	}
	
	public Node insert(Node node, K key, V value, int height) {
		int index;
		
		if (height == 0) {
			// external
			for (index = 0; index < node.count; index++) {
				if (key.compareTo((K) node.keys[index]) < 0) {
					break;
				}
			}
		} else {
			// internal
			for (index = 0; index < node.count; index++) {
				if (key.compareTo((K) node.keys[index + 1]) < 0) {
					Node child = this.nm.getNode(node.children[index + 1]);
					Node n = insert(child, key, value, --height);
					if (n == null) {
						return null;
					}
					node.keys[index] = n.keys[0];
					break;
				}
			}
		}

		for (int i = node.count; i > index; i--) {
			node.keys[i] = node.keys[i - 1];
			node.children[i] = node.children[i - 1];
		}
	
		node.keys[index] = key;
		node.children[index] = -1;
		node.count++;
		
		if (node.isFull()) {
			return split(node);
		} else {
			return null;
		}
	}
	
	private class NodeManager {
		private List<Node> nodes;
		private long next = 0;
		
		public NodeManager() {
			this.nodes = new ArrayList<>(1000);
			for(int i = 0; i < 1000; i++) {
				this.nodes.add(i, new Node(i));
			}
		}
		
		public Node getNode(long n) {
			return this.nodes.get((int) n);
		}
		
		public Node createNode() {
			return this.nodes.get((int) next++);
		}
	}
	
	private class Node {
		private final int ORDER = 6;
		private final long index;
		private int count = 0;
		
		private Comparable[] keys;
		private Object[] values;
		private long[] children;
		
		
		@SuppressWarnings("unchecked")
		public Node(long index) {
			this.index = index;
			this.keys = new Comparable[ORDER];
			this.children = new long[ORDER + 1];
		}

		public boolean isFull() {
			return this.count == ORDER;
		}

		public boolean contains(K key) {
			return false;
		}

	}
}
