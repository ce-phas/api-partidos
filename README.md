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

Os testes foram implementados utilizando o JUnit 5, com expectativa de cobertura mínima de 50% e foco nas classes
[Service](https://github.com/pedro-as/compass-sprint4/tree/master/src/test/java/uol/compass/partidos/service)
e [Repository](https://github.com/pedro-as/compass-sprint4/tree/master/src/test/java/uol/compass/partidos/repository).
Houve implementação parcial de teste da classe
[Controller](https://github.com/pedro-as/compass-sprint4/tree/master/src/test/java/uol/compass/partidos/controller),
para os métodos `POST` da classe `AssociadoController`.

### Cobertura de testes

| Class       | Method       | Line         |
|-------------|--------------|--------------|
| 95% (21/22) | 76% (80/105) | 79%(183/229) |

## Operações suportadas: partidos

### Listar todos

#### Requisição

`GET /partidos`

#### Resposta

##### 200

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

#### Filtrar por ideologia

`GET /partidos?ideologia={ideologia}`

Valores aceitos (*case insensitive*):
```
centro, esquerda, direita
```

### Procurar por ID

#### Requisição

`GET /partidos/{id}`

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
{
  "statusCode": 404,
  "timestamp": "2022-01-01T00:00:00.000+00:00",
  "message": "ID não encontrado",
  "description": "uri=/partidos/1"
}
```

### Listar associados do partido

#### Requisição

`GET /partidos/{id}/associados`

#### Resposta

##### 200

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

##### 404

```json
{
  "statusCode": 404,
  "timestamp": "2022-01-01T00:00:00.000+00:00",
  "message": "ID não encontrado",
  "description": "uri=/partidos/1"
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

##### 400

```json
{
  "statusCode": 400,
  "timestamp": "2022-01-01T00:00:00.000+00:00",
  "fieldErrors": [
    {
      "field": "campo",
      "error": "descrição do erro"
    }
  ],
  "description": "uri=/partidos"
}
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

##### 400

```json
{
  "statusCode": 400,
  "timestamp": "2022-01-01T00:00:00.000+00:00",
  "fieldErrors": [
    {
      "field": "campo",
      "error": "descrição do erro"
    }
  ],
  "description": "uri=/partidos"
}
```

##### 404

```json
{
  "statusCode": 404,
  "timestamp": "2022-01-01T00:00:00.000+00:00",
  "message": "ID não encontrado",
  "description": "uri=/partidos/1"
}
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
{
  "statusCode": 404,
  "timestamp": "2022-01-01T00:00:00.000+00:00",
  "message": "ID não encontrado",
  "description": "uri=/partidos/1"
}
```

## Operações suportadas: associados

### Listar todos

#### Requisição

`GET /associados`

#### Resposta

##### 200

```json
[
  {
    "id": 1,
    "nome": "Fulano de Tal",
    "cargo": "Prefeito",
    "dataNasc": "31/12/1950",
    "sexo": "Masculino"
  }
]
```

#### Ordenação e filtragem

##### Filtrar por cargo

`GET /associados?cargo={cargo}`

Valores aceitos (*case insensitive*):
```
vereador, prefeito, deputado estadual, deputado federal, senador, governador, presidente, nenhum
```

##### Ordenar por nome

`GET /associados?sort=true`

### Procurar por ID

#### Requisição

`GET /associados/{id}`

#### Resposta

#### 200

```json
{
  "id": 1,
  "nome": "Fulano de Tal",
  "cargo": "Prefeito",
  "dataNasc": "31/12/1950",
  "sexo": "Masculino"
}
```

##### 404

```json
{
  "statusCode": 404,
  "timestamp": "2022-01-01T00:00:00.000+00:00",
  "message": "ID não encontrado",
  "description": "uri=/associados/1"
}
```

### Cadastrar associado

#### Requisição

`POST /associados`

```curl
curl -X 'POST' \
  'http://localhost:8080/associados' \
  -H 'accept: application/json' \
  -H 'Content-Type: application/json' \
  -d '{
  "nome": "Fulano de Tal",
  "cargo": "Prefeito",
  "dataNasc": "31/12/1950",
  "sexo": "Masculino"
}'
```

#### Resposta

##### 201

```json
{
  "id": 1,
  "nome": "Fulano de Tal",
  "cargo": "Prefeito",
  "dataNasc": "31/12/1950",
  "sexo": "Masculino"
}
```

##### 400

```json
{
  "statusCode": 400,
  "timestamp": "2022-01-01T00:00:00.000+00:00",
  "fieldErrors": [
    {
      "field": "campo",
      "error": "descrição do erro"
    }
  ],
  "description": "uri=/associados"
}
```

### Vincular associado a um partido

#### Requisição

`POST /associados/partidos`

```curl
curl -X 'POST' \
  'http://localhost:8080/associados' \
  -H 'accept: application/json' \
  -H 'Content-Type: application/json' \
  -d '{
  "idAssociado": 1,
  "idPartido": 1
}'
```

#### Resposta

##### 200

```json
{
  "id": 1,
  "nome": "Fulano de Tal",
  "cargo": "Prefeito",
  "dataNasc": "31/12/1950",
  "sexo": "Masculino",
  "partido": {
    "id": 1,
    "nome": "Partido Central do Meio",
    "sigla": "PCM",
    "ideologia": "Centro",
    "dataFundacao": "31/12/1900"
  }
}
```

##### 404: associado não encontrado

```json
{
  "statusCode": 404,
  "timestamp": "2022-01-01T00:00:00.000+00:00",
  "message": "ID não encontrado: associado",
  "description": "uri=/associados/partidos"
}
```

##### 404: partido não encontrado

```json
{
  "statusCode": 404,
  "timestamp": "2022-01-01T00:00:00.000+00:00",
  "message": "ID não encontrado: partido",
  "description": "uri=/associados/partidos"
}
```

### Atualizar associado

#### Requisição

`PUT /associados/{id}`

```curl
curl -X 'PUT' \
  'http://localhost:8080/associados/1' \
  -H 'accept: application/json' \
  -H 'Content-Type: application/json' \
  -d '{
  "nome": "Fulana da Silva",
  "cargo": "Deputado Federal",
  "dataNasc": "01/01/1951",
  "sexo": "Feminino"
}'
```

#### Resposta

##### 200

```json
{
  "id": 1,
  "nome": "Fulana da Silva",
  "cargo": "Deputado Federal",
  "dataNasc": "01/01/1951",
  "sexo": "Feminino"
}
```

##### 400

```json
{
  "statusCode": 400,
  "timestamp": "2022-01-01T00:00:00.000+00:00",
  "fieldErrors": [
    {
      "field": "campo",
      "error": "descrição do erro"
    }
  ],
  "description": "uri=/associados"
}
```

##### 404

```json
{
  "statusCode": 404,
  "timestamp": "2022-01-01T00:00:00.000+00:00",
  "message": "ID não encontrado",
  "description": "uri=/associados/1"
}
```

### Remover associado

#### Requisição

`DELETE /associados/{id}`

#### Resposta

##### 200

```json
{
  "id": 1,
  "nome": "Fulano de Tal",
  "cargo": "Prefeito",
  "dataNasc": "31/12/1950",
  "sexo": "Masculino"
}
```

##### 404

```json
{
  "statusCode": 404,
  "timestamp": "2022-01-01T00:00:00.000+00:00",
  "message": "ID não encontrado",
  "description": "uri=/associados/1"
}
```

### Remover filiação

#### Requisição

`DELETE /associados/{id}/partidos`

#### Resposta

##### 200

```json
{
  "id": 1,
  "nome": "Fulano de Tal",
  "cargo": "Prefeito",
  "dataNasc": "31/12/1950",
  "sexo": "Masculino",
  "partido": null
}
```

##### 404

```json
{
  "statusCode": 404,
  "timestamp": "2022-01-01T00:00:00.000+00:00",
  "message": "ID não encontrado",
  "description": "uri=/associados/1/partidos"
}
```
