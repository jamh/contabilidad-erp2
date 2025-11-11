/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.feraz.ordencompra.controll;

/**
 *
 * @author vavi
 */
import com.fasterxml.jackson.databind.ObjectMapper;
import com.feraz.ordencompra.dao.ErpOrdenCompraDao;
import com.feraz.ordencompra.dao.ErpOrdenCompraDetDao;
import com.feraz.ordencompra.dao.ErpOrdenXFacturaDao;
import com.feraz.ordencompra.dao.ErpOrdenXPedidosDao;
import com.feraz.ordencompra.dto.OrdenDTO;
import com.feraz.ordencompra.dto.OrdenDetDTO;
import com.feraz.ordencompra.dto.OrdenDetDTOAut;
import com.feraz.ordencompra.dto.PedidosDTO;
import com.feraz.ordencompra.dto.PedidosDetDTO;
import com.feraz.ordencompra.dto.RelFactDTO;
import com.feraz.ordencompra.model.ErpOrdenCompra;
import com.feraz.ordencompra.model.ErpOrdenCompraDet;
import com.feraz.ordencompra.model.ErpOrdenCompraDetId;
import com.feraz.ordencompra.model.ErpOrdenCompraId;
import com.feraz.ordencompra.model.ErpOrdenXFactura;
import com.feraz.ordencompra.model.ErpOrdenXFacturaId;
import com.feraz.ordencompra.model.ErpOrdenXPedidos;
import com.feraz.ordencompra.model.ErpOrdenXPedidosId;
import java.math.BigDecimal;
import java.math.MathContext;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import javax.swing.JFormattedTextField;
import javax.swing.text.MaskFormatter;
import org.jamh.data.process.ProcessDao;
import org.jamh.wf.json.model.ResponseQuery;
import org.jamh.wf.json.model.ResponseQuery2;
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
 * @author vavi
 */
@Controller
@RequestMapping("/detOrden")
@SessionAttributes({"compania", "usuario"})
public class ControlDetOrden {
    
    private ErpOrdenCompraDao erpOrdenCompraDao;
    private ErpOrdenCompraDetDao erpOrdenCompraDetDao;
    private ErpOrdenXFacturaDao erpOrdenXFacturaDao;
    private ProcessDao processDao;
    private ErpOrdenXPedidosDao erpOrdenXPedidosDao;
    
    
 @RequestMapping(value={"/insert"}, method={org.springframework.web.bind.annotation.RequestMethod.POST})
  @ResponseBody
  public ResponseQuery2 insertAction(String data, WebRequest webRequest, Model model)
    throws ParseException
  {
    ResponseQuery2 rq = new ResponseQuery2();
    SimpleDateFormat formatoDelTexto = new SimpleDateFormat("dd/MM/yyyy");
    int index = data.indexOf("[");
    if (index == -1) {
      data = "[" + data + "]";
    }
    System.out.println("data:" + data);
    if (model.asMap().get("compania") == null)
    {
      rq.setSuccess(false);
      rq.setData(null);
      rq.setMsg("La sesion no es valida.");
      return rq;
    }
    String compania = model.asMap().get("compania").toString();
    String usuario = model.asMap().get("usuario").toString();
    try
    {
      int guardado = 0;
      
      ObjectMapper mapper = new ObjectMapper();
      DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
      
      mapper.setDateFormat(df);
      List<OrdenDetDTO> lista = (List)mapper.readValue(data, mapper
        .getTypeFactory().constructCollectionType(List.class, OrdenDetDTO.class));
      if (lista.isEmpty())
      {
        rq.setSuccess(false);
        rq.setData(null);
        rq.setMsg("Error no existen datos que guardar");
        return rq;
      }
      ErpOrdenCompraDet ordenDet = new ErpOrdenCompraDet();
      ErpOrdenCompraDetId ordenDetId = new ErpOrdenCompraDetId();
      

      int val = 0;
      Iterator<OrdenDetDTO> it = lista.iterator();
      
      ErpOrdenCompraDetId result = null;
      while (it.hasNext())
      {
        System.out.println("-------------------------------------------------------------------");
        OrdenDetDTO ss = (OrdenDetDTO)it.next();
        
        ordenDetId.setCompania(compania);
        ordenDetId.setIdOrdenCompra(Integer.valueOf(Integer.parseInt(ss.idOrdenCompra)));
        int id = this.erpOrdenCompraDetDao.getMaxIdDetOrden(ordenDetId);
        ordenDetId.setLinea(Integer.valueOf(id));
        

        String idOr = agregarCeros(ss.idOrdenCompra, 5);
        String idLi = agregarCeros(String.valueOf(id), 5);
        
        ordenDet.setId(ordenDetId);
        ordenDet.setCantidadLlego(new BigDecimal(0));
        ordenDet.setCantidadPedida(new BigDecimal(ss.cantidadPedida));
        ordenDet.setDescripcion(ss.descripcion);
        ordenDet.setIdAlmacen(Integer.valueOf(Integer.parseInt(ss.idAlmacen)));
        ordenDet.setIdDet(idOr + idLi);
        ordenDet.setIdEstatus(Integer.valueOf(1));
        if (!ss.idPrioridad.equalsIgnoreCase("")) {
          ordenDet.setIdPrioridad(Integer.valueOf(Integer.parseInt(ss.idPrioridad)));
        }
        if (!ss.fechaRequeridaDet.equalsIgnoreCase("")) {
          ordenDet.setFechaEntrega(df.parse(ss.fechaRequeridaDet));
        }
        ordenDet.setIdProducto(ss.idProducto);
        ordenDet.setImporteCotizacion(new BigDecimal(ss.importeCotizacion));
        ordenDet.setIva(new BigDecimal(0));
        ordenDet.setPrecioUnitario(new BigDecimal(0));
        ordenDet.setTotal(new BigDecimal(0));
        
        if(ss.idGerencia != null){
            if(!ss.idGerencia.equalsIgnoreCase("")){
                       ordenDet.setIdGerencia(Integer.parseInt(ss.idGerencia));
                    }
         }
        
        ordenDet.setAvionCC(ss.avionCC);
        ordenDet.setEstacion(ss.estacion);
        ordenDet.setGrupoGasto(ss.grupoGasto);
        ordenDet.setTipoGasto(ss.tipoGasto);
        ordenDet.settGDescripcion(ss.tgDescripcion);
        
        if(ss.departamento != null){
            if(!ss.departamento.equalsIgnoreCase("")){
                       ordenDet.setDepartamento(Integer.parseInt(ss.departamento));
                    }
         }
         
        
        if(ss.idFamilia != null){
            if(!ss.idFamilia.equalsIgnoreCase("")){
                       ordenDet.setIdFamilia(Integer.parseInt(ss.idFamilia));
                    }
         }
        
        if(ss.tipoProduccion != null){
        
            if(ss.tipoProduccion != ""){
                  ordenDet.setTipoProduccion(Integer.parseInt(ss.tipoProduccion));
            }
        
        }
        
        ordenDet.setEstatusLinea("1");
        if (!ss.importeCotizacionIva.equalsIgnoreCase("")) {
          ordenDet.setImporteCotizacionIva(new BigDecimal(ss.importeCotizacionIva));
        } else {
          ordenDet.setImporteCotizacionIva(new BigDecimal(0));
        }
        
       if(ss.ieps != null){
            if (!ss.ieps.equalsIgnoreCase("")) {
              ordenDet.setIeps(new BigDecimal(ss.ieps));
            } else {
              ordenDet.setIeps(new BigDecimal(0));
            }
        }else{
            ordenDet.setIeps(new BigDecimal(0));
        }
        
        
        if (!ss.importeCotizacionSub.equalsIgnoreCase("")) {
          ordenDet.setImporteCotizacionSub(new BigDecimal(ss.importeCotizacionSub));
        } else {
          ordenDet.setImporteCotizacionSub(new BigDecimal(0));
        }
        if (!ss.descuento.equalsIgnoreCase("")) {
          ordenDet.setDescuento(new BigDecimal(ss.descuento));
        } else {
          ordenDet.setDescuento(new BigDecimal(0));
        }
        
        
        if (!ss.descuento2.equalsIgnoreCase("")) {
          ordenDet.setDescuento2(new BigDecimal(ss.descuento2));
        } else {
          ordenDet.setDescuento2(new BigDecimal(0));
        }
        
        if (!ss.descuentoProv.equalsIgnoreCase("")) {
          ordenDet.setDescuentoProv(new BigDecimal(ss.descuentoProv));
        } else {
          ordenDet.setDescuentoProv(new BigDecimal(0));
        }
        ordenDet.setIdProyecto(ss.idProyecto);
        ordenDet.setIdMoneda(ss.idMoneda);
        ordenDet.setTasaIva(ss.tasaIva);
         if(ss.retencion != null){
            if(!ss.retencion.equalsIgnoreCase("")){
                       ordenDet.setRetencion(Integer.parseInt(ss.retencion));
                    }
         }
         
         if(ss.retencion2 != null){
            if(!ss.retencion2.equalsIgnoreCase("")){
                       ordenDet.setRetencion2(Integer.parseInt(ss.retencion2));
                    }
         }
        
        result = this.erpOrdenCompraDetDao.save(ordenDet);
        
        
             Querys que = new Querys();
               String store = que.getSQL("ActualizaImporteCotizacion");
               
                   Map parametros = new HashMap();
             
 //  P_SID              VARCHAR2,

             
                parametros.put("compania", compania);
                parametros.put("importeCotizacion",new BigDecimal(ss.importeCotizacionSub).divide(new BigDecimal(ss.cantidadPedida)));
                parametros.put("id",ss.idProducto);
           



               int valP = processDao.execute(store, parametros);
               
                
//                 Querys queConc = new Querys();
//                    String storeConc = queConc.getSQL("RelacionaTotaXOrden");
//
//
//                          
//                    Map concLinea = new HashMap();
//
//                    concLinea.put("compania", compania);
//                    concLinea.put("orden", ss.idOrdenCompra);
//                    concLinea.put("linea", ss.linea);
//                    concLinea.put("numeroFe", "");
//                    concLinea.put("origen", "");
//
//                    processDao.execute(storeConc, concLinea);     
//               
               
               
        
      }
      if (result != null)
      {
        rq.setSuccess(true);
        

        rq.setMsg("Guardados Correctamente");
      }
      else
      {
        rq.setSuccess(false);
        rq.setData(null);
        
        rq.setMsg("Error al guardar");
      }
    }
    catch (Exception e)
    {
      e.printStackTrace();
    }
    return rq;
  }
  
  @RequestMapping(value={"/update"}, method={org.springframework.web.bind.annotation.RequestMethod.POST})
  @ResponseBody
  public ResponseQuery2 updateAction(String data, WebRequest webRequest, Model model)
    throws ParseException
  {
    ResponseQuery2 rq = new ResponseQuery2();
    SimpleDateFormat formatoDelTexto = new SimpleDateFormat("dd/MM/yyyy");
    int index = data.indexOf("[");
    if (index == -1) {
      data = "[" + data + "]";
    }
    System.out.println("data:" + data);
    if (model.asMap().get("compania") == null)
    {
      rq.setSuccess(false);
      rq.setData(null);
      rq.setMsg("La sesion no es valida.");
      return rq;
    }
    String compania = model.asMap().get("compania").toString();
    String usuario = model.asMap().get("usuario").toString();
    try
    {
      int guardado = 0;
      
      ObjectMapper mapper = new ObjectMapper();
      DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
      
      mapper.setDateFormat(df);
      List<OrdenDetDTO> lista = (List)mapper.readValue(data, mapper
        .getTypeFactory().constructCollectionType(List.class, OrdenDetDTO.class));
      if (lista.isEmpty())
      {
        rq.setSuccess(false);
        rq.setData(null);
        rq.setMsg("Error no existen datos que guardar");
        return rq;
      }
      ErpOrdenCompraDet ordenDet = new ErpOrdenCompraDet();
      ErpOrdenCompraDetId ordenDetId = new ErpOrdenCompraDetId();
      

      int val = 0;
      Iterator<OrdenDetDTO> it = lista.iterator();
      
      boolean result = false;
      while (it.hasNext())
      {
        System.out.println("-------------------------------------------------------------------");
        OrdenDetDTO ss = (OrdenDetDTO)it.next();
        
        ordenDetId.setCompania(compania);
        ordenDetId.setIdOrdenCompra(Integer.valueOf(Integer.parseInt(ss.idOrdenCompra)));
        
        ordenDetId.setLinea(Integer.valueOf(Integer.parseInt(ss.linea)));
        
        ordenDet.setId(ordenDetId);
        
        ordenDet.setCantidadLlego(new BigDecimal(ss.cantidadLlego));
        ordenDet.setCantidadPedida(new BigDecimal(ss.cantidadPedida));
        ordenDet.setDescripcion(ss.descripcion);
        ordenDet.setIdAlmacen(Integer.valueOf(Integer.parseInt(ss.idAlmacen)));
        ordenDet.setIdDet(ss.id);
        ordenDet.setIdEstatus(Integer.valueOf(Integer.parseInt(ss.idEstatus)));
        if ((ss.idPrioridad != null) && 
          (!ss.idPrioridad.equalsIgnoreCase(""))) {
          ordenDet.setIdPrioridad(Integer.valueOf(Integer.parseInt(ss.idPrioridad)));
        }
        
        if (!ss.fechaRequeridaDet.equalsIgnoreCase("")) {
          ordenDet.setFechaEntrega(df.parse(ss.fechaRequeridaDet));
        }
        
        ordenDet.setIdProducto(ss.idProducto);
        ordenDet.setImporteCotizacion(new BigDecimal(ss.importeCotizacion));
        ordenDet.setIva(new BigDecimal(ss.iva));
        ordenDet.setPrecioUnitario(new BigDecimal(ss.precioUnitario));
        ordenDet.setTotal(new BigDecimal(ss.total));
        ordenDet.setEstatusLinea(ss.estatusLinea);
        if(ss.idGerencia != null){
            if(!ss.idGerencia.equalsIgnoreCase("")){
                       ordenDet.setIdGerencia(Integer.parseInt(ss.idGerencia));
                    }
         }
        
        ordenDet.setAvionCC(ss.avionCC);
        ordenDet.setEstacion(ss.estacion);
        ordenDet.setGrupoGasto(ss.grupoGasto);
        ordenDet.setTipoGasto(ss.tipoGasto);
        ordenDet.settGDescripcion(ss.tgDescripcion);
        
        if(ss.departamento != null){
            if(!ss.departamento.equalsIgnoreCase("")){
                       ordenDet.setDepartamento(Integer.parseInt(ss.departamento));
                    }
         }
         
        if(ss.idFamilia != null){
            if(!ss.idFamilia.equalsIgnoreCase("")){
                       ordenDet.setIdFamilia(Integer.parseInt(ss.idFamilia));
                    }
         }
        
        if(ss.tipoProduccion != null){
        
            if(ss.tipoProduccion != ""){
                  ordenDet.setTipoProduccion(Integer.parseInt(ss.tipoProduccion));
            }
        
        }
        if(ss.ieps != null){
            if (!ss.ieps.equalsIgnoreCase("")) {
              ordenDet.setIeps(new BigDecimal(ss.ieps));
            } else {
              ordenDet.setIeps(new BigDecimal(0));
            }
        }else{
            ordenDet.setIeps(new BigDecimal(0));
        }
        
        if (!ss.importeCotizacionIva.equalsIgnoreCase("")) {
          ordenDet.setImporteCotizacionIva(new BigDecimal(ss.importeCotizacionIva));
        } else {
          ordenDet.setImporteCotizacionIva(new BigDecimal(0));
        }
        if (!ss.importeCotizacionSub.equalsIgnoreCase("")) {
          ordenDet.setImporteCotizacionSub(new BigDecimal(ss.importeCotizacionSub));
        } else {
          ordenDet.setImporteCotizacionSub(new BigDecimal(0));
        }
        if (!ss.descuento.equalsIgnoreCase("")) {
          ordenDet.setDescuento(new BigDecimal(ss.descuento));
        } else {
          ordenDet.setDescuento(new BigDecimal(0));
        }
        
        if (ss.descuento2 != null) {
          ordenDet.setDescuento2(new BigDecimal(ss.descuento2));
        } else {
          ordenDet.setDescuento2(new BigDecimal(0));
        }
        
         if (!ss.descuentoProv.equalsIgnoreCase("")) {
          ordenDet.setDescuentoProv(new BigDecimal(ss.descuentoProv));
        } else {
          ordenDet.setDescuentoProv(new BigDecimal(0));
        }
        ordenDet.setIdProyecto(ss.idProyecto);
                ordenDet.setIdMoneda(ss.idMoneda);
                ordenDet.setTasaIva(ss.tasaIva);
                if(ss.retencion != null){
                    if(!ss.retencion.equalsIgnoreCase("")){
                       ordenDet.setRetencion(Integer.parseInt(ss.retencion));
                    }  
                }
                
                if(ss.retencion2 != null){
                    if(!ss.retencion2.equalsIgnoreCase("")){
                       ordenDet.setRetencion2(Integer.parseInt(ss.retencion2));
                    }  
                }

        result = this.erpOrdenCompraDetDao.update(ordenDet);
        
         Querys que = new Querys();
               String store = que.getSQL("ActualizaImporteCotizacion");
               
               String store2 = que.getSQL("ActualizaCCPedidos");
               
                   Map parametros = new HashMap();
                   
                   Map parametros2 = new HashMap();
             
 //  P_SID              VARCHAR2,
                
              
             
                parametros.put("compania", compania);
                parametros.put("importeCotizacion",ss.importeCotizacionSub);
                parametros.put("id",ss.idProducto);
                
                parametros2.put("compania", compania);
                parametros2.put("orden", ss.idOrdenCompra);
                parametros2.put("linea", ss.linea);
           



               int valP = processDao.execute(store, parametros);
               
               int valP2 = processDao.execute(store2, parametros2);
               
               
//                 Querys queConc = new Querys();
//                    String storeConc = queConc.getSQL("RelacionaTotaXOrden");
//
//
//                          
//                    Map concLinea = new HashMap();
//
//                    concLinea.put("compania", compania);
//                    concLinea.put("orden", ss.idOrdenCompra);
//                    concLinea.put("linea", ss.linea);
//                    concLinea.put("numeroFe", "");
//                    concLinea.put("origen", "");
//
//                    processDao.execute(storeConc, concLinea);     
            
          
        
      }
      if (result == true)
      {
        rq.setSuccess(true);
        

        rq.setMsg("Guardados Correctamente");
      }
      else
      {
        rq.setSuccess(false);
        rq.setData(null);
        
        rq.setMsg("Error al guardar");
      }
    }
    catch (Exception e)
    {
      e.printStackTrace();
    }
    return rq;
  }
  
