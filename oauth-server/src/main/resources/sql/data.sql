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

INSERT INTO sys_menu
(menu_id, menu_name, `path`, comment, order_id, enabled, parent_id)
VALUES(1, '3C', '', '3C', 1, 1, 0);
INSERT INTO sys_menu
(menu_id, menu_name, `path`, comment, order_id, enabled, parent_id)
VALUES(2, 'APPLE', '', 'APPLE', 2, 1, 1);
INSERT INTO sys_menu
(menu_id, menu_name, `path`, comment, order_id, enabled, parent_id)
VALUES(3, 'HTC', '', 'HTC', 1, 1, 1);
INSERT INTO sys_menu
(menu_id, menu_name, `path`, comment, order_id, enabled, parent_id)
VALUES(4, 'IPOD', '', 'IPOD', 1, 1, 2);
INSERT INTO sys_menu
(menu_id, menu_name, `path`, comment, order_id, enabled, parent_id)
VALUES(5, 'Electronic', '', 'Electronic', 2, 1, 0);
INSERT INTO sys_menu
(menu_id, menu_name, `path`, comment, order_id, enabled, parent_id)
VALUES(6, 'VR', 'test1-2-1', 'VR', 2, 1, 3);
INSERT INTO sys_menu
(menu_id, menu_name, `path`, comment, order_id, enabled, parent_id)
VALUES(7, 'Sound', 'test2-1', 'Sound', 1, 1, 5);
INSERT INTO sys_menu
(menu_id, menu_name, `path`, comment, order_id, enabled, parent_id)
VALUES(8, 'Furniture', '', 'Furniture', 3, 1, 0);
INSERT INTO sys_menu
(menu_id, menu_name, `path`, comment, order_id, enabled, parent_id)
VALUES(9, 'TV', 'test2-2', 'TV', 2, 1, 5);
INSERT INTO sys_menu
(menu_id, menu_name, `path`, comment, order_id, enabled, parent_id)
VALUES(10, 'Lamp', 'test2-3', 'Lamp', 3, 1, 5);
INSERT INTO sys_menu
(menu_id, menu_name, `path`, comment, order_id, enabled, parent_id)
VALUES(11, 'HP', 'test1-3', 'HP', 3, 1, 1);
INSERT INTO sys_menu
(menu_id, menu_name, `path`, comment, order_id, enabled, parent_id)
VALUES(12, 'Transportation', 'test4', 'Transportation', 4, 1, 0);
INSERT INTO sys_menu
(menu_id, menu_name, `path`, comment, order_id, enabled, parent_id)
VALUES(13, 'Sofa', 'test3-1', 'Sofa', 2, 1, 8);
INSERT INTO sys_menu
(menu_id, menu_name, `path`, comment, order_id, enabled, parent_id)
VALUES(14, 'Table', 'test3-2', 'Table', 1, 1, 8);
INSERT INTO sys_menu
(menu_id, menu_name, `path`, comment, order_id, enabled, parent_id)
VALUES(15, 'Mobile phone', 'test1-2-2', 'Mobile phone', 1, 1, 3);

INSERT INTO oauth_client_details
(client_id, resource_ids, client_secret, `scope`, authorized_grant_types, web_server_redirect_uri, authorities, access_token_validity, refresh_token_validity, additional_information, autoapprove)
VALUES('api_service', 'oauth2-resource', 'secret', 'read,write', 'client_credentials', NULL, NULL, NULL, NULL, NULL, 'true');
INSERT INTO oauth_client_details
(client_id, resource_ids, client_secret, `scope`, authorized_grant_types, web_server_redirect_uri, authorities, access_token_validity, refresh_token_validity, additional_information, autoapprove)
VALUES('auth_test', 'oauth2-resource', 'secret', 'any,read,write', 'authorization_code,refresh_token', NULL, NULL, NULL, NULL, NULL, 'true');
INSERT INTO oauth_client_details
(client_id, resource_ids, client_secret, `scope`, authorized_grant_types, web_server_redirect_uri, authorities, access_token_validity, refresh_token_validity, additional_information, autoapprove)
VALUES('client_test', 'oauth2-resource', 'secret', 'read,write', 'client_credentials', NULL, NULL, NULL, NULL, NULL, 'true');
INSERT INTO oauth_client_details
(client_id, resource_ids, client_secret, `scope`, authorized_grant_types, web_server_redirect_uri, authorities, access_token_validity, refresh_token_validity, additional_information, autoapprove)
VALUES('feign_service', 'oauth2-resource', 'secret', 'read,write', 'authorization_code,implicit', NULL, NULL, NULL, NULL, NULL, 'true');
INSERT INTO oauth_client_details
(client_id, resource_ids, client_secret, `scope`, authorized_grant_types, web_server_redirect_uri, authorities, access_token_validity, refresh_token_validity, additional_information, autoapprove)
VALUES('hystrix', 'oauth2-resource', 'secret', 'read,write', 'authorization_code,implicit', NULL, NULL, NULL, NULL, NULL, 'true');
INSERT INTO oauth_client_details
(client_id, resource_ids, client_secret, `scope`, authorized_grant_types, web_server_redirect_uri, authorities, access_token_validity, refresh_token_validity, additional_information, autoapprove)
VALUES('implicit_test', 'oauth2-resource', 'secret', 'read,write', 'implicit', NULL, NULL, NULL, NULL, NULL, 'true');
INSERT INTO oauth_client_details
(client_id, resource_ids, client_secret, `scope`, authorized_grant_types, web_server_redirect_uri, authorities, access_token_validity, refresh_token_validity, additional_information, autoapprove)
VALUES('loadbalance', 'oauth2-resource', 'secret', 'read,write', 'authorization_code,implicit', NULL, NULL, NULL, NULL, NULL, 'true');
INSERT INTO oauth_client_details
(client_id, resource_ids, client_secret, `scope`, authorized_grant_types, web_server_redirect_uri, authorities, access_token_validity, refresh_token_validity, additional_information, autoapprove)
VALUES('password_test', 'oauth2-resource', 'secret', 'read,write', 'password,refresh_token', NULL, NULL, NULL, NULL, NULL, 'true');
INSERT INTO oauth_client_details
(client_id, resource_ids, client_secret, `scope`, authorized_grant_types, web_server_redirect_uri, authorities, access_token_validity, refresh_token_validity, additional_information, autoapprove)
VALUES('sso_service', 'oauth2-resource', 'secret', 'read,write', 'authorization_code,implicit', NULL, NULL, NULL, NULL, NULL, 'true');
INSERT INTO oauth_client_details
(client_id, resource_ids, client_secret, `scope`, authorized_grant_types, web_server_redirect_uri, authorities, access_token_validity, refresh_token_validity, additional_information, autoapprove)
VALUES('zuul_service', 'oauth2-resource', 'secret', 'read,write', 'authorization_code,implicit', NULL, NULL, NULL, NULL, NULL, 'true');
