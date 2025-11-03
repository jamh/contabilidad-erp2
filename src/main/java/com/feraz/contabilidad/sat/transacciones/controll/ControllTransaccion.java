/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.feraz.contabilidad.sat.transacciones.controll;

/**
 *
 * @author Administrador
 */

import com.fasterxml.jackson.databind.ObjectMapper;
import com.feraz.contabilidad.sat.transacciones.dao.ErpSatTransaccionDao;
import com.feraz.contabilidad.sat.transacciones.model.ErpSatTransaccion;
import com.feraz.contabilidad.sat.transacciones.model.ErpSatTransaccionId;
import com.feraz.cfdi.process.ProcesaCFDISAT;
import com.feraz.contabilidad.convertidor.util.ResponseQuery2;
import com.feraz.contabilidad.sat.transacciones.dao.DetPolizasXTransaccionDao;
import com.feraz.contabilidad.sat.transacciones.model.DetPolizasXTransaccion;
import com.feraz.contabilidad.sat.transacciones.model.DetPolizasXTransaccionId;
import com.feraz.polizas3.dao.DetPolizasDao;
import com.feraz.polizas3.dto.DetControlPolizaDTO;
import java.io.IOException;
import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import org.jamh.data.process.ProcessDao;
import org.jamh.wf.json.model.ResponseQuery;
import org.jamh.wf.process.Querys;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.context.request.WebRequest;

@Controller
@RequestMapping("/Transaccion")
@SessionAttributes({"compania", "usuario"})
public class ControllTransaccion {
    
    private ProcessDao processDao;
    private ErpSatTransaccionDao erpSatTransaccionDao;
    private DetPolizasDao detPolizasDao;
    private DetPolizasXTransaccionDao detPolizasXTransaccionDao;
    
