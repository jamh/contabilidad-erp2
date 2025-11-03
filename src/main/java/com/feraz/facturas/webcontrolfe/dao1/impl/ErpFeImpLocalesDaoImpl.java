/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.feraz.facturas.webcontrolfe.dao1.impl;

import com.feraz.facturas.webcontrolfe.dao1.ErpFeImpLocalesDao;
import com.feraz.facturas.webcontrolfe.model.ErpFeImpLocales;
import com.feraz.facturas.webcontrolfe.model.ErpFeImpLocalesId;
import java.util.List;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

/**
 *
 * @author vavi
 */
public class ErpFeImpLocalesDaoImpl implements ErpFeImpLocalesDao{
    
         private SessionFactory sessionFactory;


    @Override
    public ErpFeImpLocalesId save(ErpFeImpLocales erpFeImpLocales) {
        
          Session session = sessionFactory.openSession();
        Transaction transaccion = session.beginTransaction();
        ErpFeImpLocalesId id = null;
        try{
            
            id = (ErpFeImpLocalesId)session.save(erpFeImpLocales);
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
    public List<ErpFeImpLocales> findErpFeImpLocales(String compania, int numero) {
        
            List<ErpFeImpLocales> erpFeImpLocales;
        Session session = sessionFactory.openSession();
        Transaction transaccion = session.beginTransaction();

        try {

            erpFeImpLocales = (List<ErpFeImpLocales>) session.createQuery("from ErpFeImpLocales where id.compania = :compania and id.numero = :numero")
                    .setString("compania", compania)
                     .setInteger("numero",new Integer(numero))
                    .list();
            transaccion.commit();
        } catch (HibernateException e) {
            transaccion.rollback();
            return null;
        } finally {

            session.close();

        }

        return erpFeImpLocales;
        
    }

    @Override
    public boolean delete(ErpFeImpLocales deleteErpFeImpLocales) {
        
        Session session = sessionFactory.openSession();
        Transaction transaccion = session.beginTransaction();
        try{
          
            session.delete(deleteErpFeImpLocales);
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
    public boolean update(ErpFeImpLocales updateErpFeImpLocales) {
        
          Session session = sessionFactory.openSession();
        Transaction transaccion = session.beginTransaction();
        
        try{
            
            session.update(updateErpFeImpLocales);
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
