package cl.reduc.commons.ofimatica.excel;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import cl.reduc.commons.utils.DateUtils;

import jxl.Workbook;
import jxl.WorkbookSettings;
import jxl.write.Label;
import jxl.write.Number;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import jxl.write.WriteException;
import jxl.write.biff.RowsExceededException;

public class WriteExcel extends Excel {
	
	private WritableWorkbook workBook;
	private WritableSheet sheet;
	private FileOutputStream fos;	
	
	public void createWorkBook(File f) {
		 try {
			WorkbookSettings ws = new WorkbookSettings();
			ws.setUseTemporaryFileDuringWrite(true);
			workBook= Workbook.createWorkbook(f,ws);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void createWorkBook(String file) {
		 try {
			 fos = new FileOutputStream(file);
			 WorkbookSettings ws = new WorkbookSettings();
			 ws.setUseTemporaryFileDuringWrite(true);
			 //workBook= Workbook.createWorkbook(new File(file),ws);
			 workBook= Workbook.createWorkbook(fos, ws);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void createWorkBook(String file, Workbook wb) {
		 try {
			workBook= Workbook.createWorkbook(new File(file),wb);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void createSheet() {
		workBook.createSheet(DateUtils.getDate(), 0);
		sheet = workBook.getSheet(0);
		sheet.getSettings().setDefaultColumnWidth(20);
	}
	
	public void createSheet(int sheetNumber) {
		workBook.createSheet("Report", sheetNumber);
		sheet = workBook.getSheet(sheetNumber);
	}
	
	public void createSheet(int sheetNumber, String sheetName) {
		workBook.createSheet(sheetName, sheetNumber);
		sheet = workBook.getSheet(sheetNumber);
	}
	
	public void addCell(int column, int row, Object value) {
		try {
			value = (value==null)?"":value;
			if(value.getClass().equals(Double.class) ) {
				sheet.addCell(new Number(column, row, Integer.parseInt((String) value), format));
			} else if(value.getClass().equals(String.class)) {
				sheet.addCell(new Label(column, row, value.toString(), format));
			}
		} catch (RowsExceededException e) {
			e.printStackTrace();
		} catch (WriteException e) {
			e.printStackTrace();
		} 
	} 
	
	public void writeWorkbook() {
		try {
			workBook.write();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void flushFOS() {
		try {
			fos.flush();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void writeExcel() {
		try {
			workBook.write();
			workBook.close();
			fos.close();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (WriteException e) {
			e.printStackTrace();
		}
	}
}