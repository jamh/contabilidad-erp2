/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.feraz.facturas.webcontrolfe.dao1;

import com.feraz.facturas.webcontrolfe.model.ErpFeRetencionDr;
import com.feraz.facturas.webcontrolfe.model.ErpFeRetencionDrId;
import java.util.List;

/**
 *
 * @author Ing. David Ortiz
 */
public interface ErpFeRetencionDrDao {
    
    public ErpFeRetencionDrId save(ErpFeRetencionDr erpFeRetencionDr);
    public List<ErpFeRetencionDr> findErpFeRetencionDr(ErpFeRetencionDr erpFeRetencionDr);
    public boolean delete(ErpFeRetencionDr erpFeRetencionDr);
    public boolean update(ErpFeRetencionDr erpFeRetencionDr);
    public int getMaxIdfindErpFeRetencionDr(ErpFeRetencionDrId id);
    
}
