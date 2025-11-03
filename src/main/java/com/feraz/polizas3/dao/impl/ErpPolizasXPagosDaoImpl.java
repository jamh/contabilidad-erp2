/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.feraz.polizas3.dao.impl;

import com.feraz.polizas3.dao.ErpPolizasXPagosDao;
import com.feraz.polizas3.model.ErpPolizasXFacturasId;
import com.feraz.polizas3.model.ErpPolizasXPagos;
import com.feraz.polizas3.model.ErpPolizasXPagosId;
import java.util.Date;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

/**
 *
 * @author 55555
 */
public class ErpPolizasXPagosDaoImpl implements ErpPolizasXPagosDao{
    
     private SessionFactory sessionFactory;

    @Override
    public ErpPolizasXPagosId save(ErpPolizasXPagos erpPolizasXPagos) {
        
         Session session = sessionFactory.openSession();
        Transaction transaccion = session.beginTransaction();
        ErpPolizasXPagosId id = null;
        try {

            id = (ErpPolizasXPagosId) session.save(erpPolizasXPagos);
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
    
    public boolean delete(ErpPolizasXPagos erpPolizasXPagos) {

        Session session = sessionFactory.openSession();
        Transaction transaccion = session.beginTransaction();
        try {

            session.delete(erpPolizasXPagos);
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
    
    
    
    
         public boolean deleteRelacion(ErpPolizasXPagos erpPolizasXPagos) {
        Session session = sessionFactory.openSession();
        Transaction transaccion = session.beginTransaction();
        String id = null;
    
        try {
            
            System.out.println(erpPolizasXPagos.getId().getCompania());
            System.out.println(erpPolizasXPagos.getId().getNumeroPol());
            System.out.println(erpPolizasXPagos.getId().getFechaPol());
            System.out.println(erpPolizasXPagos.getId().getTipoPol());
            System.out.println(erpPolizasXPagos.getUuid());
    
            
            
            String hql = "Delete ErpPolizasXPagos where  id.compania = :compania and id.numeroPol = :numeroPol and id.fechaPol= :fechaPol and id.tipoPol= :tipoPol and uuid= :uuid";
           // System.out.println("BORRAR DET CONVERT:" + erpcatid.getCompania() + erpcatid.getIdconcgasto() + erpcatid.getOrigen() + erpcatid.getNoCaso());
            Query query = session.createQuery(hql);
            query.setString("compania", erpPolizasXPagos.getId().getCompania())
                    .setString("numeroPol", erpPolizasXPagos.getId().getNumeroPol())
                    .setDate("fechaPol",erpPolizasXPagos.getId().getFechaPol())
                    .setString("tipoPol", erpPolizasXPagos.getId().getTipoPol())
                    .setString("uuid", erpPolizasXPagos.getUuid());
            System.out.println("query"+query);
            int delete = query.executeUpdate();
            System.out.println("delete:" + delete);
            transaccion.commit();
            if (delete <= 0) {
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
         
        public ErpPolizasXPagos buscaPoliza(String compania, String numero, String tipo, Date fecha) {
        ErpPolizasXPagos erpPolizasXPagos;
        Session session = sessionFactory.openSession();
        Transaction transaccion = session.beginTransaction();

        try {
            //   System.out.println(compania+"-"+ numero);
            erpPolizasXPagos = (ErpPolizasXPagos) session.createQuery("from ErpPolizasXPagos where id.compania= :compania and id.numeroPol = :numero and id.tipoPol = :tipo and id.fechaPol= :fecha")
                    .setString("compania", compania)
                    .setString("numero", numero)
                    .setString("tipo", tipo)
                    .setDate("fecha", fecha)
                    .setMaxResults(1)
                    .uniqueResult();
 transaccion.commit();
            //   System.out.println(erpFeComprobantes);
           // ErpPolizasXFacturas res = null;
            //session.getTransaction().commit();
            if (erpPolizasXPagos !=null) {
                return erpPolizasXPagos;
            } else {
                return null;
            }
            //return res.getValor();
            //System.out.println(respuestas);

           
//            return res;
        } catch (HibernateException e) {
            transaccion.rollback();
            return null;

        } catch (Exception ex) {
            ex.printStackTrace();
            return null;
        } finally {
            session.close();
        }
    }

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }
    
    
}
