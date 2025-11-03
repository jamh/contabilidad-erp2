/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.feraz.cuentas.controller;

/**
 *
 * @author vavi
 */
/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonToken;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.feraz.contabilidad.saldos.dao.SaldosDao;
import com.feraz.cuentas.dao.CompaniaDao;
import com.feraz.cuentas.dao.CuentasDao;
import com.feraz.cuentas.dao.EstructurasDao;
import com.feraz.cuentas.model.Estructuras;
import com.feraz.cuentas.model.EstructurasId;
import com.feraz.cuentas.model.Cuentas;
import com.feraz.cuentas.model.CuentasId;
import com.feraz.cuentas.model.Compania;
import com.feraz.cuentas.model.CompaniaId;
import com.feraz.cuentas.dto.CuentasDTO;
import com.feraz.facturas.webcontrolfe.model.ErpFeComprobantes;
import com.feraz.facturas.webcontrolfe.model.ErpFeComprobantesId;
import com.feraz.polizas3.dao.DetPolizasDao;
import com.feraz.sat.cfdi.model.ResponseWSValida;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.jamh.data.process.ProcessDao;
import org.jamh.wf.json.model.ResponseQuery;
import org.jamh.wf.process.Querys;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.context.request.WebRequest;

@Controller
@RequestMapping("/CrearCuenta2")
@SessionAttributes({"compania", "usuario"})
public class ProcessCrearCuenta2 {

