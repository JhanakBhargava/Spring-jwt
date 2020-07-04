insert into location (id,location) values ('X122413','Gurugram');
insert into location (id,location) values ('X411002','Pune');
insert into location (id,location) values ('X560002','Bangalore');
commit;

INSERT INTO users (id,username,password,active,roles,first_name,last_name,location_id) VALUES('3927985','jhanak.bhargava@xebia.com','password',True,'ROLE_SUPERADMIN','Jhanak','Bhargava','X122413');
INSERT INTO users (id,username,password,active,roles,first_name,last_name,location_id) VALUES('1530815','mudassir.shaikh@xebia.com','password',True,'ROLE_ADMIN','Mudassir','Shaikh','X122413');
INSERT INTO users (id,username,password,active,roles,first_name,last_name,location_id) VALUES('2969368','raghav.sindhwani@xebia.com','password',True,'ROLE_SUPERADMIN','Raghav','Sindhwani','X560002');
INSERT INTO users (id,username,password,active,roles,first_name,last_name,location_id) VALUES('6190459','kamal.kumar@xebia.com','password',True,'ROLE_ADMIN','Kamal','Kumar','X411002');
--INSERT INTO users (id,username,password,active,roles,first_name,last_name,location_id) VALUES('1329542','yogesh.srivastava@xebia.com','password',True,'ROLE_SUPERADMIN,'Yogesh','Srivastava','X122413');
commit;

