/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.feraz.contabilidad.nomina.dao.impl;

/**
 *
 * @author Feraz3
 */

import com.feraz.contabilidad.nomina.dao.ErpNomCancelaDao;
import com.feraz.contabilidad.nomina.model.ErpNomCancela;
//import com.feraz.contabilidad.convertidor.model.ErpCatConvertidor2;
import com.feraz.contabilidad.nomina.model.ErpNomCancelaId;
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


public class ErpNomCancelaDaoImpl implements ErpNomCancelaDao{
    
     private SessionFactory sessionFactory;

    public ErpNomCancelaId save(ErpNomCancela erpNomCancela) {
        
         Session session = sessionFactory.openSession();
        Transaction transaccion = session.beginTransaction();
        ErpNomCancelaId id = null;
        try {

            id = (ErpNomCancelaId) session.save(erpNomCancela);
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

    public List<ErpNomCancela> findErpNomCancela() {
        
          List<ErpNomCancela> erpNomCancela;
        Session session = sessionFactory.openSession();
        Transaction transaccion = session.beginTransaction();

        try {

            erpNomCancela = (List<ErpNomCancela>) session.createQuery("from ErpNomCancela").list();
            transaccion.commit();

        } catch (HibernateException e) {
            e.printStackTrace();
            transaccion.rollback();
            return null;
        } finally {

            session.close();

        }
        return erpNomCancela;
    }

    public boolean delete(ErpNomCancela deleteErpNomCancela) {
        
          Session session = sessionFactory.openSession();
        Transaction transaccion = session.beginTransaction();

        try {

            session.delete(deleteErpNomCancela);
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

    public boolean update(ErpNomCancela updateErpNomCancela) {
        
          Session session = sessionFactory.openSession();
        Transaction transaccion = session.beginTransaction();

        try {

            session.update(updateErpNomCancela);
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

    public int getMaxIdErpNomPoliza() {
        
        Session session = sessionFactory.openSession();
        try {
            DetachedCriteria maxSec = DetachedCriteria.forClass(ErpNomCancela.class);

            Criteria criteria = maxSec.getExecutableCriteria(session);
            //criteria.add(Restrictions.eq("id.compania", id.getCompania()));
//            criteria.add(Restrictions.eq("id.origen", id.getOrigen()));
//            criteria.add(Restrictions.eq("id.idconcgasto", id.getIdconcgasto()));
            criteria.setProjection(Projections.max("id.id"));
            int id = 0;
            List lista = criteria.list();
            if (lista.get(0) == null) {
                id = 0;
            } else {
                id = new Integer(lista.get(0).toString());
            }
            id++;
//
            return id;
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
