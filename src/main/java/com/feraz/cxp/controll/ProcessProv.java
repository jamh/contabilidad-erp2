/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.feraz.cxp.controll;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.feraz.cxp.dao.ErpCClientesDao;
import com.feraz.cxp.dao.ErpCClientesLog2Dao;
import com.feraz.cxp.dao.ErpFamiliaProveedorDao;
import com.feraz.cxp.dao.ErpProvDireccionDao;
import com.feraz.cxp.dao.ErpProvProductosDao;
import com.feraz.cxp.dto.ProductoDTO;
import com.feraz.cxp.dto.ProvDTO;
import com.feraz.cxp.dto.ServiciosDTO;
import com.feraz.cxp.model.ErpCClientes;
import com.feraz.cxp.model.ErpCClientesId;
import com.feraz.cxp.model.ErpCClientesLog2;
import com.feraz.cxp.model.ErpCClientesLog2Id;
import com.feraz.cxp.model.ErpFamiliaProveedor;
import com.feraz.cxp.model.ErpFamiliaProveedorId;
import com.feraz.cxp.model.ErpProvDireccion;
import com.feraz.cxp.model.ErpProvDireccionId;
import com.feraz.cxp.model.ErpProvProductos;
import com.feraz.cxp.model.ErpProvProductosId;
import com.feraz.facturas.webcontrolfe.model.FileUploadBean;
import com.feraz.polizas3.model.ExtJSFormResult;
import com.feraz.portal.mail.MailCadena;
import com.feraz.portal.mail.MailVerificacion;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import org.jamh.data.process.ProcessDao;
import org.jamh.wf.json.model.ResponseQuery;
import org.jamh.wf.process.Querys;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.multipart.MultipartFile;

/**
 *
 * @author 55555
 */
@Controller
@RequestMapping("/CrearProveedor")
@SessionAttributes({"compania", "usuario"})
public class ProcessProv {
    private final Logger log = LoggerFactory.getLogger(ProcessProv.class);
    private ProcessDao processDao;
    private ErpCClientesDao erpCClientesDao;
    private ErpProvDireccionDao erpProvDireccionDao;
    private ErpProvProductosDao erpProvProductosDao;
    private ErpFamiliaProveedorDao erpFamiliaProveedorDao;
    private ErpCClientesLog2Dao erpCClientesLog2Dao;

