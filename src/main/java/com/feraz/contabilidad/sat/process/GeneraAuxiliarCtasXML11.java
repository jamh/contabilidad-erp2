package com.feraz.contabilidad.sat.process;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.feraz.contabilidad.sat.electronica.dao.ErpSatAuxiliarCtasDetDao;
import com.feraz.contabilidad.sat.electronica.dto.AuxNumCtaDTO;
import com.feraz.contabilidad.sat.electronica.dto.AuxNumCtaDetDTO;
import com.feraz.contabilidad.sat.electronica.model.ErpSatAuxiliarCtas;
import com.feraz.contabilidad.sat.electronica.model.ErpSatAuxiliarCtasDet;
import com.feraz.contabilidad.ws.WSContabilidad;
import com.feraz.sat.contabilidad_electronica.AuxiliarCtasv11;
import com.feraz.sat.contabilidad_electronica.auxiliar.cuentas.ObjectFactory;
import com.feraz.sat.contabilidad_electronica.auxiliar.cuentas.AuxiliarCtas;
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
import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;
import mx.bigdata.sat.ce_polizas_periodo.schema.Polizas;
import org.apache.log4j.Logger;
import org.jamh.data.process.ProcessDao;
import org.springframework.beans.factory.annotation.Value;

/**
 *
 * @author Administrador
 */
public class GeneraAuxiliarCtasXML11 {
    
    private static final Logger log = Logger.getLogger(GeneraAuxiliarCtasXML11.class);
    
    @Value("${document.file.dirXMLSAT}")
    private String dirOut;
    private ProcessDao processDao;
    private ErpSatAuxiliarCtasDetDao erpSatAuxiliarCtasDetDao;

    public GeneraAuxiliarCtasXML11() {
    }

    public String procesa2(ErpSatAuxiliarCtas erpSatAuxiliarCtas, String name) {
        ObjectFactory of = new ObjectFactory();
        AuxiliarCtas auxiliarCtas = of.createAuxiliarCtas();

        auxiliarCtas.setAnio(Integer.parseInt(erpSatAuxiliarCtas.getAnio()));
        auxiliarCtas.setMes(erpSatAuxiliarCtas.getMes());
        auxiliarCtas.setRFC(erpSatAuxiliarCtas.getRfc());
        auxiliarCtas.setVersion(erpSatAuxiliarCtas.getVersiones());
        auxiliarCtas.setTipoSolicitud(erpSatAuxiliarCtas.getTipoSolicitud());
        auxiliarCtas.setNumOrden(erpSatAuxiliarCtas.getNumOrden());
        auxiliarCtas.setNumTramite(erpSatAuxiliarCtas.getNumTramite());
        
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
                  
                  

            while (itT.hasNext()) {
               AuxiliarCtas.Cuenta cuenta = new AuxiliarCtas.Cuenta();
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
            
         
                while (iteDet.hasNext()) {
                    AuxiliarCtas.Cuenta.DetalleAux detalleAux = new AuxiliarCtas.Cuenta.DetalleAux();
                    AuxNumCtaDetDTO ctasDet = iteDet.next();
                   //Detalle del auxiliar
                    
                      //System.out.println("numero de polizas"+ctasDet.getNumUnidenPol());
                    
                      DatatypeFactory datatypeFactory = DatatypeFactory.newInstance();
                        GregorianCalendar calendar = new GregorianCalendar();

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

                        XMLGregorianCalendar xmlCalendar = datatypeFactory.newXMLGregorianCalendar(calendar);

                  
                    detalleAux.setFecha(xmlCalendar);
                    detalleAux.setNumUnIdenPol(ctasDet.NUM_UNIDEN_POL);
                    detalleAux.setDebe(new BigDecimal(ctasDet.DEBE));
                    detalleAux.setHaber(new BigDecimal(ctasDet.HABER));
                    detalleAux.setConcepto(ctasDet.CONCEPTO);
                    
                    
                    
                    cuenta.getDetalleAux().add(detalleAux);            
            
                }
              }
             auxiliarCtas.getCuenta().add(cuenta);
            
            }
            
           }
            
           
            
          
           
            //Genera  el XML
            AuxiliarCtasv11 auxiliarCtasv11 = new AuxiliarCtasv11(auxiliarCtas, "com.feraz.sat.contabilidad_electronica.auxiliar.cuentas");

            String dir = dirOut + name + ".xml";
//            System.out.println(dir);
            FileOutputStream out = new FileOutputStream(dir);
            auxiliarCtasv11.guardar(out);
        //    auxiliarCtasv11.validar();
            out.close();

            
            val = Files.toString(new File(dir), Charsets.UTF_8);
       
        } catch (Exception e) {
            log.error("Error auxiliar cuentas",e);
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
