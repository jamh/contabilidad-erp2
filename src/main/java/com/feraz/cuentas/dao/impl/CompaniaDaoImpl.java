/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.feraz.cuentas.dao.impl;

import com.feraz.cuentas.dao.CompaniaDao;
import com.feraz.cuentas.model.Compania;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

/**
 *
 * @author Feraz3
 */
public class CompaniaDaoImpl implements CompaniaDao{

    private SessionFactory sessionFactory;
    
    public boolean updateEstructura(Compania compania) {
 
          Session session = sessionFactory.openSession();
        Transaction transaccion = session.beginTransaction();
        try {

            String hql = "update Compania set ESTRUCTURA = '" + compania.getEstructura() + "'  where id.compania = :compania";
            Query query = session.createQuery(hql);
            query.setString("compania", compania.getId().getCompania());
            int rowCount = query.executeUpdate();
            //    System.out.println("Rows affected: " + rowCount);

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
