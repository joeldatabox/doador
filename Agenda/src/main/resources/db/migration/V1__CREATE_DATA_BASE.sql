Create table agenda (
	id Bigint NOT NULL AUTO_INCREMENT,
	id_hemocentro Bigint NOT NULL,
	observacao Longtext,
	qtde_leito Int NOT NULL,
 Primary Key (id)) ENGINE = InnoDB
DEFAULT CHARACTER SET utf8 COLLATE utf8_general_ci;

Create table dia_atendimento (
	id Bigint NOT NULL AUTO_INCREMENT,
	id_agenda Bigint NOT NULL,
	dia Enum('DOMINGO','SEGUNDA','TERCA','QUINTA','SEXTA','SABADO') NOT NULL,
	hr_ini_atendimento Varchar(40) NOT NULL,
	hr_end_atendimento Varchar(40) NOT NULL,
 Primary Key (id)) ENGINE = InnoDB
DEFAULT CHARACTER SET utf8 COLLATE utf8_general_ci;

Create table agendamento (
	id Bigint NOT NULL AUTO_INCREMENT,
	id_agenda Bigint NOT NULL,
	id_paciente Bigint NOT NULL,
	dt_agendamento Timestamp,
 Primary Key (id)) ENGINE = InnoDB
DEFAULT CHARACTER SET utf8 COLLATE utf8_general_ci;


Alter table dia_atendimento add Foreign Key (id_agenda) references agenda (id) on delete  restrict on update  restrict;
Alter table agendamento add Foreign Key (id_agenda) references agenda (id) on delete  restrict on update  restrict;


/* Users permissions */


