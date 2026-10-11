# Amantes do Futebol — API SDUI

API para cadastro e pesquisa de pessoas fãs de futebol. Esta versão usa Server-Driven UI. As definições SDUI para lista, cadastro, detalhes e edição estão disponíveis na seção “Contrato SDUI para as telas do app”. O modelo, as regras de negócio e os dados persistidos são compatíveis com a API CDUI.

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

## Contrato SDUI para as telas do app

> **Status:** os endpoints de configuração descritos nesta seção estão implementados. Os caminhos legados `/fans/registration-screen` e `/api/v1/fans/registration-screen` também retornam o novo contrato para permitir a migração gradual do app.

Esta seção define o contrato entre a API SDUI e o app iOS. A API descreve a estrutura e os metadados das telas. O cliente interpreta essa configuração, controla a navegação, busca os dados e executa as operações de cadastro, leitura, atualização e exclusão.

## Princípios

- As telas de lista, cadastro, detalhes e edição são descritas pela API.
- A configuração não contém registros de fãs: os dados vêm dos endpoints CRUD.
- A configuração não executa navegação nem operações. Ações identificam intenções; o app decide a navegação e chama o serviço apropriado.
- IDs de componentes e nomes de campos são estáveis e usam `lowerCamelCase`.
- Componentes desconhecidos devem falhar de forma controlada, sem encerrar o app.
- Textos visíveis são fornecidos pela API; identificadores técnicos permanecem independentes da localização.

## Endpoints de configuração

| Método e caminho | Uso |
| --- | --- |
| `GET /api/v1/ui/fans/list-screen` | Estrutura da lista de fãs |
| `GET /api/v1/ui/fans/registration-screen` | Estrutura do cadastro |
| `GET /api/v1/ui/fans/{id}/detail-screen` | Estrutura dos detalhes do fã |
| `GET /api/v1/ui/fans/{id}/edit-screen` | Estrutura do formulário de edição |

Esses endpoints retornam configuração, não dados de fãs. Para dados e operações, o app usa os endpoints de recursos abaixo. O caminho legado `GET /api/v1/fans/registration-screen` também retorna a configuração no formato comum; novos clientes devem usar o endpoint de UI acima.

## Endpoints de dados e operações

| Método e caminho | Uso |
| --- | --- |
| `GET /api/v1/fans` | Listar fãs |
| `GET /api/v1/fans/{id}` | Buscar um fã |
| `POST /api/v1/fans` | Cadastrar fã |
| `PUT /api/v1/fans/{id}` | Atualizar fã |
| `DELETE /api/v1/fans/{id}` | Excluir fã |
| `GET /api/v1/teams` | Listar times disponíveis |

O app é responsável por chamar esses endpoints após interpretar a tela e receber uma ação do usuário.

## Formato comum da configuração

Toda configuração usa o mesmo envelope:

```json
{
  "screenId": "fan-registration",
  "screenTitle": "Cadastro de fã",
  "screenType": "FORM",
  "sections": [
    {
      "id": "personalData",
      "title": "Dados pessoais",
      "components": []
    }
  ],
  "actions": [
    { "id": "submit", "title": "Cadastrar fã", "role": "SUBMIT" }
  ]
}
```

`screenType` aceita `LIST`, `FORM` e `DETAIL`. `sections` preserva a ordem de apresentação. `actions` descreve rótulos e intenção sem delegar execução à API. Campos não aplicáveis a uma tela podem ser omitidos ou enviados como listas vazias.

### Componentes

| `type` | Propriedades principais | Uso |
| --- | --- | --- |
| `TEXT_FIELD` | `id`, `field`, `label`, `required`, `keyboard`, `placeholder` | Entrada de texto |
| `DATE_FIELD` | `id`, `field`, `label`, `required`, `format` | Entrada de data |
| `SELECT` | `id`, `field`, `label`, `required`, `options` ou `optionsSource` | Seleção única |
| `VALUE` | `id`, `field` ou `fields`, `label`, `format`, `optionsSource` | Valor somente leitura; `fields` permite compor localização e `optionsSource` resolve IDs para rótulos |
| `FAN_LIST` | `id`, `titleField`, `subtitleField`, `selectAction` | Lista de registros |

Uma opção possui `id` (valor enviado ao CRUD) e `label` (texto exibido). `optionsSource` referencia uma fonte de dados, por exemplo `GET /api/v1/teams`, sem embutir o catálogo de times na configuração.

