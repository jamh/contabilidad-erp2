/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.feraz.cxp.dao;

import com.feraz.cxp.model.ErpCClientes;
import com.feraz.cxp.model.ErpCClientesId;
import com.feraz.cxp.model.ErpClientes;
import com.feraz.cxp.model.ErpClientesId;
import java.util.List;

/**
 *
 * @author 55555
 */
public interface ErpClientesDao {
    
    public ErpClientesId save(ErpClientes erpClientes);

    public List<ErpClientes> findErpClientes(ErpClientes clientes);

    public boolean delete(ErpClientes deleteErpClientes);

    public boolean update(ErpClientes updateErpClientes);
    
    public String getMaxErpClientes(ErpClientesId id);
    
    public ErpClientes findCliente(String compania,String rfc);
    
}
