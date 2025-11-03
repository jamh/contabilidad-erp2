/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.feraz.facturas.webcontrolfe.dao1.impl;

import com.feraz.facturas.webcontrolfe.dao1.ErpFeNominaReceptorDao;
import com.feraz.facturas.webcontrolfe.model.ErpFeNominaReceptor;
import com.feraz.facturas.webcontrolfe.model.ErpFeNominaReceptorId;
import java.util.List;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

/**
 *
 * @author vavi
 */
public class ErpFeNominaReceptorDaoImpl implements ErpFeNominaReceptorDao{
    
    private SessionFactory sessionFactory;

    @Override
    public ErpFeNominaReceptorId save(ErpFeNominaReceptor erpFeNominaReceptor) {
        
          Session session = sessionFactory.openSession();
        Transaction transaccion = session.beginTransaction();
        ErpFeNominaReceptorId id = null;
        try{
            
            id = (ErpFeNominaReceptorId)session.save(erpFeNominaReceptor);
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
    public ErpFeNominaReceptor findErpFeNominaReceptor(String compania, int numero) {
        
             ErpFeNominaReceptor erpFeNominaReceptor;
        Session session = sessionFactory.openSession();
        Transaction transaccion = session.beginTransaction();

        try {

            erpFeNominaReceptor = (ErpFeNominaReceptor) session.createQuery("from ErpFeNominaReceptor where id.compania = :compania and id.numero = :numero")
                    .setString("compania", compania)
                    .setInteger("numero", numero)
                    .setMaxResults(1)
                    .uniqueResult();

            transaccion.commit();
        } catch (HibernateException e) {
         
            transaccion.rollback();
            return null;
        } finally {

            session.close();

        }

        return erpFeNominaReceptor;
        
    }

    @Override
    public boolean delete(ErpFeNominaReceptor erpFeNominaReceptor) {
        
          Session session = sessionFactory.openSession();
        Transaction transaccion = session.beginTransaction();
        try {

            session.delete(erpFeNominaReceptor);
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

    @Override
    public boolean update(ErpFeNominaReceptor erpFeNominaReceptor) {
        
          Session session = sessionFactory.openSession();
        Transaction transaccion = session.beginTransaction();

        try {

            session.update(erpFeNominaReceptor);
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
