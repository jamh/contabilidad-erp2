/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.feraz.pedidos.controller;

/**
 *
 * @author vavi
 */
import com.fasterxml.jackson.databind.ObjectMapper;
import com.feraz.facturas.webcontrolfe.model.FileUploadBean;
import java.net.*;
import java.io.*;
import com.feraz.pedidos.dao.ErpPedidoMaestroDao;
import com.feraz.pedidos.dto.DetPedidosDTO;
import com.feraz.pedidos.dto.DetPedidosImpuestosDTO;
import com.feraz.pedidos.mail.MailVerificacionProv;
import com.feraz.pedidos.model.ErpPedidoMaestro;
import com.feraz.pedidos.model.ErpPedidoMaestroId;
import com.feraz.polizas3.model.ExtJSFormResult;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import org.jamh.data.process.ProcessDao;
import org.jamh.wf.json.model.ResponseQuery;
import org.jamh.wf.json.model.ResponseQuery2;
import org.jamh.wf.process.Querys;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.multipart.MultipartFile;

/**
 *
 * @author vavi
 */
@Controller
@RequestMapping("/pedidos")
@SessionAttributes({"compania", "usuario"})
public class ControlPedidos {
    
    private ProcessDao processDao;
    private ErpPedidoMaestroDao erpPedidoMaestroDao;
     private MailVerificacionProv mailVerificacionProv;
    
      private final Logger log = LoggerFactory.getLogger(ControlPedidos.class);
      
      
      
              
      @RequestMapping(value = "/copia", method = RequestMethod.POST)
     public @ResponseBody
    ResponseQuery2 copiaAction(String correo,WebRequest webRequest, Model model) throws ParseException {

        ResponseQuery2 rq = new ResponseQuery2();
        SimpleDateFormat formatoDelTexto = new SimpleDateFormat("dd/MM/yyyy");
        SimpleDateFormat formatFecha = new SimpleDateFormat("dd/MM/yyyy");
       
//
        if (model.asMap().get("compania") == null) {
            rq.setSuccess(false);
            rq.setData(null);
            rq.setMsg("La sesion no es valida.");
            return rq;
        }
        String compania = model.asMap().get("compania").toString();
        String usuario = model.asMap().get("usuario").toString();
        
        String folio = webRequest.getParameter("idFolioCopia").toString();
        String fechaRequerida = webRequest.getParameter("pedidosFormFECHA_RCopia").toString();
        
       
               Querys que = new Querys();
               String store = que.getSQL("CopiaPedidos");
               
                   Map parametros = new HashMap();
             
 //  P_SID              VARCHAR2,

             
                parametros.put("compania", compania);
                parametros.put("usuario",usuario);
                parametros.put("fecha",fechaRequerida);
                parametros.put("folio",folio);
           



               int val = processDao.execute(store, parametros);
                
                 if (val > 0){
               
                 
                            rq.setSuccess(true);
                            rq.setMsg("Copia Exitosa");

                    } else {
                        rq.setSuccess(false);
                        rq.setData(null);
                        rq.setMsg("Error al guardar");
                    }
  
        return rq;

    }
    
