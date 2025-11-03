/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.feraz.conciliacion.dao.impl;

import com.feraz.conciliacion.dao.ErpEdoCuentaXErpDao;
import com.feraz.conciliacion.model.ErpEdoCuentaXErp;
import com.feraz.conciliacion.model.ErpEdoCuentaXErpId;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

/**
 *
 * @author vavi
 */
public class ErpEdoCuentaXErpDaoImpl implements ErpEdoCuentaXErpDao{
    
    private SessionFactory sessionFactory;

    @Override
    public ErpEdoCuentaXErpId save(ErpEdoCuentaXErp erpEdoCuentaXErp) {
        
          
          Session session = sessionFactory.openSession();
        Transaction transaccion = session.beginTransaction();
        ErpEdoCuentaXErpId id = null;
        try {

            id = (ErpEdoCuentaXErpId) session.save(erpEdoCuentaXErp);
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

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }
    
    
    
    
}
