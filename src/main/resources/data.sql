-- inserir partidos

INSERT INTO partido (nome, sigla, ideologia, data_fundacao)
VALUES ('Avante', 'Avante', 'CENTRO', '1989-05-15');

INSERT INTO partido (nome, sigla, ideologia, data_fundacao)
VALUES ('Cidadania', 'Cidadania', 'CENTRO', '1992-01-26');

INSERT INTO partido (nome, sigla, ideologia, data_fundacao)
VALUES ('Democracia Cristã', 'DC', 'DIREITA', '1995-03-30');

INSERT INTO partido (nome, sigla, ideologia, data_fundacao)
VALUES ('Democratas', 'DEM', 'DIREITA', '1985-01-24');

INSERT INTO partido (nome, sigla, ideologia, data_fundacao)
VALUES ('Movimento Democrático Brasileiro', 'MDB', 'CENTRO', '1980-01-15');

INSERT INTO partido (nome, sigla, ideologia, data_fundacao)
VALUES ('Partido Comunista Brasileiro', 'PCB', 'ESQUERDA', '1922-03-25');

INSERT INTO partido (nome, sigla, ideologia, data_fundacao)
VALUES ('Partido Comunista do Brasil', 'PCdoB', 'ESQUERDA', '1962-02-18');

INSERT INTO partido (nome, sigla, ideologia, data_fundacao)
VALUES ('Partido da Causa Operária', 'PCO', 'ESQUERDA', '1995-12-07');

INSERT INTO partido (nome, sigla, ideologia, data_fundacao)
VALUES ('Partido da Mobilização Nacional', 'PMN', 'ESQUERDA', '1984-04-21');

INSERT INTO partido (nome, sigla, ideologia, data_fundacao)
VALUES ('Partido da Mulher Brasileira', 'PMB', 'DIREITA', '2008-09-13');

INSERT INTO partido (nome, sigla, ideologia, data_fundacao)
VALUES ('Partido da Social Democracia Brasileira', 'PSDB', 'CENTRO', '1988-06-25');

INSERT INTO partido (nome, sigla, ideologia, data_fundacao)
VALUES ('Partido Democrático Trabalhista', 'PDT', 'ESQUERDA', '1979-06-17');

INSERT INTO partido (nome, sigla, ideologia, data_fundacao)
VALUES ('Partido dos Trabalhadores', 'PT', 'ESQUERDA', '1980-02-10');

INSERT INTO partido (nome, sigla, ideologia, data_fundacao)
VALUES ('Partido Liberal', 'PL', 'DIREITA', '2006-10-26');

INSERT INTO partido (nome, sigla, ideologia, data_fundacao)
VALUES ('Partido Novo', 'NOVO', 'DIREITA', '2011-12-02');

INSERT INTO partido (nome, sigla, ideologia, data_fundacao)
VALUES ('Partido Renovador Trabalhista Brasileiro', 'PRTB', 'DIREITA', '1994-11-27');

INSERT INTO partido (nome, sigla, ideologia, data_fundacao)
VALUES ('Partido Republicano da Ordem Social', 'PROS', 'CENTRO', '2010-01-04');

INSERT INTO partido (nome, sigla, ideologia, data_fundacao)
VALUES ('Partido Social Cristão', 'PSC', 'DIREITA', '1985-05-15');

INSERT INTO partido (nome, sigla, ideologia, data_fundacao)
VALUES ('Partido Social Democrático', 'PSD', 'CENTRO', '2011-03-11');

INSERT INTO partido (nome, sigla, ideologia, data_fundacao)
VALUES ('Partido Social Liberal', 'PSL', 'DIREITA', '1994-10-30');

INSERT INTO partido (nome, sigla, ideologia, data_fundacao)
VALUES ('Partido Socialismo e Liberdade', 'PSOL', 'ESQUERDA', '2004-07-07');

INSERT INTO partido (nome, sigla, ideologia, data_fundacao)
VALUES ('Partido Socialista Brasileiro', 'PSB', 'ESQUERDA', '1985-07-02');

INSERT INTO partido (nome, sigla, ideologia, data_fundacao)
VALUES ('Partido Socialista dos Trabalhadores Unificado', 'PSTU', 'ESQUERDA', '1994-06-05');

INSERT INTO partido (nome, sigla, ideologia, data_fundacao)
VALUES ('Partido Trabalhista Brasileiro', 'PTB', 'DIREITA', '1979-11-21');

INSERT INTO partido (nome, sigla, ideologia, data_fundacao)
VALUES ('Partido Trabalhista Cristão', 'PTC', 'DIREITA', '1985-07-11');

INSERT INTO partido (nome, sigla, ideologia, data_fundacao)
VALUES ('Partido Verde', 'PV', 'CENTRO', '1986-01-17');

INSERT INTO partido (nome, sigla, ideologia, data_fundacao)
VALUES ('Patriota', 'Patriota', 'DIREITA', '2011-08-09');

INSERT INTO partido (nome, sigla, ideologia, data_fundacao)
VALUES ('Podemos', 'PODE', 'DIREITA', '1995-05-01');

INSERT INTO partido (nome, sigla, ideologia, data_fundacao)
VALUES ('Progressistas', 'PP', 'DIREITA', '1995-08-08');

INSERT INTO partido (nome, sigla, ideologia, data_fundacao)
VALUES ('Rede Sustentabilidade', 'REDE', 'ESQUERDA', '2013-02-16');

INSERT INTO partido (nome, sigla, ideologia, data_fundacao)
VALUES ('Republicanos', 'Republicanos', 'DIREITA', '2003-12-16');

INSERT INTO partido (nome, sigla, ideologia, data_fundacao)
VALUES ('Solidariedade', 'Solidariedade', 'CENTRO', '2012-10-25');

-- inserir associados

INSERT INTO associado (nome, cargo, data_nasc, sexo, id_partido)
VALUES ('Simone Tebet', 'SENADOR', '1970-02-22', 'FEMININO', 5);

INSERT INTO associado (nome, cargo, data_nasc, sexo, id_partido)
VALUES ('Efraim Filho', 'DEPUTADO_ESTADUAL', '1979-03-18', 'MASCULINO', 4);

INSERT INTO associado (nome, cargo, data_nasc, sexo, id_partido)
VALUES ('Fátima Bezerra', 'GOVERNADOR', '1955-09-19', 'FEMININO', 13);
