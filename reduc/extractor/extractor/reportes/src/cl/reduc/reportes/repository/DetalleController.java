package cl.reduc.reportes.repository;
import cl.reduc.commons.mvc.controller.AbstractJpaController;
import cl.reduc.commons.utils.DateUtils;

import cl.reduc.commons.utils.StringUtils;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import org.apache.log4j.Logger;

public class DetalleController extends AbstractJpaController {
    
    private static final Logger logger = Logger.getLogger(DetalleController.class);
    
    private String getEncabezado() {
        return " SELECT DISTINCT oa.NUM_ORDEN_ATENCION as NUMERO_ORDEN_ATENCION, "
				   + " TO_CHAR(oa.FECHA_CREACION_OA,'dd/mm/yyyy') as FECHA_CREACION_ORDEN, "
				   + " TO_CHAR(oa.FECHA_PAGO_OA,'dd/mm/yyyy') as FECHA_PAGO_ORDEN, "
				   + " dt.COD_CENTRO_RESULTADO AS CODIGO_CENTRO_RESULTADO," 
				   + " dt.DES_CENTRO_RESULTADO as DESCRIPCION_CENTRO_RESULTADO," 
				   + " dt.COD_AGRUPADOR as CODIGO_AGRUPADOR, " 
				   + " dt.COD_PRESTACION as CODIGO_PRESTACION," 
				   + " dt.DES_PRESTACION as DESCRIPCION_PRESTACION," 
				   + " dt.TIPO_PRESTACION_ORIGEN as TIPO_PRESTACION_ORIGEN , "
				   + " dt.TIPO_PRESTACION_INT as TIPO_PRESTACION_RED , "
				   + " dt.TIPO_CONSULTA as TIPO_CONSULTA, "
				   + " dt.ESPECIALIDAD as ESPECIALIDAD_RED, "
				   
				   + " dt.VALOR_LISTA as VALOR_LISTA, " 
				   + " dt.VALOR_CONVENIO as VALOR_CONVENIO, "  
				   + " dt.VALOR_PAGO as MONTO_PAGADO, " 
				   + " dt.CANTIDAD_PRESTACION AS CANTIDAD_PRESTACION, "
				   + " oa.COD_SECCION_ORIGEN as COD_SECCION_ORIGEN, "
				   + " dt.COD_SECCION_INT as CENTRO_ATENCION_RED, "
				   + " oa.DES_LUGAR_PAGO as DES_LUGAR_PAGO, "
				   + " oa.TIPO_PACIENTE as TIPO_PACIENTE, " 
				   + " oa.TIPO_ATENCION as TIPO_ATENCION, "
				   + " oa.COD_CONVENIO as COD_CONVENIO, "
				   + " oa.DES_CONVENIO as DES_CONVENIO, "
				   + " oa.INSTITUCION_CONVENIO as INSTITUCION_CONVENIO, "
				   + " oa.COD_PREVISION as COD_PREVISION, "
				   + " oa.DES_PREVISION as DES_PREVISION, "
				   + " oa.RUT_PACIENTE as RUT_PACIENTE, "
				   + " (oa.APA_PACIENTE || ' '|| oa.AMA_PACIENTE || ' ' || oa.NOM_PACIENTE ) as NOMBRE_PACIENTE, "
				   + " oa.DIRECCION_PAC as DIRECCION_PACIENTE, "
				   + " oa.COMUNA_PAC as COMUNA_PACIENTE, " 
				   + " oa.TELEFONO_PAC as TELEFONO_PACIENTE, "
				   + " TO_CHAR(oa.FECHA_NACIMIENTO, 'dd/mm/yyyy') as FECHA_NACIMIENTO, "
				   + " oa.EDAD_PACIENTE as EDAD_PACIENTE, "
				   + " oa.SEXO_PACIENTE as SEXO_PACIENTE, "
				   + " dt.RUT_PROF_RESERVA as RUT_MEDICO_DERIVADOR, " 
				   + " (dt.NOM_PROF_RESERVA || ' ' || dt.APA_PROF_RESERVA || ' ' || dt.AMA_PROF_RESERVA) as NOMBRE_MEDICO_DERIVADOR, " 
				   + " dt.FOLIO_RESERVA AS FOLIO_RESERVA, "
				   + " oa.RUT_MED_DERIVADOR as RUT_MEDICO, " 
 				   + " (oa.NOM_MED_DERIVADOR || ' ' || oa.APA_MED_DERIVADOR || ' ' || oa.AMA_MED_DERIVADOR) as NOMBRE_MEDICO, "
				   + " oa.OBSERVACION as OBSERVACION, "
				   + " oa.FECHA_PAGO_OA as FECHA_PAGO_ORDEN2,  "
				   + " pf.COD_FONASA AS CODIGO_FONASA ";
    }

