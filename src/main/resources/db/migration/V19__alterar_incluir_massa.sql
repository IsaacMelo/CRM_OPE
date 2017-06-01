use ope;
#########################################################################################################################################
# ITEM_ESTOQUE
#########################################################################################################################################
ALTER TABLE `ope`.`item_estoque` 
DROP FOREIGN KEY `item_estoque_ibfk_2`;
ALTER TABLE `ope`.`item_estoque` 
ADD CONSTRAINT `item_estoque_ibfk_2`
  FOREIGN KEY (`codigo_registro`)
  REFERENCES `ope`.`registro_estoque` (`codigo`);

#########################################################################################################################################
# ITEM_VENDA
#########################################################################################################################################
delete FROM ope.item_venda
where codigo >= 1
and quantidade >= 1;
#########################################################################################################################################
# VENDA
#########################################################################################################################################
delete FROM ope.venda
where codigo >= 1
and codigo_cliente >= 1;
#########################################################################################################################################
# CONTA_BANCARIA
#########################################################################################################################################
delete from conta_bancaria
where codigo >= 1;
#########################################################################################################################################
# USUARIO_GRUPO
#########################################################################################################################################
delete FROM ope.usuario_grupo
where codigo_usuario >= 1
and codigo_grupo >= 1;
#########################################################################################################################################
# USUARIO
#########################################################################################################################################
delete FROM ope.usuario
where codigo >= 1;

#########################################################################################################################################
# USUARIO
#########################################################################################################################################
INSERT INTO `usuario` VALUES (1,'Admin','admin@wansan.com.br','(11) 3901-4916','042.212.078-25','23.895.477-8','$2a$10$g.wT4R0Wnfel1jc/k84OXuwZE02BlACSLfWy6TycGPvvEKvIm86SG',1,'1988-02-05','Rua Francesco Melzi','772',NULL,'Jardim Marília','03579-140','São Paulo','SP'),(2,'Cauê Matheus Carvalho','caue.carvalho@wansan.com.br','(11) 2912-8815','427.803.908-59','10.486.889-2','$2a$10$Qvm2.cg/GtTD56owpszBguKgDlI55bjgIhWZFm6MPTRVxtnPxfDEG',1,'1995-03-22','Beco Santa Francisca','316','','Paraisópolis','05.663-140','São Paulo','SP'),(3,'Diogo Nicolas da Silva','diogo.silva@wansan.com.br','(11) 2755-4938','550.543.708-70','20.801.499-8','$2a$10$6s8TmqVbrQxvJW9UKbzqe.FBvBrUi8GZAsSG9EscoSrVPRYaV/iiq',1,'1995-09-16','Rua José Rasquinho','278','','Jardim Novo Parelheiros','04.890-740','São Paulo','SP'),(4,'Joaquim Vinicius Diego Monteiro','joaquim.monteiro@wansan.com.br','(11) 3916-4838','853.474.878-01','42.527.085-3','$2a$10$5DFlw2PQHyfwySH7sDi.eev93RlUQXZpz3KQUZiDP8LdEFEs6Ko/K',1,'1994-07-21','Rua Alagoas','873','','Vila Carmosina','08.270-250','São Paulo','SP'),(5,'Pietro Levi Paulo Pereira','pietro.pereira@wansan.com.br','(11) 3572-9668','196.771.138-01','13.825.515-5','$2a$10$7WYJzpe7A6D5Tzh3hBzu6OdebuZBF3jSWeWW.py8gb41abVEQjPOi',1,'1991-06-12','Rua Cláudia','893','','Vila Marieta','03.617-000','São Paulo','SP'),(6,'Raul Antonio Ryan Almeida','raul.almeida@wansan.com.br','(11) 2730-2488','114.817.478-86','24.294.264-7','$2a$10$k0kynSp42cR/d8ZITHKZYuJ87JAo0N.JZdTqW9NycTrzMNqBFEaK2',1,'1993-07-14','Rua Persépolis','561','','Cidade Dutra','04.805-200','São Paulo','SP'),(7,'Arthur Nicolas Isaac Campos','arthur.campos@wansan.com.br','(11) 99955-7063','924.723.868-42','34.720.883-6','$2a$10$EmQi/ukzDMIvif94j68YO.VrhXPictcnjPVlQ/boys6vmPQ/7JLTu',1,'1989-07-12','Rua Abigail Adams','980','','Vila Talarico','03.534-030','São Paulo','SP'),(8,'Erick Theo Freitas','erick.freitas@wansan.com.br','(11) 99912-9432','110.440.788-47','35.765.289-7','$2a$10$tQw.F4No25N.xV.Xj14qQ.f3AjncDWLEHyA/DTDTQwQVJed2awIAC',1,'1994-01-04','Rua Padre Marcel Cutsem','131','','Parque Tomas Saraiva','03.280-070','São Paulo','SP'),(9,'Gerente Exemplo da Silva','gerente@wansan.com.br','(11) 99388-6643','359.093.188-45','37.466.097-9','$2a$10$HtfZvLZBkG0mu54dKUtPY.tV3UCu971p2nD0ixkWbeZnLkaEW.YiC',1,'1989-06-13','Rua Matias Taborda','520','','Jardim Maristela','05.159-320','São Paulo','SP'),(10,'Estoquista Exemplo da Silva','estoquista@wansan.com.br','(11) 99789-2301','433.458.328-87','27.737.726-2','$2a$10$NtznErgZmzbzMW1vPIP8NuvdDgb3wldPBqpDZo7aqeFdCZu.2tNbK',1,'1990-05-14','Rua Salvador','440','','Jardim Pantanal','08.180-770','São Paulo','SP'),(11,'Transportador Exemplo da Silva','transportador@wansan.com.br','(11) 99542-9208','019.940.588-38','47.534.998-2','$2a$10$ww9jgI.w05hbFF2SAqjC8.mhpSRawK2brhAKBm9Sm5onRhh7MbJRK',1,'1992-06-02','Rua Doutor Augusto Meirelles Reis Neto','114','','Parque Continental','05.325-090','SAO PAULO','SP');

#########################################################################################################################################
# USUARIO_GRUPO
#########################################################################################################################################
INSERT INTO `usuario_grupo` VALUES (1,1),(2,2),(3,2),(4,2),(5,2),(6,2),(7,2),(8,2),(9,3),(10,4),(11,5);

#########################################################################################################################################
# CONTA_BANCARIA
#########################################################################################################################################
INSERT INTO `conta_bancaria` VALUES (1,'341 - BANCO ITAU S.A.','8521','14549-3',1,2),(2,'237 - BANCO BRADESCO S.A.','2860','1465590-5',0,2),(3,'341 - BANCO ITAU S.A.','6922','58011-0',1,3),(4,'237 - BANCO BRADESCO S.A.','3373','1368915-6',0,3),(5,'341 - BANCO ITAU S.A.','9183','75269-1',1,4),(6,'341 - BANCO ITAU S.A.','8228','89122-5',1,5),(7,'341 - BANCO ITAU S.A.','0121','77125-7',1,6),(8,'341 - BANCO ITAU S.A.','3169','75693-3',1,1),(9,'341 - BANCO ITAU S.A.','8089','78963-3',1,7),(10,'341 - BANCO ITAU S.A.','0298','30113-6',1,8),(11,'341 - BANCO ITAU S.A.','7451','97420-3',1,9),(12,'341 - BANCO ITAU S.A.','9113','61266-8',1,10),(13,'341 - BANCO ITAU S.A.','7196','68223-6',1,11);

