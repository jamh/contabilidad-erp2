/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.feraz.facturas.webcontrolfe.dao1.impl;

import com.feraz.facturas.webcontrolfe.dao1.ErpFeImpuestoComplDao;
import com.feraz.facturas.webcontrolfe.model.ErpFeImpuestoCompl;
import com.feraz.facturas.webcontrolfe.model.ErpFeImpuestoComplId;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

/**
 *
 * @author vavi
 */
public class ErpFeImpuestoComplDaoImpl implements ErpFeImpuestoComplDao{
    
    private SessionFactory sessionFactory;

    @Override
    public ErpFeImpuestoComplId save(ErpFeImpuestoCompl erpFeImpuestoCompl) {
        
         
        
          
              Session session = sessionFactory.openSession();
        Transaction transaccion = session.beginTransaction();
        ErpFeImpuestoComplId id = null;
        try{
            
            id = (ErpFeImpuestoComplId)session.save(erpFeImpuestoCompl);
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
    public ErpFeImpuestoCompl findErpFePercepciones(String compania, int numero) {
        
            ErpFeImpuestoCompl erpFeImpuestoCompl;
        Session session = sessionFactory.openSession();
        Transaction transaccion = session.beginTransaction();

        try {

            erpFeImpuestoCompl = (ErpFeImpuestoCompl) session.createQuery("from ErpFeImpuestoCompl where id.compania = :compania and id.numero = :numero")
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

        return erpFeImpuestoCompl;
    }

    @Override
    public boolean delete(ErpFeImpuestoCompl erpFeImpuestoCompl) {
        
        Session session = sessionFactory.openSession();
        Transaction transaccion = session.beginTransaction();
        try {

            session.delete(erpFeImpuestoCompl);
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
    public boolean update(ErpFeImpuestoCompl erpFeImpuestoCompl) {
        
         Session session = sessionFactory.openSession();
        Transaction transaccion = session.beginTransaction();

        try {

            session.update(erpFeImpuestoCompl);
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
