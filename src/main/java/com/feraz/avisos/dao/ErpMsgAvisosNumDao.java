/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.feraz.avisos.dao;

import com.feraz.avisos.model.ErpMsgAvisosNum;
import com.feraz.avisos.model.ErpMsgAvisosNumId;

/**
 *
 * @author Feraz3
 */


/**
 *
 * @author Feraz3
 */
public interface ErpMsgAvisosNumDao {
    
     
      
       public Long FindNumAvisos(String compania, String usuario ,int sec);
       public ErpMsgAvisosNumId save(ErpMsgAvisosNum ErpMsgAvisosNum);

}
