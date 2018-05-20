DROP SCHEMA IF EXISTS `kung-fu`;

CREATE SCHEMA `kung-fu`;

use `kung-fu`;

DROP TABLE IF EXISTS `student`;

CREATE TABLE `student` (
  `stu_id` int(11) NOT NULL AUTO_INCREMENT,
  `stu_first_name` varchar(128) NOT NULL,
  `stu_last_name` varchar(45) NOT NULL,
  `stu_dob` datetime NOT NULL,
  `stu_join_date` datetime NOT NULL,
  `stu_mobile` varchar(12) NOT NULL,
  `stu_email` varchar(50) NOT NULL,
  `stu_door_no` int(5),
  `stu_street` varchar(45),
  `stu_city` varchar(45),
  `stu_province` varchar(45),
  `stu_postal_code` varchar(7),
  `stu_primary_contact` varchar(45),
  `stu_primary_contact_mobile` varchar(12),
  `stu_primary_contact_email` varchar(50),
  `stu_secondary_contact` varchar(45),
  `stu_secondary_contact_mobile` varchar(12),
  `stu_secondary_contact_email` varchar(50),
  PRIMARY KEY (`stu_id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=latin1;

DROP TABLE IF EXISTS `rank`;

CREATE TABLE `rank` (
  `rank_id` int(11) NOT NULL AUTO_INCREMENT,
  `belt` varchar(15) NOT NULL,
  PRIMARY KEY (`rank_id`)
) ENGINE=InnoDB  AUTO_INCREMENT=1 DEFAULT CHARSET=latin1;


DROP TABLE IF EXISTS `student_progress`;

CREATE TABLE `student_progress` (
  `stu_progress_id` int(11) NOT NULL AUTO_INCREMENT,
  `stu_id` int(11) NOT NULL,
  `stu_rank` int(11) NOT NULL,
  `stu_level` varchar(15) default 'Beginner',
  `date_awarded` datetime default NULL,
  PRIMARY KEY (`stu_progress_id`),
  FOREIGN KEY (`stu_id`) REFERENCES `student` (`stu_id`),
  FOREIGN KEY (`stu_rank`) REFERENCES `rank` (`rank_id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=latin1;

DROP TABLE IF EXISTS `service_catalogue`;

CREATE TABLE `service_catalogue` (
  `service_id` int(11) NOT NULL AUTO_INCREMENT,
  `category` varchar(128) NOT NULL,
  `sub_category` varchar(45) NOT NULL,
  `fees` int(7),
  `description` varchar(200) NOT NULL,
   PRIMARY KEY (`service_id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=latin1;


DROP TABLE IF EXISTS `account_summary`;

CREATE TABLE `account_summary` (
 # `acc_id` int(11) NOT NULL AUTO_INCREMENT,
  `account_id` int(11) NOT NULL AUTO_INCREMENT,
  `stu_id` int(11) NOT NULL,
  `service_id` int(11) NOT NULL,
  `date_paid` datetime NOT NULL,
  /*`category` varchar(128) NOT NULL,
  `sub_category` varchar(45) NOT NULL,
  `membership_type` varchar(128) NOT NULL,
  `test_level` varchar(128) NOT NULL,
  `products` varchar(45) NOT NULL,
   PRIMARY KEY (`acc_id`),*/
   PRIMARY KEY (`account_id`),
   FOREIGN KEY (`stu_id`) REFERENCES `student` (`stu_id`),
   FOREIGN KEY (`service_id`) REFERENCES `service_catalogue` (`service_id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=latin1;

/*CREATE TABLE `account_tracker` (
  `acc_id` int(11),
  `category` varchar(128) NOT NULL,
  `sub_category` varchar(45) NOT NULL,
  `amount_paid` int(7),
  `date_paid` datetime NOT NULL,
   PRIMARY KEY (`acc_id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=latin1;*/


DROP TABLE IF EXISTS `time_table`;

CREATE TABLE `time_table` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `level` varchar(128) NOT NULL,
  `rank` varchar(45) NOT NULL,
  `day` varchar(10),
  `time` varchar(20) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=latin1;


DROP TABLE IF EXISTS `student_attendance`;

CREATE TABLE `student_attendance` (
  `stu_attendance_id` int(11) NOT NULL AUTO_INCREMENT,
  `stu_id` int(11) NOT NULL,
  `time_table_id` int(11) NOT NULL,
  `date_attended` date NOT NULL,
  PRIMARY KEY (`stu_attendance_id`),
  FOREIGN KEY (`stu_id`) REFERENCES `student` (`stu_id`),
  FOREIGN KEY (`time_table_id`) REFERENCES `time_table` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=latin1;



insert into rank values
(1,'White'),
(2,'Yellow'),
(3,'Half Green'),
(4,'Green'),
(5,'Half Blue'),
(6,'Blue'),
(7,'Half Red'),
(8,'Red'),
(9,'Half Black'),
(10,'Black');

insert into service_catalogue (category, sub_category, fees, description) values
('Membership', 'Gold', 500, 'Gold Membership is $500'),
('Membership', 'Silver', 750, 'silver Membership is $750'),
('Membership', 'Platinum', 1000, 'Platinum Membership is $1000'),
('Test', 'white', 100, 'Tests White belt skills'),
('Test', 'Yellow', 120, 'Tests Yellow belt skills'),
('Test', 'Half Green', 140, 'Tests White belt skills'),
('Test', 'Green', 160, 'Tests White belt skills'),
('Test', 'Half Blue', 180, 'Tests White belt skills'),
('Test', 'Blue', 200, 'Tests White belt skills'),
('Test', 'Half Red', 220, 'Tests White belt skills'),
('Test', 'Red', 240, 'Tests White belt skills'),
('Test', 'Half Black', 260, 'Tests White belt skills'),
('Test', 'Black', 280, 'Tests White belt skills'),
('Merchandise', 'Uniform', 100, 'Uniform $100'),
('Merchandise', 'Gloves', 50, 'Hand Gloves costs $50'),
('Merchandise', 'Shoes', 100, 'Kung fu shoes costs $100');


Insert into time_table (level, rank, day, time) values 
('Beginner', 'White', 'Monday', '06:00-07:00'),
('Beginner', 'Yellow', 'Monday', '09:00-10:00'),
('Beginner', 'Half Green', 'Monday', '14:00-15:00'),
('Beginner', 'Green', 'Monday', '16:00-17:00'),
('Beginner', 'Half Blue', 'Monday', '19:00-20:00'),
('Beginner', 'Blue', 'Thursday', '06:00-07:00'),
('Beginner', 'Half Red', 'Thursday', '09:00-10:00'),
('Beginner', 'Red', 'Thursday', '14:00-15:00'),
('Beginner', 'Half Black', 'Thursday', '16:00-17:00'),
('Beginner', 'Black', 'Thursday', '19:00-20:00'),
('Intermediate', 'White', 'Tuesday', '06:00-07:00'),
('Intermediate', 'Yellow', 'Tuesday', '09:00-10:00'),
('Intermediate', 'Half Green', 'Tuesday', '14:00-15:00'),
('Intermediate', 'Green', 'Tuesday', '16:00-17:00'),
('Intermediate', 'Half Blue', 'Tuesday', '19:00-20:00'),
('Intermediate', 'Blue', 'Friday', '06:00-07:00'),
('Intermediate', 'Half Red', 'Friday', '09:00-10:00'),
('Intermediate', 'Red', 'Friday', '14:00-15:00'),
('Intermediate', 'Half Black', 'Friday', '16:00-17:00'),
('Intermediate', 'Black', 'Friday', '19:00-20:00'),
('Advance', 'White', 'Wednesday', '06:00-07:00'),
('Advance', 'Yellow', 'Wednesday', '09:00-10:00'),
('Advance', 'Half Green', 'Wednesday', '14:00-15:00'),
('Advance', 'Green', 'Wednesday', '16:00-17:00'),
('Advance', 'Half Blue', 'Wednesday', '19:00-20:00'),
('Advance', 'Blue', 'Saturday', '06:00-07:00'),
('Advance', 'Half Red', 'Saturday', '09:00-10:00'),
('Advance', 'Red', 'Saturday', '14:00-15:00'),
('Advance', 'Half Black', 'Saturday', '16:00-17:00'),
('Advance', 'Black', 'Saturday', '19:00-20:00');



