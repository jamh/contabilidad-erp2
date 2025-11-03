/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.feraz.facturas.webcontrolfe.dao1.impl;

import com.feraz.facturas.webcontrolfe.dao1.ErpFeDeduccionDao;
import com.feraz.facturas.webcontrolfe.model.ErpFeDeduccion;
import com.feraz.facturas.webcontrolfe.model.ErpFeDeduccionId;
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
public class ErpFeDeduccionDaoImpl implements ErpFeDeduccionDao{
    
    private SessionFactory sessionFactory;

    @Override
    public ErpFeDeduccionId save(ErpFeDeduccion erpFeDeduccion) {
        
         
         Session session = sessionFactory.openSession();
        Transaction transaccion = session.beginTransaction();
        ErpFeDeduccionId id = null;
        try{
            
            id = (ErpFeDeduccionId)session.save(erpFeDeduccion);
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
    public List<ErpFeDeduccion> FindErpFeDeduccion() {
        
          List<ErpFeDeduccion> erpFeDeduccion;
        Session session = sessionFactory.openSession();
        Transaction transaccion = session.beginTransaction();

        try {

            erpFeDeduccion = (List<ErpFeDeduccion>) session.createQuery("from ErpFeDeduccion").list();
            transaccion.commit();
        } catch (HibernateException e) {
            transaccion.rollback();
            return null;
        } finally {

            session.close();

        }

        return erpFeDeduccion;
        
    }

    @Override
    public boolean delete(ErpFeDeduccion erpFeDeduccion) {
        
            Session session = sessionFactory.openSession();
        Transaction transaccion = session.beginTransaction();
        try{
          
            session.delete(erpFeDeduccion);
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
    public boolean update(ErpFeDeduccion erpFeDeduccion) {
        
          Session session = sessionFactory.openSession();
        Transaction transaccion = session.beginTransaction();
        
        try{
            
            session.update(erpFeDeduccion);
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
    public int getMaxIdErpFeDeduccion(ErpFeDeduccionId id) {
        
             
        Session session = sessionFactory.openSession();
        try {
            DetachedCriteria maxSec = DetachedCriteria.forClass(ErpFeDeduccion.class);


            Criteria criteria = maxSec.getExecutableCriteria(session);
            criteria.add(Restrictions.eq("id.compania", id.getCompania()));
            criteria.add(Restrictions.eq("id.numero", id.getNumero()));
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
