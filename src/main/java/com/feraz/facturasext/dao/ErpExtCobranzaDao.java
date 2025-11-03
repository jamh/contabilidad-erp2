/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.feraz.facturasext.dao;

import com.feraz.facturasext.model.ErpExtCobranza;
import com.feraz.facturasext.model.ErpExtCobranzaId;

/**
 *
 * @author Ing. David Ortiz García
 */
public interface ErpExtCobranzaDao {
    
       public ErpExtCobranzaId save(ErpExtCobranza erpExtCobranza);
       public boolean delete(ErpExtCobranza erpExtCobranza);
       public boolean update(ErpExtCobranza erpExtCobranza);
       public int getMaxErpExtCobranza(ErpExtCobranzaId id);
    
}
