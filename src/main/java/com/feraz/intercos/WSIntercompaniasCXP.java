/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.feraz.intercos;

import com.feraz.intercos.dao.ErpWsIntercosDao;
import com.feraz.intercos.model.ErpWsIntercos;
import com.feraz.intercos.model.ErpWsIntercosId;
import com.feraz.intercos.util.DTOCarga;
import com.feraz.intercos.util.ProcesosIntercos;
import com.feraz.intercos.util.ResultValida;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import javax.jws.WebService;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.xml.bind.JAXBException;

/**
 *
 * @author vavi
 */
@WebService(serviceName = "WSIntercompaniasCXP")
public class WSIntercompaniasCXP {
    
    
    private ProcesosIntercos procesosIntercos;

    /**
     * This is a sample web service operation
     */
    @WebMethod(operationName = "cargaCFDI")
    public String hello(
            @WebParam(name = "usuario") String usuario,
            @WebParam(name = "password") String pass,
            @WebParam(name = "compania") String compania,
            @WebParam(name = "uuid") String uuid,
            @WebParam(name = "urlXml") String urlXml,
            @WebParam(name = "urlPDF") String urlPDF,
            @WebParam(name = "rfcReceptor") String rfcRecptor,
            @WebParam(name = "rfcEmisor") String rfcEmisor,
            @WebParam(name = "operacion") String operacion
    
    ) throws IOException, FileNotFoundException, JAXBException {
        
        
                ErpWsIntercos wsInt = new ErpWsIntercos();
                ErpWsIntercosId wsIntId = new ErpWsIntercosId();
        
        System.out.println(operacion);
        System.out.println("-----------WS INTERCOS----------------");
        System.out.println(usuario);
        System.out.println(pass);
        System.out.println(compania);
        System.out.println(uuid);
        System.out.println(urlXml);
        System.out.println(urlPDF);
        System.out.println(rfcRecptor);
        System.out.println(rfcEmisor);
        System.out.println(operacion);
        
        
        System.out.println("-----------WS INTERCOS----------------");
        
        
         System.out.println(uuid.replace(" ",""));
         uuid = uuid.replace(" ","");
         uuid = uuid.replaceAll("\n", ""); 
         
         System.out.println(uuid);
        
        
         String urlC = "D:\\cfdiIntercos\\"+uuid+".xml";
         
         procesosIntercos.revisarDirectorio("D:\\cfdiIntercos\\");
        
        if(!usuario.equalsIgnoreCase("cfdiInt")){
            
            return msgResp("0","Error usuario incorrecto");
        
        }
        
        if(!pass.equalsIgnoreCase("truper")){
            
            return msgResp("0","Error password incorrecto");
        
        }
        
        if(operacion.equalsIgnoreCase("G")){
               
                
                
                String resultCOMPANIA = procesosIntercos.verificaCompania(rfcRecptor);
                
                
                if(resultCOMPANIA.equalsIgnoreCase("N/A")){
                
                    return msgResp("0","Error, compania no encontrada");
                }
                
                

                wsIntId.setCompania(resultCOMPANIA);
                wsIntId.setUuid(uuid);

                wsInt.setId(wsIntId);
                wsInt.setOperacion(operacion);
                wsInt.setRfcEmisor(rfcEmisor);
                wsInt.setRfcReceptor(rfcRecptor);
                wsInt.setUrlPdf(urlPDF);
                wsInt.setUrlXml(urlXml);
                wsInt.setCompaniaCFDI(compania);

                
                System.out.println("COMPANIA: "+resultCOMPANIA);
                
                 boolean resultUUID = procesosIntercos.validaUUID(resultCOMPANIA, uuid);
                
                if (resultUUID == false){
                    
                    System.out.println("-------Generando el archivos---------");
                    System.out.println("urlXML : " + urlXml);
                    System.out.println("uuid : " + uuid);
                    
                    System.out.println("urlC : " + urlC);
                    
                      int xmlCont = procesosIntercos.buscaXml(urlXml,uuid,urlC);
                      
                     System.out.println("-------Generando el archivos---------");
        
                      System.out.println(xmlCont);
        
       


                     if (xmlCont == 1){


                        int result = procesosIntercos.validaCFDICarga(urlC);

                        System.out.println(result);

                        if(result == 0){

                            return msgResp("0","Error al verificar la version del CFDI");
                        }


                        int numResult = procesosIntercos.cargaCFDIIntercos(urlC, urlPDF, resultCOMPANIA, rfcEmisor, rfcRecptor,uuid);

                        if(numResult == 0){

                             return msgResp("0","Error al guardar el cfdi");
                        }


                        wsInt.setNumeroFe(numResult);

                        procesosIntercos.insertDatos(wsInt);

                        System.out.println(numResult);
                        
                        File ficheroOrigen = new File(urlC);
                        
                            
                            if (ficheroOrigen.delete()){
                                System.out.println("El fichero ha sido borrado satisfactoriamente");
                            }else{
                                System.out.println("El fichero no puede ser borrado");
                            }

                        return msgResp("1","CFDI cargado correctamente con numero: "+numResult);
                    }else{
                         
                         if(xmlCont == 2){
                         
                             return msgResp("0","El archivo ya existe.");
                         }else{
                            
                            return msgResp("0","Error al crear el archivo");
                         }
                        
                        }
                }else{

                   return msgResp("0","El comprobante ya fue cargado");

                }
        }else{
        
           
            
             return msgResp("0","El campo de operacion es incorrecto");
            
            
        
        
        
        }
    }
    
