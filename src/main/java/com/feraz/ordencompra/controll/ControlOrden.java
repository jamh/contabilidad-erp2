/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.feraz.ordencompra.controll;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.feraz.ordencompra.dao.ErpOrdenCompraDao;
import com.feraz.ordencompra.dao.ErpOrdenCompraDetDao;
import com.feraz.ordencompra.dao.ErpOrdenXPedidosDao;
import com.feraz.ordencompra.dto.OrdenDTO;
import com.feraz.ordencompra.model.ErpOrdenCompra;
import com.feraz.ordencompra.model.ErpOrdenCompraDet;
import com.feraz.ordencompra.model.ErpOrdenCompraDetId;
import com.feraz.ordencompra.model.ErpOrdenCompraId;
import com.feraz.ordencompra.model.ErpOrdenXPedidos;
import com.feraz.ordencompra.model.ErpOrdenXPedidosId;
import com.feraz.pedidos.mail.MailVerificacionProv;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import org.jamh.data.process.ProcessDao;
import org.jamh.wf.json.model.ResponseQuery;
import org.jamh.wf.json.model.ResponseQuery2;
import org.jamh.wf.process.Querys;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.context.request.WebRequest;

/**
 *
 * @author vavi
 */
@Controller
@RequestMapping("/orden")
@SessionAttributes({"compania", "usuario"})
public class ControlOrden {
    
    private ErpOrdenCompraDao erpOrdenCompraDao;
    private ErpOrdenCompraDetDao erpOrdenCompraDetDao;
    private ProcessDao processDao;
    private ErpOrdenXPedidosDao erpOrdenXPedidosDao;
     private MailVerificacionProv mailVerificacionProv;
    
