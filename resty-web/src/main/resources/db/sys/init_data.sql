INSERT INTO sys_user
(id, uuid, nickname, mobile, account, email, password, salt, company_uuid, account_type, user_pt, head_url, super_uuid, sex, is_auth, card_number, role_uuid, create_uuid, update_uuid, disable_uuid, del_uuid, create_at, update_at, disable_at, del_at, sql_status, state, channel_id)
VALUES(1, 'admin_uuid', '系统管理员', '18912103568', 'admin', 'sun1020@163.com', '371f4c8e8ed16ca4b31ae767d9c7746bdcd4e79a111c88108dba6c9c6d1287c42017045ac2b81010c1edbd9f33f1781d0ab5a15677fd065e5f81cfff9270b0ac', 'sunzc', '1', 1, 1, NULL, NULL, 1, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 1, 1, '1');



INSERT INTO sys_menu
(id, uuid, parent_id, parent_ids,level, name, sort, href, target, icon, is_show, permission, remarks, menu_type, api_url,  create_uuid, update_uuid, disable_uuid, del_uuid, create_at, update_at, disable_at, del_at, sql_status, state, channel_id, admin_show)
VALUES(1, '4b81a47b-36b2-4344-9c32-52ce435c24d1', NULL, '',0, '运营平台', 1, '', NULL, NULL, 1, '', NULL, 1, NULL,  'admin_uuid', 'admin_uuid', NULL, NULL, 1533023232106, 1533023232106, NULL, NULL, 1, 1, '1', 1);
INSERT INTO sys_menu
(id, uuid, parent_id, parent_ids,level, name, sort, href, target, icon, is_show, permission, remarks, menu_type, api_url,  create_uuid, update_uuid, disable_uuid, del_uuid, create_at, update_at, disable_at, del_at, sql_status, state, channel_id, admin_show)
VALUES(2, '03a473b6-2178-46ed-966c-6e25d1e3612e', NULL, '',0, '商户平台', 2, '', NULL, NULL, 1, '', NULL, 1, NULL,  'admin_uuid', 'admin_uuid', NULL, NULL, 1533023232106, 1533023232106, NULL, NULL, 1, 1, '1', 1);
INSERT INTO sys_menu
(id, uuid, parent_id, parent_ids,level, name, sort, href, target, icon, is_show, permission, remarks, menu_type, api_url,  create_uuid, update_uuid, disable_uuid, del_uuid, create_at, update_at, disable_at, del_at, sql_status, state, channel_id, admin_show)
VALUES(3, '15160bb6-cbff-4488-80e0-ce02fdbf3291', NULL, '',0, 'APP端', 3, '', NULL, NULL, 1, '', NULL, 2, '',  'admin_uuid', 'admin_uuid', NULL, NULL, 1533104040032, 1533107457939, NULL, NULL, 1, 1, '1', 1);
INSERT INTO sys_menu
(id, uuid, parent_id, parent_ids,level, name, sort, href, target, icon, is_show, permission, remarks, menu_type, api_url,  create_uuid, update_uuid, disable_uuid, del_uuid, create_at, update_at, disable_at, del_at, sql_status, state, channel_id, admin_show)
VALUES(4, '4cda05a3-7385-4d20-a6a9-e4202483f5c0', NULL, '',0, '微信端', 4, '', NULL, NULL, 1, '', NULL, 3, '',  'admin_uuid', 'admin_uuid', NULL, NULL, 1533107475333, 1541121746744, NULL, NULL, 1, 1, '1', 1);
INSERT INTO sys_menu
(id, uuid, parent_id, parent_ids,level, name, sort, href, target, icon, is_show, permission, remarks, menu_type, api_url,  create_uuid, update_uuid, disable_uuid, del_uuid, create_at, update_at, disable_at, del_at, sql_status, state, channel_id, admin_show)
VALUES(5, '5abc6860-d9f9-4544-845c-a82887049545', '4b81a47b-36b2-4344-9c32-52ce435c24d1', '',1, '系统设置', 1, '', NULL, NULL, 1, '', NULL, 1, NULL,  'adminuuid', 'adminuuid', NULL, NULL, 1533122617100, 1541680200377, NULL, NULL, 1, 1, '1', 1);


