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

public class CentroReclasifController extends AbstractJpaController {
    
    private static final Logger logger = Logger.getLogger(CentroReclasifController.class);
    private ResultSet rs;
      
    public ResultSet finAllCr(Connection conn) throws SQLException {
          String packageName = "{ call " + Application.getInstance().packageDB() + ".centro_resultdos(?) }";
          CallableStatement call = conn.prepareCall(packageName);
          call.registerOutParameter(1, OracleTypes.CURSOR);
          call.execute();
          rs = (ResultSet) call.getObject(1);
          call.close();
          return rs;
    } 
      
    public ResultSet findCrByCod(Connection conn, String cod) throws SQLException {
        try {
            String packageName = "{ call " + Application.getInstance().packageDB() + ".CENTRO_RESULTADOS_FILTRADO(?,?) }";
            CallableStatement call = conn.prepareCall(packageName);
            call.setString(1, cod);
            call.registerOutParameter(2, OracleTypes.CURSOR);
            call.execute();
            rs = (ResultSet) call.getObject(2);
            call.close();
        } catch(SQLException e ) {
            logger.error(e);
        }
        
        return rs;
    }
    
     public ResultSet findAllSeccion(Connection conn) {
          try {
              String packageName = "{ call " + Application.getInstance().packageDB() + ".SECCIONES(?) }";
              CallableStatement call = conn.prepareCall(packageName);
              call.registerOutParameter(1, OracleTypes.CURSOR);
              call.execute();
              rs = (ResultSet) call.getObject(1);
              call.close();
          } catch(SQLException e ) {
              logger.error(e);
          }
          
          return rs;
      
    }
    
    public ResultSet findSeccionByCod(Connection conn, String cod) {
        try {
            String packageName = "{ call " + Application.getInstance().packageDB() + ".SECCIONES_FILTRADO(?,?) }";
            CallableStatement call = conn.prepareCall(packageName);
            call.setString(1, cod);
            call.registerOutParameter(2, OracleTypes.CURSOR);
            call.execute();
            rs = (ResultSet) call.getObject(2);
            call.close();
        } catch(SQLException e ) {
            logger.error(e);
        }
        
        return rs;
    }
}