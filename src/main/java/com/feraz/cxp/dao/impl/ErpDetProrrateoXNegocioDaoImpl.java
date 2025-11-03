/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.feraz.cxp.dao.impl;

import com.feraz.cxp.dao.ErpDetProrrateoXNegocioDao;
import com.feraz.cxp.model.ErpDetProrrateoXNegocio;
import com.feraz.cxp.model.ErpDetProrrateoXNegocioId;
import java.util.List;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

/**
 *
 * @author LENOVO
 */
public class ErpDetProrrateoXNegocioDaoImpl implements ErpDetProrrateoXNegocioDao{
    
     private SessionFactory sessionFactory;

    @Override
    public ErpDetProrrateoXNegocioId save(ErpDetProrrateoXNegocio erpDetProrrateoXNegocio) {
        
           
          Session session = sessionFactory.openSession();
        Transaction transaccion = session.beginTransaction();
        ErpDetProrrateoXNegocioId id = null;
        try {

            id = (ErpDetProrrateoXNegocioId) session.save(erpDetProrrateoXNegocio);
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
    public List<ErpDetProrrateoXNegocio> findErpCClientes(ErpDetProrrateoXNegocio erpDetProrrateoXNegocio) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean delete(ErpDetProrrateoXNegocio deleteErpDetProrrateoXNegocio) {
        
            Session session = sessionFactory.openSession();
        Transaction transaccion = session.beginTransaction();

        try {

            session.delete(deleteErpDetProrrateoXNegocio);
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
    public boolean update(ErpDetProrrateoXNegocio updateErpDetProrrateoXNegocio) {
        
            Session session = sessionFactory.openSession();
        Transaction transaccion = session.beginTransaction();

        try {

            session.update(updateErpDetProrrateoXNegocio);
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
    
    
    
    
}
