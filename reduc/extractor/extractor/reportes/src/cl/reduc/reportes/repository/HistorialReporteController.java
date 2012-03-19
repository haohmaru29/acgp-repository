package cl.reduc.reportes.repository;
import cl.reduc.commons.Application;
import cl.reduc.commons.mvc.controller.AbstractJpaController;
import cl.reduc.commons.utils.PropertiesUtils;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

import oracle.jdbc.OracleTypes;

import org.apache.log4j.Logger;

public class HistorialReporteController extends AbstractJpaController {
  
    private static final Logger logger = Logger.getLogger(HistorialReporteController.class);
    private ResultSet rs;
    
    public String save(Connection conn, String idUsuario, String nombreArchivo, int idEstado) {
         String response = ""; 
         try {
              String packageName = "{ call " + Application.getInstance().packageDB() + ".GUARDARINFORME(?,?,?,?) }";
              CallableStatement call = conn.prepareCall(packageName);
              call.registerOutParameter(1, OracleTypes.VARCHAR);
              call.setString(2, idUsuario);
              call.setString(3, nombreArchivo);
              call.setInt(4, idEstado);
              call.execute();
              response = (String) call.getObject(1);
              call.close();
          } catch(SQLException e ) {
              logger.error(e);
          }
          logger.info(response);
          return response;
    }
    
    public String update(Connection conn,String idUsuario, String nombreArchivo) {
        String response = "";
        try {
            String packageName = "{ call " + Application.getInstance().packageDB() + ".GUARDARINFORME(?,?,?) }";
            CallableStatement call = conn.prepareCall(packageName);
            call.registerOutParameter(1, OracleTypes.VARCHAR);
            call.setString(2, idUsuario);
            call.setString(3, nombreArchivo);
            call.execute();
            response = (String) call.getObject(1);
            
        } catch(SQLException e ) {
            logger.error(e);
        }
        logger.info(response);
        return response;
    }
    
    public boolean delete(Connection conn, String idHistorial) {
        boolean response = false;
        try {
              String packageName = "{? = call " + Application.getInstance().packageDB() + ".DELETEINFORME(?) }";
              CallableStatement call = conn.prepareCall(packageName);
              call.registerOutParameter(1, OracleTypes.NUMBER);
              call.setString(2, idHistorial);
              call.execute();
              response = call.getBoolean(1);
        } catch(SQLException e ) {
              logger.error(e);
        }
        return response;
    }
    
    public ResultSet findByUser(Connection conn, String idUsuario, int idEstado) {
        try {
              String packageName = "{ call " + Application.getInstance().packageDB() + ".BUSCARHISTORIALUSUARIO(?,?,?) }";
              CallableStatement call = conn.prepareCall(packageName);
              call.setString(1, idUsuario);
              call.setLong(2, idEstado);
              call.registerOutParameter(3, OracleTypes.CURSOR);
              call.execute();
              rs = (ResultSet) call.getObject(3);
        } catch(SQLException e ) {
              logger.error(e);
        }
        return rs;
    }
}