/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.feraz.cajachica.dao.impl;

import com.feraz.cajachica.dao.ErpFoliosSolFondosDao;
import com.feraz.cajachica.model.ErpFoliosSolFondos;
import com.feraz.cajachica.model.ErpFoliosSolFondosId;
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
 * @author LENOVO
 */
public class ErpFoliosSolFondosDaoImpl implements ErpFoliosSolFondosDao{
    
    private SessionFactory sessionFactory;

    @Override
    public ErpFoliosSolFondosId save(ErpFoliosSolFondos erpFoliosSolFondos) {
        
           
        Session session = sessionFactory.openSession();
        Transaction transaccion = session.beginTransaction();
        ErpFoliosSolFondosId id = null;
        try {

            id = (ErpFoliosSolFondosId) session.save(erpFoliosSolFondos);
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
    public boolean delete(ErpFoliosSolFondos erpFoliosSolFondos) {
        
           Session session = sessionFactory.openSession();
        Transaction transaccion = session.beginTransaction();

        try {

            session.delete(erpFoliosSolFondos);
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
    public boolean update(ErpFoliosSolFondos erpFoliosSolFondos) {
            Session session = sessionFactory.openSession();
        Transaction transaccion = session.beginTransaction();

        try {

            session.update(erpFoliosSolFondos);
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
    public int getMaxFoliosSolFondosId(ErpFoliosSolFondosId id) {
        
         Session session = sessionFactory.openSession();
        try {
            DetachedCriteria maxSec = DetachedCriteria.forClass(ErpFoliosSolFondos.class);

            Criteria criteria = maxSec.getExecutableCriteria(session);
            criteria.add(Restrictions.eq("id.compania", id.getCompania()));
            //criteria.add(Restrictions.eq("id.idSolicitud", id.getIdSolicitud()));

            criteria.setProjection(Projections.max("id.folio"));
            int campo = 0;
            List lista = criteria.list();
            if (lista.get(0) == null) {
                campo = 0;
            } else {
                campo = Integer.parseInt(lista.get(0).toString());
               // System.out.println("campo1"+campo);

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