## Configuração da lista

A tela informa campos de apresentação e uma ação semântica de seleção. A lista real vem de `GET /api/v1/fans`.

```json
{
  "screenId": "fan-list",
  "screenTitle": "Amantes do Futebol",
  "screenType": "LIST",
  "sections": [
    {
      "id": "fans",
      "components": [
        {
          "id": "fanList",
          "type": "FAN_LIST",
          "titleField": "name",
          "subtitleField": "favoriteTeamId",
          "subtitleOptionsSource": { "path": "/api/v1/teams", "valueField": "id", "labelField": "name" },
          "selectAction": "openFanDetails"
        }
      ]
    }
  ],
  "actions": [
    { "id": "createFan", "title": "Adicionar fã", "role": "NAVIGATE" }
  ]
}
```

Ao tocar em um item, o app navega para detalhes usando o `id` do registro. O botão de adicionar navega para cadastro.

## Configuração do cadastro

```json
{
  "screenId": "fan-registration",
  "screenTitle": "Cadastro de fã",
  "screenType": "FORM",
  "sections": [
    {
      "id": "personalData",
      "title": "Dados pessoais",
      "components": [
        { "id": "name", "type": "TEXT_FIELD", "field": "name", "label": "Nome", "required": true },
        { "id": "email", "type": "TEXT_FIELD", "field": "email", "label": "E-mail", "required": true, "keyboard": "EMAIL" },
        { "id": "birthDate", "type": "DATE_FIELD", "field": "birthDate", "label": "Data de nascimento", "required": true, "format": "dd/MM/yyyy" },
        { "id": "gender", "type": "SELECT", "field": "gender", "label": "Gênero", "required": true, "options": [
          { "id": "Feminino", "label": "Feminino" },
          { "id": "Masculino", "label": "Masculino" },
          { "id": "Não Binário", "label": "Não Binário" }
        ] }
      ]
    },
    {
      "id": "preferences",
      "title": "Preferências e rotina",
      "components": [
        { "id": "favoriteTeamId", "type": "SELECT", "field": "favoriteTeamId", "label": "Time favorito", "required": true, "optionsSource": { "method": "GET", "path": "/api/v1/teams", "valueField": "id", "labelField": "name" } },
        { "id": "city", "type": "TEXT_FIELD", "field": "city", "label": "Cidade", "required": true },
        { "id": "state", "type": "TEXT_FIELD", "field": "state", "label": "Estado", "required": true },
        { "id": "country", "type": "TEXT_FIELD", "field": "country", "label": "País", "required": true },
        { "id": "stadiumFrequency", "type": "SELECT", "field": "stadiumFrequency", "label": "Frequência no estádio", "required": true, "options": [
          { "id": "SEMPRE", "label": "Sempre" },
          { "id": "AS_VEZES", "label": "Às vezes" },
          { "id": "RARAMENTE", "label": "Raramente" },
          { "id": "NUNCA", "label": "Nunca" }
        ] }
      ]
    }
  ],
  "actions": [ { "id": "submit", "title": "Cadastrar fã", "role": "SUBMIT" } ]
}
```

O cliente formata a data para `DD/MM/AAAA` na interface e envia `birthDate` em `AAAA-MM-DD` no contrato REST. O campo `gender` aceita exatamente `Feminino`, `Masculino` ou `Não Binário`. A seleção de time armazena e envia o ID retornado pela API.

## Configuração de detalhes

A API de configuração informa quais campos mostrar e sua ordem. O registro vem de `GET /api/v1/fans/{id}`. A idade é calculada pelo cliente a partir de `birthDate`, sem ser persistida como atributo do fã.

