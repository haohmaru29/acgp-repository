package cl.bicevida.envionominas.model.utils;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import java.io.OutputStream;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.HSSFColor;

public class ExcelGenerator {
    public ExcelGenerator() {
    }

    public static void generateExcel( String nombre, String[] titulos, String[][] datos, OutputStream outputStream ) throws FileNotFoundException, IOException {
        FileOutputStream out = new FileOutputStream( "test.xls" );
        HSSFWorkbook wb = new HSSFWorkbook();
        HSSFSheet sheet = wb.createSheet( nombre );
        HSSFCellStyle style = null;
        HSSFRow row = null;
        HSSFCell cell;
        short rowNum = 0;
        short colNum = 0;
        /*Defino los estilos para cada parte del Excel*/
        HSSFFont fuenteEncabezado = wb.createFont();
        fuenteEncabezado.setColor( HSSFColor.DARK_BLUE.index );
        fuenteEncabezado.setFontName( "Verdana" );
        fuenteEncabezado.setFontHeightInPoints( ( short ) 8 );
        fuenteEncabezado.setBoldweight( HSSFFont.BOLDWEIGHT_BOLD );
        HSSFFont fuenteCeldas = wb.createFont();
        fuenteCeldas.setColor( HSSFColor.BLACK.index );
        fuenteCeldas.setFontName( "Verdana" );
        fuenteCeldas.setFontHeightInPoints( ( short ) 8 );
        fuenteCeldas.setBoldweight( HSSFFont.BOLDWEIGHT_BOLD );
        /*Fin definicion de fuentes */
        style = wb.createCellStyle();
        style.setFont( fuenteEncabezado );
        style.setAlignment( style.ALIGN_CENTER );
        /* Titulo de la excel */
        row = sheet.createRow( rowNum );
        for ( String s: titulos ) {
            cell = row.createCell( colNum );
            cell.setCellValue( s );
            cell.setCellStyle( style );
            colNum++;
        }
        rowNum++;
        for ( int i = 0; i < datos.length; i++ ) {
            row = sheet.createRow( rowNum );
            colNum = 0;
            for ( int j = 0; j < datos[ 0 ].length; j++ ) {
                cell = row.createCell( colNum++ );
                cell.setCellValue( datos[ i ][ j ] );
            }
            rowNum++;
        }
        wb.write( out );
        out.flush();
        if ( outputStream != null ) {
            wb.write( outputStream );
            outputStream.flush();
        }
    }
}
