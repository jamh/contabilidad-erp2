/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.feraz.cxp.dao.impl;
import com.feraz.contabilidad.sat.electronica.controll.ValidateCFDI;
import com.feraz.cxp.dao.ErpClientesDao;
import com.feraz.cxp.model.ErpClientes;
import com.feraz.cxp.model.ErpClientesId;
import java.util.List;
import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author 55555
 */
public class ErpClientesDaoImpl implements ErpClientesDao{
    private final Logger log = LoggerFactory.getLogger(ErpClientesDaoImpl.class);
    private SessionFactory sessionFactory;


    public ErpClientesId save(ErpClientes erpClientes) {
        
          System.out.println("llegando a guardar");
         Session session = sessionFactory.openSession();
        Transaction transaccion = session.beginTransaction();
        ErpClientesId id = null;
        try {

            id = (ErpClientesId) session.save(erpClientes);
            transaccion.commit();

        } catch (HibernateException e) {
            log.error("ERROR al guardar dao:",e);
            transaccion.rollback();
            return null;
        }catch (Exception e) {
            log.error("ERROR al guardar daoE:",e);
            transaccion.rollback();
            return null;
        }
        
        finally {

            session.close();
        }
        return id;
    }

    
    public List<ErpClientes> findErpClientes(ErpClientes clientes) {
        
          List<ErpClientes> erpClientes;
        Session session = sessionFactory.openSession();
        Transaction transaccion = session.beginTransaction();

        try {

            //erpSeguiDocumentos = (List<ErpSeguiDocumentos>) session.createQuery("from ErpSeguiDocumentos").list();
            
             erpClientes = (List<ErpClientes>) session.createQuery("from ErpClientes where id.compania = :compania  and  id.idCliente = :idCliente and  id.origen = :origen")
                    .setString("compania", clientes.getId().getCompania())    
                    .setString("idCliente", clientes.getId().getIdCliente())
                    .setString("origen", clientes.getId().getOrigen())    
                    .list();
            
            transaccion.commit();

        } catch (HibernateException e) {
            e.printStackTrace();
            transaccion.rollback();
            return null;
        } finally {

            session.close();

        }
        return erpClientes;
    }
    
      public ErpClientes findCliente(String compania,String rfc) {
        
          ErpClientes erpClientes;
        Session session = sessionFactory.openSession();
        Transaction transaccion = session.beginTransaction();

        try {

 
             erpClientes = (ErpClientes) session.createQuery("from ErpClientes where id.compania = :compania  and rfc = :idCliente and  id.origen = :origen")
                    .setString("compania",compania)    
                    .setString("rfc", "RFC")
                    .setString("origen", "P")    
                    .setMaxResults(1)
                    .uniqueResult();
                    
              transaccion.commit();
              
            if (erpClientes !=null) {
                return erpClientes;
            } else {
                return null;
            }
        

        } catch (HibernateException e) {
            e.printStackTrace();
            transaccion.rollback();
            return null;
        } finally {

            session.close();

        }
        
    }

    
    public boolean delete(ErpClientes deleteErpClientes) {
        
          Session session = sessionFactory.openSession();
        Transaction transaccion = session.beginTransaction();

        try {

            session.delete(deleteErpClientes);
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

    
    public boolean update(ErpClientes updateErpClientes) {
        
         Session session = sessionFactory.openSession();
        Transaction transaccion = session.beginTransaction();

        try {

            session.update(updateErpClientes);
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
    
     public String getMaxErpClientes(ErpClientesId id) {
        Session session = sessionFactory.openSession();
        try {
            DetachedCriteria maxSec = DetachedCriteria.forClass(ErpClientes.class);


            Criteria criteria = maxSec.getExecutableCriteria(session);
            criteria.add(Restrictions.eq("id.compania", id.getCompania()));
            criteria.add(Restrictions.eq("id.origen", id.getOrigen()));
            criteria.setProjection(Projections.max("id.idCliente"));
            int campo = 0;
            List lista = criteria.list();
            if (lista.get(0) == null) {
                campo = 0;
            } else {
                campo = new Integer(lista.get(0).toString());
               // System.out.println("campo1"+campo);
                
            }
            campo++;
             //System.out.println("campo2"+campo);
            String idCampo = Integer.toString(campo);
            return new String(idCampo);
        } catch (Exception e) {
            e.printStackTrace();
            return new String("0");
        } finally {
            session.close();
        }



    }
    

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }
    
    
    
}
