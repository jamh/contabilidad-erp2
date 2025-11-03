/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.feraz.ordencompra.controll;

/**
 *
 * @author vavi
 */

import com.fasterxml.jackson.databind.ObjectMapper;
import com.feraz.ordencompra.dto.DTOOrdenFact;

import java.io.IOException;
import java.math.BigDecimal;
import java.math.MathContext;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import javax.swing.JFormattedTextField;
import javax.swing.text.MaskFormatter;
import org.jamh.data.process.ProcessDao;
import org.jamh.wf.json.model.ResponseQuery;
import org.jamh.wf.json.model.ResponseQuery2;
import org.jamh.wf.process.Querys;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.context.request.WebRequest;

/**
 *
 * @author vavi
 */
@Controller
@RequestMapping("/autoriza")
@SessionAttributes({"compania", "usuario"})
public class ControlAutoriza {
    
    
    private ProcessDao processDao;
    
     @RequestMapping(value = "/autorizaFactOrden", method = RequestMethod.POST)
    public @ResponseBody
    ResponseQuery enviaTesoreriaNom( String data,String text, String id, WebRequest webRequest, Model model) throws IOException {

        ResponseQuery rq = new ResponseQuery();
       
        String compania = model.asMap().get("compania").toString();
            String usuario = model.asMap().get("usuario").toString();
       
      String just = webRequest.getParameter("justAut").toString();
       String estatus = webRequest.getParameter("radioTipoOrigen").toString();
       
                   int index = data.indexOf("[");
                    if (index == -1) {
                        data = "[" + data + "]";
                    }



                        ObjectMapper mapper = new ObjectMapper();
            
    
            
        try{
            
                    
  
            List<DTOOrdenFact> lista = mapper.readValue(data,
                    mapper.getTypeFactory().constructCollectionType(List.class,
                            DTOOrdenFact.class));
            
            
            int valP = 0;
            
            Querys que = new Querys();
               String store = que.getSQL("ActualizaAutEstatusFact");
               
             Querys que2 = new Querys();
               String store2 = que2.getSQL("ActualizaAutEstatusFactOrden");
               
                Querys que3 = new Querys();
               String store3 = que3.getSQL("ActualizaAutEstatusFactOrdenAj");
               
               Querys que4 = new Querys();
               String store4 = que4.getSQL("ActualizaAutEstatusFactAj");
               
                 Querys que5 = new Querys();
               String store5 = que5.getSQL("ACT_AUTORIZACION_ORDEN_FACT");
               
               
                 Querys queC = new Querys();
               String storeC = queC.getSQL("ActualizaAutEstatusFactCan");
               
            Querys que2C = new Querys();
               String store2C = que2C.getSQL("ActualizaAutEstatusFactCanOrden");
                  
               
                   Map parametros = new HashMap();
                   
                   

             if (lista.isEmpty()) {
                rq.setSuccess(false);
                rq.setData(null);
                rq.setMsg("Error no existen datos que guardar");
                return rq;
            }

            
 
            Iterator<DTOOrdenFact> it = lista.iterator();
             while (it.hasNext()) {
//                 System.out.println("-------------------------------------------------------------------");
                 DTOOrdenFact ss = it.next();
                 
                  parametros.put("compania", compania);
                  parametros.put("orden", ss.ordenCompra);
                  parametros.put("numeroFe", ss.numero);
                  parametros.put("origen", "FE");
                   parametros.put("motivo", just);
                   parametros.put("usuario", usuario);
                   
                  
                  
                  
//                  if(id.equalsIgnoreCase("1")){
//                      parametros.put("tipo", "");
//                      valP = processDao.execute(store, parametros);
//                      processDao.execute(store5, parametros);
//                  }else{

                if(estatus.equalsIgnoreCase("1")){
                      parametros.put("tipo", "");
                      valP = processDao.execute(store, parametros);
                      processDao.execute(store5, parametros);
                }else{
                      parametros.put("tipo", "");
                      valP = processDao.execute(storeC, parametros);
                      processDao.execute(store2C, parametros);
                  }
                  
                //  }
                  
                  
                  
                  //valP = processDao.execute(store, parametros);
                  
                  //processDao.execute(store2, parametros);
                    
                 
             }
             
 //  P_SID              VARCHAR2,

          
            
          


               
                   
          
       
                if (valP == 1){
                    rq.setSuccess(true);
                    rq.setData(null);
                    rq.setMsg("Factura Autorizada Correctamente");
                }else{
                
                    rq.setSuccess(true);
                    rq.setData(null);
                    rq.setMsg("Error al autorizar factura");
                
                }
    

        }catch(Exception e){
            
            e.printStackTrace();
            
            rq.setSuccess(false);
            rq.setData(null);
            rq.setMsg("Error al guardar");
           return rq;
        }
        
        return rq;
        
        
    } 
    