    private MailVerificacion mailVerificacion;

    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public @ResponseBody
    ResponseQuery saveProv(@RequestParam("ID_CLIENTE") String idCliente,
            @RequestParam("NOMBRE") String nombre,
            @RequestParam("RFC") String rfc,
            @RequestParam("RAZON_SOCIAL") String razonSocial,
            @RequestParam("DIAS") String dias,
            @RequestParam("CORREO") String correo,
            @RequestParam("BANCO_PAGO") String bancoPago,
            @RequestParam("TELEFONO") String telefono,
            @RequestParam("CALLE") String calle,
            @RequestParam("COLONIA") String colonia,
            @RequestParam("PAIS") String pais,
            @RequestParam("NO_INTERIOR") String noInterior,
            @RequestParam("NO_EXTERIOR") String noExterior,
            @RequestParam("ESTADO") String estado,
            @RequestParam("DELEGACION") String delegacion,
            @RequestParam("mailingPostalCode") String cp,
            @RequestParam("CUENTA_CONTABLE") String cuenta,
            @RequestParam("CUENTA_IVA") String cuentaIva,
            @RequestParam("BANCO") String banco,
            @RequestParam("CUENTA_BANCARIA") String cuentaBanco,
            @RequestParam("CLABE") String clabe,
            @RequestParam("TIPO_POLIZA") String tipoPoliza,
            @RequestParam("cboPersona") String persona,
            @RequestParam("cboMonedaProv") String moneda,
            @RequestParam("proceso") String proceso,
            @RequestParam("procesoDir") String procesoDir,
            @RequestParam("PASSWORD") String password,
            @RequestParam("cbotTercero") String tercero,
            @RequestParam("cbotOperacion") String operacion,
            @RequestParam("cboConceptoDefault") String concepto,
            @RequestParam("banderaViatic") String banderaViatico,
            @RequestParam("addBanderaCie") String banderaCie,
            @RequestParam("PAGO_CIE") String pagoCie,
            @RequestParam("REFERENCIA_CIE") String referenciaCie,
            @RequestParam("cbotOperacionDiot") String operacionDiot,
            @RequestParam("AUXILIAR") String auxiliar,
            @RequestParam("CUENTA_GASTO") String cuentaGasto,
            @RequestParam("CUENTA_IVA_PAGO") String cuentaIvaPago,
            @RequestParam("CUENTA_COMPLEMENTARIA") String cuentaComplementaria,
            @RequestParam("cboctoDefault") String ctoDefault,
            @RequestParam("TIPO_CUENTA") String tipoCuenta,
            @RequestParam("IBAN") String iban,
            @RequestParam("SWIFT") String swift,
            @RequestParam("CUENTA_VALIDA") String cuentaValida,
            @RequestParam("ACTIVACION") String activacion,
            @RequestParam("CLASIFICACION1") String clasificacion1,
            @RequestParam("CLASIFICACION2") String clasificacion2,
            @RequestParam("CORREO2") String correo2,
            @RequestParam("BANCO_DOLARES") String bancoDolares,
            @RequestParam("NUM_CUENTA_DOLARES") String numCuentaDolares,
            @RequestParam("CUENTA_CLABE_DOLARES") String clabeDolares,
            @RequestParam("CUENTA_VALIDA_DOLARES") String cuentaValidaDolares,
            @RequestParam("BANCO_EXTRANJERO") String bancoExtranjero,
            @RequestParam("DIR_BANCO_EXTRANJERO") String dirBancoExtranjero,
            @RequestParam("NUM_CTA_EXTRANJERA") String numCtaExtranjera,
            @RequestParam("DIR_BENEFICIARIO_EXT") String dirBeneficiarioExt,
            @RequestParam("DIRECCION") String direccion,
            @RequestParam("ID_FAMILIA") String idFamilia,
            @RequestParam("CTA_CONTABLE_DOLARES") String ctaContableDolares,
            @RequestParam("CTA_IVA_DOLARES") String ctaIvaDolares,
            @RequestParam("CTA_IVA_COMPLEMENTARIA") String ctaIvaComplementaria,
            @RequestParam("CUENTA_POR_COMPROBAR") String ctaPorComprobar,
            @RequestParam("CUENTA_POR_COMPR_DLLS") String ctaPorComprobarDlls,
            @RequestParam("CUENTA_POR_COMP_COMPL") String ctaPorComprobarCompl,
            @RequestParam("CUENTA_SIN_CFDI") String cuentaSinCfdi,
            @RequestParam("CUENTA_COMPL_SIN_CFDI") String cuentaComplSinCfdi,
            @RequestParam("CUENTA_TARJETA") String cuentaTarjeta,
            @RequestParam("NOMBRE_1") String nombre1,
            @RequestParam("APELLIDO_PATERNO") String apellidoPaterno,
            @RequestParam("APELLIDO_MATERNO") String apellidoMaterno,
            @RequestParam("SEGMENTACION") String segmentacion,
            @RequestParam("CTA_IVA_PAGO_DLLS") String ctaIvaPagoDlls,
            @RequestParam("CTA_IVA_COMPLE_PAGO_DLLS_COMPL") String ctaIvaComplPagDlls,
            @RequestParam("CUENTA_SIN_CFDI_X_MONEDA") String cuentaSinCfdiXMoneda,
            @RequestParam("CUENTA_SIN_CFDI_X_MONEDA_COMPL") String cuentaSinCfdiXMonedaDllsCompl,
            @RequestParam("MONEDA_SIN_CFDI") String monedaSinCfdi,
            @RequestParam("FLAG_IMP6") String flagImp6,
            @RequestParam("TAX_ID") String taxId,
            @RequestParam("PAGO_A_TERCEROS") String pagoATerceros,
            @RequestParam("CNPJ") String cnpj,
            @RequestParam("BANCO_BRASIL") String bancoBrasil,
            @RequestParam("CUENTA_CORRIENTE") String cuentaBrasil,
            @RequestParam("AGENCIA") String agencia,
            
            
            WebRequest webRequest, Model model) {

        ResponseQuery rq = new ResponseQuery();
try {
    
    if(model.asMap().get("compania")==null){
         log.info("Session no valida compania no encontrada");      
        rq.setSuccess(false);
            rq.setData(null);
            rq.setMsg("Error Session no valida");
            return rq;
        }
        
        if(model.asMap().get("usuario")==null){
              log.info("Session no valida usuario no encontrada");    
              rq.setSuccess(false);
            rq.setData(null);
            rq.setMsg("Error Session no valida");
            return rq;
        }
        String compania = model.asMap().get("compania").toString();
        String usuario = model.asMap().get("usuario").toString();

           
        
        Querys que = new Querys();
        String store = que.getSQL("GENERA_EMPLEADO_VIATIC");

        Map viatico = new HashMap();
        
       // System.out.println("nombre1: "+nombre1);
       // System.out.println("apellidoPaterno: "+apellidoPaterno);


        // log.info("banderaViatico: "+banderaViatico);    
        

            ErpCClientes prov = new ErpCClientes();
            ErpCClientesId provId = new ErpCClientesId();
               
             ErpCClientesLog2 log2 = new ErpCClientesLog2();
            ErpCClientesLog2Id logId = new ErpCClientesLog2Id();
          
      
           // ErpProvDireccion dir = new ErpProvDireccion();
            //ErpProvDireccionId dirId = new ErpProvDireccionId();
            ErpProvProductos prod = new ErpProvProductos();
            ErpProvProductosId prodId = new ErpProvProductosId();
            ErpCClientesId result = null;
            boolean result2 = false;
            ErpProvDireccionId resultDir = null;
            boolean resultDir2 = false;

            if (proceso.equalsIgnoreCase("I")) {
                    
                if(rfc != null && !rfc.equals("XEXX010101000")){
                    //Busca proveedor nacional
                     ErpCClientes provN=erpCClientesDao.findProveedor(compania, rfc);
                     if(provN!=null){
                          rq.setSuccess(false);
                            rq.setData(null);
                            rq.setMsg("RFC Ya fue dado de alta.");
                            return rq;
                     }
                }
                
                
                Map secProv = new HashMap();

                secProv.put("compania", compania);

                List listProv = processDao.getMapResult("BuscaMaxProveedorComp", secProv);

                Map provSec = (HashMap) listProv.get(0);

                log.info("cie" + banderaCie);

                provId.setCompania(compania);
                provId.setIdCliente("000" + provSec.get("ID_CLIENTE").toString());
                provId.setOrigen("P");
                prov.setId(provId);
                prov.setNombre(nombre);
                prov.setRfc(rfc);
                prov.setRazonSocial(razonSocial);
                prov.setCorreo(correo);
                prov.setTelefono(telefono);
                prov.setCuenta(cuenta);
                prov.setCuentaIva(cuentaIva);
                prov.setBanco(banco);
                prov.setNumeroCuenta(cuentaBanco);
                prov.setCuentaClave(clabe);
                prov.setTipoPoliza(tipoPoliza);
                prov.settClieprov(moneda);
                prov.settPersona(persona);
                prov.setPassword(password);
                prov.settOperacion(operacion);
                prov.settTercero(tercero);
                prov.setConceptoDefault(concepto);
                prov.setViatico(banderaViatico);
                prov.setClasificacion1(clasificacion1);
                prov.setClasificacion2(clasificacion2);
                prov.setPagoATerceros(pagoATerceros);
                
                prov.setCuentaTarjeta(cuentaTarjeta);
                prov.setNombre1(nombre1);
                prov.setApellidoMaterno(apellidoMaterno);
                prov.setApellidoPaterno(apellidoPaterno);
                prov.setCnpj(cnpj);
                
                prov.setAgencia(agencia);
                prov.setBancoBrasil(bancoBrasil);
                prov.setCuentaCorriente(cuentaBrasil);
                
                if (!segmentacion.equalsIgnoreCase("")){
                    prov.setSegmentacion(Integer.parseInt(segmentacion));
                }
                
                if(!idFamilia.equalsIgnoreCase("")){
                    
                    prov.setIdFamilia(Integer.parseInt(idFamilia));
                
                }


                if (correo.equalsIgnoreCase("")) {

                    prov.setUsuario(rfc);
                } else {

                    prov.setUsuario(correo);
                }

                //prov.setUsuario(correo);
                prov.setAuxiliar(auxiliar);
                prov.settOpearcionDiot(operacionDiot);
                prov.setCuentaGasto(cuentaGasto);
                prov.setCuentaIvaPago(cuentaIvaPago);
                prov.setCuentaComplementaria(cuentaComplementaria);
                prov.setCtoDefault(ctoDefault);
                prov.setIban(iban);
                prov.setSwift(swift);
                prov.setTipoCuenta(tipoCuenta);
               // prov.setActivacion("1");
                prov.setCuentaValida(cuentaValida);
                prov.setActivacion(activacion);
                prov.setCorreo2(correo2);
                prov.setBancoDolares(bancoDolares);
                prov.setNumCuentaDolares(numCuentaDolares);
                prov.setCuentaClabeDolares(clabeDolares);
                prov.setCuentaValidaDolares(cuentaValidaDolares);
                prov.setBancoExtranjero(bancoExtranjero);
                prov.setDirBancoExtranjero(dirBancoExtranjero);
                prov.setNumCtaExtranjera(numCtaExtranjera);
                prov.setDirBeneficiarioExt(dirBeneficiarioExt);
                
                prov.setCuentaSinCfdiXMoneda(cuentaSinCfdiXMoneda);
                prov.setCuentaSinCfdiXMonedaCompl(cuentaSinCfdiXMonedaDllsCompl);
                prov.setMonedaSinCfdi(monedaSinCfdi);
                prov.setFlagImp6(flagImp6);
                prov.setTaxId(taxId);
                
     

                if (banderaCie.equalsIgnoreCase("true") || banderaCie.equalsIgnoreCase("1")) {

                    prov.setCie("1");

                    if (!pagoCie.equalsIgnoreCase("")) {
                        prov.setPagoCie(new BigDecimal(pagoCie));
                        prov.setReferenciaCie(referenciaCie);

                    } else {

                        prov.setPagoCie(null);
                        prov.setReferenciaCie("");

                    }

                }

             
                if (!dias.equalsIgnoreCase("")) {

                    prov.setDiasCredito(Integer.parseInt(dias));
                }

                prov.setBancoPago(bancoPago);
                prov.setDireccion(direccion);
                prov.setCiudad(delegacion);
                prov.setCp(cp);
                prov.setEstado(estado);
                prov.setPais(pais);
                prov.setColonia(colonia);
                prov.setCalle(calle);
                
                prov.setCtaContableDolares(ctaContableDolares);
                prov.setCtaIvaDolares(ctaIvaDolares);
                prov.setCtaIvaComplementaria(ctaIvaComplementaria);
                prov.setCuentaPorComprobar(ctaPorComprobar);
                prov.setCuentaPorComprDlls(ctaPorComprobarDlls);
                prov.setCuentaPorCompComp(ctaPorComprobarCompl);
                prov.setCuentaSinCfdi(cuentaSinCfdi);
                prov.setCuentaComplSinCfdi(cuentaComplSinCfdi);

               
                prov.setCuentaIvaPagadoDlls(ctaIvaPagoDlls);
                prov.setCuentaIvaPagadoDllsCompl(ctaIvaComplPagDlls);

                result = erpCClientesDao.save(prov);
                
           
            
             logId.setCompania(compania);
             int id = erpCClientesLog2Dao.getMaxErpCClientesLog2Id(logId);
             logId.setFolio(id);
            
            //logId.setCompania(compania);
            //logId.setFolio(1);
            log2.setId(logId);
            log2.setId_Cliente("000" + provSec.get("ID_CLIENTE").toString());            
            log2.setRfc(rfc);
            log2.setFecha(new Date());
            log2.setCuentClabe(clabe);
            log2.setNumeroCuenta(cuentaBanco);
            log2.setCuenta(cuenta);
            log2.setBanco(banco);
            log2.setUsuario(usuario);
            log2.setEstatus("I");
            
            erpCClientesLog2Dao.save(log2);
            
            
             
                viatico.put("compania", compania);
                viatico.put("cliente", "000" + provSec.get("ID_CLIENTE").toString());
                viatico.put("rfc", rfc);
                viatico.put("banco", banco);
                viatico.put("cuenta", cuentaBanco);
                viatico.put("cuentaClabe", clabe);
                viatico.put("proceso", "I");

                if (banderaViatico.equalsIgnoreCase("true")) {

                    processDao.execute(store, viatico);
                }

            }

            if (proceso.equalsIgnoreCase("U")) {

                provId.setCompania(compania);
                provId.setIdCliente(idCliente);
                provId.setOrigen("P");
                prov.setId(provId);
                prov.setNombre(nombre);
                prov.setRfc(rfc);
                prov.setRazonSocial(razonSocial);
                prov.setCorreo(correo);
                prov.setTelefono(telefono);
                prov.setCuenta(cuenta);
                prov.setCuentaIva(cuentaIva);
                prov.setBanco(banco);
                prov.setNumeroCuenta(cuentaBanco);
                prov.setCuentaClave(clabe);
                prov.setTipoPoliza(tipoPoliza);
                prov.settClieprov(moneda);
                prov.settPersona(persona);
                prov.setPassword(password);
                prov.settOperacion(operacion);
                prov.settTercero(tercero);
                prov.setConceptoDefault(concepto);
                prov.setViatico(banderaViatico);
                prov.setActivacion(activacion);
                prov.setClasificacion1(clasificacion1);
                prov.setClasificacion2(clasificacion2);
                                prov.setPagoATerceros(pagoATerceros);

                prov.setCuentaTarjeta(cuentaTarjeta);
                prov.setNombre1(nombre1);
                prov.setApellidoMaterno(apellidoMaterno);
                prov.setApellidoPaterno(apellidoPaterno);
                prov.setCnpj(cnpj);
                
                prov.setAgencia(agencia);
                prov.setBancoBrasil(bancoBrasil);
                prov.setCuentaCorriente(cuentaBrasil);
                
                if (!segmentacion.equalsIgnoreCase("")){
                    prov.setSegmentacion(Integer.parseInt(segmentacion));
                }

                if(!idFamilia.equalsIgnoreCase("")){
                    
                    prov.setIdFamilia(Integer.parseInt(idFamilia));
                
                }
                
                if (correo.equalsIgnoreCase("")) {

                    prov.setUsuario(rfc);
                } else {

                    prov.setUsuario(correo);
                }

                prov.setAuxiliar(auxiliar);
                prov.settOpearcionDiot(operacionDiot);
                prov.setCuentaGasto(cuentaGasto);
                prov.setCuentaIvaPago(cuentaIvaPago);
                prov.setCuentaComplementaria(cuentaComplementaria);
                prov.setCtoDefault(ctoDefault);
                prov.setIban(iban);
                prov.setSwift(swift);
                prov.setTipoCuenta(tipoCuenta);
                prov.setCuentaValida(cuentaValida);
                prov.setCorreo2(correo2);
                prov.setBancoDolares(bancoDolares);
                prov.setNumCuentaDolares(numCuentaDolares);
                prov.setCuentaClabeDolares(clabeDolares);
                prov.setCuentaValidaDolares(cuentaValidaDolares);
                prov.setBancoExtranjero(bancoExtranjero);
                prov.setDirBancoExtranjero(dirBancoExtranjero);
                prov.setNumCtaExtranjera(numCtaExtranjera);
                prov.setDirBeneficiarioExt(dirBeneficiarioExt);
                prov.setTaxId(taxId);

                if (banderaCie.equalsIgnoreCase("true") || banderaCie.equalsIgnoreCase("1")) {

                    prov.setCie("1");
                    if (!pagoCie.equalsIgnoreCase("")) {
                        prov.setPagoCie(new BigDecimal(pagoCie));
                        prov.setReferenciaCie(referenciaCie);

                    } else {

                        prov.setPagoCie(null);
                        prov.setReferenciaCie(referenciaCie);

                    }

                } else {

                    prov.setCie("0");

                    prov.setPagoCie(null);
                    prov.setReferenciaCie("");

                }

                prov.setBancoPago(bancoPago);
                prov.setDireccion(direccion);
                prov.setCiudad(delegacion);
                prov.setCp(cp);
                prov.setEstado(estado);
                prov.setPais(pais);
                prov.setColonia(colonia);
                prov.setCalle(calle);
                
                
                prov.setCtaContableDolares(ctaContableDolares);
                prov.setCtaIvaDolares(ctaIvaDolares);
                prov.setCtaIvaComplementaria(ctaIvaComplementaria);
                prov.setCuentaPorComprobar(ctaPorComprobar);
                prov.setCuentaPorComprDlls(ctaPorComprobarDlls);
                prov.setCuentaPorCompComp(ctaPorComprobarCompl);
                prov.setCuentaSinCfdi(cuentaSinCfdi);
                prov.setCuentaComplSinCfdi(cuentaComplSinCfdi);
                prov.setCuentaIvaPagadoDlls(ctaIvaPagoDlls);
                prov.setCuentaIvaPagadoDllsCompl(ctaIvaComplPagDlls);
                prov.setCuentaSinCfdiXMoneda(cuentaSinCfdiXMoneda);
                prov.setCuentaSinCfdiXMonedaCompl(cuentaSinCfdiXMonedaDllsCompl);
                prov.setMonedaSinCfdi(monedaSinCfdi);

                if (!dias.equalsIgnoreCase("")) {

                    prov.setDiasCredito(Integer.parseInt(dias));
                }

                prov.setBancoPago(bancoPago);
                    log.info("prov:"+prov);
                result2 = erpCClientesDao.update(prov);
                
             logId.setCompania(compania);
             int id = erpCClientesLog2Dao.getMaxErpCClientesLog2Id(logId);
             logId.setFolio(id);
           
            //logId.setCompania(compania);
            //logId.setFolio(1);
            log2.setId(logId);
            log2.setId_Cliente(idCliente);            
            log2.setRfc(rfc);
            log2.setFecha(new Date());
            log2.setCuentClabe(clabe);
            log2.setNumeroCuenta(cuentaBanco);
            log2.setCuenta(cuenta);
            log2.setBanco(banco);
            log2.setUsuario(usuario);
            log2.setEstatus("U");
            erpCClientesLog2Dao.save(log2);
            
            
             

            }

            if (proceso.equalsIgnoreCase("I")) {
                if (result != null) {
                   
                        rq.setSuccess(true);
                        rq.setData(null);
                        rq.setMsg("Proveedor Guardado");
                   
                } else {

                    rq.setSuccess(false);
                    rq.setData(null);
                    rq.setMsg("Error al guardar");

                }
            }

            if (proceso.equalsIgnoreCase("U")) {
                if (result2 == true) {
                            rq.setSuccess(true);
                            rq.setData(null);
                            rq.setMsg("Proveedor Guardado");
                  
                } else {

                    rq.setSuccess(false);
                    rq.setData(null);
                    rq.setMsg("Error al actualizar");

                }
            }

        } catch (Exception e) {
            log.error("Error al guardar proveedor:",e);
            //e.printStackTrace();
            rq.setSuccess(false);
            rq.setData(null);
            rq.setMsg("Error al guardar");
            return rq;
        }

        return rq;
    }

