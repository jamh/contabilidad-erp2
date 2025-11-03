
package com.feraz.contabilidad.ws.nomina;

import com.feraz.contabilidad.nomina.dao.ErpNomCancelaDao;
import com.feraz.contabilidad.nomina.dao.ErpNomPolizaDao;
import com.feraz.contabilidad.nomina.model.ErpNomPoliza;
import com.feraz.contabilidad.nomina.model.ErpNomPolizaId;
import com.feraz.contabilidad.nomina.model.ErpNomCancela;
import com.feraz.contabilidad.nomina.model.ErpNomCancelaId;
import com.feraz.contabilidad.nomina.validation.ValidationWSContab;
import com.feraz.contabilidad.ws.nomina.process.ProcesaPolizasNomina;

import javax.jws.WebMethod;
import javax.jws.WebService;
import javax.jws.WebParam;

import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.jamh.data.process.ProcessDao;
import org.apache.log4j.Logger;

/**
 *
 * @author Ing. JAMH
 */
@WebService
public class WSContab {

    private ErpNomCancelaDao erpNomCancelaDao;
    private ErpNomPolizaDao erpNomPolizaDao;
     private ProcessDao processDao;
    private ValidationWSContab validationWSContab;
    private ProcesaPolizasNomina procesaPolizasNomina;
    private static final Logger log = Logger.getLogger(WSContab.class);

