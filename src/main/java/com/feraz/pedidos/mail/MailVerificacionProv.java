/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.feraz.pedidos.mail;

import com.feraz.pedidos.mail.*;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import javax.activation.DataHandler;
import javax.activation.FileDataSource;
import javax.mail.Authenticator;
import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import org.springframework.beans.factory.annotation.Value;

/**
 *
 * @author Ing. JAMH - Feraz
 */
public class MailVerificacionProv {

    @Value("${mail.correo}")
    private String correoOrigen;
    @Value("${mail.passwordCorreo}")
    private String password;
    @Value("${mail.usuario}")
    private String usuarioMail;
    @Value("${mail.hostSMTP}")
    private String host;
    private String starttls;
    @Value("${mail.puertoSMTP}")
    private String port;
    
  
    
    @Value("${mail.urlVerificacionVia}")
    private String urlCadena;
    
    @Value("${mail.imagenViaMasair}")
    private String imagen;
    
    private String user;
    private String auth;

    public MailVerificacionProv() {
    }

    public void sendMail(String cadenaVerificacion, String correoUsuario,String usuario, String compania,String orden, String reporte,String correoCopia) {
        
        

        try {
            System.out.println("pass:" + password);
            System.out.println("usuario:" + usuario);
           System.out.println("reporte:" + reporte);
          // System.out.println("imagen:"+ imagen+"logo_"+compania+".jpg");


            String msag = "<html xmlns:v=\"urn:schemas-microsoft-com:vml\" xmlns:o=\"urn:schemas-microsoft-com:office:office\" xmlns:w=\"urn:schemas-microsoft-com:office:word\" xmlns:m=\"http://schemas.microsoft.com/office/2004/12/omml\" xmlns=\"http://www.w3.org/TR/REC-html40\"><head><meta http-equiv=Content-Type content=\"text/html; charset=iso-8859-1\"><meta name=Generator content=\"Microsoft Word 14 (filtered medium)\"><!--[if !mso]><style>v\\:* {behavior:url(#default#VML);}\n"
                    + "o\\:* {behavior:url(#default#VML);}\n"
                    + "w\\:* {behavior:url(#default#VML);}\n"
                    + ".shape {behavior:url(#default#VML);}\n"
                    + "</style><![endif]--><style><!--\n"
                    + "/* Font Definitions */\n"
                    + "@font-face\n"
                    + "	{font-family:Calibri;\n"
                    + "	panose-1:2 15 5 2 2 2 4 3 2 4;}\n"
                    + "@font-face\n"
                    + "	{font-family:Tahoma;\n"
                    + "	panose-1:2 11 6 4 3 5 4 4 2 4;}\n"
                    + "/* Style Definitions */\n"
                    + "p.MsoNormal, li.MsoNormal, div.MsoNormal\n"
                    + "	{margin:0cm;\n"
                    + "	margin-bottom:.0001pt;\n"
                    + "	font-size:11.0pt;\n"
                    + "	font-family:\"Calibri\",\"sans-serif\";\n"
                    + "	mso-fareast-language:EN-US;}\n"
                    + "a:link, span.MsoHyperlink\n"
                    + "	{mso-style-priority:99;\n"
                    + "	color:blue;\n"
                    + "	text-decoration:underline;}\n"
                    + "a:visited, span.MsoHyperlinkFollowed\n"
                    + "	{mso-style-priority:99;\n"
                    + "	color:purple;\n"
                    + "	text-decoration:underline;}\n"
                    + "p.MsoAcetate, li.MsoAcetate, div.MsoAcetate\n"
                    + "	{mso-style-priority:99;\n"
                    + "	mso-style-link:\"Texto de globo Car\";\n"
                    + "	margin:0cm;\n"
                    + "	margin-bottom:.0001pt;\n"
                    + "	font-size:8.0pt;\n"
                    + "	font-family:\"Tahoma\",\"sans-serif\";\n"
                    + "	mso-fareast-language:EN-US;}\n"
                    + "span.EstiloCorreo17\n"
                    + "	{mso-style-type:personal-compose;\n"
                    + "	font-family:\"Calibri\",\"sans-serif\";\n"
                    + "	color:windowtext;}\n"
                    + "span.TextodegloboCar\n"
                    + "	{mso-style-name:\"Texto de globo Car\";\n"
                    + "	mso-style-priority:99;\n"
                    + "	mso-style-link:\"Texto de globo\";\n"
                    + "	font-family:\"Tahoma\",\"sans-serif\";}\n"
                    + ".MsoChpDefault\n"
                    + "	{mso-style-type:export-only;\n"
                    + "	font-family:\"Calibri\",\"sans-serif\";\n"
                    + "	mso-fareast-language:EN-US;}\n"
                    + "@page WordSection1\n"
                    + "	{size:612.0pt 792.0pt;\n"
                    + "	margin:70.85pt 3.0cm 70.85pt 3.0cm;}\n"
                    + "div.WordSection1\n"
                    + "	{page:WordSection1;}\n"
                    + "--></style><!--[if gte mso 9]><xml>\n"
                    + "<o:shapedefaults v:ext=\"edit\" spidmax=\"1026\" />\n"
                    + "</xml><![endif]--><!--[if gte mso 9]><xml>\n"
                    + "<o:shapelayout v:ext=\"edit\">\n"
                    + "<o:idmap v:ext=\"edit\" data=\"1\" />\n"
                    + "</o:shapelayout></xml><![endif]--></head><body lang=ES-MX link=blue vlink=purple><div class=WordSection1><p class=MsoNormal><br><br>Solicitud de Compra Urgente con Folio: "+orden+".<br><br>"
                    + ""
                    + "Dear Supplier\n" +
" \n" +
"Please find attached the PO, since this is a CRITICAL request we look forward to shippping details.\n" +
" \n" +
"Please do not hesitate on contact us in case of any inquiry\n" +
" \n" +
"Best regards.<br><br>\n" +
" \n" +
" \n" +
"Estimado proveedor,\n" +
" \n" +
"Adjunto orden de compra urgente, favor de confirmar la recepci&oacute;n de la misma.\n" +
" \n" +
"Cualquier duda o comentario con gusto lo revisamos.\n" +
" \n" +
"Saludos cordiales.<br><o:p></o:p></p><br><span style='mso-fareast-language:ES-MX'><img width=300 height=200 id=\"_x0030__x0020_Imagen\" src=\""+imagen+"logo_"+compania+".jpg\"></span></div></body></html>";

            // sets SMTP server properties
            Properties properties = new Properties();
            properties.put("mail.smtp.host", host);
            properties.put("mail.smtp.port", port);
//            properties.put("mail.pop3.host", hostPop3);
//            properties.put("mail.pop3.port", portPop3);
            properties.put("mail.smtp.auth", "true");
            //properties.put("mail.smtp.ssl.trust", host);
            properties.put("mail.smtp.starttls.enable", "true");
            properties.put("mail.smtp.ssl.protocols", "TLSv1.2");

            // creates a new session with an authenticator
            Authenticator auth = new Authenticator() {
                public PasswordAuthentication getPasswordAuthentication() {
                    return new PasswordAuthentication(usuarioMail, password);
                }
            };

            Session session = Session.getInstance(properties, auth);
            session.setDebug(true);
            
            
             BodyPart texto = new MimeBodyPart();
             ///texto.set
            texto.setText(msag);
            texto.setHeader("Content-Type", "text/html");

            // Se compone el adjunto con la imagen
            BodyPart adjunto = new MimeBodyPart();
            adjunto.setDataHandler(
               new DataHandler(new FileDataSource("D:/Administrategia/Empresarial/www/JasperReports/Reportes/JRepOrdenCompra/JRepOrdenCompra_"+reporte+".pdf")));
            //new DataHandler(new FileDataSource("D:/JRepOrdenCompra_2505588.pdf")));
            
            adjunto.setFileName("Orden de Compra "+ orden + ".pdf");

            // Una MultiParte para agrupar texto e imagen.
            MimeMultipart multiParte = new MimeMultipart();
            multiParte.addBodyPart(texto);
            multiParte.addBodyPart(adjunto);
            
            
            // creates a new e-mail message
            Message msg = new MimeMessage(session);

            msg.setFrom(new InternetAddress(correoOrigen));
            
            if (correoUsuario.indexOf(',') > 0){
                msg.setRecipients(Message.RecipientType.TO, InternetAddress.parse(correoUsuario));   
            }else{
                msg.setRecipient(Message.RecipientType.TO, new InternetAddress(correoUsuario));
            }
            if(!correoCopia.equalsIgnoreCase("")){
            if (correoCopia.indexOf(',') > 0){
                msg.setRecipients(Message.RecipientType.CC, InternetAddress.parse(correoCopia));   
            }else{
                msg.setRecipient(Message.RecipientType.CC, new InternetAddress(correoCopia));
            }
            }
            
            //InternetAddress[] toAddresses = {new InternetAddress(correoUsuario)};
           // msg.setRecipients(Message.RecipientType.TO, toAddresses);
            msg.setSubject("Solicitud de Compra Urgente "+compania);
            msg.setSentDate(new Date());
            // set plain text message

            msg.setContent(multiParte);
           // msg.setHeader("Content-Type", "text/html");

            // sends the e-mail
            Transport.send(msg);





        } catch (Exception e) {
            e.printStackTrace();
        }

    }
    
