-- phpMyAdmin SQL Dump
-- version 4.1.12
-- http://www.phpmyadmin.net
--
-- Host: 127.0.0.1
-- Generation Time: Feb 03, 2015 at 05:39 PM
-- Server version: 5.6.16
-- PHP Version: 5.5.11

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;

--
-- Database: `database`
--

-- --------------------------------------------------------

--
-- Table structure for table `db_user`
--

CREATE TABLE IF NOT EXISTS `db_user` (
  `Full name` char(20) NOT NULL,
  `Gender` char(1) NOT NULL,
  `Phone` char(10) NOT NULL,
  `Email` char(20) NOT NULL,
  `Address` char(20) NOT NULL,
  `Username` char(15) NOT NULL,
  `Password` char(20) NOT NULL,
  `City` int(10) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `db_user`
--

INSERT INTO `db_user` (`Full name`, `Gender`, `Phone`, `Email`, `Address`, `Username`, `Password`, `City`) VALUES
('Albert', 'M', '12345', 'albert@email.com', 'Kemanggisan', 'asd', 'zxc', 0)

-- --------------------------------------------------------

--
-- Table structure for table `stok`
--

CREATE TABLE IF NOT EXISTS `stok` (
  `ID` int(5) NOT NULL AUTO_INCREMENT,
  `Nama` char(15) NOT NULL,
  `Qty` int(3) NOT NULL,
  `Harga` int(10) NOT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=19 ;

--
-- Dumping data for table `stok`
--

INSERT INTO `stok` (`ID`, `Nama`, `Qty`, `Harga`) VALUES
(16, 'Es krim', 100, 3000),
(17, 'Chitato', 425, 5000),
(18, 'Frestea', 320, 7000);

-- --------------------------------------------------------

--
-- Table structure for table `transaksi`
--

CREATE TABLE IF NOT EXISTS `transaksi` (
  `ProductName` varchar(20) NOT NULL,
  `Qty` int(5) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `transaksi`
--

INSERT INTO `transaksi` (`ProductName`, `Qty`) VALUES
('Es krim', 100),
('Frestea', 30),
('Chitato', 75);

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
