package com.feraz.contabilidad.saldos.dao.impl;

import com.feraz.contabilidad.saldos.dao.SaldosDao;
import com.feraz.contabilidad.saldos.model.Saldos;
import com.feraz.contabilidad.saldos.model.SaldosId;
import java.util.List;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

/**
 *
 * @author Ing. JAMH
 */
public class SaldosDaoImpl implements SaldosDao{

   private SessionFactory sessionFactory;

    public SaldosId save(Saldos saldos) {
         Session session = sessionFactory.openSession();
        Transaction transaccion = session.beginTransaction();
        SaldosId id = null;
        try {

            id = (SaldosId) session.save(saldos);
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

    public List<Saldos> findSaldos() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public boolean delete(Saldos deleteSaldos) {
        
          Session session = sessionFactory.openSession();
        Transaction transaccion = session.beginTransaction();

        try {

            session.delete(deleteSaldos);
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

    public boolean update(Saldos updateSaldos) {
        
        Session session = sessionFactory.openSession();
        Transaction transaccion = session.beginTransaction();

        try {

            session.update(updateSaldos);
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
