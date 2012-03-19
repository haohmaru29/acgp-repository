package cl.reduc.commons.utils;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import java.util.ResourceBundle;

public class FileUtils {
  public static void copyFile(String inputFile, String outputFile){
      try{
          File f1 = new File(inputFile);
          File f2 = new File(outputFile);
          InputStream in = new FileInputStream(f1);
          OutputStream out = new FileOutputStream(f2);
          byte[] buf = new byte[1024];
          int len;
          while ((len = in.read(buf)) > 0){
              out.write(buf, 0, len);
          }
          in.close();
          out.close();
     } catch(FileNotFoundException e){
          System.out.println("UIPDF ERROR: "); e.printStackTrace();
     } catch(IOException e){
          System.out.println("UIPDF ERROR: "); e.printStackTrace();
    }
  }
  
  public static void createParentFolder(String path) {
      if(!existFile(path)) {
          File f = new File(path);
          f.getParentFile().mkdirs();
      }
  }
  
  public static boolean existFolder(String path) {
      System.out.println("Verificando si carpeta Existe: " + path);  
      File f = new File(path);
      return f.exists();
  }
  
  public static boolean existFile(String file) {
      File f = new File(file);
      if(f.exists()) 
        return true;
      else return false;  
  }
  
  public static void copyFile(File inputFile, File outputFile){
      try {
          InputStream in = new FileInputStream(inputFile);
          OutputStream out = new FileOutputStream(outputFile);
          byte[] buf = new byte[1024];
          int len;
          while ((len = in.read(buf)) > 0){
              out.write(buf, 0, len);
          }
          in.close();
          out.close();
          System.out.println("[UIPDF]Archivo Guardado en: " + outputFile.getName());
     } catch(FileNotFoundException e){
          System.out.println("UIPDF ERROR: "); e.printStackTrace();
     } catch(IOException e){
          System.out.println("UIPDF ERROR: "); e.printStackTrace();
    }
    
    inputFile.delete();
  }
  
  public static void copyFileOut(String inputFile, OutputStream out) {
      try{
          File f1 = new File(inputFile);
          InputStream in = new FileInputStream(f1);
          byte[] buf = new byte[1024];
          int len;
          while ((len = in.read(buf)) > 0){
              out.write(buf, 0, len);
          }
          in.close();
     } catch(FileNotFoundException e){
          System.out.println("UIPDF ERROR: "); e.printStackTrace();
     } catch(IOException e){
          System.out.println("UIPDF ERROR: "); e.printStackTrace();
    }
  }
  
  public static String getFileName(String path) {
      File f = new File(path);
      
      return f.getName();
  }
  
  public static String readDirectorio(String path, String rut, String fecha) {
      fecha= fecha.replaceAll("-","");
      fecha= fecha.replaceAll(" ","");
      System.out.println("[UIPDF]Buscando en el directorio: " + path + " El archivo: " + fecha);
      File f = new File(path);
      
      String fileName = "";
      if(f.exists() ) { 
           File[] listar=f.listFiles();
           for(int x=0; x<listar.length; x++) {
                if(listar[x].isFile()) {
                   fileName=((File)listar[x]).getName();
                   System.out.println(fileName);
                   if((fileName.indexOf(rut) != -1) && (fileName.indexOf(fecha)!=(-1)) ) {
                      break;  
                   } else {
                      fileName="";
                   }
                }
           }
      } else {
          System.out.println("[UIPDF]Directorio no existe");
      }
      
      return fileName;
  }
  
    public static boolean deleteFile(String especialidad,String fileName) {
        boolean result = false;
        ResourceBundle proper = ResourceBundle.getBundle("UI");
        String path=proper.getString("pdf.path").concat(especialidad).concat(File.separator).concat(fileName);
        File f = new File(path);
        
        if(f.exists()) {
            f.delete();
            System.out.println("UIDPF Archivo eliminado correctamente: " + path);
        } else {
            System.out.println("UIPDF Problemas al eliminar: " + path);
        }
        
        return result;
    }
    
    public static boolean deleteFile(String path) {
        boolean response = false;
        try {
            File f = new File(path); 
            if(f.exists()) {
              f.delete();
              response = true;
            }
        } catch(Exception e ) {
          response = false;
        }
        
        return response;
    }
  
}