package Config;
import javax.xml.parsers.*;
import org.w3c.dom.*;

import java.io.*;

public class DOMXML {
	
	public DOMXML() {
	}
	
	static protected Document getDocument(String sFile) {
		  try {
		  	DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
		  	DocumentBuilder db=dbf.newDocumentBuilder();
		  	Document doc = db.parse(new File(sFile));
		  	return doc;
		  }catch(Exception e) {
		  	return null;
		  }
	}
	static protected Document getDocument(InputStream in) {
		  try {
		  	DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
		  	DocumentBuilder db=dbf.newDocumentBuilder();
		  	Document doc = db.parse(in);
		  	return doc;
		  }catch(Exception e) {
		  	return null;
		  }
	}	
	
}