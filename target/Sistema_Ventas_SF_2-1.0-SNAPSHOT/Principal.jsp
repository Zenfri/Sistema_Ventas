<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>SISTEMA VENTAS</title>
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-T3c6CoIi6uLrA9TneNEoa7RxnatzjcDSCmG1MXxSR1GAsXEV/Dwwykc2MPK8M2HN" crossorigin="anonymous">
        <link href="css/principal.css" rel="stylesheet" type="text/css"/>
    </head>
    <body>
        <nav class="navbar navbar-expand-lg navbar-dark bg-danger">
            <div class="container-fluid">
                <a class="navbar-brand" href="#">Menu</a>
                <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarNav" aria-controls="navbarNav" aria-expanded="false" aria-label="Toggle navigation">
                    <span class="navbar-toggler-icon"></span>
                </button>
                <div class="collapse navbar-collapse " id="navbarNav">
                    <ul class="navbar-nav">
                        <li class="nav-item">
                            <a class="barra btn btn-outline-light" aria-current="page" href="Controlador?menu=Principal">Home</a>
                        </li>
                        <li class="nav-item">
                            <a class="barra btn btn-outline-light" href="Controlador?menu=Producto&opcion=Listar" target="miFrame">Productos</a>
                        </li>
                        <li class="nav-item">
                            <a class="barra btn btn-outline-light" href="Controlador?menu=Empleado&opcion=Listar" target="miFrame">Empleado</a>
                        </li>
                        <li class="nav-item">
                            <a class="barra btn btn-outline-light" href="Controlador?menu=Cliente&opcion=Listar" target="miFrame">Cliente</a>
                        </li>
                        <li class="nav-item">
                            <a class="barra btn btn-outline-light" href="Controlador?menu=NuevaVenta&opcion=Listar" target="miFrame">Nueva venta</a>
                        </li>
                    </ul>

                </div>
                <div class="dropdown" style="margin-right: 2rem">
                    <button class="btn btn-outline-dark bg-warning dropdown-toggle" type="button" id="dropdownMenuButton1" data-bs-toggle="dropdown" aria-expanded="false">
                        ${empleado.getNombre()}
                    </button>
                    <ul class="dropdown-menu text-center" aria-labelledby="dropdownMenuButton1">
                        <li>
                            <a class="dropdown-item" href="#">
                                <img class="img-fluid" src="img/user2.png" alt="login"/>
                            </a>
                        </li>
                        <li><a class="dropdown-item" href="#">ID: ${empleado.getIdEmpleado()}</a></li>
                        <li><a class="dropdown-item" href="#">Usuario: ${empleado.getUsuario()}</a></li>
                        <div class="dropdown-divider"> </div> <!--línea-->
                        <li>
                            <form action="Validar" method="post"><!--actuando con el controlador-->
                                <button name="btnEnviar" class="btn btn-primary w-100" value="salir">Salir</button>
                            </form>
                        </li>
                    </ul>
                </div>
            </div>
        </nav>

        <div class="mi_frame m-2 ">
            <iframe name="miFrame" height="100%" width="100%" title="Contenido Frame" class="fondo" ></iframe>
        </div>
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js" integrity="sha384-C6RzsynM9kWDrMNeT87bh95OGNyZPhcTNXj1NW7RuBCsyN/o0jlpcV8Qyq46cDfL" crossorigin="anonymous"></script>
    </body>
</html>
