-- phpMyAdmin SQL Dump
-- version 5.1.1
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: May 05, 2022 at 09:03 PM
-- Server version: 10.4.22-MariaDB
-- PHP Version: 8.1.2

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `groceteria_db`
--

-- --------------------------------------------------------

--
-- Table structure for table `addresses`
--

CREATE TABLE `addresses` (
  `id` int(11) NOT NULL,
  `user_mobile_number` int(11) NOT NULL,
  `building_name` varchar(255) NOT NULL,
  `apartment` varchar(255) NOT NULL,
  `delivery_instructions` varchar(255) NOT NULL,
  `receiver_name` varchar(255) NOT NULL,
  `receiver_mobile_number` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `addresses`
--

INSERT INTO `addresses` (`id`, `user_mobile_number`, `building_name`, `apartment`, `delivery_instructions`, `receiver_name`, `receiver_mobile_number`) VALUES
(1, 4543, 'ufiuy', 'uyfiu', 'yuiuy', 'gfuy', 668),
(2, 7889, 'omar', 'lebanon', 'main road', 'omar', 7397636);

-- --------------------------------------------------------

--
-- Table structure for table `signup_accounts`
--

CREATE TABLE `signup_accounts` (
  `id` int(11) NOT NULL,
  `first_name` varchar(255) NOT NULL,
  `last_name` varchar(255) NOT NULL,
  `mobile_number` int(11) NOT NULL,
  `email` varchar(255) NOT NULL,
  `year_of_birth` int(11) NOT NULL,
  `password` varchar(255) NOT NULL,
  `confirm_password` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `signup_accounts`
--

INSERT INTO `signup_accounts` (`id`, `first_name`, `last_name`, `mobile_number`, `email`, `year_of_birth`, `password`, `confirm_password`) VALUES
(1, 'Omar', 'Malaeb', 7889, 'omarmlb002@gmail.com', 2002, 'hi0123456', 'hi0123456'),
(5, 'Omar', 'Malaeb', 7889002, 'omarmlb@gmail.com', 2002, 'hi0123456', 'hi0123456'),
(6, 'Alaa', 'Malaeb', 70485427, 'alaa@gmail.com', 2006, 'alaa12345', 'alaa12345'),
(8, 'Rami', 'Malaeb', 3456, 'rami@gmail.com', 2010, 'hi234567', 'hi234567'),
(9, 'omar', 'malaeb', 1234, 'omar@gmail.com', 2002, 'hi123456', 'hi123456'),
(11, 'hanan', 'mlb', 123456, 'hanan@gmail.com', 1999, 'hi123456', 'hi123456'),
(16, 'Rami', 'Malaeb', 3565, 'rami1@gmail.com', 2010, 'hi234567', 'hi234567'),
(17, 'Rami', 'Malaeb', 6753, 'rami12@gmail.com', 2010, 'hi234567', 'hi234567'),
(19, 'gug', 'hjv', 32432, 'kbhh@gmail.com', 2002, 'vhjvjhvjevh', 'vhjvjhvjevh'),
(20, 'kjbhj', 'bjkb', 786887, 'gfj@gmail.com', 6777, 'yggug123', 'yggug123');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `addresses`
--
ALTER TABLE `addresses`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `user_mobile_number` (`user_mobile_number`);

--
-- Indexes for table `signup_accounts`
--
ALTER TABLE `signup_accounts`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `mobile_number` (`mobile_number`),
  ADD UNIQUE KEY `email` (`email`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `addresses`
--
ALTER TABLE `addresses`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- AUTO_INCREMENT for table `signup_accounts`
--
ALTER TABLE `signup_accounts`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=21;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
