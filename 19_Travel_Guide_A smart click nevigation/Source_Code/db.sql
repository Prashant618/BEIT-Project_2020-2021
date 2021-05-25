/*
SQLyog Community Edition- MySQL GUI v7.01 
MySQL - 5.0.27-community-nt : Database - 71transportapp
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;

CREATE DATABASE /*!32312 IF NOT EXISTS*/`71transportapp` /*!40100 DEFAULT CHARACTER SET latin1 */;

USE `71transportapp`;

/*Table structure for table `bookbuspayment` */

DROP TABLE IF EXISTS `bookbuspayment`;

CREATE TABLE `bookbuspayment` (
  `username` varchar(50) NOT NULL,
  `phone` varchar(50) NOT NULL,
  `busno` varchar(50) NOT NULL,
  `destination` varchar(50) NOT NULL,
  `time` varchar(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

/*Data for the table `bookbuspayment` */

insert  into `bookbuspayment`(`username`,`phone`,`busno`,`destination`,`time`) values ('a','3333','302','Thane','08:20 AM'),('a','3333','302','Thane','08:20 AM'),('yash','9930090883','387','Ghatkopar','08:20 AM'),('yash','9930090883','511','Thane','02:19 AM'),('hruta','9930090883','07','Dadar','05:00 AM'),('a','9833084113','303','Nahur','03:13 AM'),('neha','8532696565','302','Thane','08:20 AM'),('a','9833084113','302','Thane','08:20 AM');

/*Table structure for table `businfo` */

DROP TABLE IF EXISTS `businfo`;

CREATE TABLE `businfo` (
  `id` int(11) NOT NULL auto_increment,
  `busnumber` varchar(50) default NULL,
  `BusplateNumber` varchar(50) NOT NULL,
  `Source` varchar(50) default NULL,
  `Destination` varchar(50) default NULL,
  `UpTiming` varchar(500) default NULL,
  `stops` varchar(1000) default NULL,
  `DownTiming` varchar(500) default NULL,
  PRIMARY KEY  (`id`,`BusplateNumber`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

/*Data for the table `businfo` */

insert  into `businfo`(`id`,`busnumber`,`BusplateNumber`,`Source`,`Destination`,`UpTiming`,`stops`,`DownTiming`) values (1,'302','mh0302','Vikhroli','Thane','08:20 AM','Bhandup --> Mulund','02:15 AM'),(8,'387','mh0387','Vikhroli','Ghatkopar','08:20 AM','No stop','09:00 AM'),(9,'511','mh0511','Dadar','Thane','02:19 AM','Kurla --> Ghatkopar','03:05 AM'),(11,'27','mh0027','Mulund','Sion','08:20 AM','Vikhroli --> Vidyavihar','01:10 PM'),(12,'545','mh0545','Kanjurmarg','Thane','08:09 AM','Bhandup --> Mulund','09:10 AM'),(13,'525','mh0525','Kanjurmarg','Mulund','02:15 PM','Bhandup --> Nahur','02:55 PM'),(15,'07','mh0007','Vikhroli','Dadar','05:00 AM','Ghatkopar --> Sion','06:36 AM'),(16,'489','mh0489','Dadar','Vikhroli','03:29 AM','Kurla --> Sion','04:30 AM'),(17,'303','mh0303','Vidyavihar','Nahur','03:13 AM','Vikhroli -->  Bandup','04:18 AM'),(18,'512','mh0512','Nahur','Ghatkopar','02:20 AM','Kanjurmarg --> Vikhroli','01:10 AM'),(20,'513','mh0513','Nahur','Dadar','02:15 AM','Vikhroli --> Kurla','03:20 AM');

/*Table structure for table `buslocation` */

DROP TABLE IF EXISTS `buslocation`;

CREATE TABLE `buslocation` (
  `id` int(11) NOT NULL auto_increment,
  `lattitude` varchar(15) default NULL,
  `longitude` varchar(15) default NULL,
  `busnumber` varchar(10) default NULL,
  PRIMARY KEY  (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

/*Data for the table `buslocation` */

/*Table structure for table `busregistertable` */

DROP TABLE IF EXISTS `busregistertable`;

CREATE TABLE `busregistertable` (
  `busno` varchar(50) NOT NULL default '',
  `busplateno` varchar(50) NOT NULL default '',
  `email` varchar(50) NOT NULL default '',
  `phone` varchar(50) NOT NULL,
  `passward` varchar(50) NOT NULL default '',
  PRIMARY KEY  (`busplateno`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

/*Data for the table `busregistertable` */

insert  into `busregistertable`(`busno`,`busplateno`,`email`,`phone`,`passward`) values ('07','mh0007','bus@07','748512345','123456'),('27','mh0027','bus@27','8574125369','1234'),('302','mh0302','bus@302','8547125489','1234'),('380','mh0380','bus380','9930090883','1234'),('386','mh0386','bus@386','3698574125','1234'),('387','mh0387','bus@12','8585858585','123456'),('489','mh0489','bus@489','8596231457','1234'),('511','mh0511','bus@1234','7896523145','123456'),('513','mh0513','bus@513','9658454521','1234'),('525','mh0525','bus@525','8745896325','1234'),('545','mh0545','bus@545','8745123698','123456');

/*Table structure for table `locationupdater` */

DROP TABLE IF EXISTS `locationupdater`;

CREATE TABLE `locationupdater` (
  `id` int(11) NOT NULL auto_increment,
  `name` varchar(1000) default NULL,
  `Date` varchar(1000) NOT NULL,
  `longitude` varchar(1000) default NULL,
  `latitude` varchar(1000) default NULL,
  `location` varchar(1000) default NULL,
  `time` varchar(1000) default NULL,
  `busplatenumber` varchar(100) NOT NULL,
  `busnumber` varchar(100) default NULL,
  PRIMARY KEY  (`id`,`busplatenumber`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

/*Data for the table `locationupdater` */

insert  into `locationupdater`(`id`,`name`,`Date`,`longitude`,`latitude`,`location`,`time`,`busplatenumber`,`busnumber`) values (4,'302','3/11/2021','72.9152301','19.079153','mum','01:14 AM','mh0250','302'),(7,'303','3/16/2021','72.908012','19.079023','vvv','01:30 AM','mh0201','303'),(8,'387','3/19/2021','72.910133','19.099760','thane','02:20 AM','mh0387','387'),(10,'511','3/27/2021','-122.08400000000007','37.421998333333342','Dadar','03:20 AM','mh0511','511'),(11,'27','3/31/2021','-122.09400000','37.411998333','Mulund','07:24 AM','mh0027','27'),(12,'545','3/18/2021','72.9152201','19.1049692','Kanjurmarg','02:10 AM','mh0545','545'),(14,'525','3/31/2021','-122.08400000000002','37.421998333333335','Kanjurmarg','07:00 AM','mh0525','525'),(15,'07','3/29/2021','-122.08400000000002','37.421998333333335','Vikhroli','06:25 AM','mh0007','07'),(16,'489','3/26/2021','-122.08400000000002','37.421998333333335','Dadar','06:14 AM','mh0489','489'),(17,'513','4/9/2021','-122.08400000000002','37.421998333333335','USA','02:15 AM','mh0513','513');

/*Table structure for table `montlypass` */

DROP TABLE IF EXISTS `montlypass`;

CREATE TABLE `montlypass` (
  `username` varchar(50) NOT NULL,
  `busno` varchar(50) NOT NULL,
  `busplate` varchar(50) NOT NULL,
  `source` varchar(50) NOT NULL,
  `destination` varchar(50) NOT NULL,
  `from` varchar(50) NOT NULL,
  `to` varchar(50) NOT NULL,
  PRIMARY KEY  (`username`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

/*Data for the table `montlypass` */

insert  into `montlypass`(`username`,`busno`,`busplate`,`source`,`destination`,`from`,`to`) values ('a','387','mh0387','Thane','Vikhroli','4/14/2021','5/13/2021'),('yash','303','mh303','Mulund','Ghatkopar','4/29/2021','4/22/2021');

/*Table structure for table `userregistertable` */

DROP TABLE IF EXISTS `userregistertable`;

CREATE TABLE `userregistertable` (
  `username` varchar(50) NOT NULL default '',
  `passward` varchar(50) NOT NULL,
  `email` varchar(50) NOT NULL,
  `phone` varchar(50) NOT NULL,
  `address` varchar(50) NOT NULL,
  PRIMARY KEY  (`username`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

/*Data for the table `userregistertable` */

insert  into `userregistertable`(`username`,`passward`,`email`,`phone`,`address`) values ('a','a','a','9833084113','a'),('aaaaaa','aaaaaa','aaaaaaaa','8879343922','jsjsjjsssd'),('d','yashsalvi','a','1','a'),('dipesh','dipesh','dipesh@12','8569658745','varsha nagar'),('hruta','hruta','hruta@12','9930090883','dadar'),('neha','neha@12','neha@12','8532696565','iit market'),('yash','hruta1','yashs@','9930090883','hdhdhdhd');

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
