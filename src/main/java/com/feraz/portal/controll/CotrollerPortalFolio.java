/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.feraz.portal.controll;

/**
 *
 * @author vavi
 */
import com.feraz.cxp.dao.ErpCClientesDao;
import com.feraz.cxp.model.ErpCClientes;
import com.feraz.registroaereo.DTO.RegsitroAereoDTO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.ModelAndView;

/**
 *
 * @author iNG. JAMH
 */
@Controller
@RequestMapping("/")
@SessionAttributes({"compania", "usuario"})
public class CotrollerPortalFolio {

    @Autowired
    private ErpCClientesDao erpCClientesDao;
    
    @RequestMapping(value = "/portalmas",method = RequestMethod.GET)
    public String portalmas(@RequestParam(value = "compania") String compania ,@RequestParam(value = "user") String user,Model model) {      
        model.addAttribute("compania", compania);
        model.addAttribute("usuario", user);
        
        return "init/PortalMas";
    }
    
     @RequestMapping(value = "/portalmas",method = RequestMethod.POST)
    public String portalmasPost(@RequestParam(value = "compania") String compania ,@RequestParam(value = "user") String user,Model model) {      
        model.addAttribute("compania", compania);
        model.addAttribute("usuario", user);
        return "init/PortalMas";
    }
    
    @RequestMapping(value = "/PortalProveedor", method = RequestMethod.GET)
    public String inicio(Model model) {
        System.out.println("aqui1");
        model.addAttribute("compania", "T2O");
        return "init/LoginPortalFolio";
    }

    @RequestMapping(value = "/PortalProveedor", method = RequestMethod.POST)
    public String inicioPost(Model model) {
        System.out.println("aqui1");
        model.addAttribute("compania", "T2O");
        return "init/LoginPortalFolio";
    }
    
    @RequestMapping(value = "/PUNTOP/Portal/Proveedor", method = RequestMethod.GET)
    public String inicioProveedor(Model model) {
        System.out.println("aqui1");
        model.addAttribute("compania", "PUNTOP");
        model.addAttribute("nombre", "PUNTO PEN");
        return "init/PortalProveedor";
    }

    @RequestMapping(value = "/DYOPATH/Portal/Proveedor", method = RequestMethod.POST)
    public String inicioProveedorDYOPATHPost(Model model) {
        System.out.println("aqui1");
        model.addAttribute("compania", "DYOPATH");
        model.addAttribute("nombre", "DYOPATH");
        return "init/PortalProveedor";
    }
    
     @RequestMapping(value = "/DYOPATH/Portal/Proveedor", method = RequestMethod.GET)
    public String inicioProveedorDYOPATH(Model model) {
        System.out.println("aqui1");
        model.addAttribute("compania", "DYOPATH");
        model.addAttribute("nombre", "DYOPATH");
        return "init/PortalProveedor";
    }

    @RequestMapping(value = "/PUNTOP/Portal/Proveedor", method = RequestMethod.POST)
    public String inicioProveedorPost(Model model) {
        System.out.println("aqui1");
        model.addAttribute("compania", "PUNTOP");
        model.addAttribute("nombre", "PUNTO PEN");
        return "init/PortalProveedor";
    }
    
   @RequestMapping(value = "/TEMP/Portal/Proveedor", method = RequestMethod.GET)
    public String inicioProveedorDEMO(Model model) {
        System.out.println("aqui1");
        model.addAttribute("compania", "TEMP");
        model.addAttribute("nombre", "FERAZ");
        return "init/PortalProveedor";
    }

    @RequestMapping(value = "/TEMP/Portal/Proveedor", method = RequestMethod.POST)
    public String inicioProveedorDEMO2(Model model) {
        System.out.println("aqui1");
        model.addAttribute("compania", "TEMP");
        model.addAttribute("nombre", "FERAZ");
        return "init/PortalProveedor";
    }
    
    
    
