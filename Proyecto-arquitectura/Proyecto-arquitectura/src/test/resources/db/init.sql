CREATE DATABASE bd_arquitectura_corte1;

\connect  bd_arquitectura_corte1;

CREATE TABLE Tipo_usuario (
                              ID SERIAL PRIMARY KEY,
                              Tipo VARCHAR(15)
);

CREATE TABLE Usuario (
                         ID SERIAL PRIMARY KEY,
                         Nombre VARCHAR(255) UNIQUE,
                         Correo VARCHAR(255) UNIQUE,
                         Contrasena VARCHAR(255),
                         Tipo_usuario_ID INTEGER,
                         CONSTRAINT FK_Tipo_usuario_ID FOREIGN KEY (Tipo_usuario_ID) REFERENCES Tipo_usuario(ID)
);

CREATE TABLE Proyecto (
                          ID SERIAL PRIMARY KEY,
                          Nombre VARCHAR(30),
                          Descripcion VARCHAR(2000),
                          FechaInicio DATE,
                          Gerente_ID INTEGER,
                          CONSTRAINT FK_Gerente_ID FOREIGN KEY (Gerente_ID) REFERENCES Usuario(ID)
);

CREATE TABLE Usuario_proyecto (
                                  ID SERIAL PRIMARY KEY,
                                  FechaInicio DATE,
                                  Usuario_ID INTEGER,
                                  Proyecto_ID INTEGER,
                                  CONSTRAINT FK_Usuario_proyecto_Usuario_ID FOREIGN KEY (Usuario_ID) REFERENCES Usuario(ID),
                                  CONSTRAINT FK_Usuario_proyecto_Proyecto_ID FOREIGN KEY (Proyecto_ID) REFERENCES Proyecto(ID)
);

CREATE TABLE Estado (
                        ID SERIAL PRIMARY KEY,
                        Estado VARCHAR(25)
);

CREATE TABLE Historia_usuario (
                                  ID SERIAL PRIMARY KEY,
                                  Detalles VARCHAR(2000),
                                  Criterios_aceptacion VARCHAR(2000),
                                  Usuario_historia_ID INTEGER,
                                  Estado_ID INTEGER,
                                  Proyecto_ID INTEGER,
                                  CONSTRAINT FK_Historia_usuario_Usuario_ID FOREIGN KEY (Usuario_historia_ID) REFERENCES Usuario_proyecto(ID),
                                  CONSTRAINT FK_Historia_usuario_Estado_ID FOREIGN KEY (Estado_ID) REFERENCES Estado(ID),
                                  CONSTRAINT FK_Historia_usuario_Proyecto_ID FOREIGN KEY (Proyecto_ID) REFERENCES Proyecto(ID)
);

CREATE TABLE Tarea (
                       ID SERIAL PRIMARY KEY,
                       Descripcion VARCHAR(2000),
                       Usuario_tarea_ID INTEGER,
                       Estado_tarea_ID INTEGER,
                       Historia_usuario_ID INTEGER,
                       CONSTRAINT FK_Tarea_Usuario_tarea_ID FOREIGN KEY (Usuario_tarea_ID) REFERENCES Usuario_proyecto(ID),
                       CONSTRAINT FK_Tarea_Estado_tarea_ID FOREIGN KEY (Estado_tarea_ID) REFERENCES Estado(ID),
                       CONSTRAINT FK_Tarea_Historia_usuario_ID FOREIGN KEY (Historia_usuario_ID) REFERENCES Historia_usuario(ID)
);

CREATE TABLE Cambio_estado (
                               ID SERIAL PRIMARY KEY,
                               Fecha_cambio DATE,
                               Estado_cambio_ID INTEGER,
                               Usuario_ID INTEGER,
                               Status_type VARCHAR(40),
                               CONSTRAINT FK_Cambio_estado_Estado_cambio_ID FOREIGN KEY (Estado_cambio_ID) REFERENCES Estado(ID)
);

-- Insertar en Tipo_usuario y Estado
INSERT INTO Tipo_usuario (Tipo) VALUES
    ('Gerente'),
    ('Desarrollador');

INSERT INTO Estado (Estado) VALUES
    ('Nueva'),
    ('En desarrollo'),
    ('Finalizada');

