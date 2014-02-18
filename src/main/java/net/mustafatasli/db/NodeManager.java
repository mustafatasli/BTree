package net.mustafatasli.db;

import java.util.ArrayList;
import java.util.List;

public class NodeManager<K extends Comparable<K>,V> {
    private List<Node<K,V>> nodes;
    private long next = 0;
    private BTree<K,V> btree;
    
    public NodeManager(BTree<K,V> btree) {
        this.btree = btree;
        this.nodes = new ArrayList<Node<K,V>>(1000);
        for (int i = 0; i < 1000; i++) {
            this.nodes.add(i, new Node<K,V>(this.btree, i));
        }
    }

    public Node<K,V> getNode(long n) {
        return this.nodes.get((int) n);
    }

    public Node<K,V> createNode() {
        return this.nodes.get((int) next++);
    }
}
