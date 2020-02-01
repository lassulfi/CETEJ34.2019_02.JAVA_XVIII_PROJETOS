--
-- PostgreSQL database dump
--

-- Dumped from database version 12.1 (Debian 12.1-1.pgdg100+1)
-- Dumped by pg_dump version 12.1 (Debian 12.1-1.pgdg100+1)

-- @author: Luis Daniel Assulfi

SET statement_timeout = 0;
SET lock_timeout = 0;
SET idle_in_transaction_session_timeout = 0;
SET client_encoding = 'UTF8';
SET standard_conforming_strings = on;
SELECT pg_catalog.set_config('search_path', '', false);
SET check_function_bodies = false;
SET xmloption = content;
SET client_min_messages = warning;
SET row_security = off;

--
-- Name: empresa; Type: SCHEMA; Schema: -; Owner: postgres
--

CREATE SCHEMA empresa;


ALTER SCHEMA empresa OWNER TO postgres;

SET default_tablespace = '';

SET default_table_access_method = heap;

--
-- Name: departamento; Type: TABLE; Schema: empresa; Owner: postgres
--

CREATE TABLE empresa.departamento (
    codigo integer NOT NULL,
    nome character varying(30) NOT NULL
);


ALTER TABLE empresa.departamento OWNER TO postgres;

--
-- Name: departamento_codigo_seq; Type: SEQUENCE; Schema: empresa; Owner: postgres
--

CREATE SEQUENCE empresa.departamento_codigo_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE empresa.departamento_codigo_seq OWNER TO postgres;

--
-- Name: departamento_codigo_seq; Type: SEQUENCE OWNED BY; Schema: empresa; Owner: postgres
--

ALTER SEQUENCE empresa.departamento_codigo_seq OWNED BY empresa.departamento.codigo;


--
-- Name: funcionario; Type: TABLE; Schema: empresa; Owner: postgres
--

CREATE TABLE empresa.funcionario (
    codigo integer NOT NULL,
    nome character varying(80) NOT NULL,
    qtde_dependentes integer NOT NULL,
    salario numeric(7,2) NOT NULL,
    cargo character varying(30) NOT NULL,
    departamento_id integer NOT NULL
);


ALTER TABLE empresa.funcionario OWNER TO postgres;

--
-- Name: departamento_maior_salario; Type: VIEW; Schema: empresa; Owner: postgres
--

CREATE VIEW empresa.departamento_maior_salario AS
 SELECT d.nome AS departamento,
    f.nome AS funcionario,
    max(f.salario) AS salario
   FROM empresa.funcionario f,
    empresa.departamento d
  WHERE (d.codigo = f.departamento_id)
  GROUP BY d.nome, f.nome
 HAVING (max(f.salario) = ( SELECT max(funcionario.salario) AS max
           FROM empresa.funcionario));


ALTER TABLE empresa.departamento_maior_salario OWNER TO postgres;

--
-- Name: departamentos_nome_dir; Type: VIEW; Schema: empresa; Owner: postgres
--

CREATE VIEW empresa.departamentos_nome_dir AS
 SELECT d.nome AS nome_departamento,
    f.nome AS nome_funcionario
   FROM empresa.departamento d,
    empresa.funcionario f
  WHERE (((f.cargo)::text ~~ 'dir%'::text) AND (d.codigo = f.departamento_id));


ALTER TABLE empresa.departamentos_nome_dir OWNER TO postgres;

--
-- Name: depto_func_menor_qtde_depend; Type: VIEW; Schema: empresa; Owner: postgres
--

CREATE VIEW empresa.depto_func_menor_qtde_depend AS
 SELECT d.nome AS departamento,
    count(f.codigo) AS funcionarios
   FROM empresa.departamento d,
    empresa.funcionario f
  WHERE ((f.qtde_dependentes = ( SELECT min(min_func_table.qtde_min_func) AS min
           FROM ( SELECT count(funcionario.departamento_id) AS qtde_min_func
                   FROM empresa.funcionario
                  WHERE (funcionario.qtde_dependentes = ( SELECT min(funcionario_1.qtde_dependentes) AS min
                           FROM empresa.funcionario funcionario_1))
                  GROUP BY funcionario.departamento_id) min_func_table)) AND (d.codigo = f.departamento_id))
  GROUP BY d.nome;


ALTER TABLE empresa.depto_func_menor_qtde_depend OWNER TO postgres;

--
-- Name: funcionario_codigo_seq; Type: SEQUENCE; Schema: empresa; Owner: postgres
--

CREATE SEQUENCE empresa.funcionario_codigo_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE empresa.funcionario_codigo_seq OWNER TO postgres;

--
-- Name: funcionario_codigo_seq; Type: SEQUENCE OWNED BY; Schema: empresa; Owner: postgres
--

ALTER SEQUENCE empresa.funcionario_codigo_seq OWNED BY empresa.funcionario.codigo;


--
-- Name: gerentes_departamentos; Type: VIEW; Schema: empresa; Owner: postgres
--

CREATE VIEW empresa.gerentes_departamentos AS
 SELECT d.nome AS departamento,
    f.nome AS funcionario
   FROM empresa.departamento d,
    empresa.funcionario f
  WHERE (((f.cargo)::text ~~ 'gerente'::text) AND (d.codigo = f.departamento_id));


