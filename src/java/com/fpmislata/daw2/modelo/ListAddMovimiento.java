/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.fpmislata.daw2.modelo;

import java.math.BigDecimal;

/**
 *
 * @author alumno
 */
public class ListAddMovimiento extends java.util.ArrayList<MovimientoBancario>{
    
public boolean add(MovimientoBancario movimiento){

    if(movimiento.getTipoMovimientoBancario()== TipoMovimientoBancario.DEBE){
        movimiento.getCuenta().setSaldo(movimiento.getCuenta().getSaldo().subtract(movimiento.getImporte()));
        
        
    }else{
        movimiento.getCuenta().setSaldo(movimiento.getCuenta().getSaldo().add(movimiento.getImporte()));
    
    }
    movimiento.setSaldoTotal(movimiento.getCuenta().getSaldo());
        
    return super.add(movimiento);
}   
}
