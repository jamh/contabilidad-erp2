/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.feraz.cxp.dao.impl;

import com.feraz.cxp.dao.ErpCpConcPagoDao;
import com.feraz.cxp.model.ErpCpConcPago;
import com.feraz.cxp.model.ErpCpConcPagoId;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

/**
 *
 * @author 55555
 */
public class ErpCpConcPagoDaoImpl implements ErpCpConcPagoDao{
    
    private SessionFactory sessionFactory;

    @Override
    public ErpCpConcPagoId save(ErpCpConcPago erpCpConcPago) {
        
          Session session = sessionFactory.openSession();
        Transaction transaccion = session.beginTransaction();
        ErpCpConcPagoId id = null;
        try {

            id = (ErpCpConcPagoId) session.save(erpCpConcPago);
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

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public boolean delete(ErpCpConcPago erpCpConcPago) {
    
        Session session = sessionFactory.openSession();
        Transaction transaccion = session.beginTransaction();

        try {

            session.delete(erpCpConcPago);
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
    
    
}
