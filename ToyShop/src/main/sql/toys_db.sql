CREATE SCHEMA IF NOT EXISTS `toys_db`;
--
-- Table structure for table `optiongroups`
--

CREATE TABLE IF NOT EXISTS `optiongroups` (
  `OptionGroupID` int(11) NOT NULL AUTO_INCREMENT,
  `OptionGroupName` varchar(50)  DEFAULT NULL,
  PRIMARY KEY (`OptionGroupID`)
) ENGINE=MyISAM ;

--
-- Dumping data for table `optiongroups`
--

INSERT INTO `optiongroups` (`OptionGroupID`, `OptionGroupName`) VALUES
(1, 'color'),
(2, 'size');

-- --------------------------------------------------------

--
-- Table structure for table `options`
--

CREATE TABLE IF NOT EXISTS `options` (
  `OptionID` int(11) NOT NULL AUTO_INCREMENT,
  `OptionGroupID` int(11) DEFAULT NULL,
  `OptionName` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`OptionID`)
) ENGINE=MyISAM;

--
-- Dumping data for table `options`
--

INSERT INTO `options` (`OptionID`, `OptionGroupID`, `OptionName`) VALUES
(1, 1, 'red'),
(2, 1, 'blue'),
(3, 1, 'green'),
(4, 1, 'yellow'),
(5, 2, 'S'),
(6, 2, 'M'),
(7, 2, 'L'),
(8, 2, 'XL'),
(9, 2, 'XXL'),
(10, 1, 'brown'),
(11, 1, 'varicolored');

-- --------------------------------------------------------

--
-- Table structure for table `orderstatuses`
--

CREATE TABLE `toys_db`.`orderstatuses` (
  `StatusID` INT NOT NULL,
  `StatusName` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`StatusID`)
) ENGINE=MyISAM;

-- --------------------------------------------------------

--
-- Dumping data for table `orderstatuses`
--

INSERT INTO `toys_db`.`orderstatuses` (`StatusID`, `StatusName`) VALUES
(1, 'ACCEPTED'),
(2, 'CONFIRMED'),
(3, 'IN_PROGRESS'),
(4, 'SENT'),
(5, 'COMPLETED'),
(6, 'CANCELED');

-- --------------------------------------------------------

--
-- Table structure for table `ordershiptypes`
--
CREATE TABLE `toys_db`.`ordershiptypes` (
  `OrderShipTypeID` INT NOT NULL,
  `OrderShipTypeName` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`OrderShipTypeID`)
)ENGINE=MyISAM;

--
-- Dumping data for table `ordershiptypes`
--
INSERT INTO `toys_db`.`ordershiptypes` (`OrderShipTypeID`, `OrderShipTypeName`) VALUES
(1, 'SELF_PICKUP'),
(2, 'MAIL'),
(3, 'DELIVERY');
--
-- Table structure for table `orderdetails`
--

CREATE TABLE IF NOT EXISTS `orderdetails` (
  `DetailID` int(11) NOT NULL AUTO_INCREMENT,
  `DetailOrderID` int(11) NOT NULL,
  `DetailProductID` int(11) NOT NULL,
  `DetailName` varchar(250) DEFAULT NULL,
  `DetailPrice` float NOT NULL,
  `DetailQuantity` int(11) NOT NULL,
  PRIMARY KEY (`DetailID`)
) ENGINE=MyISAM;

-- --------------------------------------------------------

--
-- Table structure for table `orders`
--

CREATE TABLE IF NOT EXISTS `orders` (
  `OrderID` int(11) NOT NULL AUTO_INCREMENT,
  `OrderUserID` int(11) NOT NULL,
  `OrderAmount` float NOT NULL,
  `OrderShipAddress` varchar(100) NOT NULL,
  `OrderDate` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `OrderStatusID` INT(11) NOT NULL DEFAULT '1',
  `OrderShipTypeID` INT(11) NOT NULL DEFAULT '1',
  `OrderStateDetails` VARCHAR(45) DEFAULT NULL,
  PRIMARY KEY (`OrderID`)
) ENGINE=MyISAM;

-- --------------------------------------------------------

--
-- Table structure for table `productcategories`
--

CREATE TABLE IF NOT EXISTS `productcategories` (
  `CategoryID` int(11) NOT NULL AUTO_INCREMENT,
  `CategoryName` varchar(50) NOT NULL,
  PRIMARY KEY (`CategoryID`)
) ENGINE=MyISAM;

--
-- Dumping data for table `productcategories`
--

INSERT INTO `productcategories` (`CategoryID`, `CategoryName`) VALUES
(1, 'Constructors'),
(2, 'Boardgames'),
(3, 'Dolls'),
(4, 'Plush'),
(5, 'Models'),
(6, 'Other');

-- --------------------------------------------------------

--
-- Table structure for table `brands`
--
CREATE TABLE IF NOT EXISTS `brands` (
  `BrandID` int(11) NOT NULL AUTO_INCREMENT,
  `BrandName` varchar(50) NOT NULL,
  PRIMARY KEY (`BrandID`)
) ENGINE=MyISAM;

