/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.feraz.formula.controller;

/**
 *
 * @author 55555
 */
import com.fasterxml.jackson.databind.ObjectMapper;
import com.feraz.contabilidad.convertidor.util.ResponseQuery2;
import com.feraz.formula.dao.AuCedulasDao;
import com.feraz.formula.dao.AuCedulasDetDao;
import com.feraz.formula.dto.FormulaDTO;
import com.feraz.formula.model.AuCedulas;
import com.feraz.formula.model.AuCedulasDet;
import com.feraz.formula.model.AuCedulasDetId;
import com.feraz.formula.model.AuCedulasId;
import com.feraz.polizas3.model.ExtJSFormResult;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import org.jamh.data.process.ProcessDao;
import org.jamh.wf.json.model.ResponseQuery;
import org.jamh.wf.process.Querys;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.context.request.WebRequest;

/**
 *
 * @author 55555
 */
@Controller
@RequestMapping("/processFormula")
@SessionAttributes({"compania", "usuario"})
public class ProcessFormula {
    
     private ProcessDao processDao;
     private AuCedulasDao auCedulasDao;
     private AuCedulasDetDao auCedulasDetDao;
 
    @RequestMapping(value = "/dataFormula/{query}", method = RequestMethod.GET)
    public @ResponseBody
    ResponseQuery queryActionFormula(@PathVariable String query, WebRequest webRequest, Model model) {
        
        
        ResponseQuery rq = new ResponseQuery();
        Map parametros = processDao.paramToMap(webRequest.getParameterMap());
        parametros.put("compania", model.asMap().get("compania"));
        parametros.put("usuario", model.asMap().get("usuario"));
     //   parametros.put("grafica", model.asMap().get("grafica"));
        
         
        System.out.println("En formula");
        

        List list = processDao.getMapResult(query, parametros);
        System.out.println("En formula list: "+list);

        if (list == null  || list.size()==0) {
            rq.setSuccess(false);
            rq.setData(null);
            rq.setMsg("data null");
        } else {

            rq.setSuccess(true);
            rq.setData(list);
            rq.setMsg("Ready");
        }

        return rq;
    }
    