    private CuentasDao cuentasDao;
    private ProcessDao processDao;
    private EstructurasDao estructurasDao;
    private CompaniaDao companiaDao;
    private DetPolizasDao detPolizasDao;

    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public @ResponseBody
    ResponseQuery registro(String data, @RequestParam("tipoCompania") String tipoCompania, WebRequest webRequest, Model model) {

        Map parametros = processDao.paramToMap(webRequest.getParameterMap());

        ResponseQuery rq = new ResponseQuery();

        String compania = model.asMap().get("compania").toString();
        String usuario = model.asMap().get("usuario").toString();

        System.out.println("compania:" + compania);
        System.out.println("usuario:" + usuario);

        System.out.println("cuenta: " + parametros.get("txtCuenta").toString());

        System.out.println("Tipo de compania" + tipoCompania);

        String cuenta = parametros.get("txtCuenta").toString();
        String cuentaEstr = parametros.get("txtCuentaEstr").toString();
        String nombreCuenta = parametros.get("txtNombreCta").toString();
        String afectable = parametros.get("cboAfectable").toString();
        String cierre = parametros.get("cboCierre").toString();
        String estatus = parametros.get("cboEstatus").toString();
        String naturaleza = parametros.get("cboNaturaleza").toString();
        String tipo = parametros.get("cboTipoCta").toString();
        String mayor = parametros.get("cboMayor").toString();
        String clavePresup = parametros.get("txtClavePres").toString();
        String ctaSat = parametros.get("txtCtaSat").toString();
        String cuentaPadre = parametros.get("cboCuentaPadre").toString();
        String accion = parametros.get("txtAccion").toString();
        String idCta = parametros.get("txtID").toString();
        String saldo = parametros.get("txtSaldo").toString();
        String poliza = parametros.get("txtPoliza").toString();
        String moneda = parametros.get("cboMoneda").toString();
        String banco = parametros.get("cboBanco").toString();
        String ctaComple = parametros.get("cboCtaComplementaria").toString();
        String banderaTrans = parametros.get("txtTranspasoSaldos").toString();
        String ctaCompleTrans = parametros.get("txtCtaAcumuladoraTranSaldos").toString();
        String orden = parametros.get("txtOrdenBalanza").toString();
        String clasificacion1 = parametros.get("CLASIFICACION1").toString();
        String clasificacion2 = parametros.get("CLASIFICACION2").toString();
        String nombre2 = "";
        if (parametros.get("txtNombre2") != null) {
            nombre2 = parametros.get("txtNombre2").toString();
        }
        //LOS SYSTEM NO 
        System.out.println("------------------------------Entrando al controlador----------------------------");

        System.out.println("CUENTA PADRE" + cuentaPadre);
        Cuentas cuentas = new Cuentas();
        CuentasId id = new CuentasId();

        cuentas.setUsuario(usuario);

        try {

            nombreCuenta = nombreCuenta.replaceAll("'", " ");

            System.out.println("validando padre");
            System.out.println("banderaTrans" + banderaTrans);
            System.out.println("ctaCompleTrans" + ctaCompleTrans);

            if (ctaSat == null || ctaSat.equals("")) {

            } else {
                Map cuentaSat = new HashMap();

                cuentaSat.put("cuenta_sat", ctaSat);

                List listCtaSat = processDao.getMapResult("VerificaCtaSat", cuentaSat);

                if (listCtaSat == null || listCtaSat.isEmpty()) {

                    rq.setSuccess(false);
                    rq.setMsg("La cuenta sat no es una cuenta valida");
                    return rq;

                }
            }

            if (cuentaPadre.equals("") || cuentaPadre == null) {

                cuentaPadre = "";

            } else {
                if (cuentaPadre.equalsIgnoreCase(cuentaEstr)) {
                    rq.setSuccess(false);
                    rq.setMsg("La cuenta padre no puede ser las misma cuenta que se esta intentando modificar");
                    return rq;

                }
                System.out.println("validando padre2");
            }
            id.setEstructura(compania);
            System.out.println("validando padre3");
            int secCuenta = cuentasDao.getMaxId(id);

            System.out.println("secCuenta" + secCuenta);

            if (accion.equalsIgnoreCase("I")) {

                Cuentas cuentaAlias = cuentasDao.findCuenta(compania, cuenta);

                if (cuentaAlias != null) {

              

                    rq.setSuccess(false);
                    rq.setMsg("La cuenta ya existe");
                    return rq;

                }

                if (cuentaPadre.equalsIgnoreCase("1")) {

                    Map titulo = new HashMap();
                    String cuentaTitulo = "";
                    titulo.put("compania", compania);

                    List listTitulo = processDao.getMapResult("BuscaMaxCtaTitulo", titulo);

                    Map cuen = (HashMap) listTitulo.get(0);
                    System.out.println("CUENTA_TITULO:" + cuen.get("CUENTA_TITULO"));

                    if (cuen.get("CUENTA_TITULO").toString().equalsIgnoreCase("000001")) {

                        Map count = new HashMap();

                        count.put("compania", compania);

                        List listCount = processDao.getMapResult("BuscaNumCuentas", count);
                        Map resultCount = (HashMap) listCount.get(0);
                        int countCuenta = Integer.parseInt(resultCount.get("NUM_CUENTA").toString());

                        if (countCuenta == 0) {

                            Estructuras estructura = new Estructuras();
                            EstructurasId estructurasId = new EstructurasId();

                            estructurasId.setEstructura(compania);
                            estructura.setId(estructurasId);
                            estructura.setNombre("ESTRUCTURA DE 6 NIVELES PARA " + compania);
                            estructura.setNiveles(6);
                            estructura.setSeparador("-");
                            estructura.setFormato("XXXXXX-XXXXXX-XXXXXX-XXXXXX-XXXXXX-XXXXXX");
                            estructura.setEstatus("A");

                            EstructurasId result = estructurasDao.save(estructura);

                            Compania comEstruc = new Compania();
                            CompaniaId comEstrucId = new CompaniaId();
                            comEstrucId.setCompania(compania);
                            comEstruc.setId(comEstrucId);
                            comEstruc.setEstructura(compania);

                            boolean resultEstruc = companiaDao.updateEstructura(comEstruc);

                            cuentaTitulo = "000001";

                            id.setEstructura(compania);
                            id.setCuenta(cuentaTitulo);
                            cuentas.setId(id);
                            cuentas.setAfectable(afectable);
                            cuentas.setCierre(cierre);
                            cuentas.setClavePresp(clavePresup);
                            cuentas.setCuentaAlias(cuenta);
                            cuentas.setCuentaSat(ctaSat);
                            cuentas.setEstatus(estatus);
                            cuentas.setMayor(mayor);
                            cuentas.setNaturaleza(naturaleza);
                            cuentas.setNombre(nombreCuenta);
                            cuentas.setTipo(tipo);
                            cuentas.setIdCuentas(secCuenta);
                            cuentas.setDivisa(moneda);
                            cuentas.setBanco(banco);
                            cuentas.setCtaComplementaria(ctaComple);
                            cuentas.setIdOrdenBalanza(orden);
                            cuentas.setClasificacion1(clasificacion1);
                            cuentas.setClasificacion2(clasificacion2);
                            cuentas.setNombre2(nombre2);
                            CuentasId saveTitulo = cuentasDao.save(cuentas);

                            if (saveTitulo.getCuenta() == "" || saveTitulo.getCuenta() == null || result.getEstructura() == "" || result.getEstructura() == null || resultEstruc == false) {

                                rq.setSuccess(false);
                                rq.setMsg("Error al guardar la cuenta");

                            } else {

                                rq.setSuccess(true);
                                rq.setMsg("Cuenta Guardada");

                            }

                            return rq;

                        } else {

                            rq.setSuccess(false);
                            rq.setMsg("Error en estructuras al generar cuenta padre");

                            return rq;
                        }

                    } else {

                        Map result1 = (HashMap) listTitulo.get(0);
                        System.out.println("CUENTA_TITULO:" + result1.get("CUENTA_TITULO"));

                        cuentaTitulo = result1.get("CUENTA_TITULO").toString();

                        id.setEstructura(compania);
                        id.setCuenta(cuentaTitulo);
                        cuentas.setId(id);
                        cuentas.setAfectable(afectable);
                        cuentas.setCierre(cierre);
                        cuentas.setClavePresp(clavePresup);
                        cuentas.setCuentaAlias(cuenta);
                        cuentas.setCuentaSat(ctaSat);
                        cuentas.setEstatus(estatus);
                        cuentas.setMayor(mayor);
                        cuentas.setNaturaleza(naturaleza);
                        cuentas.setNombre(nombreCuenta);
                        cuentas.setTipo(tipo);
                        cuentas.setIdCuentas(secCuenta);
                        cuentas.setDivisa(moneda);
                        cuentas.setBanco(banco);
                        cuentas.setCtaComplementaria(ctaComple);
                        cuentas.setIdOrdenBalanza(orden);
                        cuentas.setClasificacion1(clasificacion1);
                        cuentas.setClasificacion2(clasificacion2);
                        cuentas.setNombre2(nombre2);
                        CuentasId saveTitulo = cuentasDao.save(cuentas);

                        if (saveTitulo.getCuenta() == "" || saveTitulo.getCuenta() == null) {

                            rq.setSuccess(false);
                            rq.setMsg("Error al guardar la cuenta");

                        } else {

                            rq.setSuccess(true);
                            rq.setMsg("Cuenta Guardada");

                        }

                        return rq;

                    }
                }

                //  if (cuentaPadre == "" || cuentaPadre == null){
                if (tipoCompania.equalsIgnoreCase("2")) {
                    String estructuraCuenta = cuenta.replaceAll("[0|1|2|3|4|5|6|7|8|9]", "X");

                    Map param = new HashMap();
                    param.put("compania", compania);

                    List list1 = processDao.getMapResult("BuscarEstructuraCuenta", param);

                    Map result1 = (HashMap) list1.get(0);
                    System.out.println("FORMATO:" + result1.get("FORMATO"));

                    String format = result1.get("FORMATO").toString();
                    format = format + "-";
                    int pos_ini = 1;

                    String valor_comparar = "";
                    String valor_comparar2 = "";

                    int i = 0;

                    Boolean estructuraBandera = false;

                    while (format.indexOf('-', pos_ini) > 0) {

                        int pos_fin = format.indexOf('-', pos_ini);

                        int log = pos_fin - pos_ini;

                        String valor_format = "";

                        valor_format = format.substring(pos_ini - 1, pos_fin);

                        System.out.println("valor_format" + valor_format);
                        if (pos_ini == 1) {

                            valor_comparar = valor_format;

                        } else {
                            if (pos_ini > 1) {

                                valor_comparar += "-" + valor_format;

                            }

                        }

                        if (estructuraCuenta.equalsIgnoreCase(valor_comparar)) {

                            // System.out.println("estructuraCuenta"+estructuraCuenta);
                            //System.out.println("valor_comparar"+valor_comparar);
                            estructuraBandera = true;

                            id.setEstructura(compania);
                            id.setCuenta(cuenta);
                            cuentas.setId(id);
                            cuentas.setAfectable(afectable);
                            cuentas.setCierre(cierre);
                            cuentas.setClavePresp(clavePresup);
                            cuentas.setCuentaAlias(cuenta);
                            cuentas.setCuentaSat(ctaSat);
                            cuentas.setEstatus(estatus);
                            cuentas.setMayor(mayor);
                            cuentas.setNaturaleza(naturaleza);
                            cuentas.setNombre(nombreCuenta);
                            cuentas.setTipo(tipo);
                            cuentas.setIdCuentas(secCuenta);
                            cuentas.setDivisa(moneda);
                            cuentas.setBanco(banco);
                            cuentas.setCtaComplementaria(ctaComple);
                            cuentas.setCuentaPadre(cuentaPadre);
                            cuentas.setIdOrdenBalanza(orden);
                            cuentas.setClasificacion1(clasificacion1);
                            cuentas.setClasificacion2(clasificacion2);
                            cuentas.setNombre2(nombre2);
                            CuentasId save = cuentasDao.save(cuentas);

                            int val = 0;
                            boolean detPolCta = false;
                            boolean ctaAfectable = false;

                            if (save.getCuenta() == null || save.getCuenta().equals("")  ) {

                                rq.setSuccess(false);
                                rq.setMsg("Error al guardar la cuenta");

                            } else {

                                if (banderaTrans.equalsIgnoreCase("1")) {

                                    Map mapTrans = new HashMap();

                                    Querys que = new Querys();
                                    String store = que.getSQL("TranspasoSaldos");

                                    mapTrans.put("compania", compania);
                                    mapTrans.put("cuenta_nueva", cuenta);
                                    mapTrans.put("cuenta_vieja", ctaCompleTrans);

                                    val = processDao.execute(store, mapTrans);

                                    detPolCta = detPolizasDao.updateCtaDetPolizas(compania, cuenta, ctaCompleTrans);

                                    ctaAfectable = cuentasDao.updatePorAfectable(compania, ctaCompleTrans);
                                    if (val < 0) {

                                        rq.setSuccess(true);
                                        rq.setMsg("Cuenta Guardada. Error al transapasar saldos");

                                    } else {

                                        if (detPolCta == false) {

                                            rq.setSuccess(true);
                                            rq.setMsg("Cuenta Guardada. Error al actiualizar polizas");

                                        } else {

                                            rq.setSuccess(true);
                                            rq.setMsg("Cuenta Guardada. Saldos y polizas transapasadas");

                                        }

                                    }

                                } else {

                                    rq.setSuccess(true);
                                    rq.setMsg("Cuenta Guardada");

                                }

                            }

                            break;
                        }

                        pos_ini = pos_fin + 2;
                        System.out.println("pos_ini" + pos_ini);

                        i++;

                        if (i == 100) {

                            break;
                        }

                    }

                    if (estructuraBandera == false) {

                        rq.setSuccess(false);
                        rq.setMsg("Error estructura de Cuenta no valida");

                    }

                    return rq;

                }

                String query1 = "BuscarNivelCuenta";
                String query2 = "BuscarNuevaCuenta";
                String cuentaFinal;
                Map param = new HashMap();
                param.put("compania", compania);
                param.put("cuenta_padre", cuentaPadre);

                List list1 = processDao.getMapResult(query1, param);
                // String [] array = list.get(o);
                Map result1 = (HashMap) list1.get(0);
                System.out.println("select:" + result1.get("NVL_CUENTA"));

                String nivelCuenta = result1.get("NVL_CUENTA").toString();

                if (nivelCuenta.equalsIgnoreCase("1")) {

                    cuentaFinal = cuentaPadre + "-" + "00000" + nivelCuenta;
                } else {

                    List list2 = processDao.getMapResult(query2, param);
                    // String [] array = list.get(o);
                    Map result2 = (HashMap) list2.get(0);
                    System.out.println("select:" + result2.get("CUENTA_NEW"));

                    cuentaFinal = result2.get("CUENTA_NEW").toString();

                }

                System.out.println("cuentaFinal" + cuentaFinal);
                String estructuraCuenta2 = cuentaFinal.replaceAll("[0|1|2|3|4|5|6|7|8|9]", "X");

                Map param2 = new HashMap();
                param2.put("compania", compania);

                List list2 = processDao.getMapResult("BuscarEstructuraCuenta", param2);

                Map result2 = (HashMap) list2.get(0);
                System.out.println("FORMATO:" + result2.get("FORMATO"));

                String format2 = result2.get("FORMATO").toString();
                format2 = format2 + "-";

                int pos_ini = 1;

                //String valor_comparar = "";
                String valor_comparar2 = "";

                int i = 0;

                Boolean estructuraBandera2 = false;

                while (format2.indexOf('-', pos_ini) > 0) {

                    int pos_fin = format2.indexOf('-', pos_ini);

                    int log = pos_fin - pos_ini;

                    String valor_format2 = "";

                    valor_format2 = format2.substring(pos_ini - 1, pos_fin);

                    System.out.println("valor_format" + valor_format2);
                    if (pos_ini == 1) {

                        valor_comparar2 = valor_format2;

                    } else {
                        if (pos_ini > 1) {

                            valor_comparar2 += "-" + valor_format2;
                            System.out.println("valor_comparar2" + valor_comparar2);
                        }

                    }

                    if (estructuraCuenta2.equalsIgnoreCase(valor_comparar2)) {
//           String estructuraCuenta = cuentaFinal.replaceAll("[0|1|2|3|4|5|6|7|8|9]", "X");
//           
//           int indexOf
                        //System.out.println("estructuraCuenta"+estructuraCuenta);

                        estructuraBandera2 = true;

                        id.setEstructura(compania);
                        id.setCuenta(cuentaFinal);
                        cuentas.setId(id);
                        cuentas.setAfectable(afectable);
                        cuentas.setCierre(cierre);
                        cuentas.setClavePresp(clavePresup);
                        cuentas.setCuentaAlias(cuenta);
                        cuentas.setCuentaSat(ctaSat);
                        cuentas.setEstatus(estatus);
                        cuentas.setMayor(mayor);
                        cuentas.setNaturaleza(naturaleza);
                        cuentas.setNombre(nombreCuenta);
                        cuentas.setTipo(tipo);
                        cuentas.setIdCuentas(secCuenta);
                        cuentas.setDivisa(moneda);
                        cuentas.setBanco(banco);
                        cuentas.setCtaComplementaria(ctaComple);
                        cuentas.setCuentaPadre(cuentaPadre);
                        cuentas.setIdOrdenBalanza(orden);
                        cuentas.setClasificacion1(clasificacion1);
                        cuentas.setClasificacion2(clasificacion2);
                        cuentas.setNombre2(nombre2);
                        CuentasId save = cuentasDao.save(cuentas);

                        int val = 0;
                        boolean detPolCta = false;
                        boolean ctaAfectable = false;

                        if (save.getCuenta() == null || save.getCuenta().equals("")  ) {

                            rq.setSuccess(false);
                            rq.setMsg("Error al guardar la cuenta");

                        } else {

                            if (banderaTrans.equalsIgnoreCase("1")) {

                                Map mapTrans = new HashMap();

                                Querys que = new Querys();
                                String store = que.getSQL("TranspasoSaldos");

                                mapTrans.put("compania", compania);
                                mapTrans.put("cuenta_nueva", cuentaFinal);
                                mapTrans.put("cuenta_vieja", ctaCompleTrans);

                                val = processDao.execute(store, mapTrans);

                                detPolCta = detPolizasDao.updateCtaDetPolizas(compania, cuentaFinal, ctaCompleTrans);

                                ctaAfectable = cuentasDao.updatePorAfectable(compania, ctaCompleTrans);
                                if (val < 0) {

                                    rq.setSuccess(true);
                                    rq.setMsg("Cuenta Guardada. Error al transapasar saldos");

                                } else {

                                    if (detPolCta == false) {

                                        rq.setSuccess(true);
                                        rq.setMsg("Cuenta Guardada. Error al actiualizar polizas");

                                    } else {

                                        rq.setSuccess(true);
                                        rq.setMsg("Cuenta Guardada. Saldos y polizas transapasadas");

                                    }

                                }

                            } else {

                                rq.setSuccess(true);
                                rq.setMsg("Cuenta Guardada");

                            }

                        }

                        break;
                    }

                    pos_ini = pos_fin + 2;
                    System.out.println("pos_ini" + pos_ini);

                    i++;

                    if (i == 100) {

                        break;
                    }

                }

                if (estructuraBandera2 == false) {

                    rq.setSuccess(false);
                    rq.setMsg("Error estructura de Cuenta no valida");

                }
                //////////////////////////          
                return rq;
                
                //FIN DEL INSERT DE CUENTAS 
            } else {

                if (tipoCompania.equalsIgnoreCase("2")) {

                    Cuentas cuentaAlias = cuentasDao.findCuenta(compania, cuenta);

                    System.out.println("cuentaAlias" + cuentaAlias);

                    if (cuentaAlias == null) {

                        System.out.println("saldo" + saldo);
                        System.out.println("poliza" + poliza);

                        if (saldo == null || saldo == "") {

                            if (poliza == null || poliza == "") {

                            } else {

                                rq.setSuccess(false);
                                rq.setMsg("Error la cuenta tiene saldos y polizas");
                                return rq;

                            }

                        } else {

                            Map saldoCta = new HashMap();

                            saldoCta.put("cuenta", saldo);
                            saldoCta.put("compania", compania);

                            List listSaldo = processDao.getMapResult("buscaSaldo", saldoCta);

                            //String saldoInicial = null;
                            if (listSaldo == null || listSaldo.size() == 0) {

                            } else {

                                Map saldos = (HashMap) listSaldo.get(0);
                                String saldoInicial = saldos.get("SALDO_INICIAL").toString();
                                String cargos = saldos.get("CARGOS").toString();
                                String abonos = saldos.get("ABONOS").toString();
                                String saldoFinal = saldos.get("SALDO_FINAL").toString();

                                if (saldoInicial.equalsIgnoreCase("0") && cargos.equalsIgnoreCase("0") && abonos.equalsIgnoreCase("0") && saldoFinal.equalsIgnoreCase("0")) {
                                } else {

                                    rq.setSuccess(false);
                                    rq.setMsg("Error la cuenta tiene saldos y polizas");
                                    return rq;

                                }
                            }

                        }

                    }

                    Map activarCtaPadre = new HashMap();
                    activarCtaPadre.put("compania", compania);
                    List listCtaCount = processDao.getMapResult("ActivarCuentaPadre", activarCtaPadre);
                    Map getContCta = (HashMap) listCtaCount.get(0);
                    if (Integer.parseInt(getContCta.get("TOTAL_CUENTA").toString()) > 0) {

                        id.setEstructura(compania);
                        id.setCuenta(cuenta);
                        cuentas.setId(id);
                        cuentas.setAfectable(afectable);
                        cuentas.setCierre(cierre);
                        cuentas.setClavePresp(clavePresup);
                        cuentas.setCuentaAlias(cuenta);
                        cuentas.setCuentaSat(ctaSat);
                        cuentas.setEstatus(estatus);
                        cuentas.setMayor(mayor);
                        cuentas.setNaturaleza(naturaleza);
                        cuentas.setNombre(nombreCuenta);
                        cuentas.setTipo(tipo);
                        cuentas.setIdCuentas(Integer.parseInt(idCta));
                        cuentas.setDivisa(moneda);
                        cuentas.setBanco(banco);
                        cuentas.setCtaComplementaria(ctaComple);
                        cuentas.setCuentaPadre(cuentaPadre);
                        cuentas.setIdOrdenBalanza(orden);
                        cuentas.setClasificacion1(clasificacion1);
                        cuentas.setClasificacion2(clasificacion2);
                        cuentas.setNombre2(nombre2);
                        boolean updateCta = cuentasDao.updatePorCtaAlias(cuentas);

                        if (updateCta == true) {

                            rq.setSuccess(true);
                            rq.setMsg("Actualizacion correcta");

                        } else {

                            rq.setSuccess(false);
                            rq.setMsg("Error al actualizar");

                        }

                        System.out.println("Realiza solo actualizacion de datos Compania vieja");
                        return rq;

                    } else {

                        id.setEstructura(compania);
                        id.setCuenta(cuentaEstr);
                        cuentas.setId(id);
                        cuentas.setAfectable(afectable);
                        cuentas.setCierre(cierre);
                        cuentas.setClavePresp(clavePresup);
                        cuentas.setCuentaAlias(cuenta);
                        cuentas.setCuentaSat(ctaSat);
                        cuentas.setEstatus(estatus);
                        cuentas.setMayor(mayor);
                        cuentas.setNaturaleza(naturaleza);
                        cuentas.setNombre(nombreCuenta);
                        cuentas.setTipo(tipo);
                        cuentas.setIdCuentas(Integer.parseInt(idCta));
                        cuentas.setDivisa(moneda);
                        cuentas.setBanco(banco);
                        cuentas.setCtaComplementaria(ctaComple);
                        cuentas.setCuentaPadre(cuentaPadre);
                        cuentas.setIdOrdenBalanza(orden);
                        cuentas.setClasificacion1(clasificacion1);
                        cuentas.setClasificacion2(clasificacion2);
                        cuentas.setNombre2(nombre2);
                        boolean updateCta = cuentasDao.updatePorCtaAlias(cuentas);

                        if (updateCta == true) {

                            rq.setSuccess(true);
                            rq.setMsg("Actualizacion correcta");

                        } else {

                            rq.setSuccess(false);
                            rq.setMsg("Error al actualizar");

                        }

                        System.out.println("Realiza solo actualizacion de datos");
                        return rq;
                    }

                } else {

//             Cuentas cuentaAlias = cuentasDao.findCuenta(compania, cuenta);
//           
//           if (cuentaAlias == null){
//               
//                System.out.println("en cambiando cuenta alias");
//                
//                 id.setEstructura(compania);
//                        id.setCuenta(cuentaEstr);
//                        cuentas.setId(id);
//                        cuentas.setAfectable(afectable);
//                        cuentas.setCierre(cierre);
//                        cuentas.setClavePresp(clavePresup);
//                        cuentas.setCuentaAlias(cuenta);
//                        cuentas.setCuentaSat(ctaSat);
//                        cuentas.setEstatus(estatus);
//                        cuentas.setMayor(mayor);
//                        cuentas.setNaturaleza(naturaleza);
//                        cuentas.setNombre(nombreCuenta);
//                        cuentas.setTipo(tipo);
//                        cuentas.setIdCuentas(Integer.parseInt(idCta));
//
//                        boolean updateCta = cuentasDao.updatePorCtaAlias(cuentas);
//
//                        if (updateCta == true){
//
//                            rq.setSuccess(true);
//                             rq.setMsg("Actualizacion correcta");
//
//                        }else{
//
//                            rq.setSuccess(false);
//                             rq.setMsg("Error al actualizar");
//
//                        }
//               
//           
//           }else{
//                 Map paramPadre = new HashMap();
//                 
//                 paramPadre.put("compania", compania);
//                  paramPadre.put("id", idCta);
//               
//                   List listPadre = processDao.getMapResult("BuscaCuentaPadreID", paramPadre);
//                   System.out.println("Lista Padre"+ listPadre);
//                   String ctaPaComp = "";
//                   if(listPadre == null || listPadre.size() == 0){
//                      ctaPaComp = "Sin cuenta padre";
//                   }else{
//                       
//                       Map ctaPadre = (HashMap) listPadre.get(0);
//                       ctaPaComp = ctaPadre.get("CUENTA_PADRE").toString();
//                   }
//                   
//                 if (mayor.equalsIgnoreCase("T")){
//                     
//                     ctaPaComp = cuentaPadre;
//                 
//                 }
//                 if(cuentaPadre.equalsIgnoreCase("") || cuentaPadre == null){
//                 
//                     ctaPaComp = cuentaPadre;
//                 }
                    //   if(ctaPaComp.equalsIgnoreCase(cuentaPadre)){
                    Cuentas cuentaAlias = cuentasDao.findCuenta(compania, cuenta);

                    if (cuentaAlias == null) {

                        System.out.println("saldo" + saldo);
                        System.out.println("poliza" + poliza);

                        if (saldo == null || saldo == "") {

                            if (poliza == null || poliza == "") {

                            } else {

                                rq.setSuccess(false);
                                rq.setMsg("Error la cuenta tiene saldos y polizas");
                                return rq;
                            }

                        } else {

                            Map saldoCta = new HashMap();

                            saldoCta.put("cuenta", saldo);
                            saldoCta.put("compania", compania);

                            List listSaldo = processDao.getMapResult("buscaSaldo", saldoCta);

                            //String saldoInicial = null;
                            if (listSaldo == null || listSaldo.size() == 0) {

                            } else {

                                Map saldos = (HashMap) listSaldo.get(0);
                                String saldoInicial = saldos.get("SALDO_INICIAL").toString();
                                String cargos = saldos.get("CARGOS").toString();
                                String abonos = saldos.get("ABONOS").toString();
                                String saldoFinal = saldos.get("SALDO_FINAL").toString();

                                if (saldoInicial.equalsIgnoreCase("0") && cargos.equalsIgnoreCase("0") && abonos.equalsIgnoreCase("0") && saldoFinal.equalsIgnoreCase("0")) {
                                } else {

                                    rq.setSuccess(false);
                                    rq.setMsg("Error la cuenta tiene saldos y polizas");
                                    return rq;

                                }
                            }

                        }

                    }

                    System.out.println("solo actualizando informacion123");

                    id.setEstructura(compania);
                    id.setCuenta(cuentaEstr);
                    cuentas.setId(id);
                    cuentas.setAfectable(afectable);
                    cuentas.setCierre(cierre);
                    cuentas.setClavePresp(clavePresup);
                    cuentas.setCuentaAlias(cuenta);
                    cuentas.setCuentaSat(ctaSat);
                    cuentas.setEstatus(estatus);
                    cuentas.setMayor(mayor);
                    cuentas.setNaturaleza(naturaleza);
                    cuentas.setNombre(nombreCuenta);
                    cuentas.setTipo(tipo);
                    cuentas.setIdCuentas(Integer.parseInt(idCta));
                    cuentas.setDivisa(moneda);
                    cuentas.setBanco(banco);
                    cuentas.setCtaComplementaria(ctaComple);
                    cuentas.setCuentaPadre(cuentaPadre);
                    cuentas.setIdOrdenBalanza(orden);
                    cuentas.setClasificacion1(clasificacion1);
                    cuentas.setClasificacion2(clasificacion2);
                    cuentas.setNombre2(nombre2);
                    boolean updateCta = cuentasDao.updatePorCtaAlias(cuentas);

                    if (updateCta == true) {

                        rq.setSuccess(true);
                        rq.setMsg("Actualizacion correcta");

                    } else {

                        rq.setSuccess(false);
                        rq.setMsg("Error al actualizar");

                    }

//                  }else{
//                      
//                   
//                    
//                     if(saldo == null || saldo == ""){
//                     
//                        if( poliza == null || poliza == ""){
//                        
//                        }else{
//                           
//                             rq.setSuccess(false);
//                                  rq.setMsg("Error la cuenta tiene saldos y polizas");
//                                  return rq;
//                            
//                        }
//                     
//                    
//                    }//else{
//                     
//                        Map saldoCta = new HashMap();
//                 
//                                 saldoCta.put("cuenta", saldo);
//                                 saldoCta.put("compania", compania);
//
//                                  List listSaldo = processDao.getMapResult("buscaSaldo", saldoCta);
//                                 
//                                  //String saldoInicial = null;
//                                  if(listSaldo == null || listSaldo.size() == 0){
//                                     
//                                  }else{
//
//                                      Map saldos = (HashMap) listSaldo.get(0);
//                                      String saldoInicial = saldos.get("SALDO_INICIAL").toString();
//                                      String cargos = saldos.get("CARGOS").toString();
//                                      String abonos = saldos.get("ABONOS").toString();
//                                      String saldoFinal = saldos.get("SALDO_FINAL").toString();
//                                      
//                                      if (saldoInicial.equalsIgnoreCase("0") && cargos.equalsIgnoreCase("0") && abonos.equalsIgnoreCase("0") && saldoFinal.equalsIgnoreCase("0") ){
//                                       }else{
//                                          
//                                            rq.setSuccess(false);
//                                            rq.setMsg("Error la cuenta tiene saldos y polizas");
//                                            return rq;
//                                          
//                                      }
//                                  }
//                            
//                        
//                         
//                    }
//                
//                
//                
//                  
//                      System.out.println("actualizando informacion y cta padre");
//                      
//                      //!!!!
//                      
//                            String query1 = "BuscarNivelCuenta";
//                            String query2 = "BuscarNuevaCuenta";
//                            String cuentaFinal;
//                                 Map param = new HashMap();
//                                  param.put("compania", compania);
//                                  param.put("cuenta_padre", cuentaPadre);
//
//
//                                   List list1 = processDao.getMapResult(query1, param);
//                                // String [] array = list.get(o);
//                                 Map result1 = (HashMap) list1.get(0);
//                                  System.out.println("select:" + result1.get("NVL_CUENTA"));
//
//                            String nivelCuenta = result1.get("NVL_CUENTA").toString();
//
//                           if (nivelCuenta.equalsIgnoreCase("1")){
//
//                               cuentaFinal = cuentaPadre+"-"+"00000"+nivelCuenta;
//                           } else{
//
//                               List list2 = processDao.getMapResult(query2, param);
//                                // String [] array = list.get(o);
//                                 Map result2 = (HashMap) list2.get(0);
//                                  System.out.println("select:" + result2.get("CUENTA_NEW"));
//
//                                  cuentaFinal = result2.get("CUENTA_NEW").toString();
//
//                           }
//
//
//                           System.out.println("cuentaFinal"+cuentaFinal);
//                           String estructuraCuenta2 = cuentaFinal.replaceAll("[0|1|2|3|4|5|6|7|8|9]", "X");
//
//                              Map param2 = new HashMap();
//                               param2.put("compania", compania);
//
//                                   List list2 = processDao.getMapResult("BuscarEstructuraCuenta", param2);
//
//                                 Map result2 = (HashMap) list2.get(0);
//                                  System.out.println("FORMATO:" + result2.get("FORMATO"));
//
//                                  String format2 = result2.get("FORMATO").toString();
//                                  format2 = format2 + "-";
//
//                                  int pos_ini = 1;
//
//                                  //String valor_comparar = "";
//                                  String valor_comparar2 = "";
//
//                                  int i = 0;
//
//                                  Boolean estructuraBandera2 = false;
//
//                                  while (format2.indexOf('-', pos_ini)>0){
//
//                                      int pos_fin = format2.indexOf('-', pos_ini);
//
//                                      int log = pos_fin - pos_ini;
//
//                                      String valor_format2 = "";
//
//                                      valor_format2 = format2.substring(pos_ini - 1, pos_fin);
//
//                                      System.out.println("valor_format"+valor_format2);
//                                      if (pos_ini == 1){
//
//                                          valor_comparar2 = valor_format2;
//
//                                      }else{
//                                          if (pos_ini > 1){
//
//                                              valor_comparar2 += "-"+valor_format2;
//                                               System.out.println("valor_comparar2"+valor_comparar2);
//                                          }
//
//
//                                      }
//
//
//                                      if (estructuraCuenta2.equalsIgnoreCase(valor_comparar2)){
//                //           String estructuraCuenta = cuentaFinal.replaceAll("[0|1|2|3|4|5|6|7|8|9]", "X");
//                //           
//                //           int indexOf
//                           //System.out.println("estructuraCuenta"+estructuraCuenta);
//
//                                        estructuraBandera2 = true;
//
//                                            id.setEstructura(compania);
//                                            id.setCuenta(cuentaFinal);
//                                            cuentas.setId(id);
//                                            cuentas.setAfectable(afectable);
//                                            cuentas.setCierre(cierre);
//                                            cuentas.setClavePresp(clavePresup);
//                                            cuentas.setCuentaAlias(cuenta);
//                                            cuentas.setCuentaSat(ctaSat);
//                                            cuentas.setEstatus(estatus);
//                                            cuentas.setMayor(mayor);
//                                            cuentas.setNaturaleza(naturaleza);
//                                            cuentas.setNombre(nombreCuenta);
//                                            cuentas.setTipo(tipo);
//                                            cuentas.setIdCuentas(Integer.parseInt(idCta));
//                                            cuentas.setDivisa(moneda);
//                                            cuentas.setBanco(banco);
//                                            cuentas.setCtaComplementaria(ctaComple);
//                                            cuentas.setCuentaPadre(cuentaPadre);
//                                            cuentas.setIdOrdenBalanza(orden);
//
//                                            boolean updateCtaPadre = cuentasDao.updatePorCtaAlias(cuentas);
//
//                                            if(updateCtaPadre == false){
//
//                                             rq.setSuccess(false);
//                                             rq.setMsg("Error al guardar la cuenta");
//
//                                            }else{
//
//                                             rq.setSuccess(true);
//                                             rq.setMsg("Cuenta Guardada");
//
//
//                                            }
//
//                                            break;
//                                          }
//
//                                                       pos_ini = pos_fin + 2; 
//                                                       System.out.println("pos_ini"+pos_ini);
//
//
//
//                                                       i++;
//
//                                                       if (i == 100){
//
//                                                           break;
//                                                       }
//
//
//
//
//
//
//                                                   }
//
//
//
//                                  if(estructuraBandera2 == false){
//
//                                       rq.setSuccess(false);
//                                       rq.setMsg("Error estructura de Cuenta no valida");
//
//                                  }
//
//                            return rq;
//                      
//                      
//                      //!!!!
//                  
//                    }     
                    //   }
                }
                return rq;
            }

        } catch (Exception e) {
            rq.setSuccess(false);
            rq.setMsg("Error al guardar la cuenta");
            e.printStackTrace();
            return rq;
        }

        // return rq;
    }