        public void sendMailPOAut(String cadenaVerificacion, String correoUsuario,String usuario, String compania,String orden, String reporte,String correoCopia) {
        
        

        try {
            System.out.println("pass:" + password);
            System.out.println("usuario:" + usuario);
           System.out.println("reporte:" + reporte);
          // System.out.println("imagen:"+ imagen+"logo_"+compania+".jpg");


            String msag = "<html xmlns:v=\"urn:schemas-microsoft-com:vml\" xmlns:o=\"urn:schemas-microsoft-com:office:office\" xmlns:w=\"urn:schemas-microsoft-com:office:word\" xmlns:m=\"http://schemas.microsoft.com/office/2004/12/omml\" xmlns=\"http://www.w3.org/TR/REC-html40\"><head><meta http-equiv=Content-Type content=\"text/html; charset=iso-8859-1\"><meta name=Generator content=\"Microsoft Word 14 (filtered medium)\"><!--[if !mso]><style>v\\:* {behavior:url(#default#VML);}\n"
                    + "o\\:* {behavior:url(#default#VML);}\n"
                    + "w\\:* {behavior:url(#default#VML);}\n"
                    + ".shape {behavior:url(#default#VML);}\n"
                    + "</style><![endif]--><style><!--\n"
                    + "/* Font Definitions */\n"
                    + "@font-face\n"
                    + "	{font-family:Calibri;\n"
                    + "	panose-1:2 15 5 2 2 2 4 3 2 4;}\n"
                    + "@font-face\n"
                    + "	{font-family:Tahoma;\n"
                    + "	panose-1:2 11 6 4 3 5 4 4 2 4;}\n"
                    + "/* Style Definitions */\n"
                    + "p.MsoNormal, li.MsoNormal, div.MsoNormal\n"
                    + "	{margin:0cm;\n"
                    + "	margin-bottom:.0001pt;\n"
                    + "	font-size:11.0pt;\n"
                    + "	font-family:\"Calibri\",\"sans-serif\";\n"
                    + "	mso-fareast-language:EN-US;}\n"
                    + "a:link, span.MsoHyperlink\n"
                    + "	{mso-style-priority:99;\n"
                    + "	color:blue;\n"
                    + "	text-decoration:underline;}\n"
                    + "a:visited, span.MsoHyperlinkFollowed\n"
                    + "	{mso-style-priority:99;\n"
                    + "	color:purple;\n"
                    + "	text-decoration:underline;}\n"
                    + "p.MsoAcetate, li.MsoAcetate, div.MsoAcetate\n"
                    + "	{mso-style-priority:99;\n"
                    + "	mso-style-link:\"Texto de globo Car\";\n"
                    + "	margin:0cm;\n"
                    + "	margin-bottom:.0001pt;\n"
                    + "	font-size:8.0pt;\n"
                    + "	font-family:\"Tahoma\",\"sans-serif\";\n"
                    + "	mso-fareast-language:EN-US;}\n"
                    + "span.EstiloCorreo17\n"
                    + "	{mso-style-type:personal-compose;\n"
                    + "	font-family:\"Calibri\",\"sans-serif\";\n"
                    + "	color:windowtext;}\n"
                    + "span.TextodegloboCar\n"
                    + "	{mso-style-name:\"Texto de globo Car\";\n"
                    + "	mso-style-priority:99;\n"
                    + "	mso-style-link:\"Texto de globo\";\n"
                    + "	font-family:\"Tahoma\",\"sans-serif\";}\n"
                    + ".MsoChpDefault\n"
                    + "	{mso-style-type:export-only;\n"
                    + "	font-family:\"Calibri\",\"sans-serif\";\n"
                    + "	mso-fareast-language:EN-US;}\n"
                    + "@page WordSection1\n"
                    + "	{size:612.0pt 792.0pt;\n"
                    + "	margin:70.85pt 3.0cm 70.85pt 3.0cm;}\n"
                    + "div.WordSection1\n"
                    + "	{page:WordSection1;}\n"
                    + "--></style><!--[if gte mso 9]><xml>\n"
                    + "<o:shapedefaults v:ext=\"edit\" spidmax=\"1026\" />\n"
                    + "</xml><![endif]--><!--[if gte mso 9]><xml>\n"
                    + "<o:shapelayout v:ext=\"edit\">\n"
                    + "<o:idmap v:ext=\"edit\" data=\"1\" />\n"
                    + "</o:shapelayout></xml><![endif]--></head><body lang=ES-MX link=blue vlink=purple><div class=WordSection1><p class=MsoNormal><br><br>Solicitud de Compra Urgente con Folio: "+orden+".<br><br>"
                    + ""
                    + "Dear Supplier\n" +
" \n" +
"Please find attached the PO.\n" +
" \n" +
"Please do not hesitate on contact us in case of any inquiry\n" +
" \n" +
"Best regards.<br><br>\n" +
" \n" +
" \n" +
"Estimado proveedor,\n" +
" \n" +
"Adjunto orden de compra, favor de confirmar la recepci&oacute;n de la misma.\n" +
" \n" +
"Cualquier duda o comentario con gusto lo revisamos.\n" +
" \n" +
"Saludos cordiales.<br><o:p></o:p></p><br><span style='mso-fareast-language:ES-MX'><img width=300 height=200 id=\"_x0030__x0020_Imagen\" src=\""+imagen+"logo_"+compania+".jpg\"></span></div></body></html>";

            // sets SMTP server properties
            Properties properties = new Properties();
            properties.put("mail.smtp.host", host);
            properties.put("mail.smtp.port", port);
//            properties.put("mail.pop3.host", hostPop3);
//            properties.put("mail.pop3.port", portPop3);
            properties.put("mail.smtp.auth", "true");
            //properties.put("mail.smtp.ssl.trust", host);
            properties.put("mail.smtp.starttls.enable", "true");
            properties.put("mail.smtp.ssl.protocols", "TLSv1.2");

            // creates a new session with an authenticator
            Authenticator auth = new Authenticator() {
                public PasswordAuthentication getPasswordAuthentication() {
                    return new PasswordAuthentication(usuarioMail, password);
                }
            };

            Session session = Session.getInstance(properties, auth);
            session.setDebug(true);
            
            
             BodyPart texto = new MimeBodyPart();
             ///texto.set
            texto.setText(msag);
            texto.setHeader("Content-Type", "text/html");

            // Se compone el adjunto con la imagen
            BodyPart adjunto = new MimeBodyPart();
            adjunto.setDataHandler(
               new DataHandler(new FileDataSource("D:/Administrategia/Empresarial/www/JasperReports/Reportes/JRepOrdenCompra/JRepOrdenCompra_"+reporte+".pdf")));
            //new DataHandler(new FileDataSource("D:/JRepOrdenCompra_19852308.pdf")));
            
            adjunto.setFileName("Orden de Compra "+ orden + ".pdf");

            // Una MultiParte para agrupar texto e imagen.
            MimeMultipart multiParte = new MimeMultipart();
            multiParte.addBodyPart(texto);
            multiParte.addBodyPart(adjunto);
            
            
            // creates a new e-mail message
            Message msg = new MimeMessage(session);

            msg.setFrom(new InternetAddress(correoOrigen));
            
            if (correoUsuario.indexOf(',') > 0){
                msg.setRecipients(Message.RecipientType.TO, InternetAddress.parse(correoUsuario));   
            }else{
                msg.setRecipient(Message.RecipientType.TO, new InternetAddress(correoUsuario));
            }
            if(!correoCopia.equalsIgnoreCase("")){
            if (correoCopia.indexOf(',') > 0){
                msg.setRecipients(Message.RecipientType.CC, InternetAddress.parse(correoCopia));   
            }else{
                msg.setRecipient(Message.RecipientType.CC, new InternetAddress(correoCopia));
            }
            }
            
            //InternetAddress[] toAddresses = {new InternetAddress(correoUsuario)};
           // msg.setRecipients(Message.RecipientType.TO, toAddresses);
            msg.setSubject("Solicitud de Compra "+compania);
            msg.setSentDate(new Date());
            // set plain text message

            msg.setContent(multiParte);
           // msg.setHeader("Content-Type", "text/html");

            // sends the e-mail
            Transport.send(msg);





        } catch (Exception e) {
            e.printStackTrace();
        }

    }
  
