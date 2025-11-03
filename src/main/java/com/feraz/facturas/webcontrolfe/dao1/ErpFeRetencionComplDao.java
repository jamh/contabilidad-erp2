/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.feraz.facturas.webcontrolfe.dao1;
import com.feraz.facturas.webcontrolfe.model.ErpFeRetencionCompl;
import com.feraz.facturas.webcontrolfe.model.ErpFeRetencionComplId;
import java.util.List;

/**
 * @author Armando
 */
public interface ErpFeRetencionComplDao {
    
    public ErpFeRetencionComplId save(ErpFeRetencionCompl erpFeRetencionCompl);
    public List<ErpFeRetencionCompl> FindErpFeRetencionCompl();
    public boolean delete (ErpFeRetencionCompl erpFeRetencionCompl);
    public boolean update (ErpFeRetencionCompl erpFeRetencionCompl);
    public int getMAxIdErpFeRetencionCompl(ErpFeRetencionComplId id);
}
