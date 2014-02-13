package com.fpmislata.daw2.presentacion;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fpmislata.daw2.datos.ClienteDAO;
import com.fpmislata.daw2.datos.ClienteDAOImplHibernate;
import com.fpmislata.daw2.datos.CuentaBancariaDAO;
import com.fpmislata.daw2.datos.CuentaBancariaDAOImplHibernate;
import com.fpmislata.daw2.modelo.Cliente;
import com.fpmislata.daw2.modelo.CuentaBancaria;
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
public class ClientesController {

    @Autowired
    ClienteDAO clientesDAO = new ClienteDAOImplHibernate();
    CuentaBancariaDAO cuentaBancariaDAO = new CuentaBancariaDAOImplHibernate();

    
    
    
        @RequestMapping(value = {"/Cliente/{idCliente}/CuentaBancaria"}, method = RequestMethod.GET, produces = "application/json")
    public void readCuentaBancaria(HttpServletRequest httpRequest, HttpServletResponse httpServletResponse, @PathVariable("idCliente") int idCliente, @RequestBody String json) {
        try {
            Cliente cliente = clientesDAO.read(idCliente);
            List<CuentaBancaria> listaCuentas = cuentaBancariaDAO.findbyCliente(cliente);

            httpServletResponse.setStatus(HttpServletResponse.SC_OK);

            httpServletResponse.setContentType("application/json; charset=UTF-8");
            ObjectMapper objectMapper = new ObjectMapper();
            json = objectMapper.writeValueAsString(listaCuentas);
            httpServletResponse.getWriter().println(json);
        } catch (Exception ex) {
            Logger.getLogger(ClientesController.class.getName()).log(Level.SEVERE, null, ex);
        }

        }
    
    
    
    @RequestMapping(value = {"/Cliente/{idCliente}"}, method = RequestMethod.GET, produces = "application/json")
    public void read(HttpServletRequest httpRequest, HttpServletResponse httpServletResponse, @PathVariable("idCliente") int idCliente, @RequestBody String json) {
        try {
            Cliente cliente = clientesDAO.read(idCliente);

            httpServletResponse.setStatus(HttpServletResponse.SC_OK);

            httpServletResponse.setContentType("application/json; charset=UTF-8");
            ObjectMapper objectMapper = new ObjectMapper();
            json = objectMapper.writeValueAsString(cliente);
            httpServletResponse.getWriter().println(json);
        } catch (Exception ex) {
            Logger.getLogger(ClientesController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
  

    @RequestMapping(value = {"/Cliente/{idCliente}"}, method = RequestMethod.DELETE)
    public void delete(HttpServletRequest httpRequest, HttpServletResponse httpServletResponse, @PathVariable("idCliente") int idCliente, @RequestBody String json) {
        try {
            clientesDAO.delete(idCliente);

            httpServletResponse.setStatus(HttpServletResponse.SC_NO_CONTENT);
        } catch (Exception ex) {
            try {
                httpServletResponse.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
                ex.printStackTrace(httpServletResponse.getWriter());

            } catch (IOException ex1) {
            }
        }

    }

    @RequestMapping(value = {"/Cliente/"}, method = RequestMethod.POST, produces = "application/json")
    public void insert(HttpServletRequest httpRequest, HttpServletResponse httpServletResponse, @RequestBody String json) {
        try {

            httpServletResponse.setContentType("application/json; charset=UTF-8");
            ObjectMapper objectMapper = new ObjectMapper();
            Cliente cliente = (Cliente) objectMapper.readValue(json, Cliente.class);

            httpServletResponse.setStatus(HttpServletResponse.SC_OK);
            clientesDAO.insert(cliente);

            httpServletResponse.getWriter().println(json);

        } catch (Exception ex) {
            Logger.getLogger(ClientesController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    @RequestMapping(value = {"/Cliente/{idCliente}"}, method = RequestMethod.PUT, produces = "application/json")
    public void update(HttpServletRequest httpRequest, HttpServletResponse httpServletResponse, @PathVariable("idCliente") int idCliente, @RequestBody String json) {
        try {
            ObjectMapper objectMapper = new ObjectMapper();


            Cliente cliente = clientesDAO.read(idCliente);
            httpServletResponse.setStatus(HttpServletResponse.SC_OK);
            Cliente cliente2 = (Cliente) objectMapper.readValue(json, Cliente.class);

            cliente.setApellido(cliente2.getApellido());
            cliente.setIdCliente(cliente2.getIdCliente());
            cliente.setNombre(cliente2.getNombre());
            cliente.setTipoCliente(cliente2.getTipoCliente());
            cliente.setCif(cliente2.getCif());
            cliente.setPassword(cliente2.getPassword());
            cliente.setLogin(cliente2.getLogin());

            clientesDAO.update(cliente);

            httpServletResponse.setContentType("application/json; charset=UTF-8");
            json = objectMapper.writeValueAsString(cliente);
            httpServletResponse.getWriter().println(json);
        } catch (Exception ex) {
            Logger.getLogger(ClientesController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    @RequestMapping(value = {"/Cliente"}, method = RequestMethod.GET, produces = "application/json")
    public void find(HttpServletRequest httpRequest, HttpServletResponse httpServletResponse, @RequestBody String json) {
        try {
            List<Cliente> listaClientes;
            if (httpRequest.getParameter("cif") != null) {
                String cif = httpRequest.getParameter("cif");
                listaClientes = clientesDAO.findbyCif(cif);

            } else {
                listaClientes = clientesDAO.findAll();
            }

            httpServletResponse.setStatus(HttpServletResponse.SC_OK);
            httpServletResponse.setContentType("application/json; charset=UTF-8");
            ObjectMapper objectMapper = new ObjectMapper();
            json = objectMapper.writeValueAsString(listaClientes);
            httpServletResponse.getWriter().println(json);
        } catch (Exception ex) {
            Logger.getLogger(ClientesController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
}