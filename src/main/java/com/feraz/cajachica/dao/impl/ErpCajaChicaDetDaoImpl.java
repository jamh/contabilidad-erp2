/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.feraz.cajachica.dao.impl;

import com.feraz.cajachica.dao.ErpCajaChicaDetDao;
import com.feraz.cajachica.model.ErpCajaChicaDet;
import com.feraz.cajachica.model.ErpCajaChicaDetId;
import com.feraz.cajachica.model.ErpCajaChicaId;
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
 * @author vavi
 */
public class ErpCajaChicaDetDaoImpl implements ErpCajaChicaDetDao{

    private SessionFactory sessionFactory;
    
    @Override
    public ErpCajaChicaDetId save(ErpCajaChicaDet erpCajaChicaDet) {
        
         Session session = sessionFactory.openSession();
        Transaction transaccion = session.beginTransaction();
        ErpCajaChicaDetId id = null;
        try {

            id = (ErpCajaChicaDetId) session.save(erpCajaChicaDet);
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
    public boolean delete(ErpCajaChicaDet erpCajaChicaDet) {
        
               Session session = sessionFactory.openSession();
        Transaction transaccion = session.beginTransaction();

        try {

            session.delete(erpCajaChicaDet);
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

    
      public boolean actualizaEstatusCaja(ErpCajaChicaDet erpCajaChicaDet) {
        Session session = sessionFactory.openSession();
        Transaction transaccion = session.beginTransaction();

        //System.out.println("actualizando pagos");
        try {

            String hql = "update ErpCajaChicaDet set estatus =:estatus where  id.compania = :compania and id.idCaja = :idCaja and id.sec = :sec";

            Query query = session.createQuery(hql);
            query.setString("compania", erpCajaChicaDet.getId().getCompania());
            query.setInteger("idCaja", erpCajaChicaDet.getId().getIdCaja());
            query.setInteger("sec", erpCajaChicaDet.getId().getSec());
            query.setString("estatus", erpCajaChicaDet.getEstatus());


//                System.out.println("com.IDCONCGASTO:" + com.IDCONCGASTO);
//                System.out.println("com.compania:" + compania);
//                System.out.println(" com.NUMERO:" + com.NUMERO);
            int rowCount = query.executeUpdate();
//                System.out.println("rowCount:" + rowCount);

            transaccion.commit();

        } catch (HibernateException e) {
           
            transaccion.rollback();
            return false;
        } finally {

            session.close();
        }

        return true;

    }
    
    @Override
    public boolean update(ErpCajaChicaDet erpCajaChicaDet) {
        
            
             Session session = sessionFactory.openSession();
        Transaction transaccion = session.beginTransaction();

        try {

            session.update(erpCajaChicaDet);
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

    @Override
    public int getMaxErpCajaChicaDetId(ErpCajaChicaDetId id) {
        
            Session session = sessionFactory.openSession();
        try {
            DetachedCriteria maxSec = DetachedCriteria.forClass(ErpCajaChicaDet.class);

            Criteria criteria = maxSec.getExecutableCriteria(session);
            criteria.add(Restrictions.eq("id.compania", id.getCompania()));
            criteria.add(Restrictions.eq("id.idCaja", id.getIdCaja()));

            criteria.setProjection(Projections.max("id.sec"));
            int campo = 0;
            List lista = criteria.list();
            if (lista.get(0) == null) {
                campo = 0;
            } else {
                campo = Integer.parseInt(lista.get(0).toString());
               // System.out.println("campo1"+campo);

            }
            campo++;
            return campo;

        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        } finally {
            session.close();
        }

        
    }

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }
    
    
    
}
