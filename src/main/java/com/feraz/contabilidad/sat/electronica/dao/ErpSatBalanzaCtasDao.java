/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.feraz.contabilidad.sat.electronica.dao;

import com.feraz.contabilidad.sat.electronica.model.ErpSatBalanzaCtas;
import com.feraz.contabilidad.sat.electronica.model.ErpSatBalanzaCtasId;
import java.util.List;

/**
 *
 * @author Feraz3
 */
public interface ErpSatBalanzaCtasDao {
    
    
       public List<ErpSatBalanzaCtas> FindErpSatBalanzaCtas(String compania,String pid);
      
    
}
