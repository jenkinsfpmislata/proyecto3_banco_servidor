/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.fpmislata.daw2.modelo;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fpmislata.daw2.presentacion.Credenciales;
import java.io.Serializable;
import javax.validation.constraints.NotNull;
import org.hibernate.validator.constraints.NotBlank;
import org.jasypt.util.password.BasicPasswordEncryptor;

/**
 *
 * @author Cristian
 */
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Cliente implements Serializable {
    @NotNull
    private int idCliente;
    @NotBlank
    private String nombre;
    @NotBlank
    private String apellido;
    @NotBlank
    private String tipoCliente;
    @NotBlank
    private String cif;
    @NotBlank
    private String login;
    @NotBlank
    private String password;

    public Cliente() {
    }

    public Cliente(int idCliente, String nombre, String apellido, String tipoCliente, String cif, String password, String login) {
        this.idCliente = idCliente;
        this.nombre = nombre;
        this.apellido = apellido;
        this.tipoCliente = tipoCliente;
        this.cif = cif;
        this.login = login;
        this.password = password;
    }

    public int getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(int idCliente) {
        this.idCliente = idCliente;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getTipoCliente() {
        return tipoCliente;
    }

    public void setTipoCliente(String tipoCliente) {
        this.tipoCliente = tipoCliente;
    }

    public String getCif() {
        return cif;
    }

    public void setCif(String cif) {
        this.cif = cif;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) { 
        this.password = password;
    }

    public boolean checkPassword(String unEncryptedPassword) { //no encriptado
        String cryptedPassword=getPassword(); // encriptado
       // BasicPasswordEncryptor passwordEncryptor = new BasicPasswordEncryptor();
       // String encryptedPassword = passwordEncryptor.encryptPassword(password); crear usuario

        if(unEncryptedPassword.equals(cryptedPassword)){
 
       // if (passwordEncryptor.checkPassword(unEncryptedPassword, cryptedPassword)) {
            return true;
        } else {
            return false;
        }
    }
}
