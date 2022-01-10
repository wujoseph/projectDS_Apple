package projectDS;
import crawler_tree.WebPage;
import crawler_tree.WebTree;
import crawler_tree.Keyword;
import java.util.ArrayList;
public class Main {
	public static void main(String args[]) {
		int limit = 50;//how many link need to check in each website
		WebPage rootPage = new WebPage("http://soslab.nccu.edu.tw/Welcome.html", "Soslab",limit);	
		WebTree tree = new WebTree(rootPage,limit);
		Keyword key = new Keyword("Lab",1);
		ArrayList<Keyword> keylist= new ArrayList<Keyword>();
		keylist.add(key);
		tree.setPostOrderScore(keylist);
		
		tree.eularPrintTree();
	}
}
