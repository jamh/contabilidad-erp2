/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.feraz.facturas.webcontrolfe.dao1.impl;

import com.feraz.facturas.webcontrolfe.dao1.ErpFeNominaDao;
import com.feraz.facturas.webcontrolfe.model.ErpFeNomina;
import com.feraz.facturas.webcontrolfe.model.ErpFeNominaId;
import java.util.List;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

/**
 *
 * @author vavi
 */
public class ErpFeNominaDaoImpl implements ErpFeNominaDao{
    
    private SessionFactory sessionFactory;

    @Override
    public ErpFeNominaId save(ErpFeNomina erpFeNomina) {
          Session session = sessionFactory.openSession();
        Transaction transaccion = session.beginTransaction();
        ErpFeNominaId id = null;
        try{
            
            id = (ErpFeNominaId)session.save(erpFeNomina);
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
    public ErpFeNomina findErpFeNomina(String compania, int numero) {
        
          ErpFeNomina erpFeNomina;
        Session session = sessionFactory.openSession();
        Transaction transaccion = session.beginTransaction();

        try {

            erpFeNomina = (ErpFeNomina) session.createQuery("from ErpFeNomina where id.compania = :compania and id.numero = :numero")
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

        return erpFeNomina;
        
    }

    @Override
    public boolean delete(ErpFeNomina erpFeNomina) {
        
         Session session = sessionFactory.openSession();
        Transaction transaccion = session.beginTransaction();
        try {

            session.delete(erpFeNomina);
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
    public boolean update(ErpFeNomina erpFeNomina) {
        
          Session session = sessionFactory.openSession();
        Transaction transaccion = session.beginTransaction();

        try {

            session.update(erpFeNomina);
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
