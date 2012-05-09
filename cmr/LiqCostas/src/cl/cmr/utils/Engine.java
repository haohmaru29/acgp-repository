package cl.cmr.utils;

import com.lowagie.text.DocumentException;
import com.lowagie.text.FontFactory;
import com.lowagie.text.Image;
import com.lowagie.text.Phrase;
import com.lowagie.text.pdf.BarcodeInter25;
import com.lowagie.text.pdf.PdfContentByte;
import com.lowagie.text.pdf.PdfPCell;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;
import java.awt.Color;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.StringReader;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.FactoryConfigurationError;
import javax.xml.parsers.ParserConfigurationException;

import org.apache.log4j.Logger;
import org.w3c.tidy.Tidy;
import org.xhtmlrenderer.pdf.ITextRenderer;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

public class Engine {

	private StringBuilder urlBuffer;
	private HttpServletRequest request;
	private HttpServletResponse response;
	private static final Logger logger = Logger.getLogger(Engine.class);
	static {
		AppenderUtils.getInstance().setParams(logger);
    }

	public Engine(HttpServletRequest request, HttpServletResponse response) {
		this.urlBuffer = new StringBuilder();

		this.request = request;
		this.response = response;

		this.urlBuffer.append(request.getScheme().concat("://"));
		this.urlBuffer.append(request.getServerName().concat(":"));
		this.urlBuffer.append(request.getServerPort());
		this.urlBuffer.append(request.getContextPath().concat("/"));
	}

	private ServletOutputStream create(String xhtml, ServletOutputStream out) {
		ITextRenderer renderer = new ITextRenderer();
		try {
			DocumentBuilder builder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
			org.w3c.dom.Document doc = builder.parse(new InputSource(new StringReader(xhtml)));
			renderer.setDocument(doc, this.urlBuffer.toString());
			renderer.layout();
			try {
				renderer.createPDF(out, false);
				renderer.finishPDF();
			} catch (DocumentException e) {
				logger.error("[LIQCOSTAS]", e);
			}
		} catch (ParserConfigurationException e) {
			logger.error("[LIQCOSTAS]", e);
		} catch (FactoryConfigurationError e) {
			logger.error("[LIQCOSTAS]", e);
		} catch (SAXException e) {
			logger.error("[LIQCOSTAS]", e);
		} catch (IOException e) {
			logger.error("[LIQCOSTAS]", e);
		}

		return out;
	}

	private String buildXHTML(String content, String path) throws IOException {
		StringBuffer sb = new StringBuffer("<!Doctype><html><head>");
		sb.append("").append(content).append("</body></html>");
		String raw = sb.toString();
		String url = path.substring(0,
				path.indexOf('/', "http://".length() + 1) + 1);
		raw = raw.replaceAll("src=\"/", "src=\"" + url);
		InputStream rawis = new ByteArrayInputStream(raw.getBytes("iso-8859-1"));

		Tidy tidy = new Tidy();
		tidy.setXHTML(true);
		tidy.setDocType("omit");
		tidy.setMakeClean(true);

		ByteArrayOutputStream os = new ByteArrayOutputStream();
		tidy.parse(rawis, os);
		String out = new String(os.toByteArray());
		return out;
	}

	public OutputStream createImage(String encoding, ServletOutputStream out) {
		String fechaRemesa = this.request.getParameter("fechaRemesa");
		String nombreRemesa = this.request.getParameter("nombreRemesa");
		String montoRemesa = this.request.getParameter("montoRemesa");
		String tipoProducto = this.request.getParameter("tipoProducto");
		com.lowagie.text.Document document = new com.lowagie.text.Document();
		try {
			PdfWriter writer = PdfWriter.getInstance(document, out);
			writer.open();
			PdfContentByte cb = writer.getDirectContent();
			document.open();
			PdfPTable table1 = new PdfPTable(1);
			PdfPCell cell1 = new PdfPCell(new Phrase("CÚPON DE PAGO",FontFactory.getFont("Helvetica-Bold", 10.0F)));
			cell1.setBorder(0);
			cell1.setHorizontalAlignment(1);
			table1.addCell(cell1);
			float[] colsWidth = { 1.0F, 2.0F };
			PdfPTable table = new PdfPTable(colsWidth);
			PdfPCell cell = new PdfPCell(new Phrase("Fecha",FontFactory.getFont("Helvetica-Bold", 10.0F)));
			cell.setBorder(0);
			table.addCell(cell);
			cell = new PdfPCell(new Phrase(fechaRemesa, FontFactory.getFont("Helvetica-Bold", 10.0F)));
			cell.setBorder(0);
			table.addCell(cell);

			if ("5".equals(tipoProducto))
				cell = new PdfPCell(new Phrase("Nombre agencia",FontFactory.getFont("Helvetica-Bold", 10.0F)));
			else {
				cell = new PdfPCell(new Phrase("Nombre Abogado",FontFactory.getFont("Helvetica-Bold", 10.0F)));
			}
			cell.setBorder(0);
			cell.setBorderWidth(20.0F);
			table.addCell(cell);
			cell = new PdfPCell(new Phrase(nombreRemesa, FontFactory.getFont("Helvetica-Bold", 10.0F)));
			cell.setBorder(0);
			table.addCell(cell);

			cell = new PdfPCell(new Phrase("Monto $", FontFactory.getFont("Helvetica-Bold", 10.0F)));
			cell.setBorder(0);
			cell.setBorderWidth(20.0F);
			table.addCell(cell);
			cell = new PdfPCell(new Phrase(montoRemesa, FontFactory.getFont("Helvetica-Bold", 10.0F)));
			cell.setBorder(0);
			table.addCell(cell);

			cell = new PdfPCell(new Phrase("N° Remesa", FontFactory.getFont( "Helvetica-Bold", 10.0F)));
			cell.setBorder(0);
			cell.setBorderWidth(20.0F);
			table.addCell(cell);
			cell = new PdfPCell(new Phrase(encoding, FontFactory.getFont(
					"Helvetica-Bold", 10.0F)));
			cell.setBorder(0);
			table.addCell(cell);

			BarcodeInter25 barcode = new BarcodeInter25();
			barcode.setBarHeight(27.0F);
			barcode.setSize(0.0F);
			barcode.setN(4.7F);
			barcode.setX(0.9F);
			barcode.setTextAlignment(1);
			barcode.setChecksumText(false);
			barcode.setGenerateChecksum(false);
			barcode.setCode("0" + encoding + "0");
			Image imgShipBarCode = barcode.createImageWithBarcode(cb, Color.black, Color.black);
			imgShipBarCode.setAlignment(1);
			document.add(table1);

			document.add(new Phrase(""));
			document.add(new Phrase(""));

			document.add(table);

			document.add(new Phrase(""));
			document.add(new Phrase(""));
			document.add(new Phrase(""));

			document.add(imgShipBarCode);
			document.close();
		} catch (DocumentException e) {
			logger.error("[LIQCOSTAS]", e);
		}

		return out;
	}

