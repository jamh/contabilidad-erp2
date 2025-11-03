/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.feraz.cxp.dao.impl;

import com.feraz.cxp.dao.ErpProvPagoUnicoDao;
import com.feraz.cxp.model.ErpProvPagoUnico;
import com.feraz.cxp.model.ErpProvPagoUnicoId;
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
 * @author vavi
 */
public class ErpProvPagoUnicoDaoImpl implements ErpProvPagoUnicoDao{
    
    private SessionFactory sessionFactory;

    @Override
    public ErpProvPagoUnicoId save(ErpProvPagoUnico erpProvPagoUnico) {
        
          Session session = sessionFactory.openSession();
        Transaction transaccion = session.beginTransaction();
        ErpProvPagoUnicoId id = null;
        try {

            id = (ErpProvPagoUnicoId) session.save(erpProvPagoUnico);
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
    public List<ErpProvPagoUnico> findErpCClientes(ErpProvPagoUnicoId erpProvPagoUnico) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean delete(ErpProvPagoUnico erpProvPagoUnico) {
        
        Session session = sessionFactory.openSession();
        Transaction transaccion = session.beginTransaction();

        try {

            session.delete(erpProvPagoUnico);
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
    public boolean update(ErpProvPagoUnico erpProvPagoUnico) {
        
         Session session = sessionFactory.openSession();
        Transaction transaccion = session.beginTransaction();

        try {

            session.update(erpProvPagoUnico);
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
    public int getMaxErpProvPagoUnicoId(ErpProvPagoUnicoId id) {
        
        Session session = sessionFactory.openSession();
        try {
            DetachedCriteria maxSec = DetachedCriteria.forClass(ErpProvPagoUnico.class);

            Criteria criteria = maxSec.getExecutableCriteria(session);
            criteria.add(Restrictions.eq("id.compania", id.getCompania()));

            criteria.setProjection(Projections.max("id.idMov"));
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
    
          public boolean updateErpProvPagoUnicoEstatusPagos(ErpProvPagoUnico disp) {
        Session session = sessionFactory.openSession();
        Transaction transaccion = session.beginTransaction();
        try {

            String hql = "update ErpProvPagoUnico set folioPagos = "+disp.getFolioPagos()+", estatusCxp = :estatusCxp where id.compania = :compania and id.idMov = :idMov";
            Query query = session.createQuery(hql);
            query.setString("compania", disp.getId().getCompania());
            query.setInteger("idMov", disp.getId().getIdMov());
          //  query.setInteger("folioPagos", disp.getFolioPagos());
            query.setString("estatusCxp", disp.getEstatusCxp());
            int rowCount = query.executeUpdate();
            //    System.out.println("Rows affected: " + rowCount);

            transaccion.commit();

        } catch (HibernateException e) {
            e.printStackTrace();
            transaccion.rollback();
            return false;
        } catch (Exception e2) {
            e2.printStackTrace();
            transaccion.rollback();
        } finally {
            session.close();
        }
        return true;
    }

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }
    
    
    
}
