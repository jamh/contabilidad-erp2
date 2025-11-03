/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.feraz.contabilidad.nomina.validation;

/**
 *
 * @author Feraz3
 */
import com.feraz.contabilidad.nomina.model.ErpNomCancela;
import com.feraz.contabilidad.nomina.model.ErpNomPoliza;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import org.jamh.data.process.ProcessDao;
import java.util.Map;

public class ValidationWSContab {

    private ProcessDao processDao;
    private String msgError;

    public boolean validaCuenta(ErpNomPoliza erpNomPoliza) {
//         
        Map parametros = new HashMap();
//         
//         
//         
        parametros.put("compania", erpNomPoliza.getCompania());
        parametros.put("cuenta", erpNomPoliza.getCuenta());

//         if(parametros.get("cuenta")== null || parametros.get("cuenta") == ""){
//                return false;
//         }
//         
        List list = processDao.getMapResult("BuscaCuentaWS", parametros);

        if (list == null || list.size() == 0) {

            System.out.println("Cuenta no correcta");
            setMsgError("error: La cuenta no es correcta");
            return false;
        } else {
            return true;
//        }

        }
    }

    public int cuentaPid(ErpNomPoliza erpNomPoliza) {
//         
        Map parametros = new HashMap();
//         
//         
//         
        parametros.put("compania", erpNomPoliza.getCompania());
        parametros.put("pid", erpNomPoliza.getPid());

//         if(parametros.get("cuenta")== null || parametros.get("cuenta") == ""){
//                return false;
//         }
//         
        List list = processDao.getMapResult("BuscaNumeroRegistros", parametros);

        if (list == null || list.size() == 0) {

            System.out.println("PID");
            setMsgError("error: Pid no encontrado");
            return 0;
        } else {

            Map result = (HashMap) list.get(0);
            System.out.println("select:" + result.get("PID_MAX"));

            String pidMax = result.get("PID_MAX").toString();

            return Integer.parseInt(pidMax);
             // return result.get("PID_MAX").toString();
//        }

        }
    }

    public boolean validaPoliza(ErpNomPoliza erpNomPoliza, String convertido) {
//         
        Map parametros = new HashMap();
//         
//         
//         
        parametros.put("compania", erpNomPoliza.getCompania());
        parametros.put("pid", erpNomPoliza.getPid());
        parametros.put("calendario", erpNomPoliza.getCalendario());
        parametros.put("periodo", erpNomPoliza.getPeriodo());
        parametros.put("grupo_pago", erpNomPoliza.getGrupoPago());
        parametros.put("departamento", erpNomPoliza.getDepartamento());
        parametros.put("fecha_pago", convertido);
        parametros.put("proceso_especial", erpNomPoliza.getProcesoEspecial());
        parametros.put("cto_cto", erpNomPoliza.getCentroCostos());
        parametros.put("descripcion", erpNomPoliza.getDescripcion2());
        parametros.put("empleado", erpNomPoliza.getEmpleado());

//         if(parametros.get("cuenta")== null || parametros.get("cuenta") == ""){
//                return false;
//         }
//         
        List list = processDao.getMapResult("ValidaPolizaNom", parametros);

        System.out.println("LIST" + list);

        if (list == null || list.size() == 0) {

            return true;

        }

        System.out.println("List 0" + list.get(0));

        Map result = (HashMap) list.get(0);
        System.out.println("select:" + result.get("NUMERO_POLIZA"));
        if (list == null || list.size() == 0) {

            return true;

        } else {

            if (result.get("NUMERO_POLIZA") == null || result.get("NUMERO_POLIZA") == "") {

                return true;

            } else {

                System.out.println("Ya existe la poliza");
                setMsgError("error: Ya existe poliza con estos datos");
                return false;
            }
//        }

        }
    }

    public boolean validaCC(ErpNomPoliza erpNomPoliza) {

        Map parametros = new HashMap();

        parametros.put("compania", erpNomPoliza.getCompania());
        parametros.put("CC", erpNomPoliza.getCentroCostos());

        List list = processDao.getMapResult("BuscaCCCorrectoNom", parametros);

        if (list == null || list.size() == 0) {
            setMsgError("error: El Centro de Costo no es valido");
            return false;
        } else {
            return true;
//        }

        }
    }

    public boolean buscaPeriodo(ErpNomPoliza erpNomPoliza, String fecha) {

        Map parametros = new HashMap();

        parametros.put("compania", erpNomPoliza.getCompania());
        parametros.put("periodo", erpNomPoliza.getPeriodo());
        parametros.put("calendario", erpNomPoliza.getCalendario());
        parametros.put("fecha", fecha);

        List list = processDao.getMapResult("BuscaPeriodoWS", parametros);

        if (list == null || list.size() == 0) {
            setMsgError("error: El Periodo esta cerrado o no es un periodo y calendario valido");
            return false;
        } else {
            return true;
//        }

        }
    }

