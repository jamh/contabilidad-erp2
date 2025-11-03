/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.feraz.facturas.webcontrolfe.controller;

/**
 *
 * @author Feraz3
 */
import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonToken;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.feraz.contabilidad.sat.transacciones.dao.DetPolizasXTransaccionDao;
import com.feraz.contabilidad.sat.transacciones.dao.ErpSatTransaccionDao;
import com.feraz.contabilidad.sat.transacciones.model.DetPolizasXTransaccion;
import com.feraz.contabilidad.sat.transacciones.model.DetPolizasXTransaccionId;
import com.feraz.contabilidad.sat.transacciones.model.ErpSatTransaccion;
import com.feraz.contabilidad.sat.transacciones.model.ErpSatTransaccionId;
import com.feraz.cxp.dao.ErpSeguiDocumentosDao;
import com.feraz.cxp.dto.PagosAllDTO;
import com.feraz.cxp.model.ErpSeguiDocumentos;
import com.feraz.cxp.model.ErpSeguiDocumentosId;
import com.feraz.facturas.webcontrolfe.dao1.ErpCpRelacionFacturasDao;
import com.feraz.facturas.webcontrolfe.dao1.ErpFeComprobantesDao;
import com.feraz.facturas.webcontrolfe.dto.PolizasXPagosDTO;
import com.feraz.facturas.webcontrolfe.dto.PolizasxFacturasDTO;
import com.feraz.facturas.webcontrolfe.dto.RelacionPolizas;
import com.feraz.polizas3.dao.ErpPolizasXFacturasDao;
import com.feraz.polizas3.model.ErpPolizasXFacturas;
import com.feraz.polizas3.model.ErpPolizasXFacturasId;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import com.feraz.facturas.webcontrolfe.dto.RelacionCxpDTO;
import com.feraz.facturas.webcontrolfe.model.ErpCpRelacionFacturas;
import com.feraz.facturas.webcontrolfe.model.ErpCpRelacionFacturasId;
import com.feraz.facturas.webcontrolfe.model.ErpFeComprobantes;
import com.feraz.facturas.webcontrolfe.model.ErpFeComprobantesId;
import com.feraz.polizas3.dao.ErpPolizasXPagosDao;
import com.feraz.polizas3.model.ErpPolizasXPagos;
import com.feraz.polizas3.model.ErpPolizasXPagosId;
import java.math.BigDecimal;
import java.text.DateFormat;
import java.util.Date;
import org.jamh.data.process.ProcessDao;
import org.jamh.wf.json.model.ResponseQuery;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.context.request.WebRequest;

/**
 *
 * @author Ing. JAMH
 */
@Controller
@RequestMapping("/relacionpoliza")
@SessionAttributes({"compania", "usuario"})
public class RelacionFXP {
    
    private ProcessDao processDao;
    private ErpPolizasXFacturasDao erpPolizasXFacturasDao;
    private ErpCpRelacionFacturasDao erpCpRelacionFacturasDao;
    private ErpFeComprobantesDao erpFeComprobantesDao;
    private ErpSatTransaccionDao erpSatTransaccionDao;
    private DetPolizasXTransaccionDao detPolizasXTransaccionDao;
    private ErpPolizasXPagosDao erpPolizasXPagosDao;
    private ErpSeguiDocumentosDao erpSeguiDocumentosDao;
    
