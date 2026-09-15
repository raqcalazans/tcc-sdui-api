# Brasileirão API

## Visão Geral

Esta é uma API RESTful desenvolvida com **Java e Spring Boot** como parte do desafio de código da Globo.com. Sua principal responsabilidade é servir dados de jogos de futebol, placares e eventos relacionados para o aplicativo cliente iOS.

A arquitetura foi projetada com foco em flexibilidade e escalabilidade, implementando um modelo de **UI Guiada pelo Servidor (Server-Driven UI)**.

## Arquitetura e Decisões de Design

O projeto segue uma arquitetura em camadas (`Controller` -> `Service` -> `Repository`) com separação clara de responsabilidades.

-   **UI Guiada pelo Servidor (SDUI):** A decisão de arquitetura mais significativa. Em vez de enviar uma lista simples de dados, a API envia uma estrutura de UI pré-agrupada (`GameScreenDTO`). A definição desses grupos (ex: por status ou por rodada) é controlada pelo banco de dados na tabela `filter_groups`. Isso permite que a experiência do usuário no app iOS seja alterada dinamicamente sem a necessidade de um novo deploy do cliente.
-   **Padrão DTO (Data Transfer Object):** As respostas da API são modeladas por DTOs, desacoplando o contrato público da API das entidades internas do banco de dados (JPA).
-   **Injeção de Dependências:** O projeto utiliza injeção via construtor, a prática recomendada pelo Spring, para garantir um código mais limpo, testável e com dependências explícitas.

## Stack de Tecnologias

-   **Java 17**
-   **Spring Boot 3**
-   **Spring Data JPA / Hibernate:** Para a camada de persistência.
-   **PostgreSQL:** Banco de dados utilizado para o ambiente de desenvolvimento.
-   **H2 Database:** Banco de dados em memória utilizado para os testes de integração, garantindo isolamento e rapidez.
-   **Maven:** Gerenciador de dependências e build.
-   **Lombok:** Para reduzir código boilerplate em modelos e DTOs.

## Endpoints da API

-   `GET /games`: Retorna a estrutura completa da tela de jogos, agrupada conforme definido no banco de dados.
-   `GET /teams`: Retorna uma lista de todos os times.

## Como Rodar o Projeto

**Pré-requisitos:**
-   JDK 17 ou superior
-   Maven 3.8+
-   Uma instância do PostgreSQL rodando.

**Passos:**
1.  Crie um banco de dados no PostgreSQL chamado `brasileirao_db`.
2.  Configure suas credenciais de acesso ao banco no arquivo `api/src/main/resources/application.properties`.
3.  Navegue até a pasta `api/brasileirao-api/` no terminal.
4.  Execute o comando: `mvn spring-boot:run`
5.  A API estará disponível em `http://localhost:8080`.

## Testes

O projeto inclui testes de unidade e integração para garantir a qualidade do código. Para assegurar um ambiente de testes rápido, isolado e confiável, a suíte de testes utiliza um **banco de dados em memória H2**, configurado em `src/test/resources/application.properties`. Isso desacopla os testes da necessidade de uma instância externa do PostgreSQL.

-   **Testes de Unidade:** Focados em componentes isolados, como os `Mappers`.
-   **Testes de Integração:** Usam `@WebMvcTest` e `@MockBean` para testar a camada de `Controller` de forma leve.

Para rodar todos os testes, execute o comando na pasta `api/brasileirao-api/`:
```sh
mvn clean test
```

## Melhorias Futuras

Considerando a evolução deste projeto para um ambiente de produção real, as seguintes melhorias seriam implementadas:

-   **Segurança:** Implementação de autenticação e autorização nos endpoints (ex: com Spring Security e JWT) para proteger a API.
-   **Cache Distribuído:** Adição de uma camada de cache com **Redis** para os endpoints mais acessados (como a lista de jogos), reduzindo a carga no banco de dados e melhorando drasticamente a latência.
-   **Containerização:** Criação de um `Dockerfile` para "containerizar" a aplicação, facilitando o deploy e a escalabilidade em ambientes como **Kubernetes**.
-   **Validação de Dados:** Implementação de validações nos DTOs de entrada (para futuros endpoints `POST`/`PUT`) usando `jakarta.validation.constraints`.
-   **Paginação:** Para endpoints que retornam listas grandes (como o de times), seria implementada uma estratégia de paginação para otimizar a performance.