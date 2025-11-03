
package com.feraz.polizas3.validation;

import com.feraz.polizas3.model.Polizas;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.List;
import org.jamh.data.process.ProcessDao;
import java.util.Map;

/**
 *
 * @author Feraz3
 */

 
public class DetalleValidation {
    
    private ProcessDao processDao;
     private String msgError;
     
     
     public boolean validaCuenta(Map parametros){
//         
//          Map parametros = new HashMap();
//         
//         
//         
//         parametros.put("compania", detpolizas.getId().getCompania());
//         parametros.put("CUENTA", detpolizas.getId().getCuenta());
         
         if(parametros.get("CUENTA")== null || parametros.get("CUENTA") == ""){
                return false;
         }
         
         List list = processDao.getMapResult("BuscaCuentaCorrecta", parametros);
         
         if (list == null  || list.size()==0) {
             setMsgError("error: La cuenta no es correcta");
            return false;
        } else {
              return true;
//        }
     
         }
     } 
     
      public boolean validaPeriodo(Map parametros){

        // Map parametros = new HashMap();
         
         
         SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
         
       //  parametros.put("compania", parametros);
        // parametros.put("FECHA", format.format(polizas.getId().getFecha()));
         
         List list = processDao.getMapResult("PolizasFovi_Periodos", parametros);
         
         if (list == null  || list.size()==0) {
             setMsgError("error: Periodo no valido");
            return false;
        } else {
              return true;
        }
                
     }
      public boolean validaCC( Map parametros){
         
//          Map parametros = new HashMap();
//         
//         
//         
//         parametros.put("compania", detpolizas.getId().getCompania());
//         parametros.put("CC", detpolizas.getcCostos());
          if(parametros.get("C_COSTOS").toString()=="00"){
              return true;
          }
         
         List list = processDao.getMapResult("BuscaCCCorrecto", parametros);
         
         if (list == null  || list.size()==0) {
             setMsgError("error: El Centro de Costo no es valido");
            return false;
        } else {
              return true;
//        }
     
     }
     }
      
      public boolean validaEstatus(Map parametros){

         //Map parametros = new HashMap();
         
         
         //SimpleDateFormat format = new SimpleDateFormat("yyyy/MM/dd");
         
         //parametros.put("compania", polizas.getId().getCompania());
         //parametros.put("FECHA", format.format(polizas.getId().getFecha()));
         //parametros.put("TIPO_POLIZA", polizas.getId().getTipoPoliza());
         //parametros.put("NUMERO", polizas.getId().getNumero());
         
         List list = processDao.getMapResult("PolizasFovi_Estatus", parametros);
         
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