     @RequestMapping(value = "/saveTransaccion", method = RequestMethod.POST)
    public @ResponseBody
    ResponseQuery save(String data, WebRequest webRequest, Model model) {

   
      
        Map parametros = processDao.paramToMap(webRequest.getParameterMap());

        ResponseQuery rq = new ResponseQuery();

        String compania = model.asMap().get("compania").toString();
        String usuario = model.asMap().get("usuario").toString();
        
        String companiaTrans = parametros.get("txtCOMPANIAtrans").toString();
        String IDtrans = parametros.get("txtIDtrans").toString();
        //String numeroPol = parametros.get("txtNUMERO_POLtrans").toString();
        //String tipoPol = parametros.get("txtTIPO_POLtrans").toString();
        //String fechaPol = parametros.get("txtFECHA_POLtrans").toString();
        //String secPol = parametros.get("txtSEC_POLtrans").toString();
        String origenPago = parametros.get("txtORIGEN_PAGOtrans").toString();
        String tDocPago = parametros.get("txtT_DOC_PAGOtrans").toString();
        String folioPago = parametros.get("txtFOLIO_PAGOtrans").toString();
        String secPago = parametros.get("txtSEC_PAGOtrans").toString();

        
        
        String numCta = parametros.get("txtNumCtatrans").toString();
        String desCta = parametros.get("txtDesCtatrans").toString();
        String concepto = parametros.get("txtConceptotrans").toString();
        String debe = parametros.get("txtDebetrans").toString();
        String haber = parametros.get("txtHabertrans").toString();
        
        String tipo = parametros.get("cboTipoTrans").toString();
        String num = parametros.get("txtNUMtrans").toString();
        String banEmisNal = parametros.get("txtBAN_EMIS_NALtrans").toString();
        String banEmisExt = parametros.get("txtBAN_EMIS_EXTtrans").toString();
        String ctaOri = parametros.get("txtCTA_ORItrans").toString();
        String bancoOriNal = parametros.get("txtBANCO_ORI_NALtrans").toString();
        String bancoOriExt = parametros.get("txtBANCO_ORI_EXTtrans").toString();
        String ctaDest = parametros.get("txtCTA_DESTtrans").toString();
        String bancoDestNal = parametros.get("txtBANCO_DEST_NALtrans").toString();
        String bancoDestExt = parametros.get("txtBANCO_DEST_EXTtrans").toString();
        String metPagoPol = parametros.get("txtMET_PAGO_POLtrans").toString();
        String fecha = parametros.get("txtFECHAtrans").toString();
        String benef = parametros.get("txtBENEFtrans").toString();
        String numFactExt = parametros.get("txtNUM_FACT_EXTtrans").toString();
        String taxId = parametros.get("txtTAX_IDtrans").toString();
        String uuidCfdi = parametros.get("txtUUID_CFDItrans").toString();
        String cfdCbbSerie = parametros.get("txtCFD_CBB_SERIEtrans").toString();
        String cfdCbbNumFol = parametros.get("txtCFD_CBB_NUMFOLtrans").toString();
        String rfc = parametros.get("txtRFCtrans").toString();
        String montoTotal = parametros.get("txtMONTO_TOTALtrans").toString();
        String moneda = parametros.get("txtMONEDAtrans").toString();
        String tipocambio = parametros.get("txtTIP_CAMBtrans").toString();
        String metPagoAux = parametros.get("txtMET_PAGO_AUXtrans").toString();
        
        
        
        System.out.println("tipo"+tipo);
        System.out.println("moneda"+moneda);
       
        ErpSatTransaccion erpSatTransaccion = new ErpSatTransaccion();
        ErpSatTransaccionId erpSatTransaccionId = new ErpSatTransaccionId();


       try {
           
         if(IDtrans.equalsIgnoreCase("") || IDtrans == "" || IDtrans == null) {
             
            //  Map cuenta = new HashMap();
                
            //   cuenta.put("compania", compania);
            //   cuenta.put("cuenta", numCta);
               
             //      List listCta = processDao.getMapResult("BuscaCuentaDiot", cuenta);
                   
             //       Map cuen = (HashMap) listCta.get(0);
             //     System.out.println("CUENTA:" + cuen.get("CUENTA"));
           
            erpSatTransaccionId.setCompania(compania);
            int idMax = erpSatTransaccionDao.getMaxId(erpSatTransaccionId);
            erpSatTransaccionId.setId(new BigDecimal(idMax));
            erpSatTransaccion.setId(erpSatTransaccionId);
            
         //   erpSatTransaccion.setNumeroPol(numeroPol);
         //   erpSatTransaccion.setTipoPol(tipoPol);
             SimpleDateFormat formatFecha = new SimpleDateFormat("dd/MM/yyyy");  
          //  if (fechaPol.equalsIgnoreCase("") || fechaPol == "" || fechaPol == null){
                
          //  }else{
          //    erpSatTransaccion.setFechaPol(formatFecha.parse(fechaPol));
          //  }
            
         //   if (secPol.equalsIgnoreCase("") || secPol == "" || secPol == null){
         //      erpSatTransaccion.setSecPol(0);
         //   }else{           
          //  erpSatTransaccion.setSecPol(Integer.parseInt(secPol));
          //  }
            
            erpSatTransaccion.setOrigenPago(origenPago);
            erpSatTransaccion.settDocPago(tDocPago);
            if (folioPago.equalsIgnoreCase("") || folioPago == "" || folioPago == null){
               erpSatTransaccion.setFolioPago(0); 
            }else{
            erpSatTransaccion.setFolioPago(Integer.parseInt(folioPago));
            }
            
            if (secPago.equalsIgnoreCase("") || secPago == "" || secPago == null){
            erpSatTransaccion.setSecPago(0);
            }else{
            erpSatTransaccion.setSecPago(Integer.parseInt(secPago));
            }
            
            erpSatTransaccion.setNumCta(numCta);
            erpSatTransaccion.setDesCta(desCta);
            erpSatTransaccion.setConcepto(concepto);
            if (debe.equalsIgnoreCase("") || debe == "" || debe == null){
                erpSatTransaccion.setDebe(new BigDecimal(0));

            }else{
                erpSatTransaccion.setDebe(new BigDecimal(debe));
                
            }
            if (haber.equalsIgnoreCase("") || haber == "" || haber == null){
                erpSatTransaccion.setHaber(new BigDecimal(0));
            }else{
                erpSatTransaccion.setHaber(new BigDecimal(haber));
            }
            erpSatTransaccion.setTipo(tipo);
            erpSatTransaccion.setNum(num);
            erpSatTransaccion.setBanEmisNal(banEmisNal);
            erpSatTransaccion.setBanEmisExt(banEmisExt);
            erpSatTransaccion.setCtaOri(ctaOri);
            erpSatTransaccion.setBancoOriNal(bancoOriNal);
            erpSatTransaccion.setBancoOriExt(bancoOriExt);
            erpSatTransaccion.setCtaDest(ctaDest);
            erpSatTransaccion.setBancoDestNal(bancoDestNal);
            erpSatTransaccion.setBancoDestExt(bancoDestExt);
            erpSatTransaccion.setMetPagoPol(metPagoPol);
            erpSatTransaccion.setMetPagoAux(metPagoAux);
            
            //SimpleDateFormat formatFecha = new SimpleDateFormat("dd/MM/yyyy");  
            if (fecha.equalsIgnoreCase("") || fecha == "" || fecha == null){
                
            }else{
              erpSatTransaccion.setFecha(formatFecha.parse(fecha));
            }
            erpSatTransaccion.setBenef(benef);
            erpSatTransaccion.setNumFactExt(numFactExt);
            erpSatTransaccion.setTaxId(taxId);
            erpSatTransaccion.setUuidCfdi(uuidCfdi);
            erpSatTransaccion.setCfdCbbSerie(cfdCbbSerie);
            erpSatTransaccion.setCfdCbbNnumFol(cfdCbbNumFol);
            erpSatTransaccion.setRfc(rfc);
            
            if (montoTotal.equalsIgnoreCase("") || montoTotal == "" || montoTotal == null){
                erpSatTransaccion.setMontoTotal(new BigDecimal(0));
            }else{
                erpSatTransaccion.setMontoTotal(new BigDecimal(montoTotal));
            }
            erpSatTransaccion.setMoneda(moneda);
            
            if (tipocambio.equalsIgnoreCase("") || tipocambio == "" || tipocambio == null){
                erpSatTransaccion.setTipCamb(new BigDecimal(0));
            }else{
               erpSatTransaccion.setTipCamb(new BigDecimal(tipocambio));
               
            }
            ErpSatTransaccionId resultId = erpSatTransaccionDao.save(erpSatTransaccion);
            
            if (resultId != null){
                rq.setSuccess(true);
                rq.setMsg("Datos Guardados");
            }else{
                
                rq.setSuccess(false);
                rq.setMsg("Error al guardar");
            }
           
           
           
               
         }else{
             
              erpSatTransaccionId.setCompania(compania);
            //int idMax = erpSatTransaccionDao.getMaxId(erpSatTransaccionId);
            erpSatTransaccionId.setId(new BigDecimal(IDtrans));
            erpSatTransaccion.setId(erpSatTransaccionId);
            // erpSatTransaccion.setNumeroPol(numeroPol);
           // erpSatTransaccion.setTipoPol(tipoPol);
             SimpleDateFormat formatFecha = new SimpleDateFormat("dd/MM/yyyy");  
           // if (fechaPol.equalsIgnoreCase("") || fechaPol == "" || fechaPol == null){
                
          //  }else{
           //   erpSatTransaccion.setFechaPol(formatFecha.parse(fechaPol));
           // }
            
          //  if (secPol.equalsIgnoreCase("") || secPol == "" || secPol == null){
          //     erpSatTransaccion.setSecPol(0);
           // }else{           
          //  erpSatTransaccion.setSecPol(Integer.parseInt(secPol));
          //  }
            
            erpSatTransaccion.setOrigenPago(origenPago);
            erpSatTransaccion.settDocPago(tDocPago);
            if (folioPago.equalsIgnoreCase("") || folioPago == "" || folioPago == null){
               erpSatTransaccion.setFolioPago(0); 
            }else{
            erpSatTransaccion.setFolioPago(Integer.parseInt(folioPago));
            }
            
            if (secPago.equalsIgnoreCase("") || secPago == "" || secPago == null){
            erpSatTransaccion.setSecPago(0);
            }else{
            erpSatTransaccion.setSecPago(Integer.parseInt(secPago));
            }
            
            erpSatTransaccion.setNumCta(numCta);
            erpSatTransaccion.setDesCta(desCta);
            erpSatTransaccion.setConcepto(concepto);
            if (debe.equalsIgnoreCase("") || debe == "" || debe == null){
                erpSatTransaccion.setDebe(new BigDecimal(0));

            }else{
                erpSatTransaccion.setDebe(new BigDecimal(debe));
                
            }
            if (haber.equalsIgnoreCase("") || haber == "" || haber == null){
                erpSatTransaccion.setHaber(new BigDecimal(0));
            }else{
                erpSatTransaccion.setHaber(new BigDecimal(haber));
            }
            erpSatTransaccion.setTipo(tipo);
            erpSatTransaccion.setNum(num);
            erpSatTransaccion.setBanEmisNal(banEmisNal);
            erpSatTransaccion.setBanEmisExt(banEmisExt);
            erpSatTransaccion.setCtaOri(ctaOri);
            erpSatTransaccion.setBancoOriNal(bancoOriNal);
            erpSatTransaccion.setBancoOriExt(bancoOriExt);
            erpSatTransaccion.setCtaDest(ctaDest);
            erpSatTransaccion.setBancoDestNal(bancoDestNal);
            erpSatTransaccion.setBancoDestExt(bancoDestExt);
            erpSatTransaccion.setMetPagoPol(metPagoPol);
             erpSatTransaccion.setMetPagoAux(metPagoAux);
            
          //  SimpleDateFormat formatFecha = new SimpleDateFormat("dd/MM/yyyy");  
            if (fecha.equalsIgnoreCase("") || fecha == "" || fecha == null){
                
            }else{
              erpSatTransaccion.setFecha(formatFecha.parse(fecha));
            }
            erpSatTransaccion.setBenef(benef);
            erpSatTransaccion.setNumFactExt(numFactExt);
            erpSatTransaccion.setTaxId(taxId);
            erpSatTransaccion.setUuidCfdi(uuidCfdi);
            erpSatTransaccion.setCfdCbbSerie(cfdCbbSerie);
            erpSatTransaccion.setCfdCbbNnumFol(cfdCbbNumFol);
            erpSatTransaccion.setRfc(rfc);
            
            if (montoTotal.equalsIgnoreCase("") || montoTotal == "" || montoTotal == null){
                erpSatTransaccion.setMontoTotal(new BigDecimal(0));
            }else{
                erpSatTransaccion.setMontoTotal(new BigDecimal(montoTotal));
            }
            erpSatTransaccion.setMoneda(moneda);
            
            if (tipocambio.equalsIgnoreCase("") || tipocambio == "" || tipocambio == null){
                erpSatTransaccion.setTipCamb(new BigDecimal(0));
            }else{
               erpSatTransaccion.setTipCamb(new BigDecimal(tipocambio));
               
            }
            boolean result = erpSatTransaccionDao.update(erpSatTransaccion);
           
           if(result == true){
           rq.setSuccess(true);
           rq.setMsg("Datos actualizados");
           }else{
               rq.setSuccess(false);
           rq.setMsg("Error al actualizar");
           }
           
         
         
         
         
         }
        } catch (Exception e) {
             rq.setSuccess(false);
            rq.setMsg("Error al guardar los datos");
            e.printStackTrace();
            return rq;
        }

       // return rq;
        return rq;

    }
     
