<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-rbsA2VBKQhggwzxH7pPCaAqO46MgnOM80zW1RWuH61DGLwZJEdK2Kadq2F9CUG65" crossorigin="anonymous">
        <link href="css/matenedor.css" rel="stylesheet" type="text/css"/>
    </head>
    <body class="fondo">
        <div class="container-fluid mt-3">
            <div class="row d-flex">
                <div class="col-12 col-sm-6 col-md-4">
                    <div class="card">
                        <div class="card-body">
                            <h4 class="text-center encabezado">CLIENTE</h4>
                            <form action="Controlador?menu=Cliente" method="post">
                                <div class="form-group mb-2">
                                    <label>ID</label>
                                    <input type="text" name="txtId" value="${oCliente.getId_cliente()}" readonly placeholder="Id por defecto (solo lectura)" class="form-control text-center" style="background-color: #C4EBE7"/>
                                </div>
                                <div class="form-group mb-2">
                                    <label>Dni</label>
                                    <input type="text" name="txtDni" value="${oCliente.getDni()}" required placeholder="Ingrese el dni" class="form-control text-center"/>
                                </div>
                                <div class="form-group mb-2">
                                    <label>Nombre</label>
                                    <input type="text" name="txtNombre" value="${oCliente.getNombre()}" required placeholder="Ingrese el nombre" class="form-control text-center"/>
                                </div>
                                <div class="form-group mb-2">
                                    <label>Dirección</label>
                                    <input type="text" name="txtDireccion" value="${oCliente.getDireccion()}" required placeholder="Ingrese la dirección" class="form-control text-center"/>
                                </div>
                                <div class="form-group mb-2">
                                    <label>Estado</label>
                                    <input type="number" name="txtEstado" value="${oCliente.getEstado()}" required placeholder="0: Inactivo | 1: Activo" min="0" max="1" class="form-control text-center"/>
                                </div>
                                <div class="d-flex justify-content-center mt-4">
                                    <input name="opcion" type="submit" value="Agregar" class="btn btn-outline-dark botonAgregar" style="margin-right: 2rem"/>
                                    <input name="opcion" type="submit" value="Actualizar" class="btn btn-outline-dark botonActualizar"/>
                                </div>
                            </form>
                        </div>
                    </div>
                </div>
                <div class="col-12 col-sm-6 col-md-8 mt-3">
                    <div class="table-responsive">
                        <table class="table table-hover text-center mi-tabla" style="border: 1px solid black">
                            <thead>
                                <tr style="font-weight: bold">
                                    <td>ID</td>
                                    <td>DNI</td>
                                    <td>NOMBRE</td>
                                    <td>DIRECCIÓN</td>
                                    <td>ESTADO</td>
                                    <td>ACCIÓN</td>
                                </tr>
                            </thead>
                            <tbody style="vertical-align: middle">
                                <c:forEach var="cliente" items="${lClientes}">
                                    <tr>
                                        <td>${cliente.getId_cliente()}</td>
                                        <td>${cliente.getDni()}</td>
                                        <td>${cliente.getNombre()}</td>
                                        <td>${cliente.getDireccion()}</td>
                                        <td>${cliente.getEstado()}</td>
                                        <td>
                                            <a class="btn btn-primary mt-1" href="Controlador?menu=Cliente&opcion=Editar&id=${cliente.getId_cliente()}">Editar</a>
                                            <a class="btn btn-danger mt-1" href="Controlador?menu=Cliente&opcion=Eliminar&id=${cliente.getId_cliente()}">Eliminar</a>
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