     @RequestMapping(value = "/autorizaFactOrdenMult2", method = RequestMethod.POST)
    public @ResponseBody
    ResponseQuery autorizaFactOrdenMult2( String data,String text, String id, WebRequest webRequest, Model model) throws IOException {

        ResponseQuery rq = new ResponseQuery();
       
        String compania = model.asMap().get("compania").toString();
            String usuario = model.asMap().get("usuario").toString();
       
      String just = webRequest.getParameter("justAut").toString();
       String estatus = webRequest.getParameter("radioTipoOrigen").toString();
       
                   int index = data.indexOf("[");
                    if (index == -1) {
                        data = "[" + data + "]";
                    }



                        ObjectMapper mapper = new ObjectMapper();
            
    
            
        try{
            
                    
  
            List<DTOOrdenFact> lista = mapper.readValue(data,
                    mapper.getTypeFactory().constructCollectionType(List.class,
                            DTOOrdenFact.class));
            
            
            int valP = 0;
            
            Querys que = new Querys();
               String store = que.getSQL("ActualizaAutEstatusFact");
               
             Querys que2 = new Querys();
               String store2 = que2.getSQL("ActualizaAutEstatusFactOrden");
               
                Querys que3 = new Querys();
               String store3 = que3.getSQL("ActualizaAutEstatusFactOrdenAj");
               
               Querys que4 = new Querys();
               String store4 = que4.getSQL("ActualizaAutEstatusFactAj");
               
                 Querys que5 = new Querys();
               String store5 = que5.getSQL("ACT_AUTORIZACION_ORDEN_FACT");
               
               
                 Querys queC = new Querys();
               String storeC = queC.getSQL("ActualizaAutEstatusFactCan");
               
            Querys que2C = new Querys();
               String store2C = que2C.getSQL("ActualizaAutEstatusFactCanOrden");
               
               Querys queDoble = new Querys();
               String storeDoble = queDoble.getSQL("ActuailizaFactOrdenDob2");
                  
               
               
               
                   Map parametros = new HashMap();
                   
                   

             if (lista.isEmpty()) {
                rq.setSuccess(false);
                rq.setData(null);
                rq.setMsg("Error no existen datos que guardar");
                return rq;
            }

            
 
            Iterator<DTOOrdenFact> it = lista.iterator();
             while (it.hasNext()) {
//                 System.out.println("-------------------------------------------------------------------");
                 DTOOrdenFact ss = it.next();
                 
                  parametros.put("compania", compania);
                  parametros.put("orden", ss.ordenCompra);
                  parametros.put("numeroFe", ss.numero);
                  parametros.put("origen", "FE");
                   parametros.put("motivo", just);
                   parametros.put("usuario", usuario);
                   
             if(estatus.equalsIgnoreCase("1")){
                   processDao.execute(storeDoble, parametros);


                    Map ordenFact = new HashMap();
                        ordenFact.put("compania", compania);
                        ordenFact.put("idOrden", ss.ordenCompra);
                        ordenFact.put("numeroFe", ss.numero);
                        ordenFact.put("origen", "FE");

                        List listDbAuto = this.processDao.getMapResult("BuscaDobleAutorizacionFact", ordenFact);  
                        Map aut = (HashMap)listDbAuto.get(0);

                        String estatusA = aut.get("ID_AUTO").toString();

                        if(!estatusA.equalsIgnoreCase("0")){

                                      parametros.put("tipo", "");
                                      valP = processDao.execute(store, parametros);
                                      processDao.execute(store5, parametros);




                        }
                   }else{
                                  parametros.put("tipo", "");
                                  valP = processDao.execute(storeC, parametros);
                                  processDao.execute(store2C, parametros);
                              }
                
           
                  
                //  }
                  
                  
                  
                  //valP = processDao.execute(store, parametros);
                  
                  //processDao.execute(store2, parametros);
                    
                 
             }
             
 //  P_SID              VARCHAR2,

          
            
          


               
                   
          
       
                if (valP == 1){
                    rq.setSuccess(true);
                    rq.setData(null);
                    rq.setMsg("Factura Autorizada Correctamente");
                }else{
                
                    rq.setSuccess(true);
                    rq.setData(null);
                    rq.setMsg("Error al autorizar factura");
                
                }
    

        }catch(Exception e){
            
            e.printStackTrace();
            
            rq.setSuccess(false);
            rq.setData(null);
            rq.setMsg("Error al guardar");
           return rq;
        }
        
        return rq;
        
        
    } 
    
