/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.feraz.facturas.webcontrolfe.procesa;

import com.feraz.facturas.webcontrolfe.App.App;
import com.feraz.facturas.webcontrolfe.dto.DirectoryDto;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 *
 * @author Ing. JAMH
 */
public class CargaFacturas {
    
   
    private String directorio; 
     private App app;

    public CargaFacturas() {
    }
     
    
    
     public List<DirectoryDto> procesa(List<DirectoryDto> lista,String compania, String idErr){
        
        
        return this.procesa(lista, compania, idErr,"P");
    }
    
     
    public List<DirectoryDto> procesa(List<DirectoryDto> lista,String compania, String idErr,String origen){
        
        System.out.println("idErr, Dentro de carga de facturas"+idErr);
        app.setId(idErr);
        Iterator<DirectoryDto> it=lista.iterator();
        List<DirectoryDto> listaReturn =new ArrayList();
//        System.out.println("directorio:"+directorio);
        while(it.hasNext()){
            DirectoryDto dto = it.next();
            int data = app.cargaComprobante(directorio+dto.getXml(), compania, null,origen);
//            if(data==0){
//                dto.setMsgErr("Error al cargar el archivo.");
//            }
            listaReturn.add(dto);
        }
        return listaReturn;
    }
    
       public int procesaXmlDir(String direccion,String compania, String idErr){
        
       // System.out.println("idErr, Dentro de carga de facturas"+idErr);
        app.setId(idErr);
      
            int data = app.cargaComprobante(direccion+".xml", compania, null,"P");

        return data;
    }

    public void setDirectorio(String directorio) {
        this.directorio = directorio;
    }

    public void setApp(App app) {
        this.app = app;
    }
    
    
}
