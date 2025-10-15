# ENDPOINTS

-Base : http://localhost:8080

## LOGIN

Loguearse con credenciales:


    POST : /api/auth/login


Registrar usuario:


    POST : /api/auth/register


Refrescar token:


    POST: /api/auth/refresh


## Productos
<details>
  <summary> Haz clic aquí para ver más</summary>


Listar productos paginados:


    GET : /api/producto?pagina=0&tamano=3&ordenar=nombre


listar productos por nombre o barras:


    GET : /api/producto/lista?pagina=0&tamano=3&busqueda=coca

desactivar productos:


    PATCH : /api/producto/2/estado?activo=true


guardar productos:


    POST : /api/producto/guardar

    {
    "codigoBarra":null,
    "nombre":"producto5",
    "descripcion":"producto de prueba",
    "idCategoria":1,
    "idProveedorPreferido":1,
    "precioCompra":10.5,
    "precioVenta":5.5,
    "stock":25,
    "stockMinimo":5,
    "activo":null,
    "path":null
    }


Actualizar productos:


    PATCH : /api/producto/actualizar

    {
    "idProducto":1,
    "codigoBarra":null,
    "nombre":"Aguacate",
    "descripcion":"palta fuerte",
    "precioCompra":2.8,
    "precioVenta":3.50,
    "stock": 10,
    "stockMinimo":5,
    "activo":true
    }


</details> 

 


## Categorias

<details>
  <summary> Haz clic aquí para ver más</summary>
Listar Categorias:


    GET : /api/categoria


Buscar cat por ID:


    GET : /api/categoria/2


guardar categoria:


    POST: /api/categoria/guardar

    {
    "nombre":"GASEOSA"
    }

Actualizar categorias:


    PATCH : /api/categoria/1

</details>

## Proveedor

<details>
  <summary> Haz clic aquí para ver más</summary>
Listar Proveedor:


    GET : /api/proveedor


Activar/desactivar por ID:


    PUT : /api/proveedor/1/estado?activo=true


guardar Proveedor:


    POST: /api/proveedor/guardar

    {
    "nombreProveedor":"nuevoProveedor",
    "ruc":"4152415210",
    "telefono":"805805808",
    "email":"nuevo@gmail.com",
    "direccion":"nuevos lirios ate"
    }
</details>


## Clientes

<details>
  <summary> Haz clic aquí para ver más</summary>
Listar Clientes por nombre:


    GET : /api/cliente?pagina=0&tamanio=3&nombre=nombreCliente


listar todos los clientes:


    GET : /api/cliente/listar


Desactivar Clientes:


    PATCH: /api/cliente/1?activo=true


guardar clientes:


    POST: /api/cliente/guardar

    {
    "nombreCliente": "CLIENTE 8",
    "documentoIdentidad": "80808080",
    "telefono": "17171717",
    "email": "cliente8@correo.com",
    "direccion": "direccion 8"
    }

</details>

## Rol

<details>
  <summary> Haz clic aquí para ver más</summary>
Listar roles:


    GET :/api/rol

Actualizar roles:


    PATCH: /api/rol/5

    {
    "idRol": 5,
    "nombreRol": "Nuevo modificado"
    }


Eliminar roles:


    DELETE: /api/rol/5


guardar roles:


    POST: /api/rol/guardar

    {
    "nombreRol": "Nuevo Rol"
    }


</details>

## PERMISO

<details>
  <summary> Haz clic aquí para ver más</summary>
Listar permisos:


    GET :/api/permiso


Eliminar roles:


    DELETE: /api/rol/5


guardar permiso:


    POST: /api/permiso/guardar

    {
    "nombrePermiso":"nuevo permiso",
    "descripcion":"nuevo permiso de prueba"
    }

Actualizar permiso:


    PATCH: /api/permiso/actualizar

    {
    "idPermiso":31,
    "nombrePermiso":"nuevo permiso",
    "descripcion":"nueva edicion"
    }

</details>

## VENTA

<details>
  <summary> Haz clic aquí para ver más</summary>
Descargar Boleta:


    GET :/api/venta/10/pdf


boleta:


    GET: /api/venta/10


guardar venta:

    POST: /api/venta

   {
  "idCliente": 8,
  "idUsuario": 2,
  "totalVenta": 155.50,
  "metodoPago": "EFECTIVO",
  "detalles": [
    {
      "idProducto": 1,
      "cantidad": 2,
      "precioUnitario": 25.00,
      "descuento": 0.00
    },
    {
      "idProducto": 2,
      "cantidad": 1,
      "precioUnitario": 50.50,
      "descuento": 5.00
    },
    {
      "idProducto": 3,
      "cantidad": 3,
      "precioUnitario": 20.00,
      "descuento": 0.00
    }
  ]
   }

</details>