    @RequestMapping(value = "/facturasxpoliza", method = RequestMethod.POST)
    public @ResponseBody
    ResponseQuery registro(String data, @RequestParam("tipo_pol") String tipo_pol, @RequestParam("numero") String numero, @RequestParam("fecha") String fecha, WebRequest webRequest, Model model) {


//        System.out.println("tipo_pol:" + tipo_pol);
//        System.out.println("numero:" + numero);
//        System.out.println("fecha:" + fecha);
//        System.out.println("data:" + data);
       // Map parametros = processDao.paramToMap(webRequest.getParameterMap());

        ResponseQuery rq = new ResponseQuery();

        String compania = model.asMap().get("compania").toString();
        
//        System.out.println("compania:" + compania);

        try {
            ObjectMapper mapper = new ObjectMapper();
            JsonFactory f = new JsonFactory();
            JsonParser jp = f.createJsonParser(data);
            // System.out.println("Data:"+getData());
            // advance stream to START_ARRAY first:
            jp.nextToken();
          //  List<CaBioUsuarioXDisp> lis = new ArrayList<CaBioUsuarioXDisp>();
            int i = 1;
            //ScanUser su = new ScanUser();
            String mensaje = "";
            String mensaje2 = "";
            while (jp.nextToken() == JsonToken.START_OBJECT) {
                RelacionPolizas ss = mapper.readValue(jp, RelacionPolizas.class);
                
                
                
               
            
//               System.out.println("NO_CASO: "+ss.noCaso);
               
               SimpleDateFormat formatFecha = new SimpleDateFormat("dd/MM/yyyy");
               
                Calendar calendar=Calendar.getInstance();
                calendar.setTime(formatFecha.parse(fecha));
                int month=0;
                int year = 0;
                
//                 System.out.println("fecha: "+fecha);
//
//                System.out.println("fecha: "+formatFecha.parse(fecha));
                
                  month=calendar.get(Calendar.MONTH);
                  year=calendar.get(Calendar.YEAR);
                  
//                   System.out.println("mes: "+month+" , año: "+year);
               
                   String query1 = "BuscaPeriodoRelacion";
           
            
                 Map param = new HashMap();
                  param.put("compania", compania);
                  param.put("periodo", month + 1);
                   param.put("calendario", year);
        
       
                   List list1 = processDao.getMapResult(query1, param);
                   
                   if (list1 == null || list1.size() <= 0){
                       
//                       System.out.println("periodo no valido");
                       
                        rq.setSuccess(false);
                        rq.setMsg("Periodo no valido");
                       return rq;
                       
                   }
                   
                // String [] array = list.get(o);
                 Map result1 = (HashMap) list1.get(0);
//                  System.out.println("select:" + result1.get("ESTATUS"));
                  
                    String estatus = result1.get("ESTATUS").toString();

                   if (estatus.equalsIgnoreCase("0")){

                   }else{
                      
                        rq.setSuccess(false);
                        rq.setMsg("No se pudo relacionar. El periodo esta cerrado");
                       return rq;
                   }
                  
               
               
               ErpPolizasXFacturasId pxfeid= new ErpPolizasXFacturasId();
            
            pxfeid.setCompania(compania);
            pxfeid.setFechaPol(formatFecha.parse(fecha));
            pxfeid.setNumeroPol(numero);
            pxfeid.setTipoPol(tipo_pol);
            pxfeid.setOperacion("GE");
           
          //  pxfeid.setIdFactura(pxfeid);
            
            ErpPolizasXFacturas pxfe= new ErpPolizasXFacturas();
            pxfe.setId(pxfeid);
            pxfe.setNumeroFe(Long.parseLong(ss.numero));

            pxfe.setUuid(ss.uuid);
             pxfe.setFolio(ss.folio);
            if(ss.numeroPol == "" || ss.numeroPol == null){
            ErpPolizasXFacturasId relacion = erpPolizasXFacturasDao.save(pxfe);
            
           
              ErpSatTransaccion erpTrans = new ErpSatTransaccion();
              ErpSatTransaccionId erpTransId = new ErpSatTransaccionId();
              
              erpTransId.setCompania(compania);
              int idTransaccion = erpSatTransaccionDao.getMaxId(erpTransId);
              erpTransId.setId(new BigDecimal(idTransaccion));
              erpTrans.setId(erpTransId);
              erpTrans.setTipo("CompNal");
              erpTrans.setUuidCfdi(ss.uuid);
              
              
              Map rfcCompania = new HashMap();
              //String rfcComp = "";
              rfcCompania.put("compania", compania);
               
              List listRfc = processDao.getMapResult("BuscaRFCCompania", rfcCompania);
              
                 if (listRfc == null){
                     
                     erpTrans.setRfc(ss.efcEmisor);

                     
                 }else{
                     
                     Map rfcComp = (HashMap) listRfc.get(0);
                     
                     if (rfcComp.get("RFC").toString().equalsIgnoreCase(ss.efcEmisor)){
                     
                         
                          erpTrans.setRfc(ss.rfcReceptor);
                         
                     }else{
                     
                            erpTrans.setRfc(ss.efcEmisor);
                     
                     }
                     
                 }
                  
                 
                 erpTrans.setMontoTotal(new BigDecimal(ss.total));
                 
                 if (!ss.moneda.equalsIgnoreCase("MXN") && !ss.moneda.equalsIgnoreCase("USD")){
                     
                     erpTrans.setMoneda("MXN");
                 
                 
                 }else{
                     
                     erpTrans.setMoneda(ss.moneda);
                 
                 }
                  if (ss.tipoCambio.equalsIgnoreCase("N/A") || ss.tipoCambio == null || ss.tipoCambio.isEmpty() || ss.tipoCambio.toString().equalsIgnoreCase("") ){
                     
                     System.out.println("Valor 1 a tipo de cambio");
                      erpTrans.setTipCamb(new BigDecimal(1));
                 }else{
                     
                      System.out.println("Valor que traiga a tipo de cambio "+ss.tipoCambio);
                     
                     erpTrans.setTipCamb(new BigDecimal(ss.tipoCambio));
                 }
                   
              // if (rfcComp.get("CUENTA_TITULO").toString().equalsIgnoreCase("000001")){
              
            
                 
                 
                 erpSatTransaccionDao.save(erpTrans);
                 
                     
                    Map secDetPol = new HashMap();
              //String rfcComp = "";
              secDetPol.put("compania", compania);
              secDetPol.put("tipo_poliza", tipo_pol);
              secDetPol.put("fecha", fecha);
              secDetPol.put("numero", numero);
              
               
              List listsecDetPol = processDao.getMapResult("BuscaSecDetPoliza", secDetPol);
              
           DetPolizasXTransaccion transaccion = new DetPolizasXTransaccion();
           DetPolizasXTransaccionId transaccionId = new DetPolizasXTransaccionId();
              
              for (int j = 0; listsecDetPol.size()-1>= j;j++){
                  
                  Map secDetPolS = (HashMap) listsecDetPol.get(j);
                   
                 System.out.println("sedc: "+secDetPolS.get("SEC").toString());
                 
                  transaccionId.setCompania(compania);
                    transaccionId.setFecha(formatFecha.parse(fecha));
                    transaccionId.setTipoPoliza(tipo_pol);
                    transaccionId.setNumero(numero);
                    transaccionId.setSec(Integer.parseInt(secDetPolS.get("SEC").toString()));
                    transaccionId.setIdTransaccion(idTransaccion);
                    transaccion.setId(transaccionId);

                    detPolizasXTransaccionDao.save(transaccion);
            
              }
            
            
               
            }else{
                //mensaje2 = 
                mensaje += ss.folio+", ";
                mensaje2 = ". Las facturas con folio: "+mensaje+" ya cuentan con una poliza relacionada";
                
              
            }
           
            rq.setSuccess(true);
            rq.setMsg("Poliza relacionada"+mensaje2);
            
            
               
                
              
               
               i++;
           }
            
             
        } catch (Exception e) {
             rq.setSuccess(false);
            rq.setMsg("Error al relacionar poliza");
            e.printStackTrace();
            return rq;
        }

        return rq;

    }
    
