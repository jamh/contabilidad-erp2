/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.feraz.ordencompra.ws;

import com.feraz.ordencompra.dao.ErpOrdenCompraDao;
import com.feraz.ordencompra.dao.ErpOrdenCompraDetDao;
import com.feraz.ordencompra.model.ErpOrdenCompra;
import com.feraz.ordencompra.model.ErpOrdenCompraDet;
import com.feraz.ordencompra.model.ErpOrdenCompraDetId;
import com.feraz.ordencompra.model.ErpOrdenCompraId;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;
import javax.xml.bind.JAXBException;
import org.springframework.beans.factory.annotation.Autowired;
import com.feraz.cxp.dao.ErpCClientesDao;
import com.feraz.cxp.model.ErpCClientes;
import java.util.HashMap;
import java.util.Map;
import org.jamh.wf.process.Querys;
import org.jamh.data.process.ProcessDao;



/**
 *
 * @author vavi
 */
@WebService(serviceName = "WSOrdenCompra")
public class WSOrdenCompra {
    
     @Autowired
    private ErpOrdenCompraDao erpOrdenCompraDao;
     
      @Autowired
    private ErpOrdenCompraDetDao erpOrdenCompraDetDao;
      @Autowired
    private ErpCClientesDao erpCClientesDao;
      @Autowired
    private ProcessDao processDao;
    
