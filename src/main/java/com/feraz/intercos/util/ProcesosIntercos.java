/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.feraz.intercos.util;

import com.feraz.facturas.webcontrolfe.App.App;
import com.feraz.facturas.webcontrolfe.dao1.ErpFeComprobantesDao;
import com.feraz.facturas.webcontrolfe.dto.FileInfo;
import com.feraz.facturas.webcontrolfe.dto.PolizasInfo;
import com.feraz.facturas.webcontrolfe.model.ErpFeComprobantes;
import com.feraz.facturas.webcontrolfe.model.ErpFeComprobantesId;
import com.feraz.facturas.webcontrolfe.validation.ValidaVersionCFDI;
import com.feraz.intercos.dao.ErpWsIntercosDao;
import com.feraz.intercos.model.ErpWsIntercos;
import com.feraz.intercos.model.ErpWsIntercosId;
import com.feraz.mx.sat.cfdi4.Comprobante4;
import com.feraz.sat.cfdi.check.ValidaSAT;
import com.feraz.sat.cfdi.model.ResponseWSValida;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.nio.charset.StandardCharsets;
import java.nio.file.CopyOption;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import org.apache.commons.fileupload.disk.DiskFileItem;
import org.jamh.data.process.ProcessDao;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.validation.ObjectError;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

/**
 *
 * @author vavi
 */
public class ProcesosIntercos {
    
     @Value("${document.file.dirOutDocumentFE}")
    private String dirOutFileDocument;
    @Value("${document.file.dirURLOutDocumentFE}")
    private String dirUrlOutDocument;
    @Value("${document.file.maxZiseFE}")
    private String maxSizeDocument;
    private String msgErr;
    private String extension = "";
    //private String nombreArc = "";
    private String path;
    private String pathXML;
    private String url;
    
    private ErpWsIntercosDao erpWsIntercosDao;
    private ValidaVersionCFDI validaVersionCFDI;
    private App app;
    private ProcessDao processDao;
    private ErpFeComprobantesDao erpFeComprobantesDao;
    
