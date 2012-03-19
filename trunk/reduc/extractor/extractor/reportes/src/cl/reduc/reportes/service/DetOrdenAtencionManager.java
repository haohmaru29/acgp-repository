package cl.reduc.reportes.service;
import cl.reduc.commons.mvc.service.AbstractServiceManager;
import cl.reduc.reportes.repository.DetOrdenAtencionController;
import java.sql.Connection;
import java.sql.ResultSet;

public class DetOrdenAtencionManager extends AbstractServiceManager {

      public ResultSet findAllProfesional(Connection conn) {
          return ( (DetOrdenAtencionController) jpaController).findAllProfesional(conn);
      }
      
      public ResultSet findAllTipoPrestacion(Connection conn, String tipoPrestacion) {
          return ( (DetOrdenAtencionController) jpaController).findAllTipoPrestacion(conn,tipoPrestacion);
      }
}