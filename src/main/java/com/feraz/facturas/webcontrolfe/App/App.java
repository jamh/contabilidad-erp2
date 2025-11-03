package com.feraz.facturas.webcontrolfe.App;

import com.fasterxml.jackson.databind.ObjectMapper;
//import com.feraz.mx.sat.cfdi.CMetodoPago;
import com.feraz.contabilidad.convertidor.util.ResponseComprobante;
import com.feraz.facturas.webcontrolfe.dao1.ErpFeEmisorDao;
import com.feraz.facturas.webcontrolfe.dao1.ErpFeComprobantesDao;
import com.feraz.facturas.webcontrolfe.dao1.ErpFeConceptosDao;
import com.feraz.facturas.webcontrolfe.dao1.ErpFeErrDao;
import com.feraz.facturas.webcontrolfe.dao1.ErpFeImpRetencionesDao;
import com.feraz.facturas.webcontrolfe.dao1.ErpFeImpTrasladosDao;
import com.feraz.facturas.webcontrolfe.dao1.ErpFeReceptorDao;
import com.feraz.facturas.webcontrolfe.dto.PolizasInfo;
import com.feraz.facturas.webcontrolfe.model.ErpFeEmisor;
import com.feraz.facturas.webcontrolfe.model.ErpFeEmisorId;
import com.feraz.facturas.webcontrolfe.model.ErpFeReceptor;
import com.feraz.facturas.webcontrolfe.model.ErpFeComprobantes;
import com.feraz.facturas.webcontrolfe.model.ErpFeComprobantesId;
import com.feraz.facturas.webcontrolfe.model.ErpFeConceptos;
import com.feraz.facturas.webcontrolfe.model.ErpFeConceptosId;
import com.feraz.facturas.webcontrolfe.model.ErpFeErr;
import com.feraz.facturas.webcontrolfe.model.ErpFeErrId;
import com.feraz.facturas.webcontrolfe.model.ErpFeReceptorId;
import com.feraz.facturas.webcontrolfe.model.ErpFeImpRetencionesId;
import com.feraz.facturas.webcontrolfe.model.ErpFeImpRetenciones;
import com.feraz.facturas.webcontrolfe.model.ErpFeImpTrasladosId;
import com.feraz.facturas.webcontrolfe.model.ErpFeImpTraslados;
import com.feraz.polizas3.dao.ErpPolizasXFacturasDao;
import com.feraz.polizas3.model.ErpPolizasXFacturas;
import com.feraz.cxp.dao.ErpCClientesDao;
import com.feraz.cxp.dao.ErpClientesDao;
import com.feraz.cxp.model.ErpCClientes;
import com.feraz.cxp.model.ErpCClientesId;
import com.feraz.cxp.model.ErpClientes;
import com.feraz.cxp.model.ErpClientesId;
import com.feraz.facturas.webcontrolfe.dao1.ErpFeConceptoXRetencionDao;
import com.feraz.facturas.webcontrolfe.dao1.ErpFeConceptoXTrasladoDao;
import com.feraz.facturas.webcontrolfe.dao1.ErpFeDeduccionDao;
import com.feraz.facturas.webcontrolfe.dao1.ErpFeDeduccionesDao;
import com.feraz.facturas.webcontrolfe.dao1.ErpFeDocRelacionadoDao;
import com.feraz.facturas.webcontrolfe.dao1.ErpFeImpLocalesDao;
import com.feraz.facturas.webcontrolfe.dao1.ErpFeImpuestoComplDao;
import com.feraz.facturas.webcontrolfe.dao1.ErpFeRetencionComplDao;
import com.feraz.facturas.webcontrolfe.dao1.ErpFeTrasladoComplDao;
import com.feraz.facturas.webcontrolfe.dao1.ErpFeNominaDao;
import com.feraz.facturas.webcontrolfe.dao1.ErpFeNominaEmisorDao;
import com.feraz.facturas.webcontrolfe.dao1.ErpFeNominaReceptorDao;
import com.feraz.facturas.webcontrolfe.dao1.ErpFeOtroPagoDao;
import com.feraz.facturas.webcontrolfe.dao1.ErpFePagosDao;
import com.feraz.facturas.webcontrolfe.dao1.ErpFePercepcionDao;
import com.feraz.facturas.webcontrolfe.dao1.ErpFePercepcionesDao;
import com.feraz.facturas.webcontrolfe.dao1.ErpFeTrasLocalesDao;
import com.feraz.facturas.webcontrolfe.dto.VallidaCfidDTO;
import com.feraz.facturas.webcontrolfe.dto.VallidaRespDTO;
import com.feraz.facturas.webcontrolfe.model.ErpFeConceptoXRetencion;
import com.feraz.facturas.webcontrolfe.model.ErpFeConceptoXRetencionId;
import com.feraz.facturas.webcontrolfe.model.ErpFeConceptoXTraslado;
import com.feraz.facturas.webcontrolfe.model.ErpFeConceptoXTrasladoId;
import com.feraz.facturas.webcontrolfe.model.ErpFeDeduccion;
import com.feraz.facturas.webcontrolfe.model.ErpFeDeduccionId;
import com.feraz.facturas.webcontrolfe.model.ErpFeDeducciones;
import com.feraz.facturas.webcontrolfe.model.ErpFeDeduccionesId;
import com.feraz.facturas.webcontrolfe.model.ErpFeDocRelacionado;
import com.feraz.facturas.webcontrolfe.model.ErpFeDocRelacionadoId;
import com.feraz.facturas.webcontrolfe.model.ErpFeImpLocales;
import com.feraz.facturas.webcontrolfe.model.ErpFeImpLocalesId;
import com.feraz.facturas.webcontrolfe.model.ErpFeImpuestoCompl;
import com.feraz.facturas.webcontrolfe.model.ErpFeImpuestoComplId;
import com.feraz.facturas.webcontrolfe.model.ErpFeNomina;
import com.feraz.facturas.webcontrolfe.model.ErpFeNominaEmisor;
import com.feraz.facturas.webcontrolfe.model.ErpFeNominaEmisorId;
import com.feraz.facturas.webcontrolfe.model.ErpFeNominaId;
import com.feraz.facturas.webcontrolfe.model.ErpFeNominaReceptor;
import com.feraz.facturas.webcontrolfe.model.ErpFeNominaReceptorId;
import com.feraz.facturas.webcontrolfe.model.ErpFeOtroPagoId;
import com.feraz.facturas.webcontrolfe.model.ErpFeOtroPago;
import com.feraz.facturas.webcontrolfe.model.ErpFePagos;
import com.feraz.facturas.webcontrolfe.model.ErpFePagosId;
import com.feraz.facturas.webcontrolfe.model.ErpFePercepcion;
import com.feraz.facturas.webcontrolfe.model.ErpFePercepcionId;
import com.feraz.facturas.webcontrolfe.model.ErpFePercepciones;
import com.feraz.facturas.webcontrolfe.model.ErpFePercepcionesId;
import com.feraz.facturas.webcontrolfe.model.ErpFeRetencionCompl;
import com.feraz.facturas.webcontrolfe.model.ErpFeRetencionComplId;
import com.feraz.facturas.webcontrolfe.model.ErpFeTrasLocales;
import com.feraz.facturas.webcontrolfe.model.ErpFeTrasLocalesId;
import com.feraz.facturas.webcontrolfe.model.ErpFeTrasladoCompl;
import com.feraz.facturas.webcontrolfe.model.ErpFeTrasladoComplId;
import com.feraz.mx.sat.cfdi.CTipoDeComprobante;
import com.feraz.mx.sat.cfdi4.Comprobante4;
import java.io.File;
import java.io.FileInputStream;
import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.Unmarshaller;

import mx.bigdata.sat.cfdi.CFDv32;
import mx.bigdata.sat.cfdi.v32.schema.Comprobante;
import mx.bigdata.sat.cfdi.v32.schema.Comprobante.Conceptos.Concepto;
import mx.bigdata.sat.cfdi.v32.schema.Comprobante.Impuestos.Traslados.Traslado;
import mx.bigdata.sat.cfdi.v32.schema.Comprobante.Impuestos.Retenciones.Retencion;
import mx.bigdata.sat.cfdi.v32.schema.TimbreFiscalDigital;
import org.apache.log4j.Logger;
import org.apache.xerces.dom.AttributeMap;
import org.apache.xerces.dom.ElementNSImpl;
import org.jamh.data.process.ProcessDao;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

/**
 * Ing. JAMH
 *
 */
public class App {

    private static final Logger log = Logger.getLogger(App.class);
    private ErpFeComprobantesDao erpFeComprobantesDao;
    private ErpFeEmisorDao erpFeEmisorDao;
    private ErpFeConceptosDao erpFeConceptosDao;
    private ErpFeImpTrasladosDao erpFeImpTrasladosDao;
    private ErpFeImpRetencionesDao erpFeImpRetencionesDao;
    private ErpFeReceptorDao erpFeReceptorDao;
    private ErpFeErrDao erpFeErrDao;
    private ErpPolizasXFacturasDao erpPolizasXFacturasDao;
    private ErpPolizasXFacturas relacion;
    private String nombrePdf;
    private String pathPdf;
    private String idErr;
    private String tipoCarga = "0";
    private ErpCClientesDao erpCClientesDao;
    private ProcessDao processDao;
    private ErpClientesDao erpClientesDao;
    private ErpFeConceptoXRetencionDao erpFeConceptoXRetencionDao;
    private ErpFeConceptoXTrasladoDao erpFeConceptoXTrasladoDao;
    private ErpFeNominaDao erpFeNominaDao;
    private ErpFeNominaEmisorDao erpFeNominaEmisorDao;
    private ErpFeNominaReceptorDao erpFeNominaReceptorDao;
    private ErpFePercepcionesDao erpFePercepcionesDao;
    private ErpFeDeduccionesDao erpFeDeduccionesDao;
    private ErpFePercepcionDao erpFePercepcionDao;
    private ErpFeDeduccionDao erpFeDeduccionDao;
    private ErpFePagosDao erpFePagosDao;
    private ErpFeDocRelacionadoDao erpFeDocRelacionadoDao;
    private ErpFeImpuestoComplDao erpFeImpuestoComplDao;
    private ErpFeRetencionComplDao erpFeRetencionComplDao;
    private ErpFeTrasladoComplDao erpFeTrasladoComplDao;
    private ErpFeImpLocalesDao erpFeImpLocalesDao;
    private ErpFeTrasLocalesDao erpFeTrasLocalesDao;
    private ErpFeOtroPagoDao erpFeOtroPagoDao;

    public App() {
    }

    private String usuario;

    public String validaFechaComprobante(CommonsMultipartFile file, String dirOutFileDocument, String compania) {

        try {

            Comprobante comp = CFDv32.newComprobante(file.getInputStream());

            int i = file.getOriginalFilename().lastIndexOf('.');

            String extension = file.getOriginalFilename().substring(i + 1);
            String nombreArc = file.getOriginalFilename().substring(0, i);

            comp.getFecha();

            String calendar = formatFecha(comp.getFecha(), "yyyy");
            String periodo = formatFecha(comp.getFecha(), "MM");
            String path = "";

            if (calendar.trim().equals("2015")) {
                //    revisarDirectorio(dirOutFileDocument + compania);
                path = "";

            } else {
                revisarDirectorio(dirOutFileDocument + compania + "/" + calendar + "/" + periodo);
                path = dirOutFileDocument + compania + "/" + calendar + "/" + periodo;
            }

            //System.out.println("path:"+path);
            return path;
        } catch (Exception e) {

            return "";
        }
    }

    public String validaFechaComprobante33(CommonsMultipartFile file, String dirOutFileDocument, String compania) {

        try {

            JAXBContext jaxbContext = JAXBContext.newInstance(com.feraz.mx.sat.cfdi.Comprobante.class);

            Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
            com.feraz.mx.sat.cfdi.Comprobante comp = (com.feraz.mx.sat.cfdi.Comprobante) jaxbUnmarshaller.unmarshal(file.getInputStream());

            //Comprobante comp = CFDv32.newComprobante(file.getInputStream());
            int i = file.getOriginalFilename().lastIndexOf('.');

            String extension = file.getOriginalFilename().substring(i + 1);
            String nombreArc = file.getOriginalFilename().substring(0, i);

            comp.getFecha();

            String calendar = String.valueOf(comp.getFecha().getYear());
            String periodo = String.valueOf(comp.getFecha().getMonth());

            String path = "";

            if (calendar.trim().equals("2015")) {
                //    revisarDirectorio(dirOutFileDocument + compania);
                path = "";

            } else {
                revisarDirectorio(dirOutFileDocument + compania + "/" + calendar + "/" + periodo);
                path = dirOutFileDocument + compania + "/" + calendar + "/" + periodo;
            }

            //System.out.println("path:"+path);
            return path;
        } catch (Exception e) {

            return "";
        }
    }
    
    public String validaFechaComprobante4(CommonsMultipartFile file, String dirOutFileDocument, String compania) {

        try {

             JAXBContext jaxbContext = JAXBContext.newInstance(com.feraz.mx.sat.cfdi4.Comprobante4.class);

            Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
            com.feraz.mx.sat.cfdi4.Comprobante4 comp = (com.feraz.mx.sat.cfdi4.Comprobante4) jaxbUnmarshaller.unmarshal(file.getInputStream());

            //Comprobante comp = CFDv32.newComprobante(file.getInputStream());
            int i = file.getOriginalFilename().lastIndexOf('.');

            String extension = file.getOriginalFilename().substring(i + 1);
            String nombreArc = file.getOriginalFilename().substring(0, i);

            comp.getFecha();

            String calendar = String.valueOf(comp.getFecha().getYear());
            String periodo = String.valueOf(comp.getFecha().getMonth());

            String path = "";

            if (calendar.trim().equals("2015")) {
                //    revisarDirectorio(dirOutFileDocument + compania);
                path = "";

            } else {
                revisarDirectorio(dirOutFileDocument + compania + "/" + calendar + "/" + periodo);
                path = dirOutFileDocument + compania + "/" + calendar + "/" + periodo;
            }

            //System.out.println("path:"+path);
            return path;
        } catch (Exception e) {

            return "";
        }
    }

    public String getUUID(String pathXML) {
        try {
            FileInputStream file = new FileInputStream(pathXML);
            Comprobante comp = CFDv32.newComprobante(file);
            TimbreFiscalDigital d = new mx.bigdata.sat.cfdi.v32.schema.TimbreFiscalDigital();
            for (int j = 1; comp.getComplemento().getAny().size() >= j; j++) {
                if (TimbreFiscalDigital.class.equals(comp.getComplemento().getAny().get(j - 1).getClass())) {
                    d = (TimbreFiscalDigital) comp.getComplemento().getAny().get(j - 1);
                    break;

                } else {
                }
            }
            return d.getUUID();
        } catch (Exception e) {
            log.error("Error al obtener UUID", e);
            return null;
        }
    }
    
    
    public String getUUID4(String pathXML) {
        try {
            FileInputStream file = new FileInputStream(pathXML);
            
             JAXBContext jaxbContext = JAXBContext.newInstance(com.feraz.mx.sat.cfdi4.Comprobante4.class);

            Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
            com.feraz.mx.sat.cfdi4.Comprobante4 comp = (com.feraz.mx.sat.cfdi4.Comprobante4) jaxbUnmarshaller.unmarshal(file);

            String uuid = "";
            
            if (comp.getComplemento().getAny().iterator().hasNext()) {
                                    for (int j = 1; comp.getComplemento().getAny().size() >= j; j++) {

                                        System.out.println(comp.getComplemento().getAny().get(j - 1).getClass());

                                        org.apache.xerces.dom.ElementNSImpl im = (ElementNSImpl) comp.getComplemento().getAny().get(j - 1);

                                        if (im.getNodeName().equalsIgnoreCase("tfd:TimbreFiscalDigital")) {

                                            uuid = im.getAttribute("UUID");
                                        }

                                    }

                                }
            return uuid;
        } catch (Exception e) {
            log.error("Error al obtener UUID", e);
            return null;
        }
    }

    public String validaNombreUUidComprobante(CommonsMultipartFile file, String dirOutFileDocument, String compania) {

        try {

            Comprobante comp = CFDv32.newComprobante(file.getInputStream());

            TimbreFiscalDigital d = new mx.bigdata.sat.cfdi.v32.schema.TimbreFiscalDigital();

            for (int j = 1; comp.getComplemento().getAny().size() >= j; j++) {

                if (TimbreFiscalDigital.class.equals(comp.getComplemento().getAny().get(j - 1).getClass())) {

                    // System.out.println("si hay timbre:"+comp.getComplemento().getAny().isEmpty());
                    d = (TimbreFiscalDigital) comp.getComplemento().getAny().get(j - 1);

                    break;

                } else {

                    // System.out.println("no hay timbre:"+comp.getComplemento().getAny().get(i - 1));
                }

            }

            String nombreUuid = d.getUUID();

            //System.out.println("path:"+path);
            return nombreUuid;
        } catch (Exception e) {

            return "";
        }
    }

    public String validaNombreUUidComprobante33(CommonsMultipartFile file, String dirOutFileDocument, String compania) {

        try {

            JAXBContext jaxbContext = JAXBContext.newInstance(com.feraz.mx.sat.cfdi.Comprobante.class);

            Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
            com.feraz.mx.sat.cfdi.Comprobante comp = (com.feraz.mx.sat.cfdi.Comprobante) jaxbUnmarshaller.unmarshal(file.getInputStream());

            String nombreUuid = "";

            if (comp.getComplemento().iterator().hasNext()) {
                for (int j = 1; comp.getComplemento().iterator().next().getAny().size() >= j; j++) {

                    System.out.println(comp.getComplemento().iterator().next().getAny().get(j - 1).getClass());

                    org.apache.xerces.dom.ElementNSImpl im = (ElementNSImpl) comp.getComplemento().iterator().next().getAny().get(j - 1);

                    if (!im.getNodeName().equalsIgnoreCase("pago10:Pagos")) {

                        nombreUuid = im.getAttribute("UUID");
                    }

                }

            }

            //System.out.println("path:"+path);
            return nombreUuid;
        } catch (Exception e) {

            return "";
        }
    }
    
    
     public String validaNombreUUidComprobante4(CommonsMultipartFile file, String dirOutFileDocument, String compania) {

        try {

             JAXBContext jaxbContext = JAXBContext.newInstance(com.feraz.mx.sat.cfdi4.Comprobante4.class);

            Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
            com.feraz.mx.sat.cfdi4.Comprobante4 comp = (com.feraz.mx.sat.cfdi4.Comprobante4) jaxbUnmarshaller.unmarshal(file.getInputStream());

            String nombreUuid = "";

            if (comp.getComplemento().getAny().iterator().hasNext()) {
                                    for (int j = 1; comp.getComplemento().getAny().size() >= j; j++) {

                                        System.out.println(comp.getComplemento().getAny().get(j - 1).getClass());

                                        org.apache.xerces.dom.ElementNSImpl im = (ElementNSImpl) comp.getComplemento().getAny().get(j - 1);

                                        if (im.getNodeName().equalsIgnoreCase("tfd:TimbreFiscalDigital")) {

                                            nombreUuid = im.getAttribute("UUID");
                                        }

                                    }

                                }

            //System.out.println("path:"+path);
            return nombreUuid;
        } catch (Exception e) {

            return "";
        }
    }

    public PolizasInfo validaComprobante(String compania, String archivo, String namePdf, String pathPdf) {
        //0 - No existe 
        //1 - Si Existe
        //2- Error en el fichero existio un problema
        //3- Error en el XML no esta relacionado
        PolizasInfo pi = new PolizasInfo();
        ErpPolizasXFacturas erpPolizasXFacturas = null;
        log.info("ValidaComprobante:" + archivo);
        File fichero = new File(archivo);
        if (fichero.exists()) {
            log.info("El fichero " + archivo + " existe");
        } else {
            pi.setInfTipo(2);
            pi.setMsgErr("Error al generar el archivo.");
            return pi;
        }
        try {
            FileInputStream file = new FileInputStream(archivo);

            //System.out.println("file"+file);
            Comprobante comp = CFDv32.newComprobante(file);
            // TimbreFiscalDigital d = new mx.bigdata.sat.cfdi.v32.schema.TimbreFiscalDigital();
            //   log.info("timbre fiscal:"+comp.getComplemento().getAny().get(0));
            //   log.info("timbre fiscal 2:"+comp.getComplemento().getAny().get(1));
            TimbreFiscalDigital d = new mx.bigdata.sat.cfdi.v32.schema.TimbreFiscalDigital();
            for (int i = 1; comp.getComplemento().getAny().size() >= i; i++) {

                if (TimbreFiscalDigital.class.equals(comp.getComplemento().getAny().get(i - 1).getClass())) {

                    log.info("si hay timbre:" + comp.getComplemento().getAny().isEmpty());

                    d = (TimbreFiscalDigital) comp.getComplemento().getAny().get(i - 1);

                    break;

                } else {

                    log.info("no hay timbre:" + comp.getComplemento().getAny().get(i - 1));

                }

            }
            //TimbreFiscalDigital d = (TimbreFiscalDigital) comp.getComplemento().getAny().iterator().next();
            log.info("---------------------------------------------------------------");
            log.info("UUID:" + d.getUUID());
            log.info("compania:" + compania);

//            log.info(compania+"-"+comp.getFolio()+"-"+d.getUUID()+"-"+comp.getEmisor().getRfc());
            //System.out.printf("%",compania,comp.getFolio(),comp.getSerie(),comp.getEmisor().getRfc());
//            log.info("uuid:" + d.getUUID());
            //    ErpFeComprobantes busca = erpFeComprobantesDao.buscarFactura2(compania, d.getUUID());
            // ErpFeComprobantes busca = erpFeComprobantesDao.buscarFactura2(compania, "f843a6ee-8be0-4e00-9dbc-72ced8d6949d");
            Map busca = new HashMap();
            busca.put("compania", compania);
            busca.put("uuid", d.getUUID());

            List buscaList = processDao.getMapResult("BuscaUUIDEx", busca);

            log.info("buscaList:" + buscaList);
            log.info(buscaList);

            // log.info("periodoContaList:"+ periodoContaList);
            //   log.info("busca:"+busca.getDirXML());
//          if(busca!=null){
////              log.info("numero:"+busca.getId().getNumero());
////              log.info("compania:"+compania);
//            
//         //  log.info("busca:"+erpPolizasXFacturas.getNumeroFe());
//          }
            if (!buscaList.isEmpty()) {

                Map infoFE = (HashMap) buscaList.get(0);
                log.info("Numero fe: " + infoFE.get("NUMERO"));

                erpPolizasXFacturas = erpPolizasXFacturasDao.buscaUUID(compania, infoFE.get("UUID").toString());
                relacion = erpPolizasXFacturas;
//             log.info("busca:"+erpPolizasXFacturas.getUuid());
                if (erpPolizasXFacturas == null) {
                    pi.setInfTipo(3);
                    pi.setNumeroFe(new Long(infoFE.get("NUMERO").toString()));
                    pi.setComprobante(comp);
                    pi.setUuid(d.getUUID());
                    pi.setMsgErr("Error el archivo xml ya fue cargado");
                    return pi;
                } else {
                    pi.setInfTipo(1);
                    pi.setMsgErr("Error en el XML ya tiene poliza:" + erpPolizasXFacturas.getId().getNumeroPol() + "-" + erpPolizasXFacturas.getId().getTipoPol());
                    pi.setTipoPoliza(erpPolizasXFacturas.getId().getTipoPol());
                    pi.setNumero(erpPolizasXFacturas.getId().getNumeroPol());
                    DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
                    pi.setFecha(dateFormat.format(erpPolizasXFacturas.getId().getFechaPol()));
                    pi.setUuid(d.getUUID());
                    return pi;
                }
            } else {
                pi.setInfTipo(0);
                pi.setMsgErr("El xml no existe.");
                pi.setUuid(d.getUUID());
                pi.setComprobante(comp);
                return pi;
            }

        } catch (Exception e) {
            log.error("Error validar, Error al cargar el XML:", e);
            pi.setInfTipo(2);
            pi.setMsgErr("Error al validar XML.");
            return pi;
        }
    }

