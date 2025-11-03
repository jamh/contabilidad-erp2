/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.feraz.polizas3.dao;

/**
 *
 * @author Feraz3
 */

import java.util.List;
import com.feraz.polizas3.model.LogPolizas;
import com.feraz.polizas3.model.LogPolizasId;
public interface LogPolizasDao {
    
     public LogPolizasId save(LogPolizas logPolizas);
    
}