ALTER TABLE empresa.gerentes_departamentos OWNER TO postgres;

--
-- Name: maior_departamento; Type: VIEW; Schema: empresa; Owner: postgres
--

CREATE VIEW empresa.maior_departamento AS
 SELECT d.nome,
    count(f.departamento_id) AS quantidade_funcionarios
   FROM empresa.departamento d,
    empresa.funcionario f
  WHERE (d.codigo = f.departamento_id)
  GROUP BY d.nome
 HAVING (count(f.departamento_id) = ( SELECT max(depto.qtde_funcionarios) AS max
           FROM ( SELECT count(funcionario.departamento_id) AS qtde_funcionarios
                   FROM empresa.funcionario
                  GROUP BY funcionario.departamento_id) depto));


ALTER TABLE empresa.maior_departamento OWNER TO postgres;

--
-- Name: departamento codigo; Type: DEFAULT; Schema: empresa; Owner: postgres
--

ALTER TABLE ONLY empresa.departamento ALTER COLUMN codigo SET DEFAULT nextval('empresa.departamento_codigo_seq'::regclass);


--
-- Name: funcionario codigo; Type: DEFAULT; Schema: empresa; Owner: postgres
--

ALTER TABLE ONLY empresa.funcionario ALTER COLUMN codigo SET DEFAULT nextval('empresa.funcionario_codigo_seq'::regclass);


--
-- Data for Name: departamento; Type: TABLE DATA; Schema: empresa; Owner: postgres
--

COPY empresa.departamento (codigo, nome) FROM stdin;
1	vendas
2	recursos humanos
3	suporte
7	diretoria
8	dirigencia
\.


--
-- Data for Name: funcionario; Type: TABLE DATA; Schema: empresa; Owner: postgres
--

COPY empresa.funcionario (codigo, nome, qtde_dependentes, salario, cargo, departamento_id) FROM stdin;
1	Mischa Fullerlove	1	5503.56	operador	2
2	Hyacinthia Lanfere	0	5882.21	operador	2
3	Clim Terrelly	4	4165.18	operador	1
4	Clay O' Dornan	3	7603.33	operador	2
5	Silas Eason	1	9143.41	operador	1
6	Eugenia Cosley	0	6137.76	operador	2
7	Windham Kilgallon	5	8481.17	operador	3
8	Reeta Tinto	4	5573.86	operador	2
9	Arney Cowland	3	6797.36	operador	3
10	Justinn Gabitis	4	4640.04	operador	1
11	Ludovico Robelin	0	2682.71	operador	3
12	Hagen Hinkes	5	5597.68	operador	1
13	Henrietta Gorvette	4	3164.99	operador	2
14	Valaria Alennikov	4	6233.63	operador	3
15	Sibella Skettles	4	5190.12	operador	1
16	Trix Giamuzzo	1	1467.29	operador	2
17	Timothee Dagg	1	8081.07	operador	1
18	Julie De Paepe	1	4352.01	operador	2
19	Hurleigh Leeke	4	1960.77	operador	3
20	Hattie Bartolomeu	4	9837.40	operador	2
21	Jaye Yardley	2	6651.22	operador	3
22	Elmer Chater	2	5421.27	operador	2
23	Jaynell Haly	2	7056.25	operador	3
24	Elnore Ericsson	4	8994.84	operador	2
25	Chad Fleetham	1	8829.31	operador	1
26	Marcy Ripping	0	2340.07	operador	1
27	Retha Kincey	0	4880.32	operador	2
28	Piper Rigard	5	3857.91	gerente	3
29	Missy Basso	3	2142.78	diretor	3
30	Trenton Shuttleworth	0	4943.68	gerente	3
\.


--
-- Name: departamento_codigo_seq; Type: SEQUENCE SET; Schema: empresa; Owner: postgres
--

SELECT pg_catalog.setval('empresa.departamento_codigo_seq', 10, true);


--
-- Name: funcionario_codigo_seq; Type: SEQUENCE SET; Schema: empresa; Owner: postgres
--

SELECT pg_catalog.setval('empresa.funcionario_codigo_seq', 30, true);


--
-- Name: departamento departamento_pkey; Type: CONSTRAINT; Schema: empresa; Owner: postgres
--

ALTER TABLE ONLY empresa.departamento
    ADD CONSTRAINT departamento_pkey PRIMARY KEY (codigo);


--
-- Name: funcionario funcionario_pkey; Type: CONSTRAINT; Schema: empresa; Owner: postgres
--

ALTER TABLE ONLY empresa.funcionario
    ADD CONSTRAINT funcionario_pkey PRIMARY KEY (codigo);


--
-- Name: funcionario funcionario_departamento_id_fkey; Type: FK CONSTRAINT; Schema: empresa; Owner: postgres
--

ALTER TABLE ONLY empresa.funcionario
    ADD CONSTRAINT funcionario_departamento_id_fkey FOREIGN KEY (departamento_id) REFERENCES empresa.departamento(codigo);


--
-- PostgreSQL database dump complete
--
