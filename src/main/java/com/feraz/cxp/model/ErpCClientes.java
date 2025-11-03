/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.feraz.cxp.model;

/**
 *
 * @author Feraz3
 */
import java.math.BigDecimal;
import java.util.Date;
import javax.persistence.AttributeOverride;

import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Temporal;

@Entity
@Table(name = "ERP_CCLIENTES")
public class ErpCClientes implements java.io.Serializable {

    @EmbeddedId
    @AttributeOverrides({
        @AttributeOverride(name = "compania", column = @Column(name = "COMPANIA", nullable = false, length = 10))
        ,
        @AttributeOverride(name = "idCliente", column = @Column(name = "ID_CLIENTE", nullable = false, length = 12))
        ,
        @AttributeOverride(name = "origen", column = @Column(name = "ORIGEN", nullable = false, length = 3))

    })

    private ErpCClientesId id;

    @Column(name = "NOMBRE")
    private String nombre;

    @Column(name = "RFC")
    private String rfc;

    @Column(name = "ACT_ECONOMICA")
    private String actEconomica;

    @Column(name = "F_ALTA")
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date fAlta;

    @Column(name = "RAZON_SOCIAL")
    private String razonSocial;

    @Column(name = "PAPELERIA")
    private String papeleria;

    @Column(name = "T_PERSONA")
    private String tPersona;

    @Column(name = "T_CLIEPROV")
    private String tClieprov;

    @Column(name = "T_TERCERO")
    private String tTercero;

    @Column(name = "T_OPERACION")
    private String tOperacion;

    @Column(name = "ID_FISCAL")
    private String idFiscal;

    @Column(name = "NOMBRE_EXTRANJERO")
    private String nombreExtranjero;

    @Column(name = "PAIS_RESIDENCIA")
    private String paisResidencia;

    @Column(name = "NACIONALIDAD")
    private String nacionalidad;

    @Column(name = "FORMA_PAGO")
    private String formaPago;

    @Column(name = "BANCO")
    private String banco;

    @Column(name = "NUMERO_CUENTA")
    private String numeroCuenta;

    @Column(name = "CUENTA_CLAVE")
    private String cuentaClave;

    @Column(name = "SALDO_INICIAL")
    private BigDecimal saldoInicial;

    @Column(name = "DIAS_CREDITO")
    private Integer diasCredito;

    @Column(name = "CONCEPTO_DEFAULT")
    private String conceptoDefault;

    @Column(name = "AUXILIAR")
    private String auxiliar;

    @Column(name = "T_OPERACION_DIOT")
    private String tOpearcionDiot;

    @Column(name = "CUENTA")
    private String cuenta;

    @Column(name = "CIE")
    private String cie;

    @Column(name = "PAGO_CIE")
    private BigDecimal pagoCie;

    @Column(name = "REFERENCIA_CIE")
    private String referenciaCie;

    @Column(name = "CUENTA_IVA")
    private String cuentaIva;

    @Column(name = "CORREO")
    private String correo;

    @Column(name = "TELEFONO")
    private String telefono;

    @Column(name = "TIPO_POLIZA")
    private String tipoPoliza;

    @Column(name = "BANCO_PAGO")
    private String bancoPago;

    @Column(name = "ESTATUS")
    private String estatus;

    @Column(name = "PASSWORD")
    private String password;

    @Column(name = "VIATICO")
    private String viatico;

    @Column(name = "USUARIO")
    private String usuario;

    @Column(name = "CUENTA_GASTO")
    private String cuentaGasto;

    @Column(name = "CUENTA_IVA_PAGO")
    private String cuentaIvaPago;

    @Column(name = "CUENTA_COMPLEMENTARIA")
    private String cuentaComplementaria;

    @Column(name = "CTO_CTO_DEFAULT")
    private String ctoDefault;

    @Column(name = "TIPO_CUENTA")
    private String tipoCuenta;

    @Column(name = "ACTIVACION")
    private String activacion;

    @Column(name = "IBAN")
    private String iban;

    @Column(name = "SWIFT")
    private String swift;

    @Column(name = "CUENTA_VALIDA")
    private String cuentaValida;

    @Column(name = "CONTACTO")
    private String contacto;

    @Column(name = "ID_REFERENCIA")
    private String idReferencia;
    
    @Column(name = "CLASIFICACION1")
    private String clasificacion1;
    
    @Column(name = "CLASIFICACION2")
    private String clasificacion2;
    
    
    @Column(name = "CADENA_VERIFIC")
    private String cadenaVerific;
    
