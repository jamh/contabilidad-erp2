/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.feraz.contabilidad.sat.electronica.process;

import com.feraz.contabilidad.sat.transacciones.dao.DetPolizasXTransaccionDao;
import com.feraz.contabilidad.sat.transacciones.dao.ErpSatTransaccionDao;
import com.feraz.contabilidad.sat.transacciones.model.DetPolizasXTransaccion;
import com.feraz.contabilidad.sat.transacciones.model.DetPolizasXTransaccionId;
import com.feraz.contabilidad.sat.transacciones.model.ErpSatTransaccion;
import com.feraz.contabilidad.sat.transacciones.model.ErpSatTransaccionId;
import com.feraz.cxp.dao.ErpSeguiDocumentosDao;
import com.feraz.cxp.dto.PagosDTO;
import com.feraz.cxp.model.ErpSeguiDocumentos;
import com.feraz.cxp.model.ErpSeguiDocumentosId;
import com.feraz.facturas.webcontrolfe.dao1.ErpFeComprobantesDao;
import com.feraz.facturas.webcontrolfe.model.ErpFeComprobantes;
import com.feraz.facturas.webcontrolfe.model.ErpFeComprobantesId;
import com.feraz.polizas3.dao.ErpPolizasXFacturasDao;
import com.feraz.polizas3.dao.ErpPolizasXPagosDao;
import com.feraz.polizas3.model.ErpPolizasXFacturas;
import com.feraz.polizas3.model.ErpPolizasXFacturasId;
import com.feraz.polizas3.model.ErpPolizasXPagos;
import com.feraz.polizas3.model.ErpPolizasXPagosId;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.apache.log4j.Logger;
import org.jamh.data.process.ProcessDao;

/**
 *
 * @author vavi
 */
public class GeneraContaElectronica {
    
    
    private ProcessDao processDao;
    private ErpPolizasXFacturasDao erpPolizasXFacturasDao;
    private ErpPolizasXPagosDao erpPolizasXPagosDao;
    private ErpSatTransaccionDao erpSatTransaccionDao;
    private DetPolizasXTransaccionDao detPolizasXTransaccionDao;
    private ErpSeguiDocumentosDao erpSeguiDocumentosDao;
    private ErpFeComprobantesDao erpFeComprobantesDao;
    
    
    private static final Logger log = Logger.getLogger(GeneraContaElectronica.class);
    
    
        
