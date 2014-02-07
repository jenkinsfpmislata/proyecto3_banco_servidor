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
import com.fpmislata.daw2.datos.SucursalBancariaDAO;
import com.fpmislata.daw2.datos.SucursalBancariaDAOImplHibernate;
import com.fpmislata.daw2.modelo.CuentaBancaria;
import com.fpmislata.daw2.modelo.EntidadBancaria;
import com.fpmislata.daw2.modelo.SucursalBancaria;
import com.fpmislata.daw2.modelo.TipoEntidadBancaria;
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
public class SucursalBancariaController {

    @Autowired
    SucursalBancariaDAO sucursalBancariaDAO = new SucursalBancariaDAOImplHibernate();
            
    @RequestMapping(value = {"/SucursalBancaria/{idSucursalBancaria}"}, method = RequestMethod.GET,produces = "application/json")
    public void read(HttpServletRequest httpRequest, HttpServletResponse httpServletResponse, @PathVariable("idSucursalBancaria") int idSucursalBancaria, @RequestBody String json) {
        try {
            SucursalBancaria sucursalBancaria = sucursalBancariaDAO.read(idSucursalBancaria);
            httpServletResponse.setStatus(HttpServletResponse.SC_OK);

            httpServletResponse.setContentType("application/json; charset=UTF-8");
            ObjectMapper objectMapper = new ObjectMapper();
            json = objectMapper.writeValueAsString(sucursalBancaria);
            httpServletResponse.getWriter().println(json);
        } catch (Exception ex) {
            Logger.getLogger(EntidadBancariaController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
    @RequestMapping(value = {"/SucursalBancaria/{idSucursalBancaria}"}, method = RequestMethod.DELETE)
    public void delete(HttpServletRequest httpRequest, HttpServletResponse httpServletResponse, @PathVariable("idSucursalBancaria") int idSucursalBancaria, @RequestBody String json) {
        try {
            sucursalBancariaDAO.delete(idSucursalBancaria);
            httpServletResponse.setStatus(HttpServletResponse.SC_NO_CONTENT);
        } catch (Exception ex) {
            try {
                httpServletResponse.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
                ex.printStackTrace(httpServletResponse.getWriter());
                
            } catch (IOException ex1) {
                
            }
}
    
    }
    
    
     @RequestMapping(value = {"/SucursalBancaria/"}, method = RequestMethod.GET,produces = "application/json")
    public void find(HttpServletRequest httpRequest, HttpServletResponse httpServletResponse, @RequestBody String json) {
        try {
            
            List<SucursalBancaria> listaSucursales = sucursalBancariaDAO.findAll();
            httpServletResponse.setStatus(HttpServletResponse.SC_OK);
            httpServletResponse.setContentType("application/json; charset=UTF-8");
            ObjectMapper objectMapper = new ObjectMapper();
            json = objectMapper.writeValueAsString(listaSucursales);
            httpServletResponse.getWriter().println(json);
        } catch (Exception ex) {
            Logger.getLogger(EntidadBancariaController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
     
     @RequestMapping(value = {"/SucursalBancaria/"}, method = RequestMethod.POST,produces = "application/json")
    public void insert(HttpServletRequest httpRequest, HttpServletResponse httpServletResponse, @RequestBody String json) {
        try {
           
            httpServletResponse.setContentType("application/json; charset=UTF-8");
            ObjectMapper objectMapper = new ObjectMapper();
            SucursalBancaria sucursalBancaria = (SucursalBancaria)objectMapper.readValue(json, SucursalBancaria.class);;
            httpServletResponse.setStatus(HttpServletResponse.SC_OK);
            sucursalBancariaDAO.insert(sucursalBancaria);
             httpServletResponse.getWriter().println(json);
            
        } catch (Exception ex) {
            Logger.getLogger(EntidadBancariaController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
     
     
     @RequestMapping(value = {"/SucursalBancaria/{idSucursalBancaria}"}, method = RequestMethod.PUT,produces = "application/json")
    public void update(HttpServletRequest httpRequest, HttpServletResponse httpServletResponse, @PathVariable("idSucursalBancaria") int idSucursalBancaria, @RequestBody String json) {
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            
            SucursalBancaria sucursalBancaria = sucursalBancariaDAO.read(idSucursalBancaria);
            httpServletResponse.setStatus(HttpServletResponse.SC_OK);
            SucursalBancaria sucursalBancaria2 = (SucursalBancaria)objectMapper.readValue(json, SucursalBancaria.class);
            
            sucursalBancaria.setNombre(sucursalBancaria2.getNombre());
            sucursalBancaria.setCodigoSucursal(sucursalBancaria2.getCodigoSucursal());
            
            
            
            sucursalBancariaDAO.update(sucursalBancaria);
            
            
            httpServletResponse.setContentType("application/json; charset=UTF-8");
            json = objectMapper.writeValueAsString(sucursalBancaria);
            httpServletResponse.getWriter().println(json);
        } catch (Exception ex) {
            Logger.getLogger(EntidadBancariaController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
    
    
    
    
}
