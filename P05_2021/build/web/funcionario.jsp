<%-- 
    Document   : Cliente
    Created on : Jun 11, 2021, 11:18:46 PM
    Author     : azm
--%>

<jsp:useBean id="paisDAO" scope="page" class="DAO.PaisDAO"/>
<jsp:useBean id="provinciaDAO" scope="page" class="DAO.ProvinciaDAO"/>
<jsp:useBean id="municipioDAO" scope="page" class="DAO.MunicipioDAO"/>
<jsp:useBean id="comunaDAO" scope="page" class="DAO.ComunaDAO"/>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="Util.HtmlComboBoxes"%>
<%
    String username, tipoConta, uri, currentJspPageName;
    username = (String) session.getAttribute ("nome_usuario");
    tipoConta = (String) session.getAttribute ("tipo_conta");
    uri = request.getRequestURI ();
    currentJspPageName = uri.substring (uri.lastIndexOf ("/") + 1);
%>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <meta http-equiv="X-UA-Compatible" content="ie=edge">
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>IMOB Sells</title>

        <link rel = "stylesheet" type = "text/css" href = "css/mainForms.css"/>
        <link rel="icon" href="assets/logo.png">
        <link rel="stylesheet" href="css/bootstrap-5.0.0-dist/css/bootstrap.css">
        <link rel="stylesheet" type="text/css" href="Calendar/jscal2.css" />
        <link rel="stylesheet" type="text/css" href="Calendar/border-radius.css" />
        <link rel="stylesheet" type="text/css" href="Calendar/gold.css" />

        <script language="javascript" src="js/repopularCombo.js"></script>
        <script language="javascript" src="js/funcaoAuxiliar.js"></script>
        <script src="Calendar/jscal2.js"></script>
        <script src="Calendar/pt.js"></script>


    </head>


    <nav class="navbar navbar-collapse-sm navbar-dark bg-dark">
        <a href="homeRoot.jsp" class="navbar-brand">IMOB Sells</a>


        <ul class="navbar-nav mr-5">
            <li class="nav-item dropdown">
                <a class="nav-link dropdown-toggle" href="#" id="navbarDropdownMenuLink" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                    <i class="fas fa-user-cog"></i> <%=username%>
                </a>
                <ul class="dropdown-menu" aria-labelledby="navbarDropdownMenuLink">
                    <a class="dropdown-item" id="logOut" href="LogOutServlet">Log Out</a>
                </ul>
            </li>
        </ul>
    </nav>

    <div class="container">
        <div class="row">
            <div class="col-md-12">
                <form class="main-form needs-validation" id="clienteForm" name="clienteForm" method="POST">
                    <fieldset>
                        <legend class="legenda col-md-8">
                            Dados pessoais do funcionario
                        </legend>
                        <div class="row">
                            <div class="col">
                                <div class="form-group">
                                    <label for="txtPrimeiroNome">Primeiro nome</label>
                                    <input type="text" name="txtPrimeiroNome" id="txtPrimeiroNome" class="form-control">
                                    <small class="form-text text-muted">
                                        Digite o seu primeiro nome.
                                    </small>
                                </div>
                            </div>
                            <div class="col">
                                <div class="form-group">
                                    <label for="txtUltimoNome">Ultimo nome</label>
                                    <input type="text" name="txtUltimoNome" id="txtUltimoNome" class="form-control">
                                    <small class="form-text text-muted">
                                        Digite o seu ??ltimo nome.
                                    </small>
                                </div>
                            </div>
                        </div>

                        <div class="row">
                            <div class="col">
                                <div class="form-group">
                                    <label for="nome">Nome completo</label>
                                    <input type="text" name="txtnome" id="nome" class="form-control" required>
                                    <div class="invalid-feedback"> Digite o seu nome completo.</div>
                                    <small class="form-text text-muted">
                                        Digite o seu nome completo.
                                    </small>
                                </div>
                            </div>


                            <div class="col">
                                <div class="form-group">
                                    <label for="txtData_nascimento">Data de nascimento</label>
                                    <input name="txtData_nascimento" id="txtData_nascimento" class="form-control datepicker" required>
                                    <input type="button" value="Selecione uma data" id="btn_txtData_nascimento"/>
                                    <script type="text/javascript">//<![CDATA[
                                        var cal = Calendar.setup({
                                            onSelect: function (cal) {
                                                cal.hide()
                                            },
                                            showTime: true
                                        });
                                        cal.manageFields("btn_txtData_nascimento", "txtData_nascimento", "%Y / %m / %d");
                                        //]]>
                                    </script>
                                </div>
                            </div>
                        </div>
                        <hr>
                        <div class="form-group row mx-auto">
                            <div class="row">
                                <div class="col">
                                    <div class="form-control">
                                        <label for="txtSexo">Sexo</label>
                                        <%= new HtmlComboBoxes ().select ("sexo" , "clienteForm" , "comboSexo" , "sexo_pk" , "nome" , "" , "")%>
                                    </div>
                                </div>
                            </div>

                            <div class="row">
                                <div class="col">
                                    <div class="form-control">
                                        <label for="txtEstadoCivil">Estado civil</label>
                                        <%= new HtmlComboBoxes ().select ("estado_civil" , "clienteForm" , "comboEstado_civil" , "estado_civil_pk" , "nome" , "" , "")%>
                                    </div>
                                </div>
                            </div>

                            <div class="row">
                                <div class="col">
                                    <div class="form-control">
                                        <label for="txtOperadora">Operadora</label>
                                        <%= new HtmlComboBoxes ().select ("tipo_telefone" , "clienteForm" , "comboOperadora" , "tipo_telefone_pk" , "operadora" , "" , "")%>
                                    </div>
                                </div>

                            </div>
                            <div class="row">
                                <div class="col">
                                    <div class="form-control">
                                        <label for="txtTelefone">Telefone</label>
                                        <input type="tel" name="txtTelefone" id="txtTelefone" class="form-control">
                                        <small class="form-text text-muted">
                                            Digite o seu n??mero de telefone.
                                        </small>
                                    </div>
                                </div>
                            </div>
                        </div>

                    </fieldset>

                    <div class="row">
                        <div class="col">
                            <div class="form-control">
                                <label for="txtOperadora">Tipo email</label>
                                <%= new HtmlComboBoxes ().select ("tipo_email" , "clienteForm" , "comboEmail" , "tipo_email_pk" , "dominio" , "" , "")%>
                            </div>
                        </div>

                    </div>
                    <div class="row">
                        <div class="col">
                            <div class="form-control">
                                <label for="txtEmail">Email</label>
                                <input type="text" name="txtEmail" id="txtEmail" class="form-control">
                                <small class="form-text text-muted">
                                    Digite o seu email.
                                </small>
                            </div>
                        </div>
                    </div>
                    <div class="row">
                        <div class="form-control">
                            <label for="comuna">Tipo Funcionario</label>
                            <%= new HtmlComboBoxes ().select ("tipo_cliente" , "clienteForm" ,
                                    "cboTipoFuncionario" , "tipo_funcionario_pk" , "nome" , "" , "")%>
                        </div>
                    </div>
                    <hr>                     
                    <fieldset>
                        <legend class="legenda col-md-8">Dados do Endere??o</legend>
                        <div class="form-group">
                            <div class="row">
                                <div class="form-control">
                                    <label for="pais">Pais</label>
                                    <%--<%= paisDAO.gerarComboPaisesComEvento ("cbPais" , "clienteForm" , "cboProvincia" , null)%>--%>
                                </div>
                            </div>

                            <div class="row">
                                <div class="form-control">
                                    <label for="provincia">Provincia</label>
                                    <%--<%= new HtmlComboBoxes ().select ("provincia" , "clienteForm" ,"cboprovincia" , "provincia_pk" , "nome" , "" ,"")%>--%>
                                </div>
                            </div>


                            <div class="row">
                                <div class="form-control">
                                    <label for="municipio">Municipio</label>
                                    <%--<%= new HtmlComboBoxes ().select ("municipio" , "clienteForm" ,"cboMunicipio" , "municipio_pk" , "nome" , "" ,"")%>--%>
                                </div>
                            </div>

                            <div class="row">
                                <div class="form-control">
                                    <label for="comuna">Comuna</label>
                                    <%--<%= new HtmlComboBoxes ().select ("comuna" , "clienteForm" ,"cboComuna" , "comuna_pk" , "nome" , "" , "")%>--%>

                                </div>
                            </div>



                        </div>
                    </fieldset>
                    <hr>                     
                    <fieldset>
                        <legend class="legenda col-md-8">Dados da conta</legend>
                        <div class="row">
                            <div class="col">
                                <div class="form-group">
                                    <label for="nome_usuario">Nome do usu??rio</label>
                                    <input type="text" name="nome_usuario" id="nome_usuario" class="form-control" required>
                                    <div class="invalid-feedback">Digite o nome do usuario.</div>
                                </div>
                            </div>
                            <div class="col">
                                <div class="form-group">
                                    <label for="senha_usuario">Senha do usu??rio</label>
                                    <input type="password" name="senha_usuario" id="senha_usuario" class="form-control">
                                    <div class="invalid-feedback">Digite a senha do usuario</div>
                                </div>
                            </div>

                        </div>
                    </fieldset>
                    <hr>                     
                    <div class="row">
                        <div class="col-md-8" >
                            <input 
                                type="button"
                                class="btn btn-success"
                                value="Cadastrar"
                                onclick="guardarFormCliente('ClienteServlet?operacao=Cadastrar&&redirecionar=homeRoot.jsp')"
                                />
                            <button type="reset" class="btn btn-secondary ">
                                Limpar
                            </button>
                            <button type="button" class="btn btn-warning"onclick="location.href = 'homeRoot.jsp'">
                                Voltar
                            </button>
                        </div>
                    </div>
                </form>
            </div>
        </div>
    </div>

</html>
