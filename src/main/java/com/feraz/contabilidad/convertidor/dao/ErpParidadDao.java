/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.feraz.contabilidad.convertidor.dao;

import com.feraz.contabilidad.convertidor.model.ErpParidad;
import com.feraz.contabilidad.convertidor.model.ErpParidadId;
import java.util.Date;
import java.util.List;

/**
 *
 * @author Ing. JAMH
 */
public interface ErpParidadDao {

    public ErpParidadId save(ErpParidad erpParidad);

    public List<ErpParidad> findErpParidad();

    public boolean delete(ErpParidad deleteErpParidad);

    public boolean update(ErpParidad updateErpParidad);

    public ErpParidad buscaUltimaParidad(String divisaOrigen,String divisaDestino,Date fecha);
}
