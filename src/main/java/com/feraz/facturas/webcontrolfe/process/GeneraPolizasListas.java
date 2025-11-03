
package com.feraz.facturas.webcontrolfe.process;

import com.feraz.contabilidad.convertidor.dao.ErpCatConvertidorDao;
import com.feraz.contabilidad.convertidor.dao.ErpDetConvertidorDao;
import com.feraz.contabilidad.convertidor.dao.ErpParidadDao;
import com.feraz.contabilidad.convertidor.model.ErpCatConvertidor;
import com.feraz.contabilidad.convertidor.model.ErpDetConvertidor;
import com.feraz.contabilidad.convertidor.model.ErpParidadCompania;
import com.feraz.contabilidad.convertidor.dao.ErpParidadCompaniaDao;

import com.feraz.contabilidad.sat.diot.dao.DetDIOTDao;
import com.feraz.contabilidad.sat.diot.model.DetDIOT;
import com.feraz.contabilidad.sat.diot.model.DetDIOTId;
import com.feraz.contabilidad.sat.transacciones.dao.DetPolizasXTransaccionDao;
import com.feraz.contabilidad.sat.transacciones.dao.ErpSatTransaccionDao;
import com.feraz.contabilidad.sat.transacciones.model.DetPolizasXTransaccion;
import com.feraz.contabilidad.sat.transacciones.model.DetPolizasXTransaccionId;
import com.feraz.contabilidad.sat.transacciones.model.ErpSatTransaccion;
import com.feraz.contabilidad.sat.transacciones.model.ErpSatTransaccionId;
import com.feraz.cxp.dto.PagosAllDTO;
import com.feraz.facturas.webcontrolfe.dao1.ErpFeComprobantesDao;
import com.feraz.facturas.webcontrolfe.dao1.ErpFeConceptosDao;
import com.feraz.facturas.webcontrolfe.dao1.ErpFeEmisorDao;
import com.feraz.facturas.webcontrolfe.dao1.ErpFeImpRetencionesDao;
import com.feraz.facturas.webcontrolfe.dao1.ErpFeImpTrasladosDao;
import com.feraz.facturas.webcontrolfe.dao1.ErpFeReceptorDao;
import com.feraz.facturas.webcontrolfe.dao1.ErpFeTrasLocalesDao;
import com.feraz.facturas.webcontrolfe.dto.ComprobanteDto;
import com.feraz.facturas.webcontrolfe.dto.ResponseMsg2;
import com.feraz.facturas.webcontrolfe.dto.ResultDetallePoliza;
import com.feraz.facturas.webcontrolfe.dto.ResultFEMap;
import com.feraz.facturas.webcontrolfe.dto.ResultListDetPol;
import com.feraz.facturas.webcontrolfe.model.ErpFeComprobantes;
import com.feraz.facturas.webcontrolfe.model.ErpFeConceptos;
import com.feraz.facturas.webcontrolfe.model.ErpFeEmisor;
import com.feraz.facturas.webcontrolfe.model.ErpFeImpRetenciones;
import com.feraz.facturas.webcontrolfe.model.ErpFeImpTraslados;
import com.feraz.facturas.webcontrolfe.model.ErpFeReceptor;
import com.feraz.facturas.webcontrolfe.model.ErpFeTrasLocales;
import com.feraz.polizas3.dao.DetPolizasDao;
import com.feraz.polizas3.dao.ErpPolizasXFacturasDao;
import com.feraz.polizas3.dao.ErpPolizasXPagosDao;
import com.feraz.polizas3.dao.LogPolizasDao;
import com.feraz.polizas3.dao.PolizasDao;
import com.feraz.polizas3.model.DetPolizas;
import com.feraz.polizas3.model.DetPolizasId;
import com.feraz.polizas3.model.ErpPolizasXFacturas;
import com.feraz.polizas3.model.ErpPolizasXFacturasId;
import com.feraz.polizas3.model.ErpPolizasXPagos;
import com.feraz.polizas3.model.ErpPolizasXPagosId;
import com.feraz.polizas3.model.Polizas;
import com.feraz.polizas3.model.PolizasId;
import com.feraz.polizas3.validation.DetalleValidation;
import com.feraz.polizas3.validation.MaestroValidation;
import de.congrace.exp4j.Calculable;
import de.congrace.exp4j.ExpressionBuilder;
import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
//import net.objecthunter.exp4j.ExpressionBuilder;

import org.jamh.data.process.ProcessDao;
import org.jamh.wf.process.Querys;

/**
 *
 * @author Ing. JAMH
 */
public class GeneraPolizasListas {

    private StringBuilder msgErr;
    private ErpCatConvertidorDao erpCatConvertidorDao;
    private ErpDetConvertidorDao erpDetConvertidorDao;
    private ErpFeComprobantesDao erpFeComprobantesDao;
    private ErpFeEmisorDao erpFeEmisorDao;
    private ErpFeImpTrasladosDao erpFeImpTrasladosDao;
    private ErpFeImpRetencionesDao erpFeImpRetencionesDao;
    private ErpFeReceptorDao erpFeReceptorDao;
    private PolizasDao polizasDao;
    private DetPolizasDao detPolizasDao;
    private LogPolizasDao logPolizasDao;
    private MaestroValidation maestroValidation;
    private DetalleValidation detalleValidation;
    private ErpPolizasXFacturasDao erpPolizasXFacturasDao;
    private ErpPolizasXPagosDao erpPolizasXPagosDao;
    private Operaciones operaciones;
    private ProcessDao processDao;
    private ErpParidadDao erpParidadDao;
    private DetDIOTDao detDIOTDao;
    private ErpFeConceptosDao erpFeConceptosDao;
    private ErpSatTransaccionDao erpSatTransaccionDao;
    private DetPolizasXTransaccionDao detPolizasXTransaccionDao;
    private int contDet;
    private ErpParidadCompaniaDao erpParidadCompaniaDao;
    private List<String> arrList = new ArrayList<String>();
    private String mensajeB;
    private String msgParidad;
    private String mensajeF;
    private String msgParidadFactura;
    
