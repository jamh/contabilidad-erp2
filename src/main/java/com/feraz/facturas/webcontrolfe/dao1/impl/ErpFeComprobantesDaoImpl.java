package com.feraz.facturas.webcontrolfe.dao1.impl;

/**
 *
 * @author Feraz3
 */
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import com.feraz.facturas.webcontrolfe.model.ErpFeComprobantes;
import com.feraz.facturas.webcontrolfe.model.ErpFeComprobantesId;
import com.feraz.facturas.webcontrolfe.dao1.ErpFeComprobantesDao;
import com.feraz.facturas.webcontrolfe.dto.ComprobanteDto;
import com.feraz.polizas3.model.PolizasId;
import java.math.BigDecimal;
import java.util.Iterator;
import java.util.List;
import org.apache.log4j.Logger;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;

public class ErpFeComprobantesDaoImpl implements ErpFeComprobantesDao {

    private static final Logger log = Logger.getLogger(ErpFeComprobantesDaoImpl.class);

    private SessionFactory sessionFactory;

    @Override
    public ErpFeComprobantesId save(ErpFeComprobantes erpFeComprobantes) {

        Session session = sessionFactory.openSession();
        Transaction transaccion = session.beginTransaction();
        ErpFeComprobantesId id = null;
        try {

            id = (ErpFeComprobantesId) session.save(erpFeComprobantes);
            transaccion.commit();

        } catch (HibernateException e) {
            log.error("Error save Comprobantes", e);
            transaccion.rollback();
            return null;
        } finally {

            session.close();
        }
        return id;
    }
    
    
    @Override
    public ErpFeComprobantesId save2(ErpFeComprobantes erpFeComprobantes) {

        Session session = sessionFactory.openSession();
        Transaction transaccion = session.beginTransaction();
        ErpFeComprobantesId id = null;
        try {
            
            Criteria criteria = session
                .createCriteria(ErpFeComprobantes.class)
                    .add(Restrictions.eq("id.compania", erpFeComprobantes.getId().getCompania()))
                .setProjection(Projections.max("id.numero"));
                Integer maxFe = (Integer)criteria.uniqueResult();
                log.info("maxFe:"+maxFe);
                log.info("Archivo:"+erpFeComprobantes.getXml());
                if(maxFe==null)
                    maxFe=0;
             erpFeComprobantes.getId().setNumero(maxFe+1);
            id = (ErpFeComprobantesId) session.save(erpFeComprobantes);
            transaccion.commit();

        } catch (HibernateException e) {
            log.error("Error save Comprobantes:"+erpFeComprobantes.getXml(), e);
            transaccion.rollback();
            return null;
        } finally {

            session.close();
        }
        return id;
    }

    @Override
    public ErpFeComprobantes findErpFeComprobantes(String compania, int numero) {

        ErpFeComprobantes erpFeComprobantes;
        Session session = sessionFactory.openSession();
        Transaction transaccion = session.beginTransaction();

        try {

            erpFeComprobantes = (ErpFeComprobantes) session.createQuery("from ErpFeComprobantes where id.compania = :compania and id.numero = :numero")
                    .setString("compania", compania)
                    .setInteger("numero", numero)
                    .setMaxResults(1)
                    .uniqueResult();

            transaccion.commit();
        } catch (HibernateException e) {
            log.error("Error find Comprobantes", e);
            transaccion.rollback();
            return null;
        } finally {

            session.close();

        }

        return erpFeComprobantes;
    }

    @Override
    public boolean delete(ErpFeComprobantes deleteErpFeComprobantes) {

        Session session = sessionFactory.openSession();
        Transaction transaccion = session.beginTransaction();
        try {

            session.delete(deleteErpFeComprobantes);
            transaccion.commit();

        } catch (HibernateException e) {

            log.error("Error delete Comprobantes", e);
            transaccion.rollback();
            return false;

        } finally {

            session.close();

        }
        return true;
    }

    @Override
    public boolean update(ErpFeComprobantes updateErpFeComprobantes) {

        Session session = sessionFactory.openSession();
        Transaction transaccion = session.beginTransaction();

        try {

            session.update(updateErpFeComprobantes);
            transaccion.commit();

        } catch (HibernateException e) {
            log.error("Error update Comprobantes", e);
            transaccion.rollback();
            return false;

        } finally {

            session.close();

        }
        return true;
    }