    public PolizasInfo validaComprobante33(String compania, String archivo, String namePdf, String pathPdf) {
        //0 - No existe 
        //1 - Si Existe
        //2- Error en el fichero existio un problema
        //3- Error en el XML no esta relacionado
        PolizasInfo pi = new PolizasInfo();
        ErpPolizasXFacturas erpPolizasXFacturas = null;
        log.info("ValidaComprobante:" + archivo);
        File fichero = new File(archivo);
        if (fichero.exists()) {
            log.info("El fichero " + archivo + " existe");
        } else {
            pi.setInfTipo(2);
            pi.setMsgErr("Error al generar el archivo.");
            return pi;
        }
        try {

            JAXBContext jaxbContext = JAXBContext.newInstance(com.feraz.mx.sat.cfdi.Comprobante.class);

            Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
            com.feraz.mx.sat.cfdi.Comprobante comp = (com.feraz.mx.sat.cfdi.Comprobante) jaxbUnmarshaller.unmarshal(new FileInputStream(archivo));

            String uuid = "";

            if (comp.getComplemento().iterator().hasNext()) {
                for (int j = 1; comp.getComplemento().iterator().next().getAny().size() >= j; j++) {

                    System.out.println(comp.getComplemento().iterator().next().getAny().get(j - 1).getClass());

                    org.apache.xerces.dom.ElementNSImpl im = (ElementNSImpl) comp.getComplemento().iterator().next().getAny().get(j - 1);

                    uuid = im.getAttribute("UUID");

                }

            }
            //TimbreFiscalDigital d = (TimbreFiscalDigital) comp.getComplemento().getAny().iterator().next();

            log.info("UUID encontrado:" + uuid);

//            log.info(compania+"-"+comp.getFolio()+"-"+d.getUUID()+"-"+comp.getEmisor().getRfc());
            //System.out.printf("%",compania,comp.getFolio(),comp.getSerie(),comp.getEmisor().getRfc());
//            log.info("uuid:" + d.getUUID());
            ErpFeComprobantes busca = erpFeComprobantesDao.buscarFactura2(compania, uuid);
            //   log.info("busca:"+busca.getDirXML());
//          if(busca!=null){
////              log.info("numero:"+busca.getId().getNumero());
////              log.info("compania:"+compania);
//            
//         //  log.info("busca:"+erpPolizasXFacturas.getNumeroFe());
//          }
            if (busca != null) {

                System.out.println("xml ya existe");

                erpPolizasXFacturas = erpPolizasXFacturasDao.buscaUUID(compania, busca.getUuid());
                relacion = erpPolizasXFacturas;
//             log.info("busca:"+erpPolizasXFacturas.getUuid());
                if (erpPolizasXFacturas == null) {
                    pi.setInfTipo(3);
                    pi.setNumeroFe(new Long(busca.getId().getNumero()));
                    pi.setComprobante(null);
                    pi.setUuid(uuid);
                    pi.setMsgErr("Error el archivo xml ya fue cargado");
                    return pi;
                } else {
                    pi.setInfTipo(1);
                    pi.setMsgErr("Error en el XML ya tiene poliza:" + erpPolizasXFacturas.getId().getNumeroPol() + "-" + erpPolizasXFacturas.getId().getTipoPol());
                    pi.setTipoPoliza(erpPolizasXFacturas.getId().getTipoPol());
                    pi.setNumero(erpPolizasXFacturas.getId().getNumeroPol());
                    DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
                    pi.setFecha(dateFormat.format(erpPolizasXFacturas.getId().getFechaPol()));
                    pi.setUuid(uuid);
                    return pi;
                }
            } else {

                System.out.println("xml no existe");

                pi.setInfTipo(0);
                pi.setMsgErr("El xml no existe.");
                pi.setUuid(uuid);
                pi.setComprobante(null);
                return pi;
            }

        } catch (Exception e) {
            log.error("Error validar, Error al cargar el XML:", e);
            pi.setInfTipo(2);
            pi.setMsgErr("Error al validar XML.");
            return pi;
        }
    }

    public PolizasInfo validaComprobante4(String compania, String archivo, String namePdf, String pathPdf) {
        //0 - No existe 
        //1 - Si Existe
        //2- Error en el fichero existio un problema
        //3- Error en el XML no esta relacionado
        PolizasInfo pi = new PolizasInfo();
        ErpPolizasXFacturas erpPolizasXFacturas = null;
        log.info("ValidaComprobante:" + archivo);
        File fichero = new File(archivo);
        if (fichero.exists()) {
            log.info("El fichero " + archivo + " existe");
        } else {
            pi.setInfTipo(2);
            pi.setMsgErr("Error al generar el archivo.");
            return pi;
        }
        try {

            JAXBContext jaxbContext = JAXBContext.newInstance(com.feraz.mx.sat.cfdi4.Comprobante4.class);

            Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
            com.feraz.mx.sat.cfdi4.Comprobante4 comp = (com.feraz.mx.sat.cfdi4.Comprobante4) jaxbUnmarshaller.unmarshal(new FileInputStream(archivo));

            String uuid = "";

            if (comp.getComplemento().getAny().iterator().hasNext()) {
                for (int j = 1; comp.getComplemento().getAny().size() >= j; j++) {

                    System.out.println(comp.getComplemento().getAny().get(j - 1).getClass());

                    org.apache.xerces.dom.ElementNSImpl im = (ElementNSImpl) comp.getComplemento().getAny().get(j - 1);

                    uuid = im.getAttribute("UUID");

                }

            }
            //TimbreFiscalDigital d = (TimbreFiscalDigital) comp.getComplemento().getAny().iterator().next();

            log.info("UUID encontrado:" + uuid);

//            log.info(compania+"-"+comp.getFolio()+"-"+d.getUUID()+"-"+comp.getEmisor().getRfc());
            //System.out.printf("%",compania,comp.getFolio(),comp.getSerie(),comp.getEmisor().getRfc());
//            log.info("uuid:" + d.getUUID());
            ErpFeComprobantes busca = erpFeComprobantesDao.buscarFactura2(compania, uuid);
            //   log.info("busca:"+busca.getDirXML());
//          if(busca!=null){
////              log.info("numero:"+busca.getId().getNumero());
////              log.info("compania:"+compania);
//            
//         //  log.info("busca:"+erpPolizasXFacturas.getNumeroFe());
//          }
            if (busca != null) {

                System.out.println("xml ya existe");

                erpPolizasXFacturas = erpPolizasXFacturasDao.buscaUUID(compania, busca.getUuid());
                relacion = erpPolizasXFacturas;
//             log.info("busca:"+erpPolizasXFacturas.getUuid());
                if (erpPolizasXFacturas == null) {
                    pi.setInfTipo(3);
                    pi.setNumeroFe(new Long(busca.getId().getNumero()));
                    pi.setComprobante(null);
                    pi.setUuid(uuid);
                    pi.setMsgErr("Error el archivo xml ya fue cargado");
                    return pi;
                } else {
                    pi.setInfTipo(1);
                    pi.setMsgErr("Error en el XML ya tiene poliza:" + erpPolizasXFacturas.getId().getNumeroPol() + "-" + erpPolizasXFacturas.getId().getTipoPol());
                    pi.setTipoPoliza(erpPolizasXFacturas.getId().getTipoPol());
                    pi.setNumero(erpPolizasXFacturas.getId().getNumeroPol());
                    DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
                    pi.setFecha(dateFormat.format(erpPolizasXFacturas.getId().getFechaPol()));
                    pi.setUuid(uuid);
                    return pi;
                }
            } else {

                System.out.println("xml no existe");

                pi.setInfTipo(0);
                pi.setMsgErr("El xml no existe.");
                pi.setUuid(uuid);
                pi.setComprobante(null);
                return pi;
            }

        } catch (Exception e) {
            log.error("Error validar, Error al cargar el XML:", e);
            pi.setInfTipo(2);
            pi.setMsgErr("Error al validar XML.");
            return pi;
        }
    }
    
    public PolizasInfo validaComprobante33SAT(String compania, String archivo, String namePdf, String pathPdf) {
        //0 - No existe 
        //1 - Si Existe
        //2- Error en el fichero existio un problema
        //3- Error en el XML no esta relacionado
        PolizasInfo pi = new PolizasInfo();
        ErpPolizasXFacturas erpPolizasXFacturas = null;
        log.info("ValidaComprobante:" + archivo);
        File fichero = new File(archivo);
        if (fichero.exists()) {
            log.info("El fichero " + archivo + " existe");
        } else {
            pi.setInfTipo(2);
            pi.setMsgErr("Error al generar el archivo.");
            return pi;
        }
        try {

            JAXBContext jaxbContext = JAXBContext.newInstance(com.feraz.mx.sat.cfdi.Comprobante.class);

            Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
            com.feraz.mx.sat.cfdi.Comprobante comp = (com.feraz.mx.sat.cfdi.Comprobante) jaxbUnmarshaller.unmarshal(new FileInputStream(archivo));

            String uuid = "";

            if (comp.getComplemento().iterator().hasNext()) {
                for (int j = 1; comp.getComplemento().iterator().next().getAny().size() >= j; j++) {

                    System.out.println(comp.getComplemento().iterator().next().getAny().get(j - 1).getClass());

                    org.apache.xerces.dom.ElementNSImpl im = (ElementNSImpl) comp.getComplemento().iterator().next().getAny().get(j - 1);

                    uuid = im.getAttribute("UUID");

                }

            }
            //TimbreFiscalDigital d = (TimbreFiscalDigital) comp.getComplemento().getAny().iterator().next();

            log.info("UUID encontrado:" + uuid);
            String total = comp.getTotal().toString();
            if (total.contains(",")) {
                total = total.replaceAll(",", "");
            }

            final String uri = "http://localhost/EmailRead/validacfdisat";
            RestTemplate restTemplate = new RestTemplate();
            VallidaCfidDTO validaSat = new VallidaCfidDTO();
            validaSat.setUuid(uuid);
            validaSat.setRfcEmisor(comp.getEmisor().getRfc());
            validaSat.setRfcReceptor(comp.getReceptor().getRfc());
            validaSat.setTotal(total);
            VallidaRespDTO result = restTemplate.postForObject(uri, validaSat, VallidaRespDTO.class);
            log.info("valida REST SERVICE |result:" + result);

            if (!result.isValida()) {
                pi.setInfTipo(2);
                pi.setNumeroFe(null);
                pi.setComprobante(null);
                pi.setUuid(uuid);
                pi.setMsgErr("Error xml no valido en el SAT");
                return pi;
            }

//           ValidaCFDI validacfdi=new ValidaCFDI();
//          ResponseDTO resp= validacfdi.validaCFDIInv(uuid, comp.getEmisor().getRfc(), comp.getReceptor().getRfc(), total);
//            if(!resp.isValid()){
//                 pi.setInfTipo(2);
//                 pi.setMsgErr("Error al validar el CFDI en el SAT:"+resp.getMensaje());
//                return pi;
//            }
            ErpFeComprobantes busca = erpFeComprobantesDao.buscarFactura2(compania, uuid);

            if (busca != null) {

                System.out.println("xml ya existe");

                erpPolizasXFacturas = erpPolizasXFacturasDao.buscaUUID(compania, busca.getUuid());
                relacion = erpPolizasXFacturas;
//             log.info("busca:"+erpPolizasXFacturas.getUuid());
                if (erpPolizasXFacturas == null) {
                    pi.setInfTipo(3);
                    pi.setNumeroFe(new Long(busca.getId().getNumero()));
                    pi.setComprobante(null);
                    pi.setUuid(uuid);
                    pi.setMsgErr("Error el archivo xml ya fue cargado");
                    return pi;
                } else {
                    pi.setInfTipo(1);
                    pi.setMsgErr("Error en el XML ya tiene poliza:" + erpPolizasXFacturas.getId().getNumeroPol() + "-" + erpPolizasXFacturas.getId().getTipoPol());
                    pi.setTipoPoliza(erpPolizasXFacturas.getId().getTipoPol());
                    pi.setNumero(erpPolizasXFacturas.getId().getNumeroPol());
                    DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
                    pi.setFecha(dateFormat.format(erpPolizasXFacturas.getId().getFechaPol()));
                    pi.setUuid(uuid);
                    return pi;
                }
            } else {

                System.out.println("xml no existe");

                pi.setInfTipo(0);
                pi.setMsgErr("El xml no existe.");
                pi.setUuid(uuid);
                pi.setComprobante(null);
                return pi;
            }

        } catch (Exception e) {
            log.error("Error validar, Error al cargar el XML:", e);
            pi.setInfTipo(2);
            pi.setMsgErr("Error al validar XML.");
            return pi;
        }
    }

    public ErpPolizasXFacturas getRelacion() {
        return relacion;
    }

    public boolean validaCFDI1(String path, boolean varificaSAT) {
        try {
            CFDv32 cfd = new CFDv32(new FileInputStream(path));
            cfd.validar(); // Valida el XML, que todos los elementos estén presente
            cfd.verificar();
            return true;
        } catch (Exception e) {
            log.error("Error validaCFDI1:", e);
            //e.printStackTrace();
            return false;
        }

    }

    public int cargaComprobante(String archivo, String compania, String CGastos) {
        return this.cargaComprobante(archivo, compania, CGastos, "P", "");
    }

    public int cargaComprobante(String archivo, String compania, String CGastos, String origen) {
        return this.cargaComprobante(archivo, compania, CGastos, origen, "");
    }