    private ErpFeTrasLocalesDao erpFeTrasLocalesDao;
    //private ErpCClientesDao erpCClientesDaoImpl;
    
    
        public ResponseMsg2 procesaPagos(String compania, List<PagosAllDTO> lista, String usuario) {
        // ResponseMsg rm = new ResponseMsg();
            
            
         System.out.println("PROCESA PAGOS------------------------------------------------------");
         
         ResponseMsg2 rm2 = new ResponseMsg2();
         
         arrList.clear();
         mensajeB = "0";
         mensajeF = "0";
         msgParidad = "";
         msgParidadFactura = "";
         
        
        msgErr = new StringBuilder();
        try {
        Iterator<PagosAllDTO> i = lista.iterator();
       
        while (i.hasNext()) {
            PagosAllDTO com = i.next();

            //    System.out.println("AUXILIARCONTABLE:" + com.RFC_COMPANIA);
            //    System.out.println("TIPO:" + com.TIPO);
            //    System.out.println("CUENTA_CONTABLE:" + com.CUENTA_CONTABLE);
            //    System.out.println("AUXILIAR_CONTABLE:" + com.AUXILIAR_CONTABLE);
            //      System.out.println("Pasando validaciones busqeuda" );
            if (com.flagPolizaPago == null) {
             //    System.out.println("Pasando validaciones 1" );
                continue;
            }

            if (com.flagPolizaPago.equals("false")) {
              //   System.out.println("Pasando validaciones 2" );
                continue;
            }
          //  if (com.NUMERO_POL != null) {
                
          //       System.out.println("Pasando validaciones 3" );

          //      setErr("Error fecha de poliza no encontrada:" + com.NUMERO);
          //      continue;
          //  }

            if (com.fechaPolPago == null || com.fechaPolPago.equals("")) {
                
                //    System.out.println("Pasando validaciones 4" );
                //     setErr();
                rm2.setIsGood(false);
                rm2.setMsg("Error fecha de poliza no encontrada:" + com.numero);
                return rm2;
                // continue;
            }

            if (com.noCasoPagos == null || com.noCasoPagos.equals("")) {
                if (com.idConcGastoPagos == null || com.idConcGastoPagos.equals("")){
                
                        System.out.println("Pasando validaciones 5" );
                       setErr("Error no se asigno el convertidor:" + com.numero);
                       continue;
                }else{
                
                      com.noCasoPagos = com.idConcGastoPagos;
                
                }
            }
            // System.out.println("Pasando validaciones" );
            System.out.println("com.noCaso:" +com.noCasoPagos);
            ResultFEMap para = getMapDatPagos(compania, com);
            ResultFEMap para2 = getMapDatPagosParcial(compania, com);
            //System.out.println("buscando para" );
            //System.out.println("com.noCaso:" +com.noCaso);
            ErpCatConvertidor conv = buscaConvertidor(compania, com.noCasoPagos);
            if (conv == null) {
                setErr("Error convertidor no encontrado :" + com.numero);
                rm2.setIsGood(false);
                rm2.setMsg(msgErr.toString());
                return rm2;
            }
            //System.out.println("buscando convertidor" );
            ///  List<ErpCatConvertidor>  convertidor = erpCatConvertidorDao.getConvertidor( compania,  "A",com.noCaso, "X");
       
            List<ErpDetConvertidor> detalles = erpDetConvertidorDao.findErpDetConvertidorOrigen(compania, com.noCasoPagos,"X");

            if (detalles == null) {
                setErr("Error detalle convertidor no encontrado :" + com.numero);
                rm2.setIsGood(false);
                rm2.setMsg(msgErr.toString());
                return rm2;
            }

            if (detalles.isEmpty()) {
                setErr("Error detalle convertidor no encontrado :" + com.numero);
                rm2.setIsGood(false);
                rm2.setMsg(msgErr.toString());
                return rm2;
            }
              //System.out.println("buscando detalle convertidor" );
//                 System.out.println("detalles:" +detalles.size());
//                 ErpCatConvertidor conv =convertidor.get(0);
//            System.out.println("conv.getNombre():"+conv.getNombre());
//            System.out.println("operaciones:"+operaciones);
//            System.out.println("para:"+para);
            if (conv.getNombre() != null) {
                conv.setNombre(operaciones.remplaceData(conv.getNombre(), para.getMapa()));
            }

            if (conv.getDescripcion() != null) {
                conv.setDescripcion(operaciones.remplaceData(conv.getDescripcion(), para.getMapa()));
            }
            //System.out.println("genewrando poliza" );
            PolizasId polId = generaPoliza(conv, para.getComprobante(), com.fechaPolPago, usuario);
            if (polId == null) {
                setErr("Periodo no valido para :" + com.numero);
                rm2.setIsGood(false);
                rm2.setMsg(msgErr.toString());
                return rm2;
            }
            //System.out.println("poliza generadad" );
            
            /**
             * Busca Datos contables del proveedor o cliente
             */
            
           

            ResultListDetPol rse = generaDetallePolizaPagos(polId, detalles, para.getMapa(),para2.getMapa());
            
            if(mensajeB.equalsIgnoreCase("1")){
            
                arrList.add(msgParidadFactura);
            
            
            }
            
            if(mensajeF.equalsIgnoreCase("1")){
            
                arrList.add(msgParidad);
            
            
            }
            
            mensajeB = "0";
            mensajeF = "0";

            if (!rse.isValid()) {
                setErr("Error al generar el detalle Poliza de :" + com.numero + ", " + rse.getErr());
                rm2.setIsGood(false);
                rm2.setMsg(msgErr.toString());
                return rm2;
            }

            List<DetPolizas> listaDet = rse.getLista();
            List<DetPolizas> listaDet2 = listaDet;

            boolean detSave = detPolizasDao.saveListDet(listaDet);

            if (!detSave) {
                setErr("Error al guardar el detalle Poliza de :" + com.numero);
                rm2.setIsGood(false);
                rm2.setMsg(msgErr.toString());
                return rm2;
            }

            Iterator<DetPolizas> sumDet = listaDet2.iterator();
            double cargos = 0.0d;
            double abonos = 0.0d;
            
            DetPolizasXTransaccion transaccion = new DetPolizasXTransaccion();
           DetPolizasXTransaccionId transaccionId = new DetPolizasXTransaccionId();
           
            ErpSatTransaccion erpTrans = new ErpSatTransaccion();
              ErpSatTransaccionId erpTransId = new ErpSatTransaccionId();
              
              erpTransId.setCompania(compania);
              int idTransaccion = erpSatTransaccionDao.getMaxId(erpTransId);
              erpTransId.setId(new BigDecimal(idTransaccion));
              erpTrans.setId(erpTransId);
              erpTrans.setTipo("CompNal");
              erpTrans.setUuidCfdi(com.uuid);
              
              
              Map rfcCompania = new HashMap();
              //String rfcComp = "";
              rfcCompania.put("compania", compania);
               
              List listRfc = processDao.getMapResult("BuscaRFCCompania", rfcCompania);
              
                 if (listRfc != null){
                     
                     erpTrans.setRfc(com.rfc);

                     
                
                 }
                  
                 
                 erpTrans.setMontoTotal(new BigDecimal(com.total));
                 
                 if (!com.moneda.equalsIgnoreCase("MXN") && !com.moneda.equalsIgnoreCase("USD")){
                     
                     erpTrans.setMoneda("MXN");
                 
                 
                 }else{
                     
                     erpTrans.setMoneda(com.moneda);
                 
                 }
                 
                   if (com.tipoCambio == null || com.tipoCambio == ""){
                     
//                     System.out.println("Valor 1 a tipo de cambio");
                      erpTrans.setTipCamb(new BigDecimal(1));
                 }else{
                       
                    if(com.tipoCambio.equalsIgnoreCase("N/A")){  
                        
                         erpTrans.setTipCamb(new BigDecimal(1));
                    }else{

                        System.out.println("Valor que traiga a tipo de cambio "+com.tipoCambio);

                       erpTrans.setTipCamb(new BigDecimal(com.tipoCambio));
                   }
                 }
              // 
                   
              // if (rfcComp.get("CUENTA_TITULO").toString().equalsIgnoreCase("000001")){
              
            
                 
                 
                 erpSatTransaccionDao.save(erpTrans);

            while (sumDet.hasNext()) {
                DetPolizas dd = sumDet.next();
                //  System.out.println("dd.getCargos():"+dd.getCargos());
                cargos += dd.getCargos().doubleValue();
                //   System.out.println("cargos:"+cargos);   
                abonos += dd.getAbonos().doubleValue();
                
                
           
           transaccionId.setCompania(compania);
           transaccionId.setFecha(dd.getId().getFecha());
           transaccionId.setTipoPoliza(dd.getId().getTipoPoliza());
           transaccionId.setNumero(dd.getId().getNumero());
           transaccionId.setSec(dd.getId().getSec().intValue());
           transaccionId.setIdTransaccion(idTransaccion);
           transaccion.setId(transaccionId);
           
           // System.out.println("uuid1: "+com.uuid);
           // System.out.println("uuid2: "+dd.getReferencia());
           // System.out.println("uuid3: "+com.uuid.equalsIgnoreCase(dd.getReferencia()));
            
            if (com.uuid.equalsIgnoreCase(dd.getReferencia()) || com.uuid.equalsIgnoreCase(dd.getDescripcion())){
             
     
                    detPolizasXTransaccionDao.save(transaccion);
            
            }
            }
//            System.out.println("cargos:" + cargos);
//            System.out.println("abonos:" + abonos);
            String estatus = "0";
            if (cargos != abonos) {
                estatus = "1";
            }
            if (cargos == abonos) {
                estatus = "0";
            }
            boolean guardaTot = polizasDao.guardaTotales(polId, new BigDecimal(cargos), new BigDecimal(abonos), estatus);
//            System.out.println("guardaTot:" + guardaTot);
            Querys que = new Querys();
            String store = que.getSQL("CONTA_INSERTA_DETPOLIZAS_FOVI3");
            Map parametros = new HashMap();
//                System.out.println("store:" + store);
            parametros.put("compania", polId.getCompania());
            parametros.put("NUMERO", polId.getNumero());
            parametros.put("FECHA", com.fechaPolPago);
            parametros.put("TIPO_POLIZA", polId.getTipoPoliza());
            parametros.put("CARGOS", "0");
            parametros.put("ABONOS", "0");
            parametros.put("OPERACION", "S");
            parametros.put("OPE_SUMA", "1");
            int val = processDao.execute2(store, parametros);
//                System.out.println("val:"+ val);

//                 polizasDao.sumDetalles(polId);
            relacionaPolizaXPagos(polId, para.getComprobante());
            //actualizaFE(polId, para.getComprobante(), com.noCasoPagos);
            
            
            

        }

        }catch(Exception e){
            e.printStackTrace();
            setErr("Error al guardar el detalle Poliza");
                rm2.setIsGood(false);
                rm2.setMsg(msgErr.toString());
                return rm2;
        }
        
        
        rm2.setIsGood(true);
        rm2.setList(arrList);
        return rm2;
        
    }
        
         public ResultDetallePoliza generaDetallePagos(PolizasId poliza, ErpDetConvertidor det, Map datosFE,Map datosFE2, int sec) {
        ResultDetallePoliza detRes = new ResultDetallePoliza();
//        DetPolizasId detalle = new DetPolizasId();

        DetPolizasId idDet = new DetPolizasId();
        idDet.setCompania(poliza.getCompania());
        idDet.setNumero(poliza.getNumero());
        idDet.setTipoPoliza(poliza.getTipoPoliza());
        idDet.setFecha(poliza.getFecha());
        idDet.setSec(new BigDecimal(sec));
        DetPolizas detPol = new DetPolizas();

        detPol.setId(idDet);
        
        if(det.getC_costos() == null){
          detPol.setcCostos("00");
        }else{
        
           detPol.setcCostos(det.getC_costos());
          
        }
        
         
      
        if (det.getCuenta() != null) {
            det.setCuenta(operaciones.remplaceData(det.getCuenta(), datosFE));
            detPol.setCuenta(det.getCuenta());
        }
    

        datosFE.put("compania", poliza.getCompania());
        datosFE.put("CUENTA_ALIAS", det.getCuenta());
        List list = processDao.getMapResult("BuscaCuentaCorrecta", datosFE);

        if (list == null || list.size() == 0) {
            detRes.setIsValid(false);
            detRes.setMsgErr("Error la cuenta " + det.getCuenta() + " no es valido");
            return detRes;
        }

        String cuenta_real = getVal(list.get(0).toString(), 0);
           System.out.println("cuenta_real:" + cuenta_real);
                        
//               ss.cuenta = cuenta_real;
//                parametros.put("CUENTA", cuenta_real);
        
   
        detPol.setCuenta(cuenta_real);

        detPol.setFechaCap(new Date());
        if (det.getDescripcion() != null) {
            det.setDescripcion(operaciones.remplaceData(operaciones.remplaceData(det.getDescripcion(), datosFE),datosFE2));
            detPol.setDescripcion(det.getDescripcion());
        }

        if (det.getReferencia() != null) {
            det.setReferencia(operaciones.remplaceData(operaciones.remplaceData(det.getReferencia(), datosFE),datosFE2));
            detPol.setReferencia(det.getReferencia());
        }
        if (det.getReferencia2() != null) {
            det.setReferencia2(operaciones.remplaceData(operaciones.remplaceData(det.getReferencia2(), datosFE),datosFE2));
            detPol.setReferencia2(det.getReferencia2());
        }
        
        if (det.getRfc() != null) {
            det.setRfc(operaciones.remplaceData(det.getRfc(), datosFE));
            detPol.setRfc(det.getRfc());
        }
     

        BigDecimal importe = BigDecimal.ZERO;

//        System.out.println("Tipo:" + det.gettAplicacion());
//        System.out.println("operacion:" + det.getOperaciones());
//        System.out.println("det.getIdcampo():" + det.getIdcampo());
        if (det.getOperaciones() == null || det.getOperaciones().trim().equals("")) {

            if (datosFE.get(det.getIdcampo().toString()) == null) {
                detRes.setIsValid(false);
                detRes.setMsgErr("Error el concepto " + det.getIdcampo().toString() + " no es valido");
                return detRes;
            }

            importe = new BigDecimal(datosFE.get(det.getIdcampo().toString()).toString());
        } else {
            
            double imp = 0;
           if(det.getOperaciones() != null){  
            if(det.getOperaciones().toString().indexOf("montoParcial") != -1){
            
                imp = operaciones.operacion(det.getOperaciones(), datosFE2);
            
            }else{
                
                imp = operaciones.operacion(det.getOperaciones(), datosFE);
            
            }
           }else{
               
               imp = operaciones.operacion(det.getOperaciones(), datosFE);
           
           }
            
            
           // double imp = operaciones.operacion(det.getOperaciones(), datosFE);
//             double imp = procesaOperacion(datosFE,det.getOperaciones() );
            importe = new BigDecimal(imp);
        }

        if (det.gettAplicacion().equals("C")) {
            detPol.setCargos(importe);
            detPol.setCargosBase(importe);
            detPol.setAbonos(BigDecimal.ZERO);
            detPol.setAbonosBase(BigDecimal.ZERO);
        }
        if (det.gettAplicacion().equals("A")) {
            detPol.setCargos(BigDecimal.ZERO);
            detPol.setCargosBase(BigDecimal.ZERO);
            detPol.setAbonosBase(importe);
            detPol.setAbonos(importe);
        }

//          DetPolizasId result = null;
//                result = detPolizasDao.save(detPol);
//                
//                if(result == null) return null;
//           detPol.setId(result)
        
         
        detRes.setIsValid(true);
        detRes.setDetalle(detPol);
        return detRes;
    }   
        
