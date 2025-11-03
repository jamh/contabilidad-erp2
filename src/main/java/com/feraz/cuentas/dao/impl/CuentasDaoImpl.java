/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.feraz.cuentas.dao.impl;

/**
 *
 * @author Feraz3
 */
import com.feraz.cuentas.dao.CuentasDao;
import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Projections;

import com.feraz.cuentas.model.Cuentas;
import com.feraz.cuentas.model.CuentasId;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import org.hibernate.Query;
import org.hibernate.criterion.Restrictions;
public class CuentasDaoImpl implements CuentasDao{
    
    private SessionFactory sessionFactory;

    public CuentasId save(Cuentas cuentas) {
        
         Session session = sessionFactory.openSession();
        Transaction transaccion = session.beginTransaction();
        CuentasId id = null;
        try{
            
            id = (CuentasId)session.save(cuentas);
            transaccion.commit();
            
        }catch (HibernateException e){
            e.printStackTrace();
            transaccion.rollback();
            return null;
        }finally{
            
            session.close();
        }
        return id;
    }

    public List<Cuentas> findCuentasporCompania(String estructura) {
        
        List<Cuentas> cuentas;
        Session session = sessionFactory.openSession();
        Transaction transaccion = session.beginTransaction();
        
        try{
            
            cuentas = (List<Cuentas>)session.createQuery("from Cuentas where "
                    + "id.estructura = :estructura ")
                        .setString("estructura",estructura)
                    .list();
            transaccion.commit();
            
        }catch (HibernateException e){
            e.printStackTrace();
            transaccion.rollback();
            return null;
        }finally{
            
            session.close();
           
        }
        return cuentas;
    }
    
