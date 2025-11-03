/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.feraz.polizas3.controll;

import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.feraz.contabilidad.sat.diot.dao.DetDIOTDao;
import com.feraz.polizas3.dao.DetPolizasDao;
import com.feraz.polizas3.dao.ErpPolizasXFacturasDao;
import com.feraz.polizas3.dao.PolizasDao;
import com.feraz.polizas3.dto.PolizasDTO;
import com.feraz.polizas3.model.PolizasDTOUpdate;
import com.feraz.polizas3.model.DetPolizas;
import com.feraz.polizas3.model.DetPolizasId;
import com.feraz.polizas3.model.ErpPolizasXFacturas;
import com.feraz.polizas3.model.ErpPolizasXFacturasId;
import com.feraz.polizas3.model.Polizas;
import com.feraz.polizas3.model.PolizasId;
import com.feraz.polizas3.util.BeanPropertyCopyUtil;
import com.feraz.polizas3.validation.MaestroValidation;
import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import org.apache.log4j.Logger;
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

/**
 *
 * @author Ing. JAMH
 */
@Controller
@RequestMapping("/control")
@SessionAttributes({"compania", "usuario"})
public class ProcessPolizas {
    
    
    private PolizasDao polizasDao;
     private ProcessDao processDao;
    private MaestroValidation maestroValidation;
    private DetPolizasDao detPolizasDao;
     private ErpPolizasXFacturasDao erpPolizasXFacturasDao;
     
     private  DetDIOTDao detDIOTDao; 
     
      private static final Logger log = Logger.getLogger(ProcessPolizas.class);

    @RequestMapping(value = "/poliza/ins", method = RequestMethod.POST)
    public @ResponseBody
    ResponseQuery insertAction(String data, WebRequest webRequest, Model model) {

        boolean isSave;
//        System.out.println("llegando a insert");
         System.out.println("insert:"+data);
//        Map parametros = processDao.paramToMap(webRequest.getParameterMap());
//        parametros.put("compania", model.asMap().get("compania"));
//        parametros.put("usuario", model.asMap().get("usuario"));
        ResponseQuery rq = null;
        rq = new ResponseQuery();
        String compania= model.asMap().get("compania").toString();
        String usuario=model.asMap().get("usuario").toString();
        if (model.asMap().get("usuario") == null) {

            rq.setSuccess(false);
            rq.setData(null);
            rq.setMsg("session invalid");
            return rq;
        }

        data = data.replaceAll("\\[", "");
        data = data.replaceAll("\\]", "");
        
         PolizasDTO polDto= new PolizasDTO();
//        validateEmpleado.setCompania(model.asMap().get("compania").toString());
//        validateEmpleado.setUsuario(model.asMap().get("usuario").toString());

//        boolean vEmp = validateEmpleado.validateInsert(data);
//        if (!vEmp) {
//            rq.setSuccess(false);
//            rq.setData(null);
//            rq.setMsg("Error al Guardar Empleado :" + validateEmpleado.getMsgErr());
//            return rq;
//        }
        Polizas poliza = null;
        try {
            ObjectMapper mapper = new ObjectMapper();
            DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
            mapper.setDateFormat(df);

            poliza = mapper.readValue(data, Polizas.class);
            poliza.setCompaniaTemp(compania);
            poliza.setUsuario(usuario);
//            System.out.println("date:"+poliza.getFechaTemp());
            PolizasId id = new PolizasId(poliza.getCompaniaTemp(), poliza.getTipoPolizaTemp(), poliza.getFechaTemp(), poliza.getNumeroTemp());
            poliza.setId(id);
            if(poliza.getAbonos()==null || poliza.getCargos() == null){
            poliza.setAbonos(BigDecimal.ZERO);
            poliza.setAbonosBase(BigDecimal.ZERO);
            poliza.setCargos(BigDecimal.ZERO);
            poliza.setCargosBase(BigDecimal.ZERO);
            }
            poliza.setFechaCap(new Date());
            poliza.setHora(""+System.currentTimeMillis());
            
            
            if(poliza.getTipoSolicitud().equalsIgnoreCase("")||poliza.getTipoSolicitud() == null ){
                
            }else{
             int claveTipoS =  poliza.getTipoSolicitud().indexOf("-");
             
             String tipoSolicitud = poliza.getTipoSolicitud();
                
             String claveTipoSC = poliza.getTipoSolicitud().substring(0,claveTipoS);
                
             poliza.setTipoSolicitud(claveTipoSC);
             polDto.setTipoSolicitud(tipoSolicitud);
            
            }
            boolean periodo = maestroValidation.validaPeriodo(poliza);
            
//            System.out.println("periodo"+periodo);
            if(periodo == false) {
                rq.setSuccess(false);
                rq.setData(null);
                rq.setMsg(maestroValidation.getMsgError());
                return rq;
            }
//            boolean estatus = maestroValidation.validaEstatus(poliza);
//            if(estatus = false) {
//                rq.setSuccess(false);
//                rq.setData(null);
//                rq.setMsg(maestroValidation.getMsgError());
//                return rq;
//            }
            
            
                   
            
            PolizasId id2 = polizasDao.save(poliza);

              System.out.println("id2"+id2);
             
       
			
	   //  BeanUtils.copyProperties(polDto,poliza );
	
             
           
             
            
              
            if (id2 == null) {
                isSave = false;
            } else {
                isSave = true;
            }

            if (isSave) {
                
                
                     BeanPropertyCopyUtil.copyProperties(poliza, polDto,"abonos abonos","cargos cargos",
                             "nombre nombre",
                             "divisa divisa",
                             "paridad paridad",
                             "fuente fuente",
                             "referencia referencia",
                             "cargosBase cargosBase",
                             "abonosBase abonosBase",
                             "estatus estatus",
                             "usuario usuario",
                           //  "fechaCap fechaCap",
                             "hora hora",
                             "moduloOrig moduloOrig",
                             "reviso reviso",
                             "autorizo autorizo"
                             );
                     
                     SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
                     
                     polDto.setCompania(id2.getCompania());
                     polDto.setNumero(id2.getNumero());
                     polDto.setTipoPoliza(id2.getTipoPoliza());
                  //   System.out.println(id2.getFecha());
                     polDto.setFecha(format.format(id2.getFecha()));
                     
                     polDto.setNumTramite(poliza.getNumTramite());
                     polDto.setNumOrden(poliza.getNumOrden());
                     
                List lista = new ArrayList();
                polDto.setId(""+System.currentTimeMillis());
                polDto.setId2(""+System.currentTimeMillis());
                lista.add(polDto);
                rq.setSuccess(true);
                rq.setData(lista);
                rq.setMsg("Ready");
            } else {
                rq.setSuccess(false);
                rq.setData(null);
                rq.setMsg("data null");
            }

        } catch (Exception e) {
            isSave = false;
            e.printStackTrace();
        }

        return rq;
    }
    
