insert into users(id,password,username,enabled,is_admin) values(3,'$2a$12$aBB1sVby02GmP/fv8FGZ6.48JGdRfDpHIIYfg/qL112Bp6agtSDZC','kevink',true,true);
insert into users(id,password,username,enabled,is_admin) values(4,'$2a$12$14.hGgqYww/JtRtoDZVUG.TYFQkGNx7FcWL/TSjXl31WDOdrfhZJq','kev123',true,true);
insert into users(id,password,username,enabled,is_admin) values(5,'$2a$12$1xi9EwSZKxnluYBEJ0skjuz96E/62zgxBggGuQPnS0JwBK7b.vuxC','kevinkad',true,true);


insert into authorities(id,authority,username) values(3,'ROLE_ADMIN','kevinkad');
insert into authorities(id,authority,username) values(4,'ROLE_USER','kev123');
insert into authorities(id,authority,username) values(4,'ROLE_USER','kevink');