    public void sendMailAut(String cadenaVerificacion, String correoUsuario,String usuario, String compania,String orden, String reporte,String correoCopia, List correoDet) {
        
        

        try {
            System.out.println("pass:" + password);
            System.out.println("usuario:" + usuarioMail);
           System.out.println("reporte:" + reporte);
          // System.out.println("imagen:"+ imagen+"logo_"+compania+".jpg");
            String det = "";
            String arrayDet = "";
            
            for(int i = 0;correoDet.size() > i;i++){
                
                              Map resultCount = (HashMap) correoDet.get(i);
                              
                              
				 
                          det = "</tr>"

                                 +"<tr>\n"
                                 +"<td width=129 nowrap valign=top style='width:96.8pt;border-top:none;\n  border-left:none;border-bottom:solid #95B6C5 1.0pt;mso-border-bottom-themecolor:\n  accent5;mso-border-bottom-themetint:153;border-right:solid #95B6C5 1.0pt;\n  mso-border-right-themecolor:accent5;mso-border-right-themetint:153;\n  mso-border-top-alt:solid #95B6C5 .5pt;mso-border-top-themecolor:accent5;\n  mso-border-top-themetint:153;mso-border-left-alt:solid #95B6C5 .5pt;\n  mso-border-left-themecolor:accent5;mso-border-left-themetint:153;mso-border-alt:\n  solid #95B6C5 .5pt;mso-border-themecolor:accent5;mso-border-themetint:153;\n  padding:0cm 5.4pt 0cm 5.4pt;height:12.75pt'>\n  <p class=MsoNormal><span style='font-size:10.0pt;font-family:\"Arial\",sans-serif;\n  mso-fareast-font-family:\"Times New Roman\";mso-fareast-language:ES-MX'>"
                                 +resultCount.get("FOLIO").toString()
                                 +"<o:p></o:p></span></p>\n  </td>"

                                 +"<td width=129 nowrap valign=top style='width:96.8pt;border-top:none;\n  border-left:none;border-bottom:solid #95B6C5 1.0pt;mso-border-bottom-themecolor:\n  accent5;mso-border-bottom-themetint:153;border-right:solid #95B6C5 1.0pt;\n  mso-border-right-themecolor:accent5;mso-border-right-themetint:153;\n  mso-border-top-alt:solid #95B6C5 .5pt;mso-border-top-themecolor:accent5;\n  mso-border-top-themetint:153;mso-border-left-alt:solid #95B6C5 .5pt;\n  mso-border-left-themecolor:accent5;mso-border-left-themetint:153;mso-border-alt:\n  solid #95B6C5 .5pt;mso-border-themecolor:accent5;mso-border-themetint:153;\n  padding:0cm 5.4pt 0cm 5.4pt;height:12.75pt'>\n  <p class=MsoNormal><span style='font-size:10.0pt;font-family:\"Arial\",sans-serif;\n  mso-fareast-font-family:\"Times New Roman\";mso-fareast-language:ES-MX'>"
                                 +resultCount.get("PROVEEDOR").toString()
                                 +"<o:p></o:p></span></p>\n  </td>"

                                 +"<td width=129 nowrap valign=top style='width:96.8pt;border-top:none;\n  border-left:none;border-bottom:solid #95B6C5 1.0pt;mso-border-bottom-themecolor:\n  accent5;mso-border-bottom-themetint:153;border-right:solid #95B6C5 1.0pt;\n  mso-border-right-themecolor:accent5;mso-border-right-themetint:153;\n  mso-border-top-alt:solid #95B6C5 .5pt;mso-border-top-themecolor:accent5;\n  mso-border-top-themetint:153;mso-border-left-alt:solid #95B6C5 .5pt;\n  mso-border-left-themecolor:accent5;mso-border-left-themetint:153;mso-border-alt:\n  solid #95B6C5 .5pt;mso-border-themecolor:accent5;mso-border-themetint:153;\n  padding:0cm 5.4pt 0cm 5.4pt;height:12.75pt'>\n  <p class=MsoNormal><span style='font-size:10.0pt;font-family:\"Arial\",sans-serif;\n  mso-fareast-font-family:\"Times New Roman\";mso-fareast-language:ES-MX'>"
                                 +resultCount.get("DESCRIPCION").toString()
                                 +"<o:p></o:p></span></p>\n  </td>"



                                 +"<td width=129 nowrap valign=top style='width:96.8pt;border-top:none;\n  border-left:none;border-bottom:solid #95B6C5 1.0pt;mso-border-bottom-themecolor:\n  accent5;mso-border-bottom-themetint:153;border-right:solid #95B6C5 1.0pt;\n  mso-border-right-themecolor:accent5;mso-border-right-themetint:153;\n  mso-border-top-alt:solid #95B6C5 .5pt;mso-border-top-themecolor:accent5;\n  mso-border-top-themetint:153;mso-border-left-alt:solid #95B6C5 .5pt;\n  mso-border-left-themecolor:accent5;mso-border-left-themetint:153;mso-border-alt:\n  solid #95B6C5 .5pt;mso-border-themecolor:accent5;mso-border-themetint:153;\n  padding:0cm 5.4pt 0cm 5.4pt;height:12.75pt'>\n  <p class=MsoNormal><span style='font-size:10.0pt;font-family:\"Arial\",sans-serif;\n  mso-fareast-font-family:\"Times New Roman\";mso-fareast-language:ES-MX'>"
                                 +resultCount.get("CANTIDAD_PEDIDA").toString()
                                 +"<o:p></o:p></span></p>\n  </td>"

                                 +"<td width=129 nowrap valign=top style='width:96.8pt;border-top:none;\n  border-left:none;border-bottom:solid #95B6C5 1.0pt;mso-border-bottom-themecolor:\n  accent5;mso-border-bottom-themetint:153;border-right:solid #95B6C5 1.0pt;\n  mso-border-right-themecolor:accent5;mso-border-right-themetint:153;\n  mso-border-top-alt:solid #95B6C5 .5pt;mso-border-top-themecolor:accent5;\n  mso-border-top-themetint:153;mso-border-left-alt:solid #95B6C5 .5pt;\n  mso-border-left-themecolor:accent5;mso-border-left-themetint:153;mso-border-alt:\n  solid #95B6C5 .5pt;mso-border-themecolor:accent5;mso-border-themetint:153;\n  padding:0cm 5.4pt 0cm 5.4pt;height:12.75pt'>\n  <p class=MsoNormal><span style='font-size:10.0pt;font-family:\"Arial\",sans-serif;\n  mso-fareast-font-family:\"Times New Roman\";mso-fareast-language:ES-MX'>"
                                 +resultCount.get("IMPORTE_COTIZACION_SUB").toString()
                                 +"<o:p></o:p></span></p>\n  </td>"
                                  
                                  +"<td width=129 nowrap valign=top style='width:96.8pt;border-top:none;\n  border-left:none;border-bottom:solid #95B6C5 1.0pt;mso-border-bottom-themecolor:\n  accent5;mso-border-bottom-themetint:153;border-right:solid #95B6C5 1.0pt;\n  mso-border-right-themecolor:accent5;mso-border-right-themetint:153;\n  mso-border-top-alt:solid #95B6C5 .5pt;mso-border-top-themecolor:accent5;\n  mso-border-top-themetint:153;mso-border-left-alt:solid #95B6C5 .5pt;\n  mso-border-left-themecolor:accent5;mso-border-left-themetint:153;mso-border-alt:\n  solid #95B6C5 .5pt;mso-border-themecolor:accent5;mso-border-themetint:153;\n  padding:0cm 5.4pt 0cm 5.4pt;height:12.75pt'>\n  <p class=MsoNormal><span style='font-size:10.0pt;font-family:\"Arial\",sans-serif;\n  mso-fareast-font-family:\"Times New Roman\";mso-fareast-language:ES-MX'>"
                                 +resultCount.get("FECHA_REQUERIDA").toString()
                                 +"<o:p></o:p></span></p>\n  </td>"

                                 +"</tr>\n"


                            +"</table>\n";
                          
                          arrayDet = arrayDet + det;
                    }

            String msag = "<html xmlns:v=\"urn:schemas-microsoft-com:vml\" xmlns:o=\"urn:schemas-microsoft-com:office:office\" xmlns:w=\"urn:schemas-microsoft-com:office:word\" xmlns:m=\"http://schemas.microsoft.com/office/2004/12/omml\" xmlns=\"http://www.w3.org/TR/REC-html40\"><head><meta http-equiv=Content-Type content=\"text/html; charset=iso-8859-1\"><meta name=Generator content=\"Microsoft Word 14 (filtered medium)\"><!--[if !mso]><style>v\\:* {behavior:url(#default#VML);}\n"
                    + "o\\:* {behavior:url(#default#VML);}\n"
                    + "w\\:* {behavior:url(#default#VML);}\n"
                    + ".shape {behavior:url(#default#VML);}\n"
                    + "</style><![endif]--><style><!--\n"
                    + "/* Font Definitions */\n"
                    + "@font-face\n"
                    + "	{font-family:Calibri;\n"
                    + "	panose-1:2 15 5 2 2 2 4 3 2 4;}\n"
                    + "@font-face\n"
                    + "	{font-family:Tahoma;\n"
                    + "	panose-1:2 11 6 4 3 5 4 4 2 4;}\n"
                    + "/* Style Definitions */\n"
                    + "p.MsoNormal, li.MsoNormal, div.MsoNormal\n"
                    + "	{margin:0cm;\n"
                    + "	margin-bottom:.0001pt;\n"
                    + "	font-size:11.0pt;\n"
                    + "	font-family:\"Calibri\",\"sans-serif\";\n"
                    + "	mso-fareast-language:EN-US;}\n"
                    + "a:link, span.MsoHyperlink\n"
                    + "	{mso-style-priority:99;\n"
                    + "	color:blue;\n"
                    + "	text-decoration:underline;}\n"
                    + "a:visited, span.MsoHyperlinkFollowed\n"
                    + "	{mso-style-priority:99;\n"
                    + "	color:purple;\n"
                    + "	text-decoration:underline;}\n"
                    + "p.MsoAcetate, li.MsoAcetate, div.MsoAcetate\n"
                    + "	{mso-style-priority:99;\n"
                    + "	mso-style-link:\"Texto de globo Car\";\n"
                    + "	margin:0cm;\n"
                    + "	margin-bottom:.0001pt;\n"
                    + "	font-size:8.0pt;\n"
                    + "	font-family:\"Tahoma\",\"sans-serif\";\n"
                    + "	mso-fareast-language:EN-US;}\n"
                    + "span.EstiloCorreo17\n"
                    + "	{mso-style-type:personal-compose;\n"
                    + "	font-family:\"Calibri\",\"sans-serif\";\n"
                    + "	color:windowtext;}\n"
                    + "span.TextodegloboCar\n"
                    + "	{mso-style-name:\"Texto de globo Car\";\n"
                    + "	mso-style-priority:99;\n"
                    + "	mso-style-link:\"Texto de globo\";\n"
                    + "	font-family:\"Tahoma\",\"sans-serif\";}\n"
                    + ".MsoChpDefault\n"
                    + "	{mso-style-type:export-only;\n"
                    + "	font-family:\"Calibri\",\"sans-serif\";\n"
                    + "	mso-fareast-language:EN-US;}\n"
                    + "@page WordSection1\n"
                    + "	{size:612.0pt 792.0pt;\n"
                    + "	margin:70.85pt 3.0cm 70.85pt 3.0cm;}\n"
                    + "div.WordSection1\n"
                    + "	{page:WordSection1;}\n"
                    + "--></style><!--[if gte mso 9]><xml>\n"
                    + "<o:shapedefaults v:ext=\"edit\" spidmax=\"1026\" />\n"
                    + "</xml><![endif]--><!--[if gte mso 9]><xml>\n"
                    + "<o:shapelayout v:ext=\"edit\">\n"
                    + "<o:idmap v:ext=\"edit\" data=\"1\" />\n"
                    + "</o:shapelayout></xml><![endif]--></head><body lang=ES-MX link=blue vlink=purple><div class=WordSection1><p class=MsoNormal><br><br>Solicitud de Compra Autorizada con Folio: "+orden+".<br>"
                     +" <br> \n"
                    +"<table class=MsoTable15Grid4Accent5 border=1 cellspacing=0 cellpadding=0\n width=1085 style='width:813.9pt;border-collapse:collapse;border:none;\n mso-border-alt:solid #95B6C5 .5pt;mso-border-themecolor:accent5;mso-border-themetint:\n 153;mso-yfti-tbllook:1184;mso-padding-alt:0cm 5.4pt 0cm 5.4pt'>"
                    +"<tr style='mso-yfti-irow:-1;mso-yfti-firstrow:yes;mso-yfti-lastfirstrow:yes;\n  height:12.75pt'>"
                    +"<td width=45 nowrap valign=top style='width:34.1pt;border:solid #1E8449 1.0pt;\n  mso-border-themecolor:#1E8449;border-right:none;mso-border-top-alt:solid #1E8449 .5pt;\n  mso-border-top-themecolor:#1E8449;mso-border-left-alt:solid #1E8449 .5pt;\n  mso-border-left-themecolor:#1E8449;mso-border-bottom-alt:solid #1E8449 .5pt;\n  mso-border-bottom-themecolor:#1E8449;background:#1E8449;mso-background-themecolor:\n  #1E8449;padding:0cm 5.4pt 0cm 5.4pt;height:12.75pt'>\n  <p class=MsoNormal style='mso-yfti-cnfc:5'><b><span style='font-size:10.0pt;\n  font-family:\"Arial\",sans-serif;mso-fareast-font-family:\"Times New Roman\";\n  color:white;mso-themecolor:#1E8449;mso-fareast-language:ES-MX'>FOLIO<o:p></o:p></span></b></p>\n  </td>"
                    +"<td width=45 nowrap valign=top style='width:34.1pt;border:solid #1E8449 1.0pt;\n  mso-border-themecolor:#1E8449;border-right:none;mso-border-top-alt:solid #1E8449 .5pt;\n  mso-border-top-themecolor:#1E8449;mso-border-left-alt:solid #1E8449 .5pt;\n  mso-border-left-themecolor:#1E8449;mso-border-bottom-alt:solid #1E8449 .5pt;\n  mso-border-bottom-themecolor:#1E8449;background:#1E8449;mso-background-themecolor:\n  #1E8449;padding:0cm 5.4pt 0cm 5.4pt;height:12.75pt'>\n  <p class=MsoNormal style='mso-yfti-cnfc:5'><b><span style='font-size:10.0pt;\n  font-family:\"Arial\",sans-serif;mso-fareast-font-family:\"Times New Roman\";\n  color:white;mso-themecolor:#1E8449;mso-fareast-language:ES-MX'>PROVEEDOR<o:p></o:p></span></b></p>\n  </td>"
                    +"<td width=45 nowrap valign=top style='width:34.1pt;border:solid #1E8449 1.0pt;\n  mso-border-themecolor:#1E8449;border-right:none;mso-border-top-alt:solid #1E8449 .5pt;\n  mso-border-top-themecolor:#1E8449;mso-border-left-alt:solid #1E8449 .5pt;\n  mso-border-left-themecolor:#1E8449;mso-border-bottom-alt:solid #1E8449 .5pt;\n  mso-border-bottom-themecolor:#1E8449;background:#1E8449;mso-background-themecolor:\n  #1E8449;padding:0cm 5.4pt 0cm 5.4pt;height:12.75pt'>\n  <p class=MsoNormal style='mso-yfti-cnfc:5'><b><span style='font-size:10.0pt;\n  font-family:\"Arial\",sans-serif;mso-fareast-font-family:\"Times New Roman\";\n  color:white;mso-themecolor:#1E8449;mso-fareast-language:ES-MX'>PRODUCTO<o:p></o:p></span></b></p>\n  </td>"
                    +"<td width=45 nowrap valign=top style='width:34.1pt;border:solid #1E8449 1.0pt;\n  mso-border-themecolor:#1E8449;border-right:none;mso-border-top-alt:solid #1E8449 .5pt;\n  mso-border-top-themecolor:#1E8449;mso-border-left-alt:solid #1E8449 .5pt;\n  mso-border-left-themecolor:#1E8449;mso-border-bottom-alt:solid #1E8449 .5pt;\n  mso-border-bottom-themecolor:#1E8449;background:#1E8449;mso-background-themecolor:\n  #1E8449;padding:0cm 5.4pt 0cm 5.4pt;height:12.75pt'>\n  <p class=MsoNormal style='mso-yfti-cnfc:5'><b><span style='font-size:10.0pt;\n  font-family:\"Arial\",sans-serif;mso-fareast-font-family:\"Times New Roman\";\n  color:white;mso-themecolor:#1E8449;mso-fareast-language:ES-MX'>CANTIDAD<o:p></o:p></span></b></p>\n  </td>"
                    +"<td width=45 nowrap valign=top style='width:34.1pt;border:solid #1E8449 1.0pt;\n  mso-border-themecolor:#1E8449;border-right:none;mso-border-top-alt:solid #1E8449 .5pt;\n  mso-border-top-themecolor:#1E8449;mso-border-left-alt:solid #1E8449 .5pt;\n  mso-border-left-themecolor:#1E8449;mso-border-bottom-alt:solid #1E8449 .5pt;\n  mso-border-bottom-themecolor:#1E8449;background:#1E8449;mso-background-themecolor:\n  #1E8449;padding:0cm 5.4pt 0cm 5.4pt;height:12.75pt'>\n  <p class=MsoNormal style='mso-yfti-cnfc:5'><b><span style='font-size:10.0pt;\n  font-family:\"Arial\",sans-serif;mso-fareast-font-family:\"Times New Roman\";\n  color:white;mso-themecolor:#1E8449;mso-fareast-language:ES-MX'>IMPORTE<o:p></o:p></span></b></p>\n  </td>"
                    +"<td width=45 nowrap valign=top style='width:34.1pt;border:solid #1E8449 1.0pt;\n  mso-border-themecolor:#1E8449;border-right:none;mso-border-top-alt:solid #1E8449 .5pt;\n  mso-border-top-themecolor:#1E8449;mso-border-left-alt:solid #1E8449 .5pt;\n  mso-border-left-themecolor:#1E8449;mso-border-bottom-alt:solid #1E8449 .5pt;\n  mso-border-bottom-themecolor:#1E8449;background:#1E8449;mso-background-themecolor:\n  #1E8449;padding:0cm 5.4pt 0cm 5.4pt;height:12.75pt'>\n  <p class=MsoNormal style='mso-yfti-cnfc:5'><b><span style='font-size:10.0pt;\n  font-family:\"Arial\",sans-serif;mso-fareast-font-family:\"Times New Roman\";\n  color:white;mso-themecolor:#1E8449;mso-fareast-language:ES-MX'>FECHA REQUERIDA<o:p></o:p></span></b></p>\n  </td>"
                    +arrayDet
                    +"<br>\n"
                    +"<br>\n"
                    + ""
                    + "Dear Supplier\n" +
            " \n" +
            "Please find attached the PO, we look forward to shippping details.\n" +
            " \n" +
            "Please do not hesitate on contact us in case of any inquiry\n" +
            " \n" +
            "Best regards.<br><br>\n" +
            " \n" +
            " \n" +
                   "Prezado fornecedor,\n" +
            " \n" +
            "estou anexando um pedido de compra, favor confirmar o recebimento do mesmo.\n" +
            " \n" +
            "Teremos o prazer de analisar quaisquer perguntas ou comentários\n" +
            " \n" +
            "Atenciosamente.<br><br>\n" +
            " \n" +
            " \n" +
            "Estimado proveedor,\n" +
            " \n" +
            "Adjunto orden de compra, favor de confirmar la recepci&oacute;n de la misma.\n" +
            " \n" +
            "Cualquier duda o comentario con gusto lo revisamos.\n" +
            " \n" +
            "Saludos cordiales.<br><o:p></o:p></p><br><span style='mso-fareast-language:ES-MX'><img width=300 height=200 id=\"_x0030__x0020_Imagen\" src=\""+imagen+"logo_"+compania+".jpg\"></span></div></body></html>";
            
            // sets SMTP server properties
            Properties properties = new Properties();
            properties.put("mail.smtp.host", host);
            properties.put("mail.smtp.port", port);
//            properties.put("mail.pop3.host", hostPop3);
//            properties.put("mail.pop3.port", portPop3);
            properties.put("mail.smtp.auth", "true");
            //properties.put("mail.smtp.ssl.trust", host);
            properties.put("mail.smtp.starttls.enable", "true");
            properties.put("mail.smtp.ssl.protocols", "TLSv1.2");

            // creates a new session with an authenticator
            Authenticator auth = new Authenticator() {
                public PasswordAuthentication getPasswordAuthentication() {
                    return new PasswordAuthentication(usuarioMail, password);
                }
            };

            Session session = Session.getInstance(properties, auth);
            session.setDebug(true);
            
            
             BodyPart texto = new MimeBodyPart();
             ///texto.set
            texto.setText(msag);
            texto.setHeader("Content-Type", "text/html");
            String dirRep = "D:/Administrategia/Empresarial/www/JasperReports/Reportes/JRepOrdenCompraPOR/JRepOrdenCompraPOR_"+reporte+".pdf";
            // Se compone el adjunto con la imagen
            System.out.println("dirRep: "+dirRep);
            BodyPart adjunto = new MimeBodyPart();
            adjunto.setDataHandler(
               new DataHandler(new FileDataSource(dirRep)));
            //new DataHandler(new FileDataSource("D:/JRepOrdenCompraPOR_19335927.pdf")));
            
            adjunto.setFileName("Orden de Compra "+ orden + ".pdf");

            // Una MultiParte para agrupar texto e imagen.
            MimeMultipart multiParte = new MimeMultipart();
            multiParte.addBodyPart(texto);
            multiParte.addBodyPart(adjunto);
            
            
            // creates a new e-mail message
            Message msg = new MimeMessage(session);

            msg.setFrom(new InternetAddress(correoOrigen));
            
            if (correoUsuario.indexOf(',') > 0){
                msg.setRecipients(Message.RecipientType.TO, InternetAddress.parse(correoUsuario));   
            }else{
                msg.setRecipient(Message.RecipientType.TO, new InternetAddress(correoUsuario));
            }
            if(!correoCopia.equalsIgnoreCase("")){
            if (correoCopia.indexOf(',') > 0){
                msg.setRecipients(Message.RecipientType.CC, InternetAddress.parse(correoCopia));   
            }else{
                msg.setRecipient(Message.RecipientType.CC, new InternetAddress(correoCopia));
            }
            }
            
            //InternetAddress[] toAddresses = {new InternetAddress(correoUsuario)};
           // msg.setRecipients(Message.RecipientType.TO, toAddresses);
            msg.setSubject("Compra Autorizada "+compania);
            msg.setSentDate(new Date());
            // set plain text message

            msg.setContent(multiParte);
           // msg.setHeader("Content-Type", "text/html");

            // sends the e-mail
            Transport.send(msg);





        } catch (Exception e) {
            e.printStackTrace();
        }

    }
    public void setHost(String host) {
        this.host = host;
    }

    public void setStarttls(String starttls) {
        this.starttls = starttls;
    }

    public void setPort(String port) {
        this.port = port;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public void setAuth(String auth) {
        this.auth = auth;
    }

    public void setCorreoOrigen(String correoOrigen) {
        this.correoOrigen = correoOrigen;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setImagen(String imagen) {
        this.imagen = imagen;
    }

    public void setUsuarioMail(String usuarioMail) {
        this.usuarioMail = usuarioMail;
    }

    
    
    

}