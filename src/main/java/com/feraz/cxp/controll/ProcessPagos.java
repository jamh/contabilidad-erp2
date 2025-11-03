/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.feraz.cxp.controll;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.feraz.contabilidad.sat.transacciones.dao.ErpSatTransaccionDao;
import com.feraz.contabilidad.sat.transacciones.model.ErpSatTransaccion;
import com.feraz.contabilidad.sat.transacciones.model.ErpSatTransaccionId;
import com.feraz.cxp.dao.ErpCpConcPagoDao;
import com.feraz.cxp.dao.ErpCpOtrasDao;
import com.feraz.cxp.dao.ErpCxpFacturasXOtrasDao;
import com.feraz.cxp.dao.ErpCxpFoliosDao;
import com.feraz.cxp.dao.ErpPolizasVSFacturasAutErrDao;
import com.feraz.cxp.dao.ErpProvPagoUnicoDao;
import com.feraz.cxp.dao.ErpProvisionDetDao;
import com.feraz.cxp.dao.ErpSeguiDocumentosDao;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import org.jamh.data.process.ProcessDao;
import org.jamh.wf.json.model.ResponseQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.context.request.WebRequest;
import com.feraz.cxp.dto.CxpDTO;
import com.feraz.cxp.dto.CxpOtrasDTO;
import com.feraz.cxp.dto.EnvioTesDTO;
import com.feraz.cxp.dto.EnvioTesDTONom;
import com.feraz.cxp.dto.ErpConcGastoDTO;
import com.feraz.cxp.dto.FoliosNomDispDTO;
import com.feraz.cxp.dto.PagosAllDTO;
import com.feraz.cxp.dto.PagosDTO;
import com.feraz.cxp.dto.ProvPagoUnicoDTO;
import com.feraz.cxp.dto.RelacionFactXOtrasDTO;
import com.feraz.cxp.dto.RembolsoDTO;
import com.feraz.cxp.dto.TesoreriaDTO;
import com.feraz.cxp.dto.ViaticosDTO;
import com.feraz.cxp.model.ErpCpConcPago;
import com.feraz.cxp.model.ErpCpConcPagoId;
import com.feraz.cxp.model.ErpCpOtras;
import com.feraz.cxp.model.ErpCpOtrasId;
import com.feraz.cxp.model.ErpCxpFacturasXOtras;
import com.feraz.cxp.model.ErpCxpFacturasXOtrasId;
import com.feraz.cxp.model.ErpCxpFolios;
import com.feraz.cxp.model.ErpCxpFoliosId;
import com.feraz.cxp.model.ErpPolizasVSFacturasAutErr;
import com.feraz.cxp.model.ErpPolizasVSFacturasAutErrId;
import com.feraz.cxp.model.ErpProvPagoUnico;
import com.feraz.cxp.model.ErpProvPagoUnicoId;
import com.feraz.cxp.model.ErpProvisionDet;
import com.feraz.cxp.model.ErpProvisionDetId;
import com.feraz.cxp.model.ErpSeguiDocumentos;
import com.feraz.cxp.model.ErpSeguiDocumentosId;
import com.feraz.facturas.webcontrolfe.dao1.ErpFeComprobantesDao;
import com.feraz.facturas.webcontrolfe.model.ErpFeComprobantes;
import com.feraz.facturas.webcontrolfe.model.ErpFeComprobantesId;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.Closeable;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.math.BigDecimal;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLDecoder;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.Deque;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.logging.Level;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;
import org.jamh.wf.process.Querys;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

/**
 *
 * @author Feraz3
 */
@Controller
@RequestMapping("/Pagos")
@SessionAttributes({"compania", "usuario"})
public class ProcessPagos {
    
    private ErpCxpFoliosDao erpCxpFoliosDao;
    private ErpFeComprobantesDao erpFeComprobantesDao;
    private ErpSeguiDocumentosDao erpSeguiDocumentosDao;
    private ErpCpOtrasDao erpCpOtrasDao;
    private ProcessDao processDao;
    private ErpPolizasVSFacturasAutErrDao erpPolizasVSFacturasAutErrDao;
    private ErpCxpFacturasXOtrasDao erpCxpFacturasXOtrasDao;
    
    private ErpCpConcPagoDao erpCpConcPagoDao;
    
    private ErpSatTransaccionDao erpSatTransaccionDao;
    
    private ErpProvisionDetDao erpProvisionDetDao;
    
    
    private ErpProvPagoUnicoDao erpProvPagoUnicoDao;
    
    
    
    
    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public @ResponseBody
    ResponseQuery savePagos( String data, WebRequest webRequest, Model model) throws IOException {

        ResponseQuery rq = new ResponseQuery();
        //System.out.println("data Pagos creacion:"+data);
        
         int index = data.indexOf("[");
        if (index == -1) {
            data = "[" + data + "]";
        }


            String compania = model.asMap().get("compania").toString();
            String usuario = model.asMap().get("usuario").toString();
            ObjectMapper mapper = new ObjectMapper();
          
        try{
            List<TesoreriaDTO> lista = mapper.readValue(data,
                    mapper.getTypeFactory().constructCollectionType(List.class,
                            TesoreriaDTO.class));

            if (lista.isEmpty()) {
                rq.setSuccess(false);
                rq.setData(null);
                rq.setMsg("Error no existen datos que guardar");
                return rq;
            }            Iterator<TesoreriaDTO> it = lista.iterator();
           ErpCxpFolios folios = new ErpCxpFolios();
           ErpCxpFoliosId foliosId = new ErpCxpFoliosId();
           
           ErpFeComprobantes comprobantes = new ErpFeComprobantes();
           ErpFeComprobantesId comprobantesId = new ErpFeComprobantesId();
           
           ErpCpOtras otras = new ErpCpOtras();
           ErpCpOtrasId otrasId = new ErpCpOtrasId();
           
           foliosId.setCompania(compania);
           
           int geFolio = erpCxpFoliosDao.getMaxCxpFolios(foliosId);
           
           int banderaGe = 0;
           
        //   System.out.println("GENRADOR DE FOLIO:"+geFolio);
           
           int folioPagos = geFolio;
           
           Querys que = new Querys();
            String store = que.getSQL("CONTA_ACTUALIZA_VIATICOS");
          
            while (it.hasNext()) {
//                 System.out.println("-------------------------------------------------------------------");
                 TesoreriaDTO ss = it.next();
               //  if (ss.estatusCxp == null){
                 int id = erpCxpFoliosDao.getMaxIdCxpFolios(foliosId);
                 
                // folioPagos = id;
                 
                 foliosId.setId(id);
                 folios.setId(foliosId);
                 folios.setFolio(geFolio);
                 folios.setOperacion("FG");
                 folios.setUsuario(usuario);
                 folios.setOrigen(ss.origen);
                 folios.setNumeroFe(Integer.parseInt(ss.numero));
                
                 
                 ErpCxpFoliosId result = erpCxpFoliosDao.save(folios);
                 
                 if(ss.origen.equalsIgnoreCase("F")){

                    comprobantesId.setCompania(compania);
                    comprobantesId.setNumero(Integer.parseInt(ss.numero));
                    comprobantes.setId(comprobantesId);
                    comprobantes.setEstatusCxp("FG");
                    comprobantes.setTipoPago(ss.tipoPagoTes);
                    comprobantes.setTotalAPagar(new BigDecimal(ss.totalAPagar));
                    comprobantes.setFolioPagos(geFolio);

   //                 
                    BigDecimal pagoCie = new BigDecimal(0);
                    if(ss.pagoCie == "" || ss.pagoCie == null){

                       
                    }else{

                        pagoCie = new BigDecimal(ss.pagoCie);
                    }
                    comprobantes.setReferenciaCie(ss.referenciaCie);
                    comprobantes.setConceptoCie(ss.conceptoCie);


                    boolean result2 = false;

                    if (ss.cie.equalsIgnoreCase("1")){


                      result2 = erpFeComprobantesDao.actualizaEstatusFolioPagosCie(comprobantes, ss.monedaPago, pagoCie);

                    }else{


                        result2 = erpFeComprobantesDao.actualizaEstatusFolioPagos(comprobantes, ss.monedaPago);

                    }

                 }
                 if(ss.origen.equalsIgnoreCase("O")){

                      otrasId.setCompania(ss.compania);
                      otrasId.setId(Integer.parseInt(ss.numero));
                      otras.setId(otrasId);
                      otras.setEstatusCxp("FG");
                      otras.setFolioPagos(geFolio);
                      otras.setPagoCie(ss.pagoCie);
                      otras.setRefenciaCie(ss.referenciaCie);
                      otras.setConceptoCie(ss.conceptoCie);
                      otras.setTipoPago(ss.tipoPagoTes);
                      otras.setTotalAPagar(new BigDecimal(ss.totalAPagar));
                    
                 
                      
                      erpCpOtrasDao.updateErpCpOtrasEstatusPagos(otras);

                 }
                 
                 if(ss.origen.equalsIgnoreCase("V")){

                      Map paramViatico = new HashMap();
                        paramViatico.put("COMPANIA",compania);
                        paramViatico.put("COMISION",ss.numero);
                        paramViatico.put("TIPO","FG");
                        paramViatico.put("FOLIO_PAGO",geFolio);

                    int val = processDao.execute(store, paramViatico);

                         if (val <= 0) {

                    }

                 }
                 
                 
                 banderaGe = 1;
//                 
//             

            }
            

            
      //     if (result2 == true){ 
             rq.setSuccess(true);
             rq.setData(null);
             rq.setMsg("Facturas enviadas con folio: "+geFolio);
      //     }
            
        }catch(Exception e){
            rq.setSuccess(false);
            rq.setData(null);
            rq.setMsg("Error al guardar");
           return rq;
        }
        
        return rq;
    }
    
     @RequestMapping(value = "/saveFolioPagoUnico", method = RequestMethod.POST)
    public @ResponseBody
    ResponseQuery saveFolioPagoUnico( String data, WebRequest webRequest, Model model) throws IOException {

        ResponseQuery rq = new ResponseQuery();
        //System.out.println("data Pagos creacion:"+data);
        
         int index = data.indexOf("[");
        if (index == -1) {
            data = "[" + data + "]";
        }


            String compania = model.asMap().get("compania").toString();
            String usuario = model.asMap().get("usuario").toString();
            ObjectMapper mapper = new ObjectMapper();
          
        try{
            List<ProvPagoUnicoDTO> lista = mapper.readValue(data,
                    mapper.getTypeFactory().constructCollectionType(List.class,
                            ProvPagoUnicoDTO.class));

            if (lista.isEmpty()) {
                rq.setSuccess(false);
                rq.setData(null);
                rq.setMsg("Error no existen datos que guardar");
                return rq;
            }            
            
            Iterator<ProvPagoUnicoDTO> it = lista.iterator();
           ErpCxpFolios folios = new ErpCxpFolios();
           ErpCxpFoliosId foliosId = new ErpCxpFoliosId();
           
           ErpProvPagoUnico erpProvPagoUnico = new ErpProvPagoUnico();
           ErpProvPagoUnicoId erpProvPagoUnicoId = new ErpProvPagoUnicoId();
         
           foliosId.setCompania(compania);
           
           int geFolio = erpCxpFoliosDao.getMaxCxpFolios(foliosId);
           
           int banderaGe = 0;
           
          
           
        //   System.out.println("GENRADOR DE FOLIO:"+geFolio);
           
           int folioPagos = geFolio;
          
            while (it.hasNext()) {
//                 System.out.println("-------------------------------------------------------------------");
                 ProvPagoUnicoDTO ss = it.next();
               //  if (ss.estatusCxp == null){
                 int id = erpCxpFoliosDao.getMaxIdCxpFolios(foliosId);
                 
                // folioPagos = id;
                 
                 foliosId.setId(id);
                 folios.setId(foliosId);
                 folios.setFolio(geFolio);
                 folios.setOperacion("FG");
                 folios.setUsuario(usuario);
                 folios.setOrigen("U");
                 folios.setNumeroFe(Integer.parseInt(ss.idMov));
                
                 
                 ErpCxpFoliosId result = erpCxpFoliosDao.save(folios);
                 
                 
                 erpProvPagoUnicoId.setCompania(compania);
                 erpProvPagoUnicoId.setIdMov(Integer.parseInt(ss.idMov));
                 erpProvPagoUnico.setId(erpProvPagoUnicoId);
                 erpProvPagoUnico.setEstatusCxp("FG");
                 erpProvPagoUnico.setFolioPagos(geFolio);
                 
                 erpProvPagoUnicoDao.updateErpProvPagoUnicoEstatusPagos(erpProvPagoUnico);
                 
                 
    
                 
                
            
//             

            }
            
            

            
      //     if (result2 == true){ 
             rq.setSuccess(true);
             rq.setData(null);
             rq.setMsg("Gastos enviados con folio: "+geFolio);
      //     }
            
        }catch(Exception e){
            rq.setSuccess(false);
            rq.setData(null);
            rq.setMsg("Error al guardar");
           return rq;
        }
        
        return rq;
    }
    
     @RequestMapping(value = "/saveViaticos", method = RequestMethod.POST)
    public @ResponseBody
    ResponseQuery saveViaticos( String data, WebRequest webRequest, Model model) throws IOException {

        ResponseQuery rq = new ResponseQuery();
        //System.out.println("data Pagos creacion:"+data);
        
         int index = data.indexOf("[");
        if (index == -1) {
            data = "[" + data + "]";
        }


            String compania = model.asMap().get("compania").toString();
            String usuario = model.asMap().get("usuario").toString();
            ObjectMapper mapper = new ObjectMapper();
          
        try{
            List<ViaticosDTO> lista = mapper.readValue(data,
                    mapper.getTypeFactory().constructCollectionType(List.class,
                            ViaticosDTO.class));

            if (lista.isEmpty()) {
                rq.setSuccess(false);
                rq.setData(null);
                rq.setMsg("Error no existen datos que guardar");
                return rq;
            }            Iterator<ViaticosDTO> it = lista.iterator();
           ErpCxpFolios folios = new ErpCxpFolios();
           ErpCxpFoliosId foliosId = new ErpCxpFoliosId();
           
         
           foliosId.setCompania(compania);
           
           int geFolio = erpCxpFoliosDao.getMaxCxpFolios(foliosId);
           
           int banderaGe = 0;
           
           Querys que = new Querys();
            String store = que.getSQL("CONTA_ACTUALIZA_VIATICOS");
           
        //   System.out.println("GENRADOR DE FOLIO:"+geFolio);
           
           int folioPagos = geFolio;
          
            while (it.hasNext()) {
//                 System.out.println("-------------------------------------------------------------------");
                 ViaticosDTO ss = it.next();
               //  if (ss.estatusCxp == null){
                 int id = erpCxpFoliosDao.getMaxIdCxpFolios(foliosId);
                 
                // folioPagos = id;
                 
                 foliosId.setId(id);
                 folios.setId(foliosId);
                 folios.setFolio(geFolio);
                 folios.setOperacion("FG");
                 folios.setUsuario(usuario);
                 folios.setOrigen("V");
                 folios.setNumeroFe(Integer.parseInt(ss.numero));
                
                 
                 ErpCxpFoliosId result = erpCxpFoliosDao.save(folios);
                 
    
                 
                 banderaGe = 1;
                   
                  Map paramViatico = new HashMap();
                    paramViatico.put("COMPANIA",compania);
                    paramViatico.put("COMISION",ss.numero);
                    paramViatico.put("TIPO","FG");
                    paramViatico.put("FOLIO_PAGO",geFolio);
            
                int val = processDao.execute(store, paramViatico);

                     if (val <= 0) {

                }
            
//             

            }
            
            

            
      //     if (result2 == true){ 
             rq.setSuccess(true);
             rq.setData(null);
             rq.setMsg("Facturas enviadas con folio: "+geFolio);
      //     }
            
        }catch(Exception e){
            rq.setSuccess(false);
            rq.setData(null);
            rq.setMsg("Error al guardar");
           return rq;
        }
        
        return rq;
    }
    
    
           @RequestMapping(value = "/updatePagoUnico", method = RequestMethod.POST)
    public @ResponseBody
    ResponseQuery updatePagoUnico( String data,@RequestParam("folio") String folio, WebRequest webRequest, Model model) throws IOException {
       ResponseQuery rq = new ResponseQuery();
        //System.out.println("data Pagos creacion:"+data);
        
         int index = data.indexOf("[");
        if (index == -1) {
            data = "[" + data + "]";
        }


            String compania = model.asMap().get("compania").toString();
            String usuario = model.asMap().get("usuario").toString();
            ObjectMapper mapper = new ObjectMapper();
          
        try{
            List<ProvPagoUnicoDTO> lista = mapper.readValue(data,
                    mapper.getTypeFactory().constructCollectionType(List.class,
                            ProvPagoUnicoDTO.class));

            if (lista.isEmpty()) {
                rq.setSuccess(false);
                rq.setData(null);
                rq.setMsg("Error no existen datos que guardar");
                return rq;
            }            Iterator<ProvPagoUnicoDTO> it = lista.iterator();
           ErpCxpFolios folios = new ErpCxpFolios();
           ErpCxpFoliosId foliosId = new ErpCxpFoliosId();
           
           ErpProvPagoUnico erpProvPagoUnico = new ErpProvPagoUnico();
           ErpProvPagoUnicoId erpProvPagoUnicoId = new ErpProvPagoUnicoId();
         
           foliosId.setCompania(compania);
           
           int geFolio = Integer.parseInt(folio);
           
           int banderaGe = 0;
           
           Querys que = new Querys();
           
        //   System.out.println("GENRADOR DE FOLIO:"+geFolio);
           
           int folioPagos = geFolio;
          
            while (it.hasNext()) {
//                 System.out.println("-------------------------------------------------------------------");
                 ProvPagoUnicoDTO ss = it.next();
                 
                 int id = erpCxpFoliosDao.getMaxIdCxpFolios(foliosId);
               //  if (ss.estatusCxp == null){
                 foliosId.setId(id);
                 folios.setId(foliosId);
                 folios.setFolio(geFolio);
                 folios.setOperacion("FG");
                 folios.setUsuario(usuario);
                 folios.setOrigen("U");
                 folios.setNumeroFe(Integer.parseInt(ss.idMov));
                
                 
                 ErpCxpFoliosId result = erpCxpFoliosDao.save(folios);
                 
                 
                 erpProvPagoUnicoId.setCompania(compania);
                 erpProvPagoUnicoId.setIdMov(Integer.parseInt(ss.idMov));
                 erpProvPagoUnico.setId(erpProvPagoUnicoId);
                 erpProvPagoUnico.setEstatusCxp("FG");
                 erpProvPagoUnico.setFolioPagos(geFolio);
                 
                 erpProvPagoUnicoDao.updateErpProvPagoUnicoEstatusPagos(erpProvPagoUnico);
                 
                 
    
                 
                
            

            }
            
            

            
      //     if (result2 == true){ 
             rq.setSuccess(true);
             rq.setData(null);
             rq.setMsg("Facturas enviadas con folio: "+geFolio);
      //     }
            
        }catch(Exception e){
            rq.setSuccess(false);
            rq.setData(null);
            rq.setMsg("Error al guardar");
           return rq;
        }
        
        return rq;
    }
    
       @RequestMapping(value = "/updateViaticos", method = RequestMethod.POST)
    public @ResponseBody
    ResponseQuery updateViaticos( String data,@RequestParam("folio") String folio, WebRequest webRequest, Model model) throws IOException {
       ResponseQuery rq = new ResponseQuery();
        //System.out.println("data Pagos creacion:"+data);
        
         int index = data.indexOf("[");
        if (index == -1) {
            data = "[" + data + "]";
        }


            String compania = model.asMap().get("compania").toString();
            String usuario = model.asMap().get("usuario").toString();
            ObjectMapper mapper = new ObjectMapper();
          
        try{
            List<ViaticosDTO> lista = mapper.readValue(data,
                    mapper.getTypeFactory().constructCollectionType(List.class,
                            ViaticosDTO.class));

            if (lista.isEmpty()) {
                rq.setSuccess(false);
                rq.setData(null);
                rq.setMsg("Error no existen datos que guardar");
                return rq;
            }            Iterator<ViaticosDTO> it = lista.iterator();
           ErpCxpFolios folios = new ErpCxpFolios();
           ErpCxpFoliosId foliosId = new ErpCxpFoliosId();
           
         
           foliosId.setCompania(compania);
           
           int geFolio = Integer.parseInt(folio);
           
           int banderaGe = 0;
           
           Querys que = new Querys();
            String store = que.getSQL("CONTA_ACTUALIZA_VIATICOS");
           
        //   System.out.println("GENRADOR DE FOLIO:"+geFolio);
           
           int folioPagos = geFolio;
          
            while (it.hasNext()) {
//                 System.out.println("-------------------------------------------------------------------");
                 ViaticosDTO ss = it.next();
               //  if (ss.estatusCxp == null){
                 int id = erpCxpFoliosDao.getMaxIdCxpFolios(foliosId);
                 
                // folioPagos = id;
                 
                 foliosId.setId(id);
                 folios.setId(foliosId);
                 folios.setFolio(geFolio);
                 folios.setOperacion("FG");
                 folios.setUsuario(usuario);
                 folios.setOrigen("V");
                 folios.setNumeroFe(Integer.parseInt(ss.numero));
                
                 
                 ErpCxpFoliosId result = erpCxpFoliosDao.save(folios);
                 
    
                 
                 banderaGe = 1;
                 
                   
                  Map paramViatico = new HashMap();
                    paramViatico.put("COMPANIA",compania);
                    paramViatico.put("COMISION",ss.numero);
                    paramViatico.put("TIPO","FG");
                    paramViatico.put("FOLIO_PAGO",geFolio);
            
                int val = processDao.execute(store, paramViatico);

                     if (val <= 0) {

                }
            
//             

            }
            
            

            
      //     if (result2 == true){ 
             rq.setSuccess(true);
             rq.setData(null);
             rq.setMsg("Facturas enviadas con folio: "+geFolio);
      //     }
            
        }catch(Exception e){
            rq.setSuccess(false);
            rq.setData(null);
            rq.setMsg("Error al guardar");
           return rq;
        }
        
        return rq;
    }
    
