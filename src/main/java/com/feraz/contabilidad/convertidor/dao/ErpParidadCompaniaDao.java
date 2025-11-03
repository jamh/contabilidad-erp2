/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.feraz.contabilidad.convertidor.dao;

import com.feraz.contabilidad.convertidor.model.ErpParidadCompania;
import com.feraz.contabilidad.convertidor.model.ErpParidadCompaniaId;
import java.util.Date;
import java.util.List;

/**
 *
 * @author 55555
 */
public interface ErpParidadCompaniaDao {
    
     public ErpParidadCompaniaId save(ErpParidadCompania erpParidadCompania);

    public List<ErpParidadCompania> findErpParidad();

    public boolean delete(ErpParidadCompania deleteErpParidad);

    public boolean update(ErpParidadCompania updateErpParidad);
    
     public ErpParidadCompania buscaUltimaParidad(String divisaOrigen,String divisaDestino,Date fecha);
    
}