  @RequestMapping(value={"/updateEstatus"}, method={org.springframework.web.bind.annotation.RequestMethod.POST})
  @ResponseBody
  public ResponseQuery2 updateActionEstatus(String data, WebRequest webRequest, Model model)
    throws ParseException
  {
    ResponseQuery2 rq = new ResponseQuery2();
    SimpleDateFormat formatoDelTexto = new SimpleDateFormat("dd/MM/yyyy");
    int index = data.indexOf("[");
    if (index == -1) {
      data = "[" + data + "]";
    }
    System.out.println("data:" + data);
    if (model.asMap().get("compania") == null)
    {
      rq.setSuccess(false);
      rq.setData(null);
      rq.setMsg("La sesion no es valida.");
      return rq;
    }
    String compania = model.asMap().get("compania").toString();
    String usuario = model.asMap().get("usuario").toString();
    try
    {
      int guardado = 0;
      
      ObjectMapper mapper = new ObjectMapper();
      DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
      
      mapper.setDateFormat(df);
      List<OrdenDetDTO> lista = (List)mapper.readValue(data, mapper
        .getTypeFactory().constructCollectionType(List.class, OrdenDetDTO.class));
      if (lista.isEmpty())
      {
        rq.setSuccess(false);
        rq.setData(null);
        rq.setMsg("Error no existen datos que guardar");
        return rq;
      }
      ErpOrdenCompraDet ordenDet = new ErpOrdenCompraDet();
      ErpOrdenCompraDetId ordenDetId = new ErpOrdenCompraDetId();
      

      int val = 0;
      Iterator<OrdenDetDTO> it = lista.iterator();
      
      boolean result = false;
      while (it.hasNext())
      {
        System.out.println("-------------------------------------------------------------------");
        OrdenDetDTO ss = (OrdenDetDTO)it.next();
        
        ordenDetId.setCompania(compania);
        ordenDetId.setIdOrdenCompra(Integer.valueOf(Integer.parseInt(ss.idOrdenCompra)));
        
        ordenDetId.setLinea(Integer.valueOf(Integer.parseInt(ss.linea)));
        
        ordenDet.setId(ordenDetId);
        

        ordenDet.setEstatusLinea(ss.estatusLinea);
        


        result = this.erpOrdenCompraDetDao.actualizaEstatus(ordenDet);
      }
      if (result == true)
      {
        rq.setSuccess(true);
        

        rq.setMsg("Guardados Correctamente");
      }
      else
      {
        rq.setSuccess(false);
        rq.setData(null);
        
        rq.setMsg("Error al guardar");
      }
    }
    catch (Exception e)
    {
      e.printStackTrace();
    }
    return rq;
  }
  
  @RequestMapping(value={"/delete"}, method={org.springframework.web.bind.annotation.RequestMethod.POST})
  @ResponseBody
  public ResponseQuery2 deleteAction(String data, WebRequest webRequest, Model model)
    throws ParseException
  {
    ResponseQuery2 rq = new ResponseQuery2();
    SimpleDateFormat formatoDelTexto = new SimpleDateFormat("dd/MM/yyyy");
    int index = data.indexOf("[");
    if (index == -1) {
      data = "[" + data + "]";
    }
    System.out.println("data:" + data);
    if (model.asMap().get("compania") == null)
    {
      rq.setSuccess(false);
      rq.setData(null);
      rq.setMsg("La sesion no es valida.");
      return rq;
    }
    String compania = model.asMap().get("compania").toString();
    String usuario = model.asMap().get("usuario").toString();
    try
    {
      int guardado = 0;
      
      ObjectMapper mapper = new ObjectMapper();
      DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
      
      mapper.setDateFormat(df);
      List<OrdenDetDTO> lista = (List)mapper.readValue(data, mapper
        .getTypeFactory().constructCollectionType(List.class, OrdenDetDTO.class));
      if (lista.isEmpty())
      {
        rq.setSuccess(false);
        rq.setData(null);
        rq.setMsg("Error no existen datos que guardar");
        return rq;
      }
      ErpOrdenCompraDet ordenDet = new ErpOrdenCompraDet();
      ErpOrdenCompraDetId ordenDetId = new ErpOrdenCompraDetId();
      
      ErpOrdenXPedidos erpOrdenXPedidos = new ErpOrdenXPedidos();
      ErpOrdenXPedidosId erpOrdenXPedidosId = new ErpOrdenXPedidosId();
      

      int val = 0;
      Iterator<OrdenDetDTO> it = lista.iterator();
      
      boolean result = false;
      while (it.hasNext())
      {
        System.out.println("-------------------------------------------------------------------");
        OrdenDetDTO ss = (OrdenDetDTO)it.next();
        
        ordenDetId.setCompania(compania);
        ordenDetId.setIdOrdenCompra(Integer.valueOf(Integer.parseInt(ss.idOrdenCompra)));
        
        ordenDetId.setLinea(Integer.valueOf(Integer.parseInt(ss.linea)));
        
        ordenDet.setId(ordenDetId);
        


        result = this.erpOrdenCompraDetDao.delete(ordenDet);
        
        erpOrdenXPedidosId.setCompania(compania);
        erpOrdenXPedidosId.setLinea(Integer.valueOf(Integer.parseInt(ss.linea)));
        erpOrdenXPedidosId.setOrdenCompra(Integer.valueOf(Integer.parseInt(ss.idOrdenCompra)));
        erpOrdenXPedidos.setId(erpOrdenXPedidosId);
        erpOrdenXPedidos.setUsuario(usuario);
        this.erpOrdenXPedidosDao.borraPorLinea(erpOrdenXPedidos);
        

        Querys que = new Querys();
        String store = que.getSQL("ActualizaPedidos");
        
        Map archivoPedidos = new HashMap();
        
        archivoPedidos.put("compania", compania);
        archivoPedidos.put("id", ss.idPedido);
        archivoPedidos.put("estatus", "C");
        
        this.processDao.execute(store, archivoPedidos);
        
         
//                 Querys queConc = new Querys();
//                    String storeConc = queConc.getSQL("RelacionaTotaXOrden");
//
//
//                          
//                    Map concLinea = new HashMap();
//
//                    concLinea.put("compania", compania);
//                    concLinea.put("orden", ss.idOrdenCompra);
//                    concLinea.put("linea", ss.linea);
//                    concLinea.put("numeroFe", "");
//                    concLinea.put("origen", "");
//
//                    processDao.execute(storeConc, concLinea);     
        
      }
      if (result == true)
      {
        rq.setSuccess(true);
        

        rq.setMsg("Guardados Correctamente");
      }
      else
      {
        rq.setSuccess(false);
        rq.setData(null);
        
        rq.setMsg("Error al guardar");
      }
    }
    catch (Exception e)
    {
      e.printStackTrace();
    }
    return rq;
  }
  
   @RequestMapping(value={"/insertAut"}, method={org.springframework.web.bind.annotation.RequestMethod.POST})
  @ResponseBody
  public ResponseQuery2 insertActionAut(String data, WebRequest webRequest, Model model)
    throws ParseException
  {
    ResponseQuery2 rq = new ResponseQuery2();
    SimpleDateFormat formatoDelTexto = new SimpleDateFormat("dd/MM/yyyy");
    int index = data.indexOf("[");
    if (index == -1) {
      data = "[" + data + "]";
    }
    System.out.println("data:" + data);
    if (model.asMap().get("compania") == null)
    {
      rq.setSuccess(false);
      rq.setData(null);
      rq.setMsg("La sesion no es valida.");
      return rq;
    }
    String compania = model.asMap().get("compania").toString();
    String usuario = model.asMap().get("usuario").toString();
    try
    {
      int guardado = 0;
      
      ObjectMapper mapper = new ObjectMapper();
      DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
      
      mapper.setDateFormat(df);
      List<OrdenDetDTOAut> lista = (List)mapper.readValue(data, mapper
        .getTypeFactory().constructCollectionType(List.class, OrdenDetDTOAut.class));
      if (lista.isEmpty())
      {
        rq.setSuccess(false);
        rq.setData(null);
        rq.setMsg("Error no existen datos que guardar");
        return rq;
      }
      ErpOrdenCompraDet ordenDet = new ErpOrdenCompraDet();
      ErpOrdenCompraDetId ordenDetId = new ErpOrdenCompraDetId();
      

      int val = 0;
      Iterator<OrdenDetDTOAut> it = lista.iterator();
      
      ErpOrdenCompraDetId result = null;
      while (it.hasNext())
      {
        System.out.println("-------------------------------------------------------------------");
        OrdenDetDTOAut ss = (OrdenDetDTOAut)it.next();
        
        ordenDetId.setCompania(compania);
        ordenDetId.setIdOrdenCompra(Integer.valueOf(Integer.parseInt(ss.idOrdenCompra)));
        int id = this.erpOrdenCompraDetDao.getMaxIdDetOrden(ordenDetId);
        ordenDetId.setLinea(Integer.valueOf(id));
        

        String idOr = agregarCeros(ss.idOrdenCompra, 5);
        String idLi = agregarCeros(String.valueOf(id), 5);
        
        ordenDet.setId(ordenDetId);
        ordenDet.setCantidadLlego(new BigDecimal(0));
        ordenDet.setCantidadPedida(new BigDecimal(ss.cantidadPedida));
        ordenDet.setDescripcion(ss.descripcion);
        ordenDet.setIdAlmacen(Integer.valueOf(Integer.parseInt(ss.idAlmacen)));
        ordenDet.setIdDet(idOr + idLi);
        ordenDet.setIdEstatus(Integer.valueOf(1));
        if (!ss.idPrioridad.equalsIgnoreCase("")) {
          ordenDet.setIdPrioridad(Integer.valueOf(Integer.parseInt(ss.idPrioridad)));
        }
        ordenDet.setIdProducto(ss.idProducto);
        ordenDet.setImporteCotizacion(new BigDecimal(ss.importeCotizacion));
        ordenDet.setIva(new BigDecimal(0));
        ordenDet.setPrecioUnitario(new BigDecimal(0));
        ordenDet.setTotal(new BigDecimal(0));
        
        if(ss.tipoProduccion != null){
        
            if(ss.tipoProduccion != ""){
                  ordenDet.setTipoProduccion(Integer.parseInt(ss.tipoProduccion));
            }
        
        }
        
        ordenDet.setEstatusLinea("1");
        if (!ss.importeCotizacionIva.equalsIgnoreCase("")) {
          ordenDet.setImporteCotizacionIva(new BigDecimal(ss.importeCotizacionIva));
        } else {
          ordenDet.setImporteCotizacionIva(new BigDecimal(0));
        }
        if (!ss.importeCotizacionSub.equalsIgnoreCase("")) {
          ordenDet.setImporteCotizacionSub(new BigDecimal(ss.importeCotizacionSub));
        } else {
          ordenDet.setImporteCotizacionSub(new BigDecimal(0));
        }
        if (!ss.descuento.equalsIgnoreCase("")) {
          ordenDet.setDescuento(new BigDecimal(ss.descuento));
        } else {
          ordenDet.setDescuento(new BigDecimal(0));
        }
        
        if (!ss.descuentoProv.equalsIgnoreCase("")) {
          ordenDet.setDescuentoProv(new BigDecimal(ss.descuentoProv));
        } else {
          ordenDet.setDescuentoProv(new BigDecimal(0));
        }
        ordenDet.setIdProyecto(ss.idProyecto);
        ordenDet.setIdMoneda(ss.idMoneda);
        ordenDet.setTasaIva(ss.tasaIva);
         if(ss.retencion != null){
            if(!ss.retencion.equalsIgnoreCase("")){
                       ordenDet.setRetencion(Integer.parseInt(ss.retencion));
                    }
         }
         
         
         if(ss.idFamilia != null){
            if(!ss.idFamilia.equalsIgnoreCase("")){
                       ordenDet.setIdFamilia(Integer.parseInt(ss.idFamilia));
                    }
         }
         
         if(ss.idDetFamilia != null){
            if(!ss.idDetFamilia.equalsIgnoreCase("")){
                       ordenDet.setIdDetFamilia(Integer.parseInt(ss.idDetFamilia));
                    }
         }
         
         if(ss.idGerencia != null){
            if(!ss.idGerencia.equalsIgnoreCase("")){
                       ordenDet.setIdGerencia(Integer.parseInt(ss.idGerencia));
                    }
         }
         
         ordenDet.setcCostos(ss.cCostos);
        
        result = this.erpOrdenCompraDetDao.save(ordenDet);
        
        
             Querys que = new Querys();
               String store = que.getSQL("ActualizaImporteCotizacion");
               
                   Map parametros = new HashMap();
             
 //  P_SID              VARCHAR2,

             
                parametros.put("compania", compania);
                parametros.put("importeCotizacion",new BigDecimal(ss.importeCotizacionSub).divide(new BigDecimal(ss.cantidadPedida)));
                parametros.put("id",ss.idProducto);
           



               int valP = processDao.execute(store, parametros);
        
      }
      if (result != null)
      {
        rq.setSuccess(true);
        

        rq.setMsg("Guardados Correctamente");
      }
      else
      {
        rq.setSuccess(false);
        rq.setData(null);
        
        rq.setMsg("Error al guardar");
      }
    }
    catch (Exception e)
    {
      e.printStackTrace();
    }
    return rq;
  }
  
