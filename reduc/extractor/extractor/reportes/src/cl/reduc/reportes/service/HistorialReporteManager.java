package cl.reduc.reportes.service;
import cl.reduc.commons.mvc.service.AbstractServiceManager;
import cl.reduc.reportes.repository.HistorialReporteController;
import java.sql.Connection;
import java.sql.ResultSet;

public class HistorialReporteManager extends AbstractServiceManager {
      
      public String save(Connection conn, String idUsuario, String nombreArchivo, int idEstado) {
          return ((HistorialReporteController) jpaController).save(conn, idUsuario, nombreArchivo, idEstado);
      }
      
      public boolean delete(Connection conn, String idHistorial) {
          return ((HistorialReporteController) jpaController).delete(conn,idHistorial);
      }
      
      public ResultSet findByUser(Connection conn, String idUsuario, int idEstado) {
           return ((HistorialReporteController) jpaController).findByUser(conn,idUsuario, idEstado);
      }
      
      public String update(Long id) {
        return null;
      }
}