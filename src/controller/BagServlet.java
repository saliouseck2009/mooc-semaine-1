package controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.*;
import javax.servlet.http.*;


// TODO: this class should extends something to be a usable servlet.
// TODO: add an annotation here to map your servlet on an URL.
@WebServlet("/home")
public class BagServlet extends HttpServlet{

	public static String jspView = "/WEB-INF/test.jsp";
	
	
//	@Override
//    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        System.out.println( "In the service method" );
//        super.service(req, resp);
//    }
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException{
		this.getServletContext().getRequestDispatcher(jspView).forward(req, res);
	}


	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse res) 
	throws ServletException, IOException {
		HttpSession session = req.getSession();
		if (session.getAttribute("myBag") == null) {
			Bag myBag = new Bag();
			session.setAttribute("myBag", myBag);
		}
		Bag myBag = (Bag) session.getAttribute("myBag");
		
		
		String ref = req.getParameter("ref");
		String qty = req.getParameter("qty");
		
		if(ref.isBlank() || qty.isBlank()) {
			try (PrintWriter out = res.getWriter()){
				out.println("Veillez renseigner tous les champs");
			}
		}
		
		try {
			
			myBag.setItem(ref, Integer.parseInt(qty));
			req.setAttribute("myBag",myBag);
			this.getServletContext().getRequestDispatcher(jspView).forward(req, res);
		} catch (NumberFormatException e) {
			e.getMessage();
			doGet(req,res);
			
		}
		
		
		System.out.println("in the doPost");
		res.setContentType("text/html");
		try (PrintWriter out = res.getWriter()){
			out.println("ref: "+req.getParameter("ref"));
			out.println("qte: "+ req.getParameter("qty"));
		}
		
		
		
	

	}
	
	
	

}
