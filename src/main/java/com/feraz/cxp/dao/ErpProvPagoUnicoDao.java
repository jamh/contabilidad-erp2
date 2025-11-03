/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.feraz.cxp.dao;

import com.feraz.cxp.model.ErpProvPagoUnico;
import com.feraz.cxp.model.ErpProvPagoUnicoId;
import java.util.List;

/**
 *
 * @author vavi
 */
public interface ErpProvPagoUnicoDao {
    
    public ErpProvPagoUnicoId save(ErpProvPagoUnico erpProvPagoUnico);

    public List<ErpProvPagoUnico> findErpCClientes(ErpProvPagoUnicoId erpProvPagoUnico);

    public boolean delete(ErpProvPagoUnico erpProvPagoUnico);

    public boolean update(ErpProvPagoUnico erpProvPagoUnico);
    
    public int getMaxErpProvPagoUnicoId(ErpProvPagoUnicoId id);
    public boolean updateErpProvPagoUnicoEstatusPagos(ErpProvPagoUnico disp);
    
}
