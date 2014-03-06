
/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.fpmislata.daw2.presentacion;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fpmislata.daw2.datos.CuentaBancariaDAO;
import com.fpmislata.daw2.datos.EntidadBancariaDAO;
import com.fpmislata.daw2.datos.CuentaBancariaDAOImplHibernate;
import com.fpmislata.daw2.datos.EntidadBancariaDAOImplHibernate;
import com.fpmislata.daw2.datos.MovimientoBancarioDAO;
import com.fpmislata.daw2.datos.MovimientoBancarioDAOImplHibernate;
import com.fpmislata.daw2.modelo.CuentaBancaria;
import com.fpmislata.daw2.modelo.EntidadBancaria;
import com.fpmislata.daw2.modelo.MensajeError;
import com.fpmislata.daw2.modelo.MovimientoBancario;
import com.fpmislata.daw2.modelo.TipoEntidadBancaria;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.ConstraintViolation;
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
public class CuentaBancariaController {

    @Autowired
    CuentaBancariaDAO cuentaBancariaDAO = new CuentaBancariaDAOImplHibernate();
    MovimientoBancarioDAO movimientoBancarioDAO = new MovimientoBancarioDAOImplHibernate();
            
    @RequestMapping(value = {"/CuentaBancaria/{idCuentaBancaria}"}, method = RequestMethod.GET,produces = "application/json")
    public void read(HttpServletRequest httpRequest, HttpServletResponse httpServletResponse, @PathVariable("idCuentaBancaria") int idCuentaBancaria, @RequestBody String json) {
        try {
            CuentaBancaria cuentaBancaria = cuentaBancariaDAO.read(idCuentaBancaria);
            httpServletResponse.setStatus(HttpServletResponse.SC_OK);

            httpServletResponse.setContentType("application/json; charset=UTF-8");
            ObjectMapper objectMapper = new ObjectMapper();
            json = objectMapper.writeValueAsString(cuentaBancaria);
            httpServletResponse.getWriter().println(json);
        } catch (Exception ex) {
            Logger.getLogger(CuentaBancariaController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
    @RequestMapping(value = {"/CuentaBancaria/{idCuentaBancaria}"}, method = RequestMethod.DELETE)
    public void delete(HttpServletRequest httpRequest, HttpServletResponse httpServletResponse, @PathVariable("idCuentaBancaria") int idCuentaBancaria, @RequestBody String json) {
        try {
            cuentaBancariaDAO.delete(idCuentaBancaria);
            httpServletResponse.setStatus(HttpServletResponse.SC_NO_CONTENT);
        } catch (Exception ex) {
            try {
                httpServletResponse.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
                ex.printStackTrace(httpServletResponse.getWriter());
                
            } catch (IOException ex1) {
                
            }
}
    
    }
    
    
     @RequestMapping(value = {"/CuentaBancaria"}, method = RequestMethod.GET,produces = "application/json")
    public void find(HttpServletRequest httpRequest, HttpServletResponse httpServletResponse, @RequestBody String json) {
        try {
            List<CuentaBancaria> listaCuentas;
            CuentaBancaria cuenta = null;
            if(httpRequest.getParameter("cif")!=null){
                String cif= httpRequest.getParameter("cif");
                listaCuentas = cuentaBancariaDAO.findbyCif(cif);
                 
            }else{
                 listaCuentas = cuentaBancariaDAO.findAll();
            }
           
            httpServletResponse.setStatus(HttpServletResponse.SC_OK);
            httpServletResponse.setContentType("application/json; charset=UTF-8");
            ObjectMapper objectMapper = new ObjectMapper();
            
            json = objectMapper.writeValueAsString(listaCuentas);
            
            httpServletResponse.getWriter().println(json);
        } catch (Exception ex) {
            Logger.getLogger(CuentaBancariaController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
     
     @RequestMapping(value = {"/CuentaBancaria/"}, method = RequestMethod.POST,produces = "application/json")
    public void insert(HttpServletRequest httpRequest, HttpServletResponse httpServletResponse, @RequestBody String json) {
        try {
           
            httpServletResponse.setContentType("application/json; charset=UTF-8");
            ObjectMapper objectMapper = new ObjectMapper();
            CuentaBancaria cuentaBancaria = (CuentaBancaria)objectMapper.readValue(json, CuentaBancaria.class);;
            List<MensajeError> listaMensajes = new ArrayList<MensajeError>();
//            boolean error = false;
//            if(cuentaBancaria.getSucursalBancaria()==null){
//                MensajeError mensajeError = new MensajeError("Sucursal", "Sucursal no puede estar vacio");
//                listaMensajes.add(mensajeError);
//                 error = true;
//            }
//            if(cuentaBancaria.getNumeroDeCuenta().equals("")){
//                MensajeError mensajeError = new MensajeError("Numero", "Numero no puede estar vacio");
//                listaMensajes.add(mensajeError);
//                error = true;
//                 
//            }
//            if(cuentaBancaria.getDc().equals("")){
//                MensajeError mensajeError = new MensajeError("Dc", "Dc no puede estar vacio");
//                error = true;
//                 listaMensajes.add(mensajeError);
//            }
//            if(cuentaBancaria.getCif().equals("")){
//                MensajeError mensajeError = new MensajeError("Cif", "Cif  no puede estar vacio");
//                error = true;
//               listaMensajes.add(mensajeError);
//            }
//            if(cuentaBancaria.getCliente()==null){
//                MensajeError mensajeError = new MensajeError("Cliente", "Cliente  no puede estar vacio");
//                error = true;
//               listaMensajes.add(mensajeError);
//            }
//            if(cuentaBancaria.getSaldo()==null){
//                MensajeError mensajeError = new MensajeError("Saldo", "Saldo  no puede estar vacio");
//                error = true;
//               listaMensajes.add(mensajeError);
//            }
//            
//            if(error ==true){
//                httpServletResponse.setStatus(HttpServletResponse.SC_BAD_REQUEST);
//                String mensajeError = objectMapper.writeValueAsString(listaMensajes);
//                httpServletResponse.getWriter().println(mensajeError);
//            }else{
                
            httpServletResponse.setStatus(HttpServletResponse.SC_OK);
            cuentaBancariaDAO.insert(cuentaBancaria);
             httpServletResponse.getWriter().println(json);
            
        }catch (javax.validation.ConstraintViolationException ex) {
            httpServletResponse.setStatus(HttpServletResponse.SC_BAD_REQUEST);

            List<MensajeError> listaBussinesMessages = new ArrayList<MensajeError>();
            for (ConstraintViolation constraintViolation : ex.getConstraintViolations()) {
                
                MensajeError bussinesMessage = new MensajeError(constraintViolation.getPropertyPath().toString(), constraintViolation.getMessage());
                listaBussinesMessages.add(bussinesMessage);
            }
            httpServletResponse.setContentType("application/json; charset=UTF-8");
            ObjectMapper objectMapper = new ObjectMapper();
            try {
                json = objectMapper.writeValueAsString(listaBussinesMessages);
                httpServletResponse.getWriter().println(json);
            } catch (JsonProcessingException ex1) {
                Logger.getLogger(CuentaBancariaController.class.getName()).log(Level.SEVERE, null, ex1);
            } catch (IOException ex1) {
                Logger.getLogger(CuentaBancariaController.class.getName()).log(Level.SEVERE, null, ex1);
            }
            
        } catch (org.hibernate.exception.ConstraintViolationException ex) {
            httpServletResponse.setStatus(HttpServletResponse.SC_BAD_REQUEST);

            List<MensajeError> listaBussinesMessages = new ArrayList<MensajeError>();
               
                MensajeError bussinesMessage = new MensajeError(null, ex.getLocalizedMessage());
                listaBussinesMessages.add(bussinesMessage);
           
            httpServletResponse.setContentType("application/json; charset=UTF-8");
            ObjectMapper objectMapper = new ObjectMapper();
            try {
                json = objectMapper.writeValueAsString(listaBussinesMessages);
                httpServletResponse.getWriter().println(json);
            } catch (JsonProcessingException ex1) {
                Logger.getLogger(CuentaBancariaController.class.getName()).log(Level.SEVERE, null, ex1);
            } catch (IOException ex1) {
                Logger.getLogger(CuentaBancariaController.class.getName()).log(Level.SEVERE, null, ex1);
            }

        } catch (Exception ex) {

            try {
                httpServletResponse.setContentType("text/plain; charset=UTF-8");
                httpServletResponse.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
                ex.printStackTrace(httpServletResponse.getWriter());

            } catch (IOException ex1) {
            }
        }

    }
      @RequestMapping(value = {"/CuentaBancaria/{idCuentaBancaria}/movimientosBancarios/"}, method = RequestMethod.GET,produces = "application/json")
    public void findMovimientos(HttpServletRequest httpRequest, HttpServletResponse httpServletResponse,@PathVariable("idCuentaBancaria") int idCuentaBancaria, @RequestBody String json) {
        try {
            
            List<MovimientoBancario> listaMovimientos = movimientoBancarioDAO.findbyIdCuenta(idCuentaBancaria);
            httpServletResponse.setStatus(HttpServletResponse.SC_OK);

            httpServletResponse.setContentType("application/json; charset=UTF-8");
            ObjectMapper objectMapper = new ObjectMapper();
            json = objectMapper.writeValueAsString(listaMovimientos);
            httpServletResponse.getWriter().println(json);
        } catch (Exception ex) {
            Logger.getLogger(EntidadBancariaController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
     
     
     @RequestMapping(value = {"/CuentaBancaria/{idCuentaBancaria}"}, method = RequestMethod.PUT,produces = "application/json")
    public void update(HttpServletRequest httpRequest, HttpServletResponse httpServletResponse, @PathVariable("idCuentaBancaria") int idCuentaBancaria, @RequestBody String json) {
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            
            CuentaBancaria cuentaBancaria = cuentaBancariaDAO.read(idCuentaBancaria);
            httpServletResponse.setStatus(HttpServletResponse.SC_OK);
            CuentaBancaria cuentaBancaria2 = (CuentaBancaria)objectMapper.readValue(json, CuentaBancaria.class);
            
            cuentaBancaria.setCif(cuentaBancaria2.getCif());
            cuentaBancaria.setDc(cuentaBancaria2.getDc());
            cuentaBancaria.setNumeroDeCuenta(cuentaBancaria2.getNumeroDeCuenta());
            cuentaBancaria.setSaldo(cuentaBancaria2.getSaldo());
            cuentaBancaria.setSucursalBancaria(cuentaBancaria2.getSucursalBancaria());
            
            
            cuentaBancariaDAO.update(cuentaBancaria);
            
            
            httpServletResponse.setContentType("application/json; charset=UTF-8");
            json = objectMapper.writeValueAsString(cuentaBancaria);
            httpServletResponse.getWriter().println(json);
        } catch (Exception ex) {
            Logger.getLogger(EntidadBancariaController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
    
    
    
    
}