    @Override
    public int getMaxIdCampo(ErpFeComprobantesId id) {
        Session session = sessionFactory.openSession();

        try {
            DetachedCriteria maxSec = DetachedCriteria.forClass(ErpFeComprobantes.class);

            Criteria criteria = maxSec.getExecutableCriteria(session);
            criteria.add(Restrictions.eq("id.compania", id.getCompania()));
            criteria.setProjection(Projections.max("id.numero"));
            int campo ;
            List lista = criteria.list();
            if (lista.get(0) == null) {
                campo = 0;
            } else {
                campo = new Integer(lista.get(0).toString());
            }
            campo++;

            return campo;
        } catch (HibernateException e) {
            log.error("Error maxId Comprobantes h", e);
            return 0;
        } catch (NumberFormatException e) {
            log.error("Error maxId Comprobantes", e);
            return 0;
        } finally {
            session.close();
        }

    }

    @Override
    public String buscarFactura(String compania, String folio, String uuid) {
        List<ErpFeComprobantes> erpFeComprobantes;
        Session session = sessionFactory.openSession();
        Transaction transaccion = session.beginTransaction();

        log.info("compania" + compania + " folio" + folio + " uuid" + uuid);

        try {
            erpFeComprobantes = (List<ErpFeComprobantes>) session.createQuery("from ErpFeComprobantes where id.compania = :compania and folio = :folio and uuid = :uuid")
                    .setString("compania", compania)
                    .setString("folio", folio)
                    .setString("uuid", uuid)
                    // .setString("rfc", rfc)
                    .list();

            ErpFeComprobantes res = null;
            //session.getTransaction().commit();
            if (!erpFeComprobantes.isEmpty()) {
                res = (ErpFeComprobantes) erpFeComprobantes.get(0);
            } else {
                return null;
            }
            //return res.getValor();
            //System.out.println(respuestas);

            transaccion.commit();
            return res.getFolio();
        } catch (HibernateException e) {
            transaccion.rollback();
            return null;

        } catch (Exception ex) {
            log.error("Error buscarFactura Comprobantes:", ex);
            return null;
        } finally {
            session.close();
        }

        //return res;
    }

    @Override
    public ErpFeComprobantes buscarFactura2(String compania, String uuid) {
        List<ErpFeComprobantes> erpFeComprobantes;
        Session session = sessionFactory.openSession();
        Transaction transaccion = session.beginTransaction();

        try {
            erpFeComprobantes = (List<ErpFeComprobantes>) session.createQuery("from ErpFeComprobantes where id.compania = :compania and uuid = :uuid")
                    .setString("compania", compania)
                    .setString("uuid", uuid)
                    .list();

            ErpFeComprobantes res = null;
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
            log.error("Error de comprobantes busca2:", ex);
            return null;
        } finally {
            session.close();
        }

        //return res;
    }

    @Override
    public boolean precesaLista(List<ComprobanteDto> lista, String compania) {
        Session session = sessionFactory.openSession();
        Transaction transaccion = session.beginTransaction();

        try {
            Iterator<ComprobanteDto> i = lista.iterator();

            while (i.hasNext()) {
                ComprobanteDto com = i.next();
                String hql = "update ErpFeComprobantes set idConCGastos = :parconc where  compania = :compania and numero = :numero";

                Query query = session.createQuery(hql);
                query.setString("compania", compania);
                query.setString("parconc", com.IDCONCGASTO);
                query.setInteger("numero", new Integer(com.NUMERO));

//                System.out.println("com.IDCONCGASTO:" + com.IDCONCGASTO);
//                System.out.println("com.compania:" + compania);
//                System.out.println(" com.NUMERO:" + com.NUMERO);
                int rowCount = query.executeUpdate();
//                System.out.println("rowCount:" + rowCount);
            }
            transaccion.commit();

        } catch (HibernateException e) {
            log.error("Error de comprobantes procesa lista:", e);
            transaccion.rollback();
            return false;
        } finally {

            session.close();
        }

        return true;

    }
    
