package Config;
import java.io.InputStream;

import org.w3c.dom.*;

public class ConfigXMLHandler {
	private String sFile;
	  private ConfigItem actual; 
	
	public ConfigXMLHandler(String sFile) {
		this.sFile = sFile;
		this.actual = new ConfigItem();
	}
	public ConfigXMLHandler() {
		this.actual = new ConfigItem();
	}
	
	public void setFileXML(String sFile) {
		this.sFile = sFile;
	}
	public String getFileXML(){
		return this.sFile;
	}
	
	public void parseXML() {
		Document doc = DOMXML.getDocument(this.sFile);
		traverseTree(doc);
	}

	public void parseXML(InputStream in) {
		Document doc = DOMXML.getDocument(in);
		traverseTree(doc);
	}

	public ConfigItem getObjectConfig() {
		return this.actual;
	}
	
	private void traverseTree(Node node) {
		traverseTree(node,null);
	}
	private void traverseTree(Node node,ConfigItem oCfg) {
		if (node==null)
			return;		
		int iTipo = node.getNodeType();
		switch(iTipo) {
			case Node.TEXT_NODE:
				//String sName1 = node.getNodeName().toLowerCase().trim();
				break;
			case Node.DOCUMENT_NODE: {
			     traverseTree(((Document)node).getDocumentElement());
			     break;
			}
			case Node.ELEMENT_NODE: {
				String sName = node.getNodeName().toLowerCase().trim();
				NamedNodeMap map = node.getAttributes();
				NodeList childNodes = node.getChildNodes();
				if(sName.toUpperCase().equals("MENU")) {
					if (node.hasAttributes()) {
						ConfigItem ci = new ConfigItem();
						ci.setNombre((map.getNamedItem("nombre")!=null?map.getNamedItem("nombre").getNodeValue():""));
						ci.setPagina((map.getNamedItem("pagina")!=null?map.getNamedItem("pagina").getNodeValue():""));
						ci.setDescripcion((map.getNamedItem("descripcion")!=null?map.getNamedItem("descripcion").getNodeValue():""));
						ci.setFuncionalidad((map.getNamedItem("funcion")!=null?map.getNamedItem("funcion").getNodeValue():""));
						if (childNodes!=null) {
							for (int i=0;i<childNodes.getLength();i++){
								traverseTree(childNodes.item(i),ci);
							}
						}
						if (oCfg!=null)
							oCfg.addMenu(ci);
						else
							actual = ci;
					}	
				}
				if (childNodes!=null && !sName.equals("menu")) {
					for (int i=0;i<childNodes.getLength();i++){
						traverseTree(childNodes.item(i));
					}
				}				
				break;
			}
		}
	}
}

