/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.feraz.contabilidad.nomina.process;

import com.feraz.contabilidad.nomina.model.ErpNomCancela;
import com.feraz.contabilidad.nomina.model.ErpNomCancelaId;
import com.feraz.contabilidad.nomina.model.ErpNomPoliza;
import com.feraz.contabilidad.nomina.model.ErpNomPolizaId;
import com.feraz.contabilidad.nomina.dao.ErpNomPolizaDao;
import com.feraz.contabilidad.nomina.dao.ErpNomCancelaDao;
import java.math.BigDecimal;
import org.jamh.data.process.ProcessDao;



/**
 *
 * @author Feraz3
 */
public class ProcessNomPoliza {
    
     private  ErpNomCancelaDao erpNomCancelaDao;
    private ErpNomPolizaDao erpNomPolizaDao;
     private ProcessDao processDao;
   
      public boolean processPoliza(ErpNomPoliza erpNomPoliza){
      
           
        
         ErpNomPolizaId id = erpNomPolizaDao.save(erpNomPoliza);
         
         return true;
     
      }
    public void setErpNomCancelaDao(ErpNomCancelaDao erpNomCancelaDao) {
        this.erpNomCancelaDao = erpNomCancelaDao;
    }
    
   
    public void setErpNomPolizaDao(ErpNomPolizaDao erpNomPolizaDao) {
        this.erpNomPolizaDao = erpNomPolizaDao;
    }

    
    public void setProcessDao(ProcessDao processDao) {
        this.processDao = processDao;
    }
}
