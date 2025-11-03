/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.feraz.contabilidad.sat.process;

/**
 *
 * @author 55555
 */

import com.feraz.contabilidad.sat.electronica.model.ErpSatBalanza;
import com.feraz.contabilidad.sat.electronica.model.ErpSatBalanzaCtas;
import com.feraz.contabilidad.sat.exception.CuentasException;
import com.google.common.base.Charsets;
import com.google.common.collect.ImmutableMap;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.google.common.io.Files;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import javax.xml.XMLConstants;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;
import javax.xml.namespace.QName;
import mx.bigdata.sat.common.NamespacePrefixMapperImpl;
import mx.gob.sat.esquemas.contabilidadE.x13.balanzaComprobacion.BalanzaDocument;
//import mx.bigdata.sat.ce_balanza_comprobacion.schema.Balanza;
//import mx.bigdata.sat.ce_balanza_comprobacion.schema.ObjectFactory;
//import mx.bigdata.sat.contabilidad_electronica.BalanzaComprobacionv11;
import mx.gob.sat.esquemas.contabilidadE.x13.balanzaComprobacion.BalanzaDocument.Balanza.Factory;
import mx.gob.sat.esquemas.contabilidadE.x13.balanzaComprobacion.BalanzaDocument.Balanza.Ctas;
import mx.gob.sat.esquemas.contabilidadE.x13.balanzaComprobacion.impl.BalanzaDocumentImpl;
import org.springframework.beans.factory.annotation.Value;
import mx.gob.sat.esquemas.contabilidadE.x13.balanzaComprobacion.BalanzaDocument.Balanza.Mes;
import org.apache.xmlbeans.XmlCalendar;
import org.apache.xmlbeans.XmlCursor;
import org.apache.xmlbeans.XmlOptions;

public class GeneraBalanzaXML13 {
    
    @Value("${document.file.dirXMLSAT}")
  private String dirOut;
    
    
 
