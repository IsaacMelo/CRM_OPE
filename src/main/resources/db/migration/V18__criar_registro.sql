CREATE TABLE registro_estoque (
    codigo BIGINT(20) PRIMARY KEY AUTO_INCREMENT,
    data_criacao DATETIME NOT NULL,
    total_produtos BIGINT(20),
    total_itens BIGINT(20),
    registro VARCHAR(30) NOT NULL,
    codigo_usuario BIGINT(20) NOT NULL,
    FOREIGN KEY (codigo_usuario) REFERENCES usuario(codigo)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE item_estoque (
    codigo BIGINT(20) PRIMARY KEY AUTO_INCREMENT,
    quantidade INTEGER NOT NULL,   
    codigo_produto BIGINT(20) NOT NULL,
    codigo_registro BIGINT(20) NOT NULL,
    FOREIGN KEY (codigo_produto) REFERENCES produto(codigo),
    FOREIGN KEY (codigo_registro) REFERENCES venda(codigo)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;