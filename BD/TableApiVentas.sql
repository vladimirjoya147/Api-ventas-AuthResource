
CREATE DATABASE IF NOT EXISTS venta_sistema;
USE venta_sistema;

CREATE DATABASE IF NOT EXISTS venta_sistema2;
USE venta_sistema2;

CREATE TABLE IF NOT EXISTS roles (
  id_rol int NOT NULL AUTO_INCREMENT,
  nombre_rol varchar(50) NOT NULL COMMENT 'Ej: Administrador, Cajero, Almacenista',
  PRIMARY KEY (id_rol),
  UNIQUE KEY nombre_rol (nombre_rol)
) ;

-- Volcando estructura para permisos
CREATE TABLE IF NOT EXISTS permisos (
  id_permiso int NOT NULL AUTO_INCREMENT,
  nombre_permiso varchar(100) NOT NULL COMMENT 'Ej: productos_crear, ventas_ver_reporte',
  descripcion varchar(255) DEFAULT NULL,
  PRIMARY KEY (id_permiso),
  UNIQUE KEY nombre_permiso (nombre_permiso)
);

-- tabla rol_permiso
CREATE TABLE IF NOT EXISTS rol_permiso (
  id_rol int NOT NULL,
  id_permiso int NOT NULL,
  PRIMARY KEY (id_rol,id_permiso),
  FOREIGN KEY (id_rol) REFERENCES roles (id_rol) ON DELETE CASCADE,
  FOREIGN KEY (id_permiso) REFERENCES permisos (id_permiso) ON DELETE CASCADE
);

-- tabla clientes
CREATE TABLE IF NOT EXISTS clientes (
  id_cliente int NOT NULL AUTO_INCREMENT,
  nombre_cliente varchar(150) NOT NULL,
  documento_identidad varchar(20) DEFAULT NULL,
  telefono varchar(20) DEFAULT NULL,
  email varchar(100) DEFAULT NULL,
  direccion text DEFAULT NULL,
  activo tinyint NOT NULL DEFAULT 1,
  PRIMARY KEY (id_cliente),
  UNIQUE KEY documento_identidad (documento_identidad)
);

--  tabla categorias----
CREATE TABLE IF NOT EXISTS categorias (
  id_categoria int NOT NULL AUTO_INCREMENT,
  nombre varchar(100) NOT NULL,
  activo tinyint NOT NULL DEFAULT 1 COMMENT '1 = Activo, 0 = Inactivo',
  PRIMARY KEY (id_categoria),
  UNIQUE KEY nombre (nombre)
);

CREATE TABLE IF NOT EXISTS proveedores (
  id_proveedor int NOT NULL AUTO_INCREMENT,
  nombre_proveedor varchar(150) NOT NULL,
  ruc varchar(20) DEFAULT NULL,
  telefono varchar(20) DEFAULT NULL,
  email varchar(100) DEFAULT NULL,
  direccion text DEFAULT NULL,
  activo tinyint NOT NULL DEFAULT 1 COMMENT '1 = Activo, 0 = Inactivo',
  PRIMARY KEY (id_proveedor),
  UNIQUE KEY ruc (ruc)
);

-- tabla productos
CREATE TABLE IF NOT EXISTS productos (
  id_producto int NOT NULL AUTO_INCREMENT,
  codigo_barra varchar(100) DEFAULT NULL,
  nombre varchar(255) NOT NULL,
  descripcion text DEFAULT NULL,
  id_categoria int DEFAULT NULL,
  id_proveedor_preferido int DEFAULT NULL,
  precio_compra decimal(10,2) NOT NULL DEFAULT 0.00,
  precio_venta decimal(10,2) NOT NULL,
  stock int NOT NULL DEFAULT 0,
  stock_minimo int NOT NULL DEFAULT 5,
  activo tinyint NOT NULL DEFAULT 1 COMMENT '1 = Activo, 0 = Inactivo',
  fecha_creacion timestamp NOT NULL DEFAULT current_timestamp(),
  fecha_actualizacion timestamp NOT NULL DEFAULT current_timestamp() ON UPDATE current_timestamp(),
  img_url VARCHAR(255),
  PRIMARY KEY (id_producto),
  UNIQUE KEY codigo_barra (codigo_barra),
  FOREIGN KEY (id_categoria) REFERENCES categorias (id_categoria) ON DELETE SET NULL,
  FOREIGN KEY (id_proveedor_preferido) REFERENCES proveedores (id_proveedor) ON DELETE SET NULL
);