    @Override
    public boolean updateErpComprobantesDeduc(ErpFeComprobantes disp){
        
          Session session = sessionFactory.openSession();
        Transaction transaccion = session.beginTransaction();
        try {

            String hql = "update ErpFeComprobantes set DEDUCIBLE = '" + disp.getDeducible() + "', IMPORTE_DEDUCIBLE = " + disp.getImporteDeducible() + " where id.compania = :compania and id.numero = :numero";
            Query query = session.createQuery(hql);
            query.setString("compania", disp.getId().getCompania());
            query.setInteger("numero", disp.getId().getNumero());
            int rowCount = query.executeUpdate();
            //    System.out.println("Rows affected: " + rowCount);

            transaccion.commit();

        } catch (HibernateException e) {
            log.error("Error de comprobantes updateErpComprobantes h:", e);
            transaccion.rollback();
            return false;
        } catch (Exception e2) {
            log.error("Error de comprobantes updateErpComprobantes:", e2);
            transaccion.rollback();
        } finally {
            session.close();
        }
        return true;
    }

    @Override
    public boolean updateErpComprobantes(ErpFeComprobantes disp, String fecha) {
        Session session = sessionFactory.openSession();
        Transaction transaccion = session.beginTransaction();
        try {

            String hql = "update ErpFeComprobantes set CONCEPTO_CXP = '" + disp.getConceptoCxp() + "', CTO_CXP = '" + disp.getCtoCxp() + "' , ID_PROYECTO = '" + disp.getIdProyecto() + "', ID_PAIS_CXP = '" + disp.getIdPaisCXP()+ "', ID_TIPO_NEGOCIO = '" + disp.getIdTipoNegocio()+ "', ID_TIPO_GASTO = " + disp.getIdTipoGasto() + ", FECHA_VENC_CXP = :fecha , FECHA_CASH_FLOW = :fechaCashFlow  , FECHA_CONTAB_PROV = :fechaContab  where id.compania = :compania and id.numero = :numero";
            Query query = session.createQuery(hql);
            query.setString("compania", disp.getId().getCompania());
            query.setInteger("numero", disp.getId().getNumero());
            query.setDate("fecha", disp.getFechaVencCxp());
            query.setDate("fechaContab", disp.getFechaContabProv());
            query.setDate("fechaCashFlow", disp.getFechaCashFlow());
            
            int rowCount = query.executeUpdate();
            //    System.out.println("Rows affected: " + rowCount);

            transaccion.commit();

        } catch (HibernateException e) {
            log.error("Error de comprobantes updateErpComprobantes h:", e);
            transaccion.rollback();
            return false;
        } catch (Exception e2) {
            log.error("Error de comprobantes updateErpComprobantes:", e2);
            transaccion.rollback();
        } finally {
            session.close();
        }
        return true;
    }

    @Override
    public boolean updateSaldoCxp(ErpFeComprobantes disp) {
        Session session = sessionFactory.openSession();
        Transaction transaccion = session.beginTransaction();
        try {

            String hql = "update ErpFeComprobantes set SALDOS_CXP = " + disp.getSaldosCxp() + ", ESTATUS_CXP = '"+disp.getEstatusCxp()+"', PAGO_ACUMULADO_CXP = "+disp.getPagoAcumuladoCxp()+" where id.compania = :compania and id.numero = :numero";
            Query query = session.createQuery(hql);
            query.setString("compania", disp.getId().getCompania());
            query.setInteger("numero", disp.getId().getNumero());
            int rowCount = query.executeUpdate();
            //    System.out.println("Rows affected: " + rowCount);

            transaccion.commit();

        } catch (HibernateException e) {
            log.error("Error de comprobantes updateSaldoCxp h:", e);
            transaccion.rollback();
            return false;
        } catch (Exception e2) {
            log.error("Error de comprobantes updateSaldoCxp:", e2);
            transaccion.rollback();
        } finally {
            session.close();
        }
        return true;
    }

    @Override
    public boolean actualizaComprobancePoliza(PolizasId idPol, ErpFeComprobantes comp, String noCaso) {
        Session session = sessionFactory.openSession();
        Transaction transaccion = session.beginTransaction();

        try {

            String hql = "update ErpFeComprobantes set idConCGastos = :parconc where  compania = :compania and numero = :numero";

            Query query = session.createQuery(hql);
            query.setString("compania", idPol.getCompania());
            query.setString("parconc", noCaso);
            query.setInteger("numero", comp.getId().getNumero());

            int rowCount = query.executeUpdate();

            transaccion.commit();

        } catch (HibernateException e) {
            log.error("Error de comprobantes actualizaComprobancePoliza:", e);
            transaccion.rollback();
            return false;
        } finally {

            session.close();
        }

        return true;

    }

