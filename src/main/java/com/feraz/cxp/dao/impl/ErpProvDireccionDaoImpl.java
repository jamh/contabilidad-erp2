/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.feraz.cxp.dao.impl;

import com.feraz.cxp.dao.ErpProvDireccionDao;
import com.feraz.cxp.model.ErpProvDireccion;
import com.feraz.cxp.model.ErpProvDireccionId;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

/**
 *
 * @author 55555
 */
public class ErpProvDireccionDaoImpl implements ErpProvDireccionDao{
    
    private SessionFactory sessionFactory;

    @Override
    public ErpProvDireccionId save(ErpProvDireccion erpProvDireccion) {
        
        Session session = sessionFactory.openSession();
        Transaction transaccion = session.beginTransaction();
        ErpProvDireccionId id = null;
        try {

            id = (ErpProvDireccionId) session.save(erpProvDireccion);
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
    public boolean delete(ErpProvDireccion deleteErpProvDireccion) {
        
        
         Session session = sessionFactory.openSession();
        Transaction transaccion = session.beginTransaction();

        try {

            session.delete(deleteErpProvDireccion);
            transaccion.commit();

        } catch (HibernateException e) {
            e.printStackTrace();
            transaccion.rollback();
            return true;
        } finally {

            session.close();

        }
        return true;
        
    }

    @Override
    public boolean update(ErpProvDireccion updateErpProvDireccion) {
        
        Session session = sessionFactory.openSession();
        Transaction transaccion = session.beginTransaction();

        try {

            session.update(updateErpProvDireccion);
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
