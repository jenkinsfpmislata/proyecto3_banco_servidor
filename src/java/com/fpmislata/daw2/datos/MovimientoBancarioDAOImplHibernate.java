/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.fpmislata.daw2.datos;


import com.fpmislata.daw2.modelo.MovimientoBancario;
import java.io.Serializable;
import java.util.List;
import org.hibernate.Query;
import org.hibernate.Session;

/**
 *
 * @author alumno
 */
public class MovimientoBancarioDAOImplHibernate extends GenericDAOImplHibernate<MovimientoBancario, Integer> implements MovimientoBancarioDAO {

    @Override
    public List<MovimientoBancario> findbyIdCuenta(int id) {
       
            Session session = sessionFactory.getCurrentSession();
            session.beginTransaction();
            Query query = session.createQuery("Select movimientoBancario from MovimientoBancario movimientoBancario where cuenta.idCuentaBancaria=?");
            query.setInteger(0, id);
            List<MovimientoBancario> objectList = query.list();
            session.getTransaction().commit();
            return objectList;
        
    }

    
    
}