    public boolean validaUUID (String compania,String uuid){
    
          System.out.println(compania);
          System.out.println(uuid);
       ErpWsIntercos inter =  erpWsIntercosDao.findErpIntercos(compania, uuid);
       
         return inter != null;
    
    }
    
    
    public int buscaXml(String urlXml, String uuid, String urlC) throws MalformedURLException, IOException{
        
        
        
           //     URL url = new URL("http://appferaz1.com/carga_erp/xml/OFICDUR/2017/11/A7B4CCD4-0284-4BF3-8184-EE21768E6DE9.xml");
            
           URL url = new URL(urlXml);
           
           System.out.println("Generando url");
           
           URLConnection uc = url.openConnection();
            uc.connect();
            //Creamos el objeto con el que vamos a leer
            BufferedReader in = new BufferedReader(new InputStreamReader(uc.getInputStream(), StandardCharsets.UTF_8));
            String inputLine;
            String contenido = "";
            System.out.println("Escribiendo Contenido");
            while ((inputLine = in.readLine()) != null) {
                contenido += inputLine + "\n";
            }
            in.close();
            
            System.out.println("contenido xml WSCONTAB");
            
            System.out.println(contenido);
            
           //urlC es el archivo que se creara ejemplo D://TEST/prueba.txt
           File archivo=new File(urlC);
           
           System.out.println("Archivo definido");
            
            try
                {
                //Crear un objeto File se encarga de crear o abrir acceso a un archivo que se especifica en su constructor
                
                System.out.println("validando existencia...");
                 if (!archivo.exists()) {
                     
                     System.out.println("archivo no existe");
                     Writer escribir = null;
                    BufferedWriter out = null;
                
                        escribir = new OutputStreamWriter(new FileOutputStream(archivo), StandardCharsets.UTF_8);
                     
                   //   FileWriter escribir=new FileWriter(archivo,true);
                      System.out.println("Escribimos en el archivo con el metodo write ");
                      
                   

                        //Escribimos en el archivo con el metodo write 
                        escribir.write(contenido);
                        
                        System.out.println("Archivo Escrito");

                        //Cerramos la conexion
                        escribir.close();
                    
                }else{
                 
                    return 2;
                 }

                //Crear objeto FileWriter que sera el que nos ayude a escribir sobre archivo
               
                }

                //Si existe un problema al escribir cae aqui
                catch(Exception e)
                {
                System.out.println("Error al escribir");
                e.printStackTrace();
                
                return 0;
                
                }
            
            FileReader fr=new FileReader(archivo);
             fr.close();
                
            
            return 1;
        
        
        
      
    
            
    
    
    }
    
    
        public int buscaXml2(String urlXml, String urlC) throws MalformedURLException, IOException{
        
        
        
           //     URL url = new URL("http://appferaz1.com/carga_erp/xml/OFICDUR/2017/11/A7B4CCD4-0284-4BF3-8184-EE21768E6DE9.xml");
            
         String contenido = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>" + urlXml;
         
         System.out.println("Imprimiendo contenido");
         
            System.out.println(contenido);
            
         System.out.println("url guardado");
         
            System.out.println(urlC);
            
           
           File archivo=new File(urlC);
           
           System.out.println("Archivo definido");
            
            try
                {
                //Crear un objeto File se encarga de crear o abrir acceso a un archivo que se especifica en su constructor
                
                System.out.println("validando existencia...");
                 if (!archivo.exists()) {
                     
                     System.out.println("archivo no existe");
                     Writer escribir = null;
                    BufferedWriter out = null;
                
                        escribir = new OutputStreamWriter(new FileOutputStream(archivo), StandardCharsets.UTF_8);
                     
                   //   FileWriter escribir=new FileWriter(archivo,true);
                      System.out.println("Escribimos en el archivo con el metodo write ");
                      
                   

                        //Escribimos en el archivo con el metodo write 
                        escribir.write(contenido);
                        
                        System.out.println("Archivo Escrito");

                        //Cerramos la conexion
                        escribir.close();
                    
                }else{
                 
                    return 2;
                 }

                //Crear objeto FileWriter que sera el que nos ayude a escribir sobre archivo
               
                }

                //Si existe un problema al escribir cae aqui
                catch(Exception e)
                {
                System.out.println("Error al escribir");
                e.printStackTrace();
                
                return 0;
                
                }
            
            FileReader fr=new FileReader(archivo);
             fr.close();
                
            
            return 1;
        
        
        
      
    
            
    
    
    }
    
    
    public boolean actualizaEstatus(String compania, int numero){
    
    
        
        ErpFeComprobantes comp = new ErpFeComprobantes();
        ErpFeComprobantesId compId = new ErpFeComprobantesId();
        
        compId.setCompania(compania);
        compId.setNumero(numero);
        
        comp.setId(compId);
      
        
        boolean updateCan = erpFeComprobantesDao.cancelaFactura("CANF", comp);
        
        
        
         return updateCan;
        
        
    }
    
    public String verificaCompania(String rfcReceptor){
        
             Map companiaR = new HashMap();
                 
               companiaR.put("rfcReceptor", rfcReceptor);
               
                   List listCompaniaInt = processDao.getMapResult("BuscaCompaniaIntercos", companiaR);
                 
                   if(listCompaniaInt == null || listCompaniaInt.isEmpty()){
                   
                      return "N/A";
                   }else{
                    Map comR = (HashMap) listCompaniaInt.get(0);
                    String companiaRecp = comR.get("COMPANIA").toString();
                    
                    
             
                     return companiaRecp;
                   }
    
    
    }
    
    public int verificaDatosCancelar(String compania,String uuid){
        
             Map companiaCanc = new HashMap();
                 
               companiaCanc.put("companiaC", compania);
               companiaCanc.put("uuidC", uuid);
               
                   List listCancelaInt = processDao.getMapResult("BuscaDatosCancelacion", companiaCanc);
                 
                   if(listCancelaInt == null || listCancelaInt.isEmpty()){
                   
                      return -1;
                   }else{
                    Map comC = (HashMap) listCancelaInt.get(0);
                    String uuidRec = comC.get("UUID").toString();
                    String compRec = comC.get("COMPANIA").toString();
                    String numeroRec = comC.get("NUMERO").toString();
                    
                    if(comC.get("ESTATUS_CXP") == null){
                        
                        return Integer.parseInt(numeroRec);
                    
                    }
                    
                    String estatusCxp = comC.get("ESTATUS_CXP").toString();
                    
                    if(estatusCxp != null){
                            if (estatusCxp.equalsIgnoreCase("TES") || estatusCxp.equalsIgnoreCase("PAG") || estatusCxp.equalsIgnoreCase("IMP") || estatusCxp.equalsIgnoreCase("FG") ){


                              return 0;
                           }else{
                                
                                return Integer.parseInt(numeroRec);
                            
                            
                            }
                   
                   }else{
                        
                        return Integer.parseInt(numeroRec);
                    }
                           
                           
                   }
    
    
    }
    
