/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.fpmislata.daw2.datos;

import com.fpmislata.daw2.modelo.Cliente;
import java.util.List;
import org.hibernate.Query;
import org.hibernate.Session;

/**
 *
 * @author alumno
 */
public interface ClienteDAO extends GenericDAO<Cliente, Integer>{

      public List<Cliente> findbyCif(String cif);
      
}

    

