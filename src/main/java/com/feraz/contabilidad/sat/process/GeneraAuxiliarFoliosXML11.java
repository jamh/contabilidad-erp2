/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.feraz.contabilidad.sat.process;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.feraz.contabilidad.sat.electronica.dto.TransaccionDTO;
import com.feraz.contabilidad.sat.electronica.model.ErpSatRepAuxFol;
import com.feraz.contabilidad.sat.electronica.model.ErpSatRepAuxFolDet;
import com.feraz.sat.contabilidad_electronica.RepAuxFolv11;
import com.feraz.sat.contabilidad_electronica.auxiliar.folios.CMoneda;
import com.google.common.base.Charsets;
import com.google.common.io.Files;
import java.io.File;
import java.io.FileOutputStream;
import com.feraz.sat.contabilidad_electronica.auxiliar.folios.ObjectFactory;
import com.feraz.sat.contabilidad_electronica.auxiliar.folios.RepAuxFol;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;
import org.apache.log4j.Logger;
import org.jamh.data.process.ProcessDao;
import org.springframework.beans.factory.annotation.Value;

/**
 *
 * @author Administrador
 */
public class GeneraAuxiliarFoliosXML11 {
    
    private static final Logger log = Logger.getLogger(GeneraAuxiliarFoliosXML11.class);

    @Value("${document.file.dirXMLSAT}")
    private String dirOut;
    private ProcessDao processDao;

    public GeneraAuxiliarFoliosXML11() {
    }

