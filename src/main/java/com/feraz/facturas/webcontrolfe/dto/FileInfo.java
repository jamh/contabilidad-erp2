/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.feraz.facturas.webcontrolfe.dto;

/**
 *
 * @author Ing. JAMH
 */
public class FileInfo {
    private String url;
    private String path;
    private String file;
    private boolean isSave;

    public FileInfo() {
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getFile() {
        return file;
    }

    public void setFile(String file) {
        this.file = file;
    }

    public void setIsSave(boolean isSave) {
        this.isSave = isSave;
    }

    public boolean isIsSave() {
        return isSave;
    }

    
    
}
