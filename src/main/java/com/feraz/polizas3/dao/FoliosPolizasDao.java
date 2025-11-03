/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.feraz.polizas3.dao;

/**
 *
 * @author Feraz3
 */
import com.feraz.polizas3.model.FoliosPolizas;
import com.feraz.polizas3.model.FoliosPolizasId;
import java.util.List;
public interface FoliosPolizasDao {
    
     public FoliosPolizasId save(FoliosPolizas foliosPolizas);
     
     public boolean buscaActualiza(String compania, int ano, int mes, String tipoPoliza);
       
      
    
    
}
