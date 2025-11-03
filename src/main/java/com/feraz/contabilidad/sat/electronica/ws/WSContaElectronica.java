/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.feraz.contabilidad.sat.electronica.ws;

import com.feraz.contabilidad.sat.electronica.process.GeneraContaElectronica;
import java.text.ParseException;
import javax.jws.WebMethod;
import javax.jws.WebService;
import javax.jws.WebParam;


/**
 *
 * @author vavi
 */

@WebService
public class WSContaElectronica {
    
    private GeneraContaElectronica generaContaElectronica;
    
        @WebMethod(operationName = "generaContaElectronica")
    public String setPolizaNomina(
            @WebParam(name = "compania") String compania,
            @WebParam(name = "numero") int numero,
            @WebParam(name = "numeroPol") String numeroPol,
            @WebParam(name = "fechaPol") String fechaPol,
            @WebParam(name = "tipoPol") String tipoPol
         
            ) throws ParseException {
        
        
        
        System.out.println(compania);
        System.out.println(numero);
        System.out.println(numeroPol);
        System.out.println(fechaPol);
        System.out.println(tipoPol);

 
        
       try{ 
           
           generaContaElectronica.limpiaContaElectronica(compania, numero, numeroPol, fechaPol, tipoPol);
           
          boolean resultContaElect = generaContaElectronica.generaRelacion(compania, numero, numeroPol, fechaPol, tipoPol);
          
          if(resultContaElect == true){
          
                return "1-Contabilidad Electronica Generada";
          
          }else{
          
              return "0-Error al generar la relacion";
          
          }
      
        
       } catch (Exception e) {
           
          // log.error("Error de nomina poliza "+ e);
           return null;
        }
        
        
        //return "Hello " + pid + " !";
      //  return "Proceso Exitosos";
    }
    @WebMethod(exclude = true)
    public void setGeneraContaElectronica(GeneraContaElectronica generaContaElectronica) {
        this.generaContaElectronica = generaContaElectronica;
    }
    
    

    
}
