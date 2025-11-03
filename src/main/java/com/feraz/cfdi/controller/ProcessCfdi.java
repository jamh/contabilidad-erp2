
package com.feraz.cfdi.controller;

/**
 *
 * @author Feraz3
 */
import com.feraz.cfdi.dao.ErpSatLeerCfdiDao;
import com.feraz.cfdi.model.ErpSatLeerCfdi;
import com.feraz.cfdi.model.ErpSatLeerCfdiId;
import com.feraz.cfdi.process.ProcesaCFDISAT;
import java.util.Map;
import org.jamh.data.process.ProcessDao;
import org.jamh.wf.json.model.ResponseQuery;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.context.request.WebRequest;

@Controller
@RequestMapping("/CfdiSat")
@SessionAttributes({"compania", "usuario"})
public class ProcessCfdi {

    private ErpSatLeerCfdiDao erpSatLeerCfdiDao;
    private ProcessDao processDao;
    private ProcesaCFDISAT procesaCFDISAT;

    @RequestMapping(value = "/saveDatos", method = RequestMethod.POST)
    public @ResponseBody
    ResponseQuery registro(String data,        
            WebRequest webRequest, Model model) {

        Map parametros = processDao.paramToMap(webRequest.getParameterMap());

        ResponseQuery rq = new ResponseQuery();

        String compania = model.asMap().get("compania").toString();
        String usuario = model.asMap().get("usuario").toString();

        System.out.println("txt_RFC en save datos");

        String rfc = parametros.get("rfc").toString();
        String password = parametros.get("password").toString();
        String secuencia = parametros.get("secuencia").toString();
        String periodo = parametros.get("periodo").toString();
        String calendario = parametros.get("calendario").toString();
        String bandera = parametros.get("txtSaveDat").toString();
        System.out.println("bandera: " + bandera);


        try {
            ErpSatLeerCfdi erpSatLeerCfdi = new ErpSatLeerCfdi();
            ErpSatLeerCfdiId id = new ErpSatLeerCfdiId();

            if (!bandera.equalsIgnoreCase("false")) {
                if (secuencia == null || secuencia.equals("")) {

                    id.setCompania(compania);
                    int maxId = erpSatLeerCfdiDao.getMaxId(id);

                    System.out.println("maxId" + maxId);

                    id.setId(maxId);
                    erpSatLeerCfdi.setId(id);
                    erpSatLeerCfdi.setRfc(rfc);
                    erpSatLeerCfdi.setPassword(password);
                    erpSatLeerCfdi.setUsuario(usuario);

                    ErpSatLeerCfdiId result = erpSatLeerCfdiDao.save(erpSatLeerCfdi);

                    if (result == null) {

                        rq.setSuccess(false);
                        rq.setMsg("Error al guardar datos");

                    } else {

                        rq.setSuccess(true);
                        rq.setMsg("Datos guardados correctamente");

                    }

                } else {

                    id.setCompania(compania);
                    id.setId(Integer.parseInt(secuencia));
                    erpSatLeerCfdi.setId(id);
                    erpSatLeerCfdi.setRfc(rfc);
                    erpSatLeerCfdi.setPassword(password);
                    erpSatLeerCfdi.setUsuario(usuario);

                    boolean result = erpSatLeerCfdiDao.update(erpSatLeerCfdi);

                    if (result == false) {

                        rq.setSuccess(false);
                        rq.setMsg("Error al guardar datos");

                    } else {

                        rq.setSuccess(true);
                        rq.setMsg("Datos guardados correctamente");

                    }

                }
            }
            int numeroFact = procesaCFDISAT.procesaSATCFDI(compania, rfc, password, calendario, periodo,usuario);

            if (numeroFact == 0) {
                rq.setSuccess(false);
                rq.setMsg("Error en la lectura de las facturas");
            } else {

                rq.setSuccess(true);
                rq.setMsg(numeroFact + " facturas descargadas");

            }

        } catch (Exception e) {
            rq.setSuccess(false);
            rq.setMsg("Error al guardar los datos");
            e.printStackTrace();
            return rq;
        }

        // return rq;
        return rq;

    }

