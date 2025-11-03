/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.feraz.contabilidad.saldos.dao;


import com.feraz.contabilidad.saldos.model.Saldos;
import com.feraz.contabilidad.saldos.model.SaldosId;
import java.util.List;

/**
 *
 * @author Ing. JAMH
 */
public interface SaldosDao {
    
    public SaldosId save(Saldos saldos);

    public List<Saldos> findSaldos();

    public boolean delete(Saldos deleteSaldos);

    public boolean update(Saldos updateSaldos);

    
}
