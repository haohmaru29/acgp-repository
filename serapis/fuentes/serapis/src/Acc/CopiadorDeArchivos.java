package Acc;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.channels.FileChannel;

public class CopiadorDeArchivos{

    public CopiadorDeArchivos() {
    }

    public static int copia2(String s, String s1)
        throws IOException {
        int i = 0;
        FileInputStream fileinputstream = new FileInputStream(s);
        FileOutputStream fileoutputstream = new FileOutputStream(s1);
        i = fileinputstream.available();
        fileinputstream.close();
        fileoutputstream.close();
        return i;
    }

    public static void copia(String s, String s1)
        throws IOException {
        FileInputStream fileinputstream = new FileInputStream(s);
        FileOutputStream fileoutputstream = new FileOutputStream(s1);
        FileChannel filechannel = fileinputstream.getChannel();
        FileChannel filechannel1 = fileoutputstream.getChannel();
        filechannel.transferTo(0L, filechannel.size(), filechannel1);
        fileinputstream.close();
        fileoutputstream.close();
    }

    public static void main(String args[])
        throws Exception {
        copia(args[0], args[1]);
    }
}