package cl.reduc.reportes.service;

import cl.reduc.commons.utils.ExcelUtils;
import cl.reduc.reportes.repository.ResumenController;

public class ResumenManager {
	
	private ResumenController resumenController = new ResumenController();
	
	public void resumenPorEspecialidad(String mes, String anio, String especialidad, String seccion, String path) {
		ExcelUtils.resultSetToExcel(resumenController.porEspecialidad(mes, anio, especialidad, seccion),path);	
	}
	
	public void resumenPorSeccion(String mes, String anio, String especialidad, String seccion, String path) {
		ExcelUtils.resultSetToExcel(resumenController.porSeccion(mes, anio, especialidad, seccion),path);	
	}
}
