/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.feraz.contabilidad.ws.nomina.process;

import com.feraz.contabilidad.nomina.model.ErpNomCancela;
import com.feraz.contabilidad.nomina.model.ErpNomPoliza;
import com.feraz.procesos.dao.ProcesosDao;
import com.feraz.procesos.model.Proceso;
import com.feraz.sqr.EjSQR2;
import org.springframework.beans.factory.annotation.Value;
import java.util.Date;

/**
 *
 * @author Administrador
 */
public class ProcesaPolizasNomina {

//    private Proceso proceso;
    private ProcesosDao procesosDao;
    @Value("${batch.program.polizas.genera}")
    private String sqrGeneraPolizas;
    @Value("${batch.program.polizas.cancela}")
    private String sqrCancelaPolizas;
//    private SessionFactory sessionFactory;

    public int generaPolizas(ErpNomPoliza erpNomPoliza) {
        
        try{
        Long pidMax = procesosDao.getMaxPid();
        System.out.println("MaxPid:" + pidMax);
        Proceso proceso = new Proceso();
        proceso.setPid(pidMax);
        proceso.setUsuario("WSErpNomina");
        proceso.setModulo("PRO_nomina_polizas");
        proceso.setServidor("web");
        proceso.setParametros(erpNomPoliza.getCompania()+"|"+erpNomPoliza.getPid());
        proceso.setFechaIni(new Date());
        proceso.setFormato("0");
        proceso.setDestino("D:\\Administrategia\\Empresarial\\Reportes\\PRO_nomina_polizas\\PRO_nomina_polizas.pdf");
        proceso.setComando("-PRINTER:EP -EP -EH_PDF -EH_CSV ");
        proceso.setEstatus(new Long(1));
        proceso.setArchProp("/webFactory/contabWeb.properties");
        Long guarda = null;
        guarda = procesosDao.save(proceso);

        System.out.println("Guarda:" + guarda);
        
        EjSQR2 ej2 = new EjSQR2(sqrGeneraPolizas);
        ej2.procesa(""+pidMax);
      //  ej2.procesa(""+pidMax);
            
          } catch (Exception e) {
            e.printStackTrace();
            return 1;
        }
        return 0;
    }
    
    public int cancelaPolizas(ErpNomCancela erpNomCancela) {
        
        Long pidMax = procesosDao.getMaxPid();
//        System.out.println("MaxPid:" + pidMax);
        Proceso proceso = new Proceso();
        proceso.setPid(pidMax);
        proceso.setUsuario("WSErpNomina");
        proceso.setModulo("PRO_cancela_poliza_nom");
        proceso.setServidor("web");
        proceso.setParametros(erpNomCancela.getCompania()+"|"+erpNomCancela.getPid());
        proceso.setFechaIni(new Date());
        proceso.setFormato("0");
        proceso.setDestino("D:\\Administrategia\\Empresarial\\Reportes\\PRO_cancela_poliza_nom\\PRO_cancela_poliza_nom.pdf");
        proceso.setComando("-PRINTER:EP -EP -EH_PDF -EH_CSV ");
        proceso.setEstatus(new Long(1));
        proceso.setArchProp("/webFactory/contabWeb.properties");
        Long guarda = null;
        guarda = procesosDao.save(proceso);

        System.out.println("Guarda:" + guarda);
        
        EjSQR2 ej2 = new EjSQR2(sqrCancelaPolizas);
        ej2.procesa(""+pidMax);
      //  ej2.procesa(""+maxPid);
        
     return 0;
    }

    public void setProcesosDao(ProcesosDao procesosDao) {
        this.procesosDao = procesosDao;
    }

    
    
}
