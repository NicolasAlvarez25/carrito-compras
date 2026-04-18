-- liquibase formatted sql

-- changeset nico:1
CREATE TABLE IF NOT EXISTS usuarios (
    id BIGSERIAL PRIMARY KEY,
    nombre VARCHAR(255),
    email VARCHAR(255),
    password VARCHAR(255)
);

-- changeset nico:2
CREATE TABLE IF NOT EXISTS productos (
    id BIGSERIAL PRIMARY KEY,
    nombre VARCHAR(255),
    descripcion VARCHAR(255),
    precio DOUBLE PRECISION,
    stock INTEGER
);

-- changeset nico:3
CREATE TABLE IF NOT EXISTS facturas (
    id BIGSERIAL PRIMARY KEY,
    usuario_id BIGINT REFERENCES usuarios(id),
    fecha TIMESTAMP,
    total DOUBLE PRECISION
);

-- changeset nico:4
CREATE TABLE IF NOT EXISTS detalle_factura (
    id BIGSERIAL PRIMARY KEY,
    factura_id BIGINT REFERENCES facturas(id),
    producto_id BIGINT REFERENCES productos(id),
    cantidad INTEGER,
    subtotal DOUBLE PRECISION
);