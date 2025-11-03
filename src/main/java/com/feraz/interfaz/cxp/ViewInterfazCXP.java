/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.feraz.interfaz.cxp;

/**
 *
 * @author vavi
 */

import com.feraz.interfaz.cxc.*;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;

/**
 *
 * @author 55555
 */
@Controller
@RequestMapping("/")
@SessionAttributes({"compania","usuario"})
public class ViewInterfazCXP {
    
      @RequestMapping(value = "/interfazCXP",method = RequestMethod.GET)
    public String inicio(@RequestParam(value = "compania") String compania ,@RequestParam(value = "user") String user,Model model) {      
         System.out.println("aqui1");
        model.addAttribute("compania", compania);
        model.addAttribute("usuario", user);
        
        return "init/indexInterfazCXP";
    }
    
     @RequestMapping(value = "/interfazCXP",method = RequestMethod.POST)
    public String inicioPost(@RequestParam(value = "compania") String compania ,@RequestParam(value = "user") String user,Model model) {      
         System.out.println("aqui1");
        model.addAttribute("compania", compania);
        model.addAttribute("usuario", user);
        return "init/indexInterfazCXP";
    }
    
}