    public int cargaComprobante(String archivo, String compania, String CGastos, String origen, String user) {
        log.info("CARGA DE COMPROBANTES");
        log.info("archivo:" + archivo);
        log.info("compania:" + compania);
        log.info("CGastos:" + CGastos);
        Comprobante comp = null;
        int num = 0;
        try {

            File fichero = new File(archivo);
            if (fichero.exists()) {
                log.info("El fichero " + archivo + "SI existe");
            } else {
                log.info("Pues va a ser que no");
            }

            FileInputStream file = new FileInputStream(archivo);

            comp = CFDv32.newComprobante(file);
            file.close();
            comp.getEmisor().getRfc();

            log.info("-----COMPROBANTES-------------");
            ObjectMapper mapper = new ObjectMapper();
//            ObjectMapper mapper2 = new ObjectMapper();
            DateFormat df = new SimpleDateFormat("dd/MM/yyyy");

            mapper.setDateFormat(df);
            ErpFeComprobantesId id = new ErpFeComprobantesId();
            id.setCompania(compania);
            //id.setCompania("TEMP");
//            log.info("erpFeComprobantesDao");
//            log.info(erpFeComprobantesDao);
//            log.info(id);
            num = erpFeComprobantesDao.getMaxIdCampo(id);
            log.info("Numero CFDI:" + num);
            id.setNumero(num);
            ErpFeComprobantes comp2 = mapper.convertValue(comp, ErpFeComprobantes.class);
            String nombreXml = fichero.getName();

            if (nombrePdf == null || nombrePdf == "") {
                log.info("xml: " + nombreXml);

                int index = nombreXml.indexOf(".xml");
                if (index == -1) {

                    index = nombreXml.indexOf(".XML");

                }
                String nombrePdf2 = nombreXml.substring(0, index);

                nombrePdf = nombrePdf2 + ".pdf";

                int index2 = archivo.indexOf(".");

                String nombreDirPdf = archivo.substring(0, index2);
                pathPdf = nombreDirPdf + ".pdf";

            }
            log.info("nombrePdf" + nombrePdf);
            log.info("pathPdf" + pathPdf);
            comp2.setId(id);
            comp2.setOrigen(origen);
            comp2.setIdConCGastos(CGastos);
            comp2.setPdf(nombrePdf);
            comp2.setDirPdf(pathPdf);
            comp2.setXml(fichero.getName());
            comp2.setDirXML(archivo);
            comp2.setRfc(comp.getEmisor().getRfc());
            comp2.setUsuario(user);
            comp2.setTipoCarga(tipoCarga);
            log.info("---------------COMPLEMENTO----------------");
            nombrePdf = "";
            usuario = "";
            tipoCarga = "0";
            TimbreFiscalDigital d = new mx.bigdata.sat.cfdi.v32.schema.TimbreFiscalDigital();

            for (int i = 1; comp.getComplemento().getAny().size() >= i; i++) {

                if (TimbreFiscalDigital.class.equals(comp.getComplemento().getAny().get(i - 1).getClass())) {

                    log.info("si hay timbre:" + comp.getComplemento().getAny().isEmpty());

                    d = (TimbreFiscalDigital) comp.getComplemento().getAny().get(i - 1);

                    break;

                } else {

                    log.info("no hay timbre:" + comp.getComplemento().getAny().get(i - 1));

                }

            }

//             log.info("UUID"+d.getUUID());
            // d = (TimbreFiscalDigital) comp.getComplemento().getAny().iterator().next();
            // log.info("Cmplento: "+d.getUUID());
            //log.info("d.getUUID(): "+d.getUUID());
            //log.info("d.getNoCertificadoSAT(): "+d.getNoCertificadoSAT());
            // log.info("d.getSelloCFD(): "+d.getSelloCFD());
            //log.info("d.getSelloSAT(): "+d.getSelloSAT());
            //log.info("d.getVersion(): "+d.getVersion());
            //log.info("d.getFechaTimbrado(): "+d.getFechaTimbrado());
            comp2.setUuid(d.getUUID());
            comp2.setNoCertSat(d.getNoCertificadoSAT());
            comp2.setSelloCfd(d.getSelloCFD());
            comp2.setSelloSat(d.getSelloSAT());
            comp2.setVersionComple(d.getVersion());
            comp2.setFechaTimbrado(d.getFechaTimbrado());

            comp2.setTotalLetra(comp.getTotal().toString());
            //comp2.setOrigen("1");
            //comp2.setIdConCGastos("1");
            //comp2.setXml("1");
//          log.info("FOLIO:"+comp2.getFolio());
//          log.info("FECHA:"+comp2.getFecha());
            ErpFeErr err = new ErpFeErr();
            ErpFeErrId errId = new ErpFeErrId();
            ErpFeComprobantes folio = erpFeComprobantesDao.buscarFactura2(compania, comp2.getUuid());
//          log.info("folio"+folio);
            if (folio != null) {

                errId.setCompania(compania);
                errId.setId(idErr);
                errId.setXml(fichero.getName());
                err.setId(errId);
                err.setMsg("El Archivo ya existe con el nombre de:" + folio.getXml());
                log.info("El Archivo ya existe con el nombre de:" + folio.getXml());
                erpFeErrDao.save(err);

                return 0;

            }
            ErpFeComprobantesId guardarSave = erpFeComprobantesDao.save(comp2);
            log.info("GuardaComprobante:" + guardarSave.getNumero());
            if (guardarSave == null) {

                errId.setCompania(compania);
                errId.setId(idErr);
                errId.setXml(fichero.getName());
                err.setId(errId);
                err.setMsg("El comprobante tubo errores al guardar");

                erpFeErrDao.save(err);

                return 0;

            }
            log.info("-----ERP_EMISOR-------------");

            ErpFeEmisor emisor = new ErpFeEmisor();
            ErpFeEmisorId idEmisor = new ErpFeEmisorId();
            if (comp.getEmisor() == null) {

                log.info("Sin emisor");

            } else {
                idEmisor.setCompania(id.getCompania());
                idEmisor.setNumero(id.getNumero());
                emisor.setId(idEmisor);
                emisor.setNombre(comp.getEmisor().getNombre());
                emisor.setRfc(comp.getEmisor().getRfc());
                if (comp.getEmisor().getDomicilioFiscal() != null) {
                    emisor.setCalle(comp.getEmisor().getDomicilioFiscal().getCalle());
                    emisor.setCodigoPostal(comp.getEmisor().getDomicilioFiscal().getCodigoPostal());
                    emisor.setColonia(comp.getEmisor().getDomicilioFiscal().getColonia());
                    emisor.setEstado(comp.getEmisor().getDomicilioFiscal().getEstado());
                    emisor.setLocalidad(comp.getEmisor().getDomicilioFiscal().getLocalidad());
                    emisor.setMunicipio(comp.getEmisor().getDomicilioFiscal().getMunicipio());
                    emisor.setNoInterior(comp.getEmisor().getDomicilioFiscal().getNoInterior());
                    emisor.setNoExterior(comp.getEmisor().getDomicilioFiscal().getNoExterior());
                    emisor.setPais(comp.getEmisor().getDomicilioFiscal().getPais());
                    emisor.setReferencia(comp.getEmisor().getDomicilioFiscal().getReferencia());
                }
                //              log.info("Referencia:" + comp.getEmisor().getDomicilioFiscal().getReferencia());
                if (comp.getEmisor().getExpedidoEn() != null) {
                    emisor.setExpColonia(comp.getEmisor().getExpedidoEn().getColonia());
                    emisor.setExpEstado(comp.getEmisor().getExpedidoEn().getEstado());
                    emisor.setExpLocalidad(comp.getEmisor().getExpedidoEn().getLocalidad());
                    emisor.setExpMunicipio(comp.getEmisor().getExpedidoEn().getMunicipio());
                    emisor.setExpNoInterior(comp.getEmisor().getExpedidoEn().getNoInterior());
                    emisor.setExpNoExterior(comp.getEmisor().getExpedidoEn().getNoExterior());
                    emisor.setExpPais(comp.getEmisor().getExpedidoEn().getPais());
                    emisor.setReferencia(comp.getEmisor().getExpedidoEn().getReferencia());
                }

                ErpFeEmisorId result = erpFeEmisorDao.save(emisor);
                log.info("saveEmisor:" + result.getNumero());
            }

//            log.info("-----CONCEPTOS------------");
            ErpFeConceptosId idC = new ErpFeConceptosId();
            ErpFeConceptos conceptos = new ErpFeConceptos();
            if (comp.getConceptos() == null) {

                log.info("Sin conceptos");

            } else {
                Iterator<Concepto> it = comp.getConceptos().getConcepto().iterator();
                Concepto con = null;
                int i = 1;

                while (it.hasNext()) {
                    con = it.next();

                    log.info("conceptos i:" + i);
                    idC.setCompania(id.getCompania());
                    idC.setNumero(id.getNumero());
                    idC.setSec(i);
                    conceptos.setCantidad(con.getCantidad());
                    conceptos.setDescripcion(con.getDescripcion());
                    conceptos.setFolio(comp2.getFolio());
                    conceptos.setImporte(con.getImporte());
                    conceptos.setNoIdentificador(con.getNoIdentificacion());
                    conceptos.setUnidad(con.getUnidad());
                    conceptos.setValorUnitario(con.getValorUnitario());
                    conceptos.setId(idC);

                    log.info("Guardando conceptos:" + erpFeConceptosDao.save(conceptos));
                    i++;

                }
            }
//            log.info("-------------TRANSLADOS----------------");
            if (comp.getImpuestos().getTraslados() == null) {

//                log.info("Sin translado");
            } else {

                Iterator<Traslado> translado = comp.getImpuestos().getTraslados().getTraslado().iterator();
                ErpFeImpTrasladosId translados = new ErpFeImpTrasladosId();
                ErpFeImpTraslados trans2 = new ErpFeImpTraslados();
                Traslado trans = null;
                int i2 = 1;
                if (translado == null) {

                } else {
                    while (translado.hasNext()) {
                        trans = translado.next();
                        //comp.getImpuestos().getTraslados().getTraslado().get(0).getImpuesto();

                        translados.setCompania(id.getCompania());
                        translados.setNumero(id.getNumero());
                        translados.setSec(i2);
                        //ErpImpTraslados trans2 = mapper.convertValue(trans, ErpImpTraslados.class);
                        trans2.setImporte(trans.getImporte());
                        trans2.setImpuesto(trans.getImpuesto());
                        trans2.setTasa(trans.getTasa());
                        trans2.setId(translados);

                        ErpFeImpTrasladosId resultTrans = erpFeImpTrasladosDao.save(trans2);

                        log.info("resultTrans:" + resultTrans);

                        i2++;

                    }
                }
            }
//            log.info("-------------RECEPTOR----------------");

            ErpFeReceptor receptor = new ErpFeReceptor();
            ErpFeReceptorId receptorId = new ErpFeReceptorId();

            if (comp.getEmisor() == null) {

                log.info("Sin receptor");

            } else {

                receptorId.setCompania(id.getCompania());
                receptorId.setNumero(id.getNumero());
                receptor.setId(receptorId);
                receptor.setNombre(comp.getReceptor().getNombre());
                receptor.setRfc(comp.getReceptor().getRfc());

                if (comp.getAddenda() != null) {
//                    log.info("ADENA:" + comp.getAddenda().getAny());
//                    log.info("ADENA:" + comp.getAddenda().getAny().size());
//                    log.info("ADENA2:" + comp.getAddenda().getAny().get(0).toString());
                }
                if (comp.getEmisor().getExpedidoEn() != null) {
                    receptor.setExpColonia(comp.getEmisor().getExpedidoEn().getColonia());
                    receptor.setExpEstado(comp.getEmisor().getExpedidoEn().getEstado());
                    receptor.setExpLocalidad(comp.getEmisor().getExpedidoEn().getLocalidad());
                    receptor.setExpMunicipio(comp.getEmisor().getExpedidoEn().getMunicipio());
                    receptor.setExpNoInterior(comp.getEmisor().getExpedidoEn().getNoInterior());
                    receptor.setExpNoExterior(comp.getEmisor().getExpedidoEn().getNoExterior());
                    receptor.setExpPais(comp.getEmisor().getExpedidoEn().getPais());
                    receptor.setExpReferencia(comp.getEmisor().getExpedidoEn().getReferencia());
                }
                ErpFeReceptorId resultRecep = erpFeReceptorDao.save(receptor);

                log.info(resultRecep);
            }
            log.info("-------------RETECIONES----------------");

            if (comp.getImpuestos().getRetenciones() == null) {

                log.info("Sin retenciones");

            } else {
//                log.info("Rete:" + comp.getImpuestos().getRetenciones().getRetencion().size());
                Iterator<Retencion> retencion = comp.getImpuestos().getRetenciones().getRetencion().iterator();
                ErpFeImpRetencionesId retId = new ErpFeImpRetencionesId();
                ErpFeImpRetenciones re = new ErpFeImpRetenciones();
                Retencion ret = null;
                int i3 = 1;

                while (retencion.hasNext()) {
                    ret = retencion.next();
                    //comp.getImpuestos().getRetenciones().getRetencion().get(0).getImporte()  
//                    log.info("datos:"+id.getCompania()+","+id.getNumero()+","+ret.getImporte()+","+ret.getImpuesto());
                    retId.setCompania(id.getCompania());
                    retId.setNumero(id.getNumero());
                    retId.setSec(i3);
                    re.setId(retId);
                    re.setImporte(ret.getImporte());
                    re.setImpuesto(ret.getImpuesto());

                    ErpFeImpRetencionesId resultRet = erpFeImpRetencionesDao.save(re);

                    log.info("resultRetenciones:" + resultRet);

                    i3++;

                }

            }

            log.info("origen Agregar:" + origen);
            if (origen.equalsIgnoreCase("CP")) {

//                log.info("-------------Preparando la creacion de Proveedor ------------------");
                Map rfcExis = new HashMap();

                rfcExis.put("compania", compania);
                rfcExis.put("rfc", comp.getEmisor().getRfc());

                List listRfc = processDao.getMapResult("BuscaRfcProveedor", rfcExis);

                if (listRfc.isEmpty()) {

                    ErpCClientes clientes = new ErpCClientes();
                    ErpCClientesId clientesId = new ErpCClientesId();

                    DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
                    Date date = new Date();
//                        log.info("Date Proveedor" + dateFormat.format(date));

                    clientesId.setCompania(compania);
                    clientesId.setOrigen("P");

                    Map numRegistros = new HashMap();

                    numRegistros.put("compania", compania);

                    List listRegistros = processDao.getMapResult("BuscaRegistrosPro", numRegistros);

                    Map count = (HashMap) listRegistros.get(0);
                    log.info("NUM_REGISTROS:" + count.get("NUM_REGISTROS"));

                    //log.info("compania" + compania);
                    //String pid = erpCClientesDao.getMaxErpCClientes(clientesId);
                    //log.info("pid" + pid);
                    clientesId.setIdCliente("000" + count.get("NUM_REGISTROS").toString());
                    clientes.setId(clientesId);
                    clientes.setNombre(comp.getEmisor().getNombre());
                    clientes.setRfc(comp.getEmisor().getRfc());
                    clientes.settPersona("M");
                    clientes.settClieprov("PES");
                    clientes.settTercero("04");
                    clientes.settOperacion("85");
                    clientes.setRazonSocial(comp.getEmisor().getNombre());
                    clientes.setfAlta(date);

                    ErpCClientesId result = erpCClientesDao.save(clientes);

                    if (result != null) {

                        errId.setCompania(compania);
                        errId.setId(idErr);

                        errId.setXml(fichero.getName());
                        err.setId(errId);
                        err.setMsg("Guardado Correctamente, Se genero el proveedor");

                    } else {

                        errId.setCompania(compania);
                        errId.setId(idErr);

                        errId.setXml(fichero.getName());
                        err.setId(errId);
                        err.setMsg("Guardado Correctamente, No se pudo generar el proveedor");

                    }
                } else {
                    //log.info("tipo normal fe");
                    errId.setCompania(compania);
                    errId.setId(idErr);

                    errId.setXml(fichero.getName());
                    err.setId(errId);
                    err.setMsg("Guardado Correctamente, El proveedor ya existe");

                }
                erpFeErrDao.update(err);

            }

            if (origen.equalsIgnoreCase("CCP")) {

                Map rfcCompania = new HashMap();

                rfcCompania.put("compania", compania);
                rfcCompania.put("rfc", comp.getEmisor().getRfc());

                List listRfcProveedore = processDao.getMapResult("BuscaExisteRfcCompania", rfcCompania);

                if (listRfcProveedore.isEmpty()) {

                    Map rfcCompaniaClien = new HashMap();

                    rfcCompaniaClien.put("compania", compania);
                    rfcCompaniaClien.put("rfc", comp.getReceptor().getRfc());

                    List listRfcCliente = processDao.getMapResult("BuscaExisteRfcCompania", rfcCompaniaClien);

                    if (!listRfcCliente.isEmpty()) {

                        Map rfcExisClien = new HashMap();

                        rfcExisClien.put("compania", compania);
                        rfcExisClien.put("rfc", comp.getReceptor().getRfc());

                        List listRfcClientes = processDao.getMapResult("BuscaRfcCliente", rfcExisClien);

                        if (listRfcClientes.isEmpty()) {

                            if (comp.getReceptor().getRfc().length() == 12) {

                                ErpClientes cliente = new ErpClientes();
                                ErpClientesId clienteId = new ErpClientesId();

                                DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
                                Date date = new Date();
                                //                        log.info("Date Proveedor" + dateFormat.format(date));

                                clienteId.setCompania(compania);
                                clienteId.setOrigen("C");

                                Map numRegistrosProvClien = new HashMap();

                                numRegistrosProvClien.put("compania", compania);

                                List listRegistrosProvClien = processDao.getMapResult("BuscaRegistrosCli", numRegistrosProvClien);

                                Map countClien = (HashMap) listRegistrosProvClien.get(0);
                                log.info("NUM_REGISTROS:" + countClien.get("NUM_REGISTROS"));

                                clienteId.setIdCliente("000" + countClien.get("NUM_REGISTROS").toString());
                                cliente.setId(clienteId);
                                cliente.setNombre(comp.getReceptor().getNombre());
                                cliente.setRfc(comp.getReceptor().getRfc());
                                cliente.setRazonSocial(comp.getReceptor().getNombre());
                                cliente.setfAlta(date);

                                ErpClientesId result = erpClientesDao.save(cliente);

                                if (result != null) {

                                    errId.setCompania(compania);
                                    errId.setId(idErr);

                                    errId.setXml(fichero.getName());
                                    err.setId(errId);
                                    err.setMsg("Guardado Correctamente, Se genero el cliente");

                                } else {

                                    errId.setCompania(compania);
                                    errId.setId(idErr);

                                    errId.setXml(fichero.getName());
                                    err.setId(errId);
                                    err.setMsg("Guardado Correctamente, No se pudo generar el cliente");

                                }
                            } else {

                                errId.setCompania(compania);
                                errId.setId(idErr);

                                errId.setXml(fichero.getName());
                                err.setId(errId);
                                err.setMsg("Guardado Correctamente, No guardo cliente. Rfc de persona Fisica");

                            }
                        } else {
                            //log.info("tipo normal fe");
                            errId.setCompania(compania);
                            errId.setId(idErr);

                            errId.setXml(fichero.getName());
                            err.setId(errId);
                            err.setMsg("Guardado Correctamente, El cliente ya existe");

                        }
                        erpFeErrDao.update(err);

                    }

                } else {

                    Map rfcExisProv = new HashMap();

                    rfcExisProv.put("compania", compania);
                    rfcExisProv.put("rfc", comp.getEmisor().getRfc());

                    List listRfcProv = processDao.getMapResult("BuscaRfcProveedor", rfcExisProv);

                    if (listRfcProv.isEmpty()) {

                        if (comp.getEmisor().getRfc().length() == 12) {

                            ErpCClientes proveedor = new ErpCClientes();
                            ErpCClientesId proveedorId = new ErpCClientesId();

                            DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
                            Date date = new Date();
                            //                        log.info("Date Proveedor" + dateFormat.format(date));

                            proveedorId.setCompania(compania);
                            proveedorId.setOrigen("P");

                            Map numRegistrosProv = new HashMap();

                            numRegistrosProv.put("compania", compania);

                            List listRegistrosProv = processDao.getMapResult("BuscaRegistrosPro", numRegistrosProv);

                            Map count = (HashMap) listRegistrosProv.get(0);
                            log.info("NUM_REGISTROS:" + count.get("NUM_REGISTROS"));

                            proveedorId.setIdCliente("000" + count.get("NUM_REGISTROS").toString());
                            proveedor.setId(proveedorId);
                            proveedor.setNombre(comp.getEmisor().getNombre());
                            proveedor.setRfc(comp.getEmisor().getRfc());
                            proveedor.settPersona("M");
                            proveedor.settClieprov("PES");
                            proveedor.settTercero("04");
                            proveedor.settOperacion("85");
                            proveedor.setRazonSocial(comp.getEmisor().getNombre());
                            proveedor.setfAlta(date);

                            ErpCClientesId result = erpCClientesDao.save(proveedor);

                            if (result != null) {

                                errId.setCompania(compania);
                                errId.setId(idErr);

                                errId.setXml(fichero.getName());
                                err.setId(errId);
                                err.setMsg("Guardado Correctamente, Se genero el proveedor");

                            } else {

                                errId.setCompania(compania);
                                errId.setId(idErr);

                                errId.setXml(fichero.getName());
                                err.setId(errId);
                                err.setMsg("Guardado Correctamente, No se pudo generar el proveedor");

                            }

                        } else {

                            errId.setCompania(compania);
                            errId.setId(idErr);

                            errId.setXml(fichero.getName());
                            err.setId(errId);
                            err.setMsg("Guardado Correctamente, No guardo proveedor. Rfc de persona Fisica");

                        }
                    } else {
                        //log.info("tipo normal fe");
                        errId.setCompania(compania);
                        errId.setId(idErr);

                        errId.setXml(fichero.getName());
                        err.setId(errId);
                        err.setMsg("Guardado Correctamente, El proveedor ya existe");

                    }
                    erpFeErrDao.update(err);

                }

            }

//            log.info("idErr:"+idErr);
            errId.setCompania(compania);
            errId.setId(idErr);

            errId.setXml(fichero.getName());
            err.setId(errId);
            err.setMsg("Guardado Correctamente");
            log.info("errId:" + errId.getId());

            if (errId.getId() != null) {
                erpFeErrDao.save(err);
            }
//            log.info("TERMINO");

        } catch (Exception e) {
            log.error("Error a cargar el CFDI", e);
            return 0;
        }

        // instantiate our spring dao object from the application context
        // FileEventDao fileEventDao = (FileEventDao)ctx.getBean("fileEventDao");
        //CFDv22 cfd = new CFDv22(comp);
        return num;

    }

    public ResponseComprobante cargaComprobante2(String archivo, String compania, String CGastos) {
//        log.info("CARGA DE COMPROBANTES");
//        log.info("archivo:" + archivo);
//        log.info("compania:" + compania);
//        log.info("CGastos:" + CGastos);
        Comprobante comp = null;
        int num = 0;
        ResponseComprobante rcomp = new ResponseComprobante();
        try {

            File fichero = new File(archivo);
            if (fichero.exists()) {
                log.info("El fichero " + archivo + " existe");
            } else {
                log.info("Pues va a ser que no");
            }

            FileInputStream file = new FileInputStream(archivo);

            comp = CFDv32.newComprobante(file);
            file.close();
            comp.getEmisor().getRfc();

            log.info("-----COMPROBANTES-------------");
            ObjectMapper mapper = new ObjectMapper();
//            ObjectMapper mapper2 = new ObjectMapper();
            DateFormat df = new SimpleDateFormat("dd/MM/yyyy");

            mapper.setDateFormat(df);
            ErpFeComprobantesId id = new ErpFeComprobantesId();
            id.setCompania(compania);
            //id.setCompania("TEMP");
//            log.info("erpFeComprobantesDao");
//            log.info(erpFeComprobantesDao);
//            log.info(id);
            num = erpFeComprobantesDao.getMaxIdCampo(id);
            id.setNumero(num);
            ErpFeComprobantes comp2 = mapper.convertValue(comp, ErpFeComprobantes.class);
            log.info("nombrePdf" + nombrePdf);
            log.info("pathPdf" + pathPdf);
            comp2.setId(id);
            comp2.setOrigen("P");
            comp2.setIdConCGastos(CGastos);
            comp2.setPdf(nombrePdf);
            comp2.setDirPdf(pathPdf);
            comp2.setXml(fichero.getName());
            comp2.setDirXML(archivo);
            comp2.setRfc(comp.getEmisor().getRfc());
            log.info("---------------COMPLEMENTO----------------");

            TimbreFiscalDigital d = new mx.bigdata.sat.cfdi.v32.schema.TimbreFiscalDigital();

            for (int i = 1; comp.getComplemento().getAny().size() >= i; i++) {

                if (TimbreFiscalDigital.class.equals(comp.getComplemento().getAny().get(i - 1).getClass())) {

                    log.info("si hay timbre:" + comp.getComplemento().getAny().isEmpty());

                    d = (TimbreFiscalDigital) comp.getComplemento().getAny().get(i - 1);

                    break;

                } else {

                    log.info("no hay timbre:" + comp.getComplemento().getAny().get(i - 1));

                }

            }
            //TimbreFiscalDigital d = (TimbreFiscalDigital) comp.getComplemento().getAny().iterator().next();

            log.info("UUID" + d.getUUID());
            // d = (TimbreFiscalDigital) comp.getComplemento().getAny().iterator().next();
            // log.info("Cmplento: "+d.getUUID());
            //log.info("d.getUUID(): "+d.getUUID());
            //log.info("d.getNoCertificadoSAT(): "+d.getNoCertificadoSAT());
            // log.info("d.getSelloCFD(): "+d.getSelloCFD());
            //log.info("d.getSelloSAT(): "+d.getSelloSAT());
            //log.info("d.getVersion(): "+d.getVersion());
            //log.info("d.getFechaTimbrado(): "+d.getFechaTimbrado());
            comp2.setUuid(d.getUUID());
            comp2.setNoCertSat(d.getNoCertificadoSAT());
            comp2.setSelloCfd(d.getSelloCFD());
            comp2.setSelloSat(d.getSelloSAT());
            comp2.setVersionComple(d.getVersion());
            comp2.setFechaTimbrado(d.getFechaTimbrado());

            ErpFeErr err = new ErpFeErr();
            ErpFeErrId errId = new ErpFeErrId();
            ErpFeComprobantes folio = erpFeComprobantesDao.buscarFactura2(compania, comp2.getUuid());

            if (folio != null) {

                errId.setCompania(compania);
                errId.setId(idErr);
                errId.setXml(fichero.getName());
                err.setId(errId);
                err.setMsg("El Archivo ya existe");

                erpFeErrDao.save(err);

                return null;

            }

            ErpFeComprobantesId guardarSave = erpFeComprobantesDao.save(comp2);
            log.info("GuardaComprobante:" + guardarSave);
            if (guardarSave == null) {

                //log.info("ya existe, no guarda"+idErr);
                errId.setCompania(compania);
                errId.setId(idErr);
                errId.setXml(fichero.getName());
                err.setId(errId);
                err.setMsg("Error el comprobante no se pudo generar.");

                erpFeErrDao.save(err);

                return null;

            }
            rcomp.setNumero(new Long(num));

            log.info("-----ERP_EMISOR-------------");

            ErpFeEmisor emisor = new ErpFeEmisor();
            ErpFeEmisorId idEmisor = new ErpFeEmisorId();
            if (comp.getEmisor() == null) {

                log.info("Sin emisor");

            } else {
                idEmisor.setCompania(id.getCompania());
                idEmisor.setNumero(id.getNumero());
                emisor.setId(idEmisor);
                emisor.setNombre(comp.getEmisor().getNombre());
                emisor.setRfc(comp.getEmisor().getRfc());
                if (comp.getEmisor().getDomicilioFiscal() != null) {
                    emisor.setCalle(comp.getEmisor().getDomicilioFiscal().getCalle());
                    emisor.setCodigoPostal(comp.getEmisor().getDomicilioFiscal().getCodigoPostal());
                    emisor.setColonia(comp.getEmisor().getDomicilioFiscal().getColonia());
                    emisor.setEstado(comp.getEmisor().getDomicilioFiscal().getEstado());
                    emisor.setLocalidad(comp.getEmisor().getDomicilioFiscal().getLocalidad());
                    emisor.setMunicipio(comp.getEmisor().getDomicilioFiscal().getMunicipio());
                    emisor.setNoInterior(comp.getEmisor().getDomicilioFiscal().getNoInterior());
                    emisor.setNoExterior(comp.getEmisor().getDomicilioFiscal().getNoExterior());
                    emisor.setPais(comp.getEmisor().getDomicilioFiscal().getPais());
                    emisor.setReferencia(comp.getEmisor().getDomicilioFiscal().getReferencia());

                    log.info("Referencia:" + comp.getEmisor().getDomicilioFiscal().getReferencia());
                }
                if (comp.getEmisor().getExpedidoEn() != null) {
                    emisor.setExpColonia(comp.getEmisor().getExpedidoEn().getColonia());
                    emisor.setExpEstado(comp.getEmisor().getExpedidoEn().getEstado());
                    emisor.setExpLocalidad(comp.getEmisor().getExpedidoEn().getLocalidad());
                    emisor.setExpMunicipio(comp.getEmisor().getExpedidoEn().getMunicipio());
                    emisor.setExpNoInterior(comp.getEmisor().getExpedidoEn().getNoInterior());
                    emisor.setExpNoExterior(comp.getEmisor().getExpedidoEn().getNoExterior());
                    emisor.setExpPais(comp.getEmisor().getExpedidoEn().getPais());
                    emisor.setReferencia(comp.getEmisor().getExpedidoEn().getReferencia());
                }

                ErpFeEmisorId result = erpFeEmisorDao.save(emisor);

                log.info("erpFeEmisorDao.save:" + result);
            }

            log.info("-----CONCEPTOS------------");
            ErpFeConceptosId idC = new ErpFeConceptosId();
            ErpFeConceptos conceptos = new ErpFeConceptos();
            if (comp.getConceptos() == null) {

                log.info("Sin conceptos");

            } else {
                Iterator<Concepto> it = comp.getConceptos().getConcepto().iterator();
                Concepto con = null;
                int i = 1;

                while (it.hasNext()) {
                    con = it.next();

                    log.info("i" + i);
                    idC.setCompania(id.getCompania());
                    idC.setNumero(id.getNumero());
                    idC.setSec(i);
                    conceptos.setCantidad(con.getCantidad());
                    conceptos.setDescripcion(con.getDescripcion());
                    conceptos.setFolio(comp2.getFolio());
                    conceptos.setImporte(con.getImporte());
                    conceptos.setNoIdentificador(con.getNoIdentificacion());
                    conceptos.setUnidad(con.getUnidad());
                    conceptos.setValorUnitario(con.getValorUnitario());
                    conceptos.setId(idC);

                    log.info(erpFeConceptosDao.save(conceptos));
                    i++;

                }
            }
            log.info("-------------TRANSLADOS----------------");
            if (comp.getImpuestos().getTraslados() == null) {

                log.info("Sin translado");

            } else {

                Iterator<Traslado> translado = comp.getImpuestos().getTraslados().getTraslado().iterator();
                ErpFeImpTrasladosId translados = new ErpFeImpTrasladosId();
                ErpFeImpTraslados trans2 = new ErpFeImpTraslados();
                Traslado trans = null;
                int i2 = 1;
                if (translado == null) {

                } else {
                    while (translado.hasNext()) {
                        trans = translado.next();
                        //comp.getImpuestos().getTraslados().getTraslado().get(0).getImpuesto();

                        translados.setCompania(id.getCompania());
                        translados.setNumero(id.getNumero());
                        translados.setSec(i2);
                        //ErpImpTraslados trans2 = mapper.convertValue(trans, ErpImpTraslados.class);
                        trans2.setImporte(trans.getImporte());
                        trans2.setImpuesto(trans.getImpuesto());
                        trans2.setTasa(trans.getTasa());
                        trans2.setId(translados);

                        ErpFeImpTrasladosId resultTrans = erpFeImpTrasladosDao.save(trans2);

                        log.info(resultTrans);

                        i2++;

                    }
                }
            }
            log.info("-------------RECEPTOR----------------");

            ErpFeReceptor receptor = new ErpFeReceptor();
            ErpFeReceptorId receptorId = new ErpFeReceptorId();

            if (comp.getEmisor() == null) {

                log.info("Sin receptor");

            } else {

                receptorId.setCompania(id.getCompania());
                receptorId.setNumero(id.getNumero());
                receptor.setId(receptorId);
                receptor.setNombre(comp.getReceptor().getNombre());
                receptor.setRfc(comp.getReceptor().getRfc());

                if (comp.getAddenda() != null) {
                    log.info("ADENA:" + comp.getAddenda().getAny());
                    log.info("ADENA:" + comp.getAddenda().getAny().size());
                    log.info("ADENA2:" + comp.getAddenda().getAny().get(0).toString());
                }
                if (comp.getEmisor().getExpedidoEn() != null) {
                    receptor.setExpColonia(comp.getEmisor().getExpedidoEn().getColonia());
                    receptor.setExpEstado(comp.getEmisor().getExpedidoEn().getEstado());
                    receptor.setExpLocalidad(comp.getEmisor().getExpedidoEn().getLocalidad());
                    receptor.setExpMunicipio(comp.getEmisor().getExpedidoEn().getMunicipio());
                    receptor.setExpNoInterior(comp.getEmisor().getExpedidoEn().getNoInterior());
                    receptor.setExpNoExterior(comp.getEmisor().getExpedidoEn().getNoExterior());
                    receptor.setExpPais(comp.getEmisor().getExpedidoEn().getPais());
                    receptor.setExpReferencia(comp.getEmisor().getExpedidoEn().getReferencia());
                }
                ErpFeReceptorId resultRecep = erpFeReceptorDao.save(receptor);

                log.info(resultRecep);
            }
            log.info("-------------RETECIONES----------------");

            if (comp.getImpuestos().getRetenciones() == null) {

                log.info("Sin retenciones");

            } else {
                log.info("Rete:" + comp.getImpuestos().getRetenciones().getRetencion().size());
                Iterator<Retencion> retencion = comp.getImpuestos().getRetenciones().getRetencion().iterator();
                ErpFeImpRetencionesId retId = new ErpFeImpRetencionesId();
                ErpFeImpRetenciones re = new ErpFeImpRetenciones();
                Retencion ret = null;
                int i3 = 1;
                log.info("Retencion:" + retencion.toString());
                while (retencion.hasNext()) {
                    ret = retencion.next();
                    //comp.getImpuestos().getRetenciones().getRetencion().get(0).getImporte()  

                    retId.setCompania(id.getCompania());
                    retId.setNumero(id.getNumero());
                    retId.setSec(i3);
                    re.setId(retId);
                    re.setImporte(ret.getImporte());
                    re.setImpuesto(ret.getImpuesto());

                    ErpFeImpRetencionesId resultRet = erpFeImpRetencionesDao.save(re);

                    log.info("erpFeImpRetencionesDao.save:" + resultRet);
                    //  id.ge
                    i3++;

                }

            }
            log.info("SALIO");

        } catch (Exception e) {
            log.error("Error cargar cargaComprobante2:", e);
            return null;
        }

        // instantiate our spring dao object from the application context
        // FileEventDao fileEventDao = (FileEventDao)ctx.getBean("fileEventDao");
        rcomp.setComprobante(comp);

        //CFDv22 cfd = new CFDv22(comp);
        return rcomp;
    }