    public boolean generaRelacion(String compania,int numero,String numeroPol,String fechaPol,String tipoPol){
        
             SimpleDateFormat formatFecha = new SimpleDateFormat("dd/MM/yyyy");
             
             
             log.debug("-----------------Entrando a generar nueva relacion------------------");
             log.debug("compania : "+compania);
             log.debug("numeroPol: "+numeroPol);
             log.debug("fechaPol : "+fechaPol);
             log.debug("tipoPol  : "+tipoPol);
             log.debug("numeroFe : "+numero);
          
        
                 ErpPolizasXFacturas polizas = new ErpPolizasXFacturas();
                 ErpPolizasXFacturasId polizasId = new ErpPolizasXFacturasId();
                 
                 ErpPolizasXPagos polizasPagos = new ErpPolizasXPagos();
                 ErpPolizasXPagosId polizasPagosId = new ErpPolizasXPagosId();
                 
                 ErpPolizasXPagosId idPago = null;
                 ErpPolizasXFacturasId idFacturas = null;
                 
                 boolean result = false;
                 
                 SimpleDateFormat formatoDelTexto2 = new SimpleDateFormat("dd/MM/yyyy");
                 
    
           try {
            
            
      
        
               log.debug("Buscando tipo de polizas sat...");
      
               Map tipoPolSatMap = new HashMap();
              //String rfcComp = "";
              tipoPolSatMap.put("compania", compania);
              tipoPolSatMap.put("tipoPoliza", tipoPol);
                    
              List listTipoPolSat= processDao.getMapResult("BuscaTipoPolSat", tipoPolSatMap);
              
                  String tipoSat = "";
              
               if (listTipoPolSat != null){
              
              
                  Map secTipoPolSat = (HashMap) listTipoPolSat.get(0);
                   
                  tipoSat = secTipoPolSat.get("TIPO_SAT").toString();
                 
               }else{
               
                    log.error("Error. No se encontro tipo de polizas sat.");
                    return false;
               
               }
                 
                    Map docCfdiMap = new HashMap();
                    //String rfcComp = "";
                    docCfdiMap.put("compania", compania);
                    docCfdiMap.put("numero", numero);
              
              
              
             log.debug("Buscando Datos de CFDI...");
                    
              List listDocCfdi= processDao.getMapResult("BuscaDatCFDI", docCfdiMap);
              
              
              
              
              String folio = "";
              String uudi = "";
              String rfcEmisor = "";
              String rfcReceptor = "";
              String tipoCambio = "";
              String moneda = "";
              String total = "";
                  
                
                if(listDocCfdi != null){
                        Map secListDocCfdi = (HashMap) listDocCfdi.get(0);
                        if (secListDocCfdi.get("FOLIO") != null){
                            folio = secListDocCfdi.get("FOLIO").toString();
                        }
                        uudi = secListDocCfdi.get("UUID").toString();
                        //rfcEmisor = secListDocCfdi.get("RFC_EMISOR").toString();
                        //rfcReceptor = secListDocCfdi.get("RFC_RECEPTOR").toString();
                        //tipoCambio = secListDocCfdi.get("TIPO_CAMBIO").toString();
                        //moneda = secListDocCfdi.get("MONEDA").toString();
                        //total = secListDocCfdi.get("TOTAL").toString();
                 
                 
                }else{
                    
                    
                    log.error("Error. Datos de CFDI no encontrados.");
                    return false;
                }

                   
                 if(tipoSat.equalsIgnoreCase("I") || tipoSat.equalsIgnoreCase("E")){
                        polizasPagosId.setCompania(compania);
                        polizasPagosId.setFechaPol(formatoDelTexto2.parse(fechaPol));
                        polizasPagosId.setNumeroPol(numeroPol);
                        polizasPagosId.setTipoPol(tipoPol);
                        polizasPagos.setId(polizasPagosId);
                        polizasPagos.setFolio(folio);
                        polizasPagos.setNumeroFe(String.valueOf(numero));
                        polizasPagos.setUuid(uudi);
                        polizasPagos.setOperacion("GE");



                       idPago = erpPolizasXPagosDao.save(polizasPagos);
                       
                       if (idPago == null){
                           
                         log.error("Error. Error al relacionar PolizaXPagos.");
                       
                         result = false;
                         return result;
                         
                       }else{
                       
                          result = true;
                       }
                       
               }else{
                           
                           polizasId.setCompania(compania);
                            polizasId.setFechaPol(formatoDelTexto2.parse(fechaPol));
                            polizasId.setNumeroPol(numeroPol);
                            polizasId.setOperacion("GE");
                            polizasId.setTipoPol(tipoPol);
                            polizas.setId(polizasId);
                            polizas.setFolio(folio);
                            polizas.setNumeroFe(numero);
                            polizas.setUuid(uudi);



                           idFacturas = erpPolizasXFacturasDao.save(polizas);
                           
                           
                           if (idFacturas == null){
                       
                                log.error("Error. Error al relacionar PolizaXFacturas.");
                       
                                result = false;
                                return result;
                               
                              }else{
                               
                               

                                 result = true;
                                 
                                 
                                 
                                 
                              }

                           
                           
                        }
                
                 
                
                if(tipoSat.equalsIgnoreCase("I")){
                    
                    
                      log.debug("Generando Transaccion de Cobranza");
                    
                      generaTransaccionCobranza(compania,numero,numeroPol,fechaPol,tipoPol,listDocCfdi);
                      
                }
                
                if(tipoSat.equalsIgnoreCase("E")){
                    
                    log.debug("Generando Transaccion de Pago");
                    
                    generaTransaccionPago(compania,numero,numeroPol,fechaPol,tipoPol,listDocCfdi);
                    
                }
                if(tipoSat.equalsIgnoreCase("D")){
                    
                    log.debug("Generando Transaccion");
                
                    boolean resultTr = generaTransaccion(compania,numero,numeroPol,fechaPol,tipoPol,listDocCfdi);
                
                
                }
                 
             return result;
                
                
               
        } catch (Exception e) {
            
            e.printStackTrace();
            log.error("Error. Error al generar la relacion");
            return false;
        }

    
    
    
    }
    
    
        public boolean generaTransaccion(String compania,int numero,String numeroPol,String fechaPol,String tipoPol,List listDocCfdi){
        
      
                 SimpleDateFormat formatoDelTexto2 = new SimpleDateFormat("dd/MM/yyyy");
                 
    
           try {
            
          
              String folio = "";
              String uudi = "";
              String rfcEmisor = "";
              String rfcReceptor = "";
              String tipoCambio = "";
              String moneda = "";
              String total = "";
                  
                
                if(listDocCfdi != null){
                        Map secListDocCfdi = (HashMap) listDocCfdi.get(0);
                        
                        if(secListDocCfdi.get("FOLIO")!= null){
                            folio = secListDocCfdi.get("FOLIO").toString();
                        }
                        uudi = secListDocCfdi.get("UUID").toString();
                        rfcEmisor = secListDocCfdi.get("RFC_EMISOR").toString();
                        rfcReceptor = secListDocCfdi.get("RFC_RECEPTOR").toString();
                        if(secListDocCfdi.get("TIPO_CAMBIO")!=null){
                             tipoCambio = secListDocCfdi.get("TIPO_CAMBIO").toString();
                        }
                        if (secListDocCfdi.get("MONEDA") !=null){
                               moneda = secListDocCfdi.get("MONEDA").toString();
                        }
                        total = secListDocCfdi.get("TOTAL").toString();
                 
                 
                }else{
                
                    return false;
                }

              ErpSatTransaccion erpTrans = new ErpSatTransaccion();
              ErpSatTransaccionId erpTransId = new ErpSatTransaccionId();
              
              erpTransId.setCompania(compania);
              int idTransaccion = erpSatTransaccionDao.getMaxId(erpTransId);
              erpTransId.setId(new BigDecimal(idTransaccion));
              erpTrans.setId(erpTransId);
              erpTrans.setTipo("CompNal");
              erpTrans.setUuidCfdi(uudi);
              
              
              Map rfcCompania = new HashMap();
              String rfcComp = "";
              rfcCompania.put("compania", compania);
               
              List listRfc = processDao.getMapResult("BuscaRFCCompania", rfcCompania);
              
                 if (listRfc == null){
                     
                     erpTrans.setRfc(rfcEmisor);

                     
                 }else{
                     
                     Map rfcCompa = (HashMap) listRfc.get(0);
                     
                     if (rfcCompa.get("RFC").toString().equalsIgnoreCase(rfcEmisor)){
                     
                         
                          erpTrans.setRfc(rfcReceptor);
                         
                     }else{
                     
                            erpTrans.setRfc(rfcEmisor);
                     
                     }
                     
                 }
                  
                 
                 erpTrans.setMontoTotal(new BigDecimal(total));
                 
                        Map mnd = new HashMap();
//                
                mnd.put("moneda", moneda);
               
                   List listMnd = processDao.getMapResult("BuscaMonedaCFDI", mnd);
                   
                  // System.out.println("listMnd"+listMnd);
                   
                   if (listMnd == null){
                       
                        erpTrans.setMoneda("MXN");
                        
                        
                 
                   
                   }else{
                       
                     //  System.out.println("get 0"+listMnd.get(0));
                       
                         Map mndM = (HashMap) listMnd.get(0);
                    
                      //  System.out.println("moneda sat "+mndM.get("MONEDA_SAT").toString());
                
                       if (mndM.get("MONEDA_SAT").toString().equalsIgnoreCase("1")){
                          erpTrans.setMoneda("MXN");
                       }
                       
                        if (mndM.get("MONEDA_SAT").toString().equalsIgnoreCase("2")){
                          erpTrans.setMoneda("USD");
                       }
                   
                   }
                   
                  
               
                   if (tipoCambio.equalsIgnoreCase("N/A") || tipoCambio == null || tipoCambio.isEmpty() || tipoCambio.equalsIgnoreCase("") ){
                     
//                     System.out.println("Valor 1 a tipo de cambio");
                      erpTrans.setTipCamb(new BigDecimal(1));
                 }else{
                     
                     
                     erpTrans.setTipCamb(new BigDecimal(tipoCambio));
                 }
              // 
              
              //  erpTrans.setMoneda("MXN");
              //  erpTrans.setTipCamb(new BigDecimal(1));
             
            
                 
                 
                 erpSatTransaccionDao.save(erpTrans);
                 
                     
                    Map secDetPol = new HashMap();
              //String rfcComp = "";
              secDetPol.put("compania", compania);
              secDetPol.put("tipo_poliza", tipoPol);
              secDetPol.put("fecha", fechaPol);
              secDetPol.put("numero", numeroPol);
              
               
              List listsecDetPol = processDao.getMapResult("BuscaSecDetPoliza", secDetPol);
              
           DetPolizasXTransaccion transaccion = new DetPolizasXTransaccion();
           DetPolizasXTransaccionId transaccionId = new DetPolizasXTransaccionId();
              DetPolizasXTransaccionId resultTrans = null;
              for (int j = 0; listsecDetPol.size()-1>= j;j++){
                  
                  Map secDetPolS = (HashMap) listsecDetPol.get(j);
                   
                 System.out.println("sedc: "+secDetPolS.get("SEC").toString());
                 
                  transaccionId.setCompania(compania);
                    transaccionId.setFecha(formatoDelTexto2.parse(fechaPol));
                    transaccionId.setTipoPoliza(tipoPol);
                    transaccionId.setNumero(numeroPol);
                    transaccionId.setSec(Integer.parseInt(secDetPolS.get("SEC").toString()));
                    transaccionId.setIdTransaccion(idTransaccion);
                    transaccion.setId(transaccionId);

                    resultTrans = detPolizasXTransaccionDao.save(transaccion);
            
              }
               
                 if (resultTrans == null) {
                     
                     log.error("Error al relacionar transaccion con detalle de poliza.");
            
                    return false;
                    
                } else {
                     log.debug("Transaccion correctamente generada.");
                    return true;
                    
                }
                
                
               
        } catch (Exception e) {
            
            e.printStackTrace();
            log.error("Error al generar transaccion.");
            return false;
        }

    
    
    
    }
    
    
    public boolean generaTransaccionCobranza(String compania,int numero,String numeroPol,String fechaPol,String tipoPol,List listDocCfdi){
        
      
                 SimpleDateFormat formatoDelTexto2 = new SimpleDateFormat("dd/MM/yyyy");
                 
    
           try {
            
          
              String folio = "";
              String uudi = "";
              String rfcEmisor = "";
              String rfcReceptor = "";
              String tipoCambio = "";
              String moneda = "";
              String total = "";
                  
                
                if(listDocCfdi != null){
                        Map secListDocCfdi = (HashMap) listDocCfdi.get(0);
                        
                        if(secListDocCfdi.get("FOLIO")!= null){
                            folio = secListDocCfdi.get("FOLIO").toString();
                        }
                        uudi = secListDocCfdi.get("UUID").toString();
                        rfcEmisor = secListDocCfdi.get("RFC_EMISOR").toString();
                        rfcReceptor = secListDocCfdi.get("RFC_RECEPTOR").toString();
                        if(secListDocCfdi.get("TIPO_CAMBIO")!=null){
                             tipoCambio = secListDocCfdi.get("TIPO_CAMBIO").toString();
                        }
                        if (secListDocCfdi.get("MONEDA") !=null){
                               moneda = secListDocCfdi.get("MONEDA").toString();
                        }
                        total = secListDocCfdi.get("TOTAL").toString();
                 
                 
                }else{
                
                    return false;
                }
                
                
                log.debug("Generando Transaccion de cobro...");
                
    //****************Generando Transaccion de cobro*******************
                
              Map mapDatBancos = new HashMap();
             
              mapDatBancos.put("compania", compania);
              mapDatBancos.put("numeroPol", numeroPol);
              mapDatBancos.put("fechaPol", fechaPol);
              mapDatBancos.put("tipoPol", tipoPol);
              mapDatBancos.put("numero", numero);
              
              
              log.debug("Buscando cuenta banco...");
               
              List listDatBanc= processDao.getMapResult("BuscaCuentaBanco", mapDatBancos);
              
              if(listDatBanc == null){
                  
                  log.error("Cuenta banco no encontrada");
              
                   return false;
              }
              
              log.debug("Cuenta banco encontrada, generando datos...");
              
            for(int i = 0;i<listDatBanc.size();i++){     
   
                Map datosCobro = (HashMap) listDatBanc.get(i);
                
                if(datosCobro.get("CUENTA_BANCO") != null && datosCobro.get("BANCO_SAT") != null){
                    ErpSatTransaccion erpTrans = new ErpSatTransaccion();
                    ErpSatTransaccionId erpTransId = new ErpSatTransaccionId();

                    erpTransId.setCompania(compania);
                    int idTransaccion = erpSatTransaccionDao.getMaxId(erpTransId);
                    erpTransId.setId(new BigDecimal(idTransaccion));
                    erpTrans.setId(erpTransId);
                    erpTrans.setTipo("Transferen");
                    erpTrans.setUuidCfdi(uudi);
                    erpTrans.setFecha(formatoDelTexto2.parse(fechaPol));
                    erpTrans.setCtaDest(datosCobro.get("CUENTA_BANCO").toString());
                    erpTrans.setBancoDestNal(datosCobro.get("BANCO_SAT").toString());
                   
                    
                    BigDecimal cargos = new BigDecimal(datosCobro.get("CARGOS").toString());
                    BigDecimal abonos = new BigDecimal(datosCobro.get("ABONOS").toString());
                    
                    
                    if(cargos.compareTo(new BigDecimal(0)) == 0){
                    
                        erpTrans.setMontoTotal(abonos);
                    
                    }else{
                    
                        erpTrans.setMontoTotal(cargos);
                    
                    }
                    

                    Map rfcCompania = new HashMap();
                  String rfcComp = "";
                  rfcCompania.put("compania", compania);

                  List listRfc = processDao.getMapResult("BuscaRFCCompania", rfcCompania);

                     if (listRfc == null){

                         erpTrans.setRfc(rfcEmisor);


                     }else{

                         Map rfcCompa = (HashMap) listRfc.get(0);

                         if (rfcCompa.get("RFC").toString().equalsIgnoreCase(rfcEmisor)){


                              erpTrans.setRfc(rfcReceptor);
                               erpTrans.setBenef(datosCobro.get("BENEF").toString());

                         }else{

                                erpTrans.setRfc(rfcEmisor);
                                 erpTrans.setBenef(datosCobro.get("BENEF_EMISOR").toString());

                         }

                     }


                     erpTrans.setMontoTotal(new BigDecimal(total));

                            Map mnd = new HashMap();
    //                
                    mnd.put("moneda", moneda);

                       List listMnd = processDao.getMapResult("BuscaMonedaCFDI", mnd);

                      // System.out.println("listMnd"+listMnd);

                       if (listMnd == null){

                            erpTrans.setMoneda("MXN");




                       }else{

                         //  System.out.println("get 0"+listMnd.get(0));

                             Map mndM = (HashMap) listMnd.get(0);

                          //  System.out.println("moneda sat "+mndM.get("MONEDA_SAT").toString());

                           if (mndM.get("MONEDA_SAT").toString().equalsIgnoreCase("1")){
                              erpTrans.setMoneda("MXN");
                           }

                            if (mndM.get("MONEDA_SAT").toString().equalsIgnoreCase("2")){
                              erpTrans.setMoneda("USD");
                           }

                       }



                       if (tipoCambio.equalsIgnoreCase("N/A") || tipoCambio == null || tipoCambio.isEmpty() || tipoCambio.equalsIgnoreCase("") ){

    //                     System.out.println("Valor 1 a tipo de cambio");
                          erpTrans.setTipCamb(new BigDecimal(1));
                     }else{


                         erpTrans.setTipCamb(new BigDecimal(tipoCambio));
                     }
                       
                        erpSatTransaccionDao.save(erpTrans);
                 
                     
                        Map secDetPol = new HashMap();
                        //String rfcComp = "";
                        secDetPol.put("compania", compania);
                        secDetPol.put("tipo_poliza", tipoPol);
                        secDetPol.put("fecha", fechaPol);
                        secDetPol.put("numero", numeroPol);


                     //   List listsecDetPol = processDao.getMapResult("BuscaSecDetPoliza", secDetPol);

                     DetPolizasXTransaccion transaccion = new DetPolizasXTransaccion();
                     DetPolizasXTransaccionId transaccionId = new DetPolizasXTransaccionId();
                        DetPolizasXTransaccionId resultTrans = null;
                     //   for (int j = 0; listsecDetPol.size()-1>= j;j++){

                         //   Map secDetPolS = (HashMap) listsecDetPol.get(j);

                         //  System.out.println("sedc: "+secDetPolS.get("SEC").toString());

                            transaccionId.setCompania(compania);
                              transaccionId.setFecha(formatoDelTexto2.parse(fechaPol));
                              transaccionId.setTipoPoliza(tipoPol);
                              transaccionId.setNumero(numeroPol);
                              transaccionId.setSec(Integer.parseInt(datosCobro.get("SEC").toString()));
                              transaccionId.setIdTransaccion(idTransaccion);
                              transaccion.setId(transaccionId);

                              resultTrans = detPolizasXTransaccionDao.save(transaccion);
                              
                              
                              
                                  //**********************GENERA PAGO******************************************/
    
                       
                        ErpSeguiDocumentos segui = new ErpSeguiDocumentos(); 
                        ErpSeguiDocumentosId seguiId = new ErpSeguiDocumentosId(); 

                     
                         seguiId.setCompania(compania);
                         seguiId.setNumFe(numero);
                         seguiId.setOrigen("P");
                         seguiId.setSerie("A");
                         seguiId.settDoc("FE");
                         int sec = erpSeguiDocumentosDao.getMaxId(seguiId);
                //         System.out.println("pagos sec "+sec);
                         seguiId.setSec(sec);

                         segui.setId(seguiId);
                         segui.setBanco(datosCobro.get("BANCO_SAT").toString());
                        
                         segui.setFolio(numero);
                         
                         if(cargos.compareTo(new BigDecimal(0)) == 0){
                    
                                segui.setImporteOperacion(abonos);

                            }else{

                                segui.setImporteOperacion(cargos);

                            }
                    

                        
                         
                         //segui.setMoneda(ss.);
                         segui.setObservaciones("PAGO-"+folio);
                         segui.setParidad(new BigDecimal(1));
                         
                          if(cargos.compareTo(new BigDecimal(0)) == 0){
                    
                                segui.setTotPesos(abonos);

                            }else{

                                segui.setTotPesos(cargos);

                            }
                         
                         segui.setUsuario("pagoAuto");
                         segui.settOperacion("03");
                         segui.setCuentaBanco(datosCobro.get("CUENTA_BANCO").toString());
                         segui.setFechaPagoCxpFe(formatoDelTexto2.parse(fechaPol));
                         segui.setIdTransaccion(idTransaccion);
                         ErpSeguiDocumentosId result = erpSeguiDocumentosDao.save(segui);
                             
                             Map pagoTotal = new HashMap();
                
                        pagoTotal.put("compania", compania);
                        pagoTotal.put("numero", numero);

                            List listTotal = processDao.getMapResult("TotalesPagos", pagoTotal);

                             Map pag = (HashMap) listTotal.get(0);
                             BigDecimal pago = new BigDecimal(pag.get("PAGO").toString());
                             BigDecimal total2 = new BigDecimal(pag.get("TOTAL").toString());

                        //     System.out.println("buscando actualizar"+pago);
                        //     System.out.println("buscando actualizar"+total);

                             ErpFeComprobantes comp = new ErpFeComprobantes();
                             ErpFeComprobantesId compId = new ErpFeComprobantesId();

                             compId.setCompania(compania);
                             compId.setNumero(numero);
                             comp.setId(compId);

                            if (total2.compareTo(pago) == 1){

                                erpFeComprobantesDao.actualizaPago("PAR", pago, comp);


                            }

                             if (total2.compareTo(pago) == 0){

                                erpFeComprobantesDao.actualizaPago("PAG", pago, comp);


                            }

                              if (total2.compareTo(pago) == -1){

                                erpFeComprobantesDao.actualizaPago("PAG", pago, comp);


                            }
                              

                       // }

                         //  if (resultTrans == null) {

                         //     return false;

                         // } else {
                         //     return true;

                         // }



                }
            }
            
            log.debug("Transaccion generada exitosamente");
//                     
//                    rfcCompa.get("RFC").toString()
//                

//***********************Generando transaccion Compnal**************************
              log.debug("Generando transaccion Compnal...");
              
              ErpSatTransaccion erpTrans = new ErpSatTransaccion();
              ErpSatTransaccionId erpTransId = new ErpSatTransaccionId();
              
              erpTransId.setCompania(compania);
              int idTransaccion = erpSatTransaccionDao.getMaxId(erpTransId);
              erpTransId.setId(new BigDecimal(idTransaccion));
              erpTrans.setId(erpTransId);
              erpTrans.setTipo("CompNal");
              erpTrans.setUuidCfdi(uudi);
              
              
              Map rfcCompania = new HashMap();
              String rfcComp = "";
              rfcCompania.put("compania", compania);
               
              List listRfc = processDao.getMapResult("BuscaRFCCompania", rfcCompania);
              
                 if (listRfc == null){
                     
                     erpTrans.setRfc(rfcEmisor);

                     
                 }else{
                     
                     Map rfcCompa = (HashMap) listRfc.get(0);
                     
                     if (rfcCompa.get("RFC").toString().equalsIgnoreCase(rfcEmisor)){
                     
                         
                          erpTrans.setRfc(rfcReceptor);
                         
                     }else{
                     
                            erpTrans.setRfc(rfcEmisor);
                     
                     }
                     
                 }
                  
                 
                 erpTrans.setMontoTotal(new BigDecimal(total));
                 
                        Map mnd = new HashMap();
//                
                mnd.put("moneda", moneda);
               
                   List listMnd = processDao.getMapResult("BuscaMonedaCFDI", mnd);
                   
                  // System.out.println("listMnd"+listMnd);
                   
                   if (listMnd == null){
                       
                        erpTrans.setMoneda("MXN");
                        
                        
                 
                   
                   }else{
                       
                     //  System.out.println("get 0"+listMnd.get(0));
                       
                         Map mndM = (HashMap) listMnd.get(0);
                    
                      //  System.out.println("moneda sat "+mndM.get("MONEDA_SAT").toString());
                
                       if (mndM.get("MONEDA_SAT").toString().equalsIgnoreCase("1")){
                          erpTrans.setMoneda("MXN");
                       }
                       
                        if (mndM.get("MONEDA_SAT").toString().equalsIgnoreCase("2")){
                          erpTrans.setMoneda("USD");
                       }
                   
                   }
                   
                  
               
                   if (tipoCambio.equalsIgnoreCase("N/A") || tipoCambio == null || tipoCambio.isEmpty() || tipoCambio.equalsIgnoreCase("") ){
                     
//                     System.out.println("Valor 1 a tipo de cambio");
                      erpTrans.setTipCamb(new BigDecimal(1));
                 }else{
                     
                     
                     erpTrans.setTipCamb(new BigDecimal(tipoCambio));
                 }
              // 
              
              //  erpTrans.setMoneda("MXN");
              //  erpTrans.setTipCamb(new BigDecimal(1));
             
            
                 
                 
                 erpSatTransaccionDao.save(erpTrans);
                 
                     
                    Map secDetPol = new HashMap();
              //String rfcComp = "";
              secDetPol.put("compania", compania);
              secDetPol.put("tipo_poliza", tipoPol);
              secDetPol.put("fecha", fechaPol);
              secDetPol.put("numero", numeroPol);
              
               
              List listsecDetPol = processDao.getMapResult("BuscaSecDetPolizaSB", secDetPol);
              
           DetPolizasXTransaccion transaccion = new DetPolizasXTransaccion();
           DetPolizasXTransaccionId transaccionId = new DetPolizasXTransaccionId();
              DetPolizasXTransaccionId resultTrans = null;
              for (int j = 0; listsecDetPol.size()-1>= j;j++){
                  
                  Map secDetPolS = (HashMap) listsecDetPol.get(j);
                   
                 System.out.println("sedc: "+secDetPolS.get("SEC").toString());
                 
                  transaccionId.setCompania(compania);
                    transaccionId.setFecha(formatoDelTexto2.parse(fechaPol));
                    transaccionId.setTipoPoliza(tipoPol);
                    transaccionId.setNumero(numeroPol);
                    transaccionId.setSec(Integer.parseInt(secDetPolS.get("SEC").toString()));
                    transaccionId.setIdTransaccion(idTransaccion);
                    transaccion.setId(transaccionId);

                    resultTrans = detPolizasXTransaccionDao.save(transaccion);
            
              }
               
                 if (resultTrans == null) {
                     
                     
                    log.error("Error al generar la relacion con detalle de poliza");
            
                    return false;
                    
                } else {
                     
                     log.debug("Transaccion generada correctamente.");
                    return true;
                    
                }
                
                
               
        } catch (Exception e) {
            
            e.printStackTrace();
            log.error("Error al generar la transaccion de cobranza.");
            return false;
        }

    
    
    
    }
    
    
        public boolean generaTransaccionPago(String compania,int numero,String numeroPol,String fechaPol,String tipoPol,List listDocCfdi){
        
      
                 SimpleDateFormat formatoDelTexto2 = new SimpleDateFormat("dd/MM/yyyy");
                 
    
           try {
            
          
              String folio = "";
              String uudi = "";
              String rfcEmisor = "";
              String rfcReceptor = "";
              String tipoCambio = "";
              String moneda = "";
              String total = "";
                  
                
                if(listDocCfdi != null){
                        Map secListDocCfdi = (HashMap) listDocCfdi.get(0);
                        
                        if(secListDocCfdi.get("FOLIO")!= null){
                            folio = secListDocCfdi.get("FOLIO").toString();
                        }
                        uudi = secListDocCfdi.get("UUID").toString();
                        rfcEmisor = secListDocCfdi.get("RFC_EMISOR").toString();
                        rfcReceptor = secListDocCfdi.get("RFC_RECEPTOR").toString();
                        if(secListDocCfdi.get("TIPO_CAMBIO")!=null){
                             tipoCambio = secListDocCfdi.get("TIPO_CAMBIO").toString();
                        }
                        if (secListDocCfdi.get("MONEDA") !=null){
                               moneda = secListDocCfdi.get("MONEDA").toString();
                        }
                        total = secListDocCfdi.get("TOTAL").toString();
                 
                 
                }else{
                
                    return false;
                }
                
                
                log.debug("Generando Transaccion de pago...");
                
                
    //****************Generando Transaccion de cobro*******************
                
              Map mapDatBancos = new HashMap();
             
              mapDatBancos.put("compania", compania);
              mapDatBancos.put("numeroPol", numeroPol);
              mapDatBancos.put("fechaPol", fechaPol);
              mapDatBancos.put("tipoPol", tipoPol);
              mapDatBancos.put("numero", numero);
              
              
              log.debug("Buscando cuenta de banco...");
               
              List listDatBanc= processDao.getMapResult("BuscaCuentaBancoCXP", mapDatBancos);
              
              if(listDatBanc == null){
                  
                  log.error("Error. No se encontro cuenta de banco.");
              
                   return false;
              }
              
              log.debug("cuenta de banco encontrada");
              
            for(int i = 0;i<listDatBanc.size();i++){     
   
                Map datosCobro = (HashMap) listDatBanc.get(i);
                
                if(datosCobro.get("CUENTA_BANCO") != null && datosCobro.get("BANCO_SAT") != null && datosCobro.get("BANCO_PROV") != null && datosCobro.get("CUENTA_PROV") != null){
                    ErpSatTransaccion erpTrans = new ErpSatTransaccion();
                    ErpSatTransaccionId erpTransId = new ErpSatTransaccionId();

                    erpTransId.setCompania(compania);
                    int idTransaccion = erpSatTransaccionDao.getMaxId(erpTransId);
                    erpTransId.setId(new BigDecimal(idTransaccion));
                    erpTrans.setId(erpTransId);
                    erpTrans.setTipo("Transferen");
                    erpTrans.setUuidCfdi(uudi);
                    erpTrans.setFecha(formatoDelTexto2.parse(fechaPol));
                    erpTrans.setCtaOri(datosCobro.get("CUENTA_BANCO").toString());
                    erpTrans.setBancoOriNal(datosCobro.get("BANCO_SAT").toString());
                    erpTrans.setCtaDest(datosCobro.get("CUENTA_PROV").toString());
                    erpTrans.setBancoDestNal(datosCobro.get("BANCO_PROV").toString());
                   
                    
                    BigDecimal cargos = new BigDecimal(datosCobro.get("CARGOS").toString());
                    BigDecimal abonos = new BigDecimal(datosCobro.get("ABONOS").toString());
                    
                    
                    if(cargos.compareTo(new BigDecimal(0)) == 0){
                    
                        erpTrans.setMontoTotal(abonos);
                    
                    }else{
                    
                        erpTrans.setMontoTotal(cargos);
                    
                    }
                    

                    Map rfcCompania = new HashMap();
                  String rfcComp = "";
                  rfcCompania.put("compania", compania);

//                  List listRfc = processDao.getMapResult("BuscaRFCCompania", rfcCompania);
//
//                     if (listRfc == null){
//
//                         erpTrans.setRfc(rfcEmisor);
//
//
//                     }else{
//
//                         Map rfcCompa = (HashMap) listRfc.get(0);
//
//                         if (rfcCompa.get("RFC").toString().equalsIgnoreCase(rfcEmisor)){
//
//
//                              erpTrans.setRfc(rfcReceptor);
//                               erpTrans.setBenef(datosCobro.get("BENEF").toString());
//
//                         }else{

                                erpTrans.setRfc(rfcEmisor);
                                 erpTrans.setBenef(datosCobro.get("BENEF_EMISOR").toString());

//                         }
//
//                     }


                     erpTrans.setMontoTotal(new BigDecimal(total));

                            Map mnd = new HashMap();
    //                
                    mnd.put("moneda", moneda);

                       List listMnd = processDao.getMapResult("BuscaMonedaCFDI", mnd);

                      // System.out.println("listMnd"+listMnd);

                       if (listMnd == null){

                            erpTrans.setMoneda("MXN");




                       }else{

                         //  System.out.println("get 0"+listMnd.get(0));

                             Map mndM = (HashMap) listMnd.get(0);

                          //  System.out.println("moneda sat "+mndM.get("MONEDA_SAT").toString());

                           if (mndM.get("MONEDA_SAT").toString().equalsIgnoreCase("1")){
                              erpTrans.setMoneda("MXN");
                           }

                            if (mndM.get("MONEDA_SAT").toString().equalsIgnoreCase("2")){
                              erpTrans.setMoneda("USD");
                           }

                       }



                       if (tipoCambio.equalsIgnoreCase("N/A") || tipoCambio == null || tipoCambio.isEmpty() || tipoCambio.equalsIgnoreCase("") ){

    //                     System.out.println("Valor 1 a tipo de cambio");
                          erpTrans.setTipCamb(new BigDecimal(1));
                     }else{


                         erpTrans.setTipCamb(new BigDecimal(tipoCambio));
                     }
                       
                        erpSatTransaccionDao.save(erpTrans);
                 
                     
                        Map secDetPol = new HashMap();
                        //String rfcComp = "";
                        secDetPol.put("compania", compania);
                        secDetPol.put("tipo_poliza", tipoPol);
                        secDetPol.put("fecha", fechaPol);
                        secDetPol.put("numero", numeroPol);


                     //   List listsecDetPol = processDao.getMapResult("BuscaSecDetPoliza", secDetPol);

                     DetPolizasXTransaccion transaccion = new DetPolizasXTransaccion();
                     DetPolizasXTransaccionId transaccionId = new DetPolizasXTransaccionId();
                        DetPolizasXTransaccionId resultTrans = null;
                     //   for (int j = 0; listsecDetPol.size()-1>= j;j++){

                         //   Map secDetPolS = (HashMap) listsecDetPol.get(j);

                         //  System.out.println("sedc: "+secDetPolS.get("SEC").toString());

                            transaccionId.setCompania(compania);
                              transaccionId.setFecha(formatoDelTexto2.parse(fechaPol));
                              transaccionId.setTipoPoliza(tipoPol);
                              transaccionId.setNumero(numeroPol);
                              transaccionId.setSec(Integer.parseInt(datosCobro.get("SEC").toString()));
                              transaccionId.setIdTransaccion(idTransaccion);
                              transaccion.setId(transaccionId);

                              resultTrans = detPolizasXTransaccionDao.save(transaccion);
                              
                              
    //**********************GENERA PAGO******************************************/
    
                       
                        ErpSeguiDocumentos segui = new ErpSeguiDocumentos(); 
                        ErpSeguiDocumentosId seguiId = new ErpSeguiDocumentosId(); 

                     
                         seguiId.setCompania(compania);
                         seguiId.setNumFe(numero);
                         seguiId.setOrigen("P");
                         seguiId.setSerie("A");
                         seguiId.settDoc("FE");
                         int sec = erpSeguiDocumentosDao.getMaxId(seguiId);
                //         System.out.println("pagos sec "+sec);
                         seguiId.setSec(sec);

                         segui.setId(seguiId);
                         segui.setBanco(datosCobro.get("BANCO_SAT").toString());
                        
                         segui.setFolio(numero);
                         
                         if(cargos.compareTo(new BigDecimal(0)) == 0){
                    
                                segui.setImporteOperacion(abonos);

                            }else{

                                segui.setImporteOperacion(cargos);

                            }
                    

                        
                         
                         //segui.setMoneda(ss.);
                         segui.setObservaciones("PAGO-"+folio);
                         segui.setParidad(new BigDecimal(1));
                         
                          if(cargos.compareTo(new BigDecimal(0)) == 0){
                    
                                segui.setTotPesos(abonos);

                            }else{

                                segui.setTotPesos(cargos);

                            }
                         
                         segui.setUsuario("pagoAuto");
                         segui.settOperacion("03");
                         segui.setCuentaBanco(datosCobro.get("CUENTA_BANCO").toString());
                         segui.setFechaPagoCxpFe(formatoDelTexto2.parse(fechaPol));
                         segui.setIdTransaccion(idTransaccion);
                         ErpSeguiDocumentosId result = erpSeguiDocumentosDao.save(segui);
                         
                         
                             Map pagoTotal = new HashMap();
                
                        pagoTotal.put("compania", compania);
                        pagoTotal.put("numero", numero);

                            List listTotal = processDao.getMapResult("TotalesPagos", pagoTotal);

                             Map pag = (HashMap) listTotal.get(0);
                             BigDecimal pago = new BigDecimal(pag.get("PAGO").toString());
                             BigDecimal total2 = new BigDecimal(pag.get("TOTAL").toString());

                        //     System.out.println("buscando actualizar"+pago);
                        //     System.out.println("buscando actualizar"+total);

                             ErpFeComprobantes comp = new ErpFeComprobantes();
                             ErpFeComprobantesId compId = new ErpFeComprobantesId();

                             compId.setCompania(compania);
                             compId.setNumero(numero);
                             comp.setId(compId);

                            if (total2.compareTo(pago) == 1){

                                erpFeComprobantesDao.actualizaPago("PAR", pago, comp);


                            }

                             if (total2.compareTo(pago) == 0){

                                erpFeComprobantesDao.actualizaPago("PAG", pago, comp);


                            }

                              if (total2.compareTo(pago) == -1){

                                erpFeComprobantesDao.actualizaPago("PAG", pago, comp);


                            }
                              
                              

                       // }

                         //  if (resultTrans == null) {

                         //     return false;

                         // } else {
                         //     return true;

                         // }



                }
            }
            
            
            log.debug("Transferencia de pago correctamente generada.");
//                     
//                    rfcCompa.get("RFC").toString()
//                
             log.debug("Generando transaccion Compnal...");
//***********************Generando transaccion Compnal**************************

              ErpSatTransaccion erpTrans = new ErpSatTransaccion();
              ErpSatTransaccionId erpTransId = new ErpSatTransaccionId();
              
              erpTransId.setCompania(compania);
              int idTransaccion = erpSatTransaccionDao.getMaxId(erpTransId);
              erpTransId.setId(new BigDecimal(idTransaccion));
              erpTrans.setId(erpTransId);
              erpTrans.setTipo("CompNal");
              erpTrans.setUuidCfdi(uudi);
              
              
//              Map rfcCompania = new HashMap();
//              String rfcComp = "";
//              rfcCompania.put("compania", compania);
//               
//              List listRfc = processDao.getMapResult("BuscaRFCCompania", rfcCompania);
//              
//                 if (listRfc == null){
//                     
//                     erpTrans.setRfc(rfcEmisor);
//
//                     
//                 }else{
//                     
//                     Map rfcCompa = (HashMap) listRfc.get(0);
//                     
//                     if (rfcCompa.get("RFC").toString().equalsIgnoreCase(rfcEmisor)){
//                     
//                         
//                          erpTrans.setRfc(rfcReceptor);
//                         
//                     }else{
                     
                            erpTrans.setRfc(rfcEmisor);
                     
//                     }
//                     
//                 }
//                  
                 
                 erpTrans.setMontoTotal(new BigDecimal(total));
                 
                        Map mnd = new HashMap();
//                
                mnd.put("moneda", moneda);
               
                   List listMnd = processDao.getMapResult("BuscaMonedaCFDI", mnd);
                   
                  // System.out.println("listMnd"+listMnd);
                   
                   if (listMnd == null){
                       
                        erpTrans.setMoneda("MXN");
                        
                        
                 
                   
                   }else{
                       
                     //  System.out.println("get 0"+listMnd.get(0));
                       
                         Map mndM = (HashMap) listMnd.get(0);
                    
                      //  System.out.println("moneda sat "+mndM.get("MONEDA_SAT").toString());
                
                       if (mndM.get("MONEDA_SAT").toString().equalsIgnoreCase("1")){
                          erpTrans.setMoneda("MXN");
                       }
                       
                        if (mndM.get("MONEDA_SAT").toString().equalsIgnoreCase("2")){
                          erpTrans.setMoneda("USD");
                       }
                   
                   }
                   
                  
               
                   if (tipoCambio.equalsIgnoreCase("N/A") || tipoCambio == null || tipoCambio.isEmpty() || tipoCambio.equalsIgnoreCase("") ){
                     
//                     System.out.println("Valor 1 a tipo de cambio");
                      erpTrans.setTipCamb(new BigDecimal(1));
                 }else{
                     
                     
                     erpTrans.setTipCamb(new BigDecimal(tipoCambio));
                 }
              // 
              
              //  erpTrans.setMoneda("MXN");
              //  erpTrans.setTipCamb(new BigDecimal(1));
             
            
                 
                 
                 erpSatTransaccionDao.save(erpTrans);
                 
                     
                    Map secDetPol = new HashMap();
              //String rfcComp = "";
              secDetPol.put("compania", compania);
              secDetPol.put("tipo_poliza", tipoPol);
              secDetPol.put("fecha", fechaPol);
              secDetPol.put("numero", numeroPol);
              
               
              List listsecDetPol = processDao.getMapResult("BuscaSecDetPolizaSB", secDetPol);
              
           DetPolizasXTransaccion transaccion = new DetPolizasXTransaccion();
           DetPolizasXTransaccionId transaccionId = new DetPolizasXTransaccionId();
              DetPolizasXTransaccionId resultTrans = null;
              for (int j = 0; listsecDetPol.size()-1>= j;j++){
                  
                  Map secDetPolS = (HashMap) listsecDetPol.get(j);
                   
                 System.out.println("sedc: "+secDetPolS.get("SEC").toString());
                 
                  transaccionId.setCompania(compania);
                    transaccionId.setFecha(formatoDelTexto2.parse(fechaPol));
                    transaccionId.setTipoPoliza(tipoPol);
                    transaccionId.setNumero(numeroPol);
                    transaccionId.setSec(Integer.parseInt(secDetPolS.get("SEC").toString()));
                    transaccionId.setIdTransaccion(idTransaccion);
                    transaccion.setId(transaccionId);

                    resultTrans = detPolizasXTransaccionDao.save(transaccion);
            
              }
               
                 if (resultTrans == null) {
                     
                     log.error("Error al relacionar transaccion con detalle de poliza.");
            
                    return false;
                    
                } else {
                     
                     log.debug("Transaccion correctamente generada");
                    return true;
                    
                }
                
                
               
        } catch (Exception e) {
            
            e.printStackTrace();
            log.error("Error al generar la transaccion");
            return false;
        }

    
    
    
    }
        
        
        
        
        
