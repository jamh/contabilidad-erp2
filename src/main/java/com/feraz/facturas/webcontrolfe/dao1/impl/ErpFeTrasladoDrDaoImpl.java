/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.feraz.facturas.webcontrolfe.dao1.impl;

import com.feraz.facturas.webcontrolfe.dao1.ErpFeTrasladoDrDao;
import com.feraz.facturas.webcontrolfe.model.ErpFeTrasladoDr;
import com.feraz.facturas.webcontrolfe.model.ErpFeTrasladoDrId;
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
 * @author Ing. David Ortiz
 */
public class ErpFeTrasladoDrDaoImpl implements ErpFeTrasladoDrDao{
    
    private SessionFactory sessionFactory;

    @Override
    public ErpFeTrasladoDrId save(ErpFeTrasladoDr erpFeTrasladoDr) {
        Session session = sessionFactory.openSession();
        Transaction transaccion = session.beginTransaction();
        ErpFeTrasladoDrId id = null;
        try{
            
            id = (ErpFeTrasladoDrId)session.save(erpFeTrasladoDr);
            transaccion.commit();
            
        }catch (HibernateException e){
            e.printStackTrace();
            transaccion.rollback();
            return null;
        }finally{
            
            session.close();
        }
        return id;
    }

    @Override
    public List<ErpFeTrasladoDr> findErpFeTrasladoDr(ErpFeTrasladoDr erpFeTrasladoDr) {
        
        List<ErpFeTrasladoDr> erpFeTrasladoDrL;
        Session session = sessionFactory.openSession();
        Transaction transaccion = session.beginTransaction();

        try {

            erpFeTrasladoDrL = (List<ErpFeTrasladoDr>) session.createQuery("from ErpFeTrasladoDr").list();
            transaccion.commit();
        } catch (HibernateException e) {
            transaccion.rollback();
            return null;
        } finally {

            session.close();

        }

        return erpFeTrasladoDrL;
    }

    @Override
    public boolean delete(ErpFeTrasladoDr erpFeTrasladoDr) {
        
           Session session = sessionFactory.openSession();
        Transaction transaccion = session.beginTransaction();
        try{
          
            session.delete(erpFeTrasladoDr);
            transaccion.commit();
            
        }catch (HibernateException e){
            
            e.printStackTrace();
            transaccion.rollback();
            return false;
            
        }finally{
            
            session.close();
            
        }
        return true;
    }

    @Override
    public boolean update(ErpFeTrasladoDr erpFeTrasladoDr) {
        
         Session session = sessionFactory.openSession();
        Transaction transaccion = session.beginTransaction();
        
        try{
            
            session.update(erpFeTrasladoDr);
            transaccion.commit();
            
        }catch (HibernateException e){
            e.printStackTrace();
            transaccion.rollback();
            return false;
            
        }finally{
            
            session.close();
            
        }
        return true;
    }

    @Override
    public int getMaxIdfindErpFeTrasladoDr(ErpFeTrasladoDrId id) {
        
        Session session = sessionFactory.openSession();
        try {
            DetachedCriteria maxSec = DetachedCriteria.forClass(ErpFeTrasladoDr.class);


            Criteria criteria = maxSec.getExecutableCriteria(session);
            criteria.add(Restrictions.eq("id.compania", id.getCompania()));
            criteria.add(Restrictions.eq("id.numero", id.getNumero()));
            criteria.add(Restrictions.eq("id.idPago", id.getIdPago()));
            criteria.add(Restrictions.eq("id.idDocRel", id.getIdDocRel()));
            criteria.setProjection(Projections.max("id.id"));
            int campo = 0;
            List lista = criteria.list();
            if (lista.get(0) == null) {
                campo = 0;
            } else {
                campo = new Integer(lista.get(0).toString());
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
