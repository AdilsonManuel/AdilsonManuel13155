<%-- 
    Document   : Cliente
    Created on : Jun 11, 2021, 11:18:46 PM
    Author     : azm
--%>

<jsp:useBean id="paisDAO" scope="page" class="DAO.PaisDAO"/>
<jsp:useBean id="provinciaDAO" scope="page" class="DAO.ProvinciaDAO"/>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="Util.HtmlComboBoxes"%>
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

        <script type="text/javascript">
            $(function () {
                $('.datepicker').datepicker({
                    format: 'yyyy-mm-dd'
                });
            });
        </script>
    </head>


    <nav class="navbar navbar-expand-sm navbar-dark bg-dark">
        <a href="#" class="navbar-brand">IMOB Sells</a>
        <button class="navbar-toggler" data-toggle="collapse" data-target="#navbarMenu">
            <span class="navbar-toggler-icon"></span>
        </button>
        <div class="collapse navbar-collapse" id="navbarMenu">
            <ul class="navbar-nav">
                <li class="nav-item">
                    <a href="#" class="nav-link">Visualizar</a>
                </li>
                <li class="nav-item">
                    <a href="home.jsp" class="nav-link">Pagina inicial</a>
                </li>
            </ul>
        </div>

    </nav>

    <form action="" class="main-form needs-validation">
        <div class="row">
            <div class="col">
                <div class="form-group">
                    <label for="firstname">Primeiro nome</label>
                    <input type="text" name="txtPrimeiroNome" id="txtPrimeiroNome" class="form-control">
                    <small class="form-text text-muted">
                        Digite o seu primeiro nome.
                    </small>
                </div>
            </div>
            <div class="col">
                <div class="form-group">
                    <label for="lastname">Ultimo nome</label>
                    <input type="text" name="txtUltimoNome" id="txtUltimoNome" class="form-control">
                    <small class="form-text text-muted">
                        Digite o seu último nome.
                    </small>
                </div>
            </div>
        </div>

        <div class="row">
            <div class="col">
                <div class="form-group">
                    <label for="username">Nome completo</label>
                    <input type="text" name="username" id="username" class="form-control" required>
                    <div class="invalid-feedback"> Digite o seu nome completo.</div>

                </div>
            </div>

            <div class="col">
                <div class="form-group">
                    <label for="username">Data de nascimento</label>
                    <input type="date" name="username" id="username" class="form-control datepicker" required>
                    <div class="invalid-feedback">Digite selecione uma data válida.</div>
                </div>
            </div>
        </div>

        <div class="row">
            <div class="col">
                <div class="form-group">
                    <label for="username">Nome do usuário</label>
                    <input type="text" name="username" id="username" class="form-control" required>
                    <div class="invalid-feedback">Digite o nome do usuario.</div>
                </div>
            </div>
            <div class="col">
                <div class="form-group">
                    <label for="password">Senha do usuário</label>
                    <input type="password" name="password" id="password" class="form-control">
                    <div class="invalid-feedback">Digite a senha do usuario</div>
                </div>
            </div>

        </div>

        <div class="row">
            <div class="col">
                <div class="form-group">
                    <label for="gender">Sexo</label>
                    <%= new HtmlComboBoxes ().select ("sexo" , "formCliente" , "comboSexo" , "sexo_pk" , "nome" , "" , "")%>
                </div>
            </div>

            <div class="col">
                <div class="form-group">
                    <label for="gender">Estado civil</label>
                    <%= new HtmlComboBoxes ().select ("estado_civil" , "formCliente" , "comboEstado_civil" , "estado_civil_pk" , "nome" , "" , "")%>
                </div>
            </div>
        </div>

        <div class="form-group">
            <label for="username">Telefone</label>
            <input type="telefone" name="telefone" id="telefone" class="form-control" required>

            <label for="email">Email</label>
            <input type="email" name="email" id="email" class="form-control" required>
        </div>

        <div class="form-group">
            <div class="row">
                <div class="col-md-8">
                    <label for="gender">Pais</label>
                    <%= paisDAO.gerarComboPaisesComEvento ("" , "clienteForm" , "comboProvincia" , null)%>
                </div>
            </div>

            <div class="row">
                <div class="col-md-8">
                    <label for="gender">Provincia</label>
                    <%= new HtmlComboBoxes ().select ("provincia" , "clienteForm" , "comboProvincia" , "provincia_pk" , "nome" , "" , "")%>
                </div>
            </div>


            <div class="row">
                <div class="col-md-8">
                    <label for="gender">Municipio</label>
                    <%= new HtmlComboBoxes ().select ("municipio" , "clienteForm" , "comboMunicipio" , "municipio_pk" , "nome" , "" , "")%>
                </div>
            </div>

        </div>

        <div class="form-group">
            <div class="row">
                <div class="col-md-8">
                    <label for="gender">Comuna</label>
                    <%= new HtmlComboBoxes ().select ("comuna" , "clienteForm" , "comboComuna" , "comuna_pk" , "nome" , "" , "")%>

                </div>
            </div>

            <label for="gender">Bairro</label>
            <select name="gender" id="gender" class="form-control">
                <option value="male">Male</option>
                <option value="female">Female</option>
            </select>

            <div class="row">
                <div class="col-md-8">
                    <label for="gender">Endereco</label>
                    <%= new HtmlComboBoxes ().select ("endereco" , "clienteForm" , "comboEndereco" , "endereco_pk" , "nome" , "" , "")%>

                </div>
            </div>
        </div>
        <div class="row">
            <div class="col-md-8" >
                <input
                    type="button"
                    class="btn btn-success"
                    value="cadastrar"
                    onclick=""
                    />
                <button type="reset" class="btn btn-primary-outline">
                    Limpar
                </button>
                <button
                    type="button"
                    class="btn btn-dark-green"
                    onclick="location.href = 'homeRoot.jsp'"
                    >
                    Voltar
                </button>
            </div>
        </div>
    </form>

</html>
