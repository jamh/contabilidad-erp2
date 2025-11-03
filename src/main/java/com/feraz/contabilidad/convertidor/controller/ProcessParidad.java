/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.feraz.contabilidad.convertidor.controller;

import com.feraz.contabilidad.convertidor.dao.ErpParidadCompaniaDao;
import com.feraz.contabilidad.convertidor.dao.ErpParidadDao;
import com.feraz.contabilidad.convertidor.dto.Paridad;
import com.feraz.contabilidad.convertidor.model.ErpParidad;
import com.feraz.contabilidad.convertidor.model.ErpParidadCompania;
import com.feraz.contabilidad.convertidor.model.ErpParidadCompaniaId;
import com.feraz.contabilidad.convertidor.model.ErpParidadId;
import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import static org.apache.commons.lang.time.DateUtils.addDays;
import org.jamh.data.process.ProcessDao;
import org.jamh.wf.json.model.ResponseQuery;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.request.WebRequest;
import com.feraz.contabilidad.convertidor.util.ParidadConvertidor;
import java.text.ParseException;
import java.util.HashMap;
import java.util.Map;
import org.springframework.web.bind.annotation.RequestParam;

/**
 *
 * @author Administrador
 */
@Controller
@RequestMapping(value = "/paridad")
//@SessionAttributes({"compania", "usuario"})
public class ProcessParidad {

    private ProcessDao processDao;
    
    private ErpParidadDao erpParidadDao;
    
    private ErpParidadCompaniaDao erpParidadCompaniaDao;

    @RequestMapping(value = "/semana", method = RequestMethod.GET)
    public @ResponseBody
    ResponseQuery paridad( WebRequest webRequest, Model model) {
        // Map parametros = processDao.paramToMap(webRequest.getParameterMap());
        ParidadConvertidor pc = new ParidadConvertidor();
        ResponseQuery rq = new ResponseQuery();
        boolean isSave = true;
        List<Paridad> lista = new ArrayList<Paridad>();
//        if (model.asMap().get("compania") == null) {
//            rq.setMsg("Sesion no valida.");
//            rq.setSuccess(false);
//            return rq;
//        }

        DateFormat df = new SimpleDateFormat("dd/MM/yyyy");

        Date today = Calendar.getInstance().getTime();
        

        String hoy = df.format(today);
      String f1=  df.format(addDays(today, 1));
        Paridad fechaHoy = new Paridad();
        fechaHoy.setFECHA(f1);
        fechaHoy.setFACTOR(pc.getParidadHTML(f1, f1));
        lista.add(fechaHoy);

        
        for(int i=0;i<10;i++){
            Paridad fecha1 = new Paridad();
            String f=  df.format(addDays(today, ((-1)*i)));
            fecha1.setFECHA(f);
            fecha1.setFACTOR(pc.getParidadHTML(f, f));

            lista.add(fecha1);
        }
        
        //System.out.println("Report Date: " + reportDate);
        rq.setTotal(new BigDecimal(lista.size()));
        rq.setData(lista);
        rq.setMsg("OK");
        rq.setSuccess(true);

        return rq;
    }
    
