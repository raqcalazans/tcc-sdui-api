# Amantes do Futebol — API SDUI

API para cadastro e pesquisa de pessoas fãs de futebol. Esta versão usa Server-Driven UI para entregar ao app a definição da tela de cadastro; o modelo, as regras de negócio e os dados persistidos são compatíveis com a API CDUI.

## Cadastro

O registro contém `id`, `name`, `email`, `birthDate`, `gender`, `favoriteTeamId`, `city`, `state`, `country` e `stadiumFrequency`. O time favorito é escolhido no catálogo fixo de participantes da Série A de 2026. A frequência no estádio aceita `SEMPRE`, `AS_VEZES`, `RARAMENTE` e `NUNCA`.

O endpoint SDUI entrega uma tela com campos para todos esses dados. O formato de nascimento informado pelo app é `AAAA-MM-DD`.

## Endpoints

| Método | Endpoint | Descrição |
|---|---|---|
| `GET` | `/fans/registration-screen` | Retorna a definição SDUI do formulário |
| `POST` | `/fans` | Cadastra uma pessoa |
| `GET` | `/fans` | Lista todas as pessoas |
| `GET` | `/fans/{id}` | Consulta uma pessoa |
| `PUT` | `/fans/{id}` | Atualiza os dados |
| `DELETE` | `/fans/{id}` | Exclui uma pessoa |
| `GET` | `/teams` | Lista os times permitidos |

As rotas versionadas `/api/v1/fans` e `/api/v1/teams` também são aceitas para compatibilidade com a API CDUI. O botão definido no formulário SDUI envia o cadastro a `/fans` para manter compatibilidade com o app existente.

## Tecnologias e execução

- Java 17, Spring Boot 3.5.3, Spring Web, Spring Data JPA e Bean Validation
- PostgreSQL e Maven

As duas APIs usam o mesmo banco `amantes_do_futebol` e as tabelas `football_fans` e `teams`. Crie esse banco uma vez no PostgreSQL, depois execute:

```bash
./mvnw spring-boot:run
```

Use `DB_URL`, `DB_USERNAME` e `DB_PASSWORD` para sobrescrever a conexão. A documentação OpenAPI fica em `/swagger-ui.html`.

## Testes e cobertura

Execute `./mvnw clean test`. O relatório JaCoCo é gerado em `target/site/jacoco/index.html`.