    @WebMethod(operationName = "PolizaNomina")
    public String setPolizaNomina(
            @WebParam(name = "pid") String pid,
            @WebParam(name = "numeroOperacion") String numeroOperacion,
            @WebParam(name = "compania") String compania,
            @WebParam(name = "tipoOperacion") String tipoOperacion,
            @WebParam(name = "numeroMovimientos") String numeroMovimientos,
            @WebParam(name = "calendario") int calendario,
            @WebParam(name = "periodo") int periodo,
            @WebParam(name = "fechaPago") Date fechaPago,
            @WebParam(name = "grupoPago") String grupoPago,
            @WebParam(name = "departamento") String departamento,
            @WebParam(name = "tipoNomina") String tipoNomina,
            @WebParam(name = "procesoEspecial") String procesoEspecial,
            @WebParam(name = "centroCostos") String centroCostos,
            @WebParam(name = "cuenta") String cuenta,
            @WebParam(name = "cargos") BigDecimal cargos,
            @WebParam(name = "abonos") BigDecimal abonos,
            @WebParam(name = "descripcion") String descripcion,
            @WebParam(name = "descripcion_nom") String descripcion_nom,
            
            @WebParam(name = "descripcion2") String descripcion2,
            @WebParam(name = "dicTimbrado") String dicTimbrado,
            @WebParam(name = "empleado") String empleado
            ) throws ParseException {

        ErpNomPoliza erpNomPoliza = new ErpNomPoliza();
        ErpNomPolizaId erpNomPolizaId = new ErpNomPolizaId();
      //  log.debug("tipoPolizaFeraz: " + tPoliza.get("TIPO_POLIZA"));
        log.debug("------------------------------------------- ");
        log.debug("NUMERO DE OPERACION "+numeroOperacion);
        log.debug("CARGOS "+cargos);
        log.debug("ABONOS "+abonos);
         //SimpleDateFormat formatFecha = new SimpleDateFormat("dd/MM/yyyy");
        int sec = erpNomPolizaDao.getMaxIdErpNomPoliza();
        
          Map companiaNom = new HashMap();
               
               companiaNom.put("compania", compania);
               
                   List listCompNom = processDao.getMapResult("BuscaCompaniaNomina", companiaNom);
                  
                  if (listCompNom == null || listCompNom.isEmpty()){
                    return msgResp(pid,"0", "La compania no existe",numeroOperacion,"SIN POLIZA");  
                  }
                   
                    Map compNom = (HashMap) listCompNom.get(0);
                  log.debug("COMPANIA:" + compNom.get("COMPANIA"));
                   
        
       // if(compania.equals("HRSS")){
         //       compania = "HRSSO";
       // }
        
        erpNomPolizaId.setId(sec);
        erpNomPoliza.setId(erpNomPolizaId);
        erpNomPoliza.setPid(pid);
        erpNomPoliza.setCompania(compNom.get("COMPANIA").toString());
        erpNomPoliza.setTipoOperacion(tipoOperacion);
        erpNomPoliza.setNumeroMovimientos(numeroMovimientos);
        erpNomPoliza.setCalendario(calendario);
        erpNomPoliza.setPeriodo(periodo);
        //erpNomPoliza.setFechaPago(formatFecha.parse(fechaPago));
        
        erpNomPoliza.setGrupoPago(grupoPago);
        erpNomPoliza.setDepartamento(departamento);
        erpNomPoliza.setTipoNomina(tipoNomina);
        erpNomPoliza.setProcesoEspecial(procesoEspecial);
        erpNomPoliza.setCentroCostos(centroCostos);
        erpNomPoliza.setCuenta(cuenta);
        erpNomPoliza.setCargos(cargos);
        erpNomPoliza.setAbonos(abonos);
        erpNomPoliza.setDescripcion(descripcion);
        erpNomPoliza.setDescripcionNom(descripcion_nom);
        erpNomPoliza.setDescripcion2(descripcion2);
        erpNomPoliza.setDicTimbrado(dicTimbrado);
        erpNomPoliza.setEmpleado(empleado);
        
        DateFormat fecha = new SimpleDateFormat("dd/MM/yyyy");
	String convertido = fecha.format(fechaPago);
        
        Date dateFecha = fecha.parse(convertido);
        
        erpNomPoliza.setFechaPago(dateFecha);
        
        
       try{ 
        boolean datos = validationWSContab.validaDatos(erpNomPoliza);

        if (datos == false) {
            return msgResp(pid,"0", validationWSContab.getMsgError() ,numeroOperacion,"SIN POLIZA");   
        }

        
        
        boolean cuentaV = validationWSContab.validaCuenta(erpNomPoliza);
        boolean cCosto = validationWSContab.validaCC(erpNomPoliza);
        boolean periodoV = validationWSContab.buscaPeriodo(erpNomPoliza,convertido);
        boolean polizaNom = validationWSContab.validaPoliza(erpNomPoliza,convertido);

        if (!cCosto) {
            log.error("pid: "+pid+" mensaje: "+ validationWSContab.getMsgError() + "numeroOperacion: " +numeroOperacion); 
            return msgResp(pid,"0", validationWSContab.getMsgError() ,numeroOperacion,"SIN POLIZA");         
        }

        if (!periodoV) {
            log.error("pid: "+pid+" mensaje: "+ validationWSContab.getMsgError() + "numeroOperacion: " +numeroOperacion); 
            

            return msgResp(pid,"0", validationWSContab.getMsgError() ,numeroOperacion,"SIN POLIZA");

        }
        if (tipoNomina.equalsIgnoreCase("1")){
            
                                log.debug("tipoNomina"+tipoNomina);

            
            
        }else{    
                    
                    
                
                if (!polizaNom) {
                    
                    log.error("pid: "+pid+" mensaje: "+ validationWSContab.getMsgError() + "numeroOperacion: " +numeroOperacion); 

                    return msgResp(pid,"0", validationWSContab.getMsgError() ,numeroOperacion,"SIN POLIZA");

                    }
        }
        
        if(!cuentaV){
            
            log.error("pid: "+pid+" mensaje: "+ validationWSContab.getMsgError() + "numeroOperacion: " +numeroOperacion); 
        
            return msgResp(pid,"0", validationWSContab.getMsgError() ,numeroOperacion,"SIN POLIZA");

        
        }
            

//        System.out.println("cuentaV"+ cuentaV);
//        
//         System.out.println("Empezando web service2"+ erpNomPoliza.getId().getId());
//         
        ErpNomPolizaId result = erpNomPolizaDao.save(erpNomPoliza);
        
        if (result != null) {
            
            if(Integer.parseInt(numeroOperacion) == Integer.parseInt(numeroMovimientos)){
            
            
            }else{
            
                log.error("pid: "+pid+" mensaje: Guardado exitoso " + "numeroOperacion: " +numeroOperacion); 
                
            return msgResp(pid,"1", "Guardado exitoso" ,numeroOperacion,"EN ESPERA");
            
            }
        
        }

        if (cuentaV) {
            if (result != null) {
                
                if(Integer.parseInt(numeroOperacion) == Integer.parseInt(numeroMovimientos)){
                    
                    log.debug("GENERANDO POLIZA");
                boolean polizaNom2 = validationWSContab.validaPoliza(erpNomPoliza,convertido);
                
                if (tipoNomina.equalsIgnoreCase("1")){
                    
                    log.debug("tipoNomina"+tipoNomina);
                    polizaNom2 = true;
                
                }
                
                log.debug("-------------------polizaNom2----------------"+polizaNom2);
                
                if (polizaNom2) {
                    
                    int registros = validationWSContab.cuentaPid(erpNomPoliza);
                    
                    if(Integer.parseInt(numeroMovimientos) != registros){
                        
                        
                        log.error("pid: "+pid+" mensaje: Guardado exitoso No se genero la poliza, existen datos con errores " + "numeroOperacion: " +numeroOperacion); 
                
                        return msgResp(pid,"1", "Guardado exitoso\n, No se genero la poliza, existen datos con errores" ,numeroOperacion,"SIN POLIZA");

                        
                    }
                
                int poliza = procesaPolizasNomina.generaPolizas(erpNomPoliza);
                
                   boolean polizaNom3 = validationWSContab.validaPoliza(erpNomPoliza,convertido);
                   
                  if (poliza == 0){
                       
                       
                   
                   if (!polizaNom3) {
                       
                        log.debug("Guardado exitoso Se genero la Poliza");
                        
                      String datosPoliza = validationWSContab.buscaDatosPoliza(erpNomPoliza);
                      
                      log.debug("Guardado exitoso Se genero la Poliza 2");
                      
                      log.debug("datosPoliza"+datosPoliza);
                        
                        
                        return msgResp(pid,"1", "Guardado exitoso Se genero la Poliza" ,numeroOperacion,datosPoliza);

                 
                   
                   }else{
                   
                        return msgResp(pid,"1", "Guardado exitoso, No se genero la poliza" ,numeroOperacion,"SIN POLIZA");

                   
                   }
                
                
                  }else{
                  
                        return msgResp(pid,"0", "Error al Generar la poliza" ,numeroOperacion,"SIN POLIZA");
                   
                   
                  }
                
                }else{
                    
                    return msgResp(pid,"0", validationWSContab.getMsgError() ,numeroOperacion,"SIN POLIZA");
                
                
                }
                }else{
//               
                        return msgResp(pid,"1", "Guardado exitoso" ,numeroOperacion,"EN ESPERA");
                
                }
            } else {
                     return msgResp(pid,"0", "Error al Guardar" ,numeroOperacion,"SIN POLIZA");

                 
            }
        } else {

            return msgResp(pid,"0", validationWSContab.getMsgError() ,numeroOperacion,"SIN POLIZA");

        }
        
        
        
       } catch (Exception e) {
           
           log.error("Error de nomina poliza "+ e);
           return null;
        }
        
        
        //return "Hello " + pid + " !";
      //  return "Proceso Exitosos";
    }
    
   

