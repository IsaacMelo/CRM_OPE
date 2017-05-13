CREATE TABLE forma_pagamento (
    codigo BIGINT(20) PRIMARY KEY AUTO_INCREMENT,
    nome VARCHAR(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

ALTER TABLE venda ADD codigo_pagamento BIGINT(20) NOT NULL;

ALTER TABLE venda
ADD FOREIGN KEY (codigo_pagamento) REFERENCES forma_pagamento(codigo);

INSERT INTO forma_pagamento VALUES (0, 'Dinheiro');
INSERT INTO forma_pagamento VALUES (0, 'Boleto');