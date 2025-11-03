/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.feraz.contabilidad.sat.transacciones.dao;

import com.feraz.contabilidad.sat.transacciones.model.ErpSatTransaccion;
import com.feraz.contabilidad.sat.transacciones.model.ErpSatTransaccionId;
import java.util.List;

/**
 *
 * @author Feraz3
 */
public interface ErpSatTransaccionDao {
    
     public ErpSatTransaccionId save(ErpSatTransaccion transacciones);
   
    public List<ErpSatTransaccion> findErpSatTransaccion(String compania);
    public boolean delete (ErpSatTransaccion deleteErpSatTransaccion);
    public boolean update (ErpSatTransaccion updateErpSatTransaccion);
    public int getMaxId(ErpSatTransaccionId id);
    public boolean actualizaTransaccionCXC(ErpSatTransaccion comp);
    public boolean actualizaTransaccionCXP(ErpSatTransaccion comp); 
    
}
