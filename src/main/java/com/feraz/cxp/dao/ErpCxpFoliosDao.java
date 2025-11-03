/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.feraz.cxp.dao;

import com.feraz.cxp.model.ErpCxpFolios;
import com.feraz.cxp.model.ErpCxpFoliosId;

/**
 *
 * @author Feraz3
 */
public interface ErpCxpFoliosDao {
    
      public ErpCxpFoliosId save(ErpCxpFolios erpCxpFolios);
      
      public boolean update (ErpCxpFolios erpCxpFolios);
      public int getMaxIdCxpFolios(ErpCxpFoliosId id);
      public int getMaxCxpFolios(ErpCxpFoliosId id);
      public boolean actualizaEstatusFolio(ErpCxpFolios comp);
      public boolean eliminaFactFolio(ErpCxpFolios comp);
    
}
