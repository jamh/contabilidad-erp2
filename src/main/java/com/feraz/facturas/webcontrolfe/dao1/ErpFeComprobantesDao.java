/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.feraz.facturas.webcontrolfe.dao1;

import com.feraz.facturas.webcontrolfe.dto.ComprobanteDto;
import com.feraz.facturas.webcontrolfe.model.ErpFeComprobantes;
import com.feraz.facturas.webcontrolfe.model.ErpFeComprobantesId;
import com.feraz.polizas3.model.PolizasId;
import java.math.BigDecimal;
import java.util.List;

/**
 *
 * @author Feraz3
 */
public interface ErpFeComprobantesDao {

    public ErpFeComprobantesId save(ErpFeComprobantes erpFeComprobantes);
    
    public ErpFeComprobantesId save2(ErpFeComprobantes erpFeComprobantes);

    public ErpFeComprobantes findErpFeComprobantes(String compania,int numero);

    public boolean delete(ErpFeComprobantes deleteErpFeComprobantes);
    
    public boolean updateErpComprobantes(ErpFeComprobantes disp, String fecha);
    public boolean updateErpComprobantesDeduc(ErpFeComprobantes disp);
    
    public boolean update(ErpFeComprobantes updateErpFeComprobantes);

    public int getMaxIdCampo(ErpFeComprobantesId id);

    public String buscarFactura(String compania, String folio, String uuid);

    public ErpFeComprobantes buscarFactura2(String compania, String uuid);

    public boolean precesaLista(List<ComprobanteDto> lista, String compania);
    
    public boolean actualizaComprobancePoliza(PolizasId idPol,ErpFeComprobantes comp,String noCaso);
    
    public boolean actualizaEstatus(ErpFeComprobantes comp);
    public boolean actualizaEstatusFolioPagos(ErpFeComprobantes comp, String monedaPago);
    public boolean actualizaEstatusFolioPagosCie(ErpFeComprobantes comp, String monedaPago,BigDecimal pagoCie);
    public boolean actualizaEstatusFolioPagosReembolso(ErpFeComprobantes comp, String monedaPago);
     public boolean actualizaEstatusFolioPagosUuid(ErpFeComprobantes comp, String monedaPago);
    public boolean updateSaldoCxp(ErpFeComprobantes disp);
     public boolean actualizaPago(String estatus, BigDecimal pago, ErpFeComprobantes comp);
     
     public boolean eliminaRelacionTesoreria(String estatus, BigDecimal pago, ErpFeComprobantes comp);
     public boolean descripcionCancelacion(String descripcionCan, ErpFeComprobantes comp);
     public boolean cancelaFactura(String estatus, ErpFeComprobantes comp);
    
     

}
