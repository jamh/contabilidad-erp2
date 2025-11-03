/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.feraz.cxp.dao.impl;

import com.feraz.cxp.dao.ErpCClientesLog2Dao;
import com.feraz.cxp.model.ErpCClientesLog2;
import com.feraz.cxp.model.ErpCClientesLog2Id;
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
 * @author lbadi
 */
public class ErpCClientesLog2DaoImpl implements ErpCClientesLog2Dao {
    
    private SessionFactory sessionFactory;
    

    @Override
    public ErpCClientesLog2Id save(ErpCClientesLog2 erpCClientesLog2) {
    
      System.out.println("llegando a guardar");
         Session session = sessionFactory.openSession();
        Transaction transaccion = session.beginTransaction();
        ErpCClientesLog2Id id = null;
        try {

            id = (ErpCClientesLog2Id) session.save(erpCClientesLog2);
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
    
    public int getMaxErpCClientesLog2Id(ErpCClientesLog2Id id) {
        Session session = sessionFactory.openSession();
        try {
            DetachedCriteria maxSec = DetachedCriteria.forClass(ErpCClientesLog2.class);

            Criteria criteria = maxSec.getExecutableCriteria(session);
            criteria.add(Restrictions.eq("id.compania", id.getCompania()));

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