      @RequestMapping(value = "/deletefacturasxpoliza", method = RequestMethod.POST)
    public @ResponseBody
    ResponseQuery delete(String data, WebRequest webRequest, Model model) {


      
        System.out.println("data:" + data);
       // Map parametros = processDao.paramToMap(webRequest.getParameterMap());

        ResponseQuery rq = new ResponseQuery();

        String compania = model.asMap().get("compania").toString();
        
//        System.out.println("compania:" + compania);

        try {
            ObjectMapper mapper = new ObjectMapper();
            JsonFactory f = new JsonFactory();
            JsonParser jp = f.createJsonParser(data);
            // System.out.println("Data:"+getData());
            // advance stream to START_ARRAY first:
            jp.nextToken();
          //  List<CaBioUsuarioXDisp> lis = new ArrayList<CaBioUsuarioXDisp>();
            int i = 1;
            //ScanUser su = new ScanUser();
            String mensaje = "";
            String mensaje2 = "";
            while (jp.nextToken() == JsonToken.START_OBJECT) {
                RelacionPolizas ss = mapper.readValue(jp, RelacionPolizas.class);
                
            
               System.out.println("NO_CASO: "+ss.noCaso);
               
               SimpleDateFormat formatFecha = new SimpleDateFormat("dd/MM/yyyy");
               
                Calendar calendar=Calendar.getInstance();
                calendar.setTime(formatFecha.parse(ss.fechaPol));
                int month=0;
                int year = 0;
                
//                 System.out.println("fecha: "+ss.fechaPol);

//                System.out.println("fecha: "+formatFecha.parse(ss.fechaPol));
                
                  month=calendar.get(Calendar.MONTH);
                  year=calendar.get(Calendar.YEAR);
                  
//                   System.out.println("mes: "+month+" , año: "+year);
               
                   String query1 = "BuscaPeriodoRelacion";
           
            
                 Map param = new HashMap();
                  param.put("compania", compania);
                  param.put("periodo", month + 1);
                   param.put("calendario", year);
        
       
                   List list1 = processDao.getMapResult(query1, param);
                   
                   if (list1 == null || list1.size() <= 0){
                       
                       System.out.println("periodo no valido");
                       
                        rq.setSuccess(false);
                        rq.setMsg("Periodo no valido");
                       return rq;
                       
                   }
                   
                // String [] array = list.get(o);
                 Map result1 = (HashMap) list1.get(0);
//                  System.out.println("select:" + result1.get("ESTATUS"));
                  
                    String estatus = result1.get("ESTATUS").toString();

                   if (estatus.equalsIgnoreCase("0")){

                   }else{
//                      System.out.println("estatus no es igual a cero");
                        rq.setSuccess(false);
                        rq.setMsg("No se pudo quitar la relacionar. El periodo esta cerrado");
                       return rq;
                   }
               
               
               ErpPolizasXFacturasId pxfeid= new ErpPolizasXFacturasId();
            pxfeid.setCompania(compania);
            pxfeid.setFechaPol(formatFecha.parse(ss.fechaPol));
            pxfeid.setNumeroPol(ss.numeroPol);
            pxfeid.setTipoPol(ss.tipoPoliza);
            pxfeid.setOperacion("GE");
           
            System.out.println(ss.fechaPol);
            System.out.println(formatFecha.parse(ss.fechaPol));
            
          //  pxfeid.setIdFactura(pxfeid);
            
            ErpPolizasXFacturas pxfe= new ErpPolizasXFacturas();
            pxfe.setId(pxfeid);
//            pxfe.setNumeroFe(Long.parseLong(ss.numero));
//
            pxfe.setUuid(ss.uuid);
             pxfe.setFolio(ss.folio);
           
            Boolean relacion = erpPolizasXFacturasDao.deleteRelacion(pxfe);
            
            
             
              Map IdTransaccion = new HashMap();
              //String rfcComp = "";
              IdTransaccion.put("compania", compania);
              IdTransaccion.put("uuid", ss.uuid);
               
              List listIdTransaccion = processDao.getMapResult("BuscaIDTransaccion", IdTransaccion);
              
              if (listIdTransaccion != null){
                  
                   Map idTransaccionUuid = (HashMap) listIdTransaccion.get(0);
                  
                   ErpSatTransaccion erpTrans = new ErpSatTransaccion();
                   ErpSatTransaccionId erpTransId = new ErpSatTransaccionId();
              
                    erpTransId.setCompania(compania);
                    erpTransId.setId(new BigDecimal(idTransaccionUuid.get("ID").toString()));
                    erpTrans.setId(erpTransId);

                    erpSatTransaccionDao.delete(erpTrans);
                    
                    
                    Map secDetPol = new HashMap();
              //String rfcComp = "";
              secDetPol.put("compania", compania);
              secDetPol.put("tipo_poliza", ss.tipoPoliza);
              secDetPol.put("fecha", ss.fechaPol);
              secDetPol.put("numero", ss.numeroPol);
              
               
              List listsecDetPol = processDao.getMapResult("BuscaSecDetPoliza", secDetPol);
              
           DetPolizasXTransaccion transaccion = new DetPolizasXTransaccion();
           DetPolizasXTransaccionId transaccionId = new DetPolizasXTransaccionId();
              
              for (int j = 0; listsecDetPol.size()-1>= j;j++){
                  
                  Map secDetPolS = (HashMap) listsecDetPol.get(j);
                   
                 System.out.println("sedc: "+secDetPolS.get("SEC").toString());
                 
                  transaccionId.setCompania(compania);
                    transaccionId.setFecha(formatFecha.parse(ss.fechaPol));
                    transaccionId.setTipoPoliza(ss.tipoPoliza);
                    transaccionId.setNumero(ss.numeroPol);
                    transaccionId.setSec(Integer.parseInt(secDetPolS.get("SEC").toString()));
                    transaccionId.setIdTransaccion(Integer.parseInt(idTransaccionUuid.get("ID").toString()));
                    transaccion.setId(transaccionId);

                    detPolizasXTransaccionDao.borraTransaccionXPolizaCompl(transaccion);
            
              }
              
              }
              
               
              
              
              
            
            
         
           if(relacion == true){
            rq.setSuccess(true);
            rq.setMsg("Se quito la relacion correctamente");
           }else{
               
               rq.setSuccess(false);
            rq.setMsg("Fallo al quitar relacion");
           
           }
            
               
                
              
               
               i++;
           }
            
             
        } catch (Exception e) {
             rq.setSuccess(false);
            rq.setMsg("Error al relacionar poliza");
            e.printStackTrace();
            return rq;
        }

        return rq;

    }
    
