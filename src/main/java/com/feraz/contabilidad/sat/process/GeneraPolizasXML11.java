
package com.feraz.contabilidad.sat.process;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.feraz.contabilidad.sat.electronica.dto.TransaccionDTO;
import com.feraz.contabilidad.sat.electronica.model.ErpSatPoliza;
import com.feraz.contabilidad.sat.electronica.model.ErpSatPolizas;
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
import mx.bigdata.sat.ce_polizas_periodo.schema.CMoneda;
import mx.bigdata.sat.ce_polizas_periodo.schema.Polizas;
import mx.bigdata.sat.ce_polizas_periodo.schema.ObjectFactory;
import mx.bigdata.sat.contabilidad_electronica.PolizasPeriodov11;
import org.jamh.data.process.ProcessDao;
import org.springframework.beans.factory.annotation.Value;

/**
 *
 * @author Ing. JAMH
 */
public class GeneraPolizasXML11 {

    @Value("${document.file.dirXMLSAT}")
    private String dirOut;
    private ProcessDao processDao;

    public Polizas procesa() {
        ObjectFactory of = new ObjectFactory();
        Polizas poliza = of.createPolizas();

        return poliza;
    }

    public Polizas procesa2(ErpSatPolizas erpSatPolizas, List<ErpSatPoliza> lista, String name) {

        ObjectFactory of = new ObjectFactory();
        Polizas poliza = of.createPolizas();
        String val = null;
        
       

        //Datos generales de la poliza  o nodo principal
        poliza.setAnio(Integer.parseInt(erpSatPolizas.getAnio()));
        poliza.setCertificado(erpSatPolizas.getCertificado());
        poliza.setMes(erpSatPolizas.getMes());
        poliza.setNoCertificado(erpSatPolizas.getNoCertificado());
        poliza.setNumOrden(erpSatPolizas.getNumOrden());
        poliza.setNumTramite(erpSatPolizas.getNumTramite());
        poliza.setRFC(erpSatPolizas.getRfc());
        poliza.setTipoSolicitud(erpSatPolizas.getTipoSolicitud());
        poliza.setVersion(erpSatPolizas.getVersiones());
        poliza.setSello(erpSatPolizas.getSello());

        try {
          //Datos de la poliza iterar para que se vea las polizas maestro de polizas

              SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy");
            // SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
            Iterator<ErpSatPoliza> it = lista.iterator();
            
            // Polizas.Poliza.Transaccion tr = of.createPolizasPolizaTransaccion();
                     //poliza.getPoliza().get(0).getTransaccion().add(tr)
            // Polizas.Poliza.Transaccion.CompNal compNal =   of.createPolizasPolizaTransaccionCompNal();
             //   poliza.getPoliza().get(0).getTransaccion() = new ArrayList();
             // GregorianCalendar gregorianCalendar;
            // XMLGregorianCalendar resultFecha = null;

            while (it.hasNext()) {
                Polizas.Poliza pol = new Polizas.Poliza();
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

                XMLGregorianCalendar xmlCalendar = datatypeFactory.newXMLGregorianCalendar(calendar);

                   // Date t = df.parse(pl.getFecha().toString());
                // gregorianCalendar = (GregorianCalendar)GregorianCalendar.getInstance();
                // gregorianCalendar.setTime(t);
                // resultFecha = DatatypeFactory.newInstance().newXMLGregorianCalendar(gregorianCalendar);
                //   XMLGregorianCalendar xgc=DatatypeFactory.newInstance().newXMLGregorianCalendar(pl.getFecha().toString());
                pol.setConcepto(pl.getConcepto());
                pol.setFecha(xmlCalendar);
                
                
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
                  
                  List<TransaccionDTO> listaTran = mapper.readValue(data,
                    mapper.getTypeFactory().constructCollectionType(List.class,
                            TransaccionDTO.class));
                

                  Iterator<TransaccionDTO> itT = listaTran.iterator();
                  
                  

            while (itT.hasNext()) {
                Polizas.Poliza.Transaccion tr = new Polizas.Poliza.Transaccion();
              TransaccionDTO ss = itT.next();
                //System.out.println("Entrando a generar transaccion"); 
               System.out.println("itT"+ss.cuenta);
                //Agregar la transaccion
               System.out.println("----------Generando transaccion---------------");
               
               System.out.println("ss.cuenta"+ss.cuenta);
               System.out.println("ss.desCta"+ss.desCta);
               System.out.println("ss.descripcion"+ ss.descripcion);
               
               System.out.println("----------------------------------------------");
               
                tr.setNumCta(ss.cuenta);
                tr.setDesCta(ss.desCta);
                tr.setConcepto(ss.descripcion);
                tr.setDebe(new BigDecimal(ss.cargos));
                tr.setHaber(new BigDecimal(ss.abonos));
              
                
                     
//                         System.out.println("CMoneda.AED.toString()"+CMoneda.AED.toString());
//                           CMoneda moneda = CMoneda.valueOf(ss.moneda);
//                           
//                          System.out.println("moneda"+moneda);
                            
                        
                        
                    //    moneda.
             //Esto por el moemento es un if poara estas opciones dependiendo lo que tenga      
                       

                if (ss.tipo.equalsIgnoreCase("CompNal")){
                    
                    Polizas.Poliza.Transaccion.CompNal compNal = new Polizas.Poliza.Transaccion.CompNal();
                    compNal.setUUIDCFDI(ss.uuidCfdi);
                    
                    if (ss.moneda == ""){
                         
                        compNal.setMoneda(CMoneda.valueOf("MXN"));
                        
                    }else{
                    
                        
                        compNal.setMoneda(CMoneda.valueOf(ss.moneda));
                    }
                    
                    compNal.setMontoTotal(new BigDecimal(ss.montoTotal));
                    compNal.setRFC(ss.rfc);
                    compNal.setTipCamb(new BigDecimal(ss.tipCamb));
                    
                   // System.out.println("Agregando COMPNAL"+ tr.getNumCta());
                    
                    tr.getCompNal().add(compNal);
                }
                if (ss.tipo.equalsIgnoreCase("CompNalOtr")){
                    Polizas.Poliza.Transaccion.CompNalOtr compNalOtr= new Polizas.Poliza.Transaccion.CompNalOtr() ;
                    
                    compNalOtr.setCFDCBBNumFol(new BigInteger(ss.cfdCbbNumFol));
                    compNalOtr.setCFDCBBSerie(ss.cfdCbbSerie);
                    compNalOtr.setMoneda(CMoneda.valueOf(ss.moneda));
                    compNalOtr.setMontoTotal(new BigDecimal(ss.montoTotal));
                    compNalOtr.setRFC(ss.rfc);
                    compNalOtr.setTipCamb(new BigDecimal(ss.tipCamb));
     
                    tr.getCompNalOtr().add(compNalOtr);
                }
                
                if (ss.tipo.equalsIgnoreCase("CompExt")){
                
                    Polizas.Poliza.Transaccion.CompExt compExt = new Polizas.Poliza.Transaccion.CompExt();
                    System.out.println("CMoneda.AED.toString()"+CMoneda.AED.toString());
                    compExt.setMoneda(CMoneda.valueOf(ss.moneda));
                    compExt.setMontoTotal(new BigDecimal(ss.montoTotal));
                    compExt.setNumFactExt(ss.numFactExt);
                    compExt.setTaxID(ss.taxId);
                    compExt.setTipCamb(new BigDecimal(ss.tipCamb));
                    
                    
                    tr.getCompExt().add(compExt);
                }
                if(ss.tipo.equalsIgnoreCase("Cheque")){
                    Polizas.Poliza.Transaccion.Cheque cheque = new Polizas.Poliza.Transaccion.Cheque();
                    
                    cheque.setBanEmisExt(ss.banEmisExt);
                    cheque.setBanEmisNal(ss.banEmisNal);
                    cheque.setBenef(ss.benef);
                    cheque.setCtaOri(ss.ctaOri);
                    
                    GregorianCalendar calendar2 = new GregorianCalendar();

                     calendar2.clear();

                     Calendar parsedCalendar2 = Calendar.getInstance();

                     Date rawDate2 = formato.parse(ss.fecha);
                     parsedCalendar2.setTime(rawDate2);

                     calendar2.set(parsedCalendar2.get(Calendar.YEAR),
                        parsedCalendar2.get(Calendar.MONTH),
                        parsedCalendar2.get(Calendar.DATE));

                   XMLGregorianCalendar xmlCalendar2 = datatypeFactory.newXMLGregorianCalendar(calendar2);
                    
                    cheque.setFecha(xmlCalendar2);
                    cheque.setMoneda(CMoneda.valueOf(ss.moneda));
                    cheque.setMonto(new BigDecimal(ss.montoTotal));
                    cheque.setNum(ss.num);
                    cheque.setRFC(ss.rfc);
                    cheque.setTipCamb(new BigDecimal(ss.tipCamb));
                    
                    
                    tr.getCheque().add(cheque);
                }
                if(ss.tipo.equalsIgnoreCase("Transferen")){
                    Polizas.Poliza.Transaccion.Transferencia transferencia= new Polizas.Poliza.Transaccion.Transferencia();
                    
                      transferencia.setBancoDestExt(ss.bancoDestExt);
                      transferencia.setBancoDestNal(ss.bancoDestNal);
                      transferencia.setBancoOriExt(ss.bancoOriExt);
                      transferencia.setBancoOriNal(ss.bancoOriNal);
                      transferencia.setBenef(ss.benef);
                      transferencia.setCtaDest(ss.ctaDest);
                      transferencia.setCtaOri(ss.ctaOri);
                      GregorianCalendar calendar2 = new GregorianCalendar();

                     calendar2.clear();

                     Calendar parsedCalendar2 = Calendar.getInstance();

                     Date rawDate2 = formato.parse(ss.fecha);
                     parsedCalendar2.setTime(rawDate2);

                     calendar2.set(parsedCalendar2.get(Calendar.YEAR),
                        parsedCalendar2.get(Calendar.MONTH),
                        parsedCalendar2.get(Calendar.DATE));

                     XMLGregorianCalendar xmlCalendar2 = datatypeFactory.newXMLGregorianCalendar(calendar2);
                    
                    
                      transferencia.setFecha(xmlCalendar2);
                      
                      if (ss.moneda == ""){
                          
                          transferencia.setMoneda(CMoneda.valueOf("MXN"));
                      
                      }else{
                              
                         transferencia.setMoneda(CMoneda.valueOf(ss.moneda));     
                              
                      }
                         
                      transferencia.setMonto(new BigDecimal(ss.montoTotal));
                      transferencia.setRFC(ss.rfc);
                      transferencia.setTipCamb(new BigDecimal(ss.tipCamb));
                      
                    
                    
                    tr.getTransferencia().add(transferencia);
                }
                if(ss.tipo.equalsIgnoreCase("OtrMetodoP")){
                    Polizas.Poliza.Transaccion.OtrMetodoPago  otrMetodoPago= new Polizas.Poliza.Transaccion.OtrMetodoPago();
                    
                    otrMetodoPago.setBenef(ss.benef);
                     GregorianCalendar calendar2 = new GregorianCalendar();

                     calendar2.clear();

                     Calendar parsedCalendar2 = Calendar.getInstance();

                     Date rawDate2 = formato.parse(ss.fecha);
                     parsedCalendar2.setTime(rawDate2);

                     calendar2.set(parsedCalendar2.get(Calendar.YEAR),
                        parsedCalendar2.get(Calendar.MONTH),
                        parsedCalendar2.get(Calendar.DATE));

                     XMLGregorianCalendar xmlCalendar2 = datatypeFactory.newXMLGregorianCalendar(calendar2);
                    otrMetodoPago.setFecha(xmlCalendar2);
                    otrMetodoPago.setMetPagoPol(ss.metPagoPol);
                    otrMetodoPago.setMoneda(CMoneda.valueOf(ss.moneda));
                    otrMetodoPago.setMonto(new BigDecimal(ss.montoTotal));
                    otrMetodoPago.setRFC(ss.rfc);
                    otrMetodoPago.setTipCamb(new BigDecimal(ss.tipCamb));
                    
                    
                    tr.getOtrMetodoPago().add(otrMetodoPago);
                }

               // System.out.println("Agregando a transaccion");
                 pol.getTransaccion().add(tr);
                 
                // System.out.println("tr cuenta"+tr.getNumCta());
                 //System.out.println("tr compnal"+tr.getCompNal().get(0));
                 
                 
            }
              
               poliza.getPoliza().add(pol);
    
            }//else{
                 //pol.getTransaccion().add(tr);
             // }
           
          }
//            System.out.println(" poliza.getPoliza().size:"+ poliza.getPoliza().isEmpty());
//            System.out.println(" poliza.getPoliza().size:"+ poliza.getPoliza().size());
            
            if(!poliza.getPoliza().isEmpty() || poliza.getPoliza().size()>0){
            PolizasPeriodov11 polizas = new PolizasPeriodov11(poliza, "mx.bigdata.sat.ce_polizas_periodo.schema");
            String dir =dirOut+name+".xml";
            FileOutputStream out = new FileOutputStream(dir);
            polizas.guardar(out);
         //   polizas.vaar();
            out.close();
             }else{
                System.out.println("No se encontraron detalles");
                return null;
            }
//            // String text = Files.toString(new File(path), Charsets.UTF_8);
            //val = Files.toString(new File(dir), Charsets.UTF_8);
            // System.out.println("val:"+val);
            //catalogo = catalogoCuentas.
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }

        return poliza;
    }

    public void setDirOut(String dirOut) {
        this.dirOut = dirOut;
    }

    public void setProcessDao(ProcessDao processDao) {
        this.processDao = processDao;
    }

    
}
