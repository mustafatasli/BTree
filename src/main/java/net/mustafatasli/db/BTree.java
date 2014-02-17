package net.mustafatasli.db;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.ListIterator;
import java.util.Map;
import java.util.Set;

public class BTree<K extends Comparable<K>, V> implements Map<K, V> {
    private final int ORDER = 8;
    private int height = 0;
    private Node root;
    private NodeManager nm = new NodeManager();

    public BTree() {
        this.root = this.nm.createNode();
    }

    public void clear() {
    }

    @SuppressWarnings("unchecked")
    public boolean containsKey(Object key) {
        return this.root.contains((K) key);
    }

    public boolean containsValue(Object arg0) {
        // TODO Auto-generated method stub
        return false;
    }

    public Set<java.util.Map.Entry<K, V>> entrySet() {
        // TODO Auto-generated method stub
        return null;
    }

    public V get(Object arg0) {
        // TODO Auto-generated method stub
        return null;
    }

    public boolean isEmpty() {
        // TODO Auto-generated method stub
        return false;
    }

    public Set<K> keySet() {
        // TODO Auto-generated method stub
        return null;
    }

    public V remove(Object arg0) {
        // TODO Auto-generated method stub
        return null;
    }

    public int size() {
        // TODO Auto-generated method stub
        return 0;
    }

    public Collection<V> values() {
        // TODO Auto-generated method stub
        return null;
    }

    public void putAll(Map<? extends K, ? extends V> arg0) {

    }

    @SuppressWarnings("unchecked")
    public V put(K key, V value) {
        Node node = insert(root, key, value, height);
        if (node != null) {
            Node tmp = this.nm.createNode();
            tmp.count = 2;
            tmp.keys[0] = (K) root.keys[0];
            tmp.keys[1] = (K) node.keys[0];
            tmp.children[0] = root.index;
            tmp.children[1] = node.index;
            root = tmp;
            this.height++;
        }

        return null;
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

    @SuppressWarnings("unchecked")
    private Node insert(Node node, K key, V value, int height) {
        int index;
        int child = -1;

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
                if ((index + 1 == node.count) || key.compareTo((K) node.keys[index + 1]) < 0) {
                    Node n = insert(this.nm.getNode(node.children[index++]), key, value, --height);
                    if (key.compareTo((K) node.keys[0]) < 0) {
                        node.keys[0] = key;
                    }
                    if (n == null) {
                        return null;
                    }

                    key = (K) n.keys[0];
                    child = (int) n.index;
                    break;
                }
            }
        }

        for (int i = node.count; i > index; i--) {
            node.keys[i] = node.keys[i - 1];
            node.children[i] = node.children[i - 1];
        }

        node.keys[index] = key;
        node.children[index] = child;
        node.count++;

        if (node.isFull()) {
            return split(node);
        } else {
            return null;
        }
    }

    public void dump() {
        List<Node> nodes = new ArrayList<Node>();
        nodes.add(this.root);
        dump(nodes);
    }

    @SuppressWarnings("unchecked")
    private void dump(List<Node> nodes) {
        K key;
        long child;
        List<Node> children = new ArrayList<Node>();
        
        for(Node n: nodes) {
            System.out.println(n.index);
            for(int i = 0; i < n.count; i++) {
                key = (K) n.keys[i];
                if (key != null) {
                   System.out.print(key.toString() + ", ");
                } 
                
                child = n.children[i];
                if (child != -1) {
                    children.add(this.nm.getNode(child));
                }  
            }
            
            System.out.println();
        }
        
        if(children.size() > 0) {
            this.dump(children); 
        } else {
            return;
        }
    }

    private class NodeManager {
        private List<Node> nodes;
        private long next = 0;

        public NodeManager() {
            this.nodes = new ArrayList<Node>(1000);
            for (int i = 0; i < 1000; i++) {
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
        private final long index;
        private int count = 0;

        private Comparable[] keys;
        private Object[] values;
        private long[] children;

        @SuppressWarnings("unchecked")
        public Node(long index) {
            this.index = index;
            this.keys = new Comparable[ORDER];
            this.children = new long[ORDER];
            for(int i = 0; i < ORDER; i++) {
                this.children[i] = -1;
            }
        }

        public boolean isFull() {
            return this.count == ORDER;
        }

        public boolean contains(K key) {
            return false;
        }

    }
}
