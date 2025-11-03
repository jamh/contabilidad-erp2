package com.feraz.cxp.dao.impl;

import com.feraz.cxp.dao.ErpCxpOrdenCompraLogDao;
import com.feraz.cxp.model.ErpCxpOrdenCompraLog;
import com.feraz.cxp.model.ErpCxpOrdenCompraLogId;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

/**
 * @author Armando
 */
public class ErpCxpOrdenCompraLogDaoImpl implements ErpCxpOrdenCompraLogDao {
    
    private SessionFactory sessionFactory;
    
    @Override
    public ErpCxpOrdenCompraLogId save(ErpCxpOrdenCompraLog erpCxpOrdenCompraLog){
        System.out.println("llegando a guardar");
        Session session = sessionFactory.openSession();
        Transaction transaccion = session.beginTransaction();
        ErpCxpOrdenCompraLogId id = null;
        try {
            id = (ErpCxpOrdenCompraLogId) session.save(erpCxpOrdenCompraLog);
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
