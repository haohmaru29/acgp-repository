package cl.reduc.commons.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;

import jxl.common.Logger;

public class FileUtils {

	public static List readDirectory(String directory) {
		File f = new File(directory);
		List files = new ArrayList();
		String fileName="";
		if(f.exists() ) { 
	           File[] listar=f.listFiles();
	           for(int x=0; x<listar.length; x++) {
	                if(listar[x].isFile()) {
	                   fileName=((File)listar[x]).getName();
	                   if(fileName.toLowerCase().indexOf(".pdf")!=-1 ) {
	                	   files.add(fileName);
	                   }
	                } else {
	                	System.out.println("Es directorio: " + ((File)listar[x]).getAbsolutePath());
	                	readDirectory(((File)listar[x]).getAbsolutePath());
	                }
	           }
	      } else {
	          System.out.println("Directorio no existe");
	      }
		
		return files;
	}
	
	public static boolean findFiles(String fileName, String filePath) {
		boolean response = false;
		File f = new File(filePath);
		File[] listFiles =  f.listFiles();
		String files = "";
		for(int x=0;x<listFiles.length;x++) {
			files = (listFiles[x]).getName();
			if(listFiles[x].isFile()) {
				if(files.toLowerCase().indexOf(".pdf")!=-1 ) {
					if(files.toUpperCase().trim().equals(fileName.toUpperCase().trim())) {
						response = true;
						System.out.println("Archivo Encontrado");
						break;
					} else {
						response=false;
					}
	            }
			} else {
				System.out.println("Es directorio: " + ((File)listFiles[x]).getAbsolutePath());
				findFiles(fileName, (listFiles[x]).getAbsolutePath());
			}
		}
		Logger.getLogger(FileUtils.class).info("Archivo encontrado "+ fileName +" : " + response);
		return response;
	}
	
	public static boolean copyFile(String inputFile, String outputFile){
		boolean response = false;
	      try{
	          File f1 = new File(inputFile);
	          File f2 = new File(outputFile);
	          createDir(f2);
	          InputStream in = new FileInputStream(f1);
	          OutputStream out = new FileOutputStream(f2);
	          byte[] buf = new byte[1024];
	          int len;
	          while ((len = in.read(buf)) > 0){
	              out.write(buf, 0, len);
	          }
	          in.close();
	          out.close();
	          response =true;
	     } catch(FileNotFoundException e){
	          Logger.getLogger(FileUtils.class).error(e);
	     } catch(IOException e){
	    	 Logger.getLogger(FileUtils.class).error(e);
		}
	      
	    return response;  
	}
	
	public static void createDir(File f) {
		if(!f.exists()) {
			f.getParentFile().mkdirs();
		} else {
			System.out.println("Directorio Existente");
		}
	}
}
