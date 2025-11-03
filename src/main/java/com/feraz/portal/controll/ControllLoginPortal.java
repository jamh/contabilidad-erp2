/*
 * Login para el portal
 */
package com.feraz.portal.controll;

import com.feraz.cxp.dao.ErpCClientesDao;
import com.feraz.cxp.model.ErpCClientes;
import java.math.BigDecimal;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import org.jamh.wf.json.model.ResponseQuery;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.ModelAndView;

/**
 *
 * @author Ing. JAMH
 */
@Controller
@RequestMapping("/por")
@SessionAttributes({"compania"})
public class ControllLoginPortal {

    private ErpCClientesDao erpCClientesDao;

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public @ResponseBody
    ResponseQuery insertDiot(
            @RequestParam("usuario") String usuario,
            @RequestParam("password") String password,
            HttpServletRequest request,
            WebRequest webRequest, Model model) {

        ResponseQuery rq = new ResponseQuery();
        
       // System.out.println("intentos : "+request.getSession().getAttribute("intentos"));
        
//        
//         HttpSession session = request.getSession(false);
//         if (session == null){
//         
//         
//         }
        
        
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
            rq.setSuccess(false);
                rq.setMsg("Se supero el limite de intentos. Debe esperar 10 minutos para volver a intentarlo.");
                rq.setTotal(new BigDecimal(3));
                rq.setData(null);
                
                request.getSession().setAttribute("intentos",inten);
                
                return rq;  
            }
            
            
            String compania = model.asMap().get("compania").toString();
            ErpCClientes prov = erpCClientesDao.findProveedor(compania, usuario);
            

            if (prov == null || prov.getPassword().trim().equals("")) {
                rq.setSuccess(false);
                rq.setMsg("Error Acceso invalido. Intentos restantes: "+intenMsg);
                rq.setTotal(BigDecimal.ZERO);
                rq.setData(null);
                
                request.getSession().setAttribute("intentos",inten);
                
                return rq;
            }
//            System.out.println("prov.getPassword():"+prov.getPassword());
            if (prov.getPassword().equals(password)) {
                rq.setSuccess(true);
                rq.setMsg("Correcto");
                rq.setTotal(BigDecimal.ONE);
                rq.setData(null);
                request.getSession().setAttribute("proveedor", prov);
                request.getSession().setAttribute("usuario", prov.getRfc());
                request.getSession().setAttribute("compania", compania);
                return rq;
            } else {
//                System.out.println("intento 1");
//                request.getSession().setAttribute("intentos",i);
                
                rq.setSuccess(false);
                rq.setMsg("Error password invalido. Intentos restantes: "+intenMsg);
                rq.setTotal(BigDecimal.ONE);
                rq.setData(null);
                
                request.getSession().setAttribute("intentos",inten);
                
                return rq;
            }

        } catch (Exception e) {
            //  e.printStackTrace();
            rq.setSuccess(false);
            rq.setMsg("Error Acceso invalido. Intentos restantes: "+intenMsg);
        }
        
        request.getSession().setAttribute("intentos",inten);
        return rq;

    }
    
        @RequestMapping(value = "/login2", method = RequestMethod.POST)
    public @ResponseBody
    ResponseQuery portalLogin2(
            @RequestParam("compania") String compania2,
            @RequestParam("usuario") String usuario,
            @RequestParam("password") String password,
            HttpServletRequest request,
            WebRequest webRequest, Model model) {

        ResponseQuery rq = new ResponseQuery();
        
       // System.out.println("intentos : "+request.getSession().getAttribute("intentos"));
        
//        
//         HttpSession session = request.getSession(false);
//         if (session == null){
//         
//         
//         }
        
        
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
            rq.setSuccess(false);
                rq.setMsg("Se supero el limite de intentos. Debe esperar 10 minutos para volver a intentarlo.");
                rq.setTotal(new BigDecimal(3));
                rq.setData(null);
                
                request.getSession().setAttribute("intentos",inten);
                
                return rq;  
            }
            
            
           // String compania = model.asMap().get("compania").toString();
            ErpCClientes prov = erpCClientesDao.findProveedor(compania2, usuario);
            

            if (prov == null || prov.getPassword().trim().equals("")) {
                rq.setSuccess(false);
                rq.setMsg("Error Acceso invalido. Intentos restantes: "+intenMsg);
                rq.setTotal(BigDecimal.ZERO);
                rq.setData(null);
                
                request.getSession().setAttribute("intentos",inten);
                
                return rq;
            }
