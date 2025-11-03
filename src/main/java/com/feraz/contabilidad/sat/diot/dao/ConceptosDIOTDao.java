/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.feraz.contabilidad.sat.diot.dao;

/**
 *
 * @author Feraz3
 */

import com.feraz.contabilidad.sat.diot.model.ConceptosDIOT;
import com.feraz.contabilidad.sat.diot.model.ConceptosDIOTId;
import java.math.BigDecimal;
import java.util.List;
public interface ConceptosDIOTDao {
    
    
     public ConceptosDIOTId save(ConceptosDIOT conceptosDIOT);

            public ConceptosDIOT FindConceptosDIOT(ConceptosDIOT conceptosDIOT);

            public boolean delete(ConceptosDIOT deleteConceptosDIOT);

            public boolean update(ConceptosDIOT updateConceptosDIOT);
    
}
