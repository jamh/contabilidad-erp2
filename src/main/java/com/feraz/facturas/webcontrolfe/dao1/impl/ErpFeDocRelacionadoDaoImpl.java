/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.feraz.facturas.webcontrolfe.dao1.impl;

import com.feraz.facturas.webcontrolfe.dao1.ErpFeDocRelacionadoDao;
import com.feraz.facturas.webcontrolfe.model.ErpFeDocRelacionado;
import com.feraz.facturas.webcontrolfe.model.ErpFeDocRelacionadoId;
import com.feraz.facturas.webcontrolfe.model.ErpFePercepcion;
import java.util.List;
import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;

/**
 *
 * @author vavi
 */
public class ErpFeDocRelacionadoDaoImpl implements ErpFeDocRelacionadoDao{
    
    private SessionFactory sessionFactory;

    @Override
    public ErpFeDocRelacionadoId save(ErpFeDocRelacionado erpFeDocRelacionado) {
        
          Session session = sessionFactory.openSession();
        Transaction transaccion = session.beginTransaction();
        ErpFeDocRelacionadoId id = null;
        try{
            
            id = (ErpFeDocRelacionadoId)session.save(erpFeDocRelacionado);
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
    public List<ErpFeDocRelacionado> FindErpFeDocRelacionado() {
        
           List<ErpFeDocRelacionado> erpFeDocRelacionado;
        Session session = sessionFactory.openSession();
        Transaction transaccion = session.beginTransaction();

        try {

            erpFeDocRelacionado = (List<ErpFeDocRelacionado>) session.createQuery("from ErpFeDocRelacionado").list();
            transaccion.commit();
        } catch (HibernateException e) {
            transaccion.rollback();
            return null;
        } finally {

            session.close();

        }

        return erpFeDocRelacionado;
    }

    @Override
    public boolean delete(ErpFeDocRelacionado erpFeDocRelacionado) {
        
          
            Session session = sessionFactory.openSession();
        Transaction transaccion = session.beginTransaction();
        try{
          
            session.delete(erpFeDocRelacionado);
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
    public boolean update(ErpFeDocRelacionado erpFeDocRelacionado) {
        
         
         Session session = sessionFactory.openSession();
        Transaction transaccion = session.beginTransaction();
        
        try{
            
            session.update(erpFeDocRelacionado);
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
    public int getMaxIdErpFeDocRelacionado(ErpFeDocRelacionadoId id) {
        
            Session session = sessionFactory.openSession();
        try {
            DetachedCriteria maxSec = DetachedCriteria.forClass(ErpFeDocRelacionado.class);


            Criteria criteria = maxSec.getExecutableCriteria(session);
            criteria.add(Restrictions.eq("id.compania", id.getCompania()));
            criteria.add(Restrictions.eq("id.numero", id.getNumero()));
            criteria.add(Restrictions.eq("id.idPago", id.getIdPago()));
            criteria.setProjection(Projections.max("id.id"));
            int campo = 0;
            List lista = criteria.list();
            if (lista.get(0) == null) {
                campo = 0;
            } else {
                campo = new Integer(lista.get(0).toString());
            }
            campo++;
            
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
