/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.feraz.cajachica.dao;

import com.feraz.cajachica.model.ErpSolicitudDeFondos;
import com.feraz.cajachica.model.ErpSolicitudDeFondosId;

/**
 *
 * @author vavi
 */
public interface ErpSolicitudDeFondosDao {
    
      public ErpSolicitudDeFondosId save(ErpSolicitudDeFondos erpSolicitudDeFondos);
      public boolean updateEstatusAuto(ErpSolicitudDeFondos erpSolicitudDeFondos);

    public boolean delete(ErpSolicitudDeFondos erpSolicitudDeFondos);
    
    public boolean updateEstatusEnFolio(ErpSolicitudDeFondos erpSolicitudDeFondos);
    public boolean updateEstatusEnFolioRetira(ErpSolicitudDeFondos erpSolicitudDeFondos);

    public boolean update(ErpSolicitudDeFondos erpSolicitudDeFondos);
    
    public boolean updateEstatus(ErpSolicitudDeFondos erpSolicitudDeFondos);
    
    public boolean updateEstatusAutorizar(ErpSolicitudDeFondos erpSolicitudDeFondos);
    public boolean updateEstatusAutorizarPag(ErpSolicitudDeFondos erpSolicitudDeFondos);
    public boolean updateIdCaja(ErpSolicitudDeFondos erpSolicitudDeFondos,Integer idCaja);
    
    public int getMaxErpSolCajaId(ErpSolicitudDeFondosId id);
    
}
