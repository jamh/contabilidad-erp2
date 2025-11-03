
package com.feraz.avisos.controller;

/**
 *
 * @author Feraz3
 */

import com.fasterxml.jackson.databind.ObjectMapper;
import com.feraz.avisos.dto.AvisosDTO;
import com.feraz.avisos.dao.ErpMsgAvisosDao;
import com.feraz.avisos.dao.ErpMsgAvisosNumDao;
import com.feraz.avisos.dto.AvisosResponseDTO;
import com.feraz.avisos.model.ErpMsgAvisosNum;
import com.feraz.avisos.model.ErpMsgAvisosNumId;
import com.feraz.avisos.util.RemplazaQuery;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import org.jamh.wf.json.model.ResponseQuery;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.text.ParseException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import org.jamh.data.process.ProcessDao;
import org.springframework.web.bind.annotation.RequestParam;


@Controller
@RequestMapping(value = "/avisos")
public class ProcessAvisos {
    
    private ErpMsgAvisosDao erpMsgAvisosDao;
    private ErpMsgAvisosNumDao erpMsgAvisosNumDao;
    private ProcessDao processDao;
  
    
     @RequestMapping(value = "/process",method = RequestMethod.GET)
    public @ResponseBody ResponseQuery inicio2(@RequestParam(value = "compania") String compania ,@RequestParam(value = "user") String user,Model model)throws ParseException, IOException {      
       
   
        model.addAttribute("compania", compania);
        model.addAttribute("usuario", user);
         ResponseQuery rq = new ResponseQuery();
        System.out.println("compania"+compania);
        System.out.println("usuario"+user);
        
            Map  avisosBusqueda = new HashMap();
           
              
               avisosBusqueda.put("compania", compania);
               avisosBusqueda.put("usuario", user);
              
              RemplazaQuery remplaza = new RemplazaQuery();
               
          List queryMsg = processDao.getMapResult("BuscaQueryAviso", avisosBusqueda);
//           System.out.println("QUERY0: " +queryMsg);
          if (!queryMsg.isEmpty()){
                Map query = (HashMap) queryMsg.get(0);
//                  System.out.println("QUERY: " + query.get("QUERY"));
               if (query.get("QUERY")!= null){
                   
                     String sql = remplaza.remplazaParametros(query.get("QUERY").toString(), avisosBusqueda);
                     
//                     System.out.println("Query sql: "+sql);
                     
                     avisosBusqueda.put("query", sql);
               }else{
                   avisosBusqueda.put("query","");
               }
          }else{
              
              avisosBusqueda.put("query","");
          
          }
        
          List buscaAvisosMsg = processDao.getMapResult("BuscaAvisos", avisosBusqueda);  
           
          //    System.out.println("buscaTransacciones.get(0).toString()"+buscaTransacciones.toString());
               
              if (buscaAvisosMsg.size() > 0){
              
              }else{
              
                    rq.setData(null);
                    rq.setMsg("sin datos");
                    rq.setSuccess(false);
                    return rq;
              }
              
           
              
              
              String data = buscaAvisosMsg.toString();
              int index = data.indexOf("[");
                    if (index == -1) {
                        data = "[" + data + "]";
                    }
                    System.out.println("DATA1"+data);
                    data = data.replace("=", "\":\"");
                    data = data.replace("{", "{\"");
                    data = data.replace("}", "\"}");
                    data = data.replace(", ", "\", \"");
                    data = data.replace("null", "");
                    data = data.replace("}\", \"{", "}, {");
                    data = data.replace("#", ",");
                    
                    
                 ObjectMapper mapper = new ObjectMapper();
                  System.out.println("buscaAvisosMsg.get(0).toString()"+data);
                  
                  List<AvisosDTO> listaAvisos = mapper.readValue(data,
                    mapper.getTypeFactory().constructCollectionType(List.class,
                            AvisosDTO.class));
                

                    Iterator<AvisosDTO>  it  = listaAvisos.iterator();
           
           
            List<AvisosResponseDTO> avisosResponseDTO = new ArrayList<AvisosResponseDTO>();
        
       
        //List<ErpMsgAvisos> erpMsgAvisos = erpMsgAvisosDao.FindErpAvisos(compania, user);
        
        //Iterator<ErpMsgAvisos> it = erpMsgAvisos.iterator();
       
           while(it.hasNext()){
               
               
                //ErpMsgAvisos msg = new ErpMsgAvisos();
                AvisosResponseDTO msg = new AvisosResponseDTO();
                
                AvisosDTO avisos = it.next();
                
               //  Long numVistas = new Long(avisos.getNumVistas());
                
                ErpMsgAvisosNum num = new ErpMsgAvisosNum();
                ErpMsgAvisosNumId numId = new ErpMsgAvisosNumId();
                numId.setCompania(compania);
                numId.setUsuario(user);
                numId.setSec(Integer.parseInt(avisos.getSec()));
                num.setId(numId);
               // Long numeroAvisos = erpMsgAvisosNumDao.FindNumAvisos(avisos.getId().getCompania(), avisos.getId().getUsuario(), avisos.getId().getSec());
                
               // System.out.println("Numero de avisos por usuario y sec"+ numeroAvisos);
                
              
                
               
             //   SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
	     
                
             //   Date d = new Date();
            // if (avisos.getTipoControl().equalsIgnoreCase("1")){
             //  if (d.after(avisos.getFechaIni()) && d.before(avisos.getFechaFin()) || dateFormat.format(d).equalsIgnoreCase(dateFormat.format(avisos.getFechaFin())) || dateFormat.format(d).equalsIgnoreCase(dateFormat.format(avisos.getFechaIni()))){
                   
               //    if (avisos.getEstatus().equalsIgnoreCase("1")){
                        msg.setColorFondo(avisos.getColorFondo());
                        msg.setMensaje(avisos.getMensaje());
                        msg.setTiempoVisible(avisos.getTiempoVisible());
                        msg.setTipo(avisos.getTipo());
                        msg.setTitulo(avisos.getTitulo());
                        avisosResponseDTO.add(msg);
                         
                          erpMsgAvisosNumDao.save(num);
            
         
                
               
           }
       
        
        
       if (avisosResponseDTO == null || avisosResponseDTO.isEmpty()){
           
            rq.setData(null);
            rq.setMsg("sin datos");
            rq.setSuccess(false);
       
       }else{
        
            rq.setData(avisosResponseDTO);
            rq.setMsg("datos");
            rq.setSuccess(true);
       }
        return rq;
    }

    public void setErpMsgAvisosDao(ErpMsgAvisosDao erpMsgAvisosDao) {
        this.erpMsgAvisosDao = erpMsgAvisosDao;
    }

    public void setErpMsgAvisosNumDao(ErpMsgAvisosNumDao erpMsgAvisosNumDao) {
        this.erpMsgAvisosNumDao = erpMsgAvisosNumDao;
    }

    public void setProcessDao(ProcessDao processDao) {
        this.processDao = processDao;
    }

   
    
    
     
     
}