        public ResultListDetPol generaDetallePolizaPagos(PolizasId id, List<ErpDetConvertidor> detalles, Map datosFE,  Map datosFE2) {
        ResultListDetPol resLis = new ResultListDetPol();
        List<DetPolizas> detPol = new ArrayList<DetPolizas>();

        Iterator<ErpDetConvertidor> it = detalles.iterator();
        int i = 1;
        while (it.hasNext()) {
            ErpDetConvertidor det = it.next();
//            System.out.println("Cuenta:"+ det.getCuenta());
//            System.out.println("Campo:"+ det.getIdcampo());
          //  System.out.println("det.getOperaciones().toString():"+ det.getOperaciones().toString());
//            System.out.println("Importe:"+ datosFE.get(det.getIdcampo()).toString());
         if(det.getOperaciones() != null){   
            if(det.getOperaciones().toString().indexOf("paridadFacturaUSD") != -1){
            
                mensajeB = "1";
            
            }
            
            if(det.getOperaciones().toString().indexOf("paridadUSD") != -1){
            
        
                mensajeF = "1";
            
            }
            }
            
            
            ResultDetallePoliza rsult = generaDetallePagos(id, det, datosFE,datosFE2, i);
            
       
            if (!rsult.isIsValid()) {
                resLis.setLista(null);
                resLis.setValid(false);
                resLis.setErr(rsult.getMsgErr());
                return resLis;
//                return null;
            }
            detPol.add(rsult.getDetalle());
//            System.out.println("detallePol:" + detallePol.getId().getSec());
            i++;
        }

        resLis.setLista(detPol);
        resLis.setValid(true);

        return resLis;
    }
        
    public ResultFEMap getMapDatPagosParcial(String compania, PagosAllDTO dto) {
        ResultFEMap result = new ResultFEMap();
        Map par = new HashMap();
        
         Map parcial = new HashMap();
         Map cuen;
               
               parcial.put("compania", compania);
               parcial.put("numero", dto.numero);
               
                   List listPagosParciales = processDao.getMapResult("BuscaPagosParciales", parcial);
                   
              if(listPagosParciales != null){ 
                  System.out.println("listPagosParciales.size()+"+listPagosParciales.size());
                  
                   for (int i = 0;listPagosParciales.size() - 1>=i;i++){
                       
                        System.out.println("Entrando..........");
                        cuen = (HashMap) listPagosParciales.get(i);
                        
                        par.put("montoParcial-"+(i+1), cuen.get("IMPORTE_OPERACION").toString());
                        par.put("descripcionPago-"+(i+1), cuen.get("OBSERVACIONES").toString());
                        par.put("cuentaBanco-"+(i+1), cuen.get("CUENTA_BANCO").toString());
                        par.put("banco-"+(i+1), cuen.get("BANCO").toString());
                        par.put("fechaPago-"+(i+1), cuen.get("FECHA_PAGO").toString());
                        
                                
                                
                        
                        System.out.println("montoParcial-"+(i+1)+ ",  "+ cuen.get("IMPORTE_OPERACION").toString());
                   
                   }
              
                        cuen = (HashMap) listPagosParciales.get(0);
                    
                        par.put("descripcionPago", cuen.get("OBSERVACIONES").toString());
                        par.put("cuentaBanco", cuen.get("CUENTA_BANCO").toString());
                        par.put("banco", cuen.get("BANCO").toString());
                        par.put("fechaPago", cuen.get("FECHA_PAGO").toString());
                    
                
                
                
                
              }
                       

        result.setMapa(par);

        return result;
    }
        
       public ResultFEMap getMapDatPagos(String compania, PagosAllDTO dto) {
        ResultFEMap result = new ResultFEMap();
        Map par = new HashMap();
//        System.out.println("numero:"+numero);
//        System.out.println("compania:"+compania);
        ErpFeComprobantes comprobante = erpFeComprobantesDao.findErpFeComprobantes(compania, new Integer(dto.numero));
//        System.out.println("comprobante:"+comprobante);
        if (comprobante == null) {
            return null;
        } else {
            par.put("FOLIO", comprobante.getFolio());
            par.put("SUBTOTAL", comprobante.getSubTotal().toString());
            par.put("montoPagoTotal", comprobante.getTotal().toString());
            par.put("TOTAL", comprobante.getTotal().toString());
            par.put("MONEDA", comprobante.getMoneda());
            par.put("fechaFactura", comprobante.getFecha());
             par.put("UUID", comprobante.getUuid());
           

        }
     

//        System.out.println("-------------------------------------------Paridad-------------------------------------------------");
        ErpParidadCompania paridad = erpParidadCompaniaDao.buscaUltimaParidad("USD", "MXN", new Date());
//                System.out.println("PAridad:"+paridad);
        System.out.println("PAridad:"+paridad.getFactor());
        if (paridad != null) {
//            System.out.println("paridad" + paridad.getId().getDivisaOrigen());
            par.put("paridad" + paridad.getId().getDivisaOrigen(), paridad.getFactor());
            msgParidad = "numero: "+comprobante.getId().getNumero() + " -> " + "fecha: " + comprobante.getFecha()+ " -> " +"paridad: "+ paridad.getFactor();
           
        }else{
        
            msgParidad = "";
        }


        ErpParidadCompania paridadFactura = erpParidadCompaniaDao.buscaUltimaParidad("USD", "MXN", comprobante.getFecha());
        System.out.println("PAridadFactura:" + paridadFactura);
//        System.out.println("paridadFactura:" + paridadFactura.getFactor());
        if (paridadFactura != null) {
//            System.out.println("paridadFactura" + paridadFactura.getId().getDivisaOrigen());
            par.put("paridadFactura" + paridadFactura.getId().getDivisaOrigen(), paridadFactura.getFactor());
            
             msgParidadFactura = "numero: "+comprobante.getId().getNumero() + " -> " + "fecha: " + comprobante.getFecha()+ " -> " +"paridad: "+ paridadFactura.getFactor();
           // arrList.add("hola");
             
            
        }else{
        
             msgParidadFactura = "";
        }
        
         
         

        result.setComprobante(comprobante);
        ErpFeEmisor emisor = erpFeEmisorDao.findErpEmisor(compania, dto.numero);
//          System.out.println("emisor:"+emisor);
        par.put("nombreEmisor", emisor.getNombre());
        par.put("rfcEmisor", emisor.getRfc());

        ErpFeReceptor receptor = erpFeReceptorDao.findErpFeReceptor(compania, new Integer(dto.numero));
//           System.out.println("receptor:"+receptor);
        par.put("nombreReceptor", receptor.getNombre());
        par.put("rfcReceptor", receptor.getRfc());

   
        result.setMapa(par);

        return result;
    }
    
    

