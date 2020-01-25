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
select c.nome_cantor, count(g.cod_cantor) as num_gravadora 
	from cantor as c, gravacao as g 
	where c.cod_cantor = g.cod_cantor
	group by c.nome_cantor
	having count(g.cod_cantor) = (select max(contagem.total) from (
		select count(gravacoes.cod_cantor) as total from (
			select distinct cod_gravadora, cod_cantor from gravacao
		) as gravacoes group by gravacoes.cod_cantor
	) as contagem);

-- Exercicio 3	
select g.cod_cantor, avg(m.duracao) as media_duracao 
	from gravacao as g, musica as m
	where g.cod_musica = m.cod_musica
 	group by g.cod_cantor;

-- Exercicio 4
-- Query statement
select nome_cantor from cantor where cantor.cod_cantor not in (
	select cod_cantor from gravacao where gravacao.cod_gravadora = (
		select cod_gravadora from gravadora where nome_gravadora = 'Sony'
	)
);
-- Query result:
-- +---------------+
-- | nome_cantor   |
-- +---------------+
-- | Djavan        |
-- +---------------+
-- | Laura Pausini |
-- +---------------+
-- | Roberto Leal  |
-- +---------------+
-- | The Corrs     |
-- +---------------+
-- | Legi�o Urbana |
-- +---------------+
-- | Cazuza        |
-- +---------------+
-- | Tom Jobim     |
-- +---------------+

-- Exercicio 5:
-- Query statement:
select distinct cantor.nome_cantor as cantor, musica.titulo as musica, gravacao.data_gravacao
	from cantor, musica natural inner join gravacao
	where cantor.cod_cantor in (select cod_cantor from gravacao where date_part('YEAR', data_gravacao) = 2004)
	and musica.cod_musica in (select cod_musica from gravacao where date_part('YEAR', data_gravacao) = 2004);
-- Query result:
-- +-------------+-----------------------------+---------------+
-- | cantor      | musica                      | data_gravacao |
-- +-------------+-----------------------------+---------------+
-- | Coldplay    | A Rush of Blood to the head | 2004-08-30    |
-- +-------------+-----------------------------+---------------+
-- | Coldplay    | Clocks                      | 2004-10-01    |
-- +-------------+-----------------------------+---------------+
-- | Coldplay    | Green eyes                  | 2004-09-20    |
-- +-------------+-----------------------------+---------------+
-- | Coldplay    | Politik                     | 2004-09-12    |
-- +-------------+-----------------------------+---------------+

-- Exercicio 6:
-- Query statement:
select c.nome_cantor, 
	case 
		when max(g.data_gravacao) is null then '' 
		else to_char(max(g.data_gravacao),'DD/MM/YYYY') 
	end as data_ultima_gravacao
	from cantor as c
	left join gravacao as g
	on c.cod_cantor = g.cod_cantor
	group by c.nome_cantor
	order by max(g.data_gravacao) desc;
-- Query result:
-- +----------------+----------------------+
-- | nome_cantor    | data_ultima_gravacao |
-- +----------------+----------------------+
-- | The Corrs      |                      |
-- +----------------+----------------------+
-- | Legi�o Urbana  | 29/12/2005           |
-- +----------------+----------------------+
-- | Coldplay       | 01/10/2004           |
-- +----------------+----------------------+
-- | Marisa Monte   | 30/05/2001           |
-- +----------------+----------------------+
-- | Laura Pausini  | 10/10/1998           |
-- +----------------+----------------------+
-- | Djavan         | 20/01/1995           |
-- +----------------+----------------------+
-- | U2             | 25/03/1992           |
-- +----------------+----------------------+
-- | Roberto Leal   | 30/07/1988           |
-- +----------------+----------------------+
-- | Cazuza         | 06/07/1987           |
-- +----------------+----------------------+
-- | Tom Jobim      | 18/04/1981           |
-- +----------------+----------------------+
-- | Frank Sinatra  | 29/08/1971           |
-- +----------------+----------------------+

-- Exercicio 7
-- Query statement:
select distinct p.nome_pessoa, 
	(select f.numero as fone_residencial from fone as f where tipo = 'R' and p.cod_pessoa = f.cod_pessoa),
	(select f.numero as fone_comercial from fone as f where tipo = 'C' and p.cod_pessoa = f.cod_pessoa),
	(select f.numero as celular from fone as f where tipo = 'L' and p.cod_pessoa = f.cod_pessoa)
	from pessoa as p, fone as f where p.cod_pessoa = f.cod_pessoa;
-- Query result:
-- +-------------+------------------+----------------+-----------+
-- | nome_pessoa | fone_residencial | fone_comercial | celular   |
-- +-------------+------------------+----------------+-----------+
-- | Ana         | 3333-1111        | 4444-1111      | 9999-1111 |
-- +-------------+------------------+----------------+-----------+
-- | Beto        | 3333-2222        |4444-2222       | 9999-2222 |
-- +-------------+------------------+----------------+-----------+
-- | Carlos      | 3333-3333        |4444-3333       | 8888-3333 |
-- +-------------+------------------+----------------+-----------+