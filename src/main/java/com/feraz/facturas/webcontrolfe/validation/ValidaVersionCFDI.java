/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.feraz.facturas.webcontrolfe.validation;



import com.feraz.mx.sat.cfdi.Comprobante;
import com.feraz.mx.sat.cfdi4.Comprobante4;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import mx.bigdata.sat.cfdi.CFDv32;
import org.springframework.web.multipart.commons.CommonsMultipartFile;



/**
 *
 * @author admwrk-
 */
public class ValidaVersionCFDI {
    
    
      public boolean version4 (String archivo){
        
        try {

		//File file = new File("H:\\logs\\test.xml");
                File file = new File(archivo);
		JAXBContext jaxbContext = JAXBContext.newInstance(Comprobante4.class);

		Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
		Comprobante4 comprobante = (Comprobante4) jaxbUnmarshaller.unmarshal(file);
		System.out.println(comprobante);
                System.out.println("comp:"+comprobante.getTotal());
                System.out.println("comp:"+comprobante.getReceptor().getRfc());
                
            return comprobante.getEmisor().getRfc() != null;

	  } catch (JAXBException e) {
		//e.printStackTrace();
                return false;
	  }catch (Exception e) {
		//e.printStackTrace();
                return false;
	  }

        
        
    
        
    }
    
    public boolean version33 (String archivo){
        
        try {

		//File file = new File("H:\\logs\\test.xml");
                File file = new File(archivo);
		JAXBContext jaxbContext = JAXBContext.newInstance(Comprobante.class);

		Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
		Comprobante comprobante = (Comprobante) jaxbUnmarshaller.unmarshal(file);
		System.out.println(comprobante);
                System.out.println("comp:"+comprobante.getTotal());
                System.out.println("comp:"+comprobante.getReceptor().getRfc());
                
            return comprobante.getEmisor().getRfc() != null;

	  } catch (JAXBException e) {
		//e.printStackTrace();
                return false;
	  }catch (Exception e) {
		//e.printStackTrace();
                return false;
	  }

        
        
    
        
    }
    
    public boolean verision32 (String archivo){
    
        //   File fichero = new File(archivo);
        //if (fichero.exists()) {
        //    System.out.println("El fichero " + archivo + " existe");
        //} else {
        //  System.out.println("No existe");
        //}
        try {
            FileInputStream file = new FileInputStream(archivo);
            
            //System.out.println("file"+file);

            mx.bigdata.sat.cfdi.v32.schema.Comprobante comp = CFDv32.newComprobante(file);
            
            
            
            return comp.getEmisor().getRfc() != null;
            
        } catch (Exception e) {
          // e.printStackTrace();
            return false;
        }
    
    }
    
     public boolean version4File (CommonsMultipartFile file) throws IOException{
        
        try {

		//File file = new File("H:\\logs\\test.xml");
               // File file = new File(archivo);
		JAXBContext jaxbContext = JAXBContext.newInstance(Comprobante4.class);

		Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
		Comprobante4 comprobante = (Comprobante4) jaxbUnmarshaller.unmarshal(file.getInputStream());
		System.out.println(comprobante);
                System.out.println("comp:"+comprobante.getTotal());
                System.out.println("comp:"+comprobante.getReceptor().getRfc());
                
            return comprobante.getEmisor().getRfc() != null;

	  } catch (JAXBException e) {
		e.printStackTrace();
                return false;
	  }

        
        
    
        
    }
     
    public boolean version33File (CommonsMultipartFile file) throws IOException{
        
        try {

		//File file = new File("H:\\logs\\test.xml");
               // File file = new File(archivo);
		JAXBContext jaxbContext = JAXBContext.newInstance(Comprobante.class);

		Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
		Comprobante comprobante = (Comprobante) jaxbUnmarshaller.unmarshal(file.getInputStream());
		System.out.println(comprobante);
                System.out.println("comp:"+comprobante.getTotal());
                System.out.println("comp:"+comprobante.getReceptor().getRfc());
                
            return comprobante.getEmisor().getRfc() != null;

	  } catch (JAXBException e) {
		e.printStackTrace();
                return false;
	  }

        
        
    
        
    }
    
    public boolean verision32File (CommonsMultipartFile file){
    
//           File fichero = new File(archivo);
//        if (fichero.exists()) {
//            System.out.println("El fichero " + archivo + " existe");
//        } else {
//          System.out.println("No existe");
//        }
        try {
          //  FileInputStream file = new FileInputStream(file.getInputStream());
            
            //System.out.println("file"+file);

            mx.bigdata.sat.cfdi.v32.schema.Comprobante comp = CFDv32.newComprobante(file.getInputStream());
            
            
            
            return comp.getEmisor().getRfc() != null;
            
        } catch (Exception e) {
           e.printStackTrace();
            return false;
        }
    
    }
    
    
}
