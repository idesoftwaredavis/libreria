-- --------------------------------------------------------
-- Host:                         127.0.0.1
-- Versión del servidor:         10.5.9-MariaDB - mariadb.org binary distribution
-- SO del servidor:              Win64
-- HeidiSQL Versión:             11.0.0.5919
-- --------------------------------------------------------

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET NAMES utf8 */;
/*!50503 SET NAMES utf8mb4 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;


-- Volcando estructura de base de datos para libreria_udemy
CREATE DATABASE IF NOT EXISTS `libreria_udemy` /*!40100 DEFAULT CHARACTER SET latin1 */;
USE `libreria_udemy`;



-- Volcando estructura para tabla libreria_udemy.libros
CREATE TABLE IF NOT EXISTS `libros` (
  `id` int(11) NOT NULL,
  `titulo` varchar(100) DEFAULT NULL,
  `genero` varchar(100) DEFAULT NULL,
  `autor` varchar(100) DEFAULT NULL,
  `copias` int(11) DEFAULT NULL,
  `novedad` tinyint(4) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- Volcando datos para la tabla libreria_udemy.libros: ~20 rows (aproximadamente)
/*!40000 ALTER TABLE `libros` DISABLE KEYS */;
INSERT INTO `libros` (`id`, `titulo`, `genero`, `autor`, `copias`, `novedad`) VALUES
	(1, 'Robinson Crusoe', 'Aventuras', 'Daniel Defoe', 4, 0),
	(2, 'Los miserables', 'Histórica', 'Victor Hugo', 4, 1),
	(3, 'Viaje al centro de la tierra', 'Aventuras', 'Julio Verne', 3, 0),
	(4, '1984', 'Ciencia Ficción', 'George Orwell', 2, 0),
	(5, 'Los pilares de la tierra', 'Histórica', 'Ken Follet', 10, 1),
	(6, 'Un mundo feliz', 'Ciencia Ficción', 'Aldous Huxley', 6, 0),
	(7, 'Cien años de soledad', 'Aventuras', 'Gabriel García Márquez', 10, 0),
	(8, 'Orgullo y prejuicio', 'Romántica', 'Jane Austen', 4, 0),
	(9, 'El Señor de los Anillos', 'Ciencia Ficción', 'J.R. Tolkien', 8, 1),
	(10, 'El sueño eterno', 'Policiaca', 'Raymond Chandler', 4, 1),
	(11, 'Pensar rápido, pensar despacio', 'Científica', 'Daniel Kahneman', 4, 1),
	(12, 'Asesinato en el Orient Express', 'Policiaca', 'Agatha Christie', 5, 0),
	(13, 'El resplandor', 'Terror', 'Stephen King', 8, 0),
	(14, 'Sapiens: de animales a dioses', 'Científica', 'Yuval Noah Harari', 6, 0),
	(15, 'El exorcista', 'Terror', 'William Peter Blatty', 3, 0),
	(16, 'Steve Jobs', 'Biografía', 'Walter Isaacson', 6, 0),
	(17, 'Elon Musk', 'Biografía', 'Ashlee Vance', 2, 1),
	(18, 'El silencio de los corderos', 'Terror', 'Thomas Harris', 3, 0),
	(19, 'El guardián entre el centeno', 'Romántica', 'J.D. Salinger', 4, 0),
	(20, 'El señor de las moscas', 'Aventuras', 'William Golding', 6, 0);
/*!40000 ALTER TABLE `libros` ENABLE KEYS */;

-- Volcando estructura para tabla libreria_udemy.usuarios
CREATE TABLE IF NOT EXISTS `usuarios` (
  `username` varchar(100) NOT NULL,
  `contrasena` varchar(100) DEFAULT NULL,
  `nombre` varchar(100) DEFAULT NULL,
  `apellidos` varchar(100) DEFAULT NULL,
  `email` varchar(100) DEFAULT NULL,
  `saldo` double(22,2) DEFAULT NULL,
  `premium` tinyint(4) DEFAULT NULL,
  PRIMARY KEY (`username`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- Volcando datos para la tabla libreria_udemy.usuarios: ~3 rows (aproximadamente)
/*!40000 ALTER TABLE `usuarios` DISABLE KEYS */;
/*!40000 ALTER TABLE `usuarios` ENABLE KEYS */;

-- Volcando estructura para tabla libreria_udemy.alquiler
CREATE TABLE IF NOT EXISTS `alquiler` (
  `id` int(11) NOT NULL,
  `username` varchar(100) NOT NULL,
  `fecha` datetime NOT NULL,
  PRIMARY KEY (`id`,`username`,`fecha`) USING BTREE,
  KEY `FK__usuarios` (`username`),
  CONSTRAINT `FK__libros` FOREIGN KEY (`id`) REFERENCES `libros` (`id`),
  CONSTRAINT `FK__usuarios` FOREIGN KEY (`username`) REFERENCES `usuarios` (`username`)
) ENGINE=InnoDB DEFAULT CHARSET=LATIN1;

-- Volcando datos para la tabla libreria_udemy.alquiler: ~1 rows (aproximadamente)
/*!40000 ALTER TABLE `alquiler` DISABLE KEYS */;
/*!40000 ALTER TABLE `alquiler` ENABLE KEYS */;

/*!40101 SET SQL_MODE=IFNULL(@OLD_SQL_MODE, '') */;
/*!40014 SET FOREIGN_KEY_CHECKS=IF(@OLD_FOREIGN_KEY_CHECKS IS NULL, 1, @OLD_FOREIGN_KEY_CHECKS) */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
