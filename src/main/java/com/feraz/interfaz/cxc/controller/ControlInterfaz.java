/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.feraz.interfaz.cxc.controller;

/**
 *
 * @author vavi
 */
import com.fasterxml.jackson.databind.ObjectMapper;
import com.feraz.interfaz.cxc.dao.ErpCatBancoDao;
import com.feraz.interfaz.cxc.dao.ErpCuentasCxcImplDao;
import com.feraz.interfaz.cxc.dto.BancoDTO;
import com.feraz.interfaz.cxc.dto.CuentasCxcImplDTO;
import com.feraz.interfaz.cxc.model.ErpCatBanco;
import com.feraz.interfaz.cxc.model.ErpCatBancoId;
import com.feraz.interfaz.cxc.model.ErpCuentasCxcImpl;
import com.feraz.interfaz.cxc.model.ErpCuentasCxcImplId;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import org.jamh.data.process.ProcessDao;
import org.jamh.wf.json.model.ResponseQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.context.request.WebRequest;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.text.DateFormat;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import org.jamh.wf.process.Querys;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
/**
 *
 * @author Feraz3
 */
@Controller
@RequestMapping("/ControlInterfaz")
@SessionAttributes({"compania", "usuario"})
public class ControlInterfaz {
    
    
     private ErpCuentasCxcImplDao erpCuentasCxcImplDao;
     private ErpCatBancoDao erpCatBancoDao;
    
      @RequestMapping(value = "/updateCuentas", method = RequestMethod.POST)
    public @ResponseBody
    ResponseQuery saveCuentasPrev( String data, WebRequest webRequest, Model model) throws IOException {

        ResponseQuery rq = new ResponseQuery();
        //System.out.println("data Pagos creacion:"+data);
        
         int index = data.indexOf("[");
        if (index == -1) {
            data = "[" + data + "]";
        }


            String compania = model.asMap().get("compania").toString();
            String usuario = model.asMap().get("usuario").toString();
            ObjectMapper mapper = new ObjectMapper();
          
        try{
            List<CuentasCxcImplDTO> lista = mapper.readValue(data,
                    mapper.getTypeFactory().constructCollectionType(List.class,
                            CuentasCxcImplDTO.class));

            if (lista.isEmpty()) {
                rq.setSuccess(false);
                rq.setData(null);
                rq.setMsg("Error no existen datos que guardar");
                return rq;
            }     
            
            Iterator<CuentasCxcImplDTO> it = lista.iterator();
           
           
            ErpCuentasCxcImpl impl = new ErpCuentasCxcImpl();
            ErpCuentasCxcImplId implId = new ErpCuentasCxcImplId();
            while (it.hasNext()) {
//                 System.out.println("-------------------------------------------------------------------");
                 CuentasCxcImplDTO ss = it.next();
               //  if (ss.estatusCxp == null){
               
               
                    implId.setCompania(compania);
                    implId.setClave(ss.clave);
                    impl.setId(implId);
                    impl.setNombre(ss.nombre);
                    impl.setCuenta(ss.cuenta);
                    
                    
                    
                    
                    erpCuentasCxcImplDao.update(impl);
            
//             

            }
            

            
      //     if (result2 == true){ 
             rq.setSuccess(true);
             rq.setData(null);
             rq.setMsg("Cuentas Actualizadas");
      //     }
            
        }catch(Exception e){
            rq.setSuccess(false);
            rq.setData(null);
            rq.setMsg("Error al guardar");
           return rq;
        }
        
        return rq;
    }
    
    
    @RequestMapping(value = "/updateBancos", method = RequestMethod.POST)
    public @ResponseBody
    ResponseQuery saveBancosPrev( String data, WebRequest webRequest, Model model) throws IOException {

        ResponseQuery rq = new ResponseQuery();
        //System.out.println("data Pagos creacion:"+data);
        
         int index = data.indexOf("[");
        if (index == -1) {
            data = "[" + data + "]";
        }


            String compania = model.asMap().get("compania").toString();
            String usuario = model.asMap().get("usuario").toString();
            ObjectMapper mapper = new ObjectMapper();
          
        try{
            List<BancoDTO> lista = mapper.readValue(data,
                    mapper.getTypeFactory().constructCollectionType(List.class,
                            BancoDTO.class));

            if (lista.isEmpty()) {
                rq.setSuccess(false);
                rq.setData(null);
                rq.setMsg("Error no existen datos que guardar");
                return rq;
            }     
            
            Iterator<BancoDTO> it = lista.iterator();
           
           
            ErpCatBanco impl = new ErpCatBanco();
            ErpCatBancoId implId = new ErpCatBancoId();
            while (it.hasNext()) {
//                 System.out.println("-------------------------------------------------------------------");
                 BancoDTO ss = it.next();
               //  if (ss.estatusCxp == null){
               
               
                    implId.setCompania(compania);
                    implId.setBanco(ss.banco);
                    impl.setId(implId);
                    impl.setCuenta(ss.cuenta);
                    impl.setCuentaComplementaria(ss.cuentaComplementaria);
                    
                    
                    
                    
                    erpCatBancoDao.updateCuentasContaBan(impl);
            
//             

            }
            

            
      //     if (result2 == true){ 
             rq.setSuccess(true);
             rq.setData(null);
             rq.setMsg("Cuentas Actualizadas");
      //     }
            
        }catch(Exception e){
            rq.setSuccess(false);
            rq.setData(null);
            rq.setMsg("Error al guardar");
           return rq;
        }
        
        return rq;
    }

    public void setErpCuentasCxcImplDao(ErpCuentasCxcImplDao erpCuentasCxcImplDao) {
        this.erpCuentasCxcImplDao = erpCuentasCxcImplDao;
    }

    public void setErpCatBancoDao(ErpCatBancoDao erpCatBancoDao) {
        this.erpCatBancoDao = erpCatBancoDao;
    }
    
    
    
    
}
