/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.feraz.polizas3.validation;

import com.feraz.polizas3.model.Polizas;
import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.List;
import org.jamh.data.process.ProcessDao;
import java.util.Map;
/**
 *
 * @author Feraz3
 */
public class MaestroValidation {
    
     private ProcessDao processDao;
     private String msgError;

     
     public boolean validaPeriodo(Polizas polizas){

         Map parametros = new HashMap();
         
         
         SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
         
         parametros.put("compania", polizas.getId().getCompania());
         parametros.put("FECHA", format.format(polizas.getId().getFecha()));
         
         List list = processDao.getMapResult("PolizasFovi_Periodos", parametros);
         
         if (list == null  || list.size()==0) {
             setMsgError("error: Periodo no valido");
            return false;
        } else {
              return true;
        }
                
     }
     
     public boolean validaEstatus(Polizas polizas, String estatus){

         Map parametros = new HashMap();
         
         
         SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
         
         parametros.put("compania", polizas.getId().getCompania());
         parametros.put("FECHA", format.format(polizas.getId().getFecha()));
         parametros.put("TIPO_POLIZA", polizas.getId().getTipoPoliza());
         parametros.put("NUMERO", polizas.getId().getNumero());
         parametros.put("estatus",estatus);
         
         List list = processDao.getMapResult("PolizasFovi_EstatusM", parametros);
       
             
         
         if (list == null  || list.isEmpty()) {
             return true;
        } else {
               setMsgError("error: La poliza esta bloqueda");
            return false;
             
        }
                
     }
     
     
     
     
     
     
    public void setProcessDao(ProcessDao processDao) {
        this.processDao = processDao;
    }

    public void setMsgError(String msgError) {
        this.msgError = msgError;
    }

    public String getMsgError() {
        return msgError;
    }
     
    
}
