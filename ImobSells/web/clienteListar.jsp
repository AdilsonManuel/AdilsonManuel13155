<%-- 
    Document   : clienteListar
    Created on : 26-Jul-2021, 06:32:33
    Author     : azm
--%>
<%@page import="Modelo.ClienteModelo"%>
<%@page import="DAO.ClienteDAO"%>
<jsp:useBean id="clienteDAO" scope="page" class="DAO.ClienteDAO"/>
<jsp:useBean id="clienteModelo" scope="page" class="Modelo.ClienteModelo"/>
<%@page import="java.util.*"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>


<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="css/bootstrap-5.0.0-dist/css/bootstrap.css">
        <title>Listar cliente</title>
    </head>
    <body>
        <!--        <div style="margin-top:0px;" class="row">
        
        <%--<jsp:include page="Menu.jsp" flush=""></jsp:include>--%>
    </div>-->
        <div class="container-fluid">


            <div class="row">
                <div class="col-md-10" style="margin-left: 100px">
                    <h2 class="legenda"> Todos os Registros de Clientes </h2>
                </div>
                <div class="col-md-8 table-responsive" style="margin-left: 250px">
                    <table class="table table-striped table-bordered table-hover table-condensed card">
                        <thead>
                            <tr>
                                <th>ID</th>
                                <th>Nome</th>
                                <th>Sexo</th>
                                <th>Data de Nascimento</th>
                                <th>Telefone</th>
                                <th>Email</th>
                                <th>Endereço</th>
                                <th>Estado civil</th>
                                <th>Operacao 1</th>
                                <th>Operacao 2</th>
                            </tr>
                        </thead>
                        <tbody>
                            <% 
                                clienteDAO = new ClienteDAO ();
                                List<ClienteModelo> clientes = clienteDAO.getListaCliente ();
                                
                                for (ClienteModelo clienteModelo : clientes){
                            %>
                          
                           <tr class="active">
                                
                               
                               <td><%clienteModelo.getCliente_pk();%></td>
                               <td><%%></td>
                               <td></td>
                               <td></td>
                               <td></td>
                               <td></td>
                               <td></td>
                               <td></td>
                               <td><input type="button" class="btn btn-primary" value="Editar"></td>
                               <td><input type="button" class="btn btn-danger" value="Eliminar"></td>
                           </tr>
                            <%
                                 }
                            %>
                        </tbody>
                    </table>
                </div>
            </div>
        </div>
    </body>
</html>
