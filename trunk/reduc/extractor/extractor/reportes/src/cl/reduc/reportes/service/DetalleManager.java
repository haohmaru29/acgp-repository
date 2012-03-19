package cl.reduc.reportes.service;
import cl.reduc.commons.Application;
import cl.reduc.commons.mvc.service.AbstractServiceManager;
import cl.reduc.commons.odbc.Oracle;
import cl.reduc.commons.ofimatica.CsvEngine;
import cl.reduc.commons.utils.ArrayUtils;
import cl.reduc.commons.utils.ExcelUtils;
import cl.reduc.reportes.repository.DetalleController;

import java.sql.Connection;
import java.sql.SQLException;

import java.sql.Statement;
import java.util.Map;

import org.apache.log4j.Logger;

public class DetalleManager extends AbstractServiceManager {

    private static final Logger logger = Logger.getLogger(DetalleManager.class);
    
    public boolean porCr(Map params, String path, String orderBy) throws SQLException, OutOfMemoryError {
        String fechaDesde = ArrayUtils.extract((String[])  params.get("fechaInicio") );
        String fechaHasta = ArrayUtils.extract((String[]) params.get("fechaFin") );
        String campos = ArrayUtils.extract((String[]) params.get("campos") ); //centro_resultados
        String cr = ArrayUtils.extract((String[]) params.get("opcion"));
        String crAll = ArrayUtils.extract((String[]) params.get("crAll") );
        String espAll= ArrayUtils.extract((String[]) params.get("espAll") );
        String seccAll = ArrayUtils.extract((String[]) params.get("seccAll") );
        String espSelected = ArrayUtils.extract((String[]) params.get("espSelected") );
        String seccSelected = ArrayUtils.extract((String[]) params.get("seccSelected") );
        
        String codigoPrestacionAll = ArrayUtils.extract((String[]) params.get("prestacionCodigoAll") );
        String tipoPrestacionAll = ArrayUtils.extract((String[]) params.get("prestacionTipoAll") );
        String atencionTipoAll = ArrayUtils.extract((String[]) params.get("atencionAll") );
        String pacienteTipoAll = ArrayUtils.extract((String[]) params.get("pacienteTipoAll") );
        String profesionalAll = ArrayUtils.extract((String[]) params.get("pacienteTipoAll") );
        String institucionAll = ArrayUtils.extract((String[]) params.get("institucionAll") );
        
        String codigoPrestacion = ArrayUtils.extract((String[]) params.get("prestacionTipo") );
        String tipoPrestacion = ArrayUtils.extract((String[]) params.get("prestacionTipo") );
        String atencionTipo = ArrayUtils.extract((String[]) params.get("atencionTipo") );
        String pacienteTipo = ArrayUtils.extract((String[]) params.get("pacienteTipo") );
        String profesional = ArrayUtils.extract((String[]) params.get("profesional") );
        String institucion = ArrayUtils.extract((String[]) params.get("institucion") );
        
        ExcelUtils excel = new ExcelUtils(path,fechaDesde);
        boolean response = false;
        int mesDesde = Integer.parseInt(fechaDesde.substring(fechaDesde.indexOf("/")+1, fechaDesde.lastIndexOf("/")) );
        int anioDesde = Integer.parseInt(fechaDesde.substring(fechaDesde.lastIndexOf("/")+1, fechaDesde.length()) );
        int mesHasta = Integer.parseInt(fechaHasta.substring(fechaHasta.indexOf("/")+1, fechaHasta.lastIndexOf("/")) );
        int anioHasta = Integer.parseInt(fechaHasta.substring(fechaHasta.lastIndexOf("/")+1, fechaHasta.length()) );
        if(crAll.equals("true")) campos="TODOS";
        if(espAll.equals("true")) espSelected="TODOS";
        if(seccAll.equals("true")) seccSelected="TODOS";
        Connection conn = null;
        Statement statement = null;
        try {
            conn = Oracle.getInstances(Application.getInstance().jndiDB()).getJNDIConnection();
            statement= conn.createStatement();
            excel.resultSetToExcelUnique(((DetalleController) jpaController).porCrFiltro(fechaDesde, fechaHasta, cr, mesHasta+"", anioHasta+"", 
                campos, orderBy, espSelected, seccSelected, codigoPrestacionAll, tipoPrestacionAll, 
                atencionTipoAll, pacienteTipoAll, profesionalAll, institucionAll, codigoPrestacion,
                tipoPrestacion, atencionTipo, pacienteTipo, profesional , institucion, statement) , mesDesde + "-" + anioDesde, mesHasta + "-" + 
                anioHasta, fechaDesde, fechaHasta,  mesHasta+"", anioHasta+""); 
        }catch(SQLException e ) {
          logger.error(e);;
        } finally {
           if(statement!= null) {
              statement.close();
           }
            Oracle.getInstances(Application.getInstance().jndiDB()).closeConnection(conn);
        }
          response = true;
        
        return response;
    }
    /*
     public void porCrCSV(Map params, String path) {
        String fechaDesde = ArrayUtils.extract((String[])  params.get("fechaInicio") );
        String fechaHasta = ArrayUtils.extract((String[]) params.get("fechaFin") );
        String cr = ArrayUtils.extract((String[]) params.get("opcion"));
        cr = cr.substring(0, cr.length()-1);
        CsvEngine csvEngine = new CsvEngine(path, ';');
        try {
          int mesDesde = Integer.parseInt(fechaDesde.substring(fechaDesde.indexOf("/")+1, fechaDesde.lastIndexOf("/")) );
          int anioDesde = Integer.parseInt(fechaDesde.substring(fechaDesde.lastIndexOf("/")+1, fechaDesde.length()) );
          int mesHasta = Integer.parseInt(fechaHasta.substring(fechaHasta.indexOf("/")+1, fechaHasta.lastIndexOf("/")) );
          int anioHasta = Integer.parseInt(fechaHasta.substring(fechaHasta.lastIndexOf("/")+1, fechaHasta.length()) );
          csvEngine.csvRunner(path, ((DetalleController) jpaController).porCr(fechaDesde, fechaHasta, cr, mesHasta+"", anioHasta+"")) ;
        }catch(OutOfMemoryError e) {
          logger.error(e);
        } catch(SQLException e) {
          logger.error(e);
        }
    }
    */
}