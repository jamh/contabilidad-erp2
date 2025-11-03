/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.feraz.facturas.webcontrolfe.dao1;
import com.feraz.facturas.webcontrolfe.model.ErpFeOtroPago;
import com.feraz.facturas.webcontrolfe.model.ErpFeOtroPagoId;
/**
 *
 * @author FERAZ-14
 */
public interface ErpFeOtroPagoDao {
    
    public ErpFeOtroPagoId save(ErpFeOtroPago erpFeOtroPago);
       public ErpFeOtroPago findErpFeOtroPago(String compania, int numero);
       public boolean delete(ErpFeOtroPago erpFeOtroPago);
       public boolean update(ErpFeOtroPago erpFeOtroPago);
       public int getMaxIdErpFeOtroPago(ErpFeOtroPagoId id);
    
}
