<!-- =======================================================
* Ficheiro:   home.jsp
* Autor:     Adilson Zumba Manuel
======================================================== -->

<!DOCTYPE html>
<html lang="en">

    <head>
        <meta charset="utf-8" />
        <meta content="width=device-width, initial-scale=1.0" name="viewport" />

        <title>IMOB Sells</title>
        <meta content="" name="description" />
        <meta content="" name="keywords" />

        <!-- Favicons -->
        <link rel="icon" href="assets/logo.png">

        <!-- Google Fonts -->
        <link
            href="https://fonts.googleapis.com/css?family=Open+Sans:300,300i,400,400i,600,600i,700,700i|Roboto:300,300i,400,400i,500,500i,600,600i,700,700i|Poppins:300,300i,400,400i,500,500i,600,600i,700,700i"
            rel="stylesheet" />

        <!-- Vendor CSS Files -->
        <link href="css/vendor/animate.css/animate.min.css" rel="stylesheet" />
        <link href="css/vendor/aos/aos.css" rel="stylesheet" />
        <link href="css/vendor/bootstrap/css/bootstrap.min.css" rel="stylesheet" />
        <link href="css/vendor/bootstrap-icons/bootstrap-icons.css" rel="stylesheet" />
        <link href="css/vendor/boxicons/css/boxicons.min.css" rel="stylesheet" />
        <link href="css/vendor/fontawesome-free/css/all.min.css" rel="stylesheet" />
        <link href="css/vendor/glightbox/css/glightbox.min.css" rel="stylesheet" />
        <link href="css/vendor/swiper/swiper-bundle.min.css" rel="stylesheet" />

        <!-- Template Main CSS File -->
        <link href="css/home.css" rel="stylesheet" />


    </head>

    <body>
        <!-- ======= Top Bar ======= -->
        <div id="topbar" class="d-flex align-items-center fixed-top">
            <div class="container d-flex align-items-center justify-content-center justify-content-md-between">
                <div class="align-items-center d-none d-md-flex">
                    <i class="bi bi-clock"></i> Segunda-feira - Sexta-feira, 8AM to 18AM
                </div>
                <div class="d-flex align-items-center">
                    <i class="bi bi-phone"></i> Ligue para n�s: +244 929 214 112
                </div>
            </div>
        </div>

        <!-- ======= Header ======= -->
        <header id="header" class="fixed-top">
            <div class="container d-flex align-items-center">
                <a href="index.html" class="logo me-auto"><img src="css/img/logo12.png" alt="" /></a>

                <nav id="navbar" class="navbar order-last order-lg-0">
                    <ul>
                        <li>
                            <a class="nav-link scrollto" href="#hero">Pagina Inicial</a>
                        </li>
                        <li class="dropdown">
                            <a href="#"><span>Clientes</span> <i class="bi bi-chevron-down"></i></a>
                            <ul>
                                <li><a href="cliente.jsp">Cadastrar</a></li>
                                <li><a href="#">Visualizar</a></li>
                            </ul>
                        </li>
                        <li class="dropdown">
                            <a href="#"><span>Funcion�rio</span> <i class="bi bi-chevron-down"></i></a>
                            <ul>
                                <li><a href="funcionario.jsp">Cadastrar</a></li>
                                <li><a href="#">Visualizar</a></li>
                            </ul>
                        </li>
                     <li class="dropdown">
                            <a href="#"><span>Produto</span> <i class="bi bi-chevron-down"></i></a>
                            <ul>
                                <li><a href="produto.jsp">Cadastrar</a></li>
                                <li><a href="#">Visualizar</a></li>
                            </ul>
                        </li>
                        <li class="dropdown">
                            <a href="#"><span>Tabelas</span> <i class="bi bi-chevron-down"></i></a>
                            <ul>
                                <li><a href="#">Sexo</a></li>
                                <li class="dropdown">
                                    <a href="#"><span>Pesquisa</span> <i class="bi bi-chevron-right"></i></a>
                                    <ul>
                                        <li><a href="#">Por data</a></li>
                                        <li><a href="#">Por sexo</a></li>
                                        <li><a href="#">Por idade</a></li>
                                        <li><a href="#">Por nome</a></li>
                                    </ul>
                                </li>
                                <li class="dropdown">
                                    <a href="#"><span>Portofolios</span> <i class="bi bi-chevron-right"></i></a>
                                    <ul>
                                        <li><a href="#">Carregar Localiza��o</a></li>
                                        <li><a href="#">Carregar produto</a></li>
                                    </ul>
                                </li>
                                <li><a href="#">Telefone</a></li>
                                <li><a href="#">Estado Civil</a></li>
                                <li><a href="pais.jsp">Pais</a></li>
                                <li><a href="#">Provincia</a></li>
                                <li><a href="#">Municipio</a></li>
                                <li><a href="#">Endere�o</a></li>
                            </ul>
                        </li>
                        <li><a class="nav-link scrollto" href="#contact">Contacto</a></li>
                        <li><a class="nav-link scrollto" href="#about">Sobre</a></li>
                    </ul>
                    <i class="bi bi-list mobile-nav-toggle"></i>
                </nav>
                <!-- .navbar -->

                <a href="#appointment" class="appointment-btn scrollto"><span class="d-none d-md-inline">Marcar</span>
                    Consultas</a>
            </div>
        </header>
        <!-- End Header -->

        <!-- ======= Hero Section ======= -->
        <section id="hero">
            <div id="heroCarousel" data-bs-interval="5000" class="carousel slide carousel-fade" data-bs-ride="carousel">
                <ol class="carousel-indicators" id="hero-carousel-indicators"></ol>

                <div class="carousel-inner" role="listbox">
                    <!-- Slide 1 -->
                    <div class="carousel-item active" style="background-image: url(assets/home/slide/slide01.jpg)">
                        <div class="container">
                            <h2>Bem-vindo a <span>IMOB Sells</span></h2>
                            <p>
                                Somos uma Ag�ncia de Gest�o, Media��o e Angaria��o Imobili�ria, que atua no mercado angolano desde 2011,
                                atendendo rigorosamente as necessidades dos nossos clientes e oferecendo a quem nos procura um servi�o
                                referenciado com seriedade e profissionalismo. Colaboramos com Ag�ncias parceiras, Construtoras,
                                Promotoras do mercado imobili�rio angolano, al�m de pessoas f�sicas e jur�dicas, que nos procuram atrav�s da m�dia.
                                Primamos por um servi�o cred�vel, fornecemos informa��es importantes sobre cuidados e procedimentos para compra, 
                                vender ou arrendamento de um Im�vel, passando confian�a e agilidade em qualquer tipo de transa��o imobili�ria efetuada por n�s.
                            </p>
                            <a href="#about" class="btn-get-started scrollto">Leia Mais</a>
                        </div>
                    </div>

                    <!-- Slide 2 -->
                    <div class="carousel-item" style="background-image: url(assets/home/slide/slide02.jpg)">
                        <div class="container">
                            <h2>Vis�o</h2>
                            <p>
                                Ser uma empresa fi�vel e comoda em qualquer neg�cio oferecido aos nossos clientes. 
                                Ser uma refer�ncia a n�vel do mercado Angolano e prefer�ncia no que tange a neg�cios imobili�rios.
                            </p>
                            <a href="#about" class="btn-get-started scrollto">Leia Mais</a>
                        </div>
                    </div>

                    <!-- Slide 3 -->
                    <div class="carousel-item" style="background-image: url(assets/home/slide/slide03.jpg)">
                        <div class="container">
                            <h2>Miss�o</h2>
                            <p>
                                Oferecer produtos e servi�os de Gest�o Imobili�ria com excel�ncia, fornecendo solu��es de 
                                rela��es duradouras aos nossos clientes, valorizando toda a colabora��o e parceria que elevem
                                o nosso crescimento da MCSMVC
                            </p>
                            <a href="#about" class="btn-get-started scrollto">Leia Mais</a>
                        </div>
                    </div>
                </div>

                <a class="carousel-control-prev" href="#heroCarousel" role="button" data-bs-slide="prev">
                    <span class="carousel-control-prev-icon bi bi-chevron-left" aria-hidden="true"></span>
                </a>

                <a class="carousel-control-next" href="#heroCarousel" role="button" data-bs-slide="next">
                    <span class="carousel-control-next-icon bi bi-chevron-right" aria-hidden="true"></span>
                </a>
            </div>
        </section>
        <!-- End Hero -->

        <main id="main">
            <!-- ======= Featured Services Section ======= -->
            <section id="featured-services" class="featured-services">
                <div class="container" data-aos="fade-up">
                    <div class="row">
                        <div class="col-md-6 col-lg-3 d-flex align-items-stretch mb-5 mb-lg-0">
                            <div class="icon-box" data-aos="fade-up" data-aos-delay="100">
                                <div class="icon"><i class="fas fa-heartbeat"></i></div>
                                <h4 class="title"><a href="">CONSULTA DE ESPECIALIDADE</a></h4>
                                <p class="description">
                                    Temos � disposi��o dos nossos utentes uma ampla oferta de
                                    consultas de especialidade fundamentais para o seu bem-estar e
                                    para a sua sa�de f�sica e mental. Ver especialidades
                                    dispon�veis.
                                </p>
                            </div>
                        </div>

                        <div class="col-md-6 col-lg-3 d-flex align-items-stretch mb-5 mb-lg-0">
                            <div class="icon-box" data-aos="fade-up" data-aos-delay="200">
                                <div class="icon"><i class="fas fa-pills"></i></div>
                                <h4 class="title"><a href="">IMAGIOLOGIA</a></h4>
                                <p class="description">
                                    Acesso a diagn�sticos precisos por meio das mais modernas
                                    t�cnicas de exames por imagem. Realizadas por uma equipe
                                    altamente capacitada.
                                </p>
                            </div>
                        </div>

                        <div class="col-md-6 col-lg-3 d-flex align-items-stretch mb-5 mb-lg-0">
                            <div class="icon-box" data-aos="fade-up" data-aos-delay="300">
                                <div class="icon"><i class="fas fa-thermometer"></i></div>
                                <h4 class="title"><a href="">LINHA SOS VACINAS</a></h4>
                                <p class="description">
                                    Linha de atendimento gratuito, que funciona das 7:30h �s
                                    22h00, criado para responder a quest�es sobre vacinas, efeitos
                                    secund�rios, como se devem administrar, plano de vacina��o do
                                    LMC, quais as vacinas do Plano Nacional de Sa�de (ou outros
                                    pa�ses), quais as vacinas de compra, stocks, hor�rios, etc.
                                    Telefone ou Whatsapp: +244 944 375 313
                                </p>
                            </div>
                        </div>

                        <div class="col-md-6 col-lg-3 d-flex align-items-stretch mb-5 mb-lg-0">
                            <div class="icon-box" data-aos="fade-up" data-aos-delay="400">
                                <div class="icon"><i class="fas fa-dna"></i></div>
                                <h4 class="title"><a href="">LABORAT�RIO DE AN�LISES</a></h4>
                                <p class="description">
                                    Temos nas nossas instala��es um laborat�rio pr�prio, capaz de
                                    disponibilizar patologia cl�nica e anatomia patol�gica.
                                </p>
                            </div>
                        </div>
                    </div>
                </div>
            </section>
            <!-- End Featured Services Section -->

            <!-- ======= Footer ======= -->
            <footer id="footer">
                <div class="footer-top">
                    <div class="container">
                        <div class="row">
                            <div class="col-lg-3 col-md-6">
                                <div class="footer-info">
                                    <h3>IMOB Sells</h3>
                                    <p>
                                        <br />
                                        Faculdade de Engenharia Inform�tica da Universidade Cat�lica de Angola<br /><br />
                                        <strong>Telefone:</strong> (+244) 222 010 916<br />
                                        <strong>Email:</strong> info@ucan.edu<br />
                                    </p>
                                    <div class="social-links mt-3">
                                        <a href="#" class="twitter"><i class="bx bxl-twitter"></i></a>
                                        <a href="#" class="facebook"><i class="bx bxl-facebook"></i></a>
                                        <a href="#" class="instagram"><i class="bx bxl-instagram"></i></a>
                                        <a href="#" class="google-plus"><i class="bx bxl-skype"></i></a>
                                        <a href="#" class="linkedin"><i class="bx bxl-linkedin"></i></a>
                                    </div>
                                </div>
                            </div>

                            <div class="col-lg-2 col-md-6 footer-links">
                                <h4>Links Auxilares</h4>
                                <ul>
                                    <li>
                                        <i class="bx bx-chevron-right"></i> <a href="#">Pagina Inicial</a>
                                    </li>
                                    <li>
                                        <i class="bx bx-chevron-right"></i> <a href="#">Sobre</a>
                                    </li>
                                    <li>
                                        <i class="bx bx-chevron-right"></i> <a href="#">Consultas</a>
                                    </li>
                                    <li>
                                        <i class="bx bx-chevron-right"></i>
                                        <a href="#">Termos de servi�o</a>
                                    </li>
                                    <li>
                                        <i class="bx bx-chevron-right"></i>
                                        <a href="#">Politica de provacidade</a>
                                    </li>
                                </ul>
                            </div>

                            <div class="col-lg-3 col-md-6 footer-links">
                                <h4>Nossos servi�os</h4>
                                <ul>
                                    <li>
                                        <i class="bx bx-chevron-right"></i> <a href="#">CONSULTA DE ESPECIALIDADE</a>
                                    </li>
                                    <li>
                                        <i class="bx bx-chevron-right"></i>
                                        <a href="#">IMAGIOLOGIA</a>
                                    </li>
                                    <li>
                                        <i class="bx bx-chevron-right"></i>
                                        <a href="#">VACINAS</a>
                                    </li>
                                    <li>
                                        <i class="bx bx-chevron-right"></i> <a href="#">LABORAT�RIO DE AN�LISES</a>
                                    </li>
                                    <li>
                                        <i class="bx bx-chevron-right"></i>
                                        <a href="#">TERAPIAS</a>
                                    </li>
                                </ul>
                            </div>

                            <div class="col-lg-4 col-md-6 footer-newsletter">
                                <h4>Feedback</h4>
                                <p>
                                    FALE CONNOSCO DE FORMA INSTANT�NEA. QUE LHE RESPONDEMOS NA HORA.
                                </p>
                                <form action="" method="post">
                                    <input type="email" name="email" /><input type="submit" value="Enviar" />
                                </form>
                            </div>
                        </div>
                    </div>
                </div>

                <div class="container">
                    <div class="copyright">
                        &copy; Copyright <strong><span>UCAN</span></strong>. All Rights Reserved
                    </div>
                    <div class="credits">

                        Designed by Adilson Zumba Manuel
                    </div>
                </div>
            </footer>
            <!-- End Footer -->

            <!--<div id="preloader"></div>-->
            <a href="#" class="back-to-top d-flex align-items-center justify-content-center"><i
                    class="bi bi-arrow-up-short"></i></a>

            <!-- Vendor JS Files -->
            <script src="css/vendor/aos/aos.js"></script>
            <script src="css/vendor/bootstrap/js/bootstrap.bundle.min.js"></script>
            <script src="css/vendor/glightbox/js/glightbox.min.js"></script>
            <script src="css/vendor/php-email-form/validate.js"></script>
            <script src="css/vendor/purecounter/purecounter.js"></script>
            <script src="css/vendor/swiper/swiper-bundle.min.js"></script>

            <!-- Template Main JS File -->
            <script src="js/main.js"></script>
    </body>

</html>