     @RequestMapping(value = "/poliza/upd", method = RequestMethod.POST)
    public @ResponseBody
    ResponseQuery updateAction(String data, WebRequest webRequest, Model model) {
         
          System.out.println("dataaaaaU:" + data);
        //Map parametros = processDao.paramToMap(webRequest.getParameterMap());
        data=data.replaceAll("&", "");
        int index = data.indexOf("[");
      
        
        if(index == -1){
            
            data = "["+data+"]";
//            System.out.println("data2" + data);
        }
         System.out.println("data:" + data);
         ResponseQuery rq = new ResponseQuery();
//
        String compania = model.asMap().get("compania").toString();
        String usuario = model.asMap().get("usuario").toString();
        boolean result = false;
        
        try {
//            System.out.println("intentando");
            ObjectMapper mapper = new ObjectMapper();  
             DateFormat df = new SimpleDateFormat("dd/MM/yyyy");

            mapper.setDateFormat(df);
//            JsonFactory f = new JsonFactory();
//            JsonParser jp = f.createJsonParser(data);

           // System.out.println("jp:"+jp.getValueAsString());
            
        List<PolizasDTO> lista = mapper.readValue(data,
                    mapper.getTypeFactory().constructCollectionType(List.class,
                            PolizasDTO.class));
        Iterator<PolizasDTO> it = lista.iterator();
//              System.out.println("jp:"+jp.);
//            List<Polizas> lis = new ArrayList<Polizas>();
//             List<Polizas> lisErr = new ArrayList<Polizas>();
             
          //  SimpleDateFormat format = new SimpleDateFormat("yyyy/MM/dd");
        
//            int i = 1;
            
//            System.out.println("jp.nextToken()  "+jp.nextToken());
//               System.out.println("jp:"+jp.getValueAsString());

          //  while (jp.nextToken() == JsonToken.START_OBJECT) {
               while(it.hasNext()){
                
//                System.out.println("dentro de while");
                PolizasDTO ss = it.next();
                // String idTime = ""+System.currentTimeMillis();
               SimpleDateFormat formatoDelTexto = new SimpleDateFormat("dd/MM/yyyy");
                 String strFecha = ss.getFecha();
                    Date fecha = null;
                 //Obtener maxima secuencia
                    fecha = formatoDelTexto.parse(strFecha);
                    
                   
                     System.out.println("fecha:"+fecha);
//              System.out.println("compania"+ss.getCompania());
//              System.out.println("tipoPoliza"+ss.getTipoPoliza());
              
                PolizasDTOUpdate polizas = new PolizasDTOUpdate();
                Polizas polizas2 = new Polizas();
                PolizasId polizasId = new PolizasId();
                
                polizasId.setCompania(ss.getCompania());
                polizasId.setFecha(fecha);
                polizasId.setNumero(ss.getNumero());
                polizasId.setTipoPoliza(ss.getTipoPoliza());
                  polizas2.setId(polizasId);
                polizas2.setNombre(ss.getNombre());
                polizas2.setDivisa(ss.getDivisa());
                polizas2.setParidad(ss.getParidad());
                polizas2.setFuente(ss.getFuente());
                polizas2.setReferencia(ss.getReferencia());
                polizas2.setCargosBase(ss.getCargosBase());
                polizas2.setAbonosBase(ss.getAbonosBase());
                polizas2.setCargos(ss.getCargos());
                polizas2.setAbonos(ss.getAbonos());
                polizas2.setEstatus(ss.getEstatus());
                polizas2.setUsuario(usuario);
                
                 if(ss.getTipoSolicitud() == null ){
                
                }else{
                
                int claveTipoS =  ss.getTipoSolicitud().indexOf("-");
             
               String tipoSolicitud = ss.getTipoSolicitud();   
               String claveTipoSC = ss.getTipoSolicitud().substring(0,claveTipoS);
                
            
                
                polizas2.setTipoSolicitud(claveTipoSC);
                 polizas.setTipoSolicitud(claveTipoSC);
                
                 } 
                polizas2.setNumTramite(ss.getNumTramite());
                polizas2.setNumOrden(ss.getNumOrden());
//                 if(ss.getFechaCap()==null || ss.getFechaCap()== ""){
//                  
//                    }else{
//                     SimpleDateFormat formatoDelTexto2 = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss aa");
//                   String strFechaCap = ss.getFechaCap();
//                    Date fechaCap = null;
//                 //Obtener maxima secuencia
//                    fechaCap = formatoDelTexto2.parse(strFechaCap);
//                polizas.setFechaCap(fechaCap);
//                 }
                polizas2.setHora(ss.getHora());
                polizas2.setModuloOrig(ss.getModuloOrig());
                polizas2.setReviso(ss.getReviso());
                polizas2.setAutorizo(ss.getAutorizo());
                
                polizas.setId(polizasId);
                polizas.setNombre(ss.getNombre());
                polizas.setDivisa(ss.getDivisa());
                polizas.setParidad(ss.getParidad());
                polizas.setFuente(ss.getFuente());
                polizas.setReferencia(ss.getReferencia());
                polizas.setCargosBase(ss.getCargosBase());
                polizas.setAbonosBase(ss.getAbonosBase());
                polizas.setCargos(ss.getCargos());
                polizas.setAbonos(ss.getAbonos());
                polizas.setEstatus(ss.getEstatus());
                polizas.setUsuario(usuario);
              
                polizas.setNumTramite(ss.getNumTramite());
                polizas.setNumOrden(ss.getNumOrden());
//                 if(ss.getFechaCap()==null || ss.getFechaCap()== ""){
//                  
//                    }else{
//                     SimpleDateFormat formatoDelTexto2 = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss aa");
//                   String strFechaCap = ss.getFechaCap();
//                    Date fechaCap = null;
//                 //Obtener maxima secuencia
//                    fechaCap = formatoDelTexto2.parse(strFechaCap);
//                polizas.setFechaCap(fechaCap);
//                 }
                polizas.setHora(ss.getHora());
                polizas.setModuloOrig(ss.getModuloOrig());
                polizas.setReviso(ss.getReviso());
                polizas.setAutorizo(ss.getAutorizo());
                
                 boolean estatus = maestroValidation.validaEstatus(polizas2,"2");
                System.out.println("estatus:"+estatus);   
                if (!estatus) {
                    rq.setSuccess(false);
                    rq.setData(null);
                    rq.setMsg(maestroValidation.getMsgError());
                    return rq;
                }
                
                result = polizasDao.update(polizas);
   
            }
            if(result==true){
                rq.setSuccess(true);
                rq.setData(null);
                rq.setMsg("Poliza Guardada");
            } else {
                rq.setSuccess(false);
                rq.setData(null);
                rq.setMsg("Error al guardar poliza");
            }

                
           
          }catch (JsonGenerationException ege) {
 
		ege.printStackTrace();
                rq.setSuccess(false);
                rq.setData(null);
                rq.setMsg("Error en los datos enviados");
                return rq;
 
	} catch (JsonMappingException jme) {
            
 
		jme.printStackTrace();
                	
                rq.setSuccess(false);
                rq.setData(null);
                rq.setMsg("Error en el mapeo de datos");
                return rq;
        }catch (Exception e) {
            e.printStackTrace();
       
                rq.setSuccess(false);
                rq.setData(null);
                rq.setMsg("Error en el mapeo de datos");
                return rq;
            
        }

        return rq;
   
     
    }
     
