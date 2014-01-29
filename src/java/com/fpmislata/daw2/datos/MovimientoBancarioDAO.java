/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.fpmislata.daw2.datos;

import com.fpmislata.daw2.modelo.MovimientoBancario;
import java.util.List;

/**
 *
 * @author alumno
 */
public interface MovimientoBancarioDAO extends GenericDAO<MovimientoBancario, Integer>{

     public List<MovimientoBancario> findbyIdCuenta(int id);

    
}