     public Cuentas findCuenta(String estructura, String cuentaAlias) {

        Cuentas cuentas;
        Session session = sessionFactory.openSession();
        Transaction transaccion = session.beginTransaction();

        try {

            cuentas = (Cuentas) session.createQuery("from Cuentas where id.estructura= :estructura and cuentaAlias= :cuentaAlias")
                    .setString("estructura", estructura)
                    .setString("cuentaAlias", cuentaAlias)
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

        return cuentas;
    }

    public boolean delete(Cuentas deleteCuentas) {
        
         Session session = sessionFactory.openSession();
        Transaction transaccion = session.beginTransaction();
        try{
            
            session.delete(deleteCuentas);
            transaccion.commit();
            
        }catch (HibernateException e){
            
            e.printStackTrace();
            transaccion.rollback();
            return false;
            
        }finally{
            
            session.close();
            
        }
        return true;
    }

    public boolean update(Cuentas updateCuentas) {
        
         Session session = sessionFactory.openSession();
        Transaction transaccion = session.beginTransaction();
        
        try{
            
            session.update(updateCuentas);
            transaccion.commit();
            
        }catch (HibernateException e){
            e.printStackTrace();
            transaccion.rollback();
            return false;
            
        }finally{
            
            session.close();
            
        }
        return true;
    }
    
    
    public Cuentas findCuentaUpdate(Cuentas cta){
    
     Cuentas cuentas;
        Session session = sessionFactory.openSession();
        Transaction transaccion = session.beginTransaction();

        try {
            

            cuentas = (Cuentas) session.createQuery("from Cuentas where id.estructura= :estructura and id.cuenta= :cuenta and cuentaAlias= :cuentaAlias"
                    + "and nombre= :nombre and afectable = : afectable and cierre= : cierre and estatus= : estatus and naturaleza= : naturaleza and tipo= : tipo and mayor= : mayor and cuentaSat= : cuentaSat")
                    .setString("estructura", cta.getId().getEstructura())
                    .setString("cuenta", cta.getId().getCuenta())
                    .setString("cuentaAlias", cta.getCuentaAlias())
                    .setString("nombre", cta.getNombre())
                    .setString("afectable", cta.getAfectable())
                    .setString("cierre", cta.getCierre())
                    .setString("estatus", cta.getEstatus())
                    .setString("naturaleza", cta.getNaturaleza())
                    .setString("tipo", cta.getTipo())
                    .setString("mayor", cta.getMayor())
                    .setString("cuentaSat", cta.getCuentaSat())
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

        return cuentas;
    
    }
    
    
     public boolean updatePorCtaAlias(Cuentas cuentas) {
        Session session = sessionFactory.openSession();
        Transaction transaccion = session.beginTransaction();
        try {

            String hql = "update Cuentas set CUENTA = '" + cuentas.getId().getCuenta() + "', NOMBRE = '" + cuentas.getNombre() + "',USUARIO = '" + cuentas.getUsuario() + "', ID_ORD_BALANZA = '" + cuentas.getIdOrdenBalanza() + "' , CUENTA_PADRE = '" + cuentas.getCuentaPadre() + "' , CUENTA_ALIAS = '" + cuentas.getCuentaAlias()+ "' , DIVISA = '" + cuentas.getDivisa()+ "' , BANCO = '" + cuentas.getBanco()+ "' , AFECTABLE = '" + cuentas.getAfectable() + "' , CIERRE = '" + cuentas.getCierre() + "' , ESTATUS = '" + cuentas.getEstatus() + "' , NATURALEZA = '" + cuentas.getNaturaleza() + "' , TIPO = '" + cuentas.getTipo() + "' ,CTA_COMPLEMENTARIA = '" + cuentas.getCtaComplementaria() + "' , MAYOR = '" + cuentas.getMayor() + "' , CLASIFICACION1 = '" + cuentas.getClasificacion1() + "' , CLASIFICACION2 = '" + cuentas.getClasificacion2() + "' , CUENTA_SAT = '" + cuentas.getCuentaSat() + "' , NOMBRE2 = '" + cuentas.getNombre2() + "'" 
                    + " where id.estructura = :estructura and idCuentas = :idCuentas";
            Query query = session.createQuery(hql);
            query.setString("estructura", cuentas.getId().getEstructura());
            query.setInteger("idCuentas", cuentas.getIdCuentas());
            
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
     
      public boolean updateAtributosCta(Cuentas cuentas) {
        Session session = sessionFactory.openSession();
        Transaction transaccion = session.beginTransaction();
        try {

            String hql = "update Cuentas set ESTATUS = '" + cuentas.getEstatus() + "'" 
                    + " where id.estructura = :estructura and id.cuenta = :cuenta";
            Query query = session.createQuery(hql);
            query.setString("estructura", cuentas.getId().getEstructura());
            query.setString("cuenta", cuentas.getId().getCuenta());
            
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
     
      public boolean updatePorAfectable(String compania, String ctaPadre) {
        Session session = sessionFactory.openSession();
        Transaction transaccion = session.beginTransaction();
        try {

            String hql = "update Cuentas set AFECTABLE = 'N' where id.estructura = :estructura and id.cuenta = :cuenta";
            Query query = session.createQuery(hql);
            query.setString("estructura", compania);
            query.setString("cuenta", ctaPadre);
            
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
     
    
     
     
      public int getMaxId(CuentasId id) {
        Session session = sessionFactory.openSession();
        System.out.println("id.getEstructura()"+id.getEstructura());
        try {
            DetachedCriteria maxSec = DetachedCriteria.forClass(Cuentas.class);


            Criteria criteria = maxSec.getExecutableCriteria(session);
            criteria.add(Restrictions.eq("id.estructura", id.getEstructura()));
            criteria.setProjection(Projections.max("idCuentas"));
            int campo = 0;
            List lista = criteria.list();
            if (lista.get(0) == null) {
                campo = 0;
            } else {
                campo = new Integer(lista.get(0).toString());
            }
            campo++;
            //String idCampo = Integer.toString(campo);
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
