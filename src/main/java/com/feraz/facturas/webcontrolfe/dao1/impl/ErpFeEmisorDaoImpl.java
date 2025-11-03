
package com.feraz.facturas.webcontrolfe.dao1.impl;

/**
 *
 * @author Feraz3
 */

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import com.feraz.facturas.webcontrolfe.model.ErpFeEmisor;
import com.feraz.facturas.webcontrolfe.model.ErpFeEmisorId;
import com.feraz.facturas.webcontrolfe.dao1.ErpFeEmisorDao;

public class ErpFeEmisorDaoImpl implements ErpFeEmisorDao {

    private SessionFactory sessionFactory;

    public ErpFeEmisorId save(ErpFeEmisor erpEmisor) {

        Session session = sessionFactory.openSession();
        Transaction transaccion = session.beginTransaction();
        ErpFeEmisorId id = null;
        try {

            id = (ErpFeEmisorId) session.save(erpEmisor);
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

    public ErpFeEmisor findErpEmisor(String compania, String numero) {

        ErpFeEmisor erpEmisor;
        Session session = sessionFactory.openSession();
        Transaction transaccion = session.beginTransaction();

        try {

            erpEmisor = (ErpFeEmisor) session.createQuery("from ErpFeEmisor where id.compania= :compania and id.numero= :numero")
                    .setString("compania", compania)
                    .setInteger("numero", new Integer(numero))
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

        return erpEmisor;
    }

    public boolean delete(ErpFeEmisor deleteErpEmisor) {

        Session session = sessionFactory.openSession();
        Transaction transaccion = session.beginTransaction();
        try {

            session.delete(deleteErpEmisor);
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

    public boolean update(ErpFeEmisor updateErpEmisor) {

        Session session = sessionFactory.openSession();
        Transaction transaccion = session.beginTransaction();

        try {

            session.update(updateErpEmisor);
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