      @RequestMapping(value = "/pagaViaticCaja", method = RequestMethod.POST)
    public @ResponseBody
    ResponseQuery pagaViaticCaja( String data,@RequestParam("tipoCaja") String tipoCaja,@RequestParam("cajaG") String cajaG,@RequestParam("cajaSub") String cajaSub, WebRequest webRequest, Model model) throws IOException {
       ResponseQuery rq = new ResponseQuery();
        //System.out.println("data Pagos creacion:"+data);
        
         int index = data.indexOf("[");
        if (index == -1) {
            data = "[" + data + "]";
        }
  

            String compania = model.asMap().get("compania").toString();
            String usuario = model.asMap().get("usuario").toString();
            ObjectMapper mapper = new ObjectMapper();
          
            System.out.println("tipoCaja: "+tipoCaja);
            System.out.println("cajaG: "+cajaG);
            System.out.println("cajaSub: "+cajaSub);
            
        try{
            List<ViaticosDTO> lista = mapper.readValue(data,
                    mapper.getTypeFactory().constructCollectionType(List.class,
                            ViaticosDTO.class));

            if (lista.isEmpty()) {
                rq.setSuccess(false);
                rq.setData(null);
                rq.setMsg("Error no existen datos que guardar");
                return rq;
            }            Iterator<ViaticosDTO> it = lista.iterator();
        
           
           Querys que = new Querys();
            String store = que.getSQL("ActualizaViaticCaja");
          
            while (it.hasNext()) {
//                 System.out.println("-------------------------------------------------------------------");
                 ViaticosDTO ss = it.next();
               //  if (ss.estatusCxp == null){
            
                 
                   
                  Map paramViatico = new HashMap();
                    paramViatico.put("COMPANIA",compania);
                    paramViatico.put("P_ID_CAJA",cajaG);
                    paramViatico.put("P_ID_SUBCAJA",cajaSub);
                    paramViatico.put("P_COMISION",ss.numero);
                    paramViatico.put("P_TIPO","FG");
                    paramViatico.put("IMPORTE_PAGADO","FG");
                    paramViatico.put("P_FECHA_PAGO","FG");
                    paramViatico.put("TIPO","FG");
                    paramViatico.put("TIPO","FG");
                    paramViatico.put("TIPO","FG");
                    paramViatico.put("TIPO","FG");
                   // paramViatico.put("FOLIO_PAGO",geFolio);
            
                //int val = processDao.execute(store, paramViatico);

                //     if (val <= 0) {

                //}
            
//             

            }
            
            

            
      //     if (result2 == true){ 
             rq.setSuccess(true);
             rq.setData(null);
             rq.setMsg("Facturas enviadas con folio: ");
      //     }
            
        }catch(Exception e){
            rq.setSuccess(false);
            rq.setData(null);
            rq.setMsg("Error al guardar");
           return rq;
        }
        
        return rq;
    }
    
    
    
    
            
            
        @RequestMapping(value = "/enviaCajaViaticos", method = RequestMethod.POST)
    public @ResponseBody
    ResponseQuery cancelaViaticos( String data,WebRequest webRequest, Model model) throws IOException {

        ResponseQuery rq = new ResponseQuery();
   //     System.out.println("data factura cancelacion:"+data);
        
         int index = data.indexOf("[");
        if (index == -1) {
            data = "[" + data + "]";
        }


            String compania = model.asMap().get("compania").toString();
            String usuario = model.asMap().get("usuario").toString();
            ObjectMapper mapper = new ObjectMapper();
          
        try{
            List<ViaticosDTO> lista = mapper.readValue(data,
                    mapper.getTypeFactory().constructCollectionType(List.class,
                            ViaticosDTO.class));

            if (lista.isEmpty()) {
                rq.setSuccess(false);
                rq.setData(null);
                rq.setMsg("Error no existen datos que guardar");
                return rq;
            }

  
            Iterator<ViaticosDTO> it = lista.iterator();
//           ErpCxpFolios folios = new ErpCxpFolios();
//           ErpCxpFoliosId foliosId = new ErpCxpFoliosId();
//           foliosId.setCompania(compania);
//           ErpFeComprobantes comprobantes = new ErpFeComprobantes();
//           ErpFeComprobantesId comprobantesId = new ErpFeComprobantesId();
           
           int banderaGe = 0;
          
            while (it.hasNext()) {
//                 System.out.println("-------------------------------------------------------------------");
                 ViaticosDTO ss = it.next();
               //  if (ss.estatusCxp == null){
               
               
                   Querys que = new Querys();
                    String store = que.getSQL("ACTUALIZA_EST_VIATICOS");

                    Map param = new HashMap();


                       param.put("compania", compania);
                       param.put("comision", ss.numero);
                       param.put("motivo", "");
                       param.put("estatus", "EC");
                        int val = processDao.execute(store, param);

                 
             
                 banderaGe = 1;

            }
            
     
            
            
            
            if (banderaGe == 1){
            
             
                
                    rq.setSuccess(true);
                    rq.setData(null);
                    rq.setMsg("El Viatico se envio correctamente");
                
                
            }else{
                
                rq.setSuccess(true);
                rq.setData(null);
                rq.setMsg("Error al guardar");
                
            }
            
        }catch(Exception e){
            rq.setSuccess(false);
            rq.setData(null);
            rq.setMsg("Error al guardar");
           return rq;
        }
        
        return rq;
    }        
            
    
            
        @RequestMapping(value = "/cancelaViaticos", method = RequestMethod.POST)
    public @ResponseBody
    ResponseQuery cancelaViaticos( String data, @RequestParam("text") String text,WebRequest webRequest, Model model) throws IOException {

        ResponseQuery rq = new ResponseQuery();
   //     System.out.println("data factura cancelacion:"+data);
        
         int index = data.indexOf("[");
        if (index == -1) {
            data = "[" + data + "]";
        }


            String compania = model.asMap().get("compania").toString();
            String usuario = model.asMap().get("usuario").toString();
            ObjectMapper mapper = new ObjectMapper();
          
        try{
            List<ViaticosDTO> lista = mapper.readValue(data,
                    mapper.getTypeFactory().constructCollectionType(List.class,
                            ViaticosDTO.class));

            if (lista.isEmpty()) {
                rq.setSuccess(false);
                rq.setData(null);
                rq.setMsg("Error no existen datos que guardar");
                return rq;
            }

  
            Iterator<ViaticosDTO> it = lista.iterator();
//           ErpCxpFolios folios = new ErpCxpFolios();
//           ErpCxpFoliosId foliosId = new ErpCxpFoliosId();
//           foliosId.setCompania(compania);
//           ErpFeComprobantes comprobantes = new ErpFeComprobantes();
//           ErpFeComprobantesId comprobantesId = new ErpFeComprobantesId();
           
           int banderaGe = 0;
          
            while (it.hasNext()) {
//                 System.out.println("-------------------------------------------------------------------");
                 ViaticosDTO ss = it.next();
               //  if (ss.estatusCxp == null){
               
               
                   Querys que = new Querys();
                    String store = que.getSQL("ACTUALIZA_EST_VIATICOS");

                    Map param = new HashMap();


                       param.put("compania", compania);
                       param.put("comision", ss.numero);
                       param.put("motivo", text);
                       param.put("estatus", "R");

                        int val = processDao.execute(store, param);

                 
             
                 banderaGe = 1;

            }
            
     
            
            
            
            if (banderaGe == 1){
            
             
                
                    rq.setSuccess(true);
                    rq.setData(null);
                    rq.setMsg("El Viatico se rechazo correctamente");
                
                
            }else{
                
                rq.setSuccess(true);
                rq.setData(null);
                rq.setMsg("Error al rechazar");
                
            }
            
        }catch(Exception e){
            rq.setSuccess(false);
            rq.setData(null);
            rq.setMsg("Error al rechazar");
           return rq;
        }
        
        return rq;
    }        
    
        @RequestMapping(value = "/cancelaFactura", method = RequestMethod.POST)
    public @ResponseBody
    ResponseQuery cancelaFactura( String data, @RequestParam("text") String text,WebRequest webRequest, Model model) throws IOException {

        ResponseQuery rq = new ResponseQuery();
   //     System.out.println("data factura cancelacion:"+data);
        
         int index = data.indexOf("[");
        if (index == -1) {
            data = "[" + data + "]";
        }


            String compania = model.asMap().get("compania").toString();
            String usuario = model.asMap().get("usuario").toString();
            ObjectMapper mapper = new ObjectMapper();
          
        try{
            List<CxpDTO> lista = mapper.readValue(data,
                    mapper.getTypeFactory().constructCollectionType(List.class,
                            CxpDTO.class));

            if (lista.isEmpty()) {
                rq.setSuccess(false);
                rq.setData(null);
                rq.setMsg("Error no existen datos que guardar");
                return rq;
            }

  
            Iterator<CxpDTO> it = lista.iterator();
           ErpCxpFolios folios = new ErpCxpFolios();
           ErpCxpFoliosId foliosId = new ErpCxpFoliosId();
           foliosId.setCompania(compania);
           ErpFeComprobantes comprobantes = new ErpFeComprobantes();
           ErpFeComprobantesId comprobantesId = new ErpFeComprobantesId();
           
           int banderaGe = 0;
          
            while (it.hasNext()) {
//                 System.out.println("-------------------------------------------------------------------");
                 CxpDTO ss = it.next();
               //  if (ss.estatusCxp == null){
                 
                 int id = erpCxpFoliosDao.getMaxIdCxpFolios(foliosId);
                 
                // folioPagos = id;
              
                 foliosId.setId(id);
              
                 folios.setId(foliosId);
              
                 folios.setFolio(-1);
               
                 folios.setOperacion("CANF");
              
                 folios.setUsuario(usuario);
             
                 folios.setNumeroFe(Integer.parseInt(ss.numero));
                 
                 ErpCxpFoliosId result = erpCxpFoliosDao.save(folios);
                 
                  
                 comprobantesId.setCompania(compania);
                 comprobantesId.setNumero(Integer.parseInt(ss.numero));
                 comprobantes.setId(comprobantesId);
                 comprobantes.setEstatusCxp("CANF");
                 comprobantes.setFolioPagos(-1);
                 comprobantes.setUsuarioCancelo(usuario);
                 comprobantes.setFechaCancelacion(new Date());
                 
                  
                 boolean result2 = erpFeComprobantesDao.actualizaEstatusFolioPagos(comprobantes, "");
                 erpFeComprobantesDao.descripcionCancelacion(text, comprobantes);
                 
                 banderaGe = 1;

            }
            
     
            
            
            
            if (banderaGe == 1){
            
             
                
                    rq.setSuccess(true);
                    rq.setData(null);
                    rq.setMsg("La factura se cancelo correctamente");
                
                
            }else{
                
                rq.setSuccess(true);
                rq.setData(null);
                rq.setMsg("Error al cancelar");
                
            }
            
        }catch(Exception e){
            rq.setSuccess(false);
            rq.setData(null);
            rq.setMsg("Error al cancelar");
           return rq;
        }
        
        return rq;
    }
    
    
       @RequestMapping(value = "/cancelaFacturaExt", method = RequestMethod.POST)
    public @ResponseBody
    ResponseQuery cancelaFacturaExt( String data, @RequestParam("text") String text,WebRequest webRequest, Model model) throws IOException {

        ResponseQuery rq = new ResponseQuery();
        System.out.println("data factura cancelacion:"+data);
        
         int index = data.indexOf("[");
        if (index == -1) {
            data = "[" + data + "]";
        }


            String compania = model.asMap().get("compania").toString();
            String usuario = model.asMap().get("usuario").toString();
            ObjectMapper mapper = new ObjectMapper();
          
        try{
            List<CxpOtrasDTO> lista = mapper.readValue(data,
                    mapper.getTypeFactory().constructCollectionType(List.class,
                            CxpOtrasDTO.class));

            if (lista.isEmpty()) {
                rq.setSuccess(false);
                rq.setData(null);
                rq.setMsg("Error no existen datos que guardar");
                return rq;
            }

  
            Iterator<CxpOtrasDTO> it = lista.iterator();
           ErpCxpFolios folios = new ErpCxpFolios();
           ErpCxpFoliosId foliosId = new ErpCxpFoliosId();
           foliosId.setCompania(compania);
           ErpCpOtras erpCpOtras = new ErpCpOtras();
           ErpCpOtrasId erpCpOtrasId = new ErpCpOtrasId();
           
           int banderaGe = 0;
          
            while (it.hasNext()) {
                 System.out.println("-------------------------------------------------------------------");
                 
                 CxpOtrasDTO ss = it.next();
                 System.out.println("id: "+ss.id);
               //  if (ss.estatusCxp == null){
                 
                 int id = erpCxpFoliosDao.getMaxIdCxpFolios(foliosId);
                 
                // folioPagos = id;
              
                 foliosId.setId(id);
              
                 folios.setId(foliosId);
              
                 folios.setFolio(-1);
               
                 folios.setOperacion("CANF");
              
                 folios.setUsuario(usuario);
             
                 folios.setNumeroFe(Integer.parseInt(ss.id));
                 
                 ErpCxpFoliosId result = erpCxpFoliosDao.save(folios);
                 
                  
                 erpCpOtrasId.setCompania(compania);
                 erpCpOtrasId.setId(Integer.parseInt(ss.id));
                 erpCpOtras.setId(erpCpOtrasId);
                 erpCpOtras.setEstatusCxp("CANF");
                 erpCpOtras.setFolioPagos(-1);
                 erpCpOtras.setUsuarioCancela(usuario);
                 erpCpOtras.setFechaCancela(new Date());
                 
                  System.out.println("Actualizando estatus");
                 boolean result2 = erpCpOtrasDao.updateErpCpOtrasEstatusPagos(erpCpOtras);
                 
                 System.out.println("Actualizando datos de cancelacion");
                 erpCpOtrasDao.descripcionCancelacion(text, erpCpOtras);
                 
                 banderaGe = 1;

            }
            
     
            
            
            
            if (banderaGe == 1){
            
             
                
                    rq.setSuccess(true);
                    rq.setData(null);
                    rq.setMsg("La factura se cancelo correctamente");
                
                
            }else{
                
                rq.setSuccess(true);
                rq.setData(null);
                rq.setMsg("Error al cancelar");
                
            }
            
        }catch(Exception e){
            e.printStackTrace();
            rq.setSuccess(false);
            rq.setData(null);
            rq.setMsg("Error al cancelar");
           return rq;
        }
        
        return rq;
    }
    
     @RequestMapping(value = "/updateFolioTes", method = RequestMethod.POST)
    public @ResponseBody
    ResponseQuery updateFolioTes( String data,@RequestParam("folio") String folio, WebRequest webRequest, Model model) throws IOException {

        ResponseQuery rq = new ResponseQuery();
        
         int index = data.indexOf("[");
        if (index == -1) {
            data = "[" + data + "]";
        }


            String compania = model.asMap().get("compania").toString();
            String usuario = model.asMap().get("usuario").toString();
            ObjectMapper mapper = new ObjectMapper();
          
        try{
            List<TesoreriaDTO> lista = mapper.readValue(data,
                    mapper.getTypeFactory().constructCollectionType(List.class,
                            TesoreriaDTO.class));
            
            
            Map validaFolio = new HashMap();
                
               validaFolio.put("folio", folio);
               validaFolio.put("compania", compania);
               
                   List folioValid = processDao.getMapResult("validaFolioTes", validaFolio);
                   
              if (folioValid == null || folioValid.isEmpty() || folioValid.size() == 0){
              
                rq.setSuccess(false);
                rq.setMsg("El folio no se puede actualizar por que ya fue enviado a tesoreria");
                return rq; 
              
              }
            
            
            
            if (folio.equalsIgnoreCase("") || folio == null){
                
                rq.setSuccess(false);
                rq.setData(null);
                rq.setMsg("Error el folio de la actualizacion es nulo");
                return rq;
            
            
            }

            if (lista.isEmpty()) {
                rq.setSuccess(false);
                rq.setData(null);
                rq.setMsg("Error no existen datos que guardar");
                return rq;
            }

  
            Iterator<TesoreriaDTO> it = lista.iterator();
           ErpCxpFolios folios = new ErpCxpFolios();
           ErpCxpFoliosId foliosId = new ErpCxpFoliosId();
           
           ErpFeComprobantes comprobantes = new ErpFeComprobantes();
           ErpFeComprobantesId comprobantesId = new ErpFeComprobantesId();
           
           ErpCpOtras otras = new ErpCpOtras();
           ErpCpOtrasId otrasId = new ErpCpOtrasId();
           
           foliosId.setCompania(compania);
           
           int geFolio = Integer.parseInt(folio);
           
           int banderaGe = 0;
           
           int folioPagos = geFolio;
          
            while (it.hasNext()) {
                 TesoreriaDTO ss = it.next();
                 int id = erpCxpFoliosDao.getMaxIdCxpFolios(foliosId);

                 foliosId.setId(id);
                 folios.setId(foliosId);
                 folios.setFolio(geFolio);
                 folios.setOperacion("FG");
                 folios.setUsuario(usuario);
                 folios.setOrigen(ss.origen);
                 folios.setNumeroFe(Integer.parseInt(ss.numero));
                
                 
                 ErpCxpFoliosId result = erpCxpFoliosDao.save(folios);
                 
                  if(ss.origen.equalsIgnoreCase("F")){
                 
                        comprobantesId.setCompania(compania);
                        comprobantesId.setNumero(Integer.parseInt(ss.numero));
                        comprobantes.setId(comprobantesId);
                        comprobantes.setEstatusCxp("FG");
                        comprobantes.setTipoPago(ss.tipoPagoTes);
                        comprobantes.setTotalAPagar(new BigDecimal(ss.totalAPagar));
                        comprobantes.setFolioPagos(geFolio);
                         BigDecimal pagoCie = new BigDecimal(0);
                        if(ss.pagoCie == "" || ss.pagoCie == null){
                        }else{
                            pagoCie = new BigDecimal(ss.pagoCie);
                        }
                        comprobantes.setReferenciaCie(ss.referenciaCie);
                        comprobantes.setConceptoCie(ss.conceptoCie);

                        boolean result2 = false;

                        if (ss.cie.equalsIgnoreCase("1")){


                          result2 = erpFeComprobantesDao.actualizaEstatusFolioPagosCie(comprobantes, ss.monedaPago, pagoCie);

                        }else{

                            result2 = erpFeComprobantesDao.actualizaEstatusFolioPagos(comprobantes, ss.monedaPago);

                        }
                 
                  }
                  if(ss.origen.equalsIgnoreCase("O")){

                      otrasId.setCompania(ss.compania);
                      otrasId.setId(Integer.parseInt(ss.numero));
                      otras.setId(otrasId);
                      otras.setEstatusCxp("FG");
                      otras.setFolioPagos(geFolio);
                      otras.setPagoCie(ss.pagoCie);
                      otras.setRefenciaCie(ss.referenciaCie);
                      otras.setConceptoCie(ss.conceptoCie);
                      otras.setTipoPago(ss.tipoPagoTes);
                      otras.setTotalAPagar(new BigDecimal(ss.totalAPagar));
                      erpCpOtrasDao.updateErpCpOtrasEstatusPagos(otras);

                 }
                 
                 banderaGe = 1;

            }
            
            
            if (banderaGe == 1){
            
                rq.setSuccess(true);
                rq.setData(null);
                rq.setMsg("Facturas enviadas con folio: "+geFolio);
            }else{
                
                rq.setSuccess(true);
                rq.setData(null);
                rq.setMsg("No hay registros para enviar");
                
            }
            
        }catch(Exception e){
            rq.setSuccess(false);
            rq.setData(null);
            rq.setMsg("Error al guardar");
           return rq;
        }
        
        return rq;
    }
    
    
        
    @RequestMapping(value = "/savePagoOtras", method = RequestMethod.POST)
    public @ResponseBody
    ResponseQuery saveSeguiPagosOtras( String data, WebRequest webRequest, Model model) throws IOException, ParseException {

        ResponseQuery rq = new ResponseQuery();
        System.out.println("data Pagos:"+data);
        
         int index = data.indexOf("[");
        if (index == -1) {
            data = "[" + data + "]";
        }


            String compania = model.asMap().get("compania").toString();
            String usuario = model.asMap().get("usuario").toString();
            ObjectMapper mapper = new ObjectMapper();
            
            DateFormat df = new SimpleDateFormat("dd/MM/yyyy");

           
          
        try{
            
              List<PagosDTO> lista = mapper.readValue(data,
                    mapper.getTypeFactory().constructCollectionType(List.class,
                            PagosDTO.class));

            if (lista.isEmpty()) {
                rq.setSuccess(false);
                rq.setData(null);
                rq.setMsg("Error no existen datos que guardar");
                return rq;
            }
          
            Iterator<PagosDTO> it = lista.iterator();
            
            ErpSeguiDocumentos segui = new ErpSeguiDocumentos(); 
            ErpSeguiDocumentosId seguiId = new ErpSeguiDocumentosId(); 
            String numFE = "";
            String fechaPago = "";
            
              while (it.hasNext()) {
         //        System.out.println("------------------------------PAGOS-------------------------------");
                 PagosDTO ss = it.next();
                 numFE = ss.numFe;
         //        System.out.println("pagos"+ss.tOperacionDet);
                 
                 seguiId.setCompania(compania);
                 seguiId.setNumFe(Integer.parseInt(ss.numFe));
                 seguiId.setOrigen("P");
                 seguiId.setSerie("A");
                 seguiId.settDoc("OTR");
                 int sec = erpSeguiDocumentosDao.getMaxId(seguiId);
        //         System.out.println("pagos sec "+sec);
                 seguiId.setSec(sec);
                 
                 segui.setId(seguiId);
                 segui.setBanco(ss.banco);
                     
                 segui.setFolio(Integer.parseInt(ss.numFe));
                     
                
                 segui.setImporteOperacion(new BigDecimal(ss.importeOperacionDet));
                 //segui.setMoneda(ss.);
                 segui.setObservaciones(ss.observaciones);
                 segui.setParidad(new BigDecimal(ss.paridad));
                 segui.setTotPesos(new BigDecimal(ss.totPesos));
                 segui.setUsuario(usuario);
                 segui.settOperacion(ss.tOperacionDet);
                 segui.setCuentaBanco(ss.cuentaBanco);
                 segui.setFechaPagoCxpFe(df.parse(ss.fechaPago));
                 segui.setBancoPago(ss.bancoPago);
                 segui.setCuentaPago(ss.cuentaPago);
                 segui.setMoneda(ss.monedaPago);
                 fechaPago = ss.fechaPago;
                 
                 ErpSeguiDocumentosId result = erpSeguiDocumentosDao.save(segui);
          //       System.out.println("buscando ..........");
            
                      rq.setSuccess(true);
                        rq.setData(null);
                        rq.setMsg("Pago Guerdado");
                      
                 
                 
             
                 
                 

            }
              
        //      System.out.println("buscando actualizar");
              
                Map pagoTotal = new HashMap();
                
               pagoTotal.put("compania", compania);
               pagoTotal.put("numero", numFE);
               
                   List listTotal = processDao.getMapResult("TotalesPagosOtras", pagoTotal);
                   System.out.println("Lista total");
                   System.out.println(listTotal);
                   
                    Map pag = (HashMap) listTotal.get(0);
                    System.out.println("Lista total");
                    BigDecimal pago = new BigDecimal(pag.get("PAGO").toString());
                    BigDecimal total = new BigDecimal(pag.get("TOTAL").toString());
                    
                    System.out.println("buscando actualizar"+pago);
                    System.out.println("buscando actualizar"+total);
                     
                    ErpCpOtras comp = new ErpCpOtras();
                    ErpCpOtrasId compId = new ErpCpOtrasId();
                    
                    compId.setCompania(compania);
                    compId.setId(Integer.parseInt(numFE));
                    comp.setId(compId);
                    comp.setPagoAcumuladoCXP(pago);
                    comp.setFechaPagoCxp(df.parse(fechaPago));
                    
                   if (total.compareTo(pago) == 1){
                       
                        comp.setEstatusCxp("PAR");
                        
                        erpCpOtrasDao.updateErpCpOtrasEstatusPagosOtras(comp);
                   
                   
                   }
                   
                    if (total.compareTo(pago) == 0){
                      
                       comp.setEstatusCxp("PAG");
                        
                        erpCpOtrasDao.updateErpCpOtrasEstatusPagosOtras(comp);
                   
                   
                   }
                    
                     if (total.compareTo(pago) == -1){
                       
                       comp.setEstatusCxp("PAG");
                        
                        erpCpOtrasDao.updateErpCpOtrasEstatusPagosOtras(comp);
                   
                   
                   }
                          
          
                     

         
            
        }catch(Exception e){
            rq.setSuccess(false);
            rq.setData(null);
            rq.setMsg("Error al guardar");
           return rq;
        }
        
        return rq;
    }