    public String formatFecha(Date date, String format) {
        try {
            return new SimpleDateFormat(format).format(date);
        } catch (Exception e) {
            return "";
        }
    }

    public void revisarDirectorio(String dirCompania) {

        File file = new File(dirCompania + "/");
        //  System.out.println("Directory" + dirCompania + "/");
        if (!file.exists()) {
            if (file.mkdirs()) {
                //   System.out.println("Directory is created!");
            } else {
                //   System.out.println("Failed to create directory!");
            }
        }

    }

    //CargaComprobante 4 es mas rapido para obtener el maxId ya que esto marca error a
    /* ala hora de guardar la informacion causa que ya se haya inserta do el Id el numero
           
     */
    //


    public int cargaComprobante33(String archivo, String compania, String usuario, String CGastos, String origen) {
        log.info("CARGA DE COMPROBANTES 33");
        log.info("archivo:" + archivo);
        log.info("compania:" + compania);
        log.info("CGastos:" + CGastos);
        //Comprobante comp = null;
        int num = 0;
        try {

            File fichero = new File(archivo);
            if (fichero.exists()) {
                log.info("El fichero " + archivo + " existe");
            } else {
                log.info("Pues va a ser que no");
            }

            FileInputStream file = new FileInputStream(archivo);

            //  comp = CFDv32.newComprobante(file);
            JAXBContext jaxbContext = JAXBContext.newInstance(com.feraz.mx.sat.cfdi.Comprobante.class);

            Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
            com.feraz.mx.sat.cfdi.Comprobante comp = (com.feraz.mx.sat.cfdi.Comprobante) jaxbUnmarshaller.unmarshal(file);

            file.close();
            comp.getEmisor().getRfc();

            log.info("-----COMPROBANTES-------------");
            ObjectMapper mapper = new ObjectMapper();
//            ObjectMapper mapper2 = new ObjectMapper();
            DateFormat df = new SimpleDateFormat("dd/MM/yyyy");

            mapper.setDateFormat(df);

            ErpFeComprobantes comp2 = mapper.convertValue(comp, ErpFeComprobantes.class);
            String nombreXml = fichero.getName();

            if (nombrePdf == null || nombrePdf == "") {
                log.info("xml: " + nombreXml);

                int index = nombreXml.indexOf(".xml");
                if (index == -1) {

                    index = nombreXml.indexOf(".XML");

                }
                String nombrePdf2 = nombreXml.substring(0, index);

                nombrePdf = nombrePdf2 + ".pdf";

                int index2 = archivo.indexOf(".");

                String nombreDirPdf = archivo.substring(0, index2);
                pathPdf = nombreDirPdf + ".pdf";

            }
            log.info("nombrePdf" + nombrePdf);
            log.info("pathPdf" + pathPdf);

            ErpFeComprobantesId id = new ErpFeComprobantesId();
            id.setCompania(compania);

            // num = erpFeComprobantesDao.getMaxIdCampo(id);
            //log.info("Numero CFDI:"+num);
            //id.setNumero(num);
            comp2.setId(id);
            comp2.setOrigen(origen);
            comp2.setIdConCGastos(CGastos);
            comp2.setPdf(nombrePdf);
            comp2.setDirPdf(pathPdf);
            comp2.setXml(fichero.getName());
            comp2.setDirXML(archivo);
            comp2.setRfc(comp.getEmisor().getRfc());
            comp2.setUsuario(usuario);
            comp2.setTipoCarga(tipoCarga);

            if (comp.getMetodoPago() != null) {
                comp2.setMetodoDePago(comp.getMetodoPago().toString());
            }
            comp2.setFormaDePago(comp.getFormaPago());

            if (origen.equalsIgnoreCase("CP") || origen.equalsIgnoreCase("WS") || origen.equalsIgnoreCase("PRTL")) {

                Map datEmis = new HashMap();
                //  String conceptoDefault = "";
                datEmis.put("compania", compania);
                datEmis.put("rfc", comp.getEmisor().getRfc());

                List listdatEmis = processDao.getMapResult("BuscaDatosDefault", datEmis);

                System.out.println("Default");
                System.out.println(listdatEmis);

                if (listdatEmis != null) {

                    if (!listdatEmis.isEmpty()) {

                        Map datDefault = (HashMap) listdatEmis.get(0);

                        if (datDefault.get("CONCEPTO_DEFAULT") != null) {

                            comp2.setConceptoCxp(datDefault.get("CONCEPTO_DEFAULT").toString());
                        }
                        if (datDefault.get("CTO_CTO_DEFAULT") != null) {
                            comp2.setCtoCxp(datDefault.get("CTO_CTO_DEFAULT").toString());
                        }

                    }
                }

            }

            //ConvertidorNumLetr numConv = new ConvertidorNumLetr();
            //String numLetra = numConv.convertNumberToLetter(comp2.getTotal().doubleValue());
            System.out.println("total mapper "+ comp.getTotal());
             System.out.println("total object " +comp2.getTotal());
            // System.out.println(numLetra);
            comp2.setTotalLetra(comp2.getTotal().toString());

            log.info("---------------COMPLEMENTO----------------");
            nombrePdf = "";
            usuario = "";
            tipoCarga = "0";

            String uuidd = "";
            String version = "";
            String fechaTim = "";
            String noCertSat = "";
            String selloCfd = "";
            String selloSat = "";
            String nomina = "";
            if (comp.getComplemento().iterator().hasNext()) {
                for (int j = 1; comp.getComplemento().iterator().next().getAny().size() >= j; j++) {

                    System.out.println(comp.getComplemento().iterator().next().getAny().get(j - 1).getClass());
                    System.out.println(comp.getComplemento().iterator().next().getAny().get(j - 1));

                    org.apache.xerces.dom.ElementNSImpl im = (ElementNSImpl) comp.getComplemento().iterator().next().getAny().get(j - 1);

                    System.out.println(im);
                    System.out.println(im.getClass());
                    System.out.println(im.getAttributes().getLength());
                    System.out.println(im.getSchemaTypeInfo().getClass());
                    System.out.println(im.getNodeName());

                    if (im.getNodeName().equalsIgnoreCase("tfd:TimbreFiscalDigital")) {

                        uuidd = im.getAttribute("UUID");
                        version = im.getAttribute("Version");
                        fechaTim = im.getAttribute("FechaTimbrado");
                        noCertSat = im.getAttribute("NoCertificadoSAT");
                        selloCfd = im.getAttribute("SelloCFD");
                        selloSat = im.getAttribute("SelloSAT");

                    }

                }

            }

//             log.info("UUID"+d.getUUID());
            // d = (TimbreFiscalDigital) comp.getComplemento().getAny().iterator().next();
            // log.info("Cmplento: "+d.getUUID());
            //log.info("d.getUUID(): "+d.getUUID());
            //log.info("d.getNoCertificadoSAT(): "+d.getNoCertificadoSAT());
            // log.info("d.getSelloCFD(): "+d.getSelloCFD());
            //log.info("d.getSelloSAT(): "+d.getSelloSAT());
            //log.info("d.getVersion(): "+d.getVersion());
            //log.info("d.getFechaTimbrado(): "+d.getFechaTimbrado());
            comp2.setUuid(uuidd);
            comp2.setNoCertSat(noCertSat);
            comp2.setSelloCfd(selloCfd);
            comp2.setSelloSat(selloSat);
            comp2.setVersionComple(version);
            //  comp2.setFechaTimbrado(fechaTim);
            //comp2.setOrigen("1");
            //comp2.setIdConCGastos("1");
            //comp2.setXml("1");
//          log.info("FOLIO:"+comp2.getFolio());
//          log.info("FECHA:"+comp2.getFecha());
            ErpFeErr err = new ErpFeErr();
            ErpFeErrId errId = new ErpFeErrId();
            ErpFeComprobantes folio = erpFeComprobantesDao.buscarFactura2(compania, comp2.getUuid());

//          log.info("folio"+folio);
            if (folio != null) {

                errId.setCompania(compania);
                errId.setId(idErr);
                errId.setXml(fichero.getName());
                err.setId(errId);
                err.setMsg("El Archivo ya existe con el nombre de:" + folio.getXml());
                log.info("El Archivo ya existe con el nombre de:" + folio.getXml());
                erpFeErrDao.save(err);

                return 0;

            }
            ErpFeComprobantesId guardarSave = erpFeComprobantesDao.save2(comp2);

            if (guardarSave == null) {
                log.info("Guarda 2a Op");
                guardarSave = erpFeComprobantesDao.save2(comp2);
            }

            if (guardarSave == null) {

                errId.setCompania(compania);
                errId.setId(idErr);
                errId.setXml(fichero.getName());
                err.setId(errId);
                err.setMsg("El comprobante tubo errores al guardar");

                erpFeErrDao.save(err);

                return 0;

            } else {
                log.info("GuardaComprobante:" + guardarSave.getNumero());
                id.setNumero(guardarSave.getNumero());
                num = guardarSave.getNumero();

            }

            log.info("------------ERP_COMPLEMENTO_PAGOS-------------");

            if (comp.getComplemento().iterator().hasNext() && comp.getTipoDeComprobante().compareTo(CTipoDeComprobante.P) == 0) {
                for (int j = 1; comp.getComplemento().iterator().next().getAny().size() >= j; j++) {

                    System.out.println(comp.getComplemento().iterator().next().getAny().get(j - 1).getClass());
                    System.out.println(comp.getComplemento().iterator().next().getAny().get(j - 1));

                    org.apache.xerces.dom.ElementNSImpl im = (ElementNSImpl) comp.getComplemento().iterator().next().getAny().get(j - 1);

                    System.out.println(im);
                    System.out.println(im.getClass());
                    System.out.println(im.getAttributes().getLength());
                    System.out.println(im.getSchemaTypeInfo().getClass());
                    System.out.println(im.getNodeName());

                    if (im.getNodeName().equalsIgnoreCase("pago10:Pagos")) {
                        System.out.println("------Crgando Complemento de Pagos-------");

                        ErpFePagos pagos = new ErpFePagos();
                        ErpFePagosId pagosId = new ErpFePagosId();

                        ErpFeDocRelacionado docRelacionado = new ErpFeDocRelacionado();
                        ErpFeDocRelacionadoId docRelacionadoId = new ErpFeDocRelacionadoId();

                        ErpFeImpuestoCompl impCompl = new ErpFeImpuestoCompl();
                        ErpFeImpuestoComplId impComplId = new ErpFeImpuestoComplId();

                        ErpFeRetencionCompl retCompl = new ErpFeRetencionCompl();
                        ErpFeRetencionComplId retComplId = new ErpFeRetencionComplId();

                        ErpFeTrasladoCompl trasCompl = new ErpFeTrasladoCompl();
                        ErpFeTrasladoComplId trasComplId = new ErpFeTrasladoComplId();

                        if (im.getChildNodes().getLength() > 0) {

                            System.out.println(im.getChildNodes().getLength());
                            for (int w = 0; im.getChildNodes().getLength() - 1 >= w; w++) {

                                System.out.println(im.getChildNodes().item(w));
                                System.out.println(im.getChildNodes().item(w).getNodeName());

                                org.apache.xerces.dom.AttributeMap map = (AttributeMap) im.getChildNodes().item(w).getAttributes();

                                if (im.getChildNodes().item(w).getNodeName().equalsIgnoreCase("pago10:Pago")) {

                                    pagosId.setCompania(id.getCompania());
                                    pagosId.setNumero(id.getNumero());

                                    int pagoSec = erpFePagosDao.getMaxIdErpFePagos(pagosId);

                                    pagosId.setId(pagoSec);

                                    pagos.setId(pagosId);
                                    if (map.getNamedItem("CtaBeneficiario") != null) {
                                        pagos.setCtaBeneficiario(map.getNamedItem("CtaBeneficiario").getNodeValue());
                                    }
                                    pagos.setFormaPagoP(map.getNamedItem("FormaDePagoP").getNodeValue());
                                    pagos.setMonedaP(map.getNamedItem("MonedaP").getNodeValue());

                                    pagos.setMonto(new BigDecimal(map.getNamedItem("Monto").getNodeValue().replace(" ", "")));

                                    SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");

                                    Date dateP = formatter.parse(map.getNamedItem("FechaPago").getNodeValue());

                                    pagos.setFechaPago(dateP);

                                    if (map.getNamedItem("TipoCambioP") == null) {

                                        pagos.setTipoCambioP(new BigDecimal(1));

                                    } else {

                                        pagos.setTipoCambioP(new BigDecimal(map.getNamedItem("TipoCambioP").getNodeValue()));
                                    }

                                    if (map.getNamedItem("NumOperacion") != null) {

                                        pagos.setNumOperacion(map.getNamedItem("NumOperacion").getNodeValue());
                                    }
                                    if (map.getNamedItem("RfcEmisorCtaOrd") != null) {
                                        pagos.setRfcEmisorCtaOrd(map.getNamedItem("RfcEmisorCtaOrd").getNodeValue());
                                    }

                                    if (map.getNamedItem("NomBancoOrdExt") != null) {
                                        pagos.setNomBancoOrdExt(map.getNamedItem("NomBancoOrdExt").getNodeValue());
                                    }

                                    if (map.getNamedItem("CtaOrdenante") != null) {
                                        pagos.setCtaOrdenante(map.getNamedItem("CtaOrdenante").getNodeValue());
                                    }

                                    if (map.getNamedItem("RfcEmisorCtaBen") != null) {
                                        pagos.setRfcEmisorCtaBen(map.getNamedItem("RfcEmisorCtaBen").getNodeValue());
                                    }
                                    if (map.getNamedItem("TipoCadPago") != null) {
                                        pagos.setTipoCadPago(map.getNamedItem("TipoCadPago").getNodeValue());
                                    }
                                    if (map.getNamedItem("CertPago") != null) {
                                        pagos.setCertPago(map.getNamedItem("CertPago").getNodeValue());
                                    }
                                    if (map.getNamedItem("CadPago") != null) {
                                        pagos.setCadPago(map.getNamedItem("CadPago").getNodeValue());
                                    }
                                    if (map.getNamedItem("SelloPago") != null) {
                                        pagos.setSelloPago(map.getNamedItem("SelloPago").getNodeValue());
                                    }

                                    erpFePagosDao.save(pagos);

                                    for (int z = 0; im.getChildNodes().item(w).getChildNodes().getLength() - 1 >= z; z++) {

                                        org.apache.xerces.dom.AttributeMap mapP = (AttributeMap) im.getChildNodes().item(w).getChildNodes().item(z).getAttributes();

                                        if (mapP != null) {

                                            if (im.getChildNodes().item(w).getChildNodes().item(z).getNodeName().equalsIgnoreCase("pago10:DoctoRelacionado")) {

                                                System.out.println("-------doct relacionado---------");
                                                System.out.println(mapP);

                                                docRelacionadoId.setCompania(id.getCompania());
                                                docRelacionadoId.setNumero(id.getNumero());
                                                docRelacionadoId.setIdPago(pagoSec);
                                                int idDocRel = erpFeDocRelacionadoDao.getMaxIdErpFeDocRelacionado(docRelacionadoId);
                                                docRelacionadoId.setId(idDocRel);
                                                docRelacionado.setId(docRelacionadoId);
                                                if (mapP.getNamedItem("Folio") != null) {
                                                    docRelacionado.setFolio(mapP.getNamedItem("Folio").getNodeValue());
                                                }
                                                docRelacionado.setIdDocumento(mapP.getNamedItem("IdDocumento").getNodeValue());
                                                if (mapP.getNamedItem("ImpPagado") != null) {
                                                    docRelacionado.setImpPagado(new BigDecimal(mapP.getNamedItem("ImpPagado").getNodeValue()));
                                                }
                                                System.out.println("mapP.getNamedItem(ImpSaldoAnt):" + mapP.getNamedItem("ImpSaldoAnt"));
                                                if (mapP.getNamedItem("ImpSaldoAnt") != null) {
                                                    docRelacionado.setImpSaldoAnt(new BigDecimal(mapP.getNamedItem("ImpSaldoAnt").getNodeValue()));
                                                }

                                                if (mapP.getNamedItem("ImpSaldoInsoluto") != null) {
                                                    docRelacionado.setImpSaldoInsoluto(new BigDecimal(mapP.getNamedItem("ImpSaldoInsoluto").getNodeValue()));
                                                }
                                                docRelacionado.setMetodoPagoDr(mapP.getNamedItem("MetodoDePagoDR").getNodeValue());
                                                docRelacionado.setMonedaDr(mapP.getNamedItem("MonedaDR").getNodeValue());

                                                if (mapP.getNamedItem("NumParcialidad") != null) {

                                                    docRelacionado.setNumParcialidad(Integer.parseInt(mapP.getNamedItem("NumParcialidad").getNodeValue()));

                                                }
                                                if (mapP.getNamedItem("Serie") != null) {
                                                    docRelacionado.setSerie(mapP.getNamedItem("Serie").getNodeValue());
                                                }

                                                if (map.getNamedItem("TipoCambioDR") == null) {

                                                    docRelacionado.setTipoCambioDr(new BigDecimal(1));

                                                } else {

                                                    docRelacionado.setTipoCambioDr(new BigDecimal(map.getNamedItem("TipoCambioDR").getNodeValue()));
                                                }

                                                erpFeDocRelacionadoDao.save(docRelacionado);
                                            }

                                            if (im.getChildNodes().item(w).getChildNodes().item(z).getNodeName().equalsIgnoreCase("pago10:Impuestos")) {

                                                System.out.println("-------Impuestos Complemento---------");
                                                System.out.println(mapP);

                                                impComplId.setCompania(id.getCompania());
                                                impComplId.setNumero(id.getNumero());
                                                impComplId.setIdPago(pagoSec);
                                                impCompl.setId(impComplId);

                                                if (mapP.getNamedItem("TotalImpuestosRetenidos") != null) {

                                                    impCompl.setTotImpRetenidos(new BigDecimal(mapP.getNamedItem("TotalImpuestosRetenidos").getNodeValue()));

                                                }

                                                if (mapP.getNamedItem("TotalImpuestosTrasladados") != null) {

                                                    impCompl.setTotImpRetenidos(new BigDecimal(mapP.getNamedItem("TotalImpuestosTrasladados").getNodeValue()));

                                                }

                                                erpFeImpuestoComplDao.save(impCompl);

                                                for (int a = 0; im.getChildNodes().item(w).getChildNodes().item(z).getChildNodes().getLength() - 1 >= a; a++) {

                                                    org.apache.xerces.dom.AttributeMap mapA = (AttributeMap) im.getChildNodes().item(w).getChildNodes().item(z).getChildNodes().item(a).getAttributes();

                                                    if (im.getChildNodes().item(w).getChildNodes().item(z).getChildNodes().item(a).getNodeName().equalsIgnoreCase("pago10:Retenciones")) {

                                                        for (int b = 0; im.getChildNodes().item(w).getChildNodes().item(z).getChildNodes().item(a).getChildNodes().getLength() - 1 >= b; b++) {

                                                            org.apache.xerces.dom.AttributeMap mapB = (AttributeMap) im.getChildNodes().item(w).getChildNodes().item(z).getChildNodes().item(a).getChildNodes().item(b).getAttributes();

                                                            if (mapB != null) {

                                                                retComplId.setCompania(id.getCompania());
                                                                retComplId.setNumero(id.getNumero());
                                                                retComplId.setIdPago(pagoSec);

                                                                erpFeRetencionComplDao.getMAxIdErpFeRetencionCompl(retComplId);

                                                                retCompl.setId(retComplId);
                                                                retCompl.setImporte(new BigDecimal(mapP.getNamedItem("Importe").getNodeValue()));
                                                                retCompl.setImpuesto(mapP.getNamedItem("Impuesto").getNodeValue());

                                                                erpFeRetencionComplDao.save(retCompl);

                                                            }
                                                        }

                                                    }

                                                    if (im.getChildNodes().item(w).getChildNodes().item(z).getChildNodes().item(a).getNodeName().equalsIgnoreCase("pago10:Traslados")) {

                                                        for (int b = 0; im.getChildNodes().item(w).getChildNodes().item(z).getChildNodes().item(a).getChildNodes().getLength() - 1 >= b; b++) {

                                                            org.apache.xerces.dom.AttributeMap mapB = (AttributeMap) im.getChildNodes().item(w).getChildNodes().item(z).getChildNodes().item(a).getChildNodes().item(b).getAttributes();

                                                            if (mapB != null) {

                                                                trasComplId.setCompania(id.getCompania());
                                                                trasComplId.setNumero(id.getNumero());
                                                                trasComplId.setIdPago(pagoSec);

                                                                int trasImpId = erpFeTrasladoComplDao.getMaxIdErpFeTrasladoCompl(trasComplId);
                                                                trasComplId.setId(trasImpId);
                                                                trasCompl.setId(trasComplId);

                                                                trasCompl.setImpuesto(mapP.getNamedItem("Impuesto").getNodeValue());
                                                                trasCompl.setTipoFactor(mapP.getNamedItem("TipoFactor").getNodeValue());
                                                                trasCompl.setImporte(new BigDecimal(mapP.getNamedItem("Importe").getNodeValue()));
                                                                trasCompl.setTasaCuota(new BigDecimal(mapP.getNamedItem("TasaOCuota").getNodeValue()));

                                                                erpFeTrasladoComplDao.save(trasCompl);

                                                            }
                                                        }
                                                    }

                                                }

                                            }

                                        }

                                    }

                                }

                            }
                        }
                    }
                }

            }

            log.info("-----ERP_NOMINA e impuestos locales-------------");

            if (comp.getComplemento().iterator().hasNext()) {
                for (int j = 1; comp.getComplemento().iterator().next().getAny().size() >= j; j++) {

                    System.out.println(comp.getComplemento().iterator().next().getAny().get(j - 1).getClass());
                    System.out.println(comp.getComplemento().iterator().next().getAny().get(j - 1));

                    org.apache.xerces.dom.ElementNSImpl im = (ElementNSImpl) comp.getComplemento().iterator().next().getAny().get(j - 1);

                    System.out.println(im);
                    System.out.println(im.getClass());
                    System.out.println(im.getAttributes().getLength());
                    System.out.println(im.getSchemaTypeInfo().getClass());
                    System.out.println(im.getNodeName());

                    log.info("-------ERP IMPUESTOS LOCALES-----------");

                    if (im.getNodeName().equalsIgnoreCase("implocal:ImpuestosLocales")) {

                        ErpFeImpLocales loc = new ErpFeImpLocales();
                        ErpFeImpLocalesId locid = new ErpFeImpLocalesId();

                        locid.setCompania(compania);
                        locid.setNumero(id.getNumero());
                        loc.setId(locid);
                        loc.setTotalRetenciones(new BigDecimal(im.getAttribute("TotaldeRetenciones")));
                        loc.setTotalTraslados(new BigDecimal(im.getAttribute("TotaldeTraslados")));
                        loc.setVersion(im.getAttribute("version"));

                        erpFeImpLocalesDao.save(loc);

                        if (im.getChildNodes().getLength() > 0) {
                            int sect = 0;
                            System.out.println(im.getChildNodes().getLength());
                            for (int w = 0; im.getChildNodes().getLength() - 1 >= w; w++) {

                                sect = sect + 1;
                                System.out.println(im.getChildNodes().item(w));
                                System.out.println(im.getChildNodes().item(w).getNodeName());

                                org.apache.xerces.dom.AttributeMap map = (AttributeMap) im.getChildNodes().item(w).getAttributes();
                                if (im.getChildNodes().item(w).getNodeName().equalsIgnoreCase("implocal:TrasladosLocales")) {
                                    ErpFeTrasLocales tLoc = new ErpFeTrasLocales();
                                    ErpFeTrasLocalesId tLocId = new ErpFeTrasLocalesId();
                                    tLocId.setCompania(compania);
                                    tLocId.setNumero(id.getNumero());
                                    tLocId.setSec(sect);
                                    tLoc.setId(tLocId);
                                    tLoc.setImpLocTraslado(map.getNamedItem("ImpLocTrasladado").getNodeValue());
                                    tLoc.setImporte(new BigDecimal(map.getNamedItem("Importe").getNodeValue()));
                                    tLoc.setTasaTraslado(map.getNamedItem("TasadeTraslado").getNodeValue());
                                    erpFeTrasLocalesDao.save(tLoc);
                                }
                            }
                        }

                        log.info("----Encontrado impuesto local---------");

                    }

                    if (im.getNodeName().equalsIgnoreCase("nomina12:Nomina")) {
                        System.out.println("---------------");

                        ErpFeNomina nom = new ErpFeNomina();
                        ErpFeNominaId nomId = new ErpFeNominaId();
                        ErpFeNominaEmisor nomE = new ErpFeNominaEmisor();
                        ErpFeNominaEmisorId nomEId = new ErpFeNominaEmisorId();
                        ErpFeNominaReceptor nomR = new ErpFeNominaReceptor();
                        ErpFeNominaReceptorId nomRId = new ErpFeNominaReceptorId();
                        ErpFePercepciones pers = new ErpFePercepciones();
                        ErpFePercepcionesId persId = new ErpFePercepcionesId();
                        ErpFeDeducciones deds = new ErpFeDeducciones();
                        ErpFeDeduccionesId dedsId = new ErpFeDeduccionesId();
                        ErpFePercepcion per = new ErpFePercepcion();
                        ErpFePercepcionId perId = new ErpFePercepcionId();
                        ErpFeDeduccion ded = new ErpFeDeduccion();
                        ErpFeDeduccionId dedId = new ErpFeDeduccionId();

                        nomId.setCompania(id.getCompania());
                        nomId.setNumero(id.getNumero());
                        nom.setId(nomId);
                        nom.setTotalDeducciones(new BigDecimal(im.getAttribute("TotalDeducciones")));
                        nom.setTotalPercepciones(new BigDecimal(im.getAttribute("TotalPercepciones")));
                        nom.setNumDiasPagados(im.getAttribute("NumDiasPagados"));
                        nom.setFechaFinalPago(im.getAttribute("FechaFinalPago"));
                        nom.setFechaInicialPago(im.getAttribute("FechaInicialPago"));
                        nom.setFechaPago(im.getAttribute("FechaPago"));
                        nom.setTipoNomina(im.getAttribute("TipoNomina"));
                        nom.setVersion(im.getAttribute("Version"));

                        erpFeNominaDao.save(nom);

                        if (im.getChildNodes().getLength() > 0) {

                            System.out.println(im.getChildNodes().getLength());
                            for (int w = 0; im.getChildNodes().getLength() - 1 >= w; w++) {

                                System.out.println(im.getChildNodes().item(w));
                                System.out.println(im.getChildNodes().item(w).getNodeName());

                                org.apache.xerces.dom.AttributeMap map = (AttributeMap) im.getChildNodes().item(w).getAttributes();
                                if (im.getChildNodes().item(w).getNodeName().equalsIgnoreCase("nomina12:Emisor")) {

                                    nomEId.setCompania(id.getCompania());
                                    nomEId.setNumero(id.getNumero());
                                    nomE.setId(nomEId);
                                    nomE.setRegistroPatronal(map.getNamedItem("RegistroPatronal").getNodeValue());
                                    erpFeNominaEmisorDao.save(nomE);
                                }

                                if (im.getChildNodes().item(w).getNodeName().equalsIgnoreCase("nomina12:Receptor")) {

                                    nomRId.setCompania(id.getCompania());
                                    nomRId.setNumero(id.getNumero());
                                    nomR.setId(nomRId);
                                    nomR.setClaveEntFed(map.getNamedItem("ClaveEntFed").getNodeValue());
                                    nomR.setSalarioDiarioInt(new BigDecimal(map.getNamedItem("SalarioDiarioIntegrado").getNodeValue()));
                                    if (map.getNamedItem("CuentaBancaria") != null) {
                                        nomR.setCuentaBancario(map.getNamedItem("CuentaBancaria").getNodeValue());
                                    }
                                    nomR.setPeriocidadPago(map.getNamedItem("PeriodicidadPago").getNodeValue());
                                    nomR.setRiesgoPuesto(map.getNamedItem("RiesgoPuesto").getNodeValue());
                                    nomR.setPuesto(map.getNamedItem("Puesto").getNodeValue());
                                    nomR.setDepartamento(map.getNamedItem("Departamento").getNodeValue());
                                    nomR.setNumEmpleado(map.getNamedItem("NumEmpleado").getNodeValue());
                                    nomR.setTipoRegimen(map.getNamedItem("TipoRegimen").getNodeValue());

                                    if (map.getNamedItem("TipoJornada") != null) {
                                        nomR.setTipoJornada(map.getNamedItem("TipoJornada").getNodeValue());

                                    }

                                    nomR.setSindicalizado(map.getNamedItem("Sindicalizado").getNodeValue());
                                    nomR.setTipoContrato(map.getNamedItem("TipoContrato").getNodeValue());
                                    nomR.setAntiguedad(map.getNamedItem("Antigüedad").getNodeValue());
                                    nomR.setFechaIniRelLaboral(map.getNamedItem("FechaInicioRelLaboral").getNodeValue());
                                    nomR.setNumSegSocial(map.getNamedItem("NumSeguridadSocial").getNodeValue());
                                    nomR.setCurp(map.getNamedItem("Curp").getNodeValue());

                                    erpFeNominaReceptorDao.save(nomR);

                                }

                                if (im.getChildNodes().item(w).getNodeName().equalsIgnoreCase("nomina12:Percepciones")) {

                                    persId.setCompania(id.getCompania());
                                    persId.setNumero(id.getNumero());
                                    pers.setId(persId);
                                    pers.setTotalExento(new BigDecimal(map.getNamedItem("TotalExento").getNodeValue()));
                                    pers.setTotalGravado(new BigDecimal(map.getNamedItem("TotalGravado").getNodeValue()));
                                    pers.setTotalSueldos(new BigDecimal(map.getNamedItem("TotalSueldos").getNodeValue()));

                                    erpFePercepcionesDao.save(pers);

                                    for (int z = 0; im.getChildNodes().item(w).getChildNodes().getLength() - 1 >= z; z++) {

                                        org.apache.xerces.dom.AttributeMap mapP = (AttributeMap) im.getChildNodes().item(w).getChildNodes().item(z).getAttributes();
                                        System.out.println("-------percepcion---------");

                                        perId.setCompania(id.getCompania());
                                        perId.setNumero(id.getNumero());
                                        int idPer = erpFePercepcionDao.getMaxIdErpFePercepcion(perId);
                                        perId.setId(idPer);
                                        per.setId(perId);
                                        per.setImporteExento(new BigDecimal(mapP.getNamedItem("ImporteExento").getNodeValue()));
                                        per.setImporteGravado(new BigDecimal(mapP.getNamedItem("ImporteGravado").getNodeValue()));
                                        per.setConcepto(mapP.getNamedItem("Concepto").getNodeValue());
                                        per.setClave(mapP.getNamedItem("Clave").getNodeValue());
                                        per.setTipoPercepcion(mapP.getNamedItem("TipoPercepcion").getNodeValue());

                                        erpFePercepcionDao.save(per);
                                    }

                                }

                                if (im.getChildNodes().item(w).getNodeName().equalsIgnoreCase("nomina12:Deducciones")) {

                                    dedsId.setCompania(id.getCompania());
                                    dedsId.setNumero(id.getNumero());
                                    deds.setId(dedsId);
                                    deds.setTotalOtrasDeduc(new BigDecimal(map.getNamedItem("TotalOtrasDeducciones").getNodeValue()));

                                    erpFeDeduccionesDao.save(deds);

                                    for (int z = 0; im.getChildNodes().item(w).getChildNodes().getLength() - 1 >= z; z++) {

                                        org.apache.xerces.dom.AttributeMap mapP = (AttributeMap) im.getChildNodes().item(w).getChildNodes().item(z).getAttributes();
                                        System.out.println("-------deduccion---------");

                                        dedId.setCompania(id.getCompania());
                                        dedId.setNumero(id.getNumero());
                                        int idDeduc = erpFeDeduccionDao.getMaxIdErpFeDeduccion(dedId);
                                        dedId.setId(idDeduc);
                                        ded.setId(dedId);
                                        ded.setConcepto(mapP.getNamedItem("Concepto").getNodeValue());
                                        ded.setImporte(new BigDecimal(mapP.getNamedItem("Importe").getNodeValue()));
                                        ded.setClave(mapP.getNamedItem("Clave").getNodeValue());
                                        ded.setTipoDeduccion(mapP.getNamedItem("TipoDeduccion").getNodeValue());

                                        erpFeDeduccionDao.save(ded);

                                    }

                                }

                            }
                        }
                    }
                }

            }

            log.info("-----ERP_EMISOR-------------");

            ErpFeEmisor emisor = new ErpFeEmisor();
            ErpFeEmisorId idEmisor = new ErpFeEmisorId();
            if (comp.getEmisor() == null) {

                log.info("Sin emisor");

            } else {
                idEmisor.setCompania(id.getCompania());
                idEmisor.setNumero(id.getNumero());
                emisor.setId(idEmisor);
                emisor.setNombre(comp.getEmisor().getNombre());
                emisor.setRfc(comp.getEmisor().getRfc());
                emisor.setRegimenFiscal(comp.getEmisor().getRegimenFiscal());

//                if (comp.getEmisor().getDomicilioFiscal() != null) {
//                emisor.setCalle(comp.getEmisor().getDomicilioFiscal().getCalle());
//                emisor.setCodigoPostal(comp.getEmisor().getDomicilioFiscal().getCodigoPostal());
//                emisor.setColonia(comp.getEmisor().getDomicilioFiscal().getColonia());
//                emisor.setEstado(comp.getEmisor().getDomicilioFiscal().getEstado());
//                emisor.setLocalidad(comp.getEmisor().getDomicilioFiscal().getLocalidad());
//                emisor.setMunicipio(comp.getEmisor().getDomicilioFiscal().getMunicipio());
//                emisor.setNoInterior(comp.getEmisor().getDomicilioFiscal().getNoInterior());
//                emisor.setNoExterior(comp.getEmisor().getDomicilioFiscal().getNoExterior());
//                emisor.setPais(comp.getEmisor().getDomicilioFiscal().getPais());
//                emisor.setReferencia(comp.getEmisor().getDomicilioFiscal().getReferencia());
//                }
                //  log.info("Referencia:" + comp.getEmisor().getDomicilioFiscal().getReferencia());
//                if (comp.getEmisor().getExpedidoEn() != null) {
//                    emisor.setExpColonia(comp.getEmisor().getExpedidoEn().getColonia());
//                    emisor.setExpEstado(comp.getEmisor().getExpedidoEn().getEstado());
//                    emisor.setExpLocalidad(comp.getEmisor().getExpedidoEn().getLocalidad());
//                    emisor.setExpMunicipio(comp.getEmisor().getExpedidoEn().getMunicipio());
//                    emisor.setExpNoInterior(comp.getEmisor().getExpedidoEn().getNoInterior());
//                    emisor.setExpNoExterior(comp.getEmisor().getExpedidoEn().getNoExterior());
//                    emisor.setExpPais(comp.getEmisor().getExpedidoEn().getPais());
//                    emisor.setReferencia(comp.getEmisor().getExpedidoEn().getReferencia());
//                }
                ErpFeEmisorId result = erpFeEmisorDao.save(emisor);
                log.info("saveEmisor:" + result.getNumero());
            }

            log.info("-----CONCEPTOS------------");
            ErpFeConceptosId idC = new ErpFeConceptosId();
            ErpFeConceptos conceptos = new ErpFeConceptos();
            if (comp.getConceptos() == null) {

                log.info("Sin conceptos");

            } else {

                Iterator<com.feraz.mx.sat.cfdi.Comprobante.Conceptos.Concepto> it = comp.getConceptos().getConcepto().iterator();
                com.feraz.mx.sat.cfdi.Comprobante.Conceptos.Concepto con = null;
                int i = 1;

                while (it.hasNext()) {
                    con = it.next();

                    log.info("conceptos i:" + i);
                    idC.setCompania(id.getCompania());
                    idC.setNumero(id.getNumero());
                    idC.setSec(i);
                    conceptos.setCantidad(con.getCantidad());
                    conceptos.setDescripcion(con.getDescripcion());
                    conceptos.setFolio(comp2.getFolio());
                    conceptos.setImporte(con.getImporte());
                    conceptos.setNoIdentificador(con.getNoIdentificacion());
                    conceptos.setUnidad(con.getUnidad());
                    conceptos.setValorUnitario(con.getValorUnitario());
                    conceptos.setClaveUnidad(con.getClaveUnidad());
                    conceptos.setClaveProdServ(con.getClaveProdServ());

                    System.out.println("buscando descuento por concepto...");

                    System.out.println(con.getDescuento());
                    if (con.getDescuento() == null) {

                        conceptos.setDescuento(new BigDecimal(0));
                    } else {
                        conceptos.setDescuento(con.getDescuento());
                    }

                    conceptos.setId(idC);
                    System.out.println("------------impuestos por concepto-----------");
                    log.info("------------impuestos por concepto-----------");
                    System.out.println(con.getImpuestos());
                    log.info(con.getImpuestos());

                    if (con.getImpuestos() != null) {

                        log.info("Retenciones");
                        System.out.println("Retenciones");
                        System.out.println(con.getImpuestos().getRetenciones());

                        log.info("Traslados");
                        System.out.println("Traslados");
                        System.out.println(con.getImpuestos().getTraslados());

                        if (con.getImpuestos().getRetenciones() == null) {

                        } else {
//                        
                            Iterator<com.feraz.mx.sat.cfdi.Comprobante.Conceptos.Concepto.Impuestos.Retenciones.Retencion> ConcReten = con.getImpuestos().getRetenciones().getRetencion().iterator();

                            ErpFeConceptoXRetencion concXRet = new ErpFeConceptoXRetencion();

                            ErpFeConceptoXRetencionId concXRetId = new ErpFeConceptoXRetencionId();
                            com.feraz.mx.sat.cfdi.Comprobante.Conceptos.Concepto.Impuestos.Retenciones.Retencion conctrans = null;

                            int trC = 1;

                            if (ConcReten == null) {

                            } else {
                                while (ConcReten.hasNext()) {
                                    conctrans = ConcReten.next();
                                    //comp.getImpuestos().getTraslados().getTraslado().get(0).getImpuesto();

                                    concXRetId.setCompania(id.getCompania());
                                    concXRetId.setNumero(id.getNumero());
                                    concXRetId.setIdConcepto(i);
                                    concXRetId.setSec(trC);
                                    //ErpImpTraslados trans2 = mapper.convertValue(trans, ErpImpTraslados.class);
                                    concXRet.setBase(conctrans.getBase());
                                    concXRet.setFolio(comp2.getFolio());
                                    concXRet.setImporte(conctrans.getImporte());
                                    concXRet.setImpuesto(conctrans.getImpuesto());
                                    concXRet.setTasaOcuota(conctrans.getTasaOCuota());
                                    concXRet.setTipoFactor(conctrans.getTipoFactor().toString());
                                    concXRet.setId(concXRetId);

                                    ErpFeConceptoXRetencionId resultRetConc = erpFeConceptoXRetencionDao.save(concXRet);

                                    log.info("resultTransConc:" + resultRetConc.getNumero());

                                    trC++;

                                }
                            }

                        }

                        if (con.getImpuestos().getTraslados() == null) {

                        } else {
//                        
                            Iterator<com.feraz.mx.sat.cfdi.Comprobante.Conceptos.Concepto.Impuestos.Traslados.Traslado> ConcTrans = con.getImpuestos().getTraslados().getTraslado().iterator();

                            ErpFeConceptoXTraslado concXTrans = new ErpFeConceptoXTraslado();
                            ErpFeConceptoXTrasladoId concXTransId = new ErpFeConceptoXTrasladoId();

                            com.feraz.mx.sat.cfdi.Comprobante.Conceptos.Concepto.Impuestos.Traslados.Traslado conctrans2 = null;

                            int trC2 = 1;

                            if (ConcTrans == null) {

                            } else {
                                while (ConcTrans.hasNext()) {
                                    conctrans2 = ConcTrans.next();
                                    //comp.getImpuestos().getTraslados().getTraslado().get(0).getImpuesto();

                                    concXTransId.setCompania(id.getCompania());
                                    concXTransId.setNumero(id.getNumero());
                                    concXTransId.setIdConcepto(i);
                                    concXTransId.setSec(trC2);
                                    //ErpImpTraslados trans2 = mapper.convertValue(trans, ErpImpTraslados.class);
                                    concXTrans.setBase(conctrans2.getBase());
                                    concXTrans.setFolio(comp2.getFolio());
                                    concXTrans.setImporte(conctrans2.getImporte());
                                    concXTrans.setImpuesto(conctrans2.getImpuesto());
                                    concXTrans.setTasaOcuota(conctrans2.getTasaOCuota());
                                    concXTrans.setTipoFactor(conctrans2.getTipoFactor().toString());
                                    concXTrans.setId(concXTransId);

                                    ErpFeConceptoXTrasladoId resultTransConc2 = erpFeConceptoXTrasladoDao.save(concXTrans);

                                    // log.info("resultTransConc:"+resultTransConc2.getNumero());
                                    trC2++;

                                }
                            }

                        }

                    }

                    log.info("Guardando conceptos:" + erpFeConceptosDao.save(conceptos).getSec());
                    i++;

                }
            }
//            log.info("-------------TRANSLADOS----------------");
            if (comp.getImpuestos() != null) {
                if (comp.getImpuestos().getTraslados() == null) {

                    //                log.info("Sin translado");
                } else {

                    Iterator<com.feraz.mx.sat.cfdi.Comprobante.Impuestos.Traslados.Traslado> translado = comp.getImpuestos().getTraslados().getTraslado().iterator();
                    ErpFeImpTrasladosId translados = new ErpFeImpTrasladosId();
                    ErpFeImpTraslados trans2 = new ErpFeImpTraslados();
                    com.feraz.mx.sat.cfdi.Comprobante.Impuestos.Traslados.Traslado trans = null;
                    int i2 = 1;
                    if (translado == null) {

                    } else {
                        while (translado.hasNext()) {
                            trans = translado.next();
                            //comp.getImpuestos().getTraslados().getTraslado().get(0).getImpuesto();

                            translados.setCompania(id.getCompania());
                            translados.setNumero(id.getNumero());
                            translados.setSec(i2);
                            //ErpImpTraslados trans2 = mapper.convertValue(trans, ErpImpTraslados.class);
                            trans2.setImporte(trans.getImporte());
                            trans2.setImpuesto(trans.getImpuesto());
                            trans2.setTasa(trans.getTasaOCuota());
                            trans2.setTasaOCuota(trans.getTasaOCuota());
                            trans2.setTipoFactor(trans.getTipoFactor().toString());
                            trans2.setId(translados);

                            ErpFeImpTrasladosId resultTrans = erpFeImpTrasladosDao.save(trans2);

                            log.info("resultTrans:" + resultTrans.getNumero());

                            i2++;

                        }
                    }
                }
            }
//            log.info("-------------RECEPTOR----------------");

            ErpFeReceptor receptor = new ErpFeReceptor();
            ErpFeReceptorId receptorId = new ErpFeReceptorId();

            if (comp.getEmisor() == null) {

                log.info("Sin receptor");

            } else {

                receptorId.setCompania(id.getCompania());
                receptorId.setNumero(id.getNumero());
                receptor.setId(receptorId);
                receptor.setNombre(comp.getReceptor().getNombre());
                receptor.setRfc(comp.getReceptor().getRfc());

                receptor.setUsoCfdi(comp.getReceptor().getUsoCFDI().toString());

                if (comp.getAddenda() != null) {
//                    log.info("ADENA:" + comp.getAddenda().getAny());
//                    log.info("ADENA:" + comp.getAddenda().getAny().size());
//                    log.info("ADENA2:" + comp.getAddenda().getAny().get(0).toString());
                }
//                if (comp.getEmisor().getExpedidoEn() != null) {
//                    receptor.setExpColonia(comp.getEmisor().getExpedidoEn().getColonia());
//                    receptor.setExpEstado(comp.getEmisor().getExpedidoEn().getEstado());
//                    receptor.setExpLocalidad(comp.getEmisor().getExpedidoEn().getLocalidad());
//                    receptor.setExpMunicipio(comp.getEmisor().getExpedidoEn().getMunicipio());
//                    receptor.setExpNoInterior(comp.getEmisor().getExpedidoEn().getNoInterior());
//                    receptor.setExpNoExterior(comp.getEmisor().getExpedidoEn().getNoExterior());
//                    receptor.setExpPais(comp.getEmisor().getExpedidoEn().getPais());
//                    receptor.setExpReferencia(comp.getEmisor().getExpedidoEn().getReferencia());
//                }
                ErpFeReceptorId resultRecep = erpFeReceptorDao.save(receptor);

                log.info("resultRecep:" + resultRecep.getNumero());
            }
            log.info("-------------RETECIONES----------------");
            if (comp.getImpuestos() != null) {
                if (comp.getImpuestos().getRetenciones() == null) {

                    log.info("Sin retenciones");

                } else {

                    //                log.info("Rete:" + comp.getImpuestos().getRetenciones().getRetencion().size());
                    Iterator<com.feraz.mx.sat.cfdi.Comprobante.Impuestos.Retenciones.Retencion> retencion = comp.getImpuestos().getRetenciones().getRetencion().iterator();
                    ErpFeImpRetencionesId retId = new ErpFeImpRetencionesId();
                    ErpFeImpRetenciones re = new ErpFeImpRetenciones();
                    com.feraz.mx.sat.cfdi.Comprobante.Impuestos.Retenciones.Retencion ret = null;
                    int i3 = 1;

                    while (retencion.hasNext()) {
                        ret = retencion.next();
                        //comp.getImpuestos().getRetenciones().getRetencion().get(0).getImporte()  
                        //                    log.info("datos:"+id.getCompania()+","+id.getNumero()+","+ret.getImporte()+","+ret.getImpuesto());
                        retId.setCompania(id.getCompania());
                        retId.setNumero(id.getNumero());
                        retId.setSec(i3);
                        re.setId(retId);
                        re.setImporte(ret.getImporte());
                        re.setImpuesto(ret.getImpuesto());

                        ErpFeImpRetencionesId resultRet = erpFeImpRetencionesDao.save(re);

                        log.info("resultRetenciones:" + resultRet);

                        i3++;

                    }

                }
            }
            log.info("origen Agregar:" + origen);
            if (origen.equalsIgnoreCase("CP")) {

//                log.info("-------------Preparando la creacion de Proveedor ------------------");
                Map rfcExis = new HashMap();

                rfcExis.put("compania", compania);
                rfcExis.put("rfc", comp.getEmisor().getRfc());

                List listRfc = processDao.getMapResult("BuscaRfcProveedor", rfcExis);

                if (listRfc.isEmpty()) {

                    ErpCClientes clientes = new ErpCClientes();
                    ErpCClientesId clientesId = new ErpCClientesId();

                    DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
                    Date date = new Date();
//                        log.info("Date Proveedor" + dateFormat.format(date));

                    clientesId.setCompania(compania);
                    clientesId.setOrigen("P");

                    Map numRegistros = new HashMap();

                    numRegistros.put("compania", compania);

                    List listRegistros = processDao.getMapResult("BuscaRegistrosPro", numRegistros);

                    Map count = (HashMap) listRegistros.get(0);
                    log.info("NUM_REGISTROS:" + count.get("NUM_REGISTROS"));

                    //log.info("compania" + compania);
                    //String pid = erpCClientesDao.getMaxErpCClientes(clientesId);
                    //log.info("pid" + pid);
                    clientesId.setIdCliente("000" + count.get("NUM_REGISTROS").toString());
                    clientes.setId(clientesId);
                    clientes.setNombre(comp.getEmisor().getNombre());
                    clientes.setRfc(comp.getEmisor().getRfc());
                    clientes.settPersona("M");
                    clientes.settClieprov("PES");
                    clientes.settTercero("04");
                    clientes.settOperacion("85");
                    clientes.setRazonSocial(comp.getEmisor().getNombre());
                    clientes.setfAlta(date);

                    ErpCClientesId result = erpCClientesDao.save(clientes);

                    if (result != null) {

                        errId.setCompania(compania);
                        errId.setId(idErr);

                        errId.setXml(fichero.getName());
                        err.setId(errId);
                        err.setMsg("Guardado Correctamente, Se genero el proveedor");

                    } else {

                        errId.setCompania(compania);
                        errId.setId(idErr);

                        errId.setXml(fichero.getName());
                        err.setId(errId);
                        err.setMsg("Guardado Correctamente, No se pudo generar el proveedor");

                    }
                } else {
                    //log.info("tipo normal fe");
                    errId.setCompania(compania);
                    errId.setId(idErr);

                    errId.setXml(fichero.getName());
                    err.setId(errId);
                    err.setMsg("Guardado Correctamente, El proveedor ya existe");

                }
                erpFeErrDao.update(err);

            }

            if (origen.equalsIgnoreCase("CCP")) {

                Map rfcCompania = new HashMap();

                rfcCompania.put("compania", compania);
                rfcCompania.put("rfc", comp.getEmisor().getRfc());

                List listRfcProveedore = processDao.getMapResult("BuscaExisteRfcCompania", rfcCompania);

                if (listRfcProveedore.isEmpty()) {

                    Map rfcCompaniaClien = new HashMap();

                    rfcCompaniaClien.put("compania", compania);
                    rfcCompaniaClien.put("rfc", comp.getReceptor().getRfc());

                    List listRfcCliente = processDao.getMapResult("BuscaExisteRfcCompania", rfcCompaniaClien);

                    if (!listRfcCliente.isEmpty()) {

                        Map rfcExisClien = new HashMap();

                        rfcExisClien.put("compania", compania);
                        rfcExisClien.put("rfc", comp.getReceptor().getRfc());

                        List listRfcClientes = processDao.getMapResult("BuscaRfcCliente", rfcExisClien);

                        if (listRfcClientes.isEmpty()) {

                            if (comp.getReceptor().getRfc().length() == 12) {

                                ErpClientes cliente = new ErpClientes();
                                ErpClientesId clienteId = new ErpClientesId();

                                DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
                                Date date = new Date();
                                //                        log.info("Date Proveedor" + dateFormat.format(date));

                                clienteId.setCompania(compania);
                                clienteId.setOrigen("C");

                                Map numRegistrosProvClien = new HashMap();

                                numRegistrosProvClien.put("compania", compania);

                                List listRegistrosProvClien = processDao.getMapResult("BuscaRegistrosCli", numRegistrosProvClien);

                                Map countClien = (HashMap) listRegistrosProvClien.get(0);
                                log.info("NUM_REGISTROS:" + countClien.get("NUM_REGISTROS"));

                                clienteId.setIdCliente("000" + countClien.get("NUM_REGISTROS").toString());
                                cliente.setId(clienteId);
                                cliente.setNombre(comp.getReceptor().getNombre());
                                cliente.setRfc(comp.getReceptor().getRfc());
                                cliente.setRazonSocial(comp.getReceptor().getNombre());
                                cliente.setfAlta(date);

                                ErpClientesId result = erpClientesDao.save(cliente);

                                if (result != null) {

                                    errId.setCompania(compania);
                                    errId.setId(idErr);

                                    errId.setXml(fichero.getName());
                                    err.setId(errId);
                                    err.setMsg("Guardado Correctamente, Se genero el cliente");

                                } else {

                                    errId.setCompania(compania);
                                    errId.setId(idErr);

                                    errId.setXml(fichero.getName());
                                    err.setId(errId);
                                    err.setMsg("Guardado Correctamente, No se pudo generar el cliente");

                                }
                            } else {

                                errId.setCompania(compania);
                                errId.setId(idErr);

                                errId.setXml(fichero.getName());
                                err.setId(errId);
                                err.setMsg("Guardado Correctamente, No guardo cliente. Rfc de persona Fisica");

                            }
                        } else {
                            //log.info("tipo normal fe");
                            errId.setCompania(compania);
                            errId.setId(idErr);

                            errId.setXml(fichero.getName());
                            err.setId(errId);
                            err.setMsg("Guardado Correctamente, El cliente ya existe");

                        }
                        erpFeErrDao.update(err);

                    }

                } else {

                    Map rfcExisProv = new HashMap();

                    rfcExisProv.put("compania", compania);
                    rfcExisProv.put("rfc", comp.getEmisor().getRfc());

                    List listRfcProv = processDao.getMapResult("BuscaRfcProveedor", rfcExisProv);

                    if (listRfcProv.isEmpty()) {

                        if (comp.getEmisor().getRfc().length() == 12) {

                            ErpCClientes proveedor = new ErpCClientes();
                            ErpCClientesId proveedorId = new ErpCClientesId();

                            DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
                            Date date = new Date();
                            //                        log.info("Date Proveedor" + dateFormat.format(date));

                            proveedorId.setCompania(compania);
                            proveedorId.setOrigen("P");

                            Map numRegistrosProv = new HashMap();

                            numRegistrosProv.put("compania", compania);

                            List listRegistrosProv = processDao.getMapResult("BuscaRegistrosPro", numRegistrosProv);

                            Map count = (HashMap) listRegistrosProv.get(0);
                            log.info("NUM_REGISTROS:" + count.get("NUM_REGISTROS"));

                            proveedorId.setIdCliente("000" + count.get("NUM_REGISTROS").toString());
                            proveedor.setId(proveedorId);
                            proveedor.setNombre(comp.getEmisor().getNombre());
                            proveedor.setRfc(comp.getEmisor().getRfc());
                            proveedor.settPersona("M");
                            proveedor.settClieprov("PES");
                            proveedor.settTercero("04");
                            proveedor.settOperacion("85");
                            proveedor.setRazonSocial(comp.getEmisor().getNombre());
                            proveedor.setfAlta(date);

                            ErpCClientesId result = erpCClientesDao.save(proveedor);

                            if (result != null) {

                                errId.setCompania(compania);
                                errId.setId(idErr);

                                errId.setXml(fichero.getName());
                                err.setId(errId);
                                err.setMsg("Guardado Correctamente, Se genero el proveedor");

                            } else {

                                errId.setCompania(compania);
                                errId.setId(idErr);

                                errId.setXml(fichero.getName());
                                err.setId(errId);
                                err.setMsg("Guardado Correctamente, No se pudo generar el proveedor");

                            }

                        } else {

                            errId.setCompania(compania);
                            errId.setId(idErr);

                            errId.setXml(fichero.getName());
                            err.setId(errId);
                            err.setMsg("Guardado Correctamente, No guardo proveedor. Rfc de persona Fisica");

                        }
                    } else {
                        //log.info("tipo normal fe");
                        errId.setCompania(compania);
                        errId.setId(idErr);

                        errId.setXml(fichero.getName());
                        err.setId(errId);
                        err.setMsg("Guardado Correctamente, El proveedor ya existe");

                    }
                    erpFeErrDao.update(err);

                }

            }

//            log.info("idErr:"+idErr);
            errId.setCompania(compania);
            errId.setId(idErr);

            errId.setXml(fichero.getName());
            err.setId(errId);
            err.setMsg("Guardado Correctamente");
            log.info("errId:" + errId.getId());

            if (errId.getId() != null) {
                erpFeErrDao.save(err);
            }
//            log.info("TERMINO");

        } catch (Exception e) {
            log.error("Error a cargar el CFDI", e);
            return 0;
        }

        // instantiate our spring dao object from the application context
        // FileEventDao fileEventDao = (FileEventDao)ctx.getBean("fileEventDao");
        //CFDv22 cfd = new CFDv22(comp);
        return num;

    }

    
    public int cargaComprobante4(String archivo, String compania, String usuario, String CGastos, String origen) {
        log.info("CARGA DE COMPROBANTES 4");
        log.info("archivo:" + archivo);
        log.info("compania:" + compania);
        log.info("CGastos:" + CGastos);
        //Comprobante comp = null;
        int num = 0;
        try {

            File fichero = new File(archivo);
            if (fichero.exists()) {
                log.info("El fichero " + archivo + " existe");
            } else {
                log.info("Pues va a ser que no");
            }

            FileInputStream file = new FileInputStream(archivo);

            //  comp = CFDv32.newComprobante(file);
            JAXBContext jaxbContext = JAXBContext.newInstance(com.feraz.mx.sat.cfdi4.Comprobante4.class);

            Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
            com.feraz.mx.sat.cfdi4.Comprobante4 comp = (com.feraz.mx.sat.cfdi4.Comprobante4) jaxbUnmarshaller.unmarshal(file);

            file.close();
            comp.getEmisor().getRfc();

            log.info("-----COMPROBANTES-------------");
            ObjectMapper mapper = new ObjectMapper();
//            ObjectMapper mapper2 = new ObjectMapper();
            DateFormat df = new SimpleDateFormat("dd/MM/yyyy");

            mapper.setDateFormat(df);

            ErpFeComprobantes comp2 = mapper.convertValue(comp, ErpFeComprobantes.class);
            String nombreXml = fichero.getName();

            if (nombrePdf == null || nombrePdf == "") {
                log.info("xml: " + nombreXml);

                int index = nombreXml.indexOf(".xml");
                if (index == -1) {

                    index = nombreXml.indexOf(".XML");

                }
                String nombrePdf2 = nombreXml.substring(0, index);

                nombrePdf = nombrePdf2 + ".pdf";

                int index2 = archivo.indexOf(".");

                String nombreDirPdf = archivo.substring(0, index2);
                pathPdf = nombreDirPdf + ".pdf";

            }
            log.info("nombrePdf" + nombrePdf);
            log.info("pathPdf" + pathPdf);

            ErpFeComprobantesId id = new ErpFeComprobantesId();
            id.setCompania(compania);

            // num = erpFeComprobantesDao.getMaxIdCampo(id);
            //log.info("Numero CFDI:"+num);
            //id.setNumero(num);
            comp2.setId(id);
            comp2.setOrigen(origen);
            comp2.setIdConCGastos(CGastos);
            comp2.setPdf(nombrePdf);
            comp2.setDirPdf(pathPdf);
            comp2.setXml(fichero.getName());
            comp2.setDirXML(archivo);
            comp2.setRfc(comp.getEmisor().getRfc());
            comp2.setUsuario(usuario);
            comp2.setTipoCarga(tipoCarga);

            if (comp.getMetodoPago() != null) {
                comp2.setMetodoDePago(comp.getMetodoPago().toString());
            }
            comp2.setFormaDePago(comp.getFormaPago());

            if (origen.equalsIgnoreCase("CP") || origen.equalsIgnoreCase("WS") || origen.equalsIgnoreCase("PRTL")) {

                Map datEmis = new HashMap();
                //  String conceptoDefault = "";
                datEmis.put("compania", compania);
                datEmis.put("rfc", comp.getEmisor().getRfc());

                List listdatEmis = processDao.getMapResult("BuscaDatosDefault", datEmis);

                System.out.println("Default");
                System.out.println(listdatEmis);

                if (listdatEmis != null) {

                    if (!listdatEmis.isEmpty()) {

                        Map datDefault = (HashMap) listdatEmis.get(0);

                        if (datDefault.get("CONCEPTO_DEFAULT") != null) {

                            comp2.setConceptoCxp(datDefault.get("CONCEPTO_DEFAULT").toString());
                        }
                        if (datDefault.get("CTO_CTO_DEFAULT") != null) {
                            comp2.setCtoCxp(datDefault.get("CTO_CTO_DEFAULT").toString());
                        }

                    }
                }

            }

            //ConvertidorNumLetr numConv = new ConvertidorNumLetr();
            //String numLetra = numConv.convertNumberToLetter(comp2.getTotal().doubleValue());
            // System.out.println(comp2.getTotal());
            // System.out.println(numLetra);
            comp2.setTotalLetra(comp2.getTotal().toString());

            log.info("---------------COMPLEMENTO----------------");
            nombrePdf = "";
            usuario = "";
            tipoCarga = "0";

            String uuidd = "";
            String version = "";
            String fechaTim = "";
            String noCertSat = "";
            String selloCfd = "";
            String selloSat = "";
            String nomina = "";
            if (comp.getComplemento().getAny().iterator().hasNext()) {
                for (int j = 1; comp.getComplemento().getAny().size() >= j; j++) {

                    System.out.println(comp.getComplemento().getAny().get(j - 1).getClass());
                    System.out.println(comp.getComplemento().getAny().get(j - 1));

                    org.apache.xerces.dom.ElementNSImpl im = (ElementNSImpl) comp.getComplemento().getAny().get(j - 1);

                    System.out.println(im);
                    System.out.println(im.getClass());
                    System.out.println(im.getAttributes().getLength());
                    System.out.println(im.getSchemaTypeInfo().getClass());
                    System.out.println(im.getNodeName());

                    if (im.getNodeName().equalsIgnoreCase("tfd:TimbreFiscalDigital")) {

                        uuidd = im.getAttribute("UUID");
                        version = im.getAttribute("Version");
                        fechaTim = im.getAttribute("FechaTimbrado");
                        noCertSat = im.getAttribute("NoCertificadoSAT");
                        selloCfd = im.getAttribute("SelloCFD");
                        selloSat = im.getAttribute("SelloSAT");

                    }

                }

            }

//             log.info("UUID"+d.getUUID());
            // d = (TimbreFiscalDigital) comp.getComplemento().getAny().iterator().next();
            // log.info("Cmplento: "+d.getUUID());
            //log.info("d.getUUID(): "+d.getUUID());
            //log.info("d.getNoCertificadoSAT(): "+d.getNoCertificadoSAT());
            // log.info("d.getSelloCFD(): "+d.getSelloCFD());
            //log.info("d.getSelloSAT(): "+d.getSelloSAT());
            //log.info("d.getVersion(): "+d.getVersion());
            //log.info("d.getFechaTimbrado(): "+d.getFechaTimbrado());
            comp2.setUuid(uuidd);
            comp2.setNoCertSat(noCertSat);
            comp2.setSelloCfd(selloCfd);
            comp2.setSelloSat(selloSat);
            comp2.setVersionComple(version);
            //  comp2.setFechaTimbrado(fechaTim);
            //comp2.setOrigen("1");
            //comp2.setIdConCGastos("1");
            //comp2.setXml("1");
//          log.info("FOLIO:"+comp2.getFolio());
//          log.info("FECHA:"+comp2.getFecha());
            ErpFeErr err = new ErpFeErr();
            ErpFeErrId errId = new ErpFeErrId();
            ErpFeComprobantes folio = erpFeComprobantesDao.buscarFactura2(compania, comp2.getUuid());

//          log.info("folio"+folio);
            if (folio != null) {

                errId.setCompania(compania);
                errId.setId(idErr);
                errId.setXml(fichero.getName());
                err.setId(errId);
                err.setMsg("El Archivo ya existe con el nombre de:" + folio.getXml());
                log.info("El Archivo ya existe con el nombre de:" + folio.getXml());
                erpFeErrDao.save(err);

                return 0;

            }
            ErpFeComprobantesId guardarSave = erpFeComprobantesDao.save2(comp2);

            if (guardarSave == null) {
                log.info("Guarda 2a Op");
                guardarSave = erpFeComprobantesDao.save2(comp2);
            }

            if (guardarSave == null) {

                errId.setCompania(compania);
                errId.setId(idErr);
                errId.setXml(fichero.getName());
                err.setId(errId);
                err.setMsg("El comprobante tubo errores al guardar");

                erpFeErrDao.save(err);

                return 0;

            } else {
                log.info("GuardaComprobante:" + guardarSave.getNumero());
                id.setNumero(guardarSave.getNumero());
                num = guardarSave.getNumero();

            }

            log.info("------------ERP_COMPLEMENTO_PAGOS-------------");

            if (comp.getComplemento().getAny().iterator().hasNext() && comp.getTipoDeComprobante().compareTo(com.feraz.mx.sat.cfdi4.CTipoDeComprobante.P) == 0) {
                for (int j = 1; comp.getComplemento().getAny().size() >= j; j++) {

                    System.out.println(comp.getComplemento().getAny().get(j - 1).getClass());
                    System.out.println(comp.getComplemento().getAny().get(j - 1));

                    org.apache.xerces.dom.ElementNSImpl im = (ElementNSImpl) comp.getComplemento().getAny().get(j - 1);

                    System.out.println(im);
                    System.out.println(im.getClass());
                    System.out.println(im.getAttributes().getLength());
                    System.out.println(im.getSchemaTypeInfo().getClass());
                    System.out.println(im.getNodeName());

                    if (im.getNodeName().equalsIgnoreCase("pago20:Pagos")) {
                        System.out.println("------Crgando Complemento de Pagos-------");

                        ErpFePagos pagos = new ErpFePagos();
                        ErpFePagosId pagosId = new ErpFePagosId();

                        ErpFeDocRelacionado docRelacionado = new ErpFeDocRelacionado();
                        ErpFeDocRelacionadoId docRelacionadoId = new ErpFeDocRelacionadoId();

                        ErpFeImpuestoCompl impCompl = new ErpFeImpuestoCompl();
                        ErpFeImpuestoComplId impComplId = new ErpFeImpuestoComplId();

                        ErpFeRetencionCompl retCompl = new ErpFeRetencionCompl();
                        ErpFeRetencionComplId retComplId = new ErpFeRetencionComplId();

                        ErpFeTrasladoCompl trasCompl = new ErpFeTrasladoCompl();
                        ErpFeTrasladoComplId trasComplId = new ErpFeTrasladoComplId();

                        if (im.getChildNodes().getLength() > 0) {

                            System.out.println(im.getChildNodes().getLength());
                            for (int w = 0; im.getChildNodes().getLength() - 1 >= w; w++) {

                                System.out.println(im.getChildNodes().item(w));
                                System.out.println(im.getChildNodes().item(w).getNodeName());

                                org.apache.xerces.dom.AttributeMap map = (AttributeMap) im.getChildNodes().item(w).getAttributes();

                                if (im.getChildNodes().item(w).getNodeName().equalsIgnoreCase("pago20:Pago")) {

                                    pagosId.setCompania(id.getCompania());
                                    pagosId.setNumero(id.getNumero());

                                    int pagoSec = erpFePagosDao.getMaxIdErpFePagos(pagosId);

                                    pagosId.setId(pagoSec);

                                    pagos.setId(pagosId);
                                    if (map.getNamedItem("CtaBeneficiario") != null) {
                                        pagos.setCtaBeneficiario(map.getNamedItem("CtaBeneficiario").getNodeValue());
                                    }
                                    pagos.setFormaPagoP(map.getNamedItem("FormaDePagoP").getNodeValue());
                                    pagos.setMonedaP(map.getNamedItem("MonedaP").getNodeValue());

                                    pagos.setMonto(new BigDecimal(map.getNamedItem("Monto").getNodeValue().replace(" ", "")));

                                    SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");

                                    Date dateP = formatter.parse(map.getNamedItem("FechaPago").getNodeValue());

                                    pagos.setFechaPago(dateP);

                                    if (map.getNamedItem("TipoCambioP") == null) {

                                        pagos.setTipoCambioP(new BigDecimal(1));

                                    } else {

                                        pagos.setTipoCambioP(new BigDecimal(map.getNamedItem("TipoCambioP").getNodeValue()));
                                    }

                                    if (map.getNamedItem("NumOperacion") != null) {

                                        pagos.setNumOperacion(map.getNamedItem("NumOperacion").getNodeValue());
                                    }
                                    if (map.getNamedItem("RfcEmisorCtaOrd") != null) {
                                        pagos.setRfcEmisorCtaOrd(map.getNamedItem("RfcEmisorCtaOrd").getNodeValue());
                                    }

                                    if (map.getNamedItem("NomBancoOrdExt") != null) {
                                        pagos.setNomBancoOrdExt(map.getNamedItem("NomBancoOrdExt").getNodeValue());
                                    }

                                    if (map.getNamedItem("CtaOrdenante") != null) {
                                        pagos.setCtaOrdenante(map.getNamedItem("CtaOrdenante").getNodeValue());
                                    }

                                    if (map.getNamedItem("RfcEmisorCtaBen") != null) {
                                        pagos.setRfcEmisorCtaBen(map.getNamedItem("RfcEmisorCtaBen").getNodeValue());
                                    }
                                    if (map.getNamedItem("TipoCadPago") != null) {
                                        pagos.setTipoCadPago(map.getNamedItem("TipoCadPago").getNodeValue());
                                    }
                                    if (map.getNamedItem("CertPago") != null) {
                                        pagos.setCertPago(map.getNamedItem("CertPago").getNodeValue());
                                    }
                                    if (map.getNamedItem("CadPago") != null) {
                                        pagos.setCadPago(map.getNamedItem("CadPago").getNodeValue());
                                    }
                                    if (map.getNamedItem("SelloPago") != null) {
                                        pagos.setSelloPago(map.getNamedItem("SelloPago").getNodeValue());
                                    }

                                    erpFePagosDao.save(pagos);

                                    for (int z = 0; im.getChildNodes().item(w).getChildNodes().getLength() - 1 >= z; z++) {

                                        org.apache.xerces.dom.AttributeMap mapP = (AttributeMap) im.getChildNodes().item(w).getChildNodes().item(z).getAttributes();

                                        if (mapP != null) {

                                            if (im.getChildNodes().item(w).getChildNodes().item(z).getNodeName().equalsIgnoreCase("pago20:DoctoRelacionado")) {

                                                System.out.println("-------doct relacionado---------");
                                                System.out.println(mapP);

                                                docRelacionadoId.setCompania(id.getCompania());
                                                docRelacionadoId.setNumero(id.getNumero());
                                                docRelacionadoId.setIdPago(pagoSec);
                                                int idDocRel = erpFeDocRelacionadoDao.getMaxIdErpFeDocRelacionado(docRelacionadoId);
                                                docRelacionadoId.setId(idDocRel);
                                                docRelacionado.setId(docRelacionadoId);
                                                if (mapP.getNamedItem("Folio") != null) {
                                                    docRelacionado.setFolio(mapP.getNamedItem("Folio").getNodeValue());
                                                }
                                                docRelacionado.setIdDocumento(mapP.getNamedItem("IdDocumento").getNodeValue());
                                                if (mapP.getNamedItem("ImpPagado") != null) {
                                                    docRelacionado.setImpPagado(new BigDecimal(mapP.getNamedItem("ImpPagado").getNodeValue()));
                                                }
                                                System.out.println("mapP.getNamedItem(ImpSaldoAnt):" + mapP.getNamedItem("ImpSaldoAnt"));
                                                if (mapP.getNamedItem("ImpSaldoAnt") != null) {
                                                    docRelacionado.setImpSaldoAnt(new BigDecimal(mapP.getNamedItem("ImpSaldoAnt").getNodeValue()));
                                                }

                                                if (mapP.getNamedItem("ImpSaldoInsoluto") != null) {
                                                    docRelacionado.setImpSaldoInsoluto(new BigDecimal(mapP.getNamedItem("ImpSaldoInsoluto").getNodeValue()));
                                                }
                                                if (mapP.getNamedItem("MetodoDePagoDR") != null) {
                                                    docRelacionado.setMetodoPagoDr(mapP.getNamedItem("MetodoDePagoDR").getNodeValue());
                                                }
                                                docRelacionado.setMonedaDr(mapP.getNamedItem("MonedaDR").getNodeValue());

                                                if (mapP.getNamedItem("NumParcialidad") != null) {

                                                    docRelacionado.setNumParcialidad(Integer.parseInt(mapP.getNamedItem("NumParcialidad").getNodeValue()));

                                                }
                                                if (mapP.getNamedItem("Serie") != null) {
                                                    docRelacionado.setSerie(mapP.getNamedItem("Serie").getNodeValue());
                                                }

                                                if (map.getNamedItem("TipoCambioDR") == null) {

                                                    docRelacionado.setTipoCambioDr(new BigDecimal(1));

                                                } else {

                                                    docRelacionado.setTipoCambioDr(new BigDecimal(map.getNamedItem("TipoCambioDR").getNodeValue()));
                                                }

                                                erpFeDocRelacionadoDao.save(docRelacionado);
                                            }

                                            if (im.getChildNodes().item(w).getChildNodes().item(z).getNodeName().equalsIgnoreCase("pago20:Impuestos")) {

                                                System.out.println("-------Impuestos Complemento---------");
                                                System.out.println(mapP);

                                                impComplId.setCompania(id.getCompania());
                                                impComplId.setNumero(id.getNumero());
                                                impComplId.setIdPago(pagoSec);
                                                impCompl.setId(impComplId);

                                                if (mapP.getNamedItem("TotalImpuestosRetenidos") != null) {

                                                    impCompl.setTotImpRetenidos(new BigDecimal(mapP.getNamedItem("TotalImpuestosRetenidos").getNodeValue()));

                                                }

                                                if (mapP.getNamedItem("TotalImpuestosTrasladados") != null) {

                                                    impCompl.setTotImpRetenidos(new BigDecimal(mapP.getNamedItem("TotalImpuestosTrasladados").getNodeValue()));

                                                }

                                                erpFeImpuestoComplDao.save(impCompl);

                                                for (int a = 0; im.getChildNodes().item(w).getChildNodes().item(z).getChildNodes().getLength() - 1 >= a; a++) {

                                                    org.apache.xerces.dom.AttributeMap mapA = (AttributeMap) im.getChildNodes().item(w).getChildNodes().item(z).getChildNodes().item(a).getAttributes();

                                                    if (im.getChildNodes().item(w).getChildNodes().item(z).getChildNodes().item(a).getNodeName().equalsIgnoreCase("pago20:Retenciones")) {

                                                        for (int b = 0; im.getChildNodes().item(w).getChildNodes().item(z).getChildNodes().item(a).getChildNodes().getLength() - 1 >= b; b++) {

                                                            org.apache.xerces.dom.AttributeMap mapB = (AttributeMap) im.getChildNodes().item(w).getChildNodes().item(z).getChildNodes().item(a).getChildNodes().item(b).getAttributes();

                                                            if (mapB != null) {

                                                                retComplId.setCompania(id.getCompania());
                                                                retComplId.setNumero(id.getNumero());
                                                                retComplId.setIdPago(pagoSec);

                                                                erpFeRetencionComplDao.getMAxIdErpFeRetencionCompl(retComplId);

                                                                retCompl.setId(retComplId);
                                                                retCompl.setImporte(new BigDecimal(mapP.getNamedItem("Importe").getNodeValue()));
                                                                retCompl.setImpuesto(mapP.getNamedItem("Impuesto").getNodeValue());

                                                                erpFeRetencionComplDao.save(retCompl);

                                                            }
                                                        }

                                                    }

                                                    if (im.getChildNodes().item(w).getChildNodes().item(z).getChildNodes().item(a).getNodeName().equalsIgnoreCase("pago20:Traslados")) {

                                                        for (int b = 0; im.getChildNodes().item(w).getChildNodes().item(z).getChildNodes().item(a).getChildNodes().getLength() - 1 >= b; b++) {

                                                            org.apache.xerces.dom.AttributeMap mapB = (AttributeMap) im.getChildNodes().item(w).getChildNodes().item(z).getChildNodes().item(a).getChildNodes().item(b).getAttributes();

                                                            if (mapB != null) {

                                                                trasComplId.setCompania(id.getCompania());
                                                                trasComplId.setNumero(id.getNumero());
                                                                trasComplId.setIdPago(pagoSec);

                                                                int trasImpId = erpFeTrasladoComplDao.getMaxIdErpFeTrasladoCompl(trasComplId);
                                                                trasComplId.setId(trasImpId);
                                                                trasCompl.setId(trasComplId);

                                                                trasCompl.setImpuesto(mapP.getNamedItem("Impuesto").getNodeValue());
                                                                trasCompl.setTipoFactor(mapP.getNamedItem("TipoFactor").getNodeValue());
                                                                trasCompl.setImporte(new BigDecimal(mapP.getNamedItem("Importe").getNodeValue()));
                                                                trasCompl.setTasaCuota(new BigDecimal(mapP.getNamedItem("TasaOCuota").getNodeValue()));

                                                                erpFeTrasladoComplDao.save(trasCompl);

                                                            }
                                                        }
                                                    }

                                                }

                                            }

                                        }

                                    }

                                }

                            }
                        }
                    }
                }

            }

            log.info("-----ERP_NOMINA e impuestos locales-------------");

            if (comp.getComplemento().getAny().iterator().hasNext()) {
                for (int j = 1; comp.getComplemento().getAny().size() >= j; j++) {

                    System.out.println(comp.getComplemento().getAny().get(j - 1).getClass());
                    System.out.println(comp.getComplemento().getAny().get(j - 1));

                    org.apache.xerces.dom.ElementNSImpl im = (ElementNSImpl) comp.getComplemento().getAny().get(j - 1);

                    System.out.println(im);
                    System.out.println(im.getClass());
                    System.out.println(im.getAttributes().getLength());
                    System.out.println(im.getSchemaTypeInfo().getClass());
                    System.out.println(im.getNodeName());

                    log.info("-------ERP IMPUESTOS LOCALES-----------");

                    if (im.getNodeName().equalsIgnoreCase("implocal:ImpuestosLocales")) {

                        ErpFeImpLocales loc = new ErpFeImpLocales();
                        ErpFeImpLocalesId locid = new ErpFeImpLocalesId();

                        locid.setCompania(compania);
                        locid.setNumero(id.getNumero());
                        loc.setId(locid);
                        loc.setTotalRetenciones(new BigDecimal(im.getAttribute("TotaldeRetenciones")));
                        loc.setTotalTraslados(new BigDecimal(im.getAttribute("TotaldeTraslados")));
                        loc.setVersion(im.getAttribute("version"));

                        erpFeImpLocalesDao.save(loc);

                        if (im.getChildNodes().getLength() > 0) {
                            int sect = 0;
                            System.out.println(im.getChildNodes().getLength());
                            for (int w = 0; im.getChildNodes().getLength() - 1 >= w; w++) {

                                sect = sect + 1;
                                System.out.println(im.getChildNodes().item(w));
                                System.out.println(im.getChildNodes().item(w).getNodeName());

                                org.apache.xerces.dom.AttributeMap map = (AttributeMap) im.getChildNodes().item(w).getAttributes();
                                if (im.getChildNodes().item(w).getNodeName().equalsIgnoreCase("implocal:TrasladosLocales")) {
                                    ErpFeTrasLocales tLoc = new ErpFeTrasLocales();
                                    ErpFeTrasLocalesId tLocId = new ErpFeTrasLocalesId();
                                    tLocId.setCompania(compania);
                                    tLocId.setNumero(id.getNumero());
                                    tLocId.setSec(sect);
                                    tLoc.setId(tLocId);
                                    tLoc.setImpLocTraslado(map.getNamedItem("ImpLocTrasladado").getNodeValue());
                                    tLoc.setImporte(new BigDecimal(map.getNamedItem("Importe").getNodeValue()));
                                    tLoc.setTasaTraslado(map.getNamedItem("TasadeTraslado").getNodeValue());
                                    erpFeTrasLocalesDao.save(tLoc);
                                }
                            }
                        }

                        log.info("----Encontrado impuesto local---------");

                    }

                    if (im.getNodeName().equalsIgnoreCase("nomina12:Nomina")) {
                        System.out.println("---------------");

                        ErpFeNomina nom = new ErpFeNomina();
                        ErpFeNominaId nomId = new ErpFeNominaId();
                        ErpFeNominaEmisor nomE = new ErpFeNominaEmisor();
                        ErpFeNominaEmisorId nomEId = new ErpFeNominaEmisorId();
                        ErpFeNominaReceptor nomR = new ErpFeNominaReceptor();
                        ErpFeNominaReceptorId nomRId = new ErpFeNominaReceptorId();
                        ErpFePercepciones pers = new ErpFePercepciones();
                        ErpFePercepcionesId persId = new ErpFePercepcionesId();
                        ErpFeDeducciones deds = new ErpFeDeducciones();
                        ErpFeDeduccionesId dedsId = new ErpFeDeduccionesId();
                        ErpFePercepcion per = new ErpFePercepcion();
                        ErpFePercepcionId perId = new ErpFePercepcionId();
                        ErpFeDeduccion ded = new ErpFeDeduccion();
                        ErpFeDeduccionId dedId = new ErpFeDeduccionId();
                        ErpFeOtroPagoId otroPagoId = new ErpFeOtroPagoId();
                        ErpFeOtroPago otroPago = new ErpFeOtroPago();

                        nomId.setCompania(id.getCompania());
                        nomId.setNumero(id.getNumero());
                        nom.setId(nomId);
                        nom.setTotalDeducciones(new BigDecimal(im.getAttribute("TotalDeducciones")));
                        nom.setTotalPercepciones(new BigDecimal(im.getAttribute("TotalPercepciones")));
                        nom.setNumDiasPagados(im.getAttribute("NumDiasPagados"));
                        nom.setFechaFinalPago(im.getAttribute("FechaFinalPago"));
                        nom.setFechaInicialPago(im.getAttribute("FechaInicialPago"));
                        nom.setFechaPago(im.getAttribute("FechaPago"));
                        nom.setTipoNomina(im.getAttribute("TipoNomina"));
                        nom.setVersion(im.getAttribute("Version"));

                        erpFeNominaDao.save(nom);

                        if (im.getChildNodes().getLength() > 0) {

                            System.out.println(im.getChildNodes().getLength());
                            for (int w = 0; im.getChildNodes().getLength() - 1 >= w; w++) {

                                System.out.println(im.getChildNodes().item(w));
                                System.out.println(im.getChildNodes().item(w).getNodeName());

                                org.apache.xerces.dom.AttributeMap map = (AttributeMap) im.getChildNodes().item(w).getAttributes();
                                if (im.getChildNodes().item(w).getNodeName().equalsIgnoreCase("nomina12:Emisor")) {

                                    nomEId.setCompania(id.getCompania());
                                    nomEId.setNumero(id.getNumero());
                                    nomE.setId(nomEId);
                                    nomE.setRegistroPatronal(map.getNamedItem("RegistroPatronal").getNodeValue());
                                    erpFeNominaEmisorDao.save(nomE);
                                }

                                if (im.getChildNodes().item(w).getNodeName().equalsIgnoreCase("nomina12:Receptor")) {

                                    nomRId.setCompania(id.getCompania());
                                    nomRId.setNumero(id.getNumero());
                                    nomR.setId(nomRId);
                                    nomR.setClaveEntFed(map.getNamedItem("ClaveEntFed").getNodeValue());
                                    nomR.setSalarioDiarioInt(new BigDecimal(map.getNamedItem("SalarioDiarioIntegrado").getNodeValue()));
                                    if (map.getNamedItem("CuentaBancaria") != null) {
                                        nomR.setCuentaBancario(map.getNamedItem("CuentaBancaria").getNodeValue());
                                    }
                                    nomR.setPeriocidadPago(map.getNamedItem("PeriodicidadPago").getNodeValue());
                                    nomR.setRiesgoPuesto(map.getNamedItem("RiesgoPuesto").getNodeValue());
                                    nomR.setPuesto(map.getNamedItem("Puesto").getNodeValue());
                                    nomR.setDepartamento(map.getNamedItem("Departamento").getNodeValue());
                                    nomR.setNumEmpleado(map.getNamedItem("NumEmpleado").getNodeValue());
                                    nomR.setTipoRegimen(map.getNamedItem("TipoRegimen").getNodeValue());

                                    if (map.getNamedItem("TipoJornada") != null) {
                                        nomR.setTipoJornada(map.getNamedItem("TipoJornada").getNodeValue());

                                    }

                                    nomR.setSindicalizado(map.getNamedItem("Sindicalizado").getNodeValue());
                                    nomR.setTipoContrato(map.getNamedItem("TipoContrato").getNodeValue());
                                    nomR.setAntiguedad(map.getNamedItem("Antigüedad").getNodeValue());
                                    nomR.setFechaIniRelLaboral(map.getNamedItem("FechaInicioRelLaboral").getNodeValue());
                                    nomR.setNumSegSocial(map.getNamedItem("NumSeguridadSocial").getNodeValue());
                                    nomR.setCurp(map.getNamedItem("Curp").getNodeValue());

                                    erpFeNominaReceptorDao.save(nomR);

                                }

                                if (im.getChildNodes().item(w).getNodeName().equalsIgnoreCase("nomina12:Percepciones")) {

                                    persId.setCompania(id.getCompania());
                                    persId.setNumero(id.getNumero());
                                    pers.setId(persId);
                                    pers.setTotalExento(new BigDecimal(map.getNamedItem("TotalExento").getNodeValue()));
                                    pers.setTotalGravado(new BigDecimal(map.getNamedItem("TotalGravado").getNodeValue()));
                                    pers.setTotalSueldos(new BigDecimal(map.getNamedItem("TotalSueldos").getNodeValue()));

                                    erpFePercepcionesDao.save(pers);

                                    for (int z = 0; im.getChildNodes().item(w).getChildNodes().getLength() - 1 >= z; z++) {

                                        org.apache.xerces.dom.AttributeMap mapP = (AttributeMap) im.getChildNodes().item(w).getChildNodes().item(z).getAttributes();
                                        System.out.println("-------percepcion---------");

                                        perId.setCompania(id.getCompania());
                                        perId.setNumero(id.getNumero());
                                        int idPer = erpFePercepcionDao.getMaxIdErpFePercepcion(perId);
                                        perId.setId(idPer);
                                        per.setId(perId);
                                        per.setImporteExento(new BigDecimal(mapP.getNamedItem("ImporteExento").getNodeValue()));
                                        per.setImporteGravado(new BigDecimal(mapP.getNamedItem("ImporteGravado").getNodeValue()));
                                        per.setConcepto(mapP.getNamedItem("Concepto").getNodeValue());
                                        per.setClave(mapP.getNamedItem("Clave").getNodeValue());
                                        per.setTipoPercepcion(mapP.getNamedItem("TipoPercepcion").getNodeValue());

                                        erpFePercepcionDao.save(per);
                                    }

                                }

                                if (im.getChildNodes().item(w).getNodeName().equalsIgnoreCase("nomina12:Deducciones")) {

                                    dedsId.setCompania(id.getCompania());
                                    dedsId.setNumero(id.getNumero());
                                    deds.setId(dedsId);
                                    if(map.getNamedItem("TotalOtrasDeducciones") != null){
                                        deds.setTotalOtrasDeduc(new BigDecimal(map.getNamedItem("TotalOtrasDeducciones").getNodeValue()));
                                    }
                                    
                                    if(map.getNamedItem("TotalImpuestosRetenidos") != null){
                                        deds.setTotalImpuestosRetenidos(new BigDecimal(map.getNamedItem("TotalImpuestosRetenidos").getNodeValue()));
                                    }

                                    erpFeDeduccionesDao.save(deds);

                                    for (int z = 0; im.getChildNodes().item(w).getChildNodes().getLength() - 1 >= z; z++) {

                                        org.apache.xerces.dom.AttributeMap mapP = (AttributeMap) im.getChildNodes().item(w).getChildNodes().item(z).getAttributes();
                                        System.out.println("-------deduccion---------");

                                        dedId.setCompania(id.getCompania());
                                        dedId.setNumero(id.getNumero());
                                        int idDeduc = erpFeDeduccionDao.getMaxIdErpFeDeduccion(dedId);
                                        dedId.setId(idDeduc);
                                        ded.setId(dedId);
                                        ded.setConcepto(mapP.getNamedItem("Concepto").getNodeValue());
                                        ded.setImporte(new BigDecimal(mapP.getNamedItem("Importe").getNodeValue()));
                                        ded.setClave(mapP.getNamedItem("Clave").getNodeValue());
                                        ded.setTipoDeduccion(mapP.getNamedItem("TipoDeduccion").getNodeValue());

                                        erpFeDeduccionDao.save(ded);

                                    }

                                }
                                
                                
                                if (im.getChildNodes().item(w).getNodeName().equalsIgnoreCase("nomina12:OtrosPagos")) {

                                   

                                    for (int z = 0; im.getChildNodes().item(w).getChildNodes().getLength() - 1 >= z; z++) {

                                        org.apache.xerces.dom.AttributeMap mapP = (AttributeMap) im.getChildNodes().item(w).getChildNodes().item(z).getAttributes();
                                        System.out.println("-------otros pagos nominas---------");

                                        otroPagoId.setCompania(id.getCompania());
                                        otroPagoId.setNumero(id.getNumero());
                                        int idOtroPago = erpFeOtroPagoDao.getMaxIdErpFeOtroPago(otroPagoId);
                                        otroPagoId.setSec(idOtroPago);
                                        otroPago.setId(otroPagoId);
                                        otroPago.setConcepto(mapP.getNamedItem("Concepto").getNodeValue());
                                        otroPago.setClave(mapP.getNamedItem("Clave").getNodeValue());
                                        otroPago.setImporte(new BigDecimal(mapP.getNamedItem("Importe").getNodeValue()));
                                        otroPago.setTipoOtroPago(mapP.getNamedItem("TipoOtroPago").getNodeValue());
                                       

                                        erpFeOtroPagoDao.save(otroPago);

                                    }

                                }
                                

                            }
                        }
                    }
                }

            }

            log.info("-----ERP_EMISOR-------------");

            ErpFeEmisor emisor = new ErpFeEmisor();
            ErpFeEmisorId idEmisor = new ErpFeEmisorId();
            if (comp.getEmisor() == null) {

                log.info("Sin emisor");

            } else {
                idEmisor.setCompania(id.getCompania());
                idEmisor.setNumero(id.getNumero());
                emisor.setId(idEmisor);
                emisor.setNombre(comp.getEmisor().getNombre());
                emisor.setRfc(comp.getEmisor().getRfc());
                emisor.setRegimenFiscal(comp.getEmisor().getRegimenFiscal());

//                if (comp.getEmisor().getDomicilioFiscal() != null) {
//                emisor.setCalle(comp.getEmisor().getDomicilioFiscal().getCalle());
//                emisor.setCodigoPostal(comp.getEmisor().getDomicilioFiscal().getCodigoPostal());
//                emisor.setColonia(comp.getEmisor().getDomicilioFiscal().getColonia());
//                emisor.setEstado(comp.getEmisor().getDomicilioFiscal().getEstado());
//                emisor.setLocalidad(comp.getEmisor().getDomicilioFiscal().getLocalidad());
//                emisor.setMunicipio(comp.getEmisor().getDomicilioFiscal().getMunicipio());
//                emisor.setNoInterior(comp.getEmisor().getDomicilioFiscal().getNoInterior());
//                emisor.setNoExterior(comp.getEmisor().getDomicilioFiscal().getNoExterior());
//                emisor.setPais(comp.getEmisor().getDomicilioFiscal().getPais());
//                emisor.setReferencia(comp.getEmisor().getDomicilioFiscal().getReferencia());
//                }
                //  log.info("Referencia:" + comp.getEmisor().getDomicilioFiscal().getReferencia());
//                if (comp.getEmisor().getExpedidoEn() != null) {
//                    emisor.setExpColonia(comp.getEmisor().getExpedidoEn().getColonia());
//                    emisor.setExpEstado(comp.getEmisor().getExpedidoEn().getEstado());
//                    emisor.setExpLocalidad(comp.getEmisor().getExpedidoEn().getLocalidad());
//                    emisor.setExpMunicipio(comp.getEmisor().getExpedidoEn().getMunicipio());
//                    emisor.setExpNoInterior(comp.getEmisor().getExpedidoEn().getNoInterior());
//                    emisor.setExpNoExterior(comp.getEmisor().getExpedidoEn().getNoExterior());
//                    emisor.setExpPais(comp.getEmisor().getExpedidoEn().getPais());
//                    emisor.setReferencia(comp.getEmisor().getExpedidoEn().getReferencia());
//                }
                ErpFeEmisorId result = erpFeEmisorDao.save(emisor);
                log.info("saveEmisor:" + result.getNumero());
            }

            log.info("-----CONCEPTOS------------");
            ErpFeConceptosId idC = new ErpFeConceptosId();
            ErpFeConceptos conceptos = new ErpFeConceptos();
            if (comp.getConceptos() == null) {

                log.info("Sin conceptos");

            } else {

                Iterator<com.feraz.mx.sat.cfdi4.Comprobante4.Conceptos.Concepto> it = comp.getConceptos().getConcepto().iterator();
                com.feraz.mx.sat.cfdi4.Comprobante4.Conceptos.Concepto con = null;
                int i = 1;

                while (it.hasNext()) {
                    con = it.next();

                    log.info("conceptos i:" + i);
                    idC.setCompania(id.getCompania());
                    idC.setNumero(id.getNumero());
                    idC.setSec(i);
                    conceptos.setCantidad(con.getCantidad());
                    conceptos.setDescripcion(con.getDescripcion());
                    conceptos.setFolio(comp2.getFolio());
                    conceptos.setImporte(con.getImporte());
                    conceptos.setNoIdentificador(con.getNoIdentificacion());
                    conceptos.setUnidad(con.getUnidad());
                    conceptos.setValorUnitario(con.getValorUnitario());
                    conceptos.setClaveUnidad(con.getClaveUnidad());
                    conceptos.setClaveProdServ(con.getClaveProdServ());

                    System.out.println("buscando descuento por concepto...");

                    System.out.println(con.getDescuento());
                    if (con.getDescuento() == null) {

                        conceptos.setDescuento(new BigDecimal(0));
                    } else {
                        conceptos.setDescuento(con.getDescuento());
                    }

                    conceptos.setId(idC);
                    System.out.println("------------impuestos por concepto-----------");
                    log.info("------------impuestos por concepto-----------");
                    System.out.println(con.getImpuestos());
                    log.info(con.getImpuestos());

                    if (con.getImpuestos() != null) {

                        log.info("Retenciones");
                        System.out.println("Retenciones");
                        System.out.println(con.getImpuestos().getRetenciones());

                        log.info("Traslados");
                        System.out.println("Traslados");
                        System.out.println(con.getImpuestos().getTraslados());

                        if (con.getImpuestos().getRetenciones() == null) {

                        } else {
//                        Iterator<com.feraz.mx.sat.cfdi4.Comprobante4.Conceptos.Concepto.Impuestos.Retenciones.Retencion> ConcReten1;
                            Iterator<com.feraz.mx.sat.cfdi4.Comprobante4.Conceptos.Concepto.Impuestos.Retenciones.Retencion> ConcReten = con.getImpuestos().getRetenciones().getRetencion().iterator();

                            ErpFeConceptoXRetencion concXRet = new ErpFeConceptoXRetencion();

                            ErpFeConceptoXRetencionId concXRetId = new ErpFeConceptoXRetencionId();
                            com.feraz.mx.sat.cfdi4.Comprobante4.Conceptos.Concepto.Impuestos.Retenciones.Retencion conctrans = null;

                            int trC = 1;

                            if (ConcReten == null) {

                            } else {
                                while (ConcReten.hasNext()) {
                                    conctrans = ConcReten.next();
                                    //comp.getImpuestos().getTraslados().getTraslado().get(0).getImpuesto();

                                    concXRetId.setCompania(id.getCompania());
                                    concXRetId.setNumero(id.getNumero());
                                    concXRetId.setIdConcepto(i);
                                    concXRetId.setSec(trC);
                                    //ErpImpTraslados trans2 = mapper.convertValue(trans, ErpImpTraslados.class);
                                    concXRet.setBase(conctrans.getBase());
                                    concXRet.setFolio(comp2.getFolio());
                                    concXRet.setImporte(conctrans.getImporte());
                                    concXRet.setImpuesto(conctrans.getImpuesto());
                                    concXRet.setTasaOcuota(conctrans.getTasaOCuota());
                                    concXRet.setTipoFactor(conctrans.getTipoFactor().toString());
                                    concXRet.setId(concXRetId);

                                    ErpFeConceptoXRetencionId resultRetConc = erpFeConceptoXRetencionDao.save(concXRet);

                                    log.info("resultTransConc:" + resultRetConc.getNumero());

                                    trC++;

                                }
                            }

                        }

                        if (con.getImpuestos().getTraslados() == null) {

                        } else {
//                        
                            Iterator<com.feraz.mx.sat.cfdi4.Comprobante4.Conceptos.Concepto.Impuestos.Traslados.Traslado> ConcTrans = con.getImpuestos().getTraslados().getTraslado().iterator();

                            ErpFeConceptoXTraslado concXTrans = new ErpFeConceptoXTraslado();
                            ErpFeConceptoXTrasladoId concXTransId = new ErpFeConceptoXTrasladoId();

                            com.feraz.mx.sat.cfdi4.Comprobante4.Conceptos.Concepto.Impuestos.Traslados.Traslado conctrans2 = null;

                            int trC2 = 1;

                            if (ConcTrans == null) {

                            } else {
                                while (ConcTrans.hasNext()) {
                                    conctrans2 = ConcTrans.next();
                                    //comp.getImpuestos().getTraslados().getTraslado().get(0).getImpuesto();

                                    concXTransId.setCompania(id.getCompania());
                                    concXTransId.setNumero(id.getNumero());
                                    concXTransId.setIdConcepto(i);
                                    concXTransId.setSec(trC2);
                                    //ErpImpTraslados trans2 = mapper.convertValue(trans, ErpImpTraslados.class);
                                    concXTrans.setBase(conctrans2.getBase());
                                    concXTrans.setFolio(comp2.getFolio());
                                    concXTrans.setImporte(conctrans2.getImporte());
                                    concXTrans.setImpuesto(conctrans2.getImpuesto());
                                    concXTrans.setTasaOcuota(conctrans2.getTasaOCuota());
                                    concXTrans.setTipoFactor(conctrans2.getTipoFactor().toString());
                                    concXTrans.setId(concXTransId);

                                    ErpFeConceptoXTrasladoId resultTransConc2 = erpFeConceptoXTrasladoDao.save(concXTrans);

                                    // log.info("resultTransConc:"+resultTransConc2.getNumero());
                                    trC2++;

                                }
                            }

                        }

                    }

                    log.info("Guardando conceptos:" + erpFeConceptosDao.save(conceptos).getSec());
                    i++;

                }
            }
//            log.info("-------------TRANSLADOS----------------");
            if (comp.getImpuestos() != null) {
                if (comp.getImpuestos().getTraslados() == null) {

                    //                log.info("Sin translado");
                } else {

                    Iterator<com.feraz.mx.sat.cfdi4.Comprobante4.Impuestos.Traslados.Traslado> translado = comp.getImpuestos().getTraslados().getTraslado().iterator();
                    ErpFeImpTrasladosId translados = new ErpFeImpTrasladosId();
                    ErpFeImpTraslados trans2 = new ErpFeImpTraslados();
                    com.feraz.mx.sat.cfdi4.Comprobante4.Impuestos.Traslados.Traslado trans = null;
                    int i2 = 1;
                    if (translado == null) {

                    } else {
                        while (translado.hasNext()) {
                            trans = translado.next();
                            //comp.getImpuestos().getTraslados().getTraslado().get(0).getImpuesto();

                            translados.setCompania(id.getCompania());
                            translados.setNumero(id.getNumero());
                            translados.setSec(i2);
                            //ErpImpTraslados trans2 = mapper.convertValue(trans, ErpImpTraslados.class);
                            trans2.setImporte(trans.getImporte());
                            trans2.setImpuesto(trans.getImpuesto());
                            trans2.setTasa(trans.getTasaOCuota());
                            trans2.setTasaOCuota(trans.getTasaOCuota());
                            trans2.setTipoFactor(trans.getTipoFactor().toString());
                            trans2.setId(translados);

                            ErpFeImpTrasladosId resultTrans = erpFeImpTrasladosDao.save(trans2);

                            log.info("resultTrans:" + resultTrans.getNumero());

                            i2++;

                        }
                    }
                }
            }
//            log.info("-------------RECEPTOR----------------");

            ErpFeReceptor receptor = new ErpFeReceptor();
            ErpFeReceptorId receptorId = new ErpFeReceptorId();

            if (comp.getEmisor() == null) {

                log.info("Sin receptor");

            } else {

                receptorId.setCompania(id.getCompania());
                receptorId.setNumero(id.getNumero());
                receptor.setId(receptorId);
                receptor.setNombre(comp.getReceptor().getNombre());
                receptor.setRfc(comp.getReceptor().getRfc());

                receptor.setUsoCfdi(comp.getReceptor().getUsoCFDI().toString());
                receptor.setDomicilioFiscalReceptor(comp.getReceptor().getDomicilioFiscalReceptor());
                receptor.setRegimenFiscalReceptor(comp.getReceptor().getRegimenFiscalReceptor());
                if (comp.getAddenda() != null) {
//                    log.info("ADENA:" + comp.getAddenda().getAny());
//                    log.info("ADENA:" + comp.getAddenda().getAny().size());
//                    log.info("ADENA2:" + comp.getAddenda().getAny().get(0).toString());
                }
//                if (comp.getEmisor().getExpedidoEn() != null) {
//                    receptor.setExpColonia(comp.getEmisor().getExpedidoEn().getColonia());
//                    receptor.setExpEstado(comp.getEmisor().getExpedidoEn().getEstado());
//                    receptor.setExpLocalidad(comp.getEmisor().getExpedidoEn().getLocalidad());
//                    receptor.setExpMunicipio(comp.getEmisor().getExpedidoEn().getMunicipio());
//                    receptor.setExpNoInterior(comp.getEmisor().getExpedidoEn().getNoInterior());
//                    receptor.setExpNoExterior(comp.getEmisor().getExpedidoEn().getNoExterior());
//                    receptor.setExpPais(comp.getEmisor().getExpedidoEn().getPais());
//                    receptor.setExpReferencia(comp.getEmisor().getExpedidoEn().getReferencia());
//                }
                ErpFeReceptorId resultRecep = erpFeReceptorDao.save(receptor);

                log.info("resultRecep:" + resultRecep.getNumero());
            }
            log.info("-------------RETECIONES----------------");
            if (comp.getImpuestos() != null) {
                if (comp.getImpuestos().getRetenciones() == null) {

                    log.info("Sin retenciones");

                } else {

                    //                log.info("Rete:" + comp.getImpuestos().getRetenciones().getRetencion().size());
                    Iterator<com.feraz.mx.sat.cfdi4.Comprobante4.Impuestos.Retenciones.Retencion> retencion = comp.getImpuestos().getRetenciones().getRetencion().iterator();
                    ErpFeImpRetencionesId retId = new ErpFeImpRetencionesId();
                    ErpFeImpRetenciones re = new ErpFeImpRetenciones();
                    com.feraz.mx.sat.cfdi4.Comprobante4.Impuestos.Retenciones.Retencion ret = null;
                    int i3 = 1;

                    while (retencion.hasNext()) {
                        ret = retencion.next();
                        //comp.getImpuestos().getRetenciones().getRetencion().get(0).getImporte()  
                        //                    log.info("datos:"+id.getCompania()+","+id.getNumero()+","+ret.getImporte()+","+ret.getImpuesto());
                        retId.setCompania(id.getCompania());
                        retId.setNumero(id.getNumero());
                        retId.setSec(i3);
                        re.setId(retId);
                        re.setImporte(ret.getImporte());
                        re.setImpuesto(ret.getImpuesto());

                        ErpFeImpRetencionesId resultRet = erpFeImpRetencionesDao.save(re);

                        log.info("resultRetenciones:" + resultRet);

                        i3++;

                    }

                }
            }
            log.info("origen Agregar:" + origen);
            if (origen.equalsIgnoreCase("CP")) {

//                log.info("-------------Preparando la creacion de Proveedor ------------------");
                Map rfcExis = new HashMap();

                rfcExis.put("compania", compania);
                rfcExis.put("rfc", comp.getEmisor().getRfc());

                List listRfc = processDao.getMapResult("BuscaRfcProveedor", rfcExis);

                if (listRfc.isEmpty()) {

                    ErpCClientes clientes = new ErpCClientes();
                    ErpCClientesId clientesId = new ErpCClientesId();

                    DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
                    Date date = new Date();
//                        log.info("Date Proveedor" + dateFormat.format(date));

                    clientesId.setCompania(compania);
                    clientesId.setOrigen("P");

                    Map numRegistros = new HashMap();

                    numRegistros.put("compania", compania);

                    List listRegistros = processDao.getMapResult("BuscaRegistrosPro", numRegistros);

                    Map count = (HashMap) listRegistros.get(0);
                    log.info("NUM_REGISTROS:" + count.get("NUM_REGISTROS"));

                    //log.info("compania" + compania);
                    //String pid = erpCClientesDao.getMaxErpCClientes(clientesId);
                    //log.info("pid" + pid);
                    clientesId.setIdCliente("000" + count.get("NUM_REGISTROS").toString());
                    clientes.setId(clientesId);
                    clientes.setNombre(comp.getEmisor().getNombre());
                    clientes.setRfc(comp.getEmisor().getRfc());
                    clientes.settPersona("M");
                    clientes.settClieprov("PES");
                    clientes.settTercero("04");
                    clientes.settOperacion("85");
                    clientes.setRazonSocial(comp.getEmisor().getNombre());
                    clientes.setfAlta(date);

                    ErpCClientesId result = erpCClientesDao.save(clientes);

                    if (result != null) {

                        errId.setCompania(compania);
                        errId.setId(idErr);

                        errId.setXml(fichero.getName());
                        err.setId(errId);
                        err.setMsg("Guardado Correctamente, Se genero el proveedor");

                    } else {

                        errId.setCompania(compania);
                        errId.setId(idErr);

                        errId.setXml(fichero.getName());
                        err.setId(errId);
                        err.setMsg("Guardado Correctamente, No se pudo generar el proveedor");

                    }
                } else {
                    //log.info("tipo normal fe");
                    errId.setCompania(compania);
                    errId.setId(idErr);

                    errId.setXml(fichero.getName());
                    err.setId(errId);
                    err.setMsg("Guardado Correctamente, El proveedor ya existe");

                }
                erpFeErrDao.update(err);

            }

            if (origen.equalsIgnoreCase("CCP")) {

                Map rfcCompania = new HashMap();

                rfcCompania.put("compania", compania);
                rfcCompania.put("rfc", comp.getEmisor().getRfc());

                List listRfcProveedore = processDao.getMapResult("BuscaExisteRfcCompania", rfcCompania);

                if (listRfcProveedore.isEmpty()) {

                    Map rfcCompaniaClien = new HashMap();

                    rfcCompaniaClien.put("compania", compania);
                    rfcCompaniaClien.put("rfc", comp.getReceptor().getRfc());

                    List listRfcCliente = processDao.getMapResult("BuscaExisteRfcCompania", rfcCompaniaClien);

                    if (!listRfcCliente.isEmpty()) {

                        Map rfcExisClien = new HashMap();

                        rfcExisClien.put("compania", compania);
                        rfcExisClien.put("rfc", comp.getReceptor().getRfc());

                        List listRfcClientes = processDao.getMapResult("BuscaRfcCliente", rfcExisClien);

                        if (listRfcClientes.isEmpty()) {

                            if (comp.getReceptor().getRfc().length() == 12) {

                                ErpClientes cliente = new ErpClientes();
                                ErpClientesId clienteId = new ErpClientesId();

                                DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
                                Date date = new Date();
                                //                        log.info("Date Proveedor" + dateFormat.format(date));

                                clienteId.setCompania(compania);
                                clienteId.setOrigen("C");

                                Map numRegistrosProvClien = new HashMap();

                                numRegistrosProvClien.put("compania", compania);

                                List listRegistrosProvClien = processDao.getMapResult("BuscaRegistrosCli", numRegistrosProvClien);

                                Map countClien = (HashMap) listRegistrosProvClien.get(0);
                                log.info("NUM_REGISTROS:" + countClien.get("NUM_REGISTROS"));

                                clienteId.setIdCliente("000" + countClien.get("NUM_REGISTROS").toString());
                                cliente.setId(clienteId);
                                cliente.setNombre(comp.getReceptor().getNombre());
                                cliente.setRfc(comp.getReceptor().getRfc());
                                cliente.setRazonSocial(comp.getReceptor().getNombre());
                                cliente.setfAlta(date);

                                ErpClientesId result = erpClientesDao.save(cliente);

                                if (result != null) {

                                    errId.setCompania(compania);
                                    errId.setId(idErr);

                                    errId.setXml(fichero.getName());
                                    err.setId(errId);
                                    err.setMsg("Guardado Correctamente, Se genero el cliente");

                                } else {

                                    errId.setCompania(compania);
                                    errId.setId(idErr);

                                    errId.setXml(fichero.getName());
                                    err.setId(errId);
                                    err.setMsg("Guardado Correctamente, No se pudo generar el cliente");

                                }
                            } else {

                                errId.setCompania(compania);
                                errId.setId(idErr);

                                errId.setXml(fichero.getName());
                                err.setId(errId);
                                err.setMsg("Guardado Correctamente, No guardo cliente. Rfc de persona Fisica");

                            }
                        } else {
                            //log.info("tipo normal fe");
                            errId.setCompania(compania);
                            errId.setId(idErr);

                            errId.setXml(fichero.getName());
                            err.setId(errId);
                            err.setMsg("Guardado Correctamente, El cliente ya existe");

                        }
                        erpFeErrDao.update(err);

                    }

                } else {

                    Map rfcExisProv = new HashMap();

                    rfcExisProv.put("compania", compania);
                    rfcExisProv.put("rfc", comp.getEmisor().getRfc());

                    List listRfcProv = processDao.getMapResult("BuscaRfcProveedor", rfcExisProv);

                    if (listRfcProv.isEmpty()) {

                        if (comp.getEmisor().getRfc().length() == 12) {

                            ErpCClientes proveedor = new ErpCClientes();
                            ErpCClientesId proveedorId = new ErpCClientesId();

                            DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
                            Date date = new Date();
                            //                        log.info("Date Proveedor" + dateFormat.format(date));

                            proveedorId.setCompania(compania);
                            proveedorId.setOrigen("P");

                            Map numRegistrosProv = new HashMap();

                            numRegistrosProv.put("compania", compania);

                            List listRegistrosProv = processDao.getMapResult("BuscaRegistrosPro", numRegistrosProv);

                            Map count = (HashMap) listRegistrosProv.get(0);
                            log.info("NUM_REGISTROS:" + count.get("NUM_REGISTROS"));

                            proveedorId.setIdCliente("000" + count.get("NUM_REGISTROS").toString());
                            proveedor.setId(proveedorId);
                            proveedor.setNombre(comp.getEmisor().getNombre());
                            proveedor.setRfc(comp.getEmisor().getRfc());
                            proveedor.settPersona("M");
                            proveedor.settClieprov("PES");
                            proveedor.settTercero("04");
                            proveedor.settOperacion("85");
                            proveedor.setRazonSocial(comp.getEmisor().getNombre());
                            proveedor.setfAlta(date);

                            ErpCClientesId result = erpCClientesDao.save(proveedor);

                            if (result != null) {

                                errId.setCompania(compania);
                                errId.setId(idErr);

                                errId.setXml(fichero.getName());
                                err.setId(errId);
                                err.setMsg("Guardado Correctamente, Se genero el proveedor");

                            } else {

                                errId.setCompania(compania);
                                errId.setId(idErr);

                                errId.setXml(fichero.getName());
                                err.setId(errId);
                                err.setMsg("Guardado Correctamente, No se pudo generar el proveedor");

                            }

                        } else {

                            errId.setCompania(compania);
                            errId.setId(idErr);

                            errId.setXml(fichero.getName());
                            err.setId(errId);
                            err.setMsg("Guardado Correctamente, No guardo proveedor. Rfc de persona Fisica");

                        }
                    } else {
                        //log.info("tipo normal fe");
                        errId.setCompania(compania);
                        errId.setId(idErr);

                        errId.setXml(fichero.getName());
                        err.setId(errId);
                        err.setMsg("Guardado Correctamente, El proveedor ya existe");

                    }
                    erpFeErrDao.update(err);

                }

            }

//            log.info("idErr:"+idErr);
            errId.setCompania(compania);
            errId.setId(idErr);

            errId.setXml(fichero.getName());
            err.setId(errId);
            err.setMsg("Guardado Correctamente");
            log.info("errId:" + errId.getId());

            if (errId.getId() != null) {
                erpFeErrDao.save(err);
            }
//            log.info("TERMINO");

        } catch (Exception e) {
            log.error("Error a cargar el CFDI", e);
            return 0;
        }

        // instantiate our spring dao object from the application context
        // FileEventDao fileEventDao = (FileEventDao)ctx.getBean("fileEventDao");
        //CFDv22 cfd = new CFDv22(comp);
        return num;

    }
    
    
    
    
    public void setErpFeComprobantesDao(ErpFeComprobantesDao erpFeComprobantesDao) {
        this.erpFeComprobantesDao = erpFeComprobantesDao;
    }