    public ResponseMsg2 procesa(String compania, List<ComprobanteDto> lista, String usuario) {
        // ResponseMsg rm = new ResponseMsg();
         
         ResponseMsg2 rm2 = new ResponseMsg2();
         
         arrList.clear();
         mensajeB = "0";
         mensajeF = "0";
         msgParidad = "";
         msgParidadFactura = "";
         
        
        msgErr = new StringBuilder();
        try {
        Iterator<ComprobanteDto> i = lista.iterator();
       
        while (i.hasNext()) {
            ComprobanteDto com = i.next();

            //    System.out.println("AUXILIARCONTABLE:" + com.RFC_COMPANIA);
            //    System.out.println("TIPO:" + com.TIPO);
            //    System.out.println("CUENTA_CONTABLE:" + com.CUENTA_CONTABLE);
            //    System.out.println("AUXILIAR_CONTABLE:" + com.AUXILIAR_CONTABLE);
            //      System.out.println("Pasando validaciones busqeuda" );
            if (com.FLAG_POLIZA == null) {
             //    System.out.println("Pasando validaciones 1" );
                continue;
            }

            if (com.FLAG_POLIZA.equals("false")) {
              //   System.out.println("Pasando validaciones 2" );
                continue;
            }
          //  if (com.NUMERO_POL != null) {
                
          //       System.out.println("Pasando validaciones 3" );

          //      setErr("Error fecha de poliza no encontrada:" + com.NUMERO);
          //      continue;
          //  }

            if (com.FECHA_POL == null || com.FECHA_POL.equals("")) {
                
                //    System.out.println("Pasando validaciones 4" );
                //     setErr();
                rm2.setIsGood(false);
                rm2.setMsg("Error fecha de poliza no encontrada:" + com.NUMERO);
                return rm2;
                // continue;
            }

            if (com.noCaso == null || com.noCaso.equals("")) {
                if (com.IDCONCGASTO == null || com.IDCONCGASTO.equals("")){
                
                        System.out.println("Pasando validaciones 5" );
                       setErr("Error no se asigno el convertidor:" + com.NUMERO);
                       continue;
                }else{
                
                      com.noCaso = com.IDCONCGASTO;
                
                }
            }
            // System.out.println("Pasando validaciones" );
            System.out.println("com.noCaso:" +com.noCaso);
            ResultFEMap para = getMapDatFactura(compania, com);
            //System.out.println("buscando para" );
            //System.out.println("com.noCaso:" +com.noCaso);
            ErpCatConvertidor conv = buscaConvertidor(compania, com.noCaso);
            if (conv == null) {
                setErr("Error convertidor no encontrado :" + com.NUMERO);
                rm2.setIsGood(false);
                rm2.setMsg(msgErr.toString());
                return rm2;
            }
            //System.out.println("buscando convertidor" );
            ///  List<ErpCatConvertidor>  convertidor = erpCatConvertidorDao.getConvertidor( compania,  "A",com.noCaso, "X");
       
            List<ErpDetConvertidor> detalles = erpDetConvertidorDao.findErpDetConvertidorOrigen(compania, com.noCaso,"X");

            if (detalles == null) {
                setErr("Error detalle convertidor no encontrado :" + com.NUMERO);
                rm2.setIsGood(false);
                rm2.setMsg(msgErr.toString());
                return rm2;
            }

            if (detalles.isEmpty()) {
                setErr("Error detalle convertidor no encontrado :" + com.NUMERO);
                rm2.setIsGood(false);
                rm2.setMsg(msgErr.toString());
                return rm2;
            }
              //System.out.println("buscando detalle convertidor" );
//                 System.out.println("detalles:" +detalles.size());
//                 ErpCatConvertidor conv =convertidor.get(0);
//            System.out.println("conv.getNombre():"+conv.getNombre());
//            System.out.println("operaciones:"+operaciones);
//            System.out.println("para:"+para);
            if (conv.getNombre() != null) {
                conv.setNombre(operaciones.remplaceData(conv.getNombre(), para.getMapa()));
            }

            if (conv.getDescripcion() != null) {
                conv.setDescripcion(operaciones.remplaceData(conv.getDescripcion(), para.getMapa()));
            }
            //System.out.println("genewrando poliza" );
            PolizasId polId = generaPoliza(conv, para.getComprobante(), com.FECHA_POL, usuario);
            if (polId == null) {
                setErr("Periodo no valido para :" + com.NUMERO);
                rm2.setIsGood(false);
                rm2.setMsg(msgErr.toString());
                return rm2;
            }
            //System.out.println("poliza generadad" );
            
            /**
             * Busca Datos contables del proveedor o cliente
             */
            
           

            ResultListDetPol rse = generaDetallePoliza(polId, detalles, para.getMapa());
            
            if(mensajeB.equalsIgnoreCase("1")){
            
                arrList.add(msgParidadFactura);
            
            
            }
            
            if(mensajeF.equalsIgnoreCase("1")){
            
                arrList.add(msgParidad);
            
            
            }
            
            mensajeB = "0";
            mensajeF = "0";

            if (!rse.isValid()) {
                setErr("Error al generar el detalle Poliza de :" + com.NUMERO + ", " + rse.getErr());
                rm2.setIsGood(false);
                rm2.setMsg(msgErr.toString());
                return rm2;
            }

            List<DetPolizas> listaDet = rse.getLista();
            List<DetPolizas> listaDet2 = listaDet;

            boolean detSave = detPolizasDao.saveListDet(listaDet);

            if (!detSave) {
                setErr("Error al guardar el detalle Poliza de :" + com.NUMERO);
                rm2.setIsGood(false);
                rm2.setMsg(msgErr.toString());
                return rm2;
            }

            Iterator<DetPolizas> sumDet = listaDet2.iterator();
            double cargos = 0.0d;
            double abonos = 0.0d;
            
            DetPolizasXTransaccion transaccion = new DetPolizasXTransaccion();
           DetPolizasXTransaccionId transaccionId = new DetPolizasXTransaccionId();
           
            ErpSatTransaccion erpTrans = new ErpSatTransaccion();
              ErpSatTransaccionId erpTransId = new ErpSatTransaccionId();
              
              erpTransId.setCompania(compania);
              int idTransaccion = erpSatTransaccionDao.getMaxId(erpTransId);
              erpTransId.setId(new BigDecimal(idTransaccion));
              erpTrans.setId(erpTransId);
              erpTrans.setTipo("CompNal");
              erpTrans.setUuidCfdi(com.uuid);
              
              
              Map rfcCompania = new HashMap();
              //String rfcComp = "";
              rfcCompania.put("compania", compania);
               
              List listRfc = processDao.getMapResult("BuscaRFCCompania", rfcCompania);
              
                 if (listRfc != null){
                     
                     erpTrans.setRfc(com.RFC_EMISOR);

                     
                
                 }
                  
                 
                 erpTrans.setMontoTotal(new BigDecimal(com.TOTAL));
                 
                 if (!com.MONEDA.equalsIgnoreCase("MXN") && !com.MONEDA.equalsIgnoreCase("USD")){
                     
                     erpTrans.setMoneda("MXN");
                 
                 
                 }else{
                     
                     erpTrans.setMoneda(com.MONEDA);
                 
                 }
                 
                   if (com.TIPO_CAMBIO == null || com.TIPO_CAMBIO == ""){
                     
//                     System.out.println("Valor 1 a tipo de cambio");
                      erpTrans.setTipCamb(new BigDecimal(1));
                 }else{
                       
                    if(com.TIPO_CAMBIO.equalsIgnoreCase("N/A")){  
                        
                         erpTrans.setTipCamb(new BigDecimal(1));
                    }else{

                        System.out.println("Valor que traiga a tipo de cambio "+com.TIPO_CAMBIO);

                       erpTrans.setTipCamb(new BigDecimal(com.TIPO_CAMBIO));
                   }
                 }
              // 
                   
              // if (rfcComp.get("CUENTA_TITULO").toString().equalsIgnoreCase("000001")){
              
            
                 
                 
                 erpSatTransaccionDao.save(erpTrans);

            while (sumDet.hasNext()) {
                DetPolizas dd = sumDet.next();
                //  System.out.println("dd.getCargos():"+dd.getCargos());
                cargos += dd.getCargos().doubleValue();
                //   System.out.println("cargos:"+cargos);   
                abonos += dd.getAbonos().doubleValue();
                
                
           
           transaccionId.setCompania(compania);
           transaccionId.setFecha(dd.getId().getFecha());
           transaccionId.setTipoPoliza(dd.getId().getTipoPoliza());
           transaccionId.setNumero(dd.getId().getNumero());
           transaccionId.setSec(dd.getId().getSec().intValue());
           transaccionId.setIdTransaccion(idTransaccion);
           transaccion.setId(transaccionId);
           
           // System.out.println("uuid1: "+com.uuid);
           // System.out.println("uuid2: "+dd.getReferencia());
           // System.out.println("uuid3: "+com.uuid.equalsIgnoreCase(dd.getReferencia()));
            
            if (com.uuid.equalsIgnoreCase(dd.getReferencia()) || com.uuid.equalsIgnoreCase(dd.getDescripcion())){
             
     
                    detPolizasXTransaccionDao.save(transaccion);
            
            }
            }
//            System.out.println("cargos:" + cargos);
//            System.out.println("abonos:" + abonos);
            String estatus = "0";
            if (cargos != abonos) {
                estatus = "1";
            }
            if (cargos == abonos) {
                estatus = "0";
            }
            boolean guardaTot = polizasDao.guardaTotales(polId, new BigDecimal(cargos), new BigDecimal(abonos), estatus);
//            System.out.println("guardaTot:" + guardaTot);
            Querys que = new Querys();
            String store = que.getSQL("CONTA_INSERTA_DETPOLIZAS_FOVI3");
            Map parametros = new HashMap();
//                System.out.println("store:" + store);
            parametros.put("compania", polId.getCompania());
            parametros.put("NUMERO", polId.getNumero());
            parametros.put("FECHA", com.FECHA_POL);
            parametros.put("TIPO_POLIZA", polId.getTipoPoliza());
            parametros.put("CARGOS", "0");
            parametros.put("ABONOS", "0");
            parametros.put("OPERACION", "S");
            parametros.put("OPE_SUMA", "1");
            int val = processDao.execute2(store, parametros);
//                System.out.println("val:"+ val);

//                 polizasDao.sumDetalles(polId);
            relacionaPolizaXFE(polId, para.getComprobante());
            actualizaFE(polId, para.getComprobante(), com.noCaso);
            
            
            

        }

        }catch(Exception e){
            e.printStackTrace();
            setErr("Error al guardar el detalle Poliza");
                rm2.setIsGood(false);
                rm2.setMsg(msgErr.toString());
                return rm2;
        }
        
        
        rm2.setIsGood(true);
        rm2.setList(arrList);
        return rm2;
        
    }
    
