/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.feraz.facturas.webcontrolfe.dao1.impl;

import com.feraz.facturas.webcontrolfe.dao1.ErpFeDeduccionesDao;
import com.feraz.facturas.webcontrolfe.model.ErpFeDeducciones;
import com.feraz.facturas.webcontrolfe.model.ErpFeDeduccionesId;
import java.util.List;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

/**
 *
 * @author vavi
 */
public class ErpFeDeduccionesDaoImpl implements ErpFeDeduccionesDao{
    
    private SessionFactory sessionFactory;

    @Override
    public ErpFeDeduccionesId save(ErpFeDeducciones erpFeDeducciones) {
        
             Session session = sessionFactory.openSession();
        Transaction transaccion = session.beginTransaction();
        ErpFeDeduccionesId id = null;
        try{
            
            id = (ErpFeDeduccionesId)session.save(erpFeDeducciones);
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
    public ErpFeDeducciones findErpFeDeducciones(String compania, int numero) {
        
         ErpFeDeducciones erpFeDeducciones;
        Session session = sessionFactory.openSession();
        Transaction transaccion = session.beginTransaction();

        try {

            erpFeDeducciones = (ErpFeDeducciones) session.createQuery("from ErpFeDeducciones where id.compania = :compania and id.numero = :numero")
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

        return erpFeDeducciones;
        
        
    }

    @Override
    public boolean delete(ErpFeDeducciones erpFeDeducciones) {
        
        
          Session session = sessionFactory.openSession();
        Transaction transaccion = session.beginTransaction();
        try {

            session.delete(erpFeDeducciones);
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
    public boolean update(ErpFeDeducciones erpFeDeducciones) {
        
          Session session = sessionFactory.openSession();
        Transaction transaccion = session.beginTransaction();

        try {

            session.update(erpFeDeducciones);
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
