
/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.fpmislata.daw2.presentacion;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fpmislata.daw2.datos.CuentaBancariaDAO;
import com.fpmislata.daw2.datos.EntidadBancariaDAO;
import com.fpmislata.daw2.datos.CuentaBancariaDAOImplHibernate;
import com.fpmislata.daw2.datos.EntidadBancariaDAOImplHibernate;
import com.fpmislata.daw2.datos.MovimientoBancarioDAO;
import com.fpmislata.daw2.datos.MovimientoBancarioDAOImplHibernate;
import com.fpmislata.daw2.modelo.CuentaBancaria;
import com.fpmislata.daw2.modelo.EntidadBancaria;
import com.fpmislata.daw2.modelo.MovimientoBancario;
import com.fpmislata.daw2.modelo.TipoEntidadBancaria;
import com.fpmislata.daw2.modelo.TipoMovimientoBancario;
import com.fpmislata.daw2.modelo.TransaccionBancaria;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 *
 * @author alumno
 */
@Controller
public class TransaccionBancariaController {

    @Autowired
    CuentaBancariaDAO cuentaBancariaDAO;
    @Autowired
    MovimientoBancarioDAO movimientoBancarioDAO;
          
           
    @RequestMapping(value = {"/TransaccionBancaria/"}, method = RequestMethod.POST,produces = "application/json")
    public void insert(HttpServletRequest httpRequest, HttpServletResponse httpServletResponse, @RequestBody String json) {
        try {
           
            httpServletResponse.setContentType("application/json; charset=UTF-8");
            ObjectMapper objectMapper = new ObjectMapper();
            TransaccionBancaria transaccionBancaria = (TransaccionBancaria)objectMapper.readValue(json, TransaccionBancaria.class);
            httpServletResponse.setStatus(HttpServletResponse.SC_OK);
            BigDecimal importe = transaccionBancaria.getImporte();
            String cuentaOrigen = transaccionBancaria.getCuentaOrigen();
            String cuentaDestino = transaccionBancaria.getCuentaDestino();
            
            MovimientoBancario movimientoBancarioOrigen = new MovimientoBancario();
            
            
            
            CuentaBancaria cuentaBancariaOrigen = cuentaBancariaDAO.findbyNumero(cuentaOrigen);
            cuentaBancariaOrigen.setSaldo(cuentaBancariaOrigen.getSaldo().subtract(importe));
            
            movimientoBancarioOrigen.setConcepto("Compra Servisport");
            movimientoBancarioOrigen.setCuenta(cuentaBancariaOrigen);
            movimientoBancarioOrigen.setFecha(new Date());
            movimientoBancarioOrigen.setImporte(importe);
            movimientoBancarioOrigen.setSaldoTotal(cuentaBancariaOrigen.getSaldo());
            movimientoBancarioOrigen.setTipoMovimientoBancario(TipoMovimientoBancario.DEBE);
            cuentaBancariaDAO.update(cuentaBancariaOrigen);
            movimientoBancarioDAO.insert(movimientoBancarioOrigen);
            
            CuentaBancaria cuentaBancariaDestino = cuentaBancariaDAO.findbyNumero(cuentaDestino);
            cuentaBancariaDestino.setSaldo(cuentaBancariaDestino.getSaldo().add(importe));
            MovimientoBancario movimientoBancarioDestino = new MovimientoBancario();
            
            movimientoBancarioDestino.setConcepto("Venta Servisport");
            movimientoBancarioDestino.setCuenta(cuentaBancariaDestino);
            movimientoBancarioDestino.setFecha(new Date());
            movimientoBancarioDestino.setImporte(importe);
            movimientoBancarioDestino.setSaldoTotal(cuentaBancariaDestino.getSaldo());
            movimientoBancarioDestino.setTipoMovimientoBancario(TipoMovimientoBancario.HABER);
            
            
            cuentaBancariaDAO.update(cuentaBancariaDestino);            
            movimientoBancarioDAO.insert(movimientoBancarioDestino);
            
            httpServletResponse.getWriter().println(json);
            
        } catch (Exception ex) {
            Logger.getLogger(EntidadBancariaController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
     
    
    
    
    
    
}