#########################################################################################################################################
# PRODUTO
#########################################################################################################################################
UPDATE `ope`.`produto` SET `quantidade_estoque`='103680' WHERE `codigo`='1';
UPDATE `ope`.`produto` SET `quantidade_estoque`='86640' WHERE `codigo`='2';
UPDATE `ope`.`produto` SET `quantidade_estoque`='54560' WHERE `codigo`='3';
UPDATE `ope`.`produto` SET `quantidade_estoque`='105600' WHERE `codigo`='4';
UPDATE `ope`.`produto` SET `quantidade_estoque`='51840' WHERE `codigo`='5';
UPDATE `ope`.`produto` SET `quantidade_estoque`='38880' WHERE `codigo`='6';
UPDATE `ope`.`produto` SET `quantidade_estoque`='124160' WHERE `codigo`='7';

#########################################################################################################################################
# REGISTRO_ESTOQUE
#########################################################################################################################################
INSERT INTO `registro_estoque` VALUES (1,'2016-12-20 10:23:22',2,165000,'ENTRADA',10);
INSERT INTO `registro_estoque` VALUES (2,'2016-12-20 10:25:22',1,30000,'ENTRADA',10);
INSERT INTO `registro_estoque` VALUES (3,'2016-12-20 10:30:22',2,136000,'ENTRADA',10);
INSERT INTO `registro_estoque` VALUES (4,'2016-12-20 10:44:22',2,150000,'ENTRADA',10);
INSERT INTO `registro_estoque` VALUES (5,'2017-01-20 10:23:22',2,165000,'ENTRADA',10);
INSERT INTO `registro_estoque` VALUES (6,'2017-01-20 10:25:22',1,30000,'ENTRADA',10);
INSERT INTO `registro_estoque` VALUES (7,'2017-01-20 10:30:22',2,136000,'ENTRADA',10);
INSERT INTO `registro_estoque` VALUES (8,'2017-01-20 10:44:22',2,150000,'ENTRADA',10);
INSERT INTO `registro_estoque` VALUES (9,'2017-02-20 10:23:22',2,165000,'ENTRADA',10);
INSERT INTO `registro_estoque` VALUES (10,'2017-02-20 10:25:22',1,30000,'ENTRADA',10);
INSERT INTO `registro_estoque` VALUES (11,'2017-02-20 10:30:22',2,136000,'ENTRADA',10);
INSERT INTO `registro_estoque` VALUES (12,'2017-02-20 10:44:22',2,150000,'ENTRADA',10);
INSERT INTO `registro_estoque` VALUES (13,'2017-03-20 10:23:22',2,165000,'ENTRADA',10);
INSERT INTO `registro_estoque` VALUES (14,'2017-03-20 10:25:22',1,30000,'ENTRADA',10);
INSERT INTO `registro_estoque` VALUES (15,'2017-03-20 10:30:22',2,136000,'ENTRADA',10);
INSERT INTO `registro_estoque` VALUES (16,'2017-03-20 10:44:22',2,150000,'ENTRADA',10);
INSERT INTO `registro_estoque` VALUES (17,'2017-04-20 10:23:22',2,165000,'ENTRADA',10);
INSERT INTO `registro_estoque` VALUES (18,'2017-04-20 10:25:22',1,30000,'ENTRADA',10);
INSERT INTO `registro_estoque` VALUES (19,'2017-04-20 10:30:22',2,136000,'ENTRADA',10);
INSERT INTO `registro_estoque` VALUES (20,'2017-04-20 10:44:22',2,150000,'ENTRADA',10);
INSERT INTO `registro_estoque` VALUES (21,'2017-05-19 10:23:22',2,165000,'ENTRADA',10);
INSERT INTO `registro_estoque` VALUES (22,'2017-05-19 10:25:22',1,30000,'ENTRADA',10);
INSERT INTO `registro_estoque` VALUES (23,'2017-05-19 10:30:22',2,136000,'ENTRADA',10);
INSERT INTO `registro_estoque` VALUES (24,'2017-05-19 10:44:22',2,150000,'ENTRADA',10);

#########################################################################################################################################
# ITEM_ESTOQUE
#########################################################################################################################################
INSERT INTO `item_estoque` VALUES (1,80000,3,1);
INSERT INTO `item_estoque` VALUES (2,85000,1,1);
INSERT INTO `item_estoque` VALUES (3,30000,2,2);
INSERT INTO `item_estoque` VALUES (4,96000,5,3);
INSERT INTO `item_estoque` VALUES (5,40000,4,3);
INSERT INTO `item_estoque` VALUES (6,30000,7,4);
INSERT INTO `item_estoque` VALUES (7,120000,6,4);
INSERT INTO `item_estoque` VALUES (8,80000,3,5);
INSERT INTO `item_estoque` VALUES (9,85000,1,5);
INSERT INTO `item_estoque` VALUES (10,30000,2,6);
INSERT INTO `item_estoque` VALUES (11,96000,5,7);
INSERT INTO `item_estoque` VALUES (12,40000,4,7);
INSERT INTO `item_estoque` VALUES (13,30000,7,8);
INSERT INTO `item_estoque` VALUES (14,120000,6,8);
INSERT INTO `item_estoque` VALUES (15,80000,3,9);
INSERT INTO `item_estoque` VALUES (16,85000,1,9);
INSERT INTO `item_estoque` VALUES (17,30000,2,10);
INSERT INTO `item_estoque` VALUES (18,96000,5,11);
INSERT INTO `item_estoque` VALUES (19,40000,4,11);
INSERT INTO `item_estoque` VALUES (20,30000,7,12);
INSERT INTO `item_estoque` VALUES (21,120000,6,12);
INSERT INTO `item_estoque` VALUES (22,80000,3,13);
INSERT INTO `item_estoque` VALUES (23,85000,1,13);
INSERT INTO `item_estoque` VALUES (24,30000,2,14);
INSERT INTO `item_estoque` VALUES (25,96000,5,15);
INSERT INTO `item_estoque` VALUES (26,40000,4,15);
INSERT INTO `item_estoque` VALUES (27,30000,7,16);
INSERT INTO `item_estoque` VALUES (28,120000,6,16);
INSERT INTO `item_estoque` VALUES (29,80000,3,17);
INSERT INTO `item_estoque` VALUES (30,85000,1,17);
INSERT INTO `item_estoque` VALUES (31,30000,2,18);
INSERT INTO `item_estoque` VALUES (32,96000,5,19);
INSERT INTO `item_estoque` VALUES (33,40000,4,19);
INSERT INTO `item_estoque` VALUES (34,30000,7,20);
INSERT INTO `item_estoque` VALUES (35,120000,6,20);
INSERT INTO `item_estoque` VALUES (36,80000,3,21);
INSERT INTO `item_estoque` VALUES (37,85000,1,21);
INSERT INTO `item_estoque` VALUES (38,30000,2,22);
INSERT INTO `item_estoque` VALUES (39,96000,5,23);
INSERT INTO `item_estoque` VALUES (40,40000,4,23);
INSERT INTO `item_estoque` VALUES (41,30000,7,24);
INSERT INTO `item_estoque` VALUES (42,120000,6,24);

