<%-- 
    Document   : produtoCadastrar
    Created on : 30-Jul-2021, 22:34:27
    Author     : azm
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="util.HtmlComboBoxes"%>
<!DOCTYPE html>
<%
    String usr = (String) request.getSession ().getAttribute ("nome_usuario");

%>
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

        <!--<script language="javascript" src="js/repopularCombo.js"></script>-->
        <script language="javascript" src="js/funcaoAuxiliar.js"></script>
        <script src="Calendar/jscal2.js"></script>
        <script src="Calendar/pt.js"></script>
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
        <form class="main-form needs-validation" id="clienteForm" name="clienteForm" method="POST">
            <div   class="col-md-10">
                <fieldset >
                    <legend class="legenda col-md-8" >Dados do Produto</legend> 

                    <div class="row" >
                        <div class="col">
                            <div class="form-group">
                                <label for="txtNomeProduto">Nome</label>
                                <input type="text" name="txtNomeProduto" id="txtNomeProduto" class="form-control">
                                <small class="form-text text-muted">
                                    Digite o nome do produto.
                                </small>
                            </div>
                        </div>
                        <div class="col">
                            <div class="form-group">
                                <label for="txtPreco">Preço</label>
                                <input type="" name="txtPreco" id="txtPreco" class="form-control">
                                <small class="form-text text-muted">
                                    Digite o preço.
                                </small>
                            </div>
                        </div>
                    </div>
                    <hr>
                    <div class="row" >
                        <div class="col-md-8" >
                            <i class="fa fa-folder" aria-hidden="true"></i>
                            <label class="control-label">Imagem </label>
                            <input type="file" class="form-control" name="imagem" >
                            <small class="form-text text-muted">
                                Selecione uma imagem para o produto.
                            </small>
                        </div>
                    </div>
                    <hr>
                    <div class="row" >
                        <div class="form-group">
                            <label for="txtData_nascimento">Data de cadastro</label>
                            <input name="txtData_nascimento" id="txtData_cadastro" class="form-control datepicker" required>
                            <input type="button" value="Selecione uma data" id="btn_txtData_cadastro"/>
                            <script type="text/javascript">
                                var cal = Calendar.setup({
                                    onSelect: function (cal) {
                                        cal.hide()
                                    },
                                    showTime: true
                                });
                                cal.manageFields("btn_txtData_cadastro", "txtData_cadastro", "%Y / %m / %d");
                            </script>
                        </div>
                        <div class="col">
                            <div class="form-group">
                                <label for="txtUltimoNome">Quantidade</label>
                                <input type="text" name="txtQualidade" id="txtQualidade" class="form-control">
                                <small class="form-text text-muted">
                                    Digite a quantidade do produto.
                                </small>
                            </div>
                        </div>
                        <div class="col">
                            <div class="form-group">
                                <label for="txtFornecedor">Fornecedor</label>
                                <input type="text" name="txtFornecedor" id="txtFornecedor" class="form-control">
                                <small class="form-text text-muted">
                                    Escolha o fornecedor.
                                </small>
                            </div>
                        </div>
                    </div>
                    <hr>
                    <!---->
                    <fieldset>
                        <legend class="legenda col-md-8">Dados da Categoria</legend>
                        <div class="form-group">
                            <div class="row" >
                                <div class="col-md-8" >
                                    <label class="control-label">Categoria</label>
                                    <%= new HtmlComboBoxes ().selectMany ("portfolio" , "clienteForm" , "comboNivel_1" , "portfolio_pk" , "designacao" , "" , "" , "1")%>
                                </div>

                                <div class="col-md-8" >
                                    <label class="control-label">Subcategoria 1</label>
                                    <%= new HtmlComboBoxes ().selectMany ("portfolio" , "clienteForm" , "comboNivel_2" , "portfolio_pk" , "designacao" , "" , "" , "2")%>
                                </div>

                                <div class="col-md-8" >
                                    <label class="control-label">subcategoria 2</label>
                                    <%= new HtmlComboBoxes ().selectMany ("portfolio" , "clienteForm" , "comboNivel_3" , "portfolio_pk" , "designacao" , "" , "" , "3")%>
                                </div>
                            </div>
                        </div>
                    </fieldset>

                </fieldset>
            </div>
            <hr>
            <div class="row">
                <div class="col-md-8" >
                    <input 
                        type="button"
                        class="btn btn-success"
                        value="Cadastrar"
                        onclick="guardarFormProduto('ProdutoServlet?operacao=Cadastrar&&redirecionar=homeRoot.jsp')"
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
    </body>
</html>
