/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.feraz.cxp.dao.impl;

import com.feraz.cxp.dao.ErpProvisionDetDao;
import com.feraz.cxp.model.ErpProvisionDet;
import com.feraz.cxp.model.ErpProvisionDetId;
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
public class ErpProvisionDetDaoImpl implements ErpProvisionDetDao{
    
    private SessionFactory sessionFactory;

    @Override
    public ErpProvisionDetId save(ErpProvisionDet erpProvisionDet) {
        
         Session session = sessionFactory.openSession();
        Transaction transaccion = session.beginTransaction();
        ErpProvisionDetId id = null;
        try {

            id = (ErpProvisionDetId) session.save(erpProvisionDet);
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
    public boolean delete(ErpProvisionDet erpProvisionDet) {
        
         Session session = sessionFactory.openSession();
        Transaction transaccion = session.beginTransaction();

        try {

            session.delete(erpProvisionDet);
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
    
        public boolean eliminaProvisionDoc(ErpProvisionDet comp) {
        Session session = sessionFactory.openSession();
        Transaction transaccion = session.beginTransaction();

        //System.out.println("actualizando pagos");
        try {

            String hql = "delete from ErpProvisionDet where id.compania = :compania and id.id = :id and idDocumento = :idDocumento and origen = :origen";

            Query query = session.createQuery(hql);
            query.setString("compania", comp.getId().getCompania());
            query.setInteger("idDocumento", comp.getIdDocumento());
            query.setInteger("id", comp.getId().getId());
            query.setString("origen", comp.getOrigen());
          


//                System.out.println("com.IDCONCGASTO:" + com.IDCONCGASTO);
//                System.out.println("com.compania:" + compania);
//                System.out.println(" com.NUMERO:" + com.NUMERO);
            int rowCount = query.executeUpdate();
//                System.out.println("rowCount:" + rowCount);

            transaccion.commit();

        } catch (HibernateException e) {
           
            transaccion.rollback();
            return false;
        } finally {

            session.close();
        }

        return true;

    }

    @Override
    public int getMaxerpProvisionDetId(ErpProvisionDetId id) {
        
          Session session = sessionFactory.openSession();
        try {
            DetachedCriteria maxSec = DetachedCriteria.forClass(ErpProvisionDet.class);

            Criteria criteria = maxSec.getExecutableCriteria(session);
            criteria.add(Restrictions.eq("id.compania", id.getCompania()));
            criteria.add(Restrictions.eq("id.id", id.getId()));

            criteria.setProjection(Projections.max("id.sec"));
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
