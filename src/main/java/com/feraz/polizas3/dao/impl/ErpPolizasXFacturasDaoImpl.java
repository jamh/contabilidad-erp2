/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.feraz.polizas3.dao.impl;

import com.feraz.contabilidad.convertidor.model.ErpCatConvertidorId;
import com.feraz.polizas3.dao.ErpPolizasXFacturasDao;
import com.feraz.polizas3.model.ErpPolizasXFacturas;
import com.feraz.polizas3.model.ErpPolizasXFacturasId;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

/**
 *
 * @author Feraz3
 */
public class ErpPolizasXFacturasDaoImpl implements ErpPolizasXFacturasDao {

    private SessionFactory sessionFactory;

    public ErpPolizasXFacturasId save(ErpPolizasXFacturas erpPolizasXFacturas) {

        Session session = sessionFactory.openSession();
        Transaction transaccion = session.beginTransaction();
        ErpPolizasXFacturasId id = null;
        try {

            id = (ErpPolizasXFacturasId) session.save(erpPolizasXFacturas);
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

    public boolean delete(ErpPolizasXFacturas deleteErpPolizasXFacturas) {

        Session session = sessionFactory.openSession();
        Transaction transaccion = session.beginTransaction();
        try {

            session.delete(deleteErpPolizasXFacturas);
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

    public boolean update(ErpPolizasXFacturas updateErpPolizasXFacturas) {

        Session session = sessionFactory.openSession();
        Transaction transaccion = session.beginTransaction();

        try {

            session.update(updateErpPolizasXFacturas);
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

    public ErpPolizasXFacturas buscaNumero(String compania, int numero) {
        List<ErpPolizasXFacturas> erpFeComprobantes;
        Session session = sessionFactory.openSession();
        Transaction transaccion = session.beginTransaction();

        try {
            System.out.println(compania + "-" + numero);
            erpFeComprobantes = (List<ErpPolizasXFacturas>) session.createQuery("from ErpPolizasXFacturas where id.compania='" + compania + "' and numeroFe =" + numero)
                    //                  .setString("compania", compania)
                    //                    .setInteger("num", numero)
                    .list();

            System.out.println(erpFeComprobantes);
            ErpPolizasXFacturas res = null;
            //session.getTransaction().commit();
            if (!erpFeComprobantes.isEmpty()) {
                res = erpFeComprobantes.get(0);
            } else {
                return null;
            }
            //return res.getValor();
            //System.out.println(respuestas);

            transaccion.commit();
            return res;
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
    
     public boolean deleteRelacion(ErpPolizasXFacturas erpPolizasXFacturas) {
        Session session = sessionFactory.openSession();
        Transaction transaccion = session.beginTransaction();
        String id = null;
    
        try {
    
            
            
            String hql = "Delete ErpPolizasXFacturas where  id.compania = :compania and id.numeroPol = :numeroPol and id.fechaPol= :fechaPol and id.tipoPol= :tipoPol and uuid= :uuid";
           // System.out.println("BORRAR DET CONVERT:" + erpcatid.getCompania() + erpcatid.getIdconcgasto() + erpcatid.getOrigen() + erpcatid.getNoCaso());
            Query query = session.createQuery(hql);
            query.setString("compania", erpPolizasXFacturas.getId().getCompania())
                    .setString("numeroPol", erpPolizasXFacturas.getId().getNumeroPol())
                    .setDate("fechaPol",erpPolizasXFacturas.getId().getFechaPol())
                    .setString("tipoPol", erpPolizasXFacturas.getId().getTipoPol())
                    .setString("uuid", erpPolizasXFacturas.getUuid());
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
 public boolean deleteRelacionPorCancelacion(String compania, String tipoPoliza,String fecha, String numero)  {
     
  
          Session session = sessionFactory.openSession();
        Transaction transaccion = session.beginTransaction();
        try {
     
             SimpleDateFormat formatoDelTexto2 = new SimpleDateFormat("dd/MM/yyyy");
                   String strFechaCap = fecha;
                    Date fechaCap = null;
              try {
                  //Obtener maxima secuencia
                     fechaCap = formatoDelTexto2.parse(strFechaCap);
              } catch (ParseException ex) {
                  Logger.getLogger(ErpPolizasXFacturasDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
              }
            String hql = "Delete ErpPolizasXFacturas where  id.compania = :compania and id.numeroPol = :numero and id.fechaPol= :fecha and id.tipoPol= :tipoPoliza";
           
              Query query = session.createQuery(hql);
        query.setString("compania",compania);
        query.setString("tipoPoliza",tipoPoliza);
        query.setDate("fecha",fechaCap);
        query.setString("numero",numero);
      

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
    public ErpPolizasXFacturas buscaUUID(String compania, String uuid) {
        List<ErpPolizasXFacturas> erpFeComprobantes;
        Session session = sessionFactory.openSession();
        Transaction transaccion = session.beginTransaction();

        try {
            //   System.out.println(compania+"-"+ numero);
            erpFeComprobantes = (List<ErpPolizasXFacturas>) session.createQuery("from ErpPolizasXFacturas where id.compania= :compania and uuid = :uuid")
                    .setString("compania", compania)
                    .setString("uuid", uuid)
                    .list();

//            System.out.println(erpFeComprobantes);
            ErpPolizasXFacturas res = null;
            //session.getTransaction().commit();
            if (!erpFeComprobantes.isEmpty()) {
                res = erpFeComprobantes.get(0);
            } else {
                return null;
            }
            //return res.getValor();
            //System.out.println(respuestas);

            transaccion.commit();
            return res;
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

    public ErpPolizasXFacturas buscaPoliza(String compania, String numero, String tipo, Date fecha) {
        ErpPolizasXFacturas erpFeComprobantes;
        Session session = sessionFactory.openSession();
        Transaction transaccion = session.beginTransaction();

        try {
            //   System.out.println(compania+"-"+ numero);
            erpFeComprobantes = (ErpPolizasXFacturas) session.createQuery("from ErpPolizasXFacturas where id.compania= :compania and id.numeroPol = :numero and id.tipoPol = :tipo and id.fechaPol= :fecha")
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
            if (erpFeComprobantes !=null) {
                return erpFeComprobantes;
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
