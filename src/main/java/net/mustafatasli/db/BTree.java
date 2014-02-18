package net.mustafatasli.db;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class BTree<K extends Comparable<K>, V> implements Map<K, V> {
    final static int ORDER = 6;
    private int height = 0;
    private int size = 0;
    private Node<K,V> root;
    
    NodeManager<K,V> nm; 

    public BTree() {
        this.nm = new NodeManager<K,V>(this);
        this.root = this.nm.createNode();
    }

    public void clear() {
    }

    @SuppressWarnings("unchecked")
    public boolean containsKey(Object key) {
        return this.root.containsKey((K) key);
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
        return this.size == 0;
    }

    public Set<K> keySet() {
        Set<K> keys = new HashSet<K>();
        return this.getKeys(this.root, keys, this.height);
    }

    public V remove(Object arg0) {
        // TODO Auto-generated method stub
        return null;
    }

    public int size() {
        return this.size;
    }

    public Collection<V> values() {
        Collection<V> values = new ArrayList<V>();
        return this.getValues(this.root, values, this.height);
    }
    
    @SuppressWarnings("unchecked")
    private Set<K> getKeys(Node<K,V> node, Set<K> keys, int h) {
        if (h != 0) {
            for (long child : node.children) {
                if(child != -1) {
                    this.getKeys(this.nm.getNode(child), keys, h - 1);
                }
            }
        } else {
            for (int i = 0; i < node.count; i++) {
                keys.add(node.keys[i]);
            }
        }
        
        return keys;
    }
    
    @SuppressWarnings("unchecked")
    private Collection<V> getValues(Node<K,V> node, Collection<V> values, int h) {
        if (h != 0) {
            for (long child : node.children) {
                if(child != -1) {
                    this.getValues(this.nm.getNode(child), values, h - 1);
                }
            }
        } else {
            for (int i = 0; i < node.count; i++) {
                values.add((V)node.values[i]);
            }
        }
        
        return values;
    }

    @SuppressWarnings("unchecked")
    public void putAll(Map<? extends K, ? extends V> src) {
        for(Entry<?,?> e : src.entrySet()) {
            this.put((K)e.getKey(), (V)e.getValue());
        }
    }

    @SuppressWarnings("unchecked")
    public V put(K key, V value) {
        Node<K,V> node = insert(root, key, value, height);
        this.size++;
        if (node != null) {
            Node<K,V> tmp = this.nm.createNode();
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

    @SuppressWarnings("unchecked")
    private Node<K,V> insert(Node<K,V> node, K key, V value, int height) {
        int index;
        int child = -1;

        if (height == 0) {
            // external
            for (index = 0; index < node.count; index++) {
                if (key.compareTo(node.keys[index]) < 0) {
                    break;
                }
            }
            
            for (int i = node.count; i > index; i--) {
                node.keys[i] = node.keys[i - 1];
                node.children[i] = node.children[i - 1];
                node.values[i] = node.values[i - 1];
            }

            node.keys[index] = key;
            node.children[index] = child;
            node.values[index] = value;
            node.external = true;
            node.count++;
            
        } else {
            // internal
            for (index = 0; index < node.count; index++) {
                if (key.compareTo(node.keys[index]) >= 0) {
                    Node<K,V> splitted= insert(this.nm.getNode(node.children[index]), key, value, --height);
                    //update nodes first key, it must be minimum key of the child node
                    if (key.compareTo(node.keys[0]) < 0) {
                        node.keys[0] = key;
                    }
                    if (splitted == null) {
                        return null;
                    }
                    
                    key = splitted.keys[0];
                    child = (int) splitted.index;
                    
                    for (index = 0; index < node.count; index++) {
                        if (key.compareTo((K) node.keys[index]) < 0) {
                            break;
                        }
                    }
                    
                    for (int i = node.count; i > index; i--) {
                        node.keys[i] = node.keys[i - 1];
                        node.children[i] = node.children[i - 1];
                    }

                    node.keys[index] = key;
                    node.children[index] = child;
                    node.count++;
                    
                    break;
                }
            }
        }

        if (node.isFull()) {
            return node.split();
        } else {
            return null;
        }
    }
    
    public void dump() {
        List<Node<K,V>> nodes = new ArrayList<Node<K,V>>();
        nodes.add(this.root);
        dump(nodes);
    }

    @SuppressWarnings("unchecked")
    private void dump(List<Node<K,V>> nodes) {
        K key;
        long child;
        List<Node<K,V>> children = new ArrayList<Node<K,V>>();
        
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
}
