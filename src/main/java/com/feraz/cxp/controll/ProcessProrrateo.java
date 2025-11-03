/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.feraz.cxp.controll;

/**
 *
 * @author vavi
 */
import com.fasterxml.jackson.databind.ObjectMapper;
import com.feraz.cxp.dao.ErpDetProrrateoXConceptoDao;
import com.feraz.cxp.dao.ErpDetProrrateoXNegocioDao;
import com.feraz.cxp.dto.DetProrrateoDTO;
import com.feraz.cxp.dto.DetProrrateoNegDto;
import com.feraz.cxp.model.ErpDetProrrateoXConcepto;
import com.feraz.cxp.model.ErpDetProrrateoXConceptoId;
import com.feraz.cxp.model.ErpDetProrrateoXNegocio;
import com.feraz.cxp.model.ErpDetProrrateoXNegocioId;

import com.feraz.facturas.webcontrolfe.dto.FileInfo;

import com.feraz.polizas3.model.FileUploadBean;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import org.jamh.data.process.ProcessDao;
import org.jamh.wf.json.model.ResponseQuery;
import org.jamh.wf.process.Querys;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.multipart.MultipartFile;


@Controller
@RequestMapping("/Prorrateo")
@SessionAttributes({"compania", "usuario"})
public class ProcessProrrateo {
    
    @Autowired
    private ErpDetProrrateoXConceptoDao erpDetProrrateoXConceptoDao;
    
