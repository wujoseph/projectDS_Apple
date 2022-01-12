package crawler_tree;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.HashMap;

public class WordCounter {
	private String urlStr;
    public String content;
    
    public WordCounter(String urlStr){
    	this.urlStr = urlStr;
    	try {
    		content = fetchContent();
    	}catch(IOException e){
    		System.out.println("content IOException. "+e.getMessage());
    	}catch(Exception e) {
    		System.out.println("other content Exception. "+e.getMessage());
    	}
    	
    }
    
    private String fetchContent() throws IOException

	{
		String retVal = "";

		URL u = new URL(this.urlStr);

		URLConnection conn = u.openConnection();
		
		//"Chrome/7.0.517.44"
		conn.setRequestProperty("User-agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/97.0.4692.71 Safari/537.36");
		conn.setRequestProperty("sec-ch-ua", " Not;A Brand;v=\"99\", \"Google Chrome\";v=\"97\", \"Chromium\";v=\"97");
		conn.setRequestProperty("sec-ch-ua-platform","Windows");
		conn.setRequestProperty("referer", "https://www.google.com/");
		
		InputStream in = conn.getInputStream();

		InputStreamReader inReader = new InputStreamReader(in,"utf-8");

		BufferedReader bufReader = new BufferedReader(inReader);
		String line = null;

		while((line=bufReader.readLine())!=null)
		{
			retVal += line;

		}
		return retVal;
	}
	public int boyerMoore(String strT,String strP) {//strT:content, strP:keyword, return the amount of keyword
    	HashMap<Character,Integer>map = new HashMap<>();
    	
		for(int i = 0;i<strP.length();i++) {
			map.put(strP.charAt(i), i);
		}		
		
		int count = 0;
		for(int i = strP.length()-1;i<strT.length();) {
			for(int j = strP.length()-1;j >= 0;) {
				int strL = strP.length()-j;
				if(strT.charAt(i) != strP.charAt(j)) {
					//System.out.println("i:"+i+"   j:"+j+"    "+strT.substring(i,i+strL)+"/"+strP.substring(j,strP.length()));
					//System.out.println("count:"+count);
					if(map.get(strT.charAt(i)) != null)
						i = i + strP.length() - Math.min(j,1+map.get(strT.charAt(i)));
					else
						i = i + strP.length();
					break;
				}
				else if(j == 0) {
					//System.out.println("-------------Substring find. "+strT.substring(i,i+strP.length()));
					//System.out.println("count:"+count);
					i = i + strP.length();
					count++;
					break;
				}else {
					i--;
					j--;
					continue;
				}
			}
		}
		//System.out.println("count:"+count);
		return count;
	}
	
	public ArrayList<String> subPageLink(String strT,int limit) {//strT:content, strP:keyword, return the amount of keyword
		if(strT == null || strT == "") {
			//System.out.println("Null error?   "+urlStr);
			return null;
		}
		
    	String strP = "href=\"";
    	ArrayList<String> returnList = new ArrayList<String>();
		HashMap<Character,Integer>map = new HashMap<>();
    	
		for(int i = 0;i<strP.length();i++) {
			map.put(strP.charAt(i), i);
		}
		int lastSlash = urlStr.lastIndexOf("/");

		int count = 0;
		for(int i = strP.length()-1;i<strT.length();) {
			for(int j = strP.length()-1;j >= 0;) {
				count++;
				int strL = strP.length()-j;
				if(strT.charAt(i) != strP.charAt(j)) {
					//System.out.println("i:"+i+"   j:"+j+"    "+strT.substring(i,i+strL)+"/"+strP.substring(j,strP.length()));
					//System.out.println("count:"+count);
					if(map.get(strT.charAt(i)) != null)
						i = i + strP.length() - Math.min(j,1+map.get(strT.charAt(i)));
					else
						i = i + strP.length();
					break;
				}
				else if(j == 0) {
					//System.out.println("-------------Substring find. "+strT.substring(i,i+strP.length()));
					//System.out.println("count:"+count);
					int endI;
					for(endI = i+6;strT.charAt(endI) != '\"';endI++) {//find the place where " first appear, which is the end of link
						if(endI-i >3000) {
							System.out.println("too long link");
							return returnList;
						}						
					}
					//System.out.println("test:"+strT.substring(i+6,endI));
					if(strT.substring(i+6,i+10).equals("http")) {
						returnList.add(strT.substring(i+6,endI));
					}else if(strT.substring(i+6,i+8).equals("./")) {//e.g. "./subwebsite"
						
						String preUrl = urlStr.substring(0,lastSlash);
						//System.out.println(preUrl+" "+ strT.substring(i+6,i+8));
						//System.out.println("---------------");
						returnList.add(preUrl + strT.substring(i+7,endI));
					}else if(strT.substring(i+6,i+7).equals("/")) {//e.g. "/subwebsite"
						
						String preUrl = urlStr.substring(0,lastSlash);
						//System.out.println(preUrl+" "+ strT.substring(i+6,i+8));
						//System.out.println("---------------");
						returnList.add(preUrl + strT.substring(i+6,endI));
					}else {//href with other format, usually javascript code
						//System.out.println("exception "+strT.substring(i,endI));
					}
					//System.out.println(strT.substring(i+6,endI));
					//returnList.add(strT.substring(i+6,endI));
					if(returnList.size() >= limit)
						return returnList;
					
					i = i + strP.length();
					break;
				}else {
					i--;
					j--;
					continue;
				}
			}
		}
		//System.out.println("count:"+count);
		return returnList;
	}
    
    public int countKeyword(String keyword){	
		//To do a case-insensitive search, we turn the whole content and keyword into upper-case:
    	if(content == null)return 0;
		content = content.toUpperCase();
		keyword = keyword.toUpperCase();
	
		int retVal = 0;
		int fromIdx = 0;
		int found = -1;
		
		/*
		while ((found = content.indexOf(keyword, fromIdx)) != -1){
		    retVal++;
		    fromIdx = found + keyword.length();
		}
		*/
		return this.boyerMoore(content, keyword);
		//return retVal;
    }
}