        public boolean limpiaContaElectronica(String compania,int numero,String numeroPol,String fechaPol,String tipoPol){
        
                 SimpleDateFormat formatFecha = new SimpleDateFormat("dd/MM/yyyy");
             

                    log.debug("-----------------Entrando a Eliminar relacion------------------");
                    log.debug("compania : "+compania);
                    log.debug("numeroPol: "+numeroPol);
                    log.debug("fechaPol : "+fechaPol);
                    log.debug("tipoPol  : "+tipoPol);
                    log.debug("numeroFe : "+numero);


                        ErpPolizasXFacturas polizas = new ErpPolizasXFacturas();
                        ErpPolizasXFacturasId polizasId = new ErpPolizasXFacturasId();

                        ErpPolizasXPagos polizasPagos = new ErpPolizasXPagos();
                        ErpPolizasXPagosId polizasPagosId = new ErpPolizasXPagosId();

                        boolean idPago = false;
                        boolean idFacturas = false;

                        boolean result = false;

                        SimpleDateFormat formatoDelTexto2 = new SimpleDateFormat("dd/MM/yyyy");


                  try {




                      log.debug("Buscando tipo de polizas sat...");

                      Map tipoPolSatMap = new HashMap();
                     //String rfcComp = "";
                     tipoPolSatMap.put("compania", compania);
                     tipoPolSatMap.put("tipoPoliza", tipoPol);

                     List listTipoPolSat= processDao.getMapResult("BuscaTipoPolSat", tipoPolSatMap);

                         String tipoSat = "";

                      if (listTipoPolSat != null){


                         Map secTipoPolSat = (HashMap) listTipoPolSat.get(0);

                         tipoSat = secTipoPolSat.get("TIPO_SAT").toString();

                      }else{

                           log.error("Error. No se encontro tipo de polizas sat.");
                           return false;

                      }

                           Map docCfdiMap = new HashMap();
                           //String rfcComp = "";
                           docCfdiMap.put("compania", compania);
                           docCfdiMap.put("numero", numero);



                    log.debug("Buscando Datos de CFDI...");

                     List listDocCfdi= processDao.getMapResult("BuscaDatCFDI", docCfdiMap);




                     String folio = "";
                     String uudi = "";
                     String rfcEmisor = "";
                     String rfcReceptor = "";
                     String tipoCambio = "";
                     String moneda = "";
                     String total = "";


                       if(listDocCfdi != null){
                               Map secListDocCfdi = (HashMap) listDocCfdi.get(0);
                               if (secListDocCfdi.get("FOLIO") != null){
                                   folio = secListDocCfdi.get("FOLIO").toString();
                               }
                               uudi = secListDocCfdi.get("UUID").toString();
                               //rfcEmisor = secListDocCfdi.get("RFC_EMISOR").toString();
                               //rfcReceptor = secListDocCfdi.get("RFC_RECEPTOR").toString();
                               //tipoCambio = secListDocCfdi.get("TIPO_CAMBIO").toString();
                               //moneda = secListDocCfdi.get("MONEDA").toString();
                               //total = secListDocCfdi.get("TOTAL").toString();


                       }else{


                           log.error("Error. Datos de CFDI no encontrados.");
                           return false;
                       }


                        if(tipoSat.equalsIgnoreCase("I") || tipoSat.equalsIgnoreCase("E")){
                               polizasPagosId.setCompania(compania);
                               polizasPagosId.setFechaPol(formatoDelTexto2.parse(fechaPol));
                               polizasPagosId.setNumeroPol(numeroPol);
                               polizasPagosId.setTipoPol(tipoPol);
                               polizasPagos.setId(polizasPagosId);
                               polizasPagos.setFolio(folio);
                               polizasPagos.setNumeroFe(String.valueOf(numero));
                               polizasPagos.setUuid(uudi);
                               polizasPagos.setOperacion("GE");



                              idPago = erpPolizasXPagosDao.delete(polizasPagos);

                              if (idPago == false){

                                log.error("Error. Error al eliminar la relacion PolizaXPagos.");

                                result = false;
                                return result;

                              }else{

                                 result = true;
                              }

                      }else{

                                  polizasId.setCompania(compania);
                                   polizasId.setFechaPol(formatoDelTexto2.parse(fechaPol));
                                   polizasId.setNumeroPol(numeroPol);
                                   polizasId.setOperacion("GE");
                                   polizasId.setTipoPol(tipoPol);
                                   polizas.setId(polizasId);
                                   polizas.setFolio(folio);
                                   polizas.setNumeroFe(numero);
                                   polizas.setUuid(uudi);



                                  idFacturas = erpPolizasXFacturasDao.delete(polizas);


                                  if (idFacturas == false){

                                       log.error("Error. Error al eliminar la relacion PolizaXFacturas.");

                                       result = false;
                                       return result;

                                     }else{



                                        result = true;




                                     }



                               }
                        
                           DetPolizasXTransaccion transaccion = new DetPolizasXTransaccion();
                           DetPolizasXTransaccionId transaccionId = new DetPolizasXTransaccionId();
                           boolean resultTrans = false;
                           
                             SimpleDateFormat formatoDelTexto = new SimpleDateFormat("dd/MM/yyyy");
                 
                 Date fechaCap = null;
                
                 fechaCap = formatoDelTexto.parse(fechaPol);
         
                    transaccionId.setCompania(compania);
                    transaccionId.setFecha(fechaCap);
              
                 
                    transaccionId.setCompania(compania);
                    transaccionId.setFecha(fechaCap);
                    transaccionId.setTipoPoliza(tipoPol);
                    transaccionId.setNumero(numeroPol);
                    transaccion.setId(transaccionId);

                    resultTrans = detPolizasXTransaccionDao.borraTransaccionXPolizaCompl2(transaccion,fechaPol);
                    
                    
                    
                    ErpSeguiDocumentos segui = new ErpSeguiDocumentos(); 
                        ErpSeguiDocumentosId seguiId = new ErpSeguiDocumentosId(); 

                     
                         seguiId.setCompania(compania);
                         seguiId.setNumFe(numero);
                         seguiId.setOrigen("P");
                         seguiId.setSerie("A");
                         seguiId.settDoc("FE");
                        

                         segui.setId(seguiId);
                      
                         boolean resultPagoDel = erpSeguiDocumentosDao.borraDetallePagos(seguiId);
                         
                   



                             ErpFeComprobantes comp = new ErpFeComprobantes();
                             ErpFeComprobantesId compId = new ErpFeComprobantesId();

                             compId.setCompania(compania);
                             compId.setNumero(numero);
                             comp.setId(compId);

                    

                                erpFeComprobantesDao.actualizaPago("", BigDecimal.ZERO, comp);





                     

                    return result;



               } catch (Exception e) {

                   e.printStackTrace();
                   log.error("Error. Error al eliminar la relacion");
                   return false;
               }

    
        
        
        }
    
    
    
    