    @Autowired
    private ErpDetProrrateoXNegocioDao erpDetProrrateoXNegocioDao;
    
    
    
    
     @RequestMapping(value = "/saveDetProrrateo", method = RequestMethod.POST)
    public @ResponseBody
    ResponseQuery saveProrrateo( WebRequest webRequest, Model model) {

        ResponseQuery rq = new ResponseQuery();
        String compania = model.asMap().get("compania").toString();       
        String usuario = model.asMap().get("usuario").toString();         
        
           String numero = webRequest.getParameter("NUMERO").toString();
           String compania2 = webRequest.getParameter("COMPANIA").toString();
           String sec = webRequest.getParameter("SEC").toString();
           String ctoCto = webRequest.getParameter("CTO_CTO").toString();
           String porcentaje = webRequest.getParameter("PORCENTAJE").toString();
           String importe = webRequest.getParameter("IMPORTE").toString();
           String operacion = webRequest.getParameter("OPERACION").toString();
           String totalConcepto = webRequest.getParameter("TOTAL_CONCEPTO").toString();
           String tipoGasto = webRequest.getParameter("cboCxpTipoGastoProrr").toString();
           
          
               
                                                          
        try {
            
          
            ErpDetProrrateoXConcepto pro = new ErpDetProrrateoXConcepto();
            ErpDetProrrateoXConceptoId proId = new ErpDetProrrateoXConceptoId();
            
            
            if (operacion.equalsIgnoreCase("I")){
            
           
            proId.setCompania(compania);
            proId.setNumero(Integer.parseInt(numero));
            proId.setSec(Integer.parseInt(sec));
            proId.setCtoCto(ctoCto);
           
            pro.setId(proId);
            pro.setImporteProrrateo(new BigDecimal(importe));
            pro.setPorcentaje(porcentaje);
            pro.setTotalConcepto(new BigDecimal(totalConcepto));
            pro.setTipoGasto(Integer.parseInt(tipoGasto));
           
           
            ErpDetProrrateoXConceptoId result = erpDetProrrateoXConceptoDao.save(pro);
            
            if(result == null){
                
                rq.setSuccess(false);
                rq.setMsg("Error al guardar Datos");
                
            }else{
                
                rq.setSuccess(true);
                rq.setMsg("Guardado Correctamente");
            
            }
            
            }else{
            
                    proId.setCompania(compania);
                    proId.setNumero(Integer.parseInt(numero));
                    proId.setSec(Integer.parseInt(sec));
                    proId.setCtoCto(ctoCto);

                    pro.setId(proId);
                    pro.setImporteProrrateo(new BigDecimal(importe));
                    pro.setPorcentaje(porcentaje);
                    pro.setTotalConcepto(new BigDecimal(totalConcepto));
                    pro.setTipoGasto(Integer.parseInt(tipoGasto));


            
               boolean result = erpDetProrrateoXConceptoDao.update(pro);
            
            if(result == false){
                
                rq.setSuccess(false);
                rq.setMsg("Error al guardar Datos");
                
            }else{
                
                rq.setSuccess(true);
                rq.setMsg("Guardado Correctamente");
            
            }
            
            
            
            }
            
             
        } catch (Exception e) {
             rq.setSuccess(false);
            rq.setMsg("Error al guardar");
            e.printStackTrace();
            return rq;
        }

        return rq;

    }
    
    
     @RequestMapping(value = "/saveDetProrrateoNeg", method = RequestMethod.POST)
    public @ResponseBody
    ResponseQuery saveProrrateoNeg( WebRequest webRequest, Model model) {

        ResponseQuery rq = new ResponseQuery();
        String compania = model.asMap().get("compania").toString();       
        String usuario = model.asMap().get("usuario").toString();         
        
           String numero = webRequest.getParameter("NUMERO").toString();
           String compania2 = webRequest.getParameter("COMPANIA").toString();
           String sec = webRequest.getParameter("SEC").toString();
           String ctoCto = webRequest.getParameter("CTO_CTO").toString();
           String porcentaje = webRequest.getParameter("PORCENTAJE").toString();
           String importe = webRequest.getParameter("IMPORTE").toString();
           String operacion = webRequest.getParameter("OPERACION").toString();
           String totalConcepto = webRequest.getParameter("TOTAL_CONCEPTO").toString();
           String tipoNegocio = webRequest.getParameter("cboCxpTipoNegocioProrrNeg").toString();
           String tipo = webRequest.getParameter("TIPO").toString();
           String cuentaContable = webRequest.getParameter("CUENTA_CONTABLE").toString();
           String cuentaComplementaria = webRequest.getParameter("CUENTA_COMPLEMENTARIA").toString();
           String idProv = webRequest.getParameter("ID_PROV").toString();
           String idServ = webRequest.getParameter("ID_SERVICIO").toString();
           
           String idConcepto = webRequest.getParameter("ID_CONCEPTO").toString();
           String tipoProrr = webRequest.getParameter("ProrrPorCuenta").toString();
           
           
                   
                   
                           
          
               System.out.println("sec: "+sec);
                                                          
        try {
            
          
            ErpDetProrrateoXNegocio pro = new ErpDetProrrateoXNegocio();
            ErpDetProrrateoXNegocioId proId = new ErpDetProrrateoXNegocioId();
            
            
            if (operacion.equalsIgnoreCase("I")){
            
           
            proId.setCompania(compania);
            proId.setNumero(Integer.parseInt(numero));
            proId.setSec(Integer.parseInt(sec));
            proId.setCtoCto(ctoCto);
            proId.setTipoNegocio(tipoNegocio);
            proId.setTipo(tipo);
            if(tipoProrr.equalsIgnoreCase("C")){
            proId.setIdFamilia(Integer.parseInt(idConcepto));
            }
            if(tipoProrr.equalsIgnoreCase("P")){
            proId.setIdFamilia(Integer.parseInt(idServ));
            }
            pro.setId(proId);
            pro.setImporteProrrateo(new BigDecimal(importe));
            pro.setPorcentaje(porcentaje);
            pro.setTotalConcepto(new BigDecimal(totalConcepto));
            pro.setCuentaContable(cuentaContable);
            pro.setCuentaComplementaria(cuentaComplementaria);
            pro.setTipoCta(tipoProrr);
            
            
            
            pro.setIdProveedor(idProv);
           
           
            ErpDetProrrateoXNegocioId result = erpDetProrrateoXNegocioDao.save(pro);
            
            if(result == null){
                
                rq.setSuccess(false);
                rq.setMsg("Error al guardar Datos");
                
            }else{
                
                rq.setSuccess(true);
                rq.setMsg("Guardado Correctamente");
            
            }
            
            }else{
            
                    proId.setCompania(compania);
                    proId.setNumero(Integer.parseInt(numero));
                    proId.setSec(Integer.parseInt(sec));
                    proId.setCtoCto(ctoCto);
                    proId.setTipoNegocio(tipoNegocio);
                    
                    if(tipoProrr.equalsIgnoreCase("C")){
                    proId.setIdFamilia(Integer.parseInt(idConcepto));
                    }
                    if(tipoProrr.equalsIgnoreCase("P")){
                    proId.setIdFamilia(Integer.parseInt(idServ));
                    }
                    proId.setTipo(tipo);
                    pro.setId(proId);
                    pro.setImporteProrrateo(new BigDecimal(importe));
                    pro.setPorcentaje(porcentaje);
                    pro.setTotalConcepto(new BigDecimal(totalConcepto));
                    pro.setCuentaContable(cuentaContable);
                    pro.setCuentaComplementaria(cuentaComplementaria);
                    pro.setIdProveedor(idProv);
                    pro.setTipoCta(tipoProrr);
                    

            
               boolean result = erpDetProrrateoXNegocioDao.update(pro);
            
            if(result == false){
                
                rq.setSuccess(false);
                rq.setMsg("Error al guardar Datos");
                
            }else{
                
                rq.setSuccess(true);
                rq.setMsg("Guardado Correctamente");
            
            }
            
            
            
            }
            
             
        } catch (Exception e) {
             rq.setSuccess(false);
            rq.setMsg("Error al guardar");
            e.printStackTrace();
            return rq;
        }

        return rq;

    }
    