       @RequestMapping(value = "/deleteTransaccion", method = RequestMethod.POST)
    public @ResponseBody
    ResponseQuery delete(String data, @RequestParam("id") String id,
             @RequestParam("tipoPoliza") String tipoPoliza, 
              @RequestParam("numero") String numero,
              @RequestParam("fecha") String fecha,
              @RequestParam("sec") String sec, WebRequest webRequest, Model model) {
   
      
        Map parametros = processDao.paramToMap(webRequest.getParameterMap());

        ResponseQuery rq = new ResponseQuery();

        String compania = model.asMap().get("compania").toString();
        String usuario = model.asMap().get("usuario").toString();
        
         int index = data.indexOf("[");
        if (index == -1) {
            data = "[" + data + "]";
        }
       
        ErpSatTransaccion erpSatTransaccion = new ErpSatTransaccion();
        ErpSatTransaccionId erpSatTransaccionId = new ErpSatTransaccionId();
        
        System.out.println("en borrado transaccion" + id);
        
        if (id == null || id == "" || id.equalsIgnoreCase("")){
            
            System.out.println("en borrado transaccion id dentro" + id);
            
             rq.setSuccess(false);
             rq.setMsg("Error al eliminar");
             
             return rq;
        
        }


       try {
           
         if(id.equalsIgnoreCase("") || id == "" || id == null) { 
           
              rq.setSuccess(false);
               rq.setMsg("No existen Datos que eliminar");
               
               return rq;
               
         }else{
             
              erpSatTransaccionId.setCompania(compania);
            //int idMax = erpSatTransaccionDao.getMaxId(erpSatTransaccionId);
            erpSatTransaccionId.setId(new BigDecimal(id));
            erpSatTransaccion.setId(erpSatTransaccionId);
           
            boolean result = erpSatTransaccionDao.delete(erpSatTransaccion);
            
         //    DetPolizasXTransaccion transaccion = new DetPolizasXTransaccion();
         //  DetPolizasXTransaccionId transaccionId = new DetPolizasXTransaccionId();
         //  SimpleDateFormat formatoDelTexto2 = new SimpleDateFormat("dd/MM/yyyy");
         //      String strFechaCap = fecha;
         //       Date fechaCap = null;
                
         //        fechaCap = formatoDelTexto2.parse(strFechaCap);
         
         //  transaccionId.setCompania(compania);
          // transaccionId.setFecha(fechaCap);
          // transaccionId.setTipoPoliza(tipoPoliza);
          // transaccionId.setNumero(numero);
          // transaccionId.setSec(Integer.parseInt(sec));
          // transaccionId.setIdTransaccion(Integer.parseInt(id));
          // transaccion.setId(transaccionId);
     
           boolean result2 = detPolizasXTransaccionDao.borraIdTransaccion(compania, id);
           
           if (result == true && result2 == true){
             rq.setSuccess(true);
             rq.setMsg("Datos Eliminados");
           }else{
              
              rq.setSuccess(false);
             rq.setMsg("Error al eliminar");
           }
             
         
         
         
         
         
         }
        } catch (Exception e) {
             rq.setSuccess(false);
            rq.setMsg("Error al guardar los datos");
            e.printStackTrace();
            return rq;
        }

       // return rq;
        return rq;

    }
    