    public void setProcessDao(ProcessDao processDao) {
        this.processDao = processDao;
    }

    public void setErpPolizasXFacturasDao(ErpPolizasXFacturasDao erpPolizasXFacturasDao) {
        this.erpPolizasXFacturasDao = erpPolizasXFacturasDao;
    }

    public void setErpPolizasXPagosDao(ErpPolizasXPagosDao erpPolizasXPagosDao) {
        this.erpPolizasXPagosDao = erpPolizasXPagosDao;
    }

    public void setErpSatTransaccionDao(ErpSatTransaccionDao erpSatTransaccionDao) {
        this.erpSatTransaccionDao = erpSatTransaccionDao;
    }

    public void setDetPolizasXTransaccionDao(DetPolizasXTransaccionDao detPolizasXTransaccionDao) {
        this.detPolizasXTransaccionDao = detPolizasXTransaccionDao;
    }

    public void setErpSeguiDocumentosDao(ErpSeguiDocumentosDao erpSeguiDocumentosDao) {
        this.erpSeguiDocumentosDao = erpSeguiDocumentosDao;
    }

    public void setErpFeComprobantesDao(ErpFeComprobantesDao erpFeComprobantesDao) {
        this.erpFeComprobantesDao = erpFeComprobantesDao;
    }
    

    
    
}