    @RequestMapping(value = "/registro", method = RequestMethod.POST)
    public @ResponseBody
    ResponseQuery registroProv(
            @RequestParam("RAZON_SOCIAL") String razonSocial,
            @RequestParam("RFC") String rfc,
            @RequestParam("PAIS") String pais,
            @RequestParam("ESTADO") String estado,
            @RequestParam("DELEGACION") String delegacion,
            @RequestParam("COLONIA") String colonia,
            @RequestParam("mailingPostalCode") String cp,
            @RequestParam("CALLE") String calle,
            @RequestParam("BANCO") String banco,
            @RequestParam("CUENTA_BANCARIA") String cuentaBancaria,
            @RequestParam("CLABE") String clabe,
            @RequestParam("CORREO") String correo,
            @RequestParam("PASSWORD") String password,
            @RequestParam("cboTipoCuentaProv") String tipoCuenta,
            @RequestParam("DIRECCION") String direccion,
            @RequestParam("IBAN") String iban,
            @RequestParam("SWIFT") String swift,
            @RequestParam("cboPersonaPortal") String tPersona,
            @RequestParam("cboMonedaPortal") String tMoneda,
            @RequestParam("PASSWORD_VERIFIC") String passwordVerific,
            WebRequest webRequest, Model model) {

        ResponseQuery rq = new ResponseQuery();

        String compania = model.asMap().get("compania").toString();
        //  String usuario = model.asMap().get("usuario").toString();

        log.info(password);
        log.info(passwordVerific);

        if (!password.equalsIgnoreCase(passwordVerific)) {

            rq.setSuccess(false);
            rq.setData(null);
            rq.setMsg("Error. El password verificado no coincide.");
            return rq;

        }

        try {

            ErpCClientes prov = new ErpCClientes();
            ErpCClientesId provId = new ErpCClientesId();
            ErpProvDireccion dir = new ErpProvDireccion();
            ErpProvDireccionId dirId = new ErpProvDireccionId();

            ErpCClientesId result = null;
            ErpProvDireccionId resultDir = null;

            Map secProv = new HashMap();

            secProv.put("compania", compania);

            List listProv = processDao.getMapResult("BuscaMaxProveedorComp", secProv);

            Map provSec = (HashMap) listProv.get(0);

            //log.info("cie"+banderaCie);
            provId.setCompania(compania);
            provId.setIdCliente("000" + provSec.get("ID_CLIENTE").toString());
            provId.setOrigen("P");
            prov.setId(provId);
            prov.setNombre(razonSocial);
            if (tipoCuenta.equalsIgnoreCase("EXT")) {

                prov.setRfc("XEXX010101000");
                prov.setIban(iban);
                prov.setSwift(swift);

            } else {
                prov.setRfc(rfc);
                prov.setBanco(banco);
                prov.setNumeroCuenta(cuentaBancaria);
                prov.setCuentaClave(clabe);
            }
            prov.setRazonSocial(razonSocial);
            prov.setCorreo(correo);
            // prov.setTelefono(telefono);

            prov.settClieprov(tMoneda);
            prov.settPersona(tPersona);

            //if(tipoCuenta.equalsIgnoreCase("EXT")){
            //    prov.setUsuario("XEXX010101000");
            //}else{
            prov.setUsuario(correo);
            //}
            prov.setPassword(password);
            prov.setActivacion("0");
            prov.setTipoCuenta(tipoCuenta);

//                if(banderaCie.equalsIgnoreCase("true") || banderaCie.equalsIgnoreCase("1")){
//                
//                    prov.setCie("1");
//                    
//                   if(!pagoCie.equalsIgnoreCase("")) {
//                    prov.setPagoCie(Integer.parseInt(pagoCie));
//                    prov.setReferenciaCie(referenciaCie);
//                    
//                   }else{
//                       
//                       prov.setPagoCie(null);
//                        prov.setReferenciaCie("");
//                   
//                   }
//                          
//                }
            dirId.setCompania(compania);
            dirId.setIdProveedor("000" + provSec.get("ID_CLIENTE").toString());
            dir.setId(dirId);
            dir.setSec(1);
            dir.setCalle(calle);
            dir.setColonia(colonia);
            dir.setPais(pais);
            dir.setEstado(estado);

            dir.setDelegacion(delegacion);
            dir.setCp(cp);
            dir.setDireccion(direccion);

            result = erpCClientesDao.save(prov);
            resultDir = erpProvDireccionDao.save(dir);

            MailCadena cadena = new MailCadena();

            String cad = cadena.getCadenaAlfanumAleatoria(10);

            // MailVerificacion mail = new MailVerificacion();
            mailVerificacion.sendMail(cad, correo, correo, password, compania);

            if (result != null) {
                if (resultDir != null) {
                    rq.setSuccess(true);
                    rq.setData(null);
                    rq.setMsg("Proveedor Guardado");
                } else {

                    rq.setSuccess(false);
                    rq.setData(null);
                    rq.setMsg("Proveedor Guardado. Error al guardar la direccion");

                }
            } else {

                rq.setSuccess(false);
                rq.setData(null);
                rq.setMsg("Error al guardar");

            }

        } catch (Exception e) {
            e.printStackTrace();
            rq.setSuccess(false);
            rq.setData(null);
            rq.setMsg("Error al guardar");
            return rq;
        }

        return rq;
    }

