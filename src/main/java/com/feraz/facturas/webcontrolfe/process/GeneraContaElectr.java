/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.feraz.facturas.webcontrolfe.process;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.feraz.contabilidad.sat.transacciones.dao.DetPolizasXTransaccionDao;
import com.feraz.contabilidad.sat.transacciones.dao.ErpSatTransaccionDao;
import com.feraz.contabilidad.sat.transacciones.model.DetPolizasXTransaccion;
import com.feraz.contabilidad.sat.transacciones.model.DetPolizasXTransaccionId;
import com.feraz.contabilidad.sat.transacciones.model.ErpSatTransaccion;
import com.feraz.contabilidad.sat.transacciones.model.ErpSatTransaccionId;
import com.feraz.polizas3.dao.ErpPolizasXFacturasDao;
import com.feraz.polizas3.dao.ErpPolizasXPagosDao;
import com.feraz.polizas3.dto.RelacionFacturasDTO;
import com.feraz.polizas3.model.ErpPolizasXFacturas;
import com.feraz.polizas3.model.ErpPolizasXFacturasId;
import com.feraz.polizas3.model.ErpPolizasXPagos;
import com.feraz.polizas3.model.ErpPolizasXPagosId;
import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import org.jamh.data.process.ProcessDao;

/**
 *
 * @author vavi
 */
public class GeneraContaElectr {
    
    
    private ProcessDao processDao;
    private ErpPolizasXFacturasDao erpPolizasXFacturasDao;
    private ErpPolizasXPagosDao erpPolizasXPagosDao;
    private ErpSatTransaccionDao erpSatTransaccionDao;
    private DetPolizasXTransaccionDao detPolizasXTransaccionDao;
    
    public boolean generaRelacion(String compania,int numero,String numeroPol,String fechaPol,String tipoPol){
        
             SimpleDateFormat formatFecha = new SimpleDateFormat("dd/MM/yyyy");
          
        
                 ErpPolizasXFacturas polizas = new ErpPolizasXFacturas();
                 ErpPolizasXFacturasId polizasId = new ErpPolizasXFacturasId();
                 
                 ErpPolizasXPagos polizasPagos = new ErpPolizasXPagos();
                 ErpPolizasXPagosId polizasPagosId = new ErpPolizasXPagosId();
                 
                 ErpPolizasXPagosId idPago = null;
                 ErpPolizasXFacturasId idFacturas = null;
                 
                 boolean result = false;
                 
                 SimpleDateFormat formatoDelTexto2 = new SimpleDateFormat("dd/MM/yyyy");
                 
    
           try {
            
            
      
        
          
      
               Map tipoPolSatMap = new HashMap();
              //String rfcComp = "";
              tipoPolSatMap.put("compania", compania);
              tipoPolSatMap.put("tipoPoliza", tipoPol);
                    
              List listTipoPolSat= processDao.getMapResult("BuscaTipoPolSat", tipoPolSatMap);
                  Map secTipoPolSat = (HashMap) listTipoPolSat.get(0);
                   
                 String tipoSat = secTipoPolSat.get("TIPO_SAT").toString();
                 
              Map docCfdiMap = new HashMap();
              //String rfcComp = "";
              docCfdiMap.put("compania", compania);
              docCfdiMap.put("numero", numero);
                    
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
                        folio = secListDocCfdi.get("FOLIO").toString();
                        uudi = secListDocCfdi.get("UUID").toString();
                        rfcEmisor = secListDocCfdi.get("RFC_EMISOR").toString();
                        rfcReceptor = secListDocCfdi.get("RFC_RECEPTOR").toString();
                        tipoCambio = secListDocCfdi.get("TIPO_CAMBIO").toString();
                        moneda = secListDocCfdi.get("MONEDA").toString();
                        total = secListDocCfdi.get("TOTAL").toString();
                 
                 
                }else{
                
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
                       
                         result = false;
                         return false;
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
                       
                                result = false;
                                return false;
                              }else{

                                 result = true;
                              }

                           
                           
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
            
                    return false;
                    
                } else {
                    return true;
                    
                }
                
                
               
        } catch (Exception e) {
            
            e.printStackTrace();
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
    
    
    
    
}
