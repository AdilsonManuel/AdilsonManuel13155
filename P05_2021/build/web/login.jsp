<%-- 
    Document   : login
    Created on : Jan 31, 2021, 9:24:12 PM
    Author     : azm
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en" dir="ltr">
    <head>
        <meta charset="utf-8" />
        <meta content="width=device-width, initial-scale=1.0" name="viewport" />

        <title>IMOB Sells</title>
        <meta content="" name="description" />
        <meta content="" name="keywords" />

        <!-- Favicons -->
        <link rel="icon" href="assets/logo.png">
        <link rel="stylesheet" href="css/login.css">
    </head>
    <body>

        <form class="box" action="ContaServlet" method="GET">
            <h1>Login</h1>
            <input type="text" name="nome_usuario" placeholder="Username">
            <input type="password" name="senha_usuario" placeholder="Password">
            <input type="submit" name="" value="Login">
            <p class="para-2">
                Não tem conta? <a href="cliente.jsp">Increva - se no site</a>
            </p>
        </form>

    </body>
</html>