#########################################################################################################################################
# VENDA
#########################################################################################################################################
INSERT INTO `venda` VALUES (1,'2017-01-02 09:01:40',10.00,15.00,1604.70,'FINALIZADA','','2017-01-04 09:00:00',1,2,80.24,1);
INSERT INTO `venda` VALUES (2,'2017-01-03 10:09:00',12.00,12.00,998.00,'FINALIZADA','','2017-01-05 10:00:00',2,3,49.90,1);
INSERT INTO `venda` VALUES (3,'2017-01-04 11:12:05',8.00,6.00,772.30,'FINALIZADA','','2017-01-06 11:00:00',3,4,38.62,2);
INSERT INTO `venda` VALUES (4,'2017-01-05 12:17:41',10.00,5.00,2304.00,'FINALIZADA','','2017-01-07 09:00:00',4,5,115.20,1);
INSERT INTO `venda` VALUES (5,'2017-01-06 13:19:10',22.00,14.00,1615.00,'FINALIZADA','','2017-01-08 12:00:00',5,6,80.75,2);
INSERT INTO `venda` VALUES (6,'2017-01-09 14:21:10',13.00,35.00,3376.00,'FINALIZADA','','2017-01-11 10:00:00',6,7,168.80,1);
INSERT INTO `venda` VALUES (7,'2017-01-10 15:26:59',13.00,8.00,1858.80,'FINALIZADA','','2017-01-12 10:00:00',7,8,92.94,1);
INSERT INTO `venda` VALUES (8,'2017-01-11 16:37:12',15.00,40.00,6264.30,'FINALIZADA','','2017-01-13 09:00:00',7,8,313.22,1);
INSERT INTO `venda` VALUES (9,'2017-01-12 09:01:40',10.00,15.00,1604.70,'FINALIZADA','','2017-01-14 09:00:00',1,2,80.24,1);
INSERT INTO `venda` VALUES (10,'2017-01-13 10:09:00',12.00,12.00,998.00,'FINALIZADA','','2017-01-15 10:00:00',2,3,49.90,1);
INSERT INTO `venda` VALUES (11,'2017-01-16 11:12:05',8.00,6.00,772.30,'FINALIZADA','','2017-01-18 11:00:00',3,4,38.62,2);
INSERT INTO `venda` VALUES (12,'2017-01-17 12:17:41',10.00,5.00,2304.00,'FINALIZADA','','2017-01-19 09:00:00',4,5,115.20,1);
INSERT INTO `venda` VALUES (13,'2017-01-18 13:19:10',22.00,14.00,1615.00,'FINALIZADA','','2017-01-20 12:00:00',5,6,80.75,2);
INSERT INTO `venda` VALUES (14,'2017-01-19 14:21:10',13.00,35.00,3376.00,'FINALIZADA','','2017-01-21 10:00:00',6,7,168.80,1);
INSERT INTO `venda` VALUES (15,'2017-01-20 15:26:59',13.00,8.00,1858.80,'FINALIZADA','','2017-01-22 10:00:00',7,8,92.94,1);
INSERT INTO `venda` VALUES (16,'2017-01-23 16:37:12',15.00,40.00,6264.30,'FINALIZADA','','2017-01-25 09:00:00',7,8,313.22,1);
INSERT INTO `venda` VALUES (17,'2017-01-24 09:01:40',10.00,15.00,1604.70,'FINALIZADA','','2017-01-26 09:00:00',1,2,80.24,1);
INSERT INTO `venda` VALUES (18,'2017-01-25 10:09:00',12.00,12.00,998.00,'FINALIZADA','','2017-01-27 10:00:00',2,3,49.90,1);
INSERT INTO `venda` VALUES (19,'2017-01-26 11:12:05',8.00,6.00,772.30,'FINALIZADA','','2017-01-28 11:00:00',3,4,38.62,2);
INSERT INTO `venda` VALUES (20,'2017-01-27 12:17:41',10.00,5.00,2304.00,'FINALIZADA','','2017-01-29 09:00:00',4,5,115.20,1);
INSERT INTO `venda` VALUES (21,'2017-01-30 13:19:10',22.00,14.00,1615.00,'FINALIZADA','','2017-02-01 12:00:00',5,6,80.75,2);
INSERT INTO `venda` VALUES (22,'2017-01-31 14:21:10',13.00,35.00,3376.00,'FINALIZADA','','2017-02-02 10:00:00',6,7,168.80,1);
INSERT INTO `venda` VALUES (23,'2017-02-01 15:26:59',13.00,8.00,1858.80,'FINALIZADA','','2017-02-03 10:00:00',7,8,92.94,1);
INSERT INTO `venda` VALUES (24,'2017-02-02 16:37:12',15.00,40.00,6264.30,'FINALIZADA','','2017-02-04 09:00:00',7,8,313.22,1);
INSERT INTO `venda` VALUES (25,'2017-02-03 09:01:40',10.00,15.00,1604.70,'FINALIZADA','','2017-02-05 09:00:00',1,2,80.24,1);
INSERT INTO `venda` VALUES (26,'2017-02-06 10:09:00',12.00,12.00,998.00,'CANCELADA','','2017-02-08 10:00:00',2,3,49.90,1);
INSERT INTO `venda` VALUES (27,'2017-02-07 11:12:05',8.00,6.00,772.30,'FINALIZADA','','2017-02-09 11:00:00',3,4,38.62,2);
INSERT INTO `venda` VALUES (28,'2017-02-08 12:17:41',10.00,5.00,2304.00,'FINALIZADA','','2017-02-10 09:00:00',4,5,115.20,1);
INSERT INTO `venda` VALUES (29,'2017-02-09 13:19:10',22.00,14.00,1615.00,'FINALIZADA','','2017-02-11 12:00:00',5,6,80.75,2);
INSERT INTO `venda` VALUES (30,'2017-02-10 14:21:10',13.00,35.00,3376.00,'FINALIZADA','','2017-02-12 10:00:00',6,7,168.80,1);
INSERT INTO `venda` VALUES (31,'2017-02-13 15:26:59',13.00,8.00,1858.80,'FINALIZADA','','2017-02-15 10:00:00',7,8,92.94,1);
INSERT INTO `venda` VALUES (32,'2017-02-14 16:37:12',15.00,40.00,6264.30,'FINALIZADA','','2017-02-16 09:00:00',7,8,313.22,1);
INSERT INTO `venda` VALUES (33,'2017-02-15 09:01:40',10.00,15.00,1604.70,'FINALIZADA','','2017-02-17 09:00:00',1,2,80.24,1);
INSERT INTO `venda` VALUES (34,'2017-02-16 10:09:00',12.00,12.00,998.00,'FINALIZADA','','2017-02-18 10:00:00',2,3,49.90,1);
INSERT INTO `venda` VALUES (35,'2017-02-17 11:12:05',8.00,6.00,772.30,'FINALIZADA','','2017-02-19 11:00:00',3,4,38.62,2);
INSERT INTO `venda` VALUES (36,'2017-02-20 12:17:41',10.00,5.00,2304.00,'FINALIZADA','','2017-02-22 09:00:00',4,5,115.20,1);
INSERT INTO `venda` VALUES (37,'2017-02-21 13:19:10',22.00,14.00,1615.00,'FINALIZADA','','2017-02-23 12:00:00',5,6,80.75,2);
INSERT INTO `venda` VALUES (38,'2017-02-22 14:21:10',13.00,35.00,3376.00,'FINALIZADA','','2017-02-24 10:00:00',6,7,168.80,1);
INSERT INTO `venda` VALUES (39,'2017-02-23 15:26:59',13.00,8.00,1858.80,'FINALIZADA','','2017-02-25 10:00:00',7,8,92.94,1);
INSERT INTO `venda` VALUES (40,'2017-02-24 16:37:12',15.00,40.00,6264.30,'CANCELADA','','2017-02-26 09:00:00',7,8,313.22,1);
INSERT INTO `venda` VALUES (41,'2017-02-27 09:01:40',10.00,15.00,1604.70,'FINALIZADA','','2017-03-01 09:00:00',1,2,80.24,1);
INSERT INTO `venda` VALUES (42,'2017-02-28 10:09:00',12.00,12.00,998.00,'FINALIZADA','','2017-03-02 10:00:00',2,3,49.90,1);
INSERT INTO `venda` VALUES (43,'2017-03-01 11:12:05',8.00,6.00,772.30,'FINALIZADA','','2017-03-03 11:00:00',3,4,38.62,2);
INSERT INTO `venda` VALUES (44,'2017-03-02 12:17:41',10.00,5.00,2304.00,'FINALIZADA','','2017-03-04 09:00:00',4,5,115.20,1);
INSERT INTO `venda` VALUES (45,'2017-03-03 13:19:10',22.00,14.00,1615.00,'FINALIZADA','','2017-03-05 12:00:00',5,6,80.75,2);
INSERT INTO `venda` VALUES (46,'2017-03-06 14:21:10',13.00,35.00,3376.00,'CANCELADA','','2017-03-08 10:00:00',6,7,168.80,1);
INSERT INTO `venda` VALUES (47,'2017-03-07 15:26:59',13.00,8.00,1858.80,'FINALIZADA','','2017-03-09 10:00:00',7,8,92.94,1);
INSERT INTO `venda` VALUES (48,'2017-03-08 16:37:12',15.00,40.00,6264.30,'FINALIZADA','','2017-03-10 09:00:00',7,8,313.22,1);
INSERT INTO `venda` VALUES (49,'2017-03-09 09:01:40',10.00,15.00,1604.70,'FINALIZADA','','2017-03-11 09:00:00',1,2,80.24,1);
INSERT INTO `venda` VALUES (50,'2017-03-10 10:09:00',12.00,12.00,998.00,'FINALIZADA','','2017-03-12 10:00:00',2,3,49.90,1);
INSERT INTO `venda` VALUES (51,'2017-03-13 11:12:05',8.00,6.00,772.30,'FINALIZADA','','2017-03-15 11:00:00',3,4,38.62,2);
INSERT INTO `venda` VALUES (52,'2017-03-14 12:17:41',10.00,5.00,2304.00,'FINALIZADA','','2017-03-16 09:00:00',4,5,115.20,1);
INSERT INTO `venda` VALUES (53,'2017-03-15 13:19:10',22.00,14.00,1615.00,'FINALIZADA','','2017-03-17 12:00:00',5,6,80.75,2);
INSERT INTO `venda` VALUES (54,'2017-03-16 14:21:10',13.00,35.00,3376.00,'FINALIZADA','','2017-03-18 10:00:00',6,7,168.80,1);
INSERT INTO `venda` VALUES (55,'2017-03-17 15:26:59',13.00,8.00,1858.80,'CANCELADA','','2017-03-19 10:00:00',7,8,92.94,1);
INSERT INTO `venda` VALUES (56,'2017-03-20 16:37:12',15.00,40.00,6264.30,'FINALIZADA','','2017-03-22 09:00:00',7,8,313.22,1);
INSERT INTO `venda` VALUES (57,'2017-03-21 09:01:40',10.00,15.00,1604.70,'FINALIZADA','','2017-03-23 09:00:00',1,2,80.24,1);
INSERT INTO `venda` VALUES (58,'2017-03-22 10:09:00',12.00,12.00,998.00,'FINALIZADA','','2017-03-24 10:00:00',2,3,49.90,1);
INSERT INTO `venda` VALUES (59,'2017-03-23 11:12:05',8.00,6.00,772.30,'CANCELADA','','2017-03-25 11:00:00',3,4,38.62,2);
INSERT INTO `venda` VALUES (60,'2017-03-24 12:17:41',10.00,5.00,2304.00,'FINALIZADA','','2017-03-26 09:00:00',4,5,115.20,1);
INSERT INTO `venda` VALUES (61,'2017-03-27 13:19:10',22.00,14.00,1615.00,'FINALIZADA','','2017-03-29 12:00:00',5,6,80.75,2);
INSERT INTO `venda` VALUES (62,'2017-03-28 14:21:10',13.00,35.00,3376.00,'FINALIZADA','','2017-03-30 10:00:00',6,7,168.80,1);
INSERT INTO `venda` VALUES (63,'2017-03-29 15:26:59',13.00,8.00,1858.80,'FINALIZADA','','2017-03-31 10:00:00',7,8,92.94,1);
INSERT INTO `venda` VALUES (64,'2017-03-30 16:37:12',15.00,40.00,6264.30,'CANCELADA','','2017-04-01 09:00:00',7,8,313.22,1);

