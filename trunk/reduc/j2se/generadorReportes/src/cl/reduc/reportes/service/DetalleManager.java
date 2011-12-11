package cl.reduc.reportes.service;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import cl.reduc.commons.utils.ExcelUtils;
import cl.reduc.commons.utils.MessagesUtils;
import cl.reduc.reportes.repository.DetalleController;

public class DetalleManager {

	private DetalleController detalleController = new DetalleController();
	
	public void detallePorCr(String fechaDesde, String fechaHasta, String cr, String path, JFrame frame ) {
		ExcelUtils excel = new ExcelUtils(path,fechaDesde);
		try {
			int mesDesde = Integer.parseInt(fechaDesde.substring(fechaDesde.indexOf("/")+1, fechaDesde.lastIndexOf("/")) );
			int anioDesde = Integer.parseInt(fechaDesde.substring(fechaDesde.lastIndexOf("/")+1, fechaDesde.length()) );
		
			int mesHasta = Integer.parseInt(fechaHasta.substring(fechaHasta.indexOf("/")+1, fechaHasta.lastIndexOf("/")) );
			int anioHasta = Integer.parseInt(fechaHasta.substring(fechaHasta.lastIndexOf("/")+1, fechaHasta.length()) );
			frame.setTitle("Red UC - Generando Informe...");
			excel.resultSetToExcelUnique(detalleController.porCr(fechaDesde, fechaHasta, cr, mesHasta+"", anioHasta+"") , mesDesde + "-" + anioDesde, mesHasta + "-" + anioHasta, fechaDesde, fechaHasta,  mesHasta+"", anioHasta+"", frame );
			JOptionPane.showMessageDialog(null, "Reporte Generado con exito", "Exito", JOptionPane.INFORMATION_MESSAGE);
		}catch(OutOfMemoryError e) {
			e.printStackTrace();
			MessagesUtils.errorMessage("Java a consumido toda la memoria asignada, favor aumente la memoria.", "Error OutOfMemoryError");
		} 
		
		frame.setTitle("Red UC - Generacion Reportes");
	}	
}
