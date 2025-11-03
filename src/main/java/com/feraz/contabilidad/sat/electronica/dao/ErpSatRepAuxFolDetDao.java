/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.feraz.contabilidad.sat.electronica.dao;

import com.feraz.contabilidad.sat.electronica.model.ErpSatRepAuxFolDet;
import java.util.List;

/**
 *
 * @author Feraz3
 */
public interface ErpSatRepAuxFolDetDao {
      public List<ErpSatRepAuxFolDet> FindErpSatRepAuxFolDe(String compania,String pid);
}
