/******************************************************************/ 
/* Titulo: PROYECTO PARA IOT DDL	                              */
/* Autor: Alejandro Delgado Cardona                               */
/* Fecha: 26 de octubre del 2023                                  */
/* Descripcion: base de datos para proyecto escaneo de matriculas */
/* Version: 01                                                    */
/*            			                                          */
/******************************************************************/

DROP DATABASE IF EXISTS alumnos_db;
CREATE DATABASE alumnos_db;
USE alumnos_db;

DROP TABLE IF EXISTS alumnos;
CREATE TABLE alumnos (
  id INT AUTO_INCREMENT,
  nombre VARCHAR(50) NOT NULL,
  apellidoPaterno VARCHAR(50) NOT NULL,
  apellidoMaterno VARCHAR(50) NOT NULL,
  matricula VARCHAR(10) NOT NULL,
  imagen VARCHAR(255),
  CONSTRAINT pk_alumnos_id PRIMARY KEY(id)
);