    @RequestMapping(value = "/genera", method = RequestMethod.GET)
    public @ResponseBody
    ResponseQuery paridadGenera( WebRequest webRequest, Model model) throws ParseException {
        // Map parametros = processDao.paramToMap(webRequest.getParameterMap());
        ParidadConvertidor pc = new ParidadConvertidor();
        ErpParidad erpParidad = new ErpParidad();
        ErpParidadId erpParidadId = new ErpParidadId();
        ResponseQuery rq = new ResponseQuery();
        boolean isSave = true;
        List<Paridad> lista = new ArrayList<Paridad>();
//        if (model.asMap().get("compania") == null) {
//            rq.setMsg("Sesion no valida.");
//            rq.setSuccess(false);
//            return rq;
//        }
        try{
        System.out.println("en genera paridad");
        DateFormat df = new SimpleDateFormat("dd/MM/yyyy");

        Date today = Calendar.getInstance().getTime();
        

        String hoy = df.format(today);
        
     
        Paridad fechaHoy = new Paridad();
        fechaHoy.setFECHA(hoy);
        fechaHoy.setFACTOR(pc.getParidadHTML(hoy, hoy));
        lista.add(fechaHoy);

        System.out.println("fecha :"+hoy);
        System.out.println("factor :"+pc.getParidadHTML(hoy, hoy));
        
        erpParidadId.setDivisaOrigen("USD");
        erpParidadId.setDivisaDestino("PES");
        erpParidadId.setFecha(df.parse(hoy));
        erpParidad.setId(erpParidadId);
        erpParidad.setFactor(Double.toString(pc.getParidadHTML(hoy, hoy)));
        
        String query1 = "BuscaParidad";
           
            
                 Map param = new HashMap();
                  param.put("divisaOrig", "USD");
                  param.put("divisaDest", "PES");
                  param.put("fecha", hoy);
        
       
                   List list1 = processDao.getMapResult(query1, param);
                   
                   if (list1 == null || list1.size() <= 0){
                       
                       System.out.println("paridad no encontrada realiza insert");
                       
                       ErpParidadId result1 = erpParidadDao.save(erpParidad);
                       
                       if (result1 == null){
                           
                        rq.setData(null);
                        rq.setMsg("Error al guardar la paridad");
                        rq.setSuccess(false);
                           
                       }else{
                       
                        rq.setData(lista);
                        rq.setMsg("Paridad Guardada");
                        rq.setSuccess(true);
                       }
                     
                       
                   }else{
                       
                       System.out.println("paridad valida, realiza update");
                       
                       boolean result2 = erpParidadDao.update(erpParidad);
                       
                       if (result2 = false){
                       
                        
                           
                        rq.setData(null);
                        rq.setMsg("Error al actualizar la paridad");
                        rq.setSuccess(false);
                           
                       }else{
                       
                        rq.setData(lista);
                        rq.setMsg("Paridad Actualizada");
                        rq.setSuccess(true);
                       }
                       
                   }
              
         } catch (Exception e) {
            e.printStackTrace();
            
            rq.setData(null);
        rq.setMsg("No se pudo guardar la paridad");
        rq.setSuccess(false);
        }
       
                    

        return rq;
    }
    
    
        @RequestMapping(value = "/genera2", method = RequestMethod.GET)
    public @ResponseBody
    ResponseQuery paridadGenera2( WebRequest webRequest, Model model) throws ParseException {
        // Map parametros = processDao.paramToMap(webRequest.getParameterMap());
        ParidadConvertidor pc = new ParidadConvertidor();
        ErpParidadCompania erpParidad = new ErpParidadCompania();
        ErpParidadCompaniaId erpParidadId = new ErpParidadCompaniaId();
        ResponseQuery rq = new ResponseQuery();
        boolean isSave = true;
        List<Paridad> lista = new ArrayList<Paridad>();
//        if (model.asMap().get("compania") == null) {
//            rq.setMsg("Sesion no valida.");
//            rq.setSuccess(false);
//            return rq;
//        }
        try{
            
            
            
        System.out.println("en genera paridad");
        DateFormat df = new SimpleDateFormat("dd/MM/yyyy");

        Date today = Calendar.getInstance().getTime();
       // Date today = df.parse("17/01/2017");
        String hoy = df.format(today);
        
        
            System.out.println("hoy "+hoy);

        Calendar calendar = Calendar.getInstance();	
//        calendar.setTime(today); 
//        calendar.add(Calendar.DAY_OF_YEAR, 5);
//        
//        Date today2 = calendar.getTime();
//        String hoy2 = df.format(today2);
//        
//        System.out.println("hoy2 "+hoy2);
        
        double paridadVal =  pc.getParidadHTML2(hoy, hoy);
        System.out.println("factor2 :"+paridadVal);
     
        
        if (paridadVal == 0){
            
          for(int i = 1; i <= 10; i++){  
            calendar.setTime(today); 
            calendar.add(Calendar.DAY_OF_YEAR, -1);

            today = calendar.getTime();
            hoy = df.format(today);

            System.out.println("hoy"+i+": "+hoy);

            paridadVal =  pc.getParidadHTML2(hoy, hoy);
            System.out.println("factor"+i+" :"+paridadVal);
            
            if (paridadVal != 0){
            
                break;
                
            }
            
          }
        
        }
        
        
            System.out.println("hoy: "+hoy);
            System.out.println("factor: "+paridadVal);
        
        today = Calendar.getInstance().getTime();
        //today = df.parse("18/01/2016");
        String hoy2 = df.format(today);
        
        
        Paridad fechaHoy = new Paridad();
        fechaHoy.setFECHA(hoy2);
        fechaHoy.setFACTOR(pc.getParidadHTML2(hoy, hoy));
        lista.add(fechaHoy);

        System.out.println("fecha :"+hoy2);
        
        double paridad =  pc.getParidadHTML2(hoy, hoy);
        System.out.println("factor :"+paridad);
        erpParidadId.setCompania("-");
        erpParidadId.setDivisaOrigen("USD");
        erpParidadId.setDivisaDestino("MXN");
        erpParidadId.setFecha(df.parse(hoy2));
        erpParidad.setId(erpParidadId);
        erpParidad.setFactor(""+paridad);
        
        String query1 = "BuscaParidadCompania";
           
            
                 Map param = new HashMap();
                  param.put("divisaOrig", "USD");
                  param.put("divisaDest", "MXN");
                  param.put("fecha", hoy2);
        
       
                   List list1 = processDao.getMapResult(query1, param);
                   
                   if (list1 == null || list1.size() <= 0){
                       
                       System.out.println("paridad no encontrada realiza insert");
                       
                       ErpParidadCompaniaId result1 = erpParidadCompaniaDao.save(erpParidad);
                       
                       if (result1 == null){
                           
                        rq.setData(null);
                        rq.setMsg("Error al guardar la paridad");
                        rq.setSuccess(false);
                           
                       }else{
                       
                        rq.setData(lista);
                        rq.setMsg("Paridad Guardada");
                        rq.setSuccess(true);
                       }
                     
                       
                   }else{
                       
                       System.out.println("paridad valida, realiza update");
                       
                       boolean result2 = erpParidadCompaniaDao.update(erpParidad);
                       
                       if (result2 = false){
                       
                        
                           
                        rq.setData(null);
                        rq.setMsg("Error al actualizar la paridad");
                        rq.setSuccess(false);
                           
                       }else{
                       
                        rq.setData(lista);
                        rq.setMsg("Paridad Actualizada");
                        rq.setSuccess(true);
                       }
                       
                   }
              
         } catch (Exception e) {
            e.printStackTrace();
            
            rq.setData(null);
        rq.setMsg("No se pudo guardar la paridad");
        rq.setSuccess(false);
        }
       
                    

        return rq;
    }

    
     @RequestMapping(value = "/genera3", method = RequestMethod.GET)
    public @ResponseBody
    ResponseQuery paridadGenera3( @RequestParam("fechaIni") String fechaIni,
            @RequestParam("fechaFin") String fechaFin,
            WebRequest webRequest
            , Model model) throws ParseException {
        // Map parametros = processDao.paramToMap(webRequest.getParameterMap());
        ParidadConvertidor pc = new ParidadConvertidor();
        ErpParidadCompania erpParidad = new ErpParidadCompania();
        ErpParidadCompaniaId erpParidadId = new ErpParidadCompaniaId();
        ResponseQuery rq = new ResponseQuery();
        boolean isSave = true;
        List<Paridad> lista = new ArrayList<Paridad>();
//        if (model.asMap().get("compania") == null) {
//            rq.setMsg("Sesion no valida.");
//            rq.setSuccess(false);
//            return rq;
//        }
        try{
            
            
       SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
        //Date startDate = formatter.parse("15/03/2016");
       Date startDate = formatter.parse(fechaIni);
       // Date endDate = formatter.parse("18/07/2016");
       Date endDate = formatter.parse(fechaFin);
        Calendar start = Calendar.getInstance();
        start.setTime(startDate);
        Calendar end = Calendar.getInstance();
        end.setTime(endDate);

        for (Date date = start.getTime(); start.before(end); start.add(Calendar.DATE, 1), date = start.getTime()) {

            System.out.println(date);
            
              System.out.println("en genera paridad");
        DateFormat df = new SimpleDateFormat("dd/MM/yyyy");

        //Date today = Calendar.getInstance().getTime();
       // Date today = df.parse("15/03/2016");
        Date today = date;
        String hoy = df.format(today);
        
        
            System.out.println("hoy "+hoy);

        Calendar calendar = Calendar.getInstance();	
//        calendar.setTime(today); 
//        calendar.add(Calendar.DAY_OF_YEAR, 5);
//        
//        Date today2 = calendar.getTime();
//        String hoy2 = df.format(today2);
//        
//        System.out.println("hoy2 "+hoy2);
        
        double paridadVal =  pc.getParidadHTML2(hoy, hoy);
        System.out.println("factor2 :"+paridadVal);
     
        
        if (paridadVal == 0){
            
          for(int i = 1; i <= 10; i++){  
            calendar.setTime(today); 
            calendar.add(Calendar.DAY_OF_YEAR, -1);

            today = calendar.getTime();
            hoy = df.format(today);

            System.out.println("hoy"+i+": "+hoy);

            paridadVal =  pc.getParidadHTML2(hoy, hoy);
            System.out.println("factor"+i+" :"+paridadVal);
            
            if (paridadVal != 0){
            
                break;
                
            }
            
          }
        
        }
        
        
            System.out.println("hoy: "+hoy);
            System.out.println("factor: "+paridadVal);
        
        //today = Calendar.getInstance().getTime();
       // today = df.parse("15/03/2016");
         today = date;
        String hoy2 = df.format(today);
        
        
        Paridad fechaHoy = new Paridad();
        fechaHoy.setFECHA(hoy2);
        fechaHoy.setFACTOR(pc.getParidadHTML2(hoy, hoy));
        lista.add(fechaHoy);

        System.out.println("fecha :"+hoy2);
        
        double paridad =  pc.getParidadHTML2(hoy, hoy);
        System.out.println("factor :"+paridad);
        erpParidadId.setCompania("-");
        erpParidadId.setDivisaOrigen("USD");
        erpParidadId.setDivisaDestino("MXN");
        erpParidadId.setFecha(df.parse(hoy2));
        erpParidad.setId(erpParidadId);
        erpParidad.setFactor(""+paridad);
        
        String query1 = "BuscaParidadCompania";
           
            
                 Map param = new HashMap();
                  param.put("divisaOrig", "USD");
                  param.put("divisaDest", "MXN");
                  param.put("fecha", hoy2);
        
       
                   List list1 = processDao.getMapResult(query1, param);
                   
                   if (list1 == null || list1.size() <= 0){
                       
                       System.out.println("paridad no encontrada realiza insert");
                       
                       ErpParidadCompaniaId result1 = erpParidadCompaniaDao.save(erpParidad);
                       
                       if (result1 == null){
                           
                        rq.setData(null);
                        rq.setMsg("Error al guardar la paridad");
                        rq.setSuccess(false);
                           
                       }else{
                       
                        rq.setData(lista);
                        rq.setMsg("Paridad Guardada");
                        rq.setSuccess(true);
                       }
                     
                       
                   }else{
                       
                       System.out.println("paridad valida, realiza update");
                       
                       boolean result2 = erpParidadCompaniaDao.update(erpParidad);
                       
                       if (result2 = false){
                       
                        
                           
                        rq.setData(null);
                        rq.setMsg("Error al actualizar la paridad");
                        rq.setSuccess(false);
                           
                       }else{
                       
                        rq.setData(lista);
                        rq.setMsg("Paridad Actualizada");
                        rq.setSuccess(true);
                       }
                       
                   }
            
        }     
            
            
      
              
         } catch (Exception e) {
            e.printStackTrace();
            
            rq.setData(null);
        rq.setMsg("No se pudo guardar la paridad");
        rq.setSuccess(false);
        }
       
                    

        return rq;
    }



  //  public double pasaValor

    public void setProcessDao(ProcessDao processDao) {
        this.processDao = processDao;
    }

    public void setErpParidadDao(ErpParidadDao erpParidadDao) {
        this.erpParidadDao = erpParidadDao;
    }

    public void setErpParidadCompaniaDao(ErpParidadCompaniaDao erpParidadCompaniaDao) {
        this.erpParidadCompaniaDao = erpParidadCompaniaDao;
    }
    
}