        @RequestMapping(value = "/insert", method = RequestMethod.POST)
     public @ResponseBody
    ResponseQuery2 insertAction(String correo,WebRequest webRequest, Model model) throws ParseException {

        ResponseQuery2 rq = new ResponseQuery2();
        SimpleDateFormat formatoDelTexto = new SimpleDateFormat("dd/MM/yyyy");
        SimpleDateFormat formatFecha = new SimpleDateFormat("dd/MM/yyyy");
       
//
        if (model.asMap().get("compania") == null) {
            rq.setSuccess(false);
            rq.setData(null);
            rq.setMsg("La sesion no es valida.");
            return rq;
        }
        String compania = model.asMap().get("compania").toString();
        String usuario = model.asMap().get("usuario").toString();
        
        String folio = webRequest.getParameter("idFolio").toString();
        String nombre = webRequest.getParameter("pedidosFormNOMBRE").toString();
        String fechaRequerida = webRequest.getParameter("pedidosFormFECHA_REQUERIDO").toString();
        String cto = webRequest.getParameter("cboCtoOrdenPedidos").toString();
        String tipoPedido = webRequest.getParameter("cboTIPO_PEDIDO").toString();
        String provSugerido = webRequest.getParameter("cboProvSugerido").toString();
        String operacion = webRequest.getParameter("pedidosOperacion").toString();
        String estatus = webRequest.getParameter("pedidosEstatus").toString();
        String proyecto = webRequest.getParameter("cboProyectoPedidoMaestro").toString();
        String produccion = webRequest.getParameter("cboProduccionPedidoMaestro").toString();
        String tipoPedidoUrg = webRequest.getParameter("radioTipoOrigenTipoSol").toString();
        String cliente = webRequest.getParameter("cboClientePedidoMaestro").toString();
        String condiciones = webRequest.getParameter("cboCondicionesPedidoMaestro").toString();
        
        
        
        ErpPedidoMaestro ma = new ErpPedidoMaestro();
        ErpPedidoMaestroId maId = new ErpPedidoMaestroId();
        int id = 0;
        try {
          
            
            if(operacion.equalsIgnoreCase("I")){
                
                maId.setCompania(compania);
                id = erpPedidoMaestroDao.getMaxIdPedidos(maId);
                maId.setFolio(id);
                ma.setId(maId);
                ma.setCtoCto(cto);
                ma.setEstatus("P");
                ma.setIdProveedor(provSugerido);
                ma.setFechaRequerido(formatFecha.parse(fechaRequerida));
                ma.setNombre(nombre);
                ma.setTipoPedido(Integer.parseInt(tipoPedido));
                ma.setUsuario(usuario);
                ma.setIdProyecto(Integer.parseInt(proyecto));
                ma.setTipoProduccion(Integer.parseInt(produccion));
                ma.setSolicitudUrgente(tipoPedidoUrg);
                ma.setCorreoProv(correo);
                ma.setIdCliente(cliente);
                ma.setIdCondiciones(condiciones);
            
                ErpPedidoMaestroId result = erpPedidoMaestroDao.save(ma);
                
                 if (result != null) {
               
                 
                            rq.setSuccess(true);
                            rq.setMsg("Pedido Guardado Correctamente");

                    } else {
                        rq.setSuccess(false);
                        rq.setData(null);
                        rq.setMsg("Error al guardar");
                    }
            
            }
            
            
            if(operacion.equalsIgnoreCase("U")){
                
                maId.setCompania(compania);
                //int id = erpPedidoMaestroDao.getMaxIdPedidos(maId);
                id=Integer.parseInt(folio);
                maId.setFolio(Integer.parseInt(folio));
                ma.setId(maId);
                ma.setCtoCto(cto);
                ma.setEstatus(estatus);
                ma.setIdProveedor(provSugerido);
                ma.setFechaRequerido(formatFecha.parse(fechaRequerida));
                ma.setNombre(nombre);
                ma.setTipoPedido(Integer.parseInt(tipoPedido));
                ma.setUsuario(usuario);
                ma.setIdProyecto(Integer.parseInt(proyecto));
                ma.setTipoProduccion(Integer.parseInt(produccion));
                ma.setSolicitudUrgente(tipoPedidoUrg);
                ma.setCorreoProv(correo);
                ma.setIdCliente(cliente);
                ma.setIdCondiciones(condiciones);
                boolean result = erpPedidoMaestroDao.update(ma);
                
                
               Querys que = new Querys();
               String store = que.getSQL("ActualizaDatosMaestro");
               
                   Map parametros = new HashMap();
             
 //  P_SID              VARCHAR2,

             
                parametros.put("compania", compania);
                parametros.put("cto",cto);
                parametros.put("fechaEntrega",fechaRequerida);
                parametros.put("tipoPedido",tipoPedido);
                parametros.put("idProveedor",provSugerido);
                parametros.put("folio",folio);
                parametros.put("idProyecto",proyecto);
                parametros.put("idProduccion",produccion);
           



               int val = processDao.execute(store, parametros);
                
                 if (result == true) {
               
                 
                            rq.setSuccess(true);
                            rq.setMsg(String.valueOf(id));

                    } else {
                        rq.setSuccess(false);
                        rq.setData(null);
                        rq.setMsg("Error al guardar");
                    }
            
            
            
            
            }
          
            

           

        } catch (Exception e) {
            e.printStackTrace();
        }

        return rq;

    }
            @RequestMapping(value = "/insert2", method = RequestMethod.POST)
     public @ResponseBody
    ResponseQuery2 insertAction2(String correo,WebRequest webRequest, Model model) throws ParseException {

        ResponseQuery2 rq = new ResponseQuery2();
        SimpleDateFormat formatoDelTexto = new SimpleDateFormat("dd/MM/yyyy");
        SimpleDateFormat formatFecha = new SimpleDateFormat("dd/MM/yyyy");
       
//
        if (model.asMap().get("compania") == null) {
            rq.setSuccess(false);
            rq.setData(null);
            rq.setMsg("La sesion no es valida.");
            return rq;
        }
        String compania = model.asMap().get("compania").toString();
        String usuario = model.asMap().get("usuario").toString();
        
        String folio = webRequest.getParameter("idFolio").toString();
        String nombre = webRequest.getParameter("pedidosFormNOMBRE").toString();
        String fechaRequerida = webRequest.getParameter("pedidosFormFECHA_REQUERIDO").toString();
        String area = webRequest.getParameter("cboAreaPedidos").toString();
        String tipoPedido = webRequest.getParameter("cboTIPO_PEDIDO").toString();
        String provSugerido = webRequest.getParameter("cboProvSugerido").toString();
        String operacion = webRequest.getParameter("pedidosOperacion").toString();
        String estatus = webRequest.getParameter("pedidosEstatus").toString();
        String pedidosusr = webRequest.getParameter("pedidosusr").toString();
        String envio = webRequest.getParameter("pedidoenvio").toString();
                String tipoPedidoUrg = webRequest.getParameter("radioTipoOrigenTipoSol").toString();
                String correoProvPedidos = webRequest.getParameter("correoProvPedidos").toString();
                
                String avion = webRequest.getParameter("cboAvionDetPedido").toString();
                String estacion = webRequest.getParameter("cboEstacionPedido").toString();
                
                String departamento = webRequest.getParameter("cboDepartamentoPedido").toString();
                
                
                        
          // String tipoPedidoUrg = "1";
        
                System.out.println("correo: "+correo);
        
        String proyecto = webRequest.getParameter("cboProyectoPedidoMaestro").toString();
        //String  = webRequest.getParameter("cboCtoOrdenPedidos").toString();
        
        
        ErpPedidoMaestro ma = new ErpPedidoMaestro();
        ErpPedidoMaestroId maId = new ErpPedidoMaestroId();
        int id = 0;
        try {
          
            
            if(operacion.equalsIgnoreCase("I")){
                
                maId.setCompania(compania);
                id = erpPedidoMaestroDao.getMaxIdPedidos(maId);
                maId.setFolio(id);
                ma.setId(maId);
                //ma.setCtoCto(cto);
                ma.setEstatus("P");
                ma.setIdProveedor(provSugerido);
                ma.setFechaRequerido(formatFecha.parse(fechaRequerida));
                ma.setNombre(nombre);
                ma.setTipoPedido(Integer.parseInt(tipoPedido));
                ma.setUsuario(usuario);
                ma.setIdArea(Integer.parseInt(area));
                ma.setEnviadoaAutorizar("0");
                ma.setSolicitudUrgente(tipoPedidoUrg);
                ma.setCorreoProv(correo);
                if(!proyecto.equalsIgnoreCase("")){
                     ma.setIdProyecto(Integer.parseInt(proyecto));
                }
                
                ma.setAvionCC(avion);
                ma.setEstacion(estacion);
                ma.setDepartamento(Integer.parseInt(departamento));
                //ma.setTipoProduccion(Integer.parseInt(produccion));
            
                ErpPedidoMaestroId result = erpPedidoMaestroDao.save(ma);
                
                 if (result != null) {
               
                 
                            rq.setSuccess(true);
                            rq.setMsg("Pedido Guardado Correctamente");

                    } else {
                        rq.setSuccess(false);
                        rq.setData(null);
                        rq.setMsg("Error al guardar");
                    }
            
            }
            
            
            if(operacion.equalsIgnoreCase("U")){
                
                maId.setCompania(compania);
                //int id = erpPedidoMaestroDao.getMaxIdPedidos(maId);
                id=Integer.parseInt(folio);
                maId.setFolio(Integer.parseInt(folio));
                ma.setId(maId);
               // ma.setCtoCto(cto);
                ma.setEstatus(estatus);
                ma.setIdProveedor(provSugerido);
                ma.setFechaRequerido(formatFecha.parse(fechaRequerida));
                ma.setNombre(nombre);
                ma.setTipoPedido(Integer.parseInt(tipoPedido));
                ma.setUsuario(pedidosusr);
                //ma.setIdProyecto(Integer.parseInt(proyecto));
                //ma.setTipoProduccion(Integer.parseInt(produccion));
                ma.setIdArea(Integer.parseInt(area));
                ma.setEnviadoaAutorizar(envio);
                ma.setSolicitudUrgente(tipoPedidoUrg);
                ma.setCorreoProv(correo);
                if(!proyecto.equalsIgnoreCase("")){
                     ma.setIdProyecto(Integer.parseInt(proyecto));
                }
                
                ma.setAvionCC(avion);
                ma.setEstacion(estacion);
                ma.setDepartamento(Integer.parseInt(departamento));
            
                boolean result = erpPedidoMaestroDao.update(ma);
                
                
               Querys que = new Querys();
               String store = "";
               if(proyecto != null){
                     store = que.getSQL("ActualizaDatosMaestro2");
                }else{
                     store = que.getSQL("ActualizaDatosMaestro3");
               }
               
               
                   Map parametros = new HashMap();
             
 //  P_SID              VARCHAR2,

             
                parametros.put("compania", compania);
              //  parametros.put("cto",cto);
                parametros.put("fechaEntrega",fechaRequerida);
                parametros.put("tipoPedido",tipoPedido);
                parametros.put("idProveedor",provSugerido);
                parametros.put("folio",folio);
                parametros.put("area",area);
                parametros.put("idProyecto",proyecto);
                



               int val = processDao.execute(store, parametros);
                
                 if (result == true) {
               
                 
                            rq.setSuccess(true);
                            rq.setMsg(String.valueOf(id));

                    } else {
                        rq.setSuccess(false);
                        rq.setData(null);
                        rq.setMsg("Error al guardar");
                    }
            
            
            
            
            }
          
            

           

        } catch (Exception e) {
            e.printStackTrace();
        }

        return rq;

    }
    
    
         @RequestMapping(value = "/insert2g", method = RequestMethod.POST)
     public @ResponseBody
    ResponseQuery2 insertAction2g(String correo,WebRequest webRequest, Model model) throws ParseException {

        ResponseQuery2 rq = new ResponseQuery2();
        SimpleDateFormat formatoDelTexto = new SimpleDateFormat("dd/MM/yyyy");
        SimpleDateFormat formatFecha = new SimpleDateFormat("dd/MM/yyyy");
       
//
        if (model.asMap().get("compania") == null) {
            rq.setSuccess(false);
            rq.setData(null);
            rq.setMsg("La sesion no es valida.");
            return rq;
        }
        String compania = model.asMap().get("compania").toString();
        String usuario = model.asMap().get("usuario").toString();
        
        String folio = webRequest.getParameter("idFolio").toString();
        String nombre = webRequest.getParameter("pedidosFormNOMBRE").toString();
        String fechaRequerida = webRequest.getParameter("pedidosFormFECHA_REQUERIDO").toString();
        String area = webRequest.getParameter("cboAreaPedidos").toString();
        String tipoPedido = webRequest.getParameter("cboTIPO_PEDIDO").toString();
        String provSugerido = webRequest.getParameter("cboProvSugerido").toString();
        String operacion = webRequest.getParameter("pedidosOperacion").toString();
        String estatus = webRequest.getParameter("pedidosEstatus").toString();
        String pedidosusr = webRequest.getParameter("pedidosusr").toString();
        String envio = webRequest.getParameter("pedidoenvio").toString();
                String tipoPedidoUrg = webRequest.getParameter("radioTipoOrigenTipoSol").toString();
                String correoProvPedidos = webRequest.getParameter("correoProvPedidos").toString();
                
                String avion = webRequest.getParameter("cboAvionDetPedido").toString();
                String estacion = webRequest.getParameter("cboEstacionPedido").toString();
                
                
                
                        
          // String tipoPedidoUrg = "1";
        
                System.out.println("correo: "+correo);
        
        String proyecto = webRequest.getParameter("cboProyectoPedidoMaestro").toString();
        //String  = webRequest.getParameter("cboCtoOrdenPedidos").toString();
        
        
        ErpPedidoMaestro ma = new ErpPedidoMaestro();
        ErpPedidoMaestroId maId = new ErpPedidoMaestroId();
        int id = 0;
        try {
          
            
            if(operacion.equalsIgnoreCase("I")){
                
                maId.setCompania(compania);
                id = erpPedidoMaestroDao.getMaxIdPedidos(maId);
                maId.setFolio(id);
                ma.setId(maId);
                //ma.setCtoCto(cto);
                ma.setEstatus("P");
                ma.setIdProveedor(provSugerido);
                ma.setFechaRequerido(formatFecha.parse(fechaRequerida));
                ma.setNombre(nombre);
                ma.setTipoPedido(Integer.parseInt(tipoPedido));
                ma.setUsuario(usuario);
                ma.setIdArea(Integer.parseInt(area));
                ma.setEnviadoaAutorizar("0");
                ma.setSolicitudUrgente(tipoPedidoUrg);
                ma.setCorreoProv(correo);
                if(!proyecto.equalsIgnoreCase("")){
                     ma.setIdProyecto(Integer.parseInt(proyecto));
                }
                
                ma.setAvionCC(avion);
                ma.setEstacion(estacion);
                //ma.setTipoProduccion(Integer.parseInt(produccion));
            
                ErpPedidoMaestroId result = erpPedidoMaestroDao.save(ma);
                
                 if (result != null) {
               
                 
                            rq.setSuccess(true);
                            rq.setMsg("Pedido Guardado Correctamente");

                    } else {
                        rq.setSuccess(false);
                        rq.setData(null);
                        rq.setMsg("Error al guardar");
                    }
            
            }
            
            
            if(operacion.equalsIgnoreCase("U")){
                
                maId.setCompania(compania);
                //int id = erpPedidoMaestroDao.getMaxIdPedidos(maId);
                id=Integer.parseInt(folio);
                maId.setFolio(Integer.parseInt(folio));
                ma.setId(maId);
               // ma.setCtoCto(cto);
                ma.setEstatus(estatus);
                ma.setIdProveedor(provSugerido);
                ma.setFechaRequerido(formatFecha.parse(fechaRequerida));
                ma.setNombre(nombre);
                ma.setTipoPedido(Integer.parseInt(tipoPedido));
                ma.setUsuario(pedidosusr);
                //ma.setIdProyecto(Integer.parseInt(proyecto));
                //ma.setTipoProduccion(Integer.parseInt(produccion));
                ma.setIdArea(Integer.parseInt(area));
                ma.setEnviadoaAutorizar(envio);
                ma.setSolicitudUrgente(tipoPedidoUrg);
                ma.setCorreoProv(correo);
                if(!proyecto.equalsIgnoreCase("")){
                     ma.setIdProyecto(Integer.parseInt(proyecto));
                }
                
                ma.setAvionCC(avion);
                ma.setEstacion(estacion);
            
                boolean result = erpPedidoMaestroDao.update(ma);
                
                
               Querys que = new Querys();
               String store = "";
               if(proyecto != null){
                     store = que.getSQL("ActualizaDatosMaestro2");
                }else{
                     store = que.getSQL("ActualizaDatosMaestro3");
               }
               
               
                   Map parametros = new HashMap();
             
 //  P_SID              VARCHAR2,

             
                parametros.put("compania", compania);
              //  parametros.put("cto",cto);
                parametros.put("fechaEntrega",fechaRequerida);
                parametros.put("tipoPedido",tipoPedido);
                parametros.put("idProveedor",provSugerido);
                parametros.put("folio",folio);
                parametros.put("area",area);
                parametros.put("idProyecto",proyecto);
                



               int val = processDao.execute(store, parametros);
                
                 if (result == true) {
               
                 
                            rq.setSuccess(true);
                            rq.setMsg(String.valueOf(id));

                    } else {
                        rq.setSuccess(false);
                        rq.setData(null);
                        rq.setMsg("Error al guardar");
                    }
            
            
            
            
            }
          
            

           

        } catch (Exception e) {
            e.printStackTrace();
        }

        return rq;

    }
    
    
    
