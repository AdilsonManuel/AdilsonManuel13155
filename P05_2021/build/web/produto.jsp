<%-- 
    Document   : produto
    Created on : Jun 30, 2021, 2:55:01 PM
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
                            Dados do produto
                        </legend>
                        <div class="row">
                            <div class="col">
                                <div class="form-group">
                                    <label for="txt_imagem">Imagem</label>
                                    <input type="image" name="txt_imagem" id="txt_imagem" class="form-control">
                                    <small class="form-text text-muted">
                                        insere uma imagem.
                                    </small>
                                </div>
                            </div>
                            <div class="col">
                                <div class="form-group">
                                    <label for="txt_descricao">Descricao</label>
                                    <input type="text" name="txt_descricao" id="txt_descricao" class="form-control">
                                    <small class="form-text text-muted">
                                        Insere uma descricao.
                                    </small>
                                </div>
                            </div>
                        </div>

                        <div class="row">
                            <div class="col">
                                <div class="form-group">
                                    <label for="nome">Portfolio codigo</label>
                                    <input type="text" name="txt_portfolio_codigo" id="txt_portfolio_codigo" class="form-control" required>
                                    <div class="invalid-feedback"> Inserir o codigo do portofolio.</div>
                                    <small class="form-text text-muted">
                                        Selecione um codigo
                                    </small>
                                </div>
                            </div>


                            <div class="col">
                                <div class="form-group">
                                    <label for="txt_data_registo">Data de registo</label>
                                    <input name="txt_data_registo" id="txt_data_registo" class="form-control datepicker" required>
                                    <input type="button" value="Selecione uma data" id="btn_txt_data_registo"/>
                                    <script type="text/javascript">//<![CDATA[
                                        var cal = Calendar.setup({
                                            onSelect: function (cal) {
                                                cal.hide()
                                            },
                                            showTime: true
                                        });
                                        cal.manageFields("btn_txt_data_registo", "txt_data_registo", "%Y / %m / %d");
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
                                        <label for="txtFornecedor">Fornecedor</label>
                                        <%= new HtmlComboBoxes ().select ("fornecedor" , "clienteForm" , "comboFornecedor" , "fornecedor_pk" , "nome" , "" , "")%>
                                    </div>
                                </div>
                            </div>

                            <div class="col">
                                <div class="form-group">
                                    <label for="nome">Preco</label>
                                    <input type="number" name="txt_preco" id="txt_preco" class="form-control" required>
                                    <div class="invalid-feedback"> Inserir o preço de compra.</div>
                                    <small class="form-text text-muted">
                                        Digite o preço do produto
                                    </small>
                                </div>
                            </div>

                            <div class="col">
                                <div class="form-group">
                                    <label for="nome">Quantidade</label>
                                    <input type="number" name="txt_quantidade" id="txt_quantidade" class="form-control" required>
                                    <div class="invalid-feedback"> Inserir a quantidade do produto.</div>
                                    <small class="form-text text-muted">
                                        Digite a quantidade do produto
                                    </small>
                                </div>
                            </div>

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
