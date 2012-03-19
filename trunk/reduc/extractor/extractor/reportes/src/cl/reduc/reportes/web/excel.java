package cl.reduc.reportes.web;

import cl.reduc.commons.Application;
import cl.reduc.commons.mvc.service.ServiceManager;
import cl.reduc.commons.odbc.Oracle;
import cl.reduc.commons.utils.FileUtils;
import cl.reduc.commons.utils.StringUtils;
import cl.reduc.commons.utils.json.ExcelView;
import cl.reduc.commons.utils.json.JsonView;
import cl.reduc.reportes.service.DetalleManager;
import cl.reduc.reportes.service.HistorialReporteManager;
import cl.reduc.reportes.service.HomeManager;

import java.io.File;
import java.io.FileNotFoundException;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import java.util.ResourceBundle;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

public class excel {
      
      private static final Logger logger = Logger.getLogger(excel.class);
      
      private DetalleManager detalleManager = 
                    (DetalleManager)ServiceManager.factory("DetalleManager");
                    
      private HistorialReporteManager reporteManager = 
                    (HistorialReporteManager) ServiceManager.factory("HistorialReporteManager");
      
      public void export(HttpServletRequest request, HttpServletResponse response ) {
          JsonView jsonView = new JsonView();
          Connection conn = null;
          try {
            String idHistorial="";
            String orderBy=request.getParameter("orderBy");
            String path = ResourceBundle.getBundle("module").getString("path.file");
            String usuario = request.getParameter("user");
            String fileName =  path + usuario + File.separator + request.getParameter("file") + ".xls";
            conn = Oracle.getInstances(Application.getInstance().jndiDB()).getJNDIConnection();
            if(detalleManager.porCr(request.getParameterMap(), fileName, orderBy)){
                idHistorial=reporteManager.save(conn, usuario, request.getParameter("file") + ".xls", 0);
            }
            String fileNameReplace = path + usuario + "//" + request.getParameter("file") + ".xls";
            Map map = new HashMap();
            map.put("fileName", request.getParameter("file"));
            map.put("patFile", request.getParameter("file"));
            map.put("idHistorial", idHistorial);
            jsonView.prepareResponse(map);
            jsonView.render(response);
          } catch(OutOfMemoryError e) {
              logger.error(e);
              jsonView.prepareResponse(false, "Se ha agotado la memoria virtual, favor filtre por mas campos y/o aumente esta memoria.");
          } catch(SQLException e) {
              logger.error(e);
              jsonView.prepareResponse(false, "Se ha producido un error, " + e.getMessage() );
          }finally {
              System.gc();
              Oracle.getInstances(Application.getInstance().jndiDB()).closeConnection(conn);
          }
      }
      /*
      public void exportCsv(HttpServletRequest request, HttpServletResponse response ) {
          try {
            JsonView jsonView = new JsonView();
            String pc = request.getRemoteHost();
            String path = ResourceBundle.getBundle("module").getString("path.file");
            String fileName =  path + pc + File.separator + request.getParameter("file") + ".csv";
            detalleManager.porCrCSV(request.getParameterMap(), fileName);
            String fileNameReplace = path + pc + "//" + request.getParameter("file") + ".csv";
            Map map = new HashMap();
            map.put("fileName", request.getParameter("file"));
            map.put("patFile", fileNameReplace);
            
            jsonView.prepareResponse(map);
            jsonView.render(response);
          } finally {
              System.gc();
          }
      }
      */
      public void getExcel(HttpServletRequest request, HttpServletResponse response) {
          ExcelView excelView = new ExcelView();
          try {
              String path = ResourceBundle.getBundle("module").getString("path.file");
              String usuario = request.getParameter("user");
              String fileName = "";
              if(!StringUtils.contains(".xls", request.getParameter("file")) )
                fileName =  path + usuario + File.separator + request.getParameter("file") + ".xls";
              else fileName =  path + usuario + File.separator + request.getParameter("file");
              
              excelView.prepareResponse(new File(fileName));
          } catch( FileNotFoundException e) {
              logger.error(e);
          }
          
          excelView.render(response);
      }
      
      public void validatePath(HttpServletRequest request, HttpServletResponse response ) {
          String path = ResourceBundle.getBundle("module").getString("path.file");
          JsonView jsonView = new JsonView();
          if(FileUtils.existFolder(path) ) {
              jsonView.prepareResponse(true , "Directorio existe");
          } else {
              jsonView.prepareResponse(false , "<b>Directorio no existe y/o no tiene acceso, favor verifique</b>");
          }
          
          jsonView.render(response);
      }
      
      public void delExcel(HttpServletRequest request, HttpServletResponse response ) {
          String path = ResourceBundle.getBundle("module").getString("path.file");
          String usuario = request.getParameter("user");
          String idHistorial = request.getParameter("idHistorial");
          String fileName = "", message= "";
          boolean boleano = false;
          if(!StringUtils.contains(".xls", request.getParameter("file")) )
              fileName =  path + usuario + File.separator + request.getParameter("file") + ".xls";
          else fileName =  path + usuario + File.separator + request.getParameter("file");
          JsonView jsonView = new JsonView();
          Connection conn = null;
          if(FileUtils.deleteFile(fileName) ) {
              try {
                  conn = Oracle.getInstances(Application.getInstance().jndiDB()).getJNDIConnection();
                  if(reporteManager.delete(conn,idHistorial) ) {
                      message = "Archivo eliminado con exito"; boleano=true;
                  }
                  else message= "Problemas al eliminar archivo en base datos.";
              } catch(SQLException e) {
                 message= "Problemas al eliminar registro en base de datos."; 
              }finally {
                  Oracle.getInstances(Application.getInstance().jndiDB()).closeConnection(conn);
              }
          } else message= "Problemas al eliminar archivo en directorio.";
          jsonView.prepareResponse(boleano, message);
          jsonView.render(response);
      }
}