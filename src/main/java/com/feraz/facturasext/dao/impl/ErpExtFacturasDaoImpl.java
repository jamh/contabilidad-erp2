/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.feraz.facturasext.dao.impl;

import com.feraz.facturasext.dao.ErpExtFacturasDao;
import com.feraz.facturasext.model.ErpExtFacturas;
import com.feraz.facturasext.model.ErpExtFacturasId;
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
public class ErpExtFacturasDaoImpl implements ErpExtFacturasDao{
    
    private SessionFactory sessionFactory;

    @Override
    public ErpExtFacturasId save(ErpExtFacturas erpExtFacturas) {
        
         Session session = sessionFactory.openSession();
        Transaction transaccion = session.beginTransaction();
        ErpExtFacturasId id = null;
        try {

            id = (ErpExtFacturasId) session.save(erpExtFacturas);
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
    public boolean delete(ErpExtFacturas erpExtFacturas) {
           Session session = sessionFactory.openSession();
        Transaction transaccion = session.beginTransaction();
        try {

            session.delete(erpExtFacturas);
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
    public boolean update(ErpExtFacturas erpExtFacturas) {
        Session session = sessionFactory.openSession();
        Transaction transaccion = session.beginTransaction();

        try {

            session.update(erpExtFacturas);
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
    public int getMaxErpExtFacturas(ErpExtFacturasId id) {
        Session session = sessionFactory.openSession();
        
        System.out.println("id.getCompania(): "+id.getCompania());
        System.out.println("id.getInvoiceNo(): "+id.getInvoiceNo());

        try {
            DetachedCriteria maxSec = DetachedCriteria.forClass(ErpExtFacturas.class);

            Criteria criteria = maxSec.getExecutableCriteria(session);
            criteria.add(Restrictions.eq("id.compania", id.getCompania()));
            criteria.add(Restrictions.eq("id.invoiceNo", id.getInvoiceNo()));
            criteria.setProjection(Projections.max("id.sec"));
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
