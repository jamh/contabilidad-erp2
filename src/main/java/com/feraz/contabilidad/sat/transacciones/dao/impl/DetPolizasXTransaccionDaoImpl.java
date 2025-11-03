/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.feraz.contabilidad.sat.transacciones.dao.impl;

import com.feraz.contabilidad.sat.transacciones.dao.DetPolizasXTransaccionDao;
import com.feraz.contabilidad.sat.transacciones.model.DetPolizasXTransaccion;
import com.feraz.contabilidad.sat.transacciones.model.DetPolizasXTransaccionId;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Projections;


import java.util.Date;
import java.util.Iterator;
import org.hibernate.Query;
import org.hibernate.criterion.Restrictions;

/**
 *
 * @author 55555
 */
public class DetPolizasXTransaccionDaoImpl implements DetPolizasXTransaccionDao{
    
    private SessionFactory sessionFactory;

    @Override
    public DetPolizasXTransaccionId save(DetPolizasXTransaccion transacciones) {
        
          Session session = sessionFactory.openSession();
        Transaction transaccion = session.beginTransaction();
        DetPolizasXTransaccionId id = null;
        try{
            
            id = (DetPolizasXTransaccionId) session.save(transacciones);
            transaccion.commit();
            
        }catch (HibernateException e){
            e.printStackTrace();
            transaccion.rollback();
            return null;
        }finally{
            
            session.close();
        }
        return id;
        
    }

    @Override
    public List<DetPolizasXTransaccion> findErpSatTransaccion(String compania) {
        
          List<DetPolizasXTransaccion> detPolizasXTransaccion;
        Session session = sessionFactory.openSession();
        Transaction transaccion = session.beginTransaction();
        
        try{
            
            detPolizasXTransaccion = (List<DetPolizasXTransaccion>)session.createQuery("from DetPolizasXTransaccion where "
                    + "id.compania = :compania ")
                        .setString("compania",compania)
                    .list();
            transaccion.commit();
            
        }catch (HibernateException e){
            e.printStackTrace();
            transaccion.rollback();
            return null;
        }finally{
            
            session.close();
           
        }
        return detPolizasXTransaccion;
        
    }

    @Override
    public boolean delete(DetPolizasXTransaccion deleteDetPolizasXTransaccion) {
        
          Session session = sessionFactory.openSession();
        Transaction transaccion = session.beginTransaction();
        try{
            
            session.delete(deleteDetPolizasXTransaccion);
            transaccion.commit();
            
        }catch (HibernateException e){
            
            e.printStackTrace();
            transaccion.rollback();
            return false;
            
        }finally{
            
            session.close();
            
        }
        return true;
        
    }

    @Override
    public boolean update(DetPolizasXTransaccion updateDetPolizasXTransaccion) {
        
          Session session = sessionFactory.openSession();
        Transaction transaccion = session.beginTransaction();
        
        try{
            
            session.update(updateDetPolizasXTransaccion);
            transaccion.commit();
            
        }catch (HibernateException e){
            e.printStackTrace();
            transaccion.rollback();
            return false;
            
        }finally{
            
            session.close();
            
        }
        return true;
        
        
    }
    
        public boolean borraIdTransaccion(String compania, String id_transaccion) {
          Session session = sessionFactory.openSession();
        Transaction transaccion = session.beginTransaction();
        try {
     
             
             String hql = "delete from DetPolizasXTransaccion where id.compania = :compania and id.idTransaccion = :id_transaccion";
        Query query = session.createQuery(hql);
        query.setString("compania",compania);
        query.setString("id_transaccion",id_transaccion);
        int rowCount = query.executeUpdate();
    //    System.out.println("Rows affected: " + rowCount);

            transaccion.commit();

        } catch (HibernateException e) {
            e.printStackTrace();
            transaccion.rollback();
            return false;
        }catch(Exception e2){
            e2.printStackTrace();
            transaccion.rollback();
        }
            finally {
            session.close();
        }
        return true;
    }
        
         public boolean borraTransaccionXPoliza(DetPolizasXTransaccion detPolizasXTransaccion) {
          Session session = sessionFactory.openSession();
        Transaction transaccion = session.beginTransaction();
        try {
     
             
            String hql = "delete from DetPolizasXTransaccion where id.compania = :compania and id.numero = :numero and id.tipoPoliza = :tipo_poliza and id.fecha = :fecha and id.sec=:sec";
        Query query = session.createQuery(hql);
        query.setString("compania",detPolizasXTransaccion.getId().getCompania());
        query.setString("numero",detPolizasXTransaccion.getId().getNumero());
        query.setString("tipo_poliza",detPolizasXTransaccion.getId().getTipoPoliza());
        query.setDate("fecha",detPolizasXTransaccion.getId().getFecha());
        query.setInteger("sec",detPolizasXTransaccion.getId().getSec());
        int rowCount = query.executeUpdate();
    //    System.out.println("Rows affected: " + rowCount);

            transaccion.commit();

        } catch (HibernateException e) {
            e.printStackTrace();
            transaccion.rollback();
            return false;
        }catch(Exception e2){
            e2.printStackTrace();
            transaccion.rollback();
        }
            finally {
            session.close();
        }
        return true;
    }
         
             public boolean borraTransaccionXPolizaCompl(DetPolizasXTransaccion detPolizasXTransaccion) {
          Session session = sessionFactory.openSession();
        Transaction transaccion = session.beginTransaction();
        try {
     
             
            String hql = "delete from DetPolizasXTransaccion where id.compania = :compania and id.numero = :numero and id.tipoPoliza = :tipo_poliza and id.fecha = :fecha";
        Query query = session.createQuery(hql);
        query.setString("compania",detPolizasXTransaccion.getId().getCompania());
        query.setString("numero",detPolizasXTransaccion.getId().getNumero());
        query.setString("tipo_poliza",detPolizasXTransaccion.getId().getTipoPoliza());
        query.setDate("fecha",detPolizasXTransaccion.getId().getFecha());
       
        int rowCount = query.executeUpdate();
    //    System.out.println("Rows affected: " + rowCount);

            transaccion.commit();

        } catch (HibernateException e) {
            e.printStackTrace();
            transaccion.rollback();
            return false;
        }catch(Exception e2){
            e2.printStackTrace();
            transaccion.rollback();
        }
            finally {
            session.close();
        }
        return true;
    }
             
              public boolean borraTransaccionXPolizaCompl2(DetPolizasXTransaccion detPolizasXTransaccion,String fecha) {
          Session session = sessionFactory.openSession();
        Transaction transaccion = session.beginTransaction();
        try {
     
             
            String hql = "delete from DetPolizasXTransaccion where id.compania = :compania and id.numero = :numero and id.tipoPoliza = :tipo_poliza and FECHA = TO_DATE('"+fecha+"','DD/MM/YYYY')";
        Query query = session.createQuery(hql);
        query.setString("compania",detPolizasXTransaccion.getId().getCompania());
        query.setString("numero",detPolizasXTransaccion.getId().getNumero());
        query.setString("tipo_poliza",detPolizasXTransaccion.getId().getTipoPoliza());
       
        int rowCount = query.executeUpdate();
    //    System.out.println("Rows affected: " + rowCount);

            transaccion.commit();

        } catch (HibernateException e) {
            e.printStackTrace();
            transaccion.rollback();
            return false;
        }catch(Exception e2){
            e2.printStackTrace();
            transaccion.rollback();
        }
            finally {
            session.close();
        }
        return true;
    }



    

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }
    
    
    
}
