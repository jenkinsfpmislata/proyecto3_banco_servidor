package com.fpmislata.daw2.presentacion;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fpmislata.daw2.datos.ClienteDAO;
import com.fpmislata.daw2.datos.ClienteDAOImplHibernate;
import com.fpmislata.daw2.modelo.Cliente;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class SessionController {

    @Autowired
    ClienteDAO clientesDAO = new ClienteDAOImplHibernate();

    @RequestMapping(value = {"/session"}, method = RequestMethod.POST, produces = "application/json")
    public void readByLogin(HttpServletRequest httpRequest, HttpServletResponse httpServletResponse, @RequestBody String json) {



        try {

            httpServletResponse.setContentType("application/json; charset=UTF-8");
            ObjectMapper objectMapper = new ObjectMapper();
            Credenciales credenciales = objectMapper.readValue(json, Credenciales.class);

            Cliente cliente = clientesDAO.readByLogin(credenciales.getLogin());

            if (cliente == null) {
                httpServletResponse.setStatus(HttpServletResponse.SC_FORBIDDEN);
                json = null;
                httpServletResponse.getWriter().println(json);

            } else {

                if (cliente.checkPassword(credenciales.getPassword()) == true) {

                    HttpSession httpSession = httpRequest.getSession(true); //crea sesion si no existe
                    httpSession.setAttribute("idCliente", cliente.getIdCliente()); //nombre variable y valor del id de cliente ¡¡¡¡JAMAS el cliente

                    httpServletResponse.setStatus(HttpServletResponse.SC_OK);

                    httpServletResponse.setContentType("application/json; charset=UTF-8");
                    ObjectMapper objectMapper2 = new ObjectMapper();
                    json = objectMapper2.writeValueAsString(cliente);
                    httpServletResponse.getWriter().println(json);

                } else {
                    httpServletResponse.setStatus(HttpServletResponse.SC_FORBIDDEN);
                    httpServletResponse.setContentType("application/json; charset=UTF-8");
                    //  ObjectMapper objectMapper = new ObjectMapper();
                    //json = objectMapper.writeValueAsString(cliente);
                    json = null;
                    httpServletResponse.getWriter().println(json);

                }
            }

        } catch (Exception ex) {
            Logger.getLogger(SessionController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
    //faltan delete y get
    //!!! cuando haga metodo get no pasar el id del idCiente sacado de la sesion
}