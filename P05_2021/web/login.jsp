<%-- 
    Document   : login
    Created on : May 19, 2021, 1:02:07 AM
    Author     : azm
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <title>IMOB Sells-Login</title>
        <meta charset="utf-8">
        <meta name="auhor" content="Beans">
        <meta name="viewport" content="width=device-width, initial-scale=1.0, shrink-to-fit=no">
        <meta name="description" content="Simple Login for IMOB Sells page">
        <link href="css/login.css" rel="stylesheet">
        <link rel="icon" href="assets/logo.png">
        
        <script>
            function entrar ()
            {
                var form = document.getElementById("formLogin");
                
                form.action = "ContaServlet";
                form.submit();
            }
        </script>

    </head>

    <body>
        <div class="container">
            <label class="switch">
                <input type="checkbox" class="toggle-switch">
                <span class="slider"></span>
            </label>
            <div class="title">
                <h3>Bem vindo ao</h3>
                <h1>IMOB Sells</h1>
            </div>

            <div class="form-group">
                <div class="form-group-info">
                    <h3 class="sign-in-btn-tab">Faça Login</h3>
                    <h3 class="sign-up-btn-tab">Increva-se</h3>
                </div>
                <div class="sign-in-tab">
                    <form id="formLogin" method="POST">
                        <input type="text" placeholder="Nome do usuário">
                        <input type="password" placeholder="Sua Senha">
                        <button type="submit" class="btn-sign-in">Entrar</button>
                        <span class="forget-password">Esqueceu a Senha?</span>
                    </form>
                </div>
                <div class="sign-up-tab">
                    <form>
                        <input type="text" placeholder="Nome do usuário">
                        <input type="email" placeholder="Seu Email">
                        <input type="password" placeholder="Sua Senha">
                        <button type="submit" class="btn-sign-up">Registrar</button>
                    </form>
                </div>
            </div>


        </div>

        <script src="js/login.js" defer></script>
    </body>

</html>
