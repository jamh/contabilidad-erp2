/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.feraz.viaticos.dao.impl;

/**
 *
 * @author FERAZ-14
 */
import com.feraz.viaticos.dao.ViComisionesDao;
import com.feraz.viaticos.model.ViComisiones;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;


public class ViComisionesDaoImpl implements ViComisionesDao{
    
    private SessionFactory sessionFactory;

    @Override
    public boolean updateErpViaticosCash(ViComisiones comisiones) {
        
           Session session = sessionFactory.openSession();
        Transaction transaccion = session.beginTransaction();
        
        
        
        
        try {
            
                  
                   String hql = "update ViComisiones set fechaCashFlow = :fechaCashFlow  where compania = :compania and comision = :comision";
                    Query query = session.createQuery(hql);
                    query.setString("compania", comisiones.getId().getCompania());
                    query.setString("comision", comisiones.getId().getComision());
                    query.setDate("fechaCashFlow",comisiones.getFechaCashFlow());
                    int rowCount = query.executeUpdate();
                 
                  
           

                   
              
            transaccion.commit();

        } catch (HibernateException e) {
            e.printStackTrace();
            transaccion.rollback();
            return false;
        } catch (Exception e2) {
            e2.printStackTrace();
            transaccion.rollback();
        } finally {
            session.close();
        }
        return true;
    }

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }
    
    
    
}
