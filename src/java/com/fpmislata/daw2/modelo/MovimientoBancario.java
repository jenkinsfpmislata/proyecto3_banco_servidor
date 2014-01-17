/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.fpmislata.daw2.modelo;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 *
 * @author alumno
 */
public class MovimientoBancario implements Serializable{
    private int idMovimientoBancario;
    private TipoMovimientoBancario tipoMovimientoBancario;
    private BigDecimal importe;
    private Date fecha;
    private BigDecimal SaldoTotal;
    private String concepto;
    private CuentaBancaria cuenta;

    public MovimientoBancario() {
    }

    public MovimientoBancario(int idMovimientoBancario, TipoMovimientoBancario tipoMovimientoBancario, BigDecimal importe, Date fecha, BigDecimal SaldoTotal, String concepto, CuentaBancaria cuenta) {
        this.idMovimientoBancario = idMovimientoBancario;
        this.tipoMovimientoBancario = tipoMovimientoBancario;
        this.importe = importe;
        this.fecha = fecha;
        this.SaldoTotal = SaldoTotal;
        this.concepto = concepto;
        this.cuenta = cuenta;
        
    }

    public int getIdMovimientoBancario() {
        return idMovimientoBancario;
    }

    public void setIdMovimientoBancario(int idMovimientoBancario) {
        this.idMovimientoBancario = idMovimientoBancario;
    }

    public TipoMovimientoBancario getTipoMovimientoBancario() {
        return tipoMovimientoBancario;
    }

    public void setTipoMovimientoBancario(TipoMovimientoBancario tipoMovimientoBancario) {
        this.tipoMovimientoBancario = tipoMovimientoBancario;
    }

    public BigDecimal getImporte() {
        return importe;
    }

    public void setImporte(BigDecimal importe) {
        this.importe = importe;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public BigDecimal getSaldoTotal() {
        return SaldoTotal;
    }

    public void setSaldoTotal(BigDecimal SaldoTotal) {
        this.SaldoTotal = SaldoTotal;
    }

    public String getConcepto() {
        return concepto;
    }

    public void setConcepto(String concepto) {
        this.concepto = concepto;
    }

    public CuentaBancaria getCuenta() {
        return cuenta;
    }

    public void setCuenta(CuentaBancaria cuenta) {
        this.cuenta = cuenta;
    }
    
    
}
