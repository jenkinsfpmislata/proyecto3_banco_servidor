/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.fpmislata.daw2.datos;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author alumno
 */
public class ConnectionFactoryImplJDBC implements ConnectionFactory{
  
    private Connection conexion;
    
    @Override
    public Connection getConnection(){
        try{
        Class.forName("com.mysql.jdbc.Driver");
        conexion = null;
        conexion = DriverManager.getConnection("jdbc:mysql://localhost:3306/banco", "root", "root");
        
        return conexion;
        }catch(Exception ex){
            throw new RuntimeException("Fallo al obtener la conexion");
        }
    }
}
