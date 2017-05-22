DROP PROCEDURE IF EXISTS proc_deleta_orcamentos_vencidos;
DELIMITER $$
CREATE PROCEDURE proc_deleta_orcamentos_vencidos()
BEGIN
	DECLARE codigo_orcamento INT;
    DECLARE data_limit DATETIME;
    
    SET data_limit = (select DATE_SUB(CURRENT_DATE , INTERVAL 30 DAY));
    
	SET codigo_orcamento = (
		SELECT codigo FROM venda
		WHERE data_criacao < data_limit
        AND `status` = 'ORCAMENTO'
        ORDER BY data_criacao DESC
		LIMIT 1);
	
    WHILE codigo_orcamento IS NOT NULL DO
		DELETE FROM item_venda WHERE codigo_venda = codigo_orcamento;
		DELETE FROM venda WHERE codigo = codigo_orcamento;
        SET codigo_orcamento = (
			SELECT codigo FROM venda
			WHERE data_criacao < data_limit
            AND `status` = 'ORCAMENTO'
			ORDER BY data_criacao DESC
			LIMIT 1);
	END WHILE;
    
END $$
DELIMITER ;
SET GLOBAL event_scheduler = ON;
DROP EVENT IF EXISTS event_deleta_orcamentos_vencidos;
CREATE EVENT event_deleta_orcamentos_vencidos
    ON SCHEDULE 
		EVERY 1 DAY
        STARTS '2017-05-21 00:00:00'
    DO
      CALL proc_deleta_orcamentos_vencidos();