           @RequestMapping(value = "/insert3", method = RequestMethod.POST)
     public @ResponseBody
    ResponseQuery2 insertAction3(String correo,String data ,String folio, WebRequest webRequest, Model model) throws ParseException, IOException {

        ResponseQuery2 rq = new ResponseQuery2();
        SimpleDateFormat formatoDelTexto = new SimpleDateFormat("dd/MM/yyyy");
        SimpleDateFormat formatFecha = new SimpleDateFormat("dd/MM/yyyy");
       String provSugerido = webRequest.getParameter("cboProvSugeridoUrgente").toString();
        String area = webRequest.getParameter("cboAreaPedidosUrgentes").toString();
         String proyecto = webRequest.getParameter("cboProyectoPedidoMaestroUrgente").toString();
//
        if (model.asMap().get("compania") == null) {
            rq.setSuccess(false);
            rq.setData(null);
            rq.setMsg("La sesion no es valida.");
            return rq;
        }
        String compania = model.asMap().get("compania").toString();
        String usuario = model.asMap().get("usuario").toString();
          
        
               System.out.println("correo "+correo);
               System.out.println("data: "+data);
               
                           
      ObjectMapper mapper = new ObjectMapper();
      DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
      
      mapper.setDateFormat(df);
      List<DetPedidosDTO> lista = (List)mapper.readValue(data, mapper
        .getTypeFactory().constructCollectionType(List.class, DetPedidosDTO.class));
      if (lista.isEmpty())
      {
        rq.setSuccess(false);
        rq.setData(null);
        rq.setMsg("Error no existen datos que guardar");
        return rq;
      }
     
      

      int val = 0;
      Iterator<DetPedidosDTO> it = lista.iterator();
      
        
//        
     
//        String nombre = webRequest.getParameter("pedidosFormNOMBRE").toString();
//        String fechaRequerida = webRequest.getParameter("pedidosFormFECHA_REQUERIDO").toString();
//        String area = webRequest.getParameter("cboAreaPedidos").toString();
//        String tipoPedido = webRequest.getParameter("cboTIPO_PEDIDO").toString();
//        String provSugerido = webRequest.getParameter("cboProvSugerido").toString();
//        String operacion = webRequest.getParameter("pedidosOperacion").toString();
//        String estatus = webRequest.getParameter("pedidosEstatus").toString();
//        String pedidosusr = webRequest.getParameter("pedidosusr").toString();
//        String envio = webRequest.getParameter("pedidoenvio").toString();
//                String tipoPedidoUrg = webRequest.getParameter("radioTipoOrigenTipoSol").toString();
//                String correoProvPedidos = webRequest.getParameter("correoProvPedidos").toString();
//                
//                
//        
//                System.out.println("correo: "+correo);
//        
//        String proyecto = webRequest.getParameter("cboProyectoPedidoMaestro").toString();
//        
//        
//        ErpPedidoMaestro ma = new ErpPedidoMaestro();
//        ErpPedidoMaestroId maId = new ErpPedidoMaestroId();
//        int id = 0;
        try {
            
   
      while (it.hasNext())
      {
        System.out.println("-------------------------------------------------------------------");
        DetPedidosDTO ss = (DetPedidosDTO)it.next();
        
          System.out.println("id: "+ ss.id);
          System.out.println("id: "+ ss.sec);
          
          
            Querys que = new Querys();
               String store = "";
               
               if(proyecto == null){
                    store = que.getSQL("ActualizaDetPedidosUrgentes");
               }else{
                   
                    if(proyecto.equalsIgnoreCase("")){
                        store = que.getSQL("ActualizaDetPedidosUrgentes");
                    }else{
                        store = que.getSQL("ActualizaDetPedidosUrgentesProy");
                    }
               
               }
              
                     
               
               
               
                   Map parametros = new HashMap();
             
 //  P_SID              VARCHAR2,

             
                parametros.put("compania", compania);
                parametros.put("id",ss.id);
                parametros.put("sec",ss.sec);
                parametros.put("descripcion",ss.descripcion);
                parametros.put("cantidad",ss.cantidad);
                parametros.put("costoUnitario",ss.costoUnitario);
                parametros.put("montoTotal",ss.montolTotal);
                parametros.put("moneda",ss.moneda);
                parametros.put("idProveedor",provSugerido);
                parametros.put("area", area);
                parametros.put("proyecto",proyecto);
                
                



               int valUp = processDao.execute(store, parametros);
                
     
            
        
      }
      
      ErpPedidoMaestroId idPedido = new ErpPedidoMaestroId();
      
      ErpPedidoMaestro pedido = new ErpPedidoMaestro();
      
      idPedido.setCompania(compania);
      idPedido.setFolio(Integer.parseInt(folio));
      pedido.setId(idPedido);
      pedido.setSolicitudUrgente("2");
      pedido.setCorreoProv(correo);
      pedido.setIdProveedor(provSugerido);
      pedido.setIdArea(Integer.parseInt(area));
      if(!proyecto.equalsIgnoreCase("")){
                     pedido.setIdProyecto(Integer.parseInt(proyecto));
                }
            
      
      erpPedidoMaestroDao.actualizaEstatusUrgente(pedido);
            
            
//          
//            
//            if(operacion.equalsIgnoreCase("I")){
//                
//                maId.setCompania(compania);
//                id = erpPedidoMaestroDao.getMaxIdPedidos(maId);
//                maId.setFolio(id);
//                ma.setId(maId);
//                //ma.setCtoCto(cto);
//                ma.setEstatus("P");
//                ma.setIdProveedor(provSugerido);
//                ma.setFechaRequerido(formatFecha.parse(fechaRequerida));
//                ma.setNombre(nombre);
//                ma.setTipoPedido(Integer.parseInt(tipoPedido));
//                ma.setUsuario(usuario);
//                ma.setIdArea(Integer.parseInt(area));
//                ma.setEnviadoaAutorizar("0");
//                ma.setSolicitudUrgente(tipoPedidoUrg);
//                ma.setCorreoProv(correo);
//                if(!proyecto.equalsIgnoreCase("")){
//                     ma.setIdProyecto(Integer.parseInt(proyecto));
//                }
//            
//                ErpPedidoMaestroId result = erpPedidoMaestroDao.save(ma);
//                
//                 if (result != null) {
//               
                 
//                            rq.setSuccess(true);
//                            rq.setMsg("Pedido Guardado Correctamente");

//                    } else {
//                        rq.setSuccess(false);
//                        rq.setData(null);
//                        rq.setMsg("Error al guardar");
//                    }
            
         //   }
            
           
                
//               Querys que = new Querys();
//               String store = "";
//               if(proyecto != null){
//                     store = que.getSQL("ActualizaDatosMaestro2");
//                }else{
//                     store = que.getSQL("ActualizaDatosMaestro3");
//               }
//               
//               
//                   Map parametros = new HashMap();
//             
//
//             
//                parametros.put("compania", compania);
//                parametros.put("fechaEntrega",fechaRequerida);
//                parametros.put("tipoPedido",tipoPedido);
//                parametros.put("idProveedor",provSugerido);
//                parametros.put("folio",folio);
//                parametros.put("area",area);
//                parametros.put("idProyecto",proyecto);
//                
//
//
//
//               int val = processDao.execute(store, parametros);
//                
//                 if (result == true) {
//               
//                 
//                            rq.setSuccess(true);
//                            rq.setMsg(String.valueOf(id));
//
//                    } else {
//                        rq.setSuccess(false);
//                        rq.setData(null);
//                        rq.setMsg("Error al guardar");
//                    }
//            
//            
//            
            
            
          
               Querys que3 = new Querys();
               String store3 = que3.getSQL("GENERA_ORDEN_URGENTE");
               
                   Map parametros2 = new HashMap();
             
 //  P_SID              VARCHAR2,

             
                parametros2.put("compania", compania);
                parametros2.put("folio",folio);
           



               processDao.execute(store3, parametros2);
               
               Map noOrd = new HashMap();

                noOrd.put("compania", compania);
                noOrd.put("folio", folio);

                List listProv = processDao.getMapResult("BuscaOrdenUrgente", noOrd);

                Map provSec = (HashMap) listProv.get(0);

                System.out.println("orden: "+provSec.get("ORDEN_COMPRA").toString());
                System.out.println("orden: "+provSec.get("FOLIO").toString());
                System.out.println("CORREO_COPIA: "+provSec.get("CORREO_COPIA"));
                
                String correoCopia = "";
                
                if(provSec.get("CORREO_COPIA") != null){
                    
                    correoCopia=provSec.get("CORREO_COPIA").toString();
                
                
                }
               
          
                   
 
            
            URL url = new URL("https://appferaz1.com/fenius/servlet/ada_ServAppletSqr?dic_sistema=empres&catalogo=JRepOrdenCompraV2&dic_idioma=Esp&dic_estado=66&ck_htm=on&compania="+compania+"&id="+provSec.get("ORDEN_COMPRA").toString()+"&reporte=JRepOrdenCompra");

             URLConnection urlConnection = url.openConnection();
                HttpURLConnection connection = null;
                if(urlConnection instanceof HttpURLConnection)
                {
                   connection = (HttpURLConnection) urlConnection;
                }
                else
                {
                   System.out.println("Please enter an HTTP URL.");

                }
                BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                String urlString = "";
                String current;
                while((current = in.readLine()) != null)
                {
                   urlString += current;
                }
                System.out.println(urlString);

                int intIndex = urlString.indexOf("/empres/JasperReports/Reportes/JRepOrdenCompra/JRepOrdenCompra_");
                int intIndex2 = urlString.indexOf(".pdf");
                System.out.println("ID");
                System.out.println(urlString.substring(intIndex + 63, intIndex2));

                String idReporte = urlString.substring(intIndex + 63, intIndex2);


                   mailVerificacionProv.sendMail("", correo, usuario, compania,provSec.get("FOLIO").toString(),idReporte,correoCopia);



            
                
                    rq.setSuccess(true);
                    rq.setData(null);
                    rq.setMsg(provSec.get("FOLIO").toString());
              
                

           

        } catch (Exception e) {
            e.printStackTrace();
        }

        return rq;

    }
    