    @Column(name = "CORREO2")
    private String correo2;
    
    @Column(name = "BANCO_DOLARES")
    private String bancoDolares;
    
    @Column(name = "NUM_CUENTA_DOLARES")
    private String numCuentaDolares;
    
    @Column(name = "CUENTA_CLABE_DOLARES")
    private String cuentaClabeDolares;
    
    @Column(name = "CUENTA_VALIDA_DOLARES")
    private String cuentaValidaDolares;
    
    @Column(name = "BANCO_EXTRANJERO")
    private String bancoExtranjero;
    
    @Column(name = "DIR_BANCO_EXTRANJERO")
    private String dirBancoExtranjero;
    
    @Column(name = "NUM_CTA_EXTRANJERA")
    private String numCtaExtranjera;
    
    @Column(name = "DIR_BENEFICIARIO_EXT")
    private String dirBeneficiarioExt;
    
    @Column(name = "CVE_PROVEEDOR")
    private String cveProveedor;
    
    @Column(name = "URL_IMAGEN")
    private String urlImagen;
    
    @Column(name = "DIRECCION")
    private String direccion;
    
    @Column(name = "CIUDAD")
    private String ciudad;
    
    @Column(name = "ESTADO")
    private String estado;
    
    @Column(name = "PAIS")
    private String pais;
    
    @Column(name = "CP")
    private String cp;
    
    @Column(name = "T_REGIMEN")
    private String tRegimen;
    
    @Column(name = "COLONIA")
    private String colonia;
    
    @Column(name = "CALLE")
    private String calle;
    
    @Column(name = "ID_FAMILIA")
    private Integer idFamilia;
    
    @Column(name = "CTA_CONTABLE_DOLARES")
    private String ctaContableDolares;
    
    @Column(name = "CTA_IVA_DOLARES")
    private String ctaIvaDolares;
    
    @Column(name = "CTA_IVA_COMPLEMENTARIA")
    private String ctaIvaComplementaria;
    
    @Column(name = "CUENTA_POR_COMPROBAR")
    private String cuentaPorComprobar;
    
    @Column(name = "CUENTA_POR_COMP_COMPL")
    private String cuentaPorCompComp;
    
    @Column(name = "CUENTA_POR_COMPR_DLLS")
    private String cuentaPorComprDlls;
    
    @Column(name = "CUENTA_SIN_CFDI")
    private String cuentaSinCfdi;
    
    @Column(name = "CUENTA_COMPL_SIN_CFDI")
    private String cuentaComplSinCfdi;
    
    @Column(name = "CUENTA_TARJETA")
    private String cuentaTarjeta;
    
    @Column(name = "NOMBRE_1")
    private String nombre1;
    
    @Column(name = "APELLIDO_PATERNO")
    private String apellidoPaterno;
    
    @Column(name = "APELLIDO_MATERNO")
    private String apellidoMaterno;
    
    @Column(name = "SEGMENTACION")
    private Integer segmentacion;
    
    @Column(name = "CUENTA_IVA_PAGADO_DLLS")
    private String cuentaIvaPagadoDlls;
    
    @Column(name = "CUENTA_IVA_PAGADO_DLLS_COMPL")
    private String cuentaIvaPagadoDllsCompl;
    
    @Column(name = "CUENTA_SIN_CFDI_X_MONEDA")
    private String cuentaSinCfdiXMoneda;
    
    @Column(name = "CUENTA_SIN_CFDI_X_MONEDA_COMPL")
    private String cuentaSinCfdiXMonedaCompl;
    
    @Column(name = "MONEDA_SIN_CFDI")
    private String monedaSinCfdi;
    
    @Column(name = "FLAG_IMP6")
    private String flagImp6;
    
    @Column(name = "PAGO_A_TERCEROS")
    private String pagoATerceros;

     @Column(name = "TAX_ID")        
    private String taxId;
     
     @Column(name = "CNPJ")        
    private String cnpj;
     
     @Column(name = "AGENCIA")        
    private String agencia;
     
     @Column(name = "CUENTA_CORRIENTE")        
    private String cuentaCorriente;
     
     @Column(name = "BANCO_BRASIL")        
    private String bancoBrasil;
     
     
    
  
    public ErpCClientes() {

    }

    public ErpCClientes(ErpCClientesId id) {

        this.id = id;

    }

    public String getTaxId() {
        return taxId;
    }

