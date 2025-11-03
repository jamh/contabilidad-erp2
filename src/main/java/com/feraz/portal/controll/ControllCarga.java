/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.feraz.portal.controll;

import com.feraz.contabilidad.convertidor.util.ResponseQuery3;
import com.feraz.cxp.dao.ErpCxpOrdenCompraLogDao;
import com.feraz.cxp.model.ErpCClientes;
import com.feraz.cxp.model.ErpCxpOrdenCompraLog;
import com.feraz.cxp.model.ErpCxpOrdenCompraLogId;
import com.feraz.facturas.webcontrolfe.App.App;
import com.feraz.facturas.webcontrolfe.dto.FileInfo;
import com.feraz.facturas.webcontrolfe.dto.PolizasInfo;
import com.feraz.facturas.webcontrolfe.model.FileUploadBean;
import com.feraz.facturas.webcontrolfe.model.FileUploadBean2;
import com.feraz.facturas.webcontrolfe.validation.ValidaVersionCFDI;
import com.feraz.mx.sat.cfdi4.Comprobante4;
import com.feraz.ordencompra.dao.ErpOrdenCompraDao;
import com.feraz.ordencompra.dao.ErpOrdenCompraDetDao;
import com.feraz.ordencompra.dao.ErpOrdenXFacturaDao;
import com.feraz.ordencompra.model.ErpOrdenCompra;
import com.feraz.ordencompra.model.ErpOrdenCompraDet;
import com.feraz.ordencompra.model.ErpOrdenCompraDetId;
import com.feraz.ordencompra.model.ErpOrdenCompraId;
import com.feraz.ordencompra.model.ErpOrdenXFactura;
import com.feraz.ordencompra.model.ErpOrdenXFacturaId;
import com.feraz.portal.dto.ReadXMLDTO;
import com.feraz.portal.dto.ValidadorGral;
import com.feraz.sat.cfdi.model.ResponseWSValida;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.Map;
import org.jamh.data.process.ProcessDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.multipart.MultipartFile;
import com.feraz.sat.cfdi.check.ValidaSAT;
import java.io.FileInputStream;
import java.io.IOException;
import java.math.BigDecimal;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import org.apache.log4j.Logger;
import org.jamh.wf.process.Querys;
import org.springframework.http.ResponseEntity;
import org.springframework.util.Assert;
import org.springframework.web.client.RestTemplate;

/**
 *
 * @author Ing. JAMH
 */

@Controller
@RequestMapping(value = "/Uploadprov")
@SessionAttributes({"compania", "usuario"})
public class ControllCarga {
    @Value("${document.file.dirOutDocumentFE}")
    private String dirOutFileDocument;
    @Value("${document.file.dirURLOutDocumentFE}")
    private String dirUrlOutDocument;
    @Value("${document.file.maxZiseFE}")
    private String maxSizeDocument;
    
     @Value("${document.file.dirOutDocumentOrden}")
    private String dirOutFileDocumentOrden;
    @Value("${document.file.dirURLOutDocumentOrden}")
    private String dirUrlOutDocumentOrden;
    @Value("${document.file.maxZiseOrden}")
    private String maxSizeDocumentOrden;
    
    private String msgErr;
    private String extension = "";
    //private String nombreArc = "";
    private String path;
    private String pathXML;
    private String url;
   // private String dirCompania;
   // private String hora;
    
    private static final Logger log = Logger.getLogger(ControllCarga.class);
    
    @Autowired
    private ProcessDao processDao;
    
    @Autowired
    private ValidaVersionCFDI validaVersionCFDI;
    
    @Autowired
    private App app;
    
    @Autowired
    private ErpOrdenXFacturaDao erpOrdenXFacturaDao;
    
    
    @Autowired
    private ErpCxpOrdenCompraLogDao erpCxpOrdenCompraLogDao;
    
    @Autowired
    private ErpOrdenCompraDao erpOrdenCompraDao;
    
