/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.feraz.cxp.controll;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.feraz.cxp.dao.ErpClientesDao;
import com.feraz.cxp.dto.ClientesDTO;
import com.feraz.cxp.model.ErpClientes;
import com.feraz.cxp.model.ErpClientesId;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
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
 * @author 55555
 */

@Controller
@RequestMapping("/CrearCliente")
@SessionAttributes({"compania", "usuario"})
public class ProcessClientes {
    
    private ProcessDao processDao;
     private ErpClientesDao erpClientesDao;
    
    
    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public @ResponseBody
    ResponseQuery saveCliente( @RequestParam("ID_CLIENTE") String idCliente,
            @RequestParam("NOMBRE") String nombre,
            @RequestParam("RFC") String rfc,
            @RequestParam("RAZON_SOCIAL") String razonSocial,
            @RequestParam("CUENTA_CONTABLE") String cuenta,
            @RequestParam("BANCO") String banco,
            @RequestParam("CUENTA_BANCARIA") String cuentaBanco,
            @RequestParam("CLABE") String clabe,
            @RequestParam("AUXILIAR") String auxiliar,
            @RequestParam("CTO_CTO") String cto_cto,            
            @RequestParam("cboPersona") String persona,
            @RequestParam("cboMonedaProv") String moneda,
            @RequestParam("CORREO") String correo,
            @RequestParam("CUENTA_CONTABLE_COMP") String cuentaComple,
            @RequestParam("CUENTA_CONTABLE_ING") String cuentaIngre,
            
            
            
            @RequestParam("proceso") String proceso,

            
            
            
                    
            
             WebRequest webRequest, Model model){

        ResponseQuery rq = new ResponseQuery();

            String compania = model.asMap().get("compania").toString();
            String usuario = model.asMap().get("usuario").toString();
        
          
        
            try{
                
                ErpClientes cli = new ErpClientes();
                ErpClientesId cliId = new ErpClientesId();
                ErpClientesId result = null;
                boolean result2 = false;

              if(proceso.equalsIgnoreCase("I")){  
                  
                  Date d = new Date();
                  
                  cliId.setCompania(compania);
                  cliId.setOrigen("C");
                  
                  
                   Map secCliente = new HashMap();
                
                   secCliente.put("compania", compania);
               
                   List listCliente = processDao.getMapResult("BuscaMaxClienteComp", secCliente);
                   
                    Map clienteSec = (HashMap) listCliente.get(0);
     
                 
                    cliId.setIdCliente("000"+clienteSec.get("ID_CLIENTE").toString());
                    
                    cli.setId(cliId);
                    cli.settPersona(persona);
                    cli.settClieprov(moneda);
                    cli.setfAlta(d);
                    cli.setRfc(rfc);
                    cli.setRazonSocial(razonSocial);
                    cli.setNumeroCuenta(cuentaBanco);
                    cli.setNombre(nombre);
                    cli.setCuentaClabe(clabe);
                    cli.setCtoCto(cto_cto);
                    cli.setBanco(banco);
                    cli.setAuxiliar(auxiliar);
                    cli.setCuentas(cuenta);
                    cli.setCorreo(correo);
                    cli.setCuentaComplementaria(cuentaComple);
                    cli.setCuentaIngreso(cuentaIngre);
                    
                    result = erpClientesDao.save(cli);
                    
                
              
           
              }
              
              
              if(proceso.equalsIgnoreCase("U")){ 
                  
                  System.out.println("actualizando");
                  
                  Date d = new Date();
                  
                  cliId.setCompania(compania);
                  cliId.setOrigen("C");
        
                    cliId.setIdCliente(idCliente);
                    
                    cli.setId(cliId);
                    cli.settPersona(persona);
                    cli.settClieprov(moneda);
                    cli.setfAlta(d);
                    cli.setRfc(rfc);
                    cli.setRazonSocial(razonSocial);
                    cli.setNumeroCuenta(cuentaBanco);
                    cli.setNombre(nombre);
                    cli.setCuentaClabe(clabe);
                    cli.setCtoCto(cto_cto);
                    cli.setBanco(banco);
                    cli.setAuxiliar(auxiliar);
                    cli.setCuentas(cuenta);
                    cli.setCorreo(correo);
                    cli.setCuentaComplementaria(cuentaComple);
                    cli.setCuentaIngreso(cuentaIngre);
                    
                    
                    result2 = erpClientesDao.update(cli);
                    
                
                  
                  
       
              }
              
           if(proceso.equalsIgnoreCase("I")){
            if (result != null){ 
             
                    rq.setSuccess(true);
                    rq.setData(null);
                    rq.setMsg("Cliente Guardado");
               
            }else{

              rq.setSuccess(false);
              rq.setData(null);
              rq.setMsg("Error al guardar");


            }
           }   
              
              
           if(proceso.equalsIgnoreCase("U")){
            if (result2 == true){
             
                    rq.setSuccess(true);
                    rq.setData(null);
                    rq.setMsg("Cliente Guardado");
      
            }else{

              rq.setSuccess(false);
              rq.setData(null);
              rq.setMsg("Error al actualizar");


            }
           }
           
           
            
        }catch(Exception e){
            e.printStackTrace();
            rq.setSuccess(false);
            rq.setData(null);
            rq.setMsg("Error al guardar");
           return rq;
        }
        
        return rq;
    }
    
    
       @RequestMapping(value = "/deleteCliente", method = RequestMethod.POST)
    public @ResponseBody
    ResponseQuery deleteCliente( String data, WebRequest webRequest, Model model) throws IOException {

        ResponseQuery rq = new ResponseQuery();
   //     System.out.println("data factura cancelacion:"+data);
        
         int index = data.indexOf("[");
        if (index == -1) {
            data = "[" + data + "]";
        }


            String compania = model.asMap().get("compania").toString();
            String usuario = model.asMap().get("usuario").toString();
            ObjectMapper mapper = new ObjectMapper();
          
                   
        try{
            List<ClientesDTO> lista = mapper.readValue(data,
                    mapper.getTypeFactory().constructCollectionType(List.class,
                            ClientesDTO.class));

            if (lista.isEmpty()) {
                rq.setSuccess(false);
                rq.setData(null);
                rq.setMsg("Error no existen datos que guardar");
                return rq;
            }

  
            Iterator<ClientesDTO> it = lista.iterator();
     
             ErpClientes cli = new ErpClientes();
             ErpClientesId cliId = new ErpClientesId();
          
            int banderaGe = 0 ;
            while (it.hasNext()) {
//                 System.out.println("-------------------------------------------------------------------");
                 ClientesDTO ss = it.next();
                 
                 cliId.setCompania(ss.compania);
                 cliId.setIdCliente(ss.idCliente);
                 cliId.setOrigen("C");
                 cli.setId(cliId);
                 
                
                 erpClientesDao.delete(cli);
               
                  banderaGe = 1;
            }
            
          
            
            if (banderaGe == 1){
            
                
                    rq.setSuccess(true);
                    rq.setData(null);
                    rq.setMsg("Registros eliminados correctamente");
              
                
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

    public ProcessDao getProcessDao() {
        return processDao;
    }

    public void setProcessDao(ProcessDao processDao) {
        this.processDao = processDao;
    }

    public ErpClientesDao getErpClientesDao() {
        return erpClientesDao;
    }

    public void setErpClientesDao(ErpClientesDao erpClientesDao) {
        this.erpClientesDao = erpClientesDao;
    }
    
}