CREATE TABLE IF NOT EXISTS movimientos_inventario (
  id_movimiento int NOT NULL AUTO_INCREMENT,
  id_producto int NOT NULL,
  tipo_movimiento enum('COMPRA','VENTA','DEVOLUCON','AJUSTE_POSITIVO','AJUSTE_NEGATIVO') NOT NULL,
  cantidad int NOT NULL,
  stock_anterior int NOT NULL,
  stock_nuevo int NOT NULL,
  fecha timestamp NOT NULL DEFAULT current_timestamp(),
  id_usuario int NOT NULL,
  referencia_id int DEFAULT NULL COMMENT 'ID de la venta, compra o ajuste relacionado',
  PRIMARY KEY (id_movimiento),
  FOREIGN KEY (id_producto) REFERENCES productos (id_producto)
);

CREATE TABLE IF NOT EXISTS ventas (
  id_venta int NOT NULL AUTO_INCREMENT,
  id_cliente int DEFAULT NULL COMMENT 'Puede ser nulo para ventas genéricas',
  id_usuario int NOT NULL,
  fecha_venta timestamp NOT NULL DEFAULT current_timestamp(),
  total_venta decimal(10,2) NOT NULL,
  metodo_pago enum('EFECTIVO','TARJETA_CREDITO','TARJETA_DEBITO','TRANSFERENCIA') NOT NULL,
  estado enum('COMPLETADA','ANULADA') NOT NULL DEFAULT 'COMPLETADA',
  PRIMARY KEY (id_venta),
  FOREIGN KEY (id_cliente) REFERENCES clientes (id_cliente)
);

-- tabla detalle_ventas
CREATE TABLE IF NOT EXISTS detalle_ventas (
  id_detalle_venta int NOT NULL AUTO_INCREMENT,
  id_venta int NOT NULL,
  id_producto int NOT NULL,
  cantidad int NOT NULL,
  precio_unitario decimal(10,2) NOT NULL,
  descuento decimal(10,2) NOT NULL DEFAULT 0.00,
  PRIMARY KEY (id_detalle_venta),
  FOREIGN KEY (id_venta) REFERENCES ventas (id_venta) ON DELETE CASCADE,
  FOREIGN KEY (id_producto) REFERENCES productos (id_producto)
);

INSERT INTO roles (id_rol, nombre_rol) VALUES
	(1, 'Administrador'),
	(2, 'Cajero'),
	(3, 'Almacenista');

-- datos para la tabla permisos:------------------
INSERT INTO permisos (id_permiso, nombre_permiso, descripcion) VALUES
	(1, 'dashboard_ver', 'Acceso al dashboard principal'),
	(2, 'productos_ver_lista', 'Ver la lista de productos en el inventario'),
	(3, 'productos_crear', 'Crear nuevos productos'),
	(4, 'productos_editar', 'Editar productos existentes'),
	(5, 'productos_eliminar', 'Eliminar productos'),
	(6, 'ventas_crear', 'Acceder al POS y registrar nuevas ventas'),
	(7, 'proveedores_ver_lista', 'Ver la lista de proveedores'),
	(8, 'proveedores_crear_editar', 'Crear y editar proveedores'),
	(10, 'compras_ver_lista', 'Ver la lista de órdenes de compra'),
	(11, 'compras_crear', 'Crear nuevas órdenes de compra'),
	(12, 'compras_recibir', 'Marcar órdenes de compra como recibidas y actualizar stock'),
	(13, 'caja_gestionar', 'Abrir, cerrar y gestionar movimientos de caja'),
	(14, 'reportes_ver_ventas', 'Ver el reporte de ventas'),
	(15, 'reportes_ver_inventario', 'Ver el reporte de inventario'),
	(16, 'admin_gestionar_roles', 'Permite editar los permisos de otros roles'),
	(17, 'categorias_ver_lista', 'Ver la lista de categorías'),
	(18, 'categorias_crear_editar', 'Crear y editar categorías'),
	(19, 'categorias_cambiar_estado', 'Activar o desactivar categorías'),
	(20, 'productos_cambiar_estado', 'Activar o desactivar productos'),
	(21, 'proveedores_cambiar_estado', 'Activar o desactivar proveedores'),
	(22, 'productos_ver_detalle', 'Ver el historial de movimientos (Kardex) de un producto'),
	(23, 'ventas_ver_listado', 'Ver el historial de ventas realizadas'),
	(24, 'clientes_ver_lista', 'Ver la lista de clientes'),
	(25, 'clientes_crear_editar', 'Crear y editar clientes'),
	(26, 'clientes_cambiar_estado', 'Activar o desactivar clientes'),
	(27, 'usuarios_ver_lista', 'Ver la lista de usuarios del sistema'),
	(28, 'usuarios_crear_editar', 'Crear y editar usuarios del sistema'),
	(29, 'usuarios_cambiar_estado', 'Activar o desactivar usuarios'),
	(30, 'reportes_ver_stock_bajo', 'Ver el reporte de productos con bajo stock');

