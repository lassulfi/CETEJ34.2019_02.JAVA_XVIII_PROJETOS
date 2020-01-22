show search_path;
set search_path to dml;
 
-- Exercicio 1
-- Query statement:
select c.nome_cantor, count(g.cod_cantor) as num_gravacoes 
	from cantor as c, gravacao as g 
	where c.cod_cantor = g.cod_cantor
	group by c.nome_cantor
	having count(g.cod_cantor) = (select min(gravacoes.total) 
		from (select count(cod_cantor) as total from gravacao group by cod_gravacao)
		as gravacoes
	);
-- Query result:
-- +---------------+---------------+ 
-- | nome_cantor   | num_gravacoes |
-- +---------------+---------------+ 
-- | Frank Sinatra | 1             |
-- +---------------+---------------+ 
-- | Djavan        | 1             |
-- +---------------+---------------+ 
-- | Laura Pausini | 1             |
-- +---------------+---------------+ 
-- | Roberto Leal  | 1             |
-- +---------------+---------------+ 

-- Exericio 2
select c.nome_cantor, count(g.cod_gravadora) as num_gravadoras
	from cantor as c, gravacao as g
	where c.cod_cantor = g.cod_cantor
	group by c.nome_cantor
	having count(g.cod_gravadora) = 
		(select max(gravacoes.total)
			from (
				select count(cod_cantor) as total from gravacao group by cod_gravacao
			) as gravacoes
		);

-- Exercicio 7
-- Query statement:
select distinct p.nome_pessoa, 
	(select f.numero as fone_residencial from fone as f where tipo = 'R' and p.cod_pessoa = f.cod_pessoa),
	(select f.numero as fone_comercial from fone as f where tipo = 'C' and p.cod_pessoa = f.cod_pessoa),
	(select f.numero as celular from fone as f where tipo = 'L' and p.cod_pessoa = f.cod_pessoa)
	from pessoa as p, fone as f where p.cod_pessoa = f.cod_pessoa;
-- Query result:
-- +-------------+------------------+----------------+-----------+
-- | nome_pessoa | fone_residencial | fone_comercial | celular  |
-- +-------------+------------------+----------------+-----------+
-- | Ana         | 3333-1111        | 4444-1111      | 9999-1111 |
-- +-------------+------------------+----------------+-----------+
-- | Beto        | 3333-2222        |4444-2222       | 9999-2222 |
-- +-------------+------------------+----------------+-----------+
-- | Carlos      | 3333-3333        |4444-3333       | 8888-3333 |
-- +-------------+------------------+----------------+-----------+