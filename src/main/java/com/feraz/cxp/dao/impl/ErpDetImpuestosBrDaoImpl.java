/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.feraz.cxp.dao.impl;

import com.feraz.cxp.dao.ErpDetImpuestosBrDao;
import com.feraz.cxp.model.ErpDetImpuestosBr;
import com.feraz.cxp.model.ErpDetImpuestosBrId;
import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;

/**
 *
 * @author FERAZ-14
 */
public class ErpDetImpuestosBrDaoImpl implements ErpDetImpuestosBrDao{
    
     private SessionFactory sessionFactory;

    @Override
    public ErpDetImpuestosBrId save(ErpDetImpuestosBr erpDetImpuestosBr) {
        
           
          Session session = sessionFactory.openSession();
        Transaction transaccion = session.beginTransaction();
        ErpDetImpuestosBrId id = null;
        try {

            id = (ErpDetImpuestosBrId) session.save(erpDetImpuestosBr);
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
    public boolean update(ErpDetImpuestosBr erpDetImpuestosBr) {
        Session session = sessionFactory.openSession();
        Transaction transaccion = session.beginTransaction();

        try {

            session.update(erpDetImpuestosBr);
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
    public boolean delete(ErpDetImpuestosBr erpDetImpuestosBr) {
        Session session = sessionFactory.openSession();
        Transaction transaccion = session.beginTransaction();

        try {

            session.delete(erpDetImpuestosBr);
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
