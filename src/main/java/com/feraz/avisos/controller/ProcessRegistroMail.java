/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.feraz.avisos.controller;

import com.feraz.avisos.dao.GentusDao;
import com.feraz.avisos.model.Gentus;
import com.feraz.avisos.model.GentusId;
import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import org.jamh.wf.json.model.ResponseQuery;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.request.WebRequest;

/**
 *
 * @author 55555
 */
@Controller
@RequestMapping("/")
public class ProcessRegistroMail {
    
    private GentusDao gentusDao;
    
     @RequestMapping(value = "/mail/resgistro", method = RequestMethod.POST)
    public @ResponseBody   ResponseQuery insertSystemUser(String username,String email,String password, WebRequest webRequest, Model model) {

        boolean isSave = false;
        System.out.println("username"+username);
        System.out.println("email"+email);
        System.out.println("password"+password);
       ResponseQuery rq = new ResponseQuery();
       
       try{
        
           SimpleDateFormat formatFecha = new SimpleDateFormat("dd/MM/yyyy");
           
            Date date = new Date();
           
            Calendar calendar = Calendar.getInstance();	
            calendar.setTime(date); 	
            calendar.add(Calendar.DAY_OF_YEAR, 30); 
          
           DateFormat fechaHora = new SimpleDateFormat("dd/MM/yyyy");
           String convertidoAlta = fechaHora.format(date);
           String convertidoBaja = fechaHora.format(calendar.getTime());   
           Gentus gentus = new Gentus();
           GentusId gentusId = new GentusId();
           
           
           System.out.println("Fecha de baja ");
           
           gentusId.setUsCvesis("empres");
           gentusId.setUsGrupo("adm");
           gentusId.setUsUsuario(username);
           gentus.setId(gentusId);
           gentus.setCorreo(email);
           gentus.setUsFechaAlta(formatFecha.parse(convertidoAlta));
           gentus.setUsFechaBaja(formatFecha.parse(convertidoBaja));
           gentus.setUsPasswd(password);
           gentus.setUsNombre(username);
           
            GentusId id = gentusDao.save(gentus);
            
            
       
         } catch (Exception e) {
            rq.setSuccess(false);
            rq.setMsg("Error al guardar registro");
            e.printStackTrace();
            return rq;
        }


        return rq;
    }

    public void setGentusDao(GentusDao gentusDao) {
        this.gentusDao = gentusDao;
    }
    
}
