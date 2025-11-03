/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.feraz.cxp.dao.impl;

import com.feraz.cxp.dao.ErpNotasExtDao;
import com.feraz.cxp.model.ErpNotasExt;
import com.feraz.cxp.model.ErpNotasExtId;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

/**
 *
 * @author vavi
 */
public class ErpNotasExtDaoImpl implements ErpNotasExtDao{
    
    private SessionFactory sessionFactory;

    @Override
    public ErpNotasExtId save(ErpNotasExt erpNotasExt) {
        
          
        
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        
        ErpNotasExtId id = null;
        
        try{
             id = (ErpNotasExtId) session.save(erpNotasExt);
            transaction.commit();
                    
        }catch(HibernateException e){
           e.printStackTrace();
            
            
            transaction.rollback();
           return null;
        }finally{
        
            session.close();
        }
        
        return id;
        
    }

    @Override
    public boolean update(ErpNotasExt erpNotasExt) {
        
              Session session = sessionFactory.openSession();
          Transaction transaction = session.beginTransaction();
          
         
        try{
             session.update(erpNotasExt);
          
              transaction.commit();
        
        }catch(HibernateException e){
            e.printStackTrace();
            transaction.rollback();
            return false;
        
        }finally{
            
            session.close();
        
        }
        
        return true;
    }

    @Override
    public boolean eliminaNotas(ErpNotasExt erpNotasExt) {
        
         Session session = sessionFactory.openSession();
        Transaction transaccion = session.beginTransaction();

        try {

            session.delete(erpNotasExt);
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

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }
    
    
    
}
