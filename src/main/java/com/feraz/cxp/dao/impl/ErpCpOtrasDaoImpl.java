package com.feraz.cxp.dao.impl;

import com.feraz.cxp.dao.ErpCpOtrasDao;
import com.feraz.cxp.model.ErpCpOtras;
import com.feraz.cxp.model.ErpCpOtrasId;
import com.feraz.facturas.webcontrolfe.model.ErpFeComprobantes;
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
public class ErpCpOtrasDaoImpl implements ErpCpOtrasDao {

    private SessionFactory sessionFactory;

    @Override
    public ErpCpOtrasId save(ErpCpOtras erpCpOtras) {
        Session session = sessionFactory.openSession();
        Transaction transaccion = session.beginTransaction();
        ErpCpOtrasId id = null;
        try {

            id = (ErpCpOtrasId) session.save(erpCpOtras);
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
    public List<ErpCpOtras> findErpCClientes(ErpCpOtrasId erpCpOtras) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean delete(ErpCpOtras erpCpOtras) {
        Session session = sessionFactory.openSession();
        Transaction transaccion = session.beginTransaction();

        try {

            session.delete(erpCpOtras);
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
    public boolean update(ErpCpOtras erpCpOtras) {
        Session session = sessionFactory.openSession();
        Transaction transaccion = session.beginTransaction();

        try {

            session.update(erpCpOtras);
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

    public int getMaxErpCpOtrasId(ErpCpOtrasId id) {
        Session session = sessionFactory.openSession();
        try {
            DetachedCriteria maxSec = DetachedCriteria.forClass(ErpCpOtras.class);

            Criteria criteria = maxSec.getExecutableCriteria(session);
            criteria.add(Restrictions.eq("id.compania", id.getCompania()));

            criteria.setProjection(Projections.max("id.id"));
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
    
    
     public boolean updateErpCpOtras(ErpCpOtras disp, String fecha) {
        Session session = sessionFactory.openSession();
        Transaction transaccion = session.beginTransaction();
        try {

            if(disp.getIdTipoGasto() != null){
                
               if(disp.getIdServicio()!= null){   
                
                    String hql = "update ErpCpOtras set CONCEPTO_CXP = '" + disp.getConceptoCxp() + "', CTO_CXP = '" + disp.getCtoCxp() + "', ID_TIPO_NEGOCIO = '" + disp.getIdTipoNegocio()+ "', ID_SERVICIO = " + disp.getIdServicio()+ ", ID_PAIS_CXP = '" + disp.getIdPaisCxp()+ "', FECHA_VENC_CXP = :fecha, ID_TIPO_GASTO = :tipoGasto, FECHA_CONTAB_PROV = :fechaConta, FECHA_CASH_FLOW = :fechaCashFlow  where id.compania = :compania and id.id = :id";
                    Query query = session.createQuery(hql);
                    query.setString("compania", disp.getId().getCompania());
                    query.setInteger("id", disp.getId().getId());
                    query.setDate("fecha", disp.getFechaVencCxp());
                    query.setDate("fechaConta", disp.getFechaContabProv());
                    query.setDate("fechaCashFlow", disp.getFechaCashFlow());
                    query.setInteger("tipoGasto", disp.getIdTipoGasto());
                    int rowCount = query.executeUpdate();
               }else{
                   
                   String hql = "update ErpCpOtras set CONCEPTO_CXP = '" + disp.getConceptoCxp() + "', CTO_CXP = '" + disp.getCtoCxp() + "', ID_TIPO_NEGOCIO = '" + disp.getIdTipoNegocio()+ "', ID_PAIS_CXP = '" + disp.getIdPaisCxp()+ "', FECHA_VENC_CXP = :fecha, ID_TIPO_GASTO = :tipoGasto, FECHA_CONTAB_PROV = :fechaConta, FECHA_CASH_FLOW = :fechaCashFlow where id.compania = :compania and id.id = :id";
                    Query query = session.createQuery(hql);
                    query.setString("compania", disp.getId().getCompania());
                    query.setInteger("id", disp.getId().getId());
                    query.setDate("fecha", disp.getFechaVencCxp());
                    query.setDate("fechaConta", disp.getFechaContabProv());
                    query.setDate("fechaCashFlow", disp.getFechaCashFlow());
                    query.setInteger("tipoGasto", disp.getIdTipoGasto());
                    int rowCount = query.executeUpdate();
               
               
               }
            }else{
                
                if(disp.getIdServicio()!= null){   
                
                String hql = "update ErpCpOtras set CONCEPTO_CXP = '" + disp.getConceptoCxp() + "', CTO_CXP = '" + disp.getCtoCxp() + "', ID_TIPO_NEGOCIO = '" + disp.getIdTipoNegocio()+ "', ID_SERVICIO = " + disp.getIdServicio()+ ", ID_PAIS_CXP = '" + disp.getIdPaisCxp()+ "', FECHA_VENC_CXP = :fecha,  FECHA_CONTAB_PROV = :fechaConta, FECHA_CASH_FLOW = :fechaCashFlow  where id.compania = :compania and id.id = :id";
                Query query = session.createQuery(hql);
                query.setString("compania", disp.getId().getCompania());
                query.setInteger("id", disp.getId().getId());
                query.setDate("fecha", disp.getFechaVencCxp());
                query.setDate("fechaConta", disp.getFechaContabProv());
                query.setDate("fechaCashFlow", disp.getFechaCashFlow());
                int rowCount = query.executeUpdate();
                
                }else{
                    
                    String hql = "update ErpCpOtras set CONCEPTO_CXP = '" + disp.getConceptoCxp() + "', CTO_CXP = '" + disp.getCtoCxp() + "', ID_TIPO_NEGOCIO = '" + disp.getIdTipoNegocio()+ "', ID_PAIS_CXP = '" + disp.getIdPaisCxp()+ "', FECHA_VENC_CXP = :fecha,  FECHA_CONTAB_PROV = :fechaConta, FECHA_CASH_FLOW = :fechaCashFlow  where id.compania = :compania and id.id = :id";
                    Query query = session.createQuery(hql);
                    query.setString("compania", disp.getId().getCompania());
                    query.setInteger("id", disp.getId().getId());
                    query.setDate("fecha", disp.getFechaVencCxp());
                    query.setDate("fechaConta", disp.getFechaContabProv());
                    query.setDate("fechaCashFlow", disp.getFechaCashFlow());
                    int rowCount = query.executeUpdate();

                
                }
            
            }
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
     
     
     public boolean descripcionCancelacion(String descripcionCan, ErpCpOtras comp) {
        Session session = sessionFactory.openSession();
        Transaction transaccion = session.beginTransaction();


        try {

            String hql = "update ErpCpOtras set USUARIO_CANCELA = :usuarioCan , FECHA_CANCELA = :fechaCance,  DESCRIPCION_CANCELA = '" + descripcionCan + "' where  id.compania = :compania and id.id = :id";

            Query query = session.createQuery(hql);
            query.setString("compania", comp.getId().getCompania());
            query.setInteger("id", comp.getId().getId());
            query.setTimestamp("fechaCance",new java.sql.Date(comp.getFechaCancela().getTime()));
            query.setString("usuarioCan", comp.getUsuarioCancela());

            int rowCount = query.executeUpdate();

            transaccion.commit();

        } catch (HibernateException e) {
            transaccion.rollback();
            return false;
        } finally {

            session.close();
        }

        return true;

    }
     
     
          public boolean updateErpCpOtrasEstatusPagos(ErpCpOtras disp) {
        Session session = sessionFactory.openSession();
        Transaction transaccion = session.beginTransaction();
        try {

            String hql = "update ErpCpOtras set folioPagos = "+disp.getFolioPagos()+", estatusCxp = :estatusCxp, pagoCie = :pagoCie,refenciaCie = :refenciaCie,conceptoCie = :conceptoCie, tipoPago = :tipoPago, totalAPagar = :totalAPagar where id.compania = :compania and id.id = :id";
            Query query = session.createQuery(hql);
            query.setString("compania", disp.getId().getCompania());
            query.setInteger("id", disp.getId().getId());
          //  query.setInteger("folioPagos", disp.getFolioPagos());
            query.setString("estatusCxp", disp.getEstatusCxp());
            query.setString("pagoCie", disp.getPagoCie());
            query.setString("refenciaCie", disp.getRefenciaCie());
            query.setString("conceptoCie", disp.getConceptoCie());
            query.setString("tipoPago", disp.getTipoPago());
            query.setBigDecimal("totalAPagar", disp.getTotalAPagar());
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
          
          public boolean updateErpCpOtrasEstatusPagosTeso(ErpCpOtras disp) {
        Session session = sessionFactory.openSession();
        Transaction transaccion = session.beginTransaction();
        try {

            String hql = "update ErpCpOtras set folioPagos = "+disp.getFolioPagos()+", estatusCxp = :estatusCxp, pagoCie = :pagoCie,refenciaCie = :refenciaCie,conceptoCie = :conceptoCie where id.compania = :compania and id.id = :id";
            Query query = session.createQuery(hql);
            query.setString("compania", disp.getId().getCompania());
            query.setInteger("id", disp.getId().getId());
          //  query.setInteger("folioPagos", disp.getFolioPagos());
            query.setString("estatusCxp", disp.getEstatusCxp());
            query.setString("pagoCie", disp.getPagoCie());
            query.setString("refenciaCie", disp.getRefenciaCie());
            query.setString("conceptoCie", disp.getConceptoCie());
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
          
     public boolean updateErpCpOtrasSaldoNotasExt(ErpCpOtras disp) {
        Session session = sessionFactory.openSession();
        Transaction transaccion = session.beginTransaction();
        try {

            String hql = "update ErpCpOtras set pagoAcumuladoCXP = :pagoAcumuladoCXP, estatusCxp = :estatusCxp where id.compania = :compania and id.id = :id";
            Query query = session.createQuery(hql);
            query.setString("compania", disp.getId().getCompania());
            query.setInteger("id", disp.getId().getId());
            //query.setDouble("total", disp.getTotal());
            query.setBigDecimal("pagoAcumuladoCXP", disp.getPagoAcumuladoCXP());
            query.setString("estatusCxp", disp.getEstatusCxp());
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
          
               public boolean updateErpCpOtrasEstatusPagosOtras(ErpCpOtras disp) {
        Session session = sessionFactory.openSession();
        Transaction transaccion = session.beginTransaction();
        try {

            String hql = "update ErpCpOtras set estatusCxp = :estatusCxp, pagoAcumuladoCXP = :pagoAcumuladoCXP, fechaPagoCxp = :fechaPagoCxp where id.compania = :compania and id.id = :id";
            Query query = session.createQuery(hql);
            query.setString("compania", disp.getId().getCompania());
            query.setInteger("id", disp.getId().getId());
          //  query.setInteger("folioPagos", disp.getFolioPagos());
            query.setString("estatusCxp", disp.getEstatusCxp());
            query.setBigDecimal("pagoAcumuladoCXP", disp.getPagoAcumuladoCXP());
            query.setDate("fechaPagoCxp", disp.getFechaPagoCxp());
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
               
      public boolean updateErpCpOtrasEstatusPagosOtrasSF(ErpCpOtras disp) {
        Session session = sessionFactory.openSession();
        Transaction transaccion = session.beginTransaction();
        try {

            String hql = "update ErpCpOtras set estatusCxp = :estatusCxp, pagoAcumuladoCXP = :pagoAcumuladoCXP where id.compania = :compania and id.id = :id";
            Query query = session.createQuery(hql);
            query.setString("compania", disp.getId().getCompania());
            query.setInteger("id", disp.getId().getId());
          //  query.setInteger("folioPagos", disp.getFolioPagos());
            query.setString("estatusCxp", disp.getEstatusCxp());
            query.setBigDecimal("pagoAcumuladoCXP", disp.getPagoAcumuladoCXP());
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
