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
	cargo VARCHAR(30) NOT NULL,
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

-- Mostre o nome do departamento que tem a
-- menor quantidade de funcionários sem
-- dependentes, com a quantidade de funcionários.
-- ESTÁ ERRADO!
SELECT funcionario.departamento_id, MIN(min_func.qtde_min_func) as qtde_min_func
	FROM funcionario, min_func
	NATURAL INNER JOIN min_func 
	WHERE funcionario.departamento_id IN (
			SELECT departamento_id, COUNT(departamento_id) as qtde_min_func
				FROM funcionario 
				WHERE qtde_dependentes = (SELECT MIN(qtde_dependentes) FROM funcionario)
				GROUP BY departamento_id) AS min_func;

-- Mostre o nome do departamento com os
-- nomes dos seus respectivos funcionários de todos
-- os departamentos cujo nome começa com “DIR”.
CREATE VIEW departamentos_nome_dir AS
	SELECT d.nome AS nome_departamento, f.nome AS nome_funcionario 
		FROM departamento AS d, funcionario AS f
		WHERE d.nome LIKE 'dir%' 
		AND d.codigo = f.departamento_id;

-- Mostre o nome do funcionário e do
-- departamento ao qual pertence o funcionário com
-- o maior salário
CREATE VIEW departamento_maior_salario AS 
	SELECT d.nome AS departamento, f.nome AS funcionario, MAX(f.salario) AS salario
		FROM funcionario AS f, departamento as d
		WHERE d.codigo = f.departamento_id
		GROUP BY d.nome, f.nome
		HAVING MAX(f.salario) = (
			SELECT MAX(salario) FROM funcionario
		);