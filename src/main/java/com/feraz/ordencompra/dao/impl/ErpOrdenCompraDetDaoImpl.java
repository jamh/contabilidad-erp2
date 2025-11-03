/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.feraz.ordencompra.dao.impl;

import com.feraz.ordencompra.dao.ErpOrdenCompraDetDao;
import com.feraz.ordencompra.model.ErpOrdenCompra;
import com.feraz.ordencompra.model.ErpOrdenCompraDet;
import com.feraz.ordencompra.model.ErpOrdenCompraDetId;
import com.feraz.ordencompra.model.ErpOrdenCompraId;
import java.text.SimpleDateFormat;
import java.util.Date;
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
public class ErpOrdenCompraDetDaoImpl implements ErpOrdenCompraDetDao{
    
    
    private SessionFactory sessionFactory;

    @Override
    public ErpOrdenCompraDetId save(ErpOrdenCompraDet erpOrdenCompraDet) {
        
            
           Session session = sessionFactory.openSession();
        Transaction transaccion = session.beginTransaction();
        ErpOrdenCompraDetId id = null;
        try {

            id = (ErpOrdenCompraDetId) session.save(erpOrdenCompraDet);
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
    public ErpOrdenCompraDet findErpOrdenCompraDet(String compania, Integer idCompra, Integer linea) {
        
           ErpOrdenCompraDet erpOrdenCompraDet;
        Session session = sessionFactory.openSession();
        Transaction transaccion = session.beginTransaction();

        try {

            erpOrdenCompraDet = (ErpOrdenCompraDet) session.createQuery("from ErpOrdenCompraDet where id.compania= :compania and id.idOrdenCompra= :id and id.linea = :linea")
                    .setString("compania", compania)
                    .setInteger("id", idCompra)
                    .setInteger("linea", linea)
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

        return erpOrdenCompraDet;
    }
    
     @Override
    public ErpOrdenCompraDet findErpOrdenCompraDetWS(String compania, String idCompra, String linea) {
        
           ErpOrdenCompraDet erpOrdenCompraDet;
        Session session = sessionFactory.openSession();
        Transaction transaccion = session.beginTransaction();

        try {

            erpOrdenCompraDet = (ErpOrdenCompraDet) session.createQuery("from ErpOrdenCompraDet where id.compania= :compania and idWs= :id and idLineaWS = :linea")
                    .setString("compania", compania)
                    .setString("id", idCompra)
                    .setString("linea", linea)
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

        return erpOrdenCompraDet;
    }

    @Override
    public boolean delete(ErpOrdenCompraDet deleteErpOrdenCompraDet) {
        
          Session session = sessionFactory.openSession();
        Transaction transaccion = session.beginTransaction();
        try {

            session.delete(deleteErpOrdenCompraDet);
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
    public boolean update(ErpOrdenCompraDet updateErpOrdenCompraDet) {
        
        Session session = sessionFactory.openSession();
        Transaction transaccion = session.beginTransaction();

        try {

            session.update(updateErpOrdenCompraDet);
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
    
        public boolean borraDetalleOrden(ErpOrdenCompraDet erpOrdenCompraDet) {
          Session session = sessionFactory.openSession();
        Transaction transaccion = session.beginTransaction();
        
           System.out.println("Borrando");
        
        try {
     
            
            
             String hql = "delete from ErpOrdenCompraDet where id.compania = :compania and id.idOrdenCompra = :idOrdenCompra";
        Query query = session.createQuery(hql);
        query.setString("compania",erpOrdenCompraDet.getId().getCompania());
        query.setInteger("idOrdenCompra",erpOrdenCompraDet.getId().getIdOrdenCompra());
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
        
        public int getMaxIdDetOrden(ErpOrdenCompraDetId id) {
        Session session = sessionFactory.openSession();

        try {
            DetachedCriteria maxSec = DetachedCriteria.forClass(ErpOrdenCompraDet.class);

            Criteria criteria = maxSec.getExecutableCriteria(session);
            criteria.add(Restrictions.eq("id.compania", id.getCompania()));
            criteria.add(Restrictions.eq("id.idOrdenCompra", id.getIdOrdenCompra()));
            criteria.setProjection(Projections.max("id.linea"));
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
    public boolean actualizaImportes(ErpOrdenCompraDet comp) {
        Session session = sessionFactory.openSession();
        Transaction transaccion = session.beginTransaction();

        //System.out.println("actualizando pagos");
        try {
            
          

            String hql = "update ErpOrdenCompraDet set precioUnitario =:precioUnitario,iva =:iva,total =:total where  id.compania = :compania and id.idOrdenCompra = :idOrdenCompra and id.linea = :linea";

            Query query = session.createQuery(hql);
            query.setString("compania", comp.getId().getCompania());
            query.setInteger("idOrdenCompra", comp.getId().getIdOrdenCompra());
            query.setInteger("linea", comp.getId().getLinea());
            query.setBigDecimal("precioUnitario", comp.getPrecioUnitario());
            query.setBigDecimal("total", comp.getTotal());
            query.setBigDecimal("iva", comp.getIva());
        
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
    public boolean actualizaEstatus(ErpOrdenCompraDet comp) {
        Session session = sessionFactory.openSession();
        Transaction transaccion = session.beginTransaction();

        //System.out.println("actualizando pagos");
        try {
            
          

            String hql = "update ErpOrdenCompraDet set estatusLinea =:estatusLinea where  id.compania = :compania and id.idOrdenCompra = :idOrdenCompra and id.linea = :linea";

            Query query = session.createQuery(hql);
            query.setString("compania", comp.getId().getCompania());
            query.setInteger("idOrdenCompra", comp.getId().getIdOrdenCompra());
            query.setInteger("linea", comp.getId().getLinea());
            query.setString("estatusLinea", comp.getEstatusLinea());
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
