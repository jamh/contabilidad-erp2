package com.feraz.polizas3.controll;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.feraz.contabilidad.sat.electronica.process.GeneraContaElectronica;
import com.feraz.contabilidad.sat.transacciones.dao.DetPolizasXTransaccionDao;
import com.feraz.contabilidad.sat.transacciones.dao.ErpSatTransaccionDao;
import com.feraz.contabilidad.sat.transacciones.model.DetPolizasXTransaccion;
import com.feraz.contabilidad.sat.transacciones.model.DetPolizasXTransaccionId;
import com.feraz.contabilidad.sat.transacciones.model.ErpSatTransaccion;
import com.feraz.contabilidad.sat.transacciones.model.ErpSatTransaccionId;
import com.feraz.polizas3.dao.ErpPolizasXFacturasDao;
import com.feraz.polizas3.dao.ErpPolizasXPagosDao;
import com.feraz.polizas3.dao.OtrasArchivosDao;
import com.feraz.polizas3.dao.PolizasArchivosDao;
import com.feraz.polizas3.dto.OtrasDTO;
import com.feraz.polizas3.dto.PolizasArchivosDTO;
import com.feraz.polizas3.dto.RelacionFacturasDTO;
import com.feraz.polizas3.model.ErpPolizasXFacturas;
import com.feraz.polizas3.model.ErpPolizasXFacturasId;
import com.feraz.polizas3.model.ErpPolizasXPagos;
import com.feraz.polizas3.model.ErpPolizasXPagosId;
import com.feraz.polizas3.model.ExtJSFormResult;
import com.feraz.polizas3.model.FileUploadBean;
import com.feraz.polizas3.model.OtrasArchivos;
import com.feraz.polizas3.model.OtrasArchivosId;
import com.feraz.polizas3.model.PolizasArchivos;
import com.feraz.polizas3.model.PolizasArchivosId;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
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
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
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
@RequestMapping(value = "/UploadDocument")
@SessionAttributes({"compania", "usuario"})
public class UploadDocumentController {

    @Value("${document.file.dirOutDocument}")
    private String dirOutFileDocument;
    @Value("${document.file.dirURLOutDocument}")
    private String dirUrlOutDocument;
    
    @Value("${document.file.dirOutDocumentOtras}")
    private String dirOutFileDocumentOtras;
    @Value("${document.file.dirURLOutDocumentOtras}")
    private String dirUrlOutDocumentOtras;
    
    @Value("${document.file.maxZise}")
    private String maxSizeDocument;
    private ProcessDao processDao;
    private PolizasArchivosDao polizasArchivosDao;
    private ErpPolizasXFacturasDao erpPolizasXFacturasDao;
    
     private ErpPolizasXPagosDao erpPolizasXPagosDao;
    