  @RequestMapping(value={"/updateAut"}, method={org.springframework.web.bind.annotation.RequestMethod.POST})
  @ResponseBody
  public ResponseQuery2 updateActionAut(String data, WebRequest webRequest, Model model)
    throws ParseException
  {
    ResponseQuery2 rq = new ResponseQuery2();
    SimpleDateFormat formatoDelTexto = new SimpleDateFormat("dd/MM/yyyy");
    int index = data.indexOf("[");
    if (index == -1) {
      data = "[" + data + "]";
    }
    System.out.println("data:" + data);
    if (model.asMap().get("compania") == null)
    {
      rq.setSuccess(false);
      rq.setData(null);
      rq.setMsg("La sesion no es valida.");
      return rq;
    }
    String compania = model.asMap().get("compania").toString();
    String usuario = model.asMap().get("usuario").toString();
    try
    {
      int guardado = 0;
      
      ObjectMapper mapper = new ObjectMapper();
      DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
      
      mapper.setDateFormat(df);
      List<OrdenDetDTOAut> lista = (List)mapper.readValue(data, mapper
        .getTypeFactory().constructCollectionType(List.class, OrdenDetDTOAut.class));
      if (lista.isEmpty())
      {
        rq.setSuccess(false);
        rq.setData(null);
        rq.setMsg("Error no existen datos que guardar");
        return rq;
      }
      ErpOrdenCompraDet ordenDet = new ErpOrdenCompraDet();
      ErpOrdenCompraDetId ordenDetId = new ErpOrdenCompraDetId();
      

      int val = 0;
      Iterator<OrdenDetDTOAut> it = lista.iterator();
      
      boolean result = false;
      while (it.hasNext())
      {
        System.out.println("-------------------------------------------------------------------");
        OrdenDetDTOAut ss = (OrdenDetDTOAut)it.next();
        
        ordenDetId.setCompania(compania);
        ordenDetId.setIdOrdenCompra(Integer.valueOf(Integer.parseInt(ss.idOrdenCompra)));
        
        ordenDetId.setLinea(Integer.valueOf(Integer.parseInt(ss.linea)));
        
        ordenDet.setId(ordenDetId);
        
        ordenDet.setCantidadLlego(new BigDecimal(ss.cantidadLlego));
        ordenDet.setCantidadPedida(new BigDecimal(ss.cantidadPedida));
        ordenDet.setDescripcion(ss.descripcion);
        ordenDet.setIdAlmacen(Integer.valueOf(Integer.parseInt(ss.idAlmacen)));
        ordenDet.setIdDet(ss.id);
        ordenDet.setIdEstatus(Integer.valueOf(Integer.parseInt(ss.idEstatus)));
        if ((ss.idPrioridad != null) && 
          (!ss.idPrioridad.equalsIgnoreCase(""))) {
          ordenDet.setIdPrioridad(Integer.valueOf(Integer.parseInt(ss.idPrioridad)));
        }
        ordenDet.setIdProducto(ss.idProducto);
        ordenDet.setImporteCotizacion(new BigDecimal(ss.importeCotizacion));
        ordenDet.setIva(new BigDecimal(ss.iva));
        ordenDet.setPrecioUnitario(new BigDecimal(ss.precioUnitario));
        ordenDet.setTotal(new BigDecimal(ss.total));
        ordenDet.setEstatusLinea(ss.estatusLinea);
        
        if(ss.tipoProduccion != null){
        
            if(ss.tipoProduccion != ""){
                  ordenDet.setTipoProduccion(Integer.parseInt(ss.tipoProduccion));
            }
        
        }
        
        if (!ss.importeCotizacionIva.equalsIgnoreCase("")) {
          ordenDet.setImporteCotizacionIva(new BigDecimal(ss.importeCotizacionIva));
        } else {
          ordenDet.setImporteCotizacionIva(new BigDecimal(0));
        }
        if (!ss.importeCotizacionSub.equalsIgnoreCase("")) {
          ordenDet.setImporteCotizacionSub(new BigDecimal(ss.importeCotizacionSub));
        } else {
          ordenDet.setImporteCotizacionSub(new BigDecimal(0));
        }
        if (!ss.descuento.equalsIgnoreCase("")) {
          ordenDet.setDescuento(new BigDecimal(ss.descuento));
        } else {
          ordenDet.setDescuento(new BigDecimal(0));
        }
        
         if (!ss.descuentoProv.equalsIgnoreCase("")) {
          ordenDet.setDescuentoProv(new BigDecimal(ss.descuentoProv));
        } else {
          ordenDet.setDescuentoProv(new BigDecimal(0));
        }
        ordenDet.setIdProyecto(ss.idProyecto);
                ordenDet.setIdMoneda(ss.idMoneda);
                ordenDet.setTasaIva(ss.tasaIva);
                if(ss.retencion != null){
                    if(!ss.retencion.equalsIgnoreCase("")){
                       ordenDet.setRetencion(Integer.parseInt(ss.retencion));
                    }  
                }
                
           if(ss.idFamilia != null){
            if(!ss.idFamilia.equalsIgnoreCase("")){
                       ordenDet.setIdFamilia(Integer.parseInt(ss.idFamilia));
                    }
         }
         
         if(ss.idDetFamilia != null){
            if(!ss.idDetFamilia.equalsIgnoreCase("")){
                       ordenDet.setIdDetFamilia(Integer.parseInt(ss.idDetFamilia));
                    }
         }
         
         if(ss.idGerencia != null){
            if(!ss.idGerencia.equalsIgnoreCase("")){
                       ordenDet.setIdGerencia(Integer.parseInt(ss.idGerencia));
                    }
         }
         

         ordenDet.setcCostos(ss.cCostos);
         
        result = this.erpOrdenCompraDetDao.update(ordenDet);
        
         Querys que = new Querys();
               String store = que.getSQL("ActualizaImporteCotizacion");
               
                   Map parametros = new HashMap();
             
 //  P_SID              VARCHAR2,
                
              
             
                parametros.put("compania", compania);
                parametros.put("importeCotizacion",ss.importeCotizacionSub);
                parametros.put("id",ss.idProducto);
           



               int valP = processDao.execute(store, parametros);
        
      }
      if (result == true)
      {
        rq.setSuccess(true);
        

        rq.setMsg("Guardados Correctamente");
      }
      else
      {
        rq.setSuccess(false);
        rq.setData(null);
        
        rq.setMsg("Error al guardar");
      }
    }
    catch (Exception e)
    {
      e.printStackTrace();
    }
    return rq;
  }
  
  @RequestMapping(value={"/updateEstatusAut"}, method={org.springframework.web.bind.annotation.RequestMethod.POST})
  @ResponseBody
  public ResponseQuery2 updateActionEstatusAut(String data, WebRequest webRequest, Model model)
    throws ParseException
  {
    ResponseQuery2 rq = new ResponseQuery2();
    SimpleDateFormat formatoDelTexto = new SimpleDateFormat("dd/MM/yyyy");
    int index = data.indexOf("[");
    if (index == -1) {
      data = "[" + data + "]";
    }
    System.out.println("data:" + data);
    if (model.asMap().get("compania") == null)
    {
      rq.setSuccess(false);
      rq.setData(null);
      rq.setMsg("La sesion no es valida.");
      return rq;
    }
    String compania = model.asMap().get("compania").toString();
    String usuario = model.asMap().get("usuario").toString();
    try
    {
      int guardado = 0;
      
      ObjectMapper mapper = new ObjectMapper();
      DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
      
      mapper.setDateFormat(df);
      List<OrdenDetDTOAut> lista = (List)mapper.readValue(data, mapper
        .getTypeFactory().constructCollectionType(List.class, OrdenDetDTOAut.class));
      if (lista.isEmpty())
      {
        rq.setSuccess(false);
        rq.setData(null);
        rq.setMsg("Error no existen datos que guardar");
        return rq;
      }
      ErpOrdenCompraDet ordenDet = new ErpOrdenCompraDet();
      ErpOrdenCompraDetId ordenDetId = new ErpOrdenCompraDetId();
      

      int val = 0;
      Iterator<OrdenDetDTOAut> it = lista.iterator();
      
      boolean result = false;
      while (it.hasNext())
      {
        System.out.println("-------------------------------------------------------------------");
        OrdenDetDTOAut ss = (OrdenDetDTOAut)it.next();
        
        ordenDetId.setCompania(compania);
        ordenDetId.setIdOrdenCompra(Integer.valueOf(Integer.parseInt(ss.idOrdenCompra)));
        
        ordenDetId.setLinea(Integer.valueOf(Integer.parseInt(ss.linea)));
        
        ordenDet.setId(ordenDetId);
        

        ordenDet.setEstatusLinea(ss.estatusLinea);
        


        result = this.erpOrdenCompraDetDao.actualizaEstatus(ordenDet);
      }
      if (result == true)
      {
        rq.setSuccess(true);
        

        rq.setMsg("Guardados Correctamente");
      }
      else
      {
        rq.setSuccess(false);
        rq.setData(null);
        
        rq.setMsg("Error al guardar");
      }
    }
    catch (Exception e)
    {
      e.printStackTrace();
    }
    return rq;
  }
  
  @RequestMapping(value={"/deleteAut"}, method={org.springframework.web.bind.annotation.RequestMethod.POST})
  @ResponseBody
  public ResponseQuery2 deleteActionAut(String data, WebRequest webRequest, Model model)
    throws ParseException
  {
    ResponseQuery2 rq = new ResponseQuery2();
    SimpleDateFormat formatoDelTexto = new SimpleDateFormat("dd/MM/yyyy");
    int index = data.indexOf("[");
    if (index == -1) {
      data = "[" + data + "]";
    }
    System.out.println("data:" + data);
    if (model.asMap().get("compania") == null)
    {
      rq.setSuccess(false);
      rq.setData(null);
      rq.setMsg("La sesion no es valida.");
      return rq;
    }
    String compania = model.asMap().get("compania").toString();
    String usuario = model.asMap().get("usuario").toString();
    try
    {
      int guardado = 0;
      
      ObjectMapper mapper = new ObjectMapper();
      DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
      
      mapper.setDateFormat(df);
      List<OrdenDetDTOAut> lista = (List)mapper.readValue(data, mapper
        .getTypeFactory().constructCollectionType(List.class, OrdenDetDTOAut.class));
      if (lista.isEmpty())
      {
        rq.setSuccess(false);
        rq.setData(null);
        rq.setMsg("Error no existen datos que guardar");
        return rq;
      }
      ErpOrdenCompraDet ordenDet = new ErpOrdenCompraDet();
      ErpOrdenCompraDetId ordenDetId = new ErpOrdenCompraDetId();
      
      ErpOrdenXPedidos erpOrdenXPedidos = new ErpOrdenXPedidos();
      ErpOrdenXPedidosId erpOrdenXPedidosId = new ErpOrdenXPedidosId();
      

      int val = 0;
      Iterator<OrdenDetDTOAut> it = lista.iterator();
      
      boolean result = false;
      while (it.hasNext())
      {
        System.out.println("-------------------------------------------------------------------");
        OrdenDetDTOAut ss = (OrdenDetDTOAut)it.next();
        
        ordenDetId.setCompania(compania);
        ordenDetId.setIdOrdenCompra(Integer.valueOf(Integer.parseInt(ss.idOrdenCompra)));
        
        ordenDetId.setLinea(Integer.valueOf(Integer.parseInt(ss.linea)));
        
        ordenDet.setId(ordenDetId);
        


        result = this.erpOrdenCompraDetDao.delete(ordenDet);
        
        erpOrdenXPedidosId.setCompania(compania);
        erpOrdenXPedidosId.setLinea(Integer.valueOf(Integer.parseInt(ss.linea)));
        erpOrdenXPedidosId.setOrdenCompra(Integer.valueOf(Integer.parseInt(ss.idOrdenCompra)));
        erpOrdenXPedidos.setId(erpOrdenXPedidosId);
        erpOrdenXPedidos.setUsuario(usuario);
        this.erpOrdenXPedidosDao.borraPorLinea(erpOrdenXPedidos);
        

        Querys que = new Querys();
        String store = que.getSQL("ActualizaPedidos");
        
        Map archivoPedidos = new HashMap();
        
        archivoPedidos.put("compania", compania);
        archivoPedidos.put("id", ss.idPedido);
        archivoPedidos.put("estatus", "A");
        
        this.processDao.execute(store, archivoPedidos);
      }
      if (result == true)
      {
        rq.setSuccess(true);
        

        rq.setMsg("Guardados Correctamente");
      }
      else
      {
        rq.setSuccess(false);
        rq.setData(null);
        
        rq.setMsg("Error al guardar");
      }
    }
    catch (Exception e)
    {
      e.printStackTrace();
    }
    return rq;
  }
  
  
  @RequestMapping(value={"/saveRel"}, method={org.springframework.web.bind.annotation.RequestMethod.POST})
  @ResponseBody
  public ResponseQuery2 relacionAction(String data, String data2,WebRequest webRequest, Model model)
    throws ParseException
  {
    ResponseQuery2 rq = new ResponseQuery2();
    SimpleDateFormat formatoDelTexto = new SimpleDateFormat("dd/MM/yyyy");
    int index = data.indexOf("[");
    if (index == -1) {
      data = "[" + data + "]";
    }
    System.out.println("data:" + data);
    if (model.asMap().get("compania") == null)
    {
      rq.setSuccess(false);
      rq.setData(null);
      rq.setMsg("La sesion no es valida.");
      return rq;
    }
    String compania = model.asMap().get("compania").toString();
    String usuario = model.asMap().get("usuario").toString();
    
    System.out.println("-------------------------------------------------------------------");
    
    System.out.println("data: "+data);
    System.out.println("data2: "+data2);
    try
    {
      int guardado = 0;
      
      ObjectMapper mapper = new ObjectMapper();
      DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
      
      mapper.setDateFormat(df);
      List<RelFactDTO> lista = (List)mapper.readValue(data, mapper
        .getTypeFactory().constructCollectionType(List.class, RelFactDTO.class));
      
       List<OrdenDetDTO> listaDet = (List)mapper.readValue(data2, mapper
        .getTypeFactory().constructCollectionType(List.class, OrdenDetDTO.class));
       
      if (lista.isEmpty())
      {
        rq.setSuccess(false);
        rq.setData(null);
        rq.setMsg("Error no existen datos que guardar");
        return rq;
      }
      
      if (listaDet.isEmpty())
      {
        rq.setSuccess(false);
        rq.setData(null);
        rq.setMsg("Error no existen datos que guardar");
        return rq;
      }
      ErpOrdenXFactura ordeFact = new ErpOrdenXFactura();
      ErpOrdenXFacturaId ordenFactId = new ErpOrdenXFacturaId();
      
      System.out.println("-------------------------------------------------------------------");
      int val = 0;
     
      Iterator<OrdenDetDTO> itDet = listaDet.iterator();
      
      ErpOrdenXFacturaId result = null;
      
      int res = 0;
      
      while (itDet.hasNext()){
          OrdenDetDTO ss2 = (OrdenDetDTO)itDet.next();
          
          System.out.println("ss2.idOrdenCompra: "+ss2.idOrdenCompra);
          System.out.println("ss2.linea: "+ss2.linea);
          
           Iterator<RelFactDTO> it = lista.iterator();
          
            while (it.hasNext())
            {
              System.out.println("-------------------------------------------------------------------");
              RelFactDTO ss = (RelFactDTO)it.next();
              
              Map ordenFact = new HashMap();
              ordenFact.put("compania", compania);
              ordenFact.put("idOrden", ss2.idOrdenCompra);
              ordenFact.put("idOrdenDet", ss2.linea);
              ordenFact.put("origen", ss.origen);
              ordenFact.put("numero", ss.numeroFe);

              
              List listValidaCarga = this.processDao.getMapResult("BuscaRelacionOF", ordenFact);
              
              if (listValidaCarga.isEmpty()){
                  
                    res = 1;

                    ordenFactId.setCompania(compania);
                    ordenFactId.setIdOrden(Integer.valueOf(Integer.parseInt(ss2.idOrdenCompra)));
                    ordenFactId.setIdOrdenDet(Integer.valueOf(Integer.parseInt(ss2.linea)));
                    ordenFactId.setNumeroFe(Integer.valueOf(Integer.parseInt(ss.numeroFe)));
                    ordeFact.setId(ordenFactId);
                    ordeFact.setOrigen(ss.origen);
                    ordeFact.setSubtotal(new BigDecimal(ss.subtotal));
                    ordeFact.setIva(new BigDecimal(ss.iva));
                    ordeFact.setTotal(new BigDecimal(ss.total));

                    result = this.erpOrdenXFacturaDao.save(ordeFact);



                    List listOrdenFact = this.processDao.getMapResult("BuscaImportesOrdenDet", ordenFact);

                    List listOrdenFactM = this.processDao.getMapResult("BuscaImportesOrden", ordenFact);

                    Map ordenTotal = (HashMap)listOrdenFact.get(0);
                    Map ordenMTotal = (HashMap)listOrdenFactM.get(0);

                    System.out.println("Obteniendo mapa");

                    ErpOrdenCompraDet ordenDet = new ErpOrdenCompraDet();
                    ErpOrdenCompraDetId ordenDetId = new ErpOrdenCompraDetId();

                    ErpOrdenCompra orden = new ErpOrdenCompra();
                    ErpOrdenCompraId ordenId = new ErpOrdenCompraId();


                    ordenDetId.setCompania(compania);
                    ordenDetId.setIdOrdenCompra(Integer.valueOf(Integer.parseInt(ss2.idOrdenCompra)));
                    ordenDetId.setLinea(Integer.valueOf(Integer.parseInt(ss2.linea)));

                    ordenDet.setId(ordenDetId);
                    ordenDet.setTotal(new BigDecimal(ordenTotal.get("TOTAL").toString()));
                    ordenDet.setPrecioUnitario(new BigDecimal(ordenTotal.get("SUBTOTAL").toString()));
                    ordenDet.setIva(new BigDecimal(ordenTotal.get("IVA").toString()));
                    System.out.println("actualizando importes");
                    this.erpOrdenCompraDetDao.actualizaImportes(ordenDet);

                    ordenId.setCompania(compania);
                    ordenId.setId(Integer.valueOf(Integer.parseInt(ss2.idOrdenCompra)));
                    orden.setId(ordenId);
                    orden.setTotalPendiente(new BigDecimal(ordenMTotal.get("TOTAL").toString()));

                    this.erpOrdenCompraDao.actualizaImportes(orden);
                    
                    
//                
//                      Querys queConc = new Querys();
//                    String storeConc = queConc.getSQL("RelacionaTotaXOrden");
//
//
//                          
//                    Map concLinea = new HashMap();
//
//                    concLinea.put("compania", compania);
//                    concLinea.put("orden", ss2.idOrdenCompra);
//                    concLinea.put("linea", ss2.linea);
//                    concLinea.put("origen", ss.origen);
//                    concLinea.put("numeroFe", ss.numeroFe);
//
//                    processDao.execute(storeConc, concLinea);     
        

                    
                    
                    
                    
                    
                    
                    
              }
            }
      }
      
      if(res == 0){
        
        rq.setSuccess(true);
        

        rq.setMsg("Guardados Correctamente");
    
      }else{ 
      
            if (result != null)
            {
              rq.setSuccess(true);


              rq.setMsg("Guardados Correctamente");
            }
            else
            {
              rq.setSuccess(false);
              rq.setData(null);

              rq.setMsg("Error al guardar");
            }
      }
    }
    catch (Exception e)
    {
      e.printStackTrace();
    }
    return rq;
  }
  