           @RequestMapping(value = "/insert4", method = RequestMethod.POST)
     public @ResponseBody
    ResponseQuery2 insertAction4(String correo,String data ,String folio, WebRequest webRequest, Model model) throws ParseException, IOException {

        ResponseQuery2 rq = new ResponseQuery2();
        SimpleDateFormat formatoDelTexto = new SimpleDateFormat("dd/MM/yyyy");
        SimpleDateFormat formatFecha = new SimpleDateFormat("dd/MM/yyyy");
       String provSugerido = webRequest.getParameter("cboProvSugeridoUrgente").toString();
         String proyecto = webRequest.getParameter("cboProyectoPedidoMaestroUrgente").toString();
//
        if (model.asMap().get("compania") == null) {
            rq.setSuccess(false);
            rq.setData(null);
            rq.setMsg("La sesion no es valida.");
            return rq;
        }
        String compania = model.asMap().get("compania").toString();
        String usuario = model.asMap().get("usuario").toString();
          
        
               System.out.println("correo "+correo);
               System.out.println("data: "+data);
               
                           
      ObjectMapper mapper = new ObjectMapper();
      DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
      
      mapper.setDateFormat(df);
      List<DetPedidosImpuestosDTO> lista = (List)mapper.readValue(data, mapper
        .getTypeFactory().constructCollectionType(List.class, DetPedidosImpuestosDTO.class));
      if (lista.isEmpty())
      {
        rq.setSuccess(false);
        rq.setData(null);
        rq.setMsg("Error no existen datos que guardar");
        return rq;
      }
     
      

      int val = 0;
      Iterator<DetPedidosImpuestosDTO> it = lista.iterator();
      
        
//        
     
//        String nombre = webRequest.getParameter("pedidosFormNOMBRE").toString();
//        String fechaRequerida = webRequest.getParameter("pedidosFormFECHA_REQUERIDO").toString();
//        String area = webRequest.getParameter("cboAreaPedidos").toString();
//        String tipoPedido = webRequest.getParameter("cboTIPO_PEDIDO").toString();
//        String provSugerido = webRequest.getParameter("cboProvSugerido").toString();
//        String operacion = webRequest.getParameter("pedidosOperacion").toString();
//        String estatus = webRequest.getParameter("pedidosEstatus").toString();
//        String pedidosusr = webRequest.getParameter("pedidosusr").toString();
//        String envio = webRequest.getParameter("pedidoenvio").toString();
//                String tipoPedidoUrg = webRequest.getParameter("radioTipoOrigenTipoSol").toString();
//                String correoProvPedidos = webRequest.getParameter("correoProvPedidos").toString();
//                
//                
//        
//                System.out.println("correo: "+correo);
//        
//        String proyecto = webRequest.getParameter("cboProyectoPedidoMaestro").toString();
//        
//        
//        ErpPedidoMaestro ma = new ErpPedidoMaestro();
//        ErpPedidoMaestroId maId = new ErpPedidoMaestroId();
//        int id = 0;
        try {
            
   
      while (it.hasNext())
      {
        System.out.println("-------------------------------------------------------------------");
        DetPedidosImpuestosDTO ss = (DetPedidosImpuestosDTO)it.next();
        
          System.out.println("id: "+ ss.id);
          System.out.println("id: "+ ss.sec);
          
          
            Querys que = new Querys();
               String store = "";
               
               if(proyecto == null){
                    store = que.getSQL("ActualizaDetPedidosUrgentes2SinArea");
               }else{
                   
                    if(proyecto.equalsIgnoreCase("")){
                        store = que.getSQL("ActualizaDetPedidosUrgentes2SinArea");
                    }else{
                        store = que.getSQL("ActualizaDetPedidosUrgentesProy2SinArea");
                    }
               
               }
              
                    
               System.out.println("Store procedure: "+store);
               
               
               
                   Map parametros = new HashMap();
             
 //  P_SID              VARCHAR2,

             
                parametros.put("compania", compania);
                parametros.put("id",ss.id);
                parametros.put("sec",ss.sec);
                parametros.put("descripcion",ss.descripcion);
                parametros.put("cantidad",ss.cantidad);
                parametros.put("costoUnitario",ss.costoUnitario);
                parametros.put("montoTotal",ss.montolTotal);
                parametros.put("moneda",ss.moneda);
                parametros.put("idProveedor",provSugerido);
                parametros.put("area", null);
                parametros.put("proyecto",proyecto);
                parametros.put("TASA_IVA",ss.tasaIva);
                parametros.put("IVA",ss.importeCotizacionIva);
                parametros.put("RETENCION",ss.retencion);
                parametros.put("DESCUENTO",ss.descuentoProv);
                parametros.put("IMPORTE_RETENCION",ss.descuento);
                



               int valUp = processDao.execute(store, parametros);
                
     
            
        
      }
      
      ErpPedidoMaestroId idPedido = new ErpPedidoMaestroId();
      
      ErpPedidoMaestro pedido = new ErpPedidoMaestro();
      
      idPedido.setCompania(compania);
      idPedido.setFolio(Integer.parseInt(folio));
      pedido.setId(idPedido);
      pedido.setSolicitudUrgente("2");
      pedido.setCorreoProv(correo);
      pedido.setIdProveedor(provSugerido);
      //pedido.setIdArea(Integer.parseInt(area));
      if(!proyecto.equalsIgnoreCase("")){
                     pedido.setIdProyecto(Integer.parseInt(proyecto));
                }
            
      
      erpPedidoMaestroDao.actualizaEstatusUrgenteSinArea(pedido);

            
          
               Querys que3 = new Querys();
               String store3 = que3.getSQL("GENERA_ORDEN_URGENTE2");
               
                   Map parametros2 = new HashMap();
             
 //  P_SID              VARCHAR2,

             
                parametros2.put("compania", compania);
                parametros2.put("folio",folio);
           



               processDao.execute(store3, parametros2);
               
               Map noOrd = new HashMap();

                noOrd.put("compania", compania);
                noOrd.put("folio", folio);

                List listProv = processDao.getMapResult("BuscaOrdenUrgente", noOrd);

                Map provSec = (HashMap) listProv.get(0);

                System.out.println("orden: "+provSec.get("ORDEN_COMPRA").toString());
                System.out.println("orden: "+provSec.get("FOLIO").toString());
                System.out.println("CORREO_COPIA: "+provSec.get("CORREO_COPIA"));
                
                String correoCopia = "";
                
                if(provSec.get("CORREO_COPIA") != null){
                    
                    correoCopia=provSec.get("CORREO_COPIA").toString();
                
                
                }
               
          
                   
 
            
             URL url = new URL("https://www.appferaz1.com/fenius/servlet/ada_ServAppletSqr?dic_sistema=empres&catalogo=JRepOrdenCompraV2&dic_idioma=Esp&dic_estado=66&ck_htm=on&compania="+compania+"&id="+provSec.get("ORDEN_COMPRA").toString()+"&reporte=JRepOrdenCompra");
                URLConnection urlConnection = url.openConnection();
                HttpURLConnection connection = null;
                if(urlConnection instanceof HttpURLConnection)
                {
                   connection = (HttpURLConnection) urlConnection;
                }
                else
                {
                   System.out.println("Please enter an HTTP URL.");

                }
                BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                String urlString = "";
                String current;
                while((current = in.readLine()) != null)
                {
                   urlString += current;
                }
                System.out.println(urlString);

                int intIndex = urlString.indexOf("/empres/JasperReports/Reportes/JRepOrdenCompra/JRepOrdenCompra_");
                int intIndex2 = urlString.indexOf(".pdf");
                System.out.println("ID");
                System.out.println(urlString.substring(intIndex + 63, intIndex2));

                String idReporte = urlString.substring(intIndex + 63, intIndex2);


                   mailVerificacionProv.sendMail("", correo, usuario, compania,provSec.get("FOLIO").toString(),idReporte,correoCopia);



            
                
                    rq.setSuccess(true);
                    rq.setData(null);
                    rq.setMsg(provSec.get("FOLIO").toString());
              
                

           

        } catch (Exception e) {
            e.printStackTrace();
        }

        return rq;

    }
    
       @RequestMapping(value = "/insertEva", method = RequestMethod.POST)
     public @ResponseBody
    ResponseQuery2 insertActionEva(WebRequest webRequest, Model model) throws ParseException {

        ResponseQuery2 rq = new ResponseQuery2();
        SimpleDateFormat formatoDelTexto = new SimpleDateFormat("dd/MM/yyyy");
        SimpleDateFormat formatFecha = new SimpleDateFormat("dd/MM/yyyy");
       
//
        if (model.asMap().get("compania") == null) {
            rq.setSuccess(false);
            rq.setData(null);
            rq.setMsg("La sesion no es valida.");
            return rq;
        }
        String compania = model.asMap().get("compania").toString();
        String usuario = model.asMap().get("usuario").toString();
        
        String folio = webRequest.getParameter("idFolio").toString();
        String fecha = webRequest.getParameter("FECHA_EVA").toString();
        String normas = webRequest.getParameter("NORMAS_EVA").toString();
        String aTiempo = webRequest.getParameter("PRODUCTO_A_TIEMPOEVA").toString();
        String calidad = webRequest.getParameter("CUMPLIMIENTO_CALIDADEVA").toString();
        String nivelCa = webRequest.getParameter("NIVEL_DE_CALIDADEVA").toString();
        String comentarios = webRequest.getParameter("COMENTARIOS_EVA").toString();
        String calidadCompras = webRequest.getParameter("CALIDAD_COMPRASEVA").toString();
        
        //String proyecto = webRequest.getParameter("cboProyectoPedidoMaestro").toString();
        //String  = webRequest.getParameter("cboCtoOrdenPedidos").toString();
        
        

        int id = 0;
        try {
          
          
                
               Querys que = new Querys();
               String store = que.getSQL("InsertaEvaluacion");
               
                   Map parametros = new HashMap();
             

             
                parametros.put("compania", compania);
              //  parametros.put("cto",cto);
                parametros.put("fecha",fecha);
                parametros.put("normas",normas);
                parametros.put("aTiempo",aTiempo);
                parametros.put("folio",folio);
                parametros.put("calidad",calidad);
                parametros.put("nivelCa",nivelCa);
                parametros.put("comentarios",comentarios);
                parametros.put("calidadCompras",calidadCompras);
                



               int val = processDao.execute(store, parametros);
                
   
                            rq.setSuccess(true);
                            rq.setMsg(String.valueOf(id));

         
           

        } catch (Exception e) {
            e.printStackTrace();
        }

        return rq;

    }
    

