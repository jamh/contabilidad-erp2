/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.feraz.polizas3.dao.impl;

import com.feraz.polizas3.dao.FoliosPolizasDao;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import com.feraz.polizas3.dao.PolizasDao;
import com.feraz.polizas3.model.PolizasDTOUpdate;
import com.feraz.polizas3.model.FoliosPolizas;
import com.feraz.polizas3.model.FoliosPolizasId;
import com.feraz.polizas3.model.Polizas;
import com.feraz.polizas3.model.PolizasId;
import java.math.BigDecimal;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;
import org.hibernate.Query;
import org.jamh.data.process.ProcessDao;

public class PolizasDaoImpl implements PolizasDao {

    private SessionFactory sessionFactory;
    private FoliosPolizasDao foliosPolizasDao;
    private ProcessDao processDao;

    public PolizasId save(Polizas polizas) {

        Session session = sessionFactory.openSession();
        Transaction transaccion = session.beginTransaction();
        PolizasId id = null;
        List<FoliosPolizas> foliosPolizas;
        try {
            
            System.out.println("llegando a inssert");
            Calendar cal = Calendar.getInstance();
//            System.out.println("fecha"+polizas.getId().getFecha());
            cal.setTime(polizas.getId().getFecha());
            int year = cal.get(Calendar.YEAR);
            int month = cal.get(Calendar.MONTH);
            System.out.println(polizas.getId().getCompania());
            System.out.println(year);
            System.out.println(month);
            System.out.println(polizas.getId().getTipoPoliza());
            foliosPolizas = (List<FoliosPolizas>) session.createQuery("from FoliosPolizas where id.compania = :compania and id.ano = :ano and id.mes = :mes and id.tipoPoliza = :tipoPoliza")
                    .setString("compania", polizas.getId().getCompania())
                    .setInteger("ano", year)
                    .setInteger("mes", month+1)
                    .setString("tipoPoliza", polizas.getId().getTipoPoliza())
                    .list();
            
            System.out.println("folioid2FOLIO:"+foliosPolizas.isEmpty());
              Map tiposPolizas = new HashMap();
               
                 tiposPolizas.put("compania", polizas.getId().getCompania());
                 tiposPolizas.put("tipoPoliza",polizas.getId().getTipoPoliza());
               
                   List listNumeracion = processDao.getMapResult("BuscaNumeracionTipoPoliza", tiposPolizas);
                   
                    Map numeracion = (HashMap) listNumeracion.get(0);
             if (foliosPolizas.isEmpty()){
                 
                    
           
 
                 
                  FoliosPolizasId folioid = new FoliosPolizasId();
                  folioid.setAno(year);
                  folioid.setMes(month+1);
                  folioid.setCompania(polizas.getId().getCompania());
                  folioid.setTipoPoliza( polizas.getId().getTipoPoliza());
                  FoliosPolizas folios2 = new FoliosPolizas(folioid);
                  folios2.setId(folioid);
                  
                  if (numeracion.get("NUMERACION").toString().equalsIgnoreCase("2")){
                      
                      
                      
                  if (month + 1 == 1){
                       folios2.setFolio(0);
                  }else{   
                  List<FoliosPolizas>  foliosPolizas2 = (List<FoliosPolizas>) session.createQuery("from FoliosPolizas where id.compania = :compania and id.ano = :ano and id.mes = :mes and id.tipoPoliza = :tipoPoliza")
                    .setString("compania", polizas.getId().getCompania())
                    .setInteger("ano", year)
                    .setInteger("mes", month)
                    .setString("tipoPoliza", polizas.getId().getTipoPoliza())
                    .list();
                     
                    if (foliosPolizas2 == null || foliosPolizas2.isEmpty()){
                        folios2.setFolio(Integer.parseInt(numeracion.get("FOLIO_INI").toString()));
                    }else{
                  
                   FoliosPolizas folio2 = (FoliosPolizas) foliosPolizas2.get(0);
                   
                       
                     

                   ///
                   
                   folios2.setFolio(folio2.getFolio()); 
                  }
                  }
                  }else{
                  
                  folios2.setFolio(0);
                  }
                  FoliosPolizasId folioid2 =foliosPolizasDao.save(folios2);
                  System.out.println("folioid2Poliza:"+folioid2);
                 if(folioid2==null){
                     return null;
                 }
                 foliosPolizas = (List<FoliosPolizas>) session.createQuery("from FoliosPolizas where id.compania = :compania and id.ano = :ano and id.mes = :mes and id.tipoPoliza = :tipoPoliza")
                    .setString("compania", polizas.getId().getCompania())
                    .setInteger("ano", year)
                    .setInteger("mes", month+1)
                    .setString("tipoPoliza", polizas.getId().getTipoPoliza())
                    .list();
             }
            FoliosPolizas folio = (FoliosPolizas) foliosPolizas.get(0);
            
            System.out.println("FOLIO"+foliosPolizas.get(0));

            int incFolio = folio.getFolio() + 1;
            String folioDa = String.format("%010d", incFolio);
            polizas.getId().setNumero(folioDa);
            System.out.println(folioDa);
            if (numeracion.get("NUMERACION").toString().equalsIgnoreCase("2")){
              Map verificaFolio = new HashMap();
               
                        verificaFolio.put("compania", polizas.getId().getCompania());
                        verificaFolio.put("tipoPoliza",polizas.getId().getTipoPoliza());
                        verificaFolio.put("numero",folioDa);

                          List listNumeracionVerifica = processDao.getMapResult("ValidaFolioAnual", verificaFolio);

                           if (listNumeracionVerifica == null || listNumeracionVerifica.isEmpty()){
                           
                           }else{
                           
                              return null;
                           }
            
            }
            id = (PolizasId) session.save(polizas);
            System.out.println("NUMERO_POL"+id.getNumero());
            if (foliosPolizas.get(0) == null) {
                return null;
            } else {

                String hql = "update FoliosPolizas set FOLIO = " + incFolio + " where  id.compania = :compania and id.ano = :ano and id.mes = :mes and id.tipoPoliza = :tipoPoliza";

                Query query = session.createQuery(hql);
                query.setString("compania", polizas.getId().getCompania());
                query.setInteger("ano", year);
                query.setInteger("mes", month+1);
                query.setString("tipoPoliza", polizas.getId().getTipoPoliza());
                
                System.out.println("query"+query);
                int rowCount = query.executeUpdate();
            }

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
    
//    public boolean actualizaTotalesPolizas(PolizasId id,BigDecimal cargos,BigDecimal Abonos){
//        
//          Session session = sessionFactory.openSession();
//        Transaction transaccion = session.beginTransaction();
////        PolizasId id = null;
////        List<FoliosPolizas> foliosPolizas;
//        try {
//                
//            String hql = "update Polizas set FOLIO = :folio where  id.compania = :compania  and id.tipo_poliza and id.numero=";
//
//                Query query = session.createQuery(hql);
//                query.setString("compania", id.getCompania());
//                query.setInteger("ano", year);
//                query.setInteger("mes", month+1);
//                query.setString("tipoPoliza", polizas.getId().getTipoPoliza());
//                int rowCount = query.executeUpdate();
//            
//
//            transaccion.commit();
//
//        } catch (HibernateException e) {
//            e.printStackTrace();
//            transaccion.rollback();
//            return false;
//
//        } finally {
//
//            session.close();
//
//        }
//    }

    public List<Polizas> FindPolizas() {

        List<Polizas> polizas;
        Session session = sessionFactory.openSession();
        Transaction transaccion = session.beginTransaction();

        try {

            polizas = (List<Polizas>) session.createQuery("from Polizas").list();
            transaccion.commit();
        } catch (HibernateException e) {
            transaccion.rollback();
            return null;
        } finally {

            session.close();

        }

        return polizas;

    }

    public boolean delete(Polizas deletePolizas) {

        Session session = sessionFactory.openSession();
        Transaction transaccion = session.beginTransaction();

        try {

            session.delete(deletePolizas);
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

    public boolean update(PolizasDTOUpdate updatePolizas) {

        Session session = sessionFactory.openSession();
        Transaction transaccion = session.beginTransaction();

        try {

            session.update(updatePolizas);
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
    
     public boolean update2(Polizas updatePolizas) {

        Session session = sessionFactory.openSession();
        Transaction transaccion = session.beginTransaction();

        try {

            session.update(updatePolizas);
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
     
      public boolean guardaTotales(PolizasId id,BigDecimal cargos, BigDecimal abonos,String estatus) {
        
        Session session = sessionFactory.openSession();
        Transaction transaccion = session.beginTransaction();
//        PolizasDTO dto = new PolizasDTO();
//        DetPolizasId id = null;
        try{
            
            
    
                String hql = "update Polizas set cargos = :cargos, cargosBase= :cargos , abonosBase = :abonos ,abonos = :abonos, estatus = :estatus where  id.compania = :compania and id.numero = :numero and fecha=:fecha and tipo_poliza = :tipo";
                Query query = session.createQuery(hql);
                query.setString("compania", id.getCompania());
                query.setDate("fecha", id.getFecha());
                query.setString("numero", id.getNumero());
                 query.setString("estatus", estatus);
                 query.setString("tipo", id.getTipoPoliza());
                query.setBigDecimal("cargos", cargos);
//                query.setBigDecimal("cargos_base", cargos);
                query.setBigDecimal("abonos", abonos);
//                 query.setBigDecimal("abonos_base", abonos);

//                System.out.println("com.IDCONCGASTO:" + com.IDCONCGASTO);
//                System.out.println("com.compania:" + compania);
//                System.out.println(" com.NUMERO:" + com.NUMERO);

                int rowCount = query.executeUpdate();
               

            transaccion.commit();
            
             return true;
            
        }catch (HibernateException e){
            e.printStackTrace();
            transaccion.rollback();
            return false;
        }finally{
            
            session.close();
        }
       
        
    }

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public void setFoliosPolizasDao(FoliosPolizasDao foliosPolizasDao) {
        this.foliosPolizasDao = foliosPolizasDao;
    }


    public PolizasId save2(Polizas polizas) {
        
        System.out.println("save 2");
        Session session = sessionFactory.openSession();
        Transaction transaccion = session.beginTransaction();
        PolizasId id = null;
        try{
            
            id = (PolizasId)session.save(polizas);
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

    public void setProcessDao(ProcessDao processDao) {
        this.processDao = processDao;
    }
    
    

}
