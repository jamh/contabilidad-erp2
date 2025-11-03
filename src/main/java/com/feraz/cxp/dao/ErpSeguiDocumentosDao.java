/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.feraz.cxp.dao;

import java.util.List;
import com.feraz.cxp.model.ErpSeguiDocumentos;
import com.feraz.cxp.model.ErpSeguiDocumentosId;

/**
 *
 * @author Feraz3
 */
public interface ErpSeguiDocumentosDao {
    
    public ErpSeguiDocumentosId save(ErpSeguiDocumentos erpSeguiDocumentos);

    public List<ErpSeguiDocumentos> findErpSeguiDocumentos(ErpSeguiDocumentos documentos);

    public boolean delete(ErpSeguiDocumentos deleteErpSeguiDocumentos);

    public boolean update(ErpSeguiDocumentos updateErpSeguiDocumentos);
     public int getMaxId(ErpSeguiDocumentosId id);
    public boolean borraDetallePagos(ErpSeguiDocumentosId id);
    public boolean borraDetallePagosNotas(ErpSeguiDocumentos id);
   // public int getMaxIdErpSeguiDocumentos();
    
}
