DROP DATABASE IF EXISTS SANUS_VICTU;
CREATE DATABASE IF NOT EXISTS SANUS_VICTU;

USE SANUS_VICTU;

-- Eliminar tablas si existen
DROP TABLE IF EXISTS dietas_productos;
DROP TABLE IF EXISTS productos_supermercados;
DROP TABLE IF EXISTS mensajes;
DROP TABLE IF EXISTS usuarios;
DROP TABLE IF EXISTS nutricionistas;
DROP TABLE IF EXISTS administradores;
DROP TABLE IF EXISTS dietas;
DROP TABLE IF EXISTS productos;
DROP TABLE IF EXISTS supermercados;
DROP TABLE IF EXISTS personas;

-- Crear tabla para personas
CREATE TABLE IF NOT EXISTS personas (
    id_persona INT AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR(255) UNIQUE NOT NULL,
    contraseña VARCHAR(255) NOT NULL,
    correo VARCHAR(255) NOT NULL,
    tipo_persona VARCHAR(255) NOT NULL
);

-- Crear tabla para nutricionistas
CREATE TABLE IF NOT EXISTS nutricionistas (
    id_persona INT PRIMARY KEY,
    FOREIGN KEY (id_persona) REFERENCES personas(id_persona)
);

-- Crear tabla para usuarios
CREATE TABLE IF NOT EXISTS usuarios (
    id_persona INT PRIMARY KEY,
    peso FLOAT NOT NULL,
    edad INT NOT NULL,
    sexo VARCHAR(10) NOT NULL,
    nutricionista_id INT NOT NULL,
    FOREIGN KEY (id_persona) REFERENCES personas(id_persona),
    FOREIGN KEY (nutricionista_id) REFERENCES nutricionistas(id_persona) -- Esta tabla debe existir
);

-- Crear tabla para administradores
CREATE TABLE IF NOT EXISTS administradores (
    id_persona INT PRIMARY KEY,
    nick_name VARCHAR(255) NOT NULL,
    FOREIGN KEY (id_persona) REFERENCES personas(id_persona)
);

-- Crear tabla para mensajes
CREATE TABLE IF NOT EXISTS mensajes (
    id_mensaje INT AUTO_INCREMENT PRIMARY KEY,
    texto VARCHAR(255) NOT NULL,
    remitente_id INT NOT NULL,
    destinatario_id INT NOT NULL,
    FOREIGN KEY (remitente_id) REFERENCES personas(id_persona),
    FOREIGN KEY (destinatario_id) REFERENCES personas(id_persona)
);

-- Crear tabla para dietas
CREATE TABLE IF NOT EXISTS dietas (
    id_dieta INT AUTO_INCREMENT PRIMARY KEY,
    favotiro BOOLEAN DEFAULT FALSE,
    usuario_id INT NOT NULL,
    FOREIGN KEY (usuario_id) REFERENCES usuarios(id_persona)
);

-- Crear tabla para productos
CREATE TABLE IF NOT EXISTS productos (
    Id_producto VARCHAR(50) PRIMARY KEY,
    nombre VARCHAR(255) NOT NULL,
    valor_energetico INT NOT NULL,
    grasas FLOAT NOT NULL,
    hidratos_carbono FLOAT NOT NULL,
    fibra_alimentaria FLOAT NOT NULL,
    proteinas FLOAT NOT NULL,
    sal FLOAT NOT NULL,
    fecha VARCHAR(50) NULL
);

-- Crear tabla para supermercados
CREATE TABLE IF NOT EXISTS supermercados (
    Id_supermercado VARCHAR(50) PRIMARY KEY,
    nombre VARCHAR(255) NOT NULL,
    localización VARCHAR(255) NOT NULL
);

-- Crear tabla intermedia para dietas y productos
CREATE TABLE IF NOT EXISTS dietas_productos (
    id INT AUTO_INCREMENT PRIMARY KEY,
    dieta_id INT NOT NULL,
    producto_id VARCHAR(50) NOT NULL,
    FOREIGN KEY (dieta_id) REFERENCES dietas(id_dieta),
    FOREIGN KEY (producto_id) REFERENCES productos(Id_producto)
);

-- Crear tabla intermedia para productos y supermercados
CREATE TABLE IF NOT EXISTS productos_supermercados (
    id INT AUTO_INCREMENT PRIMARY KEY,
    producto_id VARCHAR(50) NOT NULL,
    supermercado_id VARCHAR(50) NOT NULL,
    FOREIGN KEY (producto_id) REFERENCES productos(Id_producto),
    FOREIGN KEY (supermercado_id) REFERENCES supermercados(Id_supermercado)
);