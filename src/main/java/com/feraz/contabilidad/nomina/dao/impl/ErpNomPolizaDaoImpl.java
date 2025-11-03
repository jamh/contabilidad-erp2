/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.feraz.contabilidad.nomina.dao.impl;

/**
 *
 * @author Feraz3
 */

import com.feraz.contabilidad.nomina.dao.ErpNomPolizaDao;
import com.feraz.contabilidad.nomina.model.ErpNomPoliza;
//import com.feraz.contabilidad.convertidor.model.ErpCatConvertidor2;
import com.feraz.contabilidad.nomina.model.ErpNomPolizaId;
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


public class ErpNomPolizaDaoImpl implements ErpNomPolizaDao{
    
     private SessionFactory sessionFactory;

    public ErpNomPolizaId save(ErpNomPoliza erpNomPoliza) {
        System.out.println("llegando a guardar");
         Session session = sessionFactory.openSession();
        Transaction transaccion = session.beginTransaction();
        ErpNomPolizaId id = null;
        try {

            id = (ErpNomPolizaId) session.save(erpNomPoliza);
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

    public List<ErpNomPoliza> findErpNomPoliza() {
        
         List<ErpNomPoliza> erpNomPoliza;
        Session session = sessionFactory.openSession();
        Transaction transaccion = session.beginTransaction();

        try {

            erpNomPoliza = (List<ErpNomPoliza>) session.createQuery("from ErpNomPoliza").list();
            transaccion.commit();

        } catch (HibernateException e) {
            e.printStackTrace();
            transaccion.rollback();
            return null;
        } finally {

            session.close();

        }
        return erpNomPoliza;
        
    }

    public boolean delete(ErpNomPoliza deleteErpNomPoliza) {
        
        Session session = sessionFactory.openSession();
        Transaction transaccion = session.beginTransaction();

        try {

            session.delete(deleteErpNomPoliza);
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

    public boolean update(ErpNomPoliza updateErpNomPoliza) {
        
        Session session = sessionFactory.openSession();
        Transaction transaccion = session.beginTransaction();

        try {

            session.update(updateErpNomPoliza);
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
        
        System.out.println("Obteniendo el maximo");
        
        try {
            DetachedCriteria maxSec = DetachedCriteria.forClass(ErpNomPoliza.class);

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
