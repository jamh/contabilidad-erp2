package com.feraz.contabilidad.ws;

import com.feraz.contabilidad.saldos.dao.SaldosDao;
import com.feraz.contabilidad.sat.transacciones.dao.ErpSatTransaccionDao;
import com.feraz.cuentas.dao.CuentasDao;
import com.feraz.polizas3.dao.DetPolizasDao;
import com.feraz.polizas3.dao.PolizasDao;
import com.feraz.polizas3.validation.DetalleValidation;
import com.feraz.polizas3.validation.MaestroValidation;
import java.math.BigDecimal;
import javax.jws.WebService;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import org.jamh.data.process.ProcessDao;
import com.feraz.polizas3.model.Polizas;
import com.feraz.polizas3.model.PolizasId;
import com.feraz.polizas3.model.DetPolizas;
import com.feraz.polizas3.model.DetPolizasId;
import com.feraz.contabilidad.sat.transacciones.model.ErpSatTransaccion;
import com.feraz.contabilidad.sat.transacciones.model.ErpSatTransaccionId;
import com.feraz.contabilidad.saldos.model.Saldos;
import com.feraz.contabilidad.saldos.model.SaldosId;
import com.feraz.cuentas.model.Cuentas;
import com.feraz.cuentas.model.CuentasId;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.apache.log4j.Logger;

/**
 *
 * @author Ing. JAMH
 */
@WebService(serviceName = "WSContabilidad")
public class WSContabilidad {

    private static final Logger log = Logger.getLogger(WSContabilidad.class);

    private DetPolizasDao detPolizasDao;

    private DetalleValidation detalleValidation;

    private PolizasDao polizasDao;

    private ProcessDao processDao;

    private MaestroValidation maestroValidation;

    private CuentasDao cuentasDao;

    private ErpSatTransaccionDao erpSatTransaccionDao;

    private SaldosDao saldosDao;

    @WebMethod(operationName = "polizas")
    public String polizas(
            @WebParam(name = "user") String user,
            @WebParam(name = "password") String password,
            @WebParam(name = "compania") String compania,
            @WebParam(name = "pid") String pid,
            @WebParam(name = "tipoPoliza") String tipoPoliza,
            @WebParam(name = "fecha") String fecha,
            @WebParam(name = "numero") String numero,
            @WebParam(name = "nombre") String nombre,
            @WebParam(name = "divisa") String divisa,
            @WebParam(name = "paridad") String paridad,
            @WebParam(name = "fuente") String fuente,
            @WebParam(name = "referencia") String referencia,
            @WebParam(name = "cargosBase") BigDecimal cargosBase,
            @WebParam(name = "abonosBase") BigDecimal abonosBase,
            @WebParam(name = "cargos") BigDecimal cargos,
            @WebParam(name = "abonos") BigDecimal abonos,
            @WebParam(name = "estatus") String estatus,
            @WebParam(name = "usuario") String usuario,
            @WebParam(name = "fechaCap") String fechaCap,
            @WebParam(name = "moduloOrig") String moduloOrig,
            @WebParam(name = "reviso") String reviso,
            @WebParam(name = "autorizo") String autorizo,
            @WebParam(name = "cancelo") String cancelo,
            @WebParam(name = "tipoSolicitud") String tipoSolicitud,
            @WebParam(name = "numOrden") String numOrden,
            @WebParam(name = "numTramite") String numTramite) throws ParseException {

        Polizas polizas = new Polizas();
        PolizasId polizaId = new PolizasId();
        log.debug("Entrando al web service de Polizas");
        log.debug("compania: " + compania);
        log.debug("tipoPoliza: " + tipoPoliza);
        log.debug("fecha: " + fecha);
        log.debug("numero: " + numero);
        log.debug("fuente: " + fuente);
        //log.debug(System.currentTimeMillis());
        
        if (!user.equalsIgnoreCase("convtex") || !password.equalsIgnoreCase("drY2473")){
            
            return msgResp(pid, "0", "Usuario o Password incorrectos");
        
        }
        
        SimpleDateFormat formatoDelTexto = new SimpleDateFormat("dd/MM/yyyy");
        
        
        
        
        Map polizasMap = new HashMap();

        polizasMap.put("compania", compania);
        polizasMap.put("modulo", fuente.substring(0, 2));
         polizasMap.put("transaccion", fuente.substring(2, 4));

        List listTipo = processDao.getMapResult("BuscaWSTipoPoliza", polizasMap);
        
        if (listTipo == null){
            
              log.debug("Error en tipo de poliza "+ fuente);
            return msgResp(pid, "0", "Error: El tipo de poliza y los conceptos en la fuente no son iguales");
        }

        Map tPoliza = (HashMap) listTipo.get(0);
        //log.debug(tPoliza.get("TIPO_POLIZA"));
        log.debug("tipoPolizaFeraz: " + tPoliza.get("TIPO_POLIZA"));
        
        if (!tipoPoliza.equalsIgnoreCase(tPoliza.get("TIPO_POLIZA").toString())){
            
            log.debug("Error en tipo de poliza "+tPoliza.get("TIPO_POLIZA").toString());
            return msgResp(pid, "0", "Error: El tipo de poliza y los conceptos en la fuente no son iguales");

        
        }

        polizaId.setCompania(compania);
        polizaId.setFecha(formatoDelTexto.parse(fecha));
        polizaId.setNumero(numero);
        polizaId.setTipoPoliza(tipoPoliza);
        polizas.setId(polizaId);
        polizas.setNombre(nombre);
        polizas.setDivisa(divisa);
        polizas.setDivisa(divisa);
        if(paridad != null){
        polizas.setParidad(new BigDecimal(paridad));
        }
        polizas.setFuente(fuente);
        polizas.setReferencia(referencia);
        polizas.setCargosBase(cargosBase);
        polizas.setAbonosBase(abonosBase);
        polizas.setCargos(cargos);
        polizas.setAbonos(abonos);
        if(estatus != null){
        polizas.setEstatus(new BigDecimal(estatus));
        }
        polizas.setUsuario(usuario);
        if (fechaCap != null){
        polizas.setFechaCap(formatoDelTexto.parse(fechaCap));
        }
        polizas.setModuloOrig(moduloOrig);
        polizas.setReviso(reviso);
        polizas.setAutorizo(autorizo);
        polizas.setTipoSolicitud(tipoSolicitud);
        polizas.setNumOrden(numOrden);
        polizas.setNumTramite(numTramite);

        boolean periodo = maestroValidation.validaPeriodo(polizas);

        if (periodo == false) {

            return msgResp(pid, "0", maestroValidation.getMsgError());

        }

        PolizasId result = polizasDao.save2(polizas);

        if (result == null) {
             log.debug("Error al guardar Poliza");
            return msgResp(pid, "0", "Error al generar la poliza");

        } else {
             log.debug("Poliza Guardada");
            return msgResp(pid, "1", "Poliza Guardada Correctamente");

        }

    }

