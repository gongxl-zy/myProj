delete from p_column;
insert into p_column
select concat(table_name,column_name),column_name,column_comment,'','0',table_name,'',ordinal_position,column_type 
from information_schema.COLUMNS where table_schema = 'mydb' and table_name<>'p_lottery_record_ssq' order by table_name,ordinal_position;
update p_column c inner join information_schema.tables t on c.column_table=t.table_name set c.table_name=t.table_comment;