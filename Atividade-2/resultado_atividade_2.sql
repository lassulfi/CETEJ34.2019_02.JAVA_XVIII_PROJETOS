-- Create database
CREATE DATABASE empresas
    WITH 
    OWNER = postgres
    ENCODING = 'UTF8'
    CONNECTION LIMIT = -1;

-- Create schema
CREATE SCHEMA empresa
    AUTHORIZATION postgres;

-- Change the search path
SET search_path TO empresa;

-- Create table departamento
CREATE TABLE departamento (
	codigo SERIAL PRIMARY KEY,
	nome VARCHAR(30) NOT NULL
);

-- Create table funcionario
CREATE TABLE funcionario(
	codigo SERIAL PRIMARY KEY,
	nome VARCHAR(80) NOT NULL,
	qtde_dependentes INTEGER NOT NULL,
	salario DECIMAL(7,2) NOT NULL,
	departamento_id INTEGER NOT NULL,
	FOREIGN KEY (departamento_id) REFERENCES departamento(codigo)
);

-- Mostre o nome do departamento que tem o
-- maior número de funcionários, mostrando também
-- a quantidade de funcionários desse departamento.
CREATE VIEW maior_departamento AS 
	SELECT d.nome, COUNT(f.departamento_id) AS quantidade_funcionarios
	FROM departamento d, funcionario f 
	WHERE d.codigo = f.departamento_id
	GROUP BY d.nome
	HAVING COUNT(f.departamento_id) = (
		SELECT MAX(depto.qtde_funcionarios) FROM (
			SELECT COUNT(departamento_id) AS qtde_funcionarios FROM funcionario GROUP BY departamento_id
		) as depto
	);