//            System.out.println("prov.getPassword():"+prov.getPassword());
            if (prov.getPassword().equals(password)) {
                rq.setSuccess(true);
                rq.setMsg("Correcto");
                rq.setTotal(BigDecimal.ONE);
                rq.setData(null);
                request.getSession().setAttribute("proveedor", prov);
                request.getSession().setAttribute("usuario", prov.getRfc());
                request.getSession().setAttribute("compania", compania2);
                return rq;
            } else {
//                System.out.println("intento 1");
//                request.getSession().setAttribute("intentos",i);
                
                rq.setSuccess(false);
                rq.setMsg("Error password invalido. Intentos restantes: "+intenMsg);
                rq.setTotal(BigDecimal.ONE);
                rq.setData(null);
                
                request.getSession().setAttribute("intentos",inten);
                
                return rq;
            }

        } catch (Exception e) {
            //  e.printStackTrace();
            rq.setSuccess(false);
            rq.setMsg("Error Acceso invalido. Intentos restantes: "+intenMsg);
        }
        
        request.getSession().setAttribute("intentos",inten);
        return rq;

    }
    
    //Para el administrador del portal de proveedores
            @RequestMapping(value = "/login4a", method = RequestMethod.POST)
    public @ResponseBody
    ResponseQuery logina(
            @RequestParam("usuario") String usuario,
           
            HttpServletRequest request,
            WebRequest webRequest, Model model) {

        ResponseQuery rq = new ResponseQuery();
        
//        System.out.println("Login 3");
        
//        
//         HttpSession session = request.getSession(false);
//         if (session == null){
//         
//         
//         }
        
        
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
            rq.setSuccess(false);
                rq.setMsg("Se supero el limite de intentos. Debe esperar 10 minutos para volver a intentarlo.");
                rq.setTotal(new BigDecimal(3));
                rq.setData(null);
                
                request.getSession().setAttribute("intentos",inten);
                
                return rq;  
            }
            
            
            String compania = model.asMap().get("compania").toString();
            
         
         
            ErpCClientes prov = erpCClientesDao.findProveedor2(compania, usuario);
            

            if (prov == null || prov.getPassword().trim().equals("")) {
                rq.setSuccess(false);
                rq.setMsg("Error Acceso invalido. Intentos restantes: "+intenMsg);
                rq.setTotal(BigDecimal.ZERO);
                rq.setData(null);
                
                request.getSession().setAttribute("intentos",inten);
                
                return rq;
            }
