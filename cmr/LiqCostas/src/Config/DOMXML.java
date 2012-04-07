package Config;

import java.io.File;
import java.io.InputStream;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;

public class DOMXML {
	
	private static final DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
	
	protected static Document getDocument(String sFile) {
		  try {
		  	DocumentBuilder db=dbf.newDocumentBuilder();
		  	Document doc = db.parse(new File(sFile));
		  	return doc;
		  }catch(Exception e) {
		  	return null;
		  }
	}
	
	protected static Document getDocument(InputStream in) {
		  try {
		  	DocumentBuilder db=dbf.newDocumentBuilder();
		  	Document doc = db.parse(in);
		  	return doc;
		  }catch(Exception e) {
		  	return null;
		  }
	}	
	
}