    public void setTaxId(String taxId) {
        this.taxId = taxId;
    }

    
    
    
    public ErpCClientesId getId() {
        return id;
    }

    public void setId(ErpCClientesId id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getRfc() {
        return rfc;
    }

    public void setRfc(String rfc) {
        this.rfc = rfc;
    }

    public String getActEconomica() {
        return actEconomica;
    }

    public void setActEconomica(String actEconomica) {
        this.actEconomica = actEconomica;
    }

    public Date getfAlta() {
        return fAlta;
    }

    public void setfAlta(Date fAlta) {
        this.fAlta = fAlta;
    }

    public String getRazonSocial() {
        return razonSocial;
    }

    public void setRazonSocial(String razonSocial) {
        this.razonSocial = razonSocial;
    }

    public String getPapeleria() {
        return papeleria;
    }

    public void setPapeleria(String papeleria) {
        this.papeleria = papeleria;
    }

    public String gettPersona() {
        return tPersona;
    }

    public void settPersona(String tPersona) {
        this.tPersona = tPersona;
    }

    public String gettClieprov() {
        return tClieprov;
    }

    public void settClieprov(String tClieprov) {
        this.tClieprov = tClieprov;
    }

    public String gettTercero() {
        return tTercero;
    }

    public void settTercero(String tTercero) {
        this.tTercero = tTercero;
    }

    public String gettOperacion() {
        return tOperacion;
    }

    public void settOperacion(String tOperacion) {
        this.tOperacion = tOperacion;
    }

    public String getIdFiscal() {
        return idFiscal;
    }

    public void setIdFiscal(String idFiscal) {
        this.idFiscal = idFiscal;
    }

    public String getNombreExtranjero() {
        return nombreExtranjero;
    }

    public void setNombreExtranjero(String nombreExtranjero) {
        this.nombreExtranjero = nombreExtranjero;
    }

    public String getPaisResidencia() {
        return paisResidencia;
    }

    public void setPaisResidencia(String paisResidencia) {
        this.paisResidencia = paisResidencia;
    }

    public String getNacionalidad() {
        return nacionalidad;
    }

    public void setNacionalidad(String nacionalidad) {
        this.nacionalidad = nacionalidad;
    }

    public String getFormaPago() {
        return formaPago;
    }

    public void setFormaPago(String formaPago) {
        this.formaPago = formaPago;
    }

    public String getBanco() {
        return banco;
    }

    public void setBanco(String banco) {
        this.banco = banco;
    }

    public String getNumeroCuenta() {
        return numeroCuenta;
    }

    public void setNumeroCuenta(String numeroCuenta) {
        this.numeroCuenta = numeroCuenta;
    }

    public String getCuentaClave() {
        return cuentaClave;
    }

    public void setCuentaClave(String cuentaClave) {
        this.cuentaClave = cuentaClave;
    }

    public BigDecimal getSaldoInicial() {
        return saldoInicial;
    }

    public void setSaldoInicial(BigDecimal saldoInicial) {
        this.saldoInicial = saldoInicial;
    }

    public Integer getDiasCredito() {
        return diasCredito;
    }

    public void setDiasCredito(Integer diasCredito) {
        this.diasCredito = diasCredito;
    }

    public String getConceptoDefault() {
        return conceptoDefault;
    }

    public void setConceptoDefault(String conceptoDefault) {
        this.conceptoDefault = conceptoDefault;
    }

    public String getAuxiliar() {
        return auxiliar;
    }

    public void setAuxiliar(String auxiliar) {
        this.auxiliar = auxiliar;
    }

    public String getCuenta() {
        return cuenta;
    }

    public void setCuenta(String cuenta) {
        this.cuenta = cuenta;
    }

    public String getCie() {
        return cie;
    }

    public void setCie(String cie) {
        this.cie = cie;
    }

    public BigDecimal getPagoCie() {
        return pagoCie;
    }

    public void setPagoCie(BigDecimal pagoCie) {
        this.pagoCie = pagoCie;
    }

    public String getReferenciaCie() {
        return referenciaCie;
    }

    public void setReferenciaCie(String referenciaCie) {
        this.referenciaCie = referenciaCie;
    }

    public String getCuentaIva() {
        return cuentaIva;
    }

    public void setCuentaIva(String cuentaIva) {
        this.cuentaIva = cuentaIva;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getTipoPoliza() {
        return tipoPoliza;
    }

    public void setTipoPoliza(String tipoPoliza) {
        this.tipoPoliza = tipoPoliza;
    }

    public String getBancoPago() {
        return bancoPago;
    }

    public void setBancoPago(String bancoPago) {
        this.bancoPago = bancoPago;
    }

    public String getEstatus() {
        return estatus;
    }

    public void setEstatus(String estatus) {
        this.estatus = estatus;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getViatico() {
        return viatico;
    }

    public void setViatico(String viatico) {
        this.viatico = viatico;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String gettOpearcionDiot() {
        return tOpearcionDiot;
    }

    public void settOpearcionDiot(String tOpearcionDiot) {
        this.tOpearcionDiot = tOpearcionDiot;
    }

    public String getCuentaGasto() {
        return cuentaGasto;
    }

    public void setCuentaGasto(String cuentaGasto) {
        this.cuentaGasto = cuentaGasto;
    }

    public String getCuentaIvaPago() {
        return cuentaIvaPago;
    }

    public void setCuentaIvaPago(String cuentaIvaPago) {
        this.cuentaIvaPago = cuentaIvaPago;
    }

    public String getCuentaComplementaria() {
        return cuentaComplementaria;
    }

    public void setCuentaComplementaria(String cuentaComplementaria) {
        this.cuentaComplementaria = cuentaComplementaria;
    }

    public String getCtoDefault() {
        return ctoDefault;
    }

    public void setCtoDefault(String ctoDefault) {
        this.ctoDefault = ctoDefault;
    }

    public String getTipoCuenta() {
        return tipoCuenta;
    }

    public void setTipoCuenta(String tipoCuenta) {
        this.tipoCuenta = tipoCuenta;
    }

    public String getActivacion() {
        return activacion;
    }

    public void setActivacion(String activacion) {
        this.activacion = activacion;
    }

    public String getIban() {
        return iban;
    }

    public void setIban(String iban) {
        this.iban = iban;
    }

    public String getSwift() {
        return swift;
    }

    public void setSwift(String swift) {
        this.swift = swift;
    }

    public String getCuentaValida() {
        return cuentaValida;
    }

    public void setCuentaValida(String cuentaValida) {
        this.cuentaValida = cuentaValida;
    }

    public String getContacto() {
        return contacto;
    }

    public void setContacto(String contacto) {
        this.contacto = contacto;
    }

    public String getIdReferencia() {
        return idReferencia;
    }

    public void setIdReferencia(String idReferencia) {
        this.idReferencia = idReferencia;
    }

    public String getClasificacion1() {
        return clasificacion1;
    }

    public void setClasificacion1(String clasificacion1) {
        this.clasificacion1 = clasificacion1;
    }

    public String getClasificacion2() {
        return clasificacion2;
    }

    public void setClasificacion2(String clasificacion2) {
        this.clasificacion2 = clasificacion2;
    }

    public String getCadenaVerific() {
        return cadenaVerific;
    }

    public void setCadenaVerific(String cadenaVerific) {
        this.cadenaVerific = cadenaVerific;
    }

    public String getCorreo2() {
        return correo2;
    }

    public void setCorreo2(String correo2) {
        this.correo2 = correo2;
    }

    public String getBancoDolares() {
        return bancoDolares;
    }

    public void setBancoDolares(String bancoDolares) {
        this.bancoDolares = bancoDolares;
    }

    public String getNumCuentaDolares() {
        return numCuentaDolares;
    }

    public void setNumCuentaDolares(String numCuentaDolares) {
        this.numCuentaDolares = numCuentaDolares;
    }

    public String getCuentaClabeDolares() {
        return cuentaClabeDolares;
    }

    public void setCuentaClabeDolares(String cuentaClabeDolares) {
        this.cuentaClabeDolares = cuentaClabeDolares;
    }

    public String getCuentaValidaDolares() {
        return cuentaValidaDolares;
    }

    public void setCuentaValidaDolares(String cuentaValidaDolares) {
        this.cuentaValidaDolares = cuentaValidaDolares;
    }

    public String getBancoExtranjero() {
        return bancoExtranjero;
    }

    public void setBancoExtranjero(String bancoExtranjero) {
        this.bancoExtranjero = bancoExtranjero;
    }

    public String getDirBancoExtranjero() {
        return dirBancoExtranjero;
    }

    public void setDirBancoExtranjero(String dirBancoExtranjero) {
        this.dirBancoExtranjero = dirBancoExtranjero;
    }

    public String getNumCtaExtranjera() {
        return numCtaExtranjera;
    }

    public void setNumCtaExtranjera(String numCtaExtranjera) {
        this.numCtaExtranjera = numCtaExtranjera;
    }

    public String getDirBeneficiarioExt() {
        return dirBeneficiarioExt;
    }

    public void setDirBeneficiarioExt(String dirBeneficiarioExt) {
        this.dirBeneficiarioExt = dirBeneficiarioExt;
    }

    public String getCveProveedor() {
        return cveProveedor;
    }

    public void setCveProveedor(String cveProveedor) {
        this.cveProveedor = cveProveedor;
    }

    public String getCp() {
        return cp;
    }

    public void setCp(String cp) {
        this.cp = cp;
    }

    public String gettRegimen() {
        return tRegimen;
    }

    public void settRegimen(String tRegimen) {
        this.tRegimen = tRegimen;
    }

    public String getUrlImagen() {
        return urlImagen;
    }

    public void setUrlImagen(String urlImagen) {
        this.urlImagen = urlImagen;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getCiudad() {
        return ciudad;
    }

    public void setCiudad(String ciudad) {
        this.ciudad = ciudad;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getPais() {
        return pais;
    }

    public void setPais(String pais) {
        this.pais = pais;
    }

    public String getColonia() {
        return colonia;
    }

    public void setColonia(String colonia) {
        this.colonia = colonia;
    }

    public String getCalle() {
        return calle;
    }

    public void setCalle(String calle) {
        this.calle = calle;
    }

    public Integer getIdFamilia() {
        return idFamilia;
    }

    public void setIdFamilia(Integer idFamilia) {
        this.idFamilia = idFamilia;
    }

    public String getCtaContableDolares() {
        return ctaContableDolares;
    }

    public void setCtaContableDolares(String ctaContableDolares) {
        this.ctaContableDolares = ctaContableDolares;
    }

    public String getCtaIvaDolares() {
        return ctaIvaDolares;
    }

    public void setCtaIvaDolares(String ctaIvaDolares) {
        this.ctaIvaDolares = ctaIvaDolares;
    }

    public String getCtaIvaComplementaria() {
        return ctaIvaComplementaria;
    }

    public void setCtaIvaComplementaria(String ctaIvaComplementaria) {
        this.ctaIvaComplementaria = ctaIvaComplementaria;
    }

    public String getCuentaPorComprobar() {
        return cuentaPorComprobar;
    }

    public void setCuentaPorComprobar(String cuentaPorComprobar) {
        this.cuentaPorComprobar = cuentaPorComprobar;
    }


    public String getCuentaPorCompComp() {
        return cuentaPorCompComp;
    }

    public void setCuentaPorCompComp(String cuentaPorCompComp) {
        this.cuentaPorCompComp = cuentaPorCompComp;
    }

    public String getCuentaPorComprDlls() {
        return cuentaPorComprDlls;
    }

    public void setCuentaPorComprDlls(String cuentaPorComprDlls) {
        this.cuentaPorComprDlls = cuentaPorComprDlls;
    }

    public String getCuentaSinCfdi() {
        return cuentaSinCfdi;
    }

    public void setCuentaSinCfdi(String cuentaSinCfdi) {
        this.cuentaSinCfdi = cuentaSinCfdi;
    }

    public String getCuentaComplSinCfdi() {
        return cuentaComplSinCfdi;
    }

    public void setCuentaComplSinCfdi(String cuentaComplSinCfdi) {
        this.cuentaComplSinCfdi = cuentaComplSinCfdi;
    }

    public String getCuentaTarjeta() {
        return cuentaTarjeta;
    }

    public void setCuentaTarjeta(String cuentaTarjeta) {
        this.cuentaTarjeta = cuentaTarjeta;
    }

    public String getNombre1() {
        return nombre1;
    }

    public void setNombre1(String nombre1) {
        this.nombre1 = nombre1;
    }

    public String getApellidoPaterno() {
        return apellidoPaterno;
    }

    public void setApellidoPaterno(String apellidoPaterno) {
        this.apellidoPaterno = apellidoPaterno;
    }

    public String getApellidoMaterno() {
        return apellidoMaterno;
    }

    public void setApellidoMaterno(String apellidoMaterno) {
        this.apellidoMaterno = apellidoMaterno;
    }

    public Integer getSegmentacion() {
        return segmentacion;
    }

    public void setSegmentacion(Integer segmentacion) {
        this.segmentacion = segmentacion;
    }

    public String getCuentaIvaPagadoDlls() {
        return cuentaIvaPagadoDlls;
    }

    public void setCuentaIvaPagadoDlls(String cuentaIvaPagadoDlls) {
        this.cuentaIvaPagadoDlls = cuentaIvaPagadoDlls;
    }

    public String getCuentaIvaPagadoDllsCompl() {
        return cuentaIvaPagadoDllsCompl;
    }

    public void setCuentaIvaPagadoDllsCompl(String cuentaIvaPagadoDllsCompl) {
        this.cuentaIvaPagadoDllsCompl = cuentaIvaPagadoDllsCompl;
    }

    public String getCuentaSinCfdiXMoneda() {
        return cuentaSinCfdiXMoneda;
    }

    public void setCuentaSinCfdiXMoneda(String cuentaSinCfdiXMoneda) {
        this.cuentaSinCfdiXMoneda = cuentaSinCfdiXMoneda;
    }

    public String getCuentaSinCfdiXMonedaCompl() {
        return cuentaSinCfdiXMonedaCompl;
    }

    public void setCuentaSinCfdiXMonedaCompl(String cuentaSinCfdiXMonedaCompl) {
        this.cuentaSinCfdiXMonedaCompl = cuentaSinCfdiXMonedaCompl;
    }

    public String getMonedaSinCfdi() {
        return monedaSinCfdi;
    }

    public void setMonedaSinCfdi(String monedaSinCfdi) {
        this.monedaSinCfdi = monedaSinCfdi;
    }

    public String getFlagImp6() {
        return flagImp6;
    }

    public void setFlagImp6(String flagImp6) {
        this.flagImp6 = flagImp6;
    }

    public String getPagoATerceros() {
        return pagoATerceros;
    }

    public void setPagoATerceros(String pagoATerceros) {
        this.pagoATerceros = pagoATerceros;
    }

    public String getCnpj() {
        return cnpj;
    }

    public void setCnpj(String cnpj) {
        this.cnpj = cnpj;
    }

    public String getAgencia() {
        return agencia;
    }

    public void setAgencia(String agencia) {
        this.agencia = agencia;
    }

    public String getCuentaCorriente() {
        return cuentaCorriente;
    }

    public void setCuentaCorriente(String cuentaCorriente) {
        this.cuentaCorriente = cuentaCorriente;
    }

    public String getBancoBrasil() {
        return bancoBrasil;
    }

    public void setBancoBrasil(String bancoBrasil) {
        this.bancoBrasil = bancoBrasil;
    }

   
    
    
    
    

    @Override
    public String toString() {
        return "ErpCClientes{" + "id=" + id + ", nombre=" + nombre + ", rfc=" + rfc + ", actEconomica=" + actEconomica + ", fAlta=" + fAlta + ", razonSocial=" + razonSocial + ", papeleria=" + papeleria + ", tPersona=" + tPersona + ", tClieprov=" + tClieprov + ", tTercero=" + tTercero + ", tOperacion=" + tOperacion + ", idFiscal=" + idFiscal + ", nombreExtranjero=" + nombreExtranjero + ", paisResidencia=" + paisResidencia + ", nacionalidad=" + nacionalidad + ", formaPago=" + formaPago + ", banco=" + banco + ", numeroCuenta=" + numeroCuenta + ", cuentaClave=" + cuentaClave + ", saldoInicial=" + saldoInicial + ", diasCredito=" + diasCredito + ", conceptoDefault=" + conceptoDefault + ", auxiliar=" + auxiliar + ", tOpearcionDiot=" + tOpearcionDiot + ", cuenta=" + cuenta + ", cie=" + cie + ", pagoCie=" + pagoCie + ", referenciaCie=" + referenciaCie + ", cuentaIva=" + cuentaIva + ", correo=" + correo + ", telefono=" + telefono + ", tipoPoliza=" + tipoPoliza + ", bancoPago=" + bancoPago + ", estatus=" + estatus + ", password=" + password + ", viatico=" + viatico + ", usuario=" + usuario + ", cuentaGasto=" + cuentaGasto + ", cuentaIvaPago=" + cuentaIvaPago + ", cuentaComplementaria=" + cuentaComplementaria + ", ctoDefault=" + ctoDefault + ", tipoCuenta=" + tipoCuenta + ", activacion=" + activacion + ", iban=" + iban + ", swift=" + swift + ", cuentaValida=" + cuentaValida + ", contacto=" + contacto + ", idReferencia=" + idReferencia + ", clasificacion1=" + clasificacion1 + ", clasificacion2=" + clasificacion2 + ", cadenaVerific=" + cadenaVerific + '}';
    }

  

}
