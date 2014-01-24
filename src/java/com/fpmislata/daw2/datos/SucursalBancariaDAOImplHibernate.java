/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.fpmislata.daw2.datos;

import com.fpmislata.daw2.modelo.SucursalBancaria;
import java.util.List;
import org.hibernate.Query;
import org.hibernate.Session;

/**
 *
 * @author alumno
 */
public class SucursalBancariaDAOImplHibernate extends GenericDAOImplHibernate<SucursalBancaria, Integer> implements SucursalBancariaDAO {

   public List<SucursalBancaria> findbyEntidad(int id) {
       
            Session session = sessionFactory.getCurrentSession();
            session.beginTransaction();
            Query query = session.createQuery("Select sucursalBancaria from SucursalBancaria sucursalBancaria where entidadBancaria.idEntidad =?");
            query.setInteger(0, id );
            List<SucursalBancaria> objectList = query.list();
            session.getTransaction().commit();
            return objectList;
        
    }
}
