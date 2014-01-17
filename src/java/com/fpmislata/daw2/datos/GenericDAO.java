/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.fpmislata.daw2.datos;

import java.io.Serializable;
import java.util.List;

/**
 *
 * @author alumno
 */
    public interface GenericDAO<T,ID> {
    T read(ID id);
    List<T> findAll();
    void delete(ID id);
    void insert(T tipo);
    void update(T tipo);
    
}
    

