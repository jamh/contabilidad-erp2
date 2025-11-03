/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.feraz.facturas.webcontrolfe.dao1.impl;

import com.feraz.facturas.webcontrolfe.dao1.ErpFeRetencionComplDao;
import com.feraz.facturas.webcontrolfe.model.ErpFeRetencionCompl;
import com.feraz.facturas.webcontrolfe.model.ErpFeRetencionComplId;
import com.feraz.facturas.webcontrolfe.model.ErpFeTrasladoCompl;
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
public class ErpFeRetencionComplDaoImpl implements ErpFeRetencionComplDao {
    
    private SessionFactory sessionFactory;

    @Override
    public ErpFeRetencionComplId save(ErpFeRetencionCompl erpFeRetencionCompl) {
        
          Session session = sessionFactory.openSession();
        Transaction transaccion = session.beginTransaction();
        ErpFeRetencionComplId id = null;
        try{
            
            id = (ErpFeRetencionComplId)session.save(erpFeRetencionCompl);
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
    public List<ErpFeRetencionCompl> FindErpFeRetencionCompl() {
        
          List<ErpFeRetencionCompl> erpFeRetencionCompl;
        Session session = sessionFactory.openSession();
        Transaction transaccion = session.beginTransaction();

        try {

            erpFeRetencionCompl = (List<ErpFeRetencionCompl>) session.createQuery("from ErpFeRetencionCompl").list();
            transaccion.commit();
        } catch (HibernateException e) {
            transaccion.rollback();
            return null;
        } finally {

            session.close();

        }

        return erpFeRetencionCompl;
        
    }

    @Override
    public boolean delete(ErpFeRetencionCompl erpFeRetencionCompl) {
        
        
           Session session = sessionFactory.openSession();
        Transaction transaccion = session.beginTransaction();
        try{
          
            session.delete(erpFeRetencionCompl);
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
    public boolean update(ErpFeRetencionCompl erpFeRetencionCompl) {
        
         Session session = sessionFactory.openSession();
        Transaction transaccion = session.beginTransaction();
        
        try{
            
            session.update(erpFeRetencionCompl);
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
    public int getMAxIdErpFeRetencionCompl(ErpFeRetencionComplId id) {
        
          Session session = sessionFactory.openSession();
        try {
            DetachedCriteria maxSec = DetachedCriteria.forClass(ErpFeRetencionCompl.class);


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
