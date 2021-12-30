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
├── controller                  # Processamento de requisições
│
├── dto                         # Dados enviados ao cliente
│   └── form                    # Dados recebidos do cliente
│
├── entity                      # Representação de dados persistidos
│   └── enums                   # Constantes de domínio
│
├── exception                   # Tratamento de exceções da aplicação
│   └── dto                     # Erros de campo enviados ao cliente
│
├── repository                  # Encapsulamento do acesso à persistência
│
├── service                     # Implementação das regras de negócio
│
└── PartidosApplication.java
```

## Utilização da API

A aplicação pode ser utilizada localmente (em `localhost:8080`), com o
[Apache Tomcat](https://tomcat.apache.org/tomcat-9.0-doc/).
