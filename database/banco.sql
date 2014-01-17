CREATE DATABASE  IF NOT EXISTS `banco` /*!40100 DEFAULT CHARACTER SET utf8 */;
USE `banco`;
-- MySQL dump 10.13  Distrib 5.6.11, for Win32 (x86)
--
-- Host: localhost    Database: banco
-- ------------------------------------------------------
-- Server version	5.6.13

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `cuenta_bancaria`
--

DROP TABLE IF EXISTS `cuenta_bancaria`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `cuenta_bancaria` (
  `idCuenta_Bancaria` int(11) NOT NULL,
  `id_SucursalBancaria` int(11) DEFAULT NULL,
  `numeroDeCuenta` varchar(45) DEFAULT NULL,
  `cif` varchar(45) DEFAULT NULL,
  `dc` varchar(45) DEFAULT NULL,
  `saldo` decimal(40,2) DEFAULT NULL,
  PRIMARY KEY (`idCuenta_Bancaria`),
  KEY `id_sucursal_idx` (`id_SucursalBancaria`),
  CONSTRAINT `id_sucursal` FOREIGN KEY (`id_SucursalBancaria`) REFERENCES `sucursal` (`idSucursal`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `cuenta_bancaria`
--

LOCK TABLES `cuenta_bancaria` WRITE;
/*!40000 ALTER TABLE `cuenta_bancaria` DISABLE KEYS */;
/*!40000 ALTER TABLE `cuenta_bancaria` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `entidad_bancaria`
--

DROP TABLE IF EXISTS `entidad_bancaria`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `entidad_bancaria` (
  `idEntidad` int(11) NOT NULL,
  `codigo` varchar(45) DEFAULT NULL,
  `nombre` varchar(45) DEFAULT NULL,
  `cif` varchar(45) DEFAULT NULL,
  `tipoEntidadBancaria` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`idEntidad`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `entidad_bancaria`
--

LOCK TABLES `entidad_bancaria` WRITE;
/*!40000 ALTER TABLE `entidad_bancaria` DISABLE KEYS */;
INSERT INTO `entidad_bancaria` VALUES (1,'155','Bankia2','1234','CAJADEAHORRO'),(555,'555','prueba','555','BANCO'),(666,'666','prueba','555','BANCO'),(777,'777','777','777','BANCO'),(888,'888','888','888','CAJADEAHORRO'),(999,'999','999','999','CAJADEAHORRO'),(1000,'888','888','888','CAJADEAHORRO'),(55555,'5555','55555','5555','BANCO');
/*!40000 ALTER TABLE `entidad_bancaria` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `movimiento`
--

DROP TABLE IF EXISTS `movimiento`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `movimiento` (
  `idMovimiento` int(11) NOT NULL,
  `tipoDeMovimiento` varchar(45) DEFAULT NULL,
  `importe` decimal(40,2) DEFAULT NULL,
  `fecha` datetime DEFAULT NULL,
  `saldo` decimal(40,2) DEFAULT NULL,
  `concepto` varchar(45) DEFAULT NULL,
  `idCuentaBancaria` int(11) DEFAULT NULL,
  PRIMARY KEY (`idMovimiento`),
  KEY `idCuenta_idx` (`idCuentaBancaria`),
  CONSTRAINT `idCuenta` FOREIGN KEY (`idCuentaBancaria`) REFERENCES `cuenta_bancaria` (`idCuenta_Bancaria`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `movimiento`
--

LOCK TABLES `movimiento` WRITE;
/*!40000 ALTER TABLE `movimiento` DISABLE KEYS */;
/*!40000 ALTER TABLE `movimiento` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `sucursal`
--

DROP TABLE IF EXISTS `sucursal`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `sucursal` (
  `idSucursal` int(11) NOT NULL,
  `id_entidadBancaria` int(11) DEFAULT NULL,
  `codigo` varchar(45) DEFAULT NULL,
  `nombre` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`idSucursal`),
  KEY `id_entidadBancaria_idx` (`id_entidadBancaria`),
  CONSTRAINT `id_entidadBancaria` FOREIGN KEY (`id_entidadBancaria`) REFERENCES `entidad_bancaria` (`idEntidad`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `sucursal`
--

LOCK TABLES `sucursal` WRITE;
/*!40000 ALTER TABLE `sucursal` DISABLE KEYS */;
INSERT INTO `sucursal` VALUES (1,1,'1','Sucursal1'),(2,1,'2','Sucursal2'),(3,1,'3','Sucursal3');
/*!40000 ALTER TABLE `sucursal` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2013-11-25 12:33:47
