/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.feraz.contabilidad.sat.exception;

import org.xml.sax.ErrorHandler;
import org.xml.sax.SAXException;
import org.xml.sax.SAXParseException;

/**
 *
 * @author Administrador
 */
public class CuentasException  implements ErrorHandler {
    private String err;
   public void warning(SAXParseException exception) throws SAXException {
        // Bring things to a crashing halt
//        System.out.println("**Parsing Warning**" +"  Line:    " +  exception.getLineNumber() + "" + "  URI:     " + 
//                              exception.getSystemId() + "" + "  Message: " +  exception.getMessage());        
        throw new SAXException("Warning encountered");
    }
    public void error(SAXParseException exception) throws SAXException {
        // Bring things to a crashing halt
        err=exception.getMessage(); 
//        System.out.println("**ERROR**" +
//                           "  LINEA:    " + 
//                              exception.getLineNumber() + "" +                       
//                           "  Message: " + 
//                              exception.getMessage());        
        throw new SAXException("Error1:"+exception.getMessage());
    }
    public void fatalError(SAXParseException exception) throws SAXException {
        // Bring things to a crashing halt
//        System.out.println("**Parsing Fatal Error**" +
//                           "  Line:    " +  exception.getLineNumber() + "" + "  URI:     " + 
//                              exception.getSystemId() + "" + "  Message: " +  exception.getMessage());        
        throw new SAXException("Fatal Error encontrado:"+err);
    }
}