      @WebMethod(operationName = "cancelaCFDI")
    public String cancela(
            @WebParam(name = "usuario") String usuario,
            @WebParam(name = "password") String pass,
            @WebParam(name = "compania") String compania,
            @WebParam(name = "uuid") String uuid,
            @WebParam(name = "rfcReceptor") String rfcRecptor,
            @WebParam(name = "rfcEmisor") String rfcEmisor,
            @WebParam(name = "operacion") String operacion
    
    ) throws IOException, FileNotFoundException, JAXBException {
        
        
                ErpWsIntercos wsInt = new ErpWsIntercos();
                ErpWsIntercosId wsIntId = new ErpWsIntercosId();
        
        System.out.println(operacion);
        System.out.println("-----------WS INTERCOS CANC----------------");
        System.out.println(usuario);
        System.out.println(pass);
        System.out.println(compania);
        System.out.println(uuid);
        System.out.println(rfcRecptor);
        System.out.println(rfcEmisor);
        System.out.println(operacion);
        
        
        System.out.println("-----------WS INTERCOS CANC----------------");
        
        
         System.out.println(uuid.replace(" ",""));
         uuid = uuid.replace(" ","");
         uuid = uuid.replaceAll("\n", ""); 
         
        
        if(!usuario.equalsIgnoreCase("cfdiInt")){
            
            return msgResp("0","Error usuario incorrecto");
        
        }
        
        if(!pass.equalsIgnoreCase("truper")){
            
            return msgResp("0","Error password incorrecto");
        
        }
        

        
            if(operacion.equalsIgnoreCase("C")){
            
                String resultCOMPANIA = procesosIntercos.verificaCompania(rfcRecptor);
                
                
                if(resultCOMPANIA.equalsIgnoreCase("N/A")){
                
                    return msgResp("0","Error, compania no encontrada");
                }
                
                int resultNum = procesosIntercos.verificaDatosCancelar(resultCOMPANIA, uuid);
                
                if(resultNum == 0){
                    
                    return msgResp("0","Error. La factura ya fue procesada en tesoreria, no se puede cancelar. Favor de verificar con el departamento correspondiente.");
                
                }
                
                if(resultNum == -1){
                    
                    return msgResp("0","Error. La factura no existe.");
                
                }
           
                boolean resultUpdate = procesosIntercos.actualizaEstatus(resultCOMPANIA, resultNum);
                
                if(resultUpdate == true){
                
                  return msgResp("1","Factura con id: "+resultNum+ " correctamente cancelada");
                }else{
                
                    return msgResp("0","Error. Error al cancelar la factura.");
                
                }
                
                 
             
            }else{
            
             return msgResp("0","El campo de operacion es incorrecto");
            
            }
        
        
        
        
    }
    
