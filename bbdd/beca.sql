CREATE DATABASE profileCardDB;

USE profileCardDB;

CREATE TABLE usuarios (
    id INT AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR(100) NOT NULL,
    apellidos VARCHAR(100) NOT NULL,
    curso VARCHAR(50),
    anoIngreso INT,
    notaMedia DECIMAL(4, 2)
);

INSERT INTO usuarios (nombre, apellidos, curso, anoIngreso, notaMedia)
VALUES ('Juan', 'Pérez', 'Tercero', 2019, 8.5);

INSERT INTO usuarios (nombre, apellidos, curso, anoIngreso, notaMedia)
VALUES ('María', 'González', 'Segundo', 2020, 7.9);

INSERT INTO usuarios (nombre, apellidos, curso, anoIngreso, notaMedia)
VALUES ('Carlos', 'Martínez', 'Cuarto', 2018, 9.2);

INSERT INTO usuarios (nombre, apellidos, curso, anoIngreso, notaMedia)
VALUES ('Ana', 'López', 'Primero', 2021, 8.0);

INSERT INTO usuarios (nombre, apellidos, curso, anoIngreso, notaMedia)
VALUES ('Pedro', 'Sánchez', 'Segundo', 2020, 7.5);

INSERT INTO usuarios (nombre, apellidos, curso, anoIngreso, notaMedia)
VALUES ('Laura', 'Hernández', 'Tercero', 2019, 8.7);

INSERT INTO usuarios (nombre, apellidos, curso, anoIngreso, notaMedia)
VALUES ('Sofía', 'Díaz', 'Cuarto', 2018, 9.1);

INSERT INTO usuarios (nombre, apellidos, curso, anoIngreso, notaMedia)
VALUES ('Daniel', 'Romero', 'Segundo', 2020, 7.8);

INSERT INTO usuarios (nombre, apellidos, curso, anoIngreso, notaMedia)
VALUES ('Elena', 'Torres', 'Primero', 2021, 8.3);

INSERT INTO usuarios (nombre, apellidos, curso, anoIngreso, notaMedia)
VALUES ('Javier', 'Gómez', 'Tercero', 2019, 8.9);