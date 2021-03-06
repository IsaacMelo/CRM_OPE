CREATE TABLE cliente (
    codigo BIGINT(20) PRIMARY KEY AUTO_INCREMENT,
    nome VARCHAR(80) NOT NULL,
    tipo_pessoa VARCHAR(15) NOT NULL,
    cpf_cnpj VARCHAR(30),
    telefone VARCHAR(20),
    email VARCHAR(50) NOT NULL,
    ativo BOOLEAN DEFAULT true,
    logradouro VARCHAR(50),
    numero VARCHAR(15),
    complemento VARCHAR(20),
    bairro VARCHAR(50),
    cep VARCHAR(15),
    cidade VARCHAR(50),
    estado VARCHAR(50)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;