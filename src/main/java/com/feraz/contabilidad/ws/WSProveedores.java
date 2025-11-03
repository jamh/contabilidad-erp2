/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.feraz.contabilidad.ws;
import com.feraz.cxp.dao.ErpCClientesDao;
import com.feraz.cxp.model.ErpCClientes;
import com.feraz.cxp.model.ErpCClientesId;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.jws.WebService;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.xml.bind.JAXBException;
import org.jamh.data.process.ProcessDao;

/**
 *
 * @author vavi
 */
@WebService(serviceName = "WSProveedores")
public class WSProveedores {
    
        private ErpCClientesDao erpCClientesDao;
         private ProcessDao processDao;

    
       @WebMethod(operationName = "cargaProveedor")
    public String proveedor(
            @WebParam(name = "usuario") String usuario,
            @WebParam(name = "password") String pass,
            @WebParam(name = "compania") String compania,
            @WebParam(name = "rfc") String rfc,
            @WebParam(name = "razonSocial") String razonSocial,
            @WebParam(name = "tPersona") String tPersona,
            @WebParam(name = "banco") String banco,
            @WebParam(name = "numCuenta") String numCuenta,
            @WebParam(name = "cuentaClabe") String cuentaClabe,
            @WebParam(name = "cve_proveedor") String cveProveedor,
            @WebParam(name = "cp") String cp,
            @WebParam(name = "t_regimen") String tRegimen,
            @WebParam(name = "url_imagen") String urlImagen
    
    ) {
        
        
         
        
  
        System.out.println("-----------WS Proveedore----------------");
        System.out.println(usuario);
        System.out.println(pass);
        System.out.println(compania);
        System.out.println(rfc);
        System.out.println(razonSocial);
        System.out.println(tPersona);
        System.out.println(banco);
        System.out.println(numCuenta);
        System.out.println(cuentaClabe);
        System.out.println(cveProveedor);
        System.out.println(cp);
        System.out.println(tRegimen);
        System.out.println(urlImagen);
        
        
        System.out.println("-----------WS Proveedor----------------");
        
        System.out.println(usuario);
        System.out.println(!usuario.equalsIgnoreCase("denneve1"));
      
        ErpCClientes prov = new ErpCClientes();
        ErpCClientesId provId = new ErpCClientesId();
         
       
        if(!usuario.equalsIgnoreCase("denneve1")){
            
            return msgResp("0","Error usuario incorrecto");
        
        }
        
        if(!pass.equalsIgnoreCase("D3n3v3")){
            
            return msgResp("0","Error password incorrecto");
        
        }
        
        
           System.out.println("guardando prov");
                Map secProv = new HashMap();

                secProv.put("compania", compania);

                List listProv = processDao.getMapResult("BuscaMaxProveedorComp", secProv);

                Map provSec = (HashMap) listProv.get(0);

                 System.out.println(provSec.get("ID_CLIENTE").toString());
                provId.setCompania(compania);
                provId.setIdCliente("000" + provSec.get("ID_CLIENTE").toString());
                provId.setOrigen("P");
                prov.setId(provId);
                prov.setNombre(razonSocial);
                prov.setRfc(rfc);
                prov.setRazonSocial(razonSocial);
                prov.settPersona(tPersona);
                prov.setBanco(banco);
                prov.setNumeroCuenta(numCuenta);
                prov.setCuentaClave(cuentaClabe);
                prov.setCp(cp);
                prov.setCveProveedor(cveProveedor);
                prov.setUrlImagen(urlImagen);
                prov.settRegimen(tRegimen);
               
               
              

                ErpCClientesId result = erpCClientesDao.save(prov);
        
         if (result == null){
         
             return msgResp("0","Error al guardar el proveedor");
         
         }else{
         
             return msgResp("1","Proveedor guardado correctamente con id: "+provId.getIdCliente());
         }
        
    
    }
    
