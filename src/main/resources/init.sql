use signhelper;

insert into tbl_user (name, password_encoded, role_name, last_logout_date, last_password_reset_date) 
values('123456789@gmail.com','$2a$10$ue3P3XGH.9rc5.iRug8UF.2oyEm2dOlmAoTMNvlbEgubJHIKhv.LS' ,'ROLE_ADMIN',  STR_TO_DATE('02/25/2019', '%c/%e/%Y %r'),STR_TO_DATE('02/25/2019', '%c/%e/%Y %r'));