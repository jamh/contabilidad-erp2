/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.feraz.polizas3.reports;

import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.util.JRLoader;

/**
 *
 * @author Ing. JAMH
 */
public class GenReport {
    private String dirReporte;
    private String dirSalida;
    
    public void generaReporte(){
         try{
            
             JasperReport reporte = (JasperReport) JRLoader.loadObject(dirReporte);
                
            }catch(Exception e){
                
            }
    }
}
