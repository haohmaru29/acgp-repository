package cl.reduc.commons.utils;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class ClassUtils {

    public static Object getInstance(String className) throws ClassNotFoundException, InstantiationException, IllegalAccessException{
          Object object = null;
          Class c = Class.forName(className) ;
          object = c.newInstance();
          
          return object;
    }
	
	public static Object getInstance(String packageName, String className) throws ClassNotFoundException, InstantiationException, IllegalAccessException{
		Object object = null;
		
		Class c = Class.forName( packageName.concat( className )) ;
		object = c.newInstance();
		
		return object;
	}
	
	public static void callMethod(String methodName, Class partypes[], Object arglist[], Object obj) throws SecurityException, NoSuchMethodException, IllegalArgumentException, IllegalAccessException, InvocationTargetException{
      Method m = obj.getClass().getMethod( methodName, partypes );
    	m.invoke(obj, arglist);
	}
	
	public static Object getSingletonInstance(String singletonMethodName, String packageName, String className) throws ClassNotFoundException, SecurityException, NoSuchMethodException, IllegalArgumentException, IllegalAccessException, InvocationTargetException{
      Object object = null;
		
      Class c = Class.forName(packageName + className);
      Method method = c.getMethod( singletonMethodName, null );
      object = (Object) method.invoke(null, null);
      
      return object;
	}

}