     @RequestMapping(value = "/autorizaFactOrdenMult2Ext", method = RequestMethod.POST)
    public @ResponseBody
    ResponseQuery autorizaFactOrdenMult2Ext( String data,String text, String id, WebRequest webRequest, Model model) throws IOException {

        ResponseQuery rq = new ResponseQuery();
       
        String compania = model.asMap().get("compania").toString();
            String usuario = model.asMap().get("usuario").toString();
       
      String just = webRequest.getParameter("justAut").toString();
       String estatus = webRequest.getParameter("radioTipoOrigen").toString();
       
                   int index = data.indexOf("[");
                    if (index == -1) {
                        data = "[" + data + "]";
                    }



                        ObjectMapper mapper = new ObjectMapper();
            
    
            
        try{
            
                    
  
            List<DTOOrdenFact> lista = mapper.readValue(data,
                    mapper.getTypeFactory().constructCollectionType(List.class,
                            DTOOrdenFact.class));
            
            
            int valP = 0;
            
            Querys que = new Querys();
               String store = que.getSQL("ActualizaAutEstatusFact");
               
             Querys que2 = new Querys();
               String store2 = que2.getSQL("ActualizaAutEstatusFactOrden");
               
                Querys que3 = new Querys();
               String store3 = que3.getSQL("ActualizaAutEstatusFactOrdenAj");
               
               Querys que4 = new Querys();
               String store4 = que4.getSQL("ActualizaAutEstatusFactAj");
               
                 Querys que5 = new Querys();
               String store5 = que5.getSQL("ACT_AUTORIZACION_ORDEN_FACT");
               
               
                 Querys queC = new Querys();
               String storeC = queC.getSQL("ActualizaAutEstatusFactCan");
               
            Querys que2C = new Querys();
               String store2C = que2C.getSQL("ActualizaAutEstatusFactCanOrden");
               
               Querys queDoble = new Querys();
               String storeDoble = queDoble.getSQL("ActuailizaFactOrdenDob2");
                  
               
               
               
                   Map parametros = new HashMap();
                   
                   

             if (lista.isEmpty()) {
                rq.setSuccess(false);
                rq.setData(null);
                rq.setMsg("Error no existen datos que guardar");
                return rq;
            }

            
 
            Iterator<DTOOrdenFact> it = lista.iterator();
             while (it.hasNext()) {
//                 System.out.println("-------------------------------------------------------------------");
                 DTOOrdenFact ss = it.next();
                 
                  parametros.put("compania", compania);
                  parametros.put("orden", ss.ordenCompra);
                  parametros.put("numeroFe", ss.numero);
                  parametros.put("origen", "OTR");
                   parametros.put("motivo", just);
                   parametros.put("usuario", usuario);
                   
             if(estatus.equalsIgnoreCase("1")){
                   processDao.execute(storeDoble, parametros);


                    Map ordenFact = new HashMap();
                        ordenFact.put("compania", compania);
                        ordenFact.put("idOrden", ss.ordenCompra);
                        ordenFact.put("numeroFe", ss.numero);
                        ordenFact.put("origen", "OTR");

                        List listDbAuto = this.processDao.getMapResult("BuscaDobleAutorizacionFact", ordenFact);  
                        Map aut = (HashMap)listDbAuto.get(0);

                        String estatusA = aut.get("ID_AUTO").toString();

                        if(!estatusA.equalsIgnoreCase("0")){

                                      parametros.put("tipo", "");
                                      valP = processDao.execute(store, parametros);
                                      processDao.execute(store5, parametros);




                        }
                   }else{
                                  parametros.put("tipo", "");
                                  valP = processDao.execute(storeC, parametros);
                                  processDao.execute(store2C, parametros);
                              }
                
           
                  
                //  }
                  
                  
                  
                  //valP = processDao.execute(store, parametros);
                  
                  //processDao.execute(store2, parametros);
                    
                 
             }
             
 //  P_SID              VARCHAR2,

          
            
          


               
                   
          
       
                if (valP == 1){
                    rq.setSuccess(true);
                    rq.setData(null);
                    rq.setMsg("Factura Autorizada Correctamente");
                }else{
                
                    rq.setSuccess(true);
                    rq.setData(null);
                    rq.setMsg("Error al autorizar factura");
                
                }
    

        }catch(Exception e){
            
            e.printStackTrace();
            
            rq.setSuccess(false);
            rq.setData(null);
            rq.setMsg("Error al guardar");
           return rq;
        }
        
        return rq;
        
        
    } 
    
    
    