--
-- Dumping data for table `brands`
--

INSERT INTO `brands` (`BrandID`, `BrandName`) VALUES
  (1, 'Lego'),
  (2, 'Hasbro'),
  (3, 'Zoch'),
  (4, 'Button Blue');

-- --------------------------------------------------------

--
-- Table structure for table `productoptions`
--

CREATE TABLE IF NOT EXISTS `productoptions` (
  `ProductOptionID` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `ProductID` int(10) unsigned NOT NULL,
  `OptionID` int(10) unsigned NOT NULL,
  `OptionGroupID` int(11) NOT NULL,
  PRIMARY KEY (`ProductOptionID`)
) ENGINE=MyISAM;

--
-- Dumping data for table `productoptions`
--

INSERT INTO `productoptions` (`ProductID`, `OptionID`, `OptionGroupID`) VALUES
--
-- duck
--
(1, 4, 1),
(1, 5, 2),
--
-- bear
--
(2, 10, 1),
(2, 6, 2),
--
-- plain
--
(3, 2, 1),
(3, 6, 2),
--
-- lego death star
--
(4, 11, 1),
(4, 9, 2),
--
-- lego millennium falcon
--
(5, 11, 1),
(5, 9, 2),
--
-- monopoly
--
(6, 11, 1),
(6, 8, 2),
--
-- munchkin classic
--
(7, 10, 1),
(7, 8, 2),
--
-- rainbow spring
--
(8, 11, 1),
(8, 5, 2),
--
-- skipjack
--
(9, 11, 1),
(9, 5, 2),
--
-- yo-yo
--
(10, 1, 1),
(10, 5, 2);

-- --------------------------------------------------------

--
-- Table structure for table `products`
--

CREATE TABLE IF NOT EXISTS `products` (
  `ProductID` int(12) NOT NULL AUTO_INCREMENT,
  `ProductName` varchar(100) NOT NULL,
  `ProductPrice` float NOT NULL,
  `ProductWeight` float NOT NULL,
  `ProductImage` varchar(100) NOT NULL,
  `ProductCategoryID` int(11) NOT NULL,
  `ProductBrandID` int(11) NOT NULL,
  `ProductUpdateDate` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `ProductLive` tinyint(1) DEFAULT '0',
  PRIMARY KEY (`ProductID`)
) ENGINE=MyISAM;

--
-- Dumping data for table `products`
--

INSERT INTO `products` (`ProductID`, `ProductName`, `ProductPrice`, `ProductWeight`,
                        `ProductImage`, `ProductCategoryID`, `ProductBrandID`, `ProductLive`) VALUES
 (1, 'rubber duck', 56.0, 0.1, '/images/home/duck.png', 6, 3, 1),
 (2, 'bear', 56.0, 0.2, '/images/home/bear1.png', 4, 4, 1),
 (3, 'plain', 56.0, 0.5, '/images/home/plain.jpg', 5, 2, 1),
 (4, 'lego death star', 350.00, 1.0, '/images/home/lego_death_star.jpg', 1, 1, 1),
 (5, 'lego millennium falcon', 300.00, 1.0, '/images/home/lego_millennium_falcon.jpg', 1, 1, 1),
 (6, 'monopoly', 200.00, 0.5, '/images/home/monopoly.jpg', 2, 2, 1),
 (7, 'munchkin classic', 200.00, 0.5, '/images/home/munchkin_classic.jpg', 2, 3, 1),
 (8, 'rainbow spring', 4.99, 0.1, '/images/home/rainbow_spring.jpg', 6, 2, 1),
 (9, 'skipjack', 4.99, 0.1, '/images/home/skipjack.jpg', 6, 4, 1),
 (10, 'yo-yo', 4.99, 0.1, '/images/home/yo-yo.jpg', 6, 4, 1);
-- --------------------------------------------------------

--
-- Table structure for table `roles`
--
CREATE TABLE IF NOT EXISTS `roles` (
  RoleID INT PRIMARY KEY NOT NULL AUTO_INCREMENT,
  RoleName VARCHAR(45) DEFAULT 'USER' NOT NULL
)ENGINE=MyISAM;

--
-- Dumping data for table `roles`
--
INSERT INTO `toys_db`.`roles` (`RoleID`, `RoleName`) VALUES
  (1, 'user'),
  (2, 'admin');

--
-- Table structure for table `users`
--

CREATE TABLE IF NOT EXISTS `users` (
  `UserID` int(11) NOT NULL AUTO_INCREMENT,
  `UserEmail` varchar(100) NOT NULL UNIQUE,
  `UserPassword` varchar(100) DEFAULT NULL,
  `UserFirstName` varchar(50) DEFAULT NULL,
  `UserLastName` varchar(50) DEFAULT NULL,
  `UserRoleID` INT DEFAULT 1,

  PRIMARY KEY (`UserID`)
) ENGINE=MyISAM;




