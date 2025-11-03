/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.feraz.contabilidad.sat.diot.controller;

/**
 *
 * @author Feraz3
 */

import com.fasterxml.jackson.databind.ObjectMapper;
import com.feraz.contabilidad.sat.diot.dao.DetDIOTDao;
import com.feraz.contabilidad.sat.diot.dao.ConceptosDIOTDao;

import com.feraz.polizas3.util.ResponseQuery2;
import com.feraz.contabilidad.sat.diot.model.DetDIOT;
import com.feraz.contabilidad.sat.diot.model.DetDIOTId;
import com.feraz.contabilidad.sat.diot.model.ConceptosDIOT;
import com.feraz.contabilidad.sat.diot.model.ConceptosDIOTId;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import org.jamh.data.process.ProcessDao;
import org.jamh.wf.json.model.ResponseQuery;
import org.jamh.wf.process.Querys;
import org.springframework.stereotype.Controller;
import com.feraz.contabilidad.sat.diot.util.DetDIOTDTO;
import java.math.BigDecimal;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.context.request.WebRequest;


@Controller
@RequestMapping("/controlDiot")
@SessionAttributes({"compania", "usuario"})

public class ProcessDiot {
    
    private ProcessDao processDao;
    private DetDIOTDao detDIOTDao;
    private ConceptosDIOTDao conceptosDIOTDao;
    
    
     @RequestMapping(value = "/procesaDiot/inserta", method = RequestMethod.POST)
    public @ResponseBody
    ResponseQuery2 insertDiot(String data, WebRequest webRequest, Model model) {

        ResponseQuery2 rq = new ResponseQuery2();
        SimpleDateFormat formatoDelTexto = new SimpleDateFormat("dd/MM/yyyy");
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
        
         try {
            int guardado = 0;
           
            ObjectMapper mapper = new ObjectMapper();
            DateFormat df = new SimpleDateFormat("dd/MM/yyyy");

            mapper.setDateFormat(df);
            List<DetDIOTDTO> lista = mapper.readValue(data,
                    mapper.getTypeFactory().constructCollectionType(List.class,
                            DetDIOTDTO.class));

            if (lista.isEmpty()) {
                rq.setSuccess(false);
                rq.setData(null);
                rq.setMsg("Error no existen datos que guardar");
                return rq;
            }

            int val = 0;
            Iterator<DetDIOTDTO> it = lista.iterator();
             Iterator<DetDIOTDTO> it2 = lista.iterator();
            
            BigDecimal suma = new BigDecimal(0);
            
             while (it2.hasNext()) {
             
                 DetDIOTDTO aa = it2.next();
                 
                 
                 System.out.println("cargos"+aa.cargos);
                 System.out.println("abonos"+aa.abonos);
                 
                 suma = suma.add(new BigDecimal(aa.importe));
                 
                 System.out.println("suma"+suma);
                 
                 int compara = new BigDecimal(0).compareTo(new BigDecimal(aa.abonos));
                 int compara2 = new BigDecimal(0).compareTo(new BigDecimal(aa.cargos));
                 
                 if(compara == 0){
                 
                     int res = suma.compareTo(new BigDecimal(aa.cargos));
                     
                     System.out.println("cargos"+res);
                     
                     if (res == 1){
                       
                         System.out.println("res"+res);
                         
                         rq.setSuccess(false);
               
                        rq.setMsg("Error al guardar, importe mas grande que cargos");
                        return rq;
                     
                     }
                     
                 
                 }else{
                     
                     int res = suma.compareTo(new BigDecimal(aa.abonos));
                     
                      System.out.println("abonos"+res);
                     
                     if (res == 1){
                       
                         System.out.println("res"+res);
                          rq.setSuccess(false);
               
                        rq.setMsg("Error al guardar, importe mas grande que abonos");
                        
                        return rq;
                     
                     }
                     
                     
                 
                 
                 
                 }
                 
                 
             
             }
            
            
           
            while (it.hasNext()) {
//                 System.out.println("-------------------------------------------------------------------");
                DetDIOTDTO ss = it.next();
                
                System.out.println("importe"+ ss.importe);
                System.out.println("fecha"+ ss.fecha);
            
                
                DetDIOT detDIOT = new DetDIOT();
                DetDIOTId detDIOTId = new DetDIOTId();
                
                ConceptosDIOT conceptosDIOT = new ConceptosDIOT();
                ConceptosDIOTId conceptosDIOTId = new ConceptosDIOTId();
               
                
//                conceptosDIOTId.setCompania(ss.compania);
//                conceptosDIOTId.setConcepto(ss.concepto);
//                conceptosDIOT.setId(conceptosDIOTId);
//                
                  String query1 = "BuscaDetDiot";
            
                 Map param = new HashMap();
                  param.put("compania", ss.compania);
                  param.put("concepto", ss.concepto);
                  param.put("cuenta", ss.cuenta);
                  param.put("fecha", ss.fecha);
                  param.put("numero", ss.numero);
                  param.put("sec", ss.sec);
                  param.put("tipoPoliza", ss.tipoPoliza);
        
       
                   List buscaConcepto = processDao.getMapResult(query1, param);
                   
                    System.out.println(buscaConcepto);
//                     System.out.println(buscaConcepto.get(0));
                    
                    List cuentaDiot = processDao.getMapResult("BuscaCuentaDiot", param);
                    
                     Map cuen = (HashMap) cuentaDiot.get(0);
              
                     
                detDIOTId.setCompania(ss.compania);
                detDIOTId.setConcepto(ss.concepto);
                detDIOTId.setCuenta(cuen.get("CUENTA").toString());
                detDIOTId.setFecha(df.parse(ss.fecha));
                detDIOTId.setNumero(ss.numero);
                detDIOTId.setSec(Integer.parseInt(ss.sec));
                detDIOTId.setTipoPoliza(ss.tipoPoliza);
                detDIOT.setId(detDIOTId);
                detDIOT.setImporte(new BigDecimal(ss.importe));
                
                if (buscaConcepto.isEmpty()){
                    
                    System.out.println("Inserta");
                    
                    
                    DetDIOTId result = detDIOTDao.save(detDIOT);
                
                    if (result == null){
                    
                     rq.setSuccess(false);
               
                        rq.setMsg("Error al guardar");
                    
                    
                    }else{
                    
                     rq.setSuccess(true);
               
                     rq.setMsg("Guardados Correctamente");
                    
                    }
                
                
                
                }else{
                
                       System.out.println("Actualiza");
                       
                     Boolean result = detDIOTDao.update(detDIOT);
                
                     
                     if (result == false){
                    
                     rq.setSuccess(false);
               
                        rq.setMsg("Error al guardar");
                    
                    
                    }else{
                    
                     rq.setSuccess(true);
               
                     rq.setMsg("Guardados Correctamente");
                    
                    }
                
                }
                
                
            
            }

           
            

              
            

        } catch (Exception e) {
            
            e.printStackTrace();
            
            rq.setSuccess(false);
               
            rq.setMsg("Error al guardar");
        }

        return rq;
        
     }

    public void setProcessDao(ProcessDao processDao) {
        this.processDao = processDao;
    }

    public void setDetDIOTDao(DetDIOTDao detDIOTDao) {
        this.detDIOTDao = detDIOTDao;
    }

    public void setConceptosDIOTDao(ConceptosDIOTDao conceptosDIOTDao) {
        this.conceptosDIOTDao = conceptosDIOTDao;
    }
     
    
     
    
}