    public ResultSet porCr(String fechaDesde, String fechaHasta, String cr, String mes, String anio, Statement statement) throws SQLException  {
        
        int diaDesde = Integer.parseInt(fechaDesde.substring(0, fechaDesde.indexOf("/")) );
        int mesDesde = Integer.parseInt(fechaDesde.substring(fechaDesde.indexOf("/")+1, fechaDesde.lastIndexOf("/")) );
        int anioDesde = Integer.parseInt(fechaDesde.substring(fechaDesde.lastIndexOf("/")+1, fechaDesde.length()) );
        int diaHasta = Integer.parseInt(fechaHasta.substring(0, fechaHasta.indexOf("/")) );
        int mesHasta = Integer.parseInt(fechaHasta.substring(fechaHasta.indexOf("/")+1, fechaHasta.lastIndexOf("/")) );
        int anioHasta = Integer.parseInt(fechaHasta.substring(fechaHasta.lastIndexOf("/")+1, fechaHasta.length()) );
		
        String sql = getEncabezado()
				   + " from ORDEN_ATENCION_AMB oa, DET_ORDEN_ATENCION_AMB dt, PRESTACIONES_FONASA_AMB pf "
				   + " WHERE oa.NUM_ORDEN_ATENCION = dt.NUM_ORDEN_ATENCION(+) "
				   + " AND dt.NUM_ORDEN_ATENCION=pf.NUM_ORDEN_ATENCION(+) "
				   + " AND dt.id_prestacion = pf.id_prestacion(+) "
				   + " AND dt.COD_PRESTACION IS NOT NULL ";
		
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
        sql += " AND  UPPER(oa.TIPO_ATENCION) = 'AMBULATORIO' ORDER BY oa.NUM_ORDEN_ATENCION,FECHA_PAGO_ORDEN2 ";	
        
        return select(sql , statement);
    }
    