	public void renderDocument() {
		String content = this.request.getParameter("content");
		try {
			String xhtml = buildXHTML(content, this.request.getRequestURL().toString());
			String nameFile = this.request.getParameter("title");
			if (("".equals(nameFile)) || (nameFile == null)) {
				nameFile = "DocumentoPDF";
			}

			this.response.setHeader("Expires", "0");
			this.response.setHeader("Cache-Control", "must-revalidate, post-check=0, pre-check=0");
			this.response.setHeader("Pragma", "public");
			this.response.setContentType("application/pdf");
			this.response.setHeader("Content-Disposition", "attachment;fileName=" + nameFile + ".pdf");
			ServletOutputStream outPdf = create(xhtml, this.response.getOutputStream());

			outPdf.flush();
			outPdf.close();
		} catch (IOException e) {
			logger.error("[LIQCOSTAS]", e);
		}
	}

	public void openerDocument() {
		String content = this.request.getParameter("content");
		String encoding = this.request.getParameter("numeroOperacion");
		try {
			String xhtml = buildXHTML(content, this.request.getRequestURL().toString());
			String nameFile = this.request.getParameter("title");
			if (("".equals(nameFile)) || (nameFile == null)) {
				nameFile = "DocumentoPDF";
			}

			this.response.setHeader("Expires", "0");
			this.response.setHeader("Cache-Control", "must-revalidate, post-check=0, pre-check=0");
			this.response.setHeader("Pragma", "public");
			this.response.setContentType("application/pdf");
			this.response.setHeader("Content-Disposition", "inline;fileName=" + nameFile + ".pdf");

			OutputStream outPdf = create(xhtml, this.response.getOutputStream(), encoding);

			outPdf.flush();
			outPdf.close();
		} catch (IOException e) {
			logger.error("[LIQCOSTAS]", e);
		}
	}

	private OutputStream create(String xhtml, ServletOutputStream out, String encoding) {
		ITextRenderer renderer = new ITextRenderer();
		try {
			DocumentBuilder builder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
			org.w3c.dom.Document doc = builder.parse(new InputSource(new StringReader(xhtml)));
			renderer.setDocument(doc, this.urlBuffer.toString());
			renderer.layout();
			try {
				renderer.createPDF(out, false);
				createImage(encoding, out);
				renderer.finishPDF();
			} catch (DocumentException e) {
				logger.error("[LIQCOSTAS]", e);
			}
		} catch (ParserConfigurationException e) {
			logger.error("[LIQCOSTAS]", e);
		} catch (FactoryConfigurationError e) {
			logger.error("[LIQCOSTAS]", e);
		} catch (SAXException e) {
			logger.error("[LIQCOSTAS]", e);
		} catch (IOException e) {
			logger.error("[LIQCOSTAS]", e);
		}

		return out;
	}

	public void openerDocument(String content) {
		String encoding = this.request.getParameter("numeroOperacion");
		String html ="pdf";
		try {
			String xhtml = buildXHTML(html, this.request.getRequestURL().toString());
			String nameFile = this.request.getParameter("title");
			if (("".equals(nameFile)) || (nameFile == null)) {
				nameFile = "DocumentoPDF";
			}

			this.response.setHeader("Expires", "0");
			this.response.setHeader("Cache-Control","must-revalidate, post-check=0, pre-check=0");
			this.response.setHeader("Pragma", "public");
			this.response.setContentType("application/pdf");
			this.response.setHeader("Content-Disposition", "inline;fileName=" + nameFile + ".pdf");
			OutputStream outPdf = create(xhtml, this.response.getOutputStream(), encoding);
			outPdf.flush();
			outPdf.close();
		} catch (IOException e) {
			logger.error("[LIQCOSTAS]", e);
		}
	}
}
