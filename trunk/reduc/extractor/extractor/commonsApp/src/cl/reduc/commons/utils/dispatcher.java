package cl.reduc.commons.utils;

import cl.reduc.commons.Application;
import cl.reduc.commons.odbc.Oracle;
import cl.reduc.commons.utils.json.JsonView;

import java.lang.reflect.InvocationTargetException;

import java.util.HashMap;
import java.util.Map;
import java.util.ResourceBundle;

import javax.servlet.*;
import javax.servlet.http.*;

import org.apache.log4j.Logger;

public class dispatcher extends HttpServlet {
      private static final long serialVersionUID = 1L;
      
      private static Map controllerClass = new HashMap();
      
      public void init(ServletConfig config) throws ServletException {
          super.init(config);
      }
    
      public void doGet(HttpServletRequest request, HttpServletResponse response)  {
          proccessRequest(request, response );
      }
    
      public void doPost(HttpServletRequest request, HttpServletResponse response) {
          proccessRequest(request, response );
      }
      
      private void proccessRequest(HttpServletRequest request, HttpServletResponse response) {
          String methodName = request.getParameter("m").trim();
          String className = request.getParameter("c").trim();
          String webPath = Application.getInstance().web();
          JsonView jsonView = new JsonView();
          try {
            ClassUtils.callMethod(methodName, new Class[]{HttpServletRequest.class, HttpServletResponse.class} 
                , new Object[]{request, response}, getClassInstance( webPath, className ));
          } catch (SecurityException e) {
              Logger.getLogger(getClass()).error("Dispatcher error -> ", e);
              jsonView.prepareMessageResponse(false, e.getMessage());
              jsonView.render(response);
          } catch (IllegalArgumentException e) {
              Logger.getLogger(getClass()).error("Dispatcher error -> ", e);
              jsonView.prepareMessageResponse(false, e.getMessage());
              jsonView.render(response);
          } catch (NoSuchMethodException e) {
              Logger.getLogger(getClass()).error("Dispatcher error -> ", e);
              jsonView.prepareMessageResponse(false, e.getMessage());
              jsonView.render(response);
          } catch (IllegalAccessException e) {
              Logger.getLogger(getClass()).error("Dispatcher error -> ", e);
              jsonView.prepareMessageResponse(false, e.getMessage());
              jsonView.render(response);
          } catch (InvocationTargetException e) {
              Logger.getLogger(getClass()).error("Dispatcher error -> ", e);
              jsonView.prepareMessageResponse(false, e.getMessage());
              jsonView.render(response);
          } catch (ClassNotFoundException e) {
              Logger.getLogger(getClass()).error("Dispatcher error -> ", e);
              jsonView.prepareMessageResponse(false, e.getMessage());
              jsonView.render(response);
          } catch (InstantiationException e) {
              Logger.getLogger(getClass()).error("Dispatcher error -> ", e);
              jsonView.prepareMessageResponse(false, e.getMessage());
              jsonView.render(response);
          } catch(Exception e) {
              Logger.getLogger(getClass()).error("Dispatcher error -> ", e);
              jsonView.prepareMessageResponse(false, e.getMessage());
              jsonView.render(response);    
          } finally {
              Oracle.getInstances( ResourceBundle.getBundle("db").getString("jndiName") ).closeAll();
          }
      }
      
      public Object getClassInstance(String _package, String controller) throws ClassNotFoundException, InstantiationException, IllegalAccessException{
		
          if( !dispatcher.controllerClass.containsKey( controller )){
              dispatcher.controllerClass.put( controller , ClassUtils.getInstance(_package, controller));
          }
        
          return dispatcher.controllerClass.get( controller );
     }
}