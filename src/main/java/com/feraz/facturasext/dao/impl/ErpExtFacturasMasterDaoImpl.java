/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.feraz.facturasext.dao.impl;

import com.feraz.facturasext.dao.ErpExtFacturasMasterDao;
import com.feraz.facturasext.model.ErpExtFacturasMaster;
import com.feraz.facturasext.model.ErpExtFacturasMasterId;
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
 * @author Ing. David Ortiz García
 */
public class ErpExtFacturasMasterDaoImpl implements ErpExtFacturasMasterDao{
    
    private SessionFactory sessionFactory;

    @Override
    public ErpExtFacturasMasterId save(ErpExtFacturasMaster erpExtFacturasMaster) {
        
         Session session = sessionFactory.openSession();
        Transaction transaccion = session.beginTransaction();
        ErpExtFacturasMasterId id = null;
        try {

            id = (ErpExtFacturasMasterId) session.save(erpExtFacturasMaster);
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
    public boolean delete(ErpExtFacturasMaster erpExtFacturasMaster) {
           Session session = sessionFactory.openSession();
        Transaction transaccion = session.beginTransaction();
        try {

            session.delete(erpExtFacturasMaster);
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
    public boolean update(ErpExtFacturasMaster erpExtFacturasMaster) {
        Session session = sessionFactory.openSession();
        Transaction transaccion = session.beginTransaction();

        try {

            session.update(erpExtFacturasMaster);
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
    public boolean actualizaEstatus(ErpExtFacturasMaster comp) {
        Session session = sessionFactory.openSession();
        Transaction transaccion = session.beginTransaction();

        //System.out.println("actualizando pagos");
        try {

            String hql = "update ErpExtFacturasMaster set status =:status, numeroPoliza = :numeroPoliza, fechaPoliza = :fechaPoliza, tipoPoliza = :tipoPoliza where  id.compania = :compania  and id.invoiceNo = :invoiceNo";

            Query query = session.createQuery(hql);
            query.setString("compania", comp.getId().getCompania());
            query.setString("status", comp.getStatus());
            query.setString("invoiceNo", comp.getId().getInvoiceNo());
            query.setString("numeroPoliza",comp.getNumeroPoliza());
            query.setDate("fechaPoliza",comp.getFechaPoliza());
            query.setString("tipoPoliza",comp.getTipoPoliza());
            
           

            int rowCount = query.executeUpdate();

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

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }
    
    
    
}