    @RequestMapping(value = "/deletefacturasxpoliza2", method = RequestMethod.POST)
    public @ResponseBody
    ResponseQuery delete2(String data, String data2, WebRequest webRequest, Model model) {


      
        System.out.println("data:" + data);
        System.out.println("data2:" + data2);
       // Map parametros = processDao.paramToMap(webRequest.getParameterMap());

        ResponseQuery rq = new ResponseQuery();

        String compania = model.asMap().get("compania").toString();
        
//        System.out.println("compania:" + compania);
        
        try {
            ObjectMapper mapper = new ObjectMapper();
            JsonFactory f = new JsonFactory();
            JsonParser jp = f.createJsonParser(data);
            // System.out.println("Data:"+getData());
            // advance stream to START_ARRAY first:
            
            jp.nextToken();
            
            ObjectMapper mapper2 = new ObjectMapper();
            JsonFactory f2 = new JsonFactory();
            JsonParser jp2 = f2.createJsonParser(data2);
            
            jp2.nextToken();
          //  List<CaBioUsuarioXDisp> lis = new ArrayList<CaBioUsuarioXDisp>();
            int i = 1;
            //ScanUser su = new ScanUser();
            //PolizasxFacturasDTO
            String mensaje = "";
            String mensaje2 = "";
            while (jp.nextToken() == JsonToken.START_OBJECT) {
                RelacionPolizas ss = mapper.readValue(jp, RelacionPolizas.class);
                
                while (jp2.nextToken() == JsonToken.START_OBJECT) {  
                    PolizasxFacturasDTO ss2 = mapper2.readValue(jp2, PolizasxFacturasDTO.class);
                    
                        System.out.println("NO_CASO: "+ss.noCaso);
                        System.out.println("ss2.fecha_pol_rel: "+ss2.fecha_pol_rel);

                        SimpleDateFormat formatFecha = new SimpleDateFormat("dd/MM/yyyy");

                         Calendar calendar=Calendar.getInstance();
                         calendar.setTime(formatFecha.parse(ss2.fecha_pol_rel));
                         int month=0;
                         int year = 0;

                           month=calendar.get(Calendar.MONTH);
                           year=calendar.get(Calendar.YEAR);

                            String query1 = "BuscaPeriodoRelacion";


                          Map param = new HashMap();
                           param.put("compania", compania);
                           param.put("periodo", month + 1);
                            param.put("calendario", year);


                            List list1 = processDao.getMapResult(query1, param);

                            if (list1 == null || list1.size() <= 0){

                                System.out.println("periodo no valido");

                                 rq.setSuccess(false);
                                 rq.setMsg("Periodo no valido");
                                return rq;

                            }

                          Map result1 = (HashMap) list1.get(0);


                             String estatus = result1.get("ESTATUS").toString();

                            if (estatus.equalsIgnoreCase("0")){

                            }else{
         //                      System.out.println("estatus no es igual a cero");
                                 rq.setSuccess(false);
                                 rq.setMsg("No se pudo quitar la relacionar. El periodo esta cerrado");
                                return rq;
                            }


                        ErpPolizasXFacturasId pxfeid= new ErpPolizasXFacturasId();
                     pxfeid.setCompania(compania);
                     pxfeid.setFechaPol(formatFecha.parse(ss2.fecha_pol_rel));
                     pxfeid.setNumeroPol(ss2.num_pol_rel);
                     pxfeid.setTipoPol(ss2.tipo_pol_rel);
                     pxfeid.setOperacion("GE");

                     System.out.println(ss2.fecha_pol_rel);
                     System.out.println(formatFecha.parse(ss2.fecha_pol_rel));

                   //  pxfeid.setIdFactura(pxfeid);

                     ErpPolizasXFacturas pxfe= new ErpPolizasXFacturas();
                     pxfe.setId(pxfeid);
         //            pxfe.setNumeroFe(Long.parseLong(ss.numero));
         //
                     pxfe.setUuid(ss.uuid);
                      pxfe.setFolio(ss.folio);

                     Boolean relacion = erpPolizasXFacturasDao.deleteRelacion(pxfe);



                       Map IdTransaccion = new HashMap();
                       //String rfcComp = "";
                       IdTransaccion.put("compania", compania);
                       IdTransaccion.put("uuid", ss.uuid);

                       List listIdTransaccion = processDao.getMapResult("BuscaIDTransaccion", IdTransaccion);

                       if (listIdTransaccion != null){

                            Map idTransaccionUuid = (HashMap) listIdTransaccion.get(0);

                            ErpSatTransaccion erpTrans = new ErpSatTransaccion();
                            ErpSatTransaccionId erpTransId = new ErpSatTransaccionId();

                             erpTransId.setCompania(compania);
                             erpTransId.setId(new BigDecimal(idTransaccionUuid.get("ID").toString()));
                             erpTrans.setId(erpTransId);

                             //erpSatTransaccionDao.delete(erpTrans);


                     //        Map secDetPol = new HashMap();
                       //String rfcComp = "";
                     //  secDetPol.put("compania", compania);
                     //  secDetPol.put("tipo_poliza", ss2.tipo_pol_rel);
                     //  secDetPol.put("fecha", ss2.fecha_pol_rel);
                     //  secDetPol.put("numero", ss2.num_pol_rel);


                     //  List listsecDetPol = processDao.getMapResult("BuscaSecDetPoliza", secDetPol);

                    DetPolizasXTransaccion transaccion = new DetPolizasXTransaccion();
                    DetPolizasXTransaccionId transaccionId = new DetPolizasXTransaccionId();

                      // for (int j = 0; listsecDetPol.size()-1>= j;j++){

                        //   Map secDetPolS = (HashMap) listsecDetPol.get(j);

                        //  System.out.println("sedc: "+secDetPolS.get("SEC").toString());

                           transaccionId.setCompania(compania);
                             transaccionId.setFecha(formatFecha.parse(ss2.fecha_pol_rel));
                             transaccionId.setTipoPoliza(ss2.tipo_pol_rel);
                             transaccionId.setNumero(ss2.num_pol_rel);
                          //   transaccionId.setSec(Integer.parseInt(secDetPolS.get("SEC").toString()));
                          //   transaccionId.setIdTransaccion(Integer.parseInt(idTransaccionUuid.get("ID").toString()));
                             transaccion.setId(transaccionId);

                             detPolizasXTransaccionDao.borraTransaccionXPolizaCompl(transaccion);

                     //  }

                       }








                    if(relacion == true){
                     rq.setSuccess(true);
                     rq.setMsg("Se quito la relacion correctamente");
                    }else{

                        rq.setSuccess(false);
                     rq.setMsg("Fallo al quitar relacion");

                    }





                        i++;
                }
           }
            
             
        } catch (Exception e) {
             rq.setSuccess(false);
            rq.setMsg("Error al relacionar poliza");
            e.printStackTrace();
            return rq;
        }

        return rq;

    }
    
