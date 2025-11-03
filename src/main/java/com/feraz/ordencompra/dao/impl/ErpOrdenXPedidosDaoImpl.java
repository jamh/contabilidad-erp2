/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.feraz.ordencompra.dao.impl;

import com.feraz.ordencompra.dao.ErpOrdenXPedidosDao;
import com.feraz.ordencompra.model.ErpOrdenXPedidos;
import com.feraz.ordencompra.model.ErpOrdenXPedidosId;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

/**
 *
 * @author vavi
 */
public class ErpOrdenXPedidosDaoImpl implements ErpOrdenXPedidosDao{
    
    private SessionFactory sessionFactory;

    @Override
    public ErpOrdenXPedidosId save(ErpOrdenXPedidos erpOrdenXPedidos) {
        
                
           Session session = sessionFactory.openSession();
        Transaction transaccion = session.beginTransaction();
        ErpOrdenXPedidosId id = null;
        try {

            id = (ErpOrdenXPedidosId) session.save(erpOrdenXPedidos);
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
    public boolean delete(ErpOrdenXPedidos deleteErpOrdenXPedidos) {
        
                 
        Session session = sessionFactory.openSession();
        Transaction transaccion = session.beginTransaction();
        try {

            session.delete(deleteErpOrdenXPedidos);
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

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public boolean borraPorLinea(ErpOrdenXPedidos comp) {
        
           Session session = sessionFactory.openSession();
        Transaction transaccion = session.beginTransaction();

        //System.out.println("actualizando pagos");
        try {
            
          

            String hql = "delete from ErpOrdenXPedidos where  id.compania = :compania and id.ordenCompra = :ordenCompra and id.linea = :linea";

            Query query = session.createQuery(hql);
            query.setString("compania", comp.getId().getCompania());
            query.setInteger("ordenCompra", comp.getId().getOrdenCompra());
            query.setInteger("linea", comp.getId().getLinea());
          //  query.setInteger("idOrdenDet", comp.getId().getIdOrdenDet());
            
//                System.out.println("com.IDCONCGASTO:" + com.IDCONCGASTO);
//                System.out.println("com.compania:" + compania);
//                System.out.println(" com.NUMERO:" + com.NUMERO);
            int rowCount = query.executeUpdate();
//                System.out.println("rowCount:" + rowCount);

            transaccion.commit();

        } catch (HibernateException e) {
            transaccion.rollback();
            e.printStackTrace();
            return false;
        } finally {

            session.close();
        }

        return true;

        
    }
    
    
        @Override
    public boolean borraPorOrden(ErpOrdenXPedidos comp) {
        
           Session session = sessionFactory.openSession();
        Transaction transaccion = session.beginTransaction();

        //System.out.println("actualizando pagos");
        try {
            
          

            String hql = "delete from ErpOrdenXPedidos where  id.compania = :compania and id.ordenCompra = :ordenCompra";

            Query query = session.createQuery(hql);
            query.setString("compania", comp.getId().getCompania());
            query.setInteger("ordenCompra", comp.getId().getOrdenCompra());
          //  query.setInteger("idOrdenDet", comp.getId().getIdOrdenDet());
            
//                System.out.println("com.IDCONCGASTO:" + com.IDCONCGASTO);
//                System.out.println("com.compania:" + compania);
//                System.out.println(" com.NUMERO:" + com.NUMERO);
            int rowCount = query.executeUpdate();
//                System.out.println("rowCount:" + rowCount);

            transaccion.commit();

        } catch (HibernateException e) {
            transaccion.rollback();
            e.printStackTrace();
            return false;
        } finally {

            session.close();
        }

        return true;

        
    }
    
    
    
    
}