    @WebMethod(operationName = "CancelaPoliza")
    public String cancela(
            @WebParam(name = "pid") String pid,
            @WebParam(name = "compania") String compania,
            @WebParam(name = "operacion") String operacion,
            @WebParam(name = "usuario") String usuario
    ) {

        ErpNomCancela erpNomCancela = new ErpNomCancela();
        ErpNomCancelaId erpNomCancelaId = new ErpNomCancelaId();
        
        System.out.println("CANCELANDO-----------------");
        
          Map companiaNom = new HashMap();
               
               companiaNom.put("compania", compania);
               
                   List listCompNom = processDao.getMapResult("BuscaCompaniaNomina", companiaNom);
                   
                    Map compNom = (HashMap) listCompNom.get(0);
                  System.out.println("COMPANIA:" + compNom.get("COMPANIA"));
                   

        int secC = erpNomCancelaDao.getMaxIdErpNomPoliza();

        erpNomCancelaId.setId(secC);
        erpNomCancela.setId(erpNomCancelaId);
        erpNomCancela.setCompania(compNom.get("COMPANIA").toString());
        erpNomCancela.setOperacion(operacion);
        erpNomCancela.setPid(pid);
        erpNomCancela.setUsuario(usuario);

        boolean datosCancela = validationWSContab.validaDatosCancela(erpNomCancela);

        if (!datosCancela) {
            return msgRespCan(pid,"0", validationWSContab.getMsgError(),"SIN ESTATUS","SIN POLIZA CANCELADA");
        }

        boolean pidNomina = validationWSContab.validaPidPoliza(erpNomCancela);

        if (pidNomina == false) {
            return msgRespCan(pid,"0", validationWSContab.getMsgError(), "SIN ESTATUS","SIN POLIZA CANCELADA");                    
        }

        ErpNomCancelaId resultCancela = erpNomCancelaDao.save(erpNomCancela);

        if (resultCancela != null) {  
            
            procesaPolizasNomina.cancelaPolizas(erpNomCancela);
            
            boolean errorCancelar = validationWSContab.validaErrorCancela(erpNomCancela);
            
            if (errorCancelar == false){
                
                System.out.println("entrando en error");
                
               return msgRespCan(pid,"0",validationWSContab.getMsgError(),"SIN ESTATUS","SIN POLIZA CANCELADA");
            }
            
            String estatus = validationWSContab.validaEstatusPoliza(erpNomCancela);
            
            System.out.println("ESTATUS"+estatus);
            
            if (estatus.equalsIgnoreCase("CA") || estatus.equalsIgnoreCase("CF")){
                
                String datosCancelacion = validationWSContab.buscaDatosPolizaCancelacion(erpNomCancela);
                 
                
            
            return msgRespCan(pid,"1","Cancelacion exitosa",estatus,datosCancelacion); 
            }else{
                
                 System.out.println("MENSAJE"+validationWSContab.getMsgError());
               return msgRespCan(pid,"0", validationWSContab.getMsgError(),estatus,"SIN POLIZA CANCELADA"); 
            }
            
        } else {

            return msgRespCan(pid,"0","Error al cancelar la poliza","SIN ESTATUS","SIN POLIZA CANCELADA");
        }

    }
    
