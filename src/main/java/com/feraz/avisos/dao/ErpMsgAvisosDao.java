/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.feraz.avisos.dao;

import com.feraz.avisos.model.ErpMsgAvisos;
import java.util.List;

/**
 *
 * @author Feraz3
 */
public interface ErpMsgAvisosDao {
    
      public List<ErpMsgAvisos> FindErpAvisos(String compania,String pid);
      
      
    
}