    @WebMethod(operationName = "detallePolizas")
    public String detallePolizas(
            @WebParam(name = "user") String user,
            @WebParam(name = "password") String password,
            @WebParam(name = "compania") String compania,
            @WebParam(name = "pid") String pid,
            @WebParam(name = "tipoPoliza") String tipoPoliza,
            @WebParam(name = "fechaPoliza") String fechaPoliza,
            @WebParam(name = "numero") String numero,
            @WebParam(name = "sec") String sec,
            @WebParam(name = "cuenta") String cuenta,
            @WebParam(name = "cCostos") String cCostos,
            @WebParam(name = "descripcion") String descripcion,
            @WebParam(name = "referencia") String referencia,
            @WebParam(name = "cargosBase") BigDecimal cargosBase,
            @WebParam(name = "abonosBase") BigDecimal abonosBase,
            @WebParam(name = "cargos") BigDecimal cargos,
            @WebParam(name = "abonos") BigDecimal abonos,
            @WebParam(name = "rfc") String rfc,
            @WebParam(name = "indicador") String indicador,
            @WebParam(name = "fechaCap") String fechaCap,
            @WebParam(name = "cheque") String cheque,
            @WebParam(name = "estatus") String estatus,
            @WebParam(name = "hora") String hora,
            @WebParam(name = "ctoTrabajo") String ctoTrabajo,
            @WebParam(name = "referencia2") String referencia2,
            @WebParam(name = "fechaDocumento") String fechaDocumento,
            @WebParam(name = "tcPago") String tcPago,
            @WebParam(name = "tcProv") String tcProv,
            @WebParam(name = "divisa") String divisa,
            @WebParam(name = "numCta") String numCta,
            @WebParam(name = "desCta") String desCta,
            @WebParam(name = "tipo") String tipo,
            @WebParam(name = "uuidCfdi") String uuidCfdi,
            @WebParam(name = "cfdCbbSerie") String cfdCbbSerie,
            @WebParam(name = "cfdCbbNumfol") String cfdCbbNumfol,
            @WebParam(name = "numFactExt") String numFactExt,
            @WebParam(name = "taxId") String taxId,
            @WebParam(name = "num") String num,
            @WebParam(name = "banEmisNal") String banEmisNal,
            @WebParam(name = "banEmisExt") String banEmisExt,
            @WebParam(name = "fecha") String fecha,
            @WebParam(name = "benef") String benef,
            @WebParam(name = "ctaOri") String ctaOri,
            @WebParam(name = "bancoOriNal") String bancoOriNal,
            @WebParam(name = "bancoOriExt") String bancoOriExt,
            @WebParam(name = "ctaDest") String ctaDest,
            @WebParam(name = "bancoDestNal") String bancoDestNal,
            @WebParam(name = "bancoDestExt") String bancoDestExt,
            @WebParam(name = "metPagoPol") String metPagoPol,
            @WebParam(name = "montoTotal") String montoTotal,
            @WebParam(name = "moneda") String moneda,
            @WebParam(name = "tipCamb") String tipCamb
    ) throws ParseException {

        SimpleDateFormat formatoDelTexto = new SimpleDateFormat("dd/MM/yyyy");
        
        log.debug("Entrando al web service de Detalle de Polizas");
        log.debug("compania: "+ compania);
        log.debug("tipoPoliza: "+ tipoPoliza);
        log.debug("fechaPoliza: "+ fechaPoliza);
        log.debug("numero: "+ numero);
        log.debug("sec: "+ sec);
        log.debug("cuenta: "+ cuenta);
        log.debug("cCostos: "+ cCostos);
        //log.debug(System.currentTimeMillis());
        
        if (!user.equalsIgnoreCase("convtex") || !password.equalsIgnoreCase("drY2473")){
            
            return msgRespDetalle(pid, "0", "Usuario o Password incorrectos");
        
        }
      
        try{

        Map parametros = new HashMap();
        parametros.put("cuenta", cuenta);
        parametros.put("compania", compania);
        parametros.put("cto_cto", cCostos);

        List list = processDao.getMapResult("BuscaCuentaNormalWS", parametros);

        if (list == null || list.size() == 0) {

            return msgRespDetalle(pid, "0", "error: La cuenta no es correcta");

        }

        Map polizasMap = new HashMap();

        polizasMap.put("compania", compania);
        polizasMap.put("cuenta", cuenta);
        polizasMap.put("cto_cto", cCostos);

        List listCuenta = processDao.getMapResult("BuscaCuentaN", polizasMap);

        Map cuen = (HashMap) listCuenta.get(0);
        log.debug("Cuenta de Contabilidad ERP:" + cuen.get("CUENTA"));
        
        // Map polizasTipoMap = new HashMap();

        //polizasTipoMap.put("compania", compania);
        //polizasTipoMap.put("modulo", tipoPoliza.substring(0, 2));
         //polizasTipoMap.put("transaccion", tipoPoliza.substring(2, 4));

       // List listTipo = processDao.getMapResult("BuscaWSTipoPoliza", polizasTipoMap);

       // Map tPoliza = (HashMap) listTipo.get(0);
        //log.debug(tPoliza.get("TIPO_POLIZA"));
        //log.debug("tipoPolizaFeraz: " + tPoliza.get("TIPO_POLIZA"));

        DetPolizas detPolizas = new DetPolizas();
        DetPolizasId detPolizaId = new DetPolizasId();

        ErpSatTransaccion erpSatTransaccion = new ErpSatTransaccion();
        ErpSatTransaccionId erpSatTransaccionId = new ErpSatTransaccionId();

        log.debug("insertando det polizas...");
        detPolizaId.setCompania(compania);
        detPolizaId.setFecha(formatoDelTexto.parse(fechaPoliza));
        detPolizaId.setNumero(numero);
        detPolizaId.setSec(new BigDecimal(sec));
        detPolizaId.setTipoPoliza(tipoPoliza);
        detPolizas.setId(detPolizaId);
        detPolizas.setCuenta(cuen.get("CUENTA").toString());
        detPolizas.setcCostos(cCostos);
        detPolizas.setDescripcion(descripcion);
        detPolizas.setReferencia(referencia);
        detPolizas.setCargosBase(cargosBase);
        detPolizas.setAbonosBase(abonosBase);
        detPolizas.setCargos(cargos);
        detPolizas.setAbonos(abonos);
       log.debug("1er number...");
        detPolizas.setRfc(rfc);
        detPolizas.setIndicador(indicador);
        if(!fechaCap.equalsIgnoreCase("") ){
        detPolizas.setFechaCap(formatoDelTexto.parse(fechaCap));
        }
        detPolizas.setCheque(cheque);
        detPolizas.setEstatus(estatus);
        detPolizas.setHora(hora);
        detPolizas.setCtoTrabajo(ctoTrabajo);
        detPolizas.setReferencia2(referencia2);
        if(!fechaDocumento.equalsIgnoreCase("") ){
        detPolizas.setFechaDocumento(formatoDelTexto.parse(fechaDocumento));
        }
        detPolizas.setTcPago(tcPago);
        detPolizas.setTcProv(tcProv);
        detPolizas.setDivisa(divisa);
        
        log.debug("Insertando Transaccion en el detalle de polizas");
        erpSatTransaccionId.setCompania(compania);
        int idMax = erpSatTransaccionDao.getMaxId(erpSatTransaccionId);
        erpSatTransaccionId.setId(new BigDecimal(idMax));
        log.debug("compania: "+compania);
        log.debug("id Transaccion: "+idMax);
        erpSatTransaccion.setId(erpSatTransaccionId);
        erpSatTransaccion.setNumCta(numCta);
        erpSatTransaccion.setDesCta(desCta);
        erpSatTransaccion.setTipo(tipo);
        erpSatTransaccion.setUuidCfdi(uuidCfdi);
        erpSatTransaccion.setCfdCbbSerie(cfdCbbSerie);
        erpSatTransaccion.setCfdCbbNnumFol(cfdCbbNumfol);
        erpSatTransaccion.setNumFactExt(numFactExt);
        erpSatTransaccion.setTaxId(taxId);
        erpSatTransaccion.setNum(num);
        erpSatTransaccion.setBanEmisNal(banEmisNal);
        erpSatTransaccion.setBanEmisExt(banEmisExt);
        erpSatTransaccion.setFecha(formatoDelTexto.parse(fecha));
        erpSatTransaccion.setBenef(benef);
        erpSatTransaccion.setCtaOri(ctaOri);
        erpSatTransaccion.setBancoOriNal(bancoOriNal);
        erpSatTransaccion.setBancoOriExt(bancoOriExt);
        erpSatTransaccion.setCtaDest(ctaDest);
        erpSatTransaccion.setBancoDestNal(bancoDestNal);
        erpSatTransaccion.setBancoDestExt(bancoDestExt);
        erpSatTransaccion.setMetPagoPol(metPagoPol);
       log.debug("montoTotal2"+montoTotal);
        if (!montoTotal.equalsIgnoreCase("") ){
        erpSatTransaccion.setMontoTotal(new BigDecimal(montoTotal));
        }
       log.debug("2do number...");
        erpSatTransaccion.setMoneda(moneda);
        if (!tipCamb.equalsIgnoreCase("") ){
        erpSatTransaccion.setTipCamb(new BigDecimal(tipCamb));
        }
       log.debug("3er number...");

        DetPolizasId result = detPolizasDao.save(detPolizas);

        if (result == null) {
            
            log.debug("Error al guardar el detalle de Poliza");
            return msgRespDetalle(pid, "0", "Error al guardar el detalle");

        } else {

            ErpSatTransaccionId result2 = erpSatTransaccionDao.save(erpSatTransaccion);
            if (result2 == null) {
                log.debug("Error ak guardar la transaccion del detalle de polizas");
                return msgRespDetalle(pid, "0", "Error al guardar la transaccion SAT");
            } else {
                
                log.debug("Detalle de Polizas Guardado");

                return msgRespDetalle(pid, "1", "Detalle Guardado Correctamente");
            }
        }
          } catch (Exception e) {
          
            log.error("Error: "+ e.getMessage());
            e.printStackTrace();
            return msgRespDetalle(pid, "0", "Error: "+ e.getMessage());
            
        
      }
    }

