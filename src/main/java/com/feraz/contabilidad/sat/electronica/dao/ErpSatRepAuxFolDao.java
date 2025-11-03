/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.feraz.contabilidad.sat.electronica.dao;

import com.feraz.contabilidad.sat.electronica.model.ErpSatRepAuxFol;

/**
 *
 * @author Feraz3
 */
public interface ErpSatRepAuxFolDao {
    
           public ErpSatRepAuxFol findErpSatRepAuxFol(String compania,String pid);

}
