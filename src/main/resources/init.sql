use signhelper;

DROP TABLE IF EXISTS `tbl_student`;

create table tbl_student
(
email varchar(50) not null,
name varchar(50) not null,
student_state varchar(50) not null,
identity_num varchar(50) not null,
visa_num varchar(50) not null,
password_hash varchar(100) not null,
birth_date timestamp ,
tel varchar(50) not null,
address varchar(100) not null,
high_school varchar(50) not null,
last_logout_date timestamp,
last_password_reset_date timestamp,
PRIMARY KEY (email)
);

insert into tbl_student (email, name, password_hash, student_state, identity_num, visa_num, birth_date, tel, address, high_school, last_logout_date, last_password_reset_date)
values ('x950031@gmail.com', '蔡尚達','$2y$16$a7h6DloiV0U3.9wNGrWXH.CjJE8PUy4ZgU3S8xBWIhmr1LKZTiwxK', 'StudentState.NULL', 'A123456789', '11223344', STR_TO_DATE('11/11/1999', '%c/%e/%Y %r'), 
'0229337905', '台北市文山區興隆路4段2號', '台北市私立再興中學',  STR_TO_DATE('01/01/2000', '%c/%e/%Y %r'), STR_TO_DATE('01/01/2000', '%c/%e/%Y %r')); 

insert into tbl_student (email, name, password_hash, student_state, identity_num, visa_num, birth_date, tel, address, high_school, last_logout_date, last_password_reset_date)
values ('superfreeeee@gmail.com', '藍于涵','$2y$16$Co47pt8gMeV90C2GuVDsmuYbmGIrVSyqfd.OkiAdPAhK9xaczsdbC', 'StudentState.NULL', 'B987654321', '87654321', STR_TO_DATE('04/16/2000', '%c/%e/%Y %r'), 
'0489617644', '台北市信義區信義路10號', '北一女中',  STR_TO_DATE('01/01/2000', '%c/%e/%Y %r'), STR_TO_DATE('01/01/2000', '%c/%e/%Y %r')); 

insert into tbl_student (email, name, password_hash, student_state, identity_num, visa_num, birth_date, tel, address, high_school, last_logout_date, last_password_reset_date)
values ('clv123654@yahoo.com.tw', '黎德谦','$2y$16$76umr6rxgNFGAX5TKp929uhPskbBpsXZ8VIk2HaxRSpudhMl3UZrW', 'StudentState.NULL', 'C333222555', '12345678', STR_TO_DATE('10/09/1999', '%c/%e/%Y %r'), 
'0743218765', '台北市永和區中和路40號', '建國中學',  STR_TO_DATE('01/01/2000', '%c/%e/%Y %r'), STR_TO_DATE('01/01/2000', '%c/%e/%Y %r')); 