        @RequestMapping(value = "/insert", method = RequestMethod.POST)
     public @ResponseBody
    ResponseQuery2 insertAction(String data, WebRequest webRequest, Model model) throws ParseException {

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
       
        try {
            int guardado = 0;
           
            ObjectMapper mapper = new ObjectMapper();
            DateFormat df = new SimpleDateFormat("dd/MM/yyyy");

            mapper.setDateFormat(df);
            List<OrdenDTO> lista = mapper.readValue(data,
                    mapper.getTypeFactory().constructCollectionType(List.class,
                            OrdenDTO.class));

            if (lista.isEmpty()) {
                rq.setSuccess(false);
                rq.setData(null);
                rq.setMsg("Error no existen datos que guardar");
                return rq;
            }
            
            ErpOrdenCompra orden = new ErpOrdenCompra();
            ErpOrdenCompraId ordenId = new ErpOrdenCompraId();
           
            ErpOrdenCompraId result = null;

            int val = 0;
            Iterator<OrdenDTO> it = lista.iterator();
           
            
            while (it.hasNext()) {
                 System.out.println("-------------------------------------------------------------------");
                OrdenDTO ss = it.next();
                
                ordenId.setCompania(compania);
                
                int id = erpOrdenCompraDao.getMaxIdOrden(ordenId);
                System.out.println(id);
                ordenId.setId(id);
                
                System.out.println(ss.calendario);
                
                orden.setId(ordenId);
                
                String folio = agregarCeros(String.valueOf(id),5);
                int year = Calendar.getInstance().get(Calendar.YEAR);
                orden.setFolio(String.valueOf(year) + folio);
              //  orden.setCalendario(Integer.parseInt(ss.calendario));
                orden.setClasificacion(ss.clasificacion);
                orden.setCondicionesPago(ss.condicionesPago);
                orden.setCtoCto(ss.ctoCto);
                orden.setEstatus("PE");
                orden.setIdCondiciones(ss.idCondiciones);
                orden.setFechaOrden(df.parse(ss.fechaOrden));
                orden.setFechaRequerida(df.parse(ss.fechaRequerida));
            //    orden.setFolio(Integer.parseInt(ss.folio));
                orden.setIdAlmacen(Integer.parseInt(ss.idAlmacen));
                orden.setIdProveedor(ss.idProveedor);
                //orden.setImporte(new BigDecimal(ss.importe));
                //orden.setPeriodo(Integer.parseInt(ss.periodo));
                orden.setRfc(ss.rfc);
                orden.setTotal(new BigDecimal(0));
                orden.setTotalPendiente(new BigDecimal(0));
                orden.setUsuario(usuario);
                orden.setUsuarioAutorizo(usuario);
                orden.setUsuarioComprador(ss.usuarioComprador);
                orden.setNombre(ss.nombre);
                orden.setDescripcion(ss.descripcion);
                 if(ss.tipo.equalsIgnoreCase("")){
                       orden.setTipo("O");

                }else{
                       orden.setTipo(ss.tipo);

                }
                
                
                result = erpOrdenCompraDao.save(orden);

            }

          
            

            if (result != null) {
               
                 
                    rq.setSuccess(true);
                //    rq.setData(listaAciertos);
                //    rq.setDataErr(lisErr2);
                    rq.setMsg("Guardados Correctamente");
                 
            } else {
                rq.setSuccess(false);
                rq.setData(null);
             //   rq.setDataErr(lisErr2);
                rq.setMsg("Error al guardar");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return rq;

    }
    
    
            @RequestMapping(value = "/update", method = RequestMethod.POST)
     public @ResponseBody
    ResponseQuery2 updateAction(String data, WebRequest webRequest, Model model) throws ParseException {

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
       
        try {
            int guardado = 0;
           
            ObjectMapper mapper = new ObjectMapper();
            DateFormat df = new SimpleDateFormat("dd/MM/yyyy");

            mapper.setDateFormat(df);
            List<OrdenDTO> lista = mapper.readValue(data,
                    mapper.getTypeFactory().constructCollectionType(List.class,
                            OrdenDTO.class));

            if (lista.isEmpty()) {
                rq.setSuccess(false);
                rq.setData(null);
                rq.setMsg("Error no existen datos que guardar");
                return rq;
            }
            
            ErpOrdenCompra orden = new ErpOrdenCompra();
            ErpOrdenCompraId ordenId = new ErpOrdenCompraId();
           
            boolean result = false;

            int val = 0;
            Iterator<OrdenDTO> it = lista.iterator();
           
            
            while (it.hasNext()) {
                 System.out.println("-------------------------------------------------------------------");
                OrdenDTO ss = it.next();
                
                ordenId.setCompania(compania);
               
                ordenId.setId(Integer.parseInt(ss.id));
                
                System.out.println(ss.calendario);
                
                orden.setId(ordenId);
              //  orden.setCalendario(Integer.parseInt(ss.calendario));
                orden.setClasificacion(ss.clasificacion);
                orden.setFolio(ss.folio);
                orden.setCondicionesPago(ss.condicionesPago);
                orden.setIdCondiciones(ss.idCondiciones);
                orden.setCtoCto(ss.ctoCto);
                orden.setEstatus(ss.estatus);
                orden.setFechaOrden(df.parse(ss.fechaOrden));
                orden.setFechaRequerida(df.parse(ss.fechaRequerida));
            //    orden.setFolio(Integer.parseInt(ss.folio));
                orden.setIdAlmacen(Integer.parseInt(ss.idAlmacen));
                orden.setIdProveedor(ss.idProveedor);
                //orden.setImporte(new BigDecimal(ss.importe));
                //orden.setPeriodo(Integer.parseInt(ss.periodo));
                orden.setRfc(ss.rfc);
                orden.setTotal(new BigDecimal(ss.total));
                orden.setTotalPendiente(new BigDecimal(ss.totalPendiente));
                orden.setUsuario(usuario);
                orden.setUsuarioAutorizo(usuario);
                orden.setUsuarioComprador(ss.usuarioComprador);
                orden.setNombre(ss.nombre);
                orden.setDescripcion(ss.descripcion);
                if(ss.tipo.equalsIgnoreCase("")){
                       orden.setTipo("O");

                }else{
                       orden.setTipo(ss.tipo);

                }

                
                
                result = erpOrdenCompraDao.update(orden);

            }

          
            

            if (result == true) {
               
                 
                    rq.setSuccess(true);
                //    rq.setData(listaAciertos);
                //    rq.setDataErr(lisErr2);
                    rq.setMsg("Guardados Correctamente");
                 
            } else {
                rq.setSuccess(false);
                rq.setData(null);
             //   rq.setDataErr(lisErr2);
                rq.setMsg("Error al guardar");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return rq;

    }
    
                @RequestMapping(value = "/delete", method = RequestMethod.POST)
     public @ResponseBody
    ResponseQuery2 deleteAction(String data, WebRequest webRequest, Model model) throws ParseException {

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
       
        try {
            int guardado = 0;
           
            ObjectMapper mapper = new ObjectMapper();
            DateFormat df = new SimpleDateFormat("dd/MM/yyyy");

            mapper.setDateFormat(df);
            List<OrdenDTO> lista = mapper.readValue(data,
                    mapper.getTypeFactory().constructCollectionType(List.class,
                            OrdenDTO.class));

            if (lista.isEmpty()) {
                rq.setSuccess(false);
                rq.setData(null);
                rq.setMsg("Error no existen datos que guardar");
                return rq;
            }
            
            ErpOrdenCompra orden = new ErpOrdenCompra();
            ErpOrdenCompraId ordenId = new ErpOrdenCompraId();
            
            ErpOrdenCompraDet det  = new ErpOrdenCompraDet();
            ErpOrdenCompraDetId detId= new ErpOrdenCompraDetId();
           
            boolean result = false;

            int val = 0;
            Iterator<OrdenDTO> it = lista.iterator();
           
            
            while (it.hasNext()) {
                 System.out.println("-------------------------------------------------------------------");
                OrdenDTO ss = it.next();
                
                ordenId.setCompania(compania);
               
                ordenId.setId(Integer.parseInt(ss.id));
                
                System.out.println(ss.calendario);
                
                orden.setId(ordenId);
             
                System.out.println("Borrando orden");
                result = erpOrdenCompraDao.delete(orden);
                
                detId.setCompania(compania);
                detId.setIdOrdenCompra(Integer.parseInt(ss.id));
                det.setId(detId);
                System.out.println("Borrando detalle");
                erpOrdenCompraDetDao.borraDetalleOrden(det);

            }

          
            

            if (result == true) {
               
                 
                    rq.setSuccess(true);
                //    rq.setData(listaAciertos);
                //    rq.setDataErr(lisErr2);
                    rq.setMsg("Borrado Correctamente");
                 
            } else {
                rq.setSuccess(false);
                rq.setData(null);
             //   rq.setDataErr(lisErr2);
                rq.setMsg("Error al borra");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return rq;

    }

    
            
         @RequestMapping(value = "/actualizaEstatusGasto", method = RequestMethod.POST)
     public @ResponseBody
    ResponseQuery2 updateActionEstatusGasto(String data, WebRequest webRequest, Model model) throws ParseException {

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
       
        try {
            int guardado = 0;
           
            ObjectMapper mapper = new ObjectMapper();
            DateFormat df = new SimpleDateFormat("dd/MM/yyyy");

            mapper.setDateFormat(df);
            List<OrdenDTO> lista = mapper.readValue(data,
                    mapper.getTypeFactory().constructCollectionType(List.class,
                            OrdenDTO.class));

            if (lista.isEmpty()) {
                rq.setSuccess(false);
                rq.setData(null);
                rq.setMsg("Error no existen datos que guardar");
                return rq;
            }
            
            ErpOrdenCompra orden = new ErpOrdenCompra();
            ErpOrdenCompraId ordenId = new ErpOrdenCompraId();
            
            ErpOrdenXPedidos ordenPedido = new ErpOrdenXPedidos();
            ErpOrdenXPedidosId ordePedidoId = new ErpOrdenXPedidosId();
           
            boolean result = true;

            int val = 0;
            Iterator<OrdenDTO> it = lista.iterator();
           
            
            while (it.hasNext()) {
                 System.out.println("-------------------------------------------------------------------");
                OrdenDTO ss = it.next();
                
                ordenId.setCompania(compania);
               
                ordenId.setId(Integer.parseInt(ss.id));
                
                
                orden.setId(ordenId);
              //  orden.setCalendario(Integer.parseInt(ss.calendario));
   
                orden.setValidacionGasto("2");
          
                
              //  result = erpOrdenCompraDao.actualizaEstatusGasto(orden);
          
                 Querys que = new Querys();
                String store = que.getSQL("ACTUALIZA_AUT_DET_ORDEN");

                Map archivoOrden = new HashMap();

                archivoOrden.put("compania", compania);
                archivoOrden.put("id", ss.id);

                this.processDao.execute(store, archivoOrden);

            }

          
            

            if (result == true) {
               
                 
                    rq.setSuccess(true);
                //    rq.setData(listaAciertos);
                //    rq.setDataErr(lisErr2);
                    rq.setMsg("Guardados Correctamente");
                 
            } else {
                rq.setSuccess(false);
                rq.setData(null);
             //   rq.setDataErr(lisErr2);
                rq.setMsg("Error al guardar");
            }

        } catch (Exception e) {
            e.printStackTrace();
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
               String store = que.getSQL("ActualizaEstatusOrdenEnvioAuto");
               
                   Map parametros = new HashMap();
             
 //  P_SID              VARCHAR2,

             
                parametros.put("compania", compania);
                parametros.put("folio",folio);
           
        
               int val = processDao.execute(store, parametros);
               
          
            
            if (val > 0 ){
            
                
                    rq.setSuccess(true);
                    rq.setData(null);
                    rq.setMsg("Orden enviada a autorizar correctamente");
              
                
            }else{
                
                rq.setSuccess(true);
                rq.setData(null);
                rq.setMsg("Error al enviar");
                
            }
            

        }catch(Exception e){
            rq.setSuccess(false);
            rq.setData(null);
            rq.setMsg("Error al enviar:"+e.getMessage());
           return rq;
        }
        
        return rq;
    }
    
    
    
            
         @RequestMapping(value = "/actualizaSgAuto", method = RequestMethod.POST)
     public @ResponseBody
    ResponseQuery2 actualizaSgAuto(String data, WebRequest webRequest, Model model) throws ParseException {

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
       
        try {
            int guardado = 0;
           
            ObjectMapper mapper = new ObjectMapper();
            DateFormat df = new SimpleDateFormat("dd/MM/yyyy");

            mapper.setDateFormat(df);
            List<OrdenDTO> lista = mapper.readValue(data,
                    mapper.getTypeFactory().constructCollectionType(List.class,
                            OrdenDTO.class));

            if (lista.isEmpty()) {
                rq.setSuccess(false);
                rq.setData(null);
                rq.setMsg("Error no existen datos que guardar");
                return rq;
            }
            
            ErpOrdenCompra orden = new ErpOrdenCompra();
            ErpOrdenCompraId ordenId = new ErpOrdenCompraId();
            
           
            boolean result = false;

            int val = 0;
            Iterator<OrdenDTO> it = lista.iterator();
           
            
            while (it.hasNext()) {
                 System.out.println("-------------------------------------------------------------------");
                OrdenDTO ss = it.next();
                
                ordenId.setCompania(compania);
               
                ordenId.setId(Integer.parseInt(ss.id));
                
                
                orden.setId(ordenId);
              //  orden.setCalendario(Integer.parseInt(ss.calendario));
   
               
                orden.setUsuarioAutorizo(usuario);
          
                
                result = erpOrdenCompraDao.actualizaEstatusSg(orden,usuario,"1");
                
                   Map ordenFact = new HashMap();
                    ordenFact.put("compania", compania);
                    ordenFact.put("idOrden", ss.id);

                    List listDbAuto = this.processDao.getMapResult("BuscaDobleAutorizacion", ordenFact);  
                    Map aut = (HashMap)listDbAuto.get(0);

                    String estatus = aut.get("ID_AUTO").toString();
                    
                    if(estatus.equalsIgnoreCase("1")){
                              orden.setEstatus("AU");
                              erpOrdenCompraDao.actualizaEstatus(orden);
                    
                    
                    }else{
                    
                                orden.setEstatus("AP");
                             erpOrdenCompraDao.actualizaEstatus(orden);
                    }
                
              

            }

          
            

            if (result == true) {
               
                 
                    rq.setSuccess(true);
                //    rq.setData(listaAciertos);
                //    rq.setDataErr(lisErr2);
                    rq.setMsg("Guardados Correctamente");
                 
            } else {
                rq.setSuccess(false);
                rq.setData(null);
             //   rq.setDataErr(lisErr2);
                rq.setMsg("Error al guardar");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return rq;

    }        
            
            @RequestMapping(value = "/actualizaPrAuto", method = RequestMethod.POST)
     public @ResponseBody
    ResponseQuery2 actualizaPrAuto(String data, WebRequest webRequest, Model model) throws ParseException {

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
       
        try {
            int guardado = 0;
           
            ObjectMapper mapper = new ObjectMapper();
            DateFormat df = new SimpleDateFormat("dd/MM/yyyy");

            mapper.setDateFormat(df);
            List<OrdenDTO> lista = mapper.readValue(data,
                    mapper.getTypeFactory().constructCollectionType(List.class,
                            OrdenDTO.class));

            if (lista.isEmpty()) {
                rq.setSuccess(false);
                rq.setData(null);
                rq.setMsg("Error no existen datos que guardar");
                return rq;
            }
            
            ErpOrdenCompra orden = new ErpOrdenCompra();
            ErpOrdenCompraId ordenId = new ErpOrdenCompraId();
            
           
            boolean result = false;

            int val = 0;
            Iterator<OrdenDTO> it = lista.iterator();
           
            
            while (it.hasNext()) {
                 System.out.println("-------------------------------------------------------------------");
                OrdenDTO ss = it.next();
                
                ordenId.setCompania(compania);
               
                ordenId.setId(Integer.parseInt(ss.id));
                
                
                orden.setId(ordenId);
              //  orden.setCalendario(Integer.parseInt(ss.calendario));
   
                orden.setEstatus(ss.estatus);
                orden.setUsuarioAutorizo(usuario);
          
                
                result = erpOrdenCompraDao.actualizaEstatusPr(orden,usuario,"1");
                
                
                  Map ordenFact = new HashMap();
                    ordenFact.put("compania", compania);
                    ordenFact.put("idOrden", ss.id);

                    List listDbAuto = this.processDao.getMapResult("BuscaDobleAutorizacion", ordenFact);  
                    Map aut = (HashMap)listDbAuto.get(0);

                    String estatus = aut.get("ID_AUTO").toString();
                    
                    if(estatus.equalsIgnoreCase("1")){
                              orden.setEstatus("AU");
                              erpOrdenCompraDao.actualizaEstatus(orden);
                    
                    
                    }else{
                    
                                orden.setEstatus("AP");
                             erpOrdenCompraDao.actualizaEstatus(orden);
                    }
                
                
              

            }

          
            

            if (result == true) {
               
                 
                    rq.setSuccess(true);
                //    rq.setData(listaAciertos);
                //    rq.setDataErr(lisErr2);
                    rq.setMsg("Guardados Correctamente");
                 
            } else {
                rq.setSuccess(false);
                rq.setData(null);
             //   rq.setDataErr(lisErr2);
                rq.setMsg("Error al guardar");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return rq;

    }
            
        
            @RequestMapping(value = "/actualizaEstatus", method = RequestMethod.POST)
     public @ResponseBody
    ResponseQuery2 updateActionEstatus(String data, WebRequest webRequest, Model model) throws ParseException {

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
       
        try {
            int guardado = 0;
           
            ObjectMapper mapper = new ObjectMapper();
            DateFormat df = new SimpleDateFormat("dd/MM/yyyy");

            mapper.setDateFormat(df);
            List<OrdenDTO> lista = mapper.readValue(data,
                    mapper.getTypeFactory().constructCollectionType(List.class,
                            OrdenDTO.class));

            if (lista.isEmpty()) {
                rq.setSuccess(false);
                rq.setData(null);
                rq.setMsg("Error no existen datos que guardar");
                return rq;
            }
            
            ErpOrdenCompra orden = new ErpOrdenCompra();
            ErpOrdenCompraId ordenId = new ErpOrdenCompraId();
            
            ErpOrdenXPedidos ordenPedido = new ErpOrdenXPedidos();
            ErpOrdenXPedidosId ordePedidoId = new ErpOrdenXPedidosId();
           
            boolean result = false;

            int val = 0;
            Iterator<OrdenDTO> it = lista.iterator();
           
            
            while (it.hasNext()) {
                 System.out.println("-------------------------------------------------------------------");
                OrdenDTO ss = it.next();
                
                ordenId.setCompania(compania);
               
                ordenId.setId(Integer.parseInt(ss.id));
                
                
                orden.setId(ordenId);
              //  orden.setCalendario(Integer.parseInt(ss.calendario));
   
                orden.setEstatus(ss.estatus);
                orden.setUsuarioAutorizo(usuario);
          
                
                result = erpOrdenCompraDao.actualizaEstatus(orden);
                
                if(ss.estatus.equalsIgnoreCase("CA")){
                
                        Querys que = new Querys();
                    String store = que.getSQL("ActualizaPedidosCan");

                    Map archivoPedidos = new HashMap();

                    archivoPedidos.put("compania", compania);
                    archivoPedidos.put("ordenCompra", ss.id);
                    archivoPedidos.put("estatus", "P");

                         processDao.execute(store, archivoPedidos); 
                         
                         
                     ordePedidoId.setCompania(compania);
                     ordePedidoId.setOrdenCompra(Integer.parseInt(ss.id));
                     ordenPedido.setId(ordePedidoId);
                     
                     erpOrdenXPedidosDao.borraPorOrden(ordenPedido);
                         
                         
                    
                
                }

            }

          
            

            if (result == true) {
               
                 
                    rq.setSuccess(true);
                //    rq.setData(listaAciertos);
                //    rq.setDataErr(lisErr2);
                    rq.setMsg("Guardados Correctamente");
                 
            } else {
                rq.setSuccess(false);
                rq.setData(null);
             //   rq.setDataErr(lisErr2);
                rq.setMsg("Error al guardar");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return rq;

    }
    
    
    
            @RequestMapping(value = "/actualizaEstatusCorreo", method = RequestMethod.POST)
     public @ResponseBody
    ResponseQuery2 updateActionEstatusCorreo(String data, WebRequest webRequest, Model model) throws ParseException {

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
       
        try {
            int guardado = 0;
           
            ObjectMapper mapper = new ObjectMapper();
            DateFormat df = new SimpleDateFormat("dd/MM/yyyy");

            mapper.setDateFormat(df);
            List<OrdenDTO> lista = mapper.readValue(data,
                    mapper.getTypeFactory().constructCollectionType(List.class,
                            OrdenDTO.class));

            if (lista.isEmpty()) {
                rq.setSuccess(false);
                rq.setData(null);
                rq.setMsg("Error no existen datos que guardar");
                return rq;
            }
            
            ErpOrdenCompra orden = new ErpOrdenCompra();
            ErpOrdenCompraId ordenId = new ErpOrdenCompraId();
            
            ErpOrdenXPedidos ordenPedido = new ErpOrdenXPedidos();
            ErpOrdenXPedidosId ordePedidoId = new ErpOrdenXPedidosId();
           
            boolean result = false;

            int val = 0;
            Iterator<OrdenDTO> it = lista.iterator();
           
            
            while (it.hasNext()) {
                 System.out.println("-------------------------------------------------------------------");
                OrdenDTO ss = it.next();
                
                ordenId.setCompania(compania);
               
                ordenId.setId(Integer.parseInt(ss.id));
                
                
                orden.setId(ordenId);
              //  orden.setCalendario(Integer.parseInt(ss.calendario));
   
                orden.setEstatus(ss.estatus);
                orden.setUsuarioAutorizo(usuario);
          
                
                result = erpOrdenCompraDao.actualizaEstatus(orden);
                
                if(ss.estatus.equalsIgnoreCase("CA")){
                
                        Querys que = new Querys();
                    String store = que.getSQL("ActualizaPedidosCan");

                    Map archivoPedidos = new HashMap();

                    archivoPedidos.put("compania", compania);
                    archivoPedidos.put("ordenCompra", ss.id);
                    archivoPedidos.put("estatus", "P");

                         processDao.execute(store, archivoPedidos); 
                         
                         
                     ordePedidoId.setCompania(compania);
                     ordePedidoId.setOrdenCompra(Integer.parseInt(ss.id));
                     ordenPedido.setId(ordePedidoId);
                     
                     erpOrdenXPedidosDao.borraPorOrden(ordenPedido);
                         
                         
                    
                
                }else{
                
                String rutaRep = "https://appferaz1.com/fenius/servlet/ada_ServAppletSqr?dic_sistema=empres&catalogo=JRepOrdenCompra&dic_idioma=Esp&dic_estado=66&ck_htm=on&compania="+compania+"&id="+ss.id+"&reporte=JRepOrdenCompraPOR";
                     
                System.out.println("rutaRep: "+rutaRep);
                URL url = new URL(rutaRep);
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

                int intIndex = urlString.indexOf("/empres/JasperReports/Reportes/JRepOrdenCompraPOR/JRepOrdenCompraPOR_");
                int intIndex3 = urlString.indexOf("/empres/JasperReports/Reportes/JRepOrdenCompraPOR/JRepOrdenCompraPOR_",intIndex + 2);
                int intIndex2 = urlString.indexOf(".pdf");
                System.out.println("ID");
                System.out.println(urlString.substring(intIndex3 + 69, intIndex2));

                String idReporte = urlString.substring(intIndex3 + 69, intIndex2);
                
                
                
               Map noOrd = new HashMap();

                noOrd.put("compania", compania);
                noOrd.put("orden", ss.id);

                List listProv = processDao.getMapResult("BuscaCorreosAutorizaOrden", noOrd);
                
                if (!listProv.isEmpty()){

                        Map provSec = (HashMap) listProv.get(0);



                        String correoCopia = "";
                        String correoUsuario = "";
                        String correoProv = "";



                        if(provSec.get("CORREO_USUARIO") != null && provSec.get("CORREO_PROV") != null){

                            correoUsuario=provSec.get("CORREO_USUARIO").toString();
                            correoProv=provSec.get("CORREO_PROV").toString();

                               Map correoDet = new HashMap();

                                    correoDet.put("compania", compania);
                                    correoDet.put("orden", ss.id);

                                     List listCorreoDet = processDao.getMapResult("BuscaDatosDetCorreoOrden", correoDet);


                                mailVerificacionProv.sendMailAut("", correoProv, usuario, compania,ss.id,idReporte,correoUsuario,listCorreoDet);



                        }
                }
                
               
               
              
                
                }

            }

          
            

            if (result == true) {
               
                 
                    rq.setSuccess(true);
                //    rq.setData(listaAciertos);
                //    rq.setDataErr(lisErr2);
                    rq.setMsg("Guardados Correctamente");
                 
            } else {
                rq.setSuccess(false);
                rq.setData(null);
             //   rq.setDataErr(lisErr2);
                rq.setMsg("Error al guardar");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return rq;

    }
    
     private static String agregarCeros(String string, int largo)
        {
          String ceros = "";

          int cantidad = largo - string.length();
          if (cantidad >= 1)
          {
            for (int i = 0; i < cantidad; i++) {
              ceros = ceros + "0";
            }
            return ceros + string;
          }
          return string;
        }
  
    
    public void setErpOrdenCompraDao(ErpOrdenCompraDao erpOrdenCompraDao) {
        this.erpOrdenCompraDao = erpOrdenCompraDao;
    }

    public void setErpOrdenCompraDetDao(ErpOrdenCompraDetDao erpOrdenCompraDetDao) {
        this.erpOrdenCompraDetDao = erpOrdenCompraDetDao;
    }

    public void setProcessDao(ProcessDao processDao) {
        this.processDao = processDao;
    }

    public void setErpOrdenXPedidosDao(ErpOrdenXPedidosDao erpOrdenXPedidosDao) {
        this.erpOrdenXPedidosDao = erpOrdenXPedidosDao;
    }

    public void setMailVerificacionProv(MailVerificacionProv mailVerificacionProv) {
        this.mailVerificacionProv = mailVerificacionProv;
    }
     
    
}
