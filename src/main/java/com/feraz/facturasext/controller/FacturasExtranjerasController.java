/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.feraz.facturasext.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.feraz.facturasext.dao.ErpExtFacturasDao;
import com.feraz.facturasext.dao.ErpExtFacturasMasterDao;
import com.feraz.facturasext.dto.ErpExtFacturasDto;
import com.feraz.facturasext.dto.ErpExtFacturasMasterDto;
import com.feraz.facturasext.model.ErpExtFacturas;
import com.feraz.facturasext.model.ErpExtFacturasId;
import com.feraz.facturasext.model.ErpExtFacturasMaster;
import com.feraz.facturasext.model.ErpExtFacturasMasterId;
import com.feraz.polizas3.dao.DetPolizasDao;
import com.feraz.polizas3.dao.PolizasDao;
import com.feraz.polizas3.model.DetPolizas;
import com.feraz.polizas3.model.DetPolizasId;
import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Iterator;
import java.util.List;
import org.jamh.wf.json.model.ResponseQuery2;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.context.request.WebRequest;
import com.feraz.polizas3.model.Polizas;
import com.feraz.polizas3.model.PolizasId;
import com.feraz.polizas3.validation.MaestroValidation;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import org.jamh.data.process.ProcessDao;

/**
 *
 * @author Ing. David Ortiz García
 */

@Controller
@RequestMapping("/facturasExtranjeras")
@SessionAttributes({"compania", "usuario"})
public class FacturasExtranjerasController {
    
    private ErpExtFacturasDao erpExtFacturasDao;
    private ErpExtFacturasMasterDao erpExtFacturasMasterDao;
    private PolizasDao polizasDao;
    private MaestroValidation maestroValidation;
    private DetPolizasDao detPolizasDao;
    private ProcessDao processDao;
    
    @RequestMapping(value={"/insert"}, method={org.springframework.web.bind.annotation.RequestMethod.POST})
  @ResponseBody
  public ResponseQuery2 insertAction(String data, WebRequest webRequest, Model model)
    throws ParseException
  {
    ResponseQuery2 rq = new ResponseQuery2();
    int index = data.indexOf("[");
    if (index == -1) {
      data = "[" + data + "]";
    }
    System.out.println("data:" + data);
    if (model.asMap().get("compania") == null)
    {
      rq.setSuccess(false);
      rq.setData(null);
      rq.setMsg("La sesion no es valida.");
      return rq;
    }
    String compania = model.asMap().get("compania").toString();
    String usuario = model.asMap().get("usuario").toString();
    try
    {
      int guardado = 0;
      
      ObjectMapper mapper = new ObjectMapper();
      DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
      
      mapper.setDateFormat(df);
      List<ErpExtFacturasDto> lista = (List)mapper.readValue(data, mapper
        .getTypeFactory().constructCollectionType(List.class, ErpExtFacturasDto.class));
      if (lista.isEmpty())
      {
        rq.setSuccess(false);
        rq.setData(null);
        rq.setMsg("Error no existen datos que guardar");
        return rq;
      }
      ErpExtFacturas facturas = new ErpExtFacturas();
      ErpExtFacturasId facturasId = new ErpExtFacturasId();
      

      int val = 0;
      Iterator<ErpExtFacturasDto> it = lista.iterator();
      
      ErpExtFacturasId result = null;
      while (it.hasNext())
      {
        ErpExtFacturasDto ss = (ErpExtFacturasDto)it.next();
        
        facturasId.setCompania(compania);
        facturasId.setInvoiceNo(ss.invoice);
        int id = erpExtFacturasDao.getMaxErpExtFacturas(facturasId);
        facturasId.setSec(id);
        facturas.setId(facturasId);
        
        if (!ss.invoiceDate.equalsIgnoreCase("")) {
            facturas.setInvoceDate(df.parse(ss.invoiceDate));
        }
        
        facturas.setCustomer(ss.customer);
        facturas.setCustomerPo(ss.customerPo);
        
        if (!ss.dueDate.equalsIgnoreCase("")) {
            facturas.setDueDate(df.parse(ss.dueDate));
        }
        
        facturas.setItem(ss.item);
        facturas.setLineSubtotal(new BigDecimal(ss.lineSubtotal));
        facturas.setLineTax(new BigDecimal(ss.lineTax));
        facturas.setLineTotal(new BigDecimal(ss.lineTotal));
        facturas.setNotes1(ss.notes1);
        facturas.setNotes2(ss.notes2);
        facturas.setOperativeWeek(ss.operativeWeek);
        facturas.setPrice(new BigDecimal(ss.price));
        facturas.setQuantity(Integer.parseInt(ss.quantity));
        facturas.setTax(ss.tax);
        facturas.setTaxImp(new BigDecimal(ss.taxImp));
        facturas.setTerms(Integer.parseInt(ss.terms));
        facturas.setCuenta(ss.cuenta);
        
       
        
        result = erpExtFacturasDao.save(facturas);
        
        
        
      }
      if (result != null)
      {
        rq.setSuccess(true);
        

        rq.setMsg("Guardados Correctamente");
      }
      else
      {
        rq.setSuccess(false);
        rq.setData(null);
        
        rq.setMsg("Error al guardar");
      }
    }
    catch (Exception e)
    {
      e.printStackTrace();
    }
    return rq;
  }
  
