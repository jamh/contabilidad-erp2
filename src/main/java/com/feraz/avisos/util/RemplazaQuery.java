/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.feraz.avisos.util;

import java.util.Iterator;
import java.util.Map;

/**
 *
 * @author Feraz3
 */
public class RemplazaQuery {
    
       public String remplazaParametros(String query, Map valores)
        {
          Iterator it = valores.entrySet().iterator();
          while (it.hasNext())
          {
            Map.Entry e = (Map.Entry)it.next();
            String valRemplasa = "\\[" + (String)e.getKey() + "\\]";
            String valNuevo = pasaTexto(e.getValue());

            query = query.replaceAll(valRemplasa, valNuevo);
          }
          return query;
        }
    
       
     public String pasaTexto(Object o)
        {
          try
          {
            return o.toString();
          }
          catch (Exception e) {}
          return "null";
        }
     
    
}
