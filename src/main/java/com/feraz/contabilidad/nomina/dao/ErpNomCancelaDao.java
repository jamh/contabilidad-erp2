/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.feraz.contabilidad.nomina.dao;

/**
 *
 * @author Feraz3
 */

import java.util.List;
import com.feraz.contabilidad.nomina.model.ErpNomCancela;
import com.feraz.contabilidad.nomina.model.ErpNomCancelaId;
public interface ErpNomCancelaDao {
    
    public ErpNomCancelaId save(ErpNomCancela erpNomCancela);

    public List<ErpNomCancela> findErpNomCancela();

    public boolean delete(ErpNomCancela deleteErpNomCancela);

    public boolean update(ErpNomCancela updateErpNomCancela);

    public int getMaxIdErpNomPoliza();
    
}