    private ErpSatTransaccionDao erpSatTransaccionDao;
    private DetPolizasXTransaccionDao detPolizasXTransaccionDao;
    private OtrasArchivosDao otrasArchivosDao;
    private GeneraContaElectronica generaContaElectronica;

    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public @ResponseBody
    String create(@RequestParam("archCOMPANIA2") String compania,
            @RequestParam("archTIPO_POLIZA2") String tipoPoliza,
            @RequestParam("archFECHA2") String fecha,
            @RequestParam("archNUMERO2") String numero,
            @RequestParam("archSEC2") String sec,
            @RequestParam("archNOMBRE2") String nombre,
            @RequestParam("archDESCRIPCION2") String descripcion,
            FileUploadBean uploadItem,
            BindingResult result, WebRequest webRequest, Model model) {
        
        System.out.println("compania"+compania);
        System.out.println("uploadItem"+uploadItem);
        
        boolean isSave = true;
        SimpleDateFormat formatoDelTexto2 = new SimpleDateFormat("dd/MM/yyyy");
        PolizasArchivos polizasArchivos = new PolizasArchivos();
        PolizasArchivosId polizasArchivosId = new PolizasArchivosId();           
        Date fechaArchivo = null;
               
        ExtJSFormResult extjsFormResult = new ExtJSFormResult();
        
        if (model.asMap().get("usuario") == null) {

            isSave = false;
            extjsFormResult.setMessage("Sesion no valida");
            extjsFormResult.setSuccess(isSave);
            return extjsFormResult.toString();
        }

        if (result.hasErrors()) {
            for (ObjectError error : result.getAllErrors()) {
                System.err.println("Error: " + error.getCode() + " - " + error.getDefaultMessage());
            }
            extjsFormResult.setSuccess(isSave);
            extjsFormResult.setMessage("Error al guardar el archivo.");

            return extjsFormResult.toString();
        }
        

//        String fileName = null;
//        String nombreArchivo="";
        PolizasArchivosId idDoc = null;

        try {
            MultipartFile file = uploadItem.getFile();

            InputStream inputStream = null;
            OutputStream outputStream = null;
            if (file.getSize() > 0) {
                inputStream = file.getInputStream();
                long maxSize = new Long(maxSizeDocument);
                if (file.getSize() > maxSize) {
                    //System.out.println("File Size:::" + file.getSize());
                    isSave = false;
                    extjsFormResult.setMessage("Tamaño no valido");
                    extjsFormResult.setSuccess(isSave);
                    return extjsFormResult.toString();
                }

                int i = file.getOriginalFilename().lastIndexOf('.');
                
//                System.out.println("Nombre:" + file.getOriginalFilename());
                String extension = "";
                String nombreArc ="";
               
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
                String path =dirOutFileDocument+nombreArc+hora+"."+extension;
                polizasArchivos.setPath(path);
                String url =dirUrlOutDocument+nombreArc+hora+"."+extension;
                polizasArchivos.setUrl(url);
            
                outputStream = new FileOutputStream(path);

                int readBytes = 0;
                byte[] buffer = new byte[10000];
                while ((readBytes = inputStream.read(buffer, 0, 10000)) != -1) {
                    outputStream.write(buffer, 0, readBytes);
                }
                outputStream.close();
                inputStream.close();
            }


            
            
            polizasArchivosId.setCompania(compania);
            polizasArchivosId.setTipoPoliza(tipoPoliza);
            fechaArchivo = formatoDelTexto2.parse(fecha);
            polizasArchivosId.setFecha(fechaArchivo);
            polizasArchivosId.setNumero(numero);
            polizasArchivos.setNombre(nombre);
            polizasArchivos.setDescripcion(descripcion);
           
            
            int secMax = polizasArchivosDao.getMaxId(polizasArchivosId);
            
            polizasArchivosId.setSec(new BigDecimal(secMax));
            
            polizasArchivos.setId(polizasArchivosId);
            
            PolizasArchivosId idDoc2 = null;
            
            idDoc2 = polizasArchivosDao.save(polizasArchivos);
            if (idDoc2 == null) {
                isSave = false;
                extjsFormResult.setMessage("Error al guardar los datos");
                    extjsFormResult.setSuccess(isSave);
                return extjsFormResult.toString();
            } else {
                isSave = true;
                extjsFormResult.setMessage("Archivo Guardado");
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
    
    @RequestMapping(value = "/delete", method = RequestMethod.POST)
    public @ResponseBody
    ResponseQuery deleteDocumentos(String data, WebRequest webRequest, Model model) {
        boolean isSave = false;
         SimpleDateFormat formatoDelTexto2 = new SimpleDateFormat("dd/MM/yyyy");
         Date fechaArchivo = null;
        Map parametros = processDao.paramToMap(webRequest.getParameterMap());
        parametros.put("compania", model.asMap().get("compania"));
        parametros.put("usuario", model.asMap().get("usuario"));

        ResponseQuery rq = new ResponseQuery();

        if (model.asMap().get("usuario") == null) {

            rq.setSuccess(false);
            rq.setData(null);
            rq.setMsg("session invalid");
            return rq;
        }
        
        
      

        PolizasArchivosDTO documento = null;
        try {
            ObjectMapper mapper = new ObjectMapper();
            DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
            mapper.setDateFormat(df);

            documento = mapper.readValue(data, PolizasArchivosDTO.class);
            
            PolizasArchivosId polizasId = new PolizasArchivosId();
             PolizasArchivos polizas = new PolizasArchivos();
            polizasId.setCompania(documento.compania);
            fechaArchivo = formatoDelTexto2.parse(documento.fecha);
            polizasId.setFecha(fechaArchivo);
            polizasId.setNumero(documento.numero);
            polizasId.setSec(new BigDecimal(documento.sec));
            polizasId.setTipoPoliza(documento.tipoPoliza);
            polizas.setId(polizasId);
            

                    isSave = polizasArchivosDao.delete(polizas);
        } catch (Exception e) {
            isSave = false;
            e.printStackTrace();
        }

        if (isSave) {
            List lista = new ArrayList();
            lista.add(documento);
            rq.setSuccess(true);
            rq.setData(lista);
            rq.setMsg("Archivo Borrado");
        } else {
            rq.setSuccess(false);
            rq.setData(null);
            rq.setMsg("Error al borrar");
        }

        return rq;
    }
    
        @RequestMapping(value = "/deleteOtras", method = RequestMethod.POST)
    public @ResponseBody
    ResponseQuery deleteOtras(String data, WebRequest webRequest, Model model) {
        boolean isSave = false;
         SimpleDateFormat formatoDelTexto2 = new SimpleDateFormat("dd/MM/yyyy");
         Date fechaArchivo = null;
        Map parametros = processDao.paramToMap(webRequest.getParameterMap());
        parametros.put("compania", model.asMap().get("compania"));
        parametros.put("usuario", model.asMap().get("usuario"));

        ResponseQuery rq = new ResponseQuery();

        if (model.asMap().get("usuario") == null) {

            rq.setSuccess(false);
            rq.setData(null);
            rq.setMsg("session invalid");
            return rq;
        }
        
        
      

        OtrasDTO documento = null;
        try {
            ObjectMapper mapper = new ObjectMapper();
            DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
            mapper.setDateFormat(df);

            documento = mapper.readValue(data, OtrasDTO.class);
            
            OtrasArchivosId otrasId = new OtrasArchivosId();
            OtrasArchivos otras = new OtrasArchivos();
            otrasId.setCompania(documento.compania);
            otrasId.setId(Integer.parseInt(documento.id));
          
            otrasId.setSec(Integer.parseInt(documento.sec));
            
            otras.setId(otrasId);
            

                    isSave = otrasArchivosDao.delete(otras);
        } catch (Exception e) {
            isSave = false;
            e.printStackTrace();
        }

        if (isSave) {
            List lista = new ArrayList();
            lista.add(documento);
            rq.setSuccess(true);
            rq.setData(lista);
            rq.setMsg("Archivo Borrado");
        } else {
            rq.setSuccess(false);
            rq.setData(null);
            rq.setMsg("Error al borrar");
        }

        return rq;
    }
    
    
    
     @RequestMapping(value = "/relacionaFacturas", method = RequestMethod.POST)
    public @ResponseBody
    ResponseQuery RelacionaDoc(String data,@RequestParam("compania") String compania, 
    @RequestParam("tipoPoliza") String tipoPoliza,
    @RequestParam("fecha") String fecha,
    @RequestParam("numeroPol") String numeroPol  , WebRequest webRequest, Model model) {
        boolean isSave = false;
         SimpleDateFormat formatoDelTexto2 = new SimpleDateFormat("dd/MM/yyyy");
         Date fechaArchivo = null;
      
        ResponseQuery rq = new ResponseQuery();

       
        
       Map parametros = processDao.paramToMap(webRequest.getParameterMap());
//        parametros.put("compania", model.asMap().get("compania"));
//        parametros.put("usuario", model.asMap().get("usuario"));
       
      
       
        try {
            
            
         ObjectMapper mapper = new ObjectMapper();  
             DateFormat df = new SimpleDateFormat("dd/MM/yyyy");

            mapper.setDateFormat(df);
            
            List<RelacionFacturasDTO> lista = mapper.readValue(data,
                    mapper.getTypeFactory().constructCollectionType(List.class,
                            RelacionFacturasDTO.class));
        Iterator<RelacionFacturasDTO> it = lista.iterator();
        
                 

                    while(it.hasNext()){
                                RelacionFacturasDTO ss = it.next();

                                       System.out.println("generando relacion");
                        boolean resultContaElect = generaContaElectronica.generaRelacion(compania, Integer.parseInt(ss.numeroFe), numeroPol, fecha, tipoPoliza);
                        System.out.println(resultContaElect);
                        if(resultContaElect = true){

                             rq.setSuccess(true);
                             rq.setMsg("Factura cargada y relacionada correctamente");


                        }else{


                             rq.setSuccess(false);
                             rq.setMsg("Factura cargada. Existio error al relacionar la poliza");

                        }

                      
                 
               
               
                
                
               }
        } catch (Exception e) {
            isSave = false;
            e.printStackTrace();
        }

       

        return rq;
    }
      @RequestMapping(value = "/borraRelacionaFacturas", method = RequestMethod.POST)
    public @ResponseBody
    ResponseQuery BorraRelacionaDoc(String data,@RequestParam("compania") String compania, 
    @RequestParam("tipoPoliza") String tipoPoliza,
    @RequestParam("fecha") String fecha,
    @RequestParam("numeroPol") String numeroPol  , WebRequest webRequest, Model model) {
        boolean isSave = false;
         SimpleDateFormat formatoDelTexto2 = new SimpleDateFormat("dd/MM/yyyy");
         Date fechaArchivo = null;
      
        ResponseQuery rq = new ResponseQuery();

       
        
       Map parametros = processDao.paramToMap(webRequest.getParameterMap());
//        parametros.put("compania", model.asMap().get("compania"));
//        parametros.put("usuario", model.asMap().get("usuario"));
       
      
       
        try {
            
            
         ObjectMapper mapper = new ObjectMapper();  
             DateFormat df = new SimpleDateFormat("dd/MM/yyyy");

            mapper.setDateFormat(df);
            
            List<RelacionFacturasDTO> lista = mapper.readValue(data,
                    mapper.getTypeFactory().constructCollectionType(List.class,
                            RelacionFacturasDTO.class));
        Iterator<RelacionFacturasDTO> it = lista.iterator();
        
                  Map tipoPolSatMap = new HashMap();
              //String rfcComp = "";
              tipoPolSatMap.put("compania", compania);
              tipoPolSatMap.put("tipoPoliza", tipoPoliza);
                    
              List listTipoPolSat= processDao.getMapResult("BuscaTipoPolSat", tipoPolSatMap);
                  Map secTipoPolSat = (HashMap) listTipoPolSat.get(0);
                   
                 String tipoSat = secTipoPolSat.get("TIPO_SAT").toString();
        
                 ErpPolizasXFacturas polizas = new ErpPolizasXFacturas();
                 ErpPolizasXFacturasId polizasId = new ErpPolizasXFacturasId();
        
                 ErpPolizasXPagos polizasPagos = new ErpPolizasXPagos();
                 ErpPolizasXPagosId polizasPagosId = new ErpPolizasXPagosId();
                 
                  boolean result = true;

               while(it.hasNext()){
                    RelacionFacturasDTO ss = it.next();
                 if(tipoSat.equalsIgnoreCase("I") || tipoSat.equalsIgnoreCase("E")){
                        polizasPagosId.setCompania(compania);
                        polizasPagosId.setFechaPol(formatoDelTexto2.parse(fecha));
                        polizasPagosId.setNumeroPol(numeroPol);
                        polizasPagosId.setTipoPol(tipoPoliza);
                        polizasPagos.setId(polizasPagosId);
                        polizasPagos.setFolio(ss.folio);
                        polizasPagos.setNumeroFe(ss.numeroFe);
                        polizasPagos.setUuid(ss.uuid);
                        polizasPagos.setOperacion("GE");



                       result = erpPolizasXPagosDao.deleteRelacion(polizasPagos);
                       
               }else{
                 
                        polizasId.setCompania(compania);
                        polizasId.setFechaPol(formatoDelTexto2.parse(fecha));
                        polizasId.setNumeroPol(numeroPol);
                        polizasId.setOperacion("GE");
                        polizasId.setTipoPol(tipoPoliza);
                        polizas.setId(polizasId);
                        polizas.setFolio(ss.folio);
                        polizas.setNumeroFe(Long.parseLong(ss.numeroFe));
                        polizas.setUuid(ss.uuid);



                       result = erpPolizasXFacturasDao.deleteRelacion(polizas);
                 }
                             
              Map IdTransaccion = new HashMap();
              //String rfcComp = "";
              IdTransaccion.put("compania", compania);
              IdTransaccion.put("uuid", ss.uuid);
               
              List listIdTransaccion = processDao.getMapResult("BuscaIDTransaccion", IdTransaccion);
              
              if (listIdTransaccion != null && !listIdTransaccion.isEmpty()){
                  
                   Map idTransaccionUuid = (HashMap) listIdTransaccion.get(0);
                  
                   ErpSatTransaccion erpTrans = new ErpSatTransaccion();
                   ErpSatTransaccionId erpTransId = new ErpSatTransaccionId();
              
                    erpTransId.setCompania(compania);
                    erpTransId.setId(new BigDecimal(idTransaccionUuid.get("ID").toString()));
                    erpTrans.setId(erpTransId);

                    erpSatTransaccionDao.delete(erpTrans);
                    
                    
                    Map secDetPol = new HashMap();
              //String rfcComp = "";
              secDetPol.put("compania", compania);
              secDetPol.put("tipo_poliza", tipoPoliza);
              secDetPol.put("fecha", fecha);
              secDetPol.put("numero", numeroPol);
              
               
              List listsecDetPol = processDao.getMapResult("BuscaSecDetPoliza", secDetPol);
              
           DetPolizasXTransaccion transaccion = new DetPolizasXTransaccion();
           DetPolizasXTransaccionId transaccionId = new DetPolizasXTransaccionId();
              
              for (int j = 0; listsecDetPol.size()-1>= j;j++){
                  
                  Map secDetPolS = (HashMap) listsecDetPol.get(j);
                   
                 System.out.println("sedc: "+secDetPolS.get("SEC").toString());
                 
                  transaccionId.setCompania(compania);
                    transaccionId.setFecha(formatoDelTexto2.parse(fecha));
                    transaccionId.setTipoPoliza(tipoPoliza);
                    transaccionId.setNumero(numeroPol);
                    transaccionId.setSec(Integer.parseInt(secDetPolS.get("SEC").toString()));
                    transaccionId.setIdTransaccion(Integer.parseInt(idTransaccionUuid.get("ID").toString()));
                    transaccion.setId(transaccionId);

                    
                    System.out.println("borrando relacion");
                    detPolizasXTransaccionDao.borraTransaccionXPolizaCompl(transaccion);
            
              }
              
              }
               
                 if (result == false) {
            
                    rq.setSuccess(false);
                    rq.setData(null);
                    rq.setMsg("Error al quitar la relacion");
                    
                } else {
                    rq.setSuccess(true);
                    rq.setData(null);
                    rq.setMsg("Se quito la relacion correctamente");
                    
                }
                
                
               }
        } catch (Exception e) {
            isSave = false;
            e.printStackTrace();
        }

       

        return rq;
    }
    
    
    
    public boolean isDeleteFile(String dir)
    {	
    	try{
            
//            System.out.println("Dir:"+dir);
 
    		File file = new File(dir);
                if(!file.exists()) return true;
    		if(file.delete())return true;
                else return false;
 
    	}catch(Exception e){
 
    		return false;
 
    	}
 
    }
    
     @RequestMapping(value = "/saveOtras", method = RequestMethod.POST)
    public @ResponseBody
    String createOtras(@RequestParam("archCOMPANIAOtras") String compania,
            @RequestParam("archIDOtras") String id,
         //   @RequestParam("archSecOtras") String sec,
            @RequestParam("archNOMBREOtras") String nombre,
            @RequestParam("archDESCRIPCIONOtras") String descripcion,
            @RequestParam("archTIPOOtras") String tipo,
            FileUploadBean uploadItem,
            BindingResult result, WebRequest webRequest, Model model) {
        
        System.out.println("compania"+compania);
        System.out.println("uploadItem"+uploadItem);
        
        boolean isSave = true;
        SimpleDateFormat formatoDelTexto2 = new SimpleDateFormat("dd/MM/yyyy");
        OtrasArchivos otrasArchivos = new OtrasArchivos();
        OtrasArchivosId otrasArchivosId = new OtrasArchivosId();           
        Date fechaArchivo = null;
               
        ExtJSFormResult extjsFormResult = new ExtJSFormResult();
        
        if (model.asMap().get("usuario") == null) {

            isSave = false;
            extjsFormResult.setMessage("Sesion no valida");
            extjsFormResult.setSuccess(isSave);
            return extjsFormResult.toString();
        }

        if (result.hasErrors()) {
            for (ObjectError error : result.getAllErrors()) {
                System.err.println("Error: " + error.getCode() + " - " + error.getDefaultMessage());
            }
            extjsFormResult.setSuccess(isSave);
            extjsFormResult.setMessage("Error al guardar el archivo.");

            return extjsFormResult.toString();
        }
        

//        String fileName = null;
//        String nombreArchivo="";
      //  PolizasArchivosId idDoc = null;

        try {
            MultipartFile file = uploadItem.getFile();

            InputStream inputStream = null;
            OutputStream outputStream = null;
            if (file.getSize() > 0) {
                inputStream = file.getInputStream();
                long maxSize = new Long(maxSizeDocument);
                if (file.getSize() > maxSize) {
                    //System.out.println("File Size:::" + file.getSize());
                    isSave = false;
                    extjsFormResult.setMessage("Tamaño no valido");
                    extjsFormResult.setSuccess(isSave);
                    return extjsFormResult.toString();
                }

                int i = file.getOriginalFilename().lastIndexOf('.');
                
//                System.out.println("Nombre:" + file.getOriginalFilename());
                String extension = "";
                String nombreArc ="";
               
                    extension = file.getOriginalFilename().substring(i + 1);
                    nombreArc = file.getOriginalFilename().substring(0 , i);
                    
                    if (nombreArc.indexOf("#") > 0){
                        
                        
                        extjsFormResult.setMessage("Error: El nombre del archivo no puede contener el caracter #");
                        extjsFormResult.setSuccess(false);
                        return extjsFormResult.toString();
                    
                    }
                    
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
                
                	   File fileVerifica = new File(dirOutFileDocumentOtras + compania + "/");
				//        System.out.println("Directory" + dirOutFileDocument + dirCompania + "/");
						if (!fileVerifica.exists()) {
							if (fileVerifica.mkdirs()) {
				//                System.out.println("Directory is created!");
							} else {
				//                System.out.println("Failed to create directory!");
							}
						}
                
                
                String path =dirOutFileDocumentOtras +  compania + "/"+nombreArc+hora+"."+extension;
                otrasArchivos.setPath(path);
                String url =dirUrlOutDocumentOtras +  compania + "/"+nombreArc+hora+"."+extension;
                otrasArchivos.setUrl(url);
            
                outputStream = new FileOutputStream(path);

                int readBytes = 0;
                byte[] buffer = new byte[10000];
                while ((readBytes = inputStream.read(buffer, 0, 10000)) != -1) {
                    outputStream.write(buffer, 0, readBytes);
                }
                outputStream.close();
                inputStream.close();
            }


            
            
            otrasArchivosId.setCompania(compania);
            otrasArchivosId.setId(Integer.parseInt(id));
         //   otrasArchivosId.setSec(Integer.parseInt(sec));
            otrasArchivos.setNombre(nombre);
            otrasArchivos.setDescripcion(descripcion);
            otrasArchivos.setTipo(tipo);
           
            
            int secMax = otrasArchivosDao.getMaxId(otrasArchivosId);
            
            otrasArchivosId.setSec(secMax);
            
            otrasArchivos.setId(otrasArchivosId);
            
            OtrasArchivosId idDoc2 = null;
            
            idDoc2 = otrasArchivosDao.save(otrasArchivos);
            if (idDoc2 == null) {
                isSave = false;
                extjsFormResult.setMessage("Error al guardar los datos");
                    extjsFormResult.setSuccess(isSave);
                return extjsFormResult.toString();
            } else {
                isSave = true;
                extjsFormResult.setMessage("Archivo Guardado");
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

    public String getDirOutFileDocument() {
        return dirOutFileDocument;
    }

    public void setDirOutFileDocument(String dirOutFileDocument) {
        this.dirOutFileDocument = dirOutFileDocument;
    }

    public String getDirUrlOutDocument() {
        return dirUrlOutDocument;
    }

    public void setDirUrlOutDocument(String dirUrlOutDocument) {
        this.dirUrlOutDocument = dirUrlOutDocument;
    }

    public String getMaxSizeDocument() {
        return maxSizeDocument;
    }

    public void setMaxSizeDocument(String maxSizeDocument) {
        this.maxSizeDocument = maxSizeDocument;
    }

    public ProcessDao getProcessDao() {
        return processDao;
    }

    public void setProcessDao(ProcessDao processDao) {
        this.processDao = processDao;
    }

    public PolizasArchivosDao getpolizasArchivosDao() {
        return polizasArchivosDao;
    }

    public void setpolizasArchivosDao(PolizasArchivosDao polizasArchivosDao) {
        this.polizasArchivosDao = polizasArchivosDao;
    }

    public void setErpPolizasXFacturasDao(ErpPolizasXFacturasDao erpPolizasXFacturasDao) {
        this.erpPolizasXFacturasDao = erpPolizasXFacturasDao;
    }

    public void setErpSatTransaccionDao(ErpSatTransaccionDao erpSatTransaccionDao) {
        this.erpSatTransaccionDao = erpSatTransaccionDao;
    }

    public void setDetPolizasXTransaccionDao(DetPolizasXTransaccionDao detPolizasXTransaccionDao) {
        this.detPolizasXTransaccionDao = detPolizasXTransaccionDao;
    }

    public void setOtrasArchivosDao(OtrasArchivosDao otrasArchivosDao) {
        this.otrasArchivosDao = otrasArchivosDao;
    }

    public void setErpPolizasXPagosDao(ErpPolizasXPagosDao erpPolizasXPagosDao) {
        this.erpPolizasXPagosDao = erpPolizasXPagosDao;
    }

    public void setGeneraContaElectronica(GeneraContaElectronica generaContaElectronica) {
        this.generaContaElectronica = generaContaElectronica;
    }

    
   
    
}
