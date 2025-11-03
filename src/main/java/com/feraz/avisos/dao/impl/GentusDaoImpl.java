/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.feraz.avisos.dao.impl;

import com.feraz.avisos.dao.GentusDao;
import com.feraz.avisos.model.Gentus;
import com.feraz.avisos.model.GentusId;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

/**
 *
 * @author 55555
 */
public class GentusDaoImpl implements GentusDao{
    
    private SessionFactory sessionFactory;

    @Override
    public GentusId save(Gentus gentus) {
        
       Session session = sessionFactory.openSession();
        Transaction transaccion = session.beginTransaction();
        GentusId id = null;
        try{
            
            id = (GentusId)session.save(gentus);
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
