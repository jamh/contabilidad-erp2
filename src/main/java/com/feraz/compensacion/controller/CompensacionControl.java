/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.feraz.compensacion.controller;


import com.feraz.compensacion.dao.ErpCompensacionesDao;
import com.feraz.compensacion.dao.ErpCompensacionesDetDao;
import com.feraz.compensacion.model.ErpCompensaciones;
import com.feraz.compensacion.model.ErpCompensacionesId;
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
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.context.request.WebRequest;

/**
 *
 * @author vavi
 */

@Controller
@RequestMapping(value = "/Compensacion")
@SessionAttributes({"compania", "usuario"})
public class CompensacionControl {
    
    
    private ProcessDao processDao;
    private ErpCompensacionesDao erpCompensacionesDao;
    private ErpCompensacionesDetDao erpCompensacionesDetDao;
    
      @RequestMapping(value = "/save", method = RequestMethod.POST)
    public @ResponseBody
    String updateCampos(String data, WebRequest webRequest, Model model) {  
         
         
          Map parametros = processDao.paramToMap(webRequest.getParameterMap());
          
          
          String compania = parametros.get("COMPANIA").toString();
          String id = parametros.get("ID").toString();
          String descripcion = parametros.get("DESCRIPCION").toString();
          String montoIngreso = parametros.get("MONTO_INGRESO").toString();
          String parametro = parametros.get("PARAMETRO").toString();
          String estatus = parametros.get("ESTATUS").toString();        
          
                  
                  
                          
                  
         ExtJSFormResult extjsFormResult = new ExtJSFormResult();
         ResponseQuery rq = new ResponseQuery();
                boolean isSave = true;
               
                if (model.asMap().get("compania") == null) {
                extjsFormResult.setMessage("La sesion no es valida.");
                extjsFormResult.setSuccess(false);
            
               return extjsFormResult.toString();
        }
                
        try{
                ErpCompensacionesId erpCompensacionesId = new ErpCompensacionesId();
                ErpCompensaciones erpCompensaciones = new ErpCompensaciones();
               
                
                if (parametro.equalsIgnoreCase("I")){
                
                
                    erpCompensacionesId.setCompania(compania);
                    int idSec = erpCompensacionesDao.getMaxIdCompensacion(erpCompensacionesId);
                    erpCompensacionesId.setId(idSec);
                    erpCompensaciones.setId(erpCompensacionesId);
                    erpCompensaciones.setDescripcion(descripcion);
                    erpCompensaciones.setMontoIngreso(new BigDecimal(montoIngreso));
                    erpCompensaciones.setEstatus("GE");
                    
                    ErpCompensacionesId result = erpCompensacionesDao.save(erpCompensaciones);
                    
                        if (result == null) {
                                  isSave = false;
                                  extjsFormResult.setMessage("Error al guardar los datos");
                                  extjsFormResult.setSuccess(isSave);
                                  return extjsFormResult.toString();
                              } else {
                                  isSave = true;
                                  extjsFormResult.setMessage(""+idSec);
                                  extjsFormResult.setSuccess(isSave);
                                  return extjsFormResult.toString();
                       }
         
                
                
                }
                
                if (parametro.equalsIgnoreCase("U")){
                
                
                    erpCompensacionesId.setCompania(compania);
                    erpCompensacionesId.setId(Integer.parseInt(id));
                    erpCompensaciones.setId(erpCompensacionesId);
                    erpCompensaciones.setDescripcion(descripcion);
                    erpCompensaciones.setMontoIngreso(new BigDecimal(montoIngreso));
                    erpCompensaciones.setEstatus(estatus);
                    
                    boolean result = erpCompensacionesDao.update(erpCompensaciones);
                    
                    if (result == false) {
                                  isSave = false;
                                  extjsFormResult.setMessage("Error al guardar los datos");
                                  extjsFormResult.setSuccess(isSave);
                                  return extjsFormResult.toString();
                              } else {
                                  isSave = true;
                                  extjsFormResult.setMessage(""+id);
                                  extjsFormResult.setSuccess(isSave);
                                  return extjsFormResult.toString();
                       }
                
                
                }
             
          
     //    
         
       
      

        } catch (Exception e) {
            e.printStackTrace();
//            isSave = false;
        }




        extjsFormResult.setSuccess(isSave);
//
        return extjsFormResult.toString();

     
     
     }

    public void setProcessDao(ProcessDao processDao) {
        this.processDao = processDao;
    }

    public void setErpCompensacionesDao(ErpCompensacionesDao erpCompensacionesDao) {
        this.erpCompensacionesDao = erpCompensacionesDao;
    }

    public void setErpCompensacionesDetDao(ErpCompensacionesDetDao erpCompensacionesDetDao) {
        this.erpCompensacionesDetDao = erpCompensacionesDetDao;
    }
    
    
    
}