        @RequestMapping(value = "/savePagoCXC", method = RequestMethod.POST)
    public @ResponseBody
    ResponseQuery saveSeguiPagosCXC( String data, WebRequest webRequest, Model model) throws IOException, ParseException {

        ResponseQuery rq = new ResponseQuery();
     //   System.out.println("data Pagos:"+data);
        
         int index = data.indexOf("[");
        if (index == -1) {
            data = "[" + data + "]";
        }

        System.out.println("data:");
        System.out.println(data);
            String compania = model.asMap().get("compania").toString();
            String usuario = model.asMap().get("usuario").toString();
            ObjectMapper mapper = new ObjectMapper();
            
            DateFormat df = new SimpleDateFormat("dd/MM/yyyy");

           
          
        try{
            
              List<PagosDTO> lista = mapper.readValue(data,
                    mapper.getTypeFactory().constructCollectionType(List.class,
                            PagosDTO.class));

            if (lista.isEmpty()) {
                rq.setSuccess(false);
                rq.setData(null);
                rq.setMsg("Error no existen datos que guardar");
                return rq;
            }
          
            Iterator<PagosDTO> it = lista.iterator();
            
            ErpSeguiDocumentos segui = new ErpSeguiDocumentos(); 
            ErpSeguiDocumentosId seguiId = new ErpSeguiDocumentosId(); 
            String numFE = "";
            
            System.err.println("generando pagos");
            
              while (it.hasNext()) {
         //        System.out.println("------------------------------PAGOS-------------------------------");
                 PagosDTO ss = it.next();
                 numFE = ss.numFe;
         //        System.out.println("pagos"+ss.tOperacionDet);
                 
                 seguiId.setCompania(compania);
                 seguiId.setNumFe(Integer.parseInt(ss.numFe));
                 seguiId.setOrigen("C");
                 seguiId.setSerie("A");
                 seguiId.settDoc("FE");
                 int sec = erpSeguiDocumentosDao.getMaxId(seguiId);
        //         System.out.println("pagos sec "+sec);
                 seguiId.setSec(sec);
                 
                 segui.setId(seguiId);
                 segui.setBanco(ss.banco);
                 //if (ss.folio == null){
                     
                   segui.setFolio(Integer.parseInt(ss.numFe));
                     
                // }else{
                //  segui.setFolio(Integer.parseInt(ss.folio));
                // }
                 segui.setImporteOperacion(new BigDecimal(ss.importeOperacionDet));
                 //segui.setMoneda(ss.);
                 segui.setObservaciones(ss.observaciones);
                 segui.setParidad(new BigDecimal(ss.paridad));
                 segui.setTotPesos(new BigDecimal(ss.totPesos));
                 segui.setUsuario(usuario);
                 segui.settOperacion(ss.tOperacionDet);
                 segui.setCuentaBanco(ss.cuentaBanco);
                 segui.setFechaPagoCxpFe(df.parse(ss.fechaPago));
                 segui.setBancoPago(ss.bancoPago);
                 segui.setCuentaPago(ss.cuentaPago);
                 segui.setMoneda(ss.monedaPago);
                 
                 ErpSeguiDocumentosId result = erpSeguiDocumentosDao.save(segui);
          //       System.out.println("buscando ..........");
            
                      rq.setSuccess(true);
                        rq.setData(null);
                        rq.setMsg("Pago Guerdado");
                      
                 
                 if(ss.idTransaccion != null && !ss.idTransaccion.equalsIgnoreCase("")){
                 
                     ErpSatTransaccion tran = new ErpSatTransaccion();
                     ErpSatTransaccionId tranId = new ErpSatTransaccionId();
                     
                     tranId.setCompania(compania);
                     tranId.setId(new BigDecimal(ss.idTransaccion));
                     tran.setId(tranId);
                     tran.setFecha(df.parse(ss.fechaPago));
                     tran.setMontoTotal(new BigDecimal(ss.totPesos));
                     tran.setTipCamb(new BigDecimal(ss.paridad));
                     tran.setBancoOriNal(ss.banco);
                     tran.setBancoDestNal(ss.banco);
                     tran.setCtaDest(ss.cuentaBanco);
                     tran.setCtaOri(ss.cuentaBanco);
                 
                     if(ss.origen.equalsIgnoreCase("CXP")){
                           erpSatTransaccionDao.actualizaTransaccionCXP(tran);
                     }
                     
                     if(ss.origen.equalsIgnoreCase("CXC")){
                           erpSatTransaccionDao.actualizaTransaccionCXC(tran);
                     }
                 
                 
                 }
                 
             
                 
                 

            }
              
        //      System.out.println("buscando actualizar");
              
                Map pagoTotal = new HashMap();
                
               pagoTotal.put("compania", compania);
               pagoTotal.put("numero", numFE);
               
                   List listTotal = processDao.getMapResult("TotalesPagos", pagoTotal);
                   
                    Map pag = (HashMap) listTotal.get(0);
                    BigDecimal pago = new BigDecimal(pag.get("PAGO").toString());
                    BigDecimal total = new BigDecimal(pag.get("TOTAL").toString());
                    
         //           System.out.println("buscando actualizar"+pago);
         //           System.out.println("buscando actualizar"+total);
                     
                    ErpFeComprobantes comp = new ErpFeComprobantes();
                    ErpFeComprobantesId compId = new ErpFeComprobantesId();
                    
                    compId.setCompania(compania);
                    compId.setNumero(Integer.parseInt(numFE));
                    comp.setId(compId);
                    
                   if (total.compareTo(pago) == 1){
                       
                       erpFeComprobantesDao.actualizaPago("PAR", pago, comp);
                   
                   
                   }
                   
                    if (total.compareTo(pago) == 0){
                       
                       erpFeComprobantesDao.actualizaPago("PAG", pago, comp);
                   
                   
                   }
                    
                     if (total.compareTo(pago) == -1){
                       
                       erpFeComprobantesDao.actualizaPago("PAG", pago, comp);
                   
                   
                   }
                     
                     
                     
                     
                          
          
                     

         
            
        }catch(Exception e){
            e.printStackTrace();
            rq.setSuccess(false);
            rq.setData(null);
            rq.setMsg("Error al guardar");
           return rq;
        }
        
        return rq;
    }


    
    
    
    @RequestMapping(value = "/savePago", method = RequestMethod.POST)
    public @ResponseBody
    ResponseQuery saveSeguiPagos( String data, WebRequest webRequest, Model model) throws IOException, ParseException {

        ResponseQuery rq = new ResponseQuery();
     //   System.out.println("data Pagos:"+data);
        
         int index = data.indexOf("[");
        if (index == -1) {
            data = "[" + data + "]";
        }

        System.out.println("data:");
        System.out.println(data);
            String compania = model.asMap().get("compania").toString();
            String usuario = model.asMap().get("usuario").toString();
            ObjectMapper mapper = new ObjectMapper();
            
            DateFormat df = new SimpleDateFormat("dd/MM/yyyy");

           
          
        try{
            
              List<PagosDTO> lista = mapper.readValue(data,
                    mapper.getTypeFactory().constructCollectionType(List.class,
                            PagosDTO.class));

            if (lista.isEmpty()) {
                rq.setSuccess(false);
                rq.setData(null);
                rq.setMsg("Error no existen datos que guardar");
                return rq;
            }
          
            Iterator<PagosDTO> it = lista.iterator();
            
            ErpSeguiDocumentos segui = new ErpSeguiDocumentos(); 
            ErpSeguiDocumentosId seguiId = new ErpSeguiDocumentosId(); 
            String numFE = "";
            
            System.err.println("generando pagos");
            
              while (it.hasNext()) {
         //        System.out.println("------------------------------PAGOS-------------------------------");
                 PagosDTO ss = it.next();
                 numFE = ss.numFe;
         //        System.out.println("pagos"+ss.tOperacionDet);
                 
                 seguiId.setCompania(compania);
                 seguiId.setNumFe(Integer.parseInt(ss.numFe));
                 seguiId.setOrigen("P");
                 seguiId.setSerie("A");
                 seguiId.settDoc("FE");
                 int sec = erpSeguiDocumentosDao.getMaxId(seguiId);
        //         System.out.println("pagos sec "+sec);
                 seguiId.setSec(sec);
                 
                 segui.setId(seguiId);
                 segui.setBanco(ss.banco);
                 //if (ss.folio == null){
                     
                   segui.setFolio(Integer.parseInt(ss.numFe));
                     
                // }else{
                //  segui.setFolio(Integer.parseInt(ss.folio));
                // }
                 segui.setImporteOperacion(new BigDecimal(ss.importeOperacionDet));
                 //segui.setMoneda(ss.);
                 segui.setObservaciones(ss.observaciones);
                 segui.setParidad(new BigDecimal(ss.paridad));
                 segui.setTotPesos(new BigDecimal(ss.totPesos));
                 segui.setUsuario(usuario);
                 segui.settOperacion(ss.tOperacionDet);
                 segui.setCuentaBanco(ss.cuentaBanco);
                 segui.setFechaPagoCxpFe(df.parse(ss.fechaPago));
                 segui.setBancoPago(ss.bancoPago);
                 segui.setCuentaPago(ss.cuentaPago);
                 segui.setMoneda(ss.monedaPago);
                 
                 ErpSeguiDocumentosId result = erpSeguiDocumentosDao.save(segui);
          //       System.out.println("buscando ..........");
            
                      rq.setSuccess(true);
                        rq.setData(null);
                        rq.setMsg("Pago Guerdado");
                      
                 
                 if(ss.idTransaccion != null && !ss.idTransaccion.equalsIgnoreCase("")){
                 
                     ErpSatTransaccion tran = new ErpSatTransaccion();
                     ErpSatTransaccionId tranId = new ErpSatTransaccionId();
                     
                     tranId.setCompania(compania);
                     tranId.setId(new BigDecimal(ss.idTransaccion));
                     tran.setId(tranId);
                     tran.setFecha(df.parse(ss.fechaPago));
                     tran.setMontoTotal(new BigDecimal(ss.totPesos));
                     tran.setTipCamb(new BigDecimal(ss.paridad));
                     tran.setBancoOriNal(ss.banco);
                     tran.setBancoDestNal(ss.banco);
                     tran.setCtaDest(ss.cuentaBanco);
                     tran.setCtaOri(ss.cuentaBanco);
                 
                     if(ss.origen.equalsIgnoreCase("CXP")){
                           erpSatTransaccionDao.actualizaTransaccionCXP(tran);
                     }
                     
                     if(ss.origen.equalsIgnoreCase("CXC")){
                           erpSatTransaccionDao.actualizaTransaccionCXC(tran);
                     }
                 
                 
                 }
                 
             
                 
                 

            }
              
        //      System.out.println("buscando actualizar");
              
                Map pagoTotal = new HashMap();
                
               pagoTotal.put("compania", compania);
               pagoTotal.put("numero", numFE);
               
                   List listTotal = processDao.getMapResult("TotalesPagos", pagoTotal);
                   
                    Map pag = (HashMap) listTotal.get(0);
                    BigDecimal pago = new BigDecimal(pag.get("PAGO").toString());
                    BigDecimal total = new BigDecimal(pag.get("TOTAL").toString());
                    
         //           System.out.println("buscando actualizar"+pago);
         //           System.out.println("buscando actualizar"+total);
                     
                    ErpFeComprobantes comp = new ErpFeComprobantes();
                    ErpFeComprobantesId compId = new ErpFeComprobantesId();
                    
                    compId.setCompania(compania);
                    compId.setNumero(Integer.parseInt(numFE));
                    comp.setId(compId);
                    
                   if (total.compareTo(pago) == 1){
                       
                       erpFeComprobantesDao.actualizaPago("PAR", pago, comp);
                   
                   
                   }
                   
                    if (total.compareTo(pago) == 0){
                       
                       erpFeComprobantesDao.actualizaPago("PAG", pago, comp);
                   
                   
                   }
                    
                     if (total.compareTo(pago) == -1){
                       
                       erpFeComprobantesDao.actualizaPago("PAG", pago, comp);
                   
                   
                   }
                     
                     
                     
                     
                          
          
                     

         
            
        }catch(Exception e){
            e.printStackTrace();
            rq.setSuccess(false);
            rq.setData(null);
            rq.setMsg("Error al guardar");
           return rq;
        }
        
        return rq;
    }
    
          @RequestMapping(value = "/updatePagoOtras", method = RequestMethod.POST)
    public @ResponseBody
    ResponseQuery updateSeguiPagosOtras( String data, WebRequest webRequest, Model model) throws IOException {

        ResponseQuery rq = new ResponseQuery();
    //    System.out.println("data Pagos:"+data);
        
         int index = data.indexOf("[");
        if (index == -1) {
            data = "[" + data + "]";
        }


            String compania = model.asMap().get("compania").toString();
            String usuario = model.asMap().get("usuario").toString();
            ObjectMapper mapper = new ObjectMapper();
            
            DateFormat df = new SimpleDateFormat("dd/MM/yyyy");

           
          
        try{
            
              List<PagosDTO> lista = mapper.readValue(data,
                    mapper.getTypeFactory().constructCollectionType(List.class,
                            PagosDTO.class));

            if (lista.isEmpty()) {
                rq.setSuccess(false);
                rq.setData(null);
                rq.setMsg("Error no existen datos que guardar");
                return rq;
            }
          
            Iterator<PagosDTO> it = lista.iterator();
            
            ErpSeguiDocumentos segui = new ErpSeguiDocumentos(); 
            ErpSeguiDocumentosId seguiId = new ErpSeguiDocumentosId(); 
            String numFE = "";
            String fechaPago  = "";
            
              while (it.hasNext()) {
          //       System.out.println("------------------------------PAGOS UPDATE-------------------------------");
                 PagosDTO ss = it.next();
                 numFE = ss.numFe;
         //        System.out.println("pagos"+ss.tOperacionDet);
                 
                 seguiId.setCompania(compania);
                 seguiId.setNumFe(Integer.parseInt(ss.numFe));
                 seguiId.setOrigen("P");
                 seguiId.setSerie("A");
                 seguiId.settDoc("OTR");
                 //int sec = erpSeguiDocumentosDao.getMaxId(seguiId);
                 
                 seguiId.setSec(Integer.parseInt(ss.sec));
         //        System.out.println("llave guardada");
                 segui.setId(seguiId);
                 segui.setBanco(ss.banco);
                      
                   segui.setFolio(Integer.parseInt(ss.numFe));
                     
               
          //       System.out.println("folio");
                 segui.setImporteOperacion(new BigDecimal(ss.importeOperacionDet));
          //       System.out.println("importe operacion");
                 //segui.setMoneda(ss.);
                 segui.setObservaciones(ss.observaciones);
                 segui.setParidad(new BigDecimal(ss.paridad));
         //        System.out.println("paridad");
                 segui.setTotPesos(new BigDecimal(ss.totPesos));
          //       System.out.println("ltotpesos");
                 segui.setUsuario(usuario);
                 segui.settOperacion(ss.tOperacionDet);
                 segui.setCuentaBanco(ss.cuentaBanco);
        //         System.out.println("fechaPago"+ss.fechaPago);
                 segui.setFechaPagoCxpFe(df.parse(ss.fechaPago));
                 segui.setBancoPago(ss.bancoPago);
                 segui.setCuentaPago(ss.cuentaPago);
                 segui.setBancoPago(ss.bancoPago);
                 segui.setCuentaPago(ss.cuentaPago);
                 segui.setMoneda(ss.monedaPago);
        //         System.out.println("fechaPago");
                 fechaPago = ss.fechaPago;
        //         System.out.println("pagos enviados");
                 
                 boolean result = erpSeguiDocumentosDao.update(segui);
          //       System.out.println("buscando ..........");
            
                      rq.setSuccess(true);
                        rq.setData(null);
                        rq.setMsg("Pago Guerdado");
                      
                 
                 
             
                 
                 

            }
              
          //    System.out.println("buscando actualizar");
              
                 Map pagoTotal = new HashMap();
                
               pagoTotal.put("compania", compania);
               pagoTotal.put("numero", numFE);
               
                   List listTotal = processDao.getMapResult("TotalesPagosOtras", pagoTotal);
                   System.out.println("Lista total");
                   System.out.println(listTotal);
                   
                    Map pag = (HashMap) listTotal.get(0);
                    System.out.println("Lista total");
                    BigDecimal pago = new BigDecimal(pag.get("PAGO").toString());
                    BigDecimal total = new BigDecimal(pag.get("TOTAL").toString());
                    
                    System.out.println("buscando actualizar"+pago);
                    System.out.println("buscando actualizar"+total);
                     
                    ErpCpOtras comp = new ErpCpOtras();
                    ErpCpOtrasId compId = new ErpCpOtrasId();
                    
                    compId.setCompania(compania);
                    compId.setId(Integer.parseInt(numFE));
                    comp.setId(compId);
                    comp.setPagoAcumuladoCXP(pago);
                    comp.setFechaPagoCxp(df.parse(fechaPago));
                    
                   if (total.compareTo(pago) == 1){
                       
                        comp.setEstatusCxp("PAR");
                        
                        erpCpOtrasDao.updateErpCpOtrasEstatusPagosOtras(comp);
                   
                   
                   }
                   
                    if (total.compareTo(pago) == 0){
                      
                       comp.setEstatusCxp("PAG");
                        
                        erpCpOtrasDao.updateErpCpOtrasEstatusPagosOtras(comp);
                   
                   
                   }
                    
                     if (total.compareTo(pago) == -1){
                       
                       comp.setEstatusCxp("PAG");
                        
                        erpCpOtrasDao.updateErpCpOtrasEstatusPagosOtras(comp);
                   
                   
                   }
                        
                          
          
                     

         
            
        }catch(Exception e){
            rq.setSuccess(false);
            rq.setData(null);
            rq.setMsg("Error al guardar");
           return rq;
        }
        
        return rq;
    }
    