     @RequestMapping(value = "/deletefacturasxpagos", method = RequestMethod.POST)
    public @ResponseBody
    ResponseQuery deleteRelPagos(String data, String data2, WebRequest webRequest, Model model) {


      
        System.out.println("data:" + data);
        System.out.println("data2:" + data2);
       // Map parametros = processDao.paramToMap(webRequest.getParameterMap());

        ResponseQuery rq = new ResponseQuery();

        String compania = model.asMap().get("compania").toString();
        
//        System.out.println("compania:" + compania);
        
        try {
            ObjectMapper mapper = new ObjectMapper();
            JsonFactory f = new JsonFactory();
            JsonParser jp = f.createJsonParser(data);
            // System.out.println("Data:"+getData());
            // advance stream to START_ARRAY first:
            
            jp.nextToken();
            
            ObjectMapper mapper2 = new ObjectMapper();
            JsonFactory f2 = new JsonFactory();
            JsonParser jp2 = f2.createJsonParser(data2);
            
            jp2.nextToken();
          //  List<CaBioUsuarioXDisp> lis = new ArrayList<CaBioUsuarioXDisp>();
            int i = 1;
            //ScanUser su = new ScanUser();
            //PolizasxFacturasDTO
            String mensaje = "";
            String mensaje2 = "";
            while (jp.nextToken() == JsonToken.START_OBJECT) {
                PagosAllDTO ss = mapper.readValue(jp, PagosAllDTO.class);
                
                while (jp2.nextToken() == JsonToken.START_OBJECT) {  
                    PolizasXPagosDTO ss2 = mapper2.readValue(jp2, PolizasXPagosDTO.class);
                    
                        System.out.println("NO_CASO: "+ss.noCasoPagos);
                        System.out.println("ss2.fecha_pol_rel: "+ss2.fecha_pol_rel);

                        SimpleDateFormat formatFecha = new SimpleDateFormat("dd/MM/yyyy");

                         Calendar calendar=Calendar.getInstance();
                         calendar.setTime(formatFecha.parse(ss2.fecha_pol_rel));
                         int month=0;
                         int year = 0;

                           month=calendar.get(Calendar.MONTH);
                           year=calendar.get(Calendar.YEAR);

                            String query1 = "BuscaPeriodoRelacion";


                          Map param = new HashMap();
                           param.put("compania", compania);
                           param.put("periodo", month + 1);
                            param.put("calendario", year);


                            List list1 = processDao.getMapResult(query1, param);

                            if (list1 == null || list1.size() <= 0){

                                System.out.println("periodo no valido");

                                 rq.setSuccess(false);
                                 rq.setMsg("Periodo no valido");
                                return rq;

                            }

                          Map result1 = (HashMap) list1.get(0);


                             String estatus = result1.get("ESTATUS").toString();

                            if (estatus.equalsIgnoreCase("0")){

                            }else{
         //                      System.out.println("estatus no es igual a cero");
                                 rq.setSuccess(false);
                                 rq.setMsg("No se pudo quitar la relacionar. El periodo esta cerrado");
                                return rq;
                            }


                        ErpPolizasXPagosId pxfeid= new ErpPolizasXPagosId();
                     pxfeid.setCompania(compania);
                     pxfeid.setFechaPol(formatFecha.parse(ss2.fecha_pol_rel));
                     pxfeid.setNumeroPol(ss2.num_pol_rel);
                     pxfeid.setTipoPol(ss2.tipo_pol_rel);
                  

                     System.out.println(ss2.fecha_pol_rel);
                     System.out.println(formatFecha.parse(ss2.fecha_pol_rel));

                   //  pxfeid.setIdFactura(pxfeid);

                     ErpPolizasXPagos pxfe= new ErpPolizasXPagos();
                     pxfe.setId(pxfeid);
         //            pxfe.setNumeroFe(Long.parseLong(ss.numero));
         //
                     pxfe.setUuid(ss.uuid);
                      pxfe.setFolio(ss.folio);
                      pxfe.setOperacion("GE");

                     Boolean relacion = erpPolizasXPagosDao.deleteRelacion(pxfe);



                       Map IdTransaccion = new HashMap();
                       //String rfcComp = "";
                       IdTransaccion.put("compania", compania);
                       IdTransaccion.put("uuid", ss.uuid);

                       List listIdTransaccion = processDao.getMapResult("BuscaIDTransaccion", IdTransaccion);

                       if (listIdTransaccion != null){

                            Map idTransaccionUuid = (HashMap) listIdTransaccion.get(0);

                            ErpSatTransaccion erpTrans = new ErpSatTransaccion();
                            ErpSatTransaccionId erpTransId = new ErpSatTransaccionId();

                             erpTransId.setCompania(compania);
                             erpTransId.setId(new BigDecimal(idTransaccionUuid.get("ID").toString()));
                             erpTrans.setId(erpTransId);

                             //erpSatTransaccionDao.delete(erpTrans);


                     //        Map secDetPol = new HashMap();
                       //String rfcComp = "";
                     //  secDetPol.put("compania", compania);
                     //  secDetPol.put("tipo_poliza", ss2.tipo_pol_rel);
                     //  secDetPol.put("fecha", ss2.fecha_pol_rel);
                     //  secDetPol.put("numero", ss2.num_pol_rel);


                     //  List listsecDetPol = processDao.getMapResult("BuscaSecDetPoliza", secDetPol);

                    DetPolizasXTransaccion transaccion = new DetPolizasXTransaccion();
                    DetPolizasXTransaccionId transaccionId = new DetPolizasXTransaccionId();

                      // for (int j = 0; listsecDetPol.size()-1>= j;j++){

                        //   Map secDetPolS = (HashMap) listsecDetPol.get(j);

                        //  System.out.println("sedc: "+secDetPolS.get("SEC").toString());

                           transaccionId.setCompania(compania);
                             transaccionId.setFecha(formatFecha.parse(ss2.fecha_pol_rel));
                             transaccionId.setTipoPoliza(ss2.tipo_pol_rel);
                             transaccionId.setNumero(ss2.num_pol_rel);
                          //   transaccionId.setSec(Integer.parseInt(secDetPolS.get("SEC").toString()));
                          //   transaccionId.setIdTransaccion(Integer.parseInt(idTransaccionUuid.get("ID").toString()));
                             transaccion.setId(transaccionId);

                             detPolizasXTransaccionDao.borraTransaccionXPolizaCompl(transaccion);

                     //  }

                       }








                    if(relacion == true){
                     rq.setSuccess(true);
                     rq.setMsg("Se quito la relacion correctamente");
                    }else{

                        rq.setSuccess(false);
                     rq.setMsg("Fallo al quitar relacion");

                    }





                        i++;
                }
           }
            
             
        } catch (Exception e) {
             rq.setSuccess(false);
            rq.setMsg("Error al relacionar poliza");
            e.printStackTrace();
            return rq;
        }

        return rq;

    }
      
