package cl.reduc.commons.mvc.controller;
import java.util.HashMap;
import org.apache.log4j.Logger;

public class JpaController {
    private static JpaController instance;
    private static HashMap jpaControllerInstances = new HashMap();
    private static final Logger logger = Logger.getLogger(JpaController.class);

	private JpaController(){}
	
	public static AbstractJpaController factory(String className){
		if( instance == null)
			instance = new JpaController();
		
		return instance.callController(className);
	}
	
	private AbstractJpaController callController(String className){
		try {
			Class c = Class.forName( className );
			if( !jpaControllerInstances.containsKey(className) )
				jpaControllerInstances.put(className, (AbstractJpaController) c.newInstance());
		} catch (InstantiationException e) {
			logger.error( e.getMessage() );
		} catch (IllegalAccessException e) {
			logger.error( e.getMessage() );
		} catch (Exception e){
			logger.error( e.getMessage() );
		}
		
		return (AbstractJpaController)jpaControllerInstances.get(className);
	}
  
}