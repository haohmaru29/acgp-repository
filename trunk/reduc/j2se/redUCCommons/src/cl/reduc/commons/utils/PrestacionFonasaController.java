package cl.reduc.commons.utils;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import cl.reduc.commons.database.oracle.OracleController;

public class PrestacionFonasaController extends OracleController {
	
	private List response;
	
	public List getPrestacionesFonasa(String NUM_ORDEN_ATENCION) {
		String sql = "select COD_FONASA from PRESTACIONES_FONASA_AMB where NUM_ORDEN_ATENCION= " + NUM_ORDEN_ATENCION;
		
		ResultSet rs2 = select(sql);
		try {
			while(rs2.next()) {
				response.add(rs2.getString("COD_FONASA"));
			}
		} catch(SQLException e) {
			e.printStackTrace();
		} finally {
			closeResultSet(rs2);
		}
		
		return response;
	}

	public List findAllPrestaciones(String fechaDesde, String fechaHasta, String mes, String anio ) {
		int diaDesde = Integer.parseInt(fechaDesde.substring(0, fechaDesde.indexOf("/")) );
		int mesDesde = Integer.parseInt(fechaDesde.substring(fechaDesde.indexOf("/")+1, fechaDesde.lastIndexOf("/")) );
		int anioDesde = Integer.parseInt(fechaDesde.substring(fechaDesde.lastIndexOf("/")+1, fechaDesde.length()) );
		int diaHasta = Integer.parseInt(fechaHasta.substring(0, fechaHasta.indexOf("/")) );
		int mesHasta = Integer.parseInt(fechaHasta.substring(fechaHasta.indexOf("/")+1, fechaHasta.lastIndexOf("/")) );
		int anioHasta = Integer.parseInt(fechaHasta.substring(fechaHasta.lastIndexOf("/")+1, fechaHasta.length()) );
		if(response == null) {
			response = new ArrayList();
			System.out.println("Todas las prestacionesFonasa");
			String sql = "select p.NUM_ORDEN_ATENCION,p.CORRELATIVO_PRESTACION,p.ID_PRESTACION,p.COD_FONASA from PRESTACIONES_FONASA_AMB p,ORDEN_ATENCION_AMB oa "; 
			sql += " where p.NUM_ORDEN_ATENCION=oa.NUM_ORDEN_ATENCION AND (";
			if(mesDesde == Integer.parseInt(mes) && anioDesde == Integer.parseInt(anio) ) {
				sql+=  " oa.FECHA_PAGO_OA between to_date('" + fechaDesde.replaceAll("/", "") + "','ddmmyyyy') ";  // 
			} else {
				sql+= " oa.FECHA_PAGO_OA between to_date('" + DateUtils.nextDay(diaDesde, mesDesde, anioDesde) + "','ddmmyyyy') ";//";
			} 
			sql += " AND to_date('" + DateUtils.nextDay(diaHasta, mesHasta, anioHasta) + "','ddmmyyyy')) ";
					
			ResultSet rs = select(sql);
			String campo = "";
			int cont = 0;
			try {
				while(rs.next()) {
					System.out.println(cont++ );
					campo = rs.getString("NUM_ORDEN_ATENCION") + ";" + rs.getString("ID_PRESTACION") + ";" + rs.getString("COD_FONASA");
					response.add(campo);
				}
			} catch(SQLException e ) {
				e.printStackTrace();
			} finally {
				closeResultSet(rs);
			}
		}
		
		return response;
	}
	
	public List validaNumAtencionFonasa(String numOrdenAtencion, List list) {
		List response = new ArrayList();
		Iterator it = list.iterator();
		String[] values; 
		String value ="";
		while(it.hasNext()) {
			value = (String) it.next();
			values = value.split(";");
			//System.out.println(values[0]);
			if(values[0].toString().equals(numOrdenAtencion)) {
				response.add(values[2].toString());
			}
		}
		
		return response;
	}
}
