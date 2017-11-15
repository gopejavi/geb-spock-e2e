
-- phpMyAdmin SQL Dump
-- version 4.1.0-rc3
-- http://www.phpmyadmin.net
--
-- Host: 127.0.0.1
-- Generation Time: Jan 07, 2014 at 02:05 PM
-- Server version: 5.5.32
-- PHP Version: 5.5.7

--
-- Database: `vokuro`
--

-- --------------------------------------------------------

--
-- Table structure for table `email_confirmations`
--

DROP TABLE IF EXISTS `email_confirmations`;
CREATE TABLE IF NOT EXISTS `email_confirmations` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `usersId` int(10) unsigned NOT NULL,
  `code` char(32) NOT NULL,
  `createdAt` int(10) unsigned NOT NULL,
  `modifiedAt` int(10) unsigned DEFAULT NULL,
  `confirmed` char(1) DEFAULT 'N',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 AUTO_INCREMENT=1 ;

-- --------------------------------------------------------

--
-- Table structure for table `failed_logins`
--

DROP TABLE IF EXISTS `failed_logins`;
CREATE TABLE IF NOT EXISTS `failed_logins` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `usersId` int(10) unsigned DEFAULT NULL,
  `ipAddress` char(15) NOT NULL,
  `attempted` int(10) unsigned NOT NULL,
  PRIMARY KEY (`id`),
  KEY `usersId` (`usersId`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 AUTO_INCREMENT=1 ;

-- --------------------------------------------------------

--
-- Table structure for table `password_changes`
--

DROP TABLE IF EXISTS `password_changes`;
CREATE TABLE IF NOT EXISTS `password_changes` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `usersId` int(10) unsigned NOT NULL,
  `ipAddress` char(15) NOT NULL,
  `userAgent` text NOT NULL,
  `createdAt` int(10) unsigned NOT NULL,
  PRIMARY KEY (`id`),
  KEY `usersId` (`usersId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 AUTO_INCREMENT=1 ;

-- --------------------------------------------------------

--
-- Table structure for table `permissions`
--

DROP TABLE IF EXISTS `permissions`;
CREATE TABLE IF NOT EXISTS `permissions` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `profilesId` int(10) unsigned NOT NULL,
  `resource` varchar(16) NOT NULL,
  `action` varchar(16) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `profilesId` (`profilesId`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 AUTO_INCREMENT=23 ;

--
-- Dumping data for table `permissions`
--

INSERT INTO `permissions` (`id`, `profilesId`, `resource`, `action`) VALUES
(1, 3, 'users', 'index'),
(2, 3, 'users', 'search'),
(3, 3, 'profiles', 'index'),
(4, 3, 'profiles', 'search'),
(5, 1, 'users', 'index'),
(6, 1, 'users', 'search'),
(7, 1, 'users', 'edit'),
(8, 1, 'users', 'create'),
(9, 1, 'users', 'delete'),
(10, 1, 'users', 'changePassword'),
(11, 1, 'profiles', 'index'),
(12, 1, 'profiles', 'search'),
(13, 1, 'profiles', 'edit'),
(14, 1, 'profiles', 'create'),
(15, 1, 'profiles', 'delete'),
(16, 1, 'permissions', 'index'),
(17, 2, 'users', 'index'),
(18, 2, 'users', 'search'),
(19, 2, 'users', 'edit'),
(20, 2, 'users', 'create'),
(21, 2, 'profiles', 'index'),
(22, 2, 'profiles', 'search'),
(23, 4, 'users', 'index'),
(24, 4, 'users', 'search'),
(25, 4, 'users', 'edit'),
(26, 4, 'users', 'create'),
(27, 4, 'users', 'delete'),
(28, 4, 'users', 'changePassword'),
(29, 4, 'profiles', 'index'),
(30, 4, 'profiles', 'search'),
(31, 4, 'profiles', 'edit'),
(32, 4, 'profiles', 'create'),
(33, 4, 'profiles', 'delete'),
(34, 4, 'permissions', 'index');

-- --------------------------------------------------------

--
-- Table structure for table `profiles`
--

DROP TABLE IF EXISTS `profiles`;
CREATE TABLE IF NOT EXISTS `profiles` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `name` varchar(64) NOT NULL,
  `active` char(1) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `active` (`active`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 AUTO_INCREMENT=4 ;

--
-- Dumping data for table `profiles`
--

INSERT INTO `profiles` (`id`, `name`, `active`) VALUES
(1, 'Administrators', 'Y'),
(2, 'Users', 'Y'),
(3, 'Read-Only', 'Y'),
(4, 'Test-Profile', 'Y');

-- --------------------------------------------------------

--
-- Table structure for table `remember_tokens`
--

DROP TABLE IF EXISTS `remember_tokens`;
CREATE TABLE IF NOT EXISTS `remember_tokens` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `usersId` int(10) unsigned NOT NULL,
  `token` char(32) NOT NULL,
  `userAgent` varchar(120) NOT NULL,
  `createdAt` int(10) unsigned NOT NULL,
  PRIMARY KEY (`id`),
  KEY `token` (`token`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 AUTO_INCREMENT=1 ;

-- --------------------------------------------------------

--
-- Table structure for table `reset_passwords`
--

DROP TABLE IF EXISTS `reset_passwords`;
CREATE TABLE IF NOT EXISTS `reset_passwords` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `usersId` int(10) unsigned NOT NULL,
  `code` varchar(48) NOT NULL,
  `createdAt` int(10) unsigned NOT NULL,
  `modifiedAt` int(10) unsigned DEFAULT NULL,
  `reset` char(1) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `usersId` (`usersId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 AUTO_INCREMENT=1 ;

-- --------------------------------------------------------

--
-- Table structure for table `success_logins`
--

DROP TABLE IF EXISTS `success_logins`;
CREATE TABLE IF NOT EXISTS `success_logins` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `usersId` int(10) unsigned NOT NULL,
  `ipAddress` char(15) NOT NULL,
  `userAgent` varchar(120) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `usersId` (`usersId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 AUTO_INCREMENT=1 ;

-- --------------------------------------------------------

--
-- Table structure for table `users`
--

DROP TABLE IF EXISTS `users`;
CREATE TABLE IF NOT EXISTS `users` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `name` varchar(255) NOT NULL,
  `email` varchar(255) NOT NULL,
  `password` char(60) NOT NULL,
  `mustChangePassword` char(1) DEFAULT NULL,
  `profilesId` int(10) unsigned NOT NULL,
  `banned` char(1) NOT NULL,
  `suspended` char(1) NOT NULL,
  `active` char(1) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `profilesId` (`profilesId`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 AUTO_INCREMENT=5 ;

--
-- Dumping data for table `users`
--

INSERT INTO `users` (`id`, `name`, `email`, `password`, `mustChangePassword`, `profilesId`, `banned`, `suspended`, `active`) VALUES
(1, 'Bob Burnquist', 'bob@phalconphp.com', '$2a$08$Lx1577KNhPa9lzFYKssadetmbhaveRtCoVaOnoXXxUIhrqlCJYWCW', 'N', 1, 'N', 'N', 'Y'),
(2, 'Erik', 'erik@phalconphp.com', '$2a$08$f4llgFQQnhPKzpGmY1sOuuu23nYfXYM/EVOpnjjvAmbxxDxG3pbX.', 'N', 1, 'Y', 'Y', 'Y'),
(3, 'Veronica', 'veronica@phalconphp.com', '$2a$08$NQjrh9fKdMHSdpzhMj0xcOSwJQwMfpuDMzgtRyA89ADKUbsFZ94C2', 'N', 1, 'N', 'N', 'Y'),
(4, 'Yukimi Nagano', 'yukimi@phalconphp.com', '$2a$08$cxxpy4Jvt6Q3xGKgMWIILuf75RQDSroenvoB7L..GlXoGkVEMoSr.', 'N', 2, 'N', 'N', 'Y'),
(5, 'gopejavi', 'gopejavi@mailinator.com', '$2y$08$WmhHWGNsOGRBVktCdE9vSOKUS1drd.GEce1M0XmsYzjj81cILmrxS', 'N', 1, 'N', 'N', 'Y'),
(6, 'a', 'a@mailinator.com', '$2y$08$TEhOUlBZYmVHbzgvOXo4Z.Y/BygH6X1LQR7oyFSoSdqs2wqRuCt3O', 'Y', '3', 'N', 'N', 'Y'),
(7, 'b', 'b@mailinator.com', '$2y$08$VSszSko4c1E3bHJTdDE1UOWAdgHHu7zr.aMGxC14pFCkYk.gibMhS', 'Y', '3', 'N', 'N', 'Y'),
(8, 'c', 'c@mailinator.com', '$2y$08$V3A1UVgrbEl6M2ZhYVQzQuMcHJcW9d7sNsMC2RQxOmWhJ0pZ6v0ea', 'Y', '3', 'N', 'N', 'Y'),
(9, 'd', 'd@mailinator.com', '$2y$08$T0VySTlJT2VQaitCZzZuVuh8c9oZBGKe5FoGLvY7aq8cFTCdX32Du', 'Y', '3', 'N', 'N', 'Y'),
(10, 'e', 'e@mailinator.com', '$2y$08$WElGdUF5SHRaZnJzaFZFcOJH.SKBVEBSaHYHM5r18.jK9kOPpgaVO', 'Y', '3', 'N', 'N', 'Y'),
(11, 'f', 'f@mailinator.com', '$2y$08$bVdtMVRZOG9LYW13OTdWVeFUmzr4a31SgtZp/LhCFrONP4MnpABiO', 'Y', '3', 'N', 'N', 'Y'),
(12, 'g', 'g@mailinator.com', '$2y$08$VGdhUk1GQ2E2MHAwVWw0NOlClO2hnKoocxy030S7aembgzdSZwmHi', 'Y', '3', 'N', 'N', 'Y'),
(13, 'h', 'h@mailinator.com', '$2y$08$aVVIWHlSRWpBVHlTYjdtYOomczAbqjuwDBfiMsAQGrdoHnF4.spL2', 'Y', '3', 'N', 'N', 'Y'),
(14, 'i', 'i@mailinator.com', '$2y$08$QlRLTjFMNE80c3haQkhmKuq02fdmXoh14Cr.2Mh0p3wjoZONir.y6', 'Y', '3', 'N', 'N', 'Y'),
(15, 'j', 'j@mailinator.com', '$2y$08$Tm1YbXhIaUZCazBValExb.qCXTDMkotb1mDBLaha8ceLJpsELpM2.', 'Y', '3', 'N', 'N', 'Y'),
(16, 'k', 'k@mailinator.com', '$2y$08$SU1ZMWFlRmxwVTJ1MTFrOOBEAdz2KrGETKxDblXU.BniaqPwmdCDi', 'Y', '3', 'N', 'N', 'Y'),
(17, 'l', 'l@mailinator.com', '$2y$08$Z2x4a05McmNtVmdiU21qN.gn23x34ZJbi9igZwnOMLNfGg0ahp7Wi', 'Y', '3', 'N', 'N', 'Y'),
(18, 'm', 'm@mailinator.com', '$2y$08$eVl2MnZCL09vVElnSHdyTe6Qd.TYR4WmkrYOkh3CzNS6UzwkZJcA2', 'Y', '3', 'N', 'N', 'Y'),
(19, 'n', 'n@mailinator.com', '$2y$08$UFBFOWtVaFhXR0o3bGh1ROyg35nxpqSarbXrtEXwRoRjrDeDb3x3i', 'Y', '3', 'N', 'N', 'Y'),
(20, 'o', 'o@mailinator.com', '$2y$08$VW9Cd255UHhKQzNBSzZ6ZeQ5C5ntbsNEEgO5Rnum1/7ltx4zuFYgS', 'Y', '3', 'N', 'N', 'Y'),
(21, 'p', 'p@mailinator.com', '$2y$08$UUNtOE9ROXN1T0VicWd4Vu7DhQfFemkm9c/NhyS4Y8XJZjJ4ICoSa', 'Y', '3', 'N', 'N', 'Y'),
(22, 'Gordon', 'freeman@mailinator.com', '$2y$08$TEF6V2JML2xML3oyZHJUQukfsyS3OY2rpu.5VfAAaYunUJmpovYvW', 'N', '4', 'N', 'N', 'Y');