          @RequestMapping(value = "/insertDet", method = RequestMethod.POST)
     public @ResponseBody
    ResponseQuery2 insertActionDet(WebRequest webRequest, Model model) throws ParseException {

        ResponseQuery2 rq = new ResponseQuery2();
        SimpleDateFormat formatoDelTexto = new SimpleDateFormat("dd/MM/yyyy");
        SimpleDateFormat formatFecha = new SimpleDateFormat("dd/MM/yyyy");
       
//
        if (model.asMap().get("compania") == null) {
            rq.setSuccess(false);
            rq.setData(null);
            rq.setMsg("La sesion no es valida.");
            return rq;
        }
        String compania = model.asMap().get("compania").toString();
        String usuario = model.asMap().get("usuario").toString();
        
        String folio = webRequest.getParameter("idFolioDet").toString();
        String clasificacion = webRequest.getParameter("cboClasificacionDet").toString();
        String producto = webRequest.getParameter("cboProductoDet").toString();
        String cantidad = webRequest.getParameter("cantidadDet").toString();
        String proyecto = webRequest.getParameter("cboProyectoPedido").toString();
        String descripcion = webRequest.getParameter("pedidosFormDescripcion").toString();
        String fechaReq = webRequest.getParameter("pedidosFormFECHA_REQUERIDODet").toString();
        String ctoCto = webRequest.getParameter("cboCtoOrdenPedidosDet").toString();
        String tipoPedido = webRequest.getParameter("cboTIPO_PEDIDODet").toString();
        String provSugerido = webRequest.getParameter("cboProvSugeridoDet").toString();
        String operacion = webRequest.getParameter("pedidosOperacionDet").toString();
        String sid = webRequest.getParameter("pedidosSidDet").toString();
        String id = webRequest.getParameter("pedidosIdDet").toString();
        String sec = webRequest.getParameter("pedidosSecDet").toString();
        String produccion = webRequest.getParameter("pedidosProduccionDet").toString();
        String costoUnitarioDet = webRequest.getParameter("costoUnitarioDet").toString();
        String montoTotalDet = webRequest.getParameter("montoTotalDet").toString();
        String moneda = webRequest.getParameter("cboMonedaDET").toString();
        
        
        String tasaIva = webRequest.getParameter("txtTasaIvaDetPedidos").toString();
        String ivaImp = webRequest.getParameter("ivaDet").toString();
        String retencionImp = webRequest.getParameter("retencionDet").toString();
        String retencionClave = webRequest.getParameter("txtRetencionDetPedidos").toString();
        String descuento = webRequest.getParameter("descuentoCotProvPedidos").toString();
        
        
                
                

        
                
        
        ErpPedidoMaestro ma = new ErpPedidoMaestro();
        ErpPedidoMaestroId maId = new ErpPedidoMaestroId();
       
                Querys que = new Querys();
               String store = que.getSQL("ProcesaPedidoDet");
          
       try {
           
           
       
             Map parametros = new HashMap();
             
 //  P_SID              VARCHAR2,

             
                parametros.put("compania", compania);
                parametros.put("id", id);
                parametros.put("idProducto",producto);
                parametros.put("cantidad",cantidad);
                parametros.put("usuario",usuario);
                parametros.put("cto",ctoCto);
                parametros.put("estatus","P");
                parametros.put("fechaEntrega",fechaReq);
                parametros.put("sec",sec);
                parametros.put("autoriza","");
                parametros.put("concGastos",clasificacion);
                parametros.put("descripcion",descripcion);
                parametros.put("operacion",operacion);
                parametros.put("sid",sid);
                parametros.put("proyecto",proyecto);
                parametros.put("idProveedor",provSugerido);
                parametros.put("tipoPedido",tipoPedido);
                parametros.put("clasificacion",clasificacion);
                parametros.put("folio",folio);
                parametros.put("tipoProduccion",produccion);
                parametros.put("costoUnitarioDet",costoUnitarioDet);
                parametros.put("montoTotalDet",montoTotalDet);
                parametros.put("moneda",moneda);
                
                parametros.put("tasaIva",tasaIva);
                parametros.put("ivaImp",ivaImp);
                parametros.put("retencionImp",retencionImp);
                parametros.put("retencionClave",retencionClave);
                parametros.put("descuento",descuento);
                
                
                


               int val = processDao.execute(store, parametros);
              
               
               System.out.println(val);

           
            
                
           
                
                if (val > 0) {
               
                 
                            rq.setSuccess(true);
                            rq.setMsg("Pedido Guardado Correctamente");

                    } else {
                        rq.setSuccess(false);
                        rq.setData(null);
                        rq.setMsg("Error al guardar");
                    }
            
            
            
            
          
            

           

        } catch (Exception e) {
            e.printStackTrace();
        }

        return rq;

    }
    
    
          @RequestMapping(value = "/insertDet2", method = RequestMethod.POST)
     public @ResponseBody
    ResponseQuery2 insertActionDet2(WebRequest webRequest, Model model) throws ParseException {

        ResponseQuery2 rq = new ResponseQuery2();
        SimpleDateFormat formatoDelTexto = new SimpleDateFormat("dd/MM/yyyy");
        SimpleDateFormat formatFecha = new SimpleDateFormat("dd/MM/yyyy");
       
//
        if (model.asMap().get("compania") == null) {
            rq.setSuccess(false);
            rq.setData(null);
            rq.setMsg("La sesion no es valida.");
            return rq;
        }
        String compania = model.asMap().get("compania").toString();
        String usuario = model.asMap().get("usuario").toString();
        
        String folio = webRequest.getParameter("idFolioDet").toString();
        String clasificacion = webRequest.getParameter("cboClasificacionDet").toString();
        String producto = webRequest.getParameter("cboProductoDet").toString();
        String cantidad = webRequest.getParameter("cantidadDet").toString();
        String proyecto = webRequest.getParameter("cboProyectoPedido").toString();
        String descripcion = webRequest.getParameter("pedidosFormDescripcion").toString();
        String fechaReq = webRequest.getParameter("pedidosFormFECHA_REQUERIDODet").toString();
        String area = webRequest.getParameter("cboareaOrdenPedidosDet").toString();
        String tipoPedido = webRequest.getParameter("cboTIPO_PEDIDODET2").toString();
        String provSugerido = webRequest.getParameter("cboProvSugeridoDet").toString();
        String operacion = webRequest.getParameter("pedidosOperacionDet").toString();
        String sid = webRequest.getParameter("pedidosSidDet").toString();
        String id = webRequest.getParameter("pedidosIdDet").toString();
        String sec = webRequest.getParameter("pedidosSecDet").toString();
        //String produccion = webRequest.getParameter("pedidosProduccionDet").toString();
        String costoUnitarioDet = webRequest.getParameter("costoUnitarioDet").toString();
        String montoTotalDet = webRequest.getParameter("montoTotalDet").toString();
        String tipoNegocio = webRequest.getParameter("cbotipoNegocioDetPedido").toString();
        String moneda = webRequest.getParameter("cboMonedaDET").toString();      
        
        String periodoUso = webRequest.getParameter("cboPeriodoUsoDetPedido").toString();      
        String seguimiento = webRequest.getParameter("cboSeguimientoDetPedido").toString();      
        String lugarEntrega = webRequest.getParameter("cboLugarEntregaDetPedido").toString();      
        String unidadMedida = webRequest.getParameter("cboUnidadMedidaDetPedido").toString();      
        
        //String avion = webRequest.getParameter("cboAvionDetPedido").toString(); 
        String tipoGasto = webRequest.getParameter("cboTipoGastoPedidoDet").toString(); 
        String tipoGastoDesc = webRequest.getParameter("cboTipoGastoDescripcionPedidoDet").toString(); 
        String grupoGasto = webRequest.getParameter("cboGrupoGastoPedidoDet").toString();
        
        String type = webRequest.getParameter("cboTypeOrdenPedidosDet").toString();
        String contrato = webRequest.getParameter("cboContratoDetPedido").toString();
        String avion = webRequest.getParameter("cboAvionDetPedidoV2").toString();
        String hangar = webRequest.getParameter("cboHangarDetPedido").toString();
        
        String departamento = webRequest.getParameter("cboDeptoOrdenPedidosDet").toString();
        String mes = webRequest.getParameter("cboMesReq").toString();
        
        
        
       // 
         //       
           //     
             //           

        
                
        
        ErpPedidoMaestro ma = new ErpPedidoMaestro();
        ErpPedidoMaestroId maId = new ErpPedidoMaestroId();
       
                Querys que = new Querys();
               String store = que.getSQL("ProcesaPedidoDet2");
          
       try {
           
           
       
             Map parametros = new HashMap();
             
 //  P_SID              VARCHAR2,
            descripcion = Matcher.quoteReplacement(descripcion);
             
                parametros.put("compania", compania);
                parametros.put("id", id);
                parametros.put("idProducto",producto);
                parametros.put("cantidad",cantidad);
                parametros.put("usuario",usuario);
                parametros.put("tipoNegocio",tipoNegocio);
                parametros.put("estatus","P");
                parametros.put("fechaEntrega",fechaReq);
                parametros.put("sec",sec);
                parametros.put("autoriza","");
                parametros.put("concGastos",clasificacion);
                parametros.put("descripcion",descripcion);
                parametros.put("operacion",operacion);
                parametros.put("sid",sid);
                parametros.put("area",area);
                parametros.put("proyecto","0");
                parametros.put("cto","");
                parametros.put("idProveedor",provSugerido);
                parametros.put("tipoPedido",tipoPedido);
                parametros.put("clasificacion",clasificacion);
                parametros.put("folio",folio);
                parametros.put("tipoProduccion","0");
                parametros.put("costoUnitarioDet",costoUnitarioDet);
                parametros.put("montoTotalDet",montoTotalDet);
                parametros.put("moneda",moneda);
                
                parametros.put("periodoUso",periodoUso);
                parametros.put("seguimiento",seguimiento);
                parametros.put("lugarEntrega",lugarEntrega);
                parametros.put("unidadMedida",unidadMedida);
                parametros.put("proyecto",proyecto);
                parametros.put("grupoGasto",grupoGasto);
                parametros.put("tipoGasto",tipoGasto);
                parametros.put("tipoGastoDesc",tipoGastoDesc);
                parametros.put("type",type);
                parametros.put("contrato",contrato);
                parametros.put("hangar",hangar);
                parametros.put("avion",avion);
                
                parametros.put("departamento",departamento);
                parametros.put("mes",mes);
                
                
  
                


               int val = processDao.execute(store, parametros);
              
               
               System.out.println(val);

           
            
                
           
                
                if (val > 0) {
               
                 
                            rq.setSuccess(true);
                            rq.setMsg("Pedido Guardado Correctamente");

                    } else {
                        rq.setSuccess(false);
                        rq.setData(null);
                        rq.setMsg("Error al guardar");
                    }
            
            
            
            
          
            

           

        } catch (Exception e) {
            e.printStackTrace();
        }

        return rq;

    }
    
    
    
