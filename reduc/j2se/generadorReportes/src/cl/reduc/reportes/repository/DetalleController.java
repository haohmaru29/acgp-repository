package cl.reduc.reportes.repository;

import java.sql.ResultSet;

import cl.reduc.commons.database.oracle.OracleController;
import cl.reduc.reportes.utils.DateUtils;

public class DetalleController extends OracleController {
	
	public ResultSet porCr(String fechaDesde, String fechaHasta, String cr, String mes, String anio) {
		int diaDesde = Integer.parseInt(fechaDesde.substring(0, fechaDesde.indexOf("/")) );
		int mesDesde = Integer.parseInt(fechaDesde.substring(fechaDesde.indexOf("/")+1, fechaDesde.lastIndexOf("/")) );
		int anioDesde = Integer.parseInt(fechaDesde.substring(fechaDesde.lastIndexOf("/")+1, fechaDesde.length()) );
		int diaHasta = Integer.parseInt(fechaHasta.substring(0, fechaHasta.indexOf("/")) );
		int mesHasta = Integer.parseInt(fechaHasta.substring(fechaHasta.indexOf("/")+1, fechaHasta.lastIndexOf("/")) );
		int anioHasta = Integer.parseInt(fechaHasta.substring(fechaHasta.lastIndexOf("/")+1, fechaHasta.length()) );
		
		String sql = " SELECT DISTINCT oa.NUM_ORDEN_ATENCION as NUMERO_ORDEN_ATENCION, "
				   + " TO_CHAR(oa.FECHA_CREACION_OA,'dd/mm/yyyy') as FECHA_CREACION_ORDEN, "
				   + " TO_CHAR(oa.FECHA_PAGO_OA,'dd/mm/yyyy') as FECHA_PAGO_ORDEN, "
				   + " oa.OBSERVACION as OBSERVACION, "
				   + " oa.COD_SECCION_ORIGEN as COD_SECCION_ORIGEN, "
				   + " oa.COD_SECCION_INT as COD_SECCION_INT, "
				   + " oa.DES_LUGAR_PAGO as DES_LUGAR_PAGO, "
				   + " oa.TIPO_PACIENTE as TIPO_PACIENTE, " 
				   + " oa.TIPO_ATENCION as TIPO_ATENCION, "
				   + " oa.COD_CONVENIO as COD_CONVENIO, "
				   + " oa.DES_CONVENIO as DES_CONVENIO, "
				   + " oa.INSTITUCION_CONVENIO as INSTITUCION_CONVENIO, "
				   + " oa.RUT_PACIENTE as RUT_PACIENTE, "
				   + " oa.DIRECCION_PAC as DIRECCION_PACIENTE, "
				   + " oa.COMUNA_PAC as COMUNA_PACIENTE, " 
				   + " oa.TELEFONO_PAC as TELEFONO_PACIENTE, "
				   + " (oa.APA_PACIENTE || ' '|| oa.AMA_PACIENTE || ' ' || oa.NOM_PACIENTE ) as NOMBRE_PACIENTE,"  
				   
				   + " dt.RUT_PROF_RESERVA as RUT_MEDICO_DERIVADOR, " 
				   + " (dt.NOM_PROF_RESERVA || ' ' || dt.APA_PROF_RESERVA || ' ' || dt.AMA_PROF_RESERVA) as NOMBRE_MEDICO_DERIVADOR," 
				   
				   + " oa.COD_PREVISION as COD_PREVISION, "
				   + " oa.DES_PREVISION as DES_PREVISION, "
				   + " TO_CHAR(oa.FECHA_NACIMIENTO, 'dd/mm/yyyy') as FECHA_NACIMIENTO, "
				   + " oa.EDAD_PACIENTE as EDAD_PACIENTE, "
				   + " oa.SEXO_PACIENTE as SEXO_PACIENTE, "
				   //hasta aqui ORDEN ATENCION
				   + " oa.FECHA_PAGO_OA as FECHA_PAGO_ORDEN2,  " 
				   + " dt.COD_PRESTACION as CODIGO_PRESTACION," 
				   + " dt.DES_PRESTACION as DESCRIPCION_PRESTACION,"  
				   + " dt.TIPO_PRESTACION_INT as TIPO_PRESTACION_INT , "
				   + " dt.TIPO_PRESTACION_ORIGEN as TIPO_PRESTACION_ORIGEN , "
				   + " dt.COD_CENTRO_RESULTADO AS CODIGO_CENTRO_RESULTADO," 
				   + " dt.DES_CENTRO_RESULTADO as DESCRIPCION_CENTRO_RESULTADO," 
				   + " dt.COD_AGRUPADOR as CODIGO_AGRUPADOR," 
				   + " dt.VALOR_CONVENIO as VALOR_CONVENIO,"  
				   + " dt.VALOR_LISTA as VALOR_LISTA," 
				   + " dt.VALOR_PAGO as MONTO_PAGADO," 
				   + " dt.CANTIDAD_PRESTACION AS CANTIDAD_PRESTACION,"
				   + " dt.FOLIO_RESERVA AS FOLIO_RESERVA,"
				   
          		   + " oa.RUT_MED_DERIVADOR as RUT_MEDICO, " 
 				   + " (oa.NOM_MED_DERIVADOR || ' ' || oa.APA_MED_DERIVADOR || ' ' || oa.AMA_MED_DERIVADOR) as NOMBRE_MEDICO,"
				   
				   + " dt.ESPECIALIDAD as ESPECIALIDAD,"
				   + " dt.TIPO_CONSULTA as TIPO_CONSULTA, "
				   + " pf.COD_FONASA AS CODIGO_FONASA "
				   + " from ORDEN_ATENCION_AMB oa, DET_ORDEN_ATENCION_AMB dt, PRESTACIONES_FONASA_AMB pf "
				   + " WHERE oa.NUM_ORDEN_ATENCION = dt.NUM_ORDEN_ATENCION(+) "
				   + " AND oa.NUM_ORDEN_ATENCION=pf.NUM_ORDEN_ATENCION(+) " 
				   + " AND dt.COD_PRESTACION IS NOT NULL ";
					//+ "-- AND dt.ID_PRESTACION=PF.ID_PRESTACION ";
		
			if(cr.indexOf("TODOS") == -1) {
		       sql += " AND dt.DES_CENTRO_RESULTADO in (" + cr + ") " ;
			}
					
			sql += " AND  (";
			
			if(mesDesde == Integer.parseInt(mes) && anioDesde == Integer.parseInt(anio) ) {
				sql+=  " oa.FECHA_PAGO_OA between to_date('" + fechaDesde.replaceAll("/", "") + "','ddmmyyyy') ";  // 
			} else {
				sql+= " oa.FECHA_PAGO_OA between to_date('" + DateUtils.nextDay(diaDesde, mesDesde, anioDesde) + "','ddmmyyyy') ";//";
			} 
			sql += " AND to_date('" + DateUtils.nextDay(diaHasta, mesHasta, anioHasta) + "','ddmmyyyy')) ";
			sql += " AND  UPPER(oa.TIPO_ATENCION) = 'AMBULATORIO'  "
	             + " ORDER BY oa.NUM_ORDEN_ATENCION,FECHA_PAGO_ORDEN2 ";	        
			
		return select(sql);
	}
}