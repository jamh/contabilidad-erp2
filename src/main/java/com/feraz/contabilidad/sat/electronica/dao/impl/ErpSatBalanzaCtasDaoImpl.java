/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.feraz.contabilidad.sat.electronica.dao.impl;

/**
 *
 * @author Feraz3
 */
import com.feraz.contabilidad.sat.electronica.dao.ErpSatBalanzaCtasDao;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import com.feraz.contabilidad.sat.electronica.model.ErpSatBalanzaCtas;

import java.util.List;
public class ErpSatBalanzaCtasDaoImpl implements ErpSatBalanzaCtasDao{

    private SessionFactory sessionFactory;
       
    public List<ErpSatBalanzaCtas> FindErpSatBalanzaCtas(String compania,String pid) {
        
             List<ErpSatBalanzaCtas> erpSatBalanzaCtas;
        Session session = sessionFactory.openSession();
        Transaction transaccion = session.beginTransaction();
             System.out.println("Compania:"+ compania);
             System.out.println("pid:"+ pid);
        try {

            erpSatBalanzaCtas = (List<ErpSatBalanzaCtas>) session.createQuery("from ErpSatBalanzaCtas where id.compania  = :comp and id.pid = :pid")
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

        return erpSatBalanzaCtas;
    }

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }
    
    
}
