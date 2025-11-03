/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.feraz.contabilidad.convertidor.controller;

/**
 *
 * @author Feraz3
 */


import com.feraz.contabilidad.convertidor.dao.ErpCatConvertidorDao;
import com.feraz.contabilidad.convertidor.dao.ErpDetConvertidorDao;
import com.feraz.contabilidad.convertidor.model.ErpCatConvertidorId;
import com.feraz.contabilidad.convertidor.model.ErpCatConvertidor;
import com.feraz.contabilidad.convertidor.model.ErpDetConvertidor;
import com.feraz.contabilidad.convertidor.model.ErpDetConvertidorId;
import com.feraz.facturas.webcontrolfe.model.ExtJSFormResult;
import java.math.BigDecimal;
import java.util.List;
import java.util.Map;
import org.jamh.data.process.ProcessDao;
import org.jamh.wf.json.model.ResponseQuery;
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
 * @author 55555
 */
@Controller
@RequestMapping(value = "/Convertidor")
@SessionAttributes({"compania", "usuario"})
public class ProcessConvertidor {
    
    private ProcessDao processDao;
    private ErpCatConvertidorDao erpCatConvertidorDao;
    private ErpDetConvertidorDao erpDetConvertidorDao;

      @RequestMapping(value = "/save", method = RequestMethod.POST)
    public @ResponseBody
    String updateCampos(String data, WebRequest webRequest, Model model) {  
         
         
          Map parametros = processDao.paramToMap(webRequest.getParameterMap());
          
          System.out.println("idConGasto: "+parametros.get("IDCONCGASTO").toString());
          
         ExtJSFormResult extjsFormResult = new ExtJSFormResult();
         ResponseQuery rq = new ResponseQuery();
                boolean isSave = true;
               
                if (model.asMap().get("compania") == null) {
                extjsFormResult.setMessage("La sesion no es valida.");
                extjsFormResult.setSuccess(false);
            
               return extjsFormResult.toString();
        }
                
        try{
                ErpCatConvertidorId erpCatConvertidorId = new ErpCatConvertidorId();
                ErpCatConvertidor erpCatConvertidor = new ErpCatConvertidor();
                erpCatConvertidorId.setCompania(parametros.get("COMPANIA").toString());
                erpCatConvertidorId.setIdconcgasto(parametros.get("IDCONCGASTO").toString());
                erpCatConvertidorId.setOrigen(parametros.get("ORIGEN").toString());
                //erpCatConvertidorId.setNoCaso(new BigDecimal(1));
                
             
                
           List<ErpCatConvertidor> convertidor = erpCatConvertidorDao.getConvertidor(parametros.get("COMPANIA").toString(), parametros.get("IDCONCGASTO").toString(), parametros.get("NO_CASO").toString(), parametros.get("ORIGEN").toString());
           
           if (convertidor == null) {
               
          
                
                
          int secMax = erpCatConvertidorDao.getMaxIdConvertidor(erpCatConvertidorId);
          
          System.out.println("secuencia"+secMax);
          
                  erpCatConvertidorId.setNoCaso(new BigDecimal(secMax));
                  erpCatConvertidor.setId(erpCatConvertidorId);
                  erpCatConvertidor.setActivo(parametros.get("ACTIVO").toString());
                  erpCatConvertidor.setDescripcion(parametros.get("DESCRIPCION").toString());
                  erpCatConvertidor.setNombre(parametros.get("NOMBRE").toString());
                  erpCatConvertidor.settPoliza(parametros.get("T_POLIZA").toString());
                  
         
          
           
           ErpCatConvertidorId result = erpCatConvertidorDao.save(erpCatConvertidor);
          
           
     //    
         
         if (result == null) {
                isSave = false;
                extjsFormResult.setMessage("Error al guardar los datos");
                extjsFormResult.setSuccess(isSave);
                return extjsFormResult.toString();
            } else {
                isSave = true;
                extjsFormResult.setMessage(""+secMax);
                extjsFormResult.setSuccess(isSave);
               // return rq;
                return extjsFormResult.toString();
     //}
         }
         }else{
           
                erpCatConvertidorId.setNoCaso(new BigDecimal(parametros.get("NO_CASO").toString()));
                  erpCatConvertidor.setId(erpCatConvertidorId);
                  erpCatConvertidor.setActivo(parametros.get("ACTIVO").toString());
                  erpCatConvertidor.setDescripcion(parametros.get("DESCRIPCION").toString());
                  erpCatConvertidor.setNombre(parametros.get("NOMBRE").toString());
                  erpCatConvertidor.settPoliza(parametros.get("T_POLIZA").toString());
                  
                boolean result = erpCatConvertidorDao.update(erpCatConvertidor);
          
           
     //    
         
                        if (result == false) {
                               isSave = false;
                               extjsFormResult.setMessage("Error al guardar los datos");
                               extjsFormResult.setSuccess(isSave);
                               return extjsFormResult.toString();
                           } else {
                               isSave = true;
                               extjsFormResult.setMessage(""+parametros.get("NO_CASO").toString());
                               extjsFormResult.setSuccess(isSave);
                              // return rq;
                               return extjsFormResult.toString();
                    //}
                        }
           
           
           
           }

        } catch (Exception e) {
            e.printStackTrace();
//            isSave = false;
        }




        extjsFormResult.setSuccess(isSave);
//
        return extjsFormResult.toString();

     
     
     }
      
