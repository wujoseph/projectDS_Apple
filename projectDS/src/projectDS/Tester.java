package projectDS;
import crawler_tree.Keyword;
import crawler_tree.WebPage;
import crawler_tree.WebTree;
import crawler_tree.WordCounter;
import java.util.ArrayList;
import Google.GoogleQuery;
import java.util.LinkedHashMap;
import java.util.HashMap;
import rank.Sort;
public class Tester {
	public static void main(String args[]) {
		
		LinkedHashMap<String, String> result = new LinkedHashMap<String, String> ();
		GoogleQuery que = new GoogleQuery("蘋果");
		try {
			result = que.query();
		}catch(Exception e) {
			System.out.println(e.getMessage());
		}
		/*
		System.out.println("-------------------");
		for(String st:result.keySet()) {
			System.out.println(st+" / "+result.get(st));
		}
		
		int limit = 50;//how many link need to check in each website
		WebPage rootPage = new WebPage("http://soslab.nccu.edu.tw/Welcome.html", "Soslab",limit);	
		WebTree tree = new WebTree(rootPage,limit);
		Keyword key = new Keyword("Lab",1);
		ArrayList<Keyword> keylist= new ArrayList<Keyword>();
		keylist.add(key);
		tree.setPostOrderScore(keylist);
		
		tree.eularPrintTree();
		*/
		new Sort(result);
	}
}
