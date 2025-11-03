/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.feraz.facturas.webcontrolfe.controller;

/**
 *
 * @author Feraz3
 */
import com.fasterxml.jackson.databind.ObjectMapper;
import com.feraz.cxp.dao.ErpCpOtrasDao;
import com.feraz.facturas.webcontrolfe.dao1.ErpFeComprobantesDao;
import com.feraz.facturas.webcontrolfe.dao1.ErpFeConceptosDao;
import com.feraz.facturas.webcontrolfe.dao1.ErpFeEmisorDao;
import com.feraz.facturas.webcontrolfe.dao1.ErpFeErrDao;
import com.feraz.facturas.webcontrolfe.dao1.ErpFeImpRetencionesDao;
import com.feraz.facturas.webcontrolfe.dao1.ErpFeImpTrasladosDao;
import com.feraz.facturas.webcontrolfe.dao1.ErpFeReceptorDao;
import com.feraz.facturas.webcontrolfe.validation.ValidationFE;
import com.feraz.polizas3.util.ResponseQuery2;
import com.feraz.cxp.model.ErpCpOtras;
import com.feraz.cxp.model.ErpCpOtrasId;
import com.feraz.polizas3.validation.DetalleValidation;
import com.feraz.facturas.webcontrolfe.dto.ComprobanteDto;
import com.feraz.facturas.webcontrolfe.dto.PortalDTO;
import com.feraz.facturas.webcontrolfe.model.ErpFeComprobantes;
import com.feraz.facturas.webcontrolfe.model.ErpFeComprobantesId;
import com.feraz.facturas.webcontrolfe.model.ErpFeConceptos;
import com.feraz.facturas.webcontrolfe.model.ErpFeConceptosId;
import com.feraz.facturas.webcontrolfe.model.ErpFeEmisor;
import com.feraz.facturas.webcontrolfe.model.ErpFeEmisorId;
import com.feraz.facturas.webcontrolfe.model.ErpFeImpRetenciones;
import com.feraz.facturas.webcontrolfe.model.ErpFeImpRetencionesId;
import com.feraz.facturas.webcontrolfe.model.ErpFeImpTraslados;
import com.feraz.facturas.webcontrolfe.model.ErpFeImpTrasladosId;
import com.feraz.facturas.webcontrolfe.model.ErpFeReceptor;
import com.feraz.facturas.webcontrolfe.model.ErpFeReceptorId;
import com.feraz.facturas.webcontrolfe.process.DeleteArchivo;
import java.io.File;
import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import org.jamh.data.process.ProcessDao;
import org.jamh.wf.json.model.ResponseQuery;
import org.jamh.wf.process.Querys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.context.request.WebRequest;

@Controller
@RequestMapping("/processFactura")
@SessionAttributes({"compania", "usuario"})
public class DeleteFE {

    @Value("${document.file.dirOutDocumentFE}")
    private String dirOutFileDocument;
    @Value("${document.file.dirOutDocumentCXP}")
    private String dirOutFileDocumentCXP;
    @Value("${document.file.dirURLOutDocumentFE}")
    private String dirUrlOutDocument;
    @Value("${document.file.maxZiseFE}")
    private String maxSizeDocument;

    private ProcessDao processDao;

    private ValidationFE validationFE;
    private ErpCpOtrasDao ErpCpOtrasDao;

    private ErpFeComprobantesDao erpFeComprobantesDao;
    private ErpFeEmisorDao erpFeEmisorDao;
    private ErpFeConceptosDao erpFeConceptosDao;
    private ErpFeImpTrasladosDao erpFeImpTrasladosDao;
    private ErpFeImpRetencionesDao erpFeImpRetencionesDao;
    private ErpFeReceptorDao erpFeReceptorDao;
    private ErpFeErrDao erpFeErrDao;