    @WebMethod(exclude = true)    
    public String msgRespCan(String pid,String result,String msg, String estatus, String polizaCancelada){
         return "<?xml version=\"1.0\" encoding=\"UTF-8\"?>"
                    + "<response>\n"
                    + "   <pid>" + pid + "</pid>\n"
                    + "   <estatus>" + estatus + "</estatus>\n"
                    + "  <result>" +result+ "</result>\n"
                    + "  <poliza>" +polizaCancelada+ "</poliza>\n"
                    + "  <mensaje>"+msg+"</mensaje>\n"
                    + "</response>\n";
    }
    
     
    @WebMethod(exclude = true)    
    public String msgResp(String pid,String result,String msg,String num,String poliza){
         return "<?xml version=\"1.0\" encoding=\"UTF-8\"?>"
                    + "<response>\n"
                    + "   <pid>" + pid + "</pid>\n"
                  + "  <numeroOperacion>" + num + "</numeroOperacion>\n"
                    + "  <result>" +result+ "</result>\n"
                    + "  <poliza>" +poliza+ "</poliza>\n"
                    + "  <mensaje>"+msg+"</mensaje>\n"
                    + "</response>\n";
    }

    @WebMethod(exclude = true)
    public void setErpNomCancelaDao(ErpNomCancelaDao erpNomCancelaDao) {
        this.erpNomCancelaDao = erpNomCancelaDao;
    }

    @WebMethod(exclude = true)
    public void setErpNomPolizaDao(ErpNomPolizaDao erpNomPolizaDao) {
        this.erpNomPolizaDao = erpNomPolizaDao;
    }

    @WebMethod(exclude = true)
    public void setProcessDao(ProcessDao processDao) {
        this.processDao = processDao;
    }
    @WebMethod(exclude = true)
    public void setValidationWSContab(ValidationWSContab validationWSContab) {
        this.validationWSContab = validationWSContab;
    }

    @WebMethod(exclude = true)
    public void setProcesaPolizasNomina(ProcesaPolizasNomina procesaPolizasNomina) {
        this.procesaPolizasNomina = procesaPolizasNomina;
    }
    
    

}
