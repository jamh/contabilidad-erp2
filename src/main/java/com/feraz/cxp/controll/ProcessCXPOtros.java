/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.feraz.cxp.controll;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.feraz.cxp.dao.ErpCpOtrasDao;
import com.feraz.cxp.dao.ErpCpOtrasDetDao;
import com.feraz.cxp.dao.ErpDetImpuestosBrDao;
import com.feraz.cxp.dao.ErpNotasExtDao;
import com.feraz.cxp.dao.ErpProvPagoUnicoDao;
import com.feraz.cxp.dao.ErpSeguiDocumentosDao;
import com.feraz.cxp.dto.CxpDtoDetComp;
import com.feraz.cxp.dto.CxpOtrasDTO;
import com.feraz.cxp.dto.CxpOtrasDetDTO;
import com.feraz.cxp.dto.ImpuestosBrDTO;
import com.feraz.cxp.dto.InfoFactOtrasDTO;
import com.feraz.cxp.dto.NotasExtDTO;
import com.feraz.cxp.dto.ProvPagoUnicoDTO;
import com.feraz.cxp.dto.ViaticosDTO;
import com.feraz.cxp.model.ErpCpOtras;
import com.feraz.cxp.model.ErpCpOtrasDet;
import com.feraz.cxp.model.ErpCpOtrasDetId;
import com.feraz.cxp.model.ErpCpOtrasId;
import com.feraz.cxp.model.ErpCxpFolios;
import com.feraz.cxp.model.ErpCxpFoliosId;
import com.feraz.cxp.model.ErpDetImpuestosBr;
import com.feraz.cxp.model.ErpDetImpuestosBrId;
import com.feraz.cxp.model.ErpNotasExt;
import com.feraz.cxp.model.ErpNotasExtId;
import com.feraz.cxp.model.ErpProvPagoUnico;
import com.feraz.cxp.model.ErpProvPagoUnicoId;
import com.feraz.cxp.model.ErpSeguiDocumentos;
import com.feraz.cxp.model.ErpSeguiDocumentosId;
import com.feraz.facturas.webcontrolfe.dto.FileInfo;
import com.feraz.ordencompra.dao.ErpOrdenCompraDao;
import com.feraz.ordencompra.dao.ErpOrdenCompraDetDao;
import com.feraz.ordencompra.dao.ErpOrdenXFacturaDao;
import com.feraz.ordencompra.model.ErpOrdenCompra;
import com.feraz.ordencompra.model.ErpOrdenCompraDet;
import com.feraz.ordencompra.model.ErpOrdenCompraDetId;
import com.feraz.ordencompra.model.ErpOrdenCompraId;
import com.feraz.ordencompra.model.ErpOrdenXFactura;
import com.feraz.ordencompra.model.ErpOrdenXFacturaId;
import com.feraz.polizas3.model.FileUploadBean;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import org.jamh.data.process.ProcessDao;
import org.jamh.wf.json.model.ResponseQuery;
import org.jamh.wf.json.model.ResponseQuery2;
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

/**
 *
 * @author Administrador
 */

@Controller
@RequestMapping("/CXP")
@SessionAttributes({"compania", "usuario"})
public class ProcessCXPOtros {
    

    @Value("${document.file.dirOutDocumentOtras}")
    private String dirOutFileDocument;
    @Value("${document.file.dirURLOutDocumentOtras}")
    private String dirUrlOutDocument;
    @Value("${document.file.maxZiseOtras}")
    private String maxSizeDocument;
    
     @Value("${document.file.dirOutDocumentOrden}")
    private String dirOutFileDocumentOrden;
    @Value("${document.file.dirURLOutDocumentOrden}")
    private String dirUrlOutDocumentOrden;
    @Value("${document.file.maxZiseOrden}")
    private String maxSizeDocumentOrden;

    private ErpCpOtrasDao ErpCpOtrasDao;
    private ProcessDao processDao;
    private ErpNotasExtDao erpNotasExtDao;
    private ErpDetImpuestosBrDao erpDetImpuestosBrDao;
    
      private String msgErr;
    private String extension = "";
    //private String nombreArc = "";
    private String path;
    private String pathXML;
    private String url;
    
      @Autowired
    private ErpOrdenCompraDao erpOrdenCompraDao;
    
    @Autowired
    private ErpOrdenCompraDetDao erpOrdenCompraDetDao;
    
    
    @Autowired
    private ErpOrdenXFacturaDao erpOrdenXFacturaDao;
    
    @Autowired
    private ErpProvPagoUnicoDao erpProvPagoUnicoDao;
    
    @Autowired
    private ErpCpOtrasDetDao erpCpOtrasDetDao;
    
    @Autowired
    private ErpSeguiDocumentosDao erpSeguiDocumentosDao;
    
