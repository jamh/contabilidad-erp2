
package com.feraz.cxp.dao;

import com.feraz.cxp.model.ErpCpOtras;
import com.feraz.cxp.model.ErpCpOtrasId;
import java.util.List;

/**
 *
 * @author Administrador
 */
public interface ErpCpOtrasDao {

    public ErpCpOtrasId save(ErpCpOtras erpCClientes);

    public List<ErpCpOtras> findErpCClientes(ErpCpOtrasId erpCpOtras);

    public boolean delete(ErpCpOtras erpCpOtras);

    public boolean update(ErpCpOtras erpCpOtras);
    
    public int getMaxErpCpOtrasId(ErpCpOtrasId id);
    
    public boolean updateErpCpOtras(ErpCpOtras disp, String fecha);
    
    public boolean updateErpCpOtrasEstatusPagos(ErpCpOtras disp);
     public boolean updateErpCpOtrasEstatusPagosTeso(ErpCpOtras disp);
    
     public boolean updateErpCpOtrasEstatusPagosOtras(ErpCpOtras disp);
     
     public boolean updateErpCpOtrasEstatusPagosOtrasSF(ErpCpOtras disp);
     
     public boolean updateErpCpOtrasSaldoNotasExt(ErpCpOtras disp);
     public boolean descripcionCancelacion(String descripcionCan, ErpCpOtras comp);

}
