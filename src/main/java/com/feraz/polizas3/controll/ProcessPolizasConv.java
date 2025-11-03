/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.feraz.polizas3.controll;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.feraz.contabilidad.convertidor.dao.ErpCatConvertidorDao;
import com.feraz.contabilidad.convertidor.dao.ErpDetConvertidorDao;
import com.feraz.contabilidad.convertidor.model.ErpCatConvertidor;
import com.feraz.contabilidad.convertidor.model.ErpCatConvertidorId;
import com.feraz.contabilidad.convertidor.model.ErpDetConvertidor;
import com.feraz.contabilidad.convertidor.model.ErpDetConvertidorId;
import com.feraz.contabilidad.convertidor.util.ResponseQuery3;
import com.feraz.contabilidad.sat.electronica.process.GeneraContaElectronica;
import com.feraz.polizas3.dao.DetPolizasDao;
import com.feraz.polizas3.dao.ErpPolizasXFacturasDao;
import com.feraz.polizas3.dao.LogPolizasDao;
import com.feraz.polizas3.dao.PolizasDao;
import com.feraz.polizas3.dto.ConvertidorPolizasDTO;
import com.feraz.polizas3.dto.DetControlPolizaDTO;
import com.feraz.polizas3.model.DetPolizas;
import com.feraz.polizas3.model.DetPolizasId;
import com.feraz.polizas3.model.ErpPolizasXFacturas;
import com.feraz.polizas3.model.ErpPolizasXFacturasId;
import com.feraz.polizas3.model.LogPolizas;
import com.feraz.polizas3.model.LogPolizasId;
import com.feraz.polizas3.model.Polizas;
import com.feraz.polizas3.model.PolizasId;
import com.feraz.polizas3.validation.DetalleValidation;
import com.feraz.polizas3.validation.MaestroValidation;
import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import org.jamh.data.process.ProcessDao;
import org.jamh.wf.process.Querys;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.context.request.WebRequest;

/**
 *
 * @author Ing. JAMH
 */
@Controller
@RequestMapping("/facturas")
@SessionAttributes({"compania", "usuario"})
public class ProcessPolizasConv {

    private DetPolizasDao detPolizasDao;
    private ProcessDao processDao;
    private LogPolizasDao logPolizasDao;
    private DetalleValidation detalleValidation;
    private ErpCatConvertidorDao erpCatConvertidorDao;
    private ErpDetConvertidorDao erpDetConvertidorDao;
    private ErpPolizasXFacturasDao erpPolizasXFacturasDao;
    private GeneraContaElectronica generaContaElectronica;

    private PolizasDao polizasDao;

    private MaestroValidation maestroValidation;

