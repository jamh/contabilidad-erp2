/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.feraz.polizas3.dao.impl;

/**
 *
 * @author Feraz3
 */
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import com.feraz.polizas3.dao.FoliosPolizasDao;
import com.feraz.polizas3.model.FoliosPolizas;
import com.feraz.polizas3.model.FoliosPolizasId;
import java.util.List;
import org.hibernate.Query;


public class FoliosPolizasDaoImpl implements FoliosPolizasDao{
    
     private SessionFactory sessionFactory;


    public FoliosPolizasId save(FoliosPolizas foliosPolizas) {
        
        Session session = sessionFactory.openSession();
        Transaction transaccion = session.beginTransaction();
        FoliosPolizasId id = null;
        try{
            
            id = (FoliosPolizasId)session.save(foliosPolizas);
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
    
    
    

    public boolean buscaActualiza(String compania, int ano, int mes, String tipoPoliza) {
        
         System.out.println("Buscando Polizas....");
         
            List<FoliosPolizas> foliosPolizas;
           
             Session session = sessionFactory.openSession();
	        Transaction transaccion = session.beginTransaction();
	        try {
                    
                    
           
            
                    foliosPolizas = (List<FoliosPolizas>) session.createQuery("from FoliosPolizas where id.compania = :compania and id.ano = :ano and id.mes = :mes and id.tipoPoliza = :tipoPoliza")
                     .setString("compania", compania)                    
                     .setInteger("ano", ano) 
                     .setInteger("mes", mes) 
                     .setString("tipoPoliza", tipoPoliza)     
                    .list();
                   
                    FoliosPolizas folio = (FoliosPolizas) foliosPolizas.get(0);
                    
                    System.out.println(folio.getFolio());
                    if (foliosPolizas.get(0) == null) {
                        return false;
                     } else {
                    int incFolio = folio.getFolio() + 1;
                    String hql = "update FoliosPolizas set FOLIO = "+incFolio+" where  id.compania = :compania and id.ano = :ano and id.mes = :mes and id.tipoPoliza = :tipoPoliza";
                    
                    Query query = session.createQuery(hql);
                    query.setString("compania", compania) ;
                    query.setInteger("ano", ano);
                    query.setInteger("mes", mes);
                    query.setString("tipoPoliza", tipoPoliza) ;
                    int rowCount = query.executeUpdate();
                     
                    }
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
    
    

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }
    
    
    
}