        @RequestMapping(value = "/relacionaFacturasCXP", method = RequestMethod.POST)
    public @ResponseBody
    ResponseQuery RelacionaDocCXP(String data,@RequestParam("numFact") String numero,@RequestParam("diferencia") String diferencia,@RequestParam("saldo") String saldo, WebRequest webRequest, Model model) {
            
        boolean isSave = false;
         SimpleDateFormat formatoDelTexto2 = new SimpleDateFormat("dd/MM/yyyy");
        
        ResponseQuery rq = new ResponseQuery();
          
           int index = data.indexOf("[");
            if (index == -1) {
                data = "[" + data + "]";
            }
           
       
        
       Map parametros = processDao.paramToMap(webRequest.getParameterMap());
//        parametros.put("compania", model.asMap().get("compania"));
//        parametros.put("usuario", model.asMap().get("usuario"));
       
      
       
        try {
            
            
            ObjectMapper mapper = new ObjectMapper();
            
            List<RelacionCxpDTO> lista = mapper.readValue(data,
                    mapper.getTypeFactory().constructCollectionType(List.class,
                            RelacionCxpDTO.class));
            
           Iterator<RelacionCxpDTO> it = lista.iterator();
           
           ErpSeguiDocumentos segui = new ErpSeguiDocumentos(); 
           ErpSeguiDocumentosId seguiId = new ErpSeguiDocumentosId();
           

               while(it.hasNext()){
                    RelacionCxpDTO ss = it.next();
            
                   System.out.println("folio"+ss.folio);
                   ErpCpRelacionFacturas rel = new ErpCpRelacionFacturas();
                   ErpCpRelacionFacturasId relId = new ErpCpRelacionFacturasId();
                   ErpFeComprobantes comp = new ErpFeComprobantes();
                   ErpFeComprobantesId compId = new ErpFeComprobantesId();
               
               
                   
                   
                   relId.setCompania(ss.compania);
                   relId.setNumDocumento(Integer.parseInt(ss.numero));
                   relId.setNumFactura(Integer.parseInt(numero));
                   
                   int sec =  erpCpRelacionFacturasDao.getMaxIdFacturas(relId);
                   
                   relId.setSec(sec);
                   rel.setId(relId);
                   rel.setImporteFact(new BigDecimal(ss.importeFact));
                   
                   ErpCpRelacionFacturasId result = erpCpRelacionFacturasDao.save(rel);
                   
                   compId.setCompania(ss.compania);
                   compId.setNumero(Integer.parseInt(ss.numero));
                   comp.setId(compId);
                   comp.setSaldosCxp(new BigDecimal (ss.saldoCxpFact));
                   
                   double importeFact = new BigDecimal(ss.importeFact).doubleValue();
                   double importeSinMov = new BigDecimal(ss.totalSinMov).doubleValue();
                   double importeNota = new BigDecimal (ss.saldoCxpFact).doubleValue();
                   
                   seguiId.setCompania(ss.compania);
                 seguiId.setNumFe(Integer.parseInt(ss.numero));
                 seguiId.setOrigen("P");
                 seguiId.setSerie("A");
                 seguiId.settDoc("FE");
                
                 int secS = erpSeguiDocumentosDao.getMaxId(seguiId);
        //         System.out.println("pagos sec "+sec);
                 seguiId.setSec(secS);
                 
                 segui.setId(seguiId);
                 //segui.setBanco(ss.banco);
//                 if (ss.folio == null){
//                     
//                   segui.setFolio(Integer.parseInt(ss.numFe));
//                     
//                 }else{
                  segui.setFolio(Integer.parseInt(ss.numero));
                  segui.setTipo("NC" + "-" + numero);
                // }
                 segui.setImporteOperacion(new BigDecimal(importeFact));
                 //segui.setMoneda(ss.);
                 segui.setObservaciones("Pagos de nota de credito");
                 segui.setParidad(new BigDecimal(1));
                 segui.setTotPesos(new BigDecimal(importeFact));
                 //segui.setUsuario(usuario);
                 //segui.settOperacion(ss.tOperacionDet);
                 //segui.setCuentaBanco(ss.cuentaBanco);
                 DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
                 Date date = new Date();
                
                 segui.setFechaPagoCxpFe(dateFormat.parse(dateFormat.format(date)));
                 
                 ErpSeguiDocumentosId result3 = erpSeguiDocumentosDao.save(segui);
                   
                   
                    Map pagoAcu = new HashMap();
             
               pagoAcu.put("compania", ss.compania);
               pagoAcu.put("numero", ss.numero);
               
                   List listpagoAcu= processDao.getMapResult("BuscaPagosTot", pagoAcu);
                   
                    Map pagSum = (HashMap) listpagoAcu.get(0);
                    
                    double sumPagos = new BigDecimal(pagSum.get("SUM_IMPORTE").toString()).doubleValue();
                   
                     int pagAcumuTot = Double.compare(sumPagos,importeSinMov);
                      
                      
                     
                          if(pagAcumuTot > 0) {
                             comp.setEstatusCxp("PAG");
                             comp.setPagoAcumuladoCxp(new BigDecimal(pagSum.get("SUM_IMPORTE").toString()));
                          }
                          else if(pagAcumuTot < 0) {
                             comp.setEstatusCxp("PAR");

                             comp.setPagoAcumuladoCxp(new BigDecimal(pagSum.get("SUM_IMPORTE").toString()));
                          }
                          else {
                             comp.setEstatusCxp("PAG");
                             comp.setPagoAcumuladoCxp(new BigDecimal(pagSum.get("SUM_IMPORTE").toString()));
                          }
                 
                      
                   erpFeComprobantesDao.updateSaldoCxp(comp);
                   
                   compId.setCompania(ss.compania);
                   compId.setNumero(Integer.parseInt(numero));
                   comp.setId(compId);
                   comp.setSaldosCxp(new BigDecimal (saldo));
                    comp.setEstatusCxp("PAG");
                    comp.setPagoAcumuladoCxp(BigDecimal.ZERO); 
                   
                   
                   erpFeComprobantesDao.updateSaldoCxp(comp);
                   
                   
                   
                 
                   
                 if (result == null) {
            
                    rq.setSuccess(false);
                    rq.setData(null);
                    rq.setMsg("Error al guardar la relacion");
                    
                } else {
                    rq.setSuccess(true);
                    rq.setData(null);
                    rq.setMsg("Se guardo la relacion correctamente");
                    
               }
                
                
               }
        } catch (Exception e) {
            isSave = false;
            e.printStackTrace();
        }

       

        return rq;
    }
        