           @WebMethod(operationName = "cargaOrden")
    public String orden(
            @WebParam(name = "usuario") String usuario,
            @WebParam(name = "password") String pass,
            @WebParam(name = "idProceso") String idProceso,
            @WebParam(name = "idOrden") String idOrden,
            @WebParam(name = "compania") String compania,
            @WebParam(name = "usuarioCap") String usuarioCap,
            @WebParam(name = "estatus") String estatus,
            @WebParam(name = "rfc") String rfc,
            @WebParam(name = "usuarioAut") String usuarioAut,
            @WebParam(name = "condPago") String condPago,
            @WebParam(name = "fechaOrden") String fechaOrden,
            @WebParam(name = "fechaRequerida") String fechaRequerida,
            @WebParam(name = "idAlmacen") String idAlmacen,
            @WebParam(name = "nombre") String nombre,
            @WebParam(name = "descripcion") String descripcion,
            @WebParam(name = "linea") String linea,
            @WebParam(name = "idProducto") String idProducto,
            @WebParam(name = "descripcionLinea") String descripcionLinea,
            @WebParam(name = "cantidadPedida") String cantidadPedida,
            @WebParam(name = "cantidadLlego") String cantidadLlego,
            @WebParam(name = "precioUnitario") String precioUnitario,
            @WebParam(name = "iva") String iva,
            @WebParam(name = "total") String total,
            @WebParam(name = "importeCotizacion") String importeCotizacion,
            @WebParam(name = "idEstatus") String idEstatus,
            @WebParam(name = "idActivo") String idActivo
            
            

    
    ) throws IOException, FileNotFoundException, JAXBException {
        
        
         
        
  
        System.out.println("-----------WS Orden Compra----------------");
        System.out.println(usuario);
        System.out.println(pass);
        System.out.println(idProceso);
        System.out.println(idOrden);
        System.out.println(compania);
        System.out.println(usuarioCap);
        System.out.println(estatus);
        System.out.println(rfc);
        System.out.println(usuarioAut);
        System.out.println(condPago);
        System.out.println(fechaOrden);
        System.out.println(fechaRequerida);
        System.out.println(idAlmacen);
        System.out.println(nombre);
        System.out.println(descripcion);
        System.out.println(linea);
        System.out.println(idProducto);
        System.out.println(descripcionLinea);
        System.out.println(cantidadPedida);
        System.out.println(cantidadLlego);
        System.out.println(precioUnitario);
        System.out.println(iva);
        System.out.println(total);
        System.out.println(importeCotizacion);
        System.out.println(idEstatus);
        System.out.println(idActivo);
        System.out.println("-----------WS Orden Compra----------------");
        
      
         
       
        if(!usuario.equalsIgnoreCase("feraz2018")){
            generaLog(compania,idProceso,idOrden, linea, "0", "Error usuario incorrecto");
            return msgResp("0","Error usuario incorrecto",idProceso);
        
        }
        
        if(!pass.equalsIgnoreCase("Af278E")){
            
            generaLog(compania,idProceso,idOrden, linea, "0", "Error password incorrecto");

            return msgResp("0","Error password incorrecto",idProceso);
        
        }
        
        System.out.println("validando datos");
        System.out.println(idOrden);
        
    
        
        if(idOrden.equalsIgnoreCase("")){
            
            generaLog(compania,idProceso,idOrden, linea, "0", "campo idOrden no puede estar vacio");
        
           return msgResp("0","campo idOrden no puede estar vacio",idProceso);
        }
      
        if(idProceso.equalsIgnoreCase("")){
            
            generaLog(compania,idProceso,idOrden, linea, "0", "campo idProceso no puede estar vacio");
        
           return msgResp("0","campo idProceso no puede estar vacio",idProceso);
        }
        
        if(compania.equalsIgnoreCase("")){
            
            generaLog(compania,idProceso,idOrden, linea, "0", "campo compania no puede estar vacio");
        
           return msgResp("0","campo compania no puede estar vacio",idProceso);
        }
        
        if(estatus.equalsIgnoreCase("")){
           generaLog(compania,idProceso,idOrden, linea, "0", "campo estatus no puede estar vacio");
           return msgResp("0","campo estatus no puede estar vacio",idProceso);
        }
        
        if(rfc.equalsIgnoreCase("")){
           generaLog(compania,idProceso,idOrden, linea, "0", "campo rfc no puede estar vacio");
           return msgResp("0","campo rfc no puede estar vacio",idProceso);
        }
        
        if(fechaOrden.equalsIgnoreCase("")){
           generaLog(compania,idProceso,idOrden, linea, "0", "campo fechaOrden no puede estar vacio");
           return msgResp("0","campo fechaOrden no puede estar vacio",idProceso);
        }
        
        if(fechaRequerida.equalsIgnoreCase("")){
           generaLog(compania,idProceso,idOrden, linea, "0", "campo fechaRequerida no puede estar vacio");
           return msgResp("0","campo fechaRequerida no puede estar vacio",idProceso);
        }
        
        
        if(idAlmacen.equalsIgnoreCase("")){
            
           generaLog(compania,idProceso,idOrden, linea, "0", "campo idAlmacen no puede estar vacio"); 
        
           return msgResp("0","campo idAlmacen no puede estar vacio",idProceso);
        }
        
        if(descripcion.equalsIgnoreCase("")){
            
           generaLog(compania,idProceso,idOrden, linea, "0", "campo descripcion no puede estar vacio"); 
        
           return msgResp("0","campo descripcion no puede estar vacio",idProceso);
        }
        
        if(linea.equalsIgnoreCase("")){
            
           generaLog(compania,idProceso,idOrden, linea, "0", "campo linea no puede estar vacio"); 
        
           return msgResp("0","campo linea no puede estar vacio",idProceso);
        }
        
        if(idProducto.equalsIgnoreCase("")){
            
           generaLog(compania,idProceso,idOrden, linea, "0", "campo idProducto no puede estar vacio"); 
        
           return msgResp("0","campo idProducto no puede estar vacio",idProceso);
        }
        
        if(descripcionLinea.equalsIgnoreCase("")){
            
           generaLog(compania,idProceso,idOrden, linea, "0", "campo descripcionLinea no puede estar vacio"); 
        
           return msgResp("0","campo descripcionLinea no puede estar vacio",idProceso);
        }
        
        if(cantidadPedida.equalsIgnoreCase("")){
            
           generaLog(compania,idProceso,idOrden, linea, "0", "campo cantidadPedida no puede estar vacio"); 
        
           return msgResp("0","campo cantidadPedida no puede estar vacio",idProceso);
        }
        
        if(cantidadLlego.equalsIgnoreCase("")){
            
           generaLog(compania,idProceso,idOrden, linea, "0", "campo cantidadLlego no puede estar vacio"); 
        
           return msgResp("0","campo cantidadLlego no puede estar vacio",idProceso);
        }
        
        if(precioUnitario.equalsIgnoreCase("")){
        
           generaLog(compania,idProceso,idOrden, linea, "0", "campo precioUnitario no puede estar vacio"); 
           return msgResp("0","campo precioUnitario no puede estar vacio",idProceso);
        }
        
        if(iva.equalsIgnoreCase("")){
            
           generaLog(compania,idProceso,idOrden, linea, "0", "campo iva no puede estar vacio"); 
        
           return msgResp("0","campo iva no puede estar vacio",idProceso);
        }
        
        
        if(total.equalsIgnoreCase("")){
            
           generaLog(compania,idProceso,idOrden, linea, "0", "campo total no puede estar vacio");
        
           return msgResp("0","campo total no puede estar vacio",idProceso);
        }
        
        if(importeCotizacion.equalsIgnoreCase("")){
            
           generaLog(compania,idProceso,idOrden, linea, "0", "campo importeCotizacion no puede estar vacio");
        
           return msgResp("0","campo importeCotizacion no puede estar vacio",idProceso);
        }
        
        if(idEstatus.equalsIgnoreCase("")){
            
           generaLog(compania,idProceso,idOrden, linea, "0", "campo idEstatus no puede estar vacio");
        
           return msgResp("0","campo idEstatus no puede estar vacio",idProceso);
        }
        
        if(idActivo.equalsIgnoreCase("")){
            
           generaLog(compania,idProceso,idOrden, linea, "0", "campo idActivo no puede estar vacio");
        
           return msgResp("0","campo idActivo no puede estar vacio",idProceso);
        }
        
       
        
            ErpOrdenCompra orden = new ErpOrdenCompra();
            ErpOrdenCompraId ordenId = new ErpOrdenCompraId();
           
            ErpOrdenCompraId result2 = null;
            
           // Date fechaOrden = new Date();
        
        
        System.out.println("---------------------------Generando Orden------------------------------");
       
        try {
            int guardado = 0;
            int id = 0;
           
            DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
             
            ErpOrdenCompra resultOrden = erpOrdenCompraDao.findErpOrdenCompraWS(compania, idOrden);
             
              if(resultOrden == null){ 
                  
                  
                ErpCClientes erpCclientes = erpCClientesDao.findProveedor(compania, rfc);
                
                
                if(erpCclientes == null){
                    
                    generaLog(compania,idProceso,idOrden, linea, "0", "El rfc no existe dentro del catalogo de proveedores.");
                    
                    return msgResp("0","El rfc no existe dentro del catalogo de proveedores.",idProceso);
                
                
                }
                  
                ordenId.setCompania(compania);
                
                id = erpOrdenCompraDao.getMaxIdOrden(ordenId);
                ordenId.setId(id);                
                orden.setId(ordenId);
                orden.setClasificacion("");
                orden.setCondicionesPago(condPago);
                orden.setCtoCto("");
                orden.setEstatus(estatus);
                orden.setFechaOrden(df.parse(fechaOrden));
                orden.setFechaRequerida(df.parse(fechaRequerida));
            //    orden.setFolio(Integer.parseInt(ss.folio));
                orden.setIdAlmacen(Integer.parseInt(idAlmacen));
                orden.setIdProveedor(erpCclientes.getId().getIdCliente());
                //orden.setImporte(new BigDecimal(ss.importe));
                //orden.setPeriodo(Integer.parseInt(ss.periodo));
                orden.setRfc(rfc);
               // orden.setTotal(new BigDecimal(total));
               // orden.setTotalPendiente(new BigDecimal(0));
                orden.setUsuario(usuarioCap);
                orden.setUsuarioAutorizo(usuarioAut);
                orden.setUsuarioComprador("");
                orden.setNombre(nombre);
                orden.setDescripcion(descripcion);
                orden.setIdWs(idOrden);
                
                
                result2 = erpOrdenCompraDao.save(orden);

              }else{
              
                 id = resultOrden.getId().getId();
              
              }
       
           
           System.out.println("--------------------------Generando Linea-----------------------------------------");
            int val = 0;
          
            
            ErpOrdenCompraDet ordenDet = new ErpOrdenCompraDet();
            ErpOrdenCompraDetId ordenDetId = new ErpOrdenCompraDetId();
            
            System.out.println("-----buscando linea existente----------");
            ErpOrdenCompraDet buscLinea = erpOrdenCompraDetDao.findErpOrdenCompraDetWS(compania, idOrden, linea);
                  
            boolean result = false;
            
            ErpOrdenCompraDetId resulDet = null;
             
            if(buscLinea == null){
                   ordenDetId.setCompania(compania);
                ordenDetId.setIdOrdenCompra(id);
                int id2 = erpOrdenCompraDetDao.getMaxIdDetOrden(ordenDetId);
                ordenDetId.setLinea(id2);
                
                
                String idOr = agregarCeros(String.valueOf(id),5);
                String idLi = agregarCeros(String.valueOf(id2),5);
                
                ordenDet.setId(ordenDetId);
                ordenDet.setIdLineaWS(linea);
                ordenDet.setIdWs(idOrden);
                ordenDet.setCantidadLlego(new BigDecimal(cantidadLlego));
                ordenDet.setCantidadPedida(new BigDecimal(cantidadPedida));
                    
                ordenDet.setDescripcion(descripcionLinea);
                
                
                
                ordenDet.setIdAlmacen(Integer.parseInt(idAlmacen));
                ordenDet.setIdDet(idOr+idLi);
                ordenDet.setIdEstatus(Integer.parseInt(idEstatus));
              
                ordenDet.setIdProducto(idProducto);
                ordenDet.setImporteCotizacion(new BigDecimal(importeCotizacion));
                ordenDet.setIva(new BigDecimal(iva));
                
                 
                
                ordenDet.setPrecioUnitario(new BigDecimal(precioUnitario));
                ordenDet.setTotal(new BigDecimal(total));
                ordenDet.setEstatusLinea(idActivo);
                
                
               
                resulDet = erpOrdenCompraDetDao.save(ordenDet);
                
            }else{
            
                generaLog(compania,idProceso,idOrden, linea, "0", "El id de linea: "+linea+" ya existe para el id de Orden: "+idOrden);
                return msgResp("0","El id de linea: "+linea+" ya existe para el id de Orden: "+idOrden,idProceso);
            
            }
                
              
//                

//                
                  
                  

            

          
            

            if (resulDet != null) {
                
                  generaLog(compania,idProceso,idOrden, linea, "1", "Datos guardados correctamete.");
               
                  return msgResp("1","Datos guardados correctamete.",idProceso);
                 
            } else {
                
                generaLog(compania,idProceso,idOrden, linea, "0", "Error al guardar los datos.");
               
                return msgResp("0","Error al guardar los datos.",idProceso);
                
            }

        } catch (Exception e) {
            e.printStackTrace();
            
            generaLog(compania,idProceso,idOrden, linea, "0", "Error al guardar los datos.");
            
            return msgResp("0","Error al guardar los datos.",idProceso);
            
        }

       
         
        
    
    }
    
