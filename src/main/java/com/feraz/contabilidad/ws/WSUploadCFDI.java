package com.feraz.contabilidad.ws;

import com.feraz.contabilidad.ws.model.BeanXML;
import com.feraz.contabilidad.ws.process.ProcessXML;
import javax.jws.WebService;
import javax.jws.WebMethod;
import javax.jws.WebParam;

/**
 *
 * @author Ing. JAMH
 */
@WebService(serviceName = "WSCFDI")
public class WSUploadCFDI {
    
    private ProcessXML processXML;

    @WebMethod(operationName = "uploadCFDI")
    public String archivoXML(
            @WebParam(name = "pid") String pid,
            @WebParam(name = "user") String user,
            @WebParam(name = "password") String password,
            @WebParam(name = "compania") String compania,
            @WebParam(name = "tipoPoliza") String tipoPoliza,
            @WebParam(name = "fecha") String fecha,
            @WebParam(name = "numero") String numero,
            @WebParam(name = "nombre") String nombre,
            @WebParam(name = "XML") String xml
    ) {
        
        BeanXML b = new BeanXML();
        b.setCompania(compania);
        b.setFecha(fecha);
        b.setNombre(nombre);
        b.setNumero(numero);
        b.setPassword(password);
        b.setPid(pid);
        b.setTipoPoliza(tipoPoliza);
        b.setUser(user);
        b.setXml(xml);
      //  ProcessXML p=new ProcessXML();
      
        return  processXML.procesa(b);
    }

    public void setProcessXML(ProcessXML processXML) {
        this.processXML = processXML;
    }
    
    
    
     
}