    @Override
    public boolean actualizaEstatus(ErpFeComprobantes comp) {
        Session session = sessionFactory.openSession();
        Transaction transaccion = session.beginTransaction();

        try {

            String hql = "update ErpFeComprobantes set estatusV = :estatusV where  compania = :compania and numero = :numero";

            Query query = session.createQuery(hql);
            query.setString("compania", comp.getId().getCompania());
            query.setString("estatusV", comp.getEstatusV());
            query.setInteger("numero", comp.getId().getNumero());

//                System.out.println("com.IDCONCGASTO:" + com.IDCONCGASTO);
//                System.out.println("com.compania:" + compania);
//                System.out.println(" com.NUMERO:" + com.NUMERO);
            int rowCount = query.executeUpdate();
//                System.out.println("rowCount:" + rowCount);

            transaccion.commit();

        } catch (HibernateException e) {
            log.error("Error de comprobantes actualizaEstatus:", e);
            transaccion.rollback();
            return false;
        } finally {

            session.close();
        }

        return true;

    }

    @Override
    public boolean actualizaEstatusFolioPagos(ErpFeComprobantes comp, String monedaPago) {
        Session session = sessionFactory.openSession();
        Transaction transaccion = session.beginTransaction();

        //System.out.println("actualizando pagos");
        try {

            String hql = "update ErpFeComprobantes set tipoPago =:tipoPago, totalAPagar =:totalAPagar, monedaPago =:monedaPago, estatusCxp = :estatusCxp, folioPagos = :folioPagos, referenciaCie =:referenciaCie where  id.compania = :compania and id.numero = :numero";

            Query query = session.createQuery(hql);
            query.setString("compania", comp.getId().getCompania());
            query.setString("estatusCxp", comp.getEstatusCxp());
            query.setInteger("folioPagos", comp.getFolioPagos());
            query.setInteger("numero", comp.getId().getNumero());
            query.setString("monedaPago", monedaPago);
            query.setString("tipoPago", comp.getTipoPago());
            query.setBigDecimal("totalAPagar", comp.getTotalAPagar());
            query.setString("referenciaCie", comp.getReferenciaCie());
            

//                System.out.println("com.IDCONCGASTO:" + com.IDCONCGASTO);
//                System.out.println("com.compania:" + compania);
//                System.out.println(" com.NUMERO:" + com.NUMERO);
            int rowCount = query.executeUpdate();
//                System.out.println("rowCount:" + rowCount);

            transaccion.commit();

        } catch (HibernateException e) {
            log.error("Error de comprobantes actualizaEstatusFolioPagos:", e);
            transaccion.rollback();
            return false;
        } finally {

            session.close();
        }

        return true;

    }
    
     @Override
    public boolean actualizaEstatusFolioPagosUuid(ErpFeComprobantes comp, String monedaPago) {
        Session session = sessionFactory.openSession();
        Transaction transaccion = session.beginTransaction();

        //System.out.println("actualizando pagos");
        try {

            String hql = "update ErpFeComprobantes set tipoPago =:tipoPago, totalAPagar =:totalAPagar, monedaPago =:monedaPago, estatusCxp = :estatusCxp, folioPagos = :folioPagos, referenciaCie =:referenciaCie where  id.compania = :compania and uuid = :uuid";

            Query query = session.createQuery(hql);
            query.setString("compania", comp.getId().getCompania());
            query.setString("estatusCxp", comp.getEstatusCxp());
            query.setInteger("folioPagos", comp.getFolioPagos());
            query.setInteger("numero", comp.getId().getNumero());
            query.setString("monedaPago", monedaPago);
            query.setString("tipoPago", comp.getTipoPago());
            query.setBigDecimal("totalAPagar", comp.getTotalAPagar());
            query.setString("referenciaCie", comp.getReferenciaCie());
            query.setString("uuid", comp.getUuid());
            

//                System.out.println("com.IDCONCGASTO:" + com.IDCONCGASTO);
//                System.out.println("com.compania:" + compania);
//                System.out.println(" com.NUMERO:" + com.NUMERO);
            int rowCount = query.executeUpdate();
//                System.out.println("rowCount:" + rowCount);

            transaccion.commit();

        } catch (HibernateException e) {
            log.error("Error de comprobantes actualizaEstatusFolioPagos:", e);
            transaccion.rollback();
            return false;
        } finally {

            session.close();
        }

        return true;

    }
    
