################################################################################################################################################
# TABELA CATEGORIA
################################################################################################################################################
INSERT INTO `categoria` VALUES (3,'Descartáveis');
################################################################################################################################################
# TABELA CLIENTE
################################################################################################################################################
INSERT INTO `cliente` VALUES (1,'João e Levi Pães e Doces Ltda','JURIDICA','06873673000132','(11) 3739-8326','compras@joaolevi.com.br',1,'Rua dos Caras','175','','Salto de São José','13.324-271','Salto','SP'),(2,'Bernardo e Fernanda Limpeza','JURIDICA','25691765000146','(15) 2810-7699','compras@bernardofernanda.com.br',1,'Rua Esper Hadad','819','','Parque das Paineiras','18.078-635','Sorocaba','SP'),(3,'Pietro e Gustavo Lavanderia Ltda','JURIDICA','28800851000138','(11) 3569-8130','producao@pietrogustavo.com.br',1,'Rua Araruama','114','','Centro','09.910-320','Diadema','SP'),(4,'Mariana e Juliana Padaria Ltda','JURIDICA','31881345000125','(16) 3667-1383','compras@marianajuliana.com.br',1,'Rua Herculano de Oliveira','482','','Conjunto Habitacional Victório de Santi','14.808-279','Araraquara','SP'),(5,'Isabelle e Kevin Restaurante Ltda','JURIDICA','27910346000183','(11) 2577-7742','compras@isabellekevin.com.br',1,'Avenida Santo Amaro','535','','Brooklin Paulista','04.556-300','São Paulo','SP'),(6,'Caroline e Filipe Descartáveis ME','JURIDICA','46186319000154','(11) 2735-3625','compras@carolinefilipe.com.br',1,'Rua São Vicente de Paulo','546','','Santa Cecília','01.229-905','São Paulo','SP'),(7,'Isabelle e Benício Rstaurantes ME','JURIDICA','69345021000145','(19) 2676-6381','compras@isabellebenicio.com.br',1,'Rua Luiz Gama','835','','Jardim São Domingos','13.471-130','Americana','SP');
################################################################################################################################################
# TABELA FORNECEDOR
################################################################################################################################################
INSERT INTO `fornecedor` VALUES (1,'Martecclear - Produto de Limpeza Descartáveis','JURIDICA','19516275000100','(11) 3872-9886','vendas@deboradanilo.com.br',1,'Rua São João','330','','Jardim Irene','07.134-280','Guarulhos','SP'),(2,'Leonardo e Lívia - Descartáveis','JURIDICA','56058337000104','(19) 3541-9701','vendas@leonardolivia.com.br',1,'Avenida Doutor Moraes Salles','842','','Centro','13.010-002','Campinas','SP'),(3,'Química Amparo LTDA','JURIDICA','43461789000190','(11) 2962-4438','vendas@quimicaamparo.com.br',1,'Avenida Waldyr Beira','546','','Figueira','13.904-906','Amparo','SP');
################################################################################################################################################
# TABELA PRODUTO
################################################################################################################################################
INSERT INTO `produto` VALUES (1,'MK1301','Detergente Ypê 5lts','Detergente Ypê 5lts',20.00,10.00,5.00,1,12,'ce500d89-ea5b-4115-8fc8-adafe190c804_Detergente Ypê 5lts.png','image/png',2,3,1,1),(2,'MK3003','Luvas Latex para Procedimento','Luvas Latex para Procedimento',16.99,8.56,7.00,1,50,'efab191b-3180-44ac-bb53-e943ffe67ebb_Luvas Latex para Procedimento.jpg','image/jpeg',3,1,1,1),(3,'MK5343','Pano Multiuso 30 mts','Panos multiuso 30mts diversas cores',46.99,22.33,9.00,1,90,'e46ccc0b-e15f-48d7-80d0-a9e9ff76ce5b_Pano Multiuso 30 mts.jpg','image/jpeg',2,1,1,1),(4,'NT3306','Copos cristais nobres - pct 100','Copos cristais nobres - Pacote com 100 unidades',9.00,4.50,5.00,1,100,'5e762c1e-4855-477f-a98e-9186372639b4_Copos cristais nobres.jpg','image/jpeg',3,2,1,1),(5,'NT1506','Garfo sobremesa branco e cristal - pct 100 Ud','Garfo sobremesa branco e cristal',8.00,4.00,5.00,1,210,'848544be-3ce6-4b80-8051-1effd8e9e873_Garfo sobremesa branco e cristal.jpg','image/jpeg',3,2,1,1),(6,'GT3376','Embalagens de bolo','Embalagens de bolo',1.36,0.56,3.00,1,195,'4af47e3c-1488-49fb-8f67-1e1ddc29312a_Embalagens de bolo.jpg','image/jpeg',3,2,1,1),(7,'TJ2243','TOALHA COZINHA MASCOT','TOALHA COZINHA MASCOT',4.99,2.30,3.00,1,119,'8a4245ff-9695-475a-9fa0-6d2ae621977a_Toalha cozinha Mascot.jpg','image/jpeg',1,2,1,1);
################################################################################################################################################
# TABELA USUARIO
################################################################################################################################################
UPDATE `usuario` SET `email`='admin@wansan.com.br', `telefone`='(11) 3901-4916', `cpf`='042.212.078-25', `rg`='23.895.477-8', `data_nascimento`='1988-02-05', `logradouro`='Rua Francesco Melzi', `numero`='772', `bairro`='Jardim Marília', `cep`='03579-140', `cidade`='São Paulo', `estado`='SP' WHERE `codigo`='1';
INSERT INTO `usuario` VALUES (2,'Cauê Matheus Carvalho','caue.carvalho@wansan.com.br','(11) 2912-8815','427.803.908-59','10.486.889-2','$2a$10$Qvm2.cg/GtTD56owpszBguKgDlI55bjgIhWZFm6MPTRVxtnPxfDEG',1,'1995-03-22','Beco Santa Francisca','316','','Paraisópolis','05.663-140','São Paulo','SP'),(3,'Diogo Nicolas da Silva','diogo.silva@wansan.com.br','(11) 2755-4938','550.543.708-70','20.801.499-8','$2a$10$6s8TmqVbrQxvJW9UKbzqe.FBvBrUi8GZAsSG9EscoSrVPRYaV/iiq',1,'1995-09-16','Rua José Rasquinho','278','','Jardim Novo Parelheiros','04.890-740','São Paulo','SP'),(4,'Joaquim Vinicius Diego Monteiro','joaquim.monteiro@wansan.com.br','(11) 3916-4838','853.474.878-01','42.527.085-3','$2a$10$5DFlw2PQHyfwySH7sDi.eev93RlUQXZpz3KQUZiDP8LdEFEs6Ko/K',1,'1994-07-21','Rua Alagoas','873','','Vila Carmosina','08.270-250','São Paulo','SP'),(5,'Pietro Levi Paulo Pereira','pietro.pereira@wansan.com.br','(11) 3572-9668','196.771.138-01','13.825.515-5','$2a$10$7WYJzpe7A6D5Tzh3hBzu6OdebuZBF3jSWeWW.py8gb41abVEQjPOi',1,'1991-06-12','Rua Cláudia','893','','Vila Marieta','03.617-000','São Paulo','SP'),(6,'Raul Antonio Ryan Almeida','raul.almeida@wansan.com.br','(11) 2730-2488','114.817.478-86','24.294.264-7','$2a$10$k0kynSp42cR/d8ZITHKZYuJ87JAo0N.JZdTqW9NycTrzMNqBFEaK2',1,'1993-07-14','Rua Persépolis','561','','Cidade Dutra','04.805-200','São Paulo','SP');
################################################################################################################################################
# TABELA CONTA_BANCARIA
################################################################################################################################################
INSERT INTO `conta_bancaria` VALUES (1,'341 - BANCO ITAU S.A.','8521','14549-3',1,2),(2,'237 - BANCO BRADESCO S.A.','2860','1465590-5',0,2),(3,'341 - BANCO ITAU S.A.','6922','58011-0',1,3),(4,'237 - BANCO BRADESCO S.A.','3373','1368915-6',0,3),(5,'341 - BANCO ITAU S.A.','9183','75269-1',1,4),(6,'341 - BANCO ITAU S.A.','8228','89122-5',1,5),(7,'341 - BANCO ITAU S.A.','0121','77125-7',1,6),(8,'341 - BANCO ITAU S.A.','3169','75693-3',1,1);
################################################################################################################################################
# TABELA VENDA
################################################################################################################################################
INSERT INTO `venda` VALUES (1,'2017-05-10 16:02:28',10.00,5.00,634.90,'EMITIDA','',NULL,1,2,31.75,2);
INSERT INTO `venda` VALUES (2,'2017-05-10 16:03:53',5.00,2.00,103.00,'FATURADA','',NULL,2,3,5.15,1);
INSERT INTO `venda` VALUES (3,'2017-05-10 16:13:12',3.00,35.00,1168.00,'FATURADA','',NULL,3,4,58.40,2);
INSERT INTO `venda` VALUES (4,'2017-05-11 16:14:40',5.00,30.00,1174.40,'FATURADA','',NULL,4,5,58.72,1);
INSERT INTO `venda` VALUES (5,'2017-05-11 16:17:21',2.00,0.00,8.80,'FATURADA','',NULL,5,6,0.44,1);
INSERT INTO `venda` VALUES (6,'2017-05-11 16:19:42',7.00,4.00,163.00,'FATURADA','',NULL,6,2,8.15,1);
INSERT INTO `venda` VALUES (7,'2017-05-15 16:26:36',10.00,5.00,424.80,'FATURADA','',NULL,7,6,21.24,1);
INSERT INTO `venda` VALUES (8,'2017-05-20 19:36:09',NULL,0.14,4.85,'FINALIZADA','',NULL,6,5,0.24,1);
INSERT INTO `venda` VALUES (9,'2017-04-10 16:02:28',10.01,5.01,634.91,'FINALIZADA','',NULL,5,4,31.76,2);
INSERT INTO `venda` VALUES (10,'2017-04-10 16:03:53',5.01,2.01,103.01,'ORCAMENTO','',NULL,4,3,5.16,1);
INSERT INTO `venda` VALUES (11,'2017-04-10 16:13:12',3.01,35.01,1168.01,'FINALIZADA','',NULL,3,2,58.104,2);
INSERT INTO `venda` VALUES (12,'2017-04-11 16:14:40',5.01,30.01,1174.41,'FINALIZADA','',NULL,2,3,58.136,1);
INSERT INTO `venda` VALUES (13,'2017-04-11 16:17:21',2.01,0.01,8.81,'FINALIZADA','',NULL,3,4,0.45,1);
INSERT INTO `venda` VALUES (14,'2017-04-11 16:19:42',7.01,4.01,163.01,'ORCAMENTO','',NULL,4,5,8.16,1);
INSERT INTO `venda` VALUES (15,'2017-04-15 16:26:36',10.01,5.01,424.81,'FINALIZADA','',NULL,5,6,21.25,1);
INSERT INTO `venda` VALUES (16,'2017-04-20 19:36:09',NULL,0.15,4.86,'FINALIZADA','',NULL,2,4,0.25,1);
INSERT INTO `venda` VALUES (17,'2017-03-10 16:02:28',10.02,5.02,634.92,'FATURADA','',NULL,1,4,31.77,2);
INSERT INTO `venda` VALUES (18,'2017-03-10 16:03:53',5.02,2.02,103.02,'ORCAMENTO','',NULL,2,2,5.17,1);
INSERT INTO `venda` VALUES (19,'2017-03-10 16:13:12',3.02,35.02,1168.02,'CANCELADA','',NULL,3,2,58.168,2);
INSERT INTO `venda` VALUES (20,'2017-03-11 16:14:40',5.02,30.02,1174.42,'FINALIZADA','',NULL,6,1,58.200,1);
INSERT INTO `venda` VALUES (21,'2017-03-11 16:17:21',2.02,0.02,8.82,'FINALIZADA','',NULL,7,3,0.46,1);
INSERT INTO `venda` VALUES (22,'2017-03-11 16:19:42',7.02,4.02,163.02,'ORCAMENTO','',NULL,7,5,8.17,1);
INSERT INTO `venda` VALUES (23,'2017-03-15 16:26:36',10.02,5.02,424.82,'FINALIZADA','',NULL,2,4,21.26,1);
INSERT INTO `venda` VALUES (24,'2017-03-20 19:36:09',NULL,0.16,4.87,'FINALIZADA','',NULL,5,3,0.26,1);
INSERT INTO `venda` VALUES (25,'2017-02-10 16:02:28',10.03,5.03,634.93,'FINALIZADA','',NULL,4,5,31.78,2);
INSERT INTO `venda` VALUES (26,'2017-02-10 16:03:53',5.03,2.03,103.03,'ORCAMENTO','',NULL,4,3,5.18,1);
INSERT INTO `venda` VALUES (27,'2017-02-10 16:13:12',3.03,35.03,1168.03,'FINALIZADA','',NULL,3,3,58.232,2);
INSERT INTO `venda` VALUES (28,'2017-02-11 16:14:40',5.03,30.03,1174.43,'FINALIZADA','',NULL,2,6,58.264,1);
INSERT INTO `venda` VALUES (29,'2017-02-11 16:17:21',2.03,0.03,8.83,'FINALIZADA','',NULL,1,5,0.47,1);
INSERT INTO `venda` VALUES (30,'2017-02-11 16:19:42',7.03,4.03,163.03,'FINALIZADA','',NULL,2,4,8.18,1);
INSERT INTO `venda` VALUES (31,'2017-02-15 16:26:36',10.03,5.03,424.83,'CANCELADA','',NULL,2,3,21.27,1);
INSERT INTO `venda` VALUES (32,'2017-02-20 19:36:09',NULL,0.17,4.88,'FINALIZADA','',NULL,3,2,0.27,1);
INSERT INTO `venda` VALUES (33,'2017-01-10 16:02:28',10.04,5.04,634.94,'FINALIZADA','',NULL,4,4,31.79,2);
INSERT INTO `venda` VALUES (34,'2017-01-10 16:03:53',5.04,2.04,103.04,'ORCAMENTO','',NULL,7,1,5.19,1);
INSERT INTO `venda` VALUES (35,'2017-01-10 16:13:12',3.04,35.04,1168.04,'FINALIZADA','',NULL,6,2,58.296,2);
INSERT INTO `venda` VALUES (36,'2017-01-11 16:14:40',5.04,30.04,1174.44,'FINALIZADA','',NULL,5,3,58.328,1);
INSERT INTO `venda` VALUES (37,'2017-01-11 16:17:21',2.04,0.04,8.84,'CANCELADA','',NULL,4,6,0.48,1);
INSERT INTO `venda` VALUES (38,'2017-01-11 16:19:42',7.04,4.04,163.04,'ORCAMENTO','',NULL,3,5,8.19,1);
INSERT INTO `venda` VALUES (39,'2017-01-15 16:26:36',10.04,5.04,424.84,'FINALIZADA','',NULL,7,2,21.28,1);
INSERT INTO `venda` VALUES (40,'2017-01-20 19:36:09',NULL,0.18,4.89,'FINALIZADA','',NULL,4,6,0.28,1);
INSERT INTO `venda` VALUES (41,'2016-12-10 16:02:28',10.05,5.05,634.95,'FINALIZADA','',NULL,2,4,31.80,2);
INSERT INTO `venda` VALUES (42,'2016-12-10 16:03:53',5.05,2.05,103.05,'ORCAMENTO','',NULL,1,5,5.20,1);
INSERT INTO `venda` VALUES (43,'2016-12-10 16:13:12',3.05,35.05,1168.05,'FINALIZADA','',NULL,2,2,58.360,2);
INSERT INTO `venda` VALUES (44,'2016-12-11 16:14:40',5.05,30.05,1174.45,'FINALIZADA','',NULL,4,1,58.392,1);
INSERT INTO `venda` VALUES (45,'2016-12-11 16:17:21',2.05,0.05,8.85,'FINALIZADA','',NULL,6,6,0.49,1);
INSERT INTO `venda` VALUES (46,'2016-12-11 16:19:42',7.05,4.05,163.05,'ORCAMENTO','',NULL,3,5,8.20,1);
INSERT INTO `venda` VALUES (47,'2016-12-15 16:26:36',10.05,5.05,424.85,'CANCELADA','',NULL,5,4,21.29,1);
INSERT INTO `venda` VALUES (48,'2016-12-20 19:36:09',NULL,0.19,4.90,'FINALIZADA','',NULL,4,5,0.29,1);
################################################################################################################################################
# TABELA ITEM VENDA
################################################################################################################################################
INSERT INTO `item_venda` VALUES (1,10,46.99,2.23,3,1);
INSERT INTO `item_venda` VALUES (2,8,20.00,1.00,1,2);
INSERT INTO `item_venda` VALUES (3,5,20.00,1.00,1,2);
INSERT INTO `item_venda` VALUES (4,150,8.00,0.40,5,3);
INSERT INTO `item_venda` VALUES (5,100,9.00,0.45,4,4);
INSERT INTO `item_venda` VALUES (6,60,4.99,0.15,7,4);
INSERT INTO `item_venda` VALUES (7,5,1.36,0.04,6,5);
INSERT INTO `item_venda` VALUES (8,8,20.00,1.00,1,6);
INSERT INTO `item_venda` VALUES (9,20,4.99,0.15,7,7);
INSERT INTO `item_venda` VALUES (10,40,9.00,0.40,5,7);
INSERT INTO `item_venda` VALUES (11,1,4.99,0.15,7,8);
INSERT INTO `item_venda` VALUES (12,10,46.99,2.23,3,9);
INSERT INTO `item_venda` VALUES (13,8,20.00,1.00,1,10);
INSERT INTO `item_venda` VALUES (14,5,20.00,1.00,1,10);
INSERT INTO `item_venda` VALUES (15,150,8.00,0.40,5,11);
INSERT INTO `item_venda` VALUES (16,100,9.00,0.45,4,12);
INSERT INTO `item_venda` VALUES (17,60,4.99,0.15,7,12);
INSERT INTO `item_venda` VALUES (18,5,1.36,0.04,6,13);
INSERT INTO `item_venda` VALUES (19,8,20.00,1.00,1,14);
INSERT INTO `item_venda` VALUES (20,20,4.99,0.15,7,15);
INSERT INTO `item_venda` VALUES (21,40,9.00,0.40,5,15);
INSERT INTO `item_venda` VALUES (22,1,4.99,0.15,7,16);
INSERT INTO `item_venda` VALUES (23,10,46.99,2.23,3,17);
INSERT INTO `item_venda` VALUES (24,8,20.00,1.00,1,18);
INSERT INTO `item_venda` VALUES (25,5,20.00,1.00,1,18);
INSERT INTO `item_venda` VALUES (26,150,8.00,0.40,5,19);
INSERT INTO `item_venda` VALUES (27,100,9.00,0.45,4,20);
INSERT INTO `item_venda` VALUES (28,60,4.99,0.15,7,20);
INSERT INTO `item_venda` VALUES (29,5,1.36,0.04,6,21);
INSERT INTO `item_venda` VALUES (30,8,20.00,1.00,1,22);
INSERT INTO `item_venda` VALUES (31,20,4.99,0.15,7,23);
INSERT INTO `item_venda` VALUES (32,40,9.00,0.40,5,23);
INSERT INTO `item_venda` VALUES (33,1,4.99,0.15,7,24);
INSERT INTO `item_venda` VALUES (34,10,46.99,2.23,3,25);
INSERT INTO `item_venda` VALUES (35,8,20.00,1.00,1,26);
INSERT INTO `item_venda` VALUES (36,5,20.00,1.00,1,26);
INSERT INTO `item_venda` VALUES (37,150,8.00,0.40,5,27);
INSERT INTO `item_venda` VALUES (38,100,9.00,0.45,4,28);
INSERT INTO `item_venda` VALUES (39,60,4.99,0.15,7,28);
INSERT INTO `item_venda` VALUES (40,5,1.36,0.04,6,29);
INSERT INTO `item_venda` VALUES (41,8,20.00,1.00,1,30);
INSERT INTO `item_venda` VALUES (42,20,4.99,0.15,7,31);
INSERT INTO `item_venda` VALUES (43,40,9.00,0.40,5,31);
INSERT INTO `item_venda` VALUES (44,1,4.99,0.15,7,32);
INSERT INTO `item_venda` VALUES (45,10,46.99,2.23,3,33);
INSERT INTO `item_venda` VALUES (46,8,20.00,1.00,1,34);
INSERT INTO `item_venda` VALUES (47,5,20.00,1.00,1,34);
INSERT INTO `item_venda` VALUES (48,150,8.00,0.40,5,35);
INSERT INTO `item_venda` VALUES (49,100,9.00,0.45,4,36);
INSERT INTO `item_venda` VALUES (50,60,4.99,0.15,7,36);
INSERT INTO `item_venda` VALUES (51,5,1.36,0.04,6,37);
INSERT INTO `item_venda` VALUES (52,8,20.00,1.00,1,38);
INSERT INTO `item_venda` VALUES (53,20,4.99,0.15,7,39);
INSERT INTO `item_venda` VALUES (54,40,9.00,0.40,5,39);
INSERT INTO `item_venda` VALUES (55,1,4.99,0.15,7,40);
INSERT INTO `item_venda` VALUES (56,10,46.99,2.23,3,41);
INSERT INTO `item_venda` VALUES (57,8,20.00,1.00,1,42);
INSERT INTO `item_venda` VALUES (58,5,20.00,1.00,1,42);
INSERT INTO `item_venda` VALUES (59,150,8.00,0.40,5,43);
INSERT INTO `item_venda` VALUES (60,100,9.00,0.45,4,44);
INSERT INTO `item_venda` VALUES (61,60,4.99,0.15,7,44);
INSERT INTO `item_venda` VALUES (62,5,1.36,0.04,6,45);
INSERT INTO `item_venda` VALUES (63,8,20.00,1.00,1,46);
INSERT INTO `item_venda` VALUES (64,20,4.99,0.15,7,47);
INSERT INTO `item_venda` VALUES (65,40,9.00,0.40,5,47);
INSERT INTO `item_venda` VALUES (66,1,4.99,0.15,7,48);
