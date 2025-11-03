/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.feraz.contabilidad.sat.process;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.feraz.contabilidad.sat.electronica.dto.ErpCargaPolizasRelacionDTO;
import com.feraz.contabilidad.sat.electronica.dto.ErpFeCargaCfdiDTO;
import com.feraz.contabilidad.sat.electronica.dto.TransaccionDTO;
import com.feraz.contabilidad.sat.electronica.model.ErpSatRepAuxFol;
import com.feraz.contabilidad.sat.electronica.model.ErpSatRepAuxFolDet;
import com.feraz.sat.contabilidad_electronica.RepAuxFolv11;
import com.feraz.sat.contabilidad_electronica.auxiliar.folios.CMoneda;
import com.feraz.sat.contabilidad_electronica.auxiliar.folios.ObjectFactory;
import com.feraz.sat.contabilidad_electronica.auxiliar.folios.RepAuxFol;
import com.google.common.base.Charsets;
import com.google.common.io.Files;
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
import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;
import org.apache.log4j.Logger;
import org.jamh.data.process.ProcessDao;
import org.springframework.beans.factory.annotation.Value;

/**
 *
 * @author 55555
 */
public class GeneraAuxiliarFoliosTXML {
    

    
  //  private static final Logger log = Logger.getLogger(GeneraAuxiliarFoliosXML11.class);

    @Value("${document.file.dirXMLSAT}")
    private String dirOut;
    private ProcessDao processDao;

    public GeneraAuxiliarFoliosTXML() {
    }

