-- Massa de dados utilizada na atividade 2
-- @author Luis Daniel Assulfi

-- Massa de dados da tabela de departamentos
INSERT INTO departamento(nome) VALUES
    ('vendas'),
    ('recursos humanos'),
    ('suporte'),
    ('diretoria'),
    ('dirigencia');

-- Massa de dados da tabela funcionarios
insert into funcionario (nome, qtde_dependentes, salario, departamento_id, cargo) values ('Mischa Fullerlove', 1, 5503.56, 2, 'operador');
insert into funcionario (nome, qtde_dependentes, salario, departamento_id, cargo) values ('Hyacinthia Lanfere', 0, 5882.21, 2, 'operador');
insert into funcionario (nome, qtde_dependentes, salario, departamento_id, cargo) values ('Clim Terrelly', 4, 4165.18, 1, 'operador');
insert into funcionario (nome, qtde_dependentes, salario, departamento_id, cargo) values ('Clay O'' Dornan', 3, 7603.33, 2, 'operador');
insert into funcionario (nome, qtde_dependentes, salario, departamento_id, cargo) values ('Silas Eason', 1, 9143.41, 1, 'operador');
insert into funcionario (nome, qtde_dependentes, salario, departamento_id, cargo) values ('Eugenia Cosley', 0, 6137.76, 2, 'operador');
insert into funcionario (nome, qtde_dependentes, salario, departamento_id, cargo) values ('Windham Kilgallon', 5, 8481.17, 3, 'operador');
insert into funcionario (nome, qtde_dependentes, salario, departamento_id, cargo) values ('Reeta Tinto', 4, 5573.86, 2, 'operador');
insert into funcionario (nome, qtde_dependentes, salario, departamento_id, cargo) values ('Arney Cowland', 3, 6797.36, 3, 'operador');
insert into funcionario (nome, qtde_dependentes, salario, departamento_id, cargo) values ('Justinn Gabitis', 4, 4640.04, 1, 'operador');
insert into funcionario (nome, qtde_dependentes, salario, departamento_id, cargo) values ('Ludovico Robelin', 0, 2682.71, 3, 'operador');
insert into funcionario (nome, qtde_dependentes, salario, departamento_id, cargo) values ('Hagen Hinkes', 5, 5597.68, 1, 'operador');
insert into funcionario (nome, qtde_dependentes, salario, departamento_id, cargo) values ('Henrietta Gorvette', 4, 3164.99, 2, 'operador');
insert into funcionario (nome, qtde_dependentes, salario, departamento_id, cargo) values ('Valaria Alennikov', 4, 6233.63, 3, 'operador');
insert into funcionario (nome, qtde_dependentes, salario, departamento_id, cargo) values ('Sibella Skettles', 4, 5190.12, 1, 'operador');
insert into funcionario (nome, qtde_dependentes, salario, departamento_id, cargo) values ('Trix Giamuzzo', 1, 1467.29, 2, 'operador');
insert into funcionario (nome, qtde_dependentes, salario, departamento_id, cargo) values ('Timothee Dagg', 1, 8081.07, 1, 'operador');
insert into funcionario (nome, qtde_dependentes, salario, departamento_id, cargo) values ('Julie De Paepe', 1, 4352.01, 2, 'operador');
insert into funcionario (nome, qtde_dependentes, salario, departamento_id, cargo) values ('Hurleigh Leeke', 4, 1960.77, 3, 'operador');
insert into funcionario (nome, qtde_dependentes, salario, departamento_id, cargo) values ('Hattie Bartolomeu', 4, 9837.4, 2, 'operador');
insert into funcionario (nome, qtde_dependentes, salario, departamento_id, cargo) values ('Jaye Yardley', 2, 6651.22, 3, 'operador');
insert into funcionario (nome, qtde_dependentes, salario, departamento_id, cargo) values ('Elmer Chater', 2, 5421.27, 2, 'operador');
insert into funcionario (nome, qtde_dependentes, salario, departamento_id, cargo) values ('Jaynell Haly', 2, 7056.25, 3, 'operador');
insert into funcionario (nome, qtde_dependentes, salario, departamento_id, cargo) values ('Elnore Ericsson', 4, 8994.84, 2, 'operador');
insert into funcionario (nome, qtde_dependentes, salario, departamento_id, cargo) values ('Chad Fleetham', 1, 8829.31, 1, 'operador');
insert into funcionario (nome, qtde_dependentes, salario, departamento_id, cargo) values ('Marcy Ripping', 0, 2340.07, 1, 'operador');
insert into funcionario (nome, qtde_dependentes, salario, departamento_id, cargo) values ('Retha Kincey', 0, 4880.32, 2, 'operador');
insert into funcionario (nome, qtde_dependentes, salario, departamento_id, cargo) values ('Piper Rigard', 5, 3857.91, 3, 'gerente');
insert into funcionario (nome, qtde_dependentes, salario, departamento_id, cargo) values ('Missy Basso', 3, 2142.78, 3, 'diretor');
insert into funcionario (nome, qtde_dependentes, salario, departamento_id, cargo) values ('Trenton Shuttleworth', 0, 4943.68, 3, 'gerente');

-- Resultados esperados das consultas das views

-- 1 - Mostre o nome do departamento que tem o
-- maior número de funcionários, mostrando também
-- a quantidade de funcionários desse departamento.
-- +------------------+-------------------------+
-- | nome             | quantidade_funcionarios |
-- +------------------+-------------------------+
-- | recursos humanos | 12                      |
-- +------------------+-------------------------+

-- 2 - Mostre o nome do departamento que tem a
-- menor quantidade de funcionários sem
-- dependentes, com a quantidade de funcionários.
-- +------------------+--------------+
-- | departamento     | funcionarios |
-- +------------------+--------------+
-- | recursos humanos | 3            |
-- +------------------+--------------+
-- | vendas           | 3            |
-- +------------------+--------------+

-- 3 - Mostre o nome do departamento com os
-- nomes dos seus respectivos funcionários de todos
-- os departamentos cujo nome começa com “DIR”.
-- +-------------------+------------------+
-- | nome_departamento | nome_funcionario |
-- +-------------------+------------------+
-- | suporte           | Missy Basso      |
-- +-------------------+------------------+

-- 4 - Mostre o nome do funcionário e do
-- departamento ao qual pertence o funcionário com
-- o maior salário.
-- +------------------+-------------------+---------+
-- | departamento     | funcionario       | salario |
-- +------------------+-------------------+---------+
-- | recursos humanos | Hattie Bartolomeu | 9837.40 |
-- +------------------+-------------------+---------+

-- 5 - Mostre o nome do departamento e do
-- funcionário de cada departamento que tem o cargo
-- de “Gerente”.
-- +--------------+----------------------+
-- | departamento | funcionario          |
-- +--------------+----------------------+
-- | suporte      | Piper Rigard         |
-- +--------------+----------------------+
-- | suporte      | Trenton Shuttleworth |
-- +--------------+----------------------+