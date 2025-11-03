/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.feraz.conciliacion.dao.impl;

import com.feraz.conciliacion.dao.ErpFeCargaBancoDao;
import com.feraz.conciliacion.model.ErpFeCargaBanco;
import com.feraz.conciliacion.model.ErpFeCargaBancoId;
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
 * @author vavi
 */
public class ErpFeCargaBancoDaoImpl implements ErpFeCargaBancoDao{
    
    private SessionFactory sessionFactory;

    @Override
    public ErpFeCargaBancoId save(ErpFeCargaBanco erpFeCargaBanco) {
        
//        System.out.println("descripcion: "+erpFeCargaBanco.getDescripcion());
//        System.out.println("estatus: "+erpFeCargaBanco.getEstatus());
//        System.out.println("idTipoPago: "+erpFeCargaBanco.getIdTipoPago());
//        System.out.println("noCuenta: "+erpFeCargaBanco.getNoCuenta());
//        System.out.println("nomArchivo: "+erpFeCargaBanco.getNomArchivo());
//        System.out.println("referencia: "+erpFeCargaBanco.getReferencia());
//        System.out.println("tipoMovto: "+erpFeCargaBanco.getTipoMovto());
//        System.out.println("fechaEmision: "+erpFeCargaBanco.getFechaEmision());
//        System.out.println("fechaPago: "+erpFeCargaBanco.getFechaPagado());
//        System.out.println("importe: "+erpFeCargaBanco.getImporte());
//        System.out.println("compania: "+erpFeCargaBanco.getId().getCompania());
//        System.out.println("sec: "+erpFeCargaBanco.getId().getSec());
        
        
          Session session = sessionFactory.openSession();
        Transaction transaccion = session.beginTransaction();
        ErpFeCargaBancoId id = null;
        try {

            id = (ErpFeCargaBancoId) session.save(erpFeCargaBanco);
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
    
    
        public int getMaxIdErpFeCargaBanco(ErpFeCargaBancoId id) {
        Session session = sessionFactory.openSession();
        try {
            DetachedCriteria maxSec = DetachedCriteria.forClass(ErpFeCargaBanco.class);

            Criteria criteria = maxSec.getExecutableCriteria(session);
            criteria.add(Restrictions.eq("id.compania", id.getCompania()));
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

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }
    
    
    
    
}