           @RequestMapping(value = "/insertDet3", method = RequestMethod.POST)
     public @ResponseBody
    ResponseQuery2 insertActionDet3(WebRequest webRequest, Model model) throws ParseException {

        ResponseQuery2 rq = new ResponseQuery2();
        SimpleDateFormat formatoDelTexto = new SimpleDateFormat("dd/MM/yyyy");
        SimpleDateFormat formatFecha = new SimpleDateFormat("dd/MM/yyyy");
       
//
        if (model.asMap().get("compania") == null) {
            rq.setSuccess(false);
            rq.setData(null);
            rq.setMsg("La sesion no es valida.");
            return rq;
        }
        String compania = model.asMap().get("compania").toString();
        String usuario = model.asMap().get("usuario").toString();
        
        String folio = webRequest.getParameter("idFolioDet").toString();
        String clasificacion = webRequest.getParameter("cboClasificacionDet").toString();
        String producto = webRequest.getParameter("cboProductoDet").toString();
        String cantidad = webRequest.getParameter("cantidadDet").toString();
        String proyecto = webRequest.getParameter("cboProyectoPedido").toString();
        String descripcion = webRequest.getParameter("pedidosFormDescripcion").toString();
        String fechaReq = webRequest.getParameter("pedidosFormFECHA_REQUERIDODet").toString();
        String area = webRequest.getParameter("cboareaOrdenPedidosDet").toString();
        String tipoPedido = webRequest.getParameter("cboTIPO_PEDIDODET2").toString();
        String provSugerido = webRequest.getParameter("cboProvSugeridoDet").toString();
        String operacion = webRequest.getParameter("pedidosOperacionDet").toString();
        String sid = webRequest.getParameter("pedidosSidDet").toString();
        String id = webRequest.getParameter("pedidosIdDet").toString();
        String sec = webRequest.getParameter("pedidosSecDet").toString();
        //String produccion = webRequest.getParameter("pedidosProduccionDet").toString();
        String costoUnitarioDet = webRequest.getParameter("costoUnitarioDet").toString();
        String montoTotalDet = webRequest.getParameter("montoTotalDet").toString();
        String tipoNegocio = webRequest.getParameter("cbotipoNegocioDetPedido").toString();
        String moneda = webRequest.getParameter("cboMonedaDET").toString();      
        
        String periodoUso = webRequest.getParameter("cboPeriodoUsoDetPedido").toString();      
        String seguimiento = webRequest.getParameter("cboSeguimientoDetPedido").toString();      
        String lugarEntrega = webRequest.getParameter("cboLugarEntregaDetPedido").toString();      
        String unidadMedida = webRequest.getParameter("cboUnidadMedidaDetPedido").toString();      
        
        //String avion = webRequest.getParameter("cboAvionDetPedido").toString(); 
        String tipoGasto = webRequest.getParameter("cboTipoGastoPedidoDet").toString(); 
        String tipoGastoDesc = webRequest.getParameter("cboTipoGastoDescripcionPedidoDet").toString(); 
        String grupoGasto = webRequest.getParameter("cboGrupoGastoPedidoDet").toString();
        
       
        
        
       // 
         //       
           //     
             //           

        
                
        
        ErpPedidoMaestro ma = new ErpPedidoMaestro();
        ErpPedidoMaestroId maId = new ErpPedidoMaestroId();
       
                Querys que = new Querys();
               String store = que.getSQL("ProcesaPedidoDet2G");
          
       try {
           
           
       
             Map parametros = new HashMap();
             
 //  P_SID              VARCHAR2,
            descripcion = Matcher.quoteReplacement(descripcion);
             
                parametros.put("compania", compania);
                parametros.put("id", id);
                parametros.put("idProducto",producto);
                parametros.put("cantidad",cantidad);
                parametros.put("usuario",usuario);
                parametros.put("tipoNegocio",tipoNegocio);
                parametros.put("estatus","P");
                parametros.put("fechaEntrega",fechaReq);
                parametros.put("sec",sec);
                parametros.put("autoriza","");
                parametros.put("concGastos",clasificacion);
                parametros.put("descripcion",descripcion);
                parametros.put("operacion",operacion);
                parametros.put("sid",sid);
                parametros.put("area",area);
                parametros.put("proyecto","0");
                parametros.put("cto","");
                parametros.put("idProveedor",provSugerido);
                parametros.put("tipoPedido",tipoPedido);
                parametros.put("clasificacion",clasificacion);
                parametros.put("folio",folio);
                parametros.put("tipoProduccion","0");
                parametros.put("costoUnitarioDet",costoUnitarioDet);
                parametros.put("montoTotalDet",montoTotalDet);
                parametros.put("moneda",moneda);
                
                parametros.put("periodoUso",periodoUso);
                parametros.put("seguimiento",seguimiento);
                parametros.put("lugarEntrega",lugarEntrega);
                parametros.put("unidadMedida",unidadMedida);
                parametros.put("proyecto",proyecto);
                parametros.put("grupoGasto",grupoGasto);
                parametros.put("tipoGasto",tipoGasto);
                parametros.put("tipoGastoDesc",tipoGastoDesc);
               
                
                
  
                


               int val = processDao.execute(store, parametros);
              
               
               System.out.println(val);

           
            
                
           
                
                if (val > 0) {
               
                 
                            rq.setSuccess(true);
                            rq.setMsg("Pedido Guardado Correctamente");

                    } else {
                        rq.setSuccess(false);
                        rq.setData(null);
                        rq.setMsg("Error al guardar");
                    }
            
            
            
            
          
            

           

        } catch (Exception e) {
            e.printStackTrace();
        }

        return rq;

    }
    
    
       @RequestMapping(value = "/delete", method = RequestMethod.POST)
    public @ResponseBody
    ResponseQuery deleteOtros( String folio, WebRequest webRequest, Model model) {

        ResponseQuery rq = new ResponseQuery();
   //     System.out.println("data factura cancelacion:"+data);
        
       


            String compania = model.asMap().get("compania").toString();
            String usuario = model.asMap().get("usuario").toString();
            ObjectMapper mapper = new ObjectMapper();
          
                   
        try{
            
             ErpPedidoMaestro ma = new ErpPedidoMaestro();
             ErpPedidoMaestroId maId = new ErpPedidoMaestroId();
           

           
          maId.setCompania(compania);
          maId.setFolio(Integer.parseInt(folio));
          ma.setId(maId);
           
            boolean result = erpPedidoMaestroDao.delete(ma);
            
              Querys que = new Querys();
               String store = que.getSQL("BorraDatosMaestro");
               
                   Map parametros = new HashMap();
             
 //  P_SID              VARCHAR2,

             
                parametros.put("compania", compania);
                parametros.put("folio",folio);
           



               int val = processDao.execute(store, parametros);
          
            
            if (result == true){
            
                
                    rq.setSuccess(true);
                    rq.setData(null);
                    rq.setMsg("Pedido eliminado correctamente");
              
                
            }else{
                
                rq.setSuccess(true);
                rq.setData(null);
                rq.setMsg("Error al eliminar");
                
            }
            

        }catch(Exception e){
            e.printStackTrace();
            rq.setSuccess(false);
            rq.setData(null);
            rq.setMsg("Error al eliminar");
           return rq;
        }
        
        return rq;
    }
    
    
        @RequestMapping(value = "/deleteDet", method = RequestMethod.POST)
    public @ResponseBody
    ResponseQuery deleteDet( String folio,String id, WebRequest webRequest, Model model) {

        ResponseQuery rq = new ResponseQuery();
   //     System.out.println("data factura cancelacion:"+data);
        
              System.out.println("Borrando Pedidos");


            String compania = model.asMap().get("compania").toString();
            String usuario = model.asMap().get("usuario").toString();
            ObjectMapper mapper = new ObjectMapper();
          
                   
        try{
            
             
            
              Querys que = new Querys();
               String store = que.getSQL("BorraDatosDetPedidos");
               
                   Map parametros = new HashMap();
             
 //  P_SID              VARCHAR2,

             
                parametros.put("compania", compania);
                parametros.put("folio",folio);
                 parametros.put("id",id);
           



               int val = processDao.execute(store, parametros);
          
            
            if (val > 0){
            
                
                    rq.setSuccess(true);
                    rq.setData(null);
                    rq.setMsg("Producto eliminado correctamente");
              
                
            }else{
                
                rq.setSuccess(true);
                rq.setData(null);
                rq.setMsg("Error al eliminar");
                
            }
            

        }catch(Exception e){
            e.printStackTrace();
            rq.setSuccess(false);
            rq.setData(null);
            rq.setMsg("Error al eliminar");
           return rq;
        }
        
        return rq;
    }
    
    
    
    
     
           @RequestMapping(value = "/cancelaEstatus", method = RequestMethod.POST)
    public @ResponseBody
    ResponseQuery cancelaEstatus( String folio,String motivo, WebRequest webRequest, Model model) {

        ResponseQuery rq = new ResponseQuery();
   //     System.out.println("data factura cancelacion:"+data);
        
       


            String compania = model.asMap().get("compania").toString();
            String usuario = model.asMap().get("usuario").toString();
            ObjectMapper mapper = new ObjectMapper();
          
                   
        try{
        
            
              Querys que = new Querys();
               String store = que.getSQL("ActualizaEstatusPedidoMC");
               String store2 = que.getSQL("ActualizaEstatusPedidoDC");
               
                   Map parametros = new HashMap();
             
 //  P_SID              VARCHAR2,

             
                parametros.put("compania", compania);
                parametros.put("folio",folio);
                parametros.put("usuario",usuario);
                parametros.put("estatus",'C');
                parametros.put("motivo",motivo);
           



               int val = processDao.execute(store, parametros);
               
               int val2 = processDao.execute(store2, parametros);
          
            
            if (val > 0 && val2 > 0){
            
                
                    rq.setSuccess(true);
                    rq.setData(null);
                    rq.setMsg("Pedido autorizado correctamente");
              
                
            }else{
                
                rq.setSuccess(true);
                rq.setData(null);
                rq.setMsg("Error al autorizar");
                
            }
            

        }catch(Exception e){
            e.printStackTrace();
            rq.setSuccess(false);
            rq.setData(null);
            rq.setMsg("Error al eliminar");
           return rq;
        }
        
        return rq;
    }
    
    
    
            
            @RequestMapping(value = "/updateEstatusEnvioAut", method = RequestMethod.POST)
    public @ResponseBody
    ResponseQuery updateEstatusEnvioAut( String folio, WebRequest webRequest, Model model) {

        ResponseQuery rq = new ResponseQuery();
   //     System.out.println("data factura cancelacion:"+data);
        
       


            String compania = model.asMap().get("compania").toString();
            String usuario = model.asMap().get("usuario").toString();
            ObjectMapper mapper = new ObjectMapper();
          
                   
        try{
            

            
              Querys que = new Querys();
               String store = que.getSQL("ActualizaEstatusPedidoMEnvioAuto");
               
                   Map parametros = new HashMap();
             
 //  P_SID              VARCHAR2,

             
                parametros.put("compania", compania);
                parametros.put("folio",folio);
           
        
               int val = processDao.execute(store, parametros);
               
          
            
            if (val > 0 ){
            
                
                    rq.setSuccess(true);
                    rq.setData(null);
                    rq.setMsg("Pedido enviado a autorizar correctamente");
              
                
            }else{
                
                rq.setSuccess(true);
                rq.setData(null);
                rq.setMsg("Error al enviar");
                
            }
            

        }catch(Exception e){
            log.error("Error cambiar estatus:",e);
            rq.setSuccess(false);
            rq.setData(null);
            rq.setMsg("Error al enviar:"+e.getMessage());
           return rq;
        }
        
        return rq;
    }
    
    
           @RequestMapping(value = "/updateEstatus", method = RequestMethod.POST)
    public @ResponseBody
    ResponseQuery updateEstatus( String folio, WebRequest webRequest, Model model) {

        ResponseQuery rq = new ResponseQuery();
   //     System.out.println("data factura cancelacion:"+data);
        
       


            String compania = model.asMap().get("compania").toString();
            String usuario = model.asMap().get("usuario").toString();
            ObjectMapper mapper = new ObjectMapper();
          
                   
        try{
            

            List list = new ArrayList();
              Querys que = new Querys();
               String store = que.getSQL("ActualizaEstatusPedidoM");
               String store2 = que.getSQL("ActualizaEstatusPedidoD");
               
                   Map parametros = new HashMap();
             
 //  P_SID              VARCHAR2,

             
                parametros.put("compania", compania);
                parametros.put("folio",folio);
                parametros.put("usuario",usuario);
                parametros.put("estatus",'A');
           
                List listNivel = this.processDao.getMapResult("ValidaNivelAutorizador", parametros);
                List listNivelDa = this.processDao.getMapResult("validaDirectorArea", parametros);
                List listPermAuto = this.processDao.getMapResult("validaPermisoAuto", parametros);
                
                
                if(listNivel==null){
                    
                    list.add("1");
                    
                    rq.setSuccess(false);
                    rq.setData(list);
                    rq.setMsg("Error. El usuario no cuenta con nivel de autorización.");
                    return rq;
                }else{
                    
                    if(listNivel.isEmpty()){
                        list.add("1");
                        rq.setSuccess(false);
                        rq.setData(list);
                        rq.setMsg("Error. El usuario no cuenta con nivel de autorización.");
                        return rq;
                    }

                
                
                
                }
                
                
                Map nivel = (HashMap)listNivel.get(0);
                
                
                Map nivelDa = (HashMap)listNivelDa.get(0);
                
                Map permAuto = (HashMap)listPermAuto.get(0);
                
                if(!nivelDa.get("NIVEL_D_G").toString().equalsIgnoreCase("0")){
                      list.add("2");
                       rq.setSuccess(false);
                        rq.setData(list);
                        rq.setMsg("Error. La solicitud requiere ser autorizada por el Director General.");
                        return rq;
                
                }
                
                if(!permAuto.get("PERM").toString().equalsIgnoreCase("0")){
                
                    List listPermAutoAprob = this.processDao.getMapResult("verificalAutoAprob", parametros);
                    Map autoAprob = (HashMap)listPermAutoAprob.get(0);
                    
                    if(!autoAprob.get("NIVEL_REQUERIDO").toString().equalsIgnoreCase("0")){
                        
                         list.add("3");
                       rq.setSuccess(false);
                        rq.setData(list);
                        rq.setMsg("Error. La solicitud requiere ser autorizada por usuario nivel "+autoAprob.get("NIVEL_REQUERIDO").toString()+".");
                        return rq;
                    
                    
                    }
                
                }
                
                
                String resultNivel = nivel.get("RESULT").toString();
                String msgNivel = nivel.get("MSG").toString();
                
                
                if(resultNivel.equalsIgnoreCase("0")){
                    
                    rq.setSuccess(false);
                    rq.setData(null);
                    rq.setMsg(msgNivel);
              
                    return rq;
                }


               int val = processDao.execute(store, parametros);
               
               int val2 = processDao.execute(store2, parametros);
          
            
            if (val > 0 && val2 > 0){
            
                
                    rq.setSuccess(true);
                    rq.setData(null);
                    rq.setMsg("Pedido autorizado correctamente");
              
                
            }else{
                
                rq.setSuccess(true);
                rq.setData(null);
                rq.setMsg("Error al autorizar");
                
            }
            

        }catch(Exception e){
            log.error("Error cambiar estatus:",e);
            rq.setSuccess(false);
            rq.setData(null);
            rq.setMsg("Error al autorizar:"+e.getMessage());
           return rq;
        }
        
        return rq;
    }
    
