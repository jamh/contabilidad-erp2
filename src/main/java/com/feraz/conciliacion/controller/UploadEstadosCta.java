/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author vavi
 */

package com.feraz.conciliacion.controller;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.feraz.conciliacion.dao.ErpEdoCuentaXErpDao;
import com.feraz.conciliacion.dao.impl.ErpFeCargaBancoDaoImpl;
import com.feraz.conciliacion.dto.ConciliaDTO;
import com.feraz.conciliacion.dto.ConciliaManualDTO;
import com.feraz.conciliacion.model.ErpEdoCuentaXErp;
import com.feraz.conciliacion.model.ErpEdoCuentaXErpId;
import com.feraz.conciliacion.model.ErpFeCargaBanco;
import com.feraz.conciliacion.model.ErpFeCargaBancoId;
import com.feraz.facturas.webcontrolfe.App.App;
import com.feraz.facturas.webcontrolfe.dto.PolizasInfo;
import com.feraz.facturas.webcontrolfe.model.FileUploadBean;
import com.feraz.facturas.webcontrolfe.validation.ValidaVersionCFDI;
import com.feraz.polizas3.model.ExtJSFormResult;
import java.io.IOException;
import java.util.Iterator;
import java.util.LinkedList;
import java.io.File;
import java.io.FileInputStream;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.feraz.procesos.model.FileMeta;
import java.io.BufferedReader;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.nio.charset.Charset;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import mx.bigdata.sat.cfdi.CFDv32;
import mx.bigdata.sat.cfdi.v32.schema.Comprobante;
import mx.bigdata.sat.cfdi.v32.schema.TimbreFiscalDigital;
import org.apache.log4j.Logger;
import org.apache.xerces.dom.ElementNSImpl;
import org.jamh.data.process.ProcessDao;
import org.jamh.wf.json.model.ResponseQuery;
import org.jamh.wf.json.model.ResponseQuery2;
import org.jamh.wf.process.Querys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.context.request.WebRequest;

@Controller
@RequestMapping("/filesEdoCta")
@SessionAttributes({"compania", "usuario"})
public class UploadEstadosCta {

    @Value("${document.file.dirOutDocumentFE}")
    private String dirOutFileDocument;
    @Value("${document.file.dirURLOutDocumentFE}")
    private String dirUrlOutDocument;
    @Value("${document.file.maxZiseFE}")
    private String maxSizeDocument;
    private ProcessDao processDao;
   // private String extension = "";
    //private String nombreArc = "";
    private String dirCompania;

    private ErpFeCargaBancoDaoImpl erpFeCargaBancoDao;
    private ErpEdoCuentaXErpDao erpEdoCuentaXErpDao;
    
    
     @RequestMapping(value = "/saveArchivo", method = RequestMethod.POST )
     
