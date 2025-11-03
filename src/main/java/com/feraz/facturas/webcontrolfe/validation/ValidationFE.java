/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.feraz.facturas.webcontrolfe.validation;

/**
 *
 * @author Feraz3
 */
import com.feraz.facturas.webcontrolfe.model.ErpFeComprobantes;
import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.List;
import org.jamh.data.process.ProcessDao;
import java.util.Map;

public class ValidationFE {
    
    private ProcessDao processDao;
     private String msgErrorFe;
     
     
     
      public boolean validaRelacion(ErpFeComprobantes erpFeComprobantes){

         Map parametros = new HashMap();
         
         
         SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
         
         parametros.put("compania", erpFeComprobantes.getId().getCompania());
         parametros.put("folio", erpFeComprobantes.getFolio());
         parametros.put("numero_fe", erpFeComprobantes.getId().getNumero());
         parametros.put("uuid", erpFeComprobantes.getUuid());
         
         List list = processDao.getMapResult("BuscaRelacion", parametros);
         
         if (list == null  || list.size()==0) {
             
            return true;
        } else {
             setMsgErrorFe("Error: La factura ya tiene relacion. Para realizar esta operación debe quitar la relación");
              return false;
        }
                
     }
     
     

    public void setProcessDao(ProcessDao processDao) {
        this.processDao = processDao;
    }

    public void setMsgErrorFe(String msgErrorFe) {
        this.msgErrorFe = msgErrorFe;
    }

    public String getMsgErrorFe() {
        return msgErrorFe;
    }
     
     
    
}
