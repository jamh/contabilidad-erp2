/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.feraz.facturas.webcontrolfe.dao1.impl;

import com.feraz.facturas.webcontrolfe.dao1.ErpCpRelacionFacturasDao;
import com.feraz.facturas.webcontrolfe.model.ErpCpRelacionFacturas;
import com.feraz.facturas.webcontrolfe.model.ErpCpRelacionFacturasId;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import java.util.List;
import org.apache.log4j.Logger;
import org.hibernate.Criteria;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;

/**
 *
 * @author Feraz3
 */
public class ErpCpRelacionFacturasDaoImpl implements ErpCpRelacionFacturasDao{

    private static final Logger log = Logger.getLogger(ErpCpRelacionFacturasDaoImpl.class);
    private SessionFactory sessionFactory;
    
    @Override
    public ErpCpRelacionFacturasId save(ErpCpRelacionFacturas erpCpRelacionFacturas) {
        
        Session session = sessionFactory.openSession();
        Transaction transaccion = session.beginTransaction();
        ErpCpRelacionFacturasId id = null;
        try {

            id = (ErpCpRelacionFacturasId) session.save(erpCpRelacionFacturas);
            transaccion.commit();

        } catch (HibernateException e) {
            log.error("Error Save Relacion Facturas:",e);
            transaccion.rollback();
            return null;
        } finally {

            session.close();
        }
        return id;
        
    }

    @Override
    public boolean delete(ErpCpRelacionFacturas deleteErpCpRelacionFacturas) {
        
        Session session = sessionFactory.openSession();
        Transaction transaccion = session.beginTransaction();
        try {

            session.delete(deleteErpCpRelacionFacturas);
            transaccion.commit();

        } catch (HibernateException e) {
            log.error("Error Delete Relacion Facturas:",e);   
            //e.printStackTrace();
            transaccion.rollback();
            return false;

        } finally {

            session.close();

        }
        return true;
        
    }

    @Override
    public int getMaxIdFacturas(ErpCpRelacionFacturasId id) {
        
        
         Session session = sessionFactory.openSession();

        try {
            DetachedCriteria maxSec = DetachedCriteria.forClass(ErpCpRelacionFacturas.class);

            Criteria criteria = maxSec.getExecutableCriteria(session);
            criteria.add(Restrictions.eq("id.compania", id.getCompania()));
            criteria.add(Restrictions.eq("id.numFactura", id.getNumFactura()));
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
        } catch (HibernateException e) {
            //e.printStackTrace();
            log.error("Error getMaxId H", e);
            return 0;
        } catch (NumberFormatException e) {
            //e.printStackTrace();
            log.error("Error getMaxId", e);
            return 0;
        } finally {
            session.close();
        }
        
        
    }

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }
    
    
    
}
