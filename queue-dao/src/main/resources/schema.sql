SET FOREIGN_KEY_CHECKS = 0;

DROP TABLE IF EXISTS  `tb_`;
CREATE TABLE `tb_` (
  `id` bigint unsigned NOT NULL AUTO_INCREMENT,
  `is_deleted` tinyint unsigned NOT NULL DEFAULT '0' COMMENT '0 未删除\n1 已删除',
  `creator` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `modifier` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL,
  `create_time` datetime NOT NULL,
  `update_time` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

DROP TABLE IF EXISTS  `tb_app`;
CREATE TABLE `tb_app` (
  `id` bigint unsigned NOT NULL AUTO_INCREMENT,
  `name` varchar(255) COLLATE utf8mb4_general_ci NOT NULL,
  `is_deleted` tinyint unsigned NOT NULL DEFAULT '0' COMMENT '0 未删除\n1 已删除',
  `creator` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `modifier` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL,
  `create_time` datetime NOT NULL,
  `update_time` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=10001 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci COMMENT='application';

DROP TABLE IF EXISTS  `tb_area`;
CREATE TABLE `tb_area` (
  `id` bigint unsigned NOT NULL AUTO_INCREMENT,
  `code` varchar(255) COLLATE utf8mb4_general_ci DEFAULT NULL,
  `name` varchar(255) COLLATE utf8mb4_general_ci NOT NULL,
  `location` varchar(255) COLLATE utf8mb4_general_ci DEFAULT NULL,
  `is_deleted` tinyint unsigned NOT NULL DEFAULT '0' COMMENT '0 未删除\n1 已删除',
  `creator` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `modifier` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL,
  `create_time` datetime NOT NULL,
  `update_time` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci COMMENT='区域';

DROP TABLE IF EXISTS  `tb_client`;
CREATE TABLE `tb_client` (
  `id` bigint unsigned NOT NULL AUTO_INCREMENT,
  `code` varchar(255) COLLATE utf8mb4_general_ci DEFAULT NULL,
  `name` varchar(255) COLLATE utf8mb4_general_ci NOT NULL,
  `ip` varchar(255) COLLATE utf8mb4_general_ci NOT NULL,
  `scene` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT 'area：区域\ndept：科室\nroom：诊室\nmedia：无',
  `scene_id` bigint unsigned DEFAULT NULL,
  `status` varchar(255) COLLATE utf8mb4_general_ci NOT NULL COMMENT '状态',
  `is_deleted` tinyint unsigned NOT NULL DEFAULT '0' COMMENT '0 未删除\n1 已删除',
  `creator` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `modifier` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL,
  `create_time` datetime NOT NULL,
  `update_time` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci COMMENT='终端';

DROP TABLE IF EXISTS  `tb_dept`;
CREATE TABLE `tb_dept` (
  `id` bigint unsigned NOT NULL AUTO_INCREMENT,
  `pid` bigint unsigned NOT NULL COMMENT '上级',
  `code` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL,
  `name` varchar(255) COLLATE utf8mb4_general_ci NOT NULL,
  `dept_style_id` bigint unsigned DEFAULT NULL COMMENT '护士站 - 样式',
  `dept_voice_id` bigint unsigned DEFAULT NULL COMMENT '护士站 - 语音',
  `room_style_id` bigint unsigned DEFAULT NULL COMMENT '诊室 - 样式',
  `is_deleted` tinyint unsigned NOT NULL DEFAULT '0' COMMENT '0 未删除\n1 已删除',
  `creator` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `modifier` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL,
  `create_time` datetime NOT NULL,
  `update_time` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci COMMENT='科室';

DROP TABLE IF EXISTS  `tb_dict`;
CREATE TABLE `tb_dict` (
  `id` bigint unsigned NOT NULL AUTO_INCREMENT,
  `dict_type_id` bigint unsigned NOT NULL,
  `dict_type_value` varchar(255) COLLATE utf8mb4_general_ci NOT NULL,
  `name` varchar(255) COLLATE utf8mb4_general_ci NOT NULL,
  `value` varchar(255) COLLATE utf8mb4_general_ci NOT NULL,
  `remark` text COLLATE utf8mb4_general_ci,
  `is_deleted` tinyint unsigned NOT NULL DEFAULT '0' COMMENT '0 未删除\n1 已删除',
  `creator` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `modifier` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL,
  `create_time` datetime NOT NULL,
  `update_time` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci COMMENT='字典';

DROP TABLE IF EXISTS  `tb_dict_type`;
CREATE TABLE `tb_dict_type` (
  `id` bigint unsigned NOT NULL AUTO_INCREMENT,
  `name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `value` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `remark` text CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci,
  `is_deleted` tinyint unsigned NOT NULL DEFAULT '0' COMMENT '0 未删除\n1 已删除',
  `creator` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `modifier` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL,
  `create_time` datetime NOT NULL,
  `update_time` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci COMMENT='字典';

DROP TABLE IF EXISTS  `tb_doctor`;
CREATE TABLE `tb_doctor` (
  `id` bigint unsigned NOT NULL AUTO_INCREMENT,
  `user_id` bigint unsigned NOT NULL,
  `code` varchar(255) COLLATE utf8mb4_general_ci DEFAULT NULL,
  `name` varchar(255) COLLATE utf8mb4_general_ci NOT NULL,
  `head_img_url` varchar(255) COLLATE utf8mb4_general_ci DEFAULT NULL,
  `title` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '职称',
  `content` text CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci COMMENT '简介',
  `is_deleted` tinyint unsigned NOT NULL DEFAULT '0' COMMENT '0 未删除\n1 已删除',
  `creator` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `modifier` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL,
  `create_time` datetime NOT NULL,
  `update_time` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci COMMENT='医生';

DROP TABLE IF EXISTS  `tb_media`;
CREATE TABLE `tb_media` (
  `id` bigint unsigned NOT NULL AUTO_INCREMENT,
  `code` varchar(255) COLLATE utf8mb4_general_ci DEFAULT NULL,
  `name` varchar(255) COLLATE utf8mb4_general_ci NOT NULL,
  `type` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT 'video\nimage',
  `file_list` text CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '[]',
  `is_deleted` tinyint unsigned NOT NULL DEFAULT '0' COMMENT '0 未删除\n1 已删除',
  `creator` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `modifier` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL,
  `create_time` datetime NOT NULL,
  `update_time` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci COMMENT='媒体';

DROP TABLE IF EXISTS  `tb_member`;
CREATE TABLE `tb_member` (
  `id` bigint unsigned NOT NULL AUTO_INCREMENT,
  `openid` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '用户的唯一标识',
  `nickname` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '用户昵称',
  `sex` int unsigned DEFAULT NULL COMMENT '用户的性别，值为1时是男性，值为2时是女性，值为0时是未知',
  `province` varchar(255) COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '用户个人资料填写的省份',
  `city` varchar(255) COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '普通用户个人资料填写的城市',
  `country` varchar(255) COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '国家，如中国为CN',
  `headimgurl` varchar(255) COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '用户头像，最后一个数值代表正方形头像大小（有0、46、64、96、132数值可选，0代表640*640正方形头像），用户没有头像时该项为空。若用户更换头像，原有头像URL将失效。',
  `tel` varchar(255) COLLATE utf8mb4_general_ci DEFAULT NULL,
  `is_deleted` tinyint unsigned NOT NULL DEFAULT '0' COMMENT '0 未删除\n1 已删除',
  `creator` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `modifier` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL,
  `create_time` datetime NOT NULL,
  `update_time` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci COMMENT='会员';

DROP TABLE IF EXISTS  `tb_menu`;
CREATE TABLE `tb_menu` (
  `id` bigint unsigned NOT NULL AUTO_INCREMENT,
  `pid` bigint unsigned NOT NULL,
  `name` varchar(255) COLLATE utf8mb4_general_ci NOT NULL,
  `is_deleted` tinyint unsigned NOT NULL DEFAULT '0' COMMENT '0 未删除\n1 已删除',
  `creator` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `modifier` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL,
  `create_time` datetime NOT NULL,
  `update_time` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci COMMENT='菜单';

DROP TABLE IF EXISTS  `tb_notice`;
CREATE TABLE `tb_notice` (
  `id` bigint unsigned NOT NULL AUTO_INCREMENT,
  `dept_id` bigint unsigned NOT NULL COMMENT '科室',
  `start_time` datetime NOT NULL COMMENT '开始时间',
  `end_time` datetime NOT NULL COMMENT '结束时间',
  `content` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '内容',
  `source` varchar(255) COLLATE utf8mb4_general_ci DEFAULT NULL,
  `is_deleted` tinyint unsigned NOT NULL DEFAULT '0' COMMENT '0 未删除\n1 已删除',
  `creator` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `modifier` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL,
  `create_time` datetime NOT NULL,
  `update_time` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci COMMENT='公告';

DROP TABLE IF EXISTS  `tb_nurse`;
CREATE TABLE `tb_nurse` (
  `id` bigint unsigned NOT NULL AUTO_INCREMENT,
  `user_id` bigint unsigned NOT NULL,
  `code` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL,
  `name` varchar(255) COLLATE utf8mb4_general_ci NOT NULL,
  `is_deleted` tinyint unsigned NOT NULL DEFAULT '0' COMMENT '0 未删除\n1 已删除',
  `creator` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `modifier` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL,
  `create_time` datetime NOT NULL,
  `update_time` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci COMMENT='护士';

DROP TABLE IF EXISTS  `tb_patient`;
CREATE TABLE `tb_patient` (
  `id` bigint unsigned NOT NULL AUTO_INCREMENT,
  `code` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL,
  `name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `member_id` bigint unsigned DEFAULT NULL,
  `is_deleted` tinyint unsigned NOT NULL DEFAULT '0' COMMENT '0 未删除\n1 已删除',
  `creator` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `modifier` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL,
  `create_time` datetime NOT NULL,
  `update_time` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci COMMENT='患者';

DROP TABLE IF EXISTS  `tb_queue`;
CREATE TABLE `tb_queue` (
  `id` bigint unsigned NOT NULL AUTO_INCREMENT,
  `dept_id` bigint unsigned NOT NULL COMMENT '科室',
  `signed_no` int unsigned NOT NULL DEFAULT '0' COMMENT '已签到号',
  `status` varchar(255) COLLATE utf8mb4_general_ci NOT NULL COMMENT '状态',
  `is_deleted` tinyint unsigned NOT NULL DEFAULT '0' COMMENT '0 未删除\n1 已删除',
  `creator` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `modifier` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL,
  `create_time` datetime NOT NULL,
  `update_time` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci COMMENT='排队';

DROP TABLE IF EXISTS  `tb_queue_detail`;
CREATE TABLE `tb_queue_detail` (
  `id` bigint unsigned NOT NULL AUTO_INCREMENT,
  `queue_id` bigint NOT NULL,
  `room_id` bigint unsigned DEFAULT NULL COMMENT '诊室',
  `patient_id` bigint NOT NULL COMMENT '患者',
  `patient_name` varchar(255) COLLATE utf8mb4_general_ci NOT NULL,
  `no` int NOT NULL,
  `status` varchar(255) COLLATE utf8mb4_general_ci NOT NULL COMMENT '状态',
  `is_deleted` tinyint unsigned NOT NULL DEFAULT '0' COMMENT '0 未删除\n1 已删除',
  `creator` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `modifier` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL,
  `create_time` datetime NOT NULL,
  `update_time` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci COMMENT='排队 - 详情';

DROP TABLE IF EXISTS  `tb_queue_rule`;
CREATE TABLE `tb_queue_rule` (
  `id` bigint unsigned NOT NULL AUTO_INCREMENT,
  `queue_id` bigint unsigned NOT NULL,
  `rule_id` bigint NOT NULL COMMENT '规则',
  `rule_code` varchar(255) COLLATE utf8mb4_general_ci NOT NULL,
  `value` int NOT NULL COMMENT '阈值（上限）',
  `is_deleted` tinyint unsigned NOT NULL DEFAULT '0' COMMENT '0 未删除\n1 已删除',
  `creator` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `modifier` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL,
  `create_time` datetime NOT NULL,
  `update_time` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci COMMENT='排队 - 规则';

DROP TABLE IF EXISTS  `tb_queue_template`;
CREATE TABLE `tb_queue_template` (
  `id` bigint unsigned NOT NULL AUTO_INCREMENT,
  `queue_id` bigint unsigned NOT NULL,
  `template_id` bigint NOT NULL COMMENT '模版',
  `is_deleted` tinyint unsigned NOT NULL DEFAULT '0' COMMENT '0 未删除\n1 已删除',
  `creator` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `modifier` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL,
  `create_time` datetime NOT NULL,
  `update_time` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci COMMENT='排队 - 消息模板';

DROP TABLE IF EXISTS  `tb_role`;
CREATE TABLE `tb_role` (
  `id` bigint unsigned NOT NULL AUTO_INCREMENT,
  `code` varchar(255) COLLATE utf8mb4_general_ci NOT NULL,
  `name` varchar(255) COLLATE utf8mb4_general_ci NOT NULL,
  `is_deleted` tinyint unsigned NOT NULL DEFAULT '0' COMMENT '0 未删除\n1 已删除',
  `creator` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `modifier` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL,
  `create_time` datetime NOT NULL,
  `update_time` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci COMMENT='角色';

DROP TABLE IF EXISTS  `tb_role_menu`;
CREATE TABLE `tb_role_menu` (
  `id` bigint unsigned NOT NULL AUTO_INCREMENT,
  `role_id` bigint NOT NULL COMMENT '角色',
  `menu_id` bigint NOT NULL COMMENT '菜单',
  `is_deleted` tinyint unsigned NOT NULL DEFAULT '0' COMMENT '0 未删除\n1 已删除',
  `creator` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `modifier` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL,
  `create_time` datetime NOT NULL,
  `update_time` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci COMMENT='角色 - 菜单';

DROP TABLE IF EXISTS  `tb_room`;
CREATE TABLE `tb_room` (
  `id` bigint unsigned NOT NULL AUTO_INCREMENT,
  `dept_id` bigint unsigned NOT NULL COMMENT '科室',
  `code` varchar(255) COLLATE utf8mb4_general_ci DEFAULT NULL,
  `name` varchar(255) COLLATE utf8mb4_general_ci NOT NULL,
  `ip` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `status` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '状态',
  `is_deleted` tinyint unsigned NOT NULL DEFAULT '0' COMMENT '0 未删除\n1 已删除',
  `creator` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `modifier` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL,
  `create_time` datetime NOT NULL,
  `update_time` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci COMMENT='诊室（窗口）';

DROP TABLE IF EXISTS  `tb_room_doctor`;
CREATE TABLE `tb_room_doctor` (
  `id` bigint unsigned NOT NULL AUTO_INCREMENT,
  `room_id` bigint unsigned NOT NULL COMMENT '诊室',
  `doctor_id` bigint unsigned NOT NULL COMMENT '医生',
  `is_deleted` tinyint unsigned NOT NULL DEFAULT '0' COMMENT '0 未删除\n1 已删除',
  `creator` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `modifier` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL,
  `create_time` datetime NOT NULL,
  `update_time` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci COMMENT='诊室 - 医生';

DROP TABLE IF EXISTS  `tb_room_nurse`;
CREATE TABLE `tb_room_nurse` (
  `id` bigint unsigned NOT NULL AUTO_INCREMENT,
  `room_id` bigint NOT NULL COMMENT '诊室',
  `nurse_id` bigint NOT NULL COMMENT '护士',
  `is_deleted` tinyint unsigned NOT NULL DEFAULT '0' COMMENT '0 未删除\n1 已删除',
  `creator` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `modifier` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL,
  `create_time` datetime NOT NULL,
  `update_time` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci COMMENT='诊室 - 护士';

DROP TABLE IF EXISTS  `tb_rule`;
CREATE TABLE `tb_rule` (
  `id` bigint unsigned NOT NULL AUTO_INCREMENT,
  `code` varchar(255) COLLATE utf8mb4_general_ci NOT NULL,
  `content` text CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '说明',
  `is_deleted` tinyint unsigned NOT NULL DEFAULT '0' COMMENT '0 未删除\n1 已删除',
  `creator` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `modifier` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL,
  `create_time` datetime NOT NULL,
  `update_time` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci COMMENT='规则';

DROP TABLE IF EXISTS  `tb_schedule`;
CREATE TABLE `tb_schedule` (
  `id` bigint unsigned NOT NULL AUTO_INCREMENT,
  `dept_id` bigint unsigned NOT NULL COMMENT '科室',
  `code` varchar(255) COLLATE utf8mb4_general_ci DEFAULT NULL,
  `date` datetime NOT NULL COMMENT 'yyyy-mm-dd',
  `time` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT 'am\npm',
  `doctor_id` bigint unsigned NOT NULL COMMENT '医生',
  `max_no` int NOT NULL DEFAULT '0',
  `is_deleted` tinyint unsigned NOT NULL DEFAULT '0' COMMENT '0 未删除\n1 已删除',
  `creator` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `modifier` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL,
  `create_time` datetime NOT NULL,
  `update_time` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci COMMENT='排班';

DROP TABLE IF EXISTS  `tb_style`;
CREATE TABLE `tb_style` (
  `id` bigint unsigned NOT NULL AUTO_INCREMENT,
  `code` varchar(255) COLLATE utf8mb4_general_ci NOT NULL,
  `name` varchar(255) COLLATE utf8mb4_general_ci NOT NULL,
  `url` varchar(255) COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '示例',
  `content` text COLLATE utf8mb4_general_ci COMMENT '{}',
  `is_deleted` tinyint unsigned NOT NULL DEFAULT '0' COMMENT '0 未删除\n1 已删除',
  `creator` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `modifier` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL,
  `create_time` datetime NOT NULL,
  `update_time` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci COMMENT='样式';

DROP TABLE IF EXISTS  `tb_template`;
CREATE TABLE `tb_template` (
  `id` bigint unsigned NOT NULL AUTO_INCREMENT,
  `template_id` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '模版消息',
  `name` varchar(255) COLLATE utf8mb4_general_ci DEFAULT NULL,
  `content` text COLLATE utf8mb4_general_ci COMMENT '{}',
  `is_deleted` tinyint unsigned NOT NULL DEFAULT '0' COMMENT '0 未删除\n1 已删除',
  `creator` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `modifier` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL,
  `create_time` datetime NOT NULL,
  `update_time` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci COMMENT='消息模版';

DROP TABLE IF EXISTS  `tb_tunnel`;
CREATE TABLE `tb_tunnel` (
  `id` bigint unsigned NOT NULL AUTO_INCREMENT,
  `client_id` bigint unsigned NOT NULL COMMENT '终端',
  `tunnel_id` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `is_deleted` tinyint unsigned NOT NULL COMMENT '0 未删除\n1 已删除',
  `creator` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `modifier` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL,
  `create_time` datetime NOT NULL,
  `update_time` datetime DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `idx_clientid_isdeleted` (`client_id`,`is_deleted`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci COMMENT='信道';

DROP TABLE IF EXISTS  `tb_user`;
CREATE TABLE `tb_user` (
  `id` bigint unsigned NOT NULL AUTO_INCREMENT,
  `name` varchar(255) COLLATE utf8mb4_general_ci NOT NULL,
  `passport` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '账号',
  `password` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '密码',
  `role_id` bigint unsigned NOT NULL COMMENT '角色',
  `status` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT 'normal',
  `is_deleted` tinyint unsigned NOT NULL DEFAULT '0' COMMENT '0 未删除\n1 已删除',
  `creator` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `modifier` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL,
  `create_time` datetime NOT NULL,
  `update_time` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci COMMENT='用户';

DROP TABLE IF EXISTS  `tb_user_dept`;
CREATE TABLE `tb_user_dept` (
  `id` bigint unsigned NOT NULL AUTO_INCREMENT,
  `user_id` bigint unsigned NOT NULL,
  `dept_id` bigint NOT NULL COMMENT '科室',
  `is_deleted` tinyint unsigned NOT NULL DEFAULT '0' COMMENT '0 未删除\n1 已删除',
  `creator` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `modifier` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL,
  `create_time` datetime NOT NULL,
  `update_time` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci COMMENT='用户 - 科室';

DROP TABLE IF EXISTS  `tb_version`;
CREATE TABLE `tb_version` (
  `id` bigint unsigned NOT NULL AUTO_INCREMENT,
  `app_id` bigint unsigned NOT NULL,
  `client` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT 'android\nwgt',
  `url` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '更新地址',
  `is_deleted` tinyint unsigned NOT NULL DEFAULT '0' COMMENT '0 未删除\n1 已删除',
  `creator` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `modifier` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL,
  `create_time` datetime NOT NULL,
  `update_time` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci COMMENT='版本';

DROP TABLE IF EXISTS  `tb_version_detail`;
CREATE TABLE `tb_version_detail` (
  `id` bigint unsigned NOT NULL AUTO_INCREMENT,
  `version_id` bigint unsigned NOT NULL,
  `version` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '1.0.0',
  `status` varchar(255) COLLATE utf8mb4_general_ci NOT NULL,
  `is_deleted` tinyint unsigned NOT NULL DEFAULT '0' COMMENT '0 未删除\n1 已删除',
  `creator` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `modifier` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL,
  `create_time` datetime NOT NULL,
  `update_time` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci COMMENT='版本';

DROP TABLE IF EXISTS  `tb_voice`;
CREATE TABLE `tb_voice` (
  `id` bigint unsigned NOT NULL AUTO_INCREMENT,
  `code` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `name` varchar(255) COLLATE utf8mb4_general_ci NOT NULL,
  `content` text COLLATE utf8mb4_general_ci COMMENT '{}',
  `is_deleted` tinyint unsigned NOT NULL DEFAULT '0' COMMENT '0 未删除\n1 已删除',
  `creator` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `modifier` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL,
  `create_time` datetime NOT NULL,
  `update_time` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci COMMENT='语音';

SET FOREIGN_KEY_CHECKS = 1;