       @WebMethod(operationName = "actualizaProveedor")
    public String proveedorUpdate(
            @WebParam(name = "usuario") String usuario,
            @WebParam(name = "password") String pass,
            @WebParam(name = "compania") String compania,
            @WebParam(name = "rfc") String rfc,
            @WebParam(name = "razonSocial") String razonSocial,
            @WebParam(name = "tPersona") String tPersona,
            @WebParam(name = "banco") String banco,
            @WebParam(name = "numCuenta") String numCuenta,
            @WebParam(name = "cuentaClabe") String cuentaClabe,
             @WebParam(name = "cve_proveedor") String cveProveedor,
            @WebParam(name = "cp") String cp,
            @WebParam(name = "t_regimen") String tRegimen,
            @WebParam(name = "url_imagen") String urlImagen
    
    ) {
        
        
         
        
  
        System.out.println("-----------WS Proveedore----------------");
        System.out.println(usuario);
        System.out.println(pass);
        System.out.println(compania);
        System.out.println(rfc);
        System.out.println(razonSocial);
        System.out.println(tPersona);
        System.out.println(banco);
        System.out.println(numCuenta);
        System.out.println(cuentaClabe);
        System.out.println(cveProveedor);
        System.out.println(cp);
        System.out.println(tRegimen);
        System.out.println(urlImagen);
        
        
        System.out.println("-----------WS Proveedor----------------");
        
        System.out.println(usuario);
        System.out.println(!usuario.equalsIgnoreCase("denneve1"));
      
        ErpCClientes prov = new ErpCClientes();
        ErpCClientesId provId = new ErpCClientesId();
         
       
        if(!usuario.equalsIgnoreCase("denneve1")){
            
            return msgResp("0","Error usuario incorrecto");
        
        }
        
        if(!pass.equalsIgnoreCase("D3n3v3")){
            
            return msgResp("0","Error password incorrecto");
        
        }
        
        
           System.out.println("guardando prov");
                Map secProv = new HashMap();

                secProv.put("compania", compania);
                secProv.put("rfc", rfc);
                List listProv = processDao.getMapResult("BuscaIdProv", secProv);
                
                if(listProv.size() == 0){
                
                    return msgResp("0","Error el rfc del proveedor no se encuentra registrado");
                
                }

                Map provSec = (HashMap) listProv.get(0);

                 System.out.println(provSec.get("ID_CLIENTE").toString());
                provId.setCompania(compania);
                provId.setIdCliente(provSec.get("ID_CLIENTE").toString());
                provId.setOrigen("P");
                prov.setId(provId);
                prov.setNombre(razonSocial);
                prov.setRfc(rfc);
                prov.setRazonSocial(razonSocial);
                prov.settPersona(tPersona);
                prov.setBanco(banco);
                prov.setNumeroCuenta(numCuenta);
                prov.setCuentaClave(cuentaClabe);
                prov.setCp(cp);
                prov.setCveProveedor(cveProveedor);
                prov.setUrlImagen(urlImagen);
                prov.settRegimen(tRegimen);
               
              

                boolean result = erpCClientesDao.update(prov);
        
         if (result == false){
         
             return msgResp("0","Error al actualizar el proveedor");
         
         }else{
         
             return msgResp("1","Proveedor actualizado correctamente con id: "+provId.getIdCliente());
         }
        
    
    }
    
    
    @WebMethod(exclude = true)
    public String msgResp(String result, String msg) {
       
        return "<?xml version=\"1.0\" encoding=\"UTF-8\"?>"
                + "<response>\n"
                + "  <result>" + result + "</result>\n"
                + "  <mensaje>" + msg + "</mensaje>\n"
                + "</response>\n";
    }

     @WebMethod(exclude = true)
    public void setErpCClientesDao(ErpCClientesDao erpCClientesDao) {
        this.erpCClientesDao = erpCClientesDao;
    }

     @WebMethod(exclude = true)
    public void setProcessDao(ProcessDao processDao) {
        this.processDao = processDao;
    }
    
    

}
