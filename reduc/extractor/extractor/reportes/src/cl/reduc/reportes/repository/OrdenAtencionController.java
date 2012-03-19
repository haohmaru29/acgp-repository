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

public class OrdenAtencionController extends AbstractJpaController {

    private static final Logger logger = Logger.getLogger(OrdenAtencionController.class);
    private ResultSet rs;
    
    public ResultSet findAllTipoPaciente(Connection conn, String tipoPaciente) {
          try {
              String packageName = "{ call " + Application.getInstance().packageDB() + ".TIPO_PACIENTE(?,?) }";
              CallableStatement call = conn.prepareCall(packageName);
              call.setString(1, tipoPaciente);
              call.registerOutParameter(2, OracleTypes.CURSOR);
              call.execute();
              rs = (ResultSet) call.getObject(2);
              call.close();
          } catch(SQLException e ) {
              logger.error(e);
          }
          
          return rs;
    }
    
    public ResultSet findTipoAtencion(Connection conn, String tipoAtencion) {
          try {
              String packageName = "{ call " + Application.getInstance().packageDB() + ".TIPO_ATENCION(?,?) }";
              CallableStatement call = conn.prepareCall(packageName);
              call.setString(1, tipoAtencion);
              call.registerOutParameter(2, OracleTypes.CURSOR);
              call.execute();
              rs = (ResultSet) call.getObject(2);
              call.close();
          } catch(SQLException e ) {
              logger.error(e);
          }
          
          return rs;
    }
    
    public ResultSet finAllInstitucion(Connection conn) {
          String packageName = "{ call " + Application.getInstance().packageDB() + ".INSTITUCION(?) }";
          try {
              CallableStatement call = conn.prepareCall(packageName);
              call.registerOutParameter(1, OracleTypes.CURSOR);
              
              call.execute();
              rs = (ResultSet) call.getObject(1);
              call.close();
          }catch(SQLException e ) {
              logger.error(e);
          }
          return rs;
    }
}