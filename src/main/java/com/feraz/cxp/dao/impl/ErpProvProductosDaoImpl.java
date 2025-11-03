/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.feraz.cxp.dao.impl;

import com.feraz.cxp.dao.ErpProvProductosDao;
import com.feraz.cxp.model.ErpProvProductos;
import com.feraz.cxp.model.ErpProvProductosId;
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
 * @author 55555
 */
public class ErpProvProductosDaoImpl implements ErpProvProductosDao{
    
    private SessionFactory sessionFactory;

    @Override
    public ErpProvProductosId save(ErpProvProductos erpProvProductos) {
        
          
        Session session = sessionFactory.openSession();
        Transaction transaccion = session.beginTransaction();
        ErpProvProductosId id = null;
        try {

            id = (ErpProvProductosId) session.save(erpProvProductos);
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
    public boolean delete(ErpProvProductos deleteErpProvProductos) {
        
          Session session = sessionFactory.openSession();
        Transaction transaccion = session.beginTransaction();

        try {

            session.delete(deleteErpProvProductos);
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
    public boolean update(ErpProvProductos updateErpProvProductos) {
        
          Session session = sessionFactory.openSession();
        Transaction transaccion = session.beginTransaction();

        try {

            session.update(updateErpProvProductos);
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
    
       public int getMaxErpProductos(ErpProvProductosId id) {
        Session session = sessionFactory.openSession();
        try {
            DetachedCriteria maxSec = DetachedCriteria.forClass(ErpProvProductos.class);


            Criteria criteria = maxSec.getExecutableCriteria(session);
            criteria.add(Restrictions.eq("id.compania", id.getCompania()));
            criteria.add(Restrictions.eq("id.proveedor", id.getProveedor()));
            criteria.setProjection(Projections.max("id.idProducto"));
            int campo = 0;
            List lista = criteria.list();
            if (lista.get(0) == null) {
                campo = 0;
            } else {
                campo = new Integer(lista.get(0).toString());
               // System.out.println("campo1"+campo);
                
            }
            campo++;
             //System.out.println("campo2"+campo);
            String idCampo = Integer.toString(campo);
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
