/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.feraz.formula.dao;

import com.feraz.formula.model.AuCedulasDet;
import com.feraz.formula.model.AuCedulasDetId;
import java.util.List;

/**
 *
 * @author 55555
 */
public interface AuCedulasDetDao {
    
    public AuCedulasDetId save(AuCedulasDet auCedulasDet);

    public List<AuCedulasDet> findAuCedulasDet();

    public boolean delete(AuCedulasDet deleteAuCedulasDet);

    public boolean update(AuCedulasDet updateAuCedulasDet);
    public boolean deleteCedula(String compania, String cedula);
    public boolean deleteCedulaRenglon(String compania, String cedula,int renglon);
    
}
