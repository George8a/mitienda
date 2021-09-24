-- phpMyAdmin SQL Dump
-- version 5.0.2
-- https://www.phpmyadmin.net/
--
-- Servidor: 127.0.0.1:3306
-- Tiempo de generación: 23-09-2021 a las 23:59:00
-- Versión del servidor: 5.7.31
-- Versión de PHP: 7.3.21

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de datos: `mitienda`
--

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `categoria`
--

DROP TABLE IF EXISTS `categoria`;
CREATE TABLE IF NOT EXISTS `categoria` (
  `idCategoria` int(5) NOT NULL AUTO_INCREMENT,
  `nombre` varchar(50) COLLATE utf8_spanish2_ci NOT NULL,
  `descripcion` varchar(50) COLLATE utf8_spanish2_ci NOT NULL,
  PRIMARY KEY (`idCategoria`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_spanish2_ci;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `cliente`
--

DROP TABLE IF EXISTS `cliente`;
CREATE TABLE IF NOT EXISTS `cliente` (
  `idCliente` varchar(20) COLLATE utf8_spanish2_ci NOT NULL,
  `nombres` varchar(100) COLLATE utf8_spanish2_ci NOT NULL,
  `apellidos` varchar(100) COLLATE utf8_spanish2_ci NOT NULL,
  `direccion` text COLLATE utf8_spanish2_ci NOT NULL,
  `fechaNacimiento` date NOT NULL,
  `telefono` varchar(15) COLLATE utf8_spanish2_ci NOT NULL,
  `email` varchar(100) COLLATE utf8_spanish2_ci NOT NULL,
  `ESTADO` varchar(20) COLLATE utf8_spanish2_ci NOT NULL,
  PRIMARY KEY (`idCliente`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_spanish2_ci;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `detallefactura`
--

DROP TABLE IF EXISTS `detallefactura`;
CREATE TABLE IF NOT EXISTS `detallefactura` (
  `idDetalle` int(5) NOT NULL AUTO_INCREMENT,
  `idFactura` int(5) NOT NULL,
  `idProducto` int(10) NOT NULL,
  `cantidad` int(3) NOT NULL,
  `precio` double NOT NULL,
  PRIMARY KEY (`idDetalle`),
  KEY `idProducto` (`idProducto`),
  KEY `idFactura` (`idFactura`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_spanish2_ci;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `detallerol`
--

DROP TABLE IF EXISTS `detallerol`;
CREATE TABLE IF NOT EXISTS `detallerol` (
  `idDetalle` int(5) NOT NULL AUTO_INCREMENT,
  `idRol` int(5) NOT NULL,
  `idPermiso` int(5) NOT NULL,
  PRIMARY KEY (`idDetalle`,`idRol`),
  KEY `idPermiso` (`idPermiso`),
  KEY `idRol` (`idRol`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_spanish2_ci;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `factura`
--

DROP TABLE IF EXISTS `factura`;
CREATE TABLE IF NOT EXISTS `factura` (
  `idFactura` int(5) NOT NULL AUTO_INCREMENT,
  `idCliente` varchar(20) COLLATE utf8_spanish2_ci NOT NULL,
  `fechaFactura` datetime NOT NULL,
  `metoPago` varchar(20) COLLATE utf8_spanish2_ci NOT NULL,
  `totalFactura` double NOT NULL,
  PRIMARY KEY (`idFactura`),
  KEY `idCliente` (`idCliente`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_spanish2_ci;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `permiso`
--

DROP TABLE IF EXISTS `permiso`;
CREATE TABLE IF NOT EXISTS `permiso` (
  `idpermiso` int(5) NOT NULL AUTO_INCREMENT,
  `modulo` varchar(20) COLLATE utf8_spanish2_ci NOT NULL,
  `actualizar` tinyint(1) NOT NULL,
  `crear` tinyint(1) NOT NULL,
  `ver` tinyint(1) NOT NULL,
  PRIMARY KEY (`idpermiso`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_spanish2_ci;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `producto`
--

DROP TABLE IF EXISTS `producto`;
CREATE TABLE IF NOT EXISTS `producto` (
  `idProducto` int(10) NOT NULL,
  `idCategoria` int(5) NOT NULL,
  `nombre` varchar(50) COLLATE utf8_spanish2_ci NOT NULL,
  `precio` double NOT NULL,
  `stock` int(10) NOT NULL,
  PRIMARY KEY (`idProducto`),
  KEY `idCategoria` (`idCategoria`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_spanish2_ci;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `rol`
--

DROP TABLE IF EXISTS `rol`;
CREATE TABLE IF NOT EXISTS `rol` (
  `idRol` int(5) NOT NULL,
  `tipoRol` varchar(50) COLLATE utf8_spanish2_ci NOT NULL,
  PRIMARY KEY (`idRol`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_spanish2_ci;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `usuario`
--

DROP TABLE IF EXISTS `usuario`;
CREATE TABLE IF NOT EXISTS `usuario` (
  `idUsuario` int(5) NOT NULL AUTO_INCREMENT,
  `idRol` int(5) NOT NULL,
  `usuario` varchar(10) COLLATE utf8_spanish2_ci NOT NULL,
  `contraseña` varchar(200) COLLATE utf8_spanish2_ci NOT NULL,
  `ultimoIngreso` datetime NOT NULL,
  PRIMARY KEY (`idUsuario`),
  KEY `idRol` (`idRol`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_spanish2_ci;

--
-- Restricciones para tablas volcadas
--

--
-- Filtros para la tabla `detallefactura`
--
ALTER TABLE `detallefactura`
  ADD CONSTRAINT `detallefactura_ibfk_1` FOREIGN KEY (`idFactura`) REFERENCES `factura` (`idFactura`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `detallefactura_ibfk_2` FOREIGN KEY (`idProducto`) REFERENCES `producto` (`idProducto`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Filtros para la tabla `detallerol`
--
ALTER TABLE `detallerol`
  ADD CONSTRAINT `detallerol_ibfk_1` FOREIGN KEY (`idRol`) REFERENCES `rol` (`idRol`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `detallerol_ibfk_2` FOREIGN KEY (`idPermiso`) REFERENCES `permiso` (`idpermiso`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Filtros para la tabla `factura`
--
ALTER TABLE `factura`
  ADD CONSTRAINT `factura_ibfk_1` FOREIGN KEY (`idCliente`) REFERENCES `cliente` (`idCliente`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Filtros para la tabla `producto`
--
ALTER TABLE `producto`
  ADD CONSTRAINT `producto_ibfk_1` FOREIGN KEY (`idCategoria`) REFERENCES `categoria` (`idCategoria`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Filtros para la tabla `rol`
--
ALTER TABLE `rol`
  ADD CONSTRAINT `rol_ibfk_1` FOREIGN KEY (`idRol`) REFERENCES `usuario` (`idRol`) ON DELETE CASCADE ON UPDATE CASCADE;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
