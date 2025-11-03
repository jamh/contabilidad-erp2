/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.feraz.contabilidad.sat.electronica.dao;

import com.feraz.contabilidad.sat.electronica.model.ErpSatAuxiliarCtasDet;
import java.util.List;

/**
 *
 * @author Feraz3
 */
public interface ErpSatAuxiliarCtasDetDao {
     public List<ErpSatAuxiliarCtasDet> FindErpSatAuxiliarCtasDet(String compania,String pid);
     public List<ErpSatAuxiliarCtasDet> FindErpSatAuxiliarCtasDet2(String compania, String pid, String cuenta);
}
