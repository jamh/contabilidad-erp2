/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.feraz.contabilidad.ws;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import javax.jws.WebService;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.xml.bind.JAXBException;


/**
 *
 * @author vavi
 */
@WebService(serviceName = "WScfdi")
public class WScfdi {
    
      @WebMethod(operationName = "cargaCFDI")
    public String hello(
            @WebParam(name = "usuario") String usuario,
            @WebParam(name = "password") String pass,
            @WebParam(name = "compania") String compania,
            @WebParam(name = "uuid") String uuid,
            @WebParam(name = "urlXml") String urlXml,
            @WebParam(name = "urlPDF") String urlPDF,
            @WebParam(name = "rfcReceptor") String rfcRecptor,
            @WebParam(name = "rfcEmisor") String rfcEmisor,
            @WebParam(name = "operacion") String operacion
    
    ) throws IOException, FileNotFoundException, JAXBException {
        
        
         
        
        System.out.println(operacion);
        System.out.println("-----------WS CFDI----------------");
        System.out.println(usuario);
        System.out.println(pass);
        System.out.println(compania);
        System.out.println(uuid);
        System.out.println(urlXml);
        System.out.println(urlPDF);
        System.out.println(rfcRecptor);
        System.out.println(rfcEmisor);
        System.out.println(operacion);
        
        
        System.out.println("-----------WS CFDI----------------");
        
        
         System.out.println(uuid.replace(" ",""));
         uuid = uuid.replace(" ","");
         uuid = uuid.replaceAll("\n", ""); 
         
         System.out.println(uuid);
        
        
         String urlC = "D:\\cfdiIntercos\\"+uuid+".xml";
         
       
        if(!usuario.equalsIgnoreCase("cfdiInt")){
            
            return msgResp("0","Error usuario incorrecto");
        
        }
        
        if(!pass.equalsIgnoreCase("truper")){
            
            return msgResp("0","Error password incorrecto");
        
        }
          return null;
        
    
    }
    

    
    @WebMethod(exclude = true)
    public String msgResp(String result, String msg) {
       
        return "<?xml version=\"1.0\" encoding=\"UTF-8\"?>"
                + "<response>\n"
                + "  <result>" + result + "</result>\n"
                + "  <mensaje>" + msg + "</mensaje>\n"
                + "</response>\n";
    }

    
}
