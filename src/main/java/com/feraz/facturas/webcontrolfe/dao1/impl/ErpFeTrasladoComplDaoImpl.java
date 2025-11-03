/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.feraz.facturas.webcontrolfe.dao1.impl;

import com.feraz.facturas.webcontrolfe.dao1.ErpFeTrasladoComplDao;
import com.feraz.facturas.webcontrolfe.model.ErpFePercepcion;
import com.feraz.facturas.webcontrolfe.model.ErpFeTrasladoCompl;
import com.feraz.facturas.webcontrolfe.model.ErpFeTrasladoComplId;
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
public class ErpFeTrasladoComplDaoImpl implements ErpFeTrasladoComplDao{
    
    private SessionFactory sessionFactory;

    @Override
    public ErpFeTrasladoComplId save(ErpFeTrasladoCompl erpFeTrasladoCompl) {
        
        Session session = sessionFactory.openSession();
        Transaction transaccion = session.beginTransaction();
        ErpFeTrasladoComplId id = null;
        try{
            
            id = (ErpFeTrasladoComplId)session.save(erpFeTrasladoCompl);
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
    public List<ErpFeTrasladoCompl> findErpFeTrasladoCompl(ErpFeTrasladoCompl erpFeTrasladoCompl) {
        
          List<ErpFeTrasladoCompl> erpFeTrasladoComplL;
        Session session = sessionFactory.openSession();
        Transaction transaccion = session.beginTransaction();

        try {

            erpFeTrasladoComplL = (List<ErpFeTrasladoCompl>) session.createQuery("from ErpFeTrasladoCompl").list();
            transaccion.commit();
        } catch (HibernateException e) {
            transaccion.rollback();
            return null;
        } finally {

            session.close();

        }

        return erpFeTrasladoComplL;
        
    }

    @Override
    public boolean delete(ErpFeTrasladoCompl erpFeTrasladoCompl) {
        
           Session session = sessionFactory.openSession();
        Transaction transaccion = session.beginTransaction();
        try{
          
            session.delete(erpFeTrasladoCompl);
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
    public boolean update(ErpFeTrasladoCompl erpFeTrasladoCompl) {
        
        Session session = sessionFactory.openSession();
        Transaction transaccion = session.beginTransaction();
        
        try{
            
            session.update(erpFeTrasladoCompl);
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
    public int getMaxIdErpFeTrasladoCompl(ErpFeTrasladoComplId id) {
          
        Session session = sessionFactory.openSession();
        try {
            DetachedCriteria maxSec = DetachedCriteria.forClass(ErpFeTrasladoCompl.class);


            Criteria criteria = maxSec.getExecutableCriteria(session);
            criteria.add(Restrictions.eq("id.compania", id.getCompania()));
            criteria.add(Restrictions.eq("id.numero", id.getNumero()));
            criteria.add(Restrictions.eq("id.idPago", id.getIdPago()));
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