    @RequestMapping(value = "/cuentas/del", method = RequestMethod.POST)
    public @ResponseBody
    ResponseQuery deleteAction(String data, @RequestParam("estructura") String estructura, @RequestParam("cuenta") String cuenta, @RequestParam("idCuenta") String idCuenta, WebRequest webRequest, Model model) {

        ResponseQuery rq = new ResponseQuery();

        Cuentas cuentas = new Cuentas();
        CuentasId cuentasId = new CuentasId();

        try {

            Map saldoCta = new HashMap();

            saldoCta.put("cuenta", cuenta);
            saldoCta.put("compania", estructura);

            List listSaldo = processDao.getMapResult("buscaSaldo", saldoCta);

            //String saldoInicial = null;
            if (listSaldo == null || listSaldo.size() == 0) {

            } else {

                Map saldos = (HashMap) listSaldo.get(0);
                String saldoInicial = saldos.get("SALDO_INICIAL").toString();
                String cargos = saldos.get("CARGOS").toString();
                String abonos = saldos.get("ABONOS").toString();
                String saldoFinal = saldos.get("SALDO_FINAL").toString();

                if (saldoInicial.equalsIgnoreCase("0") && cargos.equalsIgnoreCase("0") && abonos.equalsIgnoreCase("0") && saldoFinal.equalsIgnoreCase("0")) {
                } else {

                    rq.setSuccess(false);
                    rq.setMsg("Error la cuenta tiene saldos y polizas");
                    return rq;

                }
            }
            Map detPolizaCta = new HashMap();

            detPolizaCta.put("cuenta", cuenta);
            detPolizaCta.put("compania", estructura);

            List listDetPoliza = processDao.getMapResult("buscaDetPoliza", detPolizaCta);

            //String saldoInicial = null;
            if (listDetPoliza == null || listDetPoliza.size() == 0) {

            } else {

                rq.setSuccess(false);
                rq.setMsg("Error la cuenta tiene polizas");
                return rq;

            }

            cuentasId.setEstructura(estructura);
            cuentasId.setCuenta(cuenta);
            cuentas.setId(cuentasId);
            cuentas.setIdCuentas(Integer.parseInt(idCuenta));
            boolean result = cuentasDao.delete(cuentas);
            if (result == true) {

                rq.setSuccess(true);
                rq.setData(null);
                rq.setMsg("Borrado Correctamente");

            } else {
                rq.setSuccess(false);
                rq.setData(null);
                rq.setMsg("Error al Guardar");
            }
        } catch (Exception e) {
            e.printStackTrace();
            rq.setSuccess(false);
            rq.setData(null);
            rq.setMsg("Error al Guardar");
            return rq;
        }

        return rq;
    }

