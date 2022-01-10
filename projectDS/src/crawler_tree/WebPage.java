package crawler_tree;

import java.io.IOException;
import java.util.ArrayList;

public class WebPage {
	public String url;
	public String name;
	public WordCounter counter;
	public double score;
	public ArrayList<String> urlList;
	public int limit;
	
	public WebPage(String url,String name,int limit){
		this.limit = limit;
		this.url = url;
		this.name = name;
		this.counter = new WordCounter(url);
		this.urlList = counter.subPageLink(counter.content,limit);
	}
	
	public void setScore(ArrayList<Keyword> keywords){
		this.score = 0;
//		3.calculate score
		for(Keyword k : keywords){	
			this.score += counter.countKeyword(k.name)*k.weight;
		}
	}
	
}
