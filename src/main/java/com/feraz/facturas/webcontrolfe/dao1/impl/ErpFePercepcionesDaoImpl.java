/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.feraz.facturas.webcontrolfe.dao1.impl;

import com.feraz.facturas.webcontrolfe.dao1.ErpFePercepcionesDao;
import com.feraz.facturas.webcontrolfe.model.ErpFePercepciones;
import com.feraz.facturas.webcontrolfe.model.ErpFePercepcionesId;
import java.util.List;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

/**
 *
 * @author vavi
 */
public class ErpFePercepcionesDaoImpl implements ErpFePercepcionesDao{
    
    private SessionFactory sessionFactory;

    @Override
    public ErpFePercepcionesId save(ErpFePercepciones erpFePercepciones) {
        
              Session session = sessionFactory.openSession();
        Transaction transaccion = session.beginTransaction();
        ErpFePercepcionesId id = null;
        try{
            
            id = (ErpFePercepcionesId)session.save(erpFePercepciones);
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
    public ErpFePercepciones findErpFePercepciones(String compania, int numero) {
        
        ErpFePercepciones erpFePercepciones;
        Session session = sessionFactory.openSession();
        Transaction transaccion = session.beginTransaction();

        try {

            erpFePercepciones = (ErpFePercepciones) session.createQuery("from ErpFePercepciones where id.compania = :compania and id.numero = :numero")
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

        return erpFePercepciones;
        
    }

    @Override
    public boolean delete(ErpFePercepciones erpFePercepciones) {
        
          Session session = sessionFactory.openSession();
        Transaction transaccion = session.beginTransaction();
        try {

            session.delete(erpFePercepciones);
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
    public boolean update(ErpFePercepciones erpFePercepciones) {
        
        Session session = sessionFactory.openSession();
        Transaction transaccion = session.beginTransaction();

        try {

            session.update(erpFePercepciones);
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