INSERT INTO `venda` VALUES (65,'2017-04-01 09:01:40',10.00,15.00,1604.70,'FINALIZADA','','2017-04-03 09:00:00',1,2,80.24,1);
INSERT INTO `venda` VALUES (66,'2017-04-02 10:09:00',12.00,12.00,998.00,'FINALIZADA','','2017-04-04 10:00:00',2,3,49.90,1);
INSERT INTO `venda` VALUES (67,'2017-04-03 11:12:05',8.00,6.00,772.30,'FINALIZADA','','2017-04-05 11:00:00',3,4,38.62,2);
INSERT INTO `venda` VALUES (68,'2017-04-04 12:17:41',10.00,5.00,2304.00,'FINALIZADA','','2017-04-06 09:00:00',4,5,115.20,1);
INSERT INTO `venda` VALUES (69,'2017-04-05 13:19:10',22.00,14.00,1615.00,'FINALIZADA','','2017-04-07 12:00:00',5,6,80.75,2);
INSERT INTO `venda` VALUES (70,'2017-04-08 14:21:10',13.00,35.00,3376.00,'FINALIZADA','','2017-04-10 10:00:00',6,7,168.80,1);
INSERT INTO `venda` VALUES (71,'2017-04-09 15:26:59',13.00,8.00,1858.80,'FINALIZADA','','2017-04-11 10:00:00',7,8,92.94,1);
INSERT INTO `venda` VALUES (72,'2017-04-10 16:37:12',15.00,40.00,6264.30,'FINALIZADA','','2017-04-15 09:00:00',7,8,313.22,1);
INSERT INTO `venda` VALUES (73,'2017-04-11 09:01:40',10.00,15.00,1604.70,'FINALIZADA','','2017-04-13 09:00:00',1,2,80.24,1);
INSERT INTO `venda` VALUES (74,'2017-04-12 10:09:00',12.00,12.00,998.00,'FINALIZADA','','2017-04-14 10:00:00',2,3,49.90,1);
INSERT INTO `venda` VALUES (75,'2017-04-15 11:12:05',8.00,6.00,772.30,'FINALIZADA','','2017-04-11 17:00:00',3,4,38.62,2);
INSERT INTO `venda` VALUES (76,'2017-04-16 12:17:41',10.00,5.00,2304.00,'FINALIZADA','','2017-04-18 09:00:00',4,5,115.20,1);
INSERT INTO `venda` VALUES (77,'2017-04-17 13:19:10',22.00,14.00,1615.00,'FINALIZADA','','2017-04-19 12:00:00',5,6,80.75,2);
INSERT INTO `venda` VALUES (78,'2017-04-18 14:21:10',13.00,35.00,3376.00,'FINALIZADA','','2017-04-20 10:00:00',6,7,168.80,1);
INSERT INTO `venda` VALUES (79,'2017-04-19 15:26:59',13.00,8.00,1858.80,'FINALIZADA','','2017-04-21 10:00:00',7,8,92.94,1);
INSERT INTO `venda` VALUES (80,'2017-04-22 16:37:12',15.00,40.00,6264.30,'FINALIZADA','','2017-04-24 09:00:00',7,8,313.22,1);
INSERT INTO `venda` VALUES (81,'2017-04-23 09:01:40',10.00,15.00,1604.70,'FINALIZADA','','2017-04-25 09:00:00',1,2,80.24,1);
INSERT INTO `venda` VALUES (82,'2017-04-26 10:09:00',12.00,12.00,998.00,'FINALIZADA','','2017-04-28 10:00:00',2,3,49.90,1);
INSERT INTO `venda` VALUES (83,'2017-04-27 11:12:05',8.00,6.00,772.30,'FINALIZADA','','2017-04-29 11:00:00',3,4,38.62,2);
INSERT INTO `venda` VALUES (84,'2017-04-28 12:17:41',10.00,5.00,2304.00,'FINALIZADA','','2017-04-30 09:00:00',4,5,115.20,1);
INSERT INTO `venda` VALUES (85,'2017-04-29 13:19:10',22.00,14.00,1615.00,'FINALIZADA','','2017-05-01 12:00:00',5,6,80.75,2);
INSERT INTO `venda` VALUES (86,'2017-04-30 14:21:10',13.00,35.00,3376.00,'FINALIZADA','','2017-05-02 10:00:00',6,7,168.80,1);
INSERT INTO `venda` VALUES (87,'2017-05-01 15:26:59',13.00,8.00,1858.80,'FINALIZADA','','2017-05-03 10:00:00',7,8,92.94,1);
INSERT INTO `venda` VALUES (88,'2017-05-02 16:37:12',15.00,40.00,6264.30,'FINALIZADA','','2017-05-04 09:00:00',7,8,313.22,1);
INSERT INTO `venda` VALUES (89,'2017-05-03 09:01:40',10.00,15.00,1604.70,'FINALIZADA','','2017-05-05 09:00:00',1,2,80.24,1);
INSERT INTO `venda` VALUES (90,'2017-05-04 10:09:00',12.00,12.00,998.00,'EMITIDA','','2017-05-06 10:00:00',2,3,49.90,1);
INSERT INTO `venda` VALUES (91,'2017-05-05 11:12:05',8.00,6.00,772.30,'FINALIZADA','','2017-05-07 11:00:00',3,4,38.62,2);
INSERT INTO `venda` VALUES (92,'2017-05-08 12:17:41',10.00,5.00,2304.00,'FINALIZADA','','2017-05-10 09:00:00',4,5,115.20,1);
INSERT INTO `venda` VALUES (93,'2017-05-09 13:19:10',22.00,14.00,1615.00,'EMITIDA','','2017-05-11 12:00:00',5,6,80.75,2);
INSERT INTO `venda` VALUES (94,'2017-05-10 14:21:10',13.00,35.00,3376.00,'FINALIZADA','','2017-05-12 10:00:00',6,7,168.80,1);
INSERT INTO `venda` VALUES (95,'2017-05-11 15:26:59',13.00,8.00,1858.80,'FINALIZADA','','2017-05-13 10:00:00',7,8,92.94,1);
INSERT INTO `venda` VALUES (96,'2017-05-12 16:37:12',15.00,40.00,6264.30,'ORCAMENTO','','2017-05-14 09:00:00',7,8,313.22,1);
INSERT INTO `venda` VALUES (97,'2017-05-15 09:01:40',10.00,15.00,1604.70,'FINALIZADA','','2017-05-17 09:00:00',1,2,80.24,1);
INSERT INTO `venda` VALUES (98,'2017-05-16 10:09:00',12.00,12.00,998.00,'FINALIZADA','','2017-05-18 10:00:00',2,3,49.90,1);
INSERT INTO `venda` VALUES (99,'2017-05-17 11:12:05',8.00,6.00,772.30,'FINALIZADA','','2017-05-19 11:00:00',3,4,38.62,2);
INSERT INTO `venda` VALUES (100,'2017-05-18 12:17:41',10.00,5.00,2304.00,'ORCAMENTO','','2017-05-20 09:00:00',4,5,115.20,1);
INSERT INTO `venda` VALUES (101,'2017-05-19 13:19:10',22.00,14.00,1615.00,'FINALIZADA','','2017-05-21 12:00:00',5,6,80.75,2);
INSERT INTO `venda` VALUES (102,'2017-05-22 14:21:10',13.00,35.00,3376.00,'FINALIZADA','','2017-05-24 10:00:00',6,7,168.80,1);
INSERT INTO `venda` VALUES (103,'2017-05-23 15:26:59',13.00,8.00,1858.80,'ORCAMENTO','','2017-05-25 10:00:00',7,8,92.94,1);
INSERT INTO `venda` VALUES (104,'2017-05-24 16:37:12',15.00,40.00,6264.30,'FINALIZADA','','2017-05-26 09:00:00',7,8,313.22,1);
INSERT INTO `venda` VALUES (105,'2017-05-25 09:01:40',10.00,15.00,1604.70,'FINALIZADA','','2017-05-27 09:00:00',1,2,80.24,1);
INSERT INTO `venda` VALUES (106,'2017-05-26 10:09:00',12.00,12.00,998.00,'FINALIZADA','','2017-05-28 10:00:00',2,3,49.90,1);
INSERT INTO `venda` VALUES (107,'2017-05-29 11:12:05',8.00,6.00,772.30,'FATURADA','','2017-06-06 11:00:00',3,4,38.62,2);
INSERT INTO `venda` VALUES (108,'2017-05-30 12:17:41',10.00,5.00,2304.00,'FATURADA','','2017-06-05 09:00:00',4,5,115.20,1);
INSERT INTO `venda` VALUES (109,'2017-05-31 13:19:10',22.00,14.00,1615.00,'FINALIZADA','','2017-06-02 12:00:00',5,6,80.75,2);
INSERT INTO `venda` VALUES (110,'2017-05-31 14:21:10',13.00,35.00,3376.00,'FINALIZADA','','2017-06-02 10:00:00',6,7,168.80,1);
INSERT INTO `venda` VALUES (111,'2017-06-01 15:26:59',13.00,8.00,1858.80,'FATURADA','','2017-06-09 10:00:00',7,8,92.94,1);
INSERT INTO `venda` VALUES (112,'2017-06-01 16:37:12',15.00,40.00,6264.30,'FATURADA','','2017-06-09 09:00:00',7,8,313.22,1);

