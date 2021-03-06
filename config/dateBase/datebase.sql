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

drop table if exists p_option;
create table p_option(
	option_set	CHAR(16)	not null	comment	'参数类型',
	option_key	CHAR(4)	not null	comment	'参数值',
	option_name	CHAR(32)	not null	comment	'参数名',
	option_par	CHAR(4)	not null	comment	'父参数值',
)comment = '下拉参数表';

drop table if exists p_area;
create table p_area(
	area_code	CHAR(12)	not null	comment	'地区代码',
	area_name	CHAR(64)	not null	comment	'地区名',
	area_level	CHAR(2)	not null	comment	'地区级别',
	area_type	CHAR(3)	not null	comment	'地区类型',
	primary key (`area_code`)
)comment = '地区表';

drop table if exists p_member;
create table p_member(
	mbr_id	CHAR(32)	not null	comment	'会员ID',
	mbr_name	CHAR(20)	not null	comment	'会员名',
	password	CHAR(32)	not null	comment	'会员密码',
	phone	CHAR(11)	null	comment	'手机号',
	email	CHAR(32)	null	comment	'邮箱号',
	qq_no	CHAR(12)	null	comment	'QQ号',
	weixin_no	CHAR(16)	null	comment	'微信号',
	register_time	CHAR(14)	not null	comment	'注册时间',
	recommender_id	CHAR(32)	not null	comment	'推荐人ID',
	pic_url	CHAR(64)	not null	comment	'头像URL',
	primary key (`mbr_id`)
)comment = '会员表';

drop table if exists p_mbr_info;
create table p_mbr_info(
	mbr_id	CHAR(32)	not null	comment	'会员ID',
	mbr_name	CHAR(20)	null	comment	'昵称',
	sex	CHAR(2)	null	comment	'性别',
	age	CHAR(3)	null	comment	'年龄',
	constellation	CHAR(2)	null	comment	'星座',
	height	CHAR(3)	null	comment	'身高',
	blood_type	CHAR(2)	null	comment	'血型',
	weight	CHAR(3)	null	comment	'体重',
	occupation	CHAR(2)	null	comment	'职业',
	education	CHAR(2)	null	comment	'学历',
	marry_status	CHAR(2)	null	comment	'婚姻状况',
	month_income	CHAR(2)	null	comment	'月收入',
	native_place	CHAR(9)	null	comment	'籍贯',
	hava_house	CHAR(2)	null	comment	'是否有房',
	birthday	CHAR(8)	null	comment	'生日',
	location	CHAR(12)	null	comment	'所在地',
	primary key (`mbr_id`)
)comment = '会员基本信息表';

drop table if exists p_mbr_detail;
create table p_mbr_detail(
	mbr_id	CHAR(32)	not null	comment	'会员ID',
	need_child	CHAR(2)	null	comment	'想要小孩',
	can_yidi	CHAR(2)	null	comment	'接受异地恋',
	like_type	CHAR(2)	null	comment	'喜欢的类型',
	can_sex	CHAR(2)	null	comment	'接受婚前性行为',
	with_parents	CHAR(2)	null	comment	'接受与父母同住',
	best_part	CHAR(2)	null	comment	'最有魅力部位',
	hobbys	CHAR(128)	null	comment	'兴趣爱好',
	characters	CHAR(128)	null	comment	'个性特征',
	soliloquy	CHAR(254)	null	comment	'内心独白',
	primary key (`mbr_id`)
)comment = '会员详细信息表';

drop table if exists p_mbr_pic;
create table p_mbr_pic(
	pic_id	CHAR(32)	not null	comment	'照片Id',
	mbr_id	CHAR(32)	not null	comment	'会员ID',
	pic_state	CHAR(2)	not null	comment	'照片状态',
	pic_url	CHAR(64)	not null	comment	'照片URL',
	upload_time	CHAR(14)	not null	comment	'上传时间',
	primary key (`pic_id`)
)comment = '会员照片表';

drop table if exists p_frd_limit;
create table p_frd_limit(
	mbr_id	CHAR(32)	not null	comment	'会员ID',
	location	CHAR(12)	null	comment	'居住地',
	begin_age	CHAR(3)	null	comment	'起始年龄',
	end_age	CHAR(3)	null	comment	'终止年龄',
	begin_height	CHAR(3)	null	comment	'起始身高',
	end_height	CHAR(3)	null	comment	'终止身高',
	education	CHAR(2)	null	comment	'学历',
	month_income	CHAR(2)	null	comment	'月收入',
	primary key (`mbr_id`)
)comment = '会员征友条件表';

drop table if exists p_mbr_letter;
create table p_mbr_letter(
	letter_id	CHAR(32)	not null	comment	'信件ID',
	mbr_id	CHAR(32)	not null	comment	'会员ID',
	letter_content	CHAR(128)	not null	comment	'信件类容',
	send_time	CHAR(14)	not null	comment	'发送时间',
	state	CHAR(2)	not null	comment	'信件状态',
	primary key (`letter_id`)
)comment = '会员信件表';

drop table if exists p_mbr_ads;
create table p_mbr_ads(
	mbr_id	CHAR(32)	not null	comment	'会员ID',
	ad_amt	CHAR(10)	not null	comment	'投放金币',
	ad_time	CHAR(14)	not null	comment	'投放时间',
	end_time	CHAR(14)	not null	comment	'结束时间',
	primary key (`mbr_id`)
)comment = '会员广告表';

drop table if exists p_wait_search;
create table p_wait_search(
	ws_url	CHAR(128)	not null	comment	'待遍历链接',
	ws_depth	CHAR(2)	not null	comment	'深度',
	primary key (`ws_url`)
)comment = '待遍历表';

