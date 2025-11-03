
package com.feraz.facturas.webcontrolfe.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.feraz.facturas.webcontrolfe.dao1.ErpFeXMLDao;
import com.feraz.facturas.webcontrolfe.dto.DirectoryDto;
import com.feraz.facturas.webcontrolfe.model.ErpFeXML;
import com.feraz.facturas.webcontrolfe.procesa.CargaFacturas;
import java.io.File;
import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import org.jamh.data.process.ProcessDao;
import org.jamh.wf.json.dto.ResultData;
import org.jamh.wf.json.model.ResponseQuery;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.context.request.WebRequest;

/**
 *
 * @author Ing. JAMH
 */

@Controller
@RequestMapping(value = "/dirdatacxp")
@SessionAttributes({"compania", "usuario"})
public class DirectoryControllerCXP {
    
    @Value("${document.file.dirOutDocumentCXP}")
    private String dirOutFileDocument;
    @Value("${document.file.dirURLOutDocumentCXP}")
    private String dirUrlOutDocument;
    @Value("${document.file.maxZiseCXP}")
    private String maxSizeDocument;
    
    private CargaFacturas cargaFacturas;
    
    private ErpFeXMLDao erpFeXMLDao;
    private ProcessDao processDao;
    
    @RequestMapping(value = "/read", method = RequestMethod.POST)
    public @ResponseBody
    ResponseQuery queryAction( @RequestParam(value = "sql") String sql,
                         @RequestParam(value = "limit") int limit,
                         @RequestParam(value = "page") int page,
                         //@RequestParam(value = "query") String query,
                         @RequestParam(value = "start") int start,WebRequest webRequest, Model model) {
        ResponseQuery rq = new ResponseQuery();
//        Map parametros = processDao.paramToMap(webRequest.getParameterMap());
//        parametros.put("compania", model.asMap().get("compania"));
//        parametros.put("usuario", model.asMap().get("usuario"));
        
        if( model.asMap().get("compania")==null){
            rq.setSuccess(false);
            rq.setData(null);
            rq.setMsg("Sesion no valida");
            return rq;
        }
        String compania  =model.asMap().get("compania").toString();
            
           List<ErpFeXML> list = new ArrayList<ErpFeXML>();
        try{
            String sDirectorio = dirOutFileDocument+"/" +compania;
            File f = new File(sDirectorio);     
            if (f.exists()){ // Directorio existe }
            }else { 
               rq.setSuccess(false);
               rq.setData(null);
               rq.setMsg("El directorio de archivos no existe.");
               return rq;
            }//Directorio no existe 
             SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
            String hora = ""+System.currentTimeMillis();
            File[] ficheros = f.listFiles();
            for (int x=0;x<ficheros.length;x++){
             System.out.println(ficheros[x].getName());
              ErpFeXML ar=new ErpFeXML();
              
              if(ficheros[x].getName().endsWith("xml") || ficheros[x].getName().endsWith("XML")){
                ar.setCompania(compania);       
                ar.setHora(hora);
                ar.setSec(hora+x);
                ar.setXml(ficheros[x].getName());
                ar.setDir_xml(ficheros[x].getAbsolutePath());
                 String fecha=sdf.format( ficheros[x].lastModified());
//                System.out.println("fecha:"+fecha);
                Date date = sdf.parse(fecha);
                ar.setFecha(date);
                list.add(ar);
              }
             
            }
            
            boolean delAr= erpFeXMLDao.deleteAll(compania);
//            System.out.println("delAr:"+delAr);
            if(delAr){
                boolean proAr= erpFeXMLDao.saveLista(list, compania);            
//                System.out.println("proAr:"+proAr);
                
                if(proAr){
                    
                    
                     int to = limit * page;
                    int from = to - limit;
                    Map parametros = processDao.paramToMap(webRequest.getParameterMap());

                    parametros.put("compania", model.asMap().get("compania"));
                    parametros.put("usuario", model.asMap().get("usuario"));
                    parametros.put("HORA",hora);

         
                    List list2= null;
                      ResultData result=processDao.getMapResultPage3(sql,parametros,from,limit);
                   if(result == null){
                rq.setSuccess(false);
                rq.setData(null);
                rq.setMsg("Sin datos");
                return rq;
           }
           if(result.getData().isEmpty()){
               rq.setSuccess(false);
                rq.setData(null);
                rq.setTotal(new BigDecimal(0));
                rq.setMsg("Sin datos");
                return rq;
           }
               BigDecimal tot=new BigDecimal(0);
           if(result.getData().size()>0){
                tot = processDao.getTotalData3(result.getQuery());
           }
                rq.setSuccess(true);
                rq.setData(result.getData());
                rq.setTotal(tot);
                rq.setMsg("Bien");
           
               return rq;     
                    
                }
            }
            
        }catch(Exception ioe){
            ioe.printStackTrace();
            rq.setSuccess(false);
            rq.setData(null);
            rq.setMsg("Error al buscar archivos.");
            return rq;
        }


       // List list = processDao.getMapResult(query, parametros);

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
    
    
     @RequestMapping(value = "/process", method = RequestMethod.POST)
    public @ResponseBody
    ResponseQuery processAction( @RequestParam("data") String data,WebRequest webRequest, Model model) {
        ResponseQuery rq = new ResponseQuery();
        
        
        
        System.out.println("data:"+data);
        if( model.asMap().get("compania")==null){
            rq.setSuccess(false);
            rq.setData(null);
            rq.setMsg("Sesion no valida");
            return rq;
        }
        
        
        try{
          ObjectMapper mapper = new ObjectMapper();
            DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
            
            String idErr = ""+System.currentTimeMillis();

            mapper.setDateFormat(df);
            List<DirectoryDto> lista = mapper.readValue(data,
                    mapper.getTypeFactory().constructCollectionType(List.class,
                            DirectoryDto.class));
            cargaFacturas.setDirectorio(dirOutFileDocument+model.asMap().get("compania").toString()+"/");
          List<DirectoryDto> listaR =  cargaFacturas.procesa(lista,model.asMap().get("compania").toString(),idErr);
            
            
            rq.setSuccess(true);
            rq.setData(listaR);
            rq.setMsg(idErr);
           // System.out.println("size:"+lista.size());
            
        }catch(Exception e){
            e.printStackTrace();
        }
        return rq;
    }

     @RequestMapping(value = "/processCXP", method = RequestMethod.POST)
    public @ResponseBody
    ResponseQuery processActionCXP( @RequestParam("data") String data,WebRequest webRequest, Model model) {
        ResponseQuery rq = new ResponseQuery();
        
        
        
        System.out.println("data:"+data);
        if( model.asMap().get("compania")==null){
            rq.setSuccess(false);
            rq.setData(null);
            rq.setMsg("Sesion no valida");
            return rq;
        }
        
        
        try{
          ObjectMapper mapper = new ObjectMapper();
            DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
            
            String idErr = ""+System.currentTimeMillis();

            mapper.setDateFormat(df);
            List<DirectoryDto> lista = mapper.readValue(data,
                    mapper.getTypeFactory().constructCollectionType(List.class,
                            DirectoryDto.class));
            cargaFacturas.setDirectorio(dirOutFileDocument+model.asMap().get("compania").toString()+"/");
          List<DirectoryDto> listaR =  cargaFacturas.procesa(lista,model.asMap().get("compania").toString(),idErr,"CP");
            
            
            rq.setSuccess(true);
            rq.setData(listaR);
            rq.setMsg(idErr);
           // System.out.println("size:"+lista.size());
            
        }catch(Exception e){
            e.printStackTrace();
        }
        return rq;
    }
     
     
       @RequestMapping(value = "/buscarPDF", method = RequestMethod.POST)
    public @ResponseBody
    ResponseQuery findPdf( @RequestParam("pdf") String pdf,WebRequest webRequest, Model model) {
        ResponseQuery rq = new ResponseQuery();
        
        
        
//        System.out.println("pdf:"+pdf);
        if( model.asMap().get("compania")==null){
            rq.setSuccess(false);
            rq.setData(null);
            rq.setMsg("Sesion no valida");
            return rq;
        }
        
        String compania  =model.asMap().get("compania").toString();
        
//        System.out.println("dirOutFileDocument"+dirOutFileDocument+compania+"/"+pdf);
        
        try{
                   File file = new File(dirOutFileDocument+compania+"/"+pdf);
              
                  
                 if (file.exists()){ // Directorio existe }
                       System.out.println("directorio existe");
                        rq.setSuccess(true);
                        rq.setData(null);
                        rq.setMsg("");
                        return rq;
                     }else { 
                    rq.setSuccess(false);
                    rq.setData(null);
                    rq.setMsg("Archivo no existe");
                    return rq;
                 }//Directorio no existe 
                   
                   
              
            
        }catch(Exception e){
            e.printStackTrace();
        }
        return rq;
    }

    public void setProcessDao(ProcessDao processDao) {
        this.processDao = processDao;
    }
    
    
    

    public void setCargaFacturas(CargaFacturas cargaFacturas) {
        this.cargaFacturas = cargaFacturas;
    }

    public void setErpFeXMLDao(ErpFeXMLDao erpFeXMLDao) {
        this.erpFeXMLDao = erpFeXMLDao;
    }        

    public void setDirOutFileDocument(String dirOutFileDocument) {
        this.dirOutFileDocument = dirOutFileDocument;
    }

    public void setDirUrlOutDocument(String dirUrlOutDocument) {
        this.dirUrlOutDocument = dirUrlOutDocument;
    }

    public void setMaxSizeDocument(String maxSizeDocument) {
        this.maxSizeDocument = maxSizeDocument;
    }
    
    
}
