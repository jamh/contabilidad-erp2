/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.feraz.viaticos.controller;

/**
 *
 * @author vavi
 */

import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonToken;
import com.fasterxml.jackson.databind.ObjectMapper;

import com.feraz.cxp.dao.ErpCpOtrasDao;

import java.text.SimpleDateFormat;
import org.jamh.data.process.ProcessDao;
import org.jamh.wf.json.model.ResponseQuery;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.context.request.WebRequest;

import com.feraz.cxp.dto.ViaticosDTO;
import com.feraz.cxp.model.ErpCpOtras;
import com.feraz.cxp.model.ErpCpOtrasId;

import com.feraz.polizas3.model.FileUploadBean;
import com.feraz.viaticos.dao.ErpFoliosPagoViaticosDao;
import com.feraz.viaticos.dao.ViComisionesDao;
import com.feraz.viaticos.dto.ComprobacionViaDTO;
import com.feraz.viaticos.dto.EnvioTesViaDTO;
import com.feraz.viaticos.model.ErpFoliosPagoViaticos;
import com.feraz.viaticos.model.ErpFoliosPagoViaticosId;
import com.feraz.viaticos.model.ViComisiones;
import com.feraz.viaticos.model.ViComisionesId;


import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.math.BigDecimal;

import java.text.DateFormat;

import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import org.jamh.wf.process.Querys;
import org.springframework.web.bind.annotation.RequestParam;

/**
 *
 * @author Feraz3
 */
@Controller
@RequestMapping("/Viaticos")
@SessionAttributes({"compania", "usuario"})
public class ViaticosController {
    
    
    private ProcessDao processDao;
    private ErpFoliosPagoViaticosDao erpFoliosPagoViaticosDao;
    private ErpCpOtrasDao erpCpOtrasDao;
    private ViComisionesDao viComisionesDao;
    
     @RequestMapping(value = "/saveViaticos", method = RequestMethod.POST)
    public @ResponseBody
    ResponseQuery saveViaticos( String data, WebRequest webRequest, Model model) throws IOException {

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
            List<ViaticosDTO> lista = mapper.readValue(data,
                    mapper.getTypeFactory().constructCollectionType(List.class,
                            ViaticosDTO.class));

            if (lista.isEmpty()) {
                rq.setSuccess(false);
                rq.setData(null);
                rq.setMsg("Error no existen datos que guardar");
                return rq;
            }            Iterator<ViaticosDTO> it = lista.iterator();
           ErpFoliosPagoViaticos folios = new ErpFoliosPagoViaticos();
           ErpFoliosPagoViaticosId foliosId = new ErpFoliosPagoViaticosId();
           
         
           foliosId.setCompania(compania);
           
           int geFolio = erpFoliosPagoViaticosDao.getMaxCxpFolios(foliosId);
           
           int banderaGe = 0;
           
           Querys que = new Querys();
            String store = que.getSQL("CONTA_ACTUALIZA_VIATICOS");
           
        //   System.out.println("GENRADOR DE FOLIO:"+geFolio);
           
           int folioPagos = geFolio;
          
            while (it.hasNext()) {
//                 System.out.println("-------------------------------------------------------------------");
                 ViaticosDTO ss = it.next();
               
                 foliosId.setFolio(geFolio);
                 foliosId.setComision(new BigDecimal(ss.numero));
                 folios.setId(foliosId);
                 folios.setUsuario(usuario);
                 folios.setEstatus("FG");
                 folios.setTipoPago("T");
                 
                 erpFoliosPagoViaticosDao.save(folios);
                 
//    
//                 
                 banderaGe = 1;
                   
                  Map paramViatico = new HashMap();
                    paramViatico.put("COMPANIA",compania);
                    paramViatico.put("COMISION",ss.numero);
                    paramViatico.put("TIPO","FG");
                    paramViatico.put("FOLIO_PAGO",geFolio);
            
                int val = processDao.execute(store, paramViatico);

                     if (val <= 0) {

                }
//            
//             

            }
            
            

            
      //     if (result2 == true){ 
             rq.setSuccess(true);
             rq.setData(null);
             rq.setMsg("Facturas enviadas con folio: "+geFolio);
      //     }
            
        }catch(Exception e){
            rq.setSuccess(false);
            rq.setData(null);
            rq.setMsg("Error al guardar");
           return rq;
        }
        
