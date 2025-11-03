/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.feraz.contabilidad.sat.process;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.feraz.contabilidad.sat.electronica.dao.ErpSatAuxiliarCtasDetDao;
import com.feraz.contabilidad.sat.electronica.dto.AuxNumCtaDTO;
import com.feraz.contabilidad.sat.electronica.dto.AuxNumCtaDetDTO;
import com.feraz.contabilidad.sat.electronica.model.ErpSatAuxiliarCtas;
import mx.gob.sat.esquemas.contabilidadE.x13.auxiliarCtas.AuxiliarCtasDocument;

import mx.gob.sat.esquemas.contabilidadE.x13.auxiliarCtas.AuxiliarCtasDocument.Factory;
import mx.gob.sat.esquemas.contabilidadE.x13.auxiliarCtas.AuxiliarCtasDocument.AuxiliarCtas;
import mx.gob.sat.esquemas.contabilidadE.x13.auxiliarCtas.AuxiliarCtasDocument.AuxiliarCtas.Cuenta;
import mx.gob.sat.esquemas.contabilidadE.x13.auxiliarCtas.AuxiliarCtasDocument.AuxiliarCtas.Cuenta.DetalleAux;
import mx.gob.sat.esquemas.contabilidadE.x13.auxiliarCtas.AuxiliarCtasDocument.AuxiliarCtas.Mes;
import mx.gob.sat.esquemas.contabilidadE.x13.auxiliarCtas.impl.AuxiliarCtasDocumentImpl;
import com.google.common.base.Charsets;
import com.google.common.io.Files;
import java.io.File;
import java.io.FileOutputStream;
import java.math.BigDecimal;
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
import javax.xml.namespace.QName;
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
public class GeneraAuxiliarCtasXML13 {
    
    private static final Logger log = Logger.getLogger(GeneraAuxiliarCtasXML11.class);
    
    @Value("${document.file.dirXMLSAT}")
    private String dirOut;
    private ProcessDao processDao;
    private ErpSatAuxiliarCtasDetDao erpSatAuxiliarCtasDetDao;

