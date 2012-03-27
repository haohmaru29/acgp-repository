package Acc;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Calendar;
import java.util.Vector;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class funciones {

    private int ObtieneNumero(char c) {
        int i = 65535;
        switch(c)
        {
        case 65: // 'A'
            i = 12;
            break;

        case 66: // 'B'
            i = 3;
            break;

        case 67: // 'C'
            i = 133;
            break;

        case 68: // 'D'
            i = 138;
            break;

        case 69: // 'E'
            i = 118;
            break;

        case 70: // 'F'
            i = 40;
            break;

        case 71: // 'G'
            i = 136;
            break;

        case 72: // 'H'
            i = 137;
            break;

        case 73: // 'I'
            i = 184;
            break;

        case 74: // 'J'
            i = 9;
            break;

        case 75: // 'K'
            i = 86;
            break;

        case 76: // 'L'
            i = 60;
            break;

        case 77: // 'M'
            i = 194;
            break;

        case 78: // 'N'
            i = 229;
            break;

        case 209:
            i = 66;
            break;

        case 79: // 'O'
            i = 217;
            break;

        case 80: // 'P'
            i = 244;
            break;

        case 81: // 'Q'
            i = 208;
            break;

        case 82: // 'R'
            i = 80;
            break;

        case 83: // 'S'
            i = 144;
            break;

        case 84: // 'T'
            i = 151;
            break;

        case 85: // 'U'
            i = 166;
            break;

        case 86: // 'V'
            i = 223;
            break;

        case 87: // 'W'
            i = 159;
            break;

        case 88: // 'X'
            i = 162;
            break;

        case 89: // 'Y'
            i = 241;
            break;

        case 90: // 'Z'
            i = 11;
            break;

        case 97: // 'a'
            i = 104;
            break;

        case 98: // 'b'
            i = 51;
            break;

        case 99: // 'c'
            i = 90;
            break;

        case 100: // 'd'
            i = 204;
            break;

        case 101: // 'e'
            i = 146;
            break;

        case 102: // 'f'
            i = 75;
            break;

        case 103: // 'g'
            i = 198;
            break;

        case 104: // 'h'
            i = 121;
            break;

        case 105: // 'i'
            i = 254;
            break;

        case 106: // 'j'
            i = 129;
            break;

        case 107: // 'k'
            i = 41;
            break;

        case 108: // 'l'
            i = 32;
            break;

        case 109: // 'm'
            i = 190;
            break;

        case 110: // 'n'
            i = 91;
            break;

        case 241:
            i = 175;
            break;

        case 111: // 'o'
            i = 189;
            break;

        case 112: // 'p'
            i = 25;
            break;

        case 113: // 'q'
            i = 6;
            break;

        case 114: // 'r'
            i = 218;
            break;

        case 115: // 's'
            i = 34;
            break;

        case 116: // 't'
            i = 155;
            break;

        case 117: // 'u'
            i = 20;
            break;

        case 118: // 'v'
            i = 165;
            break;

        case 119: // 'w'
            i = 117;
            break;

        case 120: // 'x'
            i = 109;
            break;

        case 121: // 'y'
            i = 67;
            break;

        case 122: // 'z'
            i = 63;
            break;

        case 48: // '0'
            i = 77;
            break;

        case 49: // '1'
            i = 62;
            break;

        case 50: // '2'
            i = 147;
            break;

        case 51: // '3'
            i = 228;
            break;

        case 52: // '4'
            i = 256;
            break;

        case 53: // '5'
            i = 220;
            break;

        case 54: // '6'
            i = 239;
            break;

        case 55: // '7'
            i = 128;
            break;

        case 56: // '8'
            i = 30;
            break;

        case 57: // '9'
            i = 28;
            break;

        case 46: // '.'
            i = 8;
            break;

        case 44: // ','
            i = 16;
            break;

        case 59: // ';'
            i = 125;
            break;

        case 95: // '_'
            i = 123;
            break;

        case 43: // '+'
            i = 50;
            break;

        case 45: // '-'
            i = 150;
            break;

        case 32: // ' '
            i = 272;
            break;

        case 225:
            i = 31;
            break;

        case 233:
            i = 76;
            break;

        case 237:
            i = 27;
            break;

        case 243:
            i = 19;
            break;

        case 250:
            i = 227;
            break;

        case 193:
            i = 249;
            break;

        case 201:
            i = 243;
            break;

        case 205:
            i = 1;
            break;

        case 211:
            i = 212;
            break;

        case 218:
            i = 188;
            break;
        }
        return i;
    }

    private char ObtieneLetra(int i)
    {
        char c = ' ';
        switch(i)
        {
        case 12: // '\f'
            c = 'A';
            break;

        case 3: // '\003'
            c = 'B';
            break;

        case 133:
            c = 'C';
            break;

        case 138:
            c = 'D';
            break;

        case 118: // 'v'
            c = 'E';
            break;

        case 40: // '('
            c = 'F';
            break;

        case 136:
            c = 'G';
            break;

        case 137:
            c = 'H';
            break;

        case 184:
            c = 'I';
            break;

        case 9: // '\t'
            c = 'J';
            break;

        case 86: // 'V'
            c = 'K';
            break;

        case 60: // '<'
            c = 'L';
            break;

        case 194:
            c = 'M';
            break;

        case 229:
            c = 'N';
            break;

        case 66: // 'B'
            c = '\321';
            break;

        case 217:
            c = 'O';
            break;

        case 244:
            c = 'P';
            break;

        case 208:
            c = 'Q';
            break;

        case 80: // 'P'
            c = 'R';
            break;

        case 144:
            c = 'S';
            break;

        case 151:
            c = 'T';
            break;

        case 166:
            c = 'U';
            break;

        case 223:
            c = 'V';
            break;

        case 159:
            c = 'W';
            break;

        case 162:
            c = 'X';
            break;

        case 241:
            c = 'Y';
            break;

        case 11: // '\013'
            c = 'Z';
            break;

        case 104: // 'h'
            c = 'a';
            break;

        case 51: // '3'
            c = 'b';
            break;

        case 90: // 'Z'
            c = 'c';
            break;

        case 204:
            c = 'd';
            break;

        case 146:
            c = 'e';
            break;

        case 75: // 'K'
            c = 'f';
            break;

        case 198:
            c = 'g';
            break;

        case 121: // 'y'
            c = 'h';
            break;

        case 254:
            c = 'i';
            break;

        case 129:
            c = 'j';
            break;

        case 41: // ')'
            c = 'k';
            break;

        case 32: // ' '
            c = 'l';
            break;

        case 190:
            c = 'm';
            break;

        case 91: // '['
            c = 'n';
            break;

        case 175:
            c = '\361';
            break;

        case 189:
            c = 'o';
            break;

        case 25: // '\031'
            c = 'p';
            break;

        case 6: // '\006'
            c = 'q';
            break;

        case 218:
            c = 'r';
            break;

        case 34: // '"'
            c = 's';
            break;

        case 155:
            c = 't';
            break;

        case 20: // '\024'
            c = 'u';
            break;

        case 165:
            c = 'v';
            break;

        case 117: // 'u'
            c = 'w';
            break;

        case 109: // 'm'
            c = 'x';
            break;

        case 67: // 'C'
            c = 'y';
            break;

        case 63: // '?'
            c = 'z';
            break;

        case 77: // 'M'
            c = '0';
            break;

        case 62: // '>'
            c = '1';
            break;

        case 147:
            c = '2';
            break;

        case 228:
            c = '3';
            break;

        case 256:
            c = '4';
            break;

        case 220:
            c = '5';
            break;

        case 239:
            c = '6';
            break;

        case 128:
            c = '7';
            break;

        case 30: // '\036'
            c = '8';
            break;

        case 28: // '\034'
            c = '9';
            break;

        case 8: // '\b'
            c = '.';
            break;

        case 16: // '\020'
            c = ',';
            break;

        case 125: // '}'
            c = ';';
            break;

        case 123: // '{'
            c = '_';
            break;

        case 50: // '2'
            c = '+';
            break;

        case 150:
            c = '-';
            break;

        case 272:
            c = ' ';
            break;

        case 31: // '\037'
            c = '\341';
            break;

        case 76: // 'L'
            c = '\351';
            break;

        case 27: // '\033'
            c = '\355';
            break;

        case 19: // '\023'
            c = '\363';
            break;

        case 227:
            c = '\372';
            break;

        case 249:
            c = '\301';
            break;

        case 243:
            c = '\311';
            break;

        case 1: // '\001'
            c = '\315';
            break;

        case 212:
            c = '\323';
            break;

        case 188:
            c = '\332';
            break;
        }
        return c;
    }

    public int EsValida(PrintWriter printwriter, String s)
    {
        byte byte0 = 1;
        int i = 0;
        do
        {
            if(i >= s.length())
                break;
            char c = s.substring(i, i + 1).charAt(0);
            int j = ObtieneNumero(c);
            if(j == -1)
            {
                byte0 = -1;
                break;
            }
            i++;
        } while(true);
        return byte0;
    }

    public int ObtieneDatoFecha(int i)
    {
        int j = 1;
        Calendar calendar = Calendar.getInstance();
        switch(i)
        {
        case 1: // '\001'
            //Calendar calendar1 = calendar;
            j = calendar.get(5);
            break;

        case 2: // '\002'
            //Calendar calendar2 = calendar;
            j = calendar.get(2);
            break;

        case 3: // '\003'
            //Calendar calendar3 = calendar;
            j = calendar.get(1);
            break;
        }
        return j;
    }

    public int ObtieneSumaDia()
    {
        byte byte0 = 0;
        Calendar calendar = Calendar.getInstance();
        int i = calendar.get(7);
        switch(i)
        {
        case 1: // '\001'
            byte0 = -6;
            break;

        case 2: // '\002'
            byte0 = 0;
            break;

        case 3: // '\003'
            byte0 = -1;
            break;

        case 4: // '\004'
            byte0 = -2;
            break;

        case 5: // '\005'
            byte0 = -3;
            break;

        case 6: // '\006'
            byte0 = -4;
            break;

        case 7: // '\007'
            byte0 = -5;
            break;
        }
        return byte0;
    }

    public Vector ObtieneFechaHora()
    {
        String s = "";
        String s1 = "";
        Calendar calendar = Calendar.getInstance();
        int i = 0;
        int j = 0;
        int k = 0;
        int l = 0;
        int i1 = 0;
        int j1 = 0;
        i = calendar.get(5);
        j = calendar.get(2) + 1;
        k = calendar.get(1);
        l = calendar.get(11);
        i1 = calendar.get(12);
        j1 = calendar.get(13);
        s = "" + k;
        if(j < 10)
            s = s + "0" + j;
        else
            s = s + j;
        if(i < 10)
            s = s + "0" + i;
        else
            s = s + i;
        s1 = "" + l;
        if(i1 < 10)
            s1 = s1 + "0" + i1;
        else
            s1 = s1 + i1;
        if(j1 < 10)
            s1 = s1 + "0" + j1;
        else
            s1 = s1 + j1;
        Vector vector = new Vector();
        Integer integer = new Integer(s);
        vector.add(integer);
        integer = new Integer(s1);
        vector.add(integer);
        return vector;
    }

    public String ConstruyeFecha(String s, String s1, String s2)
    {
        String s3 = "";
        String s4 = "";
        String s5 = "";
        String s6 = "";
        s4 = s.substring(6, 8);
        s5 = s.substring(4, 6);
        s6 = s.substring(0, 4);
        if(s2.compareTo("dmy") == 0)
            s3 = s4 + s1 + s5 + s1 + s6;
        if(s2.compareTo("ymd") == 0)
            s3 = s6 + s1 + s5 + s1 + s4;
        return s3;
    }

    public String ConstruyeHora(String s, String s1)
    {
        String s2 = "";
        String s3 = "";
        String s4 = "";
        String s5 = "";
        if(s.length() > 0)
            if(s.length() == 6)
            {
                s3 = s.substring(0, 2);
                s4 = s.substring(2, 4);
                s5 = s.substring(4, 6);
            } else
            {
                s3 = "0" + s.substring(0, 1);
                s4 = s.substring(1, 3);
                s5 = s.substring(3, 5);
            }
        s2 = s3 + s1 + s4 + s1 + s5;
        return s2;
    }

    private String ConstruyeFecha(Calendar calendar, String s)
    {
        String s1 = "";
        //Calendar calendar1 = calendar;
        Integer integer = new Integer(calendar.get(5));
        //Calendar calendar2 = calendar;
        Integer integer1 = new Integer(calendar.get(2) + 1);
        //Calendar calendar3 = calendar;
        Integer integer2 = new Integer(calendar.get(1));
        if(s.compareTo("dmy") == 0)
        {
            if(integer.intValue() < 10)
                s1 = "0" + integer.toString();
            else
                s1 = integer.toString();
            if(integer1.intValue() < 10)
                s1 = s1 + "0" + integer1.toString();
            else
                s1 = s1 + integer1.toString();
            s1 = s1 + integer2.toString();
        }
        if(s.compareTo("ymd") == 0)
        {
            s1 = integer2.toString();
            if(integer1.intValue() < 10)
                s1 = s1 + "0" + integer1.toString();
            else
                s1 = s1 + integer1.toString();
            if(integer.intValue() < 10)
                s1 = s1 + "0" + integer.toString();
            else
                s1 = s1 + integer.toString();
        }
        return s1;
    }

    public Vector ConstruyeFecha(String s)
    {
        Vector vector = new Vector();
        String s1 = s.substring(6, 8);
        vector.add(s1);
        s1 = s.substring(4, 6);
        vector.add(s1);
        s1 = s.substring(0, 4);
        vector.add(s1);
        return vector;
    }

    public Vector ObtieneSemana()
    {
        //boolean flag = false;
        //boolean flag1 = false;
        //boolean flag2 = false;
        Vector vector = new Vector();
        Integer integer = new Integer(0);
        Integer integer1 = new Integer(0);
        Calendar calendar = Calendar.getInstance();
        //Calendar calendar1 = calendar;
        int i = calendar.get(7);
        switch(i)
        {
        case 1: // '\001'
            //Calendar calendar2 = calendar;
            calendar.add(5, -6);
            break;

        case 2: // '\002'
            //Calendar calendar4 = calendar;
            calendar.add(5, 0);
            break;

        case 3: // '\003'
            //Calendar calendar6 = calendar;
            calendar.add(5, -1);
            break;

        case 4: // '\004'
            //Calendar calendar8 = calendar;
            calendar.add(5, -2);
            break;

        case 5: // '\005'
           // Calendar calendar10 = calendar;
            calendar.add(5, -3);
            break;

        case 6: // '\006'
            //Calendar calendar12 = calendar;
            calendar.add(5, -4);
            break;

        case 7: // '\007'
            //Calendar calendar14 = calendar;
            calendar.add(5, -5);
            break;
        }
        //Calendar calendar3 = calendar;
        integer = new Integer(calendar.get(5));
        vector.add(integer);
        integer1 = new Integer(ConstruyeFecha(calendar, "ymd"));
        vector.add(integer1);
        calendar.add(5, 1);
        integer = new Integer(calendar.get(5));
        vector.add(integer);
        integer1 = new Integer(ConstruyeFecha(calendar, "ymd"));
        vector.add(integer1);
        calendar.add(5, 1);
        integer = new Integer(calendar.get(5));
        vector.add(integer);
        integer1 = new Integer(ConstruyeFecha(calendar, "ymd"));
        vector.add(integer1);
        calendar.add(5, 1);
        integer = new Integer(calendar.get(5));
        vector.add(integer);
        integer1 = new Integer(ConstruyeFecha(calendar, "ymd"));
        vector.add(integer1);
        //Calendar calendar16 = calendar;
        calendar.add(5, 1);
        //Calendar calendar17 = calendar;
        integer = new Integer(calendar.get(5));
        vector.add(integer);
        integer1 = new Integer(ConstruyeFecha(calendar, "ymd"));
        vector.add(integer1);
        //Calendar calendar18 = calendar;
        calendar.add(5, 1);
        //Calendar calendar19 = calendar;
        integer = new Integer(calendar.get(5));
        vector.add(integer);
        integer1 = new Integer(ConstruyeFecha(calendar, "ymd"));
        vector.add(integer1);
        //Calendar calendar20 = calendar;
        calendar.add(5, 1);
        //Calendar calendar21 = calendar;
        integer = new Integer(calendar.get(5));
        vector.add(integer);
        integer1 = new Integer(ConstruyeFecha(calendar, "ymd"));
        vector.add(integer1);
        return vector;
    }

    public Vector AddFecha(String s, int i)
    {
        Vector vector = new Vector();
        Integer integer = new Integer(0);
        int j = 0;
        int k = 0;
        int l = 0;
        String s1 = s.substring(6, 8);
        integer = new Integer(s1);
        j = integer.intValue();
        s1 = s.substring(4, 6);
        integer = new Integer(s1);
        k = integer.intValue() - 1;
        s1 = s.substring(0, 4);
        integer = new Integer(s1);
        l = integer.intValue();
        Calendar calendar = Calendar.getInstance();
        calendar.set(l, k, j);
        //Calendar calendar1 = calendar;
        calendar.add(5, i);
        s1 = ConstruyeFecha(calendar, "ymd");
        vector.add(s1);
        s1 = ConstruyeFecha(s1, "/", "ymd");
        vector.add(s1);
        return vector;
    }

    public Vector ObtieneSemana(String s)
    {
        Vector vector = new Vector();
        Integer integer = new Integer(0);
        Integer integer1 = new Integer(0);
        int i = 0;
        int j = 0;
        int k = 0;
        String s1 = s.substring(6, 8);
        integer1 = new Integer(s1);
        i = integer1.intValue();
        s1 = s.substring(4, 6);
        integer1 = new Integer(s1);
        j = integer1.intValue() - 1;
        s1 = s.substring(0, 4);
        integer1 = new Integer(s1);
        k = integer1.intValue();
        Calendar calendar = Calendar.getInstance();
        calendar.set(k, j, i);
        //Calendar calendar1 = calendar;
        int l = calendar.get(7);
        switch(l)
        {
        case 1: // '\001'
            //Calendar calendar2 = calendar;
            calendar.add(5, -6);
            break;

        case 2: // '\002'
            //Calendar calendar4 = calendar;
            calendar.add(5, 0);
            break;

        case 3: // '\003'
            //Calendar calendar6 = calendar;
            calendar.add(5, -1);
            break;

        case 4: // '\004'
            //Calendar calendar8 = calendar;
            calendar.add(5, -2);
            break;

        case 5: // '\005'
            //Calendar calendar10 = calendar;
            calendar.add(5, -3);
            break;

        case 6: // '\006'
            //Calendar calendar12 = calendar;
            calendar.add(5, -4);
            break;

        case 7: // '\007'
            //Calendar calendar14 = calendar;
            calendar.add(5, -5);
            break;
        }
        //Calendar calendar3 = calendar;
        integer = new Integer(calendar.get(5));
        vector.add(integer);
        integer1 = new Integer(ConstruyeFecha(calendar, "ymd"));
        vector.add(integer1);
        //Calendar calendar5 = calendar;
        calendar.add(5, 1);
        //Calendar calendar7 = calendar;
        integer = new Integer(calendar.get(5));
        vector.add(integer);
        integer1 = new Integer(ConstruyeFecha(calendar, "ymd"));
        vector.add(integer1);
        //Calendar calendar9 = calendar;
        calendar.add(5, 1);
        //Calendar calendar11 = calendar;
        integer = new Integer(calendar.get(5));
        vector.add(integer);
        integer1 = new Integer(ConstruyeFecha(calendar, "ymd"));
        vector.add(integer1);
        //Calendar calendar13 = calendar;
        calendar.add(5, 1);
        //Calendar calendar15 = calendar;
        integer = new Integer(calendar.get(5));
        vector.add(integer);
        integer1 = new Integer(ConstruyeFecha(calendar, "ymd"));
        vector.add(integer1);
        //Calendar calendar16 = calendar;
        calendar.add(5, 1);
        //Calendar calendar17 = calendar;
        integer = new Integer(calendar.get(5));
        vector.add(integer);
        integer1 = new Integer(ConstruyeFecha(calendar, "ymd"));
        vector.add(integer1);
        //Calendar calendar18 = calendar;
        calendar.add(5, 1);
        //Calendar calendar19 = calendar;
        integer = new Integer(calendar.get(5));
        vector.add(integer);
        integer1 = new Integer(ConstruyeFecha(calendar, "ymd"));
        vector.add(integer1);
        //Calendar calendar20 = calendar;
        calendar.add(5, 1);
        //Calendar calendar21 = calendar;
        integer = new Integer(calendar.get(5));
        vector.add(integer);
        integer1 = new Integer(ConstruyeFecha(calendar, "ymd"));
        vector.add(integer1);
        return vector;
    }

    public int SiguienteDia(int i, int j, int k, int l)
    {
        Calendar calendar = Calendar.getInstance();
        calendar.set(k, j, i);
        //Calendar calendar1 = calendar;
        calendar.add(5, l);
        //Calendar calendar2 = calendar;
        int i1 = calendar.get(5);
        return i1;
    }

    public Vector ObtieneFecha()
    {
        Vector vector = new Vector();
        //Integer integer = new Integer(0);
        Calendar calendar = Calendar.getInstance();
        String s = ConstruyeFecha(calendar, "ymd");
        vector.add(s);
        s = ConstruyeFecha(s, "/", "ymd");
        vector.add(s);
        return vector;
    }

    public int ObtieneAnio()
    {
        Calendar calendar = Calendar.getInstance();
        int i = calendar.get(1);
        return i;
    }

    public int ObtieneMes()
    {
        Calendar calendar = Calendar.getInstance();
        int i = calendar.get(2) + 1;
        return i;
    }

    public String ObtieneFechaActual()
    {
        String s = "";
        Integer integer = new Integer(0);
        Calendar calendar = Calendar.getInstance();
        int i = calendar.get(5);
        int j = calendar.get(2) + 1;
        int k = calendar.get(1);
        integer = new Integer(i);
        if(i < 10)
            s = "0" + integer.toString();
        else
            s = integer.toString();
        s = s + "/";
        integer = new Integer(j);
        if(j < 10)
            s = s + "0" + integer.toString();
        else
            s = s + integer.toString();
        s = s + "/";
        integer = new Integer(k);
        s = s + integer.toString();
        return s;
    }

    public String FormateaDato(String s, int i, String s1, String s2)
    {
        String s3 = "";
       // boolean flag = false;
        s3 = s;
        if(s3.length() > i)
        {
            s3 = s3.substring(0, i);
        } else
        {
            if(s2.compareTo("I") == 0)
            {
                int j = s3.length();
                for(int l = j; l < i; l++)
                    s3 = s1 + s3;

            }
            if(s2.compareTo("D") == 0)
            {
                int k = s3.length();
                for(int i1 = k; i1 < i; i1++)
                    s3 = s3 + s1;

            }
        }
        return s3;
    }

    public String encripta(String s)
    {
        int i = 7;
        s = s.trim();
        int j = s.length();
        int k = j;
        Integer integer = new Integer(k);
        String s1 = FormateaDato(integer.toString(), 3, "0", "I");
        for(int l = 0; l <= j - 1; l++)
        {
            char c = s.substring(l, l + 1).charAt(0);
            int i1 = ObtieneNumero(c);
            i1 *= i;
            Integer integer1 = new Integer(i1);
            String s2 = integer1.toString();
            s2 = s2.length() + s2;
            s1 = s1 + s2;
            if(--i < 4)
                i = 7;
        }

        return s1;
    }

    public String desencripta(String s)
    {
        String s1 = "";
        int i = 7;
        String s2 = s.substring(0, 3);
        Integer integer = new Integer(s2);
        int j = integer.intValue();
        int k = 1;
        int l;
        for(int i1 = 3; k <= j; i1 = i1 + 1 + l)
        {
            String s3 = s.substring(i1, i1 + 1);
            Integer integer1 = new Integer(s3);
            l = integer1.intValue();
            s3 = s.substring(i1 + 1, i1 + 1 + l);
            integer1 = new Integer(s3);
            int j1 = integer1.intValue();
            j1 /= i;
            char c = ObtieneLetra(j1);
            s1 = s1 + c;
            if(--i < 4)
                i = 7;
            k++;
        }

        return s1;
    }

    public int CreaDir(String s, PrintWriter printwriter)
    {
        int i = 0;
        File file = new File(s);
        if(!file.exists())
        {
            if(file.mkdir())
                i = 1;
            else
                i = 0;
        } else
        {
            i = 1;
        }
        return i;
    }

    public int CreaDirectorio(String s, String s1, PrintWriter printwriter)
    {
        int i = 0;
        String s2 = "C:\\Archivos de programa\\Apache Tomcat 4.0\\webapps\\serapis\\sad\\proyecto\\";
        String s3 = s2 + "\\" + s + "\\";
        i = CreaDir(s3, printwriter);
        System.out.println("ret = " + i);
        if(i != 0)
        {
            String s4 = s2 + "\\" + s + "\\" + s1 + "\\";
            i = CreaDir(s4, printwriter);
            System.out.println("ret = " + i);
        }
        return i;
    }

    public int CreaDirectorio(String s, String s1, String s2, PrintWriter printwriter)
    {
        int i = 0;
        String s3 = s + "sad\\proyecto\\";
        String s4 = s3 + "\\" + s1 + "\\";
        i = CreaDir(s4, printwriter);
        //System.out.println("ret = " + i);
        if(i != 0)
        {
            String s5 = s3 + "\\" + s1 + "\\" + s2 + "\\";
            i = CreaDir(s5, printwriter);
           // System.out.println("ret = " + i);
        }
        return i;
    }

    public String leer(String s)
    {
        String s1 = "";
        try
        {
            FileReader filereader = new FileReader(s);
            BufferedReader bufferedreader = new BufferedReader(filereader);
            String s3;
            while((s3 = bufferedreader.readLine()) != null)
                s1 = s1 + s3 + "  \n";
        }
        catch(FileNotFoundException filenotfoundexception)
        {
            s1 = "";
        }
        catch(IOException ioexception)
        {
            s1 = "";
        }
        return s1;
    }

    public int ExisteArchivo(String s)
    {
        int i = 0;
        //System.out.println("archivo" + s);
        try
        {
            new FileReader(s);
            i = 1;
        }
        catch(FileNotFoundException filenotfoundexception)
        {
            i = 0;
        }
        catch(IOException ioexception)
        {
            i = 0;
        }
        return i;
    }

    public void reindex(HttpServletRequest httpservletrequest, PrintWriter printwriter, int i, String s, int j)
    {
        HttpSession httpsession = httpservletrequest.getSession(true);
        if(s.length() > 0)
            httpsession.putValue("SerapisSis", s);
        printwriter.println("<HTML>");
        printwriter.println("<HEAD>");
        printwriter.println("<title>SERAPIS. Sistema de Gesti\363n de Calidad.</title>");
        printwriter.println("</HEAD>");
        printwriter.println("</HEAD>");
        printwriter.println("<script language=\"javascript\">");
        if(i == 0)
            printwriter.println("window.open(\"autentica.jsp?ERR=" + j + "\",\"cuerpo\");");
        else
            printwriter.println("window.open(\"../autentica.jsp?ERR=" + j + "\",\"cuerpo\");");
        printwriter.println("</script>");
        printwriter.println("<body bgcolor='#FFFFFF'>");
        printwriter.println("</BODY>");
        printwriter.println("</HTML>");
    }

    public void errorlicencia(PrintWriter printwriter, int i)
    {
        printwriter.println("<html>");
        printwriter.println("<head>");
        printwriter.println("</head>");
        if(i == 0)
            printwriter.println("<LINK REL='stylesheet' TYPE='text/css' HREF='serapis.css'>");
        else
            printwriter.println("<LINK REL='stylesheet' TYPE='text/css' HREF='../serapis.css'>");
        printwriter.println("<TITLE>SERAPIS. Sistema de Gesti\363n de Calidad.</TITLE>");
        printwriter.println("<frameset framespacing='0' border='true' frameborder='0' rows='68px,*'>");
        if(i == 0)
        {
            printwriter.println("  <frame name='encabezado' src='encabezado.jsp' scrolling='no' noresize>");
            printwriter.println("  <frame name='cuerpo' src='errlicencia.jsp' noresize>");
        } else
        {
            printwriter.println("  <frame name='encabezado' src='../encabezado.jsp' scrolling='no' noresize>");
            printwriter.println("  <frame name='cuerpo' src='../errlicencia.jsp' noresize>");
        }
        printwriter.println("</frameset>");
        printwriter.println("</body>");
    }

    public void cargainicio(PrintWriter printwriter, int i)
    {
        printwriter.println("<html>");
        printwriter.println("<head>");
        printwriter.println("</head>");
        if(i == 0)
            printwriter.println("<LINK REL='stylesheet' TYPE='text/css' HREF='serapis.css'>");
        else
            printwriter.println("<LINK REL='stylesheet' TYPE='text/css' HREF='../serapis.css'>");
        printwriter.println("<TITLE>SERAPIS. Sistema de Gesti\363n de Calidad.</TITLE>");
        printwriter.println("<frameset framespacing='0' border='true' frameborder='0' rows='68px,*'>");
        if(i == 0)
        {
            printwriter.println("  <frame name='encabezado' src='encabezado.jsp' scrolling='no' noresize>");
            printwriter.println("  <frame name='cuerpo' src='cuerpo.jsp' noresize>");
        } else
        {
            printwriter.println("  <frame name='encabezado' src='../encabezado.jsp' scrolling='no' noresize>");
            printwriter.println("  <frame name='cuerpo' src='../cuerpo.jsp' noresize>");
        }
        printwriter.println("</frameset>");
        printwriter.println("</body>");
    }

    public void cargamenu(PrintWriter printwriter, int i)
    {
        if(i == 0)
        {
            printwriter.println("<script type='text/javascript' language='JavaScript1.2' src='menu/stm31.js'></script>");
            printwriter.println("<script type='text/javascript' language='JavaScript1.2' src='menu/menu01.js'></script>");
        } else
        {
            printwriter.println("<script type='text/javascript' language='JavaScript1.2' src='../menu/stm31.js'></script>");
            printwriter.println("<script type='text/javascript' language='JavaScript1.2' src='../menu/menu01n1.js'></script>");
        }
        printwriter.println("<BR><BR><BR>");
    }

    public void cargamenucompleto(PrintWriter printwriter, int i, String s)
    {
        if(i == 0)
        {
            printwriter.println("<script type='text/javascript' language='JavaScript1.2' src='menu/stm31.js'></script>");
            printwriter.println("<script type='text/javascript' language='JavaScript1.2' src='menu/menu01.js'></script>");
        } else
        {
            printwriter.println("<script type='text/javascript' language='JavaScript1.2' src='../menu/stm31.js'></script>");
            printwriter.println("<script type='text/javascript' language='JavaScript1.2' src='../menu/menu01n1.js'></script>");
        }
        printwriter.println("<BR><BR><BR>");
    }
}