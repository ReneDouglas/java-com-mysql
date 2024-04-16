create database banco_java;

use banco_java;

create table funcionarios(
	id int primary key auto_increment not null,
	nome varchar(255) not null,
	data_nasc date not null,
	salario decimal(6,2),
	data_registro datetime(6),
	cadastro_ativo boolean not null
);

INSERT INTO funcionarios (id, nome, data_nasc, salario, data_registro, cadastro_ativo) VALUES(1, 'teste', '2024-04-11', 2500.50, '2024-04-11 16:51:48.593000000', 1);
