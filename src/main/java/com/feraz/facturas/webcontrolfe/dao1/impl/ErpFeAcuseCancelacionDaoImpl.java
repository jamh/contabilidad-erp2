/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.feraz.facturas.webcontrolfe.dao1.impl;

import com.feraz.facturas.webcontrolfe.dao1.ErpFeAcuseCancelacionDao;
import com.feraz.facturas.webcontrolfe.model.ErpFeAcuseCancelacion;
import com.feraz.facturas.webcontrolfe.model.ErpFeAcuseCancelacionId;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

/**
 *
 * @author LENOVO
 */
public class ErpFeAcuseCancelacionDaoImpl implements ErpFeAcuseCancelacionDao{
    
    private SessionFactory sessionFactory;

    @Override
    public ErpFeAcuseCancelacionId save(ErpFeAcuseCancelacion erpFeAcuseCancelacion) {
        
         Session session = sessionFactory.openSession();
        Transaction transaccion = session.beginTransaction();
        ErpFeAcuseCancelacionId id = null;
        try {

            id = (ErpFeAcuseCancelacionId) session.save(erpFeAcuseCancelacion);
            transaccion.commit();

        } catch (HibernateException e) {
            transaccion.rollback();
            return null;
        } finally {

            session.close();
        }
        return id;
        
    }

    public SessionFactory getSessionFactory() {
        return sessionFactory;
    }

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }
    
    
    
}
