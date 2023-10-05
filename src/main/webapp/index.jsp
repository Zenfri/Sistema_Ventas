
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>LOGIN</title>
        <!--link css-->
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">

    </head>

    <body>
        <div class="container mt-4 col-lg-4"> <!--col-lg-4: lg: large, cuando sea >= que los 992px se ocupará 4 de los 12 posibles espacios-->
            <div class="card">
                <div class="card-body">
                    <form action="Validar" method="post"> <!--action: enviaremos el formulario al servlet Validar || post: enviar datos sensibles-->
                        <div class="form-group text-center"> <!-- mejora la visualización del formulario (label e inputs)-->
                            <h3>Inicio de sesión</h3>
                            <img src="img/user.webp" alt="login" width="170"/><br/>
                            <label>Bienvenido!!!</label>
                        </div>
                        <div class="form-group mt-4"> 
                            <label>Usuario:</label>
                            <input type="text" name="txtUser" class="form-control"/>
                            <!-- form-control es una clase de bootstrap que ayuda a que el campo de entra se vea más uniforme y ayuda a la gestion de eventos y validación -->
                        </div>
                        <div class="form-group mt-4"> 
                            <label>Contraseña:</label>
                            <input type="password" name="txtPassword" class="form-control"/>
                        </div>
                        <div class="form-group mt-4 text-center"> 
                            <input type="submit" name="btnEnviar" value="Ingresar" class="btn btn-primary"/>
                        </div>
                    </form>
                </div>
            </div>
        </div>
        <!--link js-->
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-ka7Sk0Gln4gmtz2MlQnikT1wXgYsOg+OMhuP+IlRH9sENBO0LRn5q+8nbTov4+1p" crossorigin="anonymous"></script>
    </body>
</html>