    public @ResponseBody
    String create(
            @RequestParam("cboBancoConciliaCarga") String banco,
            @RequestParam("cboTipoConciliaCarga") String tipo,
            @RequestParam("cboCalendarioConciliaCarga") String calendario,
            @RequestParam("cboPeriodoConciliaCarga") String periodo,
            @RequestParam("archDESCRIPCIONConcilia") String descripcion,
            @RequestParam("dtCompaniaConciliaCarga") String companiaC,
            @RequestParam("cboCuentaConciliaCarga") String cuenta,
            
            FileUploadBean uploadItemConcilia,
            BindingResult result, WebRequest webRequest, Model model) throws UnsupportedEncodingException {
        
           
       
         String descripcionCod = new String(descripcion.getBytes("ISO-8859-15"), "UTF-8");
         
        
        boolean isSave = true;
        SimpleDateFormat formatoDelTexto2 = new SimpleDateFormat("dd/MM/yyyy");
        
         String compania = model.asMap().get("compania").toString();
         String usuario = model.asMap().get("usuario").toString();
     
         System.out.println("Cargando Archivo");
               
        ExtJSFormResult extjsFormResult = new ExtJSFormResult();
        
        if (model.asMap().get("usuario") == null) {

            isSave = false;
            extjsFormResult.setMessage("Sesion no valida");
            extjsFormResult.setSuccess(isSave);
            return extjsFormResult.toString();
        }
        
        System.out.println("Session Valida");

        String path = "";
        String url = "";
        String extension = "";
        String nombreArc ="";

        try {
            MultipartFile file = uploadItemConcilia.getFile();

            InputStream inputStream = null;
            OutputStream outputStream = null;
            if (file.getSize() > 0) {
                inputStream = file.getInputStream();
                long maxSizeDocument;
                long maxSize = new Long("5145728");
                
                System.out.println("Tamaño en server: "+maxSize);
                System.out.println("Tamaño archivo: "+file.getSize());
                
                if (file.getSize() > maxSize) {
                    //System.out.println("File Size:::" + file.getSize());
                    isSave = false;
                    extjsFormResult.setMessage("Tamaño no valido");
                    extjsFormResult.setSuccess(isSave);
                    return extjsFormResult.toString();
                }

                int i = file.getOriginalFilename().lastIndexOf('.');
                
//                System.out.println("Nombre:" + file.getOriginalFilename());
                
               
                    extension = file.getOriginalFilename().substring(i + 1);
                    nombreArc = file.getOriginalFilename().substring(0 , i);
//                    System.out.println("i:" + i);
//               System.out.println("exte:" + extension);
//               System.out.println("nombreArc:" + nombreArc);
               // System.out.println("exte:" + extension);
//                if (!extension.trim().equals("pdf")) {
//                    isSave = false;
//                    extjsFormResult.setMessage("Archivo pdf no valido");
//                    extjsFormResult.setSuccess(isSave);
//                    return extjsFormResult.toString();
//                }
                String hora = "" + System.currentTimeMillis();
                // path ="D:/Administrategia/Empresarial/upload/"+compania+"/"+id+"/"+nombreArc+"."+extension;
                path ="D:/carga/edoCta/"+compania+"/"+nombreArc+"."+extension;
              
                 url ="D:/carga/edoCta/"+compania+"/"+nombreArc+"."+extension;
                 
                 revisarDirectorio("D:/carga/edoCta/"+compania);
                 
                 System.out.println("Ruta: "+path);
                 System.out.println("url: "+url);
             
                outputStream = new FileOutputStream(path);

                int readBytes = 0;
                byte[] buffer = new byte[10000];
                
                System.out.println("Empezando Carga...");
                
                while ((readBytes = inputStream.read(buffer, 0, 10000)) != -1) {
                    outputStream.write(buffer, 0, readBytes);
                }
                outputStream.close();
                inputStream.close();
                
                System.out.println("Archivo Guardado");
            }

            
            
               Querys que = new Querys();
               String store = que.getSQL("InsertaArchivoConcilia");
               
                   Map parametros = new HashMap();
             
 //  P_SID              VARCHAR2,

                parametros.put("COMPANIA", compania);
                parametros.put("BANCO",banco);
                parametros.put("CUENTA",cuenta);
                parametros.put("CALENDARIO",calendario);
                parametros.put("PERIODO",periodo);
                parametros.put("TIPO",tipo);
                parametros.put("DESCRIPCION",descripcionCod);
                parametros.put("PATH",path);
                parametros.put("URL",url);
                parametros.put("NOMBRE_ARCHIVO",nombreArc+"."+extension);
                parametros.put("USUARIO",usuario);

                
           



               int val = processDao.execute(store, parametros);
                
            System.out.println("Datos Insertados");
            String reportIso = "";
            if (banco.equalsIgnoreCase("012")){
              reportIso= "https://www.appferaz1.com/fenius/servlet/ada_ServAppletSqr?dic_sistema=empres&catalogo=Pro_carga_conciliacion&dic_idioma=Esp&dic_estado=66&ck_htm=on&compania="+compania+"&archivo="+nombreArc+"."+extension+"&reporte=Pro_Carga_conciliacion";
            } 
            
            if (banco.equalsIgnoreCase("014")){
              reportIso= "https://www.appferaz1.com/Conciliaciones/CargaDatosSan.jsp?compania="+compania+"&archivo="+nombreArc+"."+extension+"&calendario="+calendario+"&periodo="+periodo+"&cuenta="+cuenta;
            } 
            
//            if (banco.equalsIgnoreCase("014")){
//              reportIso= "https://www.appferaz1.com/fenius/servlet/ada_ServAppletSqr?dic_sistema=empres&catalogo=Pro_carga_conciliacion&dic_idioma=Esp&dic_estado=66&ck_htm=on&compania="+compania+"&archivo="+nombreArc+"."+extension+"&reporte=PRO_carga_conciliacion_Sant";
//            } 
            
            if (banco.equalsIgnoreCase("002")){
                
                if(tipo.equalsIgnoreCase("M")){
                     reportIso= "https://appferaz1.com/Conciliaciones/CargaDatosMen.jsp?compania="+compania+"&archivo="+nombreArc+"."+extension;
                }
                
                if(tipo.equalsIgnoreCase("P")){
                     reportIso= "https://appferaz1.com/Conciliaciones/CargaDatos.jsp?compania="+compania+"&archivo="+nombreArc+"."+extension;
                }
                
            } 
            
            System.out.println("reportIso: "+reportIso);
            
            if(reportIso.equalsIgnoreCase("")){
                           isSave = false;
                          extjsFormResult.setMessage("Error al guardar los datos. El sistema no cuenta con la carga del estado de cuenta para el banco asignado.");
                              extjsFormResult.setSuccess(isSave);
                          return extjsFormResult.toString();
            }
              
              String reporte = new String(reportIso.getBytes("ISO-8859-15"), "UTF-8");
              System.out.println("Reporte codificado: "+reporte);  
              if(reporte.contains(" ")){
                  reporte = reporte.replace(" ", "%20");
              }
              
              try{
                        URL url2 = new URL(reporte);
                          URLConnection urlConnection = url2.openConnection();
                          HttpURLConnection connection = null;
                          if(urlConnection instanceof HttpURLConnection)
                          {
                             connection = (HttpURLConnection) urlConnection;
                          }
                          else
                          {
                             System.out.println("Please enter an HTTP URL.");

                          }
                          BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                         //BufferedReader in = new BufferedReader(new InputStreamReader(((HttpURLConnection) (new URL(reporte)).openConnection()).getInputStream(), Charset.forName("UTF-8")));
                         // BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream(), Charset.forName("UTF-8")));
                          String urlString = "";
                          String current;
                          while((current = in.readLine()) != null)
                          {
                             urlString += current;
                          }
                          System.out.println(urlString);



                      if (val > 0) {
                             isSave = true;
                          extjsFormResult.setMessage("Archivo Guardado");
                          extjsFormResult.setSuccess(isSave);
                          return extjsFormResult.toString();

                      } else {

                           isSave = false;
                          extjsFormResult.setMessage("Error al guardar los datos");
                              extjsFormResult.setSuccess(isSave);
                          return extjsFormResult.toString();
                      }
                  } catch (Exception e) {
                        e.printStackTrace();
                        isSave = false;
                         extjsFormResult.setMessage("Error al guardar los datos");
                              extjsFormResult.setSuccess(isSave);
                          return extjsFormResult.toString();
                    }

        } catch (Exception e) {
            e.printStackTrace();
            isSave = false;
        }




        extjsFormResult.setSuccess(isSave);

        return extjsFormResult.toString();
    }
    
    
       @RequestMapping(value = "/conciliaCompl", method = RequestMethod.POST )   
    public @ResponseBody
    String createConciliac(
            @RequestParam("compania") String compania,
            @RequestParam("banco") String banco,
            @RequestParam("cuenta") String cuenta,
            @RequestParam("calendario") String calendario,
            @RequestParam("periodo") String periodo,
            @RequestParam("fecha_ini") String fecha_ini,
            @RequestParam("fecha_fin") String fecha_fin,
//            @RequestParam("cboTipoConciliaCarga") String tipo,
//            @RequestParam("cboCalendarioConciliaCarga") String calendario,
//            @RequestParam("cboPeriodoConciliaCarga") String periodo,
//            @RequestParam("archDESCRIPCIONConcilia") String descripcion,
//            @RequestParam("dtCompaniaConciliaCarga") String companiaC,
//            @RequestParam("cboCuentaConciliaCarga") String cuenta,
            
//            FileUploadBean uploadItemConcilia,
        WebRequest webRequest, Model model) throws UnsupportedEncodingException {
        
           
       
         
        
        boolean isSave = true;
        SimpleDateFormat formatoDelTexto2 = new SimpleDateFormat("dd/MM/yyyy");
        
         //String compania = model.asMap().get("compania").toString();
         String usuario = model.asMap().get("usuario").toString();
     
         System.out.println(compania);
               
        ExtJSFormResult extjsFormResult = new ExtJSFormResult();
        
        if (model.asMap().get("usuario") == null) {

            isSave = false;
            extjsFormResult.setMessage("Sesion no valida");
            extjsFormResult.setSuccess(isSave);
            return extjsFormResult.toString();
        }
        
        System.out.println("Session Valida");

        String path = "";
        String url = "";
        String extension = "";
        String nombreArc ="";

        try {
           
             String reportIso= "https://www.appferaz1.com/fenius/servlet/ada_ServAppletSqr?dic_sistema=empres&catalogo=REP_GENERA_CON_COMP&dic_idioma=Esp&dic_estado=66&ck_htm=on&compania="+compania+"&banco="+banco+"&cuenta="+cuenta+"&calendario="+calendario+"&periodo="+periodo+"&fecha_ini="+fecha_ini+"&fecha_fin="+fecha_fin+"&reporte=REP_GENERA_CON_COMP";
            
            
            if(reportIso.equalsIgnoreCase("")){
                           isSave = false;
                          extjsFormResult.setMessage("Error al guardar los datos. El sistema no cuenta con la carga del estado de cuenta para el banco asignado.");
                              extjsFormResult.setSuccess(isSave);
                          return extjsFormResult.toString();
            }
              
              String reporte = new String(reportIso.getBytes("ISO-8859-15"), "UTF-8");
              System.out.println("Reporte codificado: "+reporte);  
              if(reporte.contains(" ")){
                  reporte = reporte.replace(" ", "%20");
              }
              
              try{
                        URL url2 = new URL(reporte);
                          URLConnection urlConnection = url2.openConnection();
                          HttpURLConnection connection = null;
                          if(urlConnection instanceof HttpURLConnection)
                          {
                             connection = (HttpURLConnection) urlConnection;
                          }
                          else
                          {
                             System.out.println("Please enter an HTTP URL.");

                          }
                          BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                         //BufferedReader in = new BufferedReader(new InputStreamReader(((HttpURLConnection) (new URL(reporte)).openConnection()).getInputStream(), Charset.forName("UTF-8")));
                         // BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream(), Charset.forName("UTF-8")));
                          String urlString = "";
                          String current;
                          while((current = in.readLine()) != null)
                          {
                             urlString += current;
                          }
                          System.out.println(urlString);



                     // if (!urlString.equalsIgnoreCase("")) {
                             isSave = true;
                          extjsFormResult.setMessage("Se completo el proceso con exito");
                          extjsFormResult.setSuccess(isSave);
                          return extjsFormResult.toString();

//                      } else {
//
//                           isSave = false;
//                          extjsFormResult.setMessage("Error al guardar los datos");
//                              extjsFormResult.setSuccess(isSave);
//                          return extjsFormResult.toString();
//                      }
                  } catch (Exception e) {
                        e.printStackTrace();
                        isSave = false;
                         extjsFormResult.setMessage("Error al guardar los datos");
                              extjsFormResult.setSuccess(isSave);
                          return extjsFormResult.toString();
                    }

        } catch (Exception e) {
            e.printStackTrace();
            isSave = false;
        }




        extjsFormResult.setSuccess(isSave);

        return extjsFormResult.toString();
    }
    
    
    @RequestMapping(value = "/delete", method = RequestMethod.POST)
    public @ResponseBody
    ResponseQuery deleteOtros( String idEdo,String idErp, WebRequest webRequest, Model model) {

        ResponseQuery rq = new ResponseQuery();
   //     System.out.println("data factura cancelacion:"+data);
        
       


            String compania = model.asMap().get("compania").toString();
            String usuario = model.asMap().get("usuario").toString();
            ObjectMapper mapper = new ObjectMapper();
          
                   
        try{
            
             
           
            
              Querys que = new Querys();
               String store = que.getSQL("BorraConciliacion");
               
                   Map parametros = new HashMap();
             
 //  P_SID              VARCHAR2,

             
                parametros.put("compania", compania);
                parametros.put("folio",idEdo);
                parametros.put("idErp",idErp);
           



               int val = processDao.execute(store, parametros);
          
            
            if (val > 0){
            
                
                    rq.setSuccess(true);
                    rq.setData(null);
                    rq.setMsg("Registro eliminado correctamente");
              
                
            }else{
                
                rq.setSuccess(true);
                rq.setData(null);
                rq.setMsg("Error al eliminar");
                
            }
            

        }catch(Exception e){
            e.printStackTrace();
            rq.setSuccess(false);
            rq.setData(null);
            rq.setMsg("Error al eliminar");
           return rq;
        }
        
        return rq;
    }
    

