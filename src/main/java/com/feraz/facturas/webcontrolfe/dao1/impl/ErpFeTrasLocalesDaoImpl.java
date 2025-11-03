/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.feraz.facturas.webcontrolfe.dao1.impl;

import com.feraz.facturas.webcontrolfe.dao1.ErpFeTrasLocalesDao;
import com.feraz.facturas.webcontrolfe.model.ErpFeTrasLocales;
import com.feraz.facturas.webcontrolfe.model.ErpFeTrasLocalesId;
import java.util.List;
import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;

/**
 *
 * @author vavi
 */
public class ErpFeTrasLocalesDaoImpl implements ErpFeTrasLocalesDao{
    
        private SessionFactory sessionFactory;


    @Override
    public ErpFeTrasLocalesId save(ErpFeTrasLocales erpFeTrasLocales) {
        
        
          Session session = sessionFactory.openSession();
        Transaction transaccion = session.beginTransaction();
        ErpFeTrasLocalesId id = null;
        try{
            
            id = (ErpFeTrasLocalesId)session.save(erpFeTrasLocales);
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
    public List<ErpFeTrasLocales> FindErpFeTrasLocales(String compania,int numero) {
        
            List<ErpFeTrasLocales> erpFeTrasLocales;
        Session session = sessionFactory.openSession();
        Transaction transaccion = session.beginTransaction();

        try {

             erpFeTrasLocales = (List<ErpFeTrasLocales>) session.createQuery("from ErpFeTrasLocales where id.compania = :compania and id.numero = :numero")
                    .setString("compania", compania)
                     .setInteger("numero",numero)
                    .list();
            transaccion.commit();
        } catch (HibernateException e) {
            transaccion.rollback();
            return null;
        } finally {

            session.close();

        }

        return erpFeTrasLocales;
        
    }

    @Override
    public boolean delete(ErpFeTrasLocales erpFeTrasLocales) {
        
        
           Session session = sessionFactory.openSession();
        Transaction transaccion = session.beginTransaction();
        try{
          
            session.delete(erpFeTrasLocales);
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
    public boolean update(ErpFeTrasLocales erpFeTrasLocales) {
        
          
         Session session = sessionFactory.openSession();
        Transaction transaccion = session.beginTransaction();
        
        try{
            
            session.update(erpFeTrasLocales);
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
    public int getMAxIdErpFeRetencionCompl(ErpFeTrasLocalesId id) {
        
         Session session = sessionFactory.openSession();
        try {
            DetachedCriteria maxSec = DetachedCriteria.forClass(ErpFeTrasLocales.class);


            Criteria criteria = maxSec.getExecutableCriteria(session);
            criteria.add(Restrictions.eq("id.compania", id.getCompania()));
            criteria.add(Restrictions.eq("id.numero", id.getNumero()));
            criteria.setProjection(Projections.max("id.sec"));
            int campo = 0;
            List lista = criteria.list();
            if (lista.get(0) == null) {
                campo = 0;
            } else {
                campo = new Integer(lista.get(0).toString());
            }
            campo++;
            
            return campo;
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        } finally {
            session.close();
        }
        
    }

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }
    
    
    
    
}
