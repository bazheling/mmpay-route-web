-- 1.0

CREATE TABLE `mmp_route_config` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `app_id` varchar(32) DEFAULT NULL COMMENT '应用编码',
  `app_secret` varchar(32) DEFAULT NULL,
  `app_name` varchar(20) DEFAULT NULL,
  `mmp_web_url` varchar(50) DEFAULT NULL,
  `app_public_key` varchar(2048) DEFAULT NULL COMMENT '应用公钥',
  `mmp_private_key` varchar(2048) DEFAULT NULL COMMENT '平台私钥',
  `app_flag` varchar(5) DEFAULT NULL COMMENT '应用标识（ALI支付宝、WX微信、UP银联、PA平安、HIS医院、MI医保等）',
  `app_channel` varchar(2) DEFAULT NULL COMMENT '应用类型（01服务窗、02公众号、03移动APP、04H5网页、05HIS系统）',
  `med_org_no` varchar(32) DEFAULT NULL COMMENT '医疗机构编号',
  `status` tinyint(2) DEFAULT '1' COMMENT '应用状态，1:有效 0:无效',
  `gmt_create` datetime DEFAULT NULL COMMENT '创建时间',
  `gmt_modified` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

