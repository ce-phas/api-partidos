CREATE TABLE partido
(
    id            bigint       NOT NULL AUTO_INCREMENT,
    nome          varchar(100) NOT NULL,
    sigla         varchar(20)  NOT NULL,
    ideologia     varchar(10)  NOT NULL,
    data_fundacao date         NOT NULL,
    PRIMARY KEY (id)
);

CREATE TABLE associado
(
    id         bigint       NOT NULL AUTO_INCREMENT,
    nome       varchar(100) NOT NULL,
    cargo      varchar(20)  NOT NULL,
    data_nasc  date         NOT NULL,
    sexo       varchar(10)  NOT NULL,
    id_partido bigint       NOT NULL,
    PRIMARY KEY (id),
    FOREIGN KEY (id_partido) REFERENCES partido (id)
);

INSERT INTO partido (nome, sigla, ideologia, data_fundacao)
VALUES ('Movimento Democrático Brasileiro', 'MDB', 'CENTRO', '1980-01-15');

INSERT INTO partido (nome, sigla, ideologia, data_fundacao)
VALUES ('Democratas', 'DEM', 'DIREITA', '1985-01-24');

INSERT INTO partido (nome, sigla, ideologia, data_fundacao)
VALUES ('Partido dos Trabalhadores', 'PT', 'ESQUERDA', '1980-02-10');

INSERT INTO associado (nome, cargo, data_nasc, sexo, id_partido)
VALUES ('Simone Tebet', 'Senador', '1970-02-22', 'FEMININO', 1);

INSERT INTO associado (nome, cargo, data_nasc, sexo, id_partido)
VALUES ('Efraim Filho', 'Deputado Estadual', '1979-03-18', 'MASCULINO', 2);

INSERT INTO associado (nome, cargo, data_nasc, sexo, id_partido)
VALUES ('Fátima Bezerra', 'Governador', '1955-09-19', 'FEMININO', 3);
