CREATE SCHEMA IF NOT EXISTS `bookshop`;

CREATE TABLE `role` (
  `id` int(10)  NOT NULL,
  `name` varchar(45) NOT NULL,
  PRIMARY KEY (`id`)
);

CREATE TABLE `status` (
  `id` int(10)  NOT NULL,
  `name` varchar(45) NOT NULL,
  PRIMARY KEY (`id`),
);

CREATE TABLE `user` (
  `id` int(10) NOT NULL AUTO_INCREMENT,
  `email` varchar(225) NOT NULL,
  `password` varchar(45) NOT NULL,
  `full_name` varchar(100) NOT NULL,
  `birth_date` date DEFAULT NULL,
  `phone_number` varchar(15) NOT NULL,
  `address` varchar(45) DEFAULT NULL,
  `id_role` int(10) NOT NULL DEFAULT '3',
  PRIMARY KEY (`id`),
  CONSTRAINT `role`
  FOREIGN KEY (`id_role`)
  REFERENCES `role` (`id`)
  ON UPDATE NO ACTION
);
CREATE INDEX `role_idx` ON `user`(`id_role`);

CREATE TABLE `item` (
  `id` int(10) NOT NULL AUTO_INCREMENT,
  `ISBN` varchar(45) NOT NULL,
  `name` varchar(45) NOT NULL,
  `author` varchar(45) NOT NULL,
  `publisher` varchar(45) NOT NULL,
  `year` date NOT NULL,
  `pages` int(11) NOT NULL,
  `count` int(10) DEFAULT NULL,
  `price` float NOT NULL DEFAULT '0',
  PRIMARY KEY (`id`,`ISBN`),
);

CREATE TABLE `mail_template` (
  `email_type` varchar(50) NOT NULL,
  `email_subject` varchar(225) DEFAULT NULL,
  `email_text` text NOT NULL,
  PRIMARY KEY (`email_type`)
);

CREATE TABLE `order` (
  `id` int(10) NOT NULL AUTO_INCREMENT,
  `id_user` int(10) NOT NULL,
  `id_status` int(11) NOT NULL DEFAULT '1',
  `date` datetime NOT NULL,
  PRIMARY KEY (`id`),
  CONSTRAINT `user`
  FOREIGN KEY (`id_user`)
  REFERENCES `user` (`id`)
  ON DELETE CASCADE
  ON UPDATE NO ACTION);
CREATE INDEX `user_idx` ON `order`(`id_user`);

CREATE TABLE `order_item` (
  `id` int(10)  NOT NULL,
  `item_id` int(10)  NOT NULL,
  `order_id` int(10)  NOT NULL,
  `quantity` int(10) NOT NULL,
  PRIMARY KEY (`id`),
  CONSTRAINT `item_order_fk`
  FOREIGN KEY (`item_id`)
  REFERENCES `item` (`id`)
  ON DELETE NO ACTION
  ON UPDATE NO ACTION,
  CONSTRAINT `order_item_fk`
  FOREIGN KEY (`order_id`)
  REFERENCES `order` (`id`)
  ON DELETE NO ACTION
  ON UPDATE NO ACTION);
CREATE INDEX `order_item_fk_idx` ON `order_item`(`order_id`);
CREATE INDEX `item_order_fk_idx` ON `order_item`(`order_id`);


