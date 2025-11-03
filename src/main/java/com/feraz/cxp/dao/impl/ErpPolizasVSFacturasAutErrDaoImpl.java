/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.feraz.cxp.dao.impl;
import com.feraz.cxp.dao.ErpCxpFoliosDao;
import com.feraz.cxp.dao.ErpPolizasVSFacturasAutErrDao;
import com.feraz.cxp.model.ErpCxpFolios;
import com.feraz.cxp.model.ErpCxpFoliosId;
import com.feraz.cxp.model.ErpPolizasVSFacturasAutErr;
import com.feraz.cxp.model.ErpPolizasVSFacturasAutErrId;
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
 * @author 55555
 */
public class ErpPolizasVSFacturasAutErrDaoImpl implements ErpPolizasVSFacturasAutErrDao{
    
    private SessionFactory sessionFactory;

    @Override
    public ErpPolizasVSFacturasAutErrId save(ErpPolizasVSFacturasAutErr erpPolizasVSFacturasAutErr) {
        
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        
        ErpPolizasVSFacturasAutErrId id = null;
        
        try{
             id = (ErpPolizasVSFacturasAutErrId) session.save(erpPolizasVSFacturasAutErr);
            transaction.commit();
                    
        }catch(HibernateException e){
            e.printStackTrace();
            transaction.rollback();
           return null;
        }finally{
        
            session.close();
        }
        
        return id;
        
    }

    @Override
    public boolean update(ErpPolizasVSFacturasAutErr erpPolizasVSFacturasAutErr) {
        
           Session session = sessionFactory.openSession();
          Transaction transaction = session.beginTransaction();
          
         
        try{
             session.update(erpPolizasVSFacturasAutErr);
          
              transaction.commit();
        
        }catch(HibernateException e){
            e.printStackTrace();
            transaction.rollback();
            return false;
        
        }finally{
            
            session.close();
        
        }
        
        return true;
        
    }

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }
    
}