      @RequestMapping(value = "/quitarRelacionPolizasTrans", method = RequestMethod.POST)
    public @ResponseBody
    ResponseQuery deleteRelacionPolizasTrans(String data, WebRequest webRequest, Model model) {
   
      
        Map parametros = processDao.paramToMap(webRequest.getParameterMap());

        ResponseQuery rq = new ResponseQuery();

        String compania = model.asMap().get("compania").toString();
        String usuario = model.asMap().get("usuario").toString();
        
         int index = data.indexOf("[");
        if (index == -1) {
            data = "[" + data + "]";
        }
       
      
       try {
           
        
             
          
               ObjectMapper mapper = new ObjectMapper();
            DateFormat df = new SimpleDateFormat("dd/MM/yyyy");

            mapper.setDateFormat(df);
            List<DetControlPolizaDTO> lista = mapper.readValue(data,
                    mapper.getTypeFactory().constructCollectionType(List.class,
                            DetControlPolizaDTO.class));
//        
       
           
           
           if (lista.isEmpty()) {
                rq.setSuccess(false);
                rq.setData(null);
                rq.setMsg("Error no existen datos que guardar");
                return rq;
            }

            int val = 0;
            Iterator<DetControlPolizaDTO> it = lista.iterator();
            List<DetControlPolizaDTO> lisErr2 = new ArrayList<DetControlPolizaDTO>();
           
            while (it.hasNext()) {
//                 System.out.println("-------------------------------------------------------------------");
                DetControlPolizaDTO ss = it.next();
                
                 DetPolizasXTransaccion transaccion = new DetPolizasXTransaccion();
                 DetPolizasXTransaccionId transaccionId = new DetPolizasXTransaccionId();
                 SimpleDateFormat formatoDelTexto2 = new SimpleDateFormat("dd/MM/yyyy");
                 
                 Date fechaCap = null;
                
                 fechaCap = formatoDelTexto2.parse(ss.fecha);
         
                    transaccionId.setCompania(compania);
                    transaccionId.setFecha(fechaCap);
                    transaccionId.setTipoPoliza(ss.tipoPoliza);
                    transaccionId.setNumero(ss.numero);
                    transaccionId.setSec(Integer.parseInt(ss.sec));
                    transaccionId.setIdTransaccion(Integer.parseInt("1"));
                    transaccion.setId(transaccionId);

                    boolean result = detPolizasXTransaccionDao.borraTransaccionXPoliza(transaccion);

                    if (result == true){
                      rq.setSuccess(true);
                      rq.setMsg("Transaccion Borrada");
                    }else{
                     
                        rq.setSuccess(false);
                        rq.setMsg("Error al quitar Transaccion");
                    }
                
            }
          
         
         
         
         
         
         
        } catch (Exception e) {
             rq.setSuccess(false);
            rq.setMsg("Error al guardar los datos");
            e.printStackTrace();
            return rq;
        }

       // return rq;
        return rq;

    }
       