    public int insertDatos(ErpWsIntercos erpWsIntercos){
        
        System.out.println("Salvando intercos");
        
      ErpWsIntercosId id = erpWsIntercosDao.save(erpWsIntercos);
      
      if(id == null){
          
          return 0;
      
      }else{
         return 1;
      }
    
    
    }
    
    public int validaCFDICarga(String dirXML){
    
           boolean result = validaVersionCFDI.version33(dirXML);
           boolean result4 = validaVersionCFDI.version4(dirXML);
           
           boolean result2 = validaVersionCFDI.verision32(dirXML);
     
           if(result == true){
               
                 return 3;
               
               
           }else{
               
               
                if(result2 == true){
                    
                    return 2;
                    
           
           
                }else{
                    
                    if (result4 == true){
                    
                     return 4;
                    }else{
                    
                                           return 0;

                    
                    }
                
                
                
                }
               
           
           
           }
           
            


    }
    
    
    
    
    public int cargaCFDIIntercos(String archivoXML, String archivoPDF,String compania, String rfcEmisor,String rfcReceptor,String uuidCfdi) throws FileNotFoundException, IOException, JAXBException{
    
        
        // File file = new File(archivoXML);
      //   File file2 = new File(archivoPDF);
         
         
        //FileInputStream file = new FileInputStream(archivoXML);
        
        
         
            
         String dirCompania;
         
          dirCompania = compania;
        FileInfo fi;
        FileInfo fi2;
        
         boolean result1 = validaVersionCFDI.verision32(archivoXML);
         boolean result2 = validaVersionCFDI.version33(archivoXML);
         boolean result4 = validaVersionCFDI.version4(archivoXML);
         
         System.out.println("result1: " +result1);
         System.out.println("result2: " +result2);
         System.out.println("result4: " +result4);
        
     
        
        

       
        String idErr = "" + System.currentTimeMillis();
        app.setId(idErr);
       PolizasInfo valida;
       
       if(!result2 && !result4 && result1){
            
                valida = app.validaComprobante(compania, archivoXML, archivoPDF, archivoPDF);
              
            }else{
            
               if(result2 && !result4 && !result1){
        
                valida = app.validaComprobante33(compania, archivoXML, archivoPDF, archivoPDF);

            
                }else{
                   
                   if(!result2 && result4 && !result1){

                        valida = app.validaComprobante4(compania, archivoXML, archivoPDF, archivoPDF);

                   }else{
                   
                       valida = app.validaComprobante33(compania, archivoXML, archivoPDF, archivoPDF);
                   
                   }
               
               
                }
            
            }
            
        
        
        
        
          if(!result2 && !result4 && result1){
             System.out.println("Soy version 32");
               if(valida.getComprobante()==null){
            System.out.println("Error al validar el xml");
            return 0;
        }
       // System.out.println("usuario:"+usuario);
        
       // System.out.println("prov.getRfc():"+valida.getComprobante().getEmisor().getRfc());
        //Valida que el RFC emisor sea de la secion
        if(!rfcEmisor.equals(valida.getComprobante().getEmisor().getRfc())){
            
           System.out.println("ERROR RFC del Emisor no es valido");
           
            return 0;
        }
        
        //Valida que el RFC receptor sea de SHW
        if(!rfcReceptor.equals(valida.getComprobante().getReceptor().getRfc())){
            System.out.println("ERROR RFC Receptor No valido");
            //rq.setMsg("ERROR RFC Receptor No valido");
            return 0;
        }
        
                SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
                java.util.Date dateFecha = valida.getComprobante().getFecha();
                 String calendar = formatFecha(dateFecha, "yyyy");
                                 String periodo = formatFecha(dateFecha, "MM");

                       //  revisarDirectorio("D:/carga/xml3/" + compania + "/" + calendar+ "/" + periodo);
                       //  String path = "D:/carga/xml3/" + compania + "/" + calendar + "/" + periodo + "/" + xml;



                           revisarDirectorio("D:/carga/xml/" + compania + "/" + calendar+ "/" + periodo);
                           path = "D:/carga/xml/" + compania + "/" + calendar + "/" + periodo + "/" + uuidCfdi + ".xml";
                           
                            String origen = archivoXML;
                            String destino = path;
                        

                            String result = copyFile_Java7(origen,destino);
                            
                            if(!result.equalsIgnoreCase("success")){
                            
                                 return 0;
                            }
                            
                            
        //Obtener el uuid
         }
         
         
         
        if(result2 && !result4 && !result1){
                System.out.println("Soy version 33");
                JAXBContext jaxbContext = JAXBContext.newInstance(com.feraz.mx.sat.cfdi.Comprobante.class);

                    Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
                    com.feraz.mx.sat.cfdi.Comprobante comp = (com.feraz.mx.sat.cfdi.Comprobante) jaxbUnmarshaller.unmarshal(new FileInputStream(archivoXML));

             
         if(comp==null){
            System.out.println("Error al validar el XML:");
           
            return 0;
        }
       // System.out.println("usuario:"+usuario);
        
       // System.out.println("prov.getRfc():"+valida.getComprobante().getEmisor().getRfc());
        //Valida que el RFC emisor sea de la secion
        if(!rfcEmisor.equals(comp.getEmisor().getRfc())){
            
            System.out.println("ERROR RFC del Emisor no es valido");
            return 0;
        }
        
        //Valida que el RFC receptor sea de SHW
        if(!rfcReceptor.equals(comp.getReceptor().getRfc())){
         
            System.out.println("ERROR RFC Receptor No valido");
            return 0;
        }
        
        
                   SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
               
                            String calendar =  String.valueOf(comp.getFecha().getYear());
                            String periodo = String.valueOf(comp.getFecha().getMonth());

                       //  revisarDirectorio("C:/carga/xml3/" + compania + "/" + calendar+ "/" + periodo);
                       //  String path = "C:/carga/xml3/" + compania + "/" + calendar + "/" + periodo + "/" + xml;



                           revisarDirectorio("D:/carga/xml/" + compania + "/" + calendar+ "/" + periodo);
                           path = "D:/carga/xml/" + compania + "/" + calendar + "/" + periodo + "/" + uuidCfdi + ".xml";
                           
                            String origen = archivoXML;
                            String destino = path;
                        

                            String result = copyFile_Java7(origen,destino);
            
                            if(!result.equalsIgnoreCase("success")){
                            
                                 return 0;
                            }
                            
        }
        
       if(!result2 && result4 && !result1){
                System.out.println("Soy version 4");

                    JAXBContext jaxbContext = JAXBContext.newInstance(Comprobante4.class);

		Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
		Comprobante4 comp = (Comprobante4) jaxbUnmarshaller.unmarshal(new FileInputStream(archivoXML));
             
           
         if(comp==null){
            System.out.println("Error al validar el XML:");
           
            return 0;
        }
       // System.out.println("usuario:"+usuario);
        
       // System.out.println("prov.getRfc():"+valida.getComprobante().getEmisor().getRfc());
        //Valida que el RFC emisor sea de la secion
        if(!rfcEmisor.equals(comp.getEmisor().getRfc())){
            
            System.out.println("ERROR RFC del Emisor no es valido");
            return 0;
        }
        
        //Valida que el RFC receptor sea de SHW
        if(!rfcReceptor.equals(comp.getReceptor().getRfc())){
         
            System.out.println("ERROR RFC Receptor No valido");
            return 0;
        }
        
        
                   SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
               
                            String calendar =  String.valueOf(comp.getFecha().getYear());
                            String periodo = String.valueOf(comp.getFecha().getMonth());

                       //  revisarDirectorio("C:/carga/xml3/" + compania + "/" + calendar+ "/" + periodo);
                       //  String path = "C:/carga/xml3/" + compania + "/" + calendar + "/" + periodo + "/" + xml;



                           revisarDirectorio("D:/carga/xml/" + compania + "/" + calendar+ "/" + periodo);
                           path = "D:/carga/xml/" + compania + "/" + calendar + "/" + periodo + "/" + uuidCfdi + ".xml";
                           
                            String origen = archivoXML;
                            String destino = path;
                        

                            String result = copyFile_Java7(origen,destino);
            
                            if(!result.equalsIgnoreCase("success")){
                            
                                 return 0;
                            }
                            
        }
         
         String uuid ="";
        
         if(!result2 && result4 && !result1){
            
            uuid =app.getUUID4(path);
        
        }else{
            uuid =app.getUUID(path);
        }
        
        //Valida en el SAT
        
//        System.out.println("Validando en el SAT....");
//        if(!result2){
//        ValidaSAT vl = new ValidaSAT();
//        ResponseWSValida res = vl.validaCFDIWSSAT(valida.getComprobante().getEmisor().getRfc(), valida.getComprobante().getReceptor().getRfc(),""+ valida.getComprobante().getTotal(),uuid);
//        
//        
//        System.out.println("res.getEstatus():"+res.getEstatus());
//               
//               
//        if (res.getEstatus().equals("Vigente")) {
//            System.out.println("CFDI se Valido Bien en el SAT");
//        } else {
//           
//            System.out.println("ERROR Factuara no se encontro valida en el SAT.");
//            return 0;
//        }
        //}
   
        
        app.setNombrePdf(archivoPDF);
        app.setPathPdf(archivoPDF);
        app.setUsuario("WS");
        app.setTipoCarga("1");
        String concGasto = null;
        
        
        int data = 0;
          
            if(!result2 && !result4 && result1){
            
              data = app.cargaComprobante(path, compania, concGasto,"WS");
              
            }
            
            if(result2 && !result4 && !result1){
        
                data = app.cargaComprobante33(path, compania, concGasto,"","WS");

            
            }

            if(!result2 && result4 && !result1){

                 data = app.cargaComprobante4(path, compania, concGasto,"","WS");

            }
        
        
        return data;
      
    }
    
