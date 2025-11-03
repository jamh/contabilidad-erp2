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
public class ViewCargaTerceros {
    
    
      @RequestMapping(value = "/cargaPagosTerceros",method = RequestMethod.GET)
    public String inicio(@RequestParam(value = "compania") String compania ,@RequestParam(value = "user") String user,Model model) {      
        model.addAttribute("compania", compania);
        model.addAttribute("usuario", user);
        
        //return "init/indexUploadEdoCta";
        return "init/IndexCargaPagoTerceros";
    }
    
     @RequestMapping(value = "/cargaPagosTerceros",method = RequestMethod.POST)
    public String inicioPost(@RequestParam(value = "compania") String compania ,@RequestParam(value = "user") String user,Model model) {      
        model.addAttribute("compania", compania);
        model.addAttribute("usuario", user);
        
       // return "init/indexUploadEdoCta";
        return "init/IndexCargaPagoTerceros";
    }
    
          @RequestMapping(value = "/uploadCeTerceros",method = RequestMethod.GET)
    public String inicio3(Model model) {      
//        model.addAttribute("compania", compania);
//        model.addAttribute("usuario", user);
        
        return "init/indexUploadTerceros";
        //return "init/indexConciliacion";
    }
    
    @RequestMapping(value = "/uploadCeTerceros",method = RequestMethod.POST)
    public String inicioPost4(Model model) {      
//        model.addAttribute("compania", compania);
//        model.addAttribute("usuario", user);
        
        return "init/indexUploadTerceros";
        //return "init/indexConciliacion";
    }
    
    
}
