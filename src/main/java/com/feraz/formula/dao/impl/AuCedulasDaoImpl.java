/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.feraz.formula.dao.impl;

/**
 *
 * @author 55555
 */
import com.feraz.formula.dao.AuCedulasDao;
import com.feraz.formula.model.AuCedulas;
import com.feraz.formula.model.AuCedulasId;
import java.math.BigDecimal;
import java.util.List;
import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
public class AuCedulasDaoImpl implements AuCedulasDao{
    
    private SessionFactory sessionFactory;

    @Override
    public AuCedulasId save(AuCedulas auCedulas) {
         Session session = sessionFactory.openSession();
        Transaction transaccion = session.beginTransaction();
        AuCedulasId id = null;
        try {

            id = (AuCedulasId) session.save(auCedulas);
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
    public List<AuCedulas> findAuCedulas() {
        
         List<AuCedulas> auCedulas;
        Session session = sessionFactory.openSession();
        Transaction transaccion = session.beginTransaction();

        try {

            auCedulas = (List<AuCedulas>) session.createQuery("from AuCedulas").list();
            transaccion.commit();

        } catch (HibernateException e) {
            e.printStackTrace();
            transaccion.rollback();
            return null;
        } finally {

            session.close();

        }
        return auCedulas;
        
    }

    @Override
    public boolean delete(AuCedulas deleteAuCedulas) {
        
         Session session = sessionFactory.openSession();
        Transaction transaccion = session.beginTransaction();

        try {

            session.delete(deleteAuCedulas);
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
    public boolean update(AuCedulas updateAuCedulas) {
        Session session = sessionFactory.openSession();
        Transaction transaccion = session.beginTransaction();

        try {

            session.update(updateAuCedulas);
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
