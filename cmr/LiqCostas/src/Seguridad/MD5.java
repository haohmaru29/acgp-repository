package Seguridad;

import java.security.MessageDigest;  
import java.security.NoSuchAlgorithmException;  

public class MD5  {
	
  private MessageDigest md = null;
  static private MD5 md5 = null;
  
  // Cadena de Caracteres para el sistema Hexadecimal
  private static final char[] hexChars ={'0','1','2','3','4','5','6','7','8','9','A','B','C','D','E','F'};
  
  // Constructor ...
  public MD5() throws NoSuchAlgorithmException
  {
	 md = MessageDigest.getInstance("MD5");
  }
  
  // Creamos Objeto MD5 ....
  private static MD5 getInstance()throws NoSuchAlgorithmException {
	   if (md5 == null)
		 md5 = new MD5();
	 return (md5);
  }
  
  // Algoritmo Hash ......
  private String hashData(byte[] dataToHash) {
  return hexStringFromBytes((calculateHash(dataToHash)));
  }
  
  private byte[] calculateHash(byte[] dataToHash)  {
	  md.update(dataToHash, 0, dataToHash.length);
	 return (md.digest());
 }

 // Realizamos conversion .....
  private String hexStringFromBytes(byte[] b)  {
  // Variables
  String hex = "";
  int msb;
  int lsb = 0;
  int i;
  // MSB maps to idx 0
  // Recoremos cada Byte del Vector y lo convertimos a Hexadecimal
  for (i = 0; i <b.length; i++) {
	 msb = ((int)b[i] & 0x000000FF) / 16;
	 lsb = ((int)b[i] & 0x000000FF) % 16;
	 hex = hex + hexChars[msb] + hexChars[lsb]; // Cadena de Salida
	}
	 return(hex);
  }

  /*
   public static String EncriptarMD5(String pass) throws NoSuchAlgorithmException 
	{
			
		MessageDigest m = MessageDigest.getInstance("MD5");
		byte[] data = pass.getBytes(); 
		m.update(data,0,data.length);
		BigInteger i = new BigInteger(1,m.digest());
		return String.format("%1$032X", i);
	}
   */	
  public static String EncriptarMD5(String ClaveEncrip) throws NoSuchAlgorithmException 
  {	
	MD5 md = MD5.getInstance();
    ClaveEncrip=md.hashData(ClaveEncrip.getBytes());
    return  ClaveEncrip;	
  } 
  
} 


