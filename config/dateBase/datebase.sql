drop table if exists p_menu;
create table p_menu(
	menu_id	CHAR(32)	not null	comment	'菜单ID',
	menu_name	CHAR(32)	not null	comment	'菜单名',
	menu_type	CHAR(2)	not null	comment	'菜单类型',
	menu_prop	CHAR(2)	not null	comment	'菜单性质',
	menu_sort	CHAR(2)	not null	comment	'菜单排序',
	menu_state	CHAR(2)	not null	comment	'菜单状态',
	up_menu_id	CHAR(32)	null	comment	'父菜单ID',
	menu_icon	CHAR(32)	null	comment	'菜单图标',
	menu_link	CHAR(64)	null	comment	'菜单链接',
	menu_desc	VARCHAR(512)	null	comment	'菜单描述',
	creater_id	CHAR(32)	not null	comment	'创建人ID',
	create_time	CHAR(14)	not null	comment	'创建时间',
	updater_id	CHAR(32)	null	comment	'更新人ID',
	update_time	CHAR(14)	null	comment	'更新时间',
	primary key (`menu_id`)
)comment = '菜单表';

drop table if exists p_function;
create table p_function(
	func_id	CHAR(32)	not null	comment	'功能ID',
	func_code	CHAR(20)	not null	comment	'功能代码',
	func_name	CHAR(20)	not null	comment	'功能名称',
	func_icon	CHAR(32)	null	comment	'功能图标',
	func_sort	CHAR(2)	not null	comment	'功能排序',
	func_state	CHAR(2)	not null	comment	'功能状态',
	func_desc	VARCHAR(512)	null	comment	'功能描述',
	creater_id	CHAR(32)	not null	comment	'创建人ID',
	create_time	CHAR(14)	not null	comment	'创建时间',
	updater_id	CHAR(32)	null	comment	'更新人ID',
	update_time	CHAR(14)	null	comment	'更新时间',
	primary key (`func_id`)
)comment = '功能表';

drop table if exists p_user;
create table p_user(
	user_id	CHAR(32)	not null	comment	'用户ID',
	user_no	CHAR(20)	not null	comment	'用户号',
	user_pwd	CHAR(32)	not null	comment	'用户密码',
	user_name	CHAR(32)	null	comment	'用户名',
	role_id	CHAR(32)	null	comment	'用户角色',
	dept_id	CHAR(32)	null	comment	'用户部门',
	user_level	CHAR(2)	null	comment	'用户级别',
	user_phone	CHAR(20)	null	comment	'用户手机号',
	user_email	CHAR(50)	null	comment	'用户邮箱',
	user_state	CHAR(2)	not null	DEFAULT '1'	comment	'用户状态',
	is_online	CHAR(2)	not null	DEFAULT '0'	comment	'是否在线',
	online_ip	CHAR(20)	null	comment	'在线IP',
	creater_id	CHAR(32)	not null	comment	'创建人ID',
	create_time	CHAR(14)	not null	comment	'创建时间',
	updater_id	CHAR(32)	null	comment	'更新人ID',
	update_time	CHAR(14)	null	comment	'更新时间',
	primary key (`user_id`)
)comment = '用户表';

insert into p_user values ('USER000001','admin','c81e728d9d4c2f636f067f89cc14862c','超级管理员',null,null,'1','18627706918','987031490@qq.com','1','0',null,'USER000001','20161018115311',null,null);

drop table if exists p_dept;
create table p_dept(
	dept_id	CHAR(32)	not null	comment	'部门ID',
	dept_name	CHAR(32)	not null	comment	'部门名称',
	dept_desc	VARCHAR(512)	null	comment	'部门描述',
	dept_level	CHAR(2)	not null	comment	'部门级别',
	dept_mng_id	CHAR(32)	null	comment	'负责人ID',
	up_dept_id	CHAR(32)	null	comment	'上级部门',
	dept_state	CHAR(2)	not null	DEFAULT '1'	comment	'部门状态',
	creater_id	CHAR(32)	not null	comment	'创建人ID',
	create_time	CHAR(14)	not null	comment	'创建时间',
	updater_id	CHAR(32)	null	comment	'更新人ID',
	update_time	CHAR(14)	null	comment	'更新时间',
	primary key (`dept_id`)
)comment = '部门表';

drop table if exists p_role;
create table p_role(
	role_id	CHAR(32)	not null	comment	'角色ID',
	role_name	CHAR(32)	not null	comment	'角色名',
	role_desc	VARCHAR(512)	null	comment	'角色描述',
	role_state	CHAR(2)	not null	DEFAULT '1'	comment	'角色状态',
	creater_id	CHAR(32)	not null	comment	'创建人ID',
	create_time	CHAR(14)	not null	comment	'创建时间',
	updater_id	CHAR(32)	null	comment	'更新人ID',
	update_time	CHAR(14)	null	comment	'更新时间',
	primary key (`role_id`)
)comment = '角色表';

drop table if exists p_role_menu;
create table p_role_menu(
	rm_id	CHAR(32)	not null	comment	'角色菜单ID',
	role_id	CHAR(32)	not null	comment	'角色ID',
	menu_id	CHAR(32)	not null	comment	'菜单ID',
	primary key (`rm_id`)
)comment = '角色菜单表';