        @RequestMapping(value = "/poliza/del", method = RequestMethod.POST)
    public @ResponseBody
    ResponseQuery deleteAction(String data,@RequestParam("compania") String compania,@RequestParam("origen") String origen,@RequestParam("idconcgasto") String idconcgasto,@RequestParam("caso") String caso,WebRequest webRequest, Model model) {

       System.out.println("Controlador delete");
//      
//        Map parametros = processDao.paramToMap(webRequest.getParameterMap());
//        
//        System.out.println("compania"+compania);
//        System.out.println("fecha"+origen);
//        System.out.println("tipoPoliza"+idconcgasto);
//        System.out.println("numero"+caso);
             
        //Map parametros = processDao.paramToMap(webRequest.getParameterMap());
       
        
        ResponseQuery rq = new ResponseQuery();
        
           
        
            ErpCatConvertidor erpCatConvertidor = new ErpCatConvertidor();
            ErpCatConvertidorId erpCatConvertidorId = new ErpCatConvertidorId();
            
            ErpDetConvertidor erpDetConvertidor = new ErpDetConvertidor();
            ErpDetConvertidorId erpDetConvertidorId = new ErpDetConvertidorId();
            
            erpCatConvertidorId.setCompania(compania);
            erpCatConvertidorId.setIdconcgasto(idconcgasto);
            erpCatConvertidorId.setNoCaso(new BigDecimal(caso));
            erpCatConvertidorId.setOrigen(origen);
            erpCatConvertidor.setId(erpCatConvertidorId);
            
            erpDetConvertidorId.setCompania(compania);
            erpDetConvertidorId.setIdconcgasto(idconcgasto);
            erpDetConvertidorId.setNoCaso(new BigDecimal(caso));
            erpDetConvertidorId.setOrigen(origen);
            erpDetConvertidor.setId(erpDetConvertidorId);
            
            if (model.asMap().get("compania") == null) {
               
                
                 rq.setSuccess(false);
                rq.setData(null);
                rq.setMsg("La sesion no es valida.");
            
               return rq;
            }
            
       try {
        
            boolean result = erpCatConvertidorDao.delete(erpCatConvertidor);
            boolean result2 = erpDetConvertidorDao.delete(compania,idconcgasto,origen,new BigDecimal(caso));

           if (result == true & result2 == true) {

                // lista.add(curso);
                rq.setSuccess(true);
                rq.setData(null);
                rq.setMsg("Borrado Correctamente");
            } else {
                rq.setSuccess(false);
                rq.setData(null);
                rq.setMsg("Error al Borrar");
           }
  } catch (Exception e) {
            e.printStackTrace();
        }

        return rq;
    }
    
