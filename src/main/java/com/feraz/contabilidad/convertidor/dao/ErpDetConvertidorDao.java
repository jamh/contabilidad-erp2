/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.feraz.contabilidad.convertidor.dao;

import com.feraz.contabilidad.convertidor.model.ErpCatConvertidorId;
import com.feraz.contabilidad.convertidor.model.ErpDetConvertidor;
import com.feraz.contabilidad.convertidor.model.ErpDetConvertidorId;
import java.math.BigDecimal;
import java.util.List;

/**
 *
 * @author Ing.JAMH
 */
public interface ErpDetConvertidorDao {

    public ErpDetConvertidorId save(ErpDetConvertidor erpDetConvertidor);

    public List<ErpDetConvertidor> findErpDetConvertidor(String compania,String noCaso);
    
    public List<ErpDetConvertidor> findErpDetConvertidorOrigen(String compania,String noCaso,String Origen);

   public boolean delete(String compania, String idconcgasto, String origen, BigDecimal noCaso);

    public boolean deleteAll(ErpCatConvertidorId erpcatid);

    public boolean update(ErpDetConvertidor updateErpDetConvertidor);

    public int getMaxIdDetConvertidor(ErpDetConvertidorId id);

    public boolean updateDetConvPorSec(String compania, String idconcgasto, String origen, BigDecimal noCaso, BigDecimal sec, ErpDetConvertidor disp);

    public boolean borraDetConvPorSec(String compania, String idconcgasto, String origen, BigDecimal noCaso, BigDecimal sec);
}
