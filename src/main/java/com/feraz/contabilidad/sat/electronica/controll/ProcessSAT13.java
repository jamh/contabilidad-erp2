/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.feraz.contabilidad.sat.electronica.controll;

/**
 *
 * @author 55555
 */


import com.feraz.contabilidad.sat.electronica.dao.ErpSatAuxiliarCtasDao;
import com.feraz.contabilidad.sat.electronica.model.ErpSatBalanza;
import com.feraz.contabilidad.sat.process.GeneraBalanzaXML13;
import com.feraz.contabilidad.sat.electronica.dao.ErpSatBalanzaDao;
import com.feraz.contabilidad.sat.electronica.dao.ErpSatCatalogoCtasDao;
import com.feraz.contabilidad.sat.electronica.dao.ErpSatCatalogoDao;
import com.feraz.contabilidad.sat.electronica.dao.ErpSatPolizaDao;
import com.feraz.contabilidad.sat.electronica.dao.ErpSatPolizasDao;
import com.feraz.contabilidad.sat.electronica.dao.ErpSatRepAuxFolDao;
import com.feraz.contabilidad.sat.electronica.dao.ErpSatRepAuxFolDetDao;
import com.feraz.contabilidad.sat.electronica.model.ErpSatAuxiliarCtas;
import com.feraz.contabilidad.sat.electronica.model.ErpSatCatalogo;
import com.feraz.contabilidad.sat.electronica.model.ErpSatCatalogoCtas;
import com.feraz.contabilidad.sat.electronica.model.ErpSatPoliza;
import com.feraz.contabilidad.sat.electronica.model.ErpSatPolizas;
import com.feraz.contabilidad.sat.electronica.model.ErpSatRepAuxFol;
import com.feraz.contabilidad.sat.electronica.model.ErpSatRepAuxFolDet;
import com.feraz.contabilidad.sat.process.GeneraAuxiliarCtasXML13;
import com.feraz.contabilidad.sat.process.GeneraAuxiliarFoliosXML13;
import com.feraz.contabilidad.sat.process.GeneraCuentasXML13;
import com.feraz.contabilidad.sat.process.GeneraPolizasXML13;

import java.io.IOException;
import java.text.ParseException;
import java.util.List;
import javax.xml.bind.JAXBException;
import javax.xml.datatype.DatatypeConfigurationException;
import mx.bigdata.sat.ce_polizas_periodo.schema.Polizas;
import org.jamh.data.process.ProcessDao;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.request.WebRequest;

/**
 *
 * @author Ing. JAMH
 */
@Controller
@RequestMapping(value = "/sat13")

public class ProcessSAT13 {
    
    private GeneraBalanzaXML13 generaBalanzaXML13;
    private ErpSatBalanzaDao erpSatBalanzaDao;
    private GeneraCuentasXML13 generaCuentasXML13;
    private ErpSatCatalogoDao erpSatCatalogoDao;
    private ErpSatCatalogoCtasDao erpSatCatalogoCtasDao;
    private ErpSatPolizasDao erpSatPolizasDao;
    private ErpSatPolizaDao erpSatPolizaDao;
    private GeneraPolizasXML13 generaPolizasXML13;
    private ErpSatAuxiliarCtasDao erpSatAuxiliarCtasDao;
    private GeneraAuxiliarCtasXML13 generaAuxiliarCtasXML13;
    private ErpSatRepAuxFolDao erpSatRepAuxFolDao;
    private ErpSatRepAuxFolDetDao erpSatRepAuxFolDetDao;
    private GeneraAuxiliarFoliosXML13 generaAuxiliarFoliosXML13;

    public void setGeneraCuentasXML13(GeneraCuentasXML13 generaCuentasXML13) {
        this.generaCuentasXML13 = generaCuentasXML13;
    }
    
    @RequestMapping(value = "/balanza13/{comp}/{id}/{name}.xml", method = RequestMethod.GET, produces = "application/*+xml;charset=UTF-8")
    public @ResponseBody
    String doBalanza13(@PathVariable("comp") String comp, @PathVariable("id") String id,
            @PathVariable("name") String name,
            WebRequest webRequest, Model model) throws IOException, DatatypeConfigurationException, ParseException, JAXBException {

        List<ErpSatBalanza> lista = erpSatBalanzaDao.FindErpSatBalanza(comp, id);

        return generaBalanzaXML13.procesa(lista, name);
    }
    
    @RequestMapping(value = "/cuentas13/{comp}/{id}/{name}.xml", method = RequestMethod.GET, produces = "application/*+xml;charset=UTF-8")
    public @ResponseBody
    String doCatalogo13(@PathVariable("comp") String comp, @PathVariable("id") String id,
            @PathVariable("name") String name,
            WebRequest webRequest, Model model) {
        ErpSatCatalogo erpSatCatalogo = erpSatCatalogoDao.findErpSatCatalogo(comp, id);
        List<ErpSatCatalogoCtas> lista = erpSatCatalogoCtasDao.FindErpSatCatalogoCtas(comp, id);
        return generaCuentasXML13.procesa2(erpSatCatalogo, lista, name);
    }
    
