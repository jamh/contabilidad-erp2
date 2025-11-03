/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.feraz.facturas.webcontrolfe.view;

/**
 *
 * @author vavi
 */

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
public class CfdiPagos {
    
       @RequestMapping(value = "/cfdiPagos",method = RequestMethod.GET)
    public String inicio(@RequestParam(value = "compania") String compania ,@RequestParam(value = "user") String user,@RequestParam(value = "pago") String pago,Model model) {      
         System.out.println("aqui1");
        model.addAttribute("compania", compania);
        model.addAttribute("usuario", user);
        model.addAttribute("pago", pago);
        
        return "init/indexAppCargaFE2Pagos";
    }
    
     @RequestMapping(value = "/cfdiPagos",method = RequestMethod.POST)
    public String inicioPost(@RequestParam(value = "compania") String compania ,@RequestParam(value = "user") String user,@RequestParam(value = "pago") String pago,Model model) {      
         System.out.println("aqui1");
        model.addAttribute("compania", compania);
        model.addAttribute("usuario", user);
        model.addAttribute("pago", pago);
        
        return "init/indexAppCargaFE2Pagos";
    }
    
    @RequestMapping(value = "/complPagos",method = RequestMethod.POST)
    public String inicioPost2(@RequestParam(value = "compania") String compania ,@RequestParam(value = "user") String user,Model model) {      
         System.out.println("aqui1");
        model.addAttribute("compania", compania);
        model.addAttribute("usuario", user);
        
        return "init/indexComplemento";
    }
    
    @RequestMapping(value = "/complPagos",method = RequestMethod.GET)
    public String inicioget2(@RequestParam(value = "compania") String compania ,@RequestParam(value = "user") String user,Model model) {      
         System.out.println("aqui1");
        model.addAttribute("compania", compania);
        model.addAttribute("usuario", user);
        
        return "init/indexComplemento";
    }
   
    
}
