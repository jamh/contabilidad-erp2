/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.feraz.contabilidad.sat.diot.dao.impl;

/**
 *
 * @author Administrador
 */

import com.feraz.contabilidad.sat.diot.dao.ConceptosDIOTDao;
import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Projections;

import com.feraz.contabilidad.sat.diot.model.ConceptosDIOT;
import com.feraz.contabilidad.sat.diot.model.ConceptosDIOTId;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import org.hibernate.Query;
import org.hibernate.criterion.Restrictions;


public class ConceptosDIOTDaoImpl implements ConceptosDIOTDao{

    
     private SessionFactory sessionFactory;
    
    public ConceptosDIOTId save(ConceptosDIOT conceptosDIOT) {
        
          Session session = sessionFactory.openSession();
        Transaction transaccion = session.beginTransaction();
        ConceptosDIOTId id = null;
        try{
            
            id = (ConceptosDIOTId)session.save(conceptosDIOT);
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

    public ConceptosDIOT FindConceptosDIOT(ConceptosDIOT conceptosDIOT) {
        
        
         ConceptosDIOT concepDiot;
        Session session = sessionFactory.openSession();
        Transaction transaccion = session.beginTransaction();
        
        try{
            
            concepDiot = (ConceptosDIOT)session.createQuery("from ConceptosDIOT where "
                  
                    + "id.compania = :compania "
                    + "and id.concepto = :concepto")
        .setString("compania",conceptosDIOT.getId().getCompania())
        .setString("concepto",conceptosDIOT.getId().getConcepto())
                    .setMaxResults(1).uniqueResult();
                   // .list();
            transaccion.commit();
            
        }catch (HibernateException e){
            e.printStackTrace();
            transaccion.rollback();
            return null;
        }finally{
            
            session.close();
           
        }
        return concepDiot;
        
    }

    public boolean delete(ConceptosDIOT deleteConceptosDIOT) {
        
           Session session = sessionFactory.openSession();
        Transaction transaccion = session.beginTransaction();
        try{
            
            session.delete(deleteConceptosDIOT);
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

    public boolean update(ConceptosDIOT updateConceptosDIOT) {
        Session session = sessionFactory.openSession();
        Transaction transaccion = session.beginTransaction();
        
        try{
            
            session.update(updateConceptosDIOT);
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
