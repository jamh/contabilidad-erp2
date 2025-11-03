/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.feraz.viaticos.dao;

import com.feraz.viaticos.model.ErpFoliosPagoViaticos;
import com.feraz.viaticos.model.ErpFoliosPagoViaticosId;

/**
 *
 * @author vavi
 */
public interface ErpFoliosPagoViaticosDao {
    
      public ErpFoliosPagoViaticosId save(ErpFoliosPagoViaticos via);
      
      public boolean update (ErpFoliosPagoViaticos via);
      public boolean delete (ErpFoliosPagoViaticos via);
     // public int getMaxIdCxpFolios(ErpCxpFoliosId id);
      public int getMaxCxpFolios(ErpFoliosPagoViaticosId id);
      public boolean actualizaEstatusFolio(ErpFoliosPagoViaticos comp);
     // public boolean eliminaFactFolio(ErpCxpFolios comp);
    
}