-- datos para la tabla rol_permiso: ~
INSERT INTO rol_permiso (id_rol, id_permiso) VALUES
	(1, 1),(1, 2),(1, 3),(1, 4),(1, 5),(1, 6),(1, 7),(1, 8),(1, 10),(1, 11),(1, 12),(1, 13),(1, 14),(1, 15),(1, 16),(1, 17),(1, 18),(1, 19),(1, 20),
	(1, 21),(1, 22),(1, 23),(1, 24),(1, 25),(1, 26),(1, 27),(1, 28),(1, 29),(1, 30),(2, 1),(2, 2),(2, 6),(2, 13);
    
-- datos para la tabla clientes--------------
INSERT INTO clientes (id_cliente, nombre_cliente, documento_identidad, telefono, email, direccion,activo) VALUES
	(1, 'CLIENTE 1', '10101010', '12121212', 'clientex@correo.com', 'direccion x', 1),
    (2, 'CLIENTE 2', '20202020', '23232323', 'cliente2@correo.com', 'Av. Siempre Viva 123', 1),
	(3, 'CLIENTE 3', '30303030', '34343434', 'cliente3@correo.com', 'Jr. Los Pinos 456', 1),
	(4, 'CLIENTE 4', '40404040', '45454545', 'cliente4@correo.com', 'Calle Las Flores 789', 0),
	(5, 'CLIENTE 5', '50505050', '56565656', 'cliente5@correo.com', 'Av. Los Olivos 321', 1),
	(6, 'CLIENTE 6', '60606060', '67676767', 'cliente6@correo.com', 'Jr. San Martín 654', 1);

-- datos para la tabla categorias------------
INSERT INTO categorias (id_categoria, nombre, activo) VALUES
	(1, 'PISCO', 1),
	(2, 'WHISKY', 1),
	(3, 'VODKA', 1);

-- datos proveedores:-----------------
INSERT INTO proveedores (id_proveedor, nombre_proveedor, ruc, telefono, email, direccion, activo) VALUES
	(1, 'PROVEEDOR 1', '212121211', '12121212', 'proveedor1@correo.com', 'dirección1', 1),
	(2, 'PROVEEDOR 2', '232323231', '12121212', 'proveedor2@correo.com', 'direccion2', 1);

