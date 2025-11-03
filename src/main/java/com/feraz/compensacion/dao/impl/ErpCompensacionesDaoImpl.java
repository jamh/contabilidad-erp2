/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.feraz.compensacion.dao.impl;

import com.feraz.compensacion.dao.ErpCompensacionesDao;
import com.feraz.compensacion.model.ErpCompensaciones;
import com.feraz.compensacion.model.ErpCompensacionesId;
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
public class ErpCompensacionesDaoImpl implements ErpCompensacionesDao{
    
    private SessionFactory sessionFactory;

    @Override
    public ErpCompensacionesId save(ErpCompensaciones erpCompensaciones) {
        
          Session session = sessionFactory.openSession();
        Transaction transaccion = session.beginTransaction();
        ErpCompensacionesId id = null;
        try {

            id = (ErpCompensacionesId) session.save(erpCompensaciones);
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
    public List<ErpCompensaciones> findErpCompensaciones() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean delete(ErpCompensaciones deleteErpCompensaciones) {
           Session session = sessionFactory.openSession();
        Transaction transaccion = session.beginTransaction();

        try {

            session.delete(deleteErpCompensaciones);
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
    public boolean update(ErpCompensaciones updateErpCompensaciones) {
        Session session = sessionFactory.openSession();
        Transaction transaccion = session.beginTransaction();

        try {

            session.update(updateErpCompensaciones);
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
    
        public int getMaxIdCompensacion(ErpCompensacionesId id) {
        Session session = sessionFactory.openSession();
        try {
            DetachedCriteria maxSec = DetachedCriteria.forClass(ErpCompensaciones.class);

            Criteria criteria = maxSec.getExecutableCriteria(session);
            criteria.add(Restrictions.eq("id.compania", id.getCompania()));
            criteria.setProjection(Projections.max("id.id"));
            int caso = 0;
            List lista = criteria.list();
            if (lista.get(0) == null) {
                caso = 0;
            } else {
                caso = new Integer(lista.get(0).toString());
            }
            caso++;

            return caso;
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