    @Autowired
    private ErpOrdenCompraDetDao erpOrdenCompraDetDao;
    
    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public @ResponseBody
    ResponseQuery3 create(
            @RequestParam("COMPANIA") String compania1,
            @RequestParam("RFC") String rfc,
            @RequestParam("archCOMPANIA") String archCOMPANIA,
            @ModelAttribute("proveedor")  ErpCClientes prov,
            FileUploadBean uploadItem,
            FileUploadBean2 uploadItem2,
            BindingResult result,
            WebRequest webRequest,
            Model model) throws IOException, JAXBException {
        log.debug("---------------------------------------------------------------------");
        log.debug("compania1:"+compania1);
        log.debug("rfc:"+rfc);
        log.debug("archCOMPANIA:"+archCOMPANIA);
        
        Map parametros = processDao.paramToMap(webRequest.getParameterMap());
        String concGasto = null;
        
        String hora;

          hora = "" + System.currentTimeMillis();

        boolean isSave = true;
        SimpleDateFormat formatoDelTexto2 = new SimpleDateFormat("dd/MM/yyyy");

        ResponseQuery3 rq = new ResponseQuery3();
        
        if (prov == null) {
            rq.setMsg("Sesion no valida");
            rq.setSuccess(false);
            return rq;
        }

        String compania = model.asMap().get("compania").toString();
         String usuario = model.asMap().get("usuario").toString();
         
         log.debug("usuario:"+usuario);
         log.debug("compania session:"+compania);
         
         String dirCompania;
         
          dirCompania = compania;
        FileInfo fi;
        FileInfo fi2;
        
         boolean result1 = validaVersionCFDI.verision32File(uploadItem.getFile());
         boolean result2 = validaVersionCFDI.version33File(uploadItem.getFile());
         
         
         System.out.println("result1: " +result1);
         System.out.println("result2: " +result2);
        
        String dirFecha = "";
        
        if(!result2){
        
               dirFecha = app.validaFechaComprobante(uploadItem.getFile(), dirOutFileDocument, compania);
        }else{
        
               dirFecha = app.validaFechaComprobante33(uploadItem.getFile(), dirOutFileDocument, compania);
        }
        if (dirFecha.equalsIgnoreCase("")){
            
                fi = generaArchivos(uploadItem.getFile(), 1,dirCompania, hora);

                if (!fi.isIsSave()) {
                    rq.setMsg("Error al guardar archivo XML:" + msgErr);
                    rq.setSuccess(false);
                    return rq;
                }
                fi2 = generaArchivos(uploadItem2.getFile2(), 2,dirCompania, hora);
                if (!fi2.isIsSave()) {
                    rq.setMsg("Error al guardar archivo PDF:" + msgErr);
                    rq.setSuccess(false);
                    return rq;
                }
        
        }else{
            
                 String nombreUuid = "";
            
            if(!result2){
            
              nombreUuid = app.validaNombreUUidComprobante(uploadItem.getFile(), dirOutFileDocument, compania);
              
            }else{
                
                nombreUuid = app.validaNombreUUidComprobante33(uploadItem.getFile(), dirOutFileDocument, compania);
                
                
                      
                }
            
            System.out.println("nombreUuid: "+nombreUuid);
            
               fi = generaArchivos2(uploadItem.getFile(), 1,dirFecha,nombreUuid);

                if (!fi.isIsSave()) {
                    rq.setMsg("Error al guardar archivo XML:" + msgErr);
                    rq.setSuccess(false);
                    return rq;
                }
                fi2 = generaArchivos2(uploadItem2.getFile2(), 2,dirFecha,nombreUuid);
        //        System.out.println("res2:" + res2);
                if (!fi2.isIsSave()) {
                    rq.setMsg("Error al guardar archivo PDF:" + msgErr);
                    rq.setSuccess(false);
                    return rq;
                }
        
        }
        
        

        if (result.hasErrors()) {
            for (ObjectError error : result.getAllErrors()) {
                System.err.println("Error: " + error.getCode() + " - " + error.getDefaultMessage());
            }
            rq.setSuccess(isSave);
            rq.setMsg("Error al guardar el archivo.");

            return rq;
        }
        String idErr = "" + System.currentTimeMillis();
        app.setId(idErr);
       log.debug("pathXML:"+pathXML);
       PolizasInfo valida;
         if(!result2){
            valida = app.validaComprobante(compania, pathXML, fi2.getFile(), fi2.getPath());
       }else{
            valida = app.validaComprobante33(compania, pathXML, fi2.getFile(), fi2.getPath());
       }
        log.debug("valida:"+valida.getComprobante());
        log.debug("vali:"+valida.getInfTipo());
        log.debug("msgErr:"+valida.getMsgErr());
        
        if (valida.getInfTipo() ==1 || valida.getInfTipo() ==3) {
            rq.setMsg("Error al validar el XML ya fue cargado al portal");
            rq.setSuccess(false);
            return rq;
        }
        
        
        
         if(!result2){
        
        if(valida.getComprobante()==null){
            rq.setMsg("Error al validar el XML:");
            rq.setSuccess(false);
            return rq;
        }
       // System.out.println("usuario:"+usuario);
        
       // System.out.println("prov.getRfc():"+valida.getComprobante().getEmisor().getRfc());
        //Valida que el RFC emisor sea de la secion
        if(!usuario.equals(valida.getComprobante().getEmisor().getRfc())){
             rq.setSuccess(false);
            rq.setMsg("ERROR RFC del Emisor no es valido");
            return rq;
        }
        
        //Valida que el RFC receptor sea de SHW
        if(!rfc.equals(valida.getComprobante().getReceptor().getRfc())){
            rq.setSuccess(false);
            rq.setMsg("ERROR RFC Receptor No valido");
            return rq;
        }
        //Obtener el uuid
         }else{
             
               JAXBContext jaxbContext = JAXBContext.newInstance(com.feraz.mx.sat.cfdi.Comprobante.class);

                    Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
                    com.feraz.mx.sat.cfdi.Comprobante comp = (com.feraz.mx.sat.cfdi.Comprobante) jaxbUnmarshaller.unmarshal(new FileInputStream(pathXML));

             
              if(comp==null){
            rq.setMsg("Error al validar el XML:");
            rq.setSuccess(false);
            return rq;
        }
       // System.out.println("usuario:"+usuario);
        
       // System.out.println("prov.getRfc():"+valida.getComprobante().getEmisor().getRfc());
        //Valida que el RFC emisor sea de la secion
        if(!usuario.equals(comp.getEmisor().getRfc())){
             rq.setSuccess(false);
            rq.setMsg("ERROR RFC del Emisor no es valido");
            return rq;
        }
        
        //Valida que el RFC receptor sea de SHW
        if(!rfc.equals(comp.getReceptor().getRfc())){
            rq.setSuccess(false);
            rq.setMsg("ERROR RFC Receptor No valido");
            return rq;
        }
            
            
            
        }
        String uuid =app.getUUID(pathXML);
        
        //Valida en el SAT
        
        log.debug("Validando en el SAT....");
        if(!result2){
        ValidaSAT vl = new ValidaSAT();
        ResponseWSValida res = vl.validaCFDIWSSAT(valida.getComprobante().getEmisor().getRfc(), valida.getComprobante().getReceptor().getRfc(),""+ valida.getComprobante().getTotal(),uuid);
        
        
        log.debug("res.getEstatus():"+res.getEstatus());
               
               
        if (res.getEstatus().equals("Vigente")) {
            System.out.println("CFDI se Valido Bien en el SAT");
        } else {
            rq.setSuccess(false);
            rq.setMsg("ERROR Factuara no se encontro valida en el SAT.");
            return rq;
        }
        }
        app.setNombrePdf(fi2.getFile());
        app.setPathPdf(fi2.getPath());
        app.setUsuario(usuario);
        app.setTipoCarga("1");

          int data;
        if(!result2){
           data = app.cargaComprobante(pathXML, compania, concGasto,"PRTL");
        }else{
           data = app.cargaComprobante33(pathXML, compania, concGasto,"","PRTL");
        }
        log.debug("Result data: "+data);
        
        if (data >= 1) {

            rq.setSuccess(true);
            rq.setMsg(Integer.toString(data));

            return rq;
        } else {
            rq.setSuccess(false);
            rq.setMsg("Error al cargar comprobante");

            return rq;
        }

    }
    
    
        @RequestMapping(value = "/savePortalFolio", method = RequestMethod.POST)
    public @ResponseBody
    ResponseQuery3 createPortalFolio(
            @RequestParam("COMPANIA") String compania1,
            @RequestParam("RFC") String rfc,
            @RequestParam("archCOMPANIA") String archCOMPANIA,
            @RequestParam("RFC_PROV") String rfcProv,
            
            @ModelAttribute("proveedor")  ErpCClientes prov,
            FileUploadBean uploadItem,
            FileUploadBean2 uploadItem2,
            BindingResult result,
            WebRequest webRequest,
            Model model) throws IOException, JAXBException {
        log.debug("---------------------------------------------------------------------");
        log.debug("compania1:"+compania1);
        log.debug("rfc:"+rfc);
        log.debug("archCOMPANIA:"+archCOMPANIA);
        
        Map parametros = processDao.paramToMap(webRequest.getParameterMap());
        String concGasto = null;
        
        String hora;
        String rfcProveedor = "";

          hora = "" + System.currentTimeMillis();

        boolean isSave = true;
        SimpleDateFormat formatoDelTexto2 = new SimpleDateFormat("dd/MM/yyyy");

        ResponseQuery3 rq = new ResponseQuery3();
        
        if (prov == null) {
            rq.setMsg("Sesion no valida");
            rq.setSuccess(false);
            return rq;
        }

        String compania = model.asMap().get("compania").toString();
         String usuario = model.asMap().get("usuario").toString();
         
         log.debug("usuario:"+usuario);
         log.debug("compania session:"+compania);
         
         String dirCompania;
         
          dirCompania = compania;
        FileInfo fi;
        FileInfo fi2;
        
        ErpCxpOrdenCompraLog ordenLog = new ErpCxpOrdenCompraLog();
        ErpCxpOrdenCompraLogId ordenLogId = new ErpCxpOrdenCompraLogId();
        
         boolean result1 = validaVersionCFDI.verision32File(uploadItem.getFile());
         boolean result2 = validaVersionCFDI.version33File(uploadItem.getFile());
          
                     
         
         System.out.println("result1: " +result1);
         System.out.println("result2: " +result2);
        
        String dirFecha = "";
        String nombreUuid = "";
        
        if(!result2){
        
               dirFecha = app.validaFechaComprobante(uploadItem.getFile(), dirOutFileDocument, compania);
        }else{
        
               dirFecha = app.validaFechaComprobante33(uploadItem.getFile(), dirOutFileDocument, compania);
        }
        if (dirFecha.equalsIgnoreCase("")){
            
                fi = generaArchivos(uploadItem.getFile(), 1,dirCompania, hora);

                if (!fi.isIsSave()) {
                    rq.setMsg("Error al guardar archivo XML:" + msgErr);
                    rq.setSuccess(false);
                    return rq;
                }
                fi2 = generaArchivos(uploadItem2.getFile2(), 2,dirCompania, hora);
                if (!fi2.isIsSave()) {
                    rq.setMsg("Error al guardar archivo PDF:" + msgErr);
                    rq.setSuccess(false);
                    return rq;
                }
        
        }else{
            
                 
            
            if(!result2){
            
              nombreUuid = app.validaNombreUUidComprobante(uploadItem.getFile(), dirOutFileDocument, compania);
              
            }else{
                
                nombreUuid = app.validaNombreUUidComprobante33(uploadItem.getFile(), dirOutFileDocument, compania);
                
                
                      
                }
            
            System.out.println("nombreUuid: "+nombreUuid);
            
               fi = generaArchivos2(uploadItem.getFile(), 1,dirFecha,nombreUuid);

                if (!fi.isIsSave()) {
                    rq.setMsg("Error al guardar archivo XML:" + msgErr);
                    rq.setSuccess(false);
                    return rq;
                }
                fi2 = generaArchivos2(uploadItem2.getFile2(), 2,dirFecha,nombreUuid);
        //        System.out.println("res2:" + res2);
                if (!fi2.isIsSave()) {
                    rq.setMsg("Error al guardar archivo PDF:" + msgErr);
                    rq.setSuccess(false);
                    return rq;
                }
        
        }
        
        

        if (result.hasErrors()) {
            for (ObjectError error : result.getAllErrors()) {
                System.err.println("Error: " + error.getCode() + " - " + error.getDefaultMessage());
            }
            rq.setSuccess(isSave);
            rq.setMsg("Error al guardar el archivo.");

            return rq;
        }
        String idErr = "" + System.currentTimeMillis();
        app.setId(idErr);
       log.debug("pathXML:"+pathXML);
       PolizasInfo valida;
         if(!result2){
            valida = app.validaComprobante(compania, pathXML, fi2.getFile(), fi2.getPath());
       }else{
            valida = app.validaComprobante33(compania, pathXML, fi2.getFile(), fi2.getPath());
       }
        log.debug("valida:"+valida.getComprobante());
        log.debug("vali:"+valida.getInfTipo());
        log.debug("msgErr:"+valida.getMsgErr());
        
        if (valida.getInfTipo() ==1 || valida.getInfTipo() ==3) {
            rq.setMsg("Error al validar el XML ya fue cargado al portal");
            rq.setSuccess(false);
            return rq;
        }
        
        
        
         if(!result2){
             System.out.println("Soy version 32");
        if(valida.getComprobante()==null){
            rq.setMsg("Error al validar el XML:");
            rq.setSuccess(false);
            return rq;
        }
       // System.out.println("usuario:"+usuario);
        
       // System.out.println("prov.getRfc():"+valida.getComprobante().getEmisor().getRfc());
        //Valida que el RFC emisor sea de la secion
        if(!rfcProv.equals(valida.getComprobante().getEmisor().getRfc())){
             rq.setSuccess(false);
            rq.setMsg("ERROR RFC del Emisor no es valido");
            return rq;
        }
        
        //Valida que el RFC receptor sea de SHW
        
        Map provTerc = new HashMap();
           
               provTerc.put("rfc", rfcProv);
               provTerc.put("compania", compania);
               
                   List listPagTerc = processDao.getMapResult("BuscaPagoTercerosPortal", provTerc);
        
        if(listPagTerc.isEmpty()){
        
            if(!rfc.equals(valida.getComprobante().getReceptor().getRfc())){
                rq.setSuccess(false);
                rq.setMsg("ERROR RFC Receptor No valido");
                return rq;
            }
        }
            SimpleDateFormat format = new SimpleDateFormat("yyyy");
            String DateToStr = format.format(valida.getComprobante().getFecha());
            int year = Integer.parseInt(DateToStr);
            //int year = valida.getComprobante().getFecha().getYear();
            int mes = valida.getComprobante().getFecha().getMonth();
            String rfcE = valida.getComprobante().getEmisor().getRfc();
             //Valida que este cargando sólo Facturas del año en curso
        Calendar cal= Calendar.getInstance();
        int yearSys= cal.get(Calendar.YEAR);
             System.out.println("year xml "+year);
             System.out.println("year sys "+yearSys);
        if(year != yearSys){
            rq.setSuccess(false);
            rq.setMsg("ERROR Calendario No valido");
            return rq;
        }

           Map orden = new HashMap();
                 String buscaOrden = "";
               orden.put("total", valida.getComprobante().getTotal());
               orden.put("calendario", year);
               orden.put("periodo", mes);
               orden.put("rfc", rfcE);
               orden.put("compania", compania);
               
                   List listOrden = processDao.getMapResult("BuscaFolioOrden", orden);
                 if(listOrden.isEmpty()){
                     
                     ordenLogId.setCompania(compania);
                     ordenLogId.setFolio(BigDecimal.ZERO);
                     ordenLogId.setId(0);
                     ordenLogId.setUuid(nombreUuid);
                     ordenLog.setId(ordenLogId);
                     ordenLog.setCalendario(year);
                     ordenLog.setEstatus("RECHAZADO");
                     ordenLog.setImporteFactura(valida.getComprobante().getTotal());
                     ordenLog.setPeriodo(mes);
                     ordenLog.setRfc(rfc);
                     erpCxpOrdenCompraLogDao.save(ordenLog);
                     
                     rq.setSuccess(false);
                    rq.setMsg("El importe de la factura no corresponde al importe ingresado en el folio del periodo");
                    return rq;
                     
                     
                 
                 }
                     
                     
        //Obtener el uuid
         }else{
                System.out.println("Soy version 33");
               JAXBContext jaxbContext = JAXBContext.newInstance(com.feraz.mx.sat.cfdi.Comprobante.class);

                    Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
                    com.feraz.mx.sat.cfdi.Comprobante comp = (com.feraz.mx.sat.cfdi.Comprobante) jaxbUnmarshaller.unmarshal(new FileInputStream(pathXML));

             
              if(comp==null){
            rq.setMsg("Error al validar el XML:");
            rq.setSuccess(false);
            return rq;
        }
       // System.out.println("usuario:"+usuario);
        
       // System.out.println("prov.getRfc():"+valida.getComprobante().getEmisor().getRfc());
        //Valida que el RFC emisor sea de la secion
        if(!rfcProv.equals(comp.getEmisor().getRfc())){
             rq.setSuccess(false);
            rq.setMsg("ERROR RFC del Emisor no es valido");
            return rq;
        }
        
        //Valida que el RFC receptor sea de SHW
           Map provTerc = new HashMap();
           
               provTerc.put("rfc", rfcProv);
               provTerc.put("compania", compania);
               
                   List listPagTerc = processDao.getMapResult("BuscaPagoTercerosPortal", provTerc);
        
        if(listPagTerc.isEmpty()){
            if(!rfc.equals(comp.getReceptor().getRfc())){
                rq.setSuccess(false);
                rq.setMsg("ERROR RFC Receptor No valido");
                return rq;
            }
        }
        int year = comp.getFecha().getYear();
        int mes = comp.getFecha().getMonth();
        String rfcE = comp.getEmisor().getRfc();
       
        //Valida que este cargando sólo Facturas del año en curso
        Calendar cal= Calendar.getInstance();
        int yearSys= cal.get(Calendar.YEAR);
        System.out.println("year xml "+year);
        System.out.println("year sys "+yearSys);
        if(year != yearSys){
            rq.setSuccess(false);
            rq.setMsg("ERROR Calendario No valido");
            return rq;
        }
        
           Map orden = new HashMap();
                 String buscaOrden = "";
               orden.put("total", comp.getTotal());
               orden.put("calendario", year);
               orden.put("periodo", mes);
               orden.put("rfc", rfcE);
               orden.put("compania", compania);
               
                   List listOrden = processDao.getMapResult("BuscaFolioOrden", orden);
                 if(listOrden.isEmpty()){
                     
                     ordenLogId.setCompania(compania);
                     ordenLogId.setFolio(BigDecimal.ZERO);
                     ordenLogId.setId(0);
                     ordenLogId.setUuid(nombreUuid);
                     ordenLog.setId(ordenLogId);
                     ordenLog.setCalendario(year);
                     ordenLog.setEstatus("RECHAZADO");
                     ordenLog.setImporteFactura(comp.getTotal());
                     ordenLog.setPeriodo(mes);
                     ordenLog.setRfc(rfc);
                     erpCxpOrdenCompraLogDao.save(ordenLog);
                     
                     rq.setSuccess(false);
                    rq.setMsg("El importe de la factura no corresponde al importe ingresado en el folio del periodo");
                    return rq;
                     
                     
                 
                 }
                     
                     
                 
                   
                      
            
        
            
            
        }
        String uuid =app.getUUID(pathXML);
        
        //Valida en el SAT
        
        log.debug("Validando en el SAT....");
        if(!result2){
        ValidaSAT vl = new ValidaSAT();
        ResponseWSValida res = vl.validaCFDIWSSAT(valida.getComprobante().getEmisor().getRfc(), valida.getComprobante().getReceptor().getRfc(),""+ valida.getComprobante().getTotal(),uuid);
        
        
        log.debug("res.getEstatus():"+res.getEstatus());
               
               
        if (res.getEstatus().equals("Vigente")) {
            System.out.println("CFDI se Valido Bien en el SAT");
        } else {
            rq.setSuccess(false);
            rq.setMsg("ERROR Factuara no se encontro valida en el SAT.");
            return rq;
        }
        }
        app.setNombrePdf(fi2.getFile());
        app.setPathPdf(fi2.getPath());
        app.setUsuario(usuario);
        app.setTipoCarga("1");

          int data;
        if(!result2){
           data = app.cargaComprobante(pathXML, compania, concGasto,"PRTL");
        }else{
           data = app.cargaComprobante33(pathXML, compania, concGasto,"","PRTL");
        }
        log.debug("Result data: "+data);
        Querys que = new Querys();
            String store = que.getSQL("CONTA_INSERTA_FECHAVENC");
            
           
             
             Map client = new HashMap();
                 String buscaidProv = "";
               client.put("compania", compania);
               client.put("rfc", rfcProv);

                   List listidProv = processDao.getMapResult("BuscaIdProv", client);
                   
                
                             Map resultProv = (HashMap) listidProv.get(0);
                  
                  buscaidProv = resultProv.get("ID_CLIENTE").toString();
                  
             parametros.put("compania", compania);
             parametros.put("numero", data);
             parametros.put("idCliente", buscaidProv);
             parametros.put("origen", "FE");

                
        
        if (data >= 1) {

            processDao.execute(store, parametros);
            rq.setSuccess(true);
            rq.setMsg(Integer.toString(data));

            return rq;
        } else {
            rq.setSuccess(false);
            rq.setMsg("Error al cargar comprobante");

            return rq;
        }

    }
    
    
    
