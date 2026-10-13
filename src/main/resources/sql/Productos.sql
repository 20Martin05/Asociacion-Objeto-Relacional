-- ==========================================
-- TABLA: PRODUCTOS
-- ==========================================
DROP TABLE IF EXISTS productos;

CREATE TABLE productos (
    id SERIAL PRIMARY KEY,
    nombre VARCHAR,
    precio NUMERIC(10,2),
    cantidad INT,
    categoria VARCHAR
);

SELECT * FROM productos;
