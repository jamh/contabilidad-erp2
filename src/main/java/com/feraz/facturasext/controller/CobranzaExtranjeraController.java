/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.feraz.facturasext.controller;

import com.feraz.facturasext.dao.ErpExtCobranzaDao;
import org.jamh.data.process.ProcessDao;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

/**
 *
 * @author Ing. David Ortiz García
 */

@Controller
@RequestMapping("/cobranzaExtranjera")
@SessionAttributes({"compania", "usuario"})
public class CobranzaExtranjeraController {
    
    private ProcessDao processDao;
    private ErpExtCobranzaDao erpExtCobranzaDao;

    public void setProcessDao(ProcessDao processDao) {
        this.processDao = processDao;
    }

    public void setErpExtCobranzaDao(ErpExtCobranzaDao erpExtCobranzaDao) {
        this.erpExtCobranzaDao = erpExtCobranzaDao;
    }
    
    
    
    
}
