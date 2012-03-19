package cl.reduc.commons.odbc.controller;

import cl.reduc.commons.utils.DateUtils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import jxl.Workbook;

import jxl.write.Label;
import jxl.write.Number;
import jxl.write.WritableCellFormat;
import jxl.write.WritableFont;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import jxl.write.WriteException;
import jxl.write.biff.RowsExceededException;

public class ExcelEngine {
      private WritableWorkbook workBook;
      private WritableSheet sheet;
      private Label label;
      private Number number;
      private File f;
      
      private WritableCellFormat format;
	
      public void setFormatTimes() {
          try {
              WritableFont times10pt = new WritableFont(WritableFont.TIMES, 10);
              format = new WritableCellFormat(times10pt);
              format.setWrap(true);
          } catch (WriteException e) {
              e.printStackTrace();
          }
      }
      
      public void setFormatArial() {
          try {
              WritableFont times10pt = new WritableFont(WritableFont.ARIAL, 10);
              format = new WritableCellFormat(times10pt);
              format.setWrap(true);
          } catch (WriteException e) {
              e.printStackTrace();
          }
      }
      
      public void setFormatArialBold() {
          try {
              WritableFont times10pt = new WritableFont(WritableFont.ARIAL, 10, WritableFont.BOLD);
              format = new WritableCellFormat(times10pt);
              format.setWrap(false);
          } catch (WriteException e) {
              e.printStackTrace();
          }
      }
      
      public void setFormatArialNoBold() {
          try {
              WritableFont times10pt = new WritableFont(WritableFont.ARIAL, 10, WritableFont.NO_BOLD);
              format = new WritableCellFormat(times10pt);
              format.setWrap(false);
          } catch (WriteException e) {
              e.printStackTrace();
          }
      }
	
      public void createWorkBook(File f) {
        this.f = f;
         try {
          workBook= Workbook.createWorkbook(f);
        } catch (IOException e) {
          e.printStackTrace();
        }
      }
      
      public void createWorkBook(String file) {
         try {
          workBook= Workbook.createWorkbook(new File(file));
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
                    number = new Number(column, row, Integer.parseInt((String) value), format);
                    sheet.addCell(number);
                } else if(value.getClass().equals(String.class)) {
                    label = new Label(column, row, value.toString(), format);
                    sheet.addCell(label);
                }
            } catch (RowsExceededException e) {
                e.printStackTrace();
            } catch (WriteException e) {
                e.printStackTrace();
            }
        } 
      
        public void writeExcel() {
            try {
                workBook.write();
                workBook.close();
              
            } catch (IOException e) {
                e.printStackTrace();
            } catch (WriteException e) {
                e.printStackTrace();
            }
        }
        
        
        public FileInputStream getFile() throws FileNotFoundException {
              return new FileInputStream( f );
        }
}