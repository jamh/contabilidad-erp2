
package com.feraz.polizas3.controll;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.feraz.contabilidad.sat.diot.dao.DetDIOTDao;
import com.feraz.contabilidad.sat.diot.model.DetDIOT;
import com.feraz.contabilidad.sat.diot.model.DetDIOTId;
import com.feraz.polizas3.dao.DetPolizasDao;
import com.feraz.polizas3.dao.LogPolizasDao;
import com.feraz.polizas3.dto.DetControlPolizaDTO;
import com.feraz.polizas3.model.DetPolizas;
import com.feraz.polizas3.model.DetPolizasId;
import com.feraz.polizas3.model.LogPolizasId;
import com.feraz.polizas3.model.LogPolizas;
import com.feraz.polizas3.util.ResponseQuery2;
import com.feraz.polizas3.validation.DetalleValidation;
import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import org.jamh.data.process.ProcessDao;
import org.jamh.wf.process.Querys;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.context.request.WebRequest;

@Controller
@RequestMapping("/controlDet")
@SessionAttributes({"compania", "usuario"})

/**
 *
 * @author Ing. JAMH
 */
public class ProcessDetalle {

    private DetPolizasDao detPolizasDao;
    private ProcessDao processDao;
    private LogPolizasDao logPolizasDao;
    private DetalleValidation detalleValidation;
    private  DetDIOTDao detDIOTDao; 

