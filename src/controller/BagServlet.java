package controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.*;
import javax.servlet.http.*;

import org.apache.catalina.connector.Response;

// TODO: this class should extends something to be a usable servlet.
// TODO: add an annotation here to map your servlet on an URL.
@WebServlet("/home")
public class BagServlet extends HttpServlet{
	
	
	@Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println( "In the service method" );
        super.service(req, resp);
    }
	
	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException{
		
	HttpSession session = req.getSession();
	System.out.println("in the do GET");
	String login = req.getParameter( "txtLogin" );
    String password = req.getParameter( "txtPassword" );
    if ( login == null ) login = "";
    if ( password == null ) password = "";
	res.setContentType("text/html");
	
		try (PrintWriter out = res.getWriter()){
		out.println("<!DOCTYPE html>");
		out.println("<html>");
		out.println("<head>");
		out.println("<title>Test</title>");
		out.println("</head>");
		out.println("<body>");
		out.println("<h1> Contenue du panier <h1/>");
		if(session.getAttribute("myBag")!=null) {
			Bag mynewBag = (Bag) session.getAttribute("myBag");
			mynewBag.print(out);
		}
		
		out.println("<form method='POST' action='home'>" );
		out.println("<label for='ref'>Ref</label>");
		out.println("<input type='text' id='ref' name='ref' /><br/>");
		out.println("<label for='qty'>Qty</label>");
		out.println( "<input type='text id='qty' name='qty' /><br/>");
		out.println( "<input type='submit' value='send' >");
		out.println( "</form>");
		out.println("</body>");
		out.println("</html>");

		}
		
	}

	
	
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
			doGet(req,res);
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
