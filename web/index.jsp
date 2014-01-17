<%-- 
    Document   : index
    Created on : 07-oct-2013, 10:31:52
    Author     : alumno
--%>



<%@page import="com.fpmislata.daw2.modelo.MovimientoBancario"%>
<%@page import="com.fpmislata.daw2.datos.MovimientoBancarioDAOImplHibernate"%>
<%@page import="com.fpmislata.daw2.datos.MovimientoBancarioDAO"%>
<%@page import="com.fpmislata.daw2.modelo.CuentaBancaria"%>
<%@page import="com.fpmislata.daw2.datos.CuentaBancariaDAOImplHibernate"%>
<%@page import="com.fpmislata.daw2.datos.CuentaBancariaDAO"%>
<%@page import="com.fpmislata.daw2.datos.EntidadBancariaDAOImplHibernate"%>

<%@page import="java.util.List"%>
<%@page import="com.fpmislata.daw2.datos.EntidadBancariaDAO"%>
<%@page import="com.fpmislata.daw2.modelo.EntidadBancaria"%>
<%
    MovimientoBancarioDAO movimientoBancarioDAO = new MovimientoBancarioDAOImplHibernate();

    List<MovimientoBancario> listaMovimientos = movimientoBancarioDAO.findAll();
%>
<%@page import="java.util.Date"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel='stylesheet' href='css/bootstrap.css' media='all'>
        <link rel='stylesheet' href='css/bootstrap-responsive.css' media='all'>
        <link rel='stylesheet' href='css/estilogeneral.css' media='all'>
        <script src='js/bootstrap.js'></script>
        <script type='text/javascript' src='http://ajax.googleapis.com/ajax/libs/jquery/1.7.1/jquery.min.js'></script>

        <title>JSP Page</title>
        
    </head>
    <body>

        <table  class="table table-striped"  >
            <thead>
                <tr>
                    <td>id</td>
                    <td>Tipo</td>
                    <td>Importe</td>
                    <td>Fecha</td>
                    <td>Saldo</td>
                    <td>Concepto</td>
                    <td>Cuenta</td>
                    
                </tr>  
            </thead>
            <%

                for (MovimientoBancario cuenta : listaMovimientos) {
                    out.println("<tr class='success'>");
                    out.println("<td>" + cuenta.getIdMovimientoBancario()+ "</td>");
                    out.println("<td>" + cuenta.getTipoMovimientoBancario().name() +" </td>");
                    out.println("<td>" + cuenta.getImporte()+ "</td>");
                    out.println("<td>" + cuenta.getFecha().toString() + "</td>");
                    out.println("<td>" + cuenta.getSaldoTotal() + "</td>");
                    out.println("<td>" + cuenta.getConcepto() + "</td>");
                    out.println("<td>" + cuenta.getCuenta() + "</td>");
                    out.println("</tr>");

                }

            %>
        </table>
        <a href="formAnyadir.jsp">Anyadir entidad</a>
    </body>
</html>
