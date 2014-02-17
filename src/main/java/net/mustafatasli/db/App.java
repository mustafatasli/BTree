package net.mustafatasli.db;

import java.util.Random;

public class App {

	public static void main(String[] args) {
		integerTree();
	}
	
	private static void integerTree() {
	    BTree<Integer, Integer> btree = new BTree<Integer, Integer>();
	    
        btree.put(Integer.valueOf(706), null);
        btree.put(Integer.valueOf(176), null);
        btree.put(Integer.valueOf(601), null);
        btree.put(Integer.valueOf(153), null);
        btree.put(Integer.valueOf(513), null);
        btree.put(Integer.valueOf(773), null);
        btree.put(Integer.valueOf(742), null);
        btree.put(Integer.valueOf(373), null);
        btree.put(Integer.valueOf(524), null);
        btree.put(Integer.valueOf(766), null);
        btree.put(Integer.valueOf(275), null);
        btree.put(Integer.valueOf(737), null);
        btree.put(Integer.valueOf(574), null);
        btree.put(Integer.valueOf(434), null);
        btree.put(Integer.valueOf(641), null);
        btree.put(Integer.valueOf(207), null);
        btree.put(Integer.valueOf(001), null);
        btree.put(Integer.valueOf(277), null);
        btree.put(Integer.valueOf(061), null);
        btree.put(Integer.valueOf(736), null);
        btree.put(Integer.valueOf(526), null);
        btree.put(Integer.valueOf(562), null);
        btree.put(Integer.valueOf(017), null);
        btree.put(Integer.valueOf(107), null);
        btree.put(Integer.valueOf(147), null);

        btree.dump();
	}
	
	private static void domainTree() {
	    BTree<String, String> btree = new BTree<String, String>();
	    btree.put("www.princeton.edu", "");
	    btree.put("www.kartaca.com", "");
	    btree.put("www.simpsons.com", "");
	    btree.put("www.stanford.edu", "");
	    btree.put("www.apple.com", "");
	    btree.put("www.amazon.com", "");
	    btree.put("www.ebay.com", "");
	    btree.put("www.cnn.com", "");
	    btree.put("www.google.com", "");
	    btree.put("www.nytimes.com", "");
	    btree.put("www.microsoft.com", "");
	    btree.put("www.dell.com", "");
	    btree.put("www.slashdot.org", "");
	    btree.put("www.espn.com", "");
	    btree.put("www.weather.com", "");
	    btree.put("www.yahoo.com", "");
	    
	    btree.dump();
	}
	
	private static void randomIntegerTree() {
        BTree<Integer, Integer> btree = new BTree<Integer, Integer>();
        Random rnd = new Random();
        
        for(int i = 0; i < 100; i++) {
            btree.put(Integer.valueOf(rnd.nextInt(1000) + 100), null);
        }
        
        btree.dump();
	}

}
