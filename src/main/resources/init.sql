use signhelper;

insert into tbl_user (name, password_encoded, role_name, last_logout_date, last_password_reset_date) 
values('123456789@gmail.com','$2a$10$LPsiFAiYaazfnvkjc25wYu4mQ61S7XWEAN86pIQl0mLsjn25mLU7.' ,'ROLE_ADMIN',  STR_TO_DATE('02/25/2019', '%c/%e/%Y %r'),STR_TO_DATE('02/25/2019', '%c/%e/%Y %r'));