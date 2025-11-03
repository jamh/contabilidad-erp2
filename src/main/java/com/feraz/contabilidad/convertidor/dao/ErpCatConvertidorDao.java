
package com.feraz.contabilidad.convertidor.dao;

import com.feraz.contabilidad.convertidor.model.ErpCatConvertidor;
//import com.feraz.contabilidad.convertidor.model.ErpCatConvertidor2;
import com.feraz.contabilidad.convertidor.model.ErpCatConvertidorId;
import java.util.List;

/**
 *
 * @author Ing JAMH
 */
public interface ErpCatConvertidorDao {

    public ErpCatConvertidorId save(ErpCatConvertidor erpCatConvertidor);

    public List<ErpCatConvertidor> findErpCatConvertidor();

    public boolean delete(ErpCatConvertidor deleteErpCatConvertidor);

    public boolean update(ErpCatConvertidor updateErpCatConvertidor);

    public int getMaxIdConvertidor(ErpCatConvertidorId id);

    public List<ErpCatConvertidor> getConvertidor(String compania, String idconcgasto, String noCaso, String origen);

    public ErpCatConvertidor buscaRfc(String emisor, String receptor);

//    public List<ErpCatConvertidor2> findErpCatConvertidor2(String compania);
}
