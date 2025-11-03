package com.feraz.contabilidad.convertidor.util;

import java.io.IOException;
import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

/**
 *
 * @author Administrador
 */
public class ParidadConvertidor {

    public double getParidadHTML(String fechaIni, String fechaFin) {
        String msg = "";
//        System.out.println("f1:"+fechaIni+ " f2:"+fechaFin);
        try {

            //idioma=sp&fechaInicial=13%2F10%2F2014&fechaFinal=13%2F10%2F2014&salida=HTML
            fechaIni = fechaIni.replaceAll("/", "%2F");
            fechaFin = fechaFin.replaceAll("/", "%2F");

            String url = "http://www.banxico.org.mx/tipcamb/tipCamIHAction.do?idioma=sp&fechaInicial=" + fechaIni + "&fechaFinal=" + fechaFin + "&salida=HTML";
            System.out.println(url);
            Document doc = Jsoup.connect(url).get();
            Elements newsHeadlines = doc.getElementsByAttributeValue("class", "renglonPar");
//             String msg="";
//             System.out.println(newsHeadlines.size());
            Iterator<Element> el = newsHeadlines.iterator();
//              System.out.println("--------------------------"+fechaIni);
            while (el.hasNext()) {
                Element ele = el.next();
//                System.out.println(ele.text());
//                System.out.println(ele.toString());
                msg = ele.text();
            }
//            System.out.println("--------------------------"+fechaFin);
//            System.out.println();

            return parseDouble(msg);

        } catch (IOException e) {
            e.printStackTrace();
            return 0.0;
        }
    }

    public double getParidadHTML2(String fechaIni, String fechaFin) {
        String msg = "";
//        System.out.println("f1:"+fechaIni+ " f2:"+fechaFin);
        try {

            //idioma=sp&fechaInicial=13%2F10%2F2014&fechaFinal=13%2F10%2F2014&salida=HTML
            fechaIni = fechaIni.replaceAll("/", "%2F");
            fechaFin = fechaFin.replaceAll("/", "%2F");

            String url = "http://www.dof.gob.mx/indicadores_detalle.php?cod_tipo_indicador=158&dfecha=" + fechaIni + "&hfecha=" + fechaFin ;
            System.out.println(url);
            Document doc = Jsoup.connect(url).get();
            Elements newsHeadlines = doc.getElementsByAttributeValue("class", "txt");
//             String msg="";
//             System.out.println(newsHeadlines.size());
            Iterator<Element> el = newsHeadlines.iterator();
//              System.out.println("--------------------------"+fechaIni);
            while (el.hasNext()) {
                Element ele = el.next();
//                System.out.println("ele.text():"+ele.text());
//                System.out.println("ele.toString():"+ele.toString());
                msg = ele.text();
            }
//            System.out.println("--------------------------"+fechaFin);
//            System.out.println("MSG:"+msg);

            return parseDouble(msg);

        } catch (IOException e) {
            e.printStackTrace();
            return 0.0;
        }
    }

    public double parseDouble(String val) {
        try {
            Double temp = Double.valueOf(val);
            return temp.doubleValue();
        } catch (Exception e) {
            return 0.0d;
        }
    }

    public Date addDays(Date date, int days) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.add(Calendar.DATE, days); //minus number would decrement the days
        return cal.getTime();
    }

}
