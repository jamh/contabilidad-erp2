/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.feraz.cxp.dao;

import com.feraz.cxp.model.ErpDetProrrateoXNegocio;
import com.feraz.cxp.model.ErpDetProrrateoXNegocioId;
import java.util.List;

/**
 *
 * @author LENOVO
 */
public interface ErpDetProrrateoXNegocioDao {
    
         public ErpDetProrrateoXNegocioId save(ErpDetProrrateoXNegocio erpDetProrrateoXNegocio);

    public List<ErpDetProrrateoXNegocio> findErpCClientes(ErpDetProrrateoXNegocio erpDetProrrateoXNegocio);

    public boolean delete(ErpDetProrrateoXNegocio deleteErpDetProrrateoXNegocio);

    public boolean update(ErpDetProrrateoXNegocio updateErpDetProrrateoXNegocio);
    
}