         @RequestMapping(value = "/autorizaFactOrdenMult1", method = RequestMethod.POST)
    public @ResponseBody
    ResponseQuery autorizaFactOrdenMult1( String data,String text, String id, WebRequest webRequest, Model model) throws IOException {

        ResponseQuery rq = new ResponseQuery();
       
        String compania = model.asMap().get("compania").toString();
            String usuario = model.asMap().get("usuario").toString();
       
      String just = webRequest.getParameter("justAut").toString();
       String estatus = webRequest.getParameter("radioTipoOrigen").toString();
       
                   int index = data.indexOf("[");
                    if (index == -1) {
                        data = "[" + data + "]";
                    }



                        ObjectMapper mapper = new ObjectMapper();
            
    
            
        try{
            
                    
  
            List<DTOOrdenFact> lista = mapper.readValue(data,
                    mapper.getTypeFactory().constructCollectionType(List.class,
                            DTOOrdenFact.class));
            
            
            int valP = 0;
            
            Querys que = new Querys();
               String store = que.getSQL("ActualizaAutEstatusFact");
               
             Querys que2 = new Querys();
               String store2 = que2.getSQL("ActualizaAutEstatusFactOrden");
               
                Querys que3 = new Querys();
               String store3 = que3.getSQL("ActualizaAutEstatusFactOrdenAj");
               
               Querys que4 = new Querys();
               String store4 = que4.getSQL("ActualizaAutEstatusFactAj");
               
                 Querys que5 = new Querys();
               String store5 = que5.getSQL("ACT_AUTORIZACION_ORDEN_FACT");
               
               
                 Querys queC = new Querys();
               String storeC = queC.getSQL("ActualizaAutEstatusFactCan");
               
            Querys que2C = new Querys();
               String store2C = que2C.getSQL("ActualizaAutEstatusFactCanOrden");
               
               Querys queDoble = new Querys();
               String storeDoble = queDoble.getSQL("ActuailizaFactOrdenDob1");
                  
               
               
               
                   Map parametros = new HashMap();
                   
                   

             if (lista.isEmpty()) {
                rq.setSuccess(false);
                rq.setData(null);
                rq.setMsg("Error no existen datos que guardar");
                return rq;
            }

            
 
            Iterator<DTOOrdenFact> it = lista.iterator();
             while (it.hasNext()) {
//                 System.out.println("-------------------------------------------------------------------");
                 DTOOrdenFact ss = it.next();
                 
                  parametros.put("compania", compania);
                  parametros.put("orden", ss.ordenCompra);
                  parametros.put("numeroFe", ss.numero);
                  parametros.put("origen", "FE");
                   parametros.put("motivo", just);
                   parametros.put("usuario", usuario);
                   
             if(estatus.equalsIgnoreCase("1")){
                   processDao.execute(storeDoble, parametros);


                    Map ordenFact = new HashMap();
                        ordenFact.put("compania", compania);
                        ordenFact.put("idOrden", ss.ordenCompra);
                        ordenFact.put("numeroFe", ss.numero);
                        ordenFact.put("origen", "FE");

                        List listDbAuto = this.processDao.getMapResult("BuscaDobleAutorizacionFact", ordenFact);  
                        Map aut = (HashMap)listDbAuto.get(0);

                        String estatusA = aut.get("ID_AUTO").toString();

                        if(!estatusA.equalsIgnoreCase("0")){

                                      parametros.put("tipo", "");
                                      valP = processDao.execute(store, parametros);
                                      processDao.execute(store5, parametros);




                        }
                   }else{
                                  parametros.put("tipo", "");
                                  valP = processDao.execute(storeC, parametros);
                                  processDao.execute(store2C, parametros);
                              }
                
           
                  
                //  }
                  
                  
                  
                  //valP = processDao.execute(store, parametros);
                  
                  //processDao.execute(store2, parametros);
                    
                 
             }
             
 //  P_SID              VARCHAR2,

          
            
          


               
                   
          
       
                if (valP == 1){
                    rq.setSuccess(true);
                    rq.setData(null);
                    rq.setMsg("Factura Autorizada Correctamente");
                }else{
                
                    rq.setSuccess(true);
                    rq.setData(null);
                    rq.setMsg("Error al autorizar factura");
                
                }
    

        }catch(Exception e){
            
            e.printStackTrace();
            
            rq.setSuccess(false);
            rq.setData(null);
            rq.setMsg("Error al guardar");
           return rq;
        }
        
        return rq;
        
        
    } 
    