  @RequestMapping(value={"/update"}, method={org.springframework.web.bind.annotation.RequestMethod.POST})
  @ResponseBody
  public ResponseQuery2 updateAction(String data, WebRequest webRequest, Model model)
    throws ParseException
  {
    ResponseQuery2 rq = new ResponseQuery2();
    int index = data.indexOf("[");
    if (index == -1) {
      data = "[" + data + "]";
    }
    System.out.println("data:" + data);
    if (model.asMap().get("compania") == null)
    {
      rq.setSuccess(false);
      rq.setData(null);
      rq.setMsg("La sesion no es valida.");
      return rq;
    }
    String compania = model.asMap().get("compania").toString();
    String usuario = model.asMap().get("usuario").toString();
    try
    {
      int guardado = 0;
      
      ObjectMapper mapper = new ObjectMapper();
      DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
      
      mapper.setDateFormat(df);
      List<ErpExtFacturasDto> lista = (List)mapper.readValue(data, mapper
        .getTypeFactory().constructCollectionType(List.class, ErpExtFacturasDto.class));
      if (lista.isEmpty())
      {
        rq.setSuccess(false);
        rq.setData(null);
        rq.setMsg("Error no existen datos que guardar");
        return rq;
      }
      ErpExtFacturas facturas = new ErpExtFacturas();
      ErpExtFacturasId facturasId = new ErpExtFacturasId();
      

      int val = 0;
      Iterator<ErpExtFacturasDto> it = lista.iterator();
      
      Boolean result = false;
      while (it.hasNext())
      {
        ErpExtFacturasDto ss = (ErpExtFacturasDto)it.next();
        
        facturasId.setCompania(compania);
        facturasId.setInvoiceNo(ss.invoice);
        facturasId.setSec(Integer.parseInt(ss.sec));
        facturas.setId(facturasId);
        
        if (!ss.invoiceDate.equalsIgnoreCase("")) {
            facturas.setInvoceDate(df.parse(ss.invoiceDate));
        }
        
        facturas.setCustomer(ss.customer);
        facturas.setCustomerPo(ss.customerPo);
        
        if (!ss.dueDate.equalsIgnoreCase("")) {
            facturas.setDueDate(df.parse(ss.dueDate));
        }
        
        facturas.setItem(ss.item);
        facturas.setLineSubtotal(new BigDecimal(ss.lineSubtotal));
        facturas.setLineTax(new BigDecimal(ss.lineTax));
        facturas.setLineTotal(new BigDecimal(ss.lineTotal));
        facturas.setNotes1(ss.notes1);
        facturas.setNotes2(ss.notes2);
        facturas.setOperativeWeek(ss.operativeWeek);
        facturas.setPrice(new BigDecimal(ss.price));
        facturas.setQuantity(Integer.parseInt(ss.quantity));
        facturas.setTax(ss.tax);
        facturas.setTaxImp(new BigDecimal(ss.taxImp));
        facturas.setTerms(Integer.parseInt(ss.terms));
        facturas.setCuenta(ss.cuenta);
        
       
        
        result = erpExtFacturasDao.update(facturas);
        
        
        
      }
      if (result == true)
      {
        rq.setSuccess(true);
        

        rq.setMsg("Guardados Correctamente");
      }
      else
      {
        rq.setSuccess(false);
        rq.setData(null);
        
        rq.setMsg("Error al guardar");
      }
    }
    catch (Exception e)
    {
      e.printStackTrace();
    }
    return rq;
  }
  
  
  @RequestMapping(value={"/delete"}, method={org.springframework.web.bind.annotation.RequestMethod.POST})
  @ResponseBody
  public ResponseQuery2 deleteAction(String data, WebRequest webRequest, Model model)
    throws ParseException
  {
    ResponseQuery2 rq = new ResponseQuery2();
    int index = data.indexOf("[");
    if (index == -1) {
      data = "[" + data + "]";
    }
    if (model.asMap().get("compania") == null)
    {
      rq.setSuccess(false);
      rq.setData(null);
      rq.setMsg("La sesion no es valida.");
      return rq;
    }
    String compania = model.asMap().get("compania").toString();
    String usuario = model.asMap().get("usuario").toString();
    try
    {
      
      ObjectMapper mapper = new ObjectMapper();
      DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
      
      mapper.setDateFormat(df);
      List<ErpExtFacturasDto> lista = (List)mapper.readValue(data, mapper
        .getTypeFactory().constructCollectionType(List.class, ErpExtFacturasDto.class));
      if (lista.isEmpty())
      {
        rq.setSuccess(false);
        rq.setData(null);
        rq.setMsg("Error no existen datos que guardar");
        return rq;
      }
      ErpExtFacturas facturas = new ErpExtFacturas();
      ErpExtFacturasId facturasId = new ErpExtFacturasId();
      
      
      int val = 0;
      Iterator<ErpExtFacturasDto> it = lista.iterator();
      
      boolean result = false;
      while (it.hasNext())
      {
        ErpExtFacturasDto ss = (ErpExtFacturasDto)it.next();
        
        facturasId.setCompania(compania);
        facturasId.setInvoiceNo(ss.invoice);
        facturasId.setSec(Integer.parseInt(ss.sec));
        facturas.setId(facturasId);
      


        result = this.erpExtFacturasDao.delete(facturas);
         
        
      }
      if (result == true)
      {
        rq.setSuccess(true);
        

        rq.setMsg("Borrado Correctamente");
      }
      else
      {
        rq.setSuccess(false);
        rq.setData(null);
        
        rq.setMsg("Error al guardar");
      }
    }
    catch (Exception e)
    {
      e.printStackTrace();
    }
    return rq;
  }
  
  
    @RequestMapping(value={"/insertMaster"}, method={org.springframework.web.bind.annotation.RequestMethod.POST})
  @ResponseBody
  public ResponseQuery2 insertActionMaster(String data, WebRequest webRequest, Model model)
    throws ParseException
  {
    ResponseQuery2 rq = new ResponseQuery2();
    int index = data.indexOf("[");
    if (index == -1) {
      data = "[" + data + "]";
    }
    System.out.println("data:" + data);
    if (model.asMap().get("compania") == null)
    {
      rq.setSuccess(false);
      rq.setData(null);
      rq.setMsg("La sesion no es valida.");
      return rq;
    }
    String compania = model.asMap().get("compania").toString();
    String usuario = model.asMap().get("usuario").toString();
    try
    {
      int guardado = 0;
      
      ObjectMapper mapper = new ObjectMapper();
      DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
      
      mapper.setDateFormat(df);
      List<ErpExtFacturasMasterDto> lista = (List)mapper.readValue(data, mapper
        .getTypeFactory().constructCollectionType(List.class, ErpExtFacturasMasterDto.class));
      if (lista.isEmpty())
      {
        rq.setSuccess(false);
        rq.setData(null);
        rq.setMsg("Error no existen datos que guardar");
        return rq;
      }
      ErpExtFacturasMaster facturas = new ErpExtFacturasMaster();
      ErpExtFacturasMasterId facturasId = new ErpExtFacturasMasterId();
      

      int val = 0;
      Iterator<ErpExtFacturasMasterDto> it = lista.iterator();
      
      ErpExtFacturasMasterId result = null;
      while (it.hasNext())
      {
        ErpExtFacturasMasterDto ss = (ErpExtFacturasMasterDto)it.next();
        
        facturasId.setCompania(compania);
        facturasId.setInvoiceNo(ss.invoice);
        facturas.setId(facturasId);
        
        if (!ss.invoiceDate.equalsIgnoreCase("")) {
            facturas.setInvoceDate(df.parse(ss.invoiceDate));
        }
        
        facturas.setCustomer(ss.customer);
        facturas.setInvoiceDesc(ss.invoiceDesc);
        facturas.setCuenta(ss.cuenta);
        
        if (!ss.terms.equalsIgnoreCase("")) {
         facturas.setTerms(Integer.parseInt(ss.terms));
        }else{
            
            facturas.setTerms(0);
        
        }
        
        facturas.setStatus("A");
        
       
        
        result = erpExtFacturasMasterDao.save(facturas);
        
        
        
      }
      if (result != null)
      {
        rq.setSuccess(true);
        

        rq.setMsg("Guardados Correctamente");
      }
      else
      {
        rq.setSuccess(false);
        rq.setData(null);
        
        rq.setMsg("Error al guardar");
      }
    }
    catch (Exception e)
    {
      e.printStackTrace();
    }
    return rq;
  }
  
