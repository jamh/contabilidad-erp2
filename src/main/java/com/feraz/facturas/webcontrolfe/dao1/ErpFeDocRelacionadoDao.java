/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.feraz.facturas.webcontrolfe.dao1;

import com.feraz.facturas.webcontrolfe.model.ErpFeDocRelacionado;
import com.feraz.facturas.webcontrolfe.model.ErpFeDocRelacionadoId;
import java.util.List;

/**
 *
 * @author vavi
 */
public interface ErpFeDocRelacionadoDao {
    
     public ErpFeDocRelacionadoId save(ErpFeDocRelacionado erpFeDocRelacionado);
       public List<ErpFeDocRelacionado> FindErpFeDocRelacionado();
       public boolean delete(ErpFeDocRelacionado erpFeDocRelacionado);
       public boolean update(ErpFeDocRelacionado erpFeDocRelacionado);
        public int getMaxIdErpFeDocRelacionado(ErpFeDocRelacionadoId id);
    
}