    public GeneraAuxiliarCtasXML13() {
    }
    
    
      public String procesa2(ErpSatAuxiliarCtas erpSatAuxiliarCtas, String name) {
          
        AuxiliarCtasDocument auxiliarCtasDocument = AuxiliarCtasDocument.Factory.newInstance(); //Documento
        AuxiliarCtasDocument.AuxiliarCtas auxiliarCtas = auxiliarCtasDocument.addNewAuxiliarCtas();//Comprobante
        
          XmlCursor cursorCatalogo = auxiliarCtas.newCursor();
         
         
             cursorCatalogo.setAttributeText(
				new QName(XMLConstants.W3C_XML_SCHEMA_INSTANCE_NS_URI,
						"schemaLocation"),
				"http://www.sat.gob.mx/esquemas/ContabilidadE/1_3/AuxiliarCtas http://www.sat.gob.mx/esquemas/ContabilidadE/1_3/AuxiliarCtas/AuxiliarCtas_1_3.xsd");
     //         javax.xml.namespace.QName qname = cursorBalanza.getName();
         
       
       
        auxiliarCtas.setAnio(Integer.parseInt(erpSatAuxiliarCtas.getAnio()));
        
        int mes = Integer.parseInt(erpSatAuxiliarCtas.getMes());
        
         switch (mes){
      
          case 1:
              
               auxiliarCtas.setMes(Mes.X_01);
          
          break;
              
          case 2:
              
               auxiliarCtas.setMes(Mes.X_02);
          
          break;
          
          case 3:
              
              auxiliarCtas.setMes(Mes.X_03);
          
          break;
              
          case 4:
              
              auxiliarCtas.setMes(Mes.X_04);
          
          break;
          
          case 5:
              
              auxiliarCtas.setMes(Mes.X_05);
          
          break;
          
          case 6:
              
              auxiliarCtas.setMes(Mes.X_06);
          
          break;
         
          case 7:
              
              auxiliarCtas.setMes(Mes.X_07);
          
          break;
          
          case 8:
              
              auxiliarCtas.setMes(Mes.X_08);
          
          break;
              
          case 9:
              
              auxiliarCtas.setMes(Mes.X_09);
          
          break;
              
          case 10:
              
              auxiliarCtas.setMes(Mes.X_10);
          
          break;
              
              
          case 11:
              
              auxiliarCtas.setMes(Mes.X_11);
          
          break;
              
          case 12:
              
              auxiliarCtas.setMes(Mes.X_12);
          
          break;
          
          case 13:
              
              auxiliarCtas.setMes(Mes.X_12);
          
          break;
              
      }
        
        auxiliarCtas.setRFC(erpSatAuxiliarCtas.getRfc());
        auxiliarCtas.setVersion("1.3");
        auxiliarCtas.setTipoSolicitud(erpSatAuxiliarCtas.getTipoSolicitud());
        
      
        
        if (erpSatAuxiliarCtas.getTipoSolicitud().equalsIgnoreCase("AF")){
            auxiliarCtas.setNumOrden(erpSatAuxiliarCtas.getNumOrden());
        }
        
            
        if (erpSatAuxiliarCtas.getTipoSolicitud().equalsIgnoreCase("FC")){
            auxiliarCtas.setNumOrden(erpSatAuxiliarCtas.getNumOrden());
        }
        
        if (erpSatAuxiliarCtas.getTipoSolicitud().equalsIgnoreCase("DE")){
            auxiliarCtas.setNumTramite(erpSatAuxiliarCtas.getNumTramite());
        }
        
        if (erpSatAuxiliarCtas.getTipoSolicitud().equalsIgnoreCase("CO")){
            auxiliarCtas.setNumTramite(erpSatAuxiliarCtas.getNumTramite());
        }
        
       // auxiliarCtas.setNumOrden(erpSatAuxiliarCtas.getNumOrden());
       // auxiliarCtas.setNumTramite(erpSatAuxiliarCtas.getNumTramite());
        
        String val = null;
        
       // System.out.println("generando... ");

        try {
           

            
            Map  numCtaAux = new HashMap();
         
               numCtaAux.put("compania", erpSatAuxiliarCtas.getCompania());
               numCtaAux.put("pid", erpSatAuxiliarCtas.getPid());
               numCtaAux.put("mes", erpSatAuxiliarCtas.getMes());
               numCtaAux.put("calendario", erpSatAuxiliarCtas.getAnio());
               
              
               
               List buscaCuenta = processDao.getMapResult("BuscaCtaAuxCtas", numCtaAux);    
               
               log.debug("compania: "+erpSatAuxiliarCtas.getCompania());
               log.debug("pid: "+erpSatAuxiliarCtas.getPid());
               log.debug("mes: "+erpSatAuxiliarCtas.getMes());
               log.debug("calendario: "+erpSatAuxiliarCtas.getAnio());
               
             //  System.out.println("compania: "+erpSatAuxiliarCtas.getCompania());
             //  System.out.println("pid: "+erpSatAuxiliarCtas.getPid());
             //  System.out.println("mes: "+erpSatAuxiliarCtas.getMes());
             //  System.out.println("calendario: "+erpSatAuxiliarCtas.getAnio());
               
          //    System.out.println("buscaTransacciones.get(0).toString()"+buscaTransacciones.toString());
               
              if (buscaCuenta.size() > 0){
              String data = buscaCuenta.toString();
             // log.debug("data:"+data);
              int index = data.indexOf("[");
                    if (index == -1) {
                        data = "[" + data + "]";
                    }
                     data = data.replaceAll("\"", "\\\\\"");
                    data = data.replace("=", "\":\"");
                    data = data.replace("{", "{\"");
                    data = data.replace("}", "\"}");
                    data = data.replace(", ", "\", \"");
                    data = data.replace("null", "");
                    data = data.replace("}\", \"{", "}, {");
                    data = data.replaceAll("\\p{Cc}", "");
                    
                    
                    //System.out.println("data: "+data);
                    
                 ObjectMapper mapper = new ObjectMapper();
                  //log.debug("data2:"+data);
                  
                  
                  List<AuxNumCtaDTO> listaCta = mapper.readValue(data,
                    mapper.getTypeFactory().constructCollectionType(List.class,
                            AuxNumCtaDTO.class));
                

                  Iterator<AuxNumCtaDTO> itT = listaCta.iterator();
                  
                  
              int i = 0;
            while (itT.hasNext()) {
               
               Cuenta cuenta = auxiliarCtas.addNewCuenta();
               
              AuxNumCtaDTO ss = itT.next();
              
              log.debug("cuenta: "+ss.cuenta);
           //   System.out.println("cuenta: "+ss.cuenta);
              
               cuenta.setNumCta(ss.cuenta);
               
              // ss.desCta = ss.desCta.replace(",", " ");
               
               cuenta.setDesCta(ss.desCta);
               cuenta.setSaldoIni(new BigDecimal(ss.saldoIni));
               cuenta.setSaldoFin(new BigDecimal(ss.saldoFin));
               
               
                  Map  numCtaAuxDet = new HashMap();
         
               numCtaAuxDet.put("compania", erpSatAuxiliarCtas.getCompania());
               numCtaAuxDet.put("pid", erpSatAuxiliarCtas.getPid());
               numCtaAuxDet.put("cuenta", ss.cuenta );
               numCtaAuxDet.put("mes", erpSatAuxiliarCtas.getMes());
               numCtaAuxDet.put("calendario", erpSatAuxiliarCtas.getAnio());
          
               
              
               
               List buscaCuentaDet = processDao.getMapResult("BuscaCtaAuxCtasDet", numCtaAuxDet);                 
          //    System.out.println("buscaTransacciones.get(0).toString()"+buscaTransacciones.toString());
               
              if (buscaCuentaDet.size() > 0){
              String data2 = buscaCuentaDet.toString();
              System.out.println("data2");
                  System.out.println(data2);
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
                    
                    System.out.println(data2);
                 ObjectMapper mapper2 = new ObjectMapper();
                  //System.out.println("buscaTransacciones.get(0).toString()"+data2);
                  
                  List<AuxNumCtaDetDTO> listaCtaDet = mapper2.readValue(data2,
                    mapper.getTypeFactory().constructCollectionType(List.class,
                            AuxNumCtaDetDTO.class));
                

                  Iterator<AuxNumCtaDetDTO> iteDet = listaCtaDet.iterator();
               
               
                   //List<ErpSatAuxiliarCtasDet> lista2 = erpSatAuxiliarCtasDetDao.FindErpSatAuxiliarCtasDet2(erpSatAuxiliarCtas.getCompania(), erpSatAuxiliarCtas.getPid(),ss.cuenta);
                   // Iterator<ErpSatAuxiliarCtasDet> it = lista2.iterator();
                    
                   // System.out.println("lista"+ lista2.get(4).getNumUnidenPol());
                   //  System.out.println("lista"+ lista2.listIterator());
            
                   int j = 0;
                while (iteDet.hasNext()) {
                    //AuxiliarCtas.Cuenta.DetalleAux detalleAux = new AuxiliarCtas.Cuenta.DetalleAux();
                    
                    DetalleAux detalleAux = cuenta.addNewDetalleAux();
                    
                    AuxNumCtaDetDTO ctasDet = iteDet.next();
                   //Detalle del auxiliar
                    
                      //System.out.println("numero de polizas"+ctasDet.getNumUnidenPol());
                    
                      DatatypeFactory datatypeFactory = DatatypeFactory.newInstance();
                        GregorianCalendar calendar = new GregorianCalendar();
                        
                        System.out.println(ctasDet.CONCEPTO);
                        System.out.println(ctasDet.NUM_UNIDEN_POL);

                        calendar.clear();

                        Calendar parsedCalendar = Calendar.getInstance();

                        //System.out.println(pl.getFecha().toString());
                        // eg "yyyy-MM-dd"
                        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                        // this may throw ParseException
                        Date rawDate = sdf.parse(ctasDet.FECHA);
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
                

                  
                    detalleAux.setFecha(holiday);
                    detalleAux.setNumUnIdenPol(ctasDet.NUM_UNIDEN_POL);
                    detalleAux.setDebe(new BigDecimal(ctasDet.DEBE));
                    detalleAux.setHaber(new BigDecimal(ctasDet.HABER));
                    detalleAux.setConcepto(ctasDet.CONCEPTO);
                    
                   // System.out.println("------------");
                   // System.out.println(detalleAux.getConcepto());
                   // System.out.println(detalleAux.getNumUnIdenPol());
                   // System.out.println(j);
                    
                    
                    
                    cuenta.setDetalleAuxArray(j, detalleAux);
                    
                    j = j+1;
            
                }
              }
              
              auxiliarCtas.setCuentaArray(i, cuenta);
        
           
           
               i = i + 1;
          //   auxiliarCtas.getCuenta().add(cuenta);
            
            }
            
           }
            
           
            
             String dir =dirOut+name+".xml";
       
        
        XmlOptions xmlOptions = new XmlOptions();
        xmlOptions.setCharacterEncoding("UTF-8");
        xmlOptions.setSavePrettyPrint();
        xmlOptions.setSavePrettyPrintIndent(4);
       String xmlStr = auxiliarCtasDocument.xmlText(xmlOptions);
       
       System.out.println(xmlStr);
       
     
             FileOutputStream out = new FileOutputStream(dir);
             auxiliarCtasDocument.save(out);
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

    public void setErpSatAuxiliarCtasDetDao(ErpSatAuxiliarCtasDetDao erpSatAuxiliarCtasDetDao) {
        this.erpSatAuxiliarCtasDetDao = erpSatAuxiliarCtasDetDao;
    }

    
}
