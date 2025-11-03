/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.feraz.contabilidad.sat.electronica.dao.impl;

/**
 *
 * @author Feraz3
 */
import com.feraz.contabilidad.sat.electronica.dao.ErpSatRepAuxFolDetDao;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import com.feraz.contabilidad.sat.electronica.model.ErpSatRepAuxFolDet;

import java.util.List;

public class ErpSatRepAuxFolDetDaoImpl implements ErpSatRepAuxFolDetDao{
 private SessionFactory sessionFactory;

    public List<ErpSatRepAuxFolDet> FindErpSatRepAuxFolDe(String compania, String pid) {
        
        
        
        List<ErpSatRepAuxFolDet> erpSatRepAuxFolDet;
        Session session = sessionFactory.openSession();
        Transaction transaccion = session.beginTransaction();
        System.out.println("Compania:" + compania);
        System.out.println("pid:" + pid);
        try {

//            erpSatPoliza = (List<ErpSatPoliza>) session.createQuery("from ErpSatPoliza where id.compania  = :comp and id.pid = :pid")
            erpSatRepAuxFolDet = (List<ErpSatRepAuxFolDet>) session.createQuery("from ErpSatRepAuxFolDet where compania  = :comp and pid = :pid")
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

        return erpSatRepAuxFolDet;
        
    }

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }
    
    
    
}
