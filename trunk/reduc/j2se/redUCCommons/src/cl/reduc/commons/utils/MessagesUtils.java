package cl.reduc.commons.utils;

import javax.swing.JOptionPane;

public class MessagesUtils {
	
	public static void errorMessage(Object message) {
		JOptionPane.showMessageDialog(null,"Se a producido el siguiente error: " + message,"Error", JOptionPane.ERROR_MESSAGE);	
	}
	
	public static void errorMessage(String message, String title) {
		JOptionPane.showMessageDialog(null,  message , title, JOptionPane.ERROR_MESSAGE);	
	}
}