    @RequestMapping(value = "/registroEmail", method = RequestMethod.GET)
    public @ResponseBody
    ResponseQuery enviaCorreo(
            @RequestParam("correo") String correo,
            @RequestParam("password") String password,
            @RequestParam("compania") String compania,
            WebRequest webRequest, Model model) {
        ResponseQuery rq = new ResponseQuery();

        try {

            MailCadena cadena = new MailCadena();

            String cad = cadena.getCadenaAlfanumAleatoria(10);

            mailVerificacion.sendMail(cad, correo, correo, password, compania);

            rq.setSuccess(true);
            rq.setData(null);
            rq.setMsg("correo Enviado");

        } catch (Exception e) {
            e.printStackTrace();
            rq.setSuccess(false);
            rq.setData(null);
            rq.setMsg("Error al guardar");
            return rq;
        }

        return rq;
    }

    @RequestMapping(value = "/registroAereo", method = RequestMethod.POST)
    public @ResponseBody
    ResponseQuery registroProvAereo(
            @RequestParam("RAZON_SOCIAL") String razonSocial,
            @RequestParam("RFC") String rfc,
            @RequestParam("PAIS") String pais,
            @RequestParam("ESTADO") String estado,
            @RequestParam("DELEGACION") String delegacion,
            @RequestParam("COLONIA") String colonia,
            @RequestParam("mailingPostalCode") String cp,
            @RequestParam("CALLE") String calle,
            @RequestParam("BANCO") String banco,
            @RequestParam("CUENTA_BANCARIA") String cuentaBancaria,
            @RequestParam("CLABE") String clabe,
            @RequestParam("CORREO") String correo,
            @RequestParam("PASSWORD") String password,
            @RequestParam("cboTipoCuentaProv") String tipoCuenta,
            @RequestParam("DIRECCION") String direccion,
            @RequestParam("IBAN") String iban,
            @RequestParam("SWIFT") String swift,
            @RequestParam("cboPersonaPortal") String tPersona,
            @RequestParam("cboMonedaPortal") String tMoneda,
            @RequestParam("PASSWORD_VERIFIC") String passwordVerific,
            @RequestParam("TELEFONO") String telefono,
            @RequestParam("CONTACTO") String contacto,
            WebRequest webRequest, Model model) {

        ResponseQuery rq = new ResponseQuery();

        String compania = model.asMap().get("compania").toString();
        //  String usuario = model.asMap().get("usuario").toString();

        log.info(password);
        log.info(passwordVerific);

        log.info("contacto");
        log.info(contacto);

        if (!password.equalsIgnoreCase(passwordVerific)) {

            rq.setSuccess(false);
            rq.setData(null);
            rq.setMsg("Error. El password verificado no coincide.");
            return rq;

        }
        
       

        try {

            ErpCClientes prov = new ErpCClientes();
            ErpCClientesId provId = new ErpCClientesId();
            ErpProvDireccion dir = new ErpProvDireccion();
            ErpProvDireccionId dirId = new ErpProvDireccionId();

            ErpCClientesId result = null;
            ErpProvDireccionId resultDir = null;

            Map secProv = new HashMap();

            secProv.put("compania", compania);
            
            if(rfc != null && !rfc.equals("XEXX010101000")){
                //Busca proveedor nacional
                 ErpCClientes provN=erpCClientesDao.findProveedor(compania, rfc);
                 if(provN!=null){
                      rq.setSuccess(false);
                        rq.setData(null);
                        rq.setMsg("RFC Already exists.");
                        return rq;
                 }
            }
             

            List listProv = processDao.getMapResult("BuscaMaxProveedorComp", secProv);

            Map provSec = (HashMap) listProv.get(0);

            //log.info("cie"+banderaCie);
            provId.setCompania(compania);
            provId.setIdCliente("000" + provSec.get("ID_CLIENTE").toString());
            provId.setOrigen("P");
            prov.setId(provId);
            prov.setNombre(razonSocial);
            if (tipoCuenta.equalsIgnoreCase("EXT")) {

                prov.setRfc("XEXX010101000");
                prov.setIban(iban);
                prov.setSwift(swift);

            } else {
                prov.setRfc(rfc);
                prov.setBanco(banco);
                prov.setNumeroCuenta(cuentaBancaria);
                prov.setCuentaClave(clabe);
            }
            prov.setRazonSocial(razonSocial);
            prov.setCorreo(correo);
            // prov.setTelefono(telefono);

            prov.settClieprov(tMoneda);
            prov.settPersona(tPersona);

            //if(tipoCuenta.equalsIgnoreCase("EXT")){
            //    prov.setUsuario("XEXX010101000");
            //}else{
            prov.setUsuario(correo);
            //}
            prov.setPassword(password);
            prov.setActivacion("0");
            prov.setTipoCuenta(tipoCuenta);
            prov.setTelefono(telefono);
            prov.setContacto(contacto);

//                if(banderaCie.equalsIgnoreCase("true") || banderaCie.equalsIgnoreCase("1")){
//                
//                    prov.setCie("1");
//                    
//                   if(!pagoCie.equalsIgnoreCase("")) {
//                    prov.setPagoCie(Integer.parseInt(pagoCie));
//                    prov.setReferenciaCie(referenciaCie);
//                    
//                   }else{
//                       
//                       prov.setPagoCie(null);
//                        prov.setReferenciaCie("");
//                   
//                   }
//                          
//                }
            dirId.setCompania(compania);
            dirId.setIdProveedor("000" + provSec.get("ID_CLIENTE").toString());
            dir.setId(dirId);
            dir.setSec(1);
            dir.setCalle(calle);
            dir.setColonia(colonia);
            dir.setPais(pais);
            dir.setEstado(estado);

            dir.setDelegacion(delegacion);
            dir.setCp(cp);
            dir.setDireccion(direccion);

            result = erpCClientesDao.save(prov);
            resultDir = erpProvDireccionDao.save(dir);

            MailCadena cadena = new MailCadena();

            String cad = cadena.getCadenaAlfanumAleatoria(10);

            // MailVerificacion mail = new MailVerificacion();
            mailVerificacion.sendMailAereo(cad, correo, correo, password, compania);

            if (result != null) {
                if (resultDir != null) {
                    rq.setSuccess(true);
                    rq.setData(null);
                    rq.setMsg("Proveedor Guardado");
                } else {

                    rq.setSuccess(false);
                    rq.setData(null);
                    rq.setMsg("Proveedor Guardado. Error al guardar la direccion");

                }
            } else {

                rq.setSuccess(false);
                rq.setData(null);
                rq.setMsg("Error al guardar");

            }

        } catch (Exception e) {
            e.printStackTrace();
            rq.setSuccess(false);
            rq.setData(null);
            rq.setMsg("Error al guardar");
            return rq;
        }

        return rq;
    }
    
