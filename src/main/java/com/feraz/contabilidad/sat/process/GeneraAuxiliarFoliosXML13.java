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
import mx.gob.sat.esquemas.contabilidadE.x13.auxiliarFolios.RepAuxFolDocument;
import mx.gob.sat.esquemas.contabilidadE.x13.auxiliarFolios.RepAuxFolDocument.RepAuxFol.Mes;
import mx.gob.sat.esquemas.contabilidadE.x13.auxiliarFolios.RepAuxFolDocument.RepAuxFol.DetAuxFol;
import mx.gob.sat.esquemas.contabilidadE.x13.auxiliarFolios.RepAuxFolDocument.RepAuxFol.DetAuxFol.ComprExt;
import mx.gob.sat.esquemas.contabilidadE.x13.auxiliarFolios.RepAuxFolDocument.RepAuxFol.DetAuxFol.ComprNal;
import mx.gob.sat.esquemas.contabilidadE.x13.auxiliarFolios.RepAuxFolDocument.RepAuxFol.DetAuxFol.ComprNalOtr;
import mx.gob.sat.esquemas.contabilidadE.x13.catalogosParaEsqContE.CMoneda;
import mx.gob.sat.esquemas.contabilidadE.x13.catalogosParaEsqContE.CMetPagos;

import com.google.common.base.Charsets;
import com.google.common.io.Files;
import javax.xml.namespace.QName;

import java.io.File;
import java.io.FileOutputStream;
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
import javax.xml.XMLConstants;
import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;
import org.apache.log4j.Logger;
import org.apache.xmlbeans.XmlCalendar;
import org.apache.xmlbeans.XmlCursor;
import org.apache.xmlbeans.XmlOptions;
import org.jamh.data.process.ProcessDao;
import org.springframework.beans.factory.annotation.Value;

/**
 *
 * @author vavi
 */
public class GeneraAuxiliarFoliosXML13 {
    
    private static final Logger log = Logger.getLogger(GeneraAuxiliarFoliosXML11.class);

    @Value("${document.file.dirXMLSAT}")
    private String dirOut;
    private ProcessDao processDao;
    
     public GeneraAuxiliarFoliosXML13() {
    }
     
