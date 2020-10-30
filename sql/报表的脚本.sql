
drop table if exists rp_main;
CREATE TABLE `rp_main` (
  `id` varchar(64) NOT NULL COMMENT '编号',
	`code` varchar(64) NOT NULL COMMENT '自定义查询编号',
`cnname` varchar(64) NOT NULL COMMENT '自定义查询名称',
`enname` varchar(64) NOT NULL COMMENT '自定义查询英文名称',
`template_id` varchar(64) NOT NULL COMMENT '自定义查询使用的模板',
cus_group_id varchar(64) NULL COMMENT '客户组',
cus_id varchar(64) NULL COMMENT '客户',
operator_id varchar(64) NULL COMMENT '业务员',
pay_group_id varchar(64) NULL COMMENT '薪酬组',
hr_id varchar(64) NULL COMMENT 'HR',
emp_id varchar(64) NULL COMMENT '雇员',
datasource_id varchar(64) NOT NULL COMMENT '从哪个数据库中读取数据',
be_sql text NULL COMMENT '输出数据之前需要执行的语句',
main_sql text NOT NULL COMMENT '输出数据需要执行的语句',
end_sql text NULL COMMENT '输出数据之后需要执行的语句',
ispage tinyint COMMENT '是否分页',
def_page_num int comment '默认分页每页展示行数',
isprint tinyint COMMENT '是否打印',
isexport tinyint COMMENT '是否导出',
  `status` char(1) NOT NULL DEFAULT '0' COMMENT '状态（0正常 1删除 2停用）',
  `create_by` varchar(64) NOT NULL COMMENT '创建者',
  `create_date` datetime NOT NULL COMMENT '创建时间',
  `update_by` varchar(64) NOT NULL COMMENT '更新者',
  `update_date` datetime NOT NULL COMMENT '更新时间',
  `remarks` varchar(500) DEFAULT NULL COMMENT '备注信息',
	headdiv text COMMENT '表头HTML',
	footdiv text COMMENT '表尾HTML',
	enheaddiv text COMMENT '英文表头HTML',
	enfootdiv text COMMENT '英文表尾HTML',
	cnenheaddiv text COMMENT '中英文表头HTML',
	cnenfootdiv text COMMENT '中英文表尾HTML',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='报表主表';

drop table if exists rp_main_detial;
CREATE TABLE `rp_main_detial` (
  `id` varchar(64) NOT NULL COMMENT '编号',
  `rp_main_id` varchar(64) NOT NULL COMMENT '外键，报表主表ID',
  `detial_order` int NOT NULL COMMENT '显示顺序',
  `code` varchar(64) NOT NULL COMMENT 'SQL语句中的列名',
  `code_type` varchar(64) NOT NULL COMMENT '数据类型',
  `cnname` varchar(64) NOT NULL COMMENT '展示到页面上的列名',
  `enname` varchar(64) NOT NULL COMMENT '展示到页面上的英文列名',
	`cnenname` varchar(64) NULL COMMENT '展示到页面上的英文中文列名',
  `cn_group_name` varchar(64) DEFAULT NULL COMMENT '展示到页面上的组合列名',
  `en_group_name` varchar(64) DEFAULT NULL COMMENT '展示到页面上的组合英文列名',
  `cnen_group_name` varchar(64) DEFAULT NULL COMMENT '展示到页面上的组合中文英文列名',
  `isvisible` int DEFAULT NULL COMMENT '是否显示',
  `per_visible` int DEFAULT NULL COMMENT '显示占比',
  `iscondition` tinyint DEFAULT NULL COMMENT '是否用于查询条件',
  `isexport` tinyint DEFAULT NULL COMMENT '是否导出',
  `dict_table_name` varchar(64) DEFAULT NULL COMMENT '对应字典表名',
	edit varchar(64) DEFAULT NULL COMMENT '条件框',
	templet varchar(64) DEFAULT NULL COMMENT '#selectSex  edit:"radio"时templet:"爬山:ps|跳舞:tw" 等',
	layVerify varchar(64) DEFAULT NULL COMMENT '验证方式如：number，phone，email',
  `status` char(1) NOT NULL DEFAULT '0' COMMENT '状态（0正常 1删除 2停用）',
  `create_by` varchar(64) NOT NULL COMMENT '创建者',
  `create_date` datetime NOT NULL COMMENT '创建时间',
  `update_by` varchar(64) NOT NULL COMMENT '更新者',
  `update_date` datetime NOT NULL COMMENT '更新时间',
  `remarks` varchar(500) DEFAULT NULL COMMENT '备注信息',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='报表详细列设置';

drop table if exists rp_rel_emp;
CREATE TABLE `rp_rel_emp` (
  `id` varchar(64) NOT NULL COMMENT '编号',
	`code` varchar(64) NOT NULL COMMENT '雇员编号,外键，与报表主表关联，当相同时表示是同一组',
`cnname` varchar(64) NOT NULL COMMENT '雇员名称',
`enname` varchar(64) NOT NULL COMMENT '雇员英文名称',
  `status` char(1) NOT NULL DEFAULT '0' COMMENT '状态（0正常 1删除 2停用）',
  `create_by` varchar(64) NOT NULL COMMENT '创建者',
  `create_date` datetime NOT NULL COMMENT '创建时间',
  `update_by` varchar(64) NOT NULL COMMENT '更新者',
  `update_date` datetime NOT NULL COMMENT '更新时间',
  `remarks` varchar(500) DEFAULT NULL COMMENT '备注信息',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='雇员的报表限制';


drop table if exists rp_rel_hr;
CREATE TABLE `rp_rel_hr` (
  `id` varchar(64) NOT NULL COMMENT '编号',
	`code` varchar(64) NOT NULL COMMENT 'HR编号,外键，与报表主表关联，当相同时表示是同一组',
`cnname` varchar(64) NOT NULL COMMENT 'HR名称',
`enname` varchar(64) NOT NULL COMMENT 'HR英文名称',
  `status` char(1) NOT NULL DEFAULT '0' COMMENT '状态（0正常 1删除 2停用）',
  `create_by` varchar(64) NOT NULL COMMENT '创建者',
  `create_date` datetime NOT NULL COMMENT '创建时间',
  `update_by` varchar(64) NOT NULL COMMENT '更新者',
  `update_date` datetime NOT NULL COMMENT '更新时间',
  `remarks` varchar(500) DEFAULT NULL COMMENT '备注信息',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='HR的报表限制';



drop table if exists rp_rel_pay_group;
CREATE TABLE `rp_rel_pay_group` (
  `id` varchar(64) NOT NULL COMMENT '编号',
	`code` varchar(64) NOT NULL COMMENT '薪酬组编号,外键，与报表主表关联，当相同时表示是同一组',
`cnname` varchar(64) NOT NULL COMMENT '薪酬组名称',
`enname` varchar(64) NOT NULL COMMENT '薪酬组英文名称',
  `status` char(1) NOT NULL DEFAULT '0' COMMENT '状态（0正常 1删除 2停用）',
  `create_by` varchar(64) NOT NULL COMMENT '创建者',
  `create_date` datetime NOT NULL COMMENT '创建时间',
  `update_by` varchar(64) NOT NULL COMMENT '更新者',
  `update_date` datetime NOT NULL COMMENT '更新时间',
  `remarks` varchar(500) DEFAULT NULL COMMENT '备注信息',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='薪酬组的报表限制';


drop table if exists rp_rel_operator;
CREATE TABLE `rp_rel_operator` (
  `id` varchar(64) NOT NULL COMMENT '编号',
	`code` varchar(64) NOT NULL COMMENT '业务员编号,外键，与报表主表关联，当相同时表示是同一组',
`cnname` varchar(64) NOT NULL COMMENT '业务员名称',
`enname` varchar(64) NOT NULL COMMENT '业务员英文名称',
  `status` char(1) NOT NULL DEFAULT '0' COMMENT '状态（0正常 1删除 2停用）',
  `create_by` varchar(64) NOT NULL COMMENT '创建者',
  `create_date` datetime NOT NULL COMMENT '创建时间',
  `update_by` varchar(64) NOT NULL COMMENT '更新者',
  `update_date` datetime NOT NULL COMMENT '更新时间',
  `remarks` varchar(500) DEFAULT NULL COMMENT '备注信息',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='业务员的报表限制';


drop table if exists rp_rel_cus;
CREATE TABLE `rp_rel_cus` (
  `id` varchar(64) NOT NULL COMMENT '编号',
	`code` varchar(64) NOT NULL COMMENT '客户编号,外键，与报表主表关联，当相同时表示是同一组',
`cnname` varchar(64) NOT NULL COMMENT '客户名称',
`enname` varchar(64) NOT NULL COMMENT '客户英文名称',
  `status` char(1) NOT NULL DEFAULT '0' COMMENT '状态（0正常 1删除 2停用）',
  `create_by` varchar(64) NOT NULL COMMENT '创建者',
  `create_date` datetime NOT NULL COMMENT '创建时间',
  `update_by` varchar(64) NOT NULL COMMENT '更新者',
  `update_date` datetime NOT NULL COMMENT '更新时间',
  `remarks` varchar(500) DEFAULT NULL COMMENT '备注信息',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='客户的报表限制';


drop table if exists rp_rel_cus_group;
CREATE TABLE `rp_rel_cus_group` (
  `id` varchar(64) NOT NULL COMMENT '编号',
	`code` varchar(64) NOT NULL COMMENT '客户组编号,外键，与报表主表关联，当相同时表示是同一组',
`cnname` varchar(64) NOT NULL COMMENT '客户组名称',
`enname` varchar(64) NOT NULL COMMENT '客户组英文名称',
  `status` char(1) NOT NULL DEFAULT '0' COMMENT '状态（0正常 1删除 2停用）',
  `create_by` varchar(64) NOT NULL COMMENT '创建者',
  `create_date` datetime NOT NULL COMMENT '创建时间',
  `update_by` varchar(64) NOT NULL COMMENT '更新者',
  `update_date` datetime NOT NULL COMMENT '更新时间',
  `remarks` varchar(500) DEFAULT NULL COMMENT '备注信息',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='客户组的报表限制';