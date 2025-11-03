/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.feraz.cxp.dao.impl;

import com.feraz.contabilidad.convertidor.model.ErpDetConvertidor;
import com.feraz.cxp.model.ErpSeguiDocumentos;
import com.feraz.cxp.model.ErpSeguiDocumentosId;
import com.feraz.cxp.dao.ErpSeguiDocumentosDao;
import java.util.List;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Date;
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
 * @author Administrador
 */
public class ErpSeguiDocumentosDaoImpl implements ErpSeguiDocumentosDao{
    
    private SessionFactory sessionFactory;

    public ErpSeguiDocumentosId save(ErpSeguiDocumentos erpSeguiDocumentos) {
        
         System.out.println("llegando a guardar");
         Session session = sessionFactory.openSession();
        Transaction transaccion = session.beginTransaction();
        ErpSeguiDocumentosId id = null;
        try {

            id = (ErpSeguiDocumentosId) session.save(erpSeguiDocumentos);
            transaccion.commit();

        } catch (HibernateException e) {
            e.printStackTrace();
            transaccion.rollback();
            return null;
        } finally {

            session.close();
        }
        return id;
    }

    public List<ErpSeguiDocumentos> findErpSeguiDocumentos(ErpSeguiDocumentos documentos) {
        
         List<ErpSeguiDocumentos> erpSeguiDocumentos;
        Session session = sessionFactory.openSession();
        Transaction transaccion = session.beginTransaction();

        try {

            //erpSeguiDocumentos = (List<ErpSeguiDocumentos>) session.createQuery("from ErpSeguiDocumentos").list();
            
             erpSeguiDocumentos = (List<ErpSeguiDocumentos>) session.createQuery("from ErpSeguiDocumentos where id.compania = :compania  and  id.origen = :origen and  id.tDoc = :tDoc and  id.serie = :serie and  id.folio = :folio and  id.sec = :sec")
                    .setString("compania", documentos.getId().getCompania())    
                    .setString("origen", documentos.getId().getOrigen())
                    .setString("tDoc", documentos.getId().gettDoc())    
                    .setString("serie", documentos.getId().getSerie())
                    .setInteger("folio", documentos.getId().getNumFe())    
                    .setInteger("sec", documentos.getId().getSec())
                    .list();
            
            transaccion.commit();

        } catch (HibernateException e) {
            e.printStackTrace();
            transaccion.rollback();
            return null;
        } finally {

            session.close();

        }
        return erpSeguiDocumentos;
    }

    public boolean delete(ErpSeguiDocumentos deleteErpSeguiDocumentos) {
        
         Session session = sessionFactory.openSession();
        Transaction transaccion = session.beginTransaction();

        try {

            session.delete(deleteErpSeguiDocumentos);
            transaccion.commit();

        } catch (HibernateException e) {
            e.printStackTrace();
            transaccion.rollback();
            return true;
        } finally {

            session.close();

        }
        return true;
    }


    public boolean update(ErpSeguiDocumentos updateErpSeguiDocumentos) {
        
         Session session = sessionFactory.openSession();
        Transaction transaccion = session.beginTransaction();

        try {

            session.update(updateErpSeguiDocumentos);
            transaccion.commit();

        } catch (HibernateException e) {
            e.printStackTrace();
            transaccion.rollback();
            return false;

        } finally {

            session.close();

        }
        return true;
    }
    
       public int getMaxId(ErpSeguiDocumentosId id) {
        Session session = sessionFactory.openSession();
        try {
            DetachedCriteria maxSec = DetachedCriteria.forClass(ErpSeguiDocumentos.class);


            Criteria criteria = maxSec.getExecutableCriteria(session);
            criteria.add(Restrictions.eq("id.compania", id.getCompania()));
            criteria.add(Restrictions.eq("id.numFe", id.getNumFe()));
            criteria.add(Restrictions.eq("id.origen", id.getOrigen()));
            criteria.add(Restrictions.eq("id.tDoc", id.gettDoc()));
            criteria.add(Restrictions.eq("id.serie", id.getSerie()));
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
       
        public boolean borraDetallePagos(ErpSeguiDocumentosId id) {
          Session session = sessionFactory.openSession();
        Transaction transaccion = session.beginTransaction();
        try {
     
           
            
             String hql = "delete from ErpSeguiDocumentos where id.compania = :compania and id.origen = :origen and id.serie = :serie and id.tDoc = :tDoc and id.numFe = :numFe";
        Query query = session.createQuery(hql);
        query.setString("compania",id.getCompania());
        query.setString("origen",id.getOrigen());
        query.setString("serie",id.getSerie());
        query.setString("tDoc",id.gettDoc());
        query.setInteger("numFe",id.getNumFe());
        int rowCount = query.executeUpdate();
    //    System.out.println("Rows affected: " + rowCount);

            transaccion.commit();

        } catch (HibernateException e) {
            e.printStackTrace();
            transaccion.rollback();
            return false;
        }catch(Exception e2){
            e2.printStackTrace();
            transaccion.rollback();
        }
            finally {
            session.close();
        }
        return true;
    }
        
            public boolean borraDetallePagosNotas(ErpSeguiDocumentos id) {
          Session session = sessionFactory.openSession();
        Transaction transaccion = session.beginTransaction();
        try {
     
           
            
             String hql = "delete from ErpSeguiDocumentos where id.compania = :compania and id.origen = :origen and id.serie = :serie and id.tDoc = :tDoc and id.numFe = :numFe and tipo = :tipo";
        Query query = session.createQuery(hql);
        query.setString("compania",id.getId().getCompania());
        query.setString("origen",id.getId().getOrigen());
        query.setString("serie",id.getId().getSerie());
        query.setString("tDoc",id.getId().gettDoc());
        query.setInteger("numFe",id.getId().getNumFe());
        query.setString("tipo",id.getTipo());
        int rowCount = query.executeUpdate();
    //    System.out.println("Rows affected: " + rowCount);

            transaccion.commit();

        } catch (HibernateException e) {
            e.printStackTrace();
            transaccion.rollback();
            return false;
        }catch(Exception e2){
            e2.printStackTrace();
            transaccion.rollback();
        }
            finally {
            session.close();
        }
        return true;
    }
        
    
    

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }
    
    
    
}
