# API de Partidos

### Sprint 4 - Trilha Spring Boot - Compass.Uol

---

Esta API simples foi desenvolvida com [Spring Boot](https://spring.io/projects/spring-boot),
com implementação de testes unitários e de integração utilizando o [JUnit 5](https://junit.org/junit5/),
para a trilha de desenvolvimento em Spring Boot da [Compass.Uol](https://compass.uol/).

A API permite ao usuário criar, recuperar, atualizar e remover dados de partidos políticos e
de seus associados em um banco de dados H2 local. A aplicação deve ser executada por meio do
arquivo `PartidosApplication.java`.

## Estrutura do projeto
```
.src/main/java/uol/compass/partidos
│
├── controller                  # Processamento de requisições
├── dto                         # Dados enviados ao cliente
│   └── form                    # Dados recebidos do cliente
├── entity                      # Representação de dados persistidos
│   └── enums                   # Constantes de domínio
├── exception                   # Tratamento de exceções da aplicação
│   └── dto                     # Erros de campo enviados ao cliente
├── repository                  # Encapsulamento do acesso à persistência
├── service                     # Implementação das regras de negócio
│
└── PartidosApplication.java
```
- [controller](https://github.com/pedro-as/compass-sprint4/tree/master/src/main/java/uol/compass/partidos/controller)
- [dto](https://github.com/pedro-as/compass-sprint4/tree/master/src/main/java/uol/compass/partidos/dto)
/ [form](https://github.com/pedro-as/compass-sprint4/tree/master/src/main/java/uol/compass/partidos/dto/form)
- [entity](https://github.com/pedro-as/compass-sprint4/tree/master/src/main/java/uol/compass/partidos/entity)
/ [enums](https://github.com/pedro-as/compass-sprint4/tree/master/src/main/java/uol/compass/partidos/entity/enums)
- [exception](https://github.com/pedro-as/compass-sprint4/tree/master/src/main/java/uol/compass/partidos/exception)
/ [dto](https://github.com/pedro-as/compass-sprint4/tree/master/src/main/java/uol/compass/partidos/exception/dto)
- [repository](https://github.com/pedro-as/compass-sprint4/tree/master/src/main/java/uol/compass/partidos/repository)
- [service](https://github.com/pedro-as/compass-sprint4/tree/master/src/main/java/uol/compass/partidos/service)

## Testes

### Cobertura de testes

| Class       | Method       | Line         |
|-------------|--------------|--------------|
| 95% (21/22) | 76% (80/105) | 79%(183/229) |

## Operações suportadas: partidos

### Listar todos

#### Requisição

`GET /partidos`

#### Resposta

```json
[
  {
    "id": 1,
    "nome": "Partido Central do Meio",
    "sigla": "PCM",
    "ideologia": "Centro",
    "dataFundacao": "31/12/1900"
  }
]
```

#### Ordenação e filtragem

##### Filtrar por ideologia

`GET /partidos?ideologia={centro,direita,esquerda}`

### Procurar por ID

#### Requisição

`GET /partidos/{id}`

#### Resposta

```json
{
  "id": 1,
  "nome": "Partido Central do Meio",
  "sigla": "PCM",
  "ideologia": "Centro",
  "dataFundacao": "31/12/1900"
}
```

### Listar associados do partido

#### Requisição

`GET /partidos/{id}/associados`

#### Resposta

```json
{
  "id": 1,
  "nome": "Partido Central do Meio",
  "sigla": "PCM",
  "ideologia": "Centro",
  "dataFundacao": "31/12/1900",
  "associados": []
}
```

### Cadastrar partido

#### Requisição

`POST /partidos`

```curl
curl -X 'POST' \
  'http://localhost:8080/partidos' \
  -H 'accept: application/json' \
  -H 'Content-Type: application/json' \
  -d '{
  "nome": "Partido Central do Meio",
  "sigla": "PCM",
  "ideologia": "Centro",
  "dataFundacao": "31/12/1900"
}'
```

#### Resposta

##### 201

```json
{
  "id": 1,
  "nome": "Partido Central do Meio",
  "sigla": "PCM",
  "ideologia": "Centro",
  "dataFundacao": "31/12/1900"
}
```

##### 404

```json
"ID não encontrado"
```

### Atualizar partido

#### Requisição

`PUT /partidos/{id}`

```curl
curl -X 'PUT' \
  'http://localhost:8080/partidos/1' \
  -H 'accept: application/json' \
  -H 'Content-Type: application/json' \
  -d '{
  "nome": "Partido da Esquerda Canhota",
  "sigla": "PEC",
  "ideologia": "Esquerda",
  "dataFundacao": "01/01/1901"
}'
```

#### Resposta

##### 200

```json
{
  "id": 1,
  "nome": "Partido da Esquerda Canhota",
  "sigla": "PEC",
  "ideologia": "Esquerda",
  "dataFundacao": "01/01/1901"
}
```

##### 404

```json
"ID não encontrado"
```

### Remover partido

#### Requisição

`DELETE /partidos/{id}`

#### Resposta

##### 200

```json
{
  "id": 1,
  "nome": "Partido Central do Meio",
  "sigla": "PCM",
  "ideologia": "Centro",
  "dataFundacao": "31/12/1900"
}
```

##### 404

```json
"ID não encontrado"
```