      @RequestMapping(value = "/updatePago", method = RequestMethod.POST)
    public @ResponseBody
    ResponseQuery updateSeguiPagos( String data, WebRequest webRequest, Model model) throws IOException {

        ResponseQuery rq = new ResponseQuery();
    //    System.out.println("data Pagos:"+data);
        
         int index = data.indexOf("[");
        if (index == -1) {
            data = "[" + data + "]";
        }


            String compania = model.asMap().get("compania").toString();
            String usuario = model.asMap().get("usuario").toString();
            ObjectMapper mapper = new ObjectMapper();
            
            DateFormat df = new SimpleDateFormat("dd/MM/yyyy");

           
          
        try{
            
              List<PagosDTO> lista = mapper.readValue(data,
                    mapper.getTypeFactory().constructCollectionType(List.class,
                            PagosDTO.class));

            if (lista.isEmpty()) {
                rq.setSuccess(false);
                rq.setData(null);
                rq.setMsg("Error no existen datos que guardar");
                return rq;
            }
          
            Iterator<PagosDTO> it = lista.iterator();
            
            ErpSeguiDocumentos segui = new ErpSeguiDocumentos(); 
            ErpSeguiDocumentosId seguiId = new ErpSeguiDocumentosId(); 
            String numFE = "";
            
              while (it.hasNext()) {
          //       System.out.println("------------------------------PAGOS UPDATE-------------------------------");
                 PagosDTO ss = it.next();
                 numFE = ss.numFe;
         //        System.out.println("pagos"+ss.tOperacionDet);
                 
                 seguiId.setCompania(compania);
                 seguiId.setNumFe(Integer.parseInt(ss.numFe));
                 seguiId.setOrigen("P");
                 seguiId.setSerie("A");
                 seguiId.settDoc("FE");
                 //int sec = erpSeguiDocumentosDao.getMaxId(seguiId);
                 
                 seguiId.setSec(Integer.parseInt(ss.sec));
         //        System.out.println("llave guardada");
                 segui.setId(seguiId);
                 segui.setBanco(ss.banco);
                  if (ss.folio == null){
                     
                   segui.setFolio(Integer.parseInt(ss.numFe));
                     
                 }else{
                  segui.setFolio(Integer.parseInt(ss.folio));
                 }
          //       System.out.println("folio");
                 segui.setImporteOperacion(new BigDecimal(ss.importeOperacionDet));
          //       System.out.println("importe operacion");
                 //segui.setMoneda(ss.);
                 segui.setObservaciones(ss.observaciones);
                 segui.setParidad(new BigDecimal(ss.paridad));
         //        System.out.println("paridad");
                 segui.setTotPesos(new BigDecimal(ss.totPesos));
          //       System.out.println("ltotpesos");
                 segui.setUsuario(usuario);
                 segui.settOperacion(ss.tOperacionDet);
                 segui.setCuentaBanco(ss.cuentaBanco);
        //         System.out.println("fechaPago"+ss.fechaPago);
                 segui.setFechaPagoCxpFe(df.parse(ss.fechaPago));
                 segui.setBancoPago(ss.bancoPago);
                 segui.setCuentaPago(ss.cuentaPago);
                 segui.setMoneda(ss.monedaPago);
                 
                 if(ss.idTransaccion != null){
                     
                     segui.setIdTransaccion(Integer.parseInt(ss.idTransaccion));
                 
                 
                 }
                 
        //         System.out.println("fechaPago");
                 
        //         System.out.println("pagos enviados");
                 
                 boolean result = erpSeguiDocumentosDao.update(segui);
          //       System.out.println("buscando ..........");
            
                      rq.setSuccess(true);
                        rq.setData(null);
                        rq.setMsg("Pago Guerdado");
                      
                    System.out.println("buscando transacccion ..........");
                    
                    System.out.println(ss.idTransaccion);
                    if(ss.idTransaccion != null){
                 
                     ErpSatTransaccion tran = new ErpSatTransaccion();
                     ErpSatTransaccionId tranId = new ErpSatTransaccionId();
                     
                     tranId.setCompania(compania);
                     tranId.setId(new BigDecimal(ss.idTransaccion));
                     tran.setId(tranId);
                     tran.setFecha(df.parse(ss.fechaPago));
                     tran.setMontoTotal(new BigDecimal(ss.totPesos));
                     tran.setTipCamb(new BigDecimal(ss.paridad));
                     tran.setBancoOriNal(ss.banco);
                     tran.setBancoDestNal(ss.banco);
                     tran.setCtaDest(ss.cuentaBanco);
                     tran.setCtaOri(ss.cuentaBanco);
                     
                     System.out.println("buscando origen ..........");
                      System.out.println(ss.origen);
                 
                     if(ss.origen.equalsIgnoreCase("CXP")){
                           erpSatTransaccionDao.actualizaTransaccionCXP(tran);
                     }
                     
                     if(ss.origen.equalsIgnoreCase("CXC")){
                           erpSatTransaccionDao.actualizaTransaccionCXC(tran);
                     }
                 
                 
                 }
             
                 
                 

            }
              
          //    System.out.println("buscando actualizar");
              
                Map pagoTotal = new HashMap();
                
               pagoTotal.put("compania", compania);
               pagoTotal.put("numero", numFE);
               
                   List listTotal = processDao.getMapResult("TotalesPagos", pagoTotal);
                   
                    Map pag = (HashMap) listTotal.get(0);
                    BigDecimal pago = new BigDecimal(pag.get("PAGO").toString());
                    BigDecimal total = new BigDecimal(pag.get("TOTAL").toString());
                    
               //     System.out.println("buscando actualizar"+pago);
               //     System.out.println("buscando actualizar"+total);
                     
                    ErpFeComprobantes comp = new ErpFeComprobantes();
                    ErpFeComprobantesId compId = new ErpFeComprobantesId();
                    
                    compId.setCompania(compania);
                    compId.setNumero(Integer.parseInt(numFE));
                    comp.setId(compId);
                    
                   if (total.compareTo(pago) == 1){
                       
                       erpFeComprobantesDao.actualizaPago("PAR", pago, comp);
                   
                   
                   }
                   
                    if (total.compareTo(pago) == 0){
                       
                       erpFeComprobantesDao.actualizaPago("PAG", pago, comp);
                   
                   
                   }
                    
                     if (total.compareTo(pago) == -1){
                       
                       erpFeComprobantesDao.actualizaPago("PAG", pago, comp);
                   
                   
                   }
                          
          
                     

         
            
        }catch(Exception e){
            rq.setSuccess(false);
            rq.setData(null);
            rq.setMsg("Error al guardar");
           return rq;
        }
        
        return rq;
    }
    
    
      @RequestMapping(value = "/updatePagoCXC", method = RequestMethod.POST)
    public @ResponseBody
    ResponseQuery updateSeguiPagosCXC( String data, WebRequest webRequest, Model model) throws IOException {

        ResponseQuery rq = new ResponseQuery();
    //    System.out.println("data Pagos:"+data);
        
         int index = data.indexOf("[");
        if (index == -1) {
            data = "[" + data + "]";
        }


            String compania = model.asMap().get("compania").toString();
            String usuario = model.asMap().get("usuario").toString();
            ObjectMapper mapper = new ObjectMapper();
            
            DateFormat df = new SimpleDateFormat("dd/MM/yyyy");

           
          
        try{
            
              List<PagosDTO> lista = mapper.readValue(data,
                    mapper.getTypeFactory().constructCollectionType(List.class,
                            PagosDTO.class));

            if (lista.isEmpty()) {
                rq.setSuccess(false);
                rq.setData(null);
                rq.setMsg("Error no existen datos que guardar");
                return rq;
            }
          
            Iterator<PagosDTO> it = lista.iterator();
            
            ErpSeguiDocumentos segui = new ErpSeguiDocumentos(); 
            ErpSeguiDocumentosId seguiId = new ErpSeguiDocumentosId(); 
            String numFE = "";
            
              while (it.hasNext()) {
          //       System.out.println("------------------------------PAGOS UPDATE-------------------------------");
                 PagosDTO ss = it.next();
                 numFE = ss.numFe;
         //        System.out.println("pagos"+ss.tOperacionDet);
                 
                 seguiId.setCompania(compania);
                 seguiId.setNumFe(Integer.parseInt(ss.numFe));
                 seguiId.setOrigen("C");
                 seguiId.setSerie("A");
                 seguiId.settDoc("FE");
                 //int sec = erpSeguiDocumentosDao.getMaxId(seguiId);
                 
                 seguiId.setSec(Integer.parseInt(ss.sec));
         //        System.out.println("llave guardada");
                 segui.setId(seguiId);
                 segui.setBanco(ss.banco);
                  if (ss.folio == null){
                     
                   segui.setFolio(Integer.parseInt(ss.numFe));
                     
                 }else{
                  segui.setFolio(Integer.parseInt(ss.folio));
                 }
          //       System.out.println("folio");
                 segui.setImporteOperacion(new BigDecimal(ss.importeOperacionDet));
          //       System.out.println("importe operacion");
                 //segui.setMoneda(ss.);
                 segui.setObservaciones(ss.observaciones);
                 segui.setParidad(new BigDecimal(ss.paridad));
         //        System.out.println("paridad");
                 segui.setTotPesos(new BigDecimal(ss.totPesos));
          //       System.out.println("ltotpesos");
                 segui.setUsuario(usuario);
                 segui.settOperacion(ss.tOperacionDet);
                 segui.setCuentaBanco(ss.cuentaBanco);
        //         System.out.println("fechaPago"+ss.fechaPago);
                 segui.setFechaPagoCxpFe(df.parse(ss.fechaPago));
                 segui.setBancoPago(ss.bancoPago);
                 segui.setCuentaPago(ss.cuentaPago);
                 segui.setMoneda(ss.monedaPago);
                 
                 if(ss.idTransaccion != null){
                     
                     segui.setIdTransaccion(Integer.parseInt(ss.idTransaccion));
                 
                 
                 }
                 
        //         System.out.println("fechaPago");
                 
        //         System.out.println("pagos enviados");
                 
                 boolean result = erpSeguiDocumentosDao.update(segui);
          //       System.out.println("buscando ..........");
            
                      rq.setSuccess(true);
                        rq.setData(null);
                        rq.setMsg("Pago Guerdado");
                      
                    System.out.println("buscando transacccion ..........");
                    
                    System.out.println(ss.idTransaccion);
                    if(ss.idTransaccion != null){
                 
                     ErpSatTransaccion tran = new ErpSatTransaccion();
                     ErpSatTransaccionId tranId = new ErpSatTransaccionId();
                     
                     tranId.setCompania(compania);
                     tranId.setId(new BigDecimal(ss.idTransaccion));
                     tran.setId(tranId);
                     tran.setFecha(df.parse(ss.fechaPago));
                     tran.setMontoTotal(new BigDecimal(ss.totPesos));
                     tran.setTipCamb(new BigDecimal(ss.paridad));
                     tran.setBancoOriNal(ss.banco);
                     tran.setBancoDestNal(ss.banco);
                     tran.setCtaDest(ss.cuentaBanco);
                     tran.setCtaOri(ss.cuentaBanco);
                     
                     System.out.println("buscando origen ..........");
                      System.out.println(ss.origen);
                 
                     if(ss.origen.equalsIgnoreCase("CXP")){
                           erpSatTransaccionDao.actualizaTransaccionCXP(tran);
                     }
                     
                     if(ss.origen.equalsIgnoreCase("CXC")){
                           erpSatTransaccionDao.actualizaTransaccionCXC(tran);
                     }
                 
                 
                 }
             
                 
                 

            }
              
          //    System.out.println("buscando actualizar");
              
                Map pagoTotal = new HashMap();
                
               pagoTotal.put("compania", compania);
               pagoTotal.put("numero", numFE);
               
                   List listTotal = processDao.getMapResult("TotalesPagos", pagoTotal);
                   
                    Map pag = (HashMap) listTotal.get(0);
                    BigDecimal pago = new BigDecimal(pag.get("PAGO").toString());
                    BigDecimal total = new BigDecimal(pag.get("TOTAL").toString());
                    
               //     System.out.println("buscando actualizar"+pago);
               //     System.out.println("buscando actualizar"+total);
                     
                    ErpFeComprobantes comp = new ErpFeComprobantes();
                    ErpFeComprobantesId compId = new ErpFeComprobantesId();
                    
                    compId.setCompania(compania);
                    compId.setNumero(Integer.parseInt(numFE));
                    comp.setId(compId);
                    
                   if (total.compareTo(pago) == 1){
                       
                       erpFeComprobantesDao.actualizaPago("PAR", pago, comp);
                   
                   
                   }
                   
                    if (total.compareTo(pago) == 0){
                       
                       erpFeComprobantesDao.actualizaPago("PAG", pago, comp);
                   
                   
                   }
                    
                     if (total.compareTo(pago) == -1){
                       
                       erpFeComprobantesDao.actualizaPago("PAG", pago, comp);
                   
                   
                   }
                          
          
                     

         
            
        }catch(Exception e){
            rq.setSuccess(false);
            rq.setData(null);
            rq.setMsg("Error al guardar");
           return rq;
        }
        
        return rq;
    }
    
    
    
          @RequestMapping(value = "/deletePagoOtras", method = RequestMethod.POST)
    public @ResponseBody
    ResponseQuery deleteSeguiPagosOtras( String data, WebRequest webRequest, Model model) throws IOException {

        ResponseQuery rq = new ResponseQuery();
     //   System.out.println("data Pagos:"+data);
        
         int index = data.indexOf("[");
        if (index == -1) {
            data = "[" + data + "]";
        }


            String compania = model.asMap().get("compania").toString();
            String usuario = model.asMap().get("usuario").toString();
            ObjectMapper mapper = new ObjectMapper();
            
            DateFormat df = new SimpleDateFormat("dd/MM/yyyy");

           
          
        try{
            
              List<PagosDTO> lista = mapper.readValue(data,
                    mapper.getTypeFactory().constructCollectionType(List.class,
                            PagosDTO.class));

            if (lista.isEmpty()) {
                rq.setSuccess(false);
                rq.setData(null);
                rq.setMsg("Error no existen datos que guardar");
                return rq;
            }
          
            Iterator<PagosDTO> it = lista.iterator();
            
            ErpSeguiDocumentos segui = new ErpSeguiDocumentos(); 
            ErpSeguiDocumentosId seguiId = new ErpSeguiDocumentosId(); 
            String numFE = "";
            String fechaPago = "";
            
              while (it.hasNext()) {
       //          System.out.println("------------------------------PAGOS DELETE-------------------------------");
                 PagosDTO ss = it.next();
                 numFE = ss.numFe;
      //           System.out.println("pagos"+ss.tOperacionDet);
                 
                 seguiId.setCompania(compania);
                 seguiId.setNumFe(Integer.parseInt(ss.numFe));
                 seguiId.setOrigen("P");
                 seguiId.setSerie("A");
                 seguiId.settDoc("OTR");
                 //int sec = erpSeguiDocumentosDao.getMaxId(seguiId);
                 
                 seguiId.setSec(Integer.parseInt(ss.sec));
         //        System.out.println("llave guardada");
                 segui.setId(seguiId);
                 
                 fechaPago = ss.fechaPago;
             
                 
                 boolean result = erpSeguiDocumentosDao.delete(segui);
           //      System.out.println("buscando ..........");
            
                      rq.setSuccess(true);
                        rq.setData(null);
                        rq.setMsg("Pago Borrado");
                      
                 
                 
             
                 
                 

            }
              
         //     System.out.println("buscando actualizar");
              
                Map pagoTotal = new HashMap();
                
               pagoTotal.put("compania", compania);
               pagoTotal.put("numero", numFE);
               
                   List listTotal = processDao.getMapResult("TotalesPagosOtras", pagoTotal);
               
                    
                     ErpCpOtras comp = new ErpCpOtras();
                    ErpCpOtrasId compId = new ErpCpOtrasId();
                    
                    compId.setCompania(compania);
                    compId.setId(Integer.parseInt(numFE));
                    comp.setId(compId);
                    
                    
                    
                   if (listTotal == null || listTotal.isEmpty()){
                       comp.setFechaPagoCxp(null);
                       comp.setPagoAcumuladoCXP(new BigDecimal(0));
                       comp.setEstatusCxp("");
                       erpCpOtrasDao.updateErpCpOtrasEstatusPagosOtras(comp);
                       
                       return rq;
                   
                   }
                   
          //         System.out.println("listTotal: "+listTotal);
                   
                    Map pag = (HashMap) listTotal.get(0);
                    BigDecimal pago = new BigDecimal(pag.get("PAGO").toString());
                    BigDecimal total = new BigDecimal(pag.get("TOTAL").toString());
                    
             //       System.out.println("buscando actualizar"+pago);
             //       System.out.println("buscando actualizar"+total);
                     
                   
                    
                   if (pago.compareTo(new BigDecimal(0)) == 0){
                       
                       comp.setFechaPagoCxp(null);
                       comp.setPagoAcumuladoCXP(new BigDecimal(0));
                       comp.setEstatusCxp("");
                        erpCpOtrasDao.updateErpCpOtrasEstatusPagosOtras(comp);
                   
                   }else{ 
                       
                       
                       
                        if (total.compareTo(pago) == 1){
                            
                             comp.setPagoAcumuladoCXP(pago);
                             comp.setEstatusCxp("PAR");
                             erpCpOtrasDao.updateErpCpOtrasEstatusPagosOtrasSF(comp);



                        }

                         if (total.compareTo(pago) == 0){

                             comp.setPagoAcumuladoCXP(pago);
                             comp.setEstatusCxp("PAG");
                             erpCpOtrasDao.updateErpCpOtrasEstatusPagosOtrasSF(comp);


                        }

                          if (total.compareTo(pago) == -1){

                            comp.setPagoAcumuladoCXP(pago);
                             comp.setEstatusCxp("PAG");
                             erpCpOtrasDao.updateErpCpOtrasEstatusPagosOtrasSF(comp);



                        }
                   }
                          
          
                     

         
            
        }catch(Exception e){
            rq.setSuccess(false);
            rq.setData(null);
            rq.setMsg("Error al borrar");
           return rq;
        }
        
        return rq;
    }
    
      @RequestMapping(value = "/deletePago", method = RequestMethod.POST)
    public @ResponseBody
    ResponseQuery deleteSeguiPagos( String data, WebRequest webRequest, Model model) throws IOException {

        ResponseQuery rq = new ResponseQuery();
     //   System.out.println("data Pagos:"+data);
        
         int index = data.indexOf("[");
        if (index == -1) {
            data = "[" + data + "]";
        }


            String compania = model.asMap().get("compania").toString();
            String usuario = model.asMap().get("usuario").toString();
            ObjectMapper mapper = new ObjectMapper();
            
            DateFormat df = new SimpleDateFormat("dd/MM/yyyy");

           
          
        try{
            
              List<PagosDTO> lista = mapper.readValue(data,
                    mapper.getTypeFactory().constructCollectionType(List.class,
                            PagosDTO.class));

            if (lista.isEmpty()) {
                rq.setSuccess(false);
                rq.setData(null);
                rq.setMsg("Error no existen datos que guardar");
                return rq;
            }
          
            Iterator<PagosDTO> it = lista.iterator();
            
            ErpSeguiDocumentos segui = new ErpSeguiDocumentos(); 
            ErpSeguiDocumentosId seguiId = new ErpSeguiDocumentosId(); 
            String numFE = "";
            
              while (it.hasNext()) {
       //          System.out.println("------------------------------PAGOS DELETE-------------------------------");
                 PagosDTO ss = it.next();
                 numFE = ss.numFe;
      //           System.out.println("pagos"+ss.tOperacionDet);
                 
                 seguiId.setCompania(compania);
                 seguiId.setNumFe(Integer.parseInt(ss.numFe));
                 seguiId.setOrigen("P");
                 seguiId.setSerie("A");
                 seguiId.settDoc("FE");
                 //int sec = erpSeguiDocumentosDao.getMaxId(seguiId);
                 
                 seguiId.setSec(Integer.parseInt(ss.sec));
         //        System.out.println("llave guardada");
                 segui.setId(seguiId);
             
                 
                 boolean result = erpSeguiDocumentosDao.delete(segui);
           //      System.out.println("buscando ..........");
            
                      rq.setSuccess(true);
                        rq.setData(null);
                        rq.setMsg("Pago Borrado");
                      
                 
                 
             
                 
                 

            }
              
         //     System.out.println("buscando actualizar");
              
                Map pagoTotal = new HashMap();
                
               pagoTotal.put("compania", compania);
               pagoTotal.put("numero", numFE);
               
                   List listTotal = processDao.getMapResult("TotalesPagos", pagoTotal);
                   
                    ErpFeComprobantes comp = new ErpFeComprobantes();
                    ErpFeComprobantesId compId = new ErpFeComprobantesId();
                    
                    compId.setCompania(compania);
                    compId.setNumero(Integer.parseInt(numFE));
                    comp.setId(compId);
                    
                   if (listTotal == null || listTotal.isEmpty()){
                       
                       erpFeComprobantesDao.actualizaPago("", new BigDecimal(0), comp);
                       
                       return rq;
                   
                   }
                   
          //         System.out.println("listTotal: "+listTotal);
                   
                    Map pag = (HashMap) listTotal.get(0);
                    BigDecimal pago = new BigDecimal(pag.get("PAGO").toString());
                    BigDecimal total = new BigDecimal(pag.get("TOTAL").toString());
                    
             //       System.out.println("buscando actualizar"+pago);
             //       System.out.println("buscando actualizar"+total);
                     
                   
                    
                   if (pago.compareTo(new BigDecimal(0)) == 0){
                       
                        erpFeComprobantesDao.actualizaPago("", pago, comp);
                   
                   }else{ 
                        if (total.compareTo(pago) == 1){

                            erpFeComprobantesDao.actualizaPago("PAR", pago, comp);


                        }

                         if (total.compareTo(pago) == 0){

                            erpFeComprobantesDao.actualizaPago("PAG", pago, comp);


                        }

                          if (total.compareTo(pago) == -1){

                            erpFeComprobantesDao.actualizaPago("PAG", pago, comp);


                        }
                   }
                          
          
                     

         
            
        }catch(Exception e){
            rq.setSuccess(false);
            rq.setData(null);
            rq.setMsg("Error al borrar");
           return rq;
        }
        
        return rq;
    }
    
          @RequestMapping(value = "/actualizaEstatus", method = RequestMethod.POST)
    public @ResponseBody
    ResponseQuery actualizaEstatus( String data, WebRequest webRequest, Model model) throws IOException {

        ResponseQuery rq = new ResponseQuery();
    

            String compania = model.asMap().get("compania").toString();
            String usuario = model.asMap().get("usuario").toString();
            ObjectMapper mapper = new ObjectMapper();
            
            DateFormat df = new SimpleDateFormat("dd/MM/yyyy");

           
          
        try{
            
             Querys que = new Querys();
            String store = que.getSQL("ACTUALIZA_PAGOS");
            
            Map param = new HashMap();
             
            
               param.put("compania", compania);
               
                int val = processDao.execute(store, param);

//                if (val <= 0) {
//                  
//                }
           
                    rq.setSuccess(true);
                    rq.setData(null);
                    rq.setMsg("");
           //          System.out.println("En actualiza pagos");

         
            
        }catch(Exception e){
            rq.setSuccess(false);
            rq.setData(null);
            rq.setMsg("");
           return rq;
        }
        
        return rq;
    }
    
