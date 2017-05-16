CREATE TABLE categoria (
    codigo BIGINT(20) PRIMARY KEY AUTO_INCREMENT,
    nome VARCHAR(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE fornecedor (
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

CREATE TABLE produto (
    codigo BIGINT(20) PRIMARY KEY AUTO_INCREMENT,
    sku VARCHAR(50) NOT NULL,
    nome VARCHAR(80) NOT NULL,
    descricao TEXT NOT NULL,
    valor DECIMAL(10, 2) NOT NULL,
    valor_compra DECIMAL(10, 2) NOT NULL,
    comissao DECIMAL(10, 2) NOT NULL,
	quantidade_minima INTEGER,
	quantidade_estoque INTEGER,
	foto VARCHAR(100),
	content_type VARCHAR(100),
    codigo_categoria BIGINT(20) NOT NULL,
	codigo_fornecedor BIGINT(20) NOT NULL,
    ativo BOOLEAN DEFAULT true,
    estoque_ativo BOOLEAN DEFAULT true,
	FOREIGN KEY (codigo_categoria) REFERENCES categoria(codigo),
	FOREIGN KEY (codigo_fornecedor) REFERENCES fornecedor(codigo)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

INSERT INTO categoria VALUES (0, 'Higiene');
INSERT INTO categoria VALUES (0, 'Limpeza');
