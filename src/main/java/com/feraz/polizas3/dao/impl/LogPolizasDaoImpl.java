/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.feraz.polizas3.dao.impl;

/**
 *
 * @author Feraz3
 */

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Projections;
import com.feraz.polizas3.dao.DetPolizasDao;
import com.feraz.polizas3.dao.LogPolizasDao;
import com.feraz.polizas3.model.LogPolizas;
import com.feraz.polizas3.model.LogPolizasId;
import java.util.List;
import org.hibernate.Query;
import org.hibernate.criterion.Restrictions;

public class LogPolizasDaoImpl implements LogPolizasDao {
    
     private SessionFactory sessionFactory;

    public LogPolizasId save(LogPolizas logPolizas) {
        
        
        Session session = sessionFactory.openSession();
        Transaction transaccion = session.beginTransaction();
        LogPolizasId id = null;
        try{
            
            id = (LogPolizasId)session.save(logPolizas);
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

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }
    
    
    
}
