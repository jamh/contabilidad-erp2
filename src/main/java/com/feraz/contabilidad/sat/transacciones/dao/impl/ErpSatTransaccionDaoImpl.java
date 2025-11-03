/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.feraz.contabilidad.sat.transacciones.dao.impl;

import com.feraz.contabilidad.sat.transacciones.dao.ErpSatTransaccionDao;
import com.feraz.contabilidad.sat.transacciones.model.ErpSatTransaccion;
import com.feraz.contabilidad.sat.transacciones.model.ErpSatTransaccionId;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Projections;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import org.hibernate.Query;
import org.hibernate.criterion.Restrictions;

/**
 *
 * @author Administrador
 */
public class ErpSatTransaccionDaoImpl implements ErpSatTransaccionDao{
    
    private SessionFactory sessionFactory;

    public ErpSatTransaccionId save(ErpSatTransaccion transacciones) {
        
          
         Session session = sessionFactory.openSession();
        Transaction transaccion = session.beginTransaction();
        ErpSatTransaccionId id = null;
        try{
            
            id = (ErpSatTransaccionId) session.save(transacciones);
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

    public List<ErpSatTransaccion> findErpSatTransaccion(String compania) {
        
         List<ErpSatTransaccion> erpSatTransaccion;
        Session session = sessionFactory.openSession();
        Transaction transaccion = session.beginTransaction();
        
        try{
            
            erpSatTransaccion = (List<ErpSatTransaccion>)session.createQuery("from ErpSatTransaccion where "
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
        return erpSatTransaccion;
        
    }

    public boolean delete(ErpSatTransaccion deleteErpSatTransaccion) {
        
           Session session = sessionFactory.openSession();
        Transaction transaccion = session.beginTransaction();
        try{
            
            session.delete(deleteErpSatTransaccion);
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

    public boolean update(ErpSatTransaccion updateErpSatTransaccion) {
        
         Session session = sessionFactory.openSession();
        Transaction transaccion = session.beginTransaction();
        
        try{
            
            session.update(updateErpSatTransaccion);
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

    public int getMaxId(ErpSatTransaccionId id) {
        
        Session session = sessionFactory.openSession();
      
        try {
            DetachedCriteria maxSec = DetachedCriteria.forClass(ErpSatTransaccion.class);


            Criteria criteria = maxSec.getExecutableCriteria(session);
            criteria.add(Restrictions.eq("id.compania", id.getCompania()));
            criteria.setProjection(Projections.max("id.id"));
            int campo = 0;
            List lista = criteria.list();
            if (lista.get(0) == null) {
                campo = 0;
            } else {
                campo = new Integer(lista.get(0).toString());
            }
            campo++;
            //String idCampo = Integer.toString(campo);
            return campo;
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        } finally {
            session.close();
        }
        
    }
    
    
      
    public boolean actualizaTransaccionCXC(ErpSatTransaccion comp) {
        Session session = sessionFactory.openSession();
        Transaction transaccion = session.beginTransaction();

        //System.out.println("actualizando pagos");
        try {

            String hql = "update ErpSatTransaccion set fecha =:fecha, montoTotal =:montoTotal, tipCamb = :tipCamb, bancoDestNal = :bancoDestNal, ctaDest = :ctaDest where  id.compania = :compania and id.id = :id";

            Query query = session.createQuery(hql);
            query.setString("compania", comp.getId().getCompania());
            query.setBigDecimal("id", comp.getId().getId());
            query.setDate("fecha", comp.getFecha());
            query.setBigDecimal("montoTotal", comp.getMontoTotal());
            //query.setString("moneda", comp.getMoneda());
            query.setBigDecimal("tipCamb", comp.getTipCamb());
            query.setString("bancoDestNal", comp.getBancoDestNal());
            query.setString("ctaDest", comp.getCtaDest());
            

//                System.out.println("com.IDCONCGASTO:" + com.IDCONCGASTO);
//                System.out.println("com.compania:" + compania);
//                System.out.println(" com.NUMERO:" + com.NUMERO);
            int rowCount = query.executeUpdate();
//                System.out.println("rowCount:" + rowCount);

            transaccion.commit();

        } catch (HibernateException e) {
            
            transaccion.rollback();
            return false;
        } finally {

            session.close();
        }

        return true;

    }
  
    public boolean actualizaTransaccionCXP(ErpSatTransaccion comp) {
        Session session = sessionFactory.openSession();
        Transaction transaccion = session.beginTransaction();

        //System.out.println("actualizando pagos");
        try {

            String hql = "update ErpSatTransaccion set fecha =:fecha, montoTotal =:montoTotal, tipCamb = :tipCamb, bancoOriNal = :bancoOriNal, ctaOri = :ctaOri where  id.compania = :compania and id.id = :id";

            Query query = session.createQuery(hql);
            query.setString("compania", comp.getId().getCompania());
            query.setBigDecimal("id", comp.getId().getId());
            query.setDate("fecha", comp.getFecha());
            query.setBigDecimal("montoTotal", comp.getMontoTotal());
            //query.setString("moneda", comp.getMoneda());
            query.setBigDecimal("tipCamb", comp.getTipCamb());
            query.setString("bancoOriNal", comp.getBancoOriNal());
            query.setString("ctaOri", comp.getCtaOri());
            

//                System.out.println("com.IDCONCGASTO:" + com.IDCONCGASTO);
//                System.out.println("com.compania:" + compania);
//                System.out.println(" com.NUMERO:" + com.NUMERO);
            int rowCount = query.executeUpdate();
//                System.out.println("rowCount:" + rowCount);

            transaccion.commit();

        } catch (HibernateException e) {
            
            transaccion.rollback();
            return false;
        } finally {

            session.close();
        }

        return true;

    }

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }
    
    
    
}
