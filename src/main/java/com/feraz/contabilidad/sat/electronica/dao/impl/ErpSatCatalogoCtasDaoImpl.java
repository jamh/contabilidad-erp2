/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.feraz.contabilidad.sat.electronica.dao.impl;

/**
 *
 * @author Feraz3
 */

import com.feraz.contabilidad.sat.electronica.dao.ErpSatCatalogoCtasDao;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import com.feraz.contabilidad.sat.electronica.model.ErpSatCatalogoCtas;
import com.feraz.contabilidad.sat.electronica.model.ErpSatCatalogoCtasId;
import java.util.List;

import java.util.List;
import org.hibernate.Criteria;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
public class ErpSatCatalogoCtasDaoImpl implements ErpSatCatalogoCtasDao{
    
    
    private SessionFactory sessionFactory;
    

    public List<ErpSatCatalogoCtas> FindErpSatCatalogoCtas(String compania,String pid) {
        
        
            List<ErpSatCatalogoCtas> erpSatCatalogoCtas;
        Session session = sessionFactory.openSession();
        Transaction transaccion = session.beginTransaction();
             System.out.println("Compania:"+ compania);
             System.out.println("pid:"+ pid);
        try {

            erpSatCatalogoCtas = (List<ErpSatCatalogoCtas>) session.createQuery("from ErpSatCatalogoCtas where id.compania  = :comp and id.pid = :pid")
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

        return erpSatCatalogoCtas;
    }

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }
    
    
    
}
