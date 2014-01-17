/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.fpmislata.daw2.datos;

import java.io.IOException;
import java.util.Collection;
import javax.servlet.Filter;

import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

import org.hibernate.HibernateException;
import org.hibernate.engine.spi.FilterDefinition;

/**
 *
 * @author alumno
 */
public class HibernateContextListenerAndFilter implements Filter,ServletContextListener {

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        HibernateUtil.buildSessionFactory();
        System.out.println("Hola");
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        HibernateUtil.closeSessionFactory();
        System.out.println("Adios");
    }

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
    
        try{
            HibernateUtil.openSessionAndBindToThread();
            chain.doFilter(request, response);
        }finally{
            HibernateUtil.closeSessionAndUnbindFromThread();
        }
    
    }

    @Override
    public void destroy() {
        
    }

   
}
