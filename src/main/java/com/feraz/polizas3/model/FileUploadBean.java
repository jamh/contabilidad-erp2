/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.feraz.polizas3.model;

import org.springframework.web.multipart.commons.CommonsMultipartFile;

/**
 *
 * @author 5555
 */
public class FileUploadBean {
        private CommonsMultipartFile file;

	public CommonsMultipartFile getFile() {
		return file;
	}

	public void setFile(CommonsMultipartFile file) {
		this.file = file;
	}
}