#########################################################################################################################################
# ITEM_VENDA
#########################################################################################################################################
INSERT INTO `item_venda` VALUES (127,10,46.99,42.29,3,1);
INSERT INTO `item_venda` VALUES (128,20,16.99,23.79,2,1);
INSERT INTO `item_venda` VALUES (129,40,20.00,40.00,1,1);
INSERT INTO `item_venda` VALUES (130,200,4.99,0.15,7,2);
INSERT INTO `item_venda` VALUES (131,60,8.00,0.40,5,3);
INSERT INTO `item_venda` VALUES (132,30,1.36,0.04,6,3);
INSERT INTO `item_venda` VALUES (133,50,4.99,0.15,7,3);
INSERT INTO `item_venda` VALUES (134,100,4.99,0.15,7,4);
INSERT INTO `item_venda` VALUES (135,200,9.00,0.45,4,4);
INSERT INTO `item_venda` VALUES (136,20,16.99,1.19,2,5);
INSERT INTO `item_venda` VALUES (137,30,8.00,0.40,5,5);
INSERT INTO `item_venda` VALUES (138,20,1.36,0.04,6,5);
INSERT INTO `item_venda` VALUES (139,50,20.00,1.00,1,5);
INSERT INTO `item_venda` VALUES (140,200,16.99,1.19,2,6);
INSERT INTO `item_venda` VALUES (141,40,1.36,0.04,6,7);
INSERT INTO `item_venda` VALUES (142,30,8.00,0.40,5,7);
INSERT INTO `item_venda` VALUES (143,30,16.99,1.19,2,7);
INSERT INTO `item_venda` VALUES (144,30,4.99,0.15,7,7);
INSERT INTO `item_venda` VALUES (145,100,9.00,0.45,4,7);
INSERT INTO `item_venda` VALUES (146,150,20.00,1.00,1,8);
INSERT INTO `item_venda` VALUES (147,70,46.99,4.23,3,8);
INSERT INTO `item_venda` VALUES (148,10,46.99,42.29,3,9);
INSERT INTO `item_venda` VALUES (149,20,16.99,23.79,2,9);
INSERT INTO `item_venda` VALUES (150,40,20.00,40.00,1,9);
INSERT INTO `item_venda` VALUES (151,200,4.99,0.15,7,10);
INSERT INTO `item_venda` VALUES (152,60,8.00,0.40,5,11);
INSERT INTO `item_venda` VALUES (153,30,1.36,0.04,6,11);
INSERT INTO `item_venda` VALUES (154,50,4.99,0.15,7,11);
INSERT INTO `item_venda` VALUES (155,100,4.99,0.15,7,12);
INSERT INTO `item_venda` VALUES (156,200,9.00,0.45,4,12);
INSERT INTO `item_venda` VALUES (157,20,16.99,1.19,2,13);
INSERT INTO `item_venda` VALUES (158,30,8.00,0.40,5,13);
INSERT INTO `item_venda` VALUES (159,20,1.36,0.04,6,13);
INSERT INTO `item_venda` VALUES (160,50,20.00,1.00,1,13);
INSERT INTO `item_venda` VALUES (161,200,16.99,1.19,2,14);
INSERT INTO `item_venda` VALUES (162,40,1.36,0.04,6,15);
INSERT INTO `item_venda` VALUES (163,30,8.00,0.40,5,15);
INSERT INTO `item_venda` VALUES (164,30,16.99,1.19,2,15);
INSERT INTO `item_venda` VALUES (165,30,4.99,0.15,7,15);
INSERT INTO `item_venda` VALUES (166,100,9.00,0.45,4,15);
INSERT INTO `item_venda` VALUES (167,150,20.00,1.00,1,16);
INSERT INTO `item_venda` VALUES (168,70,46.99,4.23,3,16);
INSERT INTO `item_venda` VALUES (169,10,46.99,42.29,3,17);
INSERT INTO `item_venda` VALUES (170,20,16.99,23.79,2,17);
INSERT INTO `item_venda` VALUES (171,40,20.00,40.00,1,17);
INSERT INTO `item_venda` VALUES (172,200,4.99,0.15,7,18);
INSERT INTO `item_venda` VALUES (173,60,8.00,0.40,5,19);
INSERT INTO `item_venda` VALUES (174,30,1.36,0.04,6,19);
INSERT INTO `item_venda` VALUES (175,50,4.99,0.15,7,19);
INSERT INTO `item_venda` VALUES (176,100,4.99,0.15,7,20);
INSERT INTO `item_venda` VALUES (177,200,9.00,0.45,4,20);
INSERT INTO `item_venda` VALUES (178,20,16.99,1.19,2,21);
INSERT INTO `item_venda` VALUES (179,30,8.00,0.40,5,21);
INSERT INTO `item_venda` VALUES (180,20,1.36,0.04,6,21);
INSERT INTO `item_venda` VALUES (181,50,20.00,1.00,1,21);
INSERT INTO `item_venda` VALUES (182,200,16.99,1.19,2,22);
INSERT INTO `item_venda` VALUES (183,40,1.36,0.04,6,23);
INSERT INTO `item_venda` VALUES (184,30,8.00,0.40,5,23);
INSERT INTO `item_venda` VALUES (185,30,16.99,1.19,2,23);
INSERT INTO `item_venda` VALUES (186,30,4.99,0.15,7,23);
INSERT INTO `item_venda` VALUES (187,100,9.00,0.45,4,23);
INSERT INTO `item_venda` VALUES (188,150,20.00,1.00,1,24);
INSERT INTO `item_venda` VALUES (189,70,46.99,4.23,3,24);
INSERT INTO `item_venda` VALUES (190,10,46.99,42.29,3,25);
INSERT INTO `item_venda` VALUES (191,20,16.99,23.79,2,25);
INSERT INTO `item_venda` VALUES (192,40,20.00,40.00,1,25);
INSERT INTO `item_venda` VALUES (193,200,4.99,0.15,7,26);
INSERT INTO `item_venda` VALUES (194,60,8.00,0.40,5,27);
INSERT INTO `item_venda` VALUES (195,30,1.36,0.04,6,27);
INSERT INTO `item_venda` VALUES (196,50,4.99,0.15,7,27);
INSERT INTO `item_venda` VALUES (197,100,4.99,0.15,7,28);
INSERT INTO `item_venda` VALUES (198,200,9.00,0.45,4,28);
INSERT INTO `item_venda` VALUES (199,20,16.99,1.19,2,29);
INSERT INTO `item_venda` VALUES (200,30,8.00,0.40,5,29);
INSERT INTO `item_venda` VALUES (201,20,1.36,0.04,6,29);
INSERT INTO `item_venda` VALUES (202,50,20.00,1.00,1,29);
INSERT INTO `item_venda` VALUES (203,200,16.99,1.19,2,30);
INSERT INTO `item_venda` VALUES (204,40,1.36,0.04,6,31);
INSERT INTO `item_venda` VALUES (205,30,8.00,0.40,5,31);
INSERT INTO `item_venda` VALUES (206,30,16.99,1.19,2,31);
INSERT INTO `item_venda` VALUES (207,30,4.99,0.15,7,31);
INSERT INTO `item_venda` VALUES (208,100,9.00,0.45,4,31);
INSERT INTO `item_venda` VALUES (209,150,20.00,1.00,1,32);
INSERT INTO `item_venda` VALUES (210,70,46.99,4.23,3,32);
INSERT INTO `item_venda` VALUES (211,10,46.99,42.29,3,33);
INSERT INTO `item_venda` VALUES (212,20,16.99,23.79,2,33);
INSERT INTO `item_venda` VALUES (213,40,20.00,40.00,1,33);
INSERT INTO `item_venda` VALUES (214,200,4.99,0.15,7,34);
INSERT INTO `item_venda` VALUES (215,60,8.00,0.40,5,35);
INSERT INTO `item_venda` VALUES (216,30,1.36,0.04,6,35);
INSERT INTO `item_venda` VALUES (217,50,4.99,0.15,7,35);
INSERT INTO `item_venda` VALUES (218,100,4.99,0.15,7,36);
INSERT INTO `item_venda` VALUES (219,200,9.00,0.45,4,36);
INSERT INTO `item_venda` VALUES (220,20,16.99,1.19,2,37);
INSERT INTO `item_venda` VALUES (221,30,8.00,0.40,5,37);
INSERT INTO `item_venda` VALUES (222,20,1.36,0.04,6,37);
INSERT INTO `item_venda` VALUES (223,50,20.00,1.00,1,37);
INSERT INTO `item_venda` VALUES (224,200,16.99,1.19,2,38);
INSERT INTO `item_venda` VALUES (225,40,1.36,0.04,6,39);
INSERT INTO `item_venda` VALUES (226,30,8.00,0.40,5,39);
INSERT INTO `item_venda` VALUES (227,30,16.99,1.19,2,39);
INSERT INTO `item_venda` VALUES (228,30,4.99,0.15,7,39);
INSERT INTO `item_venda` VALUES (229,100,9.00,0.45,4,39);
INSERT INTO `item_venda` VALUES (230,150,20.00,1.00,1,40);
INSERT INTO `item_venda` VALUES (231,70,46.99,4.23,3,40);
INSERT INTO `item_venda` VALUES (232,10,46.99,42.29,3,41);
INSERT INTO `item_venda` VALUES (233,20,16.99,23.79,2,41);
INSERT INTO `item_venda` VALUES (234,40,20.00,40.00,1,41);
INSERT INTO `item_venda` VALUES (235,200,4.99,0.15,7,42);
INSERT INTO `item_venda` VALUES (236,60,8.00,0.40,5,43);
INSERT INTO `item_venda` VALUES (237,30,1.36,0.04,6,43);
INSERT INTO `item_venda` VALUES (238,50,4.99,0.15,7,43);
INSERT INTO `item_venda` VALUES (239,100,4.99,0.15,7,44);
INSERT INTO `item_venda` VALUES (240,200,9.00,0.45,4,44);
INSERT INTO `item_venda` VALUES (241,20,16.99,1.19,2,45);
INSERT INTO `item_venda` VALUES (242,30,8.00,0.40,5,45);
INSERT INTO `item_venda` VALUES (243,20,1.36,0.04,6,45);
INSERT INTO `item_venda` VALUES (244,50,20.00,1.00,1,45);
INSERT INTO `item_venda` VALUES (245,200,16.99,1.19,2,46);
INSERT INTO `item_venda` VALUES (246,40,1.36,0.04,6,47);
INSERT INTO `item_venda` VALUES (247,30,8.00,0.40,5,47);
INSERT INTO `item_venda` VALUES (248,30,16.99,1.19,2,47);
INSERT INTO `item_venda` VALUES (249,30,4.99,0.15,7,47);
INSERT INTO `item_venda` VALUES (250,100,9.00,0.45,4,47);
INSERT INTO `item_venda` VALUES (251,150,20.00,1.00,1,48);
INSERT INTO `item_venda` VALUES (252,70,46.99,4.23,3,48);
INSERT INTO `item_venda` VALUES (253,10,46.99,42.29,3,49);
INSERT INTO `item_venda` VALUES (254,20,16.99,23.79,2,49);
INSERT INTO `item_venda` VALUES (255,40,20.00,40.00,1,49);
INSERT INTO `item_venda` VALUES (256,200,4.99,0.15,7,98);
INSERT INTO `item_venda` VALUES (257,60,8.00,0.40,5,51);
INSERT INTO `item_venda` VALUES (258,30,1.36,0.04,6,51);
INSERT INTO `item_venda` VALUES (259,50,4.99,0.15,7,51);
INSERT INTO `item_venda` VALUES (260,100,4.99,0.15,7,52);
INSERT INTO `item_venda` VALUES (261,200,9.00,0.45,4,52);
INSERT INTO `item_venda` VALUES (262,20,16.99,1.19,2,53);
INSERT INTO `item_venda` VALUES (263,30,8.00,0.40,5,53);
INSERT INTO `item_venda` VALUES (264,20,1.36,0.04,6,53);
INSERT INTO `item_venda` VALUES (265,50,20.00,1.00,1,53);
INSERT INTO `item_venda` VALUES (266,200,16.99,1.19,2,54);
INSERT INTO `item_venda` VALUES (267,40,1.36,0.04,6,55);
INSERT INTO `item_venda` VALUES (268,30,8.00,0.40,5,55);
INSERT INTO `item_venda` VALUES (269,30,16.99,1.19,2,55);
INSERT INTO `item_venda` VALUES (270,30,4.99,0.15,7,55);
INSERT INTO `item_venda` VALUES (271,100,9.00,0.45,4,55);
INSERT INTO `item_venda` VALUES (272,150,20.00,1.00,1,56);
INSERT INTO `item_venda` VALUES (273,70,46.99,4.23,3,56);
INSERT INTO `item_venda` VALUES (274,10,46.99,42.29,3,57);
INSERT INTO `item_venda` VALUES (275,20,16.99,23.79,2,57);
INSERT INTO `item_venda` VALUES (276,40,20.00,40.00,1,57);
INSERT INTO `item_venda` VALUES (277,200,4.99,0.15,7,58);
INSERT INTO `item_venda` VALUES (278,60,8.00,0.40,5,59);
INSERT INTO `item_venda` VALUES (279,30,1.36,0.04,6,59);
INSERT INTO `item_venda` VALUES (280,50,4.99,0.15,7,59);
INSERT INTO `item_venda` VALUES (281,100,4.99,0.15,7,60);
INSERT INTO `item_venda` VALUES (282,200,9.00,0.45,4,60);
INSERT INTO `item_venda` VALUES (283,20,16.99,1.19,2,61);
INSERT INTO `item_venda` VALUES (284,30,8.00,0.40,5,61);
INSERT INTO `item_venda` VALUES (285,20,1.36,0.04,6,61);
INSERT INTO `item_venda` VALUES (286,50,20.00,1.00,1,61);
INSERT INTO `item_venda` VALUES (287,200,16.99,1.19,2,62);
INSERT INTO `item_venda` VALUES (288,40,1.36,0.04,6,63);
INSERT INTO `item_venda` VALUES (289,30,8.00,0.40,5,63);
INSERT INTO `item_venda` VALUES (290,30,16.99,1.19,2,63);
INSERT INTO `item_venda` VALUES (291,30,4.99,0.15,7,63);
INSERT INTO `item_venda` VALUES (292,100,9.00,0.45,4,63);
INSERT INTO `item_venda` VALUES (293,150,20.00,1.00,1,64);
INSERT INTO `item_venda` VALUES (294,70,46.99,4.23,3,64);

