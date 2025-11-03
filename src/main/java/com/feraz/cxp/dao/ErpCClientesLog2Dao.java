/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.feraz.cxp.dao;
import com.feraz.cxp.model.ErpCClientesLog2;
import com.feraz.cxp.model.ErpCClientesLog2Id;


/**ErpCClientesLog2 erpCClientesLog2
 *
 * @author lbadi
 */
public interface ErpCClientesLog2Dao {
    public ErpCClientesLog2Id save(ErpCClientesLog2 erpCClientesLog2);
    
    public int getMaxErpCClientesLog2Id(ErpCClientesLog2Id id);
    
}
