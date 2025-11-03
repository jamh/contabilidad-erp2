package com.feraz.cfdi.process;

import com.feraz.cfdi.dao.ErpSatLeerCfdiDao;
import com.feraz.facturas.webcontrolfe.App.App;
import com.feraz.facturas.webcontrolfe.dto.PolizasInfo;

import com.feraz.sat.cfdi.read.SeleReadSAT;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import org.springframework.beans.factory.annotation.Value;

/**
 *
 * @author Ing. JAMH
 */
public class ProcesaCFDISAT {

    private App app;
    private ErpSatLeerCfdiDao erpSatLeerCfdiDao;

    @Value("${document.file.dirOutDocumentCXP}")
    private String dirOutXml;

    @Value("${file.phantom.phantomdir}")
    private String dirPhantom;

    @Value("${file.phantom.chromedriver}")
    private String dirChromeDriver;

    @Value("${file.phantom.flag}")
    private String flagPhantom;
//    private SeleReadSAT readCRSAT;

    public ProcesaCFDISAT() {
    }

    public int procesaSATCFDI(String compania, String rfc, String password) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy");
        SimpleDateFormat sdfp = new SimpleDateFormat("MM");

//        System.out.println("CAlendario: "+sdf.format(new Date()));
        int numXml = procesaSATCFDI(compania, rfc, password, sdf.format(new Date()), sdfp.format(new Date()),"");

        return numXml;
    }

    public int procesaSATCFDI(String compania, String rfc, String password, String calendario, String periodo,String user) {
       return 1;

    }

    public int procesaSATCFDICXC(String compania, String rfc, String password) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy");
        SimpleDateFormat sdfp = new SimpleDateFormat("MM");

//        System.out.println("CAlendario: "+sdf.format(new Date()));
        int numXml = procesaSATCFDICXC(compania, rfc, password, sdf.format(new Date()), sdfp.format(new Date()));

        return numXml;
    }

    public int procesaSATCFDICXC(String compania, String rfc, String password, String calendario, String periodo) {
      return 1;

    }

    public void setApp(App app) {
        this.app = app;
    }

    public void setDirOutXml(String dirOutXml) {
        this.dirOutXml = dirOutXml;
    }

    public void setDirPhantom(String dirPhantom) {
        this.dirPhantom = dirPhantom;
    }

    public void setErpSatLeerCfdiDao(ErpSatLeerCfdiDao erpSatLeerCfdiDao) {
        this.erpSatLeerCfdiDao = erpSatLeerCfdiDao;
    }

    public void setFlagPhantom(String flagPhantom) {
        this.flagPhantom = flagPhantom;
    }

    public void setDirChromeDriver(String dirChromeDriver) {
        this.dirChromeDriver = dirChromeDriver;
    }

}
