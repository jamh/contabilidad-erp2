/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.feraz.facturas.webcontrolfe.process;

import java.io.File;

/**
 *
 * @author Feraz3
 */
public class DeleteArchivo {
    
    
    public boolean borrarArchivo(String dir,String compania,String archivo){
        
        
              File file = new File(dir + compania + "/"+archivo);
                System.out.println("Directory" + dir + compania + "/"+archivo);
                if (file.delete()){
                System.out.println("El fichero ha sido borrado satisfactoriamente");
                }else{
                System.out.println("El fichero no puede ser borrado");
                  return false;
                }
                
        
        
        return true;
    
    
    } 
    
}
