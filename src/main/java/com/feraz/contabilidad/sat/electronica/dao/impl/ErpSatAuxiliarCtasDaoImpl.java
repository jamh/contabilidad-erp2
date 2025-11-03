/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.feraz.contabilidad.sat.electronica.dao.impl;

/**
 *
 * @author Feraz3
 */
import com.feraz.contabilidad.sat.electronica.dao.ErpSatAuxiliarCtasDao;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import com.feraz.contabilidad.sat.electronica.model.ErpSatAuxiliarCtas;
public class ErpSatAuxiliarCtasDaoImpl implements ErpSatAuxiliarCtasDao{

    
    private SessionFactory sessionFactory;
    @Override
    public ErpSatAuxiliarCtas findErpSatAuxiliarCtas(String compania, String pid) {
        
        
        
            ErpSatAuxiliarCtas erpSatAuxiliarCtas;
        Session session = sessionFactory.openSession();
        Transaction transaccion = session.beginTransaction();

        try {

            erpSatAuxiliarCtas = (ErpSatAuxiliarCtas) session.createQuery("from ErpSatAuxiliarCtas where compania= :compania and pid= :pid")
                    .setString("compania", compania)
                    .setString("pid", pid)
                    .setMaxResults(1)
                    .uniqueResult();
            
            System.out.println("erpSatRepAuxFol.getAnio()"+erpSatAuxiliarCtas.getAnio());
            
            transaccion.commit();
        } catch (HibernateException e) {
            e.printStackTrace();
            transaccion.rollback();
            return null;
        } finally {

            session.close();

        }

        return erpSatAuxiliarCtas;
        
    }

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }
    
    
}
