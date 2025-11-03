/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.feraz.contabilidad.sat.electronica.model;

/**
 *
 * @author Feraz3
 */
import org.jamh.tools.DateAdapter;
import java.util.Date;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

//@JsonIgnoreProperties(ignoreUnknown = true)
@Entity
@Table(name = "ERP_SAT_POLIZA")
@XmlRootElement(name = "Polizas")
@XmlAccessorType(XmlAccessType.FIELD)
//@XmlSeeAlso({ErpSatPlztransaccion.class})
public class ErpSatPoliza implements java.io.Serializable {

//      @EmbeddedId
//    @AttributeOverrides({
//        @AttributeOverride(name = "compania", column = @Column(name = "COMPANIA", nullable = false, length = 10)),
//        @AttributeOverride(name = "pid", column = @Column(name = "PID", nullable = false, length = 25))
//        
//    })    
//    private ErpSatPolizaId id;
    @Id
    @XmlTransient
    @Column(name = "NUMERO")
    private long numero;

    @Column(name = "PID")
    @XmlTransient
    private String pid;
     @XmlTransient
    @Column(name = "COMPANIA")
    private String compania;

    @Column(name = "TIPO")
    // @XmlElement(name = "TIPO")
    private String tipo;

    @Column(name = "NUM")
    // @XmlElement(name = "NUM")
    private String num;

    @Column(name = "FECHA")
    @XmlElement(name = "fecha", required = true)
    @XmlJavaTypeAdapter(DateAdapter.class)
    private Date fecha;

    @Column(name = "CONCEPTO")
    // @XmlElement(name = "CONCEPTO")
    private String concepto;
    // @XmlElementWrapper(name = "TransaccionList")  
    @XmlElement(name = "Transacciones")
    @OrderBy("sec")
    @OneToMany(mappedBy = "erpSatPoliza", fetch = FetchType.EAGER)
    private List<ErpSatPlztransaccion> erpSatPlztransaccion;

    public ErpSatPoliza() {
    }

//        public ErpSatPoliza(ErpSatPolizaId id){
//
//            this.id = id;
//
//        }
//
//    public ErpSatPolizaId getId() {
//        return id;
//    }
    public String getTipo() {
        return tipo;
    }

    public String getNum() {
        return num;
    }

    public Date getFecha() {
        return fecha;
    }

    public String getConcepto() {
        return concepto;
    }

//    public void setId(ErpSatPolizaId id) {
//        this.id = id;
//    }
    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public void setNum(String num) {
        this.num = num;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public void setConcepto(String concepto) {
        this.concepto = concepto;
    }

    public String getPid() {
        return pid;
    }

    public void setPid(String pid) {
        this.pid = pid;
    }

    public List<ErpSatPlztransaccion> getErpSatPlztransaccion() {
        return erpSatPlztransaccion;
    }

    public void setErpSatPlztransaccion(List<ErpSatPlztransaccion> erpSatPlztransaccion) {
        this.erpSatPlztransaccion = erpSatPlztransaccion;
    }

    public String getCompania() {
        return compania;
    }

    public void setCompania(String compania) {
        this.compania = compania;
    }

    public long getNumero() {
        return numero;
    }

    public void setNumero(long numero) {
        this.numero = numero;
    }

}
