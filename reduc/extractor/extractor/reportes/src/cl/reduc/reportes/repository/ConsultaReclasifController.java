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

public class ConsultaReclasifController extends AbstractJpaController {

    private static final Logger logger = Logger.getLogger(ConsultaReclasifController.class);
    private ResultSet rs;
    
    public ResultSet findAllCodigos(Connection conn) {
          String packageName = "{ call " + Application.getInstance().packageDB()+ ".CODIGOS_PRESTACION(?) }";
          try {
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
}