      @RequestMapping(value = "/savePortalFolioCompl", method = RequestMethod.POST)
    public @ResponseBody
    ResponseQuery3 createPortalFolioCompl(
            @RequestParam("COMPANIA") String compania1,
            @RequestParam("RFC") String rfc,
            @RequestParam("archCOMPANIA") String archCOMPANIA,
            @RequestParam("RFC_PROV") String rfcProv,
            
            @ModelAttribute("proveedor")  ErpCClientes prov,
            FileUploadBean uploadItem,
            FileUploadBean2 uploadItem2,
            BindingResult result,
            WebRequest webRequest,
            Model model) throws IOException, JAXBException {
        log.debug("---------------------------------------------------------------------");
        log.debug("compania1:"+compania1);
        log.debug("rfc:"+rfc);
        log.debug("archCOMPANIA:"+archCOMPANIA);
        
        Map parametros = processDao.paramToMap(webRequest.getParameterMap());
        String concGasto = null;
        
        String hora;
        String rfcProveedor = "";

          hora = "" + System.currentTimeMillis();

        boolean isSave = true;
        SimpleDateFormat formatoDelTexto2 = new SimpleDateFormat("dd/MM/yyyy");

        ResponseQuery3 rq = new ResponseQuery3();
        
        if (prov == null) {
            rq.setMsg("Sesion no valida");
            rq.setSuccess(false);
            return rq;
        }

        String compania = model.asMap().get("compania").toString();
         String usuario = model.asMap().get("usuario").toString();
         
         log.debug("usuario:"+usuario);
         log.debug("compania session:"+compania);
         
         String dirCompania;
         
          dirCompania = compania;
        FileInfo fi;
        FileInfo fi2;
        
        ErpCxpOrdenCompraLog ordenLog = new ErpCxpOrdenCompraLog();
        ErpCxpOrdenCompraLogId ordenLogId = new ErpCxpOrdenCompraLogId();
        
         boolean result1 = validaVersionCFDI.verision32File(uploadItem.getFile());
         boolean result2 = validaVersionCFDI.version33File(uploadItem.getFile());
         
         
         System.out.println("result1: " +result1);
         System.out.println("result2: " +result2);
        
        String dirFecha = "";
        String nombreUuid = "";
        
        if(!result2){
        
               dirFecha = app.validaFechaComprobante(uploadItem.getFile(), dirOutFileDocument, compania);
        }else{
        
               dirFecha = app.validaFechaComprobante33(uploadItem.getFile(), dirOutFileDocument, compania);
        }
        if (dirFecha.equalsIgnoreCase("")){
            
                fi = generaArchivos(uploadItem.getFile(), 1,dirCompania, hora);

                if (!fi.isIsSave()) {
                    rq.setMsg("Error al guardar archivo XML:" + msgErr);
                    rq.setSuccess(false);
                    return rq;
                }
                fi2 = generaArchivos(uploadItem2.getFile2(), 2,dirCompania, hora);
                if (!fi2.isIsSave()) {
                    rq.setMsg("Error al guardar archivo PDF:" + msgErr);
                    rq.setSuccess(false);
                    return rq;
                }
        
        }else{
            
                 
            
            if(!result2){
            
              nombreUuid = app.validaNombreUUidComprobante(uploadItem.getFile(), dirOutFileDocument, compania);
              
            }else{
                
                nombreUuid = app.validaNombreUUidComprobante33(uploadItem.getFile(), dirOutFileDocument, compania);
                
                
                      
                }
            
            System.out.println("nombreUuid: "+nombreUuid);
            
               fi = generaArchivos2(uploadItem.getFile(), 1,dirFecha,nombreUuid);

                if (!fi.isIsSave()) {
                    rq.setMsg("Error al guardar archivo XML:" + msgErr);
                    rq.setSuccess(false);
                    return rq;
                }
                fi2 = generaArchivos2(uploadItem2.getFile2(), 2,dirFecha,nombreUuid);
        //        System.out.println("res2:" + res2);
                if (!fi2.isIsSave()) {
                    rq.setMsg("Error al guardar archivo PDF:" + msgErr);
                    rq.setSuccess(false);
                    return rq;
                }
        
        }
        
        

        if (result.hasErrors()) {
            for (ObjectError error : result.getAllErrors()) {
                System.err.println("Error: " + error.getCode() + " - " + error.getDefaultMessage());
            }
            rq.setSuccess(isSave);
            rq.setMsg("Error al guardar el archivo.");

            return rq;
        }
        String idErr = "" + System.currentTimeMillis();
        app.setId(idErr);
       log.debug("pathXML:"+pathXML);
       PolizasInfo valida;
         if(!result2){
            valida = app.validaComprobante(compania, pathXML, fi2.getFile(), fi2.getPath());
       }else{
            valida = app.validaComprobante33(compania, pathXML, fi2.getFile(), fi2.getPath());
       }
        log.debug("valida:"+valida.getComprobante());
        log.debug("vali:"+valida.getInfTipo());
        log.debug("msgErr:"+valida.getMsgErr());
        
        if (valida.getInfTipo() ==1 || valida.getInfTipo() ==3) {
            rq.setMsg("Error al validar el XML ya fue cargado al portal");
            rq.setSuccess(false);
            return rq;
        }
        
        
        
         if(!result2){
             System.out.println("Soy version 32");
        if(valida.getComprobante()==null){
            rq.setMsg("Error al validar el XML:");
            rq.setSuccess(false);
            return rq;
        }
       // System.out.println("usuario:"+usuario);
        
       // System.out.println("prov.getRfc():"+valida.getComprobante().getEmisor().getRfc());
        //Valida que el RFC emisor sea de la secion
        if(!rfcProv.equals(valida.getComprobante().getEmisor().getRfc())){
             rq.setSuccess(false);
            rq.setMsg("ERROR RFC del Emisor no es valido");
            return rq;
        }
        
        //Valida que el RFC receptor sea de SHW
        if(!rfc.equals(valida.getComprobante().getReceptor().getRfc())){
            rq.setSuccess(false);
            rq.setMsg("ERROR RFC Receptor No valido");
            return rq;
        }
            SimpleDateFormat format = new SimpleDateFormat("yyyy");
            String DateToStr = format.format(valida.getComprobante().getFecha());
            int year = Integer.parseInt(DateToStr);
            //int year = valida.getComprobante().getFecha().getYear();
            int mes = valida.getComprobante().getFecha().getMonth();
            String rfcE = valida.getComprobante().getEmisor().getRfc();
             //Valida que este cargando sólo Facturas del año en curso
        Calendar cal= Calendar.getInstance();
        int yearSys= cal.get(Calendar.YEAR);
             System.out.println("year xml "+year);
             System.out.println("year sys "+yearSys);
        if(year != yearSys){
            rq.setSuccess(false);
            rq.setMsg("ERROR Calendario No valido");
            return rq;
        }

        
                     
        //Obtener el uuid
         }else{
                System.out.println("Soy version 33");
               JAXBContext jaxbContext = JAXBContext.newInstance(com.feraz.mx.sat.cfdi.Comprobante.class);

                    Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
                    com.feraz.mx.sat.cfdi.Comprobante comp = (com.feraz.mx.sat.cfdi.Comprobante) jaxbUnmarshaller.unmarshal(new FileInputStream(pathXML));

             
              if(comp==null){
            rq.setMsg("Error al validar el XML:");
            rq.setSuccess(false);
            return rq;
        }
       // System.out.println("usuario:"+usuario);
        
       // System.out.println("prov.getRfc():"+valida.getComprobante().getEmisor().getRfc());
        //Valida que el RFC emisor sea de la secion
        if(!rfcProv.equals(comp.getEmisor().getRfc())){
             rq.setSuccess(false);
            rq.setMsg("ERROR RFC del Emisor no es valido");
            return rq;
        }
        
        //Valida que el RFC receptor sea de SHW
        if(!rfc.equals(comp.getReceptor().getRfc())){
            rq.setSuccess(false);
            rq.setMsg("ERROR RFC Receptor No valido");
            return rq;
        }
        int year = comp.getFecha().getYear();
        int mes = comp.getFecha().getMonth();
        String rfcE = comp.getEmisor().getRfc();
       
        //Valida que este cargando sólo Facturas del año en curso
        Calendar cal= Calendar.getInstance();
        int yearSys= cal.get(Calendar.YEAR);
        System.out.println("year xml "+year);
        System.out.println("year sys "+yearSys);
        if(year != yearSys){
            rq.setSuccess(false);
            rq.setMsg("ERROR Calendario No valido");
            return rq;
        }
        
         
            
        }
        String uuid =app.getUUID(pathXML);
        
        //Valida en el SAT
        
        log.debug("Validando en el SAT....");
        if(!result2){
        ValidaSAT vl = new ValidaSAT();
        ResponseWSValida res = vl.validaCFDIWSSAT(valida.getComprobante().getEmisor().getRfc(), valida.getComprobante().getReceptor().getRfc(),""+ valida.getComprobante().getTotal(),uuid);
        
        
        log.debug("res.getEstatus():"+res.getEstatus());
               
               
        if (res.getEstatus().equals("Vigente")) {
            System.out.println("CFDI se Valido Bien en el SAT");
        } else {
            rq.setSuccess(false);
            rq.setMsg("ERROR Factuara no se encontro valida en el SAT.");
            return rq;
        }
        }
        app.setNombrePdf(fi2.getFile());
        app.setPathPdf(fi2.getPath());
        app.setUsuario(usuario);
        app.setTipoCarga("1");

          int data;
        if(!result2){
           data = app.cargaComprobante(pathXML, compania, concGasto,"PRTL");
        }else{
           data = app.cargaComprobante33(pathXML, compania, concGasto,"","PRTL");
        }
        log.debug("Result data: "+data);
        Querys que = new Querys();
            String store = que.getSQL("CONTA_INSERTA_FECHAVENC");
            
           
             
             Map client = new HashMap();
                 String buscaidProv = "";
               client.put("compania", compania);
               client.put("rfc", rfcProv);

                   List listidProv = processDao.getMapResult("BuscaIdProv", client);
                   
                
                             Map resultProv = (HashMap) listidProv.get(0);
                  
                  buscaidProv = resultProv.get("ID_CLIENTE").toString();
                  
             parametros.put("compania", compania);
             parametros.put("numero", data);
             parametros.put("idCliente", buscaidProv);
             parametros.put("origen", "FE");

                
        
        if (data >= 1) {

            processDao.execute(store, parametros);
            rq.setSuccess(true);
            rq.setMsg(Integer.toString(data));

            return rq;
        } else {
            rq.setSuccess(false);
            rq.setMsg("Error al cargar comprobante");

            return rq;
        }

    }
    
    
             @RequestMapping(value = "/savePortalAereoVal", method = RequestMethod.POST)
    public @ResponseBody
    ResponseQuery3 createPortalAereoVal(
            @RequestParam("COMPANIA") String compania1,
            @RequestParam("RFC") String rfc,
            @RequestParam("archCOMPANIA") String archCOMPANIA,
            @RequestParam("RFC_PROV") String rfcProv,
            @RequestParam("ORDEN_COMPRA") String ordenCompra,
            @RequestParam("TIPO_FACTURA_FE") String tipoFactura,
            
            
            @ModelAttribute("proveedor")  ErpCClientes prov,
            FileUploadBean uploadItem,
            FileUploadBean2 uploadItem2,
            FileUploadBean2 uploadItem3,
            BindingResult result,
            WebRequest webRequest,
            Model model) throws IOException, JAXBException {
        log.debug("---------------------------------------------------------------------");
        log.debug("compania1:"+compania1);
        log.debug("rfc:"+rfc);
        log.debug("archCOMPANIA:"+archCOMPANIA);
        
        Map parametros = processDao.paramToMap(webRequest.getParameterMap());
        String concGasto = null;
        
        String hora;
        String rfcProveedor = "";

          hora = "" + System.currentTimeMillis();

        boolean isSave = true;
        SimpleDateFormat formatoDelTexto2 = new SimpleDateFormat("dd/MM/yyyy");

        ResponseQuery3 rq = new ResponseQuery3();
        
        if (prov == null) {
            rq.setMsg("Sesion no valida");
            rq.setSuccess(false);
            return rq;
        }

        String compania = model.asMap().get("compania").toString();
         String usuario = model.asMap().get("usuario").toString();
         
         log.debug("usuario:"+usuario);
         log.debug("compania session:"+compania);
         
         String dirCompania;
         
          dirCompania = compania;
        FileInfo fi;
        FileInfo fi2;
        
        ErpCxpOrdenCompraLog ordenLog = new ErpCxpOrdenCompraLog();
        ErpCxpOrdenCompraLogId ordenLogId = new ErpCxpOrdenCompraLogId();
        
         boolean result1 = validaVersionCFDI.verision32File(uploadItem.getFile());
         boolean result2 = validaVersionCFDI.version33File(uploadItem.getFile());
         boolean result4 = validaVersionCFDI.version4File(uploadItem.getFile());
         
         System.out.println("result1: " +result1);
         System.out.println("result2: " +result2);
         System.out.println("result4: " +result4);
        
        String dirFecha = "";
        String nombreUuid = "";
        
         if(!result2 && !result4 && result1){
        
               dirFecha = app.validaFechaComprobante(uploadItem.getFile(), dirOutFileDocument, compania);
        }
            
        if(result2 && !result4 && !result1){
        
               dirFecha = app.validaFechaComprobante33(uploadItem.getFile(), dirOutFileDocument, compania);
            
        }
        
        if(!result2 && result4 && !result1){
            
            dirFecha = app.validaFechaComprobante4(uploadItem.getFile(), dirOutFileDocument, compania);

            
        }
            
            
        if (dirFecha.equalsIgnoreCase("")){
            
                fi = generaArchivos(uploadItem.getFile(), 1,dirCompania, hora);

                if (!fi.isIsSave()) {
                    rq.setMsg("Error al guardar archivo XML:" + msgErr);
                    rq.setSuccess(false);
                    return rq;
                }
                fi2 = generaArchivos(uploadItem2.getFile2(), 2,dirCompania, hora);
                if (!fi2.isIsSave()) {
                    rq.setMsg("Error al guardar archivo PDF:" + msgErr);
                    rq.setSuccess(false);
                    return rq;
                }
        
        }else{
            
                 
            
             if(!result2 && !result4 && result1){
            
              nombreUuid = app.validaNombreUUidComprobante(uploadItem.getFile(), dirOutFileDocument, compania);
              
            }
            
            if(result2 && !result4 && !result1){
        
                nombreUuid = app.validaNombreUUidComprobante33(uploadItem.getFile(), dirOutFileDocument, compania);

            
            }

            if(!result2 && result4 && !result1){

                 nombreUuid = app.validaNombreUUidComprobante4(uploadItem.getFile(), dirOutFileDocument, compania);

            }
                
            
            System.out.println("nombreUuid: "+nombreUuid);
            
               fi = generaArchivos2(uploadItem.getFile(), 1,dirFecha,nombreUuid);

                if (!fi.isIsSave()) {
                    rq.setMsg("Error al guardar archivo XML:" + msgErr);
                    rq.setSuccess(false);
                    return rq;
                }
                fi2 = generaArchivos2(uploadItem2.getFile2(), 2,dirFecha,nombreUuid);
        //        System.out.println("res2:" + res2);
                if (!fi2.isIsSave()) {
                    rq.setMsg("Error al guardar archivo PDF:" + msgErr);
                    rq.setSuccess(false);
                    return rq;
                }
        
        }
        
        

        if (result.hasErrors()) {
            for (ObjectError error : result.getAllErrors()) {
                System.err.println("Error: " + error.getCode() + " - " + error.getDefaultMessage());
            }
            rq.setSuccess(isSave);
            rq.setMsg("Error al guardar el archivo.");

            return rq;
        }
        String idErr = "" + System.currentTimeMillis();
        app.setId(idErr);
       log.debug("pathXML:"+pathXML);
       PolizasInfo valida;
         if(!result2 && !result4 && result1){
            
                valida = app.validaComprobante(compania, pathXML, fi2.getFile(), fi2.getPath());
              
            }else{
            
               if(result2 && !result4 && !result1){
        
                valida = app.validaComprobante33(compania, pathXML, fi2.getFile(), fi2.getPath());

            
                }else{
                   
                   if(!result2 && result4 && !result1){

                        valida = app.validaComprobante4(compania, pathXML, fi2.getFile(), fi2.getPath());

                   }else{
                   
                       valida = app.validaComprobante33(compania, pathXML, fi2.getFile(), fi2.getPath());
                   
                   }
               
               
                }
            
            }
            
            

            
        log.debug("valida:"+valida.getComprobante());
        log.debug("vali:"+valida.getInfTipo());
        log.debug("msgErr:"+valida.getMsgErr());
        
        if (valida.getInfTipo() ==1 || valida.getInfTipo() ==3) {
            rq.setMsg("Error al validar el XML ya fue cargado al portal");
            rq.setSuccess(false);
            return rq;
        }
        
        
        
          if(!result2 && !result4 && result1){
             System.out.println("Soy version 32");
        if(valida.getComprobante()==null){
            rq.setMsg("Error al validar el XML:");
            rq.setSuccess(false);
            return rq;
        }
       // System.out.println("usuario:"+usuario);
        
       // System.out.println("prov.getRfc():"+valida.getComprobante().getEmisor().getRfc());
        //Valida que el RFC emisor sea de la secion
        if(!rfcProv.equals(valida.getComprobante().getEmisor().getRfc())){
             rq.setSuccess(false);
            rq.setMsg("ERROR RFC del Emisor no es valido");
            return rq;
        }
        
        //Valida que el RFC receptor sea de SHW
        if(!rfc.equals(valida.getComprobante().getReceptor().getRfc())){
            rq.setSuccess(false);
            rq.setMsg("ERROR RFC Receptor No valido");
            return rq;
        }
            SimpleDateFormat format = new SimpleDateFormat("yyyy");
            String DateToStr = format.format(valida.getComprobante().getFecha());
            int year = Integer.parseInt(DateToStr);
            //int year = valida.getComprobante().getFecha().getYear();
            int mes = valida.getComprobante().getFecha().getMonth();
            String rfcE = valida.getComprobante().getEmisor().getRfc();
             //Valida que este cargando sólo Facturas del año en curso
        Calendar cal= Calendar.getInstance();
        int yearSys= cal.get(Calendar.YEAR);
             System.out.println("year xml "+year);
             System.out.println("year sys "+yearSys);
        if(year != yearSys){
            rq.setSuccess(false);
            rq.setMsg("ERROR Calendario No valido");
            return rq;
        }

           Map orden = new HashMap();
                 String buscaOrden = "";
               orden.put("total", valida.getComprobante().getTotal());
               orden.put("calendario", year);
               orden.put("periodo", mes);
               orden.put("rfc", rfcE);
               orden.put("compania", compania);
               orden.put("orden", ordenCompra);
               
                   List listOrden = processDao.getMapResult("BuscaOrdenCompraId", orden);
                 if(listOrden.isEmpty()){
                  
                     
                     rq.setSuccess(false);
                    rq.setMsg("La orden de compra es invalida");
                    return rq;
                     
                     
                 
                 }
                     
                     
        //Obtener el uuid
         }
         
         
         
        if(result2 && !result4 && !result1){
                System.out.println("Soy version 33");
               JAXBContext jaxbContext = JAXBContext.newInstance(com.feraz.mx.sat.cfdi.Comprobante.class);

                    Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
                    com.feraz.mx.sat.cfdi.Comprobante comp = (com.feraz.mx.sat.cfdi.Comprobante) jaxbUnmarshaller.unmarshal(new FileInputStream(pathXML));

             
              if(comp==null){
            rq.setMsg("Error al validar el XML:");
            rq.setSuccess(false);
            return rq;
        }
       // System.out.println("usuario:"+usuario);
        
       // System.out.println("prov.getRfc():"+valida.getComprobante().getEmisor().getRfc());
        //Valida que el RFC emisor sea de la secion
        if(!rfcProv.equals(comp.getEmisor().getRfc())){
             rq.setSuccess(false);
            rq.setMsg("ERROR RFC del Emisor no es valido");
            return rq;
        }
        
        //Valida que el RFC receptor sea de SHW
        if(!rfc.equals(comp.getReceptor().getRfc())){
            rq.setSuccess(false);
            rq.setMsg("ERROR RFC Receptor No valido");
            return rq;
        }
        int year = comp.getFecha().getYear();
        int mes = comp.getFecha().getMonth();
        String rfcE = comp.getEmisor().getRfc();
       
        //Valida que este cargando sólo Facturas del año en curso
        Calendar cal= Calendar.getInstance();
        int yearSys= cal.get(Calendar.YEAR);
        System.out.println("year xml "+year);
        System.out.println("year sys "+yearSys);
//        if(year != yearSys){
//            rq.setSuccess(false);
//            rq.setMsg("ERROR Calendario No valido");
//            return rq;
//        }
        
        //if(tipoFactura.equalsIgnoreCase("0")){
           Map orden = new HashMap();
                 String buscaOrden = "";
               orden.put("total", comp.getTotal());
               orden.put("calendario", year);
               orden.put("periodo", mes);
               orden.put("rfc", rfcE);
               orden.put("compania", compania);
               orden.put("orden", ordenCompra);
               
                   List listOrden = processDao.getMapResult("BuscaOrdenCompraId", orden);
                 if(listOrden.isEmpty()){
                     
                  
                     
                     rq.setSuccess(false);
                    rq.setMsg("La orden de compra es invalida");
                    return rq;
                     
                     
                 
                 }
        //}
        
                     
                     
                 
                   
                      
            
        
            
            
        }
        
       if(!result2 && result4 && !result1){
                System.out.println("Soy version 4");

                    JAXBContext jaxbContext = JAXBContext.newInstance(Comprobante4.class);

		Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
		Comprobante4 comp = (Comprobante4) jaxbUnmarshaller.unmarshal(new FileInputStream(pathXML));
             
              if(comp==null){
            rq.setMsg("Error al validar el XML:");
            rq.setSuccess(false);
            return rq;
        }
       // System.out.println("usuario:"+usuario);
        
       // System.out.println("prov.getRfc():"+valida.getComprobante().getEmisor().getRfc());
        //Valida que el RFC emisor sea de la secion
        if(!rfcProv.equals(comp.getEmisor().getRfc())){
             rq.setSuccess(false);
            rq.setMsg("ERROR RFC del Emisor no es valido");
            return rq;
        }
        
        //Valida que el RFC receptor sea de SHW
        if(!rfc.equals(comp.getReceptor().getRfc())){
            rq.setSuccess(false);
            rq.setMsg("ERROR RFC Receptor No valido");
            return rq;
        }
        int year = comp.getFecha().getYear();
        int mes = comp.getFecha().getMonth();
        String rfcE = comp.getEmisor().getRfc();
       
        //Valida que este cargando sólo Facturas del año en curso
        Calendar cal= Calendar.getInstance();
        int yearSys= cal.get(Calendar.YEAR);
        System.out.println("year xml "+year);
        System.out.println("year sys "+yearSys);
//        if(year != yearSys){
//            rq.setSuccess(false);
//            rq.setMsg("ERROR Calendario No valido");
//            return rq;
//        }
        
        //if(tipoFactura.equalsIgnoreCase("0")){
           Map orden = new HashMap();
                 String buscaOrden = "";
               orden.put("total", comp.getTotal());
               orden.put("calendario", year);
               orden.put("periodo", mes);
               orden.put("rfc", rfcE);
               orden.put("compania", compania);
               orden.put("orden", ordenCompra);
               
                   List listOrden = processDao.getMapResult("BuscaOrdenCompraId", orden);
                 if(listOrden.isEmpty()){
                     
                  
                     
                     rq.setSuccess(false);
                    rq.setMsg("La orden de compra es invalida");
                    return rq;
                     
                     
                 
                 }
        //}
        
                     
                     
                 
                   
                      
            
        
            
            
        }
        
        String uuid ="";
        
         if(!result2 && result4 && !result1){
            
            uuid =app.getUUID4(pathXML);
        
        }else{
            uuid =app.getUUID(pathXML);
        }
        
        //Valida en el SAT
        
        log.debug("Validando en el SAT....");
        if(!result2 && !result4){
        ValidaSAT vl = new ValidaSAT();
        ResponseWSValida res = vl.validaCFDIWSSAT(valida.getComprobante().getEmisor().getRfc(), valida.getComprobante().getReceptor().getRfc(),""+ valida.getComprobante().getTotal(),uuid);
        
        
        log.debug("res.getEstatus():"+res.getEstatus());
               
               
        if (res.getEstatus().equals("Vigente")) {
            System.out.println("CFDI se Valido Bien en el SAT");
        } else {
            rq.setSuccess(false);
            rq.setMsg("ERROR Factuara no se encontro valida en el SAT.");
            return rq;
        }
        }
        app.setNombrePdf(fi2.getFile());
        app.setPathPdf(fi2.getPath());
        app.setUsuario(usuario);
        app.setTipoCarga("1");

           int data = 0;
          
            if(!result2 && !result4 && result1){
            
              data = app.cargaComprobante(pathXML, compania, concGasto,"PRTL");
              
            }
            
            if(result2 && !result4 && !result1){
        
                data = app.cargaComprobante33(pathXML, compania, concGasto,"","PRTL");

            
            }

            if(!result2 && result4 && !result1){

                 data = app.cargaComprobante4(pathXML, compania, concGasto,"","PRTL");

            }
        log.debug("Result data: "+data);
        Querys que = new Querys();
            String store = que.getSQL("CONTA_INSERTA_FECHAVENC");
            
           
            // if(tipoFactura.equalsIgnoreCase("0")){
             
                    Map client = new HashMap();
                        String buscaidProv = "";
                      client.put("compania", compania);
                      client.put("rfc", rfcProv);

                          List listidProv = processDao.getMapResult("BuscaIdProv", client);


                                    Map resultProv = (HashMap) listidProv.get(0);

                         buscaidProv = resultProv.get("ID_CLIENTE").toString();

                    parametros.put("compania", compania);
                    parametros.put("numero", data);
                    parametros.put("idCliente", buscaidProv);
                    parametros.put("origen", "FE");

                     fi2 = generaArchivosOrden(uploadItem3.getFile2(), ordenCompra,dirCompania,data);
                       if (!fi2.isIsSave()) {
                           rq.setMsg("Error al guardar archivo PDF de orden:" + msgErr);
                           rq.setSuccess(false);
                           return rq;
                       }

           //  } 
        
        if (data >= 1) {

            processDao.execute(store, parametros);
            rq.setSuccess(true);
            rq.setMsg(Integer.toString(data));

            return rq;
        } else {
            rq.setSuccess(false);
            rq.setMsg("Error al cargar comprobante");

            return rq;
        }

    }
    
    
      @RequestMapping(value = "/savePortalProveedorStandart", method = RequestMethod.POST)
    public @ResponseBody
    ResponseQuery3 createPortalProveedorStandart(
            @RequestParam("COMPANIA") String compania1,
            @RequestParam("RFC") String rfc,
            @RequestParam("archCOMPANIA") String archCOMPANIA,
            @RequestParam("RFC_PROV") String rfcProv,
            @RequestParam("ORDEN_COMPRA") String ordenCompra,
            @RequestParam("TIPO_FACTURA_FE") String tipoFactura,
            
            
            @ModelAttribute("proveedor")  ErpCClientes prov,
            FileUploadBean uploadItem,
            FileUploadBean2 uploadItem2,
            FileUploadBean2 uploadItem3,
            BindingResult result,
            WebRequest webRequest,
            Model model) throws IOException, JAXBException {
        log.debug("---------------------------------------------------------------------");
        log.debug("compania1:"+compania1);
        log.debug("rfc:"+rfc);
        log.debug("archCOMPANIA:"+archCOMPANIA);
        
        Map parametros = processDao.paramToMap(webRequest.getParameterMap());
        String concGasto = null;
        
        String hora;
        String rfcProveedor = "";

          hora = "" + System.currentTimeMillis();

        boolean isSave = true;
        SimpleDateFormat formatoDelTexto2 = new SimpleDateFormat("dd/MM/yyyy");

        ResponseQuery3 rq = new ResponseQuery3();
        
        if (prov == null) {
            rq.setMsg("Sesion no valida");
            rq.setSuccess(false);
            return rq;
        }

        String compania = model.asMap().get("compania").toString();
         String usuario = model.asMap().get("usuario").toString();
         
         log.debug("usuario:"+usuario);
         log.debug("compania session:"+compania);
         
         String dirCompania;
         
          dirCompania = compania;
        FileInfo fi;
        FileInfo fi2;
        
        ErpCxpOrdenCompraLog ordenLog = new ErpCxpOrdenCompraLog();
        ErpCxpOrdenCompraLogId ordenLogId = new ErpCxpOrdenCompraLogId();
        
         boolean result1 = validaVersionCFDI.verision32File(uploadItem.getFile());
         boolean result2 = validaVersionCFDI.version33File(uploadItem.getFile());
         boolean result4 = validaVersionCFDI.version4File(uploadItem.getFile());
         
         System.out.println("result1: " +result1);
         System.out.println("result2: " +result2);
         System.out.println("result4: " +result4);
        
        String dirFecha = "";
        String nombreUuid = "";
        
         if(!result2 && !result4 && result1){
        
               dirFecha = app.validaFechaComprobante(uploadItem.getFile(), dirOutFileDocument, compania);
        }
            
        if(result2 && !result4 && !result1){
        
               dirFecha = app.validaFechaComprobante33(uploadItem.getFile(), dirOutFileDocument, compania);
            
        }
        
        if(!result2 && result4 && !result1){
            
            dirFecha = app.validaFechaComprobante4(uploadItem.getFile(), dirOutFileDocument, compania);

            
        }
            
            
        if (dirFecha.equalsIgnoreCase("")){
            
                fi = generaArchivos(uploadItem.getFile(), 1,dirCompania, hora);

                if (!fi.isIsSave()) {
                    rq.setMsg("Error al guardar archivo XML:" + msgErr);
                    rq.setSuccess(false);
                    return rq;
                }
                fi2 = generaArchivos(uploadItem2.getFile2(), 2,dirCompania, hora);
                if (!fi2.isIsSave()) {
                    rq.setMsg("Error al guardar archivo PDF:" + msgErr);
                    rq.setSuccess(false);
                    return rq;
                }
        
        }else{
            
                 
            
             if(!result2 && !result4 && result1){
            
              nombreUuid = app.validaNombreUUidComprobante(uploadItem.getFile(), dirOutFileDocument, compania);
              
            }
            
            if(result2 && !result4 && !result1){
        
                nombreUuid = app.validaNombreUUidComprobante33(uploadItem.getFile(), dirOutFileDocument, compania);

            
            }

            if(!result2 && result4 && !result1){

                 nombreUuid = app.validaNombreUUidComprobante4(uploadItem.getFile(), dirOutFileDocument, compania);

            }
                
            
            System.out.println("nombreUuid: "+nombreUuid);
            
               fi = generaArchivos2(uploadItem.getFile(), 1,dirFecha,nombreUuid);

                if (!fi.isIsSave()) {
                    rq.setMsg("Error al guardar archivo XML:" + msgErr);
                    rq.setSuccess(false);
                    return rq;
                }
                fi2 = generaArchivos2(uploadItem2.getFile2(), 2,dirFecha,nombreUuid);
        //        System.out.println("res2:" + res2);
                if (!fi2.isIsSave()) {
                    rq.setMsg("Error al guardar archivo PDF:" + msgErr);
                    rq.setSuccess(false);
                    return rq;
                }
        
        }
        
        

        if (result.hasErrors()) {
            for (ObjectError error : result.getAllErrors()) {
                System.err.println("Error: " + error.getCode() + " - " + error.getDefaultMessage());
            }
            rq.setSuccess(isSave);
            rq.setMsg("Error al guardar el archivo.");

            return rq;
        }
        String idErr = "" + System.currentTimeMillis();
        app.setId(idErr);
       log.debug("pathXML:"+pathXML);
       PolizasInfo valida;
         if(!result2 && !result4 && result1){
            
                valida = app.validaComprobante(compania, pathXML, fi2.getFile(), fi2.getPath());
              
            }else{
            
               if(result2 && !result4 && !result1){
        
                valida = app.validaComprobante33(compania, pathXML, fi2.getFile(), fi2.getPath());

            
                }else{
                   
                   if(!result2 && result4 && !result1){

                        valida = app.validaComprobante4(compania, pathXML, fi2.getFile(), fi2.getPath());

                   }else{
                   
                       valida = app.validaComprobante33(compania, pathXML, fi2.getFile(), fi2.getPath());
                   
                   }
               
               
                }
            
            }
            
            

            
        log.debug("valida:"+valida.getComprobante());
        log.debug("vali:"+valida.getInfTipo());
        log.debug("msgErr:"+valida.getMsgErr());
        
        if (valida.getInfTipo() ==1 || valida.getInfTipo() ==3) {
            rq.setMsg("Error al validar el XML ya fue cargado al portal");
            rq.setSuccess(false);
            return rq;
        }
        
        
          
        
          if(!result2 && !result4 && result1){
             System.out.println("Soy version 32");
        if(valida.getComprobante()==null){
            rq.setMsg("Error al validar el XML:");
            rq.setSuccess(false);
            return rq;
        }
       // System.out.println("usuario:"+usuario);
        
       // System.out.println("prov.getRfc():"+valida.getComprobante().getEmisor().getRfc());
        //Valida que el RFC emisor sea de la secion
        if(!rfcProv.equals(valida.getComprobante().getEmisor().getRfc())){
             rq.setSuccess(false);
            rq.setMsg("ERROR RFC del Emisor no es valido");
            return rq;
        }
        
        //Valida que el RFC receptor sea de SHW
        if(!rfc.equals(valida.getComprobante().getReceptor().getRfc())){
            rq.setSuccess(false);
            rq.setMsg("ERROR RFC Receptor No valido");
            return rq;
        }
            SimpleDateFormat format = new SimpleDateFormat("yyyy");
            String DateToStr = format.format(valida.getComprobante().getFecha());
            int year = Integer.parseInt(DateToStr);
            //int year = valida.getComprobante().getFecha().getYear();
            int mes = valida.getComprobante().getFecha().getMonth();
            String rfcE = valida.getComprobante().getEmisor().getRfc();
             //Valida que este cargando sólo Facturas del año en curso
        Calendar cal= Calendar.getInstance();
        int yearSys= cal.get(Calendar.YEAR);
             System.out.println("year xml "+year);
             System.out.println("year sys "+yearSys);
        if(year != yearSys){
            rq.setSuccess(false);
            rq.setMsg("ERROR Calendario No valido");
            return rq;
        }
        
        
         
                if(tipoFactura.equalsIgnoreCase("0")){
           Map orden = new HashMap();
                 String buscaOrden = "";
               orden.put("total", valida.getComprobante().getTotal());
               orden.put("calendario", year);
               orden.put("periodo", mes);
               orden.put("rfc", rfcE);
               orden.put("compania", compania);
               orden.put("orden", ordenCompra);
               
                   List listOrden = processDao.getMapResult("BuscaOrdenCompraId", orden);
                 if(listOrden.isEmpty()){
                     
                  
                     
                     rq.setSuccess(false);
                    rq.setMsg("La orden de compra es invalida");
                    return rq;
                     
                     
                 
                 }
                 
                 
                Map ordenImp = new HashMap();
               ordenImp.put("total", valida.getComprobante().getTotal());
               ordenImp.put("calendario", year);
               ordenImp.put("periodo", mes);
               ordenImp.put("rfc", rfcE);
               ordenImp.put("compania", compania);
               ordenImp.put("orden", ordenCompra);
               ordenImp.put("metPago", valida.getComprobante().getMetodoDePago());
               
                   List listOrdenImp = processDao.getMapResult("BuscaOrdenImpPortal", ordenImp);
                 if(!listOrdenImp.isEmpty()){
                     
                  Map impOrden = (HashMap) listOrdenImp.get(0);
                         //   System.out.println("COMISION:" + factCompRel.get("COMISION"));
                            BigDecimal impToOrd = new BigDecimal(impOrden.get("IMPORTE_ORDEN").toString());
                           if(valida.getComprobante().getTotal().compareTo(impToOrd) != 0){
                               
                                rq.setSuccess(false);
                                rq.setMsg("El importe de la factura no coincide con el importe de la orden: "+impOrden.get("IMPORTE_ORDEN").toString());
                                return rq;
                     
                           
                           }
                           
                 
                 }
                 
                           
                                   
                     
                    
                     
                  List listOrdenImpMet = processDao.getMapResult("BuscaValMetPag", ordenImp);
                 if(!listOrdenImpMet.isEmpty()){
                     
                  Map impOrdenMet = (HashMap) listOrdenImpMet.get(0);
               
                           if(!impOrdenMet.get("ID_MET_PAGO").toString().equalsIgnoreCase(valida.getComprobante().getMetodoDePago())){
                               
                                rq.setSuccess(false);
                                rq.setMsg("Error, el metodo de pago es incorrecto, se espera: "+impOrdenMet.get("ID_MET_PAGO").toString());
                                return rq;
                     
                           
                           }
                 
                 
                 }
                         
         }
                     
                     
        //Obtener el uuid
         }
         
         
         
        if(result2 && !result4 && !result1){
                System.out.println("Soy version 33");
               JAXBContext jaxbContext = JAXBContext.newInstance(com.feraz.mx.sat.cfdi.Comprobante.class);

                    Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
                    com.feraz.mx.sat.cfdi.Comprobante comp = (com.feraz.mx.sat.cfdi.Comprobante) jaxbUnmarshaller.unmarshal(new FileInputStream(pathXML));

             
              if(comp==null){
            rq.setMsg("Error al validar el XML:");
            rq.setSuccess(false);
            return rq;
        }
       // System.out.println("usuario:"+usuario);
        
       // System.out.println("prov.getRfc():"+valida.getComprobante().getEmisor().getRfc());
        //Valida que el RFC emisor sea de la secion
        if(!rfcProv.equals(comp.getEmisor().getRfc())){
             rq.setSuccess(false);
            rq.setMsg("ERROR RFC del Emisor no es valido");
            return rq;
        }
        
        //Valida que el RFC receptor sea de SHW
        if(!rfc.equals(comp.getReceptor().getRfc())){
            rq.setSuccess(false);
            rq.setMsg("ERROR RFC Receptor No valido");
            return rq;
        }
        int year = comp.getFecha().getYear();
        int mes = comp.getFecha().getMonth();
        String rfcE = comp.getEmisor().getRfc();
       
        //Valida que este cargando sólo Facturas del año en curso
        Calendar cal= Calendar.getInstance();
        int yearSys= cal.get(Calendar.YEAR);
        System.out.println("year xml "+year);
        System.out.println("year sys "+yearSys);
//        if(year != yearSys){
//            rq.setSuccess(false);
//            rq.setMsg("ERROR Calendario No valido");
//            return rq;
//        }
        
         if(tipoFactura.equalsIgnoreCase("0")){
           Map orden = new HashMap();
                 String buscaOrden = "";
               orden.put("total", comp.getTotal());
               orden.put("calendario", year);
               orden.put("periodo", mes);
               orden.put("rfc", rfcE);
               orden.put("compania", compania);
               orden.put("orden", ordenCompra);
               
                   List listOrden = processDao.getMapResult("BuscaOrdenCompraId", orden);
                 if(listOrden.isEmpty()){
                     
                  
                     
                     rq.setSuccess(false);
                    rq.setMsg("La orden de compra es invalida");
                    return rq;
                     
                     
                 
                 }
                 
                 
                Map ordenImp = new HashMap();
               ordenImp.put("total", comp.getTotal());
               ordenImp.put("calendario", year);
               ordenImp.put("periodo", mes);
               ordenImp.put("rfc", rfcE);
               ordenImp.put("compania", compania);
               ordenImp.put("orden", ordenCompra);
               ordenImp.put("metPago", comp.getMetodoPago().value());
               
                   List listOrdenImp = processDao.getMapResult("BuscaOrdenImpPortal", ordenImp);
                 if(!listOrdenImp.isEmpty()){
                     
                  Map impOrden = (HashMap) listOrdenImp.get(0);
                         //   System.out.println("COMISION:" + factCompRel.get("COMISION"));
                            BigDecimal impToOrd = new BigDecimal(impOrden.get("IMPORTE_ORDEN").toString());
                           if(comp.getTotal().compareTo(impToOrd) != 0){
                               
                                rq.setSuccess(false);
                                rq.setMsg("El importe de la factura no coincide con el importe de la orden: "+impOrden.get("IMPORTE_ORDEN").toString());
                                return rq;
                     
                           
                           }
                           
                 
                 }
                 
                           
                                   
                     
                    
                     
                  List listOrdenImpMet = processDao.getMapResult("BuscaValMetPag", ordenImp);
                 if(!listOrdenImpMet.isEmpty()){
                     
                  Map impOrdenMet = (HashMap) listOrdenImpMet.get(0);
                  
                         // System.out.println("comp.getMetodoPago().value():"+comp.getMetodoPago().value());
                       //System.out.println("comp.getMetodoPago().name():"+comp.getMetodoPago().name());
                       //System.out.println("comp.getMetodoPago().toString():"+comp.getMetodoPago().toString());

                         // System.out.println("impOrdenMet.get(ID_MET_PAGO).toString():"+impOrdenMet.get("ID_MET_PAGO").toString());
               
                           if(!impOrdenMet.get("ID_MET_PAGO").toString().equalsIgnoreCase(comp.getMetodoPago().value())){
                               
                                rq.setSuccess(false);
                                rq.setMsg("Error, el metodo de pago es incorrecto, se espera: "+impOrdenMet.get("ID_MET_PAGO").toString());
                                return rq;
                     
                           
                           }
                 
                 
                 }
                         
         }
        //}
        
                     
                     
                 
                   
                      
            
        
            
            
        }
        
       if(!result2 && result4 && !result1){
                System.out.println("Soy version 4");

                    JAXBContext jaxbContext = JAXBContext.newInstance(Comprobante4.class);

		Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
		Comprobante4 comp = (Comprobante4) jaxbUnmarshaller.unmarshal(new FileInputStream(pathXML));
             
              if(comp==null){
            rq.setMsg("Error al validar el XML:");
            rq.setSuccess(false);
            return rq;
        }
       // System.out.println("usuario:"+usuario);
        
       // System.out.println("prov.getRfc():"+valida.getComprobante().getEmisor().getRfc());
        //Valida que el RFC emisor sea de la secion
        if(!rfcProv.equals(comp.getEmisor().getRfc())){
             rq.setSuccess(false);
            rq.setMsg("ERROR RFC del Emisor no es valido");
            return rq;
        }
        
        //Valida que el RFC receptor sea de SHW
        if(!rfc.equals(comp.getReceptor().getRfc())){
            rq.setSuccess(false);
            rq.setMsg("ERROR RFC Receptor No valido");
            return rq;
        }
        int year = comp.getFecha().getYear();
        int mes = comp.getFecha().getMonth();
        String rfcE = comp.getEmisor().getRfc();
       
        //Valida que este cargando sólo Facturas del año en curso
        Calendar cal= Calendar.getInstance();
        int yearSys= cal.get(Calendar.YEAR);
        System.out.println("year xml "+year);
        System.out.println("year sys "+yearSys);
//        if(year != yearSys){
//            rq.setSuccess(false);
//            rq.setMsg("ERROR Calendario No valido");
//            return rq;
//        }
        
        //if(tipoFactura.equalsIgnoreCase("0")){
         if(tipoFactura.equalsIgnoreCase("0")){
           Map orden = new HashMap();
                 String buscaOrden = "";
               orden.put("total", comp.getTotal());
               orden.put("calendario", year);
               orden.put("periodo", mes);
               orden.put("rfc", rfcE);
               orden.put("compania", compania);
               orden.put("orden", ordenCompra);
               
                   List listOrden = processDao.getMapResult("BuscaOrdenCompraId", orden);
                 if(listOrden.isEmpty()){
                     
                  
                     
                     rq.setSuccess(false);
                    rq.setMsg("La orden de compra es invalida");
                    return rq;
                     
                     
                 
                 }
                 
                 
                Map ordenImp = new HashMap();
               ordenImp.put("total", comp.getTotal());
               ordenImp.put("calendario", year);
               ordenImp.put("periodo", mes);
               ordenImp.put("rfc", rfcE);
               ordenImp.put("compania", compania);
               ordenImp.put("orden", ordenCompra);
               ordenImp.put("metPago", comp.getMetodoPago().value());
               
                   List listOrdenImp = processDao.getMapResult("BuscaOrdenImpPortal", ordenImp);
                 if(!listOrdenImp.isEmpty()){
                     
                  Map impOrden = (HashMap) listOrdenImp.get(0);
                         //   System.out.println("COMISION:" + factCompRel.get("COMISION"));
                            BigDecimal impToOrd = new BigDecimal(impOrden.get("IMPORTE_ORDEN").toString());
                           if(comp.getTotal().compareTo(impToOrd) != 0){
                               
                                rq.setSuccess(false);
                                rq.setMsg("El importe de la factura no coincide con el importe de la orden: "+impOrden.get("IMPORTE_ORDEN").toString());
                                return rq;
                     
                           
                           }
                           
                 
                 }
                 
                           
                                   
                     
                    
                     
                  List listOrdenImpMet = processDao.getMapResult("BuscaValMetPag", ordenImp);
                 if(!listOrdenImpMet.isEmpty()){
                     
                  Map impOrdenMet = (HashMap) listOrdenImpMet.get(0);
               
                           if(!impOrdenMet.get("ID_MET_PAGO").toString().equalsIgnoreCase(comp.getMetodoPago().value())){
                               
                                rq.setSuccess(false);
                                rq.setMsg("Error, el metodo de pago es incorrecto, se espera: "+impOrdenMet.get("ID_MET_PAGO").toString());
                                return rq;
                     
                           
                           }
                 
                 
                 }
                         
         }
        //}
        
                     
                     
                 
                   
                      
            
        
            
            
        }
        
        String uuid ="";
        
         if(!result2 && result4 && !result1){
            
            uuid =app.getUUID4(pathXML);
        
        }else{
            uuid =app.getUUID(pathXML);
        }
         
        
       
         
        //Valida en el SAT
        
        log.debug("Validando en el SAT....");
        if(!result2){
//        ValidaSAT vl = new ValidaSAT();
//        ResponseWSValida res = vl.validaCFDIWSSAT(valida.getComprobante().getEmisor().getRfc(), valida.getComprobante().getReceptor().getRfc(),""+ valida.getComprobante().getTotal(),uuid);
//        
//        
//        log.debug("res.getEstatus():"+res.getEstatus());
//               
//               
//        if (res.getEstatus().equals("Vigente")) {
//            System.out.println("CFDI se Valido Bien en el SAT");
//        } else {
//            rq.setSuccess(false);
//            rq.setMsg("ERROR Factuara no se encontro valida en el SAT.");
//            return rq;
//        }
        }
        app.setNombrePdf(fi2.getFile());
        app.setPathPdf(fi2.getPath());
        app.setUsuario(usuario);
        app.setTipoCarga("1");

         int data = 0;
          
            if(!result2 && !result4 && result1){
            
              data = app.cargaComprobante(pathXML, compania, concGasto,"PRTL");
              
            }
            
            if(result2 && !result4 && !result1){
        
                data = app.cargaComprobante33(pathXML, compania, concGasto,"","PRTL");

            
            }

            if(!result2 && result4 && !result1){

                 data = app.cargaComprobante4(pathXML, compania, concGasto,"","PRTL");

            }
        log.debug("Result data: "+data);
        Querys que = new Querys();
            String store = que.getSQL("CONTA_INSERTA_FECHAVENC");
            
           
             if(tipoFactura.equalsIgnoreCase("0")){
             
                    Map client = new HashMap();
                        String buscaidProv = "";
                      client.put("compania", compania);
                      client.put("rfc", rfcProv);

                          List listidProv = processDao.getMapResult("BuscaIdProv", client);


                                    Map resultProv = (HashMap) listidProv.get(0);

                         buscaidProv = resultProv.get("ID_CLIENTE").toString();

                    parametros.put("compania", compania);
                    parametros.put("numero", data);
                    parametros.put("idCliente", buscaidProv);
                    parametros.put("origen", "FE");

                     fi2 = generaArchivosOrden(uploadItem3.getFile2(), ordenCompra,dirCompania,data);
                       if (!fi2.isIsSave()) {
                           rq.setMsg("Error al guardar archivo PDF de orden:" + msgErr);
                           rq.setSuccess(false);
                           return rq;
                       }

             } 
        
        if (data >= 1) {

            processDao.execute(store, parametros);
            rq.setSuccess(true);
            rq.setMsg(Integer.toString(data));

            return rq;
        } else {
            rq.setSuccess(false);
            rq.setMsg("Error al cargar comprobante");

            return rq;
        }

    }
    
    
    
