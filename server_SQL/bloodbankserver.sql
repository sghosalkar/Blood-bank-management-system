-- phpMyAdmin SQL Dump
-- version 4.5.4.1deb2ubuntu2
-- http://www.phpmyadmin.net
--
-- Host: localhost
-- Generation Time: Apr 03, 2017 at 12:50 AM
-- Server version: 5.7.17-0ubuntu0.16.04.1
-- PHP Version: 7.0.15-0ubuntu0.16.04.4

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `bloodbankserver`
--

-- --------------------------------------------------------

--
-- Table structure for table `bloodbank`
--

CREATE TABLE `bloodbank` (
  `id` int(11) NOT NULL,
  `name` varchar(30) NOT NULL,
  `address` text NOT NULL,
  `contact_no` varchar(10) NOT NULL,
  `password` varchar(16) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `bloodbank`
--

INSERT INTO `bloodbank` (`id`, `name`, `address`, `contact_no`, `password`) VALUES
(1, 'yo', 'this', '9999999999', 'test'),
(2, 'yo', 'this', '9999999999', 'test'),
(3, 'yo', 'this', '9999999999', 'test'),
(4, 'yo', 'this', '9999999999', 'test'),
(5, 'yo', 'this', '9999999999', 'test'),
(6, 'yo', 'this', '9999999999', 'test'),
(7, 'yo', 'this', '9999999999', 'test'),
(8, 'yo', 'this', '9999999999', 'test'),
(9, 'yo', 'this', '9999999999', 'test');

-- --------------------------------------------------------

--
-- Table structure for table `date_table`
--

CREATE TABLE `date_table` (
  `id` int(11) NOT NULL,
  `day` int(11) NOT NULL,
  `month` int(11) NOT NULL,
  `year` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `date_table`
--

INSERT INTO `date_table` (`id`, `day`, `month`, `year`) VALUES
(1, 2, 4, 2017),
(2, 2, 4, 2017),
(3, 2, 4, 2017),
(5, 2, 4, 2017),
(6, 2, 4, 2017),
(7, 3, 4, 2017);

-- --------------------------------------------------------

--
-- Table structure for table `transactions`
--

CREATE TABLE `transactions` (
  `bloodbank_id` int(11) NOT NULL,
  `name` varchar(30) NOT NULL,
  `contact_no` varchar(10) NOT NULL,
  `type` varchar(10) NOT NULL,
  `blood_group` varchar(4) NOT NULL,
  `quantity` int(11) NOT NULL,
  `price` int(11) NOT NULL,
  `date_id` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `transactions`
--

INSERT INTO `transactions` (`bloodbank_id`, `name`, `contact_no`, `type`, `blood_group`, `quantity`, `price`, `date_id`) VALUES
(1, 'asstere', '1111111122', 'Donor', 'O+', 11, 11, 1),
(1, 'asstere', '1111111122', 'Donor', 'O+', 11, 11, 1),
(1, 'hello', '2222555533', 'Donor', 'O+', 22, 22, 7),
(1, 'world', '6666666666', 'Donor', 'B+', 99, 99, 7),
(1, 'yureka', '9999999999', 'Donor', 'O+', 66, 88, 7);

--
-- Indexes for dumped tables
--

--
-- Indexes for table `bloodbank`
--
ALTER TABLE `bloodbank`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `date_table`
--
ALTER TABLE `date_table`
  ADD PRIMARY KEY (`id`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `bloodbank`
--
ALTER TABLE `bloodbank`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=10;
--
-- AUTO_INCREMENT for table `date_table`
--
ALTER TABLE `date_table`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=8;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
