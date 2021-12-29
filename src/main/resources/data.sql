-- inserir partidos

INSERT INTO partido (nome, sigla, ideologia, data_fundacao)
VALUES ('Avante', 'Avante', 'CENTRO', '1989-05-15');

INSERT INTO partido (nome, sigla, ideologia, data_fundacao)
VALUES ('Democratas', 'DEM', 'DIREITA', '1985-01-24');

INSERT INTO partido (nome, sigla, ideologia, data_fundacao)
VALUES ('Movimento Democrático Brasileiro', 'MDB', 'CENTRO', '1980-01-15');

INSERT INTO partido (nome, sigla, ideologia, data_fundacao)
VALUES ('Partido dos Trabalhadores', 'PT', 'ESQUERDA', '1980-02-10');

-- inserir associados

INSERT INTO associado (nome, cargo, data_nasc, sexo, id_partido)
VALUES ('Simone Tebet', 'SENADOR', '1970-02-22', 'FEMININO', 3);

INSERT INTO associado (nome, cargo, data_nasc, sexo, id_partido)
VALUES ('Efraim Filho', 'DEPUTADO_ESTADUAL', '1979-03-18', 'MASCULINO', 2);

INSERT INTO associado (nome, cargo, data_nasc, sexo, id_partido)
VALUES ('Fátima Bezerra', 'GOVERNADOR', '1955-09-19', 'FEMININO', 4);
