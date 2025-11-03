/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.feraz.almacen.controller;

import com.feraz.almacen.dao.ErpProductosSolicitadosDao;
import java.text.ParseException;
import org.jamh.data.process.ProcessDao;
import org.jamh.wf.json.model.ResponseQuery2;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.context.request.WebRequest;

/**
 *
 * @author FERAZ-14
 */

@Controller
@RequestMapping("/almacenProcess")
@SessionAttributes({"compania", "usuario"})
public class AlmacenController {
    
     private ProcessDao processDao;
    private ErpProductosSolicitadosDao erpProductosSolicitadosDao;
    
      
        @RequestMapping(value = "/insert", method = RequestMethod.POST)
     public @ResponseBody
    ResponseQuery2 insertAction(WebRequest webRequest, Model model) throws ParseException {

        ResponseQuery2 rq = new ResponseQuery2();
//        SimpleDateFormat formatoDelTexto = new SimpleDateFormat("dd/MM/yyyy");
//        SimpleDateFormat formatFecha = new SimpleDateFormat("dd/MM/yyyy");
       
//
        if (model.asMap().get("compania") == null) {
            rq.setSuccess(false);
            rq.setData(null);
            rq.setMsg("La sesion no es valida.");
            return rq;
        }
        String compania = model.asMap().get("compania").toString();
        String usuario = model.asMap().get("usuario").toString();
        
        String idProducto = webRequest.getParameter("idProdcutoDetAlmacen").toString();
        
        try{
        
                System.out.println("idProducto: "+idProducto);
            
        } catch (Exception e) {
            e.printStackTrace();
        }

        return rq;

    }

    public void setProcessDao(ProcessDao processDao) {
        this.processDao = processDao;
    }

    public void setErpProductosSolicitadosDao(ErpProductosSolicitadosDao erpProductosSolicitadosDao) {
        this.erpProductosSolicitadosDao = erpProductosSolicitadosDao;
    }
    
    
    
    
}
