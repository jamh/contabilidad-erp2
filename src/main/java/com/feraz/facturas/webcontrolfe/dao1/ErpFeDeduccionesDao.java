/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.feraz.facturas.webcontrolfe.dao1;

import com.feraz.facturas.webcontrolfe.model.ErpFeDeducciones;
import com.feraz.facturas.webcontrolfe.model.ErpFeDeduccionesId;
import java.util.List;

/**
 *
 * @author vavi
 */
public interface ErpFeDeduccionesDao {
    
    public ErpFeDeduccionesId save(ErpFeDeducciones erpFeDeducciones);
       public ErpFeDeducciones findErpFeDeducciones(String compania, int numero);
       public boolean delete(ErpFeDeducciones erpFeDeducciones);
       public boolean update(ErpFeDeducciones erpFeDeducciones);
    
}
