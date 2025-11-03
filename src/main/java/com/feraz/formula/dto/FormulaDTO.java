/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.feraz.formula.dto;

import com.fasterxml.jackson.annotation.JsonProperty;


/**
 *
 * @author 55555
 */
public class FormulaDTO {
    
    
    @JsonProperty("COMPANIA")
    private String compania;
    
    @JsonProperty("CEDULA")
    private String cedula;
    
    @JsonProperty("COL1")
    private String col1;
    
    @JsonProperty("COL2")
    private String col2;
    
    @JsonProperty("COL3")
    private String col3;
    
    @JsonProperty("COL4")
    private String col4;
    
    @JsonProperty("COL5")
    private String col5;
    
    @JsonProperty("COL6")
    private String col6;
    
    @JsonProperty("COL7")
    private String col7;
    
    @JsonProperty("COL8")
    private String col8;
    
    @JsonProperty("COL9")
    private String col9;
    
    @JsonProperty("COL10")
    private String col10;
    
    @JsonProperty("id")
    private String id;


    public FormulaDTO() {
    }

    public void setCol1(String col1) {
        this.col1 = col1;
    }

    public void setCol2(String col2) {
        this.col2 = col2;
    }

    public void setCol3(String col3) {
        this.col3 = col3;
    }

    public void setCol4(String col4) {
        this.col4 = col4;
    }

    public void setCol5(String col5) {
        this.col5 = col5;
    }

    public void setCol6(String col6) {
        this.col6 = col6;
    }

    public void setCol7(String col7) {
        this.col7 = col7;
    }

    public void setCol8(String col8) {
        this.col8 = col8;
    }

    public void setCol9(String col9) {
        this.col9 = col9;
    }

    public void setCol10(String col10) {
        this.col10 = col10;
    }

    public void setCompania(String compania) {
        this.compania = compania;
    }

    public void setCedula(String cedula) {
        this.cedula = cedula;
    }

    public String getCompania() {
        return compania;
    }

    public String getCedula() {
        return cedula;
    }

    public String getCol1() {
        return col1;
    }

    public String getCol2() {
        return col2;
    }

    public String getCol3() {
        return col3;
    }

    public String getCol4() {
        return col4;
    }

    public String getCol5() {
        return col5;
    }

    public String getCol6() {
        return col6;
    }

    public String getCol7() {
        return col7;
    }

    public String getCol8() {
        return col8;
    }

    public String getCol9() {
        return col9;
    }

    public String getCol10() {
        return col10;
    }

    
}
