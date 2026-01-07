/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.feraz.facturas.webcontrolfe.dao1.impl;

import com.feraz.facturas.webcontrolfe.dao1.ErpFeRetencionDrDao;
import com.feraz.facturas.webcontrolfe.model.ErpFeRetencionDr;
import com.feraz.facturas.webcontrolfe.model.ErpFeRetencionDrId;
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
public class ErpFeRetencionDrDaoImpl implements ErpFeRetencionDrDao{
    
    private SessionFactory sessionFactory;

    @Override
    public ErpFeRetencionDrId save(ErpFeRetencionDr erpFeRetencionDr) {
        
        Session session = sessionFactory.openSession();
        Transaction transaccion = session.beginTransaction();
        ErpFeRetencionDrId id = null;
        try{
            
            id = (ErpFeRetencionDrId)session.save(erpFeRetencionDr);
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
    public List<ErpFeRetencionDr> findErpFeRetencionDr(ErpFeRetencionDr erpFeRetencionDr) {
        
        List<ErpFeRetencionDr> erpFeRetencionDrL;
        Session session = sessionFactory.openSession();
        Transaction transaccion = session.beginTransaction();

        try {

            erpFeRetencionDrL = (List<ErpFeRetencionDr>) session.createQuery("from ErpFeRetencionDr").list();
            transaccion.commit();
        } catch (HibernateException e) {
            transaccion.rollback();
            return null;
        } finally {

            session.close();

        }

        return erpFeRetencionDrL;
    }

    @Override
    public boolean delete(ErpFeRetencionDr erpFeRetencionDr) {
           Session session = sessionFactory.openSession();
        Transaction transaccion = session.beginTransaction();
        try{
          
            session.delete(erpFeRetencionDr);
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
    public boolean update(ErpFeRetencionDr erpFeRetencionDr) {
        
        Session session = sessionFactory.openSession();
        Transaction transaccion = session.beginTransaction();
        
        try{
            
            session.update(erpFeRetencionDr);
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
    public int getMaxIdfindErpFeRetencionDr(ErpFeRetencionDrId id) {
        
        Session session = sessionFactory.openSession();
        try {
            DetachedCriteria maxSec = DetachedCriteria.forClass(ErpFeRetencionDr.class);


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
