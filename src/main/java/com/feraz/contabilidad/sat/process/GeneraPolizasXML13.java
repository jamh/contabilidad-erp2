/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.feraz.contabilidad.sat.process;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.feraz.contabilidad.sat.electronica.dto.TransaccionDTO;
import com.feraz.contabilidad.sat.electronica.dto.TransaccionMDTO;
import com.feraz.contabilidad.sat.electronica.model.ErpSatPoliza;
import com.feraz.contabilidad.sat.electronica.model.ErpSatPolizas;
import com.google.common.base.Charsets;
import com.google.common.io.Files;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import javax.xml.XMLConstants;
import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;
import javax.xml.namespace.QName;

import mx.bigdata.sat.contabilidad_electronica.PolizasPeriodov11;
import mx.gob.sat.esquemas.contabilidadE.x13.balanzaComprobacion.BalanzaDocument;
import mx.gob.sat.esquemas.contabilidadE.x13.catalogosParaEsqContE.CBanco;
import mx.gob.sat.esquemas.contabilidadE.x13.catalogosParaEsqContE.CMetPagos;
import mx.gob.sat.esquemas.contabilidadE.x13.polizasPeriodo.PolizasDocument;
import mx.gob.sat.esquemas.contabilidadE.x13.polizasPeriodo.PolizasDocument.Polizas.Poliza;
import mx.gob.sat.esquemas.contabilidadE.x13.polizasPeriodo.PolizasDocument.Polizas.Mes;
import mx.gob.sat.esquemas.contabilidadE.x13.polizasPeriodo.PolizasDocument.Polizas.Poliza.Transaccion;
import mx.gob.sat.esquemas.contabilidadE.x13.polizasPeriodo.PolizasDocument.Polizas.Poliza.Transaccion.Cheque;
import mx.gob.sat.esquemas.contabilidadE.x13.polizasPeriodo.PolizasDocument.Polizas.Poliza.Transaccion.CompExt;
import mx.gob.sat.esquemas.contabilidadE.x13.polizasPeriodo.PolizasDocument.Polizas.Poliza.Transaccion.CompNal;
import mx.gob.sat.esquemas.contabilidadE.x13.polizasPeriodo.PolizasDocument.Polizas.Poliza.Transaccion.CompNalOtr;
import mx.gob.sat.esquemas.contabilidadE.x13.polizasPeriodo.PolizasDocument.Polizas.Poliza.Transaccion.Concepto;
import mx.gob.sat.esquemas.contabilidadE.x13.polizasPeriodo.PolizasDocument.Polizas.Poliza.Transaccion.DesCta;
import mx.gob.sat.esquemas.contabilidadE.x13.polizasPeriodo.PolizasDocument.Polizas.Poliza.Transaccion.NumCta;
import mx.gob.sat.esquemas.contabilidadE.x13.polizasPeriodo.PolizasDocument.Polizas.Poliza.Transaccion.OtrMetodoPago;
import mx.gob.sat.esquemas.contabilidadE.x13.polizasPeriodo.PolizasDocument.Polizas.Poliza.Transaccion.Transferencia;
import mx.gob.sat.esquemas.contabilidadE.x13.polizasPeriodo.PolizasDocument.Factory;
import mx.gob.sat.esquemas.contabilidadE.x13.catalogosParaEsqContE.CMoneda;
import org.apache.xmlbeans.XmlCalendar;
import org.apache.xmlbeans.XmlCursor;
import org.apache.xmlbeans.XmlOptions;
import org.jamh.data.process.ProcessDao;
import org.springframework.beans.factory.annotation.Value;

/**
 *
 * @author vavi
 */
public class GeneraPolizasXML13 {
    
      @Value("${document.file.dirXMLSAT}")
    private String dirOut;
    private ProcessDao processDao;

   

