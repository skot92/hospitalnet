### Create the admin user

username: admin, pass: admin

```sql
delimiter $$
create procedure `create_admin`()
begin
	declare admin_id bigint default null;
    declare admin_user_id bigint default null;
	set admin_id := (select r.id from roles r where r.role_name like '%ADMIN%' limit 1);
	if(admin_id is null) then
		insert into roles (role_name) values ('ROLE_ADMIN');
        set admin_id := (select r.id from roles r where r.role_name like '%ADMIN%' limit 1);
	end if;
    select admin_id;
    set admin_user_id := (select id from workers where id = admin_id limit 1);
    if(admin_user_id is null) then
		insert into workers (dob, id_num, full_name, pass, ssn, username, role_id)
		values (str_to_date('1990-11-08','%Y-%m-%d'), '21451131', 'Admin Géza', '$2a$10$7YJsQW9reBfaPbyJnjkXf.S6qtr5uU8JNX/KUp3LrVeZIssr78a.a', '121411', 'admin', admin_id);
    end if;
end$$
delimiter ;
call create_admin;
```
---

### BNO load to database
```sql
LOAD DATA INFILE 'C:\Users\\skot9\\Desktop\\bno\\bno2.csv' 
IGNORE INTO TABLE `bno` FIELDS TERMINATED BY ';' ENCLOSED BY '"' 
LINES TERMINATED BY '\r\n' IGNORE 1 LINES (`KOD10`,`bno_name`)

