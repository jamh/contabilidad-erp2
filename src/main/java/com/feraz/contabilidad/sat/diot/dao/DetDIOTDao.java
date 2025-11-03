/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.feraz.contabilidad.sat.diot.dao;

/**
 *
 * @author Feraz3
 */
import com.feraz.contabilidad.sat.diot.model.DetDIOT;
import com.feraz.contabilidad.sat.diot.model.DetDIOTId;
import java.math.BigDecimal;
import java.util.List;
public interface DetDIOTDao {

    
            public DetDIOTId save(DetDIOT detDIOT);

            public List<DetDIOT> FindDetDIOT(DetDIOT detDIOT);

            public boolean delete(DetDIOT deleteDetDIOT);

            public boolean update(DetDIOT updateDetDIOT);
            
             public boolean deleteDiot(DetDIOT deleteDetDIOT);
}