   @RequestMapping(value = "/quitaRelacionFacturasCXP", method = RequestMethod.POST)
    public @ResponseBody
    ResponseQuery quitarRelacionaDocCXP(String data,@RequestParam("numFact") String numero,@RequestParam("importeArchivo") String importeArchivo,@RequestParam("importeRel") String importeRel, WebRequest webRequest, Model model) {
            
        boolean isSave = false;
         SimpleDateFormat formatoDelTexto2 = new SimpleDateFormat("dd/MM/yyyy");
        
        ResponseQuery rq = new ResponseQuery();
          
           int index = data.indexOf("[");
            if (index == -1) {
                data = "[" + data + "]";
            }
     
        
       Map parametros = processDao.paramToMap(webRequest.getParameterMap());
//        parametros.put("compania", model.asMap().get("compania"));
//        parametros.put("usuario", model.asMap().get("usuario"));
       
           ErpSeguiDocumentos segui = new ErpSeguiDocumentos(); 
           ErpSeguiDocumentosId seguiId = new ErpSeguiDocumentosId();
       
        try {
            
            
            ObjectMapper mapper = new ObjectMapper();
            
            List<RelacionCxpDTO> lista = mapper.readValue(data,
                    mapper.getTypeFactory().constructCollectionType(List.class,
                            RelacionCxpDTO.class));
            
           Iterator<RelacionCxpDTO> it = lista.iterator();
           
                    ErpCpRelacionFacturas rel = new ErpCpRelacionFacturas();
                   ErpCpRelacionFacturasId relId = new ErpCpRelacionFacturasId();
                    ErpFeComprobantes comp = new ErpFeComprobantes();
                   ErpFeComprobantesId compId = new ErpFeComprobantesId();
           
               while(it.hasNext()){
                    RelacionCxpDTO ss = it.next();
                    
                    if (ss.numFact == null){
                    
                        relId.setCompania(ss.compania);
                        relId.setNumDocumento(Integer.parseInt(numero));
                        relId.setNumFactura(Integer.parseInt(ss.numero));
                        relId.setSec(Integer.parseInt(ss.secDoc));
                        rel.setId(relId);
                    }else{
                    
                      if (ss.numDoc == null){
                          
                        relId.setCompania(ss.compania);
                        relId.setNumDocumento(Integer.parseInt(ss.numero));
                        relId.setNumFactura(Integer.parseInt(numero));
                        relId.setSec(Integer.parseInt(ss.secFact));
                        rel.setId(relId);
                      
                      }
                    
                    }
                    
                    double importeFact = new BigDecimal(ss.importeFact).doubleValue();
                   double importeSinMov = new BigDecimal(ss.totalSinMov).doubleValue();
                   double importeNota = new BigDecimal (ss.saldoCxpFact).doubleValue();
            
                   
                    Boolean result = erpCpRelacionFacturasDao.delete(rel);
                    
                     compId.setCompania(ss.compania);
                   compId.setNumero(Integer.parseInt(ss.numero));
                   comp.setId(compId);
                   comp.setSaldosCxp(null);
                   
                   
                   
                     seguiId.setCompania(ss.compania);
                 seguiId.setNumFe(Integer.parseInt(ss.numero));
                 seguiId.setOrigen("P");
                 seguiId.setSerie("A");
                 seguiId.settDoc("FE");
                
              
                 segui.setId(seguiId);
                 //segui.setBanco(ss.banco);
//                 if (ss.folio == null){
//                     
//                   segui.setFolio(Integer.parseInt(ss.numFe));
//                     
//                 }else{
                  segui.setFolio(Integer.parseInt(ss.numero));
                  segui.setTipo("NC" + "-" + numero);
                // }
                
                 
                 boolean result3 = erpSeguiDocumentosDao.borraDetallePagosNotas(segui);
                 
                  
                    Map pagoAcu = new HashMap();
             
               pagoAcu.put("compania", ss.compania);
               pagoAcu.put("numero", ss.numero);
               
                   List listpagoAcu= processDao.getMapResult("BuscaPagosTot", pagoAcu);
                   
                    Map pagSum = (HashMap) listpagoAcu.get(0);
                    
                    double sumPagos = new BigDecimal(pagSum.get("SUM_IMPORTE").toString()).doubleValue();
                   
                     int pagAcumuTot = Double.compare(sumPagos,importeSinMov);
                      
                      if(sumPagos == 0){
                          
                          comp.setEstatusCxp("CAN");
                             comp.setPagoAcumuladoCxp(new BigDecimal(0));
                      
                      
                      }else{
                     
                          if(pagAcumuTot > 0) {
                             comp.setEstatusCxp("PAG");
                             comp.setPagoAcumuladoCxp(new BigDecimal(pagSum.get("SUM_IMPORTE").toString()));
                          }
                          else if(pagAcumuTot < 0) {
                             comp.setEstatusCxp("PAR");

                             comp.setPagoAcumuladoCxp(new BigDecimal(pagSum.get("SUM_IMPORTE").toString()));
                          }
                          else {
                             comp.setEstatusCxp("PAG");
                             comp.setPagoAcumuladoCxp(new BigDecimal(pagSum.get("SUM_IMPORTE").toString()));
                          }
                      }
                 
                   
                   
                   
                   
                   
                   erpFeComprobantesDao.updateSaldoCxp(comp);
                   
                   compId.setCompania(ss.compania);
                   compId.setNumero(Integer.parseInt(numero));
                   comp.setId(compId);
                   comp.setSaldosCxp(new BigDecimal (importeArchivo));
                   comp.setEstatusCxp("");
                  comp.setPagoAcumuladoCxp(BigDecimal.ZERO); 
                   
                   
                   erpFeComprobantesDao.updateSaldoCxp(comp);
                   
                   
                 
                      
                  
                   
                   
                   
                 if (result == false) {
            
                    rq.setSuccess(false);
                    rq.setData(null);
                    rq.setMsg("Error al quitar la relacion");
                    
                } else {
                    rq.setSuccess(true);
                    rq.setData(null);
                    rq.setMsg("Se quito la relacion correctamente");
                    
               }
                
                
               }
        } catch (Exception e) {
            isSave = false;
            e.printStackTrace();
        }

       

        return rq;
    }

