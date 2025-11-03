/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.feraz.polizas3.dao;

import com.feraz.polizas3.model.OtrasArchivos;
import com.feraz.polizas3.model.OtrasArchivosId;
import java.util.List;

/**
 *
 * @author 55555
 */
public interface OtrasArchivosDao {
    
    public OtrasArchivosId save(OtrasArchivos otrasArchivos);
    public List<OtrasArchivos> findOtrasArchivos();
    public boolean delete (OtrasArchivos otrasArchivosDelete);
    public boolean update (OtrasArchivos updateOtrasArchivos);
    public int getMaxId(OtrasArchivosId id);
    
}
