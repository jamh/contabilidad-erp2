/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.feraz.facturas.webcontrolfe.dao1.impl;

import com.feraz.facturas.webcontrolfe.dao1.ErpFeXMLDao;
import com.feraz.facturas.webcontrolfe.model.ErpFeXML;
import java.util.Iterator;
import java.util.List;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

/**
 *
 * @author Ing. JAMH
 */
public class ErpFeXMLDaoImpl implements ErpFeXMLDao{
    
    private SessionFactory sessionFactory;

    public boolean saveLista(List<ErpFeXML> listaXml, String compania) {
        
        Session session = sessionFactory.openSession();
        Transaction transaccion = session.beginTransaction();
        String id = null;
        try{
         Iterator<ErpFeXML> i = listaXml.iterator();
         
         while (i.hasNext()) {
             
             ErpFeXML dat =i.next();
//             System.out.println("lista:"+dat.getXml());
            id = (String)session.save(dat);
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

    public boolean deleteAll(String compania) {
        
          Session session = sessionFactory.openSession();
        Transaction transaccion = session.beginTransaction();
        String id = null;
        try{
          String hql = "Delete ErpFeXML where  compania = :compania";

                Query query = session.createQuery(hql);
                query.setString("compania", compania);
       
                int delete = query.executeUpdate();
                System.out.println("delete:"+delete);
                 transaccion.commit();
              if(delete <0) 
                  return false;
              else return true;
              
            
           
          
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
    
    
    
}
