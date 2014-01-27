/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.fpmislata.daw2.presentacion;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fpmislata.daw2.datos.EntidadBancariaDAO;
import com.fpmislata.daw2.datos.CuentaBancariaDAOImplHibernate;
import com.fpmislata.daw2.datos.EntidadBancariaDAOImplHibernate;
import com.fpmislata.daw2.datos.SucursalBancariaDAO;
import com.fpmislata.daw2.datos.SucursalBancariaDAOImplHibernate;
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
public class EntidadBancariaController {

    @Autowired
    EntidadBancariaDAO entidadBancariaDAO = new EntidadBancariaDAOImplHibernate();
    SucursalBancariaDAO sucursalBancariaDAO = new SucursalBancariaDAOImplHibernate();
            
    @RequestMapping(value = {"/EntidadBancaria/{idEntidad}"}, method = RequestMethod.GET,produces = "application/json")
    public void read(HttpServletRequest httpRequest, HttpServletResponse httpServletResponse, @PathVariable("idEntidad") int idEntidad, @RequestBody String json) {
        try {
            EntidadBancaria entidadBancaria = entidadBancariaDAO.read(idEntidad);
            httpServletResponse.setStatus(HttpServletResponse.SC_OK);

            httpServletResponse.setContentType("application/json; charset=UTF-8");
            ObjectMapper objectMapper = new ObjectMapper();
            json = objectMapper.writeValueAsString(entidadBancaria);
            httpServletResponse.getWriter().println(json);
        } catch (Exception ex) {
            Logger.getLogger(EntidadBancariaController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
    @RequestMapping(value = {"/EntidadBancaria/{idEntidad}"}, method = RequestMethod.DELETE)
    public void delete(HttpServletRequest httpRequest, HttpServletResponse httpServletResponse, @PathVariable("idEntidad") int idEntidad, @RequestBody String json) {
        try {
            entidadBancariaDAO.delete(idEntidad);
            httpServletResponse.setStatus(HttpServletResponse.SC_NO_CONTENT);
        } catch (Exception ex) {
            try {
                httpServletResponse.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
                ex.printStackTrace(httpServletResponse.getWriter());
                
            } catch (IOException ex1) {
                
            }
}
    
    }
    
    
     @RequestMapping(value = {"/EntidadBancaria"}, method = RequestMethod.GET,produces = "application/json")
    public void find(HttpServletRequest httpRequest, HttpServletResponse httpServletResponse, @RequestBody String json) {
        try {
            List<EntidadBancaria> listaEntidades;
            if(httpRequest.getParameter("nombre")!=null){
                String nombre = httpRequest.getParameter("nombre");
                listaEntidades = entidadBancariaDAO.findbyNombre(nombre);
                
            }else{
                listaEntidades = entidadBancariaDAO.findAll();
            }
            
            httpServletResponse.setStatus(HttpServletResponse.SC_OK);
            httpServletResponse.setContentType("application/json; charset=UTF-8");
            ObjectMapper objectMapper = new ObjectMapper();
            json = objectMapper.writeValueAsString(listaEntidades);
            httpServletResponse.getWriter().println(json);
        } catch (Exception ex) {
            Logger.getLogger(EntidadBancariaController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
     
          @RequestMapping(value = {"/EntidadBancaria/{idEntidad}/sucursalesBancarias/"}, method = RequestMethod.GET,produces = "application/json")
    public void findSucursales(HttpServletRequest httpRequest, HttpServletResponse httpServletResponse,@PathVariable("idEntidad") int idEntidad, @RequestBody String json) {
        try {
            List<SucursalBancaria> listaSucursales = sucursalBancariaDAO.findbyEntidad(idEntidad);
            httpServletResponse.setStatus(HttpServletResponse.SC_OK);

            httpServletResponse.setContentType("application/json; charset=UTF-8");
            ObjectMapper objectMapper = new ObjectMapper();
            json = objectMapper.writeValueAsString(listaSucursales);
            httpServletResponse.getWriter().println(json);
        } catch (Exception ex) {
            Logger.getLogger(EntidadBancariaController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
     
     
     
     
     @RequestMapping(value = {"/EntidadBancaria/"}, method = RequestMethod.POST,produces = "application/json")
    public void insert(HttpServletRequest httpRequest, HttpServletResponse httpServletResponse, @RequestBody String json) {
        try {
           
            httpServletResponse.setContentType("application/json; charset=UTF-8");
            ObjectMapper objectMapper = new ObjectMapper();
            EntidadBancaria entidadBancaria = (EntidadBancaria)objectMapper.readValue(json, EntidadBancaria.class);;
            httpServletResponse.setStatus(HttpServletResponse.SC_OK);
            entidadBancariaDAO.insert(entidadBancaria);
             httpServletResponse.getWriter().println(json);
            
        } catch (Exception ex) {
            Logger.getLogger(EntidadBancariaController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
     
     
     @RequestMapping(value = {"/EntidadBancaria/{idEntidad}"}, method = RequestMethod.PUT,produces = "application/json")
    public void update(HttpServletRequest httpRequest, HttpServletResponse httpServletResponse, @PathVariable("idEntidad") int idEntidad, @RequestBody String json) {
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            
            EntidadBancaria entidadBancaria = entidadBancariaDAO.read(idEntidad);
            httpServletResponse.setStatus(HttpServletResponse.SC_OK);
            EntidadBancaria entidadBancaria2 = (EntidadBancaria)objectMapper.readValue(json, EntidadBancaria.class);
            
            entidadBancaria.setCif(entidadBancaria2.getCif());
            entidadBancaria.setCodigoEntidad(entidadBancaria2.getCodigoEntidad());
            entidadBancaria.setNombre(entidadBancaria2.getNombre());
            entidadBancaria.setTipoEntidadBancaria(entidadBancaria2.getTipoEntidadBancaria());
            
            entidadBancariaDAO.update(entidadBancaria);
            
            
            httpServletResponse.setContentType("application/json; charset=UTF-8");
            json = objectMapper.writeValueAsString(entidadBancaria);
            httpServletResponse.getWriter().println(json);
        } catch (Exception ex) {
            Logger.getLogger(EntidadBancariaController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
    
    
    
    
}
