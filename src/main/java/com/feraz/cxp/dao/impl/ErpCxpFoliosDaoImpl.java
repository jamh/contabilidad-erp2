/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.feraz.cxp.dao.impl;
import com.feraz.cxp.dao.ErpCxpFoliosDao;
import com.feraz.cxp.model.ErpCxpFolios;
import com.feraz.cxp.model.ErpCxpFoliosId;
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
 * @author Feraz3
 */
public class ErpCxpFoliosDaoImpl implements ErpCxpFoliosDao{
    
    private SessionFactory sessionFactory;

    @Override
    public ErpCxpFoliosId save(ErpCxpFolios erpCxpFolios) {
        
        
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        
        ErpCxpFoliosId id = null;
        
        try{
             id = (ErpCxpFoliosId) session.save(erpCxpFolios);
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
    public boolean update(ErpCxpFolios erpCxpFolios) {
        
          Session session = sessionFactory.openSession();
          Transaction transaction = session.beginTransaction();
          
         
        try{
             session.update(erpCxpFolios);
          
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
    
      public int getMaxIdCxpFolios(ErpCxpFoliosId id) {
        Session session = sessionFactory.openSession();
        try {
            DetachedCriteria maxSec = DetachedCriteria.forClass(ErpCxpFolios.class);


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
      
      public int getMaxCxpFolios(ErpCxpFoliosId id) {
        Session session = sessionFactory.openSession();
        try {
            DetachedCriteria maxSec = DetachedCriteria.forClass(ErpCxpFolios.class);


            Criteria criteria = maxSec.getExecutableCriteria(session);
            criteria.add(Restrictions.eq("id.compania", id.getCompania()));
            criteria.setProjection(Projections.max("folio"));
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
      

    public boolean actualizaEstatusFolio(ErpCxpFolios comp) {
        Session session = sessionFactory.openSession();
        Transaction transaccion = session.beginTransaction();

        //System.out.println("actualizando pagos");
        try {

            String hql = "update ErpCxpFolios set operacion =:operacion where  id.compania = :compania and folio = :folio and numeroFe = :numeroFe";

            Query query = session.createQuery(hql);
            query.setString("compania", comp.getId().getCompania());
            query.setInteger("folio", comp.getFolio());
            query.setInteger("numeroFe", comp.getNumeroFe());
            query.setString("operacion", comp.getOperacion());


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
    
    public boolean eliminaFactFolio(ErpCxpFolios comp) {
        Session session = sessionFactory.openSession();
        Transaction transaccion = session.beginTransaction();

        //System.out.println("actualizando pagos");
        try {

            String hql = "delete from ErpCxpFolios where id.compania = :compania and folio = :folio and numeroFe = :numeroFe";

            Query query = session.createQuery(hql);
            query.setString("compania", comp.getId().getCompania());
            query.setInteger("folio", comp.getFolio());
            query.setInteger("numeroFe", comp.getNumeroFe());
          


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
