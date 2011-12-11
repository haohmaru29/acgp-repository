package cl.reduc.commons.ofimatica.excel;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import jxl.Cell;
import jxl.Sheet;
import jxl.Workbook;
import jxl.read.biff.BiffException;

public class ReadExcel extends Excel {
	
	private Workbook wb;
	private Sheet sheet;
	
	public Sheet getSheet() {
		sheet= wb.getSheet(0);
		System.out.println(sheet.getName());
		return sheet;
	}
	
	public Sheet getSheet(int sheetNumber) {
		sheet =wb.getSheet(sheetNumber);
		System.out.println(sheet.getName()); 
		return sheet;
	}
	
	public Cell getCell(int column, int row) {
		return sheet.getCell(row,column);
	}
	
	public ReadExcel(File f) {
		try {
			if(f.exists()) 
				wb = Workbook.getWorkbook(f);
			else {
				System.out.println("Archivo no existe favor verifique");
			}
		} catch (BiffException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public ReadExcel(String path) {
		try {
			wb = Workbook.getWorkbook(new File(path));
		} catch (BiffException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public ReadExcel() {
	}
	
	public Workbook getWorkBook(String path) {
		try {
			wb = Workbook.getWorkbook(new File(path));
		} catch (BiffException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return wb;
	}
	
	public List readColumnSheet() {
		List array;
		List response = new ArrayList();
		for(int i=1;i<(sheet.getRows());i++) {
			array = new ArrayList();
        	for(int j=0;j<sheet.getColumns();j++) {
        		array.add(sheet.getCell(j,i).getContents());
        	}
        	response.add(array);
		}
		
		return response;
	}
	
	public void closeWorkBook() {
		wb.close();
	}
}
