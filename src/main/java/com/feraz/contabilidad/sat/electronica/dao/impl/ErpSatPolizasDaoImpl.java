/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.feraz.contabilidad.sat.electronica.dao.impl;

/**
 *
 * @author Feraz3
 */

import com.feraz.contabilidad.sat.electronica.dao.ErpSatPolizasDao;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import com.feraz.contabilidad.sat.electronica.model.ErpSatPolizas;

public class ErpSatPolizasDaoImpl implements ErpSatPolizasDao{
    
    private SessionFactory sessionFactory;

    public ErpSatPolizas findErpSatPolizas(String compania,String pid) {
        
        
            ErpSatPolizas erpSatPolizas;
        Session session = sessionFactory.openSession();
        Transaction transaccion = session.beginTransaction();

        try {

            erpSatPolizas = (ErpSatPolizas) session.createQuery("from ErpSatPolizas where compania= :compania and pid= :pid")
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

        return erpSatPolizas;
    }

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }
    
    
    
    
}
