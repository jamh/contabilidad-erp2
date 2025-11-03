/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.feraz.facturasext.dao.impl;

import com.feraz.facturasext.dao.ErpExtCobranzaDao;
import com.feraz.facturasext.model.ErpExtCobranza;
import com.feraz.facturasext.model.ErpExtCobranzaId;
import org.hibernate.SessionFactory;

/**
 *
 * @author Ing. David Ortiz García
 */
public class ErpExtCobranzaDaoImpl implements ErpExtCobranzaDao{
    
    private SessionFactory sessionFactory;

    @Override
    public ErpExtCobranzaId save(ErpExtCobranza erpExtCobranza) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean delete(ErpExtCobranza erpExtCobranza) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean update(ErpExtCobranza erpExtCobranza) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public int getMaxErpExtCobranza(ErpExtCobranzaId id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }
    
    
}
