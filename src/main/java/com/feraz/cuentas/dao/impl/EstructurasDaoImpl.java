/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.feraz.cuentas.dao.impl;

/**
 *
 * @author Feraz3
 */
import com.feraz.cuentas.dao.EstructurasDao;
import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Projections;

import com.feraz.cuentas.model.Estructuras;
import com.feraz.cuentas.model.EstructurasId;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import org.hibernate.Query;
import org.hibernate.criterion.Restrictions;
public class EstructurasDaoImpl implements EstructurasDao{

    private SessionFactory sessionFactory;

    public EstructurasId save(Estructuras estructuras) {
        
         Session session = sessionFactory.openSession();
        Transaction transaccion = session.beginTransaction();
        EstructurasId id = null;
        try{
            
            id = (EstructurasId)session.save(estructuras);
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


    public List<Estructuras> findEstructuras(String estructura) {
        
          List<Estructuras> estructuras;
        Session session = sessionFactory.openSession();
        Transaction transaccion = session.beginTransaction();
        
        try{
            
            estructuras = (List<Estructuras>)session.createQuery("from Estructuras where "
                    + "id.estructura = :estructura ")
                        .setString("estructura",estructura)
                    .list();
            transaccion.commit();
            
        }catch (HibernateException e){
            e.printStackTrace();
            transaccion.rollback();
            return null;
        }finally{
            
            session.close();
           
        }
        return estructuras;
    }

    public boolean delete(Estructuras deleteEstructuras) {
        
        Session session = sessionFactory.openSession();
        Transaction transaccion = session.beginTransaction();
        try{
            
            session.delete(deleteEstructuras);
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


    public boolean update(Estructuras updateEstructuras) {
        
         Session session = sessionFactory.openSession();
        Transaction transaccion = session.beginTransaction();
        
        try{
            
            session.update(updateEstructuras);
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

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }
    
    
    
}