INSERT INTO productos (id_producto, codigo_barra, nombre, descripcion, id_categoria, id_proveedor_preferido, precio_compra, precio_venta, stock, stock_minimo, activo, fecha_creacion, fecha_actualizacion, img_url) VALUES
(1, NULL, 'Pisco PORTON Mosto Verde Italia 700ml', '', 1, 1, 60.00, 75.00, 10, 2, 1, NOW(), NOW(),'https://i.ibb.co/tTwFMBLL/001.jpg'),
(2, NULL, 'Kit madera pisco huamani (01 quebranta 700ml + 01 m.v. acholado 700ml)', '', 1, 1, 143.90, 179.90, 10, 2, 1, NOW(), NOW(),'https://i.ibb.co/fdVd07GK/002.jpg'),
(3, NULL, 'Piscano sour de piña colada - 700ml', '', 1, 1, 15.00, 18.90, 10, 2, 1, NOW(), NOW(),'https://i.ibb.co/fGk3Bw52/003.jpg'),
(4, NULL, 'Pisco 1615 mosto verde italia 700ml', '', 1, 1, 65.00, 81.00, 10, 2, 1, NOW(), NOW(),'https://i.ibb.co/fzzrHY3H/004.jpg'),
(5, NULL, 'Pisco 4 fundos acholado 500ml', '', 1, 1, 48.00, 59.90, 10, 2, 1, NOW(), NOW(),'https://i.ibb.co/DHfb5s7w/005.jpg'),
(6, NULL, 'Pisco 4 fundos quebranta 500ml', '', 1, 1, 48.00, 59.90, 10, 2, 1, NOW(), NOW(),'https://i.ibb.co/7tBQjhX4/006.jpg'),
(7, NULL, 'Pisco altos de azpitia m.v acholado/mollar/italia 500ml', '', 1, 1, 65.00, 81.90, 10, 2, 1, NOW(), NOW(),'https://i.ibb.co/N2MgvxC5/007.jpg'),
(8, NULL, 'Pisco altos de azpitia m.v mollar 500ml', '', 1, 1, 65.00, 81.90, 10, 2, 1, NOW(), NOW(),'https://i.ibb.co/vCT69bP5/008.jpg'),
(9, NULL, 'Pisco barsol mosto verde quebranta 750ml', '', 1, 1, 82.00, 102.50, 10, 2, 1, NOW(), NOW(),'https://i.ibb.co/tTY01c95/009.jpg'),
(10, NULL, 'Pisco biondi tradicional acholado 500ml', '', 1, 1, 56.00, 70.00, 10, 2, 1, NOW(), NOW(),'https://i.ibb.co/BKQd4NFG/010.jpg'),
(11, NULL, 'Whisky Chivas Regal 12 años 700ml + Vodka Wyborowa 700ml', '', 2, 1, 92.00, 114.90, 10, 2, 1, NOW(), NOW(),'https://i.ibb.co/Ps0ry9FJ/011.jpg'),
(12, NULL, 'Whisky Chivas Regal 12 años 700ml + Whisky Chivas Regal 200ml', '', 2, 1, 77.00, 96.90, 10, 2, 1, NOW(), NOW(),'https://i.ibb.co/FLKx00Jb/012.jpg'),
(13, NULL, 'Whisky Chivas Regal 12 años 700ml x 02 botellas', '', 2, 1, 132.00, 165.00, 10, 2, 1, NOW(), NOW(),'https://i.ibb.co/GQGzgY8v/014.jpg'),
(14, NULL, 'Whisky Glenlivet Founders Reserve 700ml + Posavaso', '', 2, 1, 104.00, 129.90, 10, 2, 1, NOW(), NOW(),'https://i.ibb.co/G4JKN7n3/013.jpg'),
(15, NULL, 'Whisky Johnnie Walker Black Label 750ml + Termo edición limitada', '', 2, 1, 84.00, 104.90, 10, 2, 1, NOW(), NOW(),'https://i.ibb.co/wh4stck0/015.jpg'),
(16, NULL, 'Whisky Johnnie Walker Gold Label Reserve 750ml + Copa', '', 2, 1, 172.00, 214.90, 10, 2, 1, NOW(), NOW(),'https://i.ibb.co/C3XHR2HN/016.jpg'),
(17, NULL, 'Whisky Johnnie Walker Red Label 750ml + 02 vasos de acero', '', 2, 1, 44.00, 54.90, 10, 2, 1, NOW(), NOW(),'https://i.ibb.co/Zz8x30mJ/017.jpg'),
(18, NULL, 'Whisky Something 750ml x 02 botellas', '', 2, 1, 83.00, 103.80, 10, 2, 1, NOW(), NOW(),'https://i.ibb.co/LDJNXtk7/018.jpg'),
(19, NULL, 'Whisky Something Special 750ml + Vasos', '', 2, 1, 41.00, 51.90, 10, 2, 1, NOW(), NOW(),'https://i.ibb.co/1YfSZgsf/019.jpg'),
(20, NULL, 'Whisky Ballantines 12 años 700ml', '', 2, 1, 64.00, 79.90, 10, 2, 1, NOW(), NOW(),'https://i.ibb.co/V0yv60V5/020.jpg'),
(21, NULL, 'Vodka Smirnoff Tamarindo + Shot', '', 3, 1, 24.00, 29.90, 10, 2, 1, NOW(), NOW(),'https://i.ibb.co/tpR64HMh/022.jpg'),
(22, NULL, 'Vodka 14 Inkas 750ml', '', 3, 1, 72.00, 89.90, 10, 2, 1, NOW(), NOW(),'https://i.ibb.co/vxfw75V3/021.jpg'),
(23, NULL, 'Vodka Absolut Azul 1lt', '', 3, 1, 50.00, 61.90, 10, 2, 1, NOW(), NOW(),'https://i.ibb.co/xqhpKzhr/023.jpg'),
(24, NULL, 'Vodka Absolut Elyx 700ml', '', 3, 1, 156.00, 195.00, 10, 2, 1, NOW(), NOW(),'https://i.ibb.co/gZvXpbcG/024.jpg'),
(25, NULL, 'Vodka Absolut Lime 750ml', '', 3, 1, 45.00, 56.90, 10, 2, 1, NOW(), NOW(),'https://i.ibb.co/QFX68xCJ/025.jpg'),
(26, NULL, 'Vodka Absolut Mandrin 700ml', '', 3, 1, 45.00, 56.90, 10, 2, 1, NOW(), NOW(),'https://i.ibb.co/CKcwnDwv/026.jpg'),
(27, NULL, 'Vodka Absolut Pears 1lt', '', 3, 1, 50.00, 62.00, 10, 2, 1, NOW(), NOW(),'https://i.ibb.co/2Yv3kkLP/027.jpg'),
(28, NULL, 'Vodka Absolut Pears 700ml', '', 3, 1, 45.00, 56.90, 10, 2, 1, NOW(), NOW(),'https://i.ibb.co/VYnyyVzq/028.jpg'),
(29, NULL, 'Vodka Absolut Raspberri 700ml', '', 3, 1, 48.00, 60.00, 10, 2, 1, NOW(), NOW(),'https://i.ibb.co/7dZ6cXMW/029.jpg'),
(30, NULL, 'Vodka Absolut Warhol 700ml', '', 3, 1, 41.00, 51.90, 10, 2, 1, NOW(), NOW(),'https://i.ibb.co/chTyTvN9/030.jpg');

