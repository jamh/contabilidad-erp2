/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.feraz.intercos.controller;
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
 * @author vavi
 */
@Controller
@RequestMapping("/")
@SessionAttributes({"compania","usuario"})
public class ViewIntercos {
    
    
        
  
    
    
    @RequestMapping(value = "/intercosCXP",method = RequestMethod.GET)
    public String intercosCXP(@RequestParam(value = "compania") String compania ,@RequestParam(value = "user") String user,Model model) {      
         System.out.println("aqui1");
        model.addAttribute("compania", compania);
        model.addAttribute("usuario", user);
        
        return "init/indexIntercos";
    }
    
    @RequestMapping(value = "/intercosCXP",method = RequestMethod.POST)
    public String intercosCXPPost(@RequestParam(value = "compania") String compania ,@RequestParam(value = "user") String user,Model model) {      
         System.out.println("aqui1");
        model.addAttribute("compania", compania);
        model.addAttribute("usuario", user);
        
        return "init/indexIntercos";
    }

    
}



    