    public void setErpPolizasXFacturasDao(ErpPolizasXFacturasDao erpPolizasXFacturasDao) {
        this.erpPolizasXFacturasDao = erpPolizasXFacturasDao;
    }

    public void setProcessDao(ProcessDao processDao) {
        this.processDao = processDao;
    }

    public void setErpCpRelacionFacturasDao(ErpCpRelacionFacturasDao erpCpRelacionFacturasDao) {
        this.erpCpRelacionFacturasDao = erpCpRelacionFacturasDao;
    }

    public void setErpFeComprobantesDao(ErpFeComprobantesDao erpFeComprobantesDao) {
        this.erpFeComprobantesDao = erpFeComprobantesDao;
    }

    public void setErpSatTransaccionDao(ErpSatTransaccionDao erpSatTransaccionDao) {
        this.erpSatTransaccionDao = erpSatTransaccionDao;
    }

    public void setDetPolizasXTransaccionDao(DetPolizasXTransaccionDao detPolizasXTransaccionDao) {
        this.detPolizasXTransaccionDao = detPolizasXTransaccionDao;
    }

    public void setErpPolizasXPagosDao(ErpPolizasXPagosDao erpPolizasXPagosDao) {
        this.erpPolizasXPagosDao = erpPolizasXPagosDao;
    }

    public void setErpSeguiDocumentosDao(ErpSeguiDocumentosDao erpSeguiDocumentosDao) {
        this.erpSeguiDocumentosDao = erpSeguiDocumentosDao;
    }
    
    
    
}