    public String procesa2(ErpSatPolizas erpSatPolizas, List<ErpSatPoliza> lista, String name) throws IOException, DatatypeConfigurationException, ParseException {

      //  ObjectFactory of = new ObjectFactory();
      //  Polizas poliza = of.createPolizas();
        
        PolizasDocument polizasDocument = PolizasDocument.Factory.newInstance(); //Documento
        PolizasDocument.Polizas poliza = polizasDocument.addNewPolizas();//Comprobante
        XmlCursor cursorPoliza = poliza.newCursor();//cursor para ubicar secciones dentro del comprobante
        
        
             cursorPoliza.setAttributeText(
				new QName(XMLConstants.W3C_XML_SCHEMA_INSTANCE_NS_URI,
						"schemaLocation"),
				"http://www.sat.gob.mx/esquemas/ContabilidadE/1_3/PolizasPeriodo http://www.sat.gob.mx/esquemas/ContabilidadE/1_3/PolizasPeriodo/PolizasPeriodo_1_3.xsd");
        
        
        String val = null;
        
       System.out.println("------Entrando a generar poliza-1-----------");

        //Datos generales de la poliza  o nodo principal
        poliza.setAnio(Integer.parseInt(erpSatPolizas.getAnio()));
        
        if(erpSatPolizas.getCertificado()!=null){
        poliza.setCertificado(erpSatPolizas.getCertificado());
        }
        
        
        System.out.println("------Entrando a generar poliza2------------");
         int mes = Integer.parseInt(erpSatPolizas.getMes());
      
      switch (mes){
      
          case 1:
              
              poliza.setMes(Mes.X_01);
          
          break;
              
          case 2:
              
              poliza.setMes(Mes.X_02);
          
          break;
          
          case 3:
              
              poliza.setMes(Mes.X_03);
          
          break;
              
          case 4:
              
              poliza.setMes(Mes.X_04);
          
          break;
          
          case 5:
              
              poliza.setMes(Mes.X_05);
          
          break;
          
          case 6:
              
              poliza.setMes(Mes.X_06);
          
          break;
         
          case 7:
              
              poliza.setMes(Mes.X_07);
          
          break;
          
          case 8:
              
              poliza.setMes(Mes.X_08);
          
          break;
              
          case 9:
              
              poliza.setMes(Mes.X_09);
          
          break;
              
          case 10:
              
              poliza.setMes(Mes.X_10);
          
          break;
              
              
          case 11:
              
              poliza.setMes(Mes.X_11);
          
          break;
              
          case 12:
              
              poliza.setMes(Mes.X_12);
          
          break;
          
          case 13:
              
              poliza.setMes(Mes.X_12);
          
          break;
              
      }
      System.out.println("------Entrando a generar poliza3------------");
        
      if(erpSatPolizas.getNoCertificado() != null){
        poliza.setNoCertificado(erpSatPolizas.getNoCertificado());
      }
        
        
        poliza.setRFC(erpSatPolizas.getRfc());
        poliza.setTipoSolicitud(erpSatPolizas.getTipoSolicitud());
        
        if (erpSatPolizas.getTipoSolicitud().equalsIgnoreCase("AF")){
            poliza.setNumOrden(erpSatPolizas.getNumOrden());
        }
        
            
        if (erpSatPolizas.getTipoSolicitud().equalsIgnoreCase("FC")){
            poliza.setNumOrden(erpSatPolizas.getNumOrden());
        }
        
        if (erpSatPolizas.getTipoSolicitud().equalsIgnoreCase("DE")){
            poliza.setNumTramite(erpSatPolizas.getNumTramite());
        }
        
        if (erpSatPolizas.getTipoSolicitud().equalsIgnoreCase("CO")){
            poliza.setNumTramite(erpSatPolizas.getNumTramite());
        }
       

        
        poliza.setVersion("1.3");
        
        if(erpSatPolizas.getSello()!= null){
        
        poliza.setSello(erpSatPolizas.getSello());
        }
        System.out.println("------Entrando a generar poliza4------------");

        try {
          //Datos de la poliza iterar para que se vea las polizas maestro de polizas

              SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy");
            // SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
            Iterator<ErpSatPoliza> it = lista.iterator();
            
            System.out.println(lista);
            
            // Polizas.Poliza.Transaccion tr = of.createPolizasPolizaTransaccion();
                     //poliza.getPoliza().get(0).getTransaccion().add(tr)
            // Polizas.Poliza.Transaccion.CompNal compNal =   of.createPolizasPolizaTransaccionCompNal();
             //   poliza.getPoliza().get(0).getTransaccion() = new ArrayList();
             // GregorianCalendar gregorianCalendar;
            // XMLGregorianCalendar resultFecha = null;
            
            int i = 0;

            while (it.hasNext()) {
                Poliza pol= poliza.addNewPoliza();
                ErpSatPoliza pl = it.next();
                
                System.out.println("------Entrando a generar poliza------------");
                
                
                System.out.println("pl.getNum()"+ pl.getNum());
                System.out.println("pl.getNum()"+ pl.getConcepto());
                System.out.println("pl.getNum()"+ pl.getFecha());
                System.out.println("pl.getNum()"+ pl.getTipo());
                
                System.out.println("----------------------------------------------");

                DatatypeFactory datatypeFactory = DatatypeFactory.newInstance();
                GregorianCalendar calendar = new GregorianCalendar();

                calendar.clear();

                Calendar parsedCalendar = Calendar.getInstance();

                //System.out.println(pl.getFecha().toString());
                // eg "yyyy-MM-dd"
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                // this may throw ParseException
                Date rawDate = sdf.parse(pl.getFecha().toString());
                parsedCalendar.setTime(rawDate);

                calendar.set(parsedCalendar.get(Calendar.YEAR),
                        parsedCalendar.get(Calendar.MONTH),
                        parsedCalendar.get(Calendar.DATE));

             
         

                calendar.set(parsedCalendar.get(Calendar.YEAR),
                        parsedCalendar.get(Calendar.MONTH),
                        parsedCalendar.get(Calendar.DATE));

              //  XMLGregorianCalendar xmlCalendar = datatypeFactory.newXMLGregorianCalendar(calendar);
                
         //       Calendar  holiday = new XmlCalendar("2004-07-04");
              Calendar  holiday =  new XmlCalendar();
              
              holiday.set(parsedCalendar.get(Calendar.YEAR),
                        parsedCalendar.get(Calendar.MONTH),
                        parsedCalendar.get(Calendar.DATE));
                
                
                   // Date t = df.parse(pl.getFecha().toString());
                // gregorianCalendar = (GregorianCalendar)GregorianCalendar.getInstance();
                // gregorianCalendar.setTime(t);
                // resultFecha = DatatypeFactory.newInstance().newXMLGregorianCalendar(gregorianCalendar);
                //   XMLGregorianCalendar xgc=DatatypeFactory.newInstance().newXMLGregorianCalendar(pl.getFecha().toString());
                
                if(pl.getConcepto()!=null){
                     pol.setConcepto(pl.getConcepto());
                }else{
                
                   pol.setConcepto("N/A");
                }
                
                
                pol.setFecha(holiday);
                
                
                SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy"); //Formato en que desamos la fecha
                String fechaConFormato = formato.format(rawDate); //Obtenemos la fecha ya con el formato.
                
                 Map  transacciones = new HashMap();
              String tPoliza = "" ;
              
              tPoliza=pl.getTipo();
              
              String fechaNum = fechaConFormato.replace("/", "");
              
              //System.out.println("fechaNum: "+fechaNum);
              
//              if (tPolizaNum == 1){
//                  
//                  tPoliza = "I";
//              
//              }
//              if (tPolizaNum == 2){
//                  
//                  tPoliza = "E";
//              
//              }
//              if (tPolizaNum == 3){
//                  
//                  tPoliza = "D";
//              
//              }
              
              pol.setNumUnIdenPol(fechaNum+tPoliza+pl.getNum());
              
               transacciones.put("compania", erpSatPolizas.getCompania());
               transacciones.put("tipo_pol", tPoliza);
               transacciones.put("numero", pl.getNum().toString());
               transacciones.put("fecha", fechaConFormato);
               
              
               System.out.println("TRANSACCION CONTA");
               List buscaTransacciones = processDao.getMapResult("BuscaTransXPoliza", transacciones);                 
             System.out.println("buscaTransacciones.get(0).toString()"+buscaTransacciones.size());
             System.out.println("buscaTransacciones.get(0).toString()"+buscaTransacciones.isEmpty());
               
              if (!buscaTransacciones.isEmpty() || buscaTransacciones.size() > 0){
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
                    
                    
                 ObjectMapper mapper = new ObjectMapper();
                  System.out.println("buscaTransacciones.get(0).toString()"+data);
                  
                 // List<TransaccionDTO> listaTran = mapper.readValue(data,
                 //   mapper.getTypeFactory().constructCollectionType(List.class,
                 //           TransaccionDTO.class));
                 
                 List<TransaccionMDTO> listaTran = mapper.readValue(data,
                     mapper.getTypeFactory().constructCollectionType(List.class,
                            TransaccionMDTO.class));
                
                 

                  Iterator<TransaccionMDTO> itT = listaTran.iterator();
                  
                  
            int iCompNal = 0;
            int iComNalOtr = 0;
            int iCompExt = 0;
            int iCheque = 0;
            int iTransferen = 0;
            int iOtrMetodoP = 0;
            int trans = 0;
            
            
            
            while (itT.hasNext()) {
                Transaccion tr = pol.addNewTransaccion();
              TransaccionMDTO ssM = itT.next();
                //System.out.println("Entrando a generar transaccion"); 
               System.out.println("itT"+ssM.cuenta);
                //Agregar la transaccion
               System.out.println("----------Generando transaccion---------------");
               
               System.out.println("ss.cuenta"+ssM.cuenta);
               System.out.println("ss.desCta"+ssM.desCta);
               System.out.println("ss.descripcion"+ ssM.descripcion);
               
               System.out.println("----------------------------------------------");
               
                tr.setNumCta(ssM.cuenta);
                tr.setDesCta(ssM.desCta);
                 if (new BigDecimal(ssM.cargos).compareTo(new BigDecimal(0)) == 0){
                                
                                    tr.setConcepto("Abono a "+ ssM.desCta);
                                }else{
                                    
                                    
                                    tr.setConcepto("Cargo a "+ssM.desCta);
                                
                                
                                }
                tr.setDebe(new BigDecimal(ssM.cargos));
                tr.setHaber(new BigDecimal(ssM.abonos));
              
                
                     
//                         System.out.println("CMoneda.AED.toString()"+CMoneda.AED.toString());
//                           CMoneda moneda = CMoneda.valueOf(ss.moneda);
//                           
//                          System.out.println("moneda"+moneda);
                            
                        
                        
                    //    moneda.
             //Esto por el moemento es un if poara estas opciones dependiendo lo que tenga      
                System.out.println("---------------------2------------------------");

              
               transacciones.put("sec", ssM.sec);
               
              
               List buscaTransaccionesDet = processDao.getMapResult("BuscaTransXPolizaDet", transacciones);                 
             System.out.println("comp.get(0).toString()"+buscaTransaccionesDet.size());
             System.out.println("comp.get(0).toString()"+buscaTransaccionesDet.isEmpty());
               
              if (!buscaTransaccionesDet.isEmpty() || buscaTransacciones.size() > 0){
              String dataDet = buscaTransaccionesDet.toString();
              int index2 = dataDet.indexOf("[");
                    if (index2 == -1) {
                        dataDet = "[" + dataDet + "]";
                    }
                    
                    dataDet = dataDet.replace("=", "\":\"");
                    dataDet = dataDet.replace("{", "{\"");
                    dataDet = dataDet.replace("}", "\"}");
                    dataDet = dataDet.replace(", ", "\", \"");
                    dataDet = dataDet.replace("null", "");
                    dataDet = dataDet.replace("}\", \"{", "}, {");
                    
                    
                
                  System.out.println("buscando comp.get(0).toString()"+dataDet);
                  
                 // List<TransaccionDTO> listaTran = mapper.readValue(data,
                 //   mapper.getTypeFactory().constructCollectionType(List.class,
                 //           TransaccionDTO.class));
                 
                 List<TransaccionDTO> listaTranDet = mapper.readValue(dataDet,
                     mapper.getTypeFactory().constructCollectionType(List.class,
                            TransaccionDTO.class));
                
                 

                  Iterator<TransaccionDTO> itTDet = listaTranDet.iterator();
    
            
            
            while (itTDet.hasNext()) {
          
              TransaccionDTO ss = itTDet.next();
                if (ss.tipo.equalsIgnoreCase("CompNal")){
                    
                     System.out.println("Compnal");
                     System.out.println("Compnal id:" +iCompNal);
                    
                    CompNal compNal = tr.addNewCompNal();
                    compNal.setUUIDCFDI(ss.uuidCfdi);
                    System.out.println("Compnal2");
                    if (ss.moneda == ""){
                         
                        compNal.setMoneda(CMoneda.Enum.forString("MXN"));
                        
                    }else{
                    
                        
                        compNal.setMoneda(CMoneda.Enum.forString(ss.moneda));
                    }
                    
                    System.out.println("Compnal3");
                    
                    compNal.setMontoTotal(new BigDecimal(ss.montoTotal));
                    compNal.setRFC(ss.rfc);
                    compNal.setTipCamb(new BigDecimal(ss.tipCamb));
                    System.out.println("Compnal4");
                   // System.out.println("Agregando COMPNAL"+ tr.getNumCta());
                    
                    tr.setCompNalArray(0,compNal);
                    iCompNal = iCompNal + 1;
                    
                    System.out.println("Compnal2");
                    
                }
                if (ss.tipo.equalsIgnoreCase("CompNalOtr")){
                    CompNalOtr compNalOtr= tr.addNewCompNalOtr();
                    
                    compNalOtr.setCFDCBBNumFol(new BigInteger(ss.cfdCbbNumFol));
                    compNalOtr.setCFDCBBSerie(ss.cfdCbbSerie);
                    compNalOtr.setMoneda(CMoneda.Enum.forString(ss.moneda));
                    compNalOtr.setMontoTotal(new BigDecimal(ss.montoTotal));
                    compNalOtr.setRFC(ss.rfc);
                    compNalOtr.setTipCamb(new BigDecimal(ss.tipCamb));
     
                    tr.setCompNalOtrArray(0,compNalOtr);
                    
                    iComNalOtr = iComNalOtr + 1;
                }
                
                if (ss.tipo.equalsIgnoreCase("CompExt")){
                
                    CompExt compExt = tr.addNewCompExt();
                   
                    compExt.setMoneda(CMoneda.Enum.forString(ss.moneda));
                    compExt.setMontoTotal(new BigDecimal(ss.montoTotal));
                    compExt.setNumFactExt(ss.numFactExt);
                    compExt.setTaxID(ss.taxId);
                    compExt.setTipCamb(new BigDecimal(ss.tipCamb));
                    
                    
                    tr.setCompExtArray(0, compExt);
                    
                    iCompExt = iCompExt + 1;
                }
                if(ss.tipo.equalsIgnoreCase("Cheque")){
                    Cheque cheque = tr.addNewCheque();
                    
                    cheque.setBanEmisExt(ss.banEmisExt);
                    cheque.setBanEmisNal(CBanco.Enum.forString(ss.banEmisNal));
                    cheque.setBenef(ss.benef);
                    cheque.setCtaOri(ss.ctaOri);
                    
                    GregorianCalendar calendar2 = new GregorianCalendar();

                     calendar2.clear();

                     Calendar parsedCalendar2 = calendar2.getInstance();

                     Date rawDate2 = formato.parse(ss.fecha);
                     parsedCalendar2.setTime(rawDate2);

                    Calendar  holidayCheque =  new XmlCalendar();
              
                      holidayCheque.set(parsedCalendar2.get(calendar2.YEAR),
                        parsedCalendar2.get(calendar2.MONTH),
                        parsedCalendar2.get(calendar2.DATE));
                    cheque.setFecha(holidayCheque);
                    cheque.setMoneda(CMoneda.Enum.forString(ss.moneda));
                    cheque.setMonto(new BigDecimal(ss.montoTotal));
                    cheque.setNum(ss.num);
                    cheque.setRFC(ss.rfc);
                    cheque.setTipCamb(new BigDecimal(ss.tipCamb));
                    
                    
                    tr.setChequeArray(0, cheque);
                    
                    iCheque = iCheque + iCheque;
                }
                if(ss.tipo.equalsIgnoreCase("Transferen")){
                    
                    System.out.println("Transferen");
                    Transferencia transferencia= tr.addNewTransferencia();
                    
                    if(!ss.bancoDestExt.equalsIgnoreCase("")){
                      transferencia.setBancoDestExt(ss.bancoDestExt);
                    }
                    if(!ss.bancoDestNal.equalsIgnoreCase("")){
                    transferencia.setBancoDestNal(CBanco.Enum.forString(ss.bancoDestNal));
                    }
                    
                    if(!ss.bancoOriExt.equalsIgnoreCase("")){
                      transferencia.setBancoOriExt(ss.bancoOriExt);
                    }
                    
                    if(!ss.bancoOriNal.equalsIgnoreCase("")){
                      transferencia.setBancoOriNal(CBanco.Enum.forString(ss.bancoOriNal));
                    }
                      transferencia.setBenef(ss.benef);
                    if(!ss.ctaDest.equalsIgnoreCase("")){
                      transferencia.setCtaDest(ss.ctaDest);
                    }
                    if(!ss.ctaOri.equalsIgnoreCase("")){
                      transferencia.setCtaOri(ss.ctaOri);
                    }
                       GregorianCalendar calendar2 = new GregorianCalendar();

                     calendar2.clear();

                     Calendar parsedCalendar2 = calendar2.getInstance();

                     Date rawDate2 = formato.parse(ss.fecha);
                     parsedCalendar2.setTime(rawDate2);

                    Calendar  holidayTrans =  new XmlCalendar();
              
                      holidayTrans.set(parsedCalendar2.get(calendar2.YEAR),
                        parsedCalendar2.get(calendar2.MONTH),
                        parsedCalendar2.get(calendar2.DATE));
                      transferencia.setFecha(holidayTrans);
                      
                      if (ss.moneda == ""){
                          
                          transferencia.setMoneda(CMoneda.Enum.forString("MXN"));
                      
                      }else{
                              
                         transferencia.setMoneda(CMoneda.Enum.forString(ss.moneda));     
                              
                      }
                         
                      transferencia.setMonto(new BigDecimal(ss.montoTotal));
                      transferencia.setRFC(ss.rfc);
                      transferencia.setTipCamb(new BigDecimal(ss.tipCamb));
                      
                    
                    
                    tr.setTransferenciaArray(0,transferencia);
                    
                    iTransferen = iTransferen + 1;
                }
                if(ss.tipo.equalsIgnoreCase("OtrMetodoP")){
                    OtrMetodoPago  otrMetodoPago= tr.addNewOtrMetodoPago();
                    
                    otrMetodoPago.setBenef(ss.benef);
                     
                     GregorianCalendar calendar2 = new GregorianCalendar();

                     calendar2.clear();

                     Calendar parsedCalendar2 = calendar2.getInstance();

                     Date rawDate2 = formato.parse(ss.fecha);
                     parsedCalendar2.setTime(rawDate2);

                    Calendar  holidayOtrMP =  new XmlCalendar();
              
                      holidayOtrMP.set(parsedCalendar2.get(calendar2.YEAR),
                        parsedCalendar2.get(calendar2.MONTH),
                        parsedCalendar2.get(calendar2.DATE));

                    otrMetodoPago.setFecha(holidayOtrMP);
                    otrMetodoPago.setMetPagoPol(CMetPagos.Enum.forString(ss.metPagoPol));
                    otrMetodoPago.setMoneda(CMoneda.Enum.forString(ss.moneda));
                    otrMetodoPago.setMonto(new BigDecimal(ss.montoTotal));
                    otrMetodoPago.setRFC(ss.rfc);
                    otrMetodoPago.setTipCamb(new BigDecimal(ss.tipCamb));
                    
                    
                    tr.setOtrMetodoPagoArray(0,otrMetodoPago);
                    
                    iOtrMetodoP = iOtrMetodoP + 1;
                    
                     System.out.println("Transferen2");
                    
                }
            }
            }

               // System.out.println("Agregando a transaccion");
                 pol.setTransaccionArray(trans,tr);
                 
                 trans = trans + 1;
                 
                // System.out.println("tr cuenta"+tr.getNumCta());
                 //System.out.println("tr compnal"+tr.getCompNal().get(0));
                 
                 
            }
              
               poliza.setPolizaArray(i,pol);
               
               i = i + 1;
    
            }else{
                  
                                 System.out.println("TRANSACCION NOMINA");

                  
                    List buscaTransaccionesNom = processDao.getMapResult("BuscaTransXPolizaNom", transacciones);                 
                        System.out.println("buscaTransacciones.get(0).toString()"+buscaTransaccionesNom.size());
                        System.out.println("buscaTransacciones.get(0).toString()"+buscaTransaccionesNom.isEmpty());

                         if (!buscaTransaccionesNom.isEmpty() || buscaTransaccionesNom.size() > 0){
                         String data = buscaTransaccionesNom.toString();
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


                            ObjectMapper mapper = new ObjectMapper();
                             System.out.println("buscaTransacciones.get(0).toString()"+data);

                             List<TransaccionDTO> listaTran = mapper.readValue(data,
                               mapper.getTypeFactory().constructCollectionType(List.class,
                                       TransaccionDTO.class));


                             Iterator<TransaccionDTO> itT = listaTran.iterator();


                            int iCompNal = 0;
                            int iComNalOtr = 0;
                            int iCompExt = 0;
                            int iCheque = 0;
                            int iTransferen = 0;
                            int iOtrMetodoP = 0;
                            int trans = 0;
                            
                            String sec = "";
                          
                            int z = 0;
                            
                            Transaccion tr = null;
                            
                            while (itT.hasNext()) {
                                
                                
                              TransaccionDTO ss = itT.next();
                              if(!sec.equalsIgnoreCase(ss.SEC)){
                                tr = pol.addNewTransaccion();
                              }
                                //System.out.println("Entrando a generar transaccion"); 
                               System.out.println("itT"+ss.cuenta);
                                //Agregar la transaccion
                               System.out.println("----------Generando transaccion---------------");

                               System.out.println("ss.cuenta"+ss.cuenta);
                               System.out.println("ss.desCta"+ss.desCta);
                               System.out.println("ss.descripcion"+ ss.descripcion);

                               System.out.println("----------------------------------------------");
                               
                               
                               if(!sec.equalsIgnoreCase(ss.SEC)){
                               
                               
                                tr.setNumCta(ss.cuenta);
                                tr.setDesCta(ss.desCta);
                                
                                   System.out.println("comparando");
                                   System.out.println(new BigDecimal(ss.cargos).compareTo(new BigDecimal(0)));
                                
                                if (new BigDecimal(ss.cargos).compareTo(new BigDecimal(0)) == 0){
                                
                                    tr.setConcepto("Abono a "+ ss.desCta);
                                }else{
                                    
                                    
                                    tr.setConcepto("Cargo a "+ss.desCta);
                                
                                
                                }
                                
                               // if(ss.descripcion.equalsIgnoreCase("")){

                              //      tr.setConcepto("N/A");


                               // }else{

                               //     tr.setConcepto(ss.descripcion);

                               // }
                                tr.setDebe(new BigDecimal(ss.cargos));
                                tr.setHaber(new BigDecimal(ss.abonos));
                                
                                z = 0;
                                
                               }

                               

                //                         System.out.println("CMoneda.AED.toString()"+CMoneda.AED.toString());
                //                           CMoneda moneda = CMoneda.valueOf(ss.moneda);
                //                           
                //                          System.out.println("moneda"+moneda);



                                    //    moneda.
                             //Esto por el moemento es un if poara estas opciones dependiendo lo que tenga      
                                System.out.println("---------------------2------------------------");

                                if (ss.tipo.equalsIgnoreCase("CompNal")){

                                     System.out.println("Compnal");
                                     System.out.println("Compnal id:" +iCompNal);

                                    CompNal compNal = tr.addNewCompNal();
                                    compNal.setUUIDCFDI(ss.uuidCfdi);
                                    System.out.println("Compnal2");
                                    if (ss.moneda == ""){

                                        compNal.setMoneda(CMoneda.Enum.forString("MXN"));

                                    }else{


                                        compNal.setMoneda(CMoneda.Enum.forString(ss.moneda));
                                    }

                                    System.out.println("Compnal3");

                                    compNal.setMontoTotal(new BigDecimal(ss.montoTotal));
                                    compNal.setRFC(ss.rfc);
                                    compNal.setTipCamb(new BigDecimal(ss.tipCamb));
                                    System.out.println("Compnal4");
                                   // System.out.println("Agregando COMPNAL"+ tr.getNumCta());

                                    tr.setCompNalArray(z,compNal);
                                    iCompNal = iCompNal + 1;

                                    System.out.println("Compnal2");

                                }
                                
                                if(!sec.equalsIgnoreCase(ss.SEC)){
                               
                                    pol.setTransaccionArray(trans,tr);

                                    trans = trans + 1;
                                }
                                 
                                  sec = ss.SEC;
                                  
                                  z = z+1;

                              

                            }

                               poliza.setPolizaArray(i,pol);

                               i = i + 1;
            
            
                    }
                  
                  
                 //pol.getTransaccion().add(tr);
            }
           
          }
//            System.out.println(" poliza.getPoliza().size:"+ poliza.getPoliza().isEmpty());
//            System.out.println(" poliza.getPoliza().size:"+ poliza.getPoliza().size());
            
        String dir =dirOut+name+".xml";
              
        XmlOptions xmlOptions = new XmlOptions();
        xmlOptions.setCharacterEncoding("UTF-8");
        xmlOptions.setSavePrettyPrint();
        xmlOptions.setSavePrettyPrintIndent(4);
       String xmlStr = polizasDocument.xmlText(xmlOptions);
       
       System.out.println(xmlStr);
       
     
             FileOutputStream out = new FileOutputStream(dir);
             polizasDocument.save(out);
              out.close();
       
     
          
       
            val = Files.toString(new File(dir), Charsets.UTF_8);

        } catch (Exception e) {
            
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
