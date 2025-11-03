/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.feraz.contabilidad.sat.diot.dao.impl;

/**
 *
 * @author Administrador
 */
import com.feraz.contabilidad.sat.diot.dao.DetDIOTDao;
import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Projections;

import com.feraz.contabilidad.sat.diot.model.DetDIOT;
import com.feraz.contabilidad.sat.diot.model.DetDIOTId;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import org.hibernate.Query;
import org.hibernate.criterion.Restrictions;
public class DetDIOTDaoImpl implements DetDIOTDao{
    
    private SessionFactory sessionFactory;

    public DetDIOTId save(DetDIOT detDIOT) {
        
         
         Session session = sessionFactory.openSession();
        Transaction transaccion = session.beginTransaction();
        DetDIOTId id = null;
        try{
            
            id = (DetDIOTId)session.save(detDIOT);
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

    public List<DetDIOT> FindDetDIOT(DetDIOT detDIOT) {
        
          List<DetDIOT> diot;
        Session session = sessionFactory.openSession();
        Transaction transaccion = session.beginTransaction();
        
        try{
            
            diot = (List<DetDIOT>)session.createQuery("from DetDIOT where "
                    + "id.compania = :compania "
                    + "and id.tipoPoliza = :tipoPoliza "
                    + "and id.fecha = :fecha "
                    + "and id.numero = :numero")
                        .setString("compania",detDIOT.getId().getCompania())
        .setString("tipoPoliza",detDIOT.getId().getTipoPoliza())
        .setDate("fecha",detDIOT.getId().getFecha())
        .setString("numero",detDIOT.getId().getNumero())
                    .list();
            transaccion.commit();
            
        }catch (HibernateException e){
            e.printStackTrace();
            transaccion.rollback();
            return null;
        }finally{
            
            session.close();
           
        }
        return diot;
                
    }

    public boolean delete(DetDIOT deleteDetDIOT) {
        
         Session session = sessionFactory.openSession();
        Transaction transaccion = session.beginTransaction();
        try{
            
            session.delete(deleteDetDIOT);
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

    public boolean update(DetDIOT updateDetDIOT) {
        
         Session session = sessionFactory.openSession();
        Transaction transaccion = session.beginTransaction();
        
        try{
            
            session.update(updateDetDIOT);
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
    
    
     public boolean deleteDiot(DetDIOT deleteDetDIOT) {
        Session session = sessionFactory.openSession();
        Transaction transaccion = session.beginTransaction();
        
        System.out.println("deleteDetDIOT.getId().getCompania()"+deleteDetDIOT.getId().getCompania());
        
        try {

            String hql = "delete from DetDIOT where id.compania = :compania and id.tipoPoliza = :tipoPoliza and id.fecha = :fecha and id.numero = :numero and id.sec = :sec and id.cuenta = :cuenta";
            Query query = session.createQuery(hql);
            query.setString("compania", deleteDetDIOT.getId().getCompania());
            query.setString("tipoPoliza", deleteDetDIOT.getId().getTipoPoliza());
            query.setDate("fecha", deleteDetDIOT.getId().getFecha());
            query.setString("numero", deleteDetDIOT.getId().getNumero());
            query.setInteger("sec", deleteDetDIOT.getId().getSec());
            query.setString("cuenta", deleteDetDIOT.getId().getCuenta());
           // query.setBigDecimal("sec", sec);
            int rowCount = query.executeUpdate();
            //    System.out.println("Rows affected: " + rowCount);

            transaccion.commit();
            //session.delete(deleteErpDetConvertidor);
            //transaccion.commit();

        } catch (HibernateException e) {
            e.printStackTrace();
            transaccion.rollback();
            return true;
        } finally {

            session.close();

        }
        return true;

    }
    
    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }
    
    
    
}
