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
import com.feraz.formula.dao.AuCedulasDetDao;
import com.feraz.formula.model.AuCedulasDet;
import com.feraz.formula.model.AuCedulasDetId;
import java.math.BigDecimal;
import java.util.List;
import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
public class AuCedulasDetDaoImpl implements AuCedulasDetDao{
    
    private SessionFactory sessionFactory;

    @Override
    public AuCedulasDetId save(AuCedulasDet auCedulasDet) {
        Session session = sessionFactory.openSession();
        Transaction transaccion = session.beginTransaction();
        AuCedulasDetId id = null;
        try {

            id = (AuCedulasDetId) session.save(auCedulasDet);
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
    public List<AuCedulasDet> findAuCedulasDet() {
        
         List<AuCedulasDet> auCedulasDet;
        Session session = sessionFactory.openSession();
        Transaction transaccion = session.beginTransaction();

        try {

            auCedulasDet = (List<AuCedulasDet>) session.createQuery("from AuCedulasDet").list();
            transaccion.commit();

        } catch (HibernateException e) {
            e.printStackTrace();
            transaccion.rollback();
            return null;
        } finally {

            session.close();

        }
        return auCedulasDet;
    }

    @Override
    public boolean delete(AuCedulasDet deleteAuCedulasDet) {
        
        Session session = sessionFactory.openSession();
        Transaction transaccion = session.beginTransaction();

        try {

            session.delete(deleteAuCedulasDet);
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
    public boolean update(AuCedulasDet updateAuCedulasDet) {
         Session session = sessionFactory.openSession();
        Transaction transaccion = session.beginTransaction();

        try {

            session.update(updateAuCedulasDet);
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
    
     public boolean deleteCedula(String compania, String cedula) {
        Session session = sessionFactory.openSession();
        Transaction transaccion = session.beginTransaction();
        
        System.out.println("borrando detalle");

        try {

            String hql = "delete from AuCedulasDet where id.compania = :compania and id.cedula = :cedula";
            Query query = session.createQuery(hql);
            query.setString("compania", compania);
            query.setString("cedula", cedula);
           // query.setBigDecimal("sec", sec);
            int rowCount = query.executeUpdate();
            //    System.out.println("Rows affected: " + rowCount);

            transaccion.commit();
            //session.delete(deleteErpDetConvertidor);
            //transaccion.commit();

        } catch (HibernateException e) {
            e.printStackTrace();
            transaccion.rollback();
            return true;
        } finally {

            session.close();

        }
        return true;

    }
     
      public boolean deleteCedulaRenglon(String compania, String cedula,int renglon) {
        Session session = sessionFactory.openSession();
        Transaction transaccion = session.beginTransaction();
        
        System.out.println("borrando detalle por renglon");

        try {

            String hql = "delete from AuCedulasDet where id.compania = :compania and id.cedula = :cedula and id.renglon = :renglon";
            Query query = session.createQuery(hql);
            query.setString("compania", compania);
            query.setString("cedula", cedula);
            query.setInteger("renglon", renglon);
           // query.setBigDecimal("sec", sec);
            int rowCount = query.executeUpdate();
            //    System.out.println("Rows affected: " + rowCount);

            transaccion.commit();
            //session.delete(deleteErpDetConvertidor);
            //transaccion.commit();

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