      @RequestMapping(value = "/savePagoAll", method = RequestMethod.POST)
    public @ResponseBody
    ResponseQuery saveSeguiPagosAll( String data,@RequestParam("met_pago") String met_pago,
            @RequestParam("banco") String banco,
            @RequestParam("cuenta_banco") String cuenta_banco,
            @RequestParam("observaciones") String observaciones,
            @RequestParam("fechaPago") String fechaPago,
            @RequestParam("moneda") String moneda,
            @RequestParam("tipo_cambio") String tipo_cambio,
            @RequestParam("importeApli") String importeApli,
            @RequestParam("accion") String accion,
            @RequestParam("importePagoTotal") String importePagoTotal,
            WebRequest webRequest, Model model) throws IOException {

        ResponseQuery rq = new ResponseQuery();
     //   System.out.println("data Pagos:"+data);
        
//         System.out.println("data met_pago:"+met_pago);
//         System.out.println("data banco:"+banco);
//         System.out.println("data cuenta_banco:"+cuenta_banco);
//         System.out.println("data observaciones:"+observaciones);
//         System.out.println("data fechaPago:"+fechaPago);
//         System.out.println("data moneda:"+moneda);
//         System.out.println("data tipo_cambio:"+tipo_cambio);
//         System.out.println("data importeApli:"+importeApli);
//         System.out.println("data accion:"+accion);
        
         int index = data.indexOf("[");
        if (index == -1) {
            data = "[" + data + "]";
        }


            String compania = model.asMap().get("compania").toString();
            String usuario = model.asMap().get("usuario").toString();
            ObjectMapper mapper = new ObjectMapper();
            
            DateFormat df = new SimpleDateFormat("dd/MM/yyyy");

          
        try{
            
              List<PagosAllDTO> lista = mapper.readValue(data,
                    mapper.getTypeFactory().constructCollectionType(List.class,
                            PagosAllDTO.class));

            if (lista.isEmpty()) {
                rq.setSuccess(false);
                rq.setData(null);
                rq.setMsg("Error no existen datos que guardar");
                return rq;
            }
          
            Iterator<PagosAllDTO> it = lista.iterator();
            
            ErpSeguiDocumentos segui = new ErpSeguiDocumentos(); 
            ErpSeguiDocumentosId seguiId = new ErpSeguiDocumentosId(); 
            
              while (it.hasNext()) {
             //    System.out.println("------------------------------PAGOS TOTALES-------------------------------");
                 PagosAllDTO ss = it.next();
               // System.out.println("ss.numero"+ss.numero);
                 seguiId.setCompania(compania);
                 seguiId.setNumFe(Integer.parseInt(ss.numero));
                 seguiId.setOrigen("P");
                 seguiId.setSerie("A");
                 seguiId.settDoc("FE");
                 int sec = erpSeguiDocumentosDao.getMaxId(seguiId);
           //      System.out.println("pagos sec "+sec);
                 seguiId.setSec(sec);
                 
                 segui.setId(seguiId);
                // System.out.println("1");
                 segui.setBanco(banco);
                // System.out.println("2");
                // if (ss.folio == null){
                     
                   segui.setFolio(Integer.parseInt(ss.numero));
                     
                // }else{
                //  segui.setFolio(Integer.parseInt(ss.folio));
                // }
                // System.out.println("3");
                 if (accion.equalsIgnoreCase("T")){
                   segui.setImporteOperacion(new BigDecimal(ss.total));
                 }
                 if (accion.equalsIgnoreCase("P")){
                   segui.setImporteOperacion(new BigDecimal(importeApli));
                 }
                // System.out.println("4");
                 segui.setObservaciones(observaciones);
                // System.out.println("5");
                 segui.setParidad(new BigDecimal(tipo_cambio));
                // System.out.println("6");
                 if (accion.equalsIgnoreCase("T")){
                   segui.setTotPesos(new BigDecimal(ss.total).multiply(new BigDecimal(tipo_cambio)));
                 }
                 if (accion.equalsIgnoreCase("P")){
                   segui.setTotPesos(new BigDecimal(importeApli).multiply(new BigDecimal(tipo_cambio)));
                 }
                 
                 if (importePagoTotal.equalsIgnoreCase("")){
                       segui.setImporteTotalPago(new BigDecimal(0));
                 }else{
                     
                     segui.setImporteTotalPago(new BigDecimal(importePagoTotal));
                 
                 }
                 
                // System.out.println("7");
                 segui.setUsuario(usuario);
                // System.out.println("8");
                 segui.settOperacion(met_pago);
                // System.out.println("9");
                 segui.setCuentaBanco(cuenta_banco);
                 //System.out.println("10");
                 segui.setFechaPagoCxpFe(df.parse(fechaPago));
                 segui.setBancoPago(banco);
                 segui.setCuentaBanco(cuenta_banco);
                 segui.setCuentaPago(cuenta_banco);
                 segui.setMoneda(moneda);
                 //System.out.println("11");
                 
                 ErpSeguiDocumentosId result = erpSeguiDocumentosDao.save(segui);
                
                      rq.setSuccess(true);
                        rq.setData(null);
                        rq.setMsg("Pago Guardado");
                   //   System.out.println("12");
                  ErpFeComprobantes comp = new ErpFeComprobantes();
                    ErpFeComprobantesId compId = new ErpFeComprobantesId();
                    
                    compId.setCompania(compania);
                    compId.setNumero(Integer.parseInt(ss.numero));
                    comp.setId(compId);
                 
                 if (accion.equalsIgnoreCase("T")){
                     erpFeComprobantesDao.actualizaPago("PAG", new BigDecimal(ss.total), comp);
                 }
                 if (accion.equalsIgnoreCase("P")){
                     if (new BigDecimal(importeApli).compareTo(new BigDecimal(ss.total)) == 0){
                         
                          erpFeComprobantesDao.actualizaPago("PAG", new BigDecimal(importeApli), comp);
                     
                     }
                     if (new BigDecimal(importeApli).compareTo(new BigDecimal(ss.total)) == -1){
                         
                          erpFeComprobantesDao.actualizaPago("PAR", new BigDecimal(importeApli), comp);
                     
                     }
                     if (new BigDecimal(importeApli).compareTo(new BigDecimal(ss.total)) == 1){
                         
                          erpFeComprobantesDao.actualizaPago("PAG", new BigDecimal(importeApli), comp);
                     
                     }
                    
                 }
                 

            }
             
         
            
        }catch(Exception e){
            rq.setSuccess(false);
            rq.setData(null);
            e.printStackTrace();
            rq.setMsg("Error al guardar");
           return rq;
        }
        
        return rq;
    }
    
    
      @RequestMapping(value = "/savePagoAllCXC", method = RequestMethod.POST)
    public @ResponseBody
    ResponseQuery saveSeguiPagosAllCXC( String data,@RequestParam("met_pago") String met_pago,
            @RequestParam("banco") String banco,
            @RequestParam("cuenta_banco") String cuenta_banco,
            @RequestParam("observaciones") String observaciones,
            @RequestParam("fechaPago") String fechaPago,
            @RequestParam("moneda") String moneda,
            @RequestParam("tipo_cambio") String tipo_cambio,
            @RequestParam("importeApli") String importeApli,
            @RequestParam("accion") String accion,
            @RequestParam("importePagoTotal") String importePagoTotal,
            WebRequest webRequest, Model model) throws IOException {

        ResponseQuery rq = new ResponseQuery();
     //   System.out.println("data Pagos:"+data);
        
//         System.out.println("data met_pago:"+met_pago);
//         System.out.println("data banco:"+banco);
//         System.out.println("data cuenta_banco:"+cuenta_banco);
//         System.out.println("data observaciones:"+observaciones);
//         System.out.println("data fechaPago:"+fechaPago);
//         System.out.println("data moneda:"+moneda);
//         System.out.println("data tipo_cambio:"+tipo_cambio);
//         System.out.println("data importeApli:"+importeApli);
//         System.out.println("data accion:"+accion);
        
         int index = data.indexOf("[");
        if (index == -1) {
            data = "[" + data + "]";
        }


            String compania = model.asMap().get("compania").toString();
            String usuario = model.asMap().get("usuario").toString();
            ObjectMapper mapper = new ObjectMapper();
            
            DateFormat df = new SimpleDateFormat("dd/MM/yyyy");

          
        try{
            
              List<PagosAllDTO> lista = mapper.readValue(data,
                    mapper.getTypeFactory().constructCollectionType(List.class,
                            PagosAllDTO.class));

            if (lista.isEmpty()) {
                rq.setSuccess(false);
                rq.setData(null);
                rq.setMsg("Error no existen datos que guardar");
                return rq;
            }
          
            Iterator<PagosAllDTO> it = lista.iterator();
            
            ErpSeguiDocumentos segui = new ErpSeguiDocumentos(); 
            ErpSeguiDocumentosId seguiId = new ErpSeguiDocumentosId(); 
            
              while (it.hasNext()) {
             //    System.out.println("------------------------------PAGOS TOTALES-------------------------------");
                 PagosAllDTO ss = it.next();
               // System.out.println("ss.numero"+ss.numero);
                 seguiId.setCompania(compania);
                 seguiId.setNumFe(Integer.parseInt(ss.numero));
                 seguiId.setOrigen("C");
                 seguiId.setSerie("A");
                 seguiId.settDoc("FE");
                 int sec = erpSeguiDocumentosDao.getMaxId(seguiId);
           //      System.out.println("pagos sec "+sec);
                 seguiId.setSec(sec);
                 
                 segui.setId(seguiId);
                // System.out.println("1");
                 segui.setBanco(banco);
                // System.out.println("2");
                // if (ss.folio == null){
                     
                   segui.setFolio(Integer.parseInt(ss.numero));
                     
                // }else{
                //  segui.setFolio(Integer.parseInt(ss.folio));
                // }
                // System.out.println("3");
                 if (accion.equalsIgnoreCase("T")){
                   segui.setImporteOperacion(new BigDecimal(ss.total));
                 }
                 if (accion.equalsIgnoreCase("P")){
                   segui.setImporteOperacion(new BigDecimal(importeApli));
                 }
                // System.out.println("4");
                 segui.setObservaciones(observaciones);
                // System.out.println("5");
                 segui.setParidad(new BigDecimal(tipo_cambio));
                // System.out.println("6");
                 if (accion.equalsIgnoreCase("T")){
                   segui.setTotPesos(new BigDecimal(ss.total).multiply(new BigDecimal(tipo_cambio)));
                 }
                 if (accion.equalsIgnoreCase("P")){
                   segui.setTotPesos(new BigDecimal(importeApli).multiply(new BigDecimal(tipo_cambio)));
                 }
                 
                 if (importePagoTotal.equalsIgnoreCase("")){
                       segui.setImporteTotalPago(new BigDecimal(0));
                 }else{
                     
                     segui.setImporteTotalPago(new BigDecimal(importePagoTotal));
                 
                 }
                 
                // System.out.println("7");
                 segui.setUsuario(usuario);
                // System.out.println("8");
                 segui.settOperacion(met_pago);
                // System.out.println("9");
                 segui.setCuentaBanco(cuenta_banco);
                 //System.out.println("10");
                 segui.setFechaPagoCxpFe(df.parse(fechaPago));
                 segui.setBancoPago(banco);
                 segui.setCuentaBanco(cuenta_banco);
                 segui.setCuentaPago(cuenta_banco);
                 segui.setMoneda(moneda);
                 //System.out.println("11");
                 
                 ErpSeguiDocumentosId result = erpSeguiDocumentosDao.save(segui);
                
                      rq.setSuccess(true);
                        rq.setData(null);
                        rq.setMsg("Pago Guardado");
                   //   System.out.println("12");
                  ErpFeComprobantes comp = new ErpFeComprobantes();
                    ErpFeComprobantesId compId = new ErpFeComprobantesId();
                    
                    compId.setCompania(compania);
                    compId.setNumero(Integer.parseInt(ss.numero));
                    comp.setId(compId);
                 
                 if (accion.equalsIgnoreCase("T")){
                     erpFeComprobantesDao.actualizaPago("PAG", new BigDecimal(ss.total), comp);
                 }
                 if (accion.equalsIgnoreCase("P")){
                     if (new BigDecimal(importeApli).compareTo(new BigDecimal(ss.total)) == 0){
                         
                          erpFeComprobantesDao.actualizaPago("PAG", new BigDecimal(importeApli), comp);
                     
                     }
                     if (new BigDecimal(importeApli).compareTo(new BigDecimal(ss.total)) == -1){
                         
                          erpFeComprobantesDao.actualizaPago("PAR", new BigDecimal(importeApli), comp);
                     
                     }
                     if (new BigDecimal(importeApli).compareTo(new BigDecimal(ss.total)) == 1){
                         
                          erpFeComprobantesDao.actualizaPago("PAG", new BigDecimal(importeApli), comp);
                     
                     }
                    
                 }
                 

            }
             
         
            
        }catch(Exception e){
            rq.setSuccess(false);
            rq.setData(null);
            e.printStackTrace();
            rq.setMsg("Error al guardar");
           return rq;
        }
        
        return rq;
    }
    
    
    
    @RequestMapping(value = "/cancelPagoAll", method = RequestMethod.POST)
    public @ResponseBody
    ResponseQuery cancelPagoAll( String data,
            WebRequest webRequest, Model model) throws IOException {

        ResponseQuery rq = new ResponseQuery();
      //  System.out.println("data Pagos:"+data);
       
        
         int index = data.indexOf("[");
        if (index == -1) {
            data = "[" + data + "]";
        }


            String compania = model.asMap().get("compania").toString();
            String usuario = model.asMap().get("usuario").toString();
            ObjectMapper mapper = new ObjectMapper();
            
            DateFormat df = new SimpleDateFormat("dd/MM/yyyy");

          
        try{
            
              List<PagosAllDTO> lista = mapper.readValue(data,
                    mapper.getTypeFactory().constructCollectionType(List.class,
                            PagosAllDTO.class));

            if (lista.isEmpty()) {
                rq.setSuccess(false);
                rq.setData(null);
                rq.setMsg("Error no existen datos que guardar");
                return rq;
            }
          
            Iterator<PagosAllDTO> it = lista.iterator();
            
            ErpSeguiDocumentos segui = new ErpSeguiDocumentos(); 
            ErpSeguiDocumentosId seguiId = new ErpSeguiDocumentosId(); 
            
              while (it.hasNext()) {
              //   System.out.println("--------------------------CANCELA PAGOS TOTALES-------------------------------");
                 PagosAllDTO ss = it.next();
             
                 seguiId.setCompania(compania);
                 seguiId.setNumFe(Integer.parseInt(ss.numero));
                 seguiId.setOrigen("P");
                 seguiId.setSerie("A");
                 seguiId.settDoc("FE");
                 
                
                 
                 
                 boolean result = erpSeguiDocumentosDao.borraDetallePagos(seguiId);
                
                        rq.setSuccess(true);
                        rq.setData(null);
                        rq.setMsg("Pago Eliminado");
                   //   System.out.println("12");
                  ErpFeComprobantes comp = new ErpFeComprobantes();
                    ErpFeComprobantesId compId = new ErpFeComprobantesId();
                    
                    compId.setCompania(compania);
                    compId.setNumero(Integer.parseInt(ss.numero));
                    comp.setId(compId);
                 
                
                          erpFeComprobantesDao.actualizaPago("", new BigDecimal(0), comp);
                     
                     
                    
                 
                 

            }
             
         
            
        }catch(Exception e){
            rq.setSuccess(false);
            rq.setData(null);
            rq.setMsg("Error al eliminar pago");
           return rq;
        }
        
        return rq;
    }
    
          @RequestMapping(value = "/dataTesoreria/{query}", method = RequestMethod.POST)
    public @ResponseBody
    ResponseQuery queryActionPost(@PathVariable String query,
             @RequestParam(value = "limit") int limit,
                         @RequestParam(value = "page") int page,
                         //@RequestParam(value = "query") String query,
                         @RequestParam(value = "start") int start,
            WebRequest webRequest, Model model) {
        ResponseQuery rq = new ResponseQuery();
        
     //   System.out.println("tesoreria");
     //   System.out.println(webRequest.getParameter("jsonData"));
        
        int to = limit * page;
        int from = to - limit;
       
        Map parametros = processDao.paramToMap(webRequest.getParameterMap());
        
        
        String data = webRequest.getParameter("jsonData");
        
           int index = data.indexOf("[");
        if (index == -1) {
            data = "[" + data + "]";
        }


            
            ObjectMapper mapper = new ObjectMapper();
          
        try{
            List<CxpDTO> lista = mapper.readValue(data,
                    mapper.getTypeFactory().constructCollectionType(List.class,
                            CxpDTO.class));

            if (lista.isEmpty()) {
                rq.setSuccess(false);
                rq.setData(null);
                rq.setMsg("Error no existen datos que guardar");
                return rq;
            }

  
            Iterator<CxpDTO> it = lista.iterator();
      
            List listT = new ArrayList();
          
            int total = 0;
            
            while (it.hasNext()) {
           //      System.out.println("-------------------------------------------------------------------");
                 CxpDTO ss = it.next();
                 
                 
           //      System.out.println("parametro: "+ ss.folio);
                 
                 parametros.put("compania", ss.compania);
                 parametros.put("numero",ss.numero);
                 
                 List list = processDao.getMapResult(query, parametros);
                 
                
                 
          //       System.out.println(list.get(0));
                 
                 listT.add(list.get(0));
                 
                 total = total + 1;
            }
            
       //     System.out.println("list finally");
       //     System.out.println(listT);
            
            int paginas;
            
            if (total > limit){
            
              paginas = (int) ((total/limit)+1);
            }else{
            
              paginas = 1;
            }
            
            if (listT.isEmpty() || listT == null){
                
                rq.setSuccess(false);
                rq.setData(null);
                rq.setMsg("Ready");
                
            
            
            }else{
            
                rq.setSuccess(true);
                rq.setData(listT);
                rq.setMsg("Ready");
                rq.setTotal(new BigDecimal(paginas));
            
            }
            
            
            
        }catch (Exception e){
        
             rq.setSuccess(true);
            rq.setData(null);
            rq.setMsg("Ready");
        }
        
       // parametros.put("compania", model.asMap().get("compania"));
       // parametros.put("usuario", model.asMap().get("usuario"));

       // System.out.println("compania"+parametros.put("compania", model.asMap().get("compania")));
       // System.out.println("query"+query);
       
       // List list = processDao.getMapResult(query, parametros);

//        if (list == null  || list.size()==0) {
//            rq.setSuccess(false);
//            rq.setData(null);
//            rq.setMsg("data null");
//        } else {

//            rq.setSuccess(true);
//            rq.setData(null);
//            rq.setMsg("Ready");
       // }

        return rq;
    }
    
            @RequestMapping(value = "/dataTesoreriaOtros/{query}", method = RequestMethod.POST)
    public @ResponseBody
    ResponseQuery dataTesoreriaOtros(@PathVariable String query,
             @RequestParam(value = "limit") int limit,
                         @RequestParam(value = "page") int page,
                         //@RequestParam(value = "query") String query,
                         @RequestParam(value = "start") int start,
            WebRequest webRequest, Model model) {
        ResponseQuery rq = new ResponseQuery();
        
     //   System.out.println("tesoreria");
     //   System.out.println(webRequest.getParameter("jsonData"));
        
        int to = limit * page;
        int from = to - limit;
       
        Map parametros = processDao.paramToMap(webRequest.getParameterMap());
        
        
        String data = webRequest.getParameter("jsonData");
        
           int index = data.indexOf("[");
        if (index == -1) {
            data = "[" + data + "]";
        }


            
            ObjectMapper mapper = new ObjectMapper();
          
        try{
            List<CxpOtrasDTO> lista = mapper.readValue(data,
                    mapper.getTypeFactory().constructCollectionType(List.class,
                            CxpOtrasDTO.class));

            if (lista.isEmpty()) {
                rq.setSuccess(false);
                rq.setData(null);
                rq.setMsg("Error no existen datos que guardar");
                return rq;
            }

  
            Iterator<CxpOtrasDTO> it = lista.iterator();
      
            List listT = new ArrayList();
          
            int total = 0;
            
            while (it.hasNext()) {
           //      System.out.println("-------------------------------------------------------------------");
                 CxpOtrasDTO ss = it.next();
                 
                 
           //      System.out.println("parametro: "+ ss.folio);
                 
                 parametros.put("compania", ss.compania);
                 parametros.put("numero",ss.id);
                 
                 List list = processDao.getMapResult(query, parametros);
                 
                
                 
          //       System.out.println(list.get(0));
                 
                 listT.add(list.get(0));
                 
                 total = total + 1;
            }
            
       //     System.out.println("list finally");
       //     System.out.println(listT);
            
            int paginas;
            
            if (total > limit){
            
              paginas = (int) ((total/limit)+1);
            }else{
            
              paginas = 1;
            }
            
            if (listT.isEmpty() || listT == null){
                
                rq.setSuccess(false);
                rq.setData(null);
                rq.setMsg("Ready");
                
            
            
            }else{
            
                rq.setSuccess(true);
                rq.setData(listT);
                rq.setMsg("Ready");
                rq.setTotal(new BigDecimal(paginas));
            
            }
            
            
            
        }catch (Exception e){
        
             rq.setSuccess(true);
            rq.setData(null);
            rq.setMsg("Ready");
        }
        
       // parametros.put("compania", model.asMap().get("compania"));
       // parametros.put("usuario", model.asMap().get("usuario"));

       // System.out.println("compania"+parametros.put("compania", model.asMap().get("compania")));
       // System.out.println("query"+query);
       
       // List list = processDao.getMapResult(query, parametros);

//        if (list == null  || list.size()==0) {
//            rq.setSuccess(false);
//            rq.setData(null);
//            rq.setMsg("data null");
//        } else {

//            rq.setSuccess(true);
//            rq.setData(null);
//            rq.setMsg("Ready");
       // }

        return rq;
    }
    
    
     @RequestMapping(value = "/dataRembolso/{query}", method = RequestMethod.POST)
    public @ResponseBody
    ResponseQuery queryActionRembolso(@PathVariable String query,
             @RequestParam(value = "limit") int limit,
                         @RequestParam(value = "page") int page,
                         //@RequestParam(value = "query") String query,
                         @RequestParam(value = "start") int start,
            WebRequest webRequest, Model model) {
        ResponseQuery rq = new ResponseQuery();
        
     //   System.out.println("rembolso");
     //   System.out.println(webRequest.getParameter("jsonData"));
        
        int to = limit * page;
        int from = to - limit;
       
        Map parametros = processDao.paramToMap(webRequest.getParameterMap());
        
        
        String data = webRequest.getParameter("jsonData");
        
           int index = data.indexOf("[");
        if (index == -1) {
            data = "[" + data + "]";
        }


            
            ObjectMapper mapper = new ObjectMapper();
          
        try{
            List<CxpDTO> lista = mapper.readValue(data,
                    mapper.getTypeFactory().constructCollectionType(List.class,
                            CxpDTO.class));

            if (lista.isEmpty()) {
                rq.setSuccess(false);
                rq.setData(null);
                rq.setMsg("Error no existen datos que guardar");
                return rq;
            }

  
            Iterator<CxpDTO> it = lista.iterator();
      
            List listT = new ArrayList();
          
            int total = 0;
            
            while (it.hasNext()) {
         //        System.out.println("-------------------------------------------------------------------");
                 CxpDTO ss = it.next();
                 
                 
          //       System.out.println("parametro: "+ ss.folio);
                 
                 parametros.put("compania", ss.compania);
                 parametros.put("numero",ss.numero);
                 
                 List list = processDao.getMapResult(query, parametros);
                 
                
                 
              //   System.out.println(list.get(0));
                 
                 listT.add(list.get(0));
                 
                 total = total + 1;
            }
            
          //  System.out.println("list finally");
          //  System.out.println(listT);
            
            int paginas;
            
            if (total > limit){
            
              paginas = (int) ((total/limit)+1);
            }else{
            
              paginas = 1;
            }
            
            if (listT.isEmpty() || listT == null){
                
                rq.setSuccess(false);
                rq.setData(null);
                rq.setMsg("Ready");
                
            
            
            }else{
            
                rq.setSuccess(true);
                rq.setData(listT);
                rq.setMsg("Ready");
                rq.setTotal(new BigDecimal(paginas));
            
            }
            
            
            
        }catch (Exception e){
        
             rq.setSuccess(true);
            rq.setData(null);
            rq.setMsg("Ready");
        }
        
     
        return rq;
    }

     @RequestMapping(value = "/saveRembolso", method = RequestMethod.POST)
    public @ResponseBody
    ResponseQuery saveRembolso( String data, WebRequest webRequest, Model model) throws IOException {

        ResponseQuery rq = new ResponseQuery();
      //  System.out.println("data rembolso creacion:"+data);
        
         int index = data.indexOf("[");
        if (index == -1) {
            data = "[" + data + "]";
        }


            String compania = model.asMap().get("compania").toString();
            String usuario = model.asMap().get("usuario").toString();
            ObjectMapper mapper = new ObjectMapper();
          
        try{
            List<RembolsoDTO> lista = mapper.readValue(data,
                    mapper.getTypeFactory().constructCollectionType(List.class,
                            RembolsoDTO.class));

            if (lista.isEmpty()) {
                rq.setSuccess(false);
                rq.setData(null);
                rq.setMsg("Error no existen datos que guardar");
                return rq;
            }

  
            Iterator<RembolsoDTO> it = lista.iterator();
           ErpCxpFolios folios = new ErpCxpFolios();
           ErpCxpFoliosId foliosId = new ErpCxpFoliosId();
           
           ErpFeComprobantes comprobantes = new ErpFeComprobantes();
           ErpFeComprobantesId comprobantesId = new ErpFeComprobantesId();
           
           foliosId.setCompania(compania);
           
           int geFolio = erpCxpFoliosDao.getMaxCxpFolios(foliosId);
           
           int banderaGe = 0;
           
        //   System.out.println("GENRADOR DE FOLIO:"+geFolio);
           
           int folioPagos = geFolio;
          
            while (it.hasNext()) {
//                 System.out.println("-------------------------------------------------------------------");
                 RembolsoDTO ss = it.next();
               //  if (ss.estatusCxp == null){
                 int id = erpCxpFoliosDao.getMaxIdCxpFolios(foliosId);
                 
                // folioPagos = id;
                 
                 foliosId.setId(id);
                 folios.setId(foliosId);
                 folios.setFolio(geFolio);
                 folios.setOperacion("EA");
                 folios.setUsuario(usuario);
                 folios.setNumeroFe(Integer.parseInt(ss.numeroRem));
                
                 
                 ErpCxpFoliosId result = erpCxpFoliosDao.save(folios);
                 
                 comprobantesId.setCompania(compania);
                 comprobantesId.setNumero(Integer.parseInt(ss.numeroRem));
                 comprobantes.setId(comprobantesId);
                 comprobantes.setEstatusCxp("REMB");
                 comprobantes.setFolioPagos(geFolio);
                 comprobantes.setReembolsoClieprov(ss.empleadoRem);
                 

                 
                 boolean result2 = false;
                 
                
                     
               //      System.out.println("actualiza reembolso");
                 
                     result2 = erpFeComprobantesDao.actualizaEstatusFolioPagosReembolso(comprobantes, ss.monedaRem);
                 
                
              //   System.out.println("result2"+result2);
                 
                 banderaGe = 1;
  

            }
            
            ErpPolizasVSFacturasAutErrId result=null;
            
            if (folioPagos != 0){
            
                ErpPolizasVSFacturasAutErr correo = new ErpPolizasVSFacturasAutErr();

                ErpPolizasVSFacturasAutErrId correoId = new ErpPolizasVSFacturasAutErrId();

                correoId.setCompania(compania);
                correoId.setFolio(String.valueOf(folioPagos));
                correoId.setTipo("5");
                
                correo.setId(correoId);
                correo.setEnvio("0");
                correo.setDescripcion("Se envio Folio: "+folioPagos+ " para autorización");
                correo.setFechaCap(new Date());
                correo.setNombre("Folio para autorización");
               
                
                result = erpPolizasVSFacturasAutErrDao.save(correo);
            }
            
            
            
            if (banderaGe == 1){
            
                if (result == null){
                    rq.setSuccess(true);
                    rq.setData(null);
                    rq.setMsg("Enviado a pagar con folio: "+geFolio+", error al enviar correo");
                }else{
                
                    rq.setSuccess(true);
                    rq.setData(null);
                    rq.setMsg("Enviado a pagar con folio: "+geFolio+", se ha enviado correo a Tesoreria");
                
                }
            }else{
                
                rq.setSuccess(true);
                rq.setData(null);
                rq.setMsg("No hay registros para enviar");
                
            }
            
        }catch(Exception e){
            rq.setSuccess(false);
            rq.setData(null);
            rq.setMsg("Error al guardar");
           return rq;
        }
        
        return rq;
    }
    
    
            
