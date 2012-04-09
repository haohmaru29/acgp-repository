package utils;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

public class PdfEngine extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private static final Logger logger = Logger.getLogger(PdfEngine.class);
	
	
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
			logger.info("[ LiqCosas ][ Creando PDF ]");
			Engine pdf = new Engine(request, response);
			pdf.openerDocument("");
			logger.info("[ LiqCosas ][ PDF Generado]");
		} catch(Exception e) {
			logger.error("[LiqCostas]", e );
		}
	}
	
	public void init() throws ServletException {
	}

}