      @RequestMapping(value = "/poliza/del", method = RequestMethod.POST)
    public @ResponseBody
    ResponseQuery deleteAction(String data,@RequestParam("compania") String compania,@RequestParam("fecha") String fecha,@RequestParam("tipoPoliza") String tipoPoliza,@RequestParam("numero") String numero,WebRequest webRequest, Model model) {

//       System.out.println("Controlador delete");
//      
//        Map parametros = processDao.paramToMap(webRequest.getParameterMap());
//        
//        System.out.println("compania"+compania);
//        System.out.println("fecha"+fecha);
//        System.out.println("tipoPoliza"+tipoPoliza);
//        System.out.println("numero"+numero);
             
        //Map parametros = processDao.paramToMap(webRequest.getParameterMap());
       

        ResponseQuery rq = new ResponseQuery();
        
            DetPolizas detPolizas = new DetPolizas();
            DetPolizasId detPolizasId = new DetPolizasId();
            Polizas polizas = new Polizas();
            PolizasId polizasId = new PolizasId();
            ErpPolizasXFacturas erpPolizasXFacturas = new ErpPolizasXFacturas();
            ErpPolizasXFacturasId erpPolizasXFacturasId = new ErpPolizasXFacturasId();
            
       try {
           
           
             SimpleDateFormat formatoDelTexto2 = new SimpleDateFormat("dd/MM/yyyy");
                  
                    Date fechaPol = null;
                 //Obtener maxima secuencia
                    fechaPol = formatoDelTexto2.parse(fecha);
           polizasId.setCompania(compania);
           polizasId.setFecha(fechaPol);
           polizasId.setNumero(numero);
           polizasId.setTipoPoliza(tipoPoliza);
           polizas.setId(polizasId);
            int val = 0;
            
           boolean estatus = maestroValidation.validaEstatus(polizas,"2");
           
            if(estatus == false) {
                rq.setSuccess(false);
                rq.setData(null);
                rq.setMsg(maestroValidation.getMsgError());
//                System.out.println("estatus 2"+maestroValidation.getMsgError());
                return rq;
            }
          
             Querys que = new Querys();
            String store = que.getSQL("OPERACIONES_POLIZAS");
            
             Map param = new HashMap();
                  param.put("compania", compania);
                  param.put("TIPO_POL", tipoPoliza);
                  param.put("FECHA_POL", fecha);
                  param.put("NUMERO_POL", numero);
                   param.put("OPERACION", 'D');
                   
               val = processDao.execute(store, param);

                if (val <= 0) {
                    
                }
           
            boolean result = detPolizasDao.borraDetallePoliza(compania, tipoPoliza, fecha, numero);
            
              Querys que2 = new Querys();
            String store2 = que2.getSQL("CANCELA_POLIZA");
            
            System.out.println("EJECUTANDO CANCELACION DE MODULOS");
            
            log.info("EJECUTANDO CANCELACION DE MODULOS");
            
             Map param2 = new HashMap();
                  param2.put("compania", compania);
                  param2.put("TIPO_POL", tipoPoliza);
                  param2.put("FECHA_POL", fecha);
                  param2.put("NUMERO_POL", numero);
                  
            log.info("store2 :"+store2);
            log.info("compania :"+compania);
            log.info("TIPO_POL :"+tipoPoliza);
            log.info("FECHA_POL :"+fecha);
            log.info("NUMERO_POL :"+numero);
                   
              int rst = processDao.execute(store2, param2);

          log.info("ejecucion :"+rst);
            
           // boolean resutl2 = erpPolizasXFacturasDao.deleteRelacionPorCancelacion(compania, tipoPoliza, fecha, numero);

           if (result == true) {
               // if (resutl2 == true) {
                // lista.add(curso);
                rq.setSuccess(true);
                rq.setData(null);
                rq.setMsg("Borrado Correctamente");
//                }else{
//                  rq.setSuccess(true);
//                rq.setData(null);
//                rq.setMsg("Borrado Correctamente. No se logro quitar la relacion");
//                }
            } else {
                rq.setSuccess(false);
                rq.setData(null);
                rq.setMsg("Error al Guardar");
           }
  } catch (Exception e) {
      
            
            e.printStackTrace();
            
            log.error("error al borrar");
            
            log.info("err :"+e.getMessage());
            
            
            
            
        }

        return rq;
    }
      
