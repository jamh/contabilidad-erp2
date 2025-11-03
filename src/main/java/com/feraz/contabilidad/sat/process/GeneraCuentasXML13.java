/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.feraz.contabilidad.sat.process;

import com.feraz.contabilidad.sat.electronica.model.ErpSatCatalogo;
import com.feraz.contabilidad.sat.electronica.model.ErpSatCatalogoCtas;
import mx.gob.sat.esquemas.contabilidadE.x13.catalogoCuentas.CatalogoDocument.Factory;
import mx.gob.sat.esquemas.contabilidadE.x13.catalogoCuentas.CatalogoDocument;
import com.feraz.contabilidad.sat.exception.CuentasException;
import com.google.common.base.Charsets;
import com.google.common.io.Files;
import java.io.File;
import java.io.FileOutputStream;
import java.util.Iterator;
import java.util.List;
import javax.xml.XMLConstants;
import javax.xml.namespace.QName;

import mx.bigdata.sat.ce_catalogo_cuentas.schema.ObjectFactory;
import mx.bigdata.sat.contabilidad_electronica.CuentasContablesv11;
import mx.gob.sat.esquemas.contabilidadE.x13.catalogoCuentas.CatalogoDocument.Catalogo.Mes;
import mx.gob.sat.esquemas.contabilidadE.x13.catalogoCuentas.CatalogoDocument.Catalogo.Ctas;
import org.springframework.beans.factory.annotation.Value;
import mx.gob.sat.esquemas.contabilidadE.x13.catalogosParaEsqContE.CCodAgrup;
import org.apache.xmlbeans.XmlCursor;
import org.apache.xmlbeans.XmlOptions;

/**
 *
 * @author Ing. JAMH
 */
public class GeneraCuentasXML13 {
      @Value("${document.file.dirXMLSAT}")
  private String dirOut;
       
     public String procesa2( ErpSatCatalogo erpSatCatalogo,List<ErpSatCatalogoCtas> lista,String name ){
      //  ObjectFactory of = new ObjectFactory();
      //  Catalogo catalogo = of.createCatalogo();
      
         CatalogoDocument catalogoDocument = CatalogoDocument.Factory.newInstance();
         
         CatalogoDocument.Catalogo catalogo = catalogoDocument.addNewCatalogo();
         
         XmlCursor cursorCatalogo = catalogo.newCursor();
         
         
             cursorCatalogo.setAttributeText(
				new QName(XMLConstants.W3C_XML_SCHEMA_INSTANCE_NS_URI,
						"schemaLocation"),
				"http://www.sat.gob.mx/esquemas/ContabilidadE/1_3/CatalogoCuentas http://www.sat.gob.mx/esquemas/ContabilidadE/1_3/CatalogoCuentas/CatalogoCuentas_1_3.xsd");
     //         javax.xml.namespace.QName qname = cursorBalanza.getName();
         
         
        catalogo.setAnio(new Integer(erpSatCatalogo.getAnio()));
        
         int mes = Integer.parseInt(erpSatCatalogo.getMes());
      
      switch (mes){
      
          case 1:
              
              catalogo.setMes(Mes.X_01);
          
          break;
              
          case 2:
              
              catalogo.setMes(Mes.X_02);
          
          break;
          
          case 3:
              
              catalogo.setMes(Mes.X_03);
          
          break;
              
          case 4:
              
              catalogo.setMes(Mes.X_04);
          
          break;
          
          case 5:
              
              catalogo.setMes(Mes.X_05);
          
          break;
          
          case 6:
              
              catalogo.setMes(Mes.X_06);
          
          break;
         
          case 7:
              
              catalogo.setMes(Mes.X_07);
          
          break;
          
          case 8:
              
              catalogo.setMes(Mes.X_08);
          
          break;
              
          case 9:
              
              catalogo.setMes(Mes.X_09);
          
          break;
              
          case 10:
              
              catalogo.setMes(Mes.X_10);
          
          break;
              
              
          case 11:
              
              catalogo.setMes(Mes.X_11);
          
          break;
              
          case 12:
              
              catalogo.setMes(Mes.X_12);
          
          break;
              
          case 13:
              
              catalogo.setMes(Mes.X_12);
          
          break;
      }
        
        catalogo.setRFC(erpSatCatalogo.getRfc());
        catalogo.setVersion("1.3");
       String val =null; 
        
        
             try{

       Iterator<ErpSatCatalogoCtas> it =lista.iterator();
       
       int i = 0;
       
       while(it.hasNext()){
           ErpSatCatalogoCtas cta = it.next();
           Ctas ct= catalogo.addNewCtas();
         
          //ct.setCodAgrup(CCodAgrup.X_401_08);
           ct.setCodAgrup(CCodAgrup.Enum.forString(cta.getCodAgrup()));
          ct.setDesc(cta.getDescrip());
          ct.setNatur(cta.getNatur());
          ct.setNivel(cta.getNivel());
           ct.setNumCta(cta.getNumCta());
           ct.setSubCtaDe(cta.getSubCtaDe());
           
           catalogo.setCtasArray(i, ct);
           
           i = i + 1;
       }
      
          String dir =dirOut+name+".xml";
       
        
        XmlOptions xmlOptions = new XmlOptions();
        xmlOptions.setCharacterEncoding("UTF-8");
        xmlOptions.setSavePrettyPrint();
        xmlOptions.setSavePrettyPrintIndent(4);
       String xmlStr = catalogoDocument.xmlText(xmlOptions);
       
       System.out.println(xmlStr);
       
     
             FileOutputStream out = new FileOutputStream(dir);
             catalogoDocument.save(out);
              out.close();
       
     
          
       
            val = Files.toString(new File(dir), Charsets.UTF_8);
       //catalogo = catalogoCuentas.
       }
        catch(Exception e){
         //  System.out.println("val:"+e.getMessage());
          // e.printStackTrace();
           return e.getMessage();
       }
       
        
        
        return val;
     }

    public void setDirOut(String dirOut) {
        this.dirOut = dirOut;
    }
     
     
     
}
