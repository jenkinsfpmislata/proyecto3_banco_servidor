/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.fpmislata.daw2.presentacion;

import com.fpmislata.daw2.datos.EntidadBancariaDAO;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;


/**
 *
 * @author alumno
 */
@Controller
public class PruebaContrroller {

    @RequestMapping({"/index.json"})
    public void read(HttpServletRequest request, HttpServletResponse response) {
        try {
            response.getWriter().println("Hola mundo");
        } catch (IOException ex) {
            Logger.getLogger(PruebaContrroller.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}