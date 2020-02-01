-- Crie o usuário “funcionario”, com acesso limitado
CREATE USER funcionario WITH 
	LOGIN
	NOSUPERUSER
	NOCREATEDB
	NOCREATEROLE
	PASSWORD 'Mudar;123';

-- Crie o usuário “gerente”, com acesso completo
CREATE USER gerente WITH 
	LOGIN
	SUPERUSER
	CREATEDB
	CREATEROLE
	PASSWORD 'Mudar;123';