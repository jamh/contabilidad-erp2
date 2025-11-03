/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.feraz.contabilidad.sat.electronica.dao;

import com.feraz.contabilidad.sat.electronica.model.ErpSatBalanza;
import java.util.List;

/**
 *
 * @author Feraz3
 */
public interface ErpSatBalanzaDao {
    
    
       public List<ErpSatBalanza> FindErpSatBalanza(String compania,String pid);
      
    
}
