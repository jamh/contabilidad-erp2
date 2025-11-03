/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.feraz.compensacion.dao.impl;

import com.feraz.compensacion.dao.ErpCompensacionesDetDao;
import com.feraz.compensacion.model.ErpCompensacionesDet;
import com.feraz.compensacion.model.ErpCompensacionesDetId;
import java.util.List;
import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;

/**
 *
 * @author vavi
 */
public class ErpCompensacionesDetDaoImpl implements ErpCompensacionesDetDao{
    
        private SessionFactory sessionFactory;


    @Override
    public ErpCompensacionesDetId save(ErpCompensacionesDet erpCompensacionesDet) {
        
          Session session = sessionFactory.openSession();
        Transaction transaccion = session.beginTransaction();
        ErpCompensacionesDetId id = null;
        try {

            id = (ErpCompensacionesDetId) session.save(erpCompensacionesDet);
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
    public List<ErpCompensacionesDet> findErpCompensacionesDet() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean delete(ErpCompensacionesDet deleteErpCompensacionesDet) {
        
           Session session = sessionFactory.openSession();
        Transaction transaccion = session.beginTransaction();

        try {

            session.delete(deleteErpCompensacionesDet);
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
    public boolean update(ErpCompensacionesDet updateErpCompensacionesDet) {
        
         Session session = sessionFactory.openSession();
        Transaction transaccion = session.beginTransaction();

        try {

            session.update(updateErpCompensacionesDet);
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