    public String procesa2( String name, String calendario, String periodo, String compania) {
        ObjectFactory of = new ObjectFactory();
        RepAuxFol repAuxFol = of.createRepAuxFol();
        
       
        
        
        // System.out.println("erpSatRepAuxFol:"+ erpSatRepAuxFol.getAnio());
        //   System.out.println("erpSatRepAuxFol.getRfc():"+ erpSatRepAuxFol.getRfc());
        repAuxFol.setAnio(Integer.parseInt(calendario));
        repAuxFol.setMes(periodo);
        if (compania.equalsIgnoreCase("TPQ")){
           repAuxFol.setRFC("TPQ9112231K3");
        }else{
        
            repAuxFol.setRFC("TPA920117QJ3");
        
        }
        
         
        repAuxFol.setVersion("1.2");
        repAuxFol.setTipoSolicitud("AF");
        //repAuxFol.setNumOrden("ABC1212345/12");
        //repAuxFol.setNumTramite("0123456789");
        repAuxFol.setNumOrden("ABC1212345/12");
        repAuxFol.setNumTramite("0123456789");
       String val = null;
        

    //  log.debug("Generando auxiliar de folios");


        try {
            
             
            Map  polizast = new HashMap();
           
            polizast.put("calendario", calendario);
            polizast.put("periodo", periodo);
            polizast.put("compania", compania);
               
        //       log.debug("Generando QUERY");
               
               
               List buscaPolizasT = processDao.getMapResult("BuscaPolizasT", polizast);    
               
      //         log.debug("Data encontrada");
               
             
               
            //  if (buscaPolizasT.size() > 0){
              String data = buscaPolizasT.toString();
              
              System.out.println("data: "+data);
              
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
            
            //  }
              
                    
                 ObjectMapper mapper = new ObjectMapper();
                //  System.out.println("buscaTransacciones.get(0).toString()"+data);
                  
                  
                  System.out.println("Convertiendo...");
                  
                  System.out.println("Convertiendo..."+ data);
                  
                  
                  List<ErpCargaPolizasRelacionDTO> listaTran = mapper.readValue(data,
                    mapper.getTypeFactory().constructCollectionType(List.class,
                            ErpCargaPolizasRelacionDTO.class));
                
                    System.out.println("Iterador data...");
                  Iterator<ErpCargaPolizasRelacionDTO> it = listaTran.iterator();
                  
              
            
         

            while (it.hasNext()) {
                
             
                
               RepAuxFol.DetAuxFol det = new RepAuxFol.DetAuxFol();
               ErpCargaPolizasRelacionDTO auxFolDet = it.next();
              
         //      log.debug("Auxiliar folios Detalles");
         //      log.debug("auxFolDet.getNumUniDenPol(): "+auxFolDet.getNumUniDenPol());
         //      log.debug("auxFolDet.getFecha()"+auxFolDet.getFecha());
               System.out.println("Auxiliar folios Detalles");
               System.out.println("auxFolDet.getNumUniDenPol(): "+auxFolDet.NUMIDENPOL);
               //System.out.println("auxFolDet.getFecha()"+auxFolDet.getFecha());
               
                
                // det.setConcepto("test");
                det.setNumUnIdenPol(auxFolDet.NUMIDENPOL);
        
               DatatypeFactory datatypeFactory = DatatypeFactory.newInstance();
                GregorianCalendar calendar = new GregorianCalendar();

                calendar.clear();

                Calendar parsedCalendar = Calendar.getInstance();

                //System.out.println(pl.getFecha().toString());
                // eg "yyyy-MM-dd"
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                // this may throw ParseException
                Date rawDate = sdf.parse(auxFolDet.FECHA);
                parsedCalendar.setTime(rawDate);

                calendar.set(parsedCalendar.get(Calendar.YEAR),
                        parsedCalendar.get(Calendar.MONTH),
                        parsedCalendar.get(Calendar.DATE));

                XMLGregorianCalendar xmlCalendar = datatypeFactory.newXMLGregorianCalendar(calendar);
                det.setFecha(xmlCalendar);
                
            

                  SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy"); //Formato en que desamos la fecha
                String fechaConFormato = formato.format(rawDate); //Obtenemos la fecha ya con el formato.
                
             
                Map  transacciones = new HashMap();
            
               transacciones.put("compania", auxFolDet.compania);
               transacciones.put("numIde", auxFolDet.NUMIDENPOL);
               
               
        System.out.println("Generando QUERY det aux...................");
               
               
               List buscaTransacciones = processDao.getMapResult("BuscaTransXFolioT", transacciones);    
               
      //         log.debug("Data encontrada");
               
              
               
              if (buscaTransacciones.size() > 0){
              String data2 = buscaTransacciones.toString();
              System.out.println("data2: "+data2);
              int index2 = data2.indexOf("[");
                    if (index2 == -1) {
                        data2 = "[" + data2 + "]";
                    }
                    
                    data2 = data2.replace("=", "\":\"");
                    data2 = data2.replace("{", "{\"");
                    data2 = data2.replace("}", "\"}");
                    data2 = data2.replace(", ", "\", \"");
                    data2 = data2.replace("null", "");
                    data2 = data2.replace("}\", \"{", "}, {");
                    data2 = data2.replaceAll("\\p{Cc}", "");
                    
                    
                 ObjectMapper mapper2 = new ObjectMapper();
                //  System.out.println("buscaTransacciones.get(0).toString()"+data);
                  
                  
                 System.out.println("Convertiendo...");
                  
                  List<ErpFeCargaCfdiDTO> listaTran2 = mapper.readValue(data2,
                    mapper.getTypeFactory().constructCollectionType(List.class,
                            ErpFeCargaCfdiDTO.class));
                
                    System.out.println("Iterador...");
                  Iterator<ErpFeCargaCfdiDTO> itT = listaTran2.iterator();
                  
             //      log.debug("generando archivo xml");

                    while (itT.hasNext()) {
                     
                      ErpFeCargaCfdiDTO ss = itT.next();
                      
              //        log.debug("UUID "+ss.uuidCfdi);
                      System.out.println("UUID "+ss.UUID);
                      
                      
//                        compNal.setMetPagoAux(val);
//                        compNal.setMoneda(CMoneda.AFN);
//                        compNal.setMontoTotal(BigDecimal.TEN);
//                        compNal.setRFC("VLH801218232");
//                        compNal.setTipCamb(BigDecimal.ONE);
//                        compNal.setUUIDCFDI("64491328-62FE-4822-82F5-F5EAA187D66B");
//                        det.getComprNal().add(compNal);
                      
                      // if (ss.MET_PAGO_AUX.equalsIgnoreCase("") || ss.MET_PAGO_AUX == null){
                           
                           
                         //  ss.MET_PAGO_AUX = "98";
                       
                      // }
                        
                       //  if (ss.tipo.equalsIgnoreCase("CompNal")){
                    
                            RepAuxFol.DetAuxFol.ComprNal compNal = new RepAuxFol.DetAuxFol.ComprNal();
                            compNal.setUUIDCFDI(ss.UUID);
                             System.out.println("UUID "+ss.UUID);
                            compNal.setMoneda(CMoneda.valueOf("MXN"));
                             System.out.println("MONEDA ");
                            compNal.setMontoTotal(new BigDecimal(ss.MONTO_TOTAL));
                             System.out.println("MONTO ");
                            compNal.setRFC(ss.RFC_RECEPTOR);
                             System.out.println("RFC ");
                            compNal.setTipCamb(new BigDecimal(ss.TIPO_CAMBIO));
                             System.out.println("CAMBIO ");
                            compNal.setMetPagoAux("98");

                            System.out.println("Agregando ss.UUID"+ ss.UUID);
                            det.getComprNal().add(compNal);
                           
                       // }
//                        if (ss.tipo.equalsIgnoreCase("CompNalOtr")){
//                             RepAuxFol.DetAuxFol.ComprNalOtr compNalOtr = new RepAuxFol.DetAuxFol.ComprNalOtr();
//                            compNalOtr.setCFDCBBNumFol(new BigInteger(ss.cfdCbbNumFol));
//                            compNalOtr.setCFDCBBSerie(ss.cfdCbbSerie);
//                            compNalOtr.setMoneda(CMoneda.valueOf(ss.moneda));
//                            compNalOtr.setMontoTotal(new BigDecimal(ss.montoTotal));
//                            compNalOtr.setRFC(ss.rfc);
//                            compNalOtr.setTipCamb(new BigDecimal(ss.tipCamb));
//                            compNalOtr.setMetPagoAux(ss.metPagoAux);
//
//                            det.getComprNalOtr().add(compNalOtr);
//                        }
//
//                        if (ss.tipo.equalsIgnoreCase("CompExt")){
//
//                            RepAuxFol.DetAuxFol.ComprExt compExt = new RepAuxFol.DetAuxFol.ComprExt();
//                            //System.out.println("CMoneda.AED.toString()"+mx.bigdata.sat.ce_polizas_periodo.schema.CMoneda.AED.toString());
//                            compExt.setMoneda(CMoneda.valueOf(ss.moneda));
//                            compExt.setMontoTotal(new BigDecimal(ss.montoTotal));
//                            compExt.setNumFactExt(ss.numFactExt);
//                            compExt.setTaxID(ss.taxId);
//                            compExt.setTipCamb(new BigDecimal(ss.tipCamb));
//                            compExt.setMetPagoAux(ss.metPagoAux);
//
//
//                            det.getComprExt().add(compExt);
//                        }

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
            
     //       log.error("val:"+e.getMessage());
             
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
 
    
    

