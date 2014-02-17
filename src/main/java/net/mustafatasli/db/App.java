package net.mustafatasli.db;

public class App {

	public static void main(String[] args) {
		BTree<Integer, Integer> btree = new BTree<>();
		
		btree.put(Integer.valueOf(153), null);
		btree.put(Integer.valueOf(176), null);
		btree.put(Integer.valueOf(706), null);
		btree.put(Integer.valueOf(513), null);
		btree.put(Integer.valueOf(601), null);
		btree.put(Integer.valueOf(773), null);
		btree.put(Integer.valueOf(300), null);
	}

}
