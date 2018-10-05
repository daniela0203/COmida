CREATE DATABASE deportes;

CREATE TABLE `deportes`.`usuarios` (
  `idusuarios` INT NOT NULL AUTO_INCREMENT,
  `nombre` VARCHAR(45) NULL,
  `correo` VARCHAR(100) NULL,
  `contrasenia` VARCHAR(15) NULL,
  PRIMARY KEY (`idusuarios`));

INSERT INTO `usuarios` (`nombre`, `correo`, `contrasenia`) VALUES ('Alex', 'alexanderjaramillo4iep@gmail.com', '1234');
