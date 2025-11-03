/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.feraz.facturas.webcontrolfe.dao1.impl;

import com.feraz.facturas.webcontrolfe.dao1.ErpFeConceptoXTrasladoDao;
import com.feraz.facturas.webcontrolfe.model.ErpFeConceptoXTraslado;
import com.feraz.facturas.webcontrolfe.model.ErpFeConceptoXTrasladoId;
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
public class ErpFeConceptoXTrasladoDaoImpl implements ErpFeConceptoXTrasladoDao{
    
     private SessionFactory sessionFactory;

    @Override
    public ErpFeConceptoXTrasladoId save(ErpFeConceptoXTraslado erpFeConceptoXTraslado) {
        
         Session session = sessionFactory.openSession();
        Transaction transaccion = session.beginTransaction();
        ErpFeConceptoXTrasladoId id = null;
        try{
            
            id = (ErpFeConceptoXTrasladoId)session.save(erpFeConceptoXTraslado);
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
    public List<ErpFeConceptoXTraslado> FindErpFeConceptoXTraslado() {
        
        List<ErpFeConceptoXTraslado> erpFeConceptoXTraslado;
        Session session = sessionFactory.openSession();
        Transaction transaccion = session.beginTransaction();

        try {

            erpFeConceptoXTraslado = (List<ErpFeConceptoXTraslado>) session.createQuery("from ErpFeConceptoXTraslado").list();
            transaccion.commit();
        } catch (HibernateException e) {
            transaccion.rollback();
            return null;
        } finally {

            session.close();

        }

        return erpFeConceptoXTraslado;
        
    }

    @Override
    public boolean delete(ErpFeConceptoXTraslado deleteErpFeConceptoXTraslado) {
        
         Session session = sessionFactory.openSession();
        Transaction transaccion = session.beginTransaction();
        try{
          
            session.delete(deleteErpFeConceptoXTraslado);
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
    public boolean update(ErpFeConceptoXTraslado updateErpFeConceptoXTraslado) {
        
        
         Session session = sessionFactory.openSession();
        Transaction transaccion = session.beginTransaction();
        
        try{
            
            session.update(updateErpFeConceptoXTraslado);
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

            String hql = "delete from ErpFeConceptoXTraslado where id.compania = :compania and id.numero = :numero";
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
    public int getMaxIdConceptoXTraslado(ErpFeConceptoXTrasladoId id) {
        
        
        Session session = sessionFactory.openSession();
        try {
            DetachedCriteria maxSec = DetachedCriteria.forClass(ErpFeConceptoXTraslado.class);


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