    @RequestMapping(value = "/enviaTesoreriaNom", method = RequestMethod.POST)
    public @ResponseBody
    ResponseQuery enviaTesoreriaNom( String folio,String aut,String text,String compania2, WebRequest webRequest, Model model) throws IOException {

        ResponseQuery rq = new ResponseQuery();
       
        String compania = model.asMap().get("compania").toString();
            String usuario = model.asMap().get("usuario").toString();
       
      
     
      


           
            ObjectMapper mapper = new ObjectMapper();
            
        try{
            
            int valP = 0;
            
            Querys que = new Querys();
               String store = que.getSQL("ActualizaAutEstatusNom");
               
            Querys que2 = new Querys();
               String store2 = que2.getSQL("ActualizaAutEstatusCanNom");
               
            Querys que3 = new Querys();
               String store3 = que3.getSQL("ActualizaAutEstatusCanDetNom");
               
               
                   Map parametros = new HashMap();
                   
                    parametros.put("compania2", compania2);
                    parametros.put("estatus",aut);
                    parametros.put("folio",folio);
                    parametros.put("motCan",text);
                    
                    String urlS = "";

           
             
 //  P_SID              VARCHAR2,

            if (aut.equalsIgnoreCase("1")){ 
               



               valP = processDao.execute(store, parametros);
               
               // urlS = "http://appferaz1.com/CorreosAvisos/envioTesorNomAut.jsp?compania="+compania+"&folio="+folio;
            
                   
 
            }
            
            if (aut.equalsIgnoreCase("0")){ 
               



               valP = processDao.execute(store2, parametros);
               processDao.execute(store3, parametros);
                   
               // urlS = "http://appferaz1.com/CorreosAvisos/envioTesorNomCan.jsp?compania="+compania+"&folio="+folio;

            }
            
            
//            URL url = null;
//                try {
//                    url = new URL(urlS);
//                } catch (MalformedURLException ex) {
//                }
//              URLConnection con = null;
//                try {
//                    con = url.openConnection();
//                } catch (IOException ex) {
//                }
//
//              BufferedReader in = null;
//                try {
//                    in = new BufferedReader(
//                            new InputStreamReader(con.getInputStream()));
//                } catch (IOException ex) {
//                }
//
//              String linea;
//                try {
//                    while ((linea = in.readLine()) != null) {
//                        System.out.println(linea);
//                    } } catch (IOException ex) {
//                }
//            
            
            
       
                if (valP == 1){
                    rq.setSuccess(true);
                    rq.setData(null);
                    rq.setMsg("Folio Actualizado Correctamente");
                }else{
                
                    rq.setSuccess(true);
                    rq.setData(null);
                    rq.setMsg("Error al actualizar el folio");
                
                }
    

        }catch(Exception e){
            
            e.printStackTrace();
            
            rq.setSuccess(false);
            rq.setData(null);
            rq.setMsg("Error al guardar");
           return rq;
        }
        
        return rq;
        
        
    }        
    
        @RequestMapping(value = "/enviaTesoreriaNomCXP", method = RequestMethod.POST)
    public @ResponseBody
    ResponseQuery enviaTesoreriaNomCXP( String folio,String aut,String text, String compania2,WebRequest webRequest, Model model) throws IOException {

        ResponseQuery rq = new ResponseQuery();
       
        String compania = model.asMap().get("compania").toString();
            String usuario = model.asMap().get("usuario").toString();
       
      
     
      


           
            ObjectMapper mapper = new ObjectMapper();
            
        try{
            
            int valP = 0;
            
            Querys que = new Querys();
               String store = que.getSQL("ActualizaAutEstatusNomCXP");
               
            Querys que2 = new Querys();
               String store2 = que2.getSQL("ActualizaAutEstatusCanNomCXP");
               
            Querys que3 = new Querys();
               String store3 = que3.getSQL("ActualizaAutEstatusCanDetNomCXP");
               
               
                   Map parametros = new HashMap();
                   
                    parametros.put("compania2", compania2);
                    parametros.put("estatus",aut);
                    parametros.put("folio",folio);
                    parametros.put("motCan",text);
                    
                    String urlS = "";

           
             
 //  P_SID              VARCHAR2,

            if (aut.equalsIgnoreCase("1")){ 
               



               valP = processDao.execute(store, parametros);
               
               // urlS = "http://appferaz1.com/CorreosAvisos/envioTesorNomAut.jsp?compania="+compania+"&folio="+folio;
            
                   
 
            }
            
            if (aut.equalsIgnoreCase("0")){ 
               



               valP = processDao.execute(store2, parametros);
               processDao.execute(store3, parametros);
                   
               // urlS = "http://appferaz1.com/CorreosAvisos/envioTesorNomCan.jsp?compania="+compania+"&folio="+folio;

            }
            
            
//            URL url = null;
//                try {
//                    url = new URL(urlS);
//                } catch (MalformedURLException ex) {
//                }
//              URLConnection con = null;
//                try {
//                    con = url.openConnection();
//                } catch (IOException ex) {
//                }
//
//              BufferedReader in = null;
//                try {
//                    in = new BufferedReader(
//                            new InputStreamReader(con.getInputStream()));
//                } catch (IOException ex) {
//                }
//
//              String linea;
//                try {
//                    while ((linea = in.readLine()) != null) {
//                        System.out.println(linea);
//                    } } catch (IOException ex) {
//                }
//            
            
            
       
                if (valP == 1){
                    rq.setSuccess(true);
                    rq.setData(null);
                    rq.setMsg("Folio Actualizado Correctamente");
                }else{
                
                    rq.setSuccess(true);
                    rq.setData(null);
                    rq.setMsg("Error al actualizar el folio");
                
                }
    

        }catch(Exception e){
            
            e.printStackTrace();
            
            rq.setSuccess(false);
            rq.setData(null);
            rq.setMsg("Error al guardar");
           return rq;
        }
        
        return rq;
        
        
    }  
    
            @RequestMapping(value = "/imprimeFoliosNom", method = RequestMethod.POST)
    public @ResponseBody
    ResponseQuery imprimeFoliosNom( String folio, String compania2,WebRequest webRequest, Model model) throws IOException {

        ResponseQuery rq = new ResponseQuery();
       
        String compania = model.asMap().get("compania").toString();
            String usuario = model.asMap().get("usuario").toString();
       
      
     
      


           
            ObjectMapper mapper = new ObjectMapper();
            
            
            
        try{
            
            
            String query = "BuscaRutaFol";
            
            Map parametros = processDao.paramToMap(webRequest.getParameterMap());
                 parametros.put("compania", compania2);
                 parametros.put("folio",folio);
                 
                 List list = processDao.getMapResult(query, parametros);
            
            
  
                if(list == null || list.size() == 0){


                       rq.setSuccess(false);
                       rq.setData(null);
                       rq.setMsg("Error, folio no encontrado");
                       return rq;




                }else{

                  Map t = (HashMap) list.get(0);

                      System.out.println("url archivo: " +t.get("URL_ARCHIVO").toString());
                      
                       int valP = 0;
            
                    Querys que = new Querys();
                       String store = que.getSQL("ActualizaEstatusNom");
               

                        valP = processDao.execute(store, parametros);
                      
                      rq.setSuccess(true);
                        rq.setData(null);
                        rq.setMsg(t.get("URL_ARCHIVO").toString());
                       return rq;
            
                }

        }catch(Exception e){
            
            e.printStackTrace();
            
            rq.setSuccess(false);
            rq.setData(null);
            rq.setMsg("Error al guardar");
           return rq;
        }
        
       // return rq;
        
        
    }     
    
    
    @RequestMapping(value = "/imprimeFoliosNomMas", method = RequestMethod.POST)
    public @ResponseBody
    ResponseQuery imprimeFoliosNomMas( String data,WebRequest webRequest, Model model) throws IOException {

        ResponseQuery rq = new ResponseQuery();
       
        String compania = model.asMap().get("compania").toString();
            String usuario = model.asMap().get("usuario").toString();
       
      
     
      


           
            ObjectMapper mapper = new ObjectMapper();
            
            
            
        try{
                List<FoliosNomDispDTO> lista = mapper.readValue(data,
                    mapper.getTypeFactory().constructCollectionType(List.class,
                            FoliosNomDispDTO.class));


                    if (lista.isEmpty()) {
                        rq.setSuccess(false);
                        rq.setData(null);
                        rq.setMsg("Error no existen datos que guardar");
                        return rq;
                    }



                Iterator<FoliosNomDispDTO> it = lista.iterator();
                boolean result = false;
                
                 String cadena = getCadenaAlfanumAleatoria(5);
                 
                 Calendar calendar = Calendar.getInstance();
                SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMdd");
                    cadena = dateFormat.format(calendar.getTime()) + cadena;
        
                    String urlC = "D:\\carga\\FoliosNomina\\"+ cadena;
                    
                    File directorioZIP = new File("D:\\carga\\FoliosNomina\\"+ cadena );
                    
                    
                    
                    

                 while (it.hasNext()) {
//                 System.out.println("-------------------------------------------------------------------");
                 FoliosNomDispDTO ss = it.next();
                 
                 //descargaFolioNom(urlC,ss.urlArchivo,ss.ruta,ss.nombreArchivo);
                 //descargaFolioNom(urlC,"http://www.appferaz.com/sqr_nomina/ArchivosFolio/120822-62206285.txt",ss.ruta,ss.nombreArchivo);
                 
                 if(!ss.tipoPago.equalsIgnoreCase("PCHN")){
                     if(!ss.tipoPago.equalsIgnoreCase("PASCH")){
                        descargaFolioNom(urlC,ss.urlArchivo,ss.ruta,ss.nombreArchivo);

                        comprimir(directorioZIP,"D:\\carga\\FoliosNomina",cadena);
                     }
                 }
                 
                 Map parametros = new HashMap();
                   
                    parametros.put("compania", ss.compania);
                    parametros.put("folio",ss.folio);
                    parametros.put("zip",cadena);
                    
                    Querys que = new Querys();
                       String store = que.getSQL("ActualizaEstatusNomZip");
               

                        processDao.execute(store, parametros);
               
            }
      
                     
                      
                      rq.setSuccess(true);
                        rq.setData(null);
                        rq.setMsg(cadena);
                       return rq;
            
                

        }catch(Exception e){
            
            e.printStackTrace();
            
            rq.setSuccess(false);
            rq.setData(null);
            rq.setMsg("Error al guardar");
           return rq;
        }
        
       // return rq;
        
        
    }     
    
    
 
    
      @RequestMapping(value = "/enviaTesoreria", method = RequestMethod.POST)
    public @ResponseBody
    ResponseQuery enviaTesoreria( String data,String text,String importePagar, WebRequest webRequest, Model model) throws IOException {

        ResponseQuery rq = new ResponseQuery();
       System.out.println("text:"+text);
          System.out.println("importePagar:"+importePagar);
       
        String compania = model.asMap().get("compania").toString();
            String usuario = model.asMap().get("usuario").toString();
       
      
        Map tok = new HashMap();
              
        tok.put("idTok", text);
        tok.put("compania", compania);
        tok.put("usuario", usuario);
        
        List permisoTok = processDao.getMapResult("permisosTok", tok);
        
         if(permisoTok == null || permisoTok.size() == 0){
             
         }else{       
                List listVToken = processDao.getMapResult("ValidaToken", tok);

                if(listVToken == null || listVToken.size() == 0){


                       rq.setSuccess(false);
                       rq.setData(null);
                       rq.setMsg("Error, el token no es valido");
                       return rq;




                }else{

                  Map t = (HashMap) listVToken.get(0);

                      System.out.println(t.get("ID_CEL").toString());
                      System.out.println(t.get("TOK_ID").toString());
                      System.out.println(t.get("DIFERENECIA").toString());

                      BigDecimal dif = new BigDecimal(t.get("DIFERENECIA").toString());
                      BigDecimal dif2 = new BigDecimal(3);

                      if(dif.compareTo(dif2) == 1){

                           rq.setSuccess(false);
                           rq.setData(null);
                           rq.setMsg("Error, el token ha caducado");
                           return rq;


                      }



                }
         }
         
          Map nvlCXP = new HashMap();
                
                   nvlCXP.put("compania", compania);
                   nvlCXP.put("usuario", usuario);
                   nvlCXP.put("importePagar", importePagar);
                   
               
                   List listNvlCxp = processDao.getMapResult("BuscaNvlCXP", nvlCXP);
                   
                   System.out.println(listNvlCxp);
                    
                   if (listNvlCxp != null && !listNvlCxp.isEmpty()){
                       
                          Map nvlCxpC = (HashMap) listNvlCxp.get(0);
                          
                          if(!nvlCxpC.get("EXIST_NVL_CXP").toString().equalsIgnoreCase("0")){
                              
                               List listNvlCxpImp = processDao.getMapResult("BuscaNvlCXPImp", nvlCXP);

                            System.out.println(listNvlCxpImp);

                            if (listNvlCxpImp != null && !listNvlCxpImp.isEmpty()){

                                   Map nvlCxpCImp = (HashMap) listNvlCxpImp.get(0);

                                   if(nvlCxpCImp.get("EXIST_NVL_CXP_IMP").toString().equalsIgnoreCase("0")){

                                         rq.setSuccess(false);
                                         rq.setData(null);
                                         rq.setMsg("Error el usuario no puede autorizar, el monto excede la cantidad permitida.");

                                         return rq;
                                   }}

                              
                          }}
         
         
        
         int index = data.indexOf("[");
        if (index == -1) {
            data = "[" + data + "]";
        }


           
            ObjectMapper mapper = new ObjectMapper();
            
        try{
            List<EnvioTesDTO> lista = mapper.readValue(data,
                    mapper.getTypeFactory().constructCollectionType(List.class,
                            EnvioTesDTO.class));
            
            

            if (lista.isEmpty()) {
                rq.setSuccess(false);
                rq.setData(null);
                rq.setMsg("Error no existen datos que guardar");
                return rq;
            }

            
                   Map permisos = new HashMap();
                
                   permisos.put("compania", compania);
                   permisos.put("usuario", usuario);
               
                   List listPermisos = processDao.getMapResult("BuscaPermisosComponentesCXP", permisos);
                   
                   System.out.println(listPermisos);
                    
                   if (listPermisos != null && !listPermisos.isEmpty()){
                       
                          Map perm = (HashMap) listPermisos.get(0);
                          
                          if(!perm.get("HIDDEN").toString().equalsIgnoreCase("0")){
                              
                                rq.setSuccess(false);
                                rq.setData(null);
                                rq.setMsg("Error el usuario no tiene permisos para liberar a tesoreria");

                                return rq;
                          
                          
                          }
                          
                   }else{
                   
                        rq.setSuccess(false);
                        rq.setData(null);
                        rq.setMsg("Error el usuario no tiene permisos para liberar a tesoreria");
                        
                        return rq;

                       
                   }
                   
 
            Iterator<EnvioTesDTO> it = lista.iterator();
           ErpCxpFolios folios = new ErpCxpFolios();
           ErpCxpFoliosId foliosId = new ErpCxpFoliosId();
           
           ErpFeComprobantes comprobantes = new ErpFeComprobantes();
           ErpFeComprobantesId comprobantesId = new ErpFeComprobantesId();
           
           ErpCpOtras otras = new ErpCpOtras();
           ErpCpOtrasId otrasId = new ErpCpOtrasId();
           
           ErpProvPagoUnico uni = new ErpProvPagoUnico();
           ErpProvPagoUnicoId uniId = new ErpProvPagoUnicoId();
          
            String folioPagos = "";
            int banderaGe = 0 ;
            
            Querys que = new Querys();
            String store = que.getSQL("CONTA_ACTUALIZA_VIATICOS");
            
            while (it.hasNext()) {
//                 System.out.println("-------------------------------------------------------------------");
                 EnvioTesDTO ss = it.next();
                 
                 
                 comprobantesId.setCompania(ss.compania);
                 comprobantesId.setNumero(Integer.parseInt(ss.numero));
                 comprobantes.setId(comprobantesId);
                 
                 otrasId.setCompania(ss.compania);
                 otrasId.setId(Integer.parseInt(ss.numero));
                 otras.setId(otrasId);
                 otras.setEstatusCxp("TES");
                 otras.setPagoCie(ss.pagoCieFol);
                 otras.setRefenciaCie(ss.referenciaCieFol);
                 otras.setConceptoCie(ss.conceptoCieFol);
                 otras.setFolioPagos(Integer.parseInt(ss.folioCxp));
                 
                 
                 uniId.setCompania(compania);
                 uniId.setIdMov(Integer.parseInt(ss.numero));
                 uni.setId(uniId);
                 uni.setEstatusCxp("TES");
                 uni.setFolioPagos(Integer.parseInt(ss.folioCxp));
                 
                 foliosId.setCompania(ss.compania);
                 foliosId.setId(0);
                 folios.setId(foliosId);
                 folios.setFolio(Integer.parseInt(ss.folioCxp));
                 folios.setNumeroFe(Integer.parseInt(ss.numero));
                 folios.setOperacion("ET");
                 
                 
                 erpCxpFoliosDao.actualizaEstatusFolio(folios);
                 
                 if(ss.origenFol.equalsIgnoreCase("F")){
                    erpFeComprobantesDao.actualizaPago("TES", new BigDecimal(0), comprobantes);
                 }
                 if(ss.origenFol.equalsIgnoreCase("O")){
                    erpCpOtrasDao.updateErpCpOtrasEstatusPagosTeso(otras);
                 }
                 if(ss.origenFol.equalsIgnoreCase("U")){
                    erpProvPagoUnicoDao.updateErpProvPagoUnicoEstatusPagos(uni);
                 }
                 if(ss.origenFol.equalsIgnoreCase("V")){
                    
                  Map paramViatico = new HashMap();
                    paramViatico.put("COMPANIA",compania);
                    paramViatico.put("COMISION",ss.numero);
                    paramViatico.put("TIPO","TES");
                    paramViatico.put("FOLIO_PAGO",ss.folioCxp);
            
                int val = processDao.execute(store, paramViatico);

                     if (val <= 0) {

                }
                 }
                 
                  folioPagos = ss.folioCxp;
                  banderaGe = 1;
            }
            
             Querys queT = new Querys();
             String storeT = queT.getSQL("InsertaTokenFolio");
            
             
             Map paramTOK = new HashMap();
                    paramTOK.put("TOK_ID",text);
                    paramTOK.put("FOLIO_AUT",folioPagos);
           
            
                int val = processDao.execute(storeT, paramTOK);
             
            
            ErpPolizasVSFacturasAutErrId result=null;
            
         
                ErpPolizasVSFacturasAutErr correo = new ErpPolizasVSFacturasAutErr();

                ErpPolizasVSFacturasAutErrId correoId = new ErpPolizasVSFacturasAutErrId();

                correoId.setCompania(compania);
                correoId.setFolio(folioPagos);
                correoId.setTipo("5");
                
                correo.setId(correoId);
                correo.setEnvio("0");
                correo.setDescripcion("Se envio Folio: "+folioPagos+ " para impresión");
                correo.setFechaCap(new Date());
                correo.setNombre("Folio para impresion");
               
                
                result = erpPolizasVSFacturasAutErrDao.save(correo);
            
            
            
            
            if (banderaGe == 1){
            
                if (result == null){
                    rq.setSuccess(true);
                    rq.setData(null);
                    rq.setMsg("Enviado a pagar con folio: "+folioPagos+", error al enviar correo");
                }else{
                
                    rq.setSuccess(true);
                    rq.setData(null);
                    rq.setMsg("Enviado a pagar con folio: "+folioPagos+", se ha enviado correo a Tesoreria");
                
                }
            }else{
                
                rq.setSuccess(true);
                rq.setData(null);
                rq.setMsg("No hay registros para enviar");
                
            }
            

        }catch(Exception e){
            
            e.printStackTrace();
            
            rq.setSuccess(false);
            rq.setData(null);
            rq.setMsg("Error al guardar");
           return rq;
        }
        
        return rq;
        
        
    }
    