INSERT INTO `item_venda` VALUES (1,10,46.99,42.29,3,65);
INSERT INTO `item_venda` VALUES (2,20,16.99,23.79,2,65);
INSERT INTO `item_venda` VALUES (3,40,20.00,40.00,1,65);
INSERT INTO `item_venda` VALUES (4,200,4.99,0.15,7,66);
INSERT INTO `item_venda` VALUES (5,60,8.00,0.40,5,67);
INSERT INTO `item_venda` VALUES (6,30,1.36,0.04,6,67);
INSERT INTO `item_venda` VALUES (7,50,4.99,0.15,7,67);
INSERT INTO `item_venda` VALUES (8,100,4.99,0.15,7,68);
INSERT INTO `item_venda` VALUES (9,200,9.00,0.45,4,68);
INSERT INTO `item_venda` VALUES (10,20,16.99,1.19,2,69);
INSERT INTO `item_venda` VALUES (11,30,8.00,0.40,5,69);
INSERT INTO `item_venda` VALUES (12,20,1.36,0.04,6,69);
INSERT INTO `item_venda` VALUES (13,50,20.00,1.00,1,69);
INSERT INTO `item_venda` VALUES (14,200,16.99,1.19,2,70);
INSERT INTO `item_venda` VALUES (15,40,1.36,0.04,6,71);
INSERT INTO `item_venda` VALUES (16,30,8.00,0.40,5,71);
INSERT INTO `item_venda` VALUES (17,30,16.99,1.19,2,71);
INSERT INTO `item_venda` VALUES (18,30,4.99,0.15,7,71);
INSERT INTO `item_venda` VALUES (19,100,9.00,0.45,4,71);
INSERT INTO `item_venda` VALUES (20,150,20.00,1.00,1,72);
INSERT INTO `item_venda` VALUES (21,70,46.99,4.23,3,72);
INSERT INTO `item_venda` VALUES (22,10,46.99,42.29,3,73);
INSERT INTO `item_venda` VALUES (23,20,16.99,23.79,2,73);
INSERT INTO `item_venda` VALUES (24,40,20.00,40.00,1,73);
INSERT INTO `item_venda` VALUES (25,200,4.99,0.15,7,74);
INSERT INTO `item_venda` VALUES (26,60,8.00,0.40,5,75);
INSERT INTO `item_venda` VALUES (27,30,1.36,0.04,6,75);
INSERT INTO `item_venda` VALUES (28,50,4.99,0.15,7,75);
INSERT INTO `item_venda` VALUES (29,100,4.99,0.15,7,76);
INSERT INTO `item_venda` VALUES (30,200,9.00,0.45,4,76);
INSERT INTO `item_venda` VALUES (31,20,16.99,1.19,2,77);
INSERT INTO `item_venda` VALUES (32,30,8.00,0.40,5,77);
INSERT INTO `item_venda` VALUES (33,20,1.36,0.04,6,77);
INSERT INTO `item_venda` VALUES (34,50,20.00,1.00,1,77);
INSERT INTO `item_venda` VALUES (35,200,16.99,1.19,2,78);
INSERT INTO `item_venda` VALUES (36,40,1.36,0.04,6,79);
INSERT INTO `item_venda` VALUES (37,30,8.00,0.40,5,79);
INSERT INTO `item_venda` VALUES (38,30,16.99,1.19,2,79);
INSERT INTO `item_venda` VALUES (39,30,4.99,0.15,7,79);
INSERT INTO `item_venda` VALUES (40,100,9.00,0.45,4,79);
INSERT INTO `item_venda` VALUES (41,150,20.00,1.00,1,80);
INSERT INTO `item_venda` VALUES (42,70,46.99,4.23,3,80);
INSERT INTO `item_venda` VALUES (43,10,46.99,42.29,3,81);
INSERT INTO `item_venda` VALUES (44,20,16.99,23.79,2,81);
INSERT INTO `item_venda` VALUES (45,40,20.00,40.00,1,81);
INSERT INTO `item_venda` VALUES (46,200,4.99,0.15,7,82);
INSERT INTO `item_venda` VALUES (47,60,8.00,0.40,5,83);
INSERT INTO `item_venda` VALUES (48,30,1.36,0.04,6,83);
INSERT INTO `item_venda` VALUES (49,50,4.99,0.15,7,83);
INSERT INTO `item_venda` VALUES (50,100,4.99,0.15,7,84);
INSERT INTO `item_venda` VALUES (51,200,9.00,0.45,4,84);
INSERT INTO `item_venda` VALUES (52,20,16.99,1.19,2,85);
INSERT INTO `item_venda` VALUES (53,30,8.00,0.40,5,85);
INSERT INTO `item_venda` VALUES (54,20,1.36,0.04,6,85);
INSERT INTO `item_venda` VALUES (55,50,20.00,1.00,1,85);
INSERT INTO `item_venda` VALUES (56,200,16.99,1.19,2,86);
INSERT INTO `item_venda` VALUES (57,40,1.36,0.04,6,87);
INSERT INTO `item_venda` VALUES (58,30,8.00,0.40,5,87);
INSERT INTO `item_venda` VALUES (59,30,16.99,1.19,2,87);
INSERT INTO `item_venda` VALUES (60,30,4.99,0.15,7,87);
INSERT INTO `item_venda` VALUES (61,100,9.00,0.45,4,87);
INSERT INTO `item_venda` VALUES (62,150,20.00,1.00,1,88);
INSERT INTO `item_venda` VALUES (63,70,46.99,4.23,3,88);
INSERT INTO `item_venda` VALUES (64,10,46.99,42.29,3,89);
INSERT INTO `item_venda` VALUES (65,20,16.99,23.79,2,89);
INSERT INTO `item_venda` VALUES (66,40,20.00,40.00,1,89);
INSERT INTO `item_venda` VALUES (67,200,4.99,0.15,7,90);
INSERT INTO `item_venda` VALUES (68,60,8.00,0.40,5,91);
INSERT INTO `item_venda` VALUES (69,30,1.36,0.04,6,91);
INSERT INTO `item_venda` VALUES (70,50,4.99,0.15,7,91);
INSERT INTO `item_venda` VALUES (71,100,4.99,0.15,7,92);
INSERT INTO `item_venda` VALUES (72,200,9.00,0.45,4,92);
INSERT INTO `item_venda` VALUES (73,20,16.99,1.19,2,93);
INSERT INTO `item_venda` VALUES (74,30,8.00,0.40,5,93);
INSERT INTO `item_venda` VALUES (75,20,1.36,0.04,6,93);
INSERT INTO `item_venda` VALUES (76,50,20.00,1.00,1,93);
INSERT INTO `item_venda` VALUES (77,200,16.99,1.19,2,94);
INSERT INTO `item_venda` VALUES (78,40,1.36,0.04,6,95);
INSERT INTO `item_venda` VALUES (79,30,8.00,0.40,5,95);
INSERT INTO `item_venda` VALUES (80,30,16.99,1.19,2,95);
INSERT INTO `item_venda` VALUES (81,30,4.99,0.15,7,95);
INSERT INTO `item_venda` VALUES (82,100,9.00,0.45,4,95);
INSERT INTO `item_venda` VALUES (83,150,20.00,1.00,1,96);
INSERT INTO `item_venda` VALUES (84,70,46.99,4.23,3,96);
INSERT INTO `item_venda` VALUES (85,10,46.99,42.29,3,97);
INSERT INTO `item_venda` VALUES (86,20,16.99,23.79,2,97);
INSERT INTO `item_venda` VALUES (87,40,20.00,40.00,1,97);
INSERT INTO `item_venda` VALUES (88,200,4.99,0.15,7,98);
INSERT INTO `item_venda` VALUES (89,60,8.00,0.40,5,99);
INSERT INTO `item_venda` VALUES (90,30,1.36,0.04,6,99);
INSERT INTO `item_venda` VALUES (91,50,4.99,0.15,7,99);
INSERT INTO `item_venda` VALUES (92,100,4.99,0.15,7,100);
INSERT INTO `item_venda` VALUES (93,200,9.00,0.45,4,100);
INSERT INTO `item_venda` VALUES (94,20,16.99,1.19,2,101);
INSERT INTO `item_venda` VALUES (95,30,8.00,0.40,5,101);
INSERT INTO `item_venda` VALUES (96,20,1.36,0.04,6,101);
INSERT INTO `item_venda` VALUES (97,50,20.00,1.00,1,101);
INSERT INTO `item_venda` VALUES (98,200,16.99,1.19,2,102);
INSERT INTO `item_venda` VALUES (99,40,1.36,0.04,6,103);
INSERT INTO `item_venda` VALUES (100,30,8.00,0.40,5,103);
INSERT INTO `item_venda` VALUES (101,30,16.99,1.19,2,103);
INSERT INTO `item_venda` VALUES (102,30,4.99,0.15,7,103);
INSERT INTO `item_venda` VALUES (103,100,9.00,0.45,4,103);
INSERT INTO `item_venda` VALUES (104,150,20.00,1.00,1,104);
INSERT INTO `item_venda` VALUES (105,70,46.99,4.23,3,104);
INSERT INTO `item_venda` VALUES (106,10,46.99,42.29,3,105);
INSERT INTO `item_venda` VALUES (107,20,16.99,23.79,2,105);
INSERT INTO `item_venda` VALUES (108,40,20.00,40.00,1,105);
INSERT INTO `item_venda` VALUES (109,200,4.99,0.15,7,106);
INSERT INTO `item_venda` VALUES (110,60,8.00,0.40,5,107);
INSERT INTO `item_venda` VALUES (111,30,1.36,0.04,6,107);
INSERT INTO `item_venda` VALUES (112,50,4.99,0.15,7,107);
INSERT INTO `item_venda` VALUES (113,100,4.99,0.15,7,108);
INSERT INTO `item_venda` VALUES (114,200,9.00,0.45,4,108);
INSERT INTO `item_venda` VALUES (115,20,16.99,1.19,2,109);
INSERT INTO `item_venda` VALUES (116,30,8.00,0.40,5,109);
INSERT INTO `item_venda` VALUES (117,20,1.36,0.04,6,109);
INSERT INTO `item_venda` VALUES (118,50,20.00,1.00,1,109);
INSERT INTO `item_venda` VALUES (119,200,16.99,1.19,2,110);
INSERT INTO `item_venda` VALUES (120,40,1.36,0.04,6,111);
INSERT INTO `item_venda` VALUES (121,30,8.00,0.40,5,111);
INSERT INTO `item_venda` VALUES (122,30,16.99,1.19,2,111);
INSERT INTO `item_venda` VALUES (123,30,4.99,0.15,7,111);
INSERT INTO `item_venda` VALUES (124,100,9.00,0.45,4,111);
INSERT INTO `item_venda` VALUES (125,150,20.00,1.00,1,112);
INSERT INTO `item_venda` VALUES (126,70,46.99,4.23,3,112);
