package Google;
import java.io.BufferedReader;

import java.io.IOException;

import java.io.InputStream;

import java.io.InputStreamReader;

import java.io.File;  // Import the File class

import java.io.FileWriter;   // Import the FileWriter class

import java.net.URL;

import java.net.URLConnection;

import java.util.HashMap;

import java.util.LinkedHashMap;

import java.util.PriorityQueue;

import org.jsoup.Jsoup;

import org.jsoup.nodes.Document;

import org.jsoup.nodes.Element;

import org.jsoup.select.Elements;

import java.util.ArrayList;


public class GoogleQuery 

{

	public String searchKeyword;

	public String url;

	public String content;
	
//	public PriorityQueue<WebNode> heap;

	public GoogleQuery(String searchKeyword)

	{

		this.searchKeyword = searchKeyword;
		
		this.searchKeyword = this.plus();
		
		this.url = "http://www.google.com/search?q="+this.searchKeyword+"+水果+蘋果&oe=utf8&num=100";

	}

	private String plus() {
		ArrayList<String> keywordList = new ArrayList<String>();
		String[] splited = this.searchKeyword.split("\\s+");
		String returnString = "";
		for(String k:splited) {
			if(returnString.equals("")) {
				returnString = k;
			}else {
				returnString += ("+"+k);
			}
		}
		return returnString;
	}

	private String fetchContent() throws IOException

	{
		String retVal = "";

		URL u = new URL(url);

		URLConnection conn = u.openConnection();
		
		//"Chrome/7.0.517.44"
		conn.setRequestProperty("User-agent", "Chrome/97.0.4692.71");

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
	public LinkedHashMap<String, String> query() throws IOException

	{

		if(content==null)

		{

			content= fetchContent();

		}

		LinkedHashMap<String, String> retVal = new LinkedHashMap<String, String>();
		Document doc = Jsoup.parse(content);
		System.out.println(doc.text());
		Elements lis = doc.select("div");
//		 System.out.println(lis);
		lis = lis.select(".kCrYT");
		// System.out.println(lis.size());
		
		for(Element li : lis)
		{
			try 

			{
				String citeUrl = li.select("a").get(0).attr("href");
				String title = li.select("a").get(0).select(".vvjwJb").text();
				if(title.equals("")) {
					continue;
				}
				
				System.out.println(title + "			"+citeUrl);
				
				//"/url?q="prefix of url
				if(citeUrl.length() > 7) {
					if(citeUrl.substring(0,7).equals("/url?q=")) {
						citeUrl = citeUrl.substring(7,citeUrl.length());
					}
				}
				
				retVal.put(title, citeUrl);

			} catch (IndexOutOfBoundsException e) {

//				e.printStackTrace();

			}

			

		}

		

		return retVal;

	}
	public String[][] queryText() throws IOException

	{

		if(content==null)

		{

			content= fetchContent();

		}

		
		
		Document doc = Jsoup.parse(content);
		//System.out.println(doc.text());
		/*check fetch content page 
		try {
		      FileWriter myWriter = new FileWriter("filename.txt");
		      myWriter.write(content);
		      myWriter.close();
		      System.out.println("Successfully wrote to the file.");
		    } catch (IOException e) {
		      System.out.println("An error occurred.");
		      e.printStackTrace();
		    }
		*/
		Elements lis = doc.select("div");
		lis = lis.select(".ZINbbc");//*
		System.out.println(lis.size());
		
		
		/*".tF2Cxc" for select lis
		lis = lis.select(".tF2Cxc");
		 * 
		 * 
		String citeUrl = i.select("div").get(0).select("a").get(0).attr("href");
		String title =  i.select("div").get(0).select("a").get(0).select(".vvjwJb").text();
		String text = i.select("div").get(1).select("div").get(0).select("span").get(1).text();
		 * 
		 * 
		 * 
		 */
		//System.out.println(doc.select("div").select(".tF2Cxc").get(0).className());
		
		
		String[][] returnArray = new String[100][3];//notice some space may be empty due to exception

		int index = 0;
		for(Element i:lis)
		{
			
			try {
				
				String citeUrl = i.select("div").get(0).select("a").get(0).attr("href");
				String title =  i.select("div").get(0).select("a").get(0).select("h3").text();
				//System.out.println(title + "			"+citeUrl);
				
				//String text = i.select("div").get(2).select("div").get(0).select("div").get(0).select("div").get(0).select("div").get(0).select("div").get(0).text();
				String text = i.select("div.BNeawe > div > div > div.BNeawe").text();
				
				//System.out.println("-------------"+text);
				/*
				System.out.println("*"+i.select("div").get(2));
				System.out.println("&"+i.select("div").get(0));
				System.out.println("$"+i.select("div").size());*/
				
				if(citeUrl.equals("") || title.equals("")) {
					//System.out.println(title + "			"+citeUrl+"		"+text);
					continue;
				}
				if(citeUrl == null || title == null) {
					
					continue;
				}
				if(text.equals("") || text == null) {
					System.out.println("null???: "+title);
					continue;
				}
				
				
				//"/url?q="prefix of url
				if(citeUrl.length() > 7) {
					if(citeUrl.substring(0,7).equals("/url?q=")) {
						citeUrl = citeUrl.substring(7,citeUrl.length());
					}
				}
				returnArray[index][0] = title;
				returnArray[index][1] = citeUrl;
				returnArray[index][2] = text;
				index++;
				
				//System.out.println(title + "			"+citeUrl+"\n"+text);

			} catch (IndexOutOfBoundsException e) {
				System.out.println(e.getMessage());
//				e.printStackTrace();

			}catch(Exception e) {
				System.out.println(e.getMessage());
			}

			

		}

		

		return returnArray;

	}
	

	

}