package com.feraz.contabilidad.sat.process;

import com.feraz.contabilidad.sat.electronica.model.ErpSatBalanza;
import com.feraz.contabilidad.sat.electronica.model.ErpSatBalanzaCtas;
import com.feraz.contabilidad.sat.exception.CuentasException;
import com.google.common.base.Charsets;
import com.google.common.io.Files;
import java.io.File;
import java.io.FileOutputStream;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Iterator;
import java.util.List;
import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;
import mx.bigdata.sat.ce_balanza_comprobacion.schema.Balanza;
import mx.bigdata.sat.ce_balanza_comprobacion.schema.ObjectFactory;
import mx.bigdata.sat.contabilidad_electronica.BalanzaComprobacionv11;
import org.springframework.beans.factory.annotation.Value;
import org.xml.sax.helpers.DefaultHandler;

/**
 *
 * @author Ing. JAMH
 */


public class GeneraBalanzaXML11 {
    
          @Value("${document.file.dirXMLSAT}")
  private String dirOut;
          
    public String procesa( List<ErpSatBalanza> lista ,String name) throws DatatypeConfigurationException, ParseException{
        ObjectFactory of = new ObjectFactory();
        Balanza balanza = of.createBalanza();
     String val=null;
       ErpSatBalanza bal = lista.get(0);
       balanza.setAnio(new Integer(bal.getAnio()));
       balanza.setMes(bal.getMes());
       balanza.setRFC(bal.getRfc());
       balanza.setVersion(bal.getVersiones());
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

                XMLGregorianCalendar xmlCalendar = datatypeFactory.newXMLGregorianCalendar(calendar);
           balanza.setFechaModBal(xmlCalendar);
       }
  
       balanza.setVersion(bal.getVersiones());

       try{

       Iterator<ErpSatBalanzaCtas> it = bal.getErpSatBalanzaCtas().iterator();
       
       while(it.hasNext()){
           ErpSatBalanzaCtas cta = it.next();
           Balanza.Ctas ct= new Balanza.Ctas();
         
           ct.setNumCta(cta.getNumCta());
           ct.setSaldoIni(cta.getSaldoIni());
           ct.setDebe(cta.getDebe());
           ct.setHaber(cta.getHaber());
           ct.setSaldoFin(cta.getSaldoFin());
           
           balanza.getCtas().add(ct);
       }
       
           BalanzaComprobacionv11 balanza11 = new BalanzaComprobacionv11(balanza, "mx.bigdata.sat.ce_balanza_comprobacion.schema");
           balanza11.addNamespace("www.sat.gob.mx/esquemas/ContabilidadE/1_1/BalanzaComprobacion", "BCE");    
           CuentasException err = new CuentasException();
            balanza11.validar(err);   
            balanza11.validar();     
            String dir =dirOut+name+".xml";
            FileOutputStream out = new FileOutputStream(dir);
            balanza11.guardar(out);
          
            
            out.close();
       
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
