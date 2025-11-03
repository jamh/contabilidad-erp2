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
public class ViewOrdenCompra {
    
         @RequestMapping(value = "/ordencompra",method = RequestMethod.GET)
    public String inicio(@RequestParam(value = "compania") String compania ,@RequestParam(value = "user") String user,Model model) {      
         System.out.println("aqui1");
        model.addAttribute("compania", compania);
        model.addAttribute("usuario", user);
        
        return "init/indexOrdenCompra";
    }
    
     @RequestMapping(value = "/ordencompra",method = RequestMethod.POST)
    public String inicioPost(@RequestParam(value = "compania") String compania ,@RequestParam(value = "user") String user,Model model) {      
         System.out.println("aqui1");
        model.addAttribute("compania", compania);
        model.addAttribute("usuario", user);
        return "init/indexOrdenCompra";
    }
    
    
           @RequestMapping(value = "/ordenconsulta",method = RequestMethod.GET)
    public String inicioordenconsulta(@RequestParam(value = "compania") String compania ,@RequestParam(value = "user") String user,Model model) {      
         System.out.println("aqui1");
        model.addAttribute("compania", compania);
        model.addAttribute("usuario", user);
        
        return "init/indexOrdenConsulta";
    }
    
     @RequestMapping(value = "/ordenconsulta",method = RequestMethod.POST)
    public String inicioPostordenconsulta(@RequestParam(value = "compania") String compania ,@RequestParam(value = "user") String user,Model model) {      
         System.out.println("aqui1");
        model.addAttribute("compania", compania);
        model.addAttribute("usuario", user);
        return "init/indexOrdenConsulta";
    }
    
         @RequestMapping(value = "/ordencompraaut",method = RequestMethod.GET)
    public String ordencompraaut(@RequestParam(value = "compania") String compania ,@RequestParam(value = "user") String user,Model model) {      
         System.out.println("aqui1");
        model.addAttribute("compania", compania);
        model.addAttribute("usuario", user);
        
        return "init/AppOrdenCompraAut";
    }
    
     @RequestMapping(value = "/ordencompraaut",method = RequestMethod.POST)
    public String ordencompraaut2(@RequestParam(value = "compania") String compania ,@RequestParam(value = "user") String user,Model model) {      
         System.out.println("aqui1");
        model.addAttribute("compania", compania);
        model.addAttribute("usuario", user);
        return "init/AppOrdenCompraAut";
    }
    
       @RequestMapping(value = "/entradaAlmacen",method = RequestMethod.GET)
    public String entradaAlmacen(@RequestParam(value = "compania") String compania ,@RequestParam(value = "user") String user,Model model) {      
         System.out.println("aqui1");
        model.addAttribute("compania", compania);
        model.addAttribute("usuario", user);
        
        return "init/IndexEntradaAlmacen";
    }
    
     @RequestMapping(value = "/entradaAlmacen",method = RequestMethod.POST)
    public String entradaAlmacenPost(@RequestParam(value = "compania") String compania ,@RequestParam(value = "user") String user,Model model) {      
         System.out.println("aqui1");
        model.addAttribute("compania", compania);
        model.addAttribute("usuario", user);
        return "init/IndexEntradaAlmacen";
    }
    
       @RequestMapping(value = "/historicoSolicitudes",method = RequestMethod.GET)
    public String historicoSolicitudes(@RequestParam(value = "compania") String compania ,@RequestParam(value = "user") String user,Model model) {      
         System.out.println("aqui1");
        model.addAttribute("compania", compania);
        model.addAttribute("usuario", user);
        
        return "init/IndexHistoricoSolicitudes";
    }
    
     @RequestMapping(value = "/historicoSolicitudes",method = RequestMethod.POST)
    public String historicoSolicitudesPost(@RequestParam(value = "compania") String compania ,@RequestParam(value = "user") String user,Model model) {      
         System.out.println("aqui1");
        model.addAttribute("compania", compania);
        model.addAttribute("usuario", user);
        return "init/IndexHistoricoSolicitudes";
    }
    
           @RequestMapping(value = "/uploadCeCompras",method = RequestMethod.GET)
    public String uploadCeComprasGet(Model model) {      
//        model.addAttribute("compania", compania);
//        model.addAttribute("usuario", user);
               System.out.println("controlodar compras");
        return "init/indexUploadCompras";
        //return "init/indexConciliacion";
    }
    
    @RequestMapping(value = "/uploadCeCompras",method = RequestMethod.POST)
    public String uploadCeComprasPost4(Model model) {      
//        model.addAttribute("compania", compania);
//        model.addAttribute("usuario", user);
              System.out.println("controlodar compras");
        
        return "init/indexUploadCompras";
        //return "init/indexConciliacion";
    }
    
    
    @RequestMapping(value = "/procesoscompras",method = RequestMethod.GET)
    public String inicioprocesoscompras(@RequestParam(value = "compania") String compania ,@RequestParam(value = "user") String user,Model model) {      
        model.addAttribute("compania", compania);
        model.addAttribute("usuario", user);
        
        return "init/IndexComprasvsFacturas";
    }
    
     @RequestMapping(value = "/procesoscompras",method = RequestMethod.POST)
    public String inicioprocesoscomprasPost(@RequestParam(value = "compania") String compania ,@RequestParam(value = "user") String user,Model model) {      
        model.addAttribute("compania", compania);
        model.addAttribute("usuario", user);
        return "init/IndexComprasvsFacturas";
    }
    
    
}
