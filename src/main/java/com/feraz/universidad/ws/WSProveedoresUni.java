package com.feraz.universidad.ws;

import com.feraz.cxp.dao.ErpCClientesDao;
import com.feraz.cxp.model.ErpCClientes;
import com.feraz.cxp.model.ErpCClientesId;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;
import javax.xml.bind.JAXBException;
import org.jamh.data.process.ProcessDao;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author Armand
 */
@WebService(serviceName = "WSProveedoresUni")
public class WSProveedoresUni {

    @Autowired
    private ProcessDao processDao;

    @Autowired
    private ErpCClientesDao erpCClientesDao;

    @WebMethod(operationName = "cargaProveedoresUni")
    public String orden(
            @WebParam(name = "usuario") String usuario,
            @WebParam(name = "password") String password,
            @WebParam(name = "compania") String compania,
            @WebParam(name = "id_proveedor") String ide_prov,
            @WebParam(name = "nombre") String nombre,
            @WebParam(name = "rfc") String rfc,
            @WebParam(name = "t_persona") String t_persona,
            @WebParam(name = "t_moneda") String t_clieprov,
            @WebParam(name = "banco") String banco,
            @WebParam(name = "numero_cuenta") String numero_cuenta,
            @WebParam(name = "cuenta_clave") String cuenta_clave
    ) throws IOException, FileNotFoundException, JAXBException {

        System.out.println("-----------WS Proveedores Universidad Insurgentes----------------");
        System.out.println(usuario);
        System.out.println(password);
        System.out.println(compania);
        System.out.println(ide_prov);
        System.out.println(nombre);
        System.out.println(rfc);
        System.out.println(t_persona);
        System.out.println(t_clieprov);
        System.out.println(banco);
        System.out.println(numero_cuenta);
        System.out.println(cuenta_clave);
        System.out.println("-----------WS Proveedores Universidad Insurgentes----------------");
        //**************************************************************
        try {

            System.out.println("------------- GENERANDO VALIDACIONES -------------");

            if (!usuario.equalsIgnoreCase("feraz2018")) {
                return msgResp("0", "Error usuario incorrecto");
            }

            if (!password.equalsIgnoreCase("PrU3b419*")) {
                return msgResp("0", "Error password incorrecto");
            }

            if (compania.equalsIgnoreCase("")) {
                return msgResp("0", "Error: Compañia no puede ser nula");
            }
            if (ide_prov.equalsIgnoreCase("")){
                return msgResp("0", "Error: Falta el id del Proveedor");
            }

            if (nombre.equalsIgnoreCase("")) {
                return msgResp("0", "Error: Falta la Razon Social");
            }

            if (rfc.equalsIgnoreCase("")) {
                return msgResp("0", "Error: Falta el RFC");
            }
            
            if (t_clieprov.equalsIgnoreCase("")){
                return msgResp("0", "Error: Falta el Tipo de Persona");
            }
            if (t_clieprov.equalsIgnoreCase("")) {
                return msgResp("0", "Error: Falta la Moneda");
            }

            if (banco.equalsIgnoreCase("")) {
                return msgResp("0", "Error: Falta el Banco");
            }

            if (numero_cuenta.equalsIgnoreCase("")) {
                return msgResp("0", "Error: Falta Numero de Cuenta");
            }

            if (cuenta_clave.equalsIgnoreCase("")) {
                return msgResp("0", "Error: Falta la cuenta clabe");
            }
            
            //VALIDAMOS QUE EL PROVEEDOR NO EXISTA
             ErpCClientes erpCclientes = erpCClientesDao.findProveedor(compania, rfc);
                
                if(null != erpCclientes){
                    return msgResp("0","El RFC que desea dar de alta ya existe ");
                }
            
            
            

            ErpCClientes prov = new ErpCClientes();
            ErpCClientesId provId = new ErpCClientesId();
            ErpCClientesId result = null;

            Map secProv = new HashMap();

            secProv.put("compania", compania);

            List listProv = processDao.getMapResult("BuscaMaxProveedorComp", secProv);

            Map provSec = (HashMap) listProv.get(0);

            provId.setCompania(compania);
            provId.setIdCliente("000" + provSec.get("ID_CLIENTE").toString());
            provId.setOrigen("P");
            prov.setId(provId);
            prov.setNombre(nombre);
            prov.setRfc(rfc);
            prov.setRazonSocial(nombre);
            prov.setCuenta(numero_cuenta);
            prov.setBanco(banco);
            prov.setCuentaClave(cuenta_clave);
            prov.settClieprov(t_clieprov);
            prov.settPersona(t_persona);
            prov.setIdReferencia(ide_prov);

            result = erpCClientesDao.save(prov);
            
            if (!result.equals("")){
                return msgResp("0","Proveedor Gurdado");
            }
            else{
                return msgResp("0", "Error al Guardar Proveedor");
            }

        } catch (Exception e) {
            e.printStackTrace();
            return msgResp("0","Error al guardar");
        }

        //**************************************************************
        // return msgResp("0", "Error password incorrecto");
    }

    @WebMethod(exclude = true)
    public String msgResp(String result, String msg) {
        return "<?xml version=\"1.0\" encoding=\"UTF-8\"?>"
                + "<response>\n"
                + "  <result>" + result + "</result>\n"
                + "  <mensaje>" + msg + "</mensaje>\n"
                + "</response>\n";
    }

}