        @Override
         public boolean actualizaEstatusFolioPagosReembolso(ErpFeComprobantes comp, String monedaPago) {
        Session session = sessionFactory.openSession();
        Transaction transaccion = session.beginTransaction();

        //System.out.println("actualizando pagos");
        try {

            String hql = "update ErpFeComprobantes set reembolsoClieprov =:reembolsoClieprov, monedaPago =:monedaPago, estatusCxp = :estatusCxp, folioPagos = :folioPagos where  id.compania = :compania and id.numero = :numero";

            Query query = session.createQuery(hql);
            query.setString("compania", comp.getId().getCompania());
            query.setString("estatusCxp", comp.getEstatusCxp());
            query.setInteger("folioPagos", comp.getFolioPagos());
            query.setInteger("numero", comp.getId().getNumero());
            query.setString("monedaPago", monedaPago);
            query.setString("reembolsoClieprov", comp.getReembolsoClieprov());

//                System.out.println("com.IDCONCGASTO:" + com.IDCONCGASTO);
//                System.out.println("com.compania:" + compania);
//                System.out.println(" com.NUMERO:" + com.NUMERO);
            int rowCount = query.executeUpdate();
//                System.out.println("rowCount:" + rowCount);

            transaccion.commit();

        } catch (HibernateException e) {
            log.error("Error de comprobantes actualizaEstatusFolioPagos:", e);
            transaccion.rollback();
            return false;
        } finally {

            session.close();
        }

        return true;

    }
    public boolean actualizaEstatusFolioPagosCie(ErpFeComprobantes comp, String monedaPago,BigDecimal pagoCie) {
        Session session = sessionFactory.openSession();
        Transaction transaccion = session.beginTransaction();

        //System.out.println("actualizando pagos");
        try {

            String hql = "update ErpFeComprobantes set tipoPago =:tipoPago, totalAPagar =:totalAPagar, conceptoCie =:conceptoCie, referenciaCie =:referenciaCie, pagoCie =:pagoCie, monedaPago =:monedaPago, estatusCxp = :estatusCxp, folioPagos = :folioPagos where  id.compania = :compania and id.numero = :numero";

            Query query = session.createQuery(hql);
            query.setString("compania", comp.getId().getCompania());
            query.setString("estatusCxp", comp.getEstatusCxp());
            query.setInteger("folioPagos", comp.getFolioPagos());
            query.setInteger("numero", comp.getId().getNumero());
            query.setString("monedaPago", monedaPago);
            query.setBigDecimal("pagoCie", pagoCie);
            query.setString("referenciaCie", comp.getReferenciaCie());
            query.setString("conceptoCie", comp.getConceptoCie());
            query.setString("tipoPago", comp.getTipoPago());
            query.setBigDecimal("totalAPagar", comp.getTotalAPagar());

//                System.out.println("com.IDCONCGASTO:" + com.IDCONCGASTO);
//                System.out.println("com.compania:" + compania);
//                System.out.println(" com.NUMERO:" + com.NUMERO);
            int rowCount = query.executeUpdate();
//                System.out.println("rowCount:" + rowCount);

            transaccion.commit();

        } catch (HibernateException e) {
            log.error("Error de comprobantes actualizaEstatusFolioPagos:", e);
            transaccion.rollback();
            return false;
        } finally {

            session.close();
        }

        return true;

        
    }