  @RequestMapping(value={"/delRel"}, method={org.springframework.web.bind.annotation.RequestMethod.POST})
  @ResponseBody
  public ResponseQuery2 relacionDelAction(String data, WebRequest webRequest, Model model)
    throws ParseException
  {
    ResponseQuery2 rq = new ResponseQuery2();
    SimpleDateFormat formatoDelTexto = new SimpleDateFormat("dd/MM/yyyy");
    int index = data.indexOf("[");
    if (index == -1) {
      data = "[" + data + "]";
    }
    System.out.println("data:" + data);
    if (model.asMap().get("compania") == null)
    {
      rq.setSuccess(false);
      rq.setData(null);
      rq.setMsg("La sesion no es valida.");
      return rq;
    }
    String compania = model.asMap().get("compania").toString();
    String usuario = model.asMap().get("usuario").toString();
    
    System.out.println("-------------------------------------------------------------------");
    try
    {
      int guardado = 0;
      
      ObjectMapper mapper = new ObjectMapper();
      DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
      
      mapper.setDateFormat(df);
      List<RelFactDTO> lista = (List)mapper.readValue(data, mapper
        .getTypeFactory().constructCollectionType(List.class, RelFactDTO.class));
      if (lista.isEmpty())
      {
        rq.setSuccess(false);
        rq.setData(null);
        rq.setMsg("Error no existen datos que guardar");
        return rq;
      }
      ErpOrdenXFactura ordeFact = new ErpOrdenXFactura();
      ErpOrdenXFacturaId ordenFactId = new ErpOrdenXFacturaId();
      
      System.out.println("-------------------------------------------------------------------");
      int val = 0;
      Iterator<RelFactDTO> it = lista.iterator();
      
      boolean result = false;
      while (it.hasNext())
      {
        System.out.println("-------------------------------------------------------------------");
        RelFactDTO ss = (RelFactDTO)it.next();
        
        ordenFactId.setCompania(compania);
        ordenFactId.setIdOrden(Integer.valueOf(Integer.parseInt(ss.idOrden)));
        ordenFactId.setIdOrdenDet(Integer.valueOf(Integer.parseInt(ss.idOrdenDet)));
        ordenFactId.setNumeroFe(Integer.valueOf(Integer.parseInt(ss.numeroFe)));
        ordeFact.setId(ordenFactId);
        ordeFact.setOrigen(ss.origen);
        
        result = this.erpOrdenXFacturaDao.delete(ordeFact);
        

        Map ordenFact = new HashMap();
        ordenFact.put("compania", compania);
        ordenFact.put("idOrden", ss.idOrden);
        ordenFact.put("idOrdenDet", ss.idOrdenDet);
        ordenFact.put("origen", ss.origen);
        

        List listOrdenFact = this.processDao.getMapResult("BuscaImportesOrdenDet", ordenFact);
        
        List listOrdenFactM = this.processDao.getMapResult("BuscaImportesOrden", ordenFact);
        
        Map ordenTotal = (HashMap)listOrdenFact.get(0);
        Map ordenMTotal = (HashMap)listOrdenFactM.get(0);
        
        System.out.println("Obteniendo mapa");
        
        ErpOrdenCompraDet ordenDet = new ErpOrdenCompraDet();
        ErpOrdenCompraDetId ordenDetId = new ErpOrdenCompraDetId();
        
        ErpOrdenCompra orden = new ErpOrdenCompra();
        ErpOrdenCompraId ordenId = new ErpOrdenCompraId();
        

        ordenDetId.setCompania(compania);
        ordenDetId.setIdOrdenCompra(Integer.valueOf(Integer.parseInt(ss.idOrden)));
        ordenDetId.setLinea(Integer.valueOf(Integer.parseInt(ss.idOrdenDet)));
        
        ordenDet.setId(ordenDetId);
        ordenDet.setTotal(new BigDecimal(ordenTotal.get("TOTAL").toString()));
        ordenDet.setPrecioUnitario(new BigDecimal(ordenTotal.get("SUBTOTAL").toString()));
        ordenDet.setIva(new BigDecimal(ordenTotal.get("IVA").toString()));
        System.out.println("actualizando importes");
        this.erpOrdenCompraDetDao.actualizaImportes(ordenDet);
        
        ordenId.setCompania(compania);
        ordenId.setId(Integer.valueOf(Integer.parseInt(ss.idOrden)));
        orden.setId(ordenId);
        orden.setTotalPendiente(new BigDecimal(ordenMTotal.get("TOTAL").toString()));
        
        this.erpOrdenCompraDao.actualizaImportes(orden);
      }
      if (result == true)
      {
        rq.setSuccess(true);
        

        rq.setMsg("Guardados Correctamente");
      }
      else
      {
        rq.setSuccess(false);
        rq.setData(null);
        
        rq.setMsg("Error al guardar");
      }
    }
    catch (Exception e)
    {
      e.printStackTrace();
    }
    return rq;
  }
  
  
  @RequestMapping(value={"/delFact"}, method={org.springframework.web.bind.annotation.RequestMethod.POST})
  @ResponseBody
  public ResponseQuery2 borraFactOrden(String data, WebRequest webRequest, Model model)
    throws ParseException
  {
    ResponseQuery2 rq = new ResponseQuery2();
    SimpleDateFormat formatoDelTexto = new SimpleDateFormat("dd/MM/yyyy");
    int index = data.indexOf("[");
    if (index == -1) {
      data = "[" + data + "]";
    }
    System.out.println("data:" + data);
    if (model.asMap().get("compania") == null)
    {
      rq.setSuccess(false);
      rq.setData(null);
      rq.setMsg("La sesion no es valida.");
      return rq;
    }
    String compania = model.asMap().get("compania").toString();
    String usuario = model.asMap().get("usuario").toString();
    
    System.out.println("-------------------------------------------------------------------");
    try
    {
      int guardado = 0;
      
      ObjectMapper mapper = new ObjectMapper();
      DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
      
      mapper.setDateFormat(df);
      List<RelFactDTO> lista = (List)mapper.readValue(data, mapper
        .getTypeFactory().constructCollectionType(List.class, RelFactDTO.class));
      if (lista.isEmpty())
      {
        rq.setSuccess(false);
        rq.setData(null);
        rq.setMsg("Error no existen datos que guardar");
        return rq;
      }
      ErpOrdenXFactura ordeFact = new ErpOrdenXFactura();
      ErpOrdenXFacturaId ordenFactId = new ErpOrdenXFacturaId();
      
      System.out.println("-------------------------------------------------------------------");
      int val = 0;
      Iterator<RelFactDTO> it = lista.iterator();
      
      boolean result = true;
      while (it.hasNext())
      {
        System.out.println("-------------------------------------------------------------------");
        RelFactDTO ss = (RelFactDTO)it.next();
        
                
        Querys que = new Querys();
        String store = que.getSQL("EliminaFactxCompra");
        
        Map deleteFact = new HashMap();
        
        deleteFact.put("compania", compania);
       // archivoPedidos.put("id", ss.idPedido);
        deleteFact.put("numero", ss.numeroFe);
        deleteFact.put("origen", ss.origen);
        
        
        this.processDao.execute(store, deleteFact);
       
      }
      if (result == true)
      {
        rq.setSuccess(true);
        

        rq.setMsg("Guardados Correctamente");
      }
      else
      {
        rq.setSuccess(false);
        rq.setData(null);
        
        rq.setMsg("Error al guardar");
      }
    }
    catch (Exception e)
    {
      e.printStackTrace();
    }
    return rq;
  }
  
  @RequestMapping(value={"/enviaCXP"}, method={org.springframework.web.bind.annotation.RequestMethod.POST})
  @ResponseBody
  public ResponseQuery2 enviaCXP(String data, WebRequest webRequest, Model model)
    throws ParseException
  {
    ResponseQuery2 rq = new ResponseQuery2();
    SimpleDateFormat formatoDelTexto = new SimpleDateFormat("dd/MM/yyyy");
    int index = data.indexOf("[");
    if (index == -1) {
      data = "[" + data + "]";
    }
    System.out.println("data:" + data);
    if (model.asMap().get("compania") == null)
    {
      rq.setSuccess(false);
      rq.setData(null);
      rq.setMsg("La sesion no es valida.");
      return rq;
    }
    String compania = model.asMap().get("compania").toString();
    String usuario = model.asMap().get("usuario").toString();
    
    System.out.println("-------------------------------------------------------------------");
    try
    {
      int guardado = 0;
      
      ObjectMapper mapper = new ObjectMapper();
      DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
      
      mapper.setDateFormat(df);
      List<RelFactDTO> lista = (List)mapper.readValue(data, mapper
        .getTypeFactory().constructCollectionType(List.class, RelFactDTO.class));
      if (lista.isEmpty())
      {
        rq.setSuccess(false);
        rq.setData(null);
        rq.setMsg("Error no existen datos que guardar");
        return rq;
      }
      ErpOrdenXFactura ordeFact = new ErpOrdenXFactura();
      ErpOrdenXFacturaId ordenFactId = new ErpOrdenXFacturaId();
      
      System.out.println("-------------------------------------------------------------------");
      int val = 0;
      Iterator<RelFactDTO> it = lista.iterator();
      
      boolean result = false;
      while (it.hasNext())
      {
        System.out.println("-------------------------------------------------------------------");
        RelFactDTO ss = (RelFactDTO)it.next();
        
        ordenFactId.setCompania(compania);
        ordenFactId.setIdOrden(Integer.valueOf(Integer.parseInt(ss.idOrden)));
        ordenFactId.setIdOrdenDet(Integer.valueOf(Integer.parseInt(ss.idOrdenDet)));
        ordenFactId.setNumeroFe(Integer.valueOf(Integer.parseInt(ss.numeroFe)));
        ordeFact.setId(ordenFactId);
        if (ss.estatusCXP.equalsIgnoreCase("true")) {
          ordeFact.setEstatusCXP("1");
        }
        result = this.erpOrdenXFacturaDao.actualizaEstatus(ordeFact);
        
        Querys que = new Querys();
        String store = "";
        
        if(ss.origen.equalsIgnoreCase("FE")){
            
         store = que.getSQL("ActualizaCentroCostosFact");
        }else{
            
            store = que.getSQL("ActualizaCentroCostosOTr");
        
        }
        
        Map archivoPedidos = new HashMap();
        
        archivoPedidos.put("compania", compania);
        archivoPedidos.put("numero", ss.numeroFe);
        archivoPedidos.put("orden", ss.idOrden);
        archivoPedidos.put("ordenDet", ss.idOrdenDet);
        
        this.processDao.execute(store, archivoPedidos);
        
      }
      if (result == true)
      {
        rq.setSuccess(true);
        

        rq.setMsg("Guardados Correctamente");
      }
      else
      {
        rq.setSuccess(false);
        rq.setData(null);
        
        rq.setMsg("Error al guardar");
      }
    }
    catch (Exception e)
    {
      e.printStackTrace();
    }
    return rq;
  }
  
  
          