        @WebMethod(operationName = "cargaCFDI2")
    public String cargaXml(
            
            @WebParam(name = "compania") String compania,        
            @WebParam(name = "xmlText") String xml
          
    
    ) throws IOException, FileNotFoundException, JAXBException {
        
        
                ErpWsIntercos wsInt = new ErpWsIntercos();
                ErpWsIntercosId wsIntId = new ErpWsIntercosId();
        
        System.out.println("-----------WS CARGA----------------");

        System.out.println(compania);
        System.out.println(xml);
        
        
        System.out.println("-----------WS CARGA----------------");
        
        String uuid = System.currentTimeMillis()+"";
        String nombre = System.currentTimeMillis()+"";
       
        
        
         String urlC = "D:\\cfdiIntercos\\"+uuid+".xml";
         
         procesosIntercos.revisarDirectorio("D:\\cfdiIntercos\\");
        
//        if(!usuario.equalsIgnoreCase("cfdiInt")){
//            
//            return msgResp("0","Error usuario incorrecto");
//        
//        }
//        
//        if(!pass.equalsIgnoreCase("truper")){
//            
//            return msgResp("0","Error password incorrecto");
//        
//        }
//        
              
                
                
              //  String resultCOMPANIA = procesosIntercos.verificaCompania(rfcRecptor);
                
                
              //  if(resultCOMPANIA.equalsIgnoreCase("N/A")){
                
              //      return msgResp("0","Error, compania no encontrada");
              //  }
                
                

                wsIntId.setCompania(compania);
                wsIntId.setUuid(uuid);

                wsInt.setId(wsIntId);
              
                wsInt.setUrlXml(xml);

                
           
                    
                    System.out.println("-------Generando el archivos busca xml---------");
                  //  System.out.println("urlXML : " + xml);
                  //  System.out.println("uuid : " + uuid);
                    
                  //  System.out.println("urlC : " + urlC);
                    
                      int xmlCont = procesosIntercos.buscaXml2(xml,urlC);
                      
                     System.out.println("-------Generando el archivos---------");
        
                      System.out.println(xmlCont);
        
       


                     if (xmlCont == 1){


                        int result = procesosIntercos.validaCFDICarga(urlC);

                        System.out.println(result);

                        if(result == 0){

                            return msgResp("0","Error al verificar la version del CFDI");
                        }


                        ResultValida numResult = procesosIntercos.cargaCFDIXML(urlC,compania,nombre);

                        if(numResult.getResult() == 0){

                             return msgResp("0",numResult.getMsg());
                        }


                        wsInt.setNumeroFe(numResult.getResult());

                        //procesosIntercos.insertDatos(wsInt);

                        System.out.println(numResult);
                        
                        File ficheroOrigen = new File(urlC);
                        
                            
                            if (ficheroOrigen.delete()){
                                System.out.println("El fichero ha sido borrado satisfactoriamente");
                            }else{
                                System.out.println("El fichero no puede ser borrado");
                            }

                        return msgResp("1","CFDI cargado correctamente con numero: "+numResult);
                    }else{
                         
                         if(xmlCont == 2){
                         
                             return msgResp("0","El archivo ya existe.");
                         }else{
                            
                            return msgResp("0","Error al crear el archivo");
                         }
                        
                        }
       
      
    }
    