    @RequestMapping(value = "/saveDatosCXC", method = RequestMethod.POST)
    public @ResponseBody
    ResponseQuery registroCXC(String data,
            //            @RequestParam("rfc") String rfc,
            //              @RequestParam("password") String password,
            //              @RequestParam("secuencia") String secuencia,
            //              @RequestParam("periodo") String periodo,
            //              @RequestParam("calendario") String calendario, 
            WebRequest webRequest, Model model) {

        Map parametros = processDao.paramToMap(webRequest.getParameterMap());

        ResponseQuery rq = new ResponseQuery();

        String compania = model.asMap().get("compania").toString();
        String usuario = model.asMap().get("usuario").toString();

        String rfc = parametros.get("rfc").toString();
        String password = parametros.get("password").toString();
        String secuencia = parametros.get("secuencia").toString();
        String periodo = parametros.get("periodo").toString();
        String calendario = parametros.get("calendario").toString();
        String bandera = parametros.get("txtSaveDat").toString();
        System.out.println("bandera: " + bandera);

        try {
            ErpSatLeerCfdi erpSatLeerCfdi = new ErpSatLeerCfdi();
            ErpSatLeerCfdiId id = new ErpSatLeerCfdiId();
            if (!bandera.equalsIgnoreCase("false")) {
                if (secuencia == null || secuencia == "") {

                    id.setCompania(compania);
                    int maxId = erpSatLeerCfdiDao.getMaxId(id);

                    System.out.println("maxId" + maxId);

                    id.setId(maxId);
                    erpSatLeerCfdi.setId(id);
                    erpSatLeerCfdi.setRfc(rfc);
                    erpSatLeerCfdi.setPassword(password);
                    erpSatLeerCfdi.setUsuario(usuario);

                    ErpSatLeerCfdiId result = erpSatLeerCfdiDao.save(erpSatLeerCfdi);

                    if (result == null) {

                        rq.setSuccess(false);
                        rq.setMsg("Error al guardar datos");

                    } else {

                        rq.setSuccess(true);
                        rq.setMsg("Datos guardados correctamente");

                    }

                } else {

                    id.setCompania(compania);
                    id.setId(Integer.parseInt(secuencia));
                    erpSatLeerCfdi.setId(id);
                    erpSatLeerCfdi.setRfc(rfc);
                    erpSatLeerCfdi.setPassword(password);
                    erpSatLeerCfdi.setUsuario(usuario);

                    boolean result = erpSatLeerCfdiDao.update(erpSatLeerCfdi);

                    if (result == false) {

                        rq.setSuccess(false);
                        rq.setMsg("Error al guardar datos");

                    } else {

                        rq.setSuccess(true);
                        rq.setMsg("Datos guardados correctamente");

                    }

                }
            }
            int numeroFact = procesaCFDISAT.procesaSATCFDICXC(compania, rfc, password, calendario, periodo);

            if (numeroFact == 0) {
                rq.setSuccess(false);
                rq.setMsg("Error en la lectura de las facturas");
            } else {

                rq.setSuccess(true);
                rq.setMsg(numeroFact + " facturas descargadas");

            }

        } catch (Exception e) {
            rq.setSuccess(false);
            rq.setMsg("Error al guardar los datos");
            e.printStackTrace();
            return rq;
        }

        // return rq;
        return rq;

    }

    @RequestMapping(value = "/deleteDatos", method = RequestMethod.POST)
    public @ResponseBody
    ResponseQuery deletDatos(String data, WebRequest webRequest, Model model) {

        Map parametros = processDao.paramToMap(webRequest.getParameterMap());

        ResponseQuery rq = new ResponseQuery();

        String compania = model.asMap().get("compania").toString();
        String usuario = model.asMap().get("usuario").toString();

        System.out.println("txt_RFC: " + parametros.get("txt_RFC").toString());

        String rfc = parametros.get("txt_RFC").toString();
        String password = parametros.get("txtPassword").toString();
        String secuencia = parametros.get("txtSecuencia").toString();

        try {
            ErpSatLeerCfdi erpSatLeerCfdi = new ErpSatLeerCfdi();
            ErpSatLeerCfdiId id = new ErpSatLeerCfdiId();

            id.setCompania(compania);
            id.setId(Integer.parseInt(secuencia));
            erpSatLeerCfdi.setId(id);
            erpSatLeerCfdi.setRfc(rfc);
            erpSatLeerCfdi.setPassword(password);
            erpSatLeerCfdi.setUsuario(usuario);

            boolean result = erpSatLeerCfdiDao.delete(erpSatLeerCfdi);

            if (result == false) {

                rq.setSuccess(false);
                rq.setMsg("Error al borrar datos");

            } else {

                rq.setSuccess(true);
                rq.setMsg("Datos borrados correctamente");

            }

        } catch (Exception e) {
            rq.setSuccess(false);
            rq.setMsg("Error al borrar los datos");
            e.printStackTrace();
            return rq;
        }

        // return rq;
        return rq;

    }

    @RequestMapping(value = "/readXml", method = RequestMethod.POST)
    public @ResponseBody
    ResponseQuery readXml(String data, WebRequest webRequest, Model model) {

        Map parametros = processDao.paramToMap(webRequest.getParameterMap());

        ResponseQuery rq = new ResponseQuery();

        String compania = model.asMap().get("compania").toString();
        String usuario = model.asMap().get("usuario").toString();

        String rfc = parametros.get("rfc").toString();
        String password = parametros.get("password").toString();
        String secuencia = parametros.get("secuencia").toString();
        String periodo = parametros.get("periodo").toString();
        String calendario = parametros.get("calendario").toString();

        try {

            int numeroFact = procesaCFDISAT.procesaSATCFDICXC(compania, rfc, password, calendario, periodo);

            if (numeroFact == 0) {
                rq.setSuccess(false);
                rq.setMsg("Error en la lectura de las facturas");
            } else {

                rq.setSuccess(true);
                rq.setMsg(numeroFact + " facturas descargadas");

            }

        } catch (Exception e) {
            rq.setSuccess(false);
            rq.setMsg("Error XMl");
            e.printStackTrace();
            return rq;
        }

        // return rq;
        return rq;

    }

    public void setErpSatLeerCfdiDao(ErpSatLeerCfdiDao erpSatLeerCfdiDao) {
        this.erpSatLeerCfdiDao = erpSatLeerCfdiDao;
    }

    public void setProcessDao(ProcessDao processDao) {
        this.processDao = processDao;
    }

    public void setProcesaCFDISAT(ProcesaCFDISAT procesaCFDISAT) {
        this.procesaCFDISAT = procesaCFDISAT;
    }

}
