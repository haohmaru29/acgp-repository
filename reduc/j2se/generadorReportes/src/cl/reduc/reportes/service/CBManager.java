package cl.reduc.reportes.service;

import java.util.List;

import cl.reduc.reportes.repository.CBController;

public class CBManager {

	private CBController cbController = new CBController();
	
	public List getEspecialidades() {
		return cbController.getEspecialidades();
    }
	
	public List getSucursales() {
        return cbController.getSucursales();
    }
	
	public List getCr() {
		return cbController.getCr();
	}
	
}
