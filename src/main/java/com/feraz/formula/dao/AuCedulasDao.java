/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.feraz.formula.dao;


import com.feraz.formula.model.AuCedulas;
import com.feraz.formula.model.AuCedulasId;
import java.util.List;

/**
 *
 * @author 55555
 */
public interface AuCedulasDao {
    
    public AuCedulasId save(AuCedulas auCedulas);

    public List<AuCedulas> findAuCedulas();

    public boolean delete(AuCedulas deleteAuCedulas);

    public boolean update(AuCedulas updateAuCedulas);
    
}