```json
{
  "screenId": "fan-detail",
  "screenTitle": "Detalhes do fã",
  "screenType": "DETAIL",
  "sections": [
    {
      "id": "personalData",
      "title": "Dados pessoais",
      "components": [
        { "id": "name", "type": "VALUE", "field": "name", "label": "Nome" },
        { "id": "email", "type": "VALUE", "field": "email", "label": "E-mail" },
        { "id": "birthDate", "type": "VALUE", "field": "birthDate", "label": "Data de nascimento", "format": "dd/MM/yyyy" },
        { "id": "age", "type": "VALUE", "field": "age", "label": "Idade", "format": "AGE" },
        { "id": "gender", "type": "VALUE", "field": "gender", "label": "Gênero" }
      ]
    },
    {
      "id": "preferences",
      "title": "Preferências e rotina",
      "components": [
        { "id": "favoriteTeamId", "type": "VALUE", "field": "favoriteTeamId", "label": "Time favorito", "optionsSource": { "path": "/api/v1/teams", "valueField": "id", "labelField": "name" } },
        { "id": "location", "type": "VALUE", "fields": ["city", "state", "country"], "label": "Localização", "format": "LOCATION" },
        { "id": "stadiumFrequency", "type": "VALUE", "field": "stadiumFrequency", "label": "Frequência no estádio" }
      ]
    }
  ],
  "actions": [
    { "id": "editFan", "title": "Editar", "role": "NAVIGATE" },
    { "id": "deleteFan", "title": "Excluir", "role": "DELETE", "requiresConfirmation": true }
  ]
}
```

O cliente navega para edição ao receber `editFan` e pede confirmação antes de executar `DELETE` ao receber `deleteFan`.

## Configuração de edição

A configuração da edição usa os mesmos campos e fontes de opções do cadastro, com título e ação de atualização próprios. O app busca os valores atuais em `GET /api/v1/fans/{id}`, preenche os controles e envia o formulário completo por `PUT /api/v1/fans/{id}`.

```json
{
  "screenId": "fan-edit",
  "screenTitle": "Editar fã",
  "screenType": "FORM",
  "sections": [
    {
      "id": "personalData",
      "title": "Dados pessoais",
      "components": [
        { "id": "name", "type": "TEXT_FIELD", "field": "name", "label": "Nome", "required": true },
        { "id": "email", "type": "TEXT_FIELD", "field": "email", "label": "E-mail", "required": true, "keyboard": "EMAIL" },
        { "id": "birthDate", "type": "DATE_FIELD", "field": "birthDate", "label": "Data de nascimento", "required": true, "format": "dd/MM/yyyy" },
        { "id": "gender", "type": "SELECT", "field": "gender", "label": "Gênero", "required": true, "options": [
          { "id": "Feminino", "label": "Feminino" },
          { "id": "Masculino", "label": "Masculino" },
          { "id": "Não Binário", "label": "Não Binário" }
        ] }
      ]
    },
    {
      "id": "preferences",
      "title": "Preferências e rotina",
      "components": [
        { "id": "favoriteTeamId", "type": "SELECT", "field": "favoriteTeamId", "label": "Time favorito", "required": true, "optionsSource": { "method": "GET", "path": "/api/v1/teams", "valueField": "id", "labelField": "name" } },
        { "id": "city", "type": "TEXT_FIELD", "field": "city", "label": "Cidade", "required": true },
        { "id": "state", "type": "TEXT_FIELD", "field": "state", "label": "Estado", "required": true },
        { "id": "country", "type": "TEXT_FIELD", "field": "country", "label": "País", "required": true },
        { "id": "stadiumFrequency", "type": "SELECT", "field": "stadiumFrequency", "label": "Frequência no estádio", "required": true, "options": [
          { "id": "SEMPRE", "label": "Sempre" },
          { "id": "AS_VEZES", "label": "Às vezes" },
          { "id": "RARAMENTE", "label": "Raramente" },
          { "id": "NUNCA", "label": "Nunca" }
        ] }
      ]
    }
  ],
  "actions": [ { "id": "saveChanges", "title": "Salvar alterações", "role": "SUBMIT" } ]
}
```

## Regras de compatibilidade

- O contrato de recurso compartilhado é o DTO REST de fã: `id`, `name`, `email`, `birthDate`, `gender`, `favoriteTeamId`, `city`, `state`, `country` e `stadiumFrequency`.
- `favoriteTeamId` referencia o catálogo de `/api/v1/teams`; nomes de times são para exibição.
- `stadiumFrequency` usa `SEMPRE`, `AS_VEZES`, `RARAMENTE` ou `NUNCA` no payload; os rótulos são apresentados em português.
- Data enviada à API: ISO `AAAA-MM-DD`. Data apresentada: `DD/MM/AAAA`.
- A API pode acrescentar propriedades opcionais ao contrato de tela, mas não deve alterar o significado dos campos existentes sem versionar a configuração.
- O app deve tratar carregamento, configuração inválida, componente não suportado, erro de rede e lista vazia.