        public ResponseMsg2 procesaMult(String compania, List<ComprobanteDto> lista, String usuario, String tipoPoliza) {
         ResponseMsg2 rm2 = new ResponseMsg2();
        msgErr = new StringBuilder();
        
        arrList.clear();
         mensajeB = "0";
         mensajeF = "0";
         msgParidad = "";
         msgParidadFactura = "";
        
        try {
        Iterator<ComprobanteDto> i = lista.iterator();
        
        PolizasId polId = null;
        int count = 0;
        double cargos = 0.0d;
        double abonos = 0.0d;
        setContDet(1);
        
        while (i.hasNext()) {
            ComprobanteDto com = i.next();

            if (com.FLAG_POLIZA == null) {

                continue;
            }

            if (com.FLAG_POLIZA.equals("false")) {
     
                continue;
            }

            if (com.FECHA_POL == null || com.FECHA_POL.equals("")) {

                rm2.setIsGood(false);
                rm2.setMsg("Error fecha de poliza no encontrada:" + com.NUMERO);
                return rm2;

            }

            if (com.noCaso == null || com.noCaso.equals("")) {
                if (com.IDCONCGASTO == null || com.IDCONCGASTO.equals("")){
                
                        System.out.println("Pasando validaciones 5" );
                       setErr("Error no se asigno el convertidor:" + com.NUMERO);
                       continue;
                }else{
                
                      com.noCaso = com.IDCONCGASTO;
                
                }
            }

            System.out.println("com.noCaso:" +com.noCaso);
            ResultFEMap para = getMapDatFactura(compania, com);

            ErpCatConvertidor conv = buscaConvertidor(compania, com.noCaso);
            if (conv == null) {
                setErr("Error convertidor no encontrado :" + com.NUMERO);
                rm2.setIsGood(false);
                rm2.setMsg(msgErr.toString());
                return rm2;
            }

            List<ErpDetConvertidor> detalles = erpDetConvertidorDao.findErpDetConvertidorOrigen(compania, com.noCaso,"X");

            if (detalles == null) {
                setErr("Error detalle convertidor no encontrado :" + com.NUMERO);
                rm2.setIsGood(false);
                rm2.setMsg(msgErr.toString());
                return rm2;
            }

            if (detalles.isEmpty()) {
                setErr("Error detalle convertidor no encontrado :" + com.NUMERO);
                rm2.setIsGood(false);
                rm2.setMsg(msgErr.toString());
                return rm2;
            }

            if (conv.getNombre() != null) {
                conv.setNombre(operaciones.remplaceData(conv.getNombre(), para.getMapa()));
            }

            if (conv.getDescripcion() != null) {
                conv.setDescripcion(operaciones.remplaceData(conv.getDescripcion(), para.getMapa()));
            }
            
            if(tipoPoliza != null && tipoPoliza != ""){
                conv.settPoliza(tipoPoliza);
            }

            if (count == 0){
            
              polId = generaPoliza(conv, para.getComprobante(), com.FECHA_POL, usuario);
            
            }
            
            if (polId == null) {
                setErr("Periodo no valido para :" + com.NUMERO);
                rm2.setIsGood(false);
                rm2.setMsg(msgErr.toString());
                return rm2;
            }
            
            count++;

          System.out.println("getContDet()"+getContDet());
          
            ResultListDetPol rse = generaDetallePolizaMult(polId, detalles, para.getMapa(),getContDet());
            
             if(mensajeB.equalsIgnoreCase("1")){
            
                arrList.add(msgParidadFactura);
            
            
            }
             
             if(mensajeF.equalsIgnoreCase("1")){
            
                arrList.add(msgParidad);
            
            
            }
            
            mensajeB = "0";
            mensajeF = "0";
            
           System.out.println("getContDet()"+getContDet());

            if (!rse.isValid()) {
                setErr("Error al generar el detalle Poliza de :" + com.NUMERO + ", " + rse.getErr());
                rm2.setIsGood(false);
                rm2.setMsg(msgErr.toString());
                return rm2;
            }

            List<DetPolizas> listaDet = rse.getLista();
            List<DetPolizas> listaDet2 = listaDet;

            boolean detSave = detPolizasDao.saveListDet(listaDet);

            if (!detSave) {
                setErr("Error al guardar el detalle Poliza de :" + com.NUMERO);
                rm2.setIsGood(false);
                rm2.setMsg(msgErr.toString());
                return rm2;
            }

            Iterator<DetPolizas> sumDet = listaDet2.iterator();
           
            
            DetPolizasXTransaccion transaccion = new DetPolizasXTransaccion();
           DetPolizasXTransaccionId transaccionId = new DetPolizasXTransaccionId();
           
            ErpSatTransaccion erpTrans = new ErpSatTransaccion();
              ErpSatTransaccionId erpTransId = new ErpSatTransaccionId();
              
              erpTransId.setCompania(compania);
              int idTransaccion = erpSatTransaccionDao.getMaxId(erpTransId);
              erpTransId.setId(new BigDecimal(idTransaccion));
              erpTrans.setId(erpTransId);
              erpTrans.setTipo("CompNal");
              erpTrans.setUuidCfdi(com.uuid);
              
              
              Map rfcCompania = new HashMap();
              //String rfcComp = "";
              rfcCompania.put("compania", compania);
               
              List listRfc = processDao.getMapResult("BuscaRFCCompania", rfcCompania);
              
                 if (listRfc != null){
                     
                     erpTrans.setRfc(com.RFC_EMISOR);

                     
                
                 }
                  
                 
                 erpTrans.setMontoTotal(new BigDecimal(com.TOTAL));
                 
                 if (com.MONEDA == "" || com.MONEDA == null){
                     
                     erpTrans.setMoneda(com.MONEDA);
                     
                 }else{
                 
                 
                 
                    if (!com.MONEDA.equalsIgnoreCase("MXN") && !com.MONEDA.equalsIgnoreCase("USD")){

                        erpTrans.setMoneda("MXN");


                    }else{

                        erpTrans.setMoneda(com.MONEDA);

                    }
                 }
                 
                 if (com.TIPO_CAMBIO == "" || com.TIPO_CAMBIO == null){
                     
                     erpTrans.setTipCamb(new BigDecimal(1));
                 
                 }else{
                 
                   if (com.TIPO_CAMBIO.equalsIgnoreCase("N/A") || com.TIPO_CAMBIO == null || com.TIPO_CAMBIO.isEmpty() || com.TIPO_CAMBIO.toString().equalsIgnoreCase("") ){
                     
//                     System.out.println("Valor 1 a tipo de cambio");
                      erpTrans.setTipCamb(new BigDecimal(1));
                 }else{
                     
                      System.out.println("Valor que traiga a tipo de cambio "+com.TIPO_CAMBIO);
                     
                     erpTrans.setTipCamb(new BigDecimal(com.TIPO_CAMBIO));
                 }

                 }
                 erpSatTransaccionDao.save(erpTrans);

            while (sumDet.hasNext()) {
                DetPolizas dd = sumDet.next();
                //  System.out.println("dd.getCargos():"+dd.getCargos());
                cargos += dd.getCargos().doubleValue();
                //   System.out.println("cargos:"+cargos);   
                abonos += dd.getAbonos().doubleValue();
                
                
           
           transaccionId.setCompania(compania);
           transaccionId.setFecha(dd.getId().getFecha());
           transaccionId.setTipoPoliza(dd.getId().getTipoPoliza());
           transaccionId.setNumero(dd.getId().getNumero());
           transaccionId.setSec(dd.getId().getSec().intValue());
           transaccionId.setIdTransaccion(idTransaccion);
           transaccion.setId(transaccionId);
           

            if (com.uuid.equalsIgnoreCase(dd.getReferencia()) || com.uuid.equalsIgnoreCase(dd.getDescripcion())){
             
     
                    detPolizasXTransaccionDao.save(transaccion);
            
            }
            }

          

            relacionaPolizaXFE(polId, para.getComprobante());
            actualizaFE(polId, para.getComprobante(), com.noCaso);
            
            
            

        }
        
            System.out.println("polId.getNumero()"+polId.getNumero());
            System.out.println("cargos"+cargos);
            System.out.println("abonos"+abonos);
        
             String estatus = "0";
            if (cargos != abonos) {
                estatus = "1";
            }
            if (cargos == abonos) {
                estatus = "0";
            }
        
            DateFormat fechaHora = new SimpleDateFormat("dd/MM/yyyy");
            String convertido = fechaHora.format(polId.getFecha());
            boolean guardaTot = polizasDao.guardaTotales(polId, new BigDecimal(cargos), new BigDecimal(abonos), estatus);
            Querys que = new Querys();
            String store = que.getSQL("CONTA_INSERTA_DETPOLIZAS_FOVI3");
            Map parametros = new HashMap();
            parametros.put("compania", polId.getCompania());
            parametros.put("NUMERO", polId.getNumero());
            parametros.put("FECHA", convertido);
            parametros.put("TIPO_POLIZA", polId.getTipoPoliza());
            parametros.put("CARGOS", "0");
            parametros.put("ABONOS", "0");
            parametros.put("OPERACION", "S");
            parametros.put("OPE_SUMA", "1");
            int val = processDao.execute2(store, parametros);


        }catch(Exception e){
            e.printStackTrace();
            setErr("Error al guardar el detalle Poliza");
                rm2.setIsGood(false);
                rm2.setMsg(msgErr.toString());
                return rm2;
        }
        
        
        rm2.setIsGood(true);
        rm2.setList(arrList);
        return rm2;
        
    }

    public ErpCatConvertidor buscaConvertidor(String compania, String caso) {
        List<ErpCatConvertidor> convertidor = erpCatConvertidorDao.getConvertidor(compania, "A", caso, "X");
        if (convertidor == null) {
            return null;
        } else if (convertidor.isEmpty()) {
            return null;
        } else {
            return convertidor.get(0);
        }
    }

    public void setErr(String err) {
        msgErr.append(err);
        msgErr.append("-");
    }

    public void actualizaFE(PolizasId polId, ErpFeComprobantes comp, String noCaso) {
        erpFeComprobantesDao.actualizaComprobancePoliza(polId, comp, noCaso);
    }

