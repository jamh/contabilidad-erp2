/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.feraz.facturas.webcontrolfe.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.feraz.contabilidad.convertidor.util.ResponseComprobante;
import com.feraz.contabilidad.convertidor.util.ResponseQuery3;
import com.feraz.facturas.webcontrolfe.App.App;
import com.feraz.facturas.webcontrolfe.dao1.ErpFeXMLDao;
import com.feraz.facturas.webcontrolfe.dto.CargaCfdiDTO;
import com.feraz.facturas.webcontrolfe.dto.DirectoryDto;
import com.feraz.facturas.webcontrolfe.dto.FileInfo;
import com.feraz.facturas.webcontrolfe.dto.PolizasInfo;
import com.feraz.facturas.webcontrolfe.model.ErpFeXML;
import com.feraz.facturas.webcontrolfe.model.FileUploadBean;
import com.feraz.facturas.webcontrolfe.model.FileUploadBean2;
import com.feraz.facturas.webcontrolfe.procesa.CargaFacturas;
import com.feraz.facturas.webcontrolfe.validation.ValidaVersionCFDI;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.Unmarshaller;
import javax.xml.datatype.DatatypeConfigurationException;
import mx.bigdata.sat.cfdi.CFDv32;
import mx.bigdata.sat.cfdi.v32.schema.Comprobante;
import mx.bigdata.sat.cfdi.v32.schema.TimbreFiscalDigital;
import org.apache.xerces.dom.ElementNSImpl;
import org.jamh.data.process.ProcessDao;
import org.jamh.wf.json.dto.ResultData;
import org.jamh.wf.json.model.ResponseQuery;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.FileCopyUtils;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.multipart.MultipartFile;

/**
 *
 * @author Ing. JAMH
 */

@Controller
@RequestMapping(value = "/dirdata")
@SessionAttributes({"compania", "usuario"})
public class DirectoryController {
    
    @Value("${document.file.dirOutDocumentFE}")
    private String dirOutFileDocument;
    @Value("${document.file.dirURLOutDocumentFE}")
    private String dirUrlOutDocument;
    @Value("${document.file.maxZise}")
    private String maxSizeDocument;
    
       private String msgErr;
    private String extension = "";
    private String nombreArc = "";
    private String path;
    private String path2;
    private String pathXML;
    private String url;
    private String dirCompania;
    private File file;
    private String hora;
    
    private CargaFacturas cargaFacturas;
    
    private ErpFeXMLDao erpFeXMLDao;
    private ProcessDao processDao;
    
