/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.feraz.ordencompra.dao;

import com.feraz.ordencompra.model.ErpOrdenCompraDet;
import com.feraz.ordencompra.model.ErpOrdenCompraDetId;

/**
 *
 * @author vavi
 */
public interface ErpOrdenCompraDetDao {
    
     public ErpOrdenCompraDetId save(ErpOrdenCompraDet erpOrdenCompraDet);
       public ErpOrdenCompraDet findErpOrdenCompraDet(String compania, Integer idCompra, Integer linea );
       public ErpOrdenCompraDet findErpOrdenCompraDetWS(String compania, String idCompra, String linea);
       public boolean delete(ErpOrdenCompraDet deleteErpOrdenCompraDet);
       public boolean update(ErpOrdenCompraDet updateErpOrdenCompraDet);
       public boolean borraDetalleOrden(ErpOrdenCompraDet erpOrdenCompraDet);
       public int getMaxIdDetOrden(ErpOrdenCompraDetId id);
        public boolean actualizaImportes(ErpOrdenCompraDet comp);
        public boolean actualizaEstatus(ErpOrdenCompraDet comp);
    
}