    public String procesa2( ErpSatRepAuxFol erpSatRepAuxFol, List<ErpSatRepAuxFolDet> lista, String name) {
        ObjectFactory of = new ObjectFactory();
        RepAuxFol repAuxFol = of.createRepAuxFol();
        // System.out.println("erpSatRepAuxFol:"+ erpSatRepAuxFol.getAnio());
        //   System.out.println("erpSatRepAuxFol.getRfc():"+ erpSatRepAuxFol.getRfc());
        repAuxFol.setAnio(Integer.parseInt(erpSatRepAuxFol.getAnio()));
        repAuxFol.setMes(erpSatRepAuxFol.getMes());
        repAuxFol.setRFC(erpSatRepAuxFol.getRfc());
        repAuxFol.setVersion(erpSatRepAuxFol.getVersiones());
        repAuxFol.setTipoSolicitud(erpSatRepAuxFol.getTipoSolicitud());
        //repAuxFol.setNumOrden("ABC1212345/12");
        //repAuxFol.setNumTramite("0123456789");
        repAuxFol.setNumOrden(erpSatRepAuxFol.getNumOrden());
        repAuxFol.setNumTramite(erpSatRepAuxFol.getNumTramite());
       String val = null;
        

      log.debug("Generando auxiliar de folios");


        try {
           //  List<RepAuxFol.DetAuxFol> detAuxFol= new ArrayList<RepAuxFol.DetAuxFol>();
            Iterator<ErpSatRepAuxFolDet> it = lista.iterator();
          
            
            // Polizas.Poliza.Transaccion tr = of.createPolizasPolizaTransaccion();
                     //poliza.getPoliza().get(0).getTransaccion().add(tr)
            // Polizas.Poliza.Transaccion.CompNal compNal =   of.createPolizasPolizaTransaccionCompNal();
             //   poliza.getPoliza().get(0).getTransaccion() = new ArrayList();
             // GregorianCalendar gregorianCalendar;
            // XMLGregorianCalendar resultFecha = null;
        

            while (it.hasNext()) {
                
             
                
               RepAuxFol.DetAuxFol det = new RepAuxFol.DetAuxFol();
               ErpSatRepAuxFolDet auxFolDet = it.next();
              
               log.debug("Auxiliar folios Detalles");
               log.debug("auxFolDet.getNumUniDenPol(): "+auxFolDet.getNumUniDenPol());
               log.debug("auxFolDet.getFecha()"+auxFolDet.getFecha());
               //System.out.println("Auxiliar folios Detalles");
               //System.out.println("auxFolDet.getNumUniDenPol(): "+auxFolDet.getNumUniDenPol());
               //System.out.println("auxFolDet.getFecha()"+auxFolDet.getFecha());
               
                
                // det.setConcepto("test");
                det.setNumUnIdenPol(auxFolDet.getTipoPol()+auxFolDet.getNumUniDenPol());
        
               DatatypeFactory datatypeFactory = DatatypeFactory.newInstance();
                GregorianCalendar calendar = new GregorianCalendar();

                calendar.clear();

                Calendar parsedCalendar = Calendar.getInstance();

                //System.out.println(pl.getFecha().toString());
                // eg "yyyy-MM-dd"
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                // this may throw ParseException
                Date rawDate = sdf.parse(auxFolDet.getFecha().toString());
                parsedCalendar.setTime(rawDate);

                calendar.set(parsedCalendar.get(Calendar.YEAR),
                        parsedCalendar.get(Calendar.MONTH),
                        parsedCalendar.get(Calendar.DATE));

                XMLGregorianCalendar xmlCalendar = datatypeFactory.newXMLGregorianCalendar(calendar);
                det.setFecha(xmlCalendar);
                
            

                  SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy"); //Formato en que desamos la fecha
                String fechaConFormato = formato.format(rawDate); //Obtenemos la fecha ya con el formato.
                
             
                Map  transacciones = new HashMap();
            
               transacciones.put("compania", auxFolDet.getCompania());
               transacciones.put("numero", auxFolDet.getNumUniDenPol());
               transacciones.put("fecha", fechaConFormato);
               transacciones.put("tipo_poliza", auxFolDet.getTipoPol());
               
               log.debug("Generando QUERY");
               
               
               List buscaTransacciones = processDao.getMapResult("BuscaTransXFolio", transacciones);    
               
               log.debug("Data encontrada");
               
          //    System.out.println("buscaTransacciones.get(0).toString()"+buscaTransacciones.toString());
               
              if (buscaTransacciones.size() > 0){
              String data = buscaTransacciones.toString();
              int index = data.indexOf("[");
                    if (index == -1) {
                        data = "[" + data + "]";
                    }
                    
                    data = data.replace("=", "\":\"");
                    data = data.replace("{", "{\"");
                    data = data.replace("}", "\"}");
                    data = data.replace(", ", "\", \"");
                    data = data.replace("null", "");
                    data = data.replace("}\", \"{", "}, {");
                    data = data.replaceAll("\\p{Cc}", "");
                    
                    
                 ObjectMapper mapper = new ObjectMapper();
                //  System.out.println("buscaTransacciones.get(0).toString()"+data);
                  
                  
                //  System.out.println("Convertiendo...");
                  
                  List<TransaccionDTO> listaTran = mapper.readValue(data,
                    mapper.getTypeFactory().constructCollectionType(List.class,
                            TransaccionDTO.class));
                
                 //   System.out.println("Iterador...");
                  Iterator<TransaccionDTO> itT = listaTran.iterator();
                  
                   log.debug("generando archivo xml");

                    while (itT.hasNext()) {
                     
                      TransaccionDTO ss = itT.next();
                      
                      log.debug("UUID "+ss.uuidCfdi);
                  //    System.out.println("UUID "+ss.uuidCfdi);
                      
                      
//                        compNal.setMetPagoAux(val);
//                        compNal.setMoneda(CMoneda.AFN);
//                        compNal.setMontoTotal(BigDecimal.TEN);
//                        compNal.setRFC("VLH801218232");
//                        compNal.setTipCamb(BigDecimal.ONE);
//                        compNal.setUUIDCFDI("64491328-62FE-4822-82F5-F5EAA187D66B");
//                        det.getComprNal().add(compNal);
                      
                       if (ss.metPagoAux.equalsIgnoreCase("") || ss.metPagoAux == null){
                           
                           
                           ss.metPagoAux = "98";
                       
                       }
                        
                         if (ss.tipo.equalsIgnoreCase("CompNal")){
                    
                            RepAuxFol.DetAuxFol.ComprNal compNal = new RepAuxFol.DetAuxFol.ComprNal();
                            compNal.setUUIDCFDI(ss.uuidCfdi);
                            if(ss.moneda == ""){
                               compNal.setMoneda(CMoneda.valueOf("MXN"));
                            }else{
                            
                                compNal.setMoneda(CMoneda.valueOf(ss.moneda));
                                
                            }
                            
                            compNal.setMontoTotal(new BigDecimal(ss.montoTotal));
                            compNal.setRFC(ss.rfc);
                            compNal.setTipCamb(new BigDecimal(ss.tipCamb));
                            compNal.setMetPagoAux(ss.metPagoAux);

                           // System.out.println("Agregando COMPNAL"+ tr.getNumCta());
                            det.getComprNal().add(compNal);
                           
                        }
                        if (ss.tipo.equalsIgnoreCase("CompNalOtr")){
                             RepAuxFol.DetAuxFol.ComprNalOtr compNalOtr = new RepAuxFol.DetAuxFol.ComprNalOtr();
                            compNalOtr.setCFDCBBNumFol(new BigInteger(ss.cfdCbbNumFol));
                            compNalOtr.setCFDCBBSerie(ss.cfdCbbSerie);
                            compNalOtr.setMoneda(CMoneda.valueOf(ss.moneda));
                            compNalOtr.setMontoTotal(new BigDecimal(ss.montoTotal));
                            compNalOtr.setRFC(ss.rfc);
                            compNalOtr.setTipCamb(new BigDecimal(ss.tipCamb));
                            compNalOtr.setMetPagoAux(ss.metPagoAux);

                            det.getComprNalOtr().add(compNalOtr);
                        }

                        if (ss.tipo.equalsIgnoreCase("CompExt")){

                            RepAuxFol.DetAuxFol.ComprExt compExt = new RepAuxFol.DetAuxFol.ComprExt();
                            //System.out.println("CMoneda.AED.toString()"+mx.bigdata.sat.ce_polizas_periodo.schema.CMoneda.AED.toString());
                            compExt.setMoneda(CMoneda.valueOf(ss.moneda));
                            compExt.setMontoTotal(new BigDecimal(ss.montoTotal));
                            compExt.setNumFactExt(ss.numFactExt);
                            compExt.setTaxID(ss.taxId);
                            compExt.setTipCamb(new BigDecimal(ss.tipCamb));
                            compExt.setMetPagoAux(ss.metPagoAux);


                            det.getComprExt().add(compExt);
                        }

                    }
                    
                     repAuxFol.getDetAuxFol().add(det);
                    
              }   
                
                
                    
            }
        
           

            RepAuxFolv11 auxiliarFolios = new RepAuxFolv11(repAuxFol, "com.feraz.sat.contabilidad_electronica.auxiliar.folios");

        

            String dir = dirOut + name + ".xml";
            FileOutputStream out = new FileOutputStream(dir);
            auxiliarFolios.guardar(out);
            out.close();

            // String text = Files.toString(new File(path), Charsets.UTF_8);
            val = Files.toString(new File(dir), Charsets.UTF_8);
             // System.out.println("val:"+val);
            //catalogo = catalogoCuentas.
        } catch (Exception e) {
         //  System.out.println("val:"+e.getMessage());
            
            log.error("val:"+e.getMessage());
             
            return e.getMessage();
        }

        return val;
    }

    public void setDirOut(String dirOut) {
        this.dirOut = dirOut;
    }

    public void setProcessDao(ProcessDao processDao) {
        this.processDao = processDao;
    }

}
