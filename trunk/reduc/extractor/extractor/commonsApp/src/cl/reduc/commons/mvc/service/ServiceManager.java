package cl.reduc.commons.mvc.service;
import cl.reduc.commons.Application;
import cl.reduc.commons.mvc.controller.Controller;
import cl.reduc.commons.utils.ClassUtils;
import java.util.HashMap;
import java.util.Map;
import org.apache.log4j.Logger;

public class ServiceManager {
    
  private static ServiceManager instance = null;
	private static HashMap serviceInstance = new HashMap();
  private static final Logger logger = Logger.getLogger(ServiceManager.class);
	
	private ServiceManager(){ }
	
	public static AbstractServiceManager factory(String className){
		if(instance ==  null)
			instance = new ServiceManager();
		
		return instance.callManager(className);
	}
	
	public static AbstractServiceManager factory(Class c){
		if(instance ==  null)
			instance = new ServiceManager();
		
		return instance.callManager(c);
	}
	
	private AbstractServiceManager callManager(Class c){
		String className = c.getName();
		
		try {
			if( !serviceInstance.containsKey(className) )
				serviceInstance.put(className, (AbstractServiceManager) c.newInstance());
		} catch (InstantiationException e) {
			logger.error( e );
		} catch (IllegalAccessException e) {
			logger.error( e );
		} catch (Exception e){
			logger.error( e );
		}
		
		return (AbstractServiceManager) serviceInstance.get(className);
	}
	
	private AbstractServiceManager callManager(String className){
		try {
			Class c = Class.forName(Application.getInstance().service().concat(className));
      AbstractServiceManager.entityClass = className.replaceAll("Manager","Controller");
			if( !serviceInstance.containsKey(className) )
				serviceInstance.put(className, (AbstractServiceManager) c.newInstance() );
		} catch (InstantiationException e) {
			logger.error( e );
		} catch (IllegalAccessException e) {
			logger.error( e );
		} catch (Exception e){
			logger.error( e );
		}
		
		return (AbstractServiceManager) serviceInstance.get(className);
	}

}