/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.feraz.cajachica.dao.impl;

import com.feraz.cajachica.dao.ErpSolicitudDeFondosDao;
import com.feraz.cajachica.model.ErpSolicitudDeFondos;
import com.feraz.cajachica.model.ErpSolicitudDeFondosId;
import org.hibernate.SessionFactory;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import java.util.List;
import org.hibernate.Query;


/**
 *
 * @author vavi
 */
public class ErpSolicitudDeFondosDaoImpl implements ErpSolicitudDeFondosDao{

    
        private SessionFactory sessionFactory;

    
    @Override
    public ErpSolicitudDeFondosId save(ErpSolicitudDeFondos erpSolicitudDeFondos) {
        
        
        
        Session session = sessionFactory.openSession();
        Transaction transaccion = session.beginTransaction();
        ErpSolicitudDeFondosId id = null;
        try {

            id = (ErpSolicitudDeFondosId) session.save(erpSolicitudDeFondos);
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
    public boolean delete(ErpSolicitudDeFondos erpSolicitudDeFondos) {
        
           Session session = sessionFactory.openSession();
        Transaction transaccion = session.beginTransaction();

        try {

            session.delete(erpSolicitudDeFondos);
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

    @Override
    public boolean update(ErpSolicitudDeFondos erpSolicitudDeFondos) {
        
            Session session = sessionFactory.openSession();
        Transaction transaccion = session.beginTransaction();

        try {

            session.update(erpSolicitudDeFondos);
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
    public boolean updateEstatus(ErpSolicitudDeFondos erpSolicitudDeFondos) {
       Session session = sessionFactory.openSession();
        Transaction transaccion = session.beginTransaction();
        
      
        try {

            String hql = "update ErpSolicitudDeFondos set solicitarACaja = :solicitarACaja where  id.compania = :compania and id.idSolicitud = :idSolicitud";

            Query query = session.createQuery(hql);
            query.setString("compania", erpSolicitudDeFondos.getId().getCompania());
            query.setInteger("idSolicitud", erpSolicitudDeFondos.getId().getIdSolicitud());
            query.setString("solicitarACaja", erpSolicitudDeFondos.getSolicitarACaja());

            int rowCount = query.executeUpdate();

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
    public boolean updateEstatusAuto(ErpSolicitudDeFondos erpSolicitudDeFondos) {
       Session session = sessionFactory.openSession();
        Transaction transaccion = session.beginTransaction();
        
 

        try {

            String hql = "update ErpSolicitudDeFondos set solicitarACaja = :solicitarACaja, autorizaArea = :autorizaArea, fechaAutorizoArea = :fechaAutorizoArea, usuarioAutorizaArea = :usuarioAutorizaArea, motRechazo = :motRechazo where  id.compania = :compania and id.idSolicitud = :idSolicitud";

            Query query = session.createQuery(hql);
            query.setString("compania", erpSolicitudDeFondos.getId().getCompania());
            query.setInteger("idSolicitud", erpSolicitudDeFondos.getId().getIdSolicitud());
            query.setString("solicitarACaja", erpSolicitudDeFondos.getSolicitarACaja());
             query.setString("autorizaArea", erpSolicitudDeFondos.getAutorizaArea());
             query.setString("usuarioAutorizaArea", erpSolicitudDeFondos.getUsuarioAutorizaArea());
             query.setDate("fechaAutorizoArea", erpSolicitudDeFondos.getFechaAutorizoArea());
             query.setString("motRechazo", erpSolicitudDeFondos.getMotRechazo());

            int rowCount = query.executeUpdate();

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
    public boolean updateEstatusAutorizar(ErpSolicitudDeFondos erpSolicitudDeFondos) {
       Session session = sessionFactory.openSession();
        Transaction transaccion = session.beginTransaction();
        
      
        try {

            String hql = "update ErpSolicitudDeFondos set solicitarACaja = :solicitarACaja, usuarioAutoriza = :usuarioAutoriza, fechaDeAutorizacion = :fechaDeAutorizacion where  id.compania = :compania and id.idSolicitud = :idSolicitud";

            Query query = session.createQuery(hql);
            query.setString("compania", erpSolicitudDeFondos.getId().getCompania());
            query.setInteger("idSolicitud", erpSolicitudDeFondos.getId().getIdSolicitud());
            query.setString("solicitarACaja", erpSolicitudDeFondos.getSolicitarACaja());
            query.setString("usuarioAutoriza", erpSolicitudDeFondos.getUsuarioAutoriza());
            query.setDate("fechaDeAutorizacion", erpSolicitudDeFondos.getFechaDeAutorizacion());

            int rowCount = query.executeUpdate();

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
    public boolean updateEstatusAutorizarPag(ErpSolicitudDeFondos erpSolicitudDeFondos) {
       Session session = sessionFactory.openSession();
        Transaction transaccion = session.beginTransaction();
        
      
        try {

            String hql = "update ErpSolicitudDeFondos set solicitarACaja = :solicitarACaja,usuarioAutoriza = :usuarioAutoriza, fechaDeAutorizacion = :fechaDeAutorizacion, IMPORTE_PAGADO = :importePagado, BANCO_PAGO = :bancoPago,FECHA_PAGO = :fechaPago where  id.compania = :compania and id.idSolicitud = :idSolicitud";

            Query query = session.createQuery(hql);
            query.setString("compania", erpSolicitudDeFondos.getId().getCompania());
            query.setInteger("idSolicitud", erpSolicitudDeFondos.getId().getIdSolicitud());
            query.setString("solicitarACaja", erpSolicitudDeFondos.getSolicitarACaja());
            query.setBigDecimal("importePagado", erpSolicitudDeFondos.getImporteRequerido());
            query.setDate("fechaPago", erpSolicitudDeFondos.getFechaDeAutorizacion());
            query.setString("bancoPago", erpSolicitudDeFondos.getBanco());
            
            query.setString("usuarioAutoriza", erpSolicitudDeFondos.getUsuarioAutoriza());
            query.setDate("fechaDeAutorizacion", erpSolicitudDeFondos.getFechaDeAutorizacion());

            int rowCount = query.executeUpdate();

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
    public boolean updateIdCaja(ErpSolicitudDeFondos erpSolicitudDeFondos,Integer idCaja) {
        
            Session session = sessionFactory.openSession();
        Transaction transaccion = session.beginTransaction();
        
      
        try {

            String hql = "update ErpSolicitudDeFondos set ID_CAJA = :idCaja where  id.compania = :compania and id.idSolicitud = :idSolicitud";

            Query query = session.createQuery(hql);
            query.setString("compania", erpSolicitudDeFondos.getId().getCompania());
            query.setInteger("idSolicitud", erpSolicitudDeFondos.getId().getIdSolicitud());
            query.setInteger("idCaja", idCaja);

            int rowCount = query.executeUpdate();

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
    public boolean updateEstatusEnFolio(ErpSolicitudDeFondos erpSolicitudDeFondos) {
       Session session = sessionFactory.openSession();
        Transaction transaccion = session.beginTransaction();
        
      
        try {

            String hql = "update ErpSolicitudDeFondos set solicitarACaja = :solicitarACaja, folioPagos = :folioPagos where  id.compania = :compania and id.idSolicitud = :idSolicitud";

            Query query = session.createQuery(hql);
            query.setString("compania", erpSolicitudDeFondos.getId().getCompania());
            query.setInteger("idSolicitud", erpSolicitudDeFondos.getId().getIdSolicitud());
            query.setString("solicitarACaja", erpSolicitudDeFondos.getSolicitarACaja());
            query.setInteger("folioPagos", erpSolicitudDeFondos.getFolioPagos());

            int rowCount = query.executeUpdate();

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
    public boolean updateEstatusEnFolioRetira(ErpSolicitudDeFondos erpSolicitudDeFondos) {
       Session session = sessionFactory.openSession();
        Transaction transaccion = session.beginTransaction();
        
        
        try {

            String hql = "update ErpSolicitudDeFondos set solicitarACaja = :solicitarACaja, FOLIO_PAGOS = NULL where  id.compania = :compania and id.idSolicitud = :idSolicitud";

            Query query = session.createQuery(hql);
            query.setString("compania", erpSolicitudDeFondos.getId().getCompania());
            query.setInteger("idSolicitud", erpSolicitudDeFondos.getId().getIdSolicitud());
            query.setString("solicitarACaja", erpSolicitudDeFondos.getSolicitarACaja());
            //query.setInteger("folioPagos", erpSolicitudDeFondos.getFolioPagos());

            int rowCount = query.executeUpdate();

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
    public int getMaxErpSolCajaId(ErpSolicitudDeFondosId id) {
        
         Session session = sessionFactory.openSession();
        try {
            DetachedCriteria maxSec = DetachedCriteria.forClass(ErpSolicitudDeFondos.class);

            Criteria criteria = maxSec.getExecutableCriteria(session);
            criteria.add(Restrictions.eq("id.compania", id.getCompania()));

            criteria.setProjection(Projections.max("id.idSolicitud"));
            int campo = 0;
            List lista = criteria.list();
            if (lista.get(0) == null) {
                campo = 0;
            } else {
                campo = Integer.parseInt(lista.get(0).toString());
               // System.out.println("campo1"+campo);

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
