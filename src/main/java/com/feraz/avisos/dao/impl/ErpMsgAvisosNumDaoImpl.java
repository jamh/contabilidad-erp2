/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.feraz.avisos.dao.impl;
import com.feraz.avisos.model.ErpMsgAvisos;
import com.feraz.avisos.model.ErpMsgAvisosNum;
import com.feraz.avisos.model.ErpMsgAvisosNumId;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import com.feraz.avisos.dao.ErpMsgAvisosNumDao;
import java.util.List;
import org.hibernate.Query;

/**
 *
 * @author Feraz3
 */
public class ErpMsgAvisosNumDaoImpl implements ErpMsgAvisosNumDao{
 private SessionFactory sessionFactory;
    @Override
       public Long FindNumAvisos(String compania, String usuario, int sec) {
     Session session = sessionFactory.openSession();
        Transaction transaccion = session.beginTransaction();
        
         try {

           Query query = session.createQuery(
            "select count(*) from ErpMsgAvisosNum  where id.compania=:compania and id.usuario=:usuario and id.sec=:sec");
            query.setString("compania", compania);
            query.setString("usuario", usuario);
            query.setInteger("sec", sec);
            Long count = (Long)query.uniqueResult();
            transaccion.commit();
            return count;
        } catch (HibernateException e) {
            e.printStackTrace();
            transaccion.rollback();
            return new Long(0);
        } finally {

            session.close();

        }
        

    }
    
     public ErpMsgAvisosNumId save(ErpMsgAvisosNum ErpMsgAvisosNum) {
        
         Session session = sessionFactory.openSession();
        Transaction transaccion = session.beginTransaction();
        ErpMsgAvisosNumId id = null;
        try{
            
            id = (ErpMsgAvisosNumId)session.save(ErpMsgAvisosNum);
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

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }
    
}