    private ValidaVersionCFDI validaVersionCFDI;
    private App app;
    
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
            if (f.exists()){ 
            }else { 
               rq.setSuccess(false);
               rq.setData(null);
               rq.setMsg("El directorio de archivos no existe.");
               return rq;
            }
            
            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
            String hora = ""+System.currentTimeMillis();
            File[] ficheros = f.listFiles();
            for (int x=0;x<ficheros.length;x++){
             //System.out.println(ficheros[x].getName());
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
                // ar.setDir_pdf(ficheros[x].);
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
    
    @RequestMapping(value = "/processCCP", method = RequestMethod.POST)
    public @ResponseBody
    ResponseQuery processActionCCP( @RequestParam("data") String data,WebRequest webRequest, Model model) {
        ResponseQuery rq = new ResponseQuery();
        
        
        
        System.out.println("data:"+data);
        if( model.asMap().get("compania")==null){
            rq.setSuccess(false);
            rq.setData(null);
            rq.setMsg("Sesion no valida");
            return rq;
        }
        
         System.out.println("origen ccp");
        
        try{
          ObjectMapper mapper = new ObjectMapper();
            DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
            
            String idErr = ""+System.currentTimeMillis();

            mapper.setDateFormat(df);
            List<DirectoryDto> lista = mapper.readValue(data,
                    mapper.getTypeFactory().constructCollectionType(List.class,
                            DirectoryDto.class));
            cargaFacturas.setDirectorio(dirOutFileDocument+model.asMap().get("compania").toString()+"/");
          List<DirectoryDto> listaR =  cargaFacturas.procesa(lista,model.asMap().get("compania").toString(),idErr,"CCP");
            
            
            rq.setSuccess(true);
            rq.setData(listaR);
            rq.setMsg(idErr);
          
            
        }catch(Exception e){
            e.printStackTrace();
        }
        return rq;
    }
    
       @RequestMapping(value = "/cargaarchivo/{comp}/{name}.xml", method = RequestMethod.GET, produces = "application/*+xml;charset=UTF-8")
    public @ResponseBody
    ResponseQuery guardaXmlDir(@PathVariable("comp") String compania,
            @PathVariable("name") String name,
            WebRequest webRequest, Model model) throws IOException, DatatypeConfigurationException, ParseException {

       ResponseQuery rq = new ResponseQuery();
        
      //  System.out.println("-----------------------------------------------");
      //  System.out.println("Compania: "+compania);
      //  System.out.println("Xml: "+dirOutFileDocument+name);
      //  System.out.println("directorio: "+dirOutFileDocument+compania+"/"+name);
        String idErr = ""+System.currentTimeMillis();
        try{
           int result = cargaFacturas.procesaXmlDir(dirOutFileDocument+compania+"/"+name, compania, idErr);
        
        //   System.out.println("result"+result);
        
           if (result == 0){
            
              rq.setSuccess(false);
              rq.setMsg("Error al guadar");
              rq.setData(null);
        
           }else{
              rq.setSuccess(true);
              rq.setMsg("Guardado correctamente");
              rq.setData(null);
           }
           
          } catch (Exception e) {
            e.printStackTrace();
            
            rq.setData(null);
            rq.setMsg("No se pudo guardar el xml");
            rq.setSuccess(false);
        }
        return rq;
    }

       @RequestMapping(value = "/cargaarchivo2/{comp}", method = RequestMethod.GET)
    public @ResponseBody
    ResponseQuery guardaXmlDir2(@PathVariable("comp") String compania,
            @RequestParam("name") String name,
            WebRequest webRequest, Model model) throws IOException, DatatypeConfigurationException, ParseException {

       ResponseQuery rq = new ResponseQuery();
       
      
       // System.out.println("-----------------------------------------------");
       // System.out.println("Compania: "+compania);
       // System.out.println("Xml: "+dirOutFileDocument+name);
       // System.out.println("directorio: "+dirOutFileDocument+compania+"/"+name);
       //
       String idErr = ""+System.currentTimeMillis();
        try{
           int result = cargaFacturas.procesaXmlDir(dirOutFileDocument+compania+"/"+name, compania, idErr);
         
           if (result == 0){
            
              rq.setSuccess(false);
              rq.setMsg("Error al guadar");
              rq.setData(null);
        
           }else{
              rq.setSuccess(true);
              rq.setMsg("Guardado correctamente");
              rq.setData(null);
           }
           
          } catch (Exception e) {
            e.printStackTrace();
            
            rq.setData(null);
            rq.setMsg("No se pudo guardar el xml");
            rq.setSuccess(false);
        }
        return rq;
    }
    
    
    
       @RequestMapping(value = "/cargacfdisat/{comp}/{usr}", method = RequestMethod.GET)
    public @ResponseBody
    List<CargaCfdiDTO> cargacfdisat(@PathVariable("comp") String compania,@PathVariable("usr") String usuario,
            WebRequest webRequest, Model model) throws IOException, DatatypeConfigurationException, ParseException {

       CargaCfdiDTO rq = new CargaCfdiDTO();
       
       List<CargaCfdiDTO> listResponse = new ArrayList<CargaCfdiDTO>();
       
       String idErr = ""+System.currentTimeMillis();
        try{
            
            int result = 1;
            String pathXml = dirOutFileDocument + "/" + compania;
            
            System.out.println("PATH: "+pathXml);


            String[] files = getFiles( pathXml );

            if ( files != null ) {

                int size = files.length;

                for ( int i = 0; i < size; i ++ ) {

                    System.out.println( files[ i ] );
                    boolean result1 = validaVersionCFDI.verision32(files[ i ]);
                    boolean result2 = validaVersionCFDI.version33(files[ i ]);
                    boolean result4 = validaVersionCFDI.version4(files[ i ]);
                    
//                    System.out.println( "result1: "+result1 );
//                    System.out.println( "result2: "+result2 );
//                    System.out.println( "result4: "+result4 );
//                    
                    try {
                        

                            Map titulo = new HashMap();
                            titulo.put("compania", compania);

                          
                            
                            rq.setArchivo(files[ i ]);
                            
                            if (!result2 && result1 && !result4) {
                                
                            //    
                                System.out.println("Version 3.2");
                                Comprobante comp = CFDv32.newComprobante(new FileInputStream(files[ i ]));

                                String calendar2 = formatFecha(comp.getFecha(), "yyyy");
                                String periodo2 = formatFecha(comp.getFecha(), "MM");

                                Map periodoCont = new HashMap();
                                periodoCont.put("compania", compania);
                                periodoCont.put("calendario", calendar2);
                                periodoCont.put("periodo", periodo2);

                                List periodoContaList = processDao.getMapResult("BuscaPeriodoCXP", periodoCont);

                           
                                if (comp != null) {
                                    PolizasInfo info = app.validaComprobante(compania, files[ i ], null, null);
                                    if (info.getInfTipo() == 1 || info.getInfTipo() == 3) {
                                        rq.setMsg("Error el comprobante ya fue cargado al sistema. " + info.getUuid());
                                        rq.setEstatus(false);
                                    } else if (info.getInfTipo() == 2) {
                                        rq.setMsg("Error el XML tiene un problema de lectura. ");
                                        rq.setEstatus(false);
                                    } else if (periodoContaList == null) {

                                        rq.setMsg("El periodo se encuentra cerrado, no se puede ingresar la factura ");
                                        rq.setEstatus(false);

                                    } else if (periodoContaList.isEmpty()) {

                                        rq.setMsg("Error el periodo esta cerrado, no se puede ingresar la factura ");
                                        rq.setEstatus(false);

                                    } else {

                                       
                                        ResponseComprobante rcomp = app.cargaComprobante2(files[ i ], compania, null);

                                        if (rcomp == null) {
                                            rq.setMsg("Error al leer el CFDI");
                                            rq.setEstatus(false);
                                        } else {
                                            rq.setEstatus(true);
                                            rq.setMsg("Guardado con exito");
                                        }

                                    }

                                }
                                
                                

                            } else if(result2 && !result1 && !result4) {
                            //    System.out.println("Version 3.3");
                                // File file = new File(archivo);
                                JAXBContext jaxbContext = JAXBContext.newInstance(com.feraz.mx.sat.cfdi.Comprobante.class);

                                Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
                                com.feraz.mx.sat.cfdi.Comprobante comprobante = (com.feraz.mx.sat.cfdi.Comprobante) jaxbUnmarshaller.unmarshal(new FileInputStream(files[ i ]));

                                // Comprobante comp = CFDv32.newComprobante(new FileInputStream(temp));
                                String calendar2 = String.valueOf(comprobante.getFecha().getYear());
                                String periodo2 = String.valueOf(comprobante.getFecha().getMonth());

                                Map periodoCont = new HashMap();
                                periodoCont.put("compania", compania);
                                periodoCont.put("calendario", calendar2);
                                periodoCont.put("periodo", periodo2);

                                List periodoContaList = processDao.getMapResult("BuscaPeriodoCXP", periodoCont);

                            //    System.out.println("periodoContaList:" + periodoContaList);

                                if (comprobante != null) {
                                    PolizasInfo info = app.validaComprobante(compania, files[ i ], null, null);
                                    if (info.getInfTipo() == 1 || info.getInfTipo() == 3) {
                                        rq.setMsg("Error el comprobante ya fue cargado al sistema. " + info.getUuid());
                                        rq.setEstatus(false);
                                    } else if (info.getInfTipo() == 2) {
                                        rq.setMsg("Error el XML tiene un problema de lectura. ");
                                        rq.setEstatus(false);
                                    } else if (periodoContaList == null) {

                                        rq.setMsg("Error el periodo esta cerrado, no se puede ingresar la factura ");
                                        rq.setEstatus(false);

                                    } else if (periodoContaList.isEmpty()) {

                                        rq.setMsg("Error el periodo esta cerrado, no se puede ingresar la factura ");
                                        rq.setEstatus(false);

                                    } else {

                                      
                                        int data = app.cargaComprobante33(files[ i ], compania, usuario, null, "cfdi");

                                        if (data == 0) {
                                            rq.setMsg("Error al leer el CFDI");
                                            rq.setEstatus(false);
                                        } else {
                                            rq.setEstatus(true);
                                            rq.setMsg("Guardado con exito");
                                        }

                                    }

                                }

                            }else if(!result2 && !result1 && result4){
                                //         System.out.println("Version 4");
                                // File file = new File(archivo);
                                JAXBContext jaxbContext = JAXBContext.newInstance(com.feraz.mx.sat.cfdi4.Comprobante4.class);

                                Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
                                com.feraz.mx.sat.cfdi4.Comprobante4 comprobante = (com.feraz.mx.sat.cfdi4.Comprobante4) jaxbUnmarshaller.unmarshal(new FileInputStream(files[ i ]));

                                // Comprobante comp = CFDv32.newComprobante(new FileInputStream(temp));
                                String calendar2 = String.valueOf(comprobante.getFecha().getYear());
                                String periodo2 = String.valueOf(comprobante.getFecha().getMonth());

                                Map periodoCont = new HashMap();
                                periodoCont.put("compania", compania);
                                periodoCont.put("calendario", calendar2);
                                periodoCont.put("periodo", periodo2);

                                List periodoContaList = processDao.getMapResult("BuscaPeriodoCXP", periodoCont);

                                //System.out.println("periodoContaList:" + periodoContaList);

                                if (comprobante != null) {
                                    PolizasInfo info = app.validaComprobante4(compania, files[ i ], null, null);
                                    if (info.getInfTipo() == 1 || info.getInfTipo() == 3) {
                                        rq.setMsg("Error el comprobante ya fue cargado al sistema. " + info.getUuid());
                                        rq.setEstatus(false);
                                    } else if (info.getInfTipo() == 2) {
                                        rq.setMsg("Error el XML tiene un problema de lectura. ");
                                        rq.setEstatus(false);
                                    } 
                                     else if (periodoContaList == null) {

                                        rq.setMsg("Error el periodo esta cerrado, no se puede ingresar la factura ");
                                        rq.setEstatus(false);
                                    } else if (periodoContaList.isEmpty()) {

                                        rq.setMsg("Error el periodo esta cerrado, no se puede ingresar la factura ");
                                        rq.setEstatus(false);
                                    } else {
                                       
                                        int data = app.cargaComprobante4(files[ i ], compania, usuario, null, "cfdi");

                                        if (data == 0) {
                                            rq.setMsg("Error al leer el CFDI");
                                            rq.setEstatus(false);
                                        } else {
                                            rq.setEstatus(true);
                                            rq.setMsg("Guardado con exito");
                                        }

                                    }

                                }
                            }
                            
                            listResponse.add(rq);
                
                        
                    }catch (Exception e) {
                        e.printStackTrace();
                        rq.setArchivo(files[ i ]);
                        rq.setEstatus(false);
                        rq.setMsg("Error al guardar");
                        
                        listResponse.add(rq);
                       
                    }
                    
                }
            }
            
   
           
          } catch (Exception e) {
            e.printStackTrace();
            
            rq.setArchivo("");
            rq.setMsg("Error al procesar programa");
            rq.setEstatus(false);
            
            listResponse.add(rq);
        }
        return listResponse;
    }
    
    
    @RequestMapping(value = "/cargacfdisatfile2", method = RequestMethod.POST)
    public @ResponseBody
    ResponseQuery3 create(
            FileUploadBean file,
           @RequestParam("dirxml") String dirxml,
           @RequestParam("compania") String compania,
           @RequestParam("usuario") String usuario,
            WebRequest webRequest,
            Model model) throws IOException {
        
        System.out.println("LLegando a crear archivos");
         hora = "" + System.currentTimeMillis();

        boolean isSave = true;
        SimpleDateFormat formatoDelTexto2 = new SimpleDateFormat("dd/MM/yyyy");

        ResponseQuery3 rq = new ResponseQuery3();


        //String compania = model.asMap().get("compania").toString();
        // String usuario = model.asMap().get("usuario").toString();
        try{ 
                dirCompania = compania;
                FileInfo fi;

                 boolean result1 = validaVersionCFDI.verision32File(file.getFile());
                 boolean result2 = validaVersionCFDI.version33File(file.getFile());
                 boolean result4 = validaVersionCFDI.version4File(file.getFile());

                 System.out.println("result1: " +result1);
                 System.out.println("result2: " +result2);


                String dirFecha = "";




                if(!result2 && !result4 && result1){

                       dirFecha = app.validaFechaComprobante(file.getFile(), dirOutFileDocument, compania);
                }

                if(result2 && !result4 && !result1){

                       dirFecha = app.validaFechaComprobante33(file.getFile(), dirOutFileDocument, compania);

                }

                if(!result2 && result4 && !result1){

                    dirFecha = app.validaFechaComprobante4(file.getFile(), dirOutFileDocument, compania);


                }




                if (dirFecha.equalsIgnoreCase("")){

                        fi = generaArchivos(file.getFile(), 1);

                        if (!fi.isIsSave()) {
                            rq.setMsg("Error al guardar archivo XML:" + msgErr);
                            rq.setSuccess(false);
                            return rq;
                        }
                     

                }else{


                    String nombreUuid = "";
                    
                    


                    if(!result2 && !result4 && result1){

                      nombreUuid = app.validaNombreUUidComprobante(file.getFile(), dirOutFileDocument, compania);

                    }

                    if(result2 && !result4 && !result1){

                        nombreUuid = app.validaNombreUUidComprobante33(file.getFile(), dirOutFileDocument, compania);


                    }

                    if(!result2 && result4 && !result1){

                         nombreUuid = app.validaNombreUUidComprobante4(file.getFile(), dirOutFileDocument, compania);

                    }
                    System.out.println("nombreUuid: "+nombreUuid);

                       fi = generaArchivos2(file.getFile(), 1,dirFecha,nombreUuid);

                        if (!fi.isIsSave()) {
                            rq.setMsg("Error al guardar archivo XML:" + msgErr);
                            rq.setSuccess(false);
                            return rq;
                        }
                  

                }
                 System.out.println("Archivo Generado");


               
                String idErr = "" + System.currentTimeMillis();
                app.setId(idErr);
                System.out.println("VALIDANDO COMP");
                PolizasInfo valida;


                   if(!result2 && !result4 && result1){

                        valida = app.validaComprobante(compania, pathXML, fi.getFile(), fi.getPath());

                    }else{

                       if(result2 && !result4 && !result1){

                        valida = app.validaComprobante33(compania, pathXML, fi.getFile(), fi.getPath());


                        }else{

                           if(!result2 && result4 && !result1){

                                valida = app.validaComprobante4(compania, pathXML, fi.getFile(), fi.getPath());

                           }else{

                               valida = app.validaComprobante33(compania, pathXML, fi.getFile(), fi.getPath());

                           }


                        }

                    }
                 System.out.println("valida:"+valida.getInfTipo());
                 System.out.println("validaErr:"+valida.getMsgErr());
                if (valida.getInfTipo() == 1) {
                    String va = "";
                    if (app.getRelacion() != null) {
                        va = app.getRelacion().getId().getNumeroPol() + "-" + app.getRelacion().getId().getTipoPol();
                    }
                    rq.setNumero(valida.getNumero());
                    rq.setTipoPoliza(valida.getTipoPoliza());
                    rq.setFecha(valida.getFecha());
                    //  String poliza = app.getRelacion().
                    rq.setSuccess(false);
                    rq.setMsg(valida.getMsgErr());
                    return rq;
                } else if (valida.getInfTipo() == 2) {
                    rq.setSuccess(false);
                    rq.setMsg(valida.getMsgErr());
                    return rq;
                } else if (valida.getInfTipo() == 3) {
                    rq.setSuccess(true);
                    rq.setMsg(valida.getMsgErr());
                    rq.setNumeroFe("" + valida.getNumeroFe());
                    return rq;
                }
                System.out.println("Carga Comprbante");

                //app.setNombrePdf(fi.getFile());
                //app.setPathPdf(fi2.getPath());
                app.setUsuario(usuario);
                app.setTipoCarga("1");
                int data = 0;



                 if(!result2 && !result4 && result1){

                      data = app.cargaComprobante(pathXML, compania, usuario, null, "cfdi");

                    }

                    if(result2 && !result4 && !result1){

                        data = app.cargaComprobante33(pathXML, compania, usuario, null, "cfdi");


                    }

                    if(!result2 && result4 && !result1){

                         data = app.cargaComprobante4(pathXML, compania, usuario, null, "cfdi");

                    }
                if (data >= 1) {

                    rq.setSuccess(true);
                    rq.setMsg(Integer.toString(data));

                    return rq;
                } else {
                    rq.setSuccess(false);
                    rq.setMsg(idErr);

                    return rq;
                }
         } catch (Exception e) {
             
             e.printStackTrace();
              return null;
            }

        
        
    }
    
    
    
       @RequestMapping(value = "/cargacfdisatfile/xml/{comp}/{user}/{rfc}/{origen}/{uuid}", method = RequestMethod.GET)
    public @ResponseBody
    List<CargaCfdiDTO> cargacfdisatdir(@PathVariable("comp") String compania,@PathVariable("user") String usuario,
            @PathVariable("rfc") String rfc,@PathVariable("origen") String origen,@PathVariable("uuid") String uuid,
            WebRequest webRequest, Model model) throws IOException, DatatypeConfigurationException, ParseException {

       CargaCfdiDTO rq = new CargaCfdiDTO();
       
       List<CargaCfdiDTO> listResponse = new ArrayList<CargaCfdiDTO>();
       
       String idErr = ""+System.currentTimeMillis();
        try{
            String folderName = "D:\\carga\\"+rfc + "-" + origen;
            String dir = folderName + "/" + uuid + ".xml";
            
            System.out.println("PATH: "+dir);


            if ( dir != null ) {

         

           

                    System.out.println( dir );
                    boolean result1 = validaVersionCFDI.verision32(dir);
                    boolean result2 = validaVersionCFDI.version33(dir);
                    boolean result4 = validaVersionCFDI.version4(dir);

                    try {
                        

                            Map titulo = new HashMap();
                            titulo.put("compania", compania);

                          
                            
                            rq.setArchivo(dir);
                            
                            if (!result2 && result1 && !result4) {
                                
                            //    
                                System.out.println("Version 3.2");
                                Comprobante comp = CFDv32.newComprobante(new FileInputStream(dir));


                                if (comp != null) {
                                    PolizasInfo info = app.validaComprobante(compania, dir, null, null);
                                    if (info.getInfTipo() == 1 || info.getInfTipo() == 3) {
                                        rq.setMsg("Error el comprobante ya fue cargado al sistema. " + info.getUuid());
                                        rq.setEstatus(false);
                                    } else if (info.getInfTipo() == 2) {
                                        rq.setMsg("Error el XML tiene un problema de lectura. ");
                                        rq.setEstatus(false);
                             

                                    } else {

                                       
                                        ResponseComprobante rcomp = app.cargaComprobante2(dir, compania, null);

                                        if (rcomp == null) {
                                            rq.setMsg("Error al leer el CFDI");
                                            rq.setEstatus(false);
                                        } else {
                                            rq.setEstatus(true);
                                            rq.setMsg("Guardado con exito");
                                        }

                                    }

                                }
                                
                                

                            } else if(result2 && !result1 && !result4) {
                            //    System.out.println("Version 3.3");
                                // File file = new File(archivo);
                                JAXBContext jaxbContext = JAXBContext.newInstance(com.feraz.mx.sat.cfdi.Comprobante.class);

                                Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
                                com.feraz.mx.sat.cfdi.Comprobante comprobante = (com.feraz.mx.sat.cfdi.Comprobante) jaxbUnmarshaller.unmarshal(new FileInputStream(dir));


                                if (comprobante != null) {
                                    PolizasInfo info = app.validaComprobante(compania, dir, null, null);
                                    if (info.getInfTipo() == 1 || info.getInfTipo() == 3) {
                                        rq.setMsg("Error el comprobante ya fue cargado al sistema. " + info.getUuid());
                                        rq.setEstatus(false);
                                    } else if (info.getInfTipo() == 2) {
                                        rq.setMsg("Error el XML tiene un problema de lectura. ");
                                        rq.setEstatus(false);
                                    
                                    } else {

                                      
                                        int data = app.cargaComprobante33(dir, compania, usuario, null, "cfdi");

                                        if (data == 0) {
                                            rq.setMsg("Error al leer el CFDI");
                                            rq.setEstatus(false);
                                        } else {
                                            rq.setEstatus(true);
                                            rq.setMsg("Guardado con exito");
                                        }

                                    }

                                }

                            }else if(!result2 && !result1 && result4){
                                //         System.out.println("Version 4");
                                // File file = new File(archivo);
                                JAXBContext jaxbContext = JAXBContext.newInstance(com.feraz.mx.sat.cfdi4.Comprobante4.class);

                                Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
                                com.feraz.mx.sat.cfdi4.Comprobante4 comprobante = (com.feraz.mx.sat.cfdi4.Comprobante4) jaxbUnmarshaller.unmarshal(new FileInputStream(dir));

                              

                                if (comprobante != null) {
                                    PolizasInfo info = app.validaComprobante4(compania, dir, null, null);
                                    if (info.getInfTipo() == 1 || info.getInfTipo() == 3) {
                                        rq.setMsg("Error el comprobante ya fue cargado al sistema. " + info.getUuid());
                                        rq.setEstatus(false);
                                    } else if (info.getInfTipo() == 2) {
                                        rq.setMsg("Error el XML tiene un problema de lectura. ");
                                        rq.setEstatus(false);
                                    
                                    
                                    } else {
                                        
                                       
                                        int data = app.cargaComprobante4(dir, compania, usuario, null, "cfdi");

                                        if (data == 0) {
                                            rq.setMsg("Error al leer el CFDI");
                                            rq.setEstatus(false);
                                        } else {
                                            rq.setEstatus(true);
                                            rq.setMsg("Guardado con exito");
                                        }

                                    }

                                }
                            }
                            
                            listResponse.add(rq);
                
                        
                    }catch (Exception e) {
                        e.printStackTrace();
                        rq.setArchivo(dir);
                        rq.setEstatus(false);
                        rq.setMsg("Error al guardar");
                        
                        listResponse.add(rq);
                       
                    }
                    
                
            }
            
   
           
          } catch (Exception e) {
            e.printStackTrace();
            
            rq.setArchivo("");
            rq.setMsg("Error al procesar programa");
            rq.setEstatus(false);
            
            listResponse.add(rq);
        }
        return listResponse;
    }
    
    public String[] getFiles( String dir_path ) {

            String[] arr_res = null;

            File f = new File( dir_path );

            if ( f.isDirectory( )) {

                List<String> res   = new ArrayList<>();
                File[] arr_content = f.listFiles();

                int size = arr_content.length;

                for ( int i = 0; i < size; i ++ ) {

                    if ( arr_content[ i ].isFile( )){
                        
                        String name = arr_content[ i ].getName();
                        int lastIndexOf = name.lastIndexOf(".");
                        if (lastIndexOf != -1) {
                            String extension = name.substring(lastIndexOf + 1);
                            if(extension.toLowerCase().trim().equals("xml")){
                                
                                res.add( arr_content[ i ].toString( ));
                            
                            }
                            
                            
                        }
                        
                        
                        
                        
                        
                    }
                }


                arr_res = res.toArray( new String[ 0 ] );

            } else
                System.err.println( "¡ Path NO válido !" );


            return arr_res;
        }
    
    
    public FileInfo generaArchivos(MultipartFile uploadItem, int tipo) {
        String fileName = null;
        String nombreArchivo = "";

        //  ExtJSFormResult rq = new ExtJSFormResult();
        try {

            MultipartFile file = uploadItem;
            FileInfo fi = new FileInfo();

            InputStream inputStream = null;
            OutputStream outputStream = null;
            if (file.getSize() > 0) {
                inputStream = file.getInputStream();
                long maxSize = new Long(maxSizeDocument);
                if (file.getSize() > maxSize) {
                    msgErr = "Tamaño no valido";
                    //System.out.println("File Size:::" + file.getSize());
                    // isSave = false;
//                    rq.setMsg("Tamaño no valido");
//                    rq.setSuccess(isSave);
                    fi.setIsSave(false);
                    return fi;
                }

                int i = file.getOriginalFilename().lastIndexOf('.');
               // String hora = "" + System.currentTimeMillis();
//                System.out.println("Nombre:" + file.getOriginalFilename());
                extension = file.getOriginalFilename().substring(i + 1);
                nombreArc = file.getOriginalFilename().substring(0, i);
                fi.setFile(nombreArc + hora + "." + extension);
//                System.out.println("i:" + i);
//                System.out.println("exte:" + extension);
//                System.out.println("nombreArc:" + nombreArc);
//                System.out.println("exte:" + extension);
                if (tipo == 1 && !extension.toLowerCase().trim().equals("xml")) {

                    msgErr = "Archivo xml no valido";

                    fi.setIsSave(false);
                    return fi;
                }
                if (tipo == 2 && !extension.toLowerCase().trim().equals("pdf")) {

                    msgErr = "Archivo pdf no valido";

                    fi.setIsSave(false);
                    return fi;
                }

                path = dirOutFileDocument + dirCompania + "/" + nombreArc + hora + "." + extension;
                fi.setPath(path);
                if (tipo == 1) {
                    pathXML = path;
                }

                url = dirUrlOutDocument + nombreArc + hora + "." + extension;
                fi.setUrl(url);

                revisarDirectorio2();
                outputStream = new FileOutputStream(path);

                int readBytes = 0;
                byte[] buffer = new byte[10000];
                while ((readBytes = inputStream.read(buffer, 0, 10000)) != -1) {
                    outputStream.write(buffer, 0, readBytes);
                }
                outputStream.close();
                inputStream.close();
            }

            fi.setIsSave(true);
            return fi;

        } catch (Exception e) {
            e.printStackTrace();
            FileInfo fi = new FileInfo();
            fi.setIsSave(false);
            return fi;
        }
    }
    
     public void revisarDirectorio(String dirCompania) {

        File file = new File(dirCompania + "/");
        //  System.out.println("Directory" + dirCompania + "/");
        if (!file.exists()) {
            if (file.mkdirs()) {
                //   System.out.println("Directory is created!");
            } else {
                //   System.out.println("Failed to create directory!");
            }
        }

    }
     
      public FileInfo generaArchivos2(MultipartFile uploadItem, int tipo,String pathArch, String nombreUuid) {
        String fileName = null;
        String nombreArchivo = "";
        
        //System.out.println("Guardando en genera 2");
        
        //  ExtJSFormResult rq = new ExtJSFormResult();
        try {

            MultipartFile file = uploadItem;
            FileInfo fi = new FileInfo();

            InputStream inputStream = null;
            OutputStream outputStream = null;
            if (file.getSize() > 0) {
                inputStream = file.getInputStream();
                long maxSize = new Long(maxSizeDocument);
                if (file.getSize() > maxSize) {
                    msgErr = "Tamaño no valido";
                    //System.out.println("File Size:::" + file.getSize());
                    // isSave = false;
//                    rq.setMsg("Tamaño no valido");
//                    rq.setSuccess(isSave);
                    fi.setIsSave(false);
                    return fi;
                }

                int i = file.getOriginalFilename().lastIndexOf('.');
               // String hora = "" + System.currentTimeMillis();
//                System.out.println("Nombre:" + file.getOriginalFilename());
                extension = file.getOriginalFilename().substring(i + 1);
                nombreArc = file.getOriginalFilename().substring(0, i);
                fi.setFile(nombreUuid + "." + extension);
//                System.out.println("i:" + i);
//                System.out.println("exte:" + extension);
//                System.out.println("nombreArc:" + nombreArc);
//                System.out.println("exte:" + extension);
                if (tipo == 1 && !extension.toLowerCase().trim().equals("xml")) {

                    msgErr = "Archivo xml no valido";

                    fi.setIsSave(false);
                    return fi;
                }
                if (tipo == 2 && !extension.toLowerCase().trim().equals("pdf")) {

                    msgErr = "Archivo pdf no valido";

                    fi.setIsSave(false);
                    return fi;
                }

                path = pathArch + "/"+nombreUuid+  "." + extension;
                fi.setPath(path);
                if (tipo == 1) {
                    pathXML = path;
                }

                url =  pathArch + "/"+nombreUuid+  "." + extension;
                fi.setUrl(url);

                //revisarDirectorio();
                outputStream = new FileOutputStream(path);

                int readBytes = 0;
                byte[] buffer = new byte[10000];
                while ((readBytes = inputStream.read(buffer, 0, 10000)) != -1) {
                    outputStream.write(buffer, 0, readBytes);
                }
                outputStream.close();
                inputStream.close();
            }

            fi.setIsSave(true);
            return fi;

        } catch (Exception e) {
            e.printStackTrace();
            FileInfo fi = new FileInfo();
            fi.setIsSave(false);
            return fi;
        }
    }
     
     public void revisarDirectorio2() {

        File file = new File(dirOutFileDocument + dirCompania + "/");
//        System.out.println("Directory" + dirOutFileDocument + dirCompania + "/");
        if (!file.exists()) {
            if (file.mkdirs()) {
//                System.out.println("Directory is created!");
            } else {
//                System.out.println("Failed to create directory!");
            }
        }

    }

    public String formatFecha(Date date, String format) {
        try {
            return new SimpleDateFormat(format).format(date);
        } catch (Exception e) {
            return "";
        }
    }

    public void setProcessDao(ProcessDao processDao) {
        this.processDao = processDao;
    }
    
    public void setApp(App app) {
        this.app = app;
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

    public void setValidaVersionCFDI(ValidaVersionCFDI validaVersionCFDI) {
        this.validaVersionCFDI = validaVersionCFDI;
    }
    
    
}
