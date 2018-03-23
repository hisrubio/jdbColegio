-- phpMyAdmin SQL Dump
-- version 4.6.5.2
-- https://www.phpmyadmin.net/
--
-- Servidor: 127.0.0.1
-- Tiempo de generación: 22-03-2018 a las 12:00:56
-- Versión del servidor: 10.1.21-MariaDB
-- Versión de PHP: 5.6.30

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de datos: `colegio`
--

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `alumno`
--

CREATE TABLE `alumno` (
  `Nif` varchar(9) NOT NULL,
  `nom_alumno` varchar(30) NOT NULL,
  `ape_alumno` varchar(40) NOT NULL,
  `foto` varchar(30) NOT NULL,
  `cod_curso` varchar(2) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Volcado de datos para la tabla `alumno`
--

INSERT INTO `alumno` (`Nif`, `nom_alumno`, `ape_alumno`, `foto`, `cod_curso`) VALUES
('12332113R', 'javi', 'ibarz', 'ibarz.jpg', '2H'),
('22222222K', 'noe', 'navarro', 'navarro.jpg', '1E'),
('25421587R', 'jorge', 'pimpinela', 'pimpinela.jpg', '1h'),
('33333333E', 'sergio', 'bazan', 'bazan.jpg', '2E'),
('44444444B', 'rafa', 'torrea', 'torrea', '2H'),
('56776554B', 'guia', 'canina', 'canina.jpg', '1M'),
('78787878Y', 'feo', 'garcia', 'garcia', '2E'),
('88888888I', 'hugo', 'yen', 'yen.jpg', '1M');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `asignatura`
--

CREATE TABLE `asignatura` (
  `cod_asignatura` varchar(4) NOT NULL,
  `nom_asignatura` varchar(50) NOT NULL,
  `horas` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Volcado de datos para la tabla `asignatura`
--

INSERT INTO `asignatura` (`cod_asignatura`, `nom_asignatura`, `horas`) VALUES
('CCC1', 'Cables 1', 50),
('CCC2', 'Cables 2', 60),
('DDI1', 'desarrollo de interfaces', 30),
('DDI2', 'desarrollo de interfaces 2', 20),
('EDD1', 'entornos de desarrollo', 40),
('SGE1', 'Sistemas de gestion empresarial 1', 400),
('SGE2', 'Sistemas de Gestion Empresarial 2', 300);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `curso`
--

CREATE TABLE `curso` (
  `cod_curso` varchar(2) NOT NULL,
  `nom_curso` varchar(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Volcado de datos para la tabla `curso`
--

INSERT INTO `curso` (`cod_curso`, `nom_curso`) VALUES
('1E', 'Curso de electricidad'),
('1h', 'Dam'),
('1M', 'Mecanizado'),
('2E', 'Curso de electricidad'),
('2H', 'Desarrollo de aplicaciones');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `cursoasignatura`
--

CREATE TABLE `cursoasignatura` (
  `cod_curso` varchar(2) NOT NULL,
  `cod_asignatura` varchar(4) NOT NULL,
  `nif_profesor` varchar(9) NOT NULL,
  `aula` varchar(4) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Volcado de datos para la tabla `cursoasignatura`
--

INSERT INTO `cursoasignatura` (`cod_curso`, `cod_asignatura`, `nif_profesor`, `aula`) VALUES
('1E', 'CCC1', '12345678A', 'BT4'),
('1E', 'CCC2', '32165498B', 'T4'),
('1h', 'DDI1', '14714725L', 'BT3'),
('1h', 'DDI2', '14714725L', 'BT4'),
('1M', 'SGE1', '96325874V', 'BB1');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `nota`
--

CREATE TABLE `nota` (
  `Nif_alumno` varchar(9) NOT NULL,
  `cod_asignatura` varchar(4) NOT NULL,
  `calificacion` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Volcado de datos para la tabla `nota`
--

INSERT INTO `nota` (`Nif_alumno`, `cod_asignatura`, `calificacion`) VALUES
('12332113R', 'CCC1', 5),
('22222222K', 'CCC2', 7),
('33333333E', 'DDI1', 6),
('33333333E', 'DDI2', 9),
('56776554B', 'SGE1', 3),
('78787878Y', 'DDI1', 4);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `profesor`
--

CREATE TABLE `profesor` (
  `Nif` varchar(9) NOT NULL,
  `nom_profesor` varchar(30) NOT NULL,
  `ape_profesor` varchar(40) NOT NULL,
  `foto` varchar(30) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Volcado de datos para la tabla `profesor`
--

INSERT INTO `profesor` (`Nif`, `nom_profesor`, `ape_profesor`, `foto`) VALUES
('12345678A', 'agustin', 'sole', 'sole.jpg'),
('14714725L', 'celia', 'perez', 'perez.jpg'),
('14725836C', 'maria jose', 'ramon', 'ramon.jpg'),
('32165498B', 'gorka', 'sanz', 'sanz.jpg'),
('36936936J', 'felipe', 'fernandez', 'fernandez.jpg'),
('96325874V', 'victor', 'munoz', 'munoz.jpg');

--
-- Índices para tablas volcadas
--

--
-- Indices de la tabla `alumno`
--
ALTER TABLE `alumno`
  ADD PRIMARY KEY (`Nif`),
  ADD KEY `cod_curso` (`cod_curso`);

--
-- Indices de la tabla `asignatura`
--
ALTER TABLE `asignatura`
  ADD PRIMARY KEY (`cod_asignatura`);

--
-- Indices de la tabla `curso`
--
ALTER TABLE `curso`
  ADD PRIMARY KEY (`cod_curso`);

--
-- Indices de la tabla `cursoasignatura`
--
ALTER TABLE `cursoasignatura`
  ADD PRIMARY KEY (`cod_curso`,`cod_asignatura`),
  ADD KEY `cod_curso` (`cod_curso`),
  ADD KEY `cod_asignatura` (`cod_asignatura`),
  ADD KEY `nif_profesor` (`nif_profesor`);

--
-- Indices de la tabla `nota`
--
ALTER TABLE `nota`
  ADD PRIMARY KEY (`Nif_alumno`,`cod_asignatura`),
  ADD KEY `Nif_alumno` (`Nif_alumno`),
  ADD KEY `cod_asignatura` (`cod_asignatura`);

--
-- Indices de la tabla `profesor`
--
ALTER TABLE `profesor`
  ADD PRIMARY KEY (`Nif`);

--
-- Restricciones para tablas volcadas
--

--
-- Filtros para la tabla `alumno`
--
ALTER TABLE `alumno`
  ADD CONSTRAINT `alumno_ibfk_1` FOREIGN KEY (`cod_curso`) REFERENCES `curso` (`cod_curso`) ON DELETE NO ACTION ON UPDATE CASCADE;

--
-- Filtros para la tabla `cursoasignatura`
--
ALTER TABLE `cursoasignatura`
  ADD CONSTRAINT `cursoasignatura_ibfk_1` FOREIGN KEY (`cod_curso`) REFERENCES `curso` (`cod_curso`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `cursoasignatura_ibfk_2` FOREIGN KEY (`cod_asignatura`) REFERENCES `asignatura` (`cod_asignatura`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `cursoasignatura_ibfk_3` FOREIGN KEY (`nif_profesor`) REFERENCES `profesor` (`Nif`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Filtros para la tabla `nota`
--
ALTER TABLE `nota`
  ADD CONSTRAINT `nota_ibfk_1` FOREIGN KEY (`Nif_alumno`) REFERENCES `alumno` (`Nif`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `nota_ibfk_2` FOREIGN KEY (`cod_asignatura`) REFERENCES `asignatura` (`cod_asignatura`) ON DELETE CASCADE ON UPDATE CASCADE;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
