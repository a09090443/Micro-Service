LOCK TABLES `user_title` WRITE;
/*!40000 ALTER TABLE `user_title` DISABLE KEYS */;
INSERT INTO `user_title` VALUES ('000001','01'),('000001','02'),('000002','02');
/*!40000 ALTER TABLE `user_title` ENABLE KEYS */;
UNLOCK TABLES;

INSERT INTO sys_user_authority_mapping
(user_id, authority_id)
VALUES('000001', '01');
INSERT INTO sys_user_authority_mapping
(user_id, authority_id)
VALUES('000001', '02');
INSERT INTO sys_user_authority_mapping
(user_id, authority_id)
VALUES('000003', '01');


INSERT INTO sys_user_title
(title_id, title_name)
VALUES('01', 'ADMIN');
INSERT INTO sys_user_title
(title_id, title_name)
VALUES('02', 'MANAGER');

INSERT INTO sys_user_title_mapping
(user_id, title_id)
VALUES('000001', '01');
INSERT INTO sys_user_title_mapping
(user_id, title_id)
VALUES('000001', '02');
INSERT INTO sys_user_title_mapping
(user_id, title_id)
VALUES('000002', '02');
INSERT INTO sys_user_title_mapping
(user_id, title_id)
VALUES('000003', '01');

