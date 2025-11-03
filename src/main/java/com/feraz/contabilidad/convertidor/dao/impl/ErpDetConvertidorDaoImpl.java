/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.feraz.contabilidad.convertidor.dao.impl;

import com.feraz.contabilidad.convertidor.dao.ErpDetConvertidorDao;
import com.feraz.contabilidad.convertidor.model.ErpCatConvertidor;
import com.feraz.contabilidad.convertidor.model.ErpCatConvertidorId;
import com.feraz.contabilidad.convertidor.model.ErpDetConvertidor;
import com.feraz.contabilidad.convertidor.model.ErpDetConvertidorId;
import java.math.BigDecimal;
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
 * @author Ing. JAMH
 */
public class ErpDetConvertidorDaoImpl implements ErpDetConvertidorDao {

    private SessionFactory sessionFactory;

    public ErpDetConvertidorId save(ErpDetConvertidor erpDetConvertidor) {
        Session session = sessionFactory.openSession();
        Transaction transaccion = session.beginTransaction();
        ErpDetConvertidorId id = null;
        try {

            id = (ErpDetConvertidorId) session.save(erpDetConvertidor);
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

    public List<ErpDetConvertidor> findErpDetConvertidor(String compania,String noCaso) {
//          System.out.println(compania + idconcgasto + nocaso + origen);
        List<ErpDetConvertidor> erpDetConvertidor;

        Session session = sessionFactory.openSession();
        Transaction transaccion = session.beginTransaction();

      

        try {

            erpDetConvertidor = (List<ErpDetConvertidor>) session.createQuery("from ErpDetConvertidor where id.compania = :compania  and  id.noCaso = :noCaso order by orden")
                    .setString("compania", compania)    
                    .setString("noCaso", noCaso)
                    .list();
            transaccion.commit();

        } catch (HibernateException e) {
            e.printStackTrace();
            transaccion.rollback();
            return null;
        } finally {

            session.close();

        }
        return erpDetConvertidor;
    }
    
    public List<ErpDetConvertidor> findErpDetConvertidorOrigen(String compania,String noCaso,String Origen) {
//          System.out.println(compania + idconcgasto + nocaso + origen);
        List<ErpDetConvertidor> erpDetConvertidor;

        Session session = sessionFactory.openSession();
        Transaction transaccion = session.beginTransaction();

      

        try {

            erpDetConvertidor = (List<ErpDetConvertidor>) session.createQuery("from ErpDetConvertidor where id.compania = :compania  and  id.noCaso = :noCaso and id.origen = :origen order by orden")
                    .setString("compania", compania)    
                    .setString("noCaso", noCaso)
                    .setString("origen",Origen)
                    .list();
            transaccion.commit();

        } catch (HibernateException e) {
            e.printStackTrace();
            transaccion.rollback();
            return null;
        } finally {

            session.close();

        }
        return erpDetConvertidor;
    }

    public boolean delete(String compania, String idconcgasto, String origen, BigDecimal noCaso) {
        Session session = sessionFactory.openSession();
        Transaction transaccion = session.beginTransaction();
        
        System.out.println("borrando detalle");

        try {

            String hql = "delete from ErpDetConvertidor where id.compania = :compania and id.origen = :origen and id.idconcgasto = :idconcgasto and id.noCaso = :noCaso";
            Query query = session.createQuery(hql);
            query.setString("compania", compania);
            query.setString("origen", origen);
            query.setString("idconcgasto", idconcgasto);
            query.setBigDecimal("noCaso", noCaso);
           // query.setBigDecimal("sec", sec);
            int rowCount = query.executeUpdate();
            //    System.out.println("Rows affected: " + rowCount);

            transaccion.commit();
            //session.delete(deleteErpDetConvertidor);
            //transaccion.commit();

        } catch (HibernateException e) {
            e.printStackTrace();
            transaccion.rollback();
            return true;
        } finally {

            session.close();

        }
        return true;

    }

    public boolean update(ErpDetConvertidor updateErpDetConvertidor) {
        Session session = sessionFactory.openSession();
        Transaction transaccion = session.beginTransaction();

        try {

            session.update(updateErpDetConvertidor);
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

    public int getMaxIdDetConvertidor(ErpDetConvertidorId id) {
        Session session = sessionFactory.openSession();
        try {
            DetachedCriteria maxSec = DetachedCriteria.forClass(ErpDetConvertidor.class);

            Criteria criteria = maxSec.getExecutableCriteria(session);
            criteria.add(Restrictions.eq("id.compania", id.getCompania()));
            criteria.add(Restrictions.eq("id.origen", id.getOrigen()));
            criteria.add(Restrictions.eq("id.idconcgasto", id.getIdconcgasto()));
            criteria.add(Restrictions.eq("id.noCaso", id.getNoCaso()));
            criteria.setProjection(Projections.max("id.sec"));
            int sec = 0;
            List lista = criteria.list();
            if (lista.get(0) == null) {
                sec = 0;
            } else {
                sec = new Integer(lista.get(0).toString());
            }
            sec++;

            return sec;
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        } finally {
            session.close();
        }

    }

    public boolean borraDetConvPorSec(String compania, String idconcgasto, String origen, BigDecimal noCaso, BigDecimal sec) {
        Session session = sessionFactory.openSession();
        Transaction transaccion = session.beginTransaction();
        try {

            String hql = "delete from ErpDetConvertidor where id.compania = :compania and id.origen = :origen and id.idconcgasto = :idconcgasto and id.noCaso = :noCaso and sec = :sec";
            Query query = session.createQuery(hql);
            query.setString("compania", compania);
            query.setString("origen", origen);
            query.setString("idconcgasto", idconcgasto);
            query.setBigDecimal("noCaso", noCaso);
            query.setBigDecimal("sec", sec);
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

    public boolean updateDetConvPorSec(String compania, String idconcgasto, String origen, BigDecimal noCaso, BigDecimal sec, ErpDetConvertidor disp) {
        Session session = sessionFactory.openSession();
        Transaction transaccion = session.beginTransaction();
        try {
            String conceptoDiot = "";
            if(disp.getConceptoDiot() != null){
                 conceptoDiot = disp.getConceptoDiot();
             }

            String hql = "update ErpDetConvertidor set CONCEPTO_DIOT = '" + conceptoDiot + "',IDCAMPO = '" + disp.getIdcampo() + "',C_COSTOS = '" + disp.getC_costos() + "', CUENTA = '" + disp.getCuenta() + "', DESCRIPCION = '" + disp.getDescripcion() + "', REFERENCIA = '" + disp.getReferencia() + "', REFERENCIA2 = '" + disp.getReferencia2() + "', T_APLICACION = '" + disp.gettAplicacion()+ "', RFC = '" + disp.getRfc()+"', ORDEN = '" + disp.getOrden()+ "', OPERACIONES = '" + disp.getOperaciones() + "'  where id.compania = :compania and id.origen = :origen and id.idconcgasto = :idconcgasto and id.noCaso = :noCaso and sec = :sec";
            Query query = session.createQuery(hql);
            query.setString("compania", compania);
            query.setString("origen", origen);
            query.setString("idconcgasto", idconcgasto);
            query.setBigDecimal("noCaso", noCaso);
            query.setBigDecimal("sec", sec);
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

    public boolean deleteAll(ErpCatConvertidorId erpcatid) {
        Session session = sessionFactory.openSession();
        Transaction transaccion = session.beginTransaction();
        String id = null;
        try {
            String hql = "Delete ErpDetConvertidor where  id.compania = :compania and id.idconcgasto = :idcongasto and id.origen= :origen and id.noCaso= :nocaso";
            System.out.println("BORRAR DET CONVERT:" + erpcatid.getCompania() + erpcatid.getIdconcgasto() + erpcatid.getOrigen() + erpcatid.getNoCaso());
            Query query = session.createQuery(hql);
            query.setString("compania", erpcatid.getCompania())
                    .setString("idcongasto", erpcatid.getIdconcgasto())
                    .setString("origen", erpcatid.getOrigen())
                    .setBigDecimal("nocaso", erpcatid.getNoCaso());

            int delete = query.executeUpdate();
            System.out.println("delete:" + delete);
            transaccion.commit();
            if (delete < 0) {
                return false;
            } else {
                return true;
            }

        } catch (HibernateException e) {
            e.printStackTrace();
            transaccion.rollback();
            return false;
        } finally {

            session.close();
        }

    }

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

}
