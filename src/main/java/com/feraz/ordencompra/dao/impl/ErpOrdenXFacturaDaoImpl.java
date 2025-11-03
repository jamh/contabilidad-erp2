/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.feraz.ordencompra.dao.impl;

import com.feraz.ordencompra.dao.ErpOrdenXFacturaDao;
import com.feraz.ordencompra.model.ErpOrdenXFactura;
import com.feraz.ordencompra.model.ErpOrdenXFacturaId;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

/**
 *
 * @author vavi
 */
public class ErpOrdenXFacturaDaoImpl implements ErpOrdenXFacturaDao{
    
    private SessionFactory sessionFactory;


    @Override
    public ErpOrdenXFacturaId save(ErpOrdenXFactura erpOrdenXFactura) {
        
        
              
           Session session = sessionFactory.openSession();
        Transaction transaccion = session.beginTransaction();
        ErpOrdenXFacturaId id = null;
        try {

            id = (ErpOrdenXFacturaId) session.save(erpOrdenXFactura);
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
    public boolean delete(ErpOrdenXFactura deleteErpOrdenXFactura) {
        
              
        Session session = sessionFactory.openSession();
        Transaction transaccion = session.beginTransaction();
        try {

            session.delete(deleteErpOrdenXFactura);
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
    public boolean update(ErpOrdenXFactura updateErpOrdenXFactura) {
        
         Session session = sessionFactory.openSession();
        Transaction transaccion = session.beginTransaction();

        try {

            session.update(updateErpOrdenXFactura);
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
    public boolean actualizaEstatus(ErpOrdenXFactura comp) {
        Session session = sessionFactory.openSession();
        Transaction transaccion = session.beginTransaction();

        //System.out.println("actualizando pagos");
        try {
            
          

            String hql = "update ErpOrdenXFactura set estatusCXP =:estatusCXP where  id.compania = :compania and id.idOrden = :idOrden and id.numeroFe = :numeroFe";

            Query query = session.createQuery(hql);
            query.setString("compania", comp.getId().getCompania());
            query.setInteger("idOrden", comp.getId().getIdOrden());
            query.setInteger("numeroFe", comp.getId().getNumeroFe());
          //  query.setInteger("idOrdenDet", comp.getId().getIdOrdenDet());
            
            query.setString("estatusCXP", comp.getEstatusCXP());
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
    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }
    
    
    
}