  @RequestMapping(value={"/updateMaster"}, method={org.springframework.web.bind.annotation.RequestMethod.POST})
  @ResponseBody
  public ResponseQuery2 updateActionMaster(String data, WebRequest webRequest, Model model)
    throws ParseException
  {
    ResponseQuery2 rq = new ResponseQuery2();
    int index = data.indexOf("[");
    if (index == -1) {
      data = "[" + data + "]";
    }
    System.out.println("data:" + data);
    if (model.asMap().get("compania") == null)
    {
      rq.setSuccess(false);
      rq.setData(null);
      rq.setMsg("La sesion no es valida.");
      return rq;
    }
    String compania = model.asMap().get("compania").toString();
    String usuario = model.asMap().get("usuario").toString();
    try
    {
      int guardado = 0;
      
      ObjectMapper mapper = new ObjectMapper();
      DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
      
      mapper.setDateFormat(df);
      List<ErpExtFacturasMasterDto> lista = (List)mapper.readValue(data, mapper
        .getTypeFactory().constructCollectionType(List.class, ErpExtFacturasMasterDto.class));
      if (lista.isEmpty())
      {
        rq.setSuccess(false);
        rq.setData(null);
        rq.setMsg("Error no existen datos que guardar");
        return rq;
      }
      ErpExtFacturasMaster facturas = new ErpExtFacturasMaster();
      ErpExtFacturasMasterId facturasId = new ErpExtFacturasMasterId();
      

      int val = 0;
      Iterator<ErpExtFacturasMasterDto> it = lista.iterator();
      
      Boolean result = false;
      while (it.hasNext())
      {
        ErpExtFacturasMasterDto ss = (ErpExtFacturasMasterDto)it.next();
        
        facturasId.setCompania(compania);
        facturasId.setInvoiceNo(ss.invoice);
        facturas.setId(facturasId);
        
        if (!ss.invoiceDate.equalsIgnoreCase("")) {
            facturas.setInvoceDate(df.parse(ss.invoiceDate));
        }
        
        facturas.setCustomer(ss.customer);
        facturas.setInvoiceDesc(ss.invoiceDesc);
       
        facturas.setTerms(Integer.parseInt(ss.terms));
        facturas.setStatus("A");
        facturas.setCuenta(ss.cuenta);
       
        
        result = erpExtFacturasMasterDao.update(facturas);
        
        
        
      }
      if (result == true)
      {
        rq.setSuccess(true);
        

        rq.setMsg("Guardados Correctamente");
      }
      else
      {
        rq.setSuccess(false);
        rq.setData(null);
        
        rq.setMsg("Error al guardar");
      }
    }
    catch (Exception e)
    {
      e.printStackTrace();
    }
    return rq;
  }
  
  
  @RequestMapping(value={"/deleteMaster"}, method={org.springframework.web.bind.annotation.RequestMethod.POST})
  @ResponseBody
  public ResponseQuery2 deleteActionMaster(String data, WebRequest webRequest, Model model)
    throws ParseException
  {
    ResponseQuery2 rq = new ResponseQuery2();
    int index = data.indexOf("[");
    if (index == -1) {
      data = "[" + data + "]";
    }
    if (model.asMap().get("compania") == null)
    {
      rq.setSuccess(false);
      rq.setData(null);
      rq.setMsg("La sesion no es valida.");
      return rq;
    }
    String compania = model.asMap().get("compania").toString();
    String usuario = model.asMap().get("usuario").toString();
    try
    {
      
      ObjectMapper mapper = new ObjectMapper();
      DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
      
      mapper.setDateFormat(df);
      List<ErpExtFacturasMasterDto> lista = (List)mapper.readValue(data, mapper
        .getTypeFactory().constructCollectionType(List.class, ErpExtFacturasMasterDto.class));
      if (lista.isEmpty())
      {
        rq.setSuccess(false);
        rq.setData(null);
        rq.setMsg("Error no existen datos que guardar");
        return rq;
      }
      ErpExtFacturasMaster facturas = new ErpExtFacturasMaster();
      ErpExtFacturasMasterId facturasId = new ErpExtFacturasMasterId();
      
      
      int val = 0;
      Iterator<ErpExtFacturasMasterDto> it = lista.iterator();
      
      boolean result = false;
      while (it.hasNext())
      {
        ErpExtFacturasMasterDto ss = (ErpExtFacturasMasterDto)it.next();
        
        facturasId.setCompania(compania);
        facturasId.setInvoiceNo(ss.invoice);
        facturas.setId(facturasId);
      


        result = this.erpExtFacturasMasterDao.delete(facturas);
         
        
      }
      if (result == true)
      {
        rq.setSuccess(true);
        

        rq.setMsg("Borrado Correctamente");
      }
      else
      {
        rq.setSuccess(false);
        rq.setData(null);
        
        rq.setMsg("Error al guardar");
      }
    }
    catch (Exception e)
    {
      e.printStackTrace();
    }
    return rq;
  }
  
  
  @RequestMapping(value={"/poliza"}, method={org.springframework.web.bind.annotation.RequestMethod.POST})
  @ResponseBody
  public ResponseQuery2 poliza(String data,String dataDet, WebRequest webRequest, Model model)
    throws ParseException
  {
    ResponseQuery2 rq = new ResponseQuery2();
    int index = data.indexOf("[");
    if (index == -1) {
      data = "[" + data + "]";
    }
    
    int index2 = dataDet.indexOf("[");
    if (index2 == -1) {
      dataDet = "[" + dataDet + "]";
    }
    System.out.println("data:" + data);
    System.out.println("data2:" + dataDet);
    
    if (model.asMap().get("compania") == null)
    {
      rq.setSuccess(false);
      rq.setData(null);
      rq.setMsg("La sesion no es valida.");
      return rq;
    }
    String compania = model.asMap().get("compania").toString();
    String usuario = model.asMap().get("usuario").toString();
    try
    {
      
      ObjectMapper mapper = new ObjectMapper();
      DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
      
      mapper.setDateFormat(df);
      List<ErpExtFacturasMasterDto> lista = (List)mapper.readValue(data, mapper
        .getTypeFactory().constructCollectionType(List.class, ErpExtFacturasMasterDto.class));
      
      List<ErpExtFacturasDto> listaDet = (List)mapper.readValue(dataDet, mapper
        .getTypeFactory().constructCollectionType(List.class, ErpExtFacturasDto.class));
      
      if (lista.isEmpty())
      {
        rq.setSuccess(false);
        rq.setData(null);
        rq.setMsg("Error no existen datos que guardar");
        return rq;
      }
      
      if (listaDet.isEmpty())
      {
        rq.setSuccess(false);
        rq.setData(null);
        rq.setMsg("Error no existen datos que guardar");
        return rq;
      }
      Polizas poliza = new Polizas();
      DetPolizas detPoliza = new DetPolizas();
      DetPolizasId detPolizaId = new DetPolizasId();
      PolizasId id2 = null;
      BigDecimal tot = new BigDecimal(0);

      Iterator<ErpExtFacturasMasterDto> it = lista.iterator();
      Iterator<ErpExtFacturasDto> itDet = listaDet.iterator();
      
      ErpExtFacturasMaster facturas = new ErpExtFacturasMaster();
      ErpExtFacturasMasterId facturasId = new ErpExtFacturasMasterId();
      while (it.hasNext())
      {
        ErpExtFacturasMasterDto ss = (ErpExtFacturasMasterDto)it.next();
        
            Map ordenFact = new HashMap();
                    ordenFact.put("compania", compania);
                    ordenFact.put("invoceDate", ss.invoiceDate);

                    List listTc = this.processDao.getMapResult("BuscaTC", ordenFact);  
                    Map tc = (HashMap)listTc.get(0);
                    
                    if(tc == null){
                        
                         rq.setSuccess(false);
                         rq.setData(null);
                         rq.setMsg("Error, no se encontro el tipo de cambio correspodiente.");
                         return rq;
                    
                    }

                    String tipoCambio = tc.get("TIPO_CAMBIO").toString();
        
           
            
            
            //erpExtFacturasMasterDao.actualizaEstatus(facturas);
        
            poliza.setCompaniaTemp(compania);
            poliza.setUsuario(usuario);
//            System.out.println("date:"+poliza.getFechaTemp());
            PolizasId id = new PolizasId();
            id.setFecha(df.parse(ss.invoiceDate));
            id.setTipoPoliza("D");
            id.setCompania(compania);
            poliza.setId(id);
            poliza.setAbonos(BigDecimal.ZERO);
            poliza.setAbonosBase(BigDecimal.ZERO);
            poliza.setCargos(BigDecimal.ZERO);
            poliza.setCargosBase(BigDecimal.ZERO);
            poliza.setFechaCap(new Date());
            poliza.setHora(""+System.currentTimeMillis());
            poliza.setEstatus(new BigDecimal(0));
            poliza.setNombre(ss.invoiceDesc);
            poliza.setDivisa("PES");
            poliza.setParidad(new BigDecimal(1));
            
            
          
            boolean periodo = maestroValidation.validaPeriodo(poliza);
            
            if(periodo == false) {
                rq.setSuccess(false);
                rq.setData(null);
                rq.setMsg(maestroValidation.getMsgError());
                return rq;
            }

                   
            
            id2 = polizasDao.save(poliza);
            
            facturasId.setCompania(compania);
            facturasId.setInvoiceNo(ss.invoice);
            facturas.setId(facturasId);
            facturas.setStatus("C");
            facturas.setNumeroPoliza(id2.getNumero());
            id2.getNumero();
            facturas.setFechaPoliza(id2.getFecha());
            id2.getFecha();
            facturas.setTipoPoliza(id2.getTipoPoliza());
            id2.getTipoPoliza();
            erpExtFacturasMasterDao.actualizaEstatus(facturas); 
         
            
            
          
            
            while (itDet.hasNext())
            {
              ErpExtFacturasDto ssDet = (ErpExtFacturasDto)itDet.next();
              
              detPolizaId.setCompania(compania);
              detPolizaId.setFecha(id2.getFecha());
              detPolizaId.setTipoPoliza(id2.getTipoPoliza());
              detPolizaId.setNumero(id2.getNumero());

              int sec = detPolizasDao.getMaxId(detPolizaId);
              
              detPolizaId.setSec(new BigDecimal(sec));
              detPoliza.setId(detPolizaId);
              detPoliza.setCargos(new BigDecimal(ssDet.lineSubtotal));
              detPoliza.setCargosBase(new BigDecimal(ssDet.lineSubtotal));
              detPoliza.setAbonos(BigDecimal.ZERO);
              detPoliza.setAbonosBase(BigDecimal.ZERO);
              detPoliza.setDescripcion(ssDet.notes1);
              detPoliza.setCuenta(ssDet.cuentaReal);
              detPoliza.setcCostos("00");
                 
              detPolizasDao.save(detPoliza);
              
              //Generando complementaria
              
              detPolizaId.setCompania(compania);
              detPolizaId.setFecha(id2.getFecha());
              detPolizaId.setTipoPoliza(id2.getTipoPoliza());
              detPolizaId.setNumero(id2.getNumero());

              sec = detPolizasDao.getMaxId(detPolizaId);
              
              detPolizaId.setSec(new BigDecimal(sec));
              detPoliza.setId(detPolizaId);
              detPoliza.setCargos(new BigDecimal(ssDet.lineSubtotal).multiply(new BigDecimal(tipoCambio)).subtract(new BigDecimal(ssDet.lineSubtotal)));
              detPoliza.setCargosBase(new BigDecimal(ssDet.lineSubtotal).multiply(new BigDecimal(tipoCambio)).subtract(new BigDecimal(ssDet.lineSubtotal)));
              detPoliza.setAbonos(BigDecimal.ZERO);
              detPoliza.setAbonosBase(BigDecimal.ZERO);
              detPoliza.setDescripcion(ssDet.notes1);
              detPoliza.setCuenta(ssDet.cuentaReal);
              detPoliza.setcCostos("00");
              
              detPolizasDao.save(detPoliza);
              
              BigDecimal detTotal= new BigDecimal(ssDet.lineTotal);
              tot = tot.add(detTotal);             
              

              
            }
        detPolizaId.setCompania(compania);
        detPolizaId.setFecha(id2.getFecha());
        detPolizaId.setTipoPoliza(id2.getTipoPoliza());
        detPolizaId.setNumero(id2.getNumero());

        int sec = detPolizasDao.getMaxId(detPolizaId);
              
        detPolizaId.setSec(new BigDecimal(sec));
        detPoliza.setId(detPolizaId);
        detPoliza.setCargos(BigDecimal.ZERO);
        detPoliza.setCargosBase(BigDecimal.ZERO);
        detPoliza.setAbonos(tot);
        detPoliza.setAbonosBase(tot);
        detPoliza.setDescripcion(ss.nomCliente);
        detPoliza.setCuenta(ss.cuentaReal);
        detPoliza.setcCostos("00");
        
        detPolizasDao.save(detPoliza);
        
        //Generando complementaria
        
        detPolizaId.setCompania(compania);
        detPolizaId.setFecha(id2.getFecha());
        detPolizaId.setTipoPoliza(id2.getTipoPoliza());
        detPolizaId.setNumero(id2.getNumero());

        sec = detPolizasDao.getMaxId(detPolizaId);
              
        detPolizaId.setSec(new BigDecimal(sec));
        detPoliza.setId(detPolizaId);
        detPoliza.setCargos(BigDecimal.ZERO);
        detPoliza.setCargosBase(BigDecimal.ZERO);
        detPoliza.setAbonos(tot.multiply(new BigDecimal(tipoCambio)).subtract(tot));
        detPoliza.setAbonosBase(tot.multiply(new BigDecimal(tipoCambio)).subtract(tot));
        detPoliza.setDescripcion(ss.nomCliente);
        detPoliza.setCuenta(ss.cuentaReal);
        detPoliza.setcCostos("00");
        
        detPolizasDao.save(detPoliza);
        
        polizasDao.guardaTotales(poliza.getId(), tot, tot, "0");
              
            
        
      }
      if (id2 != null)
      {
        rq.setSuccess(true);
        

        rq.setMsg("Poliza generada: "+id2.getNumero());
      }
      else
      {
        rq.setSuccess(false);
        rq.setData(null);
        
        rq.setMsg("Error al guardar");
      }
    }
    catch (Exception e)
    {
      e.printStackTrace();
    }
    return rq;
  }


    public void setErpExtFacturasDao(ErpExtFacturasDao erpExtFacturasDao) {
        this.erpExtFacturasDao = erpExtFacturasDao;
    }

    public void setErpExtFacturasMasterDao(ErpExtFacturasMasterDao erpExtFacturasMasterDao) {
        this.erpExtFacturasMasterDao = erpExtFacturasMasterDao;
    }

    public void setPolizasDao(PolizasDao polizasDao) {
        this.polizasDao = polizasDao;
    }

    public void setMaestroValidation(MaestroValidation maestroValidation) {
        this.maestroValidation = maestroValidation;
    }

    public void setDetPolizasDao(DetPolizasDao detPolizasDao) {
        this.detPolizasDao = detPolizasDao;
    }

    public void setProcessDao(ProcessDao processDao) {
        this.processDao = processDao;
    }
    
    
  
    
    
    
}