    @WebMethod(operationName = "saldos")
    public String saldos(
            @WebParam(name = "user") String user,
            @WebParam(name = "password") String password,
            @WebParam(name = "compania") String compania,
            @WebParam(name = "pid") String pid,
            @WebParam(name = "tipo") String tipo,
            @WebParam(name = "calendario") String calendario,
            @WebParam(name = "periodo") String periodo,
            @WebParam(name = "cuenta") String cuenta,
            @WebParam(name = "cCostos") String cCostos,
            @WebParam(name = "saldoInicial") String saldoInicial,
            @WebParam(name = "cargos") BigDecimal cargos,
            @WebParam(name = "abonos") BigDecimal abonos,
            @WebParam(name = "saldoFinal") String saldoFinal
    ) {
        
        log.debug("Entrando al web service de SALDOS");
        log.debug("cuenta:" + cuenta);
        log.debug("compania:" + compania);
        log.debug("cto_cto:" + cCostos);
       // log.debug(System.currentTimeMillis());
        
        
         if (!user.equalsIgnoreCase("convtex") || !password.equalsIgnoreCase("drY2473")){
            
            return msgRespSaldos(pid, "0", "Usuario o Password incorrectos");
        
        }
      
     try{
        
        Saldos saldos = new Saldos();
        SaldosId saldosId = new SaldosId();
        Map parametros = new HashMap();
        parametros.put("cuenta", cuenta);
        parametros.put("compania", compania);
        parametros.put("cto_cto", cCostos);
       
////            log.debug(processDao==null);      
////            log.debug(polizasDao==null);   
////            log.debug(cuentasDao==null);   
////            log.debug(saldosDao==null);   

        List list = processDao.getMapResult("BuscaCuentaNormalWS", parametros);

        if (list == null || list.size() == 0) {

            return msgRespSaldos(pid, "0", "error: La cuenta no es correcta");

        }

        Map polizasMap = new HashMap();

        polizasMap.put("compania", compania);
        polizasMap.put("cuenta", cuenta);
        polizasMap.put("cto_cto", cCostos);

        List listCuenta = processDao.getMapResult("BuscaCuentaN", polizasMap);

        Map cuen = (HashMap) listCuenta.get(0);
        log.debug("Cuenta de contabilidad ERP:" + cuen.get("CUENTA"));

        saldosId.setCompania(compania);
        saldosId.setCuenta(cuen.get("CUENTA").toString());
        saldosId.setPeriodo(Integer.parseInt(periodo));
        saldosId.setTipo(tipo);
        saldosId.setcCostos(cCostos);
        saldosId.setCalendario(Integer.parseInt(calendario));
        saldos.setId(saldosId);
        saldos.setSaldoFinal(new BigDecimal(saldoFinal));
        saldos.setSaldoInicial(new BigDecimal(saldoInicial));

        saldos.setAbonos(abonos);
        saldos.setCargos(cargos);

        SaldosId result = saldosDao.save(saldos);

        if (result == null) {
             log.error("Error al guardar saldos");

            return msgRespSaldos(pid, "0", "Error al guardar Saldos");

        } else {
               log.debug("Saldos Guardados");

            return msgRespSaldos(pid, "1", "Se guardo el saldo correctamente");
        }
        
      } catch (Exception e) {
          
            log.error("Error: "+ e.getMessage());
            return msgRespSaldos(pid, "0", "Error: "+ e.getCause().getMessage());
            
        
      }
     
    }