    @RequestMapping(value = "/PortalProveedorDemo", method = RequestMethod.GET)
    public String inicioDemo(Model model) {
        System.out.println("aqui1");
        model.addAttribute("compania", "TEMP");
        return "init/IndexPortalDemo";
    }

    @RequestMapping(value = "/PortalProveedorDemo", method = RequestMethod.POST)
    public String inicioPostDemo(Model model) {
        System.out.println("aqui1");
        model.addAttribute("compania", "TEMP");
        return "init/IndexPortalDemo";
    }

    @RequestMapping(value = "/PortalAereo", method = RequestMethod.GET)
    public String inicio2(Model model) {
        System.out.println("aqui1");
        model.addAttribute("compania", "MASAIR");
        model.addAttribute("idioma", "ESP");
        return "init/LoginPortalAereo";
    }
    
    @RequestMapping(value = "/PortalAereoTEMP", method = RequestMethod.GET)
    public String inicio2TEMP(Model model) {
        System.out.println("aqui1");
        model.addAttribute("compania", "TEMP");
        model.addAttribute("idioma", "ESP");
        return "init/LoginPortalAereo";
    }
    
    
    @RequestMapping(value = "/PortalAereoMyAviation", method = RequestMethod.GET)
    public String inicio2MyAvi(Model model) {
        System.out.println("aqui1");
        model.addAttribute("compania", "MABLL");
        model.addAttribute("idioma", "ESP");
        return "init/LoginMyAviation";
    }
    
    
                @RequestMapping(value = "/loginportal", method = RequestMethod.POST)
    public @ResponseBody
    ModelAndView login3P(
            @RequestParam("usuario") String usuario,
            @RequestParam("password") String password,
            @RequestParam("idioma") String idioma,
            HttpServletRequest request,
            WebRequest webRequest, Model model) {

          ModelAndView modelo = new ModelAndView("portal_aereo/LoginPortalAereo2");
        
        int j = 1;
        
         if (request.getSession().getAttribute("intentos") != null){
         
                 j = Integer.parseInt(request.getSession().getAttribute("intentos").toString());
         }
          
        
        int i = 1;
        int inten = 0;
        int intenMsg = 0;
        try {
            
            inten = i+j;
            
            intenMsg = 4 - inten;
            
            if (j >= 3){
 
                modelo.addObject("errores","Se supero el limite de intentos. Debe esperar 10 minutos para volver a intentarlo." );
                request.getSession().setAttribute("intentos",inten);
                
                return modelo;  
            }
            
            
            String compania = model.asMap().get("compania").toString();
            ErpCClientes prov = erpCClientesDao.findProveedor2(compania, usuario);
            

            if (prov == null || prov.getPassword().trim().equals("")) {
//                rq.setSuccess(false);
//                rq.setMsg("Error Acceso invalido. Intentos restantes: "+intenMsg);
//                rq.setTotal(BigDecimal.ZERO);
//                rq.setData(null);
                     if(idioma.contains("ESP"))
                    modelo.setViewName("portal_aereo/LoginPortalAereo2");
                
                else if(idioma.contains("ENG"))
                   modelo.setViewName("portal_aereo/LoginPortalAereoEng2");
                 // ModelAndView modelo = new ModelAndView("init/LoginPortalAereo2");
                modelo.addObject("errores","Error Acceso invalido. Intentos restantes: "+intenMsg);
                request.getSession().setAttribute("intentos",inten);
                
                return modelo;
            }
//            System.out.println("prov.getPassword():"+prov.getPassword());
            if (prov.getPassword().equals(password)) {
//                rq.setSuccess(true);
//                rq.setMsg("Correcto");
//                rq.setTotal(BigDecimal.ONE);
//                rq.setData(null);
                
               // ModelAndView modelo = new ModelAndView("/Polizas3/facturasAereo");
                model.addAttribute("compania", "MASAIR");
                        request.getSession().setAttribute("proveedor", prov);
                request.getSession().setAttribute("usuario", prov.getUsuario());
                request.getSession().setAttribute("compania", compania);
                request.getSession().setAttribute("idioma", idioma);
                if(idioma.contains("ESP"))
                    modelo.setViewName("init/PortalAereo");
                
                else if(idioma.contains("ENG"))
                   modelo.setViewName("init/PortalAereoEng");
              //  modelo.addObject("errores","Error Acceso invalido. Intentos restantes: "+intenMsg);
       
                //System.out.println("idiaoma: "+idioma);
                model.addAttribute("idioma", idioma);

                return modelo;
            } else {
//                System.out.println("intento 1");
//                request.getSession().setAttribute("intentos",i);
                
//                rq.setSuccess(false);
//                rq.setMsg("Error password invalido. Intentos restantes: "+intenMsg);
//                rq.setTotal(BigDecimal.ONE);
//                rq.setData(null);
                       if(idioma.contains("ESP"))
                    modelo.setViewName("portal_aereo/LoginPortalAereo2");
                
                else if(idioma.contains("ENG"))
                   modelo.setViewName("portal_aereo/LoginPortalAereoEng2");
                modelo.addObject("errores","Error password invalido. Intentos restantes: "+intenMsg);
                request.getSession().setAttribute("intentos",inten);
                
                return modelo;
            }

        } catch (Exception e) {
            //  e.printStackTrace();
                 if(idioma.contains("ESP"))
                    modelo.setViewName("portal_aereo/LoginPortalAereo2");
                
                else if(idioma.contains("ENG"))
                   modelo.setViewName("portal_aereo/LoginPortalAereoEng2");
                modelo.addObject("errores","Error password invalido. Intentos restantes: "+intenMsg);
                return modelo;
        }


    }
    
