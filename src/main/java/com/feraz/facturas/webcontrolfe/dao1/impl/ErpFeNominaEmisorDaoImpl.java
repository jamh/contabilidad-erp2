/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.feraz.facturas.webcontrolfe.dao1.impl;

import com.feraz.facturas.webcontrolfe.dao1.ErpFeNominaEmisorDao;
import com.feraz.facturas.webcontrolfe.model.ErpFeNominaEmisor;
import com.feraz.facturas.webcontrolfe.model.ErpFeNominaEmisorId;
import java.util.List;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

/**
 *
 * @author vavi
 */
public class ErpFeNominaEmisorDaoImpl implements ErpFeNominaEmisorDao{
    
    private SessionFactory sessionFactory;

    @Override
    public ErpFeNominaEmisorId save(ErpFeNominaEmisor erpFeNominaEmisor) {
        
           Session session = sessionFactory.openSession();
        Transaction transaccion = session.beginTransaction();
        ErpFeNominaEmisorId id = null;
        try{
            
            id = (ErpFeNominaEmisorId)session.save(erpFeNominaEmisor);
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
    public ErpFeNominaEmisor findErpFeNominaEmisor(String compania, int numero) {
        
           ErpFeNominaEmisor erpFeNominaEmisor;
        Session session = sessionFactory.openSession();
        Transaction transaccion = session.beginTransaction();

        try {

            erpFeNominaEmisor = (ErpFeNominaEmisor) session.createQuery("from ErpFeNominaEmisor where id.compania = :compania and id.numero = :numero")
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

        return erpFeNominaEmisor;
        
    }

    @Override
    public boolean delete(ErpFeNominaEmisor erpFeNominaEmisor) {
        
          Session session = sessionFactory.openSession();
        Transaction transaccion = session.beginTransaction();
        try {

            session.delete(erpFeNominaEmisor);
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
    public boolean update(ErpFeNominaEmisor erpFeNominaEmisor) {
        
           Session session = sessionFactory.openSession();
        Transaction transaccion = session.beginTransaction();

        try {

            session.update(erpFeNominaEmisor);
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
