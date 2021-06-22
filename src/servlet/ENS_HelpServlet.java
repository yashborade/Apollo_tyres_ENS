package servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.json.JSONArray;

import beans.UserBean;
import dao.ENS_AutoCompleteDao;
import mapper.ENS_AutoCompleteMapper;


@WebServlet("/ENS/ENS_HelpServlet")
public class ENS_HelpServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
  
    public ENS_HelpServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		HttpSession session = request.getSession(false);
		UserBean users = (UserBean) session.getAttribute("Users");
		
		String param = request.getParameter("field");
		//System.out.println("field Name :" +param);
		String arg[] = param.split("\\?");
		//System.out.println("field Name :" +param);
		
		String field = arg[0];
		String q = request.getParameter("term");
		
		//System.out.println("field another : " + field);
		//System.out.println("q : " + q);
		
		 List<String> search = new ArrayList<String>();
		 
		 if(field.equalsIgnoreCase("searchNode"))
		 {
			 try 
				{
					ENS_AutoCompleteDao mapAutoNode = new ENS_AutoCompleteMapper();
					search = mapAutoNode.getS_Node(request,users);
				} 
				catch (Exception e) 
				{
					e.printStackTrace();
				}
		 }
		 else if(field.equalsIgnoreCase("search_area_Equip"))
		 {
			 try{
				 
				 ENS_AutoCompleteDao mapAutoEquip = new ENS_AutoCompleteMapper();
				 search = mapAutoEquip.getAreaEquip(request, users);
				 
			 }
			 catch(Exception e)
			 {
				 e.printStackTrace();
			 }
			 
		 }
		 else if(field.equalsIgnoreCase("search_callNode"))
		 {
			 try{
				 
				 ENS_AutoCompleteDao mapAutoEquip = new ENS_AutoCompleteMapper();
				 search = mapAutoEquip.callNode(request, users);
				 
			 }
			 catch(Exception e)
			 {
				 e.printStackTrace();
			 }
			 
		 }
		 else if(field.equalsIgnoreCase("search_CalEquip"))
		 {
			 try{
				 
				 ENS_AutoCompleteDao mapAutoEquip = new ENS_AutoCompleteMapper();
				 search = mapAutoEquip.getCalEquip(request, users);
				 
			 }
			 catch(Exception e)
			 {
				 e.printStackTrace();
			 }
			 
		 }
		 else if(field.equalsIgnoreCase("search_area"))
		 {
			 try{
				 
				 ENS_AutoCompleteDao mapAutoEquip = new ENS_AutoCompleteMapper();
				 search = mapAutoEquip.getArea(request, users);
				 
			 }
			 catch(Exception e)
			 {
				 e.printStackTrace();
			 }
			 
		 }
		 else if(field.equalsIgnoreCase("search_area1"))
		 {
			 try{
				 
				 ENS_AutoCompleteDao mapAutoEquip = new ENS_AutoCompleteMapper();
				 search = mapAutoEquip.getArea1(request, users);
				 
			 }
			 catch(Exception e)
			 {
				 e.printStackTrace();
			 }
			 
		 }
		 
		 else if(field.equalsIgnoreCase("search_equip"))
		 {
			 try{
				 
				 ENS_AutoCompleteDao mapAutoEquip = new ENS_AutoCompleteMapper();
				 search = mapAutoEquip.getGroup(request, users);
				 
			 }
			 catch(Exception e)
			 {
				 e.printStackTrace();
			 }
			 
		 }
		 else if(field.equalsIgnoreCase("search_equip1"))
		 {
			 try{
				 
				 ENS_AutoCompleteDao mapAutoEquip = new ENS_AutoCompleteMapper();
				 search = mapAutoEquip.getEquip1(request, users);
				 
			 }
			 catch(Exception e)
			 {
				 e.printStackTrace();
			 }
			 
		 }
		  
		 else if (field.equalsIgnoreCase("search"))
			{
				 //System.out.println("In autocomplete Search Size action Servlet");
				try 
				{
					ENS_AutoCompleteDao mapAutoNode = new ENS_AutoCompleteMapper();
					search = mapAutoNode.getNode(request,users);
				} 
				catch (Exception e) 
				{
					e.printStackTrace();
				}
			}
		  
		  else if(field.equalsIgnoreCase("search_group"))
		  {
			  
			  try
			  {
				  ENS_AutoCompleteDao mapAutoNode = new ENS_AutoCompleteMapper();
				  search = mapAutoNode.getEquip(request, users);
				  
			  }
			  catch(Exception e)
			  {
				  e.printStackTrace();
			  }
		  }
		  else if(field.equalsIgnoreCase("search_mach"))
		  {
			  
			  try
			  {
				  ENS_AutoCompleteDao mapAutoNode = new ENS_AutoCompleteMapper();
				  search = mapAutoNode.getMach(request, users);  
			  }
			  catch(Exception e)
			  {
				  e.printStackTrace();
			  }
		  }
		  else if(field.equalsIgnoreCase("search_group_plus"))
		  {
			  
			  try
			  {
				  ENS_AutoCompleteDao mapAutoNode = new ENS_AutoCompleteMapper();
				  search = mapAutoNode.getGroup_mach(request, users);  
			  }
			  catch(Exception e)
			  {
				  e.printStackTrace();
			  }
		  }
		  else if(field.equalsIgnoreCase("search_m_node"))
		  {
			  
			  try
			  {
				  ENS_AutoCompleteDao mapAutoNode = new ENS_AutoCompleteMapper();
				  search = mapAutoNode.getm_node(request, users);
			  }
			  catch(Exception e)
			  {
				  e.printStackTrace();
			  }
		  }
		  
		  
			
		  //JSON Array
		  JSONArray arrayObj = new JSONArray();
			 if(search.isEmpty())
				{
					arrayObj.put("No Match Found");
				}
				else if(search != null)
				{
					System.out.println(" Search List is not null....:)");
					for (String string : search) {
						arrayObj.put(string);						
					}
				}
				PrintWriter out=response.getWriter();
				out.println(arrayObj.toString());
			  	out.close();
			  	
		  	/* System.out.println("Inside Help servlet-------------");
			 String []keys = request.getParameterValues("keyword");
				for(String key : keys)
				{
					System.out.println("Keyword in servlet = " + key);
				}*/
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
