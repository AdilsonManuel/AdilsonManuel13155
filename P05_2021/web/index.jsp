<%-- 
    Document   : index
    Created on : May 19, 2021, 12:39:43 AM
    Author     : azm
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>IMOB Sells</title>
        <script type="text/javascript" src="js/jquery.js"></script>
        <link rel = "stylesheet" type = "text/css" href = "css/apresentacao.css"/>
        <link rel="icon" href="assets/logo.png">
    </head>
    <body>
        <div id="container">
            <div id="logo">
                <div id="logotipo">
                    <img src="assets/logo.png" alt="logo"/>    
                </div>
                <div class="posicaocima">
                    <div id="data">
                        <SCRIPT language="JavaScript" src="js/getDate_pt.js"></SCRIPT>
                    </div>
                </div>
            </div>
            <div id="conteudo">
                <div id="apresentacao">
                    <h1> Apresentação</h1> 
                    <p> Esta aplicação foi desenvolvida pelo estudante <span>Adilson Zumba Manuel, Nº 13155, 3º ano de engenharia
                            Informática,</span> no âmbito do Projecto 
                        Final da cadeira de Sistemas Distribuídos e Paralelos I, sob orientação do Prof. Dr. Aires Manuel 
                        Araújo Veloso. Este projecto é propriedade da Faculdade de Engenharia da Universidade Católica de Angola 
                        (FEUCAN) e é permitido o uso exclusivo aos estudantes da FEUCAN. Nenhuma parte desta obra poderá ser gravada,
                        reproduzida ou transmitida por qualquer meio, sem prévia autorização escrita do autor. As transgressões 
                        serão passíveis de penalizações previstas na legislação em vigor. Se concorda com os termos acima 
                        descritos <span>aceite e prima no link (aceito)</span> para entrar na aplicação, se não concorda com os termos acima
                        descritos <span>prima no link (Cancelar)</span> para sair da aplicação. 
                    </p> 
                    <div class="centrar"><a href="login.jsp">ACEITO</a> <a href="javascript:window.close();" >CANCELAR</a></div>        
                </div>
            </div> 
        </div>
        <div id="Rodape">
            <p>Aplicação web da Loja IMOB Sells &copy; Todos os direitos reservados</p>
            <p>Design by Adilson Manuel</p>
        </div>    
    </body>
</html>