-- Ventas
INSERT INTO ventas (id_venta, id_cliente, id_usuario, fecha_venta, total_venta, metodo_pago, estado) VALUES
(7, 1, 1, '2025-10-02 10:01:00', 272.40, 'EFECTIVO', 'COMPLETADA'),
(8, 2, 1, '2025-10-02 10:05:00', 692.60, 'TARJETA_DEBITO', 'COMPLETADA'),
(9, 3, 1, '2025-10-02 10:10:00', 404.60, 'TRANSFERENCIA', 'COMPLETADA');

-- Detalle de ventas (5 productos por cada venta)
INSERT INTO detalle_ventas (id_detalle_venta, id_venta, id_producto, cantidad, precio_unitario, descuento) VALUES
-- Venta 7
(6, 7, 1, 1, 75.00, 0.00),
(7, 7, 3, 2, 18.90, 0.00),
(8, 7, 5, 1, 59.90, 0.00),
(9, 7, 9, 1, 102.50, 0.00),
(10, 7, 10, 1, 70.00, 0.00),

-- Venta 8
(11, 8, 11, 1, 114.90, 0.00),
(12, 8, 12, 2, 96.90, 0.00),
(13, 8, 13, 1, 165.00, 0.00),
(14, 8, 15, 1, 104.90, 0.00),
(15, 8, 16, 1, 214.90, 0.00),

-- Venta 9
(16, 9, 21, 2, 29.90, 0.00),
(17, 9, 22, 1, 89.90, 0.00),
(18, 9, 23, 1, 61.90, 0.00),
(19, 9, 24, 1, 195.00, 0.00),
(20, 9, 25, 1, 56.90, 0.00);

-- Movimientos de inventario (15 movimientos = 3 ventas x 5 productos)
INSERT INTO movimientos_inventario (id_movimiento, id_producto, tipo_movimiento, cantidad, stock_anterior, stock_nuevo, fecha, id_usuario, referencia_id) VALUES
-- Venta 7 (cliente 1)
(21, 1, 'VENTA', 1, 10, 9, '2025-10-02 10:01:00', 1, 7),
(22, 3, 'VENTA', 2, 10, 8, '2025-10-02 10:01:00', 1, 7),
(23, 5, 'VENTA', 1, 10, 9, '2025-10-02 10:01:00', 1, 7),
(24, 9, 'VENTA', 1, 10, 9, '2025-10-02 10:01:00', 1, 7),
(25, 10, 'VENTA', 1, 10, 9, '2025-10-02 10:01:00', 1, 7),

-- Venta 8 (cliente 2)
(26, 11, 'VENTA', 1, 10, 9, '2025-10-02 10:05:00', 1, 8),
(27, 12, 'VENTA', 2, 10, 8, '2025-10-02 10:05:00', 1, 8),
(28, 13, 'VENTA', 1, 10, 9, '2025-10-02 10:05:00', 1, 8),
(29, 15, 'VENTA', 1, 10, 9, '2025-10-02 10:05:00', 1, 8),
(30, 16, 'VENTA', 1, 10, 9, '2025-10-02 10:05:00', 1, 8),

-- Venta 9 (cliente 3)
(31, 21, 'VENTA', 2, 10, 8, '2025-10-02 10:10:00', 1, 9),
(32, 22, 'VENTA', 1, 10, 9, '2025-10-02 10:10:00', 1, 9),
(33, 23, 'VENTA', 1, 10, 9, '2025-10-02 10:10:00', 1, 9),
(34, 24, 'VENTA', 1, 10, 9, '2025-10-02 10:10:00', 1, 9),
(35, 25, 'VENTA', 1, 10, 9, '2025-10-02 10:10:00', 1, 9);





