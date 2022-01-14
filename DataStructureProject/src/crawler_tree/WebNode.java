package crawler_tree;

import java.io.IOException;
import java.util.ArrayList;

public class WebNode {
	public WebNode parent;
	public ArrayList<WebNode> children;
	public WebPage webPage;	//child element
	public double nodeScore;//main element This node's score += all its children��s nodeScore
	public int limit;
	public static int searchDepth;
	
	public WebNode(WebPage webPage,WebNode parent,int limit){
		this.limit = limit;
		this.parent = parent;
		this.webPage = webPage;	
		this.children = new ArrayList<WebNode>();
		
		if(webPage.urlList == null)
			return;
		
		if(getDepth() <searchDepth) {
			//System.out.println(getDepth()+" "+webPage.name+" "+webPage.url);
			findChild();
			System.out.println("size: "+this.children.size());
			
		}else {
			//System.out.println(getDepth()+" "+webPage.name+" "+webPage.url);
		}
		/*
		for(String st: webPage.urlList) {
			System.out.println(st);
		}
		*/
	}
	
	public void setNodeScore(ArrayList<Keyword> keywords){
		//this method should be called in post-order mode
		
		//**compute webPage score
		webPage.setScore(keywords);
		//**set webPage score to nodeScore
		nodeScore = webPage.score;
		
		
		//**nodeScore += all children��s nodeScore 
		for(WebNode child : children){
			nodeScore += child.nodeScore;
		}
		
				
			
	}
	public void findChild() {//find subwebpage not more then limit
		if(webPage.urlList == null)return;
		for(int i = 0;i<limit && i<webPage.urlList.size();i++) {
			WebPage newChildPage;
			/*
			if(parent == null) {
				newChildPage = new WebPage(webPage.urlList.get(i),"root/"+i,limit);
			}else {
				newChildPage = new WebPage(webPage.urlList.get(i),parent.webPage.name+"/"+i,limit);
			}*/
			newChildPage = new WebPage(webPage.urlList.get(i),limit);
			WebNode child = new WebNode(newChildPage,this,limit);
			this.children.add(child);
		}
	}
	
	public void addChild(WebNode child){
		//add the WebNode to its children list
		this.children.add(child);
		child.parent = this;
	}
	
	public boolean isTheLastChild(){
		if(this.parent == null) return true;
		ArrayList<WebNode> siblings = this.parent.children;
		
		return this.equals(siblings.get(siblings.size() - 1));
	}
	
	public int getDepth(){
		int retVal = 0;
		WebNode currNode = this;
		while(currNode.parent!=null){
			retVal ++;
			currNode = currNode.parent;
		}
		return retVal;
	}
}
