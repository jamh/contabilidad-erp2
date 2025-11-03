/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.feraz.contabilidad.convertidor.dao.impl;

import com.feraz.contabilidad.convertidor.dao.ErpParidadDao;
import com.feraz.contabilidad.convertidor.model.ErpParidad;
import com.feraz.contabilidad.convertidor.model.ErpParidadId;
import java.util.Date;

import java.util.List;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

/**
 *
 * @author Ing. JAMH
 */
public class ErpParidadDaoImpl implements  ErpParidadDao{
    private SessionFactory sessionFactory;

    public ErpParidadId save(ErpParidad erpParidad) {
         Session session = sessionFactory.openSession();
        Transaction transaccion = session.beginTransaction();
        ErpParidadId id = null;
        try {

            id = (ErpParidadId) session.save(erpParidad);
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

    public List<ErpParidad> findErpParidad() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public boolean delete(ErpParidad deleteErpParidad) {
        
          Session session = sessionFactory.openSession();
        Transaction transaccion = session.beginTransaction();

        try {

            session.delete(deleteErpParidad);
            transaccion.commit();

        } catch (HibernateException e) {
            e.printStackTrace();
            transaccion.rollback();
            return true;
        } finally {

            session.close();

        }
        return true;
    }

    public boolean update(ErpParidad updateErpParidad) {
        
         Session session = sessionFactory.openSession();
        Transaction transaccion = session.beginTransaction();

        try {

            session.update(updateErpParidad);
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

    public ErpParidad buscaUltimaParidad(String divisaOrigen,String divisaDestino,Date fecha) {
        ErpParidad erpParidad;
        Session session = sessionFactory.openSession();
        Transaction transaccion = session.beginTransaction();
//          System.out.println(divisaOrigen);  
//          System.out.println(divisaDestino);
//          System.out.println(fecha);
          
        try {

            erpParidad = (ErpParidad) session.createQuery("from ErpParidad where id.divisaOrigen = :divisaOr  and id.divisaDestino = :divisaDes and id.fecha = (select max(id.fecha) from ErpParidad where id.fecha < :fecha )")
                    .setString("divisaOr",divisaOrigen)
                    .setString("divisaDes",divisaDestino)
                    .setDate("fecha",fecha)
                     .setMaxResults(1).uniqueResult();
                    
             System.out.println(erpParidad);

            transaccion.commit();
        } catch (Exception e) {
            e.printStackTrace();
            transaccion.rollback();
            return null;
        } finally {

            session.close();

        }

        return erpParidad;
    }

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }
    
    
    
}
