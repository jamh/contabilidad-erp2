/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.feraz.intercos.dao.impl;

import com.feraz.intercos.dao.ErpWsIntercosDao;
import com.feraz.intercos.model.ErpWsIntercos;
import com.feraz.intercos.model.ErpWsIntercosId;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

/**
 *
 * @author vavi
 */
public class ErpWsIntercosDaoImpl implements ErpWsIntercosDao{
    
    private SessionFactory sessionFactory;

    @Override
    public ErpWsIntercosId save(ErpWsIntercos erpWsIntercos) {
        
           Session session = sessionFactory.openSession();
        Transaction transaccion = session.beginTransaction();
        ErpWsIntercosId id = null;
        try {

            id = (ErpWsIntercosId) session.save(erpWsIntercos);
            transaccion.commit();

        } catch (HibernateException e) {
            e.printStackTrace();
            transaccion.rollback();
            return null;
        } finally {

            session.close();
        }
        return id;
        
    }

    @Override
    public ErpWsIntercos findErpIntercos(String compania, String uuid) {
        
        ErpWsIntercos erpWsIntercos;
        Session session = sessionFactory.openSession();
        Transaction transaccion = session.beginTransaction();

        try {

            erpWsIntercos = (ErpWsIntercos) session.createQuery("from ErpWsIntercos where id.compania= :compania and id.uuid= :uuid")
                    .setString("compania", compania)
                    .setString("uuid", uuid)
                    .setMaxResults(1)
                    .uniqueResult();
            transaccion.commit();
        } catch (HibernateException e) {
            e.printStackTrace();
            transaccion.rollback();
            return null;
        } finally {

            session.close();

        }

        return erpWsIntercos;
        
    }

    @Override
    public boolean delete(ErpWsIntercos deleteErpEmisor) {
        
        
        Session session = sessionFactory.openSession();
        Transaction transaccion = session.beginTransaction();
        try {

            session.delete(deleteErpEmisor);
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

    @Override
    public boolean update(ErpWsIntercos updateErpEmisor) {
        
         Session session = sessionFactory.openSession();
        Transaction transaccion = session.beginTransaction();

        try {

            session.update(updateErpEmisor);
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
