/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.feraz.contabilidad.sat.electronica.dao;

import com.feraz.contabilidad.sat.electronica.model.ErpSatPolizas;
/**
/**
 *
 * @author Ing. JAMH
 */
public interface ErpSatPolizasDao {
    
       public ErpSatPolizas findErpSatPolizas(String compania,String pid);
}