INSERT INTO sys_menu
(id, uuid, parent_id, parent_ids,level, name, sort, href, target, icon, is_show, permission, remarks, menu_type, api_url,  create_uuid, update_uuid, disable_uuid, del_uuid, create_at, update_at, disable_at, del_at, sql_status, state, channel_id, admin_show)
VALUES(6, '6fbb485f-8f58-4453-a62a-a7ffaaf273ee', '5abc6860-d9f9-4544-845c-a82887049545', '',2, '运营管理员', 1, 'home/idsysappacount', NULL, 'fa fa-columns icon', 1, '', NULL, 1, '/idsys/idsysappacount/query',  'adminuuid', '74e2db3c-8565-4fc8-9a04-f74199aef0f7', NULL, NULL, 1533122774160, 1535505696374, NULL, NULL, 1, 1, '1', 1);
INSERT INTO sys_menu
(id, uuid, parent_id, parent_ids,level, name, sort, href, target, icon, is_show, permission, remarks, menu_type, api_url,  create_uuid, update_uuid, disable_uuid, del_uuid, create_at, update_at, disable_at, del_at, sql_status, state, channel_id, admin_show)
VALUES(7, '9638bb58-6088-4cfb-87c2-55df9445ce07', '5abc6860-d9f9-4544-845c-a82887049545', '',2, '菜单管理', 2, '/home/idsysmenu', NULL, 'fa fa-columns icon', 1, 'home:idsysmenu', NULL, 1, '/idsys/idsysmenu/query',  'adminuuid', 'adminuuid', NULL, NULL, 1533127292200, 1541677040533, NULL, NULL, 1, 1, '1', 1);
INSERT INTO sys_menu
(id, uuid, parent_id, parent_ids,level, name, sort, href, target, icon, is_show, permission, remarks, menu_type, api_url,  create_uuid, update_uuid, disable_uuid, del_uuid, create_at, update_at, disable_at, del_at, sql_status, state, channel_id, admin_show)
VALUES(8, '75e8ce16-8e5d-4dd2-b0b8-8e5c611f1116', '5abc6860-d9f9-4544-845c-a82887049545', '',2, '角色类型', 3, 'home/idsysusertype', NULL, 'fa fa-flask icon', 1, 'idsys:idsysusertype:query', NULL, 1, 'idsys/idsysusertype/query', 'adminuuid', NULL, NULL, NULL, 1533162651924, NULL, NULL, NULL, 1, 1, '1', 1);
INSERT INTO sys_menu
(id, uuid, parent_id, parent_ids,level, name, sort, href, target, icon, is_show, permission, remarks, menu_type, api_url,  create_uuid, update_uuid, disable_uuid, del_uuid, create_at, update_at, disable_at, del_at, sql_status, state, channel_id, admin_show)
VALUES(9, '4e8179a1-9b1c-4416-93b9-101d1d4fc92f', '5abc6860-d9f9-4544-845c-a82887049545', '',2, '区域管理', 4, 'home/idsysarea', NULL, 'fa fa-flask icon', 1, 'idsys:idsysarea:query', NULL, 1, '/idsys/idsysarea/query','', NULL, NULL, NULL, 1533705770628, NULL, NULL, NULL, 1, 1, '1', 1);


INSERT INTO sys_menu
(id, uuid, parent_id, parent_ids,level, name, sort, href, target, icon, is_show, permission, remarks, menu_type, api_url, create_uuid, update_uuid, disable_uuid, del_uuid, create_at, update_at, disable_at, del_at, sql_status, state, channel_id, admin_show)
VALUES(10, '30ad33b4-4e09-46ef-9db3-94d85b2910d5', '6fbb485f-8f58-4453-a62a-a7ffaaf273ee', '',3, '新增', 1, '', NULL, '111122', 1, 'operate:idsysappacount:add', NULL, 1, NULL,'adminuuid', 'adminuuid', NULL, NULL, 1533128914938, 1541680645704, NULL, NULL, 1, 1, '1', 1);
INSERT INTO sys_menu
(id, uuid, parent_id, parent_ids,level, name, sort, href, target, icon, is_show, permission, remarks, menu_type, api_url, create_uuid, update_uuid, disable_uuid, del_uuid, create_at, update_at, disable_at, del_at, sql_status, state, channel_id, admin_show)
VALUES(11, 'b9902d16-ad30-40ab-83ac-af3b64145b89', '6fbb485f-8f58-4453-a62a-a7ffaaf273ee', '',3, '修改', 2, '', NULL, NULL, 1, 'operate:idsysappacount:update', NULL, 1, NULL, '', 'adminuuid', NULL, NULL, 1533273523364, 1534144164457, NULL, NULL, 1, 1, '1', 1);
INSERT INTO sys_menu
(id, uuid, parent_id, parent_ids,level, name, sort, href, target, icon, is_show, permission, remarks, menu_type, api_url, create_uuid, update_uuid, disable_uuid, del_uuid, create_at, update_at, disable_at, del_at, sql_status, state, channel_id, admin_show)
VALUES(12, '4444bd04-a8ee-4274-baad-7b0b79cf8a33', '6fbb485f-8f58-4453-a62a-a7ffaaf273ee', '',3, '删除', 3, '', NULL, NULL, 1, 'operate:idsysappacount:del', NULL, 1, NULL, '', 'adminuuid', NULL, NULL, 1533282897458, 1534144157702, NULL, NULL, 1, 1, '1', 1);
