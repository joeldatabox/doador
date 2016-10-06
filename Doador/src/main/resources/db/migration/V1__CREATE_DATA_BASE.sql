
Create table paciente (
	id Bigint NOT NULL AUTO_INCREMENT,
	nome Varchar(255),
	cpf Varchar(20) NOT NULL,
	rg Varchar(30) NOT NULL,
	nascimento Date NOT NULL,
	nome_mae Varchar(255) NOT NULL,
	nome_pai Varchar(255),
	observacao Longtext,
	UNIQUE (cpf),
 Primary Key (id)) ENGINE = InnoDB;

Create table endereco (
	id Bigint NOT NULL AUTO_INCREMENT,
	id_paciente Bigint NOT NULL,
	descricao Varchar(250),
	cep Varchar(10),
	bairro Varchar(80),
	cidade Varchar(80),
	observacao Longtext,
 Primary Key (id)) ENGINE = InnoDB;


Alter table endereco add Foreign Key (id_paciente) references paciente (id) on delete  restrict on update  restrict;