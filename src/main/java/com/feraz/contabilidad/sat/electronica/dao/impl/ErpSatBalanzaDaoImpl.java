/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.feraz.contabilidad.sat.electronica.dao.impl;

/**
 *
 * @author Feraz3
 */
import com.feraz.contabilidad.sat.electronica.dao.ErpSatBalanzaDao;
import com.feraz.contabilidad.sat.electronica.model.ErpSatBalanza;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import java.util.List;

public class ErpSatBalanzaDaoImpl implements ErpSatBalanzaDao {

    private SessionFactory sessionFactory;

    public List<ErpSatBalanza> FindErpSatBalanza(String compania, String pid) {

        List<ErpSatBalanza> erpSatBalanza;
        Session session = sessionFactory.openSession();
        Transaction transaccion = session.beginTransaction();
             System.out.println("Compania:"+ compania);
             System.out.println("pid:"+ pid);
        try {

            erpSatBalanza = (List<ErpSatBalanza>) session.createQuery("from ErpSatBalanza where compania  = :comp and pid = :pid")
                    .setString("comp", compania)
                    .setString("pid", pid).list();
            transaccion.commit();

        } catch (HibernateException e) {
            e.printStackTrace();
            transaccion.rollback();
            return null;
        } finally {

            session.close();

        }

        return erpSatBalanza;
    }

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

}