   @RequestMapping(value={"/generaOrdenPedidoAut"}, method={org.springframework.web.bind.annotation.RequestMethod.POST})
  @ResponseBody
  public ResponseQuery2 generaOrdenPedidoAut(String data, @RequestParam("prov") String prov, @RequestParam("idProv") String idProv, @RequestParam("idAlmacen") String idAlmacen, @RequestParam("fechaR") String fechaR, WebRequest webRequest, Model model)
    throws ParseException
  {
    ResponseQuery2 rq = new ResponseQuery2();
    SimpleDateFormat formatoDelTexto = new SimpleDateFormat("dd/MM/yyyy");
    int index = data.indexOf("[");
    if (index == -1) {
      data = "[" + data + "]";
    }
    System.out.println("data:" + data);
    System.out.println("prov:" + prov);
    System.out.println("idProv:" + idProv);
    System.out.println("idAlmacen:" + idAlmacen);
    System.out.println("fechaR:" + fechaR);
    if (model.asMap().get("compania") == null)
    {
      rq.setSuccess(false);
      rq.setData(null);
      rq.setMsg("La sesion no es valida.");
      return rq;
    }
    String compania = model.asMap().get("compania").toString();
    String usuario = model.asMap().get("usuario").toString();
    
    ErpOrdenCompra orden = new ErpOrdenCompra();
    ErpOrdenCompraId ordenId = new ErpOrdenCompraId();
    
    ErpOrdenCompraId result2 = null;
    
    Date fechaOrden = new Date();
    

    System.out.println("-------------------------------------------------------------------");
    try
    {
      int guardado = 0;
      
      ObjectMapper mapper = new ObjectMapper();
      DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
      
      mapper.setDateFormat(df);
      List<PedidosDTO> lista = (List)mapper.readValue(data, mapper
        .getTypeFactory().constructCollectionType(List.class, PedidosDTO.class));
      if (lista.isEmpty())
      {
        rq.setSuccess(false);
        rq.setData(null);
        rq.setMsg("Error no existen datos que guardar");
        return rq;
      }
      ordenId.setCompania(compania);
      
      int id = this.erpOrdenCompraDao.getMaxIdOrden(ordenId);
      System.out.println(id);
      ordenId.setId(Integer.valueOf(id));
      
      orden.setId(ordenId);
      String folio = agregarCeros(String.valueOf(id),5);
                int year = Calendar.getInstance().get(Calendar.YEAR);
                orden.setFolio(String.valueOf(year) + folio);
      orden.setClasificacion("");
      orden.setCondicionesPago("Fecha Venc. " + fechaR);
      orden.setCtoCto("");
      orden.setEstatus("PE");
      orden.setFechaOrden(fechaOrden);
      orden.setFechaRequerida(df.parse(fechaR));
      
      orden.setIdAlmacen(Integer.valueOf(Integer.parseInt(idAlmacen)));
      orden.setIdProveedor(idProv);
      

      orden.setRfc(prov);
      orden.setTotal(new BigDecimal(0));
      orden.setTotalPendiente(new BigDecimal(0));
      orden.setUsuario(usuario);
      orden.setUsuarioAutorizo(usuario);
      orden.setUsuarioComprador("");
      orden.setNombre("Orden " + id);
      orden.setDescripcion("Pedido a proveedor " + prov);
      


      result2 = this.erpOrdenCompraDao.save(orden);
      

      ErpOrdenXFactura ordeFact = new ErpOrdenXFactura();
      ErpOrdenXFacturaId ordenFactId = new ErpOrdenXFacturaId();
      
      System.out.println("-------------------------------------------------------------------");
      int val = 0;
      Iterator<PedidosDTO> it = lista.iterator();
      
      ErpOrdenCompraDet ordenDet = new ErpOrdenCompraDet();
      ErpOrdenCompraDetId ordenDetId = new ErpOrdenCompraDetId();
      
      ErpOrdenXPedidos erpOrdenXPedidos = new ErpOrdenXPedidos();
      ErpOrdenXPedidosId erpOrdenXPedidosId = new ErpOrdenXPedidosId();
      
      boolean result = false;
      while (it.hasNext())
      {
        System.out.println("-------------------------------------------------------------------");
        PedidosDTO ss = (PedidosDTO)it.next();
        
        ordenDetId.setCompania(compania);
        ordenDetId.setIdOrdenCompra(Integer.valueOf(id));
        int id2 = this.erpOrdenCompraDetDao.getMaxIdDetOrden(ordenDetId);
        ordenDetId.setLinea(Integer.valueOf(id2));
        

        String idOr = agregarCeros(String.valueOf(id), 5);
        String idLi = agregarCeros(String.valueOf(id2), 5);
        
        ordenDet.setId(ordenDetId);
        ordenDet.setCantidadLlego(new BigDecimal(0));
        ordenDet.setCantidadPedida(new BigDecimal(ss.cantidad));
        if ((ss.descripcion == null) || (ss.descripcion == "")) {
          ordenDet.setDescripcion(ss.nombreProduct);
        } else {
          ordenDet.setDescripcion(ss.nombreProduct + " (" + ss.descripcion + ")");
        }
        ordenDet.setIdAlmacen(Integer.valueOf(Integer.parseInt(idAlmacen)));
        ordenDet.setIdDet(idOr + idLi);
        ordenDet.setIdEstatus(Integer.valueOf(1));
        
        ordenDet.setIdProducto(ss.idProducto);
        ordenDet.setImporteCotizacion(new BigDecimal(0));
        ordenDet.setIva(new BigDecimal(0));
        ordenDet.setcCostos(ss.ctoCto);
        
        Map ordenProdu = new HashMap();
        ordenProdu.put("compania", compania);
        ordenProdu.put("idProducto", ss.idProducto);
        
        List listCotProdc = this.processDao.getMapResult("BuscaCotizacionProducto", ordenProdu);
        if (listCotProdc != null)
        {
          Map producTotal = (HashMap)listCotProdc.get(0);
          
          System.out.println("Obteniendo mapa");
          MathContext mc = new MathContext(4);
          
          ordenDet.setImporteCotizacion(new BigDecimal(producTotal.get("IMPORTE_COTIZACION").toString()).multiply(new BigDecimal(ss.cantidad), mc));
          ordenDet.setImporteCotizacionSub(new BigDecimal(producTotal.get("IMPORTE_COTIZACION").toString()).multiply(new BigDecimal(ss.cantidad), mc));
        }
        else
        {
          ordenDet.setImporteCotizacion(new BigDecimal(0));
          ordenDet.setImporteCotizacionSub(new BigDecimal(0));
        }
        
        //=SELECT ID_FAMILIA,,ID_GERENCIA
        
        List listServicio = this.processDao.getMapResult("BuscaDetalleServicioProd", ordenProdu);
        if (listServicio != null)
        {
          Map service = (HashMap)listServicio.get(0);
          
        
          if(service.get("ID_FAMILIA") != null){
            if(!service.get("ID_FAMILIA").toString().equalsIgnoreCase("")){
                       ordenDet.setIdFamilia(Integer.parseInt(service.get("ID_FAMILIA").toString()));
                    }
         }
         
         if(service.get("SEC") != null){
            if(!service.get("SEC").toString().equalsIgnoreCase("")){
                       ordenDet.setIdDetFamilia(Integer.parseInt(service.get("SEC").toString()));
                    }
         }
         
         if(service.get("ID_GERENCIA") != null){
            if(!service.get("ID_GERENCIA").toString().equalsIgnoreCase("")){
                       ordenDet.setIdGerencia(Integer.parseInt(service.get("ID_GERENCIA").toString()));
                    }
         }
          
        }
      
        
        ordenDet.setImporteCotizacionIva(new BigDecimal(0));
        ordenDet.setDescuento(new BigDecimal(0));
        ordenDet.setDescuentoProv(new BigDecimal(0));
        

        ordenDet.setPrecioUnitario(new BigDecimal(0));
        ordenDet.setTotal(new BigDecimal(0));
        ordenDet.setEstatusLinea("1");
        
        ordenDet.setIdProyecto(ss.idProyecto);
        


        this.erpOrdenCompraDetDao.save(ordenDet);
        

        erpOrdenXPedidosId.setCompania(compania);
        erpOrdenXPedidosId.setIdPedido(Integer.valueOf(Integer.parseInt(ss.id)));
        erpOrdenXPedidosId.setLinea(Integer.valueOf(id2));
        erpOrdenXPedidosId.setOrdenCompra(Integer.valueOf(id));
        erpOrdenXPedidos.setId(erpOrdenXPedidosId);
        erpOrdenXPedidos.setUsuario(usuario);
        this.erpOrdenXPedidosDao.save(erpOrdenXPedidos);
        

        Querys que = new Querys();
        String store = que.getSQL("ActualizaPedidos");
        
        Map archivoPedidos = new HashMap();
        
        archivoPedidos.put("compania", compania);
        archivoPedidos.put("id", ss.id);
        archivoPedidos.put("estatus", "O");
        
        this.processDao.execute(store, archivoPedidos);
      }
      if (result2 != null)
      {
        rq.setSuccess(true);
        

        rq.setMsg("Orden Generada con Folio: " + String.valueOf(id));
      }
      else
      {
        rq.setSuccess(false);
        rq.setData(null);
        
        rq.setMsg("Error al guardar");
      }
    }
    catch (Exception e)
    {
      e.printStackTrace();
    }
    return rq;
  }        
  
  @RequestMapping(value={"/generaOrdenPedido"}, method={org.springframework.web.bind.annotation.RequestMethod.POST})
  @ResponseBody
  public ResponseQuery2 generaOrdenPedido(String data, @RequestParam("prov") String prov, @RequestParam("idProv") String idProv, @RequestParam("idAlmacen") String idAlmacen, @RequestParam("fechaR") String fechaR, @RequestParam("cCostos") String cCostos, WebRequest webRequest, Model model)
    throws ParseException
  {
    ResponseQuery2 rq = new ResponseQuery2();
    SimpleDateFormat formatoDelTexto = new SimpleDateFormat("dd/MM/yyyy");
    int index = data.indexOf("[");
    if (index == -1) {
      data = "[" + data + "]";
    }
    System.out.println("data:" + data);
    System.out.println("prov:" + prov);
    System.out.println("idProv:" + idProv);
    System.out.println("idAlmacen:" + idAlmacen);
    System.out.println("fechaR:" + fechaR);
    if (model.asMap().get("compania") == null)
    {
      rq.setSuccess(false);
      rq.setData(null);
      rq.setMsg("La sesion no es valida.");
      return rq;
    }
    String compania = model.asMap().get("compania").toString();
    String usuario = model.asMap().get("usuario").toString();
    
    ErpOrdenCompra orden = new ErpOrdenCompra();
    ErpOrdenCompraId ordenId = new ErpOrdenCompraId();
    
    ErpOrdenCompraId result2 = null;
    
    Date fechaOrden = new Date();
    

    System.out.println("-------------------------------------------------------------------");
    try
    {
      int guardado = 0;
      
      ObjectMapper mapper = new ObjectMapper();
      DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
      
      mapper.setDateFormat(df);
      List<PedidosDTO> lista = (List)mapper.readValue(data, mapper
        .getTypeFactory().constructCollectionType(List.class, PedidosDTO.class));
      if (lista.isEmpty())
      {
        rq.setSuccess(false);
        rq.setData(null);
        rq.setMsg("Error no existen datos que guardar");
        return rq;
      }
      ordenId.setCompania(compania);
      
      int id = this.erpOrdenCompraDao.getMaxIdOrden(ordenId);
      System.out.println(id);
      ordenId.setId(Integer.valueOf(id));
      
      orden.setId(ordenId);
      String folio = agregarCeros(String.valueOf(id),5);
                int year = Calendar.getInstance().get(Calendar.YEAR);
                orden.setFolio(String.valueOf(year) + folio);
      orden.setClasificacion("");
      orden.setCondicionesPago("Fecha Venc. " + fechaR);
      orden.setCtoCto(cCostos);
      orden.setEstatus("PE");
      orden.setFechaOrden(fechaOrden);
      orden.setFechaRequerida(df.parse(fechaR));
      
      orden.setIdAlmacen(Integer.valueOf(Integer.parseInt(idAlmacen)));
      orden.setIdProveedor(idProv);
      

      orden.setRfc(prov);
      orden.setTotal(new BigDecimal(0));
      orden.setTotalPendiente(new BigDecimal(0));
      orden.setUsuario(usuario);
      orden.setUsuarioAutorizo(usuario);
      orden.setUsuarioComprador("");
      orden.setNombre("Orden " + id);
      orden.setDescripcion("Pedido a proveedor " + prov);
      
      
      


      result2 = this.erpOrdenCompraDao.save(orden);
      

      ErpOrdenXFactura ordeFact = new ErpOrdenXFactura();
      ErpOrdenXFacturaId ordenFactId = new ErpOrdenXFacturaId();
      
      System.out.println("-------------------------------------------------------------------");
      int val = 0;
      Iterator<PedidosDTO> it = lista.iterator();
      
      ErpOrdenCompraDet ordenDet = new ErpOrdenCompraDet();
      ErpOrdenCompraDetId ordenDetId = new ErpOrdenCompraDetId();
      
      ErpOrdenXPedidos erpOrdenXPedidos = new ErpOrdenXPedidos();
      ErpOrdenXPedidosId erpOrdenXPedidosId = new ErpOrdenXPedidosId();
      
      boolean result = false;
      while (it.hasNext())
      {
        System.out.println("-------------------------------------------------------------------");
        PedidosDTO ss = (PedidosDTO)it.next();
        
        ordenDetId.setCompania(compania);
        ordenDetId.setIdOrdenCompra(Integer.valueOf(id));
        int id2 = this.erpOrdenCompraDetDao.getMaxIdDetOrden(ordenDetId);
        ordenDetId.setLinea(Integer.valueOf(id2));
        

        String idOr = agregarCeros(String.valueOf(id), 5);
        String idLi = agregarCeros(String.valueOf(id2), 5);
        
        ordenDet.setId(ordenDetId);
        ordenDet.setCantidadLlego(new BigDecimal(0));
        ordenDet.setCantidadPedida(new BigDecimal(ss.cantidad));
        if ((ss.descripcion == null) || (ss.descripcion == "")) {
          ordenDet.setDescripcion(ss.nombreProduct);
        } else {
          ordenDet.setDescripcion(ss.nombreProduct + " (" + ss.descripcion + ")");
        }
        ordenDet.setIdAlmacen(Integer.valueOf(Integer.parseInt(idAlmacen)));
        ordenDet.setIdDet(idOr + idLi);
        ordenDet.setIdEstatus(Integer.valueOf(1));
        
        ordenDet.setIdProducto(ss.idProducto);
        ordenDet.setImporteCotizacion(new BigDecimal(0));
        ordenDet.setIva(new BigDecimal(0));
        
        Map ordenProdu = new HashMap();
        ordenProdu.put("compania", compania);
        ordenProdu.put("idProducto", ss.idProducto);
        ordenProdu.put("usuario", ss.usuario);
        
        List listCotProdc = this.processDao.getMapResult("BuscaCotizacionProducto", ordenProdu);
        if (listCotProdc != null)
        {
          Map producTotal = (HashMap)listCotProdc.get(0);
          
          System.out.println("Obteniendo mapa");
          MathContext mc = new MathContext(4);
          
          ordenDet.setImporteCotizacion(new BigDecimal(producTotal.get("IMPORTE_COTIZACION").toString()).multiply(new BigDecimal(ss.cantidad), mc));
          ordenDet.setImporteCotizacionSub(new BigDecimal(producTotal.get("IMPORTE_COTIZACION").toString()).multiply(new BigDecimal(ss.cantidad), mc));
        }
        else
        {
          ordenDet.setImporteCotizacion(new BigDecimal(0));
          ordenDet.setImporteCotizacionSub(new BigDecimal(0));
        }
        
        List listServicio = this.processDao.getMapResult("BuscaAreaXUsuario", ordenProdu);
        if (!listServicio.isEmpty())
        {
          Map service = (HashMap)listServicio.get(0);
//          
//        
//          if(service.get("ID_FAMILIA") != null){
//            if(!service.get("ID_FAMILIA").toString().equalsIgnoreCase("")){
//                       ordenDet.setIdFamilia(Integer.parseInt(service.get("ID_FAMILIA").toString()));
//                    }
//         }
//         
//         if(service.get("SEC") != null){
//            if(!service.get("SEC").toString().equalsIgnoreCase("")){
//                       ordenDet.setIdDetFamilia(Integer.parseInt(service.get("SEC").toString()));
//                    }
//         }
         
         if(service.get("ID_AREA") != null){
            if(!service.get("ID_AREA").toString().equalsIgnoreCase("")){
                       ordenDet.setIdGerencia(Integer.parseInt(service.get("ID_AREA").toString()));
                    }
         }
          
        }
              
        
        ordenDet.setImporteCotizacionIva(new BigDecimal(0));
        ordenDet.setDescuento(new BigDecimal(0));
        ordenDet.setDescuentoProv(new BigDecimal(0));
        

        ordenDet.setPrecioUnitario(new BigDecimal(0));
        ordenDet.setTotal(new BigDecimal(0));
        ordenDet.setEstatusLinea("1");
        
        ordenDet.setIdProyecto(ss.idProyecto);
        


        this.erpOrdenCompraDetDao.save(ordenDet);
        

        erpOrdenXPedidosId.setCompania(compania);
        erpOrdenXPedidosId.setIdPedido(Integer.valueOf(Integer.parseInt(ss.id)));
        erpOrdenXPedidosId.setLinea(Integer.valueOf(id2));
        erpOrdenXPedidosId.setOrdenCompra(Integer.valueOf(id));
        erpOrdenXPedidos.setId(erpOrdenXPedidosId);
        erpOrdenXPedidos.setUsuario(usuario);
        this.erpOrdenXPedidosDao.save(erpOrdenXPedidos);
        

        Querys que = new Querys();
        String store = que.getSQL("ActualizaPedidos");
        
        Map archivoPedidos = new HashMap();
        
        archivoPedidos.put("compania", compania);
        archivoPedidos.put("id", ss.id);
        archivoPedidos.put("estatus", "O");
        
        this.processDao.execute(store, archivoPedidos);
      }
      if (result2 != null)
      {
        rq.setSuccess(true);
        

        rq.setMsg("Orden Generada con Folio: " + String.valueOf(id));
      }
      else
      {
        rq.setSuccess(false);
        rq.setData(null);
        
        rq.setMsg("Error al guardar");
      }
    }
    catch (Exception e)
    {
      e.printStackTrace();
    }
    return rq;
  }
 
