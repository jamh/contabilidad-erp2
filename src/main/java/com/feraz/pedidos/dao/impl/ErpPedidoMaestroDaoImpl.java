/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.feraz.pedidos.dao.impl;


import com.feraz.pedidos.dao.ErpPedidoMaestroDao;
import com.feraz.pedidos.model.ErpPedidoMaestro;
import com.feraz.pedidos.model.ErpPedidoMaestroId;
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
public class ErpPedidoMaestroDaoImpl implements ErpPedidoMaestroDao{
    
    private SessionFactory sessionFactory;

    @Override
    public ErpPedidoMaestroId save(ErpPedidoMaestro ErpPedidoMaestro) {
        
         
           Session session = sessionFactory.openSession();
        Transaction transaccion = session.beginTransaction();
        ErpPedidoMaestroId id = null;
        try {

            id = (ErpPedidoMaestroId) session.save(ErpPedidoMaestro);
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

    @Override
    public ErpPedidoMaestro findErpPedidoMaestro(String compania, Integer folio) {
        
          ErpPedidoMaestro erpPedidoMaestro;
        Session session = sessionFactory.openSession();
        Transaction transaccion = session.beginTransaction();

        try {

            erpPedidoMaestro = (ErpPedidoMaestro) session.createQuery("from ErpPedidoMaestro where id.compania= :compania and id.folio= :folio")
                    .setString("compania", compania)
                    .setInteger("folio", folio)
                    .setMaxResults(1)
                    .uniqueResult();
            transaccion.commit();
        } catch (HibernateException e) {
            e.printStackTrace();
            transaccion.rollback();
            return null;
        } finally {

            session.close();

        }

        return erpPedidoMaestro;
        
    }

    @Override
    public boolean delete(ErpPedidoMaestro deleteErpPedidoMaestro) {
        
            Session session = sessionFactory.openSession();
        Transaction transaccion = session.beginTransaction();
        try {

            session.delete(deleteErpPedidoMaestro);
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

    @Override
    public boolean update(ErpPedidoMaestro updateErpPedidoMaestro) {
        
          Session session = sessionFactory.openSession();
        Transaction transaccion = session.beginTransaction();

        try {

            session.update(updateErpPedidoMaestro);
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

    @Override
    public int getMaxIdPedidos(ErpPedidoMaestroId id) {
        
         Session session = sessionFactory.openSession();

        try {
            DetachedCriteria maxSec = DetachedCriteria.forClass(ErpPedidoMaestro.class);

            Criteria criteria = maxSec.getExecutableCriteria(session);
            criteria.add(Restrictions.eq("id.compania", id.getCompania()));
            criteria.setProjection(Projections.max("id.folio"));
            int campo ;
            List lista = criteria.list();
            if (lista.get(0) == null) {
                campo = 0;
            } else {
                campo = new Integer(lista.get(0).toString());
            }
            campo++;

            return campo;
        } catch (HibernateException e) {
            return 0;
        } catch (NumberFormatException e) {
            return 0;
        } finally {
            session.close();
        }
    }

    @Override
    public boolean actualizaEstatus(ErpPedidoMaestro comp) {
        
         Session session = sessionFactory.openSession();
        Transaction transaccion = session.beginTransaction();

        //System.out.println("actualizando pagos");
        try {

            String hql = "update ErpPedidoMaestro set estatus =:estatus where  id.compania = :compania and id.folio = :folio";

            Query query = session.createQuery(hql);
            query.setString("compania", comp.getId().getCompania());
            query.setString("estatus", comp.getEstatus());
            query.setInteger("folio", comp.getId().getFolio());
        
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
    
        @Override
    public boolean actualizaEstatusUrgente(ErpPedidoMaestro comp) {
        
         Session session = sessionFactory.openSession();
        Transaction transaccion = session.beginTransaction();

        //System.out.println("actualizando pagos");
        try {
           if(comp.getIdProyecto() != null){
            String hql = "update ErpPedidoMaestro set idProveedor =:idProveedor,solicitudUrgente =:solicitudUrgente, correoProv =:correoProv, idArea =:idArea,idProyecto =:idProyecto where  id.compania = :compania and id.folio = :folio";

            Query query = session.createQuery(hql);
            query.setString("compania", comp.getId().getCompania());
            query.setString("solicitudUrgente", comp.getSolicitudUrgente());
            query.setString("correoProv", comp.getCorreoProv());
            query.setInteger("folio", comp.getId().getFolio());
            query.setString("idProveedor", comp.getIdProveedor());
            query.setInteger("idArea", comp.getIdArea());
            query.setInteger("idProyecto", comp.getIdProyecto());
                         int rowCount = query.executeUpdate();

           }else{
            String hql = "update ErpPedidoMaestro set idProveedor =:idProveedor,solicitudUrgente =:solicitudUrgente, correoProv =:correoProv, idArea =:idArea where  id.compania = :compania and id.folio = :folio";

            Query query = session.createQuery(hql);
            query.setString("compania", comp.getId().getCompania());
            query.setString("solicitudUrgente", comp.getSolicitudUrgente());
            query.setString("correoProv", comp.getCorreoProv());
            query.setInteger("folio", comp.getId().getFolio());
            query.setString("idProveedor", comp.getIdProveedor());
            query.setInteger("idArea", comp.getIdArea());
                         int rowCount = query.executeUpdate();

           
           }
            
        
//                System.out.println("com.IDCONCGASTO:" + com.IDCONCGASTO);
//                System.out.println("com.compania:" + compania);
//                System.out.println(" com.NUMERO:" + com.NUMERO);
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
    
         @Override
    public boolean actualizaEstatusUrgenteSinArea(ErpPedidoMaestro comp) {
        
         Session session = sessionFactory.openSession();
        Transaction transaccion = session.beginTransaction();

        //System.out.println("actualizando pagos");
        try {
           if(comp.getIdProyecto() != null){
            String hql = "update ErpPedidoMaestro set idProveedor =:idProveedor,solicitudUrgente =:solicitudUrgente, correoProv =:correoProv, idProyecto =:idProyecto where  id.compania = :compania and id.folio = :folio";

            Query query = session.createQuery(hql);
            query.setString("compania", comp.getId().getCompania());
            query.setString("solicitudUrgente", comp.getSolicitudUrgente());
            query.setString("correoProv", comp.getCorreoProv());
            query.setInteger("folio", comp.getId().getFolio());
            query.setString("idProveedor", comp.getIdProveedor());
            //query.setInteger("idArea", comp.getIdArea());
            query.setInteger("idProyecto", comp.getIdProyecto());
                         int rowCount = query.executeUpdate();

           }else{
            String hql = "update ErpPedidoMaestro set idProveedor =:idProveedor,solicitudUrgente =:solicitudUrgente, correoProv =:correoProv where  id.compania = :compania and id.folio = :folio";

            Query query = session.createQuery(hql);
            query.setString("compania", comp.getId().getCompania());
            query.setString("solicitudUrgente", comp.getSolicitudUrgente());
            query.setString("correoProv", comp.getCorreoProv());
            query.setInteger("folio", comp.getId().getFolio());
            query.setString("idProveedor", comp.getIdProveedor());
           // query.setInteger("idArea", comp.getIdArea());
                         int rowCount = query.executeUpdate();

           
           }
            
        
//                System.out.println("com.IDCONCGASTO:" + com.IDCONCGASTO);
//                System.out.println("com.compania:" + compania);
//                System.out.println(" com.NUMERO:" + com.NUMERO);
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
