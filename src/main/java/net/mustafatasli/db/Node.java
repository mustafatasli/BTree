package net.mustafatasli.db;

public class Node<K extends Comparable<K>, V> {
    final long index;
    int count = 0;
    boolean external = false;
    
    BTree<K,V> btree;
    K[] keys;
    Object[] values;
    long[] children;

    @SuppressWarnings("unchecked")
    public Node(BTree<K,V> btree, long index) {
        this.btree = btree;
        this.index = index;
        this.keys = (K[]) new Comparable[BTree.ORDER];
        this.values = new Object[BTree.ORDER];
        this.children = new long[BTree.ORDER];
        for(int i = 0; i < BTree.ORDER; i++) {
            this.children[i] = -1;
        }
    }

    public boolean isFull() {
        return this.count == BTree.ORDER;
    }

    Node<K,V> split() {
        int median = BTree.ORDER / 2;
        Node<K,V> right = btree.nm.createNode();
        right.count = median;
        this.count = median;
        for (int i = 0; i < median; i++) {
            right.keys[i] = this.keys[i + median];
            right.children[i] = this.children[i + median];
        }
        return right;
    }
    
    boolean containsKey(K key) {
        return false;
    }
    
    boolean containsValue(V value) {
        return false;
    }

}
