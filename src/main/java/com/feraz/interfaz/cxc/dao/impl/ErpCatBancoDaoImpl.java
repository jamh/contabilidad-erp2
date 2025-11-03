/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.feraz.interfaz.cxc.dao.impl;

import com.feraz.interfaz.cxc.dao.ErpCatBancoDao;
import com.feraz.interfaz.cxc.model.ErpCatBanco;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

/**
 *
 * @author vavi
 */
public class ErpCatBancoDaoImpl implements ErpCatBancoDao {
    
    
    private SessionFactory sessionFactory;
    
      public boolean updateCuentasContaBan(ErpCatBanco disp) {
        Session session = sessionFactory.openSession();
        Transaction transaccion = session.beginTransaction();
        try {

            String hql = "update ErpCatBanco set cuenta = :cuenta, cuentaComplementaria = :cuentaComplementaria where id.compania = :compania and id.banco = :banco";
            Query query = session.createQuery(hql);
            query.setString("compania", disp.getId().getCompania());
            query.setString("banco", disp.getId().getBanco());
          //  query.setInteger("folioPagos", disp.getFolioPagos());
            query.setString("cuenta", disp.getCuenta());
            query.setString("cuentaComplementaria", disp.getCuentaComplementaria());

            int rowCount = query.executeUpdate();
            //    System.out.println("Rows affected: " + rowCount);

            transaccion.commit();

        } catch (HibernateException e) {
            e.printStackTrace();
            transaccion.rollback();
            return false;
        } catch (Exception e2) {
            e2.printStackTrace();
            transaccion.rollback();
        } finally {
            session.close();
        }
        return true;
    }

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }
    
      
      
}
