package projectDS;
import crawler_tree.Keyword;
import crawler_tree.WebPage;
import crawler_tree.WebTree;
import crawler_tree.WordCounter;

import java.io.IOException;
import java.util.ArrayList;
import Google.GoogleQuery;
import java.util.LinkedHashMap;
import java.util.HashMap;
import rank.Sort;
import rank.WebsiteInfo;
public class Tester {
	public static void main(String args[]){
		
		GoogleQuery google = new GoogleQuery("banana");
		
		try{
			String[][] textArray = google.queryText();
			
			Sort sort = new Sort(textArray,2);
			//LinkedHashMap<String, String> query = sort.testReturn();
			ArrayList<WebsiteInfo> result = sort.getResult();
			
			
			String[][] s = new String[result.size()][3];
			
			int num = 0;
			for(int i =0;i<result.size();i++) {
			    
			    s[i][0] = result.get(i).title;
			    s[i][1] = result.get(i).url;
			    s[i][2] = result.get(i).text;
			    //System.out.println(s[i][0]+" "+s[i][1]+" "+s[i][2]);
			}
			
		}catch(IOException e) {
			System.out.println(e.getMessage());
		}
		
		
		
	}
}
