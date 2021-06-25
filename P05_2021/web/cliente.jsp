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

    <div class="container">
        <div class="row">
            <div class="col-md-12">
                <form class="main-form needs-validation" id="clienteForm" name="clienteForm" method="POST">
                    <fieldset>
                        <legend class="legenda col-md-8">
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
                                        Digite o seu último nome.
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

                        <div class="row">
                            <div class="col">
                                <div class="form-group">
                                    <label for="txtSexo">Sexo</label>
                                    <%= new HtmlComboBoxes ().select ("sexo" , "clienteForm" , "comboSexo" , "sexo_pk" , "nome" , "" , "")%>
                                </div>
                            </div>

                            <div class="col">
                                <div class="form-group">
                                    <label for="txtEstadoCivil">Estado civil</label>
                                    <%= new HtmlComboBoxes ().select ("estado_civil" , "clienteForm" , "comboEstado_civil" , "estado_civil_pk" , "nome" , "" , "")%>
                                </div>
                            </div>
                        </div>

                        <div class="row">
                            <div class="col">
                                <label for="txtOperadora">Operadora</label>
                                <%= new HtmlComboBoxes ().select ("telefone" , "clienteForm" , "comboTelefone" , "telefone_pk" , "operadora" , "" , "")%>
                            </div>

                            <div class="col">
                                <label for="txtNumero">Numero</label>
                                <input type="tel" name="txtNumero" id="txtNumero" class="form-control" required>
                            </div>
                        </div>


                        <div class="row">
                            <div class="col">
                                <label for="txtOperadora">Tipo email</label>
                                <%= new HtmlComboBoxes ().select ("email" , "clienteForm" , "comboEmail" , "email_pk" , "dominio" , "" , "")%>
                            </div>

                            <div class="col">
                                <label for="txtEmail">Email</label>
                                <input type="email" name="txtEmail" id="txtEmail" class="form-control" required>
                            </div>
                        </div>

                    </fieldset>

                    <fieldset>
                        <legend class="legenda col-md-8"> Endereço</legend>
                        <div class="form-group">
                            <div class="row">
                                <div class="col-md-8">
                                    <label for="pais">Pais</label>
                                    <%= paisDAO.gerarComboPaisesComEvento ("cbPais" , "clienteForm" , "cboProvincia" , null)%>
                                </div>
                            </div>

                            <div class="row">
                                <div class="col-md-8">
                                    <label for="provincia">Provincia</label>
                                    <%= new HtmlComboBoxes ().select ("provincia" , "clienteForm" ,
                                            "cboprovincia" , "provincia_pk" , "nome" , "" ,
                                            "")%>
                                </div>
                            </div>


                            <div class="row">
                                <div class="col-md-8">
                                    <label for="municipio">Municipio</label>
                                    <%= new HtmlComboBoxes ().select ("municipio" , "clienteForm" ,
                                            "cboMunicipio" , "municipio_pk" , "nome" , "" ,
                                            "")%>
                                </div>
                            </div>

                        </div>

                        <div class="form-group">
                            <div class="row">
                                <div class="col-md-8">
                                    <label for="comuna">Comuna</label>
                                    <%= new HtmlComboBoxes ().select ("comuna" , "clienteForm" ,
                                            "cboComuna" , "comuna_pk" , "nome" , "" , "")%>

                                </div>
                            </div>
                        </div>
                    </fieldset>

                    <fieldset>
                        <legend class="legenda col-md-8">Dados da conta</legend>
                        <div class="row">
                            <div class="col">
                                <div class="form-group">
                                    <label for="nome_usuario">Nome do usuário</label>
                                    <input type="text" name="nome_usuario" id="nome_usuario" class="form-control" required>
                                    <div class="invalid-feedback">Digite o nome do usuario.</div>
                                </div>
                            </div>
                            <div class="col">
                                <div class="form-group">
                                    <label for="senha_usuario">Senha do usuário</label>
                                    <input type="password" name="senha_usuario" id="senha_usuario" class="form-control">
                                    <div class="invalid-feedback">Digite a senha do usuario</div>
                                </div>
                            </div>

                        </div>
                    </fieldset>

                    <div class="row">
                        <div class="col-md-8" >
                            <input 
                                type="button"
                                class="btn btn-success"
                                value="Cadastrar"
                                onclick="guardarFormCliente('ClienteServlet?operacao=Cadastrar&&redirecionar=homeRoot.jsp')"
                                />
                            <button type="reset" class="btn btn-primary-outline">
                                Limpar
                            </button>
                            <button type="button" class="btn btn-dark-green"onclick="location.href = 'homeRoot.jsp'">
                                Voltar
                            </button>
                        </div>
                    </div>
                </form>
            </div>
        </div>
    </div>

</html>
