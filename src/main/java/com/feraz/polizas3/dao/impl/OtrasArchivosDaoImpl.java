/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.feraz.polizas3.dao.impl;

/**
 *
 * @author 55555
 */

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Projections;
import com.feraz.polizas3.dao.OtrasArchivosDao;
import com.feraz.polizas3.model.OtrasArchivos;
import com.feraz.polizas3.model.OtrasArchivosId;
import java.util.List;
import org.hibernate.Query;
import org.hibernate.criterion.Restrictions;

public class OtrasArchivosDaoImpl implements OtrasArchivosDao{
    
    private SessionFactory sessionFactory;

  

    @Override
    public OtrasArchivosId save(OtrasArchivos otrasArchivos) {
        
        
           Session session = sessionFactory.openSession();
        Transaction transaccion = session.beginTransaction();
        OtrasArchivosId id = null;
        try{
            
            id = (OtrasArchivosId)session.save(otrasArchivos);
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
    public List<OtrasArchivos> findOtrasArchivos() {
        
         List<OtrasArchivos> otrasArchivos;
        Session session = sessionFactory.openSession();
        Transaction transaccion = session.beginTransaction();
        
        try{
            
            otrasArchivos = (List<OtrasArchivos>)session.createQuery("from OtrasArchivos").list();
            transaccion.commit();
            
        }catch (HibernateException e){
            e.printStackTrace();
            transaccion.rollback();
            return null;
        }finally{
            
            session.close();
           
        }
        return otrasArchivos;
        
    }

    @Override
    public boolean delete(OtrasArchivos otrasArchivosDelete) {
        
          Session session = sessionFactory.openSession();
        Transaction transaccion = session.beginTransaction();
        try{
            System.out.println("Borrando detalle");
            session.delete(otrasArchivosDelete);
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
    public boolean update(OtrasArchivos updateOtrasArchivos) {
        
        Session session = sessionFactory.openSession();
        Transaction transaccion = session.beginTransaction();
        
        try{
            
            session.update(updateOtrasArchivos);
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
    public int getMaxId(OtrasArchivosId id) {
        
         Session session = sessionFactory.openSession();
        try {
            DetachedCriteria maxSec = DetachedCriteria.forClass(OtrasArchivos.class);


            Criteria criteria = maxSec.getExecutableCriteria(session);
            criteria.add(Restrictions.eq("id.compania", id.getCompania()));
            criteria.add(Restrictions.eq("id.id", id.getId()));
            criteria.setProjection(Projections.max("id.sec"));
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
