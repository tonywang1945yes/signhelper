use signhelper;

insert into tbl_student (email, name, password_hash, student_state, identity_num, visa_num, birth_date, tel, address, high_school, last_logout_date, last_password_reset_date)
values ('x950031@gmail.com', '蔡尚達','$2a$10$i7iX8q5h6Qd5S9L25qgoCOIce4O0NsttzvcVCj/V4PYTxhosB.nOG', 'NULL', 'A123456789', '11223344', STR_TO_DATE('11/11/1999', '%c/%e/%Y %r'), 
'0229337905', '台北市文山區興隆路4段2號', '台北市私立再興中學',  STR_TO_DATE('01/01/2000', '%c/%e/%Y %r'), STR_TO_DATE('01/01/2000', '%c/%e/%Y %r')); 

insert into tbl_student (email, name, password_hash, student_state, identity_num, visa_num, birth_date, tel, address, high_school, last_logout_date, last_password_reset_date)
values ('superfreeeee@gmail.com', '藍于涵','$2a$10$GFPy7TI/xlF/xeKtZYRpZO.KYZXt2CkN46WNJfo2Ozbz9By3.nz3C', 'NULL', 'B987654321', '87654321', STR_TO_DATE('04/16/2000', '%c/%e/%Y %r'), 
'0489617644', '台北市信義區信義路10號', '北一女中',  STR_TO_DATE('01/01/2000', '%c/%e/%Y %r'), STR_TO_DATE('01/01/2000', '%c/%e/%Y %r')); 

insert into tbl_student (email, name, password_hash, student_state, identity_num, visa_num, birth_date, tel, address, high_school, last_logout_date, last_password_reset_date)
values ('clv123654@yahoo.com.tw', '黎德谦','$2a$10$RiHMRyc90MgPyJ2HYuWIFeyO9FXYaIF3q7zCekFP3.I43e70.xAlS', 'NULL', 'C333222555', '12345678', STR_TO_DATE('10/09/1999', '%c/%e/%Y %r'), 
'0743218765', '台北市永和區中和路40號', '建國中學',  STR_TO_DATE('01/01/2000', '%c/%e/%Y %r'), STR_TO_DATE('01/01/2000', '%c/%e/%Y %r')); 


insert into tbl_application_form (id,accept_assignment_value,address,art_or_sci,birth_date,first_choice,fourth_choice,second_choice,third_choice,first_name,graduation_year,
gsat_result_chinese,gsat_result_english,gsat_result_math,gsat_result_socialogy,gsat_result_sciences,high_school,identity_num,last_name,home_phone_number,mobile_phone_number,
fax_number,postal_code,school_attended_junior_middle_school_end_date,school_attended_junior_middle_school_region,school_attended_junior_middle_school_school,
school_attended_junior_middle_school_start_date,school_attended_primary_school_end_date,school_attended_primary_school_region,school_attended_primary_school_school,
school_attended_primary_school_start_date,school_attended_senior_middle_school_end_date,school_attended_senior_middle_school_region,school_attended_senior_middle_school_school,
school_attended_senior_middle_school_start_date,sex,visa_num)
values (1,0,'台北市文山區興隆路4段2號',1, STR_TO_DATE('11/11/1999', '%c/%e/%Y %r'),'軟件工程','物理學','電子信息科學與技術','信息與計算科學','蔡',2018,14,15,14,14,12,'台北市私立再興中學',
'A123456789','尚達','0229337905','+886905100114','+886229337905','116',STR_TO_DATE('06/30/2015', '%c/%e/%Y %r'),'台北市','再興中學',STR_TO_DATE('09/01/2012', '%c/%e/%Y %r'),STR_TO_DATE('06/30/2012', '%c/%e/%Y %r'),'台北市','興隆國小',STR_TO_DATE('09/01/2006', '%c/%e/%Y %r'),
STR_TO_DATE('06/30/2018', '%c/%e/%Y %r'),'台北市','再興中學',STR_TO_DATE('09/01/2015', '%c/%e/%Y %r'),1,'11223344'); 

insert into tbl_application_form (id,accept_assignment_value,address,art_or_sci,birth_date,first_choice,fourth_choice,second_choice,third_choice,first_name,graduation_year,
gsat_result_chinese,gsat_result_english,gsat_result_math,gsat_result_socialogy,gsat_result_sciences,high_school,identity_num,last_name,home_phone_number,mobile_phone_number,
fax_number,postal_code,school_attended_junior_middle_school_end_date,school_attended_junior_middle_school_region,school_attended_junior_middle_school_school,
school_attended_junior_middle_school_start_date,school_attended_primary_school_end_date,school_attended_primary_school_region,school_attended_primary_school_school,
school_attended_primary_school_start_date,school_attended_senior_middle_school_end_date,school_attended_senior_middle_school_region,school_attended_senior_middle_school_school,
school_attended_senior_middle_school_start_date,sex,visa_num)
values(2,1,'台北市信義區信義路10號',0,STR_TO_DATE('04/16/2000', '%c/%e/%Y %r'),'西班牙語','','法語','','藍',2017,15,15,13,14,15,'北一女中','B987654321','于涵','0489617644','+886956189008','+886489617644','103',STR_TO_DATE('06/30/2009', '%c/%e/%Y %r'),'基隆縣','再興中學',
STR_TO_DATE('09/01/2006', '%c/%e/%Y %r'),STR_TO_DATE('06/30/2006', '%c/%e/%Y %r'),'新竹市','及人國小',STR_TO_DATE('09/01/2000', '%c/%e/%Y %r'),STR_TO_DATE('06/30/2012', '%c/%e/%Y %r'),'花蓮縣','北一女中',STR_TO_DATE('09/01/2009', '%c/%e/%Y %r'),0,'87654321');

insert into tbl_application_form (id,accept_assignment_value,address,art_or_sci,birth_date,first_choice,fourth_choice,second_choice,third_choice,first_name,graduation_year,
gsat_result_chinese,gsat_result_english,gsat_result_math,gsat_result_socialogy,gsat_result_sciences,high_school,identity_num,last_name,home_phone_number,mobile_phone_number,
fax_number,postal_code,school_attended_junior_middle_school_end_date,school_attended_junior_middle_school_region,school_attended_junior_middle_school_school,
school_attended_junior_middle_school_start_date,school_attended_primary_school_end_date,school_attended_primary_school_region,school_attended_primary_school_school,
school_attended_primary_school_start_date,school_attended_senior_middle_school_end_date,school_attended_senior_middle_school_region,school_attended_senior_middle_school_school,
school_attended_senior_middle_school_start_date,sex,visa_num)
values(3,1,'台北市永和區中和路40號',1,STR_TO_DATE('10/09/1999', '%c/%e/%Y %r'),'自動化','','通信工程','海洋科學','黎',2006,15,15,14,15,15,'建國中學','C333222555','德謙',
'0743218765','886987456123','+886743218765','111',STR_TO_DATE('06/30/2011', '%c/%e/%Y %r'),'屏東縣','萬芳國中',STR_TO_DATE('09/01/2008', '%c/%e/%Y %r'),
STR_TO_DATE('06/30/2008', '%c/%e/%Y %r'),'高雄市','興德國小',STR_TO_DATE('09/01/2002', '%c/%e/%Y %r'),STR_TO_DATE('06/30/2014', '%c/%e/%Y %r'),'彰化縣','建國中學',STR_TO_DATE('09/01/2011', '%c/%e/%Y %r'),1,12345678);