    @RequestMapping(value = "/polizaDet/insDet", method = RequestMethod.POST)
    public @ResponseBody
    ResponseQuery2 insertAction(String data, WebRequest webRequest, Model model) {

        ResponseQuery2 rq = new ResponseQuery2();
        SimpleDateFormat formatoDelTexto = new SimpleDateFormat("dd/MM/yyyy");
        int index = data.indexOf("[");
        if (index == -1) {
            data = "[" + data + "]";
        }
           
       // System.out.println("data:" + data);
//
        if (model.asMap().get("compania") == null) {
            rq.setSuccess(false);
            rq.setData(null);
            rq.setMsg("La sesion no es valida.");
            return rq;
        }
        String compania = model.asMap().get("compania").toString();
        String usuario = model.asMap().get("usuario").toString();
        List<DetControlPolizaDTO> lisErr2 = new ArrayList<DetControlPolizaDTO>();
        List<DetControlPolizaDTO> listaAciertos = new ArrayList<DetControlPolizaDTO>();

        try {
            int guardado = 0;
           
            ObjectMapper mapper = new ObjectMapper();
            DateFormat df = new SimpleDateFormat("dd/MM/yyyy");

            mapper.setDateFormat(df);
            List<DetControlPolizaDTO> lista = mapper.readValue(data,
                    mapper.getTypeFactory().constructCollectionType(List.class,
                            DetControlPolizaDTO.class));

            if (lista.isEmpty()) {
                rq.setSuccess(false);
                rq.setData(null);
                rq.setMsg("Error no existen datos que guardar");
                return rq;
            }
            
           

            int val = 0;
            Iterator<DetControlPolizaDTO> it = lista.iterator();
            Querys que = new Querys();
            String store = que.getSQL("CONTA_INSERTA_DETPOLIZAS_FOVI3");
            
            int mensajeOrden = 0;
            
            while (it.hasNext()) {
//                 System.out.println("-------------------------------------------------------------------");
                DetControlPolizaDTO ss = it.next();
                
               
                
                ss.hora = "" + System.currentTimeMillis();
              
                ss.cargosBase = ss.cargos;
                ss.abonosBase = ss.abonos;
                ss.cCostos = ss.cc;
               if(ss.cCostos ==null || ss.cCostos.trim().equals("")){
                    ss.cCostos = "00";
                    ss.cc = "00";
                }

                @SuppressWarnings("unchecked")
                Map<String, Object> parametros = mapper.convertValue(ss, Map.class);

                parametros.put("compania", compania);
                parametros.put("usuario", usuario);
//                System.out.println(parametros.toString());
                
               List list = processDao.getMapResult("BuscaCuentaCorrecta", parametros);
         
                if (list == null  || list.size()==0) {
                  rq.setSuccess(false);
                  rq.setData(null);
                  rq.setMsg("Error cuenta no encontrada");
                  return rq;
               } 
                
                   
                
               String cuenta_real= getVal(list.get(0).toString(),0);
               
              
//                    if(getVal(list.get(0).toString(),2).equalsIgnoreCase("O")){
//                        
//                         List listOrden = processDao.getMapResult("BuscaCuentasOrden2", parametros);
//                    
//                          if (listOrden.size()>0) {
//                              
//                                
//                           mensajeOrden = 1;
//                 
//                          
//                        } 
//                    }else{
//                        
//                        List listOrden = processDao.getMapResult("BuscaCuentasOrden", parametros);
//                    
//                          if (listOrden.size()>0) {
//                         
//                                 mensajeOrden = 1;
//                        } 
//                    
//                    }
//                       
//                
               
               
                  
                 
            
               ss.cuenta = cuenta_real;
                parametros.put("CUENTA", cuenta_real);
                // PolizasId polizasId = mapper.convertValue(ss, PolizasId.class);
                DetPolizasId id = mapper.convertValue(ss, DetPolizasId.class);
                DetPolizas disp = mapper.convertValue(ss, DetPolizas.class);
                disp.setFechaCap(new Date());
                
                
 
                
                 if(disp.getCargos()==null){
                
                    disp.setCargos(BigDecimal.ZERO);
                    disp.setCargosBase(BigDecimal.ZERO);
           
                }
                
                if(disp.getAbonos()==null){
                
                    disp.setAbonos(BigDecimal.ZERO);
                    disp.setAbonosBase(BigDecimal.ZERO);
           
                }
                
                if(disp.getAbonos().compareTo(BigDecimal.ZERO) != 0 && disp.getCargos().compareTo(BigDecimal.ZERO) != 0){
                    
//                    System.out.println("Abonos"+disp.getAbonos());
//                    System.out.println("CARGOS"+disp.getCargos());
//                    
                        rq.setSuccess(false);
                        rq.setData(null);
                        rq.setMsg("Error los cargos y abonos no pueden tener ambos importes");
                        return rq;
                
                
                }

                disp.setId(id);
                disp.setCtoTrabajo(ss.ct);

                LogPolizasId idLog = new LogPolizasId();
                LogPolizas log = new LogPolizas();
                parametros.put("estatus","2,99");
                boolean estatus = detalleValidation.validaEstatus(parametros);

                if (!estatus) {
                    rq.setSuccess(false);
                    rq.setData(null);
                    rq.setMsg(detalleValidation.getMsgError());
                    return rq;
                }
                   parametros.put("FECHA", ss.fecha);
                boolean estatusP = detalleValidation.validaPeriodo(parametros);

                if (!estatusP) {
                    rq.setSuccess(false);
                    rq.setData(null);
                    rq.setMsg(detalleValidation.getMsgError());
                    return rq;
                }

                boolean cuenta = detalleValidation.validaCuenta(parametros);

                if (!cuenta) {
                    ss.msgErr = "Error en Cuenta:" + ss.cuenta;
                    lisErr2.add(ss);
                    continue;
                }
                
               
                   //  System.out.println("ss.cc:"+ss.cc);
                     
                
                 
                 if(ss.cc==null ){
                    
                }else if ( !ss.cc.trim().equals("")) {
                    boolean cc = detalleValidation.validaCC(parametros);

                    if (!cc) {
                        ss.msgErr = "Error en el Centro de costo:" + ss.cc;
                        lisErr2.add(ss);
                        continue;
                    }
                }

                parametros.put("OPERACION", "I");
                parametros.put("OPE_SUMA", "0");

                val = processDao.execute(store, parametros);

                if (val <= 0) {
                    ss.msgErr = "Error en la cuenta:" + ss.cuenta;
                    lisErr2.add(ss);
                    continue;
                }

                int sec = detPolizasDao.getMaxId(id);
                disp.getId().setSec(new BigDecimal(sec));
                DetPolizasId result = null;
                result = detPolizasDao.save(disp);
                
                

                if (result == null) {
                    ss.msgErr = "Error al guardar DTO" + result.toString();
                    lisErr2.add(ss);

                    continue;
                }
                ss.sec=""+result.getSec();
                ss.id2=""+result.getSec();
                listaAciertos.add(ss);

                //Suma detalles actualiza
                parametros.put("OPERACION", "S");
                parametros.put("OPE_SUMA", "1");
                val = processDao.execute(store, parametros);

                //Genera el Log del Detalle de polizas
                idLog.setUsuario(usuario);
                idLog.setNumPoliza(ss.numero);
                idLog.setTipoPoliza(ss.tipoPoliza);
                idLog.setFechaPoliza(disp.getId().getFecha());
                log.setId(idLog);
                log.setFecha(new Date());
                log.setAccion("INSERTA_DETPOLIZA, SEC: " + sec);
                log.setCompania(ss.compania);

                LogPolizasId resultLog = logPolizasDao.save(log);
                
                
                

            }

            System.out.println("aciertos:" + listaAciertos.size());
            System.out.println("errores:" + lisErr2.size());
            
            if (listaAciertos.size()>0) guardado = 0;
            if (lisErr2.size()>0) guardado = 2;
            
            

            if (guardado == 0) {
               
                 
                    rq.setSuccess(true);
                    rq.setData(listaAciertos);
                    rq.setDataErr(lisErr2);
                    rq.setMsg("Guardados Correctamente");
                 
            } else {
                rq.setSuccess(false);
                rq.setData(null);
                rq.setDataErr(lisErr2);
                rq.setMsg("Error al guardar detalles de polizas");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return rq;

    }
    
    
   
        @RequestMapping(value = "/polizaDet/ajuste", method = RequestMethod.POST)
    public @ResponseBody
    ResponseQuery2 ajuste(String compania2,String tipoPoliza,String numero, String fecha, WebRequest webRequest, Model model) {

        ResponseQuery2 rq = new ResponseQuery2();
        SimpleDateFormat formatoDelTexto = new SimpleDateFormat("dd/MM/yyyy");
      
       // System.out.println("data:" + data);
//
        if (model.asMap().get("compania") == null) {
            rq.setSuccess(false);
            rq.setData(null);
            rq.setMsg("La sesion no es valida.");
            return rq;
        }
        String compania = model.asMap().get("compania").toString();
        String usuario = model.asMap().get("usuario").toString();
       
        try {
            
            System.out.println("En ajuste: "+compania);
            
               Querys que = new Querys();
            String store = que.getSQL("GENERA_AJUSTE");
            
             Map param = new HashMap();
                  param.put("compania", compania);
                  param.put("tipo_poliza", tipoPoliza);
                  param.put("fecha", fecha);
                  param.put("numero", numero);
                   
             int  val = processDao.execute(store, param);

                if (val <= 0) {
                    
                }
           

        } catch (Exception e) {
            e.printStackTrace();
        }

        return rq;

    }        
            
            
    private String getVal(String jsonVal, int pos) {
        jsonVal = jsonVal.replaceAll("\\{", "");
        jsonVal = jsonVal.replaceAll("\\}", "");
        jsonVal = jsonVal.replaceAll("\\'", "");
        jsonVal = jsonVal.replaceAll("\"", "");
        String values[] = jsonVal.split(",");
        jsonVal = values[pos];
        jsonVal = jsonVal.substring(jsonVal.indexOf("=") + 1, jsonVal.length());
        return jsonVal;
    }

    @RequestMapping(value = "/polizaDet/uptDet", method = RequestMethod.POST)
    public @ResponseBody
    ResponseQuery2 updateAction(String data, WebRequest webRequest, Model model) {

            ResponseQuery2 rq = new ResponseQuery2();
        SimpleDateFormat formatoDelTexto = new SimpleDateFormat("dd/MM/yyyy");
        int index = data.indexOf("[");
        if (index == -1) {
            data = "[" + data + "]";
        }
           
    //    System.out.println("data:" + data);
//
        if (model.asMap().get("compania") == null) {
            rq.setSuccess(false);
            rq.setData(null);
            rq.setMsg("La sesion no es valida.");
            return rq;
        }
        String compania = model.asMap().get("compania").toString();
        String usuario = model.asMap().get("usuario").toString();
        List<DetControlPolizaDTO> lisErr2 = new ArrayList<DetControlPolizaDTO>();
        List<DetControlPolizaDTO> listaAciertos = new ArrayList<DetControlPolizaDTO>();

        try {
            int guardado = 0;
            String fecha = null;
            ObjectMapper mapper = new ObjectMapper();
            DateFormat df = new SimpleDateFormat("dd/MM/yyyy");

            mapper.setDateFormat(df);
            List<DetControlPolizaDTO> lista = mapper.readValue(data,
                    mapper.getTypeFactory().constructCollectionType(List.class,
                            DetControlPolizaDTO.class));

            if (lista.isEmpty()) {
                rq.setSuccess(false);
                rq.setData(null);
                rq.setMsg("Error no existen datos que guardar");
                return rq;
            }

            int val = 0;
            int mensajeOrden = 0;
            Iterator<DetControlPolizaDTO> it = lista.iterator();
            Querys que = new Querys();
            String store = que.getSQL("CONTA_INSERTA_DETPOLIZAS_FOVI3");
            while (it.hasNext()) {
//                 System.out.println("-------------------------------------------------------------------");
                DetControlPolizaDTO ss = it.next();

                ss.hora = "" + System.currentTimeMillis();
//                ss.cargosBase = ss.cargos;
//                ss.abonosBase = ss.abonos;
                ss.cCostos = ss.cc;
                 if(ss.cCostos ==null || ss.cCostos.trim().equals("")){
                    ss.cCostos = "00";
                     ss.cc="00";
                }
                ss.cargosBase = ss.cargos;
                ss.abonosBase = ss.abonos;

                @SuppressWarnings("unchecked")
                Map<String, Object> parametros = mapper.convertValue(ss, Map.class);

                parametros.put("compania", compania);
                parametros.put("usuario", usuario);
               List list = processDao.getMapResult("BuscaCuentaCorrecta", parametros);
         
                if (list == null  || list.size()==0) {
                  rq.setSuccess(false);
                  rq.setData(null);
                  rq.setMsg("Error cuenta no encontrada");
                  return rq;
               } 
                
               String cuenta_real= getVal(list.get(0).toString(),0);
               
                
                
             
               ss.cuenta = cuenta_real;
               parametros.put("CUENTA", cuenta_real);
                // PolizasId polizasId = mapper.convertValue(ss, PolizasId.class);
                DetPolizasId id = mapper.convertValue(ss, DetPolizasId.class);
                DetPolizas disp = mapper.convertValue(ss, DetPolizas.class);
                
                if(disp.getCargos()==null){
                
                    disp.setCargos(BigDecimal.ZERO);
                    disp.setCargosBase(BigDecimal.ZERO);
           
                }
                
                if(disp.getAbonos()==null){
                
                    disp.setAbonos(BigDecimal.ZERO);
                    disp.setAbonosBase(BigDecimal.ZERO);
           
                }
                
                if(disp.getAbonos().compareTo(BigDecimal.ZERO) != 0 && disp.getCargos().compareTo(BigDecimal.ZERO) != 0){
                    
                    System.out.println("Abonos"+disp.getAbonos());
                    System.out.println("CARGOS"+disp.getCargos());
                    
                        rq.setSuccess(false);
                        rq.setData(null);
                        rq.setMsg("Error los cargos y abonos no pueden tener ambos importes");
                        return rq;
                
                
                }
                

                disp.setId(id);
                disp.setCtoTrabajo(ss.ct);
                LogPolizasId idLog = new LogPolizasId();
                LogPolizas log = new LogPolizas();
                
                parametros.put("estatus","2,99");

                boolean estatus = detalleValidation.validaEstatus(parametros);

                if (!estatus) {
                    rq.setSuccess(false);
                    rq.setData(null);
                    rq.setMsg(detalleValidation.getMsgError());
                    return rq;
                }

                boolean cuenta = detalleValidation.validaCuenta(parametros);
                    parametros.put("FECHA", ss.fecha);
                boolean estatusP = detalleValidation.validaPeriodo(parametros);

                if (!estatusP) {
                    rq.setSuccess(false);
                    rq.setData(null);
                    rq.setMsg(detalleValidation.getMsgError());
                    return rq;
                }

                if (!cuenta) {
                    ss.msgErr = "Error Cuenta:" + ss.cuenta;
                    lisErr2.add(ss);
                    continue;
                }
                if(ss.cc==null){
                }else if (!ss.cc.equals("")) {
                    
                   if(ss.cc.equals("00")){
                       
                       System.out.println("Centro de costo 00");
                       
                   }else{ 
                    boolean cc = detalleValidation.validaCC(parametros);

                    if (!cc) {
                        ss.msgErr = "Error en el Centro de costo:" + ss.cc;
                        lisErr2.add(ss);
                        continue;
                    }
                   }
                }

                parametros.put("OPERACION", "U");
                parametros.put("OPE_SUMA", "0");

                val = processDao.execute(store, parametros);

                if (val <= 0) {
                    ss.msgErr = "Error en la cuenta:" + ss.cuenta;
                    lisErr2.add(ss);
                    continue;
                }

                //int sec = detPolizasDao.getMaxId(id);
                //disp.getId().setSec(new BigDecimal(sec));
                boolean  result;
                result = detPolizasDao.update(disp);

                if (!result) {
                    ss.msgErr = "Error al guardar DTO:"+disp.getId().toString();
                    lisErr2.add(ss);

                    continue;
                }

                listaAciertos.add(ss);

                //Suma detalles actualiza
                parametros.put("OPERACION", "S");
                parametros.put("OPE_SUMA", "1");
                val = processDao.execute(store, parametros);

                //Genera el Log del Detalle de polizas
                idLog.setUsuario(usuario);
                idLog.setNumPoliza(ss.numero);
                idLog.setTipoPoliza(ss.tipoPoliza);
                idLog.setFechaPoliza(disp.getId().getFecha());
                log.setId(idLog);
                log.setFecha(new Date());
                log.setAccion("ACTUALIZA_DETPOLIZA, SEC: " + ss.sec);

                LogPolizasId resultLog = logPolizasDao.save(log);

            }

            System.out.println("aciertos:" + listaAciertos.size());
            System.out.println("errores:" + lisErr2.size());
            
             if (listaAciertos.size()>0) guardado = 0;
            if (lisErr2.size()>0) guardado = 2;
           
            

            if (guardado == 0) {

              
                    rq.setSuccess(true);
                    rq.setData(listaAciertos);
                    rq.setDataErr(lisErr2);
                    rq.setMsg("Guardados Correctamente");
                 
            } else {
                rq.setSuccess(false);
                rq.setData(null);
                rq.setDataErr(lisErr2);
                rq.setMsg("Error al actualizar detalles de polizas");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        

        return rq;
    }

    @RequestMapping(value = "/polizaDet/delDet", method = RequestMethod.POST)
    public @ResponseBody
    ResponseQuery2 deleteAction(String data, WebRequest webRequest, Model model) {

              ResponseQuery2 rq = new ResponseQuery2();
        SimpleDateFormat formatoDelTexto = new SimpleDateFormat("dd/MM/yyyy");
        int index = data.indexOf("[");
        if (index == -1) {
            data = "[" + data + "]";
        }
           
        System.out.println("data:" + data);
//
        if (model.asMap().get("compania") == null) {
            rq.setSuccess(false);
            rq.setData(null);
            rq.setMsg("La sesion no es valida.");
            return rq;
        }
        String compania = model.asMap().get("compania").toString();
        String usuario = model.asMap().get("usuario").toString();
        List<DetControlPolizaDTO> lisErr2 = new ArrayList<DetControlPolizaDTO>();
        List<DetControlPolizaDTO> listaAciertos = new ArrayList<DetControlPolizaDTO>();

        try {
            int guardado = 0;
            String fecha = null;
            ObjectMapper mapper = new ObjectMapper();
            DateFormat df = new SimpleDateFormat("dd/MM/yyyy");

            mapper.setDateFormat(df);
            List<DetControlPolizaDTO> lista = mapper.readValue(data,
                    mapper.getTypeFactory().constructCollectionType(List.class,
                            DetControlPolizaDTO.class));

            if (lista.isEmpty()) {
                rq.setSuccess(false);
                rq.setData(null);
                rq.setMsg("Error no existen datos que guardar");
                return rq;
            }
            DetDIOT diot = new DetDIOT();
            DetDIOTId diotId = new DetDIOTId();
            int val = 0;
            Iterator<DetControlPolizaDTO> it = lista.iterator();
            Querys que = new Querys();
            String store = que.getSQL("CONTA_INSERTA_DETPOLIZAS_FOVI3");
            while (it.hasNext()) {
//                 System.out.println("-------------------------------------------------------------------");
                DetControlPolizaDTO ss = it.next();

                ss.hora = "" + System.currentTimeMillis();
//                ss.cargosBase = ss.cargos;
//                ss.abonosBase = ss.abonos;

                @SuppressWarnings("unchecked")
                Map<String, Object> parametros = mapper.convertValue(ss, Map.class);

                parametros.put("compania", compania);
                parametros.put("usuario", usuario);
                System.out.println(parametros.toString());

                // PolizasId polizasId = mapper.convertValue(ss, PolizasId.class);
                DetPolizasId id = mapper.convertValue(ss, DetPolizasId.class);
                DetPolizas disp = mapper.convertValue(ss, DetPolizas.class);

                disp.setId(id);

                LogPolizasId idLog = new LogPolizasId();
                LogPolizas log = new LogPolizas();
                parametros.put("estatus","2,99");
                boolean estatus = detalleValidation.validaEstatus(parametros);

                if (!estatus) {
                    rq.setSuccess(false);
                    rq.setData(null);
                    rq.setMsg(detalleValidation.getMsgError());
                    return rq;
                }
                
                
                boolean estatusP = detalleValidation.validaPeriodo(parametros);

                if (!estatusP) {
                    rq.setSuccess(false);
                    rq.setData(null);
                    rq.setMsg(detalleValidation.getMsgError());
                    return rq;
                }

//                boolean cuenta = detalleValidation.validaCuenta(parametros);
//
//                if (!cuenta) {
//                    ss.msgErr = "Error Cuenta:" + ss.cuenta;
//                    lisErr2.add(ss);
//                    continue;
//                }
//                if(ss.cc==null){
//                }else if (ss.cc != null || !ss.cc.equals("")) {
//                    boolean cc = detalleValidation.validaCC(parametros);
//
//                    if (!cc) {
//                        ss.msgErr = "Error en el Centro de costo:" + ss.cc;
//                        lisErr2.add(ss);
//                        continue;
//                    }
//                }

                parametros.put("OPERACION", "D");
                parametros.put("OPE_SUMA", "0");

                val = processDao.execute(store, parametros);

                if (val <= 0) {
                    ss.msgErr = "Error en la cuenta:" + ss.cuenta;
                    lisErr2.add(ss);
                    continue;
                }

                //int sec = detPolizasDao.getMaxId(id);
                //disp.getId().setSec(new BigDecimal(sec));
                boolean  result;
                result = detPolizasDao.delete(disp);

                if (!result) {
                    ss.msgErr = "Error al borrar DTO:"+disp.getId().toString();
                    lisErr2.add(ss);

                    continue;
                }
                
                diotId.setCompania(ss.compania);
                diotId.setNumero(ss.numero);
                diotId.setFecha(df.parse(ss.fecha));
                diotId.setTipoPoliza(ss.tipoPoliza);
                diotId.setSec(Integer.parseInt(ss.sec));
                diotId.setCuenta(ss.cuenta);
                diot.setId(diotId);
                
                boolean borradoDiot = detDIOTDao.deleteDiot(diot);

                listaAciertos.add(ss);

                //Suma detalles actualiza
                parametros.put("OPERACION", "S");
                parametros.put("OPE_SUMA", "1");
                val = processDao.execute(store, parametros);

                //Genera el Log del Detalle de polizas
                idLog.setUsuario(usuario);
                idLog.setNumPoliza(ss.numero);
                idLog.setTipoPoliza(ss.tipoPoliza);
                idLog.setFechaPoliza(disp.getId().getFecha());
                log.setId(idLog);
                log.setFecha(new Date());
                log.setAccion("BORRA_DETPOLIZA, SEC: " + ss.sec);

                LogPolizasId resultLog = logPolizasDao.save(log);

            }

            System.out.println("aciertos:" + listaAciertos.size());
            System.out.println("errores:" + lisErr2.size());
            if (lisErr2.size()>0) guardado = 2;
            if (listaAciertos.size()>0) guardado = 0;
            

            if (guardado == 0) {

                rq.setSuccess(true);
                rq.setData(listaAciertos);
                rq.setDataErr(lisErr2);
                rq.setMsg("Borrados Correctamente");
            } else {
                rq.setSuccess(false);
                rq.setData(null);
                rq.setDataErr(lisErr2);
                rq.setMsg("Error al borrar detalles de polizas");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        

        return rq;
    }

    public void setDetPolizasDao(DetPolizasDao detPolizasDao) {
        this.detPolizasDao = detPolizasDao;
    }

    public void setProcessDao(ProcessDao processDao) {
        this.processDao = processDao;
    }

    public void setLogPolizasDao(LogPolizasDao logPolizasDao) {
        this.logPolizasDao = logPolizasDao;
    }

    public void setDetalleValidation(DetalleValidation detalleValidation) {
        this.detalleValidation = detalleValidation;
    }

    public void setDetDIOTDao(DetDIOTDao detDIOTDao) {
        this.detDIOTDao = detDIOTDao;
    }
    
    

}
