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
import com.feraz.contabilidad.nomina.model.ErpNomPoliza;
import com.feraz.contabilidad.nomina.model.ErpNomPolizaId;
public interface ErpNomPolizaDao {
    
     public ErpNomPolizaId save(ErpNomPoliza erpNomPoliza);

    public List<ErpNomPoliza> findErpNomPoliza();

    public boolean delete(ErpNomPoliza deleteErpNomPoliza);

    public boolean update(ErpNomPoliza updateErpNomPoliza);

    public int getMaxIdErpNomPoliza();
    
}
