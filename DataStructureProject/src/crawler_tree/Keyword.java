package crawler_tree;
import java.util.ArrayList;
public class Keyword {
	public String name;
	public double weight;
	
	public Keyword(String name,double weight){
		this.name = name;
		this.weight = weight;
	}
	
	@Override
	public String toString(){
		return "["+name+","+weight+"]";
	}
	public static ArrayList<Keyword> returnKeyword() {
		ArrayList<Keyword> returnList = new ArrayList<Keyword>();
		returnList.add(new Keyword("水果",10));
		returnList.add(new Keyword("蘋果皮",10));
		returnList.add(new Keyword("蘋果樹",10));
		
		returnList.add(new Keyword("打蠟",8));
		returnList.add(new Keyword("台灣蘋果",8));
		
		returnList.add(new Keyword("食物",5));
		returnList.add(new Keyword("青森",5));
		returnList.add(new Keyword("甜",5));
		returnList.add(new Keyword("脆",5));
		returnList.add(new Keyword("紅色",3));
		return returnList;
	}
}
