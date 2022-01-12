package rank;
import java.util.LinkedHashMap;
import java.util.HashMap;
import java.util.ArrayList;
import crawler_tree.WebPage;
import crawler_tree.WebTree;
import crawler_tree.Keyword;

public class Sort {
	private ArrayList<WebsiteInfo> beginning;
	private ArrayList<WebsiteInfo> zeroScore;
	private ArrayList<WebsiteInfo> positiveScore;
	private ArrayList<WebsiteInfo> result;
	private LinkedHashMap<String,String> test;
	private static int searchLimit=100;//???
	private int webTreeLimit;//how many url need to check, also is the number of subwebsite for each node.
	
	public Sort(String[][] textArray,int limit) {
		this.beginning = new ArrayList<WebsiteInfo>();
		this.zeroScore = new ArrayList<WebsiteInfo>();
		this.positiveScore = new ArrayList<WebsiteInfo>();
		this.result = new ArrayList<WebsiteInfo>();
		this.webTreeLimit = limit;
		
		for(int i = 0;i<100;i++) {
			try {
				if(textArray[i][0] == "")
					break;
				if(textArray[i][2] == null)
					break;
				beginning.add(new WebsiteInfo(textArray[i][0],textArray[i][1],textArray[i][2],0));
				//System.out.println(textArray[i][0]+"    "+textArray[i][1]+"     "+textArray[i][2]);
			}catch(ArrayIndexOutOfBoundsException e) {
				break;
			}
			
		}		
		
		
		
		
		organize();
		
		System.out.println(positiveScore.size()+"/"+zeroScore.size()+ "/"+beginning.size());
		sortLink();
		for(WebsiteInfo k:result) {
			System.out.println(k.score+" "+k.title);
		}
	}
	private void organize() {
		for(int i = 0;i<beginning.size();i++) {
			if(positiveScore.size()+zeroScore.size() >= searchLimit)
				break;
			int titleLength = Math.min(beginning.get(i).title.length(), 10);
			WebPage rootPage = new WebPage(beginning.get(i).url, beginning.get(i).title.substring(0,titleLength),this.webTreeLimit);
			//System.out.println(st);
			WebTree tree = new WebTree(rootPage,this.webTreeLimit);
			//Keyword key = new Keyword("Lab",1);//input
			ArrayList<Keyword> keylist= Keyword.returnKeyword();
			//keylist.add(key);
			tree.setPostOrderScore(keylist);			
			tree.eularPrintTree();
			double score = tree.root.nodeScore;
			if(score > 0) {
				beginning.get(i).score = score;
				positiveScore.add(beginning.get(i));
			}else {
				zeroScore.add(beginning.get(i));
			}
		}
	}
	private void sortLink() {
		while(positiveScore.size()>0) {
			double max = -1;
			int index = 0;
			for(int i = 0;i<positiveScore.size();i++) {
				if(positiveScore.get(i).score > max) {
					max = positiveScore.get(i).score;
					index = i;
				}				
			}
			result.add(positiveScore.get(index));
			positiveScore.remove(index);
		}
		
		for(WebsiteInfo k:zeroScore) {
			result.add(k);
		}
	}
	public LinkedHashMap<String,String> testReturn() {		
		test = new LinkedHashMap<String,String>();
		for(WebsiteInfo i:result) {
			test.put(i.title, i.url);
		}
		return test;
	}
	public ArrayList<WebsiteInfo> getResult() {
		return this.result;
	}
	
	
}