      public ResultValida cargaCFDIXML(String archivoXML,String compania,String nombre) throws FileNotFoundException, IOException, JAXBException{
    
        
        // File file = new File(archivoXML);
      //   File file2 = new File(archivoPDF);
         
         
        //FileInputStream file = new FileInputStream(archivoXML);
        
        
         ResultValida resultR = new ResultValida();
            
         String dirCompania;
         
          dirCompania = compania;
        FileInfo fi;
        FileInfo fi2;
        
         boolean result1 = validaVersionCFDI.verision32(archivoXML);
         boolean result2 = validaVersionCFDI.version33(archivoXML);
          boolean result4 = validaVersionCFDI.version4(archivoXML);
         
         System.out.println("result1: " +result1);
         System.out.println("result2: " +result2);
         System.out.println("result4: " +result4);
        
     
        
        

       
        String idErr = "" + System.currentTimeMillis();
        app.setId(idErr);
       PolizasInfo valida;
     
        
        if(!result2 && !result4 && result1){
            
                valida = app.validaComprobante(compania, archivoXML, "", "");
              
            }else{
            
               if(result2 && !result4 && !result1){
        
                valida = app.validaComprobante33(compania, archivoXML, "", "");

            
                }else{
                   
                   if(!result2 && result4 && !result1){

                        valida = app.validaComprobante4(compania, archivoXML, "", "");

                   }else{
                   
                       valida = app.validaComprobante33(compania, archivoXML, "", "");
                   
                   }
               
               
                }
            
            }
 
        if(valida.getInfTipo()!=0){
            System.out.println("Error al validar el xml 1");
            resultR.setResult(0);
            resultR.setMsg(valida.getMsgErr());
            return resultR;
            
        }
        
        
         
         
        if(!result2 && !result4 && result1){
             System.out.println("Soy version 32");
                 SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
                java.util.Date dateFecha = valida.getComprobante().getFecha();
                 String calendar = formatFecha(dateFecha, "yyyy");
                                 String periodo = formatFecha(dateFecha, "MM");

                       //  revisarDirectorio("D:/carga/xml3/" + compania + "/" + calendar+ "/" + periodo);
                       //  String path = "D:/carga/xml3/" + compania + "/" + calendar + "/" + periodo + "/" + xml;



                           revisarDirectorio("D:/carga/xml/" + compania + "/" + calendar+ "/" + periodo);
                           path = "D:/carga/xml/" + compania + "/" + calendar + "/" + periodo + "/" + nombre + ".xml";
                           
                            String origen = archivoXML;
                            String destino = path;
                        

                            String result = copyFile_Java7(origen,destino);
                            
                            if(!result.equalsIgnoreCase("success")){
                            
                                    resultR.setResult(0);
                                    resultR.setMsg("Error al procesar el archivo");
                                    return resultR;
                            }
                            
                            
                            
        //Obtener el uuid
         }
         
         
         
        if(result2 && !result4 && !result1){
                System.out.println("Soy version 33");
                 JAXBContext jaxbContext = JAXBContext.newInstance(com.feraz.mx.sat.cfdi.Comprobante.class);

                    Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
                    com.feraz.mx.sat.cfdi.Comprobante comp = (com.feraz.mx.sat.cfdi.Comprobante) jaxbUnmarshaller.unmarshal(new FileInputStream(archivoXML));

             
                    if(comp==null){
                       System.out.println("Error al validar el XML 2:");

                                               resultR.setResult(0);
                                               resultR.setMsg("Error al procesar el archivo");
                                               return resultR;
                   }
       // System.out.println("usuario:"+usuario);
        
       // System.out.println("prov.getRfc():"+valida.getComprobante().getEmisor().getRfc());
        //Valida que el RFC emisor sea de la secion
       
        
                   SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
               
                            String calendar =  String.valueOf(comp.getFecha().getYear());
                            String periodo = String.valueOf(comp.getFecha().getMonth());

                       //  revisarDirectorio("C:/carga/xml3/" + compania + "/" + calendar+ "/" + periodo);
                       //  String path = "C:/carga/xml3/" + compania + "/" + calendar + "/" + periodo + "/" + xml;



                           revisarDirectorio("D:/carga/xml/" + compania + "/" + calendar+ "/" + periodo);
                           path = "D:/carga/xml/" + compania + "/" + calendar + "/" + periodo + "/" + nombre + ".xml";
                           
                            String origen = archivoXML;
                            String destino = path;
                        

                            String result = copyFile_Java7(origen,destino);
            
                            if(!result.equalsIgnoreCase("success")){
                            
                                 resultR.setResult(0);
                                    resultR.setMsg("Error al procesar el archivo");
                                    return resultR;
                            }
                            
                            
        }
        
       if(!result2 && result4 && !result1){
                System.out.println("Soy version 4");

                    JAXBContext jaxbContext = JAXBContext.newInstance(Comprobante4.class);

		Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
		Comprobante4 comp = (Comprobante4) jaxbUnmarshaller.unmarshal(new FileInputStream(archivoXML));

                    if(comp==null){
                       System.out.println("Error al validar el XML 2:");

                                               resultR.setResult(0);
                                               resultR.setMsg("Error al procesar el archivo");
                                               return resultR;
                   }
       // System.out.println("usuario:"+usuario);
        
       // System.out.println("prov.getRfc():"+valida.getComprobante().getEmisor().getRfc());
        //Valida que el RFC emisor sea de la secion
       
        
                   SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
               
                            String calendar =  String.valueOf(comp.getFecha().getYear());
                            String periodo = String.valueOf(comp.getFecha().getMonth());

                       //  revisarDirectorio("C:/carga/xml3/" + compania + "/" + calendar+ "/" + periodo);
                       //  String path = "C:/carga/xml3/" + compania + "/" + calendar + "/" + periodo + "/" + xml;



                           revisarDirectorio("D:/carga/xml/" + compania + "/" + calendar+ "/" + periodo);
                           path = "D:/carga/xml/" + compania + "/" + calendar + "/" + periodo + "/" + nombre + ".xml";
                           
                            String origen = archivoXML;
                            String destino = path;
                        

                            String result = copyFile_Java7(origen,destino);
            
                            if(!result.equalsIgnoreCase("success")){
                            
                                 resultR.setResult(0);
                                    resultR.setMsg("Error al procesar el archivo");
                                    return resultR;
                            }
                            
                            
        }
          
         
        String uuid ="";
        
         if(!result2 && result4 && !result1){
            
            uuid =app.getUUID4(archivoXML);
        
        }else{
            uuid =app.getUUID(archivoXML);
        }
        
        //Valida en el SAT
        
//        System.out.println("Validando en el SAT....");
//        if(!result2){
//        ValidaSAT vl = new ValidaSAT();
//        ResponseWSValida res = vl.validaCFDIWSSAT(valida.getComprobante().getEmisor().getRfc(), valida.getComprobante().getReceptor().getRfc(),""+ valida.getComprobante().getTotal(),uuid);
//        
//        
//        System.out.println("res.getEstatus():"+res.getEstatus());
//               
//               
//        if (res.getEstatus().equals("Vigente")) {
//            System.out.println("CFDI se Valido Bien en el SAT");
//        } else {
//           
//            System.out.println("ERROR Factuara no se encontro valida en el SAT.");
//            resultR.setResult(0);
//                                    resultR.setMsg("ERROR Factuara no se encontro valida en el SAT.");
//                                    return resultR;
//        }
//        }
   
        
        app.setNombrePdf("");
        app.setPathPdf("");
        app.setUsuario("WSF");
        app.setTipoCarga("1");
        String concGasto = null;
       
        int data = 0;
          
            if(!result2 && !result4 && result1){
            
              data = app.cargaComprobante(path, compania, concGasto,"CP");
              
            }
            
            if(result2 && !result4 && !result1){
        
                data = app.cargaComprobante33(path, compania, concGasto,"","CP");

            
            }

            if(!result2 && result4 && !result1){

                 data = app.cargaComprobante4(path, compania, concGasto,"","CP");

            }
        
        if(data == 0){
            
            resultR.setResult(data);
            resultR.setMsg("Error al cargar el xml");
            return resultR;
        
        }else{
            resultR.setResult(data);
            resultR.setMsg("Archivo Cargado Correctamente");
            return resultR;
        
        }
        
       
      
    }
    
      
       public ResultValida cargaCFDIXMLDir(String archivoXML,String compania) throws FileNotFoundException, IOException, JAXBException{
    
        
        // File file = new File(archivoXML);
      //   File file2 = new File(archivoPDF);
         
         
        //FileInputStream file = new FileInputStream(archivoXML);
        
        
         ResultValida resultR = new ResultValida();
            
         String dirCompania;
         
          dirCompania = compania;
        FileInfo fi;
        FileInfo fi2;
        
         boolean result1 = validaVersionCFDI.verision32(archivoXML);
         boolean result2 = validaVersionCFDI.version33(archivoXML);
          boolean result4 = validaVersionCFDI.version4(archivoXML);
         
         System.out.println("result1: " +result1);
         System.out.println("result2: " +result2);
        
     
        
        

       
        String idErr = "" + System.currentTimeMillis();
        app.setId(idErr);
       PolizasInfo valida;
     
        
        if(!result2 && !result4 && result1){
            
                valida = app.validaComprobante(compania, archivoXML, "", "");
              
            }else{
            
               if(result2 && !result4 && !result1){
        
                valida = app.validaComprobante33(compania, archivoXML, "", "");

            
                }else{
                   
                   if(!result2 && result4 && !result1){

                        valida = app.validaComprobante4(compania, archivoXML, "", "");

                   }else{
                   
                       valida = app.validaComprobante33(compania, archivoXML, "", "");
                   
                   }
               
               
                }
            
            }
 
 
        if(valida.getInfTipo()!=0){
            System.out.println("Error al validar el xml 1");
            resultR.setResult(0);
            resultR.setMsg(valida.getMsgErr());
            return resultR;
            
        }
        
         
         
         
        if(!result2 && !result4 && result1){
             System.out.println("Soy version 32");
                 
                SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
                java.util.Date dateFecha = valida.getComprobante().getFecha();
                 String calendar = formatFecha(dateFecha, "yyyy");
                                 String periodo = formatFecha(dateFecha, "MM");
           
                            
                            
        //Obtener el uuid
         }
         
         
         
        if(result2 && !result4 && !result1){
                System.out.println("Soy version 33");
                              JAXBContext jaxbContext = JAXBContext.newInstance(com.feraz.mx.sat.cfdi.Comprobante.class);

                    Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
                    com.feraz.mx.sat.cfdi.Comprobante comp = (com.feraz.mx.sat.cfdi.Comprobante) jaxbUnmarshaller.unmarshal(new FileInputStream(archivoXML));

             
                    if(comp==null){
                       System.out.println("Error al validar el XML 2:");

                                               resultR.setResult(0);
                                               resultR.setMsg("Error al procesar el archivo");
                                               return resultR;
                   }
       // System.out.println("usuario:"+usuario);
        
       // System.out.println("prov.getRfc():"+valida.getComprobante().getEmisor().getRfc());
        //Valida que el RFC emisor sea de la secion
       
        
                   SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
               
                            String calendar =  String.valueOf(comp.getFecha().getYear());
                            String periodo = String.valueOf(comp.getFecha().getMonth());

                       //  revisarDirectorio("C:/carga/xml3/" + compania + "/" + calendar+ "/" + periodo);
                       //  String path = "C:/carga/xml3/" + compania + "/" + calendar + "/" + periodo + "/" + xml;


            
        }
        
       if(!result2 && result4 && !result1){
                System.out.println("Soy version 4");

                    JAXBContext jaxbContext = JAXBContext.newInstance(Comprobante4.class);

		Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
		Comprobante4 comp = (Comprobante4) jaxbUnmarshaller.unmarshal(new FileInputStream(archivoXML));
             
                    if(comp==null){
                       System.out.println("Error al validar el XML 2:");

                                               resultR.setResult(0);
                                               resultR.setMsg("Error al procesar el archivo");
                                               return resultR;
                   }
       // System.out.println("usuario:"+usuario);
        
       // System.out.println("prov.getRfc():"+valida.getComprobante().getEmisor().getRfc());
        //Valida que el RFC emisor sea de la secion
       
        
                   SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
               
                            String calendar =  String.valueOf(comp.getFecha().getYear());
                            String periodo = String.valueOf(comp.getFecha().getMonth());

                       //  revisarDirectorio("C:/carga/xml3/" + compania + "/" + calendar+ "/" + periodo);
                       //  String path = "C:/carga/xml3/" + compania + "/" + calendar + "/" + periodo + "/" + xml;


                            
        }
          
         
        String uuid ="";
        
         if(!result2 && result4 && !result1){
            
            uuid =app.getUUID4(archivoXML);
        
        }else{
            uuid =app.getUUID(archivoXML);
        }
        
        //Valida en el SAT
        
        //System.out.println("Validando en el SAT....");
        //if(!result2){
        //ValidaSAT vl = new ValidaSAT();
        //ResponseWSValida res = vl.validaCFDIWSSAT(valida.getComprobante().getEmisor().getRfc(), valida.getComprobante().getReceptor().getRfc(),""+ valida.getComprobante().getTotal(),uuid);
        
        
//        System.out.println("res.getEstatus():"+res.getEstatus());
//               
//               
//        if (res.getEstatus().equals("Vigente")) {
//            System.out.println("CFDI se Valido Bien en el SAT");
//        } else {
//           
//            System.out.println("ERROR Factuara no se encontro valida en el SAT.");
//            resultR.setResult(0);
//                                    resultR.setMsg("ERROR Factuara no se encontro valida en el SAT.");
//                                    return resultR;
//        }
//        }
//   
        
        app.setNombrePdf("");
        app.setPathPdf("");
        app.setUsuario("WSF");
        app.setTipoCarga("1");
        String concGasto = null;
         
         int data = 0;
          
            if(!result2 && !result4 && result1){
            
              data = app.cargaComprobante(archivoXML, compania, concGasto,"CP");
              
            }
            
            if(result2 && !result4 && !result1){
        
                data = app.cargaComprobante33(archivoXML, compania, concGasto,"","CP");

            
            }

            if(!result2 && result4 && !result1){

                 data = app.cargaComprobante4(archivoXML, compania, concGasto,"","CP");

            }
       
        
        if(data == 0){
            
            resultR.setResult(data);
            resultR.setMsg("Error al cargar el xml");
            return resultR;
        
        }else{
            resultR.setResult(data);
            resultR.setMsg("Archivo Cargado Correctamente");
            return resultR;
        
        }
        
       
      
    }  
      