   @RequestMapping(value={"/generaOrdenPedidoServ"}, method={org.springframework.web.bind.annotation.RequestMethod.POST})
  @ResponseBody
  public ResponseQuery2 generaOrdenPedidoServ(String data, @RequestParam("prov") String prov, @RequestParam("idProv") String idProv, @RequestParam("idAlmacen") String idAlmacen, @RequestParam("fechaR") String fechaR, 
          @RequestParam("cCostos") String cCostos,
          @RequestParam("tipo") String tipo,
          @RequestParam("servicio") String servicio,
          @RequestParam("proveedorServ") String proveedorServ,WebRequest webRequest, Model model)
          

          
    throws ParseException
  {
    ResponseQuery2 rq = new ResponseQuery2();
    SimpleDateFormat formatoDelTexto = new SimpleDateFormat("dd/MM/yyyy");
    int index = data.indexOf("[");
    if (index == -1) {
      data = "[" + data + "]";
    }
    System.out.println("data:" + data);
    System.out.println("prov:" + prov);
    System.out.println("idProv:" + idProv);
    System.out.println("idAlmacen:" + idAlmacen);
    System.out.println("fechaR:" + fechaR);
    System.out.println("tipo: "+tipo);
    System.out.println("servicio: "+servicio);
    
    if (model.asMap().get("compania") == null)
    {
      rq.setSuccess(false);
      rq.setData(null);
      rq.setMsg("La sesion no es valida.");
      return rq;
    }
    
    
    String compania = model.asMap().get("compania").toString();
    String usuario = model.asMap().get("usuario").toString();
    
    ErpOrdenCompra orden = new ErpOrdenCompra();
    ErpOrdenCompraId ordenId = new ErpOrdenCompraId();
    
    ErpOrdenCompraId result2 = null;
    
    Date fechaOrden = new Date();
    

    System.out.println("-------------------------------------------------------------------");
    try
    {
      int guardado = 0;
      
      ObjectMapper mapper = new ObjectMapper();
      DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
      
      mapper.setDateFormat(df);
      List<PedidosDTO> lista = (List)mapper.readValue(data, mapper
        .getTypeFactory().constructCollectionType(List.class, PedidosDTO.class));
      if (lista.isEmpty())
      {
        rq.setSuccess(false);
        rq.setData(null);
        rq.setMsg("Error no existen datos que guardar");
        return rq;
      }
      ordenId.setCompania(compania);
      
      int id = this.erpOrdenCompraDao.getMaxIdOrden(ordenId);
      System.out.println(id);
      ordenId.setId(Integer.valueOf(id));
      
      orden.setId(ordenId);
      String folio = agregarCeros(String.valueOf(id),5);
                int year = Calendar.getInstance().get(Calendar.YEAR);
                orden.setFolio(String.valueOf(year) + folio);
      orden.setClasificacion("");
      orden.setCondicionesPago("Fecha Venc. " + fechaR);
      orden.setCtoCto(cCostos);
      orden.setEstatus("PE");
      orden.setFechaOrden(fechaOrden);
      orden.setFechaRequerida(df.parse(fechaR));
      
      orden.setIdAlmacen(Integer.valueOf(Integer.parseInt(idAlmacen)));
      orden.setIdProveedor(idProv);
      
      if (tipo.equalsIgnoreCase("S")){
         //orden.setRfc(proveedorServ);
         orden.setTipo("S");
      }else{
          
          orden.setTipo("O");
      }
      
      orden.setRfc(prov);
      
      orden.setTotal(new BigDecimal(0));
      orden.setTotalPendiente(new BigDecimal(0));
      orden.setUsuario(usuario);
      orden.setUsuarioAutorizo(usuario);
      orden.setUsuarioComprador("");
      orden.setNombre("Orden " + id);
      orden.setDescripcion("Pedido a proveedor " + prov);
      
      
      


      result2 = this.erpOrdenCompraDao.save(orden);
      

      ErpOrdenXFactura ordeFact = new ErpOrdenXFactura();
      ErpOrdenXFacturaId ordenFactId = new ErpOrdenXFacturaId();
      
      System.out.println("-------------------------------------------------------------------");
      int val = 0;
      Iterator<PedidosDTO> it = lista.iterator();
      
      ErpOrdenCompraDet ordenDet = new ErpOrdenCompraDet();
      ErpOrdenCompraDetId ordenDetId = new ErpOrdenCompraDetId();
      
      ErpOrdenXPedidos erpOrdenXPedidos = new ErpOrdenXPedidos();
      ErpOrdenXPedidosId erpOrdenXPedidosId = new ErpOrdenXPedidosId();
      
      boolean result = false;
      while (it.hasNext())
      {
        System.out.println("-------------------------------------------------------------------");
        PedidosDTO ss = (PedidosDTO)it.next();
        
        ordenDetId.setCompania(compania);
        ordenDetId.setIdOrdenCompra(Integer.valueOf(id));
        int id2 = this.erpOrdenCompraDetDao.getMaxIdDetOrden(ordenDetId);
        ordenDetId.setLinea(Integer.valueOf(id2));
        

        String idOr = agregarCeros(String.valueOf(id), 5);
        String idLi = agregarCeros(String.valueOf(id2), 5);
        
        ordenDet.setId(ordenDetId);
        ordenDet.setCantidadLlego(new BigDecimal(0));
        ordenDet.setCantidadPedida(new BigDecimal(ss.cantidad));
        
        ordenDet.setIdAlmacen(Integer.valueOf(Integer.parseInt(idAlmacen)));
        ordenDet.setIdDet(idOr + idLi);
        ordenDet.setIdEstatus(Integer.valueOf(1));
       
            ordenDet.setIdProducto(ss.idProducto);
            if ((ss.descripcion == null) || (ss.descripcion == "")) {
                ordenDet.setDescripcion(ss.nombreProduct);
              } else {
                ordenDet.setDescripcion(ss.nombreProduct + " (" + ss.descripcion + ")");
              }
        
        if(ss.idFamilia != null){    
         if(!ss.idFamilia.equalsIgnoreCase("")){
                 ordenDet.setIdFamilia(Integer.parseInt(ss.idFamilia));
            }        
        }
        
        ordenDet.setImporteCotizacion(new BigDecimal(0));
        ordenDet.setIva(new BigDecimal(0));
        
        Map ordenProdu = new HashMap();
        ordenProdu.put("compania", compania);
        ordenProdu.put("idProducto", ss.idProducto);
        ordenProdu.put("usuario", ss.usuario);
        
//        List listCotProdc = this.processDao.getMapResult("BuscaCotizacionProducto", ordenProdu);
//        if (listCotProdc != null)
//        {
//          Map producTotal = (HashMap)listCotProdc.get(0);
//          
//          System.out.println("Obteniendo mapa");
//          MathContext mc = new MathContext(4);
//          
//          ordenDet.setImporteCotizacion(new BigDecimal(producTotal.get("IMPORTE_COTIZACION").toString()).multiply(new BigDecimal(ss.cantidad), mc));
//          ordenDet.setImporteCotizacionSub(new BigDecimal(producTotal.get("IMPORTE_COTIZACION").toString()).multiply(new BigDecimal(ss.cantidad), mc));
//        }
//        else
//        {
          ordenDet.setImporteCotizacion(new BigDecimal(ss.costoUnitario).multiply(new BigDecimal(ss.cantidad)));
          ordenDet.setImporteCotizacionSub(new BigDecimal(ss.costoUnitario));
       // }
        
        
                
          
        //List listServicio = this.processDao.getMapResult("BuscaAreaXUsuario", ordenProdu);
       // if (!listServicio.isEmpty())
       // {
       //   Map service = (HashMap)listServicio.get(0);
//          
//        
//          if(service.get("ID_FAMILIA") != null){
//            if(!service.get("ID_FAMILIA").toString().equalsIgnoreCase("")){
//                       ordenDet.setIdFamilia(Integer.parseInt(service.get("ID_FAMILIA").toString()));
//                    }
//         }
//         
//         if(service.get("SEC") != null){
//            if(!service.get("SEC").toString().equalsIgnoreCase("")){
//                       ordenDet.setIdDetFamilia(Integer.parseInt(service.get("SEC").toString()));
//                    }
//         }
         if(ss.idArea != null){
            if(!ss.idArea.equalsIgnoreCase("")){
                       ordenDet.setIdGerencia(Integer.parseInt(ss.idArea));
                    }
         }
         
          
        //}
              
        
        ordenDet.setImporteCotizacionIva(new BigDecimal(0));
        ordenDet.setDescuento(new BigDecimal(0));
        ordenDet.setDescuentoProv(new BigDecimal(0));
        

        ordenDet.setPrecioUnitario(new BigDecimal(0));
        ordenDet.setTotal(new BigDecimal(0));
        ordenDet.setEstatusLinea("1");
        
        ordenDet.setIdProyecto(ss.idProyecto);
        ordenDet.setIdMoneda(ss.moneda);
        


        this.erpOrdenCompraDetDao.save(ordenDet);
        

        erpOrdenXPedidosId.setCompania(compania);
        erpOrdenXPedidosId.setIdPedido(Integer.valueOf(Integer.parseInt(ss.id)));
        erpOrdenXPedidosId.setLinea(Integer.valueOf(id2));
        erpOrdenXPedidosId.setOrdenCompra(Integer.valueOf(id));
        erpOrdenXPedidos.setId(erpOrdenXPedidosId);
        erpOrdenXPedidos.setUsuario(usuario);
        this.erpOrdenXPedidosDao.save(erpOrdenXPedidos);
        

        Querys que = new Querys();
        String store = que.getSQL("ActualizaPedidos");
        
        Map archivoPedidos = new HashMap();
        
        archivoPedidos.put("compania", compania);
        archivoPedidos.put("id", ss.id);
        archivoPedidos.put("estatus", "O");
        
        this.processDao.execute(store, archivoPedidos);
      }
      if (result2 != null)
      {
        rq.setSuccess(true);
        

        rq.setMsg("Orden Generada con Folio: " + String.valueOf(id));
      }
      else
      {
        rq.setSuccess(false);
        rq.setData(null);
        
        rq.setMsg("Error al guardar");
      }
    }
    catch (Exception e)
    {
      e.printStackTrace();
    }
    return rq;
  }
 
  
    @RequestMapping(value={"/generaOrdenPedidoDet"}, method={org.springframework.web.bind.annotation.RequestMethod.POST})
  @ResponseBody
  public ResponseQuery2 generaOrdenPedidoDet(String data, @RequestParam("tipo") String tipo,@RequestParam("prov") String prov, @RequestParam("idProv") String idProv, @RequestParam("idAlmacen") String idAlmacen, @RequestParam("fechaR") String fechaR, @RequestParam("cCostos") String cCostos,@RequestParam("condiciones") String condiciones,@RequestParam("condicionesNom") String condicionesNom, WebRequest webRequest, Model model)
    throws ParseException
  {
    ResponseQuery2 rq = new ResponseQuery2();
    SimpleDateFormat formatoDelTexto = new SimpleDateFormat("dd/MM/yyyy");
    int index = data.indexOf("[");
    if (index == -1) {
      data = "[" + data + "]";
    }
    System.out.println("data:" + data);
    System.out.println("tipo:" + tipo);
    System.out.println("prov:" + prov);
    System.out.println("idProv:" + idProv);
    System.out.println("idAlmacen:" + idAlmacen);
    System.out.println("fechaR:" + fechaR);
    if (model.asMap().get("compania") == null)
    {
      rq.setSuccess(false);
      rq.setData(null);
      rq.setMsg("La sesion no es valida.");
      return rq;
    }
    String compania = model.asMap().get("compania").toString();
    String usuario = model.asMap().get("usuario").toString();
    
    ErpOrdenCompra orden = new ErpOrdenCompra();
    ErpOrdenCompraId ordenId = new ErpOrdenCompraId();
    
    ErpOrdenCompraId result2 = null;
    
    Date fechaOrden = new Date();
    

    System.out.println("-------------------------------------------------------------------");
    try
    {
      int guardado = 0;
      
      ObjectMapper mapper = new ObjectMapper();
      DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
      
      mapper.setDateFormat(df);
      List<PedidosDetDTO> lista = (List)mapper.readValue(data, mapper
        .getTypeFactory().constructCollectionType(List.class, PedidosDetDTO.class));
      if (lista.isEmpty())
      {
        rq.setSuccess(false);
        rq.setData(null);
        rq.setMsg("Error no existen datos que guardar");
        return rq;
      }
      ordenId.setCompania(compania);
      
      int id = this.erpOrdenCompraDao.getMaxIdOrden(ordenId);
      System.out.println(id);
      ordenId.setId(Integer.valueOf(id));
      
      orden.setId(ordenId);
      String folio = agregarCeros(String.valueOf(id),5);
                int year = Calendar.getInstance().get(Calendar.YEAR);
                orden.setFolio(String.valueOf(year) + folio);
      orden.setClasificacion("");
      if(condicionesNom.equalsIgnoreCase("")){
        orden.setCondicionesPago("Fecha Venc. " + fechaR);
      }else{
          orden.setCondicionesPago(condicionesNom);
      
      }
      orden.setIdCondiciones(condiciones);
      orden.setCtoCto(cCostos);
      orden.setEstatus("PE");
      orden.setFechaOrden(fechaOrden);
      orden.setFechaRequerida(df.parse(fechaR));
      
      orden.setIdAlmacen(Integer.valueOf(Integer.parseInt(idAlmacen)));
      orden.setIdProveedor(idProv);
      

      orden.setRfc(prov);
      orden.setTotal(new BigDecimal(0));
      orden.setTotalPendiente(new BigDecimal(0));
      orden.setUsuario(usuario);
      orden.setUsuarioAutorizo(usuario);
      orden.setUsuarioComprador("");
      orden.setNombre("Orden " + id);
      orden.setDescripcion("Pedido a proveedor " + prov);
      if (tipo.equalsIgnoreCase("S")){
         //orden.setRfc(proveedorServ);
         orden.setTipo("S");
      }else{
          
          orden.setTipo("O");
      }


      result2 = this.erpOrdenCompraDao.save(orden);
      

      ErpOrdenXFactura ordeFact = new ErpOrdenXFactura();
      ErpOrdenXFacturaId ordenFactId = new ErpOrdenXFacturaId();
      
      System.out.println("-------------------------------------------------------------------");
      int val = 0;
      Iterator<PedidosDetDTO> it = lista.iterator();
      
      ErpOrdenCompraDet ordenDet = new ErpOrdenCompraDet();
      ErpOrdenCompraDetId ordenDetId = new ErpOrdenCompraDetId();
      
      ErpOrdenXPedidos erpOrdenXPedidos = new ErpOrdenXPedidos();
      ErpOrdenXPedidosId erpOrdenXPedidosId = new ErpOrdenXPedidosId();
      
      boolean result = false;
      while (it.hasNext())
      {
        System.out.println("-------------------------------------------------------------------");
        PedidosDetDTO ss = (PedidosDetDTO)it.next();
        
        ordenDetId.setCompania(compania);
        ordenDetId.setIdOrdenCompra(Integer.valueOf(id));
        int id2 = this.erpOrdenCompraDetDao.getMaxIdDetOrden(ordenDetId);
        ordenDetId.setLinea(Integer.valueOf(id2));
        

        String idOr = agregarCeros(String.valueOf(id), 5);
        String idLi = agregarCeros(String.valueOf(id2), 5);
        
        ordenDet.setId(ordenDetId);
        ordenDet.setIva(new BigDecimal(0));
        ordenDet.setCantidadLlego(new BigDecimal(0));
        ordenDet.setCantidadPedida(new BigDecimal(ss.cantidad));
        
          System.out.println("ss.fechaEntrega: "+ ss.fechaEntrega);
        
        if ((ss.fechaEntrega != null) || (ss.fechaEntrega != "")) {
            
            System.out.println("agregar fecchae entrega");
          ordenDet.setFechaEntrega(df.parse(ss.fechaEntrega));
        } 
        
        System.out.println("ss.fechaEntrega agregada: "+ordenDet.getFechaEntrega() );
        
        if ((ss.descripcion == null) || (ss.descripcion == "")) {
          ordenDet.setDescripcion(ss.nombreProduct);
        } else {
          ordenDet.setDescripcion(ss.nombreProduct + " (" + ss.descripcion + ")");
        }
        ordenDet.setIdAlmacen(Integer.valueOf(Integer.parseInt(idAlmacen)));
        ordenDet.setIdDet(idOr + idLi);
        ordenDet.setIdEstatus(Integer.valueOf(1));
        
        ordenDet.setIdProducto(ss.idProducto);
        ordenDet.setImporteCotizacion(new BigDecimal(0));
        
        ordenDet.setTipoGasto(ss.tipoGasto);
        ordenDet.settGDescripcion(ss.tGDescripcion);
        ordenDet.setGrupoGasto(ss.grupoGasto);
        
        
        if(ss.departamento != null){    
         if(!ss.departamento.equalsIgnoreCase("")){
                 ordenDet.setDepartamento(Integer.parseInt(ss.departamento));
            }        
        }
        
        ordenDet.setAvionCC(ss.avionCC);
        ordenDet.setEstacion(ss.estacion);
        
        
        if(ss.tipoProduccion != null){
        
            if(ss.tipoProduccion != ""){
                  ordenDet.setTipoProduccion(Integer.parseInt(ss.tipoProduccion));
            }
        
        }
        
        if(ss.idFamilia != null){    
         if(!ss.idFamilia.equalsIgnoreCase("")){
                 ordenDet.setIdFamilia(Integer.parseInt(ss.idFamilia));
            }        
        }
		
		 if(ss.idArea != null){
            if(!ss.idArea.equalsIgnoreCase("")){
                       ordenDet.setIdGerencia(Integer.parseInt(ss.idArea));
                    }
         }
        
        if(ss.montoTotal == null){
            
             Map ordenProdu = new HashMap();
                ordenProdu.put("compania", compania);
                ordenProdu.put("idProducto", ss.idProducto);
                ordenDet.setTasaIva("16");
                List listCotProdc = this.processDao.getMapResult("BuscaCotizacionProducto", ordenProdu);
                if (listCotProdc != null)
                {
                  Map producTotal = (HashMap)listCotProdc.get(0);

                  System.out.println("Obteniendo mapa");
                  MathContext mc = new MathContext(4);

                  BigDecimal ivaC  = (new BigDecimal(producTotal.get("IMPORTE_COTIZACION").toString())).multiply(new BigDecimal(0.16), mc);
                  BigDecimal ivaCant  = (new BigDecimal(producTotal.get("IMPORTE_COTIZACION").toString()).multiply(new BigDecimal(ss.cantidad), mc)).multiply(new BigDecimal(0.16), mc);

                  BigDecimal subC = new BigDecimal(producTotal.get("IMPORTE_COTIZACION").toString());
                  BigDecimal subCant = new BigDecimal(producTotal.get("IMPORTE_COTIZACION").toString()).multiply(new BigDecimal(ss.cantidad), mc);

                  BigDecimal totC = subCant.add(ivaCant);

                    System.out.println(ivaC);
                    System.out.println(subC);
                    System.out.println(totC);

                  ordenDet.setImporteCotizacion(totC);
                  ordenDet.setImporteCotizacionSub(subC);
                  ordenDet.setImporteCotizacionIva(ivaC);

                  //ordenDet.setIva(ivaC);

                }
                else
                {
                  ordenDet.setImporteCotizacion(new BigDecimal(0));
                  ordenDet.setImporteCotizacionSub(new BigDecimal(0));
                  ordenDet.setImporteCotizacionIva(new BigDecimal(0));

                  //ordenDet.setIva(new BigDecimal(0));
                }

        
            
            
        }else{
        
            if(ss.montoTotal.equalsIgnoreCase("0")){
                Map ordenProdu = new HashMap();
                ordenProdu.put("compania", compania);
                ordenProdu.put("idProducto", ss.idProducto);
                ordenDet.setTasaIva("16");
                List listCotProdc = this.processDao.getMapResult("BuscaCotizacionProducto", ordenProdu);
                if (listCotProdc != null)
                {
                  Map producTotal = (HashMap)listCotProdc.get(0);

                  System.out.println("Obteniendo mapa");
                  MathContext mc = new MathContext(4);

                  BigDecimal ivaC  = (new BigDecimal(producTotal.get("IMPORTE_COTIZACION").toString())).multiply(new BigDecimal(0.16), mc);
                  BigDecimal ivaCant  = (new BigDecimal(producTotal.get("IMPORTE_COTIZACION").toString()).multiply(new BigDecimal(ss.cantidad), mc)).multiply(new BigDecimal(0.16), mc);

                  BigDecimal subC = new BigDecimal(producTotal.get("IMPORTE_COTIZACION").toString());
                  BigDecimal subCant = new BigDecimal(producTotal.get("IMPORTE_COTIZACION").toString()).multiply(new BigDecimal(ss.cantidad), mc);

                  BigDecimal totC = subCant.add(ivaCant);

                    System.out.println(ivaC);
                    System.out.println(subC);
                    System.out.println(totC);

                  ordenDet.setImporteCotizacion(totC);
                  ordenDet.setImporteCotizacionSub(subC);
                  ordenDet.setImporteCotizacionIva(ivaC);

                  //ordenDet.setIva(ivaC);

                }
                else
                {
                  ordenDet.setImporteCotizacion(new BigDecimal(0));
                  ordenDet.setImporteCotizacionSub(new BigDecimal(0));
                  ordenDet.setImporteCotizacionIva(new BigDecimal(0));

                  //ordenDet.setIva(new BigDecimal(0));
                }

            }else{
                    ordenDet.setTasaIva("0");

                    ordenDet.setImporteCotizacion(new BigDecimal(ss.montoTotal));
                    ordenDet.setImporteCotizacionSub(new BigDecimal(ss.costoUnitario));
                    ordenDet.setImporteCotizacionIva(new BigDecimal(0));


            }

        }
        ordenDet.setDescuento(new BigDecimal(0));
        ordenDet.setDescuentoProv(new BigDecimal(0));
        

        ordenDet.setPrecioUnitario(new BigDecimal(0));
        ordenDet.setTotal(new BigDecimal(0));
        ordenDet.setEstatusLinea("1");
        
        ordenDet.setIdMoneda(ss.moneda);
        if(ss.moneda != null){
            ordenDet.setIdMoneda(ss.moneda);
        }else{
            
            if(ss.tipoPedido.equalsIgnoreCase("1")){
            
                ordenDet.setIdMoneda("MXN");

            }else{

                ordenDet.setIdMoneda("USD");
            }
        
        }
        
        
        
        
        
        
        ordenDet.setIdProyecto(ss.idProyecto);
        ordenDet.setRetencion(3);
        


        this.erpOrdenCompraDetDao.save(ordenDet);
        

        erpOrdenXPedidosId.setCompania(compania);
        erpOrdenXPedidosId.setIdPedido(Integer.valueOf(Integer.parseInt(ss.id)));
        erpOrdenXPedidosId.setLinea(Integer.valueOf(id2));
        erpOrdenXPedidosId.setOrdenCompra(Integer.valueOf(id));
        erpOrdenXPedidos.setId(erpOrdenXPedidosId);
        erpOrdenXPedidos.setUsuario(usuario);
        this.erpOrdenXPedidosDao.save(erpOrdenXPedidos);
        

        Querys que = new Querys();
        String store = que.getSQL("ActualizaPedidos");
        String store2 = que.getSQL("ActualizaMaestroPedidos");
        
        Map archivoPedidos = new HashMap();
        
        archivoPedidos.put("compania", compania);
        archivoPedidos.put("id", ss.id);
        archivoPedidos.put("estatus", "O");
        
        this.processDao.execute(store, archivoPedidos);
        this.processDao.execute(store2, archivoPedidos);
      }
      if (result2 != null)
      {
        rq.setSuccess(true);
        

        rq.setMsg("Orden Generada con Folio: " + String.valueOf(id));
      }
      else
      {
        rq.setSuccess(false);
        rq.setData(null);
        
        rq.setMsg("Error al guardar");
      }
    }
    catch (Exception e)
    {
      e.printStackTrace();
    }
    return rq;
  }
  
  
          
          
        @RequestMapping(value={"/generaOrdenPedidoExistAut"}, method={org.springframework.web.bind.annotation.RequestMethod.POST})
  @ResponseBody
  public ResponseQuery2 generaOrdenPedidoExistAut(String data, @RequestParam("idOrden") String idOrden,
          @RequestParam("idAlmacen") String idAlmacen,
          @RequestParam("servicio") String servicio,
          @RequestParam("tipo") String tipo, WebRequest webRequest, Model model)
    throws ParseException
  {
     ResponseQuery2 rq = new ResponseQuery2();
    SimpleDateFormat formatoDelTexto = new SimpleDateFormat("dd/MM/yyyy");
    int index = data.indexOf("[");
    if (index == -1) {
      data = "[" + data + "]";
    }
    System.out.println("data:" + data);

    if (model.asMap().get("compania") == null)
    {
      rq.setSuccess(false);
      rq.setData(null);
      rq.setMsg("La sesion no es valida.");
      return rq;
    }
    String compania = model.asMap().get("compania").toString();
    String usuario = model.asMap().get("usuario").toString();
    
    ErpOrdenCompra orden = new ErpOrdenCompra();
    ErpOrdenCompraId ordenId = new ErpOrdenCompraId();
    
    ErpOrdenCompraDetId result2 = null;
    
    Date fechaOrden = new Date();
    

    System.out.println("-------------------------------------------------------------------");
    try
    {
      int guardado = 0;
      
      ObjectMapper mapper = new ObjectMapper();
      DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
      
      mapper.setDateFormat(df);
      List<PedidosDTO> lista = (List)mapper.readValue(data, mapper
        .getTypeFactory().constructCollectionType(List.class, PedidosDTO.class));
      if (lista.isEmpty())
      {
        rq.setSuccess(false);
        rq.setData(null);
        rq.setMsg("Error no existen datos que guardar");
        return rq;
      }
     

      ErpOrdenXFactura ordeFact = new ErpOrdenXFactura();
      ErpOrdenXFacturaId ordenFactId = new ErpOrdenXFacturaId();
      
      System.out.println("-------------------------------------------------------------------");
      int val = 0;
      Iterator<PedidosDTO> it = lista.iterator();
      
      ErpOrdenCompraDet ordenDet = new ErpOrdenCompraDet();
      ErpOrdenCompraDetId ordenDetId = new ErpOrdenCompraDetId();
      
      ErpOrdenXPedidos erpOrdenXPedidos = new ErpOrdenXPedidos();
      ErpOrdenXPedidosId erpOrdenXPedidosId = new ErpOrdenXPedidosId();
      
      boolean result = false;
      while (it.hasNext())
      {
        System.out.println("-------------------------------------------------------------------");
        PedidosDTO ss = (PedidosDTO)it.next();
        
        ordenDetId.setCompania(compania);
        ordenDetId.setIdOrdenCompra(Integer.valueOf(idOrden));
        int id2 = this.erpOrdenCompraDetDao.getMaxIdDetOrden(ordenDetId);
        ordenDetId.setLinea(Integer.valueOf(id2));
        

        String idOr = agregarCeros(String.valueOf(idOrden), 5);
        String idLi = agregarCeros(String.valueOf(id2), 5);
        
        ordenDet.setId(ordenDetId);
        ordenDet.setCantidadLlego(new BigDecimal(0));
        ordenDet.setCantidadPedida(new BigDecimal(ss.cantidad));
        
        ordenDet.setIdAlmacen(Integer.valueOf(Integer.parseInt(idAlmacen)));
        ordenDet.setIdDet(idOr + idLi);
        ordenDet.setIdEstatus(Integer.valueOf(1));
        
        
        ordenDet.setImporteCotizacion(new BigDecimal(0));
        ordenDet.setIva(new BigDecimal(0));
        ordenDet.setcCostos(ss.ctoCto);
        
       
        
            ordenDet.setIdProducto(ss.idProducto);
            if ((ss.descripcion == null) || (ss.descripcion == "")) {
                ordenDet.setDescripcion(ss.nombreProduct);
              } else {
                ordenDet.setDescripcion(ss.nombreProduct + " (" + ss.descripcion + ")");
              }
            
            
        
        if(ss.idFamilia != null){
            if(!ss.idFamilia.equalsIgnoreCase("")){
                            ordenDet.setIdFamilia(Integer.parseInt(ss.idFamilia));

              }
        }
        
        
        Map ordenProdu = new HashMap();
        ordenProdu.put("compania", compania);
        ordenProdu.put("idProducto", ss.idProducto);
        ordenProdu.put("usuario", ss.usuario);
        
//        List listCotProdc = this.processDao.getMapResult("BuscaCotizacionProducto", ordenProdu);
//        if (listCotProdc != null)
//        {
//          Map producTotal = (HashMap)listCotProdc.get(0);
//          
//          System.out.println("Obteniendo mapa");
//          MathContext mc = new MathContext(4);
//          
//          ordenDet.setImporteCotizacion(new BigDecimal(producTotal.get("IMPORTE_COTIZACION").toString()).multiply(new BigDecimal(ss.cantidad), mc));
//          ordenDet.setImporteCotizacionSub(new BigDecimal(producTotal.get("IMPORTE_COTIZACION").toString()).multiply(new BigDecimal(ss.cantidad), mc));
//        }
//        else
//        {
          ordenDet.setImporteCotizacion(new BigDecimal(ss.costoUnitario).multiply(new BigDecimal(ss.cantidad)));
          ordenDet.setImporteCotizacionSub(new BigDecimal(ss.costoUnitario));
        //}
        
        //=SELECT ID_FAMILIA,,ID_GERENCIA
        
//        List listServicio = this.processDao.getMapResult("BuscaAreaXUsuario", ordenProdu);
//        if (!listServicio.isEmpty())
//        {
//          Map service = (HashMap)listServicio.get(0);
////          
//        
//          if(service.get("ID_FAMILIA") != null){
//            if(!service.get("ID_FAMILIA").toString().equalsIgnoreCase("")){
//                       ordenDet.setIdFamilia(Integer.parseInt(service.get("ID_FAMILIA").toString()));
//                    }
//         }
//         
//         if(service.get("SEC") != null){
//            if(!service.get("SEC").toString().equalsIgnoreCase("")){
//                       ordenDet.setIdDetFamilia(Integer.parseInt(service.get("SEC").toString()));
//                    }
//         }
         if(ss.idArea != null){
            if(!ss.idArea.equalsIgnoreCase("")){
                       ordenDet.setIdGerencia(Integer.parseInt(ss.idArea));
                    }
         }
         
          
   //     }
      
        
        ordenDet.setImporteCotizacionIva(new BigDecimal(0));
        ordenDet.setDescuento(new BigDecimal(0));
        ordenDet.setDescuentoProv(new BigDecimal(0));
        

        ordenDet.setPrecioUnitario(new BigDecimal(0));
        ordenDet.setTotal(new BigDecimal(0));
        ordenDet.setEstatusLinea("1");
        
        ordenDet.setIdProyecto(ss.idProyecto);
        ordenDet.setIdMoneda(ss.moneda);


        result2= this.erpOrdenCompraDetDao.save(ordenDet);
        

        erpOrdenXPedidosId.setCompania(compania);
        erpOrdenXPedidosId.setIdPedido(Integer.valueOf(Integer.parseInt(ss.id)));
        erpOrdenXPedidosId.setLinea(Integer.valueOf(id2));
        erpOrdenXPedidosId.setOrdenCompra(Integer.valueOf(idOrden));
        erpOrdenXPedidos.setId(erpOrdenXPedidosId);
        erpOrdenXPedidos.setUsuario(usuario);
        this.erpOrdenXPedidosDao.save(erpOrdenXPedidos);
        

        Querys que = new Querys();
        String store = que.getSQL("ActualizaPedidos");
        
        Map archivoPedidos = new HashMap();
        
        archivoPedidos.put("compania", compania);
        archivoPedidos.put("id", ss.id);
        archivoPedidos.put("estatus", "O");
        
        this.processDao.execute(store, archivoPedidos);
      }
      if (result2 != null)
      {
        rq.setSuccess(true);
        

        rq.setMsg("Orden Generada con Folio: " + String.valueOf(idOrden));
      }
      else
      {
        rq.setSuccess(false);
        rq.setData(null);
        
        rq.setMsg("Error al guardar");
      }
    }
    catch (Exception e)
    {
      e.printStackTrace();
    }
    return rq;
  }      
  
