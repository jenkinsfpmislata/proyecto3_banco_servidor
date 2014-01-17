/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.fpmislata.daw2.datos;

import com.fpmislata.daw2.modelo.EntidadBancaria;
import java.io.Serializable;
import java.util.List;
import org.hibernate.Query;
import org.hibernate.Session;

/**
 *
 * @author alumno
 */
public class EntidadBancariaDAOImplHibernate extends GenericDAOImplHibernate<EntidadBancaria, Integer> implements EntidadBancariaDAO {

    public List<EntidadBancaria> findbyNombre(String nombre) {
        if (nombre == null || nombre.equals("")) {
            return findAll();
        } else {
            Session session = sessionFactory.getCurrentSession();
            session.beginTransaction();
            Query query = session.createQuery("Select entidadBancaria from EntidadBancaria entidadBancaria where nombre LIKE ?");
            query.setString(0, "%" + nombre + "%");
            List<EntidadBancaria> objectList = query.list();
            session.getTransaction().commit();
            return objectList;
        }
    }
}
