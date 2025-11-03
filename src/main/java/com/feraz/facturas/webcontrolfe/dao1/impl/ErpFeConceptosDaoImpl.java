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
import com.feraz.facturas.webcontrolfe.model.ErpFeConceptos;
import com.feraz.facturas.webcontrolfe.model.ErpFeConceptosId;
import com.feraz.facturas.webcontrolfe.dao1.ErpFeConceptosDao;
import com.feraz.facturas.webcontrolfe.model.ErpFeComprobantes;
import com.feraz.facturas.webcontrolfe.model.ErpFeComprobantesId;
import java.math.BigDecimal;
import java.util.List;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
public class ErpFeConceptosDaoImpl implements ErpFeConceptosDao {
    
     private SessionFactory sessionFactory;


    public ErpFeConceptosId save(ErpFeConceptos erpFeConceptos) {
        
         Session session = sessionFactory.openSession();
        Transaction transaccion = session.beginTransaction();
        ErpFeConceptosId id = null;
        try{
            
            id = (ErpFeConceptosId)session.save(erpFeConceptos);
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
    

    public List<ErpFeConceptos> findErpFeConceptos2(String compania,String numero) {
        
         List<ErpFeConceptos> erpFeConceptos;
        Session session = sessionFactory.openSession();
        Transaction transaccion = session.beginTransaction();

        try {

            erpFeConceptos = (List<ErpFeConceptos>) session.createQuery("from ErpFeConceptos where id.compania = '"+compania+"' and id.numero = "+numero).list();
            transaccion.commit();
        } catch (HibernateException e) {
            transaccion.rollback();
            return null;
        } finally {

            session.close();

        }

        return erpFeConceptos;
    }
    
    public List<ErpFeConceptos> FindErpFeConceptos() {
        
         List<ErpFeConceptos> erpFeConceptos;
        Session session = sessionFactory.openSession();
        Transaction transaccion = session.beginTransaction();

        try {

            erpFeConceptos = (List<ErpFeConceptos>) session.createQuery("from ErpFeConceptos").list();
            transaccion.commit();
        } catch (HibernateException e) {
            transaccion.rollback();
            return null;
        } finally {

            session.close();

        }

        return erpFeConceptos;
    }

    public boolean delete(ErpFeConceptos deleteErpFeConceptos) {
        
          Session session = sessionFactory.openSession();
        Transaction transaccion = session.beginTransaction();
        try{
          
            session.delete(deleteErpFeConceptos);
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
    
     public boolean deletePorFactura(String compania, int numero) {
        Session session = sessionFactory.openSession();
        Transaction transaccion = session.beginTransaction();
        
        System.out.println("borrando conceptos");

        try {

            String hql = "delete from ErpFeConceptos where id.compania = :compania and id.numero = :numero";
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

    public boolean update(ErpFeConceptos updateErpFeConceptos) {
        
        
          Session session = sessionFactory.openSession();
        Transaction transaccion = session.beginTransaction();
        
        try{
            
            session.update(updateErpFeConceptos);
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
    
    public boolean updateConceptos(ErpFeConceptosId id,String idS) {
        Session session = sessionFactory.openSession();
        Transaction transaccion = session.beginTransaction();
        
        System.out.println("borrando conceptos");

        try {

            String hql = "update ErpFeConceptos set ID_PRODUCTXSERVICIO = "+idS+"  where id.compania = :compania and id.numero = :numero and id.sec = :sec";
            Query query = session.createQuery(hql);
            query.setString("compania", id.getCompania());
            query.setInteger("numero", id.getNumero());
            query.setInteger("sec", id.getSec());
            
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


    
     public int getMaxIdConceptos(ErpFeConceptosId id) {
        Session session = sessionFactory.openSession();
        try {
            DetachedCriteria maxSec = DetachedCriteria.forClass(ErpFeConceptos.class);


            Criteria criteria = maxSec.getExecutableCriteria(session);
            criteria.add(Restrictions.eq("id.compania", id.getCompania()));
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
