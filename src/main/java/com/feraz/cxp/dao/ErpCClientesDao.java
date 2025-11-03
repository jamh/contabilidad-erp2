/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.feraz.cxp.dao;

import java.util.List;
import com.feraz.cxp.model.ErpCClientes;
import com.feraz.cxp.model.ErpCClientesId;

/**
 *
 * @author JAMH
 */
public interface ErpCClientesDao {
    
     public ErpCClientesId save(ErpCClientes erpCClientes);

    public List<ErpCClientes> findErpCClientes(ErpCClientes clientes);

    public boolean delete(ErpCClientes deleteErpCClientes);

    public boolean update(ErpCClientes updateErpCClientes);
    
    public String getMaxErpCClientes(ErpCClientesId id);
    
    public ErpCClientes findProveedor(String compania,String rfc);
    public ErpCClientes findProveedor2(String compania,String rfc);
    
     public boolean updateDatosBancarios(ErpCClientes disp);
     public boolean updateDatosBancariosExt(ErpCClientes disp);
     
     public boolean updateVerific(ErpCClientes disp);
    
    
    
    
}
