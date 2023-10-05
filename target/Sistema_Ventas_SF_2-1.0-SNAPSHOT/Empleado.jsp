
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>EMPLEADO</title>
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-rbsA2VBKQhggwzxH7pPCaAqO46MgnOM80zW1RWuH61DGLwZJEdK2Kadq2F9CUG65" crossorigin="anonymous">
        <link href="css/matenedor.css" rel="stylesheet" type="text/css"/>
    </head>
    <body>
        <div class="container-fluid mt-3">
            <div class="row">
                <div class="col-12 col-sm-6 col-md-4">
                    <div class="card"><!--FORM PARA INSERTAR EMPLEADO-->
                        <div class="card-body">
                            <h4 class="text-center encabezado">EMPLEADO</h4>
                            <form action="Controlador?menu=Empleado" method="post">
                                <div class="form-group">
                                    <label>ID</label>
                                    <input type="number" name="txtId" value="${empleado.getIdEmpleado()}"  readonly min="0" class="form-control mb-2 text-center" placeholder="ID por defecto (solo lectura)" style="background-color: #C4EBE7"/>
                                </div>
                                <div class="form-group">
                                    <label>DNI</label>
                                    <input type="text" name="txtDni" value="${empleado.getDni()}" required placeholder="Ingrese su DNI" class="form-control mb-2 text-center"/>
                                </div>
                                <div class="form-group">
                                    <label>Nombre</label>
                                    <input type="text" name="txtNombre" value="${empleado.getNombre()}" required placeholder="Ingrese sus nombres" class="form-control mb-2 text-center"/>
                                </div>
                                <div class="form-group">
                                    <label>Teléfono</label>
                                    <input type="text" name="txtTelefono" value="${empleado.getTelefono()}" required placeholder="Ingrese su teléfono" class="form-control mb-2 text-center"/>
                                </div>
                                <div class="form-group">
                                    <label>Estado</label>
                                    <input type="number" name="txtEstado" value="${empleado.getEstado()}" required placeholder="Ingrese su estado" class="form-control mb-2 text-center" min="0" max="1" />
                                </div>
                                <div class="form-group">
                                    <label>Usuario: </label>
                                    <input type="text" name="txtUsuario" value="${empleado.getUsuario()}" required placeholder="Ingrese su nombre de usuario" class="form-control mb-2 text-center"/>
                                </div>
                                <div class="form-group">
                                    <label>Clave </label>
                                    <input type="password" name="txtClave" value="${empleado.getClave()}" required placeholder="Ingrese su clave" class="form-control mb-2 text-center"/>
                                </div>
                                <div class="form-group d-flex justify-content-center mt-3"> <!--botón agregar empleado-->
                                    <input type="submit" name="opcion" value="Agregar" class="btn btn-outline-dark botonAgregar" style="margin-right: 2rem;"/>
                                    <input type="submit" name="opcion" value="Actualizar" class="btn btn-outline-dark botonActualizar"/>
                                </div>

                            </form>
                        </div>
                    </div>
                </div>
                <div class="col-12 col-sm-6 col-md-8 mt-2">
                    <div class=" table-responsive ">
                        <table class="table table-hover text-center mi-tabla" style="border: 1px solid black"><!--TABLA PARA VISUALIZAR LOS EMPLEADOS-->
                            <thead>
                                <tr>
                                    <th>ID</th>
                                    <th>DNI</th>
                                    <th>NOMBRE</th>
                                    <th>TELÉFONO</th>
                                    <th>ESTADO</th>
                                    <th>USUARIO</th>
                                    <th>CLAVE</th>
                                    <th>ACCIÓN</th> <!--para editar o eliminar-->
                                </tr>
                            </thead>
                            <tbody style="vertical-align: middle;">
                                <c:forEach var="empleado" items="${listaEmpleados}">
                                    <tr>
                                        <td >${empleado.getIdEmpleado()}</td>
                                        <td>${empleado.getDni()}</td>
                                        <td>${empleado.getNombre()}</td>
                                        <td>${empleado.getTelefono()}</td>
                                        <td>${empleado.getEstado()}</td>
                                        <td>${empleado.getUsuario()}</td>
                                        <td>${empleado.getClave()}</td>
                                        <td>
                                            <a class="btn btn-primary mt-1" href="Controlador?menu=Empleado&opcion=Editar&id=${empleado.getIdEmpleado()}">Editar</a>
                                            <a class="btn btn-danger mt-1" href="Controlador?menu=Empleado&opcion=Eliminar&id=${empleado.getIdEmpleado()}">Eliminar</a>
                                        </td>
                                    </tr>
                                </c:forEach>
                            </tbody>
                        </table>
                    </div>

                </div>
            </div>
        </div>
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-kenU1KFdBIe4zVF0s0G1M5b4hcpxyD9F7jL+jjXkk+Q2h455rYXK/7HAuoJl+0I4" crossorigin="anonymous"></script>
    </body>
</html>
