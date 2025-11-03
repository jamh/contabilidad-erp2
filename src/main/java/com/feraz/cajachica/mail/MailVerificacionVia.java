/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.feraz.cajachica.mail;

import java.util.Date;
import java.util.Properties;
import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Value;

/**
 *
 * @author Ing. JAMH - Feraz
 */
public class MailVerificacionVia {

    @Value("${mail.correo}")
    private String correoOrigen;
    @Value("${mail.passwordCorreo}")
    private String password;
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

    public MailVerificacionVia() {
    }

    public void sendMail(String cadenaVerificacion, String correoUsuario,String usuario, String compania,String nombreVia,String nombreCaja,String importe, String comision, String moneda) {
        
        

        try {
            System.out.println("pass:" + password);
            System.out.println("importe:" + importe);


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
                    + "</o:shapelayout></xml><![endif]--></head><body lang=ES-MX link=blue vlink=purple><div class=WordSection1><p class=MsoNormal><br><br>Hola "+nombreVia+".<br><br>Se registro una entrega de efectivo a su nombre por la cantidad de "+importe +" "+ moneda+" con folio de comision: "+comision+", esto por parte del usuario de caja: "+nombreCaja+" dentro del sistema.<br><br><br>Haga <a href=\""+urlCadena+"?compania="+compania+"&user="+usuario+"&verific="+cadenaVerificacion+"\">clic aqu&iacute;</a> si reconoce este movimiento, de lo contrario favor de contactar con el &aacute;rea de caja chica. <o:p></o:p></p><br><span style='mso-fareast-language:ES-MX'><img width=300 height=200 id=\"_x0030__x0020_Imagen\" src=\""+imagen+"\"></span></div></body></html>";

            // sets SMTP server properties
            Properties properties = new Properties();
            properties.put("mail.smtp.host", host);
            properties.put("mail.smtp.port", port);
//            properties.put("mail.pop3.host", hostPop3);
//            properties.put("mail.pop3.port", portPop3);
            properties.put("mail.smtp.auth", "true");
            properties.put("mail.smtp.ssl.trust", host);
            properties.put("mail.smtp.starttls.enable", "true");

            // creates a new session with an authenticator
            Authenticator auth = new Authenticator() {
                public PasswordAuthentication getPasswordAuthentication() {
                    return new PasswordAuthentication(correoOrigen, password);
                }
            };

            Session session = Session.getInstance(properties, auth);
            session.setDebug(true);
            // creates a new e-mail message
            Message msg = new MimeMessage(session);

            msg.setFrom(new InternetAddress(correoOrigen));
            InternetAddress[] toAddresses = {new InternetAddress(correoUsuario)};
            msg.setRecipients(Message.RecipientType.TO, toAddresses);
            msg.setSubject("Entrega de Efectivo "+compania);
            msg.setSentDate(new Date());
            // set plain text message

            msg.setContent(msag, "text/html");
            msg.setHeader("Content-Type", "text/html");

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


    

}