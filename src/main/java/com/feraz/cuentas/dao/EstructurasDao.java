/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.feraz.cuentas.dao;

import com.feraz.cuentas.model.Estructuras;
import com.feraz.cuentas.model.EstructurasId;
import java.util.List;

/**
 *
 * @author Feraz3
 */
public interface EstructurasDao {
    
    public EstructurasId save(Estructuras estructuras);
   
    public List<Estructuras> findEstructuras(String estructura);
    public boolean delete (Estructuras deleteEstructuras);
    public boolean update (Estructuras updateEstructuras);
    
}
