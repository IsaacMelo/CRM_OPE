#################################################################################
------------------------ GRUPOS DE USUÁRIOS -------------------------------------
#################################################################################

INSERT INTO grupo (codigo, nome) VALUES (3, 'Gerente');
INSERT INTO grupo (codigo, nome) VALUES (4, 'Estoquista');
INSERT INTO grupo (codigo, nome) VALUES (5, 'Transportador');

#################################################################################
------------------------ PERMISSÕES DE USUÁRIOS ---------------------------------
#################################################################################

INSERT INTO permissao VALUES (4, 'ROLE_VISUALIZAR_DASHBOARD');
INSERT INTO permissao VALUES (5, 'ROLE_CADASTRAR_CLIENTES');
INSERT INTO permissao VALUES (6, 'ROLE_CADASTRAR_FORNECEDORES');
INSERT INTO permissao VALUES (7, 'ROLE_CADASTRAR_PRODUTOS');
INSERT INTO permissao VALUES (8, 'ROLE_VISUALIZAR_USUARIOS');
INSERT INTO permissao VALUES (9, 'ROLE_CADASTRAR_VENDAS');
INSERT INTO permissao VALUES (10, 'ROLE_VENDAS_FATURADAS');
INSERT INTO permissao VALUES (11, 'ROLE_CADASTRAR_PARAMETROS');
INSERT INTO permissao VALUES (12, 'ROLE_RELATORIO_COMISSOES');
INSERT INTO permissao VALUES (13, 'ROLE_RELATORIO_VENDAS_EMITIDAS');

#################################################################################
------------------------ AMARRANDO GRUPO COM PERMISSÕES ---------------------------------
#################################################################################

--ADMINISTRADOR
INSERT INTO grupo_permissao (codigo_grupo, codigo_permissao) VALUES (1, 4);
INSERT INTO grupo_permissao (codigo_grupo, codigo_permissao) VALUES (1, 5);
INSERT INTO grupo_permissao (codigo_grupo, codigo_permissao) VALUES (1, 6);
INSERT INTO grupo_permissao (codigo_grupo, codigo_permissao) VALUES (1, 7);
INSERT INTO grupo_permissao (codigo_grupo, codigo_permissao) VALUES (1, 8);
INSERT INTO grupo_permissao (codigo_grupo, codigo_permissao) VALUES (1, 9);
INSERT INTO grupo_permissao (codigo_grupo, codigo_permissao) VALUES (1, 10);
INSERT INTO grupo_permissao (codigo_grupo, codigo_permissao) VALUES (1, 11);
INSERT INTO grupo_permissao (codigo_grupo, codigo_permissao) VALUES (1, 12);
INSERT INTO grupo_permissao (codigo_grupo, codigo_permissao) VALUES (1, 13);

--VENDEDOR
INSERT INTO grupo_permissao (codigo_grupo, codigo_permissao) VALUES (2, 5);
INSERT INTO grupo_permissao (codigo_grupo, codigo_permissao) VALUES (2, 8);
INSERT INTO grupo_permissao (codigo_grupo, codigo_permissao) VALUES (2, 9);

-- GERENTE
INSERT INTO grupo_permissao (codigo_grupo, codigo_permissao) VALUES (3, 4);
INSERT INTO grupo_permissao (codigo_grupo, codigo_permissao) VALUES (3, 5);
INSERT INTO grupo_permissao (codigo_grupo, codigo_permissao) VALUES (3, 6);
INSERT INTO grupo_permissao (codigo_grupo, codigo_permissao) VALUES (3, 8);
INSERT INTO grupo_permissao (codigo_grupo, codigo_permissao) VALUES (3, 9);
INSERT INTO grupo_permissao (codigo_grupo, codigo_permissao) VALUES (3, 10);
INSERT INTO grupo_permissao (codigo_grupo, codigo_permissao) VALUES (3, 11);
INSERT INTO grupo_permissao (codigo_grupo, codigo_permissao) VALUES (3, 12);
INSERT INTO grupo_permissao (codigo_grupo, codigo_permissao) VALUES (3, 13);

--ESTOQUISTA
INSERT INTO grupo_permissao (codigo_grupo, codigo_permissao) VALUES (4, 7);
INSERT INTO grupo_permissao (codigo_grupo, codigo_permissao) VALUES (4, 8);

--TRANSPORTADOR
INSERT INTO grupo_permissao (codigo_grupo, codigo_permissao) VALUES (5, 10);
INSERT INTO grupo_permissao (codigo_grupo, codigo_permissao) VALUES (5, 8);




