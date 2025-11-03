/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.feraz.cxp.dao.impl;

import com.feraz.cxp.dao.ErpDetProrrateoXConceptoDao;
import com.feraz.cxp.model.ErpDetProrrateoXConcepto;
import com.feraz.cxp.model.ErpDetProrrateoXConceptoId;
import java.util.List;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

/**
 *
 * @author vavi
 */
public class ErpDetProrrateoXConceptoDaoImpl implements ErpDetProrrateoXConceptoDao{
    
    private SessionFactory sessionFactory;

    @Override
    public ErpDetProrrateoXConceptoId save(ErpDetProrrateoXConcepto erpDetProrrateoXConcepto) {
        
          Session session = sessionFactory.openSession();
        Transaction transaccion = session.beginTransaction();
        ErpDetProrrateoXConceptoId id = null;
        try {

            id = (ErpDetProrrateoXConceptoId) session.save(erpDetProrrateoXConcepto);
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
    public List<ErpDetProrrateoXConcepto> findErpCClientes(ErpDetProrrateoXConcepto erpDetProrrateoXConcepto) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean delete(ErpDetProrrateoXConcepto deleteErpDetProrrateoXConcepto) {
      Session session = sessionFactory.openSession();
        Transaction transaccion = session.beginTransaction();

        try {

            session.delete(deleteErpDetProrrateoXConcepto);
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
    public boolean update(ErpDetProrrateoXConcepto updateErpDetProrrateoXConcepto) {
        
          Session session = sessionFactory.openSession();
        Transaction transaccion = session.beginTransaction();

        try {

            session.update(updateErpDetProrrateoXConcepto);
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
