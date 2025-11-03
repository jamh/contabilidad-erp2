/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.feraz.cajachica.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.feraz.cajachica.dao.ErpCajaChicaDao;
import com.feraz.cajachica.dao.ErpCajaChicaDetDao;
import com.feraz.cajachica.dao.ErpCajaGastosDao;
import com.feraz.cajachica.dao.ErpFoliosSolFondosDao;
import com.feraz.cajachica.dao.ErpSolicitudDeFondosDao;
import com.feraz.cajachica.dto.CajaDispCDTO;
import com.feraz.cajachica.dto.CajaDispDTO;
import com.feraz.cajachica.dto.CompCajaDTO;
import com.feraz.cajachica.dto.FoliosSolFondosDTO;
import com.feraz.cajachica.dto.SolicitudDTO;
import com.feraz.cajachica.dto.SolicitudFolioDTO;
import com.feraz.cajachica.dto.ViaticosPendMXNDTO;
import com.feraz.cajachica.dto.ViaticosPendUSDDTO;
import com.feraz.cajachica.mail.MailCadena;
import com.feraz.cajachica.mail.MailVerificacionVia;
import com.feraz.cajachica.model.ErpCajaChica;
import com.feraz.cajachica.model.ErpCajaChicaDet;
import com.feraz.cajachica.model.ErpCajaChicaDetId;
import com.feraz.cajachica.model.ErpCajaChicaId;
import com.feraz.cajachica.model.ErpCajaGastos;
import com.feraz.cajachica.model.ErpCajaGastosId;
import com.feraz.cajachica.model.ErpFoliosSolFondos;
import com.feraz.cajachica.model.ErpFoliosSolFondosId;
import com.feraz.cajachica.model.ErpSolicitudDeFondos;
import com.feraz.cajachica.model.ErpSolicitudDeFondosId;
import com.feraz.cajachica.util.CajaChicaDTO;
import com.feraz.cajachica.util.CajaDetDTO;
import com.feraz.cajachica.util.DevolucionDTO;
import com.feraz.facturas.webcontrolfe.model.FileUploadBean;
import com.feraz.polizas3.model.ExtJSFormResult;
import org.jamh.wf.json.model.ResponseQuery2;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
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
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.multipart.MultipartFile;

/**
 *
 * @author vavi
 */
@Controller
@RequestMapping("/cajaGeneral")
@SessionAttributes({"compania", "usuario"})
public class ControlCaja {
    
    private ErpCajaChicaDao erpCajaChicaDao;
    private ErpCajaChicaDetDao erpCajaChicaDetDao;
    private ProcessDao processDao;
    private ErpSolicitudDeFondosDao erpSolicitudDeFondosDao;
    private MailVerificacionVia mailVerificacionVia;
    private ErpFoliosSolFondosDao erpFoliosSolFondosDao;
    private ErpCajaGastosDao erpCajaGastosDao;
    
     @RequestMapping(value = "/saveCajaGeneral", method = RequestMethod.POST)
    public @ResponseBody
    ResponseQuery saveCajaGeneral( WebRequest webRequest, Model model) {

        ResponseQuery rq = new ResponseQuery();
        String compania = model.asMap().get("compania").toString();       
        String usuario = model.asMap().get("usuario").toString();         
     
           String id = webRequest.getParameter("ID").toString();
           String descripcion = webRequest.getParameter("DESCRIPCION").toString();
           String ctoCto = webRequest.getParameter("CTO_CTO").toString();
           String idTipoNegocio = webRequest.getParameter("ID_TIPO_NEGOCIO").toString();
           String idPaisCxp = webRequest.getParameter("ID_PAIS_CXP").toString();
           String importe = webRequest.getParameter("IMPORTE").toString();
           String moneda = webRequest.getParameter("MONEDA").toString();
           String fecha = webRequest.getParameter("FECHA").toString();
           String banco = webRequest.getParameter("BANCO").toString();
           String estatus = webRequest.getParameter("ESTATUS").toString();
           
   
          
                System.out.println("id"+id);
                System.out.println("descripcion"+descripcion);
                
 
                                                          
        try {
            
            

            SimpleDateFormat formatFecha = new SimpleDateFormat("dd/MM/yyyy");
      

               System.out.println("fecha: "+formatFecha.parse(fecha));
     
            
            ErpCajaChica erpCajaChica = new ErpCajaChica();
            ErpCajaChicaId erpCajaChicaId = new ErpCajaChicaId();
            
            int numDir = 0;
            
            
            if (id.isEmpty() == true || id.length() == 0){
            

            erpCajaChicaId.setCompania(compania);
            
            int idAu = erpCajaChicaDao.getMaxErpCajaChicaId(erpCajaChicaId);
            
             erpCajaChicaId.setId(idAu);
             erpCajaChica.setId(erpCajaChicaId);
             erpCajaChica.setIdPaisCxp(idPaisCxp);
             erpCajaChica.setIdTipoNegocio(idTipoNegocio);
             erpCajaChica.setImporte(new BigDecimal(importe));
             erpCajaChica.setImporteRestante(new BigDecimal(importe));
             erpCajaChica.setMoneda(moneda);
             erpCajaChica.setCtocto(ctoCto);
             erpCajaChica.setDescripcion(descripcion);
             erpCajaChica.setFecha(formatFecha.parse(fecha));
             erpCajaChica.setUsuarioCap(usuario);
             erpCajaChica.setBanco(banco);
             erpCajaChica.setEstatus("1");
             
             ErpCajaChicaId result = erpCajaChicaDao.save(erpCajaChica);
             
                     
            if(result == null){
                
                rq.setSuccess(false);
                rq.setMsg("Error al guardar Datos");
                
            }else{
                
                rq.setSuccess(true);
                rq.setMsg(""+idAu);
            
            }
             
                       
            }else{
                
                 erpCajaChicaId.setCompania(compania);
            
                 erpCajaChicaId.setId(Integer.parseInt(id));
                 erpCajaChica.setId(erpCajaChicaId);
                 erpCajaChica.setIdPaisCxp(idPaisCxp);
                 erpCajaChica.setIdTipoNegocio(idTipoNegocio);
                 erpCajaChica.setImporte(new BigDecimal(importe));
                 erpCajaChica.setImporteRestante(new BigDecimal(importe));
                 erpCajaChica.setMoneda(moneda);
                 erpCajaChica.setCtocto(ctoCto);
                 erpCajaChica.setDescripcion(descripcion);
                 erpCajaChica.setFecha(formatFecha.parse(fecha));
                 erpCajaChica.setUsuarioCap(usuario);
                 erpCajaChica.setBanco(banco);
                 erpCajaChica.setEstatus(estatus);
                 boolean result = erpCajaChicaDao.update(erpCajaChica);
                
                 if (result == true){
                 
                     rq.setSuccess(true);
                    rq.setMsg("Datos Actualizados");
                 
                 }else{
                 
                     rq.setSuccess(false);
                     rq.setMsg("Error al guardar Datos");
                 
                 }
                
                
        
            
            
            }
            
            
           
             
        } catch (Exception e) {
             rq.setSuccess(false);
            rq.setMsg("Error al guardar  cxp");
            e.printStackTrace();
            return rq;
        }

        return rq;

    }
    
