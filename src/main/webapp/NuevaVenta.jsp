<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-rbsA2VBKQhggwzxH7pPCaAqO46MgnOM80zW1RWuH61DGLwZJEdK2Kadq2F9CUG65" crossorigin="anonymous">
        <link href="css/nueva_venta.css" rel="stylesheet" type="text/css"/>
    </head>
    <body class="fondo">
        <div class="container-fluid mt-3 ">
            <div class="row d-flex ">
                <div class="col-12 col-sm-6 col-md-5 no-imprimir">
                    <div class="card">
                        <div class="card-body card-registrar">
                            <form action="Controlador?menu=NuevaVenta" method="post">
                                <div class="form-group d-flex">
                                    <label>Datos del cliente</label>
                                </div>
                                <div class="form-group d-flex mt-3 ">
                                    <div class="container">
                                        <div class="row">
                                            <div class="col-6 col-md-6 col-lg-4 col-xxl-4 d-flex" style="padding-right: 0px">
                                                <input type="text" value="${oCliente.getDni()}" name="txtDniCliente" placeholder="Ingrese Dni" class="form-control text-center"/>
                                            </div>
                                            <div class="col-6 col-md-6 col-lg-3 col-xxl-2 d-flex"  style="padding-left: 0px">
                                                <button name="opcion" value="buscarCliente" class="btn btn-outline-light w-100 btn-buscar">Buscar</button>
                                            </div>
                                            <div class="col-md-12 col-lg-5 col-xxl-6 d-flex">
                                                <input type="text" value="${oCliente.getNombre()}" placeholder="Nombre del cliente" readonly class="form-control text-center" >
                                            </div>
                                        </div>
                                    </div>
                                </div>
                                <div class="form-group d-flex mt-4">
                                    <label>Datos del producto</label>
                                </div>
                                <div class="form-group d-flex mt-3">
                                    <div class="container">
                                        <div class="row">
                                            <div class="col-6 col-md-6 col-lg-4 col-xxl-4 d-flex" style="padding-right: 0px">
                                                <input type="text" name="txtIdProducto" value="${oProducto.getId_producto()}" placeholder="Ingrese Código" class="form-control text-center"/>
                                            </div>
                                            <div class="col-6 col-md-6 col-lg-3 col-xxl-2 d-flex" style="padding-left: 0px">
                                                <button name="opcion" value="buscarProducto" class="btn btn-outline-light w-100 btn-buscar">Buscar</button>
                                            </div>
                                            <div class="col-md-12 col-lg-5 col-xxl-6 d-flex">
                                                <input type="text" name="txtNomProducto" value="${oProducto.getNombre()}" placeholder="Datos del producto" readonly class="form-control  text-center" >
                                            </div>
                                        </div>
                                    </div>
                                </div>
                                <div class="form-group d-flex mt-3">
                                    <div class="container">
                                        <div class="row">
                                            <div class="col-3 col-md-2 col-lg-2 d-flex" >
                                                <input type="text" readonly placeholder="S/" class="form-control text-center"/>
                                            </div>
                                            <div class="col-3 col-md-4 col-lg-4 d-flex" >
                                                <input type="text" name="txtPrecio" value="${oProducto.getPrecio()}" readonly class="form-control text-center"/>
                                            </div>
                                            <div class="col-6 col-md-6 col-lg-3 d-flex" >
                                                <input type="number" min="1" name="txtCantidad" value="1" placeholder="Cantidad" class="form-control text-center"/>
                                            </div>
                                            <div class="col-12 col-md-12 col-lg-3 d-flex">
                                                <input type="text" name="txtStock" placeholder="Stock" value="${oProducto.getStock()}" readonly class="form-control text-center" >
                                            </div>
                                        </div>
                                    </div>
                                </div>
                                <div class="d-flex justify-content-center" style="margin-top: 2rem">
                                    <button name="opcion" value="agregar" class="btn btn-outline-light btn-agregar">Agregar</button>
                                </div>
                            </form>
                        </div>
                    </div>
                </div>
                <!--Buscar por número de serie y luego mostrar en la tabla el producto asociado con el cliente-->
                <div class="col-12 col-sm-6 col-md-7 ">
                    <div class="card imprimir" style="background-color: #41E27C">
                        <div class="card-body" style="padding-top: 1rem">
                            <div class="container">
                                <div class="row" style="padding-bottom: 1rem">
                                    <!--tipo "encabezado"-->
                                    <div class="col-md-5" style="margin-left: auto">
                                        <label>N°de Serie</label>
                                        <input type="text" value="${serie}" name="txtNumSerie" readonly placeholder="Ingrese el num. de serie"/>
                                    </div>
                                </div>
                            </div>
                            <div class="table-responsive " style="background-color: #A9F5C5!important">
                                <table border="1" class="table table-hover text-center">
                                    <thead class="tb-cabecera">
                                        <tr>
                                            <th>N°</th> <!--Num fila de la tabla, representa los detalles del producto que se comprará-->
                                            <th>Cod. Producto</th>
                                            <th>Descripcción</th>
                                            <th>Precio</th>
                                            <th>Cantidad</th>
                                            <th>SubTotal</th>
                                            <th class="no-imprimir">Acciones</th>
                                        </tr>
                                    </thead>
                                    <tbody style="vertical-align: middle">
                                        
                                        <c:forEach var="venta" items="${lVentas}">
                                            <tr>
                                                <td>${venta.getNum_item()}</td>
                                                <td>${venta.getId_producto()}</td>
                                                <td>${venta.getDescripcion()}</td>
                                                <td>${venta.getPrecio()}</td>
                                                <td>${venta.getCantidad()}</td>
                                                <td>${venta.getSubtotal()}</td>
                                                <td class="no-imprimir">
                                                    <div class="d-flex container-fluid " style="vertical-align: middle">
                                                        <a class="btn btn-danger mt-1 " href="Controlador?menu=NuevaVenta&opcion=EliminarFila&id=${venta.getId_producto()}">Eliminar</a>
                                                    </div>
                                                </td>
                                                
                                            </tr>
                                        </c:forEach>

                                    </tbody>
                                </table>
                            </div>

                        </div>
                        <!--Pie del card -->
                        <div class="card-footer  py-3" style="background-color: #D2D6D4">
                            <div class="container">
                                <div class="row">
                                    <!--Generar o cancelar venta-->
                                    <div class="col-md-8 d-flex justify-content-center ">
                                        <a href="Controlador?menu=NuevaVenta&opcion=GenerarVenta" class="btn btn-generar" style="margin-right: 3rem" onclick="print()" >Generar Venta</a>
                                        <a href="Controlador?menu=NuevaVenta&opcion=CancelarVenta" class="btn btn-cancelar" style="margin-right: 3rem">Cancelar Venta</a>
                                    </div>
                                    <!--Mostrar total-->
                                    <div class="col-md-4" style="margin-left: auto">
                                        <label style="margin-right: 1rem" >Monto Total</label>
                                        <input class="text-center" readonly type="text" value="S/. ${monto}0" name="txtMonto" style="max-width: 6.5rem">
                                    </div>  
                                </div>
                            </div>
                        </div>

                    </div>
                </div>

            </div>

        </div>
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-kenU1KFdBIe4zVF0s0G1M5b4hcpxyD9F7jL+jjXkk+Q2h455rYXK/7HAuoJl+0I4" crossorigin="anonymous"></script>
       
        
    </body>
</html>


