CREATE DATABASE `simpleblog` /*!40100 DEFAULT CHARACTER SET utf8 */;

DROP TABLE IF EXISTS `simpleblog`.`article`;
CREATE TABLE  `simpleblog`.`article` (
  `asDraft` tinyint(1) NOT NULL,
  `title` varchar(200) NOT NULL,
  `content` text NOT NULL,
  `postDate` datetime NOT NULL,
  `userID` varchar(45) NOT NULL,
  `staticLinkURL` varchar(45) DEFAULT NULL,
  `setTop` tinyint(1) DEFAULT '0',
  `articleID` varchar(45) NOT NULL,
  PRIMARY KEY (`articleID`),
  KEY `FK_article_1` (`userID`),
  CONSTRAINT `FK_article_1` FOREIGN KEY (`userID`) REFERENCES `user` (`userID`) ON DELETE NO ACTION ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

DROP TABLE IF EXISTS `simpleblog`.`category`;
CREATE TABLE  `simpleblog`.`category` (
  `categoryName` varchar(45) NOT NULL,
  `categoryComments` varchar(45) DEFAULT NULL,
  `relatedPosts` int(10) unsigned NOT NULL DEFAULT '1',
  PRIMARY KEY (`categoryName`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

DROP TABLE IF EXISTS `simpleblog`.`comments`;
CREATE TABLE  `simpleblog`.`comments` (
  `title` varchar(45) NOT NULL,
  `userName` varchar(45) NOT NULL,
  `homepageURL` varchar(45) DEFAULT NULL,
  `userEmail` varchar(45) NOT NULL,
  `content` varchar(45) NOT NULL COMMENT 'the content of comments',
  `postDate` datetime NOT NULL,
  `commentsID` varchar(45) NOT NULL,
  `theCommentsArticleID` varchar(45) NOT NULL,
  PRIMARY KEY (`commentsID`),
  KEY `FK_comments_1` (`theCommentsArticleID`),
  CONSTRAINT `FK_comments_1` FOREIGN KEY (`theCommentsArticleID`) REFERENCES `article` (`articleID`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

DROP TABLE IF EXISTS `simpleblog`.`tag`;
CREATE TABLE  `simpleblog`.`tag` (
  `tagName` varchar(45) NOT NULL,
  `relatedPosts` int(10) unsigned NOT NULL DEFAULT '1',
  PRIMARY KEY (`tagName`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

DROP TABLE IF EXISTS `simpleblog`.`user`;
CREATE TABLE  `simpleblog`.`user` (
  `userID` varchar(45) NOT NULL,
  `password` varchar(45) NOT NULL,
  `email` varchar(45) NOT NULL,
  `nickName` varchar(45) NOT NULL,
  PRIMARY KEY (`userID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

DROP TABLE IF EXISTS `simpleblog`.`article_category`;
CREATE TABLE  `simpleblog`.`article_category` (
  `articleID` varchar(45) NOT NULL,
  `categoryName` varchar(45) NOT NULL,
  KEY `FK_article_category_1` (`articleID`),
  KEY `FK_article_category_2` (`categoryName`),
  CONSTRAINT `FK_article_category_2` FOREIGN KEY (`categoryName`) REFERENCES `category` (`categoryName`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `FK_article_category_1` FOREIGN KEY (`articleID`) REFERENCES `article` (`articleID`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

DROP TABLE IF EXISTS `simpleblog`.`article_tag`;
CREATE TABLE  `simpleblog`.`article_tag` (
  `articleID` varchar(45) NOT NULL,
  `tagName` varchar(45) NOT NULL,
  KEY `FK_article_tag_1` (`articleID`),
  KEY `FK_article_tag_2` (`tagName`),
  CONSTRAINT `FK_article_tag_2` FOREIGN KEY (`tagName`) REFERENCES `tag` (`tagName`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `FK_article_tag_1` FOREIGN KEY (`articleID`) REFERENCES `article` (`articleID`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;