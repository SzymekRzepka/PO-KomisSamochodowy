-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Feb 04, 2024 at 09:54 PM
-- Wersja serwera: 10.4.28-MariaDB
-- Wersja PHP: 8.2.4

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `komis_samochodowy`
--

-- --------------------------------------------------------

--
-- Struktura tabeli dla tabeli `samochody`
--

CREATE TABLE `samochody` (
  `id` int(11) NOT NULL,
  `marka_samochodu` varchar(20) NOT NULL,
  `model_samochodu` varchar(30) NOT NULL,
  `rok_produkcji` int(4) NOT NULL,
  `pojemnosc_silnika` int(4) NOT NULL,
  `moc_silnika` int(4) NOT NULL,
  `cena` int(7) NOT NULL,
  `kolor` varchar(20) NOT NULL,
  `rodzaj_paliwa` varchar(10) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_polish_ci;

--
-- Dumping data for table `samochody`
--

INSERT INTO `samochody` (`id`, `marka_samochodu`, `model_samochodu`, `rok_produkcji`, `pojemnosc_silnika`, `moc_silnika`, `cena`, `kolor`, `rodzaj_paliwa`) VALUES
(1, 'BMW', 'E46', 2002, 2000, 150, 7500, 'czarny', 'diesel'),
(2, 'Audi', 'A3', 2010, 1900, 105, 15000, 'czerwony', 'diesel'),
(5, 'Opel', 'Astra', 2012, 1900, 130, 23000, 'biały', 'diesel'),
(9, 'Seat', 'Leon', 200, 1900, 120, 5000, 'niebieieski', 'diesel');

--
-- Indeksy dla zrzutów tabel
--

--
-- Indeksy dla tabeli `samochody`
--
ALTER TABLE `samochody`
  ADD PRIMARY KEY (`id`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `samochody`
--
ALTER TABLE `samochody`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=10;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