    @Override
    public boolean actualizaPago(String estatus, BigDecimal pago, ErpFeComprobantes comp) {
        Session session = sessionFactory.openSession();
        Transaction transaccion = session.beginTransaction();

        log.info("actualizando pagos");

        try {

            String hql = "update ErpFeComprobantes set ESTATUS_CXP = '" + estatus + "', PAGO_ACUMULADO_CXP = " + pago + " where  id.compania = :compania and id.numero = :numero";

            Query query = session.createQuery(hql);
            query.setString("compania", comp.getId().getCompania());
            query.setInteger("numero", comp.getId().getNumero());

//                System.out.println("com.IDCONCGASTO:" + com.IDCONCGASTO);
//                System.out.println("com.compania:" + compania);
//                System.out.println(" com.NUMERO:" + com.NUMERO);
            int rowCount = query.executeUpdate();
//                System.out.println("rowCount:" + rowCount);

            transaccion.commit();

        } catch (HibernateException e) {
            log.error("Error de comprobantes actualizaPago:", e);
            transaccion.rollback();
            return false;
        } finally {

            session.close();
        }

        return true;

    }
    
     
    public boolean eliminaRelacionTesoreria(String estatus, BigDecimal pago, ErpFeComprobantes comp) {
        Session session = sessionFactory.openSession();
        Transaction transaccion = session.beginTransaction();

        log.info("actualizando pagos");

        try {

            String hql = "update ErpFeComprobantes set ESTATUS_CXP = '" + estatus + "', FOLIO_PAGOS = " + pago + " where  id.compania = :compania and id.numero = :numero";

            Query query = session.createQuery(hql);
            query.setString("compania", comp.getId().getCompania());
            query.setInteger("numero", comp.getId().getNumero());

//                System.out.println("com.IDCONCGASTO:" + com.IDCONCGASTO);
//                System.out.println("com.compania:" + compania);
//                System.out.println(" com.NUMERO:" + com.NUMERO);
            int rowCount = query.executeUpdate();
//                System.out.println("rowCount:" + rowCount);

            transaccion.commit();

        } catch (HibernateException e) {
            log.error("Error de comprobantes actualizaPago:", e);
            transaccion.rollback();
            return false;
        } finally {

            session.close();
        }

        return true;

    }

        public boolean descripcionCancelacion(String descripcionCan, ErpFeComprobantes comp) {
        Session session = sessionFactory.openSession();
        Transaction transaccion = session.beginTransaction();

        log.info("actualizando pagos");

        try {

            String hql = "update ErpFeComprobantes set USUARIO_CANCELO = :usuarioCan , FECHA_CANCELACION = :fechaCance,  DESCRIPCION_CANCELACION = '" + descripcionCan + "' where  id.compania = :compania and id.numero = :numero";

            Query query = session.createQuery(hql);
            query.setString("compania", comp.getId().getCompania());
            query.setInteger("numero", comp.getId().getNumero());
            query.setTimestamp("fechaCance",new java.sql.Date(comp.getFechaCancelacion().getTime()));
            query.setString("usuarioCan", comp.getUsuarioCancelo());

            int rowCount = query.executeUpdate();

            transaccion.commit();

        } catch (HibernateException e) {
            log.error("Error de comprobantes actualizaPago:", e);
            transaccion.rollback();
            return false;
        } finally {

            session.close();
        }

        return true;

    }
        
        
      public boolean cancelaFactura(String estatus, ErpFeComprobantes comp) {
        Session session = sessionFactory.openSession();
        Transaction transaccion = session.beginTransaction();

        log.info("actualizando pagos");
          System.out.println("compania: "+comp.getId().getCompania());
          System.out.println("numero: "+comp.getId().getNumero());

        try {

            String hql = "update ErpFeComprobantes set ESTATUS_CXP = '" + estatus + "'  where  id.compania = :compania and id.numero = :numero";

            Query query = session.createQuery(hql);
            query.setString("compania", comp.getId().getCompania());
            query.setInteger("numero", comp.getId().getNumero());

//                System.out.println("com.IDCONCGASTO:" + com.IDCONCGASTO);
//                System.out.println("com.compania:" + compania);
//                System.out.println(" com.NUMERO:" + com.NUMERO);
            int rowCount = query.executeUpdate();
//                System.out.println("rowCount:" + rowCount);

            transaccion.commit();

        } catch (HibernateException e) {
            log.error("Error de comprobantes actualizaPago:", e);
            transaccion.rollback();
            return false;
        } finally {

            session.close();
        }

        return true;

    }
    
    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

}
