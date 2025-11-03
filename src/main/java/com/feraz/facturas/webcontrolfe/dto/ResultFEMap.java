package com.feraz.facturas.webcontrolfe.dto;

import com.feraz.facturas.webcontrolfe.model.ErpFeComprobantes;
import java.util.Map;

/**
 *
 * @author Ing. JAMH
 */
public class ResultFEMap {
    
    private ErpFeComprobantes comprobante;
    private Map mapa;

    public ResultFEMap() {
    }

    public ErpFeComprobantes getComprobante() {
        return comprobante;
    }

    public void setComprobante(ErpFeComprobantes comprobante) {
        this.comprobante = comprobante;
    }

    public Map getMapa() {
        return mapa;
    }

    public void setMapa(Map mapa) {
        this.mapa = mapa;
    }
    
    
    
}
