<%-- 
    Document   : clienteListar
    Created on : 26-Jul-2021, 06:32:33
    Author     : azm
--%>

<%@page import="Modelo.PessoaModelo"%>
<%@page import="Modelo.ClienteModelo"%>
<%@page import="DAO.PessoaDAO"%>
<%@page import="DAO.ClienteDAO"%>
<jsp:useBean id="pessoaDAO" scope="page" class="DAO.PessoaDAO"/>
<jsp:useBean id="clienteDAO" scope="page" class="DAO.ClienteDAO"/>
<jsp:useBean id="pessoaModelo" scope="page" class="Modelo.PessoaModelo"/>
<%--<jsp:useBean id="clienteModelo" scope="page" class="Modelo.PessoaModelo"/>--%>
<%@page import="java.util.*"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%
    String usr = (String) request.getSession ().getAttribute ("nome_usuario");

%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="css/bootstrap-5.0.0-dist/css/bootstrap.css">
        <script language="javascript" src="js/funcaoAuxiliar.js"></script>

    </head>
    <body>
        <nav class="navbar navbar-collapse-sm navbar-dark bg-dark">
            <a href="homeRoot.jsp" class="navbar-brand">IMOB Sells</a>


            <ul class="navbar-nav mr-5">
                <li class="nav-item dropdown">
                    <a class="nav-link dropdown-toggle" href="#" id="navbarDropdownMenuLink" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                        <i class="fas fa-user-cog"></i> <%=usr%>
                    </a>
                    <ul class="dropdown-menu" aria-labelledby="navbarDropdownMenuLink">
                        <a class="dropdown-item" id="logOut" href="LogOutServlet">Log Out</a>
                    </ul>
                </li>
            </ul>
        </nav>
        <div class="container-fluid">


            <div class="row">
                <div class="col-md-10" style="margin-left: 450px">
                    <h2 class="legenda"> Todos os Registros de Clientes </h2>
                </div>

                <!--<form id="clienteForm" name="clienteForm" class="col-md-10" method="DELETE" style="margin-left: 100px">-->
                    <table class="table-bordered table-striped" id="clienteForm" >
                        <thead >
                            <tr>
                                <th>ID</th>
                                <th>Nome</th>
                                <th>Sexo</th>
                                <th>Data de Nascimento</th>
                                <th>Telefone</th>
                                <th>Email</th>
                                <th>Endere??o</th>
                                <th>Estado civil</th>
                                <th>Operacao 1</th>
                                <th>Operacao 2</th>
                            </tr>
                        </thead>
                        <tbody>
                            <% 
                                clienteDAO = new ClienteDAO ();
                                pessoaDAO = new PessoaDAO ();
                                List<ClienteModelo> cliente = clienteDAO.getListaCliente ();
       
                                for (ClienteModelo clienteModelo : cliente)
                                {                                 
                            %>
                            <%
                                            List<PessoaModelo> pessoaModelos = pessoaDAO.getDadosPessoa (clienteModelo.getPessoa_fk ());
                                            for (PessoaModelo pessoaModelo1 : pessoaModelos)
                                            {
                            %>
                            <tr class="active">

                                <td><%=pessoaModelo1.getPessoa_pk ()%></td>
                                <td><%=pessoaModelo1.getNome ()%></td>
                                <td><%=pessoaModelo1.getSexo_fk ().getNome ()%></td>
                                <td><%=pessoaModelo1.getData_nascimento ()%></td>
                                <td>
                                    <%=
                                    pessoaModelo1.getTelefone_fk ().getNumero ()
                                    %>
                                </td>
                                <td><%=
                                    pessoaModelo1.getEmail_fk ().getNome ()                                    
                                    %>
                                </td>
                                <td><%=
                                    pessoaModelo1.getEndereco_fk ().getNumero_casa ()
                                    %>
                                    <%=
                                        pessoaModelo1.getEndereco_fk ().getRua ()
                                    %>
                                    <%=
                                        pessoaModelo1.getEndereco_fk ().getLocalizacaoModelo ().getDesignacao ()
                                    %>
                                </td>
                                <td>
                                    <%=
                                    pessoaModelo1.getEstado_civil_fk ().getNome ()
                                    %>
                                </td>
                                <td><input type="button" class="btn btn-primary" value="Editar"></td>  
                                <td>                             
                                    <input type="button" class="btn btn-danger" value="Eliminar"
                                    onclick="eliminarFormCliente('ClienteServlet?operacao=eliminar&&redirecionar=clienteListar.jsp')"       
                                           />                        
                                </td>
                            </tr>
                            <%
                                 }
                             }
                            %>
                        </tbody>
                    </table>
                <!--</form>-->

            </div>
        </div>
    </body>
</html>
