/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.feraz.pedidos.view;

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
public class ViewPedidos {
    
         @RequestMapping(value = "/pedidos",method = RequestMethod.GET)
    public String inicio(@RequestParam(value = "compania") String compania ,@RequestParam(value = "user") String user,Model model) {      
         System.out.println("aqui1");
        model.addAttribute("compania", compania);
        model.addAttribute("usuario", user);
        
        return "init/IndexPedidos";
    }
    
     @RequestMapping(value = "/pedidos",method = RequestMethod.POST)
    public String inicioPost(@RequestParam(value = "compania") String compania ,@RequestParam(value = "user") String user,Model model) {      
         System.out.println("aqui1");
        model.addAttribute("compania", compania);
        model.addAttribute("usuario", user);
        return "init/IndexPedidos";
    }
    
//             @RequestMapping(value = "/pedidosMasair",method = RequestMethod.GET)
//    public String pedidosMasair(@RequestParam(value = "compania") String compania ,@RequestParam(value = "user") String user,@RequestParam(value = "idioma") String idioma,Model model) {      
//         System.out.println("aqui1");
//        model.addAttribute("compania", compania);
//        model.addAttribute("usuario", user);
//        model.addAttribute("idioma", idioma);
//        
//        return "init/IndexPedidosMasair";
//    }
//    
//     @RequestMapping(value = "/pedidosMasair",method = RequestMethod.POST)
//    public String pedidosMasairPost(@RequestParam(value = "compania") String compania ,@RequestParam(value = "user") String user,@RequestParam(value = "idioma") String idioma, Model model) {      
//         System.out.println("aqui1");
//        model.addAttribute("compania", compania);
//        model.addAttribute("usuario", user);
//        model.addAttribute("idioma", idioma);
//        return "init/IndexPedidosMasair";
//    }
    
               @RequestMapping(value = "/pedidosMasair",method = RequestMethod.GET)
    public String pedidosMasair(@RequestParam(value = "compania") String compania ,@RequestParam(value = "user") String user,Model model) {      
         System.out.println("aqui1");
        model.addAttribute("compania", compania);
        model.addAttribute("usuario", user);
        
        String rent;
        
        if (compania.equalsIgnoreCase("MASAIR") || compania.equalsIgnoreCase("MABLL")){
            
            rent = "init/IndexPedidosMasair";
        
        }else{
            
            rent = "init/IndexPedidosGeneral";
            
            
        }
   
        
        return rent;
    }
    
     @RequestMapping(value = "/pedidosMasair",method = RequestMethod.POST)
    public String pedidosMasairPost(@RequestParam(value = "compania") String compania ,@RequestParam(value = "user") String user, Model model) {      
         System.out.println("aqui1");
        model.addAttribute("compania", compania);
        model.addAttribute("usuario", user);
       
        String rent;
        
        if (compania.equalsIgnoreCase("MASAIR") || compania.equalsIgnoreCase("MABLL")){
            
            rent = "init/IndexPedidosMasair";
        
        }else{
            
            rent = "init/IndexPedidosGeneral";
            
            
        }
   
        
        return rent;
    }
    
              @RequestMapping(value = "/evaluaPedidos",method = RequestMethod.GET)
    public String evaluaPedidos(@RequestParam(value = "compania") String compania ,@RequestParam(value = "user") String user,Model model) {      
         System.out.println("aqui1");
        model.addAttribute("compania", compania);
        model.addAttribute("usuario", user);
        
        return "init/IndexEvalua";
    }
    
     @RequestMapping(value = "/evaluaPedidos",method = RequestMethod.POST)
    public String evaluaPedidosPost(@RequestParam(value = "compania") String compania ,@RequestParam(value = "user") String user,Model model) {      
         System.out.println("aqui1");
        model.addAttribute("compania", compania);
        model.addAttribute("usuario", user);
        return "init/IndexEvalua";
    }
    
    
            @RequestMapping(value = "/pedidosDirectorGeneral",method = RequestMethod.GET)
    public String pedidosDesarrollo(Model model) {      
         System.out.println("aqui1");
        model.addAttribute("compania", "TEMP");
        model.addAttribute("usuario", "admin_TEMPO");
        
        return "init/IndexPedidosMasair";
    }
    
     @RequestMapping(value = "/pedidosDirectorGeneral",method = RequestMethod.POST)
    public String pedidosDesarrolloPost(Model model) {      
         System.out.println("aqui1");
        model.addAttribute("compania", "TEMP");
        model.addAttribute("usuario", "admin_TEMPO");
        return "init/IndexPedidosMasair";
    }
    
            @RequestMapping(value = "/pedidosDirectorArea",method = RequestMethod.GET)
    public String pedidosDesarrolloArea(Model model) {      
         System.out.println("aqui1");
        model.addAttribute("compania", "TEMP");
        model.addAttribute("usuario", "E796");
        
        return "init/IndexPedidosMasair";
    }
    
     @RequestMapping(value = "/pedidosDirectorArea",method = RequestMethod.POST)
    public String pedidosDesarrolloAreaPost(Model model) {      
         System.out.println("aqui1");
        model.addAttribute("compania", "TEMP");
        model.addAttribute("usuario", "E796");
        return "init/IndexPedidosMasair";
    }
    
}
