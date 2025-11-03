/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.feraz.facturas.webcontrolfe.dao1;

import com.feraz.facturas.webcontrolfe.model.ErpFePercepcion;
import com.feraz.facturas.webcontrolfe.model.ErpFePercepcionId;
import java.util.List;

/**
 *
 * @author vavi
 */
public interface ErpFePercepcionDao {
    
     public ErpFePercepcionId save(ErpFePercepcion erpFePercepcion);
       public List<ErpFePercepcion> FindErpFePercepcion();
       public boolean delete(ErpFePercepcion erpFePercepcion);
       public boolean update(ErpFePercepcion erpFePercepcion);
        public int getMaxIdErpFePercepcion(ErpFePercepcionId id);
    
}
