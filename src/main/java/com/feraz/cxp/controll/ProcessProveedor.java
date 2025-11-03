/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.feraz.cxp.controll;

import com.feraz.cxp.dao.ErpCClientesDao;
import com.feraz.cxp.model.ErpCClientes;
import com.feraz.cxp.model.ErpCClientesId;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 *
 * @author Feraz3
 */
public class ProcessProveedor {

    private ErpCClientesDao erpCClientesDao;

    public ProcessProveedor() {

    }

    public boolean crearProveedor(String compania, String nombre, String rfc) {
        try {

            ErpCClientes clientes = new ErpCClientes();
            ErpCClientesId clientesId = new ErpCClientesId();

            DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
            Date date = new Date();
            System.out.println("Date Proveedor" + dateFormat.format(date));

            clientesId.setCompania(compania);
            clientesId.setOrigen("P");

            System.out.println("compania" + compania);

            String pid = erpCClientesDao.getMaxErpCClientes(clientesId);

            clientesId.setIdCliente(pid);
            clientes.setId(clientesId);
            clientes.setNombre(nombre);
            clientes.setRfc(rfc);
            clientes.setRazonSocial(nombre);
            clientes.setfAlta(date);

            ErpCClientesId result = erpCClientesDao.save(clientes);

            if (result == null) {

                return false;

            } else {

                return true;

            }

        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }

    }

    public void setErpCClientesDao(ErpCClientesDao erpCClientesDao) {
        this.erpCClientesDao = erpCClientesDao;
    }

}