        return rq;
    }

         @RequestMapping(value = "/saveViaticosE", method = RequestMethod.POST)
    public @ResponseBody
    ResponseQuery saveViaticosE( String data, WebRequest webRequest, Model model) throws IOException {

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
            List<ViaticosDTO> lista = mapper.readValue(data,
                    mapper.getTypeFactory().constructCollectionType(List.class,
                            ViaticosDTO.class));

            if (lista.isEmpty()) {
                rq.setSuccess(false);
                rq.setData(null);
                rq.setMsg("Error no existen datos que guardar");
                return rq;
            }            Iterator<ViaticosDTO> it = lista.iterator();
           ErpFoliosPagoViaticos folios = new ErpFoliosPagoViaticos();
           ErpFoliosPagoViaticosId foliosId = new ErpFoliosPagoViaticosId();
           
         
           foliosId.setCompania(compania);
           
           int geFolio = erpFoliosPagoViaticosDao.getMaxCxpFolios(foliosId);
           
           int banderaGe = 0;
           
           Querys que = new Querys();
            String store = que.getSQL("CONTA_ACTUALIZA_VIATICOS");
           
        //   System.out.println("GENRADOR DE FOLIO:"+geFolio);
           
           int folioPagos = geFolio;
          
            while (it.hasNext()) {
//                 System.out.println("-------------------------------------------------------------------");
                 ViaticosDTO ss = it.next();
               
                 foliosId.setFolio(geFolio);
                 foliosId.setComision(new BigDecimal(ss.numero));
                 folios.setId(foliosId);
                 folios.setUsuario(usuario);
                 folios.setEstatus("FG");
                 folios.setTipoPago("E");
                 
                 erpFoliosPagoViaticosDao.save(folios);
                 
//    
//                 
                 banderaGe = 1;
                   
                  Map paramViatico = new HashMap();
                    paramViatico.put("COMPANIA",compania);
                    paramViatico.put("COMISION",ss.numero);
                    paramViatico.put("TIPO","FG");
                    paramViatico.put("FOLIO_PAGO",geFolio);
            
                int val = processDao.execute(store, paramViatico);

                     if (val <= 0) {

                }
//            
//             

            }
            
            

            
      //     if (result2 == true){ 
             rq.setSuccess(true);
             rq.setData(null);
             rq.setMsg("Facturas enviadas con folio: "+geFolio);
      //     }
            
        }catch(Exception e){
            rq.setSuccess(false);
            rq.setData(null);
            rq.setMsg("Error al guardar");
           return rq;
        }
        
        return rq;
    }
    
      @RequestMapping(value = "/updateViaticosE", method = RequestMethod.POST)
    public @ResponseBody
    ResponseQuery updateViaticosE( String data,@RequestParam("folio") String folio, WebRequest webRequest, Model model) throws IOException {
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
            List<ViaticosDTO> lista = mapper.readValue(data,
                    mapper.getTypeFactory().constructCollectionType(List.class,
                            ViaticosDTO.class));

            if (lista.isEmpty()) {
                rq.setSuccess(false);
                rq.setData(null);
                rq.setMsg("Error no existen datos que guardar");
                return rq;
            }            Iterator<ViaticosDTO> it = lista.iterator();
            
           ErpFoliosPagoViaticos folios = new ErpFoliosPagoViaticos();
           ErpFoliosPagoViaticosId foliosId = new ErpFoliosPagoViaticosId();
           
         
           foliosId.setCompania(compania);
           
           int geFolio = Integer.parseInt(folio);
           
           int banderaGe = 0;
           
           Querys que = new Querys();
            String store = que.getSQL("CONTA_ACTUALIZA_VIATICOS");
           
        //   System.out.println("GENRADOR DE FOLIO:"+geFolio);
           
           int folioPagos = geFolio;
          
            while (it.hasNext()) {
//                 System.out.println("-------------------------------------------------------------------");
                 ViaticosDTO ss = it.next();
               //  if (ss.estatusCxp == null){
                 
                // folioPagos = id;
                 
                   foliosId.setFolio(geFolio);
                 foliosId.setComision(new BigDecimal(ss.numero));
                 folios.setId(foliosId);
                 folios.setUsuario(usuario);
                 folios.setEstatus("FG");
                 folios.setTipoPago("E");
                 
                 erpFoliosPagoViaticosDao.save(folios);
                 
    
                 
                 banderaGe = 1;
                 
                   
                  Map paramViatico = new HashMap();
                    paramViatico.put("COMPANIA",compania);
                    paramViatico.put("COMISION",ss.numero);
                    paramViatico.put("TIPO","FG");
                    paramViatico.put("FOLIO_PAGO",geFolio);
            
                int val = processDao.execute(store, paramViatico);

                     if (val <= 0) {

                }
            
//             

            }
            
            

            
      //     if (result2 == true){ 
             rq.setSuccess(true);
             rq.setData(null);
             rq.setMsg("Facturas enviadas con folio: "+geFolio);
      //     }
            
        }catch(Exception e){
            rq.setSuccess(false);
            rq.setData(null);
            rq.setMsg("Error al guardar");
           return rq;
        }
        
        return rq;
    }
    
    
     @RequestMapping(value = "/updateViaticos", method = RequestMethod.POST)
    public @ResponseBody
    ResponseQuery updateViaticos( String data,@RequestParam("folio") String folio, WebRequest webRequest, Model model) throws IOException {
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
            List<ViaticosDTO> lista = mapper.readValue(data,
                    mapper.getTypeFactory().constructCollectionType(List.class,
                            ViaticosDTO.class));

            if (lista.isEmpty()) {
                rq.setSuccess(false);
                rq.setData(null);
                rq.setMsg("Error no existen datos que guardar");
                return rq;
            }            Iterator<ViaticosDTO> it = lista.iterator();
            
           ErpFoliosPagoViaticos folios = new ErpFoliosPagoViaticos();
           ErpFoliosPagoViaticosId foliosId = new ErpFoliosPagoViaticosId();
           
         
           foliosId.setCompania(compania);
           
           int geFolio = Integer.parseInt(folio);
           
           int banderaGe = 0;
           
           Querys que = new Querys();
            String store = que.getSQL("CONTA_ACTUALIZA_VIATICOS");
           
        //   System.out.println("GENRADOR DE FOLIO:"+geFolio);
           
           int folioPagos = geFolio;
          
            while (it.hasNext()) {
//                 System.out.println("-------------------------------------------------------------------");
                 ViaticosDTO ss = it.next();
               //  if (ss.estatusCxp == null){
                 
                // folioPagos = id;
                 
                   foliosId.setFolio(geFolio);
                 foliosId.setComision(new BigDecimal(ss.numero));
                 folios.setId(foliosId);
                 folios.setUsuario(usuario);
                 folios.setEstatus("FG");
                 folios.setTipoPago("T");
                 
                 erpFoliosPagoViaticosDao.save(folios);
                 
    
                 
                 banderaGe = 1;
                 
                   
                  Map paramViatico = new HashMap();
                    paramViatico.put("COMPANIA",compania);
                    paramViatico.put("COMISION",ss.numero);
                    paramViatico.put("TIPO","FG");
                    paramViatico.put("FOLIO_PAGO",geFolio);
            
                int val = processDao.execute(store, paramViatico);

                     if (val <= 0) {

                }
            
//             

            }
            
            

            
      //     if (result2 == true){ 
             rq.setSuccess(true);
             rq.setData(null);
             rq.setMsg("Facturas enviadas con folio: "+geFolio);
      //     }
            
        }catch(Exception e){
            rq.setSuccess(false);
            rq.setData(null);
            rq.setMsg("Error al guardar");
           return rq;
        }
        
        return rq;
    }
    
    
     @RequestMapping(value = "/eliminaFolioVia", method = RequestMethod.POST)
    public @ResponseBody
    ResponseQuery eliminaFolioVia( String data, WebRequest webRequest, Model model) throws IOException {

        ResponseQuery rq = new ResponseQuery();
        
         int index = data.indexOf("[");
        if (index == -1) {
            data = "[" + data + "]";
        }
         
      //  System.out.println("----------------------------------------------");
        
     //   System.out.println(data);

            String compania = model.asMap().get("compania").toString();
            String usuario = model.asMap().get("usuario").toString();
            ObjectMapper mapper = new ObjectMapper();
            
        try{
            List<EnvioTesViaDTO> lista = mapper.readValue(data,
                    mapper.getTypeFactory().constructCollectionType(List.class,
                            EnvioTesViaDTO.class));

            if (lista.isEmpty()) {
                rq.setSuccess(false);
                rq.setData(null);
                rq.setMsg("Error no existen datos que guardar");
                return rq;
            }

  
            Iterator<EnvioTesViaDTO> it = lista.iterator();
           ErpFoliosPagoViaticos folios = new ErpFoliosPagoViaticos();
           ErpFoliosPagoViaticosId foliosId = new ErpFoliosPagoViaticosId();
           
         
          
            String folioPagos = "";
            int banderaGe = 0 ;
            
            Querys que = new Querys();
            String store = que.getSQL("CONTA_ACTUALIZA_VIATICOS");
         
            
            while (it.hasNext()) {
              
                 EnvioTesViaDTO ss = it.next();
                 
                 foliosId.setCompania(compania);
                 foliosId.setComision(new BigDecimal(ss.comision));
                 foliosId.setFolio(Integer.parseInt(ss.folio));
                 folios.setId(foliosId);
                 
                 
                 erpFoliosPagoViaticosDao.delete(folios);
                 
                
                   
                  Map paramViatico = new HashMap();
                    paramViatico.put("COMPANIA",compania);
                    paramViatico.put("COMISION",ss.comision);
                    paramViatico.put("TIPO","CAN");
                    paramViatico.put("FOLIO_PAGO",ss.folio);
                    
                     int val = processDao.execute(store, paramViatico);

                     if (val <= 0) {

                }
                    
                 
                  folioPagos = ss.folio;
                  banderaGe = 1;
            }
            
          
            
            if (banderaGe == 1){
            
                
                    rq.setSuccess(true);
                    rq.setData(null);
                    rq.setMsg("Comisiones Eliminadas Correctamente del folio: "+folioPagos);
              
                
            }else{
                
                rq.setSuccess(true);
                rq.setData(null);
                rq.setMsg("No hay registros para enviar");
                
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
    
      @RequestMapping(value = "/enviaTesoreriaVia", method = RequestMethod.POST)
    public @ResponseBody
    ResponseQuery enviaTesoreriaVia( String data,String text, WebRequest webRequest, Model model) throws IOException {

        ResponseQuery rq = new ResponseQuery();
       System.out.println("text:"+text);
       
        String compania = model.asMap().get("compania").toString();
            String usuario = model.asMap().get("usuario").toString();
       
      
         int index = data.indexOf("[");
        if (index == -1) {
            data = "[" + data + "]";
        }


           
            ObjectMapper mapper = new ObjectMapper();
            
        try{
            List<EnvioTesViaDTO> lista = mapper.readValue(data,
                    mapper.getTypeFactory().constructCollectionType(List.class,
                            EnvioTesViaDTO.class));
            
            

            if (lista.isEmpty()) {
                rq.setSuccess(false);
                rq.setData(null);
                rq.setMsg("Error no existen datos que guardar");
                return rq;
            }

            
 
            Iterator<EnvioTesViaDTO> it = lista.iterator();
           ErpFoliosPagoViaticos folios = new ErpFoliosPagoViaticos();
           ErpFoliosPagoViaticosId foliosId = new ErpFoliosPagoViaticosId();
           
           
         
            String folioPagos = "";
            int banderaGe = 0 ;
            
            Querys que = new Querys();
            String store = que.getSQL("CONTA_ACTUALIZA_VIATICOS");
            
            Date d = new Date();
            
            while (it.hasNext()) {
//                 System.out.println("-------------------------------------------------------------------");
                 EnvioTesViaDTO ss = it.next();
                 
                 
                 
                 foliosId.setCompania(compania);
                 foliosId.setComision(new BigDecimal(ss.comision));
                 foliosId.setFolio(Integer.parseInt(ss.folio));
                 folios.setId(foliosId);
                 folios.setFechaAutorizacion(d);
                 folios.setUsuarioAutorizo(usuario);
                 
                 System.out.println("ss.tipoPago: "+ss.tipoPago);
                 
                 if(ss.tipoPago.equalsIgnoreCase("T")){
                     folios.setEstatus("ET");
                 }
                 if(ss.tipoPago.equalsIgnoreCase("E")){
                     folios.setEstatus("EC");
                 }
                 
                 erpFoliosPagoViaticosDao.actualizaEstatusFolio(folios);
              
                    
                  Map paramViatico = new HashMap();
                    paramViatico.put("COMPANIA",compania);
                    paramViatico.put("COMISION",ss.comision);
                    
                    if(ss.tipoPago.equalsIgnoreCase("T")){
                     paramViatico.put("TIPO","TES");
                 }
                 if(ss.tipoPago.equalsIgnoreCase("E")){
                     paramViatico.put("TIPO","EC");
                 }
                 
                    
                    
                    paramViatico.put("FOLIO_PAGO",ss.folio);
            
                int val = processDao.execute(store, paramViatico);

                     if (val <= 0) {

                }
                 
                 
                  folioPagos = ss.folio;
                  banderaGe = 1;
            }
            
            
            
            
                    rq.setSuccess(true);
                    rq.setData(null);
                    rq.setMsg("Enviado a pagar con folio: "+folioPagos);
               
       
            

        }catch(Exception e){
            
            e.printStackTrace();
            
            rq.setSuccess(false);
            rq.setData(null);
            rq.setMsg("Error al guardar");
           return rq;
        }
        
        return rq;
        
        
    }
    
    
       @RequestMapping(value = "/saveOtrosCXP", method = RequestMethod.POST)
    public @ResponseBody
    ResponseQuery saveCXPOtros( WebRequest webRequest, Model model,FileUploadBean uploadItemLineaCap) {

        ResponseQuery rq = new ResponseQuery();
        String compania = model.asMap().get("compania").toString();       
        String usuario = model.asMap().get("usuario").toString();         
         System.out.println("compania:"+compania);  
         
         System.out.println("txtTIPO_CAMBIO:"+webRequest.getParameter("TIPO_CAMBIO")); 
         
                  System.out.println("rfc:"+webRequest.getParameter("RFCEmisor")); 
                  
           String numero = webRequest.getParameter("NUMERO").toString();
           String rfcEmisor = webRequest.getParameter("RFCEmisor").toString();
           String tipoGasto = webRequest.getParameter("TIPO_GASTO").toString();
           String fecha = webRequest.getParameter("FECHA").toString();
           String moneda = webRequest.getParameter("MONEDA").toString();
           String beneficiario = webRequest.getParameter("BENEFICIARIO").toString();
           String cboBeneficiario = webRequest.getParameter("cboBENEFICIARIO").toString();
           String importe = webRequest.getParameter("IMPORTE").toString();
           String iva = webRequest.getParameter("IVA").toString();
           String otrosImpuestos = webRequest.getParameter("OTROS_IMPUESTOS").toString();
           String total = webRequest.getParameter("TOTAL").toString();
           String ctoCxp = webRequest.getParameter("CTO_CXP").toString();
           String tipoCambio = webRequest.getParameter("TIPO_CAMBIO").toString();
           String descripcion = webRequest.getParameter("DESCRIPCION_OTRAS").toString();
           String idProveedor = webRequest.getParameter("ID_PROVEEDOR").toString();
           
           String tipoGastoCXP = webRequest.getParameter("TIPO_GASTO_CXP").toString();
           
 
           
           String idServicio= webRequest.getParameter("ID_SERVICIO").toString();
           
           String pais= webRequest.getParameter("PAIS_CXP_FORM").toString();
           String tipoNeg= webRequest.getParameter("TIPO_NEG_CXP").toString();
           
           
                   
          
                System.out.println("beneficiario"+beneficiario);
                System.out.println("cboBeneficiario"+cboBeneficiario);
                
                
                
                
                                                          
        try {
            
            
            
            
            
            SimpleDateFormat formatFecha = new SimpleDateFormat("dd/MM/yyyy");
      
                 System.out.println("fecha: "+fecha);

                System.out.println("fecha: "+formatFecha.parse(fecha));
                System.out.println("numero----: "+numero);
                System.out.println("numero----: "+numero.isEmpty());
                System.out.println("numero----: "+numero.length());
                
                 
            
                     String calendar2 = formatFecha2(formatFecha.parse(fecha), "yyyy");
                            String periodo2 = formatFecha2(formatFecha.parse(fecha), "MM");
                            
                                 Map periodoCont = new HashMap();
                               periodoCont.put("compania", compania);
                               periodoCont.put("calendario", calendar2);
                               periodoCont.put("periodo", periodo2);
               
                                 List periodoContaList = processDao.getMapResult("BuscaPeriodoCXP", periodoCont);
                   
                                System.out.println("periodoContaList:" + periodoContaList);
								
								
			        if (periodoContaList == null){
                                    
                                    rq.setSuccess(false);
                                    rq.setMsg("El periodo se encuentra cerrado");
                                    return rq;
                                  
                                
                               } else  if (periodoContaList.isEmpty()){
                                  
                                    rq.setSuccess(false);
                                    rq.setMsg("El periodo se encuentra cerrado");
                                    return rq;
                               }

            InputStream inputStream = null;
            OutputStream outputStream = null;
            
            ErpCpOtras erpCpOtras = new ErpCpOtras();
            ErpCpOtrasId erpCpOtrasId = new ErpCpOtrasId();
            
            int numDir = 0;
            
            
            
                
        
            
            
           
            erpCpOtrasId.setCompania(compania);
            
            int id = erpCpOtrasDao.getMaxErpCpOtrasId(erpCpOtrasId);
            
             erpCpOtrasId.setId(id);
             
            numDir = erpCpOtrasId.getId();
            
            erpCpOtras.setId(erpCpOtrasId);
            erpCpOtras.setRfc(rfcEmisor);
            erpCpOtras.setTipoGasto(tipoGasto);
            if(!fecha.equalsIgnoreCase("")){
            erpCpOtras.setFecha(formatFecha.parse(fecha));
            }
            erpCpOtras.setMoneda(moneda);
            erpCpOtras.setDescripcion(descripcion);
               if (beneficiario.equalsIgnoreCase("")){
                
              erpCpOtras.setBeneficiario(cboBeneficiario);
            
            }else{
                 erpCpOtras.setBeneficiario(beneficiario);
            }
                
            
            erpCpOtras.setImporte(Double.parseDouble(importe));
            erpCpOtras.setIva(Double.parseDouble(iva));
            if (otrosImpuestos.equalsIgnoreCase("")){
                 erpCpOtras.setOtrosImpuestos(Double.parseDouble("0"));
            }else{
              erpCpOtras.setOtrosImpuestos(Double.parseDouble(otrosImpuestos));
            }
            
        
            erpCpOtras.setTotal(Double.parseDouble(total));
            erpCpOtras.setMontoRestProvision(Double.parseDouble(total));
            erpCpOtras.setCtoCxp(ctoCxp);
         
            erpCpOtras.setUsuario(usuario);
            erpCpOtras.setIdProveedor(idProveedor);
            erpCpOtras.setViaticos("1");
             erpCpOtras.setIdTipoNegocio(tipoNeg);
            
                    erpCpOtras.setIdPaisCxp(pais);
       
         
            if(!tipoGastoCXP.equalsIgnoreCase("")){
                
                erpCpOtras.setIdTipoGasto(Integer.parseInt(tipoGastoCXP));
            
            }
            
        
            if(moneda.equalsIgnoreCase("MXN")){
                erpCpOtras.setTipoCambio("");
           
            }else{
                
                 erpCpOtras.setTipoCambio(tipoCambio);
                
            }
            
            
       
         
            
            
            ErpCpOtrasId result = erpCpOtrasDao.save(erpCpOtras);
            
            if(result == null){
                
                rq.setSuccess(false);
                rq.setMsg("Error al guardar Datos");
                
            }else{
                
                rq.setSuccess(true);
                rq.setMsg(""+id);
            
            }
            
       
           
             
        } catch (Exception e) {
             rq.setSuccess(false);
            rq.setMsg("Error al guardar  cxp");
            e.printStackTrace();
            return rq;
        }

        return rq;

    }
    
       @RequestMapping(value = "/vistoBueno", method = RequestMethod.POST)
    public @ResponseBody
    ResponseQuery vistoBueno( String data, String estatus,String motivo, WebRequest webRequest, Model model) throws IOException {

      
        ResponseQuery rq = new ResponseQuery();
         SimpleDateFormat formatoDelTexto = new SimpleDateFormat("dd/MM/yyyy");
    int index = data.indexOf("[");
    if (index == -1) {
      data = "[" + data + "]";
    }
    System.out.println("data:" + data);
    if (model.asMap().get("compania") == null)
    {
      rq.setSuccess(false);
      rq.setData(null);
      rq.setMsg("La sesion no es valida.");
      return rq;
    }
    String compania = model.asMap().get("compania").toString();
    String usuario = model.asMap().get("usuario").toString();
    
    System.out.println("-------------------------------------------------------------------");
    
    System.out.println("data: "+data);
   
                                                        
    try {
            
            

            int guardado = 0;

            ObjectMapper mapper = new ObjectMapper();
            DateFormat df = new SimpleDateFormat("dd/MM/yyyy");

            mapper.setDateFormat(df);
            List<ComprobacionViaDTO> lista = (List)mapper.readValue(data, mapper
              .getTypeFactory().constructCollectionType(List.class, ComprobacionViaDTO.class));

            if (lista.isEmpty())
            {
              rq.setSuccess(false);
              rq.setData(null);
              rq.setMsg("Error no existen datos que guardar");
              return rq;
            }


          
           Iterator<ComprobacionViaDTO> it = lista.iterator();
          
            while (it.hasNext())
            {
              System.out.println("-------------------------------------------------------------------");
              ComprobacionViaDTO ss = (ComprobacionViaDTO)it.next();
              
              String store = "";
              
                    Querys que = new Querys();
                    if (ss.tipoGasto.equalsIgnoreCase("C") || ss.tipoGasto.equalsIgnoreCase("F")){
                        store = que.getSQL("VistoBuenosViaComp");
                    }else{
                        store = que.getSQL("VistoBuenosVia");
                    }
                    
                     Map param = new HashMap();
                          param.put("compania", compania);
                          param.put("comision", ss.numRel);
                          param.put("sec", ss.numero);
                          param.put("motivo", motivo);
                          param.put("estatus", estatus);
                         

                     int  val = processDao.execute(store, param);
                     
                     
                     

                        
       

              }
 
            
            
             
          //   System.out.println("val: "+val);

                 rq.setSuccess(true);
                 rq.setMsg("Guardado Correctamente");
            
           
             
        } catch (Exception e) {
             rq.setSuccess(false);
            rq.setMsg("Error al guardar");
            e.printStackTrace();
            return rq;
        }

        return rq;

    }
    
    
            
    @RequestMapping(value = "/generafechaCash", method = RequestMethod.POST)
    public @ResponseBody
    ResponseQuery generafechaCash(String data,//@RequestParam("rfcEmisor") String rfcEmisor,
            //@RequestParam("rfcReceptor") String rfcReceptor,
           // @RequestParam("total") String total,
           // @RequestParam("uuid") String uuid,
            WebRequest webRequest,
            Model model) {
//        System.out.println("data"+ data);
        ResponseQuery rq = new ResponseQuery();
        ViComisiones comisiones = new ViComisiones();
            ViComisionesId comisionesId = new ViComisionesId();
        
           try {
            ObjectMapper mapper = new ObjectMapper();
            JsonFactory f = new JsonFactory();
            JsonParser jp = f.createJsonParser(data);
            // System.out.println("Data:"+getData());
            // advance stream to START_ARRAY first:
            jp.nextToken();
          //  List<CaBioUsuarioXDisp> lis = new ArrayList<CaBioUsuarioXDisp>();
            int i = 1;
            //ScanUser su = new ScanUser();
//            String mensaje = "";
          //  String mensaje2 = "";
            int countFallas = 0;
            while (jp.nextToken() == JsonToken.START_OBJECT) {
                ViaticosDTO ss = mapper.readValue(jp, ViaticosDTO.class);
                
                
                 Querys que = new Querys();
            String store = que.getSQL("ACTUALIZA_FECHA_CASHXVIATIC");

            Map paramCash = new HashMap();
            paramCash.put("COMPANIA",ss.compania);
            paramCash.put("COMISION",ss.numero);
                int val = processDao.execute(store, paramCash);

                     //if (val <= 0) {

                        rq.setData(null);
                        rq.setMsg("Bien");
                        rq.setSuccess(true);
                        rq.setTotal(BigDecimal.ONE);
                     
                    // }else{
                     
                     
                    // }
                
                
              
                    
                   
                    
            }
           } catch (Exception e) {
             rq.setSuccess(false);
            rq.setMsg("Error al generar proceso");
            e.printStackTrace();
            return rq;
        }

             return rq;
    }
    
    
     @RequestMapping(value = "/updateFechaCash", method = RequestMethod.POST)
    public @ResponseBody
    ResponseQuery updateViaticos( String data, WebRequest webRequest, Model model) throws IOException {
       ResponseQuery rq = new ResponseQuery();
        //System.out.println("data Pagos creacion:"+data);
        
         int index = data.indexOf("[");
        if (index == -1) {
            data = "[" + data + "]";
        }
           
            System.out.println("data:"+data);

            String compania = model.asMap().get("compania").toString();
            String usuario = model.asMap().get("usuario").toString();
            ObjectMapper mapper = new ObjectMapper();
          boolean result = false;
        try{
            List<ViaticosDTO> lista = mapper.readValue(data,
                    mapper.getTypeFactory().constructCollectionType(List.class,
                            ViaticosDTO.class));

            if (lista.isEmpty()) {
                rq.setSuccess(false);
                rq.setData(null);
                rq.setMsg("Error no existen datos que guardar");
                return rq;
            }           
            
            Iterator<ViaticosDTO> it = lista.iterator();
            
            SimpleDateFormat formatFecha = new SimpleDateFormat("dd/MM/yyyy");
            
            ViComisiones comisiones = new ViComisiones();
            ViComisionesId comisionesId = new ViComisionesId();
         
            while (it.hasNext()) {
//                 System.out.println("-------------------------------------------------------------------");
                 ViaticosDTO ss = it.next();
                 
                 System.out.println("ss.compania:"+ss.compania);
                 
                 System.out.println("ss.numero:"+ss.numero);
                 
                 System.out.println("ss.fechaCashFlow:"+ss.fechaCashFlow);
                 
                 //if(ss.fechaCashFlow != null){

                    comisionesId.setCompania(ss.compania);
                    comisionesId.setComision(ss.numero);
                    comisiones.setId(comisionesId);
                    comisiones.setFechaCashFlow(formatFecha.parse(ss.fechaCashFlow));
                    result = viComisionesDao.updateErpViaticosCash(comisiones);

                 //} 
//              

            }
            
            

            
           if (result == true){ 
             rq.setSuccess(true);
             rq.setData(null);
             rq.setMsg("Fecha actualizada correctamente");
           }else{
               
               rq.setSuccess(false);
             rq.setData(null);
             rq.setMsg("Error al actualizar fecha");
           
           }
            
        }catch(Exception e){
            rq.setSuccess(false);
            rq.setData(null);
            rq.setMsg("Error al guardar");
           return rq;
        }
        
        return rq;
    }
    
    
    
       public String formatFecha2(Date date, String format) {
        try {
            return new SimpleDateFormat(format).format(date);
        } catch (Exception e) {
            return "";
        }
    }
    
    
    public void setProcessDao(ProcessDao processDao) {
        this.processDao = processDao;
    }

    public void setErpFoliosPagoViaticosDao(ErpFoliosPagoViaticosDao erpFoliosPagoViaticosDao) {
        this.erpFoliosPagoViaticosDao = erpFoliosPagoViaticosDao;
    }

    public void setErpCpOtrasDao(ErpCpOtrasDao erpCpOtrasDao) {
        this.erpCpOtrasDao = erpCpOtrasDao;
    }

    public void setViComisionesDao(ViComisionesDao viComisionesDao) {
        this.viComisionesDao = viComisionesDao;
    }
    
    
    
}
