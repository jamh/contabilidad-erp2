/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.feraz.cxp.controll;

/**
 *
 * @author vavi
 */
import com.fasterxml.jackson.databind.ObjectMapper;
import com.feraz.cxp.dto.ServiciosCXPDTO;
import com.feraz.facturas.webcontrolfe.dao1.ErpFeConceptosDao;


import com.feraz.facturas.webcontrolfe.dto.FileInfo;
import com.feraz.facturas.webcontrolfe.model.ErpFeConceptos;
import com.feraz.facturas.webcontrolfe.model.ErpFeConceptosId;

import com.feraz.polizas3.model.FileUploadBean;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import org.jamh.data.process.ProcessDao;
import org.jamh.wf.json.model.ResponseQuery;
import org.jamh.wf.process.Querys;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.multipart.MultipartFile;


@Controller
@RequestMapping("/Servicios")
@SessionAttributes({"compania", "usuario"})
public class ProcessServicios {
    
    private ErpFeConceptosDao erpFeConceptosDao;
    
    
         @RequestMapping(value = "/updateServicio", method = RequestMethod.POST)
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
            List<ServiciosCXPDTO> lista = mapper.readValue(data,
                    mapper.getTypeFactory().constructCollectionType(List.class,
                            ServiciosCXPDTO.class));

            if (lista.isEmpty()) {
                rq.setSuccess(false);
                rq.setData(null);
                rq.setMsg("Error no existen datos que guardar");
                return rq;
            }

  
            Iterator<ServiciosCXPDTO> it = lista.iterator();
           ErpFeConceptos conceptos = new ErpFeConceptos();
           ErpFeConceptosId conceptosId = new ErpFeConceptosId();
          
          
            int banderaGe = 0 ;
            while (it.hasNext()) {
//                 System.out.println("-------------------------------------------------------------------");
                 ServiciosCXPDTO ss = it.next();
                 
                 conceptosId.setCompania(compania);
                 conceptosId.setNumero(Integer.parseInt(ss.numero));
                 conceptosId.setSec(Integer.parseInt(ss.sec));
                 conceptos.setId(conceptosId);
                 if(!ss.idProductxServicio.equalsIgnoreCase("")){
                 erpFeConceptosDao.updateConceptos(conceptosId, ss.idProductxServicio);
                 }
                 
            }
            
          
            
            if (banderaGe == 1){
            
                
                    rq.setSuccess(true);
                    rq.setData(null);
                    rq.setMsg("Registros guardados correctamente");
              
                
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

    public void setErpFeConceptosDao(ErpFeConceptosDao erpFeConceptosDao) {
        this.erpFeConceptosDao = erpFeConceptosDao;
    }
    
    
    
}