    @RequestMapping(value = "/relacionTransaccion", method = RequestMethod.POST)
    public @ResponseBody
    ResponseQuery2 relacion(String data, 
              @RequestParam("id") String id,
              @RequestParam("tipoPoliza") String tipoPoliza, 
              @RequestParam("numero") String numero,
              @RequestParam("fecha") String fecha,
              @RequestParam("sec") String sec, WebRequest webRequest, Model model) throws IOException {

        
        Map parametros = processDao.paramToMap(webRequest.getParameterMap());

        ResponseQuery2 rq = new ResponseQuery2();
        
         int index = data.indexOf("[");
        if (index == -1) {
            data = "[" + data + "]";
        }

        String compania = model.asMap().get("compania").toString();
        String usuario = model.asMap().get("usuario").toString();
        
       

       try {
           
           
          
           ObjectMapper mapper = new ObjectMapper();
            DateFormat df = new SimpleDateFormat("dd/MM/yyyy");

            mapper.setDateFormat(df);
            List<DetControlPolizaDTO> lista = mapper.readValue(data,
                    mapper.getTypeFactory().constructCollectionType(List.class,
                            DetControlPolizaDTO.class));
//        
       
           
           
           if (lista.isEmpty()) {
                rq.setSuccess(false);
                rq.setData(null);
                rq.setMsg("Error no existen datos que guardar");
                return rq;
            }

            int val = 0;
            Iterator<DetControlPolizaDTO> it = lista.iterator();
            List<DetControlPolizaDTO> lisErr2 = new ArrayList<DetControlPolizaDTO>();
           
            while (it.hasNext()) {
//                 System.out.println("-------------------------------------------------------------------");
                DetControlPolizaDTO ss = it.next();
                
                 DetPolizasXTransaccion transaccion = new DetPolizasXTransaccion();
                 DetPolizasXTransaccionId transaccionId = new DetPolizasXTransaccionId();
                 SimpleDateFormat formatoDelTexto2 = new SimpleDateFormat("dd/MM/yyyy");
                 String strFechaCap = fecha;
                 Date fechaCap = null;
                
                 fechaCap = formatoDelTexto2.parse(ss.fecha);
         
                    transaccionId.setCompania(compania);
                    transaccionId.setFecha(fechaCap);
                    transaccionId.setTipoPoliza(ss.tipoPoliza);
                    transaccionId.setNumero(ss.numero);
                    transaccionId.setSec(Integer.parseInt(ss.sec));
                    transaccionId.setIdTransaccion(Integer.parseInt(id));
                    transaccion.setId(transaccionId);

                    DetPolizasXTransaccionId result = detPolizasXTransaccionDao.save(transaccion);

                    if (result != null){
                     // rq.setSuccess(true);
                     // rq.setMsg("Transaccion Relacionada");
                    }else{
                        ss.msgErr = "Error al guardar relacion para la cuenta :"+ ss.cuentaAlias;
                        lisErr2.add(ss);

                      // rq.setSuccess(false);
                     // rq.setMsg("Error al relacionar Transaccion");
                    }
                
            }
           

           
          if (lisErr2.size() > 0){
              
              rq.setSuccess(false);
               rq.setDataErr(lisErr2);
              
             
          
          
          }else{
              
              
               rq.setMsg("Relacion Guardada Correctamente");
               rq.setSuccess(true);
               rq.setDataErr(lisErr2);
               
          
          }
             
         
         
         //}
        } catch (Exception e) {
             rq.setSuccess(false);
            rq.setMsg("Error al guardar los datos");
            e.printStackTrace();
            return rq;
        }

       // return rq;
        return rq;

    }
     