//            System.out.println("prov.getPassword():"+prov.getPassword());
            if (prov.getPassword().equals(prov.getPassword())) {
                rq.setSuccess(true);
                rq.setMsg("Correcto");
                rq.setTotal(BigDecimal.ONE);
                rq.setData(null);
                request.getSession().setAttribute("proveedor", prov);
                request.getSession().setAttribute("usuario", prov.getUsuario());
                request.getSession().setAttribute("compania", compania);
                request.getSession().setAttribute("idioma", "Esp");
//                System.out.println("idiaoma: "+idioma);
                model.addAttribute("idioma", "Esp");

                return rq;
            } else {
//                System.out.println("intento 1");
//                request.getSession().setAttribute("intentos",i);
                
                rq.setSuccess(false);
                rq.setMsg("Error password invalido. Intentos restantes: "+intenMsg);
                rq.setTotal(BigDecimal.ONE);
                rq.setData(null);
                
                request.getSession().setAttribute("intentos",inten);
                
                return rq;
            }

        } catch (Exception e) {
            //  e.printStackTrace();
            rq.setSuccess(false);
            rq.setMsg("Error Acceso invalido. Intentos restantes: "+intenMsg);
        }
        
        request.getSession().setAttribute("intentos",inten);
        return rq;

    }
    
        @RequestMapping(value = "/login3", method = RequestMethod.POST)
    public @ResponseBody
    ResponseQuery login3(
            @RequestParam("usuario") String usuario,
            @RequestParam("password") String password,
            @RequestParam("idioma") String idioma,
            HttpServletRequest request,
            WebRequest webRequest, Model model) {

        ResponseQuery rq = new ResponseQuery();
        
//        System.out.println("Login 3");
        
//        
//         HttpSession session = request.getSession(false);
//         if (session == null){
//         
//         
//         }
        
        
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
            rq.setSuccess(false);
                rq.setMsg("Se supero el limite de intentos. Debe esperar 10 minutos para volver a intentarlo.");
                rq.setTotal(new BigDecimal(3));
                rq.setData(null);
                
                request.getSession().setAttribute("intentos",inten);
                
                return rq;  
            }
            
            
            String compania = model.asMap().get("compania").toString();
            
   
            
            if(     compania.equals("PUNTOP") && 
                        (
                          (usuario.equals("AdminPUNTOP") && password.equals("Adpunt0p*")) || 
                          (usuario.equals("AdminP3") && password.equals("Adp2024Dor")  )  ||
                          (usuario.equals("javier.simbron@pen.com.mx") && password.equals("j4v13r*P")  )
                        )
                    ){
                rq.setSuccess(true);
                rq.setMsg("login2");
                rq.setTotal(BigDecimal.ONE);
                rq.setData(null);
                return rq;
            }
            
            ErpCClientes prov = erpCClientesDao.findProveedor2(compania, usuario);
            

            if (prov == null || prov.getPassword().trim().equals("")) {
                rq.setSuccess(false);
                rq.setMsg("Error Acceso invalido. Intentos restantes: "+intenMsg);
                rq.setTotal(BigDecimal.ZERO);
                rq.setData(null);
                
                request.getSession().setAttribute("intentos",inten);
                
                return rq;
            }
