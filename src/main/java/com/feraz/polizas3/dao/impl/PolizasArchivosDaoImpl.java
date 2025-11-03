/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.feraz.polizas3.dao.impl;

/**
 *
 * @author Feraz3
 */

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Projections;
import com.feraz.polizas3.dao.PolizasArchivosDao;
import com.feraz.polizas3.model.PolizasArchivos;
import com.feraz.polizas3.model.PolizasArchivosId;
import java.util.List;
import org.hibernate.Query;
import org.hibernate.criterion.Restrictions;


public class PolizasArchivosDaoImpl implements PolizasArchivosDao{
    
    private SessionFactory sessionFactory;

    public PolizasArchivosId save(PolizasArchivos polizasArchivos) {
        
        Session session = sessionFactory.openSession();
        Transaction transaccion = session.beginTransaction();
        PolizasArchivosId id = null;
        try{
            
            id = (PolizasArchivosId)session.save(polizasArchivos);
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

    public List<PolizasArchivos> findArchivosPolizas() {
        
         List<PolizasArchivos> polizasArchivos;
        Session session = sessionFactory.openSession();
        Transaction transaccion = session.beginTransaction();
        
        try{
            
            polizasArchivos = (List<PolizasArchivos>)session.createQuery("from PolizasArchivos").list();
            transaccion.commit();
            
        }catch (HibernateException e){
            e.printStackTrace();
            transaccion.rollback();
            return null;
        }finally{
            
            session.close();
           
        }
        return polizasArchivos;
        
    }

    public boolean delete(PolizasArchivos deleteArchivos) {
      
        Session session = sessionFactory.openSession();
        Transaction transaccion = session.beginTransaction();
        try{
            System.out.println("Borrando detalle");
            session.delete(deleteArchivos);
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

    public boolean update(PolizasArchivos updateArchivos) {
        
         Session session = sessionFactory.openSession();
        Transaction transaccion = session.beginTransaction();
        
        try{
            
            session.update(updateArchivos);
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

    public int getMaxId(PolizasArchivosId id) {
        
         Session session = sessionFactory.openSession();
        try {
            DetachedCriteria maxSec = DetachedCriteria.forClass(PolizasArchivos.class);


            Criteria criteria = maxSec.getExecutableCriteria(session);
            criteria.add(Restrictions.eq("id.compania", id.getCompania()));
            criteria.add(Restrictions.eq("id.tipoPoliza", id.getTipoPoliza()));
            criteria.add(Restrictions.eq("id.fecha", id.getFecha()));
            criteria.add(Restrictions.eq("id.numero", id.getNumero()));
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
