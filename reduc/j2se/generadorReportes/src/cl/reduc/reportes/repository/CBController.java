package cl.reduc.reportes.repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import cl.reduc.commons.database.oracle.OracleController;

public class CBController extends OracleController {
	
	private List response;
	
	public List getEspecialidades() {
		response = new ArrayList();
        ResultSet rs = select("SELECT DISTINCT DES_FLEX from ESPECIALID ORDER BY DES_FLEX ASC");
        try {
            response.add("TODOS");
            String value = "";
            while (rs.next()) {
                value = rs.getString("DES_FLEX");
                if (!value.equals("") || value != null) {
                    response.add(value);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
        	closeResultSet(rs);
        }

        return response;
    }
	
	public List getSucursales() {
		response = new ArrayList();
        ResultSet rs = select("SELECT DISTINCT SECCION FROM ESPECIALIDAD_RECLASIF ORDER BY SECCION ASC");
        try {
            response.add("TODOS");
            String seccion="";
            while(rs.next()) {
                seccion = rs.getString("SECCION");
                if(seccion == null ) {
                	response.add("No hay datos");
                } else if(!seccion.equals("") ) {
                    response.add(seccion);
                }
            }
        } catch (SQLException e) {
        	e.printStackTrace();
        } finally {
        	closeResultSet(rs);
        }
        
        return response;
    }
	
	public List getCr() {
		response = new ArrayList();
        ResultSet rs = select("SELECT DISTINCT COD_CENTRO_RESULTADO, DES_CENTRO_RESULTADO FROM DET_ORDEN_ATENCION_AMB WHERE COD_CENTRO_RESULTADO IS NOT NULL  ORDER BY COD_CENTRO_RESULTADO ASC"); 
        		//select("select distinct cr.DES_FLEX from CENRES cr, DETALLE_ORDEN_ATENCION dt where cr.SINONIMO = dt.ID_CENTRO_RESULTADO ORDER BY cr.DES_FLEX ASC");
        try {
            response.add("TODOS");
            String seccion="";
            String cod_seccion = "";
            while(rs.next()) {
                seccion = rs.getString("DES_CENTRO_RESULTADO");
                cod_seccion = rs.getString("COD_CENTRO_RESULTADO");
                if(seccion!=null ) {
                	if(!seccion.equals(""))
                		response.add(cod_seccion + " - " + seccion);
                }
            }
        } catch (SQLException e) {
        	e.printStackTrace();
        } finally {
        	closeResultSet(rs);
        }
        
        return response;
		
	}
}
