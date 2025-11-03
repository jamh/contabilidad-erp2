/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.feraz.contabilidad.sat.electronica.dao;

import com.feraz.contabilidad.sat.electronica.model.ErpSatCatalogo;
import java.util.List;

/**
 *
 * @author Administrador
 */
public interface  ErpSatCatalogoDao {
    public ErpSatCatalogo findErpSatCatalogo(String compania,String pid);
    
}
