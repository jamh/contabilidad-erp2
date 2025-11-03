/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.feraz.facturas.webcontrolfe.dao1.impl;

/**
 *
 * @author Feraz3
 */
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import com.feraz.facturas.webcontrolfe.dao1.ErpFeErrDao;
import com.feraz.facturas.webcontrolfe.model.ErpFeErr;
import com.feraz.facturas.webcontrolfe.model.ErpFeErrId;
public class ErpFeErrDaoImpl implements ErpFeErrDao {
    
     private SessionFactory sessionFactory;

    public ErpFeErrId save(ErpFeErr erpFeErr) {
        
           Session session = sessionFactory.openSession();
        Transaction transaccion = session.beginTransaction();
        ErpFeErrId id = null;
        try{
            
            id = (ErpFeErrId)session.save(erpFeErr);
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
    
     public boolean update(ErpFeErr updateErpFeErr) {
        
         Session session = sessionFactory.openSession();
        Transaction transaccion = session.beginTransaction();

        try {

            session.update(updateErpFeErr);
            transaccion.commit();

        } catch (HibernateException e) {
            e.printStackTrace();
            transaccion.rollback();
            return false;

        } finally {

            session.close();

        }
        return true;
    }

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }
    
}
