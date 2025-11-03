package com.feraz.contabilidad.sat.electronica.controll;

import com.feraz.contabilidad.sat.electronica.dao.ErpSatAuxiliarCtasDao;
import com.feraz.contabilidad.sat.electronica.dao.ErpSatAuxiliarCtasDetDao;
import com.feraz.contabilidad.sat.electronica.dao.ErpSatBalanzaDao;
import com.feraz.contabilidad.sat.electronica.dao.ErpSatCatalogoCtasDao;
import com.feraz.contabilidad.sat.electronica.dao.ErpSatCatalogoDao;
import com.feraz.contabilidad.sat.electronica.dao.ErpSatPolizaDao;
import com.feraz.contabilidad.sat.electronica.dao.ErpSatPolizasDao;
import com.feraz.contabilidad.sat.electronica.dao.ErpSatRepAuxFolDao;
import com.feraz.contabilidad.sat.electronica.dao.ErpSatRepAuxFolDetDao;
import com.feraz.contabilidad.sat.electronica.model.ErpSatAuxiliarCtas;
import com.feraz.contabilidad.sat.electronica.model.ErpSatAuxiliarCtasDet;
import com.feraz.contabilidad.sat.electronica.model.ErpSatBalanza;
import com.feraz.contabilidad.sat.electronica.model.ErpSatCatalogo;
import com.feraz.contabilidad.sat.electronica.model.ErpSatCatalogoCtas;
import com.feraz.contabilidad.sat.electronica.model.ErpSatPoliza;
import com.feraz.contabilidad.sat.electronica.model.ErpSatPolizas;
import com.feraz.contabilidad.sat.electronica.model.ErpSatRepAuxFol;
import com.feraz.contabilidad.sat.electronica.model.ErpSatRepAuxFolDet;
import com.feraz.contabilidad.sat.process.GeneraAuxiliarCtasXML11;
import com.feraz.contabilidad.sat.process.GeneraAuxiliarFoliosTXML;
import com.feraz.contabilidad.sat.process.GeneraAuxiliarFoliosXML11;
import com.feraz.contabilidad.sat.process.GeneraBalanzaXML11;
import com.feraz.contabilidad.sat.process.GeneraBalanzaXML13;
import com.feraz.contabilidad.sat.process.GeneraCuentasXML11;
import com.feraz.contabilidad.sat.process.GeneraPolizasXML11;
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
@RequestMapping(value = "/sat11")
public class ProcessSAT11 {

    private GeneraBalanzaXML11 generaBalanzaXML11;
    private GeneraCuentasXML11 generaCuentasXML11;
    private GeneraPolizasXML11 generaPolizasXML11;
    private GeneraAuxiliarFoliosXML11 generaAuxiliarFoliosXML11;
    private GeneraAuxiliarCtasXML11 generaAuxiliarCtasXML11;
    private ErpSatBalanzaDao erpSatBalanzaDao;
    private ErpSatCatalogoDao erpSatCatalogoDao;
    private ErpSatCatalogoCtasDao erpSatCatalogoCtasDao;
    private ErpSatPolizaDao erpSatPolizaDao;
    private ErpSatPolizasDao erpSatPolizasDao;
    private ProcessDao processDao;
        private ErpSatRepAuxFolDao erpSatRepAuxFolDao;
        private ErpSatRepAuxFolDetDao erpSatRepAuxFolDetDao;
        private ErpSatAuxiliarCtasDao erpSatAuxiliarCtasDao;
        private ErpSatAuxiliarCtasDetDao erpSatAuxiliarCtasDetDao;
    private GeneraAuxiliarFoliosTXML generaAuxiliarFoliosTXML;
    private GeneraBalanzaXML13 generaBalanzaXML13;

    @RequestMapping(value = "/balanza11/{comp}/{id}/{name}.xml", method = RequestMethod.GET, produces = "application/*+xml;charset=UTF-8")
    public @ResponseBody
    String doBalanza11(@PathVariable("comp") String comp, @PathVariable("id") String id,
            @PathVariable("name") String name,
            WebRequest webRequest, Model model) throws IOException, DatatypeConfigurationException, ParseException {

        List<ErpSatBalanza> lista = erpSatBalanzaDao.FindErpSatBalanza(comp, id);

        return generaBalanzaXML11.procesa(lista, name);
    }
    