     @RequestMapping(value = "/registroSGS", method = RequestMethod.POST)
    public @ResponseBody
    ResponseQuery registroProvSGS(
            @RequestParam("RAZON_SOCIAL") String razonSocial,
            @RequestParam("RFC") String rfc,
            @RequestParam("PAIS") String pais,
            @RequestParam("ESTADO") String estado,
            @RequestParam("DELEGACION") String delegacion,
            @RequestParam("COLONIA") String colonia,
            @RequestParam("mailingPostalCode") String cp,
            @RequestParam("CALLE") String calle,
            @RequestParam("BANCO") String banco,
            @RequestParam("CUENTA_BANCARIA") String cuentaBancaria,
            @RequestParam("CLABE") String clabe,
            @RequestParam("CORREO") String correo,
            @RequestParam("PASSWORD") String password,
            @RequestParam("cboTipoCuentaProv") String tipoCuenta,
            @RequestParam("DIRECCION") String direccion,
            @RequestParam("IBAN") String iban,
            @RequestParam("SWIFT") String swift,
            @RequestParam("cboPersonaPortal") String tPersona,
            @RequestParam("cboMonedaPortal") String tMoneda,
            @RequestParam("PASSWORD_VERIFIC") String passwordVerific,
            @RequestParam("TELEFONO") String telefono,
            @RequestParam("CONTACTO") String contacto,
            WebRequest webRequest, Model model) {

        ResponseQuery rq = new ResponseQuery();

        String compania = model.asMap().get("compania").toString();
        //  String usuario = model.asMap().get("usuario").toString();

        log.info(password);
        log.info(passwordVerific);

        log.info("contacto");
        log.info(contacto);

        if (!password.equalsIgnoreCase(passwordVerific)) {

            rq.setSuccess(false);
            rq.setData(null);
            rq.setMsg("Error. El password verificado no coincide.");
            return rq;

        }

        try {

            ErpCClientes prov = new ErpCClientes();
            ErpCClientesId provId = new ErpCClientesId();
            ErpProvDireccion dir = new ErpProvDireccion();
            ErpProvDireccionId dirId = new ErpProvDireccionId();

            ErpCClientesId result = null;
            ErpProvDireccionId resultDir = null;

            Map secProv = new HashMap();

            secProv.put("compania", compania);

            List listProv = processDao.getMapResult("BuscaMaxProveedorComp", secProv);

            Map provSec = (HashMap) listProv.get(0);

            //log.info("cie"+banderaCie);
            provId.setCompania(compania);
            provId.setIdCliente("000" + provSec.get("ID_CLIENTE").toString());
            provId.setOrigen("P");
            prov.setId(provId);
            prov.setNombre(razonSocial);
            if (tipoCuenta.equalsIgnoreCase("EXT")) {

                prov.setRfc("XEXX010101000");
                prov.setIban(iban);
                prov.setSwift(swift);

            } else {
                prov.setRfc(rfc);
                prov.setBanco(banco);
                prov.setNumeroCuenta(cuentaBancaria);
                prov.setCuentaClave(clabe);
            }
            prov.setRazonSocial(razonSocial);
            prov.setCorreo(correo);
            // prov.setTelefono(telefono);

            prov.settClieprov(tMoneda);
            prov.settPersona(tPersona);

            //if(tipoCuenta.equalsIgnoreCase("EXT")){
            //    prov.setUsuario("XEXX010101000");
            //}else{
            prov.setUsuario(correo);
            //}
            prov.setPassword(password);
            prov.setActivacion("0");
            prov.setTipoCuenta(tipoCuenta);
            prov.setTelefono(telefono);
            prov.setContacto(contacto);

//                if(banderaCie.equalsIgnoreCase("true") || banderaCie.equalsIgnoreCase("1")){
//                
//                    prov.setCie("1");
//                    
//                   if(!pagoCie.equalsIgnoreCase("")) {
//                    prov.setPagoCie(Integer.parseInt(pagoCie));
//                    prov.setReferenciaCie(referenciaCie);
//                    
//                   }else{
//                       
//                       prov.setPagoCie(null);
//                        prov.setReferenciaCie("");
//                   
//                   }
//                          
//                }
            dirId.setCompania(compania);
            dirId.setIdProveedor("000" + provSec.get("ID_CLIENTE").toString());
            dir.setId(dirId);
            dir.setSec(1);
            dir.setCalle(calle);
            dir.setColonia(colonia);
            dir.setPais(pais);
            dir.setEstado(estado);

            dir.setDelegacion(delegacion);
            dir.setCp(cp);
            dir.setDireccion(direccion);

            result = erpCClientesDao.save(prov);
            resultDir = erpProvDireccionDao.save(dir);

            MailCadena cadena = new MailCadena();

            String cad = cadena.getCadenaAlfanumAleatoria(10);

            // MailVerificacion mail = new MailVerificacion();
            mailVerificacion.sendMailSGS(cad, correo, correo, password, compania);

            if (result != null) {
                if (resultDir != null) {
                    rq.setSuccess(true);
                    rq.setData(null);
                    rq.setMsg("Proveedor Guardado");
                } else {

                    rq.setSuccess(false);
                    rq.setData(null);
                    rq.setMsg("Proveedor Guardado. Error al guardar la direccion");

                }
            } else {

                rq.setSuccess(false);
                rq.setData(null);
                rq.setMsg("Error al guardar");

            }

        } catch (Exception e) {
            e.printStackTrace();
            rq.setSuccess(false);
            rq.setData(null);
            rq.setMsg("Error al guardar");
            return rq;
        }

        return rq;
    }

    @RequestMapping(value = "/registro/verific")
    public String verificSystemUser(String compania, String user, String verific, WebRequest webRequest, Model model) {

        boolean isSave = false;

        ResponseQuery rq = new ResponseQuery();

        boolean result = false;

        try {
            ObjectMapper mapper = new ObjectMapper();
            DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
            mapper.setDateFormat(df);

//            log.info(user);
//            log.info(verific);
            //     isSave=SystemUserDao.verifica(user, verific);
            // isSave = SystemUserDao.update(systemUser);
            ErpCClientes prov = new ErpCClientes();
            ErpCClientesId provId = new ErpCClientesId();

            provId.setCompania(compania);
            prov.setId(provId);
            prov.setUsuario(user);
            prov.setActivacion("1");

            result = erpCClientesDao.updateVerific(prov);

        } catch (Exception e) {
            isSave = false;
            e.printStackTrace();
            return "inicio/RegistroIncorrecto";
        }

        if (result) {
            List lista = new ArrayList();
            lista.add(null);
            rq.setSuccess(true);
            rq.setData(lista);
            rq.setMsg("Ready");
            return "init/RegistroCorrecto";
        } else {
            rq.setSuccess(false);
            rq.setData(null);
            rq.setMsg("data null");
            return "init/RegistroIncorrecto";
        }

    }

