/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.fpmislata.daw2.datos;

import com.fpmislata.daw2.modelo.CuentaBancaria;
import java.io.Serializable;
import java.util.List;
import org.hibernate.Query;
import org.hibernate.Session;

/**
 *
 * @author alumno
 */
public class CuentaBancariaDAOImplHibernate extends GenericDAOImplHibernate<CuentaBancaria, Integer> implements CuentaBancariaDAO {

    public List<CuentaBancaria> findbyCif(String cif){
    
        
        if (cif == null || cif.equals("")) {
            return findAll();
        } else {
            Session session = sessionFactory.getCurrentSession();
            session.beginTransaction();
            Query query = session.createQuery("Select cuentaBancaria from CuentaBancaria cuentaBancaria where cif LIKE ?");
            query.setString(0, cif + "%");
            List<CuentaBancaria> objectList = query.list();
            session.getTransaction().commit();
            return objectList;
        }
        
        
    }

}
