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
import java.io.IOException;
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
public class MovimientoBancarioController {

    @Autowired
    CuentaBancariaDAO cuentaBancariaDAO = new CuentaBancariaDAOImplHibernate();
    MovimientoBancarioDAO movimientoBancarioDAO = new MovimientoBancarioDAOImplHibernate();
            
    @RequestMapping(value = {"/MovimientoBancario/{idMovimientoBancario}"}, method = RequestMethod.GET,produces = "application/json")
    public void read(HttpServletRequest httpRequest, HttpServletResponse httpServletResponse, @PathVariable("idMovimientoBancario") int idMovimientoBancario, @RequestBody String json) {
        try {
            MovimientoBancario movimientoBancario = movimientoBancarioDAO.read(idMovimientoBancario);
            httpServletResponse.setStatus(HttpServletResponse.SC_OK);

            httpServletResponse.setContentType("application/json; charset=UTF-8");
            ObjectMapper objectMapper = new ObjectMapper();
            json = objectMapper.writeValueAsString(movimientoBancario);
            httpServletResponse.getWriter().println(json);
        } catch (Exception ex) {
            Logger.getLogger(EntidadBancariaController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
    @RequestMapping(value = {"/MovimientoBancario/{idMovimientoBancario}"}, method = RequestMethod.DELETE)
    public void delete(HttpServletRequest httpRequest, HttpServletResponse httpServletResponse, @PathVariable("idMovimientoBancario") int idMovimientoBancario, @RequestBody String json) {
        try {
            movimientoBancarioDAO.delete(idMovimientoBancario);
            httpServletResponse.setStatus(HttpServletResponse.SC_NO_CONTENT);
        } catch (Exception ex) {
            try {
                httpServletResponse.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
                ex.printStackTrace(httpServletResponse.getWriter());
                
            } catch (IOException ex1) {
                
            }
}
        }
    
    
     @RequestMapping(value = {"/MovimientoBancario"}, method = RequestMethod.GET,produces = "application/json")
    public void find(HttpServletRequest httpRequest, HttpServletResponse httpServletResponse, @RequestBody String json) {
        try {
            List<MovimientoBancario> listaMovimientos;
            
                listaMovimientos = movimientoBancarioDAO.findAll();
            
           
            httpServletResponse.setStatus(HttpServletResponse.SC_OK);
            httpServletResponse.setContentType("application/json; charset=UTF-8");
            ObjectMapper objectMapper = new ObjectMapper();
            json = objectMapper.writeValueAsString(listaMovimientos);
            httpServletResponse.getWriter().println(json);
        } catch (Exception ex) {
            Logger.getLogger(EntidadBancariaController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
     
     @RequestMapping(value = {"/MovimientoBancario/"}, method = RequestMethod.POST,produces = "application/json")
    public void insert(HttpServletRequest httpRequest, HttpServletResponse httpServletResponse, @RequestBody String json) {
        try {
           
            httpServletResponse.setContentType("application/json; charset=UTF-8");
            ObjectMapper objectMapper = new ObjectMapper();
            MovimientoBancario movimientoBancario = (MovimientoBancario)objectMapper.readValue(json, MovimientoBancario.class);;
            
            CuentaBancaria cuentaBancaria = movimientoBancario.getCuenta();
            
            if(movimientoBancario.getTipoMovimientoBancario()== TipoMovimientoBancario.DEBE){
            
                cuentaBancaria.setSaldo(cuentaBancaria.getSaldo().subtract(movimientoBancario.getImporte()));
                
            }else if(movimientoBancario.getTipoMovimientoBancario()== TipoMovimientoBancario.HABER){
                
                cuentaBancaria.setSaldo(cuentaBancaria.getSaldo().add(movimientoBancario.getImporte()));
                
                
            }
            movimientoBancario.setSaldoTotal(cuentaBancaria.getSaldo());
            
            cuentaBancariaDAO.update(cuentaBancaria);
            httpServletResponse.setStatus(HttpServletResponse.SC_OK);
            movimientoBancarioDAO.insert(movimientoBancario);
             httpServletResponse.getWriter().println(json);
            
        } catch (Exception ex) {
            Logger.getLogger(EntidadBancariaController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
    
     
     
}
