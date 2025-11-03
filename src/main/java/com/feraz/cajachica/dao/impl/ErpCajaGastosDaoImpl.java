/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.feraz.cajachica.dao.impl;

import com.feraz.cajachica.dao.ErpCajaGastosDao;
import com.feraz.cajachica.model.ErpCajaGastos;
import com.feraz.cajachica.model.ErpCajaGastosId;
import java.util.List;
import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
/**
 *
 * @author LENOVO
 */
public class ErpCajaGastosDaoImpl implements ErpCajaGastosDao{
    
    private SessionFactory sessionFactory;

    @Override
    public ErpCajaGastosId save(ErpCajaGastos erpCajaGastos) {
        
        
         Session session = sessionFactory.openSession();
        Transaction transaccion = session.beginTransaction();
        ErpCajaGastosId id = null;
        try {

            id = (ErpCajaGastosId) session.save(erpCajaGastos);
            transaccion.commit();

        } catch (HibernateException e) {
            e.printStackTrace();
            transaccion.rollback();
            return null;
        } finally {

            session.close();
        }
        return id;
        
    }

    @Override
    public boolean delete(ErpCajaGastos erpCajaGastos) {
        
        
               Session session = sessionFactory.openSession();
        Transaction transaccion = session.beginTransaction();

        try {

            session.delete(erpCajaGastos);
            transaccion.commit();

        } catch (HibernateException e) {
            e.printStackTrace();
            transaccion.rollback();
            return true;
        } finally {

            session.close();

        }
        return true;
        
    }

    @Override
    public boolean update(ErpCajaGastos erpCajaGastos) {
        
            
             Session session = sessionFactory.openSession();
        Transaction transaccion = session.beginTransaction();

        try {

            session.update(erpCajaGastos);
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
    public int getMaxErpCajaGastosId(ErpCajaGastosId id) {
            Session session = sessionFactory.openSession();
        try {
            DetachedCriteria maxSec = DetachedCriteria.forClass(ErpCajaGastos.class);

            Criteria criteria = maxSec.getExecutableCriteria(session);
            criteria.add(Restrictions.eq("id.compania", id.getCompania()));
            criteria.add(Restrictions.eq("id.idCaja", id.getIdCaja()));
            criteria.add(Restrictions.eq("id.sec", id.getSec()));
            criteria.setProjection(Projections.max("id.idGasto"));
            int campo = 0;
            List lista = criteria.list();
            
            if (lista.get(0) == null) {
                campo = 0;
            } else {
                campo = Integer.parseInt(lista.get(0).toString());
                System.out.println("campo1"+campo);

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