    @WebMethod(operationName = "cuentas")
    public String cuentas(
            @WebParam(name = "user") String user,
            @WebParam(name = "password") String password,
            @WebParam(name = "pid") String pid,
            @WebParam(name = "estructura") String estructura,
            @WebParam(name = "cuenta") String cuenta,
            @WebParam(name = "nombre") String nombre,
            @WebParam(name = "afectable") String afectable,
            @WebParam(name = "cierre") String cierre,
            @WebParam(name = "estatus") String estatus,
            @WebParam(name = "naturaleza") String naturaleza,
            @WebParam(name = "clavePresup") String clavePresup,
            @WebParam(name = "tipo") String tipo,
            @WebParam(name = "mayor") String mayor,
            @WebParam(name = "ctaComplementaria") String ctaComplementaria,
            @WebParam(name = "divisa") String divisa,
            @WebParam(name = "cuentaSat") String cuentaSat,
            @WebParam(name = "cuentaAlias") String cuentaAlias,
            @WebParam(name = "id") String id,
            @WebParam(name = "banco") String banco
    ) {

        Cuentas cuentas = new Cuentas();
        CuentasId cuentasId = new CuentasId();
        
        log.debug("Entrando al web service de Cuentas");
        log.debug("compania: "+estructura);
        log.debug("cuenta: "+ cuenta);
        log.debug("cuentaAlias"+ cuentaAlias);
       // log.debug(System.currentTimeMillis());
        
        if (!user.equalsIgnoreCase("convtex") || !password.equalsIgnoreCase("drY2473")){
            
            return msgRespCuentas(pid, "0", "Usuario o Password incorrectos");
        
        }

        cuentasId.setEstructura(estructura);
        cuentasId.setCuenta(cuenta);
        cuentas.setId(cuentasId);
        cuentas.setNombre(nombre);
        cuentas.setAfectable(afectable);
        cuentas.setCierre(cierre);
        cuentas.setEstatus(estatus);
        cuentas.setNaturaleza(naturaleza);
        cuentas.setClavePresp(clavePresup);
        cuentas.setTipo(tipo);
        cuentas.setMayor(mayor);
        cuentas.setCtaComplementaria(ctaComplementaria);
        cuentas.setDivisa(divisa);
        cuentas.setCuentaSat(cuentaSat);
        cuentas.setCuentaAlias(cuentaAlias);
        cuentas.setIdCuentas(Integer.parseInt(id));
        cuentas.setBanco(banco);

        CuentasId result = cuentasDao.save(cuentas);
        if (result == null) {
            log.debug("Error al guardar la cuenta");
            return msgRespCuentas(pid, "0", "Error al guardar la cuenta");

        } else {
            
            log.debug("Cuenta guardada correctamente");

            return msgRespCuentas(pid, "1", "Cuenta Guardada Correctamente");

        }
    }

