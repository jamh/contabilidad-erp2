/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.feraz.cxp.dao.impl;

import com.feraz.cxp.dao.ErpFamiliaProveedorDao;
import com.feraz.cxp.model.ErpFamiliaProveedor;
import com.feraz.cxp.model.ErpFamiliaProveedorId;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

/**
 *
 * @author vavi
 */
public class ErpFamiliaProveedorDaoImpl implements ErpFamiliaProveedorDao{
    
    private SessionFactory sessionFactory;

    @Override
    public ErpFamiliaProveedorId save(ErpFamiliaProveedor erpFamiliaProveedor) {
        
               
        Session session = sessionFactory.openSession();
        Transaction transaccion = session.beginTransaction();
        ErpFamiliaProveedorId id = null;
        try {

            id = (ErpFamiliaProveedorId) session.save(erpFamiliaProveedor);
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
    public boolean delete(ErpFamiliaProveedor deleteErpFamiliaProveedor) {
        
          Session session = sessionFactory.openSession();
        Transaction transaccion = session.beginTransaction();

        try {

            session.delete(deleteErpFamiliaProveedor);
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

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }
    
    
    
}
