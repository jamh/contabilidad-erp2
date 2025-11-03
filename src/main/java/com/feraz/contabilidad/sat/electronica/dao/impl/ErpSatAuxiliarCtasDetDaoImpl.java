/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.feraz.contabilidad.sat.electronica.dao.impl;

/**
 *
 * @author Feraz3
 */
import com.feraz.contabilidad.sat.electronica.dao.ErpSatAuxiliarCtasDetDao;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import com.feraz.contabilidad.sat.electronica.model.ErpSatAuxiliarCtasDet;

import java.util.List;
public class ErpSatAuxiliarCtasDetDaoImpl implements ErpSatAuxiliarCtasDetDao{
  
     private SessionFactory sessionFactory;
    @Override
    public List<ErpSatAuxiliarCtasDet> FindErpSatAuxiliarCtasDet(String compania, String pid) {
        
        
            
        List<ErpSatAuxiliarCtasDet> erpSatAuxiliarCtasDet;
        Session session = sessionFactory.openSession();
        Transaction transaccion = session.beginTransaction();
        
        System.out.println("Buscando detalle de auxiliar:" + compania);
        
        System.out.println("Compania:" + compania);
        System.out.println("pid:" + pid);
        try {

//            erpSatPoliza = (List<ErpSatPoliza>) session.createQuery("from ErpSatPoliza where id.compania  = :comp and id.pid = :pid")
            erpSatAuxiliarCtasDet = (List<ErpSatAuxiliarCtasDet>) session.createQuery("from ErpSatAuxiliarCtasDet where compania  = :comp and pid = :pid order by fecha asc,sec asc")
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

        return erpSatAuxiliarCtasDet;
        
        
    }
    
       @Override
    public List<ErpSatAuxiliarCtasDet> FindErpSatAuxiliarCtasDet2(String compania, String pid, String cuenta) {
        
        
            
        List<ErpSatAuxiliarCtasDet> erpSatAuxiliarCtasDet;
        Session session = sessionFactory.openSession();
        Transaction transaccion = session.beginTransaction();
//        System.out.println("Compania:" + compania);
//        System.out.println("pid:" + pid);
        try {

//            erpSatPoliza = (List<ErpSatPoliza>) session.createQuery("from ErpSatPoliza where id.compania  = :comp and id.pid = :pid")
            erpSatAuxiliarCtasDet = (List<ErpSatAuxiliarCtasDet>) session.createQuery("from ErpSatAuxiliarCtasDet where compania  = :comp and pid = :pid and numCta =:cuenta order by fecha asc,sec asc")
                    .setString("comp", compania)
                    .setString("pid", pid)
                    .setString("cuenta", cuenta).list();
            transaccion.commit();

        } catch (HibernateException e) {
            e.printStackTrace();
            transaccion.rollback();
            return null;
        } finally {

            session.close();

        }

        return erpSatAuxiliarCtasDet;
        
        
    }


    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }
    
}