    public ResultFEMap getMapDatFactura(String compania, ComprobanteDto dto) {
        ResultFEMap result = new ResultFEMap();
        Map par = new HashMap();
//        System.out.println("numero:"+numero);
//        System.out.println("compania:"+compania);
        ErpFeComprobantes comprobante = erpFeComprobantesDao.findErpFeComprobantes(compania, new Integer(dto.NUMERO));
//        System.out.println("comprobante:"+comprobante);
        if (comprobante == null) {
            return null;
        } else {
            par.put("FOLIO", comprobante.getFolio());
            par.put("SUBTOTAL", comprobante.getSubTotal().toString());
            par.put("TOTAL", comprobante.getTotal().toString());
            par.put("MONEDA", comprobante.getMoneda());
            par.put("fechaFactura", comprobante.getFecha());
             par.put("UUID", comprobante.getUuid());
             
             if(comprobante.getDescuento() != null){
                 
                 par.put("DESCUENTO", comprobante.getDescuento());
             
             
             }else{
                 
                 par.put("DESCUENTO", "0");
             
             }

        }
        
           if(dto.TIPO.equals("Proveedor")){
                par.put("cuentaProveedor", dto.CUENTA_CONTABLE);
                par.put("auxiliarProveedor", dto.AUXILIAR_CONTABLE);
            }else{
                par.put("cuentaCliente", dto.CUENTA_CONTABLE);
                par.put("auxiliarCliente", dto.AUXILIAR_CONTABLE);
            }
        

//        System.out.println("-------------------------------------------Paridad-------------------------------------------------");
        ErpParidadCompania paridad = erpParidadCompaniaDao.buscaUltimaParidad("USD", "MXN", new Date());
//                System.out.println("PAridad:"+paridad);
        System.out.println("PAridad:"+paridad.getFactor());
        if (paridad != null) {
//            System.out.println("paridad" + paridad.getId().getDivisaOrigen());
            par.put("paridad" + paridad.getId().getDivisaOrigen(), paridad.getFactor());
            msgParidad = "numero: "+comprobante.getId().getNumero() + " -> " + "fecha: " + comprobante.getFecha()+ " -> " +"paridad: "+ paridad.getFactor();
           
        }else{
        
            msgParidad = "";
        }
//          SimpleDateFormat formatoDelTexto2 = new SimpleDateFormat("dd/MM/yyyy");
//           String fechaF = comprobante.getFecha();        
//           Date fechaFactura = null;
//                 //Obtener maxima secuencia
//           fechaFactura = formatoDelTexto2.parse();

        ErpParidadCompania paridadFactura = erpParidadCompaniaDao.buscaUltimaParidad("USD", "MXN", comprobante.getFecha());
        System.out.println("PAridadFactura:" + paridadFactura);
//        System.out.println("paridadFactura:" + paridadFactura.getFactor());
        if (paridadFactura != null) {
//            System.out.println("paridadFactura" + paridadFactura.getId().getDivisaOrigen());
            par.put("paridadFactura" + paridadFactura.getId().getDivisaOrigen(), paridadFactura.getFactor());
            
             msgParidadFactura = "numero: "+comprobante.getId().getNumero() + " -> " + "fecha: " + comprobante.getFecha()+ " -> " +"paridad: "+ paridadFactura.getFactor();
           // arrList.add("hola");
             
            
        }else{
        
             msgParidadFactura = "";
        }
        
         
         

        result.setComprobante(comprobante);
        ErpFeEmisor emisor = erpFeEmisorDao.findErpEmisor(compania, dto.NUMERO);
//          System.out.println("emisor:"+emisor);
        par.put("nombreEmisor", emisor.getNombre());
        par.put("rfcEmisor", emisor.getRfc());

        ErpFeReceptor receptor = erpFeReceptorDao.findErpFeReceptor(compania, new Integer(dto.NUMERO));
//           System.out.println("receptor:"+receptor);
        par.put("nombreReceptor", receptor.getNombre());
        par.put("rfcReceptor", receptor.getRfc());
        
        
        System.out.println("-------------Buscando importe ISH-------------");
        
        List<ErpFeTrasLocales> tLoc = erpFeTrasLocalesDao.FindErpFeTrasLocales(compania, new Integer(dto.NUMERO));
        
         if (tLoc != null) {
            Iterator<ErpFeTrasLocales> itLoc = tLoc.iterator();
            
            BigDecimal importeImpLocalTotal = BigDecimal.ZERO;

            while (itLoc.hasNext()) {
                ErpFeTrasLocales trasLoc = itLoc.next();
                  System.out.println(trasLoc.getImpLocTraslado()+"->"+trasLoc.getImporte().toString());
                  
                  if(trasLoc.getImpLocTraslado().equalsIgnoreCase("ISH") || trasLoc.getImpLocTraslado().equalsIgnoreCase("IH") || trasLoc.getImpLocTraslado().equalsIgnoreCase("I.S.H")){
                      
                      importeImpLocalTotal = importeImpLocalTotal.add(trasLoc.getImporte());
                  
                  }
                  
                
                  
                  System.out.println("ISH ->"+importeImpLocalTotal.toString());
               
            }
            
            System.out.println("ISH TOTAL ->"+importeImpLocalTotal.toString());
            
             par.put("ISH", importeImpLocalTotal.toString());
             par.put("IMP", importeImpLocalTotal.toString());
            
        }

        List<ErpFeImpTraslados> traslados = erpFeImpTrasladosDao.findErpImpTraslados(compania, new Integer(dto.NUMERO));
        //    System.out.println("traslados:"+traslados);
        if (traslados != null) {
            Iterator<ErpFeImpTraslados> it1 = traslados.iterator();
            
            BigDecimal importeIvaTotal = BigDecimal.ZERO;

            while (it1.hasNext()) {
                ErpFeImpTraslados tras = it1.next();
                  System.out.println(tras.getImpuesto()+"->"+tras.getImporte().toString());
                  
                  if(tras.getImpuesto().equalsIgnoreCase("002")){
                      
                      importeIvaTotal = importeIvaTotal.add(tras.getImporte());
                  
                  }
                  
                  if(tras.getImpuesto().equalsIgnoreCase("IVA")){
                      
                      importeIvaTotal = importeIvaTotal.add(tras.getImporte());
                  
                  }
                  
                  System.out.println("IVATOTAL ->"+importeIvaTotal.toString());
               
            }
            
            System.out.println("IVATOTAL ->"+importeIvaTotal.toString());
            
             par.put("IVA", importeIvaTotal.toString());
            
        }

        List<ErpFeImpRetenciones> retenciones = erpFeImpRetencionesDao.findErpImpRetenciones(compania, new Integer(dto.NUMERO));
//                    System.out.println("retenciones:"+retenciones); 
        if (retenciones != null) {
            Iterator<ErpFeImpRetenciones> it1 = retenciones.iterator();

            while (it1.hasNext()) {
                ErpFeImpRetenciones tras = it1.next();
//                     System.out.println("RET_"+tras.getImpuesto()+"->"+tras.getImporte().toString());
                par.put("RET_" + tras.getImpuesto(), tras.getImporte().toString());
            }
        }

        
         List<ErpFeConceptos> conceptos = erpFeConceptosDao.findErpFeConceptos2(compania,dto.NUMERO);
        
        int ic=0;
        if (conceptos != null) {
            Iterator<ErpFeConceptos> it1 = conceptos.iterator();

            while (it1.hasNext()) {
                ErpFeConceptos conc = it1.next();
                System.out.println("conc:"+conc.getDescripcion());
                if(ic==0){
                    if(conc.getDescripcion().length()>=500)
                        par.put("CONCEPTO", conc.getDescripcion().substring(0, 490));
                    else
                          par.put("CONCEPTO", conc.getDescripcion());
                } else{
                     if(conc.getDescripcion().length()>=500)
                         par.put("CONCEPTO" + ic, conc.getDescripcion().substring(0, 490));
                     else
                           par.put("CONCEPTO", conc.getDescripcion());
                }
                ic++;
            }
        }
         
         
        result.setMapa(par);
        
      

       
       
       
        return result;
    }

    public void relacionaPolizaXFE(PolizasId polizaId, ErpFeComprobantes comp) {

        ErpPolizasXFacturasId pxfeid = new ErpPolizasXFacturasId();
        pxfeid.setCompania(polizaId.getCompania());
        pxfeid.setFechaPol(polizaId.getFecha());
        pxfeid.setNumeroPol(polizaId.getNumero());
        pxfeid.setTipoPol(polizaId.getTipoPoliza());
        pxfeid.setOperacion("GE");

        ErpPolizasXFacturas pxfe = new ErpPolizasXFacturas();
        pxfe.setId(pxfeid);
        pxfe.setNumeroFe(comp.getId().getNumero());
        pxfe.setUuid(comp.getUuid());
        pxfe.setFolio(comp.getFolio());
        pxfe.setIdFactura("" + comp.getId().getNumero());

        ErpPolizasXFacturasId relacion = erpPolizasXFacturasDao.save(pxfe);

    }
    
    public void relacionaPolizaXPagos(PolizasId polizaId,  ErpFeComprobantes comp) {

        ErpPolizasXPagosId pxfeid = new ErpPolizasXPagosId();
        pxfeid.setCompania(polizaId.getCompania());
        pxfeid.setFechaPol(polizaId.getFecha());
        pxfeid.setNumeroPol(polizaId.getNumero());
        pxfeid.setTipoPol(polizaId.getTipoPoliza());
       

        ErpPolizasXPagos pxfe = new ErpPolizasXPagos();
        pxfe.setId(pxfeid);
        pxfe.setNumeroFe(""+comp.getId().getNumero());
        pxfe.setUuid(comp.getUuid());
        pxfe.setFolio(comp.getFolio());
        pxfe.setIdFactura("" +comp.getId().getNumero());
        pxfe.setOrigen("CXP");
        pxfe.setOperacion("GE");

        ErpPolizasXPagosId relacion = erpPolizasXPagosDao.save(pxfe);

    }

