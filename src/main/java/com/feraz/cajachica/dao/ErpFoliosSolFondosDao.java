/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.feraz.cajachica.dao;

import com.feraz.cajachica.model.ErpFoliosSolFondos;
import com.feraz.cajachica.model.ErpFoliosSolFondosId;

/**
 *
 * @author LENOVO
 */
public interface ErpFoliosSolFondosDao {
    
    public ErpFoliosSolFondosId save(ErpFoliosSolFondos erpFoliosSolFondos);


    public boolean delete(ErpFoliosSolFondos erpFoliosSolFondos);

    public boolean update(ErpFoliosSolFondos erpFoliosSolFondos);
    
    public int getMaxFoliosSolFondosId(ErpFoliosSolFondosId id);
    
}
