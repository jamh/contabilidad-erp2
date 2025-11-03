
package com.feraz.contabilidad.sat.electronica.dao;

import com.feraz.contabilidad.sat.electronica.model.ErpSatPoliza;
import java.util.List;
/**
/**
 *
 * @author Feraz3
 */
public interface ErpSatPolizaDao {
    
       public List<ErpSatPoliza> FindErpSatPoliza(String compania,String pid);
}