    @RequestMapping(value = "/upload", method = RequestMethod.POST)
    public @ResponseBody
    Map upload(MultipartHttpServletRequest request, HttpServletResponse response,@RequestParam("origen") String origen, Model model) {

        Map<String, Object> filesm = new HashMap<String, Object>();
        
    ///    log.info("maxSize:" + maxSizeDocument);

        System.out.println("En carga");
    
        if (model.asMap().get("compania") == null) {
//            msgErr ="Sesion no valida";
            //System.out.println("Sesion no valida");
            filesm.put("files", "Sesion no valida");
            return filesm;
        }
        
       
        String compania = model.asMap().get("compania").toString();
        String usuario = model.asMap().get("usuario").toString();
//         System.out.println("usuario:"+usuario);
        LinkedList<FileMeta> files = new LinkedList<FileMeta>();
        //1. build an iterator
        Iterator<String> itr = request.getFileNames();
        MultipartFile mpf = null;
        boolean err = false;
        //2. get each file
        while (itr.hasNext()) {

            mpf = request.getFile(itr.next());

            if (files.size() >= 10) {
                files.pop();
            }

            //2.3 create new fileMeta
            FileMeta fileMeta = new FileMeta();
            fileMeta.setFileName(mpf.getOriginalFilename());
            fileMeta.setFileSize(mpf.getSize() / 1024 + " Kb");
            fileMeta.setFileType(mpf.getContentType());
//              System.out.println("mpf.getSize():"+mpf.getSize());
//            fileMeta.setError("Error");

            try {
                fileMeta.setBytes(mpf.getBytes());

                long maxSize = new Long(maxSizeDocument);
//                    System.out.println("maxSize:"+maxSize);
//                    System.out.println("mpf.getSize():"+mpf.getSize());
                if (mpf.getSize() > maxSize) {
                    fileMeta.setError("Tamaño no valido");
                    fileMeta.setBytes(null);
                    files.add(fileMeta);
                    continue;
                }

            //    log.info("maxSize:" + maxSize);

                int i = mpf.getOriginalFilename().lastIndexOf('.');
                // String hora = "" + System.currentTimeMillis();
//                System.out.println("Nombre:" + file.getOriginalFilename());
               String extension = mpf.getOriginalFilename().substring(i + 1);
                String nombreArc = mpf.getOriginalFilename().substring(0, i);
             //   log.info("extension:" + extension);
             //   log.info("nombreArc:" + nombreArc);
                if (!extension.toLowerCase().trim().equals("csv")) {

                    fileMeta.setError("Archivo no valido");
                    fileMeta.setBytes(null);
                    files.add(fileMeta);
                    continue;

                }
//                      System.out.println("nombreArc:"+nombreArc);
//               String hora = "" + System.currentTimeMillis();

//               System.out.println("path:"+path);
                // copy file to local disk (make sure the path "e.g. D:/temp/files" exists)
                //FileCopyUtils.copy(mpf.getBytes(), new FileOutputStream("H:/temp/files/" + mpf.getOriginalFilename()));
                String timeT=""+System.currentTimeMillis();


                File temp = File.createTempFile(nombreArc+timeT, ".tmp");
                temp.deleteOnExit();
                FileCopyUtils.copy(mpf.getBytes(), temp);

                if (extension.toLowerCase().trim().equals("csv")) {
               
                 

                      
                            
                            String path ;

                             //   revisarDirectorio(dirOutFileDocument + compania);
                             //   path = dirOutFileDocument + compania + "/" + nombreUuid + "." + extension;

                             path="D:/EstadosCta/test" + "." + extension;
                            File ff = new File(path);
                            FileCopyUtils.copy(mpf.getBytes(), ff);
                 //           log.info("path:"+path);
                 //           int data = app.cargaComprobante4(path, compania,usuario,null, origen);
                 //           log.info("cargaComprobante:" + data);
                 
                      //  String archCSV = "D:\\ISO-Codes.csv";
                        System.out.println("Leyendo Archivos csv");
                        leerCSVSimple(compania,path);
                        
                            
                          //  if (data == 0) {
                            //    fileMeta.set("Error al leer el CFDI");
                          //  } else {
                          //      fileMeta.setError(null);
                                fileMeta.setMsg("Guardado con exito");
                          //  }

                        

                    
                     
                      
                  
                }

            } catch (IOException e) {
                fileMeta.setError("Error al guardar archivo");
                // TODO Auto-generated catch block
                System.out.println("Error Crear Archivo" + e);
            } catch (Exception e) {
                fileMeta.setError("Error al guardar archivo");
                System.out.println("Error al guardar archivo" + e);
            }
            //2.4 add to files
            fileMeta.setBytes(null);
            files.add(fileMeta);
            //log.info("filesSize:"+files.size());
        }

        filesm.put("files", files);

        // result will be like this
        // [{"fileName":"app_engine-85x77.png","fileSize":"8 Kb","fileType":"image/png"},...]
        return filesm;

    }
    
    
    
