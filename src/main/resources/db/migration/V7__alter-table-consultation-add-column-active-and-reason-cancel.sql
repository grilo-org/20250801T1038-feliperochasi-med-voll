alter table consultations add active tinyint;
alter table consultations add reason_cancel varchar(255);
update consultations set active = 1;