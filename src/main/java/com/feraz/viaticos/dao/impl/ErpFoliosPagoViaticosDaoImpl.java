/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.feraz.viaticos.dao.impl;

import com.feraz.viaticos.dao.ErpFoliosPagoViaticosDao;
import com.feraz.viaticos.model.ErpFoliosPagoViaticos;
import com.feraz.viaticos.model.ErpFoliosPagoViaticosId;
import org.hibernate.SessionFactory;

import java.util.List;
import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import java.text.SimpleDateFormat;


/**
 *
 * @author vavi
 */
public class ErpFoliosPagoViaticosDaoImpl implements ErpFoliosPagoViaticosDao{
    
    private SessionFactory sessionFactory;

    @Override
    public ErpFoliosPagoViaticosId save(ErpFoliosPagoViaticos via) {
        
          
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        
        ErpFoliosPagoViaticosId id = null;
        
        try{
             id = (ErpFoliosPagoViaticosId) session.save(via);
            transaction.commit();
                    
        }catch(HibernateException e){
           e.printStackTrace();
            
            
            transaction.rollback();
           return null;
        }finally{
        
            session.close();
        }
        
        return id;
        
    }

    @Override
    public boolean update(ErpFoliosPagoViaticos via) {
        
          Session session = sessionFactory.openSession();
          Transaction transaction = session.beginTransaction();
          
         
        try{
             session.update(via);
          
              transaction.commit();
        
        }catch(HibernateException e){
            e.printStackTrace();
            transaction.rollback();
            return false;
        
        }finally{
            
            session.close();
        
        }
        
        return true;
        
        
    }
    
     @Override
    public boolean delete(ErpFoliosPagoViaticos via) {
        
          Session session = sessionFactory.openSession();
          Transaction transaction = session.beginTransaction();
          
         
        try{
             session.delete(via);
          
              transaction.commit();
        
        }catch(HibernateException e){
            e.printStackTrace();
            transaction.rollback();
            return false;
        
        }finally{
            
            session.close();
        
        }
        
        return true;
        
        
    }
    
    public boolean actualizaEstatusFolio(ErpFoliosPagoViaticos comp) {
        Session session = sessionFactory.openSession();
        Transaction transaccion = session.beginTransaction();

        //System.out.println("actualizando pagos");
        try {

            String hql = "update ErpFoliosPagoViaticos set estatus =:estatus, fechaAutorizacion =:fechaAutorizacion, usuarioAutorizo =:usuarioAutorizo where  id.compania = :compania and folio = :folio and comision = :comision";

            Query query = session.createQuery(hql);
            query.setString("compania", comp.getId().getCompania());
            query.setInteger("folio", comp.getId().getFolio());
            query.setBigDecimal("comision", comp.getId().getComision());
            query.setString("estatus", comp.getEstatus());
            query.setDate("fechaAutorizacion", comp.getFechaAutorizacion());
            query.setString("usuarioAutorizo", comp.getUsuarioAutorizo());


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
    

    @Override
    public int getMaxCxpFolios(ErpFoliosPagoViaticosId id) {
        
         Session session = sessionFactory.openSession();
        try {
            DetachedCriteria maxSec = DetachedCriteria.forClass(ErpFoliosPagoViaticos.class);


            Criteria criteria = maxSec.getExecutableCriteria(session);
            criteria.add(Restrictions.eq("id.compania", id.getCompania()));
            criteria.setProjection(Projections.max("id.folio"));
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
    

     

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }
    
    
    
    
}