        @RequestMapping(value = "/concilia", method = RequestMethod.POST)
    public @ResponseBody
    ResponseQuery processConcilia( @RequestParam("data") String data,WebRequest webRequest, Model model) {
        ResponseQuery rq = new ResponseQuery();
        
        
        
        System.out.println("data:"+data);
        if( model.asMap().get("compania")==null){
            rq.setSuccess(false);
            rq.setData(null);
            rq.setMsg("Sesion no valida");
            return rq;
        }
        
        String compania = model.asMap().get("compania").toString();
        String usuario = model.asMap().get("usuario").toString();
        
        try{
          ObjectMapper mapper = new ObjectMapper();
            DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
            
            String idErr = ""+System.currentTimeMillis();

            mapper.setDateFormat(df);
            List<ConciliaDTO> lista = mapper.readValue(data,
                    mapper.getTypeFactory().constructCollectionType(List.class,
                            ConciliaDTO.class));
          
                        
            Iterator<ConciliaDTO> it = lista.iterator();
            
            ErpEdoCuentaXErp erp = new ErpEdoCuentaXErp();
            
            ErpEdoCuentaXErpId erpIdErp = new ErpEdoCuentaXErpId();
            
            while (it.hasNext()) {
//                 System.out.println("-------------------------------------------------------------------");
                 ConciliaDTO ss = it.next();
      
                 
                erpIdErp.setCompania(compania);
                erpIdErp.setIdEdoCuenta(Integer.parseInt(ss.sec));
                erpIdErp.setIdErp(Long.parseLong(ss.numeroFe));
                erpIdErp.settDocErp(ss.tDoc);
                erp.setId(erpIdErp);
                erp.setTipoConciliacion("A");
                erp.setImportePagoErp(new BigDecimal(ss.totPesos));
                erp.setBancoErp(ss.banco);
                
                erpEdoCuentaXErpDao.save(erp);
//             

            }
            
            

            
      //     if (result2 == true){ 
             rq.setSuccess(true);
             rq.setData(null);
             rq.setMsg("Conciliacion Guardada");

        }catch(Exception e){
            e.printStackTrace();
        }
        return rq;
    }
    
    
        @RequestMapping(value = "/conciliaManual", method = RequestMethod.POST)
    public @ResponseBody
    ResponseQuery processConcilia( @RequestParam("data") String data,@RequestParam("edoCta") String edoSec,@RequestParam("banco") String banco,WebRequest webRequest, Model model) {
        ResponseQuery rq = new ResponseQuery();
        
        
        
        System.out.println("data:"+data);
        if( model.asMap().get("compania")==null){
            rq.setSuccess(false);
            rq.setData(null);
            rq.setMsg("Sesion no valida");
            return rq;
        }
        
        String compania = model.asMap().get("compania").toString();
        String usuario = model.asMap().get("usuario").toString();
        
        try{
          ObjectMapper mapper = new ObjectMapper();
            DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
            
            String idErr = ""+System.currentTimeMillis();

            mapper.setDateFormat(df);
            List<ConciliaManualDTO> lista = mapper.readValue(data,
                    mapper.getTypeFactory().constructCollectionType(List.class,
                            ConciliaManualDTO.class));
          
                        
            Iterator<ConciliaManualDTO> it = lista.iterator();
            
            ErpEdoCuentaXErp erp = new ErpEdoCuentaXErp();
            
            ErpEdoCuentaXErpId erpIdErp = new ErpEdoCuentaXErpId();
            
            while (it.hasNext()) {
//                 System.out.println("-------------------------------------------------------------------");
                 ConciliaManualDTO ss = it.next();
      
                 
                erpIdErp.setCompania(compania);
                erpIdErp.setIdEdoCuenta(Integer.parseInt(edoSec));
                erpIdErp.setIdErp(Long.parseLong(ss.numero));
                erpIdErp.settDocErp(ss.origen);
                erp.setId(erpIdErp);
                erp.setTipoConciliacion("A");
                erp.setImportePagoErp(new BigDecimal(ss.pago));
                erp.setBancoErp(banco);
                
                erpEdoCuentaXErpDao.save(erp);
//             

            }
            
            

            
      //     if (result2 == true){ 
             rq.setSuccess(true);
             rq.setData(null);
             rq.setMsg("Conciliacion Guardada");

        }catch(Exception e){
            e.printStackTrace();
        }
        return rq;
    }
    
    
    
            
    @RequestMapping(value = "/generaAgrComisiones", method = RequestMethod.POST)
     public @ResponseBody
    ResponseQuery2 insertActionAgrCom(WebRequest webRequest, Model model) throws ParseException {

        ResponseQuery2 rq = new ResponseQuery2();
        SimpleDateFormat formatoDelTexto = new SimpleDateFormat("dd/MM/yyyy");
        SimpleDateFormat formatFecha = new SimpleDateFormat("dd/MM/yyyy");
       
//
        if (model.asMap().get("compania") == null) {
            rq.setSuccess(false);
            rq.setData(null);
            rq.setMsg("La sesion no es valida.");
            return rq;
        }
        String compania = model.asMap().get("compania").toString();
        String usuario = model.asMap().get("usuario").toString();
        
        String fechaIni = webRequest.getParameter("dtFECHA_INIConciliaAgrCom").toString();
        String fechaFin = webRequest.getParameter("dtFECHA_FINConciliaAgrCom").toString();
        
        
      
        int id = 0;
        try {
          
         
               Querys que = new Querys();
               String store = que.getSQL("GeneraComisiones");
               
                   Map parametros = new HashMap();
             
 //  P_SID              VARCHAR2,

             
                parametros.put("compania", compania);
                parametros.put("fechaIni",fechaIni);
                parametros.put("fechaFin",fechaFin);
           



               int val = processDao.execute(store, parametros);
                
                 if (val > 0) {
               
                 
                            rq.setSuccess(true);
                            rq.setMsg(String.valueOf(id));

                    } else {
                        rq.setSuccess(false);
                        rq.setData(null);
                        rq.setMsg("Error al generar datos");
                    }
            
            
            
            
            
          
            

           

        } catch (Exception e) {
            e.printStackTrace();
        }

        return rq;

    }
    