    public boolean validaDatos(ErpNomPoliza erpNomPoliza) {

//         
        if (erpNomPoliza.getCompania() == null || erpNomPoliza.getCompania() == "") {

            System.out.println("en compañia");

            setMsgError("error: Falta compañia");
            return false;

        }

        if (erpNomPoliza.getPid() == null || erpNomPoliza.getPid() == "") {

            setMsgError("error: Falta pid");
            return false;

        }

        if (erpNomPoliza.getNumeroMovimientos() == null || erpNomPoliza.getNumeroMovimientos() == "") {

            setMsgError("error: Falta Numero de Movimientos");
            return false;

        }
        if (erpNomPoliza.getEmpleado() == null || erpNomPoliza.getEmpleado()== "") {

            setMsgError("error: Falta numero de Empleado");
            return false;

        }
        if (Integer.toString(erpNomPoliza.getCalendario()) == null || Integer.toString(erpNomPoliza.getCalendario()) == "") {

            setMsgError("error: Falta Calendario");
            return false;

        }

        if (Integer.toString(erpNomPoliza.getPeriodo()) == null || Integer.toString(erpNomPoliza.getPeriodo()) == "") {

            setMsgError("error: Falta Periodo");
            return false;

        }

        if (erpNomPoliza.getFechaPago() == null) {

            setMsgError("error: Falta Fecha de Pago");
            return false;

        }

        if (erpNomPoliza.getCentroCostos() == null || erpNomPoliza.getCentroCostos() == "") {

            setMsgError("error: Falta Centro de Costos");
            return false;

        }

        if (erpNomPoliza.getCuenta() == null || erpNomPoliza.getCuenta() == "") {

            setMsgError("error: Falta Cuenta");
            return false;

        }

        if (erpNomPoliza.getCargos() == null && erpNomPoliza.getAbonos() == null) {

            setMsgError("error: Falta Cargos y Abonos");
            return false;

        } else {

            if (erpNomPoliza.getCargos() == null) {

                erpNomPoliza.setCargos(BigDecimal.ZERO);

            }

            if (erpNomPoliza.getAbonos() == null) {

                erpNomPoliza.setAbonos(BigDecimal.ZERO);

            }

        }

        return true;

    }

//Validaciones de ERP_NOM_CANCELA
    public String validaEstatusPoliza(ErpNomCancela erpNomCancela) {
//         
        Map parametros = new HashMap();
//         
//         
//         
        parametros.put("compania", erpNomCancela.getCompania());
        parametros.put("pid", erpNomCancela.getPid());

//         if(parametros.get("cuenta")== null || parametros.get("cuenta") == ""){
//                return false;
//         }
//         
        System.out.println("BUSCANCO POLIZA ESTATUS--------------------");
        List list = processDao.getMapResult("BuscaPidNomina", parametros);
        if (list == null || list.size() == 0) {

            System.out.println("List null");
            setMsgError("error: error al cancelar");
            return "SIN ESTATUS";
        }

        Map result = (HashMap) list.get(0);
        System.out.println("select:" + result.get("ESTATUS"));

        if (result.get("ESTATUS") == null || result.get("ESTATUS") == "") {

            setMsgError("error: error al cancelar poliza");
            return "SIN ESTATUS";

//        }
        } else {

            if (result.get("ESTATUS").toString().equalsIgnoreCase("GE")) {

                System.out.println("List estatus ge");

                setMsgError("error: error al cancelar la poliza");
                return "SIN ESTATUS";

            } else {

                if (result.get("ESTATUS").toString().equalsIgnoreCase("CA") || result.get("ESTATUS").toString().equalsIgnoreCase("CF")) {

                    System.out.println("estatus ca y cf");
                    return result.get("ESTATUS").toString();

                }

            }

        }

        return "0";
    }

    public boolean validaPidPoliza(ErpNomCancela erpNomCancela) {
//         
        Map parametros = new HashMap();
//         
//         
//         
        parametros.put("compania", erpNomCancela.getCompania());
        parametros.put("pid", erpNomCancela.getPid());

//         if(parametros.get("cuenta")== null || parametros.get("cuenta") == ""){
//                return false;
//         }
//         
        System.out.println("BUSCANCO POLIZA PID--------------------");
        List list = processDao.getMapResult("BuscaPidNomina", parametros);
        if (list == null || list.size() == 0) {

            System.out.println("List null");
            setMsgError("error: este pid no tiene poliza que cancelar");
            return false;
        }

        Map result = (HashMap) list.get(0);
        System.out.println("select:" + result.get("ESTATUS"));

        if (result.get("ESTATUS") == null || result.get("ESTATUS") == "") {

            System.out.println("List estatus null");

            setMsgError("error: este pid no tiene poliza que cancelar");
            return false;

//        }
        } else {

            if (result.get("ESTATUS").equals("GE")) {

                System.out.println("List estatus ge");

                return true;

            } else {

                if (result.get("ESTATUS").equals("CA") || result.get("ESTATUS").equals("CF")) {

                    System.out.println("List estatus ca y cf");

                    setMsgError("error: esta poliza ya fue cancelada");
                    return false;

                }

            }

        }

        return true;
    }

