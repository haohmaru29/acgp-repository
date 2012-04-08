package Seguridad;

import java.io.IOException;
import java.io.InputStream;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.naming.NamingException;

import org.apache.log4j.Logger;
import Config.ConfigItem;
import Config.ConfigXMLHandler;
import Proc.Login;

public class Menu {

	private static final Logger logger = Logger.getLogger(Menu.class);
	private ArrayList<String> hm = new ArrayList<String>();
	private static InputStream in;

	public ConfigItem ProcesaXml() {
		try {
			in = Thread.currentThread().getContextClassLoader().getResourceAsStream("/Menu.xml");
			ConfigXMLHandler oHand = new ConfigXMLHandler();
			oHand.parseXML(in);
			in.close();
			return oHand.getObjectConfig();
		} catch (Exception e) {
			logger.error("[ LiqCostas ]", e);
		}
		return null;
	}

	@SuppressWarnings("unchecked")
	public ArrayList<ArrayList<String>> obtieneMenuUsuario(String sUsuario) throws SQLException, IOException, NamingException {
		ArrayList<ArrayList<String>> resultado = new ArrayList<ArrayList<String>>();
		try {
			Login ObjProc = new Login();
			resultado = new ArrayList<ArrayList<String>>();
			ObjProc.ObtieneMenuUsuario(sUsuario, resultado);

		} catch (Exception e) {
			logger.error("[LiqCostas]", e);
		}
		return (ArrayList<ArrayList<String>>) resultado.clone();
	}

	public String genMenu(ConfigItem obj) {
		String sMenu = "";
		if (obj == null)
			return "[]";

		ArrayList<?> arrItems = (ArrayList<?>) obj.getArrayMenus();
		if (arrItems.size() > 0) {
			for (int i = 0; i < arrItems.size(); i++) {
				if (i > 0)
					sMenu += ",";
				obj = (ConfigItem) arrItems.get(i);
				sMenu += "new mnuMenu('" + obj.getDescripcion() + "','"
						+ obj.getPagina() + "'," + (arrItems.size() == 0) + ","
						+ genMenu(obj) + ")";
			}
			sMenu = "[" + sMenu + "]";

		} else
			sMenu = "[]";

		return sMenu;
	}

	private ConfigItem getMenu(ConfigItem obj) {
		ArrayList<?> arrObj = obj.getArrayMenus();
		ConfigItem oItem = null;
		if (arrObj.size() <= 0) {// nodo hoja
			if (hm.contains(obj.getFuncionalidad().trim()))
				oItem = obj;
		} else {
			ArrayList<ConfigItem> arrNvo = new ArrayList<ConfigItem>(); // contiene solo los que tienen
												// permiso
			for (int i = 0; i < arrObj.size(); i++) {
				ConfigItem ci = getMenu((ConfigItem) arrObj.get(i));
				if (ci != null)
					arrNvo.add(ci);
			}
			if (arrNvo.size() > 0) {
				obj.setArrayMenu(arrNvo);
				oItem = obj;
			} else
				oItem = null;

		}

		return oItem;
	}

	public ConfigItem getAccesoMenu(String sUsuario) {
		ConfigItem ci = new ConfigItem();

		try {
			ArrayList<ArrayList<String>> arrMenu = obtieneMenuUsuario(sUsuario);
			hm.add("0"); // siempre se muestra
			for (int i = 0; i < arrMenu.size(); i++) {
				ArrayList<?> arrPaso = new ArrayList<Object>();
				arrPaso = (ArrayList<?>) arrMenu.get(i);
				String sFunc = arrPaso.get(1).toString().trim();
				if (!hm.contains(sFunc))
					hm.add(sFunc);
			}

			ConfigItem oItem = getMenu(ProcesaXml());

			ci = oItem;

		} catch (Exception e) {
			logger.error("[LiqCostas]", e);
		}
		return ci;
	}

}
