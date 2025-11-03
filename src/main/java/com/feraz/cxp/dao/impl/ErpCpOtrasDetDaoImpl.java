/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.feraz.cxp.dao.impl;

import com.feraz.cxp.dao.ErpCpOtrasDetDao;
import com.feraz.cxp.model.ErpCpOtrasDet;
import com.feraz.cxp.model.ErpCpOtrasDetId;
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
public class ErpCpOtrasDetDaoImpl implements ErpCpOtrasDetDao{
    
    private SessionFactory sessionFactory;

    @Override
    public ErpCpOtrasDetId save(ErpCpOtrasDet erpCpOtrasDet) {
        
          Session session = sessionFactory.openSession();
        Transaction transaccion = session.beginTransaction();
        ErpCpOtrasDetId id = null;
        try {

            id = (ErpCpOtrasDetId) session.save(erpCpOtrasDet);
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
    public List<ErpCpOtrasDet> findErpCpOtrasDet(ErpCpOtrasDetId erpCpOtrasDet) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean delete(ErpCpOtrasDet erpCpOtrasDet) {
        
        Session session = sessionFactory.openSession();
        Transaction transaccion = session.beginTransaction();

        try {

            session.delete(erpCpOtrasDet);
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
    public boolean update(ErpCpOtrasDet erpCpOtrasDet) {
        
            Session session = sessionFactory.openSession();
        Transaction transaccion = session.beginTransaction();

        try {

            session.update(erpCpOtrasDet);
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
    public int getMaxErpCpOtrasDetId(ErpCpOtrasDetId id) {
        
        
          Session session = sessionFactory.openSession();
        try {
            DetachedCriteria maxSec = DetachedCriteria.forClass(ErpCpOtrasDet.class);

            Criteria criteria = maxSec.getExecutableCriteria(session);
            criteria.add(Restrictions.eq("id.compania", id.getCompania()));
            criteria.add(Restrictions.eq("id.id", id.getId()));

            criteria.setProjection(Projections.max("id.sec"));
            int campo = 0;
            List lista = criteria.list();
            if (lista.get(0) == null) {
                campo = 0;
            } else {
                campo = Integer.parseInt(lista.get(0).toString());
               // System.out.println("campo1"+campo);

            }
            campo++;
            return campo;

        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        } finally {
            session.close();
        }

        
    }

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }
    
    
    
    
}