        @RequestMapping(value = "/quitarRelacionTransaccionDet", method = RequestMethod.POST)
    public @ResponseBody
    ResponseQuery2 quitarRelacion(String data, 
              @RequestParam("id") String id,
              @RequestParam("tipoPoliza") String tipoPoliza, 
              @RequestParam("numero") String numero,
              @RequestParam("fecha") String fecha,
              @RequestParam("sec") String sec, WebRequest webRequest, Model model) {

        
        Map parametros = processDao.paramToMap(webRequest.getParameterMap());

         ResponseQuery2 rq = new ResponseQuery2();
        
         int index = data.indexOf("[");
        if (index == -1) {
            data = "[" + data + "]";
        }

        String compania = model.asMap().get("compania").toString();
        String usuario = model.asMap().get("usuario").toString();
        
//   
//        
       

       try {
           
        ObjectMapper mapper = new ObjectMapper();
            DateFormat df = new SimpleDateFormat("dd/MM/yyyy");

            mapper.setDateFormat(df);
            List<DetControlPolizaDTO> lista = mapper.readValue(data,
                    mapper.getTypeFactory().constructCollectionType(List.class,
                            DetControlPolizaDTO.class));
//        
       
           
           
           if (lista.isEmpty()) {
                rq.setSuccess(false);
                rq.setData(null);
                rq.setMsg("Error no existen datos que guardar");
                return rq;
            }

            int val = 0;
            Iterator<DetControlPolizaDTO> it = lista.iterator();
            List<DetControlPolizaDTO> lisErr2 = new ArrayList<DetControlPolizaDTO>();
           
            while (it.hasNext()) {
//                 System.out.println("-------------------------------------------------------------------");
                DetControlPolizaDTO ss = it.next();
                
                 DetPolizasXTransaccion transaccion = new DetPolizasXTransaccion();
                 DetPolizasXTransaccionId transaccionId = new DetPolizasXTransaccionId();
                 SimpleDateFormat formatoDelTexto2 = new SimpleDateFormat("dd/MM/yyyy");
                 String strFechaCap = fecha;
                 Date fechaCap = null;
                
                 fechaCap = formatoDelTexto2.parse(ss.fecha);
         
                    transaccionId.setCompania(compania);
                    transaccionId.setFecha(fechaCap);
                    transaccionId.setTipoPoliza(ss.tipoPoliza);
                    transaccionId.setNumero(ss.numero);
                    transaccionId.setSec(Integer.parseInt(ss.sec));
                    transaccionId.setIdTransaccion(Integer.parseInt(id));
                    transaccion.setId(transaccionId);

                    boolean result = detPolizasXTransaccionDao.delete(transaccion);

                    if (result == true){
                     // rq.setSuccess(true);
                     // rq.setMsg("Transaccion Relacionada");
                    }else{
                        ss.msgErr = "Error al borrar relacion para la cuenta :"+ ss.cuentaAlias;
                        lisErr2.add(ss);

                      // rq.setSuccess(false);
                     // rq.setMsg("Error al relacionar Transaccion");
                    }
                
            }
           

           
          if (lisErr2.size() > 0){
              
              rq.setSuccess(false);
               rq.setDataErr(lisErr2);
              
             
          
          
          }else{
              
              
               rq.setMsg("Relacion Borrada Correctamente");
               rq.setSuccess(true);
               rq.setDataErr(lisErr2);
               
          
          }

         
         //}
        } catch (Exception e) {
             rq.setSuccess(false);
            rq.setMsg("Error al guardar los datos");
            e.printStackTrace();
            return rq;
        }

       // return rq;
        return rq;

    }
       
    public void setProcessDao(ProcessDao processDao) {
        this.processDao = processDao;
    }

    public void setErpSatTransaccionDao(ErpSatTransaccionDao erpSatTransaccionDao) {
        this.erpSatTransaccionDao = erpSatTransaccionDao;
    }

    public void setDetPolizasDao(DetPolizasDao detPolizasDao) {
        this.detPolizasDao = detPolizasDao;
    }

    public void setDetPolizasXTransaccionDao(DetPolizasXTransaccionDao detPolizasXTransaccionDao) {
        this.detPolizasXTransaccionDao = detPolizasXTransaccionDao;
    }
     
     
    
}