                 @RequestMapping(value = "/deleteDetProrrateoNeg", method = RequestMethod.POST)
    public @ResponseBody
    ResponseQuery deleteDetProrrateoNeg( String data, WebRequest webRequest, Model model) throws IOException {

        ResponseQuery rq = new ResponseQuery();
   //     System.out.println("data factura cancelacion:"+data);
        
         int index = data.indexOf("[");
        if (index == -1) {
            data = "[" + data + "]";
        }


            String compania = model.asMap().get("compania").toString();
            String usuario = model.asMap().get("usuario").toString();
            ObjectMapper mapper = new ObjectMapper();
          
                   
        try{
            List<DetProrrateoNegDto> lista = mapper.readValue(data,
                    mapper.getTypeFactory().constructCollectionType(List.class,
                            DetProrrateoNegDto.class));

            if (lista.isEmpty()) {
                rq.setSuccess(false);
                rq.setData(null);
                rq.setMsg("Error no existen datos que guardar");
                return rq;
            }

  
            Iterator<DetProrrateoNegDto> it = lista.iterator();
           ErpDetProrrateoXNegocio pro = new ErpDetProrrateoXNegocio();
           ErpDetProrrateoXNegocioId provId = new ErpDetProrrateoXNegocioId();
          
          
            int banderaGe = 0 ;
            while (it.hasNext()) {
//                 System.out.println("-------------------------------------------------------------------");
                 DetProrrateoNegDto ss = it.next();
                 
                 
                 provId.setCompania(compania);
                 provId.setCtoCto(ss.ctoCto);
                 provId.setNumero(Integer.parseInt(ss.numero));
                 provId.setSec(Integer.parseInt(ss.sec));
                 provId.setTipo(ss.tipo);
                 provId.setTipoNegocio(ss.tipoNegocio);
                 provId.setIdFamilia(Integer.parseInt(ss.idFamilia));
                 pro.setId(provId);
                 
                 erpDetProrrateoXNegocioDao.delete(pro);
               
                  banderaGe = 1;
            }
            
          
            
            if (banderaGe == 1){
            
                
                    rq.setSuccess(true);
                    rq.setData(null);
                    rq.setMsg("Registros eliminados correctamente");
              
                
            }else{
                
                rq.setSuccess(true);
                rq.setData(null);
                rq.setMsg("Error al eliminar");
                
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
    
    
    

    
             @RequestMapping(value = "/deleteDetProrrateo", method = RequestMethod.POST)
    public @ResponseBody
    ResponseQuery deleteDetProrrateo( String data, WebRequest webRequest, Model model) throws IOException {

        ResponseQuery rq = new ResponseQuery();
   //     System.out.println("data factura cancelacion:"+data);
        
         int index = data.indexOf("[");
        if (index == -1) {
            data = "[" + data + "]";
        }


            String compania = model.asMap().get("compania").toString();
            String usuario = model.asMap().get("usuario").toString();
            ObjectMapper mapper = new ObjectMapper();
          
                   
        try{
            List<DetProrrateoDTO> lista = mapper.readValue(data,
                    mapper.getTypeFactory().constructCollectionType(List.class,
                            DetProrrateoDTO.class));

            if (lista.isEmpty()) {
                rq.setSuccess(false);
                rq.setData(null);
                rq.setMsg("Error no existen datos que guardar");
                return rq;
            }

  
            Iterator<DetProrrateoDTO> it = lista.iterator();
           ErpDetProrrateoXConcepto pro = new ErpDetProrrateoXConcepto();
           ErpDetProrrateoXConceptoId provId = new ErpDetProrrateoXConceptoId();
          
          
            int banderaGe = 0 ;
            while (it.hasNext()) {
//                 System.out.println("-------------------------------------------------------------------");
                 DetProrrateoDTO ss = it.next();
                 
                 
                 provId.setCompania(compania);
                 provId.setCtoCto(ss.ctoCto);
                 provId.setNumero(Integer.parseInt(ss.numero));
                 provId.setSec(Integer.parseInt(ss.sec));
                 
                 pro.setId(provId);
                 
                 erpDetProrrateoXConceptoDao.delete(pro);
               
                  banderaGe = 1;
            }
            
          
            
            if (banderaGe == 1){
            
                
                    rq.setSuccess(true);
                    rq.setData(null);
                    rq.setMsg("Registros eliminados correctamente");
              
                
            }else{
                
                rq.setSuccess(true);
                rq.setData(null);
                rq.setMsg("Error al eliminar");
                
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
    
    
    
}
