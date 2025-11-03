/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.feraz.ordencompra.view;

/**
 *
 * @author vavi
 */
import com.feraz.cxp.view.*;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;

/**
 *
 * @author vavi
 */

@Controller
@RequestMapping("/")
@SessionAttributes({"compania","usuario"})
public class ViewAutoFactOrden {
    
    @RequestMapping(value = "/facturasOrden",method = RequestMethod.GET)
    public String inicio(@RequestParam(value = "compania") String compania ,@RequestParam(value = "user") String user,Model model) {      
        model.addAttribute("compania", compania);
        model.addAttribute("usuario", user);
        
        return "init/AutorizaFactOrdenes";
    }
    
     @RequestMapping(value = "/facturasOrden",method = RequestMethod.POST)
    public String inicioPost(@RequestParam(value = "compania") String compania ,@RequestParam(value = "user") String user,Model model) {      
        model.addAttribute("compania", compania);
        model.addAttribute("usuario", user);
        
        return "init/AutorizaFactOrdenes";
    }
    
    @RequestMapping(value = "/facturasOrdenDArea",method = RequestMethod.GET)
    public String inicioDirArea(Model model) {      
        model.addAttribute("compania", "TEMP");
        model.addAttribute("usuario", "E796");
        
        return "init/AutorizaFactOrdenes";
    }
    
     @RequestMapping(value = "/facturasOrdenDArea",method = RequestMethod.POST)
    public String inicioDirAreaPost(Model model) {      
        model.addAttribute("compania", "TEMP");
        model.addAttribute("usuario", "E796");
        
        return "init/AutorizaFactOrdenes";
    }
    
    @RequestMapping(value = "/facturasOrdenDGeneral",method = RequestMethod.GET)
    public String inicioDirGeneral(Model model) {      
        model.addAttribute("compania", "TEMP");
        model.addAttribute("usuario", "admin_TEMPO");
        
        return "init/AutorizaFactOrdenes";
    }
    
     @RequestMapping(value = "/facturasOrdenDGeneral",method = RequestMethod.POST)
    public String inicioDirGeneralPost(Model model) {      
        model.addAttribute("compania", "TEMP");
        model.addAttribute("usuario", "admin_TEMPO");
        
        return "init/AutorizaFactOrdenes";
    }
    
}