            @RequestMapping(value = "/generaAgr", method = RequestMethod.POST)
     public @ResponseBody
    ResponseQuery2 insertActionAgr(WebRequest webRequest, Model model) throws ParseException {

        ResponseQuery2 rq = new ResponseQuery2();
        SimpleDateFormat formatoDelTexto = new SimpleDateFormat("dd/MM/yyyy");
        SimpleDateFormat formatFecha = new SimpleDateFormat("dd/MM/yyyy");
       
//
        if (model.asMap().get("compania") == null) {
            rq.setSuccess(false);
            rq.setData(null);
            rq.setMsg("La sesion no es valida.");
            return rq;
        }
        String compania = model.asMap().get("compania").toString();
        String usuario = model.asMap().get("usuario").toString();
        
        String fechaIni = webRequest.getParameter("dtFECHA_INIConciliaAgr").toString();
        String fechaFin = webRequest.getParameter("dtFECHA_FINConciliaAgr").toString();
        
        
      
        int id = 0;
        try {
          
         
               Querys que = new Querys();
               String store = que.getSQL("GeneraPagosAgr");
               
                   Map parametros = new HashMap();
             
 //  P_SID              VARCHAR2,

             
                parametros.put("compania", compania);
                parametros.put("fechaIni",fechaIni);
                parametros.put("fechaFin",fechaFin);
           



               int val = processDao.execute(store, parametros);
                
                 if (val > 0) {
               
                 
                            rq.setSuccess(true);
                            rq.setMsg(String.valueOf(id));

                    } else {
                        rq.setSuccess(false);
                        rq.setData(null);
                        rq.setMsg("Error al generar datos");
                    }
            
            
            
            
            
          
            

           

        } catch (Exception e) {
            e.printStackTrace();
        }

        return rq;

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

    public String formatFecha(Date date, String format) {
        try {
            return new SimpleDateFormat(format).format(date);
        } catch (Exception e) {
            return "";
        }
    }
    
    
    public void leerCSVSimple(String compania,String path) throws IOException, ParseException {
        
        
           System.out.println(path);
           
           SimpleDateFormat formatFecha2 = new SimpleDateFormat("dd/MM/yyyy");
	   SimpleDateFormat formatFecha3 = new SimpleDateFormat("ddMMyyyy");	
            System.out.println("Leyendo Archivos");
		// Abro el .csv en buffer de lectura
		BufferedReader bufferLectura = new BufferedReader(new FileReader(path));
		
		// Leo una línea del archivo
		String linea = bufferLectura.readLine();
                
                
                ErpFeCargaBanco ban = new ErpFeCargaBanco();
                ErpFeCargaBancoId banId = new ErpFeCargaBancoId();
                
                int contador= 1;
                String strCurrentLine;
                
                System.out.println("Leyendo linea1");
                
		while ((strCurrentLine = bufferLectura.readLine()) != null) {    
                    System.out.println(strCurrentLine);  
                    
                    String[] campos = strCurrentLine.split(String.valueOf(","));
			//System.out.println(Arrays.toString(campos));
                        
                            
                            System.out.println("COMPANIA: "+ compania);
                            
                            
                        
                            banId.setCompania(compania);
                            
                            
                            banId.setSec(erpFeCargaBancoDao.getMaxIdErpFeCargaBanco(banId));
                            ban.setId(banId);
                            System.out.println("Llave insertada");
                            ban.setNoCuenta(campos[0].replace("\"", "").trim());
                            ban.setDescripcion(campos[4].replace("\"", "").trim());
                            ban.setNomArchivo("Test");
                            ban.setReferencia(campos[8].replace("\"", "").trim());
                            ban.setImporte(new BigDecimal(campos[6]));
                            ban.setIdTipoPago(campos[4].replace("\"", "").trim());
                            ban.setEstatus("P");
                            String fecha = formatFecha(formatFecha3.parse(campos[1].replace("\"", "").trim()),"dd/MM/yyyy");
                
                            ban.setFechaEmision(formatFecha2.parse(fecha));
                            ban.setFechaPagado(formatFecha2.parse(fecha));
                            ban.setConcepto(campos[9].replace("\"", "").trim());

                            if (campos[5].replace("\"", "").trim().equalsIgnoreCase("+")){

                                ban.setTipoMovto("I");

                            }

                            if (campos[5].replace("\"", "").trim().equalsIgnoreCase("-")){

                                ban.setTipoMovto("E");

                            }
                            
                            erpFeCargaBancoDao.save(ban);

                    
                }  
                
              
		
		// CIerro el buffer de lectura
		if (bufferLectura != null) {
			bufferLectura.close();
		}
	}

    public String getDirOutFileDocument() {
        return dirOutFileDocument;
    }

    public void setDirOutFileDocument(String dirOutFileDocument) {
        this.dirOutFileDocument = dirOutFileDocument;
    }

    public String getDirUrlOutDocument() {
        return dirUrlOutDocument;
    }

    public void setDirUrlOutDocument(String dirUrlOutDocument) {
        this.dirUrlOutDocument = dirUrlOutDocument;
    }

    public String getMaxSizeDocument() {
        return maxSizeDocument;
    }

    public void setMaxSizeDocument(String maxSizeDocument) {
        this.maxSizeDocument = maxSizeDocument;
    }

    public ProcessDao getProcessDao() {
        return processDao;
    }

    public void setProcessDao(ProcessDao processDao) {
        this.processDao = processDao;
    }

    public void setErpFeCargaBancoDao(ErpFeCargaBancoDaoImpl erpFeCargaBancoDao) {
        this.erpFeCargaBancoDao = erpFeCargaBancoDao;
    }

    public void setErpEdoCuentaXErpDao(ErpEdoCuentaXErpDao erpEdoCuentaXErpDao) {
        this.erpEdoCuentaXErpDao = erpEdoCuentaXErpDao;
    }



}