    @RequestMapping(value = "/electronica/poliza", method = RequestMethod.POST)
    public @ResponseBody
    ResponseQuery3 insertAction(String data, String tipo, String referencia, String fecha,String emisor,String receptor, WebRequest webRequest, Model model,String numeroFE,String folio,String uuid) {

        ResponseQuery3 rq = new ResponseQuery3();

//        System.out.println("data" + data);
//        System.out.println(tipo);
//        System.out.println(referencia);
//        System.out.println(fecha);
        SimpleDateFormat formatFecha = new SimpleDateFormat("dd/MM/yyyy");

        if (model.asMap().get("usuario") == null) {

            rq.setSuccess(false);
            rq.setData(null);
            rq.setMsg("session invalid");
            return rq;
        }
        String compania = model.asMap().get("compania").toString();
        String usuario = model.asMap().get("usuario").toString();

        try {
            PolizasId id = new PolizasId();
            id.setCompania(compania);
            id.setFecha(formatFecha.parse(fecha));
            id.setTipoPoliza(tipo);
            Polizas poliza = new Polizas();
            poliza.setId(id);
            poliza.setDivisa("PES");
            poliza.setNombre(referencia);
            poliza.setParidad(BigDecimal.ONE);
            poliza.setAbonos(BigDecimal.ZERO);
            poliza.setAbonosBase(BigDecimal.ZERO);
            poliza.setCargos(BigDecimal.ZERO);
            poliza.setCargosBase(BigDecimal.ZERO);
            poliza.setFechaCap(new Date());
            poliza.setEstatus(BigDecimal.ZERO);
            poliza.setReferencia(uuid);
            poliza.setFuente(emisor);
            
            poliza.setHora("" + System.currentTimeMillis());
            poliza.setUsuario(usuario);

            boolean periodo = maestroValidation.validaPeriodo(poliza);

//            System.out.println("periodo:" + periodo);
            if (!periodo) {
                rq.setSuccess(false);
                rq.setData(null);
                rq.setMsg(maestroValidation.getMsgError());
                return rq;
            }

            PolizasId id2 = polizasDao.save(poliza);
            rq.setCompania(null);
            rq.setNumero(id2.getNumero());
            rq.setTipoPoliza(id2.getTipoPoliza());
            rq.setFecha(fecha);
//            System.out.println("Poliza Numero:" + id2.getNumero());

            ObjectMapper mapper = new ObjectMapper();
            mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
            DateFormat df = new SimpleDateFormat("dd/MM/yyyy");

            mapper.setDateFormat(df);
            List<ConvertidorPolizasDTO> lista = mapper.readValue(data,
                    mapper.getTypeFactory().constructCollectionType(List.class,
                            ConvertidorPolizasDTO.class));
            List<DetControlPolizaDTO> listaConv = new ArrayList<DetControlPolizaDTO>();
            Iterator<ConvertidorPolizasDTO> it2 = lista.iterator();

            while (it2.hasNext()) {
                ConvertidorPolizasDTO ss2 = it2.next();

                if (ss2.tipoImporte.equals("C")) {
                    ss2.cargos = ss2.importe;
                    ss2.abonos = "0";
                } else if (ss2.tipoImporte.equals("A")) {
                    ss2.cargos = "0";
                    ss2.abonos = ss2.importe;
                }

                @SuppressWarnings("unchecked")
                DetControlPolizaDTO detdto = mapper.convertValue(ss2, DetControlPolizaDTO.class);
//                           System.out.println("ss:"+ss2.cuenta);
//                       System.out.println("detdto:"+detdto.cuentaAlias);
                detdto.compania = id2.getCompania();
                detdto.numero = id2.getNumero();
                detdto.fecha = formatFecha.format(id2.getFecha());
                detdto.tipoPoliza = id2.getTipoPoliza();

                listaConv.add(detdto);
            }

            if (lista.isEmpty()) {
                rq.setSuccess(false);
                rq.setData(null);
                rq.setMsg("Error no existen detalles datos que guardar");
                return rq;
            }

            List<DetControlPolizaDTO> lisErr2 = new ArrayList<DetControlPolizaDTO>();
            List<DetControlPolizaDTO> listaAciertos = new ArrayList<DetControlPolizaDTO>();
            int val = 1;
            Iterator<DetControlPolizaDTO> it = listaConv.iterator();
            Querys que = new Querys();
            String store = que.getSQL("CONTA_INSERTA_DETPOLIZAS_FOVI3");
            while (it.hasNext()) {
//                System.out.println("-------------------------------------------------------------------");
                DetControlPolizaDTO ss = it.next();

                ss.hora = "" + System.currentTimeMillis();
                ss.sec = "" + val;

                ss.cargosBase = ss.cargos;
                ss.abonosBase = ss.abonos;
                ss.cCostos = ss.cc;
                if (ss.cCostos == null || ss.cCostos.trim().equals("")) {
                    ss.cCostos = "00";
                    ss.cc = "00";
                }

                @SuppressWarnings("unchecked")
                Map<String, Object> parametros = mapper.convertValue(ss, Map.class);

                parametros.put("compania", compania);
                parametros.put("usuario", usuario);
                parametros.put("RFC", emisor);
                parametros.put("DESCRIPCION", referencia);
                parametros.put("REFERENCIA2", uuid);
                
               // System.out.println(parametros.toString());

                List list = processDao.getMapResult("BuscaCuentaCorrecta", parametros);

                if (list == null || list.size() == 0) {
                    rq.setSuccess(false);
                    rq.setData(null);
                    rq.setMsg("Error cuenta no encontrada");
                    return rq;
                }

                String cuenta_real = getVal(list.get(0).toString(), 0);
                System.out.println("cuenta_real:" + cuenta_real);
                ss.cuenta = cuenta_real;
                parametros.put("CUENTA", cuenta_real);
                // PolizasId polizasId = mapper.convertValue(ss, PolizasId.class);
                DetPolizasId idDet = mapper.convertValue(ss, DetPolizasId.class);
                DetPolizas disp = mapper.convertValue(ss, DetPolizas.class);
                disp.setFechaCap(new Date());

                if (disp.getCargos() == null) {

                    disp.setCargos(BigDecimal.ZERO);
                    disp.setCargosBase(BigDecimal.ZERO);

                }

                if (disp.getAbonos() == null) {

                    disp.setAbonos(BigDecimal.ZERO);
                    disp.setAbonosBase(BigDecimal.ZERO);

                }

                disp.setId(idDet);

                LogPolizasId idLog = new LogPolizasId();
                LogPolizas log = new LogPolizas();
                
                parametros.put("estatus", "2");

                boolean estatus = detalleValidation.validaEstatus(parametros);

                if (!estatus) {
                    rq.setSuccess(false);
                    rq.setData(null);
                    rq.setMsg(detalleValidation.getMsgError());
                    return rq;
                }

                boolean cuenta = detalleValidation.validaCuenta(parametros);

                if (!cuenta) {
                    ss.msgErr = "Error en Cuenta:" + ss.cuenta;
                    lisErr2.add(ss);
                    continue;
                }
               // System.out.println("ss.cc:" + ss.cc);

                if (ss.cc == null) {

                } else if (!ss.cc.trim().equals("")) {
                    boolean cc = detalleValidation.validaCC(parametros);

                    if (!cc) {
                        ss.msgErr = "Error en el Centro de costo:" + ss.cc;
                        lisErr2.add(ss);
                        continue;
                    }
                }

                parametros.put("OPERACION", "I");
                parametros.put("OPE_SUMA", "0");

                val = processDao.execute(store, parametros);

                if (val <= 0) {
                    ss.msgErr = "Error en la cuenta:" + ss.cuenta;
                    lisErr2.add(ss);
                    continue;
                }

                int sec = detPolizasDao.getMaxId(idDet);
                disp.getId().setSec(new BigDecimal(sec));
                disp.setRfc(emisor);
                disp.setDescripcion(referencia);
                disp.setReferencia2(uuid);
                DetPolizasId result = null;
                result = detPolizasDao.save(disp);

                if (result == null) {
                    ss.msgErr = "Error al guardar DTO" + result.toString();
                    lisErr2.add(ss);

                    continue;
                }
                ss.sec = "" + result.getSec();
                ss.id2 = "" + result.getSec();
                listaAciertos.add(ss);

                //Suma detalles actualiza
                parametros.put("OPERACION", "S");
                parametros.put("OPE_SUMA", "1");
                val = processDao.execute(store, parametros);

                //Genera el Log del Detalle de polizas
                idLog.setUsuario(usuario);
                idLog.setNumPoliza(ss.numero);
                idLog.setTipoPoliza(ss.tipoPoliza);
                idLog.setFechaPoliza(disp.getId().getFecha());
                log.setId(idLog);
                log.setFecha(new Date());
                log.setAccion("INSERTA_DETPOLIZA, SEC: " + sec);

                LogPolizasId resultLog = logPolizasDao.save(log);
                val++;
            }
            
            
                ErpCatConvertidorId conID = new ErpCatConvertidorId();
                ErpCatConvertidor cat = new ErpCatConvertidor();
                conID.setCompania(compania);
                conID.setIdconcgasto("A");
                conID.setOrigen("X");   
                conID.setNoCaso(new BigDecimal(erpCatConvertidorDao.getMaxIdConvertidor(conID)));
                cat.setId(conID);
                cat.setNombre(emisor+"-"+receptor);
                cat.settPoliza(tipo);
                cat.setActivo("1");
                cat.setRfcEmisor(emisor);
                cat.setRfcReceptor(receptor);
                procesaConvertidor(emisor,receptor);
                ErpCatConvertidorId rId = erpCatConvertidorDao.save(cat);
                
                int i = 1;
               // ErpDetConvertidor erpDetCon = new ErpDetConvertidor();
                ErpDetConvertidorId erpDetConId = new ErpDetConvertidorId();
                erpDetConId.setCompania(conID.getCompania());
                erpDetConId.setIdconcgasto(conID.getIdconcgasto());
                erpDetConId.setNoCaso(conID.getNoCaso());
                erpDetConId.setOrigen(conID.getOrigen());
                erpDetConId.setSec(i);
                
                   Iterator<ConvertidorPolizasDTO> it3 = lista.iterator();

            while (it3.hasNext()) {
                ConvertidorPolizasDTO ss2 = it3.next();

            ErpDetConvertidor  detC= new ErpDetConvertidor(); 
            detC.setCuenta(ss2.cuentaAlias);
            detC.setIdcampo(ss2.nomImporte);
            detC.settAplicacion(ss2.tipoImporte);
            erpDetConId.setSec(i);
            detC.setId(erpDetConId);

              i++;
             ErpDetConvertidorId saveDet= erpDetConvertidorDao.save(detC);
            }
            
            
                boolean resultContaElect = generaContaElectronica.generaRelacion(compania, Integer.parseInt(numeroFE), poliza.getId().getNumero(), fecha, poliza.getId().getTipoPoliza());
                        System.out.println(resultContaElect);
                        if(resultContaElect = true){

                             rq.setSuccess(true);
                             rq.setMsg("Factura cargada y relacionada correctamente");


                        }else{


                             rq.setSuccess(false);
                             rq.setMsg("Factura cargada. Existio error al relacionar la poliza");

                        }

            

//            System.out.println("realcion:"+relacion.getNumeroPol());
            
                 rq.setSuccess(true);
              rq.setMsg("Poliza generada:"+id2.getCompania()+"-"+id2.getNumero()+"-"+id2.getTipoPoliza()+"-"+df.format(id2.getFecha()) );
        } catch (Exception e) {
            e.printStackTrace();
        }
   

        return rq;

    }
    
