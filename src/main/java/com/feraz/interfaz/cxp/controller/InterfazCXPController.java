/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.feraz.interfaz.cxp.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.feraz.interfaz.cxp.dto.ServiciosDTO;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import org.jamh.data.process.ProcessDao;
import org.jamh.wf.json.model.ResponseQuery;
import org.jamh.wf.json.model.ResponseQuery2;
import org.jamh.wf.process.Querys;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.context.request.WebRequest;

/**
 *
 * @author vavi
 */
@Controller
@RequestMapping("/InterfazControlCXP")
@SessionAttributes({"compania", "usuario"})
public class InterfazCXPController {
    
    
    
    private ProcessDao processDao;
    
    
      @RequestMapping(value = "/saveServicio", method = RequestMethod.POST)
    public @ResponseBody
    ResponseQuery saveServicio( WebRequest webRequest, Model model) {

        ResponseQuery rq = new ResponseQuery();
        String compania = model.asMap().get("compania").toString();       
        String usuario = model.asMap().get("usuario").toString();         
         System.out.println("compania:"+compania);  
         
    
                  
           String nombre = webRequest.getParameter("servNombreAdd").toString();
           String cuenta = webRequest.getParameter("cboCtaServProduct").toString();
            String cuentaCompl = webRequest.getParameter("cboCtaServProductCompl").toString();
           String id = webRequest.getParameter("servIDAdd").toString();
           
           
           
           
          
                System.out.println("nombre"+nombre);
                System.out.println("cuenta"+cuenta);
                System.out.println("id"+id);
                
                
                
                
                
                                                          
        try {
            
            
            
            
            
          
            
            if (id.isEmpty() == true || id.length() == 0){
            
                    
                 Querys que = new Querys();
               String store = que.getSQL("ProcesaServicio");
               
              
               
               
                   Map parametros = new HashMap();
                   
    
            
 
                 
                  parametros.put("compania", compania);
                  parametros.put("id", "");
                  parametros.put("nombre", nombre);
                  parametros.put("cuenta", cuenta);
                  parametros.put("cuentaCompl", cuentaCompl);
                  parametros.put("operacion", "I");
                  
                  
                  int valP = processDao.execute(store, parametros);
                    
                 
             
                if (valP == 1){
                    rq.setSuccess(true);
                    rq.setData(null);
                    rq.setMsg("Servicio Guardado Correctamente");
                }else{
                
                    rq.setSuccess(true);
                    rq.setData(null);
                    rq.setMsg("Error al guardar Servicio");
                
                }
//
//                if(result == null){
//
//                    rq.setSuccess(false);
//                    rq.setMsg("Error al guardar Datos");
//
//                }else{
//
//                    rq.setSuccess(true);
//                    rq.setMsg(""+id);
//
//                }

            }else{
                
                  Querys que = new Querys();
               String store = que.getSQL("ProcesaServicio");
               
              
               
               
                   Map parametros = new HashMap();
                   
    
            
 
                 
                  parametros.put("compania", compania);
                  parametros.put("id", id);
                  parametros.put("nombre", nombre);
                  parametros.put("cuenta", cuenta);
                  parametros.put("cuentaCompl", cuentaCompl);
                  parametros.put("operacion", "U");
                  
                  
                  int valP = processDao.execute(store, parametros);
                    
                 
             
                if (valP == 1){
                    rq.setSuccess(true);
                    rq.setData(null);
                    rq.setMsg("Servicio Guardado Correctamente");
                }else{
                
                    rq.setSuccess(true);
                    rq.setData(null);
                    rq.setMsg("Error al guardar Servicio");
                
                }
             
    //            
    //            if(result == false){
    //                
    //                rq.setSuccess(false);
    //                rq.setMsg("Error al guardar Datos");
    //                
    //            }else{
    //                
    //                rq.setSuccess(true);
    //                rq.setMsg(numero);
    //            
    //            }
    //            
    //            
            
            }
            
            
           
             
        } catch (Exception e) {
             rq.setSuccess(false);
            rq.setMsg("Error al guardar  cxp");
            e.printStackTrace();
            return rq;
        }

        return rq;

    }
    
     @RequestMapping(value = "/deleteServicio", method = RequestMethod.POST)
    public @ResponseBody
    ResponseQuery2 deleteAction(String data, WebRequest webRequest, Model model) {

        ResponseQuery2 rq = new ResponseQuery2();
      
        //System.out.println("data"+data);
        int index = data.indexOf("[");
        if (index == -1) {
            data = "[" + data + "]";
        }

        System.out.println("data:" + data);
//
        if (model.asMap().get("compania") == null) {
            rq.setSuccess(false);
            rq.setData(null);
            rq.setMsg("La sesion no es valida.");
            return rq;
        }
        String compania = model.asMap().get("compania").toString();
        String usuario = model.asMap().get("usuario").toString();



        try {
            int guardado = 0;
            String fecha = null;
            ObjectMapper mapper = new ObjectMapper();

            List<ServiciosDTO> lista = mapper.readValue(data,
                    mapper.getTypeFactory().constructCollectionType(List.class,
                            ServiciosDTO.class));

            if (lista.isEmpty()) {
                rq.setSuccess(false);
                rq.setData(null);
                rq.setMsg("Error no existen datos que guardar");
                return rq;
            }

            List<String> errores = new ArrayList<String>();

            int val = 0;
            Iterator<ServiciosDTO> it = lista.iterator();

            while (it.hasNext()) {
//                 System.out.println("-------------------------------------------------------------------");
                ServiciosDTO ss = it.next();
                
                
                  Querys que = new Querys();
               String store = que.getSQL("ProcesaServicio");
               
              
               
               
                   Map parametros = new HashMap();
                   
    
            
 
                 
                  parametros.put("compania", compania);
                  parametros.put("id", ss.id);
                  parametros.put("nombre", ss.nombre);
                  parametros.put("cuenta", ss.cuentaCargo);
                  parametros.put("cuentaCompl", ss.cuentaCargoComplementaria);
                  parametros.put("operacion", "D");
                  
                  
                  int valP = processDao.execute(store, parametros);
                    
                 
        

            }


           
                rq.setSuccess(true);
                rq.setData(null);
                rq.setMsg("Borrados Correctamente");
           
        } catch (Exception e) {
            e.printStackTrace();
        }

        return rq;
    }
    
   
    

    public void setProcessDao(ProcessDao processDao) {
        this.processDao = processDao;
    }
    
    
    
}
