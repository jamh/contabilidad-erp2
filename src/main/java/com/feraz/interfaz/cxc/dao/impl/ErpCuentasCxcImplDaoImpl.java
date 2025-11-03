/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.feraz.interfaz.cxc.dao.impl;

import com.feraz.interfaz.cxc.dao.ErpCuentasCxcImplDao;
import com.feraz.interfaz.cxc.model.ErpCuentasCxcImpl;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

/**
 *
 * @author vavi
 */
public class ErpCuentasCxcImplDaoImpl implements ErpCuentasCxcImplDao{
    
    private SessionFactory sessionFactory;

    @Override
    public boolean update(ErpCuentasCxcImpl ErpCuentasCxcImpl) {
            Session session = sessionFactory.openSession();
        Transaction transaccion = session.beginTransaction();

        try {

            session.update(ErpCuentasCxcImpl);
            transaccion.commit();

        } catch (HibernateException e) {
            e.printStackTrace();
            transaccion.rollback();
            return false;

        } finally {

            session.close();

        }
        return true;
    }

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }
    
    
    
}
