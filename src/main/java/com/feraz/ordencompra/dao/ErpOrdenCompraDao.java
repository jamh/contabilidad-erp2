/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.feraz.ordencompra.dao;

import com.feraz.ordencompra.model.ErpOrdenCompra;
import com.feraz.ordencompra.model.ErpOrdenCompraId;

/**
 *
 * @author vavi
 */
public interface ErpOrdenCompraDao {
    
       public ErpOrdenCompraId save(ErpOrdenCompra erpWsIntercos);
       public ErpOrdenCompra findErpOrdenCompra(String compania, String id);
       public ErpOrdenCompra findErpOrdenCompraWS(String compania, String id);
       public boolean delete(ErpOrdenCompra deleteErpOrdenCompra);
       public boolean update(ErpOrdenCompra updateErpOrdenCompra);
        public int getMaxIdOrden(ErpOrdenCompraId id);
        public boolean actualizaEstatusGasto(ErpOrdenCompra comp);
        public boolean actualizaEstatus(ErpOrdenCompra comp);
        public boolean actualizaImportes(ErpOrdenCompra comp);
        public boolean actualizaEstatusPr(ErpOrdenCompra comp,String usuario,String estatus);
        public boolean actualizaEstatusSg(ErpOrdenCompra comp,String usuario,String estatus);
    
    
    
}
