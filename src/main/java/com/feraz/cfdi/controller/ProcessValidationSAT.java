package com.feraz.cfdi.controller;

import com.feraz.sat.cfdi.check.ValidaSAT;
import org.jamh.wf.json.model.ResponseQuery;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.context.request.WebRequest;

/**
 *
 * @author Ing. JAMH
 */
@Controller
@RequestMapping("/CFDISATValid")
@SessionAttributes({"compania", "usuario"})
public class ProcessValidationSAT {
    
    private ValidaSAT validaSAT;

    @RequestMapping(value = "/valid", method = RequestMethod.POST)
    public @ResponseBody
    ResponseQuery validaSAt(String data, WebRequest webRequest, Model model) {
        ResponseQuery rq = new ResponseQuery();

        if (model.asMap().get("compania") == null) {
            rq.setMsg("Sesion no valida");
            rq.setSuccess(false);
            rq.setData(null);
        }

        return rq;
    }

    public void setValidaSAT(ValidaSAT validaSAT) {
        this.validaSAT = validaSAT;
    }
    
    
    
}
