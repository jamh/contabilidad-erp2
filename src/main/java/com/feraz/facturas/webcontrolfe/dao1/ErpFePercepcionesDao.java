/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.feraz.facturas.webcontrolfe.dao1;

import com.feraz.facturas.webcontrolfe.model.ErpFePercepciones;
import com.feraz.facturas.webcontrolfe.model.ErpFePercepcionesId;
import java.util.List;

/**
 *
 * @author vavi
 */
public interface ErpFePercepcionesDao {
    
    public ErpFePercepcionesId save(ErpFePercepciones erpFePercepciones);
       public ErpFePercepciones findErpFePercepciones(String compania, int numero);
       public boolean delete(ErpFePercepciones erpFePercepciones);
       public boolean update(ErpFePercepciones erpFePercepciones);
    
}