-- Insertar en Usuario
INSERT INTO Usuario (Nombre, Correo, Contrasena, Tipo_usuario_ID)
VALUES
    ('Gerente1', 'gerente1@example.com', 'contrasena1', 1),
    ('Gerente2', 'gerente2@example.com', 'contrasena2', 1),
    ('Gerente3', 'gerente3@example.com', 'contrasena3', 1),
    ('Gerente4', 'gerente4@example.com', 'contrasena4', 1),
    ('Desarrollador1', 'desarrollador1@example.com', 'contrasena3', 2),
    ('Desarrollador2', 'desarrollador2@example.com', 'contrasena4', 2),
    ('Desarrollador3', 'desarrollador3@example.com', 'contrasena5', 2),
    ('Desarrollador4', 'desarrollador4@example.com', 'contrasena6', 2),
    ('Desarrollador5', 'desarrollador5@example.com', 'contrasena7', 2),
    ('Desarrollador6', 'desarrollador6@example.com', 'contrasena8', 2),
    ('Desarrollador7', 'desarrollador7@example.com', 'contrasena9', 2),
    ('Desarrollador8', 'desarrollador8@example.com', 'contrasena10', 2);

-- Insertar en Proyecto
INSERT INTO Proyecto (Nombre, Descripcion, FechaInicio, Gerente_ID)
VALUES
    ('Proyecto_X', 'Descripción del Proyecto_X', CURRENT_DATE, 1),
    ('Proyecto_Y', 'Descripción del Proyecto_Y', CURRENT_DATE, 2),
    ('Proyecto_R', 'Descripción del Proyecto_R', CURRENT_DATE, 3),
    ('Proyecto_T', 'Descripción del Proyecto_T', CURRENT_DATE, 4),
    ('Proyecto_W', 'Descripción del Proyecto_W', CURRENT_DATE, 5);

-- Insertar en Usuario_proyecto
INSERT INTO Usuario_proyecto (FechaInicio, Usuario_ID, Proyecto_ID)
VALUES
    (CURRENT_DATE, 5, 3),
    (CURRENT_DATE, 6, 1),
    (CURRENT_DATE, 7, 3),
    (CURRENT_DATE, 8, 1),
    (CURRENT_DATE, 9, 4),
    (CURRENT_DATE, 10, 2),
    (CURRENT_DATE, 11, 4),
    (CURRENT_DATE, 12, 2);

-- Insertar en Historia_usuario
INSERT INTO Historia_usuario (Detalles, Criterios_aceptacion, Usuario_historia_ID, Estado_ID, Proyecto_ID)
VALUES
    ('Detalles de la historia 1', 'Criterios de aceptación de la historia 1', 1, 1, 2),
    ('Detalles de la historia 2', 'Criterios de aceptación de la historia 2', 2, 1, 3),
    ('Detalles de la historia 3', 'Criterios de aceptación de la historia 3', 3, 1, 4),
    ('Detalles de la historia 4', 'Criterios de aceptación de la historia 4', 1, 1, 2),
    ('Detalles de la historia 5', 'Criterios de aceptación de la historia 5', 2, 2, 1),
    ('Detalles de la historia 6', 'Criterios de aceptación de la historia 6', 3, 3, 3),
    ('Detalles de la historia 7', 'Criterios de aceptación de la historia 7', 4, 1, 4),
    ('Detalles de la historia 8', 'Criterios de aceptación de la historia 8', 5, 2, 2);

-- Insertar en Tarea
INSERT INTO Tarea (Descripcion, Usuario_tarea_ID, Estado_tarea_ID, Historia_usuario_ID)
VALUES
    ('Implementar la funcionalidad de inicio de sesión', 6, 1, 1),
    ('Diseñar la interfaz de usuario', 5, 2, 2),
    ('Escribir pruebas unitarias', 6, 3, 3),
    ('Optimizar consultas SQL', 7, 1, 4),
    ('Actualizar documentación', 8, 2, 5),
    ('Revisar el código de colega', 9, 3, 6),
    ('Realizar pruebas de integración', 10, 1, 7),
    ('Resolver incidencias de seguridad', 11, 2, 8);
