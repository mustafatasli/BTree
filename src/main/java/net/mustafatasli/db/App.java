package net.mustafatasli.db;

import java.util.Random;

public class App {

	public static void main(String[] args) {
	    BTree<String, String> btree = domainTree();
	    btree.dump();
	    
	    for (String value : btree.keySet()) {
            System.out.println(value);
        }
	    
	    for (String value : btree.values()) {
	        System.out.println(value);
	    }
	}
	
	private static BTree<Integer, Integer> integerTree() {
	    BTree<Integer, Integer> btree = new BTree<Integer, Integer>();
	    
        btree.put(Integer.valueOf(706), Integer.valueOf(706));
        btree.put(Integer.valueOf(176), Integer.valueOf(176));
        btree.put(Integer.valueOf(601), Integer.valueOf(601));
        btree.put(Integer.valueOf(153), Integer.valueOf(153));
        btree.put(Integer.valueOf(513), Integer.valueOf(513));
        btree.put(Integer.valueOf(773), Integer.valueOf(773));
        btree.put(Integer.valueOf(742), Integer.valueOf(742));
        btree.put(Integer.valueOf(373), Integer.valueOf(373));
        btree.put(Integer.valueOf(524), Integer.valueOf(524));
        btree.put(Integer.valueOf(766), Integer.valueOf(766));
        btree.put(Integer.valueOf(275), Integer.valueOf(275));
        btree.put(Integer.valueOf(737), Integer.valueOf(737));
        btree.put(Integer.valueOf(574), Integer.valueOf(574));
        btree.put(Integer.valueOf(434), Integer.valueOf(434));
        btree.put(Integer.valueOf(641), Integer.valueOf(641));
        btree.put(Integer.valueOf(207), Integer.valueOf(207));
        btree.put(Integer.valueOf(001), Integer.valueOf(001));
        btree.put(Integer.valueOf(277), Integer.valueOf(277));
        btree.put(Integer.valueOf(061), Integer.valueOf(061));
        btree.put(Integer.valueOf(736), Integer.valueOf(736));
        btree.put(Integer.valueOf(526), Integer.valueOf(526));
        btree.put(Integer.valueOf(562), Integer.valueOf(562));
        btree.put(Integer.valueOf(017), Integer.valueOf(017));
        btree.put(Integer.valueOf(107), Integer.valueOf(107));
        btree.put(Integer.valueOf(147), Integer.valueOf(147));

        return btree;
	}
	
	private static BTree<String, String> domainTree() {
	    BTree<String, String> btree = new BTree<String, String>();
	    btree.put("www.princeton.edu", "1");
	    btree.put("www.kartaca.com", "2");
	    btree.put("www.simpsons.com", "3");
	    btree.put("www.stanford.edu", "4");
	    btree.put("www.apple.com", "5");
	    btree.put("www.amazon.com", "6");
	    btree.put("www.ebay.com", "7");
	    btree.put("www.cnn.com", "8");
	    btree.put("www.google.com", "9");
	    btree.put("www.nytimes.com", "10");
	    btree.put("www.microsoft.com", "11");
	    btree.put("www.dell.com", "12");
	    btree.put("www.slashdot.org", "13");
	    btree.put("www.espn.com", "14");
	    btree.put("www.weather.com", "15");
	    btree.put("www.yahoo.com", "16");
	    
	    return btree;
	}
	
	private static BTree<Integer, Integer> randomIntegerTree() {
        BTree<Integer, Integer> btree = new BTree<Integer, Integer>();
        Random rnd = new Random();
        
        for(int i = 0; i < 100; i++) {
            btree.put(Integer.valueOf(rnd.nextInt(1000) + 100), null);
        }
        
        return btree;
	}

}