    public boolean validaDatosCancela(ErpNomCancela erpNomCancela) {

        if (erpNomCancela.getCompania() == null || erpNomCancela.getCompania() == "") {

            setMsgError("error: Falta Compañia");
            return false;
        }

        if (erpNomCancela.getPid() == null || erpNomCancela.getPid() == "") {

            setMsgError("error: Falta PID");
            return false;
        }

        if (erpNomCancela.getOperacion() == null || erpNomCancela.getOperacion() == "") {

            setMsgError("error: Falta Operacion");
            return false;
        }

        return true;

    }

    public String buscaDatosPoliza(ErpNomPoliza erpNomPoliza) {
//         
        Map parametros = new HashMap();
//         
//         
//         
        parametros.put("compania", erpNomPoliza.getCompania());
        parametros.put("pid", erpNomPoliza.getPid());

//         if(parametros.get("cuenta")== null || parametros.get("cuenta") == ""){
//                return false;
//         }
//         
        System.out.println("BUSCANCO POLIZA, fecha y tipo--------------------");
        List list = processDao.getMapResult("BuscaDatosPoliza", parametros);
        if (list == null || list.size() == 0) {

            System.out.println("List null");
            setMsgError("error: este pid no tiene poliza");
            return "SIN POLIZA";
        }
        
        if (list.isEmpty()) {

            System.out.println("List null");
            setMsgError("error: este pid no tiene poliza");
            return "SIN POLIZA";
        }

        Map result = (HashMap) list.get(0);
        System.out.println("select:" + result.get("ESTATUS"));

        if (result.get("TIPO_POLIZA") != null || result.get("TIPO_POLIZA") != "") {

            if (result.get("FECHA_POLIZA") != null || result.get("FECHA_POLIZA") != "") {

                if (result.get("NUMERO_POLIZA") != null || result.get("NUMERO_POLIZA") != "") {

                    return result.get("FECHA_POLIZA").toString() + "-" + result.get("TIPO_POLIZA").toString() + "-" + result.get("NUMERO_POLIZA").toString();

                } else {

                    setMsgError("error: este pid no tiene poliza");
                    return "SIN POLIZA";

                }

            } else {

                setMsgError("error: este pid no tiene poliza");
                return "SIN POLIZA";

            }

        } else {

            setMsgError("error: este pid no tiene poliza");
            return "SIN POLIZA";

        }

    }

    public String buscaDatosPolizaCancelacion(ErpNomCancela erpNomCancela) {
//         
        Map parametros = new HashMap();
//         
//         
//         
        parametros.put("compania", erpNomCancela.getCompania());
        parametros.put("pid", erpNomCancela.getPid());

//         if(parametros.get("cuenta")== null || parametros.get("cuenta") == ""){
//                return false;
//         }
//         
        System.out.println("BUSCANCO POLIZA, fecha y tipo--------------------");
        List list = processDao.getMapResult("BuscaDatosPoliza", parametros);
        if (list == null || list.size() == 0) {

            System.out.println("List null");
            setMsgError("error: este pid no tiene poliza");
            return "SIN POLIZA";
        }

        Map result = (HashMap) list.get(0);
        System.out.println("select:" + result.get("ESTATUS"));

        if (result.get("TIPO_POLIZA") != null || result.get("TIPO_POLIZA") != "") {

            if (result.get("FECHA_POLIZA") != null || result.get("FECHA_POLIZA") != "") {

                if (result.get("NUMERO_POLIZA") != null || result.get("NUMERO_POLIZA") != "") {

                    return result.get("FECHA_POLIZA").toString() + "-" + result.get("TIPO_POLIZA").toString() + "-" + result.get("NUMERO_POLIZA").toString();

                } else {

                    setMsgError("error: este pid no tiene poliza");
                    return "SIN POLIZA";

                }

            } else {

                setMsgError("error: este pid no tiene poliza");
                return "SIN POLIZA";

            }

        } else {

            setMsgError("error: este pid no tiene poliza");
            return "SIN POLIZA";

        }

    }

    public boolean validaErrorCancela(ErpNomCancela erpNomCancela) {
//         
        Map parametros = new HashMap();
//         
//         
//         
        parametros.put("compania", erpNomCancela.getCompania());
        parametros.put("pid", erpNomCancela.getPid());

//         if(parametros.get("cuenta")== null || parametros.get("cuenta") == ""){
//                return false;
//         }
//         
        System.out.println("BUSCANCO ERROR EN CANCELA--------------------");
        List list = processDao.getMapResult("BuscaErrorCancela", parametros);
        if (list == null || list.size() == 0) {

            System.out.println("List null");

            return true;
        }

        Map result = (HashMap) list.get(0);
        System.out.println("select:" + result.get("ERROR"));

        if (result.get("ERROR") == null || result.get("ERROR") == "") {

            setMsgError("error: no se encontro el mensaje de error");
            return false;

//        }
        } else {

            setMsgError("error: " + result.get("ERROR").toString());
        }
        return false;
    }

    public void setProcessDao(ProcessDao processDao) {
        this.processDao = processDao;
    }

    public void setMsgError(String msgError) {
        this.msgError = msgError;
    }

    public String getMsgError() {
        return msgError;
    }

}
