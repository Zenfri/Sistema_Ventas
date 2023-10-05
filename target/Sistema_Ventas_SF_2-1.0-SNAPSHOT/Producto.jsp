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
    <body>
        <div class="container-fluid mt-3 fondo">
            <div class="row d-flex">
                <div class="col-12 col-sm-6 col-md-4">
                    <div class="card">
                        <div class="card-body">
                            <h4 class="text-center encabezado">PRODUCTO</h4>
                            <form action="Controlador?menu=Producto" method="post">
                                <div class="form-group mb-2">
                                    <label>ID</label>
                                    <input type="text" name="txtId" value="${oProducto.getId_producto()}" readonly placeholder="Id por defecto (solo lectura)" class="form-control text-center" style="background-color: #C4EBE7"/>
                                </div>
                                <div class="form-group mb-2">
                                    <label>Nombre</label>
                                    <input type="text" name="txtNombre" value="${oProducto.getNombre()}" required placeholder="Ingrese el nombre" class="form-control text-center"/>
                                </div>
                                <div class="form-group mb-2">
                                    <label>Precio</label>
                                    <input type="number" name="txtPrecio" value="${oProducto.getPrecio()}" step="0.01" required placeholder="Ingrese el precio" class="form-control text-center"/>
                                </div>
                                <div class="form-group mb-2">
                                    <label>Stock</label>
                                    <input type="number" name="txtStock" value="${oProducto.getStock()}" required placeholder="Ingrese el stock" min="0" class="form-control text-center"/>
                                </div>
                                <div class="form-group mb-2">
                                    <label>Estado</label>
                                    <input type="number" name="txtEstado" value="${oProducto.getEstado()}" required placeholder="0: Inactivo | 1: Activo" min="0" max="1" class="form-control text-center"/>
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
                                    <td>NOMBRE</td>
                                    <td>PRECIO</td>
                                    <td>STOCK</td>
                                    <td>ESTADO</td>
                                    <td>ACCIÓN</td>
                                </tr>
                            </thead>
                            <tbody style="vertical-align: middle">
                                <c:forEach var="producto" items="${lProductos}">
                                    <tr>
                                        <td>${producto.getId_producto()}</td>
                                        <td>${producto.getNombre()}</td>
                                        <td>${producto.getPrecio()}</td>
                                        <td>${producto.getStock()}</td>
                                        <td>${producto.getEstado()}</td>
                                        <td>
                                            <a class="btn btn-primary mt-1" href="Controlador?menu=Producto&opcion=Editar&id=${producto.getId_producto()}">Editar</a>
                                            <a class="btn btn-danger mt-1" href="Controlador?menu=Producto&opcion=Eliminar&id=${producto.getId_producto()}">Eliminar</a>
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
