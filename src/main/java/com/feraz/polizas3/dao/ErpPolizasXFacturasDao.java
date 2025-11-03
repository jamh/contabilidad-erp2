/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.feraz.polizas3.dao;

import com.feraz.polizas3.model.ErpPolizasXFacturas;
import com.feraz.polizas3.model.ErpPolizasXFacturasId;
import java.util.Date;

/**
 *
 * @author Ing. JAMH
 */
public interface ErpPolizasXFacturasDao {

    public ErpPolizasXFacturasId save(ErpPolizasXFacturas erpPolizasXFacturas);
    
    public boolean deleteRelacion(ErpPolizasXFacturas erpPolizasXFacturas);

    public boolean delete(ErpPolizasXFacturas deleteErpPolizasXFacturas);

    public boolean update(ErpPolizasXFacturas updateErpPolizasXFacturas);

    public ErpPolizasXFacturas buscaNumero(String compania, int numero);

    public ErpPolizasXFacturas buscaUUID(String compania, String uuid);

    public ErpPolizasXFacturas buscaPoliza(String compania, String numero, String tipo, Date fecha);
    
    public boolean deleteRelacionPorCancelacion(String compania, String tipoPoliza,String fecha, String numero) ;
}