            @RequestMapping(value = "/savePortalAereo", method = RequestMethod.POST)
    public @ResponseBody
    ResponseQuery3 createPortalAereo(
            @RequestParam("COMPANIA") String compania1,
            @RequestParam("RFC") String rfc,
            @RequestParam("archCOMPANIA") String archCOMPANIA,
            @RequestParam("RFC_PROV") String rfcProv,
            @RequestParam("ORDEN_COMPRA") String ordenCompra,
            
            @ModelAttribute("proveedor")  ErpCClientes prov,
            FileUploadBean uploadItem,
            FileUploadBean2 uploadItem2,
            FileUploadBean2 uploadItem3,
            BindingResult result,
            WebRequest webRequest,
            Model model) throws IOException, JAXBException {
        log.debug("---------------------------------------------------------------------");
        log.debug("compania1:"+compania1);
        log.debug("rfc:"+rfc);
        log.debug("archCOMPANIA:"+archCOMPANIA);
        
        Map parametros = processDao.paramToMap(webRequest.getParameterMap());
        String concGasto = null;
        
        String hora;
        String rfcProveedor = "";

          hora = "" + System.currentTimeMillis();

        boolean isSave = true;
        SimpleDateFormat formatoDelTexto2 = new SimpleDateFormat("dd/MM/yyyy");

        ResponseQuery3 rq = new ResponseQuery3();
        
        if (prov == null) {
            rq.setMsg("Sesion no valida");
            rq.setSuccess(false);
            return rq;
        }

        String compania = model.asMap().get("compania").toString();
         String usuario = model.asMap().get("usuario").toString();
         
         log.debug("usuario:"+usuario);
         log.debug("compania session:"+compania);
         
         String dirCompania;
         
          dirCompania = compania;
        FileInfo fi;
        FileInfo fi2;
        
        ErpCxpOrdenCompraLog ordenLog = new ErpCxpOrdenCompraLog();
        ErpCxpOrdenCompraLogId ordenLogId = new ErpCxpOrdenCompraLogId();
        
         boolean result1 = validaVersionCFDI.verision32File(uploadItem.getFile());
         boolean result2 = validaVersionCFDI.version33File(uploadItem.getFile());
         
         
         System.out.println("result1: " +result1);
         System.out.println("result2: " +result2);
        
        String dirFecha = "";
        String nombreUuid = "";
        
        if(!result2){
        
               dirFecha = app.validaFechaComprobante(uploadItem.getFile(), dirOutFileDocument, compania);
        }else{
        
               dirFecha = app.validaFechaComprobante33(uploadItem.getFile(), dirOutFileDocument, compania);
        }
        if (dirFecha.equalsIgnoreCase("")){
            
                fi = generaArchivos(uploadItem.getFile(), 1,dirCompania, hora);

                if (!fi.isIsSave()) {
                    rq.setMsg("Error al guardar archivo XML:" + msgErr);
                    rq.setSuccess(false);
                    return rq;
                }
                fi2 = generaArchivos(uploadItem2.getFile2(), 2,dirCompania, hora);
                if (!fi2.isIsSave()) {
                    rq.setMsg("Error al guardar archivo PDF:" + msgErr);
                    rq.setSuccess(false);
                    return rq;
                }
        
        }else{
            
                 
            
            if(!result2){
            
              nombreUuid = app.validaNombreUUidComprobante(uploadItem.getFile(), dirOutFileDocument, compania);
              
            }else{
                
                nombreUuid = app.validaNombreUUidComprobante33(uploadItem.getFile(), dirOutFileDocument, compania);
                
                
                      
                }
            
            System.out.println("nombreUuid: "+nombreUuid);
            
               fi = generaArchivos2(uploadItem.getFile(), 1,dirFecha,nombreUuid);

                if (!fi.isIsSave()) {
                    rq.setMsg("Error al guardar archivo XML:" + msgErr);
                    rq.setSuccess(false);
                    return rq;
                }
                fi2 = generaArchivos2(uploadItem2.getFile2(), 2,dirFecha,nombreUuid);
        //        System.out.println("res2:" + res2);
                if (!fi2.isIsSave()) {
                    rq.setMsg("Error al guardar archivo PDF:" + msgErr);
                    rq.setSuccess(false);
                    return rq;
                }
        
        }
        
        

        if (result.hasErrors()) {
            for (ObjectError error : result.getAllErrors()) {
                System.err.println("Error: " + error.getCode() + " - " + error.getDefaultMessage());
            }
            rq.setSuccess(isSave);
            rq.setMsg("Error al guardar el archivo.");

            return rq;
        }
        String idErr = "" + System.currentTimeMillis();
        app.setId(idErr);
       log.debug("pathXML:"+pathXML);
       PolizasInfo valida;
         if(!result2){
            valida = app.validaComprobante(compania, pathXML, fi2.getFile(), fi2.getPath());
       }else{
            valida = app.validaComprobante33(compania, pathXML, fi2.getFile(), fi2.getPath());
       }
        log.debug("valida:"+valida.getComprobante());
        log.debug("vali:"+valida.getInfTipo());
        log.debug("msgErr:"+valida.getMsgErr());
        
        if (valida.getInfTipo() ==1 || valida.getInfTipo() ==3) {
            rq.setMsg("Error al validar el XML ya fue cargado al portal");
            rq.setSuccess(false);
            return rq;
        }
        
        
        
         if(!result2){
             System.out.println("Soy version 32");
        if(valida.getComprobante()==null){
            rq.setMsg("Error al validar el XML:");
            rq.setSuccess(false);
            return rq;
        }
       // System.out.println("usuario:"+usuario);
        
       // System.out.println("prov.getRfc():"+valida.getComprobante().getEmisor().getRfc());
        //Valida que el RFC emisor sea de la secion
        if(!rfcProv.equals(valida.getComprobante().getEmisor().getRfc())){
             rq.setSuccess(false);
            rq.setMsg("ERROR RFC del Emisor no es valido");
            return rq;
        }
        
        //Valida que el RFC receptor sea de SHW
        if(!rfc.equals(valida.getComprobante().getReceptor().getRfc())){
            rq.setSuccess(false);
            rq.setMsg("ERROR RFC Receptor No valido");
            return rq;
        }
            SimpleDateFormat format = new SimpleDateFormat("yyyy");
            String DateToStr = format.format(valida.getComprobante().getFecha());
            int year = Integer.parseInt(DateToStr);
            //int year = valida.getComprobante().getFecha().getYear();
            int mes = valida.getComprobante().getFecha().getMonth();
            String rfcE = valida.getComprobante().getEmisor().getRfc();
             //Valida que este cargando sólo Facturas del año en curso
        Calendar cal= Calendar.getInstance();
        int yearSys= cal.get(Calendar.YEAR);
             System.out.println("year xml "+year);
             System.out.println("year sys "+yearSys);
        if(year != yearSys){
            rq.setSuccess(false);
            rq.setMsg("ERROR Calendario No valido");
            return rq;
        }

           Map orden = new HashMap();
                 String buscaOrden = "";
               orden.put("total", valida.getComprobante().getTotal());
               orden.put("calendario", year);
               orden.put("periodo", mes);
               orden.put("rfc", rfcE);
               orden.put("compania", compania);
               orden.put("orden", ordenCompra);
               
                   List listOrden = processDao.getMapResult("BuscaOrdenCompraId", orden);
                 if(listOrden.isEmpty()){
                  
                     
                     rq.setSuccess(false);
                    rq.setMsg("La orden de compra es invalida");
                    return rq;
                     
                     
                 
                 }
                     
                     
        //Obtener el uuid
         }else{
                System.out.println("Soy version 33");
               JAXBContext jaxbContext = JAXBContext.newInstance(com.feraz.mx.sat.cfdi.Comprobante.class);

                    Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
                    com.feraz.mx.sat.cfdi.Comprobante comp = (com.feraz.mx.sat.cfdi.Comprobante) jaxbUnmarshaller.unmarshal(new FileInputStream(pathXML));

             
              if(comp==null){
            rq.setMsg("Error al validar el XML:");
            rq.setSuccess(false);
            return rq;
        }
       // System.out.println("usuario:"+usuario);
        
       // System.out.println("prov.getRfc():"+valida.getComprobante().getEmisor().getRfc());
        //Valida que el RFC emisor sea de la secion
        if(!rfcProv.equals(comp.getEmisor().getRfc())){
             rq.setSuccess(false);
            rq.setMsg("ERROR RFC del Emisor no es valido");
            return rq;
        }
        
        //Valida que el RFC receptor sea de SHW
        if(!rfc.equals(comp.getReceptor().getRfc())){
            rq.setSuccess(false);
            rq.setMsg("ERROR RFC Receptor No valido");
            return rq;
        }
        int year = comp.getFecha().getYear();
        int mes = comp.getFecha().getMonth();
        String rfcE = comp.getEmisor().getRfc();
       
        //Valida que este cargando sólo Facturas del año en curso
        Calendar cal= Calendar.getInstance();
        int yearSys= cal.get(Calendar.YEAR);
        System.out.println("year xml "+year);
        System.out.println("year sys "+yearSys);
        if(year != yearSys){
            rq.setSuccess(false);
            rq.setMsg("ERROR Calendario No valido");
            return rq;
        }
        
           Map orden = new HashMap();
                 String buscaOrden = "";
               orden.put("total", comp.getTotal());
               orden.put("calendario", year);
               orden.put("periodo", mes);
               orden.put("rfc", rfcE);
               orden.put("compania", compania);
               orden.put("orden", ordenCompra);
               
                   List listOrden = processDao.getMapResult("BuscaOrdenCompraId", orden);
                 if(listOrden.isEmpty()){
                     
                  
                     
                     rq.setSuccess(false);
                    rq.setMsg("La orden de compra es invalida");
                    return rq;
                     
                     
                 
                 }
                     
                     
                 
                   
                      
            
        
            
            
        }
        String uuid =app.getUUID(pathXML);
        
        //Valida en el SAT
        
        log.debug("Validando en el SAT....");
        if(!result2){
        ValidaSAT vl = new ValidaSAT();
        ResponseWSValida res = vl.validaCFDIWSSAT(valida.getComprobante().getEmisor().getRfc(), valida.getComprobante().getReceptor().getRfc(),""+ valida.getComprobante().getTotal(),uuid);
        
        
        log.debug("res.getEstatus():"+res.getEstatus());
               
               
        if (res.getEstatus().equals("Vigente")) {
            System.out.println("CFDI se Valido Bien en el SAT");
        } else {
            rq.setSuccess(false);
            rq.setMsg("ERROR Factuara no se encontro valida en el SAT.");
            return rq;
        }
        }
        app.setNombrePdf(fi2.getFile());
        app.setPathPdf(fi2.getPath());
        app.setUsuario(usuario);
        app.setTipoCarga("1");

          int data;
        if(!result2){
           data = app.cargaComprobante(pathXML, compania, concGasto,"PRTL");
        }else{
           data = app.cargaComprobante33(pathXML, compania, concGasto,"","PRTL");
        }
        log.debug("Result data: "+data);
        Querys que = new Querys();
            String store = que.getSQL("CONTA_INSERTA_FECHAVENC");
            
           
             
             Map client = new HashMap();
                 String buscaidProv = "";
               client.put("compania", compania);
               client.put("rfc", rfcProv);

                   List listidProv = processDao.getMapResult("BuscaIdProv", client);
                   
                
                             Map resultProv = (HashMap) listidProv.get(0);
                  
                  buscaidProv = resultProv.get("ID_CLIENTE").toString();
                  
             parametros.put("compania", compania);
             parametros.put("numero", data);
             parametros.put("idCliente", buscaidProv);
             parametros.put("origen", "FE");
             
              fi2 = generaArchivosOrden(uploadItem3.getFile2(), ordenCompra,dirCompania,data);
                if (!fi2.isIsSave()) {
                    rq.setMsg("Error al guardar archivo PDF de orden:" + msgErr);
                    rq.setSuccess(false);
                    return rq;
                }

                
        
        if (data >= 1) {

            processDao.execute(store, parametros);
            rq.setSuccess(true);
            rq.setMsg(Integer.toString(data));

            return rq;
        } else {
            rq.setSuccess(false);
            rq.setMsg("Error al cargar comprobante");

            return rq;
        }

    }
    
