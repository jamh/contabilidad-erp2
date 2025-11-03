/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.feraz.ordencompra.dao.impl;

import com.feraz.facturas.webcontrolfe.model.ErpFeComprobantes;
import com.feraz.facturas.webcontrolfe.model.ErpFeComprobantesId;
import com.feraz.ordencompra.dao.ErpOrdenCompraDao;
import com.feraz.ordencompra.model.ErpOrdenCompra;
import com.feraz.ordencompra.model.ErpOrdenCompraId;
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
public class ErpOrdenCompraDaoImpl implements ErpOrdenCompraDao{
    
     private SessionFactory sessionFactory;

    @Override
    public ErpOrdenCompraId save(ErpOrdenCompra erpOrdenCompra) {
        
           
           Session session = sessionFactory.openSession();
        Transaction transaccion = session.beginTransaction();
        ErpOrdenCompraId id = null;
        try {

            id = (ErpOrdenCompraId) session.save(erpOrdenCompra);
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
    public ErpOrdenCompra findErpOrdenCompra(String compania, String id) {
        
         ErpOrdenCompra erpOrdenCompra;
        Session session = sessionFactory.openSession();
        Transaction transaccion = session.beginTransaction();

        try {

            erpOrdenCompra = (ErpOrdenCompra) session.createQuery("from ErpOrdenCompra where id.compania= :compania and id.id= :id")
                    .setString("compania", compania)
                    .setString("id", id)
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

        return erpOrdenCompra;
        
    }
    
    
        @Override
    public ErpOrdenCompra findErpOrdenCompraWS(String compania, String id) {
        
         ErpOrdenCompra erpOrdenCompra;
        Session session = sessionFactory.openSession();
        Transaction transaccion = session.beginTransaction();

        try {

            erpOrdenCompra = (ErpOrdenCompra) session.createQuery("from ErpOrdenCompra where id.compania= :compania and idWs= :id")
                    .setString("compania", compania)
                    .setString("id", id)
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

        return erpOrdenCompra;
        
    }

    @Override
    public boolean delete(ErpOrdenCompra deleteErpOrdenCompra) {
        
          
        Session session = sessionFactory.openSession();
        Transaction transaccion = session.beginTransaction();
        try {

            session.delete(deleteErpOrdenCompra);
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
    public boolean update(ErpOrdenCompra updateErpOrdenCompra) {
        
          Session session = sessionFactory.openSession();
        Transaction transaccion = session.beginTransaction();

        try {

            session.update(updateErpOrdenCompra);
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
    
    public int getMaxIdOrden(ErpOrdenCompraId id) {
        Session session = sessionFactory.openSession();

        try {
            DetachedCriteria maxSec = DetachedCriteria.forClass(ErpOrdenCompra.class);

            Criteria criteria = maxSec.getExecutableCriteria(session);
            criteria.add(Restrictions.eq("id.compania", id.getCompania()));
            criteria.setProjection(Projections.max("id.id"));
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
    public boolean actualizaEstatusGasto(ErpOrdenCompra comp) {
        Session session = sessionFactory.openSession();
        Transaction transaccion = session.beginTransaction();

        //System.out.println("actualizando pagos");
        try {

            String hql = "update ErpOrdenCompra set validacionGasto =:validacionGasto where  id.compania = :compania and id.id = :id";

            Query query = session.createQuery(hql);
            query.setString("compania", comp.getId().getCompania());
            query.setString("validacionGasto", comp.getValidacionGasto());
            query.setInteger("id", comp.getId().getId());
        
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
    public boolean actualizaEstatus(ErpOrdenCompra comp) {
        Session session = sessionFactory.openSession();
        Transaction transaccion = session.beginTransaction();

        //System.out.println("actualizando pagos");
        try {

            String hql = "update ErpOrdenCompra set estatus =:estatus, USUARIO_AUTORIZO =:usuarioAutorizo, FECHA_AUTORIZACION = SYSDATE where  id.compania = :compania and id.id = :id";

            Query query = session.createQuery(hql);
            query.setString("compania", comp.getId().getCompania());
            query.setString("estatus", comp.getEstatus());
            query.setString("usuarioAutorizo", comp.getUsuarioAutorizo());
            query.setInteger("id", comp.getId().getId());
        
//                System.out.println("com.IDCONCGASTO:" + com.IDCONCGASTO);
//                System.out.println("com.compania:" + compania);
//                System.out.println(" com.NUMERO:" + com.NUMERO);
            int rowCount = query.executeUpdate();
//                System.out.println("rowCount:" + rowCount);

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
    public boolean actualizaEstatusSg(ErpOrdenCompra comp,String usuario,String estatus) {
        Session session = sessionFactory.openSession();
        Transaction transaccion = session.beginTransaction();

        //System.out.println("actualizando pagos");
        try {

            String hql = "update ErpOrdenCompra set SEGUNDA_AUTORIZACION =:estatus, USUARIO_SEGUNDA_AUTO =:usuarioAutorizo, FECHA_SEGUNDA_AUTO = SYSDATE where  id.compania = :compania and id.id = :id";

            Query query = session.createQuery(hql);
            query.setString("compania", comp.getId().getCompania());
            query.setString("estatus", estatus);
            query.setString("usuarioAutorizo", usuario);
            query.setInteger("id", comp.getId().getId());
        
//                System.out.println("com.IDCONCGASTO:" + com.IDCONCGASTO);
//                System.out.println("com.compania:" + compania);
//                System.out.println(" com.NUMERO:" + com.NUMERO);
            int rowCount = query.executeUpdate();
//                System.out.println("rowCount:" + rowCount);

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
    public boolean actualizaEstatusPr(ErpOrdenCompra comp,String usuario,String estatus) {
        Session session = sessionFactory.openSession();
        Transaction transaccion = session.beginTransaction();

        //System.out.println("actualizando pagos");
        try {

            String hql = "update ErpOrdenCompra set PRIMER_AUTORIZACION =:estatus, USUARIO_PRIMER_AUTO =:usuarioAutorizo, FECHA_PRIMER_AUTO = SYSDATE where  id.compania = :compania and id.id = :id";

            Query query = session.createQuery(hql);
            query.setString("compania", comp.getId().getCompania());
            query.setString("estatus", estatus);
            query.setString("usuarioAutorizo", usuario);
            query.setInteger("id", comp.getId().getId());
        
//                System.out.println("com.IDCONCGASTO:" + com.IDCONCGASTO);
//                System.out.println("com.compania:" + compania);
//                System.out.println(" com.NUMERO:" + com.NUMERO);
            int rowCount = query.executeUpdate();
//                System.out.println("rowCount:" + rowCount);

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
    public boolean actualizaImportes(ErpOrdenCompra comp) {
        Session session = sessionFactory.openSession();
        Transaction transaccion = session.beginTransaction();

        //System.out.println("actualizando pagos");
        try {
            
          

            String hql = "update ErpOrdenCompra set totalPendiente =:totalPendiente where  id.compania = :compania and id.id = :id";

            Query query = session.createQuery(hql);
            query.setString("compania", comp.getId().getCompania());
            query.setInteger("id", comp.getId().getId());
            query.setBigDecimal("totalPendiente", comp.getTotalPendiente());
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