    @RequestMapping(value = "/registro/verific2")
    public String verificSystemUser2(String compania, String user, String verific, WebRequest webRequest, Model model) {

        boolean isSave = false;

        ResponseQuery rq = new ResponseQuery();

        boolean result = false;

        try {
            ObjectMapper mapper = new ObjectMapper();
            DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
            mapper.setDateFormat(df);

//            log.info(user);
//            log.info(verific);
            //     isSave=SystemUserDao.verifica(user, verific);
            // isSave = SystemUserDao.update(systemUser);
            ErpCClientes prov = new ErpCClientes();
            ErpCClientesId provId = new ErpCClientesId();

            provId.setCompania(compania);
            prov.setId(provId);
            prov.setUsuario(user);
            prov.setActivacion("1");
            prov.setCadenaVerific(verific);

            result = erpCClientesDao.updateVerific(prov);

        } catch (Exception e) {
            isSave = false;
            e.printStackTrace();
            return "inicio/RegistroIncorrectoAereo";
        }

        if (result) {
            List lista = new ArrayList();
            lista.add(null);
            rq.setSuccess(true);
            rq.setData(lista);
            rq.setMsg("Ready");
            return "init/RegistroCorrectoAereo";
        } else {
            rq.setSuccess(false);
            rq.setData(null);
            rq.setMsg("data null");
            return "init/RegistroIncorrectoAereo";
        }

    }
    
      @RequestMapping(value = "/registro/verificSGS")
    public String verificSystemUser2SGS(String compania, String user, String verific, WebRequest webRequest, Model model) {

        boolean isSave = false;

        ResponseQuery rq = new ResponseQuery();

        boolean result = false;

        try {
            ObjectMapper mapper = new ObjectMapper();
            DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
            mapper.setDateFormat(df);

//            log.info(user);
//            log.info(verific);
            //     isSave=SystemUserDao.verifica(user, verific);
            // isSave = SystemUserDao.update(systemUser);
            ErpCClientes prov = new ErpCClientes();
            ErpCClientesId provId = new ErpCClientesId();

            provId.setCompania(compania);
            prov.setId(provId);
            prov.setUsuario(user);
            prov.setActivacion("1");
            prov.setCadenaVerific(verific);


            result = erpCClientesDao.updateVerific(prov);

        } catch (Exception e) {
    
            isSave = false;
            e.printStackTrace();
            return "inicio/RegistroIncorrectoSGS";
        }

        if (result) {
            List lista = new ArrayList();
            lista.add(null);
            rq.setSuccess(true);
            rq.setData(lista);
            rq.setMsg("Ready");
            return "init/RegistroCorrectoSGS";
        } else {
            rq.setSuccess(false);
            rq.setData(null);
            rq.setMsg("data null");
            return "init/RegistroIncorrectoSGS";
        }

    }

    @RequestMapping(value = "/deleteProveedor", method = RequestMethod.POST)
    public @ResponseBody
    ResponseQuery deleteProv(String data, WebRequest webRequest, Model model) throws IOException {

        ResponseQuery rq = new ResponseQuery();
        //     log.info("data factura cancelacion:"+data);

        int index = data.indexOf("[");
        if (index == -1) {
            data = "[" + data + "]";
        }

        String compania = model.asMap().get("compania").toString();
        String usuario = model.asMap().get("usuario").toString();
        ObjectMapper mapper = new ObjectMapper();

        try {
            List<ProvDTO> lista = mapper.readValue(data,
                    mapper.getTypeFactory().constructCollectionType(List.class,
                            ProvDTO.class));

            if (lista.isEmpty()) {
                rq.setSuccess(false);
                rq.setData(null);
                rq.setMsg("Error no existen datos que guardar");
                return rq;
            }

            Iterator<ProvDTO> it = lista.iterator();

            ErpCClientes prov = new ErpCClientes();
            ErpCClientesId provId = new ErpCClientesId();
            ErpProvDireccion dir = new ErpProvDireccion();
            ErpProvDireccionId dirId = new ErpProvDireccionId();
            int banderaGe = 0;
            while (it.hasNext()) {
//                 log.info("-------------------------------------------------------------------");
                ProvDTO ss = it.next();

                provId.setCompania(ss.compania);
                provId.setIdCliente(ss.idCliente);
                provId.setOrigen("P");
                prov.setId(provId);

                dirId.setCompania(ss.compania);
                dirId.setIdProveedor(ss.idCliente);
                dir.setId(dirId);

                erpCClientesDao.delete(prov);
                erpProvDireccionDao.delete(dir);
                
            ErpCClientesLog2 log2 = new ErpCClientesLog2();
            ErpCClientesLog2Id logId = new ErpCClientesLog2Id();
            
            logId.setCompania(compania);
             int id = erpCClientesLog2Dao.getMaxErpCClientesLog2Id(logId);
             logId.setFolio(id);
            
            //logId.setCompania(compania);
            //logId.setFolio(1);
            log2.setId(logId);
            log2.setId_Cliente(ss.idCliente);            
            log2.setRfc(ss.rfc);
            log2.setFecha(new Date());
            log2.setCuentClabe(ss.cuentaClabe);
            log2.setNumeroCuenta(ss.numeroCuenta);
            log2.setCuenta(ss.cuenta);
            log2.setBanco(ss.banco);
            log2.setUsuario(usuario);
            log2.setEstatus("D");
            
            erpCClientesLog2Dao.save(log2);
            
            

                
                

                banderaGe = 1;
            }

            if (banderaGe == 1) {

                rq.setSuccess(true);
                rq.setData(null);
                rq.setMsg("Registros eliminados correctamente");

            } else {

                rq.setSuccess(true);
                rq.setData(null);
                rq.setMsg("Error al eliminar");

            }

        } catch (Exception e) {
            log.error("Error deleteProv:",e);
            rq.setSuccess(false);
            rq.setData(null);
            rq.setMsg("Error al eliminar");
            return rq;
        }

        return rq;
    }
    
    
    @RequestMapping(value = "/saveServicio", method = RequestMethod.POST)
    public @ResponseBody
    ResponseQuery saveServicio(@RequestParam("ID_PROVEEDOR") String proveedor,
            @RequestParam("ID_SERVICIO") String idServicio,
            WebRequest webRequest, Model model) {

        ResponseQuery rq = new ResponseQuery();

        String compania = model.asMap().get("compania").toString();
        String usuario = model.asMap().get("usuario").toString();

        try {
            boolean result2 = false;
            ErpFamiliaProveedorId result = null;

            ErpFamiliaProveedor prod = new ErpFamiliaProveedor();
            ErpFamiliaProveedorId prodId = new ErpFamiliaProveedorId();

                prodId.setCompania(compania);
                prodId.setIdProveedor(proveedor);
                prodId.setIdFamilia(Integer.parseInt(idServicio));
                
                prod.setId(prodId);
                prod.setEstatus("1");

                result = erpFamiliaProveedorDao.save(prod);

            
                if (result != null) {

                    rq.setSuccess(true);
                    rq.setData(null);
                    rq.setMsg("Servicio Guardado");
                } else {

                    rq.setSuccess(false);
                    rq.setData(null);
                    rq.setMsg("Error al guardar Servicio");

                }
        

        } catch (Exception e) {
                    log.error("Error al guardar servicio:",e);
            
            rq.setSuccess(false);
            rq.setData(null);
            rq.setMsg("Error al guardar");
            return rq;
        }

        return rq;
    }
    @RequestMapping(value = "/saveProduct", method = RequestMethod.POST)
    public @ResponseBody
    ResponseQuery saveProduct(@RequestParam("PROVEEDOR_FORM") String proveedor,
            @RequestParam("ID_PRODUCTO_FORM") String idProducto,
            @RequestParam("NOMBRE_FORM") String nombre,
            @RequestParam("CODIGO_FORM") String codigo,
            @RequestParam("UNIDAD_MEDIDA_FORM") String unidadMedida,
            @RequestParam("PRECIO_SIN_IVA_FORM") String precio,
            @RequestParam("CODIGO_PROVEEDOR_FORM") String codigoProveedor,
            WebRequest webRequest, Model model) {

        ResponseQuery rq = new ResponseQuery();

        String compania = model.asMap().get("compania").toString();
        String usuario = model.asMap().get("usuario").toString();

        try {
            boolean result2 = false;
            ErpProvProductosId result = null;

            ErpProvProductos prod = new ErpProvProductos();
            ErpProvProductosId prodId = new ErpProvProductosId();

            if (idProducto.equalsIgnoreCase("")) {
                prodId.setCompania(compania);
                prodId.setProveedor(proveedor);
                int sec = erpProvProductosDao.getMaxErpProductos(prodId);
                prodId.setIdProducto(sec);
                prod.setId(prodId);
                prod.setCodigo(codigo);
                prod.setCodigoProveedor(codigoProveedor);
                prod.setNombre(nombre);
                if (!precio.equalsIgnoreCase("")) {
                    prod.setPrecioSinIva(new BigDecimal(precio));
                }
                prod.setUnidadMedida(unidadMedida);

                result = erpProvProductosDao.save(prod);

            } else {

                prodId.setCompania(compania);
                prodId.setProveedor(proveedor);
                prodId.setIdProducto(Integer.parseInt(idProducto));
                prod.setId(prodId);
                prod.setCodigo(codigo);
                prod.setCodigoProveedor(codigoProveedor);
                prod.setNombre(nombre);
                if (!precio.equalsIgnoreCase("")) {
                    prod.setPrecioSinIva(new BigDecimal(precio));
                }
                prod.setUnidadMedida(unidadMedida);

                result2 = erpProvProductosDao.update(prod);

            }

            if (idProducto.equalsIgnoreCase("")) {
                if (result != null) {

                    rq.setSuccess(true);
                    rq.setData(null);
                    rq.setMsg("Producto Guardado");
                } else {

                    rq.setSuccess(false);
                    rq.setData(null);
                    rq.setMsg("Error al guardar Producto");

                }
            } else {

                if (result2 == true) {

                    rq.setSuccess(true);
                    rq.setData(null);
                    rq.setMsg("Producto Actualizado");
                } else {

                    rq.setSuccess(false);
                    rq.setData(null);
                    rq.setMsg("Error al guardar Producto");

                }

            }

        } catch (Exception e) {
                    log.error("Error al guardar producto:",e);
            
            rq.setSuccess(false);
            rq.setData(null);
            rq.setMsg("Error al guardar");
            return rq;
        }

        return rq;
    }
    
    
            