   @RequestMapping(value = "/poliza/copyDet", method = RequestMethod.POST)
    public @ResponseBody
    ResponseQuery copyAction(String data,@RequestParam("compania") String compania,@RequestParam("fecha") String fecha,@RequestParam("tipoPoliza") String tipoPoliza,@RequestParam("numero") String numero, @RequestParam("fechaCopi") String fechaCopi,@RequestParam("tipoPolizaCopi") String tipoPolizaCopi,@RequestParam("numeroCopi") String numeroCopi,WebRequest webRequest, Model model) {

//       System.out.println("Controlador copiar poliza");
//       System.out.println("compania"+compania);
//       System.out.println("fecha"+fecha);
//       System.out.println("tipoPoliza"+tipoPoliza);
//       System.out.println("numero"+numero);
//       System.out.println("fechaCopi"+fechaCopi);
//       System.out.println("tipoPolizaCopi"+tipoPolizaCopi);
//       System.out.println("numeroCopi"+numeroCopi);
//      
//        Map parametros = processDao.paramToMap(webRequest.getParameterMap());
//        
     
        //Map parametros = processDao.paramToMap(webRequest.getParameterMap());
       

        ResponseQuery rq = new ResponseQuery();
         Querys que = new Querys();
         int val2 = 0;
            String store = que.getSQL("CopiaPolizasDet");
          
       try {
             Map parametros = new HashMap();
             
                parametros.put("compania_copia", compania);
                parametros.put("tipoPoliza_copia", tipoPolizaCopi);
                parametros.put("fecha_copia",fechaCopi);
                parametros.put("NUMERO_copia",numeroCopi);
                parametros.put("compania",compania);
                parametros.put("numero_original",numero);
                parametros.put("tipoPoliza_original",tipoPoliza);
                parametros.put("fecha_original",fecha);

               int val = processDao.execute(store, parametros);
              
               
               System.out.println(val);

           if (val > 0 && val2 > 0) {

                // lista.add(curso);
                rq.setSuccess(true);
                rq.setData(null);
                rq.setMsg("Borrado Correctamente");
            } else {
                rq.setSuccess(false);
                rq.setData(null);
                rq.setMsg("Error al Guardar");
           }
  } catch (Exception e) {
            e.printStackTrace();
        }

        return rq;
    }
     

    public void setPolizasDao(PolizasDao polizasDao) {
        this.polizasDao = polizasDao;
    }

    public void setMaestroValidation(MaestroValidation maestroValidation) {
        this.maestroValidation = maestroValidation;
    }

    public void setDetPolizasDao(DetPolizasDao detPolizasDao) {
        this.detPolizasDao = detPolizasDao;
    }

    public void setProcessDao(ProcessDao processDao) {
        this.processDao = processDao;
    }

    public void setErpPolizasXFacturasDao(ErpPolizasXFacturasDao erpPolizasXFacturasDao) {
        this.erpPolizasXFacturasDao = erpPolizasXFacturasDao;
    }

    public void setDetDIOTDao(DetDIOTDao detDIOTDao) {
        this.detDIOTDao = detDIOTDao;
    }
    
    

}
