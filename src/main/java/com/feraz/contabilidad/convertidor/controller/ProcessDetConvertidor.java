/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.feraz.contabilidad.convertidor.controller;

/**
 *
 * @author Feraz3
 */
import com.fasterxml.jackson.databind.ObjectMapper;
import com.feraz.contabilidad.convertidor.dto.ConvertidorDetDTO;
import com.feraz.polizas3.validation.DetalleValidation;
import com.feraz.contabilidad.convertidor.util.ResponseQuery2;
import com.feraz.contabilidad.convertidor.model.ErpDetConvertidor;
import com.feraz.contabilidad.convertidor.model.ErpDetConvertidorId;
import com.feraz.contabilidad.convertidor.dao.ErpDetConvertidorDao;
import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import org.jamh.data.process.ProcessDao;
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
public class ProcessDetConvertidor {
    
    private ErpDetConvertidorDao erpDetConvertidorDao;
    private ProcessDao processDao;
    private DetalleValidation detalleValidation;

    
      @RequestMapping(value = "/convDet/insDet", method = RequestMethod.POST)
    public @ResponseBody
    ResponseQuery2 insertAction(String data,  WebRequest webRequest, Model model) {

        ResponseQuery2 rq = new ResponseQuery2();
        SimpleDateFormat formatoDelTexto = new SimpleDateFormat("dd/MM/yyyy");
             if (data.substring(0, 1).equals("{")) {
            data = "[" + data + "]";
        }
   
        
//         Map parametros = processDao.paramToMap(webRequest.getParameterMap());
//        
//         System.out.println("sec acso:" + parametros.get("NO_CASO").toString());
        
//        System.out.println("data:" + data);
//      
        String compania = model.asMap().get("compania").toString();
      
       // String compania = model.asMap().get("compania").toString();
        //String usuario = model.asMap().get("usuario").toString();
        List<ConvertidorDetDTO> lisErr2 = new ArrayList<ConvertidorDetDTO>();
        List<ConvertidorDetDTO> listaAciertos = new ArrayList<ConvertidorDetDTO>();

        try {
            int guardado = 0;
           
            ObjectMapper mapper = new ObjectMapper();
            DateFormat df = new SimpleDateFormat("dd/MM/yyyy");

            mapper.setDateFormat(df);
            List<ConvertidorDetDTO> lista = mapper.readValue(data,
                    mapper.getTypeFactory().constructCollectionType(List.class,
                            ConvertidorDetDTO.class));

            if (lista.isEmpty()) {
                rq.setSuccess(false);
                rq.setData(null);
                rq.setMsg("Error no existen datos que guardar");
                return rq;
            }

            int val = 0;
            Iterator<ConvertidorDetDTO> it = lista.iterator();
            while (it.hasNext()) {
//                 System.out.println("-------------------------------------------------------------------");
                ConvertidorDetDTO ss = it.next();

           //     ss.cCostos = ss.cc;

                @SuppressWarnings("unchecked")
                Map<String, Object> parametros = mapper.convertValue(ss, Map.class);

//                System.out.println(parametros.toString());
//                System.out.println("caso"+ss.noCaso);
//                
         
                ErpDetConvertidorId id = new  ErpDetConvertidorId();
                ErpDetConvertidor disp = new ErpDetConvertidor();
//                System.out.println(ss.compania+ss.idConCGastos+ss.noCaso+ss.origen);
                id.setCompania(ss.compania);
                id.setIdconcgasto(ss.idConCGastos);
                id.setNoCaso(new BigDecimal(ss.noCaso));
                id.setOrigen(ss.origen);
                 int sec = erpDetConvertidorDao.getMaxIdDetConvertidor(id);
                 id.setSec(sec);
                disp.setId(id);
               
               
                disp.setIdcampo(ss.idCampo);
                disp.setCuenta(ss.cuenta);
                disp.setDescripcion(ss.descripcion);
                disp.setReferencia(ss.referencia);
                disp.setOperaciones(ss.operaciones);
                disp.settAplicacion(ss.tAplicacion);
                
                System.out.println("---------------ss.referencia2: "+ss.referencia2);
                
                disp.setReferencia2(ss.referencia2);
                disp.setRfc(ss.rfc);  
                if( ss.conceptoDiot != null){
                  disp.setConceptoDiot(ss.conceptoDiot);
                }
                if (ss.c_costos != null){
                      disp.setC_costos(ss.c_costos);
                }
                 if (ss.orden != null){
                     
                     disp.setOrden(Integer.parseInt(ss.orden));
                   
                }else{
                    
                    disp.setOrden(1);
                
                }
                 
                
                System.out.println("auxiliarProveedor"+ss.cuenta.indexOf("auxiliarProveedor"));
                System.out.println("auxiliarProveedor"+ss.cuenta);
                        
             //Comodines para  cuentas   
             if(ss.cuenta.indexOf("cuentaProveedor")>-1 || ss.cuenta.indexOf("auxiliarProveedor") >-1 || ss.cuenta.indexOf("auxiliarCliente") >-1 || ss.cuenta.indexOf("cuentaCliente") >-1  ){
             
             }else{
                String query1 = "BuscarCuentaConvertidor";
            
                 Map param = new HashMap();
                  param.put("compania", compania);
                  param.put("cuenta_alias", ss.cuenta);
        
       
                   List list1 = processDao.getMapResult(query1, param);
                //   cuentaProveedor"));
//        System.out.println("cuentaClientes:" + datosFE.get(""));
//        System.out.println("auxiliarProveedores:" + datosFE.get("auxiliarProveedor"));
//        System.out.println("auxiliarProveedores:" + datosFE.get("auxiliarCliente
            
            
            if (list1.size() == 0){
                
                 rq.setSuccess(false);
                rq.setData(null);
               
                rq.setMsg("La cuenta: "+ss.cuenta+" no es valida");
                
                return rq;
                
                
            }
             }
            
          
               ErpDetConvertidorId result = erpDetConvertidorDao.save(disp);
                
                
                
//                if(disp.getAbonos()==null){
//                
//                    disp.setAbonos(BigDecimal.ZERO);
//                    disp.setAbonosBase(BigDecimal.ZERO);
//           
//                }
//
//                disp.setId(id);
//
//          
//                int sec = erpDetConvertidorDao.getMaxIdDetConvertidor(id);
//                disp.setSec(sec);
//                
//                System.out.println("sec det:"+sec);
                
                //DetPolizasId result = null;
                //result = detPolizasDao.save(disp);

                if (result == null) {
                    //ss.msgErr = "Error al guardar DTO" + result.toString();
                    lisErr2.add(ss);

                    continue;
                }
//                ss.sec=""+result.getSec();
//                ss.id2=""+result.getSec();
//                listaAciertos.add(ss);

              
            }

//            System.out.println("aciertos:" + listaAciertos.size());
//            System.out.println("errores:" + lisErr2.size());
//            
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
                rq.setMsg("Error al guardar detalles del Convertidor");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return rq;

    }

        @RequestMapping(value = "/converDet/uptDet", method = RequestMethod.POST)
        public @ResponseBody
        ResponseQuery2 updateAction(String data, WebRequest webRequest, Model model) {
    
             ResponseQuery2 rq = new ResponseQuery2();
        SimpleDateFormat formatoDelTexto = new SimpleDateFormat("dd/MM/yyyy");
      //  System.out.println("data:"+data.substring(0, 1));
        //int index = data.substring(1, 1);
        if (data.substring(0, 1).equals("{")) {
            data = "[" + data + "]";
        }
        
//         Map parametros = processDao.paramToMap(webRequest.getParameterMap());
//        
//         System.out.println("sec acso:" + parametros.get("NO_CASO").toString());
        
       
//
      
        String compania = model.asMap().get("compania").toString();
        //String usuario = model.asMap().get("usuario").toString();
        List<ConvertidorDetDTO> lisErr2 = new ArrayList<ConvertidorDetDTO>();
        List<ConvertidorDetDTO> listaAciertos = new ArrayList<ConvertidorDetDTO>();

        try {
            int guardado = 0;
           
            ObjectMapper mapper = new ObjectMapper();
            DateFormat df = new SimpleDateFormat("dd/MM/yyyy");

            mapper.setDateFormat(df);
            
           
            
            
            List<ConvertidorDetDTO> lista = mapper.readValue(data,
                    mapper.getTypeFactory().constructCollectionType(List.class,
                            ConvertidorDetDTO.class));

            if (lista.isEmpty()) {
                rq.setSuccess(false);
                rq.setData(null);
                rq.setMsg("Error no existen datos que guardar");
                return rq;
            }

            int val = 0;
            Iterator<ConvertidorDetDTO> it = lista.iterator();
            while (it.hasNext()) {
//                 System.out.println("-------------------------------------------------------------------");
                ConvertidorDetDTO ss = it.next();

           //     ss.cCostos = ss.cc;

                @SuppressWarnings("unchecked")
                Map<String, Object> parametros = mapper.convertValue(ss, Map.class);

//                System.out.println(parametros.toString());
//                System.out.println("caso"+ss.noCaso);
                 System.out.println("centro de costos:" + ss.c_costos);
         
                ErpDetConvertidorId id = new  ErpDetConvertidorId();
                ErpDetConvertidor disp = new ErpDetConvertidor();
                
                id.setCompania(ss.compania);
                id.setIdconcgasto(ss.idConCGastos);
                id.setNoCaso(new BigDecimal(ss.noCaso));
                id.setOrigen(ss.origen);
                id.setSec(ss.sec);
                disp.setId(id);
                //int sec = erpDetConvertidorDao.getMaxIdDetConvertidor(id);
               // disp.setSec(ss.sec);
                disp.setIdcampo(ss.idCampo);
                disp.setCuenta(ss.cuenta);
                if(ss.descripcion==null) ss.descripcion="";
                 if(ss.referencia==null) ss.referencia="";
                 if(ss.referencia2==null) ss.referencia2="";
                  if(ss.operaciones==null) ss.operaciones="";
                   if(ss.tAplicacion==null) ss.tAplicacion="";
                   if(ss.rfc==null) ss.rfc="";
                if (ss.c_costos != null){
                    if (!ss.c_costos.equalsIgnoreCase("null")){
                      disp.setC_costos(ss.c_costos);
                    }else{
                         disp.setC_costos("");
                    }
                }else{
                    
                    disp.setC_costos("");
                
                }
                    
                disp.setDescripcion(ss.descripcion);
                disp.setReferencia(ss.referencia);
                disp.setReferencia2(ss.referencia2);
                disp.setOperaciones(ss.operaciones);
                disp.settAplicacion(ss.tAplicacion);
                 disp.setRfc(ss.rfc);
          
                 if (ss.conceptoDiot != null){
                    if (!ss.conceptoDiot.equalsIgnoreCase("null")){
                      disp.setConceptoDiot(ss.conceptoDiot);
                    }else{
                        disp.setConceptoDiot("");
                    }
                }else{
                    
                    disp.setConceptoDiot("");
                
                }
                 
                 if (ss.orden != null){
                     
                     disp.setOrden(Integer.parseInt(ss.orden));
                   
                }else{
                    
                    disp.setOrden(1);
                
                }
                 
                
                 
                 
//                    System.out.println("auxiliarProveedor"+ss.cuenta.indexOf("auxiliarProveedor"));
//                System.out.println("auxiliarProveedor"+ss.cuenta);
                        
             //Comodines para  cuentas   
             if(ss.cuenta.indexOf("cuentaProveedor")>-1 || ss.cuenta.indexOf("auxiliarProveedor") >-1 || ss.cuenta.indexOf("auxiliarCliente") >-1 || ss.cuenta.indexOf("cuentaCliente") >-1  ){
             
             }else{
                
                    String query1 = "BuscarCuentaConvertidor";
            
                 Map param = new HashMap();
                  param.put("compania", compania);
                  param.put("cuenta_alias", ss.cuenta);
        
       
                   List list1 = processDao.getMapResult(query1, param);
                // String [] array = list.get(o);
//                 Map result1 = (HashMap) list1.get(0);
//                  System.out.println("select:" + list1.size());
//                  
//            String existeCuenta = result1.get("CUENTA_ALIAS").toString();
            
            if (list1.size() == 0){
                
                 rq.setSuccess(false);
                rq.setData(null);
               
                rq.setMsg("La cuenta: "+ss.cuenta+" no es valida");
                
                return rq;
                
                
            }
             }
                
               boolean result = erpDetConvertidorDao.updateDetConvPorSec(ss.compania,ss.idConCGastos,ss.origen,new BigDecimal(ss.noCaso), new BigDecimal(ss.sec), disp);
                
                
    
//                if(disp.getAbonos()==null){
//                
//                    disp.setAbonos(BigDecimal.ZERO);
//                    disp.setAbonosBase(BigDecimal.ZERO);
//           
//                }
//
//                disp.setId(id);
//
//          
//                int sec = erpDetConvertidorDao.getMaxIdDetConvertidor(id);
//                disp.setSec(sec);
//                
//                System.out.println("sec det:"+sec);
                
                //DetPolizasId result = null;
                //result = detPolizasDao.save(disp);

                if (result == false) {
                    //ss.msgErr = "Error al guardar DTO" + result.toString();
                    lisErr2.add(ss);

                    continue;
                }
//                ss.sec=""+result.getSec();
//                ss.id2=""+result.getSec();
//                listaAciertos.add(ss);

              
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
                rq.setMsg("Error al guardar detalles del Convertidor");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return rq;
        }
    
        @RequestMapping(value = "/convDet/delDet", method = RequestMethod.POST)
        public @ResponseBody
        ResponseQuery2 deleteAction(String data, WebRequest webRequest, Model model) {
    
                  ResponseQuery2 rq = new ResponseQuery2();
            SimpleDateFormat formatoDelTexto = new SimpleDateFormat("dd/MM/yyyy");
           if (data.substring(0, 1).equals("{")) {
            data = "[" + data + "]";
           }
   
    
            System.out.println("data:" + data);
            
             List<ConvertidorDetDTO> lisErr2 = new ArrayList<ConvertidorDetDTO>();
             List<ConvertidorDetDTO> listaAciertos = new ArrayList<ConvertidorDetDTO>();
    //
        
    
            try {
                int guardado = 0;
                String fecha = null;
                ObjectMapper mapper = new ObjectMapper();
                DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
    
                mapper.setDateFormat(df);
                List<ConvertidorDetDTO> lista = mapper.readValue(data,
                        mapper.getTypeFactory().constructCollectionType(List.class,
                                ConvertidorDetDTO.class));
    
               
    
                int val = 0;
                Iterator<ConvertidorDetDTO> it = lista.iterator();
                
                while (it.hasNext()) {
//                     System.out.println("-------------------------------------------------------------------");
                    ConvertidorDetDTO ss = it.next();
    
    //                ss.cargosBase = ss.cargos;
    //                ss.abonosBase = ss.abonos;
    
                    @SuppressWarnings("unchecked")
                    Map<String, Object> parametros = mapper.convertValue(ss, Map.class);
    
                 
                    System.out.println(parametros.toString());
                    
                     ErpDetConvertidorId id = new  ErpDetConvertidorId();
                    ErpDetConvertidor disp = new ErpDetConvertidor();

                    id.setCompania(ss.compania);
                    id.setIdconcgasto(ss.idConCGastos);
                    id.setNoCaso(new BigDecimal(ss.noCaso));
                    id.setOrigen(ss.origen);
                    id.setSec(ss.sec);
                    disp.setId(id);
                    disp.setIdcampo(ss.idCampo);
                    
                    
                    
    
                    // PolizasId polizasId = mapper.convertValue(ss, PolizasId.class);
               
    
                    //int sec = detPolizasDao.getMaxId(id);
                    //disp.getId().setSec(new BigDecimal(sec));
                    boolean  result;
                    result = erpDetConvertidorDao.borraDetConvPorSec(ss.compania,ss.idConCGastos,ss.origen,new BigDecimal(ss.noCaso), new BigDecimal(ss.sec));
    
                    if (!result) {
     //                   ss.msgErr = "Error al borrar DTO:"+disp.getId().toString();
                        lisErr2.add(ss);
    
                        continue;
                    }
    
                    listaAciertos.add(ss);
    
                    //Suma detalles actualiza
              
    
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
                    rq.setMsg("Error al borrar detalles de convertidor");
                }
    
            } catch (Exception e) {
                e.printStackTrace();
            }
    
    
    
            return rq;
        }
        
          @RequestMapping(value = "/copiarconvertidordet", method = RequestMethod.POST)
    public @ResponseBody
    ResponseQuery2 copiActionDet(String data,  WebRequest webRequest, Model model) {

        ResponseQuery2 rq = new ResponseQuery2();
        SimpleDateFormat formatoDelTexto = new SimpleDateFormat("dd/MM/yyyy");
        int index = data.indexOf("[");
        if (index == -1) {
            data = "[" + data + "]";
        }
        
//         Map parametros = processDao.paramToMap(webRequest.getParameterMap());
//        
//         System.out.println("sec acso:" + parametros.get("NO_CASO").toString());
        
        System.out.println("data:" + data);
//
      
       // String compania = model.asMap().get("compania").toString();
        //String usuario = model.asMap().get("usuario").toString();
        List<ConvertidorDetDTO> lisErr2 = new ArrayList<ConvertidorDetDTO>();
        List<ConvertidorDetDTO> listaAciertos = new ArrayList<ConvertidorDetDTO>();

        try {
            int guardado = 0;
           
            ObjectMapper mapper = new ObjectMapper();
            DateFormat df = new SimpleDateFormat("dd/MM/yyyy");

            mapper.setDateFormat(df);
            List<ConvertidorDetDTO> lista = mapper.readValue(data,
                    mapper.getTypeFactory().constructCollectionType(List.class,
                            ConvertidorDetDTO.class));

            if (lista.isEmpty()) {
                rq.setSuccess(false);
                rq.setData(null);
                rq.setMsg("Error no existen datos que guardar");
                return rq;
            }

            int val = 0;
            Iterator<ConvertidorDetDTO> it = lista.iterator();
            while (it.hasNext()) {
//                 System.out.println("-------------------------------------------------------------------");
                ConvertidorDetDTO ss = it.next();

           //     ss.cCostos = ss.cc;

                @SuppressWarnings("unchecked")
                Map<String, Object> parametros = mapper.convertValue(ss, Map.class);

                System.out.println(parametros.toString());
                System.out.println("caso"+ss.noCaso);
                
         
                ErpDetConvertidorId id = new  ErpDetConvertidorId();
                ErpDetConvertidor disp = new ErpDetConvertidor();
                System.out.println(ss.compania+ss.idConCGastos+ss.noCaso+ss.origen);
                id.setCompania(ss.compania);
                id.setIdconcgasto(ss.idConCGastos);
                id.setNoCaso(new BigDecimal(ss.noCaso));
                id.setOrigen(ss.origen);
                 int sec = erpDetConvertidorDao.getMaxIdDetConvertidor(id);
                 id.setSec(sec);
                disp.setId(id);
               
               
                disp.setIdcampo(ss.idCampo);
                disp.setCuenta(ss.cuenta);
                disp.setDescripcion(ss.descripcion);
                disp.setReferencia(ss.referencia);
                disp.setReferencia(ss.referencia2);
                disp.setOperaciones(ss.operaciones);
                disp.settAplicacion(ss.tAplicacion);
                if( ss.conceptoDiot != null){
                  disp.setConceptoDiot(ss.conceptoDiot);
                }
                 if (ss.orden != null){
                     
                     disp.setOrden(Integer.parseInt(ss.orden));
                   
                }else{
                    
                    disp.setOrden(1);
                
                }
                 
                
               ErpDetConvertidorId result = erpDetConvertidorDao.save(disp);
                
                
                
//                if(disp.getAbonos()==null){
//                
//                    disp.setAbonos(BigDecimal.ZERO);
//                    disp.setAbonosBase(BigDecimal.ZERO);
//           
//                }
//
//                disp.setId(id);
//
//          
//                int sec = erpDetConvertidorDao.getMaxIdDetConvertidor(id);
//                disp.setSec(sec);
//                
//                System.out.println("sec det:"+sec);
                
                //DetPolizasId result = null;
                //result = detPolizasDao.save(disp);

                if (result == null) {
                    //ss.msgErr = "Error al guardar DTO" + result.toString();
                    lisErr2.add(ss);

                    continue;
                }
//                ss.sec=""+result.getSec();
//                ss.id2=""+result.getSec();
//                listaAciertos.add(ss);

              
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
                rq.setMsg("Error al guardar detalles del Convertidor");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return rq;

    }
        
        
    public ErpDetConvertidorDao getErpDetConvertidorDao() {
        return erpDetConvertidorDao;
    }

    public ProcessDao getProcessDao() {
        return processDao;
    }

    public void setErpDetConvertidorDao(ErpDetConvertidorDao erpDetConvertidorDao) {
        this.erpDetConvertidorDao = erpDetConvertidorDao;
    }

    public void setProcessDao(ProcessDao processDao) {
        this.processDao = processDao;
    }

    public void setDetalleValidation(DetalleValidation detalleValidation) {
        this.detalleValidation = detalleValidation;
    }

    
}
