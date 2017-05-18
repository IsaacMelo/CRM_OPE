CREATE TABLE parametro (
    codigo BIGINT(20) PRIMARY KEY AUTO_INCREMENT,
	nome VARCHAR(50) NOT NULL,
	razao VARCHAR(50) NOT NULL,
 	comissao DECIMAL(10, 2) NOT NULL,
	desconto DECIMAL(10, 2) NOT NULL,
	cnpj VARCHAR(30) NOT NULL,
    telefone VARCHAR(20) NOT NULL,
    email VARCHAR(50) NOT NULL,
    logradouro VARCHAR(50),
    numero VARCHAR(15),
    complemento VARCHAR(20),
    bairro VARCHAR(50),
    cep VARCHAR(15),
    cidade VARCHAR(50),
    estado VARCHAR(50)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

INSERT INTO `parametro` VALUES 
	(0,'Wansan','Wansan Comercial LTDA',5.00,3.00,'59713305000193','(11) 2233-9300','contato@wansan.com.br','Rua Lacerda Marques','864','','Lauzane Paulista','02.441-200','São Paulo','SP');