    public ResultListDetPol generaDetallePoliza(PolizasId id, List<ErpDetConvertidor> detalles, Map datosFE) {
        ResultListDetPol resLis = new ResultListDetPol();
        List<DetPolizas> detPol = new ArrayList<DetPolizas>();

        Iterator<ErpDetConvertidor> it = detalles.iterator();
        int i = 1;
        while (it.hasNext()) {
            ErpDetConvertidor det = it.next();
//            System.out.println("Cuenta:"+ det.getCuenta());
//            System.out.println("Campo:"+ det.getIdcampo());
           // System.out.println("det.getOperaciones().toString():"+ det.getOperaciones().toString());
//            System.out.println("Importe:"+ datosFE.get(det.getIdcampo()).toString());
          
          if(det.getOperaciones() != null){ 
            if(det.getOperaciones().toString().indexOf("paridadFacturaUSD") != -1){
            
                mensajeB = "1";
            
            }
            
            if(det.getOperaciones().toString().indexOf("paridadUSD") != -1){
            
                mensajeF = "1";
            
            }
          }
            
            
            
            ResultDetallePoliza rsult = generaDetalle(id, det, datosFE, i);
            
            if(det.getConceptoDiot() != null){
            
              boolean diot = generaConceptoDiot(id, det,datosFE, i);
              
            }  

            if (!rsult.isIsValid()) {
                resLis.setLista(null);
                resLis.setValid(false);
                resLis.setErr(rsult.getMsgErr());
                return resLis;
//                return null;
            }
            detPol.add(rsult.getDetalle());
//            System.out.println("detallePol:" + detallePol.getId().getSec());
            i++;
        }

        resLis.setLista(detPol);
        resLis.setValid(true);

        return resLis;
    }

       public ResultListDetPol generaDetallePolizaMult(PolizasId id, List<ErpDetConvertidor> detalles, Map datosFE,int i) {
        ResultListDetPol resLis = new ResultListDetPol();
        List<DetPolizas> detPol = new ArrayList<DetPolizas>();

        Iterator<ErpDetConvertidor> it = detalles.iterator();
        
       
       
        while (it.hasNext()) {
            ErpDetConvertidor det = it.next();
            System.out.println("Cuenta:"+ det.getCuenta());
            System.out.println("Campo:"+ det.getIdcampo());
            System.out.println("Tipo:"+ det.gettAplicacion());
            System.out.println("Importe:"+ datosFE.get(det.getIdcampo()).toString());
            
           if(det.getOperaciones() != null){  
            
             if(det.getOperaciones().toString().indexOf("paridadFacturaUSD") != -1){
            
                mensajeB = "1";
            
            }
             
             if(det.getOperaciones().toString().indexOf("paridadUSD") != -1){
            
                mensajeF = "1";
            
            }
           }
            
           
           
           
            ResultDetallePoliza rsult = generaDetalle(id, det, datosFE, i);
            
            
            
            if(det.getConceptoDiot() != null){
            
              boolean diot = generaConceptoDiot(id, det,datosFE, i);
              
            }  

            if (!rsult.isIsValid()) {
                resLis.setLista(null);
                resLis.setValid(false);
                resLis.setErr(rsult.getMsgErr());
                return resLis;
//                return null;
            }
            detPol.add(rsult.getDetalle());
//            System.out.println("detallePol:" + detallePol.getId().getSec());
            i++;
            setContDet(i);
        }

        resLis.setLista(detPol);
        resLis.setValid(true);

        return resLis;
    }

    public PolizasId generaPoliza(ErpCatConvertidor convertidor, ErpFeComprobantes comprobante, String fecha, String usuario) {
//        System.out.println("Convertidor:"+ convertidor.gettPoliza());
//        System.out.println("Convertidor:"+ convertidor.getRfcEmisor());

        PolizasId id = generaPoliza(convertidor, fecha, usuario, comprobante);
        if (id == null) {
            return null;
        }
//        System.out.println("numero:"+id.getNumero());

        return id;

    }
    


    public ResultDetallePoliza generaDetalle(PolizasId poliza, ErpDetConvertidor det, Map datosFE, int sec) {
        ResultDetallePoliza detRes = new ResultDetallePoliza();
//        DetPolizasId detalle = new DetPolizasId();

        DetPolizasId idDet = new DetPolizasId();
        idDet.setCompania(poliza.getCompania());
        idDet.setNumero(poliza.getNumero());
        idDet.setTipoPoliza(poliza.getTipoPoliza());
        idDet.setFecha(poliza.getFecha());
        idDet.setSec(new BigDecimal(sec));
        DetPolizas detPol = new DetPolizas();

        detPol.setId(idDet);
        
        if(det.getC_costos() == null){
          detPol.setcCostos("00");
        }else{
        
           detPol.setcCostos(det.getC_costos());
          
        }
        
     //   erpFeConceptosDao.findErpFeConceptos2(msgParidad, mensajeB);
         
        System.out.println("cuentaProveedores:" + datosFE.get("cuentaProveedor") + "-----");
        System.out.println("cuentaClientes:" + datosFE.get("cuentaCliente"));
        System.out.println("auxiliarProveedores:" + datosFE.get("auxiliarProveedor"));
        System.out.println("auxiliarProveedores:" + datosFE.get("auxiliarCliente"));

        System.out.println("de cuenta:" + det.getCuenta());
        
        if (det.getCuenta() != null) {
            det.setCuenta(operaciones.remplaceData(det.getCuenta(), datosFE));
            detPol.setCuenta(det.getCuenta());
        }
    
        System.out.println("cuenta:" + det.getCuenta());
        datosFE.put("compania", poliza.getCompania());
        datosFE.put("CUENTA_ALIAS", det.getCuenta().trim());
        List list = processDao.getMapResult("BuscaCuentaCorrecta", datosFE);

        if (list == null || list.size() == 0) {
            detRes.setIsValid(false);
            detRes.setMsgErr("Error la cuenta " + det.getCuenta() + " no es valido");
            return detRes;
        }

        String cuenta_real = getVal(list.get(0).toString(), 0);
           System.out.println("cuenta_real:" + cuenta_real);
                        
//               ss.cuenta = cuenta_real;
//                parametros.put("CUENTA", cuenta_real);
        
   
        detPol.setCuenta(cuenta_real);

        detPol.setFechaCap(new Date());
        if (det.getDescripcion() != null) {
            System.out.println("ddatosFE:"+datosFE.get("CONCEPTO"));
            det.setDescripcion(operaciones.remplaceData(det.getDescripcion(), datosFE));
            detPol.setDescripcion(det.getDescripcion());
        }

        if (det.getReferencia() != null) {
            det.setReferencia(operaciones.remplaceData(det.getReferencia(), datosFE));
            detPol.setReferencia(det.getReferencia());
        }
        if (det.getReferencia2() != null) {
            det.setReferencia2(operaciones.remplaceData(det.getReferencia2(), datosFE));
            detPol.setReferencia2(det.getReferencia2());
        }
        
        if (det.getRfc() != null) {
            det.setRfc(operaciones.remplaceData(det.getRfc(), datosFE));
            detPol.setRfc(det.getRfc());
        }
     

        BigDecimal importe = BigDecimal.ZERO;

//        System.out.println("Tipo:" + det.gettAplicacion());
//        System.out.println("operacion:" + det.getOperaciones());
//        System.out.println("det.getIdcampo():" + det.getIdcampo());
        if (det.getOperaciones() == null || det.getOperaciones().trim().equals("")) {

            if (datosFE.get(det.getIdcampo().toString()) == null) {
                detRes.setIsValid(false);
                detRes.setMsgErr("Error el concepto " + det.getIdcampo().toString() + " no es valido");
                return detRes;
            }

            importe = new BigDecimal(datosFE.get(det.getIdcampo().toString()).toString());
        } else {
            double imp = operaciones.operacion(det.getOperaciones(), datosFE);
//             double imp = procesaOperacion(datosFE,det.getOperaciones() );
            importe = new BigDecimal(imp);
        }

        if (det.gettAplicacion().equals("C")) {
            detPol.setCargos(importe);
            detPol.setCargosBase(importe);
            detPol.setAbonos(BigDecimal.ZERO);
            detPol.setAbonosBase(BigDecimal.ZERO);
        }
        if (det.gettAplicacion().equals("A")) {
            detPol.setCargos(BigDecimal.ZERO);
            detPol.setCargosBase(BigDecimal.ZERO);
            detPol.setAbonosBase(importe);
            detPol.setAbonos(importe);
        }

//          DetPolizasId result = null;
//                result = detPolizasDao.save(detPol);
//                
//                if(result == null) return null;
//           detPol.setId(result)
        
         
        detRes.setIsValid(true);
        detRes.setDetalle(detPol);
        return detRes;
    }

    private String getVal(String jsonVal, int pos) {
        jsonVal = jsonVal.replaceAll("\\{", "");
        jsonVal = jsonVal.replaceAll("\\}", "");
        jsonVal = jsonVal.replaceAll("\\'", "");
        jsonVal = jsonVal.replaceAll("\"", "");
        String values[] = jsonVal.split(",");
        jsonVal = values[pos];
        jsonVal = jsonVal.substring(jsonVal.indexOf("=") + 1, jsonVal.length());
        return jsonVal;
    }

    public double procesaOperacion(Map datos, String operacion) {

        Querys a = new Querys();
        String val = a.remplazaParametros(operacion, datos);

        System.out.println("Expresion:" + val);
        try {

            Calculable calc = new ExpressionBuilder(val)
                    //      .withVariable("x", varX)
                    //      .withVariable("y", varY)
                    .build();
            double result = calc.calculate();
//            Expression e = new ExpressionBuilder(val)
//                    .variables()
//                    .build();
////        .setVariable("x", 2.3)
////        .setVariable("y", 3.14);
//
//            double result = e.evaluate();
//            System.out.println("resul:" + result);
            return result;
        } catch (Exception e) {

            e.printStackTrace();
            return 0.0d;
        }
    }

