/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.feraz.polizas3.dao;

/**
 *
 * @author Feraz3
 */
import com.feraz.polizas3.model.PolizasDTOUpdate;
import com.feraz.polizas3.model.Polizas;
import com.feraz.polizas3.model.PolizasId;
import java.math.BigDecimal;
import java.util.List;

public interface PolizasDao {

    public PolizasId save(Polizas polizas);
    
    public PolizasId save2(Polizas polizas);

    public List<Polizas> FindPolizas();

    public boolean delete(Polizas deletePolizas);

    public boolean update(PolizasDTOUpdate updatePolizas);

    public boolean update2(Polizas updatePolizas);
    
     public boolean guardaTotales(PolizasId id,BigDecimal cargos, BigDecimal abonos,String estatus);

}