         @RequestMapping(value = "/autorizaFactOrdenMult1Ext", method = RequestMethod.POST)
    public @ResponseBody
    ResponseQuery autorizaFactOrdenMult1Ext( String data,String text, String id, WebRequest webRequest, Model model) throws IOException {

        ResponseQuery rq = new ResponseQuery();
       
        String compania = model.asMap().get("compania").toString();
            String usuario = model.asMap().get("usuario").toString();
       
      String just = webRequest.getParameter("justAut").toString();
       String estatus = webRequest.getParameter("radioTipoOrigen").toString();
       
                   int index = data.indexOf("[");
                    if (index == -1) {
                        data = "[" + data + "]";
                    }



                        ObjectMapper mapper = new ObjectMapper();
            
    
            
        try{
            
                    
  
            List<DTOOrdenFact> lista = mapper.readValue(data,
                    mapper.getTypeFactory().constructCollectionType(List.class,
                            DTOOrdenFact.class));
            
            
            int valP = 0;
            
            Querys que = new Querys();
               String store = que.getSQL("ActualizaAutEstatusFact");
               
             Querys que2 = new Querys();
               String store2 = que2.getSQL("ActualizaAutEstatusFactOrden");
               
                Querys que3 = new Querys();
               String store3 = que3.getSQL("ActualizaAutEstatusFactOrdenAj");
               
               Querys que4 = new Querys();
               String store4 = que4.getSQL("ActualizaAutEstatusFactAj");
               
                 Querys que5 = new Querys();
               String store5 = que5.getSQL("ACT_AUTORIZACION_ORDEN_FACT");
               
               
                 Querys queC = new Querys();
               String storeC = queC.getSQL("ActualizaAutEstatusFactCan");
               
            Querys que2C = new Querys();
               String store2C = que2C.getSQL("ActualizaAutEstatusFactCanOrden");
               
               Querys queDoble = new Querys();
               String storeDoble = queDoble.getSQL("ActuailizaFactOrdenDob1");
                  
               
               
               
                   Map parametros = new HashMap();
                   
                   

             if (lista.isEmpty()) {
                rq.setSuccess(false);
                rq.setData(null);
                rq.setMsg("Error no existen datos que guardar");
                return rq;
            }

            
 
            Iterator<DTOOrdenFact> it = lista.iterator();
             while (it.hasNext()) {
//                 System.out.println("-------------------------------------------------------------------");
                 DTOOrdenFact ss = it.next();
                 
                  parametros.put("compania", compania);
                  parametros.put("orden", ss.ordenCompra);
                  parametros.put("numeroFe", ss.numero);
                  parametros.put("origen", "OTR");
                   parametros.put("motivo", just);
                   parametros.put("usuario", usuario);
                   
             if(estatus.equalsIgnoreCase("1")){
                   processDao.execute(storeDoble, parametros);


                    Map ordenFact = new HashMap();
                        ordenFact.put("compania", compania);
                        ordenFact.put("idOrden", ss.ordenCompra);
                        ordenFact.put("numeroFe", ss.numero);
                        ordenFact.put("origen", "OTR");

                        List listDbAuto = this.processDao.getMapResult("BuscaDobleAutorizacionFact", ordenFact);  
                        Map aut = (HashMap)listDbAuto.get(0);

                        String estatusA = aut.get("ID_AUTO").toString();

                        if(!estatusA.equalsIgnoreCase("0")){

                                      parametros.put("tipo", "");
                                      valP = processDao.execute(store, parametros);
                                      processDao.execute(store5, parametros);




                        }
                   }else{
                                  parametros.put("tipo", "");
                                  valP = processDao.execute(storeC, parametros);
                                  processDao.execute(store2C, parametros);
                              }
                
           
                  
                //  }
                  
                  
                  
                  //valP = processDao.execute(store, parametros);
                  
                  //processDao.execute(store2, parametros);
                    
                 
             }
             
 //  P_SID              VARCHAR2,

          
            
          


               
                   
          
       
                if (valP == 1){
                    rq.setSuccess(true);
                    rq.setData(null);
                    rq.setMsg("Factura Autorizada Correctamente");
                }else{
                
                    rq.setSuccess(true);
                    rq.setData(null);
                    rq.setMsg("Error al autorizar factura");
                
                }
    

        }catch(Exception e){
            
            e.printStackTrace();
            
            rq.setSuccess(false);
            rq.setData(null);
            rq.setMsg("Error al guardar");
           return rq;
        }
        
        return rq;
        
        
    } 
    