    private int procesaConvertidor(String rfcEmisor,String rfcReceptor){
        ErpCatConvertidor cat = erpCatConvertidorDao.buscaRfc(rfcEmisor, rfcReceptor);
        if(cat==null) return 1;
        boolean borraCat = erpCatConvertidorDao.delete(cat);
       boolean borraDet= erpDetConvertidorDao.deleteAll(cat.getId());
       System.out.println("borraDet:"+borraDet);
       if(borraDet && borraCat) return 1;
        
        return 0;
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

    public void setDetPolizasDao(DetPolizasDao detPolizasDao) {
        this.detPolizasDao = detPolizasDao;
    }

    public void setProcessDao(ProcessDao processDao) {
        this.processDao = processDao;
    }

    public void setLogPolizasDao(LogPolizasDao logPolizasDao) {
        this.logPolizasDao = logPolizasDao;
    }

    public void setDetalleValidation(DetalleValidation detalleValidation) {
        this.detalleValidation = detalleValidation;
    }

    public void setPolizasDao(PolizasDao polizasDao) {
        this.polizasDao = polizasDao;
    }

    public void setMaestroValidation(MaestroValidation maestroValidation) {
        this.maestroValidation = maestroValidation;
    }

    public void setErpCatConvertidorDao(ErpCatConvertidorDao erpCatConvertidorDao) {
        this.erpCatConvertidorDao = erpCatConvertidorDao;
    }

    public void setErpDetConvertidorDao(ErpDetConvertidorDao erpDetConvertidorDao) {
        this.erpDetConvertidorDao = erpDetConvertidorDao;
    }

    public void setErpPolizasXFacturasDao(ErpPolizasXFacturasDao erpPolizasXFacturasDao) {
        this.erpPolizasXFacturasDao = erpPolizasXFacturasDao;
    }

    public void setGeneraContaElectronica(GeneraContaElectronica generaContaElectronica) {
        this.generaContaElectronica = generaContaElectronica;
    }
    
    

}
