package rank;
import java.util.LinkedHashMap;
import java.util.HashMap;
import java.util.ArrayList;
import crawler_tree.WebPage;
import crawler_tree.WebTree;
import crawler_tree.Keyword;

public class Sort {
	private LinkedHashMap<String,String> linkedMap;
	private ArrayList<WebsiteInfo> zeroScore;
	private ArrayList<WebsiteInfo> positiveScore;
	private ArrayList<WebsiteInfo> result;
	private LinkedHashMap<String,String> test;
	
	public Sort(LinkedHashMap<String,String> linkedMap) {
		this.linkedMap = linkedMap;
		this.zeroScore = new ArrayList<WebsiteInfo>();
		this.positiveScore = new ArrayList<WebsiteInfo>();
		result = new ArrayList<WebsiteInfo>();
		organize();
		
		System.out.println(positiveScore.size()+"/"+zeroScore.size()+ "/"+linkedMap.size());
		sortLink();
		for(WebsiteInfo k:result) {
			System.out.println(k.score+" "+k.title);
		}
	}
	private void organize() {
		for(String st:linkedMap.keySet()) {
			int limit = 0;//how many link need to check in each website
			WebPage rootPage = new WebPage(linkedMap.get(st), "Test",limit);
			//System.out.println(st);
			WebTree tree = new WebTree(rootPage,limit);
			//Keyword key = new Keyword("Lab",1);//input
			ArrayList<Keyword> keylist= Keyword.returnKeyword();
			//keylist.add(key);
			tree.setPostOrderScore(keylist);			
			tree.eularPrintTree();
			double score = rootPage.score;
			if(score > 0) {
				positiveScore.add(new WebsiteInfo(st,linkedMap.get(st),score));
			}else {
				zeroScore.add(new WebsiteInfo(st,linkedMap.get(st),score));
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
	
}