                @RequestMapping(value = "/savePortalAereoOrden", method = RequestMethod.POST)
    public @ResponseBody
    ResponseQuery3 createPortalAereoOrden(
            @RequestParam("COMPANIA") String compania1,
            @RequestParam("RFC") String rfc,
            @RequestParam("archCOMPANIA") String archCOMPANIA,
            @RequestParam("RFC_PROV") String rfcProv,
            @RequestParam("ORDEN_COMPRA") String ordenCompra,
            @RequestParam("LINEA") String linea,
            @ModelAttribute("proveedor")  ErpCClientes prov,
            FileUploadBean uploadItem,
            FileUploadBean2 uploadItem2,
            FileUploadBean2 uploadItem3,
            BindingResult result,
            WebRequest webRequest,
            Model model) throws IOException, JAXBException {
        log.debug("---------------------------------------------------------------------");
        log.debug("compania1:"+compania1);
        log.debug("rfc:"+rfc);
        log.debug("archCOMPANIA:"+archCOMPANIA);
        
        Map parametros = processDao.paramToMap(webRequest.getParameterMap());
        String concGasto = null;
        
        String hora;
        String rfcProveedor = "";

          hora = "" + System.currentTimeMillis();

        boolean isSave = true;
        SimpleDateFormat formatoDelTexto2 = new SimpleDateFormat("dd/MM/yyyy");

        ResponseQuery3 rq = new ResponseQuery3();
        
        if (prov == null) {
            rq.setMsg("Sesion no valida");
            rq.setSuccess(false);
            return rq;
        }

        String compania = model.asMap().get("compania").toString();
         String usuario = model.asMap().get("usuario").toString();
         
         log.debug("usuario:"+usuario);
         log.debug("compania session:"+compania);
         
         String dirCompania;
         
          dirCompania = compania;
        FileInfo fi;
        FileInfo fi2;
        
        ErpCxpOrdenCompraLog ordenLog = new ErpCxpOrdenCompraLog();
        ErpCxpOrdenCompraLogId ordenLogId = new ErpCxpOrdenCompraLogId();
        
         boolean result1 = validaVersionCFDI.verision32File(uploadItem.getFile());
         boolean result2 = validaVersionCFDI.version33File(uploadItem.getFile());
          boolean result4 = validaVersionCFDI.version4File(uploadItem.getFile());
          
         System.out.println("result1: " +result1);
         System.out.println("result2: " +result2);
         System.out.println("result4: " +result4);
        
        String dirFecha = "";
        String nombreUuid = "";
        
        if(!result2 && !result4 && result1){
        
               dirFecha = app.validaFechaComprobante(uploadItem.getFile(), dirOutFileDocument, compania);
        }
            
        if(result2 && !result4 && !result1){
        
               dirFecha = app.validaFechaComprobante33(uploadItem.getFile(), dirOutFileDocument, compania);
            
        }
        
        if(!result2 && result4 && !result1){
            
            dirFecha = app.validaFechaComprobante4(uploadItem.getFile(), dirOutFileDocument, compania);

            
        }
            
            
        
        if (dirFecha.equalsIgnoreCase("")){
            
                fi = generaArchivos(uploadItem.getFile(), 1,dirCompania, hora);

                if (!fi.isIsSave()) {
                    rq.setMsg("Error al guardar archivo XML:" + msgErr);
                    rq.setSuccess(false);
                    return rq;
                }
                fi2 = generaArchivos(uploadItem2.getFile2(), 2,dirCompania, hora);
                if (!fi2.isIsSave()) {
                    rq.setMsg("Error al guardar archivo PDF:" + msgErr);
                    rq.setSuccess(false);
                    return rq;
                }
        
        }else{
            
                 
            
            if(!result2 && !result4 && result1){
            
              nombreUuid = app.validaNombreUUidComprobante(uploadItem.getFile(), dirOutFileDocument, compania);
              
            }
            
            if(result2 && !result4 && !result1){
        
                nombreUuid = app.validaNombreUUidComprobante33(uploadItem.getFile(), dirOutFileDocument, compania);

            
            }

            if(!result2 && result4 && !result1){

                 nombreUuid = app.validaNombreUUidComprobante4(uploadItem.getFile(), dirOutFileDocument, compania);

            }
                
           
            
            System.out.println("nombreUuid: "+nombreUuid);
            
               fi = generaArchivos2(uploadItem.getFile(), 1,dirFecha,nombreUuid);

                if (!fi.isIsSave()) {
                    rq.setMsg("Error al guardar archivo XML:" + msgErr);
                    rq.setSuccess(false);
                    return rq;
                }
                fi2 = generaArchivos2(uploadItem2.getFile2(), 2,dirFecha,nombreUuid);
        //        System.out.println("res2:" + res2);
                if (!fi2.isIsSave()) {
                    rq.setMsg("Error al guardar archivo PDF:" + msgErr);
                    rq.setSuccess(false);
                    return rq;
                }
        
        }
        
        

        if (result.hasErrors()) {
            for (ObjectError error : result.getAllErrors()) {
                System.err.println("Error: " + error.getCode() + " - " + error.getDefaultMessage());
            }
            rq.setSuccess(isSave);
            rq.setMsg("Error al guardar el archivo.");

            return rq;
        }
        String idErr = "" + System.currentTimeMillis();
        app.setId(idErr);
       log.debug("pathXML:"+pathXML);
       PolizasInfo valida;
       
            if(!result2 && !result4 && result1){
            
                valida = app.validaComprobante(compania, pathXML, fi2.getFile(), fi2.getPath());
              
            }else{
            
               if(result2 && !result4 && !result1){
        
                valida = app.validaComprobante33(compania, pathXML, fi2.getFile(), fi2.getPath());

            
                }else{
                   
                   if(!result2 && result4 && !result1){

                        valida = app.validaComprobante4(compania, pathXML, fi2.getFile(), fi2.getPath());

                   }else{
                   
                       valida = app.validaComprobante33(compania, pathXML, fi2.getFile(), fi2.getPath());
                   
                   }
               
               
                }
            
            }
            
            

            
       
       
        log.debug("valida:"+valida.getComprobante());
        log.debug("vali:"+valida.getInfTipo());
        log.debug("msgErr:"+valida.getMsgErr());
        
        if (valida.getInfTipo() ==1 || valida.getInfTipo() ==3) {
            rq.setMsg("Error al validar el XML ya fue cargado al portal");
            rq.setSuccess(false);
            return rq;
        }
        
        
        
         if(!result2 && !result4 && result1){
             System.out.println("Soy version 32");
        if(valida.getComprobante()==null){
            rq.setMsg("Error al validar el XML:");
            rq.setSuccess(false);
            return rq;
        }
       // System.out.println("usuario:"+usuario);
        
       // System.out.println("prov.getRfc():"+valida.getComprobante().getEmisor().getRfc());
        //Valida que el RFC emisor sea de la secion
        if(!rfcProv.equals(valida.getComprobante().getEmisor().getRfc())){
             rq.setSuccess(false);
            rq.setMsg("ERROR RFC del Emisor no es valido");
            return rq;
        }
        
        //Valida que el RFC receptor sea de SHW
        if(!rfc.equals(valida.getComprobante().getReceptor().getRfc())){
            rq.setSuccess(false);
            rq.setMsg("ERROR RFC Receptor No valido");
            return rq;
        }
            SimpleDateFormat format = new SimpleDateFormat("yyyy");
            String DateToStr = format.format(valida.getComprobante().getFecha());
            int year = Integer.parseInt(DateToStr);
            //int year = valida.getComprobante().getFecha().getYear();
            int mes = valida.getComprobante().getFecha().getMonth();
            String rfcE = valida.getComprobante().getEmisor().getRfc();
             //Valida que este cargando sólo Facturas del año en curso
        Calendar cal= Calendar.getInstance();
        int yearSys= cal.get(Calendar.YEAR);
             System.out.println("year xml "+year);
             System.out.println("year sys "+yearSys);
        if(year != yearSys){
            rq.setSuccess(false);
            rq.setMsg("ERROR Calendario No valido");
            return rq;
        }

           Map orden = new HashMap();
                 String buscaOrden = "";
               orden.put("total", valida.getComprobante().getTotal());
               orden.put("calendario", year);
               orden.put("periodo", mes);
               orden.put("rfc", rfcE);
               orden.put("compania", compania);
               orden.put("orden", ordenCompra);
               
                   List listOrden = processDao.getMapResult("BuscaOrdenCompraId", orden);
                 if(listOrden.isEmpty()){
                  
                     
                     rq.setSuccess(false);
                    rq.setMsg("La orden de compra es invalida");
                    return rq;
                     
                     
                 
                 }
                     
                     
        //Obtener el uuid
         }
         
         
         
        if(result2 && !result4 && !result1){
                System.out.println("Soy version 33");
               JAXBContext jaxbContext = JAXBContext.newInstance(com.feraz.mx.sat.cfdi.Comprobante.class);

                    Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
                    com.feraz.mx.sat.cfdi.Comprobante comp = (com.feraz.mx.sat.cfdi.Comprobante) jaxbUnmarshaller.unmarshal(new FileInputStream(pathXML));

             
              if(comp==null){
            rq.setMsg("Error al validar el XML:");
            rq.setSuccess(false);
            return rq;
        }
       // System.out.println("usuario:"+usuario);
        
       // System.out.println("prov.getRfc():"+valida.getComprobante().getEmisor().getRfc());
        //Valida que el RFC emisor sea de la secion
        if(!rfcProv.equals(comp.getEmisor().getRfc())){
             rq.setSuccess(false);
            rq.setMsg("ERROR RFC del Emisor no es valido");
            return rq;
        }
        
        //Valida que el RFC receptor sea de SHW
        if(!rfc.equals(comp.getReceptor().getRfc())){
            rq.setSuccess(false);
            rq.setMsg("ERROR RFC Receptor No valido");
            return rq;
        }
        int year = comp.getFecha().getYear();
        int mes = comp.getFecha().getMonth();
        String rfcE = comp.getEmisor().getRfc();
       
        //Valida que este cargando sólo Facturas del año en curso
        Calendar cal= Calendar.getInstance();
        int yearSys= cal.get(Calendar.YEAR);
        System.out.println("year xml "+year);
        System.out.println("year sys "+yearSys);
        if(year != yearSys){
            rq.setSuccess(false);
            rq.setMsg("ERROR Calendario No valido");
            return rq;
        }
        
           Map orden = new HashMap();
                 String buscaOrden = "";
               orden.put("total", comp.getTotal());
               orden.put("calendario", year);
               orden.put("periodo", mes);
               orden.put("rfc", rfcE);
               orden.put("compania", compania);
               orden.put("orden", ordenCompra);
               
                   List listOrden = processDao.getMapResult("BuscaOrdenCompraId", orden);
                 if(listOrden.isEmpty()){
                     
                  
                     
                     rq.setSuccess(false);
                    rq.setMsg("La orden de compra es invalida");
                    return rq;
                     
                     
                 
                 }
                     
                     
                 
                   
                      
            
        
            
            
        }
        
        
        if(!result2 && result4 && !result1){
                System.out.println("Soy version 4");

                    JAXBContext jaxbContext = JAXBContext.newInstance(Comprobante4.class);

		Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
		Comprobante4 comp = (Comprobante4) jaxbUnmarshaller.unmarshal(new FileInputStream(pathXML));
             
              if(comp==null){
            rq.setMsg("Error al validar el XML:");
            rq.setSuccess(false);
            return rq;
        }
       // System.out.println("usuario:"+usuario);
        
       // System.out.println("prov.getRfc():"+valida.getComprobante().getEmisor().getRfc());
        //Valida que el RFC emisor sea de la secion
        if(!rfcProv.equals(comp.getEmisor().getRfc())){
             rq.setSuccess(false);
            rq.setMsg("ERROR RFC del Emisor no es valido");
            return rq;
        }
        
        //Valida que el RFC receptor sea de SHW
        if(!rfc.equals(comp.getReceptor().getRfc())){
            rq.setSuccess(false);
            rq.setMsg("ERROR RFC Receptor No valido");
            return rq;
        }
        int year = comp.getFecha().getYear();
        int mes = comp.getFecha().getMonth();
        String rfcE = comp.getEmisor().getRfc();
       
        //Valida que este cargando sólo Facturas del año en curso
        Calendar cal= Calendar.getInstance();
        int yearSys= cal.get(Calendar.YEAR);
        System.out.println("year xml "+year);
        System.out.println("year sys "+yearSys);
        if(year != yearSys){
            rq.setSuccess(false);
            rq.setMsg("ERROR Calendario No valido");
            return rq;
        }
        
           Map orden = new HashMap();
                 String buscaOrden = "";
               orden.put("total", comp.getTotal());
               orden.put("calendario", year);
               orden.put("periodo", mes);
               orden.put("rfc", rfcE);
               orden.put("compania", compania);
               orden.put("orden", ordenCompra);
               
                   List listOrden = processDao.getMapResult("BuscaOrdenCompraId", orden);
                 if(listOrden.isEmpty()){
                     
                  
                     
                     rq.setSuccess(false);
                    rq.setMsg("La orden de compra es invalida");
                    return rq;
                     
                     
                 
                 }
                     
                     
                 
                   
                      
            
        
            
            
        }
        
        String uuid = "";
        
        if(!result2 && result4 && !result1){
            
            uuid =app.getUUID4(pathXML);
        
        }else{
            uuid =app.getUUID(pathXML);
        }
        
        
        
        //Valida en el SAT
        
        log.debug("Validando en el SAT....");
        if(!result2 && !result4){
        ValidaSAT vl = new ValidaSAT();
        ResponseWSValida res = vl.validaCFDIWSSAT(valida.getComprobante().getEmisor().getRfc(), valida.getComprobante().getReceptor().getRfc(),""+ valida.getComprobante().getTotal(),uuid);
        
        
        log.debug("res.getEstatus():"+res.getEstatus());
               
               
        if (res.getEstatus().equals("Vigente")) {
            System.out.println("CFDI se Valido Bien en el SAT");
        } else {
            rq.setSuccess(false);
            rq.setMsg("ERROR Factuara no se encontro valida en el SAT.");
            return rq;
        }
        }
        app.setNombrePdf(fi2.getFile());
        app.setPathPdf(fi2.getPath());
        app.setUsuario(usuario);
        app.setTipoCarga("1");

          int data = 0;
          
            if(!result2 && !result4 && result1){
            
              data = app.cargaComprobante(pathXML, compania, concGasto,"PRTL");
              
            }
            
            if(result2 && !result4 && !result1){
        
                data = app.cargaComprobante33(pathXML, compania, concGasto,"","PRTL");

            
            }

            if(!result2 && result4 && !result1){

                 data = app.cargaComprobante4(pathXML, compania, concGasto,"","PRTL");

            }
                  
      
        log.debug("Result data: "+data);
        Querys que = new Querys();
            String store = que.getSQL("CONTA_INSERTA_FECHAVENC");
            
           
             
             Map client = new HashMap();
                 String buscaidProv = "";
               client.put("compania", compania);
               client.put("rfc", rfcProv);

                   List listidProv = processDao.getMapResult("BuscaIdProv", client);
                   
                
                             Map resultProv = (HashMap) listidProv.get(0);
                  
                  buscaidProv = resultProv.get("ID_CLIENTE").toString();
                  
             parametros.put("compania", compania);
             parametros.put("numero", data);
             parametros.put("idCliente", buscaidProv);
             parametros.put("origen", "FE");
             
              if (uploadItem3 != null)
                {
                  fi2 = generaArchivosOrden(uploadItem3.getFile2(), ordenCompra, dirCompania, data);
                  if (!fi2.isIsSave())
                  {
                    rq.setMsg("Error al guardar archivo PDF de orden:" + this.msgErr);
                    rq.setSuccess(false);
                    return rq;
                  }
                }else{
              
                  generaArchivosOrdenRel(ordenCompra, dirCompania, data);
                  
                  
              
              
              }

                
        
        if (data >= 1) {
            
            generaRelacionOrden(compania,data,ordenCompra,linea);

            processDao.execute(store, parametros);
            rq.setSuccess(true);
            rq.setMsg(Integer.toString(data));

            return rq;
        } else {
            rq.setSuccess(false);
            rq.setMsg("Error al cargar comprobante");

            return rq;
        }

    }
    
