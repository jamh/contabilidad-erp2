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
import com.feraz.facturas.webcontrolfe.model.ErpFeReceptor;
import com.feraz.facturas.webcontrolfe.model.ErpFeReceptorId;
import com.feraz.facturas.webcontrolfe.dao1.ErpFeReceptorDao;
import java.util.List;
public class ErpFeReceptorDaoImpl implements ErpFeReceptorDao{
    
    private SessionFactory sessionFactory;

    public ErpFeReceptorId save(ErpFeReceptor erpFeReceptor) {
        
        Session session = sessionFactory.openSession();
        Transaction transaccion = session.beginTransaction();
        ErpFeReceptorId id = null;
        try{
            
            id = (ErpFeReceptorId)session.save(erpFeReceptor);
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

    public ErpFeReceptor findErpFeReceptor( String compania, int numero) {
        
        
         ErpFeReceptor erpFeReceptor;
        Session session = sessionFactory.openSession();
        Transaction transaccion = session.beginTransaction();

        try {

            erpFeReceptor = (ErpFeReceptor) session.createQuery("from ErpFeReceptor where id.compania = :compania and id.numero = :numero")
                    .setString("compania", compania)
                     .setInteger("numero",new Integer(numero))
                     .setMaxResults(1)
                    .uniqueResult();
            transaccion.commit();
        } catch (HibernateException e) {
            transaccion.rollback();
            return null;
        } finally {

            session.close();

        }

        return erpFeReceptor;
    }

    public boolean delete(ErpFeReceptor deleteErpFeReceptor) {

          Session session = sessionFactory.openSession();
        Transaction transaccion = session.beginTransaction();
        try{
          
            session.delete(deleteErpFeReceptor);
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

    public boolean update(ErpFeReceptor updateErpFeReceptor) {
        
          Session session = sessionFactory.openSession();
        Transaction transaccion = session.beginTransaction();
        
        try{
            
            session.update(updateErpFeReceptor);
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
