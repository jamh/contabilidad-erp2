/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.feraz.facturas.webcontrolfe.model;

import org.springframework.web.multipart.commons.CommonsMultipartFile;

/**
 *
 * @author 5555
 */
public class UploadItem {
  private String name;
  private CommonsMultipartFile fileData;
 
  public String getName()
  {
    return name;
  }
 
  public void setName(String name)
  {
    this.name = name;
  }
 
  public CommonsMultipartFile getFileData()
  {
    return fileData;
  }
 
  public void setFileData(CommonsMultipartFile fileData)
  {
    this.fileData = fileData;
  }
}