    @WebMethod(exclude = true)
    public String msgRespSaldos(String pid, String result, String msg) {
        
        if (result.equals("0")){
            log.debug("pid:"+pid+",msg:"+msg);
        }
        return "<?xml version=\"1.0\" encoding=\"UTF-8\"?>"
                + "<response>\n"
                + "   <pid>" + pid + "</pid>\n"
                + "  <result>" + result + "</result>\n"
                + "  <mensaje>" + msg + "</mensaje>\n"
                + "</response>\n";
    }

    @WebMethod(exclude = true)
    public String msgRespCuentas(String pid, String result, String msg) {
        if (result.equals("0")){
            log.debug("pid:"+pid+",msg:"+msg);
        }
        return "<?xml version=\"1.0\" encoding=\"UTF-8\"?>"
                + "<response>\n"
                + "   <pid>" + pid + "</pid>\n"
                + "  <result>" + result + "</result>\n"
                + "  <mensaje>" + msg + "</mensaje>\n"
                + "</response>\n";
    }

    @WebMethod(exclude = true)
    public String msgResp(String pid, String result, String msg) {
        if (result.equals("0")){
            log.debug("pid:"+pid+",msg:"+msg);
        }
        return "<?xml version=\"1.0\" encoding=\"UTF-8\"?>"
                + "<response>\n"
                + "   <pid>" + pid + "</pid>\n"
                + "  <result>" + result + "</result>\n"
                + "  <mensaje>" + msg + "</mensaje>\n"
                + "</response>\n";
    }

