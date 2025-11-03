/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.feraz.contabilidad.sat.electronica.dao;

import com.feraz.contabilidad.sat.electronica.model.ErpSatAuxiliarCtas;

/**
 *
 * @author Feraz3
 */
public interface ErpSatAuxiliarCtasDao {
    
    
           public ErpSatAuxiliarCtas findErpSatAuxiliarCtas(String compania,String pid);
    
}