    @RequestMapping(value = "/balanza13/{comp}/{id}/{name}.xml", method = RequestMethod.GET, produces = "application/*+xml;charset=UTF-8")
    public @ResponseBody
    String doBalanza13(@PathVariable("comp") String comp, @PathVariable("id") String id,
            @PathVariable("name") String name,
            WebRequest webRequest, Model model) throws IOException, DatatypeConfigurationException, ParseException, JAXBException {

        List<ErpSatBalanza> lista = erpSatBalanzaDao.FindErpSatBalanza(comp, id);

        return generaBalanzaXML13.procesa(lista, name);
    }

    @RequestMapping(value = "/cuentas11/{comp}/{id}/{name}.xml", method = RequestMethod.GET, produces = "application/*+xml;charset=UTF-8")
    public @ResponseBody
    String doCatalogo11(@PathVariable("comp") String comp, @PathVariable("id") String id,
            @PathVariable("name") String name,
            WebRequest webRequest, Model model) {
        ErpSatCatalogo erpSatCatalogo = erpSatCatalogoDao.findErpSatCatalogo(comp, id);
        List<ErpSatCatalogoCtas> lista = erpSatCatalogoCtasDao.FindErpSatCatalogoCtas(comp, id);
        return generaCuentasXML11.procesa2(erpSatCatalogo, lista, name);
    }

    @RequestMapping(value = "/polizas11/{comp}/{id}/{name}.xml", method = RequestMethod.GET, produces = "application/*+xml;charset=UTF-8")
    public @ResponseBody
    Polizas doPolizas11(@PathVariable("comp") String comp, @PathVariable("id") String id,
            @PathVariable("name") String name,
            WebRequest webRequest, Model model) throws IOException {

        System.out.println("compania " + comp);
        System.out.println("id " + id);
        System.out.println("name " + name);
        ErpSatPolizas erpSatPolizas = erpSatPolizasDao.findErpSatPolizas(comp, id);
        List<ErpSatPoliza> lista = erpSatPolizaDao.FindErpSatPoliza(comp, id);

        return generaPolizasXML11.procesa2(erpSatPolizas, lista, name);
    }

    @RequestMapping(value = "/auxiliarfolios11/{comp}/{id}/{name}.xml", method = RequestMethod.GET, produces = "application/*+xml;charset=UTF-8")
    public @ResponseBody
    String doAuxiliarFolios11(@PathVariable("comp") String comp, @PathVariable("id") String id,
            @PathVariable("name") String name,
            WebRequest webRequest, Model model) throws IOException {

        System.out.println("compania " + comp);
        System.out.println("id " + id);
        System.out.println("name " + name);
        ErpSatRepAuxFol erpSatRepAuxFol = erpSatRepAuxFolDao.findErpSatRepAuxFol(comp, id);
        List<ErpSatRepAuxFolDet> lista = erpSatRepAuxFolDetDao.FindErpSatRepAuxFolDe(comp, id);
        
      
        return generaAuxiliarFoliosXML11.procesa2(erpSatRepAuxFol, lista, name);
    }
    
    @RequestMapping(value = "/auxiliarcuentas11/{comp}/{id}/{name}.xml", method = RequestMethod.GET, produces = "application/*+xml;charset=UTF-8")
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