    public boolean generaRelacionOrden(String compania,int data,String ordenCompra,String linea){
        
            ErpOrdenXFactura ordeFact = new ErpOrdenXFactura();
            ErpOrdenXFacturaId ordenFactId = new ErpOrdenXFacturaId();
            ErpOrdenCompraDet ordenDet = new ErpOrdenCompraDet();
            ErpOrdenCompraDetId ordenDetId = new ErpOrdenCompraDetId();
                  
            ErpOrdenCompra orden = new ErpOrdenCompra();
            ErpOrdenCompraId ordenId = new ErpOrdenCompraId();
            
             Map impoFact = new HashMap();
                   impoFact.put("compania", compania);
                   impoFact.put("numero", data);
               
                  List listImpoFact = processDao.getMapResult("BuscaImportesFactura", impoFact);
                  Map mapImpoFact = (HashMap) listImpoFact.get(0);
                  
        
            ordenFactId.setCompania(compania);
                  ordenFactId.setIdOrden(Integer.parseInt(ordenCompra));
                  ordenFactId.setIdOrdenDet(Integer.parseInt(linea));
                  ordenFactId.setNumeroFe(data);
                  ordeFact.setId(ordenFactId);
                  ordeFact.setOrigen("FE");
                  ordeFact.setSubtotal(new BigDecimal(mapImpoFact.get("SUBTOTAL").toString()));
                  ordeFact.setIva(new BigDecimal(mapImpoFact.get("IVA").toString()));
                  ordeFact.setTotal(new BigDecimal(mapImpoFact.get("TOTAL").toString()));
                 
                  erpOrdenXFacturaDao.save(ordeFact);
                  
                      Map ordenFact = new HashMap();
                   ordenFact.put("compania", compania);
                   ordenFact.put("idOrden", ordenCompra);
                   ordenFact.put("idOrdenDet", linea);
                   ordenFact.put("origen", "FE");
                   
               
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
                  
                   Querys queConc = new Querys();
                    String storeConc = queConc.getSQL("RelacionaConceptoxLinea");


                          
                    Map concLinea = new HashMap();

                    concLinea.put("compania", compania);
                    concLinea.put("orden", ordenCompra);
                    concLinea.put("linea", linea);
                    concLinea.put("origen", "FE");
                    concLinea.put("numeroFe", data);

                    processDao.execute(storeConc, concLinea);     


                  
                  
        return true;
    
    }
            