      @RequestMapping(value = "/PortalAereo2", method = RequestMethod.GET)
    public String portalAereoLogin(Model model) {
        //System.out.println("aqui1");
        model.addAttribute("compania", "MASAIR");
        model.addAttribute("idioma", "ESP");
        return "portal_aereo/LoginPortalAereo2";
    }
    
      @RequestMapping(value = "/PortalAereoEng2", method = RequestMethod.GET)
    public String portalAereoLoginEng(Model model) {
        //System.out.println("aqui1");
        model.addAttribute("compania", "MASAIR");
        model.addAttribute("idioma", "ENG");
        return "portal_aereo/LoginPortalAereoEng2";
    }
    
    @RequestMapping(value = "/registroaereo", method = {RequestMethod.GET, RequestMethod.POST})
    public ModelAndView registroPage(HttpServletRequest request, HttpServletResponse response) {

        ModelAndView mav = new ModelAndView("portal_aereo/registro");

        mav.addObject("registro", new RegsitroAereoDTO());

        return mav;
    }
    
    
    

    @RequestMapping(value = "/PortalAereo", method = RequestMethod.POST)
    public String inicioPos2t(Model model) {
        System.out.println("aqui1");
        model.addAttribute("compania", "MASAIR");
        model.addAttribute("idioma", "ESP");
        return "init/LoginPortalAereo";
    }
    
    @RequestMapping(value = "/PortalAereoTEMP", method = RequestMethod.POST)
    public String inicioPos2tTEMP(Model model) {
        System.out.println("aqui1");
        model.addAttribute("compania", "TEMP");
        model.addAttribute("idioma", "ESP");
        return "init/LoginPortalAereo";
    }

    @RequestMapping(value = "/PortalAereoEng", method = RequestMethod.GET)
    public String inicio2Eng(Model model) {
        System.out.println("aqui1");
        model.addAttribute("compania", "MASAIR");
        model.addAttribute("idioma", "ENG");
        return "init/LoginPortalAereo";
    }
    
