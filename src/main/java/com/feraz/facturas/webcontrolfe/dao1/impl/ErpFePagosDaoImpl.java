/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.feraz.facturas.webcontrolfe.dao1.impl;

import com.feraz.facturas.webcontrolfe.dao1.ErpFePagosDao;
import com.feraz.facturas.webcontrolfe.model.ErpFeDocRelacionado;
import com.feraz.facturas.webcontrolfe.model.ErpFeDocRelacionadoId;
import com.feraz.facturas.webcontrolfe.model.ErpFePagos;
import com.feraz.facturas.webcontrolfe.model.ErpFePagosId;
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
public class ErpFePagosDaoImpl implements ErpFePagosDao{
    
    
     private SessionFactory sessionFactory;

    @Override
    public ErpFePagosId save(ErpFePagos erpFePagos) {
        
        
        
          
              Session session = sessionFactory.openSession();
        Transaction transaccion = session.beginTransaction();
        ErpFePagosId id = null;
        try{
            
            id = (ErpFePagosId)session.save(erpFePagos);
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
    public ErpFePagos findErpFePercepciones(String compania, int numero) {
        
        
          ErpFePagos erpFePagos;
        Session session = sessionFactory.openSession();
        Transaction transaccion = session.beginTransaction();

        try {

            erpFePagos = (ErpFePagos) session.createQuery("from ErpFePagos where id.compania = :compania and id.numero = :numero")
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

        return erpFePagos;
    }

    @Override
    public boolean delete(ErpFePagos erpFePagos) {
        
        
          Session session = sessionFactory.openSession();
        Transaction transaccion = session.beginTransaction();
        try {

            session.delete(erpFePagos);
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
    public boolean update(ErpFePagos erpFePagos) {
        
         Session session = sessionFactory.openSession();
        Transaction transaccion = session.beginTransaction();

        try {

            session.update(erpFePagos);
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
    public int getMaxIdErpFePagos(ErpFePagosId id) {
        
            Session session = sessionFactory.openSession();
        try {
            DetachedCriteria maxSec = DetachedCriteria.forClass(ErpFePagos.class);


            Criteria criteria = maxSec.getExecutableCriteria(session);
            criteria.add(Restrictions.eq("id.compania", id.getCompania()));
            criteria.add(Restrictions.eq("id.numero", id.getNumero()));
            criteria.setProjection(Projections.max("id.id"));
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
