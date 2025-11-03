/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.feraz.polizas3.dao;

import com.feraz.polizas3.model.ErpPolizasXPagos;
import com.feraz.polizas3.model.ErpPolizasXPagosId;
import java.util.Date;

/**
 *
 * @author 55555
 */
public interface ErpPolizasXPagosDao {
    
    public ErpPolizasXPagosId save(ErpPolizasXPagos erpPolizasXFacturas);
     public boolean deleteRelacion(ErpPolizasXPagos erpPolizasXPagos);
    public ErpPolizasXPagos buscaPoliza(String compania, String numero, String tipo, Date fecha);
    public boolean delete(ErpPolizasXPagos erpPolizasXPagos);
}
