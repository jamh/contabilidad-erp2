/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.feraz.almacen.dao.impl;

import com.feraz.almacen.dao.ErpProductosSolicitadosDao;
import com.feraz.almacen.model.ErpProductosSolicitados;
import com.feraz.almacen.model.ErpProductosSolicitadosId;
import com.feraz.pedidos.model.ErpPedidoMaestro;
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
 * @author FERAZ-14
 */
public class ErpProductosSolicitadosDaoImpl implements ErpProductosSolicitadosDao{
    
    private SessionFactory sessionFactory;

    @Override
    public ErpProductosSolicitadosId save(ErpProductosSolicitados erpProductosSolicitados) {
        
        
           Session session = sessionFactory.openSession();
        Transaction transaccion = session.beginTransaction();
        ErpProductosSolicitadosId id = null;
        try {

            id = (ErpProductosSolicitadosId) session.save(erpProductosSolicitados);
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
    public ErpProductosSolicitados findErpProductosSolicitados(String compania, Integer idSolicitud) {
        
        ErpProductosSolicitados erpProductosSolicitados;
        Session session = sessionFactory.openSession();
        Transaction transaccion = session.beginTransaction();

        try {

            erpProductosSolicitados = (ErpProductosSolicitados) session.createQuery("from ErpProductosSolicitados where id.compania= :compania and id.idSolicitud= :idSolicitud")
                    .setString("compania", compania)
                    .setInteger("idSolicitud", idSolicitud)
                    .setMaxResults(1)
                    .uniqueResult();
            transaccion.commit();
        } catch (HibernateException e) {
            e.printStackTrace();
            transaccion.rollback();
            return null;
        } finally {

            session.close();

        }

        return erpProductosSolicitados;
        
    }

    @Override
    public boolean delete(ErpProductosSolicitados erpProductosSolicitados) {
        
         Session session = sessionFactory.openSession();
        Transaction transaccion = session.beginTransaction();
        try {

            session.delete(erpProductosSolicitados);
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
    public boolean update(ErpProductosSolicitados erpProductosSolicitados) {
        
         Session session = sessionFactory.openSession();
        Transaction transaccion = session.beginTransaction();

        try {

            session.update(erpProductosSolicitados);
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
    public int getMaxIdErpProductosSolicitados(ErpProductosSolicitadosId id) {
        
        Session session = sessionFactory.openSession();

        try {
            DetachedCriteria maxSec = DetachedCriteria.forClass(ErpProductosSolicitados.class);

            Criteria criteria = maxSec.getExecutableCriteria(session);
            criteria.add(Restrictions.eq("id.compania", id.getCompania()));
            criteria.setProjection(Projections.max("id.idSolicitud"));
            int campo ;
            List lista = criteria.list();
            if (lista.get(0) == null) {
                campo = 0;
            } else {
                campo = new Integer(lista.get(0).toString());
            }
            campo++;

            return campo;
        } catch (HibernateException e) {
            return 0;
        } catch (NumberFormatException e) {
            return 0;
        } finally {
            session.close();
        }
    }

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }
    
    
    
}