      @RequestMapping(value = "/copi", method = RequestMethod.POST)
    public @ResponseBody
    String CopiarCampos(String data, WebRequest webRequest, Model model) {  
         
         
          Map parametros = processDao.paramToMap(webRequest.getParameterMap());
          
//          System.out.println("idConGastoCopi: "+parametros.get("IDCONCGASTO").toString());
          
         ExtJSFormResult extjsFormResult = new ExtJSFormResult();
         ResponseQuery rq = new ResponseQuery();
                boolean isSave = true;
                
            if (model.asMap().get("compania") == null) {
                extjsFormResult.setMessage("La sesion no es valida.");
                extjsFormResult.setSuccess(false);
            
               return extjsFormResult.toString();
        }
        try{
                ErpCatConvertidorId erpCatConvertidorId = new ErpCatConvertidorId();
                ErpCatConvertidor erpCatConvertidor = new ErpCatConvertidor();
                erpCatConvertidorId.setCompania(parametros.get("COMPANIA").toString());
                erpCatConvertidorId.setIdconcgasto(parametros.get("IDCONCGASTO").toString());
                erpCatConvertidorId.setOrigen(parametros.get("ORIGEN").toString());
                //erpCatConvertidorId.setNoCaso(new BigDecimal(1));
                
             
                
          // List<ErpCatConvertidor> convertidor = erpCatConvertidorDao.getConvertidor(parametros.get("COMPANIA").toString(), parametros.get("IDCONCGASTO").toString(), parametros.get("NO_CASO").toString(), parametros.get("ORIGEN").toString());
        
                
                
          int secMax = erpCatConvertidorDao.getMaxIdConvertidor(erpCatConvertidorId);
               
          
//          System.out.println("secuencia"+secMax);
          
                  erpCatConvertidorId.setNoCaso(new BigDecimal(secMax));
                  erpCatConvertidor.setId(erpCatConvertidorId);
                  erpCatConvertidor.setActivo(parametros.get("ACTIVO").toString());
                  erpCatConvertidor.setDescripcion(parametros.get("DESCRIPCION").toString());
                  erpCatConvertidor.setNombre("Copia "+ secMax);
                  erpCatConvertidor.settPoliza(parametros.get("T_POLIZA").toString());
                  
         
          
           
           ErpCatConvertidorId result = erpCatConvertidorDao.save(erpCatConvertidor);
          
           
     //    
         
         if (result == null) {
                isSave = false;
                extjsFormResult.setMessage("Error al guardar los datos");
                extjsFormResult.setSuccess(isSave);
                return extjsFormResult.toString();
            } else {
                isSave = true;
                extjsFormResult.setMessage(""+secMax);
                extjsFormResult.setSuccess(isSave);
               // return rq;
                return extjsFormResult.toString();
     //}
         }
        

        } catch (Exception e) {
            e.printStackTrace();
//            isSave = false;
        }




        extjsFormResult.setSuccess(isSave);
//
        return extjsFormResult.toString();

     
     
     }
    public ProcessDao getProcessDao() {
        return processDao;
    }

    public void setProcessDao(ProcessDao processDao) {
        this.processDao = processDao;
    }

    public ErpCatConvertidorDao getErpCatConvertidorDao() {
        return erpCatConvertidorDao;
    }

    public void setErpCatConvertidorDao(ErpCatConvertidorDao erpCatConvertidorDao) {
        this.erpCatConvertidorDao = erpCatConvertidorDao;
    }

    public void setErpDetConvertidorDao(ErpDetConvertidorDao erpDetConvertidorDao) {
        this.erpDetConvertidorDao = erpDetConvertidorDao;
    }
    
    
    
}
