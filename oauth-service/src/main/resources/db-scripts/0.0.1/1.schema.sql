DROP TABLE IF EXISTS `clientDetails`^
CREATE TABLE `clientDetails` (
  `appId` varchar(256) NOT NULL,
  `resourceIds` varchar(256) DEFAULT NULL,
  `appSecret` varchar(256) DEFAULT NULL,
  `scope` varchar(256) DEFAULT NULL,
  `grantTypes` varchar(256) DEFAULT NULL,
  `redirectUrl` varchar(256) DEFAULT NULL,
  `authorities` varchar(256) DEFAULT NULL,
  `access_token_validity` int(11) DEFAULT NULL,
  `refresh_token_validity` int(11) DEFAULT NULL,
  `additionalInformation` varchar(4096) DEFAULT NULL,
  PRIMARY KEY (`appId`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1^

DROP TABLE IF EXISTS `authorities`^
CREATE TABLE `authorities` (
  `username` varchar(50) NOT NULL,
  `authority` varchar(50) NOT NULL,
  UNIQUE KEY `ix_auth_username` (`username`,`authority`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1^

DROP TABLE IF EXISTS `oauth_access_token`^
CREATE TABLE `oauth_access_token` (
  `token_id` varchar(256) DEFAULT NULL,
  `token` blob,
  `authentication_id` varchar(256) DEFAULT NULL,
  `user_name` varchar(256) DEFAULT NULL,
  `client_id` varchar(256) DEFAULT NULL,
  `authentication` blob,
  `refresh_token` varchar(256) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1^

DROP TABLE IF EXISTS `oauth_client_details`^
CREATE TABLE `oauth_client_details` (
  `client_id` varchar(256) NOT NULL,
  `resource_ids` varchar(256) DEFAULT NULL,
  `client_secret` varchar(256) DEFAULT NULL,
  `scope` varchar(256) DEFAULT NULL,
  `authorized_grant_types` varchar(256) DEFAULT NULL,
  `web_server_redirect_uri` varchar(256) DEFAULT NULL,
  `authorities` varchar(256) DEFAULT NULL,
  `access_token_validity` int(11) DEFAULT NULL,
  `refresh_token_validity` int(11) DEFAULT NULL,
  `additional_information` varchar(4096) DEFAULT NULL,
  `autoapprove` varchar(256) DEFAULT NULL,
  PRIMARY KEY (`client_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1^

DROP TABLE IF EXISTS `oauth_code`^
CREATE TABLE `oauth_code` (
  `code` varchar(256) DEFAULT NULL,
  `authentication` blob
) ENGINE=InnoDB DEFAULT CHARSET=latin1^

DROP TABLE IF EXISTS `oauth_refresh_token`^
CREATE TABLE `oauth_refresh_token` (
  `token_id` varchar(256) DEFAULT NULL,
  `token` blob,
  `authentication` blob
) ENGINE=InnoDB DEFAULT CHARSET=latin1^

DROP TABLE IF EXISTS `users`^
CREATE TABLE `users` (
  `username` varchar(50) NOT NULL,
  `password` varchar(150) NOT NULL,
  `enabled` tinyint(1) NOT NULL,
  `id` int(11) DEFAULT NULL,
  PRIMARY KEY (`username`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1^

DROP TABLE IF EXISTS `permissions`^
CREATE TABLE `permissions` (
  `permissionsId` int(11) not null AUTO_INCREMENT,
  `userIdFrom` int(11) NOT NULL,
  `userIdTo` int(11) NOT NULL,
  `createdDate` datetime NOT NULL,
  `deadline` datetime NOT NULL,
  `current` tinyint(1) NOT NULL,
  PRIMARY KEY (`permissionsId`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1^

DROP TABLE IF EXISTS `authority`^
CREATE TABLE `authority` (
  `name` varchar(50) NOT NULL,
  PRIMARY KEY (`name`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1^

DROP TABLE IF EXISTS `application_client`^
CREATE TABLE `application_client` (
  `client_id` varchar(255) NOT NULL,
  `name` varchar(150) NOT NULL,
  `client_secret` varchar(100) NOT NULL,
  `ui` bool NOT NULL,
  PRIMARY KEY (`client_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1^

DROP TABLE IF EXISTS `authority_client`^
CREATE TABLE `authority_client` (
  `authority_id` varchar(50) NOT NULL,
  PRIMARY KEY (`authority_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1^