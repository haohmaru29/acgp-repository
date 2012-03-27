package Acc;

import java.util.ArrayList;
import java.util.StringTokenizer;

import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.naming.Context;
import javax.naming.InitialContext;

public class correo {

    public correo() {
    }

    public void Envio(String s, String s1, String s2) {
        String s3 = "SERAPIS";
        ArrayList arraylist = new ArrayList();
        try {
            InitialContext initialcontext = new InitialContext();
            Context context = (Context)initialcontext.lookup("java:comp/env");
            Session session = (Session)context.lookup("mail/Session");
            String s5;
            for(StringTokenizer stringtokenizer = new StringTokenizer(s, ","); stringtokenizer.hasMoreTokens(); arraylist.add(new InternetAddress(s5)))
                s5 = stringtokenizer.nextToken();

            InternetAddress ainternetaddress[] = new InternetAddress[arraylist.size()];
            ainternetaddress = (InternetAddress[])arraylist.toArray(ainternetaddress);
            MimeMessage mimemessage = new MimeMessage(session);
            mimemessage.setFrom(new InternetAddress(s3));
            mimemessage.setRecipients(javax.mail.Message.RecipientType.TO, ainternetaddress);
            mimemessage.setSubject(s1);
            mimemessage.setContent(s2, "text/html");
            Transport.send(mimemessage);
        } catch(Throwable throwable) {
            throwable.printStackTrace();
        }
    }

    public String FormateaMail(String s, String s1, String s2, String s3, String s4, String s5, String s6,
            String s7, String s8, String s9, String s10, String s11, String s12, String s13) {
        String s14 = "";
        s14 = "<html>";
        s14 = s14 + " <head>";
        s14 = s14 + " <title>SERAPIS. ISO 9001-2000.</title>";
        s14 = s14 + " <link rel='stylesheet' type='text/css'>";
        s14 = s14 + " <style>";
        s14 = s14 + " .titulos";
        s14 = s14 + " {";
        s14 = s14 + " \tfont-family: Verdana, Arial, Helvetica, sans-serif;";
        s14 = s14 + " \tcolor: #953611;";
        s14 = s14 + " \tfont-size: 14px;";
        s14 = s14 + " \tfont-weight: bold;";
        s14 = s14 + " \ttext-decoration: none;";
        s14 = s14 + " \theight: 25px;";
        s14 = s14 + " \tvertical-align: middle;";
        s14 = s14 + " \tbackground-color: #FAE5B9;";
        s14 = s14 + " \ttext-indent: 10pt;";
        s14 = s14 + " }";
        s14 = s14 + " .textogral";
        s14 = s14 + " {";
        s14 = s14 + " \tfont-family: Verdana, Arial, Helvetica, sans-serif;";
        s14 = s14 + " \tfont-size: 12px;";
        s14 = s14 + " \tfont-weight: normal;";
        s14 = s14 + " \tcolor: #333333;";
        s14 = s14 + " \ttext-align: justify;";
        s14 = s14 + " \theight: 16px;";
        s14 = s14 + " }";
        s14 = s14 + " .textoaviso";
        s14 = s14 + " {";
        s14 = s14 + " \tfont-family: Verdana, Arial, Helvetica, sans-serif;";
        s14 = s14 + " \tfont-size: 10px;";
        s14 = s14 + " \tfont-weight: normal;";
        s14 = s14 + " \tcolor: #333333;";
        s14 = s14 + " \ttext-align: justify;";
        s14 = s14 + " \theight: 16px;";
        s14 = s14 + " }";
        s14 = s14 + " </style>";
        s14 = s14 + " <body>";
        s14 = s14 + " <body>";
        s14 = s14 + " <table width='95%' border='0' align='center' cellpadding='0' cellspacing='0'>";
        s14 = s14 + "   <tr>";
        s14 = s14 + "     <td valign='middle' align='left' class='titulos' colspan='2'>" + s + "</td>";
        s14 = s14 + "   </tr>";
        s14 = s14 + "   <td align='center'  valign='top' colspan='2' class='textogral'>&#32;</td>";
        s14 = s14 + "   <tr>";
        s14 = s14 + "     <td align='left' colspan='2'  valign='top' class='textogral'><B>" + s1 + "</B></td>";
        s14 = s14 + "   </tr>";
        s14 = s14 + "   <td align='center'  valign='top' colspan='2' class='textogral'>&#32;</td>";
        if(s2.length() > 0)
        {
            s14 = s14 + "   <tr>";
            s14 = s14 + "     <td width='30%' align='left' class='textogral' valign='top'>" + s2 + "</td>";
            s14 = s14 + "     <td width='70%' align='left' class='textogral' valign='top'>" + s7 + "</td>";
            s14 = s14 + "   </tr>";
        }
        if(s3.length() > 0)
        {
            s14 = s14 + "   <tr>";
            s14 = s14 + "     <td width='30%' align='left' class='textogral' valign='top'>" + s3 + "</td>";
            s14 = s14 + "     <td width='70%' align='left' class='textogral' valign='top'>" + s8 + "</td>";
            s14 = s14 + "   </tr>";
        }
        if(s4.length() > 0)
        {
            s14 = s14 + "   <tr>";
            s14 = s14 + "     <td width='30%' align='left' class='textogral' valign='top'>" + s4 + "</td>";
            s14 = s14 + "     <td width='70%' align='left' class='textogral' valign='top'>" + s9 + "</td>";
            s14 = s14 + "   </tr>";
        }
        if(s5.length() > 0)
        {
            s14 = s14 + "   <tr>";
            s14 = s14 + "     <td width='30%' align='left' class='textogral' valign='top'>" + s5 + "</td>";
            s14 = s14 + "     <td width='70%' align='left' class='textogral' valign='top'>" + s10 + "</td>";
            s14 = s14 + "   </tr>";
        }
        if(s6.length() > 0)
        {
            s14 = s14 + "   <tr>";
            s14 = s14 + "     <td width='30%' align='left' class='textogral' valign='top'>" + s6 + "</td>";
            s14 = s14 + "     <td width='70%' align='left' class='textogral' valign='top'>" + s11 + "</td>";
            s14 = s14 + "   </tr>";
        }
        if(s12.length() > 0)
        {
            s14 = s14 + "<tr>";
            s14 = s14 + "  <td align='center' colspan='2' class='textogral' valign='top'>&#32;</td>";
            s14 = s14 + "</tr>";
            s14 = s14 + "<tr>";
            s14 = s14 + "  <td align='center' colspan='2' class='textogral' valign='top'>" + s12 + "</td>";
            s14 = s14 + "</tr>";
        }
        if(s13.length() > 0)
        {
            s14 = s14 + "<tr>";
            s14 = s14 + "  <td align='center' colspan='2' class='textogral' valign='top'>&#32;</td>";
            s14 = s14 + "</tr>";
            s14 = s14 + "<tr>";
            s14 = s14 + "  <td class='textoaviso' align='center' colspan='2' valign='top'>" + s13 + "</td>";
            s14 = s14 + "</tr>";
        }
        s14 = s14 + "</table>";
        s14 = s14 + "</body>";
        s14 = s14 + "</html>";
        return s14;
    }
}