    @RequestMapping(value = "/deleteServicio", method = RequestMethod.POST)
    public @ResponseBody
    ResponseQuery deleteServicio(String data, WebRequest webRequest, Model model) throws IOException {

        ResponseQuery rq = new ResponseQuery();
        //     log.info("data factura cancelacion:"+data);

        int index = data.indexOf("[");
        if (index == -1) {
            data = "[" + data + "]";
        }

        String compania = model.asMap().get("compania").toString();
        String usuario = model.asMap().get("usuario").toString();
        ObjectMapper mapper = new ObjectMapper();

        try {
            List<ServiciosDTO> lista = mapper.readValue(data,
                    mapper.getTypeFactory().constructCollectionType(List.class,
                            ServiciosDTO.class));

            if (lista.isEmpty()) {
                rq.setSuccess(false);
                rq.setData(null);
                rq.setMsg("Error no existen datos que guardar");
                return rq;
            }

            Iterator<ServiciosDTO> it = lista.iterator();

            ErpFamiliaProveedor prod = new ErpFamiliaProveedor();
            ErpFamiliaProveedorId prodId = new ErpFamiliaProveedorId();

            int banderaGe = 0;
            while (it.hasNext()) {
//                 log.info("-------------------------------------------------------------------");
                ServiciosDTO ss = it.next();

                prodId.setCompania(ss.compania);
                prodId.setIdProveedor(ss.idProveedor);
                prodId.setIdFamilia(Integer.parseInt(ss.idFamilia));
                prod.setId(prodId);

                erpFamiliaProveedorDao.delete(prod);

                banderaGe = 1;
            }

            if (banderaGe == 1) {

                rq.setSuccess(true);
                rq.setData(null);
                rq.setMsg("Registros eliminados correctamente");

            } else {

                rq.setSuccess(true);
                rq.setData(null);
                rq.setMsg("Error al eliminar");

            }

        } catch (Exception e) {
            log.error("Error al borrar servicio:",e);
            rq.setSuccess(false);
            rq.setData(null);
            rq.setMsg("Error al eliminar");
            return rq;
        }

        return rq;
    }


    @RequestMapping(value = "/deleteProducto", method = RequestMethod.POST)
    public @ResponseBody
    ResponseQuery deleteProducto(String data, WebRequest webRequest, Model model) throws IOException {

        ResponseQuery rq = new ResponseQuery();
        //     log.info("data factura cancelacion:"+data);

        int index = data.indexOf("[");
        if (index == -1) {
            data = "[" + data + "]";
        }

        String compania = model.asMap().get("compania").toString();
        String usuario = model.asMap().get("usuario").toString();
        ObjectMapper mapper = new ObjectMapper();

        try {
            List<ProductoDTO> lista = mapper.readValue(data,
                    mapper.getTypeFactory().constructCollectionType(List.class,
                            ProductoDTO.class));

            if (lista.isEmpty()) {
                rq.setSuccess(false);
                rq.setData(null);
                rq.setMsg("Error no existen datos que guardar");
                return rq;
            }

            Iterator<ProductoDTO> it = lista.iterator();

            ErpProvProductos prod = new ErpProvProductos();
            ErpProvProductosId prodId = new ErpProvProductosId();

            int banderaGe = 0;
            while (it.hasNext()) {
//                 log.info("-------------------------------------------------------------------");
                ProductoDTO ss = it.next();

                prodId.setCompania(ss.compania);
                prodId.setIdProducto(Integer.parseInt(ss.idProductor));
                prodId.setProveedor(ss.proveedor);
                prod.setId(prodId);
                prod.setCodigo(ss.codigo);
                prod.setNombre(ss.nombre);
                prod.setPrecioSinIva(new BigDecimal(ss.precioSinIva));
                prod.setUnidadMedida(ss.unidadMedida);

                erpProvProductosDao.delete(prod);

                banderaGe = 1;
            }

            if (banderaGe == 1) {

                rq.setSuccess(true);
                rq.setData(null);
                rq.setMsg("Registros eliminados correctamente");

            } else {

                rq.setSuccess(true);
                rq.setData(null);
                rq.setMsg("Error al eliminar");

            }

        } catch (Exception e) {
            log.error("Error al guardar proveedor:",e);
            rq.setSuccess(false);
            rq.setData(null);
            rq.setMsg("Error al eliminar");
            return rq;
        }

        return rq;
    }

    @RequestMapping(value = "/actDatosBanExt", method = RequestMethod.POST)
    public @ResponseBody
    ResponseQuery actDatosBan(@RequestParam("IBAN") String iban,
            @RequestParam("SWIFT") String swift,
            WebRequest webRequest, Model model) {

        ResponseQuery rq = new ResponseQuery();

        String compania = model.asMap().get("compania").toString();
        String usuario = model.asMap().get("usuario").toString();

        // log.info("usuario "+usuario);
        // log.info("ctaClabe "+ctaClabe);
        // log.info("banco "+banco);
        // log.info("ctaNum "+ctaNum);
        // log.info("compania "+compania);
        ErpCClientes prov = new ErpCClientes();
        ErpCClientesId provId = new ErpCClientesId();

        provId.setCompania(compania);
        prov.setId(provId);
        prov.setIban(iban);
        prov.setSwift(swift);
        prov.setUsuario(usuario);

        boolean result = erpCClientesDao.updateDatosBancariosExt(prov);

        if (result == true) {

            Querys queP = new Querys();
            String storeP = queP.getSQL("InsertaCorreoProvBanExt");

            Map paramProv = new HashMap();
            paramProv.put("compania", compania);
            paramProv.put("usuario", usuario);
            paramProv.put("iban", iban);
            paramProv.put("swift", swift);

            int val = processDao.execute(storeP, paramProv);

            rq.setData(null);
            rq.setMsg("Datos Guardados Correctamente");
            rq.setSuccess(true);

        } else {

            rq.setData(null);
            rq.setMsg("Error al guardar la información");
            rq.setSuccess(false);

        }

        return rq;
    }

    @RequestMapping(value = "/actDatosBan", method = RequestMethod.POST)
    public @ResponseBody
    ResponseQuery actDatosBan(@RequestParam("CTA_CLABE") String ctaClabe,
            @RequestParam("CTA_NUM") String ctaNum,
            @RequestParam("BANCO") String banco,
            WebRequest webRequest, Model model) {

        ResponseQuery rq = new ResponseQuery();

        String compania = model.asMap().get("compania").toString();
        String usuario = model.asMap().get("usuario").toString();

        // log.info("usuario "+usuario);
        // log.info("ctaClabe "+ctaClabe);
        // log.info("banco "+banco);
        // log.info("ctaNum "+ctaNum);
        // log.info("compania "+compania);
        ErpCClientes prov = new ErpCClientes();
        ErpCClientesId provId = new ErpCClientesId();

        provId.setCompania(compania);
        prov.setId(provId);
        prov.setCuentaClave(ctaClabe);
        prov.setBanco(banco);
        prov.setNumeroCuenta(ctaNum);
        prov.setUsuario(usuario);

        boolean result = erpCClientesDao.updateDatosBancarios(prov);

        if (result == true) {

            Querys queP = new Querys();
            String storeP = queP.getSQL("InsertaCorreoProvBan");

            Map paramProv = new HashMap();
            paramProv.put("compania", compania);
            paramProv.put("usuario", usuario);
            paramProv.put("banco", banco);
            paramProv.put("ctaNum", ctaNum);
            paramProv.put("ctaClabe", ctaClabe);

            int val = processDao.execute(storeP, paramProv);

            rq.setData(null);
            rq.setMsg("Datos Guardados Correctamente");
            rq.setSuccess(true);

        } else {

            rq.setData(null);
            rq.setMsg("Error al guardar la información");
            rq.setSuccess(false);

        }

        return rq;
    }
    
    
       @RequestMapping(value = "/saveArchivo", method = RequestMethod.POST )
     
