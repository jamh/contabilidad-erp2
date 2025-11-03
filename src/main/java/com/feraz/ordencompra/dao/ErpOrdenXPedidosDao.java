/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.feraz.ordencompra.dao;

import com.feraz.ordencompra.model.ErpOrdenXPedidos;
import com.feraz.ordencompra.model.ErpOrdenXPedidosId;

/**
 *
 * @author vavi
 */
public interface ErpOrdenXPedidosDao {
    
      public ErpOrdenXPedidosId save(ErpOrdenXPedidos erpOrdenXPedidos);
       public boolean delete(ErpOrdenXPedidos deleteErpOrdenXPedidos);
       
       public boolean borraPorLinea(ErpOrdenXPedidos deleteErpOrdenXPedidos);
       public boolean borraPorOrden(ErpOrdenXPedidos comp);
    
}
