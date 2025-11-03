/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.feraz.token.process;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.util.JSONPObject;
import com.feraz.token.util.ResponseQueryToken;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletResponse;
import org.jamh.data.process.ProcessDao;
import org.jamh.wf.json.model.ResponseQuery;
import org.jamh.wf.process.Querys;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.request.WebRequest;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;

/**
 *
 * @author 55555
 */
@Controller
@RequestMapping(value = "/token")
public class ControlToken {
    
    private ProcessDao processDao;
    
    @RequestMapping(value = "/prueba",method = RequestMethod.GET)
    public String inicio(
            Model model) {      
       
        
        return "token/indexToken";
    }
    
    @RequestMapping(value = "/generador", method = RequestMethod.GET,produces = "text/javascript")
    public @ResponseBody
    String idToken( @RequestParam("idCel") String idCel,@RequestParam("jsoncallback") String cb, WebRequest webRequest, Model model, HttpServletResponse response) throws JsonProcessingException {
        
        ResponseQueryToken resultToken = new ResponseQueryToken();
        
         ObjectMapper objectMapper = new ObjectMapper();
        
      // HttpHeaders headers = new HttpHeaders();
       
        if (cb != null) {
           // headers.add("Content-Type", "text/javascript");
            response.setContentType("text/javascript;charset=UTF-8");
            
        } else {
           
            //headers.add("Content-Type", "application/x-json");
            response.setContentType("application/x-json;charset=UTF-8");
        }
        
        Querys que = new Querys();
        String store = que.getSQL("CONTA_CREATE_TOKEN");
        

           Map tok = new HashMap();
              
               tok.put("idCel", idCel);
               
                   List listIdCel = processDao.getMapResult("BuscaIdToken", tok);
                   
                 
              if(listIdCel == null || listIdCel.size() <= 0){
                  resultToken.setIdToken("");
                  resultToken.setMsg("Error. No se encuentra el usuario registrado");
                  resultToken.setSuccess(false);
              }else{
                  
                 int val = processDao.execute(store, tok);
                 
                 List listIdTok = processDao.getMapResult("BuscaToken", tok);
                 
                if(listIdTok == null || listIdTok.size() <= 0){
                    
                    resultToken.setIdToken("");
                    resultToken.setMsg("Error. Error al generar token");
                    resultToken.setSuccess(false);
                }else{
                 
                    Map resultTok = (HashMap) listIdTok.get(0);
                     System.out.println("TOK_ID:" + resultTok.get("TOK_ID"));



                     resultToken.setIdToken(resultTok.get("TOK_ID").toString());
                     resultToken.setMsg("Generado. Token generado correctamente");
                     resultToken.setSuccess(true);
                     
                }
              }
              
        return objectMapper.writeValueAsString(new JSONPObject(cb,resultToken));
        //return resultToken;
        
    }

    public void setProcessDao(ProcessDao processDao) {
        this.processDao = processDao;
    }
    
    
    
}
