package com.fpmislata.daw2.presentacion;

public class Credenciales {

    private String password;
    private String login;
    
    public Credenciales(){}
    
    public Credenciales(String password, String login ){
    this.password=password;
    this.login=login;
    }


    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }


    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }
}