    public String formatFecha(java.util.Date date, String format) {
        try {
            return new SimpleDateFormat(format).format(date);
        } catch (Exception e) {
            return "";
        }
    }
    public static String copyFile_Java7(String origen, String destino) throws IOException {
        
        try {
                Path FROM = Paths.get(origen);
                Path TO = Paths.get(destino);
                //sobreescribir el fichero de destino, si existe, y copiar
                // los atributos, incluyendo los permisos rwx
                CopyOption[] options = new CopyOption[]{
                  StandardCopyOption.REPLACE_EXISTING,
                  StandardCopyOption.COPY_ATTRIBUTES
                }; 
                Files.copy(FROM, TO, options);
                
                return "success";
        
        }catch (IOException e)
            {
                
                System.out.println("##NO LOGRO CONSULTAR## \n"+e);   
                 return "failure";
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
     
      public boolean revisarDirectorioArchivo(String dirCompania) {

        File file = new File(dirCompania);
      //  System.out.println("Directory" + dirCompania + "/");
            if (!file.exists()) {
                   return false;
            }else{
                return true;
            }
        }
  
    

    public void setErpWsIntercosDao(ErpWsIntercosDao erpWsIntercosDao) {
        this.erpWsIntercosDao = erpWsIntercosDao;
    }

    public void setValidaVersionCFDI(ValidaVersionCFDI validaVersionCFDI) {
        this.validaVersionCFDI = validaVersionCFDI;
    }

    public void setApp(App app) {
        this.app = app;
    }

    public void setProcessDao(ProcessDao processDao) {
        this.processDao = processDao;
    }

    public void setErpFeComprobantesDao(ErpFeComprobantesDao erpFeComprobantesDao) {
        this.erpFeComprobantesDao = erpFeComprobantesDao;
    }
    
    
    
    
    
    
    
}