     @RequestMapping(value = "/saveArchivo", method = RequestMethod.POST ,produces = "text/plain;charset=utf-8")
     
    public @ResponseBody
    String create(@RequestParam("archCOMPANIA2Pedidos") String compania2,
            @RequestParam("archIdPedido") String id,
            @RequestParam("archNOMBRE2Pedido") String nombre,
            @RequestParam("archDESCRIPCION2Pedido") String descripcion,
            FileUploadBean uploadItemPedido,
            BindingResult result, WebRequest webRequest, Model model) throws UnsupportedEncodingException {
        
           
       
         String nombreCod = new String(nombre.getBytes("ISO-8859-15"), "UTF-8");
         String descripcionCod = new String(descripcion.getBytes("ISO-8859-15"), "UTF-8");
         
        
        boolean isSave = true;
        SimpleDateFormat formatoDelTexto2 = new SimpleDateFormat("dd/MM/yyyy");
        
         String compania = model.asMap().get("compania").toString();
         String usuario = model.asMap().get("usuario").toString();
     
         System.out.println("Cargando Archivo");
               
        ExtJSFormResult extjsFormResult = new ExtJSFormResult();
        
        if (model.asMap().get("usuario") == null) {

            isSave = false;
            extjsFormResult.setMessage("Sesion no valida");
            extjsFormResult.setSuccess(isSave);
            return extjsFormResult.toString();
        }
        
        System.out.println("Session Valida");

        String path = "";
        String url = "";
        String extension = "";
        String nombreArc ="";

        try {
            MultipartFile file = uploadItemPedido.getFile();

            InputStream inputStream = null;
            OutputStream outputStream = null;
            if (file.getSize() > 0) {
                inputStream = file.getInputStream();
                long maxSizeDocument;
                long maxSize = new Long("5145728");
                
                System.out.println("Tamaño en server: "+maxSize);
                System.out.println("Tamaño archivo: "+file.getSize());
                
                if (file.getSize() > maxSize) {
                    //System.out.println("File Size:::" + file.getSize());
                    isSave = false;
                    extjsFormResult.setMessage("Tamaño no valido");
                    extjsFormResult.setSuccess(isSave);
                    return extjsFormResult.toString();
                }

                int i = file.getOriginalFilename().lastIndexOf('.');
                
//                System.out.println("Nombre:" + file.getOriginalFilename());
                
               
                    extension = file.getOriginalFilename().substring(i + 1);
                    nombreArc = file.getOriginalFilename().substring(0 , i);
//                    System.out.println("i:" + i);
//               System.out.println("exte:" + extension);
//               System.out.println("nombreArc:" + nombreArc);
               // System.out.println("exte:" + extension);
//                if (!extension.trim().equals("pdf")) {
//                    isSave = false;
//                    extjsFormResult.setMessage("Archivo pdf no valido");
//                    extjsFormResult.setSuccess(isSave);
//                    return extjsFormResult.toString();
//                }
                String hora = "" + System.currentTimeMillis();
                 path ="D:/Administrategia/empresarial/files/PEDIDOS/"+compania+"/"+nombreArc+"."+extension;
              
                 url ="/cfiles/PEDIDOS/"+compania+"/"+nombreArc+"."+extension;
                 
                 revisarDirectorio("D:/Administrategia/empresarial/files/PEDIDOS/"+compania+"/");
                 
                 System.out.println("Ruta: "+path);
                 System.out.println("url: "+url);
             
                outputStream = new FileOutputStream(path);

                int readBytes = 0;
                byte[] buffer = new byte[10000];
                
                System.out.println("Empezando Carga...");
                
                while ((readBytes = inputStream.read(buffer, 0, 10000)) != -1) {
                    outputStream.write(buffer, 0, readBytes);
                }
                outputStream.close();
                inputStream.close();
                
                System.out.println("Archivo Guardado");
            }

            
            
               Querys que = new Querys();
               String store = que.getSQL("insertaArchivoPedidos");
               
                   Map parametros = new HashMap();
             
 //  P_SID              VARCHAR2,

             
                parametros.put("compania", compania);
                parametros.put("idPedido",id);
                parametros.put("descripcion",descripcionCod);
                parametros.put("nombre",nombreCod);
                parametros.put("path",path);
                parametros.put("url",url);
                parametros.put("nombreArch",nombreArc+"."+extension);
                parametros.put("usuario",usuario);

                
           



               int val = processDao.execute(store, parametros);
                
            System.out.println("Datos Insertados");
   
            if (val > 0) {
                   isSave = true;
                extjsFormResult.setMessage("Archivo Guardado");
                extjsFormResult.setSuccess(isSave);
                return extjsFormResult.toString();
               
            } else {
             
                 isSave = false;
                extjsFormResult.setMessage("Error al guardar los datos");
                    extjsFormResult.setSuccess(isSave);
                return extjsFormResult.toString();
            }

        } catch (Exception e) {
            e.printStackTrace();
            isSave = false;
        }




        extjsFormResult.setSuccess(isSave);

        return extjsFormResult.toString();
    }
    
    
           @RequestMapping(value = "/deleteArchivo", method = RequestMethod.POST)
    public @ResponseBody
    ResponseQuery deleteArchivo( String idPedido,String id, WebRequest webRequest, Model model) {

        ResponseQuery rq = new ResponseQuery();
   //     System.out.println("data factura cancelacion:"+data);
        
              System.out.println("Borrando Pedidos");


            String compania = model.asMap().get("compania").toString();
            String usuario = model.asMap().get("usuario").toString();
            ObjectMapper mapper = new ObjectMapper();
          
                   
        try{
            
             
            
              Querys que = new Querys();
               String store = que.getSQL("BorraDatosArchivosPedidos");
               
                   Map parametros = new HashMap();
             
 //  P_SID              VARCHAR2,

             
                parametros.put("compania", compania);
                parametros.put("idPedido",idPedido);
                 parametros.put("id",id);
           



               int val = processDao.execute(store, parametros);
          
            
            if (val > 0){
            
                
                    rq.setSuccess(true);
                    rq.setData(null);
                    rq.setMsg("Producto eliminado correctamente");
              
                
            }else{
                
                rq.setSuccess(true);
                rq.setData(null);
                rq.setMsg("Error al eliminar");
                
            }
            

        }catch(Exception e){
            e.printStackTrace();
            rq.setSuccess(false);
            rq.setData(null);
            rq.setMsg("Error al eliminar");
           return rq;
        }
        
        return rq;
    }
    
    
            @RequestMapping(value = "/generaOrdenUrgente", method = RequestMethod.POST)
    public @ResponseBody
    ResponseQuery generaOrdenUrgente( String folio,String correo, WebRequest webRequest, Model model) {

        ResponseQuery rq = new ResponseQuery();
   //     System.out.println("data factura cancelacion:"+data);
        
       


            String compania = model.asMap().get("compania").toString();
            String usuario = model.asMap().get("usuario").toString();
            ObjectMapper mapper = new ObjectMapper();
            
            
            
              Querys que = new Querys();
               String store = que.getSQL("ActualizaEstatusPedidoM");
               String store2 = que.getSQL("ActualizaEstatusPedidoD");
               
                   Map parametros = new HashMap();
             
 //  P_SID              VARCHAR2,

             
                parametros.put("compania", compania);
                parametros.put("folio",folio);
                parametros.put("usuario",usuario);
                parametros.put("estatus",'A');
           
                List listNivel = this.processDao.getMapResult("ValidaNivelAutorizador", parametros);
                
                if(listNivel==null){
                    
                    rq.setSuccess(false);
                    rq.setData(null);
                    rq.setMsg("Error. El usuario no cuenta con nivel de autorización.");
                    return rq;
                }else{
                    
                    if(listNivel.isEmpty()){
                    
                        rq.setSuccess(false);
                        rq.setData(null);
                        rq.setMsg("Error. El usuario no cuenta con nivel de autorización.");
                        return rq;
                    }

                
                
                
                }
                
                
                
                Map nivel = (HashMap)listNivel.get(0);
                String resultNivel = nivel.get("RESULT").toString();
                String msgNivel = nivel.get("MSG").toString();
                
                
                if(resultNivel.equalsIgnoreCase("0")){
                    
                    rq.setSuccess(false);
                    rq.setData(null);
                    rq.setMsg(msgNivel);
              
                    return rq;
                }


               int val = processDao.execute(store, parametros);
               
               int val2 = processDao.execute(store2, parametros);
          
            
            
            Querys que3 = new Querys();
               String store3 = que3.getSQL("GENERA_ORDEN_URGENTE");
               
                   Map parametros2 = new HashMap();
             
 //  P_SID              VARCHAR2,

             
                parametros2.put("compania", compania);
                parametros2.put("folio",folio);
           



               processDao.execute(store3, parametros2);
               
               Map noOrd = new HashMap();

                noOrd.put("compania", compania);
                noOrd.put("folio", folio);

                List listProv = processDao.getMapResult("BuscaOrdenUrgente", noOrd);

                Map provSec = (HashMap) listProv.get(0);

                System.out.println("orden: "+provSec.get("ORDEN_COMPRA").toString());
                System.out.println("orden: "+provSec.get("FOLIO").toString());
                
                if(provSec.get("CORREO_COPIA") == null || provSec.get("CORREO_COPIA") == ""){
                    
                     rq.setSuccess(false);
                    rq.setData(null);
                    rq.setMsg("No existe correo configurado para el envío");
                    
                    return rq;
                }
                
               // System.out.println("CORREO_COPIA: "+provSec.get("CORREO_COPIA").toString());
               
          
                   
        try{
            
            
             URL url = new URL("https://appferaz1.com/fenius/servlet/ada_ServAppletSqr?dic_sistema=empres&catalogo=JRepOrdenCompraV2&dic_idioma=Esp&dic_estado=66&ck_htm=on&compania="+compania+"&id="+provSec.get("ORDEN_COMPRA").toString()+"&reporte=JRepOrdenCompra");
         URLConnection urlConnection = url.openConnection();
         HttpURLConnection connection = null;
         if(urlConnection instanceof HttpURLConnection)
         {
            connection = (HttpURLConnection) urlConnection;
         }
         else
         {
            System.out.println("Please enter an HTTP URL.");
            
         }
         BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
         String urlString = "";
         String current;
         while((current = in.readLine()) != null)
         {
            urlString += current;
         }
         System.out.println(urlString);
         
         int intIndex = urlString.indexOf("/empres/JasperReports/Reportes/JRepOrdenCompra/JRepOrdenCompra_");
         int intIndex2 = urlString.indexOf(".pdf");
         System.out.println("ID");
         System.out.println(urlString.substring(intIndex + 63, intIndex2));
            
         String idReporte = urlString.substring(intIndex + 63, intIndex2);
            
            
            mailVerificacionProv.sendMail("", correo, usuario, compania,provSec.get("FOLIO").toString(),idReporte,provSec.get("CORREO_COPIA").toString());
            

            
            
                
                    rq.setSuccess(true);
                    rq.setData(null);
                    rq.setMsg(provSec.get("FOLIO").toString());
              
                
       
            

        }catch(Exception e){
            log.error("Error cambiar estatus:",e);
            rq.setSuccess(false);
            rq.setData(null);
            rq.setMsg("Error al autorizar:"+e.getMessage());
           return rq;
        }
        
        return rq;
    }
    
    
    