    @WebMethod(exclude = true)
    public String msgRespDetalle(String pid, String result, String msg) {
        if (result.equals("0")){
            log.debug("pid:"+pid+",msg:"+msg);
        }
        return "<?xml version=\"1.0\" encoding=\"UTF-8\"?>"
                + "<response>\n"
                + "   <pid>" + pid + "</pid>\n"
                + "  <result>" + result + "</result>\n"
                + "  <mensaje>" + msg + "</mensaje>\n"
                + "</response>\n";
    }

    @WebMethod(exclude = true)
    public void setDetPolizasDao(DetPolizasDao detPolizasDao) {
        this.detPolizasDao = detPolizasDao;
    }

    @WebMethod(exclude = true)
    public void setDetalleValidation(DetalleValidation detalleValidation) {
        this.detalleValidation = detalleValidation;
    }

    @WebMethod(exclude = true)
    public void setPolizasDao(PolizasDao polizasDao) {
        this.polizasDao = polizasDao;
    }

    @WebMethod(exclude = true)
    public void setProcessDao(ProcessDao processDao) {
        this.processDao = processDao;
    }

    @WebMethod(exclude = true)
    public void setMaestroValidation(MaestroValidation maestroValidation) {
        this.maestroValidation = maestroValidation;
    }

    @WebMethod(exclude = true)
    public void setCuentasDao(CuentasDao cuentasDao) {
        this.cuentasDao = cuentasDao;
    }

    @WebMethod(exclude = true)
    public void setErpSatTransaccionDao(ErpSatTransaccionDao erpSatTransaccionDao) {
        this.erpSatTransaccionDao = erpSatTransaccionDao;
    }

    @WebMethod(exclude = true)
    public void setSaldosDao(SaldosDao saldosDao) {
        this.saldosDao = saldosDao;
    }

}
