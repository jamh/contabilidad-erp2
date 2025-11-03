/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.feraz.cuentas.dao;

import com.feraz.cuentas.model.Cuentas;
import com.feraz.cuentas.model.CuentasId;
import java.util.List;

/**
 *
 * @author Feraz3
 */
public interface CuentasDao {
    
    public CuentasId save(Cuentas cuentas);
   
    public List<Cuentas> findCuentasporCompania(String estructura);
    public boolean delete (Cuentas deleteCuentas);
    public boolean update (Cuentas updateCuentas);
    public Cuentas findCuenta(String estructura, String cuentaAlias);
    public Cuentas findCuentaUpdate(Cuentas cta);
    public boolean updatePorCtaAlias(Cuentas cuentas);
    public boolean updatePorAfectable(String compania, String ctaPadre);
     public boolean updateAtributosCta(Cuentas cuentas);
    public int getMaxId(CuentasId id);
    
}
