/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.feraz.contabilidad.convertidor.dao.impl;

import com.feraz.contabilidad.convertidor.dao.ErpParidadCompaniaDao;
import com.feraz.contabilidad.convertidor.model.ErpParidadCompania;
import com.feraz.contabilidad.convertidor.model.ErpParidadCompaniaId;
import java.util.Date;
import java.util.List;


import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;


/**
 *
 * @author 55555
 */
public class ErpParidadCompaniaDaoImpl implements ErpParidadCompaniaDao{
    
    private SessionFactory sessionFactory;

    @Override
    public ErpParidadCompaniaId save(ErpParidadCompania erpParidadCompania) {
        
          Session session = sessionFactory.openSession();
        Transaction transaccion = session.beginTransaction();
        ErpParidadCompaniaId id = null;
        try {

            id = (ErpParidadCompaniaId) session.save(erpParidadCompania);
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

    @Override
    public List<ErpParidadCompania> findErpParidad() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean delete(ErpParidadCompania deleteErpParidad) {
        
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

    @Override
    public boolean update(ErpParidadCompania updateErpParidad) {
        
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
    
        public ErpParidadCompania buscaUltimaParidad(String divisaOrigen,String divisaDestino,Date fecha) {
        ErpParidadCompania erpParidadCompania;
        Session session = sessionFactory.openSession();
        Transaction transaccion = session.beginTransaction();
          System.out.println(divisaOrigen);  
          System.out.println(divisaDestino);
          System.out.println(fecha);
          
        try {

            erpParidadCompania = (ErpParidadCompania) session.createQuery("from ErpParidadCompania where id.divisaOrigen = :divisaOr  and id.divisaDestino = :divisaDes and id.fecha = (select max(id.fecha) from ErpParidadCompania where id.fecha < :fecha )")
                    .setString("divisaOr",divisaOrigen)
                    .setString("divisaDes",divisaDestino)
                    .setDate("fecha",fecha)
                     .setMaxResults(1).uniqueResult();
                    
             //System.out.println(erpParidad);

            transaccion.commit();
        } catch (Exception e) {
            e.printStackTrace();
            transaccion.rollback();
            return null;
        } finally {

            session.close();

        }

        return erpParidadCompania;
    }

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }
    
}