     public String procesa2( ErpSatRepAuxFol erpSatRepAuxFol, List<ErpSatRepAuxFolDet> lista, String name) {
         
         RepAuxFolDocument repAuxFolDocument = RepAuxFolDocument.Factory.newInstance(); //Documento
        RepAuxFolDocument.RepAuxFol repAuxFol = repAuxFolDocument.addNewRepAuxFol();//Comprobante
        
          XmlCursor cursorCatalogo = repAuxFol.newCursor();
         
         
             cursorCatalogo.setAttributeText(
				new QName(XMLConstants.W3C_XML_SCHEMA_INSTANCE_NS_URI,
						"schemaLocation"),
				"http://www.sat.gob.mx/esquemas/ContabilidadE/1_3/AuxiliarFolios http://www.sat.gob.mx/esquemas/ContabilidadE/1_3/AuxiliarFolios/AuxiliarFolios_1_3.xsd");
     //         javax.xml.namespace.QName qname = cursorBalanza.getName();
         
       
         
      
        repAuxFol.setAnio(Integer.parseInt(erpSatRepAuxFol.getAnio()));
        
         int mes = Integer.parseInt(erpSatRepAuxFol.getMes());
        
         switch (mes){
      
          case 1:
              
               repAuxFol.setMes(Mes.X_01);
          
          break;
              
          case 2:
              
               repAuxFol.setMes(Mes.X_02);
          
          break;
          
          case 3:
              
              repAuxFol.setMes(Mes.X_03);
          
          break;
              
          case 4:
              
              repAuxFol.setMes(Mes.X_04);
          
          break;
          
          case 5:
              
              repAuxFol.setMes(Mes.X_05);
          
          break;
          
          case 6:
              
              repAuxFol.setMes(Mes.X_06);
          
          break;
         
          case 7:
              
              repAuxFol.setMes(Mes.X_07);
          
          break;
          
          case 8:
              
              repAuxFol.setMes(Mes.X_08);
          
          break;
              
          case 9:
              
              repAuxFol.setMes(Mes.X_09);
          
          break;
              
          case 10:
              
              repAuxFol.setMes(Mes.X_10);
          
          break;
              
              
          case 11:
              
              repAuxFol.setMes(Mes.X_11);
          
          break;
              
          case 12:
              
              repAuxFol.setMes(Mes.X_12);
          
          break;
          
          case 13:
              
              repAuxFol.setMes(Mes.X_12);
          
          break;
              
      }
        
      //  repAuxFol.setMes(erpSatRepAuxFol.getMes());
        repAuxFol.setRFC(erpSatRepAuxFol.getRfc());
        repAuxFol.setVersion("1.3");
        repAuxFol.setTipoSolicitud(erpSatRepAuxFol.getTipoSolicitud());
        //repAuxFol.setNumOrden("ABC1212345/12");
        //repAuxFol.setNumTramite("0123456789");
        
        if (erpSatRepAuxFol.getTipoSolicitud().equalsIgnoreCase("AF")){
            repAuxFol.setNumOrden(erpSatRepAuxFol.getNumOrden());
        }
        
            
        if (erpSatRepAuxFol.getTipoSolicitud().equalsIgnoreCase("FC")){
            repAuxFol.setNumOrden(erpSatRepAuxFol.getNumOrden());
        }
        
        if (erpSatRepAuxFol.getTipoSolicitud().equalsIgnoreCase("DE")){
            repAuxFol.setNumTramite(erpSatRepAuxFol.getNumTramite());
        }
        
        if (erpSatRepAuxFol.getTipoSolicitud().equalsIgnoreCase("CO")){
            repAuxFol.setNumTramite(erpSatRepAuxFol.getNumTramite());
        }
        
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
        
            int i = 0;

            while (it.hasNext()) {
                
             
                
                
               DetAuxFol det = repAuxFol.addNewDetAuxFol();
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

              //  XMLGregorianCalendar xmlCalendar = datatypeFactory.newXMLGregorianCalendar(calendar);
                
         //       Calendar  holiday = new XmlCalendar("2004-07-04");
              Calendar  holiday =  new XmlCalendar();
              
              holiday.set(parsedCalendar.get(Calendar.YEAR),
                        parsedCalendar.get(Calendar.MONTH),
                        parsedCalendar.get(Calendar.DATE));
                

                
                
                det.setFecha(holiday);
                
                
            

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
                    
                            ComprNal compNal = det.addNewComprNal();
                            compNal.setUUIDCFDI(ss.uuidCfdi);
                            if(ss.moneda == ""){
                               compNal.setMoneda(CMoneda.Enum.forString("MXN"));
                            }else{
                            
                                compNal.setMoneda(CMoneda.Enum.forString(ss.moneda));
                                
                            }
                            
                            compNal.setMontoTotal(new BigDecimal(ss.montoTotal));
                            compNal.setRFC(ss.rfc);
                            compNal.setTipCamb(new BigDecimal(ss.tipCamb));
                            compNal.setMetPagoAux(CMetPagos.Enum.forString(ss.metPagoAux));

                           // System.out.println("Agregando COMPNAL"+ tr.getNumCta());
                            
                           det.setComprNalArray(0, compNal);
                        }
                        if (ss.tipo.equalsIgnoreCase("CompNalOtr")){
                            ComprNalOtr compNalOtr = det.addNewComprNalOtr();
                            compNalOtr.setCFDCBBNumFol(new BigInteger(ss.cfdCbbNumFol));
                            compNalOtr.setCFDCBBSerie(ss.cfdCbbSerie);
                            compNalOtr.setMoneda(CMoneda.Enum.forString(ss.moneda));
                            compNalOtr.setMontoTotal(new BigDecimal(ss.montoTotal));
                            compNalOtr.setRFC(ss.rfc);
                            compNalOtr.setTipCamb(new BigDecimal(ss.tipCamb));
                            compNalOtr.setMetPagoAux(CMetPagos.Enum.forString(ss.metPagoAux));

                            det.setComprNalOtrArray(0, compNalOtr);
                        }

                        if (ss.tipo.equalsIgnoreCase("CompExt")){

                            ComprExt compExt = det.addNewComprExt();
                            //System.out.println("CMoneda.AED.toString()"+mx.bigdata.sat.ce_polizas_periodo.schema.CMoneda.AED.toString());
                            compExt.setMoneda(CMoneda.Enum.forString(ss.moneda));
                            compExt.setMontoTotal(new BigDecimal(ss.montoTotal));
                            compExt.setNumFactExt(ss.numFactExt);
                            compExt.setTaxID(ss.taxId);
                            compExt.setTipCamb(new BigDecimal(ss.tipCamb));
                            compExt.setMetPagoAux(CMetPagos.Enum.forString(ss.metPagoAux));


                            det.setComprExtArray(0, compExt);
                        }

                    }
                    
                     repAuxFol.setDetAuxFolArray(i, det);
                     
                     i = i + 1;
                    
              }   
                
                
                    
            }
        
           
      String dir =dirOut+name+".xml";
       
        
        XmlOptions xmlOptions = new XmlOptions();
        xmlOptions.setCharacterEncoding("UTF-8");
        xmlOptions.setSavePrettyPrint();
        xmlOptions.setSavePrettyPrintIndent(4);
       String xmlStr = repAuxFolDocument.xmlText(xmlOptions);
       
       System.out.println(xmlStr);
       
     
             FileOutputStream out = new FileOutputStream(dir);
             repAuxFolDocument.save(out);
              out.close();
       
     
          
       
            val = Files.toString(new File(dir), Charsets.UTF_8);
       //catalogo = catalogoCuentas.
       }
        catch(Exception e){
         //  System.out.println("val:"+e.getMessage());
          // e.printStackTrace();
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
