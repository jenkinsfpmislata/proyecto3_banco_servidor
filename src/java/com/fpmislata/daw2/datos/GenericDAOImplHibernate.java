/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.fpmislata.daw2.datos;

import com.fpmislata.daw2.modelo.EntidadBancaria;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.util.List;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

/**
 *
 * @author alumno
 */
public class GenericDAOImplHibernate<T, ID extends Serializable> implements GenericDAO<T, ID> {

    SessionFactory sessionFactory;

    public GenericDAOImplHibernate() {
        sessionFactory = HibernateUtil.getSessionFactory();


    }

    @Override
    public T read(ID id) {
        Session session = sessionFactory.getCurrentSession();
        session.beginTransaction();

        T entity = (T) session.get(getEntityClass(), id);


        session.getTransaction().commit();
        return entity;

    }

    @Override
    public List<T> findAll() {
        Session session = sessionFactory.getCurrentSession();
        session.beginTransaction();
        Query query = session.createQuery("Select t from "+ getEntityClass().getName() +" t");
        List<T> objectList = query.list();
        session.getTransaction().commit();

        return objectList;
    }


    @Override
    public void insert(T tipo) {
        Session session = sessionFactory.getCurrentSession();
        session.beginTransaction();
        session.save(tipo);
        session.getTransaction().commit();

    }

    @Override
    public void update(T tipo) {
        Session session = sessionFactory.getCurrentSession();
        session.beginTransaction();
        session.update(tipo);
        session.getTransaction().commit();

    }

    @Override
    public void delete(ID id) {
        Session session = sessionFactory.getCurrentSession();
        session.beginTransaction();

        T entity = (T) session.get(getEntityClass(), id);

        session.delete(entity);
        session.getTransaction().commit();


    }

    private Class<T> getEntityClass() {
        return (Class<T>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];
    }
}
