/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.feraz.contabilidad.sat.electronica.controll;

import com.feraz.contabilidad.sat.electronica.dao.ErpSatBalanzaDao;
import com.feraz.contabilidad.sat.electronica.dao.ErpSatCatalogoCtasDao;
import com.feraz.contabilidad.sat.electronica.dao.ErpSatCatalogoDao;
import com.feraz.contabilidad.sat.electronica.dao.ErpSatPolizaDao;
import com.feraz.contabilidad.sat.electronica.dao.ErpSatPolizasDao;
import com.feraz.contabilidad.sat.electronica.model.ErpSatBalanza;
import com.feraz.contabilidad.sat.electronica.model.ErpSatCatalogo;
import com.feraz.contabilidad.sat.electronica.model.ErpSatCatalogoCtas;
import com.feraz.contabilidad.sat.electronica.model.ErpSatPoliza;
import com.feraz.contabilidad.sat.electronica.model.ErpSatPolizas;
import com.feraz.contabilidad.sat.electronica.model.ListaBalanza;
import com.feraz.contabilidad.sat.electronica.model.ListaCatalogos;
import com.feraz.contabilidad.sat.electronica.model.ListaPolizas;
import java.util.List;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.context.request.WebRequest;

/**
 *
 * @author JAMH
 */
@Controller
@RequestMapping(value = "/sat")
@SessionAttributes({"compania", "usuario"})
public class ProcesaSAT {

   private ErpSatCatalogoDao erpSatCatalogoDao;
    private ErpSatBalanzaDao erpSatBalanzaDao;
    private ErpSatCatalogoCtasDao erpSatCatalogoCtasDao;
    private ErpSatPolizaDao erpSatPolizaDao;
    private ErpSatPolizasDao erpSatPolizasDao;

    @RequestMapping(value = "/balanza/{comp}/{id}.xml", method = RequestMethod.GET)
    public @ResponseBody
    ListaBalanza doBalanza(@PathVariable("comp") String comp, @PathVariable("id") String id,
            WebRequest webRequest, Model model) {
        
        List<ErpSatBalanza> lista = erpSatBalanzaDao.FindErpSatBalanza(comp, id);
//        System.out.println("lista:" + lista.size());
//        System.out.println(lista.get(0).getErpSatBalanzaCtas().get(0).getCompania());
        ListaBalanza lis = new ListaBalanza(lista);
        return lis;
    }

    @RequestMapping(value = "/cuentas/{comp}/{id}.xml", method = RequestMethod.GET)
    public @ResponseBody
    ListaCatalogos doCatalogo(@PathVariable("comp") String comp, @PathVariable("id") String id,
            WebRequest webRequest, Model model) {
        ErpSatCatalogo erpSatCatalogo =erpSatCatalogoDao.findErpSatCatalogo(comp, id); 
        List<ErpSatCatalogoCtas> lista = erpSatCatalogoCtasDao.FindErpSatCatalogoCtas(comp, id);
//        System.out.println("listaCuentas:" + lista);
        ListaCatalogos lis = new ListaCatalogos(lista);
        lis.setErpSatCatalogo(erpSatCatalogo);
        return lis;
    }

    @RequestMapping(value = "/polizas/{comp}/{id}.xml", method = RequestMethod.GET)
    public @ResponseBody
    ListaPolizas doPolizas(@PathVariable("comp") String comp, @PathVariable("id") String id,
            WebRequest webRequest, Model model) {
       ErpSatPolizas erpSatPolizas =erpSatPolizasDao.findErpSatPolizas(comp, id);
        List<ErpSatPoliza> lista = erpSatPolizaDao.FindErpSatPoliza(comp, id);
      System.out.println("listaPolizas:" + lista.size());
//     System.out.println("listaPolizas:"+lista.get(0).getErpSatPlztransaccion().get(0).getNumcta());
//         System.out.println("listaPolizas2:"+lista.get(0).getErpSatPlztransaccion().get(1).getNumcta());
//                  System.out.println("listaPolizas2:"+lista.get(1).getErpSatPlztransaccion().get(0).getNumcta());
//                   System.out.println("listaPolizas2:"+lista.get(1).getErpSatPlztransaccion().get(1).getNumcta());
        ListaPolizas lis = new ListaPolizas(lista);
        lis.setErpSatPolizas(erpSatPolizas);
        return lis;
    }

//    public void setErpSatBalanzaCtasDao(ErpSatBalanzaCtasDao erpSatBalanzaCtasDao) {
//        this.erpSatBalanzaCtasDao = erpSatBalanzaCtasDao;
//    }
    public void setErpSatCatalogoCtasDao(ErpSatCatalogoCtasDao erpSatCatalogoCtasDao) {
        this.erpSatCatalogoCtasDao = erpSatCatalogoCtasDao;
    }

    public void setErpSatPolizaDao(ErpSatPolizaDao erpSatPolizaDao) {
        this.erpSatPolizaDao = erpSatPolizaDao;
    }

//    public void setErpSatBalanzaDao(ErpSatBalanzaCtasDao erpSatBalanzaDao) {
//        this.erpSatBalanzaDao = erpSatBalanzaDao;
//    }
    public void setErpSatBalanzaDao(ErpSatBalanzaDao erpSatBalanzaDao) {
        this.erpSatBalanzaDao = erpSatBalanzaDao;
    }

    public void setErpSatCatalogoDao(ErpSatCatalogoDao erpSatCatalogoDao) {
        this.erpSatCatalogoDao = erpSatCatalogoDao;
    }

    public void setErpSatPolizasDao(ErpSatPolizasDao erpSatPolizasDao) {
        this.erpSatPolizasDao = erpSatPolizasDao;
    }
    
    

}
