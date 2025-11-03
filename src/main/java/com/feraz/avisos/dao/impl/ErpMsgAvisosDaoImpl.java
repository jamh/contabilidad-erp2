/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.feraz.avisos.dao.impl;

/**
 *
 * @author Feraz3
 */
import com.feraz.avisos.model.ErpMsgAvisos;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import com.feraz.avisos.dao.ErpMsgAvisosDao;
import java.util.List;
import org.hibernate.Query;

public class ErpMsgAvisosDaoImpl implements ErpMsgAvisosDao{
    
    private SessionFactory sessionFactory;

    @Override
    public List<ErpMsgAvisos> FindErpAvisos(String compania, String usuario) {
        
        List<ErpMsgAvisos> erpMsgAvisos;
        Session session = sessionFactory.openSession();
        Transaction transaccion = session.beginTransaction();
             System.out.println("Compania:"+ compania);
             System.out.println("pid:"+ usuario);
        try {

            erpMsgAvisos = (List<ErpMsgAvisos>) session.createQuery("from ErpMsgAvisos where id.compania  = :compania and id.usuario = :usuario")
             .setString("compania", compania)
             .setString("usuario", usuario).list();
            transaccion.commit();
            
        } catch (HibernateException e) {
            e.printStackTrace();
            transaccion.rollback();
            return null;
        } finally {

            session.close();

        }

        return erpMsgAvisos;
        
    }
    
   
    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }
    
    
}
