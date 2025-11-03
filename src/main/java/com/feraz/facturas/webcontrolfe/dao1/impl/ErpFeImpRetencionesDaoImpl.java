/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.feraz.facturas.webcontrolfe.dao1.impl;

/**
 *
 * @author Feraz3
 */

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import com.feraz.facturas.webcontrolfe.model.ErpFeImpRetenciones;
import com.feraz.facturas.webcontrolfe.model.ErpFeImpRetencionesId;
import com.feraz.facturas.webcontrolfe.dao1.ErpFeImpRetencionesDao;
import java.math.BigDecimal;
import java.util.List;
import org.hibernate.Query;
public class ErpFeImpRetencionesDaoImpl implements ErpFeImpRetencionesDao {
    
     private SessionFactory sessionFactory;

    public ErpFeImpRetencionesId save(ErpFeImpRetenciones erpImpRetenciones) {
        
         Session session = sessionFactory.openSession();
        Transaction transaccion = session.beginTransaction();
        ErpFeImpRetencionesId id = null;
        try{
            
            id = (ErpFeImpRetencionesId)session.save(erpImpRetenciones);
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

    public List<ErpFeImpRetenciones> findErpImpRetenciones(String compania, int numero) {
        
          List<ErpFeImpRetenciones> erpImpRetenciones;
        Session session = sessionFactory.openSession();
        Transaction transaccion = session.beginTransaction();

        try {

            erpImpRetenciones = (List<ErpFeImpRetenciones>) session.createQuery("from ErpFeImpRetenciones where id.compania = :compania and id.numero = :numero")
                    .setString("compania", compania)
                     .setInteger("numero",new Integer(numero))
                    .list();
            transaccion.commit();
        } catch (HibernateException e) {
            transaccion.rollback();
            return null;
        } finally {

            session.close();

        }

        return erpImpRetenciones;
    }
    
     public boolean deletePorFactura(String compania, int numero) {
        Session session = sessionFactory.openSession();
        Transaction transaccion = session.beginTransaction();
        
        System.out.println("borrando retenciones");

        try {

            String hql = "delete from ErpFeImpRetenciones where id.compania = :compania and id.numero = :numero";
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

    public boolean delete(ErpFeImpRetenciones deleteErpImpRetenciones) {
        
            Session session = sessionFactory.openSession();
        Transaction transaccion = session.beginTransaction();
        try{
          
            session.delete(deleteErpImpRetenciones);
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

    public boolean update(ErpFeImpRetenciones updateErpImpRetenciones) {
        
          Session session = sessionFactory.openSession();
        Transaction transaccion = session.beginTransaction();
        
        try{
            
            session.update(updateErpImpRetenciones);
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

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }
    
    
    
}
