package cl.reduc.commons.ofimatica.excel;
import jxl.write.WritableCellFormat;
import jxl.write.WritableFont;
import jxl.write.WriteException;

public class Excel {
    
      protected WritableCellFormat format;
	
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

}