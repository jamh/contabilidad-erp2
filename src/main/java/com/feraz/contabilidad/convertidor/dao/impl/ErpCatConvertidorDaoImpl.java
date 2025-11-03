package com.feraz.contabilidad.convertidor.dao.impl;

import com.feraz.contabilidad.convertidor.dao.ErpCatConvertidorDao;
import com.feraz.contabilidad.convertidor.model.ErpCatConvertidor;
//import com.feraz.contabilidad.convertidor.model.ErpCatConvertidor2;
import com.feraz.contabilidad.convertidor.model.ErpCatConvertidorId;
import java.math.BigDecimal;
import java.util.List;
import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;

/**
 *
 * @author Ing. JAMH
 */
public class ErpCatConvertidorDaoImpl implements ErpCatConvertidorDao {

    private SessionFactory sessionFactory;

    public ErpCatConvertidorId save(ErpCatConvertidor erpCatConvertidor) {
        Session session = sessionFactory.openSession();
        Transaction transaccion = session.beginTransaction();
        ErpCatConvertidorId id = null;
        try {

            id = (ErpCatConvertidorId) session.save(erpCatConvertidor);
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

    public List<ErpCatConvertidor> getConvertidor(String compania, String idconcgasto, String nocaso, String origen) {

        System.out.println(compania + idconcgasto + nocaso + origen);
        List<ErpCatConvertidor> erpCatConvertidor;

        Session session = sessionFactory.openSession();
        Transaction transaccion = session.beginTransaction();

        if (nocaso == null || nocaso == "") {

            return null;

        }

        BigDecimal noCaso = new BigDecimal(nocaso);

        try {

            erpCatConvertidor = (List<ErpCatConvertidor>) session.createQuery("from ErpCatConvertidor where id.compania = :compania and id.noCaso = :noCaso")
                    .setString("compania", compania)
//                    .setString("origen", origen)
//                    .setString("idconcgasto", idconcgasto)
                    .setBigDecimal("noCaso", noCaso)
                    .list();
            transaccion.commit();

        } catch (HibernateException e) {
            e.printStackTrace();
            transaccion.rollback();
            return null;
        } finally {

            session.close();

        }
        return erpCatConvertidor;

    }

    public List<ErpCatConvertidor> findErpCatConvertidor() {

        List<ErpCatConvertidor> erpCatConvertidor;
        Session session = sessionFactory.openSession();
        Transaction transaccion = session.beginTransaction();

        try {

            erpCatConvertidor = (List<ErpCatConvertidor>) session.createQuery("from ErpCatConvertidor").list();
            transaccion.commit();

        } catch (HibernateException e) {
            e.printStackTrace();
            transaccion.rollback();
            return null;
        } finally {

            session.close();

        }
        return erpCatConvertidor;
    }
    
//    public List<ErpCatConvertidor2> findErpCatConvertidor2(String compania) {
//
//        List<ErpCatConvertidor2> erpCatConvertidor;
//        Session session = sessionFactory.openSession();
//        Transaction transaccion = session.beginTransaction();
//
//        try {
//
//            erpCatConvertidor = (List<ErpCatConvertidor2>) session.createQuery("from ErpCatConvertidor2 where id.compania= :compania")
//                    .setString("compania", compania)
//                    .list();
//            transaccion.commit();
//
//        } catch (HibernateException e) {
//            e.printStackTrace();
//            transaccion.rollback();
//            return null;
//        } finally {
//
//            session.close();
//
//        }
//        return erpCatConvertidor;
//    }

    public boolean delete(ErpCatConvertidor deleteErpCatConvertidor) {
        Session session = sessionFactory.openSession();
        Transaction transaccion = session.beginTransaction();

        try {

            session.delete(deleteErpCatConvertidor);
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

    public boolean update(ErpCatConvertidor updateErpCatConvertidor) {
        Session session = sessionFactory.openSession();
        Transaction transaccion = session.beginTransaction();

        try {

            session.update(updateErpCatConvertidor);
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

    public int getMaxIdConvertidor(ErpCatConvertidorId id) {
        Session session = sessionFactory.openSession();
        try {
            DetachedCriteria maxSec = DetachedCriteria.forClass(ErpCatConvertidor.class);

            Criteria criteria = maxSec.getExecutableCriteria(session);
            criteria.add(Restrictions.eq("id.compania", id.getCompania()));
//            criteria.add(Restrictions.eq("id.origen", id.getOrigen()));
//            criteria.add(Restrictions.eq("id.idconcgasto", id.getIdconcgasto()));
            criteria.setProjection(Projections.max("id.noCaso"));
            int caso = 0;
            List lista = criteria.list();
            if (lista.get(0) == null) {
                caso = 0;
            } else {
                caso = new Integer(lista.get(0).toString());
            }
            caso++;

            return caso;
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        } finally {
            session.close();
        }

    }
    
    public ErpCatConvertidor buscaRfc(String emisor,String receptor){
        List<ErpCatConvertidor> erpCatConvertidor;
        Session session = sessionFactory.openSession();
        Transaction transaccion = session.beginTransaction();

        try {

            erpCatConvertidor = (List<ErpCatConvertidor>) session.createQuery("from ErpCatConvertidor where rfcEmisor = :emisor and rfcReceptor = :receptor")
                    .setString("emisor", emisor)
                    .setString("receptor", receptor)
                    .list();
            transaccion.commit();
            if(erpCatConvertidor==null) return null;
            if(erpCatConvertidor.isEmpty()) return null;
            
            return erpCatConvertidor.get(0);
        } catch (HibernateException e) {
            e.printStackTrace();
            transaccion.rollback();
            
            return null;
        } finally {

            session.close();

        }
        
        
    }

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

}
