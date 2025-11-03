
package com.feraz.contabilidad.sat.electronica.controll;

import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonToken;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.feraz.cxp.dto.CxpOtrasDTO;
import com.feraz.facturas.webcontrolfe.dao1.ErpFeComprobantesDao;
import com.feraz.facturas.webcontrolfe.model.ErpFeComprobantes;
import com.feraz.facturas.webcontrolfe.model.ErpFeComprobantesId;
import org.jamh.wf.process.Querys;

import com.feraz.facturas.webcontrolfe.dto.RelacionPolizas;
import com.feraz.sat.cfdi.check.ValidaSAT;
import com.feraz.sat.cfdi.model.ResponseWSValida;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.jamh.data.process.ProcessDao;

import org.jamh.wf.json.model.ResponseQuery;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.request.WebRequest;

/**
 *
 * @author Ing. JAMH
 */
@Controller
@RequestMapping(value = "/validaSAT")
//@SessionAttributes({"compania", "usuario"})
public class ValidateCFDI {
    private final Logger log = LoggerFactory.getLogger(ValidateCFDI.class);
    private ErpFeComprobantesDao erpFeComprobantesDao;
    private ProcessDao processDao;

    @RequestMapping(value = "/valida", method = RequestMethod.POST)
    public @ResponseBody
    ResponseQuery create(String data,//@RequestParam("rfcEmisor") String rfcEmisor,
            //@RequestParam("rfcReceptor") String rfcReceptor,
           // @RequestParam("total") String total,
           // @RequestParam("uuid") String uuid,
            WebRequest webRequest,
            Model model) {
//        System.out.println("data"+ data);
        ResponseQuery rq = new ResponseQuery();
        ValidaSAT vl = new ValidaSAT();
        
        ErpFeComprobantes comp = new ErpFeComprobantes();
         ErpFeComprobantesId compId = new ErpFeComprobantesId();
        
           try {
            ObjectMapper mapper = new ObjectMapper();
            JsonFactory f = new JsonFactory();
            JsonParser jp = f.createJsonParser(data);
            // System.out.println("Data:"+getData());
            // advance stream to START_ARRAY first:
            jp.nextToken();
          //  List<CaBioUsuarioXDisp> lis = new ArrayList<CaBioUsuarioXDisp>();
            int i = 1;
            //ScanUser su = new ScanUser();
//            String mensaje = "";
          //  String mensaje2 = "";
            int countFallas = 0;
            while (jp.nextToken() == JsonToken.START_OBJECT) {
                RelacionPolizas ss = mapper.readValue(jp, RelacionPolizas.class);
                
                  String importe = "";
                
                  Map compFe = new HashMap();
                 
                   compFe.put("compania", ss.compania);
                   compFe.put("numero", ss.numero);
               
                   List listImpLt = processDao.getMapResult("BuscaImporteLetra", compFe);
                   
                   if (listImpLt != null && listImpLt.size() > 0){
                       
                        Map let = (HashMap) listImpLt.get(0);
                        System.out.println("ImporteLtra:" + let.get("TOTAL_LETRA"));
                        if (let.get("TOTAL_LETRA") == null){
                            
                            importe = ss.total;
                        
                        }else{
                           importe = let.get("TOTAL_LETRA").toString();
                        }
                        
                   
                   }else{
                       
                       importe = ss.total;
                   
                   }
                   
                   
                    
                
                System.out.println("ssReceptor: "+ ss.rfcReceptor+"/");
                 System.out.println("ssEmisor: "+ ss.efcEmisor+"/");
                  System.out.println("ssUUID: "+ ss.uuid+"/");
                  System.out.println("ssTotal: "+ ss.total+"/");
                   System.out.println("ssTotal: "+ importe+"/");
                  
                    ResponseWSValida res = vl.validaCFDIWSSAT(ss.efcEmisor, ss.rfcReceptor, importe, ss.uuid);
            log.debug("res.getEstatus():"+res.getEstatus());
               
               compId.setCompania(ss.compania);
               compId.setNumero(Integer.parseInt(ss.numero));
               comp.setId(compId);
                System.out.println("res.getEstatus():"+res.getEstatus());
               
               
                 if (res.getEstatus().equals("Vigente")) {
                     comp.setEstatusV("1");
                     erpFeComprobantesDao.actualizaEstatus(comp);
  
                    } else {
                     
                     comp.setEstatusV("0");
                     erpFeComprobantesDao.actualizaEstatus(comp);
                     
                     
                     countFallas = countFallas + 1;
                     
                  //     mensaje = mensaje+" "+ss.folio+",";

                    }
                 
                 
                    if(countFallas > 0){
                        
                        rq.setData(null);
                        rq.setMsg("Existio error");
                        rq.setSuccess(false);
                        rq.setTotal(BigDecimal.ONE);
                        
                    }else{
                        
                        rq.setData(null);
                        rq.setMsg("Bien");
                        rq.setSuccess(true);
                        rq.setTotal(BigDecimal.ONE);
                    
                    
                    
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
    
    
    @RequestMapping(value = "/fechaCashOtr", method = RequestMethod.POST)
    public @ResponseBody
    ResponseQuery fechaCashOtr(String data,//@RequestParam("rfcEmisor") String rfcEmisor,
            //@RequestParam("rfcReceptor") String rfcReceptor,
           // @RequestParam("total") String total,
           // @RequestParam("uuid") String uuid,
            WebRequest webRequest,
            Model model) {
//        System.out.println("data"+ data);
        ResponseQuery rq = new ResponseQuery();
        
        
           try {
            ObjectMapper mapper = new ObjectMapper();
            JsonFactory f = new JsonFactory();
            JsonParser jp = f.createJsonParser(data);
            // System.out.println("Data:"+getData());
            // advance stream to START_ARRAY first:
            jp.nextToken();
          //  List<CaBioUsuarioXDisp> lis = new ArrayList<CaBioUsuarioXDisp>();
            int i = 1;
            //ScanUser su = new ScanUser();
//            String mensaje = "";
          //  String mensaje2 = "";
            int countFallas = 0;
            while (jp.nextToken() == JsonToken.START_OBJECT) {
                CxpOtrasDTO ss = mapper.readValue(jp, CxpOtrasDTO.class);
                
                
                 Querys que = new Querys();
            String store = que.getSQL("ACTUALIZA_FECHA_CASHXOTR");

            Map paramCash = new HashMap();
            paramCash.put("COMPANIA",ss.compania);
            paramCash.put("NUMERO",ss.id);
            paramCash.put("ID_PROVEEDOR",ss.idProveedoreOtros);
                int val = processDao.execute(store, paramCash);

                     //if (val <= 0) {

                        rq.setData(null);
                        rq.setMsg("Bien");
                        rq.setSuccess(true);
                        rq.setTotal(BigDecimal.ONE);
                     
                    // }else{
                     
                     
                    // }
                
                
              
                    
                   
                    
            }
           } catch (Exception e) {
             rq.setSuccess(false);
            rq.setMsg("Error al generar proceso");
            e.printStackTrace();
            return rq;
        }

             return rq;
    }        
            
    @RequestMapping(value = "/fechaCash", method = RequestMethod.POST)
    public @ResponseBody
    ResponseQuery fechaCash(String data,//@RequestParam("rfcEmisor") String rfcEmisor,
            //@RequestParam("rfcReceptor") String rfcReceptor,
           // @RequestParam("total") String total,
           // @RequestParam("uuid") String uuid,
            WebRequest webRequest,
            Model model) {
//        System.out.println("data"+ data);
        ResponseQuery rq = new ResponseQuery();
        ValidaSAT vl = new ValidaSAT();
        
        ErpFeComprobantes comp = new ErpFeComprobantes();
         ErpFeComprobantesId compId = new ErpFeComprobantesId();
        
           try {
            ObjectMapper mapper = new ObjectMapper();
            JsonFactory f = new JsonFactory();
            JsonParser jp = f.createJsonParser(data);
            // System.out.println("Data:"+getData());
            // advance stream to START_ARRAY first:
            jp.nextToken();
          //  List<CaBioUsuarioXDisp> lis = new ArrayList<CaBioUsuarioXDisp>();
            int i = 1;
            //ScanUser su = new ScanUser();
//            String mensaje = "";
          //  String mensaje2 = "";
            int countFallas = 0;
            while (jp.nextToken() == JsonToken.START_OBJECT) {
                RelacionPolizas ss = mapper.readValue(jp, RelacionPolizas.class);
                
                
                 Querys que = new Querys();
            String store = que.getSQL("ACTUALIZA_FECHA_CASHXNUM");

            Map paramCash = new HashMap();
            paramCash.put("COMPANIA",ss.compania);
            paramCash.put("NUMERO",ss.numero);
            paramCash.put("RFC",ss.efcEmisor);
                int val = processDao.execute(store, paramCash);

                     //if (val <= 0) {

                        rq.setData(null);
                        rq.setMsg("Bien");
                        rq.setSuccess(true);
                        rq.setTotal(BigDecimal.ONE);
                     
                    // }else{
                     
                     
                    // }
                
                
              
                    
                   
                    
            }
           } catch (Exception e) {
             rq.setSuccess(false);
            rq.setMsg("Error al generar proceso");
            e.printStackTrace();
            return rq;
        }

             return rq;
    }
    
    public void setErpFeComprobantesDao(ErpFeComprobantesDao erpFeComprobantesDao) {
        this.erpFeComprobantesDao = erpFeComprobantesDao;
    }

    public void setProcessDao(ProcessDao processDao) {
        this.processDao = processDao;
    }
    
    
    
}