     @RequestMapping(value = "/eliminaFactFolioCXP", method = RequestMethod.POST)
    public @ResponseBody
    ResponseQuery eliminaFactFolioCXP( String data, WebRequest webRequest, Model model) throws IOException {

        ResponseQuery rq = new ResponseQuery();
        
         int index = data.indexOf("[");
        if (index == -1) {
            data = "[" + data + "]";
        }
         
      //  System.out.println("----------------------------------------------");
        
     //   System.out.println(data);

            String compania = model.asMap().get("compania").toString();
            String usuario = model.asMap().get("usuario").toString();
            ObjectMapper mapper = new ObjectMapper();
            
        try{
            List<EnvioTesDTO> lista = mapper.readValue(data,
                    mapper.getTypeFactory().constructCollectionType(List.class,
                            EnvioTesDTO.class));

            if (lista.isEmpty()) {
                rq.setSuccess(false);
                rq.setData(null);
                rq.setMsg("Error no existen datos que guardar");
                return rq;
            }

  
            Iterator<EnvioTesDTO> it = lista.iterator();
           ErpCxpFolios folios = new ErpCxpFolios();
           ErpCxpFoliosId foliosId = new ErpCxpFoliosId();
           
           ErpFeComprobantes comprobantes = new ErpFeComprobantes();
           ErpFeComprobantesId comprobantesId = new ErpFeComprobantesId();
           ErpCpOtras otras = new ErpCpOtras();
           ErpCpOtrasId otrasId = new ErpCpOtrasId();
           
           ErpProvPagoUnico erpProvPagoUnico = new ErpProvPagoUnico();
           ErpProvPagoUnicoId erpProvPagoUnicoId = new ErpProvPagoUnicoId();
          
            String folioPagos = "";
            int banderaGe = 0 ;
            
            Querys que = new Querys();
            String store = que.getSQL("CONTA_ACTUALIZA_VIATICOS");
         
            
            while (it.hasNext()) {
              
                 EnvioTesDTO ss = it.next();
                 
                 
                 comprobantesId.setCompania(ss.compania);
                 comprobantesId.setNumero(Integer.parseInt(ss.numero));
                 comprobantes.setId(comprobantesId);
                 
                 otrasId.setCompania(ss.compania);
                 otrasId.setId(Integer.parseInt(ss.numero));
                 otras.setId(otrasId);
                 otras.setEstatusCxp("");
                 otras.setFolioPagos(null);
                 
                 erpProvPagoUnicoId.setCompania(ss.compania);
                 erpProvPagoUnicoId.setIdMov(Integer.parseInt(ss.numero));
                 erpProvPagoUnico.setId(erpProvPagoUnicoId);
                 erpProvPagoUnico.setEstatusCxp("");
                 erpProvPagoUnico.setFolioPagos(null);
                 
                 
                 
                 foliosId.setCompania(ss.compania);
                 foliosId.setId(0);
                 folios.setId(foliosId);
                 folios.setFolio(Integer.parseInt(ss.folioCxp));
                 folios.setNumeroFe(Integer.parseInt(ss.numero));
                 folios.setOperacion("");
                 
                 
                 erpCxpFoliosDao.eliminaFactFolio(folios);
                 
                 if (ss.origenFol.equalsIgnoreCase("F")){
                 
                    erpFeComprobantesDao.eliminaRelacionTesoreria("", null, comprobantes);
                 }
                 if (ss.origenFol.equalsIgnoreCase("O")){
                     
                  
                    erpCpOtrasDao.updateErpCpOtrasEstatusPagos(otras);
                 }
                 
                 if (ss.origenFol.equalsIgnoreCase("U")){
                     
                  
                    erpProvPagoUnicoDao.updateErpProvPagoUnicoEstatusPagos(erpProvPagoUnico);
                 }
                 
                 if (ss.origenFol.equalsIgnoreCase("V")){
          
                 
                   
                  Map paramViatico = new HashMap();
                    paramViatico.put("COMPANIA",compania);
                    paramViatico.put("COMISION",ss.numero);
                    paramViatico.put("TIPO","CAN");
                    paramViatico.put("FOLIO_PAGO",ss.folioCxp);
                    
                     int val = processDao.execute(store, paramViatico);

                     if (val <= 0) {

                }
                    
                 }
                  folioPagos = ss.folioCxp;
                  banderaGe = 1;
            }
            
          
            
            if (banderaGe == 1){
            
                
                    rq.setSuccess(true);
                    rq.setData(null);
                    rq.setMsg("Facturas Eliminadas Correctamente del folio: "+folioPagos);
              
                
            }else{
                
                rq.setSuccess(true);
                rq.setData(null);
                rq.setMsg("No hay registros para enviar");
                
            }
            

        }catch(Exception e){
            e.printStackTrace();
            rq.setSuccess(false);
            rq.setData(null);
            rq.setMsg("Error al guardar");
           return rq;
        }
        
        return rq;
        
        
    }
         @RequestMapping(value = "/deleteOtros", method = RequestMethod.POST)
    public @ResponseBody
    ResponseQuery deleteOtros( String data, WebRequest webRequest, Model model) throws IOException {

        ResponseQuery rq = new ResponseQuery();
   //     System.out.println("data factura cancelacion:"+data);
        
         int index = data.indexOf("[");
        if (index == -1) {
            data = "[" + data + "]";
        }


            String compania = model.asMap().get("compania").toString();
            String usuario = model.asMap().get("usuario").toString();
            ObjectMapper mapper = new ObjectMapper();
          
                   
        try{
            List<CxpOtrasDTO> lista = mapper.readValue(data,
                    mapper.getTypeFactory().constructCollectionType(List.class,
                            CxpOtrasDTO.class));

            if (lista.isEmpty()) {
                rq.setSuccess(false);
                rq.setData(null);
                rq.setMsg("Error no existen datos que guardar");
                return rq;
            }

  
            Iterator<CxpOtrasDTO> it = lista.iterator();
           ErpCpOtras otras = new ErpCpOtras();
           ErpCpOtrasId otrasId = new ErpCpOtrasId();
          
          
            int banderaGe = 0 ;
            while (it.hasNext()) {
//                 System.out.println("-------------------------------------------------------------------");
                 CxpOtrasDTO ss = it.next();
                 
                 otrasId.setCompania(ss.compania);
                 otrasId.setId(Integer.parseInt(ss.id));
                 otras.setId(otrasId);
                 
                 erpCpOtrasDao.delete(otras);
               
                  banderaGe = 1;
            }
            
          
            
            if (banderaGe == 1){
            
                
                    rq.setSuccess(true);
                    rq.setData(null);
                    rq.setMsg("Registros eliminados correctamente");
              
                
            }else{
                
                rq.setSuccess(true);
                rq.setData(null);
                rq.setMsg("Error al eliminar");
                
            }
            

        }catch(Exception e){
            e.printStackTrace();
            rq.setSuccess(false);
            rq.setData(null);
            rq.setMsg("Error al guardar");
           return rq;
        }
        
        return rq;
    }
    
    
     @RequestMapping(value = "/updateOtros", method = RequestMethod.POST)
    public @ResponseBody
    ResponseQuery updateOtros( String data, WebRequest webRequest, Model model) throws IOException {

        ResponseQuery rq = new ResponseQuery();
   //     System.out.println("data factura cancelacion:"+data);
        SimpleDateFormat formatFecha = new SimpleDateFormat("dd/MM/yyyy");
         int index = data.indexOf("[");
        if (index == -1) {
            data = "[" + data + "]";
        }

       // System.out.println("data:  "+data);
            String compania = model.asMap().get("compania").toString();
            String usuario = model.asMap().get("usuario").toString();
            ObjectMapper mapper = new ObjectMapper();
          
                   
        try{
            List<CxpOtrasDTO> lista = mapper.readValue(data,
                    mapper.getTypeFactory().constructCollectionType(List.class,
                            CxpOtrasDTO.class));

            if (lista.isEmpty()) {
                rq.setSuccess(false);
                rq.setData(null);
                rq.setMsg("Error no existen datos que guardar");
                return rq;
            }

  
            Iterator<CxpOtrasDTO> it = lista.iterator();
           ErpCpOtras otras = new ErpCpOtras();
           ErpCpOtrasId otrasId = new ErpCpOtrasId();
          
          
            int banderaGe = 0 ;
            while (it.hasNext()) {
//                 System.out.println("-------------------------------------------------------------------");
                 CxpOtrasDTO ss = it.next();
                 
                 otrasId.setCompania(ss.compania);
                 otrasId.setId(Integer.parseInt(ss.id));
                 otras.setId(otrasId);
                 
                // System.out.println("fecha: ----"+ss.fechaCxp);
                
                if (ss.fechaCashFlow == null || ss.fechaCashFlow.equalsIgnoreCase("")){
                 
                     otras.setFechaCashFlow(null);
                 }else{
                     
                     otras.setFechaCashFlow(formatFecha.parse(ss.fechaCashFlow));
                 
                 }
                 
                 if (ss.fechaCxp == null || ss.fechaCxp.equalsIgnoreCase("")){
                 
                     otras.setFechaVencCxp(null);
                 }else{
                     
                     otras.setFechaVencCxp(formatFecha.parse(ss.fechaCxp));
                 
                 }
                 
                 if (ss.fechaContabProvOtros == null || ss.fechaContabProvOtros.equalsIgnoreCase("")){
                 
                     otras.setFechaContabProv(null);
                 }else{
                     
                     otras.setFechaContabProv(formatFecha.parse(ss.fechaContabProvOtros));
                 
                 }
                 
                 
                 if (ss.idTipoGastoOtros == null || ss.idTipoGastoOtros.equalsIgnoreCase("")){
                 
                     otras.setIdTipoGasto(null);
                 }else{
                     
                     otras.setIdTipoGasto(Integer.parseInt(ss.idTipoGastoOtros));
                 
                 }
                 
                 if (ss.idServicio == null || ss.idServicio.equalsIgnoreCase("")){
                 
                     otras.setIdServicio(null);
                 }else{
                     
                     otras.setIdServicio(Integer.parseInt(ss.idServicio));
                 
                 }
                 
                 
                 otras.setConceptoCxp(ss.concepto);
                 otras.setCtoCxp(ss.cc);
                 otras.setIdTipoNegocio(ss.idTipoNegocioOtros);
                 otras.setIdPaisCxp(ss.idPaisCxpOtros);
                 
                 erpCpOtrasDao.updateErpCpOtras(otras,"");
               
                  banderaGe = 1;
            }
            
          
            
            if (banderaGe == 1){
            
                
                    rq.setSuccess(true);
                    rq.setData(null);
                    rq.setMsg("Registros eliminados correctamente");
              
                
            }else{
                
                rq.setSuccess(true);
                rq.setData(null);
                rq.setMsg("Error al eliminar");
                
            }
            

        }catch(Exception e){
            e.printStackTrace();
            rq.setSuccess(false);
            rq.setData(null);
            rq.setMsg("Error al guardar");
           return rq;
        }
        
        return rq;
    }
    
    
    
     @RequestMapping(value = "/relacionaOtrasXFacturas", method = RequestMethod.POST)
    public @ResponseBody
    ResponseQuery relacionaOtrasXFacturas( String data,String data2, WebRequest webRequest, Model model) throws IOException {

        ResponseQuery rq = new ResponseQuery();
   //     System.out.println("data factura cancelacion:"+data);
        SimpleDateFormat formatFecha = new SimpleDateFormat("dd/MM/yyyy");
         int index = data.indexOf("[");
        if (index == -1) {
            data = "[" + data + "]";
        }
        
          int index2 = data2.indexOf("[");
        if (index2 == -1) {
            data2 = "[" + data2 + "]";
        }
//        
        System.out.println("------------------En el proceso------------------------------");
//        
//        System.out.println("data:  "+data);
//        System.out.println("---------------------------------------------------");
//        
//        System.out.println("data2:  "+data2);
//        System.out.println("---------------------------------------------------");
        
            String compania = model.asMap().get("compania").toString();
            String usuario = model.asMap().get("usuario").toString();
            ObjectMapper mapper = new ObjectMapper();
          
                   
        try{
            List<CxpOtrasDTO> lista = mapper.readValue(data,
                    mapper.getTypeFactory().constructCollectionType(List.class,
                            CxpOtrasDTO.class));
            
            List<RelacionFactXOtrasDTO> lista2 = mapper.readValue(data2,
                    mapper.getTypeFactory().constructCollectionType(List.class,
                            RelacionFactXOtrasDTO.class));

            if (lista.isEmpty()) {
                rq.setSuccess(false);
                rq.setData(null);
                rq.setMsg("Error no existen datos que guardar");
                return rq;
            }
            
            if (lista2.isEmpty()) {
                rq.setSuccess(false);
                rq.setData(null);
                rq.setMsg("Error no existen datos que guardar");
                return rq;
            }

  
           Iterator<CxpOtrasDTO> it = lista.iterator();
           Iterator<RelacionFactXOtrasDTO> it2 = lista2.iterator();
           
           ErpFeComprobantes comp = new ErpFeComprobantes();       
           ErpFeComprobantesId compId = new ErpFeComprobantesId();
           
           ErpCxpFacturasXOtras factxotras = new ErpCxpFacturasXOtras();
           ErpCxpFacturasXOtrasId factxotrasId = new ErpCxpFacturasXOtrasId();
           
             
           ErpProvisionDet provDet = new ErpProvisionDet();
           ErpProvisionDetId provDetId = new ErpProvisionDetId();
           
           ErpCpOtras otr = new ErpCpOtras();
           ErpCpOtrasId otrId = new ErpCpOtrasId();
           
           boolean result = true;
           ErpCxpFacturasXOtrasId result2 = null;
//           
//           
//         
//
            while (it.hasNext()) {
//                 System.out.println("-------------------------------------------------------------------");
                 CxpOtrasDTO ss = it.next();
                 
                 
                 
                  while (it2.hasNext()) {
                      
                      RelacionFactXOtrasDTO ss2 = it2.next();
                      
                        compId.setCompania(ss2.compania);
                        compId.setNumero(Integer.parseInt(ss2.numero));
                        comp.setId(compId);
                        otrId.setCompania(compania);
                        otrId.setId(Integer.parseInt(ss2.numero));
                        otr.setId(otrId);
                        
                       

                      System.out.println("ss2"+ss2.tipoGastoOrigen);
                      
                      
                      if(ss.tipoGasto.equalsIgnoreCase("R")){
                          
                          if(ss2.tipoGastoOrigen.equalsIgnoreCase("FE")){
                          
                             result = erpFeComprobantesDao.actualizaPago("REMB", BigDecimal.ZERO, comp);
                          }
                          
                          if(ss2.tipoGastoOrigen.equalsIgnoreCase("OTR")){
                              
                             
                              
                              otr.setEstatusCxp("REMB");
                              otr.setPagoAcumuladoCXP(BigDecimal.ZERO);
                              
                              erpCpOtrasDao.updateErpCpOtrasEstatusPagosOtras(otr);
                          
                          }
                          
                          
                      }
                      if(ss.tipoGasto.equalsIgnoreCase("A")){
                          if(ss2.tipoGastoOrigen.equalsIgnoreCase("FE")){
                              
                              result = erpFeComprobantesDao.actualizaPago("ANT", BigDecimal.ZERO, comp);
                          
                          }
                          
                           if(ss2.tipoGastoOrigen.equalsIgnoreCase("OTR")){
                              
                              otr.setEstatusCxp("ANT");
                              otr.setPagoAcumuladoCXP(BigDecimal.ZERO);
                              
                               System.out.println("otr.getId().getId()"+otr.getId().getId());
                              
                              erpCpOtrasDao.updateErpCpOtrasEstatusPagosOtras(otr);
                          
                          }
                      
                      }
                      
                      
                      
                      
                      factxotrasId.setCompania(compania);
                      int sec = erpCxpFacturasXOtrasDao.getMaxErpCpOtrasId(factxotrasId);
                      factxotrasId.setSec(sec);
                      factxotras.setId(factxotrasId);
                      factxotras.setNumeroFe(Integer.parseInt(ss2.numero));
                      factxotras.setNumeroOtras(Integer.parseInt(ss.id));
                      factxotras.setOrigen(ss2.tipoGastoOrigen);
                      
                      
                      result2 = erpCxpFacturasXOtrasDao.save(factxotras);
                      
                        System.out.println("Facturas Relacionadas");
                      
                      System.out.println("Tipo Gasto: "+ss.tipoGasto);
                      
                      System.out.println("Subtotal: "+ss2.subTotal);
                      
                      if(ss.tipoGasto.equalsIgnoreCase("W")){
                          
                          System.out.println("Entrando a crear registro");
                      
                        provDetId.setCompania(compania);
                        provDetId.setId(Integer.parseInt(ss.id));
                        int secProv = erpProvisionDetDao.getMaxerpProvisionDetId(provDetId);
                        provDetId.setSec(secProv);
                        provDet.setId(provDetId);
                        provDet.setIdDocumento(Integer.parseInt(ss2.numero));
                        provDet.setImporteDocumento(new BigDecimal(ss2.subTotal));
                        if (ss2.tipoGastoOrigen.equalsIgnoreCase("FE")){
                            provDet.setDescripcion("FACTURA NACIONAL");
                        }
                        
                        if (ss2.tipoGastoOrigen.equalsIgnoreCase("OTR")){
                            provDet.setDescripcion("FACTURA EXTRANJERA");
                        }
                        provDet.setOrigen(ss2.tipoGastoOrigen);

                        erpProvisionDetDao.save(provDet);
                      
                      }
                      
                         Querys que = new Querys();
                        String store = que.getSQL("ComprobacionAut");
			
			  Map paramComp = new HashMap();
                        paramComp.put("COMPANIA",compania);
                        paramComp.put("ID",ss.id);

                    int val = processDao.execute(store, paramComp);

                         if (val <= 0) {

                    }

                      
                      
                      
                  }
                 
                 
                 
               
                 
                 
               
            }
            
          
            
            if (result == true && result2 != null){
            
                
                    rq.setSuccess(true);
                    rq.setData(null);
                    rq.setMsg("Se guardo la relacion correctamente");
              
                
            }else{
//                
                rq.setSuccess(true);
                rq.setData(null);
                rq.setMsg("Error al guardar relacion");
                
            }
            

        }catch(Exception e){
            e.printStackTrace();
            rq.setSuccess(false);
            rq.setData(null);
            rq.setMsg("Error al guardar");
           return rq;
        }
        
        return rq;
    }
    
      @RequestMapping(value = "/quitarRelacionaOtrasXFacturas", method = RequestMethod.POST)
    public @ResponseBody
    ResponseQuery quitarRelacionaOtrasXFacturas( String data,String data2, WebRequest webRequest, Model model) throws IOException {

        ResponseQuery rq = new ResponseQuery();
   //     System.out.println("data factura cancelacion:"+data);
        SimpleDateFormat formatFecha = new SimpleDateFormat("dd/MM/yyyy");
         int index = data.indexOf("[");
        if (index == -1) {
            data = "[" + data + "]";
        }
        
          int index2 = data2.indexOf("[");
        if (index2 == -1) {
            data2 = "[" + data2 + "]";
        }
//        
//        System.out.println("---------------------------------------------------");
//        
//        System.out.println("data:  "+data);
//        System.out.println("---------------------------------------------------");
//        
//        System.out.println("data2:  "+data2);
//        System.out.println("---------------------------------------------------");
        
            String compania = model.asMap().get("compania").toString();
            String usuario = model.asMap().get("usuario").toString();
            ObjectMapper mapper = new ObjectMapper();
          
                   
        try{
            List<CxpOtrasDTO> lista = mapper.readValue(data,
                    mapper.getTypeFactory().constructCollectionType(List.class,
                            CxpOtrasDTO.class));
            
            List<RelacionFactXOtrasDTO> lista2 = mapper.readValue(data2,
                    mapper.getTypeFactory().constructCollectionType(List.class,
                            RelacionFactXOtrasDTO.class));

            if (lista.isEmpty()) {
                rq.setSuccess(false);
                rq.setData(null);
                rq.setMsg("Error no existen datos que guardar");
                return rq;
            }
            
            if (lista2.isEmpty()) {
                rq.setSuccess(false);
                rq.setData(null);
                rq.setMsg("Error no existen datos que guardar");
                return rq;
            }

  
           Iterator<CxpOtrasDTO> it = lista.iterator();
           Iterator<RelacionFactXOtrasDTO> it2 = lista2.iterator();
           
           ErpFeComprobantes comp = new ErpFeComprobantes();       
           ErpFeComprobantesId compId = new ErpFeComprobantesId();
           
           ErpCxpFacturasXOtras factxotras = new ErpCxpFacturasXOtras();
           ErpCxpFacturasXOtrasId factxotrasId = new ErpCxpFacturasXOtrasId();
           
            ErpCpOtras otr = new ErpCpOtras();
           ErpCpOtrasId otrId = new ErpCpOtrasId();
		   
	    
                              
            
//           
           boolean result = false;
           boolean result2 = false;
//           
           ErpProvisionDet provDet = new ErpProvisionDet();
           ErpProvisionDetId provDetId = new ErpProvisionDetId();
          
//         
//
            while (it.hasNext()) {
//                 System.out.println("-------------------------------------------------------------------");
                 CxpOtrasDTO ss = it.next();
                 
                 
                 
                  while (it2.hasNext()) {
                      
                      RelacionFactXOtrasDTO ss2 = it2.next();
                      
                        compId.setCompania(ss2.compania);
                        compId.setNumero(Integer.parseInt(ss2.numero));
                        comp.setId(compId);
                        
                        otrId.setCompania(compania);
                        otrId.setId(Integer.parseInt(ss2.numero));
                        otr.setId(otrId);
		   
                        otr.setEstatusCxp("");
                        otr.setPagoAcumuladoCXP(BigDecimal.ZERO);

                      if(ss.tipoGasto.equalsIgnoreCase("R")){
                          result = erpFeComprobantesDao.actualizaPago("", BigDecimal.ZERO, comp);
                          erpCpOtrasDao.updateErpCpOtrasEstatusPagosOtras(otr);
                      }
                      if(ss.tipoGasto.equalsIgnoreCase("A")){
                          
                          result = erpFeComprobantesDao.actualizaPago("", BigDecimal.ZERO, comp);
                          erpCpOtrasDao.updateErpCpOtrasEstatusPagosOtras(otr);
                      
                      }
                      
                      factxotrasId.setCompania(compania);
                     // int sec = erpCxpFacturasXOtrasDao.getMaxErpCpOtrasId(factxotrasId);
                     // factxotrasId.setSec(sec);
                      factxotras.setId(factxotrasId);
                      factxotras.setNumeroFe(Integer.parseInt(ss2.numero));
                      factxotras.setNumeroOtras(Integer.parseInt(ss.id));
                      factxotras.setOrigen(ss2.tipoGastoOrigen);
                      
                      
                      result2 = erpCxpFacturasXOtrasDao.eliminaFactOtras(factxotras);
                      
                      if(ss.tipoGasto.equalsIgnoreCase("W")){
                          
                          System.out.println("Entrando a crear registro");
                      
                        provDetId.setCompania(compania);
                        provDetId.setId(Integer.parseInt(ss.id));
                      //  int secProv = erpProvisionDetDao.getMaxerpProvisionDetId(provDetId);
                      //  provDetId.setSec(secProv);
                        provDet.setId(provDetId);
                        provDet.setIdDocumento(Integer.parseInt(ss2.numero));
                        provDet.setImporteDocumento(new BigDecimal(ss2.subTotal));
                        if (ss2.tipoGastoOrigen.equalsIgnoreCase("FE")){
                            provDet.setDescripcion("FACTURA NACIONAL");
                        }
                        
                        if (ss2.tipoGastoOrigen.equalsIgnoreCase("OTR")){
                            provDet.setDescripcion("FACTURA EXTRANJERA");
                        }
                        provDet.setOrigen(ss2.tipoGastoOrigen);

                        erpProvisionDetDao.eliminaProvisionDoc(provDet);
                      
                      }
                      
                      
                       Querys que = new Querys();
                        String store = que.getSQL("EliminaComprobacionAut");
			
			  Map paramComp = new HashMap();
                        paramComp.put("COMPANIA",compania);
                        paramComp.put("ID",ss.id);
                        paramComp.put("NUMERO",ss2.numero);
                        

                    int val = processDao.execute(store, paramComp);

                         if (val <= 0) {

                    }

                      
                      
                      
                      
                  }
                 
                 
                 
                 
                 
                 
               
            }
            
          
            
            if (result2 == true){
            
                
                    rq.setSuccess(true);
                    rq.setData(null);
                    rq.setMsg("Se elimino la relacion correctamente");
              
                
            }else{
//                
                rq.setSuccess(true);
                rq.setData(null);
                rq.setMsg("Error al eliminar relacion");
                
            }
            

        }catch(Exception e){
            e.printStackTrace();
            rq.setSuccess(false);
            rq.setData(null);
            rq.setMsg("Error al guardar");
           return rq;
        }
        
        return rq;
    }
    
    
        @RequestMapping(value = "/cancelaProvision", method = RequestMethod.POST)
    public @ResponseBody
    ResponseQuery canProv( @RequestParam("numero") String numero,@RequestParam("importe") String importe,@RequestParam("fecha") String fecha, WebRequest webRequest, Model model) throws IOException {

        ResponseQuery rq = new ResponseQuery();
        
       
        
            String compania = model.asMap().get("compania").toString();
            String usuario = model.asMap().get("usuario").toString();
            ObjectMapper mapper = new ObjectMapper();
          
        try{
            
             BigDecimal impCancelado = new BigDecimal(0);
                Map provisionMap = new HashMap();
                
               provisionMap.put("compania", compania);
               provisionMap.put("numero", numero);
               
                SimpleDateFormat formatFecha = new SimpleDateFormat("dd/MM/yyyy");
       
                  List listProvision = processDao.getMapResult("BuscaTotalesCancelarProv", provisionMap);
                   System.out.println("Lista total");
                   System.out.println(listProvision);
                if(!listProvision.isEmpty()){   
                    Map provCan = (HashMap) listProvision.get(0);
                    System.out.println("Lista total");
                    BigDecimal importeTotCan = new BigDecimal(provCan.get("IMPORTE").toString());
                    BigDecimal importeDocCan = new BigDecimal(provCan.get("IMPORTE_DOC").toString());
                    
                     System.out.println("Importe Total" + importeTotCan);
                     System.out.println("Importe restantente" + importeDocCan);
                    impCancelado = importeTotCan.subtract(importeDocCan);
                    
                    System.out.println("Importe cancelado" + impCancelado);
                    
                }else{
                
                    impCancelado = new BigDecimal(importe);
                }
            ErpCpOtras erpCpOtras = new ErpCpOtras();
            ErpCpOtrasId erpCpOtrasId = new ErpCpOtrasId();
            
            ErpProvisionDet provDet = new ErpProvisionDet();
           ErpProvisionDetId provDetId = new ErpProvisionDetId();
          
            
                provDetId.setCompania(compania);
                        provDetId.setId(Integer.parseInt(numero));
                        int secProv = erpProvisionDetDao.getMaxerpProvisionDetId(provDetId);
                        provDetId.setSec(secProv);
                        provDet.setId(provDetId);
                        provDet.setIdDocumento(0);
                        provDet.setImporteDocumento(impCancelado);
                       
                        provDet.setDescripcion("CANCELACION PROVISION");
                        
                        provDet.setOrigen("CAN");
                        provDet.setFechaCancelacion(formatFecha.parse(fecha));

                      ErpProvisionDetId  result = erpProvisionDetDao.save(provDet);
                    
          
         
            
//                 System.out.println("-------------------------------------------------------------------");
                

                if(result == null){

                    rq.setSuccess(false);
                    rq.setMsg("Error al guardar Datos");

                }else{

                    rq.setSuccess(true);
                    rq.setMsg("Se cancelo correctamente la provision");

                }
                
               
            


            
            
            

         
            
        }catch(Exception e){
            rq.setSuccess(false);
            rq.setData(null);
            rq.setMsg("Error al guardar");
           return rq;
        }
        
        return rq;
    }
    