    @RequestMapping(value = "/dataFormula/{query}", method = RequestMethod.POST)
    public @ResponseBody
    ResponseQuery queryActionPostFormula(@PathVariable String query, WebRequest webRequest, Model model) {
        ResponseQuery rq = new ResponseQuery();
        Map parametros = processDao.paramToMap(webRequest.getParameterMap());
        parametros.put("compania", model.asMap().get("compania"));
        parametros.put("usuario", model.asMap().get("usuario"));

        System.out.println("compania"+parametros.put("compania", model.asMap().get("compania")));
        System.out.println("query"+query);
       
        System.out.println("En formula");
        
         
         System.out.println("map: "+ webRequest.getParameter("cedula"));
  
        
        
        List<FormulaDTO> listaDTO = new ArrayList<FormulaDTO>(); 
                
                   
        List list = processDao.getMapResult(query, parametros);
        System.out.println("En formula list: "+list);
        System.out.println("En formula list: "+list.size());
        
        
        
        if (list.isEmpty()  || list.size()==0) {
            
            rq.setSuccess(false);
            rq.setData(null);
            rq.setMsg("data null");
        
        }else{
        
        //Map renglon = new HashMap();
                
                 //renglon.put("compania",  model.asMap().get("compania"));
               
                 List listRenglon = processDao.getMapResult("BuscaRenglonesDetCedula", parametros);
                   
                 Map reng = (HashMap) listRenglon.get(0);
                System.out.println("RENGLON:" + reng.get("RENGLON"));
                
                int rengMax = Integer.parseInt(reng.get("RENGLON").toString());
        
         
        
           for (int i = 1; i <= rengMax;i++){
               
               FormulaDTO formulaDto= new FormulaDTO();
               formulaDto.setCedula(webRequest.getParameter("cedula").toString());
               formulaDto.setCompania(model.asMap().get("compania").toString());
               
               for(int j = 0; j<= list.size() - 1; j++){
                   
                   int renglonList = 0;
                   int columnList = 0;
                   
                    Map data = (HashMap) list.get(j);
                   
                    renglonList = Integer.parseInt(data.get("RENGLON").toString());
                    
                    columnList = Integer.parseInt(data.get("COLUMNA").toString());
                    
                   
                   if(renglonList == i){
                       
                       System.out.println("NUMERO DE RENGLON "+i);
                       System.out.println("NUMERO DE COLUMNA "+columnList );
                       
                         switch ( columnList ) {
                            case 1:
                                 System.out.println("1");
                                 formulaDto.setCedula(webRequest.getParameter("cedula").toString());
                                 formulaDto.setCompania(model.asMap().get("compania").toString());
                                 formulaDto.setCol1(data.get("CAMPO").toString());
                              break;  
                            case 2:
                                 System.out.println("2");
                                 formulaDto.setCedula(webRequest.getParameter("cedula").toString());
                                 formulaDto.setCompania(model.asMap().get("compania").toString());
                                 formulaDto.setCol2(data.get("CAMPO").toString());
                               break; 
                            case 3:
                                 System.out.println("3");
                                 formulaDto.setCedula(webRequest.getParameter("cedula").toString());
                                 formulaDto.setCompania(model.asMap().get("compania").toString());
                                 formulaDto.setCol3(data.get("CAMPO").toString());
                                break; 
                            case 4:
                                 System.out.println("4");
                                 formulaDto.setCedula(webRequest.getParameter("cedula").toString());
                                 formulaDto.setCompania(model.asMap().get("compania").toString());
                                 formulaDto.setCol4(data.get("CAMPO").toString());
                                break; 
                            case 5:
                                 System.out.println("5");
                                 formulaDto.setCedula(webRequest.getParameter("cedula").toString());
                                 formulaDto.setCompania(model.asMap().get("compania").toString());
                                 formulaDto.setCol5(data.get("CAMPO").toString());
                                break; 
                            case 6:
                                 System.out.println("6");
                                 formulaDto.setCedula(webRequest.getParameter("cedula").toString());
                                 formulaDto.setCompania(model.asMap().get("compania").toString());
                                 formulaDto.setCol6(data.get("CAMPO").toString());
                               break; 
                            case 7:
                                 System.out.println("7");
                                 formulaDto.setCedula(webRequest.getParameter("cedula").toString());
                                 formulaDto.setCompania(model.asMap().get("compania").toString());
                                 formulaDto.setCol7(data.get("CAMPO").toString());
                                break; 
                            case 8:
                                 System.out.println("8");
                                 formulaDto.setCedula(webRequest.getParameter("cedula").toString());
                                 formulaDto.setCompania(model.asMap().get("compania").toString());
                                 formulaDto.setCol8(data.get("CAMPO").toString());
                                break; 
                            case 9:
                                 System.out.println("9");
                                 formulaDto.setCedula(webRequest.getParameter("cedula").toString());
                                 formulaDto.setCompania(model.asMap().get("compania").toString());
                                 formulaDto.setCol9(data.get("CAMPO").toString());
                                 break; 
                            case 10:
                                 System.out.println("10");
                                 formulaDto.setCedula(webRequest.getParameter("cedula").toString());
                                 formulaDto.setCompania(model.asMap().get("compania").toString());
                                 formulaDto.setCol10(data.get("CAMPO").toString());
                                break; 
                            default:
                                 System.out.println("error" );
                                 break;
                            }
                   
                   }
               
               }
               
               System.out.println("------------------Agregando a lista----------------------");
               
               listaDTO.add(formulaDto);
           
           
           }
           
           System.out.println("LISTA DTO: "+listaDTO);
        
        }
        if (list == null  || list.size()==0) {
            rq.setSuccess(false);
            rq.setData(null);
            rq.setMsg("data null");
        } else {

            rq.setSuccess(true);
            rq.setData(listaDTO);
            rq.setMsg("Ready");
        }

        return rq;
    }
    
