
package com.feraz.polizas3.dao.impl;

/**
 *
 * @author Ing. JAMH
 */

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Projections;
import com.feraz.polizas3.dao.DetPolizasDao;
import com.feraz.polizas3.model.DetPolizas;
import com.feraz.polizas3.model.DetPolizasId;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import org.hibernate.Query;
import org.hibernate.criterion.Restrictions;

public class DetPolizasDaoImpl implements DetPolizasDao{
    
    private SessionFactory sessionFactory;

    public DetPolizasId save(DetPolizas detPolizas) {
        
        Session session = sessionFactory.openSession();
        Transaction transaccion = session.beginTransaction();
        DetPolizasId id = null;
        try{
            
            id = (DetPolizasId)session.save(detPolizas);
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
    

    
     public boolean saveListDet(List<DetPolizas> detPolizas){
          Session session = sessionFactory.openSession();
        Transaction transaccion = session.beginTransaction();
//        DetPolizasId id = null;
        try{
            Iterator<DetPolizas>  it = detPolizas.iterator();
            while(it.hasNext()){
                DetPolizas det = it.next();
              DetPolizasId id  = (DetPolizasId)session.save(det);
              if(id ==null) return false;
              
            }
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

    public List<DetPolizas> findDetPolizas(String compania, String tipoPoliza,String fecha, String numero) {
        List<DetPolizas> detPolizas;
        Session session = sessionFactory.openSession();
        Transaction transaccion = session.beginTransaction();
        
        try{
            
            detPolizas = (List<DetPolizas>)session.createQuery("from DetPolizas where "
                    + "id.compania = :compania "
                    + "and id.tipoPoliza = :tipoPoliza "
                    + "and id.fecha = :fecha "
                    + "and id.numero = :numero")
                        .setString("compania",compania)
        .setString("tipoPoliza",tipoPoliza)
        .setString("fecha",fecha)
        .setString("numero",numero)
                    .list();
            transaccion.commit();
            
        }catch (HibernateException e){
            e.printStackTrace();
            transaccion.rollback();
            return null;
        }finally{
            
            session.close();
           
        }
        return detPolizas;
                
    }

    public boolean delete(DetPolizas deletePolizas) {
        System.out.println("Borrando detalle");
        Session session = sessionFactory.openSession();
        Transaction transaccion = session.beginTransaction();
        try{
            System.out.println("Borrando detalle");
            session.delete(deletePolizas);
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

    public boolean update(DetPolizas updatePolzas) {
        
        Session session = sessionFactory.openSession();
        Transaction transaccion = session.beginTransaction();
        
        try{
            
            session.update(updatePolzas);
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
    
    public boolean borraDetallePoliza(String compania, String tipoPoliza,String fecha, String numero) {
          Session session = sessionFactory.openSession();
        Transaction transaccion = session.beginTransaction();
        try {
     
             SimpleDateFormat formatoDelTexto2 = new SimpleDateFormat("dd/MM/yyyy");
                   String strFechaCap = fecha;
                    Date fechaCap = null;
                 //Obtener maxima secuencia
                    fechaCap = formatoDelTexto2.parse(strFechaCap);
            
             String hql = "delete from DetPolizas where id.compania = :compania and id.tipoPoliza = :tipoPoliza and id.fecha = :fecha and id.numero = :numero";
        Query query = session.createQuery(hql);
        query.setString("compania",compania);
        query.setString("tipoPoliza",tipoPoliza);
        query.setDate("fecha",fechaCap);
        query.setString("numero",numero);
        int rowCount = query.executeUpdate();
    //    System.out.println("Rows affected: " + rowCount);

            transaccion.commit();

        } catch (HibernateException e) {
            e.printStackTrace();
            transaccion.rollback();
            return false;
        }catch(Exception e2){
            e2.printStackTrace();
            transaccion.rollback();
        }
            finally {
            session.close();
        }
        return true;
    }

    
    public int getMaxId(DetPolizasId id) {
        Session session = sessionFactory.openSession();
        try {
            DetachedCriteria maxSec = DetachedCriteria.forClass(DetPolizas.class);


            Criteria criteria = maxSec.getExecutableCriteria(session);
            criteria.add(Restrictions.eq("id.compania", id.getCompania()));
            criteria.add(Restrictions.eq("id.tipoPoliza", id.getTipoPoliza()));
            criteria.add(Restrictions.eq("id.fecha", id.getFecha()));
            criteria.add(Restrictions.eq("id.numero", id.getNumero()));
            criteria.setProjection(Projections.max("id.sec"));
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
    
     public boolean updateCtaDetPolizas(String compania, String cuentaNueva, String cuentaVieja) {
        Session session = sessionFactory.openSession();
        Transaction transaccion = session.beginTransaction();
        try {

            String hql = "update DetPolizas set CUENTA = '" + cuentaNueva + "' where id.compania = :compania and cuenta = :cuenta";
            Query query = session.createQuery(hql);
            query.setString("compania", compania);
            query.setString("cuenta", cuentaVieja);
            
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
     
      public boolean actualizaTransaccion(String compania, String tipoPoliza,String fecha, String numero, int sec, String idTransaccion) {
          Session session = sessionFactory.openSession();
        Transaction transaccion = session.beginTransaction();
        try {
     
             SimpleDateFormat formatoDelTexto2 = new SimpleDateFormat("dd/MM/yyyy");
                   String strFechaCap = fecha;
                    Date fechaCap = null;
                 //Obtener maxima secuencia
                    fechaCap = formatoDelTexto2.parse(strFechaCap);
            
             String hql = "update DetPolizas set ID_TRANSACCION = '" + idTransaccion + "' where id.compania = :compania and id.tipoPoliza = :tipoPoliza and id.fecha = :fecha and id.numero = :numero and id.sec = :sec";
        Query query = session.createQuery(hql);
        query.setString("compania",compania);
        query.setString("tipoPoliza",tipoPoliza);
        query.setDate("fecha",fechaCap);
        query.setString("numero",numero);
        query.setInteger("sec",sec);
        int rowCount = query.executeUpdate();
    //    System.out.println("Rows affected: " + rowCount);

            transaccion.commit();

        } catch (HibernateException e) {
            e.printStackTrace();
            transaccion.rollback();
            return false;
        }catch(Exception e2){
            e2.printStackTrace();
            transaccion.rollback();
        }
            finally {
            session.close();
        }
        return true;
    }

     

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }
    
    
    
}