    @RequestMapping(value = "/PortalAereoEngTEMP", method = RequestMethod.GET)
    public String inicio2EngTEMP(Model model) {
        System.out.println("aqui1");
        model.addAttribute("compania", "TEMP");
        model.addAttribute("idioma", "ENG");
        return "init/LoginPortalAereo";
    }

    @RequestMapping(value = "/PortalAereoEng", method = RequestMethod.POST)
    public String inicioPos2tEng(Model model) {
        System.out.println("aqui1");
        model.addAttribute("compania", "MASAIR");
        model.addAttribute("idioma", "ENG");
        return "init/LoginPortalAereo";
    }
    
    @RequestMapping(value = "/PortalAereoEngTEMP", method = RequestMethod.POST)
    public String inicioPos2tEngTEMP(Model model) {
        System.out.println("aqui1");
        model.addAttribute("compania", "TEMP");
        model.addAttribute("idioma", "ENG");
        return "init/LoginPortalAereo";
    }

    @RequestMapping(value = "/PortalInsurgentes", method = RequestMethod.GET)
    public String inicio2Insur(Model model) {
        System.out.println("aqui1");
        model.addAttribute("compania", "UNISRG");
        return "init/LoginPortalInsurgentes";
    }

    @RequestMapping(value = "/PortalInsurgentes", method = RequestMethod.POST)
    public String inicioPos2tInsur(Model model) {
        System.out.println("aqui1");
        model.addAttribute("compania", "UNISRG");
        return "init/LoginPortalInsurgentes";
    }
    
        @RequestMapping(value = "/PortalSGS", method = RequestMethod.GET)
    public String inicio2SGS(Model model) {
        System.out.println("aqui1");
        model.addAttribute("compania", "SGS");
        model.addAttribute("idioma", "ESP");
        return "init/LoginPortalSGS";
    }

    @RequestMapping(value = "/PortalSGS", method = RequestMethod.POST)
    public String inicioPos2SGSt(Model model) {
        System.out.println("aqui1");
        model.addAttribute("compania", "SGS");
        model.addAttribute("idioma", "ESP");
        return "init/LoginPortalSGS";
    }

    @RequestMapping(value = "/PortalSGSEng", method = RequestMethod.GET)
    public String inicio2SGSEng(Model model) {
        System.out.println("aqui1");
        model.addAttribute("compania", "SGS");
        model.addAttribute("idioma", "ENG");
        return "init/LoginPortalSGS";
    }

    @RequestMapping(value = "/PortalSGSEng", method = RequestMethod.POST)
    public String inicioPos2SGStEng(Model model) {
        System.out.println("aqui1");
        model.addAttribute("compania", "SGS");
        model.addAttribute("idioma", "ENG");
        return "init/LoginPortalSGS";
    }

       @RequestMapping(value = "/PortalDSGS", method = RequestMethod.GET)
    public String inicio2SGSD(Model model) {
        //System.out.println("aqui1");
        model.addAttribute("compania", "SGS2");
        model.addAttribute("idioma", "ESP");
        return "init/LoginPortalSGS";
    }

    @RequestMapping(value = "/PortalDSGS", method = RequestMethod.POST)
    public String inicioPos2SGSDt(Model model) {
        System.out.println("aqui1");
        model.addAttribute("compania", "SGS2");
        model.addAttribute("idioma", "ESP");
        return "init/LoginPortalSGS";
    }

    @RequestMapping(value = "/PortalDSGSEng", method = RequestMethod.GET)
    public String inicio2SGSDEng(Model model) {
        System.out.println("aqui1");
        model.addAttribute("compania", "SGS2");
        model.addAttribute("idioma", "ENG");
        return "init/LoginPortalSGS";
    }

    @RequestMapping(value = "/PortalDSGSEng", method = RequestMethod.POST)
    public String inicioPos2SGSDtEng(Model model) {
        System.out.println("aqui1");
        model.addAttribute("compania", "SGS2");
        model.addAttribute("idioma", "ENG");
        return "init/LoginPortalSGS";
    }

}