      @RequestMapping(value = "/uptDet", method = RequestMethod.POST)
    public @ResponseBody
    ResponseQuery2 updateDetForm(String data,  WebRequest webRequest, Model model) {

        ResponseQuery2 rq = new ResponseQuery2();
      //  System.out.println("data:" + data);
        SimpleDateFormat formatoDelTexto = new SimpleDateFormat("dd/MM/yyyy");
             if (data.substring(0, 1).equals("{")) {
            data = "[" + data + "]";
        }
   
        
     //   System.out.println("data:" + data);
//      
        String compania = model.asMap().get("compania").toString();
      
    

        try {
            int guardado = 0;
           
            ObjectMapper mapper = new ObjectMapper();
            DateFormat df = new SimpleDateFormat("dd/MM/yyyy");

            mapper.setDateFormat(df);
            List<FormulaDTO> lista = mapper.readValue(data,
                    mapper.getTypeFactory().constructCollectionType(List.class,
                            FormulaDTO.class));

            if (lista.isEmpty()) {
                rq.setSuccess(false);
                rq.setData(null);
                rq.setMsg("Error no existen datos que guardar");
                return rq;
            }

            AuCedulasDetId cedulasDetId = new AuCedulasDetId();
            AuCedulasDet cedulasDet = new AuCedulasDet();
            
            
            
            
            int val = 0;
            int renglon = 1;
            Iterator<FormulaDTO> it = lista.iterator();
            while (it.hasNext()) {
                 System.out.println("-------------------------------------------------------------------");
                FormulaDTO ss = it.next();
                
                if (val == 0){
                   
                    auCedulasDetDao.deleteCedula(compania, ss.getCedula());
                }
                
                 val = 1;
                
                 if (ss.getCol1() != null && !ss.getCol1().equalsIgnoreCase("")){
                     
                    cedulasDetId.setCompania(compania);
                    cedulasDetId.setCedula(ss.getCedula());
                    cedulasDetId.setRenglon(renglon);
                    cedulasDetId.setColumna(1);
                    cedulasDet.setId(cedulasDetId);
                    cedulasDet.setCampo(ss.getCol1());
                    auCedulasDetDao.save(cedulasDet);

                 }
                 if (ss.getCol2() != null && !ss.getCol2().equalsIgnoreCase("")){
                     
                     System.out.println("ss.getCol2(): "+ss.getCol2());
                     
                    cedulasDetId.setCompania(compania);
                    cedulasDetId.setCedula(ss.getCedula());
                    cedulasDetId.setRenglon(renglon);
                    cedulasDetId.setColumna(2);
                    cedulasDet.setId(cedulasDetId);
                    cedulasDet.setCampo(ss.getCol2());
                    auCedulasDetDao.save(cedulasDet);

                 }
                 if (ss.getCol3() != null && !ss.getCol3().equalsIgnoreCase("")){
                     
                    cedulasDetId.setCompania(compania);
                    cedulasDetId.setCedula(ss.getCedula());
                    cedulasDetId.setRenglon(renglon);
                    cedulasDetId.setColumna(3);
                    cedulasDet.setId(cedulasDetId);
                    cedulasDet.setCampo(ss.getCol3());
                    auCedulasDetDao.save(cedulasDet);

                 }
                 if (ss.getCol4() != null && !ss.getCol4().equalsIgnoreCase("")){
                     
                    cedulasDetId.setCompania(compania);
                    cedulasDetId.setCedula(ss.getCedula());
                    cedulasDetId.setRenglon(renglon);
                    cedulasDetId.setColumna(4);
                    cedulasDet.setId(cedulasDetId);
                    cedulasDet.setCampo(ss.getCol4());
                    auCedulasDetDao.save(cedulasDet);

                 }
                 if (ss.getCol5() != null && !ss.getCol5().equalsIgnoreCase("")){
                     
                    cedulasDetId.setCompania(compania);
                    cedulasDetId.setCedula(ss.getCedula());
                    cedulasDetId.setRenglon(renglon);
                    cedulasDetId.setColumna(5);
                    cedulasDet.setId(cedulasDetId);
                    cedulasDet.setCampo(ss.getCol5());
                    auCedulasDetDao.save(cedulasDet);

                 }
                 if (ss.getCol6() != null && !ss.getCol6().equalsIgnoreCase("")){
                     
                    cedulasDetId.setCompania(compania);
                    cedulasDetId.setCedula(ss.getCedula());
                    cedulasDetId.setRenglon(renglon);
                    cedulasDetId.setColumna(6);
                    cedulasDet.setId(cedulasDetId);
                    cedulasDet.setCampo(ss.getCol6());
                    auCedulasDetDao.save(cedulasDet);

                 }
                 if (ss.getCol7() != null && !ss.getCol7().equalsIgnoreCase("")){
                     
                    cedulasDetId.setCompania(compania);
                    cedulasDetId.setCedula(ss.getCedula());
                    cedulasDetId.setRenglon(renglon);
                    cedulasDetId.setColumna(7);
                    cedulasDet.setId(cedulasDetId);
                    cedulasDet.setCampo(ss.getCol7());
                    auCedulasDetDao.save(cedulasDet);

                 }
                 if (ss.getCol8() != null && !ss.getCol8().equalsIgnoreCase("")){
                     
                    cedulasDetId.setCompania(compania);
                    cedulasDetId.setCedula(ss.getCedula());
                    cedulasDetId.setRenglon(renglon);
                    cedulasDetId.setColumna(8);
                    cedulasDet.setId(cedulasDetId);
                    cedulasDet.setCampo(ss.getCol8());
                    auCedulasDetDao.save(cedulasDet);

                 }
                 if (ss.getCol9() != null && !ss.getCol9().equalsIgnoreCase("")){
                     
                    cedulasDetId.setCompania(compania);
                    cedulasDetId.setCedula(ss.getCedula());
                    cedulasDetId.setRenglon(renglon);
                    cedulasDetId.setColumna(9);
                    cedulasDet.setId(cedulasDetId);
                    cedulasDet.setCampo(ss.getCol9());
                    auCedulasDetDao.save(cedulasDet);

                 }
                 if (ss.getCol10() != null && !ss.getCol10().equalsIgnoreCase("")){
                     
                    cedulasDetId.setCompania(compania);
                    cedulasDetId.setCedula(ss.getCedula());
                    cedulasDetId.setRenglon(renglon);
                    cedulasDetId.setColumna(10);
                    cedulasDet.setId(cedulasDetId);
                    cedulasDet.setCampo(ss.getCol10());
                    auCedulasDetDao.save(cedulasDet);

                 }
           

                 renglon = renglon + 1;
             }
            


            if (guardado == 0) {

                rq.setSuccess(true);
                rq.setData(null);
              //  rq.setDataErr(lisErr2);
                rq.setMsg("Guardados Correctamente");
            } else {
                rq.setSuccess(false);
                rq.setData(null);
               // rq.setDataErr(lisErr2);
                rq.setMsg("Error al guardar detalles del Convertidor");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return rq;

    }
    
    @RequestMapping(value = "/saveMaestroFormula", method = RequestMethod.POST)
    public @ResponseBody
    String updateMaestro(String data, WebRequest webRequest, Model model) {  
         
         
          Map parametros = processDao.paramToMap(webRequest.getParameterMap());
          
          System.out.println("CEDULA: "+parametros.get("CEDULA").toString());
          
          
         ExtJSFormResult extjsFormResult = new ExtJSFormResult();
         ResponseQuery rq = new ResponseQuery();
                boolean isSave = true;
               
                if (model.asMap().get("compania") == null) {
                extjsFormResult.setMessage("La sesion no es valida.");
                extjsFormResult.setSuccess(false);
            
               return extjsFormResult.toString();
        }
                
        try{
            
           
            AuCedulas auCedulas = new AuCedulas();            
            AuCedulasId auCedulasId = new AuCedulasId();
            
            auCedulasId.setCompania(model.asMap().get("compania").toString());
            auCedulasId.setCedula(parametros.get("CEDULA").toString());
            auCedulas.setId(auCedulasId);
            auCedulas.setDescripcion(parametros.get("DESCRIPCION").toString());
            auCedulas.setNombre(parametros.get("NOMBRE").toString());
            
           
            if (parametros.get("BANDERA").toString().equalsIgnoreCase("")){
                AuCedulasId result = auCedulasDao.save(auCedulas);


                    if (result == null) {
                           isSave = false;
                           extjsFormResult.setMessage("Error al guardar los datos");
                           extjsFormResult.setSuccess(isSave);
                           return extjsFormResult.toString();
                       } else {
                           isSave = true;
                           extjsFormResult.setMessage(""+parametros.get("CEDULA").toString());
                           extjsFormResult.setSuccess(isSave);
                          // return rq;
                           return extjsFormResult.toString();

                    }
         }else{

//          
                     boolean result = auCedulasDao.update(auCedulas);
//           
     //    
         
                        if (result == false) {
                               isSave = false;
                               extjsFormResult.setMessage("Error al guardar los datos");
                               extjsFormResult.setSuccess(isSave);
                               return extjsFormResult.toString();
                           } else {
                               isSave = true;
                                extjsFormResult.setMessage(""+parametros.get("CEDULA").toString());
                               extjsFormResult.setSuccess(isSave);
                              // return rq;
                               return extjsFormResult.toString();
                    //}
                        }
//           
//           
//           
           }

        } catch (Exception e) {
            e.printStackTrace();
//            isSave = false;
        }




        extjsFormResult.setSuccess(isSave);
//
        return extjsFormResult.toString();

     
     
     }
    
     @RequestMapping(value = "/borraDetFormula", method = RequestMethod.POST)
    public @ResponseBody
    ResponseQuery BorraFormulaRenglon(String data,@RequestParam("cedula") String cedula, 
    @RequestParam("renglon") String renglon, WebRequest webRequest, Model model) {
        boolean isSave = false;
         SimpleDateFormat formatoDelTexto2 = new SimpleDateFormat("dd/MM/yyyy");
        
        ResponseQuery rq = new ResponseQuery();

       
        
       Map parametros = processDao.paramToMap(webRequest.getParameterMap());
        parametros.put("compania", model.asMap().get("compania"));
//        parametros.put("usuario", model.asMap().get("usuario"));
       
      
       
        try {
            
               boolean result = auCedulasDetDao.deleteCedulaRenglon(model.asMap().get("compania").toString(), cedula, Integer.parseInt(renglon));            
            
            
            
   
                 if (result == true){
                     rq.setMsg("Borrado Correctamente");
                     rq.setSuccess(true);
                 }else{
                     
                     rq.setMsg("Error al borra");
                     rq.setSuccess(false);
                 
                 }
              
             
                
               
        } catch (Exception e) {
            isSave = false;
            e.printStackTrace();
        }

       

        return rq;
    }
    
     @RequestMapping(value = "/borraCedula", method = RequestMethod.POST)
    public @ResponseBody
    ResponseQuery BorraCedula(String data,@RequestParam("compania") String compania, 
    @RequestParam("cedula") String cedula, WebRequest webRequest, Model model) {
        boolean isSave = false;
         SimpleDateFormat formatoDelTexto2 = new SimpleDateFormat("dd/MM/yyyy");
        
        ResponseQuery rq = new ResponseQuery();

       
        
       Map parametros = processDao.paramToMap(webRequest.getParameterMap());
        parametros.put("compania", model.asMap().get("compania"));
//        parametros.put("usuario", model.asMap().get("usuario"));
       
      
       
        try {
            
              AuCedulas auCedulas = new AuCedulas();
              AuCedulasId auCedulasId = new AuCedulasId();
              
              auCedulasId.setCompania(compania);
              auCedulasId.setCedula(cedula);
              auCedulas.setId(auCedulasId);
              
               boolean result2 = auCedulasDao.delete(auCedulas); 
            
               boolean result = auCedulasDetDao.deleteCedula(compania, cedula);            
            
            
            
   
                 if (result == true && result2 == true){
                     rq.setMsg("Borrado Correctamente");
                     rq.setSuccess(true);
                 }else{
                     
                     rq.setMsg("Error al borra");
                     rq.setSuccess(false);
                 
                 }
              
             
                
               
        } catch (Exception e) {
            isSave = false;
            e.printStackTrace();
        }

       

        return rq;
    }

         @RequestMapping(value = "/copiarCedula", method = RequestMethod.POST)
    public @ResponseBody
    ResponseQuery copiarCedula(String data,@RequestParam("caso") String caso,
            @RequestParam("COMPANIA") String compania,
            @RequestParam("CEDULA") String cedula,
            @RequestParam("NOMBRE") String nombre,
            @RequestParam("DESCRIPCION") String decripcion, WebRequest webRequest, Model model) {
        boolean isSave = false;
         SimpleDateFormat formatoDelTexto2 = new SimpleDateFormat("dd/MM/yyyy");
        
        ResponseQuery rq = new ResponseQuery();

       
        
       Map parametros = processDao.paramToMap(webRequest.getParameterMap());
        parametros.put("compania", model.asMap().get("compania"));
//        parametros.put("usuario", model.asMap().get("usuario"));
       
      System.out.println("caso: "+caso);
      System.out.println("compania: "+compania);
      System.out.println("cedula: "+cedula);
       
        try {
            
             AuCedulas auCedulas = new AuCedulas();            
            AuCedulasId auCedulasId = new AuCedulasId();
            
            auCedulasId.setCompania(model.asMap().get("compania").toString());
            auCedulasId.setCedula(caso);
            auCedulas.setId(auCedulasId);
            auCedulas.setDescripcion(decripcion);
            auCedulas.setNombre(nombre+ " (copia)");
            
               
          
                AuCedulasId result = auCedulasDao.save(auCedulas);
                
                Querys que = new Querys();
         
                String store = que.getSQL("CopiaCedula");
                
                 Map param = new HashMap();
             
                param.put("compania", compania);
                param.put("cedula", cedula);
                param.put("cedula_nueva",caso);
          
               int val = processDao.execute(store, param);
              
               
               System.out.println(val);

           if (val > 0 && result != null) {

                // lista.add(curso);
                rq.setSuccess(true);
                rq.setData(null);
                rq.setMsg("Copiado Exitoso");
            } else {
                rq.setSuccess(false);
                rq.setData(null);
                rq.setMsg("Error al Copiar");
           }
            
            
                
               
        } catch (Exception e) {
            isSave = false;
            e.printStackTrace();
        }

       

        return rq;
    }
    public void setProcessDao(ProcessDao processDao) {
        this.processDao = processDao;
    }

    public void setAuCedulasDao(AuCedulasDao auCedulasDao) {
        this.auCedulasDao = auCedulasDao;
    }

    public void setAuCedulasDetDao(AuCedulasDetDao auCedulasDetDao) {
        this.auCedulasDetDao = auCedulasDetDao;
    }
    
    
    
}