        return generaAuxiliarCtasXML11.procesa2(erpSatAuxiliarCtas,name);
    }
    
    
    @RequestMapping(value = "/auxiliarfoliosT/{comp}/{cal}/{per}/{id}/{name}.xml", method = RequestMethod.GET, produces = "application/*+xml;charset=UTF-8")
    public @ResponseBody
    String doAuxiliarFolios11t(@PathVariable("comp") String comp, @PathVariable("cal") String calendario,@PathVariable("per") String periodo, @PathVariable("id") String id,
            @PathVariable("name") String name,
            WebRequest webRequest, Model model) throws IOException {

        System.out.println("compania " + comp);
        System.out.println("id " + id);
        System.out.println("name " + name);
        System.out.println("calendario " + calendario);
        System.out.println("periodo " + periodo);
        
      
        return generaAuxiliarFoliosTXML.procesa2(name,calendario,periodo, comp);
    }
    
    

    public void setGeneraBalanzaXML11(GeneraBalanzaXML11 generaBalanzaXML11) {
        this.generaBalanzaXML11 = generaBalanzaXML11;
    }

    public void setErpSatBalanzaDao(ErpSatBalanzaDao erpSatBalanzaDao) {
        this.erpSatBalanzaDao = erpSatBalanzaDao;
    }

    public void setGeneraCuentasXML11(GeneraCuentasXML11 generaCuentasXML11) {
        this.generaCuentasXML11 = generaCuentasXML11;
    }

    public void setErpSatCatalogoDao(ErpSatCatalogoDao erpSatCatalogoDao) {
        this.erpSatCatalogoDao = erpSatCatalogoDao;
    }

    public void setErpSatCatalogoCtasDao(ErpSatCatalogoCtasDao erpSatCatalogoCtasDao) {
        this.erpSatCatalogoCtasDao = erpSatCatalogoCtasDao;
    }

    public void setGeneraPolizasXML11(GeneraPolizasXML11 generaPolizasXML11) {
        this.generaPolizasXML11 = generaPolizasXML11;
    }

    public void setGeneraAuxiliarFoliosXML11(GeneraAuxiliarFoliosXML11 generaAuxiliarFoliosXML11) {
        this.generaAuxiliarFoliosXML11 = generaAuxiliarFoliosXML11;
    }

    public void setGeneraAuxiliarCtasXML11(GeneraAuxiliarCtasXML11 generaAuxiliarCtasXML11) {
        this.generaAuxiliarCtasXML11 = generaAuxiliarCtasXML11;
    }
    
    

    public void setErpSatPolizaDao(ErpSatPolizaDao erpSatPolizaDao) {
        this.erpSatPolizaDao = erpSatPolizaDao;
    }

    public void setErpSatPolizasDao(ErpSatPolizasDao erpSatPolizasDao) {
        this.erpSatPolizasDao = erpSatPolizasDao;
    }

    public void setProcessDao(ProcessDao processDao) {
        this.processDao = processDao;
    }

    public void setErpSatRepAuxFolDao(ErpSatRepAuxFolDao erpSatRepAuxFolDao) {
        this.erpSatRepAuxFolDao = erpSatRepAuxFolDao;
    }

    public void setErpSatRepAuxFolDetDao(ErpSatRepAuxFolDetDao erpSatRepAuxFolDetDao) {
        this.erpSatRepAuxFolDetDao = erpSatRepAuxFolDetDao;
    }

    public void setErpSatAuxiliarCtasDao(ErpSatAuxiliarCtasDao erpSatAuxiliarCtasDao) {
        this.erpSatAuxiliarCtasDao = erpSatAuxiliarCtasDao;
    }

    public void setErpSatAuxiliarCtasDetDao(ErpSatAuxiliarCtasDetDao erpSatAuxiliarCtasDetDao) {
        this.erpSatAuxiliarCtasDetDao = erpSatAuxiliarCtasDetDao;
    }

    public GeneraAuxiliarFoliosTXML getGeneraAuxiliarFoliosTXML() {
        return generaAuxiliarFoliosTXML;
    }

    public void setGeneraAuxiliarFoliosTXML(GeneraAuxiliarFoliosTXML generaAuxiliarFoliosTXML) {
        this.generaAuxiliarFoliosTXML = generaAuxiliarFoliosTXML;
    }

    public void setGeneraBalanzaXML13(GeneraBalanzaXML13 generaBalanzaXML13) {
        this.generaBalanzaXML13 = generaBalanzaXML13;
    }

  

    
}
