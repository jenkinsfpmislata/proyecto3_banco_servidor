/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.fpmislata.daw2.modelo;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import javax.validation.constraints.NotNull;
import org.hibernate.validator.constraints.NotBlank;


/**
 *
 * @author alumno
 */

@JsonIgnoreProperties({"hibernateLazyInitializer","handler"})
public class CuentaBancaria implements Serializable {
    @NotNull
    private int idCuentaBancaria;
    @NotNull
    private SucursalBancaria sucursalBancaria;
    @NotBlank
    private String numeroDeCuenta;
    @NotBlank
    private String dc;
    @NotNull
    private BigDecimal saldo;
    @NotBlank
    private String cif;
    private List<MovimientoBancario> movimientos;
    @NotNull
    private Cliente cliente;

    public CuentaBancaria() {
        this.movimientos = new ArrayList<MovimientoBancario>();
    
    }
    
    public CuentaBancaria(int idCuentaBancaria, SucursalBancaria sucursalBancaria, String numeroDeCuenta, String dc, BigDecimal saldo, String cif, List<MovimientoBancario> movimientos) {
        this.idCuentaBancaria = idCuentaBancaria;
        this.sucursalBancaria = sucursalBancaria;
        this.numeroDeCuenta = numeroDeCuenta;
        this.dc = dc;
        this.saldo = saldo;
        this.cif = cif;
        this.movimientos = new ArrayList<MovimientoBancario>();
    }

    public int getIdCuentaBancaria() {
        return idCuentaBancaria;
    }

    public void setIdCuentaBancaria(int idCuentaBancaria) {
        this.idCuentaBancaria = idCuentaBancaria;
    }

    public SucursalBancaria getSucursalBancaria() {
        return sucursalBancaria;
    }

    public void setSucursalBancaria(SucursalBancaria sucursalBancaria) {
        this.sucursalBancaria = sucursalBancaria;
    }

    public String getNumeroDeCuenta() {
        return numeroDeCuenta;
    }

    public void setNumeroDeCuenta(String numeroDeCuenta) {
        this.numeroDeCuenta = numeroDeCuenta;
    }

    public String getDc() {
        return dc;
    }

    public void setDc(String dc) {
        this.dc = dc;
    }

    public BigDecimal getSaldo() {
        return saldo;
    }

    public void setSaldo(BigDecimal saldo) {
        this.saldo = saldo;
    }

    public String getCif() {
        return cif;
    }

    public void setCif(String cif) {
        this.cif = cif;
    }

    public List<MovimientoBancario> getMovimientos() {
        return movimientos;
    }

    public void setMovimientos(List<MovimientoBancario> movimientos) {
        this.movimientos = movimientos;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    
    
    
    
}