         @RequestMapping(value = "/deleteCajaGeneral", method = RequestMethod.POST)
    public @ResponseBody
    ResponseQuery deleteCajaGeneral( String data, WebRequest webRequest, Model model) throws IOException {

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
            List<CajaChicaDTO> lista = mapper.readValue(data,
                    mapper.getTypeFactory().constructCollectionType(List.class,
                            CajaChicaDTO.class));

            if (lista.isEmpty()) {
                rq.setSuccess(false);
                rq.setData(null);
                rq.setMsg("Error no existen datos que guardar");
                return rq;
            }

  
            Iterator<CajaChicaDTO> it = lista.iterator();
           ErpCajaChica caja = new ErpCajaChica();
           ErpCajaChicaId cajaId = new ErpCajaChicaId();
          
          
            int banderaGe = 0 ;
            while (it.hasNext()) {
//                 System.out.println("-------------------------------------------------------------------");
                 CajaChicaDTO ss = it.next();
                 
                 cajaId.setCompania(ss.compania);
                 cajaId.setId(Integer.parseInt(ss.id));
                 caja.setId(cajaId);
                 
                 erpCajaChicaDao.delete(caja);
               
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
    
    
    @RequestMapping(value = "/saveCajaDet", method = RequestMethod.POST)
    public @ResponseBody
    ResponseQuery saveCajaDetM( WebRequest webRequest, Model model) {

        ResponseQuery rq = new ResponseQuery();
        String compania = model.asMap().get("compania").toString();       
        String usuario = model.asMap().get("usuario").toString();         
     
           String id = webRequest.getParameter("ID_CAJA").toString();
           String sec = webRequest.getParameter("SEC").toString();
           String idUsuario = webRequest.getParameter("ID_USUARIO").toString();
           String ctoCto = webRequest.getParameter("CTO_CTO").toString();
           String idTipoNegocio = webRequest.getParameter("ID_TIPO_NEGOCIO").toString();
           String idPaisCxp = webRequest.getParameter("ID_PAIS_CXP").toString();
           String importe = webRequest.getParameter("IMPORTE").toString();
           String moneda = webRequest.getParameter("MONEDA").toString();
           String fecha = webRequest.getParameter("FECHA").toString();
           String estatus = webRequest.getParameter("ESTATUS").toString();
           
           
   
          
                System.out.println("id"+id);
                System.out.println("idUsuario"+idUsuario);
                
 
                                                          
        try {
            
            

            SimpleDateFormat formatFecha = new SimpleDateFormat("dd/MM/yyyy");
      

               System.out.println("fecha: "+formatFecha.parse(fecha));
     
            
            ErpCajaChicaDet erpCajaChicaDet = new ErpCajaChicaDet();
            ErpCajaChicaDetId erpCajaChicaDetId = new ErpCajaChicaDetId();
            
            int numDir = 0;
            
            
            if (sec.isEmpty() == true || sec.length() == 0){
            

            erpCajaChicaDetId.setCompania(compania);
            erpCajaChicaDetId.setIdCaja(Integer.parseInt(id));
            
            int idAu = erpCajaChicaDetDao.getMaxErpCajaChicaDetId(erpCajaChicaDetId);
            erpCajaChicaDetId.setSec(idAu);
             erpCajaChicaDet.setId(erpCajaChicaDetId);
             erpCajaChicaDet.setIdPaisCxp(idPaisCxp);
             erpCajaChicaDet.setIdTipoNegocio(idTipoNegocio);
             erpCajaChicaDet.setImporteDet(new BigDecimal(importe));
             erpCajaChicaDet.setMoneda(moneda);
             erpCajaChicaDet.setCtoCto(ctoCto);
             erpCajaChicaDet.setUsuarioCaja(Integer.parseInt(idUsuario));
             erpCajaChicaDet.setFecha(formatFecha.parse(fecha));
             erpCajaChicaDet.setUsuarioCap(usuario);
             erpCajaChicaDet.setEstatus("1");
             ErpCajaChicaDetId result = erpCajaChicaDetDao.save(erpCajaChicaDet);
             
                     
            if(result == null){
                
                rq.setSuccess(false);
                rq.setMsg("Error al guardar Datos");
                
            }else{
                
                rq.setSuccess(true);
                rq.setMsg(""+idAu);
            
            }
             
                       
            }else{
                
                 erpCajaChicaDetId.setCompania(compania);
            
                 erpCajaChicaDetId.setIdCaja(Integer.parseInt(id));
                 erpCajaChicaDetId.setSec(Integer.parseInt(sec));
                 erpCajaChicaDet.setId(erpCajaChicaDetId);
                erpCajaChicaDet.setIdPaisCxp(idPaisCxp);
                erpCajaChicaDet.setIdTipoNegocio(idTipoNegocio);
                erpCajaChicaDet.setImporteDet(new BigDecimal(importe));
                erpCajaChicaDet.setMoneda(moneda);
                erpCajaChicaDet.setCtoCto(ctoCto);
                erpCajaChicaDet.setUsuarioCaja(Integer.parseInt(idUsuario));
                erpCajaChicaDet.setFecha(formatFecha.parse(fecha));
                erpCajaChicaDet.setUsuarioCap(usuario);
                erpCajaChicaDet.setEstatus(estatus);

                 boolean result = erpCajaChicaDetDao.update(erpCajaChicaDet);
                
                 if (result == true){
                 
                     rq.setSuccess(true);
                    rq.setMsg("Datos Actualizados");
                 
                 }else{
                 
                     rq.setSuccess(false);
                     rq.setMsg("Error al guardar Datos");
                 
                 }
                
                
        
            
            
            }
            
                   Querys que = new Querys();
            String store = que.getSQL("GeneraEstatusCaja");
            
             Map param = new HashMap();
                  param.put("compania", compania);
                  param.put("idCaja", id);
             int  val = processDao.execute(store, param);

                if (val <= 0) {
                    
                }
            
            
           
             
        } catch (Exception e) {
             rq.setSuccess(false);
            rq.setMsg("Error al guardar");
            e.printStackTrace();
            return rq;
        }

        return rq;

    }
            
        @RequestMapping(value = "/deleteCajaDet", method = RequestMethod.POST)
    public @ResponseBody
    ResponseQuery deleteCajaDet( String data, WebRequest webRequest, Model model) throws IOException {

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
            List<CajaDetDTO> lista = mapper.readValue(data,
                    mapper.getTypeFactory().constructCollectionType(List.class,
                            CajaDetDTO.class));

            if (lista.isEmpty()) {
                rq.setSuccess(false);
                rq.setData(null);
                rq.setMsg("Error no existen datos que guardar");
                return rq;
            }

  
            Iterator<CajaDetDTO> it = lista.iterator();
           ErpCajaChicaDet caja = new ErpCajaChicaDet();
           ErpCajaChicaDetId cajaId = new ErpCajaChicaDetId();
          
            String idCaja2 = "";
            int banderaGe = 0 ;
            while (it.hasNext()) {
//                 System.out.println("-------------------------------------------------------------------");
                 CajaDetDTO ss = it.next();
                 
                 cajaId.setCompania(ss.compania);
                 cajaId.setIdCaja(Integer.parseInt(ss.idCaja));
                 cajaId.setSec(Integer.parseInt(ss.sec));
                 caja.setId(cajaId);
                 
                 erpCajaChicaDetDao.delete(caja);
               
                  banderaGe = 1;
                  
                  idCaja2 = ss.idCaja;
            }
            
          
               Querys que = new Querys();
            String store = que.getSQL("GeneraEstatusCaja");
            
             Map param = new HashMap();
                  param.put("compania", compania);
                  param.put("idCaja", idCaja2);
             int  val = processDao.execute(store, param);

                if (val <= 0) {
                    
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
    
         @RequestMapping(value = "/actualizaEstatusDetCaja", method = RequestMethod.POST)
    public @ResponseBody
    ResponseQuery actualizaEstatusDetCaja( String data, WebRequest webRequest, Model model) throws IOException {

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
            List<CajaDetDTO> lista = mapper.readValue(data,
                    mapper.getTypeFactory().constructCollectionType(List.class,
                            CajaDetDTO.class));

            if (lista.isEmpty()) {
                rq.setSuccess(false);
                rq.setData(null);
                rq.setMsg("Error no existen datos que guardar");
                return rq;
            }

  
            Iterator<CajaDetDTO> it = lista.iterator();
           ErpCajaChicaDet caja = new ErpCajaChicaDet();
           ErpCajaChicaDetId cajaId = new ErpCajaChicaDetId();
          
            String idCaja2 = "";
            int banderaGe = 0 ;
            while (it.hasNext()) {
//                 System.out.println("-------------------------------------------------------------------");
                 CajaDetDTO ss = it.next();
                 
                 cajaId.setCompania(ss.compania);
                 cajaId.setIdCaja(Integer.parseInt(ss.idCaja));
                 cajaId.setSec(Integer.parseInt(ss.sec));
                 caja.setId(cajaId);
                 caja.setEstatus("2");
                 
                 erpCajaChicaDetDao.actualizaEstatusCaja(caja);
               
                  banderaGe = 1;
                  
                  idCaja2 = ss.idCaja;
            }
            

            
            if (banderaGe == 1){
            
                
                    rq.setSuccess(true);
                    rq.setData(null);
                    rq.setMsg("Registros guardado correctamente");
              
                
            }else{
                
                rq.setSuccess(true);
                rq.setData(null);
                rq.setMsg("Error al guardar registro");
                
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
    
      @RequestMapping(value = "/actualizaEstatusDevolucion", method = RequestMethod.POST)
    public @ResponseBody
    ResponseQuery actualizaEstatusDevolucion( String data, WebRequest webRequest, Model model) throws IOException {

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
            List<DevolucionDTO> lista = mapper.readValue(data,
                    mapper.getTypeFactory().constructCollectionType(List.class,
                            DevolucionDTO.class));

            if (lista.isEmpty()) {
                rq.setSuccess(false);
                rq.setData(null);
                rq.setMsg("Error no existen datos que guardar");
                return rq;
            }

  
            Iterator<DevolucionDTO> it = lista.iterator();
          
            String idCaja2 = "";
            int banderaGe = 0 ;
            while (it.hasNext()) {
//                 System.out.println("-------------------------------------------------------------------");
                 DevolucionDTO ss = it.next();
                 
                     banderaGe = 1;
                      Querys que = new Querys();
               String store = que.getSQL("ActualizaEstatusDevcaja");
               
                   Map parametros = new HashMap();
             

                parametros.put("compania", compania);
                parametros.put("comision",ss.comision);
                 parametros.put("sec",ss.secDev);
           



               int val = processDao.execute(store, parametros);
                  
            }
            

            
            if (banderaGe == 1){
            
                
                    rq.setSuccess(true);
                    rq.setData(null);
                    rq.setMsg("Registros guardado correctamente");
              
                
            }else{
                
                rq.setSuccess(true);
                rq.setData(null);
                rq.setMsg("Error al guardar registro");
                
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
    
             @RequestMapping(value = "/saveComp", method = RequestMethod.POST)
    public @ResponseBody
    ResponseQuery saveComprobacion( String data, WebRequest webRequest, Model model) throws IOException {

      
        ResponseQuery rq = new ResponseQuery();
        String compania = model.asMap().get("compania").toString();       
        String usuario = model.asMap().get("usuario").toString();       
        
        
        
     
           String formComp = webRequest.getParameter("cboCajaFORMA_COMPROBACION").toString();
           String concGasto = webRequest.getParameter("cboCajaCONC_GASTO").toString();
           String cajaAut = webRequest.getParameter("cajaAutViatico").toString();
           String fechaComp = webRequest.getParameter("cajaAutF_COMPROBANTE").toString();
           String moneda = webRequest.getParameter("cajaAutMONEDA").toString();
           String idCaja = webRequest.getParameter("cajaCompID").toString();
           String secCaja = webRequest.getParameter("cajaCompSECCaja").toString();
           String usuarioCaja = webRequest.getParameter("cajaCompUSUARIO_CAJA").toString();
           String rfc = webRequest.getParameter("cajaAutRFCEmisor").toString();
           String nombre = webRequest.getParameter("cajaAutNombreEmisor").toString();
           String importe = webRequest.getParameter("cajaAutIMPORTE").toString();
           String iva = webRequest.getParameter("cajaAutIVA").toString();
           String retencionRenta = webRequest.getParameter("cajaAutRETENCION_RENTA").toString();
           String retencionIva = webRequest.getParameter("cajaAutRETENCION_IVA").toString();
           String otrosImpuestos = webRequest.getParameter("cajaAutOTROS_IMPUESTOS").toString();
           String total = webRequest.getParameter("cajaAutTOTAL").toString();
           String ctoCto = webRequest.getParameter("cboCtoCajaAut").toString();
           String tipoNegocio = webRequest.getParameter("cboTipoNegocioCajaAut").toString();
           String pais = webRequest.getParameter("cboPaisOtrosCajaAut").toString();
           String tipoFactura = webRequest.getParameter("cajaAutTipoFactura").toString();
           String factura = webRequest.getParameter("cajaAutFactura").toString();
           String gasto = webRequest.getParameter("cajaAutGasto").toString();
           String empleadoAplica = webRequest.getParameter("cajaAutEMPLEADO_APLICA").toString();
           
   
          
                                                          
        try {
            
            

         
            
                   Querys que = new Querys();
            String store = que.getSQL("GeneraComprobacionCaja");
             Map param = new HashMap();
                  param.put("compania", compania);
                  param.put("formComp", formComp);
                  param.put("concGasto", concGasto);
                  param.put("cajaAut", cajaAut);
                  param.put("fechaComp", fechaComp);
                  param.put("moneda", moneda);
                  param.put("idCaja", idCaja);
                  param.put("secCaja", secCaja);
                  param.put("usuarioCaja", usuarioCaja);
                  param.put("rfc", rfc);
                  param.put("nombre", nombre);
                  param.put("importe", importe);
                  param.put("iva", iva);
                  param.put("retencionRenta", retencionRenta);
                  param.put("retencionIva", retencionIva);
                  param.put("otrosImpuestos", otrosImpuestos);
                  param.put("total", total);
                  param.put("ctoCto", ctoCto);
                  param.put("tipoNegocio", tipoNegocio);
                  param.put("pais", pais);
                  param.put("tipoFactura", tipoFactura);
                  param.put("factura", factura);
                  param.put("gasto", gasto);
                  param.put("empleadoAplica", empleadoAplica);
                  
                  
             int  val = processDao.execute(store, param);
             
             System.out.println("val: "+val);

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
    
             @RequestMapping(value = "/deleteCompCaja", method = RequestMethod.POST)
    public @ResponseBody
    ResponseQuery deleteCompCaja( String data, WebRequest webRequest, Model model) throws IOException {

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
            List<CompCajaDTO> lista = mapper.readValue(data,
                    mapper.getTypeFactory().constructCollectionType(List.class,
                            CompCajaDTO.class));

            if (lista.isEmpty()) {
                rq.setSuccess(false);
                rq.setData(null);
                rq.setMsg("Error no existen datos que guardar");
                return rq;
            }

  
            Iterator<CompCajaDTO> it = lista.iterator();
          
          
            int banderaGe = 1 ;
            while (it.hasNext()) {
//                 System.out.println("-------------------------------------------------------------------");
                 CompCajaDTO ss = it.next();
                 
                 
                   Querys que = new Querys();
            String store = que.getSQL("DELETE_COMP_CAJA");
             Map param = new HashMap();
                  param.put("compania", compania);
                  param.put("comision", ss.comision);
                  param.put("sec", ss.secComp);
                  param.put("formaComp", ss.formaComprobacion);
                  param.put("viatico", ss.comisionViatico);
                  param.put("numero", ss.numeroFe);
                  param.put("factPago", ss.facturaPago);
                  param.put("origenFactPago", ss.origenFacturaPago);
               
             int  val = processDao.execute(store, param);
                 
                 
           
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
    
       @RequestMapping(value = "/saveArchivoCaja", method = RequestMethod.POST)
    public @ResponseBody
    String create(@RequestParam("archCOMPANIA2CompCaja") String compania2,
            @RequestParam("archIdCompCaja") String id,
            @RequestParam("archNOMBRE2CompCaja") String nombre,
            @RequestParam("archDESCRIPCION2CompCaja") String descripcion,
            @RequestParam("archSECCompCaja") String sec,
            FileUploadBean uploadItemCompCaja,
            BindingResult result, WebRequest webRequest, Model model) {
        
        System.out.println("uploadItem"+uploadItemCompCaja);
        
        boolean isSave = true;
        SimpleDateFormat formatoDelTexto2 = new SimpleDateFormat("dd/MM/yyyy");
        
         String compania = model.asMap().get("compania").toString();
         String usuario = model.asMap().get("usuario").toString();
     
         System.out.println("Cargando Archivo");
               
        ExtJSFormResult extjsFormResult = new ExtJSFormResult();
        
        if (model.asMap().get("usuario") == null) {

            isSave = false;
            extjsFormResult.setMessage("Sesion no valida");
            extjsFormResult.setSuccess(isSave);
            return extjsFormResult.toString();
        }
        
        System.out.println("Session Valida");

        String path = "";
        String url = "";
        String extension = "";
        String nombreArc ="";

        try {
            MultipartFile file = uploadItemCompCaja.getFile();

            InputStream inputStream = null;
            OutputStream outputStream = null;
            if (file.getSize() > 0) {
                inputStream = file.getInputStream();
                long maxSizeDocument;
                long maxSize = new Long("5145728");
                
                System.out.println("Tamaño en server: "+maxSize);
                System.out.println("Tamaño archivo: "+file.getSize());
                
                if (file.getSize() > maxSize) {
                    //System.out.println("File Size:::" + file.getSize());
                    isSave = false;
                    extjsFormResult.setMessage("Tamaño no valido");
                    extjsFormResult.setSuccess(isSave);
                    return extjsFormResult.toString();
                }

                int i = file.getOriginalFilename().lastIndexOf('.');
                
//                System.out.println("Nombre:" + file.getOriginalFilename());
                
               
                    extension = file.getOriginalFilename().substring(i + 1);
                    nombreArc = file.getOriginalFilename().substring(0 , i);
//                    System.out.println("i:" + i);
//               System.out.println("exte:" + extension);
//               System.out.println("nombreArc:" + nombreArc);
               // System.out.println("exte:" + extension);
//                if (!extension.trim().equals("pdf")) {
//                    isSave = false;
//                    extjsFormResult.setMessage("Archivo pdf no valido");
//                    extjsFormResult.setSuccess(isSave);
//                    return extjsFormResult.toString();
//                }
                String hora = "" + System.currentTimeMillis();
                 path ="D:/Administrategia/empresarial/files/CAJA/"+compania+"/"+nombreArc+"."+extension;
              
                 url ="/cfiles/CAJA/"+compania+"/"+nombreArc+"."+extension;
                 
                 revisarDirectorio("D:/Administrategia/empresarial/files/CAJA/"+compania+"/");
                 
                 System.out.println("Ruta: "+path);
                 System.out.println("url: "+url);
             
                outputStream = new FileOutputStream(path);

                int readBytes = 0;
                byte[] buffer = new byte[10000];
                
                System.out.println("Empezando Carga...");
                
                while ((readBytes = inputStream.read(buffer, 0, 10000)) != -1) {
                    outputStream.write(buffer, 0, readBytes);
                }
                outputStream.close();
                inputStream.close();
                
                System.out.println("Archivo Guardado");
            }

            
            
               Querys que = new Querys();
               String store = que.getSQL("insertaArchivoCaja");
               
                   Map parametros = new HashMap();
             
 //  P_SID              VARCHAR2,

             
                parametros.put("compania", compania);
                parametros.put("idCaja",id);
                parametros.put("descripcion",descripcion);
                parametros.put("nombre",nombre);
                parametros.put("path",path);
                parametros.put("url",url);
                parametros.put("nombreArch",nombreArc+"."+extension);
                parametros.put("usuario",usuario);
                parametros.put("sec",sec);

                
           



               int val = processDao.execute(store, parametros);
                
            System.out.println("Datos Insertados");
   
            if (val > 0) {
                   isSave = true;
                extjsFormResult.setMessage("Archivo Guardado");
                extjsFormResult.setSuccess(isSave);
                return extjsFormResult.toString();
               
            } else {
             
                 isSave = false;
                extjsFormResult.setMessage("Error al guardar los datos");
                    extjsFormResult.setSuccess(isSave);
                return extjsFormResult.toString();
            }

        } catch (Exception e) {
            e.printStackTrace();
            isSave = false;
        }




        extjsFormResult.setSuccess(isSave);

        return extjsFormResult.toString();
    }
      @RequestMapping(value = "/deleteArchivoCaja", method = RequestMethod.POST)
    public @ResponseBody
    ResponseQuery deleteArchivoCompCaja( String idCaja,String id,String sec, WebRequest webRequest, Model model) {

        ResponseQuery rq = new ResponseQuery();
   //     System.out.println("data factura cancelacion:"+data);
        


            String compania = model.asMap().get("compania").toString();
            String usuario = model.asMap().get("usuario").toString();
            ObjectMapper mapper = new ObjectMapper();
          
                   
        try{
            
             
            
              Querys que = new Querys();
               String store = que.getSQL("BorraDatosArchivosCaja");
               
                   Map parametros = new HashMap();
             
 //  P_SID              VARCHAR2,

             
                parametros.put("compania", compania);
                parametros.put("idCaja",idCaja);
                 parametros.put("id",id);
                 parametros.put("sec",sec);
           



               int val = processDao.execute(store, parametros);
          
            
            if (val > 0){
            
                
                    rq.setSuccess(true);
                    rq.setData(null);
                    rq.setMsg("Producto eliminado correctamente");
              
                
            }else{
                
                rq.setSuccess(true);
                rq.setData(null);
                rq.setMsg("Error al eliminar");
                
            }
            

        }catch(Exception e){
            e.printStackTrace();
            rq.setSuccess(false);
            rq.setData(null);
            rq.setMsg("Error al eliminar");
           return rq;
        }
        
        return rq;
    }
    
    
    
               @RequestMapping(value = "/pagaViaticUSD", method = RequestMethod.POST)
    public @ResponseBody
    ResponseQuery pagaViaticUSD( String data, String data2, WebRequest webRequest, Model model) throws IOException {

      
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
    System.out.println("data2: "+data2);
   
                                                        
    try {
            
            

            int guardado = 0;

            ObjectMapper mapper = new ObjectMapper();
            DateFormat df = new SimpleDateFormat("dd/MM/yyyy");

            mapper.setDateFormat(df);
            List<CajaDispDTO> lista = (List)mapper.readValue(data, mapper
              .getTypeFactory().constructCollectionType(List.class, CajaDispDTO.class));

             List<ViaticosPendUSDDTO> listaDet = (List)mapper.readValue(data2, mapper
              .getTypeFactory().constructCollectionType(List.class, ViaticosPendUSDDTO.class));

            if (lista.isEmpty())
            {
              rq.setSuccess(false);
              rq.setData(null);
              rq.setMsg("Error no existen datos que guardar");
              return rq;
            }

            if (listaDet.isEmpty())
            {
              rq.setSuccess(false);
              rq.setData(null);
              rq.setMsg("Error no existen datos que guardar");
              return rq;
            }
            
            Iterator<ViaticosPendUSDDTO> itDet = listaDet.iterator();

    while (itDet.hasNext()){
          ViaticosPendUSDDTO ss2 = (ViaticosPendUSDDTO)itDet.next();
          
          System.out.println("ss2.idOrdenCompra: "+ss2.comisionUsd);
          
           Iterator<CajaDispDTO> it = lista.iterator();
          
            while (it.hasNext())
            {
              System.out.println("-------------------------------------------------------------------");
              CajaDispDTO ss = (CajaDispDTO)it.next();
              
                    Querys que = new Querys();
                    String store = que.getSQL("GeneraPagoCaja");
                     Map param = new HashMap();
                          param.put("compania", compania);
                          param.put("cajaAut", ss2.comisionUsd);
                        //  param.put("fechaComp", fechaComp);
                          param.put("moneda", ss2.tMonedaUsd);
                          param.put("idCaja", ss.idCaja);
                          param.put("secCaja", ss.sec);
                          param.put("usuarioCaja", usuario);                         
                          param.put("total", ss2.importeUsd);
                          param.put("estatus", "ECC");
                         
                          
                         

                     int  val = processDao.execute(store, param);

              }
            
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
    
    
                @RequestMapping(value = "/pagaViaticMXN", method = RequestMethod.POST)
    public @ResponseBody
    ResponseQuery pagaViaticMXN( String data, String data2, WebRequest webRequest, Model model) throws IOException {

      
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
    System.out.println("data2: "+data2);
   
                                                        
    try {
            
            

            int guardado = 0;

            ObjectMapper mapper = new ObjectMapper();
            DateFormat df = new SimpleDateFormat("dd/MM/yyyy");

            mapper.setDateFormat(df);
            List<CajaDispDTO> lista = (List)mapper.readValue(data, mapper
              .getTypeFactory().constructCollectionType(List.class, CajaDispDTO.class));

             List<ViaticosPendMXNDTO> listaDet = (List)mapper.readValue(data2, mapper
              .getTypeFactory().constructCollectionType(List.class, ViaticosPendMXNDTO.class));

            if (lista.isEmpty())
            {
              rq.setSuccess(false);
              rq.setData(null);
              rq.setMsg("Error no existen datos que guardar");
              return rq;
            }

            if (listaDet.isEmpty())
            {
              rq.setSuccess(false);
              rq.setData(null);
              rq.setMsg("Error no existen datos que guardar");
              return rq;
            }
            
            Iterator<ViaticosPendMXNDTO> itDet = listaDet.iterator();

    while (itDet.hasNext()){
          ViaticosPendMXNDTO ss2 = (ViaticosPendMXNDTO)itDet.next();
          
          System.out.println("ss2.idOrdenCompra: "+ss2.comision);
          
           Iterator<CajaDispDTO> it = lista.iterator();
          
            while (it.hasNext())
            {
              System.out.println("-------------------------------------------------------------------");
              CajaDispDTO ss = (CajaDispDTO)it.next();
              
                    Querys que = new Querys();
                    String store = que.getSQL("GeneraPagoCaja");
                     Map param = new HashMap();
                          param.put("compania", compania);
                          param.put("cajaAut", ss2.comision);
                        //  param.put("fechaComp", fechaComp);
                          param.put("moneda", ss2.tMoneda);
                          param.put("idCaja", ss.idCaja);
                          param.put("secCaja", ss.sec);
                          param.put("usuarioCaja", usuario);                         
                          param.put("total", ss2.importe);
                          param.put("estatus", "ECC");
                         

                     int  val = processDao.execute(store, param);

              }
            
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
    
                 @RequestMapping(value = "/pagaViaticCC", method = RequestMethod.POST)
    public @ResponseBody
    ResponseQuery pagaViaticCC( String data, String estatus,String motivo, WebRequest webRequest, Model model) throws IOException {

      
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
            List<CajaDispCDTO> lista = (List)mapper.readValue(data, mapper
              .getTypeFactory().constructCollectionType(List.class, CajaDispCDTO.class));

            if (lista.isEmpty())
            {
              rq.setSuccess(false);
              rq.setData(null);
              rq.setMsg("Error no existen datos que guardar");
              return rq;
            }


          
           Iterator<CajaDispCDTO> it = lista.iterator();
          
            while (it.hasNext())
            {
              System.out.println("-------------------------------------------------------------------");
              CajaDispCDTO ss = (CajaDispCDTO)it.next();
                MailCadena cadena = new MailCadena();

                        String cad = cadena.getCadenaAlfanumAleatoria(10);
                        
                    Querys que = new Querys();
                    String store = que.getSQL("GeneraPagoCaja");
                     Map param = new HashMap();
                          param.put("compania", compania);
                          param.put("cajaAut", ss.comision);
                        //  param.put("fechaComp", fechaComp);
                          param.put("moneda", ss.moneda);
                          param.put("idCaja", ss.idCaja);
                          param.put("secCaja", ss.secCaja);
                          param.put("usuarioCaja", usuario);                         
                          param.put("total", ss.importe);
                          param.put("estatus", estatus);
                          param.put("motivo", motivo);
                          param.put("codigo", cad);
                          
                         

                     int  val = processDao.execute(store, param);
                     
                     
                     

                        
                     
                    if(estatus.equalsIgnoreCase("PAG")) {
                        Map correos = new HashMap();
                        correos.put("compania", compania);
                        correos.put("comision", ss.comision);
                        correos.put("usuarioCaja", usuario);

                        List listTitulo = processDao.getMapResult("BuscaCorreosEfect", correos);
                        
                        if (!listTitulo.isEmpty()){
                            
                             Map cor = (HashMap) listTitulo.get(0);
                            String correoUsuario = cor.get("CORREO_USUARIO").toString();
                            String correoCaja = cor.get("CORREO_CAJA").toString() ;
                            String usuarioVia = cor.get("USUARIO").toString() ;
                            String nombreVia = cor.get("NOM_USUARIO").toString() ;
                            String nombreCaja = cor.get("NOM_US_CAJA").toString() ;
                            
                            double importeCaja = Double.parseDouble(ss.importe);
                        NumberFormat formatoImporte = NumberFormat.getCurrencyInstance();

                         String importe=formatoImporte.format(importeCaja);
                                    
                            mailVerificacionVia.sendMail(cad, correoUsuario,usuarioVia,  compania, nombreVia, nombreCaja,importe,ss.comision,ss.moneda);
                            mailVerificacionVia.sendMail(cad, correoCaja,  usuario, compania,nombreVia, nombreCaja,importe,ss.comision,ss.moneda);
                        }
                        
                        
                        
                    }

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
    
    
     @RequestMapping(value = "/saveGasto", method = RequestMethod.POST)
    public @ResponseBody
    ResponseQuery saveGasto( WebRequest webRequest, Model model) {

        ResponseQuery rq = new ResponseQuery();
        String compania = model.asMap().get("compania").toString();       
        String usuario = model.asMap().get("usuario").toString();         
     
           String idCaja = webRequest.getParameter("cajaGastoFormIdCaja").toString();
           String sec = webRequest.getParameter("cajaGastoFormSec").toString();
           String moneda = webRequest.getParameter("cajaGastoFormMoneda").toString();
           String nombre = webRequest.getParameter("cajaGastoFormNombre").toString();
           
           String descripcion = webRequest.getParameter("cajaGastoFormDescripcion").toString();
           String importe = webRequest.getParameter("cajaGastoFormImporte").toString();
           String operacion = webRequest.getParameter("cajaGastoOperacion").toString();
           String idGasto = webRequest.getParameter("cajaGastoIdGasto").toString();
           String fecha = webRequest.getParameter("cajaGastoFecha").toString();
           String empleado = webRequest.getParameter("cajaGastoEmpleado").toString();
           
           
      

   
          
                System.out.println("importe"+importe);
                System.out.println("moneda"+moneda);
                
 
                                                          
        try {
            
            

            SimpleDateFormat formatFecha = new SimpleDateFormat("dd/MM/yyyy");
      

              // System.out.println("fecha: "+formatFecha.parse(fecha));
     
            
            ErpCajaGastos erpCajaGastos = new ErpCajaGastos();
            ErpCajaGastosId erpCajaGastoId = new ErpCajaGastosId();
            
            int numDir = 0;
            

            erpCajaGastoId.setCompania(compania);
            erpCajaGastoId.setIdCaja(Integer.parseInt(idCaja));
            erpCajaGastoId.setSec(Integer.parseInt(sec));
            System.out.println("compania: "+compania);
            System.out.println("idCaja: "+idCaja);
            System.out.println("sec: "+sec);
            if(operacion.equalsIgnoreCase("I")){
                    int idAu = erpCajaGastosDao.getMaxErpCajaGastosId(erpCajaGastoId);
                    System.out.println("idAu: "+idAu);
                     erpCajaGastoId.setIdGasto(idAu);
                     erpCajaGastos.setId(erpCajaGastoId);
                     erpCajaGastos.setNombre(nombre);
                     erpCajaGastos.setDescripcion(descripcion);
                     erpCajaGastos.setMoneda(moneda);
                     erpCajaGastos.setImporte(new BigDecimal(importe));
                     erpCajaGastos.setFecha(formatFecha.parse(fecha));
                     erpCajaGastos.setEmpleado(empleado);

                     ErpCajaGastosId result = erpCajaGastosDao.save(erpCajaGastos);


                    if(result == null){

                        rq.setSuccess(false);
                        rq.setMsg("Error al guardar Datos");

                    }else{

                        rq.setSuccess(true);
                        rq.setMsg(""+idAu);

                    }
             
            }else{
            
                     erpCajaGastoId.setIdGasto(Integer.parseInt(idGasto));
                     erpCajaGastos.setId(erpCajaGastoId);
                     erpCajaGastos.setNombre(nombre);
                     erpCajaGastos.setDescripcion(descripcion);
                     erpCajaGastos.setMoneda(moneda);
                     erpCajaGastos.setImporte(new BigDecimal(importe));
                     erpCajaGastos.setFecha(formatFecha.parse(fecha));
                     erpCajaGastos.setEmpleado(empleado);
                     boolean result = erpCajaGastosDao.update(erpCajaGastos);


                    if(result == false){

                        rq.setSuccess(false);
                        rq.setMsg("Error al guardar Datos");

                    }else{

                        rq.setSuccess(true);
                        rq.setMsg("Se actualizo correctamente");

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
      @RequestMapping(value = "/saveFondos", method = RequestMethod.POST)
    public @ResponseBody
    ResponseQuery saveFondos( WebRequest webRequest, Model model) {

        ResponseQuery rq = new ResponseQuery();
        String compania = model.asMap().get("compania").toString();       
        String usuario = model.asMap().get("usuario").toString();         
     
           String importe = webRequest.getParameter("cajaAutIMPORTEDispFon").toString();
           String moneda = webRequest.getParameter("cajaAutMonedaDispFon").toString();
           String descripcion = webRequest.getParameter("cajaAutDESCRIPCIONDispFon").toString();
           String area = webRequest.getParameter("cboAreaFondos").toString();
           
           
   
          
                System.out.println("importe"+importe);
                System.out.println("moneda"+moneda);
                
 
                                                          
        try {
            
            

            SimpleDateFormat formatFecha = new SimpleDateFormat("dd/MM/yyyy");
      

              // System.out.println("fecha: "+formatFecha.parse(fecha));
     
            
            ErpSolicitudDeFondos erpSolicitudDeFondos = new ErpSolicitudDeFondos();
            ErpSolicitudDeFondosId erpSolicitudDeFondosId = new ErpSolicitudDeFondosId();
            
            int numDir = 0;
            

            erpSolicitudDeFondosId.setCompania(compania);
            
            int idAu = erpSolicitudDeFondosDao.getMaxErpSolCajaId(erpSolicitudDeFondosId);
            
             erpSolicitudDeFondosId.setIdSolicitud(idAu);
             erpSolicitudDeFondos.setId(erpSolicitudDeFondosId);
             erpSolicitudDeFondos.setUsuarioSolicita(usuario);
             erpSolicitudDeFondos.setMoneda(moneda);
             erpSolicitudDeFondos.setImporteRequerido(new BigDecimal(importe));
             erpSolicitudDeFondos.setSolicitarACaja("0");
             erpSolicitudDeFondos.setDescripcion(descripcion);
             erpSolicitudDeFondos.setIdArea(Integer.parseInt(area));
             erpSolicitudDeFondos.setAutorizaArea("1");
             //erpSolicitudDeFondos.setFechaDeSolicitud(new Date());
             
             ErpSolicitudDeFondosId result = erpSolicitudDeFondosDao.save(erpSolicitudDeFondos);
             
                     
            if(result == null){
                
                rq.setSuccess(false);
                rq.setMsg("Error al guardar Datos");
                
            }else{
                
                rq.setSuccess(true);
                rq.setMsg(""+idAu);
            
            }
             
             
            
            
           
             
        } catch (Exception e) {
             rq.setSuccess(false);
            rq.setMsg("Error al guardar");
            e.printStackTrace();
            return rq;
        }

        return rq;

    }
    
     @RequestMapping(value = "/deleteSolFon", method = RequestMethod.POST)
    public @ResponseBody
    ResponseQuery deleteSolFon( String idSol, WebRequest webRequest, Model model) {

        ResponseQuery rq = new ResponseQuery();
   //     System.out.println("data factura cancelacion:"+data);
        


            String compania = model.asMap().get("compania").toString();
            String usuario = model.asMap().get("usuario").toString();
            ObjectMapper mapper = new ObjectMapper();
          
                   
        try{
            
             
            
        
             ErpSolicitudDeFondos erpSolicitudDeFondos = new ErpSolicitudDeFondos();
            ErpSolicitudDeFondosId erpSolicitudDeFondosId = new ErpSolicitudDeFondosId();
            
            int numDir = 0;
            

            erpSolicitudDeFondosId.setCompania(compania);
                        
             erpSolicitudDeFondosId.setIdSolicitud(Integer.parseInt(idSol));
             erpSolicitudDeFondos.setId(erpSolicitudDeFondosId);
             //erpSolicitudDeFondos.setFechaDeSolicitud(new Date());
             
             boolean result = erpSolicitudDeFondosDao.delete(erpSolicitudDeFondos);
             
                     
            if(result == false){
                
                rq.setSuccess(false);
                rq.setMsg("Error al borrar Datos");
                
            }else{
                
               rq.setSuccess(true);
                rq.setMsg("Los datos se borraron correctamente");
            
            }
             
             
            
            
           
            

        }catch(Exception e){
            e.printStackTrace();
            rq.setSuccess(false);
            rq.setData(null);
            rq.setMsg("Error al eliminar");
           return rq;
        }
        
        return rq;
    }
    
     @RequestMapping(value = "/deleteGasto", method = RequestMethod.POST)
    public @ResponseBody
    ResponseQuery deleteGasto( String idCaja,String sec, String idGasto, WebRequest webRequest, Model model) {

        ResponseQuery rq = new ResponseQuery();
   //     System.out.println("data factura cancelacion:"+data);
        


            String compania = model.asMap().get("compania").toString();
            String usuario = model.asMap().get("usuario").toString();
            ObjectMapper mapper = new ObjectMapper();
          
                   
        try{
            
             
            
        
             ErpCajaGastos erpCajaGastos = new ErpCajaGastos();
            ErpCajaGastosId erpCajaGastosId = new ErpCajaGastosId();
            
            int numDir = 0;
            

            erpCajaGastosId.setCompania(compania);
            erpCajaGastosId.setIdCaja(Integer.parseInt(idCaja));
            erpCajaGastosId.setSec(Integer.parseInt(sec));
            erpCajaGastosId.setIdGasto(Integer.parseInt(idGasto));
            erpCajaGastos.setId(erpCajaGastosId);
      
             
             boolean result = erpCajaGastosDao.delete(erpCajaGastos);
             
                     
            if(result == false){
                
                rq.setSuccess(false);
                rq.setMsg("Error al borrar Datos");
                
            }else{
                
               rq.setSuccess(true);
                rq.setMsg("Los datos se borraron correctamente");
            
            }
             
             
            
            
           
            

        }catch(Exception e){
            e.printStackTrace();
            rq.setSuccess(false);
            rq.setData(null);
            rq.setMsg("Error al eliminar");
           return rq;
        }
        
        return rq;
    }
    
            
          @RequestMapping(value = "/actualizaEstatusSolFon", method = RequestMethod.POST)
    public @ResponseBody
    ResponseQuery actualizaEstatusSolFon( String data, WebRequest webRequest, Model model) throws IOException {

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
            List<SolicitudDTO> lista = mapper.readValue(data,
                    mapper.getTypeFactory().constructCollectionType(List.class,
                            SolicitudDTO.class));

            if (lista.isEmpty()) {
                rq.setSuccess(false);
                rq.setData(null);
                rq.setMsg("Error no existen datos que guardar");
                return rq;
            }

  
            Iterator<SolicitudDTO> it = lista.iterator();
            ErpSolicitudDeFondos erpSolicitudDeFondos = new ErpSolicitudDeFondos();
            ErpSolicitudDeFondosId erpSolicitudDeFondosId = new ErpSolicitudDeFondosId();

          
            String idCaja2 = "";
            int banderaGe = 0 ;
            while (it.hasNext()) {
//                 System.out.println("-------------------------------------------------------------------");
                 SolicitudDTO ss = it.next();
                 
                
                int numDir = 0;


                erpSolicitudDeFondosId.setCompania(compania);

                 erpSolicitudDeFondosId.setIdSolicitud(Integer.parseInt(ss.idSolicitud));
                 erpSolicitudDeFondos.setId(erpSolicitudDeFondosId);
                
                 erpSolicitudDeFondos.setSolicitarACaja("1");
                 //erpSolicitudDeFondos.setFechaDeSolicitud(new Date());

                  erpSolicitudDeFondosDao.updateEstatus(erpSolicitudDeFondos);



             
            }
            

            
            if (banderaGe == 1){
            
                
                    rq.setSuccess(true);
                    rq.setData(null);
                    rq.setMsg("Registros guardado correctamente");
              
                
            }else{
                
                rq.setSuccess(true);
                rq.setData(null);
                rq.setMsg("Error al guardar registro");
                
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
    
    
     @RequestMapping(value = "/actualizaEstatusSolFonCan", method = RequestMethod.POST)
    public @ResponseBody
    ResponseQuery actualizaEstatusSolFonCan( String data,String text, WebRequest webRequest, Model model) throws IOException {

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
            List<SolicitudDTO> lista = mapper.readValue(data,
                    mapper.getTypeFactory().constructCollectionType(List.class,
                            SolicitudDTO.class));

            if (lista.isEmpty()) {
                rq.setSuccess(false);
                rq.setData(null);
                rq.setMsg("Error no existen datos que guardar");
                return rq;
            }

  
            Iterator<SolicitudDTO> it = lista.iterator();
            ErpSolicitudDeFondos erpSolicitudDeFondos = new ErpSolicitudDeFondos();
            ErpSolicitudDeFondosId erpSolicitudDeFondosId = new ErpSolicitudDeFondosId();

          
            String idCaja2 = "";
            int banderaGe = 0 ;
            while (it.hasNext()) {
//                 System.out.println("-------------------------------------------------------------------");
                 SolicitudDTO ss = it.next();
                 
                
                int numDir = 0;


                erpSolicitudDeFondosId.setCompania(compania);

                 erpSolicitudDeFondosId.setIdSolicitud(Integer.parseInt(ss.idSolicitud));
                 erpSolicitudDeFondos.setId(erpSolicitudDeFondosId);
                
                 erpSolicitudDeFondos.setSolicitarACaja("4");
                 erpSolicitudDeFondos.setAutorizaArea("3");
                 erpSolicitudDeFondos.setFechaAutorizoArea(new Date());
                 erpSolicitudDeFondos.setUsuarioAutorizaArea(usuario);
                 erpSolicitudDeFondos.setMotRechazo(text);
                 //erpSolicitudDeFondos.setFechaDeSolicitud(new Date());

                  erpSolicitudDeFondosDao.updateEstatusAuto(erpSolicitudDeFondos);



             
            }
            

            
            if (banderaGe == 1){
            
                
                    rq.setSuccess(true);
                    rq.setData(null);
                    rq.setMsg("Registros guardado correctamente");
              
                
            }else{
                
                rq.setSuccess(true);
                rq.setData(null);
                rq.setMsg("Error al guardar registro");
                
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
      @RequestMapping(value = "/actualizaEstatusSolFonAut", method = RequestMethod.POST)
    public @ResponseBody
    ResponseQuery actualizaEstatusSolFonAut( String data, WebRequest webRequest, Model model) throws IOException {

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
            List<SolicitudDTO> lista = mapper.readValue(data,
                    mapper.getTypeFactory().constructCollectionType(List.class,
                            SolicitudDTO.class));

            if (lista.isEmpty()) {
                rq.setSuccess(false);
                rq.setData(null);
                rq.setMsg("Error no existen datos que guardar");
                return rq;
            }

  
            Iterator<SolicitudDTO> it = lista.iterator();
            ErpSolicitudDeFondos erpSolicitudDeFondos = new ErpSolicitudDeFondos();
            ErpSolicitudDeFondosId erpSolicitudDeFondosId = new ErpSolicitudDeFondosId();

          
            String idCaja2 = "";
            int banderaGe = 0 ;
            while (it.hasNext()) {
//                 System.out.println("-------------------------------------------------------------------");
                 SolicitudDTO ss = it.next();
                 
                
                int numDir = 0;


                erpSolicitudDeFondosId.setCompania(compania);

                 erpSolicitudDeFondosId.setIdSolicitud(Integer.parseInt(ss.idSolicitud));
                 erpSolicitudDeFondos.setId(erpSolicitudDeFondosId);
                
                 erpSolicitudDeFondos.setSolicitarACaja("1");
                 erpSolicitudDeFondos.setAutorizaArea("2");
                 erpSolicitudDeFondos.setFechaAutorizoArea(new Date());
                 erpSolicitudDeFondos.setUsuarioAutorizaArea(usuario);
                 //erpSolicitudDeFondos.setFechaDeSolicitud(new Date());

                  erpSolicitudDeFondosDao.updateEstatusAuto(erpSolicitudDeFondos);



             
            }
            

            
            if (banderaGe == 1){
            
                
                    rq.setSuccess(true);
                    rq.setData(null);
                    rq.setMsg("Registros guardado correctamente");
              
                
            }else{
                
                rq.setSuccess(true);
                rq.setData(null);
                rq.setMsg("Error al guardar registro");
                
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
    
    
      @RequestMapping(value = "/updateFondosFolio", method = RequestMethod.POST)
    public @ResponseBody
    ResponseQuery updateFondosFolio( WebRequest webRequest, Model model) {

        ResponseQuery rq = new ResponseQuery();
        String compania = model.asMap().get("compania").toString();       
        String usuario = model.asMap().get("usuario").toString();         
     
           String idSolicitud = webRequest.getParameter("idSolicitudFonSol").toString();
           String moneda = webRequest.getParameter("cajaAutMonedaDispFonSol").toString();
           String importe = webRequest.getParameter("cajaAutIMPORTEDispFonSol").toString();
           String tipo = webRequest.getParameter("tipoSolicitudFondSol").toString();
           String banco = webRequest.getParameter("bancoCajaGenFonSol").toString();
           String casa = webRequest.getParameter("CASACajaGenFonSol").toString();
           String monedaPago = webRequest.getParameter("cajaAutMonedaPagoDispFonSol").toString();
           String importePago = webRequest.getParameter("cajaAutIMPORTEPagoDispFonSol").toString();
           String idUsuarioFonSol = webRequest.getParameter("idUsuarioFonSol").toString();
           String numeroOperacion = webRequest.getParameter("cajaAutNumeroOperacionDispFonSol").toString();
           String fechaRetiro = webRequest.getParameter("fechaRetiroDispFonSol").toString();
           String descripcion = webRequest.getParameter("cajaAutDescripcionnDispFonSol").toString();
           String tc = webRequest.getParameter("cajaAutTC_IMPORTE_PAGOPagoDispFonSol").toString();
           
           String idArea = webRequest.getParameter("cajaAutidAreaDispFonSol").toString();
           String autorizaArea = webRequest.getParameter("cajaAutAutorizaAreaDispFonSol").toString();
           String usuarioAutorizaArea = webRequest.getParameter("cajaAutUsuarioAutorizaAreaDispFonSol").toString();
           String fechaAutorizaArea = webRequest.getParameter("cajaAutFechaAutorizaAreaDispFonSol").toString();
           String motivoRechazo = webRequest.getParameter("cajaMotRechaoDispFonSol").toString();

  
                           
      
 
                                                          
        try {
            
            

            SimpleDateFormat formatFecha = new SimpleDateFormat("dd/MM/yyyy");
      

              // System.out.println("fecha: "+formatFecha.parse(fecha));
     
            
            ErpSolicitudDeFondos erpSolicitudDeFondos = new ErpSolicitudDeFondos();
            ErpSolicitudDeFondosId erpSolicitudDeFondosId = new ErpSolicitudDeFondosId();
            
            int numDir = 0;
            if (idSolicitud.isEmpty() == true || idSolicitud.length() == 0){
                
                        erpSolicitudDeFondosId.setCompania(compania);
                        int idAu = erpSolicitudDeFondosDao.getMaxErpSolCajaId(erpSolicitudDeFondosId);
                        erpSolicitudDeFondosId.setIdSolicitud(idAu);
                        erpSolicitudDeFondos.setId(erpSolicitudDeFondosId);
                        erpSolicitudDeFondos.setTipo(tipo);
                        erpSolicitudDeFondos.setMoneda(moneda);
                        erpSolicitudDeFondos.setImporteRequerido(new BigDecimal(importe));
                        erpSolicitudDeFondos.setUsuarioSolicita(usuario);
                        erpSolicitudDeFondos.setSolicitarACaja("2");
                        erpSolicitudDeFondos.setDescripcion(descripcion);
                        if(tipo.equalsIgnoreCase("R")){

                             erpSolicitudDeFondos.setBanco(banco);
                             erpSolicitudDeFondos.setMonedaDePago(moneda);
                             erpSolicitudDeFondos.setImporteDePago(new BigDecimal(importe));
                             erpSolicitudDeFondos.setNumeroDeOperacion(numeroOperacion);
                             erpSolicitudDeFondos.setFechaDeRetiro(formatFecha.parse(fechaRetiro));
                             

                        }
                        if(tipo.equalsIgnoreCase("C")){

                             erpSolicitudDeFondos.setCasaDeCambio(Integer.parseInt(casa));
                             erpSolicitudDeFondos.setMonedaDePago(monedaPago);
                             erpSolicitudDeFondos.setImporteDePago(new BigDecimal(importePago));
                             erpSolicitudDeFondos.setTcImportePago(new BigDecimal(tc));

                        }
                        
                       ErpSolicitudDeFondosId id = erpSolicitudDeFondosDao.save(erpSolicitudDeFondos);
                       
                         if(id == null){

                            rq.setSuccess(false);
                            rq.setMsg("Error al guardar Datos");

                        }else{

                            rq.setSuccess(true);
                            rq.setMsg(""+idSolicitud);

                        }

                
            }else{

                        erpSolicitudDeFondosId.setCompania(compania);
                        erpSolicitudDeFondosId.setIdSolicitud(Integer.parseInt(idSolicitud));
                        erpSolicitudDeFondos.setId(erpSolicitudDeFondosId);
                        erpSolicitudDeFondos.setTipo(tipo);
                        erpSolicitudDeFondos.setMoneda(moneda);
                        erpSolicitudDeFondos.setImporteRequerido(new BigDecimal(importe));
                        erpSolicitudDeFondos.setUsuarioSolicita(idUsuarioFonSol);
                        erpSolicitudDeFondos.setSolicitarACaja("2");
                        erpSolicitudDeFondos.setDescripcion(descripcion);
                        
                        erpSolicitudDeFondos.setIdArea(Integer.parseInt(idArea));
                        erpSolicitudDeFondos.setAutorizaArea(autorizaArea);
                        erpSolicitudDeFondos.setUsuarioAutorizaArea(usuarioAutorizaArea);
                        erpSolicitudDeFondos.setFechaAutorizoArea(formatFecha.parse(fechaAutorizaArea));
                        erpSolicitudDeFondos.setMotRechazo(motivoRechazo);
                        
                        

                        
                        if(tipo.equalsIgnoreCase("R")){

                             erpSolicitudDeFondos.setBanco(banco);
                             erpSolicitudDeFondos.setMonedaDePago(moneda);
                             erpSolicitudDeFondos.setImporteDePago(new BigDecimal(importe));
                             erpSolicitudDeFondos.setNumeroDeOperacion(numeroOperacion);
                             erpSolicitudDeFondos.setFechaDeRetiro(formatFecha.parse(fechaRetiro));
                             

                        }
                        if(tipo.equalsIgnoreCase("C")){

                             erpSolicitudDeFondos.setCasaDeCambio(Integer.parseInt(casa));
                             erpSolicitudDeFondos.setMonedaDePago(monedaPago);
                             erpSolicitudDeFondos.setImporteDePago(new BigDecimal(importePago));

                        }



                         boolean result = erpSolicitudDeFondosDao.update(erpSolicitudDeFondos);


                        if(result == false){

                            rq.setSuccess(false);
                            rq.setMsg("Error al guardar Datos");

                        }else{

                            rq.setSuccess(true);
                            rq.setMsg(""+idSolicitud);

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
    
      @RequestMapping(value = "/autorizaSolFon", method = RequestMethod.POST)
    public @ResponseBody
    ResponseQuery autorizaSolFon( String data, WebRequest webRequest, Model model) throws IOException {

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
            List<SolicitudFolioDTO> lista = mapper.readValue(data,
                    mapper.getTypeFactory().constructCollectionType(List.class,
                            SolicitudFolioDTO.class));

            if (lista.isEmpty()) {
                rq.setSuccess(false);
                rq.setData(null);
                rq.setMsg("Error no existen datos que guardar");
                return rq;
            }

  
            Iterator<SolicitudFolioDTO> it = lista.iterator();
            ErpSolicitudDeFondos erpSolicitudDeFondos = new ErpSolicitudDeFondos();
            ErpSolicitudDeFondosId erpSolicitudDeFondosId = new ErpSolicitudDeFondosId();

          
            String idCaja2 = "";
            int banderaGe = 0 ;
            while (it.hasNext()) {
//                 System.out.println("-------------------------------------------------------------------");
                 SolicitudFolioDTO ss = it.next();
                 
                
                int numDir = 0;


                erpSolicitudDeFondosId.setCompania(compania);

                 erpSolicitudDeFondosId.setIdSolicitud(Integer.parseInt(ss.idSolicitud));
                 erpSolicitudDeFondos.setId(erpSolicitudDeFondosId);
                
                 
                 erpSolicitudDeFondos.setUsuarioAutoriza(usuario);
                 erpSolicitudDeFondos.setFechaDeAutorizacion(new Date());
                 //erpSolicitudDeFondos.setFechaDeSolicitud(new Date());
                 
                 
                 if(ss.tipoFolio.equalsIgnoreCase("R")){
                     
                     erpSolicitudDeFondos.setSolicitarACaja("5");
                     erpSolicitudDeFondos.setBanco(ss.bancoFolio);
                     erpSolicitudDeFondos.setImporteRequerido(new BigDecimal(ss.importeRequerido));
                     
                     erpSolicitudDeFondosDao.updateEstatusAutorizarPag(erpSolicitudDeFondos);
                 }else{
                     erpSolicitudDeFondos.setSolicitarACaja("3");
                     erpSolicitudDeFondosDao.updateEstatusAutorizar(erpSolicitudDeFondos);
                 
                 }

                  



             
            }
            

            
            if (banderaGe == 1){
            
                
                    rq.setSuccess(true);
                    rq.setData(null);
                    rq.setMsg("Registros guardado correctamente");
              
                
            }else{
                
                rq.setSuccess(true);
                rq.setData(null);
                rq.setMsg("Error al guardar registro");
                
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
    
      @RequestMapping(value = "/rechazarSolFon", method = RequestMethod.POST)
    public @ResponseBody
    ResponseQuery rechazarSolFon( String data, WebRequest webRequest, Model model) throws IOException {

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
            List<SolicitudFolioDTO> lista = mapper.readValue(data,
                    mapper.getTypeFactory().constructCollectionType(List.class,
                            SolicitudFolioDTO.class));

            if (lista.isEmpty()) {
                rq.setSuccess(false);
                rq.setData(null);
                rq.setMsg("Error no existen datos que guardar");
                return rq;
            }

  
            Iterator<SolicitudFolioDTO> it = lista.iterator();
            ErpSolicitudDeFondos erpSolicitudDeFondos = new ErpSolicitudDeFondos();
            ErpSolicitudDeFondosId erpSolicitudDeFondosId = new ErpSolicitudDeFondosId();

          
            String idCaja2 = "";
            int banderaGe = 0 ;
            while (it.hasNext()) {
//                 System.out.println("-------------------------------------------------------------------");
                 SolicitudFolioDTO ss = it.next();
                 
                
                int numDir = 0;


                erpSolicitudDeFondosId.setCompania(compania);

                 erpSolicitudDeFondosId.setIdSolicitud(Integer.parseInt(ss.idSolicitud));
                 erpSolicitudDeFondos.setId(erpSolicitudDeFondosId);
                
                 erpSolicitudDeFondos.setSolicitarACaja("4");
                 erpSolicitudDeFondos.setUsuarioAutoriza(usuario);
                 erpSolicitudDeFondos.setFechaDeAutorizacion(new Date());
                 //erpSolicitudDeFondos.setFechaDeSolicitud(new Date());

                  erpSolicitudDeFondosDao.updateEstatusAutorizar(erpSolicitudDeFondos);



             
            }
            

            
            if (banderaGe == 1){
            
                
                    rq.setSuccess(true);
                    rq.setData(null);
                    rq.setMsg("Registros guardado correctamente");
              
                
            }else{
                
                rq.setSuccess(true);
                rq.setData(null);
                rq.setMsg("Error al guardar registro");
                
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
    
    
    
        @RequestMapping(value = "/saveCajaGeneralSol", method = RequestMethod.POST)
    public @ResponseBody
    ResponseQuery saveCajaGeneralSol( WebRequest webRequest, Model model) throws ParseException {

        ResponseQuery rq = new ResponseQuery();
        String compania = model.asMap().get("compania").toString();       
        String usuario = model.asMap().get("usuario").toString();         
     
           String id = webRequest.getParameter("ID").toString();
           String descripcion = webRequest.getParameter("DESCRIPCION").toString();
           String ctoCto = webRequest.getParameter("CTO_CTO").toString();
           String idTipoNegocio = webRequest.getParameter("ID_TIPO_NEGOCIO").toString();
           String idPaisCxp = webRequest.getParameter("ID_PAIS_CXP").toString();
           String importe = webRequest.getParameter("IMPORTE").toString();
           String moneda = webRequest.getParameter("MONEDA").toString();
           String fecha = webRequest.getParameter("FECHA").toString();
           String banco = webRequest.getParameter("BANCO").toString();
           String estatus = webRequest.getParameter("ESTATUS").toString();
            String idSolicitud = webRequest.getParameter("cboIdsolGeneralSol").toString();
           
   
          
                System.out.println("id"+id);
                System.out.println("descripcion"+descripcion);
                
 
                                                          
        try {
            
            

            SimpleDateFormat formatFecha = new SimpleDateFormat("dd/MM/yyyy");
      

               System.out.println("fecha: "+formatFecha.parse(fecha));
     
            
            ErpCajaChica erpCajaChica = new ErpCajaChica();
            ErpCajaChicaId erpCajaChicaId = new ErpCajaChicaId();
            
            ErpSolicitudDeFondos erpSolicitudDeFondos = new ErpSolicitudDeFondos();
            ErpSolicitudDeFondosId erpSolicitudDeFondosId = new ErpSolicitudDeFondosId();

            
            int numDir = 0;
            
            
            

            erpCajaChicaId.setCompania(compania);
            
            int idAu = erpCajaChicaDao.getMaxErpCajaChicaId(erpCajaChicaId);
            
             erpCajaChicaId.setId(idAu);
             erpCajaChica.setId(erpCajaChicaId);
             erpCajaChica.setIdPaisCxp(idPaisCxp);
             erpCajaChica.setIdTipoNegocio(idTipoNegocio);
             erpCajaChica.setImporte(new BigDecimal(importe));
             erpCajaChica.setImporteRestante(new BigDecimal(importe));
             erpCajaChica.setMoneda(moneda);
             erpCajaChica.setCtocto(ctoCto);
             erpCajaChica.setDescripcion(descripcion);
             erpCajaChica.setFecha(formatFecha.parse(fecha));
             erpCajaChica.setUsuarioCap(usuario);
             erpCajaChica.setBanco(banco);
             erpCajaChica.setEstatus("1");
             
             ErpCajaChicaId result = erpCajaChicaDao.save(erpCajaChica);
             
               erpSolicitudDeFondosId.setCompania(compania);

                 erpSolicitudDeFondosId.setIdSolicitud(Integer.parseInt(idSolicitud));
                 erpSolicitudDeFondos.setId(erpSolicitudDeFondosId);
                
               

                  erpSolicitudDeFondosDao.updateIdCaja(erpSolicitudDeFondos,idAu);

             
                     
            if(result == null){
                
                rq.setSuccess(false);
                rq.setMsg("Error al guardar Datos");
                
            }else{
                
                rq.setSuccess(true);
                rq.setMsg(""+idAu);
            
            }
             
                       
            
            
           
             
        } catch (Exception e) {
             rq.setSuccess(false);
            rq.setMsg("Error al guardar  cxp");
            e.printStackTrace();
            return rq;
        }

        return rq;

    }     
     public void revisarDirectorio(String dirCompania) {

        File file = new File(dirCompania);
//        System.out.println("Directory" + dirOutFileDocument + dirCompania + "/");
        if (!file.exists()) {
            if (file.mkdirs()) {
//                System.out.println("Directory is created!");
            } else {
//                System.out.println("Failed to create directory!");
            }
        }

    }
    
       @RequestMapping(value = "/pago/verific")
    public String verificSystemUser(String compania, String user, String verific, WebRequest webRequest, Model model) {

        boolean isSave = false;

        ResponseQuery rq = new ResponseQuery();

        boolean result = false;

        try {
            ObjectMapper mapper = new ObjectMapper();
            DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
            mapper.setDateFormat(df);
            
            
             Map ver = new HashMap();
                        ver.put("compania", compania);
                        ver.put("codigo", verific);

                        List listTitulo = processDao.getMapResult("BuscaCodigoVerific", ver);
                        
                        if (listTitulo.isEmpty()){
                            
                            return "init/VerificIncorrect";
                            
                            
                        }else{
                        
                               Map cor = (HashMap) listTitulo.get(0);
                            String comision = cor.get("COMISION").toString();
                            
                            
                            Querys que = new Querys();
                            String store = que.getSQL("ActualizaCodigoVia");
                             Map param = new HashMap();
                                  param.put("compania", compania);
                                  param.put("codigo", verific);
                                  param.put("usuario",user );
                                  param.put("comision",comision );



                             int  val = processDao.execute(store, param);
                             
                             return "init/VerificCorrect";
                        
                        }


        } catch (Exception e) {
            isSave = false;
            e.printStackTrace();
            return "init/VerificIncorrect";
        }

      

    }
    
    
      @RequestMapping(value = "/saveFolioSolicitud", method = RequestMethod.POST)
    public @ResponseBody
    ResponseQuery saveFolioSolicitud( String data, WebRequest webRequest, Model model) throws IOException {

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
            List<SolicitudFolioDTO> lista = mapper.readValue(data,
                    mapper.getTypeFactory().constructCollectionType(List.class,
                            SolicitudFolioDTO.class));

            if (lista.isEmpty()) {
                rq.setSuccess(false);
                rq.setData(null);
                rq.setMsg("Error no existen datos que guardar");
                return rq;
            }            Iterator<SolicitudFolioDTO> it = lista.iterator();
           ErpFoliosSolFondos folios = new ErpFoliosSolFondos();
           ErpFoliosSolFondosId foliosId = new ErpFoliosSolFondosId();
           
           ErpSolicitudDeFondos f = new ErpSolicitudDeFondos();
           ErpSolicitudDeFondosId fId = new  ErpSolicitudDeFondosId();
           
         
           foliosId.setCompania(compania);
           
           int geFolio = erpFoliosSolFondosDao.getMaxFoliosSolFondosId(foliosId);
           
           int banderaGe = 0;
         
           
           System.out.println("GENRADOR DE FOLIO:"+geFolio);
           
           int folioPagos = geFolio;
          
            while (it.hasNext()) {
//                 System.out.println("-------------------------------------------------------------------");
                 SolicitudFolioDTO ss = it.next();
               
                 foliosId.setFolio(geFolio);
                 foliosId.setIdSolicitud(Integer.parseInt(ss.idSolicitud));
                 folios.setId(foliosId);
                 folios.setUsuario(usuario);
                 folios.setEstatus("FG");
                 folios.setTipoPago(ss.tipoFolio);
                 
                 erpFoliosSolFondosDao.save(folios);
                 
                 fId.setCompania(compania);
                 fId.setIdSolicitud(Integer.parseInt(ss.idSolicitud));
                 f.setId(fId);
                 f.setFolioPagos(folioPagos);
                 f.setSolicitarACaja("7");
                 
                 erpSolicitudDeFondosDao.updateEstatusEnFolio(f);
                 
//    
//             
//             

            }
            
            

            
      //     if (result2 == true){ 
             rq.setSuccess(true);
             rq.setData(null);
             rq.setMsg("Solicitudes enviadas con folio: "+geFolio);
      //     }
            
        }catch(Exception e){
            rq.setSuccess(false);
            rq.setData(null);
            rq.setMsg("Error al guardar");
           return rq;
        }
        
        return rq;
    }
    
      @RequestMapping(value = "/actualizaFolioSolicitud", method = RequestMethod.POST)
    public @ResponseBody
    ResponseQuery actualizaFolioSolicitud( String data,String folio, WebRequest webRequest, Model model) throws IOException {

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
            List<SolicitudFolioDTO> lista = mapper.readValue(data,
                    mapper.getTypeFactory().constructCollectionType(List.class,
                            SolicitudFolioDTO.class));

            if (lista.isEmpty()) {
                rq.setSuccess(false);
                rq.setData(null);
                rq.setMsg("Error no existen datos que guardar");
                return rq;
            }            Iterator<SolicitudFolioDTO> it = lista.iterator();
           ErpFoliosSolFondos folios = new ErpFoliosSolFondos();
           ErpFoliosSolFondosId foliosId = new ErpFoliosSolFondosId();
           
           ErpSolicitudDeFondos f = new ErpSolicitudDeFondos();
           ErpSolicitudDeFondosId fId = new  ErpSolicitudDeFondosId();
           
         
           foliosId.setCompania(compania);
           
          // int geFolio = erpFoliosSolFondosDao.getMaxFoliosSolFondosId(foliosId);
           
           int banderaGe = 0;
         
           
        //   System.out.println("GENRADOR DE FOLIO:"+geFolio);
           
          
            while (it.hasNext()) {
//                 System.out.println("-------------------------------------------------------------------");
                 SolicitudFolioDTO ss = it.next();
               
                 foliosId.setFolio(Integer.parseInt(folio));
                 foliosId.setIdSolicitud(Integer.parseInt(ss.idSolicitud));
                 folios.setId(foliosId);
                 folios.setUsuario(usuario);
                 folios.setEstatus("FG");
                 folios.setTipoPago(ss.tipoFolio);
                 
                 erpFoliosSolFondosDao.save(folios);
                 
                 fId.setCompania(compania);
                 fId.setIdSolicitud(Integer.parseInt(ss.idSolicitud));
                 f.setId(fId);
                 f.setFolioPagos(Integer.parseInt(folio));
                 f.setSolicitarACaja("7");
                 
                 erpSolicitudDeFondosDao.updateEstatusEnFolio(f);
                 
//    
//             
//             

            }
            
            

            
      //     if (result2 == true){ 
             rq.setSuccess(true);
             rq.setData(null);
             rq.setMsg("Solicitudes enviadas con folio: "+folio);
      //     }
            
        }catch(Exception e){
            rq.setSuccess(false);
            rq.setData(null);
            rq.setMsg("Error al guardar");
           return rq;
        }
        
        return rq;
    }

        @RequestMapping(value = "/actualizaFolioSolicitudAPago", method = RequestMethod.POST)
    public @ResponseBody
    ResponseQuery actualizaFolioSolicitudAPago( String data,String folio, WebRequest webRequest, Model model) throws IOException {

        ResponseQuery rq = new ResponseQuery();
        System.out.println("data Pagos creacion:"+data);
        
         int index = data.indexOf("[");
        if (index == -1) {
            data = "[" + data + "]";
        }


            String compania = model.asMap().get("compania").toString();
            String usuario = model.asMap().get("usuario").toString();
            ObjectMapper mapper = new ObjectMapper();
          
        try{
            List<FoliosSolFondosDTO> lista = mapper.readValue(data,
                    mapper.getTypeFactory().constructCollectionType(List.class,
                            FoliosSolFondosDTO.class));

            if (lista.isEmpty()) {
                rq.setSuccess(false);
                rq.setData(null);
                rq.setMsg("Error no existen datos que guardar");
                return rq;
            }            Iterator<FoliosSolFondosDTO> it = lista.iterator();
           ErpFoliosSolFondos folios = new ErpFoliosSolFondos();
           ErpFoliosSolFondosId foliosId = new ErpFoliosSolFondosId();
           
           ErpSolicitudDeFondos f = new ErpSolicitudDeFondos();
           ErpSolicitudDeFondosId fId = new  ErpSolicitudDeFondosId();
           
         
           foliosId.setCompania(compania);
           
          // int geFolio = erpFoliosSolFondosDao.getMaxFoliosSolFondosId(foliosId);
           
           int banderaGe = 0;
         
           
        //   System.out.println("GENRADOR DE FOLIO:"+geFolio);
           SimpleDateFormat formatFecha = new SimpleDateFormat("dd/MM/yyyy");
          
            while (it.hasNext()) {
                 System.out.println("-------------------------------1-----------------------------------");
                 FoliosSolFondosDTO ss = it.next();
                 
                 if(ss.tipoFolio.equalsIgnoreCase("R")){
                  foliosId.setFolio(Integer.parseInt(ss.folioPagos));
                    foliosId.setIdSolicitud(Integer.parseInt(ss.idSolicitud));
                    folios.setId(foliosId);
                    folios.setUsuario(ss.usuarioSolicito);
                    folios.setUsuarioAutorizo(usuario);
                    folios.setFechaAutorizacion(new Date());
                    folios.setEstatus("PAG");
                    folios.setTipoPago(ss.tipoFolio);

                    erpFoliosSolFondosDao.update(folios);

                    fId.setCompania(compania);
                    fId.setIdSolicitud(Integer.parseInt(ss.idSolicitud));
                    f.setId(fId);                 
                    f.setSolicitarACaja("5");
                    f.setBanco(ss.banco);
                    f.setFolioPagos(Integer.parseInt(ss.folioPagos));
                    f.setFechaDeAutorizacion(formatFecha.parse(ss.fechaRetiro));
                    f.setImporteRequerido(new BigDecimal(ss.importeRequerido));

                    erpSolicitudDeFondosDao.updateEstatusAutorizarPag(f);
                 
                 }
               
                if(ss.tipoFolio.equalsIgnoreCase("C")){
                 foliosId.setFolio(Integer.parseInt(ss.folioPagos));
                 foliosId.setIdSolicitud(Integer.parseInt(ss.idSolicitud));
                 folios.setId(foliosId);
                 folios.setUsuario(ss.usuarioSolicito);
                 folios.setUsuarioAutorizo(usuario);
                 folios.setFechaAutorizacion(new Date());
                 folios.setEstatus("ET");
                 folios.setTipoPago(ss.tipoFolio);
                 
                 erpFoliosSolFondosDao.update(folios);
                 System.out.println("-------------------------------2-----------------------------------");
                 fId.setCompania(compania);
                 fId.setIdSolicitud(Integer.parseInt(ss.idSolicitud));
                 f.setId(fId);
                 f.setFolioPagos(Integer.parseInt(ss.folioPagos));
                 f.setSolicitarACaja("3");
                 
                 erpSolicitudDeFondosDao.updateEstatusEnFolio(f);
                 
                }
                 
                 
//    
//             
//             

            }
            
            

            
      //     if (result2 == true){ 
             rq.setSuccess(true);
             rq.setData(null);
             rq.setMsg("Solicitudes enviadas con folio: "+folio);
      //     }
            
        }catch(Exception e){
            rq.setSuccess(false);
            rq.setData(null);
            rq.setMsg("Error al guardar");
           return rq;
        }
        
        return rq;
    }
    
            @RequestMapping(value = "/retiraFolioSolicitudAPago", method = RequestMethod.POST)
    public @ResponseBody
    ResponseQuery retiraFolioSolicitudAPago( String data,String folio, WebRequest webRequest, Model model) throws IOException {

        ResponseQuery rq = new ResponseQuery();
        System.out.println("data quita solicitud:"+data);
        
         int index = data.indexOf("[");
        if (index == -1) {
            data = "[" + data + "]";
        }


            String compania = model.asMap().get("compania").toString();
            String usuario = model.asMap().get("usuario").toString();
            ObjectMapper mapper = new ObjectMapper();
          
        try{
            List<FoliosSolFondosDTO> lista = mapper.readValue(data,
                    mapper.getTypeFactory().constructCollectionType(List.class,
                            FoliosSolFondosDTO.class));

            if (lista.isEmpty()) {
                rq.setSuccess(false);
                rq.setData(null);
                rq.setMsg("Error no existen datos que guardar");
                return rq;
            }            Iterator<FoliosSolFondosDTO> it = lista.iterator();
           ErpFoliosSolFondos folios = new ErpFoliosSolFondos();
           ErpFoliosSolFondosId foliosId = new ErpFoliosSolFondosId();
           
           ErpSolicitudDeFondos f = new ErpSolicitudDeFondos();
           ErpSolicitudDeFondosId fId = new  ErpSolicitudDeFondosId();
           
         
           foliosId.setCompania(compania);
           
          // int geFolio = erpFoliosSolFondosDao.getMaxFoliosSolFondosId(foliosId);
           
           int banderaGe = 0;
         
           
        //   System.out.println("GENRADOR DE FOLIO:"+geFolio);
           
          
            while (it.hasNext()) {
                 System.out.println("-------------------------------1-----------------------------------");
                 FoliosSolFondosDTO ss = it.next();
               
                 foliosId.setFolio(Integer.parseInt(ss.folioPagos));
                 foliosId.setIdSolicitud(Integer.parseInt(ss.idSolicitud));
                 folios.setId(foliosId);
                 
                 
                 erpFoliosSolFondosDao.delete(folios);
                 System.out.println("-------------------------------2-----------------------------------");
                 fId.setCompania(compania);
                 fId.setIdSolicitud(Integer.parseInt(ss.idSolicitud));
                 f.setId(fId);
                 f.setSolicitarACaja("2");
                 
                 erpSolicitudDeFondosDao.updateEstatusEnFolioRetira(f);
                 
//    
//             
//             

            }
            
            

            
      //     if (result2 == true){ 
             rq.setSuccess(true);
             rq.setData(null);
             rq.setMsg("Solicitud Actualizada");
      //     }
            
        }catch(Exception e){
            rq.setSuccess(false);
            rq.setData(null);
            rq.setMsg("Error al guardar");
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
            List<CompCajaDTO> lista = (List)mapper.readValue(data, mapper
              .getTypeFactory().constructCollectionType(List.class, CompCajaDTO.class));

            if (lista.isEmpty())
            {
              rq.setSuccess(false);
              rq.setData(null);
              rq.setMsg("Error no existen datos que guardar");
              return rq;
            }


          
           Iterator<CompCajaDTO> it = lista.iterator();
          
            while (it.hasNext())
            {
              System.out.println("-------------------------------------------------------------------");
              CompCajaDTO ss = (CompCajaDTO)it.next();
              
              String store = "";
              
                    Querys que = new Querys();
                    //if (ss.tipoGasto.equalsIgnoreCase("C")){
                        store = que.getSQL("VistoBuenosCaja");
                    //}else{
                    //    store = que.getSQL("VistoBuenosVia");
                    //}
                    
                     Map param = new HashMap();
                          param.put("compania", compania);
                          param.put("comision", ss.comision);
                          param.put("sec", ss.secComp);
                          param.put("secCaja", ss.secCaja);
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


    public void setErpCajaChicaDao(ErpCajaChicaDao erpCajaChicaDao) {
        this.erpCajaChicaDao = erpCajaChicaDao;
    }

    public void setErpCajaChicaDetDao(ErpCajaChicaDetDao erpCajaChicaDetDao) {
        this.erpCajaChicaDetDao = erpCajaChicaDetDao;
    }

    public void setProcessDao(ProcessDao processDao) {
        this.processDao = processDao;
    }

    public void setErpSolicitudDeFondosDao(ErpSolicitudDeFondosDao erpSolicitudDeFondosDao) {
        this.erpSolicitudDeFondosDao = erpSolicitudDeFondosDao;
    }

    public void setMailVerificacionVia(MailVerificacionVia mailVerificacionVia) {
        this.mailVerificacionVia = mailVerificacionVia;
    }

    public void setErpFoliosSolFondosDao(ErpFoliosSolFondosDao erpFoliosSolFondosDao) {
        this.erpFoliosSolFondosDao = erpFoliosSolFondosDao;
    }

    public void setErpCajaGastosDao(ErpCajaGastosDao erpCajaGastosDao) {
        this.erpCajaGastosDao = erpCajaGastosDao;
    }
    
    
    
    
    
    
}