    public @ResponseBody
    String create(@RequestParam("archCOMPANIA2Prov") String compania2,
            @RequestParam("archIdProv") String id,
            @RequestParam("archNOMBRE2Prov") String nombre,
            @RequestParam("archDESCRIPCION2Prov") String descripcion,
            @RequestParam("cboTipoArchProv") String tipo,
            FileUploadBean uploadItemProv,
            BindingResult result, WebRequest webRequest, Model model) throws UnsupportedEncodingException {
        
           
       
         String nombreCod = new String(nombre.getBytes("ISO-8859-15"), "UTF-8");
         String descripcionCod = new String(descripcion.getBytes("ISO-8859-15"), "UTF-8");
         
        
        boolean isSave = true;
        SimpleDateFormat formatoDelTexto2 = new SimpleDateFormat("dd/MM/yyyy");
        
         String compania = model.asMap().get("compania").toString();
         String usuario = model.asMap().get("usuario").toString();
     
         System.out.println("Cargando Archivo");
               
        ExtJSFormResult extjsFormResult = new ExtJSFormResult();
        
        if (model.asMap().get("usuario") == null) {

            isSave = false;
            extjsFormResult.setMessage("Sesion no valida");
            extjsFormResult.setSuccess(isSave);
            return extjsFormResult.toString();
        }
        
        System.out.println("Session Valida");

        String path = "";
        String url = "";
        String extension = "";
        String nombreArc ="";

        try {
            MultipartFile file = uploadItemProv.getFile();

            InputStream inputStream = null;
            OutputStream outputStream = null;
            if (file.getSize() > 0) {
                inputStream = file.getInputStream();
                long maxSizeDocument;
                long maxSize = new Long("5145728");
                
                System.out.println("Tamaño en server: "+maxSize);
                System.out.println("Tamaño archivo: "+file.getSize());
                
                if (file.getSize() > maxSize) {
                    //System.out.println("File Size:::" + file.getSize());
                    isSave = false;
                    extjsFormResult.setMessage("Tamaño no valido");
                    extjsFormResult.setSuccess(isSave);
                    return extjsFormResult.toString();
                }

                int i = file.getOriginalFilename().lastIndexOf('.');
                
//                System.out.println("Nombre:" + file.getOriginalFilename());
                
               
                    extension = file.getOriginalFilename().substring(i + 1);
                    nombreArc = file.getOriginalFilename().substring(0 , i);
//                    System.out.println("i:" + i);
//               System.out.println("exte:" + extension);
//               System.out.println("nombreArc:" + nombreArc);
               // System.out.println("exte:" + extension);
//                if (!extension.trim().equals("pdf")) {
//                    isSave = false;
//                    extjsFormResult.setMessage("Archivo pdf no valido");
//                    extjsFormResult.setSuccess(isSave);
//                    return extjsFormResult.toString();
//                }
                String hora = "" + System.currentTimeMillis();
                 path ="D:/Administrategia/empresarial/files/DOC_PROV/"+compania+"/"+id+"/"+nombreArc+"."+extension;
              
                 url ="/cfiles/DOC_PROV/"+compania+"/"+id+"/"+"/"+nombreArc+"."+extension;
                 
                 revisarDirectorio("D:/Administrategia/empresarial/files/DOC_PROV/"+compania+"/"+id+"/");
                 
                 System.out.println("Ruta: "+path);
                 System.out.println("url: "+url);
             
                outputStream = new FileOutputStream(path);

                int readBytes = 0;
                byte[] buffer = new byte[10000];
                
                System.out.println("Empezando Carga...");
                
                while ((readBytes = inputStream.read(buffer, 0, 10000)) != -1) {
                    outputStream.write(buffer, 0, readBytes);
                }
                outputStream.close();
                inputStream.close();
                
                System.out.println("Archivo Guardado");
            }

            
            
               Querys que = new Querys();
               String store = que.getSQL("insertaArchivoProveedor");
               
                   Map parametros = new HashMap();
             
 //  P_SID              VARCHAR2,

             
                parametros.put("compania", compania);
                parametros.put("idProv",id);
                parametros.put("descripcion",descripcionCod);
                parametros.put("nombre",nombreCod);
                parametros.put("path",path);
                parametros.put("url",url);
                parametros.put("nombreArch",nombreArc+"."+extension);
                parametros.put("usuario",usuario);
                parametros.put("tipo",tipo);

                
           



               int val = processDao.execute(store, parametros);
                
            System.out.println("Datos Insertados");
   
            if (val > 0) {
                   isSave = true;
                extjsFormResult.setMessage("Archivo Guardado");
                extjsFormResult.setSuccess(isSave);
                return extjsFormResult.toString();
               
            } else {
             
                 isSave = false;
                extjsFormResult.setMessage("Error al guardar los datos");
                    extjsFormResult.setSuccess(isSave);
                return extjsFormResult.toString();
            }

        } catch (Exception e) {
            e.printStackTrace();
            isSave = false;
        }




        extjsFormResult.setSuccess(isSave);

        return extjsFormResult.toString();
    }
            @RequestMapping(value = "/deleteArchivo", method = RequestMethod.POST)
    public @ResponseBody
    ResponseQuery deleteArchivo( String idProv,String id, WebRequest webRequest, Model model) {

        ResponseQuery rq = new ResponseQuery();
   //     System.out.println("data factura cancelacion:"+data);
        


            String compania = model.asMap().get("compania").toString();
            String usuario = model.asMap().get("usuario").toString();
            ObjectMapper mapper = new ObjectMapper();
          
                   
        try{
            
             
            
              Querys que = new Querys();
               String store = que.getSQL("BorraDatosArchivosProv");
               
                   Map parametros = new HashMap();
             
 //  P_SID              VARCHAR2,

             
                parametros.put("compania", compania);
                parametros.put("idProv",idProv);
                 parametros.put("id",id);
           



               int val = processDao.execute(store, parametros);
          
            
            if (val > 0){
            
                
                    rq.setSuccess(true);
                    rq.setData(null);
                    rq.setMsg("Archivo eliminado correctamente");
              
                
            }else{
                
                rq.setSuccess(true);
                rq.setData(null);
                rq.setMsg("Error al eliminar");
                
            }
            

        }catch(Exception e){
            e.printStackTrace();
            rq.setSuccess(false);
            rq.setData(null);
            rq.setMsg("Error al eliminar");
           return rq;
        }
        
        return rq;
    }
    
    
    
    
      public void revisarDirectorio(String dirCompania) {

        File file = new File(dirCompania);
//        System.out.println("Directory" + dirOutFileDocument + dirCompania + "/");
        if (!file.exists()) {
            if (file.mkdirs()) {
//                System.out.println("Directory is created!");
            } else {
//                System.out.println("Failed to create directory!");
            }
        }

    }

    public void setProcessDao(ProcessDao processDao) {
        this.processDao = processDao;
    }

    public void setErpCClientesDao(ErpCClientesDao erpCClientesDao) {
        this.erpCClientesDao = erpCClientesDao;
    }

    public void setErpProvDireccionDao(ErpProvDireccionDao erpProvDireccionDao) {
        this.erpProvDireccionDao = erpProvDireccionDao;
    }

    public void setErpProvProductosDao(ErpProvProductosDao erpProvProductosDao) {
        this.erpProvProductosDao = erpProvProductosDao;
    }

    public void setMailVerificacion(MailVerificacion mailVerificacion) {
        this.mailVerificacion = mailVerificacion;
    }

    public void setErpFamiliaProveedorDao(ErpFamiliaProveedorDao erpFamiliaProveedorDao) {
        this.erpFamiliaProveedorDao = erpFamiliaProveedorDao;
    }

    public void setErpCClientesLog2Dao(ErpCClientesLog2Dao erpCClientesLog2Dao) {
        this.erpCClientesLog2Dao = erpCClientesLog2Dao;
    }

    
    
}
