

package com.feraz.facturas.webcontrolfe.process;

import de.congrace.exp4j.Calculable;
import java.util.Map;
//import net.objecthunter.exp4j.Expression;
//import net.objecthunter.exp4j.ExpressionBuilder;
import org.jamh.wf.process.Querys;

/**
 *
 * @author Ing. JAMH
 */
public class Operaciones {
    private   Querys a = new Querys();

    public Operaciones() {
    }
    
    
    public String remplaceData(String operacion,Map datos){
        return a.remplazaParametros(operacion, datos);
    }
    public double operacion( String operacion,Map datos){
           
       String val= remplaceData(operacion, datos);
       
       System.out.println("Expresion:"+val);
       try{
           
           Calculable calc = new de.congrace.exp4j.ExpressionBuilder(val)
//      .withVariable("x", varX)
//      .withVariable("y", varY)
      .build();
    double result =calc.calculate();
//        Expression e = new ExpressionBuilder(val)
//        .variables()
//        .build();
//        .setVariable("x", 2.3)
//        .setVariable("y", 3.14);
       
//        double result = e.evaluate();
//         System.out.println("resul:"+result);
        return  result;
       }catch(Exception  e){
           
           e.printStackTrace();
           return 0.0d;
       }
    }
    
}
