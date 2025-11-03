/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.feraz.contabilidad.sat.process;

import com.feraz.contabilidad.sat.electronica.model.ErpSatCatalogo;
import com.feraz.contabilidad.sat.electronica.model.ErpSatCatalogoCtas;
import com.feraz.contabilidad.sat.exception.CuentasException;
import com.google.common.base.Charsets;
import com.google.common.io.Files;
import java.io.File;
import java.io.FileOutputStream;
import java.util.Iterator;
import java.util.List;
import mx.bigdata.sat.ce_catalogo_cuentas.schema.Catalogo;
import mx.bigdata.sat.ce_catalogo_cuentas.schema.ObjectFactory;
import mx.bigdata.sat.contabilidad_electronica.CuentasContablesv11;
import org.springframework.beans.factory.annotation.Value;

/**
 *
 * @author Ing. JAMH
 */
public class GeneraCuentasXML11 {
      @Value("${document.file.dirXMLSAT}")
  private String dirOut;
       
     public String procesa2( ErpSatCatalogo erpSatCatalogo,List<ErpSatCatalogoCtas> lista,String name ){
        ObjectFactory of = new ObjectFactory();
        Catalogo catalogo = of.createCatalogo();
      
        catalogo.setAnio(new Integer(erpSatCatalogo.getAnio()));
        catalogo.setMes(erpSatCatalogo.getMes());
        catalogo.setRFC(erpSatCatalogo.getRfc());
        catalogo.setVersion(erpSatCatalogo.getVersiones());
       String val =null; 
        
        
             try{

       Iterator<ErpSatCatalogoCtas> it =lista.iterator();
       
       while(it.hasNext()){
           ErpSatCatalogoCtas cta = it.next();
           Catalogo.Ctas ct= new Catalogo.Ctas();
         
          ct.setCodAgrup(cta.getCodAgrup());
          ct.setDesc(cta.getDescrip());
          ct.setNatur(cta.getNatur());
          ct.setNivel(cta.getNivel());
           ct.setNumCta(cta.getNumCta());
           ct.setSubCtaDe(cta.getSubCtaDe());
           catalogo.getCtas().add(ct);
       }
      
            CuentasContablesv11 catalogoCuentas = new CuentasContablesv11(catalogo, "mx.bigdata.sat.ce_catalogo_cuentas.schema");
    
           // catalogoCuentas.validar(); 
          //  CuentasException err = new CuentasException();
            //catalogoCuentas.validar(err);
            
            String dir =dirOut+name+".xml";
            FileOutputStream out = new FileOutputStream(dir);
            catalogoCuentas.guardar(out);
            out.close();
              
            
           
          
        //  String text = Files.toString(new File(path), Charsets.UTF_8);
            val = Files.toString(new File(dir), Charsets.UTF_8);
             System.out.println("val:"+val);
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