    public PolizasId generaDetallePoliza(String compania, String fecha, String tipo, String usuario, ErpFeComprobantes comprobante) {
//         System.out.println("FECHA:"+fecha);
        PolizasId id = new PolizasId();
        id.setCompania(compania);
        Date fe = pasaFecha(fecha);
        id.setFecha(fe);
        id.setTipoPoliza(tipo);
        Polizas poliza = new Polizas();
        poliza.setId(id);
        poliza.setDivisa("PES");
        poliza.setNombre(comprobante.getFolio());
        poliza.setParidad(BigDecimal.ONE);
        poliza.setAbonos(BigDecimal.ZERO);
        poliza.setAbonosBase(BigDecimal.ZERO);
        poliza.setCargos(BigDecimal.ZERO);
        poliza.setCargosBase(BigDecimal.ZERO);
        poliza.setFechaCap(new Date());
        poliza.setEstatus(BigDecimal.ZERO);
        poliza.setReferencia(comprobante.getUuid());
        poliza.setFuente(comprobante.getRfc());

        poliza.setHora("" + System.currentTimeMillis());
        poliza.setUsuario(usuario);

        boolean periodo = maestroValidation.validaPeriodo(poliza);

////            System.out.println("periodo:" + periodo);
//            if (!periodo) {
//                rq.setSuccess(false);
//                rq.setData(null);
//                rq.setMsg(maestroValidation.getMsgError());
//                return rq;
//            }
        PolizasId id2 = polizasDao.save(poliza);
        return id2;
    }

    public PolizasId generaPoliza(ErpCatConvertidor convertidor, String fecha, String usuario, ErpFeComprobantes comprobante) {
//         System.out.println("FECHA:"+fecha);
        PolizasId id = new PolizasId();
        id.setCompania(convertidor.getId().getCompania());
        Date fe = pasaFecha(fecha);
        id.setFecha(fe);
        id.setTipoPoliza(convertidor.gettPoliza());
        Polizas poliza = new Polizas();
        poliza.setId(id);
        poliza.setDivisa("PES");

        if (convertidor.getDescripcion() != null) {
            poliza.setNombre(convertidor.getDescripcion());
        }

//             if(convertidor.getDescripcion() != null){
//                 
//              poliza.setReferencia(convertidor.getDescripcion());
//            }
        poliza.setParidad(BigDecimal.ONE);
        poliza.setAbonos(BigDecimal.ZERO);
        poliza.setAbonosBase(BigDecimal.ZERO);
        poliza.setCargos(BigDecimal.ZERO);
        poliza.setCargosBase(BigDecimal.ZERO);
        poliza.setFechaCap(new Date());
        poliza.setEstatus(BigDecimal.ZERO);
        poliza.setReferencia(comprobante.getUuid());
        poliza.setFuente(comprobante.getRfc());

        poliza.setHora("" + System.currentTimeMillis());
        poliza.setUsuario(usuario);

        boolean periodo = maestroValidation.validaPeriodo(poliza);

////            System.out.println("periodo:" + periodo);
        if (!periodo) {

            return null;
        }

        PolizasId id2 = polizasDao.save(poliza);
        return id2;
    }
    
  

    public Date pasaFecha(String fecha) {
        try {
            SimpleDateFormat formatFecha = new SimpleDateFormat("dd/MM/yyyy");
            return formatFecha.parse(fecha);
        } catch (Exception e) {

            e.printStackTrace();
            return null;
        }
    }
    
     public boolean generaConceptoDiot(PolizasId poliza, ErpDetConvertidor det,  Map datosFE, int sec) {
    
        
        DetDIOT detDIOT = new DetDIOT();
        DetDIOTId detDIOTId = new DetDIOTId();

        detDIOTId.setCompania(poliza.getCompania());
        detDIOTId.setNumero(poliza.getNumero());
        detDIOTId.setTipoPoliza(poliza.getTipoPoliza());
        detDIOTId.setFecha(poliza.getFecha());
        detDIOTId.setSec(sec);
        detDIOTId.setConcepto(det.getConceptoDiot());
        
        Map datosCta = new HashMap();
        
        datosCta.put("compania", poliza.getCompania());
        datosCta.put("CUENTA_ALIAS", det.getCuenta());
        List list = processDao.getMapResult("BuscaCuentaCorrecta", datosCta);

        if (list == null || list.size() == 0) {
           
        }

        String cuenta_real = getVal(list.get(0).toString(), 0);
         BigDecimal importe = BigDecimal.ZERO;

//        System.out.println("Tipo:" + det.gettAplicacion());
//        System.out.println("operacion:" + det.getOperaciones());
//        System.out.println("det.getIdcampo():" + det.getIdcampo());
        if (det.getOperaciones() == null || det.getOperaciones().trim().equals("")) {

            if (datosFE.get(det.getIdcampo().toString()) == null) {
                
                return false;
            }

            importe = new BigDecimal(datosFE.get(det.getIdcampo().toString()).toString());
        } else {
            double imp = operaciones.operacion(det.getOperaciones(), datosFE);
//             double imp = procesaOperacion(datosFE,det.getOperaciones() );
            importe = new BigDecimal(imp);
        }
        
         detDIOTId.setCuenta(cuenta_real);
       
        Map concep = new HashMap();
                 
           concep.put("compania", poliza.getCompania());
               
           List listConcepto = processDao.getMapResult("FindConceptosDiot", concep);
            for(int i = 0;listConcepto.size() > i;i++){
                
                 Map resultCount = (HashMap) listConcepto.get(i);
                //resultCount.get("CONCEPTO").toString();
                 
                if (resultCount.get("CONCEPTO").toString().equalsIgnoreCase(det.getConceptoDiot())){
                  detDIOTId.setConcepto(det.getConceptoDiot());
                   detDIOT.setId(detDIOTId);
                   detDIOT.setImporte(importe);
       
                }else{
                    detDIOTId.setConcepto(resultCount.get("CONCEPTO").toString());
                    detDIOT.setId(detDIOTId);

                    detDIOT.setImporte(BigDecimal.ZERO);
                    
                }
            
                    DetDIOTId result = detDIOTDao.save(detDIOT);


            }  
           
            return true;
          
  
        
        
    }


    public ErpCatConvertidorDao getErpCatConvertidorDao() {
        return erpCatConvertidorDao;
    }

    public void setErpCatConvertidorDao(ErpCatConvertidorDao erpCatConvertidorDao) {
        this.erpCatConvertidorDao = erpCatConvertidorDao;
    }

    public void setErpDetConvertidorDao(ErpDetConvertidorDao erpDetConvertidorDao) {
        this.erpDetConvertidorDao = erpDetConvertidorDao;
    }

    public void setErpFeComprobantesDao(ErpFeComprobantesDao erpFeComprobantesDao) {
        this.erpFeComprobantesDao = erpFeComprobantesDao;
    }

    public void setErpFeEmisorDao(ErpFeEmisorDao erpFeEmisorDao) {
        this.erpFeEmisorDao = erpFeEmisorDao;
    }

    public void setErpFeImpTrasladosDao(ErpFeImpTrasladosDao erpFeImpTrasladosDao) {
        this.erpFeImpTrasladosDao = erpFeImpTrasladosDao;
    }

    public void setErpFeImpRetencionesDao(ErpFeImpRetencionesDao erpFeImpRetencionesDao) {
        this.erpFeImpRetencionesDao = erpFeImpRetencionesDao;
    }

    public void setErpFeReceptorDao(ErpFeReceptorDao erpFeReceptorDao) {
        this.erpFeReceptorDao = erpFeReceptorDao;
    }

    public void setPolizasDao(PolizasDao polizasDao) {
        this.polizasDao = polizasDao;
    }

    public void setDetPolizasDao(DetPolizasDao detPolizasDao) {
        this.detPolizasDao = detPolizasDao;
    }

    public void setMaestroValidation(MaestroValidation maestroValidation) {
        this.maestroValidation = maestroValidation;
    }

    public void setErpPolizasXFacturasDao(ErpPolizasXFacturasDao erpPolizasXFacturasDao) {
        this.erpPolizasXFacturasDao = erpPolizasXFacturasDao;
    }

    public void setLogPolizasDao(LogPolizasDao logPolizasDao) {
        this.logPolizasDao = logPolizasDao;
    }

    public void setDetalleValidation(DetalleValidation detalleValidation) {
        this.detalleValidation = detalleValidation;
    }

    public void setOperaciones(Operaciones operaciones) {
        this.operaciones = operaciones;
    }

    public void setProcessDao(ProcessDao processDao) {
        this.processDao = processDao;
    }

    public void setErpParidadDao(ErpParidadDao erpParidadDao) {
        this.erpParidadDao = erpParidadDao;
    }

    public void setDetDIOTDao(DetDIOTDao detDIOTDao) {
        this.detDIOTDao = detDIOTDao;
    }

    public void setErpSatTransaccionDao(ErpSatTransaccionDao erpSatTransaccionDao) {
        this.erpSatTransaccionDao = erpSatTransaccionDao;
    }

    public void setDetPolizasXTransaccionDao(DetPolizasXTransaccionDao detPolizasXTransaccionDao) {
        this.detPolizasXTransaccionDao = detPolizasXTransaccionDao;
    }

    public int getContDet() {
        return contDet;
    }

    public void setContDet(int contDet) {
        this.contDet = contDet;
    }

    public void setErpParidadCompaniaDao(ErpParidadCompaniaDao erpParidadCompaniaDao) {
        this.erpParidadCompaniaDao = erpParidadCompaniaDao;
    }

    public void setErpPolizasXPagosDao(ErpPolizasXPagosDao erpPolizasXPagosDao) {
        this.erpPolizasXPagosDao = erpPolizasXPagosDao;
    }

    public void setErpFeTrasLocalesDao(ErpFeTrasLocalesDao erpFeTrasLocalesDao) {
        this.erpFeTrasLocalesDao = erpFeTrasLocalesDao;
    }

    public ErpFeConceptosDao getErpFeConceptosDao() {
        return erpFeConceptosDao;
    }

    public void setErpFeConceptosDao(ErpFeConceptosDao erpFeConceptosDao) {
        this.erpFeConceptosDao = erpFeConceptosDao;
    }
    

}
