/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.feraz.ordencompra.ws;

import java.io.FileNotFoundException;
import java.io.IOException;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;
import javax.xml.bind.JAXBException;

/**
 *
 * @author vavi
 */
@WebService(serviceName = "WSProductos")
public class WSProductos {
    
   @WebMethod(operationName = "cargaProductos")
    public String orden(
            @WebParam(name = "usuario") String usuario
    
    ) throws IOException, FileNotFoundException, JAXBException {
        
        
         
        
  
        System.out.println("-----------WS ProveedoreEEEEEEE----------------");
        System.out.println(usuario);
        
        
        System.out.println("-----------WS Proveedor----------------");
        
      
         
       
    
          return msgResp("0","Error password incorrecto");
        
    
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
