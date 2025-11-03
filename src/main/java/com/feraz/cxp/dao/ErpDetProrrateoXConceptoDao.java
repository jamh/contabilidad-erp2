/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.feraz.cxp.dao;

import com.feraz.cxp.model.ErpDetProrrateoXConcepto;
import com.feraz.cxp.model.ErpDetProrrateoXConceptoId;
import java.util.List;

/**
 *
 * @author vavi
 */
public interface ErpDetProrrateoXConceptoDao {
    
     public ErpDetProrrateoXConceptoId save(ErpDetProrrateoXConcepto erpDetProrrateoXConcepto);

    public List<ErpDetProrrateoXConcepto> findErpCClientes(ErpDetProrrateoXConcepto erpDetProrrateoXConcepto);

    public boolean delete(ErpDetProrrateoXConcepto deleteErpDetProrrateoXConcepto);

    public boolean update(ErpDetProrrateoXConcepto updateErpDetProrrateoXConcepto);
    
}
