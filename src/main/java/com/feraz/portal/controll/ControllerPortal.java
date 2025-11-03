/*
 * Programa para generar el contenido del Portal
 */
package com.feraz.portal.controll;

import com.feraz.cxp.model.ErpCClientes;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.ModelAndView;

/**
 *
 * @author Ing. JAMH
 */
@Controller
@SessionAttributes("proveedor")

//@RequestMapping("/p")
public class ControllerPortal {
    

    
     @RequestMapping(value = "/proveedor/control",method = RequestMethod.GET)
    public String inicio(
            Model model) {      
       
        
        return "portal/indexPortal";
    }
    
    @RequestMapping(value = "/proveedor/controlFB",method = RequestMethod.GET)
    public String inicioFB(
            Model model) {      
       
        
        return "portal/indexPortalFiabi";
    }
    
    @RequestMapping(value = "/proveedor/controlVL",method = RequestMethod.GET)
    public String inicioVL(
            Model model) {      
       
        
        return "portal/indexPortalVlh1";
    }
    
    @RequestMapping(value = "/proveedor/controlOF",method = RequestMethod.GET)
    public String inicioOF(
            Model model) {      
       
        
        return "portal/indexPortalOF";
    }
    
    @RequestMapping(value = "/proveedor/controlSC",method = RequestMethod.GET)
    public String inicioSC(
            Model model) {      
       
        
        return "portal/indexPortalSC";
    }
    
    @RequestMapping(value = "/proveedor/controlBTM",method = RequestMethod.GET)
    public String inicioBTM(
            Model model) {      
       
        
        return "portal/indexPortalBTM";
    }
    
    @RequestMapping(value = "/proveedor/controlKGA",method = RequestMethod.GET)
    public String inicioKGA(
            Model model) {      
       
        
        return "portal/indexPortalKGA";
    }
    
    @RequestMapping(value = "/proveedor/controlAPI",method = RequestMethod.GET)
    public String inicioAPI(
            Model model) {      
       
        
        return "portal/indexPortalAPI";
    }
    
    @RequestMapping(value = "/proveedor/controlTEMP",method = RequestMethod.GET)
    public String inicioTEMP(
            Model model) {      
       
        
        return "portal/indexPortalTEMP";
    }
    
    @RequestMapping(value = "/proveedor/PortalInsurgentes",method = RequestMethod.GET)
    public String inicioInsurgentes(
            Model model) {      
       
        
        return "init/LoginPortalInsurgentes";
    }
    
    
     @RequestMapping(value = "/facturas", method = RequestMethod.GET)
    public ModelAndView report(WebRequest webRequest, Model model) {

        ModelAndView model2 = new ModelAndView();
        
        ErpCClientes prov = (ErpCClientes)model.asMap().get("proveedor");  
        
        if(prov==null){
            model2.setViewName("init/LoginPortal");
            return model2;
        }
        
        model2.setViewName("init/Portal");

        return model2;
    }
    
    @RequestMapping(value = "/facturasBaca", method = RequestMethod.GET)
    public ModelAndView reportBaca(WebRequest webRequest, Model model) {

        ModelAndView model2 = new ModelAndView();
        
        ErpCClientes prov = (ErpCClientes)model.asMap().get("proveedor");  
        
        if(prov==null){
            model2.setViewName("init/LoginPortal");
            return model2;
        }
        
        model2.setViewName("init/PortalBaca");

        return model2;
    }
    
     @RequestMapping(value = "/facturasUni", method = RequestMethod.GET)
    public ModelAndView reportUni(WebRequest webRequest, Model model) {

        ModelAndView model2 = new ModelAndView();
        
         System.out.println("UNIVERSIDAD");
        
        ErpCClientes prov = (ErpCClientes)model.asMap().get("proveedor");  
        
          System.out.println(prov);
        
        if(prov==null){
            model2.setViewName("init/LoginPortalInsurgentes");
            return model2;
        }
        
        model2.setViewName("init/PortalInsurgentes");

        return model2;
    }
    
    
    @RequestMapping(value = "/facturasFolio", method = RequestMethod.GET)
    public ModelAndView reportFolio(WebRequest webRequest, Model model) {

        ModelAndView model2 = new ModelAndView();
        
        ErpCClientes prov = (ErpCClientes)model.asMap().get("proveedor");  
        
        if(prov==null){
            model2.setViewName("init/LoginPortalFolio");
            return model2;
        }
        
        model2.setViewName("init/PortalFolio");

        return model2;
    }
    @RequestMapping(value = "/facturasAereo", method = RequestMethod.GET)
    public ModelAndView reportAereo(WebRequest webRequest, Model model) {

        ModelAndView model2 = new ModelAndView();
        
        ErpCClientes prov = (ErpCClientes)model.asMap().get("proveedor");  
        
        if(prov==null){
            model2.setViewName("init/LoginPortalAereo");
            return model2;
        }
        
        model2.setViewName("init/PortalAereo");

        return model2;
    }
    
     @RequestMapping(value = "/facturasProveedor", method = RequestMethod.GET)
    public ModelAndView facturasProveedor(WebRequest webRequest, Model model) {

        ModelAndView model2 = new ModelAndView();
        
        ErpCClientes prov = (ErpCClientes)model.asMap().get("proveedor");  
        
        if(prov==null){
            model2.setViewName("init/LoginPortalAereo");
            return model2;
        }
        
        model2.setViewName("init/PortalProveedorAcceso");

        return model2;
    }
    
      @RequestMapping(value = "/facturasAereoEng", method = RequestMethod.GET)
    public ModelAndView reportAereoEng(WebRequest webRequest, Model model) {

        ModelAndView model2 = new ModelAndView();
        
        ErpCClientes prov = (ErpCClientes)model.asMap().get("proveedor");  
        
        if(prov==null){
            model2.setViewName("init/LoginPortalAereo");
            return model2;
        }
        
        model2.setViewName("init/PortalAereoEng");

        return model2;
    }
    
    
       @RequestMapping(value = "/facturasSGS", method = RequestMethod.GET)
    public ModelAndView reportSGS(WebRequest webRequest, Model model) {

        ModelAndView model2 = new ModelAndView();
        
        ErpCClientes prov = (ErpCClientes)model.asMap().get("proveedor");  
        
        if(prov==null){
            model2.setViewName("init/LoginPortalSGS");
            return model2;
        }
        
        model2.setViewName("init/PortalSGS");

        return model2;
    }
    
      @RequestMapping(value = "/facturasSGSEng", method = RequestMethod.GET)
    public ModelAndView reportSGSEng(WebRequest webRequest, Model model) {

        ModelAndView model2 = new ModelAndView();
        
        ErpCClientes prov = (ErpCClientes)model.asMap().get("proveedor");  
        
        if(prov==null){
            model2.setViewName("init/LoginPortalSGS");
            return model2;
        }
        
        model2.setViewName("init/PortalSGSEng");

        return model2;
    }
    
    
    @RequestMapping(value = "/home", method = RequestMethod.GET)
    public ModelAndView home(WebRequest webRequest, Model model) {

        ModelAndView model2 = new ModelAndView();
        
     
            model2.setViewName("init/LoginPortal");
            return model2;
        
    }
}