      @RequestMapping(value={"/generaOrdenPedidoDetExist"}, method={org.springframework.web.bind.annotation.RequestMethod.POST})
  @ResponseBody
  public ResponseQuery2 generaOrdenPedidoDetExist(String data, @RequestParam("idOrden") String idOrden,@RequestParam("idAlmacen") String idAlmacen, WebRequest webRequest, Model model)
    throws ParseException
  {
    ResponseQuery2 rq = new ResponseQuery2();
    SimpleDateFormat formatoDelTexto = new SimpleDateFormat("dd/MM/yyyy");
    int index = data.indexOf("[");
    if (index == -1) {
      data = "[" + data + "]";
    }
    System.out.println("data:" + data);
    System.out.println("idOrden:" + idOrden);
   
    if (model.asMap().get("compania") == null)
    {
      rq.setSuccess(false);
      rq.setData(null);
      rq.setMsg("La sesion no es valida.");
      return rq;
    }
    String compania = model.asMap().get("compania").toString();
    String usuario = model.asMap().get("usuario").toString();
    
    ErpOrdenCompra orden = new ErpOrdenCompra();
    ErpOrdenCompraId ordenId = new ErpOrdenCompraId();
    
    ErpOrdenCompraDetId result2 = null;
    
    Date fechaOrden = new Date();
    

    System.out.println("-------------------------------------------------------------------");
    try
    {
      int guardado = 0;
      
      ObjectMapper mapper = new ObjectMapper();
      DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
      
      mapper.setDateFormat(df);
      List<PedidosDetDTO> lista = (List)mapper.readValue(data, mapper
        .getTypeFactory().constructCollectionType(List.class, PedidosDetDTO.class));
      if (lista.isEmpty())
      {
        rq.setSuccess(false);
        rq.setData(null);
        rq.setMsg("Error no existen datos que guardar");
        return rq;
      }
      

      ErpOrdenXFactura ordeFact = new ErpOrdenXFactura();
      ErpOrdenXFacturaId ordenFactId = new ErpOrdenXFacturaId();
      
      System.out.println("-------------------------------------------------------------------");
      int val = 0;
      Iterator<PedidosDetDTO> it = lista.iterator();
      
      ErpOrdenCompraDet ordenDet = new ErpOrdenCompraDet();
      ErpOrdenCompraDetId ordenDetId = new ErpOrdenCompraDetId();
      
      ErpOrdenXPedidos erpOrdenXPedidos = new ErpOrdenXPedidos();
      ErpOrdenXPedidosId erpOrdenXPedidosId = new ErpOrdenXPedidosId();
      
      boolean result = false;
      while (it.hasNext())
      {
        System.out.println("-------------------------------------------------------------------");
        PedidosDetDTO ss = (PedidosDetDTO)it.next();
        
        ordenDetId.setCompania(compania);
        ordenDetId.setIdOrdenCompra(Integer.valueOf(idOrden));
        int id2 = this.erpOrdenCompraDetDao.getMaxIdDetOrden(ordenDetId);
        ordenDetId.setLinea(Integer.valueOf(id2));
        

        String idOr = agregarCeros(String.valueOf(idOrden), 5);
        String idLi = agregarCeros(String.valueOf(id2), 5);
        
        ordenDet.setId(ordenDetId);
        ordenDet.setIva(new BigDecimal(0));
        ordenDet.setCantidadLlego(new BigDecimal(0));
        ordenDet.setCantidadPedida(new BigDecimal(ss.cantidad));
        if ((ss.descripcion == null) || (ss.descripcion == "")) {
          ordenDet.setDescripcion(ss.nombreProduct);
        } else {
          ordenDet.setDescripcion(ss.nombreProduct + " (" + ss.descripcion + ")");
        }
        ordenDet.setIdAlmacen(Integer.valueOf(Integer.parseInt(idAlmacen)));
        ordenDet.setIdDet(idOr + idLi);
        ordenDet.setIdEstatus(Integer.valueOf(1));
        ordenDet.setTipoGasto(ss.tipoGasto);
        ordenDet.settGDescripcion(ss.tGDescripcion);
        ordenDet.setGrupoGasto(ss.grupoGasto);
        if(ss.departamento != null){    
         if(!ss.departamento.equalsIgnoreCase("")){
                 ordenDet.setDepartamento(Integer.parseInt(ss.departamento));
            }        
        }
        ordenDet.setAvionCC(ss.avionCC);
        ordenDet.setEstacion(ss.estacion);
        
         if ((ss.fechaEntrega != null) || (ss.fechaEntrega != "")) {
          ordenDet.setFechaEntrega(df.parse(ss.fechaEntrega));
        } 
        
        
        ordenDet.setIdProducto(ss.idProducto);
        ordenDet.setImporteCotizacion(new BigDecimal(0));
        if(ss.tipoProduccion != null){
        
            if(ss.tipoProduccion != ""){
                  ordenDet.setTipoProduccion(Integer.parseInt(ss.tipoProduccion));
            }
        
        }
        
        if(ss.idFamilia != null){    
         if(!ss.idFamilia.equalsIgnoreCase("")){
                 ordenDet.setIdFamilia(Integer.parseInt(ss.idFamilia));
            }        
        }
		
		 if(ss.idArea != null){
            if(!ss.idArea.equalsIgnoreCase("")){
                       ordenDet.setIdGerencia(Integer.parseInt(ss.idArea));
                    }
         }
        
        if(ss.montoTotal.equalsIgnoreCase("0")){
        Map ordenProdu = new HashMap();
        ordenProdu.put("compania", compania);
        ordenProdu.put("idProducto", ss.idProducto);
        
        List listCotProdc = this.processDao.getMapResult("BuscaCotizacionProducto", ordenProdu);
        if (listCotProdc != null)
        {
          Map producTotal = (HashMap)listCotProdc.get(0);
          
          System.out.println("Obteniendo mapa");
          MathContext mc = new MathContext(4);
          ordenDet.setTasaIva("16");
          
           BigDecimal ivaC  = (new BigDecimal(producTotal.get("IMPORTE_COTIZACION").toString())).multiply(new BigDecimal(0.16), mc);
          BigDecimal ivaCant  = (new BigDecimal(producTotal.get("IMPORTE_COTIZACION").toString()).multiply(new BigDecimal(ss.cantidad), mc)).multiply(new BigDecimal(0.16), mc);

          BigDecimal subC = new BigDecimal(producTotal.get("IMPORTE_COTIZACION").toString());
          BigDecimal subCant = new BigDecimal(producTotal.get("IMPORTE_COTIZACION").toString()).multiply(new BigDecimal(ss.cantidad), mc);

          BigDecimal totC = subCant.add(ivaCant);
          
            System.out.println(ivaC);
            System.out.println(subC);
            System.out.println(totC);
          
          ordenDet.setImporteCotizacion(totC);
          ordenDet.setImporteCotizacionSub(subC);
          ordenDet.setImporteCotizacionIva(ivaC);

          //ordenDet.setIva(ivaC);
        
        }else{
          ordenDet.setImporteCotizacion(new BigDecimal(0));
          ordenDet.setImporteCotizacionSub(new BigDecimal(0));
          ordenDet.setImporteCotizacionIva(new BigDecimal(0));

          //ordenDet.setIva(new BigDecimal(0));
        }
        }else{
                ordenDet.setTasaIva("0");
        
                ordenDet.setImporteCotizacion(new BigDecimal(ss.montoTotal));
                ordenDet.setImporteCotizacionSub(new BigDecimal(ss.costoUnitario));
                ordenDet.setImporteCotizacionIva(new BigDecimal(0));
        
        
        }
        ordenDet.setDescuento(new BigDecimal(0));
        ordenDet.setDescuentoProv(new BigDecimal(0));
        

        ordenDet.setPrecioUnitario(new BigDecimal(0));
        ordenDet.setTotal(new BigDecimal(0));
        ordenDet.setEstatusLinea("1");
        
        if(ss.moneda != null){
            ordenDet.setIdMoneda(ss.moneda);
        }else{
            
            if(ss.tipoPedido.equalsIgnoreCase("1")){
            
            ordenDet.setIdMoneda("MXN");
        
        }else{
        
            ordenDet.setIdMoneda("USD");
        }
        
        
        }
        
        
        
        
        ordenDet.setIdProyecto(ss.idProyecto);
        ordenDet.setRetencion(3);
        


        result2 = this.erpOrdenCompraDetDao.save(ordenDet);
        

        erpOrdenXPedidosId.setCompania(compania);
        erpOrdenXPedidosId.setIdPedido(Integer.valueOf(Integer.parseInt(ss.id)));
        erpOrdenXPedidosId.setLinea(Integer.valueOf(id2));
        erpOrdenXPedidosId.setOrdenCompra(Integer.valueOf(idOrden));
        erpOrdenXPedidos.setId(erpOrdenXPedidosId);
        erpOrdenXPedidos.setUsuario(usuario);
        this.erpOrdenXPedidosDao.save(erpOrdenXPedidos);
        

        Querys que = new Querys();
        String store = que.getSQL("ActualizaPedidos");
        String store2 = que.getSQL("ActualizaMaestroPedidos");
        
        Map archivoPedidos = new HashMap();
        
        archivoPedidos.put("compania", compania);
        archivoPedidos.put("id", ss.id);
        archivoPedidos.put("estatus", "O");
        
        this.processDao.execute(store, archivoPedidos);
        this.processDao.execute(store2, archivoPedidos);
      }
      if (result2 != null)
      {
        rq.setSuccess(true);
        

        rq.setMsg("Orden Actializada con Folio: " + String.valueOf(idOrden));
      }
      else
      {
        rq.setSuccess(false);
        rq.setData(null);
        
        rq.setMsg("Error al guardar");
      }
    }
    catch (Exception e)
    {
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
  
  public void setErpOrdenCompraDao(ErpOrdenCompraDao erpOrdenCompraDao)
  {
    this.erpOrdenCompraDao = erpOrdenCompraDao;
  }
  
  public void setErpOrdenCompraDetDao(ErpOrdenCompraDetDao erpOrdenCompraDetDao)
  {
    this.erpOrdenCompraDetDao = erpOrdenCompraDetDao;
  }
  
  public void setErpOrdenXFacturaDao(ErpOrdenXFacturaDao erpOrdenXFacturaDao)
  {
    this.erpOrdenXFacturaDao = erpOrdenXFacturaDao;
  }
  
  public void setProcessDao(ProcessDao processDao)
  {
    this.processDao = processDao;
  }
  
  public void setErpOrdenXPedidosDao(ErpOrdenXPedidosDao erpOrdenXPedidosDao)
  {
    this.erpOrdenXPedidosDao = erpOrdenXPedidosDao;
  }
}
