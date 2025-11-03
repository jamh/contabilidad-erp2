package com.feraz.contabilidad.ws.process;

import com.feraz.contabilidad.ws.model.BeanXML;
import com.feraz.facturas.webcontrolfe.App.App;
import com.feraz.polizas3.dao.ErpPolizasXFacturasDao;
import com.feraz.polizas3.model.ErpPolizasXFacturas;
import com.feraz.polizas3.model.ErpPolizasXFacturasId;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.jamh.data.process.ProcessDao;
import org.jamh.tools.Util;
import org.springframework.beans.factory.annotation.Value;

/**
 *
 * @author Ing. JAMH
 */
public class ProcessXML {

    @Value("${document.file.dirOutDocumentFE}")
    private String dirOutFileDocument;
    private App app;
    private ErpPolizasXFacturasDao erpPolizasXFacturasDao;
    private ProcessDao processDao;

    public ProcessXML() {
    }

    public String procesa(BeanXML bean) {
        
     

        try {
//            be
            if (bean.getCompania() == null) {
                return Util.msgRespXML(bean.getPid(), 0, "Clave de Compañia no valida ");
            }

            if (bean.getXml() == null) {
                return Util.msgRespXML(bean.getPid(), 0, "XML no valida ");
            }

            if (!bean.getUser().equals("convtex") && !bean.getPassword().equals("drY2473")) {
                return Util.msgRespXML(bean.getPid(), 0, "Usuario o password no valido");
            }
            
            String archivo = dirOutFileDocument + "/" + bean.getCompania() + "/" + bean.getNombre();
            
     
             Util.createDirectory(dirOutFileDocument + "/" + bean.getCompania());
          
            boolean res = Util.createFileUTF8(archivo, bean.getXml());

            if (!res) {
                return Util.msgRespXML(bean.getPid(), 0, "Error al leer el archivo");
            }
            
            String idErr = "" + System.currentTimeMillis();
            app.setId(idErr);

            boolean valida = app.validaCFDI1(archivo, false);
          

            if (!valida) {
                return Util.msgRespXML(bean.getPid(), 0, "Error XML no valido");
            }

            int pro = app.cargaComprobante(archivo, bean.getCompania(), null);
            
              
            SimpleDateFormat formatFecha = new SimpleDateFormat("dd/MM/yyyy");
            
            ErpPolizasXFacturas pxfe= new ErpPolizasXFacturas();    
            ErpPolizasXFacturasId pxfeid= new ErpPolizasXFacturasId();
            pxfeid.setCompania(bean.getCompania());
            pxfeid.setFechaPol(formatFecha.parse(bean.getFecha()));
            pxfeid.setNumeroPol(bean.getNumero());
            pxfeid.setTipoPol(bean.getTipoPoliza());
            pxfeid.setOperacion("GE");
           
     
            pxfe.setId(pxfeid);
            pxfe.setNumeroFe(pro);
            
             Map param = new HashMap();
               param.put("compania", bean.getCompania());
               param.put("numero", pro);
               
                   List list1 = processDao.getMapResult("buscaDatosWSF", param);
                   
                   if(list1==null){
                       
                       return Util.msgRespXML(bean.getPid(), 0, "Error al insertar XML");
                   }
                
                   Map result1 = (HashMap) list1.get(0);

            pxfe.setUuid(result1.get("UUID").toString());
            pxfe.setFolio(result1.get("FOLIO").toString());
         
            ErpPolizasXFacturasId relacion = erpPolizasXFacturasDao.save(pxfe);

            if (pro == 0) {
                return Util.msgRespXML(bean.getPid(), 0, "Error al insertar XML");
            }
            if (relacion == null){
                
                return Util.msgRespXML(bean.getPid(), 0, "Error al insertar generar relacion XML");
            
            }

        } catch (Exception e) {
            return Util.msgRespXML(bean.getPid(), 0, "Error al procesar xml ");
        }
        return Util.msgRespXML(bean.getPid(), 1, "XML correcto");
    }

    public void setDirOutFileDocument(String dirOutFileDocument) {
        this.dirOutFileDocument = dirOutFileDocument;
    }

    public void setApp(App app) {
        this.app = app;
    }

    public void setErpPolizasXFacturasDao(ErpPolizasXFacturasDao erpPolizasXFacturasDao) {
        this.erpPolizasXFacturasDao = erpPolizasXFacturasDao;
    }

    public void setProcessDao(ProcessDao processDao) {
        this.processDao = processDao;
    }
    
    

}
