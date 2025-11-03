/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.feraz.contabilidad.sat.transacciones.dao;

import com.feraz.contabilidad.sat.transacciones.model.DetPolizasXTransaccion;
import com.feraz.contabilidad.sat.transacciones.model.DetPolizasXTransaccionId;
import java.util.List;

/**
 *
 * @author 55555
 */
public interface DetPolizasXTransaccionDao {
    
     public DetPolizasXTransaccionId save(DetPolizasXTransaccion transacciones);
   
    public List<DetPolizasXTransaccion> findErpSatTransaccion(String compania);
    public boolean delete (DetPolizasXTransaccion deleteDetPolizasXTransaccion);
    public boolean update (DetPolizasXTransaccion updateDetPolizasXTransaccion);
    public boolean borraIdTransaccion (String compania, String id_transaccion);
    public boolean borraTransaccionXPoliza(DetPolizasXTransaccion detPolizasXTransaccion);
    public boolean borraTransaccionXPolizaCompl(DetPolizasXTransaccion detPolizasXTransaccion);
    public boolean borraTransaccionXPolizaCompl2(DetPolizasXTransaccion detPolizasXTransaccion,String fecha);
    
}
