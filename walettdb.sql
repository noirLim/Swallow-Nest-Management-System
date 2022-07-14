-- phpMyAdmin SQL Dump
-- version 5.1.1
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Nov 20, 2021 at 10:45 AM
-- Server version: 10.4.21-MariaDB
-- PHP Version: 8.0.11

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `walettdb`
--

-- --------------------------------------------------------

--
-- Table structure for table `admin`
--

CREATE TABLE `admin` (
  `adminId` int(11) NOT NULL,
  `username` varchar(30) NOT NULL,
  `password` varchar(30) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `admin`
--

INSERT INTO `admin` (`adminId`, `username`, `password`) VALUES
(1, 'steven123', 'steven4321'),
(2, 'Wilson002', 'wilson123'),
(3, 'Yulia02', 'yulia0220');

-- --------------------------------------------------------

--
-- Table structure for table `category`
--

CREATE TABLE `category` (
  `categoryID` char(4) NOT NULL,
  `categoryname` varchar(30) NOT NULL,
  `quality` varchar(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `category`
--

INSERT INTO `category` (`categoryID`, `categoryname`, `quality`) VALUES
('C001', 'Lily', 'Medium'),
('C002', 'Red Dates', 'Premium'),
('C003', 'Lily', 'Medium'),
('C004', 'Yan', 'Premium');

-- --------------------------------------------------------

--
-- Table structure for table `customer`
--

CREATE TABLE `customer` (
  `custno` char(4) NOT NULL,
  `firstname` varchar(30) NOT NULL,
  `lastname` varchar(30) NOT NULL,
  `address` varchar(40) NOT NULL,
  `telpno` int(8) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `customer`
--

INSERT INTO `customer` (`custno`, `firstname`, `lastname`, `address`, `telpno`) VALUES
('C001', 'Steven', 'Cang', 'meta street', 1092),
('C002', 'Wilsonsssss', 'Vitori', 'xyz street', 90123);

-- --------------------------------------------------------

--
-- Table structure for table `employees`
--

CREATE TABLE `employees` (
  `empID` char(5) NOT NULL,
  `firstname` varchar(30) NOT NULL,
  `lastname` varchar(30) NOT NULL,
  `address` varchar(30) NOT NULL,
  `email` varchar(30) NOT NULL,
  `telpno` int(11) NOT NULL,
  `salary` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `employees`
--

INSERT INTO `employees` (`empID`, `firstname`, `lastname`, `address`, `email`, `telpno`, `salary`) VALUES
('E0001', 'Meta', 'Meta', 'xyz Street', 'meta@gmail.com', 12354, 5000);

-- --------------------------------------------------------

--
-- Table structure for table `orderdetail`
--

CREATE TABLE `orderdetail` (
  `orderID` char(5) NOT NULL,
  `productID` char(6) NOT NULL,
  `quantity` int(11) NOT NULL,
  `price` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `orderdetail`
--

INSERT INTO `orderdetail` (`orderID`, `productID`, `quantity`, `price`) VALUES
('O0001', 'PR0001', 10, 300),
('O002', 'PR0003', 10, 20),
('OR001', 'PR0003', 15, 30),
('OR002', 'PR0004', 15, 400),
('xxx', 'PR0001', 10, 400);

-- --------------------------------------------------------

--
-- Table structure for table `orderwalet`
--

CREATE TABLE `orderwalet` (
  `orderID` char(5) NOT NULL,
  `custno` char(4) NOT NULL,
  `empID` char(5) NOT NULL,
  `purchasedate` date NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `orderwalet`
--

INSERT INTO `orderwalet` (`orderID`, `custno`, `empID`, `purchasedate`) VALUES
('O0001', 'C001', 'E0001', '2021-11-07'),
('O002', 'C001', 'E0002', '2021-11-11'),
('OR001', 'C001', 'E0001', '2021-11-15'),
('OR002', 'C001', 'E0001', '2021-11-12'),
('xxx', 'C001', 'E0001', '2021-11-07');

-- --------------------------------------------------------

--
-- Table structure for table `product`
--

CREATE TABLE `product` (
  `productID` char(6) NOT NULL,
  `supplierID` char(5) NOT NULL,
  `categoryID` char(4) NOT NULL,
  `productName` varchar(30) NOT NULL,
  `product_unit` int(11) NOT NULL,
  `product_price` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `product`
--

INSERT INTO `product` (`productID`, `supplierID`, `categoryID`, `productName`, `product_unit`, `product_price`) VALUES
('PR0001', 'S0001', 'C001', 'x', 32, 300),
('PR0002', 'S0001', 'C001', 'xx', 29, 20),
('PR0003', 'S0001', 'C001', 'Tiger Lily', 50, 30),
('PR0004', 'S0002', 'C003', 'Lily xy', 15, 400);

-- --------------------------------------------------------

--
-- Table structure for table `supplier`
--

CREATE TABLE `supplier` (
  `supplierID` char(5) NOT NULL,
  `contactname` varchar(30) NOT NULL,
  `telpno` int(8) NOT NULL,
  `companyname` varchar(30) NOT NULL,
  `address` varchar(30) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `supplier`
--

INSERT INTO `supplier` (`supplierID`, `contactname`, `telpno`, `companyname`, `address`) VALUES
('S0001', 'Wilson', 12332, 'lazy', 'xyz Street'),
('S0002', 'Tobi', 849374, 'Yan Company', 'tesla street');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `admin`
--
ALTER TABLE `admin`
  ADD PRIMARY KEY (`adminId`);

--
-- Indexes for table `category`
--
ALTER TABLE `category`
  ADD PRIMARY KEY (`categoryID`);

--
-- Indexes for table `customer`
--
ALTER TABLE `customer`
  ADD PRIMARY KEY (`custno`);

--
-- Indexes for table `employees`
--
ALTER TABLE `employees`
  ADD PRIMARY KEY (`empID`);

--
-- Indexes for table `orderdetail`
--
ALTER TABLE `orderdetail`
  ADD PRIMARY KEY (`orderID`);

--
-- Indexes for table `orderwalet`
--
ALTER TABLE `orderwalet`
  ADD PRIMARY KEY (`orderID`);

--
-- Indexes for table `product`
--
ALTER TABLE `product`
  ADD PRIMARY KEY (`productID`);

--
-- Indexes for table `supplier`
--
ALTER TABLE `supplier`
  ADD PRIMARY KEY (`supplierID`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