    @RequestMapping(value = "/deleteFact", method = RequestMethod.POST)
    public @ResponseBody
    ResponseQuery2 deleteAction(String data, WebRequest webRequest, Model model) {

        ResponseQuery2 rq = new ResponseQuery2();
        SimpleDateFormat formatoDelTexto = new SimpleDateFormat("dd/MM/yyyy");

        //System.out.println("data"+data);
        int index = data.indexOf("[");
        if (index == -1) {
            data = "[" + data + "]";
        }

        System.out.println("data:" + data);
//
        if (model.asMap().get("compania") == null) {
            rq.setSuccess(false);
            rq.setData(null);
            rq.setMsg("La sesion no es valida.");
            return rq;
        }
        String compania = model.asMap().get("compania").toString();
        String usuario = model.asMap().get("usuario").toString();

        ErpFeComprobantes erpFeComprobantes = new ErpFeComprobantes();
        ErpFeComprobantesId erpFeComprobantesId = new ErpFeComprobantesId();
        ErpFeEmisor erpFeEmisor = new ErpFeEmisor();
        ErpFeEmisorId erpFeEmisorId = new ErpFeEmisorId();
//        ErpFeConceptos  erpFeConceptos = new ErpFeConceptos();
//        ErpFeConceptosId  erpFeConceptosId = new ErpFeConceptosId();
//        ErpFeImpRetenciones  erpFeImpRetenciones = new ErpFeImpRetenciones();
//        ErpFeImpRetencionesId  erpFeImpRetencionesId = new ErpFeImpRetencionesId();
//        ErpFeImpTraslados  erpFeImpTraslados = new ErpFeImpTraslados();
//        ErpFeImpTrasladosId  erpFeImpTrasladosId = new ErpFeImpTrasladosId();
        ErpFeReceptor erpFeReceptor = new ErpFeReceptor();
        ErpFeReceptorId erpFeReceptorId = new ErpFeReceptorId();

        ErpCpOtras erpCpOtras = new ErpCpOtras();
        ErpCpOtrasId erpCpOtrasId = new ErpCpOtrasId();

        try {
            int guardado = 0;
            String fecha = null;
            ObjectMapper mapper = new ObjectMapper();
            DateFormat df = new SimpleDateFormat("dd/MM/yyyy");

            mapper.setDateFormat(df);
            List<ComprobanteDto> lista = mapper.readValue(data,
                    mapper.getTypeFactory().constructCollectionType(List.class,
                            ComprobanteDto.class));

            if (lista.isEmpty()) {
                rq.setSuccess(false);
                rq.setData(null);
                rq.setMsg("Error no existen datos que guardar");
                return rq;
            }

            List<String> errores = new ArrayList<String>();

            int val = 0;
            Iterator<ComprobanteDto> it = lista.iterator();

            while (it.hasNext()) {
//                 System.out.println("-------------------------------------------------------------------");
                ComprobanteDto ss = it.next();

                @SuppressWarnings("unchecked")
                Map<String, Object> parametros = mapper.convertValue(ss, Map.class);

                System.out.println("tipoCxp" + ss.tipoCxp);
                if (ss.tipoCxp.equalsIgnoreCase("O")) {

                    erpCpOtrasId.setId(Integer.parseInt(ss.NUMERO));
                    erpCpOtrasId.setCompania(compania);
                    erpCpOtras.setId(erpCpOtrasId);

                    Boolean result = ErpCpOtrasDao.delete(erpCpOtras);

                    if (result == false) {

                        rq.setSuccess(false);
                        rq.setData(null);

                        rq.setMsg("No se pudo borrar la informacion");

                        return rq;

                    }

                } else {
                    erpFeComprobantesId.setCompania(compania);
                    erpFeComprobantesId.setNumero(Integer.parseInt(ss.NUMERO));
                    erpFeComprobantes.setId(erpFeComprobantesId);
                    erpFeComprobantes.setFolio(ss.FOLIO);
                    erpFeComprobantes.setUuid(ss.uuid);

                    erpFeEmisorId.setCompania(compania);
                    erpFeEmisorId.setNumero(Integer.parseInt(ss.NUMERO));
                    erpFeEmisor.setId(erpFeEmisorId);

                    erpFeReceptorId.setCompania(compania);
                    erpFeReceptorId.setNumero(Integer.parseInt(ss.NUMERO));
                    erpFeReceptor.setId(erpFeReceptorId);

                    boolean validacion = validationFE.validaRelacion(erpFeComprobantes);

                    if (validacion == false) {

                        rq.setSuccess(false);
                        rq.setData(null);

                        rq.setMsg(validationFE.getMsgErrorFe());

                        return rq;

                    }

                    boolean comprobantes = erpFeComprobantesDao.delete(erpFeComprobantes);
                    boolean emisor = erpFeEmisorDao.delete(erpFeEmisor);
                    boolean concepto = erpFeConceptosDao.deletePorFactura(compania, Integer.parseInt(ss.NUMERO));
                    boolean retenciones = erpFeImpRetencionesDao.deletePorFactura(compania, Integer.parseInt(ss.NUMERO));
                    boolean translados = erpFeImpTrasladosDao.deletePorFactura(compania, Integer.parseInt(ss.NUMERO));
                    boolean receptor = erpFeReceptorDao.delete(erpFeReceptor);

                    if (comprobantes == false) {

                        rq.setSuccess(false);
                        rq.setData(null);

                        rq.setMsg("error al borrar los comprobantes");

                        return rq;

                    }
                    if (emisor == false) {

                        rq.setSuccess(false);
                        rq.setData(null);

                        rq.setMsg("error al borrar el emisor");

                        return rq;

                    }
                    if (concepto == false) {

                        rq.setSuccess(false);
                        rq.setData(null);

                        rq.setMsg("error al borrar el concepto");

                        return rq;

                    }
                    if (retenciones == false) {

                        rq.setSuccess(false);
                        rq.setData(null);

                        rq.setMsg("error al borrar las retenciones");

                        return rq;

                    }
                    if (translados == false) {

                        rq.setSuccess(false);
                        rq.setData(null);

                        rq.setMsg("error al borrar los translados");

                        return rq;

                    }
                    if (receptor == false) {

                        rq.setSuccess(false);
                        rq.setData(null);

                        rq.setMsg("error al borrar el receptor");

                        return rq;

                    }

                    DeleteArchivo deleteArchivo = new DeleteArchivo();

                    String archivoXml = ss.XML;
                    String archivoPdf = ss.pdf;

                    boolean resultArchivoXml = true;
                    boolean resultArchivoPdf = true;

                    if (ss.ORIGEN.equalsIgnoreCase("CP")) {

                        resultArchivoXml = deleteArchivo.borrarArchivo(dirOutFileDocumentCXP, compania, archivoXml);

                    } else {

                        resultArchivoXml = deleteArchivo.borrarArchivo(dirOutFileDocument, compania, archivoXml);

                    }

                    if (resultArchivoXml == false) {

                        System.out.println("facturas no se borro archivo");
                        //rq.setSuccess(false);
                        //rq.setData(null);

                        //rq.setMsg("El archivo xml con folio: "+ss.FOLIO+"  no se pudo borrar del servidor, ");
                        errores.add("El archivo xml: " + ss.XML + "  no se pudo borrar del servidor, ");
                        //return rq;

                    }

                    if (ss.ORIGEN.equalsIgnoreCase("CP")) {

                        resultArchivoPdf = deleteArchivo.borrarArchivo(dirOutFileDocumentCXP, compania, archivoPdf);

                    } else {

                        resultArchivoPdf = deleteArchivo.borrarArchivo(dirOutFileDocument, compania, archivoPdf);

                    }

//                   if (resultArchivoPdf == false){
//                       
//                    
//                     rq.setSuccess(false);
//                     rq.setData(null);
//                     
//                     rq.setMsg("El archivo pdf no se pudo borrar del servidor");
//                     
//                     return rq;
//                
//                }
                }

            }

            String dataErr = errores.toString();
            System.out.println("errores" + errores);

            if (errores.size() == 0 || errores.isEmpty()) {
                rq.setSuccess(true);
                rq.setData(null);
                rq.setMsg("Borrados Correctamente");
            } else {

                dataErr = dataErr.replace("[", "");
                dataErr = dataErr.replace(", ]", "");
                dataErr = dataErr.replace(", ,", ", ");

                rq.setSuccess(true);
                rq.setData(null);
                rq.setMsg(dataErr);

            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return rq;
    }
    
      @RequestMapping(value = "/deleteFactPortal", method = RequestMethod.POST)
    public @ResponseBody
    ResponseQuery2 deleteActionPortal(String data, WebRequest webRequest, Model model) {

        ResponseQuery2 rq = new ResponseQuery2();
        SimpleDateFormat formatoDelTexto = new SimpleDateFormat("dd/MM/yyyy");

        //System.out.println("data"+data);
        int index = data.indexOf("[");
        if (index == -1) {
            data = "[" + data + "]";
        }

        System.out.println("data:" + data);
//
        if (model.asMap().get("compania") == null) {
            rq.setSuccess(false);
            rq.setData(null);
            rq.setMsg("La sesion no es valida.");
            return rq;
        }
        String compania = model.asMap().get("compania").toString();
        String usuario = model.asMap().get("usuario").toString();

        ErpFeComprobantes erpFeComprobantes = new ErpFeComprobantes();
        ErpFeComprobantesId erpFeComprobantesId = new ErpFeComprobantesId();
        ErpFeEmisor erpFeEmisor = new ErpFeEmisor();
        ErpFeEmisorId erpFeEmisorId = new ErpFeEmisorId();
//        ErpFeConceptos  erpFeConceptos = new ErpFeConceptos();
//        ErpFeConceptosId  erpFeConceptosId = new ErpFeConceptosId();
//        ErpFeImpRetenciones  erpFeImpRetenciones = new ErpFeImpRetenciones();
//        ErpFeImpRetencionesId  erpFeImpRetencionesId = new ErpFeImpRetencionesId();
//        ErpFeImpTraslados  erpFeImpTraslados = new ErpFeImpTraslados();
//        ErpFeImpTrasladosId  erpFeImpTrasladosId = new ErpFeImpTrasladosId();
        ErpFeReceptor erpFeReceptor = new ErpFeReceptor();
        ErpFeReceptorId erpFeReceptorId = new ErpFeReceptorId();

        ErpCpOtras erpCpOtras = new ErpCpOtras();
        ErpCpOtrasId erpCpOtrasId = new ErpCpOtrasId();

        try {
            int guardado = 0;
            String fecha = null;
            ObjectMapper mapper = new ObjectMapper();
            DateFormat df = new SimpleDateFormat("dd/MM/yyyy");

            mapper.setDateFormat(df);
            List<PortalDTO> lista = mapper.readValue(data,
                    mapper.getTypeFactory().constructCollectionType(List.class,
                            PortalDTO.class));

            if (lista.isEmpty()) {
                rq.setSuccess(false);
                rq.setData(null);
                rq.setMsg("Error no existen datos que guardar");
                return rq;
            }

            List<String> errores = new ArrayList<String>();

            int val = 0;
            Iterator<PortalDTO> it = lista.iterator();

            while (it.hasNext()) {
//                 System.out.println("-------------------------------------------------------------------");
                PortalDTO ss = it.next();
                  Querys que = new Querys();
        String store = que.getSQL("EliminaFactxOrden");
        
        Map deleteFact = new HashMap();
        
        deleteFact.put("compania", compania);
       // archivoPedidos.put("id", ss.idPedido);
        deleteFact.put("numero", ss.numero);
        deleteFact.put("orden", ss.ordenCompra);
        
        
        this.processDao.execute(store, deleteFact);

            }

            String dataErr = errores.toString();
            System.out.println("errores" + errores);

            if (errores.size() == 0 || errores.isEmpty()) {
                rq.setSuccess(true);
                rq.setData(null);
                rq.setMsg("Borrados Correctamente");
            } else {

                dataErr = dataErr.replace("[", "");
                dataErr = dataErr.replace(", ]", "");
                dataErr = dataErr.replace(", ,", ", ");

                rq.setSuccess(true);
                rq.setData(null);
                rq.setMsg(dataErr);

            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return rq;
    }
    
   
    

    public void setProcessDao(ProcessDao processDao) {
        this.processDao = processDao;
    }

    public void setValidationFE(ValidationFE validationFE) {
        this.validationFE = validationFE;
    }

    public String getDirOutFileDocument() {
        return dirOutFileDocument;
    }

    public String getDirUrlOutDocument() {
        return dirUrlOutDocument;
    }

    public String getMaxSizeDocument() {
        return maxSizeDocument;
    }

    public ValidationFE getValidationFE() {
        return validationFE;
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

    public void setErpFeComprobantesDao(ErpFeComprobantesDao erpFeComprobantesDao) {
        this.erpFeComprobantesDao = erpFeComprobantesDao;
    }

    public void setErpFeConceptosDao(ErpFeConceptosDao erpFeConceptosDao) {
        this.erpFeConceptosDao = erpFeConceptosDao;
    }

    public void setErpFeImpTrasladosDao(ErpFeImpTrasladosDao erpFeImpTrasladosDao) {
        this.erpFeImpTrasladosDao = erpFeImpTrasladosDao;
    }

    public void setErpFeImpRetencionesDao(ErpFeImpRetencionesDao erpFeImpRetencionesDao) {
        this.erpFeImpRetencionesDao = erpFeImpRetencionesDao;
    }

    public void setErpFeReceptorDao(ErpFeReceptorDao erpFeReceptorDao) {
        this.erpFeReceptorDao = erpFeReceptorDao;
    }

    public void setErpFeErrDao(ErpFeErrDao erpFeErrDao) {
        this.erpFeErrDao = erpFeErrDao;
    }

    public void setErpFeEmisorDao(ErpFeEmisorDao erpFeEmisorDao) {
        this.erpFeEmisorDao = erpFeEmisorDao;
    }

    public void setErpCpOtrasDao(ErpCpOtrasDao ErpCpOtrasDao) {
        this.ErpCpOtrasDao = ErpCpOtrasDao;
    }

}
