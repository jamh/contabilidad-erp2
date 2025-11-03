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
import com.feraz.facturas.webcontrolfe.model.ErpFeImpTraslados;
import com.feraz.facturas.webcontrolfe.model.ErpFeImpTrasladosId;
import com.feraz.facturas.webcontrolfe.dao1.ErpFeImpTrasladosDao;
import java.util.List;
import org.hibernate.Query;
public class ErpFeImpTrasladosDaoImpl implements ErpFeImpTrasladosDao{
    
     private SessionFactory sessionFactory;

    public ErpFeImpTrasladosId save(ErpFeImpTraslados erpImpTraslados) {
        
         Session session = sessionFactory.openSession();
        Transaction transaccion = session.beginTransaction();
        ErpFeImpTrasladosId id = null;
        try{
            
            id = (ErpFeImpTrasladosId)session.save(erpImpTraslados);
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

    public List<ErpFeImpTraslados> findErpImpTraslados(String compania, int numero) {
        
         
         List<ErpFeImpTraslados> erpImpTraslados;
        Session session = sessionFactory.openSession();
        Transaction transaccion = session.beginTransaction();

        try {

            erpImpTraslados = (List<ErpFeImpTraslados>) session.createQuery("from ErpFeImpTraslados where id.compania = :compania and id.numero = :numero")
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

        return erpImpTraslados;
    }
    
    public boolean deletePorFactura(String compania, int numero) {
        Session session = sessionFactory.openSession();
        Transaction transaccion = session.beginTransaction();
        
        System.out.println("borrando translados");

        try {

            String hql = "delete from ErpFeImpTraslados where id.compania = :compania and id.numero = :numero";
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
    
    public boolean delete(ErpFeImpTraslados deleteErpImpTraslados) {
        
         Session session = sessionFactory.openSession();
        Transaction transaccion = session.beginTransaction();
        try{
          
            session.delete(deleteErpImpTraslados);
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

    public boolean update(ErpFeImpTraslados updateErpImpTraslados) {
        
         Session session = sessionFactory.openSession();
        Transaction transaccion = session.beginTransaction();
        
        try{
            
            session.update(updateErpImpTraslados);
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
