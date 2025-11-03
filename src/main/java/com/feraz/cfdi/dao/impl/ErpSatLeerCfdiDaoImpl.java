/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.feraz.cfdi.dao.impl;

/**
 *
 * @author Feraz3
 */
import com.feraz.cfdi.dao.ErpSatLeerCfdiDao;
import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Projections;

import com.feraz.cfdi.model.ErpSatLeerCfdi;
import com.feraz.cfdi.model.ErpSatLeerCfdiId;
import com.feraz.facturas.webcontrolfe.model.ErpFeComprobantes;
import java.util.List;
import org.hibernate.Query;
import org.hibernate.criterion.Restrictions;

public class ErpSatLeerCfdiDaoImpl implements ErpSatLeerCfdiDao {

    private SessionFactory sessionFactory;

    public ErpSatLeerCfdiId save(ErpSatLeerCfdi erpSatLeerCfdi) {
        Session session = sessionFactory.openSession();
        Transaction transaccion = session.beginTransaction();
        ErpSatLeerCfdiId id = null;
        try {

            id = (ErpSatLeerCfdiId) session.save(erpSatLeerCfdi);
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
    public Integer findErpSatLeerCfdi(List<String> lista) {
        //  List<ErpNomPoliza> erpNomPoliza;
        Session session = sessionFactory.openSession();
        Transaction transaccion = session.beginTransaction();

        try {
            //System.out.println("lista:"+lista.size());
            if (lista == null || lista.isEmpty()) {
                return null;
            }
            String hql = "from ErpFeComprobantes where uuid in (:uuids)";
            Query q = session.createQuery(hql);
            List<ErpFeComprobantes> listad = q.setParameterList("uuids", lista).list();
//           System.out.println(listad.size());
            transaccion.commit();

        } catch (HibernateException e) {
            e.printStackTrace();
            transaccion.rollback();
            return null;
        } finally {

            session.close();

        }
        return 0;
    }

    public boolean delete(ErpSatLeerCfdi deleteErpSatLeerCfdi) {

        Session session = sessionFactory.openSession();
        Transaction transaccion = session.beginTransaction();
        try {

            session.delete(deleteErpSatLeerCfdi);
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

    public boolean update(ErpSatLeerCfdi updateErpSatLeerCfdi) {

        Session session = sessionFactory.openSession();
        Transaction transaccion = session.beginTransaction();

        try {

            session.update(updateErpSatLeerCfdi);
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

    public int getMaxId(ErpSatLeerCfdiId id) {

        Session session = sessionFactory.openSession();

        try {
            DetachedCriteria maxSec = DetachedCriteria.forClass(ErpSatLeerCfdi.class);

            Criteria criteria = maxSec.getExecutableCriteria(session);
            criteria.add(Restrictions.eq("id.compania", id.getCompania()));
            criteria.setProjection(Projections.max("id.id"));
            int campo = 0;
            List lista = criteria.list();
            if (lista.get(0) == null) {
                campo = 0;
            } else {
                System.out.println("lista.get(0).toString()" + lista.get(0).toString());
                campo = new Integer(lista.get(0).toString());
            }
            campo++;
            //String idCampo = Integer.toString(campo);
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
