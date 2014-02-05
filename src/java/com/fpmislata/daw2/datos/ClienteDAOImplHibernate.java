/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.fpmislata.daw2.datos;

import com.fpmislata.daw2.modelo.Cliente;
import com.fpmislata.daw2.modelo.EntidadBancaria;
import java.io.Serializable;
import java.util.List;
import org.hibernate.Query;
import org.hibernate.Session;

/**
 *
 * @author alumno
 */
public class ClienteDAOImplHibernate extends GenericDAOImplHibernate<Cliente, Integer> implements ClienteDAO {

    //por cif y crear cif en bbdd
        public List<Cliente> findbyCif(String cif) {
        if (cif == null || cif.equals("")) {
            return findAll();
        } else {
            Session session = sessionFactory.getCurrentSession();
            session.beginTransaction();
            Query query = session.createQuery("Select cliente from Cliente cliente where cif LIKE ?");
            query.setString(0, cif + "%");
            List<Cliente> objectList = query.list();
            session.getTransaction().commit();
            return objectList;
        }
    }
    
}
