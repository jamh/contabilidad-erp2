/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.feraz.facturas.webcontrolfe.dao1.impl;

import com.feraz.facturas.webcontrolfe.dao1.ErpFeConceptoXRetencionDao;
import com.feraz.facturas.webcontrolfe.model.ErpFeConceptoXRetencion;
import com.feraz.facturas.webcontrolfe.model.ErpFeConceptoXRetencionId;
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

/**
 *
 * @author vavi
 */
public class ErpFeConceptoXRetencionDaoImpl implements ErpFeConceptoXRetencionDao{
    
    
    private SessionFactory sessionFactory;

    @Override
    public ErpFeConceptoXRetencionId save(ErpFeConceptoXRetencion erpFeConceptoXRetencion) {
        
        Session session = sessionFactory.openSession();
        Transaction transaccion = session.beginTransaction();
        ErpFeConceptoXRetencionId id = null;
        try{
            
            id = (ErpFeConceptoXRetencionId)session.save(erpFeConceptoXRetencion);
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
    public List<ErpFeConceptoXRetencion> FindErpFeConceptoXRetencion() {
        
        
          List<ErpFeConceptoXRetencion> erpFeConceptoXRetencion;
        Session session = sessionFactory.openSession();
        Transaction transaccion = session.beginTransaction();

        try {

            erpFeConceptoXRetencion = (List<ErpFeConceptoXRetencion>) session.createQuery("from ErpFeConceptoXRetencion").list();
            transaccion.commit();
        } catch (HibernateException e) {
            transaccion.rollback();
            return null;
        } finally {

            session.close();

        }

        return erpFeConceptoXRetencion;
        
        
    }

    @Override
    public boolean delete(ErpFeConceptoXRetencion deleteErpFeConceptoXRetencion) {
        
        Session session = sessionFactory.openSession();
        Transaction transaccion = session.beginTransaction();
        try{
          
            session.delete(deleteErpFeConceptoXRetencion);
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
    public boolean update(ErpFeConceptoXRetencion updateErpFeConceptoXRetencion) {
        
         Session session = sessionFactory.openSession();
        Transaction transaccion = session.beginTransaction();
        
        try{
            
            session.update(updateErpFeConceptoXRetencion);
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
    public boolean deletePorFactura(String compania, int numero) {
        
         Session session = sessionFactory.openSession();
        Transaction transaccion = session.beginTransaction();
        
        System.out.println("borrando conceptos");

        try {

            String hql = "delete from ErpFeConceptoXRetencion where id.compania = :compania and id.numero = :numero";
            Query query = session.createQuery(hql);
            query.setString("compania", compania);
            query.setInteger("numero", numero);
            
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

    @Override
    public int getMaxIdConceptoXTraslado(ErpFeConceptoXRetencionId id) {
        
          Session session = sessionFactory.openSession();
        try {
            DetachedCriteria maxSec = DetachedCriteria.forClass(ErpFeConceptoXRetencion.class);


            Criteria criteria = maxSec.getExecutableCriteria(session);
            criteria.add(Restrictions.eq("id.compania", id.getCompania()));
            criteria.add(Restrictions.eq("id.numero", id.getNumero()));
            criteria.add(Restrictions.eq("id.idConcepto", id.getIdConcepto()));
            criteria.setProjection(Projections.max("id.sec"));
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
