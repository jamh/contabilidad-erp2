/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.feraz.polizas3.controll;

import java.io.ByteArrayOutputStream;
import java.sql.Connection;
import java.sql.SQLException;

import java.util.HashMap;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.export.JRPdfExporter;
import net.sf.jasperreports.engine.export.JRXlsExporterParameter;
import net.sf.jasperreports.engine.export.ooxml.JRDocxExporter;
import net.sf.jasperreports.engine.export.ooxml.JRXlsxExporter;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.connection.ConnectionProvider;
import org.hibernate.engine.SessionFactoryImplementor;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

/**
 *
 * @author Ing. JAMH
 */
@Controller
@RequestMapping("/report")
@SessionAttributes({"compania", "usuario"})
public class ReportController {

//    private DetPolizasDao detPolizasDao;
//    JasperReport jasperReport;
//    JasperPrint jasperPrint;
    private SessionFactory sessionFactory;

    @Value("${document.file.dirReport}")
    private String dirReporte;

//    private String dirOutReporte;
    @RequestMapping(value = "/xls", method = RequestMethod.POST)
    public @ResponseBody
    HttpEntity<byte[]> generateXlsReport(ModelAndView modelAndView, Model model,
            @RequestParam("tipo") String tipoPoliza,
            @RequestParam("fecha") String fecha,
            @RequestParam("numero") String numero,
            HttpServletRequest request, HttpServletResponse response
    ) {

        if (model.asMap().get("compania") == null) {
            return null;
        }
        String compania = model.asMap().get("compania").toString();

        byte[] reporte = null;
        try {
            String time = "" + System.currentTimeMillis();
            String nombre = "Rep_" + compania + tipoPoliza + numero + fecha + "_" + time + ".xlsx";
            //jasperReport = JasperCompileManager.compileReport("H:\\Desarrollo2\\Polizas3\\src\\main\\resources\\com\\feraz\\polizas3\\report\\ReportDetPolizas.jrxml");
            Map parametros = new HashMap();
            parametros.put("compania", compania);
            parametros.put("numero", numero);
            parametros.put("fecha", fecha);
            parametros.put("tipo", tipoPoliza);
            //reporte = JasperRunManager.runReportToPdf(jasperReport, parametros, dataSource.getConnection());

            ByteArrayOutputStream output = new ByteArrayOutputStream();
//            OutputStream outputfile = new FileOutputStream(new File("H:\\logs\\"+nombre));

            //fill the ready report with data and parameter
            //jasperPrint = JasperFillManager.fillReport(jasperReport, parametros,dataSource);
            //Connection c = DataSourceUtils.getConnection((DataSource) dataSource);
            JasperReport jasperReport = JasperCompileManager.compileReport(dirReporte);
            JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parametros,getConnection());

            //coding for Excel
            JRXlsxExporter exporterXls = new JRXlsxExporter();
            exporterXls.setParameter(JRXlsExporterParameter.JASPER_PRINT, jasperPrint);
//           exporterXls.setParameter(JRXlsExporterParameter.OUTPUT_STREAM, outputfile);
            exporterXls.setParameter(JRXlsExporterParameter.OUTPUT_STREAM, output);
            exporterXls.setParameter(JRXlsExporterParameter.OUTPUT_FILE_NAME, nombre);
//            exporterXls.setParameter(JRXlsExporterParameter.IS_ONE_PAGE_PER_SHEET, Boolean.TRUE);
//            exporterXls.setParameter(JRXlsExporterParameter.IS_DETECT_CELL_TYPE, Boolean.TRUE);
            exporterXls.exportReport();
//    outputfile.close();
            byte[] wb = output.toByteArray();
            HttpHeaders header = new HttpHeaders();
            header.setContentType(new MediaType("application", "vnd.openxmlformats-officedocument.spreadsheetml.sheet"));
            header.set("Content-Disposition",
                    "attachment; filename=" + nombre);
            header.setContentLength(wb.length);
            output.close();
            return new HttpEntity<byte[]>(wb, header);

        } catch (Exception ex) {

            ex.printStackTrace();
            return null;
        }