       @WebMethod(operationName = "cargaCFDI3")
    public String cargaXml3(
            
            @WebParam(name = "compania") String compania,        
            @WebParam(name = "xmlText") String xml
          
    
    ) throws IOException, FileNotFoundException, JAXBException {
        
        
                ErpWsIntercos wsInt = new ErpWsIntercos();
                ErpWsIntercosId wsIntId = new ErpWsIntercosId();
        
        System.out.println("-----------WS CARGA----------------");

        System.out.println(compania);
        System.out.println(xml);
        
        
        System.out.println("-----------WS CARGA----------------");
        
        String uuid = System.currentTimeMillis()+"";
        String nombre = System.currentTimeMillis()+"";
       
        
        
         String urlC = "D:\\carga\\xml\\"+compania+"\\"+uuid+".xml";
         
         procesosIntercos.revisarDirectorio("D:\\carga\\xml\\"+compania+"\\");
        


                wsIntId.setCompania(compania);
                wsIntId.setUuid(uuid);

                wsInt.setId(wsIntId);
              
                wsInt.setUrlXml(xml);

                
           
                    
                    System.out.println("-------Generando el archivos busca xml---------");
                  //  System.out.println("urlXML : " + xml);
                  //  System.out.println("uuid : " + uuid);
                    
                  //  System.out.println("urlC : " + urlC);
                    
                         int xmlCont = procesosIntercos.buscaXml(xml,uuid,urlC);
                      
                      
                     System.out.println("-------Generando el archivos---------");
        
                      System.out.println(xmlCont);
        
       


                     if (xmlCont == 1){


                        int result = procesosIntercos.validaCFDICarga(urlC);

                        System.out.println(result);

                        if(result == 0){

                            return msgResp("0","Error al verificar la version del CFDI");
                        }


                        ResultValida numResult = procesosIntercos.cargaCFDIXML(urlC,compania,nombre);

                        if(numResult.getResult() == 0){

                             return msgResp("0",numResult.getMsg());
                        }


                        wsInt.setNumeroFe(numResult.getResult());

                        //procesosIntercos.insertDatos(wsInt);

                        System.out.println(numResult);
                      

                        return msgResp("1","CFDI cargado correctamente con numero: "+numResult.getResult());
                    }else{
                         
                     
                            
                            return msgResp("0","Error al crear el archivo");
                         
                        
                        }
       
      
    }
    
    
    @WebMethod(operationName = "cargaCFDIRuta")
    public DTOCarga cargaXmlRuta(
            
            @WebParam(name = "compania") String compania,        
            @WebParam(name = "dir") String dir
          
    
    ) throws IOException, FileNotFoundException, JAXBException {
        
        
                
        DTOCarga dto = new DTOCarga();
        
        System.out.println("-----------WS CARGA DIR----------------");

        System.out.println(compania);
        System.out.println(dir);
        
        
        System.out.println("-----------WS CARGA DIR----------------");
        
       
        
        
         String urlC = dir;
         
         boolean resultDir = procesosIntercos.revisarDirectorioArchivo(dir);
         
         
        
                if (resultDir == false){
                    
                    dto.setMsg("Error, el archivo no existe.");
                    dto.setResult(0);
                    
                    return dto;
                
                }

                
           
                    
                    System.out.println("-------Generando el archivos busca xml---------");
              
                        int result = procesosIntercos.validaCFDICarga(urlC);

                        System.out.println(result);

                        if(result == 0){
                            
                            dto.setMsg("Error al verificar la version del CFDI");
                            dto.setResult(0);

                            return dto;

                        }


                        ResultValida numResult = procesosIntercos.cargaCFDIXMLDir(urlC,compania);

                        if(numResult.getResult() == 0){
                            
                            dto.setMsg(numResult.getMsg());
                            dto.setResult(0);

                            return dto;

                        }



                        //procesosIntercos.insertDatos(wsInt);

                        System.out.println(numResult);
                      
                        dto.setMsg("CFDI cargado correctamente con numero: "+numResult.getResult());
                        dto.setResult(1);

                        return dto;

                
       
      
    }
    
    @WebMethod(exclude = true)
    public String msgResp(String result, String msg) {
       
        return "<?xml version=\"1.0\" encoding=\"UTF-8\"?>"
                + "<response>\n"
                + "  <result>" + result + "</result>\n"
                + "  <mensaje>" + msg + "</mensaje>\n"
                + "</response>\n";
    }

//    public void setErpWsIntercosDao(ErpWsIntercosDao erpWsIntercosDao) {
//        this.erpWsIntercosDao = erpWsIntercosDao;
//    }
//    

    public void setProcesosIntercos(ProcesosIntercos procesosIntercos) {
        this.procesosIntercos = procesosIntercos;
    }
    
    
    
    
}