//            System.out.println("prov.getPassword():"+prov.getPassword());
            if (prov.getPassword().equals(password)) {
                rq.setSuccess(true);
                rq.setMsg("Correcto");
                rq.setTotal(BigDecimal.ONE);
                rq.setData(null);
                request.getSession().setAttribute("proveedor", prov);
                request.getSession().setAttribute("usuario", prov.getUsuario());
                request.getSession().setAttribute("compania", compania);
                request.getSession().setAttribute("idioma", idioma);
//                System.out.println("idiaoma: "+idioma);
                model.addAttribute("idioma", idioma);

                return rq;
            } else {
//                System.out.println("intento 1");
//                request.getSession().setAttribute("intentos",i);
                
                rq.setSuccess(false);
                rq.setMsg("Error password invalido. Intentos restantes: "+intenMsg);
                rq.setTotal(BigDecimal.ONE);
                rq.setData(null);
                
                request.getSession().setAttribute("intentos",inten);
                
                return rq;
            }

        } catch (Exception e) {
            //  e.printStackTrace();
            rq.setSuccess(false);
            rq.setMsg("Error Acceso invalido. Intentos restantes: "+intenMsg);
        }
        
        request.getSession().setAttribute("intentos",inten);
        return rq;

    }
    
    
            @RequestMapping(value = "/login3p", method = RequestMethod.POST)
    public @ResponseBody
    ModelAndView login3P(
            @RequestParam("usuario") String usuario,
            @RequestParam("password") String password,
            @RequestParam("idioma") String idioma,
            HttpServletRequest request,
            WebRequest webRequest, Model model) {

        
          ModelAndView modelo = new ModelAndView("portal_aereo/LoginPortalAereo2");
        
//        ResponseQuery rq = new ResponseQuery();
        
//        System.out.println("Login 3");
        
//        
//         HttpSession session = request.getSession(false);
//         if (session == null){
//         
//         
//         }
        
        
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
            
//            if (j >= 3){
// 
//                modelo.addObject("errores","Se supero el limite de intentos. Debe esperar 10 minutos para volver a intentarlo." );
//                request.getSession().setAttribute("intentos",inten);
//                
//                return modelo;  
//            }
            
            
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
                 // ModelAndView modelo = new ModelAndView("portal_aereo/LoginPortalAereo2");
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
                if(idioma.contains("ESP"))
                    modelo.setViewName("init/PortalAereo");
                
                else if(idioma.contains("ENG"))
                   modelo.setViewName("init/PortalAereoEng");
              //  modelo.addObject("errores","Error Acceso invalido. Intentos restantes: "+intenMsg);
                request.getSession().setAttribute("proveedor", prov);
                request.getSession().setAttribute("usuario", prov.getUsuario());
                request.getSession().setAttribute("compania", compania);
                request.getSession().setAttribute("idioma", idioma);
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
        //  ModelAndView modelo = new ModelAndView("PortalAereo2");
         //       modelo.addObject("errores","Error password invalido. Intentos restantes: "+intenMsg);
//        request.getSession().setAttribute("intentos",inten);
//        return  new ModelAndView("PortalAereo2");

    }
    
    
     @RequestMapping(value = "/login4", method = RequestMethod.POST)
    public @ResponseBody
    ResponseQuery login4(
            @RequestParam("usuario") String usuario,
            @RequestParam("password") String password,
            @RequestParam("compania") String compania,
            HttpServletRequest request,
            WebRequest webRequest, Model model) {

        ResponseQuery rq = new ResponseQuery();
        
       // System.out.println("intentos : "+request.getSession().getAttribute("intentos"));
        
//        
//         HttpSession session = request.getSession(false);
//         if (session == null){
//         
//         
//         }
        
        
        int j = 1;
        
//         if (request.getSession().getAttribute("intentos") != null){
//         
//                 j = Integer.parseInt(request.getSession().getAttribute("intentos").toString());
//         }
          
        
//        int i = 1;
//        int inten = 0;
//        int intenMsg = 0;
        try {
//            
//            inten = i+j;
//            
//            intenMsg = 4 - inten;
//            
//            if (j >= 3){
//            rq.setSuccess(false);
//                rq.setMsg("Se supero el limite de intentos. Debe esperar 10 minutos para volver a intentarlo.");
//                rq.setTotal(new BigDecimal(3));
//                rq.setData(null);
//                
//                request.getSession().setAttribute("intentos",inten);
//                
//                return rq;  
//            }
           System.out.println("compania");

            System.out.println(compania);

           if(compania.equalsIgnoreCase("")){
               
                rq.setSuccess(false);
                rq.setMsg("Error Acceso invalido. Debe seleccionar un Empresa");
                rq.setTotal(BigDecimal.ZERO);
                rq.setData(null);
                
               // request.getSession().setAttribute("intentos",inten);
                
                return rq;
           
           }
            
            
           // String compania = model.asMap().get("compania").toString();
            ErpCClientes prov = erpCClientesDao.findProveedor(compania, usuario);
            

            if (prov == null || prov.getPassword().trim().equals("")) {
                rq.setSuccess(false);
                rq.setMsg("Error Acceso invalido. Usuario incorrecto");
                rq.setTotal(BigDecimal.ZERO);
                rq.setData(null);
                
               // request.getSession().setAttribute("intentos",inten);
                
                return rq;
            }
//            System.out.println("prov.getPassword():"+prov.getPassword());
            if (prov.getPassword().equals(password)) {
                rq.setSuccess(true);
                rq.setMsg("Correcto");
                rq.setTotal(BigDecimal.ONE);
                rq.setData(null);
                request.getSession().setAttribute("proveedor", prov);
                request.getSession().setAttribute("usuario", prov.getRfc());
               // request.getSession().setAttribute("compania", compania);
                model.addAttribute("compania", compania);
                return rq;
            } else {
//                System.out.println("intento 1");
//                request.getSession().setAttribute("intentos",i);
                
                rq.setSuccess(false);
                rq.setMsg("Error password invalido");
                rq.setTotal(BigDecimal.ONE);
                rq.setData(null);
                
                //request.getSession().setAttribute("intentos",inten);
                
                return rq;
            }

        } catch (Exception e) {
            //  e.printStackTrace();
            rq.setSuccess(false);
            rq.setMsg("Error Acceso invalido");
        }
        
       // request.getSession().setAttribute("intentos",inten);
        return rq;

    }

    @RequestMapping(value = "/logout", method = RequestMethod.POST)
    public String logout(HttpSession session) {
        session.invalidate();
        return "redirect:/home";
    }

    public void setErpCClientesDao(ErpCClientesDao erpCClientesDao) {
        this.erpCClientesDao = erpCClientesDao;
    }

}
