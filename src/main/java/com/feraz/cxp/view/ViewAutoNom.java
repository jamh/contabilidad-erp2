/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.feraz.cxp.view;

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
public class ViewAutoNom {
    
     @RequestMapping(value = "/foliosNomina",method = RequestMethod.GET)
    public String inicio(@RequestParam(value = "compania") String compania ,@RequestParam(value = "user") String user,Model model) {      
        model.addAttribute("compania", compania);
        model.addAttribute("usuario", user);
        
        return "init/AutorizaNomina";
    }
    
     @RequestMapping(value = "/foliosNomina",method = RequestMethod.POST)
    public String inicioPost(@RequestParam(value = "compania") String compania ,@RequestParam(value = "user") String user,Model model) {      
        model.addAttribute("compania", compania);
        model.addAttribute("usuario", user);
        
        return "init/AutorizaNomina";
    }
    
    
    
       @RequestMapping(value = "/foliosNominaUin",method = RequestMethod.GET)
    public String foliosNominaUin(@RequestParam(value = "compania") String compania ,@RequestParam(value = "user") String user,Model model) {      
        model.addAttribute("compania", compania);
        model.addAttribute("usuario", user);
        
        return "init/AutorizaNominaUin";
    }
    
     @RequestMapping(value = "/foliosNominaUin",method = RequestMethod.POST)
    public String foliosNominaUinPost(@RequestParam(value = "compania") String compania ,@RequestParam(value = "user") String user,Model model) {      
        model.addAttribute("compania", compania);
        model.addAttribute("usuario", user);
        
        return "init/AutorizaNominaUin";
    }
    
    
}
