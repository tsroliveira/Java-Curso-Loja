-- MySQL dump 10.13  Distrib 8.0.33, for Win64 (x86_64)
--
-- Host: localhost    Database: loja
-- ------------------------------------------------------
-- Server version	8.0.33

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8mb4 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `cidade`
--

DROP TABLE IF EXISTS `cidade`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `cidade` (
  `id` bigint NOT NULL,
  `nome` varchar(255) DEFAULT NULL,
  `sigla` varchar(255) DEFAULT NULL,
  `estado` varchar(255) DEFAULT NULL,
  `estado_id` bigint DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKkworrwk40xj58kevvh3evi500` (`estado_id`),
  CONSTRAINT `FKkworrwk40xj58kevvh3evi500` FOREIGN KEY (`estado_id`) REFERENCES `estado` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `cidade`
--

LOCK TABLES `cidade` WRITE;
/*!40000 ALTER TABLE `cidade` DISABLE KEYS */;
INSERT INTO `cidade` VALUES (1,'Paracambi',NULL,NULL,3),(2,'Japeri',NULL,NULL,3),(4,'São Paulo',NULL,NULL,1),(5,'Guarulhos',NULL,NULL,1),(6,'Curitiba',NULL,NULL,52),(7,'Itajaí',NULL,NULL,54);
/*!40000 ALTER TABLE `cidade` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `cidade_seq`
--

DROP TABLE IF EXISTS `cidade_seq`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `cidade_seq` (
  `next_val` bigint DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `cidade_seq`
--

LOCK TABLES `cidade_seq` WRITE;
/*!40000 ALTER TABLE `cidade_seq` DISABLE KEYS */;
INSERT INTO `cidade_seq` VALUES (101);
/*!40000 ALTER TABLE `cidade_seq` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `cliente`
--

DROP TABLE IF EXISTS `cliente`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `cliente` (
  `id` bigint NOT NULL,
  `nome` varchar(255) DEFAULT NULL,
  `sigla` varchar(255) DEFAULT NULL,
  `cpf` varchar(255) DEFAULT NULL,
  `email` varchar(255) DEFAULT NULL,
  `senha` varchar(255) DEFAULT NULL,
  `cidade_id` bigint DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKc2150e1t4hbr2owxjx1smbcwc` (`cidade_id`),
  CONSTRAINT `FKc2150e1t4hbr2owxjx1smbcwc` FOREIGN KEY (`cidade_id`) REFERENCES `cidade` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `cliente`
--

LOCK TABLES `cliente` WRITE;
/*!40000 ALTER TABLE `cliente` DISABLE KEYS */;
INSERT INTO `cliente` VALUES (1,'Ana Maria Braga',NULL,NULL,'amb@email.com','$2a$10$nYmzJj4tdmYMjmNasnG0JORaYktE7f1OQckH6T7RvJNd.hRdf5/uq',5),(2,'Ana Maria Braga',NULL,NULL,'amb@email.com','$2a$10$EP.at5qKJtBlWDsN7CgxNee9WMrOaEdUeaNzQTtLx75cVDbchAvxm',5),(3,'Thiago Oliveira',NULL,'dadasdd','admin','$2a$10$10aCHhOwC5oGnRimlJFzJeF3vf3.Em1GgN4uYK85x9hcdxbeqHM3O',4),(52,'Thiago Cliente',NULL,'118.994.317-48','thiago','$2a$10$Q2bKjCcK8smM1Dc4BvAZBe.LpC4n4EJdJrRIc0c2SmOkNYnfTOAU.',4);
/*!40000 ALTER TABLE `cliente` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `cliente_seq`
--

DROP TABLE IF EXISTS `cliente_seq`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `cliente_seq` (
  `next_val` bigint DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `cliente_seq`
--

LOCK TABLES `cliente_seq` WRITE;
/*!40000 ALTER TABLE `cliente_seq` DISABLE KEYS */;
INSERT INTO `cliente_seq` VALUES (151);
/*!40000 ALTER TABLE `cliente_seq` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `compra`
--

DROP TABLE IF EXISTS `compra`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `compra` (
  `id` bigint NOT NULL,
  `data_compra` datetime(6) DEFAULT NULL,
  `forma_pagamento` varchar(255) DEFAULT NULL,
  `valor_total` double DEFAULT NULL,
  `cidade_id` bigint DEFAULT NULL,
  `cliente_id` bigint DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKmh0hv6jro3mml22v6dpvsxceo` (`cidade_id`),
  KEY `FKni21w35sfgo033m8l93ki11ab` (`cliente_id`),
  CONSTRAINT `FKmh0hv6jro3mml22v6dpvsxceo` FOREIGN KEY (`cidade_id`) REFERENCES `cidade` (`id`),
  CONSTRAINT `FKni21w35sfgo033m8l93ki11ab` FOREIGN KEY (`cliente_id`) REFERENCES `cliente` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `compra`
--

LOCK TABLES `compra` WRITE;
/*!40000 ALTER TABLE `compra` DISABLE KEYS */;
INSERT INTO `compra` VALUES (52,'2023-12-15 16:54:35.027000','cartaoDeCredito',8900,NULL,3),(102,'2023-12-15 16:56:11.695000','cartaoDeCredito',27800,NULL,3),(152,NULL,'cartaoDeCredito',56700,NULL,3),(202,'2023-12-15 16:57:30.750000','cartaoDeCredito',62300,NULL,3),(252,'2023-12-15 17:13:45.021000','cartaoDeCredito',218000,NULL,3),(253,'2023-12-15 17:14:44.242000','pix',0,NULL,3),(254,'2023-12-15 17:14:55.114000','boleto',0,NULL,3),(302,'2023-12-15 18:31:49.160000','boleto',109000,NULL,3),(352,'2023-12-15 18:34:18.263000','cartaoDeCredito',109000,NULL,3),(402,'2023-12-15 18:35:47.511000','cartaoDeCredito',18900,NULL,3),(403,'2023-12-15 18:36:55.522000','cartaoDeCredito',109000,NULL,3),(404,'2023-12-15 18:38:18.594000','boleto',109000,NULL,3),(405,'2023-12-15 18:40:12.093000','cartaoDeCredito',109000,NULL,3),(406,'2023-12-15 18:40:32.330000','cartaoDeCredito',109000,NULL,3),(407,'2023-12-15 18:40:46.388000','cartaoDeCredito',18900,NULL,3),(408,'2023-12-15 18:41:18.886000','cartaoDeCredito',18900,NULL,3),(409,'2023-12-15 18:42:03.909000','cartaoDeCredito',18900,NULL,3),(410,'2023-12-15 18:42:53.063000','cartaoDeCredito',18900,NULL,3),(411,'2023-12-15 18:43:44.146000','cartaoDeCredito',8900,NULL,3),(452,'2024-01-30 17:30:38.625000','pix',12199,NULL,3);
/*!40000 ALTER TABLE `compra` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `compra_seq`
--

DROP TABLE IF EXISTS `compra_seq`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `compra_seq` (
  `next_val` bigint DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `compra_seq`
--

LOCK TABLES `compra_seq` WRITE;
/*!40000 ALTER TABLE `compra_seq` DISABLE KEYS */;
INSERT INTO `compra_seq` VALUES (551);
/*!40000 ALTER TABLE `compra_seq` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `entrada_itens`
--

DROP TABLE IF EXISTS `entrada_itens`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `entrada_itens` (
  `id` bigint NOT NULL,
  `data_entrada` datetime(6) DEFAULT NULL,
  `observacao` varchar(255) DEFAULT NULL,
  `funcionario_id` bigint DEFAULT NULL,
  `produto` varbinary(255) DEFAULT NULL,
  `quantidade` double DEFAULT NULL,
  `valor_produto` double DEFAULT NULL,
  `entrada_id` bigint DEFAULT NULL,
  `produto_id` bigint DEFAULT NULL,
  `valor_venda` double DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK66eh2ahkke9o8hdfgccbc3iha` (`funcionario_id`),
  KEY `FKp6nssmpn3cycu6t8d4pq9fq98` (`entrada_id`),
  KEY `FKc8elh3ionpvqpgs1l3clhk5i1` (`produto_id`),
  CONSTRAINT `FK66eh2ahkke9o8hdfgccbc3iha` FOREIGN KEY (`funcionario_id`) REFERENCES `funcionario` (`id`),
  CONSTRAINT `FKc8elh3ionpvqpgs1l3clhk5i1` FOREIGN KEY (`produto_id`) REFERENCES `produtos` (`id`),
  CONSTRAINT `FKp6nssmpn3cycu6t8d4pq9fq98` FOREIGN KEY (`entrada_id`) REFERENCES `entrada_produto` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `entrada_itens`
--

LOCK TABLES `entrada_itens` WRITE;
/*!40000 ALTER TABLE `entrada_itens` DISABLE KEYS */;
/*!40000 ALTER TABLE `entrada_itens` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `entrada_itens_seq`
--

DROP TABLE IF EXISTS `entrada_itens_seq`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `entrada_itens_seq` (
  `next_val` bigint DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `entrada_itens_seq`
--

LOCK TABLES `entrada_itens_seq` WRITE;
/*!40000 ALTER TABLE `entrada_itens_seq` DISABLE KEYS */;
INSERT INTO `entrada_itens_seq` VALUES (51);
/*!40000 ALTER TABLE `entrada_itens_seq` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `entrada_produto`
--

DROP TABLE IF EXISTS `entrada_produto`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `entrada_produto` (
  `id` bigint NOT NULL,
  `data_entrada` datetime(6) DEFAULT NULL,
  `funcionario` varbinary(255) DEFAULT NULL,
  `observacao` varchar(255) DEFAULT NULL,
  `funcionario_id` bigint DEFAULT NULL,
  `fornecedor` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK71v8hcdsjjvfot7xysambghij` (`funcionario_id`),
  CONSTRAINT `FK71v8hcdsjjvfot7xysambghij` FOREIGN KEY (`funcionario_id`) REFERENCES `funcionario` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `entrada_produto`
--

LOCK TABLES `entrada_produto` WRITE;
/*!40000 ALTER TABLE `entrada_produto` DISABLE KEYS */;
INSERT INTO `entrada_produto` VALUES (1,'2023-12-07 19:30:02.333000',NULL,'',152,'');
/*!40000 ALTER TABLE `entrada_produto` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `entrada_produto_seq`
--

DROP TABLE IF EXISTS `entrada_produto_seq`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `entrada_produto_seq` (
  `next_val` bigint DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `entrada_produto_seq`
--

LOCK TABLES `entrada_produto_seq` WRITE;
/*!40000 ALTER TABLE `entrada_produto_seq` DISABLE KEYS */;
INSERT INTO `entrada_produto_seq` VALUES (51);
/*!40000 ALTER TABLE `entrada_produto_seq` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `estado`
--

DROP TABLE IF EXISTS `estado`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `estado` (
  `id` bigint NOT NULL,
  `nome` varchar(255) DEFAULT NULL,
  `sigla` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `estado`
--

LOCK TABLES `estado` WRITE;
/*!40000 ALTER TABLE `estado` DISABLE KEYS */;
INSERT INTO `estado` VALUES (1,'São Paulo','SP'),(3,'Rio de Janeiro','RJ'),(52,'Paraná','PR'),(53,'Rio Grande do Sul','RS'),(54,'Santa Catarina','SC');
/*!40000 ALTER TABLE `estado` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `estado_seq`
--

DROP TABLE IF EXISTS `estado_seq`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `estado_seq` (
  `next_val` bigint DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `estado_seq`
--

LOCK TABLES `estado_seq` WRITE;
/*!40000 ALTER TABLE `estado_seq` DISABLE KEYS */;
INSERT INTO `estado_seq` VALUES (151);
/*!40000 ALTER TABLE `estado_seq` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `funcionario`
--

DROP TABLE IF EXISTS `funcionario`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `funcionario` (
  `id` bigint NOT NULL,
  `nome` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL,
  `salario_bruto` double DEFAULT NULL,
  `bairro` varchar(255) DEFAULT NULL,
  `cargo` varchar(255) DEFAULT NULL,
  `cep` varchar(255) DEFAULT NULL,
  `complemento` varchar(255) DEFAULT NULL,
  `data_entrada` date DEFAULT NULL,
  `data_saida` date DEFAULT NULL,
  `logradouro` varchar(255) DEFAULT NULL,
  `numero` varchar(255) DEFAULT NULL,
  `uf` varchar(255) DEFAULT NULL,
  `cidade_id` bigint DEFAULT NULL,
  `email` varchar(255) DEFAULT NULL,
  `senha` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKhoqxa81f767jcril4vbc8d2rg` (`cidade_id`),
  CONSTRAINT `FKhoqxa81f767jcril4vbc8d2rg` FOREIGN KEY (`cidade_id`) REFERENCES `cidade` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `funcionario`
--

LOCK TABLES `funcionario` WRITE;
/*!40000 ALTER TABLE `funcionario` DISABLE KEYS */;
INSERT INTO `funcionario` VALUES (152,'João Carlos',1282,'111','Auxiliar Administrativo','11111-111','111111','1111-11-11','1111-11-11','11111111','111111',NULL,2,'joao','$2a$10$UM/WnMpxSMc7qmwdbx4ZDe6J4xr4dAVFT9N0Vb68mps0GbuaF9Eiq'),(202,'Jon Lenon',2222222.22,'222','Suporte ao Cliente','22222-222','222','1023-10-23','1023-10-23','222','222','RJ',2,NULL,NULL),(203,'Thiago Oliveira',222222.22,'2','Auxiliar Administrativo','02937-040','2','1023-10-23','1023-10-23','2','2',NULL,2,'thiago-oliveira@live.com','$2a$10$cc4qY7I2Z6iJjrwnd/DUE.oJVstaKsB51b0laGiE7LA3mCYt9xkBy'),(352,'Carlos Teves',2300.11,'1','Auxiliar Administrativo','11111-040','1','1111-11-11','1111-11-11','1','1',NULL,2,NULL,NULL),(402,'Admin',111.11,'111111','Vendedor','11111-111','1','1111-11-11','1111-11-11','1','1',NULL,1,'admin','$2a$10$sYiu8hvWTofsDE0wrt9AxeO.9GNJOcKiAX/AjHa0lwt2OV65sVOoS'),(453,'Maria',2222.22,'22','Auxiliar Administrativo','22222-222','22','2223-10-22','2223-10-22','22','22',NULL,2,'admin','$2a$10$fG4htNzzF0KWxk3ZWpdXtOA3G9KKPoqeYBd44Rzi3sIZj9VzA.aUW');
/*!40000 ALTER TABLE `funcionario` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `funcionario_seq`
--

DROP TABLE IF EXISTS `funcionario_seq`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `funcionario_seq` (
  `next_val` bigint DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `funcionario_seq`
--

LOCK TABLES `funcionario_seq` WRITE;
/*!40000 ALTER TABLE `funcionario_seq` DISABLE KEYS */;
INSERT INTO `funcionario_seq` VALUES (601);
/*!40000 ALTER TABLE `funcionario_seq` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `itens_compra`
--

DROP TABLE IF EXISTS `itens_compra`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `itens_compra` (
  `id` bigint NOT NULL,
  `data_entrada` datetime(6) DEFAULT NULL,
  `fornecedor` varchar(255) DEFAULT NULL,
  `observacao` varchar(255) DEFAULT NULL,
  `funcionario_id` bigint DEFAULT NULL,
  `quantidade` int DEFAULT NULL,
  `valor_produto` double DEFAULT NULL,
  `compra_id` bigint DEFAULT NULL,
  `produto_id` bigint DEFAULT NULL,
  `valor_total` double DEFAULT NULL,
  `valor_unitatio` double DEFAULT NULL,
  `valor_unitario` double DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK395tjm73bya1wixanfu7ioudd` (`funcionario_id`),
  KEY `FK960bq6gf2xajffywe8a48fajn` (`compra_id`),
  KEY `FKjspbv1ss3c67f7togq9fvugwg` (`produto_id`),
  CONSTRAINT `FK395tjm73bya1wixanfu7ioudd` FOREIGN KEY (`funcionario_id`) REFERENCES `funcionario` (`id`),
  CONSTRAINT `FK960bq6gf2xajffywe8a48fajn` FOREIGN KEY (`compra_id`) REFERENCES `compra` (`id`),
  CONSTRAINT `FKjspbv1ss3c67f7togq9fvugwg` FOREIGN KEY (`produto_id`) REFERENCES `produtos` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `itens_compra`
--

LOCK TABLES `itens_compra` WRITE;
/*!40000 ALTER TABLE `itens_compra` DISABLE KEYS */;
INSERT INTO `itens_compra` VALUES (452,NULL,NULL,NULL,NULL,1,NULL,452,105,7999,NULL,7999),(453,NULL,NULL,NULL,NULL,1,NULL,452,106,4200,NULL,4200);
/*!40000 ALTER TABLE `itens_compra` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `itens_compra_seq`
--

DROP TABLE IF EXISTS `itens_compra_seq`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `itens_compra_seq` (
  `next_val` bigint DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `itens_compra_seq`
--

LOCK TABLES `itens_compra_seq` WRITE;
/*!40000 ALTER TABLE `itens_compra_seq` DISABLE KEYS */;
INSERT INTO `itens_compra_seq` VALUES (551);
/*!40000 ALTER TABLE `itens_compra_seq` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `perfil`
--

DROP TABLE IF EXISTS `perfil`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `perfil` (
  `id` bigint NOT NULL,
  `nome` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `perfil`
--

LOCK TABLES `perfil` WRITE;
/*!40000 ALTER TABLE `perfil` DISABLE KEYS */;
INSERT INTO `perfil` VALUES (1,'gerente'),(2,'vendedor'),(152,'cliente');
/*!40000 ALTER TABLE `perfil` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `perfil_seq`
--

DROP TABLE IF EXISTS `perfil_seq`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `perfil_seq` (
  `next_val` bigint DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `perfil_seq`
--

LOCK TABLES `perfil_seq` WRITE;
/*!40000 ALTER TABLE `perfil_seq` DISABLE KEYS */;
INSERT INTO `perfil_seq` VALUES (251);
/*!40000 ALTER TABLE `perfil_seq` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `permissoes`
--

DROP TABLE IF EXISTS `permissoes`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `permissoes` (
  `id` bigint NOT NULL,
  `data_cadastro` date DEFAULT NULL,
  `funcionario_id` bigint DEFAULT NULL,
  `perfil_id` bigint DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKkqxrlad9d07bm5u88xsljlx32` (`funcionario_id`),
  KEY `FKc9yrry2ibgi9rdf609s60us6w` (`perfil_id`),
  CONSTRAINT `FKc9yrry2ibgi9rdf609s60us6w` FOREIGN KEY (`perfil_id`) REFERENCES `perfil` (`id`),
  CONSTRAINT `FKkqxrlad9d07bm5u88xsljlx32` FOREIGN KEY (`funcionario_id`) REFERENCES `funcionario` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `permissoes`
--

LOCK TABLES `permissoes` WRITE;
/*!40000 ALTER TABLE `permissoes` DISABLE KEYS */;
INSERT INTO `permissoes` VALUES (1,'2023-11-06',203,1),(2,'2023-11-06',203,2),(52,'2023-11-06',402,1),(102,'2023-11-07',352,2),(152,'2023-11-07',152,2),(202,'2023-11-07',202,152);
/*!40000 ALTER TABLE `permissoes` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `permissoes_seq`
--

DROP TABLE IF EXISTS `permissoes_seq`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `permissoes_seq` (
  `next_val` bigint DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `permissoes_seq`
--

LOCK TABLES `permissoes_seq` WRITE;
/*!40000 ALTER TABLE `permissoes_seq` DISABLE KEYS */;
INSERT INTO `permissoes_seq` VALUES (301);
/*!40000 ALTER TABLE `permissoes_seq` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `produtos`
--

DROP TABLE IF EXISTS `produtos`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `produtos` (
  `id` bigint NOT NULL,
  `categoria` varchar(255) DEFAULT NULL,
  `descricao` varchar(255) DEFAULT NULL,
  `marca` varchar(255) DEFAULT NULL,
  `valor_venda` double DEFAULT NULL,
  `quantidade_estoque` double DEFAULT NULL,
  `nome_imagem` varchar(255) DEFAULT NULL,
  `titulo` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `produtos`
--

LOCK TABLES `produtos` WRITE;
/*!40000 ALTER TABLE `produtos` DISABLE KEYS */;
INSERT INTO `produtos` VALUES (105,'Notebook','MacBook Air M1','Apple',7999,0,'105_MacBook_Air_M1.png','MacBook Air'),(106,'Notebook','Dell Inspiron 7306 Hybrid 2-in-1','Dell',4200,0,'106_Dell_Inspiron_7306_Hybrid_2-in-1.jpg','Dell Inspiron 7306'),(107,'Celular','iPhone 12 128Gb','Apple',4999,0,'107_iPhone12.jpg','iPhone 12'),(108,'Celular','iPhone 15 Pro Max 256Gb','Apple',10200,0,'108_iPhone15-Pro.jpg','iPhone 15 Pro Max'),(109,'Notebook','Lenovo Yoga Slim 7 13.3','Lenovo',4299.99,0,'109_Lenovo_Yoga_Slim_7_13.3.png','Lenovo Yoga Sl 7'),(110,'Computador','Mac Mini M1 16Gb 256Gb','Apple',8999.99,0,'110_Mac_Mini_M1.jpg','Mac Mini M1'),(111,'Notebook','MacBook Air M1 16Gb Ram 256Gb','Apple',7500,0,'111_MacBook_Air_M1.png','MacBook Air M1'),(112,'Celular','Samsung Galaxy S23 8Gb Ram 128Gb','Samsung',3999,0,'112_Samsung_Galaxy_S23.jpg','Samsung Galaxy S23'),(113,'Celular','Samsung Galaxy S23 Ultra 5g 16Gb Ram 256Gb','Samsung',8759.9,0,'113_Samsung_Galaxy_S23_Ultra_5G.jpg','Samsung Galaxy S23 Ultra');
/*!40000 ALTER TABLE `produtos` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `produtos_seq`
--

DROP TABLE IF EXISTS `produtos_seq`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `produtos_seq` (
  `next_val` bigint DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `produtos_seq`
--

LOCK TABLES `produtos_seq` WRITE;
/*!40000 ALTER TABLE `produtos_seq` DISABLE KEYS */;
INSERT INTO `produtos_seq` VALUES (201);
/*!40000 ALTER TABLE `produtos_seq` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping routines for database 'loja'
--
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2024-08-08 19:28:55
