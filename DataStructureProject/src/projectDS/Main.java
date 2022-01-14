package projectDS;

import crawler_tree.WebNode;
import Google.GoogleQuery;
import rank.Sort;
import rank.WebsiteInfo;


import java.util.ArrayList;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map.Entry;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class TestProject
 */
@WebServlet("/Main")
public class Main extends HttpServlet {
	private static final long serialVersionUID = 1L;
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Main() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.setCharacterEncoding("UTF-8");
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html");
		if(request.getParameter("keyword")== null) {
			String requestUri = request.getRequestURI();
			request.setAttribute("requestUri", requestUri);
			request.getRequestDispatcher("apple1.jsp").forward(request, response);
			return;
		}
		GoogleQuery google = new GoogleQuery(request.getParameter("keyword"));
		int displayAmount;
		int searchDepth;
		int subWebsiteAmount;
		try {			
			displayAmount = Integer.parseInt(request.getParameter("displayAmount"));
			searchDepth = Integer.parseInt(request.getParameter("searchDepth"));
			subWebsiteAmount = Integer.parseInt(request.getParameter("subWebsiteAmount"));
			System.out.println(displayAmount+" "+searchDepth+" "+subWebsiteAmount);
		}catch(Exception e) {
			System.out.println("only number is allowed");
			displayAmount = 100;
			searchDepth = 0;
			subWebsiteAmount = 0;
		}
		WebNode.searchDepth = searchDepth;
		
		
		
		String[][] textArray = google.queryText();
		
		Sort sort = new Sort(textArray,subWebsiteAmount);
		//LinkedHashMap<String, String> query = sort.testReturn();
		ArrayList<WebsiteInfo> result = sort.getResult();
		
		
		String[][] s = new String[Math.min(displayAmount,result.size())][3];
		
		
		request.setAttribute("query", s);
		int num = 0;
		for(int i =0;i<Math.min(displayAmount,result.size());i++) {//only show limited search result
		    
		    s[i][0] = result.get(i).title;
		    s[i][1] = result.get(i).url;
		    s[i][2] = result.get(i).text;
		   
		}
		request.getRequestDispatcher("AppleItem.jsp")
		 .forward(request, response); 
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