     @RequestMapping(value = "/polizas13/{comp}/{id}/{name}.xml", method = RequestMethod.GET, produces = "application/*+xml;charset=UTF-8")
    public @ResponseBody
    String doPolizas13(@PathVariable("comp") String comp, @PathVariable("id") String id,
            @PathVariable("name") String name,
            WebRequest webRequest, Model model) throws IOException, DatatypeConfigurationException, ParseException {

        System.out.println("compania " + comp);
        System.out.println("id " + id);
        System.out.println("name " + name);
        
        System.out.println("Busca POLIZASSSS " + name);
        ErpSatPolizas erpSatPolizas = erpSatPolizasDao.findErpSatPolizas(comp, id);
        System.out.println("Busca Poliza " + name);
        List<ErpSatPoliza> lista = erpSatPolizaDao.FindErpSatPoliza(comp, id);
        System.out.println("generando xml " + name);
        return generaPolizasXML13.procesa2(erpSatPolizas, lista, name);
    }
    
    @RequestMapping(value = "/auxiliarcuentas13/{comp}/{id}/{name}.xml", method = RequestMethod.GET, produces = "application/*+xml;charset=UTF-8")
    public @ResponseBody
    String doAuxiliarCuentas11(@PathVariable("comp") String comp, @PathVariable("id") String id,
            @PathVariable("name") String name,
            WebRequest webRequest, Model model) throws IOException {

     //   System.out.println("compania " + comp);
     //   System.out.println("id " + id);
     //   System.out.println("name " + name);
        ErpSatAuxiliarCtas erpSatAuxiliarCtas = erpSatAuxiliarCtasDao.findErpSatAuxiliarCtas(comp, id);
       // List<ErpSatAuxiliarCtasDet> lista = erpSatAuxiliarCtasDetDao.FindErpSatAuxiliarCtasDet(comp, id);
        
      //  System.out.println("erpSatAuxiliarCtas"+ erpSatAuxiliarCtas.getAnio());

        return generaAuxiliarCtasXML13.procesa2(erpSatAuxiliarCtas,name);
    }
    
    @RequestMapping(value = "/auxiliarfolios13/{comp}/{id}/{name}.xml", method = RequestMethod.GET, produces = "application/*+xml;charset=UTF-8")
    public @ResponseBody
    String doAuxiliarFolios11(@PathVariable("comp") String comp, @PathVariable("id") String id,
            @PathVariable("name") String name,
            WebRequest webRequest, Model model) throws IOException {

        System.out.println("compania " + comp);
        System.out.println("id " + id);
        System.out.println("name " + name);
        ErpSatRepAuxFol erpSatRepAuxFol = erpSatRepAuxFolDao.findErpSatRepAuxFol(comp, id);
        List<ErpSatRepAuxFolDet> lista = erpSatRepAuxFolDetDao.FindErpSatRepAuxFolDe(comp, id);
        
      
        return generaAuxiliarFoliosXML13.procesa2(erpSatRepAuxFol, lista, name);
    }

    public void setGeneraBalanzaXML13(GeneraBalanzaXML13 generaBalanzaXML13) {
        this.generaBalanzaXML13 = generaBalanzaXML13;
    }

    public void setErpSatBalanzaDao(ErpSatBalanzaDao erpSatBalanzaDao) {
        this.erpSatBalanzaDao = erpSatBalanzaDao;
    }

    public void setErpSatCatalogoDao(ErpSatCatalogoDao erpSatCatalogoDao) {
        this.erpSatCatalogoDao = erpSatCatalogoDao;
    }

    public void setErpSatCatalogoCtasDao(ErpSatCatalogoCtasDao erpSatCatalogoCtasDao) {
        this.erpSatCatalogoCtasDao = erpSatCatalogoCtasDao;
    }

    public void setErpSatPolizasDao(ErpSatPolizasDao erpSatPolizasDao) {
        this.erpSatPolizasDao = erpSatPolizasDao;
    }

    public void setErpSatPolizaDao(ErpSatPolizaDao erpSatPolizaDao) {
        this.erpSatPolizaDao = erpSatPolizaDao;
    }

    public void setGeneraPolizasXML13(GeneraPolizasXML13 generaPolizasXML13) {
        this.generaPolizasXML13 = generaPolizasXML13;
    }

    public void setErpSatAuxiliarCtasDao(ErpSatAuxiliarCtasDao erpSatAuxiliarCtasDao) {
        this.erpSatAuxiliarCtasDao = erpSatAuxiliarCtasDao;
    }

    public void setGeneraAuxiliarCtasXML13(GeneraAuxiliarCtasXML13 generaAuxiliarCtasXML13) {
        this.generaAuxiliarCtasXML13 = generaAuxiliarCtasXML13;
    }

    public void setErpSatRepAuxFolDao(ErpSatRepAuxFolDao erpSatRepAuxFolDao) {
        this.erpSatRepAuxFolDao = erpSatRepAuxFolDao;
    }

    public void setErpSatRepAuxFolDetDao(ErpSatRepAuxFolDetDao erpSatRepAuxFolDetDao) {
        this.erpSatRepAuxFolDetDao = erpSatRepAuxFolDetDao;
    }

    public void setGeneraAuxiliarFoliosXML13(GeneraAuxiliarFoliosXML13 generaAuxiliarFoliosXML13) {
        this.generaAuxiliarFoliosXML13 = generaAuxiliarFoliosXML13;
    }
    
    
    
}
