package utils;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class PdfEngine extends HttpServlet {
	private static final long serialVersionUID = 1L;

	
	public PdfEngine() {
		super();
	}

	public void destroy() {
		super.destroy(); 
	}

	public void doGet(HttpServletRequest request, HttpServletResponse response){
		proccessRequest(request, response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response) {
		proccessRequest(request, response);
	}

	public void proccessRequest(HttpServletRequest request, HttpServletResponse response ) {
		try {
			cl.acgp.pdf.PdfEngine pdf = new cl.acgp.pdf.PdfEngine(request, response);
			pdf.openerDocument();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public void init() throws ServletException {
	}

}
