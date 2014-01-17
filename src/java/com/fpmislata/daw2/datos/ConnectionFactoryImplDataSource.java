package com.fpmislata.daw2.datos;


import com.fpmislata.daw2.datos.ConnectionFactory;
import javax.naming.InitialContext;

import java.sql.*;
import javax.naming.NamingException;
import javax.sql.*;

// See this url for more information:
// http://tomcat.apache.org/tomcat-5.5-doc/jndi-datasource-examples-howto.html
public class ConnectionFactoryImplDataSource implements ConnectionFactory {
    private static DataSource datasource = null;
    @Override
    public Connection getConnection() {
    try{ 
    InitialContext initialContext = new InitialContext();
    
    datasource  = (DataSource) initialContext.lookup("java:/comp/env/jdbc/banco");

    return datasource.getConnection ();
    }catch(Exception ex){
        throw new RuntimeException("Fallo al obtener la conexion",ex);
    }
    }
}
