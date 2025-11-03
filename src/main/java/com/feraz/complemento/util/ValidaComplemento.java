/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.feraz.complemento.util;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.jamh.data.process.ProcessDao;

/**
 *
 * @author vavi
 */
public class ValidaComplemento {
    
    private ProcessDao processDao;
    
    public ResponseCompl validaImportes(String compania,int numero){
        
        ResponseCompl resp = new ResponseCompl();
    
        
        resp.setMsg("success");
        resp.setSuccess(true);
        
        return resp;
    }
    
    public ResponseCompl buscaFactura(String compania,int numero){
        
        ResponseCompl resp = new ResponseCompl();
        
        
          Map fact = new HashMap();
               fact.put("compania", compania);
               fact.put("numero", numero);
               
                List listFact = processDao.getMapResult("BuscarFacturaCompl", fact);
                   
                
                if (listFact != null){
                   
                  for(int i = 0;listFact.size() < i;i++){
                    Map mapFact = (HashMap) listFact.get(0);

                         String uuidFact =  mapFact.get("UUID_FACT").toString();
                         String idDoc =  mapFact.get("ID_DOCUMENTO").toString();
                         
                         if(uuidFact.equalsIgnoreCase("")){
                         
                            resp.setMsg("No se encontro la factura con uuid: "+idDoc);
                            resp.setSuccess(false);
                             
                         }else{
                             
                            resp.setMsg("Se encontraron todas las facturas");
                            resp.setSuccess(true);
                         
                         }
                         
                    }
               }else{
                    
                      resp.setMsg("No se encontro el complemento de pago");
                      resp.setSuccess(false);
                
                }
        
        
        
        return resp;
    }
    
    public ResponseCompl verificaPago(String compania,int numero){
        
        ResponseCompl resp = new ResponseCompl();
    
        
        resp.setMsg("success");
        resp.setSuccess(true);
        
        return resp;
    }

    public void setProcessDao(ProcessDao processDao) {
        this.processDao = processDao;
    }
    
    
}
