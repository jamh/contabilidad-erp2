/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.feraz.facturas.webcontrolfe.dao1;

import com.feraz.facturas.webcontrolfe.model.ErpFeTrasLocales;
import com.feraz.facturas.webcontrolfe.model.ErpFeTrasLocalesId;
import java.util.List;

/**
 *
 * @author vavi
 */
public interface ErpFeTrasLocalesDao {
    
     public ErpFeTrasLocalesId save(ErpFeTrasLocales erpFeTrasLocales);
    public List<ErpFeTrasLocales> FindErpFeTrasLocales(String compania,int numero);
    public boolean delete (ErpFeTrasLocales erpFeTrasLocales);
    public boolean update (ErpFeTrasLocales erpFeTrasLocales);
    public int getMAxIdErpFeRetencionCompl(ErpFeTrasLocalesId id);
    
}
