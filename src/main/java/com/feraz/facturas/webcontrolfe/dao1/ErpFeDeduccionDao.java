/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.feraz.facturas.webcontrolfe.dao1;

import com.feraz.facturas.webcontrolfe.model.ErpFeDeduccion;
import com.feraz.facturas.webcontrolfe.model.ErpFeDeduccionId;
import java.util.List;

/**
 *
 * @author vavi
 */
public interface ErpFeDeduccionDao {
    
     public ErpFeDeduccionId save(ErpFeDeduccion erpFeDeduccion);
       public List<ErpFeDeduccion> FindErpFeDeduccion();
       public boolean delete(ErpFeDeduccion erpFeDeduccion);
       public boolean update(ErpFeDeduccion erpFeDeduccion);
        public int getMaxIdErpFeDeduccion(ErpFeDeduccionId id);
    
}
