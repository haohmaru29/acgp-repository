package cl.reduc.commons.mvc.service;
import cl.reduc.commons.Application;
import cl.reduc.commons.utils.ClassUtils;
import java.util.HashMap;

public class Service {
  
    public static HashMap instantiatedClasses = new HashMap();
	
    public static ServiceManager callManager(String className){
        registerInstance( className );
        return getInstance( className );
    }
    
    private static ServiceManager getInstance(String className){
      return (ServiceManager) instantiatedClasses.get( className );
    }
	
    private static void registerInstance(String className){
        if( !instantiatedClasses.containsKey( className ) )
            instantiatedClasses.put(className, makeInstance( className ));
      
    }
	
    private static ServiceManager makeInstance(String className) {
      try {
          return (ServiceManager) ClassUtils.getInstance(Application.getInstance().service(), className);
      } catch (ClassNotFoundException e) {
          e.printStackTrace();
      } catch (InstantiationException e) {
          e.printStackTrace();
      } catch (IllegalAccessException e) {
          e.printStackTrace();
      }
      return null;
    }
  
}