    @RequestMapping(value = "/saveOtrosCXP", method = RequestMethod.POST)
    public @ResponseBody
    ResponseQuery saveCXPOtros( WebRequest webRequest, Model model,FileUploadBean uploadItemLineaCap) {

        ResponseQuery rq = new ResponseQuery();
        String compania = model.asMap().get("compania").toString();       
        String usuario = model.asMap().get("usuario").toString();         
                         
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
           String conceptoCxp = webRequest.getParameter("CONCEPTO_CXP").toString();
           String fechaV = webRequest.getParameter("FECHA_V").toString();
           String tipoCambio = webRequest.getParameter("TIPO_CAMBIO").toString();
           String descripcion = webRequest.getParameter("DESCRIPCION_OTRAS").toString();
           String facturable = webRequest.getParameter("cboFacturable").toString();
           String idProveedor = webRequest.getParameter("ID_PROVEEDOR").toString();
           
           String tipoGastoCXP = webRequest.getParameter("TIPO_GASTO_CXP").toString();
           
           String fechaConta = webRequest.getParameter("FECHA_CONTAB_PROV_OTROSform").toString();
           
           String retencionRenta = webRequest.getParameter("RETENCION_RENTA").toString();         
           String retencionIva= webRequest.getParameter("RETENCION_IVA").toString();
           
           String folioGasto= webRequest.getParameter("FOLIO_GASTO").toString();
           
           String idServicio= webRequest.getParameter("ID_SERVICIO").toString();
           
           String pais= webRequest.getParameter("PAIS_CXP_FORM").toString();
           String tipoNeg= webRequest.getParameter("TIPO_NEG_CXP").toString();
           
           
           String archivoOtras= webRequest.getParameter("ARCHIVO").toString();
           String rutaOtras= webRequest.getParameter("RUTA").toString();
           
           String tipoFactura= webRequest.getParameter("cboProductoServicio").toString();
           String fechaCashFlow = webRequest.getParameter("FECHA_V_CASH").toString();
           
           String referenciaPagoOtras = webRequest.getParameter("REFERENCIA_PAGO_OTRAS").toString();
           
           
   
                   
          
                System.out.println("beneficiario"+beneficiario);
                System.out.println("cboBeneficiario"+cboBeneficiario);
                
                
                
                
                                                          
        try {
            
            
            
            
            
            SimpleDateFormat formatFecha = new SimpleDateFormat("dd/MM/yyyy");
      
                
                 
            
                     String calendar2 = formatFecha2(formatFecha.parse(fecha), "yyyy");
                            String periodo2 = formatFecha2(formatFecha.parse(fecha), "MM");
                            
                                 Map periodoCont = new HashMap();
                               periodoCont.put("compania", compania);
                               periodoCont.put("calendario", calendar2);
                               periodoCont.put("periodo", periodo2);
               
                                 List periodoContaList = processDao.getMapResult("BuscaPeriodoCXP", periodoCont);
                   
								
								
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
            
            
            if (numero.isEmpty() == true || numero.length() == 0){
            
                
            if(!folioGasto.equalsIgnoreCase("")){
             Map folCont = new HashMap();
                 String buscaFolioControl = "";
               
               folCont.put("compania", compania);
               folCont.put("folioGasto", folioGasto);
               folCont.put("idProveedor", idProveedor);
               
                   List listaFolCont = processDao.getMapResult("BuscaFolioControl", folCont);
                   
                   if(!listaFolCont.isEmpty()){
                     
                  
                     
                     rq.setSuccess(false);
                    rq.setMsg("El folio de control ya existe: "+folioGasto);
                    return rq;
                 }
                   
                
            }
            
            
           
            erpCpOtrasId.setCompania(compania);
            
            int id = ErpCpOtrasDao.getMaxErpCpOtrasId(erpCpOtrasId);
            
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
            
            if (retencionRenta.equalsIgnoreCase("")){
                 erpCpOtras.setRetencionRenta(Double.parseDouble("0"));
            }else{
              erpCpOtras.setRetencionRenta(Double.parseDouble(retencionRenta));
            }
            
            if (retencionIva.equalsIgnoreCase("")){
                 erpCpOtras.setRetencionIva(Double.parseDouble("0"));
            }else{
              erpCpOtras.setRetencionIva(Double.parseDouble(retencionIva));
            }
            erpCpOtras.setTotal(Double.parseDouble(total));
            erpCpOtras.setMontoRestProvision(Double.parseDouble(total));
            erpCpOtras.setCtoCxp(ctoCxp);
            erpCpOtras.setConceptoCxp(conceptoCxp);
            erpCpOtras.setFacturable(facturable);
            erpCpOtras.setUsuario(usuario);
            erpCpOtras.setIdProveedor(idProveedor);
            erpCpOtras.setTipoFactura(tipoFactura);
            
            if (tipoGasto.equalsIgnoreCase("W")){
            
                erpCpOtras.setEstatusCxp("PROV");
            }
            
         
            
            erpCpOtras.setFolioGasto(folioGasto);
            
            if(!idServicio.equalsIgnoreCase("")){
                    erpCpOtras.setIdServicio(Integer.parseInt(idServicio));
            }
                    erpCpOtras.setIdTipoNegocio(tipoNeg);
            
                    erpCpOtras.setIdPaisCxp(pais);
            
            
            if(!fechaV.equalsIgnoreCase("")){
            erpCpOtras.setFechaVencCxp(formatFecha.parse(fechaV));
            }
            
            if(!tipoGastoCXP.equalsIgnoreCase("")){
                
                erpCpOtras.setIdTipoGasto(Integer.parseInt(tipoGastoCXP));
            
            }
            
            if(!fechaConta.equalsIgnoreCase("")){
                
                erpCpOtras.setFechaContabProv(formatFecha.parse(fechaConta));
            
            }
            
            if(!fechaCashFlow.equalsIgnoreCase("")){
                
                erpCpOtras.setFechaCashFlow(formatFecha.parse(fechaCashFlow));
            
            }
            
            if(moneda.equalsIgnoreCase("MXN")){
                erpCpOtras.setTipoCambio("");
           
            }else{
                
                 erpCpOtras.setTipoCambio(tipoCambio);
                
            }
            
            erpCpOtras.setReferenciaPago(referenciaPagoOtras);
            
            
              
            MultipartFile file = uploadItemLineaCap.getFile();
            
            
            if (file != null){
            if (file.getSize() > 0) {
                inputStream = file.getInputStream();
                long maxSize = new Long(maxSizeDocument);
                if (file.getSize() > maxSize) {
                    //System.out.println("File Size:::" + file.getSize());
                     rq.setSuccess(false);
                     rq.setMsg("Archivo demasiado grande.");
                    return rq;
                }

                int i = file.getOriginalFilename().lastIndexOf('.');
                
                String extension = "";
                String nombreArc ="";
               
                    extension = file.getOriginalFilename().substring(i + 1);
                    nombreArc = file.getOriginalFilename().substring(0 , i);

                String hora = "" + System.currentTimeMillis();
                String path =dirOutFileDocument+compania+"/LineaCaptura"+"/"+numDir+"/"+nombreArc+"."+extension;
                erpCpOtras.setArchivo(nombreArc+"."+extension);
                erpCpOtras.setRuta(path);
                String url =dirUrlOutDocument+compania+"/LineaCaptura"+"/"+numDir+"/"+nombreArc+"."+extension;
               
                revisarDirectorioLineas(dirOutFileDocument+compania+"/LineaCaptura"+"/"+numDir+"/");
            
                outputStream = new FileOutputStream(path);

                int readBytes = 0;
                byte[] buffer = new byte[10000];
                while ((readBytes = inputStream.read(buffer, 0, 10000)) != -1) {
                    outputStream.write(buffer, 0, readBytes);
                }
                outputStream.close();
                inputStream.close();
            }
            }
            
            
            ErpCpOtrasId result = ErpCpOtrasDao.save(erpCpOtras);
            
            if(result == null){
                
                rq.setSuccess(false);
                rq.setMsg("Error al guardar Datos");
                
            }else{
                
                rq.setSuccess(true);
                rq.setMsg(""+id);
            
            }
            
            }else{
                
                if(!folioGasto.equalsIgnoreCase("")){
             Map folCont = new HashMap();
                 String buscaFolioControl = "";
               
               folCont.put("compania", compania);
               folCont.put("folioGasto", folioGasto);
               folCont.put("numero", numero);
                folCont.put("idProveedor", idProveedor);
               
                   List listaFolCont = processDao.getMapResult("BuscaFolioControlUpdate", folCont);
                 
                   System.out.println(listaFolCont);
                   
                   if(!listaFolCont.isEmpty()){
                     
                  
                     
                     rq.setSuccess(false);
                    rq.setMsg("El folio de control ya existe: "+folioGasto);
                    return rq;
                 }
                   
                 
            }
            erpCpOtrasId.setId(Integer.parseInt(numero));
            numDir = erpCpOtrasId.getId();
            erpCpOtrasId.setCompania(compania);
            erpCpOtras.setId(erpCpOtrasId);
            erpCpOtras.setRfc(rfcEmisor);
            erpCpOtras.setDescripcion(descripcion);
            erpCpOtras.setTipoGasto(tipoGasto);
             if(!fecha.equalsIgnoreCase("")){
            erpCpOtras.setFecha(formatFecha.parse(fecha));
             }
            erpCpOtras.setMoneda(moneda);
             if (beneficiario.equalsIgnoreCase("")){
                
              erpCpOtras.setBeneficiario(cboBeneficiario);
            
            }else{
                 erpCpOtras.setBeneficiario(beneficiario);
            }
            erpCpOtras.setImporte(Double.parseDouble(importe));
            erpCpOtras.setIva(Double.parseDouble(iva));
            
            if(!otrosImpuestos.equalsIgnoreCase("")){
                    erpCpOtras.setOtrosImpuestos(Double.parseDouble(otrosImpuestos));
            }
            
            if (tipoGasto.equalsIgnoreCase("W")){
            
                erpCpOtras.setEstatusCxp("PROV");
            }
            
            
            erpCpOtras.setTotal(Double.parseDouble(total));
            erpCpOtras.setMontoRestProvision(Double.parseDouble(total));
            erpCpOtras.setCtoCxp(ctoCxp);
            erpCpOtras.setConceptoCxp(conceptoCxp);
            erpCpOtras.setFacturable(facturable);
            erpCpOtras.setUsuario(usuario);
            erpCpOtras.setIdProveedor(idProveedor);
            erpCpOtras.setTipoFactura(tipoFactura);
            erpCpOtras.setFolioGasto(folioGasto);
            
            if(!idServicio.equalsIgnoreCase("")){
                    erpCpOtras.setIdServicio(Integer.parseInt(idServicio));
            }
            
             if (retencionRenta.equalsIgnoreCase("")){
                 erpCpOtras.setRetencionRenta(Double.parseDouble("0"));
            }else{
              erpCpOtras.setRetencionRenta(Double.parseDouble(retencionRenta));
            }
            
            if (retencionIva.equalsIgnoreCase("")){
                 erpCpOtras.setRetencionIva(Double.parseDouble("0"));
            }else{
              erpCpOtras.setRetencionIva(Double.parseDouble(retencionIva));
            }

            if(!fechaV.equalsIgnoreCase("")){
            erpCpOtras.setFechaVencCxp(formatFecha.parse(fechaV));
            }

             if(moneda.equalsIgnoreCase("MXN")){
                  System.out.println("tipo de cambio null");
                erpCpOtras.setTipoCambio("");
           
            }else{
                  erpCpOtras.setTipoCambio(tipoCambio);
             System.out.println("tipo de cambiodatos");
                
             }
             
             if(!tipoGastoCXP.equalsIgnoreCase("")){
                
                erpCpOtras.setIdTipoGasto(Integer.parseInt(tipoGastoCXP));
            
            }
            
            if(!fechaConta.equalsIgnoreCase("")){
                
                erpCpOtras.setFechaContabProv(formatFecha.parse(fechaConta));
            
            }
            
            if(!fechaCashFlow.equalsIgnoreCase("")){
                
                erpCpOtras.setFechaCashFlow(formatFecha.parse(fechaCashFlow));
            
            }
            
            erpCpOtras.setIdTipoNegocio(tipoNeg);
            
                    erpCpOtras.setIdPaisCxp(pais);
            
            erpCpOtras.setReferenciaPago(referenciaPagoOtras);
              
            MultipartFile file = uploadItemLineaCap.getFile();
           if (file != null){
            if (file.getSize() > 0) {
                inputStream = file.getInputStream();
                long maxSize = new Long(maxSizeDocument);
                if (file.getSize() > maxSize) {
                    //System.out.println("File Size:::" + file.getSize());
                     rq.setSuccess(false);
                     rq.setMsg("Archivo demasiado grande.");
                    return rq;
                }

                int i = file.getOriginalFilename().lastIndexOf('.');
                
//                System.out.println("Nombre:" + file.getOriginalFilename());
                String extension = "";
                String nombreArc ="";
               
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
                String path =dirOutFileDocument+compania+"/LineaCaptura"+"/"+numDir+"/"+nombreArc+"."+extension;
                erpCpOtras.setArchivo(nombreArc+"."+extension);
                erpCpOtras.setRuta(path);
               // polizasArchivos.setPath(path);
                String url =dirUrlOutDocument+compania+"/LineaCaptura"+"/"+numDir+"/"+nombreArc+"."+extension;
               // polizasArchivos.setUrl(url);
               
                revisarDirectorioLineas(dirOutFileDocument+compania+"/LineaCaptura"+"/"+numDir+"/");
            
                outputStream = new FileOutputStream(path);

                int readBytes = 0;
                byte[] buffer = new byte[10000];
                while ((readBytes = inputStream.read(buffer, 0, 10000)) != -1) {
                    outputStream.write(buffer, 0, readBytes);
                }
                outputStream.close();
                inputStream.close();
            }else{
                
                erpCpOtras.setArchivo(archivoOtras);
                erpCpOtras.setRuta(rutaOtras);
            }
            }
            
             
            
            
               boolean result = ErpCpOtrasDao.update(erpCpOtras);
               
               
            
            if(result == false){
                
                rq.setSuccess(false);
                rq.setMsg("Error al guardar Datos");
                
            }else{
                
                rq.setSuccess(true);
                rq.setMsg(numero);
            
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
    
        @RequestMapping(value = "/saveOtrosCXPPortal", method = RequestMethod.POST)
    public @ResponseBody
    ResponseQuery saveCXPOtrosPortal( WebRequest webRequest, Model model,FileUploadBean uploadItem) {

        ResponseQuery rq = new ResponseQuery();
        String compania = model.asMap().get("compania").toString();             
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
           String conceptoCxp = webRequest.getParameter("CONCEPTO_CXP").toString();
           String fechaV = webRequest.getParameter("FECHA_V").toString();
           String tipoCambio = webRequest.getParameter("TIPO_CAMBIO").toString();
           String descripcion = webRequest.getParameter("DESCRIPCION_OTRAS").toString();
           String facturable = webRequest.getParameter("cboFacturable").toString();
           String idCliente = webRequest.getParameter("ID_CLIENTE").toString();
           
           
          
                System.out.println("beneficiario"+beneficiario);
                System.out.println("cboBeneficiario"+cboBeneficiario);
                                                          
        try {
            
             MultipartFile file = uploadItem.getFile();

            InputStream inputStream = null;
            OutputStream outputStream = null;
            
            ErpCpOtras erpCpOtras = new ErpCpOtras();
            ErpCpOtrasId erpCpOtrasId = new ErpCpOtrasId();
            
            if (file.getSize() > 0) {
                inputStream = file.getInputStream();
                long maxSize = new Long(maxSizeDocument);
                if (file.getSize() > maxSize) {
                    //System.out.println("File Size:::" + file.getSize());
                     rq.setSuccess(false);
                     rq.setMsg("Archivo demasiado grande.");
                    return rq;
                }

                int i = file.getOriginalFilename().lastIndexOf('.');
                
//                System.out.println("Nombre:" + file.getOriginalFilename());
                String extension = "";
                String nombreArc ="";
               
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
                String path =dirOutFileDocument+compania+"/"+nombreArc+"."+extension;
                erpCpOtras.setArchivo(nombreArc+"."+extension);
                erpCpOtras.setRuta(path);
               // polizasArchivos.setPath(path);
                String url =dirUrlOutDocument+compania+"/"+nombreArc+"."+extension;
               // polizasArchivos.setUrl(url);
               
                revisarDirectorio(compania);
            
                outputStream = new FileOutputStream(path);

                int readBytes = 0;
                byte[] buffer = new byte[10000];
                while ((readBytes = inputStream.read(buffer, 0, 10000)) != -1) {
                    outputStream.write(buffer, 0, readBytes);
                }
                outputStream.close();
                inputStream.close();
            }
            
            SimpleDateFormat formatFecha = new SimpleDateFormat("dd/MM/yyyy");
      
                 System.out.println("fecha: "+fecha);

                System.out.println("fecha: "+formatFecha.parse(fecha));
            
            
            
            System.out.println("numero: "+numero);
            //System.out.println("numero: "+numero.isEmpty());
            
           
           
            erpCpOtrasId.setCompania(compania);
            
            int id = ErpCpOtrasDao.getMaxErpCpOtrasId(erpCpOtrasId);
            
             
             erpCpOtrasId.setId(id);
            
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
            erpCpOtras.setCtoCxp(ctoCxp);
            erpCpOtras.setConceptoCxp(conceptoCxp);
            erpCpOtras.setFacturable(facturable);
            if(!fechaV.equalsIgnoreCase("")){
            erpCpOtras.setFechaVencCxp(formatFecha.parse(fechaV));
            }
            if(moneda.equalsIgnoreCase("MXN")){
                erpCpOtras.setTipoCambio("");
           
            }else{
                
                 erpCpOtras.setTipoCambio(tipoCambio);
                
            }
            erpCpOtras.setIdProveedor(idCliente);
            ErpCpOtrasId result = ErpCpOtrasDao.save(erpCpOtras);
            
               Querys que = new Querys();
            String store = que.getSQL("CONTA_INSERTA_FECHAVENC");
            
             Map parametros= new HashMap();
            
           
             
         
                  
             parametros.put("compania", compania);
             parametros.put("numero", id);
             parametros.put("idCliente", idCliente);
             parametros.put("origen", "OTR");

                
        
            
            if(result == null){
                
                rq.setSuccess(false);
                rq.setMsg("Error al guardar Datos");
                
            }else{
                
                processDao.execute(store, parametros);
                
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
    
             @RequestMapping(value = "/saveOtrosCXPPortalAereoVal", method = RequestMethod.POST)
    public @ResponseBody
    ResponseQuery saveCXPOtrosPortalAereoVal( WebRequest webRequest, Model model,FileUploadBean uploadItemPortal,FileUploadBean uploadItemPortal2) {

        ResponseQuery rq = new ResponseQuery();
        String compania = model.asMap().get("compania").toString();             
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
           String conceptoCxp = webRequest.getParameter("CONCEPTO_CXP").toString();
           String fechaV = webRequest.getParameter("FECHA_V").toString();
           String tipoCambio = webRequest.getParameter("TIPO_CAMBIO").toString();
           String descripcion = webRequest.getParameter("DESCRIPCION_OTRAS").toString();
           String facturable = webRequest.getParameter("cboFacturable").toString();
           String idCliente = webRequest.getParameter("ID_CLIENTE").toString();
           String ordenCompra = webRequest.getParameter("ORDEN_COMPRA_OTR").toString();
           String tipoFactura = webRequest.getParameter("TIPO_FACTURA").toString();
           String retencionRenta = webRequest.getParameter("RETENCION_RENTA").toString();         
           String retencionIva= webRequest.getParameter("RETENCION_IVA").toString();
           String folioGasto= webRequest.getParameter("FOLIO_GASTO").toString();
          
           
          
                System.out.println("beneficiario"+beneficiario);
                System.out.println("cboBeneficiario"+cboBeneficiario);
                                                          
        try {
            
            
            
            
            if(!folioGasto.equalsIgnoreCase("")){
             Map folCont = new HashMap();
                 String buscaFolioControl = "";
               
               folCont.put("compania", compania);
               folCont.put("folioGasto", folioGasto);
               folCont.put("idProveedor", idCliente);
               
                   List listaFolCont = processDao.getMapResult("BuscaFolioControl", folCont);
                   
                   if(!listaFolCont.isEmpty()){
                     
                  
                     
                     rq.setSuccess(false);
                    rq.setMsg("El folio de control ya existe: "+folioGasto);
                    return rq;
                 }
                   
                
            }
            
            
            
            
            //if(tipoFactura.equalsIgnoreCase("0")){
            
              Map orden = new HashMap();
                 String buscaOrden = "";
               
               orden.put("compania", compania);
               orden.put("orden", ordenCompra);
               orden.put("idClienta", idCliente);
               
                   List listOrden = processDao.getMapResult("BuscaOrdenCompraIdExt", orden);
                 if(listOrden.isEmpty()){
                     
                  
                     
                     rq.setSuccess(false);
                    rq.setMsg("La orden de compra es invalida");
                    return rq;
                     
                     
                 
                 }
            
            //}
             MultipartFile file = uploadItemPortal.getFile();

            InputStream inputStream = null;
            OutputStream outputStream = null;
            
            ErpCpOtras erpCpOtras = new ErpCpOtras();
            ErpCpOtrasId erpCpOtrasId = new ErpCpOtrasId();
            
            if (file.getSize() > 0) {
                inputStream = file.getInputStream();
                long maxSize = new Long(6441889);
                System.out.println("File Size1:::" + file.getSize());
                if (file.getSize() > maxSize) {
                    System.out.println("File Size2:::" + file.getSize());
                     rq.setSuccess(false);
                     rq.setMsg("Archivo demasiado grande.");
                    return rq;
                }

                int i = file.getOriginalFilename().lastIndexOf('.');
                
                System.out.println("Nombre:" + file.getOriginalFilename());
                String extension = "";
                String nombreArc ="";
               
                    extension = file.getOriginalFilename().substring(i + 1);
                    nombreArc = file.getOriginalFilename().substring(0 , i);
                    
                     System.out.println("Archivo:"+nombreArc);
                    
                    if (nombreArc.indexOf("#") > 0){
                        
                        
                        rq.setSuccess(false);
                            rq.setMsg("El nombre del archivo no puede contener el caracter #");
                            return rq;
                    
                    }
                    
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
                String path =dirOutFileDocument+compania+"/"+nombreArc+"."+extension;
                erpCpOtras.setArchivo(nombreArc+"."+extension);
                erpCpOtras.setRuta(path);
               // polizasArchivos.setPath(path);
                String url =dirUrlOutDocument+compania+"/"+nombreArc+"."+extension;
               // polizasArchivos.setUrl(url);
               
                revisarDirectorio(compania);
            
                outputStream = new FileOutputStream(path);

                int readBytes = 0;
                byte[] buffer = new byte[10000];
                while ((readBytes = inputStream.read(buffer, 0, 10000)) != -1) {
                    outputStream.write(buffer, 0, readBytes);
                }
                outputStream.close();
                inputStream.close();
            }
            
            SimpleDateFormat formatFecha = new SimpleDateFormat("dd/MM/yyyy");
      
                 System.out.println("fecha: "+fecha);

                System.out.println("fecha: "+formatFecha.parse(fecha));
            
            
            
            System.out.println("numero: "+numero);
            //System.out.println("numero: "+numero.isEmpty());
            
           
           
            erpCpOtrasId.setCompania(compania);
            
            int id = ErpCpOtrasDao.getMaxErpCpOtrasId(erpCpOtrasId);
            
             
             erpCpOtrasId.setId(id);
            
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
                
               
             erpCpOtras.setFolioGasto(folioGasto);
           
            
            if (retencionRenta.equalsIgnoreCase("")){
                 erpCpOtras.setRetencionRenta(Double.parseDouble("0"));
            }else{
              erpCpOtras.setRetencionRenta(Double.parseDouble(retencionRenta));
            }
            
            if (retencionIva.equalsIgnoreCase("")){
                 erpCpOtras.setRetencionIva(Double.parseDouble("0"));
            }else{
              erpCpOtras.setRetencionIva(Double.parseDouble(retencionIva));
            }
   
            
            erpCpOtras.setImporte(Double.parseDouble(importe));
            erpCpOtras.setIva(Double.parseDouble(iva));
            if (otrosImpuestos.equalsIgnoreCase("")){
                 erpCpOtras.setOtrosImpuestos(Double.parseDouble("0"));
            }else{
              erpCpOtras.setOtrosImpuestos(Double.parseDouble(otrosImpuestos));
            }
            erpCpOtras.setTotal(Double.parseDouble(total));
            erpCpOtras.setCtoCxp(ctoCxp);
            erpCpOtras.setConceptoCxp(conceptoCxp);
            erpCpOtras.setFacturable(facturable);
            if(!fechaV.equalsIgnoreCase("")){
            erpCpOtras.setFechaVencCxp(formatFecha.parse(fechaV));
            }
            if(moneda.equalsIgnoreCase("MXN")){
                erpCpOtras.setTipoCambio("");
           
            }else{
                
                 erpCpOtras.setTipoCambio(tipoCambio);
                
            }
            erpCpOtras.setIdProveedor(idCliente);
            ErpCpOtrasId result = ErpCpOtrasDao.save(erpCpOtras);
            
               Querys que = new Querys();
            String store = que.getSQL("CONTA_INSERTA_FECHAVENC");
            
            
            
            
             Map parametros= new HashMap();
            
           
             
         
                  
             parametros.put("compania", compania);
             parametros.put("numero", id);
             parametros.put("idCliente", idCliente);
             parametros.put("origen", "OTR");
             //if(tipoFactura.equalsIgnoreCase("0")){
                
                MultipartFile file2 = uploadItemPortal2.getFile();

                FileInfo fi2 = generaArchivosOrden(file2, ordenCompra, compania,id);

                     if (!fi2.isIsSave()) {
                        rq.setMsg("Error al guardar el archivo de la orden de compra:" + msgErr);
                        rq.setSuccess(false);
                        return rq;
                    } 
             //}
            
            if(result == null){
                
                rq.setSuccess(false);
                rq.setMsg("Error al guardar Datos");
                
            }else{
                
                processDao.execute(store, parametros);
                
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
    
            @RequestMapping(value = "/saveOtrosCXPPortalAereo", method = RequestMethod.POST)
    public @ResponseBody
    ResponseQuery saveCXPOtrosPortalAereo( WebRequest webRequest, Model model,FileUploadBean uploadItemPortal,FileUploadBean uploadItemPortal2) {

        ResponseQuery rq = new ResponseQuery();
        String compania = model.asMap().get("compania").toString();             
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
           String conceptoCxp = webRequest.getParameter("CONCEPTO_CXP").toString();
           String fechaV = webRequest.getParameter("FECHA_V").toString();
           String tipoCambio = webRequest.getParameter("TIPO_CAMBIO").toString();
           String descripcion = webRequest.getParameter("DESCRIPCION_OTRAS").toString();
           String facturable = webRequest.getParameter("cboFacturable").toString();
           String idCliente = webRequest.getParameter("ID_CLIENTE").toString();
           String ordenCompra = webRequest.getParameter("ORDEN_COMPRA_OTR").toString();
           
           
          
                System.out.println("beneficiario"+beneficiario);
                System.out.println("cboBeneficiario"+cboBeneficiario);
                                                          
        try {
            
            
              Map orden = new HashMap();
                 String buscaOrden = "";
               
               orden.put("compania", compania);
               orden.put("orden", ordenCompra);
               orden.put("idClienta", idCliente);
               
                   List listOrden = processDao.getMapResult("BuscaOrdenCompraIdExt", orden);
                 if(listOrden.isEmpty()){
                     
                  
                     
                     rq.setSuccess(false);
                    rq.setMsg("La orden de compra es invalida");
                    return rq;
                     
                     
                 
                 }
            
            
             MultipartFile file = uploadItemPortal.getFile();

            InputStream inputStream = null;
            OutputStream outputStream = null;
            
            ErpCpOtras erpCpOtras = new ErpCpOtras();
            ErpCpOtrasId erpCpOtrasId = new ErpCpOtrasId();
            
            if (file.getSize() > 0) {
                inputStream = file.getInputStream();
                long maxSize = new Long(maxSizeDocument);
                if (file.getSize() > maxSize) {
                    //System.out.println("File Size:::" + file.getSize());
                     rq.setSuccess(false);
                     rq.setMsg("Archivo demasiado grande.");
                    return rq;
                }

                int i = file.getOriginalFilename().lastIndexOf('.');
                
//                System.out.println("Nombre:" + file.getOriginalFilename());
                String extension = "";
                String nombreArc ="";
               
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
                String path =dirOutFileDocument+compania+"/"+nombreArc+"."+extension;
                erpCpOtras.setArchivo(nombreArc+"."+extension);
                erpCpOtras.setRuta(path);
               // polizasArchivos.setPath(path);
                String url =dirUrlOutDocument+compania+"/"+nombreArc+"."+extension;
               // polizasArchivos.setUrl(url);
               
                revisarDirectorio(compania);
            
                outputStream = new FileOutputStream(path);

                int readBytes = 0;
                byte[] buffer = new byte[10000];
                while ((readBytes = inputStream.read(buffer, 0, 10000)) != -1) {
                    outputStream.write(buffer, 0, readBytes);
                }
                outputStream.close();
                inputStream.close();
            }
            
            SimpleDateFormat formatFecha = new SimpleDateFormat("dd/MM/yyyy");
      
                 System.out.println("fecha: "+fecha);

                System.out.println("fecha: "+formatFecha.parse(fecha));
            
            
            
            System.out.println("numero: "+numero);
            //System.out.println("numero: "+numero.isEmpty());
            
           
           
            erpCpOtrasId.setCompania(compania);
            
            int id = ErpCpOtrasDao.getMaxErpCpOtrasId(erpCpOtrasId);
            
             
             erpCpOtrasId.setId(id);
            
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
            erpCpOtras.setCtoCxp(ctoCxp);
            erpCpOtras.setConceptoCxp(conceptoCxp);
            erpCpOtras.setFacturable(facturable);
            if(!fechaV.equalsIgnoreCase("")){
            erpCpOtras.setFechaVencCxp(formatFecha.parse(fechaV));
            }
            if(moneda.equalsIgnoreCase("MXN")){
                erpCpOtras.setTipoCambio("");
           
            }else{
                
                 erpCpOtras.setTipoCambio(tipoCambio);
                
            }
            erpCpOtras.setIdProveedor(idCliente);
            ErpCpOtrasId result = ErpCpOtrasDao.save(erpCpOtras);
            
               Querys que = new Querys();
            String store = que.getSQL("CONTA_INSERTA_FECHAVENC");
            
             Map parametros= new HashMap();
            
           
             
         
                  
             parametros.put("compania", compania);
             parametros.put("numero", id);
             parametros.put("idCliente", idCliente);
             parametros.put("origen", "OTR");

                
            MultipartFile file2 = uploadItemPortal2.getFile();
             
            FileInfo fi2 = generaArchivosOrden(file2, ordenCompra, compania,id);
       
                 if (!fi2.isIsSave()) {
                    rq.setMsg("Error al guardar el archivo de la orden de compra:" + msgErr);
                    rq.setSuccess(false);
                    return rq;
                } 
                
            
            if(result == null){
                
                rq.setSuccess(false);
                rq.setMsg("Error al guardar Datos");
                
            }else{
                
                processDao.execute(store, parametros);
                
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
    
    
               @RequestMapping(value = "/saveOtrosCXPPortalAereoOrden", method = RequestMethod.POST)
    public @ResponseBody
    ResponseQuery saveCXPOtrosPortalAereoOrden( WebRequest webRequest, Model model,FileUploadBean uploadItemPortal,FileUploadBean uploadItemPortal2) {

        ResponseQuery rq = new ResponseQuery();
        String compania = model.asMap().get("compania").toString();             
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
           String conceptoCxp = webRequest.getParameter("CONCEPTO_CXP").toString();
           String fechaV = webRequest.getParameter("FECHA_V").toString();
           String tipoCambio = webRequest.getParameter("TIPO_CAMBIO").toString();
           String descripcion = webRequest.getParameter("DESCRIPCION_OTRAS").toString();
           String facturable = webRequest.getParameter("cboFacturable").toString();
           String idCliente = webRequest.getParameter("ID_CLIENTE").toString();
           String ordenCompra = webRequest.getParameter("ORDEN_COMPRA_OTR").toString();
           String linea = webRequest.getParameter("LINEA_OTR").toString();
           
           String retencionRenta = webRequest.getParameter("RETENCION_RENTA").toString();         
           String retencionIva= webRequest.getParameter("RETENCION_IVA").toString();
           String folioGasto= webRequest.getParameter("FOLIO_GASTO").toString();
          
                System.out.println("beneficiario"+beneficiario);
                System.out.println("cboBeneficiario"+cboBeneficiario);
                                                          
        try {
            
            
            if(!folioGasto.equalsIgnoreCase("")){
             Map folCont = new HashMap();
                 String buscaFolioControl = "";
               
               folCont.put("compania", compania);
               folCont.put("folioGasto", folioGasto);
               folCont.put("idProveedor", idCliente);
               
                   List listaFolCont = processDao.getMapResult("BuscaFolioControl", folCont);
                   
                   if(!listaFolCont.isEmpty()){
                     
                  
                     
                     rq.setSuccess(false);
                    rq.setMsg("El folio de control ya existe: "+folioGasto);
                    return rq;
                 }
                   
                
            }
            
            
              Map orden = new HashMap();
                 String buscaOrden = "";
               
               orden.put("compania", compania);
               orden.put("orden", ordenCompra);
               orden.put("idClienta", idCliente);
               
                   List listOrden = processDao.getMapResult("BuscaOrdenCompraIdExt", orden);
                 if(listOrden.isEmpty()){
                     
                  
                     
                     rq.setSuccess(false);
                    rq.setMsg("La orden de compra es invalida");
                    return rq;
                     
                     
                 
                 }
            
            
             MultipartFile file = uploadItemPortal.getFile();

            InputStream inputStream = null;
            OutputStream outputStream = null;
            
            ErpCpOtras erpCpOtras = new ErpCpOtras();
            ErpCpOtrasId erpCpOtrasId = new ErpCpOtrasId();
            
            if (file.getSize() > 0) {
                inputStream = file.getInputStream();
                long maxSize = new Long(maxSizeDocument);
                if (file.getSize() > maxSize) {
                    //System.out.println("File Size:::" + file.getSize());
                     rq.setSuccess(false);
                     rq.setMsg("Archivo demasiado grande.");
                    return rq;
                }

                int i = file.getOriginalFilename().lastIndexOf('.');
                
//                System.out.println("Nombre:" + file.getOriginalFilename());
                String extension = "";
                String nombreArc ="";
               
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
                String path =dirOutFileDocument+compania+"/"+nombreArc+"."+extension;
                erpCpOtras.setArchivo(nombreArc+"."+extension);
                erpCpOtras.setRuta(path);
               // polizasArchivos.setPath(path);
                String url =dirUrlOutDocument+compania+"/"+nombreArc+"."+extension;
               // polizasArchivos.setUrl(url);
               
                revisarDirectorio(compania);
            
                outputStream = new FileOutputStream(path);

                int readBytes = 0;
                byte[] buffer = new byte[10000];
                while ((readBytes = inputStream.read(buffer, 0, 10000)) != -1) {
                    outputStream.write(buffer, 0, readBytes);
                }
                outputStream.close();
                inputStream.close();
            }
            
            SimpleDateFormat formatFecha = new SimpleDateFormat("dd/MM/yyyy");
      
                 System.out.println("fecha: "+fecha);

                System.out.println("fecha: "+formatFecha.parse(fecha));
            
            
            
            System.out.println("numero: "+numero);
            //System.out.println("numero: "+numero.isEmpty());
            
           
           
            erpCpOtrasId.setCompania(compania);
            
            int id = ErpCpOtrasDao.getMaxErpCpOtrasId(erpCpOtrasId);
            
             
             erpCpOtrasId.setId(id);
            
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
            erpCpOtras.setFolioGasto(folioGasto);
            if (otrosImpuestos.equalsIgnoreCase("")){
                 erpCpOtras.setOtrosImpuestos(Double.parseDouble("0"));
            }else{
              erpCpOtras.setOtrosImpuestos(Double.parseDouble(otrosImpuestos));
            }
            
            if (retencionRenta.equalsIgnoreCase("")){
                 erpCpOtras.setRetencionRenta(Double.parseDouble("0"));
            }else{
              erpCpOtras.setRetencionRenta(Double.parseDouble(retencionRenta));
            }
            
            if (retencionIva.equalsIgnoreCase("")){
                 erpCpOtras.setRetencionIva(Double.parseDouble("0"));
            }else{
              erpCpOtras.setRetencionIva(Double.parseDouble(retencionIva));
            }
            erpCpOtras.setTotal(Double.parseDouble(total));
            erpCpOtras.setCtoCxp(ctoCxp);
            erpCpOtras.setConceptoCxp(conceptoCxp);
            erpCpOtras.setFacturable(facturable);
            if(!fechaV.equalsIgnoreCase("")){
            erpCpOtras.setFechaVencCxp(formatFecha.parse(fechaV));
            }
            if(moneda.equalsIgnoreCase("MXN")){
                erpCpOtras.setTipoCambio("");
           
            }else{
                
                 erpCpOtras.setTipoCambio(tipoCambio);
                
            }
            erpCpOtras.setIdProveedor(idCliente);
            ErpCpOtrasId result = ErpCpOtrasDao.save(erpCpOtras);
            
               Querys que = new Querys();
            String store = que.getSQL("CONTA_INSERTA_FECHAVENC");
            
             Map parametros= new HashMap();
            
           
             
         
                  
             parametros.put("compania", compania);
             parametros.put("numero", id);
             parametros.put("idCliente", idCliente);
             parametros.put("origen", "OTR");

              
            if(uploadItemPortal2 != null){ 
                MultipartFile file2 = uploadItemPortal2.getFile();

                FileInfo fi2 = generaArchivosOrden(file2, ordenCompra, compania,id);

                     if (!fi2.isIsSave()) {
                        rq.setMsg("Error al guardar el archivo de la orden de compra:" + msgErr);
                        rq.setSuccess(false);
                        return rq;
                    } 
            }else{
            
            
                generaArchivosOrdenRel(ordenCompra, compania,id);
                
            }

            
             generaRelacionOrden(compania,id,ordenCompra,linea, total);    
                 
            if(result == null){
                
                rq.setSuccess(false);
                rq.setMsg("Error al guardar Datos");
                
            }else{
                
                processDao.execute(store, parametros);
                
                
                   
            
             

            
            
                
                
                
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
    
        public boolean generaRelacionOrden(String compania,int data,String ordenCompra,String linea, String importe){
        
            ErpOrdenXFactura ordeFact = new ErpOrdenXFactura();
            ErpOrdenXFacturaId ordenFactId = new ErpOrdenXFacturaId();
            ErpOrdenCompraDet ordenDet = new ErpOrdenCompraDet();
            ErpOrdenCompraDetId ordenDetId = new ErpOrdenCompraDetId();
                  
            ErpOrdenCompra orden = new ErpOrdenCompra();
            ErpOrdenCompraId ordenId = new ErpOrdenCompraId();
            
             Map impoFact = new HashMap();
                   impoFact.put("compania", compania);
                   impoFact.put("numero", data);
               
                 // List listImpoFact = processDao.getMapResult("BuscaImportesFactura", impoFact);
                 // Map mapImpoFact = (HashMap) listImpoFact.get(0);
                  
        
            ordenFactId.setCompania(compania);
                  ordenFactId.setIdOrden(Integer.parseInt(ordenCompra));
                  ordenFactId.setIdOrdenDet(Integer.parseInt(linea));
                  ordenFactId.setNumeroFe(data);
                  ordeFact.setId(ordenFactId);
                  ordeFact.setOrigen("OTR");
                  ordeFact.setSubtotal(new BigDecimal(importe));
                  ordeFact.setIva(new BigDecimal(0));
                  ordeFact.setTotal(new BigDecimal(importe));
                 
                  erpOrdenXFacturaDao.save(ordeFact);
                  
                      Map ordenFact = new HashMap();
                   ordenFact.put("compania", compania);
                   ordenFact.put("idOrden", ordenCompra);
                   ordenFact.put("idOrdenDet", linea);
                   ordenFact.put("origen", "OTR");
                   
               
                  List listOrdenFact = processDao.getMapResult("BuscaImportesOrdenDet", ordenFact);
                  
                  List listOrdenFactM = processDao.getMapResult("BuscaImportesOrden", ordenFact);
//                   
                  Map ordenTotal = (HashMap) listOrdenFact.get(0);
                  Map ordenMTotal = (HashMap) listOrdenFactM.get(0);
                  
                   System.out.println("Obteniendo mapa");
             
                  
                  ordenDetId.setCompania(compania);
                  ordenDetId.setIdOrdenCompra(Integer.parseInt(ordenCompra));
                  ordenDetId.setLinea(Integer.parseInt(linea));
                  
                  ordenDet.setId(ordenDetId);
                  ordenDet.setTotal(new BigDecimal(ordenTotal.get("TOTAL").toString()));
                  ordenDet.setPrecioUnitario(new BigDecimal(ordenTotal.get("SUBTOTAL").toString()));
                  ordenDet.setIva(new BigDecimal(ordenTotal.get("IVA").toString()));
                  System.out.println("actualizando importes");
                  erpOrdenCompraDetDao.actualizaImportes(ordenDet);
                  
                  ordenId.setCompania(compania);
                  ordenId.setId(Integer.parseInt(ordenCompra));
                  orden.setId(ordenId);
                  orden.setTotalPendiente(new BigDecimal(ordenMTotal.get("TOTAL").toString()));
                  
                  erpOrdenCompraDao.actualizaImportes(orden);
        return true;
    
    }
    
            public FileInfo generaArchivosOrden(MultipartFile uploadItem, String orden, String dirCompania,int numero) {
        String fileName = null;
        String nombreArchivo = "";
        
        String nombreArc = "";
      //  filesordencompra
                
      
        //  ExtJSFormResult rq = new ExtJSFormResult();
        try {

            MultipartFile file = uploadItem;
            FileInfo fi = new FileInfo();

            InputStream inputStream = null;
            OutputStream outputStream = null;
            if (file.getSize() > 0) {
                inputStream = file.getInputStream();
                long maxSize = new Long(maxSizeDocument);
                if (file.getSize() > maxSize) {
                    msgErr = "Tamaño no valido";
                    //System.out.println("File Size:::" + file.getSize());
                    // isSave = false;
//                    rq.setMsg("Tamaño no valido");
//                    rq.setSuccess(isSave);
                    fi.setIsSave(false);
                    return fi;
                }

                int i = file.getOriginalFilename().lastIndexOf('.');
               // String hora = "" + System.currentTimeMillis();
//                System.out.println("Nombre:" + file.getOriginalFilename());
                extension = file.getOriginalFilename().substring(i + 1);
                nombreArc = file.getOriginalFilename().substring(0, i);
                fi.setFile(nombreArc  + "." + extension);
//                System.out.println("i:" + i);
//                System.out.println("exte:" + extension);
//                System.out.println("nombreArc:" + nombreArc);
//                System.out.println("exte:" + extension);
               
                if ( !extension.toLowerCase().trim().equals("pdf")) {

                    msgErr = "Archivo pdf no valido";

                    fi.setIsSave(false);
                    return fi;
                }

                path = dirOutFileDocumentOrden + dirCompania + "/" + orden + "/" + nombreArc + "." + extension;
                fi.setPath(path);
              
                url = dirOutFileDocumentOrden + nombreArc  + "." + extension;
                fi.setUrl(url);

                revisarDirectorioOrden(dirCompania,orden);
                outputStream = new FileOutputStream(path);

                int readBytes = 0;
                byte[] buffer = new byte[10000];
                while ((readBytes = inputStream.read(buffer, 0, 10000)) != -1) {
                    outputStream.write(buffer, 0, readBytes);
                }
                outputStream.close();
                inputStream.close();
            }
            
             Querys que = new Querys();
            String store = que.getSQL("InsertaArchivoOrden");
            
            Map archivoOrden = new HashMap();
            
            archivoOrden.put("COMPANIA", dirCompania);
            archivoOrden.put("ORDEN_COMPRA", orden);
            archivoOrden.put("ORIGEN", "OTR");
            archivoOrden.put("NUMERO", numero);
            archivoOrden.put("PATH", path);
            archivoOrden.put("URL", url);
            archivoOrden.put("NOMBRE",  nombreArc + "." + extension);
        
                 processDao.execute(store, archivoOrden); 
           

            fi.setIsSave(true);
            return fi;

        } catch (Exception e) {
            e.printStackTrace();
            FileInfo fi = new FileInfo();
            fi.setIsSave(false);
            return fi;
        }
    }
     public FileInfo generaArchivosOrdenRel(String orden, String dirCompania,int numero) {
        String fileName = null;
        String nombreArchivo = "";
        
        String nombreArc = "";
      //  filesordencompra
                
      
        //  ExtJSFormResult rq = new ExtJSFormResult();
        try {

            FileInfo fi = new FileInfo();

           
            
             Querys que = new Querys();
            String store = que.getSQL("InsertaArchivoOrden");
            
            Map archivoOrden = new HashMap();
            
            archivoOrden.put("COMPANIA", dirCompania);
            archivoOrden.put("ORDEN_COMPRA", orden);
            archivoOrden.put("ORIGEN", "OTR");
            archivoOrden.put("NUMERO", numero);
            archivoOrden.put("PATH", "");
            archivoOrden.put("URL", "");
            archivoOrden.put("NOMBRE",  "");
        
                 processDao.execute(store, archivoOrden); 
           

            fi.setIsSave(true);
            return fi;

        } catch (Exception e) {
            e.printStackTrace();
            FileInfo fi = new FileInfo();
            fi.setIsSave(false);
            return fi;
        }
    }
            
               public void revisarDirectorioOrden(String dirCompania,String orden) {

        File file = new File(dirOutFileDocumentOrden + dirCompania + "/" + orden + "/");
//        System.out.println("Directory" + dirOutFileDocument + dirCompania + "/");
        if (!file.exists()) {
            if (file.mkdirs()) {
//                System.out.println("Directory is created!");
            } else {
//                System.out.println("Failed to create directory!");
            }
        }

    }
    
    
            
    @RequestMapping(value = "/saveCompensacion", method = RequestMethod.POST)
    public @ResponseBody
    ResponseQuery saveCompensacion( String data, WebRequest webRequest, Model model) throws IOException {

        ResponseQuery rq = new ResponseQuery();
        
        
         int index = data.indexOf("[");
        if (index == -1) {
            data = "[" + data + "]";
        }

        System.out.println("data Pagos creacion compensacion:"+data);
        
            String compania = model.asMap().get("compania").toString();
            String usuario = model.asMap().get("usuario").toString();
            ObjectMapper mapper = new ObjectMapper();
          
        try{
             List<CxpOtrasDTO> lista = mapper.readValue(data,
                    mapper.getTypeFactory().constructCollectionType(List.class,
                            CxpOtrasDTO.class));

            if (lista.isEmpty()) {
                rq.setSuccess(false);
                rq.setData(null);
                rq.setMsg("Error no existen datos que guardar");
                return rq;
            }            
            
            Iterator<CxpOtrasDTO> it = lista.iterator();
          
         
            ErpCpOtras erpCpOtras = new ErpCpOtras();
            ErpCpOtrasId erpCpOtrasId = new ErpCpOtrasId();
          
            Querys que = new Querys();
            String store = que.getSQL("GENERA_COMPENSACION_INTERCAM");
          
            
            
            while (it.hasNext()) {
//                 System.out.println("-------------------------------------------------------------------");
                 CxpOtrasDTO ss = it.next();
                 
          
                
                 Map paramCompensacion = new HashMap();
                    paramCompensacion.put("compania",compania);
                    paramCompensacion.put("numero",ss.id);
                    
                int val = processDao.execute(store, paramCompensacion);

                     if (val <= 0) {

                }
            


            }
            
                    rq.setSuccess(true);
                    rq.setData(null);
                    rq.setMsg("Proceso generado correctamente");
            
            

         
            
        }catch(Exception e){
            rq.setSuccess(false);
            rq.setData(null);
            rq.setMsg("Error al guardar");
           return rq;
        }
        
        return rq;
    }
               
     @RequestMapping(value = "/saveReemComp", method = RequestMethod.POST)
    public @ResponseBody
    ResponseQuery saveRembComp( String data, @RequestParam("impReembolso") String impReembolso,WebRequest webRequest, Model model) throws IOException {

        ResponseQuery rq = new ResponseQuery();
        
        
         int index = data.indexOf("[");
        if (index == -1) {
            data = "[" + data + "]";
        }

        System.out.println("data Pagos creacion:"+data);
        
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
            }            
            
            Iterator<ViaticosDTO> it = lista.iterator();
          
         
            ErpCpOtras erpCpOtras = new ErpCpOtras();
            ErpCpOtrasId erpCpOtrasId = new ErpCpOtrasId();
          
            Querys que = new Querys();
            String store = que.getSQL("GENERA_REEMB_VIATIC");
          
            
            
            while (it.hasNext()) {
//                 System.out.println("-------------------------------------------------------------------");
                 ViaticosDTO ss = it.next();
                 
                 
                 erpCpOtrasId.setCompania(compania);
                
               // erpCpOtrasId.setCompania("TEMP");
            
                int id = ErpCpOtrasDao.getMaxErpCpOtrasId(erpCpOtrasId);

                 erpCpOtrasId.setId(id);

                erpCpOtras.setId(erpCpOtrasId);
                erpCpOtras.setRfc(ss.rfcEmisor);
                erpCpOtras.setTipoGasto("R");
              
              //  erpCpOtras.setFecha(formatFecha.parse(fecha));
               erpCpOtras.setFecha(new Date());
               
                erpCpOtras.setMoneda("MXN");
                erpCpOtras.setDescripcion("Reembolso por viatico");
              
                  erpCpOtras.setBeneficiario(ss.nombreEmisor);

             
                erpCpOtras.setImporte(Double.parseDouble(impReembolso));
                erpCpOtras.setIva(Double.parseDouble("0"));
                erpCpOtras.setOtrosImpuestos(Double.parseDouble("0"));
              
                erpCpOtras.setTotal(Double.parseDouble(impReembolso));
                erpCpOtras.setCtoCxp("");
                erpCpOtras.setConceptoCxp("");
                erpCpOtras.setFacturable("S");
               
                erpCpOtras.setFechaVencCxp(new Date());
                
                erpCpOtras.setTipoCambio("");

                ErpCpOtrasId result = ErpCpOtrasDao.save(erpCpOtras);

                if(result == null){

                    rq.setSuccess(false);
                    rq.setMsg("Error al guardar Datos");

                }else{

                    rq.setSuccess(true);
                    rq.setMsg("Se genero reembolso con numero: "+id);

                }
                
                 Map paramViatico = new HashMap();
                    paramViatico.put("COMPANIA",compania);
                 //   paramViatico.put("COMPANIA","TEMP");
                    paramViatico.put("COMISION",ss.numero);
                    paramViatico.put("ID",id);
                    paramViatico.put("IMPORTE",Double.parseDouble(impReembolso));
                    
                int val = processDao.execute(store, paramViatico);

                     if (val <= 0) {

                }
            


            }
            
            

         
            
        }catch(Exception e){
            rq.setSuccess(false);
            rq.setData(null);
            rq.setMsg("Error al guardar");
           return rq;
        }
        
        return rq;
    }
    
    @RequestMapping(value = "/saveReemCompOtras", method = RequestMethod.POST)
    public @ResponseBody
    ResponseQuery saveRembCompOtras( String data, @RequestParam("impReembolso") String impReembolso,WebRequest webRequest, Model model) throws IOException {

        ResponseQuery rq = new ResponseQuery();
        
        
         int index = data.indexOf("[");
        if (index == -1) {
            data = "[" + data + "]";
        }

        System.out.println("data Pagos creacion:"+data);
        
            String compania = model.asMap().get("compania").toString();
            String usuario = model.asMap().get("usuario").toString();
            ObjectMapper mapper = new ObjectMapper();
          
        try{
            List<CxpOtrasDTO> lista = mapper.readValue(data,
                    mapper.getTypeFactory().constructCollectionType(List.class,
                            CxpOtrasDTO.class));

            if (lista.isEmpty()) {
                rq.setSuccess(false);
                rq.setData(null);
                rq.setMsg("Error no existen datos que guardar");
                return rq;
            }            
            
            Iterator<CxpOtrasDTO> it = lista.iterator();
          
         
            ErpCpOtras erpCpOtras = new ErpCpOtras();
            ErpCpOtrasId erpCpOtrasId = new ErpCpOtrasId();
          
            Querys que = new Querys();
            String store = que.getSQL("GENERA_REEMB_OTRAS");
          
            
            
            while (it.hasNext()) {
//                 System.out.println("-------------------------------------------------------------------");
                 CxpOtrasDTO ss = it.next();
                 
                 
                 erpCpOtrasId.setCompania(compania);
                
               // erpCpOtrasId.setCompania("TEMP");
            
                int id = ErpCpOtrasDao.getMaxErpCpOtrasId(erpCpOtrasId);

                 erpCpOtrasId.setId(id);

                erpCpOtras.setId(erpCpOtrasId);
                erpCpOtras.setRfc(ss.rfcEmisor);
                erpCpOtras.setTipoGasto("R");
              
              //  erpCpOtras.setFecha(formatFecha.parse(fecha));
               erpCpOtras.setFecha(new Date());
               
                erpCpOtras.setMoneda("MXN");
                erpCpOtras.setDescripcion("Reembolso por viatico");
              
                  erpCpOtras.setBeneficiario(ss.nombreEmisor);

             
                erpCpOtras.setImporte(Double.parseDouble(impReembolso));
                erpCpOtras.setIva(Double.parseDouble("0"));
                erpCpOtras.setOtrosImpuestos(Double.parseDouble("0"));
              
                erpCpOtras.setTotal(Double.parseDouble(impReembolso));
                erpCpOtras.setCtoCxp("");
                erpCpOtras.setConceptoCxp("");
                erpCpOtras.setFacturable("S");
               
                erpCpOtras.setFechaVencCxp(new Date());
                
                erpCpOtras.setTipoCambio("");

                ErpCpOtrasId result = ErpCpOtrasDao.save(erpCpOtras);

                if(result == null){

                    rq.setSuccess(false);
                    rq.setMsg("Error al guardar Datos");

                }else{

                    rq.setSuccess(true);
                    rq.setMsg("Se genero reembolso con numero: "+id);

                }
                
                 Map paramViatico = new HashMap();
                    paramViatico.put("COMPANIA",compania);
                 //   paramViatico.put("COMPANIA","TEMP");
                    paramViatico.put("COMISION",ss.id);
                    paramViatico.put("ID",id);
                    paramViatico.put("IMPORTE",Double.parseDouble(impReembolso));
                    
                int val = processDao.execute(store, paramViatico);

                     if (val <= 0) {

                }
            


            }
            
            

         
            
        }catch(Exception e){
            rq.setSuccess(false);
            rq.setData(null);
            rq.setMsg("Error al guardar");
           return rq;
        }
        
        return rq;
    }
    
         @RequestMapping(value = "/relacionaNotasExt", method = RequestMethod.POST)
    public @ResponseBody
    ResponseQuery relacionaNotasExtranjeras( String data,String data2, WebRequest webRequest, Model model) throws IOException {

        ResponseQuery rq = new ResponseQuery();
   //     System.out.println("data factura cancelacion:"+data);
        SimpleDateFormat formatFecha = new SimpleDateFormat("dd/MM/yyyy");
         int index = data.indexOf("[");
        if (index == -1) {
            data = "[" + data + "]";
        }
        
          int index2 = data2.indexOf("[");
        if (index2 == -1) {
            data2 = "[" + data2 + "]";
        }
        
        System.out.println("---------------------------------------------------");
        
        System.out.println("data:  "+data);
        System.out.println("---------------------------------------------------");
        
        System.out.println("data2:  "+data2);
        System.out.println("---------------------------------------------------");
        
            String compania = model.asMap().get("compania").toString();
            String usuario = model.asMap().get("usuario").toString();
            ObjectMapper mapper = new ObjectMapper();
          
                   
        try{
            List<CxpOtrasDTO> lista = mapper.readValue(data,
                    mapper.getTypeFactory().constructCollectionType(List.class,
                            CxpOtrasDTO.class));
            
            List<NotasExtDTO> lista2 = mapper.readValue(data2,
                    mapper.getTypeFactory().constructCollectionType(List.class,
                            NotasExtDTO.class));

            if (lista.isEmpty()) {
                rq.setSuccess(false);
                rq.setData(null);
                rq.setMsg("Error no existen datos que guardar");
                return rq;
            }
            
            if (lista2.isEmpty()) {
                rq.setSuccess(false);
                rq.setData(null);
                rq.setMsg("Error no existen datos que guardar");
                return rq;
            }

  
           Iterator<CxpOtrasDTO> it = lista.iterator();
           Iterator<NotasExtDTO> it2 = lista2.iterator();
           
         
           boolean result = true;
           ErpNotasExtId result2 = null;
           
           ErpNotasExt not = new ErpNotasExt();
           ErpNotasExtId idNot = new ErpNotasExtId();
           
           
           ErpCpOtras otr = new ErpCpOtras();
           ErpCpOtrasId otrId = new ErpCpOtrasId();
           
           ErpSeguiDocumentos segui = new ErpSeguiDocumentos(); 
           ErpSeguiDocumentosId seguiId = new ErpSeguiDocumentosId();
           
         

            while (it.hasNext()) {
//                 System.out.println("-------------------------------------------------------------------");
                 CxpOtrasDTO ss = it.next();
                 
                 while (it2.hasNext()) {
//                 System.out.println("-------------------------------------------------------------------");
                      NotasExtDTO ss2 = it2.next();
                      
                      idNot.setCompania(ss2.compania);
                      idNot.setIdFactura(Integer.parseInt(ss.id));
                      idNot.setIdNota(Integer.parseInt(ss2.id));
                      not.setId(idNot);
                      not.setOrigen("OTR");
                      
                    //  int resultComp = actualizaImporteFactNota(ss2.compania,ss.id,ss2.id);
                    
                        BigDecimal impFactAct = null;
                        BigDecimal impNotaAct = null;
                    
                          Map NotaExt = new HashMap();
                            NotaExt.put("compania", compania);
                            NotaExt.put("id_factura", ss.id);
                            NotaExt.put("id_nota", ss2.id);


                           List listNotaExt = processDao.getMapResult("BuscaImpNotExt", NotaExt);

         //                   
                           Map notExt = (HashMap) listNotaExt.get(0);

                           System.out.println(notExt.get("REST_FACT_NOT").toString());
                           System.out.println(notExt.get("TOTAL_FACT").toString());

                           BigDecimal nota = new BigDecimal(notExt.get("REST_FACT_NOT").toString());
                           BigDecimal fact = new BigDecimal(notExt.get("TOTAL_FACT").toString());
                           
                           BigDecimal pago = new BigDecimal(notExt.get("TOTAL_FACT").toString());

                           if (nota.equals(new BigDecimal(0))){

                                rq.setSuccess(false);
                                rq.setData(null);
                                rq.setMsg("Error al guardar relacion, la nota tiene un saldo de 0.");
                           }

                           if (fact.equals(new BigDecimal(0))){

                                rq.setSuccess(false);
                                rq.setData(null);
                                rq.setMsg("Error al guardar relacion, la factura ya esta saldada");
                           }


                           BigDecimal impActualiza = fact.subtract(nota);

                           if (impActualiza.compareTo(BigDecimal.ZERO) <= 0){

                                impFactAct = BigDecimal.ZERO;
                                impNotaAct = fact;
                                pago= fact;

                               System.out.println("Importe factura: "+impFactAct);
                               System.out.println("Importe Nota: "+impNotaAct);


                           }else{


                                impFactAct = impActualiza;
                                impNotaAct = nota;
                                pago= nota;

                               System.out.println("Importe factura: "+impFactAct);
                               System.out.println("Importe Nota: "+impNotaAct);




                           }
                  
                     
                           not.setImpAplicaFact(impFactAct);
                           not.setImpAplicaNota(impNotaAct);
                           not.setImpAntFact(fact);
                           
                      erpNotasExtDao.save(not);
                      
                       seguiId.setCompania(ss.compania);
                 seguiId.setNumFe(Integer.parseInt(ss.id));
                 seguiId.setOrigen("P");
                 seguiId.setSerie("A");
                 seguiId.settDoc("OTR");
                
                 int secS = erpSeguiDocumentosDao.getMaxId(seguiId);
        //         System.out.println("pagos sec "+sec);
                 seguiId.setSec(secS);
                 
                 segui.setId(seguiId);
                 //segui.setBanco(ss.banco);
//                 if (ss.folio == null){
//                     
//                   segui.setFolio(Integer.parseInt(ss.numFe));
//                     
//                 }else{
                  segui.setFolio(Integer.parseInt(ss.id));
                  segui.setTipo("NC" + "-" + ss.id);
                // }
                 segui.setImporteOperacion(pago);
                 //segui.setMoneda(ss.);
                 segui.setObservaciones("Pagos de nota de credito");
                 segui.setParidad(new BigDecimal(1));
                 segui.setTotPesos(pago);
                 //segui.setUsuario(usuario);
                 //segui.settOperacion(ss.tOperacionDet);
                 //segui.setCuentaBanco(ss.cuentaBanco);
                 DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
                 Date date = new Date();
                
                 segui.setFechaPagoCxpFe(dateFormat.parse(dateFormat.format(date)));
                 
                 ErpSeguiDocumentosId result3 = erpSeguiDocumentosDao.save(segui);
                   
                   
                    Map pagoAcu = new HashMap();
             
               pagoAcu.put("compania", ss.compania);
               pagoAcu.put("numero", ss.id);
               
                   List listpagoAcu= processDao.getMapResult("BuscaPagosTotExt", pagoAcu);
                   
                    Map pagSum = (HashMap) listpagoAcu.get(0);
                    
                    double sumPagos = new BigDecimal(pagSum.get("SUM_IMPORTE").toString()).doubleValue();
                   
                     int pagAcumuTot = Double.compare(sumPagos,Double.parseDouble(ss.total));
                      
                      otrId.setCompania(compania);
                      otrId.setId(Integer.parseInt(ss.id));
                      otr.setId(otrId);
                      otr.setTotal(impFactAct.doubleValue());
                     
                          if(pagAcumuTot > 0) {
                             otr.setEstatusCxp("PAG");
                             otr.setPagoAcumuladoCXP(new BigDecimal(pagSum.get("SUM_IMPORTE").toString()));
                          }
                          else if(pagAcumuTot < 0) {
                             otr.setEstatusCxp("PAR");

                             otr.setPagoAcumuladoCXP(new BigDecimal(pagSum.get("SUM_IMPORTE").toString()));
                          }
                          else {
                             otr.setEstatusCxp("PAG");
                             otr.setPagoAcumuladoCXP(new BigDecimal(pagSum.get("SUM_IMPORTE").toString()));
                          }
                 
                      
                      
              
                      ErpCpOtrasDao.updateErpCpOtrasSaldoNotasExt(otr);
                 
                 
                 }
                 
                

            }
            
          
            
            if (result == true ){
            
                
                    rq.setSuccess(true);
                    rq.setData(null);
                    rq.setMsg("Se guardo la relacion correctamente");
              
                
            }else{
                
                rq.setSuccess(true);
                rq.setData(null);
                rq.setMsg("Error al guardar relacion");
                
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
    
             @RequestMapping(value = "/relacionaDelNotasExt", method = RequestMethod.POST)
    public @ResponseBody
    ResponseQuery relacionaDelNotasExtranjeras( String data,String data2, WebRequest webRequest, Model model) throws IOException {

        ResponseQuery rq = new ResponseQuery();
   //     System.out.println("data factura cancelacion:"+data);
        SimpleDateFormat formatFecha = new SimpleDateFormat("dd/MM/yyyy");
         int index = data.indexOf("[");
        if (index == -1) {
            data = "[" + data + "]";
        }
        
          int index2 = data2.indexOf("[");
        if (index2 == -1) {
            data2 = "[" + data2 + "]";
        }
        
        System.out.println("---------------------------------------------------");
        
        System.out.println("data:  "+data);
        System.out.println("---------------------------------------------------");
        
        System.out.println("data2:  "+data2);
        System.out.println("---------------------------------------------------");
        
            String compania = model.asMap().get("compania").toString();
            String usuario = model.asMap().get("usuario").toString();
            ObjectMapper mapper = new ObjectMapper();
          
                   
        try{
            List<CxpOtrasDTO> lista = mapper.readValue(data,
                    mapper.getTypeFactory().constructCollectionType(List.class,
                            CxpOtrasDTO.class));
            
            List<NotasExtDTO> lista2 = mapper.readValue(data2,
                    mapper.getTypeFactory().constructCollectionType(List.class,
                            NotasExtDTO.class));

            if (lista.isEmpty()) {
                rq.setSuccess(false);
                rq.setData(null);
                rq.setMsg("Error no existen datos que guardar");
                return rq;
            }
            
            if (lista2.isEmpty()) {
                rq.setSuccess(false);
                rq.setData(null);
                rq.setMsg("Error no existen datos que guardar");
                return rq;
            }

  
           Iterator<CxpOtrasDTO> it = lista.iterator();
           Iterator<NotasExtDTO> it2 = lista2.iterator();
           
         
           boolean result = true;
           ErpNotasExtId result2 = null;
           
           ErpNotasExt not = new ErpNotasExt();
           ErpNotasExtId idNot = new ErpNotasExtId();
           
           ErpCpOtras otr = new ErpCpOtras();
           ErpCpOtrasId otrId = new ErpCpOtrasId();
         

            while (it.hasNext()) {
//                 System.out.println("-------------------------------------------------------------------");
                 CxpOtrasDTO ss = it.next();
                 
                 while (it2.hasNext()) {
//                 System.out.println("-------------------------------------------------------------------");
                      NotasExtDTO ss2 = it2.next();
                      
                      idNot.setCompania(ss2.compania);
                      idNot.setIdFactura(Integer.parseInt(ss.id));
                      idNot.setIdNota(Integer.parseInt(ss2.id));
                      not.setId(idNot);
                      not.setOrigen("OTR");
                      
                       Map NotaExtDel = new HashMap();
                            NotaExtDel.put("compania", compania);
                            NotaExtDel.put("id_factura", ss.id);
                            NotaExtDel.put("id_nota", ss2.id);


                           List listNotaExtDel = processDao.getMapResult("BuscaImpFactDelNot", NotaExtDel);

         //                   
                           Map notExtDel = (HashMap) listNotaExtDel.get(0);

                           System.out.println(notExtDel.get("IMPORTE_FACT_REST").toString());

                      erpNotasExtDao.eliminaNotas(not);
                      
                      otrId.setCompania(compania);
                      otrId.setId(Integer.parseInt(ss.id));
                      otr.setId(otrId);
                      otr.setTotal(Double.parseDouble(notExtDel.get("IMPORTE_FACT_REST").toString()));
                      
                      ErpCpOtrasDao.updateErpCpOtrasSaldoNotasExt(otr);
                 
                 
                 }
                 
                

            }
            
          
            
            if (result == true ){
            
                
                    rq.setSuccess(true);
                    rq.setData(null);
                    rq.setMsg("Se elimino la relacion correctamente");
              
                
            }else{
                
                rq.setSuccess(true);
                rq.setData(null);
                rq.setMsg("Error al guardar relacion");
                
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
    
    
     @RequestMapping(value = "/savePagoUnicoCXP", method = RequestMethod.POST)
    public @ResponseBody
    ResponseQuery saveCXPPagoUnico( WebRequest webRequest, Model model,FileUploadBean uploadItemLineaCap) {

        ResponseQuery rq = new ResponseQuery();
        String compania = model.asMap().get("compania").toString();       
        String usuario = model.asMap().get("usuario").toString();         
         System.out.println("compania:"+compania);  
         
        
                  
           String numero = webRequest.getParameter("ID_MOV").toString();
           String moneda = webRequest.getParameter("MONEDA").toString();
           String tipoCambio = webRequest.getParameter("TIPO_CAMBIO").toString();
           String beneficiario = webRequest.getParameter("BENEFICIARIO").toString();
           String rfc = webRequest.getParameter("RFC").toString();
           String importe = webRequest.getParameter("IMPORTE").toString();
           String banco = webRequest.getParameter("BANCO").toString();
           String cuentaBancaria = webRequest.getParameter("CUENTA_BANCARIA").toString();
           String cuentaClabe = webRequest.getParameter("CLABE").toString();
           String descripcion = webRequest.getParameter("DESCRIPCION").toString();
           
           
           
           
           
           System.out.println("id: "+numero);
           
           System.out.println("moneda: "+moneda);
          

           
        try {
            
            SimpleDateFormat formatFecha = new SimpleDateFormat("dd/MM/yyyy");
      
             
                 

            ErpProvPagoUnico erpProvPagoUnico = new ErpProvPagoUnico();
            ErpProvPagoUnicoId erpProvPagoUnicoId = new ErpProvPagoUnicoId();
            
            ErpProvPagoUnicoId resultS = null;
            Boolean resultU = false;
            
            int numDir = 0;
            
            
            if (numero.isEmpty() == true || numero.length() == 0){
                
                
                erpProvPagoUnicoId.setCompania(compania);
                int id = erpProvPagoUnicoDao.getMaxErpProvPagoUnicoId(erpProvPagoUnicoId);
                erpProvPagoUnicoId.setIdMov(id);
                erpProvPagoUnico.setId(erpProvPagoUnicoId);
                erpProvPagoUnico.setBanco(banco);
                erpProvPagoUnico.setCuentaClabe(cuentaClabe);
                erpProvPagoUnico.setDescripcion(descripcion);
                erpProvPagoUnico.setImporte(new BigDecimal(importe));
                erpProvPagoUnico.setMoneda(moneda);
                erpProvPagoUnico.setNombre(beneficiario);
                erpProvPagoUnico.setNumeroCuenta(cuentaBancaria);
                erpProvPagoUnico.setRfc(rfc);
                if(moneda.equalsIgnoreCase("MXN")){
                    erpProvPagoUnico.setTipoCambio(BigDecimal.ZERO);

                }else{

                     erpProvPagoUnico.setTipoCambio(new BigDecimal(tipoCambio));

                }
                
                erpProvPagoUnico.setUsuario(usuario);
                
                
                resultS = erpProvPagoUnicoDao.save(erpProvPagoUnico);
            
           
                    if(resultS == null){

                        rq.setSuccess(false);
                        rq.setMsg("Error al guardar Datos");

                    }else{

                        rq.setSuccess(true);
                        rq.setMsg(""+id);

                    }
            
            }else{
                
                erpProvPagoUnicoId.setCompania(compania);
                erpProvPagoUnicoId.setIdMov(Integer.parseInt(numero));
                erpProvPagoUnico.setId(erpProvPagoUnicoId);
                erpProvPagoUnico.setBanco(banco);
                erpProvPagoUnico.setCuentaClabe(cuentaClabe);
                erpProvPagoUnico.setDescripcion(descripcion);
                erpProvPagoUnico.setImporte(new BigDecimal(importe));
                erpProvPagoUnico.setMoneda(moneda);
                erpProvPagoUnico.setNombre(beneficiario);
                erpProvPagoUnico.setNumeroCuenta(cuentaBancaria);
                erpProvPagoUnico.setRfc(rfc);
                if(moneda.equalsIgnoreCase("MXN")){
                    erpProvPagoUnico.setTipoCambio(BigDecimal.ZERO);

                }else{

                     erpProvPagoUnico.setTipoCambio(new BigDecimal(tipoCambio));

                }
                
                erpProvPagoUnico.setUsuario(usuario);
                
                
                resultU = erpProvPagoUnicoDao.update(erpProvPagoUnico);
            
               
            
                    if(resultU == false){

                        rq.setSuccess(false);
                        rq.setMsg("Error al guardar Datos");

                    }else{

                        rq.setSuccess(true);
                        rq.setMsg(numero);

                    }
            
            
            
            }
            
            
           
             
        } catch (Exception e) {
             rq.setSuccess(false);
            rq.setMsg("Error al guardar gasto");
            e.printStackTrace();
            return rq;
        }

        return rq;

    }
    
         @RequestMapping(value = "/deleteProvPagoUnico", method = RequestMethod.POST)
    public @ResponseBody
    ResponseQuery deletePagoUnico( String data, WebRequest webRequest, Model model) throws IOException {

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
            List<ProvPagoUnicoDTO> lista = mapper.readValue(data,
                    mapper.getTypeFactory().constructCollectionType(List.class,
                            ProvPagoUnicoDTO.class));

            if (lista.isEmpty()) {
                rq.setSuccess(false);
                rq.setData(null);
                rq.setMsg("Error no existen datos que guardar");
                return rq;
            }

  
            Iterator<ProvPagoUnicoDTO> it = lista.iterator();
            ErpProvPagoUnico erpProvPagoUnico = new ErpProvPagoUnico();
            ErpProvPagoUnicoId erpProvPagoUnicoId = new ErpProvPagoUnicoId();
            
          
          
            int banderaGe = 0 ;
            while (it.hasNext()) {
//                 System.out.println("-------------------------------------------------------------------");
                 ProvPagoUnicoDTO ss = it.next();
                 
                 erpProvPagoUnicoId.setCompania(ss.compania);
                 erpProvPagoUnicoId.setIdMov(Integer.parseInt(ss.idMov));
                 erpProvPagoUnico.setId(erpProvPagoUnicoId);
                 
                 erpProvPagoUnicoDao.delete(erpProvPagoUnico);
               
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
    
           @RequestMapping(value = "/saveComprobacion", method = RequestMethod.POST)
    public @ResponseBody
    ResponseQuery saveComprobacion( WebRequest webRequest, Model model) {

        ResponseQuery rq = new ResponseQuery();
        String compania = model.asMap().get("compania").toString();             
         System.out.println("compania:"+compania);  
         

                  
           String formaComp = webRequest.getParameter("FORMA_COMPRUEBA").toString();
           String bancoPagoComp = webRequest.getParameter("BANCO_PAGO_COMP").toString();
           String moneda = webRequest.getParameter("MONEDA").toString();
           String importeComp = webRequest.getParameter("IMPORTE_COMP").toString();
           String secComp = webRequest.getParameter("SEC_COMP").toString();
           String idComp = webRequest.getParameter("ID_COMP").toString();
           String fechaComp = webRequest.getParameter("FECHA_COMP").toString();
           String tipoNegCxpComp = webRequest.getParameter("TIPO_NEG_CXP_COMP").toString();
           String paisCxpFormComp = webRequest.getParameter("PAIS_CXP_FORM_COMP").toString();
           
                   
          
                System.out.println("formaComp"+formaComp);
                System.out.println("bancoPagoComp"+bancoPagoComp);
                                                          
        try {
            
         
        
            
               Querys que = new Querys();
            String store = que.getSQL("CONTA_INSERTA_COMP_CXP");
            
            
            
            
             Map parametros= new HashMap();
            
           
             
         
                  
             parametros.put("compania", compania);
             parametros.put("idOtr", idComp);
             parametros.put("secComp", secComp);
             parametros.put("formaComp", formaComp);
             parametros.put("bancoPagoComp", bancoPagoComp);
             parametros.put("moneda", moneda);
             parametros.put("importeComp", importeComp);
             parametros.put("fechaComp", fechaComp);
             parametros.put("operacion", 'I');
             parametros.put("tipoNegCxpComp", tipoNegCxpComp);
             parametros.put("paisCxpFormComp", paisCxpFormComp);
             
                     
           
            
          
                
                processDao.execute(store, parametros);
                
                rq.setSuccess(true);
                rq.setMsg("Comprobacion Guardada");
          
                return rq;

            
             
        } catch (Exception e) {
             rq.setSuccess(false);
            rq.setMsg("Error al guardar  cxp");
            e.printStackTrace();
            return rq;
        }


    }
    
         @RequestMapping(value = "/deleteComprobacion", method = RequestMethod.POST)
    public @ResponseBody
    ResponseQuery deleteComprobacion( String data, WebRequest webRequest, Model model) throws IOException {

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
            List<InfoFactOtrasDTO> lista = mapper.readValue(data,
                    mapper.getTypeFactory().constructCollectionType(List.class,
                            InfoFactOtrasDTO.class));

            if (lista.isEmpty()) {
                rq.setSuccess(false);
                rq.setData(null);
                rq.setMsg("Error no existen datos que guardar");
                return rq;
            }

  
            Iterator<InfoFactOtrasDTO> it = lista.iterator();
           
          
            int banderaGe = 0 ;
            while (it.hasNext()) {
//                 System.out.println("-------------------------------------------------------------------");
                 InfoFactOtrasDTO ss = it.next();
                 
                   
               Querys que = new Querys();
                String store = que.getSQL("CONTA_INSERTA_COMP_CXP");




                 Map parametros= new HashMap();





                 parametros.put("compania", compania);
                 parametros.put("idOtr", ss.numRelacion);
                 parametros.put("secComp", ss.numero);
                 parametros.put("operacion", 'D');

                  processDao.execute(store, parametros);
               
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
    
       @RequestMapping(value = "/saveOtrosDet", method = RequestMethod.POST)
    public @ResponseBody
    ResponseQuery saveOtrosDet( String data, WebRequest webRequest, Model model) throws IOException {

        ResponseQuery rq = new ResponseQuery();
   //     System.out.println("data factura cancelacion:"+data);
        SimpleDateFormat formatFecha = new SimpleDateFormat("dd/MM/yyyy");
         int index = data.indexOf("[");
        if (index == -1) {
            data = "[" + data + "]";
        }

       // System.out.println("data:  "+data);
            String compania = model.asMap().get("compania").toString();
            String usuario = model.asMap().get("usuario").toString();
            ObjectMapper mapper = new ObjectMapper();
          
                   
        try{
            List<CxpOtrasDetDTO> lista = mapper.readValue(data,
                    mapper.getTypeFactory().constructCollectionType(List.class,
                            CxpOtrasDetDTO.class));

            if (lista.isEmpty()) {
                rq.setSuccess(false);
                rq.setData(null);
                rq.setMsg("Error no existen datos que guardar");
                return rq;
            }

  
            Iterator<CxpOtrasDetDTO> it = lista.iterator();
           ErpCpOtrasDet otrasDet = new ErpCpOtrasDet();
           ErpCpOtrasDetId otrasDetId = new ErpCpOtrasDetId();
          
          
            int banderaGe = 0 ;
            String id = "";
            while (it.hasNext()) {
//                 System.out.println("-------------------------------------------------------------------");
                 CxpOtrasDetDTO ss = it.next();
                 
                 otrasDetId.setCompania(compania);
                 otrasDetId.setId(Integer.parseInt(ss.id));
                 int sec = erpCpOtrasDetDao.getMaxErpCpOtrasDetId(otrasDetId);
                                  System.out.println("sec: " + sec);

                 otrasDetId.setSec(sec);
                 otrasDet.setId(otrasDetId);
                 otrasDet.setCtoCxp(ss.ctoCxp);
                 otrasDet.setIdPaisCxp(ss.idPaisCxp);
                 otrasDet.setIdServicio(Integer.parseInt(ss.idServicio));
                 otrasDet.setIdTipoGasto(Integer.parseInt(ss.idTipoGasto));
                 otrasDet.setIdTipoNegocio(ss.idTipoNegocio);
                 otrasDet.setImporte(new BigDecimal(ss.importe));
                
                 id = ss.id;
                 erpCpOtrasDetDao.save(otrasDet);
               
                  banderaGe = 1;
            }
            
             Querys que = new Querys();
            String store = que.getSQL("ActualizaCxpXDet");
            
             Map parametros= new HashMap();
            
                  
             parametros.put("compania", compania);
             parametros.put("id", id);
                     
                
                processDao.execute(store, parametros);
            
          
            
            if (banderaGe == 1){
            
                
                    rq.setSuccess(true);
                    rq.setData(null);
                    rq.setMsg("Registros guardados correctamente");
              
                
            }else{
                
                rq.setSuccess(true);
                rq.setData(null);
                rq.setMsg("Error al guardar");
                
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
    
    
          @RequestMapping(value = "/updateOtrosDet", method = RequestMethod.POST)
    public @ResponseBody
    ResponseQuery updateOtrosDet( String data, WebRequest webRequest, Model model) throws IOException {

        ResponseQuery rq = new ResponseQuery();
   //     System.out.println("data factura cancelacion:"+data);
        SimpleDateFormat formatFecha = new SimpleDateFormat("dd/MM/yyyy");
         int index = data.indexOf("[");
        if (index == -1) {
            data = "[" + data + "]";
        }

       // System.out.println("data:  "+data);
            String compania = model.asMap().get("compania").toString();
            String usuario = model.asMap().get("usuario").toString();
            ObjectMapper mapper = new ObjectMapper();
          
                   
        try{
            List<CxpOtrasDetDTO> lista = mapper.readValue(data,
                    mapper.getTypeFactory().constructCollectionType(List.class,
                            CxpOtrasDetDTO.class));

            if (lista.isEmpty()) {
                rq.setSuccess(false);
                rq.setData(null);
                rq.setMsg("Error no existen datos que guardar");
                return rq;
            }

  
            Iterator<CxpOtrasDetDTO> it = lista.iterator();
           ErpCpOtrasDet otrasDet = new ErpCpOtrasDet();
           ErpCpOtrasDetId otrasDetId = new ErpCpOtrasDetId();
          
          
            int banderaGe = 0 ;
            String id = "";
            while (it.hasNext()) {
//                 System.out.println("-------------------------------------------------------------------");
                 CxpOtrasDetDTO ss = it.next();
                 
                 otrasDetId.setCompania(compania);
                 otrasDetId.setId(Integer.parseInt(ss.id));
                 id = ss.id;
                // int sec = erpCpOtrasDetDao.getMaxErpCpOtrasDetId(otrasDetId);
                 
                 otrasDetId.setSec(Integer.parseInt(ss.sec));
                 otrasDet.setId(otrasDetId);
                 otrasDet.setCtoCxp(ss.ctoCxp);
                 otrasDet.setIdPaisCxp(ss.idPaisCxp);
                 otrasDet.setIdServicio(Integer.parseInt(ss.idServicio));
                 otrasDet.setIdTipoGasto(Integer.parseInt(ss.idTipoGasto));
                 otrasDet.setIdTipoNegocio(ss.idTipoNegocio);
                 otrasDet.setImporte(new BigDecimal(ss.importe));
                
                 
                 erpCpOtrasDetDao.update(otrasDet);
               
                  banderaGe = 1;
            }
            
            
            
    
            
          Querys que = new Querys();
            String store = que.getSQL("ActualizaCxpXDet");
            
             Map parametros= new HashMap();
            
                  
             parametros.put("compania", compania);
             parametros.put("id", id);
                     
                
                processDao.execute(store, parametros);
          
            
            
            if (banderaGe == 1){
            
                
                    rq.setSuccess(true);
                    rq.setData(null);
                    rq.setMsg("Registros guardados correctamente");
              
                
            }else{
                
                rq.setSuccess(true);
                rq.setData(null);
                rq.setMsg("Error al guardar");
                
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
    
         @RequestMapping(value = "/deleteOtrosDet", method = RequestMethod.POST)
    public @ResponseBody
    ResponseQuery deleteOtrosDet( String data, WebRequest webRequest, Model model) throws IOException {

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
            List<CxpOtrasDetDTO> lista = mapper.readValue(data,
                    mapper.getTypeFactory().constructCollectionType(List.class,
                            CxpOtrasDetDTO.class));

            if (lista.isEmpty()) {
                rq.setSuccess(false);
                rq.setData(null);
                rq.setMsg("Error no existen datos que guardar");
                return rq;
            }

  
            Iterator<CxpOtrasDetDTO> it = lista.iterator();
          ErpCpOtrasDet otrasDet = new ErpCpOtrasDet();
           ErpCpOtrasDetId otrasDetId = new ErpCpOtrasDetId();
          
          String id = "";
            int banderaGe = 0 ;
            while (it.hasNext()) {
//                 System.out.println("-------------------------------------------------------------------");
                 CxpOtrasDetDTO ss = it.next();
                 
                 otrasDetId.setCompania(compania);
                 otrasDetId.setId(Integer.parseInt(ss.id));
                 otrasDetId.setSec(Integer.parseInt(ss.sec));
                 otrasDet.setId(otrasDetId);
                 
                 
                 id = ss.id;
                 
               
                  banderaGe = 1;
                  
                  erpCpOtrasDetDao.delete(otrasDet);
            }
            
            Querys que = new Querys();
            String store = que.getSQL("ActualizaCxpXDet");
            
             Map parametros= new HashMap();
            
                  
             parametros.put("compania", compania);
             parametros.put("id", id);
                     
                
                processDao.execute(store, parametros);
          
            
            
          
            
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
    
    
            
            
     @RequestMapping(value = "/saveOtrosDetComprasPort", method = RequestMethod.POST)
    public @ResponseBody
    ResponseQuery saveOtrosDetComprasPort( String data, WebRequest webRequest, Model model) throws IOException {

        ResponseQuery rq = new ResponseQuery();
   //     System.out.println("data factura cancelacion:"+data);
        SimpleDateFormat formatFecha = new SimpleDateFormat("dd/MM/yyyy");
         int index = data.indexOf("[");
        if (index == -1) {
            data = "[" + data + "]";
        }

        System.out.println("data:  "+data);
            String compania = model.asMap().get("compania").toString();
            String usuario = model.asMap().get("usuario").toString();
            ObjectMapper mapper = new ObjectMapper();
          
                   
        try{
            List<CxpDtoDetComp> lista = mapper.readValue(data,
                    mapper.getTypeFactory().constructCollectionType(List.class,
                            CxpDtoDetComp.class));

            if (lista.isEmpty()) {
                rq.setSuccess(false);
                rq.setData(null);
                rq.setMsg("Error no existen datos que guardar");
                return rq;
            }

  
            Iterator<CxpDtoDetComp> it = lista.iterator();
           ErpCpOtrasDet otrasDet = new ErpCpOtrasDet();
           ErpCpOtrasDetId otrasDetId = new ErpCpOtrasDetId();
          
          
            int banderaGe = 0 ;
            String id = "";
            while (it.hasNext()) {
//                 System.out.println("-------------------------------------------------------------------");
                 CxpDtoDetComp ss = it.next();
                 
                 otrasDetId.setCompania(compania);
                 otrasDetId.setId(Integer.parseInt(ss.id));
                 int sec = erpCpOtrasDetDao.getMaxErpCpOtrasDetId(otrasDetId);
                                  System.out.println("sec: " + sec);

                 otrasDetId.setSec(sec);
                 otrasDet.setId(otrasDetId);
                 otrasDet.setPrecioUnitario(new BigDecimal(ss.importe));
                 otrasDet.setImporte(new BigDecimal(ss.importe).multiply(new BigDecimal(ss.cantidad)));
                 otrasDet.setDescripcion(ss.descripcion);
                 otrasDet.setCantidad(Integer.parseInt(ss.cantidad));
                
                 id = ss.id;
                 erpCpOtrasDetDao.save(otrasDet);
               
                  banderaGe = 1;
            }
            
//             Querys que = new Querys();
//            String store = que.getSQL("ActualizaCxpXDet");
//            
//             Map parametros= new HashMap();
//            
//                  
//             parametros.put("compania", compania);
//             parametros.put("id", id);
//                     
//                
//                processDao.execute(store, parametros);
//                
//                
//                Querys queConc = new Querys();
//                    String storeConc = queConc.getSQL("RelacionaConceptoxLinea");
//
//
//                          
//                    Map concLinea = new HashMap();
//
//                    concLinea.put("compania", compania);
//                    concLinea.put("orden", "");
//                    concLinea.put("linea", "");
//                    concLinea.put("numeroFe", id);
//                    concLinea.put("origen", "OTR");
//
//                    processDao.execute(storeConc, concLinea);     
//            
//          
            
            if (banderaGe == 1){
            
                
                    rq.setSuccess(true);
                    rq.setData(null);
                    rq.setMsg("Registros guardados correctamente");
              
                
            }else{
                
                rq.setSuccess(true);
                rq.setData(null);
                rq.setMsg("Error al guardar");
                
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
            
    
       @RequestMapping(value = "/saveOtrosDetCompras", method = RequestMethod.POST)
    public @ResponseBody
    ResponseQuery saveOtrosDetCompras( String data, WebRequest webRequest, Model model) throws IOException {

        ResponseQuery rq = new ResponseQuery();
   //     System.out.println("data factura cancelacion:"+data);
        SimpleDateFormat formatFecha = new SimpleDateFormat("dd/MM/yyyy");
         int index = data.indexOf("[");
        if (index == -1) {
            data = "[" + data + "]";
        }

        System.out.println("data:  "+data);
            String compania = model.asMap().get("compania").toString();
            String usuario = model.asMap().get("usuario").toString();
            ObjectMapper mapper = new ObjectMapper();
          
                   
        try{
            List<CxpDtoDetComp> lista = mapper.readValue(data,
                    mapper.getTypeFactory().constructCollectionType(List.class,
                            CxpDtoDetComp.class));

            if (lista.isEmpty()) {
                rq.setSuccess(false);
                rq.setData(null);
                rq.setMsg("Error no existen datos que guardar");
                return rq;
            }

  
            Iterator<CxpDtoDetComp> it = lista.iterator();
           ErpCpOtrasDet otrasDet = new ErpCpOtrasDet();
           ErpCpOtrasDetId otrasDetId = new ErpCpOtrasDetId();
          
          
            int banderaGe = 0 ;
            String id = "";
            while (it.hasNext()) {
//                 System.out.println("-------------------------------------------------------------------");
                 CxpDtoDetComp ss = it.next();
                 
                 otrasDetId.setCompania(compania);
                 otrasDetId.setId(Integer.parseInt(ss.id));
                 int sec = erpCpOtrasDetDao.getMaxErpCpOtrasDetId(otrasDetId);
                                  System.out.println("sec: " + sec);

                 otrasDetId.setSec(sec);
                 otrasDet.setId(otrasDetId);
                 otrasDet.setPrecioUnitario(new BigDecimal(ss.importe));
                 otrasDet.setImporte(new BigDecimal(ss.importe).multiply(new BigDecimal(ss.cantidad)));
                 otrasDet.setDescripcion(ss.descripcion);
                 otrasDet.setCantidad(Integer.parseInt(ss.cantidad));
                
                 id = ss.id;
                 erpCpOtrasDetDao.save(otrasDet);
               
                  banderaGe = 1;
            }
            
             Querys que = new Querys();
            String store = que.getSQL("ActualizaCxpXDet");
            
             Map parametros= new HashMap();
            
                  
             parametros.put("compania", compania);
             parametros.put("id", id);
                     
                
                processDao.execute(store, parametros);
                
                
                Querys queConc = new Querys();
                    String storeConc = queConc.getSQL("RelacionaConceptoxLinea");


                          
                    Map concLinea = new HashMap();

                    concLinea.put("compania", compania);
                    concLinea.put("orden", "");
                    concLinea.put("linea", "");
                    concLinea.put("numeroFe", id);
                    concLinea.put("origen", "OTR");

                    processDao.execute(storeConc, concLinea);     
            
          
            
            if (banderaGe == 1){
            
                
                    rq.setSuccess(true);
                    rq.setData(null);
                    rq.setMsg("Registros guardados correctamente");
              
                
            }else{
                
                rq.setSuccess(true);
                rq.setData(null);
                rq.setMsg("Error al guardar");
                
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
        
        
        
     
           String formComp = webRequest.getParameter("cboCajaFORMA_COMPROBACIONCXP").toString();
           String concGasto = webRequest.getParameter("cboCajaCONC_GASTOCXP").toString();
           String cajaAut = webRequest.getParameter("cajaAutViaticoCXP").toString();
           String fechaComp = webRequest.getParameter("cajaAutF_COMPROBANTECXP").toString();
           String moneda = webRequest.getParameter("cajaAutMONEDACXP").toString();
           String idCaja = webRequest.getParameter("cajaCompIDCXP").toString();
           String secCaja = webRequest.getParameter("cajaCompSECCajaCXP").toString();
           String usuarioCaja = webRequest.getParameter("cajaCompUSUARIO_CAJACXP").toString();
           String rfc = webRequest.getParameter("cajaAutRFCEmisorCXP").toString();
           String nombre = webRequest.getParameter("cajaAutNombreEmisorCXP").toString();
           String importe = webRequest.getParameter("cajaAutIMPORTECXP").toString();
           String iva = webRequest.getParameter("cajaAutIVACXP").toString();
           String retencionRenta = webRequest.getParameter("cajaAutRETENCION_RENTACXP").toString();
           String retencionIva = webRequest.getParameter("cajaAutRETENCION_IVACXP").toString();
           String otrosImpuestos = webRequest.getParameter("cajaAutOTROS_IMPUESTOSCXP").toString();
           String total = webRequest.getParameter("cajaAutTOTALCXP").toString();
           String ctoCto = webRequest.getParameter("cboCtoCajaAutCXP").toString();
           String tipoNegocio = webRequest.getParameter("cboTipoNegocioCajaAutCXP").toString();
           String pais = webRequest.getParameter("cboPaisOtrosCajaAutCXP").toString();
           String tipoFactura = webRequest.getParameter("cajaAutTipoFacturaCXP").toString();
           String factura = webRequest.getParameter("cajaAutFacturaCXP").toString();
           String gasto = webRequest.getParameter("cajaAutGastoCXP").toString();
           String empleadoAplica = webRequest.getParameter("cajaAutEMPLEADO_APLICACXP").toString();
           
   
          
                                                          
        try {
            
            

         
            
                   Querys que = new Querys();
            String store = que.getSQL("GeneraComprobacionCXP");
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
    
      @RequestMapping(value = "/deleteCompCXP", method = RequestMethod.POST)
    public @ResponseBody
    ResponseQuery deleteCompCXP( String data, WebRequest webRequest, Model model) throws IOException {

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
            List<InfoFactOtrasDTO> lista = mapper.readValue(data,
                    mapper.getTypeFactory().constructCollectionType(List.class,
                            InfoFactOtrasDTO.class));

            if (lista.isEmpty()) {
                rq.setSuccess(false);
                rq.setData(null);
                rq.setMsg("Error no existen datos que guardar");
                return rq;
            }

  
            Iterator<InfoFactOtrasDTO> it = lista.iterator();
          
          
            int banderaGe = 1 ;
            while (it.hasNext()) {
//                 System.out.println("-------------------------------------------------------------------");
                 InfoFactOtrasDTO ss = it.next();
                 
                 
                   Querys que = new Querys();
            String store = que.getSQL("DELETE_COMP_CXP");
             Map param = new HashMap();
                  param.put("compania", compania);
                  param.put("comision", ss.comision);
                  param.put("sec", ss.sec);
                  param.put("tipoGasto", ss.tipoGasto);
                  param.put("numero", ss.numero);
               
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
    
    
    
     @RequestMapping(value={"/insertImpuestos"}, method={org.springframework.web.bind.annotation.RequestMethod.POST})
  @ResponseBody
  public ResponseQuery2 insertImpuestos(String data, WebRequest webRequest, Model model)
    throws ParseException
  {
    ResponseQuery2 rq = new ResponseQuery2();
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
    try
    {
      int guardado = 0;
      
      ObjectMapper mapper = new ObjectMapper();
//      DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
      
//      mapper.setDateFormat(df);
      List<ImpuestosBrDTO> lista = (List)mapper.readValue(data, mapper
        .getTypeFactory().constructCollectionType(List.class, ImpuestosBrDTO.class));
      if (lista.isEmpty())
      {
        rq.setSuccess(false);
        rq.setData(null);
        rq.setMsg("Error no existen datos que guardar");
        return rq;
      }
      ErpDetImpuestosBr imp = new ErpDetImpuestosBr();
      ErpDetImpuestosBrId impId = new ErpDetImpuestosBrId();
      
      
      

      int val = 0;
      Iterator<ImpuestosBrDTO> it = lista.iterator();
      
      ErpDetImpuestosBrId result = null;
      while (it.hasNext())
      {
        System.out.println("-------------------------------------------------------------------");
        ImpuestosBrDTO ss = (ImpuestosBrDTO)it.next();
        
          System.out.println("ss impuesto"+ss.impuesto);
          
          impId.setCompania(compania);
          
          impId.setImpuesto(ss.impuesto);
          impId.setNumero(Integer.parseInt(ss.numero));
          
          imp.setId(impId);
          imp.setEstado(ss.estado);
          imp.setTipoImpuesto(ss.tipoImpuesto);
          imp.setMoneda(ss.moneda);
          imp.setImporte(new BigDecimal(ss.importe));
          
          result = erpDetImpuestosBrDao.save(imp);
      }
      if (result != null)
      {
        rq.setSuccess(true);
        

        rq.setMsg("Guardados Correctamente");
      }
      else
      {
        rq.setSuccess(false);
        rq.setData(null);
        
        rq.setMsg("Error al guardar");
      }
    }
    catch (Exception e)
    {
      e.printStackTrace();
    }
    return rq;
  }
  
   @RequestMapping(value={"/insertImpuestosForm"}, method={org.springframework.web.bind.annotation.RequestMethod.POST})
  @ResponseBody
  public ResponseQuery2 insertImpuestosForm(String data, WebRequest webRequest, Model model)
    throws ParseException
  {
    ResponseQuery2 rq = new ResponseQuery2();
    SimpleDateFormat formatoDelTexto = new SimpleDateFormat("dd/MM/yyyy");
    
    String tipoFactura = webRequest.getParameter("txtTipoFacturaImpuesto").toString();
    String numero = webRequest.getParameter("idNumImpuesto").toString();
    String impuesto = webRequest.getParameter("cboIdImpuestoBr").toString();
    String tipo = webRequest.getParameter("idtipoImpuesto").toString();
    String estado = webRequest.getParameter("cboEstadoBrImpuesto").toString();
    String municipio = webRequest.getParameter("cboMunicipioBrImpuesto").toString();
    String moneda = webRequest.getParameter("cboMonedaBrImpuesto").toString();
    String importe = webRequest.getParameter("txtIMPORTEImpuesto").toString();
    String operacion = webRequest.getParameter("txtOperacionImpuesto").toString();
    
    

   
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
    try
    {
      int guardado = 0;
      
      ObjectMapper mapper = new ObjectMapper();
//      DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
      
//      mapper.setDateFormat(df);
      
      ErpDetImpuestosBr imp = new ErpDetImpuestosBr();
      ErpDetImpuestosBrId impId = new ErpDetImpuestosBrId();
      
      
      

      int val = 0;
      
      ErpDetImpuestosBrId result = null;
      Boolean result2 = true;
      
        System.out.println("-------------------------------------------------------------------");
            
          impId.setCompania(compania);
        if(operacion.equalsIgnoreCase("I")){  
                impId.setImpuesto(impuesto);
                impId.setNumero(Integer.parseInt(numero));

                imp.setId(impId);
                imp.setEstado(estado);
                imp.setTipoImpuesto(tipo);
                imp.setMoneda(moneda);
                imp.setImporte(new BigDecimal(importe));
                imp.setMunicipio(municipio);

                result = erpDetImpuestosBrDao.save(imp);
      
                if (result != null)
                {
                  rq.setSuccess(true);


                  rq.setMsg("Guardados Correctamente");
                }
                else
                {
                  rq.setSuccess(false);
                  rq.setData(null);

                  rq.setMsg("Error al guardar");
                }
        }
        
        if(operacion.equalsIgnoreCase("U")){  
                impId.setImpuesto(impuesto);
                impId.setNumero(Integer.parseInt(numero));

                imp.setId(impId);
                imp.setEstado(estado);
                imp.setTipoImpuesto(tipo);
                imp.setMoneda(moneda);
                imp.setImporte(new BigDecimal(importe));
                imp.setMunicipio(municipio);

                result2 = erpDetImpuestosBrDao.update(imp);
      
                if (result2 == true)
                {
                  rq.setSuccess(true);


                  rq.setMsg("Guardados Correctamente");
                }
                else
                {
                  rq.setSuccess(false);
                  rq.setData(null);

                  rq.setMsg("Error al guardar");
                }
        }
        
        
        
    }
    catch (Exception e)
    {
      e.printStackTrace();
    }
    return rq;
  }  
  
  
       @RequestMapping(value={"/updateImpuestos"}, method={org.springframework.web.bind.annotation.RequestMethod.POST})
  @ResponseBody
  public ResponseQuery2 updateImpuestos(String data, WebRequest webRequest, Model model)
    throws ParseException
  {
    ResponseQuery2 rq = new ResponseQuery2();
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
    try
    {
      int guardado = 0;
      
      ObjectMapper mapper = new ObjectMapper();
//      DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
      
//      mapper.setDateFormat(df);
      List<ImpuestosBrDTO> lista = (List)mapper.readValue(data, mapper
        .getTypeFactory().constructCollectionType(List.class, ImpuestosBrDTO.class));
      if (lista.isEmpty())
      {
        rq.setSuccess(false);
        rq.setData(null);
        rq.setMsg("Error no existen datos que guardar");
        return rq;
      }
      ErpDetImpuestosBr imp = new ErpDetImpuestosBr();
      ErpDetImpuestosBrId impId = new ErpDetImpuestosBrId();
      
      
      

      int val = 0;
      Iterator<ImpuestosBrDTO> it = lista.iterator();
      
      Boolean result = null;
      while (it.hasNext())
      {
        System.out.println("-------------------------------------------------------------------");
        ImpuestosBrDTO ss = (ImpuestosBrDTO)it.next();
        
          System.out.println("ss impuesto"+ss.impuesto);
          
          impId.setCompania(compania);
          
          impId.setImpuesto(ss.impuesto);
          impId.setNumero(Integer.parseInt(ss.numero));
          
          imp.setId(impId);
          
          imp.setTipoImpuesto(ss.tipoImpuesto);
          imp.setEstado(ss.estado);
          imp.setMoneda(ss.moneda);
          imp.setImporte(new BigDecimal(ss.importe));
          
          result = erpDetImpuestosBrDao.update(imp);
      }
      if (result == true)
      {
        rq.setSuccess(true);
        

        rq.setMsg("Guardados Correctamente");
      }
      else
      {
        rq.setSuccess(false);
        rq.setData(null);
        
        rq.setMsg("Error al guardar");
      }
    }
    catch (Exception e)
    {
      e.printStackTrace();
    }
    return rq;
  }
      
     @RequestMapping(value={"/deleteImpuestos"}, method={org.springframework.web.bind.annotation.RequestMethod.POST})
  @ResponseBody
  public ResponseQuery2 deleteImpuestos(String data, WebRequest webRequest, Model model)
    throws ParseException
  {
    ResponseQuery2 rq = new ResponseQuery2();
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
    try
    {
      int guardado = 0;
      
      ObjectMapper mapper = new ObjectMapper();
//      DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
      
//      mapper.setDateFormat(df);
      List<ImpuestosBrDTO> lista = (List)mapper.readValue(data, mapper
        .getTypeFactory().constructCollectionType(List.class, ImpuestosBrDTO.class));
      if (lista.isEmpty())
      {
        rq.setSuccess(false);
        rq.setData(null);
        rq.setMsg("Error no existen datos que guardar");
        return rq;
      }
      ErpDetImpuestosBr imp = new ErpDetImpuestosBr();
      ErpDetImpuestosBrId impId = new ErpDetImpuestosBrId();
      
      
      

      int val = 0;
      Iterator<ImpuestosBrDTO> it = lista.iterator();
      
      Boolean result = null;
      while (it.hasNext())
      {
        System.out.println("-------------------------------------------------------------------");
        ImpuestosBrDTO ss = (ImpuestosBrDTO)it.next();
        
          System.out.println("ss impuesto"+ss.impuesto);
          
          impId.setCompania(compania);
          
          impId.setImpuesto(ss.impuesto);
          impId.setNumero(Integer.parseInt(ss.numero));
          
          imp.setId(impId);
          imp.setEstado(ss.estado);
          imp.setTipoImpuesto(ss.tipoImpuesto);
          imp.setMoneda(ss.moneda);
          imp.setImporte(new BigDecimal(ss.importe));
          
          result = erpDetImpuestosBrDao.delete(imp);
      }
      if (result == true)
      {
        rq.setSuccess(true);
        

        rq.setMsg("Guardados Correctamente");
      }
      else
      {
        rq.setSuccess(false);
        rq.setData(null);
        
        rq.setMsg("Error al guardar");
      }
    }
    catch (Exception e)
    {
      e.printStackTrace();
    }
    return rq;
  }
  
  
  @RequestMapping(value = "/generaImpuestosCXP", method = RequestMethod.POST)
     public @ResponseBody
    ResponseQuery2 generaImpuestosCXP(WebRequest webRequest, Model model) throws ParseException {

        ResponseQuery2 rq = new ResponseQuery2();
        SimpleDateFormat formatoDelTexto = new SimpleDateFormat("dd/MM/yyyy");
        SimpleDateFormat formatFecha = new SimpleDateFormat("dd/MM/yyyy");
       
//
        if (model.asMap().get("compania") == null) {
            rq.setSuccess(false);
            rq.setData(null);
            rq.setMsg("La sesion no es valida.");
            return rq;
        }
        String compania = model.asMap().get("compania").toString();
        String usuario = model.asMap().get("usuario").toString();
        
        String calendario = webRequest.getParameter("cboCalendarioImpCXP").toString();
        String periodo = webRequest.getParameter("cboPeriodoImpCXP").toString();
        String fecha = webRequest.getParameter("dtFECHAImpGen").toString();
        
                
                
        
        
      
        int id = 0;
        try {
          
         
               Querys que = new Querys();
               String store = que.getSQL("GeneraImpuestosCXP");
               
                   Map parametros = new HashMap();
             
 //  P_SID              VARCHAR2,

             
                parametros.put("compania", compania);
                parametros.put("calendario",calendario);
                parametros.put("periodo",periodo);
                parametros.put("fecha",fecha);
                parametros.put("usuario",usuario);
           
               


               int val = processDao.execute(store, parametros);
                
                 if (val > 0) {
               
                 
                            rq.setSuccess(true);
                            rq.setMsg(String.valueOf(id));

                    } else {
                        rq.setSuccess(false);
                        rq.setData(null);
                        rq.setMsg("Error al generar datos");
                    }
            
            
            
            
            
          
            

           

        } catch (Exception e) {
            e.printStackTrace();
        }

        return rq;

    }
  
  
  
      
    
    
    public int actualizaImporteFactNota(String compania,String idFact, String idNota){
        
      
        return 0;
    
    }
    

        public void revisarDirectorio(String compania) {

        File file = new File(dirOutFileDocument + compania + "/");
//        System.out.println("Directory" + dirOutFileDocument + dirCompania + "/");
        if (!file.exists()) {
            if (file.mkdirs()) {
//                System.out.println("Directory is created!");
            } else {
//                System.out.println("Failed to create directory!");
            }
        }

    }
        
         public void revisarDirectorioLineas(String dir) {

        File file = new File(dir);
//        System.out.println("Directory" + dirOutFileDocument + dirCompania + "/");
        if (!file.exists()) {
            if (file.mkdirs()) {
//                System.out.println("Directory is created!");
            } else {
//                System.out.println("Failed to create directory!");
            }
        }

    }
         
          public String formatFecha2(Date date, String format) {
        try {
            return new SimpleDateFormat(format).format(date);
        } catch (Exception e) {
            return "";
        }
    }
    
    public void setErpCpOtrasDao(ErpCpOtrasDao ErpCpOtrasDao) {
        this.ErpCpOtrasDao = ErpCpOtrasDao;
    }

    public void setProcessDao(ProcessDao processDao) {
        this.processDao = processDao;
    }

    public void setDirOutFileDocument(String dirOutFileDocument) {
        this.dirOutFileDocument = dirOutFileDocument;
    }

    public void setDirUrlOutDocument(String dirUrlOutDocument) {
        this.dirUrlOutDocument = dirUrlOutDocument;
    }

    public void setMaxSizeDocument(String maxSizeDocument) {
        this.maxSizeDocument = maxSizeDocument;
    }

    public void setErpNotasExtDao(ErpNotasExtDao erpNotasExtDao) {
        this.erpNotasExtDao = erpNotasExtDao;
    }

    public void setErpCpOtrasDetDao(ErpCpOtrasDetDao erpCpOtrasDetDao) {
        this.erpCpOtrasDetDao = erpCpOtrasDetDao;
    }

    public void setErpDetImpuestosBrDao(ErpDetImpuestosBrDao erpDetImpuestosBrDao) {
        this.erpDetImpuestosBrDao = erpDetImpuestosBrDao;
    }

    public void setErpSeguiDocumentosDao(ErpSeguiDocumentosDao erpSeguiDocumentosDao) {
        this.erpSeguiDocumentosDao = erpSeguiDocumentosDao;
    }
    
    
    
}
