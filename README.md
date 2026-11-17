# EspelhoMeu — API RESTful

API backend do **EspelhoMeu**, plataforma de consultoria de beleza com foco em recomendações personalizadas de corte, cor, penteado e maquiagem, além de simulações e produtos associados.

> Projeto acadêmico desenvolvido no contexto da **FIAP**.

---

## Sumário

- [Sobre](#sobre)
- [Funcionalidades](#funcionalidades)
- [Tecnologias](#tecnologias)
- [Arquitetura](#arquitetura)
- [Pré-requisitos](#pré-requisitos)
- [Configuração](#configuração)
- [Como executar](#como-executar)
- [Endpoints](#endpoints)
- [Estrutura do projeto](#estrutura-do-projeto)
- [Tratamento de erros](#tratamento-de-erros)
- [Licença](#licença)

---

## Sobre

O EspelhoMeu oferece uma API REST para gerenciar usuários, perfis capilares e de maquiagem, catálogos (cortes, cores, penteados, maquiagens e produtos), consultorias, recomendações com vínculo a produtos, simulações visuais, favoritos e avaliações.

A comunicação é feita via HTTP/JSON, com validação de entrada, mapeamento DTO ↔ modelo e persistência em banco **Oracle** por meio de **JDBC**.

---

## Funcionalidades

- Cadastro e gestão de **usuários**
- Perfis de **cabelo** e **maquiagem**
- Catálogo de **cortes**, **cores**, **penteados**, **maquiagens** e **produtos**
- **Consultorias** capilar e de maquiagem
- **Recomendações** (corte, cor, penteado e maquiagem) com associação a produtos
- **Simulações** (corte, cor e penteado vinculados ao usuário)
- **Favoritos** e **avaliações**
- Validação de payloads com Bean Validation
- Tratamento global de exceções com respostas padronizadas
- CORS liberado para consumo por front-ends

---

## Tecnologias

| Tecnologia | Versão / uso |
|---|---|
| Java | 21 |
| Spring Boot | 4.1.2 |
| Spring Web MVC | API REST |
| Spring JDBC | Acesso ao banco |
| Oracle JDBC (ojdbc17) | Driver Oracle |
| ModelMapper | Mapeamento DTO ↔ entidade |
| Bean Validation | Validação de entrada |
| Maven | Build e dependências |
| Docker | Empacotamento e execução |

---

## Arquitetura

Fluxo típico da aplicação:

```
Cliente HTTP
    ↓
Controller (REST + DTOs + validação)
    ↓
DAO (JDBC / SQL)
    ↓
Oracle Database
```

Camadas principais:

| Pacote | Responsabilidade |
|---|---|
| `controller` | Endpoints REST e status HTTP |
| `dto` | Contratos de entrada (`Cadastro*`, `Atualizacao*`) e saída (`Detalhes*`) |
| `model` | Modelos de domínio |
| `dao` | Persistência com JDBC e SQL nativo |
| `exception` / `handler` | Exceções de negócio e tratamento global |
| `config` | ModelMapper e CORS |

---

## Pré-requisitos

- **JDK 21+**
- **Maven 3.9+**
- **Oracle Database** acessível
- **Docker** (opcional, para execução em container)

---

## Configuração

A aplicação lê as credenciais do banco por variáveis de ambiente.

| Variável | Obrigatória | Descrição | Padrão |
|---|---|---|---|
| `DATABASE_URL` | Não | JDBC URL do Oracle | `jdbc:oracle:thin:@oracle.fiap.com.br:1521:orcl` |
| `DATABASE_USERNAME` | Sim | Usuário do banco | — |
| `DATABASE_PASSWORD` | Sim | Senha do banco | — |
| `PORT` | Não | Porta HTTP | `8080` |

### Exemplo (PowerShell)

```powershell
$env:DATABASE_USERNAME = "seu_usuario"
$env:DATABASE_PASSWORD = "sua_senha"
$env:DATABASE_URL = "jdbc:oracle:thin:@host:1521:orcl"
$env:PORT = "8080"
```

### Exemplo (Bash)

```bash
export DATABASE_USERNAME=seu_usuario
export DATABASE_PASSWORD=sua_senha
export DATABASE_URL=jdbc:oracle:thin:@host:1521:orcl
export PORT=8080
```

---

## Como executar

### Local (Maven)

```bash
git clone https://github.com/MatheusPSFranco/EspelhoMeu-API-Restful.git
cd EspelhoMeu-API-Restful

# configurar variáveis de ambiente (ver seção acima)

mvn spring-boot:run
```

A API ficará disponível em `http://localhost:8080`.

### Build do JAR

```bash
mvn clean package -DskipTests
java -jar target/espelhomeu-0.0.1-SNAPSHOT.jar
```

### Docker

```bash
docker build -t espelhomeu-api .

docker run -p 8080:8080 \
  -e DATABASE_USERNAME=seu_usuario \
  -e DATABASE_PASSWORD=sua_senha \
  -e DATABASE_URL=jdbc:oracle:thin:@host:1521:orcl \
  espelhomeu-api
```

---

## Endpoints

Base URL: `http://localhost:8080`

Todos os recursos seguem o padrão CRUD:

| Método | Rota | Descrição |
|---|---|---|
| `GET` | `/{recurso}` | Lista registros |
| `GET` | `/{recurso}/{id}` | Busca por ID |
| `POST` | `/{recurso}` | Cria registro |
| `PUT` | `/{recurso}/{id}` | Atualiza registro |
| `DELETE` | `/{recurso}/{id}` | Remove registro |

### Recursos disponíveis

| Recurso | Path |
|---|---|
| Usuários | `/usuario` |
| Perfil de cabelo | `/perfilcabelo` |
| Perfil de maquiagem | `/perfilmaquiagem` |
| Cortes | `/corte` |
| Cores de cabelo | `/corcabelo` |
| Penteados | `/penteado` |
| Maquiagens | `/maquiagem` |
| Produtos | `/produto` |
| Consultoria capilar | `/consultoriacapilar` |
| Consultoria de maquiagem | `/consultoriamaquiagem` |
| Recomendação de corte | `/recomendacaocorte` |
| Recomendação de cor | `/recomendacaocor` |
| Recomendação de penteado | `/recomendacaopenteado` |
| Recomendação de maquiagem | `/recomendacaomaquiagem` |
| Recomendação corte × produto | `/recomendacaocorteproduto` |
| Recomendação cor × produto | `/recomendacaocorproduto` |
| Recomendação penteado × produto | `/recomendacaopenteadoproduto` |
| Simulações | `/simulacao` |
| Favoritos | `/favoritos` |
| Avaliações | `/avaliacao` |

> Relacionamentos N:N (recomendação × produto) usam chaves compostas no path, por exemplo:  
> `GET /recomendacaocorteproduto/{idProduto}/{idRecomendacaoCorte}`

### Exemplo de requisição

```http
POST /usuario
Content-Type: application/json

{
  "nome": "Maria Silva",
  "email": "maria@email.com",
  "data": "1998-05-12",
  "telefone": "11999999999"
}
```

Respostas de criação retornam `201 Created` com header `Location` apontando para o novo recurso.

---

## Estrutura do projeto

```
EspelhoMeu-API-Restful/
├── Dockerfile
├── pom.xml
├── src/
│   ├── main/
│   │   ├── java/br/com/fiap/espelhomeu/
│   │   │   ├── EspelhomeuApplication.java
│   │   │   ├── config/          # CORS e ModelMapper
│   │   │   ├── controller/      # Endpoints REST
│   │   │   ├── dao/             # Acesso JDBC ao Oracle
│   │   │   ├── dto/             # DTOs de entrada e saída
│   │   │   ├── exception/       # Exceções e ErrorResponseDto
│   │   │   ├── handler/         # @RestControllerAdvice
│   │   │   └── model/           # Entidades de domínio
│   │   └── resources/
│   │       └── application.properties
│   └── test/
└── README.md
```

---

## Tratamento de erros

A API centraliza erros em `ApiExceptionHandler` e responde com `ErrorResponseDto`:

| Situação | Status |
|---|---|
| Entidade não encontrada | `404 Not Found` |
| Dados inválidos (validação) | `400 Bad Request` |
| Parâmetro com tipo inválido | `400 Bad Request` |
| Rota inexistente | `404 Not Found` |
| Método HTTP não permitido | `405 Method Not Allowed` |
| Erro de banco de dados | `500 Internal Server Error` |

Exemplo de resposta:

```json
{
  "erro": "Entidade não encontrada",
  "detalhe": "Usuário não encontrado"
}
```

---

## Licença

Projeto acadêmico — FIAP. Uso educacional.