    public FileInfo generaArchivos(MultipartFile uploadItem, int tipo, String dirCompania, String hora) {
        String fileName = null;
        String nombreArchivo = "";
        
        String nombreArc = "";

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
                fi.setFile(nombreArc + hora + "." + extension);
//                System.out.println("i:" + i);
//                System.out.println("exte:" + extension);
//                System.out.println("nombreArc:" + nombreArc);
//                System.out.println("exte:" + extension);
                if (tipo == 1 && !extension.toLowerCase().trim().equals("xml")) {

                    msgErr = "Archivo xml no valido";

                    fi.setIsSave(false);
                    return fi;
                }
                if (tipo == 2 && !extension.toLowerCase().trim().equals("pdf")) {

                    msgErr = "Archivo pdf no valido";

                    fi.setIsSave(false);
                    return fi;
                }

                path = dirOutFileDocument + dirCompania + "/" + nombreArc + hora + "." + extension;
                fi.setPath(path);
                if (tipo == 1) {
                    pathXML = path;
                }

                url = dirUrlOutDocument + nombreArc + hora + "." + extension;
                fi.setUrl(url);

                revisarDirectorio(dirCompania);
                outputStream = new FileOutputStream(path);

                int readBytes = 0;
                byte[] buffer = new byte[10000];
                while ((readBytes = inputStream.read(buffer, 0, 10000)) != -1) {
                    outputStream.write(buffer, 0, readBytes);
                }
                outputStream.close();
                inputStream.close();
            }