    public void setErpFeEmisorDao(ErpFeEmisorDao erpFeEmisorDao) {
        this.erpFeEmisorDao = erpFeEmisorDao;
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

    public void setId(String idErr) {
        this.idErr = idErr;
    }

    public void setErpFeErrDao(ErpFeErrDao erpFeErrDao) {
        this.erpFeErrDao = erpFeErrDao;
    }

    public void setErpPolizasXFacturasDao(ErpPolizasXFacturasDao erpPolizasXFacturasDao) {
        this.erpPolizasXFacturasDao = erpPolizasXFacturasDao;
    }

    public String getNombrePdf() {
        return nombrePdf;
    }

    public String getPathPdf() {
        return pathPdf;
    }

    public void setNombrePdf(String nombrePdf) {
        this.nombrePdf = nombrePdf;
    }

    public void setPathPdf(String pathPdf) {
        this.pathPdf = pathPdf;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public void setTipoCarga(String tipoCarga) {
        this.tipoCarga = tipoCarga;
    }

    public void setErpCClientesDao(ErpCClientesDao erpCClientesDao) {
        this.erpCClientesDao = erpCClientesDao;
    }

    public ProcessDao getProcessDao() {
        return processDao;
    }

    public void setProcessDao(ProcessDao processDao) {
        this.processDao = processDao;
    }

    public void setErpClientesDao(ErpClientesDao erpClientesDao) {
        this.erpClientesDao = erpClientesDao;
    }

    public void setErpFeConceptoXRetencionDao(ErpFeConceptoXRetencionDao erpFeConceptoXRetencionDao) {
        this.erpFeConceptoXRetencionDao = erpFeConceptoXRetencionDao;
    }

    public void setErpFeConceptoXTrasladoDao(ErpFeConceptoXTrasladoDao erpFeConceptoXTrasladoDao) {
        this.erpFeConceptoXTrasladoDao = erpFeConceptoXTrasladoDao;
    }

    public void setErpFeNominaDao(ErpFeNominaDao erpFeNominaDao) {
        this.erpFeNominaDao = erpFeNominaDao;
    }

    public void setErpFeNominaEmisorDao(ErpFeNominaEmisorDao erpFeNominaEmisorDao) {
        this.erpFeNominaEmisorDao = erpFeNominaEmisorDao;
    }

    public void setErpFeNominaReceptorDao(ErpFeNominaReceptorDao erpFeNominaReceptorDao) {
        this.erpFeNominaReceptorDao = erpFeNominaReceptorDao;
    }

    public void setErpFePercepcionesDao(ErpFePercepcionesDao erpFePercepcionesDao) {
        this.erpFePercepcionesDao = erpFePercepcionesDao;
    }

    public void setErpFeDeduccionesDao(ErpFeDeduccionesDao erpFeDeduccionesDao) {
        this.erpFeDeduccionesDao = erpFeDeduccionesDao;
    }

    public void setErpFePercepcionDao(ErpFePercepcionDao erpFePercepcionDao) {
        this.erpFePercepcionDao = erpFePercepcionDao;
    }

    public void setErpFeDeduccionDao(ErpFeDeduccionDao erpFeDeduccionDao) {
        this.erpFeDeduccionDao = erpFeDeduccionDao;
    }

    public void setErpFePagosDao(ErpFePagosDao erpFePagosDao) {
        this.erpFePagosDao = erpFePagosDao;
    }

    public void setErpFeDocRelacionadoDao(ErpFeDocRelacionadoDao erpFeDocRelacionadoDao) {
        this.erpFeDocRelacionadoDao = erpFeDocRelacionadoDao;
    }

    public void setErpFeImpuestoComplDao(ErpFeImpuestoComplDao erpFeImpuestoComplDao) {
        this.erpFeImpuestoComplDao = erpFeImpuestoComplDao;
    }

    public void setErpFeRetencionComplDao(ErpFeRetencionComplDao erpFeRetencionComplDao) {
        this.erpFeRetencionComplDao = erpFeRetencionComplDao;
    }

    public void setErpFeTrasladoComplDao(ErpFeTrasladoComplDao erpFeTrasladoComplDao) {
        this.erpFeTrasladoComplDao = erpFeTrasladoComplDao;
    }

    public void setErpFeImpLocalesDao(ErpFeImpLocalesDao erpFeImpLocalesDao) {
        this.erpFeImpLocalesDao = erpFeImpLocalesDao;
    }

    public void setErpFeTrasLocalesDao(ErpFeTrasLocalesDao erpFeTrasLocalesDao) {
        this.erpFeTrasLocalesDao = erpFeTrasLocalesDao;
    }

    public void setErpFeOtroPagoDao(ErpFeOtroPagoDao erpFeOtroPagoDao) {
        this.erpFeOtroPagoDao = erpFeOtroPagoDao;
    }
    
    

}
