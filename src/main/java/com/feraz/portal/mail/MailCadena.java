
package com.feraz.portal.mail;

import java.util.Random;

/**
 *
 * @author feraz765
 */
public class MailCadena {
    
    
    public String getCadenaAlfanumAleatoria (int longitud){
        String cadenaAleatoria = "";
        long milis = new java.util.GregorianCalendar().getTimeInMillis();
        Random r = new Random(milis);
        int i = 0;
        while ( i < longitud){
        char c = (char)r.nextInt(255);
        if ( (c >= '0' && c<='9') || (c >='A' && c <='Z') ){
        cadenaAleatoria += c;
        i ++;
        }
        }
        return cadenaAleatoria;
      }
     
     
//      public static void main(String[] args){
//      
//      String cadena = getCadenaAlfanumAleatoria (10);
//      
//      System.out.println(cadena);
//      
//      }
    
}