     @RequestMapping(value = "/relacionaFacturasXOtras", method = RequestMethod.POST)
    public @ResponseBody
    ResponseQuery relacionaFacturasXOtras( String data,String data2, WebRequest webRequest, Model model) throws IOException {

        ResponseQuery rq = new ResponseQuery();
   //     System.out.println("data factura cancelacion:"+data);
        SimpleDateFormat formatFecha = new SimpleDateFormat("dd/MM/yyyy");
         int index = data.indexOf("[");
        if (index == -1) {
            data = "[" + data + "]";
        }
        
          int index2 = data2.indexOf("[");
        if (index2 == -1) {
            data2 = "[" + data2 + "]";
        }
        
//        System.out.println("---------------------------------------------------");
//        
//        System.out.println("data:  "+data);
//        System.out.println("---------------------------------------------------");
//        
//        System.out.println("data2:  "+data2);
//        System.out.println("---------------------------------------------------");
        
            String compania = model.asMap().get("compania").toString();
            String usuario = model.asMap().get("usuario").toString();
            ObjectMapper mapper = new ObjectMapper();
          
                   
        try{
            List<CxpDTO> lista = mapper.readValue(data,
                    mapper.getTypeFactory().constructCollectionType(List.class,
                            CxpDTO.class));
            
            List<RelacionFactXOtrasDTO> lista2 = mapper.readValue(data2,
                    mapper.getTypeFactory().constructCollectionType(List.class,
                            RelacionFactXOtrasDTO.class));

            if (lista.isEmpty()) {
                rq.setSuccess(false);
                rq.setData(null);
                rq.setMsg("Error no existen datos que guardar");
                return rq;
            }
            
            if (lista2.isEmpty()) {
                rq.setSuccess(false);
                rq.setData(null);
                rq.setMsg("Error no existen datos que guardar");
                return rq;
            }

  
           Iterator<CxpDTO> it = lista.iterator();
           Iterator<RelacionFactXOtrasDTO> it2 = lista2.iterator();
           
           ErpFeComprobantes comp = new ErpFeComprobantes();       
           ErpFeComprobantesId compId = new ErpFeComprobantesId();
           
           ErpCxpFacturasXOtras factxotras = new ErpCxpFacturasXOtras();
           ErpCxpFacturasXOtrasId factxotrasId = new ErpCxpFacturasXOtrasId();
           
           ErpProvisionDet provDet = new ErpProvisionDet();
           ErpProvisionDetId provDetId = new ErpProvisionDetId();
           ErpCpOtras otr = new ErpCpOtras();
           ErpCpOtrasId otrId = new ErpCpOtrasId();
           
           boolean result = false;
           ErpCxpFacturasXOtrasId result2 = null;
           
           
         

            while (it.hasNext()) {
//                 System.out.println("-------------------------------------------------------------------");
                 CxpDTO ss = it.next();
                 
                 compId.setCompania(ss.compania);
                 compId.setNumero(Integer.parseInt(ss.numero));
                 comp.setId(compId);
                 
                 otrId.setCompania(compania);
                 otrId.setId(Integer.parseInt(ss.numero));
                 otr.setId(otrId);
                 
                  while (it2.hasNext()) {
                      
                      RelacionFactXOtrasDTO ss2 = it2.next();
                      
                      
                      if(ss2.tipoGasto.equalsIgnoreCase("R")){
                          
                          if(ss2.tipoGastoOrigen.equalsIgnoreCase("FE")){
                          
                             result = erpFeComprobantesDao.actualizaPago("REMB", BigDecimal.ZERO, comp);
                          }
                          
                          if(ss2.tipoGastoOrigen.equalsIgnoreCase("OTR")){
                              
                              otr.setEstatusCxp("REMB");
                              otr.setPagoAcumuladoCXP(BigDecimal.ZERO);
                              
                              erpCpOtrasDao.updateErpCpOtrasEstatusPagosOtras(otr);
                          
                          }
                          
                          
                      }
                      if(ss2.tipoGasto.equalsIgnoreCase("A")){
                          if(ss2.tipoGastoOrigen.equalsIgnoreCase("FE")){
                              
                              result = erpFeComprobantesDao.actualizaPago("ANT", BigDecimal.ZERO, comp);
                          
                          }
                          
                           if(ss2.tipoGastoOrigen.equalsIgnoreCase("OTR")){
                              
                              otr.setEstatusCxp("ANT");
                              otr.setPagoAcumuladoCXP(BigDecimal.ZERO);
                              
                              erpCpOtrasDao.updateErpCpOtrasEstatusPagosOtras(otr);
                          
                          }
                      
                      }
                      
                      factxotrasId.setCompania(compania);
                      int sec = erpCxpFacturasXOtrasDao.getMaxErpCpOtrasId(factxotrasId);
                      factxotrasId.setSec(sec);
                      factxotras.setId(factxotrasId);
                      factxotras.setNumeroFe(Integer.parseInt(ss.numero));
                      factxotras.setNumeroOtras(Integer.parseInt(ss2.numero));
                      factxotras.setOrigen(ss2.tipoGastoOrigen);
                      
                      
                      result2 = erpCxpFacturasXOtrasDao.save(factxotras);
                      
                    
                      
                  }
                 
                 
                 
                 
                 
                 
               
            }
            
          
            
            if (result == true && result2 != null){
            
                
                    rq.setSuccess(true);
                    rq.setData(null);
                    rq.setMsg("Se guardo la relacion correctamente");
              
                
            }else{
                
                rq.setSuccess(true);
                rq.setData(null);
                rq.setMsg("Error al guardar relacion");
                
            }
            

        }catch(Exception e){
            e.printStackTrace();
            rq.setSuccess(false);
            rq.setData(null);
            rq.setMsg("Error al guardar");
           return rq;
        }
        
        return rq;
    }
    
    
      @RequestMapping(value = "/quitarRelacionaFacturasXOtras", method = RequestMethod.POST)
    public @ResponseBody
    ResponseQuery quitarRelacionaFacturasXOtras( String data,String data2, WebRequest webRequest, Model model) throws IOException {

        ResponseQuery rq = new ResponseQuery();
   //     System.out.println("data factura cancelacion:"+data);
        SimpleDateFormat formatFecha = new SimpleDateFormat("dd/MM/yyyy");
         int index = data.indexOf("[");
        if (index == -1) {
            data = "[" + data + "]";
        }
        
          int index2 = data2.indexOf("[");
        if (index2 == -1) {
            data2 = "[" + data2 + "]";
        }
        
//        System.out.println("---------------------------------------------------");
//        
//        System.out.println("data:  "+data);
//        System.out.println("---------------------------------------------------");
//        
//        System.out.println("data2:  "+data2);
//        System.out.println("---------------------------------------------------");
        
            String compania = model.asMap().get("compania").toString();
            String usuario = model.asMap().get("usuario").toString();
            ObjectMapper mapper = new ObjectMapper();
          
                   
        try{
            List<CxpDTO> lista = mapper.readValue(data,
                    mapper.getTypeFactory().constructCollectionType(List.class,
                            CxpDTO.class));
            
            List<RelacionFactXOtrasDTO> lista2 = mapper.readValue(data2,
                    mapper.getTypeFactory().constructCollectionType(List.class,
                            RelacionFactXOtrasDTO.class));

            if (lista.isEmpty()) {
                rq.setSuccess(false);
                rq.setData(null);
                rq.setMsg("Error no existen datos que guardar");
                return rq;
            }
            
            if (lista2.isEmpty()) {
                rq.setSuccess(false);
                rq.setData(null);
                rq.setMsg("Error no existen datos que guardar");
                return rq;
            }

  
           Iterator<CxpDTO> it = lista.iterator();
           Iterator<RelacionFactXOtrasDTO> it2 = lista2.iterator();
           
           ErpFeComprobantes comp = new ErpFeComprobantes();       
           ErpFeComprobantesId compId = new ErpFeComprobantesId();
           
           ErpCxpFacturasXOtras factxotras = new ErpCxpFacturasXOtras();
           ErpCxpFacturasXOtrasId factxotrasId = new ErpCxpFacturasXOtrasId();
           
           boolean result = false;
           boolean result2 = false;
           
           
         

            while (it.hasNext()) {
//                 System.out.println("-------------------------------------------------------------------");
                 CxpDTO ss = it.next();
                 
                 compId.setCompania(ss.compania);
                 compId.setNumero(Integer.parseInt(ss.numero));
                 comp.setId(compId);
                 
                  while (it2.hasNext()) {
                      
                      RelacionFactXOtrasDTO ss2 = it2.next();
                      
                      
                      if(ss2.tipoGasto.equalsIgnoreCase("R")){
                          result = erpFeComprobantesDao.actualizaPago("", BigDecimal.ZERO, comp);
                      }
                      if(ss2.tipoGasto.equalsIgnoreCase("A")){
                          
                          result = erpFeComprobantesDao.actualizaPago("", BigDecimal.ZERO, comp);
                      
                      }
                      
                      factxotrasId.setCompania(compania);
//                      int sec = erpCxpFacturasXOtrasDao.getMaxErpCpOtrasId(factxotrasId);
//                      factxotrasId.setSec(sec);
                      factxotras.setId(factxotrasId);
                      factxotras.setNumeroFe(Integer.parseInt(ss.numero));
                      factxotras.setNumeroOtras(Integer.parseInt(ss2.numero));
                      
                      
                      result2 = erpCxpFacturasXOtrasDao.eliminaFactOtras(factxotras);
                      
                      
                      
                  }
                 
                 
                 
                 
                 
                 
               
            }
            
          
            
            if (result == true && result2 == true){
            
                
                    rq.setSuccess(true);
                    rq.setData(null);
                    rq.setMsg("Se elimino la relacion correctamente");
              
                
            }else{
                
                rq.setSuccess(true);
                rq.setData(null);
                rq.setMsg("Error al eliminar relacion");
                
            }
            

        }catch(Exception e){
            e.printStackTrace();
            rq.setSuccess(false);
            rq.setData(null);
            rq.setMsg("Error al guardar");
           return rq;
        }
        
        return rq;
    }
    
       @RequestMapping(value = "/generaConceptosPagos", method = RequestMethod.POST)
    public @ResponseBody
    ResponseQuery generaConceptosPagos( String data, WebRequest webRequest, Model model) throws IOException {

        ResponseQuery rq = new ResponseQuery();
   //     System.out.println("data factura cancelacion:"+data);
        SimpleDateFormat formatFecha = new SimpleDateFormat("dd/MM/yyyy");
         int index = data.indexOf("[");
        if (index == -1) {
            data = "[" + data + "]";
        }
  
        
            String compania = model.asMap().get("compania").toString();
            String usuario = model.asMap().get("usuario").toString();
            ObjectMapper mapper = new ObjectMapper();
          
                   
        try{
            List<ErpConcGastoDTO> lista = mapper.readValue(data,
                    mapper.getTypeFactory().constructCollectionType(List.class,
                            ErpConcGastoDTO.class));
            
          
            if (lista.isEmpty()) {
                rq.setSuccess(false);
                rq.setData(null);
                rq.setMsg("Error no existen datos que guardar");
                return rq;
            }
          

  
           Iterator<ErpConcGastoDTO> it = lista.iterator();
          boolean result = false;
          
          ErpCpConcPago conc = new ErpCpConcPago();
          ErpCpConcPagoId concId = new ErpCpConcPagoId();
            
         

            while (it.hasNext()) {
//                 System.out.println("-------------------------------------------------------------------");
                 ErpConcGastoDTO ss = it.next();
                 
                 concId.setCompania(compania);
                 concId.setConcepto(ss.concepto);
                 conc.setId(concId);
                 conc.setNombre(ss.nombre);
                 
                 erpCpConcPagoDao.save(conc);

                 
               
            }
            
          
            
            if (result == true){
            
                
                    rq.setSuccess(true);
                    rq.setData(null);
                    rq.setMsg("Se Guardo correctamente");
              
                
            }else{
                
                rq.setSuccess(true);
                rq.setData(null);
                rq.setMsg("Error al guardar");
                
            }
            

        }catch(Exception e){
            e.printStackTrace();
            rq.setSuccess(false);
            rq.setData(null);
            rq.setMsg("Error al guardar");
           return rq;
        }
        
        return rq;
    }
    
     @RequestMapping(value = "/quitarConceptosPagos", method = RequestMethod.POST)
    public @ResponseBody
    ResponseQuery quitarConceptosPagos( String data, WebRequest webRequest, Model model) throws IOException {

        ResponseQuery rq = new ResponseQuery();
   //     System.out.println("data factura cancelacion:"+data);
        SimpleDateFormat formatFecha = new SimpleDateFormat("dd/MM/yyyy");
         int index = data.indexOf("[");
        if (index == -1) {
            data = "[" + data + "]";
        }
  
        
            String compania = model.asMap().get("compania").toString();
            String usuario = model.asMap().get("usuario").toString();
            ObjectMapper mapper = new ObjectMapper();
          
                   
        try{
            List<ErpConcGastoDTO> lista = mapper.readValue(data,
                    mapper.getTypeFactory().constructCollectionType(List.class,
                            ErpConcGastoDTO.class));
            
          
            if (lista.isEmpty()) {
                rq.setSuccess(false);
                rq.setData(null);
                rq.setMsg("Error no existen datos que guardar");
                return rq;
            }
          

  
           Iterator<ErpConcGastoDTO> it = lista.iterator();
          boolean result = false;
          
          ErpCpConcPago conc = new ErpCpConcPago();
          ErpCpConcPagoId concId = new ErpCpConcPagoId();
            
         

            while (it.hasNext()) {
//                 System.out.println("-------------------------------------------------------------------");
                 ErpConcGastoDTO ss = it.next();
                 
                 concId.setCompania(compania);
                 concId.setConcepto(ss.concepto);
                 conc.setId(concId);
                 conc.setNombre(ss.nombre);
                 
                 erpCpConcPagoDao.delete(conc);

                 
               
            }
            
          
            
            if (result == true){
            
                
                    rq.setSuccess(true);
                    rq.setData(null);
                    rq.setMsg("Se elimino correctamente");
              
                
            }else{
                
                rq.setSuccess(true);
                rq.setData(null);
                rq.setMsg("Error al eliminar");
                
            }
            

        }catch(Exception e){
            e.printStackTrace();
            rq.setSuccess(false);
            rq.setData(null);
            rq.setMsg("Error al guardar");
           return rq;
        }
        
        return rq;
    }
    
    
    public void descargaFolioNom(String urlC,String urlArchivo, String directorioParcial, String archivoTXT) throws MalformedURLException, IOException, SQLException, ClassNotFoundException {

        List<String> arrList = new ArrayList<String>();
        
        
       
        
        //Recorre la lista de los datos recuperados de la BD, cada ciclo obtiene URL, Directorio y Archivo 
            
            System.out.println("URL: " + urlArchivo);
            URL url = new URL(urlArchivo);
            System.out.println("Directorio: " + directorioParcial);
            System.out.println("Archivo: " + archivoTXT);
            
            System.out.println("Generando url");
            
            HttpURLConnection uc = (HttpURLConnection) url.openConnection();
            //uc.setRequestMethod("POST");
           // uc.setRequestProperty("accept-charset", "UTF-8");
           // uc.setRequestProperty("content-type", "application/x-www-form-urlencoded; charset=utf-8");
         //   uc.setRequestProperty("User-Agent", "Windows NT 6.1; WOW64) AppleWebKit/534+ (KHTML, like Gecko");
            //uc.setDoOutput(true);
//            URLConnection uc = url.openConnection();
//            uc.setRequestProperty("Accept-Charset", "UTF-8");
//            uc.setRequestProperty("Content-Type", "application/json;charset=utf-8");
//            uc.setDoOutput(true);
//            uc.connect();
            
            //Creamos el objeto con el que vamos a leer
            Charset charset = Charset.forName("ISO-8859-1");
            BufferedReader in = new BufferedReader(new InputStreamReader(uc.getInputStream(), charset));
            String inputLine;
            String contenido = "";
            String contenidoUncoding = "";
             StringBuffer response = new StringBuffer();
            System.out.println("Escribiendo Contenido 2");
            while ((inputLine = in.readLine()) != null) {
                
               // System.out.println("inputLine");
               // System.out.println(inputLine);
                
                
                response.append(inputLine);
                contenidoUncoding += inputLine + "\n";
            }
            
            //String text2 = new String(response.toString().getBytes("UTF-8"));
            String text3 = new String(contenidoUncoding.getBytes("UTF-8"));
            
           // contenido = text3.replaceAll("Ã‘", "Ñ");
           
           contenido = contenidoUncoding;
            
            //String line=URLDecoder.decode(text3,"UTF-8");
            in.close();

             //System.out.println("contenidoUncoding: "+contenidoUncoding);
             //System.out.println("contenido: "+contenido);
//            System.out.println(text2);
           // System.out.println("Contenido de append3");
            //System.out.println(contenido);
//            System.out.println("Contenido de append4");
//            System.out.println(line);

            //Directorio que se creara 
            File directorio = new File(urlC + "/" + directorioParcial);
            
            String rutaZIP = directorioParcial.substring(0, directorioParcial.indexOf("/"));
            //Directorio para comprimir 
            File directorioZIP = new File(urlC + "/" +rutaZIP );
 
            System.out.println("Directorio definido " + directorio);
            System.out.println("Validando existencia...");

            if (!directorio.exists()) {
                if (directorio.mkdirs()) {
                    System.out.println("Directorio creado");
                } else {
                    System.out.println("Error al crear directorio");
                }
            } else {
                System.out.println("Directorio ya existente");
            }

            //archivo que se creara 
            File archivo = new File(directorio + "/" + archivoTXT);

            System.out.println("Archivo definido");

            try {
                //Crear un objeto File se encarga de crear o abrir acceso a un archivo que se especifica en su constructor

                System.out.println("validando existencia...");
                if (!archivo.exists()) {

                    System.out.println("archivo no existe");
                    Writer escribir = null;
                    BufferedWriter out = null;

                    escribir = new OutputStreamWriter(new FileOutputStream(archivo), StandardCharsets.UTF_8);

                    //FileWriter escribir=new FileWriter(archivo,true);
                    System.out.println("Escribimos en el archivo con el metodo write ");

                    //Escribimos en el archivo con el metodo write 
                    escribir.write(contenido);

                    System.out.println("Archivo Escrito");

                    //Cerramos la conexion
                    escribir.close();
                    
                    //Se comprime con el metodo comprimir que se definio
                   // comprimir(directorioZIP, urlC, directorioParcial);

                } else {
                    System.out.println("Archivo ya existente");
                   // comprimir(directorioZIP, urlC, directorioParcial);
                    //return 2;
                }

                //Crear objeto FileWriter que sera el que nos ayude a escribir sobre archivo
            } //Si existe un problema al escribir cae aqui
            catch (Exception e) {
                
                System.out.println("Error al escribir");
                e.printStackTrace();

                //return 0;
            }

            FileReader fr = new FileReader(archivo);
            fr.close();
        
   
    }
    
    
      public void comprimir(File directorio, String rutaZIP,String cadena) throws IOException{
        URI base = directorio.toURI();
        Deque<File> queue = new LinkedList<File>();
        queue.push(directorio);
        //String rutaZIP = directorioParcial.substring(0, directorioParcial.indexOf("/"));
        //Salida del directorio comprimido
        FileOutputStream fos = new FileOutputStream(rutaZIP + "/"  + cadena + ".zip");
        Closeable res = fos;
        try {
            ZipOutputStream zos = new ZipOutputStream(fos);
            res = zos;
            while (!queue.isEmpty()) {
                directorio = queue.pop();

                for (File kid : directorio.listFiles()) {
                    String nombre = base.relativize(kid.toURI()).getPath();
                    //Verificar si es subcarpeta
                    if (kid.isDirectory()) {
                        queue.push(kid);
                        nombre = nombre.endsWith("/") ? nombre : nombre + "/";
                        //zos.putNextEntry(new ZipEntry(nombre));
                        
                    } else {
                        System.out.println("Comprimiendo.....");
                        System.out.println(kid);
                        System.out.println(rutaZIP);
                        //Nombre con el que se va guardar el archivo dentro del zip
                        zos.putNextEntry(new ZipEntry( nombre)); 
                        //Archivo para comprimir
                        FileInputStream fis = new FileInputStream(kid);
                        try {
                            byte[] buffer = new byte[1024];
                            int len;
                            while ((len = fis.read(buffer)) > 0) {
                                zos.write(buffer, 0, len);
                            }
                            
                        } finally {
                            fis.close();
                        }
                        zos.closeEntry();
                    }
                }
            }
        } finally {
            res.close();
        }

        System.out.println("Carpeta comprimida: " + rutaZIP + ".zip");
        //System.out.println("Directorio de salida: " + urlC);
        
    }
      
     public String getCadenaAlfanumAleatoria (int longitud){
        String cadenaAleatoria = "";
        long milis = new java.util.GregorianCalendar().getTimeInMillis();
        Random r = new Random(milis);
        int i = 0;
        while ( i < longitud){
        char c = (char)r.nextInt(255);
        if ( (c >= '0' && c<='9') || (c >='A' && c <='Z') ){
        cadenaAleatoria += c;
        i ++;
        }
        }
        return cadenaAleatoria;
      }
    
    
    

    public void setErpCpConcPagoDao(ErpCpConcPagoDao erpCpConcPagoDao) {
        this.erpCpConcPagoDao = erpCpConcPagoDao;
    }
    
    
    
    
   
    public void setErpCxpFoliosDao(ErpCxpFoliosDao erpCxpFoliosDao) {
        this.erpCxpFoliosDao = erpCxpFoliosDao;
    }

    public void setErpFeComprobantesDao(ErpFeComprobantesDao erpFeComprobantesDao) {
        this.erpFeComprobantesDao = erpFeComprobantesDao;
    }

    public void setErpSeguiDocumentosDao(ErpSeguiDocumentosDao erpSeguiDocumentosDao) {
        this.erpSeguiDocumentosDao = erpSeguiDocumentosDao;
    }

    public void setProcessDao(ProcessDao processDao) {
        this.processDao = processDao;
    }

    public void setErpPolizasVSFacturasAutErrDao(ErpPolizasVSFacturasAutErrDao erpPolizasVSFacturasAutErrDao) {
        this.erpPolizasVSFacturasAutErrDao = erpPolizasVSFacturasAutErrDao;
    }

    public void setErpCpOtrasDao(ErpCpOtrasDao erpCpOtrasDao) {
        this.erpCpOtrasDao = erpCpOtrasDao;
    }

    public void setErpCxpFacturasXOtrasDao(ErpCxpFacturasXOtrasDao erpCxpFacturasXOtrasDao) {
        this.erpCxpFacturasXOtrasDao = erpCxpFacturasXOtrasDao;
    }

    public void setErpSatTransaccionDao(ErpSatTransaccionDao erpSatTransaccionDao) {
        this.erpSatTransaccionDao = erpSatTransaccionDao;
    }

    public void setErpProvisionDetDao(ErpProvisionDetDao erpProvisionDetDao) {
        this.erpProvisionDetDao = erpProvisionDetDao;
    }

    public void setErpProvPagoUnicoDao(ErpProvPagoUnicoDao erpProvPagoUnicoDao) {
        this.erpProvPagoUnicoDao = erpProvPagoUnicoDao;
    }

   
    
    
}
