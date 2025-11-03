/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.feraz.contabilidad.sat.electronica.dao.impl;

import com.feraz.contabilidad.sat.electronica.dao.ErpSatCatalogoDao;
import com.feraz.contabilidad.sat.electronica.model.ErpSatCatalogo;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

/**
 *
 * @author Administrador
 */
public class ErpSatCatalogoDaoImpl implements ErpSatCatalogoDao{
      private SessionFactory sessionFactory;

    public ErpSatCatalogo findErpSatCatalogo(String compania, String pid) {
                ErpSatCatalogo erpSatCatalogo;
        Session session = sessionFactory.openSession();
        Transaction transaccion = session.beginTransaction();

        try {

            erpSatCatalogo = (ErpSatCatalogo) session.createQuery("from ErpSatCatalogo where compania= :compania and pid= :pid")
                    .setString("compania", compania)
                    .setString("pid", pid)
                    .setMaxResults(1)
                    .uniqueResult();
            transaccion.commit();
        } catch (HibernateException e) {
            e.printStackTrace();
            transaccion.rollback();
            return null;
        } finally {

            session.close();

        }

        return erpSatCatalogo;
    }

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }
    
}
