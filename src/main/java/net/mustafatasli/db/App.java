package net.mustafatasli.db;

public class App {

	public static void main(String[] args) {
		domainTree();
	}
	
	private static void integerTree() {
	    BTree<Integer, Integer> btree = new BTree<Integer, Integer>();
	    
        btree.put(Integer.valueOf(153), null);
        btree.put(Integer.valueOf(176), null);
        btree.put(Integer.valueOf(706), null);
        btree.put(Integer.valueOf(513), null);
        btree.put(Integer.valueOf(601), null);
        btree.put(Integer.valueOf(773), null);
        btree.put(Integer.valueOf(300), null);
        btree.put(Integer.valueOf(350), null);
        btree.put(Integer.valueOf(200), null);
        btree.put(Integer.valueOf(240), null);
        btree.put(Integer.valueOf(320), null);
        btree.put(Integer.valueOf(650), null);
        btree.put(Integer.valueOf(740), null);
        btree.put(Integer.valueOf(520), null);
        btree.put(Integer.valueOf(10), null);
        btree.put(Integer.valueOf(840), null);
        btree.put(Integer.valueOf(80), null);
        btree.put(Integer.valueOf(550), null);
        btree.put(Integer.valueOf(750), null);
        
        btree.dump();
	}
	
	private static void domainTree() {
	    BTree<String, String> btree = new BTree<String, String>();
	    btree.put("www.cs.princeton.edu", "");
	    btree.put("www.princeton.edu",    "");
	    btree.put("www.yale.edu",         "");
	    btree.put("www.simpsons.com",     "");
	    btree.put("www.apple.com",        "");
	    btree.put("www.amazon.com",       "");
	    btree.put("www.ebay.com",         "");
	    btree.put("www.cnn.com",          "");
	    btree.put("www.google.com",       "");
	    btree.put("www.nytimes.com",      "");
	    btree.put("www.microsoft.com",    "");
	    btree.put("www.dell.com",         "");
	    btree.put("www.slashdot.org",     "");
	    btree.put("www.espn.com",         "");
	    btree.put("www.weather.com",      "");
	    btree.put("www.yahoo.com",        "");
	    
	    btree.dump();
	}

}