    @RequestMapping(value = "/cambiaAtributosCta", method = RequestMethod.POST)
    public @ResponseBody
    ResponseQuery create(String data, @RequestParam("activa") String activa,
            WebRequest webRequest,
            Model model) {
        System.out.println("data" + data);
        ResponseQuery rq = new ResponseQuery();

        try {

            ObjectMapper mapper = new ObjectMapper();
            JsonFactory f = new JsonFactory();
            JsonParser jp = f.createJsonParser(data);

            jp.nextToken();
            int i = 1;

            int countFallas = 0;

            Cuentas cuentas = new Cuentas();
            CuentasId cuentasId = new CuentasId();

            while (jp.nextToken() == JsonToken.START_OBJECT) {
                CuentasDTO ss = mapper.readValue(jp, CuentasDTO.class);

                cuentasId.setEstructura(ss.estructura);
                cuentasId.setCuenta(ss.cuenta);
                cuentas.setId(cuentasId);

                if (activa.equalsIgnoreCase("A")) {

                    cuentas.setEstatus("A");

                }
                if (activa.equalsIgnoreCase("I")) {

                    cuentas.setEstatus("I");

                }

                boolean result = cuentasDao.updateAtributosCta(cuentas);

                if (result == false) {

                    rq.setData(null);
                    rq.setMsg("Error al cambiar atributos de la cuenta");
                    rq.setSuccess(false);

                } else {

                    rq.setData(null);
                    rq.setMsg("Los Atributos de las cuentas se cambiaron exitosamente");
                    rq.setSuccess(true);

                }

            }
        } catch (Exception e) {
            rq.setSuccess(false);
            rq.setMsg("Error al relacionar poliza");
            e.printStackTrace();
            return rq;
        }

        return rq;
    }