    @RequestMapping(value = "/generaPedidoPO", method = RequestMethod.POST)
    public @ResponseBody
    ResponseQuery generaPedidoPO( String folio,String correo, WebRequest webRequest, Model model) {

        ResponseQuery rq = new ResponseQuery();
   //     System.out.println("data factura cancelacion:"+data);
        
       


            String compania = model.asMap().get("compania").toString();
            String usuario = model.asMap().get("usuario").toString();
            ObjectMapper mapper = new ObjectMapper();
            
            
            
              Querys que = new Querys();
               String store = que.getSQL("ActualizaEstatusPedidoM");
               String store2 = que.getSQL("ActualizaEstatusPedidoD");
               
                   Map parametros = new HashMap();
             
 //  P_SID              VARCHAR2,

             
                parametros.put("compania", compania);
                parametros.put("folio",folio);
                parametros.put("usuario",usuario);
                parametros.put("estatus",'A');
           
                List listNivel = this.processDao.getMapResult("ValidaNivelAutorizador", parametros);
                
                if(listNivel==null){
                    
                    rq.setSuccess(false);
                    rq.setData(null);
                    rq.setMsg("Error. El usuario no cuenta con nivel de autorización.");
                    return rq;
                }else{
                    
                    if(listNivel.isEmpty()){
                    
                        rq.setSuccess(false);
                        rq.setData(null);
                        rq.setMsg("Error. El usuario no cuenta con nivel de autorización.");
                        return rq;
                    }

                
                
                
                }
                
                
                
                Map nivel = (HashMap)listNivel.get(0);
                String resultNivel = nivel.get("RESULT").toString();
                String msgNivel = nivel.get("MSG").toString();
                
                
                if(resultNivel.equalsIgnoreCase("0")){
                    
                    rq.setSuccess(false);
                    rq.setData(null);
                    rq.setMsg(msgNivel);
              
                    return rq;
                }


               int val = processDao.execute(store, parametros);
               
               int val2 = processDao.execute(store2, parametros);
          
            
            
            Querys que3 = new Querys();
               String store3 = que3.getSQL("GENERA_ORDEN_AUTO");
               
                   Map parametros2 = new HashMap();
             
 //  P_SID              VARCHAR2,

             
                parametros2.put("compania", compania);
                parametros2.put("folio",folio);
           



               processDao.execute(store3, parametros2);
               
               Map noOrd = new HashMap();

                noOrd.put("compania", compania);
                noOrd.put("folio", folio);

                List listProv = processDao.getMapResult("BuscaOrdenUrgente", noOrd);

                Map provSec = (HashMap) listProv.get(0);

                System.out.println("orden: "+provSec.get("ORDEN_COMPRA").toString());
                System.out.println("orden: "+provSec.get("FOLIO").toString());
                
                if(provSec.get("CORREO_COPIA") == null || provSec.get("CORREO_COPIA") == ""){
                    
                     rq.setSuccess(false);
                    rq.setData(null);
                    rq.setMsg("No existe correo configurado para el envío");
                    
                    return rq;
                }
                
               // System.out.println("CORREO_COPIA: "+provSec.get("CORREO_COPIA").toString());
               
          
                   
        try{
            
            
             URL url = new URL("https://appferaz1.com/fenius/servlet/ada_ServAppletSqr?dic_sistema=empres&catalogo=JRepOrdenCompraV2&dic_idioma=Esp&dic_estado=66&ck_htm=on&compania="+compania+"&id="+provSec.get("ORDEN_COMPRA").toString()+"&reporte=JRepOrdenCompra");
         URLConnection urlConnection = url.openConnection();
         HttpURLConnection connection = null;
         if(urlConnection instanceof HttpURLConnection)
         {
            connection = (HttpURLConnection) urlConnection;
         }
         else
         {
            System.out.println("Please enter an HTTP URL.");
            
         }
         BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
         String urlString = "";
         String current;
         while((current = in.readLine()) != null)
         {
            urlString += current;
         }
         System.out.println(urlString);
         
         int intIndex = urlString.indexOf("/empres/JasperReports/Reportes/JRepOrdenCompra/JRepOrdenCompra_");
         int intIndex2 = urlString.indexOf(".pdf");
         System.out.println("ID");
         System.out.println(urlString.substring(intIndex + 63, intIndex2));
            
         String idReporte = urlString.substring(intIndex + 63, intIndex2);
            
            
            mailVerificacionProv.sendMailPOAut("", correo, usuario, compania,provSec.get("FOLIO").toString(),idReporte,provSec.get("CORREO_COPIA").toString());
            

            
            
                
                    rq.setSuccess(true);
                    rq.setData(null);
                    rq.setMsg(provSec.get("FOLIO").toString());
              
                
       
            

        }catch(Exception e){
            log.error("Error cambiar estatus:",e);
            rq.setSuccess(false);
            rq.setData(null);
            rq.setMsg("Error al autorizar:"+e.getMessage());
           return rq;
        }
        
        return rq;
    }
    
    
    
                @RequestMapping(value = "/generaOrdenUrgente2", method = RequestMethod.POST)
    public @ResponseBody
    ResponseQuery generaOrdenUrgente2( String folio,String correo, WebRequest webRequest, Model model) {

        ResponseQuery rq = new ResponseQuery();
   //     System.out.println("data factura cancelacion:"+data);
        
       


            String compania = model.asMap().get("compania").toString();
            String usuario = model.asMap().get("usuario").toString();
            ObjectMapper mapper = new ObjectMapper();
            
            
            
              Querys que = new Querys();
               String store = que.getSQL("ActualizaEstatusPedidoM");
               String store2 = que.getSQL("ActualizaEstatusPedidoD");
               
                   Map parametros = new HashMap();
             
 //  P_SID              VARCHAR2,

             
                parametros.put("compania", compania);
                parametros.put("folio",folio);
                parametros.put("usuario",usuario);
                parametros.put("estatus",'A');
           
                List listNivel = this.processDao.getMapResult("ValidaNivelAutorizador", parametros);
                
                if(listNivel==null){
                    
                    rq.setSuccess(false);
                    rq.setData(null);
                    rq.setMsg("Error. El usuario no cuenta con nivel de autorización.");
                    return rq;
                }else{
                    
                    if(listNivel.isEmpty()){
                    
                        rq.setSuccess(false);
                        rq.setData(null);
                        rq.setMsg("Error. El usuario no cuenta con nivel de autorización.");
                        return rq;
                    }

                
                
                
                }
                
                
                
                Map nivel = (HashMap)listNivel.get(0);
                String resultNivel = nivel.get("RESULT").toString();
                String msgNivel = nivel.get("MSG").toString();
                
                
                if(resultNivel.equalsIgnoreCase("0")){
                    
                    rq.setSuccess(false);
                    rq.setData(null);
                    rq.setMsg(msgNivel);
              
                    return rq;
                }


               int val = processDao.execute(store, parametros);
               
               int val2 = processDao.execute(store2, parametros);
          
            
            
            Querys que3 = new Querys();
               String store3 = que3.getSQL("GENERA_ORDEN_URGENTE2");
               
                   Map parametros2 = new HashMap();
             
 //  P_SID              VARCHAR2,

             
                parametros2.put("compania", compania);
                parametros2.put("folio",folio);
           



               processDao.execute(store3, parametros2);
               
               Map noOrd = new HashMap();

                noOrd.put("compania", compania);
                noOrd.put("folio", folio);

                List listProv = processDao.getMapResult("BuscaOrdenUrgente", noOrd);

                Map provSec = (HashMap) listProv.get(0);

                System.out.println("orden: "+provSec.get("ORDEN_COMPRA").toString());
                System.out.println("orden: "+provSec.get("FOLIO").toString());
                System.out.println("CORREO_COPIA: "+provSec.get("CORREO_COPIA").toString());
               
          
                   
        try{
            
            System.out.println("Buscando reporte...");
             URL url = new URL("https://appferaz1.com/fenius/servlet/ada_ServAppletSqr?dic_sistema=empres&catalogo=JRepOrdenCompraV2&dic_idioma=Esp&dic_estado=66&ck_htm=on&compania="+compania+"&id="+provSec.get("ORDEN_COMPRA").toString()+"&reporte=JRepOrdenCompra");
         URLConnection urlConnection = url.openConnection();
         HttpURLConnection connection = null;
         if(urlConnection instanceof HttpURLConnection)
         {
            connection = (HttpURLConnection) urlConnection;
         }
         else
         {
            System.out.println("Please enter an HTTP URL.");
            
         }
         BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
         String urlString = "";
         String current;
         while((current = in.readLine()) != null)
         {
            urlString += current;
         }
         System.out.println(urlString);
         
         int intIndex = urlString.indexOf("/empres/JasperReports/Reportes/JRepOrdenCompra/JRepOrdenCompra_");
         int intIndex2 = urlString.indexOf(".pdf");
         System.out.println("ID");
         System.out.println(urlString.substring(intIndex + 63, intIndex2));
            
         String idReporte = urlString.substring(intIndex + 63, intIndex2);
            
            
            mailVerificacionProv.sendMail("", correo, usuario, compania,provSec.get("FOLIO").toString(),idReporte,provSec.get("CORREO_COPIA").toString());
            

            
            
                
                    rq.setSuccess(true);
                    rq.setData(null);
                    rq.setMsg(provSec.get("FOLIO").toString());
              
                
       
            

        }catch(Exception e){
            log.error("Error cambiar estatus:",e);
            rq.setSuccess(false);
            rq.setData(null);
            rq.setMsg("Error al autorizar:"+e.getMessage());
           return rq;
        }
        
        return rq;
    }
    
       
    public void revisarDirectorio(String dirCompania) {

        File file = new File(dirCompania);
//        System.out.println("Directory" + dirOutFileDocument + dirCompania + "/");
        if (!file.exists()) {
            if (file.mkdirs()) {
//                System.out.println("Directory is created!");
            } else {
//                System.out.println("Failed to create directory!");
            }
        }

    }
  

    public void setProcessDao(ProcessDao processDao) {
        this.processDao = processDao;
    }



    public void setErpPedidoMaestroDao(ErpPedidoMaestroDao erpPedidoMaestroDao) {
        this.erpPedidoMaestroDao = erpPedidoMaestroDao;
    }

    public void setMailVerificacionProv(MailVerificacionProv mailVerificacionProv) {
        this.mailVerificacionProv = mailVerificacionProv;
    }
    
    
    
    
    
}
