/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.feraz.compensacion.dao;

import com.feraz.compensacion.model.ErpCompensaciones;
import com.feraz.compensacion.model.ErpCompensacionesId;
import java.util.List;

/**
 *
 * @author vavi
 */
public interface ErpCompensacionesDao {
    
    public ErpCompensacionesId save(ErpCompensaciones erpCompensaciones);

    public List<ErpCompensaciones> findErpCompensaciones();

    public boolean delete(ErpCompensaciones deleteErpCompensaciones);

    public boolean update(ErpCompensaciones updateErpCompensaciones);
    
    public int getMaxIdCompensacion(ErpCompensacionesId id) ;
}