    @RequestMapping(value = "/transSaldosMov", method = RequestMethod.GET)
    public @ResponseBody
    ResponseQuery TranspasoSaldosMov(String data, @RequestParam("cuentaPadre") String cuentaPadre,
            WebRequest webRequest,
            Model model) {
        System.out.println("cuentaPadre" + cuentaPadre);
        ResponseQuery rq = new ResponseQuery();

        String compania = model.asMap().get("compania").toString();

        try {

            Map cuentaMap = new HashMap();
            cuentaMap.put("compania", compania);
            cuentaMap.put("cuenta", cuentaPadre);

            List listCtasHijas = processDao.getMapResult("buscaCtasHijas", cuentaMap);
            System.out.println("listCtasHijas" + listCtasHijas);
            if (listCtasHijas.isEmpty()) {
                System.out.println("cuentaPadreNOHIJA" + cuentaPadre);
                rq.setData(null);
                rq.setMsg("SIN CUENTAS HIJAS");
                rq.setSuccess(true);

            } else {
                System.out.println("cuentaPadreCONHIJA" + cuentaPadre);
                rq.setData(null);
                rq.setMsg("CON CUENTAS HIJAS");
                rq.setSuccess(false);

            }

        } catch (Exception e) {
            rq.setSuccess(false);
            rq.setMsg("Error al relacionar poliza");
            e.printStackTrace();
            return rq;
        }

        return rq;
    }

    public void setCuentasDao(CuentasDao cuentasDao) {
        this.cuentasDao = cuentasDao;
    }

    public void setProcessDao(ProcessDao processDao) {
        this.processDao = processDao;
    }

    private String parseInteger(String toString) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public void setEstructurasDao(EstructurasDao estructurasDao) {
        this.estructurasDao = estructurasDao;
    }

    public void setCompaniaDao(CompaniaDao companiaDao) {
        this.companiaDao = companiaDao;
    }

    public void setDetPolizasDao(DetPolizasDao detPolizasDao) {
        this.detPolizasDao = detPolizasDao;
    }

}