        //return new ModelAndView("xls");
    }//generateexcel

    @RequestMapping(value = "/word", method = RequestMethod.POST)
    public @ResponseBody
    HttpEntity<byte[]> generateWordReport(ModelAndView modelAndView, Model model,
            @RequestParam("tipo") String tipoPoliza,
            @RequestParam("fecha") String fecha,
            @RequestParam("numero") String numero,
            HttpServletRequest request, HttpServletResponse response
    ) {

        if (model.asMap().get("compania") == null) {
            return null;
        }
        String compania = model.asMap().get("compania").toString();

        byte[] reporte = null;
        try {
            String time = "" + System.currentTimeMillis();
            String nombre = "Rep_" + compania + tipoPoliza + numero + fecha + "_" + time + ".docx";
            //jasperReport = JasperCompileManager.compileReport("H:\\Desarrollo2\\Polizas3\\src\\main\\resources\\com\\feraz\\polizas3\\report\\ReportDetPolizas.jrxml");
            Map parametros = new HashMap();
            parametros.put("compania", compania);
            parametros.put("numero", numero);
            parametros.put("fecha", fecha);
            parametros.put("tipo", tipoPoliza);
            //reporte = JasperRunManager.runReportToPdf(jasperReport, parametros, dataSource.getConnection());

            ByteArrayOutputStream output = new ByteArrayOutputStream();
//            OutputStream outputfile = new FileOutputStream(new File("H:\\logs\\"+nombre));

            //fill the ready report with data and parameter
            //jasperPrint = JasperFillManager.fillReport(jasperReport, parametros,dataSource);
          //  Connection c = DataSourceUtils.getConnection((DataSource) dataSource);
            JasperReport jasperReport = JasperCompileManager.compileReport(dirReporte);
            // JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parametros, dataSource.getConnection());
            JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parametros, getConnection());

            //coding for Excel
            JRDocxExporter exporterXls = new JRDocxExporter();
            exporterXls.setParameter(JRXlsExporterParameter.JASPER_PRINT, jasperPrint);
//           exporterXls.setParameter(JRXlsExporterParameter.OUTPUT_STREAM, outputfile);
            exporterXls.setParameter(JRXlsExporterParameter.OUTPUT_STREAM, output);
            exporterXls.setParameter(JRXlsExporterParameter.OUTPUT_FILE_NAME, nombre);
//            exporterXls.setParameter(JRXlsExporterParameter.IS_ONE_PAGE_PER_SHEET, Boolean.TRUE);
//            exporterXls.setParameter(JRXlsExporterParameter.IS_DETECT_CELL_TYPE, Boolean.TRUE);
            exporterXls.exportReport();
//    outputfile.close();
            byte[] wb = output.toByteArray();
            HttpHeaders header = new HttpHeaders();
            header.setContentType(new MediaType("application", "vnd.openxmlformats-officedocument.spreadsheetml.sheet"));
            header.set("Content-Disposition",
                    "attachment; filename=" + nombre);
            header.setContentLength(wb.length);
            output.close();
            return new HttpEntity<byte[]>(wb, header);

        } catch (Exception ex) {

            ex.printStackTrace();
            return null;
        }

        //return new ModelAndView("xls");
    }//generate word

    @RequestMapping(value = "/pdf", method = RequestMethod.POST)
    public @ResponseBody
    HttpEntity<byte[]> generatePdfReport(ModelAndView modelAndView, Model model,
            @RequestParam("tipo") String tipoPoliza,
            @RequestParam("fecha") String fecha,
            @RequestParam("numero") String numero,
            HttpServletRequest request, HttpServletResponse response
    ) {

        if (model.asMap().get("compania") == null) {
            return null;
        }
        String compania = model.asMap().get("compania").toString();

        byte[] reporte = null;
        try {
            String time = "" + System.currentTimeMillis();
            String nombre = "Rep_" + compania + tipoPoliza + numero + fecha + "_" + time + ".pdf";
            //jasperReport = JasperCompileManager.compileReport("H:\\Desarrollo2\\Polizas3\\src\\main\\resources\\com\\feraz\\polizas3\\report\\ReportDetPolizas.jrxml");
            Map parametros = new HashMap();
            parametros.put("compania", compania);
            parametros.put("numero", numero);
            parametros.put("fecha", fecha);
            parametros.put("tipo", tipoPoliza);
            //reporte = JasperRunManager.runReportToPdf(jasperReport, parametros, dataSource.getConnection());

            ByteArrayOutputStream output = new ByteArrayOutputStream();
//            OutputStream outputfile = new FileOutputStream(new File("H:\\logs\\"+nombre));

            //fill the ready report with data and parameter
            //jasperPrint = JasperFillManager.fillReport(jasperReport, parametros,dataSource);
        //    Connection c = DataSourceUtils.getConnection((DataSource) dataSource);
            JasperReport jasperReport = JasperCompileManager.compileReport(dirReporte);
            // JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parametros, dataSource.getConnection());
            JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parametros, getConnection());

            //coding for Excel
            JRPdfExporter exporterXls = new JRPdfExporter();
            exporterXls.setParameter(JRXlsExporterParameter.JASPER_PRINT, jasperPrint);
//           exporterXls.setParameter(JRXlsExporterParameter.OUTPUT_STREAM, outputfile);
            exporterXls.setParameter(JRXlsExporterParameter.OUTPUT_STREAM, output);
            exporterXls.setParameter(JRXlsExporterParameter.OUTPUT_FILE_NAME, nombre);
//            exporterXls.setParameter(JRXlsExporterParameter.IS_ONE_PAGE_PER_SHEET, Boolean.TRUE);
//            exporterXls.setParameter(JRXlsExporterParameter.IS_DETECT_CELL_TYPE, Boolean.TRUE);
            exporterXls.exportReport();
//    outputfile.close();
            byte[] wb = output.toByteArray();
            HttpHeaders header = new HttpHeaders();
            header.setContentType(new MediaType("application", "vnd.openxmlformats-officedocument.spreadsheetml.sheet"));
            header.set("Content-Disposition",
                    "attachment; filename=" + nombre);
            header.setContentLength(wb.length);
            output.close();
            return new HttpEntity<byte[]>(wb, header);

        } catch (Exception ex) {

            ex.printStackTrace();
            return null;
        }

        //return new ModelAndView("xls");
    }//generate word

//    public void setDataSource(JndiObjectFactoryBean dataSource) {
//        this.dataSource = dataSource;
//    }
//    public void setDataSource(JndiObjectFactoryBean dataSource) {
//        this.dataSource = dataSource;
//    }
//    public void setDataSource(JndiObjectFactoryBean dataSource) {
//        this.dataSource = dataSource;
//    }
    public Connection getConnection() {

        Session session = sessionFactory.openSession();
        SessionFactoryImplementor sessionFactoryImplementor = null;
        ConnectionProvider connectionProvider = null;
        java.sql.Connection connection = null;
        try {
            sessionFactoryImplementor = (SessionFactoryImplementor) session.getSessionFactory();
            connectionProvider = (ConnectionProvider) sessionFactoryImplementor.getConnectionProvider().getConnection();
            connection = connectionProvider.getConnection();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return connection;
    }

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public void setDirReporte(String dirReporte) {
        this.dirReporte = dirReporte;
    }

    public String getDirReporte() {
        return dirReporte;
    }

}