     public String procesa( List<ErpSatBalanza> lista ,String name) throws DatatypeConfigurationException, ParseException, JAXBException{
        
        BalanzaDocument balanzaDocument = BalanzaDocument.Factory.newInstance(); //Documento
        BalanzaDocument.Balanza balanza = balanzaDocument.addNewBalanza();//Comprobante
        XmlCursor cursorBalanza = balanza.newCursor();//cursor para ubicar secciones dentro del comprobante
        QName locationBalanza = new QName("http://www.w3.org/2001/XMLSchema-instance", "schemaLocation");
        
        QName locationBalanzaPrefix = new QName("http://www.sat.gob.mx/esquemas/ContabilidadE/1_3/BalanzaComprobacion", "BCE");
    
        
       // cursorBalanza.beginElement(locationBalanza);
        
        cursorBalanza.setAttributeText(
				new QName(XMLConstants.W3C_XML_SCHEMA_INSTANCE_NS_URI,
						"schemaLocation"),
				"http://www.sat.gob.mx/esquemas/ContabilidadE/1_3/BalanzaComprobacion http://www.sat.gob.mx/esquemas/ContabilidadE/1_3/BalanzaComprobacion/BalanzaComprobacion_1_3.xsd");
     //         javax.xml.namespace.QName qname = cursorBalanza.getName();
//        cursorBalanza.setName(new javax.xml.namespace.QName(
//            "www.sat.gob.mx/esquemas/ContabilidadE/1_3/BalanzaComprobacion", "BCE", qname.getPrefix()));
//        
      // cursorBalanza.setName(new javax.xml.namespace.QName(newURI, currQName.getLocalPart()));
    
     String val=null;
       ErpSatBalanza bal = lista.get(0);
       
       balanza.setAnio(new Integer(bal.getAnio()));
       
      int mes = Integer.parseInt(bal.getMes());
      
      switch (mes){
      
          case 1:
              
              balanza.setMes(Mes.X_01);
          
          break;
              
          case 2:
              
              balanza.setMes(Mes.X_02);
          
          break;
          
          case 3:
              
              balanza.setMes(Mes.X_03);
          
          break;
              
          case 4:
              
              balanza.setMes(Mes.X_04);
          
          break;
          
          case 5:
              
              balanza.setMes(Mes.X_05);
          
          break;
          
          case 6:
              
              balanza.setMes(Mes.X_06);
          
          break;
         
          case 7:
              
              balanza.setMes(Mes.X_07);
          
          break;
          
          case 8:
              
              balanza.setMes(Mes.X_08);
          
          break;
              
          case 9:
              
              balanza.setMes(Mes.X_09);
          
          break;
              
          case 10:
              
              balanza.setMes(Mes.X_10);
          
          break;
              
              
          case 11:
              
              balanza.setMes(Mes.X_11);
          
          break;
              
          case 12:
              
              balanza.setMes(Mes.X_12);
          
          break;
          
          case 13:
              
              balanza.setMes(Mes.X_13);
          
          break;
              
      }
      
       
       balanza.setRFC(bal.getRfc());
       balanza.setVersion("1.3");
       balanza.setTipoEnvio(bal.getTipoEnvio());
       if (bal.getTipoEnvio().equalsIgnoreCase("C")){
           
           
           DatatypeFactory datatypeFactory = DatatypeFactory.newInstance();
                GregorianCalendar calendar = new GregorianCalendar();

                calendar.clear();

                Calendar parsedCalendar = Calendar.getInstance();

                //System.out.println(pl.getFecha().toString());
                // eg "yyyy-MM-dd"
               
                SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy"); //Formato en que desamos la fecha
                String fechaModBalForm = formato.format(bal.getFechaModBal()); 
                
                Date rawDate = formato.parse(fechaModBalForm);
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
                
           balanza.setFechaModBal(holiday);
       }
  
      
       try{

       Iterator<ErpSatBalanzaCtas> it = bal.getErpSatBalanzaCtas().iterator();
       
     
        int i = 0;
        
        
       while(it.hasNext()){
          
           ErpSatBalanzaCtas cta = it.next();
           
             Ctas ct = balanza.addNewCtas();
          
           System.out.println("i: "+i);
           System.out.println(cta.getNumCta());
           
           
           ct.setNumCta(cta.getNumCta());
           ct.setSaldoIni(cta.getSaldoIni());
           ct.setDebe(cta.getDebe());
           ct.setHaber(cta.getHaber());
           ct.setSaldoFin(cta.getSaldoFin());
         
          
           
           balanza.setCtasArray(i, ct);
        
           
           
           i = i + 1;
           
          // balanza.getCtas().add(ct);
       }
       
       System.out.println(balanza);
       
       String dir =dirOut+name+".xml";
       
        File f = new File("H://logs/testBalanza2.xml");
        XmlOptions xmlOptions = new XmlOptions();
        xmlOptions.setCharacterEncoding("UTF-8");
        xmlOptions.setSavePrettyPrint();
        xmlOptions.setSavePrettyPrintIndent(4);
        
        
       String xmlStr = balanzaDocument.xmlText(xmlOptions);
       
       System.out.println(xmlStr);
       
     
             FileOutputStream out = new FileOutputStream(dir);
             balanzaDocument.save(out);
              out.close();
       
       
     // Writer writer = null;

    //writer = new BufferedWriter(new OutputStreamWriter(
    //      new FileOutputStream(dir), "utf-8"));
    //writer.write(xmlStr);
       
     //        BalanzaComprobacionv11 balanza11 = new BalanzaComprobacionv11(balanza, "mx.bigdata.sat.ce_balanza_comprobacion.schema");
         //      CuentasException err = new CuentasException();
          //  balanza11.validar(err);   
          //  balanza11.validar();     
         
         //   mx.gob.sat.esquemas.contabilidadE.x13.balanzaComprobacion.impl.BalanzaDocumentImpl impl = new mx.gob.sat.esquemas.contabilidadE.x13.balanzaComprobacion.impl.BalanzaDocumentImpl(mx.gob.sat.esquemas.contabilidadE.x13.balanzaComprobacion.BalanzaDocument.type);
            
         //  System.out.println("impl: "+impl);
          //  impl.setBalanza(balanza);
            
//            
//              String dir =dirOut+name+".xml";
//              FileOutputStream out = new FileOutputStream(dir);
//              balanza.save(out);
//              out.close();
          
       
            val = Files.toString(new File(dir), Charsets.UTF_8);
       
       
       return val;
       }catch(Exception e){
           e.printStackTrace();
           return e.getMessage();
           
       }
       

    }
    
   
    
     public void setDirOut(String dirOut) {
        this.dirOut = dirOut;
    }
    
}