    public void generaLog(String compania,String idProceso,String idOrden, String linea, String estatus, String msg){
        
                          Querys que = new Querys();
            String store = que.getSQL("insertLogWsOrden");
            
            Map logWs = new HashMap();
            
            logWs.put("compania", compania);
            logWs.put("idProceso", idProceso);
            logWs.put("idOrden", idOrden);
            logWs.put("linea", linea);
            logWs.put("estatus", estatus);
            logWs.put("msg", msg);
            
        
                 processDao.execute(store, logWs); 
    
    
    }
    
     private static String agregarCeros(String string, int largo){
    			String ceros = "";
 
    			int cantidad = largo - string.length();
 
    			if (cantidad >= 1)
    			{
    				for(int i=0;i<cantidad;i++)
    				{
    					ceros += "0";
    				}
 
    				return (ceros + string);
    			}
    			else
    				return string;
    		}
    
     
    

    
    @WebMethod(exclude = true)
    public String msgResp(String result, String msg, String idProceso) {
       
        return "<?xml version=\"1.0\" encoding=\"UTF-8\"?>"
                + "<response>\n"
                + "  <result>" + result + "</result>\n"
                + "  <mensaje>" + msg + "</mensaje>\n"
                + "  <idProceso>" + idProceso + "</idProceso>\n"
                + "</response>\n";
    }
    
}
