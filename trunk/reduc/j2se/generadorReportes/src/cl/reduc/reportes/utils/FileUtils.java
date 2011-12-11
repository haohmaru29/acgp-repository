package cl.reduc.reportes.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public class FileUtils {

	public static boolean moveFile(String inputFile, String moveFile) {
		boolean response = false;
		try {
			File f = new File(inputFile);
			File f2 = new File(moveFile);
			if (f.exists() ) {
				InputStream in = new FileInputStream(f);
				OutputStream out = new FileOutputStream(f2);
				byte[] buf = new byte[1024];
				int len;
				while ((len = in.read(buf)) > 0) {
					out.write(buf, 0, len);
				}
				in.close();
				out.close();
				
				response = true;
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		return response;
	}

	public static boolean moveAndRenameFile(String filePath, String outPutPath, String fileName) {
		return moveFile(filePath,outPutPath);
	}
	
}