     public ResultSet porCrFiltro(String fechaDesde, String fechaHasta, String cr, String mes, 
          String anio, String campos, String orderBy, String espSelected, String seccSelected, String codigoPrestacionAll,
          String tipoPrestacionAll, String atencionTipoAll, String pacienteTipoAll, String profesionalAll ,
          String institucionAll, String codigoPrestacion, String tipoPrestacion, String atencionTipo,
          String pacienteTipo, String profesional, String institucion, Statement statement ) throws SQLException, OutOfMemoryError  {
          int diaDesde = Integer.parseInt(fechaDesde.substring(0, fechaDesde.indexOf("/")) );
          int mesDesde = Integer.parseInt(fechaDesde.substring(fechaDesde.indexOf("/")+1, fechaDesde.lastIndexOf("/")) );
          int anioDesde = Integer.parseInt(fechaDesde.substring(fechaDesde.lastIndexOf("/")+1, fechaDesde.length()) );
          int diaHasta = Integer.parseInt(fechaHasta.substring(0, fechaHasta.indexOf("/")) );
          int mesHasta = Integer.parseInt(fechaHasta.substring(fechaHasta.indexOf("/")+1, fechaHasta.lastIndexOf("/")) );
          int anioHasta = Integer.parseInt(fechaHasta.substring(fechaHasta.lastIndexOf("/")+1, fechaHasta.length()) );
          String sql = " ";
          if(StringUtils.contains("TODO", campos)) {
              sql+= getEncabezado();
          } else {
              sql+= " SELECT DISTINCT " + campos + " oa.FECHA_PAGO_OA as FECHA_PAGO_ORDEN2  ";
          }
           sql+= " from ORDEN_ATENCION_AMB oa, DET_ORDEN_ATENCION_AMB dt, PRESTACIONES_FONASA_AMB pf "
				   + " WHERE oa.NUM_ORDEN_ATENCION = dt.NUM_ORDEN_ATENCION(+) "
				   + " AND dt.NUM_ORDEN_ATENCION=pf.NUM_ORDEN_ATENCION(+) "
				   + " AND dt.id_prestacion = pf.id_prestacion(+) "
				   + " AND dt.COD_PRESTACION IS NOT NULL ";
		
          if( !StringUtils.contains("TODO" , cr) && !cr.equals("")) {
               cr = cr.substring(0, cr.length()-1);
               sql += " AND dt.DES_CENTRO_RESULTADO in (" + cr + ") " ;
          }
          
          if(!"true".equals(pacienteTipoAll) && !"".equals(pacienteTipo)){
              pacienteTipo = pacienteTipo.substring(0, pacienteTipo.length()-1);
              sql += " AND oa.TIPO_PACIENTE IN (" + pacienteTipo + ")";
          }
          
          if(!"true".equals(profesionalAll) && !"".equals(profesional)){
              profesional = profesional.substring(0, profesional.length()-1);
              sql += " AND dt.RUT_PROF_RESERVA IN (" + profesional + ")";
          }
          
          if(!"true".equals(institucionAll) && !"".equals(institucion) ) {
              institucion = institucion.substring(0, institucion.length()-1);
              sql += " AND oa.INSTITUCION_CONVENIO IN (" + institucion + ")";
          }
          
          if(!"true".equals(tipoPrestacionAll) && !"".equals(tipoPrestacion)) {
              tipoPrestacion = tipoPrestacion.substring(0, tipoPrestacion.length()-1);
              sql += " AND dt.TIPO_PRESTACION_ORIGEN in (" + tipoPrestacion + ")";
          }
          
          if(!"true".equals(atencionTipoAll) && !"".equals(atencionTipo) ) {
              atencionTipo = atencionTipo.substring(0, atencionTipo.length()-1);
              sql += "  AND oa.TIPO_ATENCION IN (" + atencionTipo + ")";
          }
          
          if(!"true".equals(codigoPrestacionAll ) && !"".equals(codigoPrestacion)) {
                codigoPrestacion = codigoPrestacion.substring(0, codigoPrestacion.length()-1);
                sql += " AND  dt.COD_PRESTACION IN (" + codigoPrestacion + ")" ;          
          }
              
          sql += " AND  (";
          
          if(mesDesde == Integer.parseInt(mes) && anioDesde == Integer.parseInt(anio) ) {
            sql+=  " oa.FECHA_PAGO_OA between to_date('" + fechaDesde.replaceAll("/", "") + "','ddmmyyyy') ";  // 
          } else {
            sql+= " oa.FECHA_PAGO_OA between to_date('" + DateUtils.nextDay(diaDesde, mesDesde, anioDesde) + "','ddmmyyyy') ";//";
          } 
          sql += " AND to_date('" + DateUtils.nextDay(diaHasta, mesHasta, anioHasta) + "','ddmmyyyy')) ";
          if(!StringUtils.contains("TODO", espSelected) && !"".equals(espSelected)) {
              espSelected = espSelected.substring(0, espSelected.length()-1);
              sql+= " AND dt.TIPO_PRESTACION_INT in (" + espSelected + " )";
          }
          if(!StringUtils.contains("TODO", seccSelected) && !"".equals(seccSelected)) {
              seccSelected = seccSelected.substring(0, seccSelected.length()-1);
              sql+= " AND dt.COD_SECCION_INT in (" + seccSelected + " )";
          }
          sql += " AND  UPPER(oa.TIPO_ATENCION) = 'AMBULATORIO'  ";
          if("".equals(orderBy)) sql+= " ORDER BY oa.NUM_ORDEN_ATENCION,FECHA_PAGO_ORDEN2 ";
          else {
              orderBy = orderBy.substring(0, orderBy.length()-1);
              sql+= " ORDER BY " + orderBy; 
          }
          logger.info(sql);
          return select(sql, statement);
    }
}