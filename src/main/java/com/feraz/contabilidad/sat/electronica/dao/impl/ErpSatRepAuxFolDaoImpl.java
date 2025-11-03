/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.feraz.contabilidad.sat.electronica.dao.impl;

/**
 *
 * @author Feraz3
 */
import com.feraz.contabilidad.sat.electronica.dao.ErpSatRepAuxFolDao;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import com.feraz.contabilidad.sat.electronica.model.ErpSatRepAuxFol;
public class ErpSatRepAuxFolDaoImpl implements ErpSatRepAuxFolDao{
  
    
     private SessionFactory sessionFactory;
    
    @Override
    public ErpSatRepAuxFol findErpSatRepAuxFol(String compania, String pid) {
        
        
        
            ErpSatRepAuxFol erpSatRepAuxFol;
        Session session = sessionFactory.openSession();
        Transaction transaccion = session.beginTransaction();

        try {

            erpSatRepAuxFol = (ErpSatRepAuxFol) session.createQuery("from ErpSatRepAuxFol where compania= :compania and pid= :pid")
                    .setString("compania", compania)
                    .setString("pid", pid)
                    .setMaxResults(1)
                    .uniqueResult();
            
//            System.out.println("erpSatRepAuxFol.getAnio()"+erpSatRepAuxFol.getAnio());
            
            transaccion.commit();
        } catch (HibernateException e) {
            e.printStackTrace();
            transaccion.rollback();
            return null;
        } finally {

            session.close();

        }

        return erpSatRepAuxFol;
    }

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }
    
}