            fi.setIsSave(true);
            return fi;

        } catch (Exception e) {
            e.printStackTrace();
            FileInfo fi = new FileInfo();
            fi.setIsSave(false);
            return fi;
        }
    }
    
            public FileInfo generaArchivosOrdenRel( String orden, String dirCompania,int numero) {
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
            archivoOrden.put("ORIGEN", "FE");
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
            archivoOrden.put("ORIGEN", "FE");
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
    
    public void revisarDirectorio(String dirCompania) {

        File file = new File(dirOutFileDocument + dirCompania + "/");
//        System.out.println("Directory" + dirOutFileDocument + dirCompania + "/");
        if (!file.exists()) {
            if (file.mkdirs()) {
//                System.out.println("Directory is created!");
            } else {
//                System.out.println("Failed to create directory!");
            }
        }

    }
    
        public FileInfo generaArchivos2(MultipartFile uploadItem, int tipo,String pathArch, String nombreUuid) {
        String fileName = null;
        String nombreArchivo = "";
            System.out.println("pathArch:"+pathArch);
        //System.out.println("Guardando en genera 2");
        
        //  ExtJSFormResult rq = new ExtJSFormResult();
        
       String nombreArc = "";     
            
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
                fi.setFile(nombreUuid + "." + extension);
//                System.out.println("i:" + i);
//                System.out.println("exte:" + extension);
//                System.out.println("nombreArc:" + nombreArc);
//                System.out.println("exte:" + extension);
                if (tipo == 1 && !extension.toLowerCase().trim().equals("xml")) {

                    msgErr = "Archivo xml no valido";

                    fi.setIsSave(false);
                    return fi;
                }
                if (tipo == 2 && !extension.toLowerCase().trim().equals("pdf")) {

                    msgErr = "Archivo pdf no valido";

                    fi.setIsSave(false);
                    return fi;
                }

                path = pathArch + "/"+nombreUuid+  "." + extension;
                fi.setPath(path);
                if (tipo == 1) {
                    pathXML = path;
                }

                url =  pathArch + "/"+nombreUuid+  "." + extension;
                fi.setUrl(url);

                //revisarDirectorio();
                outputStream = new FileOutputStream(path);

                int readBytes = 0;
                byte[] buffer = new byte[10000];
                while ((readBytes = inputStream.read(buffer, 0, 10000)) != -1) {
                    outputStream.write(buffer, 0, readBytes);
                }
                outputStream.close();
                inputStream.close();
            }

            fi.setIsSave(true);
            return fi;

        } catch (Exception e) {
            e.printStackTrace();
            FileInfo fi = new FileInfo();
            fi.setIsSave(false);
            return fi;
        }
    }
        
        
       @RequestMapping(value = "/cargaComplemento", method = RequestMethod.GET)
    public @ResponseBody
    ResponseQuery3 createComplemento(
          
            WebRequest webRequest,
            Model model) throws IOException, JAXBException, URISyntaxException {
        log.debug("---------------------------------------------------------------------");
        log.debug("----------------------Cargando complemento---------------------------");
        
        
        ReadXMLDTO dto = new ReadXMLDTO();
        
        RestTemplate restTemplate = new RestTemplate();

            final String baseUrl = "http://www.appferaz1.com/EmailRead/xmlinfoComplementos/";
            URI uri = new URI(baseUrl);
            
            
            dto.setDirXML("D:\\carga\\xml\\complementos\\");
            dto.setDirPDF("");
            dto.setEmail("");
            dto.setIdProveedor("00074");
            dto.setOrden("");
            dto.setNameXML("2F84214B-7A14-4EDF-B965-D757B162D426.xml");
            dto.setNamePDF("");
            dto.setRfc("AGU980601H75");

        //    Employee employee = new Employee(null, "Adam", "Gilly", "test@email.com");

            ResponseEntity<ValidadorGral> result = restTemplate.postForEntity(uri, dto, ValidadorGral.class);
            
            System.out.println("Estatus:");
            
            System.out.println(result.toString());
  
       // Map parametros = processDao.paramToMap(webRequest.getParameterMap());
       
        ResponseQuery3 rq = new ResponseQuery3();
        
   
        //String compania = model.asMap().get("compania").toString();
        // String usuario = model.asMap().get("usuario").toString();
         
        // log.debug("usuario:"+usuario);
        // log.debug("compania session:"+compania);
        
         
            //Verify request succeed
          //  Assert.assertEquals(201, result.getStatusCode());

        
            rq.setSuccess(true);
            rq.setMsg("");

            return rq;
   

    }     
        
    
}
