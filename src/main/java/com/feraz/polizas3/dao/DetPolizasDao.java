/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.feraz.polizas3.dao;

/**
 *
 * @author Feraz3
 */
import java.util.List;
import com.feraz.polizas3.model.DetPolizas;
import com.feraz.polizas3.model.DetPolizasId;

public interface DetPolizasDao {
    
    public DetPolizasId save(DetPolizas detPolizas);
    public boolean saveListDet(List<DetPolizas> detPolizas);
    public List<DetPolizas> findDetPolizas(String compania, String tipoPoliza,String fecha, String numero);
    public boolean delete (DetPolizas deletePolizas);
    public boolean update (DetPolizas updatePolzas);
    public boolean borraDetallePoliza(String compania, String tipoPoliza,String fecha, String numero);
    public int getMaxId(DetPolizasId id);
     public boolean updateCtaDetPolizas(String compania, String cuentaNueva, String cuentaVieja);
     public boolean actualizaTransaccion(String compania, String tipoPoliza,String fecha, String numero, int sec, String idTransaccion);
    
    
}