         @RequestMapping(value = "/autorizaFactOrdenExt", method = RequestMethod.POST)
    public @ResponseBody
    ResponseQuery enviaTesoreriaFactExt( String data,String text,String id, WebRequest webRequest, Model model) throws IOException {

        ResponseQuery rq = new ResponseQuery();
       
        String compania = model.asMap().get("compania").toString();
            String usuario = model.asMap().get("usuario").toString();
            
       String just = webRequest.getParameter("justAut").toString();
       String estatus = webRequest.getParameter("radioTipoOrigen").toString();
       
             System.out.println("just: "+just);
             System.out.println("estatus: "+estatus);
       
                   int index = data.indexOf("[");
                    if (index == -1) {
                        data = "[" + data + "]";
                    }



                        ObjectMapper mapper = new ObjectMapper();
            
    
            
        try{
            
                    
  
            List<DTOOrdenFact> lista = mapper.readValue(data,
                    mapper.getTypeFactory().constructCollectionType(List.class,
                            DTOOrdenFact.class));
            
            
            int valP = 0;
            
            Querys que = new Querys();
               String store = que.getSQL("ActualizaAutEstatusFact");
               
               Querys que2 = new Querys();
               String store2 = que2.getSQL("ActualizaAutEstatusFactOrden");
               
               Querys que3 = new Querys();
               String store3 = que3.getSQL("ActualizaAutEstatusFactOrdenAj");
               
               Querys que4 = new Querys();
               String store4 = que4.getSQL("ActualizaAutEstatusFactAj");
               
               Querys que5 = new Querys();
               String store5 = que5.getSQL("ACT_AUTORIZACION_ORDEN_FACT");
               
               
                  Querys queC = new Querys();
               String storeC = queC.getSQL("ActualizaAutEstatusFactCan");
               
            Querys que2C = new Querys();
               String store2C = que2C.getSQL("ActualizaAutEstatusFactCanOrden");
                  
               
               

            
 
                    
               
               
              
                   Map parametros = new HashMap();
                   
                   

             if (lista.isEmpty()) {
                rq.setSuccess(false);
                rq.setData(null);
                rq.setMsg("Error no existen datos que guardar");
                return rq;
            }

            
 
            Iterator<DTOOrdenFact> it = lista.iterator();
             while (it.hasNext()) {
//                 System.out.println("-------------------------------------------------------------------");
                 DTOOrdenFact ss = it.next();
                 
                  parametros.put("compania", compania);
                  parametros.put("orden", ss.ordenCompra);
                  parametros.put("numeroFe", ss.numero);
                  parametros.put("origen", "OTR");
                  parametros.put("motivo", just);
                  parametros.put("usuario", usuario);
                  
                  
                  
//                  
                  if(estatus.equalsIgnoreCase("1")){
                      parametros.put("tipo", "");
                      valP = processDao.execute(store, parametros);
                      processDao.execute(store5, parametros);
                  }else{
                      parametros.put("tipo", "");
                      valP = processDao.execute(storeC, parametros);
                      processDao.execute(store2C, parametros);
                  }
                  
                  
                    
                 
             }
             

          
       
                if (valP == 1){
                    rq.setSuccess(true);
                    rq.setData(null);
                    rq.setMsg("Factura Autorizada Correctamente");
                }else{
                
                    rq.setSuccess(true);
                    rq.setData(null);
                    rq.setMsg("Error al autorizar factura");
                
                }
    

        }catch(Exception e){
            
            e.printStackTrace();
            
            rq.setSuccess(false);
            rq.setData(null);
            rq.setMsg("Error al guardar");
           return rq;
        }
        
        return rq;
        
        
    }  
    
    
    
    
             @RequestMapping(value = "/cancelaFactOrden", method = RequestMethod.POST)
    public @ResponseBody
    ResponseQuery cancelaFact( String data, WebRequest webRequest, Model model) throws IOException {

        ResponseQuery rq = new ResponseQuery();
       
        String compania = model.asMap().get("compania").toString();
            String usuario = model.asMap().get("usuario").toString();
       
      
                   int index = data.indexOf("[");
                    if (index == -1) {
                        data = "[" + data + "]";
                    }



                        ObjectMapper mapper = new ObjectMapper();
            
    
            
        try{
            
                    
  
            List<DTOOrdenFact> lista = mapper.readValue(data,
                    mapper.getTypeFactory().constructCollectionType(List.class,
                            DTOOrdenFact.class));
            
            
            int valP = 0;
            
            Querys que = new Querys();
               String store = que.getSQL("ActualizaAutEstatusFactCan");
               
                Querys que2 = new Querys();
               String store2 = que2.getSQL("ActualizaAutEstatusFactCanOrden");
               
                       
               
               
               
                   Map parametros = new HashMap();
                   
                   

             if (lista.isEmpty()) {
                rq.setSuccess(false);
                rq.setData(null);
                rq.setMsg("Error no existen datos que guardar");
                return rq;
            }

            
 
            Iterator<DTOOrdenFact> it = lista.iterator();
             while (it.hasNext()) {
//                 System.out.println("-------------------------------------------------------------------");
                 DTOOrdenFact ss = it.next();
                 
                  parametros.put("compania", compania);
                  parametros.put("orden", ss.ordenCompra);
                  parametros.put("numeroFe", ss.numero);
                  parametros.put("origen", "FE");
                  
                  
                  valP = processDao.execute(store, parametros);
                  processDao.execute(store2, parametros);
                    
                 
             }
             

          
       
                if (valP == 1){
                    rq.setSuccess(true);
                    rq.setData(null);
                    rq.setMsg("Factura Cancelada Correctamente");
                }else{
                
                    rq.setSuccess(true);
                    rq.setData(null);
                    rq.setMsg("Error al cancelar factura");
                
                }
    

        }catch(Exception e){
            
            e.printStackTrace();
            
            rq.setSuccess(false);
            rq.setData(null);
            rq.setMsg("Error al guardar");
           return rq;
        }
        
        return rq;
        
        
    }  
    
    
        @RequestMapping(value = "/cancelaFactOrdenExt", method = RequestMethod.POST)
    public @ResponseBody
    ResponseQuery cancelaFactExt( String data, WebRequest webRequest, Model model) throws IOException {

        ResponseQuery rq = new ResponseQuery();
       
        String compania = model.asMap().get("compania").toString();
            String usuario = model.asMap().get("usuario").toString();
       
      
                   int index = data.indexOf("[");
                    if (index == -1) {
                        data = "[" + data + "]";
                    }



                        ObjectMapper mapper = new ObjectMapper();
            
    
            
        try{
            
                    
  
            List<DTOOrdenFact> lista = mapper.readValue(data,
                    mapper.getTypeFactory().constructCollectionType(List.class,
                            DTOOrdenFact.class));
            
            
            int valP = 0;
            
            Querys que = new Querys();
               String store = que.getSQL("ActualizaAutEstatusFactCan");
               
            Querys que2 = new Querys();
               String store2 = que2.getSQL("ActualizaAutEstatusFactCanOrden");
                  
               
                   Map parametros = new HashMap();
                   
                   

             if (lista.isEmpty()) {
                rq.setSuccess(false);
                rq.setData(null);
                rq.setMsg("Error no existen datos que guardar");
                return rq;
            }

            
 
            Iterator<DTOOrdenFact> it = lista.iterator();
             while (it.hasNext()) {
//                 System.out.println("-------------------------------------------------------------------");
                 DTOOrdenFact ss = it.next();
                 
                  parametros.put("compania", compania);
                  parametros.put("orden", ss.ordenCompra);
                  parametros.put("numeroFe", ss.numero);
                  parametros.put("origen", "OTR");
                  
                  
                  valP = processDao.execute(store, parametros);
                  
                  processDao.execute(store2, parametros);
                    
                 
             }
             

          
       
                if (valP == 1){
                    rq.setSuccess(true);
                    rq.setData(null);
                    rq.setMsg("Factura Cancelada Correctamente");
                }else{
                
                    rq.setSuccess(true);
                    rq.setData(null);
                    rq.setMsg("Error al cancelar factura");
                
                }
    

        }catch(Exception e){
            
            e.printStackTrace();
            
            rq.setSuccess(false);
            rq.setData(null);
            rq.setMsg("Error al guardar");
           return rq;
        }
        
        return rq;
        
        
    }  
    
    
    
    
    

    public void setProcessDao(ProcessDao processDao) {
        this.processDao = processDao;
    }
    
    
    
}
