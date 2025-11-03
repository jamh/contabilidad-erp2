/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.feraz.cxp.dao.impl;

import com.feraz.cxp.dao.ErpCxpFacturasXOtrasDao;
import com.feraz.cxp.model.ErpCxpFacturasXOtras;
import com.feraz.cxp.model.ErpCxpFacturasXOtrasId;
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
 * @author 55555
 */
public class ErpCxpFacturasXOtrasDaoImpl implements ErpCxpFacturasXOtrasDao{
    
      private SessionFactory sessionFactory;

    @Override
    public ErpCxpFacturasXOtrasId save(ErpCxpFacturasXOtras erpCxpFacturasXOtras) {
        
         Session session = sessionFactory.openSession();
        Transaction transaccion = session.beginTransaction();
        ErpCxpFacturasXOtrasId id = null;
        try {

            id = (ErpCxpFacturasXOtrasId) session.save(erpCxpFacturasXOtras);
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
    public List<ErpCxpFacturasXOtras> findErpCxpFacturasXOtras(ErpCxpFacturasXOtrasId erpCpOtras) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean delete(ErpCxpFacturasXOtras erpCxpFacturasXOtras) {
        
         Session session = sessionFactory.openSession();
        Transaction transaccion = session.beginTransaction();

        try {

            session.delete(erpCxpFacturasXOtras);
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
    public boolean update(ErpCxpFacturasXOtras erpCxpFacturasXOtras) {
         Session session = sessionFactory.openSession();
        Transaction transaccion = session.beginTransaction();

        try {

            session.update(erpCxpFacturasXOtras);
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
    
    
        public boolean eliminaFactOtras(ErpCxpFacturasXOtras comp) {
        Session session = sessionFactory.openSession();
        Transaction transaccion = session.beginTransaction();

        //System.out.println("actualizando pagos");
        try {

            String hql = "delete from ErpCxpFacturasXOtras where id.compania = :compania and numeroOtras = :numeroOtras and numeroFe = :numeroFe and origen = :origen";

            Query query = session.createQuery(hql);
            query.setString("compania", comp.getId().getCompania());
            query.setInteger("numeroOtras", comp.getNumeroOtras());
            query.setInteger("numeroFe", comp.getNumeroFe());
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
    public int getMaxErpCpOtrasId(ErpCxpFacturasXOtrasId id) {
        
         Session session = sessionFactory.openSession();
        try {
            DetachedCriteria maxSec = DetachedCriteria.forClass(ErpCxpFacturasXOtras.class);

            Criteria criteria = maxSec.getExecutableCriteria(session);
            criteria.add(Restrictions.eq("id.compania", id.getCompania()));

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
