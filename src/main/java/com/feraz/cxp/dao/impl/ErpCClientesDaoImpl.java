/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.feraz.cxp.dao.impl;

import com.feraz.cxp.model.ErpCClientes;
import com.feraz.cxp.model.ErpCClientesId;
import com.feraz.cxp.dao.ErpCClientesDao;
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
 * @author Feraz3
 */
public class ErpCClientesDaoImpl implements ErpCClientesDao{
    
    private SessionFactory sessionFactory;

    @Override
    public ErpCClientesId save(ErpCClientes erpCClientes) {
        
          System.out.println("llegando a guardar");
         Session session = sessionFactory.openSession();
        Transaction transaccion = session.beginTransaction();
        ErpCClientesId id = null;
        try {

            id = (ErpCClientesId) session.save(erpCClientes);
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
    public List<ErpCClientes> findErpCClientes(ErpCClientes clientes) {
        
          List<ErpCClientes> erpCClientes;
        Session session = sessionFactory.openSession();
        Transaction transaccion = session.beginTransaction();

        try {

            //erpSeguiDocumentos = (List<ErpSeguiDocumentos>) session.createQuery("from ErpSeguiDocumentos").list();
            
             erpCClientes = (List<ErpCClientes>) session.createQuery("from ErpCClientes where id.compania = :compania  and  id.idCliente = :idCliente and  id.origen = :origen")
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
        return erpCClientes;
    }
    @Override
      public ErpCClientes findProveedor(String compania,String rfc) {
        
        ErpCClientes erpCClientes;
        Session session = sessionFactory.openSession();
        Transaction transaccion = session.beginTransaction();

        try {

 
             erpCClientes = (ErpCClientes) session.createQuery("from ErpCClientes where id.compania = :compania  and (rfc = :rfc OR correo = :correo) and  id.origen = :origen")
                    .setString("compania",compania)    
                    .setString("rfc", rfc)
                    .setString("correo", rfc)  
                    .setString("origen", "P")    
                    .setMaxResults(1)
                    .uniqueResult();
                    
              transaccion.commit();
              
            if (erpCClientes !=null) {
                return erpCClientes;
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

       @Override
      public ErpCClientes findProveedor2(String compania,String rfc) {
        
        ErpCClientes erpCClientes;
        Session session = sessionFactory.openSession();
        Transaction transaccion = session.beginTransaction();

        try {

 
             erpCClientes = (ErpCClientes) session.createQuery("from ErpCClientes where id.compania = :compania  and (rfc = :rfc OR correo = :correo) and  id.origen = :origen and activacion =:activacion")
                    .setString("compania",compania)    
                    .setString("rfc", rfc)
                    .setString("correo", rfc)  
                    .setString("origen", "P")
                    .setString("activacion", "1")
                    .setMaxResults(1)
                    .uniqueResult();
                    
              transaccion.commit();
              
            if (erpCClientes !=null) {
                return erpCClientes;
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
    @Override
    public boolean delete(ErpCClientes deleteErpCClientes) {
        
          Session session = sessionFactory.openSession();
        Transaction transaccion = session.beginTransaction();

        try {

            session.delete(deleteErpCClientes);
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

    @Override
    public boolean update(ErpCClientes updateErpCClientes) {
        
         Session session = sessionFactory.openSession();
        Transaction transaccion = session.beginTransaction();

        try {

            session.update(updateErpCClientes);
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
     public String getMaxErpCClientes(ErpCClientesId id) {
        Session session = sessionFactory.openSession();
        try {
            DetachedCriteria maxSec = DetachedCriteria.forClass(ErpCClientes.class);


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
        public boolean updateDatosBancariosExt(ErpCClientes disp) {
        Session session = sessionFactory.openSession();
        Transaction transaccion = session.beginTransaction();
        try {

            String hql = "update ErpCClientes set swift = :swift, iban = :iban where id.compania = :compania and usuario = :usuario";
            Query query = session.createQuery(hql);
            query.setString("compania", disp.getId().getCompania());
            query.setString("swift", disp.getSwift());
            query.setString("iban", disp.getIban());
            query.setString("usuario", disp.getUsuario());
            
            

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
     
     public boolean updateDatosBancarios(ErpCClientes disp) {
        Session session = sessionFactory.openSession();
        Transaction transaccion = session.beginTransaction();
        try {

            String hql = "update ErpCClientes set banco = :banco, numeroCuenta = :numeroCuenta, cuentaClave = :cuentaClave where id.compania = :compania and usuario = :usuario";
            Query query = session.createQuery(hql);
            query.setString("compania", disp.getId().getCompania());
            query.setString("banco", disp.getBanco());
            query.setString("numeroCuenta", disp.getNumeroCuenta());
            query.setString("cuentaClave", disp.getCuentaClave());
            query.setString("usuario", disp.getUsuario());
            
            

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
    
     
    public boolean updateVerific(ErpCClientes disp) {
        Session session = sessionFactory.openSession();
        Transaction transaccion = session.beginTransaction();
        try {

            String hql = "update ErpCClientes set activacion = :activacion,cadenaVerific = :cadenaVerific where id.compania = :compania and usuario = :usuario";
            Query query = session.createQuery(hql);
            query.setString("compania", disp.getId().getCompania());
            query.setString("usuario", disp.getUsuario());
            query.setString("activacion", disp.getActivacion());
            query.setString("cadenaVerific", disp.getCadenaVerific());
            

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
