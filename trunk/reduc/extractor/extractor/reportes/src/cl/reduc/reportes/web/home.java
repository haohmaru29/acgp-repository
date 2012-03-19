package cl.reduc.reportes.web;
import cl.puc.med.DesarrollosXMLJavaOracle.Comun;

import cl.reduc.commons.Application;
import cl.reduc.commons.mvc.service.ServiceManager;
import cl.reduc.commons.odbc.Oracle;
import cl.reduc.commons.utils.json.JsonView;
import cl.reduc.reportes.service.CentroReclasifManager;
import cl.reduc.reportes.service.ConsultaReclasifManager;
import cl.reduc.reportes.service.DetOrdenAtencionManager;
import cl.reduc.reportes.service.EspecialidadReclasifManager;
import cl.reduc.reportes.service.HistorialReporteManager;
import cl.reduc.reportes.service.OrdenAtencionManager;
import cl.reduc.reportes.service.SeccionManager;

import java.sql.Connection;
import java.sql.SQLException;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class home {

    private List centroResultados = new ArrayList();
    private List especialidadList = new ArrayList();
    private List codPrestacion = new ArrayList();
    private List institucionList = new ArrayList();
    private List profesionalList = new ArrayList();
    private List prestacionList = new ArrayList();
    
    
    private EspecialidadReclasifManager especialidadReclasifManager = 
          (EspecialidadReclasifManager)ServiceManager.factory("EspecialidadReclasifManager");
          
    private CentroReclasifManager centroReclasifManager = 
          (CentroReclasifManager)ServiceManager.factory("CentroReclasifManager");
    
    private HistorialReporteManager historialManager= 
          (HistorialReporteManager) ServiceManager.factory("HistorialReporteManager");    
    
    private ConsultaReclasifManager consultaReclasifManager = 
          (ConsultaReclasifManager) ServiceManager.factory("ConsultaReclasifManager");
          
    private DetOrdenAtencionManager detOrdenAtencionManager = 
          (DetOrdenAtencionManager) ServiceManager.factory("DetOrdenAtencionManager");
          
    private OrdenAtencionManager ordenManager = 
          (OrdenAtencionManager) ServiceManager.factory("OrdenAtencionManager");
  
    public void centroResultados(HttpServletRequest request, HttpServletResponse response ) {
          JsonView jsonView = new JsonView();
          int start = Integer.parseInt((request.getParameter("start")==null)?"-1":request.getParameter("start"));
          int limit = Integer.parseInt((request.getParameter("limit")==null)?"-1":request.getParameter("limit"));
          int page = Integer.parseInt((request.getParameter("page")==null)?"-1":request.getParameter("page"));
          String filter = request.getParameter("filter")==null?"":request.getParameter("filter");
          Connection conn = null;
          try {
              if(centroResultados.size() == 0) {
                  conn = Oracle.getInstances(Application.getInstance().jndiDB()).getJNDIConnection();
                  jsonView.prepareResponsePagination(centroReclasifManager.findAll(conn), start, limit*page, centroResultados);
              } else {
                  jsonView.prepareResponsePagination(filter, start, limit*page, centroResultados, "COD_CENTRO_RESULTADO");
              }
          } catch(SQLException e ) {
              jsonView.prepareResponse(false, "<b>" + e.getMessage() + "</b>");
          } finally {
              Oracle.getInstances(Application.getInstance().jndiDB()).closeConnection(conn);
          }
          jsonView.render(response);
    }  
    
    public void especialidad(HttpServletRequest request, HttpServletResponse response ) {
          JsonView jsonView = new JsonView();
          int start = Integer.parseInt((request.getParameter("start")==null)?"-1":request.getParameter("start"));
          int limit = Integer.parseInt((request.getParameter("limit")==null)?"-1":request.getParameter("limit"));
          int page = Integer.parseInt((request.getParameter("page")==null)?"-1":request.getParameter("page"));
          String filter = request.getParameter("filter")==null?"":request.getParameter("filter");
          Connection conn=null;
          try {
              if(especialidadList.size()==0) {
                  conn = Oracle.getInstances(Application.getInstance().jndiDB()).getJNDIConnection();
                  jsonView.prepareResponsePagination(especialidadReclasifManager.findAllEspecialidad(conn) , start, limit*page, especialidadList);
              } else {
                  jsonView.prepareResponsePagination(filter, start, limit*page, especialidadList, "COD_PRESTACION");
              }
          }catch(SQLException e) {
              jsonView.prepareResponse(false, "<b>" + e.getMessage() + "</b>");
          } finally {
              Oracle.getInstances(Application.getInstance().jndiDB()).closeConnection(conn);
          }
          jsonView.render(response);
    }
    
    public void secciones(HttpServletRequest request, HttpServletResponse response ) {
        SeccionManager seccionManager = (SeccionManager) ServiceManager.factory("SeccionManager");
        int start = Integer.parseInt((request.getParameter("start")==null)?"-1":request.getParameter("start"));
        int limit = Integer.parseInt((request.getParameter("limit")==null)?"-1":request.getParameter("limit"));
        int page = Integer.parseInt((request.getParameter("page")==null)?"-1":request.getParameter("page"));
        String filter = request.getParameter("filter")==null?"":request.getParameter("filter");
        JsonView jsonView = new JsonView();
        Connection conn = null;
        try {
            conn = Oracle.getInstances(Application.getInstance().jndiDB()).getJNDIConnection();
            if(!"".equals(filter) ) {
                jsonView.prepareResponsePagination(centroReclasifManager.findSeccionByCod(conn,filter), start, limit*page);
            } else {
                jsonView.prepareResponsePagination(centroReclasifManager.findAllSeccion(conn), start, limit*page);
            }
        } catch(SQLException e )  {
            jsonView.prepareResponse(false, "<b>" + e.getMessage() + "</b>");
        } finally{
          Oracle.getInstances(Application.getInstance().jndiDB()).closeConnection(conn);
        }
        jsonView.render(response);
    }
    
    public void codigoPestacion(HttpServletRequest request, HttpServletResponse response ) {
        int start = Integer.parseInt((request.getParameter("start")==null)?"-1":request.getParameter("start"));
        int limit = Integer.parseInt((request.getParameter("limit")==null)?"-1":request.getParameter("limit"));
        int page = Integer.parseInt((request.getParameter("page")==null)?"-1":request.getParameter("page"));
        String filter = request.getParameter("filter")==null?"":request.getParameter("filter");
        JsonView jsonView = new JsonView();
        Connection conn=null;
        try {
             if(codPrestacion.size()==0) {
                conn = Oracle.getInstances(Application.getInstance().jndiDB()).getJNDIConnection();
                jsonView.prepareResponsePagination(consultaReclasifManager.findAllCodigos(conn) , start, limit*page, codPrestacion);
            } else {
                jsonView.prepareResponsePagination(filter, start, limit*page, codPrestacion, "COD_PRESTACION");
            }
        }catch(SQLException e ) {
             jsonView.prepareResponse(false, "<b>" + e.getMessage() + "</b>");
             e.printStackTrace();
        } finally {
            Oracle.getInstances(Application.getInstance().jndiDB()).closeConnection(conn);
        }
        
        jsonView.render( response );
    }
    
    public void institucion(HttpServletRequest request, HttpServletResponse response ) {
        int start = Integer.parseInt((request.getParameter("start")==null)?"-1":request.getParameter("start"));
        int limit = Integer.parseInt((request.getParameter("limit")==null)?"-1":request.getParameter("limit"));
        int page = Integer.parseInt((request.getParameter("page")==null)?"-1":request.getParameter("page"));
        String filter = request.getParameter("filter")==null?"":request.getParameter("filter");
        JsonView jsonView = new JsonView();
        Connection conn = null;
        try {
            if(institucionList.size()==0) {
                conn = Oracle.getInstances(Application.getInstance().jndiDB()).getJNDIConnection();
                jsonView.prepareResponsePagination(ordenManager.findAllInstitucion(conn) , start, limit*page, institucionList);
            } else {
                jsonView.prepareResponsePagination(filter, start, limit*page, institucionList, "INSTITUCION_CONVENIO");
            }
        } catch(SQLException e ) {
            e.printStackTrace();
            jsonView.prepareResponse(false, "<b>" + e.getMessage() + "</b>");
        } finally {
            Oracle.getInstances(Application.getInstance().jndiDB()).closeConnection(conn);
        }
        jsonView.render( response );
    }
    
    public void profesional(HttpServletRequest request, HttpServletResponse response ) {
        int start = Integer.parseInt((request.getParameter("start")==null)?"-1":request.getParameter("start"));
        int limit = Integer.parseInt((request.getParameter("limit")==null)?"-1":request.getParameter("limit"));
        int page = Integer.parseInt((request.getParameter("page")==null)?"-1":request.getParameter("page"));
        String filter = request.getParameter("filter")==null?"":request.getParameter("filter");
        JsonView jsonView = new JsonView();
        Connection conn = null;
        try {
            if(profesionalList.size()==0) {
                conn = Oracle.getInstances(Application.getInstance().jndiDB()).getJNDIConnection();
                jsonView.prepareResponsePagination(detOrdenAtencionManager.findAllProfesional(conn) , start, limit*page, profesionalList);
            } else {
                jsonView.prepareResponsePagination(filter, start, limit*page, profesionalList, "NOMBRE_PROF");
            }
        } catch(SQLException e ) {
            e.printStackTrace();
            jsonView.prepareResponse(false, "<b>" + e.getMessage() + "</b>");
        } finally {
            Oracle.getInstances(Application.getInstance().jndiDB()).closeConnection(conn);
        }
        jsonView.render( response );
    }
    
    public void paciente(HttpServletRequest request, HttpServletResponse response ) {
        int start = Integer.parseInt((request.getParameter("start")==null)?"-1":request.getParameter("start"));
        int limit = Integer.parseInt((request.getParameter("limit")==null)?"-1":request.getParameter("limit"));
        int page = Integer.parseInt((request.getParameter("page")==null)?"-1":request.getParameter("page"));
        String filter = request.getParameter("filter")==null?"":request.getParameter("filter");
        List tipoPaciente = new ArrayList();
        Map m = new HashMap();
        m.put("TIPO_PACIENTE","INSTITUCIONAL" );
        tipoPaciente.add(m);
        m = new HashMap();
        m.put("TIPO_PACIENTE", "PRIVADO");
        tipoPaciente.add(m);
        JsonView jsonView = new JsonView();
        jsonView.prepareResponsePagination(filter, start, limit*page, tipoPaciente, "TIPO_PACIENTE");
        jsonView.render( response );
    }
    
    public void atencion(HttpServletRequest request, HttpServletResponse response ) {
        int start = Integer.parseInt((request.getParameter("start")==null)?"-1":request.getParameter("start"));
        int limit = Integer.parseInt((request.getParameter("limit")==null)?"-1":request.getParameter("limit"));
        int page = Integer.parseInt((request.getParameter("page")==null)?"-1":request.getParameter("page"));
        String filter = request.getParameter("filter")==null?"":request.getParameter("filter");
        List tipoAtencion = new ArrayList();
        Map m = new HashMap();
        m.put("TIPO_ATENCION","AMBULATORIO" );
        tipoAtencion.add(m);
        JsonView jsonView = new JsonView();
        jsonView.prepareResponsePagination(filter, start, limit*page, tipoAtencion, "TIPO_ATENCION");
        jsonView.render( response );
    }
    
    public void tipoPrestacion(HttpServletRequest request, HttpServletResponse response ) {
        int start = Integer.parseInt((request.getParameter("start")==null)?"-1":request.getParameter("start"));
        int limit = Integer.parseInt((request.getParameter("limit")==null)?"-1":request.getParameter("limit"));
        int page = Integer.parseInt((request.getParameter("page")==null)?"-1":request.getParameter("page"));
        String filter = request.getParameter("filter")==null?"":request.getParameter("filter");
        Connection conn = null;
        JsonView jsonView = new JsonView();
        try {
            if(prestacionList.size()==0) {
                conn = Oracle.getInstances(Application.getInstance().jndiDB()).getJNDIConnection();
                jsonView.prepareResponsePagination(detOrdenAtencionManager.findAllTipoPrestacion(conn, filter) , start, limit*page, prestacionList);        
            } else {
                jsonView.prepareResponsePagination(filter, start, limit*page, prestacionList, "TIPO_PRESTACION_ORIGEN");
            }
        } catch(SQLException e ) {
             e.printStackTrace();
            jsonView.prepareResponse(false, "<b>" + e.getMessage() + "</b>");
        } finally {
            Oracle.getInstances(Application.getInstance().jndiDB()).closeConnection(conn);
        }
        jsonView.render( response );
    }
    
    public void usuario(HttpServletRequest request, HttpServletResponse response ) {
        String usuario =  (Comun.getUsuarioConectado(request) == null || Comun.getUsuarioConectado(request) == "no_conectado") 
                          ? "PRUEBAS":  Comun.getUsuarioConectado(request);
        JsonView jsonView = new JsonView();
        jsonView.prepareResponse(true, usuario );
        